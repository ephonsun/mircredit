package com.banger.mobile.facade.loan;

import com.banger.mobile.domain.model.loan.LnDunSeting;

public interface LnDunSetingService {
	/**
	 * 保存催收配置
	 * @param lnDunSeting
	 */
	public void save(LnDunSeting lnDunSeting);
	
	/**
	 * 获取催收配置
	 * @return
	 */
	public LnDunSeting getLnDunSeting(Integer flag);
}
