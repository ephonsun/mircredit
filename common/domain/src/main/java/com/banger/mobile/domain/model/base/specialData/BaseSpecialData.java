/*
 * banger Inc.
 * Copyright (c) 2009-2013 All Rights Reserved.
 * ToDo       :特殊数据资源实体基类
 * Author     :yangy
 * Create Date:2012-6-1
 */
package com.banger.mobile.domain.model.base.specialData;

import com.banger.mobile.domain.model.base.BaseObject;
import java.sql.Date;

/**
 * Created with IntelliJ IDEA.
 * User: yangy
 * Date: 13-6-18
 * Time: 上午11:35
 * To change this template use File | Settings | File Templates.
 */
public class BaseSpecialData extends BaseObject implements java.io.Serializable {

    private static final long serialVersionUID = 1664768864425850542L;

    private Integer dataId;         //ID
    private String dataCode;        //模块代码
    private String dataName;        //模块名称
    private Integer dataParentId;   //上级模块ID
    private Integer isLeaf;         //节点类型
    private Integer sortNo;         //排序号
    private Integer isDel;          //是否删除
    private String remark;          //备注
    private Date createDate;        //创建时间
    private Date updateDate;        //更新时间
    private Integer createUser;     //创建用户
    private Integer updateUser;     //更新用户

    public Integer getDataId() {
        return dataId;
    }

    public void setDataId(Integer dataId) {
        this.dataId = dataId;
    }

    public String getDataCode() {
        return dataCode;
    }

    public void setDataCode(String dataCode) {
        this.dataCode = dataCode;
    }

    public String getDataName() {
        return dataName;
    }

    public void setDataName(String dataName) {
        this.dataName = dataName;
    }

    public Integer getDataParentId() {
        return dataParentId;
    }

    public void setDataParentId(Integer dataParentId) {
        this.dataParentId = dataParentId;
    }

    public Integer getLeaf() {
        return isLeaf;
    }

    public void setLeaf(Integer leaf) {
        isLeaf = leaf;
    }

    public Integer getSortNo() {
        return sortNo;
    }

    public void setSortNo(Integer sortNo) {
        this.sortNo = sortNo;
    }

    public Integer getDel() {
        return isDel;
    }

    public void setDel(Integer del) {
        isDel = del;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BaseSpecialData that = (BaseSpecialData) o;

        if (createDate != null ? !createDate.equals(that.createDate) : that.createDate != null) return false;
        if (createUser != null ? !createUser.equals(that.createUser) : that.createUser != null) return false;
        if (dataCode != null ? !dataCode.equals(that.dataCode) : that.dataCode != null) return false;
        if (dataId != null ? !dataId.equals(that.dataId) : that.dataId != null) return false;
        if (dataName != null ? !dataName.equals(that.dataName) : that.dataName != null) return false;
        if (dataParentId != null ? !dataParentId.equals(that.dataParentId) : that.dataParentId != null) return false;
        if (isDel != null ? !isDel.equals(that.isDel) : that.isDel != null) return false;
        if (isLeaf != null ? !isLeaf.equals(that.isLeaf) : that.isLeaf != null) return false;
        if (remark != null ? !remark.equals(that.remark) : that.remark != null) return false;
        if (sortNo != null ? !sortNo.equals(that.sortNo) : that.sortNo != null) return false;
        if (updateDate != null ? !updateDate.equals(that.updateDate) : that.updateDate != null) return false;
        if (updateUser != null ? !updateUser.equals(that.updateUser) : that.updateUser != null) return false;

        return true;
    }

    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (dataId != null ? dataId.hashCode() : 0);
        result = 31 * result + (dataCode != null ? dataCode.hashCode() : 0);
        result = 31 * result + (dataName != null ? dataName.hashCode() : 0);
        result = 31 * result + (dataParentId != null ? dataParentId.hashCode() : 0);
        result = 31 * result + (isLeaf != null ? isLeaf.hashCode() : 0);
        result = 31 * result + (sortNo != null ? sortNo.hashCode() : 0);
        result = 31 * result + (isDel != null ? isDel.hashCode() : 0);
        result = 31 * result + (remark != null ? remark.hashCode() : 0);
        result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
        result = 31 * result + (updateDate != null ? updateDate.hashCode() : 0);
        result = 31 * result + (createUser != null ? createUser.hashCode() : 0);
        result = 31 * result + (updateUser != null ? updateUser.hashCode() : 0);
        return result;
    }

    public String toString() {
        return "BaseSpecialData{" +
                "dataId=" + dataId +
                ", dataCode='" + dataCode + '\'' +
                ", dataName='" + dataName + '\'' +
                ", dataParentId=" + dataParentId +
                ", isLeaf=" + isLeaf +
                ", sortNo=" + sortNo +
                ", isDel=" + isDel +
                ", remark='" + remark + '\'' +
                ", createDate=" + createDate +
                ", updateDate=" + updateDate +
                ", createUser=" + createUser +
                ", updateUser=" + updateUser +
                '}';
    }

}

