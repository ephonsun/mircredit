/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :客户资料 dao实现
 * Author     :yuanme
 * Create Date:2012-5-29
 */
package com.banger.mobile.dao.data.ibatis;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.dao.data.DataAudioDao;
import com.banger.mobile.domain.model.base.data.BaseDatAudio;
import com.banger.mobile.domain.model.data.Audio;

import java.util.List;
import java.util.Map;
/**
 * @author yuanme
 * @version $Id: DataAuthDaoiBatis.java,v 0.1 2012-5-29 上午10:51:00 yuanme Exp $
 */
public class DataAudioDaoiBatis extends DataSuperDaoiBatis implements DataAudioDao {

    public DataAudioDaoiBatis() {
        super(DataAudioDao.class);
    }

    /**
     * @param persistentClass
     */
    @SuppressWarnings("unchecked")
    public DataAudioDaoiBatis(Class persistentClass) {
        super(DataAudioDao.class);
    }


    public List<Audio> queryAudioData(Map<String, Object> parameterMap,
                                      Page page) {

        List<Audio> audioList = this.findQueryPage("queryAudioData", "queryAudioDataCount", parameterMap, page);
        return audioList;
    }

    public String getAudioRemark(int audioId) {

        return (String) this.getSqlMapClientTemplate().queryForObject("getAudioRemark", audioId);
    }

    public void updateAudioRemark(Audio audio) {
        this.getSqlMapClientTemplate().update("updateAudioRemark", audio);

    }

    public void addNewAudio(Audio audio) {
        this.getSqlMapClientTemplate().insert("addNewAudio", audio);

    }

    /**
     * 批量添加录音资料
     */
    public void addNewAudioBatch(List<Audio> audios) {
        this.exectuteBatchInsert("addNewAudio", audios);

    }

    //以下为新资料管理功能
    /**
     * 根据客户查看录音列表（分页）
     * @param parameterMap
     * @param page
     * @return
     */
    public PageUtil<Audio> getCustomerAudioDataPage(Map<String, Object> parameterMap, Page page) {
        List<Audio> list = (List<Audio>) this.findQueryPage(
                "getCustomerAudioDataPage", "getCustomerAudioDataCount", parameterMap, page);
        return new PageUtil<Audio>(list, page);
    }

    public List<Audio> getAudioDataByUUID(Audio audio) {
        return this.getSqlMapClientTemplate().queryForList("getAudioDataByUUID", audio);
    }

    /**
     * 获得贷款中各贷款流程的录音资料信息
     *
     * @param paramMap
     * @return
     */
    @Override
    public List<Audio> getAudioOnLoanFlow(Map<String,Object> paramMap){
        return this.getSqlMapClientTemplate().queryForList("getAudioOnLoanFlow",paramMap);
    }

    /**
     * 删除录音
     * @param paramMap
     */
    @Override
    public void delAudioById(Map<String,Object> paramMap){
        this.getSqlMapClientTemplate().delete("delAudioById",paramMap);
    }

    /**
     * 获取最近一笔录音资料
     *
     * @param paramMap
     * @return
     */
    @Override
    public BaseDatAudio getLastAudio(Map<String,Object> paramMap){
        return (BaseDatAudio)this.getSqlMapClientTemplate().queryForObject("getLastAudio",paramMap);
    }

    public void updateAudioRemarkByRecordNo(Map<String, Object> paramMap) {
        this.getSqlMapClientTemplate().update("updateAudioRemarkByRecordNo", paramMap);
    }

    public List<Audio> selectAudioListByLoanId(Integer loanId){
        return this.getSqlMapClientTemplate().queryForList("selectAudioListByLoanId",loanId);
    }
}
