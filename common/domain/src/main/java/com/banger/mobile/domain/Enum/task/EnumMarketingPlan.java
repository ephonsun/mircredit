package com.banger.mobile.domain.Enum.task;

public enum EnumMarketingPlan {
    
    PLAN_REPORT_1("planReport1","执行者"),
    PLAN_REPORT_2("planReport2","产品指标"),
    PLAN_REPORT_3("planReport3","营销目标"),
    PLAN_REPORT_4("planReport4","计划营销额"),
    PLAN_REPORT_5("planReport5","实际营销额"),
    PLAN_REPORT_6("planReport6","实际占计划比"),
    PLAN_REPORT_7("planReport7","实际关联任务"),
    PLAN_REPORT_8("planReport8","排名");
    
    private String code;
    private String name;
    EnumMarketingPlan(String code, String name) {
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
