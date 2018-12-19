/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :任务管理枚举
 * Author     :liyb
 * Create Date:2012-5-28
 */
package com.banger.mobile.domain.Enum.task;

/**
 * @author liyb
 * @version $Id: EnumTask.java,v 0.1 2012-5-28 下午05:00:34 liyb Exp $
 */
public enum EnumTask {
    ACCOUNT_MESSAGE("account_mwssage","客户经理"),
    BUSINESS_COMPETENT("business_competent","业务主管"),
    ACCOUNT_ADMIN("account_admin","系统管理员"),
    ACCOUNT_AGENT("account_agent","机构系统管理员"),
    TASK_UNALL("task_unall","未分配"),
    FINISHED("finished","已完成"),
    FINISH_UNALL("finish_unall","未完成"),
    DEPT_NAME("dept_name","您管理的机构"),
    USER_STOP("user_stop","(已停用)"),
    USER_DEL("user_del","(已删除)"),
    
    TASK_UNfINISH("task_unFinish","所有未完成的任务"),
    TASK_TODAY("task_today","今天执行任务"),
    TASK_THREEDAY("task_threeDay","三天内未完成的任务"),
    TASK_ONEWEEK("task_oneWeek","一周内未完成的任务"),
    TASK_EXPIRED("task_expired","过期的任务"),
    TASK_FINFISH("task_finfish","已完成的任务"),
    TASK_STOP("task_stop","已中止的任务"),
    
    REPORT_USERNAME("reportUserName","分配者"),
    REPORT_TASK_COUNT("reportTaskCount","任务总量"),
    REPORT_FINISH_RATE("reportFinishRate","总完成情况"),
    REPORT_FINISH_RATE_FENBU("reportFinsihRate","完成情况分布"),
    REPORT_TWENTY_FIVE_RATE("reportTwentyFiveRate","0%~25%"),
    REPORT_FIFTY_RATE("reportFiftyRate","25%~50%"),
    REPORT_SEVENTY_FIVE_RATE("reportSeventyFiveRate","50%~75%"),
    REPORT_HUNDRED_RATE("reportHundredRate","75%~100%"),
    
    PLAN_REPORT_ZXZ("planReportZxz","执行者"),
    PLAN_REPORT_JHLXL("planReportJhlxl","计划联系量"),
    PLAN_REPORT_YXLXL("planReportYxlxl","有效联系量"),
    PLAN_REPORT_ZL("planReportZl","总量"),
    PLAN_REPORT_WC("planReportWc","完成"),
    PLAN_REPORT_NYLX("planReportNylx","难以联系"),
    PLAN_REPORT_YXZJHB("planReportYxzjhb","有效占计划比"),
    PLAN_REPORT_YXGLRWS("planReportYxglrws","有效关联任务数"),
    PLAN_REPORT_PM("planReportPm","综合业绩排名");
    
   
    
    private String code;
    private String name;
    EnumTask(String code, String name) {
        this.code = code;
        this.name = name;
    }
    public String getKey() {
        return this.code;
    }
    public String getValue() {
        return this.name;
    }
}
