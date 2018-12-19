package com.banger.mobile.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.TimeZone;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.i18n.LocaleContextHolder;

import com.banger.mobile.constants.Constants;




/**
 * Date Utility Class used to convert Strings to Dates and Timestamps
 *
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 *  Modified by <a href="mailto:dan@getrolling.com">Dan Kibler </a>
 *  to correct time pattern. Minutes should be mm not MM (MM is month).
 */
public class DateUtil {
    private static Log          log          = LogFactory.getLog(DateUtil.class);
    private static final String TIME_PATTERN = "HH:mm";

    /**
     * Checkstyle rule: utility classes should not have public constructor
     */
    public DateUtil() {
    }

    //Timestamp和String之间转换的函数：
    public static String getTimestampToString(Timestamp obj) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//定义格式，不显示毫秒
        String str = df.format(obj);
        return str;
    }
    
    //Date和String之间转换的函数：
    public static String getDateToString(Date obj) {
    	if (obj==null)  return "1981-10-27";
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//定义格式，不显示毫秒
        String str = df.format(obj);
        return str;
    }

    //Date和String之间转换的函数：
    public static String getCNDateToString(Date obj) {
        if (obj==null) obj = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy年MM月dd日");//定义格式，不显示毫秒
        String str = df.format(obj);
        return str;
    }

    /*
     * 自定义 转换模式将Timestamp 输出
     */
    public static String getTimestampToString(String formatPattern, Timestamp obj) {
        SimpleDateFormat df = new SimpleDateFormat(formatPattern);
        String str = df.format(obj);
        return str;
    }

    //String转化为Timestamp:
    public static Timestamp getStringToTimestamp(String str) {
        Timestamp ts = Timestamp.valueOf(str);
        return ts;
    }

    public static Date strToDate(String str, String pattern) {
        Date dateTemp = null;
        SimpleDateFormat formater2 = new SimpleDateFormat(pattern);
        try {
            dateTemp = formater2.parse(str);
        } catch (Exception e) {
            log.error("exception in convert string to date!", e);
        }
        return dateTemp;
    }

    /**
     * Return default datePattern (MM/dd/yyyy)
     * @return a string representing the date pattern on the UI
     */
    public static String getDatePattern() {
        Locale locale = LocaleContextHolder.getLocale();
        String defaultDatePattern;
        try {
            defaultDatePattern = ResourceBundle.getBundle(Constants.BUNDLE_KEY, locale).getString(
                "date.format");
        } catch (MissingResourceException mse) {
            defaultDatePattern = "MM/dd/yyyy";
        }

        return defaultDatePattern;
    }

    public static String getDateTimePattern() {
        return DateUtil.getDatePattern() + " HH:mm:ss.S";
    }

    /**
     * This method attempts to convert an Oracle-formatted date
     * in the form dd-MMM-yyyy to mm/dd/yyyy.
     *
     * @param aDate date from database as a string
     * @return formatted string for the ui
     */
    public static String getDate(Date aDate) {
        SimpleDateFormat df;
        String returnValue = "";

        if (aDate != null) {
            df = new SimpleDateFormat(getDatePattern());
            returnValue = df.format(aDate);
        }

        return (returnValue);
    }

    /**
     * This method generates a string representation of a date/time
     * in the format you specify on input
     *
     * @param aMask the date pattern the string is in
     * @param strDate a string representation of a date
     * @return a converted Date object
     * @see java.text.SimpleDateFormat
     * @throws ParseException when String doesn't match the expected format
     */
    public static Date convertStringToDate(String aMask, String strDate) throws ParseException {
        SimpleDateFormat df;
        Date date;
        df = new SimpleDateFormat(aMask);

        if (log.isDebugEnabled()) {
            log.debug("converting '" + strDate + "' to date with mask '" + aMask + "'");
        }

        try {
            date = df.parse(strDate);
        } catch (ParseException pe) {
            //log.error("ParseException: " + pe);
            throw new ParseException(pe.getMessage(), pe.getErrorOffset());
        }

        return (date);
    }

    /**
     * This method returns the current date time in the format:
     * MM/dd/yyyy HH:MM a
     *
     * @param theTime the current time
     * @return the current date/time
     */
    public static String getTimeNow(Date theTime) {
        return getDateTime(TIME_PATTERN, theTime);
    }

    /**
     * This method returns the current date in the format: MM/dd/yyyy
     *
     * @return the current date
     * @throws ParseException when String doesn't match the expected format
     */
    public static Calendar getToday() throws ParseException {
        Date today = new Date();
        SimpleDateFormat df = new SimpleDateFormat(getDatePattern());

        // This seems like quite a hack (date -> string -> date),
        // but it works ;-)
        String todayAsString = df.format(today);
        Calendar cal = new GregorianCalendar();
        cal.setTime(convertStringToDate(todayAsString));

        return cal;
    }

    public static Calendar getCurrentDay() throws ParseException {
        Calendar cal = Calendar.getInstance();
        return cal;

    }

    /**
     * This method generates a string representation of a date's date/time
     * in the format you specify on input
     *
     * @param aMask the date pattern the string is in
     * @param aDate a date object
     * @return a formatted string representation of the date
     *
     * @see java.text.SimpleDateFormat
     */
    public static String getDateTime(String aMask, Date aDate) {
        SimpleDateFormat df = null;
        String returnValue = "";

        if (aDate == null) {
            log.error("aDate is null!");
        } else {
            df = new SimpleDateFormat(aMask);
            returnValue = df.format(aDate);
        }

        return (returnValue);
    }

    /**
     * This method generates a string representation of a date based
     * on the System Property 'dateFormat'
     * in the format you specify on input
     *
     * @param aDate A date to convert
     * @return a string representation of the date
     */
    public static String convertDateToString(Date aDate) {
        return getDateTime(getDatePattern(), aDate);
    }

    /**
     * This method converts a String to a date using the datePattern
     *
     * @param strDate the date to convert (in format MM/dd/yyyy)
     * @return a date object
     * @throws ParseException when String doesn't match the expected format
     */
    public static Date convertStringToDate(String strDate) throws ParseException {
        Date aDate = null;

        try {
            if (log.isDebugEnabled()) {
                log.debug("converting date with pattern: " + getDatePattern());
            }

            aDate = convertStringToDate(getDatePattern(), strDate);
        } catch (ParseException pe) {
            log.error("Could not convert '" + strDate + "' to a date, throwing exception");
            log.error(pe);
            throw new ParseException(pe.getMessage(), pe.getErrorOffset());
        }

        return aDate;
    }

    /**
     *
     * @param aDate
     * @return
     */
    public static String convertDateToString(String pattern, Date aDate) {
        return getDateTime(pattern, aDate);
    }

    /**
     * 取得从startDate开始的前(正)/后(负)day天
     * @param startDate
     * @param day
     * @return
     */
    public static Date getRelativeDate(Date startDate, int day) {
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(startDate);
            calendar.add(Calendar.DAY_OF_MONTH, day);
            return calendar.getTime();
        } catch (Exception e) {
            log.error(e);
            return startDate;
        }
    }

    /**
     * 请注意这个方法,它增加或者减少的日期是相对new Date()来说的，而不是相对startDate;为了避免风险，这里不做修改
     * @param startdate
     * @param days
     * @return
     */
    @Deprecated
    public static Date getDate(Date startdate, int days) {
        Date dateresult = startdate;
        try {
            GregorianCalendar cal = new GregorianCalendar();

            cal.setTime(new Date());
            cal.add(GregorianCalendar.DAY_OF_MONTH, days);
            dateresult = cal.getTime();
        } catch (Exception e) {
            log.error(e);
        }
        return dateresult;
    }
    
    public static Date addTime(Date d,int hour,int minute,int second)
	{
		Calendar cal = Calendar.getInstance(); 
		cal.setTime(d);
		cal.add(Calendar.HOUR,hour);
		cal.add(Calendar.MINUTE,minute);
		cal.add(Calendar.SECOND,second);cal.getTimeInMillis();
		return cal.getTime();
	}
	
	public static Date addDay(Date d,int day)
	{
		Calendar cal = Calendar.getInstance(); 
		cal.setTime(d);
		cal.add(Calendar.DATE,day);
		return cal.getTime();
	}
	
	public static Date addMonth(Date d,int month)
	{
		Calendar cal = Calendar.getInstance(); 
		cal.setTime(d);
		cal.add(Calendar.MONTH,month);
		return cal.getTime();
	}
	
	public static Date addYear(Date d,int year)
	{
		Calendar cal = Calendar.getInstance(); 
		cal.setTime(d);
		cal.add(Calendar.YEAR,year);
		return cal.getTime();
	}

    /**
     * 根据日期获取星期几
     *
     * @param date java.util.Date对象,不能为null
     * @return
     */
    public static int getDay(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.DAY_OF_WEEK) - 1;
    }

    /**
     * 统计两个时间差，返回的是天数(即24小时算一天，少于24小时就为0，用这个的时候最好把小时、分钟等去掉)
     * @param begin 开始时间
     * @param end
     * @return
     */
    public static int countDays(String beginStr, String endStr, String Foramt) {
        Date end = strToDate(endStr, Foramt);
        Date begin = strToDate(beginStr, Foramt);
        long times = end.getTime() - begin.getTime();
        return (int) (times / 60 / 60 / 1000 / 24);
    }
    
    public static int countDays(Date begin, Date end) {
        DateFormat df=new SimpleDateFormat("yyyy-MM-dd");
        long times = end.getTime() - begin.getTime();
        return (int) (times / 60 / 60 / 1000 / 24);
    }
    
    public static long countDays(String beginDateStr,String endDateStr)
    {
        long day=0;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");    
        Date beginDate;
        Date endDate;
        try{
            beginDate = format.parse(beginDateStr);
            endDate= format.parse(endDateStr);    
            day=(endDate.getTime()-beginDate.getTime())/(24*60*60*1000);    
        } catch (ParseException e){
            e.printStackTrace();
        }   
        return day;
    }

//        public static void main(String[] args) {
//            System.out.println(countDays("2009-08-30", "2009-08-30"));
//        }
    
    /**
     * 获取系统时间
     * result = 1 系统时间在参数时间之后
     * result = 0 相等
     * result = -1系统时间在参数时间之前
     */
    public static Integer getSystemDate(String date){
        int result=0;
        try {
            DateFormat df=new SimpleDateFormat("yyyy-MM-dd");
            Calendar systemDate=Calendar.getInstance();
            Calendar execDate=Calendar.getInstance();
            systemDate.setTime(df.parse(getDateToString(new Date())));
            execDate.setTime(df.parse(date));
            result=systemDate.compareTo(execDate);
        } catch (Exception e) {}
        return result;
    }
    
    /**
     * 格式化对像的日期值
     * @param obj
     */
    public static void format(Object obj)
    {
    	PropertyInfo[] pis = ReflectorUtil.getProperties(obj.getClass());
    	for(PropertyInfo pi : pis)
    	{
    		Object val = ReflectorUtil.getPropertyValue(obj,pi.getGetMethod());
    		if(val instanceof Date)
    		{
    			ReflectorUtil.setPropertyValue(obj, pi.getSetMethod(),new DateWrapper(((Date)val).getTime()));
    		}
    	}
    }
    
    /**
     * 前端获取服务器系统时间(校验)
     */
    public static String getSystemTimes(){
        return getDateToString(new Date());
    }
    
    /**
     * 返回日期字符换 yyyyMMddHmsS
     * 年月日时分秒毫秒
     */
    public static String getTimeString(){
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHmsS");
        return format.format(new Date());
    }
    
    public static String getNowTimeString(){
    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        TimeZone timeZoneChina = TimeZone.getTimeZone("Asia/Shanghai");
        format.setTimeZone(timeZoneChina);
        String timeStr = format.format(Calendar.getInstance().getTime());
        return timeStr;
    }
    
    /**
     * 转换文件大小
     * @param size
     * @return
     */
    public static String FormetFileSize(String size) {
        DecimalFormat df = new DecimalFormat("#.00");
        String fileSizeString = "";
        if(!StringUtil.isBlank(size)&&StringUtil.isNumeric(size)) {
            int fileS = Integer.parseInt(size);
            if (fileS < 1024) {
                fileSizeString = df.format((double) fileS) + "B";
            } else if (fileS < 1048576) {
                fileSizeString = df.format((double) fileS / 1024) + "Kb";
            } else if (fileS < 1073741824) {
                fileSizeString = df.format((double) fileS / 1048576) + "MB";
            } else {
                fileSizeString = df.format((double) fileS / 1073741824) + "G";
            }
        }
        return fileSizeString;
    }
    
    /**系统桌面用
     * 获得系统当前时间和星期
     */
    public static String getNowTimeAndWeek(){
        Calendar ca = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy年MM月dd日");
        String[] week = {"星期日","星期一","星期二","星期三","星期四","星期五","星期六"};
        int i = ca.get(Calendar.DAY_OF_WEEK);
        return  df.format(ca.getTime())+"     "+ week[i-1];
    }
    
    /**
     * 验证是否执行中
     */
    public static Integer getExecutionDate(String startDate,String endDate){
        int resultStart = 0;
        int resultEnd = 0;
        int result=0;
        try {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            Calendar systemDate = Calendar.getInstance();
            Calendar sDate = Calendar.getInstance();
            systemDate.setTime(df.parse(getDateToString(new Date())));//系统时间
            sDate.setTime(df.parse(startDate));
            resultStart = systemDate.compareTo(sDate);
            Calendar eDate = Calendar.getInstance();
            eDate.setTime(df.parse(endDate));
            resultEnd = systemDate.compareTo(eDate);
            if(resultStart==-1){
                result=2;
            }else{
                if(resultStart==1&&resultEnd!=1){
                    result=1;
                }
            }
        } catch (Exception e) {}
        return result;
    }
    
    /**
     * 获取当前日期的前(正)/后(负)month月
     * @param n 
     * @return 
     */
    public static String getAfterNMonth(int n){
        String m="";
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.MONTH, n); //得到前一个月
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH) + 1;
            int day=calendar.get(calendar.DATE);
            m=dateFormat.format(dateFormat.parse(year+"-"+month+"-"+day));
        } catch (ParseException e) {}
        return m;
    }
    
   /**
    * 时长转换成HH:mm:ss格式
    * @param Integer callTime
    * @return String
    */
   public static String formatCallTime(Integer callTime) {
       String H = "" + callTime / 3600;
       if (H.length() < 2) H = "0" + H;callTime = callTime % 3600;
       String M = "" + callTime / 60;
       if (M.length() < 2) M = "0" + M;callTime = callTime % 60;
       String S = "" + callTime;
       if (S.length() < 2) S = "0" + S;
       return H + ":" + M + ":" + S;
   }
   
   /**
    * 返回日期字符换 yyyyMMddHHmmssSSS
    * 年月日时分秒毫秒
    */
   public static String getNowTimeToString(){
       SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSSS");
       TimeZone timeZoneChina = TimeZone.getTimeZone("Asia/Shanghai");
       format.setTimeZone(timeZoneChina);
       String timeStr = format.format(Calendar.getInstance().getTime());
       return timeStr;
   }

   /**
    * 转换文件大小
    * @param size
    * @return
    */
   public static String PadFormetFileSize(String size) {
       DecimalFormat df = new DecimalFormat("#.00");
       String fileSizeString = "";
       if(StringUtil.isBlank(size)){
           fileSizeString="0B";
       }else{
           double fileS = Double.parseDouble(size); 
           if (fileS < 1024) {
               fileSizeString = df.format(fileS) + "B";
           } else if (fileS < 1048576) {
               fileSizeString = df.format(fileS / 1024) + "Kb";
           } else if (fileS < 1073741824) {
               fileSizeString = df.format(fileS / 1048576) + "MB";
           } else {
               fileSizeString = df.format(fileS / 1073741824) + "G";
           }
       }
       return fileSizeString;
   }
   
   /**
    * 时间比较
    * result = 0 相等
    */
   public static Integer DateComparison(String date,String loginDate){
       int result=0;
       try {
           DateFormat df=new SimpleDateFormat("yyyy-MM-dd");
           Calendar systemDate=Calendar.getInstance();
           Calendar execDate=Calendar.getInstance();
           systemDate.setTime(df.parse(loginDate));
           execDate.setTime(df.parse(date));
           result=systemDate.compareTo(execDate);
       } catch (Exception e) {}
       return result;
   }
   
   /**
    * 获取当前时间前一天
    * @param date
    * @return
    */
   public static Date BeforeDay(Date date){
	    Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		date = calendar.getTime();
	    return date;
   }
   
}
