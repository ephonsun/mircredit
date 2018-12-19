package com.banger.mobile.domain.model.base.loan;

import com.banger.mobile.domain.model.base.BaseObject;

import java.io.Serializable;
import java.util.Date;

/**
 * 抵制价物
 * @author White Wolf
 *
 */
public class BaseLnPledge extends BaseObject implements Serializable {

	private Integer pledgeId; //抵质押物编号
	private Integer loanId; //贷款编号
	private String goods;//物品
	private String goodsValue;//抵押物估价
	private String pledgeRate;//抵押率
	private String ownerName;//所有者
	private String titleCertificate;//产权证
	private Date createDate;//创建时间
	private Date updateDate;//修改时间

	private String goodsAmount;//抵押（元）
	private String goodsRemark;//抵押物描述


	public String getPledgeRate() {
		return pledgeRate;
	}

	public void setPledgeRate(String pledgeRate) {
		this.pledgeRate = pledgeRate;
	}

	public String getGoodsAmount() {
		return goodsAmount;
	}

	public void setGoodsAmount(String goodsAmount) {
		this.goodsAmount = goodsAmount;
	}

	public String getGoodsRemark() {
		return goodsRemark;
	}

	public void setGoodsRemark(String goodsRemark) {
		this.goodsRemark = goodsRemark;
	}

	public BaseLnPledge() {
	}

	public BaseLnPledge(Integer pledgeId) {
		this.pledgeId = pledgeId;
	}

	public Integer getPledgeId() {
		return pledgeId;
	}
	public void setPledgeId(Integer pledgeId) {
		this.pledgeId = pledgeId;
	}
	public Integer getLoanId() {
		return loanId;
	}

	public void setLoanId(Integer loanId) {
		this.loanId = loanId;
	}

	public String getGoods() {
		return goods;
	}
	public void setGoods(String goods) {
		this.goods = goods;
	}
	public String getGoodsValue() {
		return goodsValue;
	}
	public void setGoodsValue(String goodsValue) {
		this.goodsValue = goodsValue;
	}
	public String getOwnerName() {
		return ownerName;
	}
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
	public String getTitleCertificate() {
		return titleCertificate;
	}
	public void setTitleCertificate(String titleCertificate) {
		this.titleCertificate = titleCertificate;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}




}
