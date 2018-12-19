/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :铃声配置...
 * Author     :Administrator
 * Create Date:Aug 30, 2012
 */
package com.banger.mobile.facade.impl.system.crmRingSetting;

import com.banger.mobile.dao.crmRingSetting.CrmRingSettingDao;
import com.banger.mobile.domain.model.crmRingSetting.CrmRingSetting;
import com.banger.mobile.facade.crmRingSetting.CrmRingSettingService;

/**
 * @author yujh
 * @version $Id: CrmRingSettingImpl.java,v 0.1 Aug 30, 2012 10:51:01 AM Administrator Exp $
 */
public class CrmRingSettingServiceImpl implements CrmRingSettingService {
    private CrmRingSettingDao crmRingSettingDao;
    
    public void setCrmRingSettingDao(CrmRingSettingDao crmRingSettingDao) {
        this.crmRingSettingDao = crmRingSettingDao;
    }

    /**
     * 删除
     */
    public void deleteCrmRingSetting(int userId) {
        this.crmRingSettingDao.deleteCrmRingSetting(userId);
    }

    /**
     * 查询
     */
    public CrmRingSetting getCrmRingSettingByUserId(int userId) {
        return this.crmRingSettingDao.getCrmRingSettingByUserId(userId);
    }

    /**
     * 添加
     */
    public void insertCrmRingSetting(int userId) {
        CrmRingSetting crs = new CrmRingSetting();
        crs.setUserId(userId);
        crs.setFilePath("");
        this.crmRingSettingDao.insertCrmRingSetting(crs);
    }
    
    /**
     * 添加
     */
    public void insertCrmRingSetting(CrmRingSetting crmRingSetting){
    	this.crmRingSettingDao.insertCrmRingSetting(crmRingSetting);
    }

    /**
     * 更新
     */
    public void updateCrmRingSetting(CrmRingSetting crmRingSetting) {
        this.crmRingSettingDao.updateCrmRingSetting(crmRingSetting);
    }

    /**
     * 根据id 获得铃声设置
     */
    public CrmRingSetting getCrmRingSettingById(Integer id) {
        return this.crmRingSettingDao.getCrmRingSettingById(id);
    }

}
