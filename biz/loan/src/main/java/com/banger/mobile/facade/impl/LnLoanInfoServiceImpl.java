package com.banger.mobile.facade.impl;

import com.banger.mobile.dao.loan.LnLoanInfoDao;
import com.banger.mobile.dao.webservice.PadLnLoanInfoDao;
import com.banger.mobile.domain.model.loan.*;
import com.banger.mobile.domain.model.pad.PadLoanInfo;
import com.banger.mobile.facade.loan.*;
import com.banger.mobile.util.JsonDateValueProcessor;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.jfree.util.Log;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ouyl
 * @version $Id: LnLoanInfoServiceImpl.java,v 0.1 13-6-20 ouyl Exp $
 */
public class LnLoanInfoServiceImpl implements LnLoanInfoService {
	
	
    private static Logger logger = Logger.getLogger(LnLoanInfoServiceImpl.class);

    private LnLoanService lnLoanService;
    private LnLoanInfoDao lnLoanInfoDao;
    private LnLoanInfoDictionaryService lnLoanInfoDictionaryService;
    private LnRepaymentPlanService lnRepaymentPlanService;
    private PadLnLoanInfoDao padLnLoanInfoDao;
    private LnLoanCoBorrowerService lnLoanCoBorrowerService;
    private LnLoanGuarantorService lnLoanGuarantorService;
    private LnCreditHistoryService lnCreditHistoryService;
    
    
	public void setLnLoanCoBorrowerService(
			LnLoanCoBorrowerService lnLoanCoBorrowerService) {
		this.lnLoanCoBorrowerService = lnLoanCoBorrowerService;
	}

	public void setLnLoanGuarantorService(
			LnLoanGuarantorService lnLoanGuarantorService) {
		this.lnLoanGuarantorService = lnLoanGuarantorService;
	}

	public void setLnCreditHistoryService(
			LnCreditHistoryService lnCreditHistoryService) {
		this.lnCreditHistoryService = lnCreditHistoryService;
	}

	public PadLnLoanInfoDao getPadLnLoanInfoDao() {
		return padLnLoanInfoDao;
	}

	public void setPadLnLoanInfoDao(PadLnLoanInfoDao padLnLoanInfoDao) {
		this.padLnLoanInfoDao = padLnLoanInfoDao;
	}

	public void setLnLoanService(LnLoanService lnLoanService) {
        this.lnLoanService = lnLoanService;
    }

    public void setLnLoanInfoDao(LnLoanInfoDao lnLoanInfoDao) {
        this.lnLoanInfoDao = lnLoanInfoDao;
    }

    public LnLoanService getLnLoanService() {
        return lnLoanService;
    }

    public LnLoanInfoDao getLnLoanInfoDao() {
        return lnLoanInfoDao;
    }

    public LnLoanInfoDictionaryService getLnLoanInfoDictionaryService() {
        return lnLoanInfoDictionaryService;
    }

    public void setLnLoanInfoDictionaryService(LnLoanInfoDictionaryService lnLoanInfoDictionaryService) {
        this.lnLoanInfoDictionaryService = lnLoanInfoDictionaryService;
    }

    public LnRepaymentPlanService getLnRepaymentPlanService() {
        return lnRepaymentPlanService;
    }

    public void setLnRepaymentPlanService(LnRepaymentPlanService lnRepaymentPlanService) {
        this.lnRepaymentPlanService = lnRepaymentPlanService;
    }

    /**
     * 插入贷款信息
     *
     * @param lnLoanInfo
     */
    public void insertLnLoanInfo(LnLoanInfo lnLoanInfo) {
        lnLoanInfoDao.insertLnLoanInfo(lnLoanInfo);
    }

    /**
     * 更新贷款信息
     *
     * @param lnLoanInfo
     */
    public void updateLnLoanInfo(LnLoanInfo lnLoanInfo) {
        lnLoanInfoDao.updateLnLoanInfo(lnLoanInfo);
    }

    /**
     * 根据流水号查找贷款信息
     *
     * @param param
     * @return
     */
    public List<LnLoanInfo> selectLoanInfoList(Map<String, Object> param) {
        return lnLoanInfoDao.selectLoanInfoList(param);
    }

    /**
     *批量插入贷款申请信息
     * @param lnLoanInfoList
     */
    public void insertLnLoanInfoBatch(List<LnLoanInfo> lnLoanInfoList){
        lnLoanInfoDao.insertLnLoanInfoBatch(lnLoanInfoList);
    }

    /**
     * 根据字符串添加贷款表中客户信息
     *
     * @param report
     */
    public void addLoanInfoByLoan(String report) throws Exception {/*
        Map<String, Object> param = new HashMap<String, Object>();
        Map<String, Object> param1 = new HashMap<String, Object>();
        Map<String, Object> param2 = new HashMap<String, Object>();
        Map<String, Object> param3 = new HashMap<String, Object>();
        Map<String, Object> dictionary = new HashMap<String, Object>();
        LnLoanInfo lnLoanInfo = new LnLoanInfo();
        String[] loanInfo = report.split("\\|", -1);
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");

        //获取贷款信息字典
        List<LnLoanInfoDictionary> loanInfoDictionaryList = lnLoanInfoDictionaryService.selectLoanInfoDictionaryList(param1);
        for (LnLoanInfoDictionary lnLoanInfoDictionary : loanInfoDictionaryList) {
            dictionary.put(lnLoanInfoDictionary.getRemark() + "_" + lnLoanInfoDictionary.getDictionaryKey(),
                    lnLoanInfoDictionary.getDictionaryValue());
        }


//        if(loanInfo[2] !=null && loanInfo[2] !=""){
//            Date genDateMin =format.parse(loanInfo[2]+" 00:00:00");
//            Date genDateMax =format.parse(loanInfo[2]+" 23:59:59");
//            param.put("genDateMin",genDateMin);
//            param.put("genDateMax",genDateMax);
//        }

        //经营性
        if (loanInfo[1].equals("CRD_WXDQZKH")) {
            param2.put("serialNumber", loanInfo[3]);
            List<LnLoanInfo> loanInfoList = lnLoanInfoDao.selectLoanInfoList(param2);

            if (loanInfoList.size() > 0) {
                param.put("serialNumber", loanInfo[3]);
            } else {
                param.put("cusName", loanInfo[4]);
                param.put("cusIdCard", loanInfo[7]);
                Integer[] loanStatusIds = {1, 2, 3, 4, 5};
                param.put("loanStatusIds", loanStatusIds);
            }

            Integer loanId = lnLoanService.getLoanId(param);
            if (loanId != null) {
                LnLoan loan = lnLoanService.getLnLoanById(loanId);
                Date djrq = format1.parse(loanInfo[2]);
                Date createDate = format1.parse(format1.format(loan.getCreateDate()));
                long djrqTime = djrq.getTime();
                long createTime = createDate.getTime();
                //比较贷款的申请时间范围，目前定为一个月内，即，移动贷款平台与信贷系统平台的贷款申请时间差在一个月内才同步，否则不同步
                if ((createTime - djrqTime > 0 && (createTime - djrqTime)/(60*1000*60*24) <= 30)
                        || (createTime - djrqTime < 0 && (djrqTime - createTime )/(60*1000*60*24) <= 30) ){
                    lnLoanInfo.setLoanId(loanId);
                    lnLoanInfo.setBizType(loanInfo[1]);
                    lnLoanInfo.setGenDate(format1.parse(loanInfo[2]));
                    lnLoanInfo.setSerialNumber(loanInfo[3]);
                    lnLoanInfo.setCusName(loanInfo[4]);
                    lnLoanInfo.setCusBirthday(loanInfo[5]);
                    if (loanInfo[6] != null && loanInfo[6] != "") {
                        lnLoanInfo.setCusSex(StringUtil.getNotNullValue(dictionary.get("性别_" + loanInfo[6])));
                    }
                    lnLoanInfo.setCusIdcard(loanInfo[7]);
                    if (loanInfo[8] != null && loanInfo[8] != "") {
                        lnLoanInfo.setCusMarriage(StringUtil.getNotNullValue(dictionary.get("婚姻状况_" + loanInfo[8])));
                    }
                    lnLoanInfo.setCusLocalYear(loanInfo[9]);
                    lnLoanInfo.setMateName(loanInfo[10]);
                    lnLoanInfo.setMateIdcard(loanInfo[11]);
                    if (loanInfo[10] != null && loanInfo[10] != "") {
                        lnLoanInfo.setMateJob(StringUtil.getNotNullValue(dictionary.get("配偶职业_" + loanInfo[12])));
                    }
                    lnLoanInfo.setCusAddress(loanInfo[13]);
                    lnLoanInfo.setCusFamilyNum(loanInfo[14]);
                    lnLoanInfo.setCusMobilePhone(loanInfo[15]);
                    lnLoanInfo.setCusPhone(loanInfo[16]);
                    if (loanInfo[17] != null && loanInfo[17] != "") {
                        lnLoanInfo.setCusLivingStatus(StringUtil.getNotNullValue(dictionary.get("居住状况_" + loanInfo[17])));
                    }
                    if (loanInfo[18] != null && loanInfo[18] != "") {
                        lnLoanInfo.setCusEducation(StringUtil.getNotNullValue(dictionary.get("最高学历_" + loanInfo[18])));
                    }
                    if (loanInfo[19] != null && loanInfo[19] != "") {
                        lnLoanInfo.setCusVocation(StringUtil.getNotNullValue(dictionary.get("职业_" + loanInfo[19])));
                    }
                    lnLoanInfo.setCusCompany(loanInfo[20]);
                    lnLoanInfo.setCusCompanyAddress(loanInfo[21]);
                    lnLoanInfo.setBizCompany(loanInfo[22]);
                    lnLoanInfo.setBizScope(loanInfo[23]);
                    lnLoanInfo.setBizAddress(loanInfo[24]);
                    lnLoanInfo.setBizStartDate(loanInfo[25]);
                    if (loanInfo[26] != null && loanInfo[26] != "") {
                        lnLoanInfo.setBizOrg(StringUtil.getNotNullValue(dictionary.get("组织形式_" + loanInfo[26])));
                    }
                    lnLoanInfo.setBizOrgCode(loanInfo[27]);
                    lnLoanInfo.setBizEmployeeNum(loanInfo[28]);
                    if (loanInfo[29] != null && loanInfo[29] != "") {
                        lnLoanInfo.setBizCompanyType(StringUtil.getNotNullValue(dictionary.get("企业类型_" + loanInfo[29])));
                    }
                    lnLoanInfo.setBizPlace(loanInfo[30]);
                    lnLoanInfo.setBizArea(loanInfo[31]);
                    lnLoanInfo.setBizHouseRent(loanInfo[32]);
                    lnLoanInfo.setBizLicence(loanInfo[33]);
                    lnLoanInfo.setAppMoney(loanInfo[34]);
                    lnLoanInfo.setAppUsage(loanInfo[35]);
                    lnLoanInfo.setAppLimitYear(loanInfo[36]);
                    lnLoanInfo.setAppRepaymentDate(loanInfo[37]);
                    lnLoanInfo.setAppRepaymentMonth(loanInfo[38]);
                    lnLoanInfo.setAppRepaymentSource(loanInfo[39]);
                    lnLoanInfo.setAppGnt(loanInfo[40]);
                    lnLoanInfo.setAppGntIdcard(loanInfo[41]);
                    lnLoanInfo.setAppGntPhone(loanInfo[42]);
                    if (loanInfo[43] != null && loanInfo[43] != "") {
                        lnLoanInfo.setAppGntRelation(StringUtil.getNotNullValue(dictionary.get("关系_" + loanInfo[43])));
                    }
                    lnLoanInfo.setAppGntCompany(loanInfo[44]);
                    lnLoanInfo.setCfaAnnualSales(loanInfo[45]);
                    lnLoanInfo.setCfaGrossMargin(loanInfo[46]);
                    lnLoanInfo.setCfaYearMargin(loanInfo[47]);
                    if (loanInfo[48] != null && loanInfo[48] != "") {
                        lnLoanInfo.setCfaProfitLevel(StringUtil.getNotNullValue(dictionary.get("利润同行比较_" + loanInfo[48])));
                    }
                    lnLoanInfo.setCfaInventory(loanInfo[49]);
                    lnLoanInfo.setCfaAccountIncoming(loanInfo[50]);
                    lnLoanInfo.setCfaDebt(loanInfo[51]);
                    lnLoanInfo.setCfaTotal(loanInfo[52]);
                    lnLoanInfo.setCfaOther(loanInfo[53]);
                    lnLoanInfo.setCfaSupply(loanInfo[54]);


                    if (loanInfoList.size() > 0) {
                        lnLoanInfoDao.updateLnLoanInfo(lnLoanInfo);
                    } else {
                        lnLoanInfoDao.insertLnLoanInfo(lnLoanInfo);
                    }
                    param3.put("loanId", loanId);
                    param3.put("loanMoney", loanInfo[34]);
                    lnLoanService.updateLnLoanById(param3);
                }
            }
        }

        if (loanInfo[1].equals("CRD_WXDXF_DJ")) {
            param2.put("serialNumber", loanInfo[3]);
            List<LnLoanInfo> loanInfoList = lnLoanInfoDao.selectLoanInfoList(param2);

            if (loanInfoList.size() > 0) {
                param.put("serialNumber", loanInfo[3]);
            } else {
                param.put("cusName", loanInfo[4]);
                param.put("cusIdCard", loanInfo[7]);
                Integer[] loanStatusIds = {1, 2, 3, 4, 5};
                param.put("loanStatusIds", loanStatusIds);
            }

            Integer loanId = lnLoanService.getLoanId(param);
            if (loanId != null) {
                lnLoanInfo.setLoanId(loanId);
                lnLoanInfo.setBizType(loanInfo[1]);
                lnLoanInfo.setGenDate(format1.parse(loanInfo[2]));
                lnLoanInfo.setSerialNumber(loanInfo[3]);
                lnLoanInfo.setCusName(loanInfo[4]);
                lnLoanInfo.setCusBirthday(loanInfo[5]);
                if (loanInfo[6] != null && loanInfo[6] != "") {
                    lnLoanInfo.setCusSex(StringUtil.getNotNullValue(dictionary.get("性别_" + loanInfo[6])));
                }
                lnLoanInfo.setCusIdcard(loanInfo[7]);
                if (loanInfo[8] != null && loanInfo[8] != "") {
                    lnLoanInfo.setCusMarriage(StringUtil.getNotNullValue(dictionary.get("婚姻状况_" + loanInfo[8])));
                }
                lnLoanInfo.setCusLocalYear(loanInfo[9]);
                lnLoanInfo.setCusAddress(loanInfo[10]);
                lnLoanInfo.setCusFamilyNum(loanInfo[11]);
                lnLoanInfo.setCusMobilePhone(loanInfo[12]);
                lnLoanInfo.setCusPhone(loanInfo[13]);
                if (loanInfo[14] != null && loanInfo[14] != "") {
                    lnLoanInfo.setCusLivingStatus(StringUtil.getNotNullValue(dictionary.get("居住状况_" + loanInfo[14])));
                }
                if (loanInfo[15] != null && loanInfo[15] != "") {
                    lnLoanInfo.setCusEducation(StringUtil.getNotNullValue(dictionary.get("最高学历_" + loanInfo[15])));
                }
                if (loanInfo[16] != null && loanInfo[16] != "") {
                    lnLoanInfo.setCusVocation(StringUtil.getNotNullValue(dictionary.get("职业_" + loanInfo[16])));
                }
                lnLoanInfo.setCusCompany(loanInfo[17]);
                lnLoanInfo.setCusCompanyAddress(loanInfo[18]);
                lnLoanInfo.setCusWorkYear(loanInfo[19]);
                lnLoanInfo.setCusMonthlyIncoming(loanInfo[20]);
                lnLoanInfo.setCusSelfBiz(loanInfo[21]);
                lnLoanInfo.setMateName(loanInfo[22]);
                lnLoanInfo.setMateIdcard(loanInfo[23]);
                if (loanInfo[24] != null && loanInfo[24] != "") {
                    lnLoanInfo.setMateVocation(StringUtil.getNotNullValue(dictionary.get("配偶职业_" + loanInfo[24])));
                }
                lnLoanInfo.setMateWorkYear(loanInfo[25]);
                lnLoanInfo.setMatePhone(loanInfo[26]);
                lnLoanInfo.setMateMobilePhone(loanInfo[27]);
                lnLoanInfo.setMateCompany(loanInfo[28]);
                lnLoanInfo.setMateJob(loanInfo[29]);
                lnLoanInfo.setMateAddress(loanInfo[30]);
                if (loanInfo[31] != null && loanInfo[31] != "") {
                    lnLoanInfo.setMateMonthlyIncoming(StringUtil.getNotNullValue(dictionary.get("配偶工资水平_" + loanInfo[31])));
                }
                lnLoanInfo.setChdName(loanInfo[32]);
                lnLoanInfo.setChdSex(loanInfo[33]);
                lnLoanInfo.setChdHealth(loanInfo[34]);
                lnLoanInfo.setChdBirthday(loanInfo[35]);
                lnLoanInfo.setChdCompany(loanInfo[36]);
                lnLoanInfo.setChdEducation(loanInfo[37]);
                lnLoanInfo.setChdParentInfo(loanInfo[38]);
                lnLoanInfo.setAppMoney(loanInfo[39]);
                lnLoanInfo.setAppUsage(loanInfo[40]);
                lnLoanInfo.setAppLimitYear(loanInfo[41]);
                lnLoanInfo.setAppRepaymentDate(loanInfo[42]);
                lnLoanInfo.setAppRepaymentMonth(loanInfo[43]);
                lnLoanInfo.setAppRepaymentSource(loanInfo[44]);
                lnLoanInfo.setAppGnt(loanInfo[45]);
                lnLoanInfo.setAppGntIdcard(loanInfo[46]);
                lnLoanInfo.setAppGntPhone(loanInfo[47]);
                if (loanInfo[48] != null && loanInfo[48] != "") {
                    lnLoanInfo.setAppGntRelation(StringUtil.getNotNullValue(dictionary.get("关系_" + loanInfo[48])));
                }
                lnLoanInfo.setAppGntCompany(loanInfo[49]);
                lnLoanInfo.setAppGntJob(loanInfo[50]);
                lnLoanInfo.setAppGntAddress(loanInfo[51]);
                if (loanInfo[52] != null && loanInfo[52] != "") {
                    lnLoanInfo.setAppGntSalary(StringUtil.getNotNullValue(dictionary.get("月工资水品_" + loanInfo[52])));
                }
                lnLoanInfo.setAppGntProperty(loanInfo[53]);

                param2.put("serialNumber", loanInfo[3]);
                if (loanInfoList.size() > 0) {
                    lnLoanInfoDao.updateLnLoanInfo(lnLoanInfo);
                } else {
                    lnLoanInfoDao.insertLnLoanInfo(lnLoanInfo);
                }
                param3.put("loanId", loanId);
                param3.put("loanMoney", loanInfo[39]);
                lnLoanService.updateLnLoanById(param3);
            }
        }
    */}

    /**
     * 更新贷款账号字段
     *
     * @param param
     */
    public void updateLoanInfoLoanAccount(Map<String, Object> param) {
        lnLoanInfoDao.updateLoanInfoLoanAccount(param);
    }

    /**
     * 同步贷款状态
     *
     * @param msg
     * @throws Exception
     */
    public void synchronousLoanStatus(String msg) throws Exception {
        try {
            HttpServletResponse response = ServletActionContext.getResponse();
            response.setContentType("text/html;charset=utf-8");
            PrintWriter out = response.getWriter();
            String[] report = msg.split("\\|", -1);
            if (report[3].trim().equals("1")) {
                Map<String, Object> param = new HashMap<String, Object>();
                Map<String, Object> param1 = new HashMap<String, Object>();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                param.put("serialNumber", report[1]);
                //根据流水号找到是哪笔贷款
                List<LnLoanInfo> loanInfoList = lnLoanInfoDao.selectLoanInfoList(param);

                //更新贷款信息表 贷款账号
                param.put("loanAccount", report[2]);
                lnLoanInfoDao.updateLoanInfoLoanAccount(param);

                if (loanInfoList != null && loanInfoList.size() > 0) {
                    Integer loanId = loanInfoList.get(0).getLoanId();
                    param1.put("loanId", loanId);
                    param1.put("loanStatusId", 6);
                    param1.put("lendDate", new Date());
                    lnLoanService.updateLnLoanById(param1);
                    if (report.length > 4) {
                        String[] repaymentPlanList = report[4].split(";");
                        for (int i = 0; i < repaymentPlanList.length; i++) {
                            LnRepaymentPlan lnRepaymentPlan = new LnRepaymentPlan();
                            String[] repaymentPlan = repaymentPlanList[i].split("#");
                            if (repaymentPlan[0] != null && repaymentPlan[0].trim() != "") {
                                Integer sortno = Integer.parseInt(repaymentPlan[0]);
                                lnRepaymentPlan.setSortno(sortno);
                            }
                            if (repaymentPlan[1] != null && repaymentPlan[1].trim() != "") {
                                Date repaymentDate = sdf.parse(repaymentPlan[1].trim());
                                lnRepaymentPlan.setPlanDate(repaymentDate);
                            }

                            if (repaymentPlan[2] != null && repaymentPlan[2].trim() != "") {
                                BigDecimal principal = new BigDecimal(repaymentPlan[2].trim());
                                lnRepaymentPlan.setPrincipal(principal);
                            }
                            if (repaymentPlan[3] != null && repaymentPlan[3].trim() != "") {
                                BigDecimal interest = new BigDecimal(repaymentPlan[3].trim());
                                lnRepaymentPlan.setInterest(interest);
                            }
                            if (repaymentPlan[4] != null && repaymentPlan[4].trim() != "") {
                                BigDecimal remaining = new BigDecimal(repaymentPlan[4].trim());
                                lnRepaymentPlan.setRemaining(remaining);
                            }
                            lnRepaymentPlan.setLoanId(loanId);
                            lnRepaymentPlanService.addRepaymentPlan(lnRepaymentPlan);
                        }
                    }
                }
                out.print("success");
            }
            if (out != null) {
                out.flush();
                out.close();
            }
        } catch (Exception e) {
            logger.error("synchronousLoanStatus error: ", e);
        }
    }

    /**
     *
     * @param paramMapList
     * @param lnLoanInfoList
     */
    @Transactional
    public void execLoanInfo(List<Map<String, Object>> paramMapList, List<LnLoanInfo> lnLoanInfoList){
        this.insertLnLoanInfoBatch(lnLoanInfoList);
        lnLoanService.updateLnLoanByIdBatch(paramMapList);
    }

  	@Override
    public void updateLnLoanInfoBatch(List<LnLoanInfo> lnLoanInfoList) {
        lnLoanInfoDao.updateLnLoanInfoBatch(lnLoanInfoList);
    }

    /**
     * 可以置空值的更新
     * @param lnLoanInfo
     */
    @Override
    public void updateLnLoanInfoWhitOutNull(LnLoanInfo lnLoanInfo) {
        lnLoanInfoDao.updateLnLoanInfoWhitOutNull(lnLoanInfo);
    }


    @Override
    public void updateLnLoanInfoWhitOutNullBatch(List<LnLoanInfo> lnLoanInfoList) {
        lnLoanInfoDao.updateLnLoanInfoWhitOutNullBatch(lnLoanInfoList);
    }

	@Override
	public void insertLnLoanInfoSelective(LnLoanInfo lnLoanInfo) {
		lnLoanInfoDao.insertLnLoanInfoSelective(lnLoanInfo);
	}

	@Override
	public PadLoanInfo getPanLoanInfoById(int loanId) {
		return this.padLnLoanInfoDao.getPanLoanInfoById(loanId);
	}

	@Override
	public void bakAppForm(Integer loanId) {
		
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("loanId", loanId);
		//贷款基本信息
		LnLoanInfo lnLoanInfo = this.selectLoanInfoList(param).get(0);
		//共同借款人列表                
        List<LnLoanCoBorrowerBean> loanCoBorrowerList = lnLoanCoBorrowerService.getAllLnLoanCoBorrowerBeanByConds(param);
        //担保人列表
        List<LnLoanGuarantorBean> loanGuarantorList=lnLoanGuarantorService.getAllLnLoanGuarantorBeanByConds(param);
        //信贷历史列表
        List<LnCreditHistory> creditHistoryList = lnCreditHistoryService.getCreditHistoryByLoanId(loanId);
        
        LnLoanAppFormBak lnLoanAppFormBak = new LnLoanAppFormBak();
        lnLoanAppFormBak.setLnLoanInfo(lnLoanInfo);
        lnLoanAppFormBak.setLnLoanCoBorrowerList(loanCoBorrowerList);
        lnLoanAppFormBak.setLnLoanGuarantorList(loanGuarantorList);
        lnLoanAppFormBak.setCreditHistoryList(creditHistoryList);
        
        //JSON序列号---------------------------------------------------------------------
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());                
        String result = JSONObject.fromObject(lnLoanAppFormBak, jsonConfig).toString();
        Log.info("贷款编号：" + loanId + "申请信息备份：" + result);
        
        //更新贷款从表
        LnLoanInfo lnLoanInfoBak = new LnLoanInfo();
        lnLoanInfoBak.setLoanId(loanId);
        lnLoanInfoBak.setAppFormBak(result);
        this.updateLnLoanInfo(lnLoanInfoBak);
        
	}
    
	
	public String getAppLoanTypeIdByLoanId(Integer loanId){
		return lnLoanInfoDao.getAppLoanTypeIdByLoanId(loanId);
	}
	public String getAdviceMoneyByLoanId(Integer loanId){
		return lnLoanInfoDao.getAdviceMoneyByLoanId(loanId);
	}
	
}
