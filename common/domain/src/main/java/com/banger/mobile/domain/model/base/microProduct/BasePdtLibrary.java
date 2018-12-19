/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :产品知识库-Domain
 * Author     :QianJie
 * Create Date:Nov 12, 2012
 */
package com.banger.mobile.domain.model.base.microProduct;

import java.io.Serializable;
import java.util.Date;

import com.banger.mobile.domain.annotation.EscapeClassFilter;
import com.banger.mobile.domain.model.base.BaseObject;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author QianJie
 * @version $Id: BasePdtLibrary.java,v 0.1 Nov 12, 2012 3:27:07 PM QianJie Exp $
 */
@EscapeClassFilter
public class BasePdtLibrary extends BaseObject implements Serializable {

    private static final long serialVersionUID = 4349444084640121717L;

    private Integer           libraryId;                              //主键
    private Integer           parentId;                               //上级ID
    private Integer           isLeaf;                                 //节点类型 0:树枝节点    1:叶子节点
    private String            libTitle;                               //标题
    private String            libNo;                                  //知识编号
    private String            libContent;                             //知识内容
    private String            remark;                                 //知识备注
    private Integer           sortNo;                                 //排序号    
    private Integer           isDel;                                  //是否删除
    private Date              createDate;                             //创建时间
    private Date              updateDate;                             //更新时间
    private Integer           createUser;                             //创建用户
    private Integer           updateUser;                             //更新用户
    private String            libTitlePinyin;                         //知识标题拼音
    
    public BasePdtLibrary() {
        super();
    }

    public BasePdtLibrary(Integer libraryId, Integer parentId, Integer isLeaf, String libTitle,
                          String libNo, String libContent, String remark,
                          Integer sortNo, Integer isDel, Date createDate, Date updateDate,
                          Integer createUser, Integer updateUser) {
        super();
        this.libraryId = libraryId;
        this.parentId = parentId;
        this.isLeaf = isLeaf;
        this.libTitle = libTitle;
        this.libNo = libNo;
        this.libContent = libContent;
        this.remark = remark;
        this.sortNo = sortNo;
        this.isDel = isDel;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.createUser = createUser;
        this.updateUser = updateUser;
    }

    public Integer getLibraryId() {
        return libraryId;
    }

    public void setLibraryId(Integer libraryId) {
        this.libraryId = libraryId;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getIsLeaf() {
        return isLeaf;
    }

    public void setIsLeaf(Integer isLeaf) {
        this.isLeaf = isLeaf;
    }

    public String getLibTitle() {
        return libTitle;
    }

    public void setLibTitle(String libTitle) {
        this.libTitle = libTitle;
    }

    public String getLibNo() {
        return libNo;
    }

    public void setLibNo(String libNo) {
        this.libNo = libNo;
    }

    public String getLibContent() {
        return libContent;
    }

    public void setLibContent(String libContent) {
        this.libContent = libContent;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getSortNo() {
        return sortNo;
    }

    public void setSortNo(Integer sortNo) {
        this.sortNo = sortNo;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
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

    public String getLibTitlePinyin() {
        return libTitlePinyin;
    }

    public void setLibTitlePinyin(String libTitlePinyin) {
        this.libTitlePinyin = libTitlePinyin;
    }

    /**
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object) {
        if (!(object instanceof BasePdtLibrary)) {
            return false;
        }
        BasePdtLibrary rhs = (BasePdtLibrary) object;
        return new EqualsBuilder().appendSuper(super.equals(object)).append(this.parentId,
            rhs.parentId).append(this.remark, rhs.remark).append(this.updateDate, rhs.updateDate)
            .append(this.libContent, rhs.libContent).append(this.libraryId, rhs.libraryId).append(
                this.libTitle, rhs.libTitle).append(this.sortNo, rhs.sortNo).append(this.isDel,
                rhs.isDel).append(this.libNo, rhs.libNo).append(this.isLeaf, rhs.isLeaf).append(
                this.createDate, rhs.createDate).append(this.createUser, rhs.createUser).append(
                this.updateUser, rhs.updateUser).isEquals();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(1882556981, 604046021).appendSuper(super.hashCode()).append(
            this.parentId).append(this.remark).append(this.updateDate).append(this.libContent)
            .append(this.libraryId).append(this.libTitle).append(this.sortNo).append(this.isDel)
            .append(this.libNo).append(this.isLeaf).append(this.createDate).append(this.createUser)
            .append(this.updateUser).toHashCode();
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this).append("startRow", this.getStartRow()).append("isLeaf",
            this.isLeaf).append("parentId", this.parentId).append("updateDate", this.updateDate)
            .append("endRow", this.getEndRow()).append("updateUser", this.updateUser).append(
                "createUser", this.createUser).append("libraryId", this.libraryId).append("sortNo",
                this.sortNo).append("libNo", this.libNo).append("libContent", this.libContent)
            .append("libTitle", this.libTitle).append("isDel", this.isDel).append("remark",
                this.remark).append("createDate", this.createDate).toString();
    }

    
    
}
