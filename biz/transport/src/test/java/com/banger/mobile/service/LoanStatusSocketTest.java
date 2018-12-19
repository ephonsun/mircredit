package com.banger.mobile.service;

import com.banger.mobile.constants.TransportConstants;
import com.banger.mobile.util.StringUtil;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.concurrent.CountDownLatch;

public class LoanStatusSocketTest {

    /**
     * @param args
     * @throws Exception 
     */
    public static void main(String[] args) throws Exception {
        String result = "";
        Socket socket = null;
        InputStream in = null;
        OutputStream out = null;
        BufferedReader buffIn = null;
        BufferedWriter buffOut = null;
        try {
            //先对流水号补零
            String serialNumber = "SNWD2290114000141";

            //小贷平台发生报文格式：
            //报文长度4位：0026
            //接口号5位：70002
            //流水号17位：SNWD0050113000299
            //sample:002670002SNWD0050113000299
            String message = "002670002" + serialNumber;
            System.out.println("DEL70002发送的报文信息数据：" + message);

            socket = new Socket("170.100.120.51", 12008);
            socket.setSoTimeout(30000);

            out = socket.getOutputStream();
            buffOut = new BufferedWriter(new OutputStreamWriter(out, "UTF-8"));

            // 发送数据
            buffOut.write(message);
            buffOut.flush();

            // 收取数据
            in = socket.getInputStream();
            buffIn = new BufferedReader(new InputStreamReader(in, "UTF-8"));

            int amount = 0;
            StringBuilder sb = new StringBuilder();
            char[] ch2 = new char[TransportConstants.BUFF_LENGTH];
            while ((amount = buffIn.read(ch2)) != -1) {
                sb.append(ch2, 0, amount);
            }
            String msg = sb.toString();
            System.out.println("DEL70002收到报文..." + msg);

            //报文格式
            //接口号|申请信息里面的流水号|贷款账号|是否放贷成功（1表示已放贷,空或者其他值表示不成功）|期号#还款时间#应还本金#应还利息#贷款余额;期号#还款时间#应还本金#应还利息#贷款余额|
            
            // 处理业务逻辑
            // 如果放贷成功，则更新贷款状态到已放贷6，然后插入还款计划
            // 更新贷款账号入贷款申请信息表ln_loan_info
            
        } catch (Exception e) {
            e.printStackTrace();
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
                e.printStackTrace();
            }
        }
    }

}
