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
import com.banger.mobile.domain.model.data.Audio;
import com.banger.mobile.domain.model.data.CustomerData;
import com.banger.mobile.domain.model.data.Event;
import com.banger.mobile.domain.model.data.SearchData;
import com.banger.mobile.domain.model.data.Video;

/**
 * @author yuanme
 * @version $Id: CustomerDataService.java,v 0.1 2012-5-24 下午04:42:52 yuanme Exp $
 */
public interface DataVideoService extends DataSuperService{
   
    
    /**
     * 搜索视频
     * @param parmeterMap 存储条件的Map
     * @param page
     * @return 
     */
    public List<Video> queryVideoData(Map<String, Object> parameterMap, Page page);
 	
 	/**
     * 拿到旧的视频备注信息
     * @param videoId 视频的ID
     * @return  Old remark
     */
 	public String getVideoRemark(int videoId);
 	
 	/**
     * 修改备注信息
     * @param video 对象
     */
 	public void updateVideoRemark(Video video);
 	/**
 	 * 增加
 	 * @param video
 	 */
 	public void addNewVideo(Video video);
 	
 	/**
 	 * 批量添加视频资料
 	 */
 	public void addNewVideoBatch(List<Video> videos);
 	
	//以下为新资料管理功能
	/**
     * 根据客户查看视频列表（分页）
     * @param parameterMap
     * @param page
     * @return 
     */
    public PageUtil<Video> getCustomerVideoDataPage(Map<String, Object> parameterMap, Page page,SearchData searchData);

    List<Video> getVideoOnLoanFlow(Map<String, Object> paramMap);

    void delVideoById(Map<String, Object> paramMap);
    
    List<Video> selectVideoListByLoanId(Integer loanId);
}
