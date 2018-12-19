/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :模版对象dao
 * Author     :yangy
 * Create Date:2012-8-14
 */
package com.banger.mobile.dao.user;

import java.util.List;
import java.util.Map;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;


/**
 * @author yangyang
 * @version $Id: ObjectDao.java,v 0.1 2012-8-14 上午9:26:34 yangyong Exp $
 */
public interface ObjectDao {

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
    
    
    
}
