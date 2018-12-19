/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :产品报表service接口
 * Author     :liyb
 * Create Date:2013-1-5
 */
package com.banger.mobile.facade.report;

import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.banger.mobile.domain.model.report.ProductReportBean;
import com.banger.mobile.domain.model.report.ProductTypeTotalBean;
import com.banger.mobile.domain.model.report.ProductTypeTreeBean;

import net.sf.json.JSONArray;

/**
 * @author liyb
 * @version $Id: ProductReportService.java,v 0.1 2013-1-5 下午01:06:58 liyb Exp $
 */
public interface PdtProductReportService {

    /**
     * 返回产品类型树JSON数组
     * @param map
     * @return
     */
    public JSONArray getProductTypeTreeJson(Map<String,Object> map);
    
    /**
     * 产品报表列表
     * @param parames
     * @return
     */
    public List<ProductReportBean> getProductReportList(Map<String,Object> parames);
    
    /**
     * 查询有购买产品记录的用户以及有营销产品的用户
     * @param parames
     * @return
     */
    public List<ProductTypeTreeBean> getProductUserList(Map<String,Object> parames,List<ProductTypeTotalBean> typeList,List<ProductTypeTotalBean> subTypeList);
    
    /**
     * 查询用户的产品大类的销售额
     * @param parames
     * @return
     */
    public List<ProductTypeTotalBean> getProductTypeTotalList(Map<String,Object> parames);
    
    /**
     * 查询用户的产品子类型的销售额
     * @param parames
     * @return
     */
    public List<ProductTypeTotalBean> getProductSubTypeTotalList(Map<String,Object> parames);
    
    /**
     * 产品销售数据表转化为Excel格式
     * @param typeList 大类型
     * @param subTypeList 子类型
     * @param userList 用户/机构
     * @param productList 产品信息
     * @param userName 用户名
     * @return
     */
    public HSSFWorkbook exportProductReportExcel(List<ProductTypeTotalBean> typeList,List<ProductTypeTotalBean> subTypeList
                                                 ,List<ProductTypeTreeBean> userList,List<ProductReportBean> productList,String userName);

}
