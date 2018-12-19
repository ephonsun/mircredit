package com.banger.mobile.facade.loan;

import com.banger.mobile.domain.model.loan.LnCancelReason;

import java.util.List;

/**
 * Created with IntelliJ IDEA. User: Zhangfp Date: 13-2-6 Time: 上午9:34 To change
 * this template use File | Settings | File Templates.
 * 
 * 贷款撤销原因
 */
public interface LnCancelReasonService {
	List<LnCancelReason> getCancelReasonList();

	/**
	 * 根据id获取CancelReason
	 * 
	 * @param lnCanReaId
	 * @return
	 */
	public LnCancelReason getCancelReasonById(Integer lnCanReaId);

	public LnCancelReason getCancelReasonByName(String lnCanReaName);
	
	public LnCancelReason getCancelReasonByName(String lnCanReaName , Integer cancelReasonId);

	public LnCancelReason getCancelReasonBySortNo(Integer sortNo);
	
	public void deleteCancelReason(Integer inCanReaId);

	public void insertCancelReason(LnCancelReason lnCancelReason);

	public void updateCancelReason(LnCancelReason lnCancelReason);


    LnCancelReason selectCancelReasonById(Integer cancelReasonId);
}
