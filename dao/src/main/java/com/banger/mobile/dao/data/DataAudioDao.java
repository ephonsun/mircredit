/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :客户数据 dao
 * Author     :yuanme
 * Create Date:2012-5-29
 */
package com.banger.mobile.dao.data;

import java.util.List;
import java.util.Map;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.model.base.data.BaseDatAudio;
import com.banger.mobile.domain.model.data.CustomerData;
import com.banger.mobile.domain.model.data.Event;
import com.banger.mobile.domain.model.data.Audio;

/**
 * @author yuanme
 * @version $Id: DataAuthDao.java,v 0.1 2012-5-29 上午10:50:22 yuanme Exp $
 */
public interface DataAudioDao extends DataSuperDao{
   
	/**
    * 搜索录音
    * @param parmeterMap 存储条件的Map
    * @param page
    * @return
    */
	public List<Audio> queryAudioData(Map<String, Object> parameterMap, Page page);
	
	
	/**
     * 拿到旧的视频备注信息
     * @param videoId 视频的ID
     * @return  Old remark
     */
 	public String getAudioRemark(int videoId);
 	
 	/**
     * 修改备注信息
     * @param video 对象
     */
 	public void updateAudioRemark(Audio video);
 	
 	/**
 	 * 增加
 	 */
 	public void addNewAudio(Audio audio);
 	
 	/**
	 * 批量添加录音资料
	 */
	public void addNewAudioBatch(List<Audio> audios);
 	
 	//以下为新资料管理功能
	/**
     * 根据客户查看录音列表（分页）
     * @param parameterMap
     * @param page
     * @return 
     */
    public PageUtil<Audio> getCustomerAudioDataPage(Map<String, Object> parameterMap, Page page);

    public List<Audio> getAudioDataByUUID(Audio audio);

    List<Audio> getAudioOnLoanFlow(Map<String, Object> paramMap);

    void delAudioById(Map<String,Object> paramMap);

    BaseDatAudio getLastAudio(Map<String, Object> paramMap);

    void updateAudioRemarkByRecordNo(Map<String,Object> paramMap);
    
    List<Audio> selectAudioListByLoanId(Integer loanId);
}
