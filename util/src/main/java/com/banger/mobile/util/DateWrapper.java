package com.banger.mobile.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateWrapper extends java.util.Date {
	private static final long serialVersionUID = -5788623012904426038L;
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
	private Calendar c;
	
	public DateWrapper(long date)
	{
		super(date);
	}
	
	@Override
	public String toString()
	{
		return sdf.format(this);
	}
	
	public Calendar getCal()
	{
		if(c==null)
		{
			c = Calendar.getInstance();
			c.setTimeInMillis(this.getTime());
		}
		return c;
	}
	
	@Override
	public int getYear()
	{
		return getCal().get(Calendar.YEAR);
	}
	
	@Override
	public int getMonth()
	{
		return getCal().get(Calendar.MONTH)+1;
	}
	
	@Override
	public int getDay()
	{
		return getCal().get(Calendar.DATE);
	}
	
	public int getHour()
	{
		return getCal().get(Calendar.HOUR);
	}
	
	public int getMinute()
	{
		return getCal().get(Calendar.MINUTE);
	}
	
	public int getSecond()
	{
		return getCal().get(Calendar.SECOND);
	}
	
	public int getMilliSecond()
	{
		return getCal().get(Calendar.MILLISECOND);
	}
	
	public int getTotalMillis()
	{
		return (this.getHour()*3600+this.getMinute()*60+this.getSecond())*1000+this.getMilliSecond();
	}
	
	public int getTotalSecond()
	{
		return this.getHour()*3600+this.getMinute()*60+this.getSecond();
	}
	
	public int getWeek()
	{
		return getCal().get(Calendar.WEDNESDAY);
	}
}
