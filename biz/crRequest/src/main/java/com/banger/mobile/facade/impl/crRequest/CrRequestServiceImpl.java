package com.banger.mobile.facade.impl.crRequest;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.dao.crRequest.CrRequestDao;
import com.banger.mobile.domain.model.crRequest.CrRequest;
import com.banger.mobile.facade.crRequest.CrRequestService;
import java.util.List;
import java.util.Map;

/**
 * Created by BH-TCL on 15-1-19.
 */
public class CrRequestServiceImpl implements CrRequestService{

	private CrRequestDao crRequestDao;

	public void setCrRequestDao(CrRequestDao crRequestDao) {
		this.crRequestDao = crRequestDao;
	}

	public CrRequestDao getCrRequestDao() {
		return crRequestDao;
	}

	@Override
	public void addCrRequest(CrRequest crRequest) {
		crRequestDao.addCrRequest(crRequest);
	}

	// 征信修改
	public void updateCrRequest(CrRequest crRequest) {
		crRequestDao.updateCrRequest(crRequest);
	}

	// 查询

	public PageUtil<CrRequest> getCrRequestByPage(
			Map<String, Object> parameterMap, Page page) {
		return crRequestDao.getCrRequestByPage(parameterMap, page);
	}



	public List<CrRequest> queryCrRequest(Map<String, Object> map) {
		return crRequestDao.queryCrRequest(map);
	}

	public CrRequest getCrRequestById(Integer requestId) {
		return crRequestDao.getCrRequestById(requestId);
	}

	/**
	 * @author xiall
	 * @保存征信信息(手机端提交)
	 * @param crRequest
	 */
	public void saveCrRequest(CrRequest crRequest) {
		// 判断新增还是修改
		Integer loanId = crRequest.getLoanId();
		Integer customerId = crRequest.getCustomerId();

		CrRequest obj = crRequestDao.getCrRequestByLoanIdAndCustomerId(loanId,
				customerId);

		if (obj == null) {
			crRequestDao.addCrRequest(crRequest);
		} else {
			crRequest.setRequestId(obj.getRequestId());
			crRequestDao.updateCrRequest(crRequest);
		}

	}

	public CrRequest getCrRequestByLoanIdAndCustomerId(Integer loanId,
			Integer customerId) {
		return crRequestDao.getCrRequestByLoanIdAndCustomerId(loanId,
				customerId);
	}

	public void updateRequestResult(String requestResult,Integer requestId){
		crRequestDao.updateRequestResult(requestResult, requestId);
	}
}
