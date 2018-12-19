/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :理财规划报告模板action
 * Author     :cheny
 * Create Date:2012-7-16
 */
package com.banger.mobile.webapp.action.PlnReportTemplate;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.Enum.plnReportTemplate.EnumPlnReportTemplate;
import com.banger.mobile.domain.model.PlnReportTemplate.PlnPlanType;
import com.banger.mobile.domain.model.PlnReportTemplate.PlnReportTemplate;
import com.banger.mobile.domain.model.PlnReportTemplate.PlnReportTemplateBean;
import com.banger.mobile.domain.model.PlnReportTemplate.PlnReportTemplateVar;
import com.banger.mobile.domain.model.PlnReportTemplate.PlnVarType;
import com.banger.mobile.domain.model.PlnReportTemplate.PlnVarTypeSub;
import com.banger.mobile.domain.model.plnFastPlan.PlnFastPlan;
import com.banger.mobile.domain.model.plnReport.PlnReport;
import com.banger.mobile.domain.model.user.SysUser;
import com.banger.mobile.facade.customer.CrmCustomerService;
import com.banger.mobile.facade.log.OpeventLoginLogService;
import com.banger.mobile.facade.plnFastPlan.PlnFastPlanService;
import com.banger.mobile.facade.plnReport.PlnReportService;
import com.banger.mobile.facade.plnReportTemplate.PlnReportTemplateService;
import com.banger.mobile.facade.user.SysUserService;
import com.banger.mobile.webapp.action.BaseAction;

/**
 * @author cheny
 * @version $Id: PlnReportTemplateAction.java,v 0.1 2012-7-16 上午11:30:13 cheny Exp $
 */
public class PlnReportTemplateAction extends BaseAction{

    private static final long serialVersionUID = -6867238410882957472L;

    private PlnReportTemplateService plnReportTemplateService;       //理财规划报告模板service
    
    private OpeventLoginLogService opeventLoginLogService;          //操作日志service
    
    private SysUserService sysUserService;               //用户管理service
    
    private CrmCustomerService crmCustomerService;        //客户管理service
    
    private PlnFastPlanService plnFastPlanService;       //快速规划service
    
    private HttpServletRequest req = request;
    
    private PlnReportTemplate plnReportTemplate;          //模板对象
    
    PageUtil<PlnReportTemplateBean> tempList;         //模板列表分页对象
    
    private int totalAmount;             //记录总数
    
    private List<PlnPlanType> planTypeList;     //所有模板类型
    

    private String templateReplaceContent;        //模板替换内容
    
    private String templateIds;                   //删除模板ids
    
    private Integer activeFlag;                   //启用停用标志
    
    private List<PlnReportTemplateVar> templateVarList; //所有的变量
    
    private List<PlnVarTypeSub>   typeTwoVarList;          //所有的二级分类
    
    private List<PlnVarType>      typeOneVarList;            //所有的一级分类
    
    private Integer fastPlanId;                       //快速规划id
        
    private String intervalName;              //投资偏好名称
    private String planTypeName;           //模板类型名称
    
    private PlnReportService            plnReportService;
    
    
    /**
     * 设置初始化数据
     */
    public void setInitParam(){
        planTypeList = plnReportTemplateService.getAllPlnPlanType();//设置规划模板类型
    }
    
    /**
     * 模板列表
     */
    public String showPlnReportTemplateList(){
        try{
            setInitParam();
            tempList = plnReportTemplateService.getPlnReportTemplatePage(plnReportTemplate,this.getPage());
            totalAmount = this.getPage().getTotalRowsAmount();
            return SUCCESS;
        }catch(Exception e){
            log.error("showPlnReportTemplateList action error:" + e.getMessage());
            e.printStackTrace();
            return ERROR;
        }
    }
    /**
     * 分页
     */
    public String getPlnReportTemplatePage(){
        try{
            setInitParam();
            tempList = plnReportTemplateService.getPlnReportTemplatePage(plnReportTemplate,this.getPage());
            return SUCCESS;
        }catch(Exception e){
            log.error("getPlnReportTemplatePage action error:" + e.getMessage());
            return ERROR;
        }
    }
    /**
     * 新建理财规划模板界面
     */
      public String savePlanTemplateForm(){
          setInitParam();
          return SUCCESS;
      }
    
    /**
     * 新建理财规划模板
     */
    public String savePlanTemplate(){
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/html;charset=utf-8");
        try{
            PrintWriter out = response.getWriter();
            JSONObject jso = new JSONObject();
            //模板名称唯一性验证 
            Map<String,Object> nMap = new HashMap<String,Object>();
            nMap.put("templateName", plnReportTemplate.getTemplateName());
            PlnReportTemplate tempName = plnReportTemplateService.getPlanTemplate(nMap);
            if(tempName != null){
                jso.put("nameMsg", EnumPlnReportTemplate.UNIK_TEMPLATENAME.getValue());
            }
            // 模板编号唯一性验证
            Map<String,Object> tMap = new HashMap<String,Object>();
            tMap.put("templateNo", plnReportTemplate.getTemplateNo());
            PlnReportTemplate tempNo = plnReportTemplateService.getPlanTemplate(tMap);
            if(tempNo != null){
                jso.put("noMsg", EnumPlnReportTemplate.UNIK_TEMPLATENO.getValue());
            }
            //启用验证
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("planTypeId", plnReportTemplate.getPlanTypeId());
            map.put("intervalTypeId", plnReportTemplate.getIntervalTypeId());
            map.put("isActived", plnReportTemplate.getIsActived());
            PlnReportTemplate template = null;
            if(plnReportTemplate.getIsActived().equals(1)){
                template = plnReportTemplateService.getPlanTemplate(map);
                if(template != null){
                    jso.put("error", EnumPlnReportTemplate.UNIK_ISACTIVE.getValue());

                }
            }
            if(tempName == null && tempNo == null && template == null){
                plnReportTemplate.setIsDel(0);
                plnReportTemplate.setCreateUser(this.getLoginInfo().getUserId());
                Integer templateId = plnReportTemplateService.insertPlanTemplate(plnReportTemplate);
                jso.put("id", templateId);
                opeventLoginLogService.addLog(11,EnumPlnReportTemplate.MODEL_ACTION_SAVE.getValue(), 1, 1);
            }
            out.print(jso.toString());
            out.close();
            return null;
        }catch(Exception e){
            opeventLoginLogService.addLog(11,EnumPlnReportTemplate.MODEL_ACTION_SAVE.getValue(), 1, 0);
            log.error("savePlanTemplate action error:" + e.getMessage());
            return ERROR;
        }
    }

    /**
     * 插入标签
     */
    public String targetList() {
        typeOneVarList = plnReportTemplateService.getOneSubList();
        typeTwoVarList = plnReportTemplateService.getTwoSubList();
        templateVarList = plnReportTemplateService.getAllVarList();
        return SUCCESS;
    }
    /**
     * 查看模板
     */
    public String getPlanTemplateDetail(){
        try {
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("templateId", plnReportTemplate.getTemplateId());
            plnReportTemplate = plnReportTemplateService.getPlanTemplate(map);
            planTypeName = plnReportTemplateService.getPlnPlanTypeById(plnReportTemplate.getPlanTypeId()).getPlanTypeName();
            return SUCCESS;
        } catch (Exception e) {
            log.error("detailPlanTemplate action error:" + e.getMessage());
            return ERROR;
        }
    }
    /**
     * 编辑模板界面
     */
    public String updatePlanTemplateForm(){
        setInitParam();
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("templateId", plnReportTemplate.getTemplateId());
        plnReportTemplate = plnReportTemplateService.getPlanTemplate(map);
        return SUCCESS;
    }
    
    /**
     * 编辑模板
     */
    public String updatePlanTemplate(){
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/html;charset=utf-8");
        try{
            PrintWriter out = response.getWriter();
            JSONObject jso = new JSONObject();
            //模板名称唯一性验证 
            Map<String,Object> nMap = new HashMap<String,Object>();
            nMap.put("templateId", plnReportTemplate.getTemplateId());
            nMap.put("templateName", plnReportTemplate.getTemplateName());
            PlnReportTemplate tempName = plnReportTemplateService.updatePlanTemplateValidate(nMap);
            if(tempName != null){
                jso.put("nameMsg", EnumPlnReportTemplate.UNIK_TEMPLATENAME.getValue());
            }
            // 模板编号唯一性验证
            Map<String,Object> tMap = new HashMap<String,Object>();
            tMap.put("templateId", plnReportTemplate.getTemplateId());
            tMap.put("templateNo", plnReportTemplate.getTemplateNo());
            PlnReportTemplate tempNo = plnReportTemplateService.updatePlanTemplateValidate(tMap);
            if(tempNo != null){
                jso.put("noMsg", EnumPlnReportTemplate.UNIK_TEMPLATENO.getValue());
            }
            //启用模板验证
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("templateId", plnReportTemplate.getTemplateId());
            map.put("planTypeId", plnReportTemplate.getPlanTypeId());
            map.put("intervalTypeId", plnReportTemplate.getIntervalTypeId());
            map.put("isActived", plnReportTemplate.getIsActived());
            PlnReportTemplate template = null;
            if(plnReportTemplate.getIsActived().equals(1)){
                template = plnReportTemplateService.updatePlanTemplateValidate(map);
                if(template != null){
                    jso.put("error", EnumPlnReportTemplate.UNIK_ISACTIVE.getValue());
                }
            }
            if(tempName == null && tempNo == null && template == null){
                plnReportTemplate.setUpdateUser(this.getLoginInfo().getUserId());
                plnReportTemplateService.updatePlanTemplate(plnReportTemplate);
                opeventLoginLogService.addLog(11,EnumPlnReportTemplate.MODEL_ACTION_UPDATE.getValue(), 1, 1);
            }
            out.print(jso.toString());
            out.close();
            return null;
           
        }catch(Exception e){
            opeventLoginLogService.addLog(11,EnumPlnReportTemplate.MODEL_ACTION_UPDATE.getValue(), 1, 0);
            log.error("updatePlanTemplate action error:" + e.getMessage());
            return ERROR;
        }
    }
    /**
     * 删除多个模板
     */
    public String delPlanTemplates(){
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/html;charset=utf-8");
        try {
            PrintWriter out = response.getWriter();
            String[] ids = templateIds.split(",");
            if(ids.length > 0){
                for(int i=0;i<ids.length;i++){
                    plnReportTemplateService.delPlanTemplateById(Integer.valueOf(ids[i]));
                }
            }
            opeventLoginLogService.addLog(11,EnumPlnReportTemplate.MODEL_ACTION_DEL.getValue(), 1, 1);
            return null;
        } catch (Exception e) {
            opeventLoginLogService.addLog(11,EnumPlnReportTemplate.MODEL_ACTION_DEL.getValue(), 1, 0);
            log.error("delPlanTemplates action error:" + e.getMessage());
            return ERROR;
        }
    }
    /**
     * 启用停用模板
     */
    public String activePlanTemplate(){
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/html;charset=utf-8");
        try {
            PrintWriter out = response.getWriter();
            if(activeFlag.equals(0)){//启用
                Map<String,Object> hashmap = new HashMap<String,Object>();
                hashmap.put("templateId", plnReportTemplate.getTemplateId());
                PlnReportTemplate plnTemplate = plnReportTemplateService.getPlanTemplate(hashmap);
                Map<String,Object> map = new HashMap<String,Object>();
                map.put("planTypeId", plnTemplate.getPlanTypeId());
                map.put("intervalTypeId", plnTemplate.getIntervalTypeId());
                map.put("isActived", 1);
                PlnReportTemplate template = plnReportTemplateService.getPlanTemplate(map);
                if (template != null) {
                    out.print(EnumPlnReportTemplate.UNIK_ISACTIVE.getValue());
                    return null;
                }else{
                    //启用
                    Map<String,Object> acmap = new HashMap<String,Object>();
                    acmap.put("isActive", 1);
                    acmap.put("templateId", plnReportTemplate.getTemplateId());
                    plnReportTemplateService.activePlanTemplate(acmap);
                }
                
            }else{//停用
                Map<String,Object> acmap = new HashMap<String,Object>();
                acmap.put("isActive", 0);
                acmap.put("templateId", plnReportTemplate.getTemplateId());
                plnReportTemplateService.activePlanTemplate(acmap);
            }
            return null;
        } catch (Exception e) {
            log.error("activePlanTemplate action error:" + e.getMessage());
            return ERROR;
        }
    }
    /**
     * 验证生成报告
     */
    public String validateReport(){
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/html;charset=utf-8");
        try {
            PrintWriter out = response.getWriter();
            JSONObject jso = new JSONObject();
            PlnFastPlan fastPlan = plnFastPlanService.getPlnFastPlanById(fastPlanId);//快速规划对象
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("planTypeId", 1);
            map.put("intervalTypeId", fastPlan.getIntervalTypeId());
            map.put("isActived", 1);
            PlnReportTemplate template = plnReportTemplateService.getPlanTemplate(map);
            if (template == null) {
                jso.put("error", EnumPlnReportTemplate.NO_TEMPALTE.getValue());
//                out.print(EnumPlnReportTemplate.NO_TEMPALTE.getValue());
            }else{
               
                //替换内容
                SysUser user = sysUserService.getSysUserById(fastPlan.getUserId());//用户对象
                templateVarList = plnReportTemplateService.getAllVarList();//所有的标签对象

                PlnReport report = new PlnReport();
                report.setPlanId(fastPlanId);
                report.setContent(templateReplaceContent);
                int reportId = plnReportService.addPlnReport(report);
                jso.put("reportId", reportId);
            }
            out.print(jso.toString());
            out.close();
            return null;
        }catch (Exception e) {
            log.error("validateReport action error:" + e.getMessage());
            return ERROR;
        }
    }
    
    /**
     * 替换模板内容，生成报告
     */
    public String generateReport() {
        try {
            int reportId = Integer.valueOf(req.getParameter("reportId"));
            templateReplaceContent = plnReportService.getReportById(reportId).getContent();
            return SUCCESS;

        } catch (Exception e) {
            log.error("generateReport action error:" + e.getMessage());
            e.printStackTrace();
            return ERROR;
        }
    }
    
    /**
     * getter and setter
     * @param plnReportTemplateService
     */
    public void setPlnReportTemplateService(PlnReportTemplateService plnReportTemplateService) {
        this.plnReportTemplateService = plnReportTemplateService;
    }

    public void setOpeventLoginLogService(OpeventLoginLogService opeventLoginLogService) {
        this.opeventLoginLogService = opeventLoginLogService;
    }

    public void setServletRequest(HttpServletRequest req) {
        this.req = req;
    }

    public HttpServletRequest getReq() {
        return req;
    }

    public void setReq(HttpServletRequest req) {
        this.req = req;
    }

    public PlnReportTemplate getPlnReportTemplate() {
        return plnReportTemplate;
    }

    public void setPlnReportTemplate(PlnReportTemplate plnReportTemplate) {
        this.plnReportTemplate = plnReportTemplate;
    }

    public PageUtil<PlnReportTemplateBean> getTempList() {
        return tempList;
    }

    public void setTempList(PageUtil<PlnReportTemplateBean> tempList) {
        this.tempList = tempList;
    }
    public int getTotalAmount() {
        return totalAmount;
    }
    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }
    public List<PlnPlanType> getPlanTypeList() {
        return planTypeList;
    }
    public void setPlanTypeList(List<PlnPlanType> planTypeList) {
        this.planTypeList = planTypeList;
    }

    public String getTemplateReplaceContent() {
        return templateReplaceContent;
    }

    public void setTemplateReplaceContent(String templateReplaceContent) {
        this.templateReplaceContent = templateReplaceContent;
    }

   
    public String getTemplateIds() {
        return templateIds;
    }

    public void setTemplateIds(String templateIds) {
        this.templateIds = templateIds;
    }

    public Integer getActiveFlag() {
        return activeFlag;
    }

    public void setActiveFlag(Integer activeFlag) {
        this.activeFlag = activeFlag;
    }

    public List<PlnReportTemplateVar> getTemplateVarList() {
        return templateVarList;
    }

    public void setTemplateVarList(List<PlnReportTemplateVar> templateVarList) {
        this.templateVarList = templateVarList;
    }

    public List<PlnVarTypeSub> getTypeTwoVarList() {
        return typeTwoVarList;
    }

    public void setTypeTwoVarList(List<PlnVarTypeSub> typeTwoVarList) {
        this.typeTwoVarList = typeTwoVarList;
    }

    public List<PlnVarType> getTypeOneVarList() {
        return typeOneVarList;
    }

    public void setTypeOneVarList(List<PlnVarType> typeOneVarList) {
        this.typeOneVarList = typeOneVarList;
    }

    public void setSysUserService(SysUserService sysUserService) {
        this.sysUserService = sysUserService;
    }

    public void setCrmCustomerService(CrmCustomerService crmCustomerService) {
        this.crmCustomerService = crmCustomerService;
    }

    public void setPlnFastPlanService(PlnFastPlanService plnFastPlanService) {
        this.plnFastPlanService = plnFastPlanService;
    }

    public Integer getFastPlanId() {
        return fastPlanId;
    }

    public void setFastPlanId(Integer fastPlanId) {
        this.fastPlanId = fastPlanId;
    }

    public String getIntervalName() {
        return intervalName;
    }

    public void setIntervalName(String intervalName) {
        this.intervalName = intervalName;
    }

    public String getPlanTypeName() {
        return planTypeName;
    }

    public void setPlanTypeName(String planTypeName) {
        this.planTypeName = planTypeName;
    }

    public void setPlnReportService(PlnReportService plnReportService) {
        this.plnReportService = plnReportService;
    }
    


}
