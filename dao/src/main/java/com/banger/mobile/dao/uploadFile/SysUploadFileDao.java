package com.banger.mobile.dao.uploadFile;

import java.util.Date;
import java.util.List;

import com.banger.mobile.domain.model.uploadFile.SysUploadFile;

/**
 * @author ouyl
 * @version $Id: SysUploadFileDao.java v 0.1 ${} 下午4:43 ouyl Exp $
 */
public interface SysUploadFileDao {

	/**
	 * 查询附件列表
	 * @param loanId
	 * @return
	 */
	public List<SysUploadFile> queryForUploadFormList(String loanId);
    /**
     * 新增上传文件
     * @param sysUploadFile
     */
    public void insertSysUploadFile(SysUploadFile sysUploadFile);

    /**
     * 新增上传文件
     * @param sysUploadFile
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

    public SysUploadFile queryByFileName(String fileName);

    public List<SysUploadFile> queryForUplaodCms();

    public List<SysUploadFile> queryForDeleteCms(String date);

    public void batchUpdateStorageType(List<SysUploadFile> fileNameList);
}
