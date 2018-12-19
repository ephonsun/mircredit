package com.banger.mobile.facade.impl.quartz.constant;

/**
 * 任务常量信息
 * 
 * @author zhangxiang
 * 
 */
public class TaskConstants {

	// 任务正在执行
	public final static String EXECUTE_TYPE_C = "C";
	// 任务执行成功
	public final static String EXECUTE_TYPE_Y = "Y";
	// 任务执行失败
	public final static String EXECUTE_TYPE_N = "N";
	// 任务运行结果 成功
	public static final String RW_STATE_SUCCESS = "0";
	// 任务运行结果 失败
	public static final String RW_STATE_FAIL = "1";
	// 任务运行方式 自动
	public static final String RW_YXFS_ZD = "0";
	// 任务运行方式 手动
	public static final String RW_YXFS_SD = "1";
	// 运行方式
	public static final String YXFS = "yxfs";

	public static final String CHECKING_SCHEDULING_IS_RUN_ERROR = "checking_scheduling_is_run_error";

	public static final String AUTO_TASK_RUN_FAILURE = "auto_task_run_failure";

	public static final String RECORD_SCHEDULIG_EXCUTE_RESULT_FAILURE = "record_schedulig_excute_result_failure";

	public static final String MANUAL_TASK_RUN_FAILURE = "manual_task_run_failure";
}
