package com.banger.mobile.domain.model.loan;

import com.banger.mobile.domain.model.base.loan.BaseLnExceptionDunLog;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Zhangfp
 * Date: 13-2-5
 * Time: 下午3:43
 * To change this template use File | Settings | File Templates.
 *
 * 异常贷款催收记录
 */
public class LnExceptionDunLog extends BaseLnExceptionDunLog {

    private BigDecimal eLoanRemaining;
    private BigDecimal eAccountRemaining;
    private Date eRepaymentDate;
    private Integer eStatus;
    private Integer daCount;
    private Integer dvCount;
    private Integer dpCount;
    public BigDecimal geteLoanRemaining() {
        return eLoanRemaining;
    }

    public void seteLoanRemaining(BigDecimal eLoanRemaining) {
        this.eLoanRemaining = eLoanRemaining;
    }

    public BigDecimal geteAccountRemaining() {
        return eAccountRemaining;
    }

    public void seteAccountRemaining(BigDecimal eAccountRemaining) {
        this.eAccountRemaining = eAccountRemaining;
    }

    public Date geteRepaymentDate() {
        return eRepaymentDate;
    }

    public void seteRepaymentDate(Date eRepaymentDate) {
        this.eRepaymentDate = eRepaymentDate;
    }

    public Integer geteStatus() {
        return eStatus;
    }

    public void seteStatus(Integer eStatus) {
        this.eStatus = eStatus;
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
