package com.banger.mobile.domain.model.base.product;

import java.sql.Timestamp;

import com.banger.mobile.domain.model.base.BaseObject;

/**
 * 产品类型模版
 */

public class BasePdtTemplate extends BaseObject implements java.io.Serializable {

    // Fields    

    private static final long serialVersionUID = 9142879865705572906L;
    private Integer   templateId;       //主键ID
    private String    templateName;     //模版名称
    private Double    templateRate;     //最低日均销售比
    private Integer   templateOrder;    //顺序
    private Integer   templateState;    //状态
    private Integer   isBuiltin;        //是否内置
    private Integer   isDel;            
    private Timestamp createDate;
    private Timestamp updateDate;
    private Integer   createUser;
    private Integer   updateUser;

    // Constructors

    /** default constructor */
    public BasePdtTemplate() {
    }

    /** minimal constructor */
    public BasePdtTemplate(Integer templateId, Integer isDel, Timestamp createDate, Integer createUser) {
        this.templateId = templateId;
        this.isDel = isDel;
        this.createDate = createDate;
        this.createUser = createUser;
    }

    /** full constructor */
    public BasePdtTemplate(Integer templateId, String templateName, Double templateRate,
                       Integer templateOrder, Integer templateState, Integer isBuiltin,
                       Integer isDel, Timestamp createDate, Timestamp updateDate,
                       Integer createUser, Integer updateUser) {
        this.templateId = templateId;
        this.templateName = templateName;
        this.templateRate = templateRate;
        this.templateOrder = templateOrder;
        this.templateState = templateState;
        this.isBuiltin = isBuiltin;
        this.isDel = isDel;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.createUser = createUser;
        this.updateUser = updateUser;
    }

    // Property accessors

    public Integer getTemplateId() {
        return this.templateId;
    }

    public void setTemplateId(Integer templateId) {
        this.templateId = templateId;
    }

    public String getTemplateName() {
        return this.templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public Double getTemplateRate() {
        return this.templateRate;
    }

    public void setTemplateRate(Double templateRate) {
        this.templateRate = templateRate;
    }

    public Integer getTemplateOrder() {
        return this.templateOrder;
    }

    public void setTemplateOrder(Integer templateOrder) {
        this.templateOrder = templateOrder;
    }

    public Integer getTemplateState() {
        return this.templateState;
    }

    public void setTemplateState(Integer templateState) {
        this.templateState = templateState;
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