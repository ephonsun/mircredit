/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :来电号码地区表
 * Author     :zsw
 * Create Date:May 28, 2012
 */
package com.banger.mobile.dao.talk.ibatis;

import com.banger.mobile.dao.talk.TlkMobileAttibutionDao;
import com.banger.mobile.domain.model.talk.TlkMobileAttibution;
import com.banger.mobile.ibatis.GenericDaoiBatis;

@SuppressWarnings("rawtypes")
public class TlkMobileAttibutionDaoiBatis extends GenericDaoiBatis implements TlkMobileAttibutionDao {

	@SuppressWarnings("unchecked")
	public TlkMobileAttibutionDaoiBatis() {
		super(TlkMobileAttibution.class);
	}

	/**
	 * 通过电话号码查地区码
	 * @param code
	 * @return
	 */
	public String getAreaCodeByNumber(String number){
		if(number!=null && !"".equals(number))
		{
			return (String)this.getSqlMapClientTemplate().queryForObject("getAreaCodeByNumber",number);
		}
		return "";
	}
}
