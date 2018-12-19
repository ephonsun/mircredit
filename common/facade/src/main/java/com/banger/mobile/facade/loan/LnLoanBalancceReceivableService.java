package com.banger.mobile.facade.loan;

import com.banger.mobile.domain.model.loan.LnBusinessModel;

public interface LnLoanBalancceReceivableService {

	/**
	 * 保存经营贷经营模式
	 * @param lnBusinessModel
	 * @return 
	 */
	LnBusinessModel insertLnBusinessModel(LnBusinessModel lnBusinessModel);

	/**
	 * 修改经营贷经营模式
	 * @param lnBusinessModel
	 * @return 
	 */
	LnBusinessModel updateLnBusinessModel(LnBusinessModel lnBusinessModel);

	/**
	 * 主键或贷款主表外键查询
	 * @param lnBusinessModel
	 * @return 
	 */
	LnBusinessModel selectByPrimary(LnBusinessModel lnBusinessModel);
    }
