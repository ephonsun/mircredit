/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :来电号码地区表
 * Author     :zsw
 * Create Date:May 28, 2012
 */
package com.banger.mobile.domain.model.base.talk;
import java.io.Serializable;
import com.banger.mobile.domain.model.base.BaseObject;

public class BaseTlkMobileAttibution extends BaseObject implements Serializable {
	private static final long serialVersionUID = 7400460107490309845L;
	
	private String mobileNumberPer;
	private String phoneCode;
	
	public String getMobileNumberPer() {
		return mobileNumberPer;
	}

	public void setMobileNumberPer(String mobileNumberPer) {
		this.mobileNumberPer = mobileNumberPer;
	}

	public String getPhoneCode() {
		return phoneCode;
	}

	public void setPhoneCode(String phoneCode) {
		this.phoneCode = phoneCode;
	}

	public BaseTlkMobileAttibution()
	{
		
	}
}
