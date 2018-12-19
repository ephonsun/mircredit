package com.banger.mobile.domain.model.base.inform;

import com.banger.mobile.domain.model.base.BaseObject;

import java.util.Date;

/**
 * Created by BH-TCL on 15-7-13.
 */
public class BaseInform extends BaseObject {

    private Integer informId; //主键
    private Integer isOpen; // 是否开启
    private String text;    //文本内容
    private Date createDate;    //创建时间
    private Date updateDate;    //更新时间
    private Integer createUser;    //创建用户
    private Integer updateUser;     //更新用户

    public Integer getInformId() {
        return informId;
    }

    public void setInformId(Integer informId) {
        this.informId = informId;
    }

    public Integer getIsOpen() {
        return isOpen;
    }

    public void setIsOpen(Integer isOpen) {
        this.isOpen = isOpen;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Integer getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Integer createUser) {
        this.createUser = createUser;
    }

    public Integer getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(Integer updateUser) {
        this.updateUser = updateUser;
    }
}
