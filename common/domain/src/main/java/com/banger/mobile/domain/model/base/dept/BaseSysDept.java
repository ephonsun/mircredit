/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :部门实体
 * Author     :cheny
 * Create Date:2012-3-28
 */
package com.banger.mobile.domain.model.base.dept;



import java.util.Date;

import com.banger.mobile.domain.model.base.BaseObject;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/**
 * @author cheny
 * @version $Id: DeptDao.java,v 0.1 2012-3-28 下午1:07:59 cheny Exp $
 */
public class BaseSysDept extends BaseObject implements java.io.Serializable {

    private static final long serialVersionUID = -3684033472251264452L;
    
    private Integer deptId;                     //部门ID
    private String deptName;                 //部门名称
    private Integer deptParentId;            //上级部门ID
    private String deptCode;                 //部门编号
    private String deptSearchCode;           //自定义部门编号
    private Integer sortno;                  // 排序号
    private Integer isLeaf;                  //节点类型
    private Integer isDel;                   //伪删除
    private String remark;                   //备注
    private Date createDate;            //创建时间
    private Date updateDate;            //更新时间
    private Integer createUser;              //创建用户
    private Integer updateUser;              //更新用户
    
    
    /**
     * 
     */
    public BaseSysDept() {
        super();
    }
    
    
    /**
     * @param deptId
     * @param deptName
     * @param deptParentId
     * @param deptCode
     * @param deptSearchCode
     * @param sortno
     * @param isLeaf
     * @param isDel
     * @param remark
     * @param createDate
     * @param updateDate
     * @param createUser
     * @param updateUser
     */
    public BaseSysDept(Integer deptId, String deptName, Integer deptParentId, String deptCode,
                       String deptSearchCode, Integer sortno, Integer isLeaf, Integer isDel,
                       String remark, Date createDate, Date updateDate, Integer createUser,
                       Integer updateUser) {
        super();
        this.deptId = deptId;
        this.deptName = deptName;
        this.deptParentId = deptParentId;
        this.deptCode = deptCode;
        this.deptSearchCode = deptSearchCode;
        this.sortno = sortno;
        this.isLeaf = isLeaf;
        this.isDel = isDel;
        this.remark = remark;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.createUser = createUser;
        this.updateUser = updateUser;
    }


    public Integer getDeptId() {
        return deptId;
    }
    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }
    public String getDeptName() {
        return deptName;
    }
    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }
    public Integer getDeptParentId() {
        return deptParentId;
    }
    public void setDeptParentId(Integer deptParentId) {
        this.deptParentId = deptParentId;
    }
    public String getDeptCode() {
        return deptCode;
    }
    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }
    public String getDeptSearchCode() {
        return deptSearchCode;
    }
    public void setDeptSearchCode(String deptSearchCode) {
        this.deptSearchCode = deptSearchCode;
    }
    public Integer getSortno() {
        return sortno;
    }
    public void setSortno(Integer sortno) {
        this.sortno = sortno;
    }
    public Integer getIsLeaf() {
        return isLeaf;
    }
    public void setIsLeaf(Integer isLeaf) {
        this.isLeaf = isLeaf;
    }
    public Integer getIsDel() {
        return isDel;
    }
    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
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


    /**
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object) {
        if (!(object instanceof BaseSysDept)) {
            return false;
        }
        BaseSysDept rhs = (BaseSysDept) object;
        return new EqualsBuilder().appendSuper(super.equals(object))
            .append(this.deptName, rhs.deptName).append(this.remark, rhs.remark)
            .append(this.updateDate, rhs.updateDate).append(this.sortno, rhs.sortno)
            .append(this.isDel, rhs.isDel).append(this.createUser, rhs.createUser)
            .append(this.isLeaf, rhs.isLeaf).append(this.deptCode, rhs.deptCode)
            .append(this.deptSearchCode, rhs.deptSearchCode)
            .append(this.createDate, rhs.createDate).append(this.deptId, rhs.deptId)
            .append(this.deptParentId, rhs.deptParentId).append(this.updateUser, rhs.updateUser)
            .isEquals();
    }


    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(-793364967, -1084211689).appendSuper(super.hashCode())
            .append(this.deptName).append(this.remark).append(this.updateDate).append(this.sortno)
            .append(this.isDel).append(this.createUser).append(this.isLeaf).append(this.deptCode)
            .append(this.deptSearchCode).append(this.createDate).append(this.deptId)
            .append(this.deptParentId).append(this.updateUser).toHashCode();
    }


    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this).append("deptSearchCode", this.deptSearchCode)
            .append("createDate", this.createDate).append("updateDate", this.updateDate)
            .append("isDel", this.isDel).append("remark", this.remark)
            .append("deptName", this.deptName).append("sortno", this.sortno)
            .append("deptId", this.deptId).append("endRow", this.getEndRow())
            .append("deptCode", this.deptCode).append("createUser", this.createUser)
            .append("deptParentId", this.deptParentId).append("updateUser", this.updateUser)
            .append("isLeaf", this.isLeaf).append("startRow", this.getStartRow()).toString();
    }
    
    

}