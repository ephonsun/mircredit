package com.banger.mobile.domain.model.report;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class RecordActivityTrendReportBean {
	private String deptIds;				//机构Ids
	private String deptNames;			//机构名称
	private String userIds;				//用户Ids
	private String userNames;			//用户名称
	private String belongTo;			//归属
	private String dateType;			//查询日期类型
	private Integer yearBegin;			//开始年份
	private Integer yearEnd;				//结束年份
	private Integer monthBegin;			//开始月份
	private Integer monthEnd;			//结束月份
	private Integer quarterBegin;		//开始季度
	private Integer quarterEnd;			//结速季度
	private Integer containSub;			//是否包含下属
	private String text;				//机构或人员名
	
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		try {
			this.text = URLDecoder.decode(text,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	public Integer getContainSub() {
		return containSub;
	}
	public void setContainSub(Integer containSub) {
		this.containSub = containSub;
	}
	public String getDeptNames() {
		return deptNames;
	}
	public void setDeptNames(String deptNames) {
		this.deptNames = deptNames;
	}
	public String getUserNames() {
		return userNames;
	}
	public void setUserNames(String userNames) {
		this.userNames = userNames;
	}
	public String getBelongTo() {
		return belongTo;
	}
	public void setBelongTo(String belongTo) {
		this.belongTo = belongTo;
	}
	public String getDeptIds() {
		return deptIds;
	}
	public void setDeptIds(String deptIds) {
		this.deptIds = deptIds;
	}
	public String getUserIds() {
		return userIds;
	}
	public void setUserIds(String userIds) {
		this.userIds = userIds;
	}
	public String getDateType() {
		return dateType;
	}
	public void setDateType(String dateType) {
		this.dateType = dateType;
	}
	
	public Integer getYearBegin() {
		return yearBegin;
	}
	public void setYearBegin(Integer yearBegin) {
		this.yearBegin = yearBegin;
	}
	public Integer getYearEnd() {
		return yearEnd;
	}
	public void setYearEnd(Integer yearEnd) {
		this.yearEnd = yearEnd;
	}
	public Integer getMonthBegin() {
		return monthBegin;
	}
	public void setMonthBegin(Integer monthBegin) {
		this.monthBegin = monthBegin;
	}
	public Integer getMonthEnd() {
		return monthEnd;
	}
	public void setMonthEnd(Integer monthEnd) {
		this.monthEnd = monthEnd;
	}
	public Integer getQuarterBegin() {
		return quarterBegin;
	}
	public void setQuarterBegin(Integer quarterBegin) {
		this.quarterBegin = quarterBegin;
	}
	public Integer getQuarterEnd() {
		return quarterEnd;
	}
	public void setQuarterEnd(Integer quarterEnd) {
		this.quarterEnd = quarterEnd;
	}
	
	/**
	 * 得到开始时间
	 * @return
	 */
	public String getDateBegin() {
		String year = this.padLeft(this.getYearBegin().toString(),4,'0');
		String month ="";
		if(this.dateType.equalsIgnoreCase("year"))
		{
			month = "01";
		}
		else if(this.dateType.equalsIgnoreCase("quarter"))
		{
			int m = this.quarterBegin.intValue()*3-2;
			month = this.padLeft(String.valueOf(m),2,'0');
		}
		else {
			month = this.padLeft(this.monthBegin.toString(),2,'0');
		}
		return year+month;
	}
	
	/**
	 * 得到结束时间
	 * @return
	 */
	public String getDateEnd() {
		String year = this.padLeft(this.getYearEnd().toString(),4,'0');
		String month ="";
		if(this.dateType.equalsIgnoreCase("year"))
		{
			month = "12";
		}
		else if(this.dateType.equalsIgnoreCase("quarter"))
		{
			int m = this.quarterEnd.intValue()*3;
			month = this.padLeft(String.valueOf(m),2,'0');
		}
		else {
			month = this.padLeft(this.monthEnd.toString(),2,'0');
		}
		return year+month;
	}
	
	/**
     * 字符串左边填补字符
     * @param str
     * @param totalLength 字符串长度
     * @param c 填补的字符
     * @return
     */
    private String padLeft(String str,int totalLength,char c)
    {
        String str1 = str;
        int bit=totalLength;
        int n = str1.length();
        if (n < bit)
        {
            int m = bit - n;
            for (int i = 0; i < m; i++)
            {
                str1 =  String.valueOf(c)+ str1;
            }
            return str1;
        }
        else return str;
    }
}
