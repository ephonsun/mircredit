/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :数据访问权限 dao实现
 * Author     :cheny
 * Create Date:2012-5-29
 */
package com.banger.mobile.dao.dataAuth.ibatis;

import java.util.List;

import com.banger.mobile.dao.dataAuth.DataAuthDao;
import com.banger.mobile.domain.model.dataAuth.SysDataAuth;
import com.banger.mobile.ibatis.GenericDaoiBatis;

/**
 * @author cheny
 * @version $Id: DataAuthDaoiBatis.java,v 0.1 2012-5-29 上午10:51:00 cheny Exp $
 */
public class DataAuthDaoiBatis extends GenericDaoiBatis implements DataAuthDao{

    /**
     * 
     */
    public DataAuthDaoiBatis() {
        super(SysDataAuth.class);
    }
    /**
     * @param persistentClass
     */
    public DataAuthDaoiBatis(Class persistentClass) {
        super(SysDataAuth.class);
    }
    
    /**
     * 新增数据访问权限
     * @param dataAuth
     */
    public void saveDataAuth(SysDataAuth dataAuth){
        this.getSqlMapClientTemplate().insert("insertDataAuth",dataAuth);
    }
    /**
     * 删除数据访问权限
     * @param roleId
     */
    public void deleteDataAuth(SysDataAuth dataAuth){
        this.getSqlMapClientTemplate().delete("deleteDataAuth", dataAuth);
    }
    /**
     * 根据roleId查询数据访问权限
     * @param roleId
     * @return
     */
    public List<SysDataAuth> getDataAuthByRoleId(int roleId){
        return this.getSqlMapClientTemplate().queryForList("getDataAuthByRoleId",roleId);
    }
    
}
