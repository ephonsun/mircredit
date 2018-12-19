/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :导入通用方法
 * Author     :yangy
 * Create Date:2012-10-31
 */
package com.banger.mobile.importUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sourceforge.pinyin4j.PinyinHelper;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import banger.util.BDate;

import com.banger.mobile.domain.Enum.customer.EnumCustomer;
import com.banger.mobile.domain.Enum.product.EnumProductType;
import com.banger.mobile.domain.collection.DataRow;
import com.banger.mobile.domain.collection.DataTable;
import com.banger.mobile.domain.model.crmModuleExport.CrmModuleExport;
import com.banger.mobile.domain.model.customer.CrmCustomer;
import com.banger.mobile.domain.model.customer.CrmCustomerExtBean;
import com.banger.mobile.domain.model.customer.CrmCustomerTransfer;
import com.banger.mobile.domain.model.pdtProduct.BuyCustomerBean;
import com.banger.mobile.domain.model.pdtProduct.PdtProductField;
import com.banger.mobile.domain.model.user.IUserInfo;
import com.banger.mobile.util.FileUtil;
import com.banger.mobile.util.SerializeUtil;
import com.banger.mobile.util.StringUtil;

/**
 * 导入通用方法
 * 
 * @author yangyang
 * @version $Id: CustomerImportUtil.java,v 0.1 2012-10-31 上午10:57:16 yangyong
 *          Exp $
 */
public class ImportUtil {

	/**
	 * 判断key是否在数组存在
	 * 
	 * @param InCharge
	 * @param key
	 * @return
	 */
	public static Boolean selectArr(Integer[] InCharge, Integer key)
	{
		for (int i = 0; i < InCharge.length; i++)
		{
			if (InCharge[i].equals(key))
			{
				return true;
			}
		}
		return false;
	}

	/**
	 * 将错误的信息转移到错误表中
	 * 
	 * @param dt
	 * @param cct
	 */
	public static void copyEorrBuyRecord(DataTable dt, CrmCustomerTransfer cct)
	{
		DataTable table = cct.getErrortbl();
		for (int i = 1; i < dt.rowSize(); i++)
		{
			DataRow dataRow = dt.getRow(i);
			DataRow row = table.newRow();
			if (dataRow.get("errorMsg") != null
					&& !dataRow.get("errorMsg").equals(""))
			{
				for (int j = 0; j < dt.colSize(); j++)
				{
					Object value = dt.getColumn(j).getValues()[i];
					row.set(j, value);
				}
			}
		}
	}

	/**
	 * 将错误的信息转移到错误表中
	 * 
	 * @param dt
	 * @param cct
	 */
	public static void copyEorrRecord(DataTable dt, CrmCustomerTransfer cct)
	{
		DataTable table = cct.getErrortbl();
		for (int i = 1; i < dt.rowSize(); i++)
		{
			DataRow dataRow = dt.getRow(i);
			DataRow row = table.newRow();
			if (dataRow.get("errorMsg") != null
					&& !dataRow.get("errorMsg").equals(""))
			{
				for (int j = 0; j < dt.colSize(); j++)
				{
					Object value = dt.getColumn(j).getValues()[i];
					if (value != null && value.toString().split(",").length > 1)
						row.set(j, value.toString().split(",")[0]);
					else
						row.set(j, value);
				}
			}
		}
	}

	/**
	 * 复制表头
	 * 
	 * @param dt
	 * @param cct
	 */
	public static void copyDataTableHead(DataTable dt, CrmCustomerTransfer cct)
	{
		DataTable table = cct.getErrortbl();
		DataRow row = dt.newRow();
		for (int j = 0; j < table.colSize(); j++)
		{
			dt.addColumn(table.getColumn(j).getName());
			Object value = table.getColumn(j).getValues()[0];
			row.set(j, value);
		}
	}

	/**
	 * 提取每个汉字的首字母
	 * 
	 * @param str
	 * @return String
	 */
	public static String getPinYinHeadChar(String str)
	{
		String convert = "";
		for (int j = 0; j < str.length(); j++)
		{
			char word = str.charAt(j);
			// 提取汉字的首字母
			String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(word);
			if (pinyinArray != null)
			{
				convert += pinyinArray[0].charAt(0);
			} else
			{
				convert += word;
			}
		}
		return convert;
	}

	/**
	 * 判断日期是否大于今天
	 * 
	 * @return
	 */
	public static boolean isDateLargeToday(Date date)
	{
		try
		{
			Date today = new Date();
			if (date.before(today))
				return false;
			else
				return true;
		} catch (Exception e)
		{
			return false;
		}
	}

	/**
	 * 判断性别是否是男和女
	 * 
	 * @param sex
	 * @return
	 */
	public static boolean isSex(String sex)
	{
		if (sex.equals(""))
		{
			return false;
		} else
		{
			if (sex.equals("男") || sex.equals("女"))
				return true;
			else
				return false;
		}
	}

	/**
	 * 去除重复项
	 * 
	 * @param str
	 * @return
	 */
	public static String delString(String str)
	{
		String[] s = str.split(",");
		List list = Arrays.asList(s);
		Set set = new HashSet(list);
		String[] rid = (String[]) set.toArray(new String[0]);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < rid.length; i++)
		{
			sb.append(rid[i] + ",");
		}
		return sb.substring(0, sb.length() - 1);
	}

	/**
	 * 判断日期是否在上架日期范围内
	 * 
	 * @return
	 */
	public static boolean isDateInSellDate(Date startDate, Date endDate,
			Date date)
	{
		try
		{
			Date date1 = BDate.addDay(date, +1);
			Date date2 = BDate.addDay(date, -1);
			if (startDate.before(date1) && endDate.after(date2))
				return false;
			else
				return true;
		} catch (Exception e)
		{
			return false;
		}
	}

	/**
	 * 取出单元格里面的值
	 * 
	 * @param hssfCell
	 * @return
	 */
	@SuppressWarnings({ "static-access" })
	public static String getValue(HSSFCell hssfCell)
	{
		DecimalFormat df = new DecimalFormat("#");
		if (hssfCell.getCellType() == hssfCell.CELL_TYPE_BOOLEAN)
		{
			return String.valueOf(hssfCell.getBooleanCellValue());
		} else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC)
		{
			return String.valueOf(df.format(hssfCell.getNumericCellValue()));
		} else
		{
			return String.valueOf(hssfCell.getStringCellValue());
		}
	}

	/**
	 * 取出单元格里面的值
	 * 
	 * @param hssfCell
	 * @return
	 */
	@SuppressWarnings({ "static-access" })
	public static String getValue(Cell hssfCell)
	{
		DecimalFormat df = new DecimalFormat("#");
		SimpleDateFormat sf = new SimpleDateFormat("yyyy年MM月dd日");
		if (hssfCell.getCellType() == hssfCell.CELL_TYPE_BOOLEAN)
		{
			return String.valueOf(hssfCell.getBooleanCellValue());
		} else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC)
		{
			// 判断是否日期格式
			if (HSSFDateUtil.isCellDateFormatted(hssfCell))
			{
				return String.valueOf(sf.format(HSSFDateUtil
						.getJavaDate(hssfCell.getNumericCellValue())));
			}
			// 判断是否自定义的日期格式
			if (hssfCell.getCellStyle().getDataFormat() == 58)
			{
				return String.valueOf(sf.format(HSSFDateUtil
						.getJavaDate(hssfCell.getNumericCellValue())));
			}
			// 纯数字直接输出
			return String.valueOf(df.format(hssfCell.getNumericCellValue()));
		} else
		{
			return String.valueOf(hssfCell.getStringCellValue());
		}
	}

	/**
	 * 根据生日计算年龄
	 * 
	 * @param birthDay
	 * @return
	 * @throws Exception
	 */
	public static int getAge(Date birthDay)
	{
		Calendar cal = Calendar.getInstance();
		if (cal.before(birthDay))
		{
			throw new IllegalArgumentException(
					"The birthDay is before Now.It's unbelievable!");
		}
		int yearNow = cal.get(Calendar.YEAR);
		int monthNow = cal.get(Calendar.MONTH);
		int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);
		cal.setTime(birthDay);
		int yearBirth = cal.get(Calendar.YEAR);
		int monthBirth = cal.get(Calendar.MONTH);
		int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);
		int age = yearNow - yearBirth;
		if (monthNow <= monthBirth)
		{
			if (monthNow == monthBirth)
			{
				if (dayOfMonthNow < dayOfMonthBirth)
				{
					age--;
				} else
				{
				}
			} else
			{
				age--;
			}
		} else
		{
		}
		return age;
	}

	/**
	 * 判断是否是float型
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isFloat(String str)
	{
		try
		{
			new Float(str);
			return true;
		} catch (Exception e)
		{
			return false;
		}
	}

	/**
	 * 判断字符在字符串中是否出现
	 * 
	 * @param str
	 * @param s
	 * @return
	 */
	public static boolean DelRepetStr(String str, String s)
	{
		String[] list = str.split(",");
		for (int i = 0; i < list.length; i++)
		{
			if (s.equals(list[i]))
				return true;
			if (s.equals("," + list[i]))
				return true;
		}
		return false;
	}

	/**
	 * 判断是否是空行
	 * 
	 * @param row
	 * @return
	 */
	public static boolean isBlankRow(HSSFRow row)
	{
		if (row == null)
			return true;
		boolean result = true;
		for (int i = row.getFirstCellNum(); i < row.getLastCellNum(); i++)
		{
			HSSFCell cell = row.getCell(i);
			String value = "";
			if (cell != null)
			{
				switch (cell.getCellType())
				{
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
				// case Cell.CELL_TYPE_BLANK:
				// break;
				default:
					break;
				}

				if (!value.trim().equals(""))
				{
					result = false;
					break;
				}
			}
		}
		return result;
	}

	/**
	 * 判断是否是空行
	 * 
	 * @param row
	 * @return
	 */
	public static boolean isBlankRow(Row row)
	{
		if (row == null)
			return true;
		boolean result = true;
		for (int i = row.getFirstCellNum(); i < row.getLastCellNum(); i++)
		{
			Cell cell = row.getCell(i);
			String value = "";
			if (cell != null)
			{
				switch (cell.getCellType())
				{
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
				// case Cell.CELL_TYPE_BLANK:
				// break;
				default:
					break;
				}

				if (!value.trim().equals(""))
				{
					result = false;
					break;
				}
			}
		}
		return result;
	}

	/**
	 * 判断是手机还是固话
	 * 
	 * @param str
	 * @return
	 */
	public static Integer isPhoneOrMobilePhone(String str)
	{
		str = str.trim();
		if (isMobileNO(str))
		{
			return 1;
		} else if (isPhoneNO(str))
		{
			return 2;
		} else
			return 3;
	}

	/**
	 * 判断是否是手机
	 * 
	 * @param mobiles
	 * @return
	 */
	public static boolean isMobileNO(String mobiles)
	{
		// Pattern p =
		// Pattern.compile("^((13[0-9])|(14[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
		// Matcher m = p.matcher(mobiles);
		// return m.matches();
		if (isNumeric(mobiles))
		{
			if (mobiles.substring(0, 1).equals("1"))
			{
				if (mobiles.length() == 11)
				{
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 判断是否是固话
	 * 
	 * @param phone
	 * @return
	 */
	public static boolean isPhoneNO(String phone)
	{
		// Pattern p =
		// Pattern.compile("1([\\d]{10})|((\\+[0-9]{2,4})?\\(?[0-9]+\\)?-?)?[0-9]{7,8}");
		// Matcher m = p.matcher(phone);
		// return m.matches();
		phone = phone.replaceAll("-", "").replaceAll("－", "");
		if (isNumeric(phone))
		{
			if (phone.length() <= 20)
			{
				return true;
			}
		}
		return false;
	}

	/**
	 * 判断字符串是否为数字
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str)
	{
		for (int i = str.length(); --i >= 0;)
		{
			if (!Character.isDigit(str.charAt(i)))
			{
				return false;
			}
		}
		return true;
	}

	/**
	 * 判断是否是double型
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isDouble(String str)
	{
		try
		{
			new Double(str);
			return true;
		} catch (Exception e)
		{
			return false;
		}
	}

	/**
	 * 判断字符串是否为邮箱格式
	 * 
	 * @param email
	 * @return
	 */
	public static boolean isEmail(String email)
	{
		String str = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
		Pattern p = Pattern.compile(str);
		Matcher m = p.matcher(email);
		return m.matches();
	}

	/**
	 * 转日期
	 * 
	 * @param date
	 * @return
	 */
	public static Date parseAllStringToDate(String date)
	{
		String dateStr = date.trim();
		if (dateStr == null)
			return null;
		Date result = null;
		String parse = dateStr;
		if (dateStr.equals(""))
		{
			return null;
		}
		parse = parse.replaceFirst("^[0-9]{4}[0-9]{2}[0-9]{2}", "yyyyMMdd");
		parse = parse.replaceFirst("^[0-9]{4}-[0-9]{1,2}-[0-9]{1,2}",
				"yyyy-MM-dd");
		parse = parse.replaceFirst("^[0-9]{4}/[0-9]{1,2}/[0-9]{1,2}",
				"yyyy/MM/dd");
		parse = parse.replaceFirst("^[0-9]{4}年[0-9]{1,2}月[0-9]{1,2}日",
				"yyyy年MM月dd日");
		parse = parse.replaceFirst("^[0-9]{1,2}-[0-9]{1,2}-[0-9]{4}",
				"MM-dd-yyyy");
		parse = parse.replaceFirst("^[0-9]{1,2}/[0-9]{1,2}/[0-9]{4}",
				"MM/dd/yyyy");
		parse = parse.replaceFirst("^[0-9]{1,2}月[0-9]{1,2}日[0-9]{4}年",
				"MM月dd日yyyy年");
		Integer year = 0, month = 0, day = 0;
		Integer monthIndex, dayIndex, yearIndex;
		if (parse.equalsIgnoreCase("yyyyMMdd"))
		{
			year = Integer.parseInt(dateStr.substring(0, 4));
			month = Integer.parseInt(dateStr.substring(4, 6));
			day = Integer.parseInt(dateStr.substring(6, 8));
		} else if (parse.equalsIgnoreCase("yyyy-MM-dd"))
		{
			monthIndex = dateStr.indexOf("-");
			dayIndex = dateStr.indexOf("-", monthIndex + 1);
			if (monthIndex != -1 && dayIndex != -1)
			{
				year = Integer.parseInt(dateStr.substring(0, monthIndex));
				month = Integer.parseInt(dateStr.substring(monthIndex + 1,
						dayIndex));
				day = Integer.parseInt(dateStr.substring(dayIndex + 1,
						dateStr.length()));
			}
		} else if (parse.equalsIgnoreCase("yyyy/MM/dd"))
		{
			monthIndex = dateStr.indexOf("/");
			dayIndex = dateStr.indexOf("/", monthIndex + 1);
			if (monthIndex != -1 && dayIndex != -1)
			{
				year = Integer.parseInt(dateStr.substring(0, monthIndex));
				month = Integer.parseInt(dateStr.substring(monthIndex + 1,
						dayIndex));
				day = Integer.parseInt(dateStr.substring(dayIndex + 1,
						dateStr.length()));
			}
		} else if (parse.equalsIgnoreCase("yyyy年MM月dd日"))
		{
			monthIndex = dateStr.indexOf("年");
			dayIndex = dateStr.indexOf("月", monthIndex + 1);
			if (monthIndex != -1 && dayIndex != -1)
			{
				year = Integer.parseInt(dateStr.substring(0, monthIndex));
				month = Integer.parseInt(dateStr.substring(monthIndex + 1,
						dayIndex));
				day = Integer.parseInt(dateStr.substring(dayIndex + 1,
						dateStr.length() - 1));
			}
		} else if (parse.equalsIgnoreCase("MM-dd-yyyy"))
		{
			dayIndex = dateStr.indexOf("-");
			yearIndex = dateStr.indexOf("-", dayIndex + 1);
			if (yearIndex != -1 && dayIndex != -1)
			{
				month = Integer.parseInt(dateStr.substring(0, dayIndex));
				day = Integer.parseInt(dateStr.substring(dayIndex + 1,
						yearIndex));
				year = Integer.parseInt(dateStr.substring(yearIndex + 1,
						dateStr.length()));
			}
		} else if (parse.equalsIgnoreCase("MM/dd/yyyy"))
		{
			dayIndex = dateStr.indexOf("/");
			yearIndex = dateStr.indexOf("/", dayIndex + 1);
			if (yearIndex != -1 && dayIndex != -1)
			{
				month = Integer.parseInt(dateStr.substring(0, dayIndex));
				day = Integer.parseInt(dateStr.substring(dayIndex + 1,
						yearIndex));
				year = Integer.parseInt(dateStr.substring(yearIndex + 1,
						dateStr.length()));
			}
		} else if (parse.equalsIgnoreCase("MM月dd日yyyy年"))
		{
			dayIndex = dateStr.indexOf("月");
			yearIndex = dateStr.indexOf("日", dayIndex + 1);
			if (yearIndex != -1 && dayIndex != -1)
			{
				month = Integer.parseInt(dateStr.substring(0, dayIndex));
				day = Integer.parseInt(dateStr.substring(dayIndex + 1,
						yearIndex));
				year = Integer.parseInt(dateStr.substring(yearIndex + 1,
						dateStr.length() - 1));
			}
		} else
		{
			return null;
		}

		if (year.intValue() <= 0 || month.intValue() > 12
				|| month.intValue() < 1 || day.intValue() < 1
				|| day.intValue() > 31)
		{
			return null;
		}
		try
		{
			SimpleDateFormat format = new SimpleDateFormat(parse);
			result = format.parse(dateStr);
			Calendar cal = Calendar.getInstance();
			cal.setTime(result);
			if (cal.get(Calendar.YEAR) < 0)
				return null;
			if (day != null && cal.get(Calendar.DATE) != day.intValue())
				return null;
		} catch (Exception e)
		{
			return null;
		}
		return result;
	}

	/**
	 * 计算导入批次
	 * 
	 * @param sumCount
	 *            总记录数
	 * @return
	 */
	public static Integer getBatchSize(Integer sumCount)
	{
		double defaultMin = 10.0;
		double defaultMax = 1000.0;
		if (sumCount < defaultMin)
			return 1;
		else if (Math.ceil((sumCount / defaultMin)) > defaultMax)
			return (int) defaultMax;
		else
			return (int) Math.floor(sumCount / defaultMin);

	}

	/**
	 * 总运行次数
	 * 
	 * @param sumCount
	 *            总记录数
	 * @param batchSzie
	 *            批次大小
	 * @return
	 */
	public static Integer getBathchCount(Integer sumCount, Integer batchSzie)
	{
		return (int) Math.ceil(sumCount / (double) batchSzie);
	}

	/**
	 ** 核心数据处理
	 * 
	 * @param batchSize
	 *            批次数
	 * @param beginSize
	 *            起始数
	 * @param dt
	 *            包装集合
	 */
	public static void BatchProcess(Integer batchSize, Integer beginSize,
			DataTable dt, List<CrmModuleExport> crmModuleList,
			CrmCustomerTransfer cct)
	{
		HttpServletRequest request = org.apache.struts2.ServletActionContext
				.getRequest();
		Map<String, String> pMap = new HashMap<String, String>();
		Map<String, String> deptCodeMap = new HashMap<String, String>();
		String coverRecord = request.getParameter("coverRecord");// 记录是否覆盖
		CrmModuleExport importCombox = null;
		DataPackage cidp = new DataPackage();
		List<CrmCustomerExtBean> extBeanList = new ArrayList<CrmCustomerExtBean>();
		CrmCustomerExtBean extBean = null;
		String sb = "";
		int currentNumber = 0;
		int currentError = Integer.parseInt(cct.getParameterMap().get(
				"erroRecord"));
		try
		{
			for (int numSheet = 0; numSheet < cct.getWorkbook()
					.getNumberOfSheets(); numSheet++)
			{
				Sheet sheet = cct.getWorkbook().getSheetAt(numSheet);
				if (sheet == null)
				{
					continue;
				}
				for (int rowNum = beginSize + 1; rowNum <= sheet
						.getLastRowNum(); rowNum++)
				{
					extBean = new CrmCustomerExtBean();
					if (currentNumber == batchSize)
					{
						break;
					}
					Row wRow = sheet.getRow(rowNum);
					if (isBlankRow(wRow))
					{
						continue;
					} else
					{
						DataRow row = null;
						row = dt.newRow();
						row.set("rowNo", rowNum);
						for (int cellNum = 0 + currentError; cellNum <= wRow
								.getLastCellNum(); cellNum++)
						{
							Cell hssfCell = wRow.getCell(cellNum);
							String cols = request.getParameter("combox"
									+ (cellNum - currentError));
							String value = "";
							if (hssfCell != null)
							{
								value = getValue(hssfCell).replaceAll(" ", "");
							}
							String cover = request.getParameter("checkbox"
									+ (cellNum - currentError)); // 是否需要覆盖
							if (cols != null && !cols.equals(""))
							{
								String val = "no";
								if (cover != null && !cover.equals(""))
								{
									val = "yes";
								}
								row.set(cols, value + "," + val);
								if (cols.equals("customerNo"))
								{
									if (!value.equals(""))
										sb += "'" + value + "',";
								}
								if (cols.equals("belongUserId"))
								{
									pMap.put("" + rowNum, value);
								}
								if (cols.equals("belongDeptId"))
								{
									deptCodeMap.put("" + rowNum, value);
								}
								if (coverRecord == null)
								{// 覆盖时为不选中时。
									cidp.getCustomizedFields(value, extBean,
											cols, cct);
								} else
								{
									if (val.equals("yes"))
									{
										cidp.getCustomizedFields(value,
												extBean, cols, cct);
									}
								}
							} else if (cols != null && cols.equals(""))
							{
								row.set((cellNum + 2), value + ",no");
							}
							if (crmModuleList != null)
							{
								if (rowNum == 1)
								{// 保存用户勾选的配制
									importCombox = new CrmModuleExport();
									if (cols != null && !cols.equals(""))
									{
										importCombox.setFeildName(cols);
									} else
									{
										importCombox.setFeildName("");
									}
									importCombox.setModuleName("combox"
											+ (cellNum - currentError));
									importCombox.setUserId(getUserLoginInfo()
											.getUserId());
									importCombox
											.setCreateUser(getUserLoginInfo()
													.getUserId());
									importCombox
											.setUpdateUser(getUserLoginInfo()
													.getUserId());
									crmModuleList.add(importCombox);
									if (coverRecord != null)
									{
										importCombox = new CrmModuleExport();
										if (cover != null && !cover.equals(""))
										{
											importCombox.setFeildName(cover);
										} else
										{
											importCombox.setFeildName("");
										}
										importCombox.setModuleName("checkbox"
												+ cellNum);
										importCombox
												.setUserId(getUserLoginInfo()
														.getUserId());
										importCombox
												.setCreateUser(getUserLoginInfo()
														.getUserId());
										importCombox
												.setUpdateUser(getUserLoginInfo()
														.getUserId());
										crmModuleList.add(importCombox);
									}
								}
							}
						}
						row.set("templateIds", extBean.getTemplateIds());
						extBean.setLineNumber("" + rowNum);
						if (rowNum != 0)
							extBeanList.add(extBean);
						currentNumber++;
					}
				}
			}
			String temp = "";
			if (!sb.equals(""))
				temp = sb.substring(0, sb.length() - 1);
			if (!StringUtil.isEmpty(temp))
				cct.setCustomerNoStr(temp);// 保存取出的客户编号
			cct.setExtList(extBeanList);
			cct.setAccountMap(pMap);
			cct.setDeptCodeMap(deptCodeMap);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 ** 购买客户核心数据处理
	 * 
	 * @param batchSize
	 *            批次数
	 * @param beginSize
	 *            起始数
	 * @param dt
	 *            包装集合
	 */
	public static void BatchProcess(Integer batchSize, Integer beginSize,
			DataTable dt, CrmCustomerTransfer cct)
	{
		HttpServletRequest request = org.apache.struts2.ServletActionContext
				.getRequest();
		String sb = "";
		int currentNumber = 0;
		int currentError = Integer.parseInt(cct.getParameterMap().get(
				"erroRecord"));
		try
		{
			for (int numSheet = 0; numSheet < cct.getWorkbook()
					.getNumberOfSheets(); numSheet++)
			{
				Sheet sheet = cct.getWorkbook().getSheetAt(numSheet);
				if (sheet == null)
				{
					continue;
				}
				for (int rowNum = beginSize + 1; rowNum <= sheet
						.getLastRowNum(); rowNum++)
				{
					if (currentNumber == batchSize)
					{
						break;
					}
					Row wRow = sheet.getRow(rowNum);
					if (isBlankRow(wRow))
					{
						currentNumber++;
						continue;
					} else
					{
						DataRow row = null;
						row = dt.newRow();
						row.set("rowNo", rowNum);
						for (int cellNum = 0 + currentError; cellNum <= wRow
								.getLastCellNum(); cellNum++)
						{
							Cell hssfCell = wRow.getCell(cellNum);
							String cols = request.getParameter("combox"
									+ (cellNum - currentError));
							String value = "";
							if (hssfCell != null)
							{
								value = getValue(hssfCell).replaceAll(" ", "");
							}

							if (cols != null && !cols.equals(""))
							{
								row.set(cols, value);
								if (cols.equals("customerNo"))
								{
									if (!value.equals(""))
										sb += "'" + value + "',";
								}
								if (cols.equals("account"))
								{
									cct.setAccount(value);
								}
							}
						}
						currentNumber++;
					}
				}
			}
			String temp = "";
			if (!sb.equals(""))
				temp = sb.substring(0, sb.length() - 1);
			if (!StringUtil.isEmpty(temp))
				cct.setCustomerNoStr(temp);// 保存取出的客户编号
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 ** 导入白名单核心数据处理
	 * 
	 * @param batchSize
	 *            批次数
	 * @param beginSize
	 *            起始数
	 * @param dt
	 *            包装集合
	 */
	public static void BatchProcessWhite(Integer batchSize, Integer beginSize,
			DataTable dt, CrmCustomerTransfer cct)
	{
		int currentNumber = 0;
		String sb = "";
		int currentError = Integer.parseInt(cct.getParameterMap().get(
				"erroRecord"));
		try
		{
			for (int numSheet = 0; numSheet < cct.getWorkbook()
					.getNumberOfSheets(); numSheet++)
			{
				Sheet sheet = cct.getWorkbook().getSheetAt(numSheet);
				if (sheet == null)
				{
					continue;
				}
				for (int rowNum = beginSize + 1; rowNum <= sheet
						.getLastRowNum(); rowNum++)
				{
					if (currentNumber == batchSize)
					{
						break;
					}
					Row wRow = sheet.getRow(rowNum);
					if (isBlankRow(wRow))
					{
						currentNumber++;
						continue;
					} else
					{
						DataRow row = null;
						row = dt.newRow();
						row.set("rowNo", rowNum);
						for (int cellNum = 0 + currentError; cellNum <= wRow
								.getLastCellNum(); cellNum++)
						{
							Cell hssfCell = wRow.getCell(cellNum);
							String value = "";
							String cols = "cols" + cellNum;
							if (hssfCell != null)
							{
								value = getValue(hssfCell).replaceAll(" ", "");
								row.set(cols, value);
								if (cols.equals("cols1"))
								{
									if (!value.equals(""))
										sb += "'" + value + "',";
								}
							}
						}
						currentNumber++;
					}
				}
			}
			String temp = "";
			if (!sb.equals(""))
				// 去掉第一个"'",最后的"',"
				temp = sb.substring(1, sb.length() - 2);
			if (!StringUtil.isEmpty(temp))
				cct.setCustomerNoStr(temp);// 如果“联系电话”在数据库中已经存在，则导入时，直接更新姓名
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 ** 导入任务客户核心数据处理
	 * 
	 * @param batchSize
	 *            批次数
	 * @param beginSize
	 *            起始数
	 * @param dt
	 *            包装集合
	 */
	public static void BatchProcessTarget(Integer batchSize, Integer beginSize,
			DataTable dt, CrmCustomerTransfer cct)
	{
		HttpServletRequest request = org.apache.struts2.ServletActionContext
				.getRequest();
		int currentNumber = 0;
		int currentError = Integer.parseInt(cct.getParameterMap().get(
				"erroRecord"));
		try
		{
			for (int numSheet = 0; numSheet < cct.getWorkbook()
					.getNumberOfSheets(); numSheet++)
			{
				Sheet sheet = cct.getWorkbook().getSheetAt(numSheet);
				if (sheet == null)
				{
					continue;
				}
				for (int rowNum = beginSize + 1; rowNum <= sheet
						.getLastRowNum(); rowNum++)
				{
					if (currentNumber == batchSize)
					{
						break;
					}
					Row wRow = sheet.getRow(rowNum);
					if (isBlankRow(wRow))
					{
						currentNumber++;
						continue;
					} else
					{
						DataRow row = null;
						row = dt.newRow();
						row.set("rowNo", rowNum);
						for (int cellNum = 0 + currentError; cellNum <= wRow
								.getLastCellNum(); cellNum++)
						{
							String cols = request.getParameter("combox"
									+ (cellNum - currentError));
							if (cols != null && cols != "")
							{
								Cell hssfCell = wRow.getCell(cellNum);
								String value = "";
								if (hssfCell != null)
								{
									value = getValue(hssfCell).replaceAll(" ",
											"");
									row.set(cols, value);
								}
							} else if (cols != null)
							{
								Cell hssfCell = wRow.getCell(cellNum);
								if (hssfCell != null)
									row.set("" + (cellNum + 2),
											getValue(hssfCell).replaceAll(" ",
													""));
							}
						}
						currentNumber++;
					}
				}
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 ** 导入任务分配数据处理
	 * 
	 * @param batchSize
	 *            批次数
	 * @param beginSize
	 *            起始数
	 * @param dt
	 *            包装集合
	 */
	public static void TaskAlloCatProcess(Integer batchSize, Integer beginSize,
			DataTable dt, CrmCustomerTransfer cct)
	{
		HttpServletRequest request = org.apache.struts2.ServletActionContext
				.getRequest();
		int currentNumber = 0;
		int currentError = Integer.parseInt(cct.getParameterMap().get(
				"erroRecord"));
		try
		{
			for (int numSheet = 0; numSheet < cct.getWorkbook()
					.getNumberOfSheets(); numSheet++)
			{
				Sheet sheet = cct.getWorkbook().getSheetAt(numSheet);
				if (sheet == null)
				{
					continue;
				}
				for (int rowNum = beginSize + 1; rowNum <= sheet
						.getLastRowNum(); rowNum++)
				{
					if (currentNumber == batchSize)
					{
						break;
					}
					Row wRow = sheet.getRow(rowNum);
					if (isBlankRow(wRow))
					{
						currentNumber++;
						continue;
					} else
					{
						DataRow row = null;
						row = dt.newRow();
						row.set("rowNo", rowNum);
						for (int cellNum = 0 + currentError; cellNum <= wRow
								.getLastCellNum(); cellNum++)
						{
							String cols = request.getParameter("combox"
									+ (cellNum - currentError));
							if (cols != null && cols != "")
							{
								Cell hssfCell = wRow.getCell(cellNum);
								String value = "";
								if (hssfCell != null)
								{
//									value = getNumbericValue(hssfCell).replaceAll(" ", "");
									value = getNumbericValue(hssfCell).trim();
									row.set(cols, value);
								}
							} else if (cols != null && cols == "")
							{
								Cell hssfCell = wRow.getCell(cellNum);
								if (hssfCell != null)
//									row.set("" + (cellNum + 2),getNumbericValue(hssfCell).replaceAll(" ", ""));
								    row.set("" + (cellNum + 2),getNumbericValue(hssfCell).trim());
							}
						}
						currentNumber++;
					}
				}
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	@SuppressWarnings("static-access")
	private static String getNumbericValue(Cell cell)
	{
		DecimalFormat df = new DecimalFormat("#");
		DecimalFormat dfDu = new DecimalFormat("#.#");
		if (cell.getCellType() == cell.CELL_TYPE_BOOLEAN)
		{
			return String.valueOf(cell.getBooleanCellValue());
		} else if (cell.getCellType() == cell.CELL_TYPE_NUMERIC)
		{
			if (((int) cell.getNumericCellValue()) != cell
					.getNumericCellValue())
			{
				return String.valueOf(dfDu.format(cell.getNumericCellValue()));
			}
			return String.valueOf(df.format(cell.getNumericCellValue()));
		} else
		{
			return String.valueOf(cell.getStringCellValue());
		}

	}

	/**
	 * 取得整个购买产品EXECL中所有客户编号
	 * 
	 * @param cct
	 * @return
	 */
	public static void getEntireCustomerNo(CrmCustomerTransfer cct)
	{
		HttpServletRequest request = org.apache.struts2.ServletActionContext
				.getRequest();
		HashMap<BuyCustomerBean, String> map = new HashMap<BuyCustomerBean, String>();
		Integer erroRecord = Integer.parseInt(cct.getParameterMap().get(
				"erroRecord"));// 导入表格中是否有序号、失败原因列
		String str = "";
		BuyCustomerBean buyCustomer = null;
		try
		{
			for (int numSheet = 0; numSheet < cct.getWorkbook()
					.getNumberOfSheets(); numSheet++)
			{
				Sheet sheet = cct.getWorkbook().getSheetAt(numSheet);
				if (sheet == null)
				{
					continue;
				}
				for (int rowNum = 1; rowNum <= sheet.getLastRowNum(); rowNum++)
				{
					Row wRow = sheet.getRow(rowNum);
					str = "";
					buyCustomer = new BuyCustomerBean();
					if (isBlankRow(wRow))
					{
						continue;
					} else
					{
						for (int cellNum = erroRecord; cellNum < wRow
								.getLastCellNum(); cellNum++)
						{
							Cell hssfCell = wRow.getCell(cellNum);
							String cols = request.getParameter("combox"
									+ (cellNum - erroRecord));
							String val = "";
							if (hssfCell != null)
							{
								val = getValue(hssfCell).replaceAll(" ", "");
							}
							if (cols != null && !cols.equals(""))
							{
								if (cols.equals("customerNo"))
								{
									DataPackage.getCustomerNo(val, buyCustomer);
								}
								if (cols.equals("account"))
								{
									DataPackage.getAccount(val, buyCustomer);
								}
								if (cols.equals("customerName"))
								{
									DataPackage.getCustomerName(val,
											buyCustomer);
								}
								if (cols.equals("phone"))
								{
									DataPackage.getPhone(val, buyCustomer);
								}
								if (cols.equals("idCard"))
								{
									DataPackage.getIdCard(val, buyCustomer);
								}
								if (cols.equals("buyDate"))
								{
									DataPackage.getBuyDate(val, buyCustomer);
								}
								if (cols.equals("buyMoney"))
								{
									DataPackage.getBuyMoney(val, buyCustomer);
								}
								if (cols.equals("bankAccount"))
								{
									DataPackage
											.getBankAccount(val, buyCustomer);
								}
							}
						}
						if (map.get(buyCustomer) == null)
						{
							map.put(buyCustomer, "" + rowNum);
						} else
						{
							str = map.get(buyCustomer) + "," + rowNum;
							map.put(buyCustomer, str);
						}
					}
				}
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		cct.setEntireCustomerNoMap(map);
	}

	/**
	 * 获得登录信息
	 * 
	 * @return
	 */
	private static IUserInfo getUserLoginInfo()
	{
		HttpServletRequest req = org.apache.struts2.ServletActionContext
				.getRequest();
		HttpSession session = req.getSession();
		return (IUserInfo) session.getAttribute("LoginInfo");
	}

	/**
	 * 检验excel文件中格式,长度
	 * 
	 * @param dt
	 * @param map
	 * @return
	 */
	public static void getCheckExeclInfo(DataTable dt, Map<String, String> map,
			CrmCustomerTransfer cct)
	{
		String formatStr = "";
		String langthStr = "";
		String failStr = "";
		for (int i = 1; i < dt.rowSize(); i++)
		{
			failStr = "";
			langthStr = "";
			formatStr = "";
			String str = "";
			DataRow dataRow = dt.getRow(i);
			if (i == 0)
			{
				continue;
			} else
			{
				str = ImportUtil.checkStrNull(dataRow.get("customerNo")).split(
						",")[0];
				if (!StringUtil.isEmpty(str))
				{
					if (checkLength(str, 20))
					{
						langthStr += "\"" + map.get("customerNo") + "\",";
					}
					if (cct.getRepeatCustomerMap() != null)
					{
						dataRow.set("customerId", cct.getRepeatCustomerMap()
								.get(str));
					}
					if (cct.getCustomerCodeMap().containsKey(str))
					{
						dataRow.set("errorMsg",
								EnumCustomer.IMPORT_CUSTOMER_NOT_COVER
										.getValue());
						continue;
					}
					cct.getCustomerCodeMap().put(str, "");
				}
				str = ImportUtil.checkStrNull(dataRow.get("customerName"))
						.split(",")[0];
				if (!StringUtil.isEmpty(str))
				{
					if (checkLength(str, 20))
					{
						langthStr += "\"" + map.get("customerName") + "\",";
					}
				} else
				{
					failStr = EnumCustomer.IMPORT_CRMCUSTOMER_NAME_NOT_NULL
							.getValue();
					dataRow.set("errorMsg", failStr);
					continue;
				}
				str = ImportUtil.checkStrNull(dataRow.get("sex")).split(",")[0];
				if (!StringUtil.isEmpty(str))
				{
					if (checkLength(str, 1))
					{
						langthStr += "\"" + map.get("sex") + "\",";
					}
				}
				str = ImportUtil.checkStrNull(dataRow.get("customerTitle"))
						.split(",")[0];
				if (!StringUtil.isEmpty(str))
				{
					if (checkLength(str, 22))
					{
						langthStr += "\"" + map.get("customerTitle") + "\",";
					}
				}
				str = ImportUtil.checkStrNull(dataRow.get("birthday")).split(
						",")[0];
				if (!StringUtil.isEmpty(str))
				{
					Date date = parseAllStringToDate(str);
					if (date == null)
					{
						formatStr += "\"" + map.get("birthday") + "\",";
					}
				}
				str = ImportUtil.checkStrNull(dataRow.get("idCard")).split(",")[0];
				if (!StringUtil.isEmpty(str))
				{
					if (checkLength(str, 20))
					{
						langthStr += "\"" + map.get("idCard") + "\",";
					}
				}
				str = ImportUtil.checkStrNull(dataRow.get("company"))
						.split(",")[0];
				if (!StringUtil.isEmpty(str))
				{
					if (checkLength(str, 50))
					{
						langthStr += "\"" + map.get("company") + "\",";
					}
				}
				str = ImportUtil.checkStrNull(dataRow.get("remark")).split(",")[0];
				if (!StringUtil.isEmpty(str))
				{
					if (checkLength(str, 2000))
					{
						langthStr += "\"" + map.get("remark") + "\",";
					}
				}
				str = ImportUtil.checkStrNull(dataRow.get("address"))
						.split(",")[0];
				if (!StringUtil.isEmpty(str))
				{
					if (checkLength(str, 50))
					{
						langthStr += "\"" + map.get("address") + "\",";
					}
				}
				str = ImportUtil.checkStrNull(dataRow.get("mobilePhone1"))
						.split(",")[0];
				if (!StringUtil.isEmpty(str))
				{
					if (checkLength(str, 11))
					{
						langthStr += "\"" + map.get("mobilePhone1") + "\",";
					} else
					{
						if (isPhoneOrMobilePhone(str) != 1)
						{
							formatStr += "\"" + map.get("mobilePhone1") + "\",";
						}
					}
				}
				str = ImportUtil.checkStrNull(dataRow.get("mobilePhone2"))
						.split(",")[0];
				if (!StringUtil.isEmpty(str))
				{
					if (checkLength(str, 11))
					{
						langthStr += "\"" + map.get("mobilePhone2") + "\",";
					} else if (isPhoneOrMobilePhone(str) != 1)
					{
						formatStr += "\"" + map.get("mobilePhone2") + "\",";
					}
				}
				str = ImportUtil.checkStrNull(dataRow.get("phone")).split(",")[0];
				if (!StringUtil.isEmpty(str))
				{
					if (checkLength(str, 20))
					{
						langthStr += "\"" + map.get("phone") + "\",";
					} else
					{
						if (isPhoneOrMobilePhone(str) == 3)
						{
							formatStr += "\"" + map.get("phone") + "\",";
						}
					}
				}
				str = ImportUtil.checkStrNull(dataRow.get("phoneExt")).split(
						",")[0];
				if (!StringUtil.isEmpty(str))
				{
					if (checkLength(str, 10))
					{
						langthStr += "\"" + map.get("phoneExt") + "\",";
					}
				}
				str = ImportUtil.checkStrNull(dataRow.get("fax")).split(",")[0];
				if (!StringUtil.isEmpty(str))
				{
					if (checkLength(str, 20))
					{
						langthStr += "\"" + map.get("fax") + "\",";
					} else
					{
						if (isPhoneOrMobilePhone(str) == 3)
						{
							formatStr += "\"" + map.get("fax") + "\",";
						}
					}
				}
				str = ImportUtil.checkStrNull(dataRow.get("faxExt")).split(",")[0];
				if (!StringUtil.isEmpty(str))
				{
					if (checkLength(str, 10))
					{
						langthStr += "\"" + map.get("faxExt") + "\",";
					}
				}
				str = ImportUtil.checkStrNull(dataRow.get("email")).split(",")[0];
				if (!StringUtil.isEmpty(str))
				{
					if (checkLength(str, 50))
					{
						langthStr += "\"" + map.get("email") + "\",";
					} else
					{
						if (!isEmail(str))
							formatStr += "\"" + map.get("email") + "\",";
					}
				}
				str = ImportUtil.checkStrNull(dataRow.get("belongDeptId"))
						.split(",")[0];
				if (!StringUtil.isEmpty(str))
				{
					if (checkLength(str, 20))
					{
						langthStr += "\"" + map.get("belongDeptId") + "\",";
					} else
					{
						cct.getParameterMap().put("belongDeptId", str);
					}
				}
				str = ImportUtil.checkStrNull(dataRow.get("belongUserId"))
						.split(",")[0];
				if (!StringUtil.isEmpty(str))
				{
					if (checkLength(str, 20))
					{
						langthStr += "\"" + map.get("belongUserId") + "\",";
					} else
					{
						cct.getParameterMap().put("belongUserId", str);
					}
				}
			}
			if (!StringUtil.isEmpty(langthStr))
			{// 判断长度过长的字段
				langthStr = langthStr.substring(0, langthStr.length() - 1);
				if (!StringUtil.isEmpty(formatStr))
				{// 验证字段格式错误
					formatStr = formatStr.substring(0, formatStr.length() - 1);
					str = langthStr
							+ EnumCustomer.IMPORT_EXCEEDFIELD.getValue() + ","
							+ formatStr
							+ EnumCustomer.IMPORT_FORMATFIELD.getValue();
				} else
				{
					str = langthStr
							+ EnumCustomer.IMPORT_EXCEEDFIELD.getValue();
				}
				dataRow.set("errorMsg", str);
				continue;
			}
			if (!StringUtil.isEmpty(formatStr))
			{
				formatStr = formatStr.substring(0, formatStr.length() - 1);
				str = formatStr + EnumCustomer.IMPORT_FORMATFIELD.getValue();
				dataRow.set("errorMsg", str);
				continue;
			}
		}
	}

	/**
	 * 检验excel文件中格式,长度
	 * 
	 * @param dt
	 * @param "\""+map
	 * @return
	 */
	public static void getCheckBuyCustomerInfo(DataTable dt,
			Map<String, String> map, CrmCustomerTransfer cct)
	{
		String formatStr = "";
		String langthStr = "";
		String nullStr = "";
		for (int i = 1; i < dt.rowSize(); i++)
		{
			langthStr = "";
			formatStr = "";
			String str = "";
			nullStr = "";
			DataRow dataRow = dt.getRow(i);
			if (i == 0)
			{
				continue;
			} else
			{
				str = ImportUtil.checkStrNull(dataRow.get("customerNo"));
				if (!StringUtil.isEmpty(str))
				{
					if (checkLength(str, 20))
					{
						langthStr += "\"" + map.get("customerNo") + "\",";
					}
					if (cct.getRepeatCustomerMap() != null)
					{
						dataRow.set("customerId", cct.getRepeatCustomerMap()
								.get(str));
					}
				} else
				{
					nullStr += "\"" + map.get("customerNo") + "\",";
				}
				str = ImportUtil.checkStrNull(dataRow.get("customerName"));
				if (!StringUtil.isEmpty(str))
				{
					if (checkLength(str, 20))
					{
						langthStr += "\"" + map.get("customerName") + "\",";
					}
				} else
				{
					nullStr += "\"" + map.get("customerName") + "\",";
				}
				str = ImportUtil.checkStrNull(dataRow.get("idCard"));
				if (!StringUtil.isEmpty(str))
				{
					if (checkLength(str, 20))
					{
						langthStr += map.get("idCard") + "\",";
					}
				} else
				{
					nullStr += "\"" + map.get("idCard") + "\",";
				}
				str = ImportUtil.checkStrNull(dataRow.get("phone"));
				if (!StringUtil.isEmpty(str))
				{
					if (checkLength(str, 20))
					{
						langthStr += "\"" + map.get("phone") + "\",";
					} else
					{
						if (isPhoneOrMobilePhone(str) == 3)
						{
							formatStr += "\"" + map.get("phone") + "\",";
						}
					}
				} else
				{
					nullStr += "\"" + map.get("phone") + "\",";
				}
				str = ImportUtil.checkStrNull(dataRow.get("bankAccount"));
				if (!StringUtil.isEmpty(str))
				{
					if (checkLength(str, 20))
					{
						langthStr += "\"" + map.get("bankAccount") + "\",";
					}
				} else
				{
					nullStr += "\"" + map.get("bankAccount") + "\",";
				}
				str = ImportUtil.checkStrNull(dataRow.get("buyMoney"));
				if (!StringUtil.isEmpty(str))
				{
					if (isFloat(str))
					{
						String[] arr = str.replace('.', '<').split("<");
						if (checkLength(arr[0], 20))
						{
							langthStr += "\"" + map.get("buyMoney") + "\",";
						}
					} else
					{
						formatStr += "\"" + map.get("buyMoney") + "\",";
					}
				} else
				{
					nullStr += "\"" + map.get("buyMoney") + "\",";
				}
				str = ImportUtil.checkStrNull(dataRow.get("buyDate"));
				if (!StringUtil.isEmpty(str))
				{
					Date date = parseAllStringToDate(str);
					if (!(date != null && !date.toLocaleString().contains(
							"1970")))
					{
						formatStr += "\"" + map.get("buyDate") + "\",";
					}
				} else
				{
					nullStr += "\"" + map.get("buyDate") + "\",";
				}
				str = ImportUtil.checkStrNull(dataRow.get("account"));
				if (!StringUtil.isEmpty(str))
				{
					if (checkLength(str, 20))
					{
						langthStr += "\"" + map.get("account") + "\",";
					}
				} else
				{
					nullStr += "\"" + map.get("account") + "\",";
				}
			}
			if (!StringUtil.isEmpty(nullStr))
			{// 非空验证
				nullStr = nullStr.substring(0, nullStr.length() - 1);
				str = EnumProductType.DATANOTCOMPLETE.getValue() + nullStr
						+ EnumProductType.COLS.getValue();
				dataRow.set("errorMsg", str);
				continue;
			}
			if (!StringUtil.isEmpty(langthStr))
			{// 判断长度过长的字段
				langthStr = langthStr.substring(0, langthStr.length() - 1);
				if (!StringUtil.isEmpty(formatStr))
				{// 验证字段格式错误
					formatStr = formatStr.substring(0, formatStr.length() - 1);
					str = langthStr
							+ EnumCustomer.IMPORT_EXCEEDFIELD.getValue() + ","
							+ formatStr
							+ EnumCustomer.IMPORT_FORMATFIELD.getValue();
				} else
				{
					str = langthStr
							+ EnumCustomer.IMPORT_EXCEEDFIELD.getValue();
				}
				dataRow.set("errorMsg", str);
				continue;
			}
			if (!StringUtil.isEmpty(formatStr))
			{
				formatStr = formatStr.substring(0, formatStr.length() - 1);
				str = formatStr + EnumCustomer.IMPORT_FORMATFIELD.getValue();
				dataRow.set("errorMsg", str);
				continue;
			}
		}
	}

	/**
	 * 检验excel文件中格式,长度
	 * 
	 * @param dt
	 * @param "\""+map
	 * @return
	 */
	public static void getCheckSysWhite(DataTable dt, CrmCustomerTransfer cct)
	{
		String formatStr = "";
		String langthStr = "";
		String nullStr = "";
		for (int i = 1; i < dt.rowSize(); i++)
		{
			langthStr = "";
			formatStr = "";
			String str = "";
			nullStr = "";
			DataRow dataRow = dt.getRow(i);
			if (i == 0)
			{
				continue;
			} else
			{
				str = ImportUtil.checkStrNull(dataRow.get("cols0"));
				if (!StringUtil.isEmpty(str))
				{
					if (checkLength(str, 20))
					{
						langthStr += "\"姓名\",";
					}
				}
				str = ImportUtil.checkStrNull(dataRow.get("cols1"));
				if (!StringUtil.isEmpty(str))
				{
					if (checkLength(str, 20))
					{
						langthStr += "\"联系电话\",";
					} else
					{
						if (isPhoneOrMobilePhone(str) == 3)
						{
							formatStr += "\"联系电话\",";
						}
					}
					if (cct.getRepeatCustomerMap() != null)
					{
						dataRow.set("whiteListId", cct.getRepeatCustomerMap()
								.get(str));
					}
				} else
				{
					nullStr += "\"联系电话\",";
				}
			}
			if (!StringUtil.isEmpty(nullStr))
			{// 非空验证
				nullStr = nullStr.substring(0, nullStr.length() - 1);
				str = nullStr + EnumProductType.SYSW_NOTNULL.getValue();
				dataRow.set("errorMsg", str);
				continue;
			}
			if (!StringUtil.isEmpty(langthStr))
			{// 判断长度过长的字段
				langthStr = langthStr.substring(0, langthStr.length() - 1);
				if (!StringUtil.isEmpty(formatStr))
				{// 验证字段格式错误
					formatStr = formatStr.substring(0, formatStr.length() - 1);
					str = langthStr + EnumProductType.SYSW_LOGSTR.getValue()
							+ "," + formatStr
							+ EnumProductType.SYSW_NOTNUMBER.getValue();
				} else
				{
					str = langthStr + EnumProductType.SYSW_LOGSTR.getValue();
				}
				dataRow.set("errorMsg", str);
				continue;
			}
			if (!StringUtil.isEmpty(formatStr))
			{
				formatStr = formatStr.substring(0, formatStr.length() - 1);
				str = formatStr + EnumProductType.SYSW_NOTNUMBER.getValue();
				dataRow.set("errorMsg", str);
				continue;
			}
		}
	}

	/**
	 * 导入任务客户格式验证
	 * 
	 * @param dt
	 * @param cct
	 */
	public static void getCheckTskCustomer(DataTable dt, CrmCustomerTransfer cct)
	{
		String formatStr = "";
		String langthStr = "";
		String nullStr = "";
		int flag=0;
		for (int i = 1; i < dt.rowSize(); i++)
		{
			langthStr = "";
			formatStr = "";
			String str = "";
			nullStr = "";
			DataRow dataRow = dt.getRow(i);
			if (i == 0)
			{
				continue;
			} else
			{
				str = ImportUtil.checkStrNull(dataRow.get("crmName"));
				if (!StringUtil.isEmpty(str))
				{
					if (checkLength(str, 20))
					{
						langthStr += "\"姓名\",";
					}
				}
				str = ImportUtil.checkStrNull(dataRow.get("phoneNumber"));
				if (!StringUtil.isEmpty(str))
				{
					if (checkLength(str, 20))
					{
						langthStr += "\"联系电话\",";
					} else
					{
						if (isPhoneOrMobilePhone(str) == 3)
						{
							formatStr += "\"联系电话\",";
						}
					}
				} else
				{
					nullStr += "\"联系电话\",";
				}
				str = ImportUtil.checkStrNull(dataRow.get("remark"));
				if (!StringUtil.isEmpty(str))
                {
                    if (checkLength(str, 300))
                    {
                        langthStr += "\"备注\",";
                        flag=1;
                    }
                }
			}
			if (!StringUtil.isEmpty(nullStr))
			{// 非空验证
				nullStr = nullStr.substring(0, nullStr.length() - 1);
				str = nullStr + EnumProductType.SYSW_NOTNULL.getValue();
				dataRow.set("errorMsg", str);
				continue;
			}
			if (!StringUtil.isEmpty(langthStr))
			{// 判断长度过长的字段
				langthStr = langthStr.substring(0, langthStr.length() - 1);
				if (!StringUtil.isEmpty(formatStr))
				{// 验证字段格式错误
					formatStr = formatStr.substring(0, formatStr.length() - 1);
					str = langthStr + EnumProductType.SYSW_LOGSTR.getValue()
							+ "," + formatStr
							+ EnumProductType.SYSW_NOTNUMBER.getValue();
				} else
				{
				    if(flag==0){
				        str = langthStr + EnumProductType.SYSW_LOGSTR.getValue();
				    }else{
				        str = langthStr + EnumProductType.SYS_LANGTH_REMARK.getValue();
				    }
					
				}
				dataRow.set("errorMsg", str);
				continue;
			}
			if (!StringUtil.isEmpty(formatStr))
			{
				formatStr = formatStr.substring(0, formatStr.length() - 1);
				str = formatStr + EnumProductType.SYSW_NOTNUMBER.getValue();
				dataRow.set("errorMsg", str);
				continue;
			}
		}
	}

	/**
	 * 导入任务目标分配格式校验
	 * 
	 * @param dt
	 * @param cct
	 * @param map
	 */
	public static void getCheckAssignTasks(DataTable dt,
			CrmCustomerTransfer cct, Map<String, Object> map)
	{
		String errorMsg = "";
		String dataValue = "";
		for (int i = 1; i < dt.rowSize(); i++)
		{
			DataRow dataRow = dt.getRow(i);
			if (i == 0)
			{
				continue;
			} else
			{
				dataValue = ImportUtil.checkStrNull(dataRow.get("assignOb"));
				if (dataValue == null || dataValue.length() == 0)
				{
					errorMsg = "分配对象不能为空";
					dataRow.set("errorMsg", errorMsg);
				}
				dataValue = ImportUtil.checkStrNull(dataRow.get("assignTa"));
				if (dataValue == null || dataValue.length() == 0)
				{
					errorMsg = "任务目标不能为空";
					dataRow.set("errorMsg", errorMsg);
				} else if (isDoubleNumber(dataValue))
				{
					errorMsg = "任务目标必须为大于零的整数";
					dataRow.set("errorMsg", errorMsg);
				}
			}
		}
	}

	/**
	 * 判断是否正整数
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isDoubleNumber(String str)
	{
		try
		{
			if (isDouble(str))
			{
				return !(new Double(str) > 0 && new Double(str) % 1 == 0);
			} else
			{
				return true;
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return true;
	}

	/**
	 * 取出表头
	 * 
	 * @param dt
	 * @return
	 */
	public static void getExeclHead(DataTable dt)
	{
		try
		{
			HttpServletRequest request = org.apache.struts2.ServletActionContext
					.getRequest();
			String filePath = request.getParameter("filePath");
			Workbook workbook = ReadExcel.getExcelType(filePath);
			Sheet sheet = workbook.getSheetAt(0);
			Row row = sheet.getRow(0);
			DataRow head = dt.newRow();
			Map<String, Integer> recordMap = (Map<String, Integer>) request
					.getSession().getAttribute("recordMap");
			Integer erroRecord = recordMap.get("erroRecord");
			head.set("rowNo", EnumCustomer.IMPORT_NO.getValue());
			head.set("errorMsg", EnumCustomer.REASONS_FOR_FAILURE.getValue());
			for (int cellNum = 0; cellNum <= row.getLastCellNum(); cellNum++)
			{
				String cols = request.getParameter("combox"
						+ (cellNum - erroRecord));
				Cell hssfCell = row.getCell(cellNum);
				if (getValue(hssfCell)
						.equals(EnumCustomer.IMPORT_NO.getValue())
						|| getValue(hssfCell).equals(
								EnumCustomer.REASONS_FOR_FAILURE.getValue()))
				{
					continue;
				}
				if (cols.equals(""))
				{
					dt.addColumn("" + (cellNum + 2));
					if (hssfCell != null)
						head.set("" + (cellNum + 2), getValue(hssfCell)
								.replaceAll(" ", ""));
				} else
				{
					dt.addColumn(cols);
					if (hssfCell != null)
						head.set(cols, getValue(hssfCell).replaceAll(" ", ""));
				}

			}
			head.set("customerId", "");
			head.set("templateIds", "");
			head.set("cover", "");
		} catch (Exception e)
		{
		}
	}

	/**
	 * 取出表头
	 * 
	 * @param filePath
	 * @param dt
	 * @return
	 */
	public static void getExeclHeadWhite(DataTable dt, String filePath)
	{
		try
		{
			Workbook workbook = ReadExcel.getExcelType(filePath);
			Sheet sheet = workbook.getSheetAt(0);
			Row row = sheet.getRow(0);
			DataRow head = dt.newRow();
			head.set("rowNo", EnumCustomer.IMPORT_NO.getValue());
			head.set("errorMsg", EnumCustomer.REASONS_FOR_FAILURE.getValue());
			for (int cellNum = 0; cellNum <= row.getLastCellNum(); cellNum++)
			{
				String cols = "cols" + cellNum;
				dt.addColumn(cols);
				Cell hssfCell = row.getCell(cellNum);
				if (hssfCell != null)
					head.set(cols, getValue(hssfCell).replaceAll(" ", ""));
			}
			head.set("whiteListId", "");
		} catch (Exception e)
		{
		}
	}

	/**
	 * 获取任务客户导入表头
	 * 
	 * @param dt
	 * @param filePath
	 * @param erroRecord
	 */
	public static void getExeclHeadTarget(DataTable dt, String filePath,
			int erroRecord)
	{
		try
		{
			HttpServletRequest request = org.apache.struts2.ServletActionContext
					.getRequest();
			Workbook workbook = ReadExcel.getExcelType(filePath);
			Sheet sheet = workbook.getSheetAt(0);
			Row row = sheet.getRow(0);
			DataRow head = dt.newRow();
			head.set("rowNo", EnumCustomer.IMPORT_NO.getValue());
			head.set("errorMsg", EnumCustomer.REASONS_FOR_FAILURE.getValue());
			for (int cellNum = 0; cellNum <= row.getLastCellNum(); cellNum++)
			{
				String cols = request.getParameter("combox"
						+ (cellNum - erroRecord));
				Cell hssfCell = row.getCell(cellNum);
				if (hssfCell != null)
				{
					if (getValue(hssfCell).equals(
							EnumCustomer.IMPORT_NO.getValue())
							|| getValue(hssfCell)
									.equals(EnumCustomer.REASONS_FOR_FAILURE
											.getValue()))
					{
						continue;
					}
				}
				if (cols != null && cols.equals(""))
				{
					dt.addColumn("" + (cellNum + 2));
					if (hssfCell != null)
						head.set("" + (cellNum + 2), getValue(hssfCell)
								.replaceAll(" ", ""));
				} else if (cols != null)
				{
					dt.addColumn(cols);
					if (hssfCell != null)
						head.set(cols, getValue(hssfCell).replaceAll(" ", ""));
				}

			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * 取出表头（任务目标分配）
	 * 
	 * @param dt
	 * @param filePath
	 */
	public static void getExeclHeadTask(DataTable dt, String filePath,
			int erroRecord)
	{
		try
		{
			HttpServletRequest request = org.apache.struts2.ServletActionContext
					.getRequest();
			Workbook workbook = ReadExcel.getExcelType(filePath);
			Sheet sheet = workbook.getSheetAt(0);
			Row row = sheet.getRow(0);
			DataRow head = dt.newRow();
			head.set("rowNo", EnumCustomer.IMPORT_NO.getValue());
			head.set("errorMsg", EnumCustomer.REASONS_FOR_FAILURE.getValue());
			for (int cellNum = 0; cellNum <= row.getLastCellNum(); cellNum++)
			{
				String cols = request.getParameter("combox"
						+ (cellNum - erroRecord));
				Cell hssfCell = row.getCell(cellNum);
				if (hssfCell != null
						&& (getValue(hssfCell).equals(
								EnumCustomer.IMPORT_NO.getValue()) || getValue(
								hssfCell).equals(
								EnumCustomer.REASONS_FOR_FAILURE.getValue())))
				{
					erroRecord++;
					continue;
				}
				if (cols != null && cols.equals(""))
				{
					dt.addColumn("" + (cellNum + 2));
					if (hssfCell != null)
						head.set("" + (cellNum + 2), getValue(hssfCell)
								.replaceAll(" ", ""));
				} else if (cols != null)
				{
					dt.addColumn(cols);
					if (hssfCell != null)
						head.set(cols, getValue(hssfCell).replaceAll(" ", ""));
				}

			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * 导出EXECL文件
	 */
	@SuppressWarnings({ "deprecation" })
	public static String exportExcel(DataTable dt, CrmCustomerTransfer cct)
	{
		try
		{
			HttpServletRequest req = org.apache.struts2.ServletActionContext
					.getRequest();
			int randomNum = (int) (Math.random() * 10000);
			String path = "Records/crm_error" + randomNum + ".xls";
			// 服务器绝对路径
			path = req.getRealPath("/") + path;
			String dir = path.substring(0, path.lastIndexOf("/")); // 取出目录
			File file = new File(dir);
			// 检查文件是否存在
			File obj = new File(path);
			if (!file.exists())
			{ // 目录不存在，则创建相应的目录
				file.mkdirs();
				if (!obj.exists()) // 接下来创建具体文件
					obj.createNewFile(); // 就是在这个点抛出异常
			}

			HSSFWorkbook workbook = new HSSFWorkbook();
			// 在Excel 工作簿中建一工作表
			HSSFSheet sheet = workbook.createSheet("Sheet1");
			HSSFRow row = sheet.createRow((int) 0);
			// 第四步，创建单元格，并设置值表头 设置表头居中
			HSSFCellStyle style = workbook.createCellStyle();
			style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
			int k = 0;
			for (int i = 0; i < dt.rowSize(); i++)
			{
				DataRow dataRow = dt.getRow(i);
				if (i == 0)
				{
					row = sheet.createRow((int) k);
					for (int j = 0; j < dt.colSize(); j++)
					{
						Object value = dt.getColumn(j).getValues()[i];
						if (value != null)
						{
							row.createCell((short) j).setCellValue("" + value);
						}
					}
					k++;
				} else
				{
					if (dataRow.get("errorMsg") != null && dataRow != null)
					{
						if (!dataRow.get("errorMsg").equals(""))
						{
							row = sheet.createRow((int) k);
							for (int j = 0; j < dt.colSize(); j++)
							{
								Object value = dt.getColumn(j).getValues()[i];
								row.createCell((short) j).setCellValue(
										value == null ? "" : value.toString());
							}
							k++;
						}
					}
				}
			}
			// 新建一输出文件流
			FileOutputStream fOut = new FileOutputStream(path);
			// 把相应的Excel 工作簿存盘
			workbook.write(fOut);
			// 操作结束，关闭文件
			fOut.flush();
			fOut.close();
			return path;
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return "";
	}

	public static String checkStrNull(Object obj)
	{
		if (obj == null)
		{
			return "";
		} else
		{
			if (!StringUtil.isEmpty(obj.toString()))
				return obj.toString();
			else
				return null;
		}

	}

	/**
	 * 根据身份证号生成出生日期
	 * 
	 * @param cardID 15位或18位的身份证号
	 * @return 出生日期
	 * 
	 */
	public static Date getBirthday(String cardID)
	{
		Date returnDate = null;
		StringBuffer tempStr = null;
		if (cardID != null && cardID.trim().length() > 0)
		{
			if (cardID.trim().length() == 15)
			{
				tempStr = new StringBuffer(cardID.substring(6, 12));
				tempStr.insert(4, '-');
				tempStr.insert(2, '-');
				tempStr.insert(0, "19");
			} else if (cardID.trim().length() == 18)
			{
				tempStr = new StringBuffer(cardID.substring(6, 14));
				tempStr.insert(6, '-');
				tempStr.insert(4, '-');
			}
		}
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		if (tempStr != null && tempStr.toString().trim().length() > 0)
		{
			try
			{
				returnDate = df.parse(tempStr.toString());
			} catch (Exception e)
			{
				System.out.println("输入的身份证错误，不能转换为相应的出生日期");
			}
		}
		return returnDate;
	}

	/**
	 * 转日期
	 * 
	 * @param date
	 * @return
	 */
	public static Date parseStringToDateBeforeToday(String date)
	{
		Date result = parseAllStringToDate(date);
		if (isDateLargeToday(result))
		{
			return null;
		}
		return result;
	}

	/**
	 * 判断长度
	 * 
	 * @param value
	 * @param length
	 * @return
	 */
	public static boolean checkLength(String value, int length)
	{
		boolean flag = false;
		if (value.length() > length)
		{
			flag = true;
		}
		return flag;
	}

	/******************** 任务导入 **********************************/
	/**
	 * 检验excel文件中格式,长度
	 * 
	 * @param dt
	 * @param map
	 * @return
	 */
	public static void getTaskFormatVelidate(DataTable dt,
			Map<String, String> map, CrmCustomerTransfer cct, String type)
	{
		String formatStr = "";
		String langthStr = "";
		String failStr = "";
		String nullStr = "";
		for (int i = 1; i < dt.rowSize(); i++)
		{
			failStr = "";
			langthStr = "";
			formatStr = "";
			String str = "";
			DataRow dataRow = dt.getRow(i);
			if (i == 0)
			{
				continue;
			} else
			{
				if (type.equals("inner"))
				{
					str = ImportUtil.checkStrNull(dataRow.get("customerNo"));
					if (!StringUtil.isEmpty(str))
					{
						if (checkLength(str, 20))
						{
							langthStr += "\"" + map.get("customerNo") + "\",";
						}
					} else
					{
						nullStr += "\"" + map.get("customerNo") + "\",";
					}

				} else if (type.equals("outer"))
				{
					str = ImportUtil.checkStrNull(dataRow.get("customerName"));
					if (!StringUtil.isEmpty(str))
					{
						if (checkLength(str, 20))
						{
							langthStr += "\"" + map.get("customerName") + "\",";
						}
					}

					str = ImportUtil.checkStrNull(dataRow.get("phoneNo"));
					if (!StringUtil.isEmpty(str))
					{
						if (checkLength(str, 11))
						{
							langthStr += "\"" + map.get("phoneNo") + "\",";
						} else
						{
							if (isPhoneOrMobilePhone(str) == 3)
							{
								formatStr += "\"" + map.get("phoneNo") + "\",";
							}
						}
					} else
					{
						nullStr += "\"" + map.get("phoneNo") + "\",";
					}
					str = ImportUtil.checkStrNull(dataRow.get("account"));
					if (!StringUtil.isEmpty(str))
					{
						if (checkLength(str, 20))
						{
							langthStr += "\"" + map.get("account") + "\",";
						}
					}

				}
			}
			if (type.equals("inner"))
			{
				if (!StringUtil.isEmpty(nullStr))
				{// 非空验证
					nullStr = nullStr.substring(0, nullStr.length() - 1);
					str = EnumCustomer.CUSTOMERNO_NEED.getValue();
					dataRow.set("errorMsg", str);
					continue;
				}
			} else if (type.equals("outer"))
			{
				if (!StringUtil.isEmpty(nullStr))
				{// 非空验证
					nullStr = nullStr.substring(0, nullStr.length() - 1);
					str = EnumCustomer.DATANOTCOMPLETE.getValue();
					dataRow.set("errorMsg", str);
					continue;
				}
			}
			if (!StringUtil.isEmpty(langthStr))
			{// 判断长度过长的字段
				langthStr = langthStr.substring(0, langthStr.length() - 1);
				if (!StringUtil.isEmpty(formatStr))
				{// 验证字段格式错误
					formatStr = formatStr.substring(0, formatStr.length() - 1);
					str = langthStr
							+ EnumCustomer.IMPORT_EXCEEDFIELD.getValue() + ","
							+ formatStr
							+ EnumCustomer.IMPORT_FORMATFIELD.getValue();
				} else
				{
					str = langthStr
							+ EnumCustomer.IMPORT_EXCEEDFIELD.getValue();
				}
				dataRow.set("errorMsg", str);
				continue;
			}
			if (!StringUtil.isEmpty(formatStr))
			{
				formatStr = formatStr.substring(0, formatStr.length() - 1);
				str = formatStr + EnumCustomer.IMPORT_FORMATFIELD.getValue();
				dataRow.set("errorMsg", str);
				continue;
			}
		}
	}

	/**
	 * 取得导入excel中客户编号 联系方式
	 * 
	 * @param cct
	 * @return
	 */
	public static void getEntireTaskCustData(CrmCustomerTransfer cct,
			String type)
	{
		HttpServletRequest request = org.apache.struts2.ServletActionContext
				.getRequest();
		HashMap<String, String> pnamemap = new HashMap<String, String>();// key:客户编号
		// value:行号
		HashMap<String, String> pcodemap = new HashMap<String, String>();// key:联系方式
		// value:行号
		Integer erroRecord = Integer.parseInt(cct.getParameterMap().get(
				"erroRecord"));// 导入表格中是否有序号、失败原因列
		String str = "";
		try
		{
			for (int numSheet = 0; numSheet < cct.getWorkbook()
					.getNumberOfSheets(); numSheet++)
			{
				Sheet hssfSheet = cct.getWorkbook().getSheetAt(numSheet);
				if (hssfSheet == null)
				{
					continue;
				}
				for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++)
				{
					Row hssfRow = hssfSheet.getRow(rowNum);
					str = "";
					if (isBlankRow(hssfRow))
					{
						continue;
					} else
					{
						for (int cellNum = erroRecord; cellNum < hssfRow
								.getLastCellNum(); cellNum++)
						{
							Cell hssfCell = hssfRow.getCell(cellNum);
							String cols = request.getParameter("combox"
									+ (cellNum - erroRecord));
							String val = "";
							if (hssfCell != null)
							{
								val = getValue(hssfCell).replaceAll(" ", "");
							}
							if (cols != null && !cols.equals(""))
							{

								if (type.equals("inner")
										&& cols.equals("customerNo"))
								{
									if (pnamemap.get(val) == null)
									{
										pnamemap.put(val, "" + rowNum);
									} else
									{
										str = pnamemap.get(val) + "," + rowNum;
										pnamemap.put(val, str);
									}
								}
								if (type.equals("outer")
										&& cols.equals("phoneNo"))
								{
									if (pcodemap.get(val) == null)
									{
										pcodemap.put(val, "" + rowNum);
									} else
									{
										str = pcodemap.get(val) + "," + rowNum;
										pcodemap.put(val, str);
									}
								}
							}
						}

					}
				}
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		cct.setCodeMap(pnamemap);// 客户编号
		cct.setProvMap(pcodemap);// 联系方式
	}

	/****************** 产品导入 ************************/
	/**
	 * 导入产品 检验excel文件中格式,长度
	 * 
	 * @param dt
	 * @param map
	 * @return
	 */
	public static void getProductImportFormatVelidate(DataTable dt,
			Map<String, String> map, CrmCustomerTransfer cct)
	{
		String formatStr = "";
		String langthStr = "";
		String nullStr = "";
		for (int i = 1; i < dt.rowSize(); i++)
		{
			langthStr = "";
			formatStr = "";
			String str = "";
			nullStr = "";
			DataRow dataRow = dt.getRow(i);
			if (i == 0)
			{
				continue;
			} else
			{
				/*************** 逻辑处理start ***************/
				str = ImportUtil.checkStrNull(dataRow.get("templateId"));
				if (!StringUtil.isEmpty(str))
				{
					if (checkLength(str, 100))
					{
						langthStr += "\"" + map.get("templateId") + "\",";
					}
				} else
				{
					nullStr += "\"" + map.get("templateId") + "\",";
				}

				str = ImportUtil.checkStrNull(dataRow.get("productName"));
				if (!StringUtil.isEmpty(str))
				{
					if (checkLength(str, 100))
					{
						langthStr += "\"" + map.get("productName") + "\",";
					}
				} else
				{
					nullStr += "\"" + map.get("productName") + "\",";
				}

				str = ImportUtil.checkStrNull(dataRow.get("productCode"));
				if (!StringUtil.isEmpty(str))
				{
					if (checkLength(str, 20))
					{
						langthStr += "\"" + map.get("productCode") + "\",";
					}
				} else
				{
					nullStr += "\"" + map.get("productCode") + "\",";
				}

				str = ImportUtil.checkStrNull(dataRow.get("saleStartDate"));
				if (!StringUtil.isEmpty(str))
				{
					Date date = parseAllStringToDate(str);
					if (!(date != null && !date.toLocaleString().contains(
							"1970")))
					{
						formatStr += "\"" + map.get("saleStartDate") + "\",";
					}
				} else
				{
					nullStr += "\"" + map.get("saleStartDate") + "\",";
				}

				str = ImportUtil.checkStrNull(dataRow.get("saleEndDate"));
				if (!StringUtil.isEmpty(str))
				{
					Date date = parseAllStringToDate(str);
					if (!(date != null && !date.toLocaleString().contains(
							"1970")))
					{
						formatStr += "\"" + map.get("saleEndDate") + "\",";
					}
				} else
				{
					nullStr += "\"" + map.get("saleEndDate") + "\",";
				}

			}

			if (!StringUtil.isEmpty(nullStr))
			{// 非空验证
				nullStr = nullStr.substring(0, nullStr.length() - 1);
				str = EnumCustomer.PRODUCTMSG_NEED.getValue() + nullStr
						+ EnumCustomer.COLS.getValue();
				dataRow.set("errorMsg", str);
				continue;
			}

			/*************** 逻辑处理end ***************/

			if (!StringUtil.isEmpty(langthStr))
			{// 判断长度过长的字段
				langthStr = langthStr.substring(0, langthStr.length() - 1);
				if (!StringUtil.isEmpty(formatStr))
				{// 验证字段格式错误
					formatStr = formatStr.substring(0, formatStr.length() - 1);
					str = langthStr
							+ EnumCustomer.IMPORT_EXCEEDFIELD.getValue() + ","
							+ formatStr
							+ EnumCustomer.IMPORT_FORMATFIELD.getValue();
				} else
				{
					str = langthStr
							+ EnumCustomer.IMPORT_EXCEEDFIELD.getValue();
				}
				dataRow.set("errorMsg", str);
				continue;
			}
			if (!StringUtil.isEmpty(formatStr))
			{
				formatStr = formatStr.substring(0, formatStr.length() - 1);
				str = formatStr + EnumCustomer.IMPORT_FORMATFIELD.getValue();
				dataRow.set("errorMsg", str);
				continue;
			}
		}
	}

	/**
	 ** 产品导入
	 * 
	 * @param batchSize 批次数
	 * @param beginSize 起始数
	 * @param dt 包装集合
	 */
	public static void BatchProcessProduct(Integer batchSize,
			Integer beginSize, DataTable dt, CrmCustomerTransfer cct)
	{
		HttpServletRequest request = org.apache.struts2.ServletActionContext
				.getRequest();
		DataPackage cidp = new DataPackage();
		List<PdtProductField> extBeanList = new ArrayList<PdtProductField>();
		PdtProductField extBean = null;
		int currentNumber = 0;
		int currentError = Integer.parseInt(cct.getParameterMap().get(
				"erroRecord"));
		try
		{
			for (int numSheet = 0; numSheet < cct.getWorkbook()
					.getNumberOfSheets(); numSheet++)
			{
				Sheet hssfSheet = cct.getWorkbook().getSheetAt(numSheet);
				if (hssfSheet == null)
				{
					continue;
				}
				for (int rowNum = beginSize + 1; rowNum <= hssfSheet
						.getLastRowNum(); rowNum++)
				{
					if (currentNumber == batchSize)
					{
						break;
					}
					Row hssfRow = hssfSheet.getRow(rowNum);
					if (isBlankRow(hssfRow))
					{
						continue;
					} else
					{
						DataRow row = null;
						row = dt.newRow();
						row.set("rowNo", rowNum);
						for (int cellNum = 0 + currentError; cellNum <= hssfRow
								.getLastCellNum(); cellNum++)
						{
							extBean = new PdtProductField();
							Cell hssfCell = hssfRow.getCell(cellNum);
							String cols = request.getParameter("combox"
									+ (cellNum - currentError));
							String value = "";
							if (hssfCell != null)
							{
								value = getValue(hssfCell).replaceAll(" ", "");
							}

							if (cols != null && !cols.equals(""))
							{
								row.set(cols, value);
								cidp.getProductFields(value, extBean, cols, cct);
							}
							extBean.setLineNumber("" + rowNum);
							extBeanList.add(extBean);
						}
						currentNumber++;
					}
				}
			}
			cct.setProductFeildList(extBeanList);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * 导入产品excel中数据对应的实体
	 * 
	 * @param cct
	 * @return
	 */
	public static void getEntireProductMap(CrmCustomerTransfer cct)
	{
		HttpServletRequest request = org.apache.struts2.ServletActionContext
				.getRequest();
		HashMap<String, String> pnamemap = new HashMap<String, String>();// key:产品名称
		// value:行号
		HashMap<String, String> pcodemap = new HashMap<String, String>();// key:产品编号
		// value:行号
		Integer erroRecord = Integer.parseInt(cct.getParameterMap().get(
				"erroRecord"));// 导入表格中是否有序号、失败原因列
		String str = "";
		try
		{
			for (int numSheet = 0; numSheet < cct.getWorkbook()
					.getNumberOfSheets(); numSheet++)
			{
				Sheet hssfSheet = cct.getWorkbook().getSheetAt(numSheet);
				if (hssfSheet == null)
				{
					continue;
				}
				for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++)
				{
					Row hssfRow = hssfSheet.getRow(rowNum);
					str = "";
					if (isBlankRow(hssfRow))
					{
						continue;
					} else
					{
						for (int cellNum = erroRecord; cellNum < hssfRow
								.getLastCellNum(); cellNum++)
						{
							Cell hssfCell = hssfRow.getCell(cellNum);
							String cols = request.getParameter("combox"
									+ (cellNum - erroRecord));
							String val = "";
							if (hssfCell != null)
							{
								val = getValue(hssfCell).replaceAll(" ", "");
							}
							if (cols != null && !cols.equals(""))
							{

								if (cols.equals("productName"))
								{
									if (pnamemap.get(val) == null)
									{
										pnamemap.put(val, "" + rowNum);
									} else
									{
										str = pnamemap.get(val) + "," + rowNum;
										pnamemap.put(val, str);
									}
								}
								if (cols.equals("productCode"))
								{
									if (pcodemap.get(val) == null)
									{
										pcodemap.put(val, "" + rowNum);
									} else
									{
										str = pcodemap.get(val) + "," + rowNum;
										pcodemap.put(val, str);
									}
								}
							}
						}

					}
				}
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		cct.setProvMap(pnamemap);// 产品名称
		cct.setCodeMap(pcodemap);// 产品编号
	}

	/**
	 * 自动导入客户长度校验
	 * 
	 * @param customer
	 */
	public static void getAutoImpCustomerFormatValidate(CrmCustomer customer)
	{
		String str = "";
		String regex = "^[0-9#*]*$";
		str = checkStrNull(customer.getCustomerNo());
		if (!StringUtil.isNullOrEmpty(str) && checkLength(str, 20))
		{
			customer.setCustomerNo(str.substring(0, 20));
		}
		str = checkStrNull(customer.getCustomerName());
		if (!StringUtil.isNullOrEmpty(str) && checkLength(str, 20))
		{
			customer.setCustomerName(str.substring(0, 20));
		}
		str = checkStrNull(customer.getSex());
		if (!StringUtil.isNullOrEmpty(str) && checkLength(str, 1))
		{
			customer.setSex(str.substring(0, 1));
		}
		str = checkStrNull(customer.getCustomerTitle());
		if (!StringUtil.isNullOrEmpty(str) && checkLength(str, 20))
		{
			customer.setCustomerTitle(str.substring(0, 20));
		}
		str = checkStrNull(customer.getIdCard());
		if (!StringUtil.isNullOrEmpty(str) && checkLength(str, 20))
		{
			customer.setIdCard(str.substring(0, 20));
		}
		str = checkStrNull(customer.getCompany());
		if (!StringUtil.isNullOrEmpty(str) && checkLength(str, 50))
		{
			customer.setCompany(str.substring(0, 50));
		}
		str = checkStrNull(customer.getRemark());
		if (!StringUtil.isNullOrEmpty(str) && checkLength(str, 2000))
		{
			customer.setRemark(str.substring(0, 2000));
		}
		str = checkStrNull(customer.getAddress());
		if (!StringUtil.isNullOrEmpty(str) && checkLength(str, 50))
		{
			customer.setAddress(str.substring(0, 50));
		}
		str = checkStrNull(customer.getMobilePhone1());
		if (!StringUtil.isNullOrEmpty(str) && isMobileNO(str))
		{
			customer.setMobilePhone1(str);
		} else
		{
			customer.setMobilePhone1("");
		}
		str = checkStrNull(customer.getMobilePhone1Remark());
		if (!StringUtil.isNullOrEmpty(str) && checkLength(str, 20))
		{
			customer.setMobilePhone1Remark(str.substring(0, 20));
		}
		str = checkStrNull(customer.getMobilePhone2());
		if (!StringUtil.isNullOrEmpty(str) && isMobileNO(str))
		{
			customer.setMobilePhone2(str);
		} else
		{
			customer.setMobilePhone2("");
		}
		str = checkStrNull(customer.getMobilePhone2Remark());
		if (!StringUtil.isNullOrEmpty(str) && checkLength(str, 20))
		{
			customer.setMobilePhone2Remark(str.substring(0, 20));
		}
		str = subString(checkStrNull(customer.getPhone()), 20);
		if (!StringUtil.isNullOrEmpty(str)
				&& isNumeric(str.replaceAll("-", "").replaceAll("－", "")))
		{
			customer.setPhone(str);
		} else
		{
			customer.setPhone("");
		}
		str = subString(checkStrNull(customer.getPhoneExt()), 10);
		if (!StringUtil.isNullOrEmpty(str) && str.matches(regex))
		{
			customer.setPhoneExt(str);
		} else
		{
			customer.setPhoneExt("");
		}
		str = subString(checkStrNull(customer.getFax()), 20);
		if (!StringUtil.isNullOrEmpty(str)
				&& isNumeric(str.replaceAll("-", "").replaceAll("－", "")))
		{
			customer.setFax(str);
		} else
		{
			customer.setFax("");
		}
		str = subString(checkStrNull(customer.getFaxExt()), 10);
		if (!StringUtil.isNullOrEmpty(str) && str.matches(regex))
		{
			customer.setFaxExt(str);
		} else
		{
			customer.setFaxExt("");
		}
		str = subString(checkStrNull(customer.getEmail()), 50);
		if (!StringUtil.isNullOrEmpty(str) && isEmail(str))
		{
			customer.setEmail(str);
		} else
		{
			customer.setEmail("");
		}
	}

	private static String subString(String str, Integer length)
	{
		if (!StringUtil.isNullOrEmpty(str) && str.length() > length)
		{
			str = str.substring(0, length);
		}
		return str;
	}

	// 自动导入客户保存失败文件
	/*
	public static boolean writeFailedRecordTxtFile(
			List<ImpCusTempdata> failedList, String pathName, String encoding)
	{
		try
		{
			File file = new File(pathName);
			if (!FileUtil.isExistFile(pathName))
			{
				file.createNewFile();
			}
			FileOutputStream fos = new FileOutputStream(new File(pathName));
			OutputStreamWriter writer = new OutputStreamWriter(fos, encoding);
			List<ImpCusSourceData> sDataList = new ArrayList<ImpCusSourceData>();
			String lineStr = "";
			Comparator<ImpCusSourceData> comparator = new Comparator<ImpCusSourceData>() {
				public int compare(ImpCusSourceData obj1, ImpCusSourceData obj2)
				{
					return obj1.getRowNumber().compareTo(obj2.getRowNumber());
				}
			};
			for (ImpCusTempdata tDdata : failedList)
			{
				sDataList = (List<ImpCusSourceData>) SerializeUtil
						.formXML(tDdata.getContent());
				Collections.sort(sDataList, comparator);
				lineStr = StringUtil.getNotNullValue(tDdata.getRemark()) + "|";
				for (ImpCusSourceData sData : sDataList)
				{
					lineStr = lineStr
							+ StringUtil.getNotNullValue(sData.getFieldValue())
							+ "|";
				}
				lineStr += "\r\n";
				writer.write(lineStr);
				lineStr = "";
			}
			writer.close();
			fos.close();
			return true;
		} catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}*/
}
