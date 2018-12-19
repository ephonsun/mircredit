package com.banger.mobile.service;

import com.banger.mobile.constants.TransportConstants;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.concurrent.CountDownLatch;

public class LoanSocketTest {

    /**
     * @param args
     * @throws Exception 
     */
    public static void main(String[] args) throws Exception {
        Socket socket = null;
        String s = null;
        String msg = "";
        String host_name = "192.168.1.188";
        int port = 8888;
        try {

            s = "70001|CRD_WXDXF_DJ|2013-07-09|SNWD0050113000299|支佳梅|1987-06-13|1|320581198706133849|01||||13962482808|52175299|1|20||||||||||||||||||||||||100000.00|买车|12||||曹怡|320581198511012721||||||||";

            System.err.println("DEL70001发送的报文信息数据：" + s);
            socket = new Socket(host_name, port);
            socket.setSoTimeout(30000);

            OutputStream out = socket.getOutputStream();
            BufferedWriter bufout = new BufferedWriter(new OutputStreamWriter(out, "UTF-8"));

            // 发送数据
            bufout.write(s);
            bufout.flush();

            out.close();
            bufout.close();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("在传输过程中出现错误!");
        }
        System.out.println(msg);
    }

}
