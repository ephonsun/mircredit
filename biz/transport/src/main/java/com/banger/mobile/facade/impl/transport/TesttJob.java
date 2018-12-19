package com.banger.mobile.facade.impl.transport;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.banger.mobile.domain.model.quartz.JobData;
import com.banger.mobile.facade.impl.quartz.AbstractTask;
import com.banger.mobile.facade.quartz.Task;

public class TesttJob extends AbstractTask {

    @Override
    public void doTask() throws Exception {
        /*****   模拟发送短信方法        ****/
        System.out.println("ip:"+getTaskRunAllowedIPs());
        System.out.println("★★★★★★★★★★★");
    }



}
