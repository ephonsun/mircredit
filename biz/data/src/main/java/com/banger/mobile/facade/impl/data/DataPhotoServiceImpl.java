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
import com.banger.mobile.dao.data.DataPhotoDao;
import com.banger.mobile.domain.model.data.CustomerData;
import com.banger.mobile.domain.model.data.Form;
import com.banger.mobile.domain.model.data.Photo;
import com.banger.mobile.domain.model.data.SearchData;
import com.banger.mobile.facade.data.DataPhotoService;
import com.banger.mobile.util.StringUtil;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author yuanme
 * @version $Id: CustomerDataServiceImpl.java,v 0.1 2012-11-14 下午5:27:03
 *          Administrator Exp $
 */
public class DataPhotoServiceImpl extends DataSuperServiceImpl implements DataPhotoService {
    private DataPhotoDao dataPhotoDao;
    SimpleDateFormat     df0 = new SimpleDateFormat("yyyy-MM-dd HH:mm:00");
    SimpleDateFormat     df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:59");

    public void setDataPhotoDao(DataPhotoDao dataPhotoDao) {
        this.dataPhotoDao = dataPhotoDao;
    }

    /**
     * 根据查询条件 查找视频信息 搜索
     * 
     * @param parameterMap
     * @return
     */
    public List<Photo> queryPhotoData(Map<String, Object> parameterMap, Page page) {
        return dataPhotoDao.queryPhotoData(parameterMap, page);
    }

    /**
     * 获得旧的视频备注信息
     * 
     * @param photoId
     * @return
     */
    public String getPhotoRemark(int photoId) {

        return dataPhotoDao.getPhotoRemark(photoId);
    }

    /**
     * 修改视频备注信息
     * 
     * @param photo
     * @return
     */
    public void updatePhotoRemark(Photo photo) {
        dataPhotoDao.updatePhotoRemark(photo);

    }

    /**
     * 根据id获得照片的信息
     * 
     * @param id
     *            对象
     */
    public Photo getPhotoById(int id) {
        return dataPhotoDao.getPhotoById(id);
    }

    public Photo getPhotoByPhotoId(Integer photoId){
        return dataPhotoDao.getPhotoByPhotoId(photoId);
    }

    public List<Photo> getPhotoByTypeId(Map<String,Integer> map){
        return dataPhotoDao.getPhotoByTypeId(map);
    }
    /**
     * 新增
     */
    public void addNewPhoto(Photo photo) {
        if (StringUtils.isNotEmpty(photo.getDatUuid())) {
            // 根据uuid去判断是否已经上传过
            List<Photo> list = dataPhotoDao.getPhotoDataByUUID(photo);
            if (list == null || list.size() == 0) {
                dataPhotoDao.addNewPhoto(photo);
            }
        } else {
            // 如果photo没有uuid则直接新增
            UUID uuid = UUID.randomUUID();
            photo.setDatUuid(uuid.toString());
            dataPhotoDao.addNewPhoto(photo);
        }
    }

    /**
	 * 批量添加照片资料
	 * @param photos
	 */
    public void addNewPhotoBatch(List<Photo> photos) {
    	 dataPhotoDao.addNewPhotoBatch(photos);
    }
    
    
    // 以下为新资料管理功能
    /**
     * 根据客户查看照片列表（分页）
     * 
     * @param parameterMap
     * @param page
     * @return
     */
    public PageUtil<Photo> getCustomerPhotoDataPage(Map<String, Object> parameterMap, Page page,
                                                    SearchData searchData) {
        if (searchData != null) {
            parameterMap.put("dataName", StringUtil.ReplaceSQLChar(searchData.getDataName().trim()));
            if (searchData.getCreateStartDate() != null)
                parameterMap.put("createStartDate", df0.format(searchData.getCreateStartDate()));
            if (searchData.getCreateEndDate() != null)
                parameterMap.put("createEndDate", df1.format(searchData.getCreateEndDate()));
            if (searchData.getUploadStartDate() != null)
                parameterMap.put("uploadStartDate", df0.format(searchData.getUploadStartDate()));
            if (searchData.getUploadEndDate() != null)
                parameterMap.put("uploadEndDate", df1.format(searchData.getUploadEndDate()));
        }
        return dataPhotoDao.getCustomerPhotoDataPage(parameterMap, page);
    }

    // 查询非分页
    public List<Photo> getCustomerPhotoDataList(Map<String, Object> parameterMap) {
        return dataPhotoDao.getCustomerPhotoDataList(parameterMap);
    }

    // 更新全字段
    public void updatePhoto(Photo photo) {
        dataPhotoDao.updatePhoto(photo);
    }

    // 申请贷款保存照片
    public void uploadLoanPhoto(CustomerData data, Integer userId, Integer photoTypeId,
                                String photoPath,String photoName) {
//        Map<String, Object> parameterMap = new HashMap<String, Object>();
//        parameterMap.put("customerDataId", data.getCustomerDataId());
//        parameterMap.put("photoTypeId", photoTypeId);
//        List<Photo> pList = this.getCustomerPhotoDataList(parameterMap);
//        if (pList.size() > 0) {
//            // 更新
//            Photo photo = pList.get(0);
//            photo.setUploadDate(Calendar.getInstance().getTime());
//            photo.setUploadUserId(userId);
//            photo.setFileName(FilenameUtils.getName(photoPath));
//            photo.setFilePath(FilenameUtils.getFullPathNoEndSeparator(photoPath));
//            this.updatePhoto(photo);
//        } else {
        // 新增
        Photo photo = new Photo();
        photo.setCustomerDataId(data.getCustomerDataId());
        if (photoTypeId == 1) {
            photo.setPhotoName("头像照片");
        } else if (photoTypeId == 2) {
            photo.setPhotoName("身份证正面");
        } else if (photoTypeId == 3) {
            photo.setPhotoName("身份证反面");
        }

        String[] pathAndIdArr = photoPath.split("@");
        String path = pathAndIdArr[0];
        Integer fileId = Integer.parseInt(pathAndIdArr[1]);

        photo.setPhotoName(photoName);
        photo.setFileId(fileId);
        photo.setPhotoTypeId(photoTypeId);
        photo.setUploadDate(Calendar.getInstance().getTime());
        photo.setUploadUserId(userId);
//            photo.setFileName(FilenameUtils.getName(photoPath));
        photo.setFileName(FilenameUtils.getName(path));
        photo.setFilePath(FilenameUtils.getFullPathNoEndSeparator(path));
        this.addNewPhoto(photo);
//        }
    }

    /**
     * 查看前复制照片文件到服务器临时目录下
     * 
     * @return path 临时目录下的文件全名
     */
    public String copyFileToTemp(String oldFilePath, String oldFileName) {
        try {
            if (!oldFilePath.isEmpty() && !oldFileName.isEmpty()) {
                HttpServletRequest req = ServletActionContext.getRequest();
                String path = req.getRealPath("/") + "Records";
                File file = new File(oldFilePath + "/" + oldFileName);
                File newFile = new File(path + "/" + oldFileName);
                FileUtils.copyFile(file, newFile);
                return "../Records/" + oldFileName;
            }
            return null;
        } catch (Exception e) {
            return null;
        }

    }

    /**
     * 根据numrow 及条件查询照片信息
     * 
     * @param parameterMap
     * @return Photo
     */
    public Photo getPhotoByNumRow(Map<String, Object> parameterMap) {
        return this.dataPhotoDao.getPhotoByNumRow(parameterMap);
    }

    // 根据图片uuid删除图片
    public void deletePhoto(String uuid) {
        this.dataPhotoDao.deletePhoto(uuid);
    }

    /**
     * 根据图片id删除财图片
     *
     * @param map
     */
    public void delPhotoById(Map<String, Object> map){
        this.dataPhotoDao.delPhotoById(map);
    }

    /**
     * 伪删除图片
     *
     * @param paramMap
     * @return
     */
    public Integer updatePhotoById(Map<String, Object> paramMap){
        return dataPhotoDao.updatePhotoById(paramMap);
    }

    /**
     * @param photo
     * @return
     */
    public Photo getPhotoByUUID(Photo photo){
        return dataPhotoDao.getPhotoByUUID(photo);
    }

	@Override
	public List<Photo> getPhotoTtpeMenu(Integer eventId,Integer loanId) {
		Map<String, Object> parameterMap = new  HashMap<String, Object>();
		parameterMap.put("eventId", eventId);
		parameterMap.put("loanId", loanId);
		return dataPhotoDao.getPhotoTypeMenu(parameterMap);
	}

	@Override
	public List<Photo> getPhotoListInfo(Integer eventId,Integer loanId,Integer photoTypeId) {
		Map<String, Object> parameterMap = new  HashMap<String, Object>();
		parameterMap.put("eventId", eventId);
		parameterMap.put("loanId", loanId);
		parameterMap.put("photoTypeId", photoTypeId);
		return dataPhotoDao.getPhotoListInfo(parameterMap);
	}

	@Override
	public void updatePhotoTypeById(String photoIds,Integer photoTypeId) {
		Map<String, Object> parameterMap = new  HashMap<String, Object>();
		parameterMap.put("photoIds", photoIds);
		parameterMap.put("photoTypeId", photoTypeId);
		dataPhotoDao.updatePhotoTypeById(parameterMap);
	}
	
    public List<Photo> selectPhotoListByLoanId(Integer loanId){
    	return dataPhotoDao.selectPhotoListByLoanId(loanId);
    }
    
}
