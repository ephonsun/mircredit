/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :任务报表service接口实现
 * Author     :liyb
 * Create Date:2013-12-26
 */
package com.banger.mobile.facade.impl.task.microTask;

import java.text.SimpleDateFormat;
import java.util.Date;
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

import com.banger.mobile.dao.microReport.MicroTaskReportDao;
import com.banger.mobile.domain.Enum.product.EnumProduct;
import com.banger.mobile.domain.model.microReport.LoanTaskReportBean;
import com.banger.mobile.facade.microReport.MicroTaskReportService;

/**
 * @author liyb
 * @version $Id: MicroTaskReportServiceImpl.java,v 0.1 2013-12-26 上午11:40:21 liyb Exp $
 */
public class MicroTaskReportServiceImpl implements MicroTaskReportService {
    private MicroTaskReportDao microTaskReportDao;

    public void setMicroTaskReportDao(MicroTaskReportDao microTaskReportDao) {
        this.microTaskReportDao = microTaskReportDao;
    }

    /**
     * 贷款任务报表统计
     * @param map
     * @param searchType
     * @return
     */
    public List<LoanTaskReportBean> getLoanTaskReportList(Map<String, Object> map, String searchType) {
        return microTaskReportDao.getLoanTaskReportList(map, searchType);
    }

    /**
     * 导出贷款任务报表
     * @param loanTskList
     * @param userName 用户名
     * @return
     */
    public HSSFWorkbook exportLoanTaskReportExcel(List<LoanTaskReportBean> loanTskList,boolean isDept,String userName) {
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet();
        sheet.setDefaultColumnWidth((short) 15);
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
            String[] headerNames;
            if(isDept){
                headerNames =new String[]{"机构名称","任务名称","任务目标","已完成","已完成占比","调查","审批","放贷"};
            }else{
                headerNames =new String[]{"机构名称","人员姓名","任务名称","任务目标","已完成","已完成占比","调查","审批","放贷"};
            }
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
            
            if(loanTskList.size()>0){
                int rowNum = 1;
                for (int i = 0; i < loanTskList.size(); i++) {
                    LoanTaskReportBean task = loanTskList.get(i);
                    if(isDept){
                        bean=new String[8];
                        bean[0]=task.getDeptName();
                        bean[1]=task.getTaskTitle();
                        bean[2]=task.getTaskTarget()+"";
                        bean[3]=task.getFinishCount()+"";
                        bean[4]=task.getComPercent()+"%";
                        bean[5]=task.getDCcount()+"";
                        bean[6]=task.getSPcount()+"";
                        bean[7]=task.getFDcount()+"";
                    }else{
                        bean=new String[9];
                        bean[0]=task.getDeptName();
                        bean[1]=task.getUserName();
                        bean[2]=task.getTaskTitle();
                        bean[3]=task.getTaskTarget()+"";
                        bean[4]=task.getFinishCount()+"";
                        bean[5]=task.getComPercent()+"%";
                        bean[6]=task.getDCcount()+"";
                        bean[7]=task.getSPcount()+"";
                        bean[8]=task.getFDcount()+"";
                    }
                    HSSFRow rowType = sheet.createRow(rowNum);
                    if (bean == null) {
                        bean = new String[] { "" };
                    }
                    if (bean != null && bean.length > 0) {
                        for (int x = 0; x < bean.length; x++) {
                            HSSFCell cell = rowType.createCell((short) x);
                            cell.setCellValue(bean[x]);
                        }
                    }
                    rowNum++;
                }
                HSSFRow endRow=sheet.createRow(rowNum);
                HSSFCell endCell;
                if(isDept) endCell=endRow.createCell((short)5);
                else endCell=endRow.createCell((short)6);
                String bootText=EnumProduct.MADE_USER.getValue()+userName+EnumProduct.MADE_DATE.getValue();
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                endCell.setCellValue(bootText+format.format(new Date()));
                if(isDept) sheet.addMergedRegion(new   Region(rowNum,(short)5,rowNum,(short)7));
                else sheet.addMergedRegion(new   Region(rowNum,(short)6,rowNum,(short)8));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return wb;
    }

    /**
     * 导出营销任务报表
     * @param loanTskList
     * @param userName 用户名
     * @return
     */
    public HSSFWorkbook exportMicroTskMarketingReportExcel(List<LoanTaskReportBean> loanTskList,boolean isDept, String userName) {
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet();
        sheet.setDefaultColumnWidth((short) 15);
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
            String[] headerNames;
            if(isDept){
                headerNames =new String[]{"机构","任务类型","任务名称","任务目标","已完成","已完成占比"};
            }else{
                headerNames =new String[]{"机构","名称","任务类型","任务名称","任务目标","已完成","已完成占比"};
            }
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
            
            if(loanTskList.size()>0){
                int rowNum = 1;
                for (int i = 0; i < loanTskList.size(); i++) {
                    LoanTaskReportBean task = loanTskList.get(i);
                    if(isDept){
                        bean=new String[6];
                        bean[0]=task.getDeptName();
                        if(task.getTaskType() == 2){
                            bean[1]="营销任务-电话营销";
                        }else{
                            bean[1]="营销任务-实地营销"; 
                        }
                        bean[2]=task.getTaskTitle();
                        bean[3]=task.getTaskTarget()+"";
                        bean[4]=task.getFinishCount()+"";
                        bean[5]=task.getComPercent()+"%";
                    }else{
                        bean=new String[7];
                        bean[0]=task.getDeptName();
                        bean[1]=task.getUserName();
                        if(task.getTaskType() == 2){
                            bean[2]="营销任务-电话营销";
                        }else{
                            bean[2]="营销任务-实地营销"; 
                        }
                        bean[3]=task.getTaskTitle();
                        bean[4]=task.getTaskTarget()+"";
                        bean[5]=task.getFinishCount()+"";
                        bean[6]=task.getComPercent()+"%";
                    }
                    HSSFRow rowType = sheet.createRow(rowNum);
                    if (bean == null) {
                        bean = new String[] { "" };
                    }
                    if (bean != null && bean.length > 0) {
                        for (int x = 0; x < bean.length; x++) {
                            HSSFCell cell = rowType.createCell((short) x);
                            cell.setCellValue(bean[x]);
                        }
                    }
                    rowNum++;
                }
                HSSFRow endRow=sheet.createRow(rowNum);
                HSSFCell endCell;
                if(isDept) endCell=endRow.createCell((short)3);
                else endCell=endRow.createCell((short)4);
                String bootText=EnumProduct.MADE_USER.getValue()+userName+EnumProduct.MADE_DATE.getValue();
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                endCell.setCellValue(bootText+format.format(new Date()));
                if(isDept) sheet.addMergedRegion(new   Region(rowNum,(short)3,rowNum,(short)5));
                else sheet.addMergedRegion(new   Region(rowNum,(short)4,rowNum,(short)6));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return wb;
    }

    /**
     * 查询任务
     * @param map
     * @return
     */
    public List<LoanTaskReportBean> getTaskByTitle(Map<String, Object> map) {
        return microTaskReportDao.getTaskByTitle(map);
    }

}
