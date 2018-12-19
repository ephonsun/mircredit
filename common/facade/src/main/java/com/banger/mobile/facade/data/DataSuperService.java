/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :资料业务接口
 * Author     :yuanme
 * Version    :1.0
 * Create Date:May 3, 2012
 */
package com.banger.mobile.facade.data;

import java.util.List;
import java.util.Map;

import com.banger.mobile.Page;
import com.banger.mobile.domain.model.data.CustomerData;
import com.banger.mobile.domain.model.data.Event;
import com.banger.mobile.domain.model.data.Video;

/**
 * @author yuanme
 * @version $Id: CustomerDataService.java,v 0.1 2012-5-24 下午04:42:52 yuanme Exp $
 */
public interface DataSuperService {
   
    
    /**
     * 查找该客户数据所属的客户名字
     * @param customerDataId
     * @return 顾客名字
     */
 	public String getCustomerNameByDataId(int customerDataId);
 	
}
