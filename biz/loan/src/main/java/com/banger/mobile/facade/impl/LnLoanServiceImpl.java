/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :贷款主表service
 * Author     :zhangfp
 * Create Date:2013-3-13
 */
package com.banger.mobile.facade.impl;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.dao.customer.CrmCustomerDao;
import com.banger.mobile.dao.loan.LnExceptionDunAssignDao;
import com.banger.mobile.dao.loan.LnLoanDao;
import com.banger.mobile.dao.loan.LnLoanInfoDao;
import com.banger.mobile.dao.webservice.PadLnLoanInfoDao;
import com.banger.mobile.domain.model.base.customer.BaseCrmCustomer;
import com.banger.mobile.domain.model.customer.CrmCustomer;
import com.banger.mobile.domain.model.data.*;
import com.banger.mobile.domain.model.loan.*;
import com.banger.mobile.domain.model.pad.PadLoan;
import com.banger.mobile.domain.model.report.LoanCountReportBean;
import com.banger.mobile.domain.model.report.LoanTypeTotalReportBean;
import com.banger.mobile.domain.model.uploadFile.SysUploadFile;
import com.banger.mobile.domain.model.user.IUserInfo;
import com.banger.mobile.domain.model.user.SysUser;
import com.banger.mobile.facade.crRequest.CrRequestService;
import com.banger.mobile.facade.data.*;
import com.banger.mobile.facade.dept.DeptFacadeService;
import com.banger.mobile.facade.loan.*;
import com.banger.mobile.facade.param.SysParamService;
import com.banger.mobile.facade.specialDataAuth.SpecialDataAuthService;
import com.banger.mobile.facade.system.team.SysTeamUserService;
import com.banger.mobile.facade.uploadFile.SysUploadFileService;
import com.banger.mobile.facade.user.SysUserService;
import com.banger.mobile.util.CompressUtil;
import com.banger.mobile.util.FileUtil;
import com.banger.mobile.util.StringUtil;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.math.BigDecimal;
import java.util.*;

/**
 * @author zhangfp
 * @version $Id: LnLoanServiceImpl.java,v 0.1 2013-3-13 下午3:13:08 zhangfp Exp $
 */
public class LnLoanServiceImpl implements LnLoanService {

    private static Logger logger = Logger.getLogger(LnLoanServiceImpl.class);
    
    private LnCreditHistoryService lnCreditHistoryService;

	private LnLoanDao                       lnLoanDao;
    private LnOpHistoryService              lnOpHistoryService;             // 贷款操作历史记录service
    private CrmCustomerDao                  crmCustomerDao;                 // 客户dao
    private LnApproveLimitUserService       lnApproveLimitUserService;      // 用户审批额度设置表service
    private LnApproveLimitRoleService       lnApproveLimitRoleService;      // 角色审批额度设置表service
    private LnLoanCoBorrowerService         lnLoanCoBorrowerService;        // 共同借贷人
    private LnLoanGuarantorService          lnLoanGuarantorService;         // 担保人
    private LnExceptionRepaymentPlanService lnExceptionRepaymentPlanService; // 异常贷款还款计划
    private LnExceptionDunAssignDao         lnExceptionDunAssignDao;        // 异常催收贷款分配
    private LnVerifyHistoryService          lnVerifyHistoryService;         // 审计历史记录service
    private SysTeamUserService sysTeamUserService;
    private CustomerDataService             customerDataService;
    private DeptFacadeService deptFacadeService; //部门组织结构service
    private SysUserService sysUserService; //系统用户service
    private SpecialDataAuthService specialDataAuthService;  //特殊数据权限service
    private SysParamService               sysParamService;
    private SysUploadFileService sysUploadFileService;
    
    private DataFormService dataFormService; //表单service
    private DataPhotoService dataPhotoService; //照片service
    private DataVideoService dataVideoService; //视频service
    private DataAudioService dataAudioService; //录音service
    private CrRequestService crRequestService;
    
    private PadLnLoanInfoDao padLnLoanInfoDao;    
    private LnLoanInfoDao lnLoanInfoDao;
         
    public LnLoanInfoDao getLnLoanInfoDao() {
		return lnLoanInfoDao;
	}

	public void setLnLoanInfoDao1(LnLoanInfoDao lnLoanInfoDao) {
		this.lnLoanInfoDao = lnLoanInfoDao;
	}

	public PadLnLoanInfoDao getPadLnLoanInfoDao() {
		return padLnLoanInfoDao;
	}

	public void setPadLnLoanInfoDao(PadLnLoanInfoDao padLnLoanInfoDao) {
		this.padLnLoanInfoDao = padLnLoanInfoDao;
	}
	
    public SysUploadFileService getSysUploadFileService() {
        return sysUploadFileService;
    }

    public void setSysUploadFileService(SysUploadFileService sysUploadFileService) {
        this.sysUploadFileService = sysUploadFileService;
    }

    public SysParamService getSysParamService() {
        return sysParamService;
    }

    public void setSysParamService(SysParamService sysParamService) {
        this.sysParamService = sysParamService;
    }

    public SpecialDataAuthService getSpecialDataAuthService() {
        return specialDataAuthService;
    }

    public void setSpecialDataAuthService(SpecialDataAuthService specialDataAuthService) {
        this.specialDataAuthService = specialDataAuthService;
    }

    public DataAudioService getDataAudioService() {
        return dataAudioService;
    }

    public void setDataAudioService(DataAudioService dataAudioService) {
        this.dataAudioService = dataAudioService;
    }

    public DataPhotoService getDataPhotoService() {
        return dataPhotoService;
    }

    public void setDataPhotoService(DataPhotoService dataPhotoService) {
        this.dataPhotoService = dataPhotoService;
    }

    public DataVideoService getDataVideoService() {
        return dataVideoService;
    }

    public void setDataVideoService(DataVideoService dataVideoService) {
        this.dataVideoService = dataVideoService;
    }

    public DataFormService getDataFormService() {
        return dataFormService;
    }

    public void setDataFormService(DataFormService dataFormService) {
        this.dataFormService = dataFormService;
    }

    public SysUserService getSysUserService() {
        return sysUserService;
    }

    public void setSysUserService(SysUserService sysUserService) {
        this.sysUserService = sysUserService;
    }

    public DeptFacadeService getDeptFacadeService() {
        return deptFacadeService;
    }

    public void setDeptFacadeService(DeptFacadeService deptFacadeService) {
        this.deptFacadeService = deptFacadeService;
    }

    public void setCustomerDataService(CustomerDataService customerDataService) {
        this.customerDataService = customerDataService;
    }

    public LnExceptionDunAssignDao getLnExceptionDunAssignDao() {
        return lnExceptionDunAssignDao;
    }

    public void setLnExceptionDunAssignDao(LnExceptionDunAssignDao lnExceptionDunAssignDao) {
        this.lnExceptionDunAssignDao = lnExceptionDunAssignDao;
    }
    
    public void setLnLoanInfoDao(LnLoanInfoDao lnLoanInfoDao) {
		this.lnLoanInfoDao = lnLoanInfoDao;
	}

	public LnLoanCoBorrowerService getLnLoanCoBorrowerService() {
        return lnLoanCoBorrowerService;
    }

    public void setLnLoanCoBorrowerService(LnLoanCoBorrowerService lnLoanCoBorrowerService) {
        this.lnLoanCoBorrowerService = lnLoanCoBorrowerService;
    }

    public LnLoanGuarantorService getLnLoanGuarantorService() {
        return lnLoanGuarantorService;
    }

    public void setLnLoanGuarantorService(LnLoanGuarantorService lnLoanGuarantorService) {
        this.lnLoanGuarantorService = lnLoanGuarantorService;
    }

    public LnLoanDao getLnLoanDao() {
        return lnLoanDao;
    }

    public void setLnLoanDao(LnLoanDao lnLoanDao) {
        this.lnLoanDao = lnLoanDao;
    }

    public LnOpHistoryService getLnOpHistoryService() {
        return lnOpHistoryService;
    }

    public void setLnOpHistoryService(LnOpHistoryService lnOpHistoryService) {
        this.lnOpHistoryService = lnOpHistoryService;
    }

    public CrmCustomerDao getCrmCustomerDao() {
        return crmCustomerDao;
    }

    public void setCrmCustomerDao(CrmCustomerDao crmCustomerDao) {
        this.crmCustomerDao = crmCustomerDao;
    }

    public LnApproveLimitUserService getLnApproveLimitUserService() {
        return lnApproveLimitUserService;
    }

    public void setLnApproveLimitUserService(LnApproveLimitUserService lnApproveLimitUserService) {
        this.lnApproveLimitUserService = lnApproveLimitUserService;
    }

    public LnApproveLimitRoleService getLnApproveLimitRoleService() {
        return lnApproveLimitRoleService;
    }

    public void setLnApproveLimitRoleService(LnApproveLimitRoleService lnApproveLimitRoleService) {
        this.lnApproveLimitRoleService = lnApproveLimitRoleService;
    }

    public LnExceptionRepaymentPlanService getLnExceptionRepaymentPlanService() {
        return lnExceptionRepaymentPlanService;
    }

    public void setLnExceptionRepaymentPlanService(LnExceptionRepaymentPlanService lnExceptionRepaymentPlanService) {
        this.lnExceptionRepaymentPlanService = lnExceptionRepaymentPlanService;
    }

    public LnVerifyHistoryService getLnVerifyHistoryService() {
        return lnVerifyHistoryService;
    }

    public void setLnVerifyHistoryService(LnVerifyHistoryService lnVerifyHistoryService) {
        this.lnVerifyHistoryService = lnVerifyHistoryService;
    }

    public CrRequestService getCrRequestService() {
        return crRequestService;
    }

    public void setCrRequestService(CrRequestService crRequestService) {
        this.crRequestService = crRequestService;
    }

    /**
     * 新增贷款
     * 
     * @param lnLoan
     */
    public void insertLoan(LnLoan lnLoan) {
        lnLoanDao.insertLoan(lnLoan);
    }

    public void insertLoanBatch(List<LnLoan> lnloans){
        lnLoanDao.insertLoanBatch(lnloans);
    }

    public void deleteLoan(Integer loanId) {
//        LnLoan lnLoan = ln
//    	lnLoanDao.updateLoanByLoanId();
//    	lnOpHistoryService.deletelnOpHistoryByLoanId(loanId);
//    	lnLoanCoBorrowerService.deleteCoBorrowerById(loanId);
//    	lnLoanGuarantorService.deleteLnLoanGuarantorByLoanId(loanId);
//    	lnLoanInfoDao.deleteLoan(loanId);
//        lnLoanDao.deleteLoan(loanId);
//        crRequestService.updateCrRequest("");
//        lnCreditHistoryService.deleteByLoanId(loanId);
//        Map<String,Object> paramMap = new HashMap<String, Object>();
//        paramMap.put("loanId", loanId);
//        customerDataService.delCustomerData(paramMap);
    }

    public List<LnLoan> queryLnLoanRelation(Map<String, Object> paramMap) {
        return lnLoanDao.queryLnLoanRelation(paramMap);
    }

    /**
     * 查询所有贷款客户数据信息根据参数
     * 
     * @param paramMap
     * @return
     * @see com.banger.mobile.facade.loan.LnLoanService#getAllLoanByConds(java.util.Map)
     */
    public List<LnLoan> getAllLoanByConds(Map<String, Object> paramMap) {
        return lnLoanDao.getAllLoanByConds(paramMap);
    }

    /**
     * 分页查询贷款列表
     * 
     * @param parameterMap
     * @param page
     * @return
     */
    public PageUtil<LnLoan> getLnLoanListPage(Map<String, Object> parameterMap, Page page) {
        return lnLoanDao.getLnLoanListPage(parameterMap, page);
    }

    @Override
    public LnLoan selectLoanById(Integer loanId) {
        return lnLoanDao.selectLoanById(loanId);
    }

    /**
     * 根据id查找
     * 
     * @param loanId
     * @return
     */
    public LnLoan getLnLoanById(Integer loanId) {
        return lnLoanDao.getLnLoanById(loanId);
    }

    public void updateLnLoanById(Map<String, Object> paramMap) {
        lnLoanDao.updateLnLoanById(paramMap);
    }

    /**
     * 批量更改更新贷款主表
     *
     * @param paramMapList
     */
    public void updateLnLoanByIdBatch(List<Map<String, Object>> paramMapList){
        lnLoanDao.updateLnLoanByIdBatch(paramMapList);
    }

    /**
     * 申请贷款撤销：将贷款状态改为待撤销
     * 
     * @param paramMap
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void cancelLoan(Map<String, Object> paramMap, LnOpHistory lnOpHistory) {
        this.updateLnLoanById(paramMap);
        lnOpHistoryService.insertLnOpHistory(lnOpHistory);
    }

    /**
     * 根据参数得到模糊查找的客户数据
     * 
     * @param conds
     * @return
     * @see com.banger.mobile.facade.loan.LnLoanService#getAllCustomerBeanByConds(java.util.Map)
     */
    public List<LnLoanCustomerBean> getAllCustomerBeanByConds(Map<String, Object> conds) {
        return lnLoanDao.getAllCustomerBeanByConds(conds);
    }

    /**
     * 得到贷款 NEXT SEQ 值
     * 
     * @return
     * @see com.banger.mobile.facade.loan.LnLoanService#getNextLoanId()
     */
    public Integer getNextLoanId() {
        return lnLoanDao.getNextLoanId();
    }

    /**
     * 批量分配
     * 
     * @param list
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void allotLoanBatch(List<Map<String, Object>> list, Map<String, String> cusParamMap,
                               List<LnOpHistory> lnOpHistoryList) {
        // 分配贷款
        lnLoanDao.updateLnLoanBatch(list);
        // 将贷款关联客户归属到被分配者
        // crmCustomerDao.updateCrmCustomerBatch(customerList);
        crmCustomerDao.modifyCusBelongTo(cusParamMap);
        // 贷款操作历史记录(批量)
        lnOpHistoryService.insertLnOpHistoryBatch(lnOpHistoryList);
    }

    /**
     * 单个分配贷款
     * 
     * @param paramMap
     * @param customer
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void allotLoanSingle(Map<String, Object> paramMap, CrmCustomer customer,
                                LnOpHistory lnOpHistory) {
        // 分配贷款
        lnLoanDao.updateLnLoanById(paramMap);
        //更新备注
        LnLoanInfo lnLoanInfo = new LnLoanInfo();
        lnLoanInfo.setLoanId((Integer)paramMap.get("loanId"));
        lnLoanInfo.setAssignRemark((String) paramMap.get("assignRemark"));
        lnLoanInfoDao.updateLnLoanInfo(lnLoanInfo);
        // 将贷款关联客户归属到被分配者
        Map<String, String> cusParamMap = new HashMap<String, String>();
        cusParamMap.put("belongDeptId", String.valueOf(customer.getBelongDeptId()));
        cusParamMap.put("belongUserId", String.valueOf(customer.getBelongUserId()));
        cusParamMap.put("cusIds", String.valueOf(customer.getCustomerId()));
        crmCustomerDao.modifyCusBelongTo(cusParamMap);
        // 贷款操作历史记录
        lnOpHistoryService.insertLnOpHistory(lnOpHistory);
    }

    /**
     * 将待调查贷款提交审批
     * 
     * @param paramMap
     * @param lnOpHistory
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void submitApproval(LnLoan lnLoan, LnOpHistory lnOpHistory) {
        // 提交审批(将贷款状态改为：4(待审批))
        lnLoanDao.updateLoanByLoanId(lnLoan);
        // 添加一条贷款操作历史记录
        lnOpHistoryService.insertLnOpHistory(lnOpHistory);
    }
    /**
     * 将待调查贷款提交审批
     *
     * @param paramMap
     * @param lnOpHistory
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void submit(String fileAttachments,CustomerData data,Integer dataCustomerId,Integer userId,Map<String, Object> paramMap, LnOpHistory lnOpHistory) {
        //更改贷款资料表数据
//        customerDataService.updateCustomerData(data);
        //添加附件到数据库
//        this.addAttachments(fileAttachments,dataCustomerId,userId);
        // 提交审批(将贷款状态改为：4(待审批))
        lnLoanDao.updateLnLoanById(paramMap);
        // 添加一条贷款操作历史记录
        lnOpHistoryService.insertLnOpHistory(lnOpHistory);
    }

    /**
     * 根据LoanId得到当前贷款状态id
     * 
     * @param paramMap
     * @return
     */
    public Integer getLoanStatusById(Map<String, Object> paramMap) {
        return lnLoanDao.getLoanStatusById(paramMap);
    }

    public Integer queryChangedLoanStatusCount(Map<String, Object> paramMap) {
        return lnLoanDao.queryChangedLoanStatusCount(paramMap);
    }

    /**
     * 按条件查看贷款总数
     * 
     * @author zhangfp
     * @param paramMap
     * @return
     */
    public Integer selectLoanCount(Map<String, Object> paramMap) {
        return lnLoanDao.selectLoanCount(paramMap);
    }

    /**
     * 按条件查出唯一符合的贷款
     * 
     * @param paramMap
     * @return
     */
    public LnLoan selectLoanList(Map<String, Object> paramMap) {
        return lnLoanDao.selectLoanList(paramMap);
    }

    /**
     * 查询所有符合条件的贷款
     * 
     * @param paramMap
     * @return
     */
    public List<LnLoan> selectAllLoanList(Map<String, Object> paramMap) {
        return lnLoanDao.selectAllLoanList(paramMap);
    }

    /**
     * 查看用户所能审批的最大贷款额度
     * 
     * @param userId
     *            用户id(优先查询)
     * @param roleIds
     *            角色id(其次查询)
     * @return 贷款额度
     */
    public BigDecimal approveLimitMoney(Integer userId, List<Integer> roleIds) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("userId", userId);
        // 首先，查询角色审批额度设置表
        Integer limitCountUser = lnApproveLimitUserService.selectCountByUserId(paramMap);
        if (limitCountUser.intValue() == 1) {
            LnApproveLimitUser limitUser = lnApproveLimitUserService.getLimitUserByUserId(paramMap);
            return limitUser.getLimitMoney();
        } else if (limitCountUser.intValue() == 0) {
            // 当以用户来查询最大限制额度的时候，没有找到，接下来就以角色来查询
            paramMap.put("roleIds", roleIds);
            Integer limitCountRole = lnApproveLimitRoleService.selectCountByRoleId(paramMap);
            if (limitCountRole.intValue() > 0) {
                List<LnApproveLimitRole> limitRoles = lnApproveLimitRoleService
                    .getLimitRoleByRoleId(paramMap);
                return limitRoles.get(0).getLimitMoney();
            } else if (limitCountRole.intValue() == 0) {
                return null;
            }
        }
        return null;
    }

    /**
     * 审批贷款
     * 
     * @param paramMap
     * @param lnOpHistory
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void approveLoan(Map<String, Object> paramMap, LnOpHistory lnOpHistory) {
        // 审批贷款(将贷款状态改为：5(未放贷))
        lnLoanDao.updateLnLoanById(paramMap);
        // 添加一条贷款操作历史记录
        lnOpHistoryService.insertLnOpHistory(lnOpHistory);
    }

    /**
     * 审批贷款
     * 
     * @param fileAttachments
     * @param data
     * @param datCustomerDataId
     * @param userId
     * @param paramMap
     * @param lnOpHistory
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void approve(String fileAttachments, CustomerData data, Integer datCustomerDataId, Integer userId, Map<String, Object> paramMap, LnOpHistory lnOpHistory) {
//        customerDataService.updateCustomerData(data);
//        this.addAttachments(fileAttachments,datCustomerDataId,userId);
        // 审批贷款(将贷款状态改为：5(未放贷))
        lnLoanDao.updateLnLoanById(paramMap);
        // 添加一条贷款操作历史记录
        lnOpHistoryService.insertLnOpHistory(lnOpHistory);
    }

    /**
     * pad端 添加贷款
     * 
     * @param lnLoan
     * @param coBorrowerList
     * @param guarantorList
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void addLoanForPad(LnLoan lnLoan, LnLoanInfo lnLoanInfo, LnOpHistory lnOpHistory) {
        lnLoanDao.insertLoan(lnLoan);
        lnLoanInfoDao.insertLnLoanInfo(lnLoanInfo);
        lnOpHistoryService.insertLnOpHistory(lnOpHistory);

        // 预先建好客户资料各个阶段数据
        for (int i = 1; i <=7; i++) {
            CustomerData data = new CustomerData();
            data.setLoanId(lnLoan.getLoanId());
            data.setCustomerId(lnLoanInfo.getCustomerId());
            data.setUploadUserId(lnLoan.getApplyUserId());
            data.setEventId(i);
            customerDataService.addNewCustomerData(data);
        }
    }

    /**
     * pad端 编辑贷款
     * 
     * @param loanParamMap
     * @param coBorrowerList
     * @param guarantorList
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void editLoanForPad(Map<String, Object> loanParamMap,
                               List<LnLoanCoBorrower> coBorrowerList,
                               List<LnLoanGuarantor> guarantorList,Boolean isNew,Integer userId) {

        
        Integer loanStatusId = (Integer)loanParamMap.get("loanStatusId");
        Integer eventId = null;
        if (loanStatusId.equals(1)){
            eventId = LoanConstants.LOAN_APPLY_EVENT;
        }else if (loanStatusId.equals(3)){
            eventId = LoanConstants.LOAN_EXAM_EVENT;
        }
        if (isNew){
            //将原客户的资料全部移到当前客户
            Integer oldCustomerId = (Integer)loanParamMap.get("oldCustomerId");
            if (oldCustomerId != null){
                Map<String,Object> param = new HashMap<String, Object>();
                param.put("oldCustomerId",oldCustomerId);
                param.put("loanId",loanParamMap.get("loanId"));
                param.put("customerId",loanParamMap.get("customerId"));
                param.put("eventId",eventId);
                customerDataService.updateDatCustomerData(param);
            }
        }

        //update start by yuanme 2014-7-21
        // 插入贷款历史操作记录
        Integer loanId = (Integer) loanParamMap.get("loanId");
        LnLoan l = lnLoanDao.getLnLoanById(loanId);
        LnOpHistory lnOpHistory = new LnOpHistory();
        lnOpHistory.setUserId(userId);
        lnOpHistory.setOpHistoryDate(new Date());
        lnOpHistory.setBeforeStatusId(l.getLoanStatusId());
        lnOpHistory.setAfterStatusId(loanStatusId);
        lnOpHistory.setContent("PAD端编辑贷款");
        lnOpHistory.setLoanId(loanId);
        if (l.getLoanStatusId() != null && loanId != null && !l.getLoanStatusId().equals(loanStatusId)) {
            //如果贷款编辑时前后状态有改变，则记录日志
            lnOpHistoryService.insertLnOpHistory(lnOpHistory);
        }
        //update end by yuanme 2014-7-21

        lnLoanDao.updateLnLoanById(loanParamMap);
//        lnLoanCoBorrowerService.deleteCoBorrowerById((Integer) loanParamMap.get("loanId"));
//        lnLoanGuarantorService.deleteLoanGuarantorById((Integer) loanParamMap.get("loanId"));
//
//        for (LnLoanCoBorrower lnLoanCoBorrower : coBorrowerList){
//            if (lnLoanCoBorrower.getOldCustomerId().equals(lnLoanCoBorrower.getCustomerId())){
//                //更新共同借贷人，这里主要更新DAT_CUSTOMER_DATA表中的相关记录
//            }else {
//                //新增共同借贷人
//                Map<String,Object> paramMap = new HashMap<String, Object>();
//                paramMap.put("loanId",lnLoanCoBorrower.getLoanId());
//                paramMap.put("customerId",lnLoanCoBorrower.getOldCustomerId());
//                paramMap.put("eventId",eventId);
//                Integer customerDataId = customerDataService.getCustomerDataId(paramMap);
//                CustomerData customerData = new CustomerData();
//                if (customerDataId != null && customerDataId > 0){
//                    customerData.setLoanId(lnLoanCoBorrower.getLoanId());
//                    customerData.setCustomerId(lnLoanCoBorrower.getCustomerId());
//                    customerData.setCustomerDataId(customerDataId);
//                    customerDataService.updateCustomerData(customerData);
//                }else {
//                    customerData.setLoanId(lnLoanCoBorrower.getLoanId());
//                    customerData.setCustomerId(lnLoanCoBorrower.getCustomerId());
//                    customerData.setEventId(eventId);
//                    customerData.setUploadUserId(userId);
//                    customerDataService.saveCustomerData(customerData);
//                }
//            }
//            lnLoanCoBorrowerService.addLnLoanCoBorrower(lnLoanCoBorrower);
//        }
        if (guarantorList.size() > 0){

            List<Integer> allCusIdsOnLoan = lnLoanGuarantorService.getCusIdListByLoanId((Integer) loanParamMap.get("loanId"));
            //未删除的担保人
            List<Integer> noDelCustomerIdList = new ArrayList<Integer>();
            for (LnLoanGuarantor lnLoanGuarantor : guarantorList){
                noDelCustomerIdList.add(lnLoanGuarantor.getCustomerId());
                if (lnLoanGuarantor.getOldCustomerId().equals(lnLoanGuarantor.getCustomerId())){
                    //更新担保人(担保人还是原来的担保人)
                }else {

                    if (allCusIdsOnLoan != null && allCusIdsOnLoan.size() > 0){
                        Map<String,Object> temMap = new HashMap<String, Object>();
                        temMap.put("loanId",(Integer) loanParamMap.get("loanId"));

                        if (!lnLoanGuarantor.getOldCustomerId().equals(-1) &&
                                allCusIdsOnLoan.contains(lnLoanGuarantor.getOldCustomerId())){
                            temMap.put("customerId",lnLoanGuarantor.getOldCustomerId());
                            customerDataService.delCustomerData(temMap);
                            lnLoanGuarantorService.delGuaByLoanCustomerId(temMap);
                        }else if (lnLoanGuarantor.getOldCustomerId().equals(-1)&&
                                allCusIdsOnLoan.contains(lnLoanGuarantor.getCustomerId())){
                            temMap.put("customerId",lnLoanGuarantor.getCustomerId());
                            customerDataService.delCustomerData(temMap);
                            lnLoanGuarantorService.delGuaByLoanCustomerId(temMap);
                        }
                    }

                    //更换担保人了(换成其他客户了)
                    Map<String,Object> paramMap = new HashMap<String, Object>();
                    paramMap.put("loanId",lnLoanGuarantor.getLoanId());
                    paramMap.put("customerId",lnLoanGuarantor.getCustomerId());
                    paramMap.put("eventId",eventId);
                    Integer customerDataId = customerDataService.getCustomerDataId(paramMap);
                    CustomerData customerData = new CustomerData();
                    if (customerDataId != null && customerDataId > 0) {
                        customerData.setLoanId(lnLoanGuarantor.getLoanId());
                        customerData.setCustomerDataId(customerDataId);
                        customerData.setCustomerId(lnLoanGuarantor.getCustomerId());
                        customerDataService.updateCustomerData(customerData);
                    }else {
                        //新增担保人
                        //新增共同借贷人
                        customerData.setLoanId(lnLoanGuarantor.getLoanId());
                        customerData.setCustomerId(lnLoanGuarantor.getCustomerId());
                        customerData.setEventId(eventId);
                        customerData.setUploadUserId(userId);
                        customerDataService.addNewCustomerData(customerData);
                    }
                    lnLoanGuarantorService.delGuaByLoanCustomerId(paramMap);
                    lnLoanGuarantorService.addLnLoanGuarantor(lnLoanGuarantor);
                }
            }
            //删除除未删除以外的担保人
            Map<String,Object> delGuaMap = new HashMap<String, Object>();
            delGuaMap.put("loanId",(Integer) loanParamMap.get("loanId"));
            delGuaMap.put("customerIdList",noDelCustomerIdList);
            lnLoanGuarantorService.delDeletedGuaByCusList(delGuaMap);
        }else if (guarantorList.size() == 0){
            //担保人都已经删除了
            lnLoanGuarantorService.deleteLoanGuarantorById((Integer) loanParamMap.get("loanId"));
        }
//        lnLoanCoBorrowerService.addLnLoanCoBorrowerBatch(coBorrowerList);
//        lnLoanGuarantorService.addLnLoanGuarantorBatch(guarantorList);
    }
    
    @Transactional(propagation = Propagation.REQUIRED)
    public void editLoanForPad (Map<String, Object> loanParamMap, LnLoanInfo lnLoanInfo, LnOpHistory lnOpHistory){
    	lnLoanDao.updateLnLoanById(loanParamMap);
        lnLoanInfoDao.updateLnLoanInfo(lnLoanInfo);
        lnOpHistoryService.insertLnOpHistory(lnOpHistory);
    }
    /**
     * 落实贷款
     * 
     * @param loanParamMap
     * @param lnOpHistory
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void checkoutLoan(Map<String, Object> loanParamMap, LnOpHistory lnOpHistory) {
        // 更改贷款
        lnLoanDao.updateLnLoanById(loanParamMap);
        // 插入历史记录
        lnOpHistoryService.insertLnOpHistory(lnOpHistory);
    }

    /**
     * 设为不良客户
     * 
     * @param loanParamMap
     * @param lnOpHistory
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void setNogoodCustomer(Map<String, Object> loanParamMap, LnOpHistory lnOpHistory, Map<String, Object> customerParamMap) {
        // 更新贷款
        lnLoanDao.updateLnLoanById(loanParamMap);
        // 插入历史记录
        lnOpHistoryService.insertLnOpHistory(lnOpHistory);
        // 更新客户表 客户为不良客户
        crmCustomerDao.updateCrmCustomerIsNogood(customerParamMap);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void editRepaymentStatus(Map<String, Object> loanParamMap,
                                    Map<String, Object> eLoanParamMap, LnOpHistory lnOpHistory) {
        // 更新贷款
        lnLoanDao.updateLnLoanById(loanParamMap);
        // 更新异常还款计划
        lnExceptionRepaymentPlanService.updateExceptionRepaymentPlan(eLoanParamMap);
        // 插入历史记录
        lnOpHistoryService.insertLnOpHistory(lnOpHistory);
    }

    /**
     * 分配异常催收贷款
     * 
     * @param dunAssign
     * @param opHistory
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void assignExpDunLoan(List<LnExceptionDunAssign> dunAssign, List<LnOpHistory> opHistory) {
        lnExceptionDunAssignDao.insertExpDunAssignBatch(dunAssign);
        lnOpHistoryService.insertLnOpHistoryBatch(opHistory);
    }

    /**
     * 重新分配异常催收贷款
     * 
     * @param loanIdList
     * @param dunAssignList
     * @param lnOpHistoryList
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void reAssignExpDunLoan(List<Integer> loanIdList,
                                   List<LnExceptionDunAssign> dunAssignList,
                                   List<LnOpHistory> lnOpHistoryList) {
        // 先删除已经分配了的异常催收贷款
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("loanIds", loanIdList);
        lnExceptionDunAssignDao.deleteAssignLoanByLoanId(paramMap);
        // 再重新向ln_exception_dun_assign表中添加记录
        lnExceptionDunAssignDao.insertExpDunAssignBatch(dunAssignList);
        // 添加历史操作记录
        lnOpHistoryService.insertLnOpHistoryBatch(lnOpHistoryList);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void editPaidMoney(Map<String, Object> loanParamMap, Map<String, Object> eLoanParamMap) {
        // 更新贷款
        lnLoanDao.updateLnLoanById(loanParamMap);
        // 更新异常还款计划
        lnExceptionRepaymentPlanService.updateExceptionRepaymentPlanBySortno(eLoanParamMap);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void editPaidPrincipal(Map<String, Object> loanParamMap,Map<String, Object> eLoanParamMap){
        lnLoanDao.updateLnLoanById(loanParamMap);
        // 更新异常还款计划
        lnExceptionRepaymentPlanService.updateExceptionRepaymentPlan(eLoanParamMap);
    }

    /**
     * 根据传入的客户列表，查出没有贷款的的客户列表
     * 
     * @author zhangfp
     * 
     * @param
     * @return 未贷款客户ID字符连接串
     */
    public String getNoLoanUserList(String customerIds) {
        List<Integer> customerIdList = new ArrayList<Integer>();
        if (StringUtils.isNotEmpty(customerIds)) {
            String[] customerIdArr = customerIds.split(",");
            for (String customerId : customerIdArr) {
                customerIdList.add(Integer.parseInt(customerId));
            }
        }
        Map<String, Object> paramMap = new HashMap<String, Object>();
//        List<Integer> loanStatusIdList = new ArrayList<Integer>();
//        loanStatusIdList.add(1);
//        loanStatusIdList.add(2);
//        loanStatusIdList.add(3);
//        loanStatusIdList.add(4);
//        loanStatusIdList.add(5);
//        loanStatusIdList.add(6);
//        loanStatusIdList.add(8);
//        paramMap.put("loanStatusIds", loanStatusIdList);
        paramMap.put("customerIds", customerIds);
        // 有贷款的用户Id
        List<Integer> cusIdList = lnLoanDao.getLoanUserList(paramMap);
        Integer tempInt = null;
        for (Integer cusId : cusIdList) {
            if (tempInt == null) {
                tempInt = cusId;
            } else {
                if (tempInt.intValue() == cusId.intValue()) {
                    continue;
                } else if (tempInt.intValue() != cusId.intValue()) {
                    tempInt = cusId;
                }
            }
            customerIdList.remove(cusId);
        }

        StringBuilder sb = new StringBuilder();
        for (Integer customerId : customerIdList) {
            sb.append(customerId + ",");
        }
        String result = "";
        if (sb.length() > 0) {

            result = sb.substring(0, sb.length() - 1);
        }
        return result;
    }

    // 添加审计备注
    @Transactional(propagation = Propagation.REQUIRED)
    public void addVerifyRemark(Map<String, Object> param, LnVerifyHistory lnVerifyHistory) {
        // 更新审计备注
        lnLoanDao.updateLnLoanById(param);

        // 插入审计历史记录
        lnVerifyHistoryService.insertLnVerifyHistory(lnVerifyHistory);
    }

    /**
     * 分頁查詢貸款信息--小頁卡
     * 
     * @param paramMap
     * @param page
     * @return
     */
    public PageUtil<LnLoan> getAllLoanByCusId(Map<String, Object> paramMap, Page page) {
        return lnLoanDao.getAllLoanByCusId(paramMap, page);
    }

    /**
     * 根據客戶ID集查詢客戶相关的贷款信息
     * 
     * @param cusIds
     * @return
     */
    public List<LnLoan> getAllLoanByCusIds(String cusIds) {
        if (StringUtils.isNotEmpty(cusIds)) {
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("customerIds", cusIds);
            return lnLoanDao.getAllLoanByCusIds(paramMap);
        }
        return null;
    }

    /**
     * 合并客户贷款
     * 
     * @param needMergeCusIds
     *            需要合并的客户ID
     * @param mergeEndCusId
     *            合并之后的客户ID
     */
    public void mergeCusLoan(String needMergeCusIds, String mergeEndCusId) {
        if (StringUtils.isNotEmpty(needMergeCusIds) && StringUtils.isNotEmpty(mergeEndCusId)) {
            String[] cusIdArr = needMergeCusIds.split(",");
            List<Integer> cusIdList = null;
            if (cusIdArr != null) {
                cusIdList = new ArrayList<Integer>();
                for (String cusId : cusIdArr) {
                    cusIdList.add(Integer.parseInt(cusId));
                }
            }
            Integer afterCusId = Integer.parseInt(mergeEndCusId);
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("afterMergeCusId", afterCusId);
            paramMap.put("beforeMergeCusIds", cusIdList);

            // 合并客户贷款
            lnLoanDao.mergeCusLoan(paramMap);
        }
    }

    /**
     * 提交贷款申请 (pad端使用)
     * 
     * @param loanParamMap
     * @param lnOpHistory
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void applyLoan(Map<String, Object> loanParamMap, LnOpHistory lnOpHistory) {
        // 更改贷款主表信息
        lnLoanDao.updateLnLoanById(loanParamMap);
        // 插入历史操作记录信息
        lnOpHistoryService.insertLnOpHistory(lnOpHistory);
    }

    /**
     * 根据客户ID查询出，当前客户最近一笔贷款的状态
     * 
     * @param customerIds
     * @return
     */
    public Map<Integer, String> getRecentlyLoanStatusByCusIds(String customerIds) {
    	//TODO:修改
    	//return null;
    
        if (StringUtils.isNotEmpty(customerIds)) {
            String[] cusIdStr = customerIds.split(",");
            List<Integer> cusIdList = null;
            if (cusIdStr != null && cusIdStr.length > 0) {
                cusIdList = new ArrayList<Integer>();
                for (String cusId : cusIdStr) {
                    cusIdList.add(Integer.parseInt(cusId));
                }
            }
            if (cusIdList != null && cusIdList.size() > 0) {
                Map<String, Object> paramMap = new HashMap<String, Object>();
                paramMap.put("customerIds", cusIdList);
                List<LnLoan> resultList = lnLoanDao.getRecentlyLoanStatusByCusIds(paramMap);

                Map<Integer, String> resultMap = new HashMap<Integer, String>();
                for (LnLoan lnLoan : resultList) {
                    resultMap.put(lnLoan.getCustomerId(), lnLoan.getLoanStatusName());
                }
                return resultMap;
            }
        }
        return null;
    }

    /**
     * 同步贷款(事务)
     * 
     * @param paramMap
     * @param lnOpHistory
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void syncLoan(Map<String, Object> paramMap, LnOpHistory lnOpHistory) {
        this.updateLnLoanById(paramMap);
        lnOpHistoryService.insertLnOpHistory(lnOpHistory);
    }

    public Integer getLoanCountByCusId(Integer customerId) {
        Map<String ,Object> paras = new HashMap<String, Object>();
        paras.put("customerId", customerId);
        return lnLoanDao.getLoanCountByCusId(paras);
    }

    //统计贷款任务进度
    public Integer getLoanCountByTask(Map<String, Object> param){
        return lnLoanDao.getLoanCountByTask(param);
    }

    /**
     * 根据贷款状态，得到相应的贷款状态贷款数
     *
     * @param paramMap
     * @return
     */
    public List<LnLoan> getCountByStatus(Map<String, Object> paramMap){
        return lnLoanDao.getCountByStatus(paramMap);
    }

    /**
     * 查询各个贷款状态下的贷款总数(控制台使用)
     * 
     * @param userId
     *            登录用户ID
     * @return
     */
    public Map<Integer,Integer> getLoanCountByLoanStatus(Integer userId){
        Map<String,Object> parameterMap = new HashMap<String, Object>();
        //为了查询出待审批贷款的总数，必须计算出当前用户所归属机构
        SysUser sysUser = sysUserService.getSysUserById(userId);
        // 是否是业务主管
        Boolean isInChargeOf = deptFacadeService.isInChargeOfDepartment();
        if (isInChargeOf||deptFacadeService.isInManOfDepartment()) {
            // 当前用户的下属用户
            String sysRoles = sysUserService.getRoleIds(userId);
            String[] roleArr = null;
            if (StringUtils.isNotEmpty(sysRoles)){
                roleArr = sysRoles.split(",");
            }
            String belongUserIds = "";
            List<String> belongUserId = sysTeamUserService.getUserIdsByChiefUserId(userId);
			int i = 0;
			for (String str : belongUserId) {
				if (i == 0) {
					belongUserIds += str;
					i = 1;
				} else {
					belongUserIds += "," + str;
				}
			}
            parameterMap.put("userIds",belongUserIds);
        }else {
        	if(deptFacadeService.isCommon()){  // 
        		//客户经理
                parameterMap.put("userId",sysUser.getUserId());
                parameterMap.put("approvalDeptId",sysUser.getDeptId());
         	}else if(deptFacadeService.isInManOfDepartment()){
         		
         		//后台
         		 parameterMap.put("userId3",sysUser.getUserId());
                 parameterMap.put("approvalDeptId2",sysUser.getDeptId());
         	}else{
         		//、审批 、
         		 parameterMap.put("userId2",sysUser.getUserId());
         	}
            
        }
//        List<LnLoan> loanList = lnLoanDao.getLoanCountByLoanStatusId(parameterMap);
        List<LnLoan> loanList = lnLoanDao.getLoanCountByLoanStatusIdOptimize(parameterMap);
        //返回结果 map
        Map<Integer,Integer> resultMap = new HashMap<Integer, Integer>();
        if(loanList != null && loanList.size() > 0){
            for (LnLoan lnLoan : loanList){
                resultMap.put(lnLoan.getLoanStatusId(),lnLoan.getStatusCount());
            }
        }
        return resultMap;
    }

    /**
     * 查询各个贷款状态下的贷款总数(控制台使用)
     *
     * @param userId
     *            登录用户ID
     * @return
     */
    public Map<Integer,Integer> getLoanCountByLoanStatus(Integer userId, Integer loanStatusId){
        if (loanStatusId != null){
            Map<String,Object> parameterMap = new HashMap<String, Object>();
            //为了查询出待审批贷款的总数，必须计算出当前用户所归属机构
            SysUser sysUser = sysUserService.getSysUserById(userId);
            // 是否是业务主管
            Boolean isInChargeOf = deptFacadeService.isInChargeOfDepartment();
            if (isInChargeOf) {
                String sysRoles = sysUserService.getRoleIds(userId);
                String[] roleArr = null;
                if (StringUtils.isNotEmpty(sysRoles)){
                    roleArr = sysRoles.split(",");
                }
                // 当前用户的下属用户
                String belongUserIds = deptFacadeService.getUserIdsBelongToManager(roleArr,"loanInfo");
                parameterMap.put("userIds",belongUserIds);
            }else {
                //客户经理
                parameterMap.put("userId",userId);
            }
            List<LnLoan> loanList = lnLoanDao.getLoanCountByLoanStatusId(parameterMap);
            //返回结果 map
            Map<Integer,Integer> resultMap = new HashMap<Integer, Integer>();
            if(loanList != null && loanList.size() > 0){
                for (LnLoan lnLoan : loanList){
                    if (lnLoan.getLoanStatusId().equals(loanStatusId)){
                        resultMap.put(lnLoan.getLoanStatusId(),lnLoan.getStatusCount());
                        break;
                    }
                }
            }
            return resultMap;
        }
        return null;
    }

    /**
     * 根据贷款用户和贷款状态得到相关状态下的贷款的数量
     *
     * @param paramMap
     * @return
     */
    public List<LnLoan> getCountByLoanStatusId(Map<String, Object> paramMap){
        return lnLoanDao.getCountByLoanStatusId(paramMap);
    }

    /**
     * 查出每个状态下的贷款总数
     *
     * @param paramMap
     * @return
     */
    public Integer getLoanCountByLoanStatusId(Map<String, Object> paramMap){
        return null;
//        return lnLoanDao.getLoanCountByLoanStatusId(paramMap);
    }

    /**
     * 全部状态列表
     *
     * @param userId
     * @return
     */
    public List<LnLoan> getAllLoanByUserId(Integer userId){

        Map<String,Object> parameterMap = new HashMap<String, Object>();
        parameterMap.put("isAllLoan",1);  //确定是查询所有的申请贷款
        //是否是业务主管
        Boolean isInChargeOf = deptFacadeService.isInChargeOfDepartment();
        if (isInChargeOf) {
            // 当前用户的下属用户
            String sysRoles = sysUserService.getRoleIds(userId);
            String[] roleArr = null;
            if (StringUtils.isNotEmpty(sysRoles)){
                roleArr = sysRoles.split(",");
            }
            String belongUserIds = deptFacadeService.getUserIdsBelongToManager(roleArr,"loanInfo");
            parameterMap.put("managerUserIds", belongUserIds); // 当前用户所管理的提交申请用户
        } else {
            // 客户经理
            parameterMap.put("allLoanUserId", userId);
        }
        parameterMap.put("startRow",1);
        parameterMap.put("endRow",10);
//        return lnLoanDao.getAllLoanByUserId(parameterMap);
        return lnLoanDao.getWPLoanListOptimize(parameterMap);
    }

    /**
     * 得到每种贷款下的贷款列表(控制台使用)
     * 
     * @param userId
     * @param status
     * @return
     */
    public List<LnLoan> getLoanListByStatus(Integer userId, Integer status){
        Map<String,Object> paramMap = new HashMap<String, Object>();

        //是否是业务主管
        Boolean isInChargeOf = deptFacadeService.isInChargeOfDepartment();
        paramMap.put("loanStatusId",status - 1);
        if (isInChargeOf) {
            String sysRoles = sysUserService.getRoleIds(userId);
            String[] roleArr = null;
            if (StringUtils.isNotEmpty(sysRoles)){
                roleArr = sysRoles.split(",");
            }
            // 当前用户的下属用户
            String belongUserIds = deptFacadeService.getUserIdsBelongToManager(roleArr,"loanInfo");
            if(status.equals(2)){
                //待提交
                paramMap.put("createUsers",belongUserIds);
            }else if(status.equals(3)) {
                //待分配
                paramMap.put("applyUserIds",belongUserIds);
            }else {
                paramMap.put("surveyUserIds",belongUserIds);
            }
//            else if(status.equals(4) || status.equals(6) || status.equals(7)){
//                //待提交、未放贷、还款中
//                paramMap.put("surveyUserIds",belongUserIds);
//            }else if(status.equals(5)){
//                //待审批
//                SysUser sysUser = sysUserService.getSysUserById(userId);
//                Integer[] belongDeptIds = deptFacadeService.getInChargeOfDeptIds();
//                paramMap.put("belongDeptIds",belongDeptIds);
//            }
        } else {
            // 客户经理
            if(status.equals(2)){
                //待提交
                paramMap.put("createUser",userId);
            }else if(status.equals(3)){
                //待分配
                paramMap.put("applyUserId",userId);
            }else if(status.equals(4) || status.equals(5) || status.equals(6) || status.equals(7)){
                //待提交、未放贷、还款中
                paramMap.put("surveyUserId",userId);
            }
//            else if(status.equals(5)){
//                //待审批
//                SysUser sysUser = sysUserService.getSysUserById(userId);
////                paramMap.put("approvalDeptId",sysUser.getDeptId());
//                paramMap.put("allLoanUserId",sysUser.getUserId());
//            }
        }

        paramMap.put("loanStatusId",status - 1);
        paramMap.put("startRow",1);
        paramMap.put("endRow",10);
//        List<LnLoan> loanList = lnLoanDao.getAllLoanByUserId(paramMap);
        List<LnLoan> loanList = lnLoanDao.getWPLoanListOptimize(paramMap);
        return loanList;
    }

    /**
     * 得到三个月内客户的审批失败的贷款记录数
     * 
     * @param paramMap
     * @return
     */
    public Boolean isApproveFail(Map<String, Object> paramMap){
        Integer count = lnLoanDao.getApproveFailCount(paramMap);
        if (count > 0){
            return true;
        }
        return false;
    }

    /**
     * 得到要立馬操作的貸款列表
     * 
     * @param paramMap
     * @param hour
     * @return
     */
    public List<LnLoan> getNowNeedHandleLoanList(Map<String, Object> paramMap, Integer hour,Integer afterHour){
        Calendar nowCal = Calendar.getInstance();
        nowCal.add(Calendar.HOUR_OF_DAY,-hour);
        paramMap.put("handleTime",nowCal.getTime());
        if (afterHour != -1){
            nowCal.add(Calendar.HOUR_OF_DAY,-(afterHour - hour));
            paramMap.put("afterTime",nowCal.getTime());
        }
        return lnLoanDao.getNowNeedHandleLoanList(paramMap);
    }

    /**
     * 得到新的需要提醒的贷款
     *
     * @param paramMap
     * @return
     */
    public List<LnLoan> getNewLoanForWarning(Map<String, Object> paramMap){
        return lnLoanDao.getNewLoanForWarning(paramMap);
    }

    /**
     * 催收贷款(上门催收)
     * 
     * @param lnLoanMap
     * @param lnOpHistory
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void dunLoan(Map<String,Object> lnLoanMap, LnOpHistory lnOpHistory){
        lnLoanDao.updateLnLoanById(lnLoanMap);
        lnOpHistoryService.insertLnOpHistory(lnOpHistory);
    }

    /**
     * pad端取得所有客户，在申请贷款中
     * @param conds
     * @param page
     * @return
     */
    public PageUtil<LnLoanCustomerBean> getAllCustomerBeanByConds(Map<String, Object> conds, Page page) {
        return lnLoanDao.getAllCustomerBeanByConds(conds, page);
    }

    /**
     * 批量更新贷款
     * 
     * @param list
     */
    public void updateLoanBatch(List<Map<String, Object>> list){
        lnLoanDao.updateLnLoanBatch(list);
    }

    /**
     * 保存附件数据到数据库
     *
     * @param datCustomerDataId
     * @param userId
     */
    private void addAttachments(String fileAttachments,Integer datCustomerDataId, Integer userId){
        if (!StringUtil.isNullOrEmpty(fileAttachments)) {
            //保存附件
            String[] attItems = fileAttachments.split(":");// "\\|"
            for (String atts : attItems) {
                if (!StringUtil.isNullOrEmpty(atts)) {
                    String[] att = atts.split("\\|");
                    //添加附件到数据库
                    Form datForm = new Form();
                    datForm.setCustomerDataId(datCustomerDataId);
                    datForm.setFileId(Integer.parseInt(att[2]));
                    datForm.setCreateUser(userId);
                    datForm.setUpdateUser(userId);

                    //添加表单附件信息
                    dataFormService.addDatForm(datForm);
                }
            }
        }
    }

    private void addAttachmentsByFileId(String fileIds,Integer datCustomerDataId, Integer userId){
        if (!StringUtil.isNullOrEmpty(fileIds)) {
            String[] fileIdItems = fileIds.split(":");
            for (String fileId : fileIdItems){
                Form datForm = new Form();
                datForm.setCustomerDataId(datCustomerDataId);
                datForm.setFileId(Integer.parseInt(fileId));
                datForm.setCreateUser(userId);
                datForm.setUpdateUser(userId);

                dataFormService.addDatForm(datForm);
            }
        }
    }

    /**
     * 调查拒绝与审批拒绝
     * 
     * @param fileIds
     * @param data
     * @param datCustomerDataId
     * @param userId
     * @param paramMap
     * @param lnOpHistory
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void refuseLoan(String fileIds, CustomerData data, Integer datCustomerDataId, Integer userId, Map<String, Object> paramMap, LnOpHistory lnOpHistory) {
        customerDataService.updateCustomerData(data);
        this.addAttachmentsByFileId(fileIds,datCustomerDataId,userId);
        this.updateLnLoanById(paramMap);
        lnOpHistoryService.insertLnOpHistory(lnOpHistory);
    }

    /**
     * 移除共同借贷人以及他的所有资料
     * @param coId
     * @param loanId
     * @param customerId
     */
    @Transactional
    public void rmCoBorrower(Integer coId, Integer loanId, Integer customerId){
        //移除LN_LOAN_CO_BORROWER(共同借贷人表)表中的相关记录
        lnLoanCoBorrowerService.deleteLnLoanCoBorrower(coId);

        Map<String,Object> paramMap = new HashMap<String, Object>();
        paramMap.put("loanId",loanId);
        paramMap.put("customerId",customerId);
        //获得该贷款客户下的资料id
        List<Integer> customerDataIdList = customerDataService.getCustomerDataIdList(paramMap);
        //移除DAT_CUSTOMER_DATA(资料表)表中的相关记录
        customerDataService.delCustomerData(paramMap);
        //移除该贷款客户的所有相关资料：相片、表单、录音、视频等
        //删除照片
        paramMap.put("customerDataIdList",customerDataIdList);
        dataPhotoService.delPhotoById(paramMap);
        //删除录音
        dataAudioService.delAudioById(paramMap);
        //删除视频
        dataVideoService.delVideoById(paramMap);
    }

    /**
     * 移除提保人以及他的所有资料
     * @param guId
     * @param loanId
     * @param customerId
     */
    @Transactional
    public void rmGuarantor(Integer guId, Integer loanId, Integer customerId){
        //移除LN_LOAN_GUARANTOR(担保人表)表中的相关记录
        lnLoanGuarantorService.deleteLnLoanGuarantor(guId);

        Map<String,Object> paramMap = new HashMap<String, Object>();
        paramMap.put("loanId",loanId);
        paramMap.put("customerId",customerId);
        //获得该贷款客户下的资料id
//        List<Integer> customerDataIdList = customerDataService.getCustomerDataIdList(paramMap);
        //移除DAT_CUSTOMER_DATA(资料表)表中的相关记录
        customerDataService.delCustomerData(paramMap);
        //移除该贷款客户的所有相关资料：相片、表单、录音、视频等
        //删除照片
//        paramMap.put("customerDataIdList",customerDataIdList);
//        dataPhotoService.delPhotoById(paramMap);
        //删除录音
//        dataAudioService.delAudioById(paramMap);
        //删除视频
//        dataVideoService.delVideoById(paramMap);
    }

    @Transactional
    public void changeLoanPerson(Integer loanId, Integer oldCustomerId, Integer customerId,LnOpHistory lnOpHistory){

        BaseCrmCustomer oldLoanCustomer = crmCustomerDao.getCrmCustomerById(oldCustomerId);
        BaseCrmCustomer newLoanCustomer = crmCustomerDao.getCrmCustomerById(customerId);

        String oldLoanCustomerName = oldLoanCustomer.getCustomerName();
        String newLoanCustomerName = newLoanCustomer.getCustomerName();

        //验证被更改的客户是否为共同借贷人或担保人，如果是这两者，则相互调换
        Map<String,Object> paramMap = new HashMap<String, Object>();
        paramMap.put("loanId",loanId);
        paramMap.put("customerId",customerId);
        paramMap.put("oldCustomerId",oldCustomerId);
        Integer coBorrowerCount = lnLoanCoBorrowerService.getCoBorrowerCount(paramMap);
        if (coBorrowerCount == 1){
            //将原先贷款人与原共同借贷人信息全部交换
            lnLoanCoBorrowerService.updateCoBorrower(paramMap);
            Map<String,Object> coBorrowerMap = new HashMap<String, Object>();
            coBorrowerMap.put("loanId",loanId);
            coBorrowerMap.put("customerId",customerId);
            List<Integer> coBorrowerCustomerDataIdList = customerDataService.getCustomerDataIdList(coBorrowerMap);

            Map<String,Object> loanMap = new HashMap<String, Object>();
            loanMap.put("loanId",loanId);
            loanMap.put("customerId",oldCustomerId);
            List<Integer> loanCustomerDataIdList = customerDataService.getCustomerDataIdList(loanMap);

            Map<String,Object> pMap = new HashMap<String, Object>();
            if (coBorrowerCustomerDataIdList != null && coBorrowerCustomerDataIdList.size() > 0){
                pMap.put("customerDataIdList",coBorrowerCustomerDataIdList);
                pMap.put("customerId",oldCustomerId);
            }
            customerDataService.updateDatCustomerData(pMap);
            if (loanCustomerDataIdList != null && loanCustomerDataIdList.size() > 0){
                pMap.put("customerDataIdList",loanCustomerDataIdList);
                pMap.put("customerId",customerId);
            }
            customerDataService.updateDatCustomerData(pMap);

            lnOpHistory.setContent("贷款共同借贷人"+newLoanCustomerName+"改为贷款人，原贷款人"+oldLoanCustomerName+"改为共同借贷人");

        }else if (coBorrowerCount == 0){
            Integer guarantorCount = lnLoanGuarantorService.getGuarantorCount(paramMap);
            if (guarantorCount == 1){
                //将原先贷款人与原担保人信息全部交换
                lnLoanGuarantorService.updateLoanGuarantor(paramMap);
                Map<String,Object> guarantorMap = new HashMap<String, Object>();
                guarantorMap.put("loanId",loanId);
                guarantorMap.put("customerId",customerId);
                List<Integer> guarantorCustomerDataIdList = customerDataService.getCustomerDataIdList(guarantorMap);

                Map<String,Object> loanMap = new HashMap<String, Object>();
                loanMap.put("loanId",loanId);
                loanMap.put("customerId",oldCustomerId);
                List<Integer> loanCustomerDataIdList = customerDataService.getCustomerDataIdList(loanMap);

                Map<String,Object> pMap = new HashMap<String, Object>();
                if (guarantorCustomerDataIdList != null && guarantorCustomerDataIdList.size() > 0){
                    pMap.put("customerDataIdList",guarantorCustomerDataIdList);
                    pMap.put("customerId",oldCustomerId);
                }
                customerDataService.updateDatCustomerData(pMap);
                if (loanCustomerDataIdList != null && loanCustomerDataIdList.size() > 0){
                    pMap.put("customerDataIdList",loanCustomerDataIdList);
                    pMap.put("customerId",customerId);
                }
                customerDataService.updateDatCustomerData(pMap);

                lnOpHistory.setContent("贷款担保人"+newLoanCustomerName+"改为贷款人，原贷款人"+oldLoanCustomerName+"改为担保人");
            }else if (guarantorCount == 0){
                //替换原贷款人信息，并将原贷款人所有资料信息关联到当前贷款人
                customerDataService.updateDatCustomerData(paramMap);
                lnOpHistory.setContent("贷款人由" + oldLoanCustomerName + "改为" + newLoanCustomerName);
            }
        }
        lnLoanDao.updateLnLoanById(paramMap);

        //插入历史操作记录
        lnOpHistoryService.insertLnOpHistory(lnOpHistory);
    }

    /**
     * 将上传的附件与贷款信息关联起来
     *
     * @param loanId
     * @param customerId
     * @param fileId
     */
    @Transactional
    public void relatedLoanAttachment(Integer loanId, Integer customerId, Integer fileId, Integer userId,Integer eventId){
        
        Form form = new Form();
        
        Map<String, Object> parameterMap = new HashMap<String, Object>();
        parameterMap.put("loanId", loanId);
        parameterMap.put("customerId", customerId);
        parameterMap.put("eventId", eventId);
        List<CustomerData> datas = customerDataService.getCustomerDataByParameterMap(parameterMap);
        if (datas.size() > 0){
        	CustomerData d = datas.get(0);
        	form.setCustomerDataId(d.getCustomerDataId());
        }else{
        	CustomerData customerData = new CustomerData();
            customerData.setLoanId(loanId);
            customerData.setCustomerId(customerId);
            customerData.setEventId(eventId);
            customerData.setUploadUserId(userId);    
            customerData.setCreateUser(userId);
            customerData.setUpdateUser(userId);
            customerData.setCreateDate(Calendar.getInstance().getTime());
            customerData.setCreateDate(Calendar.getInstance().getTime());
            
            customerDataService.addNewCustomerData(customerData);  
            
            form.setCustomerDataId(customerData.getCustomerDataId());
        }
        
        
        form.setFileId(fileId);       
        form.setCreateUser(userId);
        form.setUpdateUser(userId);

        dataFormService.addDatForm(form);
    }

    /**
     * 查看某个客户的贷款数量
     * 
     * @param customerId
     * @return
     */
    public Integer getCustomerLoanCount(Integer customerId){
        Map<String,Object> paramMap = new HashMap<String, Object>();
        paramMap.put("customerId",customerId);
        return lnLoanDao.getCustomerLoanCount(paramMap);
    }

    /**
     * 得到客户最近申请的贷款
     * @param customerId
     * @return
     */
    public LnLoan getLastLoanByCustomer(Integer customerId){
        return lnLoanDao.getLastLoanByCustomer(customerId);
    }

    public Integer getVerifyAssignCount(Map<String ,Object> paramMap){
        return lnLoanDao.getVerifyAssignCount(paramMap);
    }

    public List<LnLoan> getVerifyAssignList (Map<String ,Object> paramMap){
        return lnLoanDao.getVerifyAssignList(paramMap);
    }

     @Transactional(propagation = Propagation.REQUIRED)
     public void assignVerify(Map<String ,Object> paramMap,List<LnVerifyHistory> lnVerifyHistoryList){
          lnLoanDao.updateLnLoanById(paramMap);
         lnVerifyHistoryService.insertLnVerifyHistoryBatch(lnVerifyHistoryList);
    }

    /**
     * 单独获得各状态下的贷款数量
     *
     * @param loanStatusId
     * @return
     */
    public Integer getLoanCountByStatusId(Integer loanStatusId){
        return lnLoanDao.getLoanCountByStatusId(loanStatusId);
    }

    /**
     * 单独获得各状态下的贷款
     *
     * @param paramMap
     * @return
     */
    public List<LnLoanInfo> getLoanByStatusId(Map<String, Object> paramMap){
        return lnLoanDao.getLoanByStatusId(paramMap);
    }

    public Integer updateLoanPartFieldBatch(List<LnLoan> lnLoanList){
        return lnLoanDao.updateLoanPartFieldBatch(lnLoanList);
    }

    public Boolean isNotEndLoan(Integer customerId){
         Integer count = lnLoanDao.getNotEndLoanCount(customerId);
        if(count>0){
            return true;
        }
        return false;
    }

    public Integer getLoanId(Map<String, Object> param){
        return lnLoanDao.getLoanId(param);
    }

    /**
     * 查出客户的所有贷款
     *
     * @param customerId
     * @return
     */
    public List<LnLoan> getLoanByCustomerId(Integer customerId){
        return lnLoanDao.getLoanByCustomerId(customerId);
    }

    /**
     * 批量更新每个客户的贷款负责人
     *
     * @param mapList
     * @return
     */
    public Integer updateLoanOwnerByCustomerIdBatch(List<Map<String, Object>> mapList){
        return lnLoanDao.updateLoanOwnerByCustomerIdBatch(mapList);
    }

    /**
     * 更新贷款负责人
     * 
     * @param customerId
     */
    public void changeLoanOwner(Integer customerId){
        List<LnLoan> lnLoanList = this.getLoanByCustomerId(customerId);
        if (lnLoanList != null && lnLoanList.size() > 0){
            CrmCustomer crmCustomer = crmCustomerDao.getCrmCustomerById(customerId);
            List<Map<String,Object>> mapList = new ArrayList<Map<String, Object>>();
            for (LnLoan lnLoan : lnLoanList){
                Integer loanStatusId = lnLoan.getLoanStatusId();
                Map<String,Object> paramMap = new HashMap<String, Object>();
                paramMap.put("loanId",lnLoan.getLoanId());
                if (loanStatusId <= LoanConstants.LOAN_ASSIGN_STATUS || loanStatusId.equals(LoanConstants.LOAN_ASSIGN_REFUSE_STATUS)
                        || loanStatusId.equals(LoanConstants.LOAN_ASK_REFUSE_STATUS)){
                    paramMap.put("applyUserId",crmCustomer.getBelongUserId());
                    mapList.add(paramMap);
                }else {
                    paramMap.put("surveyUserId",crmCustomer.getBelongUserId());
                    mapList.add(paramMap);
                }
            }
            this.updateLoanOwnerByCustomerIdBatch(mapList);
        }
    }

    /**
     * 处理贷款客户的权限
     *
     * @param inChargeOf
     * @param belongUserIds
     * @param dataList
     */
    public void proCusAuthority(Boolean inChargeOf, String belongUserIds, PageUtil<LnLoan> dataList, IUserInfo userInfo) {
        if (dataList != null && dataList.getItems().size() > 0){
            if (inChargeOf){
                List<Integer> inChargeDepts = new ArrayList<Integer>();
                String roleIds= StringUtil.getIdsString(userInfo.getRoles());
                boolean flag=specialDataAuthService.getSysDataAuthCondition(roleIds,"loanInfo");
                if(flag){
                    inChargeDepts.add(userInfo.getDeptId());
                }else{
                    Integer[] inChargeDeptsIntegers = deptFacadeService.getInChargeOfDeptIds();
                    for (Integer deptId : inChargeDeptsIntegers) {
                        inChargeDepts.add(deptId);
                    }
                }
                List<Integer> belongUserIdList = new ArrayList<Integer>();
                if (belongUserIds.length() > 0){
                    String[] belongUserIdArr = belongUserIds.split(",");
                    for (String belongUserId : belongUserIdArr){
                        belongUserIdList.add(Integer.parseInt(belongUserId));
                    }
                }
                for (LnLoan lnLoan : dataList.getItems()){
                    Integer belongUserId = lnLoan.getCrmCustomer().getBelongUserId();
                    Integer belongDeptId = lnLoan.getCrmCustomer().getBelongDeptId();
                    if (belongUserId != null && belongUserId != 0){
                        //当前贷款客户存在归属
                        if (belongUserIdList.contains(belongUserId)){
                            lnLoan.setHasPermission(1);
                        }else {
                            lnLoan.setHasPermission(0);
                        }
                    }else if (belongDeptId != null && belongDeptId != 0){
                        //不存在归属人员，只归属于机构
                        if (inChargeDepts.contains(belongDeptId)){
                            lnLoan.setHasPermission(1);
                        }else {
                            lnLoan.setHasPermission(0);
                        }
                    }
                }
            }else {
                //数据权限为我的，那么就只能让归属给我的客户有权限
                for (LnLoan lnLoan : dataList.getItems()){
                    Integer belongUserId = lnLoan.getCrmCustomer().getBelongUserId();
                    if (belongUserId != null && belongUserId != 0){
                        //当前贷款客户存在归属
                        if (userInfo.getUserId().equals(belongUserId)){
                            lnLoan.setHasPermission(1);
                        }else {
                            lnLoan.setHasPermission(0);
                        }
                    }
                }
            }
        }
    }

    /**
     * 处理贷款客户的权限
     *
     * @param inChargeOf
     * @param belongUserIds
     * @param dataList
     */
    public void proCusAuthority(Boolean inChargeOf, String belongUserIds, List<LnLoan> dataList, IUserInfo userInfo) {
        if (dataList != null && dataList.size() > 0){
            if (inChargeOf){
                List<Integer> inChargeDepts = new ArrayList<Integer>();
                String roleIds= StringUtil.getIdsString(userInfo.getRoles());
                boolean flag=specialDataAuthService.getSysDataAuthCondition(roleIds,"loanInfo");
                if(flag){
                    inChargeDepts.add(userInfo.getDeptId());
                }else{
                    Integer[] inChargeDeptsIntegers = deptFacadeService.getInChargeOfDeptIds();
                    for (Integer deptId : inChargeDeptsIntegers) {
                        inChargeDepts.add(deptId);
                    }
                }
                List<Integer> belongUserIdList = new ArrayList<Integer>();
                if (belongUserIds.length() > 0){
                    String[] belongUserIdArr = belongUserIds.split(",");
                    for (String belongUserId : belongUserIdArr){
                        belongUserIdList.add(Integer.parseInt(belongUserId));
                    }
                }
                for (LnLoan lnLoan : dataList){
                    Integer belongUserId = lnLoan.getCrmCustomer().getBelongUserId();
                    Integer belongDeptId = lnLoan.getCrmCustomer().getBelongDeptId();
                    if (belongUserId != null && belongUserId != 0){
                        //当前贷款客户存在归属
                        if (belongUserIdList.contains(belongUserId)){
                            lnLoan.setHasPermission(1);
                        }else {
                            lnLoan.setHasPermission(0);
                        }
                    }else if (belongDeptId != null && belongDeptId != 0){
                        //不存在归属人员，只归属于机构
                        if (inChargeDepts.contains(belongDeptId)){
                            lnLoan.setHasPermission(1);
                        }else {
                            lnLoan.setHasPermission(0);
                        }
                    }
                }
            }else {
                //数据权限为我的，那么就只能让归属给我的客户有权限
                for (LnLoan lnLoan : dataList){
                    Integer belongUserId = lnLoan.getCrmCustomer().getBelongUserId();
                    if (belongUserId != null && belongUserId != 0){
                        //当前贷款客户存在归属
                        if (userInfo.getUserId().equals(belongUserId)){
                            lnLoan.setHasPermission(1);
                        }else {
                            lnLoan.setHasPermission(0);
                        }
                    }
                }
            }
        }
    }

    /**
     * 贷款回退事务
     * 
     * @param paramMap
     * @param opHistory
     */
    public void backLoan(Map<String, Object> paramMap, LnOpHistory opHistory){
        lnLoanDao.updateLnLoanById(paramMap);
        lnOpHistoryService.insertLnOpHistory(opHistory);
    }

    /**
     * =========以下是贷款各列表优化后的相关方法===========
     */

    /**
     * 优化后的所有贷款列表查询
     *
     * @param parameterMap
     * @param page
     * @return
     */
    public PageUtil<LnLoan> getAllLoanByPage(Map<String, Object> parameterMap, Page page){
        return lnLoanDao.getAllLoanByPage(parameterMap,page);
    }

    /**
     * 申请贷款列表和查询
     *
     * @param parameterMap
     * @param page
     * @return
     */
    public PageUtil<LnLoan> getApplyLoanByPage(Map<String, Object> parameterMap, Page page){
        return lnLoanDao.getApplyLoanByPage(parameterMap,page);
    }

    /**
     * 根据条件查询审批贷款的数量
     *
     * @param paramMap
     * @return
     */
    public Integer getAssessLoanCount(Map<String, Object> paramMap){
        return lnLoanDao.getAssessLoanCount(paramMap);
    }

    /**
     * 根据条件查询审批贷款
     *
     * @param paramMap
     * @return
     */
    public LnLoan getAssessLoanAlone(Map<String, Object> paramMap){
        return lnLoanDao.getAssessLoanAlone(paramMap);
    }

    /**
     * 根据条件查询未放贷 及放贷后 贷款
     *
     * @param paramMap
     * @return
     */
    public Integer getMakeLoanCount(Map<String, Object> paramMap){
        return lnLoanDao.getMakeLoanCount(paramMap);
    }

    /**
     * 未放贷 及放贷后 贷款列表和查询
     *
     * @param parameterMap
     * @param page
     * @return
     */
    public PageUtil<LnLoan> getMakeLoanPage(Map<String, Object> parameterMap, Page page){
        return lnLoanDao.getMakeLoanPage(parameterMap,page);
    }

    
    public Integer getMakExloanCont(Map<String, Object> parameterMap){
    	return lnLoanDao.getMakExLoanCont(parameterMap);
    }
    
    /**
     * 异常 贷款列表和查询
     *
     * @param parameterMap
     * @param page
     * @return
     */
    public PageUtil<LnLoan> getMakeExLoanPage(Map<String, Object> parameterMap, Page page){
        return lnLoanDao.getMakeExLoanPage(parameterMap,page);
    }
    /**
     * 根据条件查询审计 贷款
     *
     * @param paramMap
     * @return
     */
    public Integer getVerifyLoanCount(Map<String, Object> paramMap){
        return lnLoanDao.getVerifyLoanCount(paramMap);
    }

    /**
     * 审计 贷款列表和查询
     *
     * @param parameterMap
     * @param page
     * @return
     */
    public PageUtil<LnLoan> getVerifyLoanPage(Map<String, Object> parameterMap, Page page){
        return lnLoanDao.getVerifyLoanPage(parameterMap,page);
    }

    /**
     * 添加担保人
     * @param loanId
     * @param customerId
     * @param userId
     */
    @Transactional
    public void addGu(Integer loanId, Integer customerId, Integer userId,Integer isKownLoan,Integer isException,String checkMessage, String companyAddress, String petitionerRelate){
        CustomerData data = new CustomerData();
        data.setCustomerId(customerId);
        data.setLoanId(loanId);
        data.setUploadUserId(userId);
        data.setEventId(3); //调查事件
        data.setCreateDate(new Date());
        data.setUpdateDate(new Date());
        data.setCreateUser(userId);
        data.setUpdateUser(userId);
        customerDataService.addNewCustomerData(data);

        LnLoanGuarantor gu = new LnLoanGuarantor();
        gu.setLoanId(loanId);
        gu.setCustomerId(customerId);
        gu.setIsKownLoan(isKownLoan);
        gu.setIsException(isException);
        gu.setCheckMessage(checkMessage);
        gu.setCompanyAddress(companyAddress);
        gu.setCheckMessage(checkMessage);
        gu.setPetitionerRelate(petitionerRelate);
        gu.setCreateDate(new Date());
        gu.setUpdateDate(new Date());
        gu.setCreateUser(userId);
        gu.setUpdateUser(userId);
        lnLoanGuarantorService.addLnLoanGuarantor(gu);
    }

    /**
     * 更新担保人信息并新建担保人(即，替换之前的担保人，并把原先的资料全部移到替换后的担保人身上)
     * @param company
     * @param loanId
     * @param oldCustomerId
     * @param newCustomerId
     * @param eventId
     */
    @Transactional
    public void updateGu(Integer loanId, Integer oldCustomerId, Integer newCustomerId, Integer eventId, Integer userId, Integer isKownLoan, Integer isException, String checkMessage, String companyAddress, String petitionerRelate){
        Map<String,Object> paramMap = new HashMap<String, Object>();
        paramMap.put("loanId",loanId);
        paramMap.put("oldCustomerId",oldCustomerId);
        paramMap.put("customerId",newCustomerId);
        paramMap.put("eventId",eventId);
        paramMap.put("uploadUserId",userId);

        paramMap.put("companyAddress", companyAddress);
        paramMap.put("petitionerRelate", petitionerRelate);
        paramMap.put("isKownLoan", isKownLoan);
        paramMap.put("isException", isException);
        paramMap.put("checkMessage", checkMessage);
        customerDataService.updateDatCustomerData(paramMap);
        lnLoanGuarantorService.updateLoanGuarantorByLoan(paramMap);
    }

    public List<LnLoan> getAllLoanNoPage(Map<String, Object> parameterMap) {
        return lnLoanDao.getAllLoanNoPage(parameterMap);
    }

    public void getLoanExportListFile(List<LnLoan> dataList, String path) {
    	//TODO:修改
    /*
        try{
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Workbook book = new HSSFWorkbook();
            Sheet sheet = book.createSheet("所有贷款");
            Row row = sheet.createRow(0);
            row.createCell(0).setCellValue("客户姓名");
            row.createCell(1).setCellValue("身份证");
            row.createCell(2).setCellValue("申请金额(元)");
            row.createCell(3).setCellValue("创建时间");
            row.createCell(4).setCellValue("提交时间");
            row.createCell(5).setCellValue("贷款状态");
            row.createCell(6).setCellValue("贷后状态");
            row.createCell(7).setCellValue("申请人员");
            row.createCell(8).setCellValue("归属人员");
            for (int i = 0; i < dataList.size(); i++) {
                LnLoan loan = dataList.get(i);
                row = sheet.createRow(i + 1);
                row.createCell(0).setCellValue(loan.getCrmCustomer().getCustomerName());
                row.createCell(1).setCellValue(loan.getCrmCustomer().getIdCard());
                row.createCell(2).setCellValue(VmHelper.getUnityDecimalMoney(loan.getLoanMoney()));
                if (loan.getCreateDate() != null) {
                    row.createCell(3).setCellValue(sdf.format(loan.getCreateDate()));
                }
                if (loan.getApplyDate() != null) {
                    row.createCell(4).setCellValue(sdf.format(loan.getApplyDate()));
                }
                row.createCell(5).setCellValue(loan.getLnLoanStatus().getLoanStatusName());
                if (loan.getLoanStatusId() == 6 || loan.getLoanStatusId() == 7) {
                    if (loan.getIsCheckout() != null && loan.getIsCheckout() == 1) {
                        row.createCell(6).setCellValue("已落实");
                    } else {
                        row.createCell(6).setCellValue("未落实");
                    }
                }

                row.createCell(7).setCellValue(loan.getSysUser().getApplyUserName());
                row.createCell(8).setCellValue(loan.getSysUser().getBelongUserName());
            }
            sheet.autoSizeColumn(0);
            sheet.autoSizeColumn(1);
            sheet.autoSizeColumn(2);
            sheet.autoSizeColumn(3);
            sheet.autoSizeColumn(4);
            sheet.autoSizeColumn(5);
            sheet.autoSizeColumn(6);
            sheet.autoSizeColumn(7);
            sheet.autoSizeColumn(8);

            FileOutputStream os = new FileOutputStream(new File(path));
            book.write(os);
            os.close();
        }catch(Exception e){
            logger.error("", e);
        }

    */}

    /**
     * 查询 待催收状态的贷款数量
     * @param paramMap
     * @return
     */
    public Integer selectDunLoanCount(Map<String, Object> paramMap){
        return lnLoanDao.selectDunLoanCount(paramMap);
    }

    /**
     * 更改贷款主表的异常还款相关的信息
     *
     * @param lnLoan
     */
    public void updateLoanByRepaymentPlan(LnLoan lnLoan){
        lnLoanDao.updateLoanByRepaymentPlan(lnLoan);
    }

    /**
     * 将时间添加一定的秒数
     * @param tempDate
     * @return
     */
    public Date addSecondsForDate(Date tempDate, Integer seconds){
        if (tempDate != null){
            Calendar tempCal = Calendar.getInstance();
            tempCal.setTime(tempDate);
            tempCal.add(Calendar.SECOND,seconds);
            return tempCal.getTime();
        }
        return null;
    }

    /**
     * 将时间添加一定的时、分、秒数
     * @param tempDate
     * @param hour
     * @param minute
     * @param seconds
     * @return
     */
    public Date addHMSForDate(Date tempDate, Integer hour, Integer minute, Integer seconds){
        if (tempDate != null){
            Calendar tempCal = Calendar.getInstance();
            tempCal.setTime(tempDate);
            tempCal.add(Calendar.HOUR,hour);
            tempCal.add(Calendar.MINUTE,minute);
            tempCal.add(Calendar.SECOND,seconds);
            return tempCal.getTime();
        }
        return null;
    }

    /**
     * 
     * @param fileId
     * @param TargetDir
     */
    private void moveFile(Integer fileId,String TargetDir,String fileName){
    	logger.info("bug123123"+fileId+","+TargetDir+","+fileName);
    	SysUploadFile sysUploadFile = sysUploadFileService.getSysUploadFileById(fileId);
    	
    	File dataFile = new File(sysUploadFile.getFilePath() + File.separator + sysUploadFile.getFileName());
        logger.info("bugPuth123123"+sysUploadFile.getFilePath() + File.separator + sysUploadFile.getFileName());
        if (dataFile != null && dataFile.exists()){
        	logger.info("LnLoanServiceImpl.getLoanExportDataFile: "+dataFile.getPath()+File.separator+dataFile.getName() 
        			+" copyFileTo "+TargetDir+File.separator+sysUploadFile.getUploadFileName());
        	if(fileName==null || fileName.equals("")){
        		FileUtil.copyFile2Dir(dataFile,TargetDir,sysUploadFile.getUploadFileName());     
        	}else{
        		FileUtil.copyFile2Dir(dataFile,TargetDir,fileName);
        	}
        }else{
        	logger.info("LnLoanServiceImpl.getLoanExportDataFile: can not find file "
        			+dataFile.getPath()+File.separator+dataFile.getName());
        }   
    }
    /**
     * 获取贷款所有导出文件
     * 
     * @param paramMap
     * @param loanId
     * @return
     * @throws Exception 
     */
    public File getLoanExportDataFile(Map<String, Object> paramMap, Integer loanId)  {
        logger.info("LnLoanServiceImpl.getLoanExportDataFile开始,loanId:"+loanId);
        //----创建文件夹LoanExportData/loanId--------------------------
        logger.info("LnLoanServiceImpl.getLoanExportDataFile：create Dir LoanExportData/"+loanId+",begin.");        
        String baseSavePath = sysParamService.getRecordPath()+File.separator+"LoanExportData";
        String rootSavePath = baseSavePath+File.separator+loanId;
        
        FileUtil.createDir(baseSavePath);
        FileUtil.createDir(rootSavePath);
        logger.info("LnLoanServiceImpl.getLoanExportDataFile：create Dir LoanExportData/"+loanId+",end.");        
        //----创建文件夹LoanExportData/loanId/附件--------------------------
        logger.info("LnLoanServiceImpl.getLoanExportDataFile：create Dir LoanExportData/"+loanId+"/附件,begin.");   
        String rootSavePath_Form = baseSavePath+File.separator+loanId+File.separator+"FORM";
        FileUtil.createDir(rootSavePath_Form);
        logger.info("LnLoanServiceImpl.getLoanExportDataFile：create Dir LoanExportData/"+loanId+"/附件,end."); 
        //----创建文件夹LoanExportData/loanId/录音--------------------------
        logger.info("LnLoanServiceImpl.getLoanExportDataFile：create Dir LoanExportData/"+loanId+"/录音,begin.");   
        String rootSavePath_Audio = baseSavePath+File.separator+loanId+File.separator+"AUDIO";
        FileUtil.createDir(rootSavePath_Audio);
        logger.info("LnLoanServiceImpl.getLoanExportDataFile：create Dir LoanExportData/"+loanId+"/录音,end."); 
        //----创建文件夹LoanExportData/loanId/视频--------------------------
        logger.info("LnLoanServiceImpl.getLoanExportDataFile：create Dir LoanExportData/"+loanId+"/视频,begin."); 
        String rootSavePath_Video = baseSavePath+File.separator+loanId+File.separator+"VIDEO";
        FileUtil.createDir(rootSavePath_Video);
        logger.info("LnLoanServiceImpl.getLoanExportDataFile：create Dir LoanExportData/"+loanId+"/视频,end."); 
        //----创建文件夹LoanExportData/loanId/照片--------------------------
        logger.info("LnLoanServiceImpl.getLoanExportDataFile：create Dir LoanExportData/"+loanId+"/照片,begin.");
        String rootSavePath_Photo = baseSavePath+File.separator+loanId+File.separator+"PHOTO";
        FileUtil.createDir(rootSavePath_Photo);
        logger.info("LnLoanServiceImpl.getLoanExportDataFile：create Dir LoanExportData/"+loanId+"/照片,end.");  
        //-------文件转移处理 begin----------------------------------------------

        //--------处理附件------------
        logger.info("LnLoanServiceImpl.getLoanExportDataFile：deal with Form begin");
        List<Form> formList = dataFormService.selectFromListByLoanId(loanId);
        if(formList!=null && formList.size()>0){
        	for (Form curForm : formList){
        		moveFile(curForm.getFileId(),rootSavePath_Form,"");           
        	}
        }        
        logger.info("LnLoanServiceImpl.getLoanExportDataFile：deal with Form end");
        
        //-------处理录音文件------------------
        logger.info("LnLoanServiceImpl.getLoanExportDataFile：deal with Audio begin");
        List<Audio> audioList = dataAudioService.selectAudioListByLoanId(loanId);
        if(audioList!=null && audioList.size()>0){
        	for (Audio curAudio : audioList){
        		moveFile(curAudio.getFileId(),rootSavePath_Audio,"");
        	}
        }
        logger.info("LnLoanServiceImpl.getLoanExportDataFile：deal with Audio end");
        
        //-------处理视频文件------------------
        logger.info("LnLoanServiceImpl.getLoanExportDataFile：deal with Vidoe begin");
        List<Video> videoList = dataVideoService.selectVideoListByLoanId(loanId);
        if(videoList!=null && videoList.size()>0){
        	for (Video curVideo : videoList){
        		moveFile(curVideo.getFileId(),rootSavePath_Video,"");
        	}
        }
        logger.info("LnLoanServiceImpl.getLoanExportDataFile：deal with Vidoe end");
        
        //-------处理照片文件------------------
        logger.info("LnLoanServiceImpl.getLoanExportDataFile：deal with Photo begin");        
        List<Photo> photoList = dataPhotoService.selectPhotoListByLoanId(loanId);
        if(photoList!=null && photoList.size()>0){
        	for (Photo curPhoto : photoList){
        		String rootSavePath_Photo_Type;
        		if(curPhoto.getPhotoTypeName()==null || curPhoto.getPhotoTypeName().equals("")){
        			rootSavePath_Photo_Type = rootSavePath_Photo+File.separator+"未分类"; 
        		}else{
        			rootSavePath_Photo_Type = rootSavePath_Photo+File.separator+curPhoto.getPhotoTypeName(); 
        		}
        		FileUtil.createDir(rootSavePath_Photo_Type);
        		moveFile(curPhoto.getFileId(),rootSavePath_Photo_Type,curPhoto.getPhotoName()+".jpg");
        	}
        }        
        logger.info("LnLoanServiceImpl.getLoanExportDataFile：deal with Photo end");
        //-------文件转移处理 end----------------------------------------------
        
        //-------压缩文件begin-----------------------------------------------
        String downSaveFile = baseSavePath+File.separator+loanId;
        //String downSaveFileName ="";
        /*
        File TempFile1 = new File(downSaveFile+".tar");
        File TempFile2 = new File(downSaveFile+".tar.gz");
        if(TempFile1.exists()||TempFile2.exists()){
        	Random random = new Random(System.currentTimeMillis());
        	downSaveFile = baseSavePath+File.separator+loanId+"("+random.nextLong()*1000+").tar";
        	//downSaveFileName = loanId+"("+random.nextLong()*1000+").tar.gz";
        }else{
        	downSaveFile = baseSavePath+File.separator+loanId+".tar";
        	//downSaveFileName = loanId+".tar.gz";
        }
        */
        try {
        	//CompressUtil.compress(rootSavePath,downSaveFile);        	
        	//CompressUtil.WriteToTarGzip(rootSavePath,downSaveFile,rootSavePath);
        	CompressUtil.callLinuxCmd(baseSavePath,loanId+"");
		} catch (Throwable e) {
			e.printStackTrace();
		}
        //-------压缩文件end-------------------------------------------------
        logger.info("LnLoanServiceImpl.getLoanExportDataFile："+downSaveFile+".tar.gz");
        File loanZipFile = new File(downSaveFile+".tar.gz");
        
        FileUtil.deleteFolder(rootSavePath);

		return loanZipFile;
    }
    /*
    public File getLoanExportDataFile(Map<String, Object> paramMap, Integer loanId)  {
        logger.info("LnLoanServiceImpl.getLoanExportDataFile开始,loanId:"+loanId);
        List<LoanExportData> exportDataList = customerDataService.getFileIdListByLoan(paramMap);
        if (exportDataList != null && exportDataList.size() > 0){
            logger.info("LnLoanServiceImpl.getLoanExportDataFile...,loanId:"+loanId+",资料总数："+exportDataList.size());
            Random random = new Random(1000);
            String baseSavePath = sysParamService.getRecordPath()+File.separator+"LoanExportData";
            String rootSavePath = baseSavePath+File.separator+loanId;
            //导出目标文件,首先检查目录中是否已经存在目标文件，如果存在则重新生成另一个目标文件
            String downSaveFile = baseSavePath+File.separator+loanId+".zip";
            FileUtil.createDir(baseSavePath);
            FileUtil.createDir(rootSavePath);
            CustomerData customerData = new CustomerData();
            int index = 0;
            for (LoanExportData exportData : exportDataList){
                index++;
                String curCustomerName = exportData.getCustomerName();
                Integer eventId = exportData.getEventId();
                Integer fileId = exportData.getFileId();
                String fileEventPath = "";
                if (eventId.equals(LoanConstants.LOAN_APPLY_EVENT)) {
                    fileEventPath = "申请";
                }else if (eventId.equals(LoanConstants.LOAN_EXAM_EVENT)){
                    fileEventPath = "调查";
                }else if (eventId.equals(LoanConstants.LOAN_APPROVE_EVENT)){
                    fileEventPath = "审批";
                }else if (eventId.equals(LoanConstants.LOAN_LENDING_EVENT)){
                    fileEventPath = "放贷";
                }else if (eventId.equals(LoanConstants.LOAN_LENDED_EVENT)){
                    fileEventPath = "贷后";
                }else if (eventId.equals(LoanConstants.LOAN_DUN_EVENT)){
                    fileEventPath = "催收";
                }
                logger.info("LnLoanServiceImpl.getLoanExportDataFile,loanId:"+loanId+",资料总数："+exportDataList.size()+
                        ",第"+index+"个资料开始压缩打包,customerName:"+curCustomerName+",eventId:"+eventId+",fileId:"+fileId+
                ",开始获取实际文件...");
                File dataFile = sysUploadFileService.getRealFile(fileId,customerData);
                if (dataFile != null && dataFile.exists()){
                    logger.info("LnLoanServiceImpl.getLoanExportDataFile,loanId:"+loanId+",资料总数："+exportDataList.size()+
                            ",第"+index+"个资料开始压缩打包,customerName:"+curCustomerName+",eventId:"+eventId+",fileId:"+fileId+
                            ",得到实际文件："+dataFile.getName());
                    String curSavePath = rootSavePath+File.separator+loanId+"_"+curCustomerName+File.separator+fileEventPath;
                    FileUtil.createDir(curSavePath);
                    FileUtil.copyFile2Dir(dataFile,curSavePath);
                }else {
                    logger.info("LnLoanServiceImpl.getLoanExportDataFile,loanId:"+loanId+",资料总数："+exportDataList.size()+
                            ",第"+index+"个资料开始压缩打包,customerName:"+curCustomerName+",eventId:"+eventId+",fileId:"+fileId+
                            ",得到实际文件：null");
                }
            }
            File baseSaveDir = new File(baseSavePath);
            String[] fileNames = baseSaveDir.list();
            if (fileNames != null && fileNames.length > 0){
                String baseFileName = loanId+".zip";
                for (String fileName : fileNames){
                    if ((fileName.equals(baseFileName)) || (fileName.indexOf('(') != -1 && fileName.startsWith(""+loanId))){
                        downSaveFile = baseSavePath+File.separator+loanId+"("+random.nextLong()*1000+").gz";
                    }
                }
            }
            
            logger.info("LnLoanServiceImpl.getLoanExportDataFile,目标文件"+downSaveFile+"正式开始打包...");
            ZipCompressorByAnt zipCompressorByAnt = new ZipCompressorByAnt(downSaveFile);            
            zipCompressorByAnt.compress(rootSavePath);
            
            
            try {
				CompressUtil.WriteToTarGzip(rootSavePath,downSaveFile);
			} catch (Throwable e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
             
            File loanZipFile = new File(downSaveFile);
            
            
            logger.info("LnLoanServiceImpl.getLoanExportDataFile,目标文件"+downSaveFile+"打包完成");
            //删除当前贷款整个资料目录
            FileUtil.deleteFolder(rootSavePath);
            logger.info("LnLoanServiceImpl.getLoanExportDataFile完成,loanId:"+loanId);
            return loanZipFile;
        }
        logger.info("LnLoanServiceImpl.getLoanExportDataFile完成,loanId:"+loanId);
        return null;
    }
    */
    /**
     *  得到还未同步贷款申请信息的贷款数量
     * @return
     */
    public Integer getNoLoanInfoLoanCount(){
        return lnLoanDao.getNoLoanInfoLoanCount();
    }

    /**
     * 得到还未同步贷款申请信息的贷款列表
     * @param paramMap
     * @return
     */
    public List<NoLoanInfo> getNoLoanInfoLoanList(Map<String, Object> paramMap){
        return lnLoanDao.getNoLoanInfoLoanList(paramMap);
    }

    /**
     * 获取各状态条件下的贷款数量
     * @param paramMap
     * @return
     */
    public List<LoanCountReportBean> selectLoanCountReportByStatus(Map<String, Object> paramMap){
        return lnLoanDao.selectLoanCountReportByStatus(paramMap);
    }

    /**
     * 更新贷款主表
     * @param lnLoan
     */
    @Override
    public void updateLoanByLoanId(LnLoan lnLoan) {
        lnLoanDao.updateLoanByLoanId(lnLoan);
    }

    /**
     * 批量更新贷款
     * @param lnLoanList
     */
    @Override
    public void updateLoanByLnLoanBatch(List<LnLoan> lnLoanList) {
        lnLoanDao.updateLoanBatch(lnLoanList);
    }


    /**
     * 获取可以删除手机缓存的部分贷款id
     * @param userId
     * @return
     */
    @Override
    public List<Integer> getPartDELLoanIdByMap(Integer userId) {
        return lnLoanDao.getPartDELLoanIdByMap(userId);
    }

    /**
     * 获取可以删除手机缓存的所有贷款id
     * @param userId
     * @return
     */
    @Override
    public List<Integer> getAllDELLoanIdByMap(Integer userId) {
        return lnLoanDao.getAllDELLoanIdByMap(userId);
    }


    /**
     * 新增共同借款人
     * @param loanId
     * @param customerId
     * @param userId
     */
    @Override
    public void addCob(Integer loanId, Integer customerId, Integer userId,Integer isKownLoan,Integer isException,String checkMessage, String companyAddress, String petitionerRelate) {
        CustomerData data = new CustomerData();
        data.setCustomerId(customerId);
        data.setLoanId(loanId);
        data.setUploadUserId(userId);
        data.setEventId(3); //调查事件
        data.setCreateDate(new Date());
        data.setUpdateDate(new Date());
        data.setCreateUser(userId);
        data.setUpdateUser(userId);
        customerDataService.addNewCustomerData(data);

        LnLoanCoBorrower cob=new LnLoanCoBorrower();
        cob.setLoanId(loanId);
        cob.setCustomerId(customerId);
        cob.setIsKownLoan(isKownLoan);
        cob.setIsException(isException);
        cob.setCheckMessage(checkMessage);
        cob.setCompanyAddress(companyAddress);
        cob.setPetitionerRelate(petitionerRelate);
        cob.setCreateDate(new Date());
        cob.setUpdateDate(new Date());
        cob.setCreateUser(userId);
        cob.setUpdateUser(userId);
        lnLoanCoBorrowerService.addLnLoanCoBorrower(cob);
    }

    /**
     * 修改共同借款人
     * @param loanId
     * @param oldCustomerId
     * @param newCustomerId
     * @param eventId
     * @param userId
     */
    @Override
    public void updateCob(Integer loanId, Integer oldCustomerId, Integer newCustomerId, Integer eventId, Integer userId,Integer isKownLoan,Integer isException,String checkMessage, String companyAddress, String petitionerRelate) {
        Map<String,Object> paramMap = new HashMap<String, Object>();
        paramMap.put("loanId",loanId);
        paramMap.put("oldCustomerId",oldCustomerId);
        paramMap.put("customerId",newCustomerId);
        paramMap.put("eventId",eventId);
        paramMap.put("uploadUserId",userId);
      
        paramMap.put("companyAddress", companyAddress);
        paramMap.put("petitionerRelate", petitionerRelate);
        paramMap.put("isKownLoan", isKownLoan);
        paramMap.put("isException", isException);
        paramMap.put("checkMessage", checkMessage);
       
        customerDataService.updateDatCustomerData(paramMap);
        lnLoanCoBorrowerService.updateCoBorrower(paramMap);
    }

    /**
     * 移除共同借款人
     * @param guId
     * @param loanId
     * @param customerId
     */
    @Override
    public void removeCob(Integer guId, Integer loanId, Integer customerId) {
        lnLoanCoBorrowerService.deleteLnLoanCoBorrower(guId);
        Map<String,Object> paramMap = new HashMap<String, Object>();
        paramMap.put("loanId",loanId);
        paramMap.put("customerId",customerId);

        customerDataService.delCustomerData(paramMap);

    }

	@Override
	public PageUtil<PadLoan> getPadLoanByPage(Map<String, Object> parameterMap,
			Page page) {
			return this.padLnLoanInfoDao.getLnLoanListPage(parameterMap, page);			
	}

    
    /**
     * 创建贷款
     * @see com.banger.mobile.facade.loan.LnLoanService#insertLoanSelective(com.banger.mobile.domain.model.loan.LnLoan)
     */
	@Override
	public void insertLoanSelective(LnLoan lnLoan) {
		lnLoanDao.insertLoanSelective(lnLoan);
	}


	@Override
	public PadLoan getPanLoanById(int loanId) {
		return this.padLnLoanInfoDao.getPanLoanById(loanId);
	}
	
	@Override
	public int getPadLoanCountByLoanStatus(Map<String, Object> parameterMap){
		return this.padLnLoanInfoDao.getPadLoanCountByLoanStatus(parameterMap);
	}

	@Override
	public Integer countApproveLoanBySysUserId(Map<String, Object> parameterMap) {
		
		return lnLoanDao.countApproveLoanBySysUserId(parameterMap);
	}

	@Override
	public void submitApproveLoan(LnLoan lnLoan, LnLoanInfo lnLoanInfo,
			LnOpHistory lnOpHistory) {
		//更新贷款信息
		if(lnLoanInfo!=null){
			lnLoanInfoDao.updateLnLoanInfo(lnLoanInfo);
		}
		// 更改贷款
        lnLoanDao.updateLoanByLoanId(lnLoan);
        // 插入历史记录
        lnOpHistoryService.insertLnOpHistory(lnOpHistory);
	}

	@Override
	public void doSyncDBJob() {
		// TODO Auto-generated method stub
		lnLoanDao.doSyncDBJob();
		
	}

	@Override
	public List<CusCheck> cusCheck(String cusIds,Integer loanId) {
		return lnLoanDao.cusCheck(cusIds,loanId);
	}
	
	
	
	@Override
	public void clearApproveDataAndBakMsg(String loanId) {
		//查询备份
		LnLoan lnLoan = this.selectLoanById(Integer
				.parseInt(loanId));
		LnLoanApproveBakMsg approveBakMsg = new LnLoanApproveBakMsg();
		//approveBakMsg.setApproveBackerPassDate(lnLoan.getApproveBackerPassDate());
		//approveBakMsg.setApproveBackerRejectDate(lnLoan.getApproveBackerRejectDate());
		approveBakMsg.setApproveBackerUserId(lnLoan.getApproveBackerUserId());
		//approveBakMsg.setApproveCenterPassDate(lnLoan.getApproveCenterPassDate());
		//approveBakMsg.setApproveCenterRejectDate(lnLoan.getApproveCenterRejectDate());
		approveBakMsg.setApproveCenterUserId1(lnLoan.getApproveCenterUserId1());
		approveBakMsg.setApproveCenterUserId2(lnLoan.getApproveCenterUserId2());
		//approveBakMsg.setApproveDirectorPassDate(lnLoan.getApproveDirectorPassDate());
		//approveBakMsg.setApproveDirectorRejectDate(lnLoan.getApproveDirectorPassDate());
		approveBakMsg.setApproveDirectorUserId(lnLoan.getApproveDirectorUserId());
		
		String result = JSONObject.fromObject(approveBakMsg).toString();
		lnLoan.setApproveBakMsg(result);
		
		lnLoanDao.clearApproveDataAndBakMsg(lnLoan);
	}

	
	@Override
	public void clearApproveData(Integer loanId) {
		//LnLoan lnLoan = this.selectLoanById(Integer.parseInt(loanId));
		lnLoanDao.clearApproveData(loanId);
	}

	@Override
	public String saveApproveDataBakMsg(String loanId) {
		//查询备份
		LnLoan lnLoan = this.selectLoanById(Integer.parseInt(loanId));
		LnLoanApproveBakMsg approveBakMsg = new LnLoanApproveBakMsg();
		approveBakMsg.setApproveBackerUserId(lnLoan.getApproveBackerUserId());
		approveBakMsg.setApproveCenterUserId1(lnLoan.getApproveCenterUserId1());
		approveBakMsg.setApproveCenterUserId2(lnLoan.getApproveCenterUserId2());
		approveBakMsg.setApproveDirectorUserId(lnLoan.getApproveDirectorUserId());		
		String result = JSONObject.fromObject(approveBakMsg).toString();
		lnLoan.setApproveBakMsg(result);		
		lnLoanDao.saveApproveDataBakMsg(lnLoan);
		return result;
	}
	
	public SysTeamUserService getSysTeamUserService() {
		return sysTeamUserService;
	}

	public void setSysTeamUserService(SysTeamUserService sysTeamUserService) {
		this.sysTeamUserService = sysTeamUserService;
	}
	
	
	public void setLnCreditHistoryService(
			LnCreditHistoryService lnCreditHistoryService) {
		this.lnCreditHistoryService = lnCreditHistoryService;
	}

	@Override
	public void updateGoBackLoanSurveyById(Integer lnloanId) {
		lnLoanDao.updateGoBackLoanSurveyById(lnloanId);
		
	}

	@Override
	public void updateAssign(Integer loanId) {
		lnLoanDao.updateAssign(loanId);
		
	}
	
	public void submitLend(Integer loanId){
		lnLoanDao.submitLend(loanId);
	}

    @Override
    public LnLoan getUnCheckLoanByContractNo(String contNo) {
        return lnLoanDao.getUnCheckLoanByContractNo(contNo);
    }

    public List<LnLoan> selectListByUserAndDate(Map map){
        return lnLoanDao.selectListByUserAndDate(map);
    }

    public BigDecimal selectAmountByStatus(Map map){
        return lnLoanDao.selectAmountByStatus(map);
    }

    @Override
    public LoanTypeTotalReportBean getLoanTypeTotalSum(Map map) {
        return lnLoanDao.getLoanTypeTotalSum(map);
    }

    @Override
    public BigDecimal getLoanAmountType(Map map) {
        return lnLoanDao.getLoanAmountType(map);
    }

    @Override
    public LoanTypeTotalReportBean getAllMonthLoanBal(String start,String end,Integer loanType,Integer repayWayId) {
        Map<String,Object> map = new HashMap<String, Object>();
        if(StringUtils.isNotBlank(start)){
            map.put("start",start);
        }
        if(StringUtils.isNotBlank(end)){
            map.put("end",end);
        }
        if(loanType !=null) {
            map.put("loanTypeId", loanType);
        }
        if(repayWayId !=null){
            map.put("repayWayId",repayWayId);
        }
//        BigDecimal moeny = lnLoanDao.getAllMonthLoanBal(map);
//        if(moeny==null){
//            moeny = new BigDecimal(0);
//        }
        return lnLoanDao.getAllMonthLoanBal(map);
    }
    @Override
    public LoanTypeTotalReportBean getKDBMonthLoanBal(String start,String end,Integer loanType,String flag){
        Map<String,Object> map = new HashMap<String, Object>();
        if(StringUtils.isNotBlank(start)){
            map.put("start",start);
        }
        if(StringUtils.isNotBlank(end)){
            map.put("end",end);
        }
        if(loanType!=null) {
            map.put("loanTypeId", loanType);
        }
        if(StringUtils.isNotBlank(flag)){
            map.put("flag",flag);
        }
//        BigDecimal moeny = lnLoanDao.getKDBMonthLoanBal(map);
//        if(moeny==null){
//            moeny = new BigDecimal(0);
//        }
        return lnLoanDao.getKDBMonthLoanBal(map);
    }
}
