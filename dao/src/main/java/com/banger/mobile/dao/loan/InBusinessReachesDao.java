package com.banger.mobile.dao.loan;

import com.banger.mobile.domain.model.loan.InBusinessReaches;

import java.util.List;
import java.util.Map;

public interface InBusinessReachesDao {

	/**
	 * 保存
	 * @param lnBusinessModel
	 * @return 
	 */
	void  insertBusinessReaches(InBusinessReaches inBusinessReaches);
    
	/**
	 * 按照类型
	 * @param lnBusinessModel
	 * @param sbt
	 * @return
	 */
	List<InBusinessReaches> selectByyReachesType(Integer reachesType ,Integer loanId);
/**
	 * 按照类型
	 * @param lnBusinessModel
	 * @param sbt
	 * @return
	 */
	List<InBusinessReaches> selectbReachesType(Integer loanId);


	/**
	 * 删除
	 * @param lnBusinessModel
	 * @return
	 */
	void  deleteInBusinessReaches(Integer id);


}
