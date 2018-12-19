/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :yuanme
 * Create Date:2012-11-14
 */
package com.banger.mobile.facade.impl.data;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.axis.utils.StringUtils;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.dao.data.CustomerDataDao;
import com.banger.mobile.dao.data.DataVideoDao;
import com.banger.mobile.domain.model.data.Audio;
import com.banger.mobile.domain.model.data.CustomerData;
import com.banger.mobile.domain.model.data.Event;
import com.banger.mobile.domain.model.data.SearchData;
import com.banger.mobile.domain.model.data.Video;
import com.banger.mobile.facade.data.DataVideoService;
import com.banger.mobile.util.StringUtil;

/**
 * @author yuanme
 * @version $Id: CustomerDataServiceImpl.java,v 0.1 2012-11-14 下午5:27:03 Administrator Exp $
 */
public class DataVideoServiceImpl extends DataSuperServiceImpl implements DataVideoService {
    private DataVideoDao dataVideoDao;
    SimpleDateFormat df0=new SimpleDateFormat("yyyy-MM-dd HH:mm:00");
    SimpleDateFormat df1=new SimpleDateFormat("yyyy-MM-dd HH:mm:59");
    public void setDataVideoDao(DataVideoDao dataVideoDao) {
		this.dataVideoDao = dataVideoDao;
	}
	
    /**
     * 根据查询条件 查找视频信息 搜索
     * @param parameterMap
     * @return
     */
	public List<Video> queryVideoData(Map<String, Object> parameterMap,
			Page page) {
		return dataVideoDao.queryVideoData(parameterMap,page);
	}
	
	/**
     * 获得旧的视频备注信息
     * @param videoId
     * @return
     */
	public String getVideoRemark(int videoId) {
		
		return dataVideoDao.getVideoRemark(videoId);
	}
	
	/**
     * 修改视频备注信息
     * @param video
     * @return
     */
	public void updateVideoRemark(Video video) {
		dataVideoDao.updateVideoRemark(video);
		
	}

	public void addNewVideo(Video video) {
	    if (StringUtils.isEmpty(video.getDatUuid())) {
            UUID uuid = UUID.randomUUID();
            video.setDatUuid(uuid.toString());
        }
        //根据uuid去判断是否已经上传过
        List<Video> list = dataVideoDao.getVideoDataByUUID(video);
        if (list == null || list.size() == 0) {
            dataVideoDao.addNewVideo(video);
        }
	}

	/**
 	 * 批量添加视频资料
 	 */
 	public void addNewVideoBatch(List<Video> videos){
 		dataVideoDao.addNewVideoBatch(videos);
 	}


	//以下为新资料管理功能
	/**
     * 根据客户查看视频列表（分页）
     * @param parameterMap
     * @param page
     * @return 
     */
    public PageUtil<Video> getCustomerVideoDataPage(Map<String, Object> parameterMap, Page page,SearchData searchData){
        if(searchData!=null){
            parameterMap.put("dataName", StringUtil.ReplaceSQLChar(searchData.getDataName().trim()));
            if(searchData.getCreateStartDate()!=null)parameterMap.put("createStartDate", df0.format(searchData.getCreateStartDate()));
            if(searchData.getCreateEndDate()!=null)parameterMap.put("createEndDate", df1.format(searchData.getCreateEndDate()));
            if(searchData.getUploadStartDate()!=null)parameterMap.put("uploadStartDate", df0.format(searchData.getUploadStartDate()));
            if(searchData.getUploadEndDate()!=null)parameterMap.put("uploadEndDate", df1.format(searchData.getUploadEndDate()));
        }
    	return dataVideoDao.getCustomerVideoDataPage(parameterMap, page);
    }

    /**
     * 获得贷款中各贷款流程的视频资料信息
     *
     * @param paramMap
     * @return
     */
    public List<Video> getVideoOnLoanFlow(Map<String, Object> paramMap){
        return dataVideoDao.getVideoOnLoanFlow(paramMap);
    }

    /**
     * 删除视频
     * @param paramMap
     */
    public void delVideoById(Map<String, Object> paramMap){
        dataVideoDao.delVideoById(paramMap);
    }
    
    public List<Video> selectVideoListByLoanId(Integer loanId){
    	return dataVideoDao.selectVideoListByLoanId(loanId);
    }
    
}
