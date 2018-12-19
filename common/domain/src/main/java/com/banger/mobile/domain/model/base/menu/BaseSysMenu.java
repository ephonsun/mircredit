package com.banger.mobile.domain.model.base.menu;

import java.sql.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.banger.mobile.domain.model.base.BaseObject;

/**
 * SysMenu entity. @author MyEclipse Persistence Tools
 */

public class BaseSysMenu extends BaseObject implements java.io.Serializable {

	private static final long serialVersionUID = -7426708952109628124L;
    // Fields


    private Integer menuId;                 //菜单ID
	private String menuCode;                //菜单编号
	private String menuName;                //菜单名称
	private Integer menuParentId;           //上级菜单ID
	private String url;                     //网页地址
	private Integer isLeaf;                 //节点类型
	private Integer sortno;                 //排序号
	private String icon;                    //图标
	private String remark;                  //备注
	private Date createDate;                //创建时间
	private Date updateDate;                //更新时间
	private Integer createUser;             //创建用户
	private Integer updateUser;             //更新用户
	private Integer isDel;                  //是否删除
    /**
     * 
     */
    public BaseSysMenu() {
        super();
    }
    /**
     * @param menuId
     * @param menuCode
     * @param menuName
     * @param menuParentId
     * @param url
     * @param isLeaf
     * @param sortno
     * @param icon
     * @param remark
     * @param createDate
     * @param updateDate
     * @param createUser
     * @param updateUser
     * @param isDel
     */
    public BaseSysMenu(Integer menuId, String menuCode, String menuName, Integer menuParentId,
                       String url, Integer isLeaf, Integer sortno, String icon, String remark,
                       Date createDate, Date updateDate, Integer createUser, Integer updateUser,
                       Integer isDel) {
        super();
        this.menuId = menuId;
        this.menuCode = menuCode;
        this.menuName = menuName;
        this.menuParentId = menuParentId;
        this.url = url;
        this.isLeaf = isLeaf;
        this.sortno = sortno;
        this.icon = icon;
        this.remark = remark;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.createUser = createUser;
        this.updateUser = updateUser;
        this.isDel = isDel;
    }
    public Integer getMenuId() {
        return menuId;
    }
    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }
    public String getMenuCode() {
        return menuCode;
    }
    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode;
    }
    public String getMenuName() {
        return menuName;
    }
    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }
    public Integer getMenuParentId() {
        return menuParentId;
    }
    public void setMenuParentId(Integer menuParentId) {
        this.menuParentId = menuParentId;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public Integer getIsLeaf() {
        return isLeaf;
    }
    public void setIsLeaf(Integer isLeaf) {
        this.isLeaf = isLeaf;
    }
    public Integer getSortno() {
        return sortno;
    }
    public void setSortno(Integer sortno) {
        this.sortno = sortno;
    }
    public String getIcon() {
        return icon;
    }
    public void setIcon(String icon) {
        this.icon = icon;
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
        if (!(object instanceof BaseSysMenu)) {
            return false;
        }
        BaseSysMenu rhs = (BaseSysMenu) object;
        return new EqualsBuilder().appendSuper(super.equals(object))
            .append(this.menuCode, rhs.menuCode).append(this.menuParentId, rhs.menuParentId)
            .append(this.icon, rhs.icon).append(this.remark, rhs.remark)
            .append(this.menuId, rhs.menuId).append(this.url, rhs.url)
            .append(this.updateDate, rhs.updateDate).append(this.sortno, rhs.sortno)
            .append(this.isDel, rhs.isDel).append(this.createUser, rhs.createUser)
            .append(this.isLeaf, rhs.isLeaf).append(this.createDate, rhs.createDate)
            .append(this.menuName, rhs.menuName).append(this.updateUser, rhs.updateUser).isEquals();
    }
    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(1099704835, -1060337861).appendSuper(super.hashCode())
            .append(this.menuCode).append(this.menuParentId).append(this.icon).append(this.remark)
            .append(this.menuId).append(this.url).append(this.updateDate).append(this.sortno)
            .append(this.isDel).append(this.createUser).append(this.isLeaf).append(this.createDate)
            .append(this.menuName).append(this.updateUser).toHashCode();
    }
    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this).append("createDate", this.createDate)
            .append("updateDate", this.updateDate).append("menuId", this.menuId)
            .append("isDel", this.isDel).append("menuParentId", this.menuParentId)
            .append("icon", this.icon).append("remark", this.remark)
            .append("menuName", this.menuName).append("url", this.url)
            .append("sortno", this.sortno).append("endRow", this.getEndRow())
            .append("createUser", this.createUser).append("menuCode", this.menuCode)
            .append("updateUser", this.updateUser).append("isLeaf", this.isLeaf)
            .append("startRow", this.getStartRow()).toString();
    }

	

}