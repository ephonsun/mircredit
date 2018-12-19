/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :贷款Dao测试
 * Author     :zhangfp
 * Create Date:2013-3-27
 
package com.banger.mobile.dao;

import com.banger.mobile.dao.loan.LnLoanDao;
import com.banger.mobile.dao.loan.LnLoanInfoDao;
import com.banger.mobile.dao.loan.LnRepaymentPlanDao;
import com.banger.mobile.domain.model.loan.LnLoan;
import com.banger.mobile.domain.model.loan.LnLoanInfo;

import java.text.SimpleDateFormat;
import java.util.*;

*//**
 * @author zhangfp
 * @version $Id: LnLoanDaoTest.java v 0.1 ${} 下午4:24 Administrator Exp $
 *//*
public class LnLoanDaoTest extends BaseDaoTestCase{

    private LnLoanDao lnLoanDao;
    private LnRepaymentPlanDao lnRepaymentPlanDao;
    private LnLoanInfoDao lnLoanInfoDao;

    public LnLoanInfoDao getLnLoanInfoDao() {
        return lnLoanInfoDao;
    }

    public void setLnLoanInfoDao(LnLoanInfoDao lnLoanInfoDao) {
        this.lnLoanInfoDao = lnLoanInfoDao;
    }

    public LnRepaymentPlanDao getLnRepaymentPlanDao() {
        return lnRepaymentPlanDao;
    }

    public void setLnRepaymentPlanDao(LnRepaymentPlanDao lnRepaymentPlanDao) {
        this.lnRepaymentPlanDao = lnRepaymentPlanDao;
    }

    public LnLoanDao getLnLoanDao() {
        return lnLoanDao;
    }

    public void setLnLoanDao(LnLoanDao lnLoanDao) {
        this.lnLoanDao = lnLoanDao;
    }

    public void testGetRecentlyLoanStatusByCusIds(){
        Map<String,Object> paramMap = new HashMap<String, Object>();
        List<Integer> cusIdList = new ArrayList<Integer>();
        cusIdList.add(248);
        cusIdList.add(250);
        paramMap.put("customerIds",cusIdList);
        List<LnLoan> resultMapList = lnLoanDao.getRecentlyLoanStatusByCusIds(paramMap);
        System.out.println(resultMapList.size());
    }

    public void testGetLastSortNoByLoanId(){
        Integer sortNo = lnRepaymentPlanDao.getLastSortNoByLoanId(1);
        System.out.println(sortNo);
    }

    public void testGetAllLoanByUserId(){
        Map<String,Object> paramMap = new HashMap<String, Object>();
        Integer[] userIds = new Integer[3];
        userIds[0] = 11;
        userIds[1] = 4;
        userIds[2] = 248;
        paramMap.put("sysUserIds","20,4,248");
        paramMap.put("loanStatusId",2);
        paramMap.put("startRow",1);
        paramMap.put("endRow",10);
        List<LnLoan> lnLoanList = lnLoanDao.getAllLoanByUserId(paramMap);
        System.out.println(lnLoanList.size());
    }

    public void testGetLoanCountByLoanStatusForWork(){
        Map<String,Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isWorkPlace",1);
        paramMap.put("needSubmitOfWorkUserId",110);
        paramMap.put("unAllotOfWorkUserIds","110,170");
        List<LnLoan> mapList = lnLoanDao.getLoanCountByLoanStatusId(paramMap);
        System.out.println(mapList.size());
    }

    public void testGetApproveFailCount(){
        Map<String,Object> paramMap = new HashMap<String, Object>();
        Calendar nowCal = Calendar.getInstance();
        System.out.println(nowCal.getTime());
        nowCal.add(Calendar.MONTH,-3);
        Date beforeThreeMonth = nowCal.getTime();
        System.out.println(nowCal.getTime());
        paramMap.put("customerId",123);
        paramMap.put("approveDate",beforeThreeMonth);
        Integer count = lnLoanDao.getApproveFailCount(paramMap);
        System.out.println(count);
    }

    public void testgetLastLoanByCustomer(){
        LnLoan lnLoan = lnLoanDao.getLastLoanByCustomer(1);
        System.out.println(lnLoan.toString());
    }

    public void testgetLoanByStatusId(){
        Map<String,Object> loanParamMap = new HashMap<String, Object>();
        loanParamMap.put("loanStatusId",3);
        loanParamMap.put("startRow",1);
        loanParamMap.put("endRow",5);
        List<LnLoanInfo> loanInfoMap = lnLoanDao.getLoanByStatusId(loanParamMap);
        System.out.println(loanInfoMap.size());
    }

    public void testInsertLoan(){
        Integer i = 1;
        while (i < 100000){
            Integer seqLnLoan = lnLoanDao.getNextLoanId();
            LnLoan lnLoan = new LnLoan();
            lnLoan.setLoanId(seqLnLoan);
            lnLoan.setCustomerId(301);
            lnLoan.setLoanTypeId(1);
            lnLoan.setLoanSubTypeId(10000);
            lnLoan.setApplyUserId(11);
            lnLoan.setEventId(2);
            lnLoan.setLoanStatusId(2);
            lnLoan.setCreateDate(new Date());
            lnLoan.setUpdateDate(new Date());
            lnLoan.setCreateUser(11);
            lnLoan.setUpdateUser(11);
            lnLoanDao.insertLoan(lnLoan);

            i++;
        }
    }

    public void testselectAllLoanList(){
        Map<String,Object> paramMap = new HashMap<String, Object>();
        paramMap.put("startRow",1);
        paramMap.put("endRow",20);
        long startTime = Calendar.getInstance().getTime().getTime();
        try {
            List<LnLoan> loanList = lnLoanDao.selectAllLoanList(paramMap);
            System.out.println(loanList.size());
        }catch (Exception e){
            log.error("LnLoanDaoTest % testselectAllLoanList",e);
        }
        long endTime = Calendar.getInstance().getTime().getTime();
        System.out.println((endTime - startTime) / 1000);
    }

    public void testInsertLoanInfoList(){
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            List<LnLoanInfo> list = new ArrayList<LnLoanInfo>();
            LnLoanInfo info1 = new LnLoanInfo();
            info1.setLoanId(1003);
            list.add(info1);
            LnLoanInfo info2 = new LnLoanInfo();
            info2.setLoanId(1004);
            info2.setGenDate(format.parse("2014-02-09"));
            info2.setAppUsage("张张张张格张张张张张格张张张张张格张张张张张格张张张张张格张张张张张格张张张张张格张张张张张格张张张张张格张张张张张格张");
            list.add(info2);
            lnLoanInfoDao.insertLnLoanInfoBatch(list);
        }catch (Exception e){
            logger.error("LnLoanDaoTest % testInsertLoanInfoList",e);
        }
    }
}
*/