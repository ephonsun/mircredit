/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :个人配置所有信息
 * Author     :yujh
 * Create Date:Sep 19, 2012
 */
package com.banger.mobile.facade.impl.system.personalConfig;

import java.util.HashMap;
import java.util.Map;

import com.banger.mobile.dao.answerConfig.AnswerConfigDao;
import com.banger.mobile.dao.crmRecordRemind.CrmRecordRemindDao;
import com.banger.mobile.dao.crmRecordSetting.CrmRecordSettingDao;
import com.banger.mobile.dao.crmRingSetting.CrmRingSettingDao;
import com.banger.mobile.domain.model.answerConfig.AnswerConfig;
import com.banger.mobile.domain.model.crmRecordRemind.CrmRecordRemind;
import com.banger.mobile.domain.model.crmRecordSetting.CrmRecordSetting;
import com.banger.mobile.domain.model.crmRingSetting.CrmRingSetting;
import com.banger.mobile.domain.model.phoneConfig.PhoneConfig;
import com.banger.mobile.domain.model.phoneConfig.PhoneSetting;
import com.banger.mobile.facade.funcAuth.FuncAuthService;
import com.banger.mobile.facade.personalConfig.PersonalConfigService;
import com.banger.mobile.facade.phoneConfig.PhoneConfigService;

/**
 * @author yujh
 * @version $Id: PersonalConfigServiceImpl.java,v 0.1 Sep 19, 2012 4:12:27 PM Administrator Exp $
 */
public class PersonalConfigServiceImpl implements PersonalConfigService{
    private AnswerConfigDao         answerConfigDao;        //答录配置
    private CrmRingSettingDao       crmRingSettingDao;      //来电铃声
    private CrmRecordRemindDao      crmRecordRemindDao;     //录音提示音
    private CrmRecordSettingDao     crmRecordSettingDao;    //录音设置
    private FuncAuthService             funcAuthService;        //功能权限service
    private PhoneConfigService  phoneConfigService;
    
    /**
     * 获得个人配置信息
     */
    public PhoneSetting getPersonConfig(int userId) {
        Map<String ,Object> acMap= new HashMap<String ,Object>();
        acMap.put("userId", userId);
        acMap.put("configType",1);
        AnswerConfig        answerConfig  =this.answerConfigDao.queryAnswerConfig(acMap);                       //答录配置
        CrmRingSetting      crmRingSetting      =this.crmRingSettingDao.getCrmRingSettingByUserId(userId);      //来电铃声
        CrmRecordRemind     crmRecordRemind     =this.crmRecordRemindDao.getCrmRecordRemindByUserId(userId);    //录音提示音
        CrmRecordSetting    crmRecordSetting    =this.crmRecordSettingDao.getCrmRecordSettingById(userId);      //录音设置
        PhoneConfig			phoneConfig			=this.phoneConfigService.query(userId);							//读取话机配置
        boolean isAutoRecord=this.funcAuthService.isAutoRecord();//是否强制自动录音
        boolean isPlayRecord=this.funcAuthService.isPlayRecord();//是否自动播放录音
        if(isAutoRecord){//是否强制自动录音
            crmRecordSetting.setIsAutoRecord(1);
        }
        if(isPlayRecord){//是否自动播放录音
            crmRecordSetting.setIsAutoPlay(1);
        }
        
        PhoneSetting config = new PhoneSetting();
        config.setAnswer(answerConfig);
        config.setConfig(phoneConfig);
        config.setRing(crmRingSetting);
        config.setRemind(crmRecordRemind);
        config.setRecord(crmRecordSetting);
        
        return config;
    }

    public void setAnswerConfigDao(AnswerConfigDao answerConfigDao) {
        this.answerConfigDao = answerConfigDao;
    }

    public void setCrmRingSettingDao(CrmRingSettingDao crmRingSettingDao) {
        this.crmRingSettingDao = crmRingSettingDao;
    }

    public void setCrmRecordRemindDao(CrmRecordRemindDao crmRecordRemindDao) {
        this.crmRecordRemindDao = crmRecordRemindDao;
    }

    public void setCrmRecordSettingDao(CrmRecordSettingDao crmRecordSettingDao) {
        this.crmRecordSettingDao = crmRecordSettingDao;
    }

    public void setFuncAuthService(FuncAuthService funcAuthService) {
        this.funcAuthService = funcAuthService;
    }
    
    public void setPhoneConfigService(PhoneConfigService phoneConfigService)
    {
    	this.phoneConfigService=phoneConfigService;
    }
    
    

}
