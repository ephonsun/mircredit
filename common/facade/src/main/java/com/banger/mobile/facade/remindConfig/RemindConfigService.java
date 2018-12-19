/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :消息提醒service
 * Author     :yujh
 * Create Date:Jun 1, 2012
 */
package com.banger.mobile.facade.remindConfig;

import com.banger.mobile.domain.model.remindConfig.RemindConfig;

/**
 * @author yujh
 * @version $Id: RemindConfigService.java,v 0.1 Jun 1, 2012 4:29:01 PM Administrator Exp $
 */
public interface RemindConfigService {
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
     * 新增一条配置
     * @param useId 用户id
     */
    public void addRemindConfig(int userId);
    
    /**
     * 删除消息提醒配置
     * @param userId
     */
    public void deleteRemindConfig(int userId);
}
