/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :客户所处行业dao实现
 * Author     :QianJie
 * Create Date:May 25, 2012
 */
package com.banger.mobile.dao.system.ibatis;

import java.util.List;
import java.util.Map;

import com.banger.mobile.dao.system.CrmCustomerIndustryDao;
import com.banger.mobile.domain.model.system.CrmCustomerIndustry;
import com.banger.mobile.domain.model.system.CrmCustomerType;
import com.banger.mobile.ibatis.GenericDaoiBatis;

/**
 * @author QianJie
 * @version $Id: CrmCustomerIndustryDaoiBatis.java,v 0.1 May 25, 2012 10:28:55 AM QianJie Exp $
 */
public class CrmCustomerIndustryDaoiBatis extends GenericDaoiBatis implements CrmCustomerIndustryDao {

    public CrmCustomerIndustryDaoiBatis() {
        super(CrmCustomerIndustry.class);
    }
    
    public CrmCustomerIndustryDaoiBatis(Class persistentClass) {
        super(CrmCustomerIndustry.class);
    }

    /**
     * 添加一种行业类型
     * @param crmCustomerIndustry
     * @see com.banger.mobile.dao.system.CrmCustomerIndustryDao#AddCrmCustomerIndustry(com.banger.mobile.domain.model.system.CrmCustomerIndustry)
     */
    @Override
    public void AddCrmCustomerIndustry(CrmCustomerIndustry crmCustomerIndustry) {
        this.getSqlMapClientTemplate().insert("addCrmCustomerIndustry", crmCustomerIndustry);
    }

    /**
     * 删除一种行业类型
     * @param id
     * @see com.banger.mobile.dao.system.CrmCustomerIndustryDao#deleteCrmCustomerIndustry(int)
     */
    @Override
    public void deleteCrmCustomerIndustry(int id) {
        this.getSqlMapClientTemplate().update("deleteCrmCustomerIndustryById",id);
    }

    /**
     * 根据Id获取行业类型
     * @param id
     * @return
     * @see com.banger.mobile.dao.system.CrmCustomerIndustryDao#getCrmCustomerIndustryById(int)
     */
    @Override
    public CrmCustomerIndustry getCrmCustomerIndustryById(int id) {
        return (CrmCustomerIndustry)this.getSqlMapClientTemplate().queryForObject("getCrmCustomerIndustryById",id);
    }
    
    /**
     * 根据行业名称获取行业类型
     * @param id
     * @return
     */
    @Override
    public CrmCustomerIndustry getCrmCustomerIndustryByName(String name) {
        return (CrmCustomerIndustry)this.getSqlMapClientTemplate().queryForObject("getCrmCustomerIndustryByName",name);
    }

    /**
     * 修改一种行业类型
     * @param crmCustomerIndustry
     * @see com.banger.mobile.dao.system.CrmCustomerIndustryDao#updateCrmCustomerIndustry(com.banger.mobile.domain.model.system.CrmCustomerIndustry)
     */
    @Override
    public void updateCrmCustomerIndustry(CrmCustomerIndustry crmCustomerIndustry) {
        this.getSqlMapClientTemplate().update("updateCrmCustomerIndustry",crmCustomerIndustry);
    }

    /**
     * 获取所有未删除的行业类型
     * @return
     * @see com.banger.mobile.dao.system.CrmCustomerIndustryDao#getAllCrmCustomerIndustry()
     */
    @Override
    public List<CrmCustomerIndustry> getAllCrmCustomerIndustry() {
        return this.getSqlMapClientTemplate().queryForList("getAllCrmCustomerIndustry");
    }

    /**
     * 根据行业类型名称获取拥有相同行业类型名称的数据
     * @param crmCustomerIndustry
     * @return
     * @see com.banger.mobile.dao.system.CrmCustomerIndustryDao#getSameCrmCustomerIndustryByName(com.banger.mobile.domain.model.system.CrmCustomerIndustry)
     */
    @Override
    public List<CrmCustomerIndustry> getSameCrmCustomerIndustryByName(CrmCustomerIndustry crmCustomerIndustry) {
        return this.getSqlMapClientTemplate().queryForList("getSameCrmCustomerIndustryByName",crmCustomerIndustry);
    }

    /**
     * 获取现有行业类型数据按SORTNO排序最大的
     * @return
     * @see com.banger.mobile.dao.system.CrmCustomerIndustryDao#getMaxSortNoCrmCustomerIndustry()
     */
    @Override
    public CrmCustomerIndustry getMaxSortNoCrmCustomerIndustry() {
        return (CrmCustomerIndustry)this.getSqlMapClientTemplate().queryForObject("getMaxSortNoCrmCustomerIndustry");
    }

    /**
     * 获取现有行业类型数据按SORTNO排序最小的
     * @return
     * @see com.banger.mobile.dao.system.CrmCustomerIndustryDao#getMinSortNoCrmCustomerIndustry()
     */
    @Override
    public CrmCustomerIndustry getMinSortNoCrmCustomerIndustry() {
        return (CrmCustomerIndustry)this.getSqlMapClientTemplate().queryForObject("getMinSortNoCrmCustomerIndustry");
    }

    /**
     * 获取要移动的行业类型对象
     * @param parameters
     * @return
     * @see com.banger.mobile.dao.system.CrmCustomerIndustryDao#getNeedMoveCrmCustomerIndustry(java.util.Map)
     */
    @Override
    public CrmCustomerIndustry getNeedMoveCrmCustomerIndustry(Map<String, Object> parameters) {
        return (CrmCustomerIndustry)this.getSqlMapClientTemplate().queryForObject("getNeedMoveCrmCustomerIndustry",parameters);
    }

}
