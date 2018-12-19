/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :ThinkPad
 * Create Date:2013-7-2
 */
package com.banger.mobile.facade.impl.transport;

import com.banger.mobile.constants.TransportConstants;
import com.banger.mobile.facade.transport.ClientSocketCreditsMallService;
import net.sf.json.JSONObject;
import net.sf.json.xml.XMLSerializer;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import java.io.*;
import java.net.Socket;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ThinkPad
 * @version $Id: ClientSocketLoanStatus.java,v 0.1 2013-7-2 上午10:01:18 ThinkPad Exp $
 */
public class ClientSocketCreditsMallServiceImpl implements ClientSocketCreditsMallService {
    private static final Logger logger = Logger.getLogger(ClientSocketCreditsMallServiceImpl.class);

    private String interfaceHost;
    private int interfacePort;
    private Map<String, Object> headMap;

    public String getMessage(Map<String, Object> headMap, Map<String, Object> bodyMap, List<Map<String, Object>> row) {
        String result = "";
        Socket socket = null;
        InputStream in = null;
        OutputStream out = null;
        BufferedReader buffIn = null;
        BufferedWriter buffOut = null;
        String message = "";
        try {
            if (bodyMap.size() > 0) {
                message = maptoXml(headMap, bodyMap, row);
                message = message.trim();
                message = message.replaceAll("\n", "");
                message = message.replace("      ", "");
                message = message.replace("   ", "");
                message = addZeroForNum(message.getBytes().length + "", 8) + message;
                logger.info("发送的报文信息数据：" + message);
                //获取开始时间
                long startTime = System.currentTimeMillis();
                socket = new Socket(interfaceHost, interfacePort);
                //socket.setSoTimeout(30000);

                out = socket.getOutputStream();
                buffOut = new BufferedWriter(new OutputStreamWriter(out, "gbk"));

                // 发送数据
                buffOut.write(message);
                buffOut.flush();

                // 收取数据
                in = socket.getInputStream();
                buffIn = new BufferedReader(new InputStreamReader(in, "gbk"));

                int amount = 0;
                StringBuilder sb = new StringBuilder();
                char[] ch2 = new char[TransportConstants.BUFF_LENGTH];
                while ((amount = buffIn.read(ch2)) != -1) {
                    sb.append(ch2, 0, amount);
                }
                long endTime = System.currentTimeMillis(); //获取结束时间
                logger.info("积分商城收发报文花费时间：..."  + (endTime - startTime) + "ms");
                result = sb.toString();
                logger.info("收到报文..." + result);
                result = getReturnResult(result);
            }
        } catch (Exception e) {
            logger.error("ClientSocketCreditsMallServiceImpl % error...", e);
            return  "error";
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



    /**
     * 根据返回结果返回对应值
     *
     * @param str
     * @return
     */
    public static String getReturnResult(String str) {
        JSONObject jsonStr=new JSONObject();
        if (str.length() > 0) {
            XMLSerializer xmlSer = new XMLSerializer();
            String ss = str.substring(8);
            String temp = xmlSer.read(ss).toString();
            JSONObject json = JSONObject.fromObject(temp);
            try {
                //如果是请求失败(或者没有所要的请求)，响应报文中是不存在<body>的，所以下面这一句可能异常
                String outStr = json.getString("body").replaceAll("\\[\\]", "\"\"");
                jsonStr=JSONObject.fromObject(outStr);
                jsonStr.put("errorcode","000000");
                jsonStr.put("errormsg","");
                return jsonStr.toString();
            } catch (Exception e) {
                jsonStr.put("errorcode",json.getJSONObject("head").getString("errorcode"));
                jsonStr.put("errormsg",json.getJSONObject("head").getString("errormsg"));
                return jsonStr.toString();
            }
        }else{
            jsonStr.put("errorcode","000002");
            jsonStr.put("errormsg","服务器连接超时");
            return jsonStr.toString();
        }
    }

    public static String maptoXml(Map headMap, Map bodyMap, List<Map<String, Object>> row) {
        Document document = DocumentHelper.createDocument();
        Element nodeElement = document.addElement("message");
        Element hElement = nodeElement.addElement("head");
        for (Object obj : headMap.keySet()) {
            Element keyElement = hElement.addElement(String.valueOf(obj));
            keyElement.setText(String.valueOf(headMap.get(obj)));
        }
        Element bElement = nodeElement.addElement("body");
        for (Object obj : bodyMap.keySet()) {
            Element keyElement = bElement.addElement(String.valueOf(obj));
            keyElement.setText(String.valueOf(bodyMap.get(obj)));
        }
        if (row != null) {
            for (int i = 0; i < row.size(); i++) {
                Element rElement = bElement.addElement("row");
                for (Object obj : row.get(i).keySet()) {
                    Element keyElement = rElement.addElement(String.valueOf(obj));
                    keyElement.setText(String.valueOf(row.get(i).get(obj)));
                }
            }
        }
        return doc2String(document);
    }


    /**
     * 数字不足位数左补0
     *
     * @param str
     * @param strLength
     * @return
     */
    public static String addZeroForNum(String str, int strLength) {
        int strLen = str.length();
        if (strLen < strLength) {
            while (strLen < strLength) {
                StringBuffer sb = new StringBuffer();
                sb.append("0").append(str);//左补0
                str = sb.toString();
                strLen = str.length();
            }
        }
        return str;
    }

    /**
     * @param document
     * @return
     */
    public static String doc2String(Document document) {
        String s = "";
        try {
            // 使用输出流来进行转化
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            // 使用UTF-8编码
            OutputFormat format = new OutputFormat("   ", true, "gbk");
            XMLWriter writer = new XMLWriter(out, format);
            writer.write(document);
            s = out.toString("gbk");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return s;
    }

    public void initParameter() {
        headMap = new LinkedHashMap<String, Object>();
        headMap.put("version", "10");
        headMap.put("ApplicationID", "");
        headMap.put("FunctionID", "chl105");
        headMap.put("messageID", "");
        headMap.put("MsgPriority", "0");
        headMap.put("Reserver", "");
        headMap.put("Brc", "101001");
        headMap.put("Teller", "WEBBNK");
        headMap.put("terminalno", "");
        headMap.put("authtellerno", "");
        headMap.put("ChannelId", "");
        headMap.put("hostserno", "2013080120011");
        headMap.put("TermDate", "20130812");
        headMap.put("TermTime", "145400");
    }

    public static void main(String[] ages) {

        String str = "<?xml version=\"1.0\" encoding=\"gbk\"?><message><head><FunctionID>chl108</FunctionID><messageID/><Brc>101001</Brc><Teller>WEBBNK</Teller><terminalno/><authtellerno/><errorcode>000000</errorcode><errormsg/></head><body><orderNo>10000000000</orderNo><giftNumber>2</giftNumber><orderMoney>0.00</orderMoney><orderPoint>10000</orderPoint><tradeDate>20130821092805</tradeDate><orderState>99</orderState><receiveAddress>江苏常熟农商行</receiveAddress><receiveName>张三</receiveName><receivePhone/><receivePostCode>211213</receivePostCode><receiveMobile>15061392718</receiveMobile><sendAddress>上海</sendAddress><sendName>李四</sendName><sendPhone/><sendMobile>13984688148</sendMobile><sendPostCode>223344</sendPostCode><recCount>0</recCount><row><giftNo>123</giftNo><giftName/><giftMoney/><giftPoint/><tradeNumber/><tradeType/></row><row><giftNo>456</giftNo><giftName/><giftMoney/><giftPoint/><tradeNumber/><tradeType/></row></body></message>";
        XMLSerializer xmlSer= new XMLSerializer();
        String temp = xmlSer.read(str).toString();
        JSONObject json = JSONObject.fromObject(temp);
        try {
            String outStr = json.getString("body").replaceAll("\\[\\]","\"\"");
           System.out.println(outStr);
        } catch (Exception e) {
        }

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
