package com.banger.mobile.facade.uploadFile;

import com.banger.mobile.domain.model.data.CmsFileInfo;
import com.banger.mobile.domain.model.loan.LnLoan;

import java.io.File;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-1-27
 * Time: 上午8:52
 * To change this template use File | Settings | File Templates.
 */
public interface UploadFileToCMS {
    public void doJob();

    /**
     * 上传文件到影像系统
     * @param loanId 贷款id
     */
    public int upload(Integer loanId);

    /**
     * 下载影像系统文件
     * @param caseNo 案卷号
     * @param dataType 案卷类型
     * @return
     */
    public List<CmsFileInfo> download(LnLoan lnLoan, String caseNo, String dataType);

    /**
     * 上传征信文件到影像系统
       */
    public int uploadToZX(String caseNo, Integer loanId, Integer cusId, File up, File down, File auth);
}
