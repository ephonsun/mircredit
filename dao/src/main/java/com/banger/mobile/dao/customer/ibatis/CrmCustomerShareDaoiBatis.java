/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :共享客户
 * Author     :xuhj
 * Version    :1.0
 * Create Date:May 2, 2012
 */
package com.banger.mobile.dao.customer.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.banger.mobile.dao.customer.CrmCustomerShareDao;
import com.banger.mobile.domain.model.customer.CrmCustomerShare;
import com.banger.mobile.domain.model.customer.CusShareUserBean;
import com.banger.mobile.ibatis.GenericDaoiBatis;



public class CrmCustomerShareDaoiBatis extends GenericDaoiBatis implements CrmCustomerShareDao {

	public CrmCustomerShareDaoiBatis() {
        super(CrmCustomerShare.class);
    }
	
	public CrmCustomerShareDaoiBatis(Class persistentClass) {
		super(CrmCustomerShare.class);
	}
	/**
	 * 客户所共享的用户集合
	 */
    public List<CusShareUserBean> getShareUsersByCusId(String cusId){
    	return this.getSqlMapClientTemplate().queryForList("getShareUsersByCusId", cusId);
    }
    /**
     * 新增共享客户
     */
    public void addShareCustomer(CrmCustomerShare crmCustomerShare){
    	this.getSqlMapClientTemplate().insert("insertCrmCustomerShare", crmCustomerShare);
    }
    /**
     * 删除重复的记录
     */
    public void delRepeatShare(){
    	this.getSqlMapClientTemplate().delete("delRepeatShare", "");
    }
    /**
     * 取消共享
     */
    public void cancelShare(Map<String,String> map){
    	this.getSqlMapClientTemplate().delete("cancelCustomerShare", map);
    }
    /**
     * 共享用户下拉框
     */
    public List<CusShareUserBean> selectCustomerShareUser(String userIds){
    	Map<String,String> map = new HashMap<String, String>();
    	map.put("UserId", userIds);
    	return this.getSqlMapClientTemplate().queryForList("selectCustomerShareUser", map);
    }
    /**
     * 共享用户下拉框      toMe
     */
    public List<CusShareUserBean> selectShareToMeUser(String shareUsserIds, String userId){
    	Map<String,String> map = new HashMap<String, String>();
    	map.put("shareUserId", shareUsserIds);
    	map.put("UserId", userId);
    	return this.getSqlMapClientTemplate().queryForList("selectShareToMeUser", map);
    }
    /**
     * 人员机构 更改归属 删除共享信息
     */
  	public void cancelShareInfoByUserIds(String userIds){
  		Map<String,String> map = new HashMap<String, String>();
    	map.put("userIds", userIds);
    	this.getSqlMapClientTemplate().delete("cancelCustomerShare", map);
  	}
}
