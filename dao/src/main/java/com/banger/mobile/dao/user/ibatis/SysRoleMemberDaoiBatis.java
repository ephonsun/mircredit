/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :用户角色信息
 * Author     :yangy
 * Create Date:2012-5-21
 */
package com.banger.mobile.dao.user.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.dao.user.SysRoleMemberDao;
import com.banger.mobile.domain.model.user.SysRoleMember;
import com.banger.mobile.domain.model.user.SysUser;
import com.banger.mobile.ibatis.GenericDaoiBatis;

/**
 * @author Administrator
 * @version $Id: SysRoleMemberDaoiBatis.java,v 0.1 2012-5-21 下午2:24:46 Administrator Exp $
 */
public class SysRoleMemberDaoiBatis extends GenericDaoiBatis implements SysRoleMemberDao {

    public SysRoleMemberDaoiBatis(Class persistentClass) {
        super(SysRoleMember.class);
    }
    
    public SysRoleMemberDaoiBatis() {
        super(SysRoleMember.class);
    }

    /**
     * @param sysRoleMember
     * @see com.banger.mobile.dao.user.SysRoleMemberDao#addSysRoleMember(com.banger.mobile.domain.model.user.SysRoleMember)
     */
    public void addSysRoleMember(SysRoleMember sysRoleMember) {
        this.getSqlMapClientTemplate().insert("addSysRoleMember",sysRoleMember);
    }

    /**
     * 彻底删除用户角色信息
     * @param id
     * @see com.banger.mobile.dao.user.SysRoleMemberDao#deleteSysRoleMember(int)
     */
    public void deleteSysRoleMember(int id) {
        this.getSqlMapClientTemplate().delete("deleteSysRoleMember",id);
    }

    /**
     * @param sysRoleMember
     * @see com.banger.mobile.dao.user.SysRoleMemberDao#updateSysRoleMember(com.banger.mobile.domain.model.user.SysRoleMember)
     */
    public void updateSysRoleMember(SysRoleMember sysRoleMember) {
        this.getSqlMapClientTemplate().update("updateSysRoleMember",sysRoleMember);
    }

    /**
     * @param id
     * @return
     * @see com.banger.mobile.dao.user.SysRoleMemberDao#getSysRoleMemberById(int)
     */
    public SysRoleMember getSysRoleMemberById(int id) {
        return (SysRoleMember) this.getSqlMapClientTemplate().queryForObject("getSysRoleMemberById", id);
    }

    /**
     * @param str
     * @param codition
     * @return
     * @see com.banger.mobile.dao.user.SysRoleMemberDao#getQueryCount(java.lang.String, java.lang.Object)
     */
    public Integer getQueryCount(String str, Object codition) {
        return this.getQueryCount(str, codition);
    }

    /**
     * @param parameters
     * @param page
     * @return
     * @see com.banger.mobile.dao.user.SysRoleMemberDao#getSysRoleMemberPage(java.util.Map, com.banger.mobile.Page)
     */
    public PageUtil<SysRoleMember> getSysRoleMemberPage(Map<String, Object> parameters, Page page) {
        return null;
    }

    /**
     * @param str
     * @param parameters
     * @return
     * @see com.banger.mobile.dao.user.SysRoleMemberDao#getSysRoleMemberList(java.lang.String, java.util.Map)
     */
    public List<SysRoleMember> getSysRoleMemberList(String str, Map<String, Object> parameters) {
        return this.getSqlMapClientTemplate().queryForList(str, parameters);
    }

    /**
     * @param parameters
     * @return
     * @see com.banger.mobile.dao.user.SysRoleMemberDao#getAdminAndDepartmentList(java.util.Map)
     */
    public List<SysRoleMember> getAdminAndDepartmentList() {
         return this.getSqlMapClientTemplate().queryForList("getAdminAndDepartmentList");
    }


    /**
     * 彻底删除用户角色信息
     * @param userId
     * @see com.banger.mobile.dao.user.SysRoleMemberDao#delSysRoleMember(java.lang.Integer)
     */
    public void delSysRoleMember(Integer userId) {
        this.getSqlMapClientTemplate().delete("delSysRoleMember",userId);
    }

	@Override
	public List<Integer> getRoleIdByUserIds(String userIds) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userIds", userIds);
		
		return this.getSqlMapClientTemplate().queryForList("getRoleIdByUserIds", map);
	}
    @Override
    public Integer getRoleIdByUserId(Integer userId) {
        return (Integer)this.getSqlMapClientTemplate().queryForObject("getRoleIdByUserId", userId);
    }
	@Override
	public List<Integer> getUserIdByRole(String roleId,String approvalValue) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("roleId", roleId);
		map.put("approvalValue", approvalValue);
		return this.getSqlMapClientTemplate().queryForList("getUserIdByRole", map);
	}
	
	
}
