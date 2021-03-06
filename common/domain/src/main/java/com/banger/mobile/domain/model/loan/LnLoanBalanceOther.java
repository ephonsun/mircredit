package com.banger.mobile.domain.model.loan;

import java.math.BigDecimal;

public class LnLoanBalanceOther {
    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column YDWD.LN_LOAN_BALANCE_OTHER.LOAN_BALANCE_OTHER_ID
     *
     * @abatorgenerated Mon Mar 07 16:12:58 CST 2016
     */
    private Integer loanBalanceOtherId;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column YDWD.LN_LOAN_BALANCE_OTHER.LOAN_BALANCE_ID
     *
     * @abatorgenerated Mon Mar 07 16:12:58 CST 2016
     */
    private Integer loanBalanceId;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column YDWD.LN_LOAN_BALANCE_OTHER.PRODUCT_NAME
     *
     * @abatorgenerated Mon Mar 07 16:12:58 CST 2016
     */
    private String productName;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column YDWD.LN_LOAN_BALANCE_OTHER.BUYING_PRICE
     *
     * @abatorgenerated Mon Mar 07 16:12:58 CST 2016
     */
    private BigDecimal buyingPrice;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column YDWD.LN_LOAN_BALANCE_OTHER.SALES_PRICE
     *
     * @abatorgenerated Mon Mar 07 16:12:58 CST 2016
     */
    private BigDecimal salesPrice;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column YDWD.LN_LOAN_BALANCE_OTHER.SALES_PROP
     *
     * @abatorgenerated Mon Mar 07 16:12:58 CST 2016
     */
    private String salesProp;

   

    public Integer getLoanBalanceOtherId() {
		return loanBalanceOtherId;
	}

	public void setLoanBalanceOtherId(Integer loanBalanceOtherId) {
		this.loanBalanceOtherId = loanBalanceOtherId;
	}

	
    public Integer getLoanBalanceId() {
		return loanBalanceId;
	}

	public void setLoanBalanceId(Integer loanBalanceId) {
		this.loanBalanceId = loanBalanceId;
	}

	/**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column YDWD.LN_LOAN_BALANCE_OTHER.PRODUCT_NAME
     *
     * @return the value of YDWD.LN_LOAN_BALANCE_OTHER.PRODUCT_NAME
     *
     * @abatorgenerated Mon Mar 07 16:12:58 CST 2016
     */
    public String getProductName() {
        return productName;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column YDWD.LN_LOAN_BALANCE_OTHER.PRODUCT_NAME
     *
     * @param productName the value for YDWD.LN_LOAN_BALANCE_OTHER.PRODUCT_NAME
     *
     * @abatorgenerated Mon Mar 07 16:12:58 CST 2016
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column YDWD.LN_LOAN_BALANCE_OTHER.BUYING_PRICE
     *
     * @return the value of YDWD.LN_LOAN_BALANCE_OTHER.BUYING_PRICE
     *
     * @abatorgenerated Mon Mar 07 16:12:58 CST 2016
     */
  

   
    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column YDWD.LN_LOAN_BALANCE_OTHER.SALES_PROP
     *
     * @return the value of YDWD.LN_LOAN_BALANCE_OTHER.SALES_PROP
     *
     * @abatorgenerated Mon Mar 07 16:12:58 CST 2016
     */
    public String getSalesProp() {
        return salesProp;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column YDWD.LN_LOAN_BALANCE_OTHER.SALES_PROP
     *
     * @param salesProp the value for YDWD.LN_LOAN_BALANCE_OTHER.SALES_PROP
     *
     * @abatorgenerated Mon Mar 07 16:12:58 CST 2016
     */
    public void setSalesProp(String salesProp) {
        this.salesProp = salesProp;
    }

	public BigDecimal getBuyingPrice() {
		return buyingPrice;
	}

	public void setBuyingPrice(BigDecimal buyingPrice) {
		this.buyingPrice = buyingPrice;
	}

	public BigDecimal getSalesPrice() {
		return salesPrice;
	}

	public void setSalesPrice(BigDecimal salesPrice) {
		this.salesPrice = salesPrice;
	}
    
    
}