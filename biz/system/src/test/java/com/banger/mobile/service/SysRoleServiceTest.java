/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :角色service测试类
 * Author     :liyb
 * Create Date:2012-5-22
 */
package com.banger.mobile.service;

import com.banger.mobile.domain.model.role.SysRole;
import com.banger.mobile.facade.BaseServiceTestCase;
import com.banger.mobile.facade.role.SysRoleService;

/**
 * @author liyb
 * @version $Id: SysRoleService.java,v 0.1 2012-5-22 下午01:50:53 liyb Exp $
 */
public class SysRoleServiceTest extends BaseServiceTestCase {
    private SysRoleService sysRoleService;

    public void setSysRoleService(SysRoleService sysRoleService) {
        this.sysRoleService = sysRoleService;
    }

    /**
     * 测试Manager是否为空
     * @throws Exception
     */
    public void testServiceNotNull() throws Exception {
        assertNotNull(sysRoleService);
    }
    
    /**
     * 角色分页列表
     */
    public void testGetSysRolePage(){
        SysRole role=new SysRole();
        role.setRoleName("测试");
        sysRoleService.getSysRolePage(role, null);
    }
}
