/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :yuanme
 * Create Date:2012-11-14
 */
package com.banger.mobile.facade.impl.data;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.dao.data.DataAudioDao;
import com.banger.mobile.domain.model.base.data.BaseDatAudio;
import com.banger.mobile.domain.model.data.Audio;
import com.banger.mobile.domain.model.data.Form;
import com.banger.mobile.domain.model.data.SearchData;
import com.banger.mobile.facade.data.DataAudioService;
import com.banger.mobile.util.StringUtil;

import org.apache.axis.utils.StringUtils;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author yuanme
 * @version $Id: CustomerDataServiceImpl.java,v 0.1 2012-11-14 下午5:27:03 Administrator Exp $
 */
public class DataAudioServiceImpl extends DataSuperServiceImpl implements DataAudioService {
    private DataAudioDao dataAudioDao;
    SimpleDateFormat df0=new SimpleDateFormat("yyyy-MM-dd HH:mm:00");
    SimpleDateFormat df1=new SimpleDateFormat("yyyy-MM-dd HH:mm:59");
    
    public void setDataAudioDao(DataAudioDao dataAudioDao) {
		this.dataAudioDao = dataAudioDao;
	}
	
    /**
     * 根据查询条件 查找录音信息 搜索
     * @param parameterMap
     * @return
     */
	public List<Audio> queryAudioData(Map<String, Object> parameterMap,
			Page page) {
		return dataAudioDao.queryAudioData(parameterMap,page);
	}
	
	/**
     * 获得旧的录音备注信息
     * @param audioId
     * @return
     */
	public String getAudioRemark(int audioId) {
		
		return dataAudioDao.getAudioRemark(audioId);
	}
	
	/**
     * 修改录音备注信息
     * @param audio
     * @return
     */
	public void updateAudioRemark(Audio audio) {
		dataAudioDao.updateAudioRemark(audio);
		
	}

	public void addNewAudio(Audio audio) {
        //根据uuid去判断是否已经上传过
	    if (StringUtils.isEmpty(audio.getDatUuid())) {
	        UUID uuid = UUID.randomUUID();
	        audio.setDatUuid(uuid.toString());
	    }
        List<Audio> list = dataAudioDao.getAudioDataByUUID(audio);
        if (list == null || list.size() == 0) {
            dataAudioDao.addNewAudio(audio);
        }
	}
	
	/**
	 * 批量添加录音资料
	 */
	public void addNewAudioBatch(List<Audio> audios) {
		dataAudioDao.addNewAudioBatch(audios);
	}

	
	//以下为新资料管理功能
	/**
     * 根据客户查看录音列表（分页）
     * @param parameterMap
     * @param page
     * @return 
     */
    public PageUtil<Audio> getCustomerAudioDataPage(Map<String, Object> parameterMap, Page page,SearchData searchData){
        if(searchData!=null){
            parameterMap.put("dataName", StringUtil.ReplaceSQLChar(searchData.getDataName().trim()));
            if(searchData.getCreateStartDate()!=null)parameterMap.put("createStartDate", df0.format(searchData.getCreateStartDate()));
            if(searchData.getCreateEndDate()!=null)parameterMap.put("createEndDate", df1.format(searchData.getCreateEndDate()));
            if(searchData.getUploadStartDate()!=null)parameterMap.put("uploadStartDate", df0.format(searchData.getUploadStartDate()));
            if(searchData.getUploadEndDate()!=null)parameterMap.put("uploadEndDate", df1.format(searchData.getUploadEndDate()));
        }
    	return dataAudioDao.getCustomerAudioDataPage(parameterMap, page);
    }

    /**
     * 获得贷款中各贷款流程的录音资料信息
     *
     * @param paramMap
     * @return
     */
    public List<Audio> getAudioOnLoanFlow(Map<String, Object> paramMap){
        return dataAudioDao.getAudioOnLoanFlow(paramMap);
    }

    /**
     * 删除录音
     * @param paramMap
     */
    public void delAudioById(Map<String,Object> paramMap){
        dataAudioDao.delAudioById(paramMap);
    }

    /**
     * 获取最近一笔录音资料
     *
     * @param paramMap
     * @return
     */
    public BaseDatAudio getLastAudio(Map<String, Object> paramMap){
        return (BaseDatAudio)dataAudioDao.getLastAudio(paramMap);
    }

    public void updateAudioRemarkByRecordNo(String recordNo, String remark) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("recordNo", recordNo);
        paramMap.put("remark", remark);
        dataAudioDao.updateAudioRemarkByRecordNo(paramMap);
    }
    
    public List<Audio> selectAudioListByLoanId(Integer loanId){
    	return dataAudioDao.selectAudioListByLoanId(loanId);
    }
    
}
