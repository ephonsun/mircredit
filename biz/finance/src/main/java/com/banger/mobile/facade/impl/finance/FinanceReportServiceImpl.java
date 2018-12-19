/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :财经要点报表service接口实现
 * Author     :liyb
 * Create Date:2012-12-10
 */
package com.banger.mobile.facade.impl.finance;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.hssf.util.Region;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.dao.report.FinanceReportDao;
import com.banger.mobile.dao.user.SysUserDao;
import com.banger.mobile.domain.Enum.finance.EnumFinance;
import com.banger.mobile.domain.model.base.finance.BaseFeArticle;
import com.banger.mobile.domain.model.finance.FeArticle;
import com.banger.mobile.domain.model.report.FinanceReportBean;
import com.banger.mobile.domain.model.report.UserRelationReportBean;
import com.banger.mobile.domain.model.user.SysUserBean;
import com.banger.mobile.facade.report.FinanceReportService;
import com.banger.mobile.util.ExcelUtil;
import com.banger.mobile.util.StringUtil;

/**
 * @author liyb
 * @version $Id: FinanceReportServiceImpl.java,v 0.1 2012-12-10 下午01:58:38 liyb Exp $
 */
public class FinanceReportServiceImpl implements FinanceReportService {
    private FinanceReportDao financeReportDao;
    private SysUserDao sysUserDao;

    public void setFinanceReportDao(FinanceReportDao financeReportDao) {
        this.financeReportDao = financeReportDao;
    }

    public void setSysUserDao(SysUserDao sysUserDao) {
        this.sysUserDao = sysUserDao;
    }

    /**
     * 查询财经要点报表
     * @param map
     * @return
     */
    public List<FinanceReportBean> getFinanceReportList(Map<String, Object> map,String startDate,String endDate,List<SysUserBean> userBeanList) {
        String searchDate="";
        if(!StringUtil.isBlank(startDate) && StringUtil.isBlank(endDate)){//开始日期查询
            searchDate=" AND TO_DATE ('" + startDate + "', 'yyyy-MM-dd')<=ARTICLE.ARTICLE_READTIME_END";
        }else if(StringUtil.isBlank(startDate) && !StringUtil.isBlank(endDate)){//结束日期查询
            searchDate=" AND TO_DATE ('" + endDate + "', 'yyyy-MM-dd')>=ARTICLE.ARTICLE_READTIME_BEGIN";
        }else if(!StringUtil.isBlank(startDate) && !StringUtil.isBlank(endDate)){//开始日期和结束日期
            searchDate=" AND (ARTICLE.ARTICLE_READTIME_BEGIN<=TO_DATE ('" + endDate+ "', 'yyyy-MM-dd') AND ARTICLE.ARTICLE_READTIME_END>=TO_DATE ('" + startDate + "', 'yyyy-MM-dd')) ";
        }
        map.put("searchDate", searchDate);
        /**
         * 第一步查询财经基础数据返回List
         */
        List<FinanceReportBean> fList=financeReportDao.getFinanceReportList(map);
        /**
         * 第二步构造数据
         */
        List<Map<String,Object>> mapList=new ArrayList<Map<String,Object>>();
        Map<String,Object> mapFinance=null;
        for (int i = 0; i < userBeanList.size(); i++) {
            SysUserBean ub=userBeanList.get(i);
            for (int j = 0; j < fList.size(); j++) {
                FinanceReportBean fb=fList.get(j);
                mapFinance=new HashMap<String, Object>();
                mapFinance.put("userId", ub.getUserId());
                mapFinance.put("userName", ub.getUserName());
                mapFinance.put("columnId", fb.getColumnId());
                mapFinance.put("columnName", fb.getColumnName());
                mapFinance.put("articleCount", fb.getArticleCount());
                mapFinance.put("mastReadArticleCount", fb.getMastReadArticleCount());
                mapFinance.put("readCount", fb.getReadCount());
                mapFinance.put("mastReadCount", fb.getMastReadCount());
                mapFinance.put("unReadCount", fb.getUnReadCount());
                mapFinance.put("mastUnReadCount", fb.getMastUnReadCount());
                mapFinance.put("readRate", fb.getReadRate());
                mapFinance.put("mastReadRate", fb.getMastReadRate());
                mapList.add(mapFinance);
            }
        }
        /**
         * 第四部把构造的数据放入List集合
         */
        List<FinanceReportBean> finList=new ArrayList<FinanceReportBean>();
        FinanceReportBean newFinance=null;
        for (int i = 0; i < mapList.size(); i++) {
            Map<String,Object> newMap=mapList.get(i);
            newFinance=new FinanceReportBean();
            newFinance.setUserId(Integer.parseInt(newMap.get("userId").toString()));
            newFinance.setUserName(newMap.get("userName").toString());
            newFinance.setColumnId(Integer.parseInt(newMap.get("columnId").toString()));
            newFinance.setColumnName(newMap.get("columnName").toString());
            newFinance.setArticleCount(Integer.parseInt(newMap.get("articleCount").toString()));
            newFinance.setMastReadArticleCount(Integer.parseInt(newMap.get("mastReadArticleCount").toString()));
            newFinance.setReadCount(Integer.parseInt(newMap.get("readCount").toString()));
            newFinance.setMastReadCount(Integer.parseInt(newMap.get("mastReadCount").toString()));
            newFinance.setUnReadCount(Integer.parseInt(newMap.get("unReadCount").toString()));
            newFinance.setMastUnReadCount(Integer.parseInt(newMap.get("mastUnReadCount").toString()));
            newFinance.setReadRate(newMap.get("readRate").toString());
            newFinance.setMastReadRate(newMap.get("mastReadRate").toString());
            finList.add(newFinance);
        }
        //查询用户/财经关系数据表返回List
        List<UserRelationReportBean> userRelaList=financeReportDao.getUserRelationReportList(map);
        NumberFormat format = NumberFormat.getPercentInstance();// 获取格式化类实例 
        format.setMinimumFractionDigits(0);// 设置小数位 
        /**
         * 循环构造数据
         */
        if(finList.size()>0){
            for (int x = 0; x < finList.size(); x++) {
                FinanceReportBean fin=finList.get(x);
                if(fin.getReadCount().equals(0)){
                    fin.setReadCount(0);//已读总数
                    fin.setMastReadCount(0);//已读必读总数
                    fin.setUnReadCount(fin.getArticleCount()-fin.getReadCount());//未读总数=总数-已读总数
                    fin.setMastUnReadCount(fin.getMastReadArticleCount()-fin.getMastReadCount());//未读必读总数=总必读数-已读必读总数
                    finList.set(x, fin);
                }
                if(userRelaList.size()>0){
                    for (int y = 0; y < userRelaList.size(); y++) {
                        UserRelationReportBean userRel=userRelaList.get(y);
                        if(fin.getUserId().equals(userRel.getUserId())&&fin.getColumnId().equals(userRel.getColumnId())){
                            fin.setReadCount(userRel.getIsRead());//已读总数
                            fin.setMastReadCount(userRel.getMastRead());//已读必读总数
                            fin.setUnReadCount(fin.getArticleCount()-fin.getReadCount());//未读总数=总数-已读总数
                            fin.setMastUnReadCount(fin.getMastReadArticleCount()-fin.getMastReadCount());//未读必读总数=总必读数-已读必读总数
                            if(!fin.getArticleCount().equals(0)){
                                fin.setReadRate(format.format((double)fin.getReadCount()/fin.getArticleCount()));
                            }else{
                                fin.setReadRate("0%");
                            }
                            if(!fin.getMastReadArticleCount().equals(0)){
                                fin.setMastReadRate(format.format((double)fin.getMastReadCount()/fin.getMastReadArticleCount()));
                            }else{
                                fin.setMastReadRate("0%");
                            }
                        }
                        finList.set(x, fin);
                    }
                }
            }
        }
        return finList;
    }

    /**
     * 财经要点数据表转化为Excel格式
     * @param finList 财经List
     * @param userList 用户List
     * @param userName 当前用户名
     * @return
     */
    @SuppressWarnings("deprecation")
    public HSSFWorkbook exportFinanceReportExcel(List<FinanceReportBean> finList,List<SysUserBean> userList, String userName,int colsCount) {
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet();
        sheet.setDefaultColumnWidth((short) 21);
        HSSFCellStyle cellStyle=wb.createCellStyle();
        cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 水平
        cellStyle.setFillForegroundColor(new HSSFColor.LIGHT_ORANGE().getIndex());
        cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        
        cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
        cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
        cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
        cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
        
        HSSFFont font = wb.createFont();
        font.setFontName("黑体");
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//粗体
        cellStyle.setFont(font);
        String bean[]=null;
        try {
            String headerNames[]=new String[] {EnumFinance.MANAGER_NAME_REPORT.getValue(),EnumFinance.COLUMN_NAME_REPORT.getValue(),
                                                           EnumFinance.ARITCLE_COUNT_REPORT.getValue(),EnumFinance.READ_COUNT_REPORT.getValue(),
                                                           EnumFinance.UNREAD_COUNT_REPORT.getValue(),EnumFinance.READ_RATE_REPORT.getValue()};
            if (headerNames == null) {
                headerNames = new String[] { "" };
            }
            HSSFRow row = sheet.createRow(0);
            if (headerNames != null && headerNames.length > 0) {
                for (int i = 1; i <= headerNames.length; i++) {
                    HSSFCell cell = row.createCell((short) i-1);
                    cell.setCellValue(headerNames[i-1]);
                    cell.setCellStyle(cellStyle);
                }
            }
            HSSFCellStyle cellStyleRow=wb.createCellStyle();
            cellStyleRow.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直
            cellStyleRow.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 水平
            if(finList!=null){
                int rowNum = 1;
                int uNum=1;
                int fCount=colsCount;//财经数
                for (int x = 0; x < userList.size(); x++) {
                    SysUserBean user=userList.get(x);
                    for (int y = 0; y < finList.size(); y++) {
                        FinanceReportBean fin=finList.get(y);
                        if(user.getUserId().equals(fin.getUserId())){
                            bean=new String[6];
                            bean[0]=user.getUserName();
                            bean[1]=fin.getColumnName();
                            bean[2]=fin.getArticleCount()+"("+fin.getMastReadArticleCount()+")";
                            bean[3]=fin.getReadCount()+"("+fin.getMastReadCount()+")";
                            bean[4]=fin.getUnReadCount()+"("+fin.getMastUnReadCount()+")";
                            bean[5]=fin.getReadRate()+"("+fin.getMastReadRate()+")";
                            
                            HSSFRow row1 = sheet.createRow(rowNum);
                            if (bean == null) {
                                bean = new String[] { "" };
                            }
                            if (bean != null && bean.length > 0) {
                                for (int i = 0; i < bean.length; i++) {
                                    HSSFCell cell = row1.createCell((short) i);
                                    cell.setCellValue(bean[i]);
                                    cell.setCellStyle(cellStyleRow);
                                }
                            }
                            rowNum++;
                        }
                    }
                    sheet.addMergedRegion(new   Region(uNum*fCount-(fCount-1),(short)0,uNum*fCount,(short)0));
                    uNum++;
                }
                HSSFRow endRow=sheet.createRow(rowNum);
                HSSFCell endCell=endRow.createCell((short)4);
                String bootText="制表人："+userName+"    制表时间：";
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                endCell.setCellValue(bootText+format.format(new Date()));
                sheet.addMergedRegion(new   Region(rowNum,(short)4,rowNum,(short)5));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return wb;
    }

    /**
     * 财经要点报表明细
     * @param parameters
     * @param page
     * @return
     */
    public PageUtil<BaseFeArticle> getFianceArticlePage(Map<String, Object> parameters, Page page,Integer flag,String startDate,String endDate) {
        String searchDate="";
        if(!StringUtil.isBlank(startDate) && StringUtil.isBlank(endDate)){//开始日期查询
            searchDate=" AND TO_DATE ('" + startDate + "', 'yyyy-MM-dd')<=ARTICLE_READTIME_END";
        }else if(StringUtil.isBlank(startDate) && !StringUtil.isBlank(endDate)){//结束日期查询
            searchDate=" AND TO_DATE ('" + endDate + "', 'yyyy-MM-dd')>=ARTICLE_READTIME_BEGIN";
        }else if(!StringUtil.isBlank(startDate) && !StringUtil.isBlank(endDate)){//开始日期和结束日期
            searchDate=" AND (ARTICLE_READTIME_BEGIN<=TO_DATE ('" + endDate+ "', 'yyyy-MM-dd') AND ARTICLE_READTIME_END>=TO_DATE ('" + startDate + "', 'yyyy-MM-dd')) ";
        }
        if(flag==1){
            parameters.put("isMastRead", 1);
        }
        parameters.put("searchDate", searchDate);
        PageUtil<BaseFeArticle> articlePage=financeReportDao.getFianceArticlePage(parameters, page);
        removeHTML(articlePage);
        return articlePage;
    }
    private void removeHTML(PageUtil<BaseFeArticle> pageUtils){
        for (BaseFeArticle feArticle : pageUtils.getItems()) {
            String content = StringUtil.removeHTML(feArticle.getArticleContent());
            if(content.length() > 150){
                content = content.substring(0, 150)+"...";
            }
            feArticle.setArticleContent(content);
        }
    }
}
