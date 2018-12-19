package com.banger.mobile.service;

import org.apache.commons.io.FilenameUtils;

/**
 * Created with IntelliJ IDEA. User: Administrator Date: 13-5-13 Time: 上午10:45
 * To change this template use File | Settings | File Templates.
 */
public class TestMeth {
    public static void main(String[] args) {
        String fileName = "pic_cusId_time_重命名.xml.ok";
        fileName =  FilenameUtils.getBaseName(FilenameUtils.getBaseName(fileName));

        int index = fileName.lastIndexOf("_");

        String s = fileName.substring(index + 1);
        System.out.println(s);
    }

}
