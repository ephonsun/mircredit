/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :负责文件的存储
 * Author     :zhangxiang
 * Create Date:2012-8-10
 */
package com.banger.mobile.transport.netfox;

import java.io.*;

/**
 * @author zhangxiang
 * @version $Id: FileAccess.java,v 0.1 2012-8-10 上午11:18:34 zhangxiang Exp $
 */

public class FileAccessI implements Serializable {
    RandomAccessFile oSavedFile;
    long             nPos;

    public FileAccessI() throws IOException {
        this("", 0);
    }

    public FileAccessI(String sName, long nPos) throws IOException {
        oSavedFile = new RandomAccessFile(sName, "rw");
        this.nPos = nPos;
        oSavedFile.seek(nPos);
    }

    public synchronized int write(byte[] b, int nStart, int nLen) {
        int n = -1;
        try {
            oSavedFile.write(b, nStart, nLen);
            n = nLen;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return n;
    }
}
