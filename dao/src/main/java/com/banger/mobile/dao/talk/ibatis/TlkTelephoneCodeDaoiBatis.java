/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :来电号码地区表
 * Author     :zsw
 * Create Date:May 28, 2012
 */
package com.banger.mobile.dao.talk.ibatis;

import com.banger.mobile.dao.talk.TlkTelephoneCodeDao;
import com.banger.mobile.domain.model.talk.TlkTelephoneCode;
import com.banger.mobile.ibatis.GenericDaoiBatis;


@SuppressWarnings("rawtypes")
public class TlkTelephoneCodeDaoiBatis extends GenericDaoiBatis implements TlkTelephoneCodeDao
{
	@SuppressWarnings("unchecked")
	public TlkTelephoneCodeDaoiBatis() {
		super(TlkTelephoneCode.class);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 通过区域代码查名称
	 * @param code
	 * @return
	 */
	public String getPhoneAreaNameByCode(String code){
		if(code!=null && !"".equals(code))
		{
			return (String)this.getSqlMapClientTemplate().queryForObject("getPhoneAreaNameByCode",code);
		}
		return "";
	}

}
