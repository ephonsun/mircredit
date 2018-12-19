/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :录音导出...
 * Author     :cheny
 * Create Date:2012-9-11
 */
package com.banger.mobile.facade.record;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.banger.mobile.domain.model.customer.CustomerExportContext;
import com.banger.mobile.domain.model.record.RecordExportBean;

/**
 * @author cheny
 * @version $Id: RecordExportService.java,v 0.1 2012-9-11 下午12:53:10 cheny Exp $
 */
public interface RecordExportService {
    /**
     * 导出基础字段
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
    public void insertRow(CustomerExportContext ctx,HSSFWorkbook book,int type,List<RecordExportBean> customers);
    
}
