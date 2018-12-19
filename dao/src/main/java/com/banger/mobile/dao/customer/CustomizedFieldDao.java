/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :客户自定义信息
 * Author     :xuhj
 * Version    :1.0
 * Create Date:May 2, 2012
 */
package com.banger.mobile.dao.customer;

import java.util.List;
import java.util.Map;

import com.banger.mobile.domain.model.customer.CrmCustomer;
import com.banger.mobile.domain.model.customer.CrmCustomerExtField;
import com.banger.mobile.domain.model.customer.CustomerExtendFieldBean;

public interface CustomizedFieldDao {
	/**
     * 添加客户自定义信息
     * @param customerExtendFieldBean
     */
    public void addCustomizedField(Map<String, String> parameters);

    
    /**
     * 批量添加客户自定义信息
     * @param customerExtendFieldBean
     */
    public void addCustomizedFieldBatch(List<Map<String, String>> parameters);
    
    /**
     * 删除客户自定义信息
     * @param id
     */
    public void deleteCustomizedField(Integer id);
    
    /**
     * 修改客户自定义信息
     * @param customerExtendFieldBean
     */
    public void updateCustomizedField(Map<String, String> parameters);
    
    /**
     * 批量更新客户自定义信息
     * @param customerExtendFieldBean
     */
    public void updateCustomizedFieldBatch(List<Map<String, String>> parameters);

    /**
     * 根据客户ID获取客户实体
     * @param id
     * @return
     */
    public CustomerExtendFieldBean getCustomizedFieldById(Integer id);
    
    
    /**
     * 根据客户ID获取客户实体
     * @param id
     * @return
     */
    public CrmCustomerExtField getCustomizedFieldByCusId(Integer customerId);

    void addCustomizedFieldByCustomerBatch(List<CrmCustomer> customerList);
}
