/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :数据服务端校验结果类
 * Author     :zhushengwei
 * Create Date:2013-3-28
 */
package com.banger.mobile.webapp.valid;

public class ValidResultItem {
	private Integer index;					//错误行索引
	private String[] errorMessages;			//错误提示信息
	private String type;					//校验类型
	
	public Integer getIndex() {
		return index;
	}
	public void setIndex(Integer index) {
		this.index = index;
	}
	
	public String[] getErrorMessages(){
	    return errorMessages;
	}
	
	public void setErrorMessages(String[] errorMessages){
	    this.errorMessages = errorMessages;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public ValidResultItem(){
		this.index = 0;
		this.errorMessages = new String[0];
		this.type = "";
	}
}
