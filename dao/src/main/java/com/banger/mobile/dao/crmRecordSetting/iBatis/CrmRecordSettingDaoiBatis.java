/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :录音设置
 * Author     :yujh
 * Create Date:Sep 3, 2012
 */
package com.banger.mobile.dao.crmRecordSetting.iBatis;

import com.banger.mobile.dao.crmRecordSetting.CrmRecordSettingDao;
import com.banger.mobile.domain.model.crmRecordSetting.CrmRecordSetting;
import com.banger.mobile.ibatis.GenericDaoiBatis;

/**
 * @author yujh
 * @version $Id: CrmRecordSettingDaoiBatis.java,v 0.1 Sep 3, 2012 4:20:31 PM Administrator Exp $
 */
public class CrmRecordSettingDaoiBatis extends GenericDaoiBatis implements CrmRecordSettingDao{

    public CrmRecordSettingDaoiBatis(Class persistentClass) {
        super(persistentClass);
    }
    public CrmRecordSettingDaoiBatis(){
        super(CrmRecordSettingDaoiBatis.class);
    }
    //删除
    public void deleteCrmRecordSetting(int userId) {
        this.getSqlMapClientTemplate().delete("deleteCrmRecordSetting", userId);
    }
    //查询
    public CrmRecordSetting getCrmRecordSettingById(int userId) {
        return (CrmRecordSetting)this.getSqlMapClientTemplate().queryForObject("getCrmRecordSettingById", userId);
    }
    //添加
    public void insertCrmRecordSetting(CrmRecordSetting crmRecordSetting) {
        this.getSqlMapClientTemplate().insert("insertCrmRecordSetting", crmRecordSetting);
    }
    //更新
    public void updateCrmRecordSetting(CrmRecordSetting crmRecordSetting) {
        this.getSqlMapClientTemplate().update("updateCrmRecordSetting", crmRecordSetting);
    }

}
