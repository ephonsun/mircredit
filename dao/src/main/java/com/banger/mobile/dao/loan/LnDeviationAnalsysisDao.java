package com.banger.mobile.dao.loan;

import com.banger.mobile.domain.model.loan.InBusinessReaches;
import com.banger.mobile.domain.model.loan.LnDeviationAnalsysis;

import java.util.List;
import java.util.Map;

public interface LnDeviationAnalsysisDao {

	/**
	 * 保存
	 * @param lnBusinessModel
	 * @return 
	 */
	void  insertLnDeviationAnalsysis(Map<String,Object> paramMap);
    
	/**
	 * 按照类型
	 * @param lnBusinessModel
	 * @param sbt
	 * @return
	 */
	LnDeviationAnalsysis queryOneLnDeviationAnalsysis( Integer loanId);

	/**
	 * 按照类型
	 * @param lnBusinessModel
	 * @param sbt
	 * @return
	 */
	void  updateLnDeviationAnalsysis(Map<String,Object> paramMap);



}
