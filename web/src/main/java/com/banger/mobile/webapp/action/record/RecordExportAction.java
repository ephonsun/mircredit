/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :录音导出...
 * Author     :cheny
 * Create Date:2012-9-11
 */
package com.banger.mobile.webapp.action.record;

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
import com.banger.mobile.domain.Enum.record.EnumRecord;
import com.banger.mobile.domain.model.crmModuleExport.CrmModuleExport;
import com.banger.mobile.domain.model.customer.CustomerExportBar;
import com.banger.mobile.facade.crmModuleExport.CrmModuleExportService;
import com.banger.mobile.facade.impl.record.RecordExportServiceImpl;
import com.banger.mobile.facade.record.RecordExportService;
import com.banger.mobile.facade.template.CrmTemplateService;
import com.banger.mobile.webapp.action.BaseAction;

/**
 * @author cheny
 * @version $Id: RecordExportAction.java,v 0.1 2012-9-11 下午12:56:36 cheny Exp $
 */
public class RecordExportAction extends BaseAction{

    private static final long serialVersionUID = -8976011386310284505L;
    
    private RecordExportService         recordExportService;   //客户导出service
    private CrmModuleExportService      crmModuleExportService;  //导出字段service
    private CrmTemplateService          temp;                    //业务模板service
    private Map<String, String>         baseColumn;
    private List<String>                feilds = new ArrayList<String>(); //勾选的字段
    
    


  
    public CrmTemplateService getTemp() {
        return temp;
    }
    public void setTemp(CrmTemplateService temp) {
        this.temp = temp;
    }
    public void setRecordExportService(RecordExportService recordExportService) {
        this.recordExportService = recordExportService;
    }
    public void setCrmModuleExportService(CrmModuleExportService crmModuleExportService) {
        this.crmModuleExportService = crmModuleExportService;
    }
    /**
     * 导出客户第一步
     * @return
     */
    public String showRecordExport(){
        try {
            List<CrmModuleExport> list = crmModuleExportService.getCrmModuleExportList("record");
            if(list!= null && list.size() >0){
                for (CrmModuleExport crmModuleExport : list) {
                    feilds.add(crmModuleExport.getFeildName());
                }
            }
            baseColumn = recordExportService.initParameter();
            this.request.setAttribute("userId", this.getLoginInfo().getUserId());
        } catch (Exception e) {
            log.error("showRecordExport action error:"+e.getMessage());
           return ERROR;
        }
        return SUCCESS;
    }
 
    /**
     * 导出验证
     */
    public String exportRecordVerify(){
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/html;charset=utf-8");
        try {
            PrintWriter out = response.getWriter();
            Map<Integer,CustomerExportBar> customerExportBar = RecordExportServiceImpl.getCustomerExportBar();
            CustomerExportBar bar = customerExportBar.get(this.getLoginInfo().getUserId());
            if(bar != null){
                if(bar.getIsRun().equals(1) && bar.getIsStop().equals(0) ) {//正在运行并且没有终止
                    bar.setIsRun(0);
                    bar.setIsStop(1);
                    out.print(EnumRecord.EXPORT_WARN.getValue());
                }
            }
            bar.setIsRun(1);
            bar.setIsStop(0);
            return null;
        }catch (Exception e) {
            log.error("exportRecordVerify action error:"+e.getMessage());
            return null;
        }
    }
    
    /**
     * 导出进度条
     * @return
     */
    public String exportRecordBar(){
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/html;charset=utf-8");
        String userId = request.getParameter("userId");
        try {
            PrintWriter out = response.getWriter();
            JSONObject jso = new JSONObject();
            Map<Integer,CustomerExportBar> customerExportBar = RecordExportServiceImpl.getCustomerExportBar();
            CustomerExportBar bar = customerExportBar.get(Integer.parseInt(userId));
            if(bar != null){    
                jso.put("curr", bar.getCuurRow());
            }
            out.print(jso.toString());
            return null;
        }catch (Exception e) {
            log.error("exportRecordBar action error:"+e.getMessage());
            return ERROR;
        }
        
      
    }
    
    
    /**
     * 执行导出
     * @return
     */
    public String exportRecordExute(){
        try {
            String path = FileUploadPathConstants.REC_EXPORT_PATH+"/record_export"+this.getLoginInfo().getUserId()+".xls";
            int type = Integer.valueOf((String)request.getParameter("type"));
            String filename = "";
            if(type == 0) filename="联系记录.xls";
            else if(type == 1) filename="通话记录.xls";
            else if(type == 2) filename="座谈记录.xls";
            else if(type == 3) filename="拜访记录.xls";
            else if(type == 4) filename="短信记录.xls";
            else if(type == 5) filename="彩信记录.xls";
            
            // 服务器绝对路径
            path = this.request.getRealPath("/") + path;
            ServletOutputStream out = this.getResponse().getOutputStream();
            this.getResponse().setHeader("Content-Disposition", "attachment; filename=" + java.net.URLEncoder.encode(filename, "UTF-8"));
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
            log.error("exportRecordExute action error:"+e.getMessage());
            return ERROR;
        }
 
    }
    /**
     * 终止导出
     * @return
     */
    public String exportRecordStop(){
        try {
            Map<Integer,CustomerExportBar> customerExportBar = RecordExportServiceImpl.getCustomerExportBar();
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
    public List<String> getFeilds() {
        return feilds;
    }
    public void setFeilds(List<String> feilds) {
        this.feilds = feilds;
    }


}
