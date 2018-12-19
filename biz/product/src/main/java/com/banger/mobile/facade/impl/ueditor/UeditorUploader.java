/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :UEditor文件上传辅助类
 * Author     :QianJie
 * Create Date:Nov 29, 2012
 */
package com.banger.mobile.facade.impl.ueditor;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

import org.apache.commons.fileupload.*;
import org.apache.commons.fileupload.FileUploadBase.InvalidContentTypeException;
import org.apache.commons.fileupload.FileUploadBase.SizeLimitExceededException;
import org.apache.commons.fileupload.util.*;
import org.apache.commons.fileupload.servlet.*;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.log4j.Logger;

import com.banger.mobile.util.FileUtil;

import sun.misc.BASE64Decoder;
import javax.servlet.http.HttpServletRequest;

/**
 * @author QianJie
 * @version $Id: UeditorUploader.java,v 0.1 Nov 29, 2012 2:36:50 PM QianJie Exp $
 */
public class UeditorUploader {
    private static final Logger logger           = Logger.getLogger(UeditorUploader.class);
    
    private String                  url          = "";                            // 输出文件地址
    private String                  fileName     = "";                            // 上传文件名
    private String                  state        = "";                            // 状态
    private String                  type         = "";                            // 文件类型
    private String                  originalName = "";                            // 原始文件名
    private String                  size         = "";                            // 文件大小
    private HttpServletRequest      request      = null;
    private String                  title        = "";
    private String                  savePath     = "upload";                      // 保存路径
    private String[]                allowFiles   = { ".rar", ".doc", ".docx", ".zip", ".pdf",
            ".txt", ".swf", ".wmv", ".gif", ".png", ".jpg", ".jpeg", ".bmp" };    // 文件允许格式
    private int                     maxSize      = 10000;                         // 文件大小限制，单位KB
    private HashMap<String, String> errorInfo    = new HashMap<String, String>();

    public UeditorUploader(HttpServletRequest request) {
        this.request = request;
        HashMap<String, String> tmp = this.errorInfo;
        tmp.put("SUCCESS", "SUCCESS"); //默认成功
        tmp.put("NOFILE", "未包含文件上传域");
        tmp.put("TYPE", "不允许的文件格式");
        tmp.put("SIZE", "文件大小超出限制");
        tmp.put("ENTYPE", "请求类型ENTYPE错误");
        tmp.put("REQUEST", "上传请求异常");
        tmp.put("IO", "IO异常");
        tmp.put("DIR", "目录创建失败");
        tmp.put("UNKNOWN", "未知错误");

    }

    public void upload() throws Exception {
        boolean isMultipart = ServletFileUpload.isMultipartContent(this.request);
        if (!isMultipart) {
            this.state = this.errorInfo.get("NOFILE");
            return;
        }
        DiskFileItemFactory dff = new DiskFileItemFactory();
        String savePath = this.getFolder(this.savePath);
        dff.setRepository(new File(savePath));
        try {
            ServletFileUpload sfu = new ServletFileUpload(dff);
            sfu.setSizeMax(this.maxSize * 1024);
            sfu.setHeaderEncoding("UTF-8");
            //FileItemIterator fii = sfu.getItemIterator(this.request);
            List items = new ArrayList();
            items = sfu.parseRequest(request);//解析请求
            Iterator it = items.iterator();
            while (it.hasNext()) {
                FileItem fileItem = (FileItem) it.next();
                if (!fileItem.isFormField()) {
                    this.originalName = fileItem.getName().substring(
                        fileItem.getName().lastIndexOf(System.getProperty("file.separator")) + 1);
                    if (!this.checkFileType(this.originalName)) {
                        this.state = this.errorInfo.get("TYPE");
                        continue;
                    }
                    this.fileName = this.getName(this.originalName);
                    this.type = this.getFileExt(this.fileName);
                    this.url = savePath + "/" + this.fileName;
                    //File fullFile = new File(this.fileName);
                    File newFile = new File(this.url);
                    try {
                        fileItem.write(newFile);
                    } catch (Exception e) {
                        logger.error(e.getMessage(), e);
                    }
//                    BufferedInputStream in = new BufferedInputStream(fis.openStream());
//                    FileOutputStream out = new FileOutputStream(new File(this
//                        .getPhysicalPath(this.url)));
//                    BufferedOutputStream output = new BufferedOutputStream(out);
//                    Streams.copy(in, output, true);
                    this.state = this.errorInfo.get("SUCCESS");
                    //UE中只会处理单张上传，完成后即退出
                    //break;
                } else {
                    String fname = fileItem.getFieldName();
                    //只处理title，其余表单请自行处理
                    if (!fname.equals("pictitle")) {
                        continue;
                    }
                    BufferedInputStream in = new BufferedInputStream(fileItem.getInputStream());
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                    StringBuffer result = new StringBuffer();
                    while (reader.ready()) {
                        result.append((char) reader.read());
                    }
                    this.title = new String(result.toString().getBytes(), "utf-8");
                    reader.close();

                }
            }
        } catch (SizeLimitExceededException e) {
            this.state = this.errorInfo.get("SIZE");
        } catch (InvalidContentTypeException e) {
            this.state = this.errorInfo.get("ENTYPE");
        } catch (FileUploadException e) {
            this.state = this.errorInfo.get("REQUEST");
        } catch (Exception e) {
            this.state = this.errorInfo.get("UNKNOWN");
        }
    }

    /**
     * 接受并保存以base64格式上传的文件
     * @param fieldName
     */
    public void uploadBase64(String fieldName) {
        String savePath = this.getFolder(this.savePath);
        String base64Data = this.request.getParameter(fieldName);
        this.fileName = this.getName("test.png");
        this.url = savePath + "/" + this.fileName;
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            File outFile = new File(this.getPhysicalPath(this.url));
            OutputStream ro = new FileOutputStream(outFile);
            byte[] b = decoder.decodeBuffer(base64Data);
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {
                    b[i] += 256;
                }
            }
            ro.write(b);
            ro.flush();
            ro.close();
            this.state = this.errorInfo.get("SUCCESS");
        } catch (Exception e) {
            this.state = this.errorInfo.get("IO");
        }
    }

    /**
     * 文件类型判断
     * 
     * @param fileName
     * @return
     */
    private boolean checkFileType(String fileName) {
        Iterator<String> type = Arrays.asList(this.allowFiles).iterator();
        while (type.hasNext()) {
            String ext = type.next();
            if (fileName.toLowerCase().endsWith(ext)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 获取文件扩展名
     * 
     * @return string
     */
    private String getFileExt(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }

    /**
     * 依据原始文件名生成新文件名
     * @return
     */
    private String getName(String fileName) {
        Random random = new Random();
        return this.fileName = "" + random.nextInt(10000) + System.currentTimeMillis()
                               + this.getFileExt(fileName);
    }

    /**
     * 根据字符串创建本地目录 并按照日期建立子目录返回
     * @param path 
     * @return 
     */
    private String getFolder(String path) {
        SimpleDateFormat formater = new SimpleDateFormat("yyyyMMdd");
        path += "/" + formater.format(new Date());
        FileUtil.createDir(path);
//        File dir = new File(this.getPhysicalPath(path));
//        if (!dir.exists()) {
//            try {
//                dir.mkdirs();
//            } catch (Exception e) {
//                this.state = this.errorInfo.get("DIR");
//                return "";
//            }
//        }
        return path;
    }

    /**
     * 根据传入的虚拟路径获取物理路径
     * 
     * @param path
     * @return
     */
    private String getPhysicalPath(String path) {
        String servletPath = this.request.getServletPath();
        String realPath = this.request.getSession().getServletContext().getRealPath(servletPath);
        return new File(realPath).getParent() + "/" + path;
    }

    public void setSavePath(String savePath) {
        this.savePath = savePath;
    }

    public void setAllowFiles(String[] allowFiles) {
        this.allowFiles = allowFiles;
    }

    public void setMaxSize(int size) {
        this.maxSize = size;
    }

    public String getSize() {
        return this.size;
    }

    public String getUrl() {
        return this.url;
    }

    public String getFileName() {
        return this.fileName;
    }

    public String getState() {
        return this.state;
    }

    public String getTitle() {
        return this.title;
    }

    public String getType() {
        return this.type;
    }

    public String getOriginalName() {
        return this.originalName;
    }
}