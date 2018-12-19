package com.banger.mobile.domain.model.base.system;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.banger.mobile.domain.model.base.BaseObject;
/**
 * 
 * @author Administrator
 * @version $Id: BaseTaskGrade.java,v 0.1 Dec 27, 2012 10:24:45 AM Administrator Exp $
 */
public class BaseTaskGrade extends BaseObject implements Serializable{
    
    private static final long serialVersionUID = -1391915330985049398L;
    
    private int               marketingGradeId;                        //主键ID
    private int               isDel;                                 //是否删除
    private String            marketingGradeName;                      //任务等级名称
    private int               sortNo;                                //排序号    
    private int               isActived;                             //1 启用 、 0停用
    private String            remark;                                //备注 
    private Date              createDate;                            //创建时间
    private Date              updateDate;                            //更新时间
    private int               createUser;                            //创建用户
    private int               updateUser;                            //更新用户

    public BaseTaskGrade(){
        super();
    }
    public BaseTaskGrade(int marketingGradeId, int isDel, String marketingGradeName, int sortNo,
                               int isActived, String remark,Date createDate, Date updateDate, int createUser,
                               int updateUser) {
        super();
        this.marketingGradeId = marketingGradeId;
        this.isDel = isDel;
        this.marketingGradeName = marketingGradeName;
        this.sortNo = sortNo;
        this.isActived = isActived;
        this.remark = remark;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.createUser = createUser;
        this.updateUser = updateUser;
    }

    public int getMarketingGradeId() {
        return marketingGradeId;
    }
    public void setMarketingGradeId(int marketingGradeId) {
        this.marketingGradeId = marketingGradeId;
    }
    public int getIsDel() {
        return isDel;
    }
    public void setIsDel(int isDel) {
        this.isDel = isDel;
    }
    public String getMarketingGradeName() {
        return marketingGradeName;
    }
    public void setMarketingGradeName(String marketingGradeName) {
        this.marketingGradeName = marketingGradeName;
    }
    public int getSortNo() {
        return sortNo;
    }
    public void setSortNo(int sortNo) {
        this.sortNo = sortNo;
    }
    public int getIsActived() {
        return isActived;
    }
    public void setIsActived(int isActived) {
        this.isActived = isActived;
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
    public int getCreateUser() {
        return createUser;
    }
    public void setCreateUser(int createUser) {
        this.createUser = createUser;
    }
    public int getUpdateUser() {
        return updateUser;
    }
    public void setUpdateUser(int updateUser) {
        this.updateUser = updateUser;
    }
    /**
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object) {
        if (!(object instanceof BaseTaskGrade)) {
            return false;
        }
        BaseTaskGrade rhs = (BaseTaskGrade) object;
        return new EqualsBuilder().appendSuper(super.equals(object)).append(this.marketingGradeName,
            rhs.marketingGradeName).append(this.sortNo, rhs.sortNo).append(this.isDel, rhs.isDel)
            .append(this.isActived, rhs.isActived).append(this.createDate, rhs.createDate).append(
                this.createUser, rhs.createUser).append(this.updateDate, rhs.updateDate).append(
                this.marketingGradeId, rhs.marketingGradeId).append(this.updateUser, rhs.updateUser).append(this.remark, rhs.remark)
            .isEquals();
    }
    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(2113477699, 452485477).appendSuper(super.hashCode()).append(
            this.marketingGradeName).append(this.sortNo).append(this.isDel).append(this.isActived)
            .append(this.createDate).append(this.createUser).append(this.updateDate).append(
                this.marketingGradeId).append(this.updateUser).append(this.remark).toHashCode();
    }
    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this).append("customerTypeName", this.marketingGradeName).append(
            "sortNo", this.sortNo).append("startRow", this.getStartRow()).append("updateDate",
            this.updateDate).append("endRow", this.getEndRow()).append("isActived", this.isActived)
            .append("updateUser", this.updateUser).append("createUser", this.createUser).append(
                "isDel", this.isDel).append("createDate", this.createDate).append("customerTypeId",
                this.marketingGradeId).append("remark",this.remark).toString();
    }
}
