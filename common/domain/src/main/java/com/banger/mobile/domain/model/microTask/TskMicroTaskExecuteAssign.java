/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :任务执行者-Domian-扩展2
 * Author     :QianJie
 * Create Date:Mar 2, 2013
 */
package com.banger.mobile.domain.model.microTask;

import com.banger.mobile.domain.model.base.microTask.BaseTskMicroTaskExecute;

import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * @author QianJie
 * @version $Id: TskMicroTask.java,v 0.1 Mar 2, 2013 10:44:01 AM QianJie Exp $
 */
public class TskMicroTaskExecuteAssign extends BaseTskMicroTaskExecute {

    private static final long serialVersionUID = -6077175157159509616L;

    private Integer           id;                                       //主键（deptId、userId）
    private Integer           pid;                                      //父Id（deptId、userId）
    private Integer           epid;                                     //真实父Id
    private Integer           tMode;                                    //数据类型（1.冻结，2.树干，3.叶子）
    private String            assignObjName;                            //分配对象
    private Integer           assignTotalTarget;                        //任务目标
    private Integer           assignTarget;                             //已分配任务目标
    private Integer           unAssignTarget;                           //未分配的任务
    private String            assignPc;                                 //已分配任务比率
    private Integer           comTskNum;                                //已完成贷款笔数
    private String            comPc;                                    //完成进度
    

    public TskMicroTaskExecuteAssign() {
        super();
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getPid() {
        return pid;
    }
    public void setPid(Integer pid) {
        this.pid = pid;
    }
    public Integer getEpid() {
        return epid;
    }
    public void setEpid(Integer epid) {
        this.epid = epid;
    }
    public Integer getTMode() {
        return tMode;
    }
    public void setTMode(Integer mode) {
        tMode = mode;
    }
    public String getAssignObjName() {
        return assignObjName;
    }
    public void setAssignObjName(String assignObjName) {
        this.assignObjName = assignObjName;
    }
    public Integer getAssignTotalTarget() {
        return assignTotalTarget;
    }
    public void setAssignTotalTarget(Integer assignTotalTarget) {
        this.assignTotalTarget = assignTotalTarget;
    }
    public Integer getAssignTarget() {
        return assignTarget;
    }
    public void setAssignTarget(Integer assignTarget) {
        this.assignTarget = assignTarget;
    }
    public Integer getUnAssignTarget() {
        return unAssignTarget;
    }
    public void setUnAssignTarget(Integer unAssignTarget) {
        this.unAssignTarget = unAssignTarget;
    }
    public String getAssignPc() {
        return assignPc;
    }
    public void setAssignPc(String assignPc) {
        this.assignPc = assignPc;
    }
    public Integer getComTskNum() {
        return comTskNum;
    }
    public void setComTskNum(Integer comTskNum) {
        this.comTskNum = comTskNum;
    }
    public String getComPc() {
        return comPc;
    }
    public void setComPc(String comPc) {
        this.comPc = comPc;
    }
    
    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(340367499, -1998841965).appendSuper(super.hashCode()).append(
            this.tMode).append(this.comTskNum).append(this.assignTotalTarget).append(this.assignPc)
            .append(this.assignTarget).append(this.epid).append(this.assignObjName)
            .append(this.pid).append(this.comPc).append(this.id).append(this.unAssignTarget)
            .toHashCode();
    }   
}
