/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :cheny
 * Create Date:2012-5-17
 */
package com.banger.mobile.dao.dept.ibatis;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.dao.dept.DeptDao;
import com.banger.mobile.domain.model.dept.CusBelongToBean;
import com.banger.mobile.domain.model.dept.DeptUserBean;
import com.banger.mobile.domain.model.dept.SysDept;
import com.banger.mobile.domain.model.dept.UserOnlineBean;
import com.banger.mobile.domain.model.dept.UserSubordinateBean;
import com.banger.mobile.domain.model.user.SysUser;
import com.banger.mobile.ibatis.GenericDaoiBatis;

/**
 * @author cheny
 * @version $Id: DeptDaoiBatis.java,v 0.1 2012-5-17 下午1:34:07 cheny Exp $
 */
public class DeptDaoiBatis extends GenericDaoiBatis implements DeptDao{

    /**
     *
     */
    public DeptDaoiBatis() {
        super(SysDept.class);
    }
    /**
     * @param persistentClass
     */
    public DeptDaoiBatis(Class persistentClass) {
        super(SysDept.class);
    }

    /**本机构用户分页
     * @param parameters
     * @param page
     * @return
     * @see com.banger.mobile.dao.dept.DeptDao#getDeptUserPage(java.util.Map, com.banger.mobile.Page)
     */
    public PageUtil<DeptUserBean> getDeptUserPage(Map<String, Object> parameters, Page page) {
        ArrayList<DeptUserBean> list = (ArrayList<DeptUserBean>) this.findQueryPage(
                "getDeptUserList", "getDeptUserCount", parameters, page);
        if (list == null) {
            list = new ArrayList<DeptUserBean>();
        }
        return new PageUtil<DeptUserBean>(list, page);
    }
    /**
     * 包含下属机构用户分页
     * @param parameters
     * @param page
     * @return
     * @see com.banger.mobile.dao.dept.DeptDao#getDeptUserPage(java.util.Map, com.banger.mobile.Page)
     */
    public PageUtil<DeptUserBean> getDeptSubsUserPage(Map<String, Object> parameters, Page page) {
        ArrayList<DeptUserBean> list = (ArrayList<DeptUserBean>) this.findQueryPage(
                "getDeptSubsUserList", "getDeptSubsUserCount", parameters, page);
        if (list == null) {
            list = new ArrayList<DeptUserBean>();
        }
        return new PageUtil<DeptUserBean>(list, page);
    }

    /**新增一个机构
     * @param dept
     * @see com.banger.mobile.dao.dept.DeptDao#insertDept(com.banger.mobile.domain.model.dept.SysDept)
     */
    public int insertDept(SysDept dept) {
        return (Integer)this.getSqlMapClientTemplate().insert("insertDept",dept);
    }

    /**
     * 执行删除
     * 根据机构deptId删除机构
     * @param deptId
     * @see com.banger.mobile.dao.dept.DeptDao#deleteDeptById(int)
     */
    public void deleteDeptById(int deptId){
        this.getSqlMapClientTemplate().delete("deleteDeptById",deptId);
    }

    /**修改机构信息
     * @param dept
     * @see com.banger.mobile.dao.dept.DeptDao#updateDept(com.banger.mobile.domain.model.dept.SysDept)
     */
    public void updateDept(SysDept dept) {
        this.getSqlMapClientTemplate().update("updateDept", dept);
    }

    /**
     * 根据deptId查询机构
     * @param dept
     * @return
     */
    public SysDept getDeptById(int deptId){
        return (SysDept) this.getSqlMapClientTemplate().queryForObject("getDeptById",deptId);
    }
    /**
     * 查询一级子部门
     * @param deptParentId
     * @return
     */
    public List<SysDept> getChildDept(int deptParentId){
        return this.getSqlMapClientTemplate().queryForList("getChildDept",deptParentId);
    }

    /**
     * 查询一级子部门sortno
     * @param deptParentId
     * @return
     */
    public List<SysDept> getChildDeptSortno(int deptParentId){
        return this.getSqlMapClientTemplate().queryForList("getChildDeptSortno",deptParentId);
    }
    /**
     * 查询所有部门
     * @return
     */
    public List<SysDept> getAllDepts(){
        return this.getSqlMapClientTemplate().queryForList("getAllDepts");
    }
    /**
     * 根据deptName查询部门
     * @param deptName
     * @return
     */
    public List<SysDept> getDeptByName(String deptName){
        return this.getSqlMapClientTemplate().queryForList("getDeptByName",deptName);
    }
    /**
     *  归属本部门及下属部门
     */
    public List<SysDept> getSubListDept(Map<String,Object> map){
        return this.getSqlMapClientTemplate().queryForList("getSubListDept",map);
    }
    /**
     * 查找兄弟部门除自己
     */
    public List<SysDept> getBrotherDept(Map<String,Object> map){
        return this.getSqlMapClientTemplate().queryForList("getBrotherDept",map);
    }

    /**
     * 查询本部门及下属部门用户ID
     */
    public List<Integer> getUserDel(String deptSearchCode){
        return this.getSqlMapClientTemplate().queryForList("getUserDel",deptSearchCode);
    }
    /**
     * 查找本部门及下属部门
     */
    public List<SysDept> getDeptAndSubs(String deptSearchCode){
        return this.getSqlMapClientTemplate().queryForList("getDeptAndSubs",deptSearchCode);
    }
    /**
     * 查找下属部门不包括自己
     */
    public List<SysDept> getSubDepts(Map<String,Object> map){
        return this.getSqlMapClientTemplate().queryForList("getSubDepts",map);
    }

    /**
     * 部门名称唯一性验证
     */
    public SysDept validateDeptNameIsExist(Map<String,Object> map){
        return (SysDept) this.getSqlMapClientTemplate().queryForObject("deptNameIsExist",map);
    }
    /**
     * 部门编码唯一性验证
     */
    public SysDept validateDeptCodeIsExist(Map<String,Object> map){
        return (SysDept) this.getSqlMapClientTemplate().queryForObject("deptCodeIsExist",map);
    }
    /**
     * 查询所管理的机构树 
     */
    public List<SysDept> getInCharegeDeptTree(String searchKey){
        return this.getSqlMapClientTemplate().queryForList("getInCharegeDeptTree",searchKey);
    }
    /**
     * 查询管理的根机构serachKey
     */
    public List<String> getInCharegeSearchKey(Map<String,Object> map){
        return this.getSqlMapClientTemplate().queryForList("getInCharegeSearchKey",map);
    }
    /**
     * 查询所管理机构下的用户
     */
    public List<SysUser> getInChargeDeptUsers(String searchKet){
        return this.getSqlMapClientTemplate().queryForList("getInChargeDeptUsers",searchKet);
    }

    /**
     * 获得所在机构及下属机构 和 人员list
     */
    public List<CusBelongToBean> getBelongToUserAndDeptTreeList(Map<String,Object> map){
        return this.getSqlMapClientTemplate().queryForList("getInChargeUserAndDept", map);
    }

    /**
     * 查询同级机构及上级机构集合
     */
    public List<SysDept> getExamineDeptList(int deptParentId){
        return this.getSqlMapClientTemplate().queryForList("getExamineDeptList",deptParentId);
    }
    /**
     * @param parameters
     * @param page
     * @return
     * @see com.banger.mobile.dao.dept.DeptDao#getUserOnlinePage(java.util.Map, com.banger.mobile.Page)
     */
    public PageUtil<UserOnlineBean> getUserOnlinePage(Map<String, Object> parameters, Page page) {
        ArrayList<UserOnlineBean> list = (ArrayList<UserOnlineBean>) this.findQueryPage(
                "getUserOnlineList", "getUserOnlineCount", parameters, page);
        if (list == null) {
            list = new ArrayList<UserOnlineBean>();
        }
        return new PageUtil<UserOnlineBean>(list, page);
    }
    /**
     * @param parameters
     * @param page
     * @return
     * @see com.banger.mobile.dao.dept.DeptDao#getSubsUserOnlineList(java.util.Map, com.banger.mobile.Page)
     */
    public PageUtil<UserOnlineBean> getSubsUserOnlineList(Map<String, Object> parameters, Page page) {
        ArrayList<UserOnlineBean> list = (ArrayList<UserOnlineBean>) this.findQueryPage(
                "getSubsUserOnlineList", "getSubsUserOnlineCount", parameters, page);
        if (list == null) {
            list = new ArrayList<UserOnlineBean>();
        }
        return new PageUtil<UserOnlineBean>(list, page);
    }
    /**
     * @param parameters
     * @param page
     * @return
     * @see com.banger.mobile.dao.dept.DeptDao#getUserSubordinatePage(java.util.Map, com.banger.mobile.Page)
     */
    public PageUtil<UserSubordinateBean> getUserSubordinatePage(Map<String, Object> parameters,
                                                                Page page) {
        ArrayList<UserSubordinateBean> list = (ArrayList<UserSubordinateBean>) this.findQueryPage(
                "getDeptSubordinateList", "getDeptSubordinateCount", parameters, page);
        if (list == null) {
            list = new ArrayList<UserSubordinateBean>();
        }
        return new PageUtil<UserSubordinateBean>(list, page);
    }
    /**
     * @param parameters
     * @param page
     * @return
     * @see com.banger.mobile.dao.dept.DeptDao#getSubsUserSubordinateList(java.util.Map, com.banger.mobile.Page)
     */
    public PageUtil<UserSubordinateBean> getSubsUserSubordinateList(Map<String, Object> parameters,
                                                                    Page page) {
        ArrayList<UserSubordinateBean> list = (ArrayList<UserSubordinateBean>) this.findQueryPage(
                "getDeptSubordinateUserList", "getDeptSubordinateUserCount", parameters, page);
        if (list == null) {
            list = new ArrayList<UserSubordinateBean>();
        }
        return new PageUtil<UserSubordinateBean>(list, page);
    }

    /**
     * 根据deptIds查询机构serachKey
     */
    public List<String> getDeptSearchKeyByIds(String deptIds){
        return this.getSqlMapClientTemplate().queryForList("getDeptSearchKeyByIds",deptIds);
    }
    /**
     * 查询归属机构的客户
     */
    public Integer getBelongCustomerCount(String deptIds){
        return (Integer) this.getSqlMapClientTemplate().queryForObject("getBelongCustomerCount",deptIds);
    }
    /**
     * 根据deptSearchCode查询机构名称
     */
    public List<String> getDeptNameByDeptSearchCode(String key){
        return this.getSqlMapClientTemplate().queryForList("getDeptNameByDeptSearchCode",key);
    }
    /**
     * 根据deptIds查询实体集合 
     */
    public List<SysDept> getDeptsByDeptIds(String ids){
        return this.getSqlMapClientTemplate().queryForList("getDeptsByDeptIds",ids);
    }
    /**
     * @param parameters
     * @return
     * @see com.banger.mobile.dao.dept.DeptDao#getUserOnlineOffline(java.util.Map)
     */
    public List<UserOnlineBean> getUserOnlineOffline(Map<String, Object> parameters) {
        return  this.getSqlMapClientTemplate().queryForList("getUserOnlineOffline",parameters);
    }
    /**
     * @param parameters
     * @return
     * @see com.banger.mobile.dao.dept.DeptDao#getSubsUserOnlineOffline(java.util.Map)
     */
    public List<UserOnlineBean> getSubsUserOnlineOffline(Map<String, Object> parameters) {
        return  this.getSqlMapClientTemplate().queryForList("getSubsUserOnlineOffline",parameters);
    }

    /**
     * 客户经理审核人
     */
    public SysDept getExamineCommonList(String code){
        return (SysDept)this.getSqlMapClientTemplate().queryForObject("getExamineCommonList",code);
    }

    /**
     * 查询营销人员机构树
     */
    public List<SysDept> getCounterUserDeptList(){
        return this.getSqlMapClientTemplate().queryForList("getCounterUserDeptList");
    }

    /**
     * 查询所管理的机构树（不包含没有人员的机构）
     */
    public List<SysDept> getInCharegeDeptListFilter(String searchKey){
        return this.getSqlMapClientTemplate().queryForList("getInCharegeDeptListFilter",searchKey);
    }

    /**
     * 客户经理所管理的柜台人员
     */
    public List<Integer> getCounterUserBelongCommon(int userId){
        return this.getSqlMapClientTemplate().queryForList("getCounterUserBelongCommon",userId);
    }
    /**
     * 业务经理所管理的柜台人员
     */
    public List<Integer> getCounterUserBelongManager(String deptIds){
        return this.getSqlMapClientTemplate().queryForList("getCounterUserBelongManager",deptIds);
    }
    /**
     * 查询所管理的用户id (包含删除和停用)
     */
    public List<Integer> getUserIdsBelongToManager(String deptIds){
        return this.getSqlMapClientTemplate().queryForList("getUserIdsBelongToManager",deptIds);
    }

    /**
     * 查询所有的机构list（缓存）
     */
    public List<SysDept> getAllDeptForCache(){
        return this.getSqlMapClientTemplate().queryForList("getAllDeptForCache");
    }
    @Override
    public List<DeptUserBean> getUserDept(String userIds) {
        // TODO Auto-generated method stub
        return this.getSqlMapClientTemplate().queryForList("getUserDept",userIds);
    }
}
