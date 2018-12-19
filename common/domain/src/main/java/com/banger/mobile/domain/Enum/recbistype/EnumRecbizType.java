/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :业务类型枚举
 * Author     :liyb
 * Create Date:2012-5-22
 */
package com.banger.mobile.domain.Enum.recbistype;

/**
 * @author liyb
 * @version $Id: EnumRecbizType.java,v 0.1 2012-5-22 下午03:29:06 liyb Exp $
 */
public enum EnumRecbizType {
    ACTIVED_ENABLED("actived_enabled","启用"),
    ACTIVED_DISABLED("actived_disabled","停用"),
    VALIDATIONMSG_NUM("validationMsg_num","已存在相同的业务编号,请重新输入!"),
    VALIDATIONMSG_NAME("validationMsg_name","已存在相同的选项!"),
    VALIDATIONMSG_EMPTY("validationMsg_name","“业务名称”必须填写!"),
    SORT_FIRST("sort_first","当前已经处于第一位！"),
    SORT_LAST("sort_last","当前已经处于最后一位！"),
    SORT_TRUE("sort_true","True"),
    MODEL("model","基础数据业务类型"),
    MODEL_ACTION_DEL("model_action_del","删除业务类型"),
    DEL_ACTION_REMARK("del_action_remark","进行业务类型删除操作!"),
    PAD_ACTIVED_ENABLED("pad_actived_enabled","在线"),
    PAD_ACTIVED_STOP("pad_actived_stop","停用"),
    PAD_ACTIVED_DISABLED("pad_actived_disabled","离线"),
    PAD_LINK_CONNECTED("pad_link_connected","已连接"),
    PAD_LINK_NOT_CONNECTED("pad_link_not_connected","未连接");
    
    private String code;
    private String name;
    EnumRecbizType(String code, String name) {
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
