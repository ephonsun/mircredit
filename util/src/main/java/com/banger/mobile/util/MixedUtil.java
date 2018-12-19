/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :quartz任务时间处理
 * Author     :zhangxiang
 * Create Date:2012-8-15
 */
package com.banger.mobile.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.log4j.Logger;



/**
 * @author zhangxiang
 * @version $Id: MixedUtil.java,v 0.1 2012-8-15 下午5:10:29 zhangxiang Exp $
 */
public class MixedUtil {
    public final static Logger  logger                          = Logger.getLogger(MixedUtil.class);

    public static final String  SCHEDULING_DATE_FORMAT          = "yyyy_MM_dd_HH";

    public static final String  DATE_FORMAT_YYYYMMDD            = "yyyyMMdd";

    public static final String  DATE_FORMAT_YYYY_MM_DD_HH_MI_SS = "yyyy-MM-dd HH:mm:ss";

    // 格式化日期 yyyy-mm-dd
    public static final String  DATE_FORMAT_YYYY_MM_DD          = "yyyy-MM-dd";

    private static final String LOCAL_IP_ADDRESS                = "127.0.0.1";

    public static String getCurrentDateStr() {
        return getDateFormatStr(new Date(), DATE_FORMAT_YYYY_MM_DD_HH_MI_SS);
    }

    public static String getDateFormatStr(Date date, String dateFormat) {
        SimpleDateFormat format = new SimpleDateFormat(dateFormat);
        return format.format(date);
    }

    public static String countTime(long begin, long end) {
        long day;
        long hour;
        long minute;
        long second;
        long millSecond;
        long temp = end - begin;
        millSecond = temp % 1000;
        temp = temp / 1000;
        second = temp % 60;
        temp = temp / 60;
        minute = temp % 60;
        temp = temp / 60;
        hour = temp % 24;
        day = temp / 24;

        StringBuffer buffer = new StringBuffer();
        if (day != 0) {
            buffer.append(day).append("d");
        }
        if (hour != 0) {
            buffer.append(hour).append("h");
        }
        if (minute != 0) {
            buffer.append(minute).append("m");
        }
        if (second != 0) {
            buffer.append(second).append("m");
        }
        if (millSecond != 0) {
            buffer.append(millSecond).append("m");
        }
        return buffer.toString();

    }

    public static String getDateString(int intervalDate) {
        SimpleDateFormat yyyyMMdd = new SimpleDateFormat(DATE_FORMAT_YYYYMMDD);
        return getIntervalDate(new Date(), Calendar.DATE, intervalDate, yyyyMMdd);
    }

    public static String getIntervalDate(Date date, int field, int amount,
                                         SimpleDateFormat dateFormat) {
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(date);
        gc.add(field, amount);
        return dateFormat.format(gc.getTime());
    }

    public static String getIntervalDate(String dateStr, int field, int amount,
                                         SimpleDateFormat dateFormat) {
        GregorianCalendar gc = new GregorianCalendar();
        Date date = dateFormat.parse(dateStr, new ParsePosition(0));
        gc.setTime(date);
        gc.add(field, amount);
        return dateFormat.format(gc.getTime());

    }

    /**
     * 取间隔天数
     *
     * @param enddate
     * @param begindate
     * @return
     */
    public static int getIntervalDays(Date enddate, Date begindate) {
        long millisecond = enddate.getTime() - begindate.getTime();
        int day = (int) Math.ceil((millisecond / (24 * 60 * 60 * 1000)));
        return day;
    }

    /**
     * 将日期字符串解析为日期类型(String to Date)
     *
     * @param dateString
     *            不可心为null或空
     * @param format
     *            可以为null,默认格式化"yyyy-MM-dd"
     * @return
     */
    public static Date parse(String dateString, String format) throws Exception {
        if (format == null) {
            format = DATE_FORMAT_YYYY_MM_DD;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.parse(dateString);
    }

    public static String genFileName(String source, String replaced, String replace) {
        return source.replace(replaced, replace);
    }

    public static void closeQuietInputStream(InputStream inputStream) {
        try {
            if (inputStream != null) {
                inputStream.close();
            }
        } catch (IOException ioe) {
            logger.error(ioe);
        }
    }

    public static void closeQuietOutputStream(OutputStream outputStream) {
        try {
            if (outputStream != null) {
                outputStream.close();
            }
        } catch (IOException ioe) {
            logger.error(ioe);
        }
    }

    /**
     * 为什么要用此复杂的方式获取IP地址信息，由于linux下用简单的方式
     * (InetAddress.getLocalHost().getHostAddress()) 获取只会得到"127.0.0.1",
     * 此地址信息没什么大的用处
     *
     * @return 本地IP地址信息
     */
    public static List<String> getLocalAddrs() {
        List<String> ipList = new ArrayList<String>();
        Enumeration<NetworkInterface> netInterfaces = null;
        try {
            netInterfaces = NetworkInterface.getNetworkInterfaces();
            while (netInterfaces.hasMoreElements()) {
                NetworkInterface ni = netInterfaces.nextElement();
                Enumeration<InetAddress> ips = ni.getInetAddresses();
                while (ips.hasMoreElements()) {

                    String tempIp = ips.nextElement().getHostAddress();
                    // 过滤不需要的IP地址信息
                    if (!tempIp.equals(LOCAL_IP_ADDRESS)) {
                        ipList.add(tempIp);
                    }
                }
            }
        } catch (SocketException se) {
            logger.error(se);
        }
        ipList.add("127.0.0.1");
        return ipList;
    }

    /**
     * 校验本地IP地址是否是允许的地址
     *
     * @param allowedIPs
     * @return 允许返回true,反之false
     */
    public static boolean checkingLocalAllowedIP(String allowedIPs) {
        if (allowedIPs == null || allowedIPs.equals("")) {
            return false;
        }
        // 校验标志
        boolean flag = false;
        // 不用进行null校验，前面已经进行处理过
        List<String> tempList = getLocalAddrs();
        for (int i = 0; i < tempList.size(); i++) {
            String tempIP = (String) tempList.get(i);
            if (allowedIPs.indexOf(tempIP) != -1) {
                flag = true;
                break;
            }
        }
        return flag;
    }

    public static List<String> readLines(InputStream input, String encoding) throws IOException {
        if (encoding == null) {
            return readLines(input);
        } else {
            InputStreamReader reader = new InputStreamReader(input, encoding);
            return readLines(reader);
        }
    }

    public static List<String> readLines(InputStream input) throws IOException {
        InputStreamReader reader = new InputStreamReader(input);
        return readLines(reader);
    }

    public static List<String> readLines(Reader input) throws IOException {
        BufferedReader reader = new BufferedReader(input);
        List<String> list = new ArrayList<String>();
        String line = reader.readLine();
        while (line != null) {
            list.add(line);
            line = reader.readLine();
        }
        return list;
    }

    /**
     * 根据网卡取本机配置的IP
     * @return
     */
    public static String getLinuxIpAddress() {
        try {
            Enumeration netInterfaces;
            netInterfaces = NetworkInterface.getNetworkInterfaces();
            InetAddress ip = null;
            while (netInterfaces.hasMoreElements()) {
                NetworkInterface ni = (NetworkInterface) netInterfaces.nextElement();
                if (logger.isDebugEnabled()) {
                    logger.debug(ni.getName());
                }
                ip = (InetAddress) ni.getInetAddresses().nextElement();
                if (!ip.isSiteLocalAddress() && !ip.isLoopbackAddress()
                    && ip.getHostAddress().indexOf(":") == -1) {
                    return ip.getHostAddress();
                } else {
                    ip = null;
                }
            }
        } catch (SocketException e) {
            logger.error("get linux ip address failure", e);
        }

        return "";
    }

    /**
     * 取得服务器本身的IP地址，不管是linux还是windows的都通用，其实就是取eth0的IP地址
     * @return
     */
    public static String getLocalIP() {
        String ip = "";
        try {
            Enumeration<?> e1 = (Enumeration<?>) NetworkInterface.getNetworkInterfaces();
            while (e1.hasMoreElements()) {
                NetworkInterface ni = (NetworkInterface) e1.nextElement();
                if (!ni.getName().equals("eth0")) {
                    continue;
                } else {
                    Enumeration<?> e2 = ni.getInetAddresses();
                    while (e2.hasMoreElements()) {
                        InetAddress ia = (InetAddress) e2.nextElement();
                        if (ia instanceof Inet6Address)
                            continue;
                        ip = ia.getHostAddress();
                    }
                    break;
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
            System.exit(-1);
        }
        return ip;
    }

}
