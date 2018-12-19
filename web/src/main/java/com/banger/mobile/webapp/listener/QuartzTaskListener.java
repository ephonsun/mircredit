/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :quartz任务自动启动监听器
 * Author     :zhangxiang
 * Create Date:2012-8-17
 */
package com.banger.mobile.webapp.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.apache.log4j.Logger;
import com.banger.mobile.facade.adsync.AdSyncConfigService;
import com.banger.mobile.facade.adsync.AdUserSyncService;
import com.banger.mobile.facade.impl.quartz.AbstractTask;
import com.banger.mobile.util.MixedUtil;
import com.banger.mobile.util.SpringContextUtil;
import com.banger.mobile.constants.Constants;

/**
 * @author zhangxiang
 * @version $Id: QuartzTaskListener.java,v 0.1 2012-8-17 下午2:03:18 zhangxiang Exp $
 */
public class QuartzTaskListener implements ServletContextListener {

    /** Logger. */
    private static final Logger logger = Logger.getLogger(QuartzTaskListener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        logger.info("QuartzTaskListener startup: ");
        try {
          /*  if (Constants.isStartMmsSms) {
                logger.info("quartz listener startup: ");

                AbstractTask task = (AbstractTask) SpringContextUtil.getBean("autoTask");
                
                if (MixedUtil.checkingLocalAllowedIP(task.getTaskRunAllowedIPs()))   //如果是被允许执行的ip地址
                {
	                //短信发送通道对象
	                SmsChannelBizService smsChannelBizService = ((SmsChannelBizServiceFactory) SpringContextUtil.getBean("smsChannelBizServiceFactoryImpl")).getSmsChannelBizService();
	                //初始化通道业务
	                smsChannelBizService.InitChannelBizService();	
	                //彩信发送通道对象
	                MmsChannelBizService mmsChannelBizService = (MmsChannelBizService) SpringContextUtil.getBean("mmsChannelBizService");
	                //初始化通道业务
	                mmsChannelBizService.InitChannelBizService();
                }
            }
            AdSyncConfigService syncSetting = (AdSyncConfigService)SpringContextUtil.getBean("adSyncConfigService");
            ((AdUserSyncService)SpringContextUtil.getBean("adUserSyncService")).AddSyncJob(syncSetting.getAdSyncConfig());
            InitAutoImportService initAutoImportService = (InitAutoImportService)SpringContextUtil.getBean("initAutoImportService");
            initAutoImportService.startImportCustomerThread();  */
        } catch (Exception e) {
            logger.error("QuartzTaskListener error:" + e.getMessage());
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
    
}
