package com.banger.mobile.webservice.service.impl;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.model.customer.CrmCustomer;
import com.banger.mobile.domain.model.microProduct.*;
import com.banger.mobile.domain.model.microTask.TskSchedule;
import com.banger.mobile.domain.model.pad.*;
import com.banger.mobile.domain.model.uploadFile.SysUploadFile;
import com.banger.mobile.domain.model.user.SysUser;
import com.banger.mobile.facade.customer.CrmCustomerService;
import com.banger.mobile.facade.dept.DeptFacadeService;
import com.banger.mobile.facade.loan.LnLoanService;
import com.banger.mobile.facade.microProduct.*;
import com.banger.mobile.facade.microTask.TskScheduleService;
import com.banger.mobile.facade.uploadFile.SysUploadFileService;
import com.banger.mobile.facade.user.SysUserService;
import com.banger.mobile.facade.webservice.ProductWebService;
import com.banger.mobile.util.DateUtil;
import com.banger.mobile.util.JsonDateValueProcessor;
import com.banger.mobile.util.StringUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.codehaus.xfire.transport.http.XFireServletController;

import javax.jws.WebService;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

@WebService(serviceName = "BangerCrmProductService", endpointInterface = "com.banger.mobile.facade.webservice.ProductWebService")
public class ProductWebServicesImpl implements ProductWebService {
    private static final Logger         logger = Logger.getLogger(ProductWebServicesImpl.class);
    private DeptFacadeService           deptFacadeService;
    private SysUserService              sysUserService;
    private CrmCustomerService          crmCustomerService;
    private PdtLibraryService           pdtLibraryService;
    private PdtProductService           pdtProductService;    
    private PdtLibraryAttachmentService pdtLibraryAttachmentService;
    private PdtProductCustomerService   pdtProductCustomerService;
    private PdtNoticeService            pdtNoticeService;
    private TskScheduleService          tskScheduleService;
    private LnLoanService               lnLoanService;
    private SysUploadFileService        sysUploadFileService;

    /**
     * 
     * @param account
     * @return
     */
    public String getAllProductMicro(String account, String input, Integer pageNumber) {
        try {
            logger.info("pad端产品接口getAllProductMicro开始,account:"+account+",input:"+input+",pageNumber:"+pageNumber);
            JSONArray jsonArray = new JSONArray();
            JsonConfig jsonConfig = new JsonConfig();
            jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());

            Map<String, Object> conds = new HashMap<String, Object>();
            if (StringUtils.isNotEmpty(input)) {
                conds.put("padInput", input);
            }

            Page page = new Page();
            page.setCurrentPage(pageNumber);
            PageUtil<PdtProduct> list = pdtProductService.getPdtProductPage(conds, page);

            List<MicroProduct> plist = new ArrayList<MicroProduct>();
            for (PdtProduct p : list.getItems()) {
                MicroProduct object = new MicroProduct();
                object.setPic(p.getProductCoverImage());// 图片缩略
                object.setProductId(p.getProductId());
                object.setProductName(p.getProductName());
                object.setProductCode(p.getProductCode());
                object.setProductExplain(p.getRemark());
                object.setProductFeature(p.getFeature());
                object.setSuitableUser(p.getSuitableUser());
                object.setApplyData(p.getApplyNeedData());
                plist.add(object);
            }
            String result = jsonArray.fromObject(plist, jsonConfig).toString();
            logger.info("pad端产品接口getAllProductMicro完成,account:"+account+",input:"+input+",pageNumber:"+pageNumber);
            return result;
        } catch (Exception e) {
            logger.error("getAllProduct", e);
            return null;
        }
    }

    /**
     * 
     * @param account
     * @param input
     * @return
     */
    public Integer getAllProductMicroCount(String account, String input) {
        try {
            logger.info("pad端产品接口getAllProductMicroCount开始,account:"+account+",input:"+input);
            Map<String, Object> conds = new HashMap<String, Object>();
            if (StringUtils.isNotEmpty(input)) {
                conds.put("padInput", input);
            }

            Page page = new Page();
            PageUtil<PdtProduct> list = pdtProductService.getPdtProductPage(conds, page);
            logger.info("pad端产品接口getAllProductMicroCount完成,account:"+account+",input:"+input);
            return list.getPage().getTotalRowsAmount();
        } catch (Exception e) {
            logger.error("getAllProduct", e);
            return 0;
        }
    }

    /**
     * 
     * @param productId
     * @return
     */
    public String getProductCustomerMicro(String account, Integer productId, String input,
                                          Integer pageNumber) {
        try {
            logger.info("pad端产品接口getProductCustomerMicro开始,account:"+account+",input:"+input+",productId:"+productId+",pageNumber:"+pageNumber);
            JSONArray jsonArray = new JSONArray();
            JsonConfig jsonConfig = new JsonConfig();
            jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());

            SysUser user = sysUserService.getAllUserByAccount(account);

            Page page = new Page();
            page.setCurrentPage(pageNumber);
            Map<String, Object> conds = new HashMap<String, Object>();
            conds.put("padInput", input);
            if (productId > 0) {
                conds.put("productId", productId);
            }

//            Boolean isInChargeOf = deptFacadeService.isInChargeOfDepartment(user.getUserId() + "");
//            if (isInChargeOf){
//                // 当前用户的下属用户
//                Integer[] belongUserIds = deptFacadeService.getInChargeOfDeptUserIds(user.getUserId());
//                if (belongUserIds != null){
//                    StringBuilder sb = new StringBuilder();
//
//                    for (Integer belongUserId : belongUserIds){
//                        sb.append(String.valueOf(belongUserId));
//                        sb.append(",");
//                    }
//                    if(sb.length() > 0){
//                        String belongUserStr = sb.toString();
//                        belongUserStr += String.valueOf(user.getUserId());
//                        conds.put("manageUserIds",belongUserStr);
//                    }
//                }else {
//                    conds.put("manageUserIds",user.getUserId()+"");
//                }
//            }else {
                conds.put("myUserId",user.getUserId());
//            }

//            conds.put("userId", user.getUserId());// 我添加的意向客户
//            conds.put("brMine", user.getUserId());// 归属给我的客户（客户经理，业务主管）
                                                  // (为了解决机构管理员负责机构A但是归属机构B特殊情况)
//            Integer[] inchargeIntegers = null;
//            if (deptFacadeService.isInChargeOfDepartment(user.getUserId().toString())) {// 是否是业务主管（我负责的机构的客户和归属给我的客户）
//                inchargeIntegers = deptFacadeService.getInChargeOfDeptIds(user.getUserId());
//                String inchargeDepts = "";
//                for (Integer integer : inchargeIntegers) {
//                    inchargeDepts += integer.intValue() + ",";
//                }
//                if (inchargeDepts.length() > 0) {
//                    inchargeDepts = inchargeDepts.substring(0, (inchargeDepts.length() - 1));
//                }
//                conds.put("brDept", inchargeDepts);
//            }
            conds.put("isPad",1); //标记是pad端的查询，主要是区分web端的排序方式
            PageUtil<PdtProductCustomerBean> list = pdtProductCustomerService
                .getPdtProductCustomerBeanPage(conds, page);

            List<ProductCustomer> plist = new ArrayList<ProductCustomer>();
            for (PdtProductCustomerBean pcb : list.getItems()) {
                ProductCustomer object = new ProductCustomer();
                object.setCustomerId(pcb.getCustomerId());
                object.setCustomerNo(pcb.getCustomerNo());
                object.setCustomerName(pcb.getCustomerName());
                object.setCustomerTitle(pcb.getCustomerTitle());
                object.setAddTime(DateUtil.convertDateToString("yyyy-MM-dd", pcb.getCreateDate()));
                object.setAddPerson(pcb.getUserName());
                object.setBelongTo(pcb.getBelongUserName());
                object.setContactTel(pcb.getCphNumber());
                object.setIntentProduct(pcb.getProductName());
                object.setIntentState(pcb.getRemark());
                object.setAddress(pcb.getAddress());
                object.setEmail(pcb.getEmail());
                object.setIsNoGood(pcb.getIsNoGood());
                object.setBelongUserId(pcb.getBelongUserId());

                plist.add(object);
            }
            logger.info("pad端产品接口getProductCustomerMicro完成,account:"+account+",input:"+input+",productId:"+productId+",pageNumber:"+pageNumber);
            String result = jsonArray.fromObject(plist, jsonConfig).toString();
            return result;
        } catch (Exception e) {
            logger.error("getProductCustomer", e);
            return null;
        }
    }

    /**
     * 根据产品id查询购买总数
     * 
     * @param productId
     * @return
     */
    public Integer getProductCustomerMicroCount(String account, String input, Integer productId) {
        try {
            logger.info("pad端产品接口getProductCustomerMicroCount开始,account:"+account+",input:"+input+",productId:"+productId);
            SysUser user = sysUserService.getAllUserByAccount(account);

            Page page = new Page();
            Map<String, Object> conds = new HashMap<String, Object>();
            conds.put("padInput", input);
            if (productId > 0) {
                conds.put("productId", productId);
            }

//            Boolean isInChargeOf = deptFacadeService.isInChargeOfDepartment(user.getUserId() + "");
//            if (isInChargeOf){
//                // 当前用户的下属用户
//                Integer[] belongUserIds = deptFacadeService.getInChargeOfDeptUserIds(user.getUserId());
//                StringBuilder sb = new StringBuilder();
//
//                for (Integer belongUserId : belongUserIds){
//                    sb.append(String.valueOf(belongUserId));
//                    sb.append(",");
//                }
//                if(sb.length() > 0){
//                    String belongUserStr = sb.substring(0,sb.length() - 1);
//                    conds.put("manageUserIds",belongUserStr);
//                }
//            }else {
                conds.put("myUserId",user.getUserId());
//            }
//            conds.put("userId", user.getUserId());// 我添加的意向客户
//            conds.put("brMine", user.getUserId());// 归属给我的客户（客户经理，业务主管）
//                                                  // (为了解决机构管理员负责机构A但是归属机构B特殊情况)
//            Integer[] inchargeIntegers = null;
//            if (deptFacadeService.isInChargeOfDepartment(user.getUserId().toString())) {// 是否是业务主管（我负责的机构的客户和归属给我的客户）
//                inchargeIntegers = deptFacadeService.getInChargeOfDeptIds(user.getUserId());
//                String inchargeDepts = "";
//                for (Integer integer : inchargeIntegers) {
//                    inchargeDepts += integer.intValue() + ",";
//                }
//                if (inchargeDepts.length() > 0) {
//                    inchargeDepts = inchargeDepts.substring(0, (inchargeDepts.length() - 1));
//                }
//                conds.put("brDept", inchargeDepts);
//            }
            PageUtil<PdtProductCustomerBean> list = pdtProductCustomerService
                .getPdtProductCustomerBeanPage(conds, page);
            logger.info("pad端产品接口getProductCustomerMicroCount完成,account:"+account+",input:"+input+",productId:"+productId);
            return list.getPage().getTotalRowsAmount();
        } catch (Exception e) {
            logger.error("getProductCustomerCount", e);
            return 0;
        }
    }

    /**
     * email发送产品信息
     * 
     * @param customerId
     * @param productId
     * @param email
     * @param remark
     * @return
     */
    public boolean sendProductInfo(Integer customerId, Integer productId, String email,
                                   String remark) {
        try {
            logger.info("pad端产品接口sendProductInfo开始,customerId:"+customerId+",productId:"+productId+",email:"+email+",remark:"+remark);
            //mimeMailService.sendProdutInfoMail(productId, email, remark);
            logger.info("pad端产品接口sendProductInfo完成,customerId:"+customerId+",productId:"+productId+",email:"+email+",remark:"+remark);
            return true;
        } catch (Exception e) {
            logger.error("sendProductInfo", e);
            return false;
        }
    }

    /**
     * 产品添加意向客户
     * 
     * @param productId
     * @param customerJson
     * @return
     */
    public String addProductCustomer(Integer productId, String customerJson) {
        try {
            logger.info("pad端产品接口addProductCustomer开始,productId:"+productId+",customerJson:"+customerJson);
            JSONObject jsonObject = JSONObject.fromObject(customerJson);
            AddProductCustomer object = (AddProductCustomer) JSONObject.toBean(jsonObject,
                AddProductCustomer.class);

            SysUser user = sysUserService.getUserByAccount(object.getAccount());
            Integer customerId = null;
            if (object.getCustomerId() != null && object.getCustomerId() > 0) {
                //更新原客户
                customerId = object.getCustomerId();
                CrmCustomer crmCustomer = crmCustomerService.getCustomerById(customerId);
                crmCustomerService.matchCustomerInfo(crmCustomer,object.getCustomerName(),crmCustomer.getIdCard(),object.getContactTel());
                crmCustomer.setAddress(object.getAddress());
                crmCustomerService.updateCrmCustomer(crmCustomer);

            } else {
                // 如果没有客户id,则新增客户
                if (StringUtils.isNotEmpty(object.getCustomerName())){
                    String toName=object.getCustomerName().toLowerCase();
                    if(toName.equals("\"null\"")){
                        object.setCustomerName("null");
                    }
                }
                if (StringUtils.isNotEmpty(object.getAddress())){
                    String toName=object.getAddress().toLowerCase();
                    if(toName.equals("\"null\"")){
                        object.setAddress("null");
                    }
                }

                CrmCustomer crmCustomer = new CrmCustomer(); // 保存新客户
                crmCustomer.setIsReceiveSms(1);
                crmCustomer.setAddress(object.getAddress());
                crmCustomer.setCustomerName(object.getCustomerName());
                String contactTel = object.getContactTel();
                if (contactTel != null && contactTel.length() == 11 && contactTel.startsWith("1")){
                    crmCustomer.setMobilePhone1(contactTel);
                    crmCustomer.setDefaultPhoneType(1);
                }else {
                    crmCustomer.setPhone(contactTel);
                    crmCustomer.setDefaultPhoneType(3);
                }
                crmCustomer.setBelongUserId(user.getUserId());
                crmCustomer.setBelongDeptId(user.getDeptId());
                crmCustomerService.saveWebCustomer(object.getAccount(), crmCustomer);
                customerId = crmCustomer.getCustomerId();
            }

            PdtProductCustomer pdtProductCustomer = new PdtProductCustomer();
            pdtProductCustomer.setProductId(object.getProductId());
            pdtProductCustomer.setCustomerId(customerId);
            pdtProductCustomer.setRemark(object.getIntentState());
            pdtProductCustomer.setCreateUser(user.getUserId());
            pdtProductCustomer.setUpdateUser(user.getUserId());
            pdtProductCustomer.setUserId(user.getUserId());
            pdtProductCustomer.setIsDel(0);
            pdtProductCustomerService.addPdtProductCustomer(pdtProductCustomer);

            // 是否要发送邮件
            /*
            if (StringUtils.isNotEmpty(object.getEmail())) {
                mimeMailService.sendProdutInfoMail(productId, object.getEmail(), object.getRemark());
            }
			*/
            // 是否要添加日程
            if (object.getContactWayType() != null && object.getContactWayType() > 0) {
                TskSchedule tskSchedule = new TskSchedule();
                tskSchedule.setUserId(user.getUserId());
                tskSchedule.setCustomerId(customerId);
                tskSchedule.setContactDate(DateUtil.convertStringToDate("yyyy-MM-dd HH:mm",
                    object.getContactTime()));
                tskSchedule.setContactType(object.getContactWayType());
                tskSchedule.setRemark(object.getScheduleRemark());
                tskSchedule.setAddDate(Calendar.getInstance().getTime());
                tskSchedule.setStatus(0);// 0未完成
                tskSchedule.setCreateDate(Calendar.getInstance().getTime());
                tskSchedule.setUpdateDate(Calendar.getInstance().getTime());
                tskSchedule.setCreateUser(user.getUserId());
                tskSchedule.setUpdateUser(user.getUserId());
                //默认选择第一条进度
                Integer no1CommProgressId = tskScheduleService.getNo1CommProgressId();
                tskSchedule.setCommProgressId(no1CommProgressId);

                tskScheduleService.addNewSchedule(tskSchedule);
            }
            logger.info("pad端产品接口addProductCustomer完成,productId:"+productId+",customerJson:"+customerJson);
            return "true";
        } catch (Exception e) {
            logger.error("setInformRead", e);
            return "false";
        }

    }

    /**
     * 增加日程
     * 
     * @param account
     * @param customerId
     * @param contactWayType
     * @param contactTime
     * @param remark
     * @return
     */
    public String addSchedule(String account, Integer scheduleId, Integer customerId,
                               Integer contactWayType, String contactTime, String remark) {
        try {
            logger.info("pad端产品接口addSchedule开始,account:"+account+",scheduleId:"+scheduleId+",customerId:"+customerId+"," +
                    "contactWayType:"+contactWayType+",contactTime:"+contactTime+",remark:"+remark);
            SysUser user = sysUserService.getUserByAccount(account);

            if (scheduleId == -1) {
                //得到下一个日程的scheduleId
                Integer nextScheduleId = tskScheduleService.getNextScheduleId();

                if (nextScheduleId == -1){
                    return "-1";
                }

                TskSchedule tskSchedule = new TskSchedule();
                tskSchedule.setScheduleId(nextScheduleId);
                tskSchedule.setUserId(user.getUserId());
                tskSchedule.setCustomerId(customerId);
                tskSchedule.setContactDate(DateUtil.convertStringToDate("yyyy-MM-dd HH:mm",
                    contactTime));
                tskSchedule.setContactType(contactWayType);
                tskSchedule.setRemark(remark);
                tskSchedule.setAddDate(Calendar.getInstance().getTime());
                tskSchedule.setStatus(0);// 0未完成
                tskSchedule.setCreateDate(Calendar.getInstance().getTime());
                tskSchedule.setCreateUser(user.getUserId());
                //默认选择第一条进度
                Integer no1CommProgressId = tskScheduleService.getNo1CommProgressId();
                tskSchedule.setCommProgressId(no1CommProgressId);

                tskScheduleService.insertTskSchedule(tskSchedule);
                logger.info("pad端产品接口addSchedule完成,account:"+account+",scheduleId:"+scheduleId+",customerId:"+customerId+"," +
                        "contactWayType:"+contactWayType+",contactTime:"+contactTime+",remark:"+remark);
                return String.valueOf(nextScheduleId);
//                tskScheduleService.addNewSchedule(tskSchedule);
            } else {
                TskSchedule tskSchedule = tskScheduleService.getScheduleById(scheduleId);
                tskSchedule.setUserId(user.getUserId());
                tskSchedule.setCustomerId(customerId);
                tskSchedule.setContactDate(DateUtil.convertStringToDate("yyyy-MM-dd HH:mm",
                    contactTime));
                tskSchedule.setContactType(contactWayType);
                tskSchedule.setRemark(remark);
                tskSchedule.setUpdateDate(Calendar.getInstance().getTime());
                tskSchedule.setUpdateUser(user.getUserId());

                tskScheduleService.updateSchedule(tskSchedule);
                logger.info("pad端产品接口addSchedule完成,account:"+account+",scheduleId:"+scheduleId+",customerId:"+customerId+"," +
                        "contactWayType:"+contactWayType+",contactTime:"+contactTime+",remark:"+remark);
                return String.valueOf(scheduleId);
            }
        } catch (Exception e) {
            logger.error("addSchedule ", e);
            return "-1";
        }
    }

    /**
     * 知识库列表
     * 
     * @return
     */
    public String getAllLibrary(Integer parentId, String input, Integer allSearch, Integer pageNumber) {
        try {
            logger.info("pad端产品接口getAllLibrary开始,parentId:"+parentId + ",input:"+input+",allSearch:"+allSearch+"pageNumber:"+pageNumber);
            JSONArray jsonArray = new JSONArray();
            JsonConfig jsonConfig = new JsonConfig();
            jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());

            Page page = new Page();
            page.setCurrentPage(pageNumber);
            Map<String, Object> conds = new HashMap<String, Object>();
            conds.put("parentId", parentId);
            conds.put("padInput", input);
            conds.put("allSearch", allSearch);
            PageUtil<PdtLibraryBean> list = pdtLibraryService.getPdtLibraryPageForPad(conds, page);
            List<Library> plist = new ArrayList<Library>();
            for (PdtLibraryBean p : list.getItems()) {
                Library object = new Library();
                object.setParentId(p.getParentId());
                object.setIsLeaf(p.getIsLeaf());
                object.setLibraryId(p.getLibraryId());
                object.setLibraryCode(p.getLibNo());
                object.setLibraryName(p.getLibTitle());// 名称
                object.setLibraryContent(p.getLibContent());// 内容html
                object.setCreateDate(p.getCreateDate());

                // 取得附件
                Map<String, Object> condss = new HashMap<String, Object>();
                condss.put("libraryId", object.getLibraryId());
                List<PdtLibraryAttachment> atta = pdtLibraryAttachmentService
                    .getPdtLibraryAttachmentByConds(condss);
                List<LibraryAttachment> files = new ArrayList<LibraryAttachment>();
                for (PdtLibraryAttachment a : atta) {
                    Integer fileId = a.getFileId();
                    SysUploadFile sysUploadFile= sysUploadFileService.getSysUploadFileById(fileId);
                    if (sysUploadFile==null)
                        continue;
                    String fileName =sysUploadFile.getFileName();
                    String fileType = fileName.substring(fileName.lastIndexOf(".")+1);
                    LibraryAttachment f = new LibraryAttachment();
                    f.setFileIcon(fileType);
                    f.setFileUrl(sysUploadFile.getFilePath() + "/" + fileName);
                    f.setFileName(sysUploadFile.getUploadFileName());
                    files.add(f);
                }
                object.setFiles(files);
                plist.add(object);
            }

            LibraryPage libraryPage = new LibraryPage();
            libraryPage.setDataList(plist);
            libraryPage.setPageCount(list.getPage().getTotalPages());
            String result = jsonArray.fromObject(libraryPage, jsonConfig).toString();
            logger.info("pad端产品接口getAllLibrary完成,input:"+input+",pageNumber:"+pageNumber);
            return result;
        } catch (Exception e) {
            logger.error("getAllLibrary error: ", e);
            return null;
        }
    }

    /**
     * 添加意向客户-搜索客户（姓名）
     * 
     * @param account
     * @param customerName
     * @return
     */
    public String queryCustomerByName(String account, String customerName) {
        try {
            logger.info("pad端产品接口queryCustomerByName开始,account:"+account+",customerName:"+customerName);
            Map<String, Object> conds = new HashMap<String, Object>();
            conds.put("fetchRows", "5");// 取前五条
            conds.put("customerName", customerName);
//            List<LnLoanCustomerBean> listBean = lnLoanService.getAllCustomerBeanByConds(conds);
            List<PdtProductCustomerBean> listBean = pdtProductCustomerService.getAllCustomerBeanByConds(conds);

            JSONArray array = new JSONArray();
            for (PdtProductCustomerBean pdtProductCustomerBean : listBean) {
                JSONObject obj = new JSONObject();
                obj.put("customerId", pdtProductCustomerBean.getCustomerId());
                obj.put("customerName", StringUtil.getNotNullValue(pdtProductCustomerBean.getCustomerName()));
                obj.put("contactTel", StringUtil.getNotNullValue(pdtProductCustomerBean.getCphNumber()));
                obj.put("address", StringUtil.getNotNullValue(pdtProductCustomerBean.getAddress()));
                obj.put("email", StringUtil.getNotNullValue(pdtProductCustomerBean.getEmail()));
                obj.put("belongUserId",pdtProductCustomerBean.getBelongUserId());

                array.add(obj);
            }
            logger.info("pad端产品接口queryCustomerByName完成,account:"+account+",customerName:"+customerName);
            return array.toString();
        } catch (Exception e) {
            logger.error("queryCustomerByName", e);
            return null;
        }
    }

    public String queryCustomerByPhone(String account, String phone) {
        try {
            logger.info("pad端产品接口queryCustomerByPhone开始,account:"+account+",phone:"+phone);
            Map<String, Object> conds = new HashMap<String, Object>();
            conds.put("fetchRows", "5");// 取前五条
            conds.put("cphNumber", phone);
//            List<LnLoanCustomerBean> listBean = lnLoanService.getAllCustomerBeanByConds(conds);
            List<PdtProductCustomerBean> listBean = pdtProductCustomerService.getAllCustomerBeanByConds(conds);

            JSONArray array = new JSONArray();
            for (PdtProductCustomerBean pdtProductCustomerBean : listBean) {
                JSONObject obj = new JSONObject();
                obj.put("customerId", pdtProductCustomerBean.getCustomerId());
                obj.put("customerName", StringUtil.getNotNullValue(pdtProductCustomerBean.getCustomerName()));
                obj.put("contactTel", StringUtil.getNotNullValue(pdtProductCustomerBean.getCphNumber()));
                obj.put("address", StringUtil.getNotNullValue(pdtProductCustomerBean.getAddress()));
                obj.put("email", StringUtil.getNotNullValue(pdtProductCustomerBean.getEmail()));
                obj.put("belongUserId",pdtProductCustomerBean.getBelongUserId());
                array.add(obj);
            }
            logger.info("pad端产品接口queryCustomerByPhone完成,account:"+account+",phone:"+phone);
            return array.toString();
        } catch (Exception e) {
            logger.error("queryCustomerByPhone", e);
            return null;
        }
    }

    public String queryCustomerByAddress(String account, String address) {
        try {
            logger.info("pad端产品接口queryCustomerByAddress开始,account:"+account+",address:"+address);
            Map<String, Object> conds = new HashMap<String, Object>();
            conds.put("fetchRows", "5");// 取前五条
            conds.put("address", address);
//            List<LnLoanCustomerBean> listBean = lnLoanService.getAllCustomerBeanByConds(conds);
            List<PdtProductCustomerBean> listBean = pdtProductCustomerService.getAllCustomerBeanByConds(conds);

            JSONArray array = new JSONArray();
            for (PdtProductCustomerBean pdtProductCustomerBean : listBean) {
                JSONObject obj = new JSONObject();
                obj.put("customerId", pdtProductCustomerBean.getCustomerId());
                obj.put("customerName", StringUtil.getNotNullValue(pdtProductCustomerBean.getCustomerName()));
                obj.put("contactTel", StringUtil.getNotNullValue(pdtProductCustomerBean.getCphNumber()));
                obj.put("address", StringUtil.getNotNullValue(pdtProductCustomerBean.getAddress()));
                obj.put("email", StringUtil.getNotNullValue(pdtProductCustomerBean.getEmail()));
                obj.put("belongUserId",pdtProductCustomerBean.getBelongUserId());
                array.add(obj);
            }
            logger.info("pad端产品接口queryCustomerByAddress完成,account:"+account+",address:"+address);
            return array.toString();
        } catch (Exception e) {
            logger.error("queryCustomerByAddress", e);
            return null;
        }
    }

    /**
     * 获取通知列表
     * 
     * @return
     */
    public String getInformList(String account, Integer isRead, String name, Integer pageNumber) {
        try {
            logger.info("pad端产品接口getInformList开始,account:"+account+",isRead:"+isRead+",name:"+name+",pageNumber:"+pageNumber);
            SysUser user = sysUserService.getAllUserByAccount(account);

            HttpServletRequest request = XFireServletController.getRequest();
            String contentUrl = request.getContextPath();

            JSONArray jsonArray = new JSONArray();
            JsonConfig jsonConfig = new JsonConfig();
            jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());

            Page page = new Page();
            page.setCurrentPage(pageNumber);
            Map<String, Object> conds = new HashMap<String, Object>();
            conds.put("userId", user.getUserId());
            conds.put("noticeName", name);
            if (isRead >= 0) {
                conds.put("isRead", isRead);
            }
            PageUtil<PdtNotice> list = pdtNoticeService.getPdtNoticePage(conds, page);

            List<PadNotice> plist = new ArrayList<PadNotice>();
            for (PdtNotice notice : list.getItems()) {
                PadNotice object = new PadNotice();
                object.setInformId(notice.getNoticeId());
                object.setInformName(notice.getNoticeName());
                object.setUpdateTime(DateUtil.convertDateToString("yyyy-MM-dd",
                    notice.getNoticeUpdateDate()));
                object.setIsRead(notice.getIsRead());
                object.setFileIcon(notice.getFileType());
                object.setFileUrl(contentUrl + "../showImage?fullPath=" + notice.getFilePath() + "/"
                                  + notice.getFileName());

                plist.add(object);
            }

            String result = jsonArray.fromObject(plist, jsonConfig).toString();
            logger.info("pad端产品接口getInformList完成,account:"+account+",isRead:"+isRead+",name:"+name+",pageNumber:"+pageNumber);
            return result;
        } catch (Exception e) {
            logger.error("getInformList", e);
            return null;
        }
    }

    /**
     * 获取通知总数
     * 
     * @return
     */
    public Integer getInformListCount(String account, String name, Integer isRead) {
        try {
            logger.info("pad端产品接口getInformListCount开始,account:"+account+",isRead:"+isRead+",name:"+name);
            SysUser user = sysUserService.getAllUserByAccount(account);
            Page page = new Page();
            Map<String, Object> conds = new HashMap<String, Object>();
            conds.put("userId", user.getUserId());
            conds.put("noticeName", name);
            if (isRead >= 0) {
                conds.put("isRead", isRead);
            }
            PageUtil<PdtNotice> list = pdtNoticeService.getPdtNoticePage(conds, page);
            logger.info("pad端产品接口getInformListCount完成,account:"+account+",isRead:"+isRead+",name:"+name);
            return list.getItems().size();
        } catch (Exception e) {
            logger.error("getInformListCount", e);
            return null;
        }
    }

    /**
     * 设置通知已读s
     * 
     * @param informIdJson
     * @return
     */
    public boolean setAllInformRead(String account, String informIdJson) {
        try {
            logger.info("pad端产品接口setAllInformRead开始,account:"+account+",informIdJson:"+informIdJson);
            SysUser user = sysUserService.getAllUserByAccount(account);

            JSONArray jsonArray = JSONArray.fromObject(informIdJson);
            List<PadNotice> list = jsonArray.toList(jsonArray, PadNotice.class);
            String informIds = "";
            for (PadNotice p : list) {
                informIds += p.getInformId() + ",";
            }
            if (informIds.length() > 0) {
                informIds = informIds.substring(0, informIds.length() - 1);
            }

            Map<String, Object> conds = new HashMap<String, Object>();
            conds.put("noticeIds", informIds);
            conds.put("readState", 1);
            conds.put("userId", user.getUserId());
            pdtNoticeService.editPdtNoticeReadByConds(conds);
            logger.info("pad端产品接口setAllInformRead完成,account:"+account+",informIdJson:"+informIdJson);
            return true;
        } catch (Exception e) {
            logger.error("setAllInformRead", e);
            return false;
        }
    }

    /**
     * 设置通知未读
     * 
     * @param informIdJson
     * @return
     */
    public boolean setAllInformUnread(String account, String informIdJson) {
        try {
            logger.info("pad端产品接口setAllInformUnread开始,account:"+account+",informIdJson:"+informIdJson);
            SysUser user = sysUserService.getAllUserByAccount(account);

            JSONArray jsonArray = JSONArray.fromObject(informIdJson);
            List<PadNotice> list = jsonArray.toList(jsonArray, PadNotice.class);
            String informIds = "";
            for (PadNotice p : list) {
                informIds += p.getInformId() + ",";
            }
            if (informIds.length() > 0) {
                informIds = informIds.substring(0, informIds.length() - 1);
            }

            Map<String, Object> conds = new HashMap<String, Object>();
            conds.put("noticeIds", informIds);
            conds.put("readState", 0);
            conds.put("userId", user.getUserId());
            pdtNoticeService.editPdtNoticeReadByConds(conds);
            logger.info("pad端产品接口setAllInformUnread完成,account:"+account+",informIdJson:"+informIdJson);
            return true;
        } catch (Exception e) {
            logger.error("setAllInformUnread", e);
            return false;
        }
    }

    /**
     * 检查是否是重复添加意向
     * 
     * @param account
     * @param customerId
     * @param productId
     * @return
     */
    public String checkIntention(String account, Integer customerId, Integer productId){
        try {
            logger.info("pad端产品接口checkIntention开始,account:"+account+",customerId:"+customerId+",productId:"+productId);
            SysUser sysUser = sysUserService.getAllUserByAccount(account);
            // 是否是业务主管
            Boolean isInChargeOf = deptFacadeService.isInChargeOfDepartment(String.valueOf(sysUser.getUserId()));
            Map<String,Object> paramMap = new HashMap<String, Object>();
            if (isInChargeOf) {
                // 当前用户的下属用户
                Integer[] belongUserIds = deptFacadeService.getInChargeOfDeptUserIds(sysUser.getUserId());
                StringBuilder sb = new StringBuilder();

                for (Integer belongUserId : belongUserIds){
                    sb.append(String.valueOf(belongUserId));
                    sb.append(",");
                }
                if(sb.length() > 0){
                    String belongUserStr = sb.substring(0,sb.length() - 1);
                    paramMap.put("userIds", belongUserStr); // 当前用户所管理的提交申请用户
                }
            } else {
                // 客户经理
                paramMap.put("userId", sysUser.getUserId());
            }

            paramMap.put("customerId",customerId);
            if (productId != -1){
                paramMap.put("productId",productId);
                Integer count = pdtProductCustomerService.getProductCustomerCount(paramMap);
                if(count != null && count > 0){
                    return "false";
                }
            }
            logger.info("pad端产品接口checkIntention完成,account:"+account+",customerId:"+customerId+",productId:"+productId);
            return "true";
        }catch (Exception e){
            logger.error("ProductWebServicesImpl % checkIntention",e);
            return "false";
        }
    }

    /** getter setter **/
    public void setTskScheduleService(TskScheduleService tskScheduleService) {
        this.tskScheduleService = tskScheduleService;
    }

    public void setPdtNoticeService(PdtNoticeService pdtNoticeService) {
        this.pdtNoticeService = pdtNoticeService;
    }

    public void setPdtProductCustomerService(PdtProductCustomerService pdtProductCustomerService) {
        this.pdtProductCustomerService = pdtProductCustomerService;
    }

    public void setPdtLibraryAttachmentService(PdtLibraryAttachmentService pdtLibraryAttachmentService) {
        this.pdtLibraryAttachmentService = pdtLibraryAttachmentService;
    }


    public void setPdtProductService(PdtProductService pdtProductService) {
        this.pdtProductService = pdtProductService;
    }

    public void setPdtLibraryService(PdtLibraryService pdtLibraryService) {
        this.pdtLibraryService = pdtLibraryService;
    }

    public void setCrmCustomerService(CrmCustomerService crmCustomerService) {
        this.crmCustomerService = crmCustomerService;
    }

    public void setSysUserService(SysUserService sysUserService) {
        this.sysUserService = sysUserService;
    }

    public void setDeptFacadeService(DeptFacadeService deptFacadeService) {
        this.deptFacadeService = deptFacadeService;
    }

    public void setLnLoanService(LnLoanService lnLoanService) {
        this.lnLoanService = lnLoanService;
    }

    public void setSysUploadFileService(SysUploadFileService sysUploadFileService) {
        this.sysUploadFileService = sysUploadFileService;
    }
}
