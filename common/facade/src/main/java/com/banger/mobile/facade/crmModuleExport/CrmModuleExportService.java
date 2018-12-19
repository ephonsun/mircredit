/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :模块导出...
 * Author     :cheny
 * Create Date:2012-9-10
 */
package com.banger.mobile.facade.crmModuleExport;

import java.util.List;
import java.util.Map;

import com.banger.mobile.domain.model.crmModuleExport.CrmModuleExport;

/**
 * @author cheny
 * @version $Id: CrmModuleExportService.java,v 0.1 2012-9-10 上午11:43:48 cheny Exp $
 */
public interface CrmModuleExportService {
    
	/**
	 * 记录 导入导出 字段
	 * @param crmModuleExport
	 */
    public void insertCrmModuleExport(CrmModuleExport crmModuleExport);
    
    /**
     * 获取登录用户上次导入导出字段 记录
     * @param moduleName
     * @return
     */
    public List<CrmModuleExport>  getCrmModuleExportList(String moduleName);
    
    /**
     * 模糊匹配登录用户上次导入导出字段 记录
     * @param moduleName
     * @return
     */
    public List<CrmModuleExport>  getCrmModuleLikeList(String moduleName);
    
    /**
     * 删除 导入导出 记录字段
     * @param id
     */
    public void delCrmModuleExportById(int id);
    
    /**
     * 保存字段
     * @param data
     * @param moduleName
     */
    public void saveExportField(String data,String moduleName);
  /**
   * 页面返回的字段   
   * @param data
   * @return
   */
    public List<String> getFileValues(String data);
}
