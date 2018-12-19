/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :yuanme
 * Create Date:2012-8-3
 */
package com.banger.mobile.transport.http;

import com.banger.mobile.domain.model.data.CustomerData;
import com.banger.mobile.facade.param.SysParamService;
import com.banger.mobile.facade.uploadFile.SysUploadFileService;
import com.banger.mobile.util.SpringContextUtil;
import com.banger.mobile.util.StringUtil;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * @author yuanme
 * @version HttpFileUploadServer.java, v 0.1 2012-8-3 上午10:50:19
 */
public class ShowImageServlet extends HttpServlet {
    private static final long serialVersionUID = -295510271093612476L;
    private static final Logger logger = Logger.getLogger(ShowImageServlet.class);

    public ShowImageServlet() {
        super();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            SysParamService sysParamService = (SysParamService) SpringContextUtil
                    .getBean("sysParamService");
            SysUploadFileService sysUploadFileService = (SysUploadFileService) SpringContextUtil
                    .getBean("sysUploadFileService");

            String fname = request.getParameter("fname");
            String folder = request.getParameter("folder");
            String type = StringUtil.getNotNullValue(request.getParameter("type"));
            String fullPath = StringUtil.getNotNullValue(request.getParameter("fullPath"));
            String fileId = StringUtil.getNotNullValue(request.getParameter("fileId"));
            String cmsPath = StringUtil.getNotNullValue(request.getParameter("cmsPath"));
            String cmsFileName = StringUtil.getNotNullValue(request.getParameter("cmsFileName"));
            String cmsFileId = StringUtil.getNotNullValue(request.getParameter("cmsFileId"));

            FileInputStream file = null;
            if (cmsPath.length() > 0) { //影像系统文件全路径
                File realFile = sysUploadFileService.getRealFile(cmsPath, cmsFileName, cmsFileId);
                file = new FileInputStream(realFile);
            } else if (fileId.length() > 0 && fileId.length() < 13) {//是否是根据fileId去文件表取，可能是第三方接口，比如cm的影像  长度大于13表示为路径
                Integer userId = sysUploadFileService.getSysUploadFileById(Integer.parseInt(fileId)).getUploadUserId();
                CustomerData data = new CustomerData();
                data.setCreateUser(userId);
                File realFile = sysUploadFileService.getRealFile(Integer.valueOf(fileId), data);
                file = new FileInputStream(realFile);
            } else if (fileId.length() > 13) {//为全路径的处理
                return;
            } else if (fullPath.length() > 0) {//是否是全路径
                String s = new String(fullPath.getBytes("ISO8859_1"), "gb2312");
                file = new FileInputStream(s);
            } else { //参数路径
                String path = ""; //服务器存储路径
                if (type.equals("customer")) {
                    path = sysParamService.getCustomerHeadShowPath();
                } else if (type.equals("mms") || type.equals("")) {
                    // 图片存储路径
                    path = sysParamService.getMmsImagePath();
                }
                file = new FileInputStream(path + File.separator + folder + File.separator + fname);
            }

            response.setContentType("image/*"); //设置返回的文件类型 
            OutputStream output = response.getOutputStream(); //得到向客户端输出二进制数据的对象 
            BufferedInputStream bis = new BufferedInputStream(file);//输入缓冲流      
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
            logger.error("显示图片 error...", e);
        }
    }
}
