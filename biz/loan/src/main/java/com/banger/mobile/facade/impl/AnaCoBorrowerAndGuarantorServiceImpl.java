package com.banger.mobile.facade.impl;

import com.banger.mobile.dao.loan.AnaCoBorrowerAndGuarantorDao;
import com.banger.mobile.domain.model.base.loan.BaseAnaCoBorrower;
import com.banger.mobile.domain.model.base.loan.BaseAnaGuarantor;
import com.banger.mobile.facade.loan.AnaCoBorrowerAndGuarantorService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author zhangfp
 * @version $Id: AnaCoBorrowerAndGuarantorServiceImpl v 0.1 ${} 下午3:19 zhangfp Exp $
 *
 * 上会分析表的共同借贷人和担保人service
 */
public class AnaCoBorrowerAndGuarantorServiceImpl implements AnaCoBorrowerAndGuarantorService{

    private AnaCoBorrowerAndGuarantorDao anaCoBorrowerAndGuarantorDao;

    public AnaCoBorrowerAndGuarantorDao getAnaCoBorrowerAndGuarantorDao() {
        return anaCoBorrowerAndGuarantorDao;
    }

    public void setAnaCoBorrowerAndGuarantorDao(AnaCoBorrowerAndGuarantorDao anaCoBorrowerAndGuarantorDao) {
        this.anaCoBorrowerAndGuarantorDao = anaCoBorrowerAndGuarantorDao;
    }

    /**
     * 得到贷款所有共同借贷人信息（属：上会分析表）
     *
     * @param paramMap
     * @return
     */
    public List<BaseAnaCoBorrower> getAllCoBorrowerListByLoan(Map<String, Object> paramMap){
        return anaCoBorrowerAndGuarantorDao.getAllCoBorrowerListByLoan(paramMap);
    }

    /**
     * 得到贷款所有担保人信息（属：上会分析表）
     *
     * @param paramMap
     * @return
     */
    public List<BaseAnaGuarantor> getAllGuarantorListByLoan(Map<String, Object> paramMap){
        return anaCoBorrowerAndGuarantorDao.getAllGuarantorListByLoan(paramMap);
    }

    /**
     * 删除贷款共同借贷人
     * @param loanId
     */
    public void delAllCoBorrowerByLoan(Integer loanId){
        anaCoBorrowerAndGuarantorDao.delAllCoBorrowerByLoan(loanId);
    }

    /**
     * 删除贷款担保人
     * @param loanId
     */
    public void delAllGuarantorByLoan(Integer loanId){
        anaCoBorrowerAndGuarantorDao.delAllGuarantorByLoan(loanId);
    }

    /**
     *
     * @param anaCoBorrower
     */
    public void insertCoBorrowerOfLoan(BaseAnaCoBorrower anaCoBorrower){
        anaCoBorrowerAndGuarantorDao.insertCoBorrowerOfLoan(anaCoBorrower);
    }

    /**
     *
     * @param baseAnaGuarantor
     */
    public void insertGuarantorOfLoan(BaseAnaGuarantor baseAnaGuarantor){
        anaCoBorrowerAndGuarantorDao.insertGuarantorOfLoan(baseAnaGuarantor);
    }

    /**
     * 批量插入
     * @param baseAnaCoBorrowerList
     */
    public void insertCoBorrowerOfLoanBatch(List<BaseAnaCoBorrower> baseAnaCoBorrowerList){
        anaCoBorrowerAndGuarantorDao.insertCoBorrowerOfLoanBatch(baseAnaCoBorrowerList);
    }

    /**
     * 批量插入
     * @param baseAnaGuarantorList
     */
    public void insertGuarantorOfLoanBatch(List<BaseAnaGuarantor> baseAnaGuarantorList){
        anaCoBorrowerAndGuarantorDao.insertGuarantorOfLoanBatch(baseAnaGuarantorList);
    }

    /**
     * 添加/编辑共同借贷人
     * @param loanId
     * @param baseAnaCoBorrowerList
     */
    @Transactional
    public void saveAnalyzesCobInfo(Integer loanId, List<BaseAnaCoBorrower> baseAnaCoBorrowerList){
        this.delAllCoBorrowerByLoan(loanId);
        if (baseAnaCoBorrowerList != null && baseAnaCoBorrowerList.size() > 0){
            this.insertCoBorrowerOfLoanBatch(baseAnaCoBorrowerList);
        }
    }

    /**
     * 添加/编辑担保人
     * @param loanId
     * @param baseAnaGuarantorList
     */
    @Transactional
    public void saveAnalyzesGuaInfo(Integer loanId, List<BaseAnaGuarantor> baseAnaGuarantorList){
        this.delAllGuarantorByLoan(loanId);
        if (baseAnaGuarantorList != null && baseAnaGuarantorList.size() > 0){
            this.insertGuarantorOfLoanBatch(baseAnaGuarantorList);
        }
    }
}
