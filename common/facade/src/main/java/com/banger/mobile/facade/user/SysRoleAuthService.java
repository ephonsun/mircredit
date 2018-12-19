/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :角色权限...
 * Author     :yangy
 * Create Date:2012-8-15
 */
package com.banger.mobile.facade.user;

import java.util.List;
import java.util.Map;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.model.user.SysRoleAuth;

/**
 * @author yangyang
 * @version $Id: SysRoleAuthService.java,v 0.1 2012-8-15 下午3:50:23 yangyong Exp $
 */
public interface SysRoleAuthService {


    /**
     * 添加一条信息
     * @param recordInfo
     */
    public void addSysRoleAuth(SysRoleAuth sysRoleAuth);

    /**
     * 删除信息
     * @param id
     */
    public void deleteSysRoleAuth(int id);
    
    /**
     * 修改信息
     * @param recordInfo
     */
    public void updateSysRoleAuth(SysRoleAuth sysRoleAuth);

    /**
     * 根据id得到信息
     * @param id
     * @return
     */
    public SysRoleAuth getSysRoleAuthById(int id);
    
    
    /**
     * 分页查询
     * @param parameters
     * @param page
     * @return
     */
    public PageUtil<SysRoleAuth> getSysRoleAuthPage(Map<String, Object> parameters, Page page);
    
    /**
     * 查询所有模板集合
     * @param parameters
     * @param page
     * @return
     */
    public List<SysRoleAuth> getSysRoleAuthList(Map<String, Object> parameters);
}
