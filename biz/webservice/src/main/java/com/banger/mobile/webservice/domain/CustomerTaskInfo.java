package com.banger.mobile.webservice.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * Created with IntelliJ IDEA. User: Administrator Date: 13-3-20 Time: 下午2:55 To
 * change this template use File | Settings | File Templates.
 */
public class CustomerTaskInfo implements Serializable {
    private Integer taskId;
    private String  taskName;
    private String  taskTypeName;
    private Integer status;
    private Date    startDate;
    private Date    endDate;
    private String  createUser;
    private String  remark;

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getTaskTypeName() {
        return taskTypeName;
    }

    public void setTaskTypeName(String taskTypeName) {
        this.taskTypeName = taskTypeName;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }
}
