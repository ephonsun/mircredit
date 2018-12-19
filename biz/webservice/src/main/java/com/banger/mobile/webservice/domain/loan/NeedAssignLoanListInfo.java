package com.banger.mobile.webservice.domain.loan;
import com.banger.mobile.domain.model.pad.PadLoan;
import com.banger.mobile.util.DateUtil;

/**
 * 贷款分配列表
 * @author summerxll
 *
 */
public class NeedAssignLoanListInfo extends BaseLoanListInfo {
	private Integer applyUserId; 				// 申请用户ID  主表
	private String applyUserName; 				// 申请提交人员    人员表
	private String applyDate; 					// 申请提交时间     主表
	
	public Integer getApplyUserId() {
		return applyUserId==null ?-1:applyUserId;
	}
	public void setApplyUserId(Integer applyUserId) {
		this.applyUserId = applyUserId==null ?-1:applyUserId;
	}
	public String getApplyUserName() {
		return applyUserName==null ?"":applyUserName;
	}
	public void setApplyUserName(String applyUserName) {
		this.applyUserName = applyUserName==null ?"":applyUserName;
	}
	public String getApplyDate() {
		return applyDate==null ?"":applyDate;
	}
	public void setApplyDate(String applyDate) {
		this.applyDate = applyDate==null ?"":applyDate;
	}
	
	public NeedAssignLoanListInfo(PadLoan info){
		super(info);
		this.setApplyUserId(info.getApplyUserId());
		this.setApplyUserName(info.getApplyUserName());
		this.setApplyDate(DateUtil.getDateToString(info.getApplySubmitDate()));
	}
}
