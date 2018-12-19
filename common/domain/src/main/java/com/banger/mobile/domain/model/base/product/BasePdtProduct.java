package com.banger.mobile.domain.model.base.product;

import java.util.Date;

import com.banger.mobile.domain.model.base.BaseObject;

/**
 * 产品主体
 */

public class BasePdtProduct extends BaseObject implements java.io.Serializable {

    private static final long serialVersionUID = 1752953071687214008L;
    // Fields    

    private Integer   productId;        //主键ID
    private Integer   templateId;       //模版ID
    private String    productName;      //产品名称
    private String    productCode;      //产品编号
    private Integer   productState;     //手动停售标志
    private Date      saleStartDate;    //上架开始日期
    private Date      saleEndDate;      //上架结束日期
    private Integer   isDel;            //是否删除，0未删除，1已删除
    private Date      createDate;
    private Date      updateDate;
    private Integer   createUser;
    private Integer   updateUser;

    // Constructors

    /** default constructor */
    public BasePdtProduct() {
    }

    /** minimal constructor */
    public BasePdtProduct(Integer productId) {
        this.productId = productId;
    }

    /** full constructor */
    public BasePdtProduct(Integer productId, Integer templateId, String productName,
                      String productCode, Integer productState, Date saleStartDate,
                      Date saleEndDate, Integer isDel, Date createDate,
                      Date updateDate, Integer createUser, Integer updateUser) {
        this.productId = productId;
        this.templateId = templateId;
        this.productName = productName;
        this.productCode = productCode;
        this.productState = productState;
        this.saleStartDate = saleStartDate;
        this.saleEndDate = saleEndDate;
        this.isDel = isDel;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.createUser = createUser;
        this.updateUser = updateUser;
    }

    // Property accessors

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

    public String getProductName() {
        return this.productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductCode() {
        return this.productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public Integer getProductState() {
        return this.productState;
    }

    public void setProductState(Integer productState) {
        this.productState = productState;
    }

    public Date getSaleStartDate() {
        return this.saleStartDate;
    }

    public void setSaleStartDate(Date saleStartDate) {
        this.saleStartDate = saleStartDate;
    }

    public Date getSaleEndDate() {
        return this.saleEndDate;
    }

    public void setSaleEndDate(Date saleEndDate) {
        this.saleEndDate = saleEndDate;
    }

    public Integer getIsDel() {
        return this.isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
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