package com.banger.mobile.domain.model.contract;

import java.io.Serializable;

/**
 * 集合
 */
public class PledgorInfo implements Serializable {
    private static final long serialVersionUID = 1269170432131108007L;
    private String pledgorName ;//出质人
    private String pledgorCorporation ;//法定代表人
    private String pledgorAddress ;//住所地/住址
    private String pledgorCreditNo ;//组织机构代码/统一社会信用代码/身份证号码
    private String pledgeNo ;//质押物编号
    private String pledgeType ;//质押财产类型
    private String pledgeOwnershipName ;//权利/产权凭证名称
    private String pledgeOwnershipNo ;//权利/产权凭证编号
    private String goodsAmount ;//面额总值/现金价值
    private String goodsValue ;//协商/评估价值
    private String pledgorSendAddress ;//送达地址
    private String pledgorPhoneNum ;//电话
    private String pledgorFaxNum ;//传真
    private String pledgorEmail ;//电子邮箱


    public PledgorInfo() {

    }

    public String getPledgorName() {
        return pledgorName;
    }

    public void setPledgorName(String pledgorName) {
        this.pledgorName = pledgorName;
    }

    public String getPledgorCorporation() {
        return pledgorCorporation;
    }

    public void setPledgorCorporation(String pledgorCorporation) {
        this.pledgorCorporation = pledgorCorporation;
    }

    public String getPledgorAddress() {
        return pledgorAddress;
    }

    public void setPledgorAddress(String pledgorAddress) {
        this.pledgorAddress = pledgorAddress;
    }

    public String getPledgorCreditNo() {
        return pledgorCreditNo;
    }

    public void setPledgorCreditNo(String pledgorCreditNo) {
        this.pledgorCreditNo = pledgorCreditNo;
    }

    public String getPledgeNo() {
        return pledgeNo;
    }

    public void setPledgeNo(String pledgeNo) {
        this.pledgeNo = pledgeNo;
    }

    public String getPledgeType() {
        return pledgeType;
    }

    public void setPledgeType(String pledgeType) {
        this.pledgeType = pledgeType;
    }

    public String getPledgeOwnershipName() {
        return pledgeOwnershipName;
    }

    public void setPledgeOwnershipName(String pledgeOwnershipName) {
        this.pledgeOwnershipName = pledgeOwnershipName;
    }

    public String getPledgeOwnershipNo() {
        return pledgeOwnershipNo;
    }

    public void setPledgeOwnershipNo(String pledgeOwnershipNo) {
        this.pledgeOwnershipNo = pledgeOwnershipNo;
    }

    public String getGoodsAmount() {
        return goodsAmount;
    }

    public void setGoodsAmount(String goodsAmount) {
        this.goodsAmount = goodsAmount;
    }

    public String getGoodsValue() {
        return goodsValue;
    }

    public void setGoodsValue(String goodsValue) {
        this.goodsValue = goodsValue;
    }

    public String getPledgorSendAddress() {
        return pledgorSendAddress;
    }

    public void setPledgorSendAddress(String pledgorSendAddress) {
        this.pledgorSendAddress = pledgorSendAddress;
    }

    public String getPledgorPhoneNum() {
        return pledgorPhoneNum;
    }

    public void setPledgorPhoneNum(String pledgorPhoneNum) {
        this.pledgorPhoneNum = pledgorPhoneNum;
    }

    public String getPledgorFaxNum() {
        return pledgorFaxNum;
    }

    public void setPledgorFaxNum(String pledgorFaxNum) {
        this.pledgorFaxNum = pledgorFaxNum;
    }

    public String getPledgorEmail() {
        return pledgorEmail;
    }

    public void setPledgorEmail(String pledgorEmail) {
        this.pledgorEmail = pledgorEmail;
    }
}
