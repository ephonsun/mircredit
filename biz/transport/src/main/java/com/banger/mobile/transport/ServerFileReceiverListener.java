/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :yuanme
 * Create Date:Sep 12, 2012
 */
package com.banger.mobile.transport;

import org.apache.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @author yuanme
 * @version $Id: QuartzTaskListener.java,v 0.1 2012-8-17 下午2:03:18 yuanme Exp $
 */
public class ServerFileReceiverListener implements ServletContextListener {

    /**
     * Logger.
     */
    private static final Logger logger = Logger.getLogger(ServerFileReceiverListener.class);
    private static ServerFileReceiver server = null;
    private static ServerLoanInfoReceiver serverLoan = null;

    /**
     * @param sce
     * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce) {
        try {
            server = ServerFileReceiver.getInstance();
            Thread thread = new Thread(server);
            thread.start();
            logger.info("record file server startup: " + server.getPort());
            //信贷系统接口socket监听
            //serverLoan = ServerLoanInfoReceiver.getInstance();
            //Thread threadLoan = new Thread(serverLoan);
            //threadLoan.start();
            //logger.info("loan socket server startup: " + serverLoan.getPort());
        } catch (Exception e) {
            logger.error("ServerFileReceiverListener error:" + e);
        }
    }

    /**
     * @param sce
     * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent sce) {
        try {
            if (server != null) {
                server.quit();
                server = null;
            }

            if (serverLoan != null) {
                serverLoan.quit();
                serverLoan = null;
            }
        } catch (Exception e) {
            logger.error("stop ServerFileReceiverListener error:" + e);
        }
    }

}
