/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :在线用户DAO接口...
 * Author     :yangy
 * Create Date:2012-12-3
 */
package com.banger.mobile.dao.user;

import java.util.List;
import java.util.Map;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.model.user.SysUserOnline;

/**
 * @author yangyang
 * @version $Id: SysUserOnlineDao.java,v 0.1 2012-12-3 下午1:50:03 yangyong Exp $
 */
public interface SysUserOnlineDao {
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
     * 修改用户状态
     * @param recordInfo
     */
    public void updateSysUserOnlineStatus(Map<String, Object> parameters);
    
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
     * 取出所以在线用户的信息包含admin
     * @return
     */
    public List<SysUserOnline> getALLSysUserOnline(Map<String, Object> parameters);
    
    
    
    /**
     * 删除
     * @param parameters
     */
    public void deleteObjectMap(Map<String, Object> parameters);
    
    /**
     * 系统启动时初始化在线用户状态
     */
    public void initOnlineUserState() ;
    
    
    
    /**
     * 根据用户编号取用户在线实体
     * @param parameters
     * @param page
     * @return
     */
    public List<SysUserOnline> getSysUserOnlineByUserId(Integer userId);
    
    /**
     * 更新用户状态
     * @param userId
     */
	public void userLoginState(Integer userId) ;
    
}
