/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :共享客户
 * Author     :xuhj
 * Version    :1.0
 * Create Date:May 3, 2012
 */
package com.banger.mobile.facade.customer;

import java.util.List;

import com.banger.mobile.domain.model.customer.CrmCustomerShare;
import com.banger.mobile.domain.model.customer.CusShareUserBean;

public interface CrmCustomerShareService {
    
	/**
	 * 客户所共享的用户集合
	 * @param cusId
	 * @return
	 */
    public List<CusShareUserBean> getShareUsersByCusId(String cusId);
    /**
     * 新增共享客户
     * @param crmCustomerShare
     */
    public void addShareCustomer(CrmCustomerShare crmCustomerShare);
    /**
     * 删除重复的记录
     */
    public void delRepeatShare();
    /**
     * 取消共享用户
     * @param userIds
     * @param cusIds
     */
    public void cancelShareUser(String userIds ,String cusIds);
    /**
     * 取消共享客户
     * @param cusIds
     */
    public void cancelShareCus(String cusIds);
    /**
     * 共享用户下拉框      toOthers
     * @param userIds
     * @return
     */
    public List<CusShareUserBean> selectCustomerShareUser(String userIds);
    /**
     * 共享用户下拉框      toMe
     * @param shareUsserIds
     * @param userId
     * @return
     */
    public List<CusShareUserBean> selectShareToMeUser(String shareUsserIds, String userId);
    /**
     * 人员机构 更改归属 删除共享信息
     * @param userIds
     */
  	public void cancelShareInfoByUserIds(String userIds);
}
