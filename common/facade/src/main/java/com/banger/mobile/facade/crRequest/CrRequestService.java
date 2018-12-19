package com.banger.mobile.facade.crRequest;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.model.crRequest.CrRequest;
import com.banger.mobile.domain.model.loan.LnLoan;
import com.banger.mobile.domain.model.loan.LnOpHistory;
import com.banger.mobile.domain.model.uploadFile.SysUploadFile;

import java.util.List;
import java.util.Map;

/**
 * Created by BH-TCL on 15-1-19.
 */
public interface CrRequestService {
//新增
    void addCrRequest(CrRequest crRequest);
//修改
    void updateCrRequest(CrRequest crRequest);
    CrRequest getCrRequestById(Integer requestId);
    List<CrRequest> queryCrRequest(Map<String,Object> map);

    PageUtil<CrRequest> getCrRequestByPage(Map<String, Object> parameterMap, Page page); 
   
   
    /**
     * @author xiall
     * @保存征信信息(手机端提交)
     * @param crRequest
     */
    public void saveCrRequest(CrRequest crRequest);
    
    public CrRequest  getCrRequestByLoanIdAndCustomerId(Integer loanId,Integer customerId);
    
    public void updateRequestResult(String requestResult,Integer requestId);
}
