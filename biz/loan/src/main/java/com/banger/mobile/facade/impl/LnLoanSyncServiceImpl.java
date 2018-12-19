package com.banger.mobile.facade.impl;

import com.banger.mobile.domain.model.loan.LnRepaymentPlan;
import com.banger.mobile.facade.loan.LnLoanService;
import com.banger.mobile.facade.loan.LnLoanSyncService;
import com.banger.mobile.facade.loan.LnOpHistoryService;
import com.banger.mobile.facade.loan.LnRepaymentPlanService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Calendar;

/**
 * @author yuanme
 * @version $Id: LnLoanSyncServiceImpl.java v 0.1 ${} 下午4:24 yuanme Exp $
 */
public class LnLoanSyncServiceImpl implements LnLoanSyncService {
    private LnLoanService          lnLoanService;
    private LnRepaymentPlanService lnRepaymentPlanService;
    private LnOpHistoryService lnOpHistoryService;

    /**
     * 同步贷款放贷状态
     * 
     * @param loanId
     * @param userId
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public Boolean syncLoanStatus(Integer loanId, Integer userId) {
    	//TODO:修改
    	return true;
    	/*
        // 接口未定暂时假同步
        // TODO
        LnLoan loan = lnLoanService.getLnLoanById(loanId);
        if (loan != null) {
            loan.setLastSyncDate(Calendar.getInstance().getTime());
            loan.setLastSyncUserId(userId);
            loan.setLendDate(Calendar.getInstance().getTime());
            loan.setLoanStatusId(6);// 还款中

            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("loanId", loanId);
            paramMap.put("loanStatusId", loan.getLoanStatusId());
            paramMap.put("lastSyncDate", loan.getLastSyncDate());
            paramMap.put("lastSyncUserId", loan.getLastSyncUserId());
            paramMap.put("lendDate", loan.getLendDate());
            paramMap.put("isCheckout", 0);
            paramMap.put("currentNeedRepay",new BigDecimal(1000)); //本期还款合计
            paramMap.put("sortno",1);  //本期还款期号
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DAY_OF_MONTH,3);
            paramMap.put("repaymentDate", cal.getTime());  //本期还款时间
            paramMap.put("owedPrincipal",new BigDecimal(1000)); //本期应还本金
            paramMap.put("owedInterest",new BigDecimal(10));    //本期应还利息

            //历史操作记录
            LnOpHistory lnOpHistory = new LnOpHistory();
            lnOpHistory.setUserId(userId);
            lnOpHistory.setLoanId(loanId);
            lnOpHistory.setOpHistoryDate(Calendar.getInstance().getTime());
            lnOpHistory.setBeforeStatusId(5);
            lnOpHistory.setAfterStatusId(6);
            lnOpHistory.setContent("同步放贷状态");

            lnLoanService.updateLnLoanById(paramMap);
            lnOpHistoryService.insertLnOpHistory(lnOpHistory);

            syncLoanRepaymentPlan(loanId, userId);
        }
        return true;
    */}

    /**
     * 与核心同步贷款还款计划
     * 
     * @param loanId
     * @param userId
     * @return
     */
    public Boolean syncLoanRepaymentPlan(Integer loanId, Integer userId) {
        //TODO
//        List<LnRepaymentPlan> planList = new ArrayList<LnRepaymentPlan>();
        LnRepaymentPlan p = new LnRepaymentPlan();
        p.setLoanId(loanId);
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, 1);
        p.setPlanDate(cal.getTime());
        p.setPrincipal(new BigDecimal(1000));
        p.setInterest(new BigDecimal(100));
        p.setRemaining(new BigDecimal(0));
        p.setCreateDate(Calendar.getInstance().getTime());
        p.setCreateUser(userId);
        p.setSortno(1);

        LnRepaymentPlan p2 = new LnRepaymentPlan();
        p2.setLoanId(loanId);
        Calendar cal2 = Calendar.getInstance();
        cal.add(Calendar.MONTH,2);
        p2.setPlanDate(cal2.getTime());
        p2.setPrincipal(new BigDecimal(1000));
        p2.setInterest(new BigDecimal(100));
        p2.setRemaining(new BigDecimal(0));
        p2.setCreateDate(Calendar.getInstance().getTime());
        p2.setCreateUser(userId);
        p2.setSortno(2);

//        planList.add(p);
//        planList.add(p2);
//        lnRepaymentPlanService.addRepaymentPlan(p);
//        lnRepaymentPlanService.addRepaymentPlanBatch(planList);
        lnRepaymentPlanService.addRepaymentPlan(p);
        lnRepaymentPlanService.addRepaymentPlan(p2);
        return true;
    }

    /**
     * 同步还款状态
     * 
     * @param loanId
     * @param userId
     * @return
     */
    public Boolean syncLoanDunInfoRepayment(Integer loanId, Integer userId) {
    	//TODO:修改	
    	return true;
    	/*
        // 接口未定暂时假同步
        // TODO
        LnLoan loan = lnLoanService.getLnLoanById(loanId);
        if(loan != null){
            Integer sortNo = lnRepaymentPlanService.getLastSortNoByLoanId(loanId);
            Map<String,Object> paramMap = new HashMap<String, Object>();
            paramMap.put("repaymentStatus",2);
            paramMap.put("loanId",loanId);
            paramMap.put("paidPrincipal",new BigDecimal(1000));
            paramMap.put("paidInterest",new BigDecimal(10));

            LnOpHistory lnOpHistory = new LnOpHistory();
            lnOpHistory.setLoanId(loanId);
            lnOpHistory.setUserId(userId);
            lnOpHistory.setOpHistoryDate(Calendar.getInstance().getTime());
            lnOpHistory.setBeforeStatusId(6);

            if (sortNo.equals(loan.getSortno())){
                //当前还款为最后一期，把还款状态设置为已还款，并把贷款状态设置为已结清
                paramMap.put("loanStatusId",7); //已结清

                lnOpHistory.setAfterStatusId(7);
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                lnOpHistory.setContent("同步贷款" + format.format(loan.getRepaymentDate()) + "还款状态为：已还款，贷款结清");

                lnLoanService.syncLoan(paramMap,lnOpHistory);
            }else {
                //当前还款不是最后一期，只要把还款状态设置为已还款，即可

                paramMap.put("sortno",sortNo+1);
                //历史操作记录
                lnOpHistory.setAfterStatusId(6);
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                lnOpHistory.setContent("同步贷款" + format.format(loan.getRepaymentDate()) + "还款状态为：已还款");

                lnLoanService.syncLoan(paramMap,lnOpHistory);
            }
        }
        return true;
    */}

    // 同步客户账户余额
    public Boolean syncLoanDunInfoRemaining(Integer loanId, Integer userId) {
    	//TODO:修改
    	return true;
    /*
        LnLoan loan = lnLoanService.getLnLoanById(loanId);
        if(loan != null && loan.getIsNogood() != null && loan.getIsNogood() != 1){
            //客户必须为非不良客户
            Map<String,Object> paramMap = new HashMap<String, Object>();
            paramMap.put("loanId",loanId);
            paramMap.put("accountRemaining",10000);

            lnLoanService.updateLnLoanById(paramMap);
        }
        return true;
    */}

    /**
     * setter getter *
     */
    public void setLnLoanService(LnLoanService lnLoanService) {
        this.lnLoanService = lnLoanService;
    }

    public void setLnRepaymentPlanService(LnRepaymentPlanService lnRepaymentPlanService) {
        this.lnRepaymentPlanService = lnRepaymentPlanService;
    }

    public LnOpHistoryService getLnOpHistoryService() {
        return lnOpHistoryService;
    }

    public void setLnOpHistoryService(LnOpHistoryService lnOpHistoryService) {
        this.lnOpHistoryService = lnOpHistoryService;
    }
}
