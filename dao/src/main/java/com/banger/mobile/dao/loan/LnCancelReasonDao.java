package com.banger.mobile.dao.loan;

import com.banger.mobile.domain.model.loan.LnCancelReason;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA. User: yanqf Date: 13-2-6 Time: 上午9:19 To
 * change this template use File | Settings | File Templates.
 */
public interface LnCancelReasonDao {
	/**
	 * 返回所有的撤销原因
	 * 
	 * @return
	 */
	List<LnCancelReason> getCancelReasonList();


	/**
	 * 根据业务id删除单个数据
	 * 
	 * @param bizId
	 */
	public void deleteById(Integer lnCanReaId);

	/**
	 * 增加撤销原因
	 * 
	 * @param lnCancelReason
	 */
	public void insertCancelReason(LnCancelReason lnCancelReason);

	/**
	 * 判断是否存在
	 * 
	 * @param lnCanReaId
	 * @param lnCancelReason
	 * @return
	 */
	public Integer exsitsCancelReason(Integer lnCanReaId,
			LnCancelReason lnCancelReason);

	/**
	 * 返回撤销原因信息
	 * 
	 * @param lnCancelReason
	 * @return
	 */
	public LnCancelReason getCancelReasonById(Integer lnCanReaId);

	/**
	 * 编辑撤销原因
	 * 
	 * @param lnCancelReason
	 */
	public void updateCancelReason(LnCancelReason lnCancelReason);

	/**
	 * 通过name获取撤销类型
	 * 
	 * @param lnCanReaId
	 * @return
	 */
	public LnCancelReason getCancelReasonByName(Map<String, Object> map);
	
	/**
	 * 通过sortNo获取CancelReason
	 * @param sortNo
	 * @return
	 */
	public LnCancelReason getCancelReasonBySortNo(Integer sortNo);

    LnCancelReason selectCancelReasonById(Integer cancelReasonId);
}
