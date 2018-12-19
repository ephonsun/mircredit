
package com.banger.mobile.util;
import java.text.NumberFormat;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.Region;


/**
 * @author zhangxiang
 * @version $Id: ExcelTool.java,v 0.1 2011-6-14 下午05:05:20 zhangxiang Exp $
 */
public class ExcelTool {
    
    /**
     * 取一个excel单元格的值
     * @param cell
     * @return
     */
    public static String getCellValue(HSSFCell cell) {
        String cellValue = null;

        try {

            switch (cell.getCellType()) {
                case HSSFCell.CELL_TYPE_STRING:
                    cellValue = StringUtil.trim(cell.getStringCellValue());
                    break;
                case HSSFCell.CELL_TYPE_BOOLEAN:
                    cellValue = cell.getBooleanCellValue() ? "y" : "n";
                    break;
                case HSSFCell.CELL_TYPE_NUMERIC:
                    cellValue = NumberFormat.getNumberInstance().format(cell.getNumericCellValue());
                    cellValue = splitCommaToString(cellValue);
                    break;
                case HSSFCell.CELL_TYPE_FORMULA:
                    cellValue = StringUtil.trim(cell.getCellFormula());
                    break;
                case HSSFCell.CELL_TYPE_ERROR:
                    cellValue = StringUtil.trim(String.valueOf(cell.getErrorCellValue()));
                    break;
                default:
                    cellValue = StringUtil.trim(cell.getStringCellValue());
                    break;
            }
        } catch (Exception ex) {
            return null;
        }

        return cellValue;
    }
    /**
     * 取得单元格的LongValue值
     * @param cell
     * @return
     */
    public static Long getCellLongValue(HSSFCell cell) {

        if (cell == null) {
            return null;
        }
        // 取得单元格的内容
        String cellValue = getCellValue(cell);

        if (StringUtil.isBlank(cellValue) || !StringUtil.isNumeric(cellValue)) {
            return null;
        }

        return Long.valueOf(cellValue);
    }

    /**
     * 取得单元格的LongValue值
     * 
     * @author xiasq
     * @param cell
     * @return
     */
    public static Long getCellLongValueWithDefult(HSSFCell cell) {

        if (cell == null) {
            return null;
        }
        // 取得单元格的内容
        String cellValue = getCellValue(cell);

        if (StringUtil.isBlank(cellValue) || !StringUtil.isNumeric(cellValue)) {
            return 0L;
        }

        return Long.valueOf(cellValue);
    }

    /**
     * 取得单元格的LongValue值
     * 
     * @author xiasq
     * @param cell
     * @return
     */
    public static Integer getCellIntegerValue(HSSFCell cell) {

        if (cell == null) {
            return null;
        }
        // 取得单元格的内容
        String cellValue = getCellValue(cell);

        if (StringUtil.isBlank(cellValue) || !StringUtil.isNumeric(cellValue)) {
            return null;
        }

        return Integer.valueOf(cellValue);
    }

    /**
     * 去掉逗号,然后再整合。
     * @param dealing 要处理的字符串
     * @return
     */
    public static String splitCommaToString(String dealing) {
        if (StringUtil.isBlank(dealing)) {
            return "";
        }

        while (dealing.indexOf(",") > -1) {
            dealing = dealing.substring(0, dealing.indexOf(","))
                      + dealing.substring(dealing.indexOf(",") + 1);
        }

        return dealing;
    }

    /**
     * 写单元格(not for title)， 不加样式
     *
     * @param row 要写的Excel的行数
     * @param inum 定位到具体的单元格
     * @param str 要写的单元格的内容
     * @return
     */
    public static HSSFCell createCellWithNoStyle(HSSFRow row, int inum, String str) {
        HSSFCell cell = null;
        cell = row.createCell((short) inum);
        //cell.setEncoding(HSSFCell.ENCODING_UTF_16);
        str = StringUtil.trim(str);
        if (StringUtil.isBlank(str) || str.equals("null")) {
            cell.setCellValue("");
        } else {
            if (StringUtil.isNumeric(str)) {
                cell.setCellValue(Long.valueOf(str));
            } else {
                cell.setCellValue(str);
            }
        }
        return cell;
    }

    /**
     * 写单元格(not for title)， 不加样式
     * @param row 要写的Excel的行数
     * @param inum 定位到具体的单元格
     * @param str 要写的单元格的内容
     * @return
     */
    public static HSSFCell createCellWithStyle(HSSFRow row, HSSFCellStyle style, int inum,
                                               String str) {
        HSSFCell cell = null;
        cell = row.createCell((short) inum);
        cell.setCellStyle(style);
        //cell.setEncoding(HSSFCell.ENCODING_UTF_16);
        str = StringUtil.trim(str);
        if (StringUtil.isBlank(str) || str.equals("null")) {
            cell.setCellValue("");
        } else {
            if (StringUtil.isNumeric(str)) {
                cell.setCellValue(Long.valueOf(str));
            } else {
                cell.setCellValue(str);
            }
        }
        return cell;
    }

    /**
     * 写把单元格，使单元格的内容强制转换成字符串类型
     * 
     * @param row 要写的Excel的行数
     * @param inum 定位到具体的单元格
     * @param str 要写的单元格的内容
     * @return
     */
    public static HSSFCell createCellWithNoStyleToString(HSSFRow row, int inum, String str) {
        HSSFCell cell = null;
        cell = row.createCell((short) inum);
       // cell.setEncoding(HSSFCell.ENCODING_UTF_16);
        str = StringUtil.trim(str);
        if (StringUtil.isBlank(str) || str.equals("null")) {
            cell.setCellValue("");
        } else {
            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
            cell.setCellValue(str);
        }
        return cell;
    }

    /**
     * 合并该行的单元格从第一个到第columnMax+1个
     * 
     * @param sheet
     * @param row
     */
    public static void createCellByMergeRow(HSSFSheet sheet, HSSFRow row, int columnMax) {
        int colnum = 0;

        while (colnum < columnMax) {
            if (row.getCell((short) colnum) == null) {
                row.createCell((short) colnum);
            }
            colnum++;
        }

        Region region = new Region();
        region.setColumnFrom((short) 0);
        region.setColumnTo((short) columnMax);
        region.setRowFrom(row.getRowNum());
        region.setRowTo(row.getRowNum());
        sheet.addMergedRegion(region);
    }

    /**
     * 只设置边框样式
     * @param wb 当前工作簿
     * @param borderType 边框的类型
     * @return
     */
    public static HSSFCellStyle setCellBorderStyle(HSSFWorkbook wb, short borderType) {
        HSSFCellStyle style = wb.createCellStyle();
        style = setCellStyle(wb, style, borderType, false, false, false, (short) 0, (short) 0);
        return style;
    }

    /**
     * 设置边框样式,单元格可自动折行显示
     * @param wb 当前工作簿
     * @param borderType 边框的类型
     * @return
     */
    public static HSSFCellStyle setCellBorderStyleWarped(HSSFWorkbook wb, short borderType) {
        HSSFCellStyle style = wb.createCellStyle();
        style = setCellStyle(wb, style, borderType, true, false, false, (short) 0, (short) 0);
        return style;
    }

    /**
     * 设置边框样式,设置背景色
     * @param wb 当前工作簿
     * @param borderType 边框的类型
     * @param backColor 背景颜色
     * @return
     */
    public static HSSFCellStyle setCellBorderStyleWithBackgroundColor(HSSFWorkbook wb,
                                                                      short borderType,
                                                                      short backColor) {
        HSSFCellStyle style = wb.createCellStyle();
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        style = setCellStyle(wb, style, borderType, false, false, true, (short) 0, backColor);
        return style;
    }

    /**
     * 设置边框样式,大小并且字体为粗体
     * @param wb 当前工作簿
     * @param borderType 边框的类型
     * @return
     */
    public static HSSFCellStyle setCellBorderStyleWithBigFont(HSSFWorkbook wb, short borderType,
                                                              short fontSize, boolean isNeedAlign) {
        HSSFCellStyle style = wb.createCellStyle();
        HSSFFont font = wb.createFont();
        // font.setColor(HSSFFont.COLOR_RED);
        // 设置粗体
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        font.setFontHeight(fontSize);

        if (isNeedAlign) {
            style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            style.setVerticalAlignment(HSSFCellStyle.ALIGN_CENTER);
        }

        style.setFont(font);
        style.setWrapText(true);
        style = setCellStyle(wb, style, borderType, false, false, false, (short) 0, (short) 0);
        return style;
    }

    /**
     * 设置边框样式,大小并且字体为粗体
     * @param wb 当前工作簿
     * @param borderType 边框的类型
     * @return
     */
    public static HSSFCellStyle setCellBorderStyleWithColorFont(HSSFWorkbook wb, short borderType,
                                                                short fontSize, short fontColor,
                                                                boolean isNeedAlign) {
        HSSFCellStyle style = wb.createCellStyle();
        HSSFFont font = wb.createFont();
        // font.setColor(HSSFFont.COLOR_RED);
        // 设置粗体
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        font.setFontHeight(fontSize);
        font.setColor(fontColor);
        if (isNeedAlign) {
            style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            style.setVerticalAlignment(HSSFCellStyle.ALIGN_CENTER);
        }

        style.setFont(font);
        style.setWrapText(true);
        style = setCellStyle(wb, style, borderType, false, false, false, (short) 0, (short) 0);
        return style;
    }

    /**
     * 设置单元格边框以及格式
     * @param wb 当前工作簿
     * @param borderType 边框的类型
     * @param isWarp 是否要换行
     * @param isForeColor 是否需要前景色
     * @param isBackColor 是否需要背景色
     * @param foreColor 前景色的值
     * @param backColor 背景色的值
     * @return
     */
    public static HSSFCellStyle setCellStyle(HSSFWorkbook wb, HSSFCellStyle style,
                                             short borderType, boolean isWarp, boolean isForeColor,
                                             boolean isBackColor, short foreColor, short backColor) {
        style.setBorderBottom(borderType);
        style.setBorderLeft(borderType);
        style.setBorderRight(borderType);
        style.setBorderTop(borderType);
        // 是否自动换行
        if (isWarp) {
            style.setWrapText(true);
        }

        // 是否要设置背景色
        if (isBackColor) {
            // 设定此样式的的背景颜色填充
            style.setFillPattern(HSSFCellStyle.SPARSE_DOTS);
            style.setFillBackgroundColor(backColor);
        }

        // 是否要设置前景色
        if (isForeColor) {
            style.setFillForegroundColor(foreColor);
        }

        return style;
    }

}
