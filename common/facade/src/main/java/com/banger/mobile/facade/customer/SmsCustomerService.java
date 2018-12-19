package com.banger.mobile.facade.customer;

import java.util.List;
import java.util.Set;

import com.banger.mobile.domain.model.customer.CustomerBean;

public interface SmsCustomerService {
	/**
	 * 查询发送短信的客户
	 * @param conditionIds	条件集合
	 * @param cusIds	客户集合
	 * @return
	 */
	public List<CustomerBean> querySmsCustomer(String conditionIds,String cusIds);
	
	/**
	 * 查询发送短信的客户数量
	 * @param conditionIds	条件集合
	 * @param cusIds	客户集合
	 * @return
	 */
	public int querySmsCustomerCount(String conditionIds,String cusIds);
}
