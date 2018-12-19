package com.banger.mobile.facade;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.Region;

import com.banger.mobile.domain.collection.DataRow;
import com.banger.mobile.domain.collection.DataTable;
import com.banger.mobile.util.TypeUtil;

public abstract class BaseReportServiceImpl implements BaseReportService {
	
	/**
	 * 数据表转化为Excel格式
	 * @param table
	 * @return
	 */
	public HSSFWorkbook exportExcel(DataTable table,String userName)
	{
		return this.exportExcel(table,15,userName);
	}
	
	protected void setDefaultValue(DataTable table, int r, int c, HSSFCell eCell){
		eCell.setCellValue("");
	}
	
	public HSSFWorkbook exportExcel(DataTable table,int width,String userName)
	{
	    HSSFWorkbook wk = new HSSFWorkbook();
        HSSFSheet sheet = wk.createSheet();
        HSSFCellStyle style = wk.createCellStyle(); // 样式对象       
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直       
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 水平    
        sheet.setDefaultColumnWidth((short)width);//设置默认宽度
	    try {
	        for(int i=0;i<table.rowSize();i++)
	        {
	            DataRow row = table.getRow(i);
	            HSSFRow eRow = sheet.createRow(i);
	            
	            for(int j=0;j<table.colSize();j++)
	            {
	                HSSFCell eCell = eRow.createCell((short)j);
	                eCell.setCellStyle(style); // 样式，居中      
	                Object val = row.get(j);
	                if(val!=null)
	                {
	                    String text="";
	                    if(TypeUtil.isCollectionType(val.getClass())){//数组类型
	                        List list=(List) val;
	                        text=list.get(0).toString();
	                    }else{
	                        text = (String)TypeUtil.changeType(val,String.class);
	                    }
	                    if(text.equals("")){
	                    	setDefaultValue(table, i, j, eCell);
	                    }else{
	                    	eCell.setCellValue(text);
	                    }
	                }else{
	                	setDefaultValue(table, i, j, eCell);
	                }
	            }
	        }
	        
	        int rowId=table.rowSize();
	        int colId=table.colSize()-2;
	        HSSFRow endRow=sheet.createRow(rowId);
	        HSSFCell endCell=endRow.createCell((short)colId);
	        String bootText="制表人："+userName+"    制表时间：";
	        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        endCell.setCellValue(bootText+format.format(new Date()));
	        
	        beforeExportExcel(table, sheet);
        } catch (Exception e) {}
        return wk;
	}
	
	protected void beforeExportExcel(DataTable table, HSSFSheet sheet){
		
	}
}
