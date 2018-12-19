/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :理财规划模板枚举类
 * Author     :cheny
 * Create Date:2012-7-17
 */
package com.banger.mobile.domain.Enum.plnReportTemplate;

/**
 * @author cheny
 * @version $Id: PlnReportTemplateEnum.java,v 0.1 2012-7-17 下午2:12:13 cheny Exp $
 */
public enum EnumPlnReportTemplate {
    UNIK_TEMPLATENAME("unik_templateName","模板名称不能重复"),
    UNIK_TEMPLATENO("unik_templateNo","模板编号不能重复"),
    NO_TEMPALTE("no_template","没有可用的模板，请先启用相应的模板"),
    IS_TEMPALTE("is_tempalte","是否覆盖已生成的规划报告"),
    UNIK_ISACTIVE("unik_isactive","“规划类型+适合对象”的组合字段，存在唯一启用项，不可重复存在"),
    MODEL("model","理财规划模板管理"),
    MODEL_ACTION_SAVE("model_action_save","新增理财规划模板"),
    SAVE_ACTION_REMARK("save_action_remark","进行理财规划模板新增操作"),
    MODEL_ACTION_DEL("model_action_del","删除理财规划模板"),
    DEL_ACTION_REMARK("del_action_remark","进行理财规划模板删除操作"),
    MODEL_ACTION_UPDATE("model_action_update","编辑理财规划模板"),
    UPDATE_ACTION_REMARK("update_action_remark","进行理财规划模板编辑操作");
    
    private String code;

    private String name;

    EnumPlnReportTemplate(String code, String name) {
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
