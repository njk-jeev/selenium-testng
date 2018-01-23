package com.yahoo.ycem.tests.common;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeSuite;

import com.yahoo.ycem.tests.util.ActionUtil;
import com.yahoo.ycem.tests.util.CacheManager;

public class BaseContactTest extends BaseTest {

	private static final Logger LOGGER = LogManager.getLogger(BaseContactTest.class);
	
	@BeforeSuite (groups = { TestGroups.BVT, TestGroups.DATA_TEST, TestGroups.DEV,
			TestGroups.FEATURE, TestGroups.LARGE_TEST, TestGroups.MONITOR,
			TestGroups.REGRESSION })
	public void setupData() {

		LOGGER.info("inside setupData to get data from DB.");
		ActionUtil.logStep("inside setupData: ");
		CacheManager.getProductsFromLocale(System.getProperty("LOCALE"));
		ActionUtil.logStep("loaded the cache. ");
	}
}

