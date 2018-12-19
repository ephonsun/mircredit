/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :liyb
 * Create Date:2012-5-22
 */
package com.banger.mobile.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.banger.mobile.dao.role.SysRoleDao;
import com.banger.mobile.domain.model.role.SysRole;

/**
 * @author liyb
 * @version $Id: SysRoleTest.java,v 0.1 2012-5-22 下午01:42:16 liyb Exp $
 */
public class SysRoleTest extends BaseDaoTestCase{
    private SysRoleDao sysRoleDao;

    public void setSysRoleDao(SysRoleDao sysRoleDao) {
        this.sysRoleDao = sysRoleDao;
    }
    /**
     * 测试dao是否为空
     * @throws Exception
     */
    public void testDaoNotNull() throws Exception {
        assertNotNull(sysRoleDao);
    }
    
    /**
     * 角色分页列表
     */
    public void testGetSysRolePage(){
        Map<String,Object> map=new HashMap<String, Object>();
//        map.put("roleName", "测试");
        sysRoleDao.getSysRolePage(map, null);
    }
    
    public void testGetAllRoleName(){
        sysRoleDao.getAllRoleName();
    }
    
    public void testGetSysRoleById(){
        SysRole sysRole=new SysRole();
        sysRole.setRoleId(1);
        sysRoleDao.getSysRoleById(sysRole);
    }
    
    /**
     * 添加角色
     */
    public void testInsertSysRole(){
        SysRole sysRole=new SysRole();
        sysRole.setCreateDate(new Date());
        sysRole.setIsDel(0);
        sysRole.setRoleTypeId(2);
        sysRole.setRoleName("SASASASAS");
        sysRole.setRemark("SASASASAS");
//        sysRoleDao.insertSysRole(sysRole);
    }
    public void testGetIsUseCount(){
        System.err.println(sysRoleDao.getIsUseRoleCount(1));
    }
}
