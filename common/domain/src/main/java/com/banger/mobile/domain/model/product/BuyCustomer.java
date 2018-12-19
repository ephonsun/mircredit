/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :hk
 * Create Date:Aug 15, 2012
 */
package com.banger.mobile.domain.model.product;

import java.io.Serializable;
import java.util.Date;

/**客户购买产品的信息
 * @author hk
 * @version $Id: BuyCustomerById.java,v 0.1 Aug 15, 2012 3:36:18 PM hk Exp $
 */
public class BuyCustomer implements Serializable{

    private static final long serialVersionUID = 3392791506207650371L;
    private Integer productCustomerId;  //客户购买产品id
    private String customerNo;         //客户编号
    private String customerName;       //客户姓名
    private String phone;               //客户电话
    private String customerTitle;      //客户昵称
    private String sex;                 //客户性别
    private Integer age;                //客户年龄
    private Double buyMoney;           //销售额
    private String idCard;             //身份证
    private String bankAccount;        //账号
    private Date buyDate;              //购买日期
    private String deptName;            //营销人员机构
    private String account;             //营销人员
    private String lineNumber;          //行号
    private String failureReason;       //失败原因
    private String buyDateStr;          //导入的日期类型
    private Integer customerId;         //客户id
    private Integer isDeal;             //是否删除
    private String buyMoneyStr;         //销售额字符串
    private Integer userId;             //营销人员id
    public Integer getProductCustomerId() {
        return productCustomerId;
    }
    public void setProductCustomerId(Integer productCustomerId) {
        this.productCustomerId = productCustomerId;
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
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
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
    public Double getBuyMoney() {
        return buyMoney;
    }
    public void setBuyMoney(Double buyMoney) {
        this.buyMoney = buyMoney;
    }
    public String getIdCard() {
        return idCard;
    }
    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }
    public String getBankAccount() {
        return bankAccount;
    }
    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }
    public Date getBuyDate() {
        return buyDate;
    }
    public void setBuyDate(Date buyDate) {
        this.buyDate = buyDate;
    }
    public String getDeptName() {
        return deptName;
    }
    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }
    public String getAccount() {
        return account;
    }
    public void setAccount(String account) {
        this.account = account;
    }
    public String getLineNumber() {
        return lineNumber;
    }
    public void setLineNumber(String lineNumber) {
        this.lineNumber = lineNumber;
    }
    public String getFailureReason() {
        return failureReason;
    }
    public void setFailureReason(String failureReason) {
        this.failureReason = failureReason;
    }
    public String getBuyDateStr() {
        return buyDateStr;
    }
    public void setBuyDateStr(String buyDateStr) {
        this.buyDateStr = buyDateStr;
    }
    public Integer getCustomerId() {
        return customerId;
    }
    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }
    public Integer getIsDeal() {
        return isDeal;
    }
    public void setIsDeal(Integer isDeal) {
        this.isDeal = isDeal;
    }
    public String getBuyMoneyStr() {
        return buyMoneyStr;
    }
    public void setBuyMoneyStr(String buyMoneyStr) {
        this.buyMoneyStr = buyMoneyStr;
    }
    public Integer getUserId() {
        return userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((account == null) ? 0 : account.hashCode());
        result = prime * result + ((age == null) ? 0 : age.hashCode());
        result = prime * result + ((bankAccount == null) ? 0 : bankAccount.hashCode());
        result = prime * result + ((buyDate == null) ? 0 : buyDate.hashCode());
        result = prime * result + ((buyDateStr == null) ? 0 : buyDateStr.hashCode());
        result = prime * result + ((buyMoney == null) ? 0 : buyMoney.hashCode());
        result = prime * result + ((buyMoneyStr == null) ? 0 : buyMoneyStr.hashCode());
        result = prime * result + ((customerId == null) ? 0 : customerId.hashCode());
        result = prime * result + ((customerName == null) ? 0 : customerName.hashCode());
        result = prime * result + ((customerNo == null) ? 0 : customerNo.hashCode());
        result = prime * result + ((customerTitle == null) ? 0 : customerTitle.hashCode());
        result = prime * result + ((deptName == null) ? 0 : deptName.hashCode());
        result = prime * result + ((failureReason == null) ? 0 : failureReason.hashCode());
        result = prime * result + ((idCard == null) ? 0 : idCard.hashCode());
        result = prime * result + ((isDeal == null) ? 0 : isDeal.hashCode());
        result = prime * result + ((lineNumber == null) ? 0 : lineNumber.hashCode());
        result = prime * result + ((phone == null) ? 0 : phone.hashCode());
        result = prime * result + ((productCustomerId == null) ? 0 : productCustomerId.hashCode());
        result = prime * result + ((sex == null) ? 0 : sex.hashCode());
        result = prime * result + ((userId == null) ? 0 : userId.hashCode());
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
        final BuyCustomer other = (BuyCustomer) obj;
        if (account == null) {
            if (other.account != null)
                return false;
        } else if (!account.equals(other.account))
            return false;
        if (age == null) {
            if (other.age != null)
                return false;
        } else if (!age.equals(other.age))
            return false;
        if (bankAccount == null) {
            if (other.bankAccount != null)
                return false;
        } else if (!bankAccount.equals(other.bankAccount))
            return false;
        if (buyDate == null) {
            if (other.buyDate != null)
                return false;
        } else if (!buyDate.equals(other.buyDate))
            return false;
        if (buyDateStr == null) {
            if (other.buyDateStr != null)
                return false;
        } else if (!buyDateStr.equals(other.buyDateStr))
            return false;
        if (buyMoney == null) {
            if (other.buyMoney != null)
                return false;
        } else if (!buyMoney.equals(other.buyMoney))
            return false;
        if (buyMoneyStr == null) {
            if (other.buyMoneyStr != null)
                return false;
        } else if (!buyMoneyStr.equals(other.buyMoneyStr))
            return false;
        if (customerId == null) {
            if (other.customerId != null)
                return false;
        } else if (!customerId.equals(other.customerId))
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
        if (customerTitle == null) {
            if (other.customerTitle != null)
                return false;
        } else if (!customerTitle.equals(other.customerTitle))
            return false;
        if (deptName == null) {
            if (other.deptName != null)
                return false;
        } else if (!deptName.equals(other.deptName))
            return false;
        if (failureReason == null) {
            if (other.failureReason != null)
                return false;
        } else if (!failureReason.equals(other.failureReason))
            return false;
        if (idCard == null) {
            if (other.idCard != null)
                return false;
        } else if (!idCard.equals(other.idCard))
            return false;
        if (isDeal == null) {
            if (other.isDeal != null)
                return false;
        } else if (!isDeal.equals(other.isDeal))
            return false;
        if (lineNumber == null) {
            if (other.lineNumber != null)
                return false;
        } else if (!lineNumber.equals(other.lineNumber))
            return false;
        if (phone == null) {
            if (other.phone != null)
                return false;
        } else if (!phone.equals(other.phone))
            return false;
        if (productCustomerId == null) {
            if (other.productCustomerId != null)
                return false;
        } else if (!productCustomerId.equals(other.productCustomerId))
            return false;
        if (sex == null) {
            if (other.sex != null)
                return false;
        } else if (!sex.equals(other.sex))
            return false;
        if (userId == null) {
            if (other.userId != null)
                return false;
        } else if (!userId.equals(other.userId))
            return false;
        return true;
    }
    public BuyCustomer(Integer productCustomerId, String customerNo, String customerName,
                       String phone, String customerTitle, String sex, Integer age,
                       Double buyMoney, String idCard, String bankAccount, Date buyDate,
                       String deptName, String account, String lineNumber, String failureReason,
                       String buyDateStr, Integer customerId, Integer isDeal, String buyMoneyStr,
                       Integer userId) {
        super();
        this.productCustomerId = productCustomerId;
        this.customerNo = customerNo;
        this.customerName = customerName;
        this.phone = phone;
        this.customerTitle = customerTitle;
        this.sex = sex;
        this.age = age;
        this.buyMoney = buyMoney;
        this.idCard = idCard;
        this.bankAccount = bankAccount;
        this.buyDate = buyDate;
        this.deptName = deptName;
        this.account = account;
        this.lineNumber = lineNumber;
        this.failureReason = failureReason;
        this.buyDateStr = buyDateStr;
        this.customerId = customerId;
        this.isDeal = isDeal;
        this.buyMoneyStr = buyMoneyStr;
        this.userId = userId;
    }
    public BuyCustomer() {
        super();
    }

    
}
