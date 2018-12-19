/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :hk
 * Create Date:Sep 6, 2012
 */
package com.banger.mobile.domain.Enum.product;

/**
 * @author hk
 * @version $Id: EnumProduct.java,v 0.1 Sep 6, 2012 7:10:22 PM hk Exp $
 */
public enum EnumProduct {
    REPORT_DEPTNAME("report_deptname","机构"),
    REPORT_USERNAME("report_username","营销人员"),
    REPORT_CUSTOMERNO("report_customerno","客户编号"),
    REPORT_CUSTOMERNAME("report_customername","客户姓名"),
    REPORT_CUSTOMERPHONE("report_customerphone","客户电话"),
    REPORT_BUYMONEY("report_buymoney","销售额"),
    REPORT_IDCARD("report_idcard","身份证"),
    REPORT_ACCOUNT("report_account","账号"),
    REPORT_BUYDATE("report_buydate","购买时间"),
    REPORT_MONEYUNIT("report_moneyunit","募集单位"),
    REPORTCOUNT_DEPTNAME("REPORTCOUNT_DEPTNAME","机构名称"),
    REPORTCOUNT_MARKETSTAFF("reportcount_marketstaff","营销人员"),
    REPORTCOUNT_TASKCOUNT("reportcount_taskcount","任务量"),
    REPORTCOUNT_UNITMONEY("reportcount_unitmoney","单位"),
    REPORTCOUNT_TASKFINISHCOUNT("reportcount_taskfinishcount","任务完成量"),
    REPORTCOUNT_TASKFINISHSCALE("reportcount_taskfinishscale","任务完成比"),
    REPORTCOUNT_DEPTTASKCOUNT("reportcount_depttaskcount","本机构任务量"),
    REPORTCOUNT_DEPTTASKFINISHSCALE("reportcount_depttaskfinishscale","机构任务完成比"),
    REPORTCOUNT_ALLDEPTTASKFINISHSCALE("reportcount_alldepttaskfinishscale","总行任务完成比"),
    MADE_USER("made_user","制表人："),
    MADE_DATE("made_date","    制表时间："),
    PDT_NAME_EXIST("pdt_name_exist","已存在相同产品名称的产品！"),
    PDT_CODE_EXIST("pdt_code_exist","已存在相同产品编号的产品！"),
    PDT_EXIST_BUYCUS("pdt_exist_buyCus","产品下有客户的购买记录，不能够删除！"),
    PDT_RELATE_TASK("pdt_relate_task","产品与营销任务关联，不能够删除！"),
    REPORT_PRO_EXECUTE("report_pro_execute","执行对象"),
    REPORT_PRO_PNO("report_pro_pno","产品编号"),
    REPORT_PRO_PNAME("report_pro_pname","产品名称"),
    REPORT_PRO_TOTAL("","销售额(万元)"),
    REPORT_PRO_XIAOJI("report_pro_xiaoji","小计"),
    REPORT_PRO_HEJI("report_pro_heji","合计");
    
    private String code;
    private String name;

    EnumProduct(String code,String name) {
        this.code=code;
        this.name=name;
    }
    public String getKey() {
        return this.code;
    }
    public String getValue() {
        return this.name;
    }
    
}
