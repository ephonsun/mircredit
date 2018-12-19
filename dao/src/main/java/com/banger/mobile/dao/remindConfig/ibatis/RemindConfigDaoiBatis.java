/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :消息提醒配置dao实现类 
 * Author     :yujh
 * Create Date:Jun 1, 2012
 */
package com.banger.mobile.dao.remindConfig.ibatis;

import com.banger.mobile.dao.remindConfig.RemindConfigDao;
import com.banger.mobile.domain.model.remindConfig.RemindConfig;
import com.banger.mobile.ibatis.GenericDaoiBatis;

/**
 * @author yujh
 * @version $Id: RemindConfigDaoiBatis.java,v 0.1 Jun 1, 2012 1:55:11 PM Administrator Exp $
 */
public class RemindConfigDaoiBatis extends GenericDaoiBatis implements RemindConfigDao{

    public RemindConfigDaoiBatis() {
        super(RemindConfig.class);
    }

    /**
     * 查询
     * @return
     * @see com.banger.mobile.dao.remindConfig.RemindConfigDao#queryRemindConfig()
     */
    public RemindConfig queryRemindConfig(int userId) {
        return (RemindConfig)this.getSqlMapClientTemplate().queryForObject("queryRemindConfig", userId);
    }

    /**
     * 更新
     * @param remindConfig
     * @see com.banger.mobile.dao.remindConfig.RemindConfigDao#updateRemindConfig(com.banger.mobile.domain.model.remindConfig.RemindConfig)
     */
    public void updateRemindConfig(RemindConfig remindConfig) {
        this.getSqlMapClientTemplate().update("updateRemindConfig", remindConfig);
    }

    /**
     * 新增一条配置
     * @param remindConfig
     * @see com.banger.mobile.dao.remindConfig.RemindConfigDao#addRemindConfig(com.banger.mobile.domain.model.remindConfig.RemindConfig)
     */
    public void addRemindConfig(RemindConfig remindConfig) {
        this.getSqlMapClientTemplate().insert("addRemindConfig", remindConfig);
    }

    /**
     * 删除用户消息提醒配置
     * @param userId
     */
    public void deleteRemingConfig(int userId) {
        this.getSqlMapClientTemplate().delete("deleteRemingConfig", userId);
    }

}
