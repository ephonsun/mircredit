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

public class BaseTlkTelephoneCode extends BaseObject implements Serializable {
	private static final long serialVersionUID = -6841041666128203875L;
	
	private String phoneCode;
	private String areaName;
	
	public String getPhoneCode() {
		return phoneCode;
	}
	public void setPhoneCode(String phoneCode) {
		this.phoneCode = phoneCode;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	
	public BaseTlkTelephoneCode()
	{
		
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((areaName == null) ? 0 : areaName.hashCode());
		result = prime * result
				+ ((phoneCode == null) ? 0 : phoneCode.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BaseTlkTelephoneCode other = (BaseTlkTelephoneCode) obj;
		if (areaName == null) {
			if (other.areaName != null)
				return false;
		} else if (!areaName.equals(other.areaName))
			return false;
		if (phoneCode == null) {
			if (other.phoneCode != null)
				return false;
		} else if (!phoneCode.equals(other.phoneCode))
			return false;
		return true;
	}
	
	
}
