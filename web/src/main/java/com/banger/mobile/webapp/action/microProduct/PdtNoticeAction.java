/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :通知-Action
 * Author     :QianJie
 * Create Date:Dec 11, 2012
 */
package com.banger.mobile.webapp.action.microProduct;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.model.microProduct.PdtNotice;
import com.banger.mobile.facade.microProduct.PdtNoticeService;
import com.banger.mobile.util.StringUtil;
import com.banger.mobile.webapp.action.BaseAction;

/**
 * @author QianJie
 * @version $Id: PdtNoticeAction.java,v 0.1 Dec 11, 2012 3:05:57 PM QianJie Exp $
 */
public class PdtNoticeAction extends BaseAction {

    private static final long   serialVersionUID = -116083828905983015L;

    private PdtNoticeService    pdtNoticeService;                       //通知Service
    private PageUtil<PdtNotice> pdtNoticePage;                          //通知分类查询
    private PdtNotice           pdtNotice;                              //通知实体

    public void setPdtNoticeService(PdtNoticeService pdtNoticeService) {
        this.pdtNoticeService = pdtNoticeService;
    }

    public PageUtil<PdtNotice> getPdtNoticePage() {
        return pdtNoticePage;
    }

    public void setPdtNoticePage(PageUtil<PdtNotice> pdtNoticePage) {
        this.pdtNoticePage = pdtNoticePage;
    }

    
    public PdtNotice getPdtNotice() {
        return pdtNotice;
    }

    public void setPdtNotice(PdtNotice pdtNotice) {
        this.pdtNotice = pdtNotice;
    }

    /**
     * 查询通知
     * @return
     */
    public String queryPdtNoticeList() {
        try {
            Map<String, Object> conds = new HashMap<String, Object>();
            if (pdtNotice != null) {
                if(pdtNotice.getIsRead() != -1){
                    conds.put("isRead", pdtNotice.getIsRead());
                }
                conds.put("noticeName", pdtNotice.getNoticeName());
                conds.put("createStartDate", repairDateTime(request.getParameter("createStartDate"),"S"));
                conds.put("createEndDate", repairDateTime(request.getParameter("createEndDate"),"E"));
            }
            conds.put("userId", this.getLoginInfo().getUserId());
            pdtNoticePage = pdtNoticeService.getPdtNoticePage(conds, this.getPage());
            int count = this.getPage().getTotalRowsAmount();
            request.setAttribute("count", String.valueOf(count));
            return SUCCESS;
        } catch (Exception e) {
            log.error(e);
            return ERROR;
        }

    }
    
    private String repairDateTime(Object obj,String repairType){
        String repairDate = "";
        if(obj != null ){
            if(StringUtil.isNotEmpty(obj.toString())){
                if(repairType.equals("S")){
                    repairDate = obj.toString() + " 00:00:00";
                }else{
                    repairDate = obj.toString() + " 23:59:59";
                }
            }
        }
        return repairDate;
    }
    
    /**
     * 下载通知附件
     */
    public void downloadPdtNoticeAttachment() {
        int noticeId = Integer.parseInt(request.getParameter("noticeId"));
        PdtNotice item = pdtNoticeService.getPdtNoticeById(noticeId);
        try {
            File file = new File(item.getFilePath()+"/"+item.getFileName());
            FileInputStream fis = new FileInputStream(file);//服务器文件路径
            getResponse().addHeader("Content-Disposition", "attachment;filename="+new String(item.getNoticeName().getBytes("gbk"), "iso8859-1"));
            getResponse().addHeader("Content-Length", "" + file.length());

            getResponse().setContentType("application/octet-stream"); //设置返回的文件类型 
            OutputStream output = getResponse().getOutputStream(); //得到向客户端输出二进制数据的对象 
            BufferedInputStream bis = new BufferedInputStream(fis);//输入缓冲流      
            BufferedOutputStream bos = new BufferedOutputStream(output);//输出缓冲流     

            byte data[] = new byte[4096];//缓冲字节数    

            int size = 0;
            size = bis.read(data);
            while (size != -1) {
                bos.write(data, 0, size);
                size = bis.read(data);
            }
            bis.close();
            bos.flush();//清空输出缓冲流     
            bos.close();
            output.close();
        } catch (Exception e) {
            log.error(e);
        }
    }
    
    /**
     * 修改通知已读未读状态
     */
    public void editPdtNoticeRead(){
        PrintWriter out = null;
        try {
            out = this.getResponse().getWriter();
            Map<String, Object> conds = new HashMap<String, Object>();
            conds.put("noticeIds", request.getParameter("noticeIds"));
            conds.put("readState", request.getParameter("readState"));
            conds.put("userId", this.getLoginInfo().getUserId());
            pdtNoticeService.editPdtNoticeReadByConds(conds);
            out.write("SUCCESS");
        } catch (Exception ex) {
            out.write("修改通知状态失败");
            log.error(ex);
        }
    }
}
