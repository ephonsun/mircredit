/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :工作托管...
 * Author     :yangy
 * Create Date:2012-8-18
 */
package com.banger.mobile.facade.user;

import java.util.List;
import java.util.Map;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.model.user.SysWorkDelegate;

/**
 * @author yangyang
 * @version $Id: SysWorkDelegateService.java,v 0.1 2012-8-18 上午11:06:29 yangyong Exp $
 */
public interface SysWorkDelegateService {

    /**
     * 添加一条信息
     * @param recordInfo
     */
    public void addSysWorkDelegate(SysWorkDelegate sysWorkDelegate);

    /**
     * 删除信息
     * @param id
     */
    public void deleteSysWorkDelegate(int id);
    
    /**
     * 修改信息
     * @param recordInfo
     */
    public void updateSysWorkDelegate(SysWorkDelegate sysWorkDelegate);

    /**
     * 根据id得到信息
     * @param id
     * @return
     */
    public SysWorkDelegate getSysWorkDelegateById(int id);
    
    
    /**
     * 分页查询
     * @param parameters
     * @param page
     * @return
     */
    public PageUtil<SysWorkDelegate> getSysWorkDelegatePage(Map<String, Object> parameters, Page page);
    
    /**
     * 查询所有模板集合
     * @param parameters
     * @param page
     * @return
     */
    public List<SysWorkDelegate> getSysWorkDelegateList(Map<String, Object> parameters);
}
