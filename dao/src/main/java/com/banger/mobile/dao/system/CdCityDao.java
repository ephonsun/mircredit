/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :城市代码表
 * Author     :zsw
 * Create Date:May 21, 2012
 */
package com.banger.mobile.dao.system;

import java.util.List;

import com.banger.mobile.domain.model.system.CdCity;

public interface CdCityDao {
	/**
	 * 返回城市列表
	 * @return
	 */
	List<CdCity> getCitys(String provCode);
	
	/**
     * 返回所有城市列表
     * @return
     */
    List<CdCity> getAllCitys();
	
	/**
	 * 返回城市的名称
	 * @param cityCode
	 * @return
	 */
	String getCityName(String cityCode);
	
	/**
     * 返回城市的代码
     * @param cityName
     * @return
     */
    String getCityCode(String cityName);
}
