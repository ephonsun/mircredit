/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :pdf导出...
 * Author     :liyb
 * Create Date:2012-8-1
 */
package com.banger.mobile.webapp.action.plnReport;

import java.awt.Insets;
import java.io.ByteArrayOutputStream;
import java.io.StringReader;
import java.util.ArrayList;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;
import org.zefer.pd4ml.PD4Constants;
import org.zefer.pd4ml.PD4ML;

import com.banger.mobile.domain.model.plnReport.PlnReport;
import com.banger.mobile.domain.model.plnReport.PlnReportInfo;
import com.banger.mobile.facade.plnReport.PlnReportService;
import com.banger.mobile.util.DateUtil;
import com.banger.mobile.util.StringUtil;
import com.banger.mobile.webapp.action.BaseAction;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.html.simpleparser.HTMLWorker;
import com.lowagie.text.html.simpleparser.StyleSheet;
import com.lowagie.text.pdf.PdfWriter;

/**
 * @author liyb
 * @version $Id: PdfExportAction.java,v 0.1 2012-8-1 下午02:33:06 liyb Exp $
 */
@SuppressWarnings("serial")
public class PdfExportAction extends BaseAction {
    
    private PlnReportService        plnReportService;
    private PlnReport               plnReport;
    private Integer                 plnReportId;
    private PlnReportInfo           plnReportInfo;
    
    /**
     * 通过itext导出pdf
     * 未使用
     */
    public void exportPDF(){
        try {
            HttpServletResponse resp = ServletActionContext.getResponse();
            plnReport = this.plnReportService.getReportById(plnReportId);
            Document document = new Document( PageSize.A4 , 80 , 50 , 30 , 65 );
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            PdfWriter.getInstance(document, os);
            StyleSheet styles = new StyleSheet();
            styles.loadTagStyle("ol" , "leading" , "16,0");
            // 取得系统字体的文件夹
            String system=System.getProperty("os.name").toLowerCase();
            if(system.startsWith("windows")){
                FontFactory.register("C:\\Windows\\Fonts\\simsun.ttc");
            }else{
                FontFactory.register(ServletActionContext.getRequest().getRealPath("fonts")+"/simsun.ttc");
            }
            
            styles.loadTagStyle("li" , "face" , "SIMSUN" );
            styles.loadTagStyle("span" , "size" , "8px" );
            styles.loadStyle("sf" , "color" , "blue" );
            styles.loadStyle("sf" , "b" , "" );
            styles.loadStyle("classic" , "color" , "red" );
            styles.loadStyle("classic" , "i" , "" );
            styles.loadTagStyle("td" , "face" , "SIMSUN" );
            styles.loadTagStyle("td" , "encoding" , "Identity-H" );
            styles.loadTagStyle("td" , "leading" , "16,0" );
            styles.loadTagStyle("body" , "face" , "SIMSUN" );
            styles.loadTagStyle("body" , "encoding" , "Identity-H" );
            styles.loadTagStyle("body" , "leading" , "10,0" ); 
            document.open();
            StringBuilder sb = new StringBuilder();
            sb.append(plnReport.getContent());
            ArrayList objects = HTMLWorker.parseToList( new StringReader(sb.toString()) , styles);
            for ( int k = 0 ; k < objects.size() ; ++k ){
                document.add((Element)objects.get(k));
            } 
            document.close();
            
            resp.setContentType("application/PDF;charset=utf-8");
            resp.setContentLength(os.size());
            resp.setHeader("Content-disposition", "attachment; filename=coop007.pdf");
            ServletOutputStream out = resp.getOutputStream();
            os.writeTo(out);
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 通过PD4ML导出pdf
     */
    public void pd4mlPDF(){
        try {
            HttpServletResponse resp = ServletActionContext.getResponse();
            plnReportInfo=plnReportService.getPlnReportInfoById(plnReportId);
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            StringBuilder sb = new StringBuilder();
            sb.append(plnReportInfo.getContent());
            StringReader strReader = new StringReader(sb.toString());
            PD4ML pd4ml = new PD4ML();
            pd4ml.setPageInsets(new Insets(5, 20, 20, 20));
            pd4ml.setHtmlWidth(1000);
            pd4ml.setPageSize(pd4ml.changePageOrientation(PD4Constants.A4));
            pd4ml.useTTF("java:fonts", true);
            pd4ml.setDefaultTTFs("SimHei", "Arial", "Courier New");
            pd4ml.enableDebugInfo();
            pd4ml.render(strReader, os);
            resp.setContentType("application/PDF;charset=utf-8");
            resp.setContentLength(os.size());
            String fileName=plnReportInfo.getCustomerName()+"_"+DateUtil.convertDateToString("yyyyMMdd",plnReportInfo.getPlanDate());
            if(!StringUtil.isBlank(plnReportInfo.getCustomerNo())){
                fileName=fileName+"_"+plnReportInfo.getCustomerNo();
            }
            fileName=fileName+"_"+plnReportInfo.getPlanTypeName();
            resp.setHeader("Content-disposition", "attachment; filename="+new String(fileName.getBytes("gbk"),"iso8859-1")+".pdf");
            resp.setCharacterEncoding("UTF-8");
            ServletOutputStream out = resp.getOutputStream();
            os.writeTo(out);
            out.flush();
            os.close();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
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

    public Integer getPlnReportId() {
        return plnReportId;
    }

    public void setPlnReportId(Integer plnReportId) {
        this.plnReportId = plnReportId;
    }

    public PlnReportInfo getPlnReportInfo() {
        return plnReportInfo;
    }

    public void setPlnReportInfo(PlnReportInfo plnReportInfo) {
        this.plnReportInfo = plnReportInfo;
    }
}
