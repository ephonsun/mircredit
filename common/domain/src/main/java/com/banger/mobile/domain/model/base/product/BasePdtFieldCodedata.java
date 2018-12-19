package com.banger.mobile.domain.model.base.product;

import java.sql.Timestamp;

import com.banger.mobile.domain.model.base.BaseObject;

/**
 * 产品类型模版下拉字段项
 */

public class BasePdtFieldCodedata extends BaseObject implements java.io.Serializable {

    // Fields    

    private static final long serialVersionUID = 7118593625696422209L;
    private Integer   fieldCodedataId;      //主键ID
    private Integer   templateFieldId;      //模版字段ID
    private String    fieldCodedataKey;     //下拉字段键
    private String    fieldCodedataValue;   //下拉字段值
    private Integer   fieldCodedataOrder;   //顺序
    private Integer   isDel;                //是否删除
    private Timestamp createDate;
    private Timestamp updateDate;
    private Integer   createUser;
    private Integer   updateUser;

    // Constructors

    /** default constructor */
    public BasePdtFieldCodedata() {
    }

    /** minimal constructor */
    public BasePdtFieldCodedata(Integer fieldCodedataId, Integer templateFieldId,
                            String fieldCodedataKey, String fieldCodedataValue, Integer isDel,
                            Timestamp createDate, Integer createUser) {
        this.fieldCodedataId = fieldCodedataId;
        this.templateFieldId = templateFieldId;
        this.fieldCodedataKey = fieldCodedataKey;
        this.fieldCodedataValue = fieldCodedataValue;
        this.isDel = isDel;
        this.createDate = createDate;
        this.createUser = createUser;
    }

    /** full constructor */
    public BasePdtFieldCodedata(Integer fieldCodedataId, Integer templateFieldId,
                            String fieldCodedataKey, String fieldCodedataValue,
                            Integer fieldCodedataOrder, Integer isDel, Timestamp createDate,
                            Timestamp updateDate, Integer createUser, Integer updateUser) {
        this.fieldCodedataId = fieldCodedataId;
        this.templateFieldId = templateFieldId;
        this.fieldCodedataKey = fieldCodedataKey;
        this.fieldCodedataValue = fieldCodedataValue;
        this.fieldCodedataOrder = fieldCodedataOrder;
        this.isDel = isDel;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.createUser = createUser;
        this.updateUser = updateUser;
    }

    // Property accessors

    public Integer getFieldCodedataId() {
        return this.fieldCodedataId;
    }

    public void setFieldCodedataId(Integer fieldCodedataId) {
        this.fieldCodedataId = fieldCodedataId;
    }

    public Integer getTemplateFieldId() {
        return this.templateFieldId;
    }

    public void setTemplateFieldId(Integer templateFieldId) {
        this.templateFieldId = templateFieldId;
    }

    public String getFieldCodedataKey() {
        return this.fieldCodedataKey;
    }

    public void setFieldCodedataKey(String fieldCodedataKey) {
        this.fieldCodedataKey = fieldCodedataKey;
    }

    public String getFieldCodedataValue() {
        return this.fieldCodedataValue;
    }

    public void setFieldCodedataValue(String fieldCodedataValue) {
        this.fieldCodedataValue = fieldCodedataValue;
    }

    public Integer getFieldCodedataOrder() {
        return this.fieldCodedataOrder;
    }

    public void setFieldCodedataOrder(Integer fieldCodedataOrder) {
        this.fieldCodedataOrder = fieldCodedataOrder;
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