/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :亲友信息
 * Author     :xuhj
 * Version    :1.0
 * Create Date:May 2, 2012
 */
package com.banger.mobile.dao.customer;

import java.util.List;
import java.util.Map;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.model.base.customer.BaseCrmCustomerRelatives;
import com.banger.mobile.domain.model.customer.CrmCustomerRelatives;

public interface CrmCustomerRelativesDao {
	/**
	 * 获取亲友信息
	 * @param map
	 * @param page
	 * @return
	 */
    public PageUtil<CrmCustomerRelatives> selectCustomerRelatives(Map<String, String> map, Page page);
    /**
     * 新增亲友信息
     * @param entityList
     */
    public void addRelativesCustomer(List<BaseCrmCustomerRelatives> entityList);
    /**
     * 查询客户id列表
     * @param customerId
     * @return
     */
    public List<Integer> getCustomerIdList(String customerId);
    /**
     * 移除亲友信息
     * @param customerRelativesId
     */
  	public void delRelatives(String customerRelativesId);
  	/**
  	 * 有权限的垃圾箱客户
  	 * @param parameters
  	 * @return
  	 */
  	public String getCusIdsByPermission(Map<String, String> parameters);
  	/**
  	 * 批量彻底删除客户，清除关联信息
  	 * @param cusids
  	 */
  	public void delRelativesInDustbin(String cusids);
  	
  	/**
  	 * 清空垃圾箱 清除关联信息
  	 */
  	public void clearRelativesInDustbin(Map<String, String> parameters);

    /**
     * 查询客户关联亲友总数
     *
     * @param customerId
     * @return
     */
    public Integer selectRelativesByCustomerId(Integer customerId);
}
