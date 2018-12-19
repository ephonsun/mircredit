/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :录音操作Action
 * Author     :Administrator
 * Version    :1.0
 * Create Date:May 3, 2012
 */
package com.banger.mobile.webapp.action.record;

import com.banger.mobile.PageUtil;
import com.banger.mobile.constants.FileUploadPathConstants;
import com.banger.mobile.domain.Enum.record.EnumRecord;
import com.banger.mobile.domain.model.base.customer.BaseCrmCustomer;
import com.banger.mobile.domain.model.crmModuleExport.CrmModuleExport;
import com.banger.mobile.domain.model.customer.CrmCustomer;
import com.banger.mobile.domain.model.customer.CustomerExportBar;
import com.banger.mobile.domain.model.customer.CustomerExportContext;
import com.banger.mobile.domain.model.dept.SysDept;
import com.banger.mobile.domain.model.microTask.TskSchedule;
import com.banger.mobile.domain.model.recbistype.RecbizType;
import com.banger.mobile.domain.model.record.*;
import com.banger.mobile.domain.model.system.CommProgress;
import com.banger.mobile.domain.model.tskContact.TskContact;
import com.banger.mobile.domain.model.visitRecord.VisitRecordInfo;
import com.banger.mobile.facade.crmModuleExport.CrmModuleExportService;
import com.banger.mobile.facade.customer.CrmCustomerService;
import com.banger.mobile.facade.customer.CustomerExportService;
import com.banger.mobile.facade.dept.DeptFacadeService;
import com.banger.mobile.facade.impl.record.RecordExportServiceImpl;
import com.banger.mobile.facade.impl.record.RecordInfoServiceImpl;
import com.banger.mobile.facade.log.OpeventLogService;
import com.banger.mobile.facade.microTask.TskScheduleService;
import com.banger.mobile.facade.recbiztype.RecbizTypeService;
import com.banger.mobile.facade.record.RecordExportService;
import com.banger.mobile.facade.record.RecordInfoService;
import com.banger.mobile.facade.specialDataAuth.SpecialDataAuthService;
import com.banger.mobile.facade.system.CommProgressService;
import com.banger.mobile.facade.template.CrmTemplateService;
import com.banger.mobile.facade.tskContact.TskContactService;
import com.banger.mobile.facade.tskContact.TskPlanService;
import com.banger.mobile.facade.visitRecord.VisitRecordService;
import com.banger.mobile.util.DateUtil;
import com.banger.mobile.util.ExcelUtil;
import com.banger.mobile.util.JsUtil;
import com.banger.mobile.util.StringUtil;
import com.banger.mobile.webapp.action.BaseAction;
import it.sauronsoftware.jave.*;
import net.sf.json.JSONObject;
import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author huangkai
 * @version $Id: RecordAction.java,v 0.1 May 3, 2012 2:22:36 PM huangkai Exp $
 */
public class RecordInfoAction extends BaseAction {

    private static final long        serialVersionUID = -5111617661135032228L;
    
    private TskContactService               tskContactService;

    private RecordInfoService        recordInfoService;                       //录音信息service

    private PageUtil<RecordInfoBean> recordInfoList;                          //活动分页对象
    private List<RecordExportBean> recordExportList;

    private RecbizTypeService        recbizTypeService;                       //业务类型service
    
    private CommProgressService      commProgressService;                     //沟通进度service
    
    private CrmTemplateService        temp;     //自定义模版
    
    private List<RecbizType> recbizTypeList= new ArrayList<RecbizType>();     //业务类型集合
    
    private RecordInfo               recordInfo;                              //查询条件bean(旧)
    
    private RecordQueryBean          queryBean;                               //查询条件bean(新)          
    
    private RecordDetail             recordDetail;                            //根据id查询返回的结果集
    
    private VisitRecordInfo          visitRecordInfo;                         //拜访实体类
    
    private List<CommProgress>       commProgressList;                        //沟通进度集合
    
    private DeptFacadeService        deptFacadeService;                       //权限service
    
    private CrmCustomerService       crmCustomerService;                      //客户service   
    private RecordExportService      recordExportService;                     //联系记录导出service
    private CrmModuleExportService   crmModuleExportService;                  //导出保存默认字段
    private VisitRecordService       visitRecordService;                      //拜访记录service
    private SpecialDataAuthService	 specialDataAuthService;
    private TskScheduleService tskScheduleService;                            //日程安排service
    private List<String> feilds = new ArrayList<String>();                 //勾选的字段
    
    
    private String calltype;
    
    private Integer type;
    
    private String recordIds;
    
    HttpServletResponse response = ServletActionContext.getResponse();
    
    private Integer recordId;   //录音ID
    
    private Integer isCanceled; //录音是否有效
    
    private String ExportfileName;//批量导出录音zip名
    
    private String toType;//首页(main)接口传递参数
    
    private OpeventLogService opeventLogService;//操作日志service
    
    private String roleName; //获取当前用户的角色来判断查询条件是否要显示客户经理
    
    private String tskCustomerName;

    private Integer userId;//用户ID
    private String userName;//用户姓名
    private CustomerExportService customerExportService;
    private BaseCrmCustomer baseCrmCustomer;    //客户基础数据
    private Map<String, String>   baseColumn;
    private List<TalkRecordInfo> recentRecords;			//最近10条录音记录
    
    DateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    DateFormat df2=new SimpleDateFormat("yyyy-MM-dd 23:59:59");
    DateFormat df3=new SimpleDateFormat("yyyy-MM-dd");
    DateFormat df4=new SimpleDateFormat("yyyyMMdd");

    private Integer successCount;//导出zip文件包成功条数
    
    private String oldRemark;
    private TskPlanService tskPlanService;//任务计划service

    public TskScheduleService getTskScheduleService() {
        return tskScheduleService;
    }

    public void setTskScheduleService(TskScheduleService tskScheduleService) {
        this.tskScheduleService = tskScheduleService;
    }

    public RecordInfo getRecordInfo() {
        return recordInfo;
    }

    public void setTskPlanService(TskPlanService tskPlanService) {
        this.tskPlanService = tskPlanService;
    }

    public void setRecordInfo(RecordInfo recordInfo) {
        this.recordInfo = recordInfo;
    }
    
    

    public List<TalkRecordInfo> getRecentRecords() {
		return recentRecords;
	}

	public void setRecentRecords(List<TalkRecordInfo> recentRecords) {
		this.recentRecords = recentRecords;
	}

	/**
     * 录音列表查询分页
     * @return
     */
    @SuppressWarnings("deprecation")
    public String showRecordInfoListPage() {
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            String fenye = request.getParameter("fenye");
            String BelongToType = request.getParameter("BelongToType");
            String userIds = request.getParameter("userIds");
            String deptIds = request.getParameter("deptIds");
            String containSub=request.getParameter("containSub");//记录是否有选择包含下属机构 否1 是0
            String callTypeCodes="";
            if(queryBean!=null){
                if(queryBean.getCustomerTypeId()==1){//归属给我的
                    if(BelongToType.equals("brMine")){
                        map.put("belongUserId",this.getLoginInfo().getUserId());
                        map.put("userIds",this.getLoginInfo().getUserId());
                    }else{
                        map.put("belongUserId",this.getLoginInfo().getUserId());
                    }
                }else if(queryBean.getCustomerTypeId()==2){//共享给我的
                    Map<String, Object> m =new HashMap<String, Object>();
                    m.put("shareUerId",this.getLoginInfo().getUserId() );
                    if(BelongToType.equals("brMine")){
                        map.put("shareUerId",this.recordInfoService.getIdsShareUerId(m));
                        map.put("userIds",this.getLoginInfo().getUserId());
                    }else{
                        String ids = this.recordInfoService.getIdsShareUerId(m);
                        if(ids.length()>0){
                            map.put("shareUerId",ids);
                        }else{
                            map.put("shareUerId",0.1);//当没有共享客户时插入0.1 使客户查询出来为空记录
                        }
                    }
                }else{
                    if(BelongToType.equals("brMine")){
                        map.put("userIds", getLoginInfo().getUserId());
                    }if(BelongToType.equals("brAll")){
                    	map.put("userIds",deptFacadeService.getUserIdsBelongToManager(getLoginInfo().getRoles(),"record"));
                    }else if(BelongToType.equals("brUser")){
                    	if(userIds.equals("")){
                    		if(deptFacadeService.isInChargeOfDepartment()){ 
                    			map.put("userIds",deptFacadeService.getUserIdsBelongToManager(getLoginInfo().getRoles(),"record"));
                    		}else{
                    			map.put("userIds",getLoginInfo().getUserId());
                    		}
                    	}else{
                    		if(deptFacadeService.isInChargeOfDepartment()){ 
                    			map.put("userIds",userIds);
                    		}else{
                    			map.put("userIds",getLoginInfo().getUserId());
                    		}
                    	}
                    }else if(BelongToType.equals("brDept")&&containSub.equals("1")){
                        if(deptIds.equals("")){
                        	if(deptFacadeService.isInChargeOfDepartment()){
                        		map.put("deptIds",deptFacadeService.getInChargeOfStringDeptIds(this.getLoginInfo().getUserId()));
                    		}else{
                    			map.put("userIds",getLoginInfo().getUserId());
                    		}
                        }else{
                            map.put("deptIds",getContainSubDeptIds(deptIds));
                        }
                    }else if(BelongToType.equals("brDept")&&containSub.equals("0")){
                        if(deptIds.equals("")){
                            map.put("userIds", getLoginInfo().getUserId());
                        }else{
                            map.put("deptIds",deptIds);
                        }
                    }else{
                        map.put("userIds", getLoginInfo().getUserId());
                    }
                }
            }else{
                if(toType!=null&&toType.equals("1")){//托盘或者首页进入设置初始条件 未读留言
                    callTypeCodes+="6";
                    map.put("notExistRecord", 1);
                }else if(toType!=null&&toType.equals("2")){//未接来电
                    callTypeCodes+="3";
                    map.put("existRecord", 1);
                }
                map.put("userIds",this.getLoginInfo().getUserId());
            }
            recordInfoList = recordInfoService.getRecordInfoPage(map, this.getPage(),type,queryBean,callTypeCodes);
            
            recbizTypeList = recbizTypeService.getRecbizTypeForPad();
            commProgressList = commProgressService.getCommProgressList();
            request.setAttribute("toType", toType);
            request.setAttribute("queryBean", queryBean);
            request.setAttribute("recbizTypeList", recbizTypeList);
            request.setAttribute("count", String.valueOf(this.getPage().getTotalRowsAmount()));
            request.setAttribute("type", type);
            request.setAttribute("dataCode", this.getLoginInfo().getDataCompetence());
            String roleIds=StringUtil.getIdsString(getLoginInfo().getRoles());
            request.setAttribute("dataAuth", specialDataAuthService.getSysDataAuthCondition(roleIds,"record"));
            if(fenye!=null&&fenye.equals("1")){
                return "toPage";
            }else{
                return SUCCESS;
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("RecRecordInfoAction showRecordInfoListPage error:" + e.getMessage());
            return ERROR;
        }
    }
    
    /**
     * 任务管理模块查询指定联系记录
     * @return
     */
    @SuppressWarnings("deprecation")
    public String showRecordListForTsk(){
    	try{
    		String customerId = request.getParameter("customerId");
    		String number = request.getParameter("number");
    		String fenye = request.getParameter("fenye");
    		Map<String, Object> map = new HashMap<String, Object>();
    		if(customerId==null) customerId = "0";
    		if(customerId != null &&!customerId.equals("0")){
    			map.put("tskCusId", customerId); 
    			tskCustomerName = crmCustomerService.getCustomerInfo(Integer.parseInt(customerId)).getCustomerName();
    			tskCustomerName = JsUtil.escapeText(tskCustomerName);
    			request.setAttribute("tskCusId", customerId);
    		}
    		if(number != null && customerId.equals("0")){
    			map.put("tskPhone", number);
    			tskCustomerName = number;
    			request.setAttribute("tskCusId", 0);
    		}
    		String taskId = request.getParameter("contactId");
    		if(taskId!=null && !taskId.equals("")){
    		    TskContact t = tskContactService.getTskContactById(Integer.valueOf(taskId));
    		    map.put("startDate", DateUtil.getDateToString(t.getStartDate())+" 00:00:00");
    		    map.put("endDate", DateUtil.getDateToString(t.getEndDate())+" 23:59:59");
    		    request.setAttribute("contactId", taskId);
    		}
    		recordInfoList = recordInfoService.getTskRecordInfoPage(map, this.getPage());
    		request.setAttribute("count", String.valueOf(this.getPage().getTotalRowsAmount()));
			if(fenye!=null&&fenye.equals("1")){
	            return "toPage";
	        }else{
	            return SUCCESS;
	        }
    	}catch(Exception e){
    		e.printStackTrace();
    		 log.error("RecRecordInfoAction showRecordListForTsk error:" + e.getMessage());
             return ERROR;
    	}
    }

    /**
     * 归档录音列表查询分页
     * @return
     */
    @SuppressWarnings("deprecation")
    public String showArchiveRecordInfoListPage() {
        try {
            Map<String, Object> parameterMap = new HashMap<String, Object>();
            //插入form表单条件
            if(recordInfo!=null){
                parameterMap.put("recordNo", recordInfo.getRecordNo().trim());
                if(recordInfo.getCustomerName()!="姓名、电话"){//查看从页面返回的客户是不是默认提示
                    parameterMap.put("customerName", recordInfo.getCustomerName().trim());
                }else{
                    recordInfo.setCustomerName("");//返回页面前先清空不然回到页面时客户搜索条件有问题
                }
                parameterMap.put("idCard", recordInfo.getIdCard().trim());
                parameterMap.put("remark", recordInfo.getRemark().trim());
                if(recordInfo.getBizType()!=null){
                    parameterMap.put("bizType", recordInfo.getBizType());
                }
                parameterMap.put("isCanceled", recordInfo.getIsCanceled());
                if(recordInfo.getStartDate()!=null){//判断 如果时间不选择 format不能传空值
                    parameterMap.put("startDate",df.format(recordInfo.getStartDate()));
                    //将时间返回到查询条件前format
                    recordInfo.setStartDate(recordInfo.getStartDate());
                }
                if(recordInfo.getEndDate()!=null){
                    parameterMap.put("endDate", df2.format(queryBean.getEndDate()));
                    //将时间返回到查询条件前format
                    recordInfo.setEndDate(recordInfo.getEndDate());
                }
                if(recordInfo.getUserName()!=null){
                    if(recordInfo.getUserName().equals("1")){//我的
                        parameterMap.put("userName", getLoginInfo().getUserId());
                    }else{//下属的
                        parameterMap.put("userName", userId);
                    }
                }
                parameterMap.put("isKnowCustomer",recordInfo.getIsKnowCustomer());//已知，未知客户条件，sql（根据1，2来查询客户和电话是否已知或未知）
                if(recordInfo.getCallType()!=null&&recordInfo.getCallType()==0){
                    parameterMap.put("forcallType", 1);//当类型选择为通话记录时 SQL查询between 1 and 3
                }else{
                    parameterMap.put("callType", recordInfo.getCallType());
                }
            }
            parameterMap.put("recordInfoId", "1");//随意设置一个值在sql里添加条件。已删除和未归档的排除
            parameterMap.put("permission",deptFacadeService.getUserIdsBelongToManager(getLoginInfo().getRoles(),"record"));
            recordInfoList = recordInfoService.getArchiveRecordInfoPage(parameterMap, this.getPage());
            recbizTypeList = recbizTypeService.getRecbizTypeForPad();            
            request.setAttribute("count", String.valueOf(this.getPage().getTotalRowsAmount()));
            int nowPageSize=0;
            for(int i=0;i<recordInfoList.getPage().getPageSize();i++){
                nowPageSize++;
                if(recordInfoList.getPage().getTotalRowsAmount()<((recordInfoList.getPage().getCurrentPage()-1)*recordInfoList.getPage().getPageSize()+nowPageSize)){
                    break ;
                }
                if(recordInfoList.getItems().get(i).getCallTime()!=null&&recordInfoList.getItems().get(i).getCallTime().length()!=0){
                    recordInfoList.getItems().get(i).setCallTime(changTime(recordInfoList.getItems().get(i).getCallTime()));//修改时长格式
                }
                if(recordInfoList.getItems().get(i).getCallTypeName().equals(EnumRecord.CALL_OUT.getValue())||recordInfoList.getItems().get(i).getCallTypeName().equals(EnumRecord.CALL_IN.getValue())||recordInfoList.getItems().get(i).getCallTypeName().equals(EnumRecord.MISSED_CALLS.getValue())||recordInfoList.getItems().get(i).getCallTypeName().equals(EnumRecord.UNREAD_MESSAGE.getValue())){
                    recordInfoList.getItems().get(i).setCallTypeName(EnumRecord.CALL_INFO.getValue());
                }
            }
            request.setAttribute("recbizTypeList", recbizTypeList);
            request.setAttribute("type", 4);
            return SUCCESS;
        } catch (Exception e) {
            log.error("RecRecordInfoAction showArchiveRecordInfoListPage error:" + e.getMessage());
            return ERROR;
        }
    }
    
    /**
     * 查询短信彩信页面
     */
    public String showSMSList(){
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            String fenye = request.getParameter("fenye");
            String BelongToType = request.getParameter("BelongToType");
            String userIds = request.getParameter("userIds");
            String deptIds = request.getParameter("deptIds");
            String containSub=request.getParameter("containSub");//记录是否有选择操作者 机构 否1 是0
            String callTypeCodes="";
            if(queryBean!=null){
                if(queryBean.getCustomerTypeId()==1){//归属给我的
                    if(BelongToType.equals("brMine")){
                        map.put("belongUserId",this.getLoginInfo().getUserId());
                        map.put("userIds",this.getLoginInfo().getUserId());
                    }else{
                        map.put("belongUserId",this.getLoginInfo().getUserId());
                    }
                }else if(queryBean.getCustomerTypeId()==2){//共享给我的
                    Map<String, Object> m =new HashMap<String, Object>();
                    m.put("shareUerId",this.getLoginInfo().getUserId() );
                    if(BelongToType.equals("brMine")){
                        map.put("shareUerId",this.recordInfoService.getIdsShareUerId(m));
                        map.put("userIds",this.getLoginInfo().getUserId());
                    }else{
                        String ids = this.recordInfoService.getIdsShareUerId(m);
                        if(ids.length()>0){
                            map.put("shareUerId",ids);
                        }else{
                            map.put("shareUerId",0.1);//当没有共享客户时插入0.1 使客户查询出来为空记录
                        }
                    }
                }else{
                	if(BelongToType.equals("brMine")){
                        map.put("userIds", getLoginInfo().getUserId());
                    }else if(BelongToType.equals("brUser")){
                    	if(userIds.equals("")){
                    		if(deptFacadeService.isInChargeOfDepartment()){ 
                    			map.put("userIds",deptFacadeService.getUserIdsBelongToManager(getLoginInfo().getRoles(),"record"));
                    		}else{
                    			map.put("userIds",getLoginInfo().getUserId());
                    		}
                    	}else{
                    		if(deptFacadeService.isInChargeOfDepartment()){ 
                    			map.put("userIds",userIds);
                    		}else{
                    			map.put("userIds",getLoginInfo().getUserId());
                    		}
                    	}
                    }else if(BelongToType.equals("brDept")&&containSub.equals("1")){
                        if(deptIds.equals("")){
                        	if(deptFacadeService.isInChargeOfDepartment()){
                        		map.put("deptIds",deptFacadeService.getInChargeOfStringDeptIds(this.getLoginInfo().getUserId()));
                    		}else{
                    			map.put("userIds",getLoginInfo().getUserId());
                    		}
                        }else{
                            map.put("deptIds",getContainSubDeptIds(deptIds));
                        }
                    }else if(BelongToType.equals("brDept")&&containSub.equals("0")){
                        if(deptIds.equals("")){
                            map.put("userIds", getLoginInfo().getUserId());
                        }else{
                            map.put("deptIds",deptIds);
                        }
                    }else{
                        map.put("userIds", getLoginInfo().getUserId());
                    }
                }
            }else{
                if(toType!=null&&toType.equals("1")){//托盘或者首页进入设置初始条件 未读留言
                    callTypeCodes+="6";
                    map.put("notExistRecord", 1);
                }else if(toType!=null&&toType.equals("2")){//未接来电
                    callTypeCodes+="3";
                    map.put("existRecord", 1);
                }
                map.put("userIds",this.getLoginInfo().getUserId());
            }
            recordInfoList = recordInfoService.getRecordInfoPage(map, this.getPage(),type,queryBean,callTypeCodes);
            recbizTypeList = recbizTypeService.getRecbizTypeForPad();
            request.setAttribute("toType", toType);
            request.setAttribute("queryBean", queryBean);
            request.setAttribute("recbizTypeList", recbizTypeList);
            request.setAttribute("count", String.valueOf(this.getPage().getTotalRowsAmount()));
            request.setAttribute("type", type);
            request.setAttribute("dataCode", this.getLoginInfo().getDataCompetence());
            String roleIds=StringUtil.getIdsString(getLoginInfo().getRoles());
            request.setAttribute("dataAuth", specialDataAuthService.getSysDataAuthCondition(roleIds,"record"));
            if(fenye!=null&&fenye.equals("1")){
                return "toPage";
            }else{
                return SUCCESS;
            }
        } catch (Exception e) {
            log.error("RecRecordInfoAction showSMSList error:" + e.getMessage());
            return ERROR;
        }
    }
    
    /**
     * 根据ID删除录音信息（伪删除）
     * @return  String
     */
    public void deleteRecordInfoById(){
        try {
            if(recordId!=null){
                RecordInfo recordInfo=new RecordInfo();
                recordInfo=recordInfoService.queryAllById(recordId);
                recordInfoService.deleteRecordInfo(recordId);
                if(recordInfo.getCallType()==1||recordInfo.getCallType()==2||recordInfo.getCallType()==4){//修改联系任务
                    if(recordInfo.getCustomerId()!=null&&recordInfo.getCustomerId()!=0){
//                        int i = tskTaskTargetService.getTaskTargetDateCustomer(recordInfo.getCustomerId());//根据客户ID查询此客户的联系记录的时间是否在任务的时间范围内
//                        if(i==0){
//                            tskTaskTargetService.updateTargetCustomerById(recordInfo.getCustomerId(), this.getLoginInfo().getUserId(), 0, 1);
//                        }
                    }
                }
            }
            //opeventLogService.addOpeventLog(EnumRecord.RECORD_NAME.getValue(),EnumRecord.RECORD_DEL.getValue(), 1, "");
        } catch (Exception e) {
            //opeventLogService.addOpeventLog(EnumRecord.RECORD_NAME.getValue(),EnumRecord.RECORD_DEL.getValue(), 0, "");
            log.error("RecRecordInfoAction deleteRecordInfoById error:" + e.getMessage());
        }
    } 

    /**
     * 删除多条录音
     * @return String
     */
    public void deleteAll(){
        try {
            String cusIds[]=request.getParameter("cusIds").split(",");
            String recId[]=recordIds.split(",");
            for(int i=0;i<recId.length;i++){
                recordInfoService.deleteRecordInfo(Integer.parseInt(recId[i]));
                if(cusIds[i]!=null&&!cusIds[i].equals("0")){//修改联系任务
//                    int j = tskTaskTargetService.getTaskTargetDateCustomer(Integer.parseInt(cusIds[i]));//根据客户ID查询此客户的联系记录的时间是否在任务的时间范围内
//                    if(j==0){
//                        tskTaskTargetService.updateTargetCustomerById(Integer.parseInt(cusIds[i]), this.getLoginInfo().getUserId(), 0, 1);
//                    }
                }
            }
            //opeventLogService.addOpeventLog(EnumRecord.RECORD_NAME.getValue(), EnumRecord.RECORD_DELS.getValue(), 1, "");
        } catch (Exception e) {
            //opeventLogService.addOpeventLog(EnumRecord.RECORD_NAME.getValue(), EnumRecord.RECORD_DELS.getValue(), 0, "");
            log.error("RecRecordInfoAction deleteAll error:" + e.getMessage());
        }
    } 
    
    /**
     * 修改录音是否有效
     * @return String
     */
    public String updateRecordIsCanceledById(){
        try {
            if(recordId!=null){
                recordInfoService.updateRecordCanceled(recordId,isCanceled);
            }
            //opeventLogService.addOpeventLog(EnumRecord.RECORD_NAME.getValue(),EnumRecord.RECORDISCANCELED_UPDATE.getValue(), 1, "");
            return SUCCESS;
        } catch (Exception e) {
            //opeventLogService.addOpeventLog(EnumRecord.RECORD_NAME.getValue(),EnumRecord.RECORDISCANCELED_UPDATE.getValue(), 0, "");
            log.error("RecRecordInfoAction updateRecordIsCanceledById error:" + e.getMessage());
            return ERROR;
        }
    }
    
    /**
     * 详情 根据ID查询录音信息
     * @return String
     */
    public String queryRecordById(){
        try {
            if(calltype!=null&&calltype.equals("visit")){
                if(recordId!=null){
                    visitRecordInfo=visitRecordService.getVisitRecord(recordId);
                }
                return "tobf";
            }else{
                if(recordId!=null){
                    recordDetail=recordInfoService.getRecordInfoById(recordId);                    
                }
                request.setAttribute("type", type);
                return SUCCESS;
            }
        } catch (Exception e) {
            log.error("RecRecordInfoAction queryRecordById error:" + e.getMessage());
            return ERROR;
        }
    }
    
    /**
     * 详情 修改录音是否有效
     * @return String
     */
    public String updateRecordDetailIsCanceledById(){
        try {
            if(recordId!=null){
                recordInfoService.updateRecordCanceled(recordId,isCanceled);
                recbizTypeList = recbizTypeService.getRecbizTypeForPad();
                commProgressList=commProgressService.getCommProgressList();
                recordDetail=recordInfoService.getRecordInfoById(recordId);
            }
            //opeventLogService.addOpeventLog(EnumRecord.RECORD_NAME.getValue(),EnumRecord.RECORDDETAIL_DEL.getValue(), 1, "");
            return SUCCESS;
        } catch (Exception e) {
            //opeventLogService.addOpeventLog(EnumRecord.RECORD_NAME.getValue(),EnumRecord.RECORDDETAIL_DEL.getValue(), 0, "");
            log.error("RecRecordInfoAction updateRecordDetailIsCanceledById error:" + e.getMessage());
            return ERROR;
        }
    }
    
    /**
     * 详情 根据ID查询归档录音信息
     * @return String
     */
    public String queryArchiveRecordById(){
        try {
            if(recordId!=null){
                recordDetail=recordInfoService.getRecordInfoById(recordId);
            }
            return SUCCESS;
        } catch (Exception e) {
            log.error("RecRecordInfoAction queryArchiveRecordById error:" + e.getMessage());
            return ERROR;
        }
    }
    
    /**
     * 根据id设置记录已读
     */
    public void readedRec(){
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/html;charset=utf-8");
        try {
            PrintWriter out = response.getWriter();
            recordInfoService.updateRecIsRead(recordId);
            //opeventLogService.addOpeventLog(EnumRecord.RECORD_NAME.getValue(), EnumRecord.RECORD_READED.getValue(), 1, "");
            out.print("1");
            out.close();
        } catch (Exception e) {
            //opeventLogService.addOpeventLog(EnumRecord.RECORD_NAME.getValue(), EnumRecord.RECORD_READED.getValue(), 0, "");
            log.error("RecRecordInfoAction readedRec error:" + e.getMessage());
        }
    }

    /**
     * 根据id设置多条记录已读
     */
    public void readedRecs(){
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/html;charset=utf-8");
        try {
            PrintWriter out = response.getWriter();
            String recId[]=recordIds.split(",");
            for(int i=0;i<recId.length;i++){
                recordInfoService.updateRecIsRead(Integer.parseInt(recId[i]));
            }
            //opeventLogService.addOpeventLog(EnumRecord.RECORD_NAME.getValue(), EnumRecord.RECORD_READSED.getValue(), 1, "");
            out.print("1");
            out.close();
        } catch (Exception e) {
            //opeventLogService.addOpeventLog(EnumRecord.RECORD_NAME.getValue(), EnumRecord.RECORD_READSED.getValue(), 0, "");
            log.error("RecRecordInfoAction readedRecs error:" + e.getMessage());
        }
    }
    
    /**
     * 播放前复制录音文件到服务器临时播放目录下
     */
    public void openRecord(){
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/html;charset=utf-8");
        try {
        PrintWriter out = response.getWriter();
        String filePath=recordInfo.getFilePath();
        String fileName=recordInfo.getFileName();
        if(!filePath.isEmpty()&&!fileName.isEmpty()){
            String path = this.getRootPath()+"/Records";
            File file=new File(filePath+"/"+fileName);
            file.setLastModified(System.currentTimeMillis());
            File newFile=new File(path+"/"+fileName);
            FileUtils.copyFile(file,newFile);
        }
        out.print("../Records/" + recordInfo.getFileName());
        out.close();
        } catch (Exception e) {
            log.error("RecRecordInfoAction openRecord action error:"+e.getMessage());
        }
    }
    
    /**
     * 录音amr转成wav
     */
    public String changeRecCode(String oldFileName){
        int i=0;
        String newFileName = "";
        try {
            i = oldFileName.lastIndexOf(".");
            newFileName = oldFileName.substring(0,i+1)+"wav";
            File source = new File(oldFileName);
            File target = new File(newFileName);
            AudioAttributes audio = new AudioAttributes();
            audio.setCodec("libmp3lame");
            audio.setBitRate(new Integer(56000));
            audio.setChannels(new Integer(1));
            audio.setSamplingRate(new Integer(22050));
            VideoAttributes video = new VideoAttributes();
            video.setCodec(VideoAttributes.DIRECT_STREAM_COPY);
            EncodingAttributes attrs = new EncodingAttributes();
            attrs.setFormat("wav");
            attrs.setAudioAttributes(audio);
            attrs.setVideoAttributes(video);
            Encoder encoder = new Encoder();
            encoder.encode(source, target, attrs);
            i = oldFileName.lastIndexOf("\\");
            return newFileName.substring(i+1,newFileName.length());
        } catch (IllegalArgumentException e) {
            log.error("RecRecordInfoAction changeRecCode action error:"+e.getMessage());
        }
    catch (InputFormatException e) {
        log.error("RecRecordInfoAction changeRecCode action error:"+e.getMessage());
    }
    catch (EncoderException e) {
        log.error("RecRecordInfoAction changeRecCode action error:"+e.getMessage());
    }
    i = oldFileName.lastIndexOf("/");
    return newFileName.substring(i+1,newFileName.length());
    }
    
    /**
     * 编辑沟通记录
     */
    public String queryRecordConnectById(){
        try {
            if(calltype.equals("visit")){
                return "tovisitRecord";
            }else{
                recordDetail=recordInfoService.getRecordInfoById(recordId);
                recbizTypeList = recbizTypeService.getRecbizTypeForPad();
                commProgressList=commProgressService.getCommProgressList();
                request.setAttribute("scheduleId", request.getParameter("scheduleId"));
                request.setAttribute("type", type);
                return SUCCESS;
            }
        } catch (Exception e) {
            log.error("RecRecordInfoAction queryRecordConnectById error:" + e.getMessage());
            return ERROR;
        }
    }
    
    /**
     * 编辑联系记录
     * @return String
     */
    public void saveRecordConnect(){
        try {
            if(recordDetail!=null){
                /*---------任务管理自动完成处理 start-------------*/
//                if(oldRemark.equals("") && recordDetail.getRemark()!=null && !recordDetail.getRemark().equals("")){
//                    //添加备注 修改任务完成状态
//                    RecordInfo info = recordInfoService.queryAllById(Integer.parseInt(recordDetail.getRecordInfoId()));
//                    if(info.getCallType()==1||info.getCallType()==2||info.getCallType()==4){//呼入、呼出、座谈记录
//                        String date = DateUtil.getDateToString(info.getStartDate());
//                        if(info.getCustomerId()!=null && info.getCustomerId()>0){//内部客户
//                            tskPlanService.finishPlanInTalk(info.getCustomerId(),date,info.getUserId());//完成计划
//                            tskContactService.finishTaskInTalk(info.getCustomerId(),date,info.getUserId());//完成任务
//                        }else{
//                            if(info.getCallType()!=4 && info.getRemotePhone()!=null
//                                    && !info.getRemotePhone().equals("")){//外部客户
//                                tskPlanService.finishPlanInTalk(info.getRemotePhone(),date,info.getUserId());//完成计划
//                                tskContactService.finishTaskInTalk(info.getRemotePhone(),date,info.getUserId());//完成任务
//                            }
//                        }
//                    }
//                }
                /*---------任务管理自动完成处理 end-------------*/
                //更改日程的沟通进度
                if (recordDetail.getCommProgress() != null){
                    String scheduleId = request.getParameter("scheduleId");
                    if (!StringUtil.isNullOrEmpty(scheduleId)){
                        TskSchedule schedule = new TskSchedule();
                        schedule.setScheduleId(Integer.parseInt(scheduleId));
                        schedule.setCommProgressId(Integer.parseInt(recordDetail.getCommProgress()));
                        tskScheduleService.updateSchedule(schedule);
                    }
                }
                recordInfoService.updateRecordConnect(recordDetail);
            }
        } catch (Exception e) {
//            e.printStackTrace();
            log.error("RecRecordInfoAction saveRecordConnect error:" + e.getMessage());
        }
    }
    
    /**
     * 导出记录Excel
     * @return
     */
    public void downExcel(){
        OutputStream outputStream=null;
        HSSFWorkbook workbook=null;
        List<RecordInfoBean> rbean=new ArrayList<RecordInfoBean>();
        if(type!=4){
            Map<String, Object> parameterMap = new HashMap<String, Object>();
            //根据type参数来判断当前记录类型是什么 添加到map里
            if(type!=null&&type==2){
                parameterMap.put("callType", "4");
            }else if(type!=null&&type==3){
                parameterMap.put("callType", "5");
            }else if(type!=null&&type==0){
                if(recordInfo!=null&&recordInfo.getCallType()!=null){
                    if(recordInfo.getCallType()==0){
                        parameterMap.put("forcallType", type);//如果类型是通话类型（呼入，呼出，未接）则在sql里查询between..and
                    }else{
                        parameterMap.put("callType", recordInfo.getCallType());
                    }
                }
            }else if(type!=null&&type==1){
                if(recordInfo!=null&&recordInfo.getCallType()!=null){
                    parameterMap.put("callType", recordInfo.getCallType());
                }else{
                    parameterMap.put("forcallType", type); 
                }
            }
            //插入form表单条件
            if(recordInfo!=null){
                parameterMap.put("recordNo", recordInfo.getRecordNo());
                parameterMap.put("customerName", recordInfo.getCustomerName());
                parameterMap.put("idCard", recordInfo.getIdCard());
                parameterMap.put("remark", recordInfo.getRemark());
                parameterMap.put("bizType", recordInfo.getBizType());
                parameterMap.put("isCanceled", recordInfo.getIsCanceled());
                if(recordInfo.getStartDate()!=null){//判断 如果时间不选择 format不能传空值
                    parameterMap.put("startDate",df.format(recordInfo.getStartDate()));
                    //将时间返回到查询条件前format
                    Date date;
                    try {
                        date = df.parse(df.format(recordInfo.getStartDate()));
                        recordInfo.setStartDate(date);
                    } catch (ParseException e) {
                        log.error("",e);
                    }
                }
                if(recordInfo.getEndDate()!=null){
                    parameterMap.put("endDate", df2.format(recordInfo.getEndDate()));
                    //将时间返回到查询条件前format
                    Date date;
                    try {
                        date = df.parse(df.format(recordInfo.getEndDate()));
                        recordInfo.setEndDate(date);
                    } catch (ParseException e) {
                        log.error("",e);
                    }
                }
                parameterMap.put("userName", recordInfo.getUserName());
                parameterMap.put("isKnowCustomer",recordInfo.getIsKnowCustomer());//已知，未知客户条件，sql（根据1，2来查询客户和电话是否已知或未知）
            }
            parameterMap.put("recordInfoId", "1");//随意设置一个值在sql里添加条件。已删除和已归档的排除
            parameterMap.put("permission",deptFacadeService.getUserIdsBelongToManager(getLoginInfo().getRoles(),"record"));
            rbean = recordInfoService.getRecordInfo(parameterMap);
        }else{
            Map<String, Object> parameterMap = new HashMap<String, Object>();
            //插入form表单条件
            if(recordInfo!=null){
                parameterMap.put("recordNo", recordInfo.getRecordNo());
                parameterMap.put("idCard", recordInfo.getIdCard());
                parameterMap.put("remark", recordInfo.getRemark());
                parameterMap.put("bizType", recordInfo.getBizType());
                parameterMap.put("isCanceled", recordInfo.getIsCanceled());
                if(recordInfo.getStartDate()!=null){//判断 如果时间不选择 format不能传空值
                    parameterMap.put("startDate",df.format(recordInfo.getStartDate()));
                    //将时间返回到查询条件前format
                    recordInfo.setStartDate(recordInfo.getStartDate());
                }
                if(recordInfo.getEndDate()!=null){
                    parameterMap.put("endDate", df2.format(recordInfo.getEndDate()));
                    //将时间返回到查询条件前format
                    recordInfo.setEndDate(recordInfo.getEndDate());
                }
                parameterMap.put("userName", recordInfo.getUserName());
                parameterMap.put("isKnowCustomer",recordInfo.getIsKnowCustomer());//已知，未知客户条件，sql（根据1，2来查询客户和电话是否已知或未知）
                if(recordInfo.getCallType()!=null&&recordInfo.getCallType()==0){
                    parameterMap.put("forcallType", 1);//当类型选择为通话记录时 SQL查询between 1 and 3
                }else{
                    parameterMap.put("callType", recordInfo.getCallType());
                }
            }
            parameterMap.put("recordInfoId", "1");//随意设置一个值在sql里添加条件。已删除和未归档的排除
            rbean = recordInfoService.getRecordArchiveInfo(parameterMap);
        }
        try {
            String fileName="";
            if(type==0){
                fileName=EnumRecord.ALL_RECORDINFO.getValue();
            }else if(type==1){
                fileName=EnumRecord.CALL_INFO.getValue();
            }else if(type==2){
                fileName=EnumRecord.LOCALE_INFO.getValue();
            }else if(type==3){
                fileName=EnumRecord.VISIT_INFO.getValue();
            }else if(type==4){
                fileName=EnumRecord.ARCHIVE_INFO.getValue();
            }
            if(type==0||type==4){
                for(int i=0;i<rbean.size();i++){
                    if(rbean.get(i).getCallTypeName().equals(EnumRecord.CALL_IN.getValue())||rbean.get(i).getCallTypeName().equals(EnumRecord.CALL_OUT.getValue())||rbean.get(i).getCallTypeName().equals(EnumRecord.MISSED_CALLS.getValue())||rbean.get(i).getCallTypeName().equals(EnumRecord.UNREAD_MESSAGE.getValue())){//导出前先将类型为通话记录的设置好
                        rbean.get(i).setCallTypeName(EnumRecord.CALL_INFO.getValue());
                    }
                    if(rbean.get(i).getCallTime()!=null){
                        rbean.get(i).setCallTime(changTime(rbean.get(i).getCallTime()));
                    }
                }
            }else{
                for(int i=0;i<rbean.size();i++){
                    if(!StringUtil.isEmpty(rbean.get(i).getCallTime())){
                        rbean.get(i).setCallTime(changTime(rbean.get(i).getCallTime()));
                    }
                }
            }
            getResponse().setHeader("Content-disposition","attachment; filename="+new String(fileName.getBytes("gbk"),"iso8859-1")+System.currentTimeMillis()+".xls");//设定输出文件头
            getResponse().setContentType("application/msexcel");//定义输出类型
            getResponse().setCharacterEncoding("UTF-8");
            outputStream = getResponse().getOutputStream();
            workbook =  recordInfoService.RecordReportDownExcel(rbean);
            //opeventLogService.addOpeventLog(EnumRecord.RECORD_NAME.getValue(), EnumRecord.EXPORT_EXCEL.getValue(), 1, "");
        } catch (Exception e) {
            //opeventLogService.addOpeventLog(EnumRecord.RECORD_NAME.getValue(), EnumRecord.EXPORT_EXCEL.getValue(), 0, "");
            log.error("RecordInfoAction downExcel action error:"+e.getMessage());
            workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet();
            ExcelUtil.writeExcelDetailRow(sheet, new String[] { EnumRecord.EXPORT_ERROR.getValue() }, 0);
        } finally {
            ExcelUtil.writeToResponse(workbook, outputStream);
        }
    }
    
   
    
    /**
     * 所有未读留言和未接来电接口
     * @param str
     * @return
     */
    public String queryAllNotReadAndNotThrough(){
        try {
            toType=request.getParameter("toType");
            type=Integer.parseInt(request.getParameter("type"));
            return SUCCESS;
        } catch (Exception e) {
            log.error("RecordInfoAction queryAllNotReadAndNotThrough action error:"+e.getMessage());
            return ERROR;
        }
    }
    
    /**
     * 刷新编辑页面
     * @return
     */
    public String refreshRecordConnect(){
        try {
            if(recordId!=null&&!(recordId+"").equals("")){
                recordDetail=recordInfoService.getRecordInfoById(recordId);
                recbizTypeList = recbizTypeService.getRecbizTypeForPad();
                commProgressList=commProgressService.getCommProgressList();
                request.setAttribute("type", type);
                return SUCCESS;
            }
            return SUCCESS;
        } catch (Exception e) {
            log.error("RecordInfoAction refreshRecordConnect action error:"+e.getMessage());
            return ERROR;
        }
    }
    
    /**
     * 根据客户id查看相关记录
     * @return
     */
    public String queryAllByCustomerId(){
        try {
            String insert=request.getParameter("insert");//区分是否是通话进入还是客户进入该接口 false通话
            String actionType=request.getParameter("actionType").toString();
            Map<String, Object> map=new HashMap<String, Object>();
            String customerId=request.getParameter("customerId");
            if(customerId!=null&&customerId!=""){
                map.put("customerId",customerId);
            }
            recordInfoList = recordInfoService.getRecordInfoPage(map, this.getPage(),0,queryBean,"");
            request.setAttribute("insert", insert);
            request.setAttribute("queryBean", queryBean);
            request.setAttribute("actionType", actionType);
            request.setAttribute("customerId", customerId);
            request.setAttribute("type", type);
            return SUCCESS;
        } catch (Exception e) {
            log.error("RecordInfoAction queryAllByCustomerId action error:"+e.getMessage());
            return ERROR;
        }
    }
    
    /**
     * 根据客户id查看客户是否删除
     * 
     */
    public void getCustomerIsDel(){
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/html;charset=utf-8");
        try {
            PrintWriter out = response.getWriter();
            String customerId=request.getParameter("customerId");
            if(customerId==null||customerId.length()==0){
                out.print("0");
            }else{
                if(crmCustomerService.isDelCustomer(Integer.parseInt(customerId))){
                    out.print("0");
                }else{
                    out.print("1");
                }
            }
            out.close();
        } catch (Exception e) {
            log.error("RecordInfoAction getCustomerIsDel action error:"+e.getMessage());
        }
    }
    
    /**
     * 关联客户查询
     */
    public void relationQuery(){
        try {
            HttpServletResponse response = ServletActionContext.getResponse();
            response.setContentType("text/html;charset=utf-8");
            PrintWriter out = response.getWriter();
            String customerId=request.getParameter("customerId");
            String phone=request.getParameter("phone");
            String recordId=request.getParameter("recordId");
            BaseCrmCustomer customer=new CrmCustomer();
            if(customerId!=null||customerId.length()!=0){
                baseCrmCustomer=crmCustomerService.getCrmCustomerById(Integer.parseInt(customerId));
                //更新客户最近联系时间
                recordDetail = recordInfoService.getRecordInfoById(Integer.parseInt(recordId));
                if(baseCrmCustomer.getLastContactDate()==null){
                    crmCustomerService.updateLastContactDate(Integer.parseInt(customerId), recordDetail.getStartDate(), recordDetail.getCallTypeName());
                }else if(recordDetail!=null||recordDetail.getStartDate().after(baseCrmCustomer.getLastContactDate())){
                    crmCustomerService.updateLastContactDate(Integer.parseInt(customerId), recordDetail.getStartDate(), recordDetail.getCallTypeName());
                }
                recordInfo=new RecordInfo();
                if(StringUtil.isEmpty(phone)){
                    recordInfo.setRecordInfoId(Integer.parseInt(recordId));
                    recordInfo.setCustomerId(baseCrmCustomer.getCustomerId());
                    recordInfo.setCustomerName(baseCrmCustomer.getCustomerName());
                    if(baseCrmCustomer.getDefaultPhoneType().equals(1)){
                        recordInfo.setRemotePhone(baseCrmCustomer.getMobilePhone1());
                    }else if(baseCrmCustomer.getDefaultPhoneType().equals(2)){
                        recordInfo.setRemotePhone(baseCrmCustomer.getMobilePhone2());                       
                    }else if(baseCrmCustomer.getDefaultPhoneType().equals(3)){
                        recordInfo.setRemotePhone(baseCrmCustomer.getPhone());                       
                    }else if(baseCrmCustomer.getDefaultPhoneType().equals(4)){
                        recordInfo.setRemotePhone(baseCrmCustomer.getFax());                       
                    }
                    recordInfo.setIsRead(1);
                    recordInfoService.updateRecordInfo(recordInfo);
                    out.print(1);
                }else if(phone!=null&&phone.length()==11&&phone.substring(0, 1).equals("1")){//判断是否是手机
                    if(baseCrmCustomer.getMobilePhone1()!=null||baseCrmCustomer.getMobilePhone1()!=""||baseCrmCustomer.getMobilePhone2()!=null||baseCrmCustomer.getMobilePhone2()!=""){//判断 如果两手机号是否有一项没值 否则返回1
                        customer.setCustomerId(Integer.parseInt(customerId));
                        //开始关联客户的手机1和手机2
                        if(baseCrmCustomer.getMobilePhone1()==null||baseCrmCustomer.getMobilePhone1().length()==0){//如果号码1等于null、""
                            customer.setMobilePhone1(phone);
                            crmCustomerService.updateCusPhoneNumber(customer);
                            if(calltype.equals(EnumRecord.LOCALE_INFO.getValue())){//座谈记录关联一条
                                recordInfoService.updateCustomerIdByRecId(Integer.parseInt(recordId),Integer.parseInt(customerId), baseCrmCustomer.getCustomerName(),null);//根据记录id关联一条记录
                            }else{
                                //绑定客户到联系记录
                                recordInfoService.updateCustomerIdByPhone(phone,Integer.parseInt(customerId),baseCrmCustomer.getCustomerName());
                            }
                            out.print(1);
                        }else if(baseCrmCustomer.getMobilePhone2()==null||baseCrmCustomer.getMobilePhone2().length()==0){
                            customer.setMobilePhone2(phone);
                            crmCustomerService.updateCusPhoneNumber(customer);
                            if(calltype.equals(EnumRecord.LOCALE_INFO.getValue())){//座谈记录关联一条
                                recordInfoService.updateCustomerIdByRecId(Integer.parseInt(recordId),Integer.parseInt(customerId), baseCrmCustomer.getCustomerName(),null);//根据记录id关联一条记录
                            }else{
                                //绑定客户到联系记录
                                recordInfoService.updateCustomerIdByPhone(phone,Integer.parseInt(customerId),baseCrmCustomer.getCustomerName());
                            }
                            out.print(1);
                        }else if(baseCrmCustomer.getMobilePhone1().equals(phone)||baseCrmCustomer.getMobilePhone2().equals(phone)){//判断手机1，2是否等于待关联客户的手机 是则不替换号码直接绑定
                            if(calltype.equals(EnumRecord.LOCALE_INFO.getValue())){//座谈记录关联一条
                                recordInfoService.updateCustomerIdByRecId(Integer.parseInt(recordId),Integer.parseInt(customerId), baseCrmCustomer.getCustomerName(),null);//根据记录id关联一条记录
                            }else{
                            //绑定客户到联系记录
                                recordInfoService.updateCustomerIdByPhone(phone,Integer.parseInt(customerId),baseCrmCustomer.getCustomerName());
                            }
                            out.print(1);
                        }else{
                            out.print(customerId);
                        }
                    }else{
                        out.print(1);
                    }
                }else{//否则则为固话或
                    if(baseCrmCustomer.getPhone()!=null||baseCrmCustomer.getPhone()!=""||baseCrmCustomer.getFax()!=null||baseCrmCustomer.getFax()!=""){//判断 固话和传真是否有一项没值 否则返回1
                        customer.setCustomerId(Integer.parseInt(customerId));
                        //开始关联客户的固话和传真
                        if(baseCrmCustomer.getPhone()==null||baseCrmCustomer.getPhone().length()==0){//如果号码1等于null、""
                            customer.setPhone(phone);
                            crmCustomerService.updateCusPhoneNumber(customer);
                            if(calltype.equals(EnumRecord.LOCALE_INFO.getValue())){//座谈记录关联一条
                                recordInfoService.updateCustomerIdByRecId(Integer.parseInt(recordId),Integer.parseInt(customerId), baseCrmCustomer.getCustomerName(),null);//根据记录id关联一条记录
                            }else{
                                //绑定客户到联系记录
                                recordInfoService.updateCustomerIdByPhone(phone,Integer.parseInt(customerId),baseCrmCustomer.getCustomerName());
                            }
                            out.print(1);
                        }else if(baseCrmCustomer.getFax()==null||baseCrmCustomer.getFax().length()==0){
                            customer.setFax(phone);
                            crmCustomerService.updateCusPhoneNumber(customer);
                            if(calltype.equals(EnumRecord.LOCALE_INFO.getValue())){//座谈记录关联一条
                                recordInfoService.updateCustomerIdByRecId(Integer.parseInt(recordId),Integer.parseInt(customerId), baseCrmCustomer.getCustomerName(),null);//根据记录id关联一条记录
                            }else{
                                //绑定客户到联系记录
                                recordInfoService.updateCustomerIdByPhone(phone,Integer.parseInt(customerId),baseCrmCustomer.getCustomerName());
                            }
                            out.print(1);
                        }else if(baseCrmCustomer.getPhone().equals(phone)||baseCrmCustomer.getFax().equals(phone)){//判断固话或传真是否等于待关联客户的手机 是则不替换号码直接绑定
                            if(calltype.equals(EnumRecord.LOCALE_INFO.getValue())){//座谈记录关联一条
                                recordInfoService.updateCustomerIdByRecId(Integer.parseInt(recordId),Integer.parseInt(customerId), baseCrmCustomer.getCustomerName(),null);//根据记录id关联一条记录
                            }else{
                                //绑定客户到联系记录
                                recordInfoService.updateCustomerIdByPhone(phone,Integer.parseInt(customerId),baseCrmCustomer.getCustomerName());
                            }
                            out.print(1);
                        }else{
                            out.print(customerId);
                        }
                    }else{
                        out.print(1);
                    }
                }
            }
            out.close();
        } catch (Exception e) {
            log.error("RecordInfoAction relationQuery action error:"+e.getMessage());
        }
    }
    
    /**
     * 关联客户 选择电话
     */
    public String toCheckPhone(){
        try {
            String customerId=request.getParameter("customerId");
            String phone=request.getParameter("phone");
            String recordId=request.getParameter("recordId");
            baseCrmCustomer=crmCustomerService.getCrmCustomerById(Integer.parseInt(customerId));
            if(phone!=null&&phone.length()==11&&phone.substring(0, 1).equals("1")){//判断是否是手机
                request.setAttribute("phoneType", 1);
            }else{
                request.setAttribute("phoneType", 2);
            }
            request.setAttribute("recordId", recordId);
            request.setAttribute("phone", phone);
            return SUCCESS;
        } catch (Exception e) {
            log.error("RecordInfoAction toCheckPhone action error:"+e.getMessage());
            return ERROR;
        }
    }
    
    /**
     * 关联客户 选中电话替换
     */
    public void relationPhone(){
        try {
            String recordId=request.getParameter("recordId");
            String customerId=request.getParameter("customerId");
            String newPhone=request.getParameter("newPhone");
            String phoneNum=request.getParameter("phoneNum");
            String customerName=request.getParameter("customerName");
            BaseCrmCustomer customer=new CrmCustomer();
            customer.setCustomerId(Integer.parseInt(customerId));
            if(phoneNum.equals("1")){
                customer.setMobilePhone1(newPhone);
            }else if(phoneNum.equals("2")){
                customer.setMobilePhone2(newPhone);
            }else if(phoneNum.equals("3")){
                customer.setPhone(newPhone);
            }else if(phoneNum.equals("4")){
                customer.setFax(newPhone);
            }
            crmCustomerService.updateCusPhoneNumber(customer);//修改客户电话
            if(calltype!=null&&calltype.equals(EnumRecord.LOCALE_INFO.getValue())){
                recordInfoService.updateCustomerIdByRecId(Integer.parseInt(recordId),Integer.parseInt(customerId),customerName,null);//根据记录id关联一条记录
            }else{
                recordInfoService.updateCustomerIdByPhone(newPhone,Integer.parseInt(customerId),customerName);//修改联系记录里的客户信息
            }
        } catch (Exception e) {
            log.error("RecordInfoAction relationPhone action error:"+e.getMessage());
        }
    }
    
    /**
     * 取消关联客户
     */
    public void cancelRelation(){
        try {
            String customerId=request.getParameter("customerId");
            String phone=request.getParameter("phone");
            if(customerId!=""){
                if(calltype.equals(EnumRecord.LOCALE_INFO.getValue())){
                    recordInfoService.updateCustomerIdByRecIdForRelation(recordId);
                }else{
                    recordInfoService.updateCustomerIdByCusId(Integer.parseInt(customerId),phone);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("RecordInfoAction cancelRelation action error:"+e.getMessage());
        }
    }
    
    /**
     * 导出联系记录
     */
	public String queryRecordInfosByMap(){
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/html;charset=utf-8");
        try {
            PrintWriter out = response.getWriter();
            Map<String, Object> map = new HashMap<String, Object>();
            String fenye = request.getParameter("fenye");
            String BelongToType = request.getParameter("BelongToType");
            String userIds = request.getParameter("userIds");
            String deptIds = request.getParameter("deptIds");
            String containSub=request.getParameter("containSub");//记录是否有选择操作者 机构 否1 是0
            
            if(queryBean!=null){
                if(queryBean.getCustomerTypeId()==1){//归属给我的
                    if(BelongToType.equals("brMine")){
                        map.put("belongUserId",this.getLoginInfo().getUserId());
                        map.put("userIds",this.getLoginInfo().getUserId());
                    }else{
                        map.put("belongUserId",this.getLoginInfo().getUserId());
                    }
                }else if(queryBean.getCustomerTypeId()==2){//共享给我的
                    Map<String, Object> m =new HashMap<String, Object>();
                    m.put("shareUerId",this.getLoginInfo().getUserId() );
                    if(BelongToType.equals("brMine")){
                        map.put("shareUerId",this.recordInfoService.getIdsShareUerId(m));
                        map.put("userIds",this.getLoginInfo().getUserId());
                    }else{
                        String ids = this.recordInfoService.getIdsShareUerId(m);
                        if(ids.length()>0){
                            map.put("shareUerId",ids);
                        }else{
                            map.put("shareUerId",0.1);//当没有共享客户时插入0.1 使客户查询出来为空记录
                        }
                    }
                }else{
                	if(BelongToType.equals("brMine")){
                        map.put("userIds", getLoginInfo().getUserId());
                    }else if(BelongToType.equals("brUser")){
                    	if(userIds.equals("")){
                    		if(deptFacadeService.isInChargeOfDepartment()){ 
                    			map.put("userIds",deptFacadeService.getUserIdsBelongToManager(getLoginInfo().getRoles(),"record"));
                    		}else{
                    			map.put("userIds",getLoginInfo().getUserId());
                    		}
                    	}else{
                    		if(deptFacadeService.isInChargeOfDepartment()){ 
                    			map.put("userIds",userIds);
                    		}else{
                    			map.put("userIds",getLoginInfo().getUserId());
                    		}
                    	}
                    }else if(BelongToType.equals("brDept")&&containSub.equals("1")){
                        if(deptIds.equals("")){
                        	if(deptFacadeService.isInChargeOfDepartment()){
                        		map.put("deptIds",deptFacadeService.getInChargeOfStringDeptIds(this.getLoginInfo().getUserId()));
                    		}else{
                    			map.put("userIds",getLoginInfo().getUserId());
                    		}
                        }else{
                            map.put("deptIds",getContainSubDeptIds(deptIds));
                        }
                    }else if(BelongToType.equals("brDept")&&containSub.equals("0")){
                        if(deptIds.equals("")){
                            map.put("userIds", getLoginInfo().getUserId());
                        }else{
                            map.put("deptIds",deptIds);
                        }
                    }else{
                        map.put("userIds", getLoginInfo().getUserId());
                    }
                }
            }else{
                map.put("userIds",this.getLoginInfo().getUserId());
            }
            
            String data = request.getParameter("crmPostData");
            HSSFWorkbook workbook = new HSSFWorkbook();
            CustomerExportContext ctx = customerExportService.getContext();
            int pageNum = 1;//页数
            int pageSize = ctx.getPageSize();//每页大小
            int recordNum = pageSize;//当前页的记录数
            ctx.setData(data);
            ctx.setParams(recordExportService.initParameter());
            Integer userId = this.getLoginInfo().getUserId();
            //parameterMap所有参数组装完毕    
            Map<Integer,CustomerExportBar> bars = RecordExportServiceImpl.getCustomerExportBar();
            CustomerExportBar bar = new CustomerExportBar();
            bar.setIsRun(1);
            bar.setIsStop(0);
            bar.setCuurRow(0);
            bars.put(userId,bar);

            while(recordNum==pageSize)
            {
                if(bars.containsKey(userId) && bars.get(userId).getIsStop()>0)return null;
                int offset = (pageNum-1)*pageSize;
                int limit = pageNum*pageSize;
                ctx.setBatchCount(pageNum);
                recordExportList = recordInfoService.queryRecordInfosByMap(map,type,queryBean,offset+1,limit);
                recordExportService.insertRow(ctx,workbook,type,recordExportList);
                recordNum = recordExportList.size();
                recordExportList.clear();
                pageNum++;
            }
            
            if(bar.getIsStop().equals(0)){
                File file= recordExportService.createFile(request);
                DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(file)));
                workbook.write(dos);
                dos.close();
                //保存勾选字段
                crmModuleExportService.saveExportField(data,"record");
                out.print("over");
            }
            bar.setIsRun(0);
            bar.setIsStop(1);
            out.close();
            
           /* 
            recordExportList = recordInfoService.queryRecordInfosByMap(map,type,queryBean,1,Integer.MAX_VALUE);
            workbook = recordExportService.createExcel(recordExportList,data,type);
            */
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("RecordInfoAction queryRecordInfosByMap error:" + e.getMessage());
            return ERROR;
        }
    }
   
    /**
     * 导出录音验证
     * @return
     */
    public String exportRecordFileVerify(){
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/html;charset=utf-8");
        try {
            PrintWriter out = response.getWriter();
            JSONObject jso = new JSONObject();
            String BelongToType = request.getParameter("BelongToType");
            String userIds = request.getParameter("userIds");
            String deptIds = request.getParameter("deptIds");
            String containSub=request.getParameter("containSub");
            Map<Integer,CustomerExportBar> customerExportBar = RecordInfoServiceImpl.getCustomerExportBar();
            CustomerExportBar bar = customerExportBar.get(this.getLoginInfo().getUserId());
            int isfile=0;
            if(bar != null){
                if(bar.getIsRun().equals(1) && bar.getIsStop().equals(0) ) {//正在运行并且没有终止
                    bar.setIsRun(0);
                    bar.setIsStop(1);
                    jso.put("error", EnumRecord.EXPORT_File_WARN.getValue());
                    out.print(jso.toString());
                    out.close();
                    return null;
                }
            }
            Map<String, Object> map = new HashMap<String, Object>();
            String fenye = request.getParameter("fenye");
            
            if(queryBean!=null){
                if(queryBean.getCustomerTypeId()==1){//归属给我的
                    if(BelongToType.equals("brMine")){
                        map.put("belongUserId",this.getLoginInfo().getUserId());
                        map.put("userIds",this.getLoginInfo().getUserId());
                    }else{
                        map.put("belongUserId",this.getLoginInfo().getUserId());
                    }
                }else if(queryBean.getCustomerTypeId()==2){//共享给我的
                    Map<String, Object> m =new HashMap<String, Object>();
                    m.put("shareUerId",this.getLoginInfo().getUserId() );
                    if(BelongToType.equals("brMine")){
                        map.put("shareUerId",this.recordInfoService.getIdsShareUerId(m));
                        map.put("userIds",this.getLoginInfo().getUserId());
                    }else{
                        String ids = this.recordInfoService.getIdsShareUerId(m);
                        if(ids.length()>0){
                            map.put("shareUerId",ids);
                        }else{
                            map.put("shareUerId",0.1);//当没有共享客户时插入0.1 使客户查询出来为空记录
                        }
                    }
                }else{
                	if(BelongToType.equals("brMine")){
                        map.put("userIds", getLoginInfo().getUserId());
                    }else if(BelongToType.equals("brUser")){
                    	if(userIds.equals("")){
                    		if(deptFacadeService.isInChargeOfDepartment()){ 
                    			map.put("userIds",deptFacadeService.getUserIdsBelongToManager(getLoginInfo().getRoles(),"record"));
                    		}else{
                    			map.put("userIds",getLoginInfo().getUserId());
                    		}
                    	}else{
                    		if(deptFacadeService.isInChargeOfDepartment()){ 
                    			map.put("userIds",userIds);
                    		}else{
                    			map.put("userIds",getLoginInfo().getUserId());
                    		}
                    	}
                    }else if(BelongToType.equals("brDept")&&containSub.equals("1")){
                        if(deptIds.equals("")){
                        	if(deptFacadeService.isInChargeOfDepartment()){
                        		map.put("deptIds",deptFacadeService.getInChargeOfStringDeptIds(this.getLoginInfo().getUserId()));
                    		}else{
                    			map.put("userIds",getLoginInfo().getUserId());
                    		}
                        }else{
                            map.put("deptIds",getContainSubDeptIds(deptIds));
                        }
                    }else if(BelongToType.equals("brDept")&&containSub.equals("0")){
                        if(deptIds.equals("")){
                            map.put("userIds", getLoginInfo().getUserId());
                        }else{
                            map.put("deptIds",deptIds);
                        }
                    }else{
                        map.put("userIds", getLoginInfo().getUserId());
                    }
                }
            }else{
                map.put("userIds",this.getLoginInfo().getUserId());
            }
            recordExportList = recordInfoService.queryRecordInfosByMap(map,type,queryBean,1,Integer.MAX_VALUE);

            for (int i = 0; i < recordExportList.size(); i++) {
                if (recordExportList.get(i).getFileName()!= null){
                    if(!recordExportList.get(i).getFileName().equals("")&&recordExportList.get(i).getFilePath()!= null||!recordExportList.get(i).getFilePath().equals("")){
                        if(new File(recordExportList.get(i).getFilePath()+File.separator+recordExportList.get(i).getFileName()).isFile()){
                            isfile+=1;
                        }
                    }
                }
            }
            jso.put("isfile", isfile);
            jso.put("size", recordExportList.size());
            out.print(jso.toString());
            out.close();
            if(bar != null){
            	bar.setIsRun(1);
            	bar.setIsStop(0);
            }
            return null;
        }catch (Exception e) {
            e.printStackTrace();
            log.error("RecordInfoAction exportRecordFileVerify error:"+e.getMessage());
            return ERROR;
        }
    }
    
    
    /**
     * 导出录音第一步
     * @param str
     * @return 
     */
    public String exportRecs(){
        try {
            List<CrmModuleExport> list = crmModuleExportService.getCrmModuleExportList("recordFile");
            if(list!= null && list.size() >0){
                for (CrmModuleExport crmModuleExport : list) {
                    feilds.add(crmModuleExport.getFeildName());
                }
            }
            baseColumn = recordInfoService.initParameter();
            return SUCCESS;
        } catch (Exception e) {
            log.error("RecordInfoAction exportRecs error:"+e.getMessage());
            return ERROR;
        }
        
    }
    /**
     * 终止导出
     * @return
     */
    public String exportRecordFileStop(){
        try {
            Map<Integer,CustomerExportBar> customerExportBar = RecordInfoServiceImpl.getCustomerExportBar();
            CustomerExportBar bar = customerExportBar.get(this.getLoginInfo().getUserId());
            if(bar.getIsRun().equals(1)){
                bar.setCuurRow(0);
                bar.setIsRun(0);
                bar.setIsStop(1);//正在导出时终止
            }
            return null;
        } catch (Exception e) {
            log.error("exportRecordFileStop action error:"+e.getMessage());
            return ERROR;
        }
 
    }
 
    /**
     * 导出录音文件 
     */
    public String exportFile(){
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/html;charset=utf-8");
        try {
            PrintWriter out = response.getWriter();
            Map<String, Object> map = new HashMap<String, Object>();
            String fenye = request.getParameter("fenye");
            String BelongToType = request.getParameter("BelongToType");
            String userIds = request.getParameter("userIds");
            String deptIds = request.getParameter("deptIds");
            String containSub=request.getParameter("containSub");//记录是否有选择操作者 机构 否1 是0
            
            if(queryBean!=null){
                if(queryBean.getCustomerTypeId()==1){//归属给我的
                    if(BelongToType.equals("brMine")){
                        map.put("belongUserId",this.getLoginInfo().getUserId());
                        map.put("userIds",this.getLoginInfo().getUserId());
                    }else{
                        map.put("belongUserId",this.getLoginInfo().getUserId());
                    }
                }else if(queryBean.getCustomerTypeId()==2){//共享给我的
                    Map<String, Object> m =new HashMap<String, Object>();
                    m.put("shareUerId",this.getLoginInfo().getUserId() );
                    if(BelongToType.equals("brMine")){
                        map.put("shareUerId",this.recordInfoService.getIdsShareUerId(m));
                        map.put("userIds",this.getLoginInfo().getUserId());
                    }else{
                        String ids = this.recordInfoService.getIdsShareUerId(m);
                        if(ids.length()>0){
                            map.put("shareUerId",ids);
                        }else{
                            map.put("shareUerId",0.1);//当没有共享客户时插入0.1 使客户查询出来为空记录
                        }
                    }
                }else{
                	if(BelongToType.equals("brMine")){
                        map.put("userIds", getLoginInfo().getUserId());
                    }else if(BelongToType.equals("brUser")){
                    	if(userIds.equals("")){
                    		if(deptFacadeService.isInChargeOfDepartment()){ 
                    			map.put("userIds",deptFacadeService.getUserIdsBelongToManager(getLoginInfo().getRoles(),"record"));
                    		}else{
                    			map.put("userIds",getLoginInfo().getUserId());
                    		}
                    	}else{
                    		if(deptFacadeService.isInChargeOfDepartment()){ 
                    			map.put("userIds",userIds);
                    		}else{
                    			map.put("userIds",getLoginInfo().getUserId());
                    		}
                    	}
                    }else if(BelongToType.equals("brDept")&&containSub.equals("1")){
                        if(deptIds.equals("")){
                        	if(deptFacadeService.isInChargeOfDepartment()){
                        		map.put("deptIds",deptFacadeService.getInChargeOfStringDeptIds(this.getLoginInfo().getUserId()));
                    		}else{
                    			map.put("userIds",getLoginInfo().getUserId());
                    		}
                        }else{
                            map.put("deptIds",getContainSubDeptIds(deptIds));
                        }
                    }else if(BelongToType.equals("brDept")&&containSub.equals("0")){
                        if(deptIds.equals("")){
                            map.put("userIds", getLoginInfo().getUserId());
                        }else{
                            map.put("deptIds",deptIds);
                        }
                    }else{
                        map.put("userIds", getLoginInfo().getUserId());
                    }
                }
            }else{
                map.put("userIds",this.getLoginInfo().getUserId());
            }
            recordExportList = recordInfoService.queryRecordInfosByMap(map,type,queryBean,1,Integer.MAX_VALUE);
            String data = request.getParameter("crmPostData");
            String str=recordInfoService.exportFile(recordExportList,data);
            out.print(str+","+type);
            Map<Integer,CustomerExportBar> customerExportBar = RecordInfoServiceImpl.getCustomerExportBar();
            CustomerExportBar bar = customerExportBar.get(this.getLoginInfo().getUserId());
            if(bar == null || bar.getIsStop().equals(0)){
                out.close();
                //保存勾选字段
                crmModuleExportService.saveExportField(data,"recordFile");
            }
            bar.setIsRun(0);
            bar.setIsStop(1);
            out.close();
            return null;
        } catch (Exception e) {
            log.error("RecordInfoAction exportFile error:"+e.getMessage());
            return ERROR;
        }
    }
    
    
    /**
     * 导出进度条
     * @return
     */
    public String exportRecordFileBar(){
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/html;charset=utf-8");
        try {
            PrintWriter out = response.getWriter();
            JSONObject jso = new JSONObject();
            Map<Integer,CustomerExportBar> customerExportBar = RecordInfoServiceImpl.getCustomerExportBar();
            CustomerExportBar bar = customerExportBar.get(this.getLoginInfo().getUserId());
            if(bar!= null) {
                jso.put("curr", bar.getCuurRow());
            }
            out.print(jso.toString());
            return null;
        }catch (Exception e) {
            log.error("RecordInfoAction exportRecordFileBar error:"+e.getMessage());
            return ERROR;
        }
    }
    
    /**
     * 下载导出的录音zip文件
     */
    public void exportFileDown(){
        try {
            String type=request.getParameter("type");
            String typeName="";
            if(type!=null&&type!=""){
                if(type.equals("0")){
                    typeName+="所有记录录音-";
                }else if(type.equals("1")){
                    typeName+="通话记录录音-";
                }else if(type.equals("2")){
                    typeName+="座谈记录录音-";
                }
            }
            String path = FileUploadPathConstants.EXPORT_FILE_PATH+"/"+df4.format(new Date())+getLoginInfo().getUserId()+".zip";
            // 服务器绝对路径
            path = this.request.getRealPath("/") + path;
            ServletOutputStream out = this.getResponse().getOutputStream();
            String strPath=typeName+"["+df4.format(new Date())+"].zip";
            this.getResponse().setHeader("Content-Disposition", "attachment; filename=" + java.net.URLEncoder.encode(strPath, "UTF-8"));
            BufferedInputStream bis = null;
            BufferedOutputStream bos = null;
            bis = new BufferedInputStream(new FileInputStream(path));
            bos = new BufferedOutputStream(out);
            byte[] buff = new byte[2048];
            int bytesRead;
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
              bos.write(buff, 0, bytesRead);
            }
            bis.close();
            bos.close();
        } catch (Exception e) {
            log.error("RecordInfoAction exportFileDown error:"+e.getMessage());
        }
    } 
    
    /**
     * 根据部门id集合查询它们及下属的部门id集合 （工具类）
     * @param deptids
     * @return
     */
    private String getContainSubDeptIds(String deptids){
        List<SysDept> deptList = deptFacadeService.getContainDeptListByDeptIds(deptids);
        String newDeptIds = "";
        for(SysDept dept: deptList){
            if(newDeptIds.equals("")){
                newDeptIds = dept.getDeptId().toString();
            }else{
                newDeptIds = newDeptIds + "," + dept.getDeptId().toString();
            }
        }
        return newDeptIds;
    }
    
    /**
     * 查询录音文件是否存在
     */
    public void haveRec(){
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/html;charset=utf-8");
        String path = request.getParameter("path");
        try {
            PrintWriter out = response.getWriter();
            if(StringUtil.isEmpty(path)){
                out.print(0);
            }else{
                File f = new File(path);
                if(f.exists()){
                    out.print(1);
                }else{
                    out.print(0);
                } 
            }
            out.flush();
            out.close();
        } catch (Exception e) {
            log.error("RecordInfoAction haveRec error:"+e.getMessage());
        }
    }
    
    /**
     * 时长转换成HH:mm:ss
     * @param str
     * @return
     */
    public String changTime(String str){
        int l=Integer.parseInt(str);
        String H =""+l/3600;
        if(H.length()<2)H="0"+H;
        l = l%3600;
        String M = ""+l/60;
        if(M.length()<2)M="0"+M;
        l = l%60;
        String S = ""+l;
        if(S.length()<2)S="0"+S;
        return H+":"+M+":"+S;
    }
    
    /**
     * 打散重组
     */
    public String translate (String str) {
        String tempStr = "";
        try {
          tempStr = new String(str.getBytes("ISO-8859-1"), "GBK");
          tempStr = tempStr.trim();
        }
        catch (Exception e) {
          System.err.println(e.getMessage());
        }
        return tempStr;
      }
     
    
    public void setRecordInfoService(RecordInfoService recordInfoService) {
        this.recordInfoService = recordInfoService;
    }

    public PageUtil<RecordInfoBean> getRecordInfoList() {
        return recordInfoList;
    }

    public void setRecordInfoList(PageUtil<RecordInfoBean> recordInfoList) {
        this.recordInfoList = recordInfoList;
    }

    public void setRecbizTypeService(RecbizTypeService recbizTypeService) {
        this.recbizTypeService = recbizTypeService;
    }

    public List<RecbizType> getRecbizTypeList() {
        return recbizTypeList;
    }

    public void setRecbizTypeList(List<RecbizType> recbizTypeList) {
        this.recbizTypeList = recbizTypeList;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getRecordId() {
        return recordId;
    }

    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
    }

    public Integer getIsCanceled() {
        return isCanceled;
    }

    public void setIsCanceled(Integer isCanceled) {
        this.isCanceled = isCanceled;
    }

    public RecordDetail getRecordDetail() {
        return recordDetail;
    }

    public void setRecordDetail(RecordDetail recordDetail) {
        this.recordDetail = recordDetail;
    }

    public String getRecordIds() {
        return recordIds;
    }

    public void setRecordIds(String recordIds) {
        this.recordIds = recordIds;
    }

    public List<CommProgress> getCommProgressList() {
        return commProgressList;
    }

    public void setCommProgressList(List<CommProgress> commProgressList) {
        this.commProgressList = commProgressList;
    }

    public void setCommProgressService(CommProgressService commProgressService) {
        this.commProgressService = commProgressService;
    }

    public void setDeptFacadeService(DeptFacadeService deptFacadeService) {
        this.deptFacadeService = deptFacadeService;
    }

    public DeptFacadeService getDeptFacadeService() {
        return deptFacadeService;
    }

    public String getCalltype() {
        return calltype;
    }

    public void setCalltype(String calltype) {
        this.calltype = calltype;
    }

    public String getExportfileName() {
        return ExportfileName;
    }

    public void setExportfileName(String exportfileName) {
        ExportfileName = exportfileName;
    }

    public String getToType() {
        return toType;
    }

    public void setToType(String toType) {
        this.toType = toType;
    }

    public OpeventLogService getOpeventLogService() {
        return opeventLogService;
    }

    public void setOpeventLogService(OpeventLogService opeventLogService) {
        this.opeventLogService = opeventLogService;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public CrmCustomerService getCrmCustomerService() {
        return crmCustomerService;
    }

    public void setCrmCustomerService(CrmCustomerService crmCustomerService) {
        this.crmCustomerService = crmCustomerService;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public RecordQueryBean getQueryBean() {
        return queryBean;
    }

    public void setQueryBean(RecordQueryBean queryBean) {
        this.queryBean = queryBean;
    }

    public BaseCrmCustomer getBaseCrmCustomer() {
        return baseCrmCustomer;
    }

    public void setBaseCrmCustomer(BaseCrmCustomer baseCrmCustomer) {
        this.baseCrmCustomer = baseCrmCustomer;
    }

    public List<RecordExportBean> getRecordExportList() {
        return recordExportList;
    }

    public void setRecordExportList(List<RecordExportBean> recordExportList) {
        this.recordExportList = recordExportList;
    }

    public void setRecordExportService(RecordExportService recordExportService) {
        this.recordExportService = recordExportService;
    }

    public void setCrmModuleExportService(CrmModuleExportService crmModuleExportService) {
        this.crmModuleExportService = crmModuleExportService;
    }

    public Map<String, String> getBaseColumn() {
        return baseColumn;
    }

    public void setBaseColumn(Map<String, String> baseColumn) {
        this.baseColumn = baseColumn;
    }

    public void setCustomerExportService(CustomerExportService customerExportService) {
        this.customerExportService = customerExportService;
    }

    public void setTemp(CrmTemplateService temp) {
        this.temp = temp;
    }

    public CrmTemplateService getTemp() {
        return temp;
    }

    public List<String> getFeilds() {
        return feilds;
    }

    public void setFeilds(List<String> feilds) {
        this.feilds = feilds;
    }

    public Integer getSuccessCount() {
        return successCount;
    }

    public void setSuccessCount(Integer successCount) {
        this.successCount = successCount;
    }
    
    
    
    public VisitRecordInfo getVisitRecordInfo() {
        return visitRecordInfo;
    }

    public void setVisitRecordInfo(VisitRecordInfo visitRecordInfo) {
        this.visitRecordInfo = visitRecordInfo;
    }

    /**
	 * 最近10呼入呼出记录
	 * @return
	 */
	public String getRecentlyTalkRecord()
	{
		this.recentRecords = this.recordInfoService.getRecentlyTalkRecord(this.getLoginInfo().getUserId());
		if(this.recentRecords.size()>0)return SUCCESS;
    	else return null;
	}

    public void setVisitRecordService(VisitRecordService visitRecordService) {
        this.visitRecordService = visitRecordService;
    }

	public String getTskCustomerName() {
		return tskCustomerName;
	}

	public void setTskContactService(TskContactService tskContactService) {
        this.tskContactService = tskContactService;
    }

    public void setTskCustomerName(String tskCustomerName) {
		this.tskCustomerName = tskCustomerName;
	}

    public String getOldRemark() {
        return oldRemark;
    }

    public void setOldRemark(String oldRemark) {
        this.oldRemark = oldRemark;
    }

	public void setSpecialDataAuthService(
			SpecialDataAuthService specialDataAuthService) {
		this.specialDataAuthService = specialDataAuthService;
	}

}
