package com.banger.mobile.domain.model.base.product;

import java.sql.Timestamp;

import com.banger.mobile.domain.model.base.BaseObject;

/**
 * 产品类型模版字段
 */

public class BasePdtTemplateField extends BaseObject implements java.io.Serializable {

    // Fields    

    private static final long serialVersionUID = -7213193361684439593L;
    private Integer   templateFieldId;      //主键ID
    private Integer   templateId;           //模版ID
    private String    templateFieldName;    //字段名称
    private String    templateFieldType;    //字段类型
    private Integer   templateFieldOrder;   //字段顺序
    private Integer   templateFieldSearch;  //搜索字段
    private String    templateFieldExt;     //扩展属性
    private Integer   isBuiltin;            //是否内置
    private Integer   isDel;
    private Timestamp createDate;
    private Timestamp updateDate;
    private Integer   createUser;
    private Integer   updateUser;

    // Constructors

    /** default constructor */
    public BasePdtTemplateField() {
    }

    /** minimal constructor */
    public BasePdtTemplateField(Integer templateFieldId, Integer templateId, String templateFieldName,
                            String templateFieldType, Integer templateFieldOrder, Integer isDel,
                            Timestamp createDate, Integer createUser) {
        this.templateFieldId = templateFieldId;
        this.templateId = templateId;
        this.templateFieldName = templateFieldName;
        this.templateFieldType = templateFieldType;
        this.templateFieldOrder = templateFieldOrder;
        this.isDel = isDel;
        this.createDate = createDate;
        this.createUser = createUser;
    }

    /** full constructor */
    public BasePdtTemplateField(Integer templateFieldId, Integer templateId, String templateFieldName,
                            String templateFieldType, Integer templateFieldOrder,
                            Integer templateFieldSearch, String templateFieldExt,
                            Integer isBuiltin, Integer isDel, Timestamp createDate,
                            Timestamp updateDate, Integer createUser, Integer updateUser) {
        this.templateFieldId = templateFieldId;
        this.templateId = templateId;
        this.templateFieldName = templateFieldName;
        this.templateFieldType = templateFieldType;
        this.templateFieldOrder = templateFieldOrder;
        this.templateFieldSearch = templateFieldSearch;
        this.templateFieldExt = templateFieldExt;
        this.isBuiltin = isBuiltin;
        this.isDel = isDel;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.createUser = createUser;
        this.updateUser = updateUser;
    }

    // Property accessors

    public Integer getTemplateFieldId() {
        return this.templateFieldId;
    }

    public void setTemplateFieldId(Integer templateFieldId) {
        this.templateFieldId = templateFieldId;
    }

    public Integer getTemplateId() {
        return this.templateId;
    }

    public void setTemplateId(Integer templateId) {
        this.templateId = templateId;
    }

    public String getTemplateFieldName() {
        return this.templateFieldName;
    }

    public void setTemplateFieldName(String templateFieldName) {
        this.templateFieldName = templateFieldName;
    }

    public String getTemplateFieldType() {
        return this.templateFieldType;
    }

    public void setTemplateFieldType(String templateFieldType) {
        this.templateFieldType = templateFieldType;
    }

    public Integer getTemplateFieldOrder() {
        return this.templateFieldOrder;
    }

    public void setTemplateFieldOrder(Integer templateFieldOrder) {
        this.templateFieldOrder = templateFieldOrder;
    }

    public Integer getTemplateFieldSearch() {
        return this.templateFieldSearch;
    }

    public void setTemplateFieldSearch(Integer templateFieldSearch) {
        this.templateFieldSearch = templateFieldSearch;
    }

    public String getTemplateFieldExt() {
        return this.templateFieldExt;
    }

    public void setTemplateFieldExt(String templateFieldExt) {
        this.templateFieldExt = templateFieldExt;
    }

    public Integer getIsBuiltin() {
        return this.isBuiltin;
    }

    public void setIsBuiltin(Integer isBuiltin) {
        this.isBuiltin = isBuiltin;
    }

    public Integer getIsDel() {
        return this.isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    public Timestamp getCreateDate() {
        return this.createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public Timestamp getUpdateDate() {
        return this.updateDate;
    }

    public void setUpdateDate(Timestamp updateDate) {
        this.updateDate = updateDate;
    }

    public Integer getCreateUser() {
        return this.createUser;
    }

    public void setCreateUser(Integer createUser) {
        this.createUser = createUser;
    }

    public Integer getUpdateUser() {
        return this.updateUser;
    }

    public void setUpdateUser(Integer updateUser) {
        this.updateUser = updateUser;
    }

}