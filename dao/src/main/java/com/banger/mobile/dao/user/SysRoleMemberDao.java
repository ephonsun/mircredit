/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :用户角色信息
 * Author     :yangy
 * Create Date:2012-5-21
 */
package com.banger.mobile.dao.user;

import java.util.List;
import java.util.Map;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.model.user.SysRoleMember;
import com.banger.mobile.domain.model.user.SysUser;

/**
 * @author Administrator
 * @version $Id: SysRoleMemberDao.java,v 0.1 2012-5-21 下午2:20:52 Administrator Exp $
 */
public interface SysRoleMemberDao {

    /**
     * 添加一条用户角色信息
     * @param recordInfo
     */
    public void addSysRoleMember(SysRoleMember sysRoleMember);

    /**
     * 删除用户角色信息
     * @param id
     */
    public void deleteSysRoleMember(int id);
    
    /**
     * 修改用户角色信息
     * @param recordInfo
     */
    public void updateSysRoleMember(SysRoleMember sysRoleMember);

    /**
     * 根据id得到用户角色
     * @param id
     * @return
     */
    public SysRoleMember getSysRoleMemberById(int id);

    /**
     * 记录总计
     * @param str
     * @param codition
     * @return
     */
    public Integer getQueryCount(String str, Object codition);
    
    /**
     * 用户角色信息分页
     * @param parameters
     * @param currentPage
     * @param pageSize
     * @return
     */
    public PageUtil<SysRoleMember> getSysRoleMemberPage(Map<String, Object> parameters, Page page);

    /**
     * 用户角色编号集合
     * @param parameters
     * @param currentPage
     * @param pageSize
     * @return
     */
    public List<SysRoleMember> getSysRoleMemberList(String str,Map<String, Object> parameters);
    
    /**
     * 获取机构管理员，admin角色编号集合
     * @param parameters
     * @param currentPage
     * @param pageSize
     * @return
     */
    public List<SysRoleMember> getAdminAndDepartmentList();
    
    /**
     * 彻底删除用户角色信息
     * @param recordInfo
     */
    public void delSysRoleMember(Integer userId);
	/**
	 * 根据用户编号 查询角色编号列表
	 * @param userIds
	 * @return
	 */
    public List<Integer> getRoleIdByUserIds(String userIds);

    /**
     * 根据用户编号查询角色编号
     * @param userId 用户id
     * @return
     */
    public Integer getRoleIdByUserId(Integer userId);

	/**
	 * 根据角色编号，返回用户编号列表
	 * @param roleId
	 * @return
	 */
	//public List<Integer> getUserIdByRole(String roleId);
	public List<Integer> getUserIdByRole(String roleId,String approvalValue);
}
