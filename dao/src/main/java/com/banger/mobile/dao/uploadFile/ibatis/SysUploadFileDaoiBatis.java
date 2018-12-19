package com.banger.mobile.dao.uploadFile.ibatis;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.banger.mobile.dao.uploadFile.SysUploadFileDao;
import com.banger.mobile.domain.model.data.Photo;
import com.banger.mobile.domain.model.uploadFile.SysUploadFile;
import com.banger.mobile.ibatis.GenericDaoiBatis;

/**
 * @author ouyl
 * @version $Id: SysUploadFileDaoiBatis.java v 0.1 ${} 上午10:39 ouyl Exp $
 */
public class SysUploadFileDaoiBatis extends GenericDaoiBatis implements SysUploadFileDao {
    public SysUploadFileDaoiBatis(){
        super(SysUploadFile.class);
    }
    /**
     * Constructor that takes in a class to see which type of entity to persist
     *
     * @param persistentClass the class type you'd like to persist
     */
    public SysUploadFileDaoiBatis(Class persistentClass) {
        super(persistentClass);
    }
    public List<SysUploadFile> queryForUploadFormList(String loanId){
    	Map<String, Object> param = new HashMap<String, Object>();
    	param.put("loanId", loanId);
    	  return this.getSqlMapClientTemplate().queryForList("queryForUploadFormList",param) ;
    }
    public void insertSysUploadFile(SysUploadFile sysUploadFile) {
         this.getSqlMapClientTemplate().insert("insertSysUploadFile",sysUploadFile) ;

    }
    
    public void insertSysUploadFileBatch(List<SysUploadFile> list) {
    	this.exectuteBatchInsert("insertSysUploadFile", list);
    }

    public void updateSysUploadFile(SysUploadFile sysUploadFile) {
        this.getSqlMapClientTemplate().update("updateSysUploadFile",sysUploadFile);
    }

    public void deleteSysUploadFile(Integer fileId) {
        this.getSqlMapClientTemplate().delete("deleteSysUploadFile",fileId);
    }

    public SysUploadFile getSysUploadFileById(Integer fileId) {
    	
        return (SysUploadFile) this.getSqlMapClientTemplate().queryForObject("getSysUploadFileById",fileId);
    }

    public SysUploadFile queryByFileName(String fileName) {
        return (SysUploadFile) this.getSqlMapClientTemplate().queryForObject("queryByFileName",fileName);
    }

    public List<SysUploadFile> queryForUplaodCms() {
        return this.getSqlMapClientTemplate().queryForList("queryForUplaodCms") ;
    }

    public List<SysUploadFile> queryForDeleteCms(String date) {
        return this.getSqlMapClientTemplate().queryForList("queryForDeleteCms", date) ;
    }

    public void batchUpdateStorageType(List<SysUploadFile> fileNameList) {
        this.executeBatchUpdate("batchUpdateStorageType", fileNameList);
    }
    public List<Photo> getPhotoListInfo(Map<String, Object> parameterMap) {
        // TODO Auto-generated method stub
        return this.getSqlMapClientTemplate().queryForList("getPhotoListInfo",parameterMap);
    }



}
