package com.banger.mobile.domain.Enum.system;

public enum EnumSystem {
	TEMPLATE_UPDATE("","存在"),
	COMMPROGRESSNAME_REPEAT("validationMsg_name","已存在相同的选项！"),
	MODEL("model","系统配置"),
	ACTION_ADD_COMMPROGRESS("action_add_commprogress","新增沟通进度"),
	ADD_COMMPROGRESS_REMARK("add_commprogress_remark","进行添加沟通进度操作"),
	ACTION_UPDATE_COMMPROGRESS("action_update_commprogress","编辑沟通进度"),
	UPDATE_COMMPROGRESS_REMARK("update_commprogress_remark","进行编辑沟通进度操作"),
	ACTION_DEL_COMMPROGRESS("action_del_commprogress","删除沟通进度"),
	DEL_COMMPROGRESS_REMARK("del_commprogress_remark","进行删除沟通进度操作"),
	//客户类型枚举
	CRMCUSTOMERTYPENAME_REPEAT("validationMsg_typename","已存在相同的选项！"),
	    ACTION_ADD_CRMCUSTOMERTYPE("action_add_crmcustomertype","新增客户类型"),
	    ADD_CRMCUSTOMERTYPE_REMARK("add_crmcustomertype_remark","进行添加客户类型操作"),
	    ACTION_UPDATE_CRMCUSTOMERTYPE("action_update_crmcustomertype","编辑客户类型"),
	    UPDATE_CRMCUSTOMERTYPE_REMARK("update_crmcustomertype_remark","进行编辑客户类型操作"),
	    ACTION_DEL_CRMCUSTOMERTYPE("action_del_crmcustomertype","删除客户类型"),
	    DEL_CRMCUSTOMERTYPE_REMARK("del_crmcustomertype_remark","进行删除客户类型操作"),
	    //行业类型
	    CRMCUSTOMERINDUSTRYNAME_REPEAT("validationMsg_industryname","已存在相同的选项！"),
        ACTION_ADD_CRMCUSTOMERINDUSTRY("action_add_crmcustomerindustry","新增行业类型"),
        ADD_CRMCUSTOMERINDUSTRY_REMARK("add_crmcustomerindustry_remark","进行添加行业类型操作"),
        ACTION_UPDATE_CRMCUSTOMERINDUSTRY("action_update_crmcustomerindustry","编辑行业类型"),
        UPDATE_CRMCUSTOMERINDUSTRY_REMARK("update_crmcustomerindustry_remark","进行编辑行业类型操作"),
        ACTION_DEL_CRMCUSTOMERINDUSTRY("action_del_crmcustomerindustry","删除行业类型"),
        DEL_CRMCUSTOMERINDUSTRY_REMARK("del_crmcustomerindustry_remark","进行删除行业类型操作");
	private String code;
    private String name;
    EnumSystem(String code, String name) {
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
