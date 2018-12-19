/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :工具类，放一些简单的方法
 * Author     :zhangxiang
 * Create Date:2012-8-10
 */
package com.banger.mobile.transport.netfox;

/**
 * @author zhangxiang
 * @version $Id: Utility.java,v 0.1 2012-8-10 上午11:17:18 zhangxiang Exp $
 */
public class Utility {

    public Utility() {
    }

    public static void sleep(int nSecond) {
        try {
            Thread.sleep(nSecond);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void log(String sMsg) {
        System.err.println(sMsg);
    }

    public static void log(int sMsg) {
        System.err.println(sMsg);
    }
}
