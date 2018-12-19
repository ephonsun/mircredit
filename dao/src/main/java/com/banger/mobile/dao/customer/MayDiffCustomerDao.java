/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :合并客户
 * Author     :zhangxiang
 * Version    :1.0
 * Create Date:May 2, 2012
 */
package com.banger.mobile.dao.customer;

import java.util.List;
import java.util.Map;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.model.customer.CrmCustomer;
import com.banger.mobile.domain.model.customer.MayDiffCustomerName;

public interface MayDiffCustomerDao {
	
    /**
     * 分页查询
     * @param parameters
     * @param page
     * @return
     */
    public PageUtil<MayDiffCustomerName> getMayDiffCustomerNamePage(Map<String, Object> parameters, Page page);

    /**
     * 取得相同客户
     * @param parameters
     * @return
     */
    public List<CrmCustomer> getSameCustomerList(Map<String, Object> parameters);

    /**
     * 合并客户联系记录
     * @param p1
     */
    public void mergeRecordInfo(Map<String, Object> p1);

    /**
     * 伪删除客户
     * @param customerId
     */
    public void deleteCrmCustomer(Integer customerId);

    /**
     * 合并任务
     * @param p2
     */
    public void mergeTask(Map<String, Object> p2);
    
    /**
     * 合并计划
     * @param p2
     */
    public void mergePlan(Map<String, Object> p2);

    /**
     * 合并亲友
     * @param p3
     */
    public void mergeFriends(Map<String, Object> p3);
    
    /**
     * 合并短信
     * @param p4
     */
    public void mergeSMS(Map<String, Object> p4);

    /**
     * 合并彩信
     * @param p5
     */
    public void mergeMMS(Map<String, Object> p5);

    /**
     * 合并产品购买记录
     * @param p6
     */
    public void mergeProductCustomer(Map<String, Object> p6);

    /**
     * 手机相同    分页查询 
     * @param parameters
     * @param page
     * @return
     */
    public PageUtil<MayDiffCustomerName> getMayDiffCustomerPhonePage(Map<String, Object> parameters, Page page);

    /**
     * 姓名固话相同    分页查询 
     * @param parameters
     * @param page
     * @return
     */
    public PageUtil<MayDiffCustomerName> getMayDiffCustomerNamePhonePage(Map<String, Object> parameters, Page page);

    /**
     * 姓名单位相同    分页查询 
     * @param parameters
     * @param page
     * @return
     */
    public PageUtil<MayDiffCustomerName> getMayDiffCustomerNameCompanyPage(Map<String, Object> parameters, Page page);

    /**
     * 身份证相同    分页查询 
     * @param parameters
     * @param page
     * @return
     */
    public PageUtil<MayDiffCustomerName> getMayDiffCustomerIdcardPage(Map<String, Object> parameters, Page page);

    /**
     * 联系记录数量
     * @param customerId
     * @return
     */
    public int countRecordInfo(Integer customerId);

    /**
     * 任务数量
     * @param customerId
     * @return
     */
    public int countTask(Integer customerId);

    /**
     * 亲友数量
     * @param customerId
     * @return
     */
    public int countRelative(Integer customerId);

    /**
     * 产品数量
     * @param customerId
     * @return
     */
    public int countProduct(Integer customerId);

    /**
     * 相同姓名固话数量
     * @param parameterMap
     * @return
     */
    public int getMayDiffCustomerNamePhoneNumber(Map<String, Object> parameterMap);

    /**
     * 相同姓名公司数量
     * @param parameterMap
     * @return
     */
    public int getMayDiffCustomerNameCompanyNumber(Map<String, Object> parameterMap);

    /**
     * 相同身份证数量
     * @param parameterMap
     * @return
     */
    public int getMayDiffCustomerIdCardNumber(Map<String, Object> parameterMap);

    /**
     * 相同姓名数量
     * @param parameterMap
     * @return
     */
    public int getMayDiffCustomerPhoneNumber(Map<String, Object> parameterMap);

    /**
     * 相同姓名数量
     * @param parameterMap
     * @return
     */
    public int getMayDiffCustomerNameNumber(Map<String, Object> parameterMap);

    /**
     * 删除客户共享信息
     * @param parameterMap
     */
    public void deleteCustomerSharedInfo(Map<String, Object> parameterMap);
    
}
