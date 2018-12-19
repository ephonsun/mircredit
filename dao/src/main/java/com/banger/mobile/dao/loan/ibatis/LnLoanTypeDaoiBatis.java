package com.banger.mobile.dao.loan.ibatis;

import com.banger.mobile.dao.loan.LnLoanTypeDao;
import com.banger.mobile.domain.model.loan.LnLoanSubType;
import com.banger.mobile.domain.model.loan.LnLoanType;
import com.banger.mobile.ibatis.GenericDaoiBatis;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA. User: Zhangfp Date: 13-2-6 Time: 上午9:11 To change
 * this template use File | Settings | File Templates.
 * <p/>
 * 贷款主表
 */
public class LnLoanTypeDaoiBatis extends GenericDaoiBatis implements
		LnLoanTypeDao {

	public LnLoanTypeDaoiBatis() {
		super(LnLoanType.class);
	}

	/**
	 * @param persistentClass
	 */
	public LnLoanTypeDaoiBatis(Class persistentClass) {
		super(LnLoanType.class);
	}

	/**
	 * 取得所有启用的贷款类型
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<LnLoanType> getLoanTypeList() {
		return this.getSqlMapClientTemplate().queryForList("getLoanTypeList");
	}

	/**
	 * 取得子类型
	 * 
	 * @param loanTypeId
	 * @return
	 */
	public List<LnLoanSubType> getLoanSubTypeList(Integer loanTypeId) {
		return this.getSqlMapClientTemplate().queryForList(
				"getLoanSubTypeList", loanTypeId);
	}

	public LnLoanType getLoanTypeById(int id) {
		return (LnLoanType) this.getSqlMapClientTemplate().queryForObject(
				"getLoanTypeById", id);
	}

	public LnLoanType getMaxSortNoLoanType() {
		return (LnLoanType) this.getSqlMapClientTemplate().queryForObject(
				"getMaxSortNoLoanType");
	}

	public LnLoanType getNeedMoveLoanType(Map<String, Object> parameterMap) {
		return (LnLoanType) this.getSqlMapClientTemplate().queryForObject(
				"getNeedMoveLoanType", parameterMap);
	}

	public void updateLoanType(LnLoanType loanType) {
		this.getSqlMapClientTemplate().update("updateLoanType", loanType);
	}

	/**
	 * 批量更新贷款类型
	 */
	public void updateLoanTypes(List<LnLoanType> loanTypes) {
		this.executeBatchUpdate("updateLoanType", loanTypes);
	}

	public LnLoanType getMinSortNoLoanType() {
		return (LnLoanType) this.getSqlMapClientTemplate().queryForObject(
				"getMinSortNoLoanType");
	}

	public void insertLoanType(LnLoanType loanType) {
		this.getSqlMapClientTemplate().insert("insertLoanType", loanType);
	}

	/**
	 * 查找所有有用的贷款类型
	 * 
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<LnLoanType> getAllActivedType(Map<String, Object> paramMap) {
		return this.getSqlMapClientTemplate().queryForList("getAllActivedType",
				paramMap);
	}

	/**
	 * @param 贷款类型名称
	 * @return
	 * @see com.banger.mobile.dao.loan.LnLoanTypeDao#getLoanTypeByName(java.lang.String)
	 */
	public LnLoanType getLoanTypeByName(String name) {
		return (LnLoanType) this.getSqlMapClientTemplate().queryForObject(
				"getLoanTypeByName", name);
	}

	/**
	 * By name and id
	 */
	@SuppressWarnings("unchecked")
	public List<LnLoanType> getLoanTypesByIdAndName(LnLoanType ln) {
		// TODO Auto-generated method stub
		return this.getSqlMapClientTemplate().queryForList(
				"getLoanTypeByNameAndId", ln);
	}
}
