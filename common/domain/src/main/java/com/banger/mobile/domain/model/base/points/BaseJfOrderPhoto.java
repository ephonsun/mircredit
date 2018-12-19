package com.banger.mobile.domain.model.base.points;

import com.banger.mobile.domain.model.base.BaseObject;

import java.util.Date;

/**
 * @author zhangfp
 * @version $Id: BaseJfOrderPhoto v 0.1 ${} 下午4:07 zhangfp Exp $
 */
public class BaseJfOrderPhoto extends BaseObject implements java.io.Serializable {
    private static final long serialVersionUID = -1719706896274863784L;

    private Integer orderPhotoId;
    private String orderNo;
    private Date takeDate;
    private Integer fileId;
    private Integer userId;

    public Integer getOrderPhotoId() {
        return orderPhotoId;
    }

    public void setOrderPhotoId(Integer orderPhotoId) {
        this.orderPhotoId = orderPhotoId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Date getTakeDate() {
        return takeDate;
    }

    public void setTakeDate(Date takeDate) {
        this.takeDate = takeDate;
    }

    public Integer getFileId() {
        return fileId;
    }

    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
