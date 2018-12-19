/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :共同借贷人-Domain-扩展2
 * Author     :QianJie
 * Create Date:Feb 18, 2013
 */
package com.banger.mobile.domain.model.loan;

import com.banger.mobile.domain.model.base.loan.BaseLnLoanCoBorrower;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author QianJie
 * @version $Id: LnLoanCoBorrowerBean.java,v 0.1 Feb 18, 2013 5:32:01 PM QianJie Exp $
 */
public class LnLoanCoBorrowerBean extends BaseLnLoanCoBorrower {

    private static final long serialVersionUID = 3705914368036367373L;
    private String customerName;                           //客户姓名
    private Integer defaultPhoneType;                       //默认号码类型
    private String mobilePhone1;                           //手机1
    private String mobilePhone2;                           //手机2
    private String phone;                                  //固话
    private String fax;                                    //传真
    private String cphNumber;                              //联系电话
    private String idCard;                                 //身份证号码
    private String company; //公司地址
    private Integer hasHeadP;                             //是否有头像照片
    private Integer hasIdCardZmP;                         //是否有身份证正面照片
    private Integer hasIdCardFmP;                         //是否有身份证反面照片

    private Integer customerId;
    
    public String getCompany() {
		return company;
	}


	public void setCompany(String company) {
		this.company = company;
	}


	public LnLoanCoBorrowerBean() {
        super();
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
                default:
                    if (this.mobilePhone1 != null && !"".equals(this.mobilePhone1))
                        return this.mobilePhone1;
                    if (this.mobilePhone2 != null && !"".equals(this.mobilePhone2))
                        return this.mobilePhone2;
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


    public Integer getHasHeadP() {
        return hasHeadP;
    }

    public void setHasHeadP(Integer hasHeadP) {
        this.hasHeadP = hasHeadP;
    }

    public Integer getHasIdCardZmP() {
        return hasIdCardZmP;
    }

    public void setHasIdCardZmP(Integer hasIdCardZmP) {
        this.hasIdCardZmP = hasIdCardZmP;
    }

    public Integer getHasIdCardFmP() {
        return hasIdCardFmP;
    }

    public void setHasIdCardFmP(Integer hasIdCardFmP) {
        this.hasIdCardFmP = hasIdCardFmP;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }
}
