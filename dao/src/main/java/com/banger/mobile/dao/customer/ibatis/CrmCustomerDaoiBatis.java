/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :客户管理
 * Author     :xuhj
 * Version    :1.0
 * Create Date:May 2, 2012
 */
package com.banger.mobile.dao.customer.ibatis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.dao.customer.CrmCustomerDao;
import com.banger.mobile.domain.model.base.customer.BaseCrmCustomer;
import com.banger.mobile.domain.model.customer.CrmCustomer;
import com.banger.mobile.domain.model.customer.CrmCustomerBirthday;
import com.banger.mobile.domain.model.customer.CrmCustomerEva;
import com.banger.mobile.domain.model.customer.CrmExportBean;
import com.banger.mobile.ibatis.GenericDaoiBatis;
import com.banger.mobile.util.StringUtil;

public class CrmCustomerDaoiBatis extends GenericDaoiBatis implements
		CrmCustomerDao {

	public CrmCustomerDaoiBatis() {
		super(CrmCustomer.class);
	}

	public CrmCustomerDaoiBatis(Class persistentClass) {
		super(CrmCustomer.class);
	}

	/**
	 * 添加客户
	 *
	 * @param crmCustomer
	 */
	public void addCrmCustomer(BaseCrmCustomer crmCustomer) {
		updateMemoFiled(crmCustomer);
		this.getSqlMapClientTemplate().insert("addCrmCustomer", crmCustomer);
	}

	/**
	 * 删除客户
	 *
	 * @param id
	 */
	public void deleteCrmCustomer(Integer id) {
		this.getSqlMapClientTemplate().delete("deleteCrmCustomerById", id);
	}

	/**
	 * 修改客户
	 *
	 * @param crmCustomer
	 */
	public void updateCrmCustomer(BaseCrmCustomer crmCustomer) {
		updateMemoFiled(crmCustomer);
		this.getSqlMapClientTemplate().update("updateCrmCustomer", crmCustomer);
	}

	/**
	 * 更新冗余字段memo
	 *
	 * @param crmCustomer
	 */
	private void updateMemoFiled(BaseCrmCustomer crmCustomer) {
		String memo = "";
		memo = (StringUtil.isEmpty(crmCustomer.getCustomerName()) ? ""
				: crmCustomer.getCustomerName())
				+ ","
				+ (StringUtil.isEmpty(crmCustomer.getCustomerNamePinyin()) ? ""
				: crmCustomer.getCustomerNamePinyin())
				+ ","
				+ (StringUtil.isEmpty(crmCustomer.getCustomerNo()) ? ""
				: crmCustomer.getCustomerNo())
				+ ","
				+ (StringUtil.isEmpty(crmCustomer.getIdCard()) ? ""
				: crmCustomer.getIdCard())
				+ ","
				+ (StringUtil.isEmpty(crmCustomer.getCompany()) ? ""
				: crmCustomer.getCompany())
				+ ","
				+ (StringUtil.isEmpty(crmCustomer.getMobilePhone1()) ? ""
				: crmCustomer.getMobilePhone1())
				+ ","
				+ (StringUtil.isEmpty(crmCustomer.getMobilePhone2()) ? ""
				: crmCustomer.getMobilePhone2())
				+ ","
				+ (StringUtil.isEmpty(crmCustomer.getPhone()) ? ""
				: crmCustomer.getPhone())
				+ ","
				+ (StringUtil.isEmpty(crmCustomer.getFax()) ? "" : crmCustomer
				.getFax());
		crmCustomer.setMemo(memo);
	}

	/**
	 * 根据客户ID获取客户实体
	 *
	 * @param id
	 * @return
	 */
	public CrmCustomer getCrmCustomerById(Integer id) {
		return (CrmCustomer) this.getSqlMapClientTemplate().queryForObject(
				"getCrmCustomerById", id);
	}

	/**
	 * 根据客户编号得到客户实体
	 *
	 * @param customerNo
	 * @return
	 */
	public CrmCustomer getCustomerByCustomerNo(String customerNo) {
		return (CrmCustomer) this.getSqlMapClientTemplate().queryForObject(
				"getCustomerByCustomerNo", customerNo);
	}

	/**
	 * 分页查询
	 *
	 * @param parameters
	 * @param page
	 * @return
	 */
	public PageUtil<CrmCustomer> getCrmCustomerPage(
			Map<String, Object> parameters, Page page) {
		List<CrmCustomer> list = (List<CrmCustomer>) this.findQueryPage(
				"getCrmCustomerParameterPageMap", "getCrmCustomerCount",
				parameters, page);
		if (list == null) {
			list = new ArrayList<CrmCustomer>();
		}
		return new PageUtil<CrmCustomer>(list, page);
	}

	/**
	 * 设置客户的客户类型ID为O
	 *
	 * @param customerTypeId
	 */
	public void updateCrmCustomerByCrmCustomerTypeById(Integer customerTypeId) {
		this.getSqlMapClientTemplate().update(
				"updateCrmCustomerByCrmCustomerTypeById", customerTypeId);
	}

	/**
	 * 搜索客户PAD
	 *
	 * @param parameters
	 * @return
	 */
	public List<CrmCustomer> getCrmCustomerForPad(Map<String, String> parameters) {
		return this.getSqlMapClientTemplate().queryForList(
				"getCrmCustomerForPad", parameters);
	}

	/**
	 * 搜索客户PAD
	 *
	 * @param parameters
	 * @return
	 */
	public List<CrmCustomer> getMyCrmCustomerForPad(
			Map<String, String> parameters) {
		return this.getSqlMapClientTemplate().queryForList(
				"getCrmCustomerParameterPageMap", parameters);
	}

	/**
	 * 设置客户的所处行业ID为O
	 *
	 * @param customerIndustryId
	 */
	public void updateCrmCustomerByCustomerIndustryId(Integer customerIndustryId) {
		this.getSqlMapClientTemplate().update(
				"updateCrmCustomerByCustomerIndustryId", customerIndustryId);
	}

	/**
	 * 设置拜访客户
	 *
	 * @param crmCustomer
	 */
	public void updateCrmCustomerByVisit(CrmCustomer crmCustomer) {
		this.getSqlMapClientTemplate().update("updateCrmCustomerByVisit",
				crmCustomer);
	}

	/**
	 * 根据电话号码查询客户
	 */
	@SuppressWarnings("unchecked")
	public List<CrmCustomer> getCustomersByTel(String tel) {
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("tel", tel);
		List<CrmCustomer> custs = this.getSqlMapClientTemplate().queryForList(
				"getCustomersByTel", condition);
		return custs;
	}

	/**
	 * 根据电话号码查询客户
	 */
	@SuppressWarnings("unchecked")
	public List<CrmCustomer> getCustomersByTel(String tel, String cityCode) {
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("tel", tel);
		if (cityCode != null && !"".equals(cityCode))
			condition.put("fulltel", cityCode + tel);
		List<CrmCustomer> custs = this.getSqlMapClientTemplate().queryForList(
				"getCustomersByTel", condition);
		return custs;
	}

	/**
	 * 删除客户至垃圾箱
	 *
	 * @param customerIds
	 */
	public void delCustomerByCustomerIds(String customerIds) {
		this.getSqlMapClientTemplate().update("Update_DeleteCustomers",
				customerIds);
	}

	/**
	 * 清空垃圾箱
	 *
	 * @param parameters
	 */
	public void delCustomersComplete(Map<String, String> parameters) {
		this.getSqlMapClientTemplate()
				.update("Update_CleanDustbin", parameters);
	}

	/**
	 * 还原垃圾箱
	 *
	 * @param parameters
	 */
	public void restoreCustomerByCustomerIds(Map<String, String> parameters) {
		this.getSqlMapClientTemplate().update("Update_RestoreDustbin",
				parameters);
	}

	/**
	 * 彻底删除客户
	 *
	 * @param customerIds
	 */
	public void delCusComplete(String customerIds) {
		this.getSqlMapClientTemplate().update("Update_CompleteDeleteCustomers",
				customerIds);
	}

	/**
	 * 还原客户
	 *
	 * @param customerIds
	 */
	public void restoreCustomers(String customerIds) {
		this.getSqlMapClientTemplate().update("Update_RestoreCustomers",
				customerIds);
	}

	/**
	 * 更新最近联系时间
	 *
	 * @param parameters
	 */
	public void updateLastContactDate(Map<String, String> parameters) {
		this.getSqlMapClientTemplate().update("updateLastContactDate",
				parameters);
	}

	/**
	 * 设置客户表的归属用户ID为0
	 *
	 * @param belongUserId
	 */
	public void updateCrmCustomerBybelongUserId(Integer belongUserId) {
		this.getSqlMapClientTemplate().update(
				"updateCrmCustomerBybelongUserId", belongUserId);
	}

	/**
	 * 风险测评客户查询
	 *
	 * @param parameters
	 * @param page
	 * @return
	 * @see com.banger.mobile.dao.customer.CrmCustomerDao#getCustomerPage(java.util.Map,
	 *      com.banger.mobile.Page)
	 */
	public PageUtil<CrmCustomerEva> getCustomerPage(
			Map<String, Object> parameters, Page page) {
		List<CrmCustomerEva> list = (List<CrmCustomerEva>) this.findQueryPage(
				"getCustomerMap", "getCustomerCount", parameters, page);
		if (list == null) {
			list = new ArrayList<CrmCustomerEva>();
		}
		return new PageUtil<CrmCustomerEva>(list, page);
	}

	/**
	 * 根据客户ids查询客户集合
	 *
	 * @param customerIds
	 * @return
	 */
	public List<CrmCustomer> selectCusByIds(String customerIds) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("customerIds", customerIds);
		return this.getSqlMapClientTemplate().queryForList("selectCusByIds",
				map);
	}

	/**
	 * 更改客户归属
	 *
	 * @param map
	 */
	public void modifyCusBelongTo(Map<String, String> map) {
		this.getSqlMapClientTemplate().update("modifyCusBelongTo", map);
	}

	/**
	 * 更改客户归属
	 *
	 * @param map
	 */
	public void modifyCusBelongToAll(Map<String, String> map) {
		this.getSqlMapClientTemplate().update("modifyCusBelongToAll", map);
	}

	/**
	 * 根据号码查询客户 -------不包含权限
	 */
	public List<CrmCustomer> selectCustomerByPhone(String phoneNumber) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("phoneNumber", phoneNumber);
		return this.getSqlMapClientTemplate().queryForList(
				"selectCustomerByPhone", map);
	}

	@Override
	public List<BaseCrmCustomer> getCusByConditionByMap(Map<String,Object> map) {
		return this.getSqlMapClientTemplate().queryForList( "getCusByConditionByMap", map);
	}

	/**
	 * 根据用户ids查询客户集合
	 */
	public List<CrmCustomer> getCustomersByUserId(String userid) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userid", userid);
		return this.getSqlMapClientTemplate().queryForList(
				"getCustomersByUserId", map);
	}

	/**
	 * 是否是共享客户
	 */
	public Boolean checkShareCus(Integer cusId, String userids) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("cusId", cusId.toString());
		map.put("userId", userids);
		Integer count = (Integer) this.getSqlMapClientTemplate()
				.queryForObject("checkShareCus", map);
		if (count.intValue() > 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 查询导出客户
	 */
	public List<CrmExportBean> queryExportCustomer(
			Map<String, Object> parameters) {
		return this.getSqlMapClientTemplate().queryForList(
				"queryExportCustomer", parameters);
	}

	/**
	 * 查询生日提醒客户
	 */
	public PageUtil<CrmCustomerBirthday> queryBirthdayRemind(
			Map<String, Object> parameters, Page page) {
		List<CrmCustomerBirthday> list = (List<CrmCustomerBirthday>) this
				.findQueryPage("queryBirthdayRemind", "getBirthdayRemindCount",
						parameters, page);
		if (list == null) {
			list = new ArrayList<CrmCustomerBirthday>();
		}
		return new PageUtil<CrmCustomerBirthday>(list, page);
	}

	/**
	 * 修改客户号码
	 */
	public void updateCusPhoneNumber(BaseCrmCustomer customer) {
		this.getSqlMapClientTemplate().update("updateCusPhoneNumber", customer);
	}

	/**
	 * 得到最近10条联系客户
	 *
	 * @param condition
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<CrmCustomer> getRecentlyCustomers(Map<String, Object> condition) {
		return this.getSqlMapClientTemplate().queryForList(
				"getRecentlyCustomers", condition);
	}

	/**
	 * 得到下一个新建客户ID
	 */
	public Integer getNextCustomerId() {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"getNextCustomerId");
	}

	/**
	 * 新增客户集合
	 *
	 * @param parameterList
	 * @see com.banger.mobile.dao.customer.CrmCustomerDao#addCrmCustomerBatch(java.util.List)
	 */
	public void addCrmCustomerBatch(List<CrmCustomer> parameterList) {
		for (CrmCustomer crmCustomer : parameterList) {
			updateMemoFiled(crmCustomer);
		}
		this.exectuteBatchInsert("addCrmCustomer", parameterList);
	}

	/**
	 * 根据客户NOS查询客户集合
	 *
	 * @param customerNos
	 * @return
	 * @see com.banger.mobile.dao.customer.CrmCustomerDao#selectCusByNos(java.lang.String)
	 */
	public List<CrmCustomer> selectCusByNos(String customerNos) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("customerNos", customerNos);
		return this.getSqlMapClientTemplate().queryForList("selectCusByNos",
				map);
	}

	/**
	 * 更新客户集合
	 *
	 * @param cusList
	 * @see com.banger.mobile.dao.customer.CrmCustomerDao#updateCrmCustomerBatch(java.util.List)
	 */
	public void updateCrmCustomerBatch(List<CrmCustomer> cusList) {
		for (CrmCustomer crmCustomer : cusList) {
			updateMemoFiled(crmCustomer);
		}
		this.executeBatchUpdate("updateImpCrmCustomer", cusList);
	}

	/**
	 * 任务管理查询下次联系客户
	 *
	 * @param contactId
	 * @return
	 */
	public CrmCustomer getCustomerForTskNextContact(Integer contactId) {
		return (CrmCustomer) this.getSqlMapClientTemplate().queryForObject(
				"getCustomerForTskNextContact", contactId);
	}

	/**
	 * 用户更改归属 修改客户归属
	 *
	 * @param userid
	 * @param deptid
	 */
	public void setCustomerOfChangeBelong(String userid, String deptid) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userid", userid);
		map.put("deptid", deptid);
		this.getSqlMapClientTemplate().update("setCustomerOfChangeBelong", map);
	}

	/**
	 * 通过条件查询客户ID集合
	 *
	 * @return
	 */
	public List<Integer> getCusByCondition(Map<String, Object> map) {
		return this.getSqlMapClientTemplate().queryForList("getCusByCondition",
				map);
	}

	/**
	 * 更新客户头像
	 *
	 * @param map
	 */
	public void updateCrmCustomerHeadShow(Map<String, Object> map) {
		this.getSqlMapClientTemplate().update("updateCrmCustomerHeadShow", map);
	}


	/**
	 * 通过客户姓名和号码获取客户
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<CrmCustomer> getCusByCusNamesAndPhones(Map<String,Object>map){
		return this.getSqlMapClientTemplate().queryForList("getCusByOrPhones",map);
	}
	/**
	 * 更新客户为不良客户
	 * @param customerParamMap
	 */
	public void updateCrmCustomerIsNogood(Map<String, Object> customerParamMap){
		this.getSqlMapClientTemplate().update("updateCrmCustomerIsNogood",customerParamMap);
	}

	/**
	 * 更新客户地址
	 * @param customerParamMap
	 */
	public void updateCrmCustomerAddress(Map<String, Object> customerParamMap){
		this.getSqlMapClientTemplate().update("updateCrmCustomerAddress",customerParamMap);
	}

	/**
	 * 验证是否为不良客户
	 *
	 * @param paramMap
	 * @return
	 */
	@Override
	public Boolean checkIsNogoodCus(Map<String,Object> paramMap){
		Integer count = (Integer)this.getSqlMapClientTemplate().queryForObject("checkIsNogoodCus",paramMap);
		if (count.equals(1)){
			return true;
		}
		return false;
	}

	/**
	 * 根据传入的id_card列表查询出客户出存在的id_card列表
	 *
	 * @param paramMap
	 * @return
	 */
	@Override
	public List<BaseCrmCustomer> selectIdCardListByIdCard(Map<String,Object> paramMap){
		return (List<BaseCrmCustomer>)this.getSqlMapClientTemplate().queryForList("selectIdCardListByIdCard",paramMap);
	}

	@Override
	public Integer updateImpCrmCustomer(CrmCustomer crmCustomer){
		return (Integer)this.getSqlMapClientTemplate().update("updateImpCrmCustomer",crmCustomer);
	}

	/**
	 * 通过客户姓名和电话号码查找
	 */
	@SuppressWarnings("unchecked")
	public List<BaseCrmCustomer> getCrmCustomerByCrm(Map<String, Object> params) {
		return this.getSqlMapClientTemplate().queryForList("selectCrmCustomerByMap",params);
	}

	@Override
	public Integer updateCustomerRandom(CrmCustomer crmCustomer){
		this.updateMemoFiled(crmCustomer);
		return (Integer)this.getSqlMapClientTemplate().update("updateCustomerRandom",crmCustomer);
	}

	@Override
	public List<BaseCrmCustomer> getCromCustomerByIdcard(Map<String,Object> map) {
		return this.getSqlMapClientTemplate().queryForList("getCrmCustomerByIdcard",map);
	}

	@Override
	public List<BaseCrmCustomer> getCrmCustomerByCHM(String customerNo) {
		return this.getSqlMapClientTemplate().queryForList("getCrmCustomerByCHM",customerNo);
	}

	@Override
	public List<BaseCrmCustomer> getCrmCustomerByPYisNull(Map<String,Object> map) {
		return this.getSqlMapClientTemplate().queryForList("getCrmCustomerByPYisNull",map);
	}

	@Override
	public Integer getCrmCustomerByPYisNullCount() {
		return (Integer)this.getSqlMapClientTemplate().queryForObject("getCrmCustomerByPYisNullCount");
	}


}
