/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :产品意向客户-Domain-扩展2
 * Author     :QianJie
 * Create Date:Nov 12, 2012
 */
package com.banger.mobile.domain.model.microProduct;

import com.banger.mobile.domain.model.base.microProduct.BasePdtProductCustomer;

/**
 * @author QianJie
 * @version $Id: PdtProductCustomer.java,v 0.1 Nov 12, 2012 4:08:48 PM QianJie
 *          Exp $
 */
public class PdtProductCustomerBean extends BasePdtProductCustomer {

    private static final long serialVersionUID = 4422663138523712082L;

    private String            customerNo;                             // 客户编号
    private String            customerName;                           // 客户姓名
    private String            customerTitle;                          // 称谓
    private String            customerTypeName;                       // 客户类型名字
    private Integer           customerTypeId;                         // 客户类型id
    private String            sex;                                    // 性别
    private Integer           age;                                    // 年龄
    private Integer           defaultPhoneType;                       // 默认号码类型
    private String            mobilePhone1;                           // 手机1
    private String            mobilePhone2;                           // 手机2
    private String            phone;                                  // 固话
    private String            fax;                                    // 传真
    private String            cphNumber;                              // 联系电话
    private String            replaceNumber;                          // ****联系电话
    private String            userName;                               // 添加人员
    private String            belongUserName;                         // 归属人员
    private String            productName;                            // 产品名称
    private Integer           belongDeptId;                           // 归属机构ID
    private Integer           belongUserId;                           // 归属人员ID
    private String            address;                                // 地址
    private String            idCard;                                 // 身份证号码
    private Boolean           isMyAuthority;                          // 是否是我权限范围内的客户
    private String            email;                                  // 客户邮箱
    private Integer           isNoGood;                               // 不良客户

    public PdtProductCustomerBean() {
        super();
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

    public Integer getCustomerTypeId() {
        return customerTypeId;
    }

    public void setCustomerTypeId(Integer customerTypeId) {
        this.customerTypeId = customerTypeId;
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

    public String getReplaceNumber() {
        return replaceNumber;
    }

    public void setReplaceNumber(String replaceNumber) {
        this.replaceNumber = replaceNumber;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getBelongUserName() {
        return belongUserName;
    }

    public void setBelongUserName(String belongUserName) {
        this.belongUserName = belongUserName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
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

    public Boolean getIsMyAuthority() {
        return isMyAuthority;
    }

    public void setIsMyAuthority(Boolean isMyAuthority) {
        this.isMyAuthority = isMyAuthority;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCustomerTypeName() {
        return customerTypeName;
    }

    public void setCustomerTypeName(String customerTypeName) {
        this.customerTypeName = customerTypeName;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
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
}
