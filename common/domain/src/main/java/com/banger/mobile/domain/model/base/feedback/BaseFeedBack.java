package com.banger.mobile.domain.model.base.feedback;

import com.banger.mobile.domain.model.base.BaseObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by BH-TCL on 15-3-9.
 */
public class BaseFeedBack extends BaseObject{
    private Integer feedBackId;
    private String titleName;//标题名
    private String modular;//模块
    private String description;//描述
    private String fileUrl;//日志文件路径
    private Integer status;//处理状态 0 未处理 1已处理
    private Date createDate;
    private Integer createUser;

    public Integer getFeedBackId() {
        return feedBackId;
    }

    public void setFeedBackId(Integer feedBackId) {
        this.feedBackId = feedBackId;
    }

    public String getTitleName() {
        return titleName;
    }

    public void setTitleName(String titleName) {
        this.titleName = titleName;
    }

    public String getModular() {
        return modular;
    }

    public void setModular(String modular) {
        this.modular = modular;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Integer createUser) {
        this.createUser = createUser;
    }
}
