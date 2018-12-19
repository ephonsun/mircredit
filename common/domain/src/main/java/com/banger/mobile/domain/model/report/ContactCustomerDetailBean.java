package com.banger.mobile.domain.model.report;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class ContactCustomerDetailBean {
	private String userId;				//用户Id
	private String deptId;				//机构Id
	private String name;				//机构名称
	private String title;				//标题
	private String dateBegin;			//开始时间
	private String dateEnd;				//结束时间
	private String type;				//客户类型
	private String customerType;		//客户类型ID
	private String conFlag;				//联系类型
	private String contactFlag;			//联系类型ID
	private String contactCount;		//联系
	
	
	public String getContactCount() {
		return contactCount;
	}
	public void setContactCount(String contactCount) {
		this.contactCount = contactCount;
	}
	public String getCustomerType() {
		return customerType;
	}
	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}
	public String getContactFlag() {
		return contactFlag;
	}
	public void setContactFlag(String contactFlag) {
		this.contactFlag = contactFlag;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		try {
			this.type = URLDecoder.decode(type,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	public String getConFlag() {
		return conFlag;
	}
	public void setConFlag(String conFlag) {
		try {
			this.conFlag = URLDecoder.decode(conFlag,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	public String getDateBegin() {
		return dateBegin;
	}
	public void setDateBegin(String dateBegin) {
		this.dateBegin = dateBegin;
	}
	public String getDateEnd() {
		return dateEnd;
	}
	public void setDateEnd(String dateEnd) {
		this.dateEnd = dateEnd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		try {
			this.name = URLDecoder.decode(name,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		try {
			this.title = URLDecoder.decode(title,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
