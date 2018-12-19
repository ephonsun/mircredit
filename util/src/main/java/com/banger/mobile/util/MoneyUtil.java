package com.banger.mobile.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * @author: zhangxiang
 * @since: 2008-11-27 ����06:21:24
 * @history:
 ************************************************
 * @file: MoneyUtil.java
 * @Copyright: 2008 banger Co.,Ltd. All right reserved.
 ************************************************/
public class MoneyUtil {

    private static final int DEF_DIV_SCALE = 2;

    public MoneyUtil() {
    }

    public static double add(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.add(b2).doubleValue();
    }

    public static double add(String v1, String v2) {
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.add(b2).doubleValue();
    }

    public static double sub(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.subtract(b2).doubleValue();
    }

    public static double sub(String v1, String v2) {
        if (v1 == null || v1.equals("")) {
            v1 = "0";
        }
        if (v2 == null || v2.equals("")) {
            v2 = "0";
        }
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.subtract(b2).doubleValue();
    }

    public static double mul(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.multiply(b2).doubleValue();
    }

    public static double mul(String v1, String v2) {
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.multiply(b2).doubleValue();
    }

    public static double div(double v1, double v2) {
        return div(v1, v2, 2);
    }

    public static double div(String v1, String v2) {
        if (v1 == null || v1.equals("")) {
            v1 = "0";
        }
        if (v2 == null || v2.equals("")) {
            v2 = "0";
        }
        return div(Double.parseDouble(v1), Double.parseDouble(v2), 2);
    }

    public static double div(double v1, double v2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero");
        } else {
            BigDecimal b1 = new BigDecimal(Double.toString(v1));
            BigDecimal b2 = new BigDecimal(Double.toString(v2));
            return b1.divide(b2, scale, 4).doubleValue();
        }
    }

    public static double div(String v1, String v2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero");
        } else {
            BigDecimal b1 = new BigDecimal(v1);
            BigDecimal b2 = new BigDecimal(v2);
            return b1.divide(b2, scale, 4).doubleValue();
        }
    }

    public static double round(double v, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero");
        } else {
            BigDecimal b = new BigDecimal(Double.toString(v));
            BigDecimal one = new BigDecimal("1");
            return b.divide(one, scale, 4).doubleValue();
        }
    }

    public static double round(String v, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero");
        } else {
            BigDecimal b = new BigDecimal(v);
            BigDecimal one = new BigDecimal("1");
            return b.divide(one, scale, 4).doubleValue();
        }
    }

    /**
     * ת���ɽ�Ǯ���ַ�,���ʽ(}λС��,ǧ��λ)
     *
     * @param intMoney
     * @return
     * @create Dec 15, 2008 9:40:39 AM yuancong
     * @history
     */
    public static String getFormatMoney(String strMoney) {
        return getFormatMoney(strMoney, null);
    }

    /**
     * ת���ɽ�Ǯ���ַ�,���ʽ(}λС��,ǧ��λ)
     *
     * @param strMoney
     * @param formatStr
     *            ��ʽ(����: ###.00 Ԫ),
     *            #ռλ��ʾ�ɿ�,0ռλ��ʾ���˾Ͳ�0ռλ,Eռλ��ʾʹ�ÿ�ѧ����,��ʽ�м��������ַ��ֱ����ʾ��4
     *            ,����ǰ����ߺ���Ӹ�[Ԫ]��.������ο�DecimalFormat
     * @return
     * @create Dec 15, 2008 9:40:39 AM yuancong
     * @history
     */
    public static String getFormatMoney(String strMoney, String formatStr) {
        return getFormatMoney(strMoney, formatStr, Locale.US);
    }

    /**
     * ת���ɽ�Ǯ���ַ�,���ʽ(}λС��,ǧ��λ)
     *
     * @param strMoney
     * @param formatStr
     *            ��ʽ(����: ###.00 Ԫ),
     *            #ռλ��ʾ�ɿ�,0ռλ��ʾ���˾Ͳ�0ռλ,Eռλ��ʾʹ�ÿ�ѧ����,��ʽ�м��������ַ��ֱ����ʾ��4
     *            ,����ǰ����ߺ���Ӹ�[Ԫ]��.������ο�DecimalFormat
     * @param locale
     *            ʹ���Ĺ�����ָ�ʽ,���9��ǧ��λ4��ʾ����
     * @return
     * @create Dec 15, 2008 9:40:39 AM yuancong
     * @history
     */
    public static String getFormatMoney(String strMoney, String formatStr,
            Locale locale) {
        Double doubleMoney;
        if (strMoney == null || strMoney.trim().equals("")) {
            strMoney = "0";
        }
        try {
            doubleMoney = Double.valueOf(strMoney);
        } catch (Exception e) {
            return strMoney;
        }
        return getFormatMoney(doubleMoney, formatStr, locale);
    }

    /**
     * ת���ɽ�Ǯ���ַ�,���ʽ(}λС��,ǧ��λ)
     *
     * @param intMoney
     * @return
     * @create Dec 15, 2008 9:40:39 AM yuancong
     * @history
     */
    public static String getFormatMoney(Integer intMoney) {
        return getFormatMoney(intMoney, null);
    }

    /**
     * ת���ɽ�Ǯ���ַ�,���ʽ(}λС��,ǧ��λ)
     *
     * @param intMoney
     * @param formatStr
     *            ��ʽ(����: ###.00 Ԫ),
     *            #ռλ��ʾ�ɿ�,0ռλ��ʾ���˾Ͳ�0ռλ,Eռλ��ʾʹ�ÿ�ѧ����,��ʽ�м��������ַ��ֱ����ʾ��4
     *            ,����ǰ����ߺ���Ӹ�[Ԫ]��.������ο�DecimalFormat
     * @return
     * @create Dec 15, 2008 9:40:39 AM yuancong
     * @history
     */
    public static String getFormatMoney(Integer intMoney, String formatStr) {
        return getFormatMoney(intMoney, formatStr, Locale.US);
    }

    /**
     * ת���ɽ�Ǯ���ַ�,���ʽ(}λС��,ǧ��λ)
     *
     * @param intMoney
     * @param formatStr
     *            ��ʽ(����: ###.00 Ԫ),
     *            #ռλ��ʾ�ɿ�,0ռλ��ʾ���˾Ͳ�0ռλ,Eռλ��ʾʹ�ÿ�ѧ����,��ʽ�м��������ַ��ֱ����ʾ��4
     *            ,����ǰ����ߺ���Ӹ�[Ԫ]��.������ο�DecimalFormat
     * @param locale
     *            ʹ���Ĺ�����ָ�ʽ,���9��ǧ��λ4��ʾ����
     * @return
     * @create Dec 15, 2008 9:40:39 AM yuancong
     * @history
     */
    public static String getFormatMoney(Integer intMoney, String formatStr,
            Locale locale) {
        if (intMoney == null) {
            intMoney = Integer.parseInt("0");
        }
        if (null == formatStr || formatStr.trim().equals("")) {
            formatStr = "��#,##0.00";
        }
        DecimalFormat df = (DecimalFormat) NumberFormat.getInstance(locale);// ����ʹ���9����ָ�ʽ(ǧ��λ)
        df.applyPattern(formatStr);// ����Ӧ�ý���ʽ
        return df.format(intMoney);
    }

    /**
     * ת���ɽ�Ǯ���ַ�,���ʽ(}λС��,ǧ��λ)
     *
     * @param doubleMoney
     * @return
     * @create Dec 15, 2008 9:40:39 AM yuancong
     * @history
     */
    public static String getFormatMoney(Double doubleMoney) {
        return getFormatMoney(doubleMoney, null);
    }

    /**
     * ת���ɽ�Ǯ���ַ�,���ʽ
     *
     * @param doubleMoney
     * @param formatStr
     *            ��ʽ(����: ###.00 Ԫ),
     *            #ռλ��ʾ�ɿ�,0ռλ��ʾ���˾Ͳ�0ռλ,Eռλ��ʾʹ�ÿ�ѧ����,��ʽ�м��������ַ��ֱ����ʾ��4
     *            ,����ǰ����ߺ���Ӹ�[Ԫ]��.������ο�DecimalFormat
     * @return
     * @create Dec 15, 2008 9:40:39 AM yuancong
     * @history
     */
    public static String getFormatMoney(Double doubleMoney, String formatStr) {
        if (doubleMoney == null) {
            doubleMoney = Double.valueOf(0);
        }
        return getFormatMoney(doubleMoney, formatStr, Locale.US);
    }

    /**
     * ת���ɽ�Ǯ���ַ�,���ʽ
     *
     * @param doubleMoney
     * @param formatStr
     *            ��ʽ(����: ###.00 Ԫ),
     *            #ռλ��ʾ�ɿ�,0ռλ��ʾ���˾Ͳ�0ռλ,Eռλ��ʾʹ�ÿ�ѧ����,��ʽ�м��������ַ��ֱ����ʾ��4
     *            ,����ǰ����ߺ���Ӹ�[Ԫ]��.������ο�DecimalFormat
     * @param locale
     *            ʹ���Ĺ�����ָ�ʽ,���9��ǧ��λ4��ʾ����
     * @return
     * @create Dec 15, 2008 9:40:39 AM yuancong
     * @history
     */
    public static String getFormatMoney(Double doubleMoney, String formatStr,
            Locale locale) {
        if (doubleMoney == null) {
            doubleMoney = Double.valueOf(0);
        }
        if (null == formatStr || formatStr.trim().equals("")) {
            formatStr = "��#,##0.00";
        }
        DecimalFormat df = (DecimalFormat) NumberFormat.getInstance(locale);// ����ʹ���9����ָ�ʽ(ǧ��λ)
        df.applyPattern(formatStr);// ����Ӧ�ý���ʽ
        return df.format(doubleMoney);
    }

    /**
     * ת���ɽ�Ǯ���ַ�,���ʽ(}λС��,ǧ��λ)
     *
     * @param bigDecimalMoney
     * @return
     * @create Dec 15, 2008 9:40:39 AM yuancong
     * @history
     */
    public static String getFormatMoney(BigDecimal bigDecimalMoney) {
        return getFormatMoney(bigDecimalMoney, null);
    }

    /**
     * ת���ɽ�Ǯ���ַ�,���ʽ
     *
     * @param bigDecimalMoney
     * @param formatStr
     *            ��ʽ(����: ###.00 Ԫ),
     *            #ռλ��ʾ�ɿ�,0ռλ��ʾ���˾Ͳ�0ռλ,Eռλ��ʾʹ�ÿ�ѧ����,��ʽ�м��������ַ��ֱ����ʾ��4
     *            ,����ǰ����ߺ���Ӹ�[Ԫ]��.������ο�DecimalFormat
     * @param locale
     *            ʹ���Ĺ�����ָ�ʽ,���9��ǧ��λ4��ʾ����
     * @return
     * @create Dec 15, 2008 9:40:39 AM yuancong
     * @history
     */
    public static String getFormatMoney(BigDecimal bigDecimalMoney,
            String formatStr) {
        return getFormatMoney(bigDecimalMoney, formatStr, Locale.US);
    }

    /**
     * ת���ɽ�Ǯ���ַ�,���ʽ
     *
     * @param bigDecimalMoney
     * @param formatStr
     *            ��ʽ(����: ###.00 Ԫ),
     *            #ռλ��ʾ�ɿ�,0ռλ��ʾ���˾Ͳ�0ռλ,Eռλ��ʾʹ�ÿ�ѧ����,��ʽ�м��������ַ��ֱ����ʾ��4
     *            ,����ǰ����ߺ���Ӹ�[Ԫ]��.������ο�DecimalFormat
     * @param locale
     *            ʹ���Ĺ�����ָ�ʽ,���9��ǧ��λ4��ʾ����
     * @return
     * @create Dec 15, 2008 9:40:39 AM yuancong
     * @history
     */
    public static String getFormatMoney(BigDecimal bigDecimalMoney,
            String formatStr, Locale locale) {
        if (bigDecimalMoney == null) {
            bigDecimalMoney = BigDecimal.valueOf(0.00);
        }
        if (null == formatStr || formatStr.trim().equals("")) {
            formatStr = "��#,##0.00";
        }
        DecimalFormat df = (DecimalFormat) NumberFormat.getInstance(locale);// ����ʹ���9����ָ�ʽ(ǧ��λ)
        df.applyPattern(formatStr);// ����Ӧ�ý���ʽ
        return df.format(bigDecimalMoney);
    }

    /**
     * �۸���ת������100��ȡ����
     */
    public static int getMoneyPoint(double amount1, double amount2) {
        int point = 0;
        if (amount1 + amount2 > 0.0) {
            point = (int) ((amount1 + amount2) / 100);
        }
        return point;
    }

    public static int getMoneyPointGive(double amount1, double amount2,
            double amount3) {
        int point = 0;
        if ((amount1 + amount2 - amount3) > 0.0) {
            point = (int) ((amount1 + amount2 - amount3) / 100);
        }
        return point;
    }

    /**
     * ���ת���ɼ۸񣬳�100��ȡ2λС��
     */
    public static double getPointToMoney(double amount1) {
        double point = 0.0;
        if (amount1 > 0.0) {
            point = ((amount1) / 100);
        }
        return point;
    }

    /**
     * ��ּ���2��ֵ���������
     */
    public static int integerAdd(double point1, double point2) {
        int point = 0;
        if ((point1 + point2) > 0.0) {
            point = (int) (point1 + point2);
        }
        return point;
    }

    /**
     * ��ּ���2��ֵ���������
     */
    public static int integerMul(double point1, int point2) {
        int point = 0;
        String acount = "" + point1 * point2;
        if ((point1 * point2) > 0.0) {
            BigDecimal dd = new BigDecimal(acount).setScale(0,
                    BigDecimal.ROUND_HALF_UP);
            point = dd.intValue();
        }
        return point;
    }

    /**
     * �ַ�ת���ɸ���
     *
     * @param money
     * @return
     */
    public static double StringToDouble(String money) {
        return Double.parseDouble(money) / 100;
    }

    /**
     * ��������ʾ
     *
     * @param money
     * @return
     */
    public static double DoubleMoney(Double money) {
        if (null == money) {
            return 0;
        }
        return money / 100;
    }

    /*
     * ����ɷ�ʹ�ã����/100
     */
    public static String getFormatMoneyByPay(Object money) {
        if (money == null || "".equals(money)) {
            money = "0";
        }
        Double temp = Double.parseDouble(money.toString()) / 100;
        return getFormatMoney(temp.toString(), "#,##0.00");
    }

    /*
     * ����ɷ�ʹ�ã����/100
     */
    public static String getFormatMoneyByPay2(Object money) {
        if (money == null || "".equals(money)) {
            money = "0";
        }
        Double temp = Double.parseDouble(money.toString()) * 100;
        return getFormatMoney(temp.toString(), "#,##0.00");
    }
    public static void main(String args[]) {
//      System.out.println(mul(add(5D, 663.85000000000002D), 1.01D));
        System.out.println(getFormatMoneyByPay(-23L));

    }
    
    /**
     * 千分位保留4位小数  huangk
     */
    public static String decimeMoney(Object money){
        DecimalFormat f=new DecimalFormat("#,##0.0000");
        return f.format(Double.parseDouble(money.toString()));
    }
    /**
     * 比例保留2位小数  huangk
     */
    public static String decimePercent(Object money){
        DecimalFormat f=new DecimalFormat("##0.00");
        return f.format(Double.parseDouble(money.toString()));
    }
}