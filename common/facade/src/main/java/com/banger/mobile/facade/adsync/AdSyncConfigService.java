package com.banger.mobile.facade.adsync;

import com.banger.mobile.domain.model.adsync.SyncAdPcUsersSetting;


public interface AdSyncConfigService {
	/**
	 * 保存AD域配置
	 * @param syncAdPcUsersSetting
	 */
	public void save(SyncAdPcUsersSetting syncAdPcUsersSetting);
	
	/**
	 * 获取AD配置
	 */
	public SyncAdPcUsersSetting getAdSyncConfig();
}
