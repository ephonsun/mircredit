package com.banger.mobile.facade.adsync;

import java.util.List;

import com.banger.mobile.domain.model.adsync.SyncAdNode;
import com.banger.mobile.domain.model.adsync.SyncAdPcUsersSetting;

public interface AdUserSyncService {
	
	/**
	 * 定时任务是否可用
	 * @return
	 */
	public boolean getTaskEnable();
	
	/**
	 * 得到AD机构信息
	 * @param setting
	 * @return
	 */
	public List<SyncAdNode> getOUList(SyncAdPcUsersSetting setting);
	
	/**
	 * 同步所有AD域数据
	 * @return
	 */
	public String syncAdAllNode(SyncAdPcUsersSetting setting);
	
	/**
	 * 添加同步任务
	 * @param setting
	 */
	public void AddSyncJob(SyncAdPcUsersSetting setting);
	
	/**
	 * 删除同步任务
	 * @param setting
	 */
	public void removeSyncJob(SyncAdPcUsersSetting setting);
	
	
}
