/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :测试类
 * Author     :zhangxiang
 * Create Date:2012-8-10
 */
package com.banger.mobile.transport.netfox;

/**
 * @author zhangxiang
 * @version $Id: TestMethod.java,v 0.1 2012-8-10 上午11:58:04 zhangxiang Exp $
 */
public class TestMethod {

    public TestMethod() { ///xx/weblogic60b2_win.exe
        try {
            SiteInfoBean bean = new SiteInfoBean("http://localhost:8080/pad-app/myeclipse-7.0-win32.exe",
                "c:\\tmp", "weblogic60b2_win.exe", 5);
            //SiteInfoBean bean = new SiteInfoBean("http://localhost:8080/down.zip","L:\\temp","weblogic60b2_win.exe",5);
            SiteFileFetch fileFetch = new SiteFileFetch(bean);
            fileFetch.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new TestMethod();
    }
}
