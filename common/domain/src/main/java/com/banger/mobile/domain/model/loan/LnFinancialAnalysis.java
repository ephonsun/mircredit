package com.banger.mobile.domain.model.loan;

import com.banger.mobile.domain.model.base.loan.BaseLnBalance;
import org.apache.commons.lang.StringUtils;

import java.math.BigDecimal;
import java.util.Date;

public class LnFinancialAnalysis  {

    //资产负债
    private BigDecimal totalAmount;//总资产
    private BigDecimal flowAmount;//流动资产
    private BigDecimal flowAmountRate;//流动资产比率
    private BigDecimal cash1;//银行及现金存款
    private BigDecimal account1;//应收账款
    private BigDecimal account2;//预付账款
    private BigDecimal fixed2;//存货
    private BigDecimal fixed1;//固定资产
    private BigDecimal cash2;//其他经营资产
    private BigDecimal cash3;//其他非经营资产
    private BigDecimal cash4;//其他资产

    private BigDecimal totalDebtAmount;//总负债
    private BigDecimal flowDebtAmount;//流动负债
    private BigDecimal account3;//应付账款
    private BigDecimal account4;//预收账款
    private BigDecimal debt1;//短期负债
    private BigDecimal debt2;//长期负债
    private BigDecimal cash5;//其他负债

    private BigDecimal offBalance;//表外资产
    private BigDecimal offDebt;//表外负债

    private BigDecimal debtRate;//资产负债率
    private BigDecimal rightsRealAmount;//实际权益


    //损益
    private Integer  monthNumber;//损益表月数
    private BigDecimal  profitLoss1;//营业收入
    private BigDecimal  varibleCost;//可变成本支出
    private BigDecimal  profitLoss2;//固定成本支出
    private BigDecimal  profitLoss3;//所得税
    private BigDecimal  profitLoss4;//其他收入
    private BigDecimal  profitLoss5;//其他支出


    //销售额
    private BigDecimal  totalSalesAmount;//总销售额
    private BigDecimal  totalGrossAmount;//总毛利润
    private BigDecimal  totalGrossRate;//毛利率
    private BigDecimal  totalNetAmount;//总净利润
    private BigDecimal  totalNetRate;//净利率
    private BigDecimal  monthlyAverageAmount;//月平均月可支
    private BigDecimal  netProfitRate;//净资产收益率

    //偿债能力
    private BigDecimal  cashRate;//现金比率
    private BigDecimal  flowRate;//流动比
    private BigDecimal  quickRae;//速动比
    private BigDecimal  debtProfitRate;//销贷比

    //周转率（参考）
    private BigDecimal  assetTurnoverRate;//总资产周转率
    private BigDecimal  assetTurnoverDays;//周转天数
    private BigDecimal  accountTurnoverRate;//应收账款周转率
    private BigDecimal  accountTurnoverDays;//周转天数
    private BigDecimal  goodsTurnoverRate;//存货周转率
    private BigDecimal  goodsTurnoverDays;//周转天数

    //调查建议
    private String  financialAnalysis;//调查建议
    private String  appLoanTypeId;//


    private BigDecimal  cash6;//投资性资产
    private BigDecimal  cash7;//对外债权
    private BigDecimal  cash8;//预付款
    private BigDecimal  debt3;//自用性负债
    private BigDecimal  debt4;//投资性负债
    private BigDecimal  debt5;//消费性负债
    private BigDecimal  debt6;//其他负债
    private BigDecimal  profitLoss6;//家庭收入
    private BigDecimal  profitLoss7;//固定支出
    private BigDecimal  profitLoss8;//个人所得税

    private BigDecimal  totalIncomeAmount;//总收入

    public String getAppLoanTypeId() {
        return appLoanTypeId;
    }

    public void setAppLoanTypeId(String appLoanTypeId) {
        this.appLoanTypeId = appLoanTypeId;
    }



    public BigDecimal getTotalAmount() {

        if(null==getCash1()){
            setCash1(new BigDecimal(0));
        }
        if(null==getAccount1()){
            setAccount1(new BigDecimal(0));
        }
        if(null==getAccount2()){
            setAccount2(new BigDecimal(0));
        }
        if(null==getFixed2()){
            setFixed2(new BigDecimal(0));
        }
        if(null==getFixed1()){
            setFixed1(new BigDecimal(0));
        }
        if(null==getCash2()){
            setCash2(new BigDecimal(0));
        }
        if(null==getCash3()){
            setCash3(new BigDecimal(0));
        }
        if(null==getCash4()){
            setCash4(new BigDecimal(0));
        }
        if(null==getCash6()){
            setCash6(new BigDecimal(0));
        }
        if(null==getCash7()){
            setCash7(new BigDecimal(0));
        }
        if(null==getCash8()){
            setCash8(new BigDecimal(0));
        }

        setTotalAmount(getCash1().add(getAccount1()).add(getAccount2()).add(getFixed2()).add(getFixed1())
                .add(getCash2()).add(getCash3()).add(getCash4()).add(getCash6()).add(getCash7()).add(getCash8()));

        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getFlowAmount() {
        if(null==getCash1()){
            setCash1(new BigDecimal(0));
        }
        if(null==getAccount1()){
            setAccount1(new BigDecimal(0));
        }
        if(null==getAccount2()){
            setAccount2(new BigDecimal(0));
        }
        if(null==getFixed2()){
            setFixed2(new BigDecimal(0));
        }
        setFlowAmount(getCash1().add(getAccount1()).add(getAccount2()).add(getFixed2()));

        return flowAmount;
    }

    public void setFlowAmount(BigDecimal flowAmount) {
        this.flowAmount = flowAmount;
    }

    public BigDecimal getFlowAmountRate() {

        if(null==getFlowAmount()){
            setFlowAmount(new BigDecimal(0));
        }
        if(null==getTotalAmount()){
            setTotalAmount(new BigDecimal(0));
        }

        if(getTotalAmount().compareTo(new BigDecimal(0))==0){
            setFlowAmountRate(new BigDecimal(0));
        }else{
            setFlowAmountRate(getFlowAmount().multiply(new BigDecimal(100)).divide(getTotalAmount(), 2, BigDecimal.ROUND_HALF_UP));
        }

        return flowAmountRate;
    }

    public void setFlowAmountRate(BigDecimal flowAmountRate) {
        this.flowAmountRate = flowAmountRate;
    }

    public BigDecimal getCash1() {
        return cash1;
    }

    public void setCash1(BigDecimal cash1) {
        this.cash1 = cash1;
    }

    public BigDecimal getAccount1() {
        return account1;
    }

    public void setAccount1(BigDecimal account1) {
        this.account1 = account1;
    }

    public BigDecimal getAccount2() {
        return account2;
    }

    public void setAccount2(BigDecimal account2) {
        this.account2 = account2;
    }

    public BigDecimal getFixed2() {
        return fixed2;
    }

    public void setFixed2(BigDecimal fixed2) {
        this.fixed2 = fixed2;
    }

    public BigDecimal getFixed1() {
        return fixed1;
    }

    public void setFixed1(BigDecimal fixed1) {
        this.fixed1 = fixed1;
    }

    public BigDecimal getCash2() {
        return cash2;
    }

    public void setCash2(BigDecimal cash2) {
        this.cash2 = cash2;
    }

    public BigDecimal getCash3() {
        return cash3;
    }

    public void setCash3(BigDecimal cash3) {
        this.cash3 = cash3;
    }

    public BigDecimal getCash4() {
        return cash4;
    }

    public void setCash4(BigDecimal cash4) {
        this.cash4 = cash4;
    }

    public BigDecimal getTotalDebtAmount() {

        if(null==getAccount3()){
            setAccount3(new BigDecimal(0));
        }
        if(null==getAccount4()){
            setAccount4(new BigDecimal(0));
        }
        if(null==getDebt1()){
            setDebt1(new BigDecimal(0));
        }
        if(null==getDebt2()){
            setDebt2(new BigDecimal(0));
        }
        if(null==getCash5()){
            setCash5(new BigDecimal(0));
        }
        if(null==getDebt3()){
            setDebt3(new BigDecimal(0));
        }
        if(null==getDebt4()){
            setDebt4(new BigDecimal(0));
        }
        if(null==getDebt5()) {
            setDebt5(new BigDecimal(0));
        }
        if (null == getDebt6()) {
            setDebt6(new BigDecimal(0));
        }

        setTotalDebtAmount(getAccount3().add(getAccount4()).add(getDebt1()).add(getDebt2()).add(getCash5()).add(getDebt3())
                .add(getDebt4()).add(getDebt5()).add(getDebt6()));

        return totalDebtAmount;

    }

    public void setTotalDebtAmount(BigDecimal totalDebtAmount) {
        this.totalDebtAmount = totalDebtAmount;
    }

    public BigDecimal getFlowDebtAmount() {
        if(null==getAccount3()){
            setAccount3(new BigDecimal(0));
        }
        if(null==getAccount4()){
            setAccount4(new BigDecimal(0));
        }
        if(null==getDebt1()){
            setDebt1(new BigDecimal(0));
        }

        setFlowDebtAmount(getAccount3().add(getAccount4()).add(getDebt1()));
        return flowDebtAmount;
    }

    public void setFlowDebtAmount(BigDecimal flowDebtAmount) {
        this.flowDebtAmount = flowDebtAmount;
    }

    public BigDecimal getAccount3() {
        return account3;
    }

    public void setAccount3(BigDecimal account3) {
        this.account3 = account3;
    }

    public BigDecimal getAccount4() {
        return account4;
    }

    public void setAccount4(BigDecimal account4) {
        this.account4 = account4;
    }

    public BigDecimal getDebt1() {
        return debt1;
    }

    public void setDebt1(BigDecimal debt1) {
        this.debt1 = debt1;
    }

    public BigDecimal getDebt2() {
        return debt2;
    }

    public void setDebt2(BigDecimal debt2) {
        this.debt2 = debt2;
    }

    public BigDecimal getCash5() {
        return cash5;
    }

    public void setCash5(BigDecimal cash5) {
        this.cash5 = cash5;
    }

    public BigDecimal getOffBalance() {
        return offBalance;
    }

    public void setOffBalance(BigDecimal offBalance) {
        this.offBalance = offBalance;
    }

    public BigDecimal getOffDebt() {
        return offDebt;
    }

    public void setOffDebt(BigDecimal offDebt) {
        this.offDebt = offDebt;
    }

    public BigDecimal getDebtRate() {

        if(null==getTotalAmount()){
            setTotalAmount(new BigDecimal(0));
        }
        if(null==getTotalDebtAmount()){
            setTotalDebtAmount(new BigDecimal(0));
        }

        if(getTotalDebtAmount().compareTo(new BigDecimal(0))!=0){
            setDebtRate(getTotalAmount().multiply(new BigDecimal(100)).divide(getTotalDebtAmount(), 2, BigDecimal.ROUND_HALF_UP));
        }else{
            setDebtRate(new BigDecimal(0));
        }

        return debtRate;
    }

    public void setDebtRate(BigDecimal debtRate) {
        this.debtRate = debtRate;
    }

    public BigDecimal getRightsRealAmount() {

        if(null==getTotalAmount()){
            setTotalAmount(new BigDecimal(0));
        }
        if(null==getTotalDebtAmount()){
            setTotalDebtAmount(new BigDecimal(0));
        }

        setRightsRealAmount(getTotalAmount().subtract(getTotalDebtAmount()));

        return rightsRealAmount;
    }

    public void setRightsRealAmount(BigDecimal rightsRealAmount) {
        this.rightsRealAmount = rightsRealAmount;
    }

    public Integer getMonthNumber() {
        return monthNumber;
    }

    public void setMonthNumber(Integer monthNumber) {
        this.monthNumber = monthNumber;
    }

    public BigDecimal getProfitLoss1() {
        return profitLoss1;
    }

    public void setProfitLoss1(BigDecimal profitLoss1) {
        this.profitLoss1 = profitLoss1;
    }

    public BigDecimal getVaribleCost() {

        if(null==getProfitLoss1()){
            setProfitLoss1(new BigDecimal(0));
        }
        if(null==getTotalGrossRate()){
            setTotalGrossRate(new BigDecimal(0));
        }

        setVaribleCost(getProfitLoss1().multiply(new BigDecimal(1).subtract(getTotalGrossRate())));

        return varibleCost;
    }

    public void setVaribleCost(BigDecimal varibleCost) {
        this.varibleCost = varibleCost;
    }

    public BigDecimal getProfitLoss2() {
        return profitLoss2;
    }

    public void setProfitLoss2(BigDecimal profitLoss2) {
        this.profitLoss2 = profitLoss2;
    }

    public BigDecimal getProfitLoss3() {
        return profitLoss3;
    }

    public void setProfitLoss3(BigDecimal profitLoss3) {
        this.profitLoss3 = profitLoss3;
    }

    public BigDecimal getProfitLoss4() {
        return profitLoss4;
    }

    public void setProfitLoss4(BigDecimal profitLoss4) {
        this.profitLoss4 = profitLoss4;
    }

    public BigDecimal getProfitLoss5() {
        return profitLoss5;
    }

    public void setProfitLoss5(BigDecimal profitLoss5) {
        this.profitLoss5 = profitLoss5;
    }

    public BigDecimal getTotalSalesAmount() {

        if(null==getProfitLoss1()){
            setProfitLoss1(new BigDecimal(0));
        }
        setTotalSalesAmount(getProfitLoss1());

        return totalSalesAmount;
    }

    public void setTotalSalesAmount(BigDecimal totalSalesAmount) {
        this.totalSalesAmount = totalSalesAmount;
    }

    public BigDecimal getTotalGrossAmount() {

        if(null==getTotalGrossRate()){
            setTotalGrossRate(new BigDecimal(0));
        }
        if(null==getTotalSalesAmount()){
            setTotalSalesAmount(new BigDecimal(0));
        }

        setTotalGrossAmount(getTotalGrossRate().multiply(getTotalSalesAmount()));;

        return totalGrossAmount;
    }

    public void setTotalGrossAmount(BigDecimal totalGrossAmount) {
        this.totalGrossAmount = totalGrossAmount;
    }

    public BigDecimal getTotalGrossRate() {
        return totalGrossRate;
    }

    public void setTotalGrossRate(BigDecimal totalGrossRate) {
        this.totalGrossRate = totalGrossRate;
    }

    public BigDecimal getTotalNetAmount() {

        if(null==getTotalGrossAmount()){
            setTotalGrossAmount(new BigDecimal(0));
        }
        if(null==getProfitLoss2()){
            setProfitLoss2(new BigDecimal(0));
        }
        if(null==getProfitLoss3()){
            setProfitLoss3(new BigDecimal(0));
        }
        if(null==getProfitLoss4()){
            setProfitLoss4(new BigDecimal(0));
        }
        if(null==getProfitLoss5()){
            setProfitLoss5(new BigDecimal(0));
        }

        setTotalNetAmount(getTotalGrossAmount().add(getProfitLoss4()).subtract(getProfitLoss2()).subtract(getProfitLoss3()).subtract(getProfitLoss5()));;

        return totalNetAmount;
    }

    public void setTotalNetAmount(BigDecimal totalNetAmount) {
        this.totalNetAmount = totalNetAmount;
    }

    public BigDecimal getTotalNetRate() {

        if(null==getTotalNetAmount()){
            setTotalNetAmount(new BigDecimal(0));
        }
        if(null==getTotalSalesAmount()){
            setTotalSalesAmount(new BigDecimal(0));
        }

        if(getTotalSalesAmount().compareTo(new BigDecimal(0))==0){
            setTotalNetRate(new BigDecimal(0));
        }else{
            setTotalNetRate(getTotalNetAmount().multiply(new BigDecimal(100)).divide(getTotalSalesAmount(), 2, BigDecimal.ROUND_HALF_UP));
        }

        return totalNetRate;
    }

    public void setTotalNetRate(BigDecimal totalNetRate) {
        this.totalNetRate = totalNetRate;
    }

    public BigDecimal getMonthlyAverageAmount() {

        setMonthlyAverageAmount(new BigDecimal(0));

        if(StringUtils.isNotBlank(getAppLoanTypeId())){
            if(getAppLoanTypeId().equals("1")){
                if(null==getTotalNetAmount()){
                    setTotalNetAmount(new BigDecimal(0));
                }
                if(null==getMonthNumber()){
                    setMonthNumber(0);
                }

                if(getMonthNumber()!=0){
                    setMonthlyAverageAmount(getTotalNetAmount().divide(new BigDecimal(getMonthNumber()), 2, BigDecimal.ROUND_HALF_UP));
                }
            }else if(getAppLoanTypeId().equals("2")){
                if(null==getProfitLoss6()){
                    setProfitLoss6(new BigDecimal(0));
                }
                if(null==getProfitLoss7()){
                    setProfitLoss7(new BigDecimal(0));
                }
                if(null==getProfitLoss8()){
                    setProfitLoss8(new BigDecimal(0));
                }
                if(null==getProfitLoss4()){
                    setProfitLoss4(new BigDecimal(0));
                }
                if(null==getProfitLoss5()){
                    setProfitLoss5(new BigDecimal(0));
                }

                if(null==getMonthNumber()){
                    setMonthNumber(0);
                }

                if(getMonthNumber()!=0){
                    setMonthlyAverageAmount((getProfitLoss6().add(getProfitLoss4()).subtract(getProfitLoss7()).subtract(getProfitLoss8()).subtract(getProfitLoss5())).divide(new BigDecimal(getMonthNumber()), 2, BigDecimal.ROUND_HALF_UP));
                }

            }
        }

        return monthlyAverageAmount;

    }

    public void setMonthlyAverageAmount(BigDecimal monthlyAverageAmount) {
        this.monthlyAverageAmount = monthlyAverageAmount;
    }

    public BigDecimal getNetProfitRate() {

        if(null==getTotalNetAmount()){
            setTotalNetAmount(new BigDecimal(0));
        }

        if(null==getRightsRealAmount()){
            setRightsRealAmount(new BigDecimal(0));
        }

        if(getRightsRealAmount().compareTo(new BigDecimal(0))==0){
            setNetProfitRate(new BigDecimal(0));
        }else{
            setNetProfitRate(getTotalNetAmount().multiply(new BigDecimal(100)).divide(getRightsRealAmount(), 2, BigDecimal.ROUND_HALF_UP));
        }

        return netProfitRate;

    }

    public void setNetProfitRate(BigDecimal netProfitRate) {
        this.netProfitRate = netProfitRate;
    }

    public BigDecimal getCashRate() {

//        cash1/flowDebtAmount
        if(null==getCash1()){
            setCash1(new BigDecimal(0));
        }

        setCashRate(new BigDecimal(0));

        if(StringUtils.isNotBlank(getAppLoanTypeId())){
            if(getAppLoanTypeId().equals("1")){
                if(null==getFlowDebtAmount()){
                    setFlowDebtAmount(new BigDecimal(0));
                }

                if(getFlowDebtAmount().compareTo(new BigDecimal(0))!=0){
                    setCashRate(getCash1().multiply(new BigDecimal(100)).divide(getFlowDebtAmount(), 2, BigDecimal.ROUND_HALF_UP));
                }
            }else if(getAppLoanTypeId().equals("2")){
                if(null==getTotalDebtAmount()){
                    setTotalDebtAmount(new BigDecimal(0));
                }

                if(getTotalDebtAmount().compareTo(new BigDecimal(0))!=0){
                    setCashRate(getCash1().multiply(new BigDecimal(100)).divide(getTotalDebtAmount(), 2, BigDecimal.ROUND_HALF_UP));
                }

            }

        }


        return cashRate;
    }

    public void setCashRate(BigDecimal cashRate) {
        this.cashRate = cashRate;
    }

    public BigDecimal getFlowRate() {

        if(null==getFlowAmount()){
            setFlowAmount(new BigDecimal(0));
        }
        if(null==getFlowDebtAmount()){
            setFlowDebtAmount(new BigDecimal(0));
        }

        if(getFlowDebtAmount().compareTo(new BigDecimal(0))==0){
            setFlowRate(new BigDecimal(0));
        }else{
            setFlowRate(getFlowAmount().multiply(new BigDecimal(100)).divide(getFlowDebtAmount(), 2, BigDecimal.ROUND_HALF_UP));
        }
        return flowRate;
    }

    public void setFlowRate(BigDecimal flowRate) {
        this.flowRate = flowRate;
    }

    public BigDecimal getQuickRae() {
        if(null==getFlowAmount()){
            setFlowAmount(new BigDecimal(0));
        }
        if(null==getFixed2()){
            setFixed2(new BigDecimal(0));
        }
        if(null==getFlowDebtAmount()){
            setFlowDebtAmount(new BigDecimal(0));
        }

        if(getFlowDebtAmount().compareTo(new BigDecimal(0))==0){
            setQuickRae(new BigDecimal(0));
        }else{
            setQuickRae(getFlowAmount().multiply(new BigDecimal(100)).subtract(getFixed2()).divide(getFlowDebtAmount(), 2, BigDecimal.ROUND_HALF_UP));
        }

        return quickRae;
    }

    public void setQuickRae(BigDecimal quickRae) {
        this.quickRae = quickRae;
    }

    public BigDecimal getDebtProfitRate() {
        return debtProfitRate;
    }

    public void setDebtProfitRate(BigDecimal debtProfitRate) {
        this.debtProfitRate = debtProfitRate;
    }

    public BigDecimal getAssetTurnoverRate() {

        if(null==getTotalSalesAmount()){
            setTotalSalesAmount(new BigDecimal(0));
        }
        if(null==getTotalAmount()){
            setTotalAmount(new BigDecimal(0));
        }

        if(getTotalAmount().compareTo(new BigDecimal(0))==0){
            setAssetTurnoverRate(new BigDecimal(0));
        }else{
            setAssetTurnoverRate(getTotalSalesAmount().multiply(new BigDecimal(100)).divide(getTotalAmount(), 2, BigDecimal.ROUND_HALF_UP));
        }

        return assetTurnoverRate;
    }

    public void setAssetTurnoverRate(BigDecimal assetTurnoverRate) {
        this.assetTurnoverRate = assetTurnoverRate;
    }

    public BigDecimal getAssetTurnoverDays() {

        if(null==getAssetTurnoverRate()){
            setAssetTurnoverRate(new BigDecimal(0));
        }

        if(getAssetTurnoverRate().compareTo(new BigDecimal(0))==0){
            setAssetTurnoverDays(new BigDecimal(0));
        }else{
            setAssetTurnoverDays(new BigDecimal(360).divide(getAssetTurnoverRate(), 1, BigDecimal.ROUND_HALF_UP));
        }
        return assetTurnoverDays;

    }

    public void setAssetTurnoverDays(BigDecimal assetTurnoverDays) {
        this.assetTurnoverDays = assetTurnoverDays;
    }

    public BigDecimal getAccountTurnoverRate() {

//        totalSalesAmount/account1
        if(null==getTotalSalesAmount()){
            setTotalSalesAmount(new BigDecimal(0));
        }
        if(null==getAccount1()){
            setAccount1(new BigDecimal(0));
        }

        if(getAccount1().compareTo(new BigDecimal(0))==0){
            setAccountTurnoverRate(new BigDecimal(0));
        }else{
            setAccountTurnoverRate(getTotalSalesAmount().multiply(new BigDecimal(100)).divide(getAccount1(), 2, BigDecimal.ROUND_HALF_UP));
        }

        return accountTurnoverRate;
    }

    public void setAccountTurnoverRate(BigDecimal accountTurnoverRate) {
        this.accountTurnoverRate = accountTurnoverRate;
    }

    public BigDecimal getAccountTurnoverDays() {

        if(null==getAccountTurnoverRate()){
            setAccountTurnoverRate(new BigDecimal(0));
        }

        if(getAccountTurnoverRate().compareTo(new BigDecimal(0))==0){
            setAccountTurnoverDays(new BigDecimal(0));
        }else{
            setAccountTurnoverDays(new BigDecimal(360).divide(getAccountTurnoverRate(), 1, BigDecimal.ROUND_HALF_UP));
        }
        return accountTurnoverDays;
    }

    public void setAccountTurnoverDays(BigDecimal accountTurnoverDays) {
        this.accountTurnoverDays = accountTurnoverDays;
    }

    public BigDecimal getGoodsTurnoverRate() {

        //        varibleCost/fixed2
        if(null==getVaribleCost()){
            setVaribleCost(new BigDecimal(0));
        }
        if(null==getFixed2()){
            setFixed2(new BigDecimal(0));
        }

        if(getFixed2().compareTo(new BigDecimal(0))==0){
            setGoodsTurnoverRate(new BigDecimal(0));
        }else{
            setGoodsTurnoverRate(getVaribleCost().multiply(new BigDecimal(100)).divide(getFixed2(), 2, BigDecimal.ROUND_HALF_UP));
        }

        return goodsTurnoverRate;
    }

    public void setGoodsTurnoverRate(BigDecimal goodsTurnoverRate) {
        this.goodsTurnoverRate = goodsTurnoverRate;
    }

    public BigDecimal getGoodsTurnoverDays() {

        if(null==getGoodsTurnoverRate()){
            setGoodsTurnoverRate(new BigDecimal(0));
        }

        if(getGoodsTurnoverRate().compareTo(new BigDecimal(0))==0){
            setGoodsTurnoverDays(new BigDecimal(0));
        }else{
            setGoodsTurnoverDays(new BigDecimal(360).divide(getGoodsTurnoverRate(), 1, BigDecimal.ROUND_HALF_UP));
        }

        return goodsTurnoverDays;
    }

    public void setGoodsTurnoverDays(BigDecimal goodsTurnoverDays) {
        this.goodsTurnoverDays = goodsTurnoverDays;
    }

    public String getFinancialAnalysis() {
        return financialAnalysis;
    }

    public void setFinancialAnalysis(String financialAnalysis) {
        this.financialAnalysis = financialAnalysis;
    }

    public BigDecimal getCash6() {
        return cash6;
    }

    public void setCash6(BigDecimal cash6) {
        this.cash6 = cash6;
    }

    public BigDecimal getCash7() {
        return cash7;
    }

    public void setCash7(BigDecimal cash7) {
        this.cash7 = cash7;
    }

    public BigDecimal getCash8() {
        return cash8;
    }

    public void setCash8(BigDecimal cash8) {
        this.cash8 = cash8;
    }

    public BigDecimal getDebt3() {
        return debt3;
    }

    public void setDebt3(BigDecimal debt3) {
        this.debt3 = debt3;
    }

    public BigDecimal getDebt4() {
        return debt4;
    }

    public void setDebt4(BigDecimal debt4) {
        this.debt4 = debt4;
    }

    public BigDecimal getDebt5() {
        return debt5;
    }

    public void setDebt5(BigDecimal debt5) {
        this.debt5 = debt5;
    }

    public BigDecimal getDebt6() {
        return debt6;
    }

    public void setDebt6(BigDecimal debt6) {
        this.debt6 = debt6;
    }

    public BigDecimal getProfitLoss6() {
        return profitLoss6;
    }

    public void setProfitLoss6(BigDecimal profitLoss6) {
        this.profitLoss6 = profitLoss6;
    }

    public BigDecimal getProfitLoss7() {
        return profitLoss7;
    }

    public void setProfitLoss7(BigDecimal profitLoss7) {
        this.profitLoss7 = profitLoss7;
    }

    public BigDecimal getProfitLoss8() {
        return profitLoss8;
    }

    public void setProfitLoss8(BigDecimal profitLoss8) {
        this.profitLoss8 = profitLoss8;
    }

    public BigDecimal getTotalIncomeAmount() {
        if(null==getProfitLoss6()){
            setProfitLoss6(new BigDecimal(0));
        }

        setTotalIncomeAmount(getProfitLoss6());

        return totalIncomeAmount;
    }

    public void setTotalIncomeAmount(BigDecimal totalIncomeAmount) {
        this.totalIncomeAmount = totalIncomeAmount;
    }
}