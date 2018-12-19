package com.banger.mobile.dao.loan.ibatis;

import com.banger.mobile.dao.loan.LnLoanInfoDictionaryDao;
import com.banger.mobile.domain.model.loan.LnLoanInfoDictionary;
import com.banger.mobile.ibatis.GenericDaoiBatis;
import org.apache.commons.lang.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ouyl
 * @version $Id: LnLoanInfoDictionaryDaoiBatis.java,v 0.1 13-7-1 ouyl Exp $
 */
public class LnLoanInfoDictionaryDaoiBatis extends GenericDaoiBatis implements LnLoanInfoDictionaryDao {

    public LnLoanInfoDictionaryDaoiBatis(){
        super(LnLoanInfoDictionary.class);
    }

    public LnLoanInfoDictionaryDaoiBatis(Class persistentClass){
        super(persistentClass);
    }
    /**
     * 插入贷款信息字典
     * @param lnLoanInfoDictionary
     */
    public void insertLoanInfoDictionary(LnLoanInfoDictionary lnLoanInfoDictionary){
        this.getSqlMapClientTemplate().insert("insertLoanInfoDictionary",lnLoanInfoDictionary);
    }

    /**
     * 更新贷款信息字典
     * @param lnLoanInfoDictionary
     */
    public void updateLoanInfoDictionary(LnLoanInfoDictionary lnLoanInfoDictionary){
        this.getSqlMapClientTemplate().update("updateLoanInfoDictionary",lnLoanInfoDictionary);
    }

    /**
     * 查询贷款信息字典
     * @param param
     * @return
     */
    public List<LnLoanInfoDictionary> selectLoanInfoDictionaryList(Map<String ,Object> param){
        return this.getSqlMapClientTemplate().queryForList("selectLoanInfoDictionaryList",param);
    }
    
    public String getDictionaryValue(String dictionaryName,String dictionaryKey){
        if(StringUtils.isNotBlank(dictionaryKey)){
            Map<String ,Object> param = new HashMap<String ,Object>();
            param.put("dictionaryName", dictionaryName);
            if (dictionaryKey.equals("")){
                param.put("dictionaryKey", "-1");
            }else{
                param.put("dictionaryKey", dictionaryKey);
            }

            Object returnValue=this.getSqlMapClientTemplate().queryForObject("getDictionaryValue", param);
            if(returnValue == null){
                return "";
            }else{
                return (String)  returnValue;
            }
        }else{
            return "";
        }

    }
}
