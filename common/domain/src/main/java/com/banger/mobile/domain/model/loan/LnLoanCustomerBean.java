/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :贷款模糊查找客户-domain-扩展
 * Author     :QianJie
 * Create Date:Feb 18, 2013
 */
package com.banger.mobile.domain.model.loan;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.banger.mobile.domain.model.base.BaseObject;

/**
 * @author QianJie
 * @version $Id: LnLoanCustomerBean.java,v 0.1 Feb 18, 2013 2:40:25 PM QianJie
 *          Exp $
 */
public class LnLoanCustomerBean extends BaseObject implements Serializable {

    private static final long serialVersionUID = -8214583159209099049L;

    private Integer           customerId;                              // 客户ID
    private String            customerName;                            // 客户姓名
    private String            customerNo;
    private String            customerTitle;
    private String            customerTypeName;
    private Integer           customerTypeId;
    private String 			  credentialTypeId;	//证件类型
    private String 			  company; //公司单位
    private String            sex;
    private String            birthday;
    private Integer           defaultPhoneType;                        // 默认号码类型
    private String            mobilePhone1;                            // 手机1
    private String            mobilePhone2;                            // 手机2
    private String            phone;                                   // 固话
    private String            fax;                                     // 传真
    private String            cphNumber;                               // 联系电话
    private String            idCard;                                  // 身份证号码
    private Boolean           isMyAuthority;                           // 是否是我权限范围内的客户--待用
    private Integer           age;                                     // 年龄
    private String            address;
    private String            email;
    private Integer           isNoGood;

    public LnLoanCustomerBean() {
        super();
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
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

    public String getCphNumber() {
        if (this.defaultPhoneType != null) {
            switch (this.defaultPhoneType.intValue()) {
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
        if (this.cphNumber != null && !"".equals(this.cphNumber))
            return this.cphNumber;
        return "";
    }

    public void setCphNumber(String cphNumber) {
        this.cphNumber = cphNumber;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public Boolean getIsMyAuthority() {
        return isMyAuthority;
    }

    public void setIsMyAuthority(Boolean isMyAuthority) {
        this.isMyAuthority = isMyAuthority;
    }

    public String getCustomerTitle() {
        return customerTitle;
    }

    public void setCustomerTitle(String customerTitle) {
        this.customerTitle = customerTitle;
    }

    public String getCustomerTypeName() {
        return customerTypeName;
    }

    public void setCustomerTypeName(String customerTypeName) {
        this.customerTypeName = customerTypeName;
    }

    public Integer getCustomerTypeId() {
        return customerTypeId;
    }

    public void setCustomerTypeId(Integer customerTypeId) {
        this.customerTypeId = customerTypeId;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getCustomerNo() {
        return customerNo;
    }

    public void setCustomerNo(String customerNo) {
        this.customerNo = customerNo;
    }

    public Integer getAge() {
        if (birthday != null) {
            SimpleDateFormat date = new SimpleDateFormat("yyyy");
            Date dd = new Date();
            int year = Integer.parseInt(date.format(birthday));
            int nowYear = Integer.parseInt(date.format(dd));
            Integer newAge = nowYear - year;
            if (newAge < 120 && newAge > 0) {
                return newAge;
            }
        }
        return null;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getIsNoGood() {
        return isNoGood;
    }

    public void setIsNoGood(Integer isNoGood) {
        this.isNoGood = isNoGood;
    }

	public String getCredentialTypeId() {
		return credentialTypeId;
	}

	public void setCredentialTypeId(String credentialTypeId) {
		this.credentialTypeId = credentialTypeId;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}
    
    
}
