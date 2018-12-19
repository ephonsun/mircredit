/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :web端限流
 * Author     :yujh
 * Create Date:Aug 24, 2012
 */
package com.banger.mobile.dao.sysWebFlowControl.iBatis;

import java.util.List;

import com.banger.mobile.dao.sysWebFlowControl.SysWebFlowControlDao;
import com.banger.mobile.domain.model.sysWebFlowControl.SysWebFlowControl;
import com.banger.mobile.domain.model.sysWebFlowControl.SysWebFlowControlInfo;
import com.banger.mobile.ibatis.GenericDaoiBatis;

/**
 * @author yujh
 * @version $Id: SysWebFlowControlDaoiBatis.java,v 0.1 Aug 24, 2012 5:37:41 PM Administrator Exp $
 */
public class SysWebFlowControlDaoiBatis extends GenericDaoiBatis implements SysWebFlowControlDao{

    public SysWebFlowControlDaoiBatis(Class persistentClass) {
        super(persistentClass);
    }
    public SysWebFlowControlDaoiBatis(){
        super(SysWebFlowControlDaoiBatis.class);
    }

    /**
     * 删除
     */
    public void deleteFlowControl(int deptId) {
        this.getSqlMapClientTemplate().delete("deleteFlowControl", deptId);
    }

    /**
     * 列表
     */
    public List<SysWebFlowControlInfo> getAllFlowControl() {
        return this.getSqlMapClientTemplate().queryForList("getAllFlowControl");
    }

    /**
     * 添加
     */
    public void insertFlowControl(SysWebFlowControl flowControl) {
        this.getSqlMapClientTemplate().insert("insertFlowControl", flowControl);
    }

    /**
     * 更新
     */
    public void updateFlowControl(SysWebFlowControl flowControl) {
        this.getSqlMapClientTemplate().update("updateFlowControl", flowControl);
    }
    
    /**
     * 账号流量限制参数
     */
    public SysWebFlowControl getFlowControlByAccount(String account) {
        return (SysWebFlowControl) this.getSqlMapClientTemplate().queryForObject("getFlowControlByAccount", account);
    }

}
