package com.banger.mobile.domain.model.finance;

import java.util.Date;

import com.banger.mobile.domain.model.base.BaseObject;

public class FeFinanceUser extends BaseObject implements java.io.Serializable{

    private static final long serialVersionUID = -6853976481871563291L;
    

    private Integer   userId;
    private String    userName;
    private String    buessRank;
    private Integer   deptId;
    private String    deptName;
    private Date      readDate;
    private Date      collectDate;
    
    public Integer getUserId() {
        return userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getBuessRank() {
        return buessRank;
    }
    public void setBuessRank(String buessRank) {
        this.buessRank = buessRank;
    }
    public Integer getDeptId() {
        return deptId;
    }
    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }
    public String getDeptName() {
        return deptName;
    }
    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }
    public Date getReadDate() {
        return readDate;
    }
    public void setReadDate(Date readDate) {
        this.readDate = readDate;
    }
    public Date getCollectDate() {
        return collectDate;
    }
    public void setCollectDate(Date collectDate) {
        this.collectDate = collectDate;
    }
    
}
