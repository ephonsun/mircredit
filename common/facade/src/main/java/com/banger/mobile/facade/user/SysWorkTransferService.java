/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :工作转交...
 * Author     :yangy
 * Create Date:2012-8-18
 */
package com.banger.mobile.facade.user;

import java.util.List;
import java.util.Map;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.model.user.SysWorkTransfer;

/**
 * @author yangyang
 * @version $Id: SysWorkTransferService.java,v 0.1 2012-8-18 上午11:06:05 yangyong Exp $
 */
public interface SysWorkTransferService {

    /**
     * 添加一条信息
     * @param recordInfo
     */
    public void addSysWorkTransfer(SysWorkTransfer sysWorkTransfer);

    /**
     * 删除信息
     * @param id
     */
    public void deleteSysWorkTransfer(int id);
    
    /**
     * 修改信息
     * @param recordInfo
     */
    public void updateSysWorkTransfer(SysWorkTransfer sysWorkTransfer);

    /**
     * 根据id得到信息
     * @param id
     * @return
     */
    public SysWorkTransfer getSysWorkTransferById(int id);
    
    
    /**
     * 分页查询
     * @param parameters
     * @param page
     * @return
     */
    public PageUtil<SysWorkTransfer> getSysWorkTransferPage(Map<String, Object> parameters, Page page);
    
    /**
     * 查询所有模板集合
     * @param parameters
     * @param page
     * @return
     */
    public List<SysWorkTransfer> getSysWorkTransferList(Map<String, Object> parameters);
}
