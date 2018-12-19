/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :cheny
 * Create Date:2012-5-30
 */
package com.banger.mobile.service;

import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.banger.mobile.domain.model.dept.SysDept;
import com.banger.mobile.facade.BaseServiceTestCase;
import com.banger.mobile.facade.dept.DeptFacadeService;
import com.ibm.db2.jcc.am.de;

import edu.emory.mathcs.backport.java.util.Arrays;

/**
 * @author cheny
 * @version $Id: DeptFacadeServiceImplTest.java,v 0.1 2012-5-30 下午4:24:55 cheny Exp $
 */
public class DeptFacadeServiceImplTest extends BaseServiceTestCase{
    
    private DeptFacadeService deptFacadeService;

    public void setDeptFacadeService(DeptFacadeService deptFacadeService) {
        this.deptFacadeService = deptFacadeService;
    }
    
    /**
     * 测试deptService是否为空
     * @throws Exception
     */
    public void testServiceNotNull() throws Exception{
        assertNotNull(deptFacadeService);
    }
    
    public void testgetOnDeptUserData(){
//        Integer[] ids = deptFacadeService.getOnDeptUserData();
//        assertEquals(33, ids.length);
        
    }
    
    
    public void testgetInDeptUserData(){
//        Integer[] ids = deptFacadeService.getInDeptUserData();
//        assertEquals(44, ids.length);
//        
    }
    public void testgetContainUserData(){
//        Integer[] ids = deptFacadeService.getContainUserData();
//        assertEquals(77, ids.length);
        
    }

    public void testgetAllUserData(){
//        Integer[] ids = deptFacadeService.getAllUserData();
//        assertEquals(77, ids.length);
//        
    }
    
    public void testgetOnDeptAuth(){
//        List<SysDept> depts = deptFacadeService.getOnDeptAuth();
//        assertEquals("001", depts.get(0).getDeptSearchCode());
    }
    
    public void testgetAllDeptAuth(){
//        List<SysDept> depts = deptFacadeService.getAllDeptAuth();
//        assertEquals(33, depts.size());
    }
    /**
     * 判断是否有机构权限
     */
    public void testhasDeptDataAuth(){
//        Integer[] roleIds = {598,599,264,265,227};
//        boolean flag = deptFacadeService.hasDeptDataAuth(roleIds);
//        assertEquals(true, flag);
    }
    
    public void testgetInChargeOfDeptUserIds(){
//       Integer[] userIds = deptFacadeService.getInChargeOfDeptUserIds(553);
//       assertEquals(1, userIds.length);
    } 
       
   public void testgetDeptFullPath(){
       String name = deptFacadeService.getDeptFullPath(80);
       System.out.println(name);
//       assertEquals(1, name.length());
   }
   
//   @Test
//   public void testgetExamineCommonList(){
//       List<SysDept> depts = deptFacadeService.getExamineCommonList();
//       assertEquals(1, depts.size());
//       System.out.println(depts.get(0).getDeptName());
//   }
   
}
