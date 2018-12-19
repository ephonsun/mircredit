/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :所有代码表
 * Author     :zsw
 * Create Date:2012-5-29
 */
package com.banger.mobile.facade.impl.system.codetable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.banger.mobile.dao.system.CdCityDao;
import com.banger.mobile.dao.system.CdProvinceDao;
import com.banger.mobile.dao.system.CdSexDao;
import com.banger.mobile.dao.system.CrmCustomerIndustryDao;
import com.banger.mobile.dao.system.CrmCustomerTypeDao;
import com.banger.mobile.dao.user.CdOnlineStatusDao;

import com.banger.mobile.dao.system.CrmCredentialTypeDao;
import com.banger.mobile.dao.system.CrmLivingConditionDao;
import com.banger.mobile.dao.system.CrmEducationalHistoryDao;
import com.banger.mobile.dao.system.CrmMaritalStatusDao;
import com.banger.mobile.dao.system.CrmLegalFormDao;
import com.banger.mobile.dao.system.CrmOrgTypeDao;

import com.banger.mobile.domain.model.credentialType.CredentialType;
import com.banger.mobile.domain.model.educationalHistory.EducationalHistory;
import com.banger.mobile.domain.model.legalForm.LegalForm;
import com.banger.mobile.domain.model.livingCondition.LivingCondition;
import com.banger.mobile.domain.model.maritalStatus.MaritalStatus;
import com.banger.mobile.domain.model.orgType.OrgType;
import com.banger.mobile.domain.model.system.CdCity;
import com.banger.mobile.domain.model.system.CdProvince;
import com.banger.mobile.domain.model.system.CdSex;
import com.banger.mobile.domain.model.system.CrmCustomerIndustry;
import com.banger.mobile.domain.model.system.CrmCustomerType;
import com.banger.mobile.domain.model.user.CdOnlineStatus;
import com.banger.mobile.facade.system.CdSystemService;

public class CdSystemServiceImpl implements CdSystemService {
	private CdCityDao cityDao;
	private CdProvinceDao provDao;
	private CdSexDao sexDao;
	private CrmCustomerIndustryDao industDao;
	private CrmCustomerTypeDao cusTypeDao;
	private CdOnlineStatusDao cdOnlineStatusDao;
	
	private CrmCredentialTypeDao crmCredentialTypeDao;
	private CrmLivingConditionDao crmLivingConditionDao;
	private CrmEducationalHistoryDao crmEducationalHistoryDao;
	private CrmMaritalStatusDao crmMaritalStatusDao;
	private CrmLegalFormDao crmLegalFormDao;	
	private CrmOrgTypeDao crmOrgTypeDao;

	private Map<String,CdCity> citys;
	private Map<String,CdProvince> provs;
	
	public void setCdOnlineStatusDao(CdOnlineStatusDao cdOnlineStatusDao) {
        this.cdOnlineStatusDao = cdOnlineStatusDao;
    }

    /**
	 * 返回省份列表
	 * @return
	 */
	public List<CdProvince> getProvinces()
	{
		return provDao.getProvinces();
	}
	
	/**
	 * 返因城市列表
	 * @param provCode 省份代码
	 * @return
	 */
	public List<CdCity> getCitys(String provCode){
		return cityDao.getCitys(provCode);
	}
	
	/**
	 * 根据省份代码取省份对像
	 * @param cityCode
	 * @return
	 */
	public CdProvince getProvinceByCode(String provCode)
	{
		if(provCode!=null && !"".equals(provCode))
		{
			if(this.provs==null)
			{
				this.provs = new HashMap<String,CdProvince>();
				List<CdProvince> list = this.getProvDao().getProvinces();
				for(CdProvince prov : list)
				{
					this.provs.put(prov.getCode(),prov);
				}
			}
			return this.provs.get(provCode);
		}
		return null;
	}
	
	/**
	 * 根据城市代码取城市对像
	 * @param cityCode
	 * @return
	 */
	public CdCity getCityByCode(String cityCode)
	{
		if(cityCode!=null && !"".equals(cityCode))
		{
			if(this.citys==null)
			{
				this.citys = new HashMap<String,CdCity>();
				List<CdCity> list = this.getCityDao().getAllCitys();
				for(CdCity city : list)
				{
					this.citys.put(city.getCode(),city);
				}
			}
			return this.citys.get(cityCode);
		}
		return null;
	}
	
	/**
	 * 得到城市名称
	 * @return
	 */
	public String getCityName(String cityCode)
	{
		String name = cityDao.getCityName(cityCode);
		return 	name;
	}
	
	/**
	 * 得到省份名称
	 * @param provCode
	 * @return
	 */
	public String getProvinceName(String provCode)
	{
		String name = provDao.getProvinceName(provCode);
		return name;
	}
	
	/**
	 * 返回性别列表
	 * @return
	 */
	public List<CdSex> getSex(){
		return sexDao.getSexs();
	}
	
	/**
	 * 返回客户类型列表
	 * @return
	 */
	public List<CrmCustomerType> getCusTypes(){
		return cusTypeDao.getAllCrmCustomerType();
	}
	
	/**
	 * 返回所属行业
	 * @return
	 */
	public List<CrmCustomerIndustry> getCusIndustry()
	{
		return industDao.getAllCrmCustomerIndustry();
	}
	
	/**
	 * 得到客户类型
	 * @param id
	 * @return
	 */
	public String getCusTypeNameById(Integer id){
		if(id!=null && id.intValue()>0)
		{
			return cusTypeDao.getCrmCustomerTypeById(id).getCustomerTypeName();
		}
		return "";
	}
	
	/**
	 * 得到行业名称
	 * @param id
	 * @return
	 */
	public String getIndustryNameById(Integer id){
		if(id!=null && id.intValue()>0)
		{
			return industDao.getCrmCustomerIndustryById(id).getCustomerIndustryName();
		}
		return "";
	}
	
	public CdCityDao getCityDao() {
		return cityDao;
	}

	public void setCityDao(CdCityDao cityDao) {
		this.cityDao = cityDao;
	}

	public CdProvinceDao getProvDao() {
		return provDao;
	}

	public void setProvDao(CdProvinceDao provDao) {
		this.provDao = provDao;
	}

	public CdSexDao getSexDao() {
		return sexDao;
	}

	public void setSexDao(CdSexDao sexDao) {
		this.sexDao = sexDao;
	}

	public CrmCustomerIndustryDao getIndustDao() {
		return industDao;
	}

	public void setIndustDao(CrmCustomerIndustryDao industDao) {
		this.industDao = industDao;
	}

	public CrmCustomerTypeDao getCusTypeDao() {
		return cusTypeDao;
	}

	public void setCusTypeDao(CrmCustomerTypeDao cusTypeDao) {
		this.cusTypeDao = cusTypeDao;
	}

    /**
     * @return
     * @see com.banger.mobile.facade.system.CdSystemService#getOnlineStatus()
     */
    public List<CdOnlineStatus> getOnlineStatus() {
        return cdOnlineStatusDao.getCdOnlineStatus();
    }

    /**
     * @return
     * @see com.banger.mobile.facade.system.CdSystemService#getAllCitys()
     */
    public List<CdCity> getAllCitys() {
        return cityDao.getAllCitys();
    }

    
    
    
	@Override
	public String getCredentialTypeNameById(Integer id) {
		if(id!=null && id.intValue()>0)
		{
			return this.crmCredentialTypeDao.getCrmCredentialTypeById(id).getCredentialTypeName();
		}
		return "";
	}

	@Override
	public List<CredentialType> getCredentialType() {
		return this.crmCredentialTypeDao.getAllCrmCredentialType();
	}

	@Override
	public String getLivingConditionNameById(Integer id) {
		if(id!=null && id.intValue()>0)
		{
			return this.crmLivingConditionDao.getCrmLivingConditionById(id).getLivingConditionName();
		}
		return "";
	}

	@Override
	public List<LivingCondition> getLivingCondition() {
		return crmLivingConditionDao.getAllCrmLivingCondition();
	}

	@Override
	public String getEducationalHistoryNameById(Integer id) {
		if(id!=null && id.intValue()>0)
		{
			return this.crmEducationalHistoryDao.getCrmEducationalHistoryById(id).getEducationalHistoryName();
		}
		return "";
	}

	@Override
	public List<EducationalHistory> getEducationalHistory() {
		return crmEducationalHistoryDao.getAllCrmEducationalHistory();
	}

	@Override
	public String getMaritalStatusNameById(Integer id) {
		if(id!=null && id.intValue()>0)
		{
			return this.crmMaritalStatusDao.getCrmMaritalStatusById(id).getMaritalStatusName();
		}
		return "";
	}

	@Override
	public List<MaritalStatus> getMaritalStatus() {
		return crmMaritalStatusDao.getAllCrmMaritalStatus();
	}

	@Override
	public String getLegalFormNameById(Integer id) {
		if(id!=null && id.intValue()>0)
		{
			return this.crmLegalFormDao.getCrmLegalFormById(id).getLegalFormName();
		}
		return "";
	}

	@Override
	public List<LegalForm> getLegalForm() {
		return crmLegalFormDao.getAllCrmLegalForm();
	}

	@Override
	public String getOrgTypeById(Integer id) {
		if(id!=null && id.intValue()>0)
		{
			return this.crmOrgTypeDao.getCrmOrgTypeById(id).getOrgTypeName();
		}
		return "";
	}

	@Override
	public List<OrgType> getOrgType() {
		return crmOrgTypeDao.getAllCrmOrgType();
	}

	public CrmCredentialTypeDao getCrmCredentialTypeDao() {
		return crmCredentialTypeDao;
	}

	public void setCrmCredentialTypeDao(CrmCredentialTypeDao crmCredentialTypeDao) {
		this.crmCredentialTypeDao = crmCredentialTypeDao;
	}

	public CrmLivingConditionDao getCrmLivingConditionDao() {
		return crmLivingConditionDao;
	}

	public void setCrmLivingConditionDao(CrmLivingConditionDao crmLivingConditionDao) {
		this.crmLivingConditionDao = crmLivingConditionDao;
	}

	public CrmEducationalHistoryDao getCrmEducationalHistoryDao() {
		return crmEducationalHistoryDao;
	}

	public void setCrmEducationalHistoryDao(
			CrmEducationalHistoryDao crmEducationalHistoryDao) {
		this.crmEducationalHistoryDao = crmEducationalHistoryDao;
	}

	public CrmMaritalStatusDao getCrmMaritalStatusDao() {
		return crmMaritalStatusDao;
	}

	public void setCrmMaritalStatusDao(CrmMaritalStatusDao crmMaritalStatusDao) {
		this.crmMaritalStatusDao = crmMaritalStatusDao;
	}

	public CrmLegalFormDao getCrmLegalFormDao() {
		return crmLegalFormDao;
	}

	public void setCrmLegalFormDao(CrmLegalFormDao crmLegalFormDao) {
		this.crmLegalFormDao = crmLegalFormDao;
	}

	public CrmOrgTypeDao getCrmOrgTypeDao() {
		return crmOrgTypeDao;
	}

	public void setCrmOrgTypeDao(CrmOrgTypeDao crmOrgTypeDao) {
		this.crmOrgTypeDao = crmOrgTypeDao;
	}

	
}
