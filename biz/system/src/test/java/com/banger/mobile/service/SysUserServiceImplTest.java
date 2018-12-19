/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :yangy
 * Create Date:2012-5-25
 */
package com.banger.mobile.service;


import java.util.HashMap;
import java.util.Map;

import com.banger.mobile.facade.BaseServiceTestCase;
import com.banger.mobile.facade.user.SysUserService;

/**
 * @author yangyang
 * @version $Id: SysUserServiceImplTest.java,v 0.1 2012-5-25 下午4:40:59 yangyong Exp $
 */
public class SysUserServiceImplTest extends BaseServiceTestCase {
    
    private SysUserService    sysUserService;

    public void setSysUserService(SysUserService sysUserService) {
        this.sysUserService = sysUserService;
    }
    /**
    * 测试Manager是否为空
    * @throws Exception
    */
   public void testServiceNotNull() throws Exception {
       assertNotNull(sysUserService);
   }
   
   public void testGetSuperiorUserList()throws Exception {
//       Map<String, Object> map=new HashMap<String, Object>();
//       map.put("userId", 21);
//       map.put("account", "dept");
//       sysUserService.getSuperiorUserList(map);
   }
   public void testgetRoleNamesByUserId(){
        Map<Integer, String> map = sysUserService.getRoleNamesByUserId("10,11,31,32,44");
        for (Integer id : map.keySet()) {
            System.out.println(id+":"+map.get(id));
        }
    }
       
   
   
}
