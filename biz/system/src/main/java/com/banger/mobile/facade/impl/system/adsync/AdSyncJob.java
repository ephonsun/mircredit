package com.banger.mobile.facade.impl.system.adsync;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.banger.mobile.domain.model.adsync.SyncAdPcUsersSetting;
import com.banger.mobile.facade.adsync.AdSyncConfigService;
import com.banger.mobile.facade.adsync.AdUserSyncService;
import com.banger.mobile.util.SpringContextUtil;

public class AdSyncJob implements Job {
	private AdUserSyncService syncService;
	private AdSyncConfigService settingService;
	
	public AdUserSyncService getSyncService(){
		if(this.syncService==null)this.syncService=(AdUserSyncService)SpringContextUtil.getBean("adUserSyncService");
		return this.syncService;
	}
	
	public AdSyncConfigService getSetting(){
		if(this.settingService==null)this.settingService=(AdSyncConfigService)SpringContextUtil.getBean("adSyncConfigService");
		return this.settingService;
	}
	
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		if(this.getSyncService().getTaskEnable()){
			SyncAdPcUsersSetting setting = this.getSetting().getAdSyncConfig();
			if(setting!=null){
				if(setting.getAdActived()!=null && setting.getAdActived().intValue()>0){
					if("time".equalsIgnoreCase(setting.getAdSyncMode())){
						syncService.syncAdAllNode(setting);
					}
				}
			}
		}
	}
}
