/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :最大在线用户...
 * Author     :yangy
 * Create Date:2012-9-5
 */
package com.banger.mobile.facade.user;

import java.util.List;
import java.util.Map;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.model.user.SysUserOnlineMax;

/**
 * @author yangyang
 * @version $Id: SysUserOnlineMaxMaxService.java,v 0.1 2012-9-5 下午2:14:56 yangyong Exp $
 */
public interface SysUserOnlineMaxService {
    /**
     * 添加一条信息
     * @param recordInfo
     */
    public void addSysUserOnlineMax(SysUserOnlineMax sysUserOnlineMax);

    /**
     * 删除信息
     * @param id
     */
    public void deleteSysUserOnlineMax(int id);
    
    /**
     * 修改信息
     * @param recordInfo
     */
    public void updateSysUserOnlineMax(SysUserOnlineMax sysUserOnlineMax);

    /**
     * 根据id得到信息
     * @param id
     * @return
     */
    public SysUserOnlineMax getSysUserOnlineMaxById(int id);
    
    
    /**
     * 分页查询
     * @param parameters
     * @param page
     * @return
     */
    public PageUtil<SysUserOnlineMax> getSysUserOnlineMaxPage(Map<String, Object> parameters, Page page);
    
    /**
     * 查询峰值用户数
     * @param parameters
     * @param page
     * @return
     */
    public List<SysUserOnlineMax> getSysUserOnlineMaxList(Map<String, Object> parameters);
}
