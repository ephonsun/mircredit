/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :任务执行明细-Service-接口实现
 * Author     :QianJie
 * Create Date:Mar 2, 2013
 */
package com.banger.mobile.facade.impl.task.microTask;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.banger.mobile.dao.microTask.TskMicroTaskExecuteDao;
import com.banger.mobile.domain.model.dept.SysDept;
import com.banger.mobile.domain.model.microTask.TskMicroTask;
import com.banger.mobile.domain.model.microTask.TskMicroTaskExecute;
import com.banger.mobile.domain.model.microTask.TskMicroTaskExecuteAssign;
import com.banger.mobile.domain.model.user.SysUser;
import com.banger.mobile.facade.dept.DeptFacadeService;
import com.banger.mobile.facade.microTask.TskMicroTaskExecuteService;
import com.banger.mobile.facade.user.SysUserService;
import com.banger.mobile.util.StringUtil;

/**
 * @author QianJie
 * @version $Id: TskMicroTaskExecuteServiceImpl.java,v 0.1 Mar 2, 2013 11:51:45
 *          AM QianJie Exp $
 */
public class TskMicroTaskExecuteServiceImpl implements
		TskMicroTaskExecuteService {

	private DeptFacadeService deptFacadeService;
	private TskMicroTaskExecuteDao tskMicroTaskExecuteDao;
	private SysUserService sysUserService;

	public void setDeptFacadeService(DeptFacadeService deptFacadeService)
	{
		this.deptFacadeService = deptFacadeService;
	}

	public void setTskMicroTaskExecuteDao(
			TskMicroTaskExecuteDao tskMicroTaskExecuteDao)
	{
		this.tskMicroTaskExecuteDao = tskMicroTaskExecuteDao;
	}

	public void setSysUserService(SysUserService sysUserService)
	{
		this.sysUserService = sysUserService;
	}

	/**
	 * 添加任务执行者
	 * 
	 * @param tskMicroTaskExecute
	 * @see com.banger.mobile.facade.microTask.TskMicroTaskExecuteService#addTskMicroTaskExecute(com.banger.mobile.domain.model.microTask.TskMicroTaskExecute)
	 */
	public void addTskMicroTaskExecute(TskMicroTaskExecute tskMicroTaskExecute)
	{
		tskMicroTaskExecuteDao.addTskMicroTaskExecute(tskMicroTaskExecute);
	}

	/**
	 * 批量添加任务执行者
	 * 
	 * @param list
	 * @see com.banger.mobile.facade.microTask.TskMicroTaskExecuteService#addTskMicroTaskExecuteBatch(java.util.List)
	 */
	public void addTskMicroTaskExecuteBatch(List<TskMicroTaskExecute> list)
	{
		tskMicroTaskExecuteDao.addTskMicroTaskExecuteBatch(list);
	}

	/**
	 * 删除任务执行者
	 * 
	 * @param conds
	 * @see com.banger.mobile.facade.microTask.TskMicroTaskExecuteService#delTskMicroTaskExecuteByConds(java.util.Map)
	 */
	public void delTskMicroTaskExecuteByConds(Map<String, Object> conds)
	{
		tskMicroTaskExecuteDao.delTskMicroTaskExecuteByConds(conds);
	}

	/**
	 * 编辑任务执行者
	 * 
	 * @param tskMicroTaskExecute
	 * @see com.banger.mobile.facade.microTask.TskMicroTaskExecuteService#editTskMicroTaskExecute(com.banger.mobile.domain.model.microTask.TskMicroTaskExecute)
	 */
	public void editTskMicroTaskExecute(TskMicroTaskExecute tskMicroTaskExecute)
	{
		tskMicroTaskExecuteDao.editTskMicroTaskExecute(tskMicroTaskExecute);
	}

	/**
	 * @param list
	 * @see com.banger.mobile.facade.microTask.TskMicroTaskExecuteService#editTskMicroTaskExecuteBatch(java.util.List)
	 */
	public void editTskMicroTaskExecuteBatch(List<TskMicroTaskExecute> list)
	{
		tskMicroTaskExecuteDao.editTskMicroTaskExecute(list);
	}

	/**
	 * 查询所有任务执行者数据
	 * 
	 * @param conds
	 * @return
	 * @see com.banger.mobile.facade.microTask.TskMicroTaskExecuteService#getAllTskMicroTaskExecuteByConds(java.util.Map)
	 */
	public List<TskMicroTaskExecute> getAllTskMicroTaskExecuteByConds(
			Map<String, Object> conds)
	{
		return tskMicroTaskExecuteDao.getAllTskMicroTaskExecuteByConds(conds);
	}

	/**
	 * 查询所有任务执行者数据
	 * 
	 * @param conds
	 * @return
	 * @see com.banger.mobile.facade.microTask.TskMicroTaskExecuteService#getAllTskMicroTaskExecuteByConds(java.util.Map)
	 */
	public List<TskMicroTaskExecute> getAllTskMicroTaskExecuteByCondsProgress(
			Map<String, Object> conds)
	{
		return tskMicroTaskExecuteDao
				.getAllTskMicroTaskExecuteByCondsProgress(conds);
	}

	/**
	 * 查询我已经分配出去的任务目标
	 * 
	 * @param conds
	 * @return
	 * @see com.banger.mobile.facade.microTask.TskMicroTaskExecuteService#getMyTargetByConds(java.util.Map)
	 */
	public Integer getMyTargetByConds(Map<String, Object> conds)
	{
		return tskMicroTaskExecuteDao.getMyTargetByConds(conds);
	}

	private String getInChargeOfDeptIds(String userid)
	{
		String deptIds = "";
		Integer[] arr = deptFacadeService.getInChargeOfDeptIds(Integer
				.parseInt(userid));
		if (arr != null)
		{
			for (int i = 0; i < arr.length; i++)
			{
				if (deptIds.equals(""))
					deptIds = arr[i].toString();
				else
					deptIds = deptIds + "," + arr[i];
			}
		}
		return deptIds;
	}

	/**
	 * 查询所有任务执行者已完成任务笔数
	 * 
	 * @param tskMicroTask
	 *            任务对象
	 * @param userId
	 *            当前登录用户ID
	 * @return
	 * @see com.banger.mobile.facade.microTask.TskMicroTaskExecuteService#
	 */
	public List<TskMicroTaskExecuteAssign> getAllTskMicroTaskExecuteAssignByConds(
			TskMicroTask tskMicroTask, Integer userId)
	{
		// 任务分配Obj
		List<TskMicroTaskExecuteAssign> tskAssignList = new ArrayList<TskMicroTaskExecuteAssign>();

		Integer totalTarget = 0;

		// 已分配执行者任务数据
		Map<String, Object> conds = new HashMap<String, Object>();
		conds.put("taskId", tskMicroTask.getTaskId());
		// 该任务关联的执行者数据
		List<TskMicroTaskExecute> execAssignList = tskMicroTaskExecuteDao
				.getAllTskMicroTaskExecuteByConds(conds);

		conds.put("userId", userId);

		
		// 当前登录用户是否是创建者
		if (tskMicroTask.getAssignUserId().intValue() == userId.intValue())
		{
			totalTarget = tskMicroTask.getTaskTarget();
		} else
		{
			// 客户经理
			conds.put("userId", userId);
			conds.put("role", "brMine");
			totalTarget = tskMicroTaskExecuteDao.getMyTargetByConds(conds);
			if (totalTarget == null)
			{
				totalTarget = 0;
			}
		}
		// 组织数据,并根据执行者记录表修改数据的真实分配值和是否有权限编辑
		getAllTskMicroTaskAssignByManager(tskAssignList, execAssignList,
				tskMicroTask, userId, totalTarget);
		// ok
		/*
		if (deptFacadeService.isInChargeOfDepartment())
		{
			// 根据userID获取当前客户所管理的部门id集合并转化为字符串
			String InChargeOfDeptIds = getInChargeOfDeptIds(userId.toString());
			conds.put("InChargeOfDeptIds", InChargeOfDeptIds);
			// 我负责的根机构
			List<Integer> roots = deptFacadeService
					.getRootDeptIdByLoginId(userId);
			SysUser u = sysUserService.getSysUserById(userId);
			boolean bool = true;
			// 判断当前用户所在的部门是否我负责的根机构的下属部门
			for (int i = 0; i < roots.size(); i++)
			{
				if (u.getDeptId() >= roots.get(i))
					bool = false;
			}
			if (bool)
			{
				// 如果当前登录的客户所在的部门是我负责的部门的上级机构将我所在的部门添加到机构树中
				roots.add(u.getDeptId());
			}
			// 当前登录用户是否是创建者
			totalTarget = tskMicroTask.getTaskTarget();
			// if (tskMicroTask.getAssignUserId().intValue() ==
			// userId.intValue()) {
			// totalTarget = tskMicroTask.getTaskTarget();
			// } else {
			// conds.put("role", "brDept");
			// totalTarget = tskMicroTaskExecuteDao.getMyTargetByConds(conds);
			// if(totalTarget == null){
			// totalTarget = 0;
			// }
			// }

			// 初始化任务分配对象
			initTskAssignList(tskAssignList, totalTarget);

			// 根据创建者Id来取得可以编辑分配的列Id
			String editRowMark = getEditRowMarkByAssignUserId(tskMicroTask
					.getAssignUserId());// 可以编辑的IDRow

			// 判断当前登录人员是否有该任务创建者所在机构的管理权限
			int creTskDeptId = sysUserService.getSysUserById(
					tskMicroTask.getAssignUserId()).getDeptId();
			boolean isSuperiors = false;
			// 如果当前登录人员不是该任务的创建人员
			if (bool)
			{
				String[] ss = InChargeOfDeptIds.split(",");
				for (int i = 0; i < ss.length; i++)
				{
					if (ss[i].equals(String.valueOf(creTskDeptId)))
					{
						break;
					} else if (i == ss.length - 1)
					{
						isSuperiors = true;
					}
				}
			}
			// 组织数据
			getAllTskMicroTaskAssignByCharge(tskAssignList, tskMicroTask,
					userId, roots, isSuperiors);

			// 根据执行者记录表修改数据的真实分配值和是否有权限编辑
			String dids=getInChangeDeptUserIds(userId);
			for (int i = 1; i < tskAssignList.size(); i++)
			{
				bulidTskMicroTaskAssignData(tskAssignList.get(i),
						execAssignList, tskMicroTask, userId, totalTarget);
				//非创建者登录，是否可编辑
				if (tskMicroTask.getAssignUserId().intValue() != userId.intValue()){
					
				    //if (isMyRootInChargeDept(tskAssignList.get(i).getId(),roots)){
					//	tskAssignList.get(i).setTMode(1);
					//}else{
					//	if (isContainsRoot(editRowMark, roots)){
					//		// break;
					//	}else{
					//		tskAssignList.get(i).setTMode(1);
					//	}
					//}
					
				    //2013-12-12 add liyb 
					Integer mode=StringUtil.returnMode(dids, tskAssignList.get(i).getId().toString());
					tskAssignList.get(i).setTMode(mode);
				}
			}

			// 处理上级主管查看下级的时候 需要显示下级的数据 递归累加
			setDataByInCharge(tskAssignList);
			// 更新主记录（合计）
			String ds[] = InChargeOfDeptIds.split(",");
			for (Integer rootId : roots)
			{
				Integer assignTarget = 0;
				Integer comTskNum = 0;
				for (int i = 1; i < tskAssignList.size(); i++)
				{
					if (rootId.intValue() == tskAssignList.get(i).getId()
							.intValue())
					{
						assignTarget += tskAssignList.get(i)
								.getAssignTotalTarget();
//						comTskNum += tskAssignList.get(i).getComTskNum();
					}
					
					
					 
					//2013-12-24 liyb add 组合任务总计的总数
					
					//start
					
					//for (int j = 0; j < ds.length; j++) {
					//    Integer rId = Integer.parseInt(ds[j].toString());
					//   if(rId.intValue() == tskAssignList.get(i).getId().intValue()){
					//        comTskNum += tskAssignList.get(i).getComTskNum();
					//    }
                    //}
                    
					//end
				}
				tskAssignList.get(0).setAssignTarget(assignTarget);
				tskAssignList.get(0).setUnAssignTarget(
						totalTarget.intValue() - assignTarget.intValue());
				tskAssignList.get(0).setComTskNum(comTskNum);
				if (totalTarget.intValue() > 0)
				{// 已分配任务目标
					tskAssignList
							.get(0)
							.setAssignPc(
									String.format("%.2f",
											(assignTarget / Double
													.parseDouble(totalTarget
															.toString())) * 100)
											+ "%");
					tskAssignList
							.get(0)
							.setComPc(
									String.format("%.2f",
											(tskAssignList.get(0)
													.getComTskNum() / Double
													.parseDouble(totalTarget
															.toString())) * 100)
											+ "%");
				}
			}
			// ok
			for (int i = 1; i < tskAssignList.size(); i++)
			{
				int comTskSum = 0;
				for (int j = 1; j < tskAssignList.size(); j++)
				{
					if (tskAssignList.get(i).getId()
							.equals(tskAssignList.get(j).getEpid()))
						comTskSum += tskAssignList.get(j).getComTskNum();
				}
				if (tskAssignList.get(i).getEpid().equals(2))
					tskAssignList.get(i).setComTskNum(comTskSum);
			}
			
			  //2013-12-24 liyb delete 删除最后重新组合的总计任务完成数
			 
//			tskAssignList.get(0).setComTskNum(tskAssignList.get(1).getComTskNum());

		} else
		{
			// 当前登录用户是否是创建者
			if (tskMicroTask.getAssignUserId().intValue() == userId.intValue())
			{
				totalTarget = tskMicroTask.getTaskTarget();
			} else
			{
				// 客户经理
				conds.put("userId", userId);
				conds.put("role", "brMine");
				totalTarget = tskMicroTaskExecuteDao.getMyTargetByConds(conds);
				if (totalTarget == null)
				{
					totalTarget = 0;
				}
			}
			// 组织数据,并根据执行者记录表修改数据的真实分配值和是否有权限编辑
			getAllTskMicroTaskAssignByManager(tskAssignList, execAssignList,
					tskMicroTask, userId, totalTarget);
			// ok
		}
		*/
		return tskAssignList;
	}
	
	/**
     * 返回登录用户所管辖的机构和用户ID集合字符串
     * @return
     */
    public String getInChangeDeptUserIds(Integer userId){
        String deptUserIds="";
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
            userIds = userId.toString()+":U";
        }
        deptUserIds=deptIds+","+userIds;
        return deptUserIds;
    }

	private void setDataByInCharge(List<TskMicroTaskExecuteAssign> tskAssignList)
	{
		int tTarget = 0;
		for (TskMicroTaskExecuteAssign task : tskAssignList)
		{
			if (task.getAssignTotalTarget().equals(-1))
			{
				tTarget = getTotalTarget(task, tskAssignList);
				task.setAssignTotalTarget(tTarget);
			}
		}
	}

	// 递归计算下级所有任务目标总数
	private int getTotalTarget(TskMicroTaskExecuteAssign task,
			List<TskMicroTaskExecuteAssign> tskAssignList)
	{
		int tTarget = 0;
		for (TskMicroTaskExecuteAssign tt : tskAssignList)
		{
			if (task.getId().equals(tt.getPid()))
			{
				if (tt.getAssignTotalTarget().equals(-1))
				{
					tTarget = tTarget + getTotalTarget(tt, tskAssignList);
				} else
				{
					tTarget = tTarget + tt.getAssignTotalTarget();
				}
			}
		}
		return tTarget;
	}

	/**
	 * 初始化任务分配对象
	 * 
	 * @param tskAssignList
	 * @param totalTarget
	 */
	private void initTskAssignList(
			List<TskMicroTaskExecuteAssign> tskAssignList, Integer totalTarget)
	{
		TskMicroTaskExecuteAssign tskAssign = new TskMicroTaskExecuteAssign();// 任务分配对象
		tskAssign.setId(-1);
		// tskAssign.setPid(0);
		tskAssign.setAssignObjName("合计");
		tskAssign.setAssignPc("0.00%");
		tskAssign.setAssignTarget(0);
		tskAssign.setAssignTotalTarget(totalTarget);
		tskAssign.setComPc("0.00%");
		tskAssign.setComTskNum(0);
		tskAssign.setTMode(1);
		tskAssign.setUnAssignTarget(totalTarget);
		tskAssignList.add(tskAssign);
	}

	/**
	 * 可以编辑的IDRow Str
	 * 
	 * @param assignUserId
	 * @return
	 */
	private String getEditRowMarkByAssignUserId(Integer assignUserId)
	{
		String editRowMark = "";// 可以编辑的IDRow
		List<Integer> assignRoots = deptFacadeService
				.getRootDeptIdByLoginId(assignUserId);
		Integer[] assignDeptList = deptFacadeService
				.getInChargeOfDeptIds(assignUserId);
		if (assignDeptList != null)
		{
			for (Integer editRowId : assignDeptList)
			{// 过滤根节点
				if (!isAssignRootInChargeDept(editRowId, assignRoots))
				{
					editRowMark += "[" + editRowId.toString() + "]";
				}
			}
		}

		Integer[] assignUserList = deptFacadeService
				.getInChargeOfDeptUserIds(assignUserId);
		if (assignUserList != null)
		{
			for (Integer editRowId2 : assignUserList)
			{
				editRowMark += "[" + editRowId2.toString() + "]";
			}
		}
		return editRowMark;
	}

	/**
	 * 业务主管组合机构和人员机构树
	 * 
	 * @param tskAssignList
	 * @param tskMicroTask
	 * @param roots
	 *            负责的机构
	 * @param userId
	 */
	private void getAllTskMicroTaskAssignByCharge(
			List<TskMicroTaskExecuteAssign> tskAssignList,
			TskMicroTask tskMicroTask, Integer userId, List<Integer> roots,
			boolean isSuperiors)
	{
		List<SysDept> inChargeDepts = deptFacadeService
				.getBusinessManagerInCharegeDeptTreeList(userId);
		List<SysUser> inChargeUserIds = deptFacadeService
				.getBusinessManagerInCharegeOfUsers();
		Integer rootDeptId = null;
		// 当前登入者是该任务的创建者的上级机构的业务主管
		if (isSuperiors)
		{
			SysUser sysUser = sysUserService.getSysUserById(userId);
			SysDept sysDept = deptFacadeService.getDeptsByDeptIds(
					String.valueOf(sysUser.getDeptId())).get(0);
			// 增加当前登入人员所在的机构
			inChargeDepts.add(0, sysDept);
			rootDeptId = sysDept.getDeptId();
			// 增加当前登入人员
			inChargeUserIds.add(sysUser);
		}

		for (SysDept sysDept : inChargeDepts)
		{
			TskMicroTaskExecuteAssign tskAssign = new TskMicroTaskExecuteAssign();
			tskAssign.setId(sysDept.getDeptId());
			if (rootDeptId != null)
			{
				if (sysDept.getDeptId() == rootDeptId)
				{
					tskAssign.setPid(0);
				} else
				{
					tskAssign.setPid(sysDept.getDeptParentId());
				}
				tskAssign.setEpid(sysDept.getDeptParentId());
				tskAssign.setTMode(2);
				tskAssign.setAssignObjName(sysDept.getDeptName());
				tskAssign.setUnAssignTarget(0);
				tskAssign.setAssignTarget(0);
				tskAssign.setAssignTotalTarget(0);
				tskAssign.setComPc("0.00%");
				tskAssign.setAssignPc("0.00%");
				tskAssign.setComTskNum(0);
				tskAssignList.add(tskAssign);
			} else
			{
				if (isMyRootInChargeDept(sysDept.getDeptId(), roots))
				{ // 替换根机构的父级节点ID
					tskAssign.setPid(0);
				} else
				{
					tskAssign.setPid(sysDept.getDeptParentId());
				}
				tskAssign.setEpid(sysDept.getDeptParentId());
				tskAssign.setTMode(2);
				tskAssign.setAssignObjName(sysDept.getDeptName());
				tskAssign.setUnAssignTarget(0);
				tskAssign.setAssignTarget(0);
				tskAssign.setAssignTotalTarget(0);
				tskAssign.setComPc("0.00%");
				tskAssign.setAssignPc("0.00%");
				tskAssign.setComTskNum(0);
				tskAssignList.add(tskAssign);
			}

		}

		for (SysUser sysUser : inChargeUserIds)
		{
			TskMicroTaskExecuteAssign tskAssign = new TskMicroTaskExecuteAssign();
			tskAssign.setId(sysUser.getUserId());
			tskAssign.setPid(sysUser.getDeptId());
			tskAssign.setEpid(sysUser.getDeptId());
			tskAssign.setTMode(3);
			tskAssign.setAssignObjName(sysUser.getUserName());
			// tskAssign.setUnAssignTarget(0);
			// tskAssign.setAssignTarget(0);
			tskAssign.setAssignTotalTarget(0);
			tskAssign.setComPc("0.00%");
			tskAssign.setAssignPc("0.00%");
			tskAssign.setComTskNum(0);
			tskAssignList.add(tskAssign);
		}
	}

	/**
	 * 根据执行者记录表修改数据的真实分配值和是否有权限编辑
	 * 
	 * @param tskAssign
	 * @param execAssignList
	 * @param tskMicroTask
	 * @param userId
	 * @param totalTarget
	 */
	private void bulidTskMicroTaskAssignData(
			TskMicroTaskExecuteAssign tskAssign,
			List<TskMicroTaskExecute> execAssignList,
			TskMicroTask tskMicroTask, Integer userId, Integer totalTarget)
	{
		TskMicroTaskExecute tskMicroTaskExecute = null;
		if (tskAssign.getTMode() == 2)
		{
			tskMicroTaskExecute = getTskMicroTaskExecuteByConds(
					tskAssign.getId(), 0, execAssignList);
			if (tskMicroTaskExecute != null)
			{// 已分配到该执行对象
				tskAssign.setAssignTarget(tskMicroTaskExecute.getTargetDept()
						.intValue()
						- tskMicroTaskExecute.getTargetDeptUnassign()
								.intValue());
				tskAssign.setUnAssignTarget(tskMicroTaskExecute
						.getTargetDeptUnassign());
				tskAssign.setAssignTotalTarget(tskMicroTaskExecute
						.getTargetDept());

				Map<String, Object> conds = new HashMap<String, Object>();
				conds.put("inChargeDeptUserMark",
						getInChargeUserIdsMark(tskAssign.getId()));
				conds.put("taskId", tskMicroTask.getTaskId());
//				conds.put("deptId", tskAssign.getEpid());
				conds.put("deptId", tskAssign.getId());
				conds.put("userId", 0);
				Integer comTskNum = tskMicroTaskExecuteDao
						.getComTskNumByConds(conds);
				if (comTskNum == null)
				{
					comTskNum = 0;
				}
				tskAssign.setComTskNum(comTskNum);
				tskAssign.setAssignPc(String.format("%.2f", (tskAssign
						.getAssignTarget() / Double.parseDouble(totalTarget
						.toString())) * 100)
						+ "%");
				tskAssign.setComPc(String.format("%.2f", (tskAssign
						.getComTskNum() / Double.parseDouble(totalTarget
						.toString())) * 100)
						+ "%");
			} else
			{
				tskAssign.setAssignTotalTarget(-1);
				tskAssign.setComTskNum(0);
			}

		} else
		{
			tskMicroTaskExecute = getTskMicroTaskExecuteByConds(
					tskAssign.getEpid(), tskAssign.getId(), execAssignList);
			if (tskMicroTaskExecute != null)
			{// 已分配到该执行对象
				Map<String, Object> conds = new HashMap<String, Object>();
				conds.put("inChargeDeptUserMark", "[" + tskAssign.getId() + "]");
				conds.put("taskId", tskMicroTask.getTaskId());
				conds.put("deptId", tskAssign.getEpid());
				conds.put("userId", tskAssign.getId());
				Integer comTskNum = tskMicroTaskExecuteDao
						.getComTskNumByConds(conds);
				if (comTskNum == null)
				{
					comTskNum = 0;
				}

				tskAssign.setComTskNum(comTskNum);
				tskAssign.setAssignTotalTarget(tskMicroTaskExecute
						.getTargetUser());
				if (totalTarget.intValue() > 0)
				{// 已分配任务目标
					tskAssign.setAssignPc(String.format("%.2f", (tskAssign
							.getAssignTotalTarget() / Double
							.parseDouble(totalTarget.toString())) * 100)
							+ "%");
					tskAssign.setComPc(String.format("%.2f", (tskAssign
							.getComTskNum() / Double.parseDouble(totalTarget
							.toString())) * 100)
							+ "%");
				}
			}
		}

	}

	/**
	 * 当前登录用户是否有编辑权限
	 * 
	 * @param editRowMark
	 * @param roots
	 *            登录用户所负责的根机构
	 * @return
	 */
	private boolean isContainsRoot(String editRowMark, List<Integer> roots)
	{
		boolean isContains = false;
		for (Integer rootId : roots)
		{
			if (editRowMark.contains("[" + rootId.toString() + "]"))
			{
				isContains = true;
			}
		}
		return isContains;
	}

	/**
	 * 获取下属人员mark
	 * 
	 * @param deptId
	 * @return
	 */
	private String getInChargeUserIdsMark(Integer deptId)
	{
		if (!StringUtil.isNullOrEmpty(deptFacadeService
				.getStringUserIdContainsByDeptIds(deptId.toString())))
		{
			return "["
					+ deptFacadeService.getStringUserIdContainsByDeptIds(
							deptId.toString()).replace(",", "],[") + "]";
		} else
		{
			return "[]";
		}
	}

	/**
	 * 判断当前机构是否是我负责的根机构
	 * 
	 * @param deptId
	 * @param roots
	 * @return
	 */
	private boolean isMyRootInChargeDept(Integer deptId, List<Integer> roots)
	{
		for (Integer rootId : roots)
		{
			if (rootId.intValue() == deptId.intValue())
			{
				return true;
			}
		}
		return false;
	}

	/**
	 * 是否是分配者负责的根机构
	 * 
	 * @param deptId
	 * @param assignRoots
	 * @return
	 */
	private boolean isAssignRootInChargeDept(Integer deptId,
			List<Integer> assignRoots)
	{
		for (Integer rootId : assignRoots)
		{
			if (rootId.intValue() == deptId.intValue())
			{
				return true;
			}
		}
		return false;
	}

	/**
	 * 客户经理组合人员树
	 * 
	 * @param tskAssignList
	 * @param tskMicroTask
	 * @param userId
	 */
	private void getAllTskMicroTaskAssignByManager(
			List<TskMicroTaskExecuteAssign> tskAssignList,
			List<TskMicroTaskExecute> execAssignList,
			TskMicroTask tskMicroTask, Integer userId, Integer totalTarget)
	{
		TskMicroTaskExecuteAssign tskAssign = new TskMicroTaskExecuteAssign();// 任务分配对象
		tskAssign.setId(-1);
		// tskAssign.setPid(0);
		tskAssign.setAssignObjName("合计");
		tskAssign.setAssignPc("0.00%");
		tskAssign.setAssignTarget(0);
		tskAssign.setAssignTotalTarget(totalTarget);
		tskAssign.setComPc("0.00%");
		tskAssign.setComTskNum(0);
		tskAssign.setTMode(1);
		tskAssign.setUnAssignTarget(totalTarget);

		TskMicroTaskExecuteAssign tskAssignM = new TskMicroTaskExecuteAssign();
		tskAssignM.setId(0);
		tskAssignM.setPid(0);
		tskAssignM.setEpid(0);
		tskAssignM.setTMode(3);
		tskAssignM.setAssignObjName("");
		// tskAssign.setUnAssignTarget(0);
		// tskAssign.setAssignTarget(0);
		tskAssignM.setAssignTotalTarget(0);
		tskAssignM.setComPc("0.00%");
		tskAssignM.setAssignPc("0.00%");
		tskAssignM.setComTskNum(0);

		SysUser sysUser = null;
		if (tskMicroTask.getAssignUserId().intValue() == userId.intValue())
		{
			sysUser = sysUserService.getSysUserById(tskMicroTask
					.getAssignUserId());
			// 任务创建者
			tskAssign.setAssignTotalTarget(totalTarget);
			tskAssignM.setAssignTotalTarget(0);
			tskAssignM.setTMode(3);// 可编辑
			tskAssignM.setPid(0);

		} else
		{
			sysUser = sysUserService.getSysUserById(userId);
			// 任务执行者
			tskAssign.setAssignTotalTarget(totalTarget);
			tskAssignM.setAssignTotalTarget(totalTarget);
			tskAssignM.setTMode(1);// 不可编辑
			tskAssignM.setPid(sysUser.getDeptId());

		}
		tskAssignM.setId(sysUser.getUserId());
		tskAssignM.setEpid(sysUser.getDeptId());
		tskAssignM.setAssignObjName(sysUser.getUserName());
		tskAssignM.setComPc("0.00%");
		tskAssignM.setAssignPc("0.00%");
		tskAssignM.setComTskNum(0);

		TskMicroTaskExecute tskMicroTaskExecute = getTskMicroTaskExecuteByConds(
				sysUser.getDeptId(), sysUser.getUserId(), execAssignList);
		if (tskMicroTaskExecute != null)
		{
			Map<String, Object> conds = new HashMap<String, Object>();
			conds.put("inChargeDeptUserMark", "[" + sysUser.getUserId() + "]");
			conds.put("taskId", tskMicroTask.getTaskId());
			conds.put("deptId", sysUser.getDeptId());
			conds.put("userId", sysUser.getUserId());
			Integer comTskNum = tskMicroTaskExecuteDao
					.getComTskNumByConds(conds);
			if (comTskNum == null)
			{
				comTskNum = 0;
			}
			tskAssignM.setComTskNum(comTskNum);
			tskAssignM
					.setAssignTotalTarget(tskMicroTaskExecute.getTargetUser());
			if (totalTarget.intValue() > 0)
			{// 已分配任务目标
				Integer assignTarget = tskAssignM.getAssignTarget();
				if (assignTarget == null)
				{
					assignTarget = 0;
				}
				tskAssignM.setAssignPc(String.format("%.2f",
						(assignTarget / Double.parseDouble(totalTarget
								.toString())) * 100)
						+ "%");
				tskAssignM.setComPc(String.format("%.2f", (tskAssignM
						.getComTskNum() / Double.parseDouble(totalTarget
						.toString())) * 100)
						+ "%");
			}
			tskAssign.setComTskNum(comTskNum);
			tskAssign.setUnAssignTarget(totalTarget.intValue()
					- tskMicroTaskExecute.getTargetUser().intValue());
			tskAssign.setAssignTarget(tskMicroTaskExecute.getTargetUser());
			if (totalTarget.intValue() > 0)
			{// 已分配任务目标
				tskAssign.setAssignPc(String.format("%.2f", (tskAssign
						.getAssignTarget() / Double.parseDouble(totalTarget
						.toString())) * 100)
						+ "%");
				tskAssign.setComPc(String.format("%.2f", (tskAssign
						.getComTskNum() / Double.parseDouble(totalTarget
						.toString())) * 100)
						+ "%");
			}
		}
		tskAssignList.add(tskAssign);
		tskAssignList.add(tskAssignM);
	}

	/**
	 * 获取执行对象
	 * 
	 * @param deptId
	 * @param userId
	 * @param execAssignList
	 * @return
	 */
	private TskMicroTaskExecute getTskMicroTaskExecuteByConds(Integer deptId,
			Integer userId, List<TskMicroTaskExecute> execAssignList)
	{
		for (TskMicroTaskExecute tskMicroTaskExecute : execAssignList)
		{
			if ((tskMicroTaskExecute.getDeptId().intValue() == deptId
					.intValue())
					&& (tskMicroTaskExecute.getUserId().intValue() == userId
							.intValue()))
			{
				return tskMicroTaskExecute;
			}
		}
		return null;
	}

	/**
	 * 查询机构下分配任务的人员
	 * 
	 * @param conds
	 * @return
	 */
	public List<TskMicroTaskExecute> getSysUsersByTask(Map<String, Object> conds)
	{
		return tskMicroTaskExecuteDao.getSysUsersByTask(conds);
	}

	/**
	 * 查询任务执行者已完成任务笔数
	 * 
	 * @param conds
	 * @return
	 * @see com.banger.mobile.facade.microTask.TskMicroTaskExecuteService#getComTskNumByConds(java.util.Map)
	 */
	public Integer getComTskNumByConds(Map<String, Object> conds)
	{
		return tskMicroTaskExecuteDao.getComTskNumByConds(conds);
	}

	/**
	 * @param paras
	 * @return
	 * @see com.banger.mobile.facade.microTask.TskMicroTaskExecuteService#getTaskUserExecutable(java.util.Map)
	 */
	public List<TskMicroTaskExecute> getTaskUserExecutable(
			Map<String, Object> paras)
	{
		return tskMicroTaskExecuteDao.getTaskUserExecutable(paras);
	}

	/**
     * 
     */
	public List<Integer> getAllocateTaskByUsers(Map<String, Object> map)
	{
		// TODO Auto-generated method stub
		return tskMicroTaskExecuteDao.getIsAllocateTaskByUserIds(map);
	}
}
