/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       : 贷款各状态id枚举
 * Author     :zhangfp
 * Create Date:2013-3-14
 */
package com.banger.mobile.domain.model.loan;

/**
 * @author zhangfp
 * @version $Id: LoanConstants v 0.1 ${} 上午9:11 zhangfp Exp $
 */
public class LoanConstants {

    //贷款状态常量信息 开始
    public static Integer LOAN_ASK_STATUS = 1;  //申请

    public static Integer LOAN_ASSIGN_STATUS = 2;    //分配

    public static Integer LOAN_EXAM_STATUS = 3;            //调查

    public static Integer LOAN_APPROVAL_STATUS = 4;         //审批

    public static Integer LOAN_LENDING_STATUS = 5;         //放贷

    public static Integer LOAN_LENDED_STATUS = 6;         //贷后

    public static Integer LOAN_PAYED_STATUS = 7;          //结清

    public static Integer LOAN_ASK_REFUSE_STATUS = 11;     //申请拒绝

    public static Integer LOAN_ASSIGN_REFUSE_STATUS = 12;      //分配拒绝

    public static Integer LOAN_EXAM_REFUSE_STATUS = 13;         //调查拒绝

    public static Integer LOAN_APPROVAL_REFUSE_STATUS = 14;     //审批拒绝

    public static Integer LOAN_LENDING_REFUSE_STATUS = 15;       //放贷拒绝

    public static Integer[] LOAN_AFTER_REFUSE_STATUS = new Integer[]{
        LOAN_ASK_REFUSE_STATUS,LOAN_ASSIGN_REFUSE_STATUS,LOAN_EXAM_REFUSE_STATUS,LOAN_APPROVAL_REFUSE_STATUS,LOAN_LENDING_REFUSE_STATUS
    };

    public static String[] LOAN_REFUSE_CONTENT = new String[]{
        "贷款申请拒绝","贷款分配拒绝","贷款调查拒绝","贷款审批拒绝"
    };

    //贷款状态常量信息 结束

    //贷款类型信息 开始
    //消费类型
    public static Integer LOAN_CONSUME_TYPE_ID = 10140;
    //经营类型
    public static Integer LOAN_ENGAGE_TYPE_ID = 10130;
    //贷款类型信息结束

    //贷款事件信息 开始
    public static Integer LOAN_MARKET_EVENT_ = 0;  //营销

    public static Integer LOAN_APPLY_EVENT = 1;
    public static Integer LOAN_DISTRIBUTION_EVENT = 2;//distribution 分配
    public static Integer LOAN_EXAM_EVENT = 3;

    public static Integer LOAN_APPROVE_EVENT = 4;

    public static Integer LOAN_LENDED_EVENT = 5;//放贷

    public static Integer LOAN_DUN_EVENT = 6;

    public static Integer LOAN_LENDING_EVENT = 7;
    //贷款事件信息结束

    public static Integer[] LOAN_EVENT_STATUS_ARR = {
        LOAN_APPLY_EVENT,LOAN_APPLY_EVENT,LOAN_EXAM_EVENT,LOAN_APPROVE_EVENT,LOAN_LENDING_EVENT
    };

    public static String LOAN_BACK_HISTORY_CONTENT = "贷款状态回退";

}
