/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :cheny
 * Create Date:2012-12-29
 */
package com.banger.mobile.facade.pdtProduct;

import java.util.Map;

import com.banger.mobile.domain.model.customer.CounterOutPintMessage;

/**
 * @author cheny
 * @version $Id: ProductImportService.java,v 0.1 2012-12-29 下午5:09:11 cheny Exp $
 */
public interface ProductImportService {

    public CounterOutPintMessage execlToDataBase(Map<String, String> map);
}
