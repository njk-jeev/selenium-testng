package com.yahoo.ycem.tests.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.yahoo.ycem.tests.beans.contact.Category;
import com.yahoo.ycem.tests.beans.contact.ChannelMapping;
import com.yahoo.ycem.tests.beans.contact.Product;
import com.yahoo.ycem.tests.beans.contact.SubCategory;
import com.yahoo.ycem.tests.beans.contact.Version;
import com.yahoo.ycem.tests.beans.contact.YChannelMapping;

public class CacheManager {
	
	private static final Logger LOG = LogManager.getLogger(CacheManager.class);
	private static Properties config;
	
	static{
		try {
			config = new Properties();
			String basePath = System.getProperty("buildDirectory");
			File configFile = new File(basePath + "/classes/config.properties");
			//config.load(CacheManager.class.getResourceAsStream("config.properties"));
			config.load(new FileInputStream(configFile));
			LOG.debug("loaded config successfully: " + config.getProperty("user"));
			
			Class.forName("oracle.jdbc.OracleDriver");
			
		} catch (IOException e) {
			e.printStackTrace();
			LOG.error("unable to load properties file with JDBC config. " + e.getMessage());
		} catch (ClassNotFoundException e) {
			LOG.error("unable to load JDBC DriverManager. " + e.getMessage());
		}
		
	}
	
	
	private static Cache cache = new Cache();

	public static Map<String, Product> getProductsFromLocale(String locale){
		return cache.localeProductMap.get(locale);
	}
	public static Map<String, Version> getVersionsFromProductLocale(String locale, String productRefKey){
		return cache.localeProductVersionMap.get(locale+productRefKey);
	}
	public static Map<String, Category> getCategoriesFromProductLocaleVersion(String locale, String productRefKey, String versionRefKey){
		if(versionRefKey == null) versionRefKey = "";
		return cache.localeProductVersionCategoryMap.get(locale+productRefKey+versionRefKey);
	}
	public static Map<String, SubCategory> getSubCategoriesFromProductLocaleVersionCategory(String locale, String productRefKey, String versionRefKey, String catRefKey){
		if(versionRefKey == null) versionRefKey = "";
		return cache.localeProductVersionCategorySubCategoryMap.get(locale+productRefKey+versionRefKey+catRefKey);
	}
	public static ChannelMapping getMapping(String locale, String productRefKey, String versionRefKey, String catRefKey, String subCategoryId){
		if(versionRefKey == null) versionRefKey = "";
		if(catRefKey == null) catRefKey = "";
		if(subCategoryId == null) subCategoryId = "";
		return cache.channelMappingMap.get(locale+productRefKey+versionRefKey+catRefKey+subCategoryId);
	}


	private static class Cache {
	
		private static final Log s_log = LogFactory.getLog(Cache.class);

		private static final String LOCALE_QUERY = "select LOCALECODE from LOCALE where active='Y' order by LOCALECODE";
		private static final String PRODUCT_QUERY = "select distinct TR1.NAME as PRODUCT, T1.REFERENCEKEY as REFERENCEKEY, SP1.STARID as STARID from STARCATEGORYMAPPING SCM " +
			"JOIN TAGRESOURCE TR1 ON SCM.PRODUCTID = TR1.TAGID " +
			"JOIN TAG T1 ON SCM.PRODUCTID = T1.RECORDID " +
			"JOIN STARPRODUCT SP1 ON SCM.PRODUCTID = SP1.RECORDID " +
			"JOIN STARLOCALEMAPPING SLM ON SLM.MAPPINGID = SCM.RECORDID AND SLM.LOCALECODE = ? " +
			"WHERE SCM.ACTIVE = 'Y' AND TR1.LOCALEID = ? order by PRODUCT "; 
		private static final String VERSION_QUERY = "select distinct TR1.NAME as VERSION, T1.REFERENCEKEY as REFERENCEKEY, SP2.STARID as STARID from STARCATEGORYMAPPING SCM " +
			"JOIN TAGRESOURCE TR1 ON SCM.VERSIONID = TR1.TAGID " +
			"JOIN TAG T1 ON SCM.VERSIONID = T1.RECORDID  " +
			"JOIN STARPRODUCT SP2 ON SCM.VERSIONID = SP2.RECORDID " +
			"JOIN STARLOCALEMAPPING SLM ON SLM.MAPPINGID = SCM.RECORDID AND SLM.LOCALECODE = ? " +
			"JOIN TAG T2 ON SCM.PRODUCTID = T2.RECORDID " +
			"WHERE SCM.ACTIVE = 'Y' AND TR1.LOCALEID = ? and T2.REFERENCEKEY = ? order by VERSION";
		private static final String CATEGORY_WITH_VERSION = "select distinct TR1.NAME as CATEGORY, T1.REFERENCEKEY as REFERENCEKEY " +
				", SLC.STARID as STARID " +
				"from STARCATEGORYMAPPING SCM " +
			"JOIN TAGRESOURCE TR1 ON SCM.TOPICID = TR1.TAGID " +
			"JOIN TAG T1 ON SCM.TOPICID = T1.RECORDID " +
			"JOIN STARL2CATEGORY SLC ON SCM.TOPICID = SLC.TAGID " +
			"JOIN STARLOCALEMAPPING SLM ON SLM.MAPPINGID = SCM.RECORDID AND SLM.LOCALECODE = ? " +
			"JOIN TAG T2 ON SCM.PRODUCTID = T2.RECORDID " +
			"JOIN TAG T3 ON SCM.VERSIONID = T3.RECORDID " +
			"WHERE SCM.ACTIVE = 'Y' AND TR1.LOCALEID = ? and T2.REFERENCEKEY = ? and T3.REFERENCEKEY = ?  order by CATEGORY ";
		private static final String CATEGORY_WITHOUT_VERSION = "select distinct TR1.NAME as CATEGORY, T1.REFERENCEKEY as REFERENCEKEY " +
				", SLC.STARID as STARID  " +
				"from STARCATEGORYMAPPING SCM " +
			"JOIN TAGRESOURCE TR1 ON SCM.TOPICID = TR1.TAGID " +
			"JOIN TAG T1 ON SCM.TOPICID = T1.RECORDID " +
			"JOIN STARL2CATEGORY SLC ON SCM.TOPICID = SLC.TAGID " +
			"JOIN STARLOCALEMAPPING SLM ON SLM.MAPPINGID = SCM.RECORDID AND SLM.LOCALECODE = ? " +
			"JOIN TAG T2 ON SCM.PRODUCTID = T2.RECORDID " +
			"WHERE SCM.ACTIVE = 'Y' AND TR1.LOCALEID = ? and T2.REFERENCEKEY = ? order by CATEGORY ";
		private static final String SUBCATEGORY_WITH_VERSION = "select distinct SCL.CATEGORYID as SUBCATEGORYID, SCL.STARLABEL AS ISSUE, SCL.STARDESCRIPTION as SUBCATEGORY, SC2.STARID as STARID, SC1.STARID as L1STARID " +
			"from STARCATEGORYMAPPING SCM " +
			"JOIN STARCATEGORYLOCALIZED SCL ON SCM.CATEGORYID = SCL.CATEGORYID " +
			"JOIN STARCATEGORY SC2 ON SCM.CATEGORYID = SC2.RECORDID " +
			"JOIN STARCATEGORYLINK SCLN ON SCLN.LEVEL3ID = SCM.CATEGORYID " +
	        "JOIN STARCATEGORY SC1 ON SC1.RECORDID = SCLN.LEVEL1ID " +
			"JOIN STARLOCALEMAPPING SLM ON SLM.MAPPINGID = SCM.RECORDID AND SLM.LOCALECODE = ? " +
			"JOIN TAG T1 ON SCM.PRODUCTID = T1.RECORDID " +
			"JOIN TAG T2 ON SCM.VERSIONID = T2.RECORDID " +
			"JOIN TAG T3 ON SCM.TOPICID = T3.RECORDID " +
			"WHERE SCM.ACTIVE = 'Y' and T1.REFERENCEKEY = ? and T2.REFERENCEKEY = ? and T3.REFERENCEKEY = ? " +
			"AND SCL.STARLANGUAGE = ? order by ISSUE ";
		private static final String SUBCATEGORY_WITHOUT_VERSION = "select distinct SCL.CATEGORYID as SUBCATEGORYID,  SCL.STARLABEL AS ISSUE,SCL.STARDESCRIPTION as SUBCATEGORY, SC2.STARID as STARID, SC1.STARID as L1STARID " +
			"from STARCATEGORYMAPPING SCM " +
			"JOIN STARCATEGORYLOCALIZED SCL ON SCM.CATEGORYID = SCL.CATEGORYID " +
			"JOIN STARCATEGORY SC2 ON SCM.CATEGORYID = SC2.RECORDID " +
			"JOIN STARCATEGORYLINK SCLN ON SCLN.LEVEL3ID = SCM.CATEGORYID " +
	        "JOIN STARCATEGORY SC1 ON SC1.RECORDID = SCLN.LEVEL1ID " +
			"JOIN STARLOCALEMAPPING SLM ON SLM.MAPPINGID = SCM.RECORDID AND SLM.LOCALECODE = ? " +
			"JOIN TAG T1 ON SCM.PRODUCTID = T1.RECORDID " +
			"JOIN TAG T3 ON SCM.TOPICID = T3.RECORDID " +
			"WHERE SCM.ACTIVE = 'Y' and T1.REFERENCEKEY = ? and T3.REFERENCEKEY = ? " +
			"AND SCL.STARLANGUAGE = ? order by ISSUE ";
		private static final String CHANNELMAPPING_WITH_VERSION = "select SLM.RECORDID AS STARLOCALEMAPPINGID, SLM.REQUIRESLOGON, SLM.UNSUPPORTEDID, SLM.REQUIRESUDBCHECK, SLM.REQUIRESDEFLECTION, SLM.DEFLECTIONCONTENTID, SLM.HASDYNAMIC, SCHM.CHANNELS, SDIM.DTENABLED, SDIM.ICVALUE " +
			"from STARCATEGORYMAPPING SCM " +
			"JOIN STARCATEGORYLOCALIZED SCL ON SCM.CATEGORYID = SCL.CATEGORYID " +
			"JOIN STARLOCALEMAPPING SLM ON SLM.MAPPINGID = SCM.RECORDID AND SLM.LOCALECODE = ? " +
			"LEFT JOIN STARCHANNELMAPPING SCHM ON SCHM.LOCALEMAPPINGID = SLM.RECORDID " +
			"LEFT JOIN STARDTICMAPPING SDIM ON SDIM.SLMMAPPINGID = SLM.RECORDID " +
			"JOIN TAG T1 ON SCM.PRODUCTID = T1.RECORDID " +
			"JOIN TAG T2 ON SCM.VERSIONID = T2.RECORDID " +
			"JOIN TAG T3 ON SCM.TOPICID = T3.RECORDID " +
			"WHERE SCM.ACTIVE = 'Y' and T1.REFERENCEKEY = ? and T2.REFERENCEKEY = ? " +
			"and T3.REFERENCEKEY = ? and SCL.CATEGORYID  = ? " +
			"AND SCL.STARLANGUAGE = ? ";
		private static final String CHANNELMAPPING_WITHOUT_VERSION = "select SLM.RECORDID AS STARLOCALEMAPPINGID, SLM.REQUIRESLOGON, SLM.UNSUPPORTEDID, SLM.REQUIRESUDBCHECK, SLM.REQUIRESDEFLECTION, SLM.DEFLECTIONCONTENTID, SLM.HASDYNAMIC, SCHM.CHANNELS, SDIM.DTENABLED, SDIM.ICVALUE " +
			"from STARCATEGORYMAPPING SCM " +
			"JOIN STARCATEGORYLOCALIZED SCL ON SCM.CATEGORYID = SCL.CATEGORYID " +
			"JOIN STARLOCALEMAPPING SLM ON SLM.MAPPINGID = SCM.RECORDID AND SLM.LOCALECODE = ? " +
			"LEFT JOIN STARCHANNELMAPPING SCHM ON SCHM.LOCALEMAPPINGID = SLM.RECORDID " +
			"LEFT JOIN STARDTICMAPPING SDIM ON SDIM.SLMMAPPINGID = SLM.RECORDID " +
			"JOIN TAG T1 ON SCM.PRODUCTID = T1.RECORDID " +
			"JOIN TAG T3 ON SCM.TOPICID = T3.RECORDID " +
			"WHERE SCM.ACTIVE = 'Y' and T1.REFERENCEKEY = ? " +
			"and T3.REFERENCEKEY = ? and SCL.CATEGORYID  = ? " +
			"AND SCL.STARLANGUAGE = ? ";

		//Map<Locale, Map<Product_ref_key, Product>>
		private Map<String, Map<String, Product>> localeProductMap = new HashMap<String, Map<String,Product>>();
		//Map<Locale+Product_Ref_Key, Map<Version_ref_key, Version>>
		private Map<String, Map<String, Version>> localeProductVersionMap = new HashMap<String, Map<String,Version>>();
		//Map<Locale+Product_Ref_Key +Version_Ref_Key, Map<Topic_ref_key, Topic>>
		private Map<String, Map<String, Category>> localeProductVersionCategoryMap = new HashMap<String, Map<String, Category>>();
		//Map<Locale+Product_Ref_Key +Version_Ref_Key +Topic_Ref_Key, Map<IssueId, Issue>>
		private Map<String, Map<String, SubCategory>> localeProductVersionCategorySubCategoryMap = new HashMap<String, Map<String, SubCategory>>();
		//Map<Locale+Product_Ref_Key+Version_Ref_Key +Topic_Ref_Key +IssueId, ChannelMapping>
		private Map<String, ChannelMapping> channelMappingMap = new HashMap<String, ChannelMapping>();

		
		//private constructor
		private Cache(){
			long startTime = System.currentTimeMillis();
			s_log.info("Before loading cache : Free Memory: "+Runtime.getRuntime().freeMemory() + " Total Memory: "+Runtime.getRuntime().totalMemory()  + " Max Memory: "+Runtime.getRuntime().maxMemory());
			loadCache();
			s_log.info("After loading cache : Free Memory: "+Runtime.getRuntime().freeMemory() + " Total Memory: "+Runtime.getRuntime().totalMemory()  + " Max Memory: "+Runtime.getRuntime().maxMemory());
			s_log.info("Total cache loading time: "+(System.currentTimeMillis() - startTime)/1000.0 +" seconds.");
		}
		List<String> locales = new ArrayList<String>();
		
		private void loadCache() {
			
			Connection conn = null;
			try {
				
				
				conn = DriverManager.getConnection(System.getProperty("JDBC_URL"), config);
				LOG.debug("conn: " + conn);
				
				s_log.info("Loading Locales: ");
				PreparedStatement statement = null;
				locales.add(System.getProperty("LOCALE")); // only loading the locale for which job is triggered
				
//				PreparedStatement statement = conn.prepareStatement(LOCALE_QUERY);
//				ResultSet localeResultSet = statement.executeQuery();
//				while (localeResultSet.next()) {
//					locales.add(localeResultSet.getString("LOCALECODE"));
//				}
//				DbUtils.close(localeResultSet);
//				DbUtils.close(statement);
//				s_log.info("Locales loaded successfully. Total count: "+locales.size());
				
				
				s_log.info("Loading Products: ");
				statement = conn.prepareStatement(PRODUCT_QUERY);
				for (String locale : locales) {
					Map<String, Product> resultMap = new LinkedHashMap<String, Product>();
					statement.setString(1, locale);
					statement.setString(2, locale);
					ResultSet rs = statement.executeQuery();
					while (rs.next()) {
						String refKey = rs.getString("REFERENCEKEY");
						String prod = rs.getString("PRODUCT");
						String starId = rs.getString("STARID");
						Product product = new Product();
						product.setDescription(prod);
						product.setLocale(locale);
						product.setRefKey(refKey);
						product.setStarId(starId);
						resultMap.put(refKey, product);
					}
					localeProductMap.put(locale, resultMap);
					DbUtils.close(rs);
				}
				DbUtils.close(statement);
				s_log.info("Products loaded successfully.");

				s_log.info("Loading Versions: ");
				statement = conn.prepareStatement(VERSION_QUERY);
				for (String locale : locales) {					
					Map<String, Product> products =  localeProductMap.get(locale);
					for(Map.Entry<String, Product> prodEntry : products.entrySet()){
						Map<String, Version> resultMap = new LinkedHashMap<String, Version>();
						String prodRefKey = prodEntry.getKey();
						statement.setString(1, locale);
						statement.setString(2, locale);
						statement.setString(3, prodRefKey);
						ResultSet rs = statement.executeQuery();
						while (rs.next()) {
							String verRefKey = rs.getString("REFERENCEKEY");
							String ver = rs.getString("VERSION");
							String starId = rs.getString("STARID");
							Version version = new Version();
							version.setDescription(ver);
							version.setRefKey(verRefKey);
							version.setProduct(prodEntry.getValue());
							version.setStarId(starId);
							resultMap.put(verRefKey, version);
						}
						if(resultMap.size() > 0){
							localeProductVersionMap.put(locale+prodRefKey, resultMap);
							prodEntry.getValue().setVersions(true);
						}else{
							prodEntry.getValue().setVersions(false);
						}
						DbUtils.close(rs);
					}
				}
				DbUtils.close(statement);
				s_log.info("Versions loaded successfully.");

				s_log.info("Loading Cateogories with version : ");
				//categories with version
				statement = conn.prepareStatement(CATEGORY_WITH_VERSION);
				for (String locale : locales) {
					Map<String, Product> products =  localeProductMap.get(locale);
					for(Map.Entry<String, Product> prodEntry : products.entrySet()){
						Product prod = prodEntry.getValue();
						if(prod.hasVersions()){
							String prodRefKey = prodEntry.getKey();
							Map<String, Version> versions = localeProductVersionMap.get(locale+prodRefKey);
							for(Map.Entry<String, Version> verEntry : versions.entrySet()){
								Map<String, Category> resultMap = new LinkedHashMap<String, Category>();
								String verRefKey = verEntry.getKey();
								statement.setString(1, locale);
								statement.setString(2, locale);
								statement.setString(3, prodRefKey);
								statement.setString(4, verRefKey);
								ResultSet rs = statement.executeQuery();
								while (rs.next()) {
									String catRefKey = rs.getString("REFERENCEKEY");
									String cat = rs.getString("CATEGORY");
									String starId = rs.getString("STARID");
									Category category = new Category();
									category.setDescription(cat);
									category.setRefKey(catRefKey);		
									category.setStarId(starId);
									resultMap.put(catRefKey, category);
								}
								if(resultMap.size() > 0){
									localeProductVersionCategoryMap.put(locale+prodRefKey+verRefKey, resultMap);
									verEntry.getValue().setCategories(true);
								}else{
									verEntry.getValue().setCategories(false);
								}
								DbUtils.close(rs);
							}
						}					
					}
				}
				DbUtils.close(statement);
				s_log.info("Cateogories with version loaded successfully.");

				s_log.info("Loading Cateogories without version : ");
				//categories without version
				statement = conn.prepareStatement(CATEGORY_WITHOUT_VERSION);
				for (String locale : locales) {
					Map<String, Product> products =  localeProductMap.get(locale);
					for(Map.Entry<String, Product> prodEntry : products.entrySet()){
						Product prod = prodEntry.getValue();
						if(!prod.hasVersions()){
							String prodRefKey = prodEntry.getKey();
							Map<String, Category> resultMap = new LinkedHashMap<String, Category>();
							statement.setString(1, locale);
							statement.setString(2, locale);
							statement.setString(3, prodRefKey);
							ResultSet rs = statement.executeQuery();
							while (rs.next()) {
								String catRefKey = rs.getString("REFERENCEKEY");
								String cat = rs.getString("CATEGORY");
								String starId = rs.getString("STARID");
								Category category = new Category();
								category.setDescription(cat);
								category.setRefKey(catRefKey);			
								category.setStarId(starId);
								resultMap.put(catRefKey, category);
							}
							if(resultMap.size() > 0){
								localeProductVersionCategoryMap.put(locale+prodRefKey, resultMap);
								//verEntry.getValue().setCategories(true);
							}/*else{
								//verEntry.getValue().setCategories(false);
							}*/
							DbUtils.close(rs);
						}					
					}
				}
				DbUtils.close(statement);
				s_log.info("Cateogories without version loaded successfully.");

				s_log.info("Loading Subcateogories with version : ");
				//subcategory with version
				statement = conn.prepareStatement(SUBCATEGORY_WITH_VERSION);
				for (String locale : locales) {
					String language = YCEMUtil.getLanguageFromLocale(locale);
					Map<String, Product> products =  localeProductMap.get(locale);
					for(Map.Entry<String, Product> prodEntry : products.entrySet()){
						Product prod = prodEntry.getValue();
						if(prod.hasVersions()){
							String prodRefKey = prodEntry.getKey();
							Map<String, Version> versions = localeProductVersionMap.get(locale+prodRefKey);
							for(Map.Entry<String, Version> verEntry : versions.entrySet()){
								String verRefKey = verEntry.getKey();
								Map<String,Category> categories = localeProductVersionCategoryMap.get(locale+prodRefKey+verRefKey);
								if( categories != null){
									for(Map.Entry<String,Category> catEntry : categories.entrySet()){
										String catRefKey = catEntry.getKey();
										Map<String, SubCategory> resultMap = new LinkedHashMap<String, SubCategory>();
										statement.setString(1, locale);
										statement.setString(2, prodRefKey);
										statement.setString(3, verRefKey);
										statement.setString(4, catRefKey);
										statement.setString(5, language);
										ResultSet rs = statement.executeQuery();
										while (rs.next()) {
											String subCatId = rs.getString("SUBCATEGORYID");
											String subCat = rs.getString("SUBCATEGORY");
											String starId = rs.getString("STARID");
											String l1StarId = rs.getString("L1STARID");
											SubCategory subCategory = new SubCategory();
											subCategory.setDescription(subCat);
											subCategory.setSubCategoryId(subCatId);
											subCategory.setParentCategory(catEntry.getValue());
											subCategory.setStarId(starId);
											subCategory.setLevel1StarId(l1StarId);
											resultMap.put(subCatId, subCategory);
										}
										if(resultMap.size() > 0){
											localeProductVersionCategorySubCategoryMap.put(locale+prodRefKey+verRefKey+catRefKey, resultMap);
											catEntry.getValue().setSubCategories(true);
										}else{
											catEntry.getValue().setSubCategories(false);
										}
										DbUtils.close(rs);
									}
								}						
							}
						}			
					}
				}
				DbUtils.close(statement);
				s_log.info("Subcateogories with version loaded successfully.");

				s_log.info("Loading Subcateogories without version : ");
				//subcategories without version
				statement = conn.prepareStatement(SUBCATEGORY_WITHOUT_VERSION);
				for (String locale : locales) {
					String language = YCEMUtil.getLanguageFromLocale(locale);
					Map<String, Product> products =  localeProductMap.get(locale);
					for(Map.Entry<String, Product> prodEntry : products.entrySet()){
						Product prod = prodEntry.getValue();
						if(!prod.hasVersions()){
							String prodRefKey = prodEntry.getKey();
							Map<String,Category> categories = localeProductVersionCategoryMap.get(locale+prodRefKey);
							if( categories != null){
								for(Map.Entry<String,Category> catEntry : categories.entrySet()){
									String catRefKey = catEntry.getKey();
									Map<String, SubCategory> resultMap = new LinkedHashMap<String, SubCategory>();
									statement.setString(1, locale);
									statement.setString(2, prodRefKey);
									statement.setString(3, catRefKey);
									statement.setString(4, language);
									ResultSet rs = statement.executeQuery();
									while (rs.next()) {
										String subCatId = rs.getString("SUBCATEGORYID");
										String subCat = rs.getString("SUBCATEGORY");
										String starId = rs.getString("STARID");
										String l1StarId = rs.getString("L1STARID");
										SubCategory subCategory = new SubCategory();
										subCategory.setDescription(subCat);
										subCategory.setSubCategoryId(subCatId);
										subCategory.setParentCategory(catEntry.getValue());
										subCategory.setStarId(starId);
										subCategory.setLevel1StarId(l1StarId);
										resultMap.put(subCatId, subCategory);
									}
									if(resultMap.size() > 0){
										localeProductVersionCategorySubCategoryMap.put(locale+prodRefKey+catRefKey, resultMap);
										catEntry.getValue().setSubCategories(true);
									}else{
										catEntry.getValue().setSubCategories(false);
									}
									DbUtils.close(rs);
								}
							}
						}					
					}
				}
				DbUtils.close(statement);
				s_log.info("Subcateogories without version loaded successfully.");

				s_log.info("Loading ChannelMapping with version : ");
				//channelmapping with version
				statement = conn.prepareStatement(CHANNELMAPPING_WITH_VERSION);
				for (String locale : locales) {
					String language = YCEMUtil.getLanguageFromLocale(locale);//locale.substring(0,2);
					Map<String, Product> products =  localeProductMap.get(locale);
					for(Map.Entry<String, Product> prodEntry : products.entrySet()){
						Product prod = prodEntry.getValue();
						if(prod.hasVersions()){
							String prodRefKey = prodEntry.getKey();
							Map<String, Version> versions = localeProductVersionMap.get(locale+prodRefKey);
							for(Map.Entry<String, Version> verEntry : versions.entrySet()){
								String verRefKey = verEntry.getKey();
								Map<String,Category> categories = localeProductVersionCategoryMap.get(locale+prodRefKey+verRefKey);
								if( categories != null){
									for(Map.Entry<String,Category> catEntry : categories.entrySet()){
										String catRefKey = catEntry.getKey();
										if(catEntry.getValue().hasSubCategories()){
											Map<String,SubCategory> subCategories = localeProductVersionCategorySubCategoryMap.get(locale+prodRefKey+verRefKey+catRefKey);
											for(Map.Entry<String,SubCategory> subCatEntry : subCategories.entrySet()){
												String subCatRefKey = subCatEntry.getKey();
												statement.setString(1, locale);
												statement.setString(2, prodRefKey);
												statement.setString(3, verRefKey);
												statement.setString(4, catRefKey);
												statement.setString(5, subCatRefKey);
												statement.setString(6, language);
												ResultSet rs = statement.executeQuery();
												if(rs.next()) {
													String localeMappingID = rs.getString("STARLOCALEMAPPINGID");
													String requiresLogon = rs.getString("REQUIRESLOGON");
													String unsupportedDocId = rs.getString("UNSUPPORTEDID");
													String requiresUDBCheck = rs.getString("REQUIRESUDBCHECK");
													String requiresDeflection = rs.getString("REQUIRESDEFLECTION");
													String deflectionContentId = rs.getString("DEFLECTIONCONTENTID");
													String hasDynamic = rs.getString("HASDYNAMIC");
													int channels = rs.getInt("CHANNELS");													
													
													ChannelMapping channelMapping = new ChannelMapping();
													channelMapping.setLocalMappingId(localeMappingID);
													channelMapping.setLogonRequired(getBoolean(requiresLogon));
													channelMapping.setUnsupportedDocId(getDocIDFromRecordID(conn,unsupportedDocId));
													channelMapping.setUdbCheckRequired(getBoolean(requiresUDBCheck));
													channelMapping.setDeflectionRequired(getBoolean(requiresDeflection));
													channelMapping.setDeflectionDocId(getDocIDFromRecordID(conn,deflectionContentId));														
													channelMapping.setDeflectionContentID(deflectionContentId);
													if(getBoolean(hasDynamic)){
														channelMapping.setDynamicFields(queryDynamicFieldDocIDs(conn,localeMappingID));
													}
													channelMapping.setRntChat((channels & TYPE_CHAT_RIGHTNOW) != 0);
													channelMapping.setLpChat((channels & TYPE_CHAT_LIVEPERSON) != 0);
													channelMapping.setEmail((channels & TYPE_EMAIL) != 0);
													channelMapping.setVoice((channels & TYPE_VOICE) != 0);
													channelMapping.setCommunity((channels & TYPE_COMMUNITY) != 0);
													
													channelMapping.setChannels(new YChannelMapping(channels));
													channelMappingMap.put(locale+prodRefKey+verRefKey+catRefKey+subCatRefKey, channelMapping);
												}												
												DbUtils.close(rs);
											}											
										}else{
											//TODO FUTURE load mapping for L2 with version and no subcategory.
										} 										
									}
								}						
							}
						}			
					}
				}
				DbUtils.close(statement);
				s_log.info("ChannelMapping with version loaded successfully.");

				s_log.info("Loading ChannelMapping without version: ");
				//channelmapping without version
				statement = conn.prepareStatement(CHANNELMAPPING_WITHOUT_VERSION);
				for (String locale : locales) {
					String language = YCEMUtil.getLanguageFromLocale(locale);
					Map<String, Product> products =  localeProductMap.get(locale);
					for(Map.Entry<String, Product> prodEntry : products.entrySet()){
						Product prod = prodEntry.getValue();
						if(!prod.hasVersions()){
							String prodRefKey = prodEntry.getKey();
							Map<String,Category> categories = localeProductVersionCategoryMap.get(locale+prodRefKey);
								if( categories != null){
									for(Map.Entry<String,Category> catEntry : categories.entrySet()){
										String catRefKey = catEntry.getKey();
										if(catEntry.getValue().hasSubCategories()){
											Map<String,SubCategory> subCategories = localeProductVersionCategorySubCategoryMap.get(locale+prodRefKey+catRefKey);
											for(Map.Entry<String,SubCategory> subCatEntry : subCategories.entrySet()){
												String subCatRefKey = subCatEntry.getKey();
												statement.setString(1, locale);
												statement.setString(2, prodRefKey);
												statement.setString(3, catRefKey);
												statement.setString(4, subCatRefKey);
												statement.setString(5, language);
												ResultSet rs = statement.executeQuery();
												if(rs.next()) {
													String localeMappingID = rs.getString("STARLOCALEMAPPINGID");
													String requiresLogon = rs.getString("REQUIRESLOGON");
													String unsupportedDocId = rs.getString("UNSUPPORTEDID");
													String requiresUDBCheck = rs.getString("REQUIRESUDBCHECK");
													String requiresDeflection = rs.getString("REQUIRESDEFLECTION");
													String deflectionContentId = rs.getString("DEFLECTIONCONTENTID");
													String hasDynamic = rs.getString("HASDYNAMIC");
													int channels = rs.getInt("CHANNELS");												
													
													ChannelMapping channelMapping = new ChannelMapping();
													channelMapping.setLocalMappingId(localeMappingID);
													channelMapping.setLogonRequired(getBoolean(requiresLogon));
													channelMapping.setUnsupportedDocId(getDocIDFromRecordID(conn,unsupportedDocId));
													channelMapping.setUdbCheckRequired(getBoolean(requiresUDBCheck));
													channelMapping.setDeflectionRequired(getBoolean(requiresDeflection));
													channelMapping.setDeflectionDocId(getDocIDFromRecordID(conn,deflectionContentId));														
													channelMapping.setDeflectionContentID(deflectionContentId);
													if(getBoolean(hasDynamic)){
														channelMapping.setDynamicFields(queryDynamicFieldDocIDs(conn,localeMappingID));
													}
													channelMapping.setRntChat((channels & TYPE_CHAT_RIGHTNOW) != 0);
													channelMapping.setLpChat((channels & TYPE_CHAT_LIVEPERSON) != 0);
													channelMapping.setEmail((channels & TYPE_EMAIL) != 0);
													channelMapping.setVoice((channels & TYPE_VOICE) != 0);
													channelMapping.setCommunity((channels & TYPE_COMMUNITY) != 0);
													
													channelMapping.setChannels(new YChannelMapping(channels));
													
													channelMappingMap.put(locale+prodRefKey+catRefKey+subCatRefKey, channelMapping);
												}
												DbUtils.close(rs);
											}											
										}else{
											//TODO FUTURE load mapping for L2 without version and no subcategory.
										} 										
									}					
							}
						}			
					}
				}
				DbUtils.close(statement);
				s_log.info("ChannelMapping without version loaded successfully.");
				
				cleanup();
			} catch (SQLException e) {
				if (s_log.isFatalEnabled()) {
					s_log.fatal("Unable to retrieve connection from IM Database Connection Pool",e);
				}
			} finally {
				try {
					DbUtils.close(conn);
				} catch (SQLException e) {
					if (s_log.isErrorEnabled()) {
						s_log.error("Unable to close connection to database", e);
					}
				}
			}
		}
	
		public static final int TYPE_EMAIL = 1;
		public static final int TYPE_CHAT_RIGHTNOW = 2;
		public static final int TYPE_VOICE = 4;
		public static final int TYPE_CHAT_LIVEPERSON = 8;
		public static final int TYPE_COMMUNITY = 16;
		
	    public static String DYNAMIC_FIELD_QUERY = "SELECT CON.DOCUMENTID FROM STAREFIELDMAPPING SFM " + 
		    "JOIN STARLOCALEMAPPING SLM ON SLM.RECORDID = SFM.STARLOCALEMAPPINGID " + 
		    "JOIN CONTENT CON ON CON.RECORDID = SFM.FIELDID WHERE SFM.STARLOCALEMAPPINGID = ?";
	    public static PreparedStatement getDynamicFieldDocIDsStmt_ = null;
		public static PreparedStatement getDynamicFieldDocIDsStmt(Connection conn) throws SQLException {
	        if (getDynamicFieldDocIDsStmt_ == null) {
	        	getDynamicFieldDocIDsStmt_ = conn.prepareStatement(DYNAMIC_FIELD_QUERY);
	        }
	        return getDynamicFieldDocIDsStmt_;
	    }
		public String[] queryDynamicFieldDocIDs(Connection conn, String localeMapID) {
	        List<String> dynamicIDs = new ArrayList<String>();
	        if (localeMapID != null && localeMapID.length() > 0) {
	        	try{
		        	PreparedStatement queryStmt = getDynamicFieldDocIDsStmt(conn);
			        queryStmt.setString(1, localeMapID);
			        ResultSet rs = queryStmt.executeQuery();
			        while (rs.next()) {
			            dynamicIDs.add(rs.getString(1));
			        }
			        DbUtils.close(rs);
	        	}catch(SQLException e){
	        		e.printStackTrace();
	        		if (s_log.isErrorEnabled()) {
						s_log.error("Error getting dynamic fields for localemappingid: "+localeMapID, e);
					}
	        	}
	        }
	        return dynamicIDs.toArray(new String[dynamicIDs.size()]);
	    }
		
		public static String QUERY_DOCID_FROM_RECORDID = "SELECT DOCUMENTID FROM CONTENT WHERE RECORDID = ?";
		public static PreparedStatement getDocIDFromrecordIDStmt_ = null;
		public static PreparedStatement getDocIDFromRecordIDStmt(Connection conn) throws SQLException {
	        if (getDocIDFromrecordIDStmt_ == null) {
	            getDocIDFromrecordIDStmt_ = conn.prepareStatement(QUERY_DOCID_FROM_RECORDID);
	        }
	        return getDocIDFromrecordIDStmt_;
	    }
		public String getDocIDFromRecordID(Connection conn, String recordID) {
	        if (recordID == null || recordID.length() == 0) {
	            return null;
	        }
	        String docID = null;
	        try {
	            PreparedStatement getDocIDFromrecordIDStmt = getDocIDFromRecordIDStmt(conn);
	            getDocIDFromrecordIDStmt.setString(1, recordID);
	            ResultSet rs = getDocIDFromrecordIDStmt.executeQuery();
	            if (rs.next()) {
	                docID = rs.getString(1);
	            } else {
	            	if (s_log.isErrorEnabled()) {
						s_log.error("Failed to find doc ID with record ID: " + recordID);
					}
	            }
	            DbUtils.close(rs);
	        }catch(SQLException e){
	        	e.printStackTrace();
        		if (s_log.isErrorEnabled()) {
					s_log.error("Failed to find doc ID with record ID: " + recordID, e);
				}
	        }
	        return docID;
	    }		 
		
		public void cleanup() throws SQLException{
			if(getDocIDFromrecordIDStmt_ != null){
				DbUtils.close(getDocIDFromrecordIDStmt_);
				getDocIDFromrecordIDStmt_ = null;
			}
			if(getDynamicFieldDocIDsStmt_ != null){
				DbUtils.close(getDynamicFieldDocIDsStmt_);
				getDynamicFieldDocIDsStmt_ = null;
			}
			
		}
		
		public static boolean getBoolean(String str){
			return str != null && "Y".equalsIgnoreCase(str.trim());
		}
	}
}