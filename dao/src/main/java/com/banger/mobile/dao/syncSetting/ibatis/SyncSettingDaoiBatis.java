/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :客户资料同步设置Dao接口实现类
 * Author     :liyb
 * Create Date:2012-6-1
 */
package com.banger.mobile.dao.syncSetting.ibatis;

import java.util.Date;
import java.util.List;

import com.banger.mobile.dao.syncSetting.SyncSettingDao;
import com.banger.mobile.domain.model.syncSetting.SyncSetting;
import com.banger.mobile.ibatis.GenericDaoiBatis;

/**
 * @author liyb
 * @version $Id: SyncSettingDaoiBatis.java,v 0.1 2012-6-1 下午01:27:56 liyb Exp $
 */
public class SyncSettingDaoiBatis extends GenericDaoiBatis implements SyncSettingDao {

    public SyncSettingDaoiBatis(){
        super(SyncSetting.class);
    }
    
    /**
     * 添加客户资料同步数据
     * @param sync
     * @return
     */
    public Integer insertSyncSetting(SyncSetting sync) {
        return (Integer) this.getSqlMapClientTemplate().insert("InsertSyncSetting",sync);
    }

    /**
     * 编辑客户资料同步数据
     * @param sync
     * @return
     */
    public void updateSyncSetting(SyncSetting sync) {
        this.getSqlMapClientTemplate().update("updateSyncSetting",sync);
    }

    /**
     * 客户资料同步数据列表
     * @return
     */
    public List<SyncSetting> getSyncSettingList() {
        return this.getSqlMapClientTemplate().queryForList("GetSyncSettingList");
    }

    /**
     * 获取客户资料同步设置数据
     * @return
     */
    public SyncSetting getSyncSettingById() {
        return (SyncSetting) this.getSqlMapClientTemplate().queryForObject("GetSyncSettingById");
    }

}
