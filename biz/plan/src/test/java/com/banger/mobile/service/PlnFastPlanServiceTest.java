/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :快速规划service测试类
 * Author     :yujh
 * Create Date:Jul 18, 2012
 */
package com.banger.mobile.service;

import java.util.Date;

import com.banger.mobile.domain.model.plnFastPlan.PlnFastPlan;
import com.banger.mobile.facade.BaseServiceTestCase;
import com.banger.mobile.facade.plnFastPlan.PlnFastPlanService;

/**
 * @author yujh
 * @version $Id: PlnFastPlanServiceTest.java,v 0.1 Jul 18, 2012 1:27:04 PM Administrator Exp $
 */
public class PlnFastPlanServiceTest extends BaseServiceTestCase {
    private     PlnFastPlanService     plnFastPlanService;

    public void setPlnFastPlanService(PlnFastPlanService plnFastPlanService) {
        this.plnFastPlanService = plnFastPlanService;
    }
//    public void testNotNull(){
//        assertNotNull(plnFastPlanService);
//    }
//    public void testGetByUserId(){
//        System.out.println(this.plnFastPlanService.getPlnFastPlanByUserId(1).size());
//    }
//    public void testGetById(){
//        System.out.println(this.plnFastPlanService.getPlnFastPlanById(20));
//    }
      public void testAdd(){
          PlnFastPlan plan = new PlnFastPlan();
          plan.setAvailableInvestMoney(20);
          plan.setCollageCost(12);
          plan.setCollageYearLimit(1);
          plan.setCollageYearNeed(2);
          plan.setCustomerId(1);
          plan.setCustomerName("sss");
          plan.setCustomerNo("aaaaa");
          plan.setHouseCreditRate(23);
          plan.setHouseCreditRemaining(12);
          plan.setHouseCreditYearLimit(1);
          plan.setHouseMonthlyRepayMent(12);
          plan.setHouseValue(20);
          plan.setIdNo("sdfasdf");
          plan.setIdTypeId(1);
          plan.setInflation(12);
          plan.setIntervalTypeId(1);
          plan.setInvestIncomingRate(20);
          plan.setIsDel(0);
          plan.setIsDoCollagePlan(1);
          plan.setIsDoHousePlan(1);
          plan.setMonthlyDeposit(1);
          plan.setMonthlyFamilyExpend(12);
          plan.setMonthlyFamilyOutLay(11);
          plan.setPhone("1111111");
          plan.setPlanDate(new Date());
          plan.setPlanHouseDownPayMent(1);
          plan.setPlanHousePrice(23);
          plan.setPlanHouseYear(1);
          plan.setPlanHouseYearLimit(2);
          plan.setPlanName("aaaaaaaaaaaaa");
          plan.setRetireAge(23);
          plan.setRetireMonthlyCost(100000000);
          plan.setRetireYearLimit(3);
          plan.setSex("男");
          plan.setUserId(1);
          this.plnFastPlanService.insertFastPlanFromWeb(plan);
      }
}
