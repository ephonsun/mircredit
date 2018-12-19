/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :在线用户...
 * Author     :yangy
 * Create Date:2012-8-15
 */
package com.banger.mobile.facade.user;

import java.util.List;
import java.util.Map;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.model.user.SysUserOnline;

/**
 * @author yangyang
 * @version $Id: SysUserOnlineService.java,v 0.1 2012-8-15 上午11:46:39 yangyong Exp $
 */
public interface SysUserOnlineService {

    /**
     * 添加一条信息
     * @param recordInfo
     */
    public void addSysUserOnline(SysUserOnline sysUserOnline);

    /**
     * 删除信息
     * @param id
     */
    public void deleteSysUserOnline(int id);
    
    /**
     * 修改信息
     * @param recordInfo
     */
    public void updateSysUserOnline(SysUserOnline sysUserOnline);
    
    /**
     * 修改用户状态
     * @param recordInfo
     */
    public void updateSysUserOnlineStatus(Map<String, Object> parameters);
    

    /**
     * 根据id得到信息
     * @param id
     * @return
     */
    public SysUserOnline getSysUserOnlineById(int id);
    
    
    /**
     * 分页查询
     * @param parameters
     * @param page
     * @return
     */
    public PageUtil<SysUserOnline> getSysUserOnlinePage(Map<String, Object> parameters, Page page);
    
    /**
     * 查询所有模板集合
     * @param parameters
     * @param page
     * @return
     */
    public List<SysUserOnline> getSysUserOnlineList(Map<String, Object> parameters);
    
    
    /**
     * 根据用户编号取用户在线实体
     * @param parameters
     * @param page
     * @return
     */
    public List<SysUserOnline> getSysUserOnlineByUserId(Integer userId);
    
    /**
     * 查询峰值用户集合
     * @param parameters
     * @param page
     * @return
     */
    public List<SysUserOnline> getSysUserOnlineList();
    
    /**
     * 获得在线用户id集合
     * @return
     */
    public Integer[] getOnlineUserIds();
    
    /**
     * 系统启动时初始化在线用户状态
     */
    public void initOnlineUserState();
    
    /**
     * 取出所以在线用户的信息包含admin
     * @return
     */
    public List<SysUserOnline> getALLSysUserOnline(Map<String, Object> parameters);
    
    /**
     * 更新用户状态
     * @param userId
     */
    public void userLoginState(Integer userId);
}
