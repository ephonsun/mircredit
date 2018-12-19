package com.banger.mobile.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeWrapper extends java.util.Date {
	private static final long serialVersionUID = 7447060536861924730L;
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
	
	public TimeWrapper(long date)
	{
		super(date);
	}
	
	public TimeWrapper()
	{
		super(new Date().getTime());
	}
	
	@Override
	public String toString()
	{
		return sdf.format(this);
	}
	
	public String toString(String formator)
	{
		SimpleDateFormat sdf = new SimpleDateFormat(formator);
		return sdf.format(this);
	}
}
