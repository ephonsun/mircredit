/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :yuanme
 * Create Date:2012-11-12
 */
package com.banger.mobile.domain.model.base.data;

import java.math.BigDecimal;
import java.util.Date;

import com.banger.mobile.domain.model.base.BaseObject;

/**
 * @author yuanme
 * @version $Id: BaseDatCustomerData.java,v 0.1 2012-11-12 下午3:25:42 Administrator Exp $
 */
public class BaseDatFormDetail extends BaseObject {
    private static final long serialVersionUID = 4868892979010034254L;

    private Integer           formDetailId;
    private Integer           formId;
    private Integer           age;
    private Integer           livingYear;
    private Integer           workYear;
    private String            marriage;
    private String            mateWork;
    private String            childrenInfo;
    private BigDecimal        annulIncome;
    private BigDecimal        mateIncome;
    private BigDecimal        familyDebt;
    private String            wealthInfo;
    private String            creditCardInfo;
    private String            loanInfo;
    private String            friendInfo;
    private String            companyInfo;
    private String            homeFeeling;
    private Date              createDate;
    private Date              updateDate;
    private Integer           createUser;
    private Integer           updateUser;

    public Integer getFormDetailId() {
        return formDetailId;
    }

    public void setFormDetailId(Integer formDetailId) {
        this.formDetailId = formDetailId;
    }

    public Integer getFormId() {
        return formId;
    }

    public void setFormId(Integer formId) {
        this.formId = formId;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getLivingYear() {
        return livingYear;
    }

    public void setLivingYear(Integer livingYear) {
        this.livingYear = livingYear;
    }

    public Integer getWorkYear() {
        return workYear;
    }

    public void setWorkYear(Integer workYear) {
        this.workYear = workYear;
    }

    public String getMarriage() {
        return marriage;
    }

    public void setMarriage(String marriage) {
        this.marriage = marriage;
    }

    public String getMateWork() {
        return mateWork;
    }

    public void setMateWork(String mateWork) {
        this.mateWork = mateWork;
    }

    public String getChildrenInfo() {
        return childrenInfo;
    }

    public void setChildrenInfo(String childrenInfo) {
        this.childrenInfo = childrenInfo;
    }

    public BigDecimal getAnnulIncome() {
        return annulIncome;
    }

    public void setAnnulIncome(BigDecimal annulIncome) {
        this.annulIncome = annulIncome;
    }

    public BigDecimal getMateIncome() {
        return mateIncome;
    }

    public void setMateIncome(BigDecimal mateIncome) {
        this.mateIncome = mateIncome;
    }

    public BigDecimal getFamilyDebt() {
        return familyDebt;
    }

    public void setFamilyDebt(BigDecimal familyDebt) {
        this.familyDebt = familyDebt;
    }

    public String getWealthInfo() {
        return wealthInfo;
    }

    public void setWealthInfo(String wealthInfo) {
        this.wealthInfo = wealthInfo;
    }

    public String getCreditCardInfo() {
        return creditCardInfo;
    }

    public void setCreditCardInfo(String creditCardInfo) {
        this.creditCardInfo = creditCardInfo;
    }

    public String getLoanInfo() {
        return loanInfo;
    }

    public void setLoanInfo(String loanInfo) {
        this.loanInfo = loanInfo;
    }

    public String getFriendInfo() {
        return friendInfo;
    }

    public void setFriendInfo(String friendInfo) {
        this.friendInfo = friendInfo;
    }

    public String getCompanyInfo() {
        return companyInfo;
    }

    public void setCompanyInfo(String companyInfo) {
        this.companyInfo = companyInfo;
    }

    public String getHomeFeeling() {
        return homeFeeling;
    }

    public void setHomeFeeling(String homeFeeling) {
        this.homeFeeling = homeFeeling;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Integer getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Integer createUser) {
        this.createUser = createUser;
    }

    public Integer getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(Integer updateUser) {
        this.updateUser = updateUser;
    }

}
