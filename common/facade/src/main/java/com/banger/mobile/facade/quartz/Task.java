package com.banger.mobile.facade.quartz;

import com.banger.mobile.domain.model.quartz.JobData;



/**
 * 任务类
 * 
 * @author zhangxiang
 * @version $Id: Task.java,v 0.1 2009-7-24 下午01:31:02 zhangxiang Exp $
 */
public  interface Task {

    /**
     * 校验任务是否可以执行，在集群情况下（有些任务需要考虑集群情况）， 
     * 如果一个结点已经执行则其它的结点无需再次执行
     * 
     * @return 可以执行则返回true,反之false
     */
    boolean isRun();


    /**
     * 任务执行入口主方法
     * 
     * @param jobData
     * @throws Exception
     */
    void execute(JobData jobData) throws Exception;
    
    
    /**
     * 任务执行入口主方法
     * 
     * @param jobData
     * @throws Exception
     */
    void execute() throws Exception;

    /**
     * 手动执行方式，通过前台触发此任务的执行
     * 
     * @param jobData
     * @throws Exception
     */
    void executeManual(JobData jobData) throws Exception;

    /**
     * 任务接口,具体的任务都实现此接口
     * 
     * @param jobData
     * @throws Exception
     */

}
