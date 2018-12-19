/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :角色实体数据实现
 * Author     :liyb
 * Create Date:2012-5-22
 */
package com.banger.mobile.dao.role.ibatis;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.dao.role.SysRoleDao;
import com.banger.mobile.domain.model.role.SysRole;
import com.banger.mobile.domain.model.user.SysUser;
import com.banger.mobile.ibatis.GenericDaoiBatis;

/**
 * @author liyb
 * @version $Id: SysRoleDaoiBatis.java,v 0.1 2012-5-22 下午01:24:38 liyb Exp $
 */
public class SysRoleDaoiBatis extends GenericDaoiBatis implements SysRoleDao {

    public SysRoleDaoiBatis(){
        super(SysRole.class);
    }

    /**
     * 角色分页列表
     * @param parameters 查询条件Map集合
     * @param page
     * @return
     */
    public PageUtil<SysRole> getSysRolePage(Map<String, Object> parameters, Page page) {
        ArrayList<SysRole> list = (ArrayList<SysRole>) this.findQueryPage(
                "getSysRolePageMap", "getSysRoleCount", parameters, page);
        if (list == null) {
            list = new ArrayList<SysRole>();
        }
        return new PageUtil<SysRole>(list, page);
    }

    /**
     * 返回所有的角色
     * @return
     */
    public List<SysRole> getAllRoleName() {
        return this.getSqlMapClientTemplate().queryForList("GetAllRoleName");
    }

    /**
     * 根据条件返回角色信息
     * @param sysRole
     * @return
     */
    public SysRole getSysRoleById(SysRole sysRole) {
        return (SysRole) this.getSqlMapClientTemplate().queryForObject("GetSysRoleById", sysRole);
    }

    /**
     * 添加角色
     * @param sysRole
     */
    public void insertSysRole(SysRole sysRole) {
        this.getSqlMapClientTemplate().insert("InsertSysRole",sysRole);
    }

    /**
     * 修改角色
     * @param sysRole
     */
    public void updateSysRole(SysRole sysRole) {
        this.getSqlMapClientTemplate().update("UpdateSysRole", sysRole);
    }

    /**
     * 根据角色ID返回已使用的总数
     * @param roleId
     * @return
     */
    public Integer getIsUseRoleCount(Integer roleId) {
        return (Integer) this.getSqlMapClientTemplate().queryForObject("GetIsUseRoleCount",roleId);
    }

    /**
     * @param userId
     * @return
     * @see com.banger.mobile.dao.role.SysRoleDao#getSuperiorUserList(java.lang.String)
     */
    public List<SysUser> getSuperiorUserList(String userId) {
        return this.getSqlMapClientTemplate().queryForList("getSuperiorUserList");
    }

    /**
     * 获取所有需要录入个人日志的角色
     * @return
     */
    @Override
    public List<SysRole> getTeamLogRole() {
        return this.getSqlMapClientTemplate().queryForList("getTeamLogRole");
    }
}
