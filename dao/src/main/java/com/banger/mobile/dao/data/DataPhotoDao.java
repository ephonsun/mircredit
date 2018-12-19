/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :客户数据 dao
 * Author     :yuanme
 * Create Date:2012-5-29
 */
package com.banger.mobile.dao.data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.model.data.CustomerData;
import com.banger.mobile.domain.model.data.Event;
import com.banger.mobile.domain.model.data.Photo;

/**
 * @author yuanme
 * @version $Id: DataAuthDao.java,v 0.1 2012-5-29 上午10:50:22 yuanme Exp $
 */
public interface DataPhotoDao extends DataSuperDao{
   
 
	/**
    * 搜索视频
    * @param parmeterMap 存储条件的Map
    * @param page
    * @return
    */
	public List<Photo> queryPhotoData(Map<String, Object> parameterMap, Page page);
	
	
	/**
     * 拿到旧的视频备注信息
     * @param photoId 视频的ID
     * @return  Old remark
     */
 	public String getPhotoRemark(int photoId);
 	
 	/**
     * 修改备注信息
     * @param photo 对象
     */
 	public void updatePhotoRemark(Photo photo);
 	
 	/**
     * 根据id获得照片详细信息
     * 
     */
 	public Photo getPhotoById(Serializable id);

    public Photo getPhotoByPhotoId(Integer photoId);
    /**
     * 根据typeId获得照片详细信息
     *
     */
    public List<Photo> getPhotoByTypeId(Map<String,Integer> map);
 	/**
     * 增加
     * 
     */
 	public void addNewPhoto(Photo photo);
 	
 	
 	/**
	 * 批量添加照片资料
	 * @param photos
	 */
	public void addNewPhotoBatch(List<Photo> photos);
 	
	//以下为新资料管理功能
	/**
     * 根据客户查看照片列表（分页）
     * @param parameterMap
     * @param page
     * @return 
     */
    public PageUtil<Photo> getCustomerPhotoDataPage(Map<String, Object> parameterMap, Page page);

    //查询非分页
    public List<Photo> getCustomerPhotoDataList(Map<String,Object> parameterMap);

    //更新全字段
    public void updatePhoto(Photo photo);
    
    /**
     * 根据numrow 及条件查询照片信息
     * @param parameterMap
     * @return Photo
     */
    public Photo getPhotoByNumRow(Map<String, Object> parameterMap);

    public List<Photo> getPhotoDataByUUID(Photo photo);

    // 根据图片uuid删除图片
    public void deletePhoto(String uuid);

    void delPhotoById(Map<String, Object> map);

    Integer updatePhotoById(Map<String, Object> paramMap);

    Photo getPhotoByUUID(Photo photo);
    //图片详情菜单
    public List<Photo> getPhotoTypeMenu(Map<String,Object> parameterMap);
    //图片详情列表
    public List<Photo> getPhotoListInfo(Map<String,Object> parameterMap);
    //估计照片编号照片分类
    public void updatePhotoTypeById(Map<String,Object> parameterMap);
    
    public List<Photo> selectPhotoListByLoanId(Integer loanId);
}
