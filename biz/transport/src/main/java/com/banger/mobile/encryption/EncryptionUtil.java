/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :加密工具类...
 * Author     :yuanme
 * Create Date:2012-3-28
 */
package com.banger.mobile.encryption;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * 加密工具类
 * @author Administrator
 * @version EncryptionUtil.java,v 0.1 2012-3-28 下午12:08:11 yuanme
 */
public class EncryptionUtil {

    private static final byte XOR_CONST = 8; // 异或加密常量

    /**
     * 加密文件 
     * @param src 原始文件(例如13912348888_201203031112332.mov)
     * @param dest 加密后文件，文件名同原始文件，后缀为.e(例如13912348888_201203031112332.e)
     * @throws Exception 异常
     */
    public static void encryptFile(File src, File dest) throws Exception {
        FileInputStream fis = new FileInputStream(src);
        FileOutputStream fos = new FileOutputStream(dest);
        byte[] bs = new byte[1024];
        int len = 0;
        while ((len = fis.read(bs)) != -1) {
            for (int i = 0; i < len; i++) {
                bs[i] ^= XOR_CONST;
            }
            fos.write(bs, 0, len);
        }
        fos.close();
        fis.close();

    }

    /**
     * 解密文件
     * @param src 加密文件(例如13912348888_201203031112332.e)
     * @param dest 原始文件(例如13912348888_201203031112332.mov)
     * @throws Exception 异常
     */
    public static void decryptFile(File src, File dest) throws Exception {
        encryptFile(src, dest);
    }
}
