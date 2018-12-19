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
import com.banger.mobile.dao.data.DataMmsDao;
import com.banger.mobile.domain.model.data.Audio;
import com.banger.mobile.domain.model.data.Mms;
import com.banger.mobile.domain.model.data.SearchData;
import com.banger.mobile.facade.data.DataMmsService;
import com.banger.mobile.util.StringUtil;

/**
 * @author yuanme
 * @version $Id: CustomerDataServiceImpl.java,v 0.1 2012-11-14 下午5:27:03 Administrator Exp $
 */
public class DataMmsServiceImpl extends DataSuperServiceImpl implements DataMmsService{
    private DataMmsDao dataMmsDao;
    SimpleDateFormat df0=new SimpleDateFormat("yyyy-MM-dd HH:mm:00");
    SimpleDateFormat df1=new SimpleDateFormat("yyyy-MM-dd HH:mm:59");
    public void setDataMmsDao(DataMmsDao dataMmsDao) {
		this.dataMmsDao = dataMmsDao;
	}
	  
	/**
     * 根据客户查看彩信列表（分页）
     * @param parameterMap
     * @param page
     * @return 
     */
    public PageUtil<Mms> getCustomerMmsDataPage(Map<String, Object> parameterMap, Page page,SearchData searchData){
        if(searchData!=null){
            parameterMap.put("mmsContent",  StringUtil.ReplaceSQLChar(searchData.getMmsContent().trim()));
            parameterMap.put("mmsType", searchData.getMmsType());
            if(searchData.getSendStartDate()!=null)parameterMap.put("sendStartDate", df0.format(searchData.getSendStartDate()));
            if(searchData.getSendEndDate()!=null)parameterMap.put("sendEndDate", df1.format(searchData.getSendEndDate()));
        }
    	return dataMmsDao.getCustomerMmsDataPage(parameterMap, page);
    }


}
