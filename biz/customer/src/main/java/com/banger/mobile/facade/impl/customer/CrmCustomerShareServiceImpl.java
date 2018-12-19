/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :录音信息业务实现类
 * Author     :zhangxiang
 * Version    :1.0
 * Create Date:May 3, 2012
 */
package com.banger.mobile.facade.impl.customer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.banger.mobile.dao.customer.CrmCustomerShareDao;
import com.banger.mobile.domain.model.customer.CrmCustomerShare;
import com.banger.mobile.domain.model.customer.CusShareUserBean;
import com.banger.mobile.facade.customer.CrmCustomerShareService;


public class CrmCustomerShareServiceImpl implements CrmCustomerShareService {
	
	private CrmCustomerShareDao crmCustomerShareDao;
	
	public CrmCustomerShareDao getCrmCustomerShareDao() {
		return crmCustomerShareDao;
	}
	
	public void setCrmCustomerShareDao(CrmCustomerShareDao crmCustomerShareDao) {
		this.crmCustomerShareDao = crmCustomerShareDao;
	}  
	/**
	 * 客户所共享的用户集合
	 */
    public List<CusShareUserBean> getShareUsersByCusId(String cusId){
    	return crmCustomerShareDao.getShareUsersByCusId(cusId);
    }
    /**
     * 新增共享客户
     */
    public void addShareCustomer(CrmCustomerShare crmCustomerShare){
    	crmCustomerShareDao.addShareCustomer(crmCustomerShare);
    }
    /**
     * 删除重复的记录
     */
    public void delRepeatShare(){
    	crmCustomerShareDao.delRepeatShare();
    }
    /**
     * 取消共享用户
     */
    public void cancelShareUser(String userIds ,String cusIds){
    	//拼接shareSql
    	Map<String,String> map = new HashMap<String,String>();
    	String shareSql = "";
    	String[] users = userIds.split(",");
    	String[] cuss = cusIds.split(",");
    	for(String cus: cuss){
    		for(String user: users){
    			if(shareSql.equals("")){
    				shareSql = "(CUSTOMER_ID=" + cus + " and SHARE_USER_ID=" + user + ")" +
    							" or (CUSTOMER_ID=" + cus + " and USER_ID=" + user + ")";
    			}else{
    				shareSql = shareSql + " or (CUSTOMER_ID=" + cus + " and SHARE_USER_ID=" + user + ")" +
							" or (CUSTOMER_ID=" + cus + " and USER_ID=" + user + ")";
    			}
    		}
    	}
    	map.put("shareSql", shareSql);
    	crmCustomerShareDao.cancelShare(map);
    }
    /**
     * 取消共享客户
     */
    public void cancelShareCus(String cusIds){
    	Map<String,String> map = new HashMap<String,String>();
    	map.put("customerIds", cusIds);
    	crmCustomerShareDao.cancelShare(map);
    }
    /**
     * 共享用户下拉框
     */
    public List<CusShareUserBean> selectCustomerShareUser(String userIds){
    	return crmCustomerShareDao.selectCustomerShareUser(userIds);
    }
    /**
     * 共享用户下拉框      toMe
     */
    public List<CusShareUserBean> selectShareToMeUser(String shareUsserIds, String userId){
    	return crmCustomerShareDao.selectShareToMeUser(shareUsserIds, userId);
    }
    /**
     * 人员机构 更改归属 删除共享信息
     */
  	public void cancelShareInfoByUserIds(String userIds){
  		crmCustomerShareDao.cancelShareInfoByUserIds(userIds);
  	}
}
    
