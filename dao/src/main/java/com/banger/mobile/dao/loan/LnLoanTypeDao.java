package com.banger.mobile.dao.loan;

import com.banger.mobile.domain.model.loan.LnLoanSubType;
import com.banger.mobile.domain.model.loan.LnLoanType;
import com.banger.mobile.domain.model.system.CrmCustomerType;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA. User: Administrator Date: 13-2-6 Time: 上午9:13 To
 * change this template use File | Settings | File Templates.
 */
public interface LnLoanTypeDao {
	/**
	 * 取得所有启用的贷款类型
	 * 
	 * @return
	 */
	public List<LnLoanType> getLoanTypeList();

	/**
	 * 取得子类型
	 * 
	 * @param loanTypeId
	 * @return
	 */
	public List<LnLoanSubType> getLoanSubTypeList(Integer loanTypeId);

	public LnLoanType getLoanTypeById(int id);

	public LnLoanType getMaxSortNoLoanType();

	public LnLoanType getNeedMoveLoanType(Map<String, Object> parameterMap);

	public void updateLoanType(LnLoanType loanType);
    /**
     * 批量修改贷款类型
     * @param loanTypes
     */
	public void updateLoanTypes(List<LnLoanType> loanTypes);

	public LnLoanType getMinSortNoLoanType();

	public void insertLoanType(LnLoanType loanType);

	List<LnLoanType> getAllActivedType(Map<String, Object> paramMap);

	/**
	 * @param 贷款类型名称
	 * @return
	 */
	public LnLoanType getLoanTypeByName(String name);
	
	public List<LnLoanType> getLoanTypesByIdAndName(LnLoanType ln);
}
