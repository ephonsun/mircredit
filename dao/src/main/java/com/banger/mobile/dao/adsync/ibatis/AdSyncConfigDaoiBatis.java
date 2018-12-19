package com.banger.mobile.dao.adsync.ibatis;

import com.banger.mobile.dao.adsync.AdSyncConfigDao;
import com.banger.mobile.domain.model.adsync.SyncAdPcUsersSetting;
import com.banger.mobile.ibatis.GenericDaoiBatis;

@SuppressWarnings("rawtypes")
public class AdSyncConfigDaoiBatis extends GenericDaoiBatis implements AdSyncConfigDao {
	
	@SuppressWarnings("unchecked")
	public AdSyncConfigDaoiBatis(Class persistentClass) {
		super(persistentClass);
	}
	
	@SuppressWarnings("unchecked")
	public AdSyncConfigDaoiBatis(){
        super(SyncAdPcUsersSetting.class);
    }

	/**
	 * 新增AD域配置
	 */
	public void insertAdSyncConfig(SyncAdPcUsersSetting syncAdPcUsersSetting){
		this.getSqlMapClientTemplate().insert("insertAdSyncConfig", syncAdPcUsersSetting);
	}
	
	/**
	 * 更新AD域配置
	 * @param syncAdPcUsersSetting
	 */
	public void updateAdSyncConfig(SyncAdPcUsersSetting syncAdPcUsersSetting){
		this.getSqlMapClientTemplate().update("updateAdSyncConfig", syncAdPcUsersSetting);
	}
	
	/**
	 * 获取AD配置
	 */
	public SyncAdPcUsersSetting getAdSyncConfig(){
		return (SyncAdPcUsersSetting)this.getSqlMapClientTemplate().queryForObject("getAdSyncConfig");
	}
}
