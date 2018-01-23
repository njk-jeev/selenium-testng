package com.yahoo.ycem.tests.beans.contact;

public interface YChannelMappingTO {
	public static final int TYPE_EMAIL = 1;
	public static final int TYPE_CHAT_RIGHTNOW = 2;
	public static final int TYPE_VOICE = 4;
	public static final int TYPE_CHAT_LIVEPERSON = 8;
	public static final int TYPE_COMMUNITIES = 16;
	
	public boolean isEnabled(int channelType);
	public void updateWithDTChannels(int dtChannels);
	public int getChannels();
}
