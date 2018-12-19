/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :高级搜索条件
 * Author     :zsw
 * Create Date:2012-5-24
 */
package com.banger.mobile.domain.model.customer;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import com.banger.mobile.domain.model.base.customer.BaseCrmCustomer;

/**
 * @author zsw
 */
public class AdvanceQueryBean extends BaseCrmCustomer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1772466297561902391L;
	
	private String tel;				//电话
	private String month;			//生日
	private String day;
	private String monthEnd;		
	private String dayEnd;
	private String ageBegin;
	private String ageEnd;
	private Date lastTime;
	private Date lastTimeEnd;
	
	private Date createDateEnd;				//创建时间
	private Date updateDateEnd;				//更新时间
	
	private String birthdayBegin;
	private String birthdayEnd;
	private String belongTo;	//归属
	private String userIds;			//归属机构Id集合;
	private String deptIds;			//归属用记Id集合
	private String recordId;		//归选提交上来的Id;
	private String shareTo;			//共享类型
	private String loginId;			//登入用户
	
	private String numberRules;		//号码规则
	private String subUserIds;		//勾选用户节点
	private String subDeptIds;		//勾选机构节点
	private String containSub;		//是否包含子机构
	
	private CustomerExtendFieldBean exFields;	//扩展字段
	
	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getShareTo() {
		return shareTo;
	}

	public void setShareTo(String shareTo) {
		this.shareTo = shareTo;
	}

	public CustomerExtendFieldBean getExFields() {
		return exFields;
	}

	public void setExFields(CustomerExtendFieldBean exFields) {
		this.exFields = exFields;
	}

	public String getNumberRules() {
		return numberRules;
	}

	public void setNumberRules(String numberRules) {
		this.numberRules = numberRules;
	}

	public String getBirthQueryType()
	{
		if(birthdayBegin!=null && !birthdayBegin.equals("") && birthdayEnd!=null && !birthdayEnd.equals(""))
		{
			if(birthdayBegin.compareTo(birthdayEnd)>-1)
			{
				return "2";
			}
		}
		return "1";
	}
	
	public String getRecordId() {
		return recordId;
	}
	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}
	public String getBelongTo() {
		return belongTo;
	}
	public void setBelongTo(String belongTo) {
		this.belongTo = belongTo;
	}
	public String getUserIds() {
		return userIds;
	}
	public void setUserIds(String userIds) {
		this.userIds = userIds;
	}
	public String getDeptIds() {
		return deptIds;
	}
	public void setDeptIds(String deptIds) {
		this.deptIds = deptIds;
	}
	public String getBirthdayBegin() {
		return birthdayBegin;
	}
	public void setBirthdayBegin(String birthdayBegin) {
		this.birthdayBegin = birthdayBegin;
	}
	public String getBirthdayEnd() {
		return birthdayEnd;
	}
	public void setBirthdayEnd(String birthdayEnd) {
		this.birthdayEnd = birthdayEnd;
	}
	
	public Date getCreateDateEnd() {
		return createDateEnd;
	}

	public void setCreateDateEnd(Date createDateEnd) {
		this.createDateEnd = createDateEnd;
	}

	public Date getUpdateDateEnd() {
		return updateDateEnd;
	}

	public void setUpdateDateEnd(Date updateDateEnd) {
		this.updateDateEnd = updateDateEnd;
	}

	public String getTel() {
		if(this.numberRules!=null && !"".equals(this.numberRules))			//有号码规则的情况下，返回为空
		{
			return null;
		}
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
		if(month!=null && !"".equals(month))
		{
			if(this.birthdayBegin==null)this.birthdayBegin=padLeft(month,2,'0')+"00";
			else
			{
				this.birthdayBegin=padLeft(month,2,'0')+this.birthdayBegin.substring(2,4);
			}
		}
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
		if(day!=null && !"".equals(day))
		{
			if(this.birthdayBegin==null)this.birthdayBegin="00"+padLeft(day,2,'0');
			else
			{
				this.birthdayBegin=this.birthdayBegin.substring(0,2)+padLeft(day,2,'0');
			}
		}
	}
	public String getMonthEnd() {
		return monthEnd;
	}
	public void setMonthEnd(String monthEnd) {
		this.monthEnd = monthEnd;
		if(monthEnd!=null && !"".equals(monthEnd))
		{
			if(this.birthdayEnd==null)this.birthdayEnd=padLeft(monthEnd,2,'0')+"00";
			else
			{
				this.birthdayEnd=padLeft(monthEnd,2,'0')+this.birthdayEnd.substring(2,4);
			}
		}
	}
	public String getDayEnd() {
		return dayEnd;
	}
	
	public void setDayEnd(String dayEnd) {
		this.dayEnd = dayEnd;
		if(dayEnd!=null && !"".equals(dayEnd))
		{
			if(this.birthdayEnd==null)this.birthdayEnd="00"+padLeft(dayEnd,2,'0');
			else
			{
				this.birthdayEnd=this.birthdayEnd.substring(0,2)+padLeft(dayEnd,2,'0');
			}
		}
	}
	
	public String getAgeBegin() {
		if(ageBegin!=null && !"".equals(ageBegin))
		{
			if(ageBegin.length()>3)
			{
				int age = Integer.parseInt(ageBegin);
				Calendar c = Calendar.getInstance();
				c.setTime(new Date());
				int year = c.get(Calendar.YEAR);
				return new Integer(year-age).toString();
			}
			else
			{
				return ageBegin;
			}
		}
		return ageBegin;
	}
	
	public void setAgeBegin(String ageBegin) {
		this.ageBegin = ageBegin;
	}
	
	public String getAgeEnd() {
		if(ageEnd!=null && !"".equals(ageEnd))
		{
			if(ageEnd.length()>3)
			{
				int age = Integer.parseInt(ageEnd);
				Calendar c = Calendar.getInstance();
				c.setTime(new Date());
				int year = c.get(Calendar.YEAR);
				return new Integer(year-age).toString();
			}
			else
			{
				return ageEnd;
			}
		}
		return ageEnd;
	}
	
	public void setAgeEnd(String ageEnd) {
		this.ageEnd = ageEnd;
	}
	public Date getLastTime() {
		return lastTime;
	}
	public void setLastTime(Date lastTime) {
		this.lastTime = lastTime;
	}
	public Date getLastTimeEnd() {
		return lastTimeEnd;
	}
	public void setLastTimeEnd(Date lastTimeEnd) {
		this.lastTimeEnd = lastTimeEnd;
	}
	
	public String getContainSub() {
		return containSub;
	}

	public void setContainSub(String containSub) {
		this.containSub = containSub;
	}
	
	public String getSubDeptIds() {
		return subDeptIds;
	}

	public void setSubDeptIds(String subDeptIds) {
		this.subDeptIds = subDeptIds;
	}
	
	public String getSubUserIds() {
		return subUserIds;
	}

	public void setSubUserIds(String subUserIds) {
		this.subUserIds = subUserIds;
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
