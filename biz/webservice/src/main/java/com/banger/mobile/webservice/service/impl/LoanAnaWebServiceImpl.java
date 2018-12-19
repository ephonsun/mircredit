package com.banger.mobile.webservice.service.impl;

import com.banger.mobile.domain.model.base.loan.BaseAnaCoBorrower;
import com.banger.mobile.domain.model.base.loan.BaseAnaGuarantor;
import com.banger.mobile.domain.model.loan.AnaApplyInfo;
import com.banger.mobile.domain.model.loan.AnaChildren;
import com.banger.mobile.facade.loan.AnaApplyInfoService;
import com.banger.mobile.facade.loan.AnaCoBorrowerAndGuarantorService;
import com.banger.mobile.facade.user.SysUserService;
import com.banger.mobile.facade.webservice.LoanAnaWebService;
import com.banger.mobile.util.JsonDateValueProcessor;
import com.banger.mobile.util.VmHelper;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.apache.log4j.Logger;

import javax.jws.WebService;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhangfp
 * @version $Id: LoanAnaWebServiceImpl v 0.1 ${} 上午9:20 zhangfp Exp $
 */
@WebService(serviceName = "BangerCrmLoanAnaService", endpointInterface = "com.banger.mobile.facade.webservice.LoanAnaWebService")
public class LoanAnaWebServiceImpl implements LoanAnaWebService{

    private static final Logger logger = Logger.getLogger(LoanAnaWebServiceImpl.class);

    private SysUserService sysUserService;                                        // 系统用户service
    private AnaApplyInfoService anaApplyInfoService;                              //上会分析表——贷款申请人信息servie
    private AnaCoBorrowerAndGuarantorService anaCoBorrowerAndGuarantorService;    //上会分析表——共同借贷人和担保人service

    /**
     * 3.128 上会分析表幸福快车调查表——得到申请人信息
     * 
     * @param account
     * @param loanId
     * @return
     */
    public String getAnalyzesAppInfo(String account, Integer loanId){
        try {
            Map<String,Object> paramMap = new HashMap<String, Object>();
            paramMap.put("loanId",loanId);
            AnaApplyInfo anaApplyInfo = anaApplyInfoService.selectApplyInfoByLoanId(paramMap);
            JSONObject retMap = new JSONObject();
            if (anaApplyInfo != null){
                retMap = getAnaApplyInfoJSONObject(anaApplyInfo);
            }
            String ret = retMap.toString();
            return ret;
        }catch (Exception e){
            logger.error("LoanAnaWebServiceImpl % getAnalyzesAppInfo",e);
            return null;
        }
    }

    /**
     * 3.129 上会分析表幸福快车调查表——保存/编辑申请人信息
     * 
     * @param account
     * @param loanId
     * @param jsonString
     * @return
     */
    public String saveAnalyzesAppInfo(String account, Integer loanId, String jsonString){
        try {
            JsonConfig jsonConfig = new JsonConfig();
            jsonConfig.registerJsonValueProcessor(Date.class,new JsonDateValueProcessor());
            JSONObject jsonObject = JSONObject.fromObject(jsonString,jsonConfig);
            AnaApplyInfo anaApplyInfo = (AnaApplyInfo)JSONObject.toBean(jsonObject,AnaApplyInfo.class);
            JSONArray jsonArray = jsonObject.getJSONArray("childrenList");
            if (anaApplyInfo != null){
                List<AnaChildren> childrenList = null;
                if (jsonArray != null){
                    childrenList = JSONArray.toList(jsonArray,AnaChildren.class);
                }
                if (childrenList != null && childrenList.size() > 0){
                    anaApplyInfo.setChildrenList(childrenList);
                }
                if (anaApplyInfo.getApplyInfoId() != null && anaApplyInfo.getApplyInfoId() != -1){
                    //编辑申请人信息
                    Map<String,Object> paramMap = new HashMap<String, Object>();
                    paramMap.put("loanId",loanId);
                    AnaApplyInfo oldAnaApplyInfo = anaApplyInfoService.selectApplyInfoByLoanId(paramMap);
                    this.transferAnaApplyInfo(oldAnaApplyInfo,anaApplyInfo);
                    if (childrenList != null && childrenList.size() > 0){
//                        //要添加的家庭成员
//                        List<AnaChildren> addingChildrenList = new ArrayList<AnaChildren>();
//                        //要编辑的家庭成员
//                        List<AnaChildren> editingChildrenList = new ArrayList<AnaChildren>();
//                        for (AnaChildren anaChildren : childrenList){
//                            anaChildren.setApplyInfoId(anaApplyInfo.getApplyInfoId());
//                            if (anaChildren.getChildrenId() != null && anaChildren.getChildrenId() > 0){
//                                editingChildrenList.add(anaChildren);
//                            }else {
//                                addingChildrenList.add(anaChildren);
//                            }
//                        }
                        anaApplyInfoService.updateApplyAndChildren(anaApplyInfo,childrenList,null,true);
                    }else {
                        //删除原先存在的家庭成员
                        anaApplyInfoService.updateApplyAndChildren(anaApplyInfo,null,null,true);
                    }
                }else {
                    //添加申请人信息
                    anaApplyInfoService.insertApplyAndChildrenBatch(anaApplyInfo);
                }
            }
            return "true";
        }catch (Exception e){
            logger.error("LoanAnaWebServiceImpl % saveAnalyzesAppInfo",e);
            return "false";
        }
    }

    /**
     * 3.130	上会分析表幸福快车调查表——得到共同借贷人列表
     * @param account
     * @param loanId
     * @return
     */
    public String getAnalyzesCobList(String account, Integer loanId){
        try {
            JSONArray jsonArray = new JSONArray();
            Map<String,Object> paramMap = new HashMap<String, Object>();
            paramMap.put("loanId",loanId);
            List<BaseAnaCoBorrower> anaCoBorrowerList = anaCoBorrowerAndGuarantorService.getAllCoBorrowerListByLoan(paramMap);
            if (anaCoBorrowerList != null && anaCoBorrowerList.size() > 0){
                for (BaseAnaCoBorrower baseAnaCoBorrower : anaCoBorrowerList){
                    baseAnaCoBorrower.setCoAnnualIncoming(VmHelper.getUnityDecimalMoney(baseAnaCoBorrower.getAnnualIncoming()));
                }
                jsonArray = JSONArray.fromObject(anaCoBorrowerList);
            }
            return jsonArray.toString();
        }catch (Exception e){
            logger.error("LoanAnaWebServiceImpl % getAnalyzesCobList",e);
            return null;
        }
    }

    /**
     * 3.131	上会分析表幸福快车调查表——添加/编辑共同借贷人
     * @param account
     * @param loanId
     * @param jsonString
     * @return
     */
    public String saveAnalyzesCobInfo(String account, Integer loanId, String jsonString){
        try {
            JSONArray jsonArray = JSONArray.fromObject(jsonString);
            List<BaseAnaCoBorrower> anaCoBorrowerList = null;
            if (jsonArray != null){
                anaCoBorrowerList = JSONArray.toList(jsonArray);
            }
            if (anaCoBorrowerList != null && anaCoBorrowerList.size() > 0){
                anaCoBorrowerAndGuarantorService.saveAnalyzesCobInfo(loanId,anaCoBorrowerList);
            }
            return "true";
        }catch (Exception e){
            logger.error("LoanAnaWebServiceImpl % saveAnalyzesCobInfo",e);
            return "false";
        }
    }

    /**
     * 3.132	上会分析表幸福快车调查表——得到担保人列表
     * @param account
     * @param loanId
     * @return
     */
    public String getAnalyzesGuaList(String account, Integer loanId){
        try {
            JSONArray jsonArray = new JSONArray();
            Map<String,Object> paramMap = new HashMap<String, Object>();
            paramMap.put("loanId",loanId);
            List<BaseAnaGuarantor> anaGuarantorList = anaCoBorrowerAndGuarantorService.getAllGuarantorListByLoan(paramMap);
            if (anaGuarantorList != null && anaGuarantorList.size() > 0){
                for (BaseAnaGuarantor anaGuarantor : anaGuarantorList){
                    anaGuarantor.setGuaAnnualIncoming(VmHelper.getUnityDecimalMoney(anaGuarantor.getAnnualIncoming()));
                    anaGuarantor.setGuaDebt(VmHelper.getUnityDecimalMoney(anaGuarantor.getDebt()));
                }
                jsonArray = JSONArray.fromObject(anaGuarantorList);
            }
            return jsonArray.toString();
        }catch (Exception e){
            logger.error("LoanAnaWebServiceImpl % getAnalyzesGuaList",e);
            return null;
        }
    }

    /**
     * 3.133	上会分析表幸福快车调查表——添加/编辑担保人
     * @param account
     * @param loanId
     * @param jsonString
     * @return
     */
    public String saveAnalyzesGuaInfo(String account, Integer loanId, String jsonString){
        try {
            JSONArray jsonArray = JSONArray.fromObject(jsonString);
            List<BaseAnaGuarantor> anaGuarantorList = null;
            if (jsonArray != null){
                anaGuarantorList = JSONArray.toList(jsonArray);
            }
            anaCoBorrowerAndGuarantorService.saveAnalyzesGuaInfo(loanId,anaGuarantorList);
            return "true";
        }catch (Exception e){
            logger.error("LoanAnaWebServiceImpl % saveAnalyzesGuaInfo",e);
            return "false";
        }
    }

    private JSONObject getAnaApplyInfoJSONObject(AnaApplyInfo anaApplyInfo){
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.registerJsonValueProcessor(Date.class,new JsonDateValueProcessor());
        JSONObject retMap = JSONObject.fromObject(anaApplyInfo,jsonConfig);
        retMap.put("annualIncoming", VmHelper.getUnityDecimalMoney(anaApplyInfo.getAnnualIncoming()));
        //长期所在地
        List<AnaChildren> anaChildrenList = anaApplyInfoService.selectChildrenInfoList(anaApplyInfo.getApplyInfoId());
        JSONArray childrenInfoObj = JSONArray.fromObject(anaChildrenList);
        //家庭成员
        retMap.put("childrenList",childrenInfoObj);
        return retMap;
    }

    /**
     * 转换两个对像的数据
     * @param oldAnaApplyInfo 数据库原来的对象
     * @param newAnaApplyInfo 要编辑的对象
     */
    private void transferAnaApplyInfo(AnaApplyInfo oldAnaApplyInfo,AnaApplyInfo newAnaApplyInfo){
        oldAnaApplyInfo.setCustomerName(newAnaApplyInfo.getCustomerName());
        oldAnaApplyInfo.setIdCard(newAnaApplyInfo.getIdCard());
        oldAnaApplyInfo.setMobilePhone(newAnaApplyInfo.getMobilePhone());
        oldAnaApplyInfo.setNativeProvince(newAnaApplyInfo.getNativeProvince());
        oldAnaApplyInfo.setNativeCity(newAnaApplyInfo.getNativeCity());
        oldAnaApplyInfo.setNativeDistrict(newAnaApplyInfo.getNativeDistrict());
        oldAnaApplyInfo.setNativeAddress(newAnaApplyInfo.getNativeAddress());
        oldAnaApplyInfo.setLivingProvince(newAnaApplyInfo.getLivingProvince());
        oldAnaApplyInfo.setLivingCity(newAnaApplyInfo.getLivingCity());
        oldAnaApplyInfo.setLivingDistrict(newAnaApplyInfo.getLivingDistrict());
        oldAnaApplyInfo.setLivingAddress(newAnaApplyInfo.getLivingAddress());
        oldAnaApplyInfo.setCompany(newAnaApplyInfo.getCompany());
        oldAnaApplyInfo.setJob(newAnaApplyInfo.getJob());
        oldAnaApplyInfo.setCompanyPhone(newAnaApplyInfo.getCompanyPhone());
        oldAnaApplyInfo.setAnnualIncoming(newAnaApplyInfo.getAnnualIncoming());
        oldAnaApplyInfo.setFamilyCount(newAnaApplyInfo.getFamilyCount());
        oldAnaApplyInfo.setFamilyPhone(newAnaApplyInfo.getFamilyPhone());
        oldAnaApplyInfo.setUpdateDate(new Date());
    }

    public SysUserService getSysUserService() {
        return sysUserService;
    }

    public void setSysUserService(SysUserService sysUserService) {
        this.sysUserService = sysUserService;
    }

    public AnaApplyInfoService getAnaApplyInfoService() {
        return anaApplyInfoService;
    }

    public void setAnaApplyInfoService(AnaApplyInfoService anaApplyInfoService) {
        this.anaApplyInfoService = anaApplyInfoService;
    }

    public AnaCoBorrowerAndGuarantorService getAnaCoBorrowerAndGuarantorService() {
        return anaCoBorrowerAndGuarantorService;
    }

    public void setAnaCoBorrowerAndGuarantorService(AnaCoBorrowerAndGuarantorService anaCoBorrowerAndGuarantorService) {
        this.anaCoBorrowerAndGuarantorService = anaCoBorrowerAndGuarantorService;
    }
}
