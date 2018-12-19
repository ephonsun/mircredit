package com.banger.mobile.domain.model.base.loan;

import java.io.Serializable;
import java.util.Date;

import com.banger.mobile.domain.model.base.BaseObject;

/**
 * 信贷历史
 * 
 * @author White Wolf
 * @version $Id: BaseLnCreditHistory.java, v 0.1 2016-1-7 下午2:40:56 White Wolf Exp $
 */
public class BaseLnCreditHistory extends BaseObject implements Serializable {
    private static final long serialVersionUID = -3368547622685409941L;
    
    private Integer creditHistoryId; //主键ID
    private Integer customerId;//用户ID
    private Integer loanId;//贷款ID
    private String finaceSource;//融资来源
    private String loanPurpose;//用途
    private String loanMoney;//贷款金额
    private String loanDate; //放款日期
    private String loanLimitYear;//期限
    private String repayMonth;//月还款额
    private String overdueInformation;//逾期信息
    private String balanceMoney;//余额
    private Date createDate;//创建时间
    private Date updateDate;//修改时间
	public Integer getCreditHistoryId() {
		return creditHistoryId;
	}
	public void setCreditHistoryId(Integer creditHistoryId) {
		this.creditHistoryId = creditHistoryId;
	}
	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	public Integer getLoanId() {
		return loanId;
	}
	public void setLoanId(Integer loanId) {
		this.loanId = loanId;
	}
	public String getFinaceSource() {
		return finaceSource;
	}
	public void setFinaceSource(String finaceSource) {
		this.finaceSource = finaceSource;
	}
	public String getLoanPurpose() {
		return loanPurpose;
	}
	public void setLoanPurpose(String loanPurpose) {
		this.loanPurpose = loanPurpose;
	}
	public String getLoanMoney() {
		return loanMoney;
	}
	public void setLoanMoney(String loanMoney) {
		this.loanMoney = loanMoney;
	}
	public String getLoanDate() {
		return loanDate;
	}
	public void setLoanDate(String loanDate) {
		this.loanDate = loanDate;
	}
	
	public String getLoanLimitYear() {
		return loanLimitYear;
	}
	public void setLoanLimitYear(String loanLimitYear) {
		this.loanLimitYear = loanLimitYear;
	}
	public String getRepayMonth() {
		return repayMonth;
	}
	public void setRepayMonth(String repayMonth) {
		this.repayMonth = repayMonth;
	}
	public String getOverdueInformation() {
		return overdueInformation;
	}
	public void setOverdueInformation(String overdueInformation) {
		this.overdueInformation = overdueInformation;
	}
	public String getBalanceMoney() {
		return balanceMoney;
	}
	public void setBalanceMoney(String balanceMoney) {
		this.balanceMoney = balanceMoney;
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
}
