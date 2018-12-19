package com.banger.mobile.facade.impl.quartz.constant;

/**
 * 自助缴费项目常量信息
 * 
 * @author zhangxiang
 * 
 */
public class AutopayConstants {

	// 水费
	public static final String PAYTYPE_WATER = "water";

	// 电费
	public static final String PAYTYPE_ELEC = "elec";

	// 煤气费
	public static final String PAYTYPE_GAS = "gas";

	// 广电费
	public static final String PAYTYPE_TV = "tv";

	// 自助缴费类型
	public static final String AUTOPAY_PAYTYPE = "paytype";

	// 自助缴费月报表
	public static final String AUTOPAY_REPORT_MONTHLY = "monthly";

	// 月报名称
	public static final String AUTOPAY_REPORT_MONTHLY_NAME = "月报";

	// 自助缴费日报
	public static final String AUTOPAY_REPORT_DAILY = "daily";

	// 日报名称
	public static final String AUTOPAY_REPORT_DAILY_NAME = "日报";

	// each 按笔收(每笔固定费用）
	public static final String PROFIT_FORMULA_TYPE_EACH = "each";

	// rate按比率（百分之多少，并设置上下线）
	public static final String PROFIT_FORMULA_TYPE_RATE = "rate";

	// 报表head的城市
	public static final String PROFIT_REPORT_HEAD_CITY = "城市";

	// 报表汇总
	public static final String PROFIT_REPORT_TOTAL = "合计";

	// 报表 金额
	public static final String PROFIT_REPORT_AMOUNT = "金额";

	// 报表 笔数
	public static final String PROFIT_REPORT_COUNT = "笔数";

	// 报表 收入
	public static final String PROFIT_REPORT_INCOME = "收入";

	// 报表 类型(日报/月报)
	public static final String REPORT_TYPE = "reportType";

	// 报表 地区
	public static final String REPORT_REGION = "region";

	// 报表所属时期
	public static final String REPORT_SSSQ = "sssq";
	
	//FTP任务所属时期
	public static final String FTP_TASK_SSSQ = "sssq";
	
	//对帐任务所属时期
	public static final String CONFIRM_TASK_SSSQ = "sssq";

	// 生成名动态替换符号
	public static final String FILE_NAME_REPLACE_SYMBOL = "$";

	// 缴费对帐文件字符集
	public static final String AUTOPAY_FILE_CHARSET_UTF8 = "utf-8";

	// 内容分隔符
	public static final String AUTOPAY_FILE_CONTENT_REPLACE_SYMBOL = "|";

	// 已支付已缴费
	public static final String PAID_PAYMENT = "paid_payment";

	// 已支付未缴费
	public static final String PAID_NONPAYMENT = "paid_nonpayment";

	// 未缴费未支付
	public static final String UNPAID_NONPAYMENT = "unpaid_nonpayment";
	
	//缴费失败
	public static final String PAYMENT_FAILURE = "payment_failure";
	
	// 信息平台对帐文件中的标志位: 成功 success
	public static final String AUTOPAY_SUCCESS = "success";
	
	// 信息平台对帐文件中的标志位: 失败 failure
	public static final String AUTOPAY_FAILURE = "failure";

}
