/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :省份代码表
 * Author     :zsw
 * Create Date:May 21, 2012
 */
package com.banger.mobile.dao.system;

import java.util.List;

import com.banger.mobile.domain.model.system.CdProvince;

public interface CdProvinceDao {
	/**
	 * 返回省份列表
	 * @return
	 */
	List<CdProvince> getProvinces();
	
	/**
	 * 得到省份名称
	 * @return
	 */
	String getProvinceName(String provCode);
	
	/**
     * 返回省份代码
     * @return
     */
    String getProvinceCode(String provName);
}
