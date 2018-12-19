/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :在线用户DAO接口实现...
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
import com.banger.mobile.dao.user.SysUserOnlineDao;
import com.banger.mobile.domain.model.user.SysUserOnline;
import com.banger.mobile.ibatis.GenericDaoiBatis;

/**
 * @author yangyang
 * @version $Id: SysUserOnlineiBatis.java,v 0.1 2012-8-14 上午10:33:19 yangyong Exp $
 */
public class SysUserOnlineiBatis extends GenericDaoiBatis implements SysUserOnlineDao {

    @SuppressWarnings("unchecked")
    public SysUserOnlineiBatis(Class persistentClass) {
        super(SysUserOnline.class);
    }

    @SuppressWarnings("unchecked")
    public SysUserOnlineiBatis() {
        super(SysUserOnline.class);
    }
    /**
     * @param obj
     * @see com.banger.mobile.dao.user.ObjectDao#addObject(java.lang.Object)
     */
    public void addObject(Object obj) {
        this.getSqlMapClientTemplate().insert("addSysUserOnline",(SysUserOnline)obj);
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
        this.getSqlMapClientTemplate().update("updateSysUserOnline",(SysUserOnline)obj);
    }

    /**
     * @param id
     * @return
     * @see com.banger.mobile.dao.user.ObjectDao#getObjectById(int)
     */
    public Object getObjectById(int id) {
        return (Object) this.getSqlMapClientTemplate().queryForObject("getSysUserOnlineByIdById", id);
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
                "getSysUserOnlinePageMap", "getSysUserOnlineCount", parameters, page);
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
        return (List<Object>) this.getSqlMapClientTemplate().queryForList("getSysUserOnlineList",parameters);
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
        return (List<Object>) this.getSqlMapClientTemplate().queryForList("getSysUserOnlineMList");
    }

    /**
     *
     * @see com.banger.mobile.dao.user.SysUserOnlineDao#initOnlineUserState()
     */
    public void initOnlineUserState() {
        this.getSqlMapClientTemplate().update("initSysUserOnline");
    }

    /**
     * @param userId
     * @return
     * @see com.banger.mobile.dao.user.SysUserOnlineDao#getSysUserOnlineByUserId(java.lang.Integer)
     */
    public List<SysUserOnline> getSysUserOnlineByUserId(Integer userId) {
        return (List<SysUserOnline>)this.getSqlMapClientTemplate().queryForList("getSysUserOnlineByUserId",userId);
    }

    /**
     * @return
     * @see com.banger.mobile.dao.user.SysUserOnlineDao#getALLSysUserOnline()
     */
    public List<SysUserOnline> getALLSysUserOnline(Map<String, Object> parameters) {
        return (List<SysUserOnline>) this.getSqlMapClientTemplate().queryForList("getALLSysUserOnline",parameters);
    }

    /**
     * @param parameters
     * @see com.banger.mobile.dao.user.SysUserOnlineDao#updateSysUserOnlineStatus(java.util.Map)
     */
    public void updateSysUserOnlineStatus(Map<String, Object> parameters) {
        this.getSqlMapClientTemplate().update("updateSysUserOnlineStatus",parameters);
    }

    /**
     * 更新用户状态
     * @param userId
     */
    public void userLoginState(Integer userId) {
        this.getSqlMapClientTemplate().update("userLoginState",userId);
    }

}
