package com.banger.mobile.domain.model.base.loan;

import com.banger.mobile.domain.model.base.BaseObject;

import java.io.Serializable;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Zhangfp
 * Date: 13-2-5
 * Time: 上午11:51
 * To change this template use File | Settings | File Templates.
 *
 * 贷款子类型
 */
public class BaseLnLoanSubType extends BaseObject implements Serializable {
    private Integer loanSubTypeId;    //贷款了类型ID(主键ID)
    private Integer loanTypeId;       //贷款类型ID
    private String loanSubTypeName;   //贷款子类型名称
    private Integer isDel;            //是否删除
    private Integer isActived;        //是否启用
    private Integer sortNo;           //排序号
    private String remark;            //备注
    private Date createDate;          //创建时间
    private Date updateDate;          //更新时间
    private Integer createUser;       //创建用户
    private Integer updateUser;       //更新用户

    public BaseLnLoanSubType(){
    }

    public BaseLnLoanSubType(Integer loanSubTypeId){
        this.loanSubTypeId = loanSubTypeId;
    }

    public Integer getLoanSubTypeId() {
        return loanSubTypeId;
    }

    public void setLoanSubTypeId(Integer loanSubTypeId) {
        this.loanSubTypeId = loanSubTypeId;
    }

    public Integer getLoanTypeId() {
        return loanTypeId;
    }

    public void setLoanTypeId(Integer loanTypeId) {
        this.loanTypeId = loanTypeId;
    }

    public String getLoanSubTypeName() {
        return loanSubTypeName;
    }

    public void setLoanSubTypeName(String loanSubTypeName) {
        this.loanSubTypeName = loanSubTypeName;
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
