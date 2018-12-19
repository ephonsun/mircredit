/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :客户管理实现类
 * Author     :zhangxiang
 * Version    :1.0
 * Create Date:May 3, 2012
 */
package com.banger.mobile.facade.impl.customer;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.dao.customer.CrmCustomerDao;
import com.banger.mobile.dao.customer.CustomizedFieldDao;
import com.banger.mobile.dao.customer.FamilyNameDao;
import com.banger.mobile.domain.model.base.customer.BaseCrmCustomer;
import com.banger.mobile.domain.model.base.customer.BaseFamilyName;
import com.banger.mobile.domain.model.customer.*;
import com.banger.mobile.domain.model.dept.CusBelongToBean;
import com.banger.mobile.domain.model.template.CrmTemplate;
import com.banger.mobile.domain.model.templateField.CrmTemplateField;
import com.banger.mobile.domain.model.user.IUserInfo;
import com.banger.mobile.domain.model.user.SysUser;
import com.banger.mobile.facade.customer.CrmCustomerRelativesService;
import com.banger.mobile.facade.customer.CrmCustomerService;
import com.banger.mobile.facade.customer.CrmCustomerShareService;
import com.banger.mobile.facade.dept.DeptFacadeService;
import com.banger.mobile.facade.microTask.TskScheduleService;
import com.banger.mobile.facade.template.CrmTemplateService;
import com.banger.mobile.facade.user.SysUserService;
import com.banger.mobile.util.*;
import com.ibatis.common.logging.Log;
import com.ibatis.common.logging.LogFactory;
import net.sourceforge.pinyin4j.PinyinHelper;
import org.apache.commons.lang.StringUtils;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CrmCustomerServiceImpl implements CrmCustomerService {

    protected final Log                 log = LogFactory.getLog(getClass());
    private CrmCustomerDao              crmCustomerDao;
    private CustomizedFieldDao          customizedFieldDao;
    private FamilyNameDao               familyNameDao;
    private DeptFacadeService           deptFacadeService;                  //机构Service
    private CrmCustomerShareService     crmCustomerShareService;             //共享客户service
    private CrmCustomerRelativesService crmCustomerRelativesService;
    private CrmTemplateService          temp;                               //模板
    private SysUserService              sysUserService;
    private TskScheduleService          tskScheduleService;                 //日程信息service

    public FamilyNameDao getFamilyNameDao() {
        return familyNameDao;
    }

    public void setFamilyNameDao(FamilyNameDao familyNameDao) {
        this.familyNameDao = familyNameDao;
    }

    public CustomizedFieldDao getCustomizedFieldDao() {
        return customizedFieldDao;
    }

    public void setCustomizedFieldDao(CustomizedFieldDao customizedFieldDao) {
        this.customizedFieldDao = customizedFieldDao;
    }

    public void setCrmCustomerDao(CrmCustomerDao crmCustomerDao) {
        this.crmCustomerDao = crmCustomerDao;
    }

    public DeptFacadeService getDeptFacadeService() {
        return deptFacadeService;
    }

    public void setDeptFacadeService(DeptFacadeService deptFacadeService) {
        this.deptFacadeService = deptFacadeService;
    }

    public CrmCustomerShareService getCrmCustomerShareService() {
        return crmCustomerShareService;
    }

    public void setCrmCustomerShareService(CrmCustomerShareService crmCustomerShareService) {
        this.crmCustomerShareService = crmCustomerShareService;
    }

    public CrmCustomerRelativesService getCrmCustomerRelativesService() {
        return crmCustomerRelativesService;
    }

    public void setCrmCustomerRelativesService(CrmCustomerRelativesService crmCustomerRelativesService) {
        this.crmCustomerRelativesService = crmCustomerRelativesService;
    }

    public CrmTemplateService getTemp() {
        return temp;
    }

    public void setTemp(CrmTemplateService temp) {
        this.temp = temp;
    }

    public void setSysUserService(SysUserService sysUserService) {
        this.sysUserService = sysUserService;
    }

    public void setTskScheduleService(TskScheduleService tskScheduleService) {
        this.tskScheduleService = tskScheduleService;
    }

    /**
     * 获得登录信息
     * @return
     */
    private IUserInfo getUserLoginInfo() {
        HttpServletRequest req = org.apache.struts2.ServletActionContext.getRequest();
        HttpSession session = req.getSession();
        return (IUserInfo) session.getAttribute("LoginInfo");
    }

    /**
     * 添加客户
     */
    public void addCrmCustomer(CrmCustomer crmCustomer) {
        crmCustomerDao.addCrmCustomer(crmCustomer);
    }

    /**
     * 修改客户
     */
    public void updateCrmCustomer(BaseCrmCustomer crmCustomer) {
        CrmCustomer oldCus = getCrmCustomerById(crmCustomer.getCustomerId());
        crmCustomerDao.updateCrmCustomer(crmCustomer);
        if (!oldCus.getBelongUserId().equals(crmCustomer.getBelongUserId())
            || !oldCus.getBelongDeptId().equals(crmCustomer.getBelongDeptId())) {
            String userids = "";
            if (crmCustomer.getBelongUserId().equals(0)) {
                userids = getUserIdsBydeptId(crmCustomer.getBelongDeptId().toString());
            } else {
                userids = crmCustomer.getBelongUserId().toString();
            }
            crmCustomerShareService
                .cancelShareUser(userids, crmCustomer.getCustomerId().toString());
        }
    }

    /**
     * 根据deptId获取UserIds
     * @param deptId
     * @return
     */
    private String getUserIdsBydeptId(String deptId) {
        String ids = "";
        List<CusBelongToBean> userList = deptFacadeService.getBelongToUserAndDeptTreeList(deptId);
        for (CusBelongToBean user : userList) {
            if (user.getType().equals("U")) {
                if (ids.equals("")) {
                    ids = user.getId().toString();
                } else {
                    ids = ids + "," + user.getId().toString();
                }
            }
        }
        return ids;
    }

    /**
     * 修改客户号码
     */
    public void updateCusPhoneNumber(BaseCrmCustomer customer) {
        crmCustomerDao.updateCusPhoneNumber(customer);
    }

    /**
     * 添加客户自定义
     */
    public void addCustomizedField(Map<String, String> parameters) {
        customizedFieldDao.addCustomizedField(parameters);
    }

    /**
     * 删除客户自定义信息
     */
    public void deleteCustomizedField(Integer id) {
        customizedFieldDao.deleteCustomizedField(id);
    }

    /**
     * 删除客户
     */
    public void deleteCrmCustomer(Integer id) {
        crmCustomerDao.deleteCrmCustomer(id);
    }

    /**
     * 修改客户自定义
     */
    public void updateCustomizedField(Map<String, String> parameters) {
        customizedFieldDao.updateCustomizedField(parameters);
    }

    /**
     * 根据客户ID得到客户实体
     */
    public CrmCustomer getCrmCustomerById(Integer id) {
        CrmCustomer cus = crmCustomerDao.getCrmCustomerById(id);
        if (cus != null && cus.getBelongUserId() == null) {
            cus.setBelongUserId(0);
        }
        return cus;
    }

    /**
     * 根据客户编号得到客户实体
     */
    public CrmCustomer getCustomerByCustomerNo(String customerNo) {
        return crmCustomerDao.getCustomerByCustomerNo(customerNo);
    }

    /**
     * 根据客户ID得到客户自定义
     */
    public CustomerExtendFieldBean getCustomizedFieldById(Integer id) {
        return customizedFieldDao.getCustomizedFieldById(id);
    }

    /**
     * 分页查询
     */
    public PageUtil<CrmCustomer> getCrmCustomerPage(Map<String, Object> parameters, Page page) {
        return crmCustomerDao.getCrmCustomerPage(parameters, page);
    }

    /**
     * 设置客户的客户类型ID为O
     */
    public void updateCrmCustomerByCrmCustomerTypeById(Integer customerTypeId) {
        crmCustomerDao.updateCrmCustomerByCrmCustomerTypeById(customerTypeId);
    }

    /**
     * 设置客户的所处行业ID为O
     */
    public void updateCrmCustomerByCustomerIndustryId(Integer customerIndustryId) {
        crmCustomerDao.updateCrmCustomerByCustomerIndustryId(customerIndustryId);
    }

    /**
     * 设置拜访客户
     */
    public void updateCrmCustomerByVisit(CrmCustomer crmCustomer) {
        crmCustomerDao.updateCrmCustomerByVisit(crmCustomer);
    }

    /**
     * 获取客户百家姓
     */
    public List<BaseFamilyName> getNickName() {
        return familyNameDao.getNickName();
    }

    /**
     * 删除客户至垃圾箱
     */
    public void delCustomerByCustomerIds(String customerIds) {
        crmCustomerDao.delCustomerByCustomerIds(customerIds);
        crmCustomerShareService.cancelShareCus(customerIds); //删除共享信息
        tskScheduleService.delCusSchedule(customerIds);      //删除日程信息
    }

    /**
     * 清空垃圾箱
     */
    public void delCustomersComplete(Map<String, String> parameters) {
        crmCustomerDao.delCustomersComplete(parameters);
        crmCustomerRelativesService.delRelativesInDustbin(parameters); //删除关联信息
    }

    /**
     * 还原垃圾箱
     */
    public void restoreCustomerByCustomerIds(Map<String, String> parameters) {
        crmCustomerDao.restoreCustomerByCustomerIds(parameters);
    }

    /**
     * 彻底删除客户
     */
    public void delCusComplete(String customerIds) {
        crmCustomerDao.delCusComplete(customerIds);
        crmCustomerRelativesService.delRelativesByCusId(customerIds); //删除关联信息
    }

    /**
     * 还原客户
     */
    public void restoreCustomers(String customerIds) {
        crmCustomerDao.restoreCustomers(customerIds);
    }

    /**
     * 是否有该客户的权限
     */
    public Integer checkPermission(CrmCustomer crmCustomer, Integer[] arr) {
        if (getUserLoginInfo().getUserId().equals(crmCustomer.getBelongUserId())) {
            return 1;
        } else if (isInChargeOfDepartment(arr, crmCustomer)) {
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * 是否是业务主管
     * @param arr
     * @param crmCustomer
     * @return true 是  False 否
     */
    private Boolean isInChargeOfDepartment(Integer[] arr, CrmCustomer crmCustomer) {
        Integer flag = 0;
        if (arr != null) {
            for (int i = 0; i < arr.length; i++) {
                if (arr[i].equals(crmCustomer.getBelongDeptId())) {
                    flag = 1;
                    break;
                } else
                    flag = 0;
            }
            if (flag.equals(1)) {
                return true;
            } else
                return false;
        } else
            return false;
    }

    /**
     * 客户是否已彻底删除
     */
    public Boolean isDelCustomer(Integer customerId) {
        CrmCustomer customer = crmCustomerDao.getCrmCustomerById(customerId);
        if (customer != null) {
            if (customer.getIsDel().equals(1)) {
                return true;
            } else {
                return false;
            }
        } else {
            return true;
        }
    }

    /**
     * 更新最近联系时间
     */
    public void updateLastContactDate(Integer customerId, Date lastContactDate,
                                      String lastContactType) {
        Map<String, String> map = new HashMap<String, String>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        map.put("customerId", customerId.toString());
        map.put("lastContactDate", lastContactDate == null ? null : sdf.format(lastContactDate));
        map.put("lastContactType", lastContactType);
        crmCustomerDao.updateLastContactDate(map);
    }

    /**
     * 设置客户表的归属用户ID为0
     */
    public void updateCrmCustomerBybelongUserId(Integer belongUserId) {
        crmCustomerDao.updateCrmCustomerBybelongUserId(belongUserId);
    }

    /**
     * 分页查询客户
     */
    public PageUtil<CrmCustomerEva> getCustomerPage(Map<String, Object> parameters, Page page) {
        return crmCustomerDao.getCustomerPage(parameters, page);
    }

    /**
     * 把对象转化成map
     */
    public Map<String, Object> getMap(Object obj) {
        Map<String, Object> map = new HashMap<String, Object>();
        PropertyInfo[] pis = ReflectorUtil.getProperties(obj.getClass());
        for (PropertyInfo pi : pis) {
            Object val = ReflectorUtil.getPropertyValue(obj, pi.getName());
            if (val instanceof Date) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                map.put(pi.getName(), sdf.format((Date) obj));
            } else {
                map.put(pi.getName(), val);
            }
        }
        return map;
    }

    /**
     * 根据客户ids查询客户集合
     */
    public List<CrmCustomer> selectCusByIds(String customerIds) {
        return crmCustomerDao.selectCusByIds(customerIds);
    }

    /**
     * 提取每个汉字的首字母 
     */
    public String getPinYinHeadChar(String str) {
        String convert = "";
        for (int j = 0; j < str.length(); j++) {
            char word = str.charAt(j);
            //提取汉字的首字母 
            String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(word);
            if (pinyinArray != null) {
                convert += pinyinArray[0].charAt(0);
            } else {
                convert += word;
            }
        }
        return convert;
    }

    /**
     * 更改客户归属
     */
    public void modifyCusBelongTo(Map<String, String> map) {
        crmCustomerDao.modifyCusBelongTo(map);

        String userids = "";
        if (map.get("belongUserId").equals(0)) {
            userids = getUserIdsBydeptId(map.get("belongDeptId"));
        } else {
            userids = map.get("belongUserId");
        }
        crmCustomerShareService.cancelShareUser(userids, map.get("cusIds"));
    }

    /**
     * 更改客户归属
     */
    public void modifyCusBelongToAll(Map<String, String> map) {
        crmCustomerDao.modifyCusBelongToAll(map);

        String userids = "";
        if (map.get("belongUserId").equals(0)) {
            userids = getUserIdsBydeptId(map.get("belongDeptId"));
        } else {
            userids = map.get("belongUserId");
        }
        String str = "";
        List<CrmCustomer> list = crmCustomerDao.getCustomersByUserId(map.get("transferUserid"));
        for (CrmCustomer crmCustomer : list) {
            str += crmCustomer.getCustomerId() + ",";
        }
        str = str.substring(0, str.length() - 1);
        crmCustomerShareService.cancelShareUser(userids, str);
    }

    /**
     * 根据号码查询客户 -------不包含权限
     */
    public List<CrmCustomer> selectCustomerByPhone(String phoneNumber) {
        return crmCustomerDao.selectCustomerByPhone(phoneNumber);
    }

    /**
     * 根据用户ids查询客户集合
     */
    public List<CrmCustomer> getCustomersByUserId(String userid) {
        return crmCustomerDao.getCustomersByUserId(userid);
    }

    /**
     * 是否是共享客户
     */
    public Boolean checkShareCus(Integer cusId, Integer userid) {
        Boolean isInChargeof = deptFacadeService.isInChargeOfDepartment();
        String userids = "";
        if (isInChargeof) {
            userids = getCurrentInChargeUserIds(userid.toString());
        } else {
            userids = userid.toString();
        }
        return crmCustomerDao.checkShareCus(cusId, userids);
    }

    /**
     * 保存客户共享
     */
    public String saveShareCustomer(Map<String, String> map) {
//        List<CrmCustomer> cusList = selectCusByIds(map.get("cusIds"));
        String[] cusIds = map.get("cusIds").split(",");

        SysUser user = sysUserService.getSysUserById(Integer.valueOf(map.get("shareUsers")));
        try {
            for(String cus:cusIds) {
                CrmCustomer crmCustomer = crmCustomerDao.getCrmCustomerById(Integer.valueOf(cus));
                Integer belong_dept_id = user.getDeptId();
                Integer belong_use_id = user.getUserId();
                crmCustomer.setBelongDeptId(belong_dept_id);
                crmCustomer.setBelongUserId(belong_use_id);
                crmCustomerDao.updateCrmCustomer(crmCustomer);
            }

//            if (map.get("type").equals("insert")) {
//                saveShare(cusList, map.get("shareUsers"), 0);
//            } else if (map.get("type").equals("modify")) {
//                String addUsers = "";
//                String delUsers = "";
//                //处理新增和删除的共享用户
//                String[] sUsers = map.get("shareUsers").split(",");
//                String[] oUsers = map.get("oldShareUsers").split(",");
//                for (String su : sUsers) {
//                    Boolean bo = true;
//                    for (String ou : oUsers) {
//                        if (su.equals(ou)) {
//                            bo = false;
//                        }
//                    }
//                    if (bo) {
//                        if (addUsers.equals("")) {
//                            addUsers = su;
//                        } else {
//                            addUsers = addUsers + "," + su;
//                        }
//                    }
//                }
//                for (String ou : oUsers) {
//                    Boolean bo = true;
//                    for (String su : sUsers) {
//                        if (ou.equals(su)) {
//                            bo = false;
//                        }
//                    }
//                    if (bo) {
//                        if (delUsers.equals("")) {
//                            delUsers = ou;
//                        } else {
//                            delUsers = delUsers + "," + ou;
//                        }
//                    }
//                }
//                if (!addUsers.equals("")) {
//                    saveShare(cusList, addUsers, 0);
//                }
//                if (!delUsers.equals("")) {
//                    crmCustomerShareService.cancelShareUser(delUsers, map.get("cusIds"));
//                }
//            }
            return "success";
        } catch (Exception e) {
            return "error";
        }
    }

    /**
     * 保存客户共享
     * @param cusList
     * @param shareUsers
     */
    private void saveShare(List<CrmCustomer> cusList, String shareUsers, int userId) {
        String[] users = shareUsers.split(",");
        for (CrmCustomer cus : cusList) {
            for (String userid : users) {
                if (cus.getBelongUserId().toString().equals(userid)) {
                    continue;
                } else {
                    Integer[] arr = deptFacadeService
                        .getInChargeOfDeptIds(Integer.parseInt(userid));
                    Boolean boo = true;
                    if (arr != null) {
                        for (int i = 0; i < arr.length; i++) {
                            if (arr[i].equals(cus.getBelongDeptId())) {
                                boo = false;
                            }
                        }
                    }
                    if (boo) {
                        //添加到数据库
                        CrmCustomerShare obj = new CrmCustomerShare();
                        obj.setCustomerId(cus.getCustomerId());
                        if (userId == 0) {
                            obj.setUserId(getUserLoginInfo().getUserId());
                        } else {
                            obj.setUserId(userId);
                        }
                        obj.setShareUserId(Integer.parseInt(userid));
                        obj.setCreateUser(getUserLoginInfo().getUserId());
                        obj.setUpdateUser(getUserLoginInfo().getUserId());
                        crmCustomerShareService.addShareCustomer(obj);
                    }
                }
            }
        }
        //删除重复的记录
        crmCustomerShareService.delRepeatShare();
    }

    /**
     * 工作托管
     */
    public void workTrusteeship(String userId, String shareUserId) {
        List<CrmCustomer> cusList = getCustomersByUserId(userId);
        saveShare(cusList, shareUserId, Integer.parseInt(userId));
    }

    /**
     * 取消工作托管
     */
    public void cancelWorkTrusteeship(String userId) {
        List<CrmCustomer> cusList = getCustomersByUserId(userId);
        String cusIds = "";
        for (CrmCustomer cus : cusList) {
            if (cusIds.equals(""))
                cusIds = cus.getCustomerId().toString();
            else
                cusIds = cusIds + "," + cus.getCustomerId().toString();
        }
        if (!StringUtil.isEmpty(cusIds)) {
            crmCustomerShareService.cancelShareUser(userId, cusIds);
        }
    }

    /**
     * 人员机构 更改归属 删除共享信息
     */
    public void cancelShareInfoByUserIds(String userIds) {
        crmCustomerShareService.cancelShareInfoByUserIds(userIds);
    }

    /**
     * 查询导出客户
     */
    public List<CrmExportBean> queryExportCustomer(Map<String, Object> parameters, int startRow,
                                                   int endRow) {
        parameters.put("startRow", startRow);
        parameters.put("endRow", endRow);
        return crmCustomerDao.queryExportCustomer(parameters);
    }

    public String saveWebCustomer(String account, CrmCustomer crmCustomer) {
        try {
            if (crmCustomer != null) {
                if (crmCustomer.getCustomerId() != null && crmCustomer.getCustomerId() > 0) {
                    updateCrmCustomer(crmCustomer);
                } else {
                    SysUser user = sysUserService.getUserByAccount(account);
                    crmCustomer.setBelongUserId(user.getUserId());
                    crmCustomer.setBelongDeptId(user.getDeptId());
                    SetCustomer(crmCustomer, user.getUserId());
                    Map<String, String> parameters = new HashMap<String, String>();
                    addCrmCustomer(crmCustomer);
                    //保存自定义字段
                    parameters.put("customerId", crmCustomer.getCustomerId().toString());
                    addCustomizedField(parameters);
                }
                return crmCustomer.getCustomerId().toString();
            }else{
                return "0"; 
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return "0";
        }
    }

    /**
     * 保存客户全部信息
     */
    public void saveCustomer(CrmCustomer crmCustomer, CustomerExtendFieldBean exField) {
        if (crmCustomer != null) {
            SetCustomer(crmCustomer, getUserLoginInfo().getUserId());
            if (crmCustomer.getCustomerId() != null && crmCustomer.getCustomerId() > 0) {
                Map<String, String> parameters = setCustomizedFields(exField, crmCustomer, false);
                updateCrmCustomer(crmCustomer);
                if (parameters.containsKey("updatestr")) {
                    parameters.put("customerId", crmCustomer.getCustomerId().toString());
                    updateCustomizedField(parameters);
                }
            } else {
                Map<String, String> parameters = setCustomizedFields(exField, crmCustomer, true);
                addCrmCustomer(crmCustomer);
                //保存自定义字段
                parameters.put("customerId", crmCustomer.getCustomerId().toString());
                addCustomizedField(parameters);
            }
        }
    }

    /**
     * 新增客户实体默认值
     * @param cus
     */
    private void SetCustomer(CrmCustomer cus, Integer userid) {
        cus.setCustomerNamePinyin(getPinYinHeadChar(cus.getCustomerName()));
        cus.setCreateUser(userid);
        cus.setUpdateUser(userid);
        cus.setTemplateIds("");
        cus.setIsDel(0);
        cus.setIsTrash(0);
        cus.setIsVisit(0);
        if (cus.getMobilePhone1() != null && !cus.getMobilePhone1().equals("")) {
            cus.setMobilePhone1Regular(PhoneUtil.getPhoneRule(cus.getMobilePhone1()));
        }
        if (cus.getMobilePhone2() != null && !cus.getMobilePhone2().equals("")) {
            cus.setMobilePhone2Regular(PhoneUtil.getPhoneRule(cus.getMobilePhone2()));
        }
    }

    /**
     * 新增客户自定义字段设置
     * @param customized
     * @param cus
     * @param isInsert
     * @return
     */
    private Map<String, String> setCustomizedFields(CustomerExtendFieldBean customized,
                                                    CrmCustomer cus, Boolean isInsert) {
        customized.setCreateUser(getUserLoginInfo().getUserId());
        customized.setUpdateUser(getUserLoginInfo().getUserId());
        customized.setCreateDate(new Date());
        customized.setUpdateDate(new Date());
        String fieldstr = "", valuestr = "", templateIds = "", updatestr = "";
        List<CrmTemplate> templateList = temp.getTemplates(); //获取所有业务模版
        DateUtil.format(customized);
        for (CrmTemplate template : templateList) {
            //hasval 页面中模版中是否有填值
            Boolean hasVal = false;
            //对应模版中所有字段Field
            for (CrmTemplateField field : template.getFields()) {
                //根据field实体字段名获取字段值
                Object val = ReflectorUtil.getPropertyValue(customized, field.getFieldName());
                //数据库字段名
                String[] fieldStrArr = fieldstr.split(",");
                String colName = field.getExtFieldName();
                boolean hasSame = false;
                if (fieldStrArr != null && fieldStrArr.length > 0){
                    for (String field1 : fieldStrArr){
                        if (colName.equals(field1)) {
                            hasSame = true;
                            break;
                        }
                    }
                }
                if (hasSame){
                    //去除相同项
                    continue;
                }
                //字段类型
                String type = field.getTemplateFieldType();

                if ((val != null) && (!StringUtil.isEmpty(val.toString()))) {
                    String value = "";
                    if (val instanceof String) {
                        value = "'" + StringUtil.ReplaceSQLChar(val.toString()) + "'";
                        if (type.equals("YesNo"))
                            value = "'yes'";
                    } else if (val instanceof Date) {
                        value = "TO_DATE('" + val.toString() + "','yyyy-MM-dd')";
                    } else if (val instanceof Double) {
                        value = val.toString();
                    } else {
                        return null;
                    }
                    if (isInsert) {
                        if (StringUtil.isEmpty(fieldstr)) {
                            fieldstr = colName;
                            valuestr = value;
                        } else {
                            fieldstr = fieldstr + "," + colName;
                            valuestr = valuestr + "," + value;
                        }
                    } else {
                        if (StringUtil.isEmpty(updatestr)) {
                            updatestr = colName + "=" + value;
                        } else {
                            updatestr = updatestr + "," + colName + "=" + value;
                        }
                    }
                    hasVal = true;
                } else {
                    if (isInsert) {
                        if (StringUtil.isEmpty(fieldstr)) {
                            fieldstr = colName;
                            valuestr = "null";
                        } else {
                            fieldstr = fieldstr + "," + colName;
                            valuestr = valuestr + "," + "null";
                        }
                    } else {
                        if (StringUtil.isEmpty(updatestr)) {
                            updatestr = colName + "=" + "null";
                        } else {
                            updatestr = updatestr + "," + colName + "=" + "null";
                        }
                    }
                }
            }
            if (hasVal) {
                if (StringUtil.isEmpty(templateIds)) {
                    templateIds = String.valueOf(template.getTemplateId());
                } else {
                    templateIds = templateIds + "," + String.valueOf(template.getTemplateId());
                }
            }
        }
        cus.setTemplateIds(templateIds); //设置客户业务模版
        Map<String, String> customizedField = new HashMap<String, String>();
        if (isInsert) {
            if ((!StringUtil.isEmpty(fieldstr)) && (!StringUtil.isEmpty(valuestr))) {
                customizedField.put("fieldstr", fieldstr);
                customizedField.put("valuestr", valuestr);
            }
        } else {
            if (!StringUtil.isEmpty(updatestr)) {
                customizedField.put("updatestr", updatestr);
            }
        }
        return customizedField;
    }

    /**
     * 查询生日提醒客户
     */
    public PageUtil<CrmCustomerBirthday> queryBirthdayRemind(Page page) {
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("UserId", getUserLoginInfo().getUserId());
        Date BeginTime = new Date();
        Date EndTime = new Date();
        EndTime = DateUtil.addDay(EndTime, 6);
        if (BeginTime.getYear() == EndTime.getYear()) {
            parameters.put("SameYear", 1);
        } else {
            parameters.put("SameYear", 0);
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        parameters.put("BeginTime", sdf.format(BeginTime));
        parameters.put("EndTime", sdf.format(EndTime));
        PageUtil<CrmCustomerBirthday> cusList = crmCustomerDao
            .queryBirthdayRemind(parameters, page);
        if (cusList != null) {
            String sex = "";
            for (CrmCustomerBirthday cus : cusList.getItems()) {
                if (!StringUtil.isEmpty(cus.getSex()) && cus.getSex().equals("女")) {
                    sex = "她";
                } else {
                    sex = "他";
                }
                cus.setRemindContent(cus.getCustomerName() + "的生日到了，别忘了祝福" + sex + "哦！");
            }
            return cusList;
        } else {
            return null;
        }
    }

    /**
     * 搜索客户PAD
     */
    public List<CrmCustomer> getCrmCustomerForPad(String userid, String input) {
        Map<String, String> map = new HashMap<String, String>();
        beforQueryListforPad(map, userid);
        map.put("customerName", input);
        return crmCustomerDao.getCrmCustomerForPad(map);
    }

    /**
     * 搜索我的客户PAD
     */
    public List<CrmCustomer> getMyCrmCustomerForPad(String userid) {
        Map<String, String> map = new HashMap<String, String>();
        beforQueryListforPad(map, userid);
        return crmCustomerDao.getMyCrmCustomerForPad(map);
    }

    /**
     * pad查询客户之前
     * @param map
     * @param userid
     */
    private void beforQueryListforPad(Map<String, String> map, String userid) {
        boolean isInChargeof = deptFacadeService.isInChargeOfDepartment(userid);
        String inChargeOfDeptIds = "";
        String inChargeOfUserIds = "";
        if (isInChargeof) {
            inChargeOfDeptIds = getCurrentUserInChargeOfDeptIds(userid);
            if (StringUtil.isEmpty(inChargeOfDeptIds)) {
                inChargeOfDeptIds = "-1";
            }
            inChargeOfUserIds = getCurrentInChargeUserIds(userid);
        } else {
            inChargeOfDeptIds = "-1";
            inChargeOfUserIds = userid;
        }
        map.put("isTrash", "0");
        map.put("BelongTo", "brAll");
        map.put("ContainsShare", "ContainsShare");
        map.put("InChargeOfUserIds", inChargeOfUserIds);
        map.put("InChargeOfDeptIds", inChargeOfDeptIds);
        map.put("UserId", userid);
    }

    /**
     * 获取客户PAD
     */
    public CrmCustomer getCustomerInfo(Integer customeId) {
        return getCrmCustomerById(customeId);
    }

    /**
     * 当前用户有权限的      机构ids
     * @param userid
     * @return
     */
    private String getCurrentUserInChargeOfDeptIds(String userid) {
        String deptIds = "";
        Integer[] arr = deptFacadeService.getInChargeOfDeptIds(Integer.parseInt(userid));
        if (arr != null) {
            for (int i = 0; i < arr.length; i++) {
                if (deptIds.equals(""))
                    deptIds = arr[i].toString();
                else
                    deptIds = deptIds + "," + arr[i];
            }
        }
        return deptIds;
    }

    /**
     * 当前用户有权限的      用户ids
     * @param userid
     * @return
     */
    private String getCurrentInChargeUserIds(String userid) {
        String userIds = "";
        Integer[] arr = deptFacadeService.getInChargeOfDeptUserIds(Integer.parseInt(userid));
        if (arr != null) {
            for (int i = 0; i < arr.length; i++) {
                if (userIds.equals(""))
                    userIds = arr[i].toString();
                else
                    userIds = userIds + "," + arr[i];
            }
        }
        if (userIds.equals("")) {
            userIds = userid;
        } else {
            userIds = userIds + "," + userid;
        }
        return userIds;
    }

    /**
     * 得到最近10条客户
     */
    public List<CrmCustomer> getRecentlyCustomers(Map<String, Object> condition) {
        return this.crmCustomerDao.getRecentlyCustomers(condition);
    }

    /**
     * 得到下一个新建客户ID
     * @return
     * @see com.banger.mobile.facade.customer.CrmCustomerService#getNextCustomerId()
     */
    public Integer getNextCustomerId() {
        return this.crmCustomerDao.getNextCustomerId();
    }

    /**
     * 根据客户NOS查询客户集合
     * @param customerNos
     * @return
     * @see com.banger.mobile.facade.customer.CrmCustomerService#selectCusByNos(java.lang.String)
     */
    public List<CrmCustomer> selectCusByNos(String customerNos) {
        return crmCustomerDao.selectCusByNos(customerNos);
    }

    /**
     * 任务管理查询下次联系客户
     * @param contactId
     * @return
     */
    public CrmCustomer getCustomerForTskNextContact(Integer contactId) {
        return crmCustomerDao.getCustomerForTskNextContact(contactId);
    }

    /**
     * 用户更改归属 修改客户归属
     * @param userid
     * @param deptid
     */
    public void setCustomerOfChangeBelong(String userid, String deptid) {
        crmCustomerDao.setCustomerOfChangeBelong(userid, deptid);
    }

    /**
     * 通过条件查询客户ID集合
     * @return
     */
    public List<Integer> getCusByCondition(Map<String, Object> map) {
        return crmCustomerDao.getCusByCondition(map);
    }
    
    /**
     * 更新客户头像
     * @param map
     */
    public void updateCrmCustomerHeadShow(Map<String, Object> map){
        crmCustomerDao.updateCrmCustomerHeadShow(map);
    }
    
    /**
     * 通过客户姓名或号码获取客户
     * @param map
     * @return
     */
    public List<CrmCustomer> getCusByCusNameOrPhone(Map<String, String> map){
       // return crmCustomerDao.getCusByCusNameOrPhone(map);
    	return null;
    }

    /**
     * 更新客户为不良客户
     * @param customerParamMap
     */
    public void updateCrmCustomerIsNogood(Map<String, Object> customerParamMap){
        crmCustomerDao.updateCrmCustomerIsNogood(customerParamMap);
    }

    /**
     * 更新客户地址
     * @param customerParamMap
     */
    public void updateCrmCustomerAddress(Map<String, Object> customerParamMap) {
        crmCustomerDao.updateCrmCustomerAddress(customerParamMap);
    }

    /**
     * 验证客户为不良客户
     * 
     * @param paramMap
     * @return
     */
    public Boolean checkIsNogoodCus(Map<String, Object> paramMap){
        return crmCustomerDao.checkIsNogoodCus(paramMap);
    }

	/**
	 * 通过电话号码查找客户
	 * @param phoneNum
	 * @return
	 * @see com.banger.mobile.facade.customer.CrmCustomerService#getCustomersByTel(java.lang.String)
	 */
	public List<CrmCustomer> getCustomersByTel(String phoneNum) {
		return crmCustomerDao.getCustomersByTel(phoneNum);
	}
	
	/**
	 * 根据客户ID得到客户实体
	 */
	public CrmCustomer getCustomerById(Integer id) {
		if(id == -1){
			return null;
		}
		CrmCustomer cus = crmCustomerDao.getCrmCustomerById(id);
		if (cus!=null){
			if(cus.getBelongUserId()==null){
				cus.setBelongUserId(0);
			}
			return cus;
		}
		return null;
	}

    /**
     * 批量插入客户数据
     * 
     * @param customerListForUpdate
     * @param customerListForInsert
     * @param customerListForInsert
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void crmImportBatch(List<CrmCustomer> customerListForUpdate,List<CrmCustomer> customerListForInsert){
        if (customerListForUpdate != null && customerListForUpdate.size() > 0){
            crmCustomerDao.updateCrmCustomerBatch(customerListForUpdate);
        }
        if (customerListForInsert != null && customerListForInsert.size() > 0){
            crmCustomerDao.addCrmCustomerBatch(customerListForInsert);
//        customizedFieldDao.addCustomizedFieldBatch(extFieldMapList);
            customizedFieldDao.addCustomizedFieldByCustomerBatch(customerListForInsert);
        }
    }

    /**
     * 批量更新客户数据
     * @param customerList
     */
    public void updateCustomerBatch(List<CrmCustomer> customerList){
        crmCustomerDao.updateCrmCustomerBatch(customerList);
    }

    /**
     * 根据传入的id_card列表查询出客户出存在的id_card列表
     *
     * @param paramMap
     * @return
     */
    public List<BaseCrmCustomer> selectIdCardListByIdCard(Map<String, Object> paramMap){
        return crmCustomerDao.selectIdCardListByIdCard(paramMap);
    }

    public Integer updateImpCrmCustomer(CrmCustomer crmCustomer){
        return crmCustomerDao.updateImpCrmCustomer(crmCustomer);
    }
   
    /**
     * 通过客户姓名和电话号码查找
     */
	public List<BaseCrmCustomer> getCrmCustomerByCrm(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return crmCustomerDao.getCrmCustomerByCrm(params);
	}


    public Integer updateCustomerRandom(CrmCustomer crmCustomer){
        return crmCustomerDao.updateCustomerRandom(crmCustomer);
    }

    /**
	 * 检验客户编码是否存在,服务端校验函数
	 * @param customerNo 客户编码
	 * @param customerId 客户Id
	 * @param userId 用户Id
	 * @return
	 */
	public String checkCustomerNo(String customerNo,Integer customerId,Integer userId){
		if(!StringUtil.isNullOrEmpty(customerNo)){
			CrmCustomer cust = this.getCustomerByCustomerNo(customerNo);
			if (cust != null) {
				if (customerId!=null && customerId>0 && cust.getCustomerId().equals(customerId)){
				} else {
					boolean isInChargeof = deptFacadeService.isInChargeOfDepartment();
					Integer[] arr = isInChargeof ? deptFacadeService
							.getInChargeOfDeptIds() : null;
					Integer haspms = this.checkPermission(cust,arr);
					boolean isShareCus = this.checkShareCus(cust.getCustomerId(),userId);
					if (isShareCus) {
						haspms = 1;
					}
	
					String outStr = "";
					if (haspms == 1) {
						if (cust.getBelongUserId() <= 0
								|| cust.getBelongUserId() == null) {
							outStr = "已存在相同客户编号的客户\"" + cust.getCustomerName()
									+ "\"，此客户的归属机构为\"" + cust.getDeptName()
									+ "\"!";
						} else {
							outStr = "已存在相同客户编号的客户\"" + cust.getCustomerName()
									+ "\"!";
						}
					} else {
						if (cust.getBelongUserId() <= 0
								|| cust.getBelongUserId() == null) {
							outStr = "已存在相同客户编号的客户\"" + cust.getCustomerName()
									+ "\"，此客户的归属机构为\"" + cust.getDeptName()
									+ "\"!";
						} else {
							outStr = "已存在相同客户编号的客户\"" + cust.getCustomerName()
									+ "\"，此客户的归属机构为\"" + cust.getDeptName()
									+ "\"，归属人员为\"" + cust.getUserName() + "\"!";
						}
					}
					return outStr;
				}
			}
		}
		return "success";
	}

    /**
     * 设置客户的称谓
     *
     * 1.客户有性别 有称谓 改了姓名之后 称谓就对应变掉
     * 2.有性别 没称谓 改了姓名之后 称谓加上
     * 3.没性别 有称谓 改了姓名之后 性别 称谓都为空
     *
     * 只要姓名和性别一旦变化，根据姓名和性别自动产生称谓：

     * l  如果性别为“男”，则产生为“姓+先生”

     * l  如果性别为“女”，则产生为“姓+女士”

     * l  如果性别或姓名为<空>，则产生为<空>

     * l  如果姓名拆分没有匹配到姓，则产生为“姓名+先生\女士
     * @param crmCustomer
     * @return
     */
    public void setCustomerNickName(BaseCrmCustomer crmCustomer){
        String sex = crmCustomer.getSex();
        String cusName = crmCustomer.getCustomerName();
        //姓或者姓名（如果姓名拆分没有匹配到姓则为姓名）
        String familyNameTemp = "";
        //百家姓
        List<BaseFamilyName> familyNameList = this.getNickName();
        for (BaseFamilyName familyName : familyNameList) {
            if (cusName.indexOf(familyName.getFamilyName()) == 0) {
                familyNameTemp = familyName.getFamilyName();
                break;
            }
        }
        if (sex != null && !sex.trim().equals("")){
            //客户有性别 有称谓 改了姓名之后 称谓就对应变掉
            if (StringUtils.isNotEmpty(sex)){
                if (sex.equals("男")){
                    if (!familyNameTemp.equals("")){
                        crmCustomer.setCustomerTitle(familyNameTemp+"先生");
                    }else {
                        crmCustomer.setCustomerTitle(cusName+"先生");
                    }
                }else if (sex.equals("女")){
                    if (!familyNameTemp.equals("")){
                        crmCustomer.setCustomerTitle(familyNameTemp+"女士");
                    }else {
                        crmCustomer.setCustomerTitle(cusName+"女士");
                    }
                }
            }
        }else if (sex == null || sex.trim().equals("")){
            //没性别 有称谓 改了姓名之后 性别 称谓都为空
            crmCustomer.setCustomerTitle("");
        }
    }

    /**
     * 当客户基本信息被更改时，对客户的拼音、性别、称呼、年龄、生日等信息，做一些匹配
     * @param crmCustomer
     */
    public void matchCustomerInfo(BaseCrmCustomer crmCustomer, String customerName, String idCard, String phone) {
        if (StringUtils.isNotEmpty(customerName) && !customerName.trim().equals("")) {
            //如果名字改变了，就要自动生成拼音，并把性别、称呼置空
            if (!customerName.equals(crmCustomer.getCustomerName())){
                crmCustomer.setCustomerName(customerName);
                //crmCustomer.setCustomerNamePinyin(ImportUtil.getPinYinHeadChar(crmCustomer.getCustomerName()));
                this.setCustomerNickName(crmCustomer);
            }
        }
        if (StringUtils.isNotEmpty(idCard) && !idCard.trim().equals("")) {
            if (!(crmCustomer.getIdCard() != null && idCard.equals(crmCustomer.getIdCard()))){
                //if (IDCardUtil.validateCard(idCard) ) {//生日的特殊处理。
                //    crmCustomer.setBirthday(ImportUtil.parseStringToDateBeforeToday(IDCardUtil.getBirthByIdCard(idCard)));
                //}
                crmCustomer.setIdCard(idCard);
            }
        }
        if (StringUtils.isNotEmpty(phone)) {
            String mobilePhone1 = crmCustomer.getMobilePhone1();
            String mobilePhone2 = crmCustomer.getMobilePhone2();
            String fixedPhone = crmCustomer.getPhone();
            String fax = crmCustomer.getFax();
            if (!((mobilePhone1 != null && mobilePhone1.equals(phone)) || (mobilePhone2 != null && mobilePhone2.equals(phone))
                    || (fixedPhone != null && fixedPhone.equals(phone))
                    || (fax != null && fax.equals(phone)))){
                //不等于客户原联系方式中的任何一个
                Integer defaultPhoneType = crmCustomer.getDefaultPhoneType();
                if (phone.length() == 11 && phone.startsWith("1")){
                    //手机
                    if (mobilePhone1 == null || mobilePhone1.trim().equals("")){
                        //如果手机1为空，则插入到手机1，设置手机1为默认号码
                        crmCustomer.setMobilePhone1(phone);
                        crmCustomer.setDefaultPhoneType(1);
                    }else if (mobilePhone2 == null || mobilePhone2.trim().equals("")){
                        //如果手机2为空，则插入到手机2，设置手机2为默认号码
                        crmCustomer.setMobilePhone2(phone);
                        crmCustomer.setDefaultPhoneType(2);
                    }else {
                        //如果手机1为空，手机2不为空，则更新手机1的号码，并且随着手机1为默认号码
                        crmCustomer.setMobilePhone1(phone);
                        crmCustomer.setDefaultPhoneType(1);
                    }
                }else {
                    //固话
                    if (fixedPhone == null || fixedPhone.trim().equals("")){
                        //如果固话为空，则插入到固话
                        crmCustomer.setPhone(phone);
                    }else {
                        //如果固话不为空，则更新固话
                        crmCustomer.setPhone(phone);
                    }
                }
            }
        }
    }

    /**
     * map.put("idCard",String)
     * @param idCard
     * @return
     */
    @Override
    public List<BaseCrmCustomer> getCrmCustomerByIdCard(Map<String,Object> map) {
        return crmCustomerDao.getCromCustomerByIdcard(map);
    }

    /**
     * 根据客户码查找客户
     * @param customerNo
     * @return
     */
    @Override
    public List<BaseCrmCustomer> getCrmCustomerByKHM(String customerNo) {
        return crmCustomerDao.getCrmCustomerByCHM(customerNo);
    }

    /**
     * 获取客户名称首字母拼音为空的记录
     * @return
     */
    @Override
    public List<BaseCrmCustomer> getCrmCustomerByPYisNull(Map<String,Object> map) {
        return crmCustomerDao.getCrmCustomerByPYisNull(map);
    }

    @Override
    public Integer getCrmCustomerByPYisNullCount() {
        return crmCustomerDao.getCrmCustomerByPYisNullCount();
    }

    @Override
    public List<BaseCrmCustomer> getCusByConditionByMap(Map<String,Object> map){
        return crmCustomerDao.getCusByConditionByMap(map);
    }
}
