/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :城市代码表
 * Author     :zsw
 * Create Date:May 21, 2012
 */
package com.banger.mobile.dao.system.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.banger.mobile.dao.system.CdCityDao;
import com.banger.mobile.domain.model.system.CdCity;
import com.banger.mobile.ibatis.GenericDaoiBatis;

@SuppressWarnings("rawtypes")
public class CdCityDaoiBatis extends GenericDaoiBatis implements CdCityDao {

	@SuppressWarnings("unchecked")
	public CdCityDaoiBatis() {
		super(CdCity.class);
	}
	
	/**
	 * 返回城市的名称
	 * @param cityCode
	 * @return
	 */
	public String getCityName(String cityCode){
		if(cityCode!=null && !"".equals(cityCode))
			return (String)this.getSqlMapClientTemplate().queryForObject("getCityNameByCode",cityCode);
		else return "";
	}

	/**
	 * 返回城市列表
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<CdCity> getCitys(String provCode) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("provCode",provCode);
		return (List<CdCity>)this.getSqlMapClientTemplate().queryForList("getCityByProv",map);
	}

	/**
     * 返回城市的代码
     * @param cityName
     * @return
     */
    public String getCityCode(String cityName) {
        if(cityName!=null && !"".equals(cityName))
            return (String)this.getSqlMapClientTemplate().queryForObject("getCityCodeByName",cityName);
        else return "";
    }

    
    /**
     * 返回所有城市列表
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<CdCity> getAllCitys() {
        return (List<CdCity>)this.getSqlMapClientTemplate().queryForList("getAllCity");
    }

}
