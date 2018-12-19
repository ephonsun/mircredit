package com.banger.mobile.domain.model.customer;


import com.banger.mobile.domain.model.base.customer.BaseCrmCustomer;


public class CrmCustomerBirthday extends BaseCrmCustomer {
	
	private static final long serialVersionUID = 441686157453526409L;
	private String remindContent;			//提醒内容
	private String birthdayContent;			//日期内容
	public String getRemindContent() {
		return remindContent;
	}
	public void setRemindContent(String remindContent) {
		this.remindContent = remindContent;
	}
	public String getBirthdayContent() {
		return birthdayContent;
	}
	public void setBirthdayContent(String birthdayContent) {
		this.birthdayContent = birthdayContent;
	}
	
}