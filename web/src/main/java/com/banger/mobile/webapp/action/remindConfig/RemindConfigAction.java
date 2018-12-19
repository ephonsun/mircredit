/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :消息提醒配置...
 * Author     :Administrator
 * Create Date:Jun 1, 2012
 */
package com.banger.mobile.webapp.action.remindConfig;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.banger.mobile.domain.model.remindConfig.RemindConfig;
import com.banger.mobile.domain.model.user.IUserInfo;
import com.banger.mobile.facade.remindConfig.RemindConfigService;
import com.banger.mobile.webapp.action.BaseAction;

/**
 * @author Administrator
 * @version $Id: RemindConfigAction.java,v 0.1 Jun 1, 2012 4:54:57 PM Administrator Exp $
 */
public class RemindConfigAction extends BaseAction {

    private static final long serialVersionUID = -7529102586261920124L;
    
    private RemindConfigService     remindConfigService;
    private RemindConfig            remindConfig;
    public RemindConfig getRemindConfig() {
        return remindConfig;
    }
    public void setRemindConfig(RemindConfig remindConfig) {
        this.remindConfig = remindConfig;
    }
    public void setRemindConfigService(RemindConfigService remindConfigService) {
        this.remindConfigService = remindConfigService;
    }
    /**
     * 查询
     * @return
     */
    public String showRemindConfigPage(){
        try {
            int userId=this.getLoginInfo().getUserId();
            remindConfig =this.remindConfigService.queryRemindConfig(userId);
            return SUCCESS;
        } catch (RuntimeException e) {
            log.error("showRemindConfigPage error",e);
            return ERROR;
        }
    }
    /**
     * 更新
     * @return
     */
    public String updateRemindConfig(){
        try {
            int userId=this.getLoginInfo().getUserId();
            remindConfig.setUserId(userId);
            this.remindConfigService.updateRemindConfig(remindConfig);
            return SUCCESS;
        } catch (RuntimeException e) {
            log.error("updateRemindConfig error",e);
            return ERROR;
        }
    }

}
