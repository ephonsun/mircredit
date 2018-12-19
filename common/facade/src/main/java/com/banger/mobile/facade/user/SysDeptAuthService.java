/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :机构权限...
 * Author     :yangy
 * Create Date:2012-8-15
 */
package com.banger.mobile.facade.user;

import java.util.List;
import java.util.Map;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.model.user.SysDeptAuth;

/**
 * @author yangyang
 * @version $Id: SysDeptAuthService.java,v 0.1 2012-8-15 下午3:50:23 yangyong Exp $
 */
public interface SysDeptAuthService {


    /**
     * 添加一条信息
     * @param recordInfo
     */
    public void addSysDeptAuth(SysDeptAuth sysDeptAuth);

    /**
     * 删除信息
     * @param id
     */
    public void deleteSysDeptAuth(Map<String, Object> parameters);
    
    /**
     * 修改信息
     * @param recordInfo
     */
    public void updateSysDeptAuth(SysDeptAuth sysDeptAuth);

    /**
     * 根据id得到信息
     * @param id
     * @return
     */
    public SysDeptAuth getSysDeptAuthById(int id);
    
    
    /**
     * 分页查询
     * @param parameters
     * @param page
     * @return
     */
    public PageUtil<SysDeptAuth> getSysDeptAuthPage(Map<String, Object> parameters, Page page);
    
    /**
     * 查询所有模板集合
     * @param parameters
     * @param page
     * @return
     */
    public List<SysDeptAuth> getSysDeptAuthList(Map<String, Object> parameters);
}
