package com.banger.mobile.domain.model.points;

import com.banger.mobile.domain.model.base.points.BaseJfOrderPhoto;
import com.banger.mobile.domain.model.uploadFile.SysUploadFile;

/**
 * @author zhangfp
 * @version $Id: JfOrderPhoto v 0.1 ${} 下午4:14 zhangfp Exp $
 */
public class JfOrderPhoto extends BaseJfOrderPhoto {
    private SysUploadFile sysUploadFile;

    private String fileName;
    private String uuid;
    private String url;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public SysUploadFile getSysUploadFile() {
        return sysUploadFile;
    }

    public void setSysUploadFile(SysUploadFile sysUploadFile) {
        this.sysUploadFile = sysUploadFile;
    }
}
