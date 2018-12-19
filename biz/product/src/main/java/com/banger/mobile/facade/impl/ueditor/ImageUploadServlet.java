/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :UEditor文件上传Servlet
 * Author     :QianJie
 * Create Date:Nov 29, 2012
 */
package com.banger.mobile.facade.impl.ueditor;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * @author QianJie
 * @version $Id: UeditorServlet.java,v 0.1 Nov 29, 2012 2:48:41 PM QianJie Exp $
 */
public class ImageUploadServlet extends HttpServlet{

    private static final long serialVersionUID = 2723439421313457424L;

    private static final Logger logger           = Logger.getLogger(ImageUploadServlet.class);

    public ImageUploadServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
    	doPost(req, resp);
    }
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        UeditorUploader up = new UeditorUploader(request);
        PrintWriter out = null;
        try {
            out = response.getWriter();
            request.setCharacterEncoding("UTF-8");
            //response.setContentType("text/html;charset=utf-8");

            up.setSavePath("c:/record");
            String[] fileType = {".gif" , ".png" , ".jpg" , ".jpeg" , ".bmp"};
            up.setAllowFiles(fileType);
            up.setMaxSize(10000); //单位KB
            up.upload();
        } catch (Exception e) {
            logger.error(e);
        }
        out.print("{'original':'"+up.getOriginalName()+"','url':'"+up.getUrl()+"','title':'"+up.getTitle()+"','state':'"+up.getState()+"'}");
        
    }
}
