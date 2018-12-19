package com.banger.mobile.facade.impl.uploadFile;

import com.banger.mobile.domain.model.base.customer.BaseCrmCustomer;
import com.banger.mobile.domain.model.data.CmsFileInfo;
import com.banger.mobile.domain.model.data.CustomerData;
import com.banger.mobile.domain.model.data.LoanData;
import com.banger.mobile.domain.model.dept.SysDept;
import com.banger.mobile.domain.model.loan.LnLoan;
import com.banger.mobile.domain.model.loan.LnLoanInfo;
import com.banger.mobile.domain.model.message.MessageResult;
import com.banger.mobile.domain.model.uploadFile.SysUploadFile;
import com.banger.mobile.domain.model.user.SysUser;
import com.banger.mobile.facade.customer.CrmCustomerService;
import com.banger.mobile.facade.data.CaseHelperService;
import com.banger.mobile.facade.data.CustomerDataService;
import com.banger.mobile.facade.dept.DeptService;
import com.banger.mobile.facade.loan.LnLoanInfoService;
import com.banger.mobile.facade.loan.LnLoanService;
import com.banger.mobile.facade.uploadFile.SysUploadFileService;
import com.banger.mobile.facade.uploadFile.UploadFileToCMS;
import com.banger.mobile.facade.user.SysUserService;
import com.banger.mobile.util.FileUtil;
import com.topcheer.client.CommentInfoService;
import com.topcheer.client.CustomerImageManage;
import com.topcheer.model.*;
import com.topcheer.util.ParameterAuthority;
import com.topcheer.util.TokenHelper;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import java.io.File;
import java.util.*;

/**
 * Created with IntelliJ IDEA. User: Administrator Date: 14-1-27 Time: 上午8:54 To
 * change this template use File | Settings | File Templates.
 */
public class UploadFileToCMSImpl implements UploadFileToCMS {
    private static Logger logger = Logger.getLogger(UploadFileToCMSImpl.class);

    private SysUploadFileService sysUploadFileService;
    private CaseHelperService caseHelperService;
    private SysUserService sysUserService;
    private Integer isRun;
    private String storageType;
    private int packageSize = 1024;
    private String host;
    private int port;
    private String appid;
    private Integer support;
    private String interfaceUserId;
    private CustomerDataService customerDataService;
    private LnLoanService lnLoanService;
    private LnLoanInfoService lnLoanInfoService;
    private DeptService deptService;
    private CrmCustomerService crmCustomerService;
    private String docType1;
    private String docType2;
    private String docType3;
    private String docType4;
    private String docType5;
    private String docType6;
    private String docType7;
    private String docType8;
    private String docType9;
    private String docType10;
    private String docType11;
    private String docType12;
    private String docType13;
    private String docType14;
    private String docType15;
    private String docType16;
    private String docType20;
    private String docType21;
    private String docType22;
    private String docType23;

    private String docTypeIdcard;
    private String docTypeAuth;

    public void setDocTypeIdcard(String docTypeIdcard) {
        this.docTypeIdcard = docTypeIdcard;
    }

    public void setDocTypeAuth(String docTypeAuth) {
        this.docTypeAuth = docTypeAuth;
    }

    public void setLnLoanInfoService(LnLoanInfoService lnLoanInfoService) {
        this.lnLoanInfoService = lnLoanInfoService;
    }

    public void setDocType1(String docType1) {
        this.docType1 = docType1;
    }

    public void setDocType2(String docType2) {
        this.docType2 = docType2;
    }

    public void setDocType3(String docType3) {
        this.docType3 = docType3;
    }

    public void setDocType4(String docType4) {
        this.docType4 = docType4;
    }

    public void setDocType5(String docType5) {
        this.docType5 = docType5;
    }

    public void setDocType6(String docType6) {
        this.docType6 = docType6;
    }

    public void setDocType7(String docType7) {
        this.docType7 = docType7;
    }

    public void setDocType8(String docType8) {
        this.docType8 = docType8;
    }

    public void setDocType9(String docType9) {
        this.docType9 = docType9;
    }

    public void setDocType10(String docType10) {
        this.docType10 = docType10;
    }

    public void setDocType11(String docType11) {
        this.docType11 = docType11;
    }

    public void setDocType12(String docType12) {
        this.docType12 = docType12;
    }

    public void setDocType13(String docType13) {
        this.docType13 = docType13;
    }

    public void setDocType14(String docType14) {
        this.docType14 = docType14;
    }

    public void setDocType15(String docType15) {
        this.docType15 = docType15;
    }

    public void setDocType16(String docType16) {
        this.docType16 = docType16;
    }

    public void setDocType20(String docType20) {
        this.docType20 = docType20;
    }

    public void setDocType21(String docType21) {
        this.docType21 = docType21;
    }

    public void setDocType22(String docType22) {
        this.docType22 = docType22;
    }

    public void setDocType23(String docType23) {
        this.docType23 = docType23;
    }

    public void setCrmCustomerService(CrmCustomerService crmCustomerService) {
        this.crmCustomerService = crmCustomerService;
    }

    public void setDeptService(DeptService deptService) {
        this.deptService = deptService;
    }


    public void doJob() {
        if (isRun.equals(1) && storageType.equalsIgnoreCase("cm")) {
            // 查询sys_upload_file有caseNo,同时存储类型还是file的数据
            List<SysUploadFile> list = sysUploadFileService.queryForUplaodCms();
            logger.info("需上传文件：" + list.size());

            for (SysUploadFile sysUploadFile : list) {
                try {
                    String caseNo = sysUploadFile.getCaseNo();
                    if (StringUtils.isNotEmpty(caseNo)) {
                        logger.info("同步文件到影像系统：" + sysUploadFile.getFileName() + " 开始！！！");

                        // 上传文件至cms系统临时目录
                        UploadClient uploadClient = new UploadClient(host, port, appid, packageSize);
                        File fileInput = new File(sysUploadFile.getFilePath() + "/"
                                + sysUploadFile.getFileName());
                        String filePath = null;
                        if (support.equals(1)) {
                            SysUser sysUser = sysUserService.getSysUserById(sysUploadFile
                                    .getUploadUserId());
                            // 返回保存在DMS文件路径
                            filePath = uploadClient.sendFile(fileInput, sysUploadFile.getFileName(),
                                    sysUser.getUserCode());
                        } else {
                            // 返回保存在DMS文件路径
                            filePath = uploadClient.sendFile(fileInput, sysUploadFile.getFileName(),
                                    interfaceUserId);
                        }
                        logger.info("上传文件" + fileInput.getName() + " 返回dms文件路径：" + filePath);

                        if (filePath != null) {
                            // 调用DMS上传接口，告诉DMS有文件需要入库
                            CustomerData data = new CustomerData();
                            data.setCreateUser(sysUploadFile.getUploadUserId());
                            boolean suc = caseHelperService.uploadImage(caseNo, filePath, data);
                            if (suc) {
                                // 更新数据库
                                sysUploadFile.setStorageType("cm");
                                sysUploadFileService.updateSysUploadFile(sysUploadFile);
                                logger.info("同步文件到影像系统：" + fileInput.getName() + " 成功！！！");
                            }
                        }
                    }
                } catch (Exception e) {
                    logger.error("同步到影像系统出错", e);
                }
            }
        }
    }

    /**
     * 上传文件到影像系统
     *
     * @param loanId 贷款id
     */
    public int upload(Integer loanId) {
    	return -1;
    	/*
        logger.info("贷款ID" + loanId + "文件同步到影像系统开始...");
        LnLoan lnLoan = lnLoanService.getLnLoanById(loanId);

        //公用属性
        CustomerImageManage client = new CustomerImageManage();
        String nodeName = "BranchToChief";
        String branchLevel = "0000";
        String userId = "009527";
        String userName = "孙行者";
        String deptId = "110";
        if(lnLoan.getSurveyUserId() != null && lnLoan.getSurveyUserId() > 0) {
            SysUser sysUser = sysUserService.getSysUserById(lnLoan.getSurveyUserId());
            userId = sysUser.getOperateCode();
            userName = sysUser.getUserName();
            //SysDept sysDept = deptService.getDeptById(sysUser.getDeptId());
            deptId = sysUser.getJgm();
        } else if (lnLoan.getCreateUser() != null && lnLoan.getCreateUser() > 0) {
            SysUser sysUser = sysUserService.getSysUserById(lnLoan.getCreateUser());
            userId = sysUser.getOperateCode();
            userName = sysUser.getUserName();
            //SysDept sysDept = deptService.getDeptById(sysUser.getDeptId());
            deptId = sysUser.getJgm();
        }

        String customerId = "75461336-4";
        String customerName = "常熟市琴湖宾馆";
        if (lnLoan.getCustomerId() != null) {
            BaseCrmCustomer baseCrmCustomer = crmCustomerService.getCrmCustomerById(lnLoan.getCustomerId());
            customerId = baseCrmCustomer.getIdCard();
            customerName = baseCrmCustomer.getCustomerName();
        }

        //上会--借款人
        DocParameter doc1 = new DocParameter();
        List<FileParameter> fileList1 = new ArrayList<FileParameter>();
        doc1.setDocId("001");
        doc1.setDocName("借款人");
        doc1.setDocType(docType1);
        doc1.setDocTypeName("借款人");
        doc1.setFileList(fileList1);

        //上会--担保人
        DocParameter doc2 = new DocParameter();
        List<FileParameter> fileList2 = new ArrayList<FileParameter>();
        doc2.setDocId("002");
        doc2.setDocName("担保人");
        doc2.setDocType(docType2);
        doc2.setDocTypeName("担保人");
        doc2.setFileList(fileList2);

        //上会--征信
        DocParameter doc3 = new DocParameter();
        List<FileParameter> fileList3 = new ArrayList<FileParameter>();
        doc3.setDocId("003");
        doc3.setDocName("征信");
        doc3.setDocType(docType3);
        doc3.setDocTypeName("征信");
        doc3.setFileList(fileList3);

        //上会--抵质押物照片
        DocParameter doc4 = new DocParameter();
        List<FileParameter> fileList4 = new ArrayList<FileParameter>();
        doc4.setDocId("004");
        doc4.setDocName("抵质押物照片");
        doc4.setDocType(docType4);
        doc4.setDocTypeName("抵质押物照片");
        doc4.setFileList(fileList4);

        //上会--转贷客户的提供上次还款记录单
        DocParameter doc5 = new DocParameter();
        List<FileParameter> fileList5 = new ArrayList<FileParameter>();
        doc5.setDocId("005");
        doc5.setDocName("转贷客户的提供上次还款记录单");
        doc5.setDocType(docType5);
        doc5.setDocTypeName("转贷客户的提供上次还款记录单");
        doc5.setFileList(fileList5);

        //上会--转贷客户的提供上次调查照片、分析表格
        DocParameter doc6 = new DocParameter();
        List<FileParameter> fileList6 = new ArrayList<FileParameter>();
        doc6.setDocId("006");
        doc6.setDocName("转贷客户的提供上次调查照片、分析表格");
        doc6.setDocType(docType6);
        doc6.setDocTypeName("转贷客户的提供上次调查照片、分析表格");
        doc6.setFileList(fileList6);

        //上会--异地审核表
        DocParameter doc7 = new DocParameter();
        List<FileParameter> fileList7 = new ArrayList<FileParameter>();
        doc7.setDocId("007");
        doc7.setDocName("异地审核表");
        doc7.setDocType(docType7);
        doc7.setDocTypeName("异地审核表");
        doc7.setFileList(fileList7);

        //上会--分析表格
        DocParameter doc8 = new DocParameter();
        List<FileParameter> fileList8 = new ArrayList<FileParameter>();
        doc8.setDocId("008");
        doc8.setDocName("分析表格");
        doc8.setDocType(docType8);
        doc8.setDocTypeName("分析表格");
        doc8.setFileList(fileList8);

        //上会--审贷表
        DocParameter doc9 = new DocParameter();
        List<FileParameter> fileList9 = new ArrayList<FileParameter>();
        doc9.setDocId("009");
        doc9.setDocName("审贷表");
        doc9.setDocType(docType9);
        doc9.setDocTypeName("审贷表");
        doc9.setFileList(fileList9);

        //上会--决议表
        DocParameter doc10 = new DocParameter();
        List<FileParameter> fileList10 = new ArrayList<FileParameter>();
        doc10.setDocId("010");
        doc10.setDocName("决议表");
        doc10.setDocType(docType10);
        doc10.setDocTypeName("决议表");
        doc10.setFileList(fileList10);

        //上会--其他资料
        DocParameter doc11 = new DocParameter();
        List<FileParameter> fileList11 = new ArrayList<FileParameter>();
        doc11.setDocId("011");
        doc11.setDocName("其他资料");
        doc11.setDocType(docType11);
        doc11.setDocTypeName("其他资料");
        doc11.setFileList(fileList11);

        //上会--移动录音资料
        DocParameter doc20 = new DocParameter();
        List<FileParameter> fileList20 = new ArrayList<FileParameter>();
        doc20.setDocId("020");
        doc20.setDocName("移动录音资料");
        doc20.setDocType(docType20);
        doc20.setDocTypeName("移动录音资料");
        doc20.setFileList(fileList20);

        //上会--移动视频资料
        DocParameter doc21 = new DocParameter();
        List<FileParameter> fileList21 = new ArrayList<FileParameter>();
        doc21.setDocId("021");
        doc21.setDocName("移动视频资料");
        doc21.setDocType(docType21);
        doc21.setDocTypeName("移动视频资料");
        doc21.setFileList(fileList21);

        //放款--决议表
        DocParameter doc12 = new DocParameter();
        List<FileParameter> fileList12 = new ArrayList<FileParameter>();
        doc12.setDocId("012");
        doc12.setDocName("决议表");
        doc12.setDocType(docType12);
        doc12.setDocTypeName("决议表");
        doc12.setFileList(fileList12);

        //放款--抵质押物证明
        DocParameter doc13 = new DocParameter();
        List<FileParameter> fileList13 = new ArrayList<FileParameter>();
        doc13.setDocId("013");
        doc13.setDocName("抵质押物证明");
        doc13.setDocType(docType13);
        doc13.setDocTypeName("抵质押物证明");
        doc13.setFileList(fileList13);

        //放款--其他资料
        DocParameter doc14 = new DocParameter();
        List<FileParameter> fileList14 = new ArrayList<FileParameter>();
        doc14.setDocId("014");
        doc14.setDocName("其他资料");
        doc14.setDocType(docType14);
        doc14.setDocTypeName("其他资料");
        doc14.setFileList(fileList14);

        //放款--移动录音资料
        DocParameter doc22 = new DocParameter();
        List<FileParameter> fileList22 = new ArrayList<FileParameter>();
        doc22.setDocId("022");
        doc22.setDocName("移动录音资料");
        doc22.setDocType(docType22);
        doc22.setDocTypeName("移动录音资料");
        doc22.setFileList(fileList22);

        //放款--移动视频资料
        DocParameter doc23 = new DocParameter();
        List<FileParameter> fileList23 = new ArrayList<FileParameter>();
        doc23.setDocId("023");
        doc23.setDocName("移动视频资料");
        doc23.setDocType(docType23);
        doc23.setDocTypeName("移动视频资料");
        doc23.setFileList(fileList23);

        //得到该贷款所有资料
        Map<String, Object> photoMap = new HashMap<String, Object>();
        photoMap.put("loanId", loanId);
        photoMap.put("storageType", "file");//如果是本地文件才需要同步到影像系统
        List<LoanData> dataList = customerDataService.getAllLoanDataById(photoMap);
        for (LoanData data : dataList) {
            if (data.getEventId() == 2 || data.getEventId() == 3 || data.getEventId() == 4) {
                //申请 调查 审批 表示 上会案卷
                if (data.getDataType() == 2) { //录音资料
                    FileParameter fileParameter = new FileParameter();
                    fileParameter.setFileIndex(String.valueOf(fileList20.size() + 1));
                    fileParameter.setFileName(data.getFileName());
                    fileParameter.setFilePath(data.getFilePath() + File.separator + data.getFileName());
                    fileList20.add(fileParameter);
                } else if (data.getDataType() == 3) { //视频资料
                    FileParameter fileParameter = new FileParameter();
                    fileParameter.setFileIndex(String.valueOf(fileList21.size() + 1));
                    fileParameter.setFileName(data.getFileName());
                    fileParameter.setFilePath(data.getFilePath() + File.separator + data.getFileName());
                    fileList21.add(fileParameter);
                } else if (data.getDataType() == 1) { //照片资料
                    Integer photoTypeId = data.getPhotoTypeId();
                    if (photoTypeId == 1) {
                        FileParameter fileParameter = new FileParameter();
                        fileParameter.setFileIndex(String.valueOf(fileList1.size() + 1));
                        fileParameter.setFileName(data.getFileName());
                        fileParameter.setFilePath(data.getFilePath() + File.separator + data.getFileName());
                        fileList1.add(fileParameter);
                    } else if (photoTypeId == 2) {
                        FileParameter fileParameter = new FileParameter();
                        fileParameter.setFileIndex(String.valueOf(fileList2.size() + 1));
                        fileParameter.setFileName(data.getFileName());
                        fileParameter.setFilePath(data.getFilePath() + File.separator + data.getFileName());
                        fileList2.add(fileParameter);
                    } else if (photoTypeId == 3) {
                        FileParameter fileParameter = new FileParameter();
                        fileParameter.setFileIndex(String.valueOf(fileList3.size() + 1));
                        fileParameter.setFileName(data.getFileName());
                        fileParameter.setFilePath(data.getFilePath() + File.separator + data.getFileName());
                        fileList3.add(fileParameter);
                    } else if (photoTypeId == 4) {
                        FileParameter fileParameter = new FileParameter();
                        fileParameter.setFileIndex(String.valueOf(fileList4.size() + 1));
                        fileParameter.setFileName(data.getFileName());
                        fileParameter.setFilePath(data.getFilePath() + File.separator + data.getFileName());
                        fileList4.add(fileParameter);
                    } else if (photoTypeId == 5) {
                        FileParameter fileParameter = new FileParameter();
                        fileParameter.setFileIndex(String.valueOf(fileList5.size() + 1));
                        fileParameter.setFileName(data.getFileName());
                        fileParameter.setFilePath(data.getFilePath() + File.separator + data.getFileName());
                        fileList5.add(fileParameter);
                    } else if (photoTypeId == 6) {
                        FileParameter fileParameter = new FileParameter();
                        fileParameter.setFileIndex(String.valueOf(fileList6.size() + 1));
                        fileParameter.setFileName(data.getFileName());
                        fileParameter.setFilePath(data.getFilePath() + File.separator + data.getFileName());
                        fileList6.add(fileParameter);
                    } else if (photoTypeId == 7) {
                        FileParameter fileParameter = new FileParameter();
                        fileParameter.setFileIndex(String.valueOf(fileList7.size() + 1));
                        fileParameter.setFileName(data.getFileName());
                        fileParameter.setFilePath(data.getFilePath() + File.separator + data.getFileName());
                        fileList7.add(fileParameter);
                    } else if (photoTypeId == 8) {
                        FileParameter fileParameter = new FileParameter();
                        fileParameter.setFileIndex(String.valueOf(fileList8.size() + 1));
                        fileParameter.setFileName(data.getFileName());
                        fileParameter.setFilePath(data.getFilePath() + File.separator + data.getFileName());
                        fileList8.add(fileParameter);
                    } else if (photoTypeId == 9) {
                        FileParameter fileParameter = new FileParameter();
                        fileParameter.setFileIndex(String.valueOf(fileList9.size() + 1));
                        fileParameter.setFileName(data.getFileName());
                        fileParameter.setFilePath(data.getFilePath() + File.separator + data.getFileName());
                        fileList9.add(fileParameter);
                    } else if (photoTypeId == 10) {
                        FileParameter fileParameter = new FileParameter();
                        fileParameter.setFileIndex(String.valueOf(fileList10.size() + 1));
                        fileParameter.setFileName(data.getFileName());
                        fileParameter.setFilePath(data.getFilePath() + File.separator + data.getFileName());
                        fileList10.add(fileParameter);
                    } else if (photoTypeId == 11) {
                        FileParameter fileParameter = new FileParameter();
                        fileParameter.setFileIndex(String.valueOf(fileList11.size() + 1));
                        fileParameter.setFileName(data.getFileName());
                        fileParameter.setFilePath(data.getFilePath() + File.separator + data.getFileName());
                        fileList11.add(fileParameter);
                    }
                }
            } else if (data.getEventId() == 5 || data.getEventId() == 6 || data.getEventId() == 7) {
                //贷后 催收 放贷 表示 放款案卷
                if (data.getDataType() == 2) { //录音资料
                    FileParameter fileParameter = new FileParameter();
                    fileParameter.setFileIndex(String.valueOf(fileList22.size() + 1));
                    fileParameter.setFileName(data.getFileName());
                    fileParameter.setFilePath(data.getFilePath() + File.separator + data.getFileName());
                    fileList22.add(fileParameter);
                } else if (data.getDataType() == 3) { //视频资料
                    FileParameter fileParameter = new FileParameter();
                    fileParameter.setFileIndex(String.valueOf(fileList23.size() + 1));
                    fileParameter.setFileName(data.getFileName());
                    fileParameter.setFilePath(data.getFilePath() + File.separator + data.getFileName());
                    fileList23.add(fileParameter);
                } else if (data.getDataType() == 1) { //照片资料
                    Integer photoTypeId = data.getPhotoTypeId();
                    if (photoTypeId == 10) {
                        FileParameter fileParameter = new FileParameter();
                        fileParameter.setFileIndex(String.valueOf(fileList12.size() + 1));
                        fileParameter.setFileName(data.getFileName());
                        fileParameter.setFilePath(data.getFilePath() + File.separator + data.getFileName());
                        fileList12.add(fileParameter);
                    } else if (photoTypeId == 12) {
                        FileParameter fileParameter = new FileParameter();
                        fileParameter.setFileIndex(String.valueOf(fileList13.size() + 1));
                        fileParameter.setFileName(data.getFileName());
                        fileParameter.setFilePath(data.getFilePath() + File.separator + data.getFileName());
                        fileList13.add(fileParameter);
                    } else if (photoTypeId == 13) {
                        FileParameter fileParameter = new FileParameter();
                        fileParameter.setFileIndex(String.valueOf(fileList14.size() + 1));
                        fileParameter.setFileName(data.getFileName());
                        fileParameter.setFilePath(data.getFilePath() + File.separator + data.getFileName());
                        fileList14.add(fileParameter);
                    }
                }
            }

        }

        Map<String, Object> conds = new HashMap<String, Object>();
        conds.put("loanId", loanId);
        List<LnLoanInfo> lnLoanInfoList = lnLoanInfoService.selectLoanInfoList(conds);
        LnLoanInfo lnLoanInfo = lnLoanInfoList.get(0);

        //上会案卷所有档案
        List<DocParameter> docList = new ArrayList<DocParameter>();
        docList.add(doc1);
        docList.add(doc2);
        docList.add(doc3);
        docList.add(doc4);
        docList.add(doc5);
        docList.add(doc6);
        docList.add(doc7);
        docList.add(doc8);
        docList.add(doc9);
        docList.add(doc10);
        docList.add(doc11);
        docList.add(doc20);
        docList.add(doc21);
        String caseNoBiz = lnLoanInfo.getCaseNo1();
        String dataTypeBiz = "business";
        String busiTypeBiz = "xddjgddqzl";
        String busiNameBiz = "上会影像任务1";
        String msg = null;
        int result = 0;
        //上会阶段的才上传
        if (lnLoan.getLoanStatusId() <= 4){
            try {
                msg = client.upload(nodeName, userId, userName, deptId, dataTypeBiz, customerId, customerName, caseNoBiz, busiTypeBiz, busiNameBiz, branchLevel, docList);
                logger.info(msg);
                if (msg != null && msg.contains("status=\"0\"")) {
                    //成功后更新本系统sys_upload_file.storage_type为cm
                    List<SysUploadFile> fileNameList = new ArrayList<SysUploadFile>();
                    for (DocParameter doc : docList) {
                        List<FileParameter> fileList = doc.getFileList();
                        for (FileParameter f : fileList) {
                            SysUploadFile sysUploadFile = new SysUploadFile();
                            sysUploadFile.setFileName(f.getFileName());
                            fileNameList.add(sysUploadFile);
                        }
                    }
                    sysUploadFileService.batchUpdateStorageType(fileNameList);

                    //添加备注
                    UserBean userBean =new UserBean();
                    userBean.setUserId(userId);
                    userBean.setUserName(userName);
                    userBean.setDeptId(deptId);
                    userBean.setRoleId("60009");
                    userBean.setLoginToken(TokenHelper.getTokenString(userId, deptId));
                    userBean.setBizStep("00000");
                    List<CommentBean> commentList = new ArrayList<CommentBean>();
                    List<CmsFileInfo> cmsListAll = this.download(lnLoan, caseNoBiz, "business");
                    for (CmsFileInfo info : cmsListAll) {
                        String fileName = info.getFileName();
                        String comment = info.getComment();
                        for (LoanData data :dataList) {
                            if (data.getFileName().equals(fileName)) {
                                if (StringUtils.isEmpty(comment)) {
                                    //如果没有备注则添加
                                    CommentBean cb = new CommentBean();
                                    cb.setFileId(info.getFileId());
                                    cb.setOperateType("1");
                                    cb.setText(data.getDataName());
                                    UUID uuid = UUID.randomUUID();
                                    String uuidStr = uuid.toString().replaceAll("-","");
                                    cb.setCommentId(uuidStr);
                                    commentList.add(cb);
                                }
                            }
                        }
                    }
                    if (commentList.size() > 0) {
                        String s = new CommentInfoService().excute(userBean, commentList);
                        logger.info(s);
                    }

                    result = 1;
                }
            } catch (Exception e) {
                logger.error("", e);
            }
        } else {
            //放款案卷所有档案
            List<DocParameter> docListFK = new ArrayList<DocParameter>();
            docListFK.add(doc12);
            docListFK.add(doc13);
            docListFK.add(doc14);
            docListFK.add(doc22);
            docListFK.add(doc23);
            String dataTypeFK = "credit";
            String busiTypeFK = "xddjgdfljczl";
            String busiNameFK = "放款影像任务1";
            String caseNoFK = "";

            if (StringUtils.isNotEmpty(lnLoanInfo.getCaseNo2())) {
                caseNoFK = lnLoanInfo.getCaseNo2();
            } 
            if (StringUtils.isNotEmpty(caseNoFK)) {
                try {
                    msg = client.upload(nodeName, userId, userName, deptId, dataTypeFK, customerId, customerName, caseNoFK, busiTypeFK, busiNameFK, branchLevel, docListFK);
                    logger.info(msg);
                    if (msg != null && msg.contains("status=\"0\"")) {
                        //成功后更新本系统sys_upload_file.storage_type为cm
                        List<SysUploadFile> fileNameList = new ArrayList<SysUploadFile>();
                        for (DocParameter doc : docListFK) {
                            List<FileParameter> fileList = doc.getFileList();
                            for (FileParameter f : fileList) {
                                SysUploadFile sysUploadFile = new SysUploadFile();
                                sysUploadFile.setFileName(f.getFileName());
                                fileNameList.add(sysUploadFile);
                            }
                        }
                        sysUploadFileService.batchUpdateStorageType(fileNameList);

                        //添加备注
                        UserBean userBean =new UserBean();
                        userBean.setUserId(userId);
                        userBean.setUserName(userName);
                        userBean.setDeptId(deptId);
                        userBean.setRoleId("60009");
                        userBean.setLoginToken(TokenHelper.getTokenString(userId, deptId));
                        userBean.setBizStep("00000");
                        List<CommentBean> commentList = new ArrayList<CommentBean>();
                        List<CmsFileInfo> cmsListAll = this.download(lnLoan, caseNoFK, "credit");
                        for (CmsFileInfo info : cmsListAll) {
                            String fileName = info.getFileName();
                            String comment = info.getComment();
                            for (LoanData data : dataList) {
                                if (data.getFileName().equals(fileName)) {
                                    if (StringUtils.isEmpty(comment)) {
                                        //如果没有备注则添加
                                        CommentBean cb = new CommentBean();
                                        cb.setFileId(info.getFileId());
                                        cb.setOperateType("1");
                                        cb.setText(data.getDataName());
                                        UUID uuid = UUID.randomUUID();
                                        String uuidStr = uuid.toString().replaceAll("-","");
                                        cb.setCommentId(uuidStr);
                                        commentList.add(cb);
                                    }
                                }
                            }
                        }
                        if (commentList.size() > 0) {
                            String s = new CommentInfoService().excute(userBean, commentList);
                            logger.info(s);
                        }

                        result = 1;
                    }
                } catch (Exception e) {
                    logger.error("", e);
                }
            } else {
                result = 2;
            }
        }

        return result;
    */}

    /**
     * 上传文件到影像系统
     */
    public int uploadToZX(String caseNo, Integer loanId, Integer cusId, File up, File down, File auth) {
    	return 0;
    	/*
        logger.info("征信案卷号" + caseNo + "文件同步到影像系统开始...");
        LnLoan lnLoan = lnLoanService.getLnLoanById(loanId);

        //公用属性
        CustomerImageManage client = new CustomerImageManage();
        String nodeName = "BranchToChief";
        String branchLevel = "0000";
        String userId = "009527";
        String userName = "孙行者";
        String deptId = "110";
        if(lnLoan.getSurveyUserId() != null && lnLoan.getSurveyUserId() > 0) {
            SysUser sysUser = sysUserService.getSysUserById(lnLoan.getSurveyUserId());
            userId = sysUser.getOperateCode();
            userName = sysUser.getUserName();
            //SysDept sysDept = deptService.getDeptById(sysUser.getDeptId());
            deptId = sysUser.getJgm();
        } else if (lnLoan.getCreateUser() != null && lnLoan.getCreateUser() > 0) {
            SysUser sysUser = sysUserService.getSysUserById(lnLoan.getCreateUser());
            userId = sysUser.getOperateCode();
            userName = sysUser.getUserName();
            //SysDept sysDept = deptService.getDeptById(sysUser.getDeptId());
            deptId = sysUser.getJgm();
        }

        String customerId = "75461336-4";
        String customerName = "常熟市琴湖宾馆";
        if (lnLoan.getCustomerId() != null) {
            BaseCrmCustomer baseCrmCustomer = crmCustomerService.getCrmCustomerById(cusId);
            customerId = baseCrmCustomer.getIdCard();
            customerName = baseCrmCustomer.getCustomerName();
        }

        //身份证
        DocParameter doc1 = new DocParameter();
        List<FileParameter> fileList1 = new ArrayList<FileParameter>();
        doc1.setDocId("001");
        doc1.setDocName("身份证");
        doc1.setDocType(docTypeIdcard);
        doc1.setDocTypeName("身份证");
        doc1.setFileList(fileList1);
        FileParameter fileParameter = new FileParameter();
        fileParameter.setFileIndex(String.valueOf(1));
        fileParameter.setFileName(up.getName());
        fileParameter.setFilePath(up.getAbsolutePath());
        fileList1.add(fileParameter);
        FileParameter fileParameter2 = new FileParameter();
        fileParameter2.setFileIndex(String.valueOf(2));
        fileParameter2.setFileName(down.getName());
        fileParameter2.setFilePath(down.getAbsolutePath());
        fileList1.add(fileParameter2);

        //授权书
        DocParameter doc2 = new DocParameter();
        List<FileParameter> fileList2 = new ArrayList<FileParameter>();
        doc2.setDocId("002");
        doc2.setDocName("授权书");
        doc2.setDocType(docTypeAuth);
        doc2.setDocTypeName("授权书");
        doc2.setFileList(fileList2);
        FileParameter fileParameter3 = new FileParameter();
        fileParameter3.setFileIndex(String.valueOf(2));
        fileParameter3.setFileName(auth.getName());
        fileParameter3.setFilePath(auth.getAbsolutePath());
        fileList2.add(fileParameter3);

        List<DocParameter> docList = new ArrayList<DocParameter>();
        docList.add(doc1);
        docList.add(doc2);

        String caseNoBiz = caseNo;
        String dataTypeBiz = "business";//业务资料
        String busiTypeBiz = "zxzl";//征信资料
        String busiNameBiz = "征信影像任务1";
        String msg = null;
        int result = 0;
        try {
            msg = client.upload(nodeName, userId, userName, deptId, dataTypeBiz, customerId, customerName, caseNoBiz, busiTypeBiz, busiNameBiz, branchLevel, docList);
            logger.info(msg);
            if (msg != null && msg.contains("status=\"0\"")) {
                //成功后删除临时文件
                FileUtils.forceDelete(up);
                FileUtils.forceDelete(down);
                FileUtils.forceDelete(auth);

                result = 1;
            }
        } catch (Exception e) {
            logger.error("", e);
        }



        return result;
    */}

    /**
     * 下载影像系统文件
     *
     * @param caseNo   案卷号
     * @param dataType 案卷类型
     * @return
     */
    public List<CmsFileInfo> download(LnLoan lnLoan, String caseNo, String dataType) {
    	//TODO:修改
    	return null;
    	/*
        List<CmsFileInfo> list = new ArrayList<CmsFileInfo>();

        if (StringUtils.isEmpty(caseNo)) {
            return list;
        }

        List caseBeanList = new ArrayList();
        CustomerImageManage client = new CustomerImageManage();
        ParameterAuthority.organizeBean(caseNo, dataType, caseBeanList);
        String branchLevel = "0000";
        String month = "nomonth";
        String roleId = "60009";
        String bizStep = "90";

        //动态字段
        String userId = "009527";
        String userName = "孙行者";
        String deptId = "110";
        String customerId = "75461336-4";
        String customerName = "常熟市琴湖宾馆";
        if(lnLoan.getSurveyUserId() != null && lnLoan.getSurveyUserId() > 0) {
            SysUser sysUser = sysUserService.getSysUserById(lnLoan.getSurveyUserId());
            userId = sysUser.getOperateCode();
            userName = sysUser.getUserName();
            //SysDept sysDept = deptService.getDeptById(sysUser.getDeptId());
            deptId = sysUser.getJgm();
        } else if (lnLoan.getCreateUser() != null && lnLoan.getCreateUser() > 0) {
            SysUser sysUser = sysUserService.getSysUserById(lnLoan.getCreateUser());
            userId = sysUser.getOperateCode();
            userName = sysUser.getUserName();
            //SysDept sysDept = deptService.getDeptById(sysUser.getDeptId());
            deptId = sysUser.getJgm();
        }
        if (lnLoan.getCustomerId() != null) {
            BaseCrmCustomer baseCrmCustomer = crmCustomerService.getCrmCustomerById(lnLoan.getCustomerId());
            customerId = baseCrmCustomer.getIdCard();
            customerName = baseCrmCustomer.getCustomerName();
        }

        List<XmlElement> result;
        try {
            result = client.download(branchLevel, caseBeanList, userId, userName, deptId, roleId, bizStep, customerId, customerName, month);

            for (XmlElement ele : result) {
                String dirId = ele.getDirId();
                String dirName = ele.getDirName();
                System.out.println("root:-->Dir:" + dirId + "|Name:" + dirName);
                List<XmlElement> a = ele.getElementList();
                for (XmlElement doc : a) {
                    String docType = doc.getDocType();
                    String docTypeName = doc.getDocTypeName();
                    System.out.println("Doc:------->type:" + docType + "|Name:" + docTypeName);
                    List<XmlElement> instList = doc.getElementList();
                    if (instList.size() > 0) {
                        for (XmlElement inst : instList) {
                            String docName = doc.getDocName();
                            System.out.println("inst:------------->DocName:" + docName);
                            List<XmlElement> fileList = inst.getElementList();
                            if (fileList.size() > 0) {
                                for (XmlElement file : fileList) {
                                    String fileid = file.getFileId();
                                    String fileName = file.getFileName();
                                    String filePath = file.getFilePath();
                                    String comment = file.getComment();
                                    //String operatorName = file.getOperatorName();
                                    //String createTime = file.getCreateTime();
                                    System.out.println("file:------------->fileId:" + fileid + "||fileName:" + fileName + "||comment:" + comment + "||filePath:" + filePath);

                                    CmsFileInfo info = new CmsFileInfo();
                                    info.setFileId(fileid);
                                    info.setFileName(fileName);
                                    info.setFilePath(filePath);
                                    info.setDocType(docType);
                                    info.setDocTypeName(docTypeName);
                                    info.setComment(comment);
                                    //info.setOperatorName(operatorName);
                                    //info.setCreateTime(createTime);
                                    list.add(info);
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            logger.error("download", e);
        }

        return list;
    */}

    public void setSysUploadFileService(SysUploadFileService sysUploadFileService) {
        this.sysUploadFileService = sysUploadFileService;
    }

    public void setIsRun(Integer isRun) {
        this.isRun = isRun;
    }

    public Integer getIsRun() {
        return isRun;
    }

    public void setCaseHelperService(CaseHelperService caseHelperService) {
        this.caseHelperService = caseHelperService;
    }

    public String getInterfaceUserId() {
        return interfaceUserId;
    }

    public void setInterfaceUserId(String interfaceUserId) {
        this.interfaceUserId = interfaceUserId;
    }

    public Integer getSupport() {
        return support;
    }

    public void setSupport(Integer support) {
        this.support = support;
    }

    public int getPackageSize() {
        return packageSize;
    }

    public void setPackageSize(int packageSize) {
        this.packageSize = packageSize;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getStorageType() {
        return storageType;
    }

    public void setStorageType(String storageType) {
        this.storageType = storageType;
    }

    public void setSysUserService(SysUserService sysUserService) {
        this.sysUserService = sysUserService;
    }

    public void setCustomerDataService(CustomerDataService customerDataService) {
        this.customerDataService = customerDataService;
    }

    public void setLnLoanService(LnLoanService lnLoanService) {
        this.lnLoanService = lnLoanService;
    }
}
