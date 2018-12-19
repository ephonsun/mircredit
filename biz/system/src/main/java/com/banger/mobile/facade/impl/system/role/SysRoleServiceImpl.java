/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :角色业务实现类
 * Author     :liyb
 * Create Date:2012-5-22
 */
package com.banger.mobile.facade.impl.system.role;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringEscapeUtils;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.dao.role.SysRoleDao;
import com.banger.mobile.domain.model.role.SysRole;
import com.banger.mobile.facade.role.SysRoleService;
import com.banger.mobile.util.StringUtil;

/**
 * @author liyb
 * @version $Id: SysRoleServiceImpl.java,v 0.1 2012-5-22 下午01:36:12 liyb Exp $
 */
public class SysRoleServiceImpl implements SysRoleService {
    
    private SysRoleDao sysRoleDao;

    public void setSysRoleDao(SysRoleDao sysRoleDao) {
        this.sysRoleDao = sysRoleDao;
    }

    /**
     * 角色分页列表
     * @param parameters 查询条件Map集合
     * @param page
     * @return
     */
    public PageUtil<SysRole> getSysRolePage(SysRole sysRole, Page page) {
        Map<String,Object> map=new HashMap<String, Object>();
        if(sysRole!=null){
            if(sysRole.getRoleName()!=null&&!sysRole.getRoleName().equals("")){
                map.put("roleName", StringUtil.ReplaceSQLChar(sysRole.getRoleName()));
            }
        }
        return sysRoleDao.getSysRolePage(map, page);
    }

    /**
     * 返回所有的角色
     * @return
     */
    public List<SysRole> getAllRoleName() {
        return sysRoleDao.getAllRoleName();
    }

    /**
     * 根据条件返回角色信息
     * @param sysRole
     * @return
     */
    public SysRole getSysRoleById(SysRole sysRole) {
        return sysRoleDao.getSysRoleById(sysRole);
    }

    /**
     * 添加角色
     * @param sysRole
     */
    public void insertSysRole(SysRole sysRole) {
        sysRole.setCreateDate(new Date());
        sysRole.setIsDel(0);
        sysRole.setRoleTypeId(2);
        //祛除前后空格
        sysRole.setRoleName(sysRole.getRoleName().trim());
        sysRole.setRemark(sysRole.getRemark().trim());
        sysRoleDao.insertSysRole(sysRole);
    }

    /**
     * 修改角色
     * @param sysRole
     */
    public void updateSysRole(SysRole sysRole) {
        sysRole.setUpdateDate(new Date());
        //祛除前后空格
        sysRole.setRoleName(sysRole.getRoleName().trim());
        sysRole.setRemark(sysRole.getRemark().trim());
        sysRoleDao.updateSysRole(sysRole);
    }

    /**
     * 根据角色ID返回已使用的总数
     * @param roleId
     * @return
     */
    public Integer getIsUseRoleCount(Integer roleId) {
        return sysRoleDao.getIsUseRoleCount(roleId);
    }

    /**
     * 获取所有需要录入个人日志角色
     * @return
     */
    @Override
    public List<SysRole> getTeamLogRole() {
        return sysRoleDao.getTeamLogRole();
    }
}
