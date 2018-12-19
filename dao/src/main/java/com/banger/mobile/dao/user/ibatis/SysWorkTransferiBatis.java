/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :工作转交dao
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
import com.banger.mobile.domain.model.user.SysWorkTransfer;
import com.banger.mobile.ibatis.GenericDaoiBatis;

/**
 * @author yangyang
 * @version $Id: SysWorkTransferiBatis.java,v 0.1 2012-8-14 上午10:09:22 yangyong Exp $
 */
@SuppressWarnings("rawtypes")
public class SysWorkTransferiBatis  extends GenericDaoiBatis implements ObjectDao {

    @SuppressWarnings("unchecked")
    public SysWorkTransferiBatis(Class persistentClass) {
        super(SysWorkTransfer.class);
    }

    @SuppressWarnings("unchecked")
    public SysWorkTransferiBatis() {
        super(SysWorkTransfer.class);
    }
    /**
     * @param obj
     * @see com.banger.mobile.dao.user.ObjectDao#addObject(java.lang.Object)
     */
    public void addObject(Object obj) {
        this.getSqlMapClientTemplate().insert("addSysWorkTransfer",(SysWorkTransfer)obj);
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
        this.getSqlMapClientTemplate().update("updateSysWorkTransfer",(SysWorkTransfer)obj);
    }

    /**
     * @param id
     * @return
     * @see com.banger.mobile.dao.user.ObjectDao#getObjectById(int)
     */
    public Object getObjectById(int id) {
        return (Object) this.getSqlMapClientTemplate().queryForObject("getSysWorkTransferById", id);
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
                "getSysWorkTransferPageMap", "getSysWorkTransferCount", parameters, page);
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
        return (List<Object>) this.getSqlMapClientTemplate().queryForList("getSysWorkTransferList",parameters);
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
