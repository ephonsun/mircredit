/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :角色信息接口
 * Author     :liyb
 * Create Date:2012-5-22
 */
package com.banger.mobile.facade.role;

import java.util.List;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.model.role.SysRole;

/**
 * @author liyb
 * @version $Id: SysRoleService.java,v 0.1 2012-5-22 下午01:35:04 liyb Exp $
 */
public interface SysRoleService {
    /**
     * 角色分页列表
     * @param parameters 查询条件Map集合
     * @param page
     * @return
     */
    public PageUtil<SysRole> getSysRolePage(SysRole sysRole, Page page);
    
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

    public List<SysRole> getTeamLogRole();
}
