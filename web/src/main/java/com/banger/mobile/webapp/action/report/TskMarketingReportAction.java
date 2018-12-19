package com.banger.mobile.webapp.action.report;

import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.Enum.task.EnumTask;
import com.banger.mobile.domain.collection.DataTable;
import com.banger.mobile.domain.model.system.TaskGrade;
import com.banger.mobile.domain.model.tskContact.TskContactReportBean;
import com.banger.mobile.domain.model.tskMarketing.TskMarketingBean;
import com.banger.mobile.domain.model.tskMarketing.TskMarketingReportBean;
import com.banger.mobile.facade.dept.DeptFacadeService;
import com.banger.mobile.facade.system.TaskGradeService;
import com.banger.mobile.facade.tskMarketing.TskMarketingService;
import com.banger.mobile.util.ExcelUtil;
import com.banger.mobile.util.StringUtil;
import com.banger.mobile.util.TimeWrapper;
import com.banger.mobile.webapp.action.BaseAction;

public class TskMarketingReportAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8944198480180778743L;
	private Integer roleType; // 登录用户角色类型
	private DataTable table; // 报表数据
	private String userName; // 制表人
	private Integer userId; // 用户Id
	private Date reportTime; // 制表时间
	private String startDate; // 开始日期
	private String endDate; // 结束日期
	private String searchType;
	private String userIds; // 下属的用户ID集合
	private String deptIds;// 下属的机构ID集合
	private String taskLevelId;// 任务等级ID

	private TskMarketingService tskMarketingService;
	private TaskGradeService taskGradeService;
	private DeptFacadeService deptFacadeService; // 机构接口Service

	private List<TskMarketingReportBean> taskReportList;// 完成情况报表
	private List<TaskGrade> gradeList;// 任务等级列表
	private PageUtil<TskMarketingBean> tskMarketingBeanPage;

	/**
	 * 初始化登陆人角色
	 */
	public Integer initLoginRole() {
		this.request.setAttribute("userLoginName", this.getLoginInfo()
				.getUserName());
		this.request
				.setAttribute("userLoginDeptId", getLoginInfo().getDeptId());
		this.request
				.setAttribute("userLoginUserId", getLoginInfo().getUserId());
		String[] roleName = getLoginInfo().getRoleNames();
		if (compareStr(EnumTask.BUSINESS_COMPETENT.getValue(), roleName) != 0) {// 业务主管、系统管理员、机构系统管理员
			roleType = 0;
		} else {
			roleType = getLoginInfo().getUserId();
		}
		return roleType;
	}

	/**
	 * 初始化营销情况完成统计表
	 */
	public String initTskMarketingFinishReport() {
		try {
			initLoginRole();
			gradeList = taskGradeService.getUnDelActiveTaskGrade();
			return SUCCESS;
		} catch (Exception e) {
			log.error("getTskMarketingFinishReport action error", e);
			return ERROR;
		}
	}

	/**
	 * 查询营销情况完成统计表
	 */
	public String showTskMarketingFinishReport() {
		try {
			Map<String, Object> parameters = new HashMap<String, Object>();

			parameters.put("searchType", searchType);

			if (!StringUtil.isBlank(userIds)) {
				parameters.put("userIds", userIds);
			}else{
				parameters.put("userIds", getUserIdsString(false));
			}
			if (!StringUtil.isBlank(deptIds)) {
				parameters.put("deptIds", deptIds);
			}
			if (!StringUtil.isBlank(taskLevelId)) {
				parameters.put("taskLevelId", taskLevelId);
			}
			parameters.put("userId", this.getLoginInfo().getUserId());
			/**
			 * 执行日期搜索
			 */
			String sql = "";
			if (!StringUtil.isBlank(startDate) && StringUtil.isBlank(endDate)) {
				sql = " AND TO_DATE ('"+ startDate + "', 'yyyy-MM-dd')<=A.END_DATE  ";
				parameters.put("executeDateSearch", sql);
			} else if (StringUtil.isBlank(startDate)
					&& !StringUtil.isBlank(endDate)) {
				sql = " AND TO_DATE ('" + endDate + "', 'yyyy-MM-dd')>=A.START_DATE  ";
				parameters.put("executeDateSearch", sql);
			} else if (!StringUtil.isBlank(startDate)
					&& !StringUtil.isBlank(endDate)) {
				sql = " AND (A.START_DATE<=TO_DATE ('" + endDate + "', 'yyyy-MM-dd') and A.END_DATE>=TO_DATE ('"+ startDate + "', 'yyyy-MM-dd')) ";
				parameters.put("executeDateSearch", sql);
			}
			this.userName = this.getLoginInfo().getUserName();
			this.reportTime = new TimeWrapper(new Date().getTime());
			taskReportList = tskMarketingService.getTskMarketingFinishReportList(parameters);
			HttpSession session = getRequest().getSession();
			session.setAttribute("taskReportList", taskReportList);
			return SUCCESS;
		} catch (Exception e) {
			log.error("getTskMarketingFinishReport action error", e);
			return ERROR;
		}
	}
	
	/**
     * 获得登录用户所管理的部门用户的id集合
     * flag 为true是包含 为false不包含
     * @return
     */
    public String getUserIdsString(boolean flag) {
        String userIds = "";
        if (deptFacadeService.isInChargeOfDepartment()) {//存在业务主管
            Integer[] uids = deptFacadeService.getInChargeOfDeptUserIds(flag);
            if (uids != null) {
                for (Integer ids : uids) {
                    userIds = ids + "," + userIds;
                }
                userIds = userIds+getLoginInfo().getUserId().toString();
            }else userIds = getLoginInfo().getUserId().toString();
        } else {
            userIds = getLoginInfo().getUserId().toString();
        }
        return userIds;
    }


	/**
	 * 查看报表明细
	 * 
	 * @return
	 */
	public String showTskMarketingFinishReportDetail() {
		try {

			Map<String, Object> conds = new HashMap<String, Object>();
			conds.put("searchType", searchType);
			if("brDept".equals(searchType)){
				conds.put("deptId", request.getParameter("excuteId"));
			}else{
				conds.put("userId", request.getParameter("excuteId"));
			}

			conds.put("startDate",request.getParameter("startDate"));
			conds.put("endDate",request.getParameter("endDate"));
			if(!StringUtil.isBlank(taskLevelId)){
				conds.put("taskLevelId", taskLevelId);
			}
			tskMarketingBeanPage = tskMarketingService.GetTskMarketingFinishReportDetail(conds, this.getPage());
			int count = this.getPage().getTotalRowsAmount();
			request.setAttribute("count", String.valueOf(count));
			return SUCCESS;
		} catch (Exception e) {
			log.error("showTskMarketingFinishReportDetail action error", e);
			return ERROR;
		}
	}

	/**
	 * 格式化开始-结束时间
	 * 
	 * @param obj
	 * @param repairType
	 * @return
	 */
	private String repairDateTime(Object obj, String repairType) {
		String repairDate = "";
		if (obj != null) {
			if (StringUtil.isNotEmpty(obj.toString())) {
				if (repairType.equals("S")) {
					repairDate = obj.toString() + " 00:00:00";
				} else {
					repairDate = obj.toString() + " 23:59:59";
				}
			}
		}
		return repairDate;
	}

	/**
	 * 打印报表
	 * 
	 * @return
	 */
	public String tskMarketingFinishReportPrint() {
		try {
			showTskMarketingFinishReport();
			return SUCCESS;
		} catch (Exception e) {
			log.error("taskReportPrint action error:" + e.getMessage());
			return ERROR;
		}

	}

	/**
	 * 导出营销完成情况报表
	 */
	public void exprotTskMarketingFinishReport() {
		OutputStream outputStream = null;
		HSSFWorkbook workbook = null;
		try {
			String title = "";
			if (!StringUtil.isBlank(startDate) && !StringUtil.isBlank(endDate)) {
				title = startDate + "至" + endDate;
			}
			if (!StringUtil.isBlank(startDate) && StringUtil.isBlank(endDate)) {
				title = startDate + "之后";
			}
			if (StringUtil.isBlank(startDate) && !StringUtil.isBlank(endDate)) {
				title = "截止至" + endDate;
			}
			String reportName = title + "营销完成统计表";
			getResponse().setHeader(
					"Content-disposition",
					"attachment; filename="
							+ new String(reportName.getBytes("gbk"),
									"iso8859-1") + ".xls");// 设定输出文件头
			getResponse().setContentType("application/msexcel");// 定义输出类型
			getResponse().setCharacterEncoding("UTF-8");
			outputStream = getResponse().getOutputStream();
			HttpSession session = getRequest().getSession();
			List<TskMarketingReportBean> list = (List<TskMarketingReportBean>) session
					.getAttribute("taskReportList");
			workbook = tskMarketingService.exportTskMarketingFinishReportExcel(
					list, this.getLoginInfo().getUserName());
		} catch (Exception e) {
			e.printStackTrace();
			log.error("exportTskMarketingFinishReport action error:"
					+ e.getMessage());
			log.error(e.getMessage());
			workbook = new HSSFWorkbook();
			HSSFSheet sheet = workbook.createSheet();
			ExcelUtil.writeExcelDetailRow(sheet, new String[] { "导出报表发生系统异常" },
					0);
		} finally {
			ExcelUtil.writeToResponse(workbook, outputStream);
		}
	}

	public int compareStr(String str, String x[]) {
		int suffix = 0;// 记录字符串数组的下标
		for (int i = 0; i < x.length; i++) {
			if (str.equals(x[i].trim()))
				suffix = i + 1;
		}
		return suffix;
	}

	/**
	 * @return the tskMarketingService
	 */
	public TskMarketingService getTskMarketingService() {
		return tskMarketingService;
	}

	/**
	 * @param tskMarketingService
	 *            the tskMarketingService to set
	 */
	public void setTskMarketingService(TskMarketingService tskMarketingService) {
		this.tskMarketingService = tskMarketingService;
	}

	/**
	 * @return the taskReportList
	 */
	public List<TskMarketingReportBean> getTaskReportList() {
		return taskReportList;
	}

	/**
	 * @param taskReportList
	 *            the taskReportList to set
	 */
	public void setTaskReportList(List<TskMarketingReportBean> taskReportList) {
		this.taskReportList = taskReportList;
	}

	/**
	 * @return the roleType
	 */
	public Integer getRoleType() {
		return roleType;
	}

	/**
	 * @param roleType
	 *            the roleType to set
	 */
	public void setRoleType(Integer roleType) {
		this.roleType = roleType;
	}

	/**
	 * @return the table
	 */
	public DataTable getTable() {
		return table;
	}

	/**
	 * @param table
	 *            the table to set
	 */
	public void setTable(DataTable table) {
		this.table = table;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName
	 *            the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the userId
	 */
	public Integer getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	/**
	 * @return the reportTime
	 */
	public Date getReportTime() {
		return reportTime;
	}

	/**
	 * @param reportTime
	 *            the reportTime to set
	 */
	public void setReportTime(Date reportTime) {
		this.reportTime = reportTime;
	}

	/**
	 * @return the startDate
	 */
	public String getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate
	 *            the startDate to set
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the endDate
	 */
	public String getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate
	 *            the endDate to set
	 */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	/**
	 * @return the userIds
	 */
	public String getUserIds() {
		return userIds;
	}

	/**
	 * @param userIds
	 *            the userIds to set
	 */
	public void setUserIds(String userIds) {
		this.userIds = userIds;
	}

	/**
	 * @return the taskGradeService
	 */
	public TaskGradeService getTaskGradeService() {
		return taskGradeService;
	}

	/**
	 * @param taskGradeService
	 *            the taskGradeService to set
	 */
	public void setTaskGradeService(TaskGradeService taskGradeService) {
		this.taskGradeService = taskGradeService;
	}

	/**
	 * @return the gradeList
	 */
	public List<TaskGrade> getGradeList() {
		return gradeList;
	}

	/**
	 * @param gradeList
	 *            the gradeList to set
	 */
	public void setGradeList(List<TaskGrade> gradeList) {
		this.gradeList = gradeList;
	}

	/**
	 * @return the deptIds
	 */
	public String getDeptIds() {
		return deptIds;
	}

	/**
	 * @param deptIds
	 *            the deptIds to set
	 */
	public void setDeptIds(String deptIds) {
		this.deptIds = deptIds;
	}

	/**
	 * @return the taskLevelId
	 */
	public String getTaskLevelId() {
		return taskLevelId;
	}

	/**
	 * @param taskLevelId
	 *            the taskLevelId to set
	 */
	public void setTaskLevelId(String taskLevelId) {
		this.taskLevelId = taskLevelId;
	}

	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	public DeptFacadeService getDeptFacadeService() {
		return deptFacadeService;
	}

	public void setDeptFacadeService(DeptFacadeService deptFacadeService) {
		this.deptFacadeService = deptFacadeService;
	}

	public PageUtil<TskMarketingBean> getTskMarketingBeanPage() {
		return tskMarketingBeanPage;
	}

	public void setTskMarketingBeanPage(
			PageUtil<TskMarketingBean> tskMarketingBeanPage) {
		this.tskMarketingBeanPage = tskMarketingBeanPage;
	}
}
