/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :yanqf
 * Create Date:2013-5-20
 */
package com.banger.mobile.facade.impl.task.microTask;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.jfree.util.Log;

import com.banger.mobile.dao.dept.DeptDao;
import com.banger.mobile.dao.user.SysUserDao;
import com.banger.mobile.domain.collection.DataRow;
import com.banger.mobile.domain.collection.DataTable;
import com.banger.mobile.domain.model.customer.CrmCustomerTransfer;
import com.banger.mobile.domain.model.dept.SysDept;
import com.banger.mobile.domain.model.microTask.CounterOutPintMessage;
import com.banger.mobile.domain.model.microTask.TskAssignImportBar;
import com.banger.mobile.domain.model.microTask.TskMicroTask;
import com.banger.mobile.domain.model.microTask.TskMicroTaskExecute;
import com.banger.mobile.domain.model.microTask.TskMicroTaskExecuteAssign;
import com.banger.mobile.domain.model.sysImport.SysImport;
import com.banger.mobile.domain.model.user.IUserInfo;
import com.banger.mobile.domain.model.user.SysUser;
import com.banger.mobile.facade.dept.DeptFacadeService;
import com.banger.mobile.facade.microTask.TskMicroTaskAssignService;
import com.banger.mobile.facade.microTask.TskMicroTaskExecuteService;
import com.banger.mobile.facade.microTask.TskMicroTaskService;
import com.banger.mobile.facade.sysImport.SysImportService;
import com.banger.mobile.facade.user.SysUserService;
import com.banger.mobile.importUtil.ImportUtil;
import com.banger.mobile.importUtil.ReadExcel;
import com.banger.mobile.util.StringUtil;

/**
 * @author Yanqf
 * @version $Id: TskMicroTaskAssignServiceImpl.java,v 0.1 2013-5-20 上午10:10:00
 *          Yanqf Exp $
 */
public class TskMicroTaskAssignServiceImpl implements TskMicroTaskAssignService {
    private static final Logger logger = Logger
            .getLogger(TskMicroTaskAssignServiceImpl.class);

	// 当前的任务ID
	private int taskId;
	// 当前任务的信息
	private TskMicroTask tskMicroTask;
	// 系统用户信息
	private SysUserDao sysUserDao;
	// 系统机构信息
	private DeptDao deptDao;
	// 系统操作记录
	private SysImportService sysImportService;
	// 进度条
	private static Map<Integer, TskAssignImportBar> tskAssignBar;

	private TskMicroTaskExecute tskMicroTaskExecute;
	// 机构和权限
	private SysUserService sysUserService;
	private DeptFacadeService deptFacadeService;
	private TskMicroTaskService tskMicroTaskService;
	private TskMicroTaskExecuteService tskMicroTaskExecuteService;

	// 经过分析后需要新增或更新的数据
	private Map<Integer, TskMicroTaskExecute> reaptAddMap = null;
	private Map<Integer, TskMicroTaskExecute> reaptUpdMap = null;
	// 所有在行客户和部门
	private Map<String, SysUser> allUserMap = null;
	private Map<String, SysDept> allDeptMap = null;
	// 所有客户
	private Map<String, SysUser> sysUserMap = null;
	// 所有部门
	private Map<String, SysDept> sysDeptMap = null;
	// private Map<Integer, Integer> sysAssedMap = null;
	// 该任务所有执行者数据
	private Map<Integer, TskMicroTaskExecute> exeTskMap = null;
	// 该任务所有执行者和机构的完成情况
	private Map<Integer, TskMicroTaskExecuteAssign> assignTskMap = null;
	private List<TskMicroTaskExecuteAssign> l6 = null;

	/**
	 * @return
	 * @see com.banger.mobile.facade.microTask.TskMicroTaskAssignService#excelToDataBase()
	 */
	@SuppressWarnings("unchecked")
	public CounterOutPintMessage excelToDataBase(Map<String, Object> paramMap) {
		try {
			HttpServletRequest request = org.apache.struts2.ServletActionContext
					.getRequest();
			CounterOutPintMessage cMessage = new CounterOutPintMessage();
			DataTable dt = new DataTable();
			Map<String, Integer> recordMap = (Map<String, Integer>) request
					.getSession().getAttribute("recordMap");
			cMessage.setSumRecord(recordMap.get("total"));
			Integer nullCount = recordMap.get("nullCount");
			Integer sumCount = recordMap.get("total") + nullCount;
			Integer batchSize = ImportUtil.getBatchSize(sumCount);
			Integer bathchCount = ImportUtil
					.getBathchCount(sumCount, batchSize);
			Integer successRecordCount = 0;
			Integer erroRecord = recordMap.get("erroRecord");
			Integer beginSize = 0;
			String errorFilePath = "";
			CrmCustomerTransfer cct = new CrmCustomerTransfer();
			// 初始化用于存储数据的Map
			initParameter(cct, paramMap);
			// 获取错误的信息
			cct.getParameterMap().put("erroRecord",
					"" + recordMap.get("erroRecord"));
			if (erroRecord == null) {
				erroRecord = 0;
			}
			// 获取表头信息
			ImportUtil.getExeclHeadTask(cct.getErrortbl(),
					String.valueOf(paramMap.get("fileName")), erroRecord);
			int k = 1;
			// 将Excel放入缓存避免多次读取
			cct.setWorkbook(ReadExcel.getExcelType(String.valueOf(paramMap
					.get("fileName"))));

			long startTime = System.currentTimeMillis(); // 获取开始时间

			// 获取当前任务的信息
			tskMicroTask = tskMicroTaskService.getTskMicroTaskById(taskId);
			paramMap.put("tskMicroTask", tskMicroTask);

			paramMap.put("successRecordCount", successRecordCount);
			List<SysUser> l1 = null;
			List<SysDept> l2 = null;
			if (paramMap.get("imType").equals("sysUser")) {
				// 获取所有的在行用户
				l1 = sysUserDao.getAllData();
			} else {
				l2 = deptDao.getAllDepts();
			}

			// 查询所有的下属
			List<SysUser> l3 = deptFacadeService
					.getBusinessManagerInCharegeOfUsers();
			l3.add(sysUserService
					.getSysUserById(getUserLoginInfo().getUserId()));
			// 查询当前用户和其下属是否已分配任务
			int[] sysUserId = new int[l3.size()];
			for (int i = 0; i < l3.size(); i++) {
				sysUserId[i] = l3.get(i).getUserId();
			}
			// Map<String, Object> map = new HashMap<String, Object>();
			// map.put("taskId", taskId);
			// map.put("userIds", sysUserId);
			// List<Integer> l7 = tskMicroTaskExecuteService
			// .getAllocateTaskByUsers(map);
			// 查询所有的下属机构
			List<SysDept> l4 = deptFacadeService
					.getBusinessManagerInCharegeDeptTreeList();
			// 查询所有任务执行者数据
			HashMap<String, Object> conds = new HashMap<String, Object>();
			conds.put("taskId", taskId);
			List<TskMicroTaskExecute> l5 = null;

			// 初始化在行客户，所有部门
			initCurTaskInfoDatas(l1, l2, l3, l4);
			// 循环批处理操作
			for (int i = 0; i < bathchCount; i++) {
				// 查询所有任务执行者数据
				l5 = tskMicroTaskExecuteService
						.getAllTskMicroTaskExecuteByConds(conds);
				// 查询当前任务的所有被分配者的完成情况
				l6 = tskMicroTaskExecuteService
						.getAllTskMicroTaskExecuteAssignByConds(tskMicroTask,
								getUserLoginInfo().getUserId());
				// 当前任务执行者数据，完成情况
				initCurTaskExeInfoDatas(l5, l6);
				// 将之前的错误表格中的表头复制到导入表格中
				ImportUtil.copyDataTableHead(dt, cct);
				// 执行导入数据
				ImportUtil.TaskAlloCatProcess(batchSize, beginSize, dt, cct);
				// 格式验证
				ImportUtil.getCheckAssignTasks(dt, cct, paramMap);
				// 获取Excel中的数据,业务验证,实体对应
				receiveExcelData(dt, paramMap, cct);
				// 得到处理后的数据
				handleReaptExcelData(reaptAddMap, reaptUpdMap, cct);
				reaptAddMap.clear();
				reaptUpdMap.clear();
				if (exeTskMap != null) {
					exeTskMap.clear();
				}
				assignTskMap.clear();
				if (cct.getAddBeanList() != null
						&& cct.getAddBeanList().size() > 0) {
					addTskMicroTaskBatch(cct);
					cct.getAddBeanList().clear();
				}
				// 已存在执行时，更新执行任务
				if (cct.getUpdateBeanList() != null
						&& cct.getUpdateBeanList().size() > 0) {
					updTskMicroTaskBatch(cct);
					cct.getUpdateBeanList().clear();
				}
				// 将错误的信息转移到错误表中
				ImportUtil.copyEorrBuyRecord(dt, cct);
				beginSize = batchSize * k;
				// 循环到最后一次将错误日志输出,必免错误文件太大引发进度条显示延迟
				if (i == (bathchCount - 1)) {
					// 导出失败的文件
					if (cct.getErrortbl() != null
							&& cct.getErrortbl().colSize() > 0) {
						errorFilePath = ImportUtil.exportExcel(
								cct.getErrortbl(), cct);
					}
				}
				TskAssignImportBar bar = tskAssignBar.get(this
						.getUserLoginInfo().getUserId());
				if (k == bathchCount - 1) {
					if (bar.getIsRun().equals(1) && bar.getIsStop().equals(0)) {
						bar.setCuurRow(sumCount);
					} else {
						bar.setCuurRow(0);
						break;
					}
				} else if (bar.getIsRun().equals(1)
						&& bar.getIsStop().equals(0)) {
					// 当前执行的行数
					bar.setCuurRow(k * batchSize);
					k++;
				} else {
					bar.setCuurRow(0);
					break;
				}
				dt.clear();
				successRecordCount += cct.getAddCount();
				cct.setAddCount(0);
				cct.setCustomerNoStr("");
			}
			// 获取结束时间
			long endTime = System.currentTimeMillis();
			System.out.println("导入完成花费时间： " + (endTime - startTime) + "ms");
			cMessage.setErrorFilePath(errorFilePath);
			cMessage.setSumRecord(sumCount);
			cMessage.setSuccessRecordCount(successRecordCount);
			cMessage.setFailRecordCount(sumCount - successRecordCount
					- nullCount);
			addSysImportInfo(cMessage);
			return cMessage;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}

	/**
	 * 将最后处理好的数据放入List
	 * 
	 * @param reaptAddMap
	 * @param reaptUpdMap
	 * @param cct
	 */
	private void handleReaptExcelData(
			Map<Integer, TskMicroTaskExecute> reaptAddMap,
			Map<Integer, TskMicroTaskExecute> reaptUpdMap,
			CrmCustomerTransfer cct) {
		if (reaptAddMap != null && reaptAddMap.size() > 0) {
			Iterator<Integer> it = reaptAddMap.keySet().iterator();
			// 新增数据
			List<TskMicroTaskExecute> addList = new ArrayList<TskMicroTaskExecute>();
			while (it.hasNext()) {
				addList.add(reaptAddMap.get((Integer) it.next()));
			}
			cct.setAddBeanList(addList);
		}
		if (reaptUpdMap != null && reaptUpdMap.size() > 0) {
			Iterator<Integer> it = reaptUpdMap.keySet().iterator();
			// 更新数据
			List<TskMicroTaskExecute> updList = new ArrayList<TskMicroTaskExecute>();
			while (it.hasNext()) {
				updList.add(reaptUpdMap.get((Integer) it.next()));
			}
			cct.setUpdateBeanList(updList);

		}

	}

	/**
	 * 初始化在行客户，所有部门，当前任务执行者数据，完成情况
	 * 
	 * @param l1
	 * @param l2
	 * @param l3
	 * @param l4
	 */
	private void initCurTaskInfoDatas(List<SysUser> l1, List<SysDept> l2,
			List<SysUser> l3, List<SysDept> l4) {
		if (l1 != null && l1.size() > 0) {
			allUserMap = new HashMap<String, SysUser>();
			for (SysUser s : l1) {
				allUserMap.put(s.getAccount(), s);
			}
		}
		if (l2 != null && l2.size() > 0) {
			allDeptMap = new HashMap<String, SysDept>();
			for (SysDept s : l2) {
				allDeptMap.put(s.getDeptCode(), s);
			}
		}
		if (l3 != null && l3.size() > 0) {
			sysUserMap = new HashMap<String, SysUser>();
			for (SysUser s : l3) {
				sysUserMap.put(s.getAccount(), s);
			}
		}
		if (l4 != null && l4.size() > 0) {
			sysDeptMap = new HashMap<String, SysDept>();
			for (SysDept s : l4) {
				sysDeptMap.put(s.getDeptCode(), s);
				sysDeptMap.put(s.getDeptId() + "s", s);
			}
		}
		// if (l7 != null && l7.size() > 0) {
		// sysAssedMap = new HashMap<Integer, Integer>();
		// for (Integer s : l7) {
		// sysAssedMap.put(s, s);
		// }
		// }
	}

	/**
	 * 当前任务执行者数据，完成情况
	 * 
	 * @param l5
	 * @param l6
	 */
	private void initCurTaskExeInfoDatas(List<TskMicroTaskExecute> l5,
			List<TskMicroTaskExecuteAssign> l6) {
		if (l5 != null && l5.size() > 0) {
			exeTskMap = new HashMap<Integer, TskMicroTaskExecute>();
			for (TskMicroTaskExecute t : l5) {
				exeTskMap.put(t.getDeptId() + t.getUserId(), t);
			}
		}
		if (l6 != null && l6.size() > 0) {
			assignTskMap = new HashMap<Integer, TskMicroTaskExecuteAssign>();
			for (TskMicroTaskExecuteAssign t : l6) {
				assignTskMap.put(t.getId(), t);
			}
		}
	}

	/**
	 * 逻辑处理
	 * 
	 * @param dt
	 *            Excel数据表
	 * @param map
	 *            参数(所有机构,我的下属机构,我的下属人员,机构任务完成情况...)
	 * @param cct
	 *            容器
	 */
	private void receiveExcelData(DataTable dt, Map<String, Object> map,
			CrmCustomerTransfer cct) {
		if (dt.rowSize() > 0) {
			// 定义用于存放要修改和新增的数据
			reaptAddMap = new HashMap<Integer, TskMicroTaskExecute>();
			reaptUpdMap = new HashMap<Integer, TskMicroTaskExecute>();
			for (int i = 0; i < dt.rowSize(); i++) {

				DataRow dataRow = dt.getRow(i);
				// 指定的用户
				String assignObj = null;
				// 指定用户的任务数
				int assignNum = 0;

				// 是否是错误的行
				if (StringUtil.isEmpty(ImportUtil.checkStrNull(dataRow
						.get("errorMsg")))) {
					if (!StringUtil.isBlank(dataRow.get("assignOb") + "")) {
						assignObj = ((String) dataRow.get("assignOb") + "")
								.trim();
					}
					String assign = dataRow.get("assignTa") + "";
					if (!StringUtil.isBlank(assign)) {
						if (assign.length() > 10) {
							assignNum = Integer
									.valueOf(assign.substring(0, 10));
						} else {
							assignNum = Integer.valueOf(assign);
						}
					}
					// 导入执行者或机构
					if (map.get("imType").equals("sysDept")) {
						if (allDeptMap != null
								&& !allDeptMap.containsKey(assignObj)) {
							String errorMsg = "您导入的机构“" + assignObj
									+ "”不是系统中的机构";
							dataRow.set("errorMsg", errorMsg);
							continue;
						}
						// 是我的下属机构
						if (sysDeptMap.containsKey(assignObj)) {
							TskMicroTaskExecute task = new TskMicroTaskExecute();
							task.setTaskId(taskId);
							task.setDeptId(sysDeptMap.get(assignObj)
									.getDeptId());
							task.setTargetUser(0);
							task.setTargetDept(assignNum);
							task.setUserId(0);
							// 是否已被分配任务
							isMySubDept(task, map, dataRow, cct, assignObj);
						} else {
							String errorMsg = "您不能为“" + assignObj + "”机构分配任务";
							dataRow.set("errorMsg", errorMsg);
						}
					} else {
						if (allUserMap != null
								&& !allUserMap.containsKey(assignObj)) {
							String errorMsg = "您导入的用户“" + assignObj
									+ "”不是系统中的用户";
							dataRow.set("errorMsg", errorMsg);
							continue;
						}
						// 是我的下属人员
						SysUser sysUser = sysUserMap.get(assignObj);
						if (sysUser != null) {
							// if (sysAssedMap != null
							// && sysAssedMap.containsKey(sysUser
							// .getUserId())) {
							// dataRow.set("errorMsg",
							// "一个执行者只能分配一个贷款任务，不能重复分配！");
							// } else {
							TskMicroTaskExecute task = new TskMicroTaskExecute();
							task.setTaskId(taskId);
							task.setDeptId(sysUserMap.get(assignObj)
									.getDeptId());
							task.setTargetUser(assignNum);
							task.setTargetDept(0);
							task.setUserId(sysUserMap.get(assignObj)
									.getUserId());
							// 是否已被分配任务
							isMySubUser(task, map, dataRow, cct, assignObj);
							// }
						} else {
							// 是系统用户但不是我的下属
							dataRow.set("errorMsg", "您不能为“" + assignObj
									+ "”分配任务");
						}
					}
				}
			}
		}
	}

	/**
	 * 是我的下属人员
	 * 
	 * @param task
	 *            当前的人员任务分配
	 * @param map
	 *            参数(所有机构,我的下属机构,我的下属人员,机构任务完成情况...)
	 * @param dataRow
	 *            当前行
	 * @param cct
	 *            容器
	 * @param userName
	 *            人员名称
	 */
	private void isMySubUser(TskMicroTaskExecute task, Map<String, Object> map,
			DataRow dataRow, CrmCustomerTransfer cct, String userName) {

		if (exeTskMap != null && exeTskMap.size() > 0) {
			// 获取父机构的数据
			TskMicroTaskExecute par = exeTskMap.get(task.getDeptId());
			// 获取当前执行者数据
			TskMicroTaskExecute cur = exeTskMap.get(task.getDeptId()
					+ +task.getUserId());
			// 当前执行者完成情况
			TskMicroTaskExecuteAssign curAssign = assignTskMap.get(task
					.getUserId());
			int ispass = isPassUserValidation(par, cur, curAssign, task);
			if (ispass == 1) {
				// 该执行者所在的机构没有被分配任务
				String errorMsg = "您导入的用户“"
						+ userName
						+ "”所属机构“"
						+ sysDeptMap.get(task.getDeptId() + "s").getDeptName()
						+ "”未被分配任务“"
						+ ((TskMicroTask) map.get("tskMicroTask"))
								.getTaskTitle() + "”，不能为该用户分配任务";
				dataRow.set("errorMsg", errorMsg);
			}
			if (ispass == 2) {
				dataRow.set("errorMsg", "下级目标任务总和不能超过上级任务目标");
			}
			if (ispass == 3) {
				dataRow.set("errorMsg", "任务目标不能够低于“" + curAssign.getComTskNum()
						+ "”笔");
			}
			if (ispass == 0) {
				// 数据正确成功的条数加1
				cct.setAddCount(cct.getAddCount() + 1);
				// 更新父机构数据
				if (cur != null) {
					if (cur.getTaskExecuteId() != null) {
						updateParAssignData(par, cur, task);
						reaptUpdMap.put(task.getDeptId() + task.getUserId(),
								task);
						reaptUpdMap.put(par.getDeptId(), par);

					} else {
						updateParAssignData(par, cur, task);
						reaptAddMap.put(task.getDeptId() + task.getUserId(),
								task);
						reaptUpdMap.put(par.getDeptId(), par);
					}
				} else {
					updateParAssignData(par, null, task);
					reaptAddMap.put(task.getDeptId() + task.getUserId(), task);
					reaptUpdMap.put(par.getDeptId(), par);
				}
			}
		} else {
			String errorMsg = "上级机构未被分配任务";
			dataRow.set("errorMsg", errorMsg);
		}
	}

	/**
	 * 是我的下属机构
	 * 
	 * @param task
	 * @param map
	 * @param dataRow
	 * @param cct
	 * @param userName
	 */
	private void isMySubDept(TskMicroTaskExecute task, Map<String, Object> map,
			DataRow dataRow, CrmCustomerTransfer cct, String deptName) {

		// 是否银行总行
		boolean isTotalDept = false;
		// 当前导入的部门
		SysDept curDept = sysDeptMap.get(deptName);
		// 获取父机构的数据
		TskMicroTaskExecute par = null;
		// 获取当前执行者数据
		TskMicroTaskExecute cur = null;
		if (exeTskMap == null) {
			exeTskMap = new HashMap<Integer, TskMicroTaskExecute>();
		}
		par = exeTskMap.get(curDept.getDeptParentId());
		cur = exeTskMap.get(task.getDeptId());
		// 当前执行者完成情况
		TskMicroTaskExecuteAssign curAssign = assignTskMap
				.get(task.getDeptId());

		// 如果是银行总行
		if (curDept.getDeptId() == 3) {
			// 是银行总行
			isTotalDept = true;
			TskMicroTaskExecuteAssign parAssign = assignTskMap.get(-1);
			par = exeTskMap.get(curAssign.getEpid());
			if (par == null) {
				par = new TskMicroTaskExecute();
				par.setDeptId(curDept.getDeptParentId());
				par.setTargetDept(tskMicroTask.getTaskTarget());
				Integer unAssgin = parAssign == null ? null : parAssign
						.getTargetDept();
				if (unAssgin != null && cur != null) {
					unAssgin = cur.getTargetDept() == null ? unAssgin
							: unAssgin - cur.getTargetDept();
				}
				par.setTargetDeptUnassign(unAssgin != null ? unAssgin
						: tskMicroTask.getTaskTarget());
			}
		}
		int ispass = isPassUserValidation(par, cur, curAssign, task);
		if (ispass == 1) {
			// 该执行者所在的机构没有被分配任务
		    /** 2013-12-12 liyb修改把task.getDeptId()改成 curDept.getDeptParentId() **/
			String errorMsg = "机构“" + curDept.getDeptName() + "”分配的任务大于上级机构“"
            + sysDeptMap.get(curDept.getDeptParentId() + "s").getDeptName()
            + "”未分配任务," + "不能为该机构分配任务";
			dataRow.set("errorMsg", errorMsg);

		}
		if (ispass == 2) {
			String errorMsg = "下级目标任务总和不能超过上级任务目标";
			dataRow.set("errorMsg", errorMsg);
		}
		if (ispass == 3) {
			String errorMsg = "任务目标不能够低于“" + curAssign.getComTskNum() + "”笔";
			dataRow.set("errorMsg", errorMsg);
		}
		if (ispass == 4) {
			String errorMsg = "上级机构目标任务小于已分配的下级机构任务目标总和";
			dataRow.set("errorMsg", errorMsg);
		}
		if (ispass == 0) {
			// 数据正确成功的条数加1
			cct.setAddCount(cct.getAddCount() + 1);
			// 是银行总行
			if (cur != null) {
				if (cur.getTaskExecuteId() != null) {
					updateParAssignData(par, cur, task);
					reaptUpdMap.put(task.getDeptId(), task);
					if (!isTotalDept) {
						reaptUpdMap.put(par.getDeptId(), par);
					}
				} else {
					updateParAssignData(par, cur, task);
					reaptAddMap.put(task.getDeptId(), task);
					if (!isTotalDept) {
						reaptAddMap.put(par.getDeptId(), par);
					}
				}
			} else {
				updateParAssignData(par, null, task);
				reaptAddMap.put(task.getDeptId(), task);
				if (!isTotalDept) {
					if (par.getTaskExecuteId() == null) {
						reaptAddMap.put(par.getDeptId(), par);
					} else {
						reaptUpdMap.put(par.getDeptId(), par);
					}
				}
			}
		}
	}

	/**
	 * 更新导入时更改的数据
	 * 
	 * @param par
	 * @param cur
	 * @param task
	 */
	private void updateParAssignData(TskMicroTaskExecute par,
			TskMicroTaskExecute cur, TskMicroTaskExecute task) {
		// 获取当前任务指定数
		int isNowDeptOrUser = 0;
		// 获取先前任务指定数
		int isOldDeptOrUser = 0;
		if (cur != null) {
			isOldDeptOrUser = task.getUserId() > 0 ? cur.getTargetUser() : cur
					.getTargetDept();
			task.setTaskExecuteId(cur.getTaskExecuteId());
		}
		if (task.getUserId() > 0) {
			isNowDeptOrUser = task.getTargetUser();
			task.setTargetDeptUnassign(0);
		} else {
			isNowDeptOrUser = task.getTargetDept();
			int un = cur != null ? isNowDeptOrUser
					- (cur.getTargetDept() - cur.getTargetDeptUnassign())
					: isNowDeptOrUser;
			task.setTargetDeptUnassign(un);
		}
		exeTskMap.put(task.getDeptId() + task.getUserId(), task);
		// 修改后的数据更新到数据库
		if (par != null) {
			par.setTargetDeptUnassign(par.getTargetDeptUnassign()
					+ (isOldDeptOrUser - isNowDeptOrUser));
			// (批处理时修改的数据没有马上更新到数据库)全局变量--所有任务执行者数据更新
			exeTskMap.put(par.getDeptId(), par);
		}
	}

	/**
	 * 验证导入的数据逻辑
	 * 
	 * @param par
	 * @param cur
	 * @param curAssign
	 * @param task
	 * @return
	 */
	private int isPassUserValidation(TskMicroTaskExecute par,
			TskMicroTaskExecute cur, TskMicroTaskExecuteAssign curAssign,
			TskMicroTaskExecute task) {
		if (par != null && par.getTargetDept() > 0) {
			// 分配数
			int isExists = 0;
			// 已完成任务数
			int comNum = 0;
			// 如果是机构取TargetDept 否则取 TargetUser
			int targetNum = task.getUserId() > 0 ? task.getTargetUser() : task
					.getTargetDept();
			// 是否已存在
			if (cur != null) {
				// 取出先前分配给同一个机构或人员的分配数
				// 如果是机构取TargetDept 否则取 TargetUser
				isExists = cur.getUserId() > 0 ? cur.getTargetUser() : cur
						.getTargetDept();

			}
			// 获取已完成数
			if (curAssign != null && curAssign.getComTskNum() > 0) {
				comNum = curAssign.getComTskNum();
			}
			// 判断指定任务数是否大于上级机构未分配数
			if (targetNum - isExists > par.getTargetDeptUnassign()) {
				// 分配数大于上级机构未分配数
				return 2;
			}
			if (targetNum < comNum) {
				// 分配数小于已完成数
				return 3;
			}
			// 导入机构时分配数小于已分配的下属机构
			if (task.getUserId() <= 0) {
				if (targetNum < getUnderDeptRecursion(curAssign, l6)) {
					return 4;
				}
			}
		} else {
			// 上级机构未被分配任务
			return 1;
		}
		return 0;
	}

	/**
	 * 递归得到所有下属机构的总分配数
	 * 
	 * @param task
	 * @param list
	 * @return
	 */
	private int getUnderDeptRecursion(TskMicroTaskExecuteAssign task,
			List<TskMicroTaskExecuteAssign> list) {
		int underTargets = 0;
		for (TskMicroTaskExecuteAssign t : list) {
			if (task.getId().equals(t.getEpid())) {
				if (t.getAssignTotalTarget().equals(-1)) {
					underTargets = underTargets
							+ getUnderDeptRecursion(t, list);
				} else {
					underTargets = underTargets + t.getAssignTotalTarget();
				}
			}
		}
		return underTargets;
	}

	/**
	 * 添加到数据库
	 * 
	 * @param cct
	 */
	@SuppressWarnings("unchecked")
	private void addTskMicroTaskBatch(CrmCustomerTransfer cct) {
		tskMicroTaskExecuteService
				.addTskMicroTaskExecuteBatch((List<TskMicroTaskExecute>) cct
						.getAddBeanList());
	}

	/**
	 * 修改到数据库
	 * 
	 * @param cct
	 */
	@SuppressWarnings("unchecked")
	private void updTskMicroTaskBatch(CrmCustomerTransfer cct) {
		tskMicroTaskExecuteService
				.editTskMicroTaskExecuteBatch((List<TskMicroTaskExecute>) cct
						.getUpdateBeanList());
	}

	/**
	 * 获取进度条
	 * 
	 * @return
	 */
	public static Map<Integer, TskAssignImportBar> getTskAssignImportBar() {
		if (tskAssignBar == null)
			tskAssignBar = new HashMap<Integer, TskAssignImportBar>();
		return tskAssignBar;
	}

	/**
	 * @param counterOutPintMessageu
	 */
	private void addSysImportInfo(CounterOutPintMessage counterOutPintMessageu) {
		SysImport sysImport = new SysImport();
		sysImport.setCreateUser(getUserLoginInfo().getUserId());
		sysImport.setUpdateUser(getUserLoginInfo().getUserId());
		sysImport.setTotalNum(counterOutPintMessageu.getSumRecord());
		sysImport.setSuccessNum(counterOutPintMessageu.getSuccessRecordCount());
		sysImport.setFailNum(counterOutPintMessageu.getFailRecordCount());
		sysImport
				.setFailFilePath(counterOutPintMessageu.getErrorFilePath() == null ? ""
						: counterOutPintMessageu.getErrorFilePath());
		sysImport.setImportNo("");
		sysImport.setImportDate(new Date());
		sysImportService.addSysImport(sysImport);
	}

	/**
	 * 获取登录信息
	 * 
	 * @return
	 */
	private IUserInfo getUserLoginInfo() {
		HttpServletRequest req = org.apache.struts2.ServletActionContext
				.getRequest();
		HttpSession session = req.getSession();
		return (IUserInfo) session.getAttribute("LoginInfo");
	}

	/**
	 * 初始化
	 * 
	 * @param cct
	 */
	public void initParameter(CrmCustomerTransfer cct, Map<String, Object> map) {
		taskId = Integer.valueOf(Integer.valueOf(map.get("taskId") + ""));
		Map<String, String> pMap = new HashMap<String, String>();
		cct.setParameterMap(pMap);
		cct.setErrortbl(new DataTable());
		cct.setAddCount(new Integer(0));
	}

	// getter setter Method

	public TskMicroTask getTskMicroTask() {
		return tskMicroTask;
	}

	public void setTskMicroTask(TskMicroTask tskMicroTask) {
		this.tskMicroTask = tskMicroTask;
	}

	public TskMicroTaskExecute getTskMicroTaskExecute() {
		return tskMicroTaskExecute;
	}

	public SysUserDao getSysUserDao() {
		return sysUserDao;
	}

	public void setSysUserDao(SysUserDao sysUserDao) {
		this.sysUserDao = sysUserDao;
	}

	public DeptDao getDeptDao() {
		return deptDao;
	}

	public void setDeptDao(DeptDao deptDao) {
		this.deptDao = deptDao;
	}

	public SysUserService getSysUserService() {
		return sysUserService;
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

	public TskMicroTaskService getTskMicroTaskService() {
		return tskMicroTaskService;
	}

	public void setTskMicroTaskService(TskMicroTaskService tskMicroTaskService) {
		this.tskMicroTaskService = tskMicroTaskService;
	}

	public TskMicroTaskExecuteService getTskMicroTaskExecuteService() {
		return tskMicroTaskExecuteService;
	}

	public void setTskMicroTaskExecuteService(
			TskMicroTaskExecuteService tskMicroTaskExecuteService) {
		this.tskMicroTaskExecuteService = tskMicroTaskExecuteService;
	}

	public void setTskMicroTaskExecute(TskMicroTaskExecute tskMicroTaskExecute) {
		this.tskMicroTaskExecute = tskMicroTaskExecute;
	}

	public SysImportService getSysImportService() {
		return sysImportService;
	}

	public void setSysImportService(SysImportService sysImportService) {
		this.sysImportService = sysImportService;
	}
}
