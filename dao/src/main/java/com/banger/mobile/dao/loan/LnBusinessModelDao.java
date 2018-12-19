package com.banger.mobile.dao.loan;

import com.banger.mobile.domain.model.loan.LnBusinessModel;

public interface LnBusinessModelDao {

	/**
	 * 保存经营贷红营模式
	 * @param lnBusinessModel
	 * @return 
	 */
	LnBusinessModel insertLnBusinessModel(LnBusinessModel lnBusinessModel);
    
	/**
	 * 主键查询或贷款主表外键查询
	 * @param lnBusinessModel
	 * @return
	 */
	LnBusinessModel selectByPrimary(LnBusinessModel lnBusinessModel);
    
	/**
	 * 修改经营贷红营模式
	 * @param ln
	 * @return
	 */
	int updateLnBusinessModel(LnBusinessModel ln);

}
