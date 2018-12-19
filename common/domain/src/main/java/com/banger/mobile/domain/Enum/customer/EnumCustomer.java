/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :机构中文枚举
 * Author     :cheny
 * Create Date:2012-5-22
 */
package com.banger.mobile.domain.Enum.customer;

/**
 * @author Administrator
 * @version $Id: EnumUserType.java,v 0.1 2012-5-22 下午3:39:53 Administrator Exp $
 */
public enum EnumCustomer {
    MODEL("model","客户管理"),
    MODEL_ACTION_SAVE("model_action_save","新建客户"),
    MODEL_ACTION_UPDATE("model_action_update","编辑客户"),
    MODEL_ACTION_DEL("model_action_del","删除客户"),
    MODEL_ACTION_DELCOMPLETE("model_action_delcomplete","彻底删除客户"),
    MODEL_ACTION_RESTORE("model_action_restore","还原客户"),
    MODEL_ACTION_MERGE("model_action_merge","合并客户"),
    MODEL_ACTION_CLEANDUSTBIN("model_action_cleandustbin","清空垃圾箱"),
    MODEL_ACTION_RESTOREDUSTBIN("model_action_restoredustbin","还原垃圾箱"),
    MODEL_ACTION_MODIFYCUSBELONGTO("model_action_modifycusbelongto","批量分配客户归属"),
    MODEL_ACTION_SAVESHARECUS("model_action_savesharecus","保存共享客户"),
    MODEL_ACTION_CANCELSHARECUS("model_action_cancelsharecus","取消共享客户"),
    MODEL_ACTION_CANCELSHAREUSER("model_action_cancelshareuser","取消共享用户"),
 
    IMPORT_LINE("import_line","第"),
    IMPORT_NO("import_no","序号"),
    REASONS_FOR_FAILURE("reasons_for_failure","失败原因"),
    IMPORT_FILD_USER("import_fild_user","归属人员\""),
    IMPORT_NOT_YOU_SCOPE("import_not_you_scope","\"不是你管辖的范围"),
    IMPORT_FILD_DEPT("import_fild_dept","归属机构\""),  
    IMPORT_EXCEEDFIELD("import_exceedfield","长度过长"),
    IMPORT_EXCEED("import_exceed","小数点前面保留10位，小数点后面保留4位"),
    IMPORT_FORMATFIELD("import_formatfield","格式不正确,请您检查！"),
    IMPORT_FORMATFIELD_EXT("import_formatfield_ext","分机格式允许输入数字、#、*，长度不能超过10"),
    IMPORT_USER_DEPT_NOT_NULL("import_user_dept_not_null","归属机构和归属人员至少一项不能够为空"),
    IMPORT_USER_NOT_SYSTEM("import_user_not_system","归属人员在系统中不存在，请检查！"),
    IMPORT_DEPT_NOT_SYSTEM("import_dept_not_system","归属机构在系统中不存在，请检查！"),
    IMPORT_DEPT_DATA_ERROR("import_dept_data_error","归属机构数据错误,导入失败。"),
    IMPORT_NOT_NO_DEPT("import_not_on_dept","\"不在归属机构\""),
    IMPORT_DEPTNULL_AND_NOUSER("import_deptnull_and_nouser","归属机构为空，且人员在系统中不存在"),
    IMPORT_CRMCUSTOMER_NOT_NULL("import_crmcustomer_not_null","已存在相同客户编号的客户\""),
    IMPORT_CRMCUSTOMER_BELONGUSERID("IMPORT_CRMCUSTOMER_BELONGUSERID","归属用户\""),
    IMPORT_CRMCUSTOMER_NAME_NOT_NULL("import_crmcustomer_name_not_null","客户姓名不能够为空"),
    IMPORT_CRMCUSTOMER_CODE_NOT_NULL("import_crmcustomer_code_not_null","客户编码不能为空！"),
    IMPORT_CUSTOMER_CODE_NOT_NULL("import_customer_code_not_null","客户编号不能为空"),
    IMPORT_USER_ADMIN_AND("import_user_admin_and","\"是机构系统管理员，不能够维护客户。"),
    IMPORT_UNDER("import_under","\"下"),
    IMPORT_CUSTOMER_NOT_COVER("import_customer_not_cover","在本Excel文件中已存在相同客户编号的客户"),
    IMPORT_DEPT_ISNOT_USER_ISERROR("import_dept_isnot_user_iserror","归属机构为空且用户名数据错误"),
    EXPORT_WARN("export_warn","您已经有一个导出客户请求正在处理，不能再次导出"),
    IMPORT_WARN("import_warn","您已经有一个导入客户请求正在处理，不能再次导入"),
    RESTOREDUSTBIN_ACTION_REMARK("restoredustbin_action_remark","进行还原垃圾箱操作"),
    
    CUSTOMERNO_NEED("customerno_need","客户编号不能为空，请您填充"),
    DATANOTCOMPLETE("datanotcomplete","联系方式不能为空，请您填充"),
    CUSTOMERNO_NOEXIST("customerno_noexist","客户编号不存在"),
    CUSTOMERNO_NOINCHARGE("customerno_noincharge","客户编号对应的权限不足"),
    CUSTOMERNO_TASKEXIST("customerno_taskexist","客户编号在任务中已存在"),
    CUSTOMER_REPART("customer_repart","与内部客户冲突"),
    CUSTOMER_TASKEXIST("customer_taskexist","客户在任务中已存在"),
    CUSTOMER_NOINCHARGE("customer_noincharge","客户对应执行者的归属权限不足"),
    CUSTOMERNOREPEAT("customernorepeat","行客户编号列重复，请修改"),
    PHONENOREPEAT("phonenorepeat","行联系方式列重复，请修改"),
    
    PRODUCTMSG_NEED("productmsg_need","产品信息不完整，请您填充"),
    COLS("clos","列。"),
    EXCELFILE("excelfile","Excel文件中存在第"),
    NAMEREPEAT("namerepeat","行产品名称列重复，请修改"),
    CODEREPEAT("coderepeat","行产品编号列重复，请修改"),
    PRODUCTTYPE_ERROR1("producttype_error1","产品类型与目前导入的产品类型“"),
    PRODUCTTYPE_ERROR2("producttype_error2","”不一致，不能够导入！"),
    PRODUCTNAME_EXIST("productname_exist","系统中已经存在此产品名称"),
    PRODUCTCODE_EXIST("productcode_exist","系统中已经存在此产品编号"),
    PRODUCTDATE_ERROR("productdate_error","上架开始日期不能够大于上架结束日期!")
    
    ;
    
    private String code;

    private String name;

    EnumCustomer(String code, String name) {
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
