/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :省份
 * Author     :zsw
 * Create Date:May 21, 2012
 */
package com.banger.mobile.domain.model.system;

import com.banger.mobile.domain.model.base.BaseObject;

public class CdProvince extends BaseObject implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 589422569594270596L;
	private String code;			//省份代码
	private String fullName;		//全名
	private String shortName;		//短名
	private String py;				//拼音

	// Constructors

	/** default constructor */
	public CdProvince() {
	}

	/** minimal constructor */
	public CdProvince(String code) {
		this.code = code;
	}

	/** full constructor */
	public CdProvince(String code, String fullName, String shortName, String py) {
		this.code = code;
		this.fullName = fullName;
		this.shortName = shortName;
		this.py = py;
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

}