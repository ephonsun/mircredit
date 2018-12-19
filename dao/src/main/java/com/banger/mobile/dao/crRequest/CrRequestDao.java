package com.banger.mobile.dao.crRequest;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.model.crRequest.CrRequest;
import com.banger.mobile.domain.model.data.Photo;
import com.banger.mobile.domain.model.loan.LnLoan;
import com.banger.mobile.domain.model.loan.LnOpHistory;
import com.banger.mobile.domain.model.uploadFile.SysUploadFile;

import java.util.List;
import java.util.Map;

/**
 * Created by BH-TCL on 15-1-19.
 */
public interface CrRequestDao {

    void addCrRequest(CrRequest crRequest);

    //void updateCrRequest(Map<String, Object> paramMap);
    
    void updateCrRequest(CrRequest crRequest);

    List<CrRequest> queryCrRequest(Map<String,Object> map);

	PageUtil<CrRequest> getCrRequestByPage(Map<String, Object> parameterMap,Page page);

	CrRequest getCrRequestById(Integer requestId);
				
	/**
	 * @author xiall
	 * @param loanId
	 * @param customerId
	 * @return
	 */
	public CrRequest getCrRequestByLoanIdAndCustomerId(Integer loanId,Integer customerId);
	public void updateRequestResult(String requestResult,Integer requestId);
}
