/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :PAD管理表实体
 * Author     :liyb
 * Create Date:2013-6-17
 */
package com.banger.mobile.domain.model.base.padManagement;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.banger.mobile.domain.model.base.BaseObject;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author liyb
 * @version $Id: BasePadInfo.java,v 0.1 2013-6-17 下午02:23:47 liyb Exp $
 */
public class BasePadInfo extends BaseObject implements Serializable {

    private static final long serialVersionUID = -3481036059964846154L;

    private Integer           padInfoId;                                //主键ID
    private String            padCode;                                  //编号
    private String            serialNumber;                             //序列号
    private Integer           brandTypeId;                              //品牌ID
    private Integer           brandSubTypeId;                           //型号ID
    private Integer           useStatus;                                //状态 1:启用 2:禁用 3:停用
    private Integer           userId;                                   //使用人员
    private Date              createDate;                              //创建时间
    private Date              updateDate;                              //更新时间
    private Integer           createUser;                              //创建用户
    private Integer           updateUser;                              //更新用户

    private String            usePersonnel;                            //使用人员
    private String            loginName;                               //登录人
    private Date              loginDate;                               //最近登录时间
    private Integer           logType;                                 //登录类型   1:登入 2:登出
    private String            brandTypeName;                           //品牌
    private String            padSubTypeName;                          //型号
    private BigDecimal        countUpload;                             //上传总流量
    private BigDecimal        countDownLoad;                           //下载总流量


    private String brandType;//品牌
    private String brandSubType;//型号
    private Integer status ;//状态    1 启动   0   禁用
    private String            userName;                            //使用人员


    public String getBrandType() {
        return brandType;
    }

    public void setBrandType(String brandType) {
        this.brandType = brandType;
    }

    public String getBrandSubType() {
        return brandSubType;
    }

    public void setBrandSubType(String brandSubType) {
        this.brandSubType = brandSubType;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getPadInfoId() {
        return padInfoId;
    }
    public void setPadInfoId(Integer padInfoId) {
        this.padInfoId = padInfoId;
    }
    public String getPadCode() {
        return padCode;
    }
    public void setPadCode(String padCode) {
        this.padCode = padCode;
    }
    public String getSerialNumber() {
        return serialNumber;
    }
    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }
    public Integer getBrandTypeId() {
        return brandTypeId;
    }
    public void setBrandTypeId(Integer brandTypeId) {
        this.brandTypeId = brandTypeId;
    }
    public Integer getBrandSubTypeId() {
        return brandSubTypeId;
    }
    public void setBrandSubTypeId(Integer brandSubTypeId) {
        this.brandSubTypeId = brandSubTypeId;
    }
    public Integer getUseStatus() {
        return useStatus;
    }
    public void setUseStatus(Integer useStatus) {
        this.useStatus = useStatus;
    }
    public Integer getUserId() {
        return userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
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
    public String getUsePersonnel() {
        return usePersonnel;
    }
    public void setUsePersonnel(String usePersonnel) {
        this.usePersonnel = usePersonnel;
    }
    public Date getLoginDate() {
        return loginDate;
    }
    public void setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
    }
    public String getBrandTypeName() {
        return brandTypeName;
    }
    public void setBrandTypeName(String brandTypeName) {
        this.brandTypeName = brandTypeName;
    }
    public String getPadSubTypeName() {
        return padSubTypeName;
    }
    public void setPadSubTypeName(String padSubTypeName) {
        this.padSubTypeName = padSubTypeName;
    }
    public Integer getLogType() {
        return logType;
    }
    public void setLogType(Integer logType) {
        this.logType = logType;
    }
    public BigDecimal getCountUpload() {
        return countUpload;
    }
    public void setCountUpload(BigDecimal countUpload) {
        this.countUpload = countUpload;
    }
    public BigDecimal getCountDownLoad() {
        return countDownLoad;
    }
    public void setCountDownLoad(BigDecimal countDownLoad) {
        this.countDownLoad = countDownLoad;
    }
    public String getLoginName() {
        return loginName;
    }
    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }
    /**
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object) {
        if (!(object instanceof BasePadInfo)) {
            return false;
        }
        BasePadInfo rhs = (BasePadInfo) object;
        return new EqualsBuilder().appendSuper(super.equals(object)).append(this.padCode,
                rhs.padCode).append(this.createUser, rhs.createUser).append(this.brandTypeId,
            rhs.brandTypeId).append(this.userId, rhs.userId).append(this.brandSubTypeId,
            rhs.brandSubTypeId).append(this.serialNumber, rhs.serialNumber).append(this.padInfoId,
            rhs.padInfoId).append(this.createDate, rhs.createDate).append(this.updateDate,
            rhs.updateDate).append(this.updateUser, rhs.updateUser).append(this.useStatus,
            rhs.useStatus).append(this.brandType, rhs.brandType).append(this.brandSubType, rhs.brandSubType).append(this.status, rhs.status).isEquals();
    }
    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(-118578827, -527786239).appendSuper(super.hashCode()).append(
                this.padCode).append(this.createUser).append(this.brandTypeId).append(this.userId)
            .append(this.brandSubTypeId).append(this.serialNumber).append(this.padInfoId).append(
                this.createDate).append(this.updateDate).append(this.updateUser).append(this.useStatus).append(this.brandType).append(this.brandSubType).append(this.status).toHashCode();
    }
    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this).append("brandSubTypeId", this.brandSubTypeId).append(
                "userId", this.userId).append("padInfoId", this.padInfoId).append("createDate",
                this.createDate).append("useStatus", this.useStatus).append("updateDate",
            this.updateDate).append("brandTypeId", this.brandTypeId).append("serialNumber",
            this.serialNumber).append("endRow", this.getEndRow()).append("padCode", this.padCode)
            .append("createUser", this.createUser).append("updateUser", this.updateUser).append("brandType", this.brandType).append("brandSubType", this.brandSubType).append("status", this.status).append(
                "startRow", this.getStartRow()).toString();
    }
}
