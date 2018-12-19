/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :cheny
 * Create Date:2012-9-10
 */
package com.banger.mobile.domain.model.base.crmModuleExport;

import java.util.Date;

import com.banger.mobile.domain.model.base.BaseObject;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author cheny
 * @version $Id: BaseCrmModuleExport.java,v 0.1 2012-9-10 上午10:50:33 cheny Exp $
 */
public class BaseCrmModuleExport  extends BaseObject {

    private static final long serialVersionUID = 4677914274611360730L;
    
    private Integer     moduleExportID;           //导出模块id
    private Integer     userId;                   //用户id
    private String      moduleName;               //模块名     customer,record
    private String      feildName;                //字段名
    private Date        createDate;               //创建时间
    private Date        updateDate;               //更新时间
    private Integer     createUser;              //新建者
    private Integer     updateUser;              //修改者
    /**
     * 
     */
    public BaseCrmModuleExport() {
        super();
    }
    /**
     * @param moduleExportID
     * @param userId
     * @param moduleName
     * @param feildName
     * @param createDate
     * @param updateDate
     * @param createUser
     * @param updateUser
     */
    public BaseCrmModuleExport(Integer moduleExportID, Integer userId, String moduleName,
                               String feildName, Date createDate, Date updateDate,
                               Integer createUser, Integer updateUser) {
        super();
        this.moduleExportID = moduleExportID;
        this.userId = userId;
        this.moduleName = moduleName;
        this.feildName = feildName;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.createUser = createUser;
        this.updateUser = updateUser;
    }
    public Integer getModuleExportID() {
        return moduleExportID;
    }
    public void setModuleExportID(Integer moduleExportID) {
        this.moduleExportID = moduleExportID;
    }
    public Integer getUserId() {
        return userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public String getModuleName() {
        return moduleName;
    }
    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }
    public String getFeildName() {
        return feildName;
    }
    public void setFeildName(String feildName) {
        this.feildName = feildName;
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
    /**
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object) {
        if (!(object instanceof BaseCrmModuleExport)) {
            return false;
        }
        BaseCrmModuleExport rhs = (BaseCrmModuleExport) object;
        return new EqualsBuilder().appendSuper(super.equals(object))
            .append(this.moduleExportID, rhs.moduleExportID)
            .append(this.createUser, rhs.createUser).append(this.moduleName, rhs.moduleName)
            .append(this.userId, rhs.userId).append(this.createDate, rhs.createDate)
            .append(this.updateDate, rhs.updateDate).append(this.feildName, rhs.feildName)
            .append(this.updateUser, rhs.updateUser).isEquals();
    }
    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(-634557937, -1467654165).appendSuper(super.hashCode())
            .append(this.moduleExportID).append(this.createUser).append(this.moduleName)
            .append(this.userId).append(this.createDate).append(this.updateDate)
            .append(this.feildName).append(this.updateUser).toHashCode();
    }
    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this).append("feildName", this.feildName)
            .append("userId", this.userId).append("moduleExportID", this.moduleExportID)
            .append("createDate", this.createDate).append("moduleName", this.moduleName)
            .append("updateDate", this.updateDate).append("endRow", this.getEndRow())
            .append("createUser", this.createUser).append("updateUser", this.updateUser)
            .append("startRow", this.getStartRow()).toString();
    }
    
    
    
    
}
