package com.banger.mobile.util;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Random;

import com.hundsun.common.lang.StringUtil;

public class BangerStringUtil extends StringUtil {
    private static Random randGen           = null;
    private static char[] numbersAndLetters = null;
    private static Object initLock          = new Object();

    
   
    /**
     * 从今天起,之后daysAfter天的日期
     * @param daysAfter 可以为负数,负数表示之前多少天
     * @author yuancong 2009-9-10
     */
    public static String dateStr(int daysAfter) {
        return DateUtil.getDateTime("yyyy-MM-dd", DateUtil.getDate(new Date(), daysAfter));
    }

    /**
     *
     * @param length 随机字符串长度
     * @return
     */

    public static String randomString(int length) {
        if (length < 1) {
            return null;
        }
        if (randGen == null) {
            synchronized (initLock) {
                if (randGen == null) {
                    randGen = new Random();
                    numbersAndLetters = ("0123456789abcdefghijklmnopqrstuvwxyz"
                                         + "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ").toCharArray();
                }
            }
        }
        char[] randBuffer = new char[length];
        for (int i = 0; i < randBuffer.length; i++) {
            randBuffer[i] = numbersAndLetters[randGen.nextInt(71)];
        }
        return new String(randBuffer);

    }

    /**
     * 替换字符串的最后几位
     * @param text 查找的字符串
     * @param with 替换的字符串
     * @param lastLength text被替换的最后几位的长度
     * @return
     */
    public static String replace(String text, String with, int lastLength) {
        StringBuffer buf = new StringBuffer();
        if ((text == null) || (with == null) || (lastLength <= 0)) {
            return text;
        }
        if (text.length() > lastLength) {
            buf.append(text.substring(0, text.length() - lastLength));
            buf.append(with);
        } else {
            buf.append(with);
        }
        return buf.toString();
    }

    public static String subString(String str, int maxWidth) {
        if (str == null) {
            return null;
        }
        if (str.length() <= maxWidth) {
            return str;
        }
        return str.substring(0, maxWidth);
    }
    
    public static int length(String str) {
        if(str!=null)
            return str.length();
        else
            return 0;
    }

    public static String escapeSqlFilter(String str) {
        if (str == null)
            return null;
        int length = str.length();

        StringBuffer sql = new StringBuffer();
        for (int i = 0; i < length; i++) {
            char ch = str.charAt(i);
            switch (ch) {
                case '%':
                    sql.append("\\%");
                    break;
                case '?':
                    sql.append("\\?");
                    break;
                case '\'':
                    sql.append("''");
                    break;
                default:
                    sql.append(ch);

            }
        }
        return sql.toString();
    }

    public static String escapeGoodsAttrFilter(String str) {
        if (str == null)
            return null;
        int length = str.length();

        StringBuffer sql = new StringBuffer();
        for (int i = 0; i < length; i++) {
            char ch = str.charAt(i);
            switch (ch) {
                case ':':
                    break;
                case ';':
                    break;
                default:
                    sql.append(ch);

            }
        }
        return sql.toString();
    }

    public static String encodeUrl(String url) {
        return encodeUrl(url, "utf-8");
    }

    public static String encodeUrl(String url, String enc) {

        if (!url.contains("?")) {
            return url;
        } else {

            StringBuffer urlFinal = new StringBuffer();

            String urlPrefix = substringBefore(url, "?");
            urlFinal.append(urlPrefix);
            urlFinal.append("?");

            String urlParams = substringAfter(url, "?");
            String[] params = urlParams.split("&");

            for (String param : params) {

                param = encodeParam(param, enc);

                urlFinal.append(param);
                int count = 1;
                if (count < params.length) {
                    urlFinal.append("&");
                    count++;
                }
            }
            return urlFinal.toString();
        }

    }

    public static void main(String args[]) {
//        String sql = "detail.html?tradeId=111111+afadf=asdf&tttt=8888";
//        System.out.println(Bank6677StringUtil.encodeUrl(sql));
        String test="ddddddddddddddd";
        System.out.println(subString(test,5));
        //System.out.println(new Date().getTime() + EmallStringUtil.randomString(8));
    }

    private static String encodeParam(String param, String enc) {
        try {
            return substringBefore(param, "=") + "="
                   + java.net.URLEncoder.encode(substringAfter(param, "="), enc);
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    /**
     * eg: leftString("001",2,".")="001"
     * leftString("001.002",2,".")="001.002"
     * leftString("001.002.003",2,".")="001.002"
     * @param src 源字符串
     * @param size 分隔符出现次数
     * @param sep 分隔符
     * @return
     */
    public static String leftString(String src, int size, String sep) {
        if (null == src) {
            return null;
        }
        if (null == sep || size == 0) {
            return src;
        }
        String result = "";
        String[] ss = BangerStringUtil.split(src, sep);
        int i = 1;
        for (String s : ss) {
            if (i > size) {
                break;
            }
            result = result + s + ".";
            i++;
        }
        result = BangerStringUtil.left(result, result.length() - 1);
        return result;
    }
    
    /**
     * 字符串分割
     */
    public static String[]  splitStr(String str){
        return str.split("_");
    }
    /**
     * 字符串分割
     */
    public static String[]  SplitString(String str){
        return str.split(",");
    }
}
