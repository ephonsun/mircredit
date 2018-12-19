package com.banger.mobile.domain.model.base.loan;

import com.banger.mobile.domain.model.base.BaseObject;

import java.io.Serializable;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Zhangfp
 * Date: 13-2-5
 * Time: 下午1:57
 * To change this template use File | Settings | File Templates.
 *
 * 贷款操作历史记录
 */
public class BaseLnOpHistory extends BaseObject implements Serializable {
    private Integer opHistoryId;  //主键ID
    private Integer loanId;       //贷款ID
    private Integer requestId;    //征信ID
    private Date opHistoryDate;   //操作时间
    private Integer userId;       //操作用户ID
    private String content;       //操作内容
    private String remark;        //备注
    private Integer beforeStatusId;  //任务状态变更前
    private Integer afterStatusId;   //任务状态变更后

    public Integer getRequestId() {
		return requestId;
	}

	public void setRequestId(Integer requestId) {
		this.requestId = requestId;
	}

	public BaseLnOpHistory(){
    }

    public BaseLnOpHistory(Integer opHistoryId){
        this.opHistoryId = opHistoryId;
    }

    public Integer getOpHistoryId() {
        return opHistoryId;
    }

    public void setOpHistoryId(Integer opHistoryId) {
        this.opHistoryId = opHistoryId;
    }

    public Integer getLoanId() {
        return loanId;
    }

    public void setLoanId(Integer loanId) {
        this.loanId = loanId;
    }

    public Date getOpHistoryDate() {
        return opHistoryDate;
    }

    public void setOpHistoryDate(Date opHistoryDate) {
        this.opHistoryDate = opHistoryDate;
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

    public Integer getBeforeStatusId() {
        return beforeStatusId;
    }

    public void setBeforeStatusId(Integer beforeStatusId) {
        this.beforeStatusId = beforeStatusId;
    }

    public Integer getAfterStatusId() {
        return afterStatusId;
    }

    public void setAfterStatusId(Integer afterStatusId) {
        this.afterStatusId = afterStatusId;
    }
}
