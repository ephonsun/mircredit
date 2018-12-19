package com.banger.mobile.facade.impl.record;

import com.banger.mobile.domain.model.base.customer.BaseCrmCustomer;
import com.banger.mobile.domain.model.loan.*;
import com.banger.mobile.domain.model.pad.PadLoanInfo;
import com.banger.mobile.facade.customer.CrmCustomerService;
import com.banger.mobile.facade.loan.*;
import com.banger.mobile.facade.param.SysParamService;
import com.banger.mobile.facade.record.IWriteLendingLoanJob;
import com.banger.mobile.facade.user.SysUserService;
import com.banger.mobile.util.DateUtil;
import com.banger.mobile.util.StringUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.util.CollectionUtils;

import java.io.*;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

public class WriteLendingLoanJobImpl implements IWriteLendingLoanJob {
    private static final Logger logger = Logger.getLogger(WriteLendingLoanJobImpl.class);

    private SysParamService sysParamService;
    private LnLoanService lnLoanService;
    private LnLoanInfoService lnLoanInfoService;
    private CrmCustomerService crmCustomerService; // 客户Service
    private LnLoanGuarantorService lnLoanGuarantorService;
    private SysUserService sysUserService;
    private LnBalanceService lnBalanceService;
    private LnRepaymentPlanService lnRepaymentPlanService;


    private String sysPath;
    private SimpleDateFormat sf1 = new SimpleDateFormat("yyyy-MM-dd");
    private SimpleDateFormat sf2 = new SimpleDateFormat("yyyyMMdd");
    FtpUtil ftp ;

    @Override
    public void doWrite() {

        //ods数据处理
        readOdsData();

        //市信贷 贷款征信
        writeLendLoan();


    }

    private void readOdsData() {

        //1贷款核实
        List<TmpLoanInfo> tmpLoanInfoList = lnBalanceService.getTmpLoanInfoAll();
        if(!CollectionUtils.isEmpty(tmpLoanInfoList)){
            BigDecimal contAmt ;
            LnLoan loan ;
            for (TmpLoanInfo tmlLoan : tmpLoanInfoList) {
                if(StringUtils.isNotEmpty(tmlLoan.getAcctNo())&&StringUtils.isNotEmpty(tmlLoan.getContNo())){
                    //根据合同查询 为完成核实的 贷款
                    loan = lnLoanService.getUnCheckLoanByContractNo(tmlLoan.getContNo());
                    if(null!=loan){
                        //@TODO  数据碰对
                        if(null==loan.getLendContractCheckStatusId()||1!=loan.getLendContractCheckStatusId()){
                            loan.setLendContractCheckStatusId(1);
                            loan.setLoanStatusId(6);
                            loan.setLendContractCheckDate(new Date());
                            lnLoanService.updateLoanByLoanId(loan);
                        }

                        //
                        tmlLoan.setLoanId(loan.getLoanId());
                        lnBalanceService.updateTmpLoanInfo(tmlLoan);
                    }
                }
            }
        }

        //2还款
        List<TmpLoanRepayment> tmpLoanRepaymentList = lnBalanceService.getTmpLoanRepaymentAll();
        if(!CollectionUtils.isEmpty(tmpLoanRepaymentList)){
            LnLoan loan ;
            TmpLoanInfo tmpLoan ;
            LnRepaymentPlan plan;
            for (TmpLoanRepayment tmpRepayment :  tmpLoanRepaymentList) {
                if(StringUtils.isNotEmpty(tmpRepayment.getAcctNo())){
                    tmpLoan = lnBalanceService.getTmpLoanInfoByAccount(tmpRepayment.getAcctNo());
                    if(null!=tmpLoan&&null!=tmpLoan.getLoanId()){
                        plan = lnRepaymentPlanService.getCurrentNoRepaymentPlan(tmpLoan.getLoanId());
                        if(null!=plan){

                            //02 还本
                            //03 还息
                            if(StringUtils.isNotEmpty(tmpRepayment.getTxnLonType())&&tmpRepayment.equals("02")&&null!=tmpRepayment.getTrnAmt()){
                                plan.setPaidPrincipal(null!=plan.getPaidPrincipal()?plan.getPaidPrincipal().add(tmpRepayment.getTrnAmt()):tmpRepayment.getTrnAmt());
                            }else if(StringUtils.isNotEmpty(tmpRepayment.getTxnLonType())&&tmpRepayment.equals("03")){
                                plan.setPaidInterest(null != plan.getPaidInterest() ? plan.getPaidInterest().add(tmpRepayment.getTrnAmt()) : tmpRepayment.getTrnAmt());
                            }

                            if(plan.getPaidPrincipal().add(plan.getPaidInterest()).setScale(0, BigDecimal.ROUND_HALF_UP).compareTo(plan.getPrincipal().add(plan.getInterest()).setScale(0, BigDecimal.ROUND_HALF_UP))>-1) {
                                plan.setPaidTag("1");
                            }

                            lnRepaymentPlanService.updateLnRepaymentPlanById(plan);

                        }
                    }
                }
            }
        }

        //3贷款账户 贷款余额为0表示已结清
        List<TmpLoanAccount> tmpLoanAccountList = lnBalanceService.getTmpLoanAccountAll();
        if(!CollectionUtils.isEmpty(tmpLoanAccountList)){
            TmpLoanInfo tmpLoan ;
            List<LnRepaymentPlan> planList;
            LnLoan loan ;
            for (TmpLoanAccount tmpAccount : tmpLoanAccountList) {
                if(StringUtils.isNotEmpty(tmpAccount.getAcctNo())){
                    tmpLoan = lnBalanceService.getTmpLoanInfoByAccount(tmpAccount.getAcctNo());
                    if(null!=tmpLoan&&null!=tmpLoan.getLoanId()){
                        loan = lnLoanService.getLnLoanById(tmpLoan.getLoanId());
                        if(null!=loan){
                            //贷款余额为0表示已结清 不为0时置为6         //40Discharged 已还||(StringUtils.isNotEmpty(tmpAccount.getAccountStat())&&tmpAccount.getAccountStat().equals("40"))
                            if(null==tmpAccount.getLonBal()||tmpAccount.getLonBal().compareTo(new BigDecimal(0))<1){
                                loan.setLoanStatusId(7);
                            }else if(loan.getLoanStatusId()==7){
                                loan.setLoanStatusId(6);
                            }
                            lnLoanService.updateLoanByLoanId(loan);
                        }

                        //更新余额
                        planList = lnRepaymentPlanService.queryLnRepaymentPlan(tmpLoan.getLoanId());
                        int sortno = 1;
                        if(!CollectionUtils.isEmpty(planList)){
                            //
                            sortno = planList.size()+1;
                            for (LnRepaymentPlan plan : planList){


                                // private BigDecimal remaining;    //贷款余额
                                if(null!=tmpAccount.getLonBal()){
                                    plan.setRemaining(tmpAccount.getLonBal());
                                }
                                //private BigDecimal accountRemaining;    //账户余额
                                if(null!=tmpAccount.getAcctBal()){
                                    plan.setAccountRemaining(tmpAccount.getAcctBal());
                                }

                                //判断还款是否完成
                                if(null==plan.getPrincipal()){
                                    plan.setPrincipal(new BigDecimal(0));
                                }
                                if(null==plan.getInterest()){
                                    plan.setInterest(new BigDecimal(0));
                                }
                                if(null==plan.getPaidPrincipal()){
                                    plan.setPaidPrincipal(new BigDecimal(0));
                                }
                                if(null==plan.getPaidInterest()){
                                    plan.setPaidInterest(new BigDecimal(0));
                                }
                                if(plan.getPaidPrincipal().add(plan.getPaidInterest()).setScale(0,BigDecimal.ROUND_HALF_UP).compareTo(plan.getPrincipal().add(plan.getInterest()).setScale(0, BigDecimal.ROUND_HALF_UP))>-1) {
                                    plan.setPaidTag("1");
                                }
                                plan.setUpdateDate(new Date());
                                lnRepaymentPlanService.updateLnRepaymentPlanById(plan);
                            }
                        }

                        //下次还款金额
                        if(null!=tmpAccount.getNextRepay()){
                            LnRepaymentPlan plan = lnRepaymentPlanService.getCurrentNoRepaymentPlan(tmpLoan.getLoanId());
                            if(null!=plan){//update
                                plan.setPrincipal(tmpAccount.getNextRepay());
                                plan.setInterest(new BigDecimal(0));
                                plan.setPaidPrincipal(new BigDecimal(0));
                                plan.setPaidInterest(new BigDecimal(0));
                                lnRepaymentPlanService.updateLnRepaymentPlanById(plan);
                            }else{//insert
                                plan = new LnRepaymentPlan();
                                plan.setPrincipal(tmpAccount.getNextRepay());
                                plan.setInterest(new BigDecimal(0));
                                plan.setPaidPrincipal(new BigDecimal(0));
                                plan.setPaidInterest(new BigDecimal(0));
                                plan.setSortno(sortno);
                                plan.setPaidTag("0");
                                plan.setLoanId(tmpLoan.getLoanId());
                                plan.setPlanDate(getRepaymentPlanDate());
                                // private BigDecimal remaining;    //贷款余额
                                if(null!=tmpAccount.getLonBal()){
                                    plan.setRemaining(tmpAccount.getLonBal());
                                }
                                //private BigDecimal accountRemaining;    //账户余额
                                if(null!=tmpAccount.getAcctBal()){
                                    plan.setAccountRemaining(tmpAccount.getAcctBal());
                                }
                                lnRepaymentPlanService.addRepaymentPlan(plan);
                            }
                        }

                    }
                }
            }
        }



    }


    private Date getRepaymentPlanDate(){

        Calendar cal = Calendar.getInstance();
        int day = cal.get(Calendar.DAY_OF_MONTH);
        if(day>21){
            cal.set(Calendar.MONTH,cal.get(Calendar.MONTH)+1);
        }
        cal.set(Calendar.DAY_OF_MONTH,21);
        Date date = new Date(cal.getTimeInMillis());
//        System.out.print(new SimpleDateFormat("yyyyMMdd").format( date));
        return date;
    }

    private String customerFileName ;
    private String spouseFileName ;
    private String guarantorFileName ;
    private void writeLendLoan() {

        try {
            long currTimes = System.currentTimeMillis();

    //        今天日期  昨天日期
            Map<String, Object> paramMap = new HashMap<String, Object>();
            String startDate = sf1.format(DateUtil.addDay(new Date(), -1));
            String bizDate = sf2.format(DateUtil.addDay(new Date(), -1));
            String endDate = sf1.format(new Date());
            logger.info("//******************市信贷，小微贷客户信息接口开始：" + startDate + "******************//");


            sysPath = sysParamService.getRecordPath() + File.separator + "CityCredit"+ File.separator+bizDate+File.separator;

    //            sysPath = sysParamService.getRecordPath() + File.separator + "CityCredit"+bizDate + File.separator;
    //
    //            sysPath = sysParamService.getRecordPath() + File.separator + "credit"+ File.separator + bizDate + File.separator;

            if (!new File(sysPath).exists()) {
                new File(sysPath).mkdirs();
            }

            //ftp
            String serverIp = sysParamService.getParamValueByKey("FTP_SERVER_ID");
            String ftpName = sysParamService.getParamValueByKey("FTP_USER_NAME");
            String ftpPassword = sysParamService.getParamValueByKey("FTP_PASSWORD");
            String filePath = sysParamService.getParamValueByKey("FTP_FILE_PATH");
            String allLoan = sysParamService.getParamValueByKey("FTP_ALL_LOAN");

            if(StringUtils.isBlank(filePath)){
                filePath = "/";
            }

            //查询昨天完成放款的贷款
            paramMap.put("contCheckStatusId", "1");
            if(StringUtils.isBlank(allLoan)||allLoan.equals("1")){
                paramMap.put("contCheckStartDate",startDate);
                Map<String,Object> map = new HashMap<String, Object>();
                map.put("paramValue","0");
                map.put("paramKey","FTP_ALL_LOAN");
                sysParamService.updateSysParam(map);
            }else{
                endDate = sf1.format(DateUtil.addDay(new Date(), 1));
            }
            paramMap.put("contCheckEndDate", endDate);
            List<LnLoan> loanList = lnLoanService.getAllLoanByConds(paramMap);

            if (!CollectionUtils.isEmpty(loanList)) {
//                customerFileName  = sysPath + File.separator + "loancustomer" +bizDate+ ".del";
//                spouseFileName = sysPath + File.separator + "loanspouse"  +bizDate+ ".del";
//                guarantorFileName = sysPath + File.separator + "loanguarantor" +bizDate+ ".del";
                customerFileName  = sysPath + File.separator + "loancustomer.del";
                spouseFileName = sysPath + File.separator + "loanspouse.del";
                guarantorFileName = sysPath + File.separator + "loanguarantor.del";

                // 文件
                File customerFile = new File(customerFileName);
                if(customerFile.exists()){
                    customerFile.delete();
                }
                customerFile.createNewFile();

                File spouseFile = new File(spouseFileName);
                if(spouseFile.exists()){
                    spouseFile.delete();
                }
                spouseFile.createNewFile();

                File guarantorFile = new File(guarantorFileName);
                if (guarantorFile.exists()) {
                    guarantorFile.delete();
                }
                guarantorFile.createNewFile();
                for (LnLoan loan : loanList) {
                    logger.info("//******loanId=" + loan.getLoanId() + "******//");

                    PadLoanInfo loanInfo = lnLoanInfoService.getPanLoanInfoById(loan.getLoanId());
                    try {
                        //写入客户信息 配偶信息
                        writeCustomer(loan, loanInfo, bizDate);
                        //写入保证信息
                        writeGuarantor(loan, loanInfo, bizDate);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }


                String customerFileNameIConv  = sysPath + File.separator + "loancustomer.iconv";
                String spouseFileNameIConv  = sysPath + File.separator + "loanspouse.iconv";
                String guarantorFileNameIConv  = sysPath + File.separator + "loanguarantor.iconv";
                // 文件
                File customerFileIconv = new File(customerFileNameIConv);
                if(customerFileIconv.exists()){
                    customerFileIconv.delete();
                }

                File spouseFileIconv = new File(spouseFileNameIConv);
                if(spouseFileIconv.exists()){
                    spouseFileIconv.delete();
                }

                File guarantorFileIconv = new File(guarantorFileNameIConv);
                if (guarantorFileIconv.exists()) {
                    guarantorFileIconv.delete();
                }
                try {
                    Process p1=Runtime.getRuntime().exec("iconv -f UTF-8 -t GBK "+customerFileName+" -o "+customerFileNameIConv);
                    Process p2=Runtime.getRuntime().exec("iconv -f UTF-8 -t GBK "+spouseFileName+" -o "+spouseFileNameIConv);
                    Process p3=Runtime.getRuntime().exec("iconv -f UTF-8 -t GBK "+guarantorFileName+" -o "+guarantorFileNameIConv);
                    customerFile.delete();
                    spouseFile.delete();
                    guarantorFile.delete();

                }catch (Exception e){
                    logger.error(customerFileName+";"+spouseFileName+";"+guarantorFileName+"转换文件失败",e);
                }
                if(StringUtils.isNotEmpty(serverIp)&&StringUtil.isNotEmpty(ftpName)&&StringUtils.isNotEmpty(ftpPassword)){
                    ftp = new FtpUtil(serverIp,ftpName,ftpPassword);
                    ftp.connectServer();
                }
                if(null!=ftp){
                    boolean resultCustomer = ftp.upload(sysPath, filePath+File.separator);
                    logger.info(resultCustomer?resultCustomer+"上传成功！":resultCustomer+"上传失败！");
                }else{
                    logger.info("上传失败！未连接到ftp");
                }


            }

            logger.info("//******************市信贷，小微贷客户信息接口结束,耗时："+(System.currentTimeMillis()-currTimes)+" ,写入贷款："+(null!=loanList?loanList.size():0)+" 条******************//");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void writeCustomer(LnLoan loan, PadLoanInfo loanInfo, String bizDate) throws IOException {
        OutputStreamWriter fwCustomer = null;
        OutputStreamWriter  fwSpouse = null;
        PrintWriter pwCustomer = null;
        PrintWriter pwSpouse = null;
        try {
            // 文件
//            File customerFile = new File(customerFileName);
//            if(!customerFile.exists()){
//                customerFile.createNewFile();
//            }


            fwCustomer = new OutputStreamWriter(new FileOutputStream(customerFileName,true), "UTF-8");
            pwCustomer = new PrintWriter(new BufferedWriter(fwCustomer));

            BaseCrmCustomer customer = crmCustomerService.getCrmCustomerById(loanInfo.getCustomerId());
            StringBuffer sbfCustomer = new StringBuffer("");

            sbfCustomer.append(getTextValue(customer.getCustomerId(), null, 20));    //客户编号	CUSNO	VARCHAR(20)
            sbfCustomer.append(getTextValue("06044", null, 6));        //所属网点	BRHID	VARCHAR(6)
            sbfCustomer.append(getTextValue(customer.getCredentialTypeId(), 1, 2));        //证件类型	CERTTYPE	VARCHAR(2)
            sbfCustomer.append(getTextValue(customer.getIdCard(), loanInfo.getCusIdcard(), 20));      //证件号	CUSID	VARCHAR(20)
            sbfCustomer.append(getTextValue(customer.getCustomerTypeId(), 2, 2));     //客户类型	CUSTYPE	VARCHAR(2)//TODO 字典
            sbfCustomer.append(getTextValue(customer.getCustomerName(), loanInfo.getCusName(), 60));     //客户名称	CUSNAME	VARCHAR(60)
            sbfCustomer.append(getTextValue(customer.getAddress(), "广东省中山市", 60));     //家庭地址	CUSADDR	VARCHAR(60)
            sbfCustomer.append(getTextValue(StringUtil.isBlank(customer.getDefaultPhone())?null:customer.getDefaultPhone(), loanInfo.getCusPhone(), 16));     //联系电话	TELEPHONE	VARCHAR(16)
            sbfCustomer.append(getTextValue(null != customer.getBelongUserId() ? sysUserService.getSysUserById(customer.getBelongUserId()).getUserName() : null, null, 10));     //管户人	INVESMAIN	VARCHAR(10)
            sbfCustomer.append(getTextValue(StringUtils.isNotBlank(customer.getSex()) ? (customer.getSex().equals("男") ? 1 : 2) : null, 1, 2));      //性别	SEX	VARCHAR(2)
            sbfCustomer.append(getTextValue("3", null, 2));      //个人性质	INDVKIND	VARCHAR(2)
            sbfCustomer.append(getTextValue(customer.getCensusRegister(), "广东中山", 60));      //户籍所在地	NATADDR	VARCHAR(60)
            sbfCustomer.append(getTextValue(customer.getHouseatt(), 1, 2));    //住宅属性	HOUSEATT	VARCHAR(2)//TODO 字典
//            boolean b1=1==customer.getCredentialTypeId();
//            boolean b2=StringUtils.isNotBlank(customer.getIdCard());
//            boolean b3=customer.getIdCard().length()==18;
//            if(1==customer.getCredentialTypeId()&&StringUtils.isNotBlank(customer.getIdCard())&&customer.getIdCard().length()==18) {
//                sbfCustomer.append(getTextValue(customer.getIdCard().substring(6,14),"19700101",8));     //出生日期	BIRTHDAY	VARCHAR(8)
//            }else {
                sbfCustomer.append(getTextValue(null != customer.getBirthday() ? sf2.format(customer.getBirthday()) : null, "19700101", 8));
//            }
            sbfCustomer.append(getTextValue(customer.getEducationalHistoryId(), 9, 2));      //学历	DEGREE	VARCHAR(2)//TODO 字典
            sbfCustomer.append(getTextValue(customer.getMaritalStatusId(),StringUtils.isNotBlank(loanInfo.getCusMarriage())?loanInfo.getCusMarriage():8, 2));     //婚姻状况	MARRIED	VARCHAR(2)//TODO 字典
            sbfCustomer.append(getTextValue(customer.getCompany(), StringUtils.isNotBlank(loanInfo.getCompanyName())?loanInfo.getCompanyName():"广东中山", 60));     //工作单位	WORKPLACE	VARCHAR(60)
            sbfCustomer.append(getTextValue(null, null, 16));     //单位电话	WORKTEL	VARCHAR(16)
            sbfCustomer.append(getTextValue(null, null, 6));    //邮政编码	POSTCODE	VARCHAR(6)
            sbfCustomer.append(getTextValue(StringUtil.isBlank(customer.getPosition())?null:customer.getPosition(), 14, 2));    //职务	DUTY	VARCHAR(2)////TODO 工作岗位 字典
            sbfCustomer.append(getTextValue(StringUtil.isBlank(customer.getRank())?null:customer.getRank(), 1, 2));    //职称	RANK	VARCHAR(2)
            sbfCustomer.append(getTextValue(null, null, 16));     //总资产	TASSET	DECIMAL(13,2)//TODO 总资产
            sbfCustomer.append(getTextValue(null, null, 16));   //总负债	TOWE	DECIMAL(13,2)//TODO 总负债
            sbfCustomer.append(getTextValue(StringUtil.isBlank(loanInfo.getCusFamilyNum())?null:loanInfo.getCusFamilyNum(), null, 10));     //家庭总人口	FMPOP	INTERGER
            sbfCustomer.append(getTextValue(null, null, 16));     //家庭月均收入	INFMMON	DECIMAL(13,2)
            sbfCustomer.append(getTextValue(null, null, 16));      //人月均收入	INPERMON	DECIMAL(13,2)
            sbfCustomer.append(getTextValue(null, null, 16));      //家庭月均支出	OUTFMMON	DECIMAL(13,2)
            sbfCustomer.append(getTextValue(null, null, 16));     //家庭电话	FMTEL	VARCHAR(16)
            sbfCustomer.append(getTextValue(null, null, 20));     //贷款卡号码	LNCRDNO	VARCHAR(20)
            sbfCustomer.append(getTextValue(null, null, 2));     //贷款卡状态	CARDSTAT	VARCHAR(2)
            sbfCustomer.append(getTextValue("6", null, 2));     //最高学位	TOPDEGREE	VARCHAR(2)
            sbfCustomer.append(getTextValue(StringUtil.isBlank(customer.getDefaultPhone())?null:customer.getDefaultPhone(), StringUtil.isBlank(loanInfo.getCusMobilePhone())?null:loanInfo.getCusMobilePhone(), 16));    //手机号码	MOTEL	VARCHAR(16)
            sbfCustomer.append(getTextValue(null, null, 30));    //电子邮箱	EMAIL	VARCHAR(30)
            sbfCustomer.append(getTextValue(null, null, 6));    //通讯地址邮政编码	COMCODE	VARCHAR(6)
            sbfCustomer.append(getTextValue(StringUtil.isBlank(customer.getOccupation())?null:customer.getOccupation(), 11, 2));      //职业	OCCUPATION	VARCHAR(2)
            sbfCustomer.append(getTextValue(customer.getCustomerIndustryId(), 21, 2));     //单位所属行业	INDUSTRY	VARCHAR(2)
            sbfCustomer.append(getTextValue(loanInfo.getCompanyAddress(), null, 60));     //单位地址	CORPADDR	VARCHAR(60)
            sbfCustomer.append(getTextValue(null, null, 6));    //单位地址邮政编码	POSTCODE	VARCHAR(6)
            sbfCustomer.append(getTextValue(StringUtil.isBlank(customer.getBgnyear())?null:customer.getBgnyear(), "2017", 4));    //工作起始年份	BGNYEAR	VARCHAR(4)
            sbfCustomer.append(getTextValue(customer.getLivingAddress(),StringUtils.isNotBlank( loanInfo.getCusLivingAddress())?loanInfo.getCusLivingAddress():"广东中山", 60));     //居住地址	DWELLADDR	VARCHAR(60)
            sbfCustomer.append(getTextValue(StringUtil.isBlank(customer.getDwellCode())?null:customer.getDwellCode(), "528400", 6));     //居住地址邮政编码	DWELLCODE	VARCHAR(6)
            sbfCustomer.append(getTextValue(customer.getLivingConditionId(), 8, 2));    //居住状况	DWELLSTAT	VARCHAR(2)//TODO
            sbfCustomer.append(getTextValue(StringUtil.isBlank(loanInfo.getCusLocalYear())?null:loanInfo.getCusLocalYear(), 1, 10));     //本地居住年限	DWELLYEARS	INTERGER
            sbfCustomer.append(getTextValue(null, null, 10));     //劳动人数	WORKERS	INTERGER
            sbfCustomer.append(getTextValue(null, null, 10));      //子女数量	CHILDS	INTERGER
            sbfCustomer.append(getTextValue(null, null, 2));       //是否需借款人抚养	ISFOSTER	VARCHAR(2)
            sbfCustomer.append(getTextValue("2", null, 2));      //是否缴纳公积金	ISFUNDS	VARCHAR(2)
            sbfCustomer.append(getTextValue(null, null, 40));     //执业资格	TITLE	VARCHAR(40)
            sbfCustomer.append(getTextValue("1", null, 2));    //夫妻在本市内有无名下住房	ISHAVEHOUSE	VARCHAR(2)
            sbfCustomer.append(getTextValue(bizDate, null, 8));     //业务日期	SYS_SRC_DATE	VARCHAR(8)



            pwCustomer.println(new String(sbfCustomer.toString().getBytes(), "UTF-8").substring(0, sbfCustomer.length() - 1));
            pwCustomer.flush();
            fwCustomer.flush();
            fwCustomer.close();
            pwCustomer.close();

//            File spouseFile = new File(spouseFileName);
//            if(!spouseFile.exists()){
//                spouseFile.createNewFile();
//            }
//            fwSpouse = new FileWriter(spouseFile, true);
            fwSpouse = new OutputStreamWriter(new FileOutputStream(spouseFileName,true), "UTF-8");
            pwSpouse = new PrintWriter(fwSpouse);
            if(   (StringUtils.isNotBlank(customer.getSpouseIdCard())||StringUtils.isNotBlank(loanInfo.getCusMateIdcard()))&&
                    (StringUtils.isNotBlank(customer.getSpouseName())||StringUtils.isNotBlank(loanInfo.getCusMateName()))&&
                    (StringUtils.isNotBlank(customer.getSpousePosition())||StringUtils.isNotBlank(loanInfo.getCusMateJob()))
                    ) {
                StringBuffer sbfSpouse = new StringBuffer("");
                sbfSpouse.append(getTextValue(customer.getCustomerId(), null, 20));   // 客户编号	CUSNO	VARCHAR(20)
                sbfSpouse.append(getTextValue(customer.getSpouseName(), loanInfo.getCusMateName(), 20));   // 配偶姓名	NAME	VARCHAR(20)
                sbfSpouse.append(getTextValue(customer.getAddress(), loanInfo.getCusMateAddress(), 60));   // 家庭地址	ADDRESS	VARCHAR(60)
                sbfSpouse.append(getTextValue(StringUtils.isNotBlank(customer.getSpouseSex()) ? (customer.getSpouseSex().equals("男") ? 1 : 2) : null, 1, 2));   // 性别	SEX	VARCHAR(2)
                sbfSpouse.append(getTextValue(1, null, 2));  // 证件类型	CERTTYPE	VARCHAR(2)
                sbfSpouse.append(getTextValue(customer.getSpouseIdCard(), loanInfo.getCusMateIdcard(), 20));  // 证件号码	CERTNUM	VARCHAR(20)
                sbfSpouse.append(getTextValue(customer.getSpousePhone(), loanInfo.getCusMatePhone(), 20));  // 联系电话	PHONE1	VARCHAR(20)
                sbfSpouse.append(getTextValue(customer.getSpousePhone(), loanInfo.getCusMatePhone(), 20));  // 手机号码	PHONE2	VARCHAR(20)
                sbfSpouse.append(getTextValue(customer.getSpouseCompany(), loanInfo.getCusCompanyName(), 60));  // 工作单位	WORKPLACE	VARCHAR(60)
                sbfSpouse.append(getTextValue(customer.getSpousePosition(), 1, 2));  // 职务	DUTY	VARCHAR(2)
                sbfSpouse.append(getTextValue(customer.getSpouseRank(), 1, 2));  // 职称	RANK	VARCHAR(2)
                sbfSpouse.append(getTextValue("10", 9, 2)); // 学历	DEGREE	VARCHAR(2)
                sbfSpouse.append(getTextValue(sf2.format(new Date()), null, 8)); // 业务日期	SYS_SRC_DATE	VARCHAR(8)
                pwSpouse.println(new String(sbfSpouse.toString().getBytes(), "UTF-8").substring(0, sbfSpouse.length() - 1));
            }
            pwSpouse.flush();
            fwSpouse.flush();
            fwSpouse.close();
            pwSpouse.close();
        } catch (Exception e) {
            logger.info("//******写入客户信息出错******//");
            logger.error(e);
        }finally {
            if(null!=fwCustomer){
                fwCustomer.close();
            }
            if(null!=pwCustomer){
                pwCustomer.close();
            }
            if(null!=fwSpouse){
                fwSpouse.close();
            }
            if(null!=pwSpouse){
                pwSpouse.close();
            }
        }


    }



    private void writeGuarantor(LnLoan loan, PadLoanInfo loanInfo, String bizDate) throws IOException {
        OutputStreamWriter fwGuarantor = null;
        PrintWriter pwGuarantor = null;
        try {



//            fwGuarantor = new FileWriter(guarantorFile, true);
            fwGuarantor = new OutputStreamWriter(new FileOutputStream(guarantorFileName,true), "UTF-8");

            pwGuarantor = new PrintWriter(fwGuarantor);
            Map<String,Object> param = new HashMap<String,Object>();
            param.put("loanId", loan.getLoanId());
            List<LnLoanGuarantorBean> guarantorList = lnLoanGuarantorService.getAllLnLoanGuarantorBeanByConds(param);
            StringBuffer sbfGuarantor;
            BaseCrmCustomer customer;

            TmpLoanInfo tmpLoan = lnBalanceService.getTmpLoanInfoByLoanId(loan.getLoanId());

            if(!CollectionUtils.isEmpty(guarantorList)&&null!=tmpLoan&&StringUtils.isNotEmpty(tmpLoan.getAcctNo())){
                for (LnLoanGuarantorBean guarantor : guarantorList) {

                        sbfGuarantor = new StringBuffer("");
                        customer = crmCustomerService.getCrmCustomerById(guarantor.getCustomerId());
                    if(StringUtils.isNotBlank(tmpLoan.getAcctNo())&&StringUtils.isNotBlank(customer.getCustomerName())&&StringUtils.isNotBlank(customer.getIdCard())&&StringUtils.isNotBlank(loanInfo.getResultMoney())) {
                        sbfGuarantor.append(getTextValue(tmpLoan.getAcctNo(), null, 20));//Guarantor贷款账号	ACTNO	VARCHAR(20)	//TODO loan.getLoanAccount  贷款帐号
                        sbfGuarantor.append(getTextValue(customer.getCustomerName(), null, 60));//Guarantor保证人名称	GUARNM	VARCHAR(60)	N
                        sbfGuarantor.append(getTextValue(customer.getCredentialTypeId(), 1, 2));//Guarantor证件类型	CERTTP	VARCHAR(2)	N	同上
                        sbfGuarantor.append(getTextValue(customer.getIdCard(), null, 20));//Guarantor保证人证件号码	IDNO	VARCHAR(20)	N
                        sbfGuarantor.append(getTextValue(loanInfo.getResultMoney(), null, 20));//Guarantor担保金额	GUARAMT	DECIMAL(17,2)	N
                        sbfGuarantor.append(getTextValue(bizDate, null, 8));//Guarantor业务日期	SYS_SRC_DATE	VARCHAR(8)
                        pwGuarantor.println(new String(sbfGuarantor.toString().getBytes(), "UTF-8").substring(0, sbfGuarantor.length() - 1));
                    }
                }

            }

            pwGuarantor.flush();
            fwGuarantor.flush();
            fwGuarantor.close();
            pwGuarantor.close();

        } catch (Exception e) {
            e.printStackTrace();
            logger.info("//******写入担保信息出错******//");
            logger.error(e);
        }finally {
            if(null!=fwGuarantor){
                fwGuarantor.close();
            }
            if(null!=pwGuarantor){
                pwGuarantor.close();
            }

        }
    }

    private String getTextValue(Object value1, Object value2, int length) {

        try{
            StringBuffer value = new StringBuffer("");
            if (null != value1) {
                value.append(value1);
            } else if (null != value2) {
                value.append(value2);
            }

            length -= value.toString().getBytes().length;
            for (int i = 0; i < length; i++) {
                value.append(" ");
            }

            value.append(",");

            return value.toString();
        }catch (Exception e){
            return "";
        }


    }



    public SysParamService getSysParamService() {
        return sysParamService;
    }

    public void setSysParamService(SysParamService sysParamService) {
        this.sysParamService = sysParamService;
    }

    public LnLoanService getLnLoanService() {
        return lnLoanService;
    }

    public void setLnLoanService(LnLoanService lnLoanService) {
        this.lnLoanService = lnLoanService;
    }

    public LnLoanInfoService getLnLoanInfoService() {
        return lnLoanInfoService;
    }

    public void setLnLoanInfoService(LnLoanInfoService lnLoanInfoService) {
        this.lnLoanInfoService = lnLoanInfoService;
    }

    public CrmCustomerService getCrmCustomerService() {
        return crmCustomerService;
    }

    public void setCrmCustomerService(CrmCustomerService crmCustomerService) {
        this.crmCustomerService = crmCustomerService;
    }

    public LnLoanGuarantorService getLnLoanGuarantorService() {
        return lnLoanGuarantorService;
    }

    public void setLnLoanGuarantorService(LnLoanGuarantorService lnLoanGuarantorService) {
        this.lnLoanGuarantorService = lnLoanGuarantorService;
    }

    public SysUserService getSysUserService() {
        return sysUserService;
    }

    public void setSysUserService(SysUserService sysUserService) {
        this.sysUserService = sysUserService;
    }

    public LnBalanceService getLnBalanceService() {
        return lnBalanceService;
    }

    public void setLnBalanceService(LnBalanceService lnBalanceService) {
        this.lnBalanceService = lnBalanceService;
    }

    public LnRepaymentPlanService getLnRepaymentPlanService() {
        return lnRepaymentPlanService;
    }

    public void setLnRepaymentPlanService(LnRepaymentPlanService lnRepaymentPlanService) {
        this.lnRepaymentPlanService = lnRepaymentPlanService;
    }
}
