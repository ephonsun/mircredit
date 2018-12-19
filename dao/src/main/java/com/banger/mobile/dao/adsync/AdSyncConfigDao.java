package com.banger.mobile.dao.adsync;

import com.banger.mobile.domain.model.adsync.SyncAdPcUsersSetting;

public interface AdSyncConfigDao {
	
	/**
	 * 新增AD域配置
	 * @param syncAdPcUsersSetting
	 */
	public void insertAdSyncConfig(SyncAdPcUsersSetting syncAdPcUsersSetting);
	
	/**
	 * 更新AD域配置
	 * @param syncAdPcUsersSetting
	 */
	public void updateAdSyncConfig(SyncAdPcUsersSetting syncAdPcUsersSetting);
	
	/**
	 * 获取AD配置
	 */
	public SyncAdPcUsersSetting getAdSyncConfig();
}
