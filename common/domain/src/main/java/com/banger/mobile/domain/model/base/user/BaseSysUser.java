/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :用户基础类
 * Author     :yangy
 * Create Date:2012-5-17
 */
package com.banger.mobile.domain.model.base.user;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

import org.apache.axis2.databinding.types.soapencoding.Decimal;
import org.apache.commons.lang.builder.*;

import com.banger.mobile.domain.model.base.BaseObject;
import org.apache.log4j.Logger;

/**
 * @author yangy
 * @version $Id: BaseSysUser.java,v 0.1 2012-5-17 上午11:06:17 Administrator Exp $
 */
public class BaseSysUser extends BaseObject implements Comparable, Serializable {

    private static Logger logger = Logger.getLogger(BaseSysUser.class);
    private static final long serialVersionUID = -3509896046362878678L;

    private Integer userId;                        //用户ID
    private Integer deptId;                        //部门ID
    private String account;                       //帐号
    private String password;                      //密码
    private Integer isPasswordReset;               //密码是否重置
    private Date lastChangePassword;            //最近一次修改密码时间
    private Date lastLoginDate;                 //最近一次登陆时间
    private String phone;                         //固话
    private String phoneExt;                      //固话分机
    private String userName;                      //用户姓名
    private String sex;                           //性别
    private String userCode;                      //人员编号
    private String mobilePhone;                   //手机号码
    private String email;                         //电子邮件
    private Integer passwordStr;                   //密码强弱
    private Integer isActived;                     //是否启用(1:启用 0:不启用)
    private Integer isBinding;                     //是否绑定(1:绑定 0:不绑定)
    private Integer isDel;                         //是否删除(1:删除 0:未删除)
    private String remark;                        //备注
    private Date createDate;                         //创建时间
    private Date updateDate;                         //更新时间
    private Integer createUser;                    //创建用户
    private Integer updateUser;                    //更新用户
    private String operateCode;                     //信贷操作编号
    private String jgm;                                 //信贷系统机构码
   
    private Integer leaveTag;  
    public Integer getLeaveTag() {
		return leaveTag;
	}

	public void setLeaveTag(Integer leaveTag) {
		this.leaveTag = leaveTag;
	}

	private BigDecimal approvalValue;  
    public void setJgm(String jgm) {
        this.jgm = jgm;
    }

    public String getJgm() {
        return jgm;
    }
   

    public Integer getPasswordStr() {
        return passwordStr;
    }

    public void setPasswordStr(Integer passwordStr) {
        this.passwordStr = passwordStr;
    }

    public Date getLastChangePassword() {
        return lastChangePassword;
    }

    public void setLastChangePassword(Date lastChangePassword) {
        this.lastChangePassword = lastChangePassword;
    }

    public Date getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhoneExt() {
        return phoneExt;
    }

    public void setPhoneExt(String phoneExt) {
        this.phoneExt = phoneExt;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getIsPasswordReset() {
        return isPasswordReset;
    }

    public void setIsPasswordReset(Integer isPasswordReset) {
        this.isPasswordReset = isPasswordReset;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getIsActived() {
        return isActived;
    }

    public void setIsActived(Integer isActived) {
        this.isActived = isActived;
    }

    public Integer getIsBinding() {
        return isBinding;
    }

    public void setIsBinding(Integer isBinding) {
        this.isBinding = isBinding;
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

    public String getOperateCode() {
        return operateCode;
    }

    public void setOperateCode(String operateCode) {
        this.operateCode = operateCode;
    }





	public BigDecimal getApprovalValue() {
		return approvalValue;
	}

	public void setApprovalValue(BigDecimal approvalValue) {
		this.approvalValue = approvalValue;
	}

	/**
     * @see java.lang.Comparable#compareTo(Object)
     */
    public int compareTo(Object object) {
        BaseSysUser myClass = (BaseSysUser) object;
        return new CompareToBuilder().append(this.phone, myClass.phone)
                .append(this.sex, myClass.sex).append(this.lastLoginDate, myClass.lastLoginDate)
                .append(this.remark, myClass.remark).append(this.userCode, myClass.userCode)
                .append(this.password, myClass.password).append(this.updateDate, myClass.updateDate)
                .append(this.isBinding, myClass.isBinding).append(this.createUser, myClass.createUser)
                .append(this.isDel, myClass.isDel).append(this.phoneExt, myClass.phoneExt)
                .append(this.mobilePhone, myClass.mobilePhone).append(this.email, myClass.email)
                .append(this.userId, myClass.userId).append(this.account, myClass.account)
                .append(this.lastChangePassword, myClass.lastChangePassword)
                .append(this.userName, myClass.userName).append(this.isActived, myClass.isActived)
                .append(this.createDate, myClass.createDate).append(this.deptId, myClass.deptId)
                .append(this.updateUser, myClass.updateUser)
                .append(this.isPasswordReset, myClass.isPasswordReset)
                .append(this.operateCode, myClass.operateCode).toComparison();
    }

    /**
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object) {
        if (!(object instanceof BaseSysUser)) {
            return false;
        }
        BaseSysUser rhs = (BaseSysUser) object;
        return new EqualsBuilder().appendSuper(super.equals(object)).append(this.phone, rhs.phone)
                .append(this.sex, rhs.sex).append(this.lastLoginDate, rhs.lastLoginDate)
                .append(this.remark, rhs.remark).append(this.userCode, rhs.userCode)
                .append(this.password, rhs.password).append(this.updateDate, rhs.updateDate)
                .append(this.isBinding, rhs.isBinding).append(this.createUser, rhs.createUser)
                .append(this.isDel, rhs.isDel).append(this.phoneExt, rhs.phoneExt)
                .append(this.mobilePhone, rhs.mobilePhone).append(this.email, rhs.email)
                .append(this.userId, rhs.userId).append(this.account, rhs.account)
                .append(this.lastChangePassword, rhs.lastChangePassword)
                .append(this.userName, rhs.userName).append(this.isActived, rhs.isActived)
                .append(this.createDate, rhs.createDate).append(this.deptId, rhs.deptId)
                .append(this.updateUser, rhs.updateUser)
                .append(this.isPasswordReset, rhs.isPasswordReset)
                .append(this.operateCode, rhs.operateCode).isEquals();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(1126997863, -544416517).appendSuper(super.hashCode())
                .append(this.phone).append(this.sex).append(this.lastLoginDate).append(this.remark)
                .append(this.userCode).append(this.password).append(this.updateDate)
                .append(this.isBinding).append(this.createUser).append(this.isDel)
                .append(this.phoneExt).append(this.mobilePhone).append(this.email).append(this.userId)
                .append(this.account).append(this.lastChangePassword).append(this.userName)
                .append(this.isActived).append(this.createDate).append(this.deptId)
                .append(this.updateUser).append(this.isPasswordReset).append(this.operateCode).toHashCode();
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.DEFAULT_STYLE, new StringBuffer()).append("userId", this.userId)
                .append("isBinding", this.isBinding).append("updateDate", this.updateDate)
                .append("email", this.email).append("deptId", this.deptId).append("phone", this.phone)
                .append("endRow", this.getEndRow()).append("userCode", this.userCode)
                .append("isPasswordReset", this.isPasswordReset)
                .append("lastChangePassword", this.lastChangePassword)
                .append("createDate", this.createDate).append("isActived", this.isActived)
                .append("account", this.account).append("password", this.password)
                .append("isDel", this.isDel).append("remark", this.remark)
                .append("lastLoginDate", this.lastLoginDate).append("mobilePhone", this.mobilePhone)
                .append("phoneExt", this.phoneExt).append("createUser", this.createUser)
                .append("updateUser", this.updateUser).append("sex", this.sex)
                .append("userName", this.userName).append("startRow", this.getStartRow())
                .append("operateCode", this.operateCode).toString();
    }


}
