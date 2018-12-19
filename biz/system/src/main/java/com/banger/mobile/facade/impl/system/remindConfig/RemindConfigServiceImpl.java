/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :消息提醒service实现类
 * Author     :yujh
 * Create Date:Jun 1, 2012
 */
package com.banger.mobile.facade.impl.system.remindConfig;

import com.banger.mobile.dao.remindConfig.RemindConfigDao;
import com.banger.mobile.domain.model.remindConfig.RemindConfig;
import com.banger.mobile.facade.remindConfig.RemindConfigService;

/**
 * @author yujh
 * @version $Id: RemindConfigServiceImpl.java,v 0.1 Jun 1, 2012 4:32:08 PM Administrator Exp $
 */
public class RemindConfigServiceImpl implements RemindConfigService {
    private RemindConfigDao remindConfigDao;

    public void setRemindConfigDao(RemindConfigDao remindConfigDao) {
        this.remindConfigDao = remindConfigDao;
    }

    /**
     * 查询
     * @return
     * @see com.banger.mobile.facade.remindConfig.RemindConfigService#queryRemindConfig()
     */
    public RemindConfig queryRemindConfig(int userId) {
        return this.remindConfigDao.queryRemindConfig(userId);
    }

    /**
     * 更新
     * @param remindConfig
     * @see com.banger.mobile.facade.remindConfig.RemindConfigService#updateRemindConfig(com.banger.mobile.domain.model.remindConfig.RemindConfig)
     */
    public void updateRemindConfig(RemindConfig remindConfig) {
        this.remindConfigDao.updateRemindConfig(remindConfig);
    }

    /**
     * 新增一条配置
     * @param useId
     * @see com.banger.mobile.facade.remindConfig.RemindConfigService#addRemindConfig(int)
     */
    public void addRemindConfig(int userId) {
        RemindConfig remindConfig = new RemindConfig();
        remindConfig.setIsCallRemind(1);
        remindConfig.setIsMessageRemind(1);
        remindConfig.setIsAssignTask(1);
        remindConfig.setIsOverDueTask(1);
        remindConfig.setIsSendMms(1);
        remindConfig.setIsSendSms(1);
        remindConfig.setIsSmsRemind(1);
        remindConfig.setUserId(userId);
        remindConfig.setIsVisitRemind(1);
        remindConfig.setIsVerifyMms(1);
        remindConfig.setIsVerifySms(1);
        remindConfig.setIsSendBackMms(1);
        remindConfig.setIsSendBackSms(1);
        this.remindConfigDao.addRemindConfig(remindConfig);
    }

    public void deleteRemindConfig(int userId) {
        this.remindConfigDao.deleteRemingConfig(userId);
    }

}
