/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :hk
 * Create Date:Sep 5, 2012
 */
package com.banger.mobile.domain.model.product;

import java.io.Serializable;

/**
 * 产品销售明细报表实体类
 * @author hk
 * @version $Id: ReportProductBean.java,v 0.1 Sep 5, 2012 2:47:13 PM hk Exp $
 */
public class ReportProductBean implements Serializable{
    private static final long serialVersionUID = 8488498116227217315L;
    private String deptName;        //机构
    private String userName;        //营销人员
    private String customerNo;      //客户编号
    private String customerName;    //客户姓名
    private String customerPhone;   //客户电话
    private String buyMoney;        //销售额
    private String idCard;          //身份证
    private String account;         //账号
    private String buyDate;         //购买时间
    private String moneyUnit;       //募集单位
    
    public String getDeptName() {
        return deptName;
    }
    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getCustomerNo() {
        return customerNo;
    }
    public void setCustomerNo(String customerNo) {
        this.customerNo = customerNo;
    }
    public String getCustomerName() {
        return customerName;
    }
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    public String getCustomerPhone() {
        return customerPhone;
    }
    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }
    public String getBuyMoney() {
        return buyMoney;
    }
    public void setBuyMoney(String buyMoney) {
        this.buyMoney = buyMoney;
    }
    public String getIdCard() {
        return idCard;
    }
    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }
    public String getAccount() {
        return account;
    }
    public void setAccount(String account) {
        this.account = account;
    }
    public String getBuyDate() {
        return buyDate;
    }
    public void setBuyDate(String buyDate) {
        this.buyDate = buyDate;
    }
    public String getMoneyUnit() {
        return moneyUnit;
    }
    public void setMoneyUnit(String moneyUnit) {
        this.moneyUnit = moneyUnit;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((account == null) ? 0 : account.hashCode());
        result = prime * result + ((buyDate == null) ? 0 : buyDate.hashCode());
        result = prime * result + ((buyMoney == null) ? 0 : buyMoney.hashCode());
        result = prime * result + ((customerName == null) ? 0 : customerName.hashCode());
        result = prime * result + ((customerNo == null) ? 0 : customerNo.hashCode());
        result = prime * result + ((customerPhone == null) ? 0 : customerPhone.hashCode());
        result = prime * result + ((deptName == null) ? 0 : deptName.hashCode());
        result = prime * result + ((idCard == null) ? 0 : idCard.hashCode());
        result = prime * result + ((moneyUnit == null) ? 0 : moneyUnit.hashCode());
        result = prime * result + ((userName == null) ? 0 : userName.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final ReportProductBean other = (ReportProductBean) obj;
        if (account == null) {
            if (other.account != null)
                return false;
        } else if (!account.equals(other.account))
            return false;
        if (buyDate == null) {
            if (other.buyDate != null)
                return false;
        } else if (!buyDate.equals(other.buyDate))
            return false;
        if (buyMoney == null) {
            if (other.buyMoney != null)
                return false;
        } else if (!buyMoney.equals(other.buyMoney))
            return false;
        if (customerName == null) {
            if (other.customerName != null)
                return false;
        } else if (!customerName.equals(other.customerName))
            return false;
        if (customerNo == null) {
            if (other.customerNo != null)
                return false;
        } else if (!customerNo.equals(other.customerNo))
            return false;
        if (customerPhone == null) {
            if (other.customerPhone != null)
                return false;
        } else if (!customerPhone.equals(other.customerPhone))
            return false;
        if (deptName == null) {
            if (other.deptName != null)
                return false;
        } else if (!deptName.equals(other.deptName))
            return false;
        if (idCard == null) {
            if (other.idCard != null)
                return false;
        } else if (!idCard.equals(other.idCard))
            return false;
        if (moneyUnit == null) {
            if (other.moneyUnit != null)
                return false;
        } else if (!moneyUnit.equals(other.moneyUnit))
            return false;
        if (userName == null) {
            if (other.userName != null)
                return false;
        } else if (!userName.equals(other.userName))
            return false;
        return true;
    }
    public ReportProductBean(String deptName, String userName, String customerNo,
                             String customerName, String customerPhone, String buyMoney,
                             String idCard, String account, String buyDate, String moneyUnit) {
        super();
        this.deptName = deptName;
        this.userName = userName;
        this.customerNo = customerNo;
        this.customerName = customerName;
        this.customerPhone = customerPhone;
        this.buyMoney = buyMoney;
        this.idCard = idCard;
        this.account = account;
        this.buyDate = buyDate;
        this.moneyUnit = moneyUnit;
    }
    public ReportProductBean() {
        super();
    }
    
}
