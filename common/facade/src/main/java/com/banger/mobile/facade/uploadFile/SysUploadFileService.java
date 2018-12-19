/*
 * banger Inc.
 * Copyright (c) 2009-2013 All Rights Reserved.
 * ToDo       :文件上传service
 * Author     :ouyl
 * Create Date:2013-5-24
 */
package com.banger.mobile.facade.uploadFile;

import com.banger.mobile.domain.model.data.CustomerData;
import com.banger.mobile.domain.model.uploadFile.SysUploadFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * @author ouyl
 * @version $Id: SysUploadFileService.java v 0.1 ${} 下午2:35 ouyl Exp $
 */
public interface SysUploadFileService {

    /**
     * 新增上传文件
     * @param sysUploadFile
     */
    public Integer insertSysUploadFile(SysUploadFile sysUploadFile);
    
    /**
     * 批量新增上传文件
     * @param list
     */
    public void insertSysUploadFileBatch(List<SysUploadFile> list);

    /**
     * 更新上传文件
     * @param sysUploadFile
     */
    public void updateSysUploadFile(SysUploadFile sysUploadFile);

    /**
     * 删除上传文件
     * @param fileId
     */
    public void deleteSysUploadFile(Integer fileId);

    /**
     * 根据文件Id获取文件信息
     * @param fileId
     * @return
     */
    public SysUploadFile getSysUploadFileById(Integer fileId);

    /**
     * 保存文件到数据库并copy文件
     * @param fileInput
     * @param fileInputName
     * @param userId
     * @param needRename
     * @return
     */
    public int saveFile(File fileInput,String fileInputName,Integer userId, Boolean needRename, String caseNo,CustomerData data) throws IOException;

    int saveStaticFile(File fileInput, String fileInputName, Integer userId, Boolean needRename,
                       String caseNo, CustomerData data) throws IOException;

    /**
     * 
     * @param fileInput
     * @param fileInputName
     * @param userId
     * @param needRename
     * @return
     * @throws IOException
     */
    public int saveFile(File fileInput, String fileInputName, Integer userId, Boolean needRename) throws IOException;
    /**
     * 下载文件
     * @param fileId
     */
    public void downFile(Integer fileId,CustomerData data);
    
    /**
     * 得到实际文件File
     */
    public File getRealFile(Integer fileId,CustomerData data);

    public SysUploadFile queryByFileName(String fileName);

    public List<SysUploadFile> queryForUplaodCms();
    /**
     * 查询关联附件列表
     * @param loanId
     * @return
     */
    public List<SysUploadFile> queryForUploadFormList(String loanId);
    public List<SysUploadFile> queryForDeleteCms(String date);

    public void batchUpdateStorageType(List<SysUploadFile> fileNameList);

    public File getRealFile(String cmsFilePath, String cmsFileName, String cmsFileId);
}
