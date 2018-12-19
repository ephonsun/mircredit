package com.banger.mobile.facade.impl;

import com.banger.mobile.domain.model.data.LoanData;
import com.banger.mobile.domain.model.loan.LnLoanCoBorrowerBean;
import com.banger.mobile.domain.model.loan.LnLoanGuarantorBean;
import com.banger.mobile.facade.data.CustomerDataService;
import com.banger.mobile.facade.loan.LnLoanCoBorrowerService;
import com.banger.mobile.facade.loan.LnLoanDetailService;
import com.banger.mobile.facade.loan.LnLoanGuarantorService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA. User: yuanme Date: 13-2-26 Time: 下午2:53 To change
 * this template use File | Settings | File Templates.
 */
public class LnLoanDetailServiceImpl implements LnLoanDetailService {
    private LnLoanCoBorrowerService lnLoanCoBorrowerService;
    private LnLoanGuarantorService  lnLoanGuarantorService;
    private CustomerDataService     customerDataService;

    // 取得共同借贷人list
    public List<LnLoanCoBorrowerBean> getLoanCoList(Integer loanId, List<LoanData> dataList) {
        Map<String, Object> conds = new HashMap<String, Object>();
        conds.put("loanId", loanId);
        List<LnLoanCoBorrowerBean> loanCoBorrowerList = lnLoanCoBorrowerService
            .getAllLnLoanCoBorrowerBeanByConds(conds);
        // 是否有照片（头像，身份证正面，反面）
        if(dataList != null && dataList.size()>0){
	        for (LnLoanCoBorrowerBean co : loanCoBorrowerList) {
	            for (LoanData d : dataList) {
	                if (d.getCustomerId().equals(co.getCustomerId())
	                    && (d.getDataType() != null && d.getDataType() == 1)
	                    && (d.getPhotoTypeId() != null && d.getPhotoTypeId() == 1)) {
	                    co.setHasHeadP(1);
	                }
	                if (d.getCustomerId().equals(co.getCustomerId())
	                    && (d.getDataType() != null && d.getDataType() == 1)
	                    && (d.getPhotoTypeId() != null && d.getPhotoTypeId() == 2)) {
	                    co.setHasIdCardZmP(1);
	                }
	                if (d.getCustomerId().equals(co.getCustomerId())
	                    && (d.getDataType() != null && d.getDataType() == 1)
	                    && (d.getPhotoTypeId() != null && d.getPhotoTypeId() == 3)) {
	                    co.setHasIdCardFmP(1);
	                }
	            }
	        }
        }

        return loanCoBorrowerList;
    }

    // 取得担保人list
    public List<LnLoanGuarantorBean> getLoanGuList(Integer loanId, List<LoanData> dataList) {
        Map<String, Object> conds = new HashMap<String, Object>();
        conds.put("loanId", loanId);
        List<LnLoanGuarantorBean> loanGuarantorList = lnLoanGuarantorService
            .getAllLnLoanGuarantorBeanByConds(conds);
        // 是否有照片（头像，身份证正面，反面）
        if(dataList != null && dataList.size()>0){
	        for (LnLoanGuarantorBean gu : loanGuarantorList) {
	            for (LoanData d : dataList) {
	                if (d.getCustomerId().equals(gu.getCustomerId())
	                    && (d.getDataType() != null && d.getDataType() == 1)
	                    && (d.getPhotoTypeId() != null && d.getPhotoTypeId() == 1)) {
	                    gu.setHasHeadP(1);
	                }
	                if (d.getCustomerId().equals(gu.getCustomerId())
	                    && (d.getDataType() != null && d.getDataType() == 1)
	                    && (d.getPhotoTypeId() != null && d.getPhotoTypeId() == 2)) {
	                    gu.setHasIdCardZmP(1);
	                }
	                if (d.getCustomerId().equals(gu.getCustomerId())
	                    && (d.getDataType() != null && d.getDataType() == 1)
	                    && (d.getPhotoTypeId() != null && d.getPhotoTypeId() == 3)) {
	                    gu.setHasIdCardFmP(1);
	                }
	            }
	        }
        }

        return loanGuarantorList;
    }

    // 得到贷款，特定事件下所有图片
    public List<LoanData> getLoanDetailPhotoDataByEvent(Integer loanId, Integer eventId,Integer customerId) {
        List<LoanData> result = new ArrayList<LoanData>();
        Map<String,Object> paramMap = new HashMap<String, Object>();
        paramMap.put("loanId",loanId);
        paramMap.put("statistics",1);
        //paramMap.put("customerId", customerId);
        paramMap.put("eventId", eventId);
        List<LoanData> dataList = customerDataService.getAllLoanDataById(paramMap);
        int i = 0;
        for (LoanData d : dataList) {
            if (d.getEventId() != null && d.getEventId().equals(eventId)) {
                if (d.getDataType() != null && d.getDataType() == 1) {
                    d.setRowNum(i++);
                    result.add(d);
                }
            }
        }
        return result;
    }
    // 得到贷款，特定事件下所有图片
    public List<LoanData> getLoanDetailPhotoDataByEvent(Integer loanId, Integer eventId,Integer customerId,Integer photoTypeId) {
        List<LoanData> result = new ArrayList<LoanData>();
        Map<String,Object> paramMap = new HashMap<String, Object>();
        paramMap.put("loanId",loanId);
        paramMap.put("statistics",1);
        //paramMap.put("customerId", customerId);
        paramMap.put("photoTypeId", photoTypeId);
        paramMap.put("eventId", eventId);
        List<LoanData> dataList = customerDataService.getAllLoanDataById(paramMap);
        int i = 0;
        for (LoanData d : dataList) {
            if (d.getEventId() != null && d.getEventId().equals(eventId)) {
                if (d.getDataType() != null && d.getDataType() == 1) {
                    d.setRowNum(i++);
                    result.add(d);
                }
            }
        }
        return result;
    }
   
    public Integer getLoanDetailPhotoDataByEventCount(Integer loanId, Integer eventId,Integer customerId) {
      
        Map<String,Object> paramMap = new HashMap<String, Object>();
        paramMap.put("loanId",loanId);
        paramMap.put("statistics",1);
        //paramMap.put("customerId", customerId);
        paramMap.put("eventId", eventId);
        Integer numm = customerDataService.getAllLoanDataByIdCount(paramMap);
       
        return numm;
    }
    // 得到贷款，特定事件下所有图片
    public Integer getLoanDetailPhotoDataByEventCount(Integer loanId, Integer eventId,Integer customerId,Integer photoTypeId) {
       
        Map<String,Object> paramMap = new HashMap<String, Object>();
        paramMap.put("loanId",loanId);
        paramMap.put("statistics",1);
        //paramMap.put("customerId", customerId);
        paramMap.put("photoTypeId", photoTypeId);
        paramMap.put("eventId", eventId);
        Integer numm = customerDataService.getAllLoanDataByIdCount(paramMap);
     
        return numm;
    }
    // 给特定事件下图片设置rowNum
    public List<LoanData> setLoanDataListRowNum(List<LoanData> dataList) {
        List<LoanData> result = new ArrayList<LoanData>();
        int i = 0;
        for (LoanData d : dataList) {
            if (d.getDataType() != null && d.getDataType() == 1) {
                d.setRowNum(i++);
            }
            result.add(d);
        }
        return result;
    }

    // 取得特定事件下图片最大rowNum
    public Integer getLoanDataListMaxRowNum(List<LoanData> dataList) {
        Integer result = 0;
        for (LoanData d : dataList) {
             if (d.getRowNum() != null && d.getRowNum() > result) {
                 result = d.getRowNum();
             }
        }
        return result;
    }

    public void setLnLoanCoBorrowerService(LnLoanCoBorrowerService lnLoanCoBorrowerService) {
        this.lnLoanCoBorrowerService = lnLoanCoBorrowerService;
    }

    public void setLnLoanGuarantorService(LnLoanGuarantorService lnLoanGuarantorService) {
        this.lnLoanGuarantorService = lnLoanGuarantorService;
    }

    public void setCustomerDataService(CustomerDataService customerDataService) {
        this.customerDataService = customerDataService;
    }
}
