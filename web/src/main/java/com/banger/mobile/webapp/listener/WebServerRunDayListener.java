/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :系统运行监听器...
 * Author     :cheny
 * Create Date:2012-9-6
 */
package com.banger.mobile.webapp.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

import com.banger.mobile.domain.model.sysRunDays.SysRunDays;
import com.banger.mobile.facade.sysRunDays.SysRunDaysService;

/**
 * @author cheny
 * @version $Id: WebServerRunDayListener.java,v 0.1 2012-9-6 下午1:12:15 cheny Exp $
 */
public class WebServerRunDayListener implements ServletContextListener {
    /** Logger. */
    private static final Logger logger = Logger.getLogger(WebServerRunDayListener.class);

    public void contextInitialized(ServletContextEvent sce) {
        try {
            logger.info("WebServerRunDayListener listener startup: ");
//            System.out.println("服务器开启了***************");
            SysRunDaysService sysRunDaysService = (SysRunDaysService)com.banger.mobile.util.SpringContextUtil.getBean("sysRunDaysService");
            SysRunDays runDay = new SysRunDays();
            runDay.setRunType(1);
            sysRunDaysService.insertSysRunDays(runDay);
        } catch (Exception e) {
            logger.error("WebServerRunDayListener error:" + e.getMessage());
        }
    }

    public void contextDestroyed(ServletContextEvent sce) {
//        System.out.println("服务器关闭了***************");
        SysRunDaysService sysRunDaysService = (SysRunDaysService)com.banger.mobile.util.SpringContextUtil.getBean("sysRunDaysService");
        SysRunDays runDay = new SysRunDays();
        runDay.setRunType(0);
        sysRunDaysService.insertSysRunDays(runDay);
        
    }

}
