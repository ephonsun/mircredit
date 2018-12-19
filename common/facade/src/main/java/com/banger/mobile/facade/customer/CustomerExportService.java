/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :客户导出...
 * Author     :cheny
 * Create Date:2012-8-24
 */
package com.banger.mobile.facade.customer;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.banger.mobile.domain.model.customer.CrmExportBean;
import com.banger.mobile.domain.model.customer.CustomerExportContext;



/**
 * @author cheny
 * @version $Id: CustomerExportService.java,v 0.1 2012-8-24 下午7:44:27 cheny Exp $
 */
public interface CustomerExportService {
	/**
	 * 导出基础字段
	 * @return
	 */
    public Map<String, String> initParameter();
    /**
     * 创建文件
     * @param request
     * @return
     */
    public File createFile(HttpServletRequest request);
    /**
     * 新增行
     * @param ctx
     * @param book
     * @param customers
     */
    public void insertRow(CustomerExportContext ctx,HSSFWorkbook book,List<CrmExportBean> customers);
    /**
     * 获得客户导出内容
     * @return
     */
    public CustomerExportContext getContext();
    
}
