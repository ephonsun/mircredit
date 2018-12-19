package com.banger.mobile.facade.impl.uploadFile;

import com.banger.mobile.dao.data.CustomerDataDao;
import com.banger.mobile.dao.loan.LnLoanDao;
import com.banger.mobile.dao.loan.LnLoanInfoDao;
import com.banger.mobile.dao.uploadFile.SysUploadFileDao;
import com.banger.mobile.domain.model.base.customer.BaseCrmCustomer;
import com.banger.mobile.domain.model.data.CmDownloadUrl;
import com.banger.mobile.domain.model.data.CmsFileInfo;
import com.banger.mobile.domain.model.data.CustomerData;
import com.banger.mobile.domain.model.data.LoanData;
import com.banger.mobile.domain.model.loan.LnLoan;
import com.banger.mobile.domain.model.loan.LnLoanInfo;
import com.banger.mobile.domain.model.uploadFile.SysUploadFile;
import com.banger.mobile.domain.model.user.SysUser;
import com.banger.mobile.facade.customer.CrmCustomerService;
import com.banger.mobile.facade.data.CaseHelperService;
import com.banger.mobile.facade.dept.DeptService;
import com.banger.mobile.facade.param.SysParamService;
import com.banger.mobile.facade.uploadFile.SysUploadFileService;
import com.banger.mobile.facade.user.SysUserService;
import com.banger.mobile.util.DateUtil;
import com.banger.mobile.util.FileUtil;
import com.banger.mobile.util.ServerRealPathUtil;
import com.topcheer.client.CustomerImageManage;
import com.topcheer.model.XmlElement;
import com.topcheer.util.ParameterAuthority;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

/**
 * @author ouyl
 * @version $Id: SysUploadFileServiceImpl.java,v 0.1 13-6-5 ouyl Exp $
 */
public class SysUploadFileServiceImpl implements SysUploadFileService {

    private static Logger logger = Logger.getLogger(SysUploadFileServiceImpl.class);
    private SysUploadFileDao sysUploadFileDao;
    private SysParamService sysParamService;
    private CaseHelperService caseHelperService;
    private SysUserService sysUserService;
    private LnLoanInfoDao lnLoanInfoDao;
    private LnLoanDao lnLoanDao;
    private DeptService deptService;
    private CrmCustomerService crmCustomerService;
    private CustomerDataDao customerDataDao;
    private String storageType;
    private int packageSize = 1024;
    private String host;
    private int port;
    private String appid;
    private Integer support;
    private String interfaceUserId;

    public void setCustomerDataDao(CustomerDataDao customerDataDao) {
        this.customerDataDao = customerDataDao;
    }

    public void setCrmCustomerService(CrmCustomerService crmCustomerService) {
        this.crmCustomerService = crmCustomerService;
    }

    public void setDeptService(DeptService deptService) {
        this.deptService = deptService;
    }

    public void setLnLoanInfoDao(LnLoanInfoDao lnLoanInfoDao) {
        this.lnLoanInfoDao = lnLoanInfoDao;
    }

    public void setLnLoanDao(LnLoanDao lnLoanDao) {
        this.lnLoanDao = lnLoanDao;
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

    public SysUserService getSysUserService() {
        return sysUserService;
    }

    public void setSysUserService(SysUserService sysUserService) {
        this.sysUserService = sysUserService;
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

    public HttpServletResponse getResponse() {
        return ServletActionContext.getResponse();
    }

    public void setSysParamService(SysParamService sysParamService) {
        this.sysParamService = sysParamService;
    }

    public SysUploadFileDao getSysUploadFileDao() {
        return sysUploadFileDao;
    }

    public void setSysUploadFileDao(SysUploadFileDao sysUploadFileDao) {
        this.sysUploadFileDao = sysUploadFileDao;
    }

    public void setCaseHelperService(CaseHelperService caseHelperService) {
        this.caseHelperService = caseHelperService;
    }

    /**
     * 新增上传文件
     *
     * @param sysUploadFile
     */
    public Integer insertSysUploadFile(SysUploadFile sysUploadFile) {
        sysUploadFileDao.insertSysUploadFile(sysUploadFile);
        return sysUploadFile.getFileId();
    }

    /**
     * 批量新增上传文件
     *
     * @param list
     */
    public void insertSysUploadFileBatch(List<SysUploadFile> list) {
        sysUploadFileDao.insertSysUploadFileBatch(list);
    }

    /**
     * 更新上传文件
     *
     * @param sysUploadFile
     */
    public void updateSysUploadFile(SysUploadFile sysUploadFile) {
        sysUploadFileDao.updateSysUploadFile(sysUploadFile);
    }

    /**
     * 删除上传文件
     *
     * @param fileId
     */
    public void deleteSysUploadFile(Integer fileId) {
        sysUploadFileDao.deleteSysUploadFile(fileId);
    }

    /**
     * 根据文件Id获取文件信息
     *
     * @param fileId
     * @return
     */
    public SysUploadFile getSysUploadFileById(Integer fileId) {
        return sysUploadFileDao.getSysUploadFileById(fileId);
    }

    /**
     * 保存文件到数据库并copy文件
     *
     * @param fileInput
     * @param fileInputName
     * @param userId
     * @param needRename    保存的文件是否需要重命名
     * @param caseNo        案卷号，需要在业务代码里面传入
     * @param data          案卷号，需要在业务代码里面传入
     * @return
     */
    public int saveFile(File fileInput, String fileInputName, Integer userId, Boolean needRename,
                        String caseNo, CustomerData data) throws IOException {
        return saveLocalFileStorge(fileInput, fileInputName, userId, needRename, caseNo);
    }
    /**
     * 保存文件到数据库并copy文件
     *
     * @param fileInput
     * @param fileInputName
     * @param userId
     * @param needRename    保存的文件是否需要重命名
     * @param caseNo        案卷号，需要在业务代码里面传入
     * @param data          案卷号，需要在业务代码里面传入
     * @return
     */
    @Override
    public int saveStaticFile(File fileInput, String fileInputName, Integer userId, Boolean needRename,
                              String caseNo, CustomerData data) throws IOException {
        return saveLocalStaticFileStorge(fileInput, fileInputName, userId, needRename, caseNo);
    }

    // 保存到本地文件系统
    private int saveLocalStaticFileStorge(File fileInput, String fileInputName, Integer userId,
                                    Boolean needRename, String caseNo) throws IOException {
        String savePath =  ServerRealPathUtil.getServerRealPath()  + "/home/record/"
                + DateUtil.convertDateToString("yyyyMMdd", new Date());
        FileUtil.createDir(savePath);

        String newName = "";
        if (needRename) {
            // 如果保存的文件需要重命名
            newName = "pic_13800000000_" + "" + String.valueOf(System.currentTimeMillis())
                    + new Random().nextInt()
                    + fileInputName.substring(fileInputName.lastIndexOf("."));
        } else {
            newName = fileInput.getName();
        }

        // 将文件上传到服务器
        File imageFile = new File(savePath + File.separator + newName);

        // 复制文件
        InputStream in = null;
        OutputStream out = null;
        try {
            in = new BufferedInputStream(new FileInputStream(fileInput), 4 * 1024);
            out = new BufferedOutputStream(new FileOutputStream(imageFile), 4 * 1024);
            byte[] bs = new byte[4 * 1024];
            int i;
            while ((i = in.read(bs)) > 0) {
                out.write(bs, 0, i);
            }
            out.flush();
        } catch (IOException ex) {
            throw ex;// 把异常继续抛出到上层
        } finally {
            if (in != null)
                in.close();
            if (out != null)
                out.close();
        }

        // 入库
        SysUploadFile sysUploadFile = new SysUploadFile();
        sysUploadFile.setCaseNo(caseNo);
        sysUploadFile.setUploadFileName(fileInputName);
        sysUploadFile.setFileSize(fileInput.length());
        sysUploadFile.setFileName(newName);
        sysUploadFile.setFilePath(savePath);
        // sysUploadFile.setUploadDate(new Date());
        sysUploadFile.setStorageType("file");
        sysUploadFile.setUploadUserId(userId);
        sysUploadFile.setCreateUser(userId);
        sysUploadFile.setUpdateUser(userId);
        // sysUploadFile.setCreateDate(new Date());
        sysUploadFile.setUpdateDate(new Date());
        Integer fileId = insertSysUploadFile(sysUploadFile);

        return fileId;
    }

    public int saveFile(File fileInput, String fileInputName, Integer userId, Boolean needRename) throws IOException{
    	return saveLocalFileStorge(fileInput, fileInputName, userId, needRename,"");
    }
    // 保存到本地文件系统
    private int saveLocalFileStorge(File fileInput, String fileInputName, Integer userId,
                                    Boolean needRename, String caseNo) throws IOException {
        String savePath = sysParamService.getRecordPath() + "/"
                + DateUtil.convertDateToString("yyyyMMdd", new Date());
        FileUtil.createDir(savePath);

        String newName = "";
        if (needRename) {
            // 如果保存的文件需要重命名
            newName = "pic_13800000000_" + "" + String.valueOf(System.currentTimeMillis())
                    + new Random().nextInt()
                    + fileInputName.substring(fileInputName.lastIndexOf("."));
        } else {
            newName = fileInput.getName();
        }

        // 将文件上传到服务器
        File imageFile = new File(savePath + File.separator + newName);

        // 复制文件
        InputStream in = null;
        OutputStream out = null;
        try {
            in = new BufferedInputStream(new FileInputStream(fileInput), 4 * 1024);
            out = new BufferedOutputStream(new FileOutputStream(imageFile), 4 * 1024);
            byte[] bs = new byte[4 * 1024];
            int i;
            while ((i = in.read(bs)) > 0) {
                out.write(bs, 0, i);
            }
            out.flush();
        } catch (IOException ex) {
            throw ex;// 把异常继续抛出到上层
        } finally {
            if (in != null)
                in.close();
            if (out != null)
                out.close();
        }

        // 入库
        SysUploadFile sysUploadFile = new SysUploadFile();
        sysUploadFile.setCaseNo(caseNo);
        sysUploadFile.setUploadFileName(fileInputName);
        sysUploadFile.setFileSize(fileInput.length());
        sysUploadFile.setFileName(newName);
        sysUploadFile.setFilePath(savePath);
        // sysUploadFile.setUploadDate(new Date());
        sysUploadFile.setStorageType("file");
        sysUploadFile.setUploadUserId(userId);
        sysUploadFile.setCreateUser(userId);
        sysUploadFile.setUpdateUser(userId);
        // sysUploadFile.setCreateDate(new Date());
        sysUploadFile.setUpdateDate(new Date());
        Integer fileId = insertSysUploadFile(sysUploadFile);

        return fileId;
    }
    
    public File getRealFile(String cmsFilePath,  String cmsFileName, String cmsFileId) {
        File result = null;

        String savePath = sysParamService.getRecordPath() + "/download";
        FileUtil.createDir(savePath);

        File f = new File(savePath + "/" + cmsFileName);
        if (f.exists()) {
            return f;
        }
        HttpClient client = new HttpClient();
        GetMethod httpGet = new GetMethod(cmsFilePath);
        try {
            client.executeMethod(httpGet);
            InputStream in = httpGet.getResponseBodyAsStream();
            result = new File(savePath + "/" + cmsFileName);
            FileOutputStream out = new FileOutputStream(result);
            byte[] b = new byte[2048];
            int len = 0;
            while ((len = in.read(b)) != -1) {
                out.write(b, 0, len);
            }
            in.close();
            out.close();

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            httpGet.releaseConnection();
        }
        logger.info("download, success!!" + cmsFileName);

        return result;
    }
    
    /**
     * 下载文件
     *
     * @param fileId
     */
    public void downFile(Integer fileId, CustomerData customerData) {
        SysUploadFile sysUploadFile = getSysUploadFileById(fileId);
        File file = new File(sysUploadFile.getFilePath() + File.separator + sysUploadFile.getFileName());



        if (!file.exists()) {
            // 如果文件不存在，则表示缓存已经被删除，要去影像系统重新下载
            String caseNo = sysUploadFile.getCaseNo();
            String realFileUrl = "";
            if (StringUtils.isNotEmpty(caseNo)) {
                List<CmDownloadUrl> list = caseHelperService.getCaseDownloadUrl(caseNo,
                        customerData);
                for (CmDownloadUrl c : list) {
                    if (c.getFileName().equals(sysUploadFile.getFileName())) {
                        realFileUrl = c.getDownloadUrl();
                        break;
                    }
                }
            }

            String savePath = sysParamService.getRecordPath() + "/download";
            FileUtil.createDir(savePath);
            HttpClient client = new HttpClient();
            GetMethod httpGet = new GetMethod(realFileUrl);
            try {
                client.executeMethod(httpGet);
                InputStream in = httpGet.getResponseBodyAsStream();
                file = new File(savePath + "/" + sysUploadFile.getFileName());
                FileOutputStream out = new FileOutputStream(file);
                byte[] b = new byte[2048];
                int len = 0;
                while ((len = in.read(b)) != -1) {
                    out.write(b, 0, len);
                }
                in.close();
                out.close();

            } catch (Exception e) {
                logger.error("download, failed!!" + sysUploadFile.getFileName());
            } finally {
                httpGet.releaseConnection();
            }
            logger.info("download, success!!" + sysUploadFile.getFileName());

            // 移动文件到存储点，作为缓存
            String today = DateUtil.getDateTime("yyyyMMdd", Calendar.getInstance().getTime());
            String targetDir = sysParamService.getRecordPath() + "/" + today;
            FileUtil.createDir(targetDir);
            FileUtil.copyFile2Dir(file, targetDir);

            // 更新sys_upload_file
            sysUploadFile.setFilePath(targetDir);
            sysUploadFileDao.updateSysUploadFile(sysUploadFile);
        }

        // 往ie输出文件流
        try {
            FileInputStream fis = new FileInputStream(file);// 服务器文件路径
            getResponse()
                    .addHeader(
                            "Content-Disposition",
                            "attachment;filename="
                                    + new String(sysUploadFile.getUploadFileName().getBytes("gbk"),
                                    "iso8859-1"));
            getResponse().addHeader("Content-Length", "" + file.length());

            getResponse().setContentType("application/octet-stream"); // 设置返回的文件类型
            OutputStream output = getResponse().getOutputStream(); // 得到向客户端输出二进制数据的对象
            BufferedInputStream bis = new BufferedInputStream(fis);// 输入缓冲流
            BufferedOutputStream bos = new BufferedOutputStream(output);// 输出缓冲流

            byte data[] = new byte[4096];// 缓冲字节数

            int size = 0;
            size = bis.read(data);
            while (size != -1) {
                bos.write(data, 0, size);
                size = bis.read(data);
            }
            bis.close();
            bos.flush();// 清空输出缓冲流
            bos.close();
            output.close();
        } catch (Exception e) {
            logger.error(e);
        }
    }

    /**
     * 根据fileId取得文件对象
     */
    public File getRealFile(Integer fileId, CustomerData customerData) {
    	//TODO:修改
        File result = null;
        SysUploadFile sysUploadFile = getSysUploadFileById(fileId);
        if (sysUploadFile != null) {
            if (customerData.getCreateUser() == null) {
                customerData.setCreateUser(sysUploadFile.getUploadUserId());
            }
            result = new File(sysUploadFile.getFilePath() + File.separator
                    + sysUploadFile.getFileName());

            if (!result.exists()) {
                // 保存在第三方接口CM
                if (sysUploadFile.getStorageType() != null
                        && sysUploadFile.getStorageType().equalsIgnoreCase("cm")) {
                    String caseNo = sysUploadFile.getCaseNo();
                    String realFileUrl = "";
                    if (StringUtils.isNotEmpty(caseNo) && caseNo.contains("loan_")) {
                        String[] caseArray = caseNo.split("_");
                        if (caseArray.length >= 3) {
                            Integer loanId = Integer.valueOf(caseArray[1]);
                            Integer eventId = Integer.valueOf(caseArray[2]);

                            Map<String, Object> conds = new HashMap<String, Object>();
                            conds.put("loanId", loanId);
                            List<LnLoanInfo> lnLoanInfoList = lnLoanInfoDao.selectLoanInfoList(conds);
                            LnLoanInfo lnLoanInfo = lnLoanInfoList.get(0);
                            LnLoan lnLoan = lnLoanDao.getLnLoanById(loanId);

                           /* //如果eventId=2,3,4表示上会阶段影像
                            if (eventId == 2 || eventId == 3 || eventId == 4) {
                                List<CmsFileInfo> cmsListAll = this.download(lnLoan, lnLoanInfo.getCaseNo1(), "business");
                                for (CmsFileInfo cmsFileInfo : cmsListAll) {
                                    if (cmsFileInfo.getFileName().equalsIgnoreCase(sysUploadFile.getFileName())) {
                                        realFileUrl = cmsFileInfo.getFilePath();
                                        break;
                                    }
                                }
                            }

                            //如果是5，6，7表示放款阶段
                            if (eventId == 5 || eventId == 6 || eventId == 7) {
                                List<CmsFileInfo> cmsListAllFK = this.download(lnLoan, lnLoanInfo.getCaseNo2(), "credit");
                                for (CmsFileInfo cmsFileInfo : cmsListAllFK) {
                                    if (cmsFileInfo.getFileName().equalsIgnoreCase(sysUploadFile.getFileName())) {
                                        realFileUrl = cmsFileInfo.getFilePath();
                                        break;
                                    }
                                }
                            }*/
                        } else if (caseArray.length == 2) {
                            Integer loanId = Integer.valueOf(caseArray[1]);
                            Integer eventId = 0;
                            // 所有资料
                            Map<String, Object> paramMap = new HashMap<String, Object>();
                            paramMap.put("loanId", loanId);
                            List<LoanData> dataList = customerDataDao.getAllLoanDataById(paramMap);
                            for (LoanData data : dataList) {
                                if (data.getFileId().equals(sysUploadFile.getFileId())) {
                                    eventId = data.getEventId();
                                    break;
                                }
                            }

                            Map<String, Object> conds = new HashMap<String, Object>();
                            conds.put("loanId", loanId);
                            List<LnLoanInfo> lnLoanInfoList = lnLoanInfoDao.selectLoanInfoList(conds);
                            LnLoanInfo lnLoanInfo = lnLoanInfoList.get(0);
                            LnLoan lnLoan = lnLoanDao.getLnLoanById(loanId);
                            //如果eventId=2,3,4表示上会阶段影像
                            /*if (eventId == 2 || eventId == 3 || eventId == 4) {
                                List<CmsFileInfo> cmsListAll = this.download(lnLoan, lnLoanInfo.getCaseNo1(), "business");
                                for (CmsFileInfo cmsFileInfo : cmsListAll) {
                                    if (cmsFileInfo.getFileName().equalsIgnoreCase(sysUploadFile.getFileName())) {
                                        realFileUrl = cmsFileInfo.getFilePath();
                                        break;
                                    }
                                }
                            }

                            //如果是5，6，7表示放款阶段
                            if (eventId == 5 || eventId == 6 || eventId == 7) {
                                List<CmsFileInfo> cmsListAllFK = this.download(lnLoan, lnLoanInfo.getCaseNo2(), "credit");
                                for (CmsFileInfo cmsFileInfo : cmsListAllFK) {
                                    if (cmsFileInfo.getFileName().equalsIgnoreCase(sysUploadFile.getFileName())) {
                                        realFileUrl = cmsFileInfo.getFilePath();
                                        break;
                                    }
                                }
                            }*/
                        }
                    }

                    String savePath = sysParamService.getRecordPath() + "/download";
                    FileUtil.createDir(savePath);
                    HttpClient client = new HttpClient();
                    GetMethod httpGet = new GetMethod(realFileUrl);
                    try {
                        client.executeMethod(httpGet);
                        InputStream in = httpGet.getResponseBodyAsStream();
                        result = new File(savePath + "/" + sysUploadFile.getFileName());
                        FileOutputStream out = new FileOutputStream(result);
                        byte[] b = new byte[2048];
                        int len = 0;
                        while ((len = in.read(b)) != -1) {
                            out.write(b, 0, len);
                        }
                        in.close();
                        out.close();

                    } catch (Exception e) {
                        logger.error(e.getMessage(), e);
                    } finally {
                        httpGet.releaseConnection();
                    }
                    logger.info("download, success!!" + sysUploadFile.getFileName());

                    // 移动文件到存储点，作为缓存
                    String today = DateUtil.getDateTime("yyyyMMdd", Calendar.getInstance()
                            .getTime());
                    String targetDir = sysParamService.getRecordPath() + "/" + today;
                    FileUtil.createDir(targetDir);
                    FileUtil.copyFile2Dir(result, targetDir);

                    // 更新sys_upload_file
                    sysUploadFile.setFilePath(targetDir);
                    sysUploadFileDao.updateSysUploadFile(sysUploadFile);
                }
            }
        }

        return result;
        
    }

    public List<CmsFileInfo> download(LnLoan lnLoan, String caseNo, String dataType) {
    	//TODO:修改
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
    	 
    }

    public SysUploadFile queryByFileName(String fileName) {
        return sysUploadFileDao.queryByFileName(fileName);
    }

    public List<SysUploadFile> queryForUplaodCms() {
        return sysUploadFileDao.queryForUplaodCms();
    }

    public List<SysUploadFile> queryForDeleteCms(String date) {
        return sysUploadFileDao.queryForDeleteCms(date);
    }

    public void batchUpdateStorageType(List<SysUploadFile> fileNameList) {
        sysUploadFileDao.batchUpdateStorageType(fileNameList);
    }
    public List<SysUploadFile> queryForUploadFormList(String loanId){
    	return sysUploadFileDao.queryForUploadFormList(loanId);
    }
}
