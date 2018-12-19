/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :客户联系跟进统计报表
 * Author     :zsw
 * Create Date:Jun 1, 2012
 */
package com.banger.mobile.domain.model.report;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;

public class ContactCustomerReportBean {
	private String deptIds;				//机构Ids
	private String userIds;				//用户Ids
	private String belongTo;			//归属
	private Date dateBegin;				//开始日期
	private Date dateEnd;				//结束日期
	private String customerType;		//客户类型
	private String customerTypeName;	//客户类型名字集合
	private Integer contactCount;		//联系次数
	private Integer containSub;			//是否包含下属
	
	public String getTypeEmpty() {
		String[] names = this.getCustomerTypeName().split(",");
		String isEmpty = "0";
		for(String name: names){
			if(name.equals("空")){
				isEmpty = "1";
				break;
			}
		}
		return isEmpty;
	}
	
	public String getCustomerTypeName() {
		return customerTypeName;
	}
	public void setCustomerTypeName(String customerTypeName) {
		try {
			this.customerTypeName = URLDecoder.decode(customerTypeName,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	public Integer getContainSub() {
		return containSub;
	}
	public void setContainSub(Integer containSub) {
		this.containSub = containSub;
	}
	public String getBelongTo() {
		return belongTo;
	}
	public void setBelongTo(String belongTo) {
		this.belongTo = belongTo;
	}
	public String getDeptIds() {
		return deptIds;
	}
	public void setDeptIds(String deptIds) {
		this.deptIds = deptIds;
	}
	public String getUserIds() {
		return userIds;
	}
	public void setUserIds(String userIds) {
		this.userIds = userIds;
	}
	public Date getDateBegin() {
		return dateBegin;
	}
	public void setDateBegin(Date dateBegin) {
		this.dateBegin = dateBegin;
	}
	public Date getDateEnd() {
		return dateEnd;
	}
	public void setDateEnd(Date dateEnd) {
		this.dateEnd = dateEnd;
	}
	public String getCustomerType() {
		return customerType;
	}
	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}
	public Integer getContactCount() {
		return contactCount;
	}
	public void setContactCount(Integer contactCount) {
		this.contactCount = contactCount;
	}
}
