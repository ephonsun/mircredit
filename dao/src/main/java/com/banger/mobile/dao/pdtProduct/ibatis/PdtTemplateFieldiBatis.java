/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :cheny
 * Create Date:2012-12-25
 */
package com.banger.mobile.dao.pdtProduct.ibatis;

import java.util.List;

import com.banger.mobile.dao.pdtProduct.PdtTemplateFieldDao;
import com.banger.mobile.domain.model.pdtProduct.PdtTemplateField;
import com.banger.mobile.ibatis.GenericDaoiBatis;

/**
 * @author cheny
 * @version $Id: PdtTemplateFieldiBatis.java,v 0.1 2012-12-25 上午11:03:43 cheny Exp $
 */
public class PdtTemplateFieldiBatis extends GenericDaoiBatis implements PdtTemplateFieldDao{
    public PdtTemplateFieldiBatis(){
        super(PdtTemplateField.class);
    }
    
    /**
     * 新增模板字段
     */
    public Integer insertPdtTemplateField(PdtTemplateField feild){
        return (Integer) this.getSqlMapClientTemplate().insert("insertPdtTemplateField",feild);
    }
    /**
     * 根据模板id查询模板下所有字段
     */
    public List<PdtTemplateField> getTempFieldsByTempId(int templateId){
        return this.getSqlMapClientTemplate().queryForList("getTempFieldsByTempId",templateId);
    }
    /**
     * 编辑默认字段
     */
    public void updateDefaultPdtTemplateField(PdtTemplateField feild){
        this.getSqlMapClientTemplate().update("updateDefaultPdtTemplateField",feild);
    }
    /**
     * 根据字段id查询字段对象
     */
    public PdtTemplateField getPdtTemplateFieldById(int feildId){
        return (PdtTemplateField) this.getSqlMapClientTemplate().queryForObject("getPdtTemplateFieldById",feildId);
    }
    /**
     * 更新模板字段
     */
    public void updateTemplateField(PdtTemplateField feild){
        this.getSqlMapClientTemplate().update("updateTemplateField",feild);
    }
    /**
     * 获取所有产品模版字段
     * @return
     */
    public List<PdtTemplateField> getAllPdtTemplateField(){
    	return this.getSqlMapClientTemplate().queryForList("getAllPdtTemplateField");
    }
    
}
