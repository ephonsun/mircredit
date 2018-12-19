/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :md5工具类...
 * Author     :yuanme
 * Create Date:2012-4-6
 */
package com.banger.mobile.encryption;

import java.io.File;
import java.security.MessageDigest;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

/**
 * @author yuanme
 * @version MD5Util.java,v 0.1 2012-4-6 上午10:09:28
 */
public class MD5Util {
    private static final Logger logger = Logger.getLogger(MD5Util.class);

    /**
     * 得到文件md5
     * @param f 目标文件
     * @return 目标文件md5
     */
    public static String getFileMD5(File f) {
        String result = "";
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(FileUtils.readFileToByteArray(f));
            StringBuilder sb = new StringBuilder();
            for (byte b : md5.digest()) {
                sb.append(String.format("%02X", b));
            }
            result = sb.toString();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return result;
    }
}
