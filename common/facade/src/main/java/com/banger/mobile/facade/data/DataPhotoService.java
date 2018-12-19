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
import com.banger.mobile.domain.model.data.Photo;
import com.banger.mobile.domain.model.data.SearchData;

/**
 * @author yuanme
 * @version $Id: CustomerDataService.java,v 0.1 2012-5-24 下午04:42:52 yuanme Exp $
 */
public interface DataPhotoService extends DataSuperService{
   
    
    /**
     * 搜索照片
     * @param parmeterMap 存储条件的Map
     * @param page
     * @return 
     */
    public List<Photo> queryPhotoData(Map<String, Object> parameterMap, Page page);
 	
 	/**
     * 拿到旧的照片备注信息
     * @param photoId 照片的ID
     * @return  Old remark
     */
 	public String getPhotoRemark(int photoId);
 	
 	/**
     * 修改备注信息
     * @param photo 对象
     */
 	public void updatePhotoRemark(Photo photo);
 	
 	/**
     * 根据id获得照片的信息
     * @param photo 对象
     */
 	public Photo getPhotoById(int id);

    public Photo getPhotoByPhotoId(Integer photoId);

    public List<Photo> getPhotoByTypeId(Map<String,Integer> map);
 	
 	/**
 	 * 新增
 	 * @param photo
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
    public PageUtil<Photo> getCustomerPhotoDataPage(Map<String, Object> parameterMap, Page page,SearchData searchData);

    //查询 非分页
    public List<Photo> getCustomerPhotoDataList(Map<String,Object> parameterMap);

    //更新全字段
    public void updatePhoto(Photo photo);

    //申请贷款保存照片
    void uploadLoanPhoto(CustomerData data, Integer userId, Integer photoTypeId, String photoPath,String photoName);
    
    /**
     * 查看前复制照片文件到服务器临时目录下
     * @return path 临时目录下的文件全名
     */
    public String copyFileToTemp(String oldFilePath,String oldFileName);
    
    /**
     * 根据numrow 及条件查询照片信息
     * @param parameterMap
     * @return Photo
     */
    public Photo getPhotoByNumRow(Map<String, Object> parameterMap);

    // 根据uuid删除图片
    public void deletePhoto(String uuid);

    void delPhotoById(Map<String, Object> map);
    //批量逻辑删除photoId
    Integer updatePhotoById(Map<String, Object> paramMap);

    Photo getPhotoByUUID(Photo photo);
  //图片详情菜单
    public List<Photo> getPhotoTtpeMenu(Integer eventId,Integer loanId);
    //图片详情列表
    public  List<Photo> getPhotoListInfo(Integer eventId,Integer loanId,Integer photoTypeId);
    //估计照片编号照片分类
    public void updatePhotoTypeById(String photoIds,Integer photoTypeId);
    
    public List<Photo> selectPhotoListByLoanId(Integer loanId);
}
