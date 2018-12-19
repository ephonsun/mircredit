/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :机构权限
 * Author     :yangy
 * Create Date:2012-8-14
 */
package com.banger.mobile.dao.user;

import java.util.List;
import java.util.Map;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.model.user.SysDeptAuth;

/**
 * @author yangyang
 * @version $Id: SysDeptAuthDao.java,v 0.1 2012-8-14 上午9:14:59 yangyong Exp $
 */
public interface SysDeptAuthDao extends ObjectDao{

    /**
     * 添加一条信息
     * @param recordInfo
     */
    public void addObject(Object obj);

    /**
     * 删除信息
     * @param id
     */
    public void deleteObject(int id);
    
    /**
     * 修改信息
     * @param recordInfo
     */
    public void updateObject(Object obj);

    /**
     * 根据id得到信息
     * @param id
     * @return
     */
    public Object getObjectById(int id);
    
    
    /**
     * 分页查询
     * @param parameters
     * @param page
     * @return
     */
    public PageUtil<Object> getObjectPage(Map<String, Object> parameters, Page page);
    
    /**
     * 查询所有模板集合
     * @param parameters
     * @param page
     * @return
     */
    public List<Object> getObjectList(Map<String, Object> parameters);
    
    /**
     * 查询所有模板集合
     * @param parameters
     * @param page
     * @return
     */
    public List<Object> getObjectList();
    
    /**
     * 删除
     * @param parameters
     */
    public void deleteObjectMap(Map<String, Object> parameters);
    /**
     * 缓存所有的机构权限
     * @return
     */
    public List<SysDeptAuth> getAllDeptAuthForCache();
}
