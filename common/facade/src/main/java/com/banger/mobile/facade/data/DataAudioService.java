/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :资料业务接口
 * Author     :yuanme
 * Version    :1.0
 * Create Date:May 3, 2012
 */
package com.banger.mobile.facade.data;

import java.util.List;
import java.util.Map;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.model.base.data.BaseDatAudio;
import com.banger.mobile.domain.model.data.Audio;
import com.banger.mobile.domain.model.data.SearchData;

/**
 * @author yuanme
 * @version $Id: CustomerDataService.java,v 0.1 2012-5-24 下午04:42:52 yuanme Exp $
 */
public interface DataAudioService extends DataSuperService{
   
    
    /**
     * 搜索录音
     * @param parmeterMap 存储条件的Map
     * @param page
     * @return 
     */
    public List<Audio> queryAudioData(Map<String, Object> parameterMap, Page page);
 	
 	/**
     * 拿到旧的录音备注信息
     * @param audioId 录音的ID
     * @return  Old remark
     */
 	public String getAudioRemark(int audioId);
 	
 	/**
     * 修改备注信息
     * @param audio 对象
     */
 	public void updateAudioRemark(Audio audio);
 	
 	/**
 	 * 增加
 	 * @param audio
 	 */
 	public void addNewAudio(Audio audio);
 	
 	/**
	 * 批量添加录音资料
	 */
	public void addNewAudioBatch(List<Audio> audios) ;
 	
	//以下为新资料管理功能
	/**
     * 根据客户查看录音列表（分页）
     * @param parameterMap
     * @param page
     * @return 
     */
    public PageUtil<Audio> getCustomerAudioDataPage(Map<String, Object> parameterMap, Page page,SearchData searchData);

    List<Audio> getAudioOnLoanFlow(Map<String, Object> paramMap);

    void delAudioById(Map<String,Object> paramMap);

    BaseDatAudio getLastAudio(Map<String, Object> paramMap);

    void updateAudioRemarkByRecordNo(String recordNo, String remark);
    
    List<Audio> selectAudioListByLoanId(Integer loanId);
}
