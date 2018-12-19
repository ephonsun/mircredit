package com.banger.mobile.domain.model.base.loan;

import com.banger.mobile.domain.model.base.BaseObject;

import java.io.Serializable;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-3-14
 * Time: 上午10:53
 * To change this template use File | Settings | File Templates.
 * 审计历史操作
 */
public class BaseLnVerifyHistory extends BaseObject implements Serializable {
    private Integer verifyHistoryId;            //主键ID
    private Integer loanId;                     //贷款ID
    private Date verifyHistoryIdDate;           //操作时间
    private Integer userId;                     //操作用户
    private String content;                     //操作内容
    private String remark;                      //备注

    public Integer getVerifyHistoryId() {
        return verifyHistoryId;
    }

    public void setVerifyHistoryId(Integer verifyHistoryId) {
        this.verifyHistoryId = verifyHistoryId;
    }

    public Integer getLoanId() {
        return loanId;
    }

    public void setLoanId(Integer loanId) {
        this.loanId = loanId;
    }

    public Date getVerifyHistoryIdDate() {
        return verifyHistoryIdDate;
    }

    public void setVerifyHistoryIdDate(Date verifyHistoryIdDate) {
        this.verifyHistoryIdDate = verifyHistoryIdDate;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
