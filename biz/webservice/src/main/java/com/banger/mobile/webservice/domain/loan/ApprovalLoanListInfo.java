package com.banger.mobile.webservice.domain.loan;


import com.banger.mobile.domain.model.pad.PadLoan;
import com.banger.mobile.util.DateUtil;
/**
 * 贷款审批列表
 * @author summerxll
 *
 */
public class ApprovalLoanListInfo extends BaseLoanListInfo {

	private String submitDate; 				// 调查提交时间	主表
	
	public String getSubmitDate() {
		return submitDate==null?"":submitDate;
	}
	public void setSubmitDate(String surveySubmitDate) {
		this.submitDate = submitDate==null?"":submitDate;
	}

	public ApprovalLoanListInfo(PadLoan info){
		super(info);
		this.setSubmitDate(DateUtil.getDateToString(info.getSurveySubmitDate()));
	}
}
