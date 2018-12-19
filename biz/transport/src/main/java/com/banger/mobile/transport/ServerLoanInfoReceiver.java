/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :server file receiver
 * Author     :yuanme
 * Create Date:2012-4-1
 */
package com.banger.mobile.transport;

import com.banger.mobile.constants.TransportConstants;
import com.banger.mobile.domain.model.transport.TransportConfig;
import com.banger.mobile.facade.clusters.ClustersConfigService;
import com.banger.mobile.util.SpringContextUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * server file receiver
 * @author yuanme
 * @version ServerFileReceiver.java,v 0.1 2012-4-6 下午2:17:25
 */
public class ServerLoanInfoReceiver implements Runnable {

    protected transient final Log     log      = LogFactory.getLog(getClass());

    private static ServerLoanInfoReceiver instance = null;

    // 线程池
    private ExecutorService           executorService;
    // 监听端口
    private int                       port;

    // 退出
    private boolean                   quit     = false;
    private ServerSocket              server;

    protected ServerLoanInfoReceiver() {
    }

    /**
     * 单例模式
     * @return ServerFileReceiver
     */
    public static ServerLoanInfoReceiver getInstance() {
        if (instance == null) {
            int serverPort = TransportConstants.SERVER_LOAN_PORT;
            instance = new ServerLoanInfoReceiver(serverPort);
        }
        return instance;
    }

    private ServerLoanInfoReceiver(int port) {
        this.port = port;
        // 创建线程池
        executorService = Executors.newFixedThreadPool(TransportConstants.QUEUE_SIZE);
//        executorService = Executors.newCachedThreadPool();
    }

    /**
     * 启动服务
     * 
     * @throws Exception
     */
    public void run() {
        // 实现端口监听
        try {
            server = new ServerSocket(port);
        } catch (IOException e1) {
            log.error(e1);
        }
        if (server != null) {
            while (!quit) {
                try {
                    Socket socket = server.accept();

                    // 为支持多用户并发访问，采用线程池管理每一个用户的连接请求
                    executorService.execute(new ServerSocketLoan(socket));
                } catch (Exception e) {
                    log.error("ServerFileReceiver run error:", e);
                }
            }
        }
    }

    /**
     * 退出
     */
    public void quit() {
        this.quit = true;
        try {
            executorService.shutdown();
            instance=null;
            server.close();
        } catch (IOException e) {
            log.error("ServerFileReceiver quit  error:", e);
        }
    }

    public int getPort() {
        return port;
    }
}