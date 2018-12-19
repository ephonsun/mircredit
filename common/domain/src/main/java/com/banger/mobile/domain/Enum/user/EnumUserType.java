/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :用户业务中文枚举
 * Author     :yangy
 * Create Date:2012-5-22
 */
package com.banger.mobile.domain.Enum.user;

/**
 * @author Administrator
 * @version $Id: EnumUserType.java,v 0.1 2012-5-22 下午3:39:53 Administrator Exp $
 */
public enum EnumUserType {
    EXISTSACCOUNT("existsAccount","已存在相同的用户名，请重新输入。"),
    EXISTSUSERCODE("existsUsercode","已存在相同的人员编号，请重新输入。"),
    ACCOUNT("Account"," 用户"),
    DISABLEDLOGIN("disabledLogin","已被禁止登录，请联系系统管理员。"),
    USERDELETE("userDelete","此用户已删除，请联系系统管理员。"),
    PASSWORDNOTTURE("passWordNotTure","密码不正确，请输入正确密码。"),
    EXISTS("exists","不存在，请重新输入。"),
    OLDPASSWORDERROR("oldPassWordError","原始密码错误，请重新输入"),
    OLDEQUALSPASSWORDERROR("oldequalspassworderror","新密码不能与原始密码相同，请重新输入"),
    
    PASSWORDRESETSUCCESS("passWordResetSuccess","密码重置成功"),
    USERPASSWORDREST("userpassWordReset","重置用户密码"),
    ACTIVED_ENABLED("actived_enabled","启用"),
    USER_MANAGE("user_manage","用户管理"),
    USER_LOGIN("user_login","用户登录"),
    USER_LOGOUT("user_logout","用户登出"),
    USER_DISABLED("user_disabled","停用用户"),
    USER_ENABLED("user_enabled","启用用户"),
    USER_UPDATE("user_update","更新用户"),
    USER_AUTHORIZE("user_authorize","已超出系统授权的用户数"),
    ADD_WORKDELEGATE("add_workdelegate","新增工作托管"),
    CANCEL_WORKDELEGATE("cancel_workdelegate","撤销工作托管"),
    USER_ADD("user_add","新增用户"),
    ONLINE("online","在线"),
    OFFLINE("offline","离线"),
    USER_DEL("user_del","删除用户"),
    DEPT_NOT_SUB("dept_not_sub","不能托管给机构"),
    DEPT_WORKDELEGATE("dept_workdelegate","\"已将工作托管给用户\""),
    USER_NAME("user_name","用户\""),
    REPEAT_SELECT("repeat_select","\"，请重新选择！"),
    WORKDELEGATE_USER("workdelegate_user","\"，已将工作托管给用户\""),
    WORKDELEGATE_WHETHER("workdelegate_whether","\"，是否仅托管\""),
    WORK("work","\"的工作？"),
    USER_IS_BUSS("user_is_buss","是业务主管，不支持直接工作托管，可以通过设置助理角色为业务管理员来完成工作托管！"),
    UPDATE_USER_PASSWORD("update_user_passwrod","修改用户密码"),
    ACTIVED_DISABLED("actived_disabled","停用");
    
    
    
    private String code;

    private String name;

    EnumUserType(String code, String name) {
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
