/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :通话配置...
 * Author     :Administrator
 * Create Date:Jun 1, 2012
 */
package com.banger.mobile.facade.impl.system.phoneConfig;

import com.banger.mobile.dao.phoneConfig.PhoneConfigDao;
import com.banger.mobile.domain.model.phoneConfig.PhoneConfig;
import com.banger.mobile.facade.phoneConfig.PhoneConfigService;

/**
 * @author Administrator
 * @version $Id: PhoneConfigServiceImpl.java,v 0.1 Jun 1, 2012 11:02:06 AM Administrator Exp $
 */
public class PhoneConfigServiceImpl implements PhoneConfigService {
    private PhoneConfigDao phoneConfigDao;
    public void setPhoneConfigDao(PhoneConfigDao phoneConfigDao) {
        this.phoneConfigDao = phoneConfigDao;
    }

    /**
     * 查询参数
     * @return
     * @see com.banger.mobile.facade.phoneConfig.PhoneConfigService#query()
     */
    public PhoneConfig query(int userId) {
        return this.phoneConfigDao.query(userId);
    }

    /**
     * 更新参数
     * @param phoneConfig
     * @see com.banger.mobile.facade.phoneConfig.PhoneConfigService#update(com.banger.mobile.domain.model.phoneConfig.PhoneConfig)
     */
    public void update(PhoneConfig phoneConfig) {
        this.phoneConfigDao.update(phoneConfig);
    }

    /**
     * 新增个人通话基础配置
     * @param userId
     * @see com.banger.mobile.facade.phoneConfig.PhoneConfigService#addPhoneConfig(int)
     */
    public void addPhoneConfig(int userId) {
        PhoneConfig phoneConfig = new PhoneConfig();
        phoneConfig.setCallOverPopUp(2);
        phoneConfig.setCityCode("");
        phoneConfig.setFlashBreakTime(95);
        phoneConfig.setInsiseExtLength("0");
        phoneConfig.setIpNumber("");
        phoneConfig.setIsIpCall(0);
        phoneConfig.setIsScreamWord(0);
        phoneConfig.setIsPopUp(1);
        phoneConfig.setIsShowLastWindow(1);
        phoneConfig.setOutDelay(1.0);
        phoneConfig.setOutNumber("");
        phoneConfig.setOutsideCallCode("");
        phoneConfig.setUserId(userId);
        this.phoneConfigDao.addPhoneConfig(phoneConfig);
    }

    /**
     * 返回区号
     * @param userId
     * @return
     * @see com.banger.mobile.facade.phoneConfig.PhoneConfigService#getCityCodeByUserId(int)
     */
    public String getCityCodeByUserId(int userId) {
        PhoneConfig pc= this.phoneConfigDao.query(userId);
        if(pc!=null){
            return pc.getCityCode();
        }else{
            return null;
        }
    }

    /**
     * 是否弹出通话窗口
     */
    public void updatePhoneConfigIsPopUp(PhoneConfig phoneConfig) {
        this.phoneConfigDao.updatePhoneConfigIsPopUp(phoneConfig);
    }

    /**
     * 是否屏幕取词
     */
    public void updatePhoneConfigIsScreamWord(PhoneConfig phoneConfig) {
        this.phoneConfigDao.updatePhoneConfigIsScreamWord(phoneConfig);
    }

}
