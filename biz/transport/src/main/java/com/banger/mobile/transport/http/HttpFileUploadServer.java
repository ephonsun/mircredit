/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :yuanme
 * Create Date:2012-8-3
 */
package com.banger.mobile.transport.http;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUpload;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.RequestContext;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.servlet.ServletRequestContext;
import org.apache.log4j.Logger;

import com.banger.mobile.constants.TransportConstants;
import com.banger.mobile.facade.param.SysParamService;
import com.banger.mobile.transport.TransportUtil;
import com.banger.mobile.util.FileUtil;
import com.banger.mobile.util.SpringContextUtil;

/**
 * @author yuanme
 * @version HttpFileUploadServer.java,v 0.1 2012-8-3 上午10:50:19
 */
public class HttpFileUploadServer extends HttpServlet {
    private static final long   serialVersionUID = -295510271093612476L;
    private static final Logger logger           = Logger.getLogger(HttpFileUploadServer.class);

    public HttpFileUploadServer() {
        super();
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        SysParamService sysParamService = (SysParamService) SpringContextUtil
            .getBean("sysParamService");

        // 上传路径
        String dir = TransportUtil.getRecordPath(sysParamService)
                     + TransportConstants.UPLOAD_TEMP_DIR;

        RequestContext requestContext = new ServletRequestContext(request);
        FileUtil.createDir(dir);
        FileUtil.createDir(dir + "/android");
        
        //如果是上传文件请求
        if (FileUpload.isMultipartContent(requestContext)) {
            DiskFileItemFactory factory = new DiskFileItemFactory();
            factory.setRepository(new File(dir + "/android"));
            ServletFileUpload upload = new ServletFileUpload(factory);
            //upload.setSizeMax(1024*1024*4);
            List items = new ArrayList();
            try {
                items = upload.parseRequest(request);
            } catch (FileUploadException e) {
                logger.error("文件上传发生错误" + e.getMessage());
            }

            Iterator it = items.iterator();
            while (it.hasNext()) {
                FileItem fileItem = (FileItem) it.next();
                if (fileItem.isFormField()) {
                    logger.info(fileItem.getFieldName() + " " + fileItem.getName() + " "
                                + fileItem.getString());
                } else {
                    logger.info(fileItem.getFieldName() + " " + fileItem.getName() + " "
                                + fileItem.isInMemory() + " " + fileItem.getContentType() + " "
                                + fileItem.getSize());

                    if (fileItem.getName() != null && fileItem.getSize() != 0) {
                        File fullFile = new File(fileItem.getName());
                        File newFile = new File(dir + "/" + fullFile.getName());
                        try {
                            fileItem.write(newFile);
                        } catch (Exception e) {
                            logger.error("保存文件错误", e);
                        }
                    } else {
                        logger.info("文件没有选择 或 文件内容为空");
                    }
                }
            }
        }
    }
}
