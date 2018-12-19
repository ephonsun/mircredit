/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :vm页面帮助类...
 * Author     :yuanme
 * Create Date:2013-1-6
 */
package com.banger.mobile.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * @author yuanme
 * @version $Id: VmHelper.java,v 0.1 2013-1-6 上午9:03:19 Administrator Exp $
 */
public final class VmHelper {

    /**
     * 整型金额加千分号
     * 
     * @param intMoney
     * @return
     */
    public static String getIntegerMoney(Integer intMoney) {
        if (intMoney == null) {
            intMoney = Integer.parseInt("0");
        }
        String formatStr = "###,###";
        DecimalFormat df = (DecimalFormat) NumberFormat.getInstance(Locale.US);
        df.applyPattern(formatStr);
        return df.format(intMoney);
    }

    /**
     * 万元金额
     * 
     * @param money
     * @return
     */
    public static String getDecimalWanMoney(BigDecimal money) {
        if (money == null) {
            money = new BigDecimal(0);
        }
        String formatStr = "#,###.##";
        DecimalFormat df = (DecimalFormat) NumberFormat.getInstance(Locale.US);
        df.applyPattern(formatStr);
        return df.format(money);
    }

    /**
     * 万元金额
     * 
     * @param money
     * @return
     */
    public static String getDecimalMoney(BigDecimal money) {
        if (money == null) {
            money = new BigDecimal(0);
        }
        String formatStr = "#######.##";
        DecimalFormat df = (DecimalFormat) NumberFormat.getInstance(Locale.US);
        df.applyPattern(formatStr);
        return df.format(money);
    }

    /**
     * 格式化小数成百分比显示
     * 
     * @param decValue
     * @return
     */
    public static String getPercentDec(BigDecimal decValue) {
        if (decValue == null) {
            return "0%";
        } else {
            // modifty by huyb 修复传进来1时显示100.%的BUG
            if (decValue.doubleValue() == 1) {
                return "100%";
            }
            if (decValue.doubleValue() > 0) {
                String pDec = "";
//                NumberFormat nf = NumberFormat.getPercentInstance();
//                nf.setMaximumFractionDigits(2);
//                pDec = nf.format(decValue);
                pDec=String.format("%.2f",Double.parseDouble(decValue.toString()) * 100)+"%";
                return pDec;
            } else {
                return "0%";
            }
        }
    }

    /**
     * 得到通过时间，130s --> 2分10秒
     * 
     * @param callTime
     * @return
     */
    public String getCallTimeStr(Integer callTime) {
        if (callTime != null) {
            String min = (callTime / 60) + "分";
            String sec = (callTime % 60) + "秒";
            return min + sec;
        } else {
            return "";
        }
    }

    /**
     * 电话号码隐藏
     * 
     * @param phoneNumber
     */
    public static String getHidePhoneNumber(String phoneNumber) {
        String hideString = "****";
        if (phoneNumber == null || phoneNumber.trim().equals("")) {
            return "";
        }

        if (phoneNumber.length() > 4) {
            return phoneNumber.substring(0, phoneNumber.length() - 4) + hideString;
        } else {
            return phoneNumber;
        }
    }

    /**
     * 身份证隐藏
     * 
     * @param idCard
     */
    public static String getHideIdCard(String idCard) {
        String hideString = "****";
        if (idCard == null || idCard.trim().equals("")) {
            return "";
        }

        if (idCard.length() > 4) {
            return idCard.substring(0, idCard.length() - 4) + hideString;
        } else {
            return idCard;
        }
    }

    /**
     * 统一处理金额保留两位小数点
     * 
     * @param money
     * @return
     */
    public static String getUnityDecimalMoney(BigDecimal money){
        if (money == null){
            money = new BigDecimal(0);
        }
        NumberFormat nf = NumberFormat.getNumberInstance(Locale.US);
        nf.setMaximumFractionDigits(2);
        nf.setMinimumFractionDigits(2);
        return nf.format(money);
    }
}
