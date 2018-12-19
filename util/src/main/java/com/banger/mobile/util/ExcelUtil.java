
package com.banger.mobile.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 * @author zhangxiang
 * @version $Id: ExcelUtil.java,v 0.1 2011-6-14 下午04:47:30 zhangxiang Exp $
 */
public class ExcelUtil {
    
    private static final Log logger = LogFactory.getLog("ExcelUtil");

    /**
     * 写excel到指定的文件
     * @param workbook
     * @param fileName
     * @return
     */
    public static String writeToFile(HSSFWorkbook workbook, String fileName) {
        try {
            if (workbook == null) {
                logger.error("workbook不能为空白");
                return null;
            }
            if (StringUtil.isEmpty(fileName)) {
                logger.error("fileName不能为空白");
                return null;
            }
            File file = new File(fileName);
            if (!file.isFile()) {
                file.createNewFile();
            }
            FileOutputStream baos = new FileOutputStream(fileName);
            workbook.write(baos);
            baos.flush();
            baos.close();
        } catch (Exception e) {
            logger.error("writeToFile:" + e.getMessage());
            return null;
        }
        return fileName;
    }

    /**
     * 写excel到response
     * @param workbook
     * @param baos
     */
    public static void writeToResponse(HSSFWorkbook workbook, OutputStream baos) {
        try {
            if (workbook == null) {
                logger.error("workbook不能为空白");
            }

            workbook.write(baos);
            baos.flush();
            baos.close();
        } catch (Exception e) {
            logger.error("writeToFile:" + e.getMessage());
        }
    }

    /**
     * 写excel的header
     * @param sheet
     * @param headerNames
     */
    public static void writeExcelHeader(HSSFSheet sheet, String[] headerNames) {
        try {
            HSSFRow row = sheet.createRow(0);
            if (headerNames == null) {
                headerNames = new String[] { "" };
            }
            if (headerNames != null && headerNames.length > 0) {
                for (int i = 0; i < headerNames.length; i++) {
                    HSSFCell cell = row.createCell((short) i);
                    cell.setCellValue(headerNames[i]);
                }
            }
        } catch (Exception e) {
            logger.error("写excel的header部分发生异常");
        }
    }

    /**
     * 写excel的header
     * @param sheet
     * @param headerNames
     */
    public static void writeExcelHeader(HSSFSheet sheet, String[] headerNames, int pos) {
        try {
            HSSFRow row = sheet.createRow(pos);
            if (headerNames == null) {
                headerNames = new String[] { "" };
            }
            if (headerNames != null && headerNames.length > 0) {
                for (int i = 0; i < headerNames.length; i++) {
                    HSSFCell cell = row.createCell((short) i);
                    cell.setCellValue(headerNames[i]);
                }
            }
        } catch (Exception e) {
            logger.error("写excel的header部分发生异常");
        }
    }

    /**
     * 逐行写明细
     * @param sheet
     * @param detailValues
     * @param rowNum
     */
    public static void writeExcelDetailRow(HSSFSheet sheet, String[] detailValues, int rowNum) {
        try {
            HSSFRow row = sheet.createRow(rowNum);
            if (detailValues == null) {
                detailValues = new String[] { "" };
            }
            if (detailValues != null && detailValues.length > 0) {
                for (int i = 0; i < detailValues.length; i++) {
                    HSSFCell cell = row.createCell((short) i);
                    cell.setCellValue(detailValues[i]);
                }
            }
        } catch (Exception e) {
            logger.error("写excel的DetailRow部分发生异常");
        }
    }

}
