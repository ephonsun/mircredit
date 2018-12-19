package com.banger.mobile.domain.model.base.loan;

import com.banger.mobile.domain.model.base.BaseObject;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Zhangfp
 * Date: 13-2-5
 * Time: 下午1:33
 * To change this template use File | Settings | File Templates.
 * <p/>
 *
 * 异常贷款催收分配表
 */
public class BaseLnExceptionDunAssign extends BaseObject implements Serializable {
    private Integer expDunAssignId; //主键ID
    private Integer loanId; //贷款ID
    private Integer dunUserId;  //催收用户ID
    private Integer assignUserId; //分配用户ID
    private Date assignDate; //分配时间

    public Integer getExpDunAssignId() {
        return expDunAssignId;
    }

    public void setExpDunAssignId(Integer expDunAssignId) {
        this.expDunAssignId = expDunAssignId;
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

    public Integer getAssignUserId() {
        return assignUserId;
    }

    public void setAssignUserId(Integer assignUserId) {
        this.assignUserId = assignUserId;
    }

    public Date getAssignDate() {
        return assignDate;
    }

    public void setAssignDate(Date assignDate) {
        this.assignDate = assignDate;
    }
}
