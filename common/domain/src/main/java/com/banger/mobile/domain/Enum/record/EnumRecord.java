/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :联系记录枚举
 * Author     :huangk
 * Create Date:2012-6-26
 */
package com.banger.mobile.domain.Enum.record;

/**
 * @author huangk
 * @version $Id: EnumRecbizType.java,v 0.1 2012-6-26 下午03:29:06 huangk Exp $
 */
public enum EnumRecord {
    EXPORT_ERROR("export_error","导出列表发生系统异常"),
    RECORD_NAME("record_name","联系记录"),
    RECORD_DEL("record_del","删除联系记录操作"),
    RECORD_DELS("record_dels","删除多条联系记录操作"),
    RECORDISCANCELED_UPDATE("recordiscanceled_update","修改录音状态操作"),
    RECORDDETAIL_DEL("recorddetail_del","查看详情删除记录操作"),
    RECORD_READED("record_readed","联系记录已读操作"),
    RECORD_READSED("record_readsed","联系记录多个已读操作"),
    CONNECT_UPDATE("connect_update","修改沟通记录操作"),
    EXPORT_EXCEL("export_excel","导出Excel操作"),
    EXPORT_RECS("export_recs","导出录音操作"),
    EXCEL_RECORDNO("excel_recordNo","流水号"),
    EXCEL_CUSTOMER("excel_customer","客户姓名(电话)"),
    EXCEL_CALLTYPE("excel_callType","联系类型"),
    EXCEL_STARTDATE("excel_startDate","开始时间"),
    EXCEL_CALLTIME("excel_callTime","时长"),
    EXCEL_USER("excel_user","客户经理"),
    EXCEL_BIZTYPE("excel_bizType","业务类型"),
    EXCEL_RECORDSOURCE("excel_recordSource","记录来源"),
    EXCEL_STATE("excel_state","状态"),
    EXCEL_REMARK("excel_remark","备注"),
    EXCEL_KNOWCUSTOMER("excel_knowcustomer","未知"),
    ALL_RECORDINFO("all_recordinfo","所有联系记录"),
    CALL_INFO("call_info","通话记录"),
    LOCALE_INFO("locale_info","座谈记录"),
    VISIT_INFO("visit_info","拜访记录"),
    ARCHIVE_INFO("archive_info","归档记录"),
    CALL_IN("call_in","呼入"),
    CALL_OUT("call_out","呼出"),
    MISSED_CALLS("missed_calls","未接"),
    UNREAD_MESSAGE("unread_message","未读留言"),
    ALREADY_CALLS("already_calls","已接"),
    DIAL_CALLS("dial_calls","已拨"),
    SEND_SMS("send_sms","已发短信"),
    RECEIVE_SMS("receive_sms","已收短信"),
    EXPORT_WARN("export_warn","您已经有一个导出联系记录请求正在处理，不能再次导出"),
    EXPORT_File_WARN("export_file_warn","您已经有一个导出录音文件请求正在处理，不能再次导出"),
    SMS("sms","短信");
    private String code;
    private String name;
    EnumRecord(String code, String name) {
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
