package com.banger.mobile.webservice.domain.loan;
import com.banger.mobile.domain.model.pad.PadLoan;
import com.banger.mobile.util.DateUtil;

/**
 * 贷款放款列表
 * @author summerxll
 *
 */
public class NeedLendingLoanListInfo extends BaseLoanListInfo {

	private String approvalDate;
	
	public String getApprovalDate() {
		return approvalDate;
	}
	public void setApprovalDate(String approvalDate) {
		this.approvalDate = approvalDate;
	}

	public NeedLendingLoanListInfo(PadLoan info){
		super(info);
		if(info.getApproveProcessId()==2){
			//待审批会
			this.setApprovalDate(DateUtil.getDateToString(info.getApproveBackerPassDate()));
		}else{
			//审批中心
			this.setApprovalDate(DateUtil.getDateToString(info.getApproveCenterPassDate()));
		}
			
	}
}
