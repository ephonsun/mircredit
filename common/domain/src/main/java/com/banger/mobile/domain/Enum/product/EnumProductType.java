/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :用户业务中文枚举
 * Author     :yangy
 * Create Date:2012-5-22
 */
package com.banger.mobile.domain.Enum.product;

/**
 * @author Administrator
 * @version $Id: EnumUserType.java,v 0.1 2012-5-22 下午3:39:53 Administrator Exp $
 */
public enum EnumProductType {
    DATANOTCOMPLETE("datanotcomplete","客户购买记录信息不完整，请您填充"),
    COLS("clos","列。"),
    ACCOUNT("account","营销人员\""),
    EXCELFILE("excelfile","Excel文件中存在第"),
    REPEAT("repeat","行重复的数据，请您合并后在导入"),
    SYSTEMEXISTCUS("systemexistcus","系统中已经存在客户\""),
    TOACCOUNT("toaccount","\",向营销人员\""),
    BUY("buy","\"购买了"),
    RECORD("record","\"的一笔记录，如果确实是两笔不同的业务，则可以手工添加此笔购买记录"),
    ACCOUNTDOESNOTEXIST("accountdoesnotexist","在系统中不存在，请您维护好营销人员后，再导入"),
    PHONENOTTRUE("phonenottrue","联系电话数据不正确，请您检查。"),
    DATELARGETODAY("datelargetoday","购买日期不能够大于今天!"),
    BUYDATE("buydate","购买日期数据不正确，请您检查。"),
    DATEINSELLDATE("DATEINSELLDATE","购买日期不在产品的上架日期范围内"),
    
    SYSW_LOGSTR("sysw_logstr","长度不能够超过20位"),
    SYSW_NOTNULL("sysw_notnull","不能够为空"),
    SYSW_NOTNUMBER("sysw_notnumber","数据格式错误，只允许输入数字"),
    SYS_LANGTH_REMARK("sys_langth_remark","长度不能够超过300位");
    
    private String code;

    private String name;

    EnumProductType(String code, String name) {
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
