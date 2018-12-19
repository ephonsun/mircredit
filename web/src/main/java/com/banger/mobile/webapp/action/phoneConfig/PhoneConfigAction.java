/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :通话配置action
 * Author     :yujh
 * Create Date:Jun 1, 2012
 */
package com.banger.mobile.webapp.action.phoneConfig;

import com.banger.mobile.domain.model.phoneConfig.PhoneConfig;
import com.banger.mobile.facade.phoneConfig.PhoneConfigService;
import com.banger.mobile.webapp.action.BaseAction;

/**
 * @author yujh
 * @version $Id: PhoneConfigAction.java,v 0.1 Jun 1, 2012 11:12:15 AM Administrator Exp $
 */
public class PhoneConfigAction extends BaseAction{

    private static final long serialVersionUID = -335236291513735407L;
    private PhoneConfigService  phoneConfigService;
    private PhoneConfig  phoneConfig;
    
    public PhoneConfig getPhoneConfig() {
        return phoneConfig;
    }
    public void setPhoneConfig(PhoneConfig phoneConfig) {
        this.phoneConfig = phoneConfig;
    }
    public void setPhoneConfigService(PhoneConfigService phoneConfigService) {
        this.phoneConfigService = phoneConfigService;
    }
    /**
     * 查询
     * @return
     */
    public String queryPhoneConfig(){
        try {
            int userId = this.getLoginInfo().getUserId();
            phoneConfig =this.phoneConfigService.query(userId);
            return SUCCESS;
        } catch (RuntimeException e) {
            log.error("queryPhoneConfig ERROR",e);
            return ERROR;
        }
    }
    /**
     * 更新
     */
    public String updatePhoneConfig(){
        try {
            int userId = this.getLoginInfo().getUserId();
            phoneConfig.setUserId(userId);
            this.phoneConfigService.update(phoneConfig);
            return SUCCESS;
        } catch (RuntimeException e) {
            log.error("updatePhoneConfig ERROR",e);
            return ERROR;
        }
    }
    /**
     * 是否弹出通话窗口
     */
    public void updateIsPopUp(){
        phoneConfig.setUserId(this.getLoginInfo().getUserId());
        this.phoneConfigService.updatePhoneConfigIsPopUp(phoneConfig);
    }
    /**
     * 是否屏幕取词
     */
    public void updateIsScreamWord(){
        phoneConfig.setUserId(this.getLoginInfo().getUserId());
        this.phoneConfigService.updatePhoneConfigIsScreamWord(phoneConfig);
    }
}
