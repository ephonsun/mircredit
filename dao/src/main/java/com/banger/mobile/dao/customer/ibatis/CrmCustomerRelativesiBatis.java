/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :亲友信息
 * Author     :xuhj
 * Version    :1.0
 * Create Date:May 2, 2012
 */
package com.banger.mobile.dao.customer.ibatis;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.dao.customer.CrmCustomerRelativesDao;
import com.banger.mobile.domain.model.base.customer.BaseCrmCustomerRelatives;
import com.banger.mobile.domain.model.customer.CrmCustomerRelatives;
import com.banger.mobile.ibatis.GenericDaoiBatis;



public class CrmCustomerRelativesiBatis extends GenericDaoiBatis implements CrmCustomerRelativesDao {

	public CrmCustomerRelativesiBatis() {
		super(CrmCustomerRelatives.class);
	}

	public CrmCustomerRelativesiBatis(Class persistentClass) {
		super(CrmCustomerRelatives.class);
	}

	/**
	 * 获取亲友信息
	 */
	public PageUtil<CrmCustomerRelatives> selectCustomerRelatives(Map<String, String> map, Page page){
		List<CrmCustomerRelatives> list = (List<CrmCustomerRelatives>)this.findQueryPage("selectCustomerRelatives", "selectCustomerRelativesCount", map, page);
		if (list == null) {
			list = new ArrayList<CrmCustomerRelatives>();
		}
		return new PageUtil<CrmCustomerRelatives>(list, page);
	}
	/**
	 * 新增亲友信息
	 */
	public void addRelativesCustomer(List<BaseCrmCustomerRelatives> entityList){
		this.exectuteBatchInsert("insertCustomerRelatives", entityList);
	}
	/**
	 * 查询客户id列表
	 */
	public List<Integer> getCustomerIdList(String customerId){
		return this.getSqlMapClientTemplate().queryForList("getCustomerIdList", customerId);
	}
	/**
	 * 移除亲友信息
	 */
	public void delRelatives(String customerRelativesId){
		this.getSqlMapClientTemplate().delete("delRelatives", customerRelativesId);
	}
	/**
	 * 有权限的垃圾箱客户
	 */
	public String getCusIdsByPermission(Map<String, String> parameters){
		List<Integer> cusIdList = this.getSqlMapClientTemplate().queryForList("getCusIdsByPermission", parameters);
		String cusIds="";
		if(cusIdList!=null){
			for(Integer id: cusIdList){
				if(cusIds.equals("")){
					cusIds = id.toString();
				}else{
					cusIds = cusIds + "," + id.toString();
				}
			}
		}
		return cusIds;
	}
	/**
	 * 批量彻底删除客户，清除关联信息
	 */
	public void delRelativesInDustbin(String cusids){
		this.getSqlMapClientTemplate().delete("delRelativesInDustbin", cusids);
	}

	/**
	 * 清空垃圾箱 清除关联信息
	 */
	public void clearRelativesInDustbin(Map<String, String> parameters){
		this.getSqlMapClientTemplate().delete("clearRelativesInDustbin",parameters);
	}

	/**
	 * 查询客户关联亲友总数
	 *
	 * @param customerId
	 * @return
	 */
	@Override
	public Integer selectRelativesByCustomerId(Integer customerId) {
		return (Integer) this.getSqlMapClientTemplate().queryForObject("selectRelativesByCustomerId",customerId);
	}
}
