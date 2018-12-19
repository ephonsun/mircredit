/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :消息提醒配置dao 
 * Author     :yujh
 * Create Date:Jun 1, 2012
 */
package com.banger.mobile.dao.remindConfig;

import com.banger.mobile.domain.model.remindConfig.RemindConfig;

/**
 * @author yujh
 * @version $Id: RemindConfigDao.java,v 0.1 Jun 1, 2012 1:20:34 PM Administrator Exp $
 */
public interface RemindConfigDao {
    /**
     * 查询
     * @return
     */
    public RemindConfig queryRemindConfig(int userId);
    /**
     * 更新
     * @param remindConfig
     */
    public void updateRemindConfig(RemindConfig remindConfig);
    /**
     * 新增个人消息提醒配置
     */
    public void addRemindConfig(RemindConfig remindConfig);
    /**
     * 删除用户消息提醒配置
     * @param userId
     */
    public void deleteRemingConfig(int userId);
}
