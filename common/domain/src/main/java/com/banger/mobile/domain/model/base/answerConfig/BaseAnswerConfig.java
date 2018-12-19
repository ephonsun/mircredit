/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :答录配置基类
 * Author     :yujh
 * Create Date:Jun 4, 2012
 */
package com.banger.mobile.domain.model.base.answerConfig;

import java.io.Serializable;

import com.banger.mobile.domain.model.base.BaseObject;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author yujh
 * @version $Id: AnswerConfig.java,v 0.1 Jun 4, 2012 3:53:35 PM Administrator Exp $
 */
public class BaseAnswerConfig extends BaseObject implements Serializable{

    private static final long serialVersionUID = 405712939119196084L;
    private     Integer         answerConfigId;     //主键id
    private     Integer         userId;             //用户id
    private     String          voiceFilePath;      //留言文件
    private     Integer         ringCount;          //响铃次数
    private     Integer         isMute;             //是否对方留言静音
    private     Integer         configType;         //类型
    private     String          fileMd5;
    
    private String		filename;			//文件名
    
    public String getFileName() {
		return filename;
	}
	public void setFileName(String filename) {
		this.filename = filename;
	}
	
    public Integer getAnswerConfigId() {
        return answerConfigId;
    }
    public void setAnswerConfigId(Integer answerConfigId) {
        this.answerConfigId = answerConfigId;
    }
    public Integer getUserId() {
        return userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public String getVoiceFilePath() {
        return voiceFilePath;
    }
    public void setVoiceFilePath(String voiceFilePath) {
        this.voiceFilePath = voiceFilePath;
    }
    public Integer getRingCount() {
        return ringCount;
    }
    public void setRingCount(Integer ringCount) {
        this.ringCount = ringCount;
    }
    public Integer getIsMute() {
        return isMute;
    }
    public void setIsMute(Integer isMute) {
        this.isMute = isMute;
    }
    public Integer getConfigType() {
        return configType;
    }
    public void setConfigType(Integer configType) {
        this.configType = configType;
    }
    public String getFileMd5() {
        return fileMd5;
    }
    public void setFileMd5(String fileMd5) {
        this.fileMd5 = fileMd5;
    }
    /**
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object) {
        if (!(object instanceof BaseAnswerConfig)) {
            return false;
        }
        BaseAnswerConfig rhs = (BaseAnswerConfig) object;
        return new EqualsBuilder().appendSuper(super.equals(object)).append(this.ringCount,
            rhs.ringCount).append(this.configType, rhs.configType)
            .append(this.fileMd5, rhs.fileMd5).append(this.isMute, rhs.isMute).append(this.userId,
                rhs.userId).append(this.answerConfigId, rhs.answerConfigId).append(
                this.voiceFilePath, rhs.voiceFilePath).isEquals();
    }
    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(1976833689, -1582692757).appendSuper(super.hashCode()).append(
            this.ringCount).append(this.configType).append(this.fileMd5).append(this.isMute)
            .append(this.userId).append(this.answerConfigId).append(this.voiceFilePath)
            .toHashCode();
    }
    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this).append("configType", this.configType).append(
            "answerConfigId", this.answerConfigId).append("startRow", this.getStartRow()).append(
            "fileMd5", this.fileMd5).append("endRow", this.getEndRow()).append("ringCount",
            this.ringCount).append("voiceFilePath", this.voiceFilePath).append("isMute",
            this.isMute).append("userId", this.userId).toString();
    }
        
}
