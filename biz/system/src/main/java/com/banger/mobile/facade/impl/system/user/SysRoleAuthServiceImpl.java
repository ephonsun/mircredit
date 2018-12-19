/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :角色权限管理...
 * Author     :yangy
 * Create Date:2012-8-15
 */
package com.banger.mobile.facade.impl.system.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.dao.user.ObjectDao;
import com.banger.mobile.domain.model.user.SysRoleAuth;
import com.banger.mobile.facade.user.SysRoleAuthService;

/**
 * @author yangyang
 * @version $Id: SysRoleAuthServiceImpl.java,v 0.1 2012-8-15 下午3:51:59 yangyong Exp $
 */
public class SysRoleAuthServiceImpl implements SysRoleAuthService {

    private ObjectDao sysRoleAuthDao;
    
    public void setSysRoleAuthDao(ObjectDao sysRoleAuthDao) {
        this.sysRoleAuthDao = sysRoleAuthDao;
    }

    /**
     * 添加一条信息
     * @param sysRoleAuth
     * @see com.banger.mobile.facade.user.SysRoleAuthService#addSysRoleAuth(com.banger.mobile.domain.model.user.SysRoleAuth)
     */
    public void addSysRoleAuth(SysRoleAuth sysRoleAuth) {
        sysRoleAuthDao.addObject(sysRoleAuth);
    }

    /**
     * 删除信息
     * @param id
     * @see com.banger.mobile.facade.user.SysRoleAuthService#deleteSysRoleAuth(int)
     */
    public void deleteSysRoleAuth(int id) {
        sysRoleAuthDao.deleteObject(id);
    }

    /**
     * 修改信息
     * @param sysRoleAuth
     * @see com.banger.mobile.facade.user.SysRoleAuthService#updateSysRoleAuth(com.banger.mobile.domain.model.user.SysRoleAuth)
     */
    public void updateSysRoleAuth(SysRoleAuth sysRoleAuth) {
        sysRoleAuthDao.updateObject(sysRoleAuth);
    }

    /**
     * 根据id得到信息
     * @param id
     * @return
     * @see com.banger.mobile.facade.user.SysRoleAuthService#getSysRoleAuthById(int)
     */
    public SysRoleAuth getSysRoleAuthById(int id) {
        return (SysRoleAuth)sysRoleAuthDao.getObjectById(id);
    }

    /**
     * 分页查询
     * @param parameters
     * @param page
     * @return
     * @see com.banger.mobile.facade.user.SysRoleAuthService#getSysRoleAuthPage(java.util.Map, com.banger.mobile.Page)
     */
    public PageUtil<SysRoleAuth> getSysRoleAuthPage(Map<String, Object> parameters, Page page) {
        return null;
    }

    /**
     * 查询所有模板集合
     * @param parameters
     * @return
     * @see com.banger.mobile.facade.user.SysRoleAuthService#getSysRoleAuthList(java.util.Map)
     */
    public List<SysRoleAuth> getSysRoleAuthList(Map<String, Object> parameters) {
        List<SysRoleAuth> suo=new ArrayList<SysRoleAuth>();
        List<Object> obj=sysRoleAuthDao.getObjectList(parameters);
        for (Object object : obj) {
            suo.add((SysRoleAuth)object);
        }
        return suo;
    }

}
