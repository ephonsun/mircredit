/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :客户管理
 * Author     :xuhj
 * Version    :1.0
 * Create Date:May 2, 2012
 */
package com.banger.mobile.dao.customer;

import java.util.List;
import java.util.Map;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.model.base.customer.BaseCrmCustomer;
import com.banger.mobile.domain.model.customer.CrmCustomer;
import com.banger.mobile.domain.model.customer.CrmCustomerBirthday;
import com.banger.mobile.domain.model.customer.CrmCustomerEva;
import com.banger.mobile.domain.model.customer.CrmExportBean;

public interface CrmCustomerDao {
	/**
	 * 添加客户
	 * 
	 * @param crmCustomer
	 */
	public void addCrmCustomer(BaseCrmCustomer crmCustomer);

	/**
	 * 删除客户
	 * 
	 * @param id
	 */
	public void deleteCrmCustomer(Integer id);

	/**
	 * 修改客户
	 * 
	 * @param crmCustomer
	 */
	public void updateCrmCustomer(BaseCrmCustomer crmCustomer);

	/**
	 * 根据客户ID获取客户实体
	 * 
	 * @param id
	 * @return
	 */
	public CrmCustomer getCrmCustomerById(Integer id);

	/**
	 * 根据客户编号得到客户实体
	 * 
	 * @param customerNo
	 * @return
	 */
	public CrmCustomer getCustomerByCustomerNo(String customerNo);

	/**
	 * 分页查询
	 * 
	 * @param parameters
	 * @param page
	 * @return
	 */
	public PageUtil<CrmCustomer> getCrmCustomerPage(
			Map<String, Object> parameters, Page page);

	/**
	 * 风险测评分页查询
	 * 
	 * @param parameters
	 * @param page
	 * @return
	 */
	public PageUtil<CrmCustomerEva> getCustomerPage(
			Map<String, Object> parameters, Page page);

	/**
	 * 设置客户的客户类型ID为O
	 * 
	 * @param customerTypeId
	 */
	public void updateCrmCustomerByCrmCustomerTypeById(Integer customerTypeId);

	/**
	 * 搜索客户PAD
	 * 
	 * @param parameters
	 * @return
	 */
	public List<CrmCustomer> getCrmCustomerForPad(Map<String, String> parameters);

	/**
	 * 搜索我的客户PAD
	 * 
	 * @param parameters
	 * @return
	 */
	public List<CrmCustomer> getMyCrmCustomerForPad(
			Map<String, String> parameters);

	/**
	 * 设置客户的所处行业ID为O
	 * 
	 * @param customerIndustryId
	 */
	public void updateCrmCustomerByCustomerIndustryId(Integer customerIndustryId);

	/**
	 * 设置拜访客户
	 * 
	 * @param CrmCustomer
	 */
	public void updateCrmCustomerByVisit(CrmCustomer crmCustomer);

	/**
	 * 根据电话号码查询客户
	 */
	public List<CrmCustomer> getCustomersByTel(String tel);

	/**
	 * 根据电话号码查询客户
	 */
	public List<CrmCustomer> getCustomersByTel(String tel, String cityCode);

	/**
	 * 删除客户至垃圾箱
	 * 
	 * @param customerIds
	 */
	public void delCustomerByCustomerIds(String customerIds);

	/**
	 * 清空垃圾箱
	 * 
	 * @param customerIds
	 */
	public void delCustomersComplete(Map<String, String> parameters);

	/**
	 * 还原垃圾箱
	 * 
	 * @param customerIds
	 */
	public void restoreCustomerByCustomerIds(Map<String, String> parameters);

	/**
	 * 彻底删除客户
	 * 
	 * @param parameters
	 */
	public void delCusComplete(String customerIds);

	/**
	 * 还原客户
	 * 
	 * @param parameters
	 */
	public void restoreCustomers(String customerIds);

	/**
	 * 更新最近联系时间
	 * 
	 * @param parameters
	 */
	public void updateLastContactDate(Map<String, String> parameters);

	/**
	 * 设置客户表的归属用户ID为0
	 * 
	 * @param belongUserId
	 */
	public void updateCrmCustomerBybelongUserId(Integer belongUserId);

	/**
	 * 根据客户ids查询客户集合
	 * 
	 * @param customerIds
	 * @return
	 */
	public List<CrmCustomer> selectCusByIds(String customerIds);

	/**
	 * 根据客户NOS查询客户集合
	 * 
	 * @param customerNos
	 * @return
	 */
	public List<CrmCustomer> selectCusByNos(String customerNos);

	/**
	 * 得到最近10条联系客户
	 * 
	 * @param condition
	 * @return
	 */
	public List<CrmCustomer> getRecentlyCustomers(Map<String, Object> condition);

	/**
	 * 更改客户归属
	 * 
	 * @param map
	 */
	public void modifyCusBelongTo(Map<String, String> map);

	/**
	 * 更改客户归属
	 * 
	 * @param map
	 */
	public void modifyCusBelongToAll(Map<String, String> map);

	/**
	 * 根据号码查询客户 -------不包含权限
	 * 
	 * @param phoneNumber
	 * @return
	 */
	public List<CrmCustomer> selectCustomerByPhone(String phoneNumber);

	List<BaseCrmCustomer> getCusByConditionByMap(Map<String, Object> map);

	/**
	 * 根据用户ids查询客户集合
	 * 
	 * @param userid
	 * @return
	 */
	public List<CrmCustomer> getCustomersByUserId(String userid);

	/**
	 * 是否是共享客户
	 * 
	 * @param cusId
	 * @param userId
	 * @return
	 */
	public Boolean checkShareCus(Integer cusId, String userids);

	/**
	 * 查询导出客户
	 * 
	 * @param parameters
	 * @return
	 */
	public List<CrmExportBean> queryExportCustomer(
			Map<String, Object> parameters);

	/**
	 * 查询生日提醒客户
	 * 
	 * @param parameters
	 * @param page
	 * @return
	 */
	public PageUtil<CrmCustomerBirthday> queryBirthdayRemind(
			Map<String, Object> parameters, Page page);

	/**
	 * 修改客户号码
	 * 
	 * @param customer
	 */
	public void updateCusPhoneNumber(BaseCrmCustomer customer);

	/**
	 * 得到下一个新建客户ID
	 * 
	 * @return
	 */
	public Integer getNextCustomerId();

	/**
	 * 保存用户集合
	 */
	public void addCrmCustomerBatch(List<CrmCustomer> cusList);

	/**
	 * 更新用户集合
	 */
	public void updateCrmCustomerBatch(List<CrmCustomer> cusList);

	/**
	 * 任务管理查询下次联系客户
	 * 
	 * @param contactId
	 * @return
	 */
	public CrmCustomer getCustomerForTskNextContact(Integer contactId);

	/**
	 * 用户更改归属 修改客户归属
	 * 
	 * @param userid
	 * @param deptid
	 */
	public void setCustomerOfChangeBelong(String userid, String deptid);

	/**
	 * 通过条件查询客户ID集合
	 * 
	 * @return
	 */
	public List<Integer> getCusByCondition(Map<String, Object> map);

	/**
	 * 更新客户头像
	 * 
	 * @param map
	 */
	public void updateCrmCustomerHeadShow(Map<String, Object> map);

    
    /**
     * 通过客户姓名和号码获取客户
     * @param map
     * @return
     */
    public List<CrmCustomer> getCusByCusNamesAndPhones(Map<String,Object> map);

    /**
     * 更新客户为不良客户
     * @param customerParamMap
     */
    public void updateCrmCustomerIsNogood(Map<String, Object> customerParamMap);

    /**
     * 更新客户为地址
     * @param customerParamMap
     */
    void updateCrmCustomerAddress(Map<String,Object> customerParamMap);

    Boolean checkIsNogoodCus(Map<String, Object> paramMap);

    List<BaseCrmCustomer> selectIdCardListByIdCard(Map<String, Object> paramMap);

    Integer updateImpCrmCustomer(CrmCustomer crmCustomer);

    /**
     * 通过客户姓名和电话号码查找
     * @param params
     * @return
     */
	public List<BaseCrmCustomer> getCrmCustomerByCrm(Map<String, Object> params);

    Integer updateCustomerRandom(CrmCustomer crmCustomer);

    /**
     * 根据客户姓名和身份证ID
     * @param map
     * @return
     */
    List<BaseCrmCustomer> getCromCustomerByIdcard(Map<String,Object> map);

    /**
     * 根据客户码查找客户
     * @param customerNo
     * @return
     */
    List<BaseCrmCustomer> getCrmCustomerByCHM(String customerNo);

    /**
     * 获取所有拼音为空的记录
     * @return
     */
    List<BaseCrmCustomer> getCrmCustomerByPYisNull(Map<String,Object> map);

    Integer getCrmCustomerByPYisNullCount();
}
