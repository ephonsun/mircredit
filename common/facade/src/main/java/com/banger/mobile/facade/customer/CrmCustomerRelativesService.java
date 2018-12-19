/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :亲友信息
 * Author     :xuhj
 * Version    :1.0
 * Create Date:May 3, 2012
 */
package com.banger.mobile.facade.customer;

import java.util.Map;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.model.customer.CrmCustomerRelatives;


public interface CrmCustomerRelativesService {
	/**
	 * 获取亲友信息
	 * @param customerId
	 * @param page
	 * @return
	 */
    public PageUtil<CrmCustomerRelatives> selectCustomerRelatives(String customerId, Page page);
    /**
     * 新增亲友信息
     * @param customerId
     * @param relativesIds
     */
    public void addRelativesCustomer(String customerId, String relativesIds);
    /**
     * 移除亲友信息
     * @param customerRelativesId
     */
  	public void delRelatives(String customerRelativesId);
  	/**
  	 * 清空垃圾箱 清除关联信息
  	 * @param parameters
  	 */
  	public void delRelativesInDustbin(Map<String, String> parameters);
  	/**
  	 * 批量彻底删除客户，清除关联信息
  	 * @param customerIds
  	 */
  	public void delRelativesByCusId(String customerIds);
  	
  	/**
  	 * 查询客户亲友列表
  	 * @param parameters
  	 * @param page
  	 * @return
  	 */
  	public PageUtil<CrmCustomerRelatives> getCustomerRelativesPad(Map<String, String> parameters, Page page, String userid);

    /**
     * 查询客户关联亲友总数
     *
     * @param customerId
     * @return
     */
    public Integer selectRelativesByCustomerId(Integer customerId);
}
