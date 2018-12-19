package com.banger.mobile.domain.model.microTask;

import com.banger.mobile.domain.model.base.microTask.BaseTskSchedule;
import com.banger.mobile.domain.model.microProduct.PdtProductCustomerBean;


public class TskSchedule extends BaseTskSchedule {

	private static final long serialVersionUID = 1089044454812669016L;

	private PdtProductCustomerBean pdtProductCustomerBean;

    private String contactTypeName;

    private Long diffTime; //创建时间与当前时间相差的秒数

    public Long getDiffTime() {
        return diffTime;
    }

    public void setDiffTime(Long diffTime) {
        this.diffTime = diffTime;
    }

    public String getContactTypeName() {
        return contactTypeName;
    }

    public void setContactTypeName(String contactTypeName) {
        this.contactTypeName = contactTypeName;
    }

    public PdtProductCustomerBean getPdtProductCustomerBean() {
        return pdtProductCustomerBean;
    }

    public void setPdtProductCustomerBean(PdtProductCustomerBean pdtProductCustomerBean) {
        this.pdtProductCustomerBean = pdtProductCustomerBean;
    }
	
}