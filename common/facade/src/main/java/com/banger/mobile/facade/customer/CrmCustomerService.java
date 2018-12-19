/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :客户管理
 * Author     :xuhj
 * Version    :1.0
 * Create Date:May 3, 2012
 */
package com.banger.mobile.facade.customer;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.model.base.customer.BaseCrmCustomer;
import com.banger.mobile.domain.model.base.customer.BaseFamilyName;
import com.banger.mobile.domain.model.customer.CrmCustomer;
import com.banger.mobile.domain.model.customer.CrmCustomerBirthday;
import com.banger.mobile.domain.model.customer.CrmCustomerEva;
import com.banger.mobile.domain.model.customer.CrmExportBean;
import com.banger.mobile.domain.model.customer.CustomerExtendFieldBean;

public interface CrmCustomerService {
    
	/**
	 * 新增客户
	 * @param crmCustomer
	 */
    public void addCrmCustomer(CrmCustomer crmCustomer);
    /**
     * 删除客户
     * @param id
     */
    public void deleteCrmCustomer(Integer id);
    /**
     * 修改客户
     * @param crmCustomer
     */
    public void updateCrmCustomer(BaseCrmCustomer crmCustomer);
    /**
     * 修改客户电话号码
     * @param crmCustomer
     */
    public void updateCusPhoneNumber(BaseCrmCustomer crmCustomer);
    /**
     * 根据客户ID获取客户实体
     * @param id
     * @return
     */
    public BaseCrmCustomer getCrmCustomerById(Integer id);
    /**
     * 分页查询
     * @param parameters
     * @param page
     * @return
     */
    public PageUtil<CrmCustomer> getCrmCustomerPage(Map<String, Object> parameters, Page page);
    /**
     * 设置客户的客户类型ID为O
     * @param customerTypeId
     */
    public void updateCrmCustomerByCrmCustomerTypeById(Integer customerTypeId);
    /**
     * 搜索客户PAD
     * @param account
     * @param input
     * @return
     */
    public List<CrmCustomer> getCrmCustomerForPad(String account, String input);
    /**
     * 搜索我的客户PAD
     * @param account
     * @return
     */
    public List<CrmCustomer> getMyCrmCustomerForPad(String account);
    /**
     * PAD 根据客户ID得到实体
     * @param customeId
     * @return
     */
    public CrmCustomer getCustomerInfo(Integer customeId);
    /**
     * 根据客户编号得到客户实体
     * @param customerNo
     * @return
     */
    public CrmCustomer getCustomerByCustomerNo(String customerNo);
    /**
     * 设置客户的所处行业ID为O
     * @param customerIndustryId
     */
    public void updateCrmCustomerByCustomerIndustryId(Integer customerIndustryId);
    /**
     * 设置拜访客户
     * @param crmCustomer
     */
    public void updateCrmCustomerByVisit(CrmCustomer crmCustomer);
    /**
     * 新增客户自定义信息
     * @param parameters
     */
    public void addCustomizedField(Map<String, String> parameters);
    /**
     * 删除客户自定义信息
     * @param id
     */
    public void deleteCustomizedField(Integer id);
    /**
     * 修改客户自定义信息
     * @param parameters
     */
    public void updateCustomizedField(Map<String, String> parameters);
    /**
     * 根据客户ID获取客户自定义实体
     * @param id
     * @return
     */
    public CustomerExtendFieldBean getCustomizedFieldById(Integer id);
    /**
     * 获取客户百家姓
     * @return
     */
    public List<BaseFamilyName> getNickName();
    /**
     * 删除客户至垃圾箱
     * @param customerIds
     */
    public void delCustomerByCustomerIds(String customerIds);
    /**
     * 清空垃圾箱
     * @param parameters
     */
    public void delCustomersComplete(Map<String, String> parameters);
    /**
     * 还原垃圾箱
     * @param parameters
     */
    public void restoreCustomerByCustomerIds(Map<String, String> parameters);
    /**
     * 彻底删除客户
     * @param customerIds
     */
    public void delCusComplete(String customerIds);
    /**
     * 还原客户
     * @param customerIds
     */
    public void restoreCustomers(String customerIds);
    /**
     * 是否有该客户的权限
     * @param crmCustomer
     * @param arr 有权限的deptids集合
     * @return
     */
    public Integer checkPermission(CrmCustomer crmCustomer, Integer[] arr);
    /**
     * 客户是否已彻底删除
     * @param customerId
     * @return
     */
    public Boolean isDelCustomer(Integer customerId);
    /**
     * 更新最近联系时间
     * @param customerId
     * @param lastContactDate
     * @param lastContactType
     */
    public void updateLastContactDate(Integer customerId, Date lastContactDate, String lastContactType);
    /**
     * 设置客户表的归属用户ID为0
     * @param belongUserId
     */
    public void updateCrmCustomerBybelongUserId(Integer belongUserId);
    /**
     * 风险测评分页查询
     * @param parameters
     * @param page
     * @return
     */
    public PageUtil<CrmCustomerEva> getCustomerPage(Map<String, Object> parameters, Page page);
    /**
     * 根据客户ids查询客户集合
     * @param customerIds
     * @return
     */
    public List<CrmCustomer> selectCusByIds(String customerIds);
    /**
     * 把对象转化成map
     * @param obj
     * @return
     */
    public Map<String, Object> getMap(Object obj);
    /**
     * 提取每个汉字的首字母 
     * @param str
     * @return
     */
    public String getPinYinHeadChar(String str);
    /**
     * 更改客户归属
     * @param map
     */
    public void modifyCusBelongTo(Map<String, String> map);
    
    /**
     * 更改客户归属
     * @param map
     */
    public void modifyCusBelongToAll(Map<String, String> map);
    
    /**
     * 根据号码查询客户 -------不包含权限
     * @param phoneNumber
     * @return
     */
    public List<CrmCustomer> selectCustomerByPhone(String phoneNumber);
    /**
     * 根据用户ids查询客户集合
     * @param userid
     * @return
     */
    public List<CrmCustomer> getCustomersByUserId(String userid);
    /**
     * 是否是共享客户
     * @param cusId
     * @param userId
     * @return
     */
    public Boolean checkShareCus(Integer cusId, Integer userid);
    /**
     * 保存客户共享
     * @param map
     * @return
     */
    public String saveShareCustomer(Map<String, String> map);
    /**
     * 工作托管
     * @param userId
     * @param shareUserId
     */
  	public void workTrusteeship(String userId, String shareUserId);
  	/**
  	 * 取消工作托管
  	 * @param userId
  	 */
  	public void cancelWorkTrusteeship(String userId);
  	/**
  	 * 人员机构 更改归属 删除共享信息
  	 * @param userIds
  	 */
  	public void cancelShareInfoByUserIds(String userIds);
  	/**
  	 * 查询导出客户
  	 * @param parameters
  	 * @param startRow
  	 * @param endRow
  	 * @return
  	 */
  	public List<CrmExportBean> queryExportCustomer(Map<String, Object> parameters, int startRow, int endRow);
  	
  	public String saveWebCustomer(String account, CrmCustomer crmCustomer);
  	
  	/**
  	 * 保存客户全部信息
  	 * @param crmCustomer
  	 * @param exField
  	 */
  	public void saveCustomer(CrmCustomer crmCustomer, CustomerExtendFieldBean exField);
  	/**
  	 * 查询生日提醒客户
  	 * @param page
  	 * @return
  	 */
  	public PageUtil<CrmCustomerBirthday> queryBirthdayRemind(Page page);
  	/**
  	 * 得到最近10条联系客户
  	 * @param condition
  	 * @return
  	 */
  	public List<CrmCustomer> getRecentlyCustomers(Map<String, Object> condition);
  	/**
  	 * 得到下一个新建客户ID
  	 * @return
  	 */
  	public Integer getNextCustomerId();
  	/**
  	 * 根据客户NOS查询客户集合
  	 * @param customerNos
  	 * @return
  	 */
    public List<CrmCustomer> selectCusByNos(String customerNos);
    /**
     * 任务管理查询下次联系客户
     * @param contactId
     * @return
     */
    public CrmCustomer getCustomerForTskNextContact(Integer contactId);
    /**
     * 用户更改归属 修改客户归属
     * @param userid
     * @param deptid
     */
    public void setCustomerOfChangeBelong(String userid, String deptid);
    /**
     * 通过条件查询客户ID集合
     * @return
     */
    public List<Integer> getCusByCondition(Map<String, Object> map);
    /**
     * 更新客户头像
     * @param map  key: headshow customerId
     */
    public void updateCrmCustomerHeadShow(Map<String, Object> map);
    
    /**
     * 通过客户姓名或号码获取客户
     * @param map
     * @return
     */
    public List<CrmCustomer> getCusByCusNameOrPhone(Map<String, String> map);

    /**
     * 更新客户为不良客户
     * @param customerParamMap
     */
    public void updateCrmCustomerIsNogood(Map<String, Object> customerParamMap);

    /**
     * 更新客户地址
     * @param customerParamMap
     */
    public void updateCrmCustomerAddress(Map<String, Object> customerParamMap);

    Boolean checkIsNogoodCus(Map<String, Object> paramMap);
    /**
     * 通过电话号码查找客户
     * @param phoneNum
     * @return
     */
    public List<CrmCustomer> getCustomersByTel(String phoneNum);
    
    /**
	 * 根据客户ID得到客户实体
	 */
	public CrmCustomer getCustomerById(Integer id);

    void crmImportBatch(List<CrmCustomer> customerList,List<CrmCustomer> customerListForInsert);

    void updateCustomerBatch(List<CrmCustomer> customerList);

    List<BaseCrmCustomer> selectIdCardListByIdCard(Map<String, Object> paramMap);

    Integer updateImpCrmCustomer(CrmCustomer crmCustomer);
    
    public List<BaseCrmCustomer> getCrmCustomerByCrm(Map<String,Object>params);

    Integer updateCustomerRandom(CrmCustomer crmCustomer);
    
    String checkCustomerNo(String customerNo,Integer customerId,Integer userId);

    void setCustomerNickName(BaseCrmCustomer crmCustomer);

    void matchCustomerInfo(BaseCrmCustomer crmCustomer, String customerName, String idCard, String phone);

    List<BaseCrmCustomer> getCrmCustomerByIdCard(Map<String,Object> map);

    List<BaseCrmCustomer> getCrmCustomerByKHM(String customerNo);

    List<BaseCrmCustomer> getCrmCustomerByPYisNull(Map<String,Object> map);

    Integer getCrmCustomerByPYisNullCount();

    List<BaseCrmCustomer> getCusByConditionByMap(Map<String, Object> map);
}
