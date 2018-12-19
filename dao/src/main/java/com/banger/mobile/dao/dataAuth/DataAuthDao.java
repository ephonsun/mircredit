/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :数据访问权限 dao
 * Author     :cheny
 * Create Date:2012-5-29
 */
package com.banger.mobile.dao.dataAuth;

import java.util.List;

import com.banger.mobile.domain.model.dataAuth.SysDataAuth;

/**
 * @author cheny
 * @version $Id: DataAuthDao.java,v 0.1 2012-5-29 上午10:50:22 cheny Exp $
 */
public interface DataAuthDao {
    /**
     * 新增数据访问权限
     * @param dataAuth
     */
    public void saveDataAuth(SysDataAuth dataAuth);
    /**
     * 删除数据访问权限
     * @param roleId
     */
    public void deleteDataAuth(SysDataAuth dataAuth);
    /**
     * 根据roleId查询数据访问权限
     * @param roleId
     * @return
     */
    public List<SysDataAuth> getDataAuthByRoleId(int roleId);
}
