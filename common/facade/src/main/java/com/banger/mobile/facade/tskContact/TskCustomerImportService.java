/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :cheny
 * Create Date:2012-11-26
 */
package com.banger.mobile.facade.tskContact;

import java.util.Map;

import com.banger.mobile.domain.model.customer.CounterOutPintMessage;

/**
 * @author cheny
 * @version $Id: TskCustomerImportService.java,v 0.1 2012-11-26 上午9:09:32 cheny Exp $
 */
public interface TskCustomerImportService {

    /**
     * 客户导入
     * @param filePath
     * @param map
     * @return
     */
    public CounterOutPintMessage execlToDataBase(Map<String, String> map,String type);
}
