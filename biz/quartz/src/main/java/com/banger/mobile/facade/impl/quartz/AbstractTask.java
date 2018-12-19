/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :任务接口实现类
 * Author     :zhangxiang
 * Create Date:2012-8-17
 */
package com.banger.mobile.facade.impl.quartz;

import java.util.Date;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.BeanUtils;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.banger.mobile.dao.quartz.TaskCurrentDao;
import com.banger.mobile.dao.quartz.TaskInsDao;
import com.banger.mobile.domain.model.quartz.JobData;
import com.banger.mobile.domain.model.quartz.TaskCurrent;
import com.banger.mobile.domain.model.quartz.TaskIns;
import com.banger.mobile.facade.impl.quartz.constant.TaskConstants;
import com.banger.mobile.facade.quartz.Task;
import com.banger.mobile.util.MixedUtil;
import com.banger.mobile.util.SpringContextUtil;


/**
 * @author zhangxiang
 * @version $Id: QuartzTaskListener.java,v 0.1 2012-8-17 下午2:03:18 zhangxiang Exp $
 */
public class AbstractTask implements Task, Job {

    public Logger          logger = Logger.getLogger(this.getClass());

    // 任务DAO
    // private TaskDao taskDao;

    private TaskCurrentDao taskCurrentDao;

    private TaskInsDao     taskInsDao;

    // 是否需要集群支持（默认为N，Y为需要）
    private String         isCluster;

    // 任务实例名称（任务保存的时候用）
    private String         rwInsName;

    // 任务是否需要执行标志
    private boolean        isFlag;

    // 任务可以运行的服务器IP地址信息
    private String         taskRunAllowedIPs;

    public String getTaskRunAllowedIPs() {
        return taskRunAllowedIPs;
    }

    public void setTaskRunAllowedIPs(String taskRunAllowedIPs) {
        this.taskRunAllowedIPs = taskRunAllowedIPs;
    }

    public TaskCurrentDao getTaskCurrentDao() {
        return taskCurrentDao;
    }

    public void setTaskCurrentDao(TaskCurrentDao taskCurrentDao) {
        this.taskCurrentDao = taskCurrentDao;
    }

    public TaskInsDao getTaskInsDao() {
        return taskInsDao;
    }

    public void setTaskInsDao(TaskInsDao taskInsDao) {
        this.taskInsDao = taskInsDao;
    }

    public String getIsCluster() {
        return isCluster;
    }

    public void setIsCluster(String isCluster) {
        this.isCluster = isCluster;
    }

    public boolean isFlag() {
        return isFlag;
    }

    public void setFlag(boolean theFlag) {
        this.isFlag = theFlag;
    }

    /**
     * 任务实例名称为任务类的全限定名称_当前日期的时间组合而成
     * 
     * @return 返回当前任务实例名称
     */
    public String getRwInsName() {
        // 日期的格式只返回年月日时，分可能存在一些误差（两个任务同时执行时）
        this.rwInsName = this.getClass().getName() + "_" + MixedUtil.getCurrentDateStr();
        return rwInsName;
    }

    public void setRwInsName(String rwInsName) {
        this.rwInsName = rwInsName;
    }

    /**
     * 校验任务是否可以执行，在集群情况下（有些任务需要考虑集群情况）， 如果一个结点已经执行则其它的结点无需再次执行;非集群情况下直接返回true
     * 
     * @return 可以执行则返回true,反之false
     */
    public boolean isRun() {
        // 校验任务是否可以执行
        boolean theFlag = false;
        // 当为null或n的时候直接返回true,反之检查集群中其它的结点是否已经在执行此任务
        if (isCluster == null || "N".equalsIgnoreCase(isCluster)) {
            theFlag = true;
        } else {
            try {
                // 用任务实例名校验是否已经存在相同的任务在执行或已经执行结束，没有则设置执行标志为true
                if (!isAlreadyRun(getRwInsName())) {
                    // 保存任务
                    saveCurrentTask();
                    theFlag = true;
                }
            } catch (Exception e) {
                logger.warn(TaskConstants.CHECKING_SCHEDULING_IS_RUN_ERROR, e);
                theFlag = false;
            }
        }
        return theFlag;
    }

    /**
     * 保存当前任务执行信息
     * 
     * @throws Exception
     */
    private void saveCurrentTask() throws Exception {
        TaskCurrent taskCurrentDO = new TaskCurrent();
        taskCurrentDO.setTaskInsName(this.getRwInsName());
        taskCurrentDO.setClassName(this.getClass().getName());
        taskCurrentDO.setRunStart(new Date());
        // 保存现在需要执行的任务信息
        saveCurrentTask(taskCurrentDO);
    }

    /**
     * 集群下校验此任务是否已经执行（执行结束或正在执行）
     * 
     * @param taskInsName
     *            任务定义ID,任务的全限定名与时间的组合而成（时间配置为yyyy-mm-dd-hh年月日时）
     * @return 已经执行则在返回true,反之false
     * @throws Exception
     *             处理过程中出现则抛出 Exception
     */
    private boolean isAlreadyRun(String taskInsName) throws Exception {
        return taskCurrentDao.checkCurrentTaskByTaskInsName(taskInsName);
    }

    /**
     * 保存当前的任务(针对集群情况)
     * 
     * @param qrtzTaskCurrentDO
     *            当前需要执行的任务
     * @throws Exception
     *             处理过程中出现则抛出 Exception
     */
    private void saveCurrentTask(TaskCurrent taskCurrent) throws Exception {
        taskCurrentDao.insertCurrentTask(taskCurrent);
    }

    /**
     * 自动任务执行
     * 
     * @param jetCxt
     *            任务的上下文信息
     * @throws JobExecutionException
     *             处理过程中出现则抛出 JobExecutionException
     */
    public void execute(JobData jobData) throws JobExecutionException {
        // 校验服务器是否有运行此任务的权限，如果没有则直接退出
        if (!MixedUtil.checkingLocalAllowedIP(taskRunAllowedIPs)) {
            // 防止finally的任务实例保存信息
            this.isFlag = false;
            return;
        }

        // 校验是否需要执行
        this.isFlag = isRun();
        // 不需要执行则直接返回
        if (!this.isFlag) {
            logger.info("当前不需要执行...");
            return;
        }

        TaskIns taskInsDO = new TaskIns();
        // 设置任务定义id，取类全限定名
        taskInsDO.setClassName(this.getClass().getName());
        // 设置任务实例名称
        taskInsDO.setTaskInsName(this.getRwInsName());
        // 自动定时执行
        taskInsDO.setRunMode(TaskConstants.RW_YXFS_ZD);

        // 运行时间起
        long begin = System.currentTimeMillis();
        Date beginTime = new Date();
        taskInsDO.setRunStart(beginTime);

        try {

            jobData.getData().put(TaskConstants.YXFS, TaskConstants.RW_YXFS_ZD);
            //            this.doTask(jobData);
            // 运行成功
            taskInsDO.setStatus(TaskConstants.RW_STATE_SUCCESS);

        } catch (Exception e) {
            // 运行失败
            taskInsDO.setStatus(TaskConstants.RW_STATE_FAIL);
            logger.error(TaskConstants.AUTO_TASK_RUN_FAILURE, e);
            throw new JobExecutionException(e);
        } finally {
            try {
                // 只在在任务需要执行的时候才记录执行结果
                if (this.isFlag) {
                    // 运行时间
                    long end = System.currentTimeMillis();
                    Date endTime = new Date(end);
                    taskInsDO.setRunEnd(endTime);
                    taskInsDO.setRunUseTime(MixedUtil.countTime(begin, end));// 计算耗时
                    taskInsDO.setDescription(""); //设置描述信息

                    // 保存子任务执行信息
                    taskInsDao.insertTaskInsInfo(taskInsDO);
                }
            } catch (Exception e) {
                logger.error(TaskConstants.RECORD_SCHEDULIG_EXCUTE_RESULT_FAILURE + taskInsDO, e);
            }

        }
    }

    /**
     * 前台页面触发任务的执行
     * 
     * 只针对需要集群的有效
     */
    public void executeManual(JobData jobData) throws Exception {

        TaskIns taskIns = new TaskIns();
        // 设置任务定义id，取类全限定名
        taskIns.setClassName(this.getClass().getName());
        // 设置任务实例名称
        taskIns.setTaskInsName(getRwInsName());
        // 手动任务执行
        taskIns.setRunMode(TaskConstants.RW_YXFS_SD);

        // 运行时间起
        long begin = System.currentTimeMillis();
        Date beginTime = new Date();
        taskIns.setRunStart(beginTime);

        try {
            jobData.getData().put(TaskConstants.YXFS, TaskConstants.RW_YXFS_SD);

            //            this.doTask(jobData);
            // 运行成功
            taskIns.setStatus(TaskConstants.RW_STATE_SUCCESS);

        } catch (Exception e) {
            // 运行失败
            taskIns.setStatus(TaskConstants.RW_STATE_FAIL);
            logger.error(TaskConstants.MANUAL_TASK_RUN_FAILURE, e);
            throw e;
        } finally {
            try {
                // 运行时间
                long end = System.currentTimeMillis();
                Date endTime = new Date();
                taskIns.setRunEnd(endTime);
                taskIns.setRunUseTime(MixedUtil.countTime(begin, end));// 计算耗时

                // 保存子任务执行信息
                taskInsDao.insertTaskInsInfo(taskIns);
            } catch (Exception e) {
                logger.error(TaskConstants.RECORD_SCHEDULIG_EXCUTE_RESULT_FAILURE + taskIns, e);
            }
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @seecom.alibaba.japan.biz.core.scheduling.Task#doTask(org.quartz.
     * JobExecutionContext)
     */
    //    public abstract void doTask(JobData jobData) throws Exception;

    /**
     * xml配置quartz，需重写的方法
     * @throws Exception
     */
    public void doTask() throws Exception {
        System.out.println("★★★★★★★★★★");
    }
    
    
    /**
     * quartz自动添加任务，需重写的方法
     * @throws Exception
     */
    public void doTask(JobExecutionContext arg0) throws Exception {
        System.out.println("★★★★★★★★★★");
    }

    /**
     * 自动任务执行
     * 
     * @param jetCxt
     *            任务的上下文信息
     * @throws JobExecutionException
     *             处理过程中出现则抛出 JobExecutionException
     */
    public void execute() throws JobExecutionException {
        // 校验服务器是否有运行此任务的权限，如果没有则直接退出
        if (!MixedUtil.checkingLocalAllowedIP(taskRunAllowedIPs)) {
            // 防止finally的任务实例保存信息
            this.isFlag = false;
            return;
        }

        // 校验是否需要执行
        this.isFlag = isRun();
        // 不需要执行则直接返回
        if (!this.isFlag) {
            logger.info("当前不需要执行...");
            return;
        }

        TaskIns taskInsDO = new TaskIns();
        // 设置任务定义id，取类全限定名
        taskInsDO.setClassName(this.getClass().getName());
        // 设置任务实例名称
        taskInsDO.setTaskInsName(this.getRwInsName());
        // 自动定时执行
        taskInsDO.setRunMode(TaskConstants.RW_YXFS_ZD);

        // 运行时间起
        long begin = System.currentTimeMillis();
        Date beginTime = new Date();
        taskInsDO.setRunStart(beginTime);

        try {
            this.doTask();
            // 运行成功
            taskInsDO.setStatus(TaskConstants.RW_STATE_SUCCESS);

        } catch (Exception e) {
            // 运行失败
            taskInsDO.setStatus(TaskConstants.RW_STATE_FAIL);
            logger.error(TaskConstants.AUTO_TASK_RUN_FAILURE, e);
            throw new JobExecutionException(e);
        } finally {
            try {
                // 只在在任务需要执行的时候才记录执行结果
                if (this.isFlag) {
                    // 运行时间
                    long end = System.currentTimeMillis();
                    Date endTime = new Date(end);
                    taskInsDO.setRunEnd(endTime);
                    taskInsDO.setRunUseTime(MixedUtil.countTime(begin, end));// 计算耗时
                    taskInsDO.setDescription(""); //设置描述信息

                    // 保存子任务执行信息
                    taskInsDao.insertTaskInsInfo(taskInsDO);
                }
            } catch (Exception e) {
                logger.error(TaskConstants.RECORD_SCHEDULIG_EXCUTE_RESULT_FAILURE + taskInsDO, e);
            }

        }

    }
    
    
    
    /**
     * 自动任务执行
     * 
     * @param jetCxt
     *            任务的上下文信息
     * @throws JobExecutionException
     *             处理过程中出现则抛出 JobExecutionException
     */
    public void executeQuartz(JobExecutionContext arg0) throws JobExecutionException {
        // 校验服务器是否有运行此任务的权限，如果没有则直接退出
        if (!MixedUtil.checkingLocalAllowedIP(taskRunAllowedIPs)) {
            // 防止finally的任务实例保存信息
            this.isFlag = false;
            return;
        }

        // 校验是否需要执行
        this.isFlag = isRun();
        // 不需要执行则直接返回
        if (!this.isFlag) {
            logger.info("当前不需要执行...");
            return;
        }

        TaskIns taskInsDO = new TaskIns();
        // 设置任务定义id，取类全限定名
        taskInsDO.setClassName(this.getClass().getName());
        // 设置任务实例名称
        taskInsDO.setTaskInsName(this.getRwInsName());
        // 自动定时执行
        taskInsDO.setRunMode(TaskConstants.RW_YXFS_ZD);

        // 运行时间起
        long begin = System.currentTimeMillis();
        Date beginTime = new Date();
        taskInsDO.setRunStart(beginTime);

        try {
            this.doTask(arg0);
            // 运行成功
            taskInsDO.setStatus(TaskConstants.RW_STATE_SUCCESS);

        } catch (Exception e) {
            // 运行失败
            taskInsDO.setStatus(TaskConstants.RW_STATE_FAIL);
            logger.error(TaskConstants.AUTO_TASK_RUN_FAILURE, e);
            throw new JobExecutionException(e);
        } finally {
            try {
                // 只在在任务需要执行的时候才记录执行结果
                if (this.isFlag) {
                    // 运行时间
                    long end = System.currentTimeMillis();
                    Date endTime = new Date(end);
                    taskInsDO.setRunEnd(endTime);
                    taskInsDO.setRunUseTime(MixedUtil.countTime(begin, end));// 计算耗时
                    taskInsDO.setDescription(""); //设置描述信息

                    // 保存子任务执行信息
                    taskInsDao.insertTaskInsInfo(taskInsDO);
                }
            } catch (Exception e) {
                logger.error(TaskConstants.RECORD_SCHEDULIG_EXCUTE_RESULT_FAILURE + taskInsDO, e);
            }

        }

    }
    
    /**
     * 初始化自动添加任务值
     */
    public void initProp()
    {
        AbstractTask task = (AbstractTask) SpringContextUtil
                .getBean("autoTask");
        this.setTaskCurrentDao(task.getTaskCurrentDao());
        this.setTaskInsDao(task.getTaskInsDao());
        this.setIsCluster(task.getIsCluster());
        this.setTaskRunAllowedIPs(task.getTaskRunAllowedIPs());
    }
    
   /**
    * quartz自动添加任务触发方法
    * @param arg0
    * @throws JobExecutionException
    * @see org.quartz.Job#execute(org.quartz.JobExecutionContext)
    */
    public void execute(JobExecutionContext arg0) throws JobExecutionException {
        initProp();
        executeQuartz(arg0);
    }

}
