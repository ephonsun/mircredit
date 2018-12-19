/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :客户资料 dao实现
 * Author     :yuanme
 * Create Date:2012-5-29
 */
package com.banger.mobile.dao.data.ibatis;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.dao.data.CustomerDataDao;
import com.banger.mobile.dao.data.DataPhotoDao;
import com.banger.mobile.domain.model.data.CustomerData;
import com.banger.mobile.domain.model.data.Event;
import com.banger.mobile.domain.model.data.Photo;
import com.banger.mobile.domain.model.dept.DeptUserBean;
import com.banger.mobile.ibatis.GenericDaoiBatis;
/**
 * @author yuanme
 * @version $Id: DataAuthDaoiBatis.java,v 0.1 2012-5-29 上午10:51:00 yuanme Exp $
 */
public class DataPhotoDaoiBatis extends DataSuperDaoiBatis implements DataPhotoDao {

    public DataPhotoDaoiBatis() {
        super(Photo.class);
    }

    /**
     * @param persistentClass
     */
    @SuppressWarnings("unchecked")
    public DataPhotoDaoiBatis(Class persistentClass) {
        super(persistentClass);
    }


    public List<Photo> queryPhotoData(Map<String, Object> parameterMap,
                                      Page page) {

        List<Photo> photoList = this.findQueryPage("queryPhotoData", "queryPhotoDataCount", parameterMap, page);
        return photoList;
    }


    public String getPhotoRemark(int photoId) {

        return (String) this.getSqlMapClientTemplate().queryForObject("getPhotoRemark", photoId);
    }

    public void updatePhotoRemark(Photo photo) {
        this.getSqlMapClientTemplate().update("updatePhotoRemark", photo);

    }

    public Photo getPhotoById(Serializable photoId) {
        return (Photo) this.getSqlMapClientTemplate().queryForObject("getPhotoById", photoId);
    }

    public Photo getPhotoByPhotoId(Integer photoId){
        return (Photo) this.getSqlMapClientTemplate().queryForObject("getPhotoByPhotoId", photoId);
    }

    public void addNewPhoto(Photo photo){
        this.getSqlMapClientTemplate().insert("addNewPhoto", photo);
    }

    public List<Photo> getPhotoByTypeId(Map<String,Integer> map){
        return this.getSqlMapClientTemplate().queryForList("getPhotoByTypeId",map);
    }

    /**
     * 批量添加照片资料
     * @param photos
     */
    public void addNewPhotoBatch(List<Photo> photos){
        this.exectuteBatchInsert("addNewPhoto", photos);
    }

    //以下为新资料管理功能
    /**
     * 根据客户查看照片列表（分页）
     * @param parameterMap
     * @param page
     * @return
     */
    public PageUtil<Photo> getCustomerPhotoDataPage(Map<String, Object> parameterMap, Page page) {
        List<Photo> list = (List<Photo>) this.findQueryPage(
                "getCustomerPhotoDataPage", "getCustomerPhotoDataCount", parameterMap, page);
        return new PageUtil<Photo>(list, page);
    }

    //查询 非分页
    public List<Photo> getCustomerPhotoDataList(Map<String, Object> parameterMap) {
        return this.getSqlMapClientTemplate().queryForList("getCustomerPhotoData",parameterMap);
    }

    //更新全字段
    public void updatePhoto(Photo photo) {
        this.getSqlMapClientTemplate().update("updatePhoto", photo);
    }


    /**
     * 根据numrow 及条件查询照片信息
     * @param parameterMap
     * @return Photo
     */
    public Photo getPhotoByNumRow(Map<String, Object> parameterMap) {
        return (Photo)this.getSqlMapClientTemplate().queryForObject("getPhotoByNumRow",parameterMap);
    }

    public List<Photo> getPhotoDataByUUID(Photo photo) {
        return this.getSqlMapClientTemplate().queryForList("getPhotoDataByUUID", photo);
    }

    // 根据图片uuid删除图片
    public void deletePhoto(String uuid) {
        this.getSqlMapClientTemplate().delete("deletePhoto", uuid);
    }

    /**
     * 根据图片id删除财图片
     *
     * @param map
     */
    @Override
    public void delPhotoById(Map<String,Object> map){
        this.getSqlMapClientTemplate().delete("delPhotoById",map);
    }

    /**
     * 伪删除图片
     *
     * @param paramMap
     * @return
     */
    @Override
    public Integer updatePhotoById(Map<String, Object> paramMap){
        return (Integer)this.getSqlMapClientTemplate().update("updatePhotoById",paramMap);
    }

    /**
     * @param photo
     * @return
     */
    @Override
    public Photo getPhotoByUUID(Photo photo){
        return (Photo)this.getSqlMapClientTemplate().queryForObject("getPhotoByUUID",photo);
    }

    @Override
    public List<Photo> getPhotoTypeMenu(Map<String,Object> parameterMap) {
        return this.getSqlMapClientTemplate().queryForList("getPhotoTypeMenu",parameterMap);
    }

    @Override
    public List<Photo> getPhotoListInfo(Map<String, Object> parameterMap) {
        // TODO Auto-generated method stub
        return this.getSqlMapClientTemplate().queryForList("getPhotoListInfo",parameterMap);
    }

    @Override
    public void updatePhotoTypeById(Map<String, Object> parameterMap) {
        // TODO Auto-generated method stub
        this.getSqlMapClientTemplate().update("updatePhotoType",parameterMap);
    }

    public List<Photo> selectPhotoListByLoanId(Integer loanId){
        return this.getSqlMapClientTemplate().queryForList("selectPhotoListByLoanId",loanId);
    }
}
