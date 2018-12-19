/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :缓存更新监听类
 * Author     :zsw
 * Create Date:2013-03-29
 */
package com.banger.mobile.webapp.listener;

import java.util.Map;

import com.banger.mobile.common.CacheFlushAdvice;
import com.banger.mobile.util.SpringContextUtil;
import com.opensymphony.oscache.base.events.CacheEntryEvent;
import com.opensymphony.oscache.base.events.CacheEntryEventListener;
import com.opensymphony.oscache.base.events.CacheGroupEvent;
import com.opensymphony.oscache.base.events.CachePatternEvent;
import com.opensymphony.oscache.base.events.CachewideEvent;

public class OSCacheListener implements CacheEntryEventListener {

	public void cacheEntryAdded(CacheEntryEvent cacheentryevent) {
		// TODO Auto-generated method stub
		
	}

	public void cacheEntryFlushed(CacheEntryEvent cacheentryevent) {
		// TODO Auto-generated method stub
		
	}

	public void cacheEntryRemoved(CacheEntryEvent cacheentryevent) {
		// TODO Auto-generated method stub
		
	}

	public void cacheEntryUpdated(CacheEntryEvent cacheentryevent) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * 监听ibatis更新缓存
	 */
	@SuppressWarnings("rawtypes")
	public void cacheGroupFlushed(CacheGroupEvent cachegroupevent) {
		String group = cachegroupevent.getGroup();
		Map map = SpringContextUtil.getApplicationContext().getBeansOfType(CacheFlushAdvice.class);
		for(Object obj : map.values()){
			CacheFlushAdvice advice = (CacheFlushAdvice)obj;
			try{
				advice.flushCache(group);
			}
			catch(Exception e){
				System.out.print(e.toString());
			}
		}
	}

	public void cachePatternFlushed(CachePatternEvent cachepatternevent) {
		// TODO Auto-generated method stub
		
	}

	public void cacheFlushed(CachewideEvent cachewideevent) {
		// TODO Auto-generated method stub
		
	}

}
