/*package banger.mobile.webapp.action.testData;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.dao.customer.CrmCustomerDao;
import com.banger.mobile.dao.customer.FamilyNameDao;
import com.banger.mobile.dao.dept.DeptDao;
import com.banger.mobile.dao.loan.LnLoanDao;
import com.banger.mobile.dao.uploadFile.SysUploadFileDao;
import com.banger.mobile.dao.user.SysRoleMemberDao;
import com.banger.mobile.dao.user.SysUserDao;
import com.banger.mobile.domain.model.answerConfig.AnswerConfig;
import com.banger.mobile.domain.model.base.customer.BaseCrmCustomer;
import com.banger.mobile.domain.model.base.customer.BaseFamilyName;
import com.banger.mobile.domain.model.customer.CrmCustomer;
import com.banger.mobile.domain.model.customer.CrmCustomerShare;
import com.banger.mobile.domain.model.data.Audio;
import com.banger.mobile.domain.model.data.CustomerData;
import com.banger.mobile.domain.model.data.Form;
import com.banger.mobile.domain.model.data.Photo;
import com.banger.mobile.domain.model.data.Video;
import com.banger.mobile.domain.model.dept.SysDept;
import com.banger.mobile.domain.model.loan.LnLoan;
import com.banger.mobile.domain.model.microTask.TskMicroTask;
import com.banger.mobile.domain.model.microTask.TskMicroTaskExecute;
import com.banger.mobile.domain.model.record.RecordInfo;
import com.banger.mobile.domain.model.system.CdCity;
import com.banger.mobile.domain.model.system.CdProvince;
import com.banger.mobile.domain.model.task.TskTask;
import com.banger.mobile.domain.model.task.TskTaskTarget;
import com.banger.mobile.domain.model.template.CrmTemplate;
import com.banger.mobile.domain.model.templateField.CrmTemplateField;
import com.banger.mobile.domain.model.uploadFile.SysUploadFile;
import com.banger.mobile.domain.model.user.SysDeptAuth;
import com.banger.mobile.domain.model.user.SysRoleMember;
import com.banger.mobile.domain.model.user.SysUser;
import com.banger.mobile.facade.answerConfig.AnswerConfigService;
import com.banger.mobile.facade.crmRecordRemind.CrmRecordRemindService;
import com.banger.mobile.facade.crmRecordSetting.CrmRecordSettingService;
import com.banger.mobile.facade.crmRingSetting.CrmRingSettingService;
import com.banger.mobile.facade.customer.CrmCustomerService;
import com.banger.mobile.facade.data.CustomerDataService;
import com.banger.mobile.facade.data.DataAudioService;
import com.banger.mobile.facade.data.DataFormService;
import com.banger.mobile.facade.data.DataPhotoService;
import com.banger.mobile.facade.data.DataVideoService;
import com.banger.mobile.facade.loan.LnLoanService;
import com.banger.mobile.facade.microTask.TskMicroTaskExecuteService;
import com.banger.mobile.facade.microTask.TskMicroTaskService;
import com.banger.mobile.facade.phoneConfig.PhoneConfigService;
import com.banger.mobile.facade.record.RecordInfoService;
import com.banger.mobile.facade.remindConfig.RemindConfigService;
import com.banger.mobile.facade.system.CdSystemService;
import com.banger.mobile.facade.template.CrmTemplateService;
import com.banger.mobile.facade.templateField.CrmTemplateFieldService;
import com.banger.mobile.facade.uploadFile.SysUploadFileService;
import com.banger.mobile.facade.user.SysDeptAuthService;
import com.banger.mobile.util.NumberToHanziUtil;
import com.banger.mobile.util.PhoneUtil;
import com.banger.mobile.util.RandomUtil;

public class DataProviderTest extends BaseDataTestCase{
    private final static int DEPT_FH = 10;// 分行个数
    private final static int DEPT_ZH = 9;// 支行个数

    // 总用户数为 10+ 10*10+ 10*9*3
    private final static int USER_ZONGHANG = 10;// 总行行用户数
    private final static int USER_FENHANG = 10;// 分行用户数
    private final static int USER_ZHIHANG = 3;// 支行用户数；

    // 总客户数 = 总用户数 * 每个用户客户数量
    private final static int CUSTOMER_COUNT = 500; // 每个用户的客户数量

    // 总联系数 = 总用户数 * 每个用户联系记录
    private final static int REC_COUNT = 5000; // 每个用户联系记录

    // 总贷款数 = 总用户数 * 每个用户贷款记录
    private final static int LOAN_COUNT = 1000; // 每个用户联系记录

    // 总任务数 = 总用户数 * 每个用户任务数量
    private final static int TASK_COUNT = 333;// 每个用户任务数量

    // 短信发送总数 = 总用户数 * 每个用户发送短信信数量
    private final static int SMS_SEND_COUNT = 30;// 每个用户发送信数量
    
    // 资料总数 = 贷款总数  * 每个客户资料数量
    private final static int DATA_COUNT = 20;// 每个客户资料数量

    private DeptDao deptDao;
    private SysUserDao sysUserDao;
    private FamilyNameDao familyNameDao;
    private SysRoleMemberDao sysRoleMemberDao;
    private SysDeptAuthService sysDeptAuthService;
    private CdSystemService codetableService;
    private PhoneConfigService phoneConfigService;
    private AnswerConfigService answerConfigService;
    private RemindConfigService remindConfigService;
    private CrmRecordRemindService crmRecordRemindService;
    private CrmRingSettingService crmRingSettingService;
    private CrmRecordSettingService crmRecordSettingService;
    private CrmCustomerService crmCustomerService;
    private CrmTemplateService crmTemplateService;
    private CrmTemplateFieldService crmTemplateFieldService;
    private RecordInfoService recordInfoService;
    private CrmCustomerDao crmCustomerDao;
    private LnLoanService lnLoanService;
    private TskMicroTaskService tskMicroTaskService;
    private TskMicroTaskExecuteService tskMicroTaskExecuteService;
    private LnLoanDao lnLoanDao;

    private SysUploadFileService sysUploadFileService;
    private CustomerDataService customerDataService;
    private DataAudioService dataAudioService;
    private DataPhotoService dataPhotoService;
    private DataVideoService dataVideoService;
    private DataFormService dataFormService;
    public void testActionNotNull() throws Exception {
        endTransaction();
//        initTemplate();
//        initDept();

//        initCustomer();

//        initRecordInfo();
//        initLoan();
//        initLnLoan();
//        initLoan();
//        initData();
        initTask();
    }



    private void initShareCustomer() {
        List<SysUser> users = sysUserDao.getAllData();
        int customerIdShareId = 100000;
        for (SysUser user : users) {
            try {
                List<CrmCustomerShare> list = new ArrayList<CrmCustomerShare>();
                if (user.getUserId() == 1) {
                    continue;
                }
                List<CrmCustomer> customers = crmCustomerService.getCustomersByUserId(String.valueOf(user.getUserId()));
                for (int j = 0; j < customers.size(); j++) {
                    for (int k = 0; k < 20; k++) {
                        BaseCrmCustomer cus = customers.get(j);
                        CrmCustomerShare crmCustomerShare = new CrmCustomerShare();
                        crmCustomerShare.setCustomerIdShareId(customerIdShareId++);
                        crmCustomerShare.setCustomerId(cus.getCustomerId());
                        crmCustomerShare.setUserId(user.getUserId());
                        crmCustomerShare.setShareUserId(users.get(RandomUtil.seedIntBetween(0, users.size() - 1))
                                .getUserId());
                        crmCustomerShare.setCreateDate(Calendar.getInstance().getTime());
                        crmCustomerShare.setUpdateDate(Calendar.getInstance().getTime());
                        crmCustomerShare.setCreateUser(user.getUserId());
                        crmCustomerShare.setUpdateUser(user.getUserId());

                        list.add(crmCustomerShare);
                    }
                }

//                crmCustomerShareService.addShareCustomerBatch(list);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    private void initTask() {
        List<SysUser> users = sysUserDao.getAllData();

        int taskId = 200000,k=1;
        TskMicroTask task=null;
        TskMicroTaskExecute tskExecute=null;
        TskMicroTaskExecute tskUExecute=null;
        for (SysUser user : users) {
            if (user.getUserId() == 1) {
                continue;
            }
            try {
                List<TskMicroTask> tskMicroTaskList = new ArrayList<TskMicroTask>(); //任务列表
                List<TskMicroTaskExecute> tskMicroTaskExecuteList = new ArrayList<TskMicroTaskExecute>(); //任务列表
                for (int i = 0; i < 2600; i++) {  //任务数
                    task = new TskMicroTask();
                    tskExecute=new TskMicroTaskExecute();
                    tskUExecute=new TskMicroTaskExecute();
                    task.setTaskId(taskId + k);
                    task.setTaskTitle("万事如意" + k);
                    task.setTaskType(RandomUtil.seedIntBetween(1, 3));
                    task.setAssignUserId(user.getUserId());
                    Calendar c=Calendar.getInstance();
                    task.setStartDate(c.getTime());
                    c.add(Calendar.DATE, 30); //日期加1天
                    task.setEndDate(c.getTime());
                    Integer taskTarger= RandomUtil.seedIntBetween(1, 200);
                    task.setTaskTarget(taskTarger);
                    task.setNewCustomerPercent(new BigDecimal(20));
                    task.setOldCustomerPercent(new BigDecimal(80));
                    task.setRemark("");
                    task.setFinishDate(Calendar.getInstance().getTime());
                    task.setIsDel(0);
                    task.setTaskStatus(RandomUtil.seedIntBetween(0, 2));
                    task.setCreateDate(Calendar.getInstance().getTime());
                    task.setUpdateDate(Calendar.getInstance().getTime());
                    task.setCreateUser(1);
                    task.setUpdateUser(1);

                    //用户部门的任务目标
                    tskExecute.setTaskId(taskId + k);
                    tskExecute.setDeptId(user.getDeptId());
                    tskExecute.setUserId(0);
                    tskExecute.setTargetDept(taskTarger);
                    tskExecute.setTargetUser(0);
                    tskExecute.setTargetDeptUnassign(0);

                    //用户的任务目标
                    tskUExecute.setTaskId(taskId + k);
                    tskUExecute.setDeptId(user.getDeptId());
                    tskUExecute.setUserId(user.getUserId());
                    tskUExecute.setTargetDept(0);
                    tskUExecute.setTargetUser(taskTarger);
                    tskUExecute.setTargetDeptUnassign(0);

                    tskMicroTaskExecuteList.add(tskExecute);
                    tskMicroTaskExecuteList.add(tskUExecute);
                    tskMicroTaskList.add(task);
                    k++;
                }
                tskMicroTaskService.addTskMicroTaskBatch(tskMicroTaskList);
               tskMicroTaskExecuteService.addTskMicroTaskExecuteBatch(tskMicroTaskExecuteList);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }


    private void initRecordInfo() {
        int id = 50000000;
        // 取得所有用户
        List<SysUser> users = sysUserDao.getAllData();
        for (SysUser user : users) {
            if (user.getUserId() == 1) {
                continue;
            }
            try {
                List<RecordInfo> infoList = new ArrayList<RecordInfo>();
//                List<SysUploadFile> sysUploadFileList = new ArrayList<SysUploadFile>();
//                Long fileId = 1L;
                // 取得用户 我的客户
                List<CrmCustomer> customers = crmCustomerService.getCustomersByUserId(String.valueOf(user.getUserId()));
                for (int i = 0; i < REC_COUNT; i++) {
                    try {
//                        SysUploadFile sysUploadFile = new SysUploadFile();
//                        sysUploadFile.setFileId(fileId.intValue());
//                        sysUploadFile.setStorageType("file");
//                        sysUploadFile.setFileSize(0L);
//                        sysUploadFile.setCreateUser(0);
//                        sysUploadFile.setUpdateUser(0);
//                        sysUploadFileList.add(sysUploadFile);

                        // 随机客户
                        BaseCrmCustomer cus = customers.get(RandomUtil.seedIntBetween(0, customers.size() - 1));

                        RecordInfo recordInfo = new RecordInfo();
                        recordInfo.setRecordInfoId(id++);
                        recordInfo.setRecordNo(RandomUtil.seedStringMix(13));
                        recordInfo.setUserId(user.getUserId());
                        recordInfo.setCustomerId(cus.getCustomerId());
                        recordInfo.setCallType(RandomUtil.seedIntBetween(1, 4));
                        recordInfo.setLocalPhone("");
                        recordInfo.setRemotePhone(cus.getMobilePhone1());
                        recordInfo.setStartDate(Calendar.getInstance().getTime());
                        recordInfo.setEndDate(Calendar.getInstance().getTime());
                        recordInfo.setCallTime(RandomUtil.seedIntBetween(0, 50000));
                        recordInfo.setCustomerName(cus.getCustomerName());
                        recordInfo.setBizType(RandomUtil.seedIntBetween(1, 2));
                        recordInfo.setIdCard("");
                        recordInfo.setCreditCard("");
                        recordInfo.setRemark("");
                        recordInfo.setCommProgressId(RandomUtil.seedIntBetween(1, 6));
                        recordInfo.setIsCanceled(0);
                        recordInfo.setIsDel(0);
                        recordInfo.setIsArchived(0);
                        recordInfo.setArchiveDate(Calendar.getInstance().getTime());
                        recordInfo.setRecordSource(1);
                        recordInfo.setIsRead(1);
                        recordInfo.setContent("");
                        recordInfo.setSplitCount(0);
                        recordInfo.setStatus("");
                        recordInfo.setVerifyUserId(1);
                        recordInfo.setMmsTitle("");
                        recordInfo.setRingTime(Calendar.getInstance().getTime());
                        recordInfo.setCreateDate(Calendar.getInstance().getTime());
                        recordInfo.setUpdateDate(Calendar.getInstance().getTime());
                        recordInfo.setCreateUser(1);
                        recordInfo.setUpdateUser(1);
//                        recordInfo.setFileId((fileId++).intValue());
                        recordInfo.setCustomerStr(cus.getCustomerName()+"_"+cus.getCustomerNamePinyin()+"_"+cus.getMobilePhone1()+"_"+cus.getMobilePhone2()+"_"+cus.getPhone()+"_"+cus.getFax());
                        infoList.add(recordInfo);

                        if (infoList.size() > 5000) {
                            recordInfoService.addRecordInfoBatch(infoList);
//                            sysUploadFileService.insertSysUploadFileBatch(sysUploadFileList);
                            infoList.clear();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                recordInfoService.addRecordInfoBatch(infoList);
//                sysUploadFileService.insertSysUploadFileBatch(sysUploadFileList);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void initRecordInfoSMS() {
        // 取得所有用户
        int recordInfoId = 5920800;
        List<SysUser> users = sysUserDao.getAllData();
        for (SysUser user : users) {
            if (user.getUserId() == 1) {
                continue;
            }
            try {
                List<RecordInfo> infoList = new ArrayList<RecordInfo>();
                for (int i = 0; i < SMS_SEND_COUNT * 100; i++) {
                    // 随机客户
                    RecordInfo recordInfo = new RecordInfo();
                    recordInfo.setRecordInfoId(recordInfoId++);
                    recordInfo.setRecordNo(RandomUtil.seedStringMix(13));
                    recordInfo.setUserId(user.getUserId());
                    recordInfo.setCustomerId(-1);
                    recordInfo.setCallType(7);
                    recordInfo.setLocalPhone("");
                    recordInfo.setRemotePhone("13" + RandomUtil.seedIntBetween(100000000, 900000000));
                    recordInfo.setStartDate(Calendar.getInstance().getTime());
                    recordInfo.setEndDate(Calendar.getInstance().getTime());
                    recordInfo.setCallTime(0);
                    recordInfo.setCustomerName("");
                    recordInfo.setBizType(0);
                    recordInfo.setIdCard("");
                    recordInfo.setCreditCard("");
                    recordInfo.setRemark("");
                    recordInfo.setCommProgressId(0);
                    recordInfo.setIsCanceled(0);
                    recordInfo.setFileMd5("");
                    recordInfo.setFileName("");
                    recordInfo.setFilePath("");
                    recordInfo.setFileSize(0L);
                    recordInfo.setUploadDate(Calendar.getInstance().getTime());
                    recordInfo.setIsDel(0);
                    recordInfo.setIsArchived(0);
                    recordInfo.setArchiveDate(Calendar.getInstance().getTime());
                    recordInfo.setRecordSource(1);
                    recordInfo.setIsRead(1);
                    recordInfo.setContent("*恭喜发财*" + i);
                    recordInfo.setSplitCount(1);
                    recordInfo.setStatus("成功");
                    recordInfo.setVerifyUserId(10);
                    recordInfo.setMmsTitle("*恭喜发财*" + i);
                    recordInfo.setRingTime(Calendar.getInstance().getTime());
                    recordInfo.setCreateDate(Calendar.getInstance().getTime());
                    recordInfo.setUpdateDate(Calendar.getInstance().getTime());
                    recordInfo.setCreateUser(1);
                    recordInfo.setUpdateUser(1);

                    infoList.add(recordInfo);

                }

                recordInfoService.addRecordInfoBatch(infoList);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void initRecordInfoMMS() {
        // 取得所有用户
        int recordInfoId = 5920800;
        List<SysUser> users = sysUserDao.getAllData();
        for (SysUser user : users) {
            if (user.getUserId() == 1) {
                continue;
            }

            List<RecordInfo> infoList = new ArrayList<RecordInfo>();
            // 取得用户 我的客户
            List<CrmCustomer> customers = crmCustomerService.getCustomersByUserId(String.valueOf(user.getUserId()));
            for (int i = 0; i < 10000; i++) {
                // 随机客户
                BaseCrmCustomer cus = customers.get(RandomUtil.seedIntBetween(0, customers.size() - 1));

                RecordInfo recordInfo = new RecordInfo();
                recordInfo.setRecordInfoId(recordInfoId++);
                recordInfo.setRecordNo(RandomUtil.seedStringMix(13));
                recordInfo.setUserId(user.getUserId());
                recordInfo.setCustomerId(cus.getCustomerId());
                recordInfo.setCallType(9);
                recordInfo.setLocalPhone("");
                recordInfo.setRemotePhone(cus.getMobilePhone1());
                recordInfo.setStartDate(Calendar.getInstance().getTime());
                recordInfo.setEndDate(Calendar.getInstance().getTime());
                recordInfo.setCallTime(0);
                recordInfo.setCustomerName(cus.getCustomerName());
                recordInfo.setBizType(0);
                recordInfo.setIdCard("");
                recordInfo.setCreditCard("");
                recordInfo.setRemark("");
                recordInfo.setCommProgressId(0);
                recordInfo.setIsCanceled(0);
                recordInfo.setFileMd5("");
                recordInfo.setFileName("");
                recordInfo.setFilePath("");
                recordInfo.setFileSize(0L);
                recordInfo.setUploadDate(Calendar.getInstance().getTime());
                recordInfo.setIsDel(0);
                recordInfo.setIsArchived(0);
                recordInfo.setArchiveDate(Calendar.getInstance().getTime());
                recordInfo.setRecordSource(1);
                recordInfo.setIsRead(1);
                recordInfo.setContent(cus.getCustomerName() + "*恭喜发财*" + i);
                recordInfo.setSplitCount(1);
                recordInfo.setStatus("成功");
                recordInfo.setVerifyUserId(10);
                recordInfo.setMmsTitle(cus.getCustomerName() + "*恭喜发财*" + i);
                recordInfo.setRingTime(Calendar.getInstance().getTime());
                recordInfo.setCreateDate(Calendar.getInstance().getTime());
                recordInfo.setUpdateDate(Calendar.getInstance().getTime());
                recordInfo.setCreateUser(1);
                recordInfo.setUpdateUser(1);

                infoList.add(recordInfo);

            }
            recordInfoService.addRecordInfoBatch(infoList);
        }
    }

    private void initTemplate() {
        for (int i = 0; i < 4; i++) {
            CrmTemplate crmTemplate = new CrmTemplate();
            crmTemplate.setIsDel(0);
            crmTemplate.setTemplateTypeId(1);
            crmTemplate.setTemplateName("业务" + i);
            crmTemplate.setRemark("");
            crmTemplate.setSortno(i + 2);
            crmTemplate.setIsBasic(0);
            crmTemplate.setCreateDate(Calendar.getInstance().getTime());
            crmTemplate.setUpdateDate(Calendar.getInstance().getTime());
            crmTemplate.setCreateUser(1);
            crmTemplate.setUpdateUser(1);

            crmTemplateService.addCrmTemplate(crmTemplate);

            for (int j = 0; j < 8; j++) {
                CrmTemplateField crmTemplateField = new CrmTemplateField();
                crmTemplateField.setTemplateId(crmTemplate.getTemplateId());
                crmTemplateField.setIsDel(0);
                crmTemplateField.setTemplateFieldName(crmTemplate.getTemplateName() + "_字段" + j);
                if (j < 5) {
                    crmTemplateField.setTemplateFieldType("Text");
                } else {
                    crmTemplateField.setTemplateFieldType("Number");
                }
                crmTemplateField.setSortNo(i + 1);
                crmTemplateField.setIsPopUp(0);
                crmTemplateField.setMeasurement("");
                String extField = crmTemplateFieldService.getNotUsedExtField(crmTemplateField.getTemplateFieldType()
                        .trim());
                crmTemplateField.setExtFieldName(extField);
                crmTemplateField.setCreateDate(Calendar.getInstance().getTime());
                crmTemplateField.setUpdateDate(Calendar.getInstance().getTime());
                crmTemplateField.setCreateUser(1);
                crmTemplateField.setUpdateUser(1);

                crmTemplateFieldService.addCrmTemplateField(crmTemplateField);
            }
        }

    }

    private void initCustomer() {
        List<BaseFamilyName> nickNames = crmCustomerService.getNickName();
        List<CrmTemplate> templateList = crmTemplateService.getTemplates(); // 获取所有业务模版
        String ids = "";
        for (CrmTemplate t : templateList) {
            ids += t.getTemplateId() + ",";
        }
        if (ids.length() > 0) {
            ids = ids.substring(0, ids.length() - 1);
        }

        int cusId = 1000000;

        // 取得所有用户
        List<SysUser> users = sysUserDao.getAllData();
        for (SysUser user : users) {
            if (user.getUserId() == 1) {
                continue;
            }

            try {
                List<CrmCustomer> cusList = new ArrayList<CrmCustomer>();
                List<Map<String, String>> extList = new ArrayList<Map<String, String>>();
                for (int i = 0; i < CUSTOMER_COUNT; i++) {
                    try {
                        CrmCustomer customer = new CrmCustomer();

                        BaseFamilyName baseFamilyName = nickNames
                                .get(RandomUtil.seedIntBetween(0, nickNames.size() - 1));
                        String lastName = baseFamilyName.getFamilyName();
                        NumberToHanziUtil transform = new NumberToHanziUtil();
                        String firstName = transform.transfrom(String.valueOf(i + 1));
                        String customerName = lastName + firstName;
                        customer.setCustomerName(customerName);
                        customer.setCustomerNamePinyin(crmCustomerService.getPinYinHeadChar(customer.getCustomerName()));
                        if (i < CUSTOMER_COUNT / 2) {
                            customer.setSex("男");
                            customer.setCustomerTitle(lastName + "先生");
                        } else {
                            customer.setSex("女");
                            customer.setCustomerTitle(lastName + "女士");
                        }
                        customer.setCustomerNo(RandomUtil.seedStringMix(12));
                        customer.setIdCard("");
                        customer.setCompany("");
                        customer.setRemark("");
                        customer.setHeadshow("");
                        customer.setProvince("");
                        customer.setCity("");
                        customer.setAddress("");
                        customer.setDefaultPhoneType(1);
                        customer.setMobilePhone1("13" + RandomUtil.seedIntBetween(100000000, 900000000));
                        customer.setMobilePhone1Regular(PhoneUtil.getPhoneRule(customer.getMobilePhone1()));
                        customer.setMobilePhone1Remark("");
                        customer.setMobilePhone2("13" + RandomUtil.seedIntBetween(100000000, 900000000));
                        customer.setMobilePhone2Regular(PhoneUtil.getPhoneRule(customer.getMobilePhone2()));
                        customer.setMobilePhone2Remark("");
                        customer.setPhone("8" + RandomUtil.seedIntBetween(1000000, 9000000));
                        customer.setPhoneExt("");
                        customer.setFax("");
                        customer.setFaxExt("");
                        customer.setEmail("");

                        customer.setTemplateIds(ids);
                        customer.setIsTrash(0);
                        customer.setIsDel(0);
                        customer.setBelongDeptId(user.getDeptId());
                        customer.setBelongUserId(user.getUserId());
                        customer.setIsVisit(0);
                        customer.setLastContactDate(Calendar.getInstance().getTime());
                        customer.setIsReceiveSms(1);
                        customer.setCreateDate(Calendar.getInstance().getTime());
                        customer.setUpdateDate(Calendar.getInstance().getTime());
                        customer.setCreateUser(1);
                        customer.setUpdateUser(1);
                        customer.setMemo("");

                        customer.setCustomerId(cusId++);
                        cusList.add(customer);
//                        crmCustomerService.addCrmCustomer(customer);

                        Map<String, String> parameters = new HashMap<String, String>();
                        // 保存自定义字段
                        parameters.put("customerId", customer.getCustomerId().toString());
                        extList.add(parameters);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                crmCustomerDao.addCrmCustomerBatch(cusList);
//                crmCustomerService.batchAddExt(extList);
                cusList.clear();
                extList.clear();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void initDept() {
        List<CdProvince> provinceList = initProvince();

        // 总行10人
        initUser(USER_ZONGHANG, 3, "zonghang");

        // 总行id = 3
        // 分行
        for (int i = 0; i < DEPT_FH; i++) {
            SysDept dept = new SysDept();
            dept.setDeptName(provinceList.get(i).getShortName() + "分行");
            dept.setDeptParentId(3);
            dept.setDeptCode(provinceList.get(i).getCode());
            String deptSearchCode = "00100" + (i + 1);
            dept.setDeptSearchCode(deptSearchCode);
            dept.setSortno(i + 1);
            dept.setIsLeaf(0);
            dept.setIsDel(0);
            dept.setRemark(dept.getDeptName());
            dept.setCreateDate(Calendar.getInstance().getTime());
            dept.setUpdateDate(Calendar.getInstance().getTime());
            dept.setCreateUser(1);
            dept.setUpdateUser(1);
            int deptId = deptDao.insertDept(dept);

            initUser(USER_FENHANG, deptId, "fenhang");

            // 支行
            List<CdCity> cityList = codetableService.getCitys(provinceList.get(i).getCode());
            for (int j = 0; j < DEPT_ZH; j++) {
                SysDept dept2 = new SysDept();
                dept2.setDeptName(cityList.get(j).getShortName() + "支行");
                dept2.setDeptParentId(deptId);
                dept2.setDeptCode(cityList.get(j).getCode());
                dept2.setDeptSearchCode(dept.getDeptSearchCode() + "00" + (j + 1));
                dept2.setSortno(j + 1);
                dept2.setIsLeaf(1);
                dept2.setIsDel(0);
                dept2.setRemark(dept2.getDeptName());
                dept2.setCreateDate(Calendar.getInstance().getTime());
                dept2.setUpdateDate(Calendar.getInstance().getTime());
                dept2.setCreateUser(1);
                dept2.setUpdateUser(1);
                int deptId2 = deptDao.insertDept(dept2);

                initUser(USER_ZHIHANG, deptId2, "zhihang");
            }
        }

    }

    private void initUser(int count, int deptId, String type) {
        for (int i = 0; i < count; i++) {
            try {
                SysUser user = new SysUser();
                user.setDeptId(deptId);
                user.setAccount(type + (i + 1));
                user.setPassword("96e79218965eb72c92a549dd5a330112");
                user.setIsPasswordReset(1);
                user.setPasswordStr(0);
                user.setLastChangePassword(Calendar.getInstance().getTime());
                user.setLastLoginDate(Calendar.getInstance().getTime());
                user.setUserCode(user.getAccount());
                user.setUserName(user.getAccount());
                user.setIsActived(1);
                user.setIsDel(0);
                user.setRemark("remark");
                user.setCreateDate(Calendar.getInstance().getTime());
                user.setUpdateDate(Calendar.getInstance().getTime());
                user.setCreateUser(1);
                user.setUpdateUser(1);

                sysUserDao.addSysUser(user);

                // 第一个用户为主管
                if (i == 0) {
                    SysRoleMember member = new SysRoleMember();
                    member.setRoleId(3);
                    member.setUserId(user.getUserId());
                    member.setCreateDate(Calendar.getInstance().getTime());
                    member.setUpdateDate(Calendar.getInstance().getTime());
                    member.setCreateUser(1);
                    member.setUpdateUser(1);
                    sysRoleMemberDao.addSysRoleMember(member);

                    SysDeptAuth auth = new SysDeptAuth();
                    auth.setRoleId(3);
                    auth.setUserId(user.getUserId());
                    auth.setDeptId(deptId);
                    auth.setCreateDate(Calendar.getInstance().getTime());
                    auth.setUpdateDate(Calendar.getInstance().getTime());
                    auth.setCreateUser(1);
                    auth.setUpdateUser(1);

                    sysDeptAuthService.addSysDeptAuth(auth);
                }

                // 授权
                SysRoleMember member = new SysRoleMember();
                member.setRoleId(4);
                member.setUserId(user.getUserId());
                member.setCreateDate(Calendar.getInstance().getTime());
                member.setUpdateDate(Calendar.getInstance().getTime());
                member.setCreateUser(1);
                member.setUpdateUser(1);
                sysRoleMemberDao.addSysRoleMember(member);

                // 初始化配制
                int userId = user.getUserId();
                phoneConfigService.addPhoneConfig(userId);
                AnswerConfig answerConfig = new AnswerConfig();
                answerConfig.setUserId(userId);
                answerConfig.setConfigType(1);
                answerConfig.setVoiceFilePath("");
                answerConfigService.addAnswerConfig(answerConfig);
                remindConfigService.addRemindConfig(userId);
                crmRecordRemindService.insertCrmRecordRemind(userId);
                crmRingSettingService.insertCrmRingSetting(userId);
                crmRecordSettingService.insertCrmRecordSetting(userId);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    private List<CdProvince> initProvince() {
        List<CdProvince> result = new ArrayList<CdProvince>();
        CdProvince province = codetableService.getProvinceByCode("330000");
        CdProvince province1 = codetableService.getProvinceByCode("320000");
        CdProvince province2 = codetableService.getProvinceByCode("360000");
        CdProvince province3 = codetableService.getProvinceByCode("130000");
        CdProvince province4 = codetableService.getProvinceByCode("410000");
        CdProvince province5 = codetableService.getProvinceByCode("420000");
        CdProvince province6 = codetableService.getProvinceByCode("620000");
        CdProvince province7 = codetableService.getProvinceByCode("520000");
        CdProvince province8 = codetableService.getProvinceByCode("610000");
        CdProvince province9 = codetableService.getProvinceByCode("510000");
        CdProvince province0 = codetableService.getProvinceByCode("370000");
        result.add(province);
        result.add(province1);
        result.add(province2);
        result.add(province3);
        result.add(province4);
        result.add(province5);
        result.add(province6);
        result.add(province7);
        result.add(province8);
        result.add(province9);
        result.add(province0);
        return result;
    }

    private void initLoan() {
        int loanId = 3299600;
        // 取得所有用户
        List<SysUser> users = sysUserDao.getAllData();
        for (SysUser sysUser : users) {
            if (sysUser.getUserId() == 1) {
                continue;
            }
            try {
                List<LnLoan> lnLoans=new ArrayList<LnLoan>();
                // 取得用户 我的客户
                List<CrmCustomer> customers = crmCustomerService.getCustomersByUserId(String.valueOf(sysUser.getUserId()));
                if(customers.size()<0){
                    continue;
                }
                for (int i = 0; i < LOAN_COUNT; i++) {
                    try {

                        // 随机客户
                        BaseCrmCustomer customer = customers.get(RandomUtil.seedIntBetween(0, customers.size() - 1));
                        LnLoan loan = new LnLoan();
                        loan.setLoanId(loanId++);
                        loan.setCustomerId(customer.getCustomerId());
                        int ranType=RandomUtil.seedIntBetween(1, 2);
                        loan.setLoanTypeId(ranType);
                        if(ranType ==1){
                            loan.setLoanSubTypeId(RandomUtil.seedIntBetween(1, 2));
                        }
                        BigDecimal loanMoney = new BigDecimal(RandomUtil.seedIntBetween(1,10000));
                        loan.setLoanMoney(loanMoney);
                        loan.setLoanRemaining(loanMoney);
                        loan.setAccountRemaining(loanMoney);
                        loan.setOwedPrincipal(loanMoney);
                        loan.setPaidInterest(loanMoney);
                        loan.setApplyUserId(sysUser.getUserId());
                        loan.setAssignUserId(sysUser.getUserId());
                        loan.setSubmitUserId(sysUser.getUserId());
                        loan.setSurveyUserId(sysUser.getUserId());
                        loan.setIsCheckout(RandomUtil.seedIntBetween(0, 1));
                        loan.setCheckoutUserId(sysUser.getUserId());
                        loan.setIsNogood(RandomUtil.seedIntBetween(0, 1));
                        loan.setCancelUserId(sysUser.getUserId());
                        loan.setVerifyUserId(sysUser.getUserId());
                        loan.setIsVerify(RandomUtil.seedIntBetween(0, 1));
                        loan.setIsVerifyPass(RandomUtil.seedIntBetween(2, 3));
                        loan.setIsOldCustomer(RandomUtil.seedIntBetween(0, 1));
                        lnLoans.add(loan);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                lnLoanService.insertLoanBatch(lnLoans);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }




    *//**
     * 获取随机日期
     *
     * @param beginDate
     *            起始日期，格式为：yyyy-MM-dd
     * @param endDate
     *            结束日期，格式为：yyyy-MM-dd
     * @return
     *//*

    private Date randomDate(String beginDate, String endDate) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date start = format.parse(beginDate);// 构造开始日期
            Date end = format.parse(endDate);// 构造结束日期
            // getTime()表示返回自 1970 年 1 月 1 日 00:00:00 GMT 以来此 Date 对象表示的毫秒数。
            if (start.getTime() >= end.getTime()) {
                return null;
            }
            long date = random(start.getTime(), end.getTime());

            return new Date(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private long random(long begin, long end) {
        long rtn = begin + (long) (Math.random() * (end - begin));
        // 如果返回的是开始时间和结束时间，则递归调用本函数查找随机值
        if (rtn == begin || rtn == end) {
            return random(begin, end);
        }
        return rtn;
    }
    
    private void initData() {
    	
    	int customerDataId = 300000;
    	List<CustomerData> customerDataList = new ArrayList<CustomerData>();
    	List<Audio> audioList = new ArrayList<Audio>();
    	List<Photo> photoList = new ArrayList<Photo>();
    	List<Video> videoList = new ArrayList<Video>();
    	List<Form> FormList = new ArrayList<Form>();
    	
        // 取得所有用户
        List<SysUser> users = sysUserDao.getAllData();
        for (SysUser user : users) {
            if (user.getUserId() == 1) {
                continue;
            }
            if (user.getUserId() == 517){
            	break;
            }
            try {
                // 取得用户 我的客户
                List<CrmCustomer> customers = crmCustomerService.getCustomersByUserId(String.valueOf(user.getUserId()));
                for (int i = 0; i < 5000; i++) {
                    try {
                    	 // 随机客户
                        BaseCrmCustomer customer = customers.get(RandomUtil.seedIntBetween(0, customers.size() - 1));
                    	CustomerData customerData = new CustomerData();
                    	int eventId = RandomUtil.seedIntBetween(1, 7);
                    	customerData.setCustomerDataId(customerDataId);
            			customerData.setLoanId(RandomUtil.seedIntBetween(1, 200000));
            			customerData.setCustomerId(customer.getCustomerId());
            			customerData.setEventId(eventId);
            			customerData.setUploadUserId(user.getUserId());
            			customerData.setCreateUser(9999);
            			customerData.setUpdateUser(9999);
                        customerDataId++;
                        customerDataList.add(customerData);
                    	//一个资料总数据对应20条分数据
                    	for(int j = 0 ; j <= 3 ; j ++ ){//组装录音资料 3条
                    		Audio audio =new Audio();
                    		audio.setCustomerDataId(customerDataId);
                    		audio.setRecordLength(RandomUtil.seedIntBetween(1, 2000));
                    		audio.setFileId(RandomUtil.seedIntBetween(1, 1000000));
                    		audio.setCreateUser(9999);
                    		audio.setUpdateUser(9999);
                    		audioList.add(audio);
                    		if(audioList.size() > 5000){
                    			dataAudioService.addNewAudioBatch(audioList);
                    			audioList.clear();
                 			}
                    	}
                    	
                    	for(int j = 0 ; j <= 10 ; j ++ ){//组装照片资料 10条
                    		Photo photo =new Photo();
                    		photo.setCustomerDataId(customerDataId);
                    		photo.setFileId(RandomUtil.seedIntBetween(1, 1000000));
                    		photo.setCreateUser(9999);
                    		photo.setUpdateUser(9999);
                    		photoList.add(photo);
                    		if(photoList.size() > 5000){
                    			dataPhotoService.addNewPhotoBatch(photoList);
                    			photoList.clear();
                 			}
                    	}
                    	
                    	for(int j = 0 ; j <= 3 ; j ++ ){//组装视频资料 3条
                    		Video video =new Video();
                    		video.setCustomerDataId(customerDataId);
                    		video.setRecordLength(RandomUtil.seedIntBetween(1, 2000));
                    		video.setFileId(RandomUtil.seedIntBetween(1, 1000000));
                    		video.setCreateUser(9999);
                    		video.setUpdateUser(9999);
                    		videoList.add(video);
                    		if(videoList.size() > 5000){
                    			dataVideoService.addNewVideoBatch(videoList);
                    			videoList.clear();
                 			}
                    	}
                    	
                    	for(int j = 0 ; j <= 3 ; j ++ ){//组装录音资料 4条
                    		Form form =new Form();
                    		form.setCustomerDataId(customerDataId);
                    		form.setFileId(RandomUtil.seedIntBetween(1, 1000000));
                    		form.setCreateUser(9999);
                    		form.setUpdateUser(9999);
                    		FormList.add(form);
                    		if(FormList.size() > 5000){
                    			dataFormService.addDatFormBatch(FormList);
                    			FormList.clear();
                 			}
                    	}
                        
                        if(customerDataList.size() > 5000){
                			customerDataService.saveCustomerDataBatch(customerDataList);
                			customerDataList.clear();
            			}

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                customerDataService.saveCustomerDataBatch(customerDataList);
                dataAudioService.addNewAudioBatch(audioList);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    private void initLnLoan(){
//        int loanId = 3296009;
//        Integer count = 500000;
//        Integer pageSize = 2000;
//        Map<String,Object> paramMap = new HashMap<String, Object>();
//        for (int i=1; i<= count/pageSize; i++){
//            paramMap.put("startRow",(i-1)*pageSize+1);
//            paramMap.put("endRow",i*pageSize);
//            List<CrmCustomer> customerList = crmCustomerDao.getCustomerListByZhang(paramMap);
//            List<LnLoan> lnLoans=new ArrayList<LnLoan>();
//            for (BaseCrmCustomer customer : customerList){
//                LnLoan loan = new LnLoan();
//                loan.setLoanId(loanId++);
//                loan.setCustomerId(customer.getCustomerId());
//                int ranType=RandomUtil.seedIntBetween(1, 2);
//                loan.setLoanTypeId(ranType);
//                if(ranType ==1){
//                    loan.setLoanSubTypeId(RandomUtil.seedIntBetween(1, 2));
//                }
//                BigDecimal loanMoney = new BigDecimal(RandomUtil.seedIntBetween(1,10000));
//                loan.setLoanMoney(loanMoney);
//                loan.setLoanRemaining(loanMoney);
//                loan.setAccountRemaining(loanMoney);
//                loan.setOwedPrincipal(loanMoney);
//                loan.setPaidInterest(loanMoney);
//                loan.setApplyUserId(customer.getBelongUserId());
//                loan.setAssignUserId(customer.getBelongUserId());
//                loan.setSubmitUserId(customer.getBelongUserId());
//                loan.setSurveyUserId(customer.getBelongUserId());
//                loan.setIsCheckout(RandomUtil.seedIntBetween(0, 1));
//                loan.setCheckoutUserId(customer.getBelongUserId());
//                loan.setIsNogood(RandomUtil.seedIntBetween(0, 1));
//                loan.setCancelUserId(customer.getBelongUserId());
//                loan.setVerifyUserId(customer.getBelongUserId());
//                loan.setIsVerify(RandomUtil.seedIntBetween(0, 1));
//                loan.setIsVerifyPass(RandomUtil.seedIntBetween(2, 3));
//                loan.setIsOldCustomer(RandomUtil.seedIntBetween(0, 1));
//                lnLoans.add(loan);
//            }
//            lnLoanService.insertLoanBatch(lnLoans);
//        }
    }




    public DeptDao getDeptDao() {
        return deptDao;
    }

    public void setDeptDao(DeptDao deptDao) {
        this.deptDao = deptDao;
    }

    public SysUserDao getSysUserDao() {
        return sysUserDao;
    }

    public void setSysUserDao(SysUserDao sysUserDao) {
        this.sysUserDao = sysUserDao;
    }

    public FamilyNameDao getFamilyNameDao() {
        return familyNameDao;
    }

    public void setFamilyNameDao(FamilyNameDao familyNameDao) {
        this.familyNameDao = familyNameDao;
    }

    public SysRoleMemberDao getSysRoleMemberDao() {
        return sysRoleMemberDao;
    }

    public void setSysRoleMemberDao(SysRoleMemberDao sysRoleMemberDao) {
        this.sysRoleMemberDao = sysRoleMemberDao;
    }

    public CdSystemService getCodetableService() {
        return codetableService;
    }

    public void setCodetableService(CdSystemService codetableService) {
        this.codetableService = codetableService;
    }

    public PhoneConfigService getPhoneConfigService() {
        return phoneConfigService;
    }

    public void setPhoneConfigService(PhoneConfigService phoneConfigService) {
        this.phoneConfigService = phoneConfigService;
    }

    public AnswerConfigService getAnswerConfigService() {
        return answerConfigService;
    }

    public void setAnswerConfigService(AnswerConfigService answerConfigService) {
        this.answerConfigService = answerConfigService;
    }

    public RemindConfigService getRemindConfigService() {
        return remindConfigService;
    }

    public void setRemindConfigService(RemindConfigService remindConfigService) {
        this.remindConfigService = remindConfigService;
    }

    public CrmRecordRemindService getCrmRecordRemindService() {
        return crmRecordRemindService;
    }

    public void setCrmRecordRemindService(CrmRecordRemindService crmRecordRemindService) {
        this.crmRecordRemindService = crmRecordRemindService;
    }

    public CrmRingSettingService getCrmRingSettingService() {
        return crmRingSettingService;
    }

    public void setCrmRingSettingService(CrmRingSettingService crmRingSettingService) {
        this.crmRingSettingService = crmRingSettingService;
    }

    public CrmRecordSettingService getCrmRecordSettingService() {
        return crmRecordSettingService;
    }

    public void setCrmRecordSettingService(CrmRecordSettingService crmRecordSettingService) {
        this.crmRecordSettingService = crmRecordSettingService;
    }

    public SysDeptAuthService getSysDeptAuthService() {
        return sysDeptAuthService;
    }

    public void setSysDeptAuthService(SysDeptAuthService sysDeptAuthService) {
        this.sysDeptAuthService = sysDeptAuthService;
    }

    public CrmCustomerService getCrmCustomerService() {
        return crmCustomerService;
    }

    public void setCrmCustomerService(CrmCustomerService crmCustomerService) {
        this.crmCustomerService = crmCustomerService;
    }

    public CrmTemplateService getCrmTemplateService() {
        return crmTemplateService;
    }

    public void setCrmTemplateService(CrmTemplateService crmTemplateService) {
        this.crmTemplateService = crmTemplateService;
    }

    public CrmTemplateFieldService getCrmTemplateFieldService() {
        return crmTemplateFieldService;
    }

    public void setCrmTemplateFieldService(CrmTemplateFieldService crmTemplateFieldService) {
        this.crmTemplateFieldService = crmTemplateFieldService;
    }

    public RecordInfoService getRecordInfoService() {
        return recordInfoService;
    }

    public void setRecordInfoService(RecordInfoService recordInfoService) {
        this.recordInfoService = recordInfoService;
    }

    public CrmCustomerDao getCrmCustomerDao() {
        return crmCustomerDao;
    }

    public void setCrmCustomerDao(CrmCustomerDao crmCustomerDao) {
        this.crmCustomerDao = crmCustomerDao;
    }
    
    public SysUploadFileService getSysUploadFileService() {
        return sysUploadFileService;
    }
    
    public void setSysUploadFileService(SysUploadFileService sysUploadFileService) {
        this.sysUploadFileService = sysUploadFileService;
    }
    
    public LnLoanService getLnLoanService() {
        return lnLoanService;
    }
    
    public void setLnLoanService(LnLoanService lnLoanService) {
        this.lnLoanService = lnLoanService;
    }
    
	public CustomerDataService getCustomerDataService() {
		return customerDataService;
	}
	
	public void setCustomerDataService(CustomerDataService customerDataService) {
		this.customerDataService = customerDataService;
	}
	
	public DataAudioService getDataAudioService() {
		return dataAudioService;
	}
	
	public void setDataAudioService(DataAudioService dataAudioService) {
		this.dataAudioService = dataAudioService;
	}
	
	public DataPhotoService getDataPhotoService() {
		return dataPhotoService;
	}
	
	public void setDataPhotoService(DataPhotoService dataPhotoService) {
		this.dataPhotoService = dataPhotoService;
	}
	
	public DataVideoService getDataVideoService() {
		return dataVideoService;
	}
	
	public void setDataVideoService(DataVideoService dataVideoService) {
		this.dataVideoService = dataVideoService;
	}
	
	public DataFormService getDataFormService() {
		return dataFormService;
	}
	
	public void setDataFormService(DataFormService dataFormService) {
		this.dataFormService = dataFormService;
	}

    public void setTskMicroTaskService(TskMicroTaskService tskMicroTaskService) {
        this.tskMicroTaskService = tskMicroTaskService;
    }

    public void setTskMicroTaskExecuteService(TskMicroTaskExecuteService tskMicroTaskExecuteService) {
        this.tskMicroTaskExecuteService = tskMicroTaskExecuteService;
    }
}
*/