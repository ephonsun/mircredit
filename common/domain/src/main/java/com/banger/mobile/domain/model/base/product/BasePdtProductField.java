package com.banger.mobile.domain.model.base.product;

import java.util.Date;

import com.banger.mobile.domain.model.base.BaseObject;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * 产品自定义字段
 */

public class BasePdtProductField extends BaseObject implements java.io.Serializable {

    // Fields    

    private static final long serialVersionUID = 2164509972747800618L;
    private Integer   productFieldId;       //主键ID
    private Integer   productId;            //产品ID
    private Integer   templateId;           //模版ID
    private Integer   templateFieldId;      //自定义字段ID
    private String    templateFieldType;    //字段类型
    private String    fieldValueString;     //字段类型名称
    private Date      fieldValueDatetime;   //日期
    private Double    fieldValueNumeric;    //数值
    private Integer   fieldValueBoolean;    //布尔
    private Integer   isDel;                //是否删除

    // Constructors

    /** default constructor */
    public BasePdtProductField() {
    }

    /** minimal constructor */
    public BasePdtProductField(Integer productFieldId, Integer productId, Integer templateId,
                           Integer templateFieldId, Integer isDel) {
        this.productFieldId = productFieldId;
        this.productId = productId;
        this.templateId = templateId;
        this.templateFieldId = templateFieldId;
        this.isDel = isDel;
    }

    /** full constructor */
    public BasePdtProductField(Integer productFieldId, Integer productId, Integer templateId,
                           Integer templateFieldId, String templateFieldType,
                           String fieldValueString, Date fieldValueDatetime,
                           Double fieldValueNumeric, Integer fieldValueBoolean, Integer isDel) {
        this.productFieldId = productFieldId;
        this.productId = productId;
        this.templateId = templateId;
        this.templateFieldId = templateFieldId;
        this.templateFieldType = templateFieldType;
        this.fieldValueString = fieldValueString;
        this.fieldValueDatetime = fieldValueDatetime;
        this.fieldValueNumeric = fieldValueNumeric;
        this.fieldValueBoolean = fieldValueBoolean;
        this.isDel = isDel;
    }

    // Property accessors

    public Integer getProductFieldId() {
        return this.productFieldId;
    }

    public void setProductFieldId(Integer productFieldId) {
        this.productFieldId = productFieldId;
    }

    public Integer getProductId() {
        return this.productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getTemplateId() {
        return this.templateId;
    }

    public void setTemplateId(Integer templateId) {
        this.templateId = templateId;
    }

    public Integer getTemplateFieldId() {
        return this.templateFieldId;
    }

    public void setTemplateFieldId(Integer templateFieldId) {
        this.templateFieldId = templateFieldId;
    }

    public String getTemplateFieldType() {
        return this.templateFieldType;
    }

    public void setTemplateFieldType(String templateFieldType) {
        this.templateFieldType = templateFieldType;
    }

    public String getFieldValueString() {
        return this.fieldValueString;
    }

    public void setFieldValueString(String fieldValueString) {
        this.fieldValueString = fieldValueString;
    }

    public Date getFieldValueDatetime() {
        return this.fieldValueDatetime;
    }

    public void setFieldValueDatetime(Date fieldValueDatetime) {
        this.fieldValueDatetime = fieldValueDatetime;
    }

    public Double getFieldValueNumeric() {
        return this.fieldValueNumeric;
    }

    public void setFieldValueNumeric(Double fieldValueNumeric) {
        this.fieldValueNumeric = fieldValueNumeric;
    }

    public Integer getFieldValueBoolean() {
        return this.fieldValueBoolean;
    }

    public void setFieldValueBoolean(Integer fieldValueBoolean) {
        this.fieldValueBoolean = fieldValueBoolean;
    }

    public Integer getIsDel() {
        return this.isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(-8965159, 53247093).appendSuper(super.hashCode())
            .append(this.fieldValueString).append(this.fieldValueNumeric).append(this.fieldValueBoolean)
            .append(this.fieldValueDatetime)
            .toHashCode();
    }


}