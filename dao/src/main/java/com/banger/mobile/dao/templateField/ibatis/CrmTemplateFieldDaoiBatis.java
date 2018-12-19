/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :模版字段DAO
 * Author     :QianJie
 * Create Date:May 29, 2012
 */
package com.banger.mobile.dao.templateField.ibatis;

import java.util.List;
import java.util.Map;

import com.banger.mobile.dao.templateField.CrmTemplateFieldDao;
import com.banger.mobile.domain.model.templateField.CrmTemplateField;
import com.banger.mobile.ibatis.GenericDaoiBatis;

/**
 * @author QianJie
 * @version $Id: CrmTemplateFieldDaoiBatis.java,v 0.1 May 29, 2012 1:35:21 PM QianJie Exp $
 */
public class CrmTemplateFieldDaoiBatis extends GenericDaoiBatis implements CrmTemplateFieldDao{

    public CrmTemplateFieldDaoiBatis() {
        super(CrmTemplateField.class);
    }
    public CrmTemplateFieldDaoiBatis(Class persistentClass) {
        super(CrmTemplateField.class);
    }
    /**
     *  根据模版ID得到模版字段集合
     * @param templateId
     * @return
     */
    @Override
    public List<CrmTemplateField> getCrmTemplateFieldListByTemplateId(int templateId) {
        return this.getSqlMapClientTemplate().queryForList("getCrmTemplateFieldListByTemplateId",templateId);
    }
    /**
     * 添加一个模版字段
     * @param crmTemplateField
     * @see com.banger.mobile.dao.templateField.CrmTemplateFieldDao#addCrmTemplateField(com.banger.mobile.domain.model.templateField.CrmTemplateField)
     */
    @Override
    public int addCrmTemplateField(CrmTemplateField crmTemplateField) {
        return ((Integer)this.getSqlMapClientTemplate().insert("addCrmTemplateField",crmTemplateField)).intValue();
    }
    /**
     * 修改一个模版字段
     * @param crmTemplateField
     * @see com.banger.mobile.dao.templateField.CrmTemplateFieldDao#updateCrmTemplateField(com.banger.mobile.domain.model.templateField.CrmTemplateField)
     */
    @Override
    public void updateCrmTemplateField(CrmTemplateField crmTemplateField) {
        this.getSqlMapClientTemplate().update("updateCrmTemplateField",crmTemplateField);
    }
    
    /**
     * 删除一个模版字段
     * @param id
     * @see com.banger.mobile.dao.templateField.CrmTemplateFieldDao#deleteCrmTemplateField(int)
     */
    @Override
    public void deleteCrmTemplateField(int id) {
        this.getSqlMapClientTemplate().update("deleteCrmTemplateFieldById",id);
    }
    /**
     * 根据Id获取模版字段
     * @param id
     * @return
     * @see com.banger.mobile.dao.templateField.CrmTemplateFieldDao#getCrmTemplateFieldById(int)
     */
    @Override
    public CrmTemplateField getCrmTemplateFieldById(int id) {
        return (CrmTemplateField)this.getSqlMapClientTemplate().queryForObject("getCrmTemplateFieldById",id);
    }
    
    /**
     * 根据模版ID获取现有模版字段数据按SORTNO排序最大的
     * @param templateId
     * @return
     * @see com.banger.mobile.dao.templateField.CrmTemplateFieldDao#getMaxSortNoCrmTemplateField(int)
     */
    @Override
    public CrmTemplateField getMaxSortNoCrmTemplateField(int templateId) {
        return (CrmTemplateField)this.getSqlMapClientTemplate().queryForObject("getMaxSortNoCrmTemplateField",templateId);
    }
    /**
     * 根据模版ID获取现有模版字段数据按SORTNO排序最小的
     * @param templateId
     * @return
     * @see com.banger.mobile.dao.templateField.CrmTemplateFieldDao#getMinSortNoCrmTemplateField(int)
     */
    @Override
    public CrmTemplateField getMinSortNoCrmTemplateField(int templateId) {
        return (CrmTemplateField)this.getSqlMapClientTemplate().queryForObject("getMinSortNoCrmTemplateField",templateId);
    }
    /**
     * 获取要移动的模版字段对象
     * @param parameters
     * @return
     * @see com.banger.mobile.dao.templateField.CrmTemplateFieldDao#getNeedMoveCrmTemplateField(java.util.Map)
     */
    @Override
    public CrmTemplateField getNeedMoveCrmTemplateField(Map<String, Object> parameters) {
        return (CrmTemplateField)this.getSqlMapClientTemplate().queryForObject("getNeedMoveCrmTemplateField",parameters);
    }
    /**
     * 根据模版字段名称获取拥有相同模版字段名称的数据
     * @param crmTemplateField
     * @return
     * @see com.banger.mobile.dao.templateField.CrmTemplateFieldDao#getSameCrmTemplateFieldByName(com.banger.mobile.domain.model.templateField.CrmTemplateField)
     */
    @Override
    public List<CrmTemplateField> getSameCrmTemplateFieldByName(CrmTemplateField crmTemplateField) {
        return this.getSqlMapClientTemplate().queryForList("getSameCrmTemplateFieldByName",crmTemplateField);
    }
    /**
     * 获取现有模版字段是弹屏显示的数据
     * @return
     * @see com.banger.mobile.dao.templateField.CrmTemplateFieldDao#getPopupCrmTemplateField()
     */
    @Override
    public List<CrmTemplateField> getPopupCrmTemplateField() {
        return this.getSqlMapClientTemplate().queryForList("getPopupCrmTemplateField");
    }
    
    /**
     * 获取所有客户模版字段数据
     * @return
     * @see com.banger.mobile.dao.templateField.CrmTemplateFieldDao#getAllCrmTemplateField()
     */
    @Override
    public List<CrmTemplateField> getAllCrmTemplateField() {
        return this.getAllCrmTemplateField(true);
    }
    
    @SuppressWarnings("unchecked")
	public List<CrmTemplateField> getAllCrmTemplateField(boolean isCache) {
    	if(isCache){
    		return this.getSqlMapClientTemplate().queryForList("getAllCrmTemplateField");
    	}else{
    		return this.getSqlMapClientTemplate().queryForList("getAllCrmTemplateFieldNoCache");
    	}
    }
    
    /**
     * 获取所有产品模版字段数据
     * @return
     * @see com.banger.mobile.dao.templateField.CrmTemplateFieldDao#getAllCrmTemplateField()
     */
    @Override
    public List<CrmTemplateField> getAllPdtCrmTemplateField() {
        return this.getSqlMapClientTemplate().queryForList("getAllPdtCrmTemplateField");
    }
    
    /**
     * 获取所有模版自定义字段值
     * @param parameters
     * @return
     * @see com.banger.mobile.dao.templateField.CrmTemplateFieldDao#getCrmCustomerExtFieldByCustomerId(java.util.Map)
     */
    @Override
    public List getCrmCustomerExtFieldByCustomerId(Map<String, Object> parameters) {
        return this.getSqlMapClientTemplate().queryForList("getCrmCustomerExtFieldByCustomerId",parameters);
    }
    
    /**
     * 根据模版Id删除模版字段
     * @param templateId
     */
    @Override
    public void deleteCrmTemplateFieldByTemplateId(int templateId) {
        this.getSqlMapClientTemplate().update("deleteCrmTemplateFieldByTemplateId",templateId);
    }
    
    /**
     * 获取某个客户自定义字段值不为空的数据
     * @param parameters
     * @return
     * @see com.banger.mobile.dao.templateField.CrmTemplateFieldDao#getCrmCustomerExtFieldIsNotNull(java.util.Map)
     */
    @Override
    public List getCrmCustomerExtFieldIsNotNull(Map<String, Object> parameters) {
        return this.getSqlMapClientTemplate().queryForList("getCrmCustomerExtFieldIsNotNull",parameters);
    }
    
    /**
     * 获取某个产品自定义字段值不为空的数据
     * @param parameters
     * @return
     * @see com.banger.mobile.dao.templateField.CrmTemplateFieldDao#getPdtProductextFieldIsNotNull(java.util.Map)
     */
    @Override
    public List getPdtProductextFieldIsNotNull(Map<String, Object> parameters) {
        return this.getSqlMapClientTemplate().queryForList("getPdtProductextFieldIsNotNull",parameters);
    }
    
    /**
     * 获取新增或编辑模版字段是否已存在在基础模版字段中
     * @return
     * @see com.banger.mobile.dao.templateField.CrmTemplateFieldDao#getHasSameBasicCrmTemplateField()
     */
    @Override
    public List<CrmTemplateField> getHasSameBasicCrmTemplateField(CrmTemplateField crmTemplateField) {
        return this.getSqlMapClientTemplate().queryForList("getHasSameBasicCrmTemplateField",crmTemplateField);
    }

}
