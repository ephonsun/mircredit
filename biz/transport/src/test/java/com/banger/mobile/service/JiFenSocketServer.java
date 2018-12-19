/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :ThinkPad
 * Create Date:2013-8-15
 */
package com.banger.mobile.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.banger.mobile.constants.TransportConstants;

/**
 * @author ThinkPad
 * @version $Id: JiFenSocketServer.java,v 0.1 2013-8-15 上午09:11:40 ThinkPad Exp $
 */
public class JiFenSocketServer {
    protected static Log logger = LogFactory.getLog(JiFenSocketServer.class);

    /**
     * @param args
     */
    public static void main(String[] args) {
        ServerSocket server = null;
        // 实现端口监听
        try {
            server = new ServerSocket(9999);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        if (server != null) {
            while (true) {
                try {
                    Socket socket = server.accept();

                    InputStream in = null;
                    OutputStream out = null;
                    BufferedReader buffIn = null;
                    BufferedWriter buffOut = null;
                    try {
                        socket.setSoTimeout(TransportConstants.READ_TIMEOUT);
                        in = socket.getInputStream();
                        out = socket.getOutputStream();
                        buffIn = new BufferedReader(new InputStreamReader(in, "UTF-8"));

                        // 收取数据
                        int amount = 0;
                        StringBuilder sb = new StringBuilder();
                        char[] ch2 = new char[TransportConstants.BUFF_LENGTH];
                        while ((amount = buffIn.read(ch2)) != -1) {
                            sb.append(ch2, 0, amount);
                        }
                        String msg = sb.toString();
                        logger.info("收到..." + msg);

                        out = socket.getOutputStream();
                        buffOut = new BufferedWriter(new OutputStreamWriter(out, "UTF-8"));

                        // 发送数据
                        buffOut.write(msg);
                        buffOut.flush();
                        logger.info("发送..." + msg);

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
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
