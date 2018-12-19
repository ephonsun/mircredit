/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :QianJie
 * Create Date:Mar 2, 2013
 */
package com.banger.mobile.webapp.action.microTask;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.model.base.customer.BaseCrmCustomer;
import com.banger.mobile.domain.model.customer.CrmCustomer;
import com.banger.mobile.domain.model.microTask.TskMicroTaskTarget;
import com.banger.mobile.domain.model.record.RecordDetail;
import com.banger.mobile.facade.customer.CrmCustomerService;
import com.banger.mobile.facade.dept.DeptFacadeService;
import com.banger.mobile.facade.loan.LnLoanService;
import com.banger.mobile.facade.microTask.TskMicroTaskAutoFinishService;
import com.banger.mobile.facade.microTask.TskMicroTaskTargetService;
import com.banger.mobile.facade.record.RecordInfoService;
import com.banger.mobile.util.StringUtil;
import com.banger.mobile.util.VmHelper;
import com.banger.mobile.webapp.action.BaseAction;

/**
 * @author QianJie
 * @version $Id: TskMicroTaskTargetAction.java,v 0.1 Mar 2, 2013 12:02:48 PM
 *          QianJie Exp $
 */
public class TskMicroTaskTargetAction extends BaseAction {

    private static final long serialVersionUID = -5253230237265792405L;

    // 任务目标
    private TskMicroTaskTargetService tskMicroTaskTargetService;
    // 录音信息
    private RecordInfoService recordInfoService;
    private CrmCustomerService crmCustomerService;
    private DeptFacadeService deptFacadeService;
    private TskMicroTaskAutoFinishService tskMicroTaskAutoFinishService;
    private LnLoanService lnLoanService;
    // 查询条件
    private String customerName;
    private String phoneNumber;
    private String callDateFrom;
    private String callDateTo;
    private String isFinish;
    private Integer taskId;
    // 录音信息
    private RecordDetail recordDetail;
    // 新增字段
    private String remark;

    public DeptFacadeService getDeptFacadeService() {
        return deptFacadeService;
    }

    public void setDeptFacadeService(DeptFacadeService deptFacadeService) {
        this.deptFacadeService = deptFacadeService;
    }

    public String firstLoadExeList() {
        try {
            // Integer userId = this.getLoginInfo().getUserId();
            Map<String, Object> parameterMap = new HashMap<String, Object>();
            parameterMap.put("taskId", taskId);
            parameterMap.put("userId", this.getLoginInfo().getUserId());// 执行者是当前登录用户的数据
            parameterMap.put("myExc", 1);
            PageUtil<TskMicroTaskTarget> list = tskMicroTaskTargetService
                    .getTargetListByPage(parameterMap, this.getPage());
            if (list != null && list.getItems().size() > 0) {
                request.setAttribute("dataList", replaceNumber(list.getItems()));
                request.setAttribute("recordCount", list.getPage()
                        .getTotalRowsAmount());
            }
            return SUCCESS;
        } catch (RuntimeException e) {
            log.error("firstLoadExeList error", e);
            return ERROR;
        }
    }

    // 查询
    public String queryExeList() throws ParseException {
        try {
            Map<String, Object> parameterMap = new HashMap<String, Object>();
            parameterMap.put("taskId", taskId);
            parameterMap.put("userId", this.getLoginInfo().getUserId());// 执行者是当前登录用户的数据
            if (request.getParameter("customerQuery") != null) {
                parameterMap.put("customerQuery",
                        request.getParameter("customerQuery").toString());
            }

            if (StringUtil.isNotEmpty(callDateFrom)) {
                // DateFormat dFormat=new
                // SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                // java.util.Date date=dFormat.parse(callDateFrom+":00");
                // parameterMap.put("callDateFrom", date);
                // String callString=callDateFrom+":00";
                parameterMap.put("callDateFrom", callDateFrom + ":00");
                parameterMap.put("isFinish", "1");
            }
            if (StringUtil.isNotEmpty(callDateTo)) {
                // DateFormat dFormat=new
                // SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                // java.util.Date date=dFormat.parse(callDateTo+":00");
                // parameterMap.put("callDateTo", date);
                // String dd=callDateTo+":00";
                parameterMap.put("callDateTo", callDateTo + ":00");
                parameterMap.put("isFinish", "1");
            }
            if (StringUtil.isNotEmpty(isFinish)) {
                parameterMap.put("isFinish", isFinish);
            }
            parameterMap.put("myExc", 1);
            PageUtil<TskMicroTaskTarget> list = tskMicroTaskTargetService
                    .getTargetListByPage(parameterMap, this.getPage());
            request.setAttribute("dataList", replaceNumber(list.getItems()));
            request.setAttribute("recordCount", list.getPage()
                    .getTotalRowsAmount());
            return SUCCESS;
        } catch (RuntimeException e) {
            log.error("queryScheduleByPage error", e);
            return ERROR;
        }
    }

    // 新增任务目标，非在行页面
    public String toAddPhoneTaskExe() {
        try {
            return SUCCESS;
        } catch (RuntimeException e) {
            log.error("addPhoneTaskExe error", e);
            return ERROR;
        }
    }

    /**
     * 添加非在行客户
     */
    public void addPhoneTaskExe() {
        TskMicroTaskTarget t = null;
        try {
            if (StringUtil.isEmpty(customerName)) {
                t = isTargetExsits(-1);
                operaTargetToDB(t, 1);
            } else {
                // 到客户表查询
                Map<String, Object> params = new HashMap<String, Object>();
                params.put("crmName", customerName.trim());
                params.put("phoneNum", phoneNumber.trim());
                List<BaseCrmCustomer> crmList = crmCustomerService
                        .getCrmCustomerByCrm(params);
                if (crmList != null && crmList.size() > 0) {
                    boolean isCrm = false;
                    for (int i = 0; i < crmList.size(); i++) {
                        if (crmList.get(i).getBelongUserId()
                                .equals(this.getLoginInfo().getUserId())) {
                            isCrm = true;
                            t = isTargetExsits(crmList.get(i).getCustomerId());
                            // 如果已经被联系
                            if (t != null) {
                                Integer isOld = lnLoanService
                                        .getLoanCountByCusId(t.getCustomerId());
                                if (isOld != null && isOld > 0) {
                                    t.setIsOldCustomer(1);
                                }
                                isRecordExsits(t, crmList.get(i)
                                        .getCustomerId());
                            }
                            operaTargetToDB(t, 1);
                        }
                    }
                    if (isCrm == false) {
                        operaTargetToDB(null, 2);
                    }
                } else {
                    t = isTargetExsits(-1);
                    operaTargetToDB(t, 1);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 对应客户是否已经被联系
     *
     * @param t
     * @param crmId
     */
    private void isRecordExsits(TskMicroTaskTarget t, int crmId) {
        int recordId = 0;
        /**
        // 获取RECORD_ID
        if (crmId > 0) {
            recordDetail = recordInfoService.getRecordInfoByCustomerId(crmId);
            if (recordDetail != null) {
                recordId = StringUtil
                        .isNotEmpty(recordDetail.getRecordInfoId()) ? Integer
                        .valueOf(recordDetail.getRecordInfoId()) : 0;
            }
            if (recordId != 0) {
                t.setIsFinish(1);
            }
            t.setRecordInfoId(recordId);
        } **/
        /** liyb 2013-11-07 update **/
        if (crmId > 0) {
            if (recordId != 0) {
                t.setIsFinish(1);
            }
            t.setRecordInfoId(recordId);
        }
    }

    /**
     * 判断在任务表中是否已存在
     *
     * @param t
     * @return
     */
    private TskMicroTaskTarget isTargetExsits(int isCrmEx) {
        TskMicroTaskTarget t = new TskMicroTaskTarget();
        t.setTaskId(taskId);
        t.setIsFinish(0);
        t.setRemark(remark);
        t.setCreateUser(this.getLoginInfo().getUserId());
        t.setUserId(this.getLoginInfo().getUserId());
        if (isCrmEx > 0) {
            t.setCustomerId(isCrmEx);
        } else {
            t.setPhoneNumber(phoneNumber.trim());
            t.setCustomerName(customerName.trim());
        }
        if (tskMicroTaskTargetService.getMicroTaskTargetEqualsTarget(t) > 0) {
            return null;
        }
        return t;
    }

    /**
     * 处理添加非在行客户结果
     *
     * @param t
     * @param type
     */
    private void operaTargetToDB(TskMicroTaskTarget t, int type) {
        PrintWriter out;
        try {
            if (t != null) {
                tskMicroTaskTargetService.addTaskTarget(t);
            }
            if (t == null && type == 1) {
                out = this.getResponse().getWriter();
                out.println("isExsits");
            }
            if (t == null && type == 2) {
                out = this.getResponse().getWriter();
                out.println("notBelong");
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    // 新增任务目标，在行
    public void addPhoneTaskSel() {
        PrintWriter out = null;
        try {
            HttpServletResponse response = ServletActionContext.getResponse();
            response.setCharacterEncoding("UTF-8");
            out = response.getWriter();
            JSONArray ary = new JSONArray();
            Integer userId = this.getLoginInfo().getUserId();
            List<TskMicroTaskTarget> list = new ArrayList<TskMicroTaskTarget>();
            List<TskMicroTaskTarget> errList = new ArrayList<TskMicroTaskTarget>();
            Map<String, String> isEqueal = new HashMap<String, String>();
            if (request.getParameter("customerIds") != null) {
                List<CrmCustomer> crmList = crmCustomerService
                        .selectCusByIds(request.getParameter("customerIds")
                                .toString());
                for (CrmCustomer crmCustomer : crmList) {
                    TskMicroTaskTarget tskTaskTarget = new TskMicroTaskTarget();
                    if (crmCustomer.getBelongUserId().equals(userId)) {
                        tskTaskTarget.setTaskId(taskId);
                        tskTaskTarget.setCustomerName(crmCustomer
                                .getCustomerName());
                        tskTaskTarget.setPhoneNumber(crmCustomer
                                .getDefaultPhone());
                        tskTaskTarget
                                .setCustomerId(crmCustomer.getCustomerId());
                        tskTaskTarget.setIsFinish(0);
                        tskTaskTarget.setUserId(userId);
                        tskTaskTarget.setCreateUser(userId);
                        Integer isOld = lnLoanService
                                .getLoanCountByCusId(crmCustomer
                                        .getCustomerId());
                        // 是否老客户
                        if (isOld != null && isOld > 0) {
                            tskTaskTarget.setIsOldCustomer(1);
                        } else {
                            tskTaskTarget.setIsOldCustomer(0);
                        }
                        // 获取RECORD_ID
                        recordDetail = recordInfoService
                                .getRecordInfoByCustomerId(crmCustomer
                                        .getCustomerId());
                        if (recordDetail != null) {
                            String recordId = recordDetail.getRecordInfoId();
                            String string = StringUtil.isEmpty(recordId) ? "0"
                                    : recordId;
                            tskTaskTarget.setRecordInfoId(Integer
                                    .valueOf(string));
                        }
                        TskMicroTaskTarget val = new TskMicroTaskTarget();
                        val = tskTaskTarget;
                        String key = tskTaskTarget.getCustomerName()
                                + tskTaskTarget.getPhoneNumber();
                        //val.setCustomerName(null);
                        //val.setPhoneNumber(null);
                        val.setGpsLat("1");
                        if (tskMicroTaskTargetService.getMicroTaskTargetEqualsTarget(val) <= 0 && !isEqueal.containsKey(key)) {
                            isEqueal.put(key, key);
                            tskTaskTarget.setCustomerName(null);
                            tskTaskTarget.setPhoneNumber(null);
                            list.add(tskTaskTarget);
                        } else {
                            if(crmList.size()==1){
                                tskTaskTarget.setRemark("2");
                                tskTaskTarget.setCustomerName(crmCustomer
                                        .getCustomerName());
                                tskTaskTarget
                                        .setPhoneNumber(crmCustomer.getPhone());
                                errList.add(tskTaskTarget);
                            }else{
                                tskTaskTarget.setRemark("3");
                                tskTaskTarget.setCustomerName(crmCustomer
                                        .getCustomerName());
                                tskTaskTarget
                                        .setPhoneNumber(crmCustomer.getPhone());
                                errList.add(tskTaskTarget);
                            }
                        }
                    } else {
                        tskTaskTarget.setRemark("1");
                        tskTaskTarget.setCustomerName(crmCustomer
                                .getCustomerName());
                        tskTaskTarget.setPhoneNumber(crmCustomer.getPhone());
                        errList.add(tskTaskTarget);
                    }
                }
                if (errList.size() > 0) {
                    ary = JSONArray.fromObject(errList);
                    out.print(ary);
                }
                if (list.size() > 0) {
                    tskMicroTaskTargetService.addTaskTargetBatch(list);
                }
            }
        } catch (Exception e) {
            out.write("ERROR");
            log.error("addPhoneTaskExe error", e);
        }
    }

    // 客户管理-任务安排小页卡
    public String taskCustomerCard() {
        try {
            String customerId = request.getParameter("customerId");
            if (StringUtils.isNotEmpty(customerId)) {
                Map<String, Object> parameterMap = new HashMap<String, Object>();
                if (deptFacadeService.isInChargeOfDepartment()){ // 判断是否是业务主管
                    parameterMap.put("inChargeUserIds",getCurrentInChargeUserIds());
                }else{
                    parameterMap.put("userId", this.getLoginInfo().getUserId());// 执行者是当前登录用户的数据
                }
                parameterMap.put("customerId", customerId);
                PageUtil<TskMicroTaskTarget> list = tskMicroTaskTargetService.getTargetList(parameterMap, this.getPage());
                request.setAttribute("dataList", replaceNumber(list.getItems()));
                request.setAttribute("recordCount", list.getPage().getTotalRowsAmount());
                request.setAttribute("customerId", customerId);
            }
            return SUCCESS;
        } catch (Exception e) {
            log.error("taskCustomerCard", e);
            return ERROR;
        }
    }

    /**
     * 当前用户有权限的 用户ids
     *
     * @return
     */
    private String getCurrentInChargeUserIds() {
        String userIds = "";
        Integer[] arr = deptFacadeService.getInChargeOfDeptUserIds();
        if (arr != null) {
            for (int i = 0; i < arr.length; i++) {
                if (userIds.equals(""))
                    userIds = arr[i].toString();
                else
                    userIds = userIds + "," + arr[i];
            }
        }
        if (userIds.equals("")) {
            userIds = this.getLoginInfo().getUserId().toString();
        } else {
            userIds = userIds + "," + this.getLoginInfo().getUserId();
        }
        return userIds;
    }

    // 客户管理-任务安排小页卡
    public String taskCustomerCardQuery() {
        try {
            String customerId = request.getParameter("customerId");
            if (StringUtils.isNotEmpty(customerId)) {
                Map<String, Object> parameterMap = new HashMap<String, Object>();
                if (deptFacadeService.isInChargeOfDepartment()){ // 判断是否是业务主管
                    parameterMap.put("inChargeUserIds",getCurrentInChargeUserIds());
                }
                else{
                    parameterMap.put("userId", this.getLoginInfo().getUserId());// 执行者是当前登录用户的数据
                }
                parameterMap.put("customerId", customerId);
                PageUtil<TskMicroTaskTarget> list = tskMicroTaskTargetService.getTargetList(parameterMap, this.getPage());
                request.setAttribute("dataList", replaceNumber(list.getItems()));
                request.setAttribute("recordCount", list.getPage().getTotalRowsAmount());
                request.setAttribute("customerId", customerId);
            }
            return SUCCESS;
        } catch (Exception e) {
            log.error("taskCustomerCardQuery", e);
            return ERROR;
        }
    }

    /**
     * 用****替换手机和固话部分号码
     */
    private List<TskMicroTaskTarget> replaceNumber(List<TskMicroTaskTarget> list) {
        for (TskMicroTaskTarget t : list) {
            String pNumber = VmHelper.getHidePhoneNumber(t.getPhoneNumber());
            t.setPhoneNumberHide(pNumber);
        }

        return list;
    }

    /**
     * 导入客户
     *
     * @return
     */
    public String toAddPhoneTaskCus() {
        return SUCCESS;
    }

    /* getter and setter */
    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCallDateFrom() {
        return callDateFrom;
    }

    public void setCallDateFrom(String callDateFrom) {
        this.callDateFrom = callDateFrom;
    }

    public String getCallDateTo() {
        return callDateTo;
    }

    public void setCallDateTo(String callDateTo) {
        this.callDateTo = callDateTo;
    }

    public String getIsFinish() {
        return isFinish;
    }

    public void setIsFinish(String isFinish) {
        this.isFinish = isFinish;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public RecordDetail getRecordDetail() {
        return recordDetail;
    }

    public void setRecordDetail(RecordDetail recordDetail) {
        this.recordDetail = recordDetail;
    }

    public TskMicroTaskTargetService getTskMicroTaskTargetService() {
        return tskMicroTaskTargetService;
    }

    public void setTskMicroTaskTargetService(
            TskMicroTaskTargetService tskMicroTaskTargetService) {
        this.tskMicroTaskTargetService = tskMicroTaskTargetService;
    }

    public void setCrmCustomerService(CrmCustomerService crmCustomerService) {
        this.crmCustomerService = crmCustomerService;
    }

    public void setRecordInfoService(RecordInfoService recordInfoService) {
        this.recordInfoService = recordInfoService;
    }

    // 用于完成营销录音
    public void setTskMicroTaskAutoFinishService(
            TskMicroTaskAutoFinishService tskMicroTaskAutoFinishService) {
        this.tskMicroTaskAutoFinishService = tskMicroTaskAutoFinishService;
    }

    public void setLnLoanService(LnLoanService lnLoanService) {
        this.lnLoanService = lnLoanService;
    }

}