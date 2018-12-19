/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :来电号码地区表
 * Author     :zsw
 * Create Date:May 28, 2012
 */
package com.banger.mobile.dao.talk;

public interface TlkMobileAttibutionDao  {

	/**
	 * 通过电话号码查地区码
	 * @param code
	 * @return
	 */
	public String getAreaCodeByNumber(String number);
}
