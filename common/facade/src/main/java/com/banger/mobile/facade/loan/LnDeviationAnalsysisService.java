package com.banger.mobile.facade.loan;

import com.banger.mobile.domain.model.loan.LnDeviationAnalsysis;

import java.util.List;
import java.util.Map;

public interface LnDeviationAnalsysisService {

	/**
	 * 保存经营贷经营模式
	 * @param InBusinessReaches
	 * @return 
	 */
	void  creatrLnDeviationAnalsysis(Map<String,Object> paramMap);


	/**
	 * 修改
	 * @param InBusinessReaches
	 * @return
	 */
	void  alterLnDeviationAnalsysis(Map<String,Object> paramMap);



	/**
	 *
	 * @param InBusinessReaches
	 * @return
	 */

	LnDeviationAnalsysis  selectById(Integer Id);
/**
	 * 查看类型
	 * @param InBusinessReaches
	 * @return
	 */




}
