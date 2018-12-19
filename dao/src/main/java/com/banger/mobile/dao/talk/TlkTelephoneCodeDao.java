/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :来电号码地区表
 * Author     :zsw
 * Create Date:May 28, 2012
 */
package com.banger.mobile.dao.talk;

public interface TlkTelephoneCodeDao 
{
	/**
	 * 通过区域代码查名称
	 * @param code
	 * @return
	 */
	public String getPhoneAreaNameByCode(String code);
}
