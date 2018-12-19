/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :机构权限...
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
import com.banger.mobile.domain.model.user.SysDeptAuth;
import com.banger.mobile.domain.model.user.SysRoleAuth;
import com.banger.mobile.facade.user.SysDeptAuthService;

/**
 * @author yangyang
 * @version $Id: SysDeptAuthServiceImpl.java,v 0.1 2012-8-15 下午4:19:15 yangyong Exp $
 */
public class SysDeptAuthServiceImpl implements SysDeptAuthService {

    private ObjectDao sysDeptAuthDao;
    
    public void setSysDeptAuthDao(ObjectDao sysDeptAuthDao) {
        this.sysDeptAuthDao = sysDeptAuthDao;
    }

    /**
     * 添加一条信息
     * @param sysDeptAuth
     * @see com.banger.mobile.facade.user.SysDeptAuthService#addSysDeptAuth(com.banger.mobile.domain.model.user.SysDeptAuth)
     */
    public void addSysDeptAuth(SysDeptAuth sysDeptAuth) {
        sysDeptAuthDao.addObject(sysDeptAuth);
    }


    /**
     * 修改信息
     * @param sysDeptAuth
     * @see com.banger.mobile.facade.user.SysDeptAuthService#updateSysDeptAuth(com.banger.mobile.domain.model.user.SysDeptAuth)
     */
    public void updateSysDeptAuth(SysDeptAuth sysDeptAuth) {
        sysDeptAuthDao.updateObject(sysDeptAuth);
    }

    /**
     * 根据id得到信息
     * @param id
     * @return
     * @see com.banger.mobile.facade.user.SysDeptAuthService#getSysDeptAuthById(int)
     */
    public SysDeptAuth getSysDeptAuthById(int id) {
        return (SysDeptAuth)sysDeptAuthDao.getObjectById(id);
    }

    /**
     * 分页查询
     * @param parameters
     * @param page
     * @return
     * @see com.banger.mobile.facade.user.SysDeptAuthService#getSysDeptAuthPage(java.util.Map, com.banger.mobile.Page)
     */
    public PageUtil<SysDeptAuth> getSysDeptAuthPage(Map<String, Object> parameters, Page page) {
        return null;
    }

    /**
     * 查询所有模板集合
     * @param parameters
     * @return
     * @see com.banger.mobile.facade.user.SysDeptAuthService#getSysDeptAuthList(java.util.Map)
     */
    public List<SysDeptAuth> getSysDeptAuthList(Map<String, Object> parameters) {
        List<SysDeptAuth> suo=new ArrayList<SysDeptAuth>();
        List<Object> obj=sysDeptAuthDao.getObjectList(parameters);
        for (Object object : obj) {
            suo.add((SysDeptAuth)object);
        }
        return suo;
    }

    /**
     * 删除信息
     * @param parameters
     * @see com.banger.mobile.facade.user.SysDeptAuthService#deleteSysDeptAuth(java.util.Map)
     */
    public void deleteSysDeptAuth(Map<String, Object> parameters) {
        sysDeptAuthDao.deleteObjectMap(parameters);
    }

}
