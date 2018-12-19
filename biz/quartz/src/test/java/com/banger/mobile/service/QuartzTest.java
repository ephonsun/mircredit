package com.banger.mobile.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.banger.mobile.util.QuartzUtil;

public class QuartzTest {

    /** */
    /**
         * @param args
         */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        TestJob job = new TestJob();
        String job_name = "11";
        try {
            Map<Object, Object> map =new HashMap<Object, Object>();
            QuartzUtil quartzUtil=new QuartzUtil();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date startDate = sdf.parse("2012-09-07 18:00:00");
            Date endDate = sdf.parse("2012-09-09 00:00:00");
            System.out.println("【系统启动】");
            //QuartzManager.addJob(job_name,job,"0/5 * * * * ?");
            quartzUtil.addJob("jobName00", "jobGroupName00", "triggerName00", "triggerGroupName00",
                job, "reapte", startDate, endDate, "0/5 * * * * ?", map);
            Thread.sleep(10000);
            System.out.println("【修改时间】");
            quartzUtil.modifyJob("jobName00", "jobGroupName00", "triggerName00",
                "triggerGroupName00", "reapte", startDate, endDate, "0/10 * * * * ?");

            //            Thread.sleep(20000);
            //            System.out.println("【移除定时】");
            //            QuartzManager.removeJob(job_name);
            //            Thread.sleep(10000);
            //            
            //            System.out.println("\n【添加定时任务】");
            //            QuartzManager.addJob(job_name,job,"0/5 * * * * ?");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
