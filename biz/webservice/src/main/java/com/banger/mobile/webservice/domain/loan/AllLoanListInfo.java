package com.banger.mobile.webservice.domain.loan;

import com.banger.mobile.domain.model.pad.PadLoan;
import com.banger.mobile.util.DateUtil;

/**
 * 所有贷款列表
 * @author summerxll
 *
 */
public class AllLoanListInfo extends BaseLoanListInfo {
	private Integer loanStatusId; 				// 贷款状态ID 	主表
	private String loanStatusName; 				// 贷款状态名称 	主表
	private String createDate;					// 贷款创建时间
	
	public Integer getLoanStatusId() {
		return loanStatusId==null ?-1:loanStatusId;
	}
	public void setLoanStatusId(Integer loanStatusId) {
		this.loanStatusId = loanStatusId==null ?-1:loanStatusId;
	}
	public String getLoanStatusName() {
		return loanStatusName==null ?"":loanStatusName;
	}
	public void setLoanStatusName(String loanStatusName) {
		this.loanStatusName = loanStatusName==null ?"":loanStatusName;
	}
	public String getCreateDate() {
		return createDate==null ?"":createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate==null ?"":createDate;
	}

	public AllLoanListInfo(PadLoan info){
		super(info);
		this.setLoanStatusId(info.getLoanStatusId());
		this.setLoanStatusName(info.getLoanStatusName());
		this.setCreateDate(DateUtil.getDateToString(info.getCreateDate()));
	}
}
