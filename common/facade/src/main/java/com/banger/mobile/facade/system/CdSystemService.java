/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :所有系统代码表
 * Author     :zsw
 * Create Date:May 25, 2012
 */
package com.banger.mobile.facade.system;

import java.util.List;
import com.banger.mobile.domain.model.system.CdCity;
import com.banger.mobile.domain.model.system.CdProvince;
import com.banger.mobile.domain.model.system.CdSex;
import com.banger.mobile.domain.model.system.CrmCustomerIndustry;
import com.banger.mobile.domain.model.system.CrmCustomerType;
import com.banger.mobile.domain.model.user.CdOnlineStatus;

import com.banger.mobile.domain.model.credentialType.CredentialType;
import com.banger.mobile.domain.model.legalForm.LegalForm;
import com.banger.mobile.domain.model.orgType.OrgType;
import com.banger.mobile.domain.model.educationalHistory.EducationalHistory;
import com.banger.mobile.domain.model.livingCondition.LivingCondition;
import com.banger.mobile.domain.model.maritalStatus.MaritalStatus;

/** 
 * 所有系统代码表
 * @author zsw
 */
public interface CdSystemService {
	
	/**
	 * 返回省份列表
	 * @return
	 */
	List<CdProvince> getProvinces();
	
	/**
     * 返回城市列表
     * @return
     */
    List<CdCity> getAllCitys();
	
	/**
	 * 返因城市列表
	 * @param provCode 省份代码
	 * @return
	 */
	List<CdCity> getCitys(String provCode);
	
	/**
	 * 返回性别列表
	 * @return
	 */
	List<CdSex> getSex();
	
	/**
	 * 返回客户类型列表
	 * @return
	 */
	List<CrmCustomerType> getCusTypes();
	
	/**
	 * 得到客户类型
	 * @param id
	 * @return
	 */
	String getCusTypeNameById(Integer id);
	
	/**
	 * 返回所属行业
	 * @return
	 */
	List<CrmCustomerIndustry> getCusIndustry();
	
	/**
     * 返回登录状态
     * @return
     */
    List<CdOnlineStatus> getOnlineStatus();
	
	/**
	 * 得到行业名称
	 * @param id
	 * @return
	 */
	String getIndustryNameById(Integer id);
	
	/**
	 * 得到城市名称
	 * @return
	 */
	String getCityName(String cityCode);
	
	/**
	 * 得到省份名称
	 * @param provCode
	 * @return
	 */
	String getProvinceName(String provCode);
	
	/**
	 * 根据省份代码取省份对像
	 * @param cityCode
	 * @return
	 */
	public CdProvince getProvinceByCode(String provCode);
	
	/**
	 * 根据城市代码取城市对像
	 * @param cityCode
	 * @return
	 */
	public CdCity getCityByCode(String cityCode);
	
	/**
	 * 获取证件类型名称
	 * @param id
	 * @return
	 */
	public String getCredentialTypeNameById(Integer id);
	
	/**
	 * 获取证件类型列表
	 * @return
	 */
	public List<CredentialType> getCredentialType();
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public String getLivingConditionNameById(Integer id);
	
	/**
	 * 
	 * @return
	 */
	public List<LivingCondition> getLivingCondition();
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public String getEducationalHistoryNameById(Integer id);
	
	/**
	 * 
	 * @return
	 */
	public List<EducationalHistory> getEducationalHistory();
	
	/**
	 * @param id
	 * @return
	 */
	public String getMaritalStatusNameById(Integer id);
	
	/**
	 * 
	 * @return
	 */
	public List<MaritalStatus> getMaritalStatus();
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public String getLegalFormNameById(Integer id);
	
	/**
	 * 
	 * @return
	 */
	public List<LegalForm> getLegalForm();
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public String getOrgTypeById(Integer id);
	
	/**
	 * 
	 * @return
	 */
	public List<OrgType> getOrgType();

}
