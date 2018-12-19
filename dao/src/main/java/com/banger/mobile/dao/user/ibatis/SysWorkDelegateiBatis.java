/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :工作托管
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
import com.banger.mobile.domain.model.user.SysWorkDelegate;
import com.banger.mobile.ibatis.GenericDaoiBatis;

/**
 * @author yangyang
 * @version $Id: SysWorkDelegateiBatis.java,v 0.1 2012-8-14 上午10:51:00 yangyong Exp $
 */
public class SysWorkDelegateiBatis extends GenericDaoiBatis implements ObjectDao {

    @SuppressWarnings("unchecked")
    public SysWorkDelegateiBatis(Class persistentClass) {
        super(SysWorkDelegate.class);
    }

    @SuppressWarnings("unchecked")
    public SysWorkDelegateiBatis() {
        super(SysWorkDelegate.class);
    }
    /**
     * @param obj
     * @see com.banger.mobile.dao.user.ObjectDao#addObject(java.lang.Object)
     */
    public void addObject(Object obj) {
        this.getSqlMapClientTemplate().insert("addSysWorkDelegate",(SysWorkDelegate)obj);
    }

    /**
     * @param id
     * @see com.banger.mobile.dao.user.ObjectDao#deleteObject(int)
     */
    public void deleteObject(int id) {
    }

    /**
     * @param obj
     * @see com.banger.mobile.dao.user.ObjectDao#updateObject(java.lang.Object)
     */
    public void updateObject(Object obj) {
        this.getSqlMapClientTemplate().update("updateSysWorkDelegate",(SysWorkDelegate)obj);
    }

    /**
     * @param id
     * @return
     * @see com.banger.mobile.dao.user.ObjectDao#getObjectById(int)
     */
    public Object getObjectById(int id) {
        return (Object) this.getSqlMapClientTemplate().queryForObject("getSysWorkDelegateById", id);
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
                "getSysWorkDelegatePageMap", "getSysWorkDelegateCount", parameters, page);
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
        return (List<Object>) this.getSqlMapClientTemplate().queryForList("getSysWorkDelegateList",parameters);
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
