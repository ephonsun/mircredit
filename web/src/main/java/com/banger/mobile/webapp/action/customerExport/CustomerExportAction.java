/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :客户导出action...
 * Author     :cheny
 * Create Date:2012-8-23
 */
package com.banger.mobile.webapp.action.customerExport;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.banger.mobile.constants.FileUploadPathConstants;
import com.banger.mobile.domain.Enum.customer.EnumCustomer;
import com.banger.mobile.domain.model.crmModuleExport.CrmModuleExport;
import com.banger.mobile.domain.model.customer.CustomerExportBar;
import com.banger.mobile.domain.model.template.CrmTemplate;
import com.banger.mobile.facade.crmModuleExport.CrmModuleExportService;
import com.banger.mobile.facade.customer.CrmCustomerService;
import com.banger.mobile.facade.customer.CustomerExportService;
import com.banger.mobile.facade.impl.customer.CustomerExportServiceImpl;
import com.banger.mobile.facade.impl.record.RecordExportServiceImpl;
import com.banger.mobile.facade.template.CrmTemplateService;
import com.banger.mobile.webapp.action.BaseAction;

/**
 * @author cheny
 * @version $Id: CustomerExportAction.java,v 0.1 2012-8-23 下午1:54:55 cheny Exp $
 */
public class CustomerExportAction extends BaseAction{

    private static final long serialVersionUID = -1982201631011604938L;
    
    private CrmCustomerService    crmCustomerService;      //客户管理service 
    private CustomerExportService customerExportService;   //客户导出service
    private CrmTemplateService    temp;                    //业务模板service
    private CrmModuleExportService crmModuleExportService;  //导出字段service
    private Map<String, String>   baseColumn;
    private List<CrmTemplate>     templateList;
    private int                   totalCount;             //excel总条数
    
    private List<String>                feilds = new ArrayList<String>();                 //勾选的字段
    
    
    public void setCrmCustomerService(CrmCustomerService crmCustomerService) {
        this.crmCustomerService = crmCustomerService;
    }
    public void setTemp(CrmTemplateService temp) {
        this.temp = temp;
    }
    public void setCustomerExportService(CustomerExportService customerExportService) {
        this.customerExportService = customerExportService;
    }
    public void setCrmModuleExportService(CrmModuleExportService crmModuleExportService) {
        this.crmModuleExportService = crmModuleExportService;
    }
    /**
     * 导出客户第一步
     * @return
     */
    public String showExportPage(){
        List<CrmModuleExport> list = crmModuleExportService.getCrmModuleExportList("customer");
        if(list!= null && list.size() >0){
            for (CrmModuleExport crmModuleExport : list) {
                feilds.add(crmModuleExport.getFeildName());
            }
        }
        baseColumn = customerExportService.initParameter();
        return SUCCESS;
    }
 
    /**
     * 导出验证
     */
    public String exportCustVerify(){
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/html;charset=utf-8");
        try {
            PrintWriter out = response.getWriter();
            Map<Integer,CustomerExportBar> customerExportBar = CustomerExportServiceImpl.getCustomerExportBar();
            CustomerExportBar bar = customerExportBar.get(this.getLoginInfo().getUserId());
            if(bar != null){
                if(bar.getIsRun().equals(1) && bar.getIsStop().equals(0) ) {//正在运行并且没有终止
                    bar.setIsRun(0);
                    bar.setIsStop(1);
                    out.print(EnumCustomer.EXPORT_WARN.getValue());
                }
            }
            bar.setIsRun(1);
            bar.setIsStop(0);
            return null;
        }catch (Exception e) {
            return null;
        }
    }
    
    /**
     * 导出进度条
     * @return
     */
    public String exportCustomerBar(){
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/html;charset=utf-8");
        try {
            PrintWriter out = response.getWriter();
            JSONObject jso = new JSONObject();
            Map<Integer,CustomerExportBar> customerExportBar = CustomerExportServiceImpl.getCustomerExportBar();
            CustomerExportBar bar = customerExportBar.get(this.getLoginInfo().getUserId());
            if(bar!= null) {
                jso.put("curr", bar.getCuurRow());
            }
            out.print(jso.toString());
            return null;
        }catch (Exception e) {
            return ERROR;
        }
        
      
    }
    
    
    /**
     * 执行导出
     * @return
     */
    public String exportCustomerExute(){
        try {
            String path = FileUploadPathConstants.CRM_EXPORT_PATH+"/crm_export"+this.getLoginInfo().getUserId()+".xls";
            // 服务器绝对路径
            path = this.request.getRealPath("/") + path;
            ServletOutputStream out = this.getResponse().getOutputStream();
            this.getResponse().setHeader("Content-Disposition", "attachment; filename=" + java.net.URLEncoder.encode("客户资料.xls", "UTF-8"));
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
            
            return null;
        } catch (Exception e) {
            log.error("exportCustomerExute action error:"+e.getMessage());
            return ERROR;
        }
 
    }
    /**
     * 终止导出
     * @return
     */
    public String exportCustomerStop(){
        try {
            Map<Integer,CustomerExportBar> customerExportBar = CustomerExportServiceImpl.getCustomerExportBar();
            CustomerExportBar bar = customerExportBar.get(this.getLoginInfo().getUserId());
            if(bar.getIsRun().equals(1)){
                bar.setCuurRow(0);
                bar.setIsRun(0);
                bar.setIsStop(1);//正在导出时终止
            }
            return null;
        } catch (Exception e) {
            log.error("exportRecordStop action error:"+e.getMessage());
            return ERROR;
        }
 
    }
    
    
    
    public Map<String, String> getBaseColumn() {
        return baseColumn;
    }
    public void setBaseColumn(Map<String, String> baseColumn) {
        this.baseColumn = baseColumn;
    }
    public List<CrmTemplate> getTemplateList() {
        return templateList;
    }
    public void setTemplateList(List<CrmTemplate> templateList) {
        this.templateList = templateList;
    }
    public CrmTemplateService getTemp() {
        return temp;
    }
    public int getTotalCount() {
        return totalCount;
    }
    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }
    public List<String> getFeilds() {
        return feilds;
    }
    public void setFeilds(List<String> feilds) {
        this.feilds = feilds;
    }



    
}
