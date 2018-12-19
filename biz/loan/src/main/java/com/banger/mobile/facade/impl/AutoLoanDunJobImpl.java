package com.banger.mobile.facade.impl;

import com.banger.mobile.domain.model.loan.AutoLoanDunJob;
import com.banger.mobile.facade.loan.*;
import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created with IntelliJ IDEA. User: yuanme Date: 13-3-5 Time: 下午2:29 To change
 * this template use File | Settings | File Templates.
 */
public class AutoLoanDunJobImpl implements AutoLoanDunJob {

    private static Logger logger = Logger.getLogger(AutoLoanDunJobImpl.class);

    private LnLoanService          lnLoanService;
    private LnRepaymentPlanService lnRepaymentPlanService;
    private LnDunLogService        lnDunLogService;
    private LnDunSetingService     lnDunSetingService;
    private LnOpHistoryService     lnOpHistoryService;
    private LnExceptionRepaymentPlanService lnExceptionRepaymentPlanService;

    private Integer isRun;
    @Transactional
    public void doJob() {
    	logger.info("doJob Start Step1");
    	if (isRun.equals(1)){
    		logger.info("doJob Start Step2");
    		lnLoanService.doSyncDBJob();
    		logger.info("doJob Stop");
    	}
    }

    /**
     * getter setter *
     */

    public Integer getIsRun() {
        return isRun;
    }

    public void setIsRun(Integer isRun) {
        this.isRun = isRun;
    }


    public void setLnLoanService(LnLoanService lnLoanService) {
        this.lnLoanService = lnLoanService;
    }

    public void setLnRepaymentPlanService(LnRepaymentPlanService lnRepaymentPlanService) {
        this.lnRepaymentPlanService = lnRepaymentPlanService;
    }

    public void setLnDunLogService(LnDunLogService lnDunLogService) {
        this.lnDunLogService = lnDunLogService;
    }

    public void setLnDunSetingService(LnDunSetingService lnDunSetingService) {
        this.lnDunSetingService = lnDunSetingService;
    }

    public void setLnOpHistoryService(LnOpHistoryService lnOpHistoryService) {
        this.lnOpHistoryService = lnOpHistoryService;
    }

    public void setLnExceptionRepaymentPlanService(
                                                   LnExceptionRepaymentPlanService lnExceptionRepaymentPlanService) {
        this.lnExceptionRepaymentPlanService = lnExceptionRepaymentPlanService;
    }
    
}
