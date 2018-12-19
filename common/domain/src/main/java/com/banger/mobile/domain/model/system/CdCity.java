/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :城市
 * Author     :zsw
 * Create Date:May 21, 2012
 */
package com.banger.mobile.domain.model.system;

import com.banger.mobile.domain.model.base.BaseObject;

public class CdCity extends BaseObject implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5135904668237956325L;
	private String code;				//城市代码
	private String fullName;			//全名称
	private String shortName;			//短名称
	private String py;					//拼音
	private String provCode;			//份省代码

	// Constructors

	/** default constructor */
	public CdCity() {
	}

	/** minimal constructor */
	public CdCity(String code) {
		this.code = code;
	}

	/** full constructor */
	public CdCity(String code, String fullName, String shortName, String py,
			String provCode) {
		this.code = code;
		this.fullName = fullName;
		this.shortName = shortName;
		this.py = py;
		this.provCode = provCode;
	}

	// Property accessors

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getFullName() {
		return this.fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getShortName() {
		return this.shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getPy() {
		return this.py;
	}

	public void setPy(String py) {
		this.py = py;
	}

	public String getProvCode() {
		return this.provCode;
	}

	public void setProvCode(String provCode) {
		this.provCode = provCode;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result
				+ ((fullName == null) ? 0 : fullName.hashCode());
		result = prime * result
				+ ((provCode == null) ? 0 : provCode.hashCode());
		result = prime * result + ((py == null) ? 0 : py.hashCode());
		result = prime * result
				+ ((shortName == null) ? 0 : shortName.hashCode());
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
		CdCity other = (CdCity) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (fullName == null) {
			if (other.fullName != null)
				return false;
		} else if (!fullName.equals(other.fullName))
			return false;
		if (provCode == null) {
			if (other.provCode != null)
				return false;
		} else if (!provCode.equals(other.provCode))
			return false;
		if (py == null) {
			if (other.py != null)
				return false;
		} else if (!py.equals(other.py))
			return false;
		if (shortName == null) {
			if (other.shortName != null)
				return false;
		} else if (!shortName.equals(other.shortName))
			return false;
		return true;
	}
	
	
}