/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :QianJie
 * Create Date:May 28, 2012
 */
package com.banger.mobile.domain.model.base.templateField;

import java.io.Serializable;
import java.util.Date;

import com.banger.mobile.domain.model.base.BaseObject;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author QianJie
 * @version $Id: BaseCrmTemplateField.java,v 0.1 May 28, 2012 2:25:50 PM QianJie Exp $
 */
public class BaseCrmTemplateField extends BaseObject implements Serializable{

    private static final long serialVersionUID = 6038653928132051147L;
    
    private int               templateFieldId;                        //主键ID
    private int               templateId;                             //模版ID
    private int               isDel;                                  //是否删除
    private String            templateFieldName;                      //字段名称
    private String            templateFieldType;                      //字段类型
    private String            extFieldName;                           //关联的自定义字段扩展字段
    private int               sortNo;                                 //排序号
    private int               isPopUp;                                //是否来电弹屏显示
    private String            measurement;                            //单位
    private Date              createDate;                             //创建时间
    private Date              updateDate;                             //更新时间
    private int               createUser;                             //创建用户
    private int               updateUser;                             //更新用户
    
    
    public BaseCrmTemplateField() {
        super();
    }
    
    
    public BaseCrmTemplateField(int templateFieldId, int templateId, int isDel,
                                String templateFieldName, String templateFieldType, int sortNo,
                                int isPopUp, String measurement, Date createDate, Date updateDate,
                                int createUser, int updateUser) {
        super();
        this.templateFieldId = templateFieldId;
        this.templateId = templateId;
        this.isDel = isDel;
        this.templateFieldName = templateFieldName;
        this.templateFieldType = templateFieldType;
        this.sortNo = sortNo;
        this.isPopUp = isPopUp;
        this.measurement = measurement;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.createUser = createUser;
        this.updateUser = updateUser;
    }


    public int getTemplateFieldId() {
        return templateFieldId;
    }
    public void setTemplateFieldId(int templateFieldId) {
        this.templateFieldId = templateFieldId;
    }
    public int getTemplateId() {
        return templateId;
    }
    public void setTemplateId(int templateId) {
        this.templateId = templateId;
    }
    public int getIsDel() {
        return isDel;
    }
    public void setIsDel(int isDel) {
        this.isDel = isDel;
    }
    public String getTemplateFieldName() {
        return templateFieldName;
    }
    public void setTemplateFieldName(String templateFieldName) {
        this.templateFieldName = templateFieldName;
    }
    public String getTemplateFieldType() {
        return templateFieldType;
    }
    public void setTemplateFieldType(String templateFieldType) {
        this.templateFieldType = templateFieldType;
    }
    public int getSortNo() {
        return sortNo;
    }
    public void setSortNo(int sortNo) {
        this.sortNo = sortNo;
    }
    public String getExtFieldName() {
        return extFieldName;
    }
    public void setExtFieldName(String extFieldName) {
        this.extFieldName = extFieldName;
    }
    public int getIsPopUp() {
        return isPopUp;
    }
    public void setIsPopUp(int isPopUp) {
        this.isPopUp = isPopUp;
    }
    public String getMeasurement() {
        return measurement;
    }
    public void setMeasurement(String measurement) {
        this.measurement = measurement;
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
    public int getCreateUser() {
        return createUser;
    }
    public void setCreateUser(int createUser) {
        this.createUser = createUser;
    }
    public int getUpdateUser() {
        return updateUser;
    }
    public void setUpdateUser(int updateUser) {
        this.updateUser = updateUser;
    }

    /**
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object) {
        if (!(object instanceof BaseCrmTemplateField)) {
            return false;
        }
        BaseCrmTemplateField rhs = (BaseCrmTemplateField) object;
        return new EqualsBuilder().appendSuper(super.equals(object)).append(this.extFieldName,
            rhs.extFieldName).append(this.templateId, rhs.templateId).append(this.measurement,
            rhs.measurement).append(this.updateDate, rhs.updateDate).append(this.isPopUp,
            rhs.isPopUp).append(this.templateFieldId, rhs.templateFieldId).append(
            this.templateFieldName, rhs.templateFieldName).append(this.isDel, rhs.isDel).append(
            this.sortNo, rhs.sortNo).append(this.templateFieldType, rhs.templateFieldType).append(
            this.createDate, rhs.createDate).append(this.createUser, rhs.createUser).append(
            this.updateUser, rhs.updateUser).isEquals();
    }


    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(-1439487507, -883847335).appendSuper(super.hashCode()).append(
            this.extFieldName).append(this.templateId).append(this.measurement).append(
            this.updateDate).append(this.isPopUp).append(this.templateFieldId).append(
            this.templateFieldName).append(this.isDel).append(this.sortNo).append(
            this.templateFieldType).append(this.createDate).append(this.createUser).append(
            this.updateUser).toHashCode();
    }


    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this).append("startRow", this.getStartRow()).append(
            "templateFieldType", this.templateFieldType).append("updateDate", this.updateDate)
            .append("templateFieldName", this.templateFieldName).append("endRow", this.getEndRow())
            .append("updateUser", this.updateUser).append("createUser", this.createUser).append(
                "isPopUp", this.isPopUp).append("templateFieldId", this.templateFieldId).append(
                "templateId", this.templateId).append("sortNo", this.sortNo).append("extFieldName",
                this.extFieldName).append("measurement", this.measurement).append("isDel",
                this.isDel).append("createDate", this.createDate).toString();
    }





}
