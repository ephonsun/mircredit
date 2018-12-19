package com.banger.mobile.webapp.action.microTask;

import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.model.base.customer.BaseCrmCustomer;
import com.banger.mobile.domain.model.customer.CrmCustomer;
import com.banger.mobile.domain.model.microTask.TskSchedule;
import com.banger.mobile.domain.model.record.RecordDetail;
import com.banger.mobile.domain.model.task.TskTask;
import com.banger.mobile.domain.model.task.TskTaskTarget;
import com.banger.mobile.domain.model.visitRecord.VisitRecord;
import com.banger.mobile.domain.model.visitRecord.VisitRecordInfo;
import com.banger.mobile.facade.customer.CrmCustomerService;
import com.banger.mobile.facade.dept.DeptFacadeService;
import com.banger.mobile.facade.microTask.TskScheduleService;
import com.banger.mobile.facade.record.RecordInfoService;
import com.banger.mobile.facade.specialDataAuth.SpecialDataAuthService;
import com.banger.mobile.facade.task.TskTaskService;
import com.banger.mobile.facade.task.TskTaskTargetService;
import com.banger.mobile.facade.visitRecord.VisitRecordService;
import com.banger.mobile.util.DateUtil;
import com.banger.mobile.util.StringUtil;
import com.banger.mobile.webapp.action.BaseAction;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.*;

public class TskScheduleAction extends BaseAction{

    private static Logger logger = Logger.getLogger(TskScheduleAction.class);
    private static final long serialVersionUID = 1589326398928860527L;

    private TskScheduleService tskScheduleService;
    private CrmCustomerService crmCustomerService; //客户service
    private DeptFacadeService deptFacadeService;                             // 部门组织结构service
    private SpecialDataAuthService specialDataAuthService;  //特殊数据权限service

    private Date contactDateFrom;
    private Date contactDateEnd;

    private TskSchedule tskSchedule;

    private List<TskSchedule> scheduleList;
    private List<Map> commProgressList;
    private String customerIdString;   //用于接收checkbox选中的一组用户id
    private List<Map> customerTypeList;

    private Integer isPast;    //是否过期
    private Integer customerType; //客户类型

    private CrmCustomer crmCustomer; //客户信息

    //编辑拜访记录所添加
    private VisitRecord visitRecord;            //拜访记录实体
    private String actionType; //活动类型
    private String calltype;
    private RecordDetail recordDetail;                            //根据id查询返回的结果集

    public SpecialDataAuthService getSpecialDataAuthService() {
        return specialDataAuthService;
    }

    public void setSpecialDataAuthService(SpecialDataAuthService specialDataAuthService) {
        this.specialDataAuthService = specialDataAuthService;
    }

    public RecordDetail getRecordDetail() {
        return recordDetail;
    }

    public void setRecordDetail(RecordDetail recordDetail) {
        this.recordDetail = recordDetail;
    }

    public RecordInfoService getRecordInfoService() {
        return recordInfoService;
    }

    public void setRecordInfoService(RecordInfoService recordInfoService) {
        this.recordInfoService = recordInfoService;
    }

    private RecordInfoService recordInfoService;                       //录音信息service

    public String getCalltype() {
        return calltype;
    }

    public void setCalltype(String calltype) {
        this.calltype = calltype;
    }

    public DeptFacadeService getDeptFacadeService() {
        return deptFacadeService;
    }

    public void setDeptFacadeService(DeptFacadeService deptFacadeService) {
        this.deptFacadeService = deptFacadeService;
    }

    private VisitRecordService visitRecordService;     //拜访记录service
    private TskTask task;
    private TskTaskService tskTaskService;

    private VisitRecordInfo visitRecordInfo;        //拜访记录信息
    private TskTaskTargetService tskTaskTargetService; //任务目标客户service

    public VisitRecordInfo getVisitRecordInfo() {
        return visitRecordInfo;
    }

    public void setVisitRecordInfo(VisitRecordInfo visitRecordInfo) {
        this.visitRecordInfo = visitRecordInfo;
    }

    public TskTaskTargetService getTskTaskTargetService() {
        return tskTaskTargetService;
    }

    public void setTskTaskTargetService(TskTaskTargetService tskTaskTargetService) {
        this.tskTaskTargetService = tskTaskTargetService;
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        TskScheduleAction.logger = logger;
    }

    public Integer getPast() {
        return isPast;
    }

    public void setPast(Integer past) {
        isPast = past;
    }

    public VisitRecord getVisitRecord() {
        return visitRecord;
    }

    public void setVisitRecord(VisitRecord visitRecord) {
        this.visitRecord = visitRecord;
    }

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    public VisitRecordService getVisitRecordService() {
        return visitRecordService;
    }

    public void setVisitRecordService(VisitRecordService visitRecordService) {
        this.visitRecordService = visitRecordService;
    }

    public TskTask getTask() {
        return task;
    }

    public void setTask(TskTask task) {
        this.task = task;
    }

    public TskTaskService getTskTaskService() {
        return tskTaskService;
    }

    public void setTskTaskService(TskTaskService tskTaskService) {
        this.tskTaskService = tskTaskService;
    }

    /**
     * 首页列表
     */
    public String firstLoadScheduleList(){
        try {
            Map<String , Object> parameterMap = new HashMap<String , Object>();
            String workSpace = request.getParameter("workSpace");
            Boolean isInChargeOf = deptFacadeService.isInChargeOfDepartment();
            if (isInChargeOf && workSpace == null) {
                // 当前用户的下属用户
                String belongUserIds = deptFacadeService.getUserIdsBelongToManager(getLoginInfo().getRoles(),"taskschedule");
                parameterMap.put("userIds", belongUserIds); // 当前用户所管理的提交申请用户
            } else {
                // 客户经理
                parameterMap.put("userId" ,this.getLoginInfo().getUserId());
            }
            /**
             * 此处添加的isPad属性值，是因为对业务的修改，但又不想影响现有的业务处理方式，故加上这么一个属性值来标记
             */
            parameterMap.put("isPad",1);

            //判断是否传入这个属性值，用于查询出7天内的日程
            String isQueryWeek = request.getParameter("isQueryWeek");
            if(isQueryWeek != null && isQueryWeek.equals("0")){
                //查询7天内的未完成日程
                scheduleList = tskScheduleService.getInTimeScheduleListByPage(parameterMap,this.getPage());
                request.setAttribute("isQueryWeek",isQueryWeek);
            } else {
                //分页查询出TSK_SCHEDULE表中的日程记录
                scheduleList = tskScheduleService.getScheduleListByPage(parameterMap,this.getPage());
            }

            long curDateTime = new Date().getTime();
            for(TskSchedule tskSchedule1 : scheduleList){
                Date contactDate = tskSchedule1.getContactDate();
                if(contactDate != null){
                    long contactDateTime = contactDate.getTime();
                    if(curDateTime > contactDateTime && tskSchedule1.getStatus().equals(0)){
                        //日程过期
                        tskSchedule1.setIsOutDate(1);
                    }
                }
            }
            //查找所有沟通进度配置信息
            commProgressList = tskScheduleService.getAllCommProgress();
            request.setAttribute("scheduleCount", this.getPage().getTotalRowsAmount());
            request.setAttribute("sysUserId",this.getLoginInfo().getUserId());
            if (workSpace != null && workSpace.equals("true")){
                request.setAttribute("workSpace",workSpace);
            }
            return SUCCESS;
        } catch (RuntimeException e) {
            log.error("firstLoadScheduleList error:", e);
            return ERROR;
        }
    }
    /**
     * 搜索日程
     */
    public String queryScheduleList(){
        try {
            Map<String , Object> parameterMap = new HashMap<String , Object>();
            String isQueryWeek = request.getParameter("isQueryWeek");
            String workSpace = request.getParameter("workSpace");
            Boolean isInChargeOf = deptFacadeService.isInChargeOfDepartment();
            if (isInChargeOf && workSpace == null) {
                // 当前用户的下属用户
                String belongUserIds = deptFacadeService.getUserIdsBelongToManager(getLoginInfo().getRoles(),"taskschedule");
                parameterMap.put("userIds", belongUserIds); // 当前用户所管理的提交申请用户
            } else {
                // 客户经理
                parameterMap.put("userId" ,this.getLoginInfo().getUserId());
            }
            parameterMap.put("isPad",1);
            if(tskSchedule!=null){
                parameterMap.put("customerName", tskSchedule.getPdtProductCustomerBean().getCustomerName());
                parameterMap.put("customerPhone", tskSchedule.getPdtProductCustomerBean().getMobilePhone1());
                parameterMap.put("contactTypeId", tskSchedule.getContactType());
                parameterMap.put("commProgressId", tskSchedule.getCommProgressId());
                parameterMap.put("status", tskSchedule.getStatus());
            }
            //判断是否过期
            if(isPast!=null){
                if(isPast==0){
                    //未过期
                    parameterMap.put("noPast", 1);
                }
                if(isPast==1){
                    //已过期
                    parameterMap.put("past", 1);
                    parameterMap.put("isWebPoint",1);
                }
            }
            if(contactDateFrom!=null){
                parameterMap.put("contactDateFrom", contactDateFrom);
            }
            if(contactDateEnd!=null){
                parameterMap.put("contactDateEnd", contactDateEnd);
            }

            if(isQueryWeek != null && isQueryWeek.equals("0")){
                //查询七天内的未完成日程(分页查询)
                scheduleList = tskScheduleService.getInTimeScheduleListByPage(parameterMap,this.getPage());
            } else {
                scheduleList = tskScheduleService.getScheduleListByPage(parameterMap,this.getPage());
            }
            commProgressList = tskScheduleService.getAllCommProgress();
            long curDateTime = new Date().getTime();
            if(scheduleList != null && !scheduleList.isEmpty()){
                for(TskSchedule tskSchedule1 : scheduleList){
                    Date contactDate = tskSchedule1.getContactDate();
                    if(contactDate != null){
                        long contactDateTime = contactDate.getTime();
                        if(curDateTime > contactDateTime && tskSchedule1.getStatus().equals(0)){
                            //日程过期
                            tskSchedule1.setIsOutDate(1);
                        }
                    }
                }
            }
            request.setAttribute("scheduleCount", this.getPage().getTotalRowsAmount());
            request.setAttribute("sysUserId",this.getLoginInfo().getUserId());
            return SUCCESS;
        } catch (RuntimeException e) {
            log.error("queryScheduleByPage error", e);
            return ERROR;
        }
    }
    /**
     * 进入编辑日程
     */
    public String loadOldScheduleInfo(){
        try {
            String scheduleIdStr = request.getParameter("scheduleId");
            Integer scheduleId = null;
            if(StringUtil.isNotEmpty(scheduleIdStr) && StringUtil.isNumber(scheduleIdStr)){
                scheduleId = Integer.valueOf(scheduleIdStr);
            }
            tskSchedule = tskScheduleService.getScheduleById(scheduleId);

            return SUCCESS;
        } catch (RuntimeException e) {
            log.error("loadOldScheduleInfo error",e);
            return ERROR;
        }
    }
    /**
     * 编辑日程
     */
    public void editSchedule(){
        try{
            if(tskSchedule!=null){
                if (tskSchedule.getStatus() != null && tskSchedule.getStatus().equals(1)){
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
//                            paramMap.put("contactDate",format.format(nowDate));
                            }
                            Integer recordInfoId = recordInfoService.getRecentlyRecordForSchedule(paramMap);
                            tskSchedule.setRecordInfoId(recordInfoId);
                        }
                    }
                }
                tskScheduleService.updateSchedule(tskSchedule);
            }
            Map<String , Object> parameterMap = new HashMap<String , Object>();
            parameterMap.put("userId" ,this.getLoginInfo().getUserId());

            scheduleList = tskScheduleService.getScheduleListByPage(parameterMap,this.getPage());
            commProgressList = tskScheduleService.getAllCommProgress();

            request.setAttribute("scheduleCount", this.getPage().getTotalRowsAmount());

        }catch(Exception e){
            log.error("TskScheduleAction % editSchedule",e);
        }
    }
    /**
     * 跳转到新增日程安排
     * @return
     */
    public String toTskNewSchedule(){
        return SUCCESS;
    }
    /**
     * 新增日程(给用户新建日程/已有日程更新)
     */
//    public String tskNewSchedule(){
//        try{
//            if(customerIdString!=null){
//                if(tskSchedule!=null){
//                    int userId = this.getLoginInfo().getUserId();
//                    tskSchedule.setUserId(userId);
//                    //创建用户
//                    tskSchedule.setCreateUser(userId);
//                    //更新用户
//                    tskSchedule.setUpdateUser(userId);
//                    //状态,默认为0
//                    tskSchedule.setStatus(0);
//                    //沟通进度,默认为'初期沟通'
//                    tskSchedule.setCommProgressId(1);
//
//                    String []customerIdArray = customerIdString.split(",");
//
//                    for (int i = 0; i < customerIdArray.length; i++) {
//                        tskSchedule.setCustomerId(Integer.parseInt(customerIdArray[i]));
//
//                        Map<String,Object> map = new HashMap<String,Object>();
//                        map.put("userId", userId);
//                        map.put("customerId", Integer.parseInt(customerIdArray[i]));
//
//                        int count = tskScheduleService.judgeCustomerSchedule(map);
//                        System.out.println("count="+count+"-------------");
//
//                        if(count>0){
//                            //已有日程的更新
//                            System.out.println("已有日程的更新 ");
//                            tskScheduleService.updateSchedule(tskSchedule);
//                        }else if(count==0){
//                            //没有日程的插入
//                            System.out.println("没有日程的插入");
//                            tskScheduleService.addNewSchedule(tskSchedule);
//                        }
//                    }
//
//                }
//            }
//            Map<String , Object> parameterMap = new HashMap<String , Object>();
//            parameterMap.put("userId" ,this.getLoginInfo().getUserId());
//
//            scheduleList = tskScheduleService.getCustomerList(parameterMap,this.getPage());
//            commProgressList = tskScheduleService.getAllCommProgress();
//
//            request.setAttribute("scheduleCount", this.getPage().getTotalRowsAmount());
//
//            return SUCCESS;
//        }catch(Exception e){
//            log.error("tskNewSchedule error"+e);
//            return ERROR;
//        }
//    }

    /**
     * 给所选客户增加日程
     * @return
     */
    public String addSchedulesByCustomers(){
        try{
            Integer userId = this.getLoginInfo().getUserId();
            if(customerIdString != null){

                //前端输入的数据信息
                //联系类型
                Integer contactType = null;
                //联系时间
                Date contactDate = null;
                //备注
                String remark = null;
                if(tskSchedule != null){
                    contactType = tskSchedule.getContactType();
                    contactDate = tskSchedule.getContactDate();
                    remark = tskSchedule.getRemark();
                }
                String[] customerIdArray = customerIdString.split(",");
                if(customerIdArray != null && customerIdArray.length == 1){
                    TskSchedule insertTskSchedule = new TskSchedule();
                    insertTskSchedule.setUserId(userId);
                    insertTskSchedule.setCreateUser(userId);
                    insertTskSchedule.setUpdateUser(userId);
                    insertTskSchedule.setStatus(0);
                    //默认选择第一条进度
                    Integer no1CommProgressId = tskScheduleService.getNo1CommProgressId();
                    insertTskSchedule.setCommProgressId(no1CommProgressId);
                    insertTskSchedule.setContactDate(contactDate);
                    insertTskSchedule.setContactType(contactType);
                    insertTskSchedule.setRemark(remark);
                    insertTskSchedule.setCustomerId(Integer.parseInt(customerIdArray[0]));
                    tskScheduleService.addNewSchedule(insertTskSchedule);
                }else if(customerIdArray != null && customerIdArray.length > 1){
                    List<TskSchedule> tskScheduleList = new ArrayList<TskSchedule>();

                    for (int i = 0; i < customerIdArray.length; i++){
                        TskSchedule insertTskSchedule = new TskSchedule();
                        insertTskSchedule.setUserId(userId);
                        insertTskSchedule.setCreateUser(userId);
                        insertTskSchedule.setUpdateUser(userId);
                        insertTskSchedule.setStatus(0);
                        //默认选择第一条进度
                        Integer no1CommProgressId = tskScheduleService.getNo1CommProgressId();
                        insertTskSchedule.setCommProgressId(no1CommProgressId);
                        insertTskSchedule.setContactDate(contactDate);
                        insertTskSchedule.setContactType(contactType);
                        insertTskSchedule.setRemark(remark);

                        insertTskSchedule.setCustomerId(Integer.parseInt(customerIdArray[i]));

                        tskScheduleList.add(insertTskSchedule);
                    }
                    tskScheduleService.insertTskScheduleBatch(tskScheduleList);
                }
            }

            Map<String , Object> parameterMap = new HashMap<String , Object>();
            parameterMap.put("userId" ,userId);
            parameterMap.put("isDel",0);
            parameterMap.put("isTrash",0);
            scheduleList = tskScheduleService.getCustomerList(parameterMap,this.getPage());
            commProgressList = tskScheduleService.getAllCommProgress();

            request.setAttribute("scheduleCount", this.getPage().getTotalRowsAmount());
            return SUCCESS;
        }catch (Exception e){
            log.error("TskScheduleAction % addSchedulesByCustomers",e);
            return ERROR;
        }
    }
    /**
     * 新增日程安排(页卡)
     * @return
     */
    public String tskAddSchedulePlan(){
        try {
            Map<String , Object> parameterMap = new HashMap<String , Object>();
            parameterMap.put("userId" ,this.getLoginInfo().getUserId());
            parameterMap.put("isDel",0);
            parameterMap.put("isTrash",0);

            scheduleList = tskScheduleService.getCustomerList(parameterMap,this.getPage());

            commProgressList = tskScheduleService.getAllCommProgress();
            customerTypeList = tskScheduleService.getCustomerTypeList();

            request.setAttribute("scheduleCount", this.getPage().getTotalRowsAmount());
            return SUCCESS;
        } catch (RuntimeException e) {
            log.error("firstLoadScheduleList error:", e);
            return ERROR;
        }
    }
    /**
     * 判断是否插入新日程
     */
//	public void judgeCustomerSchedule(){
//	    try{
//	        HttpServletResponse response = ServletActionContext.getResponse();
//            response.reset();
//            response.setContentType("text/html;charset=utf-8");
//            PrintWriter out = response.getWriter();
//
//	        if(customerIdString!=null){
//
//	            int userId = this.getLoginInfo().getUserId();
//	            int count = 0;
//	            String[] customerIdArray = customerIdString.split(",");
//
//	            for (int i = 0; i < customerIdArray.length; i++) {
//
//	                 Map<String,Object> map = new HashMap<String,Object>();
//	                 map.put("userId", userId);
//	                 map.put("customerId", Integer.parseInt(customerIdArray[i]));
//
//	                count += tskScheduleService.judgeCustomerSchedule(map);
//	             }
//
//	            if(count>0){
//	                out.print(1);
//	                out.close();
//	            }else if(count==0){
//	                out.print(0);
//	                out.close();
//	            }
//	        }
//	    }catch(Exception e){
//	        log.error("firstLoadScheduleList error:", e);
//	    }
//
//	}

    /**
     * 判断所选客户是否已有日程
     */
    public void judgeScheduleByCustomer(){
        try{
            HttpServletResponse response = ServletActionContext.getResponse();
            response.reset();
            response.setContentType("text/html;charset=utf-8");
            PrintWriter out = response.getWriter();
            int count = 0;
            String customerName = null;
            try {
                if(customerIdString != null){
                    int userId = this.getLoginInfo().getUserId();
                    String[] customerIdArray = customerIdString.split(",");
                    Map<String,Object> paramMap = new HashMap<String,Object>();
                    paramMap.put("userId",userId);
                    if(customerIdArray != null && customerIdArray.length == 1){
                        List<Integer> customerIds = new ArrayList<Integer>();
                        for (int i = 0; i < customerIdArray.length; i++){
                            customerIds.add(Integer.parseInt(customerIdArray[i]));
                        }
                        paramMap.put("customerIds",customerIds);
                        //查看当前所选的用户是否有日程
                        count = tskScheduleService.judgeScheduleByCustomerId(paramMap);
                        if(count > 0){
                            BaseCrmCustomer baseCrmCustomer = crmCustomerService.getCrmCustomerById(customerIds.get(0));
                            customerName = baseCrmCustomer.getCustomerName();
                        }
                    }else if(customerIdArray != null && customerIdArray.length > 1){
                        List<Integer> customerIds = new ArrayList<Integer>();
                        for (int i = 0; i < customerIdArray.length; i++){
                            customerIds.add(Integer.parseInt(customerIdArray[i]));
                        }
                        paramMap.put("customerIds",customerIds);
                        //查看当前所选的用户是否有日程
                        count = tskScheduleService.judgeScheduleByCustomerId(paramMap);
                    }
                }
            }finally {
                if(count > 0){
                    out.print(1+","+customerName);
                }else if(count == 0){
                    out.print(0);
                }
                if (out != null)
                    out.close();
            }

        }catch (Exception e){
            log.error("TskScheduleAction%judgeScheduleByCustomer",e);
        }
    }
    /**
     * 搜索客户列表
     * @return
     */
    public String queryCustomerList(){
        try{
            Map<String , Object> map = new HashMap<String , Object>();
            map.put("userId" ,this.getLoginInfo().getUserId());
            map.put("isDel",0);
            map.put("isTrash",0);
            if(tskSchedule != null){
                map.put("customerName", tskSchedule.getPdtProductCustomerBean().getCustomerName());
                map.put("customerType", tskSchedule.getPdtProductCustomerBean().getCustomerTypeId());
                map.put("productName", tskSchedule.getPdtProductCustomerBean().getProductName());
            }

            scheduleList = tskScheduleService.getCustomerList(map,this.getPage());
            customerTypeList = tskScheduleService.getCustomerTypeList();

            request.setAttribute("scheduleCount", this.getPage().getTotalRowsAmount());
            return SUCCESS;
        }catch(Exception e){
            log.error("queryCustomerList error:", e);
            return ERROR;
        }
    }
    /**
     * 详情页
     * @return
     */
    public String viewDetail(){
        try{
            if(tskSchedule!=null){
//	            System.out.println("scheduleId="+tskSchedule.getScheduleId()+"----------------");
                logger.info("scheduleId="+tskSchedule.getScheduleId()+"----------------");
                Map<String,Object> paramMap = new HashMap<String, Object>();
                paramMap.put("scheduleId",tskSchedule.getScheduleId());
                //获取用户基本信息
                tskSchedule = tskScheduleService.getCustomerDetail(paramMap);
                //沟通进度参数信息
                commProgressList = tskScheduleService.getAllCommProgress();
                paramMap.put("customerId",tskSchedule.getCustomerId());
                paramMap.put("userId",this.getLoginInfo().getUserId());
                //获取用户意向产品信息
                scheduleList = tskScheduleService.getCustomerPDTProductList(paramMap,this.getPage());
            }
            return SUCCESS;
        }catch(Exception e){
            logger.error("TskScheduleAction%viewDetail", e);
            return ERROR;
        }
    }

    /**
     * 搜索用户意向产品信息
     * @return
     */
    public String queryLoanIntentionList(){
        try{
            if(tskSchedule != null){
                Map<String,Object> paramMap = new HashMap<String,Object>();
                paramMap.put("customerId",request.getParameter("customerId"));
                scheduleList = tskScheduleService.getCustomerPDTProductList(paramMap,this.getPage());
            }
            return SUCCESS;
        }catch (Exception e){
            logger.error("TskScheduleAction%queryLoanIntentionList",e);
            return ERROR;
        }
    }

    /**
     * 新增拜访记录页面
     * @return
     */
    @SuppressWarnings("unused")
    public String showAddVisitRecordPage(){
        try {
            //得到当前要新建拜访的日程记录信息
            TskSchedule curTskSchedule = tskScheduleService.getScheduleById(tskSchedule.getScheduleId());
            int customerId = crmCustomer.getCustomerId();
            String customerName=this.crmCustomerService.getCrmCustomerById(customerId).getCustomerName();
            commProgressList = tskScheduleService.getAllCommProgress();
//            request.setAttribute("recbizTypeList", recbizTypeList);
            request.setAttribute("commProgressList", commProgressList);
            request.setAttribute("customerName", customerName);
            request.setAttribute("customerId", customerId);
            request.setAttribute("scheduleId",tskSchedule.getScheduleId());
            request.setAttribute("commProgressId",curTskSchedule.getCommProgressId());
            return SUCCESS;
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            return ERROR;
        }

    }

    /**添加
     *
     * @return
     */
    public String addVisitRecord(){
        try {
            actionType=request.getParameter("type");
            int userId=this.getLoginInfo().getUserId();       //客户经理id
            int customerId =Integer.parseInt(visitRecord.getCustomerId().toString());
            BaseCrmCustomer customer = new BaseCrmCustomer();
            customer=this.crmCustomerService.getCrmCustomerById(customerId);
            visitRecord.setCustomerName(customer.getCustomerName());
            int phoneType=customer.getDefaultPhoneType();
            if(phoneType==1){
                visitRecord.setRemotePhone(customer.getMobilePhone1());
            }else if(phoneType==2){
                visitRecord.setRemotePhone(customer.getMobilePhone2());
            }else if(phoneType==3){
                visitRecord.setRemotePhone(customer.getPhone());
            }else{
                visitRecord.setRemotePhone(customer.getFax());
            }
            visitRecord.setUserId(userId);
            visitRecord.setRecordNo(String.valueOf(System.currentTimeMillis()));
            visitRecord.setCreatUser(userId);
            visitRecord.setBizType(0);
            //组装客户冗余字段
            String cusStr="";
            if(!StringUtil.isEmpty(customer.getCustomerName())){
                cusStr += customer.getCustomerName()+"_";
            }
            if(!StringUtil.isEmpty(customer.getCustomerNamePinyin())){
                cusStr += customer.getCustomerNamePinyin()+"_";
            }
            if(!StringUtil.isEmpty(customer.getMobilePhone1())){
                cusStr += customer.getMobilePhone1()+"_";
            }
            if(!StringUtil.isEmpty(customer.getMobilePhone2())){
                cusStr += customer.getMobilePhone2()+"_";
            }
            if(!StringUtil.isEmpty(customer.getPhone())){
                cusStr += customer.getPhone()+"_";
            }
            if(!StringUtil.isEmpty(customer.getFax())){
                cusStr += customer.getFax()+"_";
            }
            visitRecord.setCustomerStr(cusStr);
            //新建拜访
            this.visitRecordService.addVisitRecord(visitRecord);
            //新建拜访记录之后将得到的recordInfoId更新到tsk_schedule表中的record_info_id列
            tskSchedule.setRecordInfoId(visitRecord.getRecordInfoId());
            //新建拜访记录之后将得到的commProgressId更新到tsk_schedule表中的comm_progress_id列
            tskSchedule.setCommProgressId(visitRecord.getCommProgress());
            tskScheduleService.updateSchedule(tskSchedule);
            if(task!=null&&task.getTaskId()!=null){
                tskTaskService.updateNextTaskIsDel(task.getTaskId());
            }
            if(actionType.equals("save")){
                return SUCCESS;
            }else{
                //  request.setAttribute("customer.customerId", customerId);
                return "addAndAdd";
            }
        } catch (RuntimeException e) {
            log.error("addVisitRecord ERROR",e);
            return ERROR;
        }
    }


    /**
     * 跳转到更新页面
     * @return
     */
    public String showUpdateVisitRecordPage(){
        try {
            int recordInfoId =Integer.parseInt(request.getParameter("recordInfoId"));
            visitRecordInfo=this.visitRecordService.getVisitRecord(recordInfoId);
            commProgressList = tskScheduleService.getAllCommProgress();//沟通进度
//            request.setAttribute("recbizTypeList", recbizTypeList);
            request.setAttribute("commProgressList", commProgressList);
            request.setAttribute("visitRecordInfo",visitRecordInfo);
            //     visitRecordInfo =this.visitRecordService.getVisitRecord(43);
            request.setAttribute("type", request.getParameter("type"));
//
            String customerName = URLDecoder.decode(request.getParameter("customerName"), "UTF-8");
            request.setAttribute("customerName",customerName);
            Integer customerId = Integer.parseInt(request.getParameter("customerId"));
            request.setAttribute("customerId", customerId);
            request.setAttribute("scheduleId",tskSchedule.getScheduleId());
            return SUCCESS;
        } catch (Exception e) {
            log.error("showUpdateVisitRecordPage action error"+e.getMessage());
            return ERROR;
        }
    }

    /**
     * 更新
     * @return
     */
    public String updateVisitRecord(){
        try {
            if(visitRecord.getCommProgress()==null){
                visitRecord.setCommProgress(0);
            }
            visitRecord.setCreatUser(this.getLoginInfo().getUserId());
            visitRecord.setBizType(0);
            this.visitRecordService.updateVisitRecord(visitRecord);
            //新建拜访记录之后将得到的commProgressId更新到tsk_schedule表中的comm_progress_id列
            tskSchedule.setCommProgressId(visitRecord.getCommProgress());
            tskScheduleService.updateSchedule(tskSchedule);
            if(task!=null&&task.getTaskId()!=null){
                tskTaskService.updateNextTaskIsDel(task.getTaskId());
            }
            return SUCCESS;
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            return ERROR;
        }
    }

    /**
     * 下次联系信息页面
     * @return
     */
    public String nextTaskUp(){
        try {
            crmCustomer =this.crmCustomerService.getCustomerInfo(crmCustomer.getCustomerId());
            task=tskTaskService.getCustomerNextTaskById(crmCustomer.getCustomerId(),task.getTaskId(),task.getIsDel());
            return SUCCESS;
        } catch (Exception e) {
            log.error(e.getMessage());
            return ERROR;
        }
    }

    /**
     * 修改下次联系信息
     */
    public void updateNextContextTask(){
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setCharacterEncoding("UTF-8");
        PrintWriter out =null;
        try{
            out = response.getWriter();
            task.setUpdateUser(this.getLoginInfo().getUserId());
            if(task.getTaskStatus()==1){
                task.setFinishDate(new Date());
            }
            task.setEndDate(task.getStartDate());
            tskTaskService.updateTskTaskContact(task);
            out.print(task.getTaskId()+","+ DateUtil.getDateToString(task.getStartDate())+","+task.getTaskStatus());
        }catch(Exception e){
            log.error(e.getMessage());
        }
    }

    /**
     * 下次联系页面
     * @return
     */
    public String showNextTaskPage(){
        try {
            crmCustomer =this.crmCustomerService.getCustomerInfo(crmCustomer.getCustomerId());
            return SUCCESS;
        } catch (Exception e) {
            log.error(e.getMessage());
            return ERROR;
        }
    }

    /**
     * 添加下次联系任务
     * @return
     */
    public void addNextContextTask(){
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setCharacterEncoding("UTF-8");
        PrintWriter out =null;
        try{
            out = response.getWriter();
            task.setCreateUser(this.getLoginInfo().getUserId());
            task.setAssignUserId(this.getLoginInfo().getUserId());
            task.setIsNextContact(1);
            task.setEndDate(task.getStartDate());
            task.setIsDel(0);
            task.setExecuteDeptId(getLoginInfo().getDeptId());
            Integer taskId = tskTaskService.insertTskTaskContact(task);//添加主任务
            if (taskId != null) {
                //添加任务执行者
                TskTask tsktask = new TskTask();
                tsktask.setParentTaskId(taskId);
                tsktask.setExecuteDeptId(0);
                tsktask.setCreateUser(this.getLoginInfo().getUserId());
                tsktask.setExecuteUserId(getLoginInfo().getUserId());
                tskTaskService.saveTaskExecuteUser(tsktask);

                //插入联系客户
                TskTaskTarget target=new TskTaskTarget();
                target.setCustomerId(crmCustomer.getCustomerId());
                target.setCreateUser(getLoginInfo().getUserId());
                target.setTaskId(taskId);
                target.setDeptId(getLoginInfo().getDeptId());
                tskTaskTargetService.insertTskTaskTarget(target);
                out.print(taskId+","+DateUtil.getDateToString(task.getStartDate())+",0,"+1);
            }
        }catch(Exception e){
            log.error(e.getMessage());
        }
    }

    /**
     * 查看客户业务信息小页卡-日程安排
     * 
     * @return
     */
    public String getScheduleListCard(){
        try {
            Map<String,Object> paramMap = new HashMap<String, Object>();
            Boolean isInChargeOf = deptFacadeService.isInChargeOfDepartment();
            String belongUserIds = "";
            if (isInChargeOf) {
                // 当前用户的下属用户
                belongUserIds = deptFacadeService.getUserIdsBelongToManager(getLoginInfo().getRoles(),"taskschedule");
                paramMap.put("userIds", belongUserIds); // 当前用户所管理的提交申请用户
            } else {
                // 客户经理
                paramMap.put("userId" ,this.getLoginInfo().getUserId());
            }
            String customerIdStr = request.getParameter("customerId");
            if(StringUtil.isNotEmpty(customerIdStr)){
                String edit = request.getParameter("edit");
                if(edit != null && edit.equals("edit")){
                    //编辑页面
                    request.setAttribute("hasPermission",1);
                }
                Integer customerId = Integer.parseInt(customerIdStr.trim());
                paramMap.put("customerId",customerId);
//                scheduleList = tskScheduleService.getScheduleListByPage(paramMap,this.getPage());
                PageUtil<TskSchedule> list = tskScheduleService.getTskScheduleCard(paramMap,this.getPage());
                scheduleList = list.getItems();
                long curDateTime = Calendar.getInstance().getTimeInMillis();
                for (TskSchedule tskSchedule1 : scheduleList){
                    Date contactDate = tskSchedule1.getContactDate();
                    if(contactDate != null){
                        long contactDateTime = contactDate.getTime();
                        if(curDateTime > contactDateTime && tskSchedule1.getStatus().equals(0)){
                            //日程过期
                            tskSchedule1.setIsOutDate(1);
                        }
                    }
                }

                //验证当前客户是否在当前用户的管辖范围内
                CrmCustomer curCustomer = crmCustomerService.getCustomerById(customerId);
                if (curCustomer != null){
                    Integer belongUserId = curCustomer.getBelongUserId();
                    if (belongUserId != null && belongUserId != 0){
                        if (belongUserId.equals(this.getLoginInfo().getUserId())){
                            request.setAttribute("isManagedCustomer",1);
                        }
                    }
                }

                commProgressList = tskScheduleService.getAllCommProgress();
                request.setAttribute("customerId",customerId);
                request.setAttribute("scheduleCount",this.getPage().getTotalRowsAmount());
                request.setAttribute("loginUserId",this.getLoginInfo().getUserId());
            }
            return SUCCESS;
        }catch (Exception e){
            logger.error("TskScheduleAction % getScheduleListCard",e);
            return ERROR;
        }
    }

    /**
     * 查询或刷新日程安排小页卡
     * 
     * @return
     */
    public String queryScheduleListCard(){
        try {
            Map<String,Object> paramMap = new HashMap<String, Object>();
            Boolean isInChargeOf = deptFacadeService.isInChargeOfDepartment();
            String belongUserIds = "";
            if (isInChargeOf) {
                // 当前用户的下属用户
                belongUserIds = deptFacadeService.getUserIdsBelongToManager(getLoginInfo().getRoles(),"taskschedule");
                paramMap.put("userIds", belongUserIds); // 当前用户所管理的提交申请用户
            } else {
                // 客户经理
                paramMap.put("userId" ,this.getLoginInfo().getUserId());
            }
            String customerIdStr = request.getParameter("customerId");
            String edit = request.getParameter("edit");
            if(StringUtil.isNotEmpty(customerIdStr)){
                Integer customerId = Integer.parseInt(customerIdStr.trim());
                paramMap.put("customerId",customerId);
//                scheduleList = tskScheduleService.getScheduleListByPage(paramMap,this.getPage());
                PageUtil<TskSchedule> list = tskScheduleService.getTskScheduleCard(paramMap,this.getPage());
                scheduleList = list.getItems();
                long curDateTime = Calendar.getInstance().getTimeInMillis();
                for (TskSchedule tskSchedule1 : scheduleList){
                    Date contactDate = tskSchedule1.getContactDate();
                    if(contactDate != null){
                        long contactDateTime = contactDate.getTime();
                        if(curDateTime > contactDateTime && tskSchedule1.getStatus().equals(0)){
                            //日程过期
                            tskSchedule1.setIsOutDate(1);
                        }
                    }
                }
                commProgressList = tskScheduleService.getAllCommProgress();
                request.setAttribute("scheduleCount",this.getPage().getTotalRowsAmount());
                request.setAttribute("loginUserId",this.getLoginInfo().getUserId());
                if(edit != null && edit.equals("edit")) {
                    request.setAttribute("hasPermission",1);
                }
            }
            return SUCCESS;
        }catch (Exception e){
            logger.error("TskScheduleAction % getScheduleListCard",e);
            return ERROR;
        }
    }

    /**
     * 查看客户意向--小页卡
     * 
     * @return
     */
    public String getCustomerPdtProduct(){
        try {
            if(tskSchedule!=null){
                Map<String,Object> paramMap = new HashMap<String, Object>();
                Boolean isInChargeOf = deptFacadeService.isInChargeOfDepartment();
                if (isInChargeOf){
                    String belongUserIds = deptFacadeService.getUserIdsBelongToManager(this.getLoginInfo().getRoles(),"productIntention");
                    paramMap.put("userIds",belongUserIds);
                }else {
                    paramMap.put("userId",this.getLoginInfo().getUserId());
                }
                paramMap.put("customerId",tskSchedule.getCustomerId());
                //获取用户意向产品信息
                scheduleList = tskScheduleService.getCustomerPDTProductList(paramMap,this.getPage());
                String edit = request.getParameter("edit");
                if(edit != null && edit.equals("edit")){
                    //转到编辑页面
                    request.setAttribute("hasPermission","1");
                }
                request.setAttribute("scheduleList",scheduleList);
                request.setAttribute("recordCount",scheduleList.size());
                request.setAttribute("customerId",tskSchedule.getCustomerId());
            }
            return SUCCESS;
        }catch (Exception e){
            logger.error("TskScheduleAction % getPdtProductCustomer",e);
            return ERROR;
        }
    }

    /**
     * 查询客户意向--小页卡
     * 
     * @return
     */
    public String queryCustomerPdtProduct(){
        try {
            String customerIdStr = request.getParameter("customerId");
            if(StringUtil.isNotEmpty(customerIdStr)){
                Integer customerId = Integer.parseInt(customerIdStr.trim());
                Map<String,Object> paramMap = new HashMap<String, Object>();
                paramMap.put("customerId",customerId);
                Boolean isInChargeOf = deptFacadeService.isInChargeOfDepartment();
                if (isInChargeOf){
                    String belongUserIds = deptFacadeService.getUserIdsBelongToManager(this.getLoginInfo().getRoles(),"productIntention");
                    paramMap.put("userIds",belongUserIds);
                }else {
                    paramMap.put("userId",this.getLoginInfo().getUserId());
                }
                //获取客户意向产品信息
                scheduleList = tskScheduleService.getCustomerPDTProductList(paramMap,this.getPage());
                request.setAttribute("recordCount",this.getPage().getTotalRowsAmount());
                request.setAttribute("scheduleList",scheduleList);
            }
            return SUCCESS;
        }catch (Exception e){
            logger.error("TskScheduleAction % queryPdtProductCustomer",e);
            return ERROR;
        }
    }

    /**
     * 打开安排日程页卡——小页卡
     * 
     * @return
     */
    public String toTskNewScheduleCard(){
        try {
            String customerId = request.getParameter("customerId");
            request.setAttribute("customerId",customerId);
            return SUCCESS;
        }catch (Exception e){
            logger.error("TskScheduleAction % toTskNewScheduleCard",e);
            return ERROR;
        }
    }

    /**
     * 安排日程页卡——小页卡
     *
     * @return
     */
    public void addScheduleByCusIdCard(){
        try {
            HttpServletResponse response = this.getResponse();
            PrintWriter out = response.getWriter();
            try {
                Integer userId = this.getLoginInfo().getUserId();
                if(tskSchedule != null){
                    TskSchedule insertTskSchedule = new TskSchedule();
                    insertTskSchedule.setUserId(userId);
                    insertTskSchedule.setCreateUser(userId);
                    insertTskSchedule.setUpdateUser(userId);
                    insertTskSchedule.setStatus(0);
                    //默认设置沟通进度为第一项
                    Integer no1CommProgressId = tskScheduleService.getNo1CommProgressId();
                    insertTskSchedule.setCommProgressId(no1CommProgressId);
                    insertTskSchedule.setContactDate(tskSchedule.getContactDate());
                    insertTskSchedule.setContactType(tskSchedule.getContactType());
                    insertTskSchedule.setRemark(tskSchedule.getRemark());
                    insertTskSchedule.setCustomerId(tskSchedule.getCustomerId());
                    tskScheduleService.addNewSchedule(insertTskSchedule);
                    out.print("1");
                }
            }finally {
                if(out != null)
                    out.close();
            }
        }catch (Exception e){
            logger.error("TskScheduleAction % addScheduleByCusIdCard",e);
        }
    }

    /**
     * 工作台 日程提醒
     * @return
     */
    public String getAgendaToWorkPlace(){
		try {
            Map<String,Object> parameterMap = new HashMap<String, Object>();
//            Boolean isInChargeOf = deptFacadeService.isInChargeOfDepartment();
//            if (isInChargeOf) {
//                // 当前用户的下属用户
//                String belongUserIds = deptFacadeService.getUserIdsBelongToManager(getLoginInfo().getRoles(),"taskschedule");
//                parameterMap.put("userIds", belongUserIds); // 当前用户所管理的提交申请用户
//            } else {
                // 客户经理
                parameterMap.put("userId" ,this.getLoginInfo().getUserId());
//            }
			Integer scheduleCount = tskScheduleService.getInTimeScheduleCount(parameterMap);
			scheduleList = tskScheduleService.getInTimeScheduleList(parameterMap);
            commProgressList = tskScheduleService.getAllCommProgress();//查找所有沟通进度配置信息
			request.setAttribute("scheduleCount", scheduleCount);
			return SUCCESS;
		} catch (Exception e) {
			logger.error("TskScheduleAction: getAgendaToWorkPlace error:", e);
            return ERROR;
		}
    }
    
    /* getter and setter */
    public TskScheduleService getTskScheduleService() {
        return tskScheduleService;
    }

    public void setTskScheduleService(TskScheduleService tskScheduleService) {
        this.tskScheduleService = tskScheduleService;
    }


    public Date getContactDateFrom() {
        return contactDateFrom;
    }

    public void setContactDateFrom(Date contactDateFrom) {
        this.contactDateFrom = contactDateFrom;
    }

    public Date getContactDateEnd() {
        return contactDateEnd;
    }

    public void setContactDateEnd(Date contactDateEnd) {
        this.contactDateEnd = contactDateEnd;
    }


    public TskSchedule getTskSchedule() {
        return tskSchedule;
    }
    public void setTskSchedule(TskSchedule tskSchedule) {
        this.tskSchedule = tskSchedule;
    }
    public List<TskSchedule> getScheduleList() {
        return scheduleList;
    }
    public void setScheduleList(List<TskSchedule> scheduleList) {
        this.scheduleList = scheduleList;
    }
    public List<Map> getCommProgressList() {
        return commProgressList;
    }
    public void setCommProgressList(List<Map> commProgressList) {
        this.commProgressList = commProgressList;
    }
    public String getCustomerIdString() {
        return customerIdString;
    }
    public void setCustomerIdString(String customerIdString) {
        this.customerIdString = customerIdString;
    }
    public List<Map> getCustomerTypeList() {
        return customerTypeList;
    }
    public void setCustomerTypeList(List<Map> customerTypeList) {
        this.customerTypeList = customerTypeList;
    }
    public Integer getIsPast() {
        return isPast;
    }
    public void setIsPast(Integer isPast) {
        this.isPast = isPast;
    }
    public Integer getCustomerType() {
        return customerType;
    }
    public void setCustomerType(Integer customerType) {
        this.customerType = customerType;
    }

    public CrmCustomerService getCrmCustomerService() {
        return crmCustomerService;
    }

    public void setCrmCustomerService(CrmCustomerService crmCustomerService) {
        this.crmCustomerService = crmCustomerService;
    }

    public CrmCustomer getCrmCustomer() {
        return crmCustomer;
    }

    public void setCrmCustomer(CrmCustomer crmCustomer) {
        this.crmCustomer = crmCustomer;
    }
}
