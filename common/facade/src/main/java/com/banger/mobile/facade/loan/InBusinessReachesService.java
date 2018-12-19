package com.banger.mobile.facade.loan;

import com.banger.mobile.domain.model.loan.InBusinessReaches;

import java.util.List;
import java.util.Map;

public interface InBusinessReachesService {

	/**
	 * 保存经营贷经营模式
	 * @param InBusinessReaches
	 * @return 
	 */
	void  creatrInBusinessReaches(InBusinessReaches inBusinessReaches);



	/**
	 * 查看类型
	 * @param InBusinessReaches
	 * @return
	 */

	List<InBusinessReaches> selectReachesType(Integer reachesType ,Integer loanId);
/**
	 * 查看类型
	 * @param InBusinessReaches
	 * @return
	 */

	List<InBusinessReaches> selectbbReachesType(Integer loanId);

	/**
	 * 删除
	 * @param InBusinessReaches
	 * @return
	 */
	void  deleteInBusinessReaches(Integer id);



}
