/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :购买客户导入业务...
 * Author     :yangy
 * Create Date:2012-8-24
 */
package com.banger.mobile.facade.pdtProduct;

import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFRow;

import com.banger.mobile.domain.model.customer.CounterOutPintMessage;

/**
 * @author yangyang
 * @version $Id: ProductImportService.java,v 0.1 2012-8-24 下午1:35:02 yangyong Exp $
 */
public interface BuyProductImportService {

	/**
	 * 购买客户导入核心程序
	 * @param filePath
	 * @param InChargeOfDeptUserIds
	 * @param baseColumn
	 * @return
	 */
    public CounterOutPintMessage execlToDataBase(String filePath, Integer[] InChargeOfDeptUserIds,
                                                 Map<String, String> baseColumn);

}
