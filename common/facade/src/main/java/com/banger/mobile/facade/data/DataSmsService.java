/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :资料业务接口
 * Author     :yuanme
 * Version    :1.0
 * Create Date:May 3, 2012
 */
package com.banger.mobile.facade.data;

import java.util.Map;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.model.data.Audio;
import com.banger.mobile.domain.model.data.SearchData;
import com.banger.mobile.domain.model.data.Sms;

/**
 * @author yuanme
 * @version $Id: CustomerDataService.java,v 0.1 2012-5-24 下午04:42:52 yuanme Exp $
 */
public interface DataSmsService extends DataSuperService{
   
	/**
     * 根据客户查看短信列表（分页）
     * @param parameterMap
     * @param page
     * @return 
     */
    public PageUtil<Sms> getCustomerSmsDataPage(Map<String, Object> parameterMap, Page page,SearchData searchData);
}
