/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :机构业务接口
 * Author     :cheny
 * Create Date:2012-5-18
 */
package com.banger.mobile.facade.dept;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.model.dept.DeptUserBean;
import com.banger.mobile.domain.model.dept.SysDept;
import com.banger.mobile.domain.model.dept.UserOnlineBean;
import com.banger.mobile.domain.model.dept.UserSubordinateBean;

/**
 * @author cheny
 * @version $Id: DeptService.java,v 0.1 2012-5-18 下午4:41:16 cheny Exp $
 */
public interface DeptService {
    
    /**
     * 树转换为json
     * @return
     */
    public JSONArray getAllDeptJson();
    
    /**
     * 新增机构
     * @param dept
     * @param parentId
     * @param userId
     */
    public void insertDept(SysDept dept,Integer parentId,Integer userId);
    
    /**
     * 新增一个机构
     * @param dept
     */
    public void insertDept(SysDept dept,String deptParentName,int deptId,int userId);
    
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
     * 在线用户信息分页
     * @param parameters
     * @param currentPage
     * @param pageSize
     * @return
     */
    public PageUtil<UserOnlineBean> getUserOnlinePage(Map<String, Object> parameters, Page page);
    /**
     * 在线用户信息在线，离线，列表
     * @param parameters
     * @param currentPage
     * @param pageSize
     * @return
     */
    public List<UserOnlineBean> getUserOnlineOffline(Map<String, Object> parameters);
    
    /**
     * 包含下属在线用户信息分页
     * @param parameters
     * @param currentPage
     * @param pageSize
     * @return
     */
    public PageUtil<UserOnlineBean> getSubsUserOnlineList(Map<String, Object> parameters, Page page);
    
    
    /**
     * 包含下属在线用户信息在线，离线，列表
     * @param parameters
     * @param currentPage
     * @param pageSize
     * @return
     */
    public List<UserOnlineBean> getSubsUserOnlineOffline(Map<String, Object> parameters);
    
    
    
    
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
     * 包含下属部门分页
     * @param parameters
     * @param page
     * @return
     * @see com.banger.mobile.facade.dept.DeptService#getDeptUserPage(java.util.Map, com.banger.mobile.Page)
     */
    public PageUtil<DeptUserBean> getDeptSubsUserPage(Map<String, Object> parameters, Page page);
    /**
     * 根据用户部门信息
     * @param userIds
     * @return
     */
    public List<DeptUserBean> getDeptUserByIds(String userIds);
    /**
     * 根据deptName查询部门
     * @param deptName
     * @return
     */
    public List<SysDept> getDeptByName(String deptName);
    /**
     * 编辑部门
     * @param dept
     */
    public JSONObject editorDept(SysDept dept,String deptParentName,int deptId,int userId);
    
    /**
     *  归属本部门及下属部门
     */
    public List<SysDept> getSubListDept(Map<String,Object> map);
    /**
     * 查找兄弟部门除自己
     */
    public List<SysDept> getBrotherDept(Map<String,Object> map);
    /**
     * 伪删除机构
     */
    public String deleteDept(int deptId,int userId);
    /**
     * 上移部门
     * @param deptId
     * @param userId
     * @return
     */
    public String upMovingDept(int deptId,int userId);
    /**
     * 下移部门
     * @param deptId
     * @param userId
     * @return
     */
    public String downMovingDept(int deptId,int userId);
    /**
     * 部门名称唯一性
     * @param map
     * @return  true 存在       false 不存在
     */
    public boolean validateDeptName(Map<String,Object>  map);
    /**
     * 部门编码唯一性
     * @param map
     * @return
     */
    public boolean validateDeptCode(Map<String,Object>  map);
    /**
     * 根据Code返回SysDept
     * @param map
     * @return
     */
    public SysDept getDeptByCode(Map<String,Object>  map);
    
    /**
     * 机构管理员   (不 添加默认根节点) json树 
     * @return
     */
    public JSONArray getDeptJsonRemoveRoot();
    /**
     * admin 树转换为json
     * @return
     */
    public JSONArray getAdminDeptJson();
    /**
     * 机构管理员 json树  添加默认根节点
     * @return
     */
    public JSONArray getDeptAdminDeptJson();
    /**
     * admin 机构管理员 机构list
     * @return
     */
    public List<SysDept> getUserJsonTree();
    
    /**
     * 根据名称和父节点Id获取机构对像
     * @param deptName
     * @param parentId
     * @return
     */
    public SysDept getDeptByName(String deptName,Integer parentId);
    
    /**
     * 查询admin所管理的机构树 List
     */
    public List<SysDept> getAdminInCharegeDeptTreeList();
    
    /**
     * 查询所有机构
     * @return
     */
	public List<SysDept> getAllDept();
}
