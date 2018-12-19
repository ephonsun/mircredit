package com.banger.mobile.domain.model.contract;

import com.banger.mobile.domain.model.loan.LnLoanGuarantorBean;
import com.banger.mobile.domain.model.loan.LnLoanInfo;
import com.banger.mobile.domain.model.loan.LnRepaymentPlan;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 集合
 */
public class GuarantorInfo implements Serializable {
    private static final long serialVersionUID = 1269170432131108007L;
    private String guarantorName;//保证人
    private String guarantorCorporation;//法定代表人
    private String guarantorAddress;//住所地/住址
    private String guarantorSendAddress;//送达地址
    private String guarantorCreditNo;//组织机构代码/统一社会信用代码/身份证号码
    private String guarantorPhoneNum;//电话
    private String guarantorFaxNum;//传真
    private String guarantorEmail;//广东法院诉讼文书接收专用免费电子邮箱


    public GuarantorInfo() {

    }

    public String getGuarantorSendAddress() {
        return guarantorSendAddress;
    }

    public void setGuarantorSendAddress(String guarantorSendAddress) {
        this.guarantorSendAddress = guarantorSendAddress;
    }

    public String getGuarantorName() {
        return guarantorName;
    }

    public void setGuarantorName(String guarantorName) {
        this.guarantorName = guarantorName;
    }

    public String getGuarantorCorporation() {
        return guarantorCorporation;
    }

    public void setGuarantorCorporation(String guarantorCorporation) {
        this.guarantorCorporation = guarantorCorporation;
    }

    public String getGuarantorAddress() {
        return guarantorAddress;
    }

    public void setGuarantorAddress(String guarantorAddress) {
        this.guarantorAddress = guarantorAddress;
    }

    public String getGuarantorCreditNo() {
        return guarantorCreditNo;
    }

    public void setGuarantorCreditNo(String guarantorCreditNo) {
        this.guarantorCreditNo = guarantorCreditNo;
    }

    public String getGuarantorPhoneNum() {
        return guarantorPhoneNum;
    }

    public void setGuarantorPhoneNum(String guarantorPhoneNum) {
        this.guarantorPhoneNum = guarantorPhoneNum;
    }

    public String getGuarantorFaxNum() {
        return guarantorFaxNum;
    }

    public void setGuarantorFaxNum(String guarantorFaxNum) {
        this.guarantorFaxNum = guarantorFaxNum;
    }

    public String getGuarantorEmail() {
        return guarantorEmail;
    }

    public void setGuarantorEmail(String guarantorEmail) {
        this.guarantorEmail = guarantorEmail;
    }
}
