/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :录音设置
 * Author     :yujh
 * Create Date:Sep 3, 2012
 */
package com.banger.mobile.facade.impl.system.crmRecordSetting;

import com.banger.mobile.dao.crmRecordSetting.CrmRecordSettingDao;
import com.banger.mobile.domain.model.crmRecordSetting.CrmRecordSetting;
import com.banger.mobile.facade.crmRecordSetting.CrmRecordSettingService;

/**
 * @author yujh
 * @version $Id: CrmRecordSettingServiceImpl.java,v 0.1 Sep 3, 2012 4:53:00 PM Administrator Exp $
 */
public class CrmRecordSettingServiceImpl implements CrmRecordSettingService{
    private CrmRecordSettingDao     crmRecordSettingDao;
    

    public void setCrmRecordSettingDao(CrmRecordSettingDao crmRecordSettingDao) {
        this.crmRecordSettingDao = crmRecordSettingDao;
    }

    /**
     * 删除
     */
    public void deleteCrmRecordSetting(int userId) {
        this.crmRecordSettingDao.deleteCrmRecordSetting(userId);
    }

    /**
     * 查询
     */
    public CrmRecordSetting getCrmRecordSettingById(int userId) {
        
        return this.crmRecordSettingDao.getCrmRecordSettingById(userId);
    }

    /**
     * 添加
     */
    public void insertCrmRecordSetting(int userId) {
        CrmRecordSetting cs= new CrmRecordSetting();
        cs.setIsAutoPlay(1);
        cs.setIsAutoRecord(1);
        cs.setUserId(userId);
        this.crmRecordSettingDao.insertCrmRecordSetting(cs);
    }

    /**
     * 更新
     */
    public void updateCrmRecordSetting(CrmRecordSetting crmRecordSetting) {
        this.crmRecordSettingDao.updateCrmRecordSetting(crmRecordSetting);
    }

}
