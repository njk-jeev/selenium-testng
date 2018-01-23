package com.yahoo.ycem.tests.beans.contact;

import java.io.Serializable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class YChannelMapping implements YChannelMappingTO, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8526791547478183283L;

	private static final Log s_log = LogFactory.getLog(YChannelMapping.class);
	
	private int m_channels = 0;

	public YChannelMapping(int channels) {
		m_channels = channels;
	}
	
	public boolean isEnabled(int channelType) {
		return (m_channels & channelType) == channelType;
	}
	
	public void updateWithDTChannels(int dtChannels) {
		s_log.debug("updateWithDTChannels, dtChannels = "+dtChannels);
		s_log.debug("updateWithDTChannels, channels before upgrade = "+m_channels);
		m_channels = (m_channels & dtChannels);
		s_log.debug("updateWithDTChannels, channels after upgrade = "+m_channels);
	}
	
	public int getChannels() {
		return m_channels;
	}
	
}
