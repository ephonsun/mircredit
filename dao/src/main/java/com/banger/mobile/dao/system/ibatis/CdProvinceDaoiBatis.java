/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :省份代码表
 * Author     :zsw
 * Create Date:May 21, 2012
 */
package com.banger.mobile.dao.system.ibatis;

import java.util.List;

import com.banger.mobile.dao.system.CdProvinceDao;
import com.banger.mobile.domain.model.system.CdProvince;
import com.banger.mobile.ibatis.GenericDaoiBatis;

@SuppressWarnings("rawtypes")
public class CdProvinceDaoiBatis  extends GenericDaoiBatis implements CdProvinceDao {

	@SuppressWarnings("unchecked")
	public CdProvinceDaoiBatis() {
		super(CdProvince.class);
	}
	
	/**
	 * 得到省份名称
	 * @return
	 */
	public String getProvinceName(String provCode)
	{
		if(provCode!=null && !"".equals(provCode))
			return (String)this.getSqlMapClientTemplate().queryForObject("getProvNameByCode",provCode);
		else return "";
	}

	/**
	 * 返回省份列表
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<CdProvince> getProvinces() {
		return (List<CdProvince>)this.getSqlMapClientTemplate().queryForList("getProvinces");
	}

	/**
     * 返回省份代码
     * @return
     */
    public String getProvinceCode(String provName) {
        if(provName!=null && !"".equals(provName))
            return (String)this.getSqlMapClientTemplate().queryForObject("getProvCodeByName",provName);
        else return "";
    } 
}
