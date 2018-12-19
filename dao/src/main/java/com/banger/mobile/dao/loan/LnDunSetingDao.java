package com.banger.mobile.dao.loan;

import com.banger.mobile.domain.model.loan.LnDunSeting;

public interface LnDunSetingDao {
	/**
	 * 修改催收设置
	 * @param lnDunSeting
	 */
	public void updateLnDunSeting(LnDunSeting lnDunSeting);
	/**
	 * 查询催收设置
	 * @return
	 */
	public LnDunSeting getLnDunSeting(Integer flag);
}
