/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :liyb
 * Create Date:2012-5-23
 */
package com.banger.mobile.domain.Enum.role;

/**
 * @author liyb
 * @version $Id: EnumSysRole.java,v 0.1 2012-5-23 上午10:22:33 liyb Exp $
 */
public enum EnumSysRole {
    SYSROLE("sysRole","角色“"),
    IS_USE_DEL("is_use_del","”已被人员使用，不能删除"),
    VALIDATION_ROLE_NAME("role_name","已存在相同的角色名，请重新输入!"),
    DEL_FAIL("del_fail","删除失败!"),
    MODEL("model","角色管理"),
    MODEL_ACTION_SAVE("model_action_save","新增角色"),
    SAVE_ACTION_REMARK("save_action_remark","进行角色添加操作"),
    MODEL_ACTION_DEL("model_action_del","删除角色"),
    TEAM_ACTION_DEL("team_action_del","删除团队"),
    TEAMUSER_ACTION_DEL("teamuser_action_del","删除团队成员"),
    VALIDATION_TEAM_NAME("team_name","已存在相同的团队名，请重新输入!"),
    DEL_ACTION_REMARK("del_action_remark","进行角色删除操作"),
    MODEL_ACTION_UPDATE("model_action_update","编辑角色"),
    UPDATE_ACTION_REMARK("update_action_remark","进行角色编辑操作");
    private String code;
    private String name;
    EnumSysRole(String code, String name) {
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
