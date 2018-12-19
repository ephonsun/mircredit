package com.banger.mobile.domain.model.base.loan;

import com.banger.mobile.domain.model.base.BaseObject;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Zhangfp
 * Date: 13-2-5
 * Time: 下午2:11
 * To change this template use File | Settings | File Templates.
 *
 * 异常贷款催收记录
 */
public class BaseLnExceptionDunLog extends BaseObject implements Serializable {
    private Integer exceptionDunLogId;  //主键id
    private Integer loanId;               //贷款id
    private Integer dunUserId;            //催收用户id
    private Date dunDate;                 //催收时间
    private Integer dunType;              //催收类型
    private Integer customerDataId;       //客户资料ID
    private Date createDate;              //创建时间
    private Date updateDate;              //更新时间
    private Integer createUser;           //创建用户
    private Integer updateUser;           //更新用户
    private Integer sortno;               //分期期号
    private String remark;

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public BaseLnExceptionDunLog(){
    }

    public BaseLnExceptionDunLog(Integer exceptionDunLogId){
        this.exceptionDunLogId = exceptionDunLogId;
    }

    public Integer getExceptionDunLogId() {
        return exceptionDunLogId;
    }

    public void setExceptionDunLogId(Integer exceptionDunLogId) {
        this.exceptionDunLogId = exceptionDunLogId;
    }

    public Integer getLoanId() {
        return loanId;
    }

    public void setLoanId(Integer loanId) {
        this.loanId = loanId;
    }

    public Integer getDunUserId() {
        return dunUserId;
    }

    public void setDunUserId(Integer dunUserId) {
        this.dunUserId = dunUserId;
    }

    public Date getDunDate() {
        return dunDate;
    }

    public void setDunDate(Date dunDate) {
        this.dunDate = dunDate;
    }

    public Integer getDunType() {
        return dunType;
    }

    public void setDunType(Integer dunType) {
        this.dunType = dunType;
    }

    public Integer getCustomerDataId() {
        return customerDataId;
    }

    public void setCustomerDataId(Integer customerDataId) {
        this.customerDataId = customerDataId;
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

    public Integer getSortno() {
        return sortno;
    }

    public void setSortno(Integer sortno) {
        this.sortno = sortno;
    }
}
