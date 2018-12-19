package com.banger.mobile.domain.model.customer;

import com.banger.mobile.domain.model.base.customer.BaseCrmCustomerRelatives;
/**
 * CrmCustomerRelatives entity. @author MyEclipse Persistence Tools
 */

public class CrmCustomerRelatives extends BaseCrmCustomerRelatives {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3372843512301846075L;
	private String customerNo;				//客户编号
	private String customerTypeName;		//客户类型
	private String customerName;			//客户姓名
	private Integer defaultPhoneType;		//默认号码类型
	private String mobilePhone1;			//手机1
	private String mobilePhone2;			//手机2
	private String phone;					//固话
	private String fax;						//传真
	private String customerTitle;			//称谓
	private String sex;						//性别
	private Integer age;					//年龄
	private String company;					//单位
	private Integer belongDeptId;			//归属机构ID
	private Integer belongUserId;			//归属人员ID
	private String userName; 				//归属人员
	private String deptName;				//归属机构
	private String customerNamePinyin;		//拼音缩写
	private Integer isShare;		//是否是共享客户
	public String getCustomerNo() {
		return customerNo;
	}
	public void setCustomerNo(String customerNo) {
		this.customerNo = customerNo;
	}
	public String getCustomerTypeName() {
		return customerTypeName;
	}
	public void setCustomerTypeName(String customerTypeName) {
		this.customerTypeName = customerTypeName;
	}
	public Integer getDefaultPhoneType() {
		return defaultPhoneType;
	}
	public void setDefaultPhoneType(Integer defaultPhoneType) {
		this.defaultPhoneType = defaultPhoneType;
	}
	public String getMobilePhone1() {
		return mobilePhone1;
	}
	public void setMobilePhone1(String mobilePhone1) {
		this.mobilePhone1 = mobilePhone1;
	}
	public String getMobilePhone2() {
		return mobilePhone2;
	}
	public void setMobilePhone2(String mobilePhone2) {
		this.mobilePhone2 = mobilePhone2;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getCustomerTitle() {
		return customerTitle;
	}
	public void setCustomerTitle(String customerTitle) {
		this.customerTitle = customerTitle;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public Integer getBelongDeptId() {
		return belongDeptId;
	}
	public void setBelongDeptId(Integer belongDeptId) {
		this.belongDeptId = belongDeptId;
	}
	public Integer getBelongUserId() {
		return belongUserId;
	}
	public void setBelongUserId(Integer belongUserId) {
		this.belongUserId = belongUserId;
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
	public String getCustomerNamePinyin() {
		return customerNamePinyin;
	}
	public void setCustomerNamePinyin(String customerNamePinyin) {
		this.customerNamePinyin = customerNamePinyin;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public Integer getIsShare() {
		return isShare;
	}
	public void setIsShare(Integer isShare) {
		this.isShare = isShare;
	}
	
}