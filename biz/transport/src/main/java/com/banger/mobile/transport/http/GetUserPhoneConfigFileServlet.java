/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :yuanme
 * Create Date:2012-8-3
 */
package com.banger.mobile.transport.http;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.banger.mobile.domain.model.answerConfig.AnswerConfig;
import com.banger.mobile.domain.model.crmRecordRemind.CrmRecordRemind;
import com.banger.mobile.domain.model.crmRingSetting.CrmRingSetting;
import com.banger.mobile.facade.answerConfig.AnswerConfigService;
import com.banger.mobile.facade.crmRecordRemind.CrmRecordRemindService;
import com.banger.mobile.facade.crmRingSetting.CrmRingSettingService;
import com.banger.mobile.util.SpringContextUtil;
import com.banger.mobile.util.StringUtil;

/**
 * @author yuanme
 * @version GetUserPhoneConfigFileServlet.java,v 0.1 2012-8-3 上午10:50:19
 */
public class GetUserPhoneConfigFileServlet extends HttpServlet {
    private static final long   serialVersionUID = -295510271093612476L;
    private static final Logger logger           = Logger
                                                     .getLogger(GetUserPhoneConfigFileServlet.class);

    public GetUserPhoneConfigFileServlet() {
        super();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            String type = request.getParameter("type");
            String id = request.getParameter("id");

            if (!StringUtil.isNotEmpty(type) && !StringUtil.isNotEmpty(id)) {
                return;
            }

            String filePath = "";
            if (type.equals("answer")) {
                AnswerConfigService answerConfigService = (AnswerConfigService) SpringContextUtil
                    .getBean("answerConfigService");
                AnswerConfig answer = answerConfigService
                    .queryAnswerConfigById(Integer.valueOf(id));
                filePath = answer.getVoiceFilePath();
            } else if (type.equals("remind")) {
                CrmRecordRemindService crmRecordRemindService = (CrmRecordRemindService) SpringContextUtil
                    .getBean("crmRecordRemindService");
                CrmRecordRemind remind = crmRecordRemindService.getCrmRecordRemindById(Integer
                    .valueOf(id));
                filePath = remind.getFilePath();
            } else if (type.equals("ring")) {
                CrmRingSettingService crmRingSettingService = (CrmRingSettingService) SpringContextUtil
                    .getBean("crmRingSettingService");
                CrmRingSetting ring = crmRingSettingService.getCrmRingSettingById(Integer
                    .valueOf(id));
                filePath = ring.getFilePath();
            }

            if (!StringUtil.isNotEmpty(filePath)) {
                return;
            }
            File file = new File(filePath);
            if(file.exists())
            {
	            FileInputStream fis = new FileInputStream(file);//服务器文件路径
	            response.addHeader("Content-Disposition", "attachment;filename=www.wav");
	            response.addHeader("Content-Length", "" + file.length());
	
	            response.setContentType("audio/*"); //设置返回的文件类型 
	            OutputStream output = response.getOutputStream(); //得到向客户端输出二进制数据的对象 
	            BufferedInputStream bis = new BufferedInputStream(fis);//输入缓冲流      
	            BufferedOutputStream bos = new BufferedOutputStream(output);//输出缓冲流     
	
	            byte data[] = new byte[4096];//缓冲字节数    
	
	            int size = 0;
	            size = bis.read(data);
	            while (size != -1) {
	                bos.write(data, 0, size);
	                size = bis.read(data);
	            }
	            bis.close();
	            bos.flush();//清空输出缓冲流     
	            bos.close();
	            output.close();
            }
        } catch (Exception e) {
            logger.error("取得服务器答录配置录音文件 error...", e);
        }
    }
}
