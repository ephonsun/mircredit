/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :机构 业务测试类
 * Author     :cheny
 * Create Date:2012-5-18
 */
package com.banger.mobile.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.model.dept.DeptUserBean;
import com.banger.mobile.domain.model.dept.SysDept;
import com.banger.mobile.facade.BaseServiceTestCase;
import com.banger.mobile.facade.dept.DeptService;

/**
 * @author cheny
 * @version $Id: DeptServiceTest.java,v 0.1 2012-5-18 下午4:58:38 cheny Exp $
 */
public class DeptServiceTest extends BaseServiceTestCase{
    
    private DeptService deptService;

    public void setDeptService(DeptService deptService) {
        this.deptService = deptService;
    }
    
    /**
     * 测试deptService是否为空
     * @throws Exception
     */
    public void testServiceNotNull() throws Exception{
        assertNotNull(deptService);
    }
    
    /**
     * 测试新增
     * @throws Exception
     */
    public void testInsertDept() throws Exception{
        SysDept dept = new SysDept();
        dept.setDeptName("西湖支行");
        dept.setDeptCode("123");
        dept.setDeptParentId(1);
        dept.setDeptSearchCode("001022");
        dept.setIsDel(0);
        dept.setIsLeaf(1);
        dept.setCreateUser(1);
        dept.setRemark("ssss");
        dept.setSortno(1);
        dept.setUpdateUser(1);
//        deptService.insertDept(dept);
        assertNotNull(dept);
        log.info("deptId======"+dept.getDeptId());
        setComplete();
        endTransaction();
    }
    /**
     *测试删除机构信息
     */
    public void testDeleteDeptById(){
//        deptService.deleteDeptById(297);
        setComplete();
        endTransaction();
    }
    
    /**
     * 测试根据id查找机构
     */
    public void testGetDeptById(){
//        SysDept dept = deptService.getDeptById(1);
//        log.info("getDeptById  deptName======" + dept.getDeptName());
    }
    
    /**测试修改机构
     * 
     */
    public void testUpdateDept(){
        SysDept dept = new SysDept();
        dept.setDeptId(298);
        dept.setDeptName("西湖分行");
        dept.setCreateUser(1);
        dept.setDeptCode("1111100000");
        dept.setDeptParentId(1);
        dept.setDeptSearchCode("001023");
        dept.setIsDel(0);
        dept.setIsLeaf(1);
        dept.setUpdateUser(1);
        dept.setRemark("test");
        dept.setSortno(1);
//        deptService.updateDept(dept);
        setComplete();
        endTransaction();
        log.info("Update deptName========" + dept.getDeptName());
    }
    
    /**
     * 测试分页
     * @throws Exception
     */
    public void testGetDeptUserPage() throws Exception{
        Map<String, Object> parameterMap = new HashMap<String, Object>();
////        parameterMap.put("account", "l");
//        Page page = new Page();
//        PageUtil<DeptUserBean> deptList = deptService.getDeptUserPage(parameterMap, page);
//        log.info("total count======:" +  deptList.getPage().getTotalRowsAmount());
//        log.info("total page======:" + deptList.getPage().getTotalPages());
//        log.info("page size======:" + deptList.getItems().size());
    }
    /**
     * 包含下属部门测试分页
     * @throws Exception
     */
    public void testGetDeptSubsUserPage() throws Exception{
        Map<String, Object> parameterMap = new HashMap<String, Object>();
        parameterMap.put("deptSearchCode", "001017");
        Page page = new Page();
        PageUtil<DeptUserBean> deptList = deptService.getDeptSubsUserPage(parameterMap, page);
        log.info("total count======:" +  deptList.getPage().getTotalRowsAmount());
        log.info("total page======:" + deptList.getPage().getTotalPages());
        log.info("page size======:" + deptList.getItems().size());
    }


}
