/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :用户信息业务接口
 * Author     :yangy
 * Create Date:2012-5-17
 */
package com.banger.mobile.facade.user;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.model.user.SysUser;
import com.banger.mobile.domain.model.user.SysUserBean;

/**
 * @author yangyong
 * @version $Id: SysUserService.java,v 0.1 2012-5-17 上午11:34:40 yangyong Exp $
 */
public interface SysUserService {
    /**
     * 添加用户信息
     * @param sysUser  用户对象
     * @param createUserId    创建用户
     * @param roleIds        角色集合
     * @param bussdept      业务主管管理机构集合
     * @param conroleids   可管理的角色
     * @return
     */
    public String addSysUser(SysUser sysUser, Integer createUserId,String roleIds,String bussdept,String conroleids);


    /**
     * 数据导入接口
     *
     * @param account  用户名
     * @param userName 用户姓名
     * @param deptCode 机构编号
     */
    public void initAddSysUser(String account, String userName, String deptCode, Integer createUserId);
    /**
     * 添加用户信息
     * @param sysUser
     * @param createUserId
     * @return
     */
    public void syncAddSysUser(SysUser sysUser, Integer createUserId);

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
     * 修改用户信息
     * @param sysUser 用户信息
     * @param roleIds   可管理角色
     * @param bussdept    可管理机构
     * @param userId     用户ID
     */
    public void updateSysUserAndRole(SysUser sysUser,String roleIds, String bussdept,Integer userId,String conroleids);

    /**
     * 根据id得到用户信息
     * @param id
     * @return
     */
    public SysUser getSysUserById(int id);

    /**
     * 用户信息分页
     * @param parameters
     * @param page
     * @return
     */
    public PageUtil<SysUserBean> getSysUserPage (Map<String, Object> parameters, Page page);

    /**
     * 根据用户名得到用户
     * @param account
     * @return
     */
    public SysUser getUserByAccount(String account);

    /**
     * 根据用户名得到用户
     * @param account
     * @return
     */
    public SysUser getAllUserByAccount(String account);

    public List<SysUser> getAllUserByAccountMap(Map<String,Object> map);

    public List<SysUser> getAllUserByAccountAndName(Map<String,Object> map);

    /**
     * 密码重置
     * @param userId
     * @param password
     */
    public void pwdConfirm(Integer userId, String password);

    /**
     * 根据机构ID得机构名
     * @param deptid
     * @return
     */
    public String getDeptName(Integer deptid);

    /**
     * 根据角色ID得角色名
     * @param roleid
     * @return
     */
    public String getRoleName(Integer roleid);

    /**
     * 根据用户ID得到角色字符串
     * @param userid
     * @return
     */
    public String getRoleIds(Integer userid);

    /**
     * 用户登陆
     * @param name
     * @param password
     * @return
     */
    public String login(String name, String password);
    
    /**
     * 检验当前用户名密码是否正确，在贷款陪调等业务中会使用到
     * 
     * @param name
     * @param password
     * @return
     */
    public SysUser checkNameAndPasswordIsValid(String name, String password);
    /**
     * IPAD用户登陆  
     * @param account
     * @param password
     * @param ip
     * @return
     */
    public String login(String account, String password, String ip);

    /**
     * IPAD用户退出  
     * @param account
     * @param ip
     */
    public void logout(String account, String ip);


    /**
     * 根据部门ID取下属用户集合
     * @param userIds
     * @return
     */
    public List<SysUserBean> getDeptBelongUserList(String userIds);

    /**
     * 根据部门ID取用户集合
     * @param map
     * @return
     */
    public List<SysUser> getDeptUserList(Map<String, Object> map);

    /**
     * 根据用户ID取平级及上级用户集合
     * @param map
     * @return
     */
    public List<SysUserBean> getSuperiorUserList(Map<String, Object> map);

    /**
     * 根据用户ID集合取出用户对应用户对象
     * @param ids
     * @return
     */
    public List<SysUserBean> getUserListByIds(String ids);

    /**
     * 判断用户编号和用户名是否存在
     * @param sqlId
     * @param sysUser
     * @return
     */
    public Integer validation(String sqlId, SysUser sysUser);

    /**
     * 电话来电时自动登录
     * @param account
     * @return
     */
    public String autoLogin(String account);

    /**
     * 查询机构下及在指定任务中的用户
     * @param map
     * @return
     */
    public List<SysUser> getDeptBelongUserTaskList(Map<String, Object> map);

    /**
     * 查询所有的用户数 
     * @return
     */
    public Integer getAllUserCount();

    /**
     * 判断用户是否是业务主管
     * @param userId
     * @return
     */
    public boolean getUserIsCompetent(Integer userId);

    /**
     * 根据用户集合查角色名
     * @param userIds
     * @return
     */
    public Map<Integer, String> getRoleNamesByUserId(String userIds);
    
    /**
     * 返回柜台人员，用户的树形数据
     * @param userId
     * @return
     */
    public List<SysUserBean> getSysUserAndCounterUser(Integer userId);

    /**
     * 根据用户集合查主管姓名
     * @param userIds
     * @return
     */
    public Map<Integer, String> getLeadNamesByUserId(String userIds);

    /**
     * 查询所有的用户数据
     * @return
     */
    public List<SysUser> getAllData();

    /**
     * 根据信贷操作号查用户
     * @param operateCode
     * @return
     */
    public SysUser getSysUserByOperateCode(String operateCode);

    List<SysUser> getAllManagerUser();

    public Map<String,Map<String,String>> getUserJGINFO(String account);

    
    public Integer getRoleByUserId(Integer userid);

    
    /**
     * 根据用户编号，查询该用户所在的团队的主管用户
     * 当用户编号为空时，查询所有团队主管用户
     * @param userId
     * @return
     */
    public List<SysUser> getSysUserTeamChiefByUserId(Integer userId);


	/**
	 * 根据用户编号,查询该用户所在底下所有的经理
	 * 
	 * @param userId
	 * @return
	 */
	public List<SysUser> getUserListBelongToSysTeamByUserId(Integer userId);


	/**
	 * 根据用户编号,查询当前团队中当前审批量最少的用户
	 * @param userId
	 * @return
	 */
	public Integer getReadomTeamManagerUserId(Integer userId,String Type);

    /**
     * 获取后台人员列表
     * @param userId
     * @return
     */
    public List<Integer> getReadomTeamManager(Integer userId);
	
	/**
	 * 随机返回审批人员
	 * 
	 * @return
	 */
	//public List<Integer> getReadomApproveUserId(Boolean isDoubleApproval);
	public List<Integer> getReadomApproveUserId(Boolean isDoubleApproval,String approvalValue);

	/**
	 * 根据用户id和角色id得到客户经理
	 * @param param
	 * @return 
	 */
	public List<SysUser> getManagerByUserIdAndRoleId(Map<String, Object> param);
	
	/**
	 * 
	 * @param sql
	 * @return
	 * @throws SQLException 
	 */
	public  Map<Integer,String> getColumnNames(String sql) throws SQLException;
	
	/**
	 * 
	 * @param sql
	 * @return
	 * @throws SQLException 
	 */
	@SuppressWarnings("rawtypes")
	public  List<Map> getResultData(String sql) throws SQLException;
	
	/**
	 * 
	 * @param sqlString
	 * @return
	 * @throws SQLException 
	 */
	public int[]  excute(String sqlString) throws SQLException;
}
