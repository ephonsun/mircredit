/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :cheny
 * Create Date:2012-5-17
 
package com.banger.mobile.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.dao.dept.DeptDao;
import com.banger.mobile.domain.model.dept.DeptUserBean;
import com.banger.mobile.domain.model.dept.SysDept;
import com.banger.mobile.domain.model.user.SysUser;

*//**
 * @author cheny
 * @version $Id: DeptDaoTest.java,v 0.1 2012-5-17 下午3:57:02 cheny Exp $
 *//*
public class DeptDaoTest extends BaseDaoTestCase{

    private DeptDao deptDao;

    public void setDeptDao(DeptDao deptDao) {
        this.deptDao = deptDao;
    }
    *//**
     * 测试dao是否为空
     * @throws Exception
     *//*
    public void testDaoNotNull() throws Exception {
        assertNotNull(deptDao);
    }
    *//**
     * 新增测试
     *//*
    public void testInsertDept() throws Exception {
//        SysDept dept = new SysDept();
//        dept.setDeptName("余杭支行");
//        dept.setDeptCode("123");
//        dept.setDeptParentId(1);
//        dept.setDeptSearchCode("001022");
//        dept.setIsDel(0);
//        dept.setIsLeaf(1);
//        dept.setCreateUser(1);
//        dept.setRemark("ssss");
//        dept.setSortno(1);
//        dept.setUpdateUser(1);
////        deptDao.insertDept(dept);
//        assertNotNull(dept);
//        log.info("insertDept deptId======"+dept.getDeptId());
    }
    *//**
     * 测试分页
     * @throws Exception
     *//*
    public void testGetDeptUserPage() throws Exception{
//      Map<String, Object> parameterMap = new HashMap<String, Object>();
//      parameterMap.put("deptId", "1");
//      Page page = new Page();
//      PageUtil<DeptUserBean> deptList = deptDao.getDeptUserPage(parameterMap, page);
//      log.info("total count======:" +  deptList.getPage().getTotalRowsAmount());
//      log.info("total page======:" + deptList.getPage().getTotalPages());
//      log.info("page size======:" + deptList.getItems().size());
    }
    
     public void testgetDeptSubsUserPage(){
//        Map<String, Object> parameterMap = new HashMap<String, Object>();
//        parameterMap.put("deptSearchCode", "001017");
//        Page page = new Page();
//        PageUtil<DeptUserBean> deptList = deptDao.getDeptSubsUserPage(parameterMap, page);
//        log.info("total count======:" +  deptList.getPage().getTotalRowsAmount());
//        log.info("total page======:" + deptList.getPage().getTotalPages());
//        log.info("page size======:" + deptList.getItems().size());
      }
    
    *//**
     * 测试修改机构
     *//*
//    public void testUpdateDept(){
//        SysDept dept = new SysDept();
//        dept.setDeptId(290);
//        dept.setDeptName("西湖分行");
//        dept.setCreateUser(1);
//        dept.setDeptCode("1111100000");
//        dept.setDeptParentId(1);
//        dept.setDeptSearchCode("001023");
//        dept.setIsDel(0);
//        dept.setIsLeaf(1);
//        dept.setUpdateUser(1);
//        dept.setRemark("test");
//        dept.setSortno(1);
////        deptDao.updateDept(dept);
//        log.info("Update deptName========" + dept.getDeptName());
//    }
    *//**
     * 测试根据id查询机构
     *//*
//    public void testGetDeptById(){
////        SysDept dept = deptDao.getDeptById(1);
//        log.info("getDeptbyId deptName ======" + deptDao.getDeptById(1).getDeptName());
//    }
    *//**
     * 测试删除机构
     *//*
    public void testDeleteDeptById(){
////        deptDao.deleteDeptById(310);
//        log.info("deleteDept success*************");
    }
    
      public void testGetChildDept(){
//          List<SysDept> depts = deptDao.getChildDept(1);
//          assertEquals(5, depts.size());
      }
    
      public void testGetChildDeptSortno(){
//          List<SysDept> depts = deptDao.getChildDeptSortno(1);
//          assertEquals(5, depts.size());
      }
      
      public void testGetAllDepts(){
//          List<SysDept> depts = deptDao.getAllDepts();
//          assertEquals(69, depts.size());
      }
      
      *//**
       * 根据deptName查询部门
       * @param deptName
       * @return
       *//*
      public void testGetDeptByName(){
//          SysDept dept = deptDao.getDeptByName("杭州分行");
//          assertEquals(2, dept.getDeptId().intValue());
      }
      *//**
       * 查询本部门及下属部门用户ID
       *//*
      public void testGetUserDel(){
//          List<Integer> userIds = deptDao.getUserDel("001");
//          assertEquals(71, userIds.size());
      }
      *//**
       * 测试部门名称唯一性
       *//*
    public void testValidateDeptName(){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("deptName", "杭州分行1");
        map.put("deptParentId", 1);
        map.put("deptCode", "1");
        SysDept dept = deptDao.validateDeptCodeIsExist(map);
        assertEquals(1,dept.getDeptId().intValue());
    }
 
}
*/