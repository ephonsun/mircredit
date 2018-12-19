/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :QianJie
 * Create Date:Nov 29, 2012
 */
package com.banger.mobile.facade.impl.ueditor;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * @author QianJie
 * @version $Id: ImageManagerServlet.java,v 0.1 Nov 29, 2012 4:12:36 PM QianJie Exp $
 */
public class ImageManagerServlet extends HttpServlet {

    private static final long   serialVersionUID = -1392598676522491932L;

    private static final Logger logger           = Logger.getLogger(ImageManagerServlet.class);

    public ImageManagerServlet() {
        super();
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        PrintWriter out = null;
        String path = "c:/record";
        String imgStr = "";
        try {
            out = response.getWriter();
            //String realpath = getRealPath(request, path) + "/" + path;
            String realpath = path;
            List<File> files = getFiles(realpath, new ArrayList());
            for (File file : files) {
                imgStr += file.getPath().replace(getRealPath(request, path), "") + "ue_separate_ue";
            }
            if (imgStr != "") {
                imgStr = imgStr.substring(0, imgStr.lastIndexOf("ue_separate_ue")).replace(
                    File.separator, "/").trim();
            }
        } catch (Exception e) {
            logger.error(e);
        }
        out.print(imgStr);
    }

    private List getFiles(String realpath, List files) {

        File realFile = new File(realpath);
        if (realFile.isDirectory()) {
            File[] subfiles = realFile.listFiles();
            for (File file : subfiles) {
                if (file.isDirectory()) {
                    getFiles(file.getAbsolutePath(), files);
                } else {
                    if (!getFileType(file.getName()).equals("")) {
                        files.add(file);
                    }
                }
            }
        }
        return files;
    }

    private String getRealPath(HttpServletRequest request, String path) {
        ServletContext application = request.getSession().getServletContext();
        String str = application.getRealPath(request.getServletPath());
        return new File(str).getParent();
    }

    private String getFileType(String fileName) {
        String[] fileType = { ".gif", ".png", ".jpg", ".jpeg", ".bmp" };
        Iterator<String> type = Arrays.asList(fileType).iterator();
        while (type.hasNext()) {
            String t = type.next();
            if (fileName.endsWith(t)) {
                return t;
            }
        }
        return "";
    }
}
