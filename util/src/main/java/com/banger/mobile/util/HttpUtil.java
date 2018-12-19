package com.banger.mobile.util;


import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Set;

/**
 * Created by BH-TCL on 15-2-11.
 */
public class HttpUtil {

    private static final Logger logger = Logger.getLogger(HttpUtil.class);

    /**
     * 专为征信设计
     * @param urlStr
     * @return
     */
    public static String getHttpResponse(String urlStr){
        HttpURLConnection connection =null;
        BufferedReader br = null;
        try {
            URL url=new URL(urlStr);
             connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            br=new BufferedReader(new InputStreamReader(connection.getInputStream(),"gbk"));
            StringBuilder sb=new StringBuilder();
            String lines;
            while((lines=br.readLine())!=null){
                sb.append(lines);
            }
           return sb.toString();
        } catch (Exception e) {
            logger.error("征信调查取结果报错",e);
            return null;
        }finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
