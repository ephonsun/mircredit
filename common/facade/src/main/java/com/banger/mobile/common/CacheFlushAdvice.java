/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :缓存更新通短接口
 * Author     :zsw
 * Create Date:2013-03-29
 */

package com.banger.mobile.common;


public interface CacheFlushAdvice {
	/**
	 * 更新缓存
	 * @param group 缓存对应的组名
	 */
	void flushCache(String group);
}
