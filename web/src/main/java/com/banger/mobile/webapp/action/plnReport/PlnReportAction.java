/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :Administrator
 * Create Date:Jul 25, 2012
 */
package com.banger.mobile.webapp.action.plnReport;

import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.model.base.customer.BaseCrmCustomer;
import com.banger.mobile.domain.model.plnReport.PlnReport;
import com.banger.mobile.domain.model.plnReport.PlnReportInfo;
import com.banger.mobile.facade.customer.CrmCustomerService;
import com.banger.mobile.facade.dept.DeptFacadeService;
import com.banger.mobile.facade.plnReport.PlnReportService;
import com.banger.mobile.util.StringUtil;
import com.banger.mobile.webapp.action.BaseAction;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.html.simpleparser.HTMLWorker;
import com.itextpdf.text.html.simpleparser.StyleSheet;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * @author Administrator
 * @version $Id: PlnReportAction.java,v 0.1 Jul 25, 2012 2:45:26 PM Administrator Exp $
 */
public class PlnReportAction extends BaseAction {

    private static final long       serialVersionUID = -6026718863140964651L;
    private PlnReportService        plnReportService;
    private PlnReport               plnReport;
    private PageUtil<PlnReportInfo> plnReportList;
    private Integer                 count;
    private Integer                 fastPlanId;
    private String                  content;
    private Integer                 plnReportId;
    private CrmCustomerService      crmCustomerService;
    private PlnReportInfo           reportInfo;
    private DeptFacadeService        deptFacadeService;                       //权限service

    public void setCrmCustomerService(CrmCustomerService crmCustomerService) {
        this.crmCustomerService = crmCustomerService;
    }

    /**
     *  添加报告
     */
    public void addPlnReport() {

        try {
           
        } catch (RuntimeException e) {
            log.error("addPlnReport action error", e);
        }
    }

    /**
     * 列表
     * @return
     */
    public String getAllReportPage() {
        try {
            String BelongToType =request.getParameter("BelongToType");//客户经理
            Map<String, Object> map = new HashMap<String, Object>();
            if (reportInfo != null) {
                map.put("customerNo", StringUtil.ReplaceSQLChar(reportInfo.getCustomerNo().trim()));
                map.put("customerName", StringUtil.ReplaceSQLChar(reportInfo.getCustomerName().trim()));
                map.put("planName", StringUtil.ReplaceSQLChar(reportInfo.getPlanName().trim()));
            }
            map.put("planType", request.getParameter("planType"));
            map.put("startDate", request.getParameter("startDate"));
            map.put("endDate", request.getParameter("endDate"));
            if (request.getParameter("selectId") != null) {
                if (request.getParameter("selectId").equals("1")) {//我的
                    map.put("userId", getLoginInfo().getUserId());
                    request.setAttribute("userId", getLoginInfo().getUserId());
                } else {//下属的
                    map.put("userId", request.getParameter("userId"));
                    request.setAttribute("userId", request.getParameter("userId"));
                }
            }
            if(BelongToType!=null){
                if(BelongToType.equals("brMine")){
                    map.put("userId", getLoginInfo().getUserId());
                }else if(BelongToType.equals("brUser")){
                    map.put("userId", request.getParameter("userIds"));
                }
            }
            String role="";
            if(deptFacadeService.isInChargeOfDepartment()){
                role="1";
            }else if(deptFacadeService.isCommon()){
                role="2";
            }
            request.setAttribute("role", role);
            request.setAttribute("customerNo", request.getParameter("customerNo"));
            request.setAttribute("customerName", request.getParameter("customerName"));
            request.setAttribute("planName", request.getParameter("planName"));
            request.setAttribute("planType", request.getParameter("planType"));
            request.setAttribute("startDate", request.getParameter("startDate"));
            request.setAttribute("endDate", request.getParameter("endDate"));
            request.setAttribute("selectId", request.getParameter("selectId"));
            request.setAttribute("userName", request.getParameter("userName"));

            plnReportList = this.plnReportService.getAllReport(map, this.getPage());
            count = this.getPage().getTotalRowsAmount();
            return SUCCESS;
        } catch (RuntimeException e) {
            log.error("getAllReportPage action error",e);
            return ERROR;
        }
    }
    /**
     * 查询
     * @return
     */
    public String getAllReportPageForQuery() {
        try {
            String BelongToType =request.getParameter("BelongToType");//客户经理
            Map<String, Object> map = new HashMap<String, Object>();
            if (reportInfo != null) {
                if(!StringUtil.isEmpty(reportInfo.getCustomerNo()))
                map.put("customerNo", StringUtil.ReplaceSQLChar(reportInfo.getCustomerNo().trim()));
                if(!StringUtil.isEmpty(reportInfo.getCustomerName()))
                map.put("customerName", StringUtil.ReplaceSQLChar(reportInfo.getCustomerName().trim()));
                if(!StringUtil.isEmpty(reportInfo.getPlanName()))
                map.put("planName", StringUtil.ReplaceSQLChar(reportInfo.getPlanName().trim()));
            }
            map.put("planType", request.getParameter("planType"));
            map.put("startDate", request.getParameter("startDate"));
            map.put("endDate", request.getParameter("endDate"));
            if(BelongToType!=null){
                if(BelongToType.equals("brMine")){
                    map.put("userId", getLoginInfo().getUserId());
                }else if(BelongToType.equals("brUser")){
                    String userIds=request.getParameter("userIds");
                    if(StringUtil.isEmpty(userIds)){
                        userIds=deptFacadeService.getUserIdsBelongToManager();
                    }
                    map.put("userId", userIds);
                }
            }else{
                map.put("userId", getLoginInfo().getUserId());
            }
            String role="";
            if(deptFacadeService.isInChargeOfDepartment()){
                role="1";
            }else if(deptFacadeService.isCommon()){
                role="2";
            }
            request.setAttribute("role", role);
            request.setAttribute("customerNo", request.getParameter("customerNo"));
            request.setAttribute("customerName", request.getParameter("customerName"));
            request.setAttribute("planName", request.getParameter("planName"));
            request.setAttribute("planType", request.getParameter("planType"));
            request.setAttribute("startDate", request.getParameter("startDate"));
            request.setAttribute("endDate", request.getParameter("endDate"));
            request.setAttribute("selectId", request.getParameter("selectId"));
            request.setAttribute("userName", request.getParameter("userName"));

            plnReportList = this.plnReportService.getAllReport(map, this.getPage());
            count = this.getPage().getTotalRowsAmount();
            return SUCCESS;
        } catch (RuntimeException e) {
            log.error("getAllReportPage action error",e);
            return ERROR;
        }
    }

    
    /**
     * 根据客户id查看客户是否删除
     * 
     */
    public void getReportCustomerIsDel(){
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/html;charset=utf-8");
        try {
            PrintWriter out = response.getWriter();
            String customerId=request.getParameter("customerId");
            if(customerId==null||customerId.length()==0){
                out.print("3");
            }else{
                if(crmCustomerService.getCrmCustomerById(Integer.parseInt(customerId))==null){
                    out.print("3");
                }else if(crmCustomerService.isDelCustomer(Integer.parseInt(customerId))){
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
     * 删除
     * @return
     */
    public String deleteReport() {
        try {
            String id = request.getParameter("plnReportId");
            this.plnReportService.deletePlnReport(id);
            return null;
        } catch (RuntimeException e) {
            log.error("deleteReport action error", e);
            return ERROR;
        }
    }

    /**
     * 导出PDF文件
     * @return
     */
    public void downloadPDFFile() {
        try {
            HttpServletResponse resp = ServletActionContext.getResponse();

            plnReport = this.plnReportService.getReportById(plnReportId);
            Document doc = new Document(PageSize.A4, 48, 48, 48, 48);
            doc.addTitle("这是标题！！！！！");

            ByteArrayOutputStream os = new ByteArrayOutputStream();
            PdfWriter pw = PdfWriter.getInstance(doc, os);

            StringBuffer sb = new StringBuffer();
            sb.append(plnReport.getContent().toString());

            StyleSheet st = new StyleSheet();
            st.loadTagStyle("body", "leading", "16,0");
            doc.open();

            float[] widths = { 140f, 60f, 320f, 120f, 110f, 110f, 190f };//设置表格的列宽

            PdfPTable table = new PdfPTable(widths);//建立一个pdf表格
            table.setSpacingBefore(130f);//设置表格上面空白宽度
            table.setTotalWidth(535);//设置表格的宽度             
            table.setLockedWidth(true);//设置表格的宽度固定
            //table.getDefaultCell().setBorder(0);//设置表格默认为无边框

            BaseFont baseFontChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H",
                BaseFont.NOT_EMBEDDED);
            Font fontChinese = new Font(baseFontChinese, 12, Font.NORMAL);
            Paragraph paragraph = new Paragraph();
            paragraph.setFont(fontChinese);
            ArrayList p = HTMLWorker.parseToList(new StringReader(sb.toString()), st);
            for (int k = 0; k < p.size(); k++) {
                paragraph.add((Element) p.get(k));
            }

            //            Chunk chunk = new Chunk();
            //            paragraph.add(chunk);
            doc.add(paragraph);
            doc.close();
            resp.setContentType("application/PDF;charset=utf-8");
            resp.setContentLength(os.size());
            resp.setHeader("Content-disposition", "attachment; filename="
                                                  + System.currentTimeMillis() + ".pdf");
            ServletOutputStream out = resp.getOutputStream();
            os.writeTo(out);
            out.flush();
            out.close();

        } catch (Exception e) {
            log.error("downloadPDFFile action error", e);
        }

    }

    public PlnReportService getPlnReportService() {
        return plnReportService;
    }

    public void setPlnReportService(PlnReportService plnReportService) {
        this.plnReportService = plnReportService;
    }

    public PlnReport getPlnReport() {
        return plnReport;
    }

    public void setPlnReport(PlnReport plnReport) {
        this.plnReport = plnReport;
    }

    public PageUtil<PlnReportInfo> getPlnReportList() {
        return plnReportList;
    }

    public void setPlnReportList(PageUtil<PlnReportInfo> plnReportList) {
        this.plnReportList = plnReportList;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getFastPlanId() {
        return fastPlanId;
    }

    public void setFastPlanId(Integer fastPlanId) {
        this.fastPlanId = fastPlanId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getPlnReportId() {
        return plnReportId;
    }

    public void setPlnReportId(Integer plnReportId) {
        this.plnReportId = plnReportId;
    }

    public PlnReportInfo getReportInfo() {
        return reportInfo;
    }

    public void setReportInfo(PlnReportInfo reportInfo) {
        this.reportInfo = reportInfo;
    }

    public void setDeptFacadeService(DeptFacadeService deptFacadeService) {
        this.deptFacadeService = deptFacadeService;
    }
    
}
