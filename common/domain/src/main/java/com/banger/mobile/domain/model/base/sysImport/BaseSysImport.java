/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :yangy
 * Create Date:2012-8-27
 */
package com.banger.mobile.domain.model.base.sysImport;

import java.io.Serializable;
import java.util.Date;

import com.banger.mobile.domain.model.base.BaseObject;
import org.apache.commons.lang.builder.CompareToBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author yangyang
 * @version $Id: BaseSysImport.java,v 0.1 2012-8-27 上午10:58:40 yangyong Exp $
 */
public class BaseSysImport  extends BaseObject implements Comparable, Serializable{

    private static final long serialVersionUID = 6768488879846802711L;

    private Integer importId;                        //编号  
    private String  importNo;                        //批次号
    private Date    importDate;                      //导入时间
    private Integer totalNum;                        //导入购买记录总数
    private Integer successNum;                      //导入购买记录成功数
    private Integer failNum;                         //导入购买记录失败数
    private String failFilePath;                     //导入失败后文件保存路径
    private Date createDate;                         //创建时间
    private Date updateDate;                         //更新时间
    private Integer   createUser;                    //创建用户
    private Integer   updateUser;                    //更新用户
    public Integer getImportId() {
        return importId;
    }
    
    public String getFailFilePath() {
        return failFilePath;
    }

    public void setFailFilePath(String failFilePath) {
        this.failFilePath = failFilePath;
    }

    public void setImportId(Integer importId) {
        this.importId = importId;
    }
    public String getImportNo() {
        return importNo;
    }
    public void setImportNo(String importNo) {
        this.importNo = importNo;
    }
    public Date getImportDate() {
        return importDate;
    }
    public void setImportDate(Date importDate) {
        this.importDate = importDate;
    }
    public Integer getTotalNum() {
        return totalNum;
    }
    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }
    public Integer getSuccessNum() {
        return successNum;
    }
    public void setSuccessNum(Integer successNum) {
        this.successNum = successNum;
    }
    public Integer getFailNum() {
        return failNum;
    }
    public void setFailNum(Integer failNum) {
        this.failNum = failNum;
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
     * @see java.lang.Comparable#compareTo(Object)
     */
    public int compareTo(Object object) {
        BaseSysImport myClass = (BaseSysImport) object;
        return new CompareToBuilder().append(this.failNum, myClass.failNum)
            .append(this.totalNum, myClass.totalNum).append(this.createUser, myClass.createUser)
            .append(this.successNum, myClass.successNum)
            .append(this.importDate, myClass.importDate).append(this.importId, myClass.importId)
            .append(this.createDate, myClass.createDate)
            .append(this.updateDate, myClass.updateDate).append(this.importNo, myClass.importNo)
            .append(this.updateUser, myClass.updateUser).toComparison();
    }
    /**
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object) {
        if (!(object instanceof BaseSysImport)) {
            return false;
        }
        BaseSysImport rhs = (BaseSysImport) object;
        return new EqualsBuilder().appendSuper(super.equals(object))
            .append(this.failNum, rhs.failNum).append(this.totalNum, rhs.totalNum)
            .append(this.createUser, rhs.createUser).append(this.successNum, rhs.successNum)
            .append(this.importDate, rhs.importDate).append(this.importId, rhs.importId)
            .append(this.createDate, rhs.createDate).append(this.updateDate, rhs.updateDate)
            .append(this.importNo, rhs.importNo).append(this.updateUser, rhs.updateUser).isEquals();
    }
    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(-1602043647, 136016097).appendSuper(super.hashCode())
            .append(this.failNum).append(this.totalNum).append(this.createUser)
            .append(this.successNum).append(this.importDate).append(this.importId)
            .append(this.createDate).append(this.updateDate).append(this.importNo)
            .append(this.updateUser).toHashCode();
    }
    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this).append("importNo", this.importNo)
            .append("createDate", this.createDate).append("importId", this.importId)
            .append("failNum", this.failNum).append("updateDate", this.updateDate)
            .append("totalNum", this.totalNum).append("successNum", this.successNum)
            .append("endRow", this.getEndRow()).append("importDate", this.importDate)
            .append("createUser", this.createUser).append("updateUser", this.updateUser)
            .append("startRow", this.getStartRow()).toString();
    }
    
}
