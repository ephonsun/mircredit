package com.banger.mobile.domain.model.base.template;

import java.io.Serializable;
import java.util.Date;

import com.banger.mobile.domain.model.base.BaseObject;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

public class BaseCrmTemplate extends BaseObject implements Serializable {

    private static final long serialVersionUID = 1811581764457440575L;
    // Fields

    private int               templateId;
    private int               isDel;
    private int               templateTypeId;
    private String            templateName;
    private String            remark;
    private int               sortno;
    private int               isBasic;
    private Date              createDate;
    private Date              updateDate;
    private int               createUser;
    private int               updateUser;

    // Constructors

    /** default constructor */
    public BaseCrmTemplate() {
    }

    /** minimal constructor */
    public BaseCrmTemplate(int templateId) {
        this.templateId = templateId;
    }

    /** full constructor */
    public BaseCrmTemplate(int templateId, int isDel, int templateTypeId, String templateName,
                           String remark, int sortno, int isBasic, Date createDate,
                           Date updateDate, int createUser, int updateUser) {
        this.templateId = templateId;
        this.isDel = isDel;
        this.templateTypeId = templateTypeId;
        this.templateName = templateName;
        this.remark = remark;
        this.sortno = sortno;
        this.isBasic = isBasic;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.createUser = createUser;
        this.updateUser = updateUser;
    }

    public int getTemplateId() {
        return this.templateId;
    }

    public void setTemplateId(int templateId) {
        this.templateId = templateId;
    }

    public int getTemplateTypeId() {
        return this.templateTypeId;
    }

    public void setTemplateTypeId(int templateTypeId) {
        this.templateTypeId = templateTypeId;
    }

    public int getIsDel() {
        return this.isDel;
    }

    public void setIsDel(int isDel) {
        this.isDel = isDel;
    }

    public String getTemplateName() {
        return this.templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getSortno() {
        return this.sortno;
    }

    public void setSortno(int sortno) {
        this.sortno = sortno;
    }

    public int getIsBasic() {
        return this.isBasic;
    }

    public void setIsBasic(int isBasic) {
        this.isBasic = isBasic;
    }

    public Date getCreateDate() {
        return this.createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return this.updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public int getCreateUser() {
        return this.createUser;
    }

    public void setCreateUser(int createUser) {
        this.createUser = createUser;
    }

    public int getUpdateUser() {
        return this.updateUser;
    }

    public void setUpdateUser(int updateUser) {
        this.updateUser = updateUser;
    }

    /**
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object) {
        if (!(object instanceof BaseCrmTemplate)) {
            return false;
        }
        BaseCrmTemplate rhs = (BaseCrmTemplate) object;
        return new EqualsBuilder().appendSuper(super.equals(object)).append(this.templateName,
            rhs.templateName).append(this.isDel, rhs.isDel).append(this.sortno, rhs.sortno).append(
            this.isBasic, rhs.isBasic).append(this.createDate, rhs.createDate).append(this.remark,
            rhs.remark).append(this.templateTypeId, rhs.templateTypeId).append(this.createUser,
            rhs.createUser).append(this.updateDate, rhs.updateDate).append(this.templateId,
            rhs.templateId).append(this.updateUser, rhs.updateUser).isEquals();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(-1049950361, 1654481113).appendSuper(super.hashCode()).append(
            this.templateName).append(this.isDel).append(this.sortno).append(this.isBasic).append(
            this.createDate).append(this.remark).append(this.templateTypeId)
            .append(this.createUser).append(this.updateDate).append(this.templateId).append(
                this.updateUser).toHashCode();
    }

}