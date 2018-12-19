package com.banger.mobile.domain.model.loan;

import java.math.BigDecimal;
import java.util.Date;

import com.banger.mobile.domain.model.base.loan.BaseLnDunLog;

/**
 * Created with IntelliJ IDEA. User: Zhangfp Date: 13-2-5 Time: 下午3:42 To change
 * this template use File | Settings | File Templates.
 * 
 * 贷款催收记录表
 */
public class LnDunLog extends BaseLnDunLog {
	private BigDecimal loanRemaining; // 本期贷款余额
	private BigDecimal accountRemaining; // 账户余额
	private Date repaymentDate; // 本期还款时间
	private Integer status; // 还款状态
	private BigDecimal needRepay; // 本期合计
	private Integer isFinish; // 是否已催收
	private Integer daCount;
	private Integer dvCount;
	private Integer dpCount;

	public BigDecimal getLoanRemaining() {
		return loanRemaining;
	}

	public void setLoanRemaining(BigDecimal loanRemaining) {
		this.loanRemaining = loanRemaining;
	}

	public BigDecimal getAccountRemaining() {
		return accountRemaining;
	}

	public void setAccountRemaining(BigDecimal accountRemaining) {
		this.accountRemaining = accountRemaining;
	}

	public Date getRepaymentDate() {
		return repaymentDate;
	}

	public void setRepaymentDate(Date repaymentDate) {
		this.repaymentDate = repaymentDate;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public BigDecimal getNeedRepay() {
		return needRepay;
	}

	public void setNeedRepay(BigDecimal needRepay) {
		this.needRepay = needRepay;
	}

	public Integer getIsFinish() {
		return isFinish;
	}

	public void setIsFinish(Integer isFinish) {
		this.isFinish = isFinish;
	}

	public Integer getDaCount() {
		return daCount;
	}

	public void setDaCount(Integer daCount) {
		this.daCount = daCount;
	}

	public Integer getDvCount() {
		return dvCount;
	}

	public void setDvCount(Integer dvCount) {
		this.dvCount = dvCount;
	}

	public Integer getDpCount() {
		return dpCount;
	}

	public void setDpCount(Integer dpCount) {
		this.dpCount = dpCount;
	}

}
