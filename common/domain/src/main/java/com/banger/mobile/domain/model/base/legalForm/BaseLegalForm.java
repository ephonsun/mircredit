package com.banger.mobile.domain.model.base.legalForm;

import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import com.banger.mobile.domain.model.base.BaseObject;

public class BaseLegalForm extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8095046654957786839L;
	
    private         Integer         legalFormId;
    private         String          legalFormName;
    
    private int               isDel;                                 //是否删除
    private int               sortNo;                                //排序号    
    private int               isActived;                             //1 启用 、 0停用
    private Date              createDate;                            //创建时间
    private Date              updateDate;                            //更新时间
    private int               createUser;                            //创建用户
    private int               updateUser;                            //更新用户
    
	public Integer getLegalFormId() {
		return legalFormId;
	}
	public void setLegalFormId(Integer legalFormId) {
		this.legalFormId = legalFormId;
	}
	public String getLegalFormName() {
		return legalFormName;
	}
	public void setLegalFormName(String legalFormName) {
		this.legalFormName = legalFormName;
	}
    
	public int getIsDel() {
		return isDel;
	}
	public void setIsDel(int isDel) {
		this.isDel = isDel;
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
        if (!(object instanceof BaseLegalForm)) {
            return false;
        }
        BaseLegalForm rhs = (BaseLegalForm) object;
        return new EqualsBuilder()
        		.appendSuper(super.equals(object))
        		.append(this.legalFormId,rhs.legalFormId)
        		.append(this.legalFormName, rhs.legalFormName)
        		.append(this.isDel, rhs.isDel)
        		.append(this.sortNo, rhs.sortNo)
        		.append(this.isActived, rhs.isActived)
        		.append(this.createDate, rhs.createDate)
        		.append(this.updateDate, rhs.updateDate)
        		.append(this.createUser, rhs.createUser)
        		.append(this.updateUser, rhs.updateUser)
        		.isEquals();
    }
    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(-122580831, 1296370945)
        	.appendSuper(super.hashCode())
        	.append(this.legalFormId)
        	.append(this.legalFormName)
        	.append(this.isDel)
        	.append(this.sortNo)
        	.append(this.isActived)
        	.append(this.createDate)
        	.append(this.updateDate)
        	.append(this.updateUser)
        	.toHashCode();
    }    

}
