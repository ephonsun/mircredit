package com.banger.mobile.domain.model.base.loan;

import com.banger.mobile.domain.model.base.BaseObject;

import java.io.Serializable;
import java.util.Date;

/**
 * @author ouyl
 * @version $Id: BaseLnLoanInfoDictionary.java,v 0.1 13-7-1 ouyl Exp $
 */
public class BaseLnLoanInfoDictionary extends BaseObject implements Serializable {

	private static final long serialVersionUID = -2391963794235078934L;
	
	private Integer loanInfoDictionaryId;
    private String dictionaryName;
    private String dictionaryValue;
    private String remark;
    private Date updateDate;
    private String dictionaryKey;

    public Integer getLoanInfoDictionaryId() {
        return loanInfoDictionaryId;
    }

    public void setLoanInfoDictionaryId(Integer loanInfoDictionaryId) {
        this.loanInfoDictionaryId = loanInfoDictionaryId;
    }

    public String getDictionaryName() {
        return dictionaryName;
    }

    public void setDictionaryName(String dictionaryName) {
        this.dictionaryName = dictionaryName;
    }

    public String getDictionaryValue() {
        return dictionaryValue;
    }

    public void setDictionaryValue(String dictionaryValue) {
        this.dictionaryValue = dictionaryValue;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getDictionaryKey() {
        return dictionaryKey;
    }

    public void setDictionaryKey(String dictionaryKey) {
        this.dictionaryKey = dictionaryKey;
    }
}
