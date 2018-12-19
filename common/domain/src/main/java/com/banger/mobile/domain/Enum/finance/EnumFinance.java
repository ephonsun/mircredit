package com.banger.mobile.domain.Enum.finance;
/**
 * @author wumh E-mail:wumh@baihang-china.com
 * @version 创建时间：Dec 8, 2012 10:39:52 AM
 * 类说明
 */
public enum EnumFinance {
	FE_READ("fe_read","阅读"),
	FE_DISCESS("fe_discess","评论"),
	FE_COLLECT("fe_collect","收藏"),
	FE_SUPPORT("fe_support","支持"),
	FE_REPLY("fe_reply","回复"),
	MANAGER_NAME_REPORT("manager_name_report","客户经理"),
	COLUMN_NAME_REPORT("column_name_report","名称"),
	ARITCLE_COUNT_REPORT("aritcle_count_report","总条(必读)"),
	READ_COUNT_REPORT("read_count_report","已读(必读)"),
	UNREAD_COUNT_REPORT("unRead_count_report","未读(必读)"),
	READ_RATE_REPORT("read_rate_report","阅读率(必读率)");
	
    private String code;

    private String name;

    EnumFinance(String code, String name) {
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



