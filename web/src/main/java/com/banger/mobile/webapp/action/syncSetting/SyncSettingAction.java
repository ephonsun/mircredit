/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :客户资料同步设置Action
 * Author     :liyb
 * Create Date:2012-6-1
 */
package com.banger.mobile.webapp.action.syncSetting;

import com.banger.mobile.domain.model.syncSetting.SyncSetting;
import com.banger.mobile.facade.syncSetting.SyncSettingService;
import com.banger.mobile.webapp.action.BaseAction;

/**
 * @author liyb
 * @version $Id: SyncSettingAction.java,v 0.1 2012-6-1 下午02:06:31 liyb Exp $
 */
public class SyncSettingAction extends BaseAction {

    private static final long serialVersionUID = -6738813748067831390L;
    private SyncSettingService syncSettingService;//客户资料同步设置service
    private SyncSetting sync;//客户资料同步Bean
    
    /**
     * 初始化客户资料同步数据
     * @return
     */
    public String initSyncSetting(){
        try {
            sync=syncSettingService.getSyncSettingById();
            return SUCCESS;
        } catch (Exception e) {
            /***** add by yangy 2012-12-10*********/
            log.error("initSyncSetting action error:" + e.getMessage());
            return ERROR;
        }
    }
    /**
     * 修改同步设置
     * @return
     */
    public String updateSyncSetting(){
        try {
            this.syncSettingService.updateSyncSetting(sync);
            return SUCCESS;
        } catch (RuntimeException e) {
            /***** add by yangy 2012-12-10*********/
            log.error("updateSyncSetting action error:" + e.getMessage());
            return ERROR;
        }
    }
    
    public SyncSettingService getSyncSettingService() {
        return syncSettingService;
    }
    public void setSyncSettingService(SyncSettingService syncSettingService) {
        this.syncSettingService = syncSettingService;
    }
    public SyncSetting getSync() {
        return sync;
    }
    public void setSync(SyncSetting sync) {
        this.sync = sync;
    }
}
