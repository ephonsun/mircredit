/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :高级搜索客户列表Bean
 * Author     :liyb
 * Create Date:2012-5-24
 */
package com.banger.mobile.domain.model.customer;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import com.banger.mobile.domain.model.base.customer.BaseCrmCustomer;

/**
 * @author liyb
 * @version $Id: CustomerInfo.java,v 0.1 2012-5-24 下午04:04:27 liyb Exp $
 */
public class CustomerBean extends BaseCrmCustomer implements Serializable {

    private static final long serialVersionUID = -3942946093008875318L;
    private String            userName;                                //客户经理
    private String            deptName;                                //机构名称
    private String			  customerTypeName;						   //客户类型名称
    private String			  exSmsPhone;							   //号码护展
    private Integer			  isShare;								   //是否共享
    
    public Integer getIsShare(){
    	return this.isShare;
    }
    
    public void setIsShare(Integer isShare){
    	this.isShare = isShare;
    }
    
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getCustomerTypeName() {
		return customerTypeName;
	}
	public void setCustomerTypeName(String customerTypeName) {
		this.customerTypeName = customerTypeName;
	}
	
	/**
	 * 得到默认显示号码
	 * @return
	 */
	public String getDefaultPhone()
	{
		if(this.defaultPhoneType!=null)
		{
			switch(this.defaultPhoneType.intValue())
			{
				case 1:
					return this.mobilePhone1;
				case 2:
					return this.mobilePhone2;
				case 3:
					return this.phone;
				case 4:
					return this.fax;
			}
		}
		return "";
	}
	
	/**
	 * 获得短信号码
	 * @return
	 */
	public String getSmsPhone()
	{
		if(this.defaultPhoneType!=null)
		{
			switch(this.defaultPhoneType.intValue())
			{
				case 1:
					return this.mobilePhone1;
				case 2:
					return this.mobilePhone2;
				default:
					if(this.mobilePhone1!=null && !"".equals(this.mobilePhone1))return this.mobilePhone1;
					if(this.mobilePhone2!=null && !"".equals(this.mobilePhone2))return this.mobilePhone2;
			}
		}
		if(this.exSmsPhone!=null && !"".equals(this.exSmsPhone))
			return this.exSmsPhone;
		return "";
	}
	
	/**
	 * 自动增和的年龄
	 * @return
	 */
	public String getAutoAge()
	{
		if(this.getBirthday()!=null)
		{
			Calendar c = Calendar.getInstance();
			c.setTime(this.getBirthday());
			int y1 = c.get(Calendar.YEAR);
			c.setTime(new Date());
			int y2 = c.get(Calendar.YEAR);
			int g = y2-y1;
			return (g>0)?String.valueOf(g):"";
		}
		return "";
	}
    
	/**
	 * 设置短信扩展号码
	 * @param exPhone
	 */
	public void setExSmsPhone(String exPhone)
	{
		this.exSmsPhone = exPhone;
	}
    
}
