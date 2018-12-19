/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :拜访记录action
 * Author     :yujh
 * Create Date:May 29, 2012
 */
package com.banger.mobile.webapp.action.visitRecord;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.banger.mobile.domain.Enum.record.EnumRecord;
import com.banger.mobile.domain.model.base.customer.BaseCrmCustomer;
import com.banger.mobile.domain.model.customer.CrmCustomer;
import com.banger.mobile.domain.model.recbistype.RecbizType;
import com.banger.mobile.domain.model.record.RecordInfo;
import com.banger.mobile.domain.model.system.CommProgress;
import com.banger.mobile.domain.model.tskContact.TalkTask;
import com.banger.mobile.domain.model.tskContact.TskContact;
import com.banger.mobile.domain.model.visitRecord.VisitRecord;
import com.banger.mobile.domain.model.visitRecord.VisitRecordInfo;
import com.banger.mobile.facade.customer.CrmCustomerService;
import com.banger.mobile.facade.recbiztype.RecbizTypeService;
import com.banger.mobile.facade.record.RecordInfoService;
import com.banger.mobile.facade.system.CommProgressService;
import com.banger.mobile.facade.tskContact.TskContactService;
import com.banger.mobile.facade.visitRecord.VisitRecordService;
import com.banger.mobile.util.JsUtil;
import com.banger.mobile.util.StringUtil;
import com.banger.mobile.webapp.action.BaseAction;

/**
 * @author yujh
 * @version $Id: VisitRecordAction.java,v 0.1 May 29, 2012 11:08:00 AM Administrator Exp $
 */
public class VisitRecordAction extends BaseAction{

    private static final long serialVersionUID = -1988653458704839184L;
    
    private VisitRecordService  visitRecordService;     //拜访记录service
    private CrmCustomerService  crmCustomerSerivce;     //客户service
    private RecordInfoService   recordInfoService;      //录音service
    private RecbizTypeService   recbizTypeService;      //业务类型service
    private CommProgressService commProgressService;    //沟通进度service
    private List<RecbizType>    recbizTypeList;         //业务类型
    private List<CommProgress>  commProgressList;       //沟通进度
    private VisitRecord         visitRecord;            //拜访记录实体
    private VisitRecordInfo     visitRecordInfo;        //拜访记录信息
    private RecordInfo          recordInfo;             //联系记录
    private CrmCustomer         customer;               //客户实体
    private int recordInfoId;   //获取记录id号
    private String type;        //进入
    private String actionType;
    private TalkTask tskTask;       
    private TskContactService tskContactService;                //联系任务service
    
    public RecordInfo getRecordInfo() {
        return recordInfo;
    }

	public TalkTask getTskTask() {
		return tskTask;
	}

	public void setTskTask(TalkTask tskTask) {
		this.tskTask = tskTask;
	}

	public void setRecordInfo(RecordInfo recordInfo) {
        this.recordInfo = recordInfo;
    }
    /**
     * 新增拜访记录页面
     * @return
     */
    @SuppressWarnings("unused")
    public String showAddVisitRecordPage(){
        try {
            int customerId =customer.getCustomerId();
            String customerName=this.crmCustomerSerivce.getCrmCustomerById(customerId).getCustomerName();
            commProgressList =this.commProgressService.getCommProgressList();
            request.setAttribute("recbizTypeList", recbizTypeList);
            request.setAttribute("commProgressList", commProgressList);
            request.setAttribute("customerName", JsUtil.escapeText(customerName));
            request.setAttribute("customerId", customerId);
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
            customer=this.crmCustomerSerivce.getCrmCustomerById(customerId);
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
            this.visitRecordService.addVisitRecord(visitRecord);
            //更新客户上次联系时间
            if(customer.getLastContactDate()==null || visitRecord.getStartDate().after(customer.getLastContactDate()))
            	crmCustomerSerivce.updateLastContactDate(visitRecord.getCustomerId(), visitRecord.getStartDate(), EnumRecord.VISIT_INFO.getValue());

            String nextContact = request.getParameter("saveNextContact");
            if (nextContact!=null && nextContact.equals("1")) {//下次联系任务
                tskContactService.saveTaskInTalk(tskTask, visitRecord.getCustomerId());
            }
            if(actionType.equals("save")){
                return null;
            }else{
            	//request.setAttribute("customer.customerId", customerId);
                return "addAndAdd";
            }
        } catch (RuntimeException e) {
        	e.printStackTrace();
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
            commProgressList =this.commProgressService.getCommProgressList();//沟通进度
            request.setAttribute("recbizTypeList", recbizTypeList);
            request.setAttribute("commProgressList", commProgressList);
       //     visitRecordInfo =this.visitRecordService.getVisitRecord(43);
            request.setAttribute("type", type);
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
//            if(task!=null&&task.getTaskId()!=null){
//                tskTaskService.updateNextTaskIsDel(task.getTaskId());
//            }
            return SUCCESS;
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            return ERROR;
        }
    }
    /**
     * 下次联系页面
     * @return
     */
    public String showNextTaskPage(){
        try {
            customer =this.crmCustomerSerivce.getCustomerInfo(customer.getCustomerId());
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
        try{
        }catch(Exception e){
            log.error(e.getMessage());
        }
    }
    /**
     * 下次联系信息页面
     * @return
     */
    public String nextTaskUp(){
        try {
            customer =this.crmCustomerSerivce.getCustomerInfo(customer.getCustomerId());
//            task=tskTaskService.getCustomerNextTaskById(customer.getCustomerId(),task.getTaskId(),task.getIsDel());
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
//            task.setUpdateUser(this.getLoginInfo().getUserId());
//            if(task.getTaskStatus()==1){
//                task.setFinishDate(new Date());
//            }
//            task.setEndDate(task.getStartDate());
//            tskTaskService.updateTskTaskContact(task);
//            out.print(task.getTaskId()+","+DateUtil.getDateToString(task.getStartDate())+","+task.getTaskStatus());
        }catch(Exception e){
            log.error(e.getMessage());
        }
    }
    
    /**
     * 初始化客户对应的下次联系任务
     */
    public void initNextContextTask(){
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setCharacterEncoding("UTF-8");
        PrintWriter out =null;
        try{
            out = response.getWriter();
            Integer taskId=null;
//            if(task!=null){
//                if(task.getTaskId()!=null){
//                    taskId=task.getTaskId();
//                }
//            }
//            TskTask tskTask=tskTaskService.getCustomerNextTaskById(customer.getCustomerId(),taskId,0);
//            if(tskTask!=null){
//               out.print(tskTask.getTaskId()+","+DateUtil.getDateToString(tskTask.getStartDate())+","+tskTask.getTaskStatus()+","+1);
//            }
        }catch(Exception e){
            e.printStackTrace();
            log.error(e.getMessage());
        }
    }
    
    public void setVisitRecordService(VisitRecordService visitRecordService) {
        this.visitRecordService = visitRecordService;
    }


    public void setCrmCustomerSerivce(CrmCustomerService crmCustomerSerivce) {
        this.crmCustomerSerivce = crmCustomerSerivce;
    }


    public void setRecbizTypeService(RecbizTypeService recbizTypeService) {
        this.recbizTypeService = recbizTypeService;
    }


    public void setCommProgressService(CommProgressService commProgressService) {
        this.commProgressService = commProgressService;
    }

    public void setRecordInfoService(RecordInfoService recordInfoService) {
        this.recordInfoService = recordInfoService;
    }
    public VisitRecord getVisitRecord() {
        return visitRecord;
    }
    public void setVisitRecord(VisitRecord visitRecord) {
        this.visitRecord = visitRecord;
    }
    public VisitRecordInfo getVisitRecordInfo() {
        return visitRecordInfo;
    }
    public void setVisitRecordInfo(VisitRecordInfo visitRecordInfo) {
        this.visitRecordInfo = visitRecordInfo;
    }
    public int getRecordInfoId() {
        return recordInfoId;
    }
    public void setRecordInfoId(int recordInfoId) {
        this.recordInfoId = recordInfoId;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public CrmCustomer getCustomer() {
        return customer;
    }
    public void setCustomer(CrmCustomer customer) {
        this.customer = customer;
    }
    public String getActionType() {
        return actionType;
    }
    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

	public TskContactService getTskContactService() {
		return tskContactService;
	}

	public void setTskContactService(TskContactService tskContactService) {
		this.tskContactService = tskContactService;
	}
    
}
