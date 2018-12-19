/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :任务完成情况统计报表
 * Author     :liyb
 * Create Date:2012-9-5
 */
package com.banger.mobile.domain.model.tskContact;

import java.io.Serializable;

/**
 * @author liyb
 * @version $Id: TskTaskReportBean.java,v 0.1 2012-9-5 下午05:38:58 liyb Exp $
 */
public class TskContactReportBean implements Serializable {

    private static final long serialVersionUID = -1815669384992295023L;
    private Integer executeUserId;//执行者ID
    private String executeUserName;//执行者名称
    private Integer taskCount;//任务总量
    private String  finishRate;//任务完成情况
    /**
     * 完成情况分布
     */
    private Integer twentyFiveRate;//0%-25%
    private Integer fiftyRate;//25%-50%
    private Integer seventyFiveRate;//50%-75%
    private Integer hundredRate;//75%-100%
    public Integer getExecuteUserId() {
        return executeUserId;
    }
    public void setExecuteUserId(Integer executeUserId) {
        this.executeUserId = executeUserId;
    }
    public String getExecuteUserName() {
        return executeUserName;
    }
    public void setExecuteUserName(String executeUserName) {
        this.executeUserName = executeUserName;
    }
    public Integer getTaskCount() {
        return taskCount;
    }
    public void setTaskCount(Integer taskCount) {
        this.taskCount = taskCount;
    }
    public String getFinishRate() {
        return finishRate;
    }
    public void setFinishRate(String finishRate) {
        this.finishRate = finishRate;
    }
    public Integer getTwentyFiveRate() {
        return twentyFiveRate;
    }
    public void setTwentyFiveRate(Integer twentyFiveRate) {
        this.twentyFiveRate = twentyFiveRate;
    }
    public Integer getFiftyRate() {
        return fiftyRate;
    }
    public void setFiftyRate(Integer fiftyRate) {
        this.fiftyRate = fiftyRate;
    }
    public Integer getSeventyFiveRate() {
        return seventyFiveRate;
    }
    public void setSeventyFiveRate(Integer seventyFiveRate) {
        this.seventyFiveRate = seventyFiveRate;
    }
    public Integer getHundredRate() {
        return hundredRate;
    }
    public void setHundredRate(Integer hundredRate) {
        this.hundredRate = hundredRate;
    }
}
