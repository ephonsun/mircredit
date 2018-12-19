/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :导入...
 * Author     :yangy
 * Create Date:2012-8-27
 */
package com.banger.mobile.facade.sysImport;

import java.util.List;
import java.util.Map;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.model.sysImport.SysImport;

/**
 * @author yangyang
 * @version $Id: SysImportService.java,v 0.1 2012-8-27 下午1:11:35 yangyong Exp $
 */
public interface SysImportService {


    /**
     * 添加一条信息
     * @param recordInfo
     */
    public void addSysImport(SysImport sysImport);

    /**
     * 删除信息
     * @param id
     */
    public void deleteSysImport(int id);
    
    /**
     * 修改信息
     * @param recordInfo
     */
    public void updateSysImport(SysImport sysImport);

    /**
     * 根据id得到信息
     * @param id
     * @return
     */
    public SysImport getSysImportById(int id);
    
    
    /**
     * 分页查询
     * @param parameters
     * @param page
     * @return
     */
    public PageUtil<SysImport> getSysImportPage(Map<String, Object> parameters, Page page);
    
    /**
     * 查询所有模板集合
     * @param parameters
     * @param page
     * @return
     */
    public List<SysImport> getSysImportList(Map<String, Object> parameters);
}
