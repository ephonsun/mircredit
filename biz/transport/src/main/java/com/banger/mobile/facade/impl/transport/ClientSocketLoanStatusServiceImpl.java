/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :ThinkPad
 * Create Date:2013-7-2
 */
package com.banger.mobile.facade.impl.transport;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.banger.mobile.constants.TransportConstants;
import com.banger.mobile.facade.loan.LnLoanInfoService;
import com.banger.mobile.facade.transport.ClientSocketLoanStatusService;
import com.banger.mobile.util.StringUtil;

/**
 * @author ThinkPad
 * @version $Id: ClientSocketLoanStatus.java,v 0.1 2013-7-2 上午10:01:18 ThinkPad Exp $
 */
public class ClientSocketLoanStatusServiceImpl implements ClientSocketLoanStatusService {
    private static final Logger logger = Logger.getLogger(ClientSocketLoanStatusServiceImpl.class);

    private String              interfaceHost;
    private int                 interfacePort;

    private LnLoanInfoService   lnLoanInfoService;

    public LnLoanInfoService getLnLoanInfoService() {
        return lnLoanInfoService;
    }

    public void setLnLoanInfoService(LnLoanInfoService lnLoanInfoService) {
        this.lnLoanInfoService = lnLoanInfoService;
    }

    public String getLoanStatusMessage(String serialNumber) {
        String result = "";
        Socket socket = null;
        InputStream in = null;
        OutputStream out = null;
        BufferedReader buffIn = null;
        BufferedWriter buffOut = null;
        try {
            if (StringUtils.isNotEmpty(serialNumber)) {
                //先对流水号补零
                serialNumber = StringUtil.padRight(serialNumber, 17, ' ');

                //小贷平台发生报文格式：
                //报文长度4位：0026
                //接口号5位：70002
                //流水号17位：SNWD0050113000299
                //sample:002670002SNWD0050113000299
                String message = "002670002" + serialNumber;
                logger.info("DEL70002发送的报文信息数据：" + message);

                socket = new Socket(interfaceHost, interfacePort);
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
                logger.info("DEL70002收到报文..." + msg);
                //String msg = "70001|123456|aaaa|1|1#2013-11-1#123#123#123;2#2013-12-1#123#123#1231231|";

                //报文格式
                //接口号|申请信息里面的流水号|贷款账号|是否放贷成功（1表示已放贷,空或者其他值表示不成功）|期号#还款时间#应还本金#应还利息#贷款余额;期号#还款时间#应还本金#应还利息#贷款余额|

                // 处理业务逻辑
                // 如果放贷成功，则更新贷款状态到已放贷6，然后插入还款计划
                // 更新贷款账号入贷款申请信息表ln_loan_info
                lnLoanInfoService.synchronousLoanStatus(msg);
            }
        } catch (Exception e) {
            logger.error("DEL70002 error...", e);
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
                logger.error("stream close error:", e);
            }
        }

        return result;
    }

    public String getInterfaceHost() {
        return interfaceHost;
    }

    public void setInterfaceHost(String interfaceHost) {
        this.interfaceHost = interfaceHost;
    }

    public int getInterfacePort() {
        return interfacePort;
    }

    public void setInterfacePort(int interfacePort) {
        this.interfacePort = interfacePort;
    }

}
