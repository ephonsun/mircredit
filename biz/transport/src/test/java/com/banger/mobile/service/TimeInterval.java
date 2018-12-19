package com.banger.mobile.service;

public class TimeInterval {
	private long timeStart;
	private long timeBefore;
	private long timeNow;
	
	public TimeInterval(){
		timeBefore = System.currentTimeMillis();
		timeStart = timeBefore;
	}
	
	public long getInterval(){
		timeNow = System.currentTimeMillis();
		long interval = timeNow - timeBefore;
		timeBefore = timeNow;
		return interval;
	}
	
	public long getAllInterval(){
		timeNow = System.currentTimeMillis();
		long interval = timeNow - timeStart;
		return interval;
	}
}
