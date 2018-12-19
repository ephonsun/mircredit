/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :机构dao实现
 * Author     :cheny
 * Create Date:2012-5-17
 */
package com.banger.mobile.dao.dept;

import java.util.List;
import java.util.Map;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.model.dept.CusBelongToBean;
import com.banger.mobile.domain.model.dept.DeptUserBean;
import com.banger.mobile.domain.model.dept.SysDept;
import com.banger.mobile.domain.model.dept.UserOnlineBean;
import com.banger.mobile.domain.model.dept.UserSubordinateBean;
import com.banger.mobile.domain.model.user.SysUser;


/**
 * @author cheny
 * @version $Id: DeptDao.java,v 0.1 2012-5-17 下午1:31:09 cheny Exp $
 */
public interface DeptDao {

    /**
     * 查询所有部门
     * @return
     */
    public List<SysDept> getAllDepts();
    
    /**
     * 新增一个机构
     * @param dept
     */
    public int insertDept(SysDept dept);
    
    /**
     * 根据deptId删除机构信息
     * @param 
     */
    public void deleteDeptById(int deptId);
    
    /**
     * 修改机构
     * @param dept
     */
    public void updateDept(SysDept dept);
    
    
    /**
     * 在线用户信息在线，离线，列表
     * @param parameters
     * @param currentPage
     * @param pageSize
     * @return
     */
    public List<UserOnlineBean> getUserOnlineOffline(Map<String, Object> parameters);
    
    /**
     * 包含下属在线用户信息在线，离线，列表
     * @param parameters
     * @param currentPage
     * @param pageSize
     * @return
     */
    public List<UserOnlineBean> getSubsUserOnlineOffline(Map<String, Object> parameters);
    /**
     * 根据deptId查找机构
     * @param deptId
     * @return
     */
    public SysDept getDeptById(int deptId);
    
    /**
     * 查询一级子部门
     * @param deptParentId
     * @return
     */
    public List<SysDept> getChildDept(int deptParentId);
    
    
    /**
     * 在线用户信息分页
     * @param parameters
     * @param currentPage
     * @param pageSize
     * @return
     */
    public PageUtil<UserOnlineBean> getUserOnlinePage(Map<String, Object> parameters, Page page);
    
    
    /**
     * 包含下属在线用户信息分页
     * @param parameters
     * @param currentPage
     * @param pageSize
     * @return
     */
    public PageUtil<UserOnlineBean> getSubsUserOnlineList(Map<String, Object> parameters, Page page);
    /**
     * 查询一级子部门sortno
     * @param deptParentId
     * @return
     */
    public List<SysDept> getChildDeptSortno(int deptParentId);
    /**
     * 用户信息分页
     * @param parameters
     * @param currentPage
     * @param pageSize
     * @return
     */
    public PageUtil<DeptUserBean> getDeptUserPage(Map<String, Object> parameters, Page page);
    
    /**
     * 包含下属机构用户分页
     * @param parameters
     * @param page
     * @return
     * @see com.banger.mobile.dao.dept.DeptDao#getDeptUserPage(java.util.Map, com.banger.mobile.Page)
     */
    public PageUtil<DeptUserBean> getDeptSubsUserPage(Map<String, Object> parameters, Page page);

    /**
     * 根据deptName查询部门
     * @param deptName
     * @return
     */
    public List<SysDept> getDeptByName(String deptName);
    
    /**
     *  归属本部门及下属部门
     */
    public List<SysDept> getSubListDept(Map<String,Object> map);

    /**
     * 查找兄弟部门除自己
     */
    public List<SysDept> getBrotherDept(Map<String,Object> map);
    
    /**
     * 查询本部门及下属部门用户ID
     */
    public List<Integer> getUserDel(String deptSearchCode);
    /**
     * 查找本部门及下属部门
     */
    public List<SysDept> getDeptAndSubs(String deptSearchCode);
    /**
     * 查找下属部门不包括自己
     */
    public List<SysDept> getSubDepts(Map<String,Object> map);
    /**
     * 部门名称唯一性验证
     */
    public SysDept validateDeptNameIsExist(Map<String,Object> map);
    /**
     * 部门编码唯一性验证
     */
    public SysDept validateDeptCodeIsExist(Map<String,Object> map);
    
    /**
     * 查询所管理的机构树 
     */
    public List<SysDept> getInCharegeDeptTree(String searchKey);
    /**
     * 查询管理的根机构serachKey
     */
    public List<String> getInCharegeSearchKey(Map<String,Object> map);
    /**
     * 查询所管理机构下的用户
     */
    public List<SysUser> getInChargeDeptUsers(String searchKet);
    
    /**
     * 获得所在机构及下属机构 和 人员list
     */
    public List<CusBelongToBean> getBelongToUserAndDeptTreeList(Map<String,Object> map);
    /**
     * 查询同级机构及上级机构集合
     */
    public List<SysDept> getExamineDeptList(int deptParentId);
    
    /**
     * 下属用户信息分页
     * @param parameters
     * @param currentPage
     * @param pageSize
     * @return
     */
    public PageUtil<UserSubordinateBean> getUserSubordinatePage(Map<String, Object> parameters, Page page);
    /**
     * 包含下属用户信息分页
     * @param parameters
     * @param currentPage
     * @param pageSize
     * @return
     */
    public PageUtil<UserSubordinateBean> getSubsUserSubordinateList(Map<String, Object> parameters, Page page);
    /**
     * 根据deptIds查询机构serachKey
     */
    public List<String> getDeptSearchKeyByIds(String deptIds);
    /**
     * 查询归属机构的客户
     */
    public Integer getBelongCustomerCount(String deptIds);
    /**
     * 根据deptSearchCode查询机构名称
     */
    public List<String> getDeptNameByDeptSearchCode(String key);
    /**
     * 根据deptIds查询实体集合 
     */
    public List<SysDept> getDeptsByDeptIds(String ids);
    /**
     * 客户经理审核人
     */
    public SysDept getExamineCommonList(String code);
    
    /**
     * 查询营销人员机构树
     */
    public List<SysDept> getCounterUserDeptList();
    
    /**
     * 查询所管理的机构树（不包含没有人员的机构）
     */
    public List<SysDept> getInCharegeDeptListFilter(String searchKey);
    /**
     * 客户经理所管理的柜台人员
     */
    public List<Integer> getCounterUserBelongCommon(int userId);
    
    /**
     * 业务经理所管理的柜台人员
     */
    public List<Integer> getCounterUserBelongManager(String deptIds);
    /**
     * 查询所管理的用户id (包含删除和停用)
     */
    public List<Integer> getUserIdsBelongToManager(String deptIds);
    
    /**
     * 查询所有的机构list（缓存）
     */
    public List<SysDept> getAllDeptForCache();
    /**
     * 查询用户部门角色信息
     * @param userIds
     * @return
     */
    public List<DeptUserBean> getUserDept(String userIds);
}
