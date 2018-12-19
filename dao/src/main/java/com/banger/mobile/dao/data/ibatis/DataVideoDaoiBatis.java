/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :客户资料 dao实现
 * Author     :yuanme
 * Create Date:2012-5-29
 */
package com.banger.mobile.dao.data.ibatis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.dao.data.CustomerDataDao;
import com.banger.mobile.dao.data.DataVideoDao;
import com.banger.mobile.domain.model.data.CustomerData;
import com.banger.mobile.domain.model.data.Event;
import com.banger.mobile.domain.model.data.Video;
import com.banger.mobile.domain.model.dept.DeptUserBean;
import com.banger.mobile.ibatis.GenericDaoiBatis;
/**
 * @author yuanme
 * @version $Id: DataAuthDaoiBatis.java,v 0.1 2012-5-29 上午10:51:00 yuanme Exp $
 */
public class DataVideoDaoiBatis extends DataSuperDaoiBatis implements DataVideoDao {

    public DataVideoDaoiBatis() {
        super(DataVideoDao.class);
    }

    /**
     * @param persistentClass
     */
    @SuppressWarnings("unchecked")
    public DataVideoDaoiBatis(Class persistentClass) {
        super(DataVideoDao.class);
    }


    public List<Video> queryVideoData(Map<String, Object> parameterMap,
                                      Page page) {

        List<Video> videoList = this.findQueryPage("queryVideoData", "queryVideoDataCount", parameterMap, page);
        return videoList;
    }


	public String getVideoRemark(int videoId) {

		return (String) this.getSqlMapClientTemplate().queryForObject("getVideoRemark", videoId);
	}

	public void updateVideoRemark(Video video) {
		this.getSqlMapClientTemplate().update("updateVideoRemark", video);

	}

	public void addNewVideo(Video video) {
		this.getSqlMapClientTemplate().insert("addNewVideo", video);

	}

	/**
 	 * 批量添加视频资料
 	 */
 	public void addNewVideoBatch(List<Video> videos){
 		this.exectuteBatchInsert("addNewVideo", videos);
 	}


    //以下为新资料管理功能
    /**
     * 根据客户查看视频列表（分页）
     * @param parameterMap
     * @param page
     * @return
     */
    public PageUtil<Video> getCustomerVideoDataPage(Map<String, Object> parameterMap, Page page) {
        List<Video> list = (List<Video>) this.findQueryPage(
            "getCustomerVideoDataPage", "getCustomerVideoDataCount", parameterMap, page);
        return new PageUtil<Video>(list, page);
    }

    public List<Video> getVideoDataByUUID(Video video) {
        return this.getSqlMapClientTemplate().queryForList("getVideoDataByUUID", video);
    }

    /**
     * 获得贷款中各贷款流程的视频资料信息
     *
     * @param paramMap
     * @return
     */
    @Override
    public List<Video> getVideoOnLoanFlow(Map<String,Object> paramMap){
        return this.getSqlMapClientTemplate().queryForList("getVideoOnLoanFlow",paramMap);
    }

    /**
     * 删除视频
     * @param paramMap
     */
    @Override
    public void delVideoById(Map<String,Object> paramMap){
        this.getSqlMapClientTemplate().delete("delVideoById",paramMap);
    }

    public List<Video> selectVideoListByLoanId(Integer loanId){
    	return this.getSqlMapClientTemplate().queryForList("selectVideoListByLoanId", loanId);
    }
}
