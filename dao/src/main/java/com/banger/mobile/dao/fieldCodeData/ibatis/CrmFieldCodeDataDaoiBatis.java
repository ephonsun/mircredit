/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :字段代码维护类...
 * Author     :QianJie
 * Create Date:May 31, 2012
 */
package com.banger.mobile.dao.fieldCodeData.ibatis;

import java.util.List;
import java.util.Map;

import com.banger.mobile.dao.fieldCodeData.CrmFieldCodeDataDao;
import com.banger.mobile.domain.model.fieldCodeData.CrmFieldCodeData;
import com.banger.mobile.ibatis.GenericDaoiBatis;

/**
 * @author QianJie
 * @version $Id: CrmFieldCodeDataDaoiBatis.java,v 0.1 May 31, 2012 5:21:50 PM QianJie Exp $
 */
public class CrmFieldCodeDataDaoiBatis extends GenericDaoiBatis  implements CrmFieldCodeDataDao{

    public CrmFieldCodeDataDaoiBatis() {
        super(CrmFieldCodeData.class);
    }
    public CrmFieldCodeDataDaoiBatis(Class persistentClass) {
        super(CrmFieldCodeData.class);
    }
    /**
     *  根据字段Id得到代码数据集合
     * @param templateId
     * @return
     */
    @Override
    public List<CrmFieldCodeData> getCrmFieldCodeDataListByFieldId(int fieldId) {
        return this.getSqlMapClientTemplate().queryForList("getCrmFieldCodeDataListByFieldId",fieldId);
    }
    /**
     * 添加一个代码数据
     * @param crmFieldCodeData
     * @see com.banger.mobile.dao.templateField.CrmFieldCodeDataDao#addCrmFieldCodeData(com.banger.mobile.domain.model.templateField.CrmFieldCodeData)
     */
    @Override
    public void addCrmFieldCodeData(CrmFieldCodeData crmFieldCodeData) {
        this.getSqlMapClientTemplate().insert("addCrmFieldCodeData",crmFieldCodeData);
    }
    /**
     * 修改一个代码数据
     * @param crmFieldCodeData
     * @see com.banger.mobile.dao.templateField.CrmFieldCodeDataDao#updateCrmFieldCodeData(com.banger.mobile.domain.model.templateField.CrmFieldCodeData)
     */
    @Override
    public void updateCrmFieldCodeData(CrmFieldCodeData crmFieldCodeData) {
        this.getSqlMapClientTemplate().update("updateCrmFieldCodeData",crmFieldCodeData);
    }
    
    /**
     * 删除一个代码数据
     * @param id
     * @see com.banger.mobile.dao.templateField.CrmFieldCodeDataDao#deleteCrmFieldCodeData(int)
     */
    @Override
    public void deleteCrmFieldCodeData(int id) {
        this.getSqlMapClientTemplate().update("deleteCrmFieldCodeDataById",id);
    }
    /**
     * 根据Id获取代码数据
     * @param id
     * @return
     * @see com.banger.mobile.dao.templateField.CrmFieldCodeDataDao#getCrmFieldCodeDataById(int)
     */
    @Override
    public CrmFieldCodeData getCrmFieldCodeDataById(int id) {
        return (CrmFieldCodeData)this.getSqlMapClientTemplate().queryForObject("getCrmFieldCodeDataById",id);
    }
    
    /**
     * 根据模版ID获取现有代码数据数据按SORTNO排序最大的
     * @param templateId
     * @return
     * @see com.banger.mobile.dao.templateField.CrmFieldCodeDataDao#getMaxSortNoCrmFieldCodeData(int)
     */
    @Override
    public CrmFieldCodeData getMaxSortNoCrmFieldCodeData() {
        return (CrmFieldCodeData)this.getSqlMapClientTemplate().queryForObject("getMaxSortNoCrmFieldCodeData");
    }
    /**
     * 根据模版ID获取现有代码数据数据按SORTNO排序最小的
     * @param templateId
     * @return
     * @see com.banger.mobile.dao.templateField.CrmFieldCodeDataDao#getMinSortNoCrmFieldCodeData(int)
     */
    @Override
    public CrmFieldCodeData getMinSortNoCrmFieldCodeData() {
        return (CrmFieldCodeData)this.getSqlMapClientTemplate().queryForObject("getMinSortNoCrmFieldCodeData");
    }
    /**
     * 获取要移动的代码数据对象
     * @param parameters
     * @return
     * @see com.banger.mobile.dao.templateField.CrmFieldCodeDataDao#getNeedMoveCrmFieldCodeData(java.util.Map)
     */
    @Override
    public CrmFieldCodeData getNeedMoveCrmFieldCodeData(Map<String, Object> parameters) {
        return (CrmFieldCodeData)this.getSqlMapClientTemplate().queryForObject("getNeedMoveCrmFieldCodeData",parameters);
    }
    /**
     * 根据代码数据名称获取拥有相同代码数据名称的数据
     * @param CrmFieldCodeData
     * @return
     * @see com.banger.mobile.dao.templateField.CrmFieldCodeDataDao#getSameCrmFieldCodeDataByName(com.banger.mobile.domain.model.templateField.CrmFieldCodeData)
     */
    @Override
    public List<CrmFieldCodeData> getSameCrmFieldCodeDataByName(CrmFieldCodeData crmFieldCodeData) {
        return this.getSqlMapClientTemplate().queryForList("getSameCrmFieldCodeDataByName",crmFieldCodeData);
    }

    /**
     * 获取所有字段代码数据
     * @return
     */
    @Override
    public List<CrmFieldCodeData> getAllCrmFieldCodeData() {
        return this.getSqlMapClientTemplate().queryForList("getAllCrmFieldCodeData");
    }
    /**
     * 根据字段ID删除对应代码表的值
     * @param fieldId
     */
    @Override
    public void deleteCrmFieldCodeDataByFieldId(int fieldId) {
        this.getSqlMapClientTemplate().update("deleteCrmFieldCodeDataByFieldId",fieldId);
    }
}
