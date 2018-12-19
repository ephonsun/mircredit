/**
 * 
 * Copyright (c) 2015-2025 All Rights Reserved.
 */
package com.banger.mobile.domain.model.loan;

import java.util.List;

import com.banger.mobile.domain.model.base.loan.BaseLnLoanInfo;

/**
 * 
 * @author White Wolf
 * @version $Id: LnLoanAppFormBak.java, v 0.1 2016-2-18 上午10:35:54 White Wolf Exp $
 */
public class LnLoanAppFormBak{
	
	private LnLoanInfo lnLoanInfo;
	
	private List<LnLoanCoBorrowerBean> lnLoanCoBorrowerList;
	
	private List<LnLoanGuarantorBean> lnLoanGuarantorList;
	
	private List<LnCreditHistory> creditHistoryList;
	
	public LnLoanInfo getLnLoanInfo() {
		return lnLoanInfo;
	}

	public void setLnLoanInfo(LnLoanInfo lnLoanInfo) {
		this.lnLoanInfo = lnLoanInfo;
	}

	public List<LnLoanCoBorrowerBean> getLnLoanCoBorrowerList() {
		return lnLoanCoBorrowerList;
	}

	public void setLnLoanCoBorrowerList(
			List<LnLoanCoBorrowerBean> lnLoanCoBorrowerList) {
		this.lnLoanCoBorrowerList = lnLoanCoBorrowerList;
	}

	public List<LnLoanGuarantorBean> getLnLoanGuarantorList() {
		return lnLoanGuarantorList;
	}

	public void setLnLoanGuarantorList(List<LnLoanGuarantorBean> lnLoanGuarantorList) {
		this.lnLoanGuarantorList = lnLoanGuarantorList;
	}

	public List<LnCreditHistory> getCreditHistoryList() {
		return creditHistoryList;
	}

	public void setCreditHistoryList(List<LnCreditHistory> creditHistoryList) {
		this.creditHistoryList = creditHistoryList;
	}
	
}
