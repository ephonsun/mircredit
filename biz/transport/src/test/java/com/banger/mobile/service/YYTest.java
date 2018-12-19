/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :Administrator
 * Create Date:2012-10-9
 */
package com.banger.mobile.service;

import java.io.File;
import java.math.BigDecimal;
import java.util.*;

import banger.json.JsonArray;
import banger.json.convert.JsonConvert;
import com.banger.mobile.util.JsonDateValueProcessor;
import com.sun.java.util.jar.pack.*;

import it.sauronsoftware.jave.*;


import net.sf.json.JSONArray;
import org.apache.commons.lang.StringEscapeUtils;

import com.banger.mobile.util.MoneyUtil;
import com.banger.mobile.util.TypeUtil;
import org.junit.Test;

/**
 * @author Administrator
 * @version $Id: YYTest.java,v 0.1 2012-10-9 下午2:31:38 Administrator Exp $
 */
public class YYTest {

    /**
     * @param args
     */
    public static void main(String[] args) throws EncoderException {
        Double d = new Double(1.2231211123112312E8);
        System.out.println(TypeUtil.changeType(d, String.class));
        
        String userName = "1' or '1'='1";
        String password = "123456";
        userName = StringEscapeUtils.escapeSql(userName);
        password = StringEscapeUtils.escapeSql(password);
        String sql = "SELECT COUNT(userId) FROM t_user WHERE userName='"
        + userName + "' AND password ='" + password + "'";
        System.out.println(sql);
        
        String strhtml = "<h1>ddd</h1>";
        strhtml =  StringEscapeUtils.escapeHtml(strhtml);
        System.out.println(strhtml);

        System.out.println(MoneyUtil.getFormatMoney("123123123123"));

        UUID uuid = UUID.randomUUID();
        System.out.println(uuid.toString())    ;

        File source = new File("d:\\1.wav");
        File target = new File("d:\\1.mp3");
        AudioAttributes audio = new AudioAttributes();
        audio.setCodec("libmp3lame");
        //audio.setBitRate(new Integer(128000));
        //audio.setChannels(new Integer(2));
        //audio.setSamplingRate(new Integer(44100));
        EncodingAttributes attrs = new EncodingAttributes();
        attrs.setFormat("mp3");
        attrs.setAudioAttributes(audio);
        Encoder encoder = new Encoder();
        encoder.encode(source, target, attrs);
    }
    @Test
    public void test(){

        String str ="1.2123E3";
        BigDecimal amount = new BigDecimal(str);
        System.out.println("amount = " + amount);
    }

}
