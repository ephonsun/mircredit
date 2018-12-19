package com.banger.mobile.domain.model.contract;

import java.io.Serializable;

/**
 * 集合
 */
public class MortgagorInfo implements Serializable {
    private static final long serialVersionUID = 1269170432131108007L;
    private String mortgagorName ;//抵押人
    private String mortgagorCorporation ;//法定代表人
    private String mortgagorAddress ;//住所地地址
    private String mortgagorCreditNo;//身份证
    private String mortgagorSendAddress ;//送达地址
    private String mortgagorPhoneNum;//电话
    private String mortgagorFaxNum;//传真
    private String mortgagorEmail ;//电子邮箱



    public MortgagorInfo() {

    }

    public String getMortgagorName() {
        return mortgagorName;
    }

    public void setMortgagorName(String mortgagorName) {
        this.mortgagorName = mortgagorName;
    }

    public String getMortgagorCorporation() {
        return mortgagorCorporation;
    }

    public void setMortgagorCorporation(String mortgagorCorporation) {
        this.mortgagorCorporation = mortgagorCorporation;
    }

    public String getMortgagorAddress() {
        return mortgagorAddress;
    }

    public void setMortgagorAddress(String mortgagorAddress) {
        this.mortgagorAddress = mortgagorAddress;
    }

    public String getMortgagorCreditNo() {
        return mortgagorCreditNo;
    }

    public void setMortgagorCreditNo(String mortgagorCreditNo) {
        this.mortgagorCreditNo = mortgagorCreditNo;
    }

    public String getMortgagorSendAddress() {
        return mortgagorSendAddress;
    }

    public void setMortgagorSendAddress(String mortgagorSendAddress) {
        this.mortgagorSendAddress = mortgagorSendAddress;
    }

    public String getMortgagorPhoneNum() {
        return mortgagorPhoneNum;
    }

    public void setMortgagorPhoneNum(String mortgagorPhoneNum) {
        this.mortgagorPhoneNum = mortgagorPhoneNum;
    }

    public String getMortgagorFaxNum() {
        return mortgagorFaxNum;
    }

    public void setMortgagorFaxNum(String mortgagorFaxNum) {
        this.mortgagorFaxNum = mortgagorFaxNum;
    }

    public String getMortgagorEmail() {
        return mortgagorEmail;
    }

    public void setMortgagorEmail(String mortgagorEmail) {
        this.mortgagorEmail = mortgagorEmail;
    }
}
