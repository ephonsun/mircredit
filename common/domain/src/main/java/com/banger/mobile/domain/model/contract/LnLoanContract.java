package com.banger.mobile.domain.model.contract;

import com.banger.mobile.domain.model.base.BaseObject;

import java.io.Serializable;
import java.util.Date;

/**
 * 合同主表
 */
public class LnLoanContract extends BaseObject implements Serializable {

    private static final long serialVersionUID = -6765090783793244153L;

    private Integer loanId;
    private String contractType; //合同类型 1普通 2最高额 3卡贷宝
    private String contCommMain;//普通主合同
    private Integer commMainCount;
    private String contTopMain;//最高额主合同
    private Integer topMainCount;
    private String contCardMain;//卡贷宝主合同
    private Integer cardMainCount;
    private String contCommGuaranty;//普通担保合同
    private Integer commGuarantyCount;
    private String contTopGuaranty;//最高额担保合同
    private Integer topGuarantyCount;
    private String contCardGuaranty;//卡贷宝担保合同
    private Integer cardGuarantyCount;
    private String contCommMortgage;//普通抵押合同
    private Integer commMortgageCount;
    private String contTopMortgage;//最高额抵押合同
    private Integer topMortgageCount;
    private String contCardMortgage;//卡贷宝抵押合同
    private Integer cardMortgageCount;
    private String contCommPledge;//普通质押合同
    private Integer commPledgeCount;
    private String contTopPledge;//最高额质押合同
    private Integer topPledgeCount;
    private String contCardPledge;//卡贷宝质押合同 (预留？)
    private Integer cardPledgeCount;
    private Integer createUser;
    private Date createDate;
    private Integer updateUser;
    private Date updateDate;
    private String iou;//借据

    public String getIou() {
        return iou;
    }

    public void setIou(String iou) {
        this.iou = iou;
    }

    public Integer getLoanId() {
        return loanId;
    }

    public void setLoanId(Integer loanId) {
        this.loanId = loanId;
    }

    public String getContractType() {
        return contractType;
    }

    public void setContractType(String contractType) {
        this.contractType = contractType;
    }

    public String getContCommMain() {
        return contCommMain;
    }

    public void setContCommMain(String contCommMain) {
        this.contCommMain = contCommMain;
    }

    public Integer getCommMainCount() {
        return commMainCount;
    }

    public void setCommMainCount(Integer commMainCount) {
        this.commMainCount = commMainCount;
    }

    public String getContTopMain() {
        return contTopMain;
    }

    public void setContTopMain(String contTopMain) {
        this.contTopMain = contTopMain;
    }

    public Integer getTopMainCount() {
        return topMainCount;
    }

    public void setTopMainCount(Integer topMainCount) {
        this.topMainCount = topMainCount;
    }

    public String getContCardMain() {
        return contCardMain;
    }

    public void setContCardMain(String contCardMain) {
        this.contCardMain = contCardMain;
    }

    public Integer getCardMainCount() {
        return cardMainCount;
    }

    public void setCardMainCount(Integer cardMainCount) {
        this.cardMainCount = cardMainCount;
    }

    public String getContCommGuaranty() {
        return contCommGuaranty;
    }

    public void setContCommGuaranty(String contCommGuaranty) {
        this.contCommGuaranty = contCommGuaranty;
    }

    public Integer getCommGuarantyCount() {
        return commGuarantyCount;
    }

    public void setCommGuarantyCount(Integer commGuarantyCount) {
        this.commGuarantyCount = commGuarantyCount;
    }

    public String getContTopGuaranty() {
        return contTopGuaranty;
    }

    public void setContTopGuaranty(String contTopGuaranty) {
        this.contTopGuaranty = contTopGuaranty;
    }

    public Integer getTopGuarantyCount() {
        return topGuarantyCount;
    }

    public void setTopGuarantyCount(Integer topGuarantyCount) {
        this.topGuarantyCount = topGuarantyCount;
    }

    public String getContCardGuaranty() {
        return contCardGuaranty;
    }

    public void setContCardGuaranty(String contCardGuaranty) {
        this.contCardGuaranty = contCardGuaranty;
    }

    public Integer getCardGuarantyCount() {
        return cardGuarantyCount;
    }

    public void setCardGuarantyCount(Integer cardGuarantyCount) {
        this.cardGuarantyCount = cardGuarantyCount;
    }

    public String getContCommMortgage() {
        return contCommMortgage;
    }

    public void setContCommMortgage(String contCommMortgage) {
        this.contCommMortgage = contCommMortgage;
    }

    public Integer getCommMortgageCount() {
        return commMortgageCount;
    }

    public void setCommMortgageCount(Integer commMortgageCount) {
        this.commMortgageCount = commMortgageCount;
    }

    public String getContTopMortgage() {
        return contTopMortgage;
    }

    public void setContTopMortgage(String contTopMortgage) {
        this.contTopMortgage = contTopMortgage;
    }

    public Integer getTopMortgageCount() {
        return topMortgageCount;
    }

    public void setTopMortgageCount(Integer topMortgageCount) {
        this.topMortgageCount = topMortgageCount;
    }

    public String getContCardMortgage() {
        return contCardMortgage;
    }

    public void setContCardMortgage(String contCardMortgage) {
        this.contCardMortgage = contCardMortgage;
    }

    public Integer getCardMortgageCount() {
        return cardMortgageCount;
    }

    public void setCardMortgageCount(Integer cardMortgageCount) {
        this.cardMortgageCount = cardMortgageCount;
    }

    public String getContCommPledge() {
        return contCommPledge;
    }

    public void setContCommPledge(String contCommPledge) {
        this.contCommPledge = contCommPledge;
    }

    public Integer getCommPledgeCount() {
        return commPledgeCount;
    }

    public void setCommPledgeCount(Integer commPledgeCount) {
        this.commPledgeCount = commPledgeCount;
    }

    public String getContTopPledge() {
        return contTopPledge;
    }

    public void setContTopPledge(String contTopPledge) {
        this.contTopPledge = contTopPledge;
    }

    public Integer getTopPledgeCount() {
        return topPledgeCount;
    }

    public void setTopPledgeCount(Integer topPledgeCount) {
        this.topPledgeCount = topPledgeCount;
    }

    public String getContCardPledge() {
        return contCardPledge;
    }

    public void setContCardPledge(String contCardPledge) {
        this.contCardPledge = contCardPledge;
    }

    public Integer getCardPledgeCount() {
        return cardPledgeCount;
    }

    public void setCardPledgeCount(Integer cardPledgeCount) {
        this.cardPledgeCount = cardPledgeCount;
    }

    public Integer getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Integer createUser) {
        this.createUser = createUser;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(Integer updateUser) {
        this.updateUser = updateUser;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}
