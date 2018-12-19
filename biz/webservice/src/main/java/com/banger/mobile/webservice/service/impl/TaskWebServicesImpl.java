package com.banger.mobile.webservice.service.impl;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.model.base.customer.BaseCrmCustomer;
import com.banger.mobile.domain.model.data.Audio;
import com.banger.mobile.domain.model.data.CustomerData;
import com.banger.mobile.domain.model.data.Event;
import com.banger.mobile.domain.model.microProduct.PdtProductCustomerBean;
import com.banger.mobile.domain.model.microTask.*;
import com.banger.mobile.domain.model.phoneConfig.PhoneConfig;
import com.banger.mobile.domain.model.record.RecordInfo;
import com.banger.mobile.domain.model.user.SysUser;
import com.banger.mobile.facade.customer.CrmCustomerService;
import com.banger.mobile.facade.data.CustomerDataService;
import com.banger.mobile.facade.data.DataAudioService;
import com.banger.mobile.facade.dept.DeptFacadeService;
import com.banger.mobile.facade.loan.LnLoanService;
import com.banger.mobile.facade.microTask.*;
import com.banger.mobile.facade.phoneConfig.PhoneConfigService;
import com.banger.mobile.facade.record.RecordInfoService;
import com.banger.mobile.facade.uploadFile.SysUploadFileService;
import com.banger.mobile.facade.user.SysUserService;
import com.banger.mobile.facade.webservice.TaskWebService;
import com.banger.mobile.util.DateUtil;
import com.banger.mobile.util.JsonDateValueProcessor;
import com.banger.mobile.util.VmHelper;
import com.banger.mobile.webservice.domain.*;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.codehaus.xfire.transport.http.XFireServletController;

import javax.jws.WebService;
import javax.servlet.http.HttpServletRequest;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@WebService(serviceName = "BangerCrmTaskService", endpointInterface = "com.banger.mobile.facade.webservice.TaskWebService")
public class TaskWebServicesImpl implements TaskWebService {
	private static final Logger logger = Logger
			.getLogger(TaskWebServicesImpl.class);

	private SysUserService sysUserService;
	private TskScheduleService tskScheduleService;
	private TskMicroTaskService tskMicroTaskService;
	private TskMicroTaskTargetService tskMicroTaskTargetService;
	private DeptFacadeService deptFacadeService;
	private TskMicroTaskProgressService tskMicroTaskProgressService;
	private TskMicroTaskExecuteService tskMicroTaskExecuteService;
	private CrmCustomerService crmCustomerService;
	private CustomerDataService customerDataService;
	private DataAudioService dataAudioService;
	private RecordInfoService recordInfoService;
	private LnLoanService lnLoanService;
	private PhoneConfigService phoneConfigService;
	private SysUploadFileService sysUploadFileService;

	// 任务管理
	// 任务列表/搜索任务
	// 获取所有任务列表input值为空,isLate传-1
	public String getTaskList(String account, String input, Integer status,
			Integer pageNumber, Integer isLate) {
		try {
            logger.info("pad端任务接口getTaskList开始，account:"+account+",input:"+input+",status:"+status+",pageNumber:"+pageNumber+",isLate:"+isLate);
			SysUser user = sysUserService.getAllUserByAccount(account);
			JSONArray jsonArray = new JSONArray();
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.registerJsonValueProcessor(Date.class,
					new JsonDateValueProcessor());

			Page page = new Page();
			page.setCurrentPage(pageNumber);

			Map<String, Object> conds = new HashMap<String, Object>();

			PageUtil<TskMicroTask> dataList = null;

			// 判断是否是业务主管
			Boolean isInChargeOf = deptFacadeService
					.isInChargeOfDepartment(user.getUserId().toString());
			if (isInChargeOf) {
				// 如果是主管
				String inChargeUserIds = "", inChargeUserIdsMark = "", inChargeDepts = "";
				Integer[] inChargeDeptsIntegers = deptFacadeService
						.getInChargeOfDeptIds(user.getUserId());
				for (Integer deptId : inChargeDeptsIntegers) {
					inChargeDepts += deptId.intValue() + ","; // 负责的机构
				}
				if (inChargeDepts.length() > 0) {
					inChargeDepts = inChargeDepts.substring(0,
							(inChargeDepts.length() - 1));
				}

				Integer[] inChargeUserIdsIntegers = deptFacadeService
						.getInChargeOfDeptUserIds(user.getUserId());
				if (inChargeUserIdsIntegers != null
						&& inChargeUserIdsIntegers.length > 0) {
					for (Integer userId : inChargeUserIdsIntegers) {
						inChargeUserIds += userId.intValue() + ","; // 负责的机构人员
						inChargeUserIdsMark += "[" + userId.intValue() + "],";
					}
					if (inChargeUserIds.length() > 0) {
						inChargeUserIds = inChargeUserIds.substring(0,
								(inChargeUserIds.length() - 1));
						inChargeUserIdsMark = inChargeUserIdsMark.substring(0,
								(inChargeUserIdsMark.length() - 1));
						conds.put("userId", user.getUserId());
						conds.put("inChargeUserIds", inChargeUserIds+","+user.getUserId());
						conds.put("inChargeUserIdsMark", inChargeUserIdsMark+",["+user.getUserId()+"]");
						conds.put("inChargeDepts", inChargeDepts);

						conds.put("padInput", input);
						conds.put("taskStatus", status);// 查询未完成和已完成
						conds.put("isOutDate", isLate);
						conds.put("userType", 2);
						dataList = tskMicroTaskService.getTaskListPage(conds,
								page);

					}
				} else {
					String userIdMark = "[" + user.getUserId() + "]";
					conds.put("userId", user.getUserId());
					conds.put("userIdMark", userIdMark);

					// 页面查询条件
					conds.put("padInput", input);
					conds.put("taskStatus", status);
					conds.put("isOutDate", isLate);
					conds.put("userType", 3);
					dataList = tskMicroTaskService.getMyTaskListPage(conds,
							page);
				}
			} else {
				String userIdMark = "[" + user.getUserId() + "]";
				conds.put("userId", user.getUserId());
				conds.put("userIdMark", userIdMark);

				// 页面查询条件
				conds.put("padInput", input);
				conds.put("taskStatus", status);
				conds.put("isOutDate", isLate);
				conds.put("userType", 3);
				dataList = tskMicroTaskService.getMyTaskListPage(conds, page);
			}

			// 判断任务在当前账号下有没有可执行
			String taskIds = "";
			List<TskMicroTaskExecute> exeList = new ArrayList<TskMicroTaskExecute>();
			if (dataList.getItems().size() > 0) {
				for (TskMicroTask t : dataList.getItems()) {
					taskIds += t.getTaskId() + ",";
				}
			}
			if (taskIds.length() > 0) {
				taskIds = taskIds.substring(0, taskIds.length() - 1);
				Map<String, Object> paras = new HashMap<String, Object>();
				paras.put("userId", user.getUserId());
				paras.put("taskIds", taskIds);
				exeList = tskMicroTaskExecuteService
						.getTaskUserExecutable(paras);
			}

			List<Task> plist = new ArrayList<Task>();
			for (TskMicroTask t : dataList.getItems()) {
				Task tt = new Task();
				tt.setTaskId(t.getTaskId());
				tt.setTaskName(t.getTaskTitle());
				tt.setStartDate(DateUtil.convertDateToString("yyyy-MM-dd",
						t.getStartDate()));
				tt.setEndDate(DateUtil.convertDateToString("yyyy-MM-dd",
						t.getEndDate()));
				tt.setTaskType(t.getTaskType());
				tt.setTaskTypeName(getTaskTypeName(t.getTaskType()));
				tt.setRemark(t.getRemark());
				tt.setStatus(t.getTaskStatus());
				tt.setCreateUser(t.getUserName());

				if (t.getUserTaskTarget() != null) {
					tt.setMyGroupTaskTarget(t.getUserTaskTarget().intValue());
				}
				if (t.getFinishCount() != null) {
					tt.setMyGroupFinishCount(t.getFinishCount().intValue());
				}

				// 判断任务在当前账号下有没有可执行
				tt.setExecutable(0);
				for (TskMicroTaskExecute exe : exeList) {
					if (t.getTaskId().equals(exe.getTaskId())) {
						if (exe.getTargetUser() > 0) {
							tt.setExecutable(1);
							break;
						}
					}
				}

				plist.add(tt);
			}

			String result = jsonArray.fromObject(plist, jsonConfig).toString();
            logger.info("pad端任务接口getTaskList完成，account:"+account+",input:"+input+",status:"+status+",pageNumber:"+pageNumber+",isLate:"+isLate);
			return result;
		} catch (Exception e) {
			logger.error("getTaskList", e);
			return null;
		}
	}

	// 根据id查找任务详情
	public String getTaskById(String account, Integer taskId) {
		try {
            logger.info("pad端任务接口getTaskById开始，account:"+account+",taskId:"+taskId);
			SysUser user = sysUserService.getAllUserByAccount(account);

			JSONObject jsonObject = new JSONObject();
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.registerJsonValueProcessor(Date.class,
					new JsonDateValueProcessor());

			// 是否是业务主管
			Boolean isInChargeOf = deptFacadeService
					.isInChargeOfDepartment(user.getUserId().toString());
			TskMicroTask tskMicroTask = tskMicroTaskService
					.getTskMicroTaskById(taskId); // 获取任务
			Integer taskType = tskMicroTask.getTaskType(); // 任务类型Id
			String taskTypeName = this.getTaskTypeName(taskType); // 任务类型名称
			String taskName = tskMicroTask.getTaskTitle(); // 任务名称
			Date taskStartDate = tskMicroTask.getStartDate(); // 任务开始时间
			Date taskEndDate = tskMicroTask.getEndDate(); // 任务结束时间
			String taskRemark = tskMicroTask.getRemark(); // 任务备注
			Integer taskStatus = tskMicroTask.getTaskStatus(); // 任务完成状态
			Integer createUserId = tskMicroTask.getCreateUser(); // 任务创建者ID
			String createUserName = sysUserService.getSysUserById(createUserId)
					.getUserName(); // 任务创建者名称

			Task task = new Task();
			List<UserTask> myManageUserList = new ArrayList<UserTask>();

			if (isInChargeOf) { // 如果是业务主管
				TaskProgress myTaskProgress = new TaskProgress();
				Integer[] belongUserIds = deptFacadeService
						.getInChargeOfDeptUserIds(user.getUserId()); // 客户经理下属人员(包含自己)
				Map<String, Object> param = new HashMap<String, Object>();
				param.put("belongUserIds", belongUserIds);
				param.put("taskId", taskId);
				List<TskMicroTaskExecute> userList = tskMicroTaskExecuteService
						.getSysUsersByTask(param);
				// 查询下属人员集合
				StringBuffer sb = new StringBuffer();
				if (userList.size() > 0) {
					for (TskMicroTaskExecute tskMicroTaskExecute : userList) {
						if (sb.length() > 1) {
							sb.append("," + tskMicroTaskExecute.getUserId());
						} else {
							sb.append(tskMicroTaskExecute.getUserId());
						}
					}
					String[] arr = sb.toString().split(",");
					Integer[] sysUsers = new Integer[arr.length];
					for (int i = 0; i < sysUsers.length; i++) {
						sysUsers[i] = Integer.parseInt(arr[i]);
					}
					for (Integer sysUser : sysUsers) {
						UserTask userTask = new UserTask();
						TaskProgress myGroudTaskProgress = new TaskProgress();
						if (taskType == 1) {
							myGroudTaskProgress = tskMicroTaskProgressService
									.getLoanTaskCountByUser(taskId, 6, sysUser); // 查询个人任务进度
						}
						if (taskType == 2 || taskType == 3) {
							myGroudTaskProgress = tskMicroTaskProgressService
									.getCompleteMarketTaskByUser(taskId,
											sysUser); // 查询个人任务进度
						}
						// 查询我的下属总完成数
						if (task.getMyGroupFinishCount() != null) {
							task.setMyGroupFinishCount(task
									.getMyGroupFinishCount()
									+ myGroudTaskProgress.getFinishCount());
						} else {
							task.setMyGroupFinishCount(myGroudTaskProgress
									.getFinishCount());
						}
						userTask.setFinishCount(myGroudTaskProgress
								.getFinishCount());
						userTask.setTaskTarget(myGroudTaskProgress
								.getTaskTarget());
						userTask.setUserId(sysUser);
						userTask.setUserName(sysUserService.getSysUserById(
								sysUser).getUserName());
						myManageUserList.add(userTask);
					}
				}
				// 查询我团队的任务目标
				Integer myGroupTarget = 0;
				List<Integer> deptList = deptFacadeService
						.getRootDeptIdByLoginId(user.getUserId());
				for (Integer deptId : deptList) {
					TaskProgress taskProgress = tskMicroTaskProgressService
							.getCompleteMarketTaskByDept(
									Integer.valueOf(taskId),
									Integer.valueOf(deptId));
					myGroupTarget += taskProgress.getTaskTarget();
				}
				task.setMyGroupTaskTarget(myGroupTarget);

				if (taskType == 1) { // 如果是贷款任务
					myTaskProgress = tskMicroTaskProgressService
							.getLoanTaskCountByUser(taskId, 6, user.getUserId());
				} else { // 营销任务
					myTaskProgress = tskMicroTaskProgressService
							.getCompleteMarketTaskByUser(taskId,
									user.getUserId());
				}
				task.setMyFinishCount(myTaskProgress.getFinishCount()); // 我的完成情况
				task.setMyTaskTarget(myTaskProgress.getTaskTarget()); // 我的总分配
				task.setMyManageUserList(myManageUserList); // 我的下属集合
			} else { // 如果是客户经理
				TaskProgress taskProgress = new TaskProgress();
				if (taskType == 1) {
					taskProgress = tskMicroTaskProgressService
							.getLoanTaskCountByUser(taskId, 6, user.getUserId());

				} else {
					taskProgress = tskMicroTaskProgressService
							.getCompleteMarketTaskByUser(taskId,
									user.getUserId());
				}

				UserTask userTask = new UserTask();
				userTask.setFinishCount(taskProgress.getFinishCount());
				userTask.setTaskTarget(taskProgress.getTaskTarget());
				userTask.setUserId(user.getUserId());
				userTask.setUserName(user.getUserName());

				task.setMyFinishCount(taskProgress.getFinishCount());
				task.setMyTaskTarget(taskProgress.getTaskTarget());
			}
			task.setTaskId(taskId);
			task.setTaskName(taskName);
			task.setStartDate(DateUtil.convertDateToString("yyyy-MM-dd",
					taskStartDate));
			task.setEndDate(DateUtil.convertDateToString("yyyy-MM-dd",
					taskEndDate));
			task.setTaskType(taskType);
			task.setTaskTypeName(taskTypeName);
			task.setStatus(taskStatus);
			task.setRemark(taskRemark);
			task.setCreateUser(createUserName);
			task.setNewCustomerPercent(VmHelper.getDecimalMoney(tskMicroTask
					.getNewCustomerPercent()));
			task.setOldCustomerPercent(VmHelper.getDecimalMoney(tskMicroTask
					.getOldCustomerPercent()));

			List<Task> plist = new ArrayList<Task>();
			plist.add(task);
            logger.info("pad端任务接口getTaskById完成，account:"+account+",taskId:"+taskId);
			if (plist.size() > 0) {
				String result = jsonObject.fromObject(plist.get(0), jsonConfig)
						.toString();
				return result;
			} else {
				return null;
			}
		} catch (Exception e) {
			logger.error("getTaskList", e);
			return null;
		}
	}

	private String getTaskTypeName(Integer taskType) {
		String result = "";
		if (taskType == 1) {
			result = "贷款任务";
		}
		if (taskType == 2) {
			result = "营销任务-电话营销";
		}
		if (taskType == 3) {
			result = "营销任务-实地营销";
		}
		return result;
	}

	// 任务列表总数
	// 获取所有任务列表总数input值为空,isLate传-1
	public String getTaskListCount(String account, String input,
			Integer status, Integer isLate) {
		try {
            logger.info("pad端任务接口getTaskListCount开始，account:"+account+",input:"+input+",status:"+status+",isLate:"+isLate);
			SysUser user = sysUserService.getAllUserByAccount(account);

			Page page = new Page();

			Map<String, Object> conds = new HashMap<String, Object>();

			PageUtil<TskMicroTask> dataList = null;

			// 判断是否是业务主管
			Boolean isInChargeOf = deptFacadeService
					.isInChargeOfDepartment(user.getUserId().toString());
			if (isInChargeOf) {
				// 如果是主管
				String inChargeUserIds = "", inChargeUserIdsMark = "", inChargeDepts = "";
				Integer[] inChargeDeptsIntegers = deptFacadeService
						.getInChargeOfDeptIds(user.getUserId());
				for (Integer deptId : inChargeDeptsIntegers) {
					inChargeDepts += deptId.intValue() + ","; // 负责的机构
				}
				if (inChargeDepts.length() > 0) {
					inChargeDepts = inChargeDepts.substring(0,
							(inChargeDepts.length() - 1));
				}

				Integer[] inChargeUserIdsIntegers = deptFacadeService
						.getInChargeOfDeptUserIds(user.getUserId());
				for (Integer userId : inChargeUserIdsIntegers) {
					inChargeUserIds += userId.intValue() + ","; // 负责的机构人员
					inChargeUserIdsMark += "[" + userId.intValue() + "],";
				}
				if (inChargeUserIds.length() > 0) {
					inChargeUserIds = inChargeUserIds.substring(0,
							(inChargeUserIds.length() - 1));
					inChargeUserIdsMark = inChargeUserIdsMark.substring(0,
							(inChargeUserIdsMark.length() - 1));
				}

				conds.put("userId", user.getUserId());
				conds.put("inChargeUserIds", inChargeUserIds+","+user.getUserId());
				conds.put("inChargeUserIdsMark", inChargeUserIdsMark+",["+user.getUserId()+"]");
				conds.put("inChargeDepts", inChargeDepts);

				conds.put("padInput", input);
				conds.put("taskStatus", status);// 查询未完成和已完成
				conds.put("isOutDate", isLate);
				conds.put("userType", 2);
				dataList = tskMicroTaskService.getTaskListPage(conds, page);
			} else {
				String userIdMark = "[" + user.getUserId() + "]";
				conds.put("userId", user.getUserId());
				conds.put("userIdMark", userIdMark);

				// 页面查询条件
				conds.put("padInput", input);
				conds.put("taskStatus", status);
				conds.put("isOutDate", isLate);
				conds.put("userType", 3);
				dataList = tskMicroTaskService.getMyTaskListPage(conds, page);
			}
            logger.info("pad端任务接口getTaskListCount完成，account:"+account+",input:"+input+",status:"+status+",isLate:"+isLate);
			return String.valueOf(dataList.getPage().getTotalRowsAmount());
		} catch (Exception e) {
			logger.error("getTaskListCount", e);
			return null;
		}
	}

	// 执行电话/实地营销任务列表
	// 获取所有电话列表input值为空,status传-1
	public String getPhoneTaskList(String account, String input,
			Integer taskId, Integer pageNumber, Integer status) {
		try {
            logger.info("pad端任务接口getPhoneTaskList开始，account:"+account+",input:"+input+",status:"+status+",taskId:"+taskId+",pageNumber:"+pageNumber);
			SysUser user = sysUserService.getAllUserByAccount(account);

			HttpServletRequest request = XFireServletController.getRequest();
			String contentUrl = request.getContextPath();

			JSONArray jsonArray = new JSONArray();
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.registerJsonValueProcessor(Date.class,
					new JsonDateValueProcessor());

			Page page = new Page();
			page.setCurrentPage(pageNumber);

			Map<String, Object> parameterMap = new HashMap<String, Object>();
			parameterMap.put("taskId", taskId);
			parameterMap.put("userId", user.getUserId());
			parameterMap.put("phoneNumber", input);
			parameterMap.put("myExc", 1);
			if (status != null && status != -1) {
				parameterMap.put("isFinish", status);
			}
			PageUtil<TskMicroTaskTarget> list = tskMicroTaskTargetService
					.getTargetListByPage(parameterMap, page);

			List<TaskTarget> plist = new ArrayList<TaskTarget>();
			for (TskMicroTaskTarget t : list.getItems()) {
				TaskTarget tt = new TaskTarget();
				tt.setIsNoGood(t.getIsNoGood());
				tt.setTaskId(t.getTaskId());
				tt.setCustomerId(t.getCustomerId());
				tt.setCustomerName(t.getCustomerName());
				tt.setCustomerTitle(t.getCustomerTitle());
				tt.setAddress(t.getAddress());
				tt.setIdCard(t.getIdCard());
				tt.setCallTime(DateUtil.convertDateToString(
						"yyyy-MM-dd HH:mm:ss", t.getCallDate()));
				tt.setContactStatus(t.getIsFinish());
				tt.setPhone(t.getPhoneNumber());
				tt.setRecordTime(DateUtil.convertDateToString(
						"yyyy-MM-dd HH:mm:ss", t.getRecordDate()));
				tt.setRecordAddress(t.getRecordAddress());
				tt.setRemark(t.getRemark());
				String fileName = "";
				if(t.getFileId()!=null){
                    Integer curUserId = sysUploadFileService.getSysUploadFileById(t.getFileId()).getUploadUserId();
                    CustomerData data = new CustomerData();
                    data.setCreateUser(curUserId);
					File f = sysUploadFileService.getRealFile(t.getFileId(),data);
					if(f!=null){
						fileName = f.getAbsolutePath();
					}
				}
				tt.setFileUrl(fileName);
				plist.add(tt);
			}
            logger.info("pad端任务接口getPhoneTaskList完成，account:"+account+",input:"+input+",status:"+status+",taskId:"+taskId+",pageNumber:"+pageNumber);
			String result = jsonArray.fromObject(plist, jsonConfig).toString();
			return result;
		} catch (Exception e) {
			logger.error("getPhoneMarketList", e);
			return null;
		}
	}

	// 执行电话/实地营销任务列表总数
	// 获取所有电话列表总数input值为空,status传-1
	public String getPhoneTaskListCount(String account, String input,
			Integer taskId, Integer status) {
		try {
            logger.info("pad端任务接口getPhoneTaskListCount开始，account:"+account+",input:"+input+",status:"+status+",taskId:"+taskId);
			SysUser user = sysUserService.getAllUserByAccount(account);

			Page page = new Page();

			Map<String, Object> parameterMap = new HashMap<String, Object>();
			parameterMap.put("taskId", taskId);
			parameterMap.put("userId", user.getUserId());
			parameterMap.put("phoneNumber", input);
			if (status != null && status != -1) {
				parameterMap.put("isFinish", status);
			}

			PageUtil<TskMicroTaskTarget> list = tskMicroTaskTargetService
					.getTargetListByPage(parameterMap, page);
            logger.info("pad端任务接口getPhoneTaskListCount完成，account:"+account+",input:"+input+",status:"+status+",taskId:"+taskId);
			return String.valueOf(list.getPage().getTotalRowsAmount());
		} catch (Exception e) {
			logger.error("getPhoneMarketCount", e);
			return null;
		}
	}

	// 执行电话/实地营销任务列表
	// 获取所有电话列表input值为空,status传-1
	public String getMarketTaskList(String account, String startDate,
			String endDate, String input, Integer taskId, Integer pageNumber) {
		try {
            logger.info("pad端任务接口getMarketTaskList开始，account:"+account+",input:"+input+",startDate:"+startDate+"," +
                    "taskId:"+taskId+",endDate:"+endDate+",pageNumber:"+pageNumber);
			SysUser user = sysUserService.getAllUserByAccount(account);

			HttpServletRequest request = XFireServletController.getRequest();

			JSONArray jsonArray = new JSONArray();
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.registerJsonValueProcessor(Date.class,
					new JsonDateValueProcessor());

			Page page = new Page();
			page.setCurrentPage(pageNumber);

			Map<String, Object> parameterMap = new HashMap<String, Object>();
			parameterMap.put("taskId", taskId);
			parameterMap.put("userId", user.getUserId());
			if (StringUtils.isNotEmpty(startDate)) {
				parameterMap.put("recordDateFrom",
						DateUtil.strToDate(startDate, "yyyy-MM-dd HH:mm:ss"));
			}
			if (StringUtils.isNotEmpty(endDate)) {
				parameterMap.put("recordDateTo",
						DateUtil.strToDate(endDate, "yyyy-MM-dd HH:mm:ss"));
			}
			parameterMap.put("recordAddress", input);
			parameterMap.put("isPad", 1);
			parameterMap.put("sortFiled", 3);
			parameterMap.put("myExc", 1);

			PageUtil<TskMicroTaskTarget> list = tskMicroTaskTargetService
					.getTargetListByPage(parameterMap, page);

			List<TaskTarget> plist = new ArrayList<TaskTarget>();
			for (TskMicroTaskTarget t : list.getItems()) {
				TaskTarget tt = new TaskTarget();
				tt.setTaskId(t.getTaskId());
				tt.setIsNoGood(t.getIsNoGood());
				tt.setCustomerId(t.getCustomerId());
				tt.setCustomerName(t.getCustomerName());
				tt.setCustomerTitle(t.getCustomerTitle());
				tt.setCallTime(DateUtil.convertDateToString(
						"yyyy-MM-dd HH:mm:ss", t.getCallDate()));
				tt.setContactStatus(t.getIsFinish());
				tt.setPhone(t.getPhoneNumber());
				tt.setRecordTime(DateUtil.convertDateToString(
						"yyyy-MM-dd HH:mm:ss", t.getRecordDate()));
				tt.setRecordAddress(t.getRecordAddress());
				String fileName = "";
				if(t.getFileId()!=null){
                    Integer curUserId = sysUploadFileService.getSysUploadFileById(t.getFileId()).getUploadUserId();
                    CustomerData data = new CustomerData();
                    data.setCreateUser(curUserId);
					File f = sysUploadFileService.getRealFile(t.getFileId(),data);
					if(f!=null){
						fileName = f.getAbsolutePath();
					}
				}
				tt.setFileUrl(fileName);
				tt.setTaskTargetId(t.getTaskTargetId());

				plist.add(tt);
			}
            logger.info("pad端任务接口getMarketTaskList完成，account:"+account+",input:"+input+",startDate:"+startDate+"," +
                    "taskId:"+taskId+",endDate:"+endDate+",pageNumber:"+pageNumber);
			String result = jsonArray.fromObject(plist, jsonConfig).toString();
			return result;
		} catch (Exception e) {
			logger.error("getPhoneMarketList", e);
			return null;
		}
	}

	// 执行电话/实地营销任务列表总数
	// 获取所有电话列表总数input值为空,status传-1
	public String getMarketTaskListCount(String account, String startDate,
			String endDate, String input, Integer taskId) {
		try {
            logger.info("pad端任务接口getMarketTaskListCount开始，account:"+account+",input:"+input+",startDate:"+startDate+"," +
                    "taskId:"+taskId+",endDate:"+endDate);
			SysUser user = sysUserService.getAllUserByAccount(account);

			Page page = new Page();

			Map<String, Object> parameterMap = new HashMap<String, Object>();
			parameterMap.put("taskId", taskId);
			parameterMap.put("userId", user.getUserId());
			if (StringUtils.isNotEmpty(startDate)) {
				parameterMap.put("recordDateFrom",
						DateUtil.strToDate(startDate, "yyyy-MM-dd HH:mm:ss"));
			}
			if (StringUtils.isNotEmpty(endDate)) {
				parameterMap.put("recordDateTo",
						DateUtil.strToDate(endDate, "yyyy-MM-dd HH:mm:ss"));
			}
			parameterMap.put("recordAddress", input);
			parameterMap.put("isPad", 1);

			PageUtil<TskMicroTaskTarget> list = tskMicroTaskTargetService
					.getTargetListByPage(parameterMap, page);
            logger.info("pad端任务接口getMarketTaskListCount完成，account:"+account+",input:"+input+",startDate:"+startDate+"," +
                    "taskId:"+taskId+",endDate:"+endDate);
			return String.valueOf(list.getPage().getTotalRowsAmount());
		} catch (Exception e) {
			logger.error("getPhoneMarketCount", e);
			return null;
		}
	}

	// 日程管理模块

	/**
	 * PAD日程列表/搜索日程
	 * 
	 * @author zhangfp
	 * 
	 * @param account
	 *            用户登录账号
	 * @param input
	 *            姓名或地址
	 * @param status
	 *            日程状态
	 * @param contactWayType
	 *            联系方式
	 * @param page
	 *            查询的起始页
	 * @param isLate
	 *            过期标志
	 * @param customerId
	 *            客戶ID
	 * @return
	 */
	public String getScheduleList(String account, String input, Integer status,
			Integer contactWayType, Integer page, Integer isLate,
			Integer customerId) {
		try {
            logger.info("pad端任务接口getScheduleList开始，account:"+account+",input:"+input+",status:"+status+"," +
                    "contactWayType:"+contactWayType+",isLate:"+isLate+",page:"+page);
			JSONArray jsonArray = new JSONArray();

			Map<String, Object> paramMap = this.proTskScheduleQueryCondition(
					account, input, status, contactWayType, isLate, customerId);
			paramMap.put("isPad", 1);
			Page curPage = new Page();
			curPage.setCurrentPage(page);

			List<TskSchedule> tskScheduleList = tskScheduleService
					.getScheduleListByPage(paramMap, curPage);
			if (tskScheduleList != null && !tskScheduleList.isEmpty()) {
				for (TskSchedule tskSchedule : tskScheduleList) {
					Map<String, Object> contMap = this
							.getTskScheduleMap(tskSchedule);
					jsonArray.add(contMap);
				}
			}
            logger.info("pad端任务接口getScheduleList完成，account:"+account+",input:"+input+",status:"+status+"," +
                    "contactWayType:"+contactWayType+",isLate:"+isLate+",page:"+page);
			return jsonArray.toString();
		} catch (Exception e) {
			logger.error("RecordWebServicesImpl % getScheduleList", e);
			return null;
		}
	}

	/**
	 * PAD日程列表总数
	 * 
	 * @param account
	 *            用户账号
	 * @param input
	 *            客户姓名或地址
	 * @param status
	 *            日程状态
	 * @param contactWayType
	 *            联系方式
	 * @param isLate
	 *            是否过期
	 * @param customerId
	 *            客户ID
	 * @return
	 */
	public String getScheduleListCount(String account, String input,
			Integer status, Integer contactWayType, Integer isLate,
			Integer customerId) {
		try {
            logger.info("pad端任务接口getScheduleListCount开始，account:"+account+",input:"+input+",status:"+status+"," +
                    "contactWayType:"+contactWayType+",isLate:"+isLate+",customerId:"+customerId);
			Map<String, Object> paramMap = this.proTskScheduleQueryCondition(
					account, input, status, contactWayType, isLate, customerId);
			paramMap.put("isPad", 1);
//			paramMap.put("noCancel", 1);
			Integer rows = tskScheduleService.getCount(paramMap);
            logger.info("pad端任务接口getScheduleListCount完成，account:"+account+",input:"+input+",status:"+status+"," +
                    "contactWayType:"+contactWayType+",isLate:"+isLate+",customerId:"+customerId);
			return String.valueOf(rows);
		} catch (Exception e) {
			logger.error("RecordWebServicesImpl%getScheduleListCount", e);
			return null;
		}
	}

	/**
	 * PAD标记日程沟通进度
	 * 
	 * @param scheduleId
	 *            日程id
	 * @param contactProgressId
	 *            沟通进度id
	 * @return
	 */
	public String setContactProgress(Integer scheduleId,
			Integer contactProgressId) {
		try {
            logger.info("pad端任务接口setContactProgress开始，scheduleId:"+scheduleId+",contactProgressId:"+contactProgressId);
			TskSchedule tskSchedule = new TskSchedule();
			tskSchedule.setScheduleId(scheduleId);
			tskSchedule.setCommProgressId(contactProgressId);
			// 标记日程沟通进度
			tskScheduleService.updateSchedule(tskSchedule);
            logger.info("pad端任务接口setContactProgress完成，scheduleId:"+scheduleId+",contactProgressId:"+contactProgressId);
			return "true";
		} catch (Exception e) {
			logger.error("RecordWebServiceImpl%setContactProgress", e);
			return "false";
		}
	}

	/**
	 * 标记日程完成
	 * 
	 * @param scheduleId
	 * @return
	 */
	public String setScheduleComplete(Integer scheduleId) {
		try {
            logger.info("pad端任务接口setScheduleComplete开始，scheduleId:"+scheduleId);
			TskSchedule tskSchedule = new TskSchedule();
			tskSchedule.setScheduleId(scheduleId);
			// 设置日程已完成
			tskSchedule.setStatus(1);
            TskSchedule tmpSchedule = tskScheduleService.getScheduleById(tskSchedule.getScheduleId());
                /*
                 * 已完成。当改成已完成时，要去资料记录表里根据customer_id查找离当前日程的电话联系记录时间(或当前操作的时间
                 * )最近的一笔记录， 并把record_info_id更新到日程表
                 *
                 * ====具体的规则为：=======
                 * 只有已完成的电话联系日程允许用户查看和编辑通话记录
                 *
                 * 如果用户在当前电话联系日程有多次通话，则：
                 *
                 * 1 如果用户标记日程完成时间早于日程安排的时间，则通话记录显示离用户标记日程完成的时间最近的一次通话记录
                 *
                 * 2 如果用户标记日程完成时间晚于日程安排的时间，则通话记录显示离日程安排的时间最近的一次通话记录
                 */
            if (tmpSchedule.getContactType().equals(1)){
                if (tmpSchedule.getCustomerId() != null && tmpSchedule.getContactDate() != null){
                    Map<String,Object> paramMap = new HashMap<String, Object>();
                    paramMap.put("customerId",tmpSchedule.getCustomerId());
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date contactDate = tmpSchedule.getContactDate(); //日程的联系时间
                    Date nowDate = new Date(); //当前操作时间
                    //比较联系时间与当前时间的前后关系
                    if (contactDate.before(nowDate)){
                        //当前时间比联系时间晚
                        paramMap.put("contactDate",format.format(tmpSchedule.getContactDate()));
                    }else {
                        paramMap.put("contactDate",format.format(nowDate));
                    }
                    Integer recordInfoId = recordInfoService.getRecentlyRecordForSchedule(paramMap);
                    tskSchedule.setRecordInfoId(recordInfoId);
                }
            }
			tskScheduleService.updateSchedule(tskSchedule);
            logger.info("pad端任务接口setScheduleComplete完成，scheduleId:"+scheduleId);
			return "true";
		} catch (Exception e) {
			logger.error("RecordWebServiceImpl%setScheduleComplete", e);
			return "false";
		}
	}

	/**
	 * 标记日程未完成
	 * 
	 * @param scheduleId
	 * @return
	 */
	public String cancelScheduleComplete(Integer scheduleId) {
		try {
            logger.info("pad端任务接口cancelScheduleComplete开始，scheduleId:"+scheduleId);
			TskSchedule tskSchedule = new TskSchedule();
			tskSchedule.setScheduleId(scheduleId);
			// 设置日程未完成
			tskSchedule.setStatus(0);
			tskScheduleService.updateSchedule(tskSchedule);
            logger.info("pad端任务接口cancelScheduleComplete完成，scheduleId:"+scheduleId);
			return "true";
		} catch (Exception e) {
			logger.error("RecordWebServiceImpl%setScheduleComplete", e);
			return "false";
		}
	}

	/**
	 * PAD客户意向产品列表
	 * 
	 * @param account
	 * @param customerId
	 * @param page
	 * @return
	 */
	public String getIntentProductList(String account, Integer customerId,
			Integer page) {
		try {
            logger.info("pad端任务接口getIntentProductList开始，account:"+account+",customerId:"+customerId+",page:"+page);
			JSONArray jsonArray = new JSONArray();
			SysUser sysUser = sysUserService.getAllUserByAccount(account);
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("userId", sysUser.getUserId());
			paramMap.put("customerId", customerId);
			Page curPage = new Page();
			curPage.setCurrentPage(page);

			List<PdtProductCustomerBean> productCustomerBeanList = tskScheduleService
					.getCusPdtProductListForPad(paramMap, curPage);
			if (productCustomerBeanList != null
					&& !productCustomerBeanList.isEmpty()) {
				for (PdtProductCustomerBean productCustomerBean : productCustomerBeanList) {
					Map<String, String> resultMap = this
							.getPdtProductCustomerBeanMap(productCustomerBean);
					jsonArray.add(resultMap);
				}
			}
            logger.info("pad端任务接口getIntentProductList完成，account:"+account+",customerId:"+customerId+",page:"+page);
			return jsonArray.toString();
		} catch (Exception e) {
			logger.error("RecordWebServicesImpl%getIntentProductList", e);
			return null;
		}
	}

	/**
	 * PAD客户意向产品列表总数
	 * 
	 * @param account
	 * @param customerId
	 * @return
	 */
	public String getIntentProductCount(String account, Integer customerId) {
		try {
            logger.info("pad端任务接口getIntentProductCount开始，account:"+account+",customerId:"+customerId);
			// 根据登录用户的账号，求得用户所有信息
			SysUser sysUser = sysUserService.getAllUserByAccount(account);
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("userId", sysUser.getUserId());
			paramMap.put("customerId", customerId);
			Integer rows = tskScheduleService
					.getCustomerPDTProductCount(paramMap);
            logger.info("pad端任务接口getIntentProductCount完成，account:"+account+",customerId:"+customerId);
			return String.valueOf(rows);
		} catch (Exception e) {
			logger.error("RecordWebServicesImpl%getIntentProductCount", e);
			return null;
		}
	}

	/**
	 * 客户任务安排
	 * 
	 * @param account
	 * @param customerId
	 * @param pageNumber
	 * @return
	 */
	public String getCustomerTaskList(String account, Integer customerId,
			Integer pageNumber) {
		try {
            logger.info("pad端任务接口getCustomerTaskList开始，account:"+account+",customerId:"+customerId+",pageNumber:"+pageNumber);
			SysUser user = sysUserService.getAllUserByAccount(account);

			JSONObject jsonObject = new JSONObject();
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.registerJsonValueProcessor(Date.class,
					new JsonDateValueProcessor());

			Page page = new Page();
			page.setCurrentPage(pageNumber);

			Map<String, Object> parameterMap = new HashMap<String, Object>();
			// 判断是否是业务主管
            Boolean isInChargeOf = deptFacadeService.isInChargeOfDepartment(user.getUserId().toString());
			if (isInChargeOf){ //是业务主管
			    String inChargeUserIds = "";
    			Integer[] inChargeUserIdsIntegers = deptFacadeService.getInChargeOfDeptUserIds(user.getUserId());
                if (inChargeUserIdsIntegers != null&& inChargeUserIdsIntegers.length > 0) {
                    for (Integer userId : inChargeUserIdsIntegers) {
                        inChargeUserIds += userId.intValue() + ","; // 负责的机构人员
                    }
                    if (inChargeUserIds.length() > 0) {
                        inChargeUserIds = inChargeUserIds.substring(0,(inChargeUserIds.length() - 1));
                    }
                }else{
                    inChargeUserIds=user.getUserId().toString();
                }
                parameterMap.put("inChargeUserIds",inChargeUserIds);
			}else{
			    parameterMap.put("userId", user.getUserId());// 执行者是当前登录用户的数据
			}
			
			parameterMap.put("customerId", customerId);
			PageUtil<TskMicroTaskTarget> list = tskMicroTaskTargetService
					.getTargetListForPad(parameterMap, page);

			List<CustomerTaskInfo> plist = new ArrayList<CustomerTaskInfo>();
			for (TskMicroTaskTarget t : list.getItems()) {
				CustomerTaskInfo tt = new CustomerTaskInfo();
				tt.setTaskId(t.getTaskId());
				tt.setTaskName(t.getTaskTitle());
				tt.setTaskTypeName(getTaskTypeName(t.getTaskType()));
				tt.setStatus(t.getIsFinish());
				tt.setStartDate(t.getStartDate());
				tt.setEndDate(t.getEndDate());
				tt.setCreateUser(t.getCallUserName());
				tt.setRemark(t.getRemark());

				plist.add(tt);
			}

			CustomerTaskInfoPage customerTaskInfoPage = new CustomerTaskInfoPage();
			customerTaskInfoPage.setDataList(plist);
			customerTaskInfoPage.setTotalCount(list.getPage()
					.getTotalRowsAmount());

			String result = jsonObject.fromObject(customerTaskInfoPage,
					jsonConfig).toString();
            logger.info("pad端任务接口getCustomerTaskList完成，account:"+account+",customerId:"+customerId+",pageNumber:"+pageNumber);
			return result;
		} catch (Exception e) {
			logger.error("getCustomerTaskList", e);
			return null;
		}
	}
	
	// 执行实地营销任务
	public String executeTask(String account, Integer taskId,
			Integer customerId, String address, String remark, String lat,
			String lng, String recordDate) {
		try {
            logger.info("pad端任务接口executeTask开始，account:"+account+",customerId:"+customerId+",taskId:"+taskId+",address:"+address+",remark:"+remark+"," +
                    "lat:"+lat+",lng:"+lng+",recordDate:"+recordDate);
			SysUser user = sysUserService.getAllUserByAccount(account);
			// 通过用户ID查询任务数
			Integer isOldCus = lnLoanService.getLoanCountByCusId(customerId);
			TskMicroTaskTarget t = new TskMicroTaskTarget();
			t.setTaskId(taskId);
			t.setUserId(user.getUserId());
			t.setCustomerId(customerId);
			t.setRecordAddress(address);
			t.setRemark(remark);
			t.setIsFinish(1);
			t.setCreateUser(user.getUserId());
			t.setCreateDate(Calendar.getInstance().getTime());
			// 通过返回的任务数确定是否老客户
			if (isOldCus != null && isOldCus > 0) {
				t.setIsOldCustomer(1);
			} else {
				t.setIsOldCustomer(0);
			}
			t.setGpsLat(lat);
			t.setGpsLng(lng);
			t.setRecordDate(DateUtil.convertStringToDate("yyyy-MM-dd HH:mm:ss",
					recordDate));
			tskMicroTaskTargetService.addTaskTarget(t);
            logger.info("pad端任务接口executeTask完成，account:"+account+",customerId:"+customerId+",taskId:"+taskId+",address:"+address+",remark:"+remark+"," +
                    "lat:"+lat+",lng:"+lng+",recordDate:"+recordDate);
			return "true";
		} catch (Exception e) {
			logger.error("executeTask", e);
			return "false";
		}
	}

	// 关联录音
	public String relatedRecord(String account, Integer taskTargetId,
			Integer customerId) {
		try {
            logger.info("pad端任务接口relatedRecord开始，account:"+account+",customerId:"+customerId+",taskTargetId:"+taskTargetId);
			SysUser user = sysUserService.getAllUserByAccount(account);
			BaseCrmCustomer crm = crmCustomerService
					.getCrmCustomerById(customerId);
			TskMicroTaskTarget t = tskMicroTaskTargetService
					.getMicroTaskTargetById(taskTargetId);
			RecordInfo detail = recordInfoService.queryAllById(t
					.getRecordInfoId());
			
			//关联客户到联系记录
			PhoneConfig phoneConfig = phoneConfigService.query(user.getUserId());
			recordInfoService.updateRelationRecord(crm, phoneConfig, detail.getRecordInfoId(), customerId, true);
			
			//关联新/老客户(如果该客户之前有过贷款记录则自动认为老客户，否则为新客户)
			Integer isOld=lnLoanService.getLoanCountByCusId(customerId);
			if (isOld != null && isOld > 0) {
                t.setIsOldCustomer(1);//老客户
            }else{
                t.setIsOldCustomer(0);//新客户
            }

			// 更新任务目标表
			t.setCustomerId(crm.getCustomerId());
			t.setCustomerName(crm.getCustomerName());
			t.setUpdateUser(user.getUserId());
			t.setUpdateDate(Calendar.getInstance().getTime());
			tskMicroTaskTargetService.editTskTaskTargetCustomer(t);

			// 关联录音的时候往客户资料表中新增数据
			Audio audio = new Audio();
			String photoName = "";
			try {
				BaseCrmCustomer cus = crmCustomerService
						.getCrmCustomerById(Integer.valueOf(customerId));
				if (cus != null) {
					if (StringUtils.isNotEmpty(cus.getCustomerName())) {
						photoName += cus.getCustomerName() + "_";
					}
				}
			} catch (Exception e) {
				logger.error("error:", e);
			}
			try {
				Event event = customerDataService.getEventTypeListById(1);
				if (event != null) {
					if (StringUtils.isNotEmpty(event.getEventName())) {
						photoName += event.getEventName() + "_";
					}
				}
			} catch (Exception e) {
				logger.error("error:", e);
			}

			if (t.getRecordDate() != null) {
				audio.setRecordDate(t.getRecordDate());
			}
			if (t.getRecordDate() != null) {
				photoName += DateUtil.convertDateToString("yyyyMMdd(HHmmss)",
						t.getRecordDate());
			}
			audio.setAudioName(photoName);
			// 时长
		    audio.setRecordLength(detail.getCallTime());
			// 取得用户id
			if (user != null) {
				audio.setCreateUser(user.getUserId());
                audio.setUpdateUser(user.getUserId());
			}
			audio.setRemark(detail.getRemark());
            audio.setFileId(detail.getFileId());
			audio.setCreateDate(detail.getCreateDate());
            audio.setUpdateDate(detail.getCreateDate());
			UUID uuid = UUID.randomUUID();
			audio.setDatUuid(uuid.toString());

			Map<String, Object> parameterMap = new HashMap<String, Object>();
			parameterMap.put("customerId", customerId);
			parameterMap.put("eventId", 1);
			List<CustomerData> datas = customerDataService
					.getCustomerDataByParameterMap(parameterMap);
			if (datas.size() > 0) {
				// 主资料已经存在
				CustomerData d = datas.get(0);

				// 保存录音数据
				audio.setCustomerDataId(d.getCustomerDataId());
				dataAudioService.addNewAudio(audio);// 入库
			} else {
				CustomerData data = new CustomerData();
				data.setCustomerId(customerId);
				data.setEventId(1);
				data.setCreateUser(user.getUserId());
				data.setCreateDate(Calendar.getInstance().getTime());
				customerDataService.addNewCustomerData(data);

				// 保存录音数据
				audio.setCustomerDataId(data.getCustomerDataId());
				dataAudioService.addNewAudio(audio);// 入库
			}
            logger.info("pad端任务接口relatedRecord完成，account:"+account+",customerId:"+customerId+",taskTargetId:"+taskTargetId);
			return "true";
		} catch (Exception e) {
			logger.error("relatedRecord", e);
			return "false";
		}
	}

	/**
	 * PAD判断客户是否存在未完成日程
	 * 
	 * @param account
	 * @param customerId
	 * @return
	 */
	public String getUnFinishedSchedule(String account, Integer customerId) {
		try {
            logger.info("pad端任务接口getUnFinishedSchedule开始，account:"+account+",customerId:"+customerId);
			SysUser sysUser = sysUserService.getAllUserByAccount(account);

			JSONObject jsonObject = new JSONObject();
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.registerJsonValueProcessor(Date.class,
					new JsonDateValueProcessor());

			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("userId", sysUser.getUserId());
			paramMap.put("customerId", customerId);
			TskSchedule tskSchedule = tskScheduleService
					.getUnFinishedSchedule(paramMap);

			if (tskSchedule != null) {
				Map<String, Object> resutlMap = new HashMap<String, Object>();
				resutlMap.put("scheduleId", tskSchedule.getScheduleId());
				resutlMap.put("customerId", tskSchedule.getScheduleId());
				resutlMap.put("customerName", tskSchedule
						.getPdtProductCustomerBean().getCustomerName());
				resutlMap.put("customerTitle", tskSchedule
						.getPdtProductCustomerBean().getCustomerTitle());
				resutlMap.put("phone", tskSchedule.getPdtProductCustomerBean()
						.getPhone());
				if (tskSchedule.getContactDate() != null) {
					SimpleDateFormat format = new SimpleDateFormat(
							"yyyy-MM-dd HH:mm");
					String contactTime = format.format(tskSchedule
							.getContactDate());
					resutlMap.put("contactTime", contactTime);
				} else {
					resutlMap.put("contactTime", "");
				}
				resutlMap.put("contactProgress",
						tskSchedule.getCommProgressId());
				resutlMap.put("contactWay", tskSchedule.getContactType());
				resutlMap.put("remark", tskSchedule.getRemark());
				resutlMap.put("status", tskSchedule.getStatus());
				resutlMap.put("address", tskSchedule
						.getPdtProductCustomerBean().getAddress());

				String result = jsonObject.fromObject(resutlMap, jsonConfig)
						.toString();
                logger.info("pad端任务接口getUnFinishedSchedule完成，account:"+account+",customerId:"+customerId);
				return result;
			} else {
				return null;
			}
		} catch (Exception e) {
			logger.error("TaskWebServicesImpl % getUnFinishedSchedule", e);
			return null;
		}
	}

	/**
	 * 处理查看日程所用到的参数信息
	 * 
	 * @param account
	 * @param input
	 * @param status
	 * @param contactWayType
	 * @param isLate
	 * @return
	 */
	private Map<String, Object> proTskScheduleQueryCondition(String account,
			String input, Integer status, Integer contactWayType,
			Integer isLate, Integer customerId) {
		// 通过用户账号，得到当前用户信息
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("noCancel", 1);
		SysUser user = sysUserService.getAllUserByAccount(account);
		paramMap.put("userId", user.getUserId());
		if (!customerId.equals(-1)) {
			paramMap.put("customerId", customerId);
		}

		// 今天日程
		if (isLate != null && isLate.equals(0)) {
			todayDiff(paramMap);
		} else if (isLate != null && isLate.equals(2)) {
			// 已过期日程
			paramMap.put("past", 1);
		} else if (isLate != null && isLate.equals(1)) {
			// 所有日程
		}
		// 所有日程
		if (!input.equals("")) {
			paramMap.put("customerName", input);
		}
		if (!status.equals(-1)) {
			paramMap.put("status", status);
			paramMap.put("noCancel", null);
		}
		if (!contactWayType.equals(-1)) {
			paramMap.put("contactTypeId", contactWayType);
		}
		// if(!(input.equals("") && status.equals(-1) &&
		// contactWayType.equals(-1))){
		// paramMap.put("status",status);
		// paramMap.put("contactTypeId",contactWayType);
		// paramMap.put("customerName",input);
		// }

		return paramMap;
	}

	private Map<String, String> getPdtProductCustomerBeanMap(
			PdtProductCustomerBean customerBean) {
		Map<String, String> resultMap = new HashMap<String, String>();
		try {
			if (customerBean != null) {
				SimpleDateFormat format = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm");
				Date addDate = customerBean.getCreateDate();
				String addDateStr = format.format(addDate);

				resultMap.put("addTime", addDateStr);
				resultMap.put("intentProduct", customerBean.getProductName()==null?"":customerBean.getProductName());
				resultMap.put("intentState", customerBean.getRemark());
				resultMap.put("addPerson", customerBean.getUserName());
			}
			return resultMap;
		} catch (Exception e) {
			logger.error(
					"RecordWebServicesImpl % getPdtProductCustomerBeanMap", e);
			return null;
		}
	}

	/**
	 * 将TskSchedule 对象转换为 Map
	 * 
	 * @param tskSchedule
	 * @return
	 */
	private Map<String, Object> getTskScheduleMap(TskSchedule tskSchedule) {
		Map<String, Object> resutlMap = new HashMap<String, Object>();
		if (tskSchedule != null) {
			resutlMap.put("scheduleId", tskSchedule.getScheduleId());
			resutlMap.put("customerId", tskSchedule.getCustomerId());
			PdtProductCustomerBean proCustomerBean = tskSchedule
					.getPdtProductCustomerBean();
			if (proCustomerBean != null) {
				resutlMap
						.put("customerName", proCustomerBean.getCustomerName());
                if (proCustomerBean.getCustomerTitle() == null){
                    resutlMap.put("customerTitle","");
                }else {
                    resutlMap.put("customerTitle",proCustomerBean.getCustomerTitle());
                }
				resutlMap.put("phone", proCustomerBean.getCphNumber());
                if (proCustomerBean.getAddress() == null){
                    resutlMap.put("address", "");
                }else {
                    resutlMap.put("address", proCustomerBean.getAddress());
                }
				resutlMap
						.put("intentProduct", proCustomerBean.getProductName());
                if (proCustomerBean.getRemark() == null){
                    resutlMap.put("intentState", "");
                }else {
                    resutlMap.put("intentState", proCustomerBean.getRemark());
                }
				resutlMap.put("isNoGood", proCustomerBean.getIsNoGood());
			}

			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			Date contactDate = tskSchedule.getContactDate();
			String contactDateStr = null;
			if (contactDate != null) {
				contactDateStr = format.format(contactDate);
			}
			Date addTime = tskSchedule.getAddDate();
			String addTimeStr = null;
			if (addTime != null) {
				addTimeStr = format.format(addTime);
			}
			resutlMap.put("contactTime", contactDateStr);
			resutlMap.put("contactProgress", tskSchedule.getCommProgressId());
			resutlMap.put("contactWay", tskSchedule.getContactType());
            if (tskSchedule.getRemark() == null){
                resutlMap.put("remark", "");
            }else {
                resutlMap.put("remark", tskSchedule.getRemark());
            }
			resutlMap.put("status", tskSchedule.getStatus());

			resutlMap.put("addTime", addTimeStr);
		}
		return resutlMap;
	}

	/**
	 * 得到当天时间差
	 * 
	 * @param paramMap
	 */
	private void todayDiff(Map<String, Object> paramMap) {
		SimpleDateFormat startFormat = new SimpleDateFormat(
				"yyyy-MM-dd 00:00:00");
		SimpleDateFormat endFormat = new SimpleDateFormat("yyyy-MM-dd 23:59:59");
		Date today = new Date();
		String startTodayDateStr = startFormat.format(today);
		String endTodayDateStr = endFormat.format(today);

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date contactDateFrom = format.parse(startTodayDateStr);
			Date contactDateEnd = format.parse(endTodayDateStr);

			paramMap.put("contactDateFrom", contactDateFrom);
			paramMap.put("contactDateEnd", contactDateEnd);
		} catch (ParseException e) {
			logger.error("RecordWebServicesImpl%todayDiff", e);
		}
	}

	/** getter setter **/
	public void setTskMicroTaskTargetService(
			TskMicroTaskTargetService tskMicroTaskTargetService) {
		this.tskMicroTaskTargetService = tskMicroTaskTargetService;
	}

	public void setTskMicroTaskService(TskMicroTaskService tskMicroTaskService) {
		this.tskMicroTaskService = tskMicroTaskService;
	}

	public void setTskScheduleService(TskScheduleService tskScheduleService) {
		this.tskScheduleService = tskScheduleService;
	}

	public void setSysUserService(SysUserService sysUserService) {
		this.sysUserService = sysUserService;
	}

	public DeptFacadeService getDeptFacadeService() {
		return deptFacadeService;
	}

	public void setDeptFacadeService(DeptFacadeService deptFacadeService) {
		this.deptFacadeService = deptFacadeService;
	}

	public TskMicroTaskProgressService getTskMicroTaskProgressService() {
		return tskMicroTaskProgressService;
	}

	public void setTskMicroTaskProgressService(
			TskMicroTaskProgressService tskMicroTaskProgressService) {
		this.tskMicroTaskProgressService = tskMicroTaskProgressService;
	}

	public TskMicroTaskExecuteService getTskMicroTaskExecuteService() {
		return tskMicroTaskExecuteService;
	}

	public void setTskMicroTaskExecuteService(
			TskMicroTaskExecuteService tskMicroTaskExecuteService) {
		this.tskMicroTaskExecuteService = tskMicroTaskExecuteService;
	}

	public void setCrmCustomerService(CrmCustomerService crmCustomerService) {
		this.crmCustomerService = crmCustomerService;
	}

	public void setCustomerDataService(CustomerDataService customerDataService) {
		this.customerDataService = customerDataService;
	}

	public void setDataAudioService(DataAudioService dataAudioService) {
		this.dataAudioService = dataAudioService;
	}

	public void setRecordInfoService(RecordInfoService recordInfoService) {
		this.recordInfoService = recordInfoService;
	}

	public void setLnLoanService(LnLoanService lnLoanService) {
		this.lnLoanService = lnLoanService;
	}

	public void setPhoneConfigService(PhoneConfigService phoneConfigService) {
		this.phoneConfigService = phoneConfigService;
	}

	public void setSysUploadFileService(SysUploadFileService sysUploadFileService) {
		this.sysUploadFileService = sysUploadFileService;
	}
	
}
