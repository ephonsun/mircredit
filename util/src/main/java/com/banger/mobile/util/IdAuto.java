
package com.banger.mobile.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @author zhangxiang
 * @version $Id: IdAuto.java,v 0.1 2011-6-12 下午05:52:14 zhangxiang Exp $
 */
public class IdAuto {

    private static int count = 0;
    private final static int maxConut = 100;

    private static Random random = new Random();
    private final static String DATE_FORMAT = "yyyy-MM-dd";

    /**
     * 生成16位的id(时间形式 如：20080815091216)
     *
     * @return
     */
    public static Long genCommID() {

        String base = getFormatDate(new Date(), "yyyyMMddhhmmss");
        return Long.parseLong(base);
    }

    public static Long genExtCommID() {
        count++;
        if (count > maxConut)
            count = 0;
        StringBuffer sb = new StringBuffer("");
        sb.append(genCommID());
        sb.append(count);
        return Long.parseLong(sb.toString());
    }
    public static String genExtStringCommID() {
        count++;
        if (count > maxConut)
            count = 0;
        StringBuffer sb = new StringBuffer("");
        sb.append(genCommID());
        sb.append(count);
        
        return sb.toString();
    }
    public static int getRandom(int length) {
        Random r = new Random();
        return r.nextInt(length);
    }

    public static String randomString(final int bit) {
        StringBuffer activeCode = new StringBuffer();
        for (int i = 0; i < bit; i++) {
            activeCode.append(random.nextInt(10));
        }
        return activeCode.toString();
    }

    public static String getFormatDate(java.util.Date currDate, String format) {
        SimpleDateFormat dtFormatdB = null;
        try {
            dtFormatdB = new SimpleDateFormat(format);
            return dtFormatdB.format(currDate);
        } catch (Exception e) {
            dtFormatdB = new SimpleDateFormat(DATE_FORMAT);
            try {
                return dtFormatdB.format(currDate);
            } catch (Exception ex) {
            }
        }
        return null;
    }
}
