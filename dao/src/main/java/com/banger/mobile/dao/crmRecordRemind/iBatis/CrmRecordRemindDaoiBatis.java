/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :录音提示音
 * Author     :yujh
 * Create Date:Aug 30, 2012
 */

package com.banger.mobile.dao.crmRecordRemind.iBatis;

import com.banger.mobile.dao.crmRecordRemind.CrmRecordRemindDao;
import com.banger.mobile.domain.model.crmRecordRemind.CrmRecordRemind;
import com.banger.mobile.ibatis.GenericDaoiBatis;

/**
 * @author yujh
 * @version $Id: CrmRecordRemindDaoiBatis.java,v 0.1 Aug 30, 2012 8:59:59 AM Administrator Exp $
 */
public class CrmRecordRemindDaoiBatis extends GenericDaoiBatis implements CrmRecordRemindDao{

    public CrmRecordRemindDaoiBatis(Class persistentClass) {
        super(persistentClass);
    }
    public CrmRecordRemindDaoiBatis(){
        super(CrmRecordRemindDaoiBatis.class
            );
    }

    /**
     * 删除
     * @param userId
     */
    public void deleteCrmRecordRemind(int userId) {
        this.getSqlMapClientTemplate().delete("deleteCrmRecordRemind", userId);
    }

    /**
     * 查询
     * @param userId
     * @return
     */
    public CrmRecordRemind getCrmRecordRemindByUserId(int userId) {
        return (CrmRecordRemind)this.getSqlMapClientTemplate().queryForObject("getCrmRecordRemindByUserId", userId);
    }

    /**
     * 添加
     * @param crmRecordRemind
     */
    public void insertCrmRecordRemind(CrmRecordRemind crmRecordRemind) {
        this.getSqlMapClientTemplate().insert("insertCrmRecordRemind", crmRecordRemind);
    }

    /**
     * 更新
     * @param crmRecordRemind
     */
    public void updateCrmRecordRemind(CrmRecordRemind crmRecordRemind) {
        this.getSqlMapClientTemplate().update("updateCrmRecordRemind", crmRecordRemind);
    }
    /**
     * 获得录音提示音配置对象
     */
    public CrmRecordRemind getCrmRecordRemindById(Integer id) {
        return (CrmRecordRemind)this.getSqlMapClientTemplate().queryForObject("getCrmRecordRemindById", id);
    }
    
}
