/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :导出excel Dao
 * Author     :cheny
 * Create Date:2012-9-10
 */
package com.banger.mobile.dao.crmModuleExport;

import java.util.List;
import java.util.Map;

import com.banger.mobile.domain.model.crmModuleExport.CrmModuleExport;

/**
 * @author cheny
 * @version $Id: CrmModuleExportDao.java,v 0.1 2012-9-10 上午11:29:10 cheny Exp $
 */
public interface CrmModuleExportDao {

	/**
	 * 记录 导入导出 字段
	 * @param crmModuleExport
	 */
    public void insertCrmModuleExport(CrmModuleExport crmModuleExport);
    /**
     * 保存条件集合
     * @param crmModuleExport
     */
    public void insertCrmModuleExport(List<CrmModuleExport> crmModuleExport);
    
    /**
     * 获取登录用户上次导入导出字段 记录
     * @param moduleName
     * @return
     */
    public List<CrmModuleExport>  getCrmModuleExportList(Map<String,Object> map);
    
    /**
     * 删除 导入导出 记录字段
     * @param id
     */
    public void delCrmModuleExportById(int id);
    /**
     * 根据条件删除
     * @param map
     */
    public void delCrmModuleExport(Map<String,Object> map);
    /**
     * 模拟查询
     * @param moduleName
     * @return
     */
    public List<CrmModuleExport>  getCrmModuleLikeList(Map<String,Object> map);
}
