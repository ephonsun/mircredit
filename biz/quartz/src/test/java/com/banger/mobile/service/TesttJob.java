package com.banger.mobile.service;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.banger.mobile.domain.model.quartz.JobData;
import com.banger.mobile.facade.impl.quartz.AbstractTask;
import com.banger.mobile.facade.quartz.Task;

public class TesttJob extends AbstractTask {

    public void doTask() {
        System.out.println("★★★★★★★★★★★");

    }

}
