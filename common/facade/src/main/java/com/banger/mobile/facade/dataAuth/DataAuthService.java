/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :数据访问权限接口
 * Author     :cheny
 * Create Date:2012-5-29
 */
package com.banger.mobile.facade.dataAuth;

import java.util.List;
import java.util.Map;

import com.banger.mobile.domain.model.dataAuth.SysDataAuth;
import com.banger.mobile.domain.model.role.SysRole;

/**
 * @author cheny
 * @version $Id: DataAuthService.java,v 0.1 2012-5-29 上午10:39:51 cheny Exp $
 */
public interface DataAuthService {
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
    /**添加数据访问权限
     * 
     */
    public void addDataAuth(SysRole role, Map<String,Object> map,int userId);
    /**设置数据访问权限
     * 
     */
    public Map<String,Object> setDataAuth(SysRole role);
    /**修改数据访问权限
     * 
     */
     public void updateDataAuthList(int roleId,  Map<String,Object> map,int userId);
}
