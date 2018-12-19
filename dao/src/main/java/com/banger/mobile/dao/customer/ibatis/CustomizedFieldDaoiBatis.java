/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :客户自定义信息
 * Author     :xuhj
 * Version    :1.0
 * Create Date:May 2, 2012
 */
package com.banger.mobile.dao.customer.ibatis;

import java.util.List;
import java.util.Map;

import com.banger.mobile.dao.customer.CustomizedFieldDao;
import com.banger.mobile.domain.model.customer.CrmCustomer;
import com.banger.mobile.domain.model.customer.CrmCustomerExtField;
import com.banger.mobile.domain.model.customer.CustomerExtendFieldBean;
import com.banger.mobile.ibatis.GenericDaoiBatis;

public class CustomizedFieldDaoiBatis extends GenericDaoiBatis implements CustomizedFieldDao {

    public CustomizedFieldDaoiBatis() {
        super(CustomerExtendFieldBean.class);
    }

    public CustomizedFieldDaoiBatis(Class persistentClass) {
        super(CustomerExtendFieldBean.class);
    }

    /**
     * 添加客户自定义信息
     * @param customerExtendFieldBean
     */
    public void addCustomizedField(Map<String, String> parameters) {
        this.getSqlMapClientTemplate().insert("addCustomizedField", parameters);
    }

    /**
     * 批量添加客户自定义信息
     * @param customerExtendFieldBean
     */
    public void addCustomizedFieldBatch(List<Map<String, String>> parameters) {
        this.exectuteBatchInsert("addCustomizedField", parameters);
    }

    /**
     * 删除客户自定义信息
     * @param id
     */
    public void deleteCustomizedField(Integer id) {
        this.getSqlMapClientTemplate().delete("deleteCustomizedField", id);
    }

    /**
     * 修改客户自定义信息
     * @param customerExtendFieldBean
     */
    public void updateCustomizedField(Map<String, String> parameters) {
        this.getSqlMapClientTemplate().update("updateCustomizedField", parameters);
    }

    /**
     * 根据客户ID获取客户实体
     * @param id
     * @return
     */
    public CustomerExtendFieldBean getCustomizedFieldById(Integer id) {
        return (CustomerExtendFieldBean) this.getSqlMapClientTemplate().queryForObject(
            "getCustomizedFieldById", id);
    }
    
    /**
     * 根据客户ID获取客户实体
     * @param id
     * @return
     */
    public CrmCustomerExtField getCustomizedFieldByCusId(Integer customerId) {
        return (CrmCustomerExtField) this.getSqlMapClientTemplate().queryForObject(
            "getCustomizedFieldByCusId", customerId);
    }

    /**
     * 批量更新客户自定义信息
     * @param customerExtendFieldBean
     */
    public void updateCustomizedFieldBatch(List<Map<String, String>> parameters) {
       this.executeBatchUpdate("updateCustomizedField", parameters);
    }

    /**
     * 批量插入客户自定义信息(只插入customer_id字段的值)
     * @param customerList
     */
    @Override
    public void addCustomizedFieldByCustomerBatch(List<CrmCustomer> customerList){
       this.exectuteBatchInsert("addCustomizedFieldByCustomer", customerList);
    }
}
