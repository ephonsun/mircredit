package com.banger.mobile.domain.model.loan;



import com.banger.mobile.domain.model.base.data.BaseDatCustomerData;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


public class LnProfitLossProd extends BaseDatCustomerData implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3619585457045245162L;
//
    private Integer        profitLossId;        //ID INTEGER NOT NULL,主键
//    private Integer        loanId;              //LOAN_ID INTEGER,贷款ID
    private String         productName;         //PRODUCT_NAME VARCHAR(200),产品种类
    private BigDecimal     sellingPrice;        //SELLING_PRICE DECIMAL,销售价格（元）
    private BigDecimal 	   costPrice;           //COST_PRICE DECIMAL,成本价格（元）
    private BigDecimal 	   grossValue;          //GROSS_VALUE DECIMAL,毛利润(元)
    private Double         grossRate;           //GROSS_RATE DOUBLE,毛利率(%)
    private Double         sellingRate;         //SELLING_RATE DOUBLE,销售比例(%)
    private String         remark;              //REMARK VARCHAR(500),备注
//    private Integer        createUser;          //CREATE_USER INTEGER,
//    private Date           createDate;          //CREATE_DATE TIMESTAMP,
//    private Integer        updateUser;          //UPDATE_USER INTEGER,
//    private Date           updateDate;          //UPDATE_DATE TIMESTAMP,
    
	public Integer getProfitLossId() {
		return profitLossId;
	}
	public void setProfitLossId(Integer profitLossId) {
		this.profitLossId = profitLossId;
	}


	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public BigDecimal getSellingPrice() {
		return sellingPrice;
	}
	public void setSellingPrice(BigDecimal sellingPrice) {
		this.sellingPrice = sellingPrice;
	}
	public BigDecimal getCostPrice() {
		return costPrice;
	}
	public void setCostPrice(BigDecimal costPrice) {
		this.costPrice = costPrice;
	}
	public BigDecimal getGrossValue() {
		return grossValue;
	}
	public void setGrossValue(BigDecimal grossValue) {
		this.grossValue = grossValue;
	}
	public Double getGrossRate() {
		return grossRate;
	}
	public void setGrossRate(Double grossRate) {
		this.grossRate = grossRate;
	}
	public Double getSellingRate() {
		return sellingRate;
	}
	public void setSellingRate(Double sellingRate) {
		this.sellingRate = sellingRate;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}


	
	
	
}
