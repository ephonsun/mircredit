/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :QianJie
 * Create Date:Mar 2, 2013
 */
package com.banger.mobile.webapp.action.microTask;

import java.util.*;

import com.banger.mobile.domain.model.microTask.TskMicroTaskExecute;
import com.banger.mobile.facade.microTask.TskMicroTaskExecuteService;
import com.banger.mobile.facade.specialDataAuth.SpecialDataAuthService;
import org.apache.commons.lang.StringUtils;

import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.model.microTask.TskMicroTask;
import com.banger.mobile.facade.dept.DeptFacadeService;
import com.banger.mobile.facade.microTask.TskMicroTaskService;
import com.banger.mobile.util.StringUtil;
import com.banger.mobile.webapp.action.BaseAction;

/**
 * @author QianJie
 * @version $Id: TskMicroTaskAction.java,v 0.1 Mar 2, 2013 12:01:56 PM QianJie
 *          Exp $
 */
public class TskMicroTaskListAction extends BaseAction {

	private static final long serialVersionUID = -3029151366017359601L;

	private TskMicroTaskService tskMicroTaskService;
	private DeptFacadeService deptFacadeService;
	private SpecialDataAuthService specialDataAuthService; // 特殊数据权限
	private TskMicroTaskExecuteService tskMicroTaskExecuteService;

	public void setTskMicroTaskExecuteService(
			TskMicroTaskExecuteService tskMicroTaskExecuteService) {
		this.tskMicroTaskExecuteService = tskMicroTaskExecuteService;
	}

	public void setSpecialDataAuthService(
			SpecialDataAuthService specialDataAuthService) {
		this.specialDataAuthService = specialDataAuthService;
	}

	// 查询条件
	private String taskTitle;
	private Integer taskType;
	private Date startDate;
	private Date endDate;
	private Integer taskStatus;
	private Integer isOutDate;
	private String assignUserType;
	private String assignUserIds;

	// 当然登录用户id
	private Integer loginUserId;
	private Integer loginDeptId;
	private Integer isInChargeOf;
	private String loginDeptIds;
	
	/**
	 * 返回当前登录者的管辖机构且不包含自己机构
	 */
	public void returnLoginDeptIds(){
	    String newDeptIds="";
	    Integer loginDid=this.getLoginInfo().getDeptId();
	    Integer[] inChargeDeptsIntegers = deptFacadeService.getInChargeOfDeptIds();
	    if(inChargeDeptsIntegers!=null){
	        for (Integer deptId : inChargeDeptsIntegers) {
//	            if(!deptId.equals(loginDid)){
	                if(newDeptIds.equals("")){
	                    newDeptIds=deptId.toString();
	                }else{
	                    newDeptIds=newDeptIds+","+deptId.toString();
	                }
//	            }
	        }
	    }
	    loginDeptIds=newDeptIds;
	}

	/**
	 * 所有任务
	 * 
	 * @return
	 */
	public String allTaskList() {
		try {
			loginUserId = this.getLoginInfo().getUserId();
			loginDeptId = this.getLoginInfo().getDeptId();
			int userType = 0;
			String inChargeUserIds = "", inChargeUserIdsMark = "", inChargeDepts = "";

			Map<String, Object> conds = new HashMap<String, Object>();
			
			// 客户经理 显示我执行的任务
			isInChargeOf = 0;
			userType = 3;
			inChargeUserIds = String.valueOf(loginUserId);
			inChargeDepts = "0";

			String userIdMark = "[" + loginUserId + "]";
			conds.put("userId", this.getLoginInfo().getUserId());
			conds.put("userIdMark", userIdMark);
			conds.put("taskStatus", -1);// 查询未完成和已完成
			conds.put("inChargeUserIds", inChargeUserIds);
			conds.put("inChargeUserIdsMark", userIdMark);
			conds.put("inChargeDepts", inChargeDepts);
			conds.put("isInChargeOf", isInChargeOf);
			
			/*
			// 判断是否是业务主管
			if (deptFacadeService.isInChargeOfDepartment()) {
			    returnLoginDeptIds();
				isInChargeOf = 1;
				String roleIds = StringUtil.getIdsString(getLoginInfo()
						.getRoles());
				boolean flag = specialDataAuthService.getSysDataAuthCondition(
						roleIds, "taskInfo");
				if (flag) {
					inChargeDepts = "-1";
				} else {
					return SUCCESS;
					//TODO:@author雷电 修改
					//Integer[] inChargeDeptsIntegers = deptFacadeService
					//		.getInChargeOfDeptIds();
					//for (Integer deptId : inChargeDeptsIntegers) {
					//	inChargeDepts += deptId.intValue() + ","; // 负责的机构
					//}
					//if (inChargeDepts.length() > 0) {
					//	inChargeDepts = inChargeDepts.substring(0,
					//			(inChargeDepts.length() - 1));
					//}
				}
				// /////////////////所有任务分配人员
				// Map<String, Object> map= new HashMap<String, Object>();
				// List<TskMicroTaskExecute> executeList=
				// tskMicroTaskExecuteService.getAllTskMicroTaskExecuteByConds(map);
				// StringBuilder sb=new StringBuilder();
				// for (TskMicroTaskExecute bean : executeList) {
				// if(bean.getUserId()!=0)
				// sb.append(bean.getUserId()).append(",");
				// }
				// //系统中任务已分配人员
				// String[] executeStr=sb.toString().split(",");
				// 下属人员
				String inChargeUser = deptFacadeService
						.getUserIdsBelongToManager(getLoginInfo().getRoles(),
								"taskInfo");
				String[] arr = inChargeUser.split(",");
				// String[] array=getAllSameElement(arr,executeStr);

				for (String userId : arr) {
					inChargeUserIdsMark += "[" + userId + "],";
					inChargeUserIds += userId + ",";
				}
				if (inChargeUserIds.length() > 0) {
					inChargeUserIdsMark = inChargeUserIdsMark.substring(0,
							(inChargeUserIdsMark.length() - 1));
					inChargeUserIds = inChargeUserIds.substring(0,
							inChargeUserIds.length() - 1);
				}

				conds.put("userId", this.getLoginInfo().getUserId());
				conds.put("inChargeUserIds", inChargeUserIds);
				conds.put("inChargeUserIdsMark", inChargeUserIdsMark);
				conds.put("inChargeDepts", inChargeDepts);
				conds.put("taskStatus", -1);// 查询未完成和已完成
				userType = 2;
			} else {
				// 客户经理 显示我执行的任务
				isInChargeOf = 0;
				userType = 3;
				inChargeUserIds = String.valueOf(loginUserId);
				inChargeDepts = "0";

				String userIdMark = "[" + loginUserId + "]";
				conds.put("userId", this.getLoginInfo().getUserId());
				conds.put("userIdMark", userIdMark);
				conds.put("taskStatus", -1);// 查询未完成和已完成
				conds.put("inChargeUserIds", inChargeUserIds);
				conds.put("inChargeUserIdsMark", userIdMark);
				conds.put("inChargeDepts", inChargeDepts);
				conds.put("isInChargeOf", isInChargeOf);
			}
			*/
			conds.put("userType", userType);
			PageUtil<TskMicroTask> dataList = tskMicroTaskService
					.getTaskListPage(conds, this.getPage());

			request.setAttribute("dataList", dataList.getItems());
			request.setAttribute("recordCount", dataList.getPage()
					.getTotalRowsAmount());

			return SUCCESS;
		} catch (Exception e) {
			log.error("allTaskList", e);
			return ERROR;
		}
	}

	public static String[] getAllSameElement(String[] strArr1, String[] strArr2) {
		if (strArr1 == null || strArr2 == null) {
			return null;
		}
		List<String> strList1 = new ArrayList<String>(Arrays.asList(strArr1));
		List<String> strList2 = new ArrayList<String>(Arrays.asList(strArr2));
		strList1.retainAll(strList2);
		String str = "";
		for (String s : strList1) {
			str += s + ",";
		}
		if (str.length() > 0)
			return str.split(",");
		else
			return null;
	}

	/**
	 * 所有任务 查询
	 * 
	 * @return
	 */
	public String allTaskListQuery() {
		try {
			loginUserId = this.getLoginInfo().getUserId();
			loginDeptId = this.getLoginInfo().getDeptId();
			String inChargeUserIds = "", inChargeUserIdsMark = "", inChargeDepts = "";
			Map<String, Object> conds = new HashMap<String, Object>();
			int userType = 0;

			// 页面查询条件
			conds.put("taskTitle", taskTitle);
			conds.put("taskType", taskType);
			conds.put("startDate", startDate);
			conds.put("endDate", endDate);
			conds.put("taskStatus", taskStatus);// TODO
			conds.put("isOutDate", isOutDate);
			if (StringUtils.isNotEmpty(assignUserType)
					&& "my".equals(assignUserType)) {
				conds.put("assignUserId", this.getLoginInfo().getUserId());
			}else if(StringUtils.isNotEmpty(assignUserType)
                    && "brUser".equals(assignUserType)){
			    conds.put("assignUserId", assignUserIds);
			}

			// 客户经理
			isInChargeOf = 0;
			String userIdMark = "[" + loginUserId + "]";
			inChargeUserIds = String.valueOf(loginUserId);
			inChargeDepts = "0";

			conds.put("userId", this.getLoginInfo().getUserId());
			conds.put("userIdMark", userIdMark);
			conds.put("inChargeUserIds", inChargeUserIds);
			conds.put("inChargeUserIdsMark", userIdMark);
			conds.put("inChargeDepts", inChargeDepts);
			conds.put("isInChargeOf", isInChargeOf);
			userType = 3;
			
			/*
			//判断是否是业务主管
			if (deptFacadeService.isInChargeOfDepartment()) {
			    returnLoginDeptIds();
				isInChargeOf = 1;
				String roleIds = StringUtil.getIdsString(getLoginInfo()
						.getRoles());
				boolean flag = specialDataAuthService.getSysDataAuthCondition(
						roleIds, "taskInfo");
				if (flag) {
					inChargeDepts = "-1";
				} else {
					Integer[] inChargeDeptsIntegers = deptFacadeService
							.getInChargeOfDeptIds();
					for (Integer deptId : inChargeDeptsIntegers) {
						inChargeDepts += deptId.intValue() + ","; // 负责的机构
					}
					if (inChargeDepts.length() > 0) {
						inChargeDepts = inChargeDepts.substring(0,
								(inChargeDepts.length() - 1));
					}
				}
				String inChargeUser = deptFacadeService
						.getUserIdsBelongToManager(getLoginInfo().getRoles(),
								"taskInfo");
				String[] array = inChargeUser.split(",");
				inChargeUserIds = inChargeUser;
				for (String userId : array) {
					inChargeUserIdsMark += "[" + userId + "],";
				}
				if (inChargeUserIds.length() > 0) {
					inChargeUserIdsMark = inChargeUserIdsMark.substring(0,
							(inChargeUserIdsMark.length() - 1));
				}
				conds.put("userId", this.getLoginInfo().getUserId());
				conds.put("inChargeUserIds", inChargeUserIds);
				conds.put("inChargeUserIdsMark", inChargeUserIdsMark);
				conds.put("inChargeDepts", inChargeDepts);
				userType = 2;
			} else {
				// 客户经理
				isInChargeOf = 0;
				String userIdMark = "[" + loginUserId + "]";
				inChargeUserIds = String.valueOf(loginUserId);
				inChargeDepts = "0";

				conds.put("userId", this.getLoginInfo().getUserId());
				conds.put("userIdMark", userIdMark);
				conds.put("inChargeUserIds", inChargeUserIds);
				conds.put("inChargeUserIdsMark", userIdMark);
				conds.put("inChargeDepts", inChargeDepts);
				conds.put("isInChargeOf", isInChargeOf);
				userType = 3;
			}
			*/
			conds.put("userType", userType);
			PageUtil<TskMicroTask> dataList = tskMicroTaskService
					.getTaskListPage(conds, this.getPage());

			request.setAttribute("dataList", dataList.getItems());
			request.setAttribute("recordCount", dataList.getItems().size());
			request.setAttribute("recordCount", 0);
			return SUCCESS;
		} catch (Exception e) {
			log.error("allTaskListQuery", e);
			return ERROR;
		}
	}

	/**
	 * 我执行的
	 * 
	 * @return
	 */
	public String myTaskList() {
		try {
		    returnLoginDeptIds();
			loginUserId = this.getLoginInfo().getUserId();
			loginDeptId = this.getLoginInfo().getDeptId();
			String userIdMark = "[" + loginUserId + "]";

			Map<String, Object> conds = new HashMap<String, Object>();
			conds.put("userId", this.getLoginInfo().getUserId());
			conds.put("userIdMark", userIdMark);
			conds.put("taskStatus", -1);// 查询未完成和已完成

			PageUtil<TskMicroTask> dataList = tskMicroTaskService
					.getMyTaskListPage(conds, this.getPage());

			int isInChargeOf = deptFacadeService.isInChargeOfDepartment() ? 1
					: 0;
			request.setAttribute("isInChargeOf", isInChargeOf);
			request.setAttribute("dataList", dataList.getItems());
			request.setAttribute("recordCount", dataList.getPage()
					.getTotalRowsAmount());
			return SUCCESS;
		} catch (Exception e) {
			log.error("allTaskListQuery", e);
			return ERROR;
		}
	}

	// 执行任务查询
	public String myTaskListQuery() {
		try {
		    returnLoginDeptIds();
			loginUserId = this.getLoginInfo().getUserId();
			loginDeptId = this.getLoginInfo().getDeptId();
			String userIdMark = "[" + loginUserId + "]";

			Map<String, Object> conds = new HashMap<String, Object>();
			conds.put("userId", this.getLoginInfo().getUserId());
			conds.put("userIdMark", userIdMark);

			// 页面查询条件
			conds.put("taskTitle", taskTitle);
			conds.put("taskType", taskType);
			conds.put("startDate", startDate);
			conds.put("endDate", endDate);
			conds.put("taskStatus", taskStatus);// TODO
			conds.put("isOutDate", isOutDate);
			if (StringUtils.isNotEmpty(assignUserType)
					&& "my".equals(assignUserType)) {
				conds.put("assignUserId", this.getLoginInfo().getUserId());
			}else if(StringUtils.isNotEmpty(assignUserType)
                    && "brUser".equals(assignUserType)){
                conds.put("assignUserId", assignUserIds);
            }

			PageUtil<TskMicroTask> dataList = tskMicroTaskService
					.getMyTaskListPage(conds, this.getPage());
			int isInChargeOf = deptFacadeService.isInChargeOfDepartment() ? 1
					: 0;
			request.setAttribute("isInChargeOf", isInChargeOf);
			request.setAttribute("dataList", dataList.getItems());
			request.setAttribute("recordCount", dataList.getPage()
					.getTotalRowsAmount());
			return SUCCESS;
		} catch (Exception e) {
			log.error("allTaskListQuery", e);
			return ERROR;
		}
	}

	// 删除任务
	public void delTask() {
		String taskId = request.getParameter("taskId");
		try {
			if (!StringUtil.isEmpty(taskId)) {
				tskMicroTaskService.delTskMicroTask(Integer.valueOf(taskId));
			}
		} catch (Exception e) {
			log.error("delTask", e);
		}
	}

	// 中止任务
	public void stopTask() {
		String taskId = request.getParameter("taskId");
		try {
			if (!StringUtil.isEmpty(taskId)) {
				tskMicroTaskService.stopTskMicroTask(Integer.valueOf(taskId));
			}
		} catch (Exception e) {
			log.error("delTask", e);
		}
	}

	// 重启任务
	public void restartTask() {
		String taskId = request.getParameter("taskId");
		try {
			if (!StringUtil.isEmpty(taskId)) {
				tskMicroTaskService
						.restartTskMicroTask(Integer.valueOf(taskId));
			}
		} catch (Exception e) {
			log.error("delTask", e);
		}
	}

	/* getter setter */
	public DeptFacadeService getDeptFacadeService() {
		return deptFacadeService;
	}

	public void setDeptFacadeService(DeptFacadeService deptFacadeService) {
		this.deptFacadeService = deptFacadeService;
	}

	public TskMicroTaskService getTskMicroTaskService() {
		return tskMicroTaskService;
	}

	public void setTskMicroTaskService(TskMicroTaskService tskMicroTaskService) {
		this.tskMicroTaskService = tskMicroTaskService;
	}

	public String getTaskTitle() {
		return taskTitle;
	}

	public void setTaskTitle(String taskTitle) {
		this.taskTitle = taskTitle;
	}

	public Integer getTaskType() {
		return taskType;
	}

	public void setTaskType(Integer taskType) {
		this.taskType = taskType;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Integer getTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(Integer taskStatus) {
		this.taskStatus = taskStatus;
	}

	public Integer getIsOutDate() {
		return isOutDate;
	}

	public void setIsOutDate(Integer isOutDate) {
		this.isOutDate = isOutDate;
	}

	public String getAssignUserType() {
		return assignUserType;
	}

	public void setAssignUserType(String assignUserType) {
		this.assignUserType = assignUserType;
	}

	public Integer getLoginUserId() {
		return loginUserId;
	}

	public void setLoginUserId(Integer loginUserId) {
		this.loginUserId = loginUserId;
	}

	public Integer getIsInChargeOf() {
		return isInChargeOf;
	}

	public void setIsInChargeOf(Integer isInChargeOf) {
		this.isInChargeOf = isInChargeOf;
	}

    public String getAssignUserIds() {
        return assignUserIds;
    }

    public void setAssignUserIds(String assignUserIds) {
        this.assignUserIds = assignUserIds;
    }

    public String getLoginDeptIds() {
        return loginDeptIds;
    }

    public void setLoginDeptIds(String loginDeptIds) {
        this.loginDeptIds = loginDeptIds;
    }

    public Integer getLoginDeptId() {
        return loginDeptId;
    }

    public void setLoginDeptId(Integer loginDeptId) {
        this.loginDeptId = loginDeptId;
    }
}
