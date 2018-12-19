package com.banger.mobile.facade.impl.record;

import com.banger.mobile.domain.model.uploadFile.SysUploadFile;
import com.banger.mobile.facade.param.SysParamService;
import com.banger.mobile.facade.record.IDelTempRecordJobFacade;
import com.banger.mobile.facade.uploadFile.SysUploadFileService;
import com.banger.mobile.util.ServerRealPathUtil;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class DelTempRecordJobImpl implements IDelTempRecordJobFacade {
    private static final Logger logger = Logger.getLogger(DelTempRecordJobImpl.class);

    private SysUploadFileService sysUploadFileService;
    private SysParamService sysParamService;
    private String deleteTempFileDays;

    private Integer isRun;
    /**
     * 删除临时录音
     */
    public void doDelTempRecJob() {
        if (isRun.equals(1)) {
        try {
            String SysPath = ServerRealPathUtil.getServerRealPath() + "Records";// 获取服务器的绝对地址
            logger.info("待删除的临时播放文件夹目录为:" + SysPath);
            delAllFile(SysPath);
        } catch (Exception e) {
            logger.error("待删除的临时播放文件夹目录为", e);
        }

        try {
            // 删除常熟影像系统临时文件
            String SysPath2 = sysParamService.getRecordPath() + "/download";
            logger.info("删除常熟影像系统临时文件:" + SysPath2);
            delAllFile(SysPath2);
        } catch (Exception e) {
            logger.error("删除常熟影像系统临时文件 error", e);
        }

        try {
            // 删除缓存影像 sys_upload_file.storage_type=cm 并且更新日期是3个月前的今天
            Calendar cal = Calendar.getInstance();
            int days = -90;
            if (StringUtils.isNotEmpty(deleteTempFileDays)) {
                days = 0 - Integer.valueOf(deleteTempFileDays);
            }
            cal.add(Calendar.DAY_OF_MONTH, days);
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            List<SysUploadFile> list = sysUploadFileService.queryForDeleteCms(df.format(cal
                    .getTime()));
            for (SysUploadFile file : list) {
                File delFile = new File(file.getFilePath() + "/" + file.getFileName());
                if (delFile != null && delFile.exists()){
                    FileUtils.forceDelete(delFile);
                }else {
                    logger.info("文件'"+file.getFilePath()+"/"+file.getFileName()+"'不存在");
                }
                delFile = null;
            }
            logger.info("删除缓存影像:" + list.size());
        } catch (Exception e) {
            logger.error("删除缓存影像", e);
        }
        }
    }

    /**
     * 删除指定文件夹下所有文件
     *
     * @param path
     * @return
     */
    public static boolean delAllFile(String path) throws IOException {
        boolean flag = true;
        String[] extensions = {"jpg", "jpeg", "gif", "bmp", "png", "mp3", "amr", "aac", "wav",
                "3gp"};
        // 使用listFiles搜索
        File toPath = new File(path);
        if (toPath.exists()) {
            Collection files = FileUtils.listFiles(toPath, extensions, true);
            logger.info("删除缓存文件数量：" + files.size());
            // 迭代输出
            for (Iterator iterator = files.iterator(); iterator.hasNext(); ) {
                File file = (File) iterator.next();
                FileUtils.forceDelete(file);
            }
        }

        return flag;
    }


    public void setIsRun(Integer isRun) {
        this.isRun = isRun;
    }

    public Integer getIsRun() {
        return isRun;
    }

    public void setSysUploadFileService(SysUploadFileService sysUploadFileService) {
        this.sysUploadFileService = sysUploadFileService;
    }

    public void setSysParamService(SysParamService sysParamService) {
        this.sysParamService = sysParamService;
    }

    public void setDeleteTempFileDays(String deleteTempFileDays) {
        this.deleteTempFileDays = deleteTempFileDays;
    }
}
