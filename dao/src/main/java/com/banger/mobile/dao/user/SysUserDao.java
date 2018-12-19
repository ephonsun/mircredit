/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :用户记录实体数据接口
 * Author     :yangy
 * Create Date:2012-5-17
 */
package com.banger.mobile.dao.user;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.model.user.SysTalkUserBean;
import com.banger.mobile.domain.model.user.SysUser;
import com.banger.mobile.domain.model.user.SysUserBean;
import com.banger.mobile.domain.model.user.UserRoleName;

/**
 * @author yangy
 * @version $Id: SysUserDao.java,v 0.1 2012-5-17 上午11:52:56 Administrator Exp $
 */
public interface SysUserDao {
	/**
	 *  添加一条用户信息
	 * @param sysUser
	 */
    public void addSysUser(SysUser sysUser);

    /**
     * 删除用户信息
     * @param id
     */
    public void deleteSysUser(int id);

    /**
     * 修改用户信息
     * @param sysUser
     */
    public void updateSysUser(SysUser sysUser);

    /**
     * 根据id得到用户信息
     * @param id
     * @return
     */
    public SysUser getSysUserById(int id);

    /**
     * 记录总计
     * @param str
     * @param parameters
     * @return
     */
    public Integer getQueryCount(String str, Map<String, Object> parameters);

    /**
     * 用户信息分页
     * @param parameters
     * @param page
     * @return
     */
    public PageUtil<SysUserBean> getSysUserPage(Map<String, Object> parameters, Page page);

    /**
     * 用户信息集合
     * @param str
     * @param parameters
     * @return
     */
    public List<SysUser> getSysUserList(String str, Map<String, Object> parameters);

    /**
     * 用户信息集合
     * @param str
     * @param parameters
     * @return
     */
    public List<SysUser> getSysUserList(String str, String parameters);

    /**
     * 根据部门ID取下属用户集合
     * @param str
     * @param userIds
     * @return
     */
    public List<SysUserBean> getDeptBelongUserList(String str, String userIds);

    /**
     * 查询所在机构用户数据
     * @param deptId
     * @return
     */
    public List<SysUser> getOnDeptData(int deptId);

    /**
     * 根据用户名取用户
     * @param account
     * @return
     */
    public SysUser getUserByAccount(String account);

    /**
     * 根据用户名取用户
     * @param account
     * @return
     */
    public SysUser getAllUserByAccount(String account);

    public  List<SysUser> getAllUserByAccountMap(Map<String,Object> map);

    public  List<SysUser> getAllUserByAccountAndName(Map<String,Object> map);

    /**
     * 查询下属机构的用户数据
     * @param map
     * @return
     */
    public List<SysUser> getInDeptData(Map<String, Object> map);

    /**
     * 查询本机构及下属机构的用户数据 
     * @param deptSearchCode
     * @return
     */
    public List<SysUser> getContainDeptData(String deptSearchCode);

    /**
     * 查询所有的用户数据
     * @return
     */
    public List<SysUser> getAllData();

    /**
     * 查询所在机构用户数据
     * @param deptId
     * @param flag
     * @return
     */
    public List<SysUser> getOnDeptData(int deptId, boolean flag);

    /**
     * 查询下属机构的用户数据
     * @param map
     * @param flag
     * @return
     */
    public List<SysUser> getInDeptData(Map<String, Object> map, boolean flag);

    /**
     * 查询本机构及下属机构的用户数据 
     * @param deptSearchCode
     * @param flag
     * @return
     */
    public List<SysUser> getContainDeptData(String deptSearchCode, boolean flag);

    /**
     * 查询所有的用户数据 
     * @param flag
     * @return
     */
    public List<SysUser> getAllData(boolean flag);
    
    /**
     * 查询所有的用户数据 
     * @return
     */
    public List<SysUser> getAllUser();

    /**
     * 查询所管理机构下的用户 不包含伪删除和admin
     * @param map
     * @return
     */
    public List<SysUser> getInChargeDeptUsers(Map<String, Object> map);

    /**
     * 根据用户ID取平级及上级用户集合
     * @param map
     * @return
     */
    public List<SysUserBean> getSuperiorUserList(Map<String, Object> map);

    /**
     * 查询机构下及在指定任务中的用户
     * @param map
     * @return
     */
    public List<SysUser> getDeptBelongUserTaskList(Map<String, Object> map);

    /**
     * 根据部门ID取用户集合
     * @param map
     * @return
     */
    public List<SysUser> getDeptUserList(Map<String, Object> map);

    /**
     * 得到来电转接用户
     * @param condition
     * @param page
     * @return
     */
    public PageUtil<SysTalkUserBean> getTalkForwardUsers(Map<String, Object> condition, Page page);

    /**
     * 根据用户ID集合取出用户对应用户对象
     * @param ids
     * @return
     */
    public List<SysUserBean> getUserListByIds(String ids);
    /**
     * 缓存所有的客户经理和业务主管 
     * @return
     */
    public List<SysUser> getAllUserForCache();
    /**
     * 根据userIds字符串查询角色名称
     * @param userIds
     * @return
     */
    public List<UserRoleName> getRoleNamesByUserIds(String userIds);
    /**
     * 查询系统中客户经理 userId
     */
    public List<Integer> getCommonUserIdList();
    
    /**
     * 返回柜台人员，用户的树形数据
     * @param userId
     * @return
     */
    public List<SysUserBean> getSysUserAndCounterUser(Map<String, Object> condition);

    /**
     * 根据用户集合查主管姓名
     * @param userIds
     * @return
     */
    public List<SysUserBean> getLeadNamesByUserId(String userIds);

    /**
     * 根据信贷操作号查用户
     * @param operateCode
     * @return
     */
    public SysUser getSysUserByOperateCode(String operateCode);

    public List<SysUser> getAllManagerUser();

    public Map<String,Map<String,String>>  getUserJGINFO(String account);

	/**
	 * 根据用户编号，查询该用户所在的团队的主管用户
     * 当用户编号为空时，查询所有团队主管用户
	 * @param userId
	 * @return
	 */
	public List<SysUser> getSysUserTeamCheifByUserId(Integer userId);

	/**
	 * 
	 * @param userId
	 * @return
	 */
	public List<SysUser> getUserListBelongToSysTeamByUserId(Integer userId);

	/**
	 * 根据团队任意成员编号，查询该团队中后台人员用户编号列表
	 * @param userId
	 * @return
	 */
	public List<Integer> getManagerSysUserUserIdListByTeamMemberUserId(
			Integer userId);
	
	public Integer getUserLeaveTag(Integer userId);
	
	public Double getApprovalValue(Integer userId);
	
	/**
	 * 根据用户id和角色id得到客户经理
	 * @param param
	 * @return
	 */
	
	public List<SysUser> getManagerByUserIdAndRoleId(Map<String, Object> param);

	public Map<Integer, String> getColumnNames(String sql) throws SQLException;

	@SuppressWarnings("rawtypes")
	public List<Map> getResultData(String sql) throws SQLException;

	public int[] excute(String sqlString) throws SQLException;
}
