/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :客户资料同步设置Dao接口
 * Author     :liyb
 * Create Date:2012-6-1
 */
package com.banger.mobile.dao.syncSetting;

import java.util.List;

import com.banger.mobile.domain.model.syncSetting.SyncSetting;

/**
 * @author liyb
 * @version $Id: SyncSettingDao.java,v 0.1 2012-6-1 下午01:19:12 liyb Exp $
 */
public interface SyncSettingDao {
    
    /**
     * 添加客户资料同步数据
     * @param sync
     * @return
     */
    public Integer insertSyncSetting(SyncSetting sync);
    
    /**
     * 编辑客户资料同步数据
     * @param sync
     * @return
     */
    public void updateSyncSetting(SyncSetting sync);
    
    /**
     * 客户资料同步数据列表
     * @return
     */
    public List<SyncSetting> getSyncSettingList();
    
    /**
     * 获取客户资料同步设置数据
     * @param sync
     * @return
     */
    public SyncSetting getSyncSettingById();
}
