package com.banger.mobile.webservice.domain.loan;

import com.banger.mobile.domain.model.pad.PadLoanInfo;


/**
 * 客户信息
 * @author summerxll
 *
 */
public class CustomerInfo {
	
	private Integer customerId; // 客户ID
	private String cusName; // 申请人姓名
	private String cusIdtypeId; // 申请人证件类型
	private String cusIdtypeName;
	private String cusIdcard; // 申请人证件号码
	private String cusMobilePhone; // 申请人手机号
	private String appLoanTypeId; // 贷款类型
	private String appLoanTypeName;
	
	public Integer getCustomerId() {
		return customerId==null ?-1:customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId==null ?-1:customerId;
	}
	public String getCusName() {
		return cusName==null?"":cusName;
	}
	public void setCusName(String cusName) {
		this.cusName = cusName==null?"":cusName;
	}
	public String getCusIdtypeId() {
		return cusIdtypeId==null?"":cusIdtypeId;
	}
	public void setCusIdtypeId(String cusIdtypeId) {
		this.cusIdtypeId = cusIdtypeId==null?"":cusIdtypeId;
	}
	public String getCusIdtypeName() {
		return cusIdtypeName==null?"":cusIdtypeName;
	}
	public void setCusIdtypeName(String cusIdtypeName) {
		this.cusIdtypeName = cusIdtypeName==null?"":cusIdtypeName;
	}
	public String getCusIdcard() {
		return cusIdcard==null?"":cusIdcard;
	}
	public void setCusIdcard(String cusIdcard) {
		this.cusIdcard = cusIdcard==null?"":cusIdcard;
	}
	public String getCusMobilePhone() {
		return cusMobilePhone==null?"":cusMobilePhone;
	}
	public void setCusMobilePhone(String cusMobilePhone) {
		this.cusMobilePhone = cusMobilePhone==null?"":cusMobilePhone;
	}
	public String getAppLoanTypeId() {
		return appLoanTypeId==null?"":appLoanTypeId;
	}
	public void setAppLoanTypeId(String appLoanTypeId) {
		this.appLoanTypeId = appLoanTypeId==null?"":appLoanTypeId;
	}
	public String getAppLoanTypeName() {
		return appLoanTypeName==null?"":appLoanTypeName;
	}
	public void setAppLoanTypeName(String appLoanTypeName) {
		this.appLoanTypeName = appLoanTypeName==null?"":appLoanTypeName;
	}
	
	public CustomerInfo(PadLoanInfo info){
		this.setCustomerId(info.getCustomerId());
		this.setCusName(info.getCusName());
		this.setCusIdtypeId(info.getCusIdtypeId());
		this.setCusIdtypeName(info.getCusIdtypeName());
		this.setCusIdcard(info.getCusIdcard());
		this.setCusMobilePhone(info.getCusMobilePhone());
		this.setAppLoanTypeId(info.getAppLoanTypeId());
		this.setAppLoanTypeName(info.getAppLoanTypeName());
	}
}
