/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :任务目标客户-Service-接口实现
 * Author     :QianJie
 * Create Date:Mar 2, 2013
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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.dao.customer.CrmCustomerDao;
import com.banger.mobile.dao.microTask.TskMicroTaskTargetDao;
import com.banger.mobile.domain.collection.DataRow;
import com.banger.mobile.domain.collection.DataTable;
import com.banger.mobile.domain.model.customer.CounterOutPintMessage;
import com.banger.mobile.domain.model.customer.CrmCustomer;
import com.banger.mobile.domain.model.customer.CrmCustomerTransfer;
import com.banger.mobile.domain.model.customer.CustomerExportBar;
import com.banger.mobile.domain.model.microTask.TskMicroTaskTarget;
import com.banger.mobile.domain.model.sysImport.SysImport;
import com.banger.mobile.domain.model.user.IUserInfo;
import com.banger.mobile.facade.loan.LnLoanService;
import com.banger.mobile.facade.microTask.TskMicroTaskTargetService;
import com.banger.mobile.facade.sysImport.SysImportService;
import com.banger.mobile.importUtil.ImportUtil;
import com.banger.mobile.importUtil.ReadExcel;
import com.banger.mobile.util.StringUtil;

/**
 * @author QianJie
 * @version $Id: TskMicroTaskTargetServiceImpl.java,v 0.1 Mar 2, 2013 11:53:45
 *          AM QianJie Exp $
 */
public class TskMicroTaskTargetServiceImpl implements
		TskMicroTaskTargetService {

	private TskMicroTaskTargetDao tskMicroTaskTargetDao; // dao的依赖注入
	private CrmCustomerDao crmCustomerDao; // 客户dao
	private static Map<Integer, CustomerExportBar> customerExportBar; // 进度条
	private SysImportService sysImportService;
	private LnLoanService lnLoanService;
	protected final Log log = LogFactory.getLog(getClass());
	// 实例化用于存储数据库中与Excel中地换号码相同的数据
	List<TskMicroTaskTarget> tskList = new ArrayList<TskMicroTaskTarget>();
	List<CrmCustomer> crmList = new ArrayList<CrmCustomer>();

	List<TskMicroTaskTarget> updCusTargetList = new ArrayList<TskMicroTaskTarget>();
	private Map<String, TskMicroTaskTarget> reaptMap = new HashMap<String, TskMicroTaskTarget>();

	public void addTaskTarget(TskMicroTaskTarget tskMicroTaskTarget) {
		tskMicroTaskTargetDao.addTaskTarget(tskMicroTaskTarget);
	}

	/**
	 * 获取进度条
	 * 
	 * @return
	 */
	public static Map<Integer, CustomerExportBar> getCustomerExportBar() {
		if (customerExportBar == null)
			customerExportBar = new HashMap<Integer, CustomerExportBar>();
		return customerExportBar;
	}

	/**
	 * 批量添加
	 * 
	 * @param list
	 * @see com.banger.mobile.facade.microTask.TskMicroTaskTargetService#addTaskTargetBatch(java.util.List)
	 */
	public void addTaskTargetBatch(List<TskMicroTaskTarget> list) {
		tskMicroTaskTargetDao.addTaskTargetBatch(list);
	}

	public PageUtil<TskMicroTaskTarget> getTargetListByPage(
			Map<String, Object> parameterMap, Page page) {
		return tskMicroTaskTargetDao.getTargetListByPage(parameterMap, page);
	}

    public PageUtil<TskMicroTaskTarget> getTargetListForPad(Map<String, Object> parameterMap, Page page) {
        return tskMicroTaskTargetDao.getTargetListForPad(parameterMap, page);
    }

    public PageUtil<TskMicroTaskTarget> getTargetList(
			Map<String, Object> parameterMap, Page page) {
		return tskMicroTaskTargetDao.getTargetList(parameterMap, page);
	}

	/**
	 * 通过phoneNumber查询
	 * 
	 * @param target
	 * @return
	 * @see com.banger.mobile.facade.microTask.TskMicroTaskTargetService
	 */
	public List<TskMicroTaskTarget> getMicroTaskTarget(TskMicroTaskTarget target) {
		List<TskMicroTaskTarget> list = new ArrayList<TskMicroTaskTarget>();
		list = tskMicroTaskTargetDao.queryByPhoneNo(target);
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	/**
	 * Excel文件路径
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public CounterOutPintMessage excelToDb(String fileName,
			TskMicroTaskTarget tskTarget) {
		try {
			HttpServletRequest request = org.apache.struts2.ServletActionContext
					.getRequest();
			CounterOutPintMessage counterOutPintMessageu = new CounterOutPintMessage();
			DataTable dt = new DataTable();
			Map<String, Integer> recordMap = (Map<String, Integer>) request
					.getSession().getAttribute("recordMap");
			counterOutPintMessageu.setSumRecord(recordMap.get("total"));
			Integer nullCount = recordMap.get("nullCount");
			Integer sumCount = recordMap.get("total") + nullCount;
			Integer batchSize = 500;
			Integer bathchCount = ImportUtil
					.getBathchCount(sumCount, batchSize);
			Integer successRecordCount = 0;
			Integer beginSize = 0;
			String errorFilePath = "";
			CrmCustomerTransfer cct = new CrmCustomerTransfer();
			// 初始化用于存储数据的Map
			initParameter(cct);
			// 获取错误的信息
			cct.getParameterMap().put("erroRecord",
					"" + recordMap.get("erroRecord"));
			// 获取错误的条数
			int errorSum = recordMap.get("erroRecord");
			// 生成错误表头
			ImportUtil
					.getExeclHeadTarget(cct.getErrortbl(), fileName, errorSum);
			int k = 0;
			// 将Excel放入缓存避免多次读取
			cct.setWorkbook(ReadExcel.getExcelType(fileName));

			long startTime = System.currentTimeMillis(); // 获取开始时间

			for (int i = 0; i < bathchCount; i++) {
				// 将之前的错误表格中的表头复制到导入表格中
				ImportUtil.copyDataTableHead(dt, cct);
				// 执行导入数据
				ImportUtil.BatchProcessTarget(batchSize, beginSize, dt, cct);
				// 格式验证
				ImportUtil.getCheckTskCustomer(dt, cct);
				// 获取Excel中的数据
				List<TskMicroTaskTarget> vonExceList = dataFromExcel(dt,
						tskTarget);
				// 获取号码相同的在行客户或任务目标
				getDataEqualsVonDBs(vonExceList, tskTarget);
				// 业务验证,实体对应
				Map<Integer, TskMicroTaskTarget> map = validatorTaskTarget(dt,
						vonExceList);
				List<TskMicroTaskTarget> addBeanList = new ArrayList<TskMicroTaskTarget>();
				if (map != null && map.size() > 0) {
					for (Iterator<Integer> iter = map.keySet().iterator(); iter
							.hasNext();) {
						addBeanList.add(map.get(iter.next()));
					}
					addTskMicroTaskBatch(addBeanList);
				}
				if (updCusTargetList.size() > 0) {
					updTskMicroTaskBatch(updCusTargetList);
				}
				ImportUtil.copyEorrBuyRecord(dt, cct);// 将错误的信息转移到错误表中
				successRecordCount += addBeanList.size()
						+ updCusTargetList.size();

				// 循环到最后一次将错误日志输出,必免错误文件太大引发进度条显示延迟
				if (i == (bathchCount - 1)) {
					if (cct.getErrortbl() != null
							&& cct.getErrortbl().colSize() > 0) {
						errorFilePath = ImportUtil.exportExcel(
								cct.getErrortbl(), cct); // 导出失败的文件
					}
				}
				CustomerExportBar bar = customerExportBar.get(this
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
					bar.setCuurRow(beginSize); // 当前执行的行数
					k++;
				} else {
					bar.setCuurRow(0);
					break;
				}
				// 当前要导入的数据下标前移
				beginSize += batchSize;
				// 清空
				dt.clear();
				// 清空重复的数据
				reaptMap.clear();
				// 清空已经插入到数据库的数据
				addBeanList.clear();
				// 清空已经更新到数据库的数据
				updCusTargetList.clear();
			}
			long endTime = System.currentTimeMillis(); // 获取结束时间
			System.out.println("导入完成花费时间： " + (endTime - startTime) + "ms");
			counterOutPintMessageu.setErrorFilePath(errorFilePath);
			counterOutPintMessageu.setSumRecord(sumCount);
			counterOutPintMessageu.setSuccessRecordCount(successRecordCount);
			counterOutPintMessageu.setFailRecordCount(sumCount
					- successRecordCount - nullCount);
			addSysImportInfo(counterOutPintMessageu);
			return counterOutPintMessageu;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return null;
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
	 * 添加到数据库
	 * 
	 * @param addList
	 */
	private void addTskMicroTaskBatch(List<TskMicroTaskTarget> addList) {
		tskMicroTaskTargetDao.addTaskTargetBatch(addList);
	}

	/**
	 * 更新到数据库
	 * 
	 * @param updList
	 */
	private void updTskMicroTaskBatch(List<TskMicroTaskTarget> updList) {
		tskMicroTaskTargetDao.updateTaskTargetBatch(updList);
	}

	/**
	 * 将Excel中的数据放入List中
	 * 
	 * @param dt
	 * @param list
	 * @param map
	 * @param taskId
	 * @param userId
	 * @return
	 */
	private List<TskMicroTaskTarget> dataFromExcel(DataTable dt,
			TskMicroTaskTarget tskTarget) {
		List<TskMicroTaskTarget> list = new ArrayList<TskMicroTaskTarget>();
		int userId = getUserLoginInfo().getUserId();
		if (dt.colSize() > 0) {
			Map<String, TskMicroTaskTarget> map = new HashMap<String, TskMicroTaskTarget>();
			for (int i = 0; i < dt.rowSize(); i++) {
				DataRow dataRow = dt.getRow(i);
				TskMicroTaskTarget t = getDtData(dataRow, tskTarget, userId);
				if (t != null) {
					if (!map.containsKey(t.getPhoneNumber())) {
//						if (StringUtil.isEmpty(t.getCustomerName())) {
//							dataRow.set("errorMsg", "任务目标”" + t.getPhoneNumber() + "“已存在,不能重复添加！");
//						} else {
							String key = t.getCustomerName() + "@"
									+ t.getPhoneNumber() + "@";
							if (!map.containsKey(key)) {
								list.add(t);
								map.put(key, t);
							} else {
								// 报错
								dataRow.set("errorMsg", "任务目标”" + t.getCustomerName() + "“已存在,不能重复添加！");
							}
//						}
					} else {
						map.put(t.getPhoneNumber(), t);
						list.add(t);
					}
				}
			}
		}
		return list;
	}

	/**
	 * 获取Excel中的数据
	 * 
	 * @param dataRow
	 * @param t
	 * @param userId
	 * @return
	 */
	private TskMicroTaskTarget getDtData(DataRow dataRow, TskMicroTaskTarget t,
			int userId) {
		if (StringUtil
				.isEmpty(ImportUtil.checkStrNull(dataRow.get("errorMsg")))) {
			TskMicroTaskTarget tsk = new TskMicroTaskTarget();
			// 当客户姓名没有被选中时
			if (t.getCustomerName() != null) {
				tsk.setCustomerName((dataRow.get("crmName")) == null ? ""
						: ("" + dataRow.get("crmName")).trim());
			}
			// 电话号码是必选项
			tsk.setPhoneNumber(("" + dataRow.get("phoneNumber")).trim());
			// 当备注没有被选中时
			if (t.getRemark() != null) {
				tsk.setRemark(dataRow.get("remark") == null ? "" : ""
						+ dataRow.get("remark"));
			}
			// 将索引列传进去
			tsk.setAge(dataRow.getIndex());
			// 导入客户的用户
			tsk.setCreateUser(userId);
			// 任务ID
			tsk.setTaskId(t.getTaskId());
			// 用户ID
			tsk.setUserId(getUserLoginInfo().getUserId());
			tsk.setIsFinish(0);
			return tsk;
		}
		return null;
	}

	/**
	 * 获取号码与导入客户相同的在行客户 获取号码或姓名和号码相同的同一笔任务中的任务目标
	 * 
	 * @param list
	 * @param tskTarget
	 */
	private void getDataEqualsVonDBs(List<TskMicroTaskTarget> list,
			TskMicroTaskTarget tskTarget) {
		if (list != null && list.size() > 0) {
			Map<String, Object> parameterMap = new HashMap<String, Object>();
			if (list != null && list.size() > 0) {
				parameterMap.put("tskTargetList", list);
				// 通过list查询在行客户和任务目标客户
				// 当客户姓名被选中的时候（否则在行客户不需要查询）
				if (tskTarget.getCustomerName() != null) {
					crmList = crmCustomerDao.getCusByCusNamesAndPhones(parameterMap);
				}
				tskList = tskMicroTaskTargetDao.getTskMicTargetsByPhonesAndNames(parameterMap);
			}
		}
	}

	/**
	 * 验证Excel和数据库中的数据是否冲突
	 * 
	 * @param dt
	 * @param cct
	 * @param list
	 */
	private Map<Integer, TskMicroTaskTarget> validatorTaskTarget(DataTable dt,
			List<TskMicroTaskTarget> list) {
		if (list != null && list.size() > 0) {
			Map<Integer, TskMicroTaskTarget> map = new HashMap<Integer, TskMicroTaskTarget>();
			for (TskMicroTaskTarget tsk : list) {
				map.put(tsk.getAge(), tsk);
			}
			if (crmList != null && crmList.size() > 0) {
				checkCrmCustomerSame(dt, map);
			}
			if (tskList != null && tskList.size() > 0) {
				checkTaskTargetSame(dt, map);
			}
			return map;
		}
		return null;
	}

	/**
	 * 导入的任务目标和客户表的验证
	 * 
	 * @param temp
	 * @param dataRow
	 */
	private void checkCrmCustomerSame(DataTable dt,
			Map<Integer, TskMicroTaskTarget> map) {
		int currUserId = getUserLoginInfo().getUserId();
		for (CrmCustomer crm : crmList) {
			if (!crm.getBelongUserId().equals(currUserId)) {
				map.remove(crm.getIsShare());
				dt.getRow(crm.getIsShare()).set("errorMsg", "客户不是您的归属客户，不能添加为任务目标！");
			}else{
				isOldCustomer(map.get(crm.getIsShare()),crm.getCustomerId());
			}
		}
	}

	/**
	 * 导入的任务目标和任务表的验证
	 * 
	 * @param temp
	 * @param dataRow
	 */
	private void checkTaskTargetSame(DataTable dt,
			Map<Integer, TskMicroTaskTarget> map) {
		for (TskMicroTaskTarget tsk : tskList) {
			map.remove(tsk.getAge());
			dt.getRow(tsk.getAge()).set("errorMsg", "任务目标”" + tsk.getCustomerName() + "“已存在,不能重复添加！");
		}
	}

	/**
	 * 判断是否老客户(之前有贷款的客户)
	 * 
	 * @param t
	 */
	private void isOldCustomer(TskMicroTaskTarget t,int cusId) {
	    if(t!=null){
	        Integer isOldCustomer = lnLoanService.getLoanCountByCusId(cusId);
	        if (isOldCustomer != null && isOldCustomer > 0) {
	            t.setIsOldCustomer(1);
	        } else {
	            t.setIsOldCustomer(0);
	        }
	        
	        /** 2013-11-08 liyb add **/
	        t.setCustomerId(cusId);
	        t.setCustomerName(null);
	        t.setPhoneNumber(null);
	    }
	}

	/**
	 * 当前新增或是更新的是在行客户去除姓名和联系号码
	 * 
	 * @param temp
	 * @param crm
	 */
	private void isCrmCusInstoTarget(TskMicroTaskTarget temp, CrmCustomer crm) {
		if (crm != null) {
			temp.setCustomerName(null);
			// 当前导入的电话号码是在行客户的默认号码
			if (crm.getDefaultPhone().equals(temp.getPhoneNumber())) {
				temp.setPhoneNumber(null);
			}
		}
	}

	/**
	 * 初始化
	 * 
	 * @param cct
	 */
	public void initParameter(CrmCustomerTransfer cct) {
		Map<String, String> pMap = new HashMap<String, String>();
		cct.setParameterMap(pMap);
		cct.setErrortbl(new DataTable());
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
	 * 查询营销任务完成数量
	 * 
	 * @param parameterMap
	 * @return
	 */
	public Integer getTargetListByPageCount(Map<String, Object> parameterMap) {
		return tskMicroTaskTargetDao.getTargetListByPageCount(parameterMap);
	}

	public TskMicroTaskTarget getMicroTaskTargetById(Integer taskTargetId) {
		return tskMicroTaskTargetDao.getMicroTaskTargetById(taskTargetId);
	}

	public void editTskTaskTargetCustomer(TskMicroTaskTarget t) {
		tskMicroTaskTargetDao.editTskTaskTargetCustomer(t);
	}

	// 依赖注入
	public void setTskMicroTaskTargetDao(
			TskMicroTaskTargetDao tskMicroTaskTargetDao) {
		this.tskMicroTaskTargetDao = tskMicroTaskTargetDao;
	}

	public void setCrmCustomerDao(CrmCustomerDao crmCustomerDao) {
		this.crmCustomerDao = crmCustomerDao;
	}

	public void setSysImportService(SysImportService sysImportService) {
		this.sysImportService = sysImportService;
	}

	public void setLnLoanService(LnLoanService lnLoanService) {
		this.lnLoanService = lnLoanService;
	}

	/**
	 * 根据电话号码和任务ID查询
	 * 
	 * @param target
	 * @return
	 * @see com.banger.mobile.facade.microTask.TskMicroTaskTargetService#getMicroTaskTargetByPhoneAndTaskId(com.banger.mobile.domain.model.microTask.TskMicroTaskTarget)
	 */
	public Integer getMicroTaskTargetEqualsTarget(TskMicroTaskTarget target) {
		return tskMicroTaskTargetDao.getMicroTaskTargetEqualsTarget(target);
	}

	/**
	 * 修改执行任务时修改任务目标信息
	 * 
	 * @param t
	 */
	public void editTskTaskTargetInfo(TskMicroTaskTarget t) {
		tskMicroTaskTargetDao.editTskTaskTargetInfo(t);
	}

	/**
	 * 执行营销任务时修改任务目标信息
	 * 
	 * @param (MAP)params
	 */
	public void editTskTaskTargetInfo(Map<String, Object> params) {
		tskMicroTaskTargetDao.editTargetForExeMarkTask(params);
	}

	/**
	 * 通过电话号码，任务ID查询任务目标
	 */
	public List<TskMicroTaskTarget> getListByPhoneAndTaskId(
			Map<String, Object> params) {
		return tskMicroTaskTargetDao.getListByPhoneAndTaskId(params);
	}


    /**
     * 查询客户在此用户下的所有未完成任务安排
     * @param map
     * @return
     */
    public Integer getCustomerTaskCount(Map<String,Object> map){
        return tskMicroTaskTargetDao.getCustomerTaskCount(map);
    }
}