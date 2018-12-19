package com.banger.mobile.domain.model.quartz;

import java.util.Date;


import org.apache.commons.lang.builder.ToStringBuilder;

import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.commons.lang.builder.HashCodeBuilder;

import com.banger.mobile.domain.model.base.BaseObject;

/**
 * 经执行或正在执行的任务(table=TASK_CURRENT)
 * 
 * @author zhangxiang
 * 
 */
public class TaskCurrent extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1785026366423034160L;
	/* @property: */
	private Integer id;
	/* @property: */
	private Date gmtCreate;
	/* @property: */
	private Date gmtModify;
	/* @property:任务实例名称 */
	private String taskInsName;
	/* @property:任务类全限定名 */
	private String className;
	/* @property:开始执行时间 */
	private Date runStart;

	
	/* Default constructor - creates a new instance with no values set. */
	public TaskCurrent() {
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

	/* @model:设置任务类全限定名 */
	public void setClassName(String obj) {
		this.className = obj;
	}

	/* @model:获取任务类全限定名 */
	public String getClassName() {
		return this.className;
	}

	/* @model:设置开始执行时间 */
	public void setRunStart(Date obj) {
		this.runStart = obj;
	}

	/* @model:获取开始执行时间 */
	public Date getRunStart() {
		return this.runStart;
	}

	/* {@inheritDoc} */
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof TaskCurrent)) {
			return false;
		}
		final TaskCurrent taskcurrent = (TaskCurrent) o;
		return this.hashCode() == taskcurrent.hashCode();
	}

	/* {@inheritDoc} */
	public String toString() {
		ToStringBuilder sb = new ToStringBuilder(this,
				ToStringStyle.DEFAULT_STYLE).append("id", this.id).append(
				"gmtCreate", this.gmtCreate)
				.append("gmtModify", this.gmtModify).append("taskInsName",
						this.taskInsName).append("className", this.className)
				.append("runStart", this.runStart);
		return sb.toString();
	}

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(1695744617, 46542679).appendSuper(super.hashCode()).append(
            this.taskInsName).append(this.id).append(this.runStart).append(this.gmtCreate).append(
            this.gmtModify).append(this.className).toHashCode();
    }

}
