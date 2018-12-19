package com.banger.mobile.domain.model.base.func;

import java.sql.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.banger.mobile.domain.model.base.BaseObject;

/**
 * SysFunc entity. @author MyEclipse Persistence Tools
 */

public class BaseSysFunc extends BaseObject implements java.io.Serializable {

	private static final long serialVersionUID = -4934234105966176366L;
    // Fields


    private Integer funcId;         //功能ID
	private Integer menuId;         //菜单ID
	private String funcCode;        //功能代码
	private String funcName;        //功能名称
	private String funcUrl;         //网页地址
	private String remark;          //备注
	private Integer sortno;         //排序号
	private Date createDate;        //创建时间
	private Date updateDate;        //更新时间
	private Integer createUser;     //创建用户
	private Integer updateUser;     //更新用户
	private Integer isDel;          //是否删除
    /**
     * 
     */
    public BaseSysFunc() {
        super();
    }
    /**
     * @param funcId
     * @param menuId
     * @param funcCode
     * @param funcName
     * @param funcUrl
     * @param remark
     * @param sortno
     * @param createDate
     * @param updateDate
     * @param createUser
     * @param updateUser
     * @param isDel
     */
    public BaseSysFunc(Integer funcId, Integer menuId, String funcCode, String funcName,
                       String funcUrl, String remark, Integer sortno, Date createDate,
                       Date updateDate, Integer createUser, Integer updateUser, Integer isDel) {
        super();
        this.funcId = funcId;
        this.menuId = menuId;
        this.funcCode = funcCode;
        this.funcName = funcName;
        this.funcUrl = funcUrl;
        this.remark = remark;
        this.sortno = sortno;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.createUser = createUser;
        this.updateUser = updateUser;
        this.isDel = isDel;
    }
    public Integer getFuncId() {
        return funcId;
    }
    public void setFuncId(Integer funcId) {
        this.funcId = funcId;
    }
    public Integer getMenuId() {
        return menuId;
    }
    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }
    public String getFuncCode() {
        return funcCode;
    }
    public void setFuncCode(String funcCode) {
        this.funcCode = funcCode;
    }
    public String getFuncName() {
        return funcName;
    }
    public void setFuncName(String funcName) {
        this.funcName = funcName;
    }
    public String getFuncUrl() {
        return funcUrl;
    }
    public void setFuncUrl(String funcUrl) {
        this.funcUrl = funcUrl;
    }
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    public Integer getSortno() {
        return sortno;
    }
    public void setSortno(Integer sortno) {
        this.sortno = sortno;
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
    public Integer getIsDel() {
        return isDel;
    }
    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }
    /**
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object) {
        if (!(object instanceof BaseSysFunc)) {
            return false;
        }
        BaseSysFunc rhs = (BaseSysFunc) object;
        return new EqualsBuilder().appendSuper(super.equals(object))
            .append(this.funcId, rhs.funcId).append(this.sortno, rhs.sortno)
            .append(this.isDel, rhs.isDel).append(this.createUser, rhs.createUser)
            .append(this.remark, rhs.remark).append(this.funcName, rhs.funcName)
            .append(this.funcCode, rhs.funcCode).append(this.menuId, rhs.menuId)
            .append(this.funcUrl, rhs.funcUrl).append(this.createDate, rhs.createDate)
            .append(this.updateDate, rhs.updateDate).append(this.updateUser, rhs.updateUser)
            .isEquals();
    }
    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(-1054139223, -773696797).appendSuper(super.hashCode())
            .append(this.funcId).append(this.sortno).append(this.isDel).append(this.createUser)
            .append(this.remark).append(this.funcName).append(this.funcCode).append(this.menuId)
            .append(this.funcUrl).append(this.createDate).append(this.updateDate)
            .append(this.updateUser).toHashCode();
    }
    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this).append("createDate", this.createDate)
            .append("updateDate", this.updateDate).append("menuId", this.menuId)
            .append("isDel", this.isDel).append("remark", this.remark)
            .append("sortno", this.sortno).append("endRow", this.getEndRow())
            .append("funcName", this.funcName).append("createUser", this.createUser)
            .append("updateUser", this.updateUser).append("funcCode", this.funcCode)
            .append("funcId", this.funcId).append("startRow", this.getStartRow())
            .append("funcUrl", this.funcUrl).toString();
    }
  

}