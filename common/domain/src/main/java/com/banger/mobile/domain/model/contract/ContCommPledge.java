package com.banger.mobile.domain.model.contract;

import com.banger.mobile.domain.model.loan.LnLoanInfo;
import com.banger.mobile.domain.model.loan.LnPledge;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * 质押合同
 */
public class ContCommPledge extends BaseLnContract implements Serializable {
    private static final long serialVersionUID = 1269170432131108007L;
    private String code ;//编号
    private String pledgorNames;//出质人
    private List<PledgorInfo> pledgorInfoList;
    private String cusName ;//借款人
    private String pledgeAmountCurrency="人民币" ;//质押担保金额.币种
    private String pledgeAmountWords ;//质押担保金额.大写
    private String pledgeAmountFigures ;//质押担保金额.小写
    private String pledgeValueCurrency="人民币" ;//质押价值.币种
    private String pledgeValueWords ;//质押价值.大写


    public ContCommPledge(){

    }
    public ContCommPledge(LnLoanInfo lnLoanInfo, List<LnPledge> lnPledgeList) {



        PledgorInfo pledgorInfo;

        StringBuffer sbf = new StringBuffer("");
        int i=1;
        SimpleDateFormat sf = new SimpleDateFormat("yyyy年MM月dd日");
        pledgorInfoList=new ArrayList<PledgorInfo>();

        if(!CollectionUtils.isEmpty(lnPledgeList)){
            for(LnPledge bean : lnPledgeList ){
                pledgorInfo = new PledgorInfo();

                    pledgorInfo.setPledgeNo(String.valueOf(i));

                if(StringUtils.isNotBlank(bean.getOwnerName())){
                    pledgorInfo.setPledgorName(bean.getOwnerName());
                    pledgorInfo.setPledgorCorporation(bean.getOwnerName());
                }
                if(StringUtils.isNotBlank(bean.getGoodsAmount())){
                    pledgorInfo.setGoodsAmount(bean.getGoodsAmount());
                }
                if(StringUtils.isNotBlank(bean.getGoodsValue())){
                    pledgorInfo.setGoodsValue(bean.getGoodsValue());
                }
                this.pledgorInfoList.add(pledgorInfo);

                sbf.append(bean.getOwnerName()).append("，");
//                this.setCusName(this.getCusName());
                i++;
            }
            this.setPledgorNames(sbf.substring(0, sbf.length() - 1).toString());

        }
        if(StringUtils.isNotBlank(lnLoanInfo.getLendContractNum())){
            this.setContractNo(lnLoanInfo.getLendContractNum());;//合同号
        }
//        this.setLoanUserName(lnLoanInfo.getCusName());
        this.setCusName(lnLoanInfo.getCusName());



    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPledgorNames() {
        return pledgorNames;
    }

    public void setPledgorNames(String pledgorNames) {
        this.pledgorNames = pledgorNames;
    }

    public List<PledgorInfo> getPledgorInfoList() {
        return pledgorInfoList;
    }

    public void setPledgorInfoList(List<PledgorInfo> pledgorInfoList) {
        this.pledgorInfoList = pledgorInfoList;
    }

    @Override
    public String getCusName() {
        return cusName;
    }

    @Override
    public void setCusName(String cusName) {
        this.cusName = cusName;
    }

    public String getPledgeAmountCurrency() {
        return pledgeAmountCurrency;
    }

    public void setPledgeAmountCurrency(String pledgeAmountCurrency) {
        this.pledgeAmountCurrency = pledgeAmountCurrency;
    }

    public String getPledgeAmountWords() {
        return pledgeAmountWords;
    }

    public void setPledgeAmountWords(String pledgeAmountWords) {
        this.pledgeAmountWords = pledgeAmountWords;
    }

    public String getPledgeAmountFigures() {
        return pledgeAmountFigures;
    }

    public void setPledgeAmountFigures(String pledgeAmountFigures) {
        this.pledgeAmountFigures = pledgeAmountFigures;
    }

    public String getPledgeValueCurrency() {
        return pledgeValueCurrency;
    }

    public void setPledgeValueCurrency(String pledgeValueCurrency) {
        this.pledgeValueCurrency = pledgeValueCurrency;
    }

    public String getPledgeValueWords() {
        return pledgeValueWords;
    }

    public void setPledgeValueWords(String pledgeValueWords) {
        this.pledgeValueWords = pledgeValueWords;
    }
}
