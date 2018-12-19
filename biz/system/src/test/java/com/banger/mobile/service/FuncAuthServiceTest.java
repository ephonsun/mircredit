/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :cheny
 * Create Date:2012-6-5
 */
package com.banger.mobile.service;

import java.util.List;

import com.banger.mobile.facade.BaseServiceTestCase;
import com.banger.mobile.facade.funcAuth.FuncAuthService;

/**
 * @author cheny
 * @version $Id: FuncAuthServiceTest.java,v 0.1 2012-6-5 下午12:56:57 cheny Exp $
 */
public class FuncAuthServiceTest extends BaseServiceTestCase{

    private FuncAuthService funcAuthService;

    public void setFuncAuthService(FuncAuthService funcAuthService) {
        this.funcAuthService = funcAuthService;
    }
    
    /**
     * 测试deptService是否为空
     * @throws Exception
     */
    public void testServiceNotNull() throws Exception{
        assertNotNull(funcAuthService);
    }
    /**
     * 登录用户返回功能操作的id集合
     */
    public void testgetFuncIdsByRolesIds(){
//        String[] sts = funcAuthService.getFuncIdsByRolesIds(new String[]{"204"});
//        assertEquals(2, sts.length);
    }

    /**
     * 登录用户返回菜单url
     */
    public void testgetMenuUrlByRoleIds(){
//        List<String> urls = funcAuthService.getMenuUrlByRoleIds(new String[]{"1"});
//        for (String str : urls) {
//         log.info(str);   
//        }
    }
    /**
     * 登录用户返回功能操作url
     */
    public void testgetFuncUrlByRoleIds(){
      List<String> urls = funcAuthService.getFuncUrlByRoleIds(new String[]{"204"});
      assertEquals(10, urls.size());
    }
}
