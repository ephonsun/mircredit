/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :理财规划模版
 * Author     :cheny
 * Create Date:2012-7-16
 */
package com.banger.mobile.facade.impl.plnReportTemplate;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.dao.PlnReportTemplate.PlnReportTemplateDao;
import com.banger.mobile.dao.customer.CrmCustomerDao;
import com.banger.mobile.dao.plnPlanType.PlnPlanTypeDao;
import com.banger.mobile.dao.plnTemplateVar.PlnTemplateVarDao;
import com.banger.mobile.domain.model.PlnReportTemplate.PlnPlanType;
import com.banger.mobile.domain.model.PlnReportTemplate.PlnReportTemplate;
import com.banger.mobile.domain.model.PlnReportTemplate.PlnReportTemplateBean;
import com.banger.mobile.domain.model.PlnReportTemplate.PlnReportTemplateVar;
import com.banger.mobile.domain.model.PlnReportTemplate.PlnVarType;
import com.banger.mobile.domain.model.PlnReportTemplate.PlnVarTypeSub;
import com.banger.mobile.domain.model.customer.CrmCustomer;
import com.banger.mobile.domain.model.plnFastPlan.PlnFastPlan;
import com.banger.mobile.domain.model.user.SysUser;
import com.banger.mobile.facade.plnReportTemplate.PlnReportTemplateService;
import com.banger.mobile.util.MoneyUtil;
import com.banger.mobile.util.ReflectorUtil;
import com.banger.mobile.util.StringUtil;
import com.banger.mobile.util.TypeUtil;

/**
 * @author cheny
 * @version $Id: PlnReportTemplateServiceImpl.java,v 0.1 2012-7-16 上午11:12:19 cheny Exp $
 */
public class PlnReportTemplateServiceImpl implements PlnReportTemplateService{

    protected final Log   log = LogFactory.getLog(getClass());
    
    private PlnReportTemplateDao plnReportTemplateDao;   //理财规划模板dao
    
    private PlnPlanTypeDao plnPlanTypeDao;             //理财规划类型dao
    
    private PlnTemplateVarDao plnTemplateVarDao;       //模板变量Dao
    private CrmCustomerDao crmCustomerDao;

    public void setPlnReportTemplateDao(PlnReportTemplateDao plnReportTemplateDao) {
        this.plnReportTemplateDao = plnReportTemplateDao;
    }

    public void setCrmCustomerDao(CrmCustomerDao crmCustomerDao) {
        this.crmCustomerDao = crmCustomerDao;
    }

    public void setPlnPlanTypeDao(PlnPlanTypeDao plnPlanTypeDao) {
        this.plnPlanTypeDao = plnPlanTypeDao;
    }

    public void setPlnTemplateVarDao(PlnTemplateVarDao plnTemplateVarDao) {
        this.plnTemplateVarDao = plnTemplateVarDao;
    }

    /**
     * 理财规划模板分页
     */
    public PageUtil<PlnReportTemplateBean> getPlnReportTemplatePage(PlnReportTemplate plnReportTemplate, Page page){
        Map<String, Object> map = new HashMap<String, Object>();
        if(plnReportTemplate != null){
            if(plnReportTemplate.getTemplateNo()!=null && !plnReportTemplate.getTemplateNo().equals("")){
                map.put("templateNo", StringUtil.ReplaceSQLChar(plnReportTemplate.getTemplateNo()));
            }
            if(plnReportTemplate.getTemplateName()!=null && !plnReportTemplate.getTemplateName().equals("")){
                map.put("templateName", StringUtil.ReplaceSQLChar(plnReportTemplate.getTemplateName()));
            }
            if(plnReportTemplate.getIntervalTypeId()!=null && !plnReportTemplate.getIntervalTypeId().equals("")){
                map.put("intervalTypeId", plnReportTemplate.getIntervalTypeId());
            }
            if(plnReportTemplate.getIsActived()!=null && !plnReportTemplate.getIsActived().equals("")){
                map.put("isActived", plnReportTemplate.getIsActived());
            }
            if(plnReportTemplate.getPlanTypeId()!=null && !plnReportTemplate.getPlanTypeId().equals("")){
                map.put("planTypeId", plnReportTemplate.getPlanTypeId());
            }
        }
        return plnReportTemplateDao.getPlnReportTemplatePage(map, page);
    }
    /**
     * 查询所有规划模板类型
     */
    public List<PlnPlanType> getAllPlnPlanType(){
        return plnPlanTypeDao.getAllPlnPlanType();
    }
    /**
     * 根据Id查询规划模板类型
     */
    public PlnPlanType getPlnPlanTypeById(Integer id){
        return plnPlanTypeDao.getPlnPlanTypeById(id);
    }
    
    /**
     * 查询单个模板
     */
    public PlnReportTemplate getPlanTemplate(Map<String,Object> map){
        return plnReportTemplateDao.getPlanTemplate(map);
    }
    /**
     * 查询模板集合
     */
    public List<PlnReportTemplate> getPlanTemplateList(Map<String,Object> map){
        return plnReportTemplateDao.getPlanTemplateList(map);
    }
    /**
     * 新增理财规划模板
     */
    public Integer insertPlanTemplate(PlnReportTemplate plnReportTemplate){
        return plnReportTemplateDao.insertTemplate(plnReportTemplate);
    }
    /**
     * 编辑模板
     */
    public void updatePlanTemplate(PlnReportTemplate plnReportTemplate){
        plnReportTemplateDao.updatePlanTemplate(plnReportTemplate);
    }
    /**
     * 删除模板
     */
    public void delPlanTemplateById(Integer tempId){
        plnReportTemplateDao.delPlanTemplateById(tempId);
    }
    /**
     * 启用停用模板
     */
    public void activePlanTemplate(Map<String,Object> map){
        plnReportTemplateDao.activePlanTemplate(map);
    }
    /**
     * 查询所有的变量
     */
    public List<PlnReportTemplateVar> getAllVarList(){
        return plnTemplateVarDao.getAllVarList();
    }
    /**
     * 查询所有的二级分类
     */
    public List<PlnVarTypeSub> getTwoSubList(){
        return plnTemplateVarDao.getTwoSubList();
    }
    /**
     * 查询所有的一级分类
     */
    public List<PlnVarType> getOneSubList(){
        return plnTemplateVarDao.getOneSubList();
    }
    
    /**
     * 编辑模板验证
     */
    public PlnReportTemplate updatePlanTemplateValidate(Map<String,Object> map){
        return plnReportTemplateDao.updatePlanTemplateValidate(map);
    }
    
    
    /**
     * 模板内容解析
     */
    public String replaceContent(String content,SysUser user,PlnFastPlan fastPlan,String valTypeName,List<PlnReportTemplateVar> templateVarList){
//        StringBuffer sBuf = new StringBuffer(); // 字符流
        DecimalFormat format = new DecimalFormat("#.00");
        String label = null;
        String property = null;
        String repl = null;
        String regx = "\\[\\[[\u4e00-\u9fa5]+([/][\u4e00-\u9fa5]+)?\\]\\]";
        if(content==null||"".equals(content))   
            return "";    
        Integer customerId=fastPlan.getCustomerId();
        CrmCustomer customer=null;
        if(customerId!=null && customerId>0){//内部客户
             customer=crmCustomerDao.getCrmCustomerById(customerId);
        }
        java.util.regex.Pattern p = Pattern.compile(regx);
        java.util.regex.Matcher m = p.matcher(content);   
        while(m.find())   
        {   
            label = m.group();//得到标签
            for(int i=0;i<templateVarList.size();i++){
                if(label.equals(templateVarList.get(i).getVarTag())){
                    if(templateVarList.get(i).getSubId().equals(1)){
                        property = templateVarList.get(i).getVarExpression();
                        if(property.equals("age")){
                            if(customerId!=null && customerId>0) 
                                repl = (Integer)ReflectorUtil.getPropertyValue(customer, property)+"";
                                if(repl.equals(null+"")) repl = (Integer)ReflectorUtil.getPropertyValue(fastPlan, property)+"";
                            else repl = (Integer)ReflectorUtil.getPropertyValue(fastPlan, property)+"";
                        }else if(property.equals("idNo")&&customerId!=null && customerId>0){//内部客户,证件号码取客户管理
                            repl = (String)ReflectorUtil.getPropertyValue(customer, "idCard");
                        }else repl = (String)ReflectorUtil.getPropertyValue(fastPlan, property);//客户管理
                    }
                    if(templateVarList.get(i).getSubId().equals(2)){
                        property = templateVarList.get(i).getVarExpression();
                        repl = (String)ReflectorUtil.getPropertyValue(user, property);//用户管理
                    }
                    if(templateVarList.get(i).getVarTypeId().equals(2)){
                        property = templateVarList.get(i).getVarExpression();
                        if(property.equals("intervalTypeId")){//投资偏好
                            repl = valTypeName;
                        }else if(property.equals("houseCreditRate")){//购房贷款利率
                            repl = format.format(fastPlan.getHouseCreditRate()) +"%";
                        }else if(property.equals("inflation")){//预计通贷膨胀率
                            repl = format.format(fastPlan.getInflation())+"%";
                        }else if(property.equals("investIncomingRate")){//期望投资收益率
                            repl = format.format(fastPlan.getInvestIncomingRate())+"%";
                        }else if(property.equals("planHouseDownPayMent")){//计划购房首付比例
                            if(fastPlan.getIsDoHousePlan().equals(1)){
                                if(fastPlan.getPlanHouseDownPayMent() != 0.0) repl = format.format(fastPlan.getPlanHouseDownPayMent())+"%";
                            }else {
                                repl = "0%";
                            }
                        }else{
                            Double re =(Double) TypeUtil.changeType(ReflectorUtil.getPropertyValue(fastPlan, property),Double.class);//快速规划
                            repl = format.format(re.doubleValue());
                        }
                    }
                    if(templateVarList.get(i).getVarTypeId().equals(3)){
                        repl = this.getVarFormula(label,fastPlan);  //计算公式
                    }
                    content = content.replace(label, checkResult(repl));//替换
                    continue;
                }
            }   
        }   
        return content;
    }
    
    /**
     * 验证结果
     * @param repl
     * @return
     */
    public String checkResult(String repl){
        if(null==repl||repl.equals("")||repl.equals(null+"")){
            return " ";
        }else{
            if(repl.startsWith(".")){
                if(repl.equals(".00")) return "0";
                else if(repl.endsWith("%")) return "0%";
                else return "0"+repl;
            }else if(repl.endsWith(".00")){
                return repl.substring(0,repl.length()-3);
            }else return repl;
        }
    }
    
    
    
    /**
     * 格式化输出保留两位小数
     */
    public String formatDouble(String str){
        if(str.contains(".")){
            if(str.contains("%")){
                str = str.substring(0,str.length()-1);
                return MoneyUtil.round(str,2)+"%";
            }else return MoneyUtil.round(str,2)+"";
        }else{
            return str;
        }
    }
    
    /**
     * 变量 计算公式
     * @param var
     * @return
     */
    public String getVarFormula(String label,PlnFastPlan plan){
        DecimalFormat format = new DecimalFormat("#.00");
        String target = null ;
        Double totalAmount = null;//总资产 
        totalAmount = plan.getAvailableInvestMoney() + plan.getHouseValue();
        Double salaryYear =null; //估计年收入
        salaryYear = (plan.getMonthlyFamilyExpend()+plan.getMonthlyDeposit()+plan.getHouseMonthlyRepayMent()+plan.getMonthlyFamilyOutLay())*12;
        Double  income= null;//年储蓄额
        income = plan.getMonthlyDeposit()*12;
        Integer retireyear = null;//距离退休年数
        retireyear = plan.getRetireAge()-plan.getAge(); 
        Double rtotal = null;//退休养老总费用
        rtotal = plan.getRetireMonthlyCost()*Math.pow((1+plan.getInflation()*0.01),retireyear)*12*plan.getRetireYearLimit();
        Double ttotal = null;//退休时总储备
        if(plan.getInvestIncomingRate() == 0.0){
            ttotal = -1.11;
        }else{
            ttotal=plan.getAvailableInvestMoney()*Math.pow((1+plan.getInvestIncomingRate()*0.01), retireyear)+
                   plan.getMonthlyDeposit()*12*(Math.pow((1+plan.getInvestIncomingRate()*0.01), retireyear)-1)/(plan.getInvestIncomingRate()*0.01)+
                   plan.getHouseMonthlyRepayMent()*12*(Math.pow((1+plan.getInvestIncomingRate()*0.01), retireyear-plan.getHouseCreditYearLimit())-1)/(plan.getInvestIncomingRate()*0.01);
        }
        
        if(label.equals("[[总资产]]")) target = format.format(totalAmount.doubleValue());
        if(label.equals("[[净资产]]")){
            target =format.format(plan.getAvailableInvestMoney() + plan.getHouseValue()- plan.getHouseCreditRemaining());
        }
        if(label.equals("[[估计年收入]]")) target = format.format(salaryYear);
        if(label.equals("[[年还款额]]")) target= format.format(plan.getHouseMonthlyRepayMent()*12);
        if(label.equals("[[年总支出]]")) target=format.format((plan.getMonthlyFamilyExpend()+plan.getHouseMonthlyRepayMent()+plan.getMonthlyFamilyOutLay())*12);
        if(label.equals("[[年储蓄额]]"))target = format.format(income);
        if(label.equals("[[储蓄率]]")) {
            if(salaryYear.doubleValue() == 0.0) return null;
            else{
                target=format.format(income/salaryYear*100)+"%";
            }
        }
        if(label.equals("[[金融资产率]]")){
            if(totalAmount.doubleValue() == 0.0) return null;
            else{
                target=format.format(plan.getAvailableInvestMoney()/totalAmount*100)+"%";
            }
        }
        if(label.equals("[[负债率]]")){
            if(totalAmount.doubleValue() == 0.0) return null;
            else{
                target =format.format(plan.getHouseCreditRemaining()/totalAmount*100)+"%";
            }
        }
        if(label.equals("[[距离退休年数]]")) target = format.format(retireyear);
        if(label.equals("[[投资额/年储蓄额]]")){
            if(plan.getMonthlyDeposit() == 0.0) return null;
            else target=format.format(plan.getAvailableInvestMoney()/(plan.getMonthlyDeposit()*12));
        }
        if(label.equals("[[退休首年的月生活费]]")) target=format.format(plan.getRetireMonthlyCost()*Math.pow((1+plan.getInflation()*0.01),retireyear));
        if(label.equals("[[退休首年年生活费]]")) target=format.format(plan.getRetireMonthlyCost()*Math.pow((1+plan.getInflation()*0.01),retireyear)*12);
        if(label.equals("[[退休养老总费用]]")) {
            target=format.format(rtotal);
        }
        if(label.equals("[[退休时通过投资所储备]]")) target=format.format(plan.getAvailableInvestMoney()*Math.pow((1+plan.getInvestIncomingRate()*0.01), retireyear));
        if(label.equals("[[退休时通过储蓄所储备]]")) {
            if(plan.getInvestIncomingRate() == 0.0) return null;
            else {
                target=format.format(plan.getMonthlyDeposit()*12*(Math.pow((1+plan.getInvestIncomingRate()*0.01), retireyear)-1)/(plan.getInvestIncomingRate()*0.01)+
                    plan.getHouseMonthlyRepayMent()*12*(Math.pow((1+plan.getInvestIncomingRate()*0.01), retireyear-plan.getHouseCreditYearLimit())-1)/(plan.getInvestIncomingRate()*0.01));
            }
        }
        if(label.equals("[[退休时总储备]]")) {
            if(ttotal.doubleValue() == -1.11) return null;
            else target=format.format(ttotal);
        }
        if(label.equals("[[规划结果]]")) {
            if(ttotal >= rtotal) return "规划可行。";
            else return "规划不可以，请重新规划。";
        }
        if(label.equals("[[总目标合计现值]]")) {
           if(plan.getIsDoHousePlan().equals(1)){
               if(plan.getIsDoCollagePlan().equals(1)){
                   //两者都参加
                   target = format.format(plan.getRetireMonthlyCost()*12*plan.getRetireYearLimit()+plan.getPlanHousePrice()+plan.getCollageCost()*plan.getCollageYearLimit());
               }else{
                   //子女教育不参加
                   target = format.format(plan.getRetireMonthlyCost()*12*plan.getRetireYearLimit()+plan.getPlanHousePrice());
               }
           }else{
               if(plan.getIsDoCollagePlan().equals(1)){
                   //购房目标不参加
                   target = format.format(plan.getRetireMonthlyCost()*12*plan.getRetireYearLimit()+plan.getCollageCost()*plan.getCollageYearLimit());
               }else{
                   //两者不都参加
                   target = format.format(plan.getRetireMonthlyCost()*12*plan.getRetireYearLimit());
               }
           }
        }
        
        return target;
    }
   
    
}
