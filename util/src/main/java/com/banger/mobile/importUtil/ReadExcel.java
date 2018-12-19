/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :execl读取识别不同的类型如2003,2007,WPS等版本...
 * Author     :yangy
 * Create Date:2012-12-7
 */
package com.banger.mobile.importUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.POIXMLDocument;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.banger.mobile.domain.Enum.customer.EnumCustomer;
import com.banger.mobile.domain.collection.DataRow;
import com.banger.mobile.domain.collection.DataTable;
import com.banger.mobile.util.StringUtil;

/**
 * Excel文件的读取
 * 
 * @author yangyang
 * @version $Id: ReadExcel.java,v 0.1 2012-12-7 下午2:42:14 yangyong Exp $
 */
public class ReadExcel {
	protected static final Log log = LogFactory.getLog(ReadExcel.class);

	/**
	 * 返回Excel文件中的表头
	 * 
	 * @return
	 */
	public static List<String> readColumnNames(String filePath) {
		HttpServletRequest req = org.apache.struts2.ServletActionContext
				.getRequest();
		HttpSession session = req.getSession();
		List<String> columnNames = new ArrayList<String>();
		try {
			Workbook wb = getExcelType(filePath);
			// 如果该Excel不能解析
			if (wb == null) {
				return null;
			}
			Map<String, Integer> recordMap = new HashMap<String, Integer>();
			int sumRecord = 0;
			int nullrecord = 0;
			int erroRecord = 0;
			Sheet hssfSheet = wb.getSheetAt(0);
			sumRecord += hssfSheet.getLastRowNum() + 1;// 上传总记录数
			for (int rowNum = 0; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
				Row hssfRow = hssfSheet.getRow(rowNum);
				if (ImportUtil.isBlankRow(hssfRow)) {// 遇到空行,跳出本次循环
					nullrecord++;
					continue;
				} else {
					// 循环列Cell
					for (int cellNum = 0; cellNum <= hssfRow.getLastCellNum(); cellNum++) {
						Cell hssfCell = hssfRow.getCell(cellNum);
						// hssfCell.setCellType(1);
						if (hssfCell == null) {
							continue;
						}
						if (rowNum == 0) {
							String temp = ImportUtil.getValue(hssfCell);
							if (!temp.equals(EnumCustomer.IMPORT_NO.getValue())
									&& !temp.equals(EnumCustomer.REASONS_FOR_FAILURE
											.getValue()))
								columnNames.add(ImportUtil.getValue(hssfCell)
										.replaceAll(" ", ""));
							else
								erroRecord++;
						}
					}
				}
			}
			sumRecord = sumRecord - nullrecord - 1;
			Integer bathchCount = ImportUtil.getBathchCount(sumRecord,
					ImportUtil.getBatchSize(sumRecord));
			recordMap.put("total", sumRecord);
			recordMap.put("erroRecord", erroRecord);
			recordMap.put("bathchCount", bathchCount);
			recordMap.put("nullCount", nullrecord);
			session.setAttribute("recordMap", recordMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return columnNames;
	}

	/**
	 * 读取Excel
	 * 
	 * @param filename
	 * @return
	 */
	public static DataTable readExcelTable(String filename) {
		DataTable table = new DataTable();
		try {
			Workbook wb = getExcelType(filename);
			Sheet hssfSheet = wb.getSheetAt(0);
			for (int rowNum = 0; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
				Row hssfRow = hssfSheet.getRow(rowNum);
				if (rowNum == 0) {
					for (int cellNum = 0; cellNum <= hssfRow.getLastCellNum(); cellNum++) {
						Cell hssfCell = hssfRow.getCell(cellNum);
						if (hssfCell != null) {
							String val = ImportUtil.getValue(hssfCell);
							if (!StringUtil.isNullOrEmpty(val)) {
								String colName = "COL" + (cellNum + 1);
								table.addColumn(colName);
							}
						}
					}
				} else {
					if (ImportUtil.isBlankRow(hssfRow)) {// 遇到空行,跳出本次循环
						continue;
					} else {
						DataRow row = table.newRow();
						for (int cellNum = 0; cellNum <= hssfRow
								.getLastCellNum(); cellNum++) {
							String colName = "COL" + (cellNum + 1);
							Cell hssfCell = hssfRow.getCell(cellNum);
							if (hssfCell != null) {
								String val = ImportUtil.getValue(hssfCell);
								if (!StringUtil.isNullOrEmpty(val)) {
									row.set(colName, val);
								}
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return table;
	}

	/**
	 * 判断是否是空行
	 * 
	 * @param row
	 * @return
	 */
	public static boolean isBlankRow(Row row) {
		if (row == null)
			return true;
		boolean result = true;
		for (int i = row.getFirstCellNum(); i < row.getLastCellNum(); i++) {
			Cell cell = row.getCell(i);
			String value = "";
			if (cell != null) {
				switch (cell.getCellType()) {
				case Cell.CELL_TYPE_STRING:
					value = cell.getStringCellValue();
					break;
				case Cell.CELL_TYPE_NUMERIC:
					value = String.valueOf((int) cell.getNumericCellValue());
					break;
				case Cell.CELL_TYPE_BOOLEAN:
					value = String.valueOf(cell.getBooleanCellValue());
					break;
				case Cell.CELL_TYPE_FORMULA:
					value = String.valueOf(cell.getCellFormula());
					break;
				default:
					break;
				}
				if (!value.trim().equals("")) {
					result = false;
					break;
				}
			}
		}
		return result;
	}

	/**
	 * 识别不同的EXECL类型WPS,2003,2007
	 */
	public static Workbook getExcelType(String filePath) {
		try {
			InputStream inp = new FileInputStream(new File(filePath));
			if (!inp.markSupported()) {
				inp = new PushbackInputStream(inp, 8);
			}
			if (POIFSFileSystem.hasPOIFSHeader(inp)) {
				return new HSSFWorkbook(inp);
			}
			if (POIXMLDocument.hasOOXMLHeader(inp)) {
				return new XSSFWorkbook(OPCPackage.open(inp));
			}
			throw new IllegalArgumentException("您的Excel版本目前导入暂时不解析");
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}
	}

}
