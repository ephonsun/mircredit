/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :QianJie
 * Create Date:Mar 2, 2013
 */
package com.banger.mobile.webapp.action.microTask;

import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.model.dept.CusBelongToBean;
import com.banger.mobile.domain.model.dept.SysDept;
import com.banger.mobile.domain.model.microTask.TaskProgress;
import com.banger.mobile.domain.model.microTask.TskMicroTask;
import com.banger.mobile.domain.model.microTask.TskMicroTaskExecute;
import com.banger.mobile.domain.model.microTask.TskMicroTaskExecuteAssign;
import com.banger.mobile.domain.model.microTask.TskMicroTaskTarget;
import com.banger.mobile.domain.model.user.SysDeptAuth;
import com.banger.mobile.domain.model.user.SysUser;
import com.banger.mobile.domain.model.user.SysUserBean;
import com.banger.mobile.facade.dept.DeptFacadeService;
import com.banger.mobile.facade.microTask.TskMicroTaskExecuteService;
import com.banger.mobile.facade.microTask.TskMicroTaskProgressService;
import com.banger.mobile.facade.microTask.TskMicroTaskService;
import com.banger.mobile.facade.microTask.TskMicroTaskTargetService;
import com.banger.mobile.facade.microTask.TskMicroTaskWorkbenchService;
import com.banger.mobile.facade.user.SysDeptAuthService;
import com.banger.mobile.facade.user.SysUserService;
import com.banger.mobile.util.DateUtil;
import com.banger.mobile.util.StringUtil;
import com.banger.mobile.util.VmHelper;
import com.banger.mobile.webapp.action.BaseAction;

/**
 * @author QianJie
 * @version $Id: TskMicroTaskAction.java,v 0.1 Mar 2, 2013 12:01:56 PM QianJie
 *          Exp $
 */
public class TskMicroTaskAction extends BaseAction {

	private static final long serialVersionUID = -3029151366017359601L;

	/**
	 * Service
	 */
	private TskMicroTaskService tskMicroTaskService;
	private TskMicroTaskExecuteService tskMicroTaskExecuteService;
	private DeptFacadeService deptFacadeService;
	private TskMicroTaskTargetService tskMicroTaskTargetService;
	private TskMicroTaskProgressService tskMicroTaskProgressService;
	private TskMicroTaskWorkbenchService tskMicroTaskWorkbenchService;
	private SysDeptAuthService sysDeptAuthService;
	private SysUserService sysUserService;

	public void setSysUserService(SysUserService sysUserService)
	{
		this.sysUserService = sysUserService;
	}

	/**
	 * 参数
	 * 
	 * @return
	 */
	private List<TskMicroTaskExecuteAssign> tskAssignList = new ArrayList<TskMicroTaskExecuteAssign>();
	private TskMicroTask tskMicroTask;
	private PageUtil<TskMicroTaskTarget> taskTargetPage;
	private String userName;
	private Integer isDeptManger;// 是否是客户经理
	private PageUtil<TskMicroTask> tskMicroTaskPage;
	
	private String deptUserIds;

	public String getDeptUserIds() {
        return deptUserIds;
    }

    public void setDeptUserIds(String deptUserIds) {
        this.deptUserIds = deptUserIds;
    }

    public SysDeptAuthService getSysDeptAuthService()
	{
		return sysDeptAuthService;
	}

	public void setSysDeptAuthService(SysDeptAuthService sysDeptAuthService)
	{
		this.sysDeptAuthService = sysDeptAuthService;
	}

	public void setTskMicroTaskService(TskMicroTaskService tskMicroTaskService)
	{
		this.tskMicroTaskService = tskMicroTaskService;
	}

	public TskMicroTaskExecuteService getTskMicroTaskExecuteService()
	{
		return tskMicroTaskExecuteService;
	}

	public void setTskMicroTaskExecuteService(
			TskMicroTaskExecuteService tskMicroTaskExecuteService)
	{
		this.tskMicroTaskExecuteService = tskMicroTaskExecuteService;
	}

	public List<TskMicroTaskExecuteAssign> getTskAssignList()
	{
		return tskAssignList;
	}

	public void setTskAssignList(List<TskMicroTaskExecuteAssign> tskAssignList)
	{
		this.tskAssignList = tskAssignList;
	}

	public TskMicroTask getTskMicroTask()
	{
		return tskMicroTask;
	}

	public void setTskMicroTask(TskMicroTask tskMicroTask)
	{
		this.tskMicroTask = tskMicroTask;
	}

	public void setDeptFacadeService(DeptFacadeService deptFacadeService)
	{
		this.deptFacadeService = deptFacadeService;
	}

	public void setTskMicroTaskTargetService(
			TskMicroTaskTargetService tskMicroTaskTargetService)
	{
		this.tskMicroTaskTargetService = tskMicroTaskTargetService;
	}

	public PageUtil<TskMicroTaskTarget> getTaskTargetPage()
	{
		return taskTargetPage;
	}

	public void setTaskTargetPage(PageUtil<TskMicroTaskTarget> taskTargetPage)
	{
		this.taskTargetPage = taskTargetPage;
	}

	public void setTskMicroTaskProgressService(
			TskMicroTaskProgressService tskMicroTaskProgressService)
	{
		this.tskMicroTaskProgressService = tskMicroTaskProgressService;
	}

	public String getUserName()
	{
		return userName;
	}

	public void setUserName(String userName)
	{
		this.userName = userName;
	}

	public Integer getIsDeptManger()
	{
		return isDeptManger;
	}

	public void setIsDeptManger(Integer isDeptManger)
	{
		this.isDeptManger = isDeptManger;
	}

	public void setTskMicroTaskWorkbenchService(
			TskMicroTaskWorkbenchService tskMicroTaskWorkbenchService)
	{
		this.tskMicroTaskWorkbenchService = tskMicroTaskWorkbenchService;
	}

	public PageUtil<TskMicroTask> getTskMicroTaskPage()
	{
		return tskMicroTaskPage;
	}

	public void setTskMicroTaskPage(PageUtil<TskMicroTask> tskMicroTaskPage)
	{
		this.tskMicroTaskPage = tskMicroTaskPage;
	}
	
	/**
	 * 跳转到新建任务页面
	 * 
	 * @return
	 */
	public String toAddTskMicroTaskPage()
	{
		request.setAttribute("assignName", this.getLoginInfo().getUserName());
		return SUCCESS;
	}

	/**
	 * 跳转到新建贷款任务分配页面
	 * 
	 * @return
	 */
	public String toAddTskMicroTaskNextPage()
	{
		tskMicroTask = tskMicroTaskService.getTskMicroTaskById(tskMicroTask
				.getTaskId());
		request.setAttribute("assignName", this.getLoginInfo().getUserName());
		tskAssignList = tskMicroTaskExecuteService
				.getAllTskMicroTaskExecuteAssignByConds(tskMicroTask, this
						.getLoginInfo().getUserId());
		if (deptFacadeService.isInChargeOfDepartment())
		{
			isDeptManger = 1;
		} else
		{
			isDeptManger = 0;
		}
		String novate = this.request.getParameter("relax");
		if (novate != null && novate.equals("relax"))
		{
			return "relax";
		}
		return SUCCESS;
	}

	/**
	 * 跳转到编辑贷款任务页面创建者
	 * 
	 * @return
	 */
	public String toEditTskMicroTaskByAssignPage()
	{
		tskMicroTask = tskMicroTaskService.getTskMicroTaskById(tskMicroTask
				.getTaskId());
		SysUser user = sysUserService.getSysUserById(tskMicroTask
				.getAssignUserId());
		if (user.getIsDel() == 1)
			request.setAttribute("assignflag", "(已删除)");
		if (user.getIsActived() == 0)
			request.setAttribute("assignflag", "(已停用)");

		request.setAttribute("assignName", user.getUserName());
		tskAssignList = tskMicroTaskExecuteService
				.getAllTskMicroTaskExecuteAssignByConds(tskMicroTask, this
						.getLoginInfo().getUserId());
		if (deptFacadeService.isInChargeOfDepartment())
		{
			isDeptManger = 1;
		} else
		{
			isDeptManger = 0;
		}
		String novate = this.request.getParameter("relax");
		if (novate != null && novate.equals("relax"))
		{
			return novate;
		}
		return SUCCESS;
	}

	/**
	 * 跳转到编辑贷款任务页面执行者
	 * 
	 * @return
	 */
	public String toEditTskMicroTaskByExecutorPage()
	{
		tskMicroTask = tskMicroTaskService.getTskMicroTaskById(tskMicroTask
				.getTaskId());
		// 计算新老客户比例
		// isNewOrOldPercent(tskMicroTask);
		SysUser user = sysUserService.getSysUserById(tskMicroTask
				.getAssignUserId());
		if (user.getIsDel() == 1)
			request.setAttribute("assignflag", "(已删除)");
		if (user.getIsActived() == 0)
			request.setAttribute("assignflag", "(已停用)");

		request.setAttribute("assignName", user.getUserName());
		tskAssignList = tskMicroTaskExecuteService
				.getAllTskMicroTaskExecuteAssignByConds(tskMicroTask, this
						.getLoginInfo().getUserId());
		
		//getInChangeDeptUserIds();

		// 是否有导入任务分配的权限
		isDeptManger = 0;
		String isImport=request.getParameter("isImport");
		if(isImport.equals("1")){
		    isDeptManger = 1;
		}
		String novate = this.request.getParameter("relax");
		if (novate != null && novate.equals("relax"))
		{
			return novate;
		}
		return SUCCESS;
	}
	
	/**
	 * 返回登录用户所管辖的机构和用户ID集合字符串
	 * @return
	 */
	public String getInChangeDeptUserIds(){
	    //机构ids
	    String deptIds = "";
        Integer[] arrDept = deptFacadeService.getInChargeOfDeptIds();
        if (arrDept != null) {
            for (int i = 0; i < arrDept.length; i++) {
                if (deptIds.equals(""))
                    deptIds = arrDept[i].toString()+":D";
                else
                    deptIds = deptIds + "," + arrDept[i]+":D";
            }
        }
        //用户ids
        String userIds = "";
        Integer[] arrUser = deptFacadeService.getInChargeOfDeptUserIds();
        if (arrUser != null) {
            for (int i = 0; i < arrUser.length; i++) {
                if (userIds.equals(""))
                    userIds = arrUser[i].toString()+":U";
                else
                    userIds = userIds + "," + arrUser[i]+":U";
            }
        }
        if (userIds.equals("")) {
            userIds = this.getLoginInfo().getUserId().toString()+":U";
        }
        deptUserIds=deptIds+","+userIds;
	    return deptUserIds;
	}

	/**
	 * 跳转到查看贷款任务页面
	 * 
	 * @return
	 */
	public String toViewTskMicroTaskPage()
	{
		tskMicroTask = tskMicroTaskService.getTskMicroTaskById(tskMicroTask
				.getTaskId());
		// 计算新老客户比例
		// isNewOrOldPercent(tskMicroTask);
		List<SysUserBean> userAssign = deptFacadeService
				.getUsersByUserIds(tskMicroTask.getAssignUserId() + "");
		request.setAttribute("assignName", userAssign.get(0).getUserName());
		SysUser user = sysUserService.getSysUserById(tskMicroTask
				.getAssignUserId());
		if (user.getIsDel() == 1)
			request.setAttribute("assignflag", "(已删除)");
		if (user.getIsActived() == 0)
			request.setAttribute("assignflag", "(已停用)");

		request.setAttribute("assignName", user.getUserName());
		tskAssignList = tskMicroTaskExecuteService
				.getAllTskMicroTaskExecuteAssignByConds(tskMicroTask, this
						.getLoginInfo().getUserId());
		if (!deptFacadeService.isInChargeOfDepartment())
		{
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", getLoginInfo().getUserId());
			map.put("pid", getLoginInfo().getDeptId());
			map.put("name", getLoginInfo().getUserName());
			map.put("flag", "U");
			request.setAttribute("deptJson", JSONObject.fromObject(map)
					.toString());
		} else
		{
			String belongUserId = request.getParameter("belongUserId");
			String flag = "insert";
			JSONArray array = this.toUserOrDeptJson(flag, belongUserId);
			request.setAttribute("deptJson", array);
		}
		return SUCCESS;
	}

	/**
	 * 保存贷款任务（新建、编辑）
	 */
	public void saveTskMicroTask()
	{
		PrintWriter out = null;
		try
		{
			out = this.getResponse().getWriter();
			String msg = "SUCCESS";
			Map<String, Object> conds = new HashMap<String, Object>();
			conds.put("taskTitle", tskMicroTask.getTaskTitle());
			List<TskMicroTask> list = null;
			if (tskMicroTask.getTaskId() == -1)
			{// 新增
				list = tskMicroTaskService.getAllTskMicroTaskByConds(conds);
			} else
			{
				conds.put("unTaskId", tskMicroTask.getTaskId());
				list = tskMicroTaskService.getAllTskMicroTaskByConds(conds);
			}
			if (list.size() > 0)
			{
				msg = "已存在名称为\\\"" + tskMicroTask.getTaskTitle()
						+ "\\\"的任务，请重新填写任务名称！";
			} else
			{
				if (tskMicroTask.getNewCustomerPercent().intValue() == 0)
				{
					tskMicroTask.setNewCustomerPercent(null);
				}
				if (tskMicroTask.getOldCustomerPercent().intValue() == 0)
				{
					tskMicroTask.setOldCustomerPercent(null);
				}
				if (tskMicroTask.getTaskId() == -1)
				{// 新增
					tskMicroTask.setAssignUserId(this.getLoginInfo()
							.getUserId());
					tskMicroTask.setCreateUser(this.getLoginInfo().getUserId());
					// tskMicroTask.setFinishDate(finishDate) 完成日期
					tskMicroTask.setTaskStatus(0);
					tskMicroTask.setUpdateUser(this.getLoginInfo().getUserId());
					tskMicroTaskService.addTskMicroTask(tskMicroTask);// 保存新建
				} else
				{
					conds.clear();
					conds.put("userId", this.getLoginInfo().getUserId());
					conds.put("roleId", "3");
					List<SysDeptAuth> lis = sysDeptAuthService
							.getSysDeptAuthList(conds); // 登录用户所管理的部门id集合

					conds.clear();
					conds.put("taskId", tskMicroTask.getTaskId());
					List<TskMicroTaskExecute> assignList = tskMicroTaskExecuteService
							.getAllTskMicroTaskExecuteByConds(conds);
					conds.put("userId", this.getLoginInfo().getUserId());
					if (deptFacadeService.isInChargeOfDepartment())
					{
						conds.put("role", "brDept");
					} else
					{
						conds.put("role", "brMine");
					}
					String str = "";
					boolean b=false;
					for (SysDeptAuth bean : lis)
					{
						str = str + bean.getDeptId() + ",";
						if(bean.getDeptId().equals(this.getLoginInfo().getDeptId())){
						    b=true;
						}
					}
					if (str.length() > 0)
						str = str.substring(0, str.length() - 1);
					else
						str = "-1";
					
					/**
                     * add liyb 2013-11-29 处理任务目标str
                     */
                    if(!b){
                        conds.put("InChargeOfDeptIds", this.getLoginInfo().getDeptId());
                    }else{
                        conds.put("InChargeOfDeptIds", str);
                    }
					Integer assignTarget = tskMicroTaskExecuteService
							.getMyTargetByConds(conds);
					if(assignTarget==null){
					    assignTarget=0;
					}
					String inChargeUserIdsMark = "";
					String inChargeUser = deptFacadeService
							.getUserIdsBelongToManager(getLoginInfo()
									.getRoles(), "taskInfo");
					String[] arr = inChargeUser.split(",");

					for (String userId : arr)
					{
						inChargeUserIdsMark += "[" + userId + "],";
					}
					if (inChargeUserIdsMark.length() > 0)
					{
						inChargeUserIdsMark = inChargeUserIdsMark.substring(0,
								(inChargeUserIdsMark.length() - 1));
					}
					conds.put("inChargeDeptUserMark", inChargeUserIdsMark);
					Integer comTskNum = tskMicroTaskExecuteService
							.getComTskNumByConds(conds);
					if ((assignList.size() == 0)
							|| (assignTarget <= tskMicroTask.getTaskTarget()))
					{
						if (comTskNum == null
								|| comTskNum <= tskMicroTask.getTaskTarget())
						{
							TskMicroTask item = tskMicroTaskService
									.getTskMicroTaskById(tskMicroTask
											.getTaskId());
							item.setNewCustomerPercent(tskMicroTask
									.getNewCustomerPercent());
							item.setOldCustomerPercent(tskMicroTask
									.getOldCustomerPercent());
							item.setRemark(tskMicroTask.getRemark());
							item.setTaskTarget(tskMicroTask.getTaskTarget());
							item.setTaskTitle(tskMicroTask.getTaskTitle());
							item.setTaskType(tskMicroTask.getTaskType());
							item.setUpdateUser(this.getLoginInfo().getUserId());
							item.setStartDate(tskMicroTask.getStartDate());
							item.setEndDate(tskMicroTask.getEndDate());
							tskMicroTaskService.editTskMicroTask(item);// 保存编辑
						} else
						{
							if (tskMicroTask.getTaskType().intValue() >= 2)
							{
								msg = "任务目标不能够低于\\\"" + comTskNum.toString()
										+ "\\\"个";
							} else
							{
								msg = "任务目标不能够低于\\\"" + comTskNum.toString()
										+ "\\\"笔";
							}
						}
					} else
					{
						if (tskMicroTask.getTaskType().intValue() >= 2)
						{
							msg = "任务目标不能够低于\\\"" + assignTarget.toString()
									+ "\\\"个";
						} else
						{
							msg = "任务目标不能够低于\\\"" + assignTarget.toString()
									+ "\\\"笔";
						}
					}
				}
			}
			out.write("{\"result\":\"" + msg + "\",\"taskId\":\""
					+ tskMicroTask.getTaskId() + "\",\"taskTarget\":\""
					+ tskMicroTask.getTaskTarget() + "\"}");
		} catch (Exception ex)
		{
		    ex.printStackTrace();
			out.write("{\"result\":\"ERROR\",\"taskId\":\"0\"}");
			log.error(ex);
		}
	}

	/**
	 * 跳转到新建营销任务页面
	 * 
	 * @return
	 */
	public String toAddTskMicroTaskMarketingPage()
	{
		request.setAttribute("assignName", this.getLoginInfo().getUserName());
		return SUCCESS;
	}

	/**
	 * 跳转到新建营销任务分配页面
	 * 
	 * @return
	 */
	public String toAddTskMicroTaskMarketingNextPage()
	{
		tskMicroTask = tskMicroTaskService.getTskMicroTaskById(tskMicroTask
				.getTaskId());
		request.setAttribute("assignName", this.getLoginInfo().getUserName());
		tskAssignList = tskMicroTaskExecuteService
				.getAllTskMicroTaskExecuteAssignByConds(tskMicroTask, this
						.getLoginInfo().getUserId());
		// 是否有权限导入营销任务分配
		if (deptFacadeService.isInChargeOfDepartment())
		{
			isDeptManger = 1;
		} else
		{
			isDeptManger = 0;
		}
		// 刷新页面
		String novate = this.request.getParameter("relax");
		if (novate != null && novate.equals("relax"))
		{
			return novate;
		}
		return SUCCESS;
	}

	/**
	 * 跳转到编辑营销任务页面创建者
	 * 
	 * @return
	 */
	public String toEditTskMicroTaskMarketingByAssignPage()
	{
		tskMicroTask = tskMicroTaskService.getTskMicroTaskById(tskMicroTask
				.getTaskId());
		SysUser user = sysUserService.getSysUserById(tskMicroTask
				.getAssignUserId());
		if (user.getIsDel() == 1)
			request.setAttribute("assignflag", "(已删除)");
		if (user.getIsActived() == 0)
			request.setAttribute("assignflag", "(已停用)");

		request.setAttribute("assignName", user.getUserName());
		tskAssignList = tskMicroTaskExecuteService
				.getAllTskMicroTaskExecuteAssignByConds(tskMicroTask, this
						.getLoginInfo().getUserId());
		// 当前的登录用户是业务主管且是当前任务的创建者
		if (deptFacadeService.isInChargeOfDepartment()
				&& tskMicroTask.getCreateUser().equals(
						this.getLoginInfo().getUserId()))
		{
			isDeptManger = 1;
		} else
		{
			isDeptManger = 0;
		}
		// 刷新页面
		String novate = this.request.getParameter("relax");
		if (novate != null && novate.equals("relax"))
		{
			return novate;
		}
		return SUCCESS;
	}

	/**
	 * 跳转到编辑营销任务页面执行者
	 * 
	 * @return
	 */
	public String toEditTskMicroTaskMarketingByExecutorPage()
	{
		tskMicroTask = tskMicroTaskService.getTskMicroTaskById(tskMicroTask
				.getTaskId());
		// 计算新老客户比例
		// isNewOrOldPercent(tskMicroTask);
		SysUser user = sysUserService.getSysUserById(tskMicroTask
				.getAssignUserId());
		if (user.getIsDel() == 1)
			request.setAttribute("assignflag", "(已删除)");
		if (user.getIsActived() == 0)
			request.setAttribute("assignflag", "(已停用)");

		request.setAttribute("assignName", user.getUserName());
		tskAssignList = tskMicroTaskExecuteService
				.getAllTskMicroTaskExecuteAssignByConds(tskMicroTask, this
						.getLoginInfo().getUserId());
		//getInChangeDeptUserIds();
		// 是否有导入任务分配的权限
		isDeptManger = 0;
		String isImport=request.getParameter("isImport");
        if(isImport.equals("1")){
            isDeptManger = 1;
        }
		// 刷新页面
		String novate = this.request.getParameter("relax");
		if (novate != null && novate.equals("relax"))
		{
			return novate;
		}
		return SUCCESS;
	}

	/**
	 * 跳转到查看营销任务页面
	 * 
	 * @return
	 */
	public String toViewTskMicroTaskMarketingPage()
	{
		tskMicroTask = tskMicroTaskService.getTskMicroTaskById(tskMicroTask
				.getTaskId());
		// 计算新老客户比例
		// isNewOrOldPercent(tskMicroTask);
		SysUser user = sysUserService.getSysUserById(tskMicroTask
				.getAssignUserId());
		if (user.getIsDel() == 1)
			request.setAttribute("assignflag", "(已删除)");
		if (user.getIsActived() == 0)
			request.setAttribute("assignflag", "(已停用)");

		request.setAttribute("assignName", user.getUserName());

		tskAssignList = tskMicroTaskExecuteService
				.getAllTskMicroTaskExecuteAssignByConds(tskMicroTask, this
						.getLoginInfo().getUserId());

		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("taskId", tskMicroTask.getTaskId());

		if (!deptFacadeService.isInChargeOfDepartment())
		{
			parameterMap.put("userId", this.getLoginInfo().getUserId());
			taskTargetPage = tskMicroTaskTargetService.getTargetListByPage(
					parameterMap, this.getPage());
			replaceNumber(taskTargetPage.getItems());// 替换隐藏号码
			int count = this.getPage().getTotalRowsAmount();
			request.setAttribute("count", String.valueOf(count));
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", getLoginInfo().getUserId());
			map.put("pid", getLoginInfo().getDeptId());
			map.put("name", getLoginInfo().getUserName());
			map.put("flag", "U");
			request.setAttribute("deptJson", JSONObject.fromObject(map)
					.toString());
			isDeptManger = 0;
		} else
		{
			parameterMap.put("inChargeUserIds",
					deptFacadeService.getUserIdsBelongToManager());
			taskTargetPage = tskMicroTaskTargetService.getTargetListByPage(
					parameterMap, this.getPage());
			replaceNumber(taskTargetPage.getItems());// 替换隐藏号码
			int count = this.getPage().getTotalRowsAmount();
			request.setAttribute("count", String.valueOf(count));
			request.setAttribute("inChargeUserIds",
					deptFacadeService.getUserIdsBelongToManager());
			String belongUserId = request.getParameter("belongUserId");
			String flag = "insert";
			JSONArray array = this.toUserOrDeptJson(flag, belongUserId);
			request.setAttribute("deptJson", array);
			isDeptManger = 1;
		}
		userName = this.getLoginInfo().getUserName();
		return SUCCESS;
	}

	/**
	 * 跳转到查看营销任务页面
	 * 
	 * @return
	 */
	public String toViewPhoneTskMicroTaskMarketingPage()
	{
		// 查询单个任务数据
		tskMicroTask = tskMicroTaskService.getTskMicroTaskById(tskMicroTask
				.getTaskId());
		// 计算新老客户比例
		// isNewOrOldPercent(tskMicroTask);
		// 根据用户ID集合取出用户对应用户对象

		SysUser user = sysUserService.getSysUserById(tskMicroTask
				.getAssignUserId());
		if (user.getIsDel() == 1)
			request.setAttribute("assignflag", "(已删除)");
		if (user.getIsActived() == 0)
			request.setAttribute("assignflag", "(已停用)");

		request.setAttribute("assignName", user.getUserName());

		// 查询所有任务执行者完成情况列表
		tskAssignList = tskMicroTaskExecuteService.getAllTskMicroTaskExecuteAssignByConds(tskMicroTask, this.getLoginInfo().getUserId());
		
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("taskId", tskMicroTask.getTaskId());
		// 登录用户是否是业务主管
		if (!deptFacadeService.isInChargeOfDepartment())
		{
			parameterMap.put("userId", this.getLoginInfo().getUserId());
			taskTargetPage = tskMicroTaskTargetService.getTargetListByPage(
					parameterMap, this.getPage());
			replaceNumber(taskTargetPage.getItems());// 替换隐藏号码
			int count = this.getPage().getTotalRowsAmount();
			request.setAttribute("count", String.valueOf(count));
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", getLoginInfo().getUserId());
			map.put("pid", getLoginInfo().getDeptId());
			map.put("name", getLoginInfo().getUserName());
			map.put("flag", "U");
			request.setAttribute("deptJson", JSONObject.fromObject(map).toString());
			isDeptManger = 0;
		} else
		{
			parameterMap.put("inChargeUserIds",
					deptFacadeService.getUserIdsBelongToManager());
			taskTargetPage = tskMicroTaskTargetService.getTargetListByPage(
					parameterMap, this.getPage());
			replaceNumber(taskTargetPage.getItems());// 替换隐藏号码
			int count = this.getPage().getTotalRowsAmount();
			request.setAttribute("count", String.valueOf(count));
			String belongUserId = request.getParameter("belongUserId");
			String flag = "insert";
			JSONArray array = this.toUserOrDeptJson(flag, belongUserId);
			request.setAttribute("deptJson", array);
			request.setAttribute("inChargeUserIds",deptFacadeService.getUserIdsBelongToManager());
			isDeptManger = 1;
		}
		userName = this.getLoginInfo().getUserName();
		return SUCCESS;
	}

	private String repairDateTime(Object obj, String repairType)
	{
		String repairDate = "";
		if (obj != null)
		{
			if (StringUtil.isNotEmpty(obj.toString()))
			{
				if (repairType.equals("S"))
				{
					repairDate = obj.toString() + ":00";
				} else
				{
					repairDate = obj.toString() + ":00";
				}
			}
		}
		return repairDate;
	}

	/**
	 * 查询实地营销目标客户列表
	 * 
	 * @return
	 */
	public String queryTaskTargetPage()
	{
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		String belongToType = request.getParameter("BelongToType");
		String inChargeUserIds = request.getParameter("inChargeUserIds");
		if (StringUtil.isNullOrEmpty(inChargeUserIds))
		{
			inChargeUserIds = deptFacadeService.getUserIdsBelongToManager();
		}
		if (!StringUtil.isNullOrEmpty(belongToType))
		{
			if (belongToType.equals("brMine"))
			{
				inChargeUserIds = this.getLoginInfo().getUserId().toString();
			}
		}
		parameterMap.put("taskId", tskMicroTask.getTaskId());
		parameterMap
				.put("customerQuery", request.getParameter("customerQuery"));
		parameterMap.put("inChargeUserIds", inChargeUserIds);
		parameterMap
				.put("recordAddress", request.getParameter("recordAddress"));
		parameterMap.put("recordDateFrom",
				repairDateTime(request.getParameter("callStartDate"), "S"));
		parameterMap.put("recordDateTo",
				repairDateTime(request.getParameter("callEndDate"), "E"));
		taskTargetPage = tskMicroTaskTargetService.getTargetListByPage(
				parameterMap, this.getPage());
		replaceNumber(taskTargetPage.getItems());// 替换隐藏号码
		int count = this.getPage().getTotalRowsAmount();
		request.setAttribute("count", String.valueOf(count));
		return SUCCESS;
	}

	/**
	 * 查询电话营销目标客户列表
	 * 
	 * @return
	 */
	public String queryPhoneTaskTargetPage()
	{
		String belongToType = request.getParameter("BelongToType");
		String inChargeUserIds = deptFacadeService.getUserIdsBelongToManager();
		if (!StringUtil.isNullOrEmpty(belongToType))
		{
			if (belongToType.equals("brMine"))
			{
				inChargeUserIds = this.getLoginInfo().getUserId().toString();
			}
			if (belongToType.equals("brUser"))
			{
				inChargeUserIds = request.getParameter("inChargeUserIds");
			}
		}
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("taskId", tskMicroTask.getTaskId());
		parameterMap
				.put("customerQuery", request.getParameter("customerQuery"));
		parameterMap.put("inChargeUserIds", inChargeUserIds);
        parameterMap.put("belongToType", belongToType);
		parameterMap.put("isFinish", request.getParameter("isFinish"));
		parameterMap.put("callDateFrom",
				repairDateTime(request.getParameter("callStartDate"), "S"));
		parameterMap.put("callDateTo",
				repairDateTime(request.getParameter("callEndDate"), "E"));
		taskTargetPage = tskMicroTaskTargetService.getTargetListByPage(
				parameterMap, this.getPage());
		taskTargetPage.getPage().setTotalRowsAmount(10);
		replaceNumber(taskTargetPage.getItems());// 替换隐藏号码
		int count = this.getPage().getTotalRowsAmount();
		request.setAttribute("count", String.valueOf(count));
		return SUCCESS;
	}

	/**
	 * 用****替换手机和固话部分号码
	 */
	private List<TskMicroTaskTarget> replaceNumber(List<TskMicroTaskTarget> list)
	{
		for (TskMicroTaskTarget t : list)
		{
			String pNumber = VmHelper.getHidePhoneNumber(t.getPhoneNumber());
			t.setPhoneNumberHide(pNumber);
		}

		return list;
	}

	/**
	 * 得到替换后的号码
	 * 
	 * @param number
	 * @param type
	 * @return
	 * 
	 *         private String getReplaceNumber(String number) { if
	 *         (StringUtil.isNullOrEmpty(number)) { return ""; }
	 * 
	 *         if (number.length() == 11) { number = number.substring(0, 3) +
	 *         "****" + number.substring(7, 11); } else if (number.length() > 4)
	 *         { number = number.substring(0, (number.length() - 4)) + "****"; }
	 * 
	 *         return number; }
	 */

	/**
	 * 进度图
	 * 
	 * @param //taskId
	 * @param //userId
	 * @return
	 */
	public void taskProgressQuery()
	{
		String userId = request.getParameter("userId");
		String deptId = request.getParameter("deptId");
		String taskId = request.getParameter("taskId");
		String loanStatus = request.getParameter("loanStatus");
		String rdo = request.getParameter("rdo");
		Integer loanStatusId = 0;
		PrintWriter out = null;
		if (loanStatus != null)
		{
			loanStatusId = Integer.valueOf(loanStatus);
		}

		try
		{
			out = this.getResponse().getWriter();
			TaskProgress taskProgress = null;
			// 营销任务
			if (loanStatus == null)
			{
				if (userId == null)
				{
					taskProgress = tskMicroTaskProgressService
							.getCompleteMarketTaskByDept(
									Integer.valueOf(taskId),
									Integer.valueOf(deptId));
				} else
				{
					taskProgress = tskMicroTaskProgressService
							.getCompleteMarketTaskByUser(
									Integer.valueOf(taskId),
									Integer.valueOf(userId));
				}
			}// 贷款任务
			else
			{
				if (userId == null)
				{
					taskProgress = tskMicroTaskProgressService
							.getLoanTaskCountByDept(Integer.valueOf(taskId),
									Integer.valueOf(loanStatus),
									Integer.valueOf(deptId));
				} else
				{
					taskProgress = tskMicroTaskProgressService
							.getLoanTaskCountByUser(Integer.valueOf(taskId),
									Integer.valueOf(loanStatus),
									Integer.valueOf(userId));
				}
			}
			Integer notFinishCount = 0;
			JSONObject json = new JSONObject();
			if (taskProgress.getTaskTarget() - taskProgress.getFinishCount() >= 0)
			{
				notFinishCount = taskProgress.getTaskTarget()
						- taskProgress.getFinishCount();
			}

			if (loanStatusId == 1)
			{
				if (rdo.equals("columnar"))
				{
					out.write("[{name:'分配新客户',y:"
							+ taskProgress.getNewCustomerCount()
							+ ",sliced: true,selected: true}," + "['分配老客户',"
							+ taskProgress.getOldCustomerCount() + "]]");
				} else if (rdo.equals("pie"))
				{
					out.write("[['分配新客户'," + taskProgress.getNewCustomerCount()
							+ "]," + "{name:'分配老客户',y:"
							+ taskProgress.getOldCustomerCount()
							+ ",sliced: true,selected: true}]");
				} else
				{
					json.put("finishCount", taskProgress.getFinishCount());
					json.put("pieMsg", "[['其他'," + notFinishCount + "],"
							+ "{name:'分配数',y:" + taskProgress.getFinishCount()
							+ ",sliced: true,selected: true}]");
					out.write(json.toString());
				}
			}
			if (loanStatusId == 2)
			{
				if (rdo.equals("columnar"))
				{
					out.write("[{name:'调查新客户',y:"
							+ taskProgress.getNewCustomerCount()
							+ ",sliced: true,selected: true}," + "['调查老客户',"
							+ taskProgress.getOldCustomerCount() + "]]");
				} else if (rdo.equals("pie"))
				{
					out.write("[['调查新客户'," + taskProgress.getNewCustomerCount()
							+ "]," + "{name:'调查老客户',y:"
							+ taskProgress.getOldCustomerCount()
							+ ",sliced: true,selected: true}]");
				} else
				{
					json.put("finishCount", taskProgress.getFinishCount());
					json.put("pieMsg", "[['其他'," + notFinishCount + "],"
							+ "{name:'调查数',y:" + taskProgress.getFinishCount()
							+ ",sliced: true,selected: true}]");
					out.write(json.toString());
				}
			}
			if (loanStatusId == 3)
			{
				if (rdo.equals("columnar"))
				{
					out.write("[{name:'审批新客户',y:"
							+ taskProgress.getNewCustomerCount()
							+ ",sliced: true,selected: true}," + "['审批老客户',"
							+ taskProgress.getOldCustomerCount() + "]]");
				} else if (rdo.equals("pie"))
				{
					out.write("[['审批新客户'," + taskProgress.getNewCustomerCount()
							+ "]," + "{name:'审批老客户',y:"
							+ taskProgress.getOldCustomerCount()
							+ ",sliced: true,selected: true}]");
				} else
				{
					json.put("finishCount", taskProgress.getFinishCount());
					json.put("pieMsg", "[['其他'," + notFinishCount + "],"
							+ "{name:'审批数',y:" + taskProgress.getFinishCount()
							+ ",sliced: true,selected: true}]");
					out.write(json.toString());
				}
			}
			if (loanStatusId == 4)
			{
				if (rdo.equals("columnar"))
				{
					out.write("[{name:'放贷新客户',y:"
							+ taskProgress.getNewCustomerCount()
							+ ",sliced: true,selected: true}," + "['放贷老客户',"
							+ taskProgress.getOldCustomerCount() + "]]");
				} else if (rdo.equals("pie"))
				{
					out.write("[['待放贷新客户',"
							+ taskProgress.getNewCustomerCount() + "],"
							+ "{name:'放贷老客户',y:"
							+ taskProgress.getOldCustomerCount()
							+ ",sliced: true,selected: true}]");
				} else
				{
					json.put("finishCount", taskProgress.getFinishCount());
					json.put("pieMsg", "[['其他'," + notFinishCount + "],"
							+ "{name:'放贷数',y:" + taskProgress.getFinishCount()
							+ ",sliced: true,selected: true}]");
					out.write(json.toString());
				}
			}
			if (loanStatusId == 5)
			{
				if (rdo.equals("columnar"))
				{
					out.write("[{name:'已放贷新客户',y:"
							+ taskProgress.getNewCustomerCount()
							+ ",sliced: true,selected: true}," + "['已放贷老客户',"
							+ taskProgress.getOldCustomerCount() + "]]");
				} else if (rdo.equals("pie"))
				{
					out.write("[['已放贷新客户',"
							+ taskProgress.getNewCustomerCount() + "],"
							+ "{name:'已放贷老客户',y:"
							+ taskProgress.getOldCustomerCount()
							+ ",sliced: true,selected: true}]");
				} else
				{
					json.put("finishCount", taskProgress.getFinishCount());
					json.put("pieMsg", "[['未放贷数'," + notFinishCount + "],"
							+ "{name:'已放贷数',y:" + taskProgress.getFinishCount()
							+ ",sliced: true,selected: true}]");
					out.write(json.toString());
				}
			}
			if (loanStatusId == 0)
			{
				if (rdo.equals("columnar"))
				{
					out.write("[{name:'已完成新客户',y:"
							+ taskProgress.getNewCustomerCount()
							+ ",sliced: true,selected: true}," + "['已完成老客户',"
							+ taskProgress.getOldCustomerCount() + "]]");
				} else if (rdo.equals("pie"))
				{
					out.write("[['已完成新客户',"
							+ taskProgress.getNewCustomerCount() + "],"
							+ "{name:'已完成老客户',y:"
							+ taskProgress.getOldCustomerCount()
							+ ",sliced: true,selected: true}]");
				} else
				{
					json.put("finishCount", taskProgress.getFinishCount());
					json.put("pieMsg", "[['未完成数'," + notFinishCount + "],"
							+ "{name:'已完成数',y:" + taskProgress.getFinishCount()
							+ ",sliced: true,selected: true}]");
					out.write(json.toString());
				}
			}
		} catch (Exception ex)
		{
			log.error(ex);
		}
	}

	/**
	 * 下属人员树
	 * 
	 * @param flag
	 * @param belongUserId
	 * @return 人员树json
	 */
	private JSONArray toUserOrDeptJson(String flag, String belongUserId)
	{
		Map<String, Object> conds = new HashMap<String, Object>();
		conds.put("taskId", tskMicroTask.getTaskId());
		List<TskMicroTaskExecute> list = tskMicroTaskExecuteService
				.getAllTskMicroTaskExecuteByCondsProgress(conds);
		List lst = new ArrayList();
		for (TskMicroTaskExecute l : list)
		{
			if (l.getUserId() == 0)
			{
				lst.add(l.getDeptId());
			} else
			{
				lst.add(l.getUserId());
			}
		}
		JSONArray array = new JSONArray();
		List<CusBelongToBean> deptlist = null;
		if (flag.equals("shareUser"))
		{
			deptlist = deptFacadeService.getBelongToUserAll();
		} else
		{
			deptlist = deptFacadeService
					.getBelongToUserAndDeptTreeList(getCurrentUserInChargeOfDeptIds());
		}
		Set<Integer> deptSet = new HashSet<Integer>();
		int ii = 0;
		for (CusBelongToBean dept : deptlist)
		{
			deptSet.add(dept.getId());
		}
		for (CusBelongToBean dept : deptlist)
		{
			if (!deptSet.contains(dept.getParentId()))
			{
				ii++;
			}
		}

		for (CusBelongToBean dept : deptlist)
		{
			JSONObject obj = new JSONObject();
			if (lst.contains(dept.getId()))
			{
				obj.put("id", dept.getId());
				if (!deptSet.contains(dept.getParentId()))
				{
					obj.put("pId", 0);
					if (ii == 1)
					{
						obj.put("open", true);
					}
				} else
				{
					obj.put("pId", dept.getParentId());
				}

				obj.put("name", dept.getTextName());
				obj.put("flag", dept.getType());
				obj.put("deptName", dept.getDeptName());
				if (dept.getType().equals("D"))
				{
					obj.put("icon", "../images/deptTreeImage/1.png");
				} else
				{
					obj.put("icon", "../images/deptTreeImage/2.png");
				}
				if ((flag.equals("modify"))
						&& (dept.getId().toString().equals(belongUserId))
						&& (dept.getType().equals("U")))
				{
					obj.put("selected", true);
				}

				array.add(obj);
			}
		}
		return array;
	}

	/**
	 * 下属机构树
	 * 
	 * @param depts
	 * @param flag
	 * @param belongDeptId
	 * @return 机构json
	 */
	public JSONArray toTreeJson(List<SysDept> depts, String flag,
			String belongDeptId)
	{
		try
		{
			Set<Integer> deptSet = new HashSet<Integer>();
			int ii = 0;
			for (SysDept sysDept : depts)
			{
				deptSet.add(sysDept.getDeptId());
			}
			for (SysDept sysDept : depts)
			{
				if (!deptSet.contains(sysDept.getDeptParentId()))
				{
					ii++;
				}
			}

			JSONArray jsonArray = new JSONArray();
			if (depts.size() > 0)
			{
				for (SysDept dept : depts)
				{
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("id", dept.getDeptId());
					if (!deptSet.contains(dept.getDeptParentId()))
					{
						map.put("pId", 0);
						if (ii == 1)
						{
							map.put("open", true);
						}
					} else
					{
						map.put("pId", dept.getDeptParentId());
					}
					map.put("name", dept.getDeptName());
					if (flag.equals("modify")
							&& dept.getDeptId().toString().equals(belongDeptId))
					{
						map.put("selected", true);
					}
					jsonArray.add(map);
				}
			}
			return jsonArray;
		} catch (Exception e)
		{
			// add by zhangxiang 2012-08-09
			log.error("CustomerBelongToAction toTreeJson action error:"
					+ e.getMessage());
			return null;
		}

	}

	/**
	 * 当前用户有权限的 机构ids
	 * 
	 * @return
	 */
	private String getCurrentUserInChargeOfDeptIds()
	{
		String deptIds = "";
		Integer[] arr = deptFacadeService.getInChargeOfDeptIds();
		Integer deptId=this.getLoginInfo().getDeptId();
		if (arr != null)
		{
			for (int i = 0; i < arr.length; i++)
			{
			    if(!arr[i].equals(deptId)){
			        if (deptIds.equals(""))
	                    deptIds = arr[i].toString();
	                else
	                    deptIds = deptIds + "," + arr[i];
			    }
			}
			if (deptIds.equals("")){
			    deptIds=deptId.toString();
			}else{
			    deptIds=deptIds+","+deptId;
			}
		}
		return deptIds;
	}

	/**
	 * 工作台 任务提醒页卡
	 */
	public String getTaskToWorkPlace()
	{
		try
		{
			int count = 0;
			Page page1 = new Page();
			Page page2 = new Page();
			tskMicroTaskPage = tskMicroTaskWorkbenchService
					.getOutDateTaskListPage(this.getLoginInfo().getUserId(),
							page1);
			count += page1.getTotalRowsAmount();
			tskMicroTaskPage = tskMicroTaskWorkbenchService
					.getTodayTaskListPage(this.getLoginInfo().getUserId(),
							page2);
			count += page2.getTotalRowsAmount();
			request.setAttribute("sumCount", count);
			request.setAttribute("count", page2.getTotalRowsAmount());
			return SUCCESS;
		} catch (Exception e)
		{
			log.error("TskMicroTaskAction: getTaskToWorkPlace error:", e);
			return ERROR;
		}
	}

	/**
	 * 工作台 查看今天或者过期的任务
	 */
	public String queryTodayOrOutDateTask()
	{
		try
		{
			String type = request.getParameter("type");
			if (type.equals("1"))
			{
				tskMicroTaskPage = tskMicroTaskWorkbenchService
						.getOutDateTaskListPage(
								this.getLoginInfo().getUserId(), this.getPage());
			} else
			{
				tskMicroTaskPage = tskMicroTaskWorkbenchService
						.getTodayTaskListPage(this.getLoginInfo().getUserId(),
								this.getPage());
			}
			String todayS = DateUtil.getDateToString(Calendar.getInstance()
					.getTime());
			request.setAttribute(
					"nowDate",
					DateUtil.convertStringToDate("yyyy-MM-dd HH:mm:ss", todayS
							+ " 00:00:00"));
			request.setAttribute("count", this.getPage().getTotalRowsAmount());
			return SUCCESS;
		} catch (Exception e)
		{
			log.error("TskMicroTaskAction: queryTodayOrOutDateTask error:", e);
			return ERROR;
		}
	}

	/**
	 * 计算新老客户比例
	 * 
	 * @param tskMicroTask
	 */
	private void isNewOrOldPercent(TskMicroTask tskMicroTask)
	{
		if (tskMicroTask != null)
		{
			if (tskMicroTask.getNewCustomerPercent() == null)
			{
				tskMicroTask.setNewCustomerPercent(new BigDecimal(0));
			}
			if (tskMicroTask.getOldCustomerPercent() == null)
			{
				tskMicroTask.setOldCustomerPercent(new BigDecimal(0));
			}
		}
	}
}