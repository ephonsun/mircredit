package com.banger.mobile.dao.loan.ibatis;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.dao.loan.LnLoanDao;
import com.banger.mobile.domain.model.loan.CusCheck;
import com.banger.mobile.domain.model.loan.LnLoan;
import com.banger.mobile.domain.model.loan.LnLoanCustomerBean;
import com.banger.mobile.domain.model.loan.LnLoanInfo;
import com.banger.mobile.domain.model.loan.NoLoanInfo;
import com.banger.mobile.domain.model.report.LoanCountReportBean;
import com.banger.mobile.domain.model.report.LoanTypeTotalReportBean;
import com.banger.mobile.ibatis.GenericDaoiBatis;
import com.banger.mobile.util.StringUtil;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA. User: Zhangfp Date: 13-2-6 Time: 上午9:11 To change
 * this template use File | Settings | File Templates.
 *
 * 贷款主表
 */
public class LnLoanDaoiBatis extends GenericDaoiBatis implements LnLoanDao {

    @SuppressWarnings("unchecked")
    public LnLoanDaoiBatis() {
        super(LnLoan.class);
    }

    /**
     * Constructor that takes in a class to see which type of entity to persist
     *
     * @param persistentClass
     *            the class type you'd like to persist
     */
    @SuppressWarnings("unchecked")
    public LnLoanDaoiBatis(Class persistentClass) {
        super(persistentClass);
    }

    /**
     * 新增贷款
     *
     * @param lnLoan
     */
    public void insertLoan(LnLoan lnLoan) {
        this.getSqlMapClientTemplate().insert("insertLoan", lnLoan);
    }

    /**
     * 批量新增贷款
     *
     * @param lnLoans
     */
    public void insertLoanBatch(List<LnLoan> lnLoans) {
        this.exectuteBatchInsert("insertLoan", lnLoans);
    }

    public void deleteLoan(Integer loanId) {
        this.getSqlMapClientTemplate().delete("deleteLoan", loanId);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<LnLoan> queryLnLoanRelation(Map<String, Object> paramMap) {
        return this.getSqlMapClientTemplate().queryForList("queryLnLoanRelation", paramMap);
    }

    /**
     * 查询所有贷款客户数据信息根据参数
     *
     * @param paramMap
     * @return
     * @see com.banger.mobile.dao.loan.LnLoanDao#getAllLoanByConds(java.util.Map)
     */
    @SuppressWarnings("unchecked")
    public List<LnLoan> getAllLoanByConds(Map<String, Object> paramMap) {
        return this.getSqlMapClientTemplate().queryForList("getAllLoanByConds", paramMap);
    }

    /**
     * 分页查询贷款列表
     *
     * @param parameterMap
     * @param page
     * @return
     */
    @SuppressWarnings("unchecked")
    public PageUtil<LnLoan> getLnLoanListPage(Map<String, Object> parameterMap, Page page) {
        Map<String, Object> fConds = new HashMap<String, Object>();
        for (Map.Entry<String, Object> entry : parameterMap.entrySet()) {
            if (entry.getValue() instanceof String) {
                fConds.put(entry.getKey(), StringUtil.ReplaceSQLEscapeChar(StringUtil
                        .ReplaceSQLChar(entry.getValue().toString())));
            } else {
                fConds.put(entry.getKey(), entry.getValue());
            }
        }
        List<LnLoan> list = (List<LnLoan>) this.findQueryPage("selectLoanList", "selectLoanCount",
                fConds, page);
        return new PageUtil<LnLoan>(list, page);
    }

    /**
     * 根据参数得到模糊查找的客户数据
     *
     * @param conds
     * @return
     * @see com.banger.mobile.dao.loan.LnLoanDao#getAllCustomerBeanByConds(java.util.Map)
     */
    @SuppressWarnings("unchecked")
    public List<LnLoanCustomerBean> getAllCustomerBeanByConds(Map<String, Object> conds) {
        Map<String, Object> fConds = new HashMap<String, Object>();
        for (Map.Entry<String, Object> entry : conds.entrySet()) {
            if (entry.getValue() instanceof String) {
                fConds.put(entry.getKey(), StringUtil.ReplaceSQLEscapeChar(StringUtil
                        .ReplaceSQLChar(entry.getValue().toString())));
            } else {
                fConds.put(entry.getKey(), entry.getValue());
            }
        }
        return this.getSqlMapClientTemplate().queryForList("getAllLoanCustomerBeanByConds", fConds);
    }
    @Override
    public LnLoan selectLoanById(Integer loanId) {

        Map<String,Object> param = new HashMap<String,Object>();
        param.put("loanId", loanId);

        return (LnLoan) this.getSqlMapClientTemplate().queryForObject("selectLoanById", param);
    }

    /**
     * 根据id查找
     *
     * @param loanId
     * @return
     */
    public LnLoan getLnLoanById(Integer loanId) {
        return (LnLoan) this.getSqlMapClientTemplate().queryForObject("getLnLoanById", loanId);
    }

    /**
     * 更新贷款主表
     *
     * @param paramMap
     */
    public void updateLnLoanById(Map<String, Object> paramMap) {
        this.getSqlMapClientTemplate().update("updateLnLoanById", paramMap);
    }

    /**
     * 批量更改更新贷款主表
     *
     * @param paramMapList
     */
    public void updateLnLoanByIdBatch(List<Map<String, Object>> paramMapList) {
        this.executeBatchUpdate("updateLnLoanById",paramMapList);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<LnLoan> getAllLoanNoPage(Map<String, Object> parameterMap) {
        Map<String, Object> fConds = new HashMap<String, Object>();
        for (Map.Entry<String, Object> entry : parameterMap.entrySet()) {
            if (entry.getValue() instanceof String) {
                fConds.put(entry.getKey(), StringUtil.ReplaceSQLEscapeChar(StringUtil
                        .ReplaceSQLChar(entry.getValue().toString())));
            } else {
                fConds.put(entry.getKey(), entry.getValue());
            }
        }
        return this.getSqlMapClientTemplate().queryForList("selectLoanListOptimizeNoPage", fConds);
    }

    /**
     * 得到贷款 NEXT SEQ 值
     *
     * @return
     * @see com.banger.mobile.dao.loan.LnLoanDao#getNextLoanId()
     */
    public Integer getNextLoanId() {
        return (Integer) getSqlMapClientTemplate().queryForObject("getNextLoanId");
    }

    /**
     * 批量更新贷款主表
     *
     * @param list
     */
    public void updateLnLoanBatch(List<Map<String, Object>> list) {
        this.executeBatchUpdate("updateLnLoanById", list);
    }

    /**
     * 根据loanId查看当前贷款状态
     *
     * @param paramMap
     * @return
     */
    @Override
    public Integer getLoanStatusById(Map<String, Object> paramMap) {
        return (Integer) this.getSqlMapClientTemplate().queryForObject("getLoanStatusById",
                paramMap);
    }

    @Override
    public Integer queryChangedLoanStatusCount(Map<String, Object> paramMap) {
        return (Integer) this.getSqlMapClientTemplate().queryForObject(
                "queryChangedLoanStatusCount", paramMap);
    }

    /**
     * 按条件搜索贷款总数
     *
     * @author Zhangfp
     * @param paramMap
     * @return
     */
    @Override
    public Integer selectLoanCount(Map<String, Object> paramMap) {
        return (Integer) this.getSqlMapClientTemplate().queryForObject("selectLoanCount", paramMap);
    }

    /**
     * 按条件搜索贷款列表
     *
     * @param paramMap
     * @return
     */
    @Override
    public LnLoan selectLoanList(Map<String, Object> paramMap) {
        return (LnLoan) this.getSqlMapClientTemplate().queryForObject("selectLoanList", paramMap);
    }

    /**
     * 查询所有符合条件的贷款
     *
     * @param paramMap
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<LnLoan> selectAllLoanList(Map<String, Object> paramMap) {
//        return this.getSqlMapClientTemplate().queryForList("selectLoanList", paramMap);
        return this.getSqlMapClientTemplate().queryForList("selectLoanListOptimize", paramMap);
    }

    /**
     * 查询未贷款的客户
     *
     * @param paramMap
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Integer> getLoanUserList(Map<String, Object> paramMap) {
        return this.getSqlMapClientTemplate().queryForList("getLoanUserList", paramMap);
    }

    /**
     * 分页查找贷款信息--小页卡
     *
     * @param paramMap
     *            客户ID Map
     * @param page
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public PageUtil<LnLoan> getAllLoanByCusId(Map<String, Object> paramMap, Page page) {
        Map<String, Object> fConds = new HashMap<String, Object>();
        for (Map.Entry<String, Object> entry : paramMap.entrySet()) {
            if (entry.getValue() instanceof String) {
                fConds.put(entry.getKey(), StringUtil.ReplaceSQLEscapeChar(StringUtil
                        .ReplaceSQLChar(entry.getValue().toString())));
            } else {
                fConds.put(entry.getKey(), entry.getValue());
            }
        }
        List<LnLoan> list = (List<LnLoan>) this.findQueryPage("getAllLoanByCusId",
                "getLoanCountByCusId", fConds, page);
        return new PageUtil<LnLoan>(list, page);
    }

    /**
     * 根据客户ID集合查询出客户相关的贷款信息
     *
     * @param paramMap
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<LnLoan> getAllLoanByCusIds(Map<String, Object> paramMap) {
        return (List<LnLoan>) this.getSqlMapClientTemplate().queryForList("getAllLoanByCusId",
                paramMap);
    }

    /**
     * 合并客户贷款
     *
     */
    @Override
    public void mergeCusLoan(Map<String, Object> paramMap) {
        this.getSqlMapClientTemplate().update("mergeCusLoan", paramMap);
    }

    /**
     * 根据客户ID查询出，当前客户最近一笔贷款的状态
     *
     * @param paramMap
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<LnLoan> getRecentlyLoanStatusByCusIds(Map<String, Object> paramMap) {
        return (List<LnLoan>) this.getSqlMapClientTemplate().queryForList(
                "getRecentlyLoanStatusByCusIds", paramMap);
    }

    public Integer getLoanCountByCusId(Map<String, Object> paras) {
        return (Integer) this.getSqlMapClientTemplate()
                .queryForObject("getLoanCountByCusId", paras);
    }

    // 统计贷款任务进度
    public Integer getLoanCountByTask(Map<String, Object> param) {
        return (Integer) this.getSqlMapClientTemplate().queryForObject("getLoanCountByTask", param);
    }

    /**
     * 根据贷款状态，得到每个状态下有多少贷款
     *
     * @param paramMap
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<LnLoan> getCountByStatus(Map<String, Object> paramMap) {
        return (List<LnLoan>) this.getSqlMapClientTemplate().queryForList("getCountByStatus",
                paramMap);
    }

    /**
     * 查询各个贷款状态下的贷款总数
     *
     * @param paramMap
     * @return
     */
    @Override
    public List<LnLoan> getLoanCountByLoanStatus(Map<String, Object> paramMap) {
        return null;
    }

    /**
     * 根据贷款用户和贷款状态得到相关状态下的贷款的数量
     *
     * @param paramMap
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<LnLoan> getCountByLoanStatusId(Map<String, Object> paramMap) {
        return (List<LnLoan>) this.getSqlMapClientTemplate().queryForList("getCountByLoanStatusId",
                paramMap);
    }

    /**
     * 全部状态列表
     *
     * @param paramMap
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<LnLoan> getAllLoanByUserId(Map<String, Object> paramMap) {
        return (List<LnLoan>) this.getSqlMapClientTemplate().queryForList("getAllLoanByUserId",
                paramMap);
    }

    /**
     * 查出每个状态下的贷款总数
     *
     * @param paramMap
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<LnLoan> getLoanCountByLoanStatusId(Map<String, Object> paramMap) {
        return (List<LnLoan>) this.getSqlMapClientTemplate().queryForList(
                "getLoanCountByLoanStatusId", paramMap);
    }

    /**
     * 得到三个月内客户的审批失败的贷款记录数
     *
     * @param paramMap
     * @return
     */
    @Override
    public Integer getApproveFailCount(Map<String, Object> paramMap) {
        return (Integer) this.getSqlMapClientTemplate().queryForObject("getApproveFailCount",
                paramMap);
    }

    /**
     * 得到要立马操作的贷款，也就是在做下一个动作之前 ，已经超时的贷款
     *
     * @param paramMap
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<LnLoan> getNowNeedHandleLoanList(Map<String, Object> paramMap) {
        return (List<LnLoan>) this.getSqlMapClientTemplate().queryForList(
                "getNowNeedHandleLoanList", paramMap);
    }

    /**
     * 得到新的需要提醒的贷款
     *
     * @param paramMap
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<LnLoan> getNewLoanForWarning(Map<String, Object> paramMap) {
        return (List<LnLoan>) this.getSqlMapClientTemplate().queryForList("getNewLoanForWarning",
                paramMap);
    }

    @Override
    @SuppressWarnings("unchecked")
    public PageUtil<LnLoanCustomerBean> getAllCustomerBeanByConds(Map<String, Object> conds,
                                                                  Page page) {
        Map<String, Object> fConds = new HashMap<String, Object>();
        for (Map.Entry<String, Object> entry : conds.entrySet()) {
            if (entry.getValue() instanceof String) {
                fConds.put(entry.getKey(), StringUtil.ReplaceSQLEscapeChar(StringUtil
                        .ReplaceSQLChar(entry.getValue().toString())));
            } else {
                fConds.put(entry.getKey(), entry.getValue());
            }
        }
        List<LnLoanCustomerBean> list = (List<LnLoanCustomerBean>) this.findQueryPage(
                "getAllLoanCustomerBeanPageByConds", "getAllLoanCustomerBeanPageByCondsCount", fConds,
                page);
        return new PageUtil<LnLoanCustomerBean>(list, page);
    }

    /**
     * 查看某个客户的贷款数
     *
     * @param paramMap
     * @return
     */
    @Override
    public Integer getCustomerLoanCount(Map<String, Object> paramMap) {
        return (Integer) this.getSqlMapClientTemplate().queryForObject("getCustomerLoanCount",
                paramMap);
    }

    @Override
    public LnLoan getLastLoanByCustomer(Integer customerId){
        return (LnLoan)this.getSqlMapClientTemplate().queryForObject("getLastLoanByCustomer",customerId);
    }

    public Integer getVerifyAssignCount(Map<String ,Object> paramMap){
        return (Integer) this.getSqlMapClientTemplate().queryForObject("getVerifyAssignCount",paramMap);
    }

    @SuppressWarnings("unchecked")
    public List<LnLoan> getVerifyAssignList (Map<String ,Object> paramMap){
        return (List<LnLoan>) this.getSqlMapClientTemplate().queryForList("getVerifyAssignList",paramMap);
    }

    /**
     * 单独获得各状态下的贷款数量
     *
     * @param loanStatusId
     * @return
     */
    @Override
    public Integer getLoanCountByStatusId(Integer loanStatusId){
        return (Integer)this.getSqlMapClientTemplate().queryForObject("getLoanCountByStatusId",loanStatusId);
    }

    /**
     * 单独获得各状态下的贷款
     *
     * @param paramMap
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<LnLoanInfo> getLoanByStatusId(Map<String, Object> paramMap){
        return (List<LnLoanInfo>)this.getSqlMapClientTemplate().queryForList("getLoanByStatusId",paramMap);
    }

    @Override
    public Integer updateLoanPartFieldBatch(List<LnLoan> lnLoanList){
        return (Integer)this.executeBatchUpdate("updateLoanPartField",lnLoanList);
    }

    public Integer getNotEndLoanCount(Integer customerId){
        return (Integer) this.getSqlMapClientTemplate().queryForObject("getNotEndLoanCount",customerId);
    }

    public Integer getLoanId(Map<String, Object> param){
        return (Integer) this.getSqlMapClientTemplate().queryForObject("getLoanId",param);
    }

    /**
     * 查出客户的所有贷款
     *
     * @param customerId
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<LnLoan> getLoanByCustomerId(Integer customerId){
        return (List<LnLoan>)this.getSqlMapClientTemplate().queryForList("getLoanByCustomerId",customerId);
    }

    /**
     * 批量更新每个客户的贷款负责人
     *
     * @param mapList
     * @return
     */
    @Override
    public Integer updateLoanOwnerByCustomerIdBatch(List<Map<String, Object>> mapList){
        return (Integer)this.executeBatchUpdate("updateLoanOwnerByCustomerId",mapList);
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
    @SuppressWarnings("unchecked")
    public PageUtil<LnLoan> getAllLoanByPage(Map<String, Object> parameterMap, Page page) {
        Map<String, Object> fConds = new HashMap<String, Object>();
        for (Map.Entry<String, Object> entry : parameterMap.entrySet()) {
            if (entry.getValue() instanceof String) {
                fConds.put(entry.getKey(), StringUtil.ReplaceSQLEscapeChar(StringUtil
                        .ReplaceSQLChar(entry.getValue().toString())));
            } else {
                fConds.put(entry.getKey(), entry.getValue());
            }
        }
        List<LnLoan> list = (List<LnLoan>) this.findQueryPage("selectLoanListOptimize", "selectLoanCountOptimize",
                fConds, page);
        return new PageUtil<LnLoan>(list, page);
    }

    /**
     * 申请(分配或调查)贷款列表和查询
     *
     * @param parameterMap
     * @param page
     * @return
     */
    @SuppressWarnings("unchecked")
    public PageUtil<LnLoan> getApplyLoanByPage(Map<String, Object> parameterMap, Page page){
        Map<String, Object> fConds = new HashMap<String, Object>();
        for (Map.Entry<String, Object> entry : parameterMap.entrySet()) {
            if (entry.getValue() instanceof String) {
                fConds.put(entry.getKey(), StringUtil.ReplaceSQLEscapeChar(StringUtil
                        .ReplaceSQLChar(entry.getValue().toString())));
            } else {
                fConds.put(entry.getKey(), entry.getValue());
            }
        }
        List<LnLoan> list = (List<LnLoan>) this.findQueryPage("getApplyLoanList", "getApplyLoanCount",
                fConds, page);
        return new PageUtil<LnLoan>(list, page);
    }

    /**
     * 根据条件查询审批贷款的数量
     *
     * @param paramMap
     * @return
     */
    public Integer getAssessLoanCount(Map<String, Object> paramMap){
        return (Integer)this.getSqlMapClientTemplate().queryForObject("getAssessLoanCount",paramMap);
    }

    /**
     * 根据条件查询审批贷款
     *
     * @param paramMap
     * @return
     */
    public LnLoan getAssessLoanAlone(Map<String, Object> paramMap){
        return (LnLoan)this.getSqlMapClientTemplate().queryForObject("getAssessLoanAlone",paramMap);
    }

    /**
     * 根据条件查询未放贷 及放贷后 贷款
     *
     * @param paramMap
     * @return
     */
    public Integer getMakeLoanCount(Map<String, Object> paramMap){
        Map<String, Object> fConds = new HashMap<String, Object>();
        for (Map.Entry<String, Object> entry : paramMap.entrySet()) {
            if (entry.getValue() instanceof String) {
                fConds.put(entry.getKey(), StringUtil.ReplaceSQLEscapeChar(StringUtil
                        .ReplaceSQLChar(entry.getValue().toString())));
            } else {
                fConds.put(entry.getKey(), entry.getValue());
            }
        }
        return (Integer)this.getSqlMapClientTemplate().queryForObject("getMakeLoanCount",fConds);
    }

    /**
     * 未放贷 及放贷后 贷款列表和查询
     *
     * @param parameterMap
     * @param page
     * @return
     */
    @SuppressWarnings("unchecked")
    public PageUtil<LnLoan> getMakeLoanPage(Map<String, Object> parameterMap, Page page){
        Map<String, Object> fConds = new HashMap<String, Object>();
        for (Map.Entry<String, Object> entry : parameterMap.entrySet()) {
            if (entry.getValue() instanceof String) {
                fConds.put(entry.getKey(), StringUtil.ReplaceSQLEscapeChar(StringUtil
                        .ReplaceSQLChar(entry.getValue().toString())));
            } else {
                fConds.put(entry.getKey(), entry.getValue());
            }
        }
        List<LnLoan> list = (List<LnLoan>) this.findQueryPage("getMakeLoanList", "getMakeLoanCount",
                fConds, page);
        return new PageUtil<LnLoan>(list, page);
    }
    /**
     * 异常 贷款列表和查询
     *
     * @param parameterMap
     * @param page
     * @return
     */
    @SuppressWarnings("unchecked")
    public PageUtil<LnLoan> getMakeExLoanPage(Map<String, Object> parameterMap, Page page){
        Map<String, Object> fConds = new HashMap<String, Object>();
        for (Map.Entry<String, Object> entry : parameterMap.entrySet()) {
            if (entry.getValue() instanceof String) {
                fConds.put(entry.getKey(), StringUtil.ReplaceSQLEscapeChar(StringUtil
                        .ReplaceSQLChar(entry.getValue().toString())));
            } else {
                fConds.put(entry.getKey(), entry.getValue());
            }
        }
        List<LnLoan> list = (List<LnLoan>) this.findQueryPage("getMakeExLoanList", "getMakeExLoanCount",
                fConds, page);
        return new PageUtil<LnLoan>(list, page);
    }
    public Integer getVerifyLoanCount(Map<String, Object> paramMap){
        return (Integer)this.getSqlMapClientTemplate().queryForObject("getVerifyLoanCount",paramMap);
    }

    @SuppressWarnings("unchecked")
    public PageUtil<LnLoan> getVerifyLoanPage(Map<String, Object> parameterMap, Page page){
        Map<String, Object> fConds = new HashMap<String, Object>();
        for (Map.Entry<String, Object> entry : parameterMap.entrySet()) {
            if (entry.getValue() instanceof String) {
                fConds.put(entry.getKey(), StringUtil.ReplaceSQLEscapeChar(StringUtil
                        .ReplaceSQLChar(entry.getValue().toString())));
            } else {
                fConds.put(entry.getKey(), entry.getValue());
            }
        }
        List<LnLoan> list = (List<LnLoan>) this.findQueryPage("getVerifyLoanList", "getVerifyLoanCount",
                fConds, page);
        return new PageUtil<LnLoan>(list, page);
    }

    /**
     * 控制台 各贷款状态相对应的数量
     *
     * @param paramMap
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<LnLoan> getLoanCountByLoanStatusIdOptimize(Map<String, Object> paramMap) {
        return (List<LnLoan>) this.getSqlMapClientTemplate().queryForList(
                "getLoanCountByLoanStatusIdOptimize", paramMap);
    }

    /**
     * 控制台 各贷款状态列表
     *
     * @param paramMap
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<LnLoan> getWPLoanListOptimize(Map<String,Object> paramMap){
        return (List<LnLoan>)this.getSqlMapClientTemplate().queryForList("getWPLoanListOptimize",paramMap);
    }

    /**
     * 查询 待催收状态的贷款数量
     * @param paramMap
     * @return
     */
    public Integer selectDunLoanCount(Map<String,Object> paramMap){
        return (Integer)this.getSqlMapClientTemplate().queryForObject("selectDunLoanCount",paramMap);
    }

    /**
     * 更改贷款主表的异常还款相关的信息
     *
     * @param lnLoan
     */
    public void updateLoanByRepaymentPlan(LnLoan lnLoan){
        this.getSqlMapClientTemplate().update("updateLoanByRepaymentPlan",lnLoan);
    }

    /**
     *  得到还未同步贷款申请信息的贷款数量
     * @return
     */
    public Integer getNoLoanInfoLoanCount(){
        return (Integer)this.getSqlMapClientTemplate().queryForObject("getNoLoanInfoLoanCount");
    }

    /**
     * 得到还未同步贷款申请信息的贷款列表
     * @param paramMap
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<NoLoanInfo> getNoLoanInfoLoanList(Map<String,Object> paramMap){
        return (List<NoLoanInfo>)this.getSqlMapClientTemplate().queryForList("getNoLoanInfoLoanList",paramMap);
    }

    /**
     * 获取各状态条件下的贷款数量
     * @param paramMap
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<LoanCountReportBean> selectLoanCountReportByStatus(Map<String,Object> paramMap){
        return (List<LoanCountReportBean>)this.getSqlMapClientTemplate().queryForList("selectLoanCountReportByStatus",paramMap);
    }

    /**
     * 更新贷款
     * @throws Exception
     */
    @Override
    public void updateLoanByLoanId(LnLoan lnLoan)  {
        if(lnLoan.getLoanId() == null || lnLoan.getLoanId() < 0){
            try {
                throw new Exception("贷款编号不能为空！");
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }

        }
        this.getSqlMapClientTemplate().update("updateLoan",lnLoan);
    }

    /**批量更新
     *
     * @param lnLoanList
     */
    @Override
    public void updateLoanBatch(List<LnLoan> lnLoanList) {
        this.executeBatchUpdate("updateLoan",lnLoanList);
    }


    @SuppressWarnings("unchecked")
    @Override
    public List<Integer> getPartDELLoanIdByMap(Integer userId) {
        return (List<Integer>)this.getSqlMapClientTemplate().queryForList("getPartDELLoanIdByMap",userId);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Integer> getAllDELLoanIdByMap(Integer userId) {
        return (List<Integer>)this.getSqlMapClientTemplate().queryForList("getAllDELLoanIdByMap",userId);
    }

    @Override
    public void insertLoanSelective(LnLoan lnLoan) {
        this.getSqlMapClientTemplate().insert("insertLoanSelective", lnLoan);

    }

    @Override
    public Integer countApproveLoanBySysUserId(Map<String, Object> parameterMap) {
        return (Integer)this.getSqlMapClientTemplate().queryForObject("countApproveLoanBySysUserId", parameterMap);
    }

    public void doSyncDBJob(){
//		this.getSqlMapClientTemplate().queryForObject("doSyncDBJob");
    }

    @Override
    public List<CusCheck> cusCheck(String cusIds,Integer loanId) {
        Map<String, Object> fConds = new HashMap<String, Object>();
        fConds.put("cusIds", cusIds);
        fConds.put("loanId", loanId.toString());
        return this.getSqlMapClientTemplate().queryForList("cusCheck",fConds);
    }

    @Override
    public void clearApproveDataAndBakMsg(LnLoan lnLoan) {
        if(lnLoan.getLoanId() == null || lnLoan.getLoanId() < 0){
            try {
                throw new Exception("贷款编号不能为空！");
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
        this.getSqlMapClientTemplate().update("clearApproveDataAndBakMsg",lnLoan);
    }

    public void clearApproveData(Integer loanId){
        if(loanId == null || loanId.equals("")){
            try {
                throw new Exception("贷款编号不能为空！");
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
        this.getSqlMapClientTemplate().update("clearApproveData",loanId);
    }

    public void saveApproveDataBakMsg(LnLoan lnLoan){
        if(lnLoan.getLoanId() == null || lnLoan.getLoanId() < 0){
            try {
                throw new Exception("贷款编号不能为空！");
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
        this.getSqlMapClientTemplate().update("saveApproveDataBakMsg",lnLoan);
    }


    public Integer getMakExLoanCont(Map<String, Object> parameterMap){
        return (Integer) this.getSqlMapClientTemplate().queryForObject("getMakeExLoanCount",
                parameterMap);
    }
    public void updateAssign(Integer loanId) {
        getSqlMapClientTemplate().update("updateAssign",loanId);
    }
    public void updateGoBackLoanSurveyById(Integer lnLoanId) {

        this.getSqlMapClientTemplate().update("updateGoBackLoanSurveyById",lnLoanId);
    }

    public void submitLend(Integer loanId){
        this.getSqlMapClientTemplate().update("submitLend",loanId);
    }

    @Override
    public LnLoan getUnCheckLoanByContractNo(String contNo) {
        return (LnLoan)this.getSqlMapClientTemplate().queryForObject("getUnCheckLoanByContractNo",contNo);
    }

    public List<LnLoan> selectListByUserAndDate(Map map){
        return this.getSqlMapClientTemplate().queryForList("selectListByUserAndDate", map);
    }

    public BigDecimal selectAmountByStatus(Map map){
        BigDecimal result = (BigDecimal)this.getSqlMapClientTemplate().queryForObject("selectAmountByStatus", map);
        BigDecimal amount = new BigDecimal(0);
        if(result!=null){
            return result;
        }else{
            return amount;
        }
    }

    @Override
    public LoanTypeTotalReportBean getLoanTypeTotalSum(Map map) {
        return (LoanTypeTotalReportBean)this.getSqlMapClientTemplate().queryForObject("getLoanTypeTotalSum", map);
    }

    @Override
    public BigDecimal getLoanAmountType(Map map) {
        return (BigDecimal)this.getSqlMapClientTemplate().queryForObject("getLoanAmountType", map);
    }

    @Override
    public LoanTypeTotalReportBean getAllMonthLoanBal(Map map) {
        return (LoanTypeTotalReportBean)this.getSqlMapClientTemplate().queryForObject("getAllMonthLoanBal", map);
    }

    @Override
    public LoanTypeTotalReportBean getKDBMonthLoanBal(Map map) {
        return (LoanTypeTotalReportBean)this.getSqlMapClientTemplate().queryForObject("getKDBMonthLoanBal", map);
    }
}


