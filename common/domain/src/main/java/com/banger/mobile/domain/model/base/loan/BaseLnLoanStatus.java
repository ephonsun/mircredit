package com.banger.mobile.domain.model.base.loan;

import com.banger.mobile.domain.model.base.BaseObject;

import java.io.Serializable;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Zhangfp
 * Date: 13-2-6
 * Time: 下午3:25
 * To change this template use File | Settings | File Templates.
 *
 * 贷款状态
 */
public class BaseLnLoanStatus extends BaseObject implements Serializable {
    private Integer loanStatusId;   //主键ID
    private String loanStatusName;  //贷款状态名称
    private Date createDate;        //创建时间
    private Date updateDate;        //更新时间
    private Integer createUser;     //创建用户
    private Integer updateUser;     //更新用户

    public Integer getLoanStatusId() {
        return loanStatusId;
    }

    public void setLoanStatusId(Integer loanStatusId) {
        this.loanStatusId = loanStatusId;
    }

    public String getLoanStatusName() {
        return loanStatusName;
    }

    public void setLoanStatusName(String loanStatusName) {
        this.loanStatusName = loanStatusName;
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
