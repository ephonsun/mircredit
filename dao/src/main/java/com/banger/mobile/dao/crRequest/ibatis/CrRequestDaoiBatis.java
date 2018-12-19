package com.banger.mobile.dao.crRequest.ibatis;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.dao.crRequest.CrRequestDao;
import com.banger.mobile.domain.model.base.crRequest.BaseCrRequest;
import com.banger.mobile.domain.model.crRequest.CrRequest;
import com.banger.mobile.ibatis.GenericDaoiBatis;
import com.banger.mobile.util.StringUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by BH-TCL on 15-1-19.
 */
public class CrRequestDaoiBatis extends GenericDaoiBatis implements CrRequestDao {

	public CrRequestDaoiBatis(Class persistentClass) {
		super(persistentClass);
	}

	public CrRequestDaoiBatis() {
		super(CrRequest.class);
	}

	@Override
	public void addCrRequest(CrRequest crRequest) {

		this.getSqlMapClientTemplate().insert("insertCrRequest",
				(BaseCrRequest) crRequest);
	}

	public void updateCrRequest(CrRequest crRequest) {
		getSqlMapClientTemplate().update("updateCrRequest",
				(BaseCrRequest) crRequest);
	}

	@Override
	public List<CrRequest> queryCrRequest(Map<String, Object> map) {
		return this.getSqlMapClientTemplate().queryForList("queryCrRequest",
				map);
	}


	// 征信列表查询
	public PageUtil<CrRequest> getCrRequestByPage(
			Map<String, Object> parameterMap, Page page) {
		Map<String, Object> fConds = new HashMap<String, Object>();
		for (Map.Entry<String, Object> entry : parameterMap.entrySet()) {
			if (entry.getValue() instanceof String) {
				fConds.put(entry.getKey(), StringUtil.ReplaceSQLEscapeChar(StringUtil.ReplaceSQLChar(entry.getValue().toString())));
			} else {
				fConds.put(entry.getKey(), entry.getValue());
			}
		}
		List<CrRequest> list = (List<CrRequest>) this.findQueryPage("getCrRequestList", "getCrRequestCount", fConds, page);
		return new PageUtil<CrRequest>(list, page);
	}



	public CrRequest getCrRequestById(Integer requestId) {
		return (CrRequest) this.getSqlMapClientTemplate().queryForObject(
				"getCrRequestById", requestId);
	}

	public CrRequest getCrRequestByLoanIdAndCustomerId(Integer loanId,
													   Integer customerId) {
		Map<String, Object> conds = new HashMap<String, Object>();
		conds.put("loanId", loanId);
		conds.put("customerId", customerId);
		return (CrRequest) this.getSqlMapClientTemplate().queryForObject(
				"getCrRequestByLoanIdAndCustomerId", conds);
	}

	public void updateRequestResult(String requestResult,Integer requestId){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("requestResult", requestResult);
		map.put("requestId", requestId);
		getSqlMapClientTemplate().update("updateRequestResult",map);
	}
}
