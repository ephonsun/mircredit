package com.banger.mobile.dao.loan;

import com.banger.mobile.domain.model.loan.LnLoanInfoDictionary;

import java.util.List;
import java.util.Map;

/**
 * @author ouyl
 * @version $Id: LnLoanInfoDictionaryDao.java,v 0.1 13-7-1 ouyl Exp $
 */
public interface LnLoanInfoDictionaryDao {
    /**
     * 插入贷款信息字典
     * @param lnLoanInfoDictionary
     */
    public void insertLoanInfoDictionary(LnLoanInfoDictionary lnLoanInfoDictionary);

    /**
     * 更新贷款信息字典
     * @param lnLoanInfoDictionary
     */
    public void updateLoanInfoDictionary(LnLoanInfoDictionary lnLoanInfoDictionary);

    /**
     * 查询贷款信息字典
     * @param param
     * @return
     */
    public List<LnLoanInfoDictionary> selectLoanInfoDictionaryList(Map<String ,Object> param);
    
    public String getDictionaryValue(String dictionaryName,String dictionaryKey);
    
}
