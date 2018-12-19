/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :客户资料同步设置service接口实现类
 * Author     :liyb
 * Create Date:2012-6-1
 */
package com.banger.mobile.facade.impl.system.syncSetting;

import java.util.Date;

import com.banger.mobile.dao.syncSetting.SyncSettingDao;
import com.banger.mobile.domain.model.syncSetting.SyncSetting;
import com.banger.mobile.facade.syncSetting.SyncSettingService;

/**
 * @author liyb
 * @version $Id: SyncSettingServiceImpl.java,v 0.1 2012-6-1 下午02:02:02 liyb Exp $
 */
public class SyncSettingServiceImpl implements SyncSettingService {
    
    private SyncSettingDao syncSettingDao;

    public SyncSettingDao getSyncSettingDao() {
        return syncSettingDao;
    }

    public void setSyncSettingDao(SyncSettingDao syncSettingDao) {
        this.syncSettingDao = syncSettingDao;
    }

    /**
     * 获取客户资料同步设置数据
     * @param sync
     * @return
     */
    public SyncSetting getSyncSettingById() {
        return syncSettingDao.getSyncSettingById();
    }


    /**
     * 编辑客户资料同步数据
     * @param sync
     * @return
     */
    public void updateSyncSetting(SyncSetting sync) {
        syncSettingDao.updateSyncSetting(sync);
    }

}
