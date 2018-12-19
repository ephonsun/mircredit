/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :导出excel Dao
 * Author     :cheny
 * Create Date:2012-9-10
 */
package com.banger.mobile.dao.crmModuleExport.iBatis;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientCallback;

import com.banger.mobile.dao.crmModuleExport.CrmModuleExportDao;
import com.banger.mobile.domain.model.crmModuleExport.CrmModuleExport;
import com.banger.mobile.ibatis.GenericDaoiBatis;
import com.ibatis.sqlmap.client.SqlMapExecutor;

/**
 * @author cheny
 * @version $Id: CrmModuleExportDaoiBatis.java,v 0.1 2012-9-10 上午11:29:36 cheny Exp $
 */
public class CrmModuleExportDaoiBatis extends GenericDaoiBatis implements CrmModuleExportDao{

    /**
     * 
     */
    public CrmModuleExportDaoiBatis() {
        super(CrmModuleExport.class);
    }
    /**
     * @param persistentClass
     */
    public CrmModuleExportDaoiBatis(Class persistentClass) {
        super(CrmModuleExport.class);
    }

    /**
	 * 记录 导入导出 字段
	 * @param crmModuleExport
	 */
    public void insertCrmModuleExport(CrmModuleExport crmModuleExport){
        this.getSqlMapClientTemplate().insert("insertCrmModuleExport",crmModuleExport);
    }
    /**
     * 获取登录用户上次导入导出字段 记录
     * @param moduleName
     * @return
     */
    public List<CrmModuleExport>  getCrmModuleExportList(Map<String,Object> map){
        return this.getSqlMapClientTemplate().queryForList("getCrmModuleExport",map);
    }
    /**
     * 删除 导入导出 记录字段
     * @param id
     */
    public void delCrmModuleExportById(int id){
        this.getSqlMapClientTemplate().delete("delCrmModuleExportById", id);
    }
    
    /**
     * 删除 导入导出 记录字段
     * @param id
     */
    public void delCrmModuleExport(Map<String,Object> map){
        this.getSqlMapClientTemplate().delete("delCrmModuleExport", map);
    }
    /**
     * 保存条件集合
     * @param crmModuleExport
     */
    public void insertCrmModuleExport(List<CrmModuleExport> crmModuleExport) {
        this.exectuteBatchInsert("insertCrmModuleExport", crmModuleExport);
    }
    /**
     * 模拟查询
     * @param moduleName
     * @return
     */
    public List<CrmModuleExport> getCrmModuleLikeList(Map<String,Object> map) {
        return this.getSqlMapClientTemplate().queryForList("getCrmModuleLike",map);
    }
}
