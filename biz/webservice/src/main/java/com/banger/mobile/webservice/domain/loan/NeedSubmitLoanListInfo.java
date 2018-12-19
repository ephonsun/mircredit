package com.banger.mobile.webservice.domain.loan;

import com.banger.mobile.domain.model.pad.PadLoan;
import com.banger.mobile.util.DateUtil;

/**
 * 贷款申请列表 
 * @author summerxll
 *
 */
public class NeedSubmitLoanListInfo extends BaseLoanListInfo {

	private String createDate;
	
	public String getCreateDate() {
		return createDate==null?"":createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate==null?"":createDate;
	}

	public NeedSubmitLoanListInfo(PadLoan info){
		super(info);
		this.setCreateDate(DateUtil.getDateToString(info.getCreateDate()));
	}
}
