/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :系统参数
 * Author     :liyb
 * Create Date:2012-9-8
 */
package com.banger.mobile.util;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;

import chenmin.io.DiskID;


/**
 * @author liyb
 * @version $Id: SystemParm.java,v 0.1 2012-9-8 下午02:27:05 liyb Exp $
 */
public class SystemParmUtil {
    public static String getOSName() {
        return System.getProperty("os.name").toLowerCase();
    }

    /**    
     * 获取widnows硬盘序列号    
     * @return 硬盘序列号    
     */
    public static String getWindowsMACAddress() {
        String diskId=DiskID.DiskID();
        return diskId;
    }
    
    /**
     * 读取Linux下磁盘分区的UUID
     * 1、查看服务器型号：dmidecode | grep 'Product Name'
     * 2、查看主板的序列号：dmidecode |grep 'Serial Number'
     * 3、查看系统序列号：dmidecode -s system-serial-number
     * 4、查看系统信息：dmidecode -t 1
     * @return
     */
    public static String getHDForLinux(){
        String sn = "";
        String command="dmidecode -t 1";
        try {
            Process p = Runtime.getRuntime().exec(command);
            BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            System.err.println("command "+command);
            System.err.println("br.readLine() \n"+br.readLine());
            while ((line = br.readLine()) != null) {
                if (line.contains("UUID:")) {
                    int index = line.indexOf("UUID") + "UUID:".length() + 1;
                    sn = line.substring(index);
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sn;
    }
}
