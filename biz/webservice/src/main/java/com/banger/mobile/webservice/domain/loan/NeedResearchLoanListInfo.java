package com.banger.mobile.webservice.domain.loan;

import com.banger.mobile.domain.model.pad.PadLoan;
import com.banger.mobile.util.DateUtil;

/**
 * 贷款调查列表
 * @author summerxll
 *
 */
public class NeedResearchLoanListInfo extends BaseLoanListInfo{

	private String assignDate; 				// 分配提交时间	主表
	public String getAssignDate() {
		return assignDate==null?"":assignDate;
	}
	public void setAssignDate(String assignDate) {
		this.assignDate = assignDate==null?"":assignDate;
	}
	
	public NeedResearchLoanListInfo(PadLoan info){
		super(info);
		this.setAssignDate(DateUtil.getDateToString(info.getAssignSubmitDate()));

	}
}
