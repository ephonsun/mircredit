package com.banger.mobile.domain.model.quartz;

import java.util.Date;


import org.apache.commons.lang.builder.ToStringBuilder;

import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.commons.lang.builder.HashCodeBuilder;

import com.banger.mobile.domain.model.base.BaseObject;

/**
 * 任务执行情况记录(任务实例信息) table=TASK_INS
 * 
 * @author zhangxiang
 * 
 */
public class TaskIns extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5404110535315094950L;
	/* @property: */
	private Integer id;
	/* @property: */
	private Date gmtCreate;
	/* @property: */
	private Date gmtModify;
	/* @property:任务实例名称 */
	private String taskInsName;
	/* @property:类全限定名称 */
	private String className;
	/* @property:运行时间起 */
	private Date runStart;
	/* @property:运行时间止 */
	private Date runEnd;
	/* @property:运行耗时 */
	private String runUseTime;
	/* @property:运行方式（ 0 自动、1 手动） */
	private String runMode;
	/* @property:结果状态（0 成功、1 失败） */
	private String status;
	/* @property:描述（记录执行中的重要信息） */
	private String description;
	/* Default constructor - creates a new instance with no values set. */
	public TaskIns() {
	}

	/* @model:设置 */
	public void setId(Integer obj) {
		this.id = obj;
	}

	/* @model:获取 */
	public Integer getId() {
		return this.id;
	}

	public Date getGmtCreate() {
		return gmtCreate;
	}

	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	public Date getGmtModify() {
		return gmtModify;
	}

	public void setGmtModify(Date gmtModify) {
		this.gmtModify = gmtModify;
	}

	/* @model:设置任务实例名称 */
	public void setTaskInsName(String obj) {
		this.taskInsName = obj;
	}

	/* @model:获取任务实例名称 */
	public String getTaskInsName() {
		return this.taskInsName;
	}

	/* @model:设置类全限定名称 */
	public void setClassName(String obj) {
		this.className = obj;
	}

	/* @model:获取类全限定名称 */
	public String getClassName() {
		return this.className;
	}

	/* @model:设置运行时间起 */
	public void setRunStart(Date obj) {
		this.runStart = obj;
	}

	/* @model:获取运行时间起 */
	public Date getRunStart() {
		return this.runStart;
	}

	/* @model:设置运行时间止 */
	public void setRunEnd(Date obj) {
		this.runEnd = obj;
	}

	/* @model:获取运行时间止 */
	public Date getRunEnd() {
		return this.runEnd;
	}

	/* @model:设置运行耗时 */
	public void setRunUseTime(String obj) {
		this.runUseTime = obj;
	}

	/* @model:获取运行耗时 */
	public String getRunUseTime() {
		return this.runUseTime;
	}

	/* @model:设置运行方式（ 0 自动、1 手动） */
	public void setRunMode(String obj) {
		this.runMode = obj;
	}

	/* @model:获取运行方式（ 0 自动、1 手动） */
	public String getRunMode() {
		return this.runMode;
	}

	/* @model:设置结果状态（0 成功、1 失败） */
	public void setStatus(String obj) {
		this.status = obj;
	}

	/* @model:获取结果状态（0 成功、1 失败） */
	public String getStatus() {
		return this.status;
	}

	/* @model:设置描述（记录执行中的重要信息） */
	public void setDescription(String obj) {
		this.description = obj;
	}

	/* @model:获取描述（记录执行中的重要信息） */
	public String getDescription() {
		return this.description;
	}

	/* {@inheritDoc} */
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof TaskIns)) {
			return false;
		}
		final TaskIns taskins = (TaskIns) o;
		return this.hashCode() == taskins.hashCode();
	}

	/* {@inheritDoc} */
	public String toString() {
		ToStringBuilder sb = new ToStringBuilder(this,
				ToStringStyle.DEFAULT_STYLE).append("id", this.id).append(
				"gmtCreate", this.gmtCreate)
				.append("gmtModify", this.gmtModify).append("taskInsName",
						this.taskInsName).append("className", this.className)
				.append("runStart", this.runStart)
				.append("runEnd", this.runEnd).append("runUseTime",
						this.runUseTime).append("runMode", this.runMode)
				.append("status", this.status).append("description",
						this.description);
		return sb.toString();
	}

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(-901418413, 1636114881).appendSuper(super.hashCode()).append(
            this.taskInsName).append(this.id).append(this.runStart).append(this.gmtCreate).append(
            this.status).append(this.description).append(this.runEnd).append(this.runMode).append(
            this.gmtModify).append(this.runUseTime).append(this.className).toHashCode();
    }

}
