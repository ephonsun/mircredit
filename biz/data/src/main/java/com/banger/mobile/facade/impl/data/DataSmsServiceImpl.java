/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :yuanme
 * Create Date:2012-11-14
 */
package com.banger.mobile.facade.impl.data;

import java.text.SimpleDateFormat;
import java.util.Map;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.dao.data.DataSmsDao;
import com.banger.mobile.domain.model.data.Audio;
import com.banger.mobile.domain.model.data.SearchData;
import com.banger.mobile.domain.model.data.Sms;
import com.banger.mobile.facade.data.DataSmsService;
import com.banger.mobile.util.StringUtil;

/**
 * @author yuanme
 * @version $Id: CustomerDataServiceImpl.java,v 0.1 2012-11-14 下午5:27:03 Administrator Exp $
 */
public class DataSmsServiceImpl extends DataSuperServiceImpl implements DataSmsService{
    private DataSmsDao dataSmsDao;
    SimpleDateFormat df0=new SimpleDateFormat("yyyy-MM-dd HH:mm:00");
    SimpleDateFormat df1=new SimpleDateFormat("yyyy-MM-dd HH:mm:59");
    public void setDataSmsDao(DataSmsDao dataSmsDao) {
		this.dataSmsDao = dataSmsDao;
	}
	  
	/**
     * 根据客户查看短信列表（分页）
     * @param parameterMap
     * @param page
     * @return 
     */
    public PageUtil<Sms> getCustomerSmsDataPage(Map<String, Object> parameterMap, Page page,SearchData searchData){
        if(searchData!=null){
            parameterMap.put("smsContent", StringUtil.ReplaceSQLChar(searchData.getSmsContent().trim()));
            parameterMap.put("smsType", searchData.getSmsType());
            if(searchData.getSendStartDate()!=null)parameterMap.put("sendStartDate", df0.format(searchData.getSendStartDate()));
            if(searchData.getSendEndDate()!=null)parameterMap.put("sendEndDate", df1.format(searchData.getSendEndDate()));
        }
    	return dataSmsDao.getCustomerSmsDataPage(parameterMap, page);
    }


}
