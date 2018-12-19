/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :session监听器
 * Author     :zhangxiang
 * Create Date:2012-8-9
 */
package com.banger.mobile.webapp.listener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.log4j.Logger;



import com.banger.mobile.domain.model.user.SysUserOnline;
import com.banger.mobile.facade.user.SysUserOnlineService;
import com.banger.mobile.constants.OnLineSessionContext;

/**
 * @author zhangxiang
 * @version $Id: UserOnline.java,v 0.1 2012-8-9 上午10:53:51 zhangxiang Exp $
 */
public class UserOnlineListener implements HttpSessionListener {
    private static final Logger logger = Logger.getLogger(UserOnlineListener.class);

    private   OnLineSessionContext myc=OnLineSessionContext.getInstance();


    @Override
    public void sessionCreated(HttpSessionEvent event) {
        myc.addSession(event.getSession());   //把会话加入全局sessionmap中
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent event) {
        try {
            HttpSession session = event.getSession();    
            SysUserOnlineService sysUserOnlineService=(SysUserOnlineService)com.banger.mobile.util.SpringContextUtil.getBean("sysUserOnlineService");
            Map<String, Object> parameters=new HashMap<String, Object>();
            parameters.put("userSessionId", session.getId());
            List<SysUserOnline> sysUserOnlineList=sysUserOnlineService.getSysUserOnlineList(parameters);
            if(sysUserOnlineList.size()>0){
                SysUserOnline sysUserOnline=sysUserOnlineList.get(0);
                sysUserOnline.setOnlineStatusId(3);
                sysUserOnlineService.updateSysUserOnline(sysUserOnline);
            }
        } catch (Exception e) {
            logger.info(" UserOnline sessionDestroyed: " + e.getMessage());
        }

    }
    
   
}
