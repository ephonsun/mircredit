/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :角色实体数据接口
 * Author     :liyb
 * Create Date:2012-5-22
 */
package com.banger.mobile.dao.role;

import java.util.List;
import java.util.Map;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.model.role.SysRole;
import com.banger.mobile.domain.model.user.SysUser;

/**
 * @author liyb
 * @version $Id: SysRoleDao.java,v 0.1 2012-5-22 下午01:21:19 liyb Exp $
 */
public interface SysRoleDao {
    /**
     * 角色分页列表
     * @param parameters 查询条件Map集合
     * @param page
     * @return
     */
    public PageUtil<SysRole> getSysRolePage(Map<String, Object> parameters, Page page);
    
    /**
     * 返回所有的角色
     * @return
     */
    public List<SysRole> getAllRoleName();
    
    /**
     * 根据条件返回角色信息
     * @param sysRole
     * @return
     */
    public SysRole getSysRoleById(SysRole sysRole);
    
    /**
     * 添加角色
     * @param sysRole
     */
    public void insertSysRole(SysRole sysRole);
    
    /**
     * 修改角色
     * @param sysRole
     */
    public void updateSysRole(SysRole sysRole);
    
    /**
     * 根据角色ID返回已使用的总数
     * @param roleId
     * @return
     */
    public Integer getIsUseRoleCount(Integer roleId);
    
    /**
     * 根据用户ID取平级及上级用户集合
     * @return
     */
    public List<SysUser> getSuperiorUserList(String userId);

    public List<SysRole> getTeamLogRole();
    
}
