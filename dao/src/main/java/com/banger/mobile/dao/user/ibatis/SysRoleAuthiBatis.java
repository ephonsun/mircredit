/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :角色权限dao
 * Author     :yangy
 * Create Date:2012-8-14
 */
package com.banger.mobile.dao.user.ibatis;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.dao.user.ObjectDao;
import com.banger.mobile.domain.model.user.SysRoleAuth;
import com.banger.mobile.ibatis.GenericDaoiBatis;

/**
 * @author yangyang
 * @version $Id: SysRoleAuthiBatis.java,v 0.1 2012-8-14 上午11:11:44 yangyong Exp $
 */
public class SysRoleAuthiBatis extends GenericDaoiBatis implements ObjectDao {

    @SuppressWarnings("unchecked")
    public SysRoleAuthiBatis(Class persistentClass) {
        super(SysRoleAuth.class);
    }

    @SuppressWarnings("unchecked")
    public SysRoleAuthiBatis() {
        super(SysRoleAuth.class);
    }
    /**
     * @param obj
     * @see com.banger.mobile.dao.user.ObjectDao#addObject(java.lang.Object)
     */
    public void addObject(Object obj) {
        this.getSqlMapClientTemplate().insert("addSysRoleAuth",(SysRoleAuth)obj);
    }

    /**
     * @param id
     * @see com.banger.mobile.dao.user.ObjectDao#deleteObject(int)
     */
    public void deleteObject(int id) {
        this.getSqlMapClientTemplate().delete("deleteSysRoleAuth",id);
    }

    /**
     * @param obj
     * @see com.banger.mobile.dao.user.ObjectDao#updateObject(java.lang.Object)
     */
    public void updateObject(Object obj) {
        this.getSqlMapClientTemplate().update("updateSysRoleAuth",(SysRoleAuth)obj);
    }

    /**
     * @param id
     * @return
     * @see com.banger.mobile.dao.user.ObjectDao#getObjectById(int)
     */
    public Object getObjectById(int id) {
        return (Object) this.getSqlMapClientTemplate().queryForObject("getSysRoleAuthById", id);
    }

    /**
     * @param parameters
     * @param page
     * @return
     * @see com.banger.mobile.dao.user.ObjectDao#getObjectPage(java.util.Map, com.banger.mobile.Page)
     */
    public PageUtil<Object> getObjectPage(Map<String, Object> parameters, Page page) {
        @SuppressWarnings("unchecked")
        List<Object> list = (List<Object>) this.findQueryPage(
                "getSysRoleAuthPageMap", "getSysRoleAuthCount", parameters, page);
        if (list == null) {
            list = new ArrayList<Object>();
        }
        return new PageUtil<Object>(list, page);
    }

    /**
     * @param parameters
     * @return
     * @see com.banger.mobile.dao.user.ObjectDao#getObjectList(java.util.Map)
     */
    @SuppressWarnings("unchecked")
    public List<Object> getObjectList(Map<String, Object> parameters) {
        return (List<Object>) this.getSqlMapClientTemplate().queryForList("getSysRoleAuthList",parameters);
    }

    /**
     * @param parameters
     * @see com.banger.mobile.dao.user.ObjectDao#deleteObjectMap(java.util.Map)
     */
    public void deleteObjectMap(Map<String, Object> parameters) {
    }

    /**
     * @return
     * @see com.banger.mobile.dao.user.ObjectDao#getObjectList()
     */
    public List<Object> getObjectList() {
        return null;
    }
}
