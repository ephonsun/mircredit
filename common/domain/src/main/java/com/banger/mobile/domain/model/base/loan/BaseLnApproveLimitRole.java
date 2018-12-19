package com.banger.mobile.domain.model.base.loan;

import com.banger.mobile.domain.model.base.BaseObject;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Zhangfp
 * Date: 13-2-27
 * Time: 下午3:15
 * To change this template use File | Settings | File Templates.
 *
 * 角色审批额度设置表
 */
public class BaseLnApproveLimitRole extends BaseObject implements Serializable {
    private Integer limitRoleId;   //主键id
    private Integer roleId;  //角色ID;
    private BigDecimal limitMoney; //额度(万元)
    private Date createDate;   //创建时间
    private Date updateDate;   //更新时间
    private Integer createUser; //创建用户
    private Integer updateUser; //更新用户

    public Integer getLimitRoleId() {
        return limitRoleId;
    }

    public void setLimitRoleId(Integer limitRoleId) {
        this.limitRoleId = limitRoleId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public BigDecimal getLimitMoney() {
        return limitMoney;
    }

    public void setLimitMoney(BigDecimal limitMoney) {
        this.limitMoney = limitMoney;
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
}
