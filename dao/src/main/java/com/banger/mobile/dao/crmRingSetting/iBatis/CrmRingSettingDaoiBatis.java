/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :铃声设置
 * Author     :yujh
 * Create Date:Aug 30, 2012
 */
package com.banger.mobile.dao.crmRingSetting.iBatis;

import com.banger.mobile.dao.crmRingSetting.CrmRingSettingDao;
import com.banger.mobile.domain.model.crmRingSetting.CrmRingSetting;
import com.banger.mobile.ibatis.GenericDaoiBatis;

/**
 * @author Administrator
 * @version $Id: CrmRingSettingDaoiBatis.java,v 0.1 Aug 30, 2012 9:07:43 AM Administrator Exp $
 */
public class CrmRingSettingDaoiBatis extends GenericDaoiBatis implements CrmRingSettingDao{

    public CrmRingSettingDaoiBatis(Class persistentClass) {
        super(persistentClass);
    }
    public CrmRingSettingDaoiBatis(){
        super(CrmRingSettingDaoiBatis.class);
    }

    /**
     * 删除
     */
    public void deleteCrmRingSetting(int userId) {
        this.getSqlMapClientTemplate().delete("deleteCrmRingSetting", userId);
    }

    /**
     * 查询 
     */
    public CrmRingSetting getCrmRingSettingByUserId(int userId) {
        return (CrmRingSetting)this.getSqlMapClientTemplate().queryForObject("getCrmRingSettingByUserId",userId);
    }

    /**
     * 添加
     */
    public void insertCrmRingSetting(CrmRingSetting crmRingSetting) {
        this.getSqlMapClientTemplate().insert("insertCrmRingSetting", crmRingSetting);
    }

    /**
     * 更新
     */
    public void updateCrmRingSetting(CrmRingSetting crmRingSetting) {
        this.getSqlMapClientTemplate().update("updateCrmRingSetting", crmRingSetting);
    }
    /**
     * 根据id获取铃声配置
     * @param id
     * @return
     */
    public CrmRingSetting getCrmRingSettingById(Integer id) {
        return (CrmRingSetting)this.getSqlMapClientTemplate().queryForObject("getCrmRingSettingById",id);
    }

}
