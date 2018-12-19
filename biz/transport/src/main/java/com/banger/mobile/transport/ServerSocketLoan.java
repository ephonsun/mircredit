/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :流量限制类...
 * Author     :yuanme
 * Create Date:2012-4-5
 */
package com.banger.mobile.transport;

import com.banger.mobile.constants.TransportConstants;
import com.banger.mobile.facade.loan.LnLoanInfoService;
import com.banger.mobile.util.SpringContextUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.*;
import java.net.Socket;
import java.util.Properties;

/**
 * @author yuanme
 * @version ServerSocketTask.java,v 0.1 2012-4-5 上午10:04:41
 */
public class ServerSocketLoan implements Runnable {
    private static final Logger logger = Logger.getLogger(ServerSocketLoan.class);
    private Socket              socket = null;

    public ServerSocketLoan(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        LnLoanInfoService lnLoanInfoService = (LnLoanInfoService) SpringContextUtil
            .getBean("lnLoanInfoService");
        InputStream in = null;
        OutputStream out = null;
        BufferedReader buffIn = null;
        BufferedWriter buffOut = null;
        try {
            String remoteIp = socket.getRemoteSocketAddress().toString();
            Resource resource = new ClassPathResource("/global.properties");
            Properties props = PropertiesLoaderUtils.loadProperties(resource);
            String exclusion = props.getProperty("socket.remote.ip.exclusion");
            boolean isExclusion = false;
            if (StringUtils.isNotEmpty(exclusion) && StringUtils.isNotEmpty(remoteIp)) {
                String[] exclusions = exclusion.split(",");
                for(String exc : exclusions) {
                    if (remoteIp.contains(exc)) {
                        isExclusion = true;
                        break;
                    }
                }
            }

            if (isExclusion) {
//                logger.info("socket.remote.ip.exclusion..." + remoteIp);
            } else {
                socket.setSoTimeout(TransportConstants.READ_TIMEOUT);
                in = socket.getInputStream();
                out = socket.getOutputStream();
                buffIn = new BufferedReader(new InputStreamReader(in, "UTF-8"));
                buffOut = new BufferedWriter(new OutputStreamWriter(out));
                logger.info("开始接收报文...");
                // 收取数据
                int amount = 0;
                StringBuilder sb = new StringBuilder();
                char[] ch2 = new char[TransportConstants.BUFF_LENGTH];
                while ((amount = buffIn.read(ch2)) != -1) {
                    sb.append(ch2, 0, amount);
                }
                String msg = sb.toString();
                logger.info("收到报文..." + msg);

                // 处理业务逻辑
                lnLoanInfoService.addLoanInfoByLoan(msg);
            }
        } catch (Exception e) {
            logger.error("ServerSocketLoan error:", e);
        } finally {
            try {
                if (socket != null) {
                    socket.close();
                    socket = null;
                }
                if (in != null) {
                    in.close();
                    in = null;
                }
                if (out != null) {
                    out.close();
                    out = null;
                }
                if (buffIn != null) {
                    buffIn.close();
                    buffIn = null;
                }
                if (buffOut != null) {
                    buffOut.close();
                    buffOut = null;
                }
            } catch (IOException e) {
                logger.error("ServerSocketLoan stream close error:", e);
            }
        }
    }
}