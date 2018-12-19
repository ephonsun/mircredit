/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :录音提示音类...
 * Author     :yujh
 * Create Date:Aug 30, 2012
 */
package com.banger.mobile.facade.impl.system.crmRecordRemind;

import com.banger.mobile.dao.crmRecordRemind.CrmRecordRemindDao;
import com.banger.mobile.domain.model.crmRecordRemind.CrmRecordRemind;
import com.banger.mobile.facade.crmRecordRemind.CrmRecordRemindService;

/**
 * @author yujh
 * @version $Id: CrmRecordRemindServiceImpl.java,v 0.1 Aug 30, 2012 10:50:47 AM Administrator Exp $
 */
public class CrmRecordRemindServiceImpl implements CrmRecordRemindService{
    private CrmRecordRemindDao crmRecordRemindDao;
    

    public void setCrmRecordRemindDao(CrmRecordRemindDao crmRecordRemindDao) {
        this.crmRecordRemindDao = crmRecordRemindDao;
    }

    /**
     * 删除
     */
    public void deleteCrmRecordRemind(int userId) {
        this.crmRecordRemindDao.deleteCrmRecordRemind(userId);
    }

    /**
     * 查询
     */
    public CrmRecordRemind getCrmRecordRemindByUserId(int userId) {
        return this.crmRecordRemindDao.getCrmRecordRemindByUserId(userId);
    }

    /**
     * 添加
     */
    public void insertCrmRecordRemind(int userId) {
        CrmRecordRemind crr= new CrmRecordRemind();
        crr.setFilePath("");
        crr.setUserId(userId);
        crr.setFileMd5("");//MD5值2012-11-20 17:47:07修改
        this.crmRecordRemindDao.insertCrmRecordRemind(crr);
    }
    
    /**
     * 添加
     */
    public void insertCrmRecordRemind(CrmRecordRemind crmRecordRemind) {
    	this.crmRecordRemindDao.insertCrmRecordRemind(crmRecordRemind);
    }

    /**
     * 更新
     */
    public void updateCrmRecordRemind(CrmRecordRemind crmRecordRemind) {
        this.crmRecordRemindDao.updateCrmRecordRemind(crmRecordRemind);
    }

    /**
     * 获得录音提示音配置对象
     */
    public CrmRecordRemind getCrmRecordRemindById(Integer id) {
        return this.crmRecordRemindDao.getCrmRecordRemindById(id);
    }

}
