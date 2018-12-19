package com.banger.mobile.domain.model.contract;

import com.banger.mobile.domain.model.loan.LnLoanGuarantorBean;
import com.banger.mobile.domain.model.loan.LnLoanInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 担保最高额
 */
public class ContTopGuaranty extends BaseLnContract implements Serializable {
    private static final long serialVersionUID = 1269170432131108007L;
    private String code ;//编号
    private String guarantorNames;//保证人
//    private String creditNo;//贷款人 统一社会信用代码
    private List<GuarantorInfo> guarantorInfoList;//保证人、 法定代表人、 住所地/住址、 组织机构代码/统一社会信用代码/身份证号码   集合
    private Date guarantyBeginTime ;//保证期限开始
    private Date guarantyEndTime;//保证期限结束
    private String guarantyCurrency="人民币";//保证金额   币种
    private String guarantyAmountWords;//保证金额  大写
    private String guarantyAmountFigures;//保证金额  小写
    public ContTopGuaranty(){

    }
    public ContTopGuaranty(LnLoanInfo lnLoanInfo, List<LnLoanGuarantorBean> loanGuarantorList) {

        GuarantorInfo guarantorInfo;
        StringBuffer sbf = new StringBuffer("");
        guarantorInfoList=new ArrayList<GuarantorInfo>();
        if(!CollectionUtils.isEmpty(loanGuarantorList)){
            for(LnLoanGuarantorBean bean : loanGuarantorList ){

                guarantorInfo = new GuarantorInfo();
                if(StringUtils.isNotBlank(bean.getCustomerName())){
                    guarantorInfo.setGuarantorName(bean.getCustomerName());
                    guarantorInfo.setGuarantorCorporation(bean.getCustomerName());
                }
                if(StringUtils.isNotBlank(bean.getIdCard())){
                    guarantorInfo.setGuarantorCreditNo(bean.getIdCard());
                }
                if(StringUtils.isNotBlank(bean.getCphNumber())){
                    guarantorInfo.setGuarantorPhoneNum(bean.getCphNumber());
                }
                if(StringUtils.isNotBlank(bean.getCompanyAddress())){
                    guarantorInfo.setGuarantorAddress(bean.getCompanyAddress());
                    guarantorInfo.setGuarantorSendAddress(bean.getCompanyAddress());
                }
                if(StringUtils.isNotBlank(bean.getFax())){
                    guarantorInfo.setGuarantorFaxNum(bean.getFax());
                }

                this.guarantorInfoList.add(guarantorInfo);
                sbf.append(bean.getCustomerName()).append("，");


//                this.setCusName(this.getCusName());
            }
            this.setGuarantorNames(sbf.substring(0,sbf.length()-1).toString());
        }
        if(StringUtils.isNotBlank(lnLoanInfo.getLendContractNum())){
            this.setContractNo(lnLoanInfo.getLendContractNum());;//合同号
        }
//        this.setLoanUserName(lnLoanInfo.getCusName());
//        String str =lnLoanInfo.getCusName();
        this.setCusName(lnLoanInfo.getCusName());
      }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getGuarantorNames() {
        return guarantorNames;
    }

    public void setGuarantorNames(String guarantorNames) {
        this.guarantorNames = guarantorNames;
    }

//    public String getCreditNo() {
//        return creditNo;
//    }
//
//    public void setCreditNo(String creditNo) {
//        this.creditNo = creditNo;
//    }

    public List<GuarantorInfo> getGuarantorInfoList() {
        return guarantorInfoList;
    }

    public void setGuarantorInfoList(List<GuarantorInfo> guarantorInfoList) {
        this.guarantorInfoList = guarantorInfoList;
    }

    public Date getGuarantyBeginTime() {
        return guarantyBeginTime;
    }

    public void setGuarantyBeginTime(Date guarantyBeginTime) {
        this.guarantyBeginTime = guarantyBeginTime;
    }

    public Date getGuarantyEndTime() {
        return guarantyEndTime;
    }

    public void setGuarantyEndTime(Date guarantyEndTime) {
        this.guarantyEndTime = guarantyEndTime;
    }

    public String getGuarantyCurrency() {
        return guarantyCurrency;
    }

    public void setGuarantyCurrency(String guarantyCurrency) {
        this.guarantyCurrency = guarantyCurrency;
    }

    public String getGuarantyAmountWords() {
        return guarantyAmountWords;
    }

    public void setGuarantyAmountWords(String guarantyAmountWords) {
        this.guarantyAmountWords = guarantyAmountWords;
    }

    public String getGuarantyAmountFigures() {
        return guarantyAmountFigures;
    }

    public void setGuarantyAmountFigures(String guarantyAmountFigures) {
        this.guarantyAmountFigures = guarantyAmountFigures;
    }
}
