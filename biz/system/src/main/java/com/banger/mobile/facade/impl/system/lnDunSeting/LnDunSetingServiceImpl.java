package com.banger.mobile.facade.impl.system.lnDunSeting;

import com.banger.mobile.dao.loan.LnDunSetingDao;
import com.banger.mobile.domain.model.loan.LnDunSeting;
import com.banger.mobile.facade.loan.LnDunSetingService;

public class LnDunSetingServiceImpl implements LnDunSetingService{
	
	private LnDunSetingDao lnDunSetingDao;
	/**
	 * 保存催收配置
	 */
	public void save(LnDunSeting lnDunSeting){
		if(lnDunSeting!=null&&lnDunSeting.getIsActived()==null){
			lnDunSeting.setIsActived(0);
		}
		
		updateLnDunSeting(lnDunSeting);
	}
	
	private void updateLnDunSeting(LnDunSeting lnDunSeting){
		lnDunSetingDao.updateLnDunSeting(lnDunSeting);
	}
	
	/**
	 * 获取催收配置
	 * @return
	 */
	public LnDunSeting getLnDunSeting(Integer flag){
		return lnDunSetingDao.getLnDunSeting(flag);
	}

	public void setLnDunSetingDao(LnDunSetingDao lnDunSetingDao) {
		this.lnDunSetingDao = lnDunSetingDao;
	}
	
}
