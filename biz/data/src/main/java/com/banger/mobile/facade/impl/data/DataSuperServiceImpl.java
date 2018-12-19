/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :yuanme
 * Create Date:2012-11-14
 */
package com.banger.mobile.facade.impl.data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.banger.mobile.Page;
import com.banger.mobile.dao.data.CustomerDataDao;
import com.banger.mobile.dao.data.DataSuperDao;
import com.banger.mobile.dao.data.DataVideoDao;
import com.banger.mobile.domain.model.data.CustomerData;
import com.banger.mobile.domain.model.data.Event;
import com.banger.mobile.domain.model.data.Video;
import com.banger.mobile.facade.data.DataSuperService;
import com.banger.mobile.facade.data.DataVideoService;

/**
 * @author yuanme
 * @version $Id: CustomerDataServiceImpl.java,v 0.1 2012-11-14 下午5:27:03 Administrator Exp $
 */
public class DataSuperServiceImpl implements DataSuperService {
    private DataSuperDao dataSuperDao;
    
    public void setDataSuperDao(DataSuperDao dataSuperDao) {
		this.dataSuperDao = dataSuperDao;
	}
	
   
	/**
     * 根据客户资料Id 获得客户姓名
     * @param customerDataId
     * @return
     */
	public String getCustomerNameByDataId(int customerDataId) {
		
		return dataSuperDao.getCustomerNameByDataId(customerDataId);
	}

}
