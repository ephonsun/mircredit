package com.banger.mobile.facade.impl;

import com.banger.mobile.dao.loan.LnCancelReasonDao;
import com.banger.mobile.domain.model.loan.LnCancelReason;
import com.banger.mobile.facade.loan.LnCancelReasonService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA. User: Zhangfp Date: 13-2-6 Time: 上午10:18 To
 * change this template use File | Settings | File Templates.
 * 
 * 贷款撤销原因
 */
public class LnCancelReasonServiceImpl implements LnCancelReasonService {

	private LnCancelReasonDao lnCancelReasonDao;

	public List<LnCancelReason> getCancelReasonList() {
		return lnCancelReasonDao.getCancelReasonList();
	}

	/**
	 * 通过id删除撤销原因（伪删除）
	 */
	public void deleteCancelReason(Integer lnCanReaId) {
		// TODO Auto-generated method stub
		lnCancelReasonDao.deleteById(lnCanReaId);
	}

	/**
	 * 插入撤销原因
	 */
	public void insertCancelReason(LnCancelReason lnCancelReason) {
		// TODO Auto-generated method stub
		lnCancelReasonDao.insertCancelReason(lnCancelReason);
	}

	/**
	 * 更新撤销原因
	 */
	public void updateCancelReason(LnCancelReason lnCancelReason) {
		// TODO Auto-generated method stub
		lnCancelReasonDao.updateCancelReason(lnCancelReason);
	}

	/**
	 * 通过id获取CancelReason
	 */
	public LnCancelReason getCancelReasonById(Integer lnCanReaId) {
		// TODO Auto-generated method stub
		return lnCancelReasonDao.getCancelReasonById(lnCanReaId);
	}

	/**
	 * 通过name获取CancelRea
	 */
	public LnCancelReason getCancelReasonByName(String lnCanReaName) {
		return this.getCancelReasonByName(lnCanReaName , null);
	}
	
	/**
	 * 通过name获取CancelRea
	 */
	public LnCancelReason getCancelReasonByName(String lnCanReaName , Integer cancelReasonId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("lnCanReaName", lnCanReaName);
		if(cancelReasonId != null){
			map.put("cancelReasonId", cancelReasonId);
		}
		return lnCancelReasonDao.getCancelReasonByName(map);
	}

	/**
	 * 通过sortNo获取CancelReason
	 */
	public LnCancelReason getCancelReasonBySortNo(Integer sortNo) {
		// TODO Auto-generated method stub
		return lnCancelReasonDao.getCancelReasonBySortNo(sortNo);
	}
	// dao的依赖注入
	public LnCancelReasonDao getLnCancelReasonDao() {
		return lnCancelReasonDao;
	}

	public void setLnCancelReasonDao(LnCancelReasonDao lnCancelReasonDao) {
		this.lnCancelReasonDao = lnCancelReasonDao;
	}

    /**
     * 根据ID查找拒绝原因信息
     *
     * @param cancelReasonId
     * @return
     */
    public LnCancelReason selectCancelReasonById(Integer cancelReasonId){
        return lnCancelReasonDao.selectCancelReasonById(cancelReasonId);
    }

}
