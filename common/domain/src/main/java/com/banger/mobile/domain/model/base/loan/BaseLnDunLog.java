package com.banger.mobile.domain.model.base.loan;

import com.banger.mobile.domain.model.base.BaseObject;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Zhangfp
 * Date: 13-2-5
 * Time: 下午2:03
 * To change this template use File | Settings | File Templates.
 *
 * 贷款催收记录表
 */
public class BaseLnDunLog extends BaseObject implements Serializable {
    private Integer dunLogId;  //主键id
    private Integer loanId;           //贷款id
    private Date dunDate;              //催收时间
    private Date createDate;           //创建时间
    private Date updateDate;           //更新时间
    private Integer createUser;        //创建用户
    private Integer updateUser;        //更新用户
    private Integer dunType;
    private Integer customerDataId;
    private Integer sortno;           //期号
    private String remark;            //催收备注
    private Integer dunUserId;         //催收用户id
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public BaseLnDunLog(){
    }

    public BaseLnDunLog(Integer dunLogId){
        this.dunLogId = dunLogId;
    }

    public Integer getDunLogId() {
        return dunLogId;
    }

    public void setDunLogId(Integer dunLogId) {
        this.dunLogId = dunLogId;
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
}
