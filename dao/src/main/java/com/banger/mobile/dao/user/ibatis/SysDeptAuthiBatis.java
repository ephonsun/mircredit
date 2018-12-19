/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :机构权限
 * Author     :yangy
 * Create Date:2012-8-14
 */
package com.banger.mobile.dao.user.ibatis;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.dao.user.SysDeptAuthDao;
import com.banger.mobile.domain.model.user.SysDeptAuth;
import com.banger.mobile.ibatis.GenericDaoiBatis;

/**
 * @author yangyang
 * @version $Id: SysDeptAuthiBatis.java,v 0.1 2012-8-14 上午11:09:05 yangyong Exp $
 */
public class SysDeptAuthiBatis extends GenericDaoiBatis implements SysDeptAuthDao {

    @SuppressWarnings("unchecked")
    public SysDeptAuthiBatis(Class persistentClass) {
        super(SysDeptAuth.class);
    }

    @SuppressWarnings("unchecked")
    public SysDeptAuthiBatis() {
        super(SysDeptAuth.class);
    }
    /**
     * @param obj
     * @see com.banger.mobile.dao.user.ObjectDao#addObject(java.lang.Object)
     */
    public void addObject(Object obj) {
        this.getSqlMapClientTemplate().insert("addSysDeptAuth",(SysDeptAuth)obj);
    }

    /**
     * @param id
     * @see com.banger.mobile.dao.user.ObjectDao#deleteObject(int)
     */
    public void deleteObject(int id) {
        this.getSqlMapClientTemplate().delete("deleteSysDeptAuth",id);
    }

    /**
     * @param obj
     * @see com.banger.mobile.dao.user.ObjectDao#updateObject(java.lang.Object)
     */
    public void updateObject(Object obj) {
        this.getSqlMapClientTemplate().update("updateSysDeptAuth",(SysDeptAuth)obj);
    }

    /**
     * @param id
     * @return
     * @see com.banger.mobile.dao.user.ObjectDao#getObjectById(int)
     */
    public Object getObjectById(int id) {
        return (Object) this.getSqlMapClientTemplate().queryForObject("getSysDeptAuthById", id);
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
                "getSysDeptAuthPageMap", "getSysDeptAuthCount", parameters, page);
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
        return (List<Object>) this.getSqlMapClientTemplate().queryForList("getSysDeptAuthList",parameters);
    }

    /**
     * @param parameters
     * @see com.banger.mobile.dao.user.ObjectDao#deleteObjectMap(java.util.Map)
     */
    public void deleteObjectMap(Map<String, Object> parameters) {
        this.getSqlMapClientTemplate().delete("deleteSysDeptAuth",parameters);
    }

    /**
     * @return
     * @see com.banger.mobile.dao.user.ObjectDao#getObjectList()
     */
    public List<Object> getObjectList() {
        return null;
    }

    /**
     * 缓存所有的机构权限
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<SysDeptAuth> getAllDeptAuthForCache(){
        return this.getSqlMapClientTemplate().queryForList("getAllDeptAuthForCache");
    }


}
