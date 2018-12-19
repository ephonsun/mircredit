/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :负责部分文件的抓取
 * Author     :zhangxiang
 * Create Date:2012-8-10
 */
package com.banger.mobile.transport.netfox;

import java.io.*;
import java.net.*;

/**
 * @author zhangxiang
 * @version $Id: FileSplitterFetch.java,v 0.1 2012-8-10 上午11:20:42 zhangxiang Exp $
 */

public class FileSplitterFetch extends Thread {
    String      sURL;               //File URL
    long        nStartPos;          //File Snippet Start Position
    long        nEndPos;            //File Snippet End Position
    int         nThreadID;          //Thread's ID
    boolean     bDownOver   = false; //Downing is over
    boolean     bStop       = false; //Stop identical
    FileAccessI fileAccessI = null; //File Access interface

    public FileSplitterFetch(String sURL, String sName, long nStart, long nEnd, int id)
                                                                                       throws IOException {
        this.sURL = sURL;
        this.nStartPos = nStart;
        this.nEndPos = nEnd;
        nThreadID = id;
        fileAccessI = new FileAccessI(sName, nStartPos);
    }

    public void run() {
        while (nStartPos < nEndPos && !bStop) {
            try {
                URL url = new URL(sURL);
                HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
                httpConnection.setRequestProperty("User-Agent", "NetFox");
                String sProperty = "bytes=" + nStartPos + "-";
                httpConnection.setRequestProperty("RANGE", sProperty);
                Utility.log(sProperty);
                InputStream input = httpConnection.getInputStream();
                //logResponseHead(httpConnection);
                byte[] b = new byte[1024];
                int nRead;
                while ((nRead = input.read(b, 0, 1024)) > 0 && nStartPos < nEndPos && !bStop) {
                    nStartPos += fileAccessI.write(b, 0, nRead);
                    //if(nThreadID == 1)
                    // Utility.log("nStartPos = " + nStartPos + ", nEndPos = " + nEndPos);
                }
                Utility.log("Thread " + nThreadID + " is over!");
                bDownOver = true;
                //nPos = fileAccessI.write (b,0,nRead);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //打印回应的头信息
    public void logResponseHead(HttpURLConnection con) {
        for (int i = 1;; i++) {
            String header = con.getHeaderFieldKey(i);
            if (header != null)
                //responseHeaders.put(header,httpConnection.getHeaderField(header));
                Utility.log(header + " : " + con.getHeaderField(header));
            else
                break;
        }
    }

    public void splitterStop() {
        bStop = true;
    }
}
