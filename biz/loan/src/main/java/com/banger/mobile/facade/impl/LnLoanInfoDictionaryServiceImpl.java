package com.banger.mobile.facade.impl;

import com.banger.mobile.dao.loan.LnLoanInfoDictionaryDao;
import com.banger.mobile.domain.model.loan.LnLoanInfoDictionary;
import com.banger.mobile.facade.loan.LnLoanInfoDictionaryService;

import java.util.List;
import java.util.Map;

/**
 * @author ouyl
 * @version $Id: LnLoanInfoDictionaryServiceImpl.java,v 0.1 13-7-1 ouyl Exp $
 */
public class LnLoanInfoDictionaryServiceImpl implements LnLoanInfoDictionaryService {
	/**
	 * 担保方式
	 */
	public static final String DB_DBFS="DBFS"; 	
	/**
	 * 贷款类型
	 */
	public static final String DB_DKLX="DKLX"; 	
	/**
	 * 抵押
	 */
	public static final String DB_DY="DY"; 
	/**
	 * 质押
	 */
	public static final String DB_ZY="ZY"; 
	/**
	 * 法律形式
	 */
	public static final String DB_FLXS="FLXS"; 
	/**
	 * 婚姻状况
	 */
	public static final String DB_HYZK="HYZK"; 
	/**
	 * 教育程度
	 */
	public static final String DB_JYCD="JYCD"; 
	/**
	 * 经营场所
	 */
	public static final String DB_JYCS="JYCS"; 
	/**
	 * 居住场所类型
	 */
	public static final String DB_JZCSLX="JZCSLX"; 
	/**
	 * 居住状况
	 */
	public static final String DB_JZZK="JZZK"; 
	/**
	 * 信息来源
	 */
	public static final String DB_XXLY="XXLY"; 
	/**
	 * 月收入水平
	 */
	public static final String DB_YSRSP="YSRSP"; 
	/**
	 * 证件类型
	 */
	public static final String DB_ZJLX="ZJLX"; 
	/**
	 * 装修情况
	 */
	public static final String DB_ZXQK="ZXQK"; 
	
    private LnLoanInfoDictionaryDao lnLoanInfoDictionaryDao;

    public void setLnLoanInfoDictionaryDao(LnLoanInfoDictionaryDao lnLoanInfoDictionaryDao) {
        this.lnLoanInfoDictionaryDao = lnLoanInfoDictionaryDao;
    }

    /**
     * 插入贷款信息字典
     * @param lnLoanInfoDictionary
     */
    public void insertLoanInfoDictionary(LnLoanInfoDictionary lnLoanInfoDictionary){
        lnLoanInfoDictionaryDao.insertLoanInfoDictionary(lnLoanInfoDictionary);
    }

    /**
     * 更新贷款信息字典
     * @param lnLoanInfoDictionary
     */
    public void updateLoanInfoDictionary(LnLoanInfoDictionary lnLoanInfoDictionary){
        lnLoanInfoDictionaryDao.updateLoanInfoDictionary(lnLoanInfoDictionary);
    }

    /**
     * 查询贷款信息字典
     * @param param
     * @return
     */
    public List<LnLoanInfoDictionary> selectLoanInfoDictionaryList(Map<String ,Object> param){
        return lnLoanInfoDictionaryDao.selectLoanInfoDictionaryList(param);
    }
    
   
    public String getDictionaryValue(String dictionaryName,String dictionaryKey){
    	return lnLoanInfoDictionaryDao.getDictionaryValue(dictionaryName,dictionaryKey);
    }
}