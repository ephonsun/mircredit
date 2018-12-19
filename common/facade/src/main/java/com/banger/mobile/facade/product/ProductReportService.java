package com.banger.mobile.facade.product;

import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.banger.mobile.domain.model.product.ProductBean;
import com.banger.mobile.domain.model.product.ReportProductBean;
import com.banger.mobile.domain.model.product.ReportProductCountBean;

public interface ProductReportService {

    /**
     * 产品销售明细统计
     */
    public List<ReportProductBean> getReportProduct(Map<String, Object> map);

    /**
     * 导出报表
     * @param list
     * @param productBean
     * @param userName
     * @param MoneyUnit
     * @return
     */
    public HSSFWorkbook exportProductReportExcel(List<ReportProductBean> list,ProductBean productBean, String userName,String MoneyUnit) ;
    
    /**
     * 产品销售统计报表 跟人
     */
    public List<ReportProductCountBean> getReportProductCountByPeople(Map<String, Object> map);
    
    /**
     * 产品销售统计报表 跟机构
     */
    public List<ReportProductCountBean> getReportProductCountByDept(Map<String, Object> map);
    
    /**
     * 购买产品统计 报表导出
     * 数据表转化为Excel格式
     * @param list
     * @param userName
     * @return
     */
    public HSSFWorkbook exportProductReportCountExcel(List<ReportProductCountBean> list,String productName, String userName,String isdept,String MoneyUnit);
}