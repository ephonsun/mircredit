package com.banger.mobile.facade.impl.task.microTask;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.model.microTask.TskMicroTask;
import com.banger.mobile.facade.dept.DeptFacadeService;
import com.banger.mobile.facade.microTask.TskMicroTaskService;
import com.banger.mobile.facade.microTask.TskMicroTaskWorkbenchService;
import com.banger.mobile.facade.specialDataAuth.SpecialDataAuthService;
import com.banger.mobile.facade.user.SysUserService;
import com.banger.mobile.util.DateUtil;
import com.banger.mobile.util.StringUtil;
import org.apache.log4j.Logger;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * 工作台任务提醒service User: yuanme Date: 13-4-11 Time: 下午2:44
 */
public class TskMicroTaskWorkbenchServiceImpl implements TskMicroTaskWorkbenchService {
    private static final Logger logger = Logger.getLogger(TskMicroTaskWorkbenchServiceImpl.class);
    private DeptFacadeService   deptFacadeService;
    private TskMicroTaskService tskMicroTaskService;
    private SpecialDataAuthService specialDataAuthService;  //特殊数据权限
    private SysUserService sysUserService;

    public void setSysUserService(SysUserService sysUserService) {
        this.sysUserService = sysUserService;
    }

    public void setSpecialDataAuthService(SpecialDataAuthService specialDataAuthService) {
        this.specialDataAuthService = specialDataAuthService;
    }

    // 今天的任务列表
    public PageUtil<TskMicroTask> getTodayTaskListPage(Integer loginUserId, Page page) {
        try {
            String inChargeUserIds = "", inChargeUserIdsMark = "", inChargeDepts = "";

            Map<String, Object> conds = new HashMap<String, Object>();

            // 判断是否是业务主管
            if (deptFacadeService.isInChargeOfDepartment()) {
                String roleIds= sysUserService.getRoleIds(loginUserId);
                boolean flag=specialDataAuthService.getSysDataAuthCondition(roleIds,"taskInfo");
                if(flag){
                    inChargeDepts="-1";
                }else{
                    Integer[] inChargeDeptsIntegers = deptFacadeService.getInChargeOfDeptIds();
                    if(null!=inChargeDeptsIntegers){
                        for (Integer deptId : inChargeDeptsIntegers) {
                            inChargeDepts += deptId.intValue() + ","; // 负责的机构
                        }
                    }

                    if (inChargeDepts.length() > 0) {
                        inChargeDepts = inChargeDepts.substring(0,
                                (inChargeDepts.length() - 1));
                    }
                }

                String inChargeUser = deptFacadeService.getUserIdsBelongToManager(roleIds.split(","), "taskInfo");
                String[] array=  inChargeUser.split(",");
                inChargeUserIds=inChargeUser;
                for (String userId : array) {
                    inChargeUserIdsMark += "[" + userId + "],";
                }
                if (inChargeUserIds.length() > 0) {
                    inChargeUserIdsMark = inChargeUserIdsMark.substring(0,
                            (inChargeUserIdsMark.length() - 1));
                }

                conds.put("userId", loginUserId);
                conds.put("inChargeUserIds", inChargeUserIds);
                conds.put("inChargeUserIdsMark", inChargeUserIdsMark);
                conds.put("inChargeDepts", inChargeDepts);
                conds.put("taskStatus", -1);// 查询未完成和已完成
                conds.put("userType", 2);
            } else {
                // 客户经理 显示我执行的任务
                Integer isInChargeOf = 0;
                inChargeUserIds = String.valueOf(loginUserId);
                inChargeDepts = "0";

                String userIdMark = "[" + loginUserId + "]";
                conds.put("userId", loginUserId);
                conds.put("userIdMark", userIdMark);
                conds.put("taskStatus", -1);// 查询未完成和已完成
                conds.put("inChargeUserIds", inChargeUserIds);
                conds.put("inChargeUserIdsMark", userIdMark);
                conds.put("inChargeDepts", inChargeDepts);
                conds.put("isInChargeOf", isInChargeOf);
                conds.put("userType", 3);
            }

            String todayS = DateUtil.getDateToString(Calendar.getInstance().getTime());
            conds.put("startDate",
                DateUtil.convertStringToDate("yyyy-MM-dd HH:mm:ss", todayS + " 00:00:00"));
            conds.put("endDate",
                DateUtil.convertStringToDate("yyyy-MM-dd HH:mm:ss", todayS + " 23:59:59"));
            conds.put("isOutDate", 0);
            page.setPageSize(10);
            PageUtil<TskMicroTask> dataList = tskMicroTaskService.getTaskListPage(conds, page);
            return dataList;
        } catch (Exception e) {
            logger.error("getTodayTaskListPage", e);
            return null;
        }
    }

    // 已过期的任务列表
    public PageUtil<TskMicroTask> getOutDateTaskListPage(Integer loginUserId,Page page){
        try {
            String inChargeUserIds = "", inChargeUserIdsMark = "", inChargeDepts = "";

            Map<String, Object> conds = new HashMap<String, Object>();

            // 判断是否是业务主管
            if (deptFacadeService.isInChargeOfDepartment()) {
                String roleIds= sysUserService.getRoleIds(loginUserId);
                boolean flag=specialDataAuthService.getSysDataAuthCondition(roleIds,"taskInfo");
                if(flag){
                    inChargeDepts="-1";
                }else{
                    Integer[] inChargeDeptsIntegers = deptFacadeService.getInChargeOfDeptIds();
                    if(null!=inChargeDeptsIntegers){
                        for (Integer deptId : inChargeDeptsIntegers) {
                            inChargeDepts += deptId.intValue() + ","; // 负责的机构
                        }
                    }
                    if (inChargeDepts.length() > 0) {
                        inChargeDepts = inChargeDepts.substring(0,
                                (inChargeDepts.length() - 1));
                    }
                }

                String inChargeUser = deptFacadeService.getUserIdsBelongToManager(roleIds.split(","), "taskInfo");
                String[] array=  inChargeUser.split(",");
                inChargeUserIds=inChargeUser;
                for (String userId : array) {
                    inChargeUserIdsMark += "[" + userId + "],";
                }
                if (inChargeUserIds.length() > 0) {
                    inChargeUserIdsMark = inChargeUserIdsMark.substring(0,
                            (inChargeUserIdsMark.length() - 1));
                }

                conds.put("userId", loginUserId);
                conds.put("inChargeUserIds", inChargeUserIds);
                conds.put("inChargeUserIdsMark", inChargeUserIdsMark);
                conds.put("inChargeDepts", inChargeDepts);
                conds.put("taskStatus", -1);// 查询未完成和已完成
                conds.put("userType", 2);
            } else {
                // 客户经理 显示我执行的任务
                Integer isInChargeOf = 0;
                inChargeUserIds = String.valueOf(loginUserId);
                inChargeDepts = "0";

                String userIdMark = "[" + loginUserId + "]";
                conds.put("userId", loginUserId);
                conds.put("userIdMark", userIdMark);
                conds.put("taskStatus", -1);// 查询未完成和已完成
                conds.put("inChargeUserIds", inChargeUserIds);
                conds.put("inChargeUserIdsMark", userIdMark);
                conds.put("inChargeDepts", inChargeDepts);
                conds.put("isInChargeOf", isInChargeOf);
                conds.put("userType", 3);
            }
            conds.put("isOutDate", 1);
            page.setPageSize(10);
            PageUtil<TskMicroTask> dataList = tskMicroTaskService.getTaskListPage(conds, page);
            return dataList;
        } catch (Exception e) {
            logger.error("getOutDateTaskListPage", e);
            return null;
        }
    }

    public void setDeptFacadeService(DeptFacadeService deptFacadeService) {
        this.deptFacadeService = deptFacadeService;
    }

    public void setTskMicroTaskService(TskMicroTaskService tskMicroTaskService) {
        this.tskMicroTaskService = tskMicroTaskService;
    }
}
