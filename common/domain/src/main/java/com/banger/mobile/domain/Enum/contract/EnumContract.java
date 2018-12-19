/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :机构中文枚举
 * Author     :cheny
 * Create Date:2012-5-22
 */
package com.banger.mobile.domain.Enum.contract;

/**
 * @author Administrator
 * @version $Id: EnumUserType.java,v 0.1 2012-5-22 下午3:39:53 Administrator Exp $
 */
public enum EnumContract {
    CONT_COMMON_MAIN("11","1","借款合同（个人业务）" ,"contCommMain.doc"),
    CONT_TOP_MAIN("21","2","最高额借款合同（个人业务）" ,"contTopMain.doc"),
    CONT_CARD_MAIN("31","3","最高额借款合同(卡贷宝)" ,"contCardMain.doc"),

    CONT_COMMON_GUARANTY("12","1","保证担保合同" ,"contCommGuaranty.docx"),
    CONT_TOP_GUARANTY("22","2","最高额保证担保合同" ,"contTopGuaranty.doc"),
    CONT_CARD_GUARANTY("32","3","最高额保证合同(卡贷宝)" ,"contCardGuaranty.doc"),

    CONT_COMMON_MORTGAGE("13","1","抵押担保合同" ,"contCommMortgage.docx"),
    CONT_TOP_MORTGAGE("23","2","最高额抵押担保合同" ,"contTopMortgage.docx"),
    CONT_CARD_MORTGAGE("33","3","最高额抵押担保合同(卡贷宝)" ,"contCardMortgage.doc"),

    CONT_COMMON_PLEDGE("14","1","质押担保合同" ,"contCommPledge.docx"),
    CONT_TOP_PLEDGE("24","2","最高额质押担保合同" ,"contTopPledge.docx"),
//  CONT_CARD_PLEDGE("34","1","最高额质押担保合同(卡贷宝)" ,"contCardPledge.doc"),

    DEFAULT("","","",""),
    ;

    private String code;//合同
    private String type;//合同类型
    private String fileName_CN;
    private String fileName_EN;

    EnumContract(String code, String type, String fileName_CN ,String fileName_EN) {
        this.code = code;
        this.type = type;
        this.fileName_CN = fileName_CN;
        this.fileName_EN = fileName_EN;
    }

    public static EnumContract getEnumByCode (String code){

        EnumContract[] enums = EnumContract.values();
        for (EnumContract aEnum : enums){
            if(aEnum.code.equals(code)){
                return aEnum;
            }
        }

        return DEFAULT;

    }

    public String getCode() {
        return code;
    }

    public String getType() {
        return type;
    }

    public String getFileName_CN() {
        return fileName_CN;
    }

    public String getFileName_EN() {
        return fileName_EN;
    }
}
