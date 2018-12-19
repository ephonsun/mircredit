/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :录音设置action
 * Author     :yujh
 * Create Date:Sep 3, 2012
 */
package com.banger.mobile.webapp.action.crmRecordSetting;

import com.banger.mobile.domain.model.crmRecordSetting.CrmRecordSetting;
import com.banger.mobile.facade.crmRecordSetting.CrmRecordSettingService;
import com.banger.mobile.facade.funcAuth.FuncAuthService;
import com.banger.mobile.webapp.action.BaseAction;

/**
 * @author yujh
 * @version $Id: CrmRecordSettingAction.java,v 0.1 Sep 3, 2012 5:09:29 PM Administrator Exp $
 */
public class CrmRecordSettingAction  extends BaseAction{

    private static final long serialVersionUID = -2115695294568384252L;
    
    private CrmRecordSetting            crmRecordSetting;
    private CrmRecordSettingService     crmRecordSettingService;
    private FuncAuthService             funcAuthService;        //功能权限service
    
    /**
     * 查询
     * @return
     */
    public String showCrmRecordSettingPage(){
        try {
            int userId=this.getLoginInfo().getUserId();
            boolean isAutoRecord=this.funcAuthService.isAutoRecord();//是否强制自动录音
            boolean isPlayRecord=this.funcAuthService.isPlayRecord();//是否自动播放录音
            if(isAutoRecord){
                request.setAttribute("isAutoRecordByAdmin", 1);
            }else{
                request.setAttribute("isAutoRecordByAdmin", 0);
            }
            if(isPlayRecord){
                request.setAttribute("isPlayRecordByAdmin", 1);
            }else{
                request.setAttribute("isPlayRecordByAdmin", 0);
            }
            crmRecordSetting =this.crmRecordSettingService.getCrmRecordSettingById(userId);
            return SUCCESS;
        } catch (RuntimeException e) {
            log.error("showCrmRecordSettingPage action error",e);
            return ERROR;
        }
    }
   
    /**
     * 更新
     * @return
     */
    public String updateCrmRecordSetting(){
        try {
            int userId=this.getLoginInfo().getUserId();
            crmRecordSetting.setUserId(userId);
            this.crmRecordSettingService.updateCrmRecordSetting(crmRecordSetting);
            return SUCCESS;
        } catch (RuntimeException e) {
            log.error("updateCrmRecordSetting action ERROR",e);
            return ERROR;
        }
    }
    
    
    
    public CrmRecordSetting getCrmRecordSetting() {
        return crmRecordSetting;
    }
    public void setCrmRecordSetting(CrmRecordSetting crmRecordSetting) {
        this.crmRecordSetting = crmRecordSetting;
    }
    public CrmRecordSettingService getCrmRecordSettingService() {
        return crmRecordSettingService;
    }
    public void setCrmRecordSettingService(CrmRecordSettingService crmRecordSettingService) {
        this.crmRecordSettingService = crmRecordSettingService;
    }
    public void setFuncAuthService(FuncAuthService funcAuthService) {
        this.funcAuthService = funcAuthService;
    }
    
    
    
}
