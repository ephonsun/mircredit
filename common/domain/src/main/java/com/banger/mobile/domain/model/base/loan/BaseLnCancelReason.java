package com.banger.mobile.domain.model.base.loan;

import com.banger.mobile.domain.model.base.BaseObject;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Zhangfp
 * Date: 13-2-5
 * Time: 上午11:25
 * To change this template use File | Settings | File Templates.
 *
 * 贷款撤销原因
 */
public class BaseLnCancelReason extends BaseObject implements java.io.Serializable  {

    private Integer cancelReasonId;   //主键id
    private String cancelReasonName;  //担保人姓名
    private Integer isDel;            //是否删除
    private Integer isActived;        //是否启用
    private Integer sortNo;           //排序号
    private String remark;            //备注
    private Date createDate;          //创建时间
    private Date updateDate;          //更新时间
    private Integer createUser;       //创建用户
    private Integer updateUser;       //更新用户

    public BaseLnCancelReason(){
    }

    public BaseLnCancelReason(Integer cancelReasonId){
        this.cancelReasonId = cancelReasonId;
    }

    public Integer getCancelReasonId() {
        return cancelReasonId;
    }

    public void setCancelReasonId(Integer cancelReasonId) {
        this.cancelReasonId = cancelReasonId;
    }

    public String getCancelReasonName() {
        return cancelReasonName;
    }

    public void setCancelReasonName(String cancelReasonName) {
        this.cancelReasonName = cancelReasonName;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    public Integer getIsActived() {
        return isActived;
    }

    public void setIsActived(Integer isActived) {
        this.isActived = isActived;
    }

    public Integer getSortNo() {
        return sortNo;
    }

    public void setSortNo(Integer sortNo) {
        this.sortNo = sortNo;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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
