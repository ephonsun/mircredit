package com.banger.mobile.domain.model.contract;

import java.io.Serializable;
import java.util.Date;

/**
 * 集合
 */
public class MortgageGoodsInfo implements Serializable {
    private static final long serialVersionUID = 1269170432131108007L;
    private String goodsNo ;//抵押编号
    private String goodsType;//抵押物类型
    private String goodsOwnershipName;//权属证名称
    private String goodsOwnershipNo ;//权属证号
    private String goodsValue;//协商/评估价值
    private String isLease;//是否租赁
    private String leaseBeginDate;//租赁起止日
    private String goodsName;//抵押物名称
    private String goodsAddress;//地址
    private String goodsLendArea;//土地面积
    private String goodsHouseArea;//房产面积
    private String assessCompany;//评估单位


    public MortgageGoodsInfo() {

    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsAddress() {
        return goodsAddress;
    }

    public void setGoodsAddress(String goodsAddress) {
        this.goodsAddress = goodsAddress;
    }

    public String getGoodsLendArea() {
        return goodsLendArea;
    }

    public void setGoodsLendArea(String goodsLendArea) {
        this.goodsLendArea = goodsLendArea;
    }

    public String getGoodsHouseArea() {
        return goodsHouseArea;
    }

    public void setGoodsHouseArea(String goodsHouseArea) {
        this.goodsHouseArea = goodsHouseArea;
    }

    public String getAssessCompany() {
        return assessCompany;
    }

    public void setAssessCompany(String assessCompany) {
        this.assessCompany = assessCompany;
    }

    public String getGoodsNo() {
        return goodsNo;
    }

    public void setGoodsNo(String goodsNo) {
        this.goodsNo = goodsNo;
    }

    public String getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(String goodsType) {
        this.goodsType = goodsType;
    }

    public String getGoodsOwnershipName() {
        return goodsOwnershipName;
    }

    public void setGoodsOwnershipName(String goodsOwnershipName) {
        this.goodsOwnershipName = goodsOwnershipName;
    }

    public String getGoodsOwnershipNo() {
        return goodsOwnershipNo;
    }

    public void setGoodsOwnershipNo(String goodsOwnershipNo) {
        this.goodsOwnershipNo = goodsOwnershipNo;
    }

    public String getGoodsValue() {
        return goodsValue;
    }

    public void setGoodsValue(String goodsValue) {
        this.goodsValue = goodsValue;
    }

    public String getIsLease() {
        return isLease;
    }

    public void setIsLease(String isLease) {
        this.isLease = isLease;
    }

    public String getLeaseBeginDate() {
        return leaseBeginDate;
    }

    public void setLeaseBeginDate(String leaseBeginDate) {
        this.leaseBeginDate = leaseBeginDate;
    }
}
