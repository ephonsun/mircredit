/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :客户类型dao实现
 * Author     :QianJie
 * Create Date:May 21, 2012
 */
package com.banger.mobile.dao.system.ibatis;

import java.util.List;
import java.util.Map;

import com.banger.mobile.dao.system.CrmCustomerTypeDao;
import com.banger.mobile.domain.model.system.CrmCustomerType;
import com.banger.mobile.ibatis.GenericDaoiBatis;

/**
 * @author QianJie
 * @version $Id: CrmCustomerTypeDaoiBatis.java,v 0.1 May 21, 2012 10:47:40 AM QianJie Exp $
 */
public class CrmCustomerTypeDaoiBatis extends GenericDaoiBatis implements CrmCustomerTypeDao {

    public CrmCustomerTypeDaoiBatis() {
        super(CrmCustomerType.class);
    }
    
    /**
     * 
     * @param persistentClass
     */
    public CrmCustomerTypeDaoiBatis(Class persistentClass) {
        super(CrmCustomerType.class);
    }

    
    /**
     * 添加一种客户类型
     * @param crmCustomerType
     * @see com.banger.mobile.dao.system.CrmCustomerTypeDao#AddCrmCustomerType(com.banger.mobile.domain.model.system.CrmCustomerType)
     */
    @Override
    public void AddCrmCustomerType(CrmCustomerType crmCustomerType) {
        this.getSqlMapClientTemplate().insert("addCrmCustomerType", crmCustomerType);
    }

    /**
     * 删除一种客户类型
     * @param id
     * @see com.banger.mobile.dao.system.CrmCustomerTypeDao#deleteCrmCustomerType(int)
     */
    @Override
    public void deleteCrmCustomerType(int id) {
        this.getSqlMapClientTemplate().update("deleteCrmCustomerTypeById",id);
    }

    /**
     * 根据Id获取一种客户类型
     * @return
     * @see com.banger.mobile.dao.system.CrmCustomerTypeDao#getCrmCustomerTypeById()
     */
    @Override
    public CrmCustomerType getCrmCustomerTypeById(int id) {
        return (CrmCustomerType)this.getSqlMapClientTemplate().queryForObject("getCrmCustomerTypeById",id);
    }

    /**
     * 修改一种客户类型
     * @param crmCustomerType
     * @see com.banger.mobile.dao.system.CrmCustomerTypeDao#updateCrmCustomerType(com.banger.mobile.domain.model.system.CrmCustomerType)
     */
    @Override
    public void updateCrmCustomerType(CrmCustomerType crmCustomerType) {
        this.getSqlMapClientTemplate().update("updateCrmCustomerType",crmCustomerType);
    }

    /**
     * 获取所有未删除的客户类型
     * @return
     * @see com.banger.mobile.dao.system.CrmCustomerTypeDao#getAllCrmCustomerType()
     */
    @Override
    public List<CrmCustomerType> getAllCrmCustomerType() {
        return this.getSqlMapClientTemplate().queryForList("getAllCrmCustomerType");
    }

    /**
     * 根据客户类型名称获取拥有相同客户类型名称的数据
     * @param crmCustomerType
     * @return
     * @see com.banger.mobile.dao.system.CrmCustomerTypeDao#getSameCrmCustomerTypeByName(com.banger.mobile.domain.model.system.CrmCustomerType)
     */
    @Override
    public List<CrmCustomerType> getSameCrmCustomerTypeByName(CrmCustomerType crmCustomerType) {
        return this.getSqlMapClientTemplate().queryForList("getSameCrmCustomerTypeByName",crmCustomerType);
    }

    /**
     * 获取现有客户类型数据按SORTNO排序最大的
     * @return
     * @see com.banger.mobile.dao.system.CrmCustomerTypeDao#getMaxSortNoCrmCustomerType()
     */
    @Override
    public CrmCustomerType getMaxSortNoCrmCustomerType() {
        return (CrmCustomerType)this.getSqlMapClientTemplate().queryForObject("getMaxSortNoCrmCustomerType");
    }

    /**
     * 获取现有客户类型数据按SORTNO排序最小的
     * @return
     * @see com.banger.mobile.dao.system.CrmCustomerTypeDao#getMinSortNoCrmCustomerType()
     */
    @Override
    public CrmCustomerType getMinSortNoCrmCustomerType() {
        return (CrmCustomerType)this.getSqlMapClientTemplate().queryForObject("getMinSortNoCrmCustomerType");
    }

    /**
     * 获取要移动的客户类型对象
     * @return
     * @see com.banger.mobile.dao.system.CrmCustomerTypeDao#getNeedMoveCrmCustomerType()
     */
    @Override
    public CrmCustomerType getNeedMoveCrmCustomerType(Map<String, Object> parameters) {
        return (CrmCustomerType)this.getSqlMapClientTemplate().queryForObject("getNeedMoveCrmCustomerType",parameters);
    }

}
