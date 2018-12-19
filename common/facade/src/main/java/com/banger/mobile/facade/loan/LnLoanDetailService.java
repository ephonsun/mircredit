package com.banger.mobile.facade.loan;

import com.banger.mobile.domain.model.data.LoanData;
import com.banger.mobile.domain.model.loan.LnLoanCoBorrowerBean;
import com.banger.mobile.domain.model.loan.LnLoanGuarantorBean;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-2-26
 * Time: 下午2:52
 * To change this template use File | Settings | File Templates.
 */
public interface LnLoanDetailService {
    //取得共同借贷人list
    List<LnLoanCoBorrowerBean> getLoanCoList(Integer loanId, List<LoanData> dataList);

    //取得担保人list
    List<LnLoanGuarantorBean> getLoanGuList(Integer loanId, List<LoanData> dataList);

    // 得到贷款，特定事件下所有图片
    List<LoanData> getLoanDetailPhotoDataByEvent(Integer loanId, Integer eventId,Integer customerId);
 // 得到贷款，特定事件下所有图片
    List<LoanData> getLoanDetailPhotoDataByEvent(Integer loanId, Integer eventId,Integer customerId,Integer photoTypeId);
    // 得到贷款，特定事件下所有图片
   
    Integer getLoanDetailPhotoDataByEventCount(Integer loanId, Integer eventId,Integer customerId);
    // 得到贷款，特定事件下所有图片
    Integer getLoanDetailPhotoDataByEventCount(Integer loanId, Integer eventId,Integer customerId,Integer photoTypeId);
    // 给特定事件下图片设置rowNum
    List<LoanData> setLoanDataListRowNum(List<LoanData> list);

    // 取得特定事件下图片最大rowNum
    Integer getLoanDataListMaxRowNum(List<LoanData> list);

}
