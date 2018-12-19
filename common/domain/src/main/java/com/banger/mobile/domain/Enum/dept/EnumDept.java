/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :机构中文枚举
 * Author     :cheny
 * Create Date:2012-5-22
 */
package com.banger.mobile.domain.Enum.dept;

/**
 * @author Administrator
 * @version $Id: EnumUserType.java,v 0.1 2012-5-22 下午3:39:53 Administrator Exp $
 */
public enum EnumDept {
    DEPTROOT_NAME("deptRoot_name","您管理的机构"),
    DEPTMANROOT_NAME("deptManRoot_name","您负责的机构"),
    DEPTNAME_NOTEXIST("deptName_notExist","归属机构不存在!"),
    DEPTNAME("deptName","机构“"),
    DEPTDEL_ERROR("deptDel_error","”或其子机构下还有人员，无法删除。请先将人员转移到其它机构，再进行删除。"),
    DEPTDEL_CUST("deptDel_cust","”或其子机构下还有业务数据，如：客户，无法删除。请先将这些业务数据转移给其他机构或人员，再进行删除。"),
    DEPTNAME_ISEXIST("deptName_isExist","已存在相同的机构名称，请重新输入!"),
    DEPTCODE_ISEXIST("deptCode_isExist","已存在相同的机构编号，请重新输入!"),
    SAMEDEPTNAME("sameDeptName","存在相同的机构名称"),
    VERIFYDEPT1("verifyDept1","归属机构“"),
    VERIFYDEPT2("verifyDept2","”为当前机构的子机构，请重新选择"),
    MODEL("model","机构管理"),
    MODEL_ACTION_SAVE("model_action_save","新增机构"),
    SAVE_ACTION_REMARK("save_action_remark","进行机构添加操作"),
    MODEL_ACTION_DEL("model_action_del","删除机构"),
    DEL_ACTION_REMARK("del_action_remark","进行机构删除操作"),
    MODEL_ACTION_UPDATE("model_action_update","编辑机构"),
    UPDATE_ACTION_REMARK("update_action_remark","进行机构编辑操作");
    
    private String code;

    private String name;

    EnumDept(String code, String name) {
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
