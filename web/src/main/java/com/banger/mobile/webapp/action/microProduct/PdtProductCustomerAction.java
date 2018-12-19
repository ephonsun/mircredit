/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :产品意向客户-Action
 * Author     :QianJie
 * Create Date:Nov 12, 2012
 */
package com.banger.mobile.webapp.action.microProduct;

import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.model.base.customer.BaseCrmCustomer;
import com.banger.mobile.domain.model.customer.CrmCustomer;
import com.banger.mobile.domain.model.customer.CustomerExtendFieldBean;
import com.banger.mobile.domain.model.microProduct.PdtProduct;
import com.banger.mobile.domain.model.microProduct.PdtProductCustomer;
import com.banger.mobile.domain.model.microProduct.PdtProductCustomerBean;
import com.banger.mobile.facade.customer.CrmCustomerService;
import com.banger.mobile.facade.dept.DeptFacadeService;
import com.banger.mobile.facade.microProduct.PdtProductCustomerService;
import com.banger.mobile.facade.microProduct.PdtProductService;
import com.banger.mobile.util.StringUtil;
import com.banger.mobile.util.VmHelper;
import com.banger.mobile.webapp.action.BaseAction;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;

import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author QianJie
 * @version $Id: PdtProductCustomerAction.java,v 0.1 Nov 12, 2012 4:53:48 PM QianJie Exp $
 */
public class PdtProductCustomerAction extends BaseAction {

    private static final long                serialVersionUID = 6119699879164383981L;

    private PdtProductCustomerService        pdtProductCustomerService;              //产品意向客户Service
    private PdtProductService                pdtProductService;                      //产品Service
    private DeptFacadeService                deptFacadeService;                      //机构接口Service
    private PdtProductCustomer               pdtProductCustomer;                     //产品意向客户实体
    private PdtProductCustomerBean           pdtProductCustomerBean;                 //产品意向客户查询实体
    private PageUtil<PdtProductCustomerBean> pdtProductCustomerBeanPage;             //产品意向客户分页查询
    private List<PdtProduct>                 pdtProductList;                         //产品列表
    private CrmCustomerService               crmCustomerService;                     //客户service

    public void setPdtProductCustomerService(PdtProductCustomerService pdtProductCustomerService) {
        this.pdtProductCustomerService = pdtProductCustomerService;
    }

    public void setPdtProductService(PdtProductService pdtProductService) {
        this.pdtProductService = pdtProductService;
    }

    public void setDeptFacadeService(DeptFacadeService deptFacadeService) {
        this.deptFacadeService = deptFacadeService;
    }

    public PdtProductCustomer getPdtProductCustomer() {
        return pdtProductCustomer;
    }

    public void setPdtProductCustomer(PdtProductCustomer pdtProductCustomer) {
        this.pdtProductCustomer = pdtProductCustomer;
    }

    public PdtProductCustomerBean getPdtProductCustomerBean() {
        return pdtProductCustomerBean;
    }

    public void setPdtProductCustomerBean(PdtProductCustomerBean pdtProductCustomerBean) {
        this.pdtProductCustomerBean = pdtProductCustomerBean;
    }

    public PageUtil<PdtProductCustomerBean> getPdtProductCustomerBeanPage() {
        return pdtProductCustomerBeanPage;
    }

    public void setPdtProductCustomerBeanPage(
                                              PageUtil<PdtProductCustomerBean> pdtProductCustomerBeanPage) {
        this.pdtProductCustomerBeanPage = pdtProductCustomerBeanPage;
    }

    public List<PdtProduct> getPdtProductList() {
        return pdtProductList;
    }

    public void setPdtProductList(List<PdtProduct> pdtProductList) {
        this.pdtProductList = pdtProductList;
    }

    public CrmCustomerService getCrmCustomerService() {
        return crmCustomerService;
    }

    public void setCrmCustomerService(CrmCustomerService crmCustomerService) {
        this.crmCustomerService = crmCustomerService;
    }

    /**
     * 查询产品意向客户列表
     * @return
     */
    public String queryPdtProductCustomerList() {
        Map<String, Object> conds = new HashMap<String, Object>();
        pdtProductList = pdtProductService.getAllPdtProductByConds(conds);

        if (pdtProductCustomerBean != null) {
            if (pdtProductCustomerBean.getProductId() != -1) {//查所有产品
                conds.put("productId", pdtProductCustomerBean.getProductId());
            }
            conds.put("customerName", pdtProductCustomerBean.getCustomerName());
            conds.put("cphNumber", pdtProductCustomerBean.getCphNumber());
            conds.put("userName", pdtProductCustomerBean.getUserName());
            conds.put("remark",pdtProductCustomerBean.getRemark());
            conds.put("belongUserName",pdtProductCustomerBean.getBelongUserName());
        }
//        conds.put("userId", this.getLoginInfo().getUserId());//我添加的意向客户
//        conds.put("brMine", this.getLoginInfo().getUserId());//归属给我的客户（客户经理，业务主管） (为了解决机构管理员负责机构A但是归属机构B特殊情况)

//        Integer[] inchargeIntegers = null;
        Boolean isInChargeOf = deptFacadeService.isInChargeOfDepartment();
        if (isInChargeOf){
            String belongUserIds = deptFacadeService.getUserIdsBelongToManager(this.getLoginInfo().getRoles(),"productIntention");
            conds.put("manageUserIds",belongUserIds);
        }else {
            conds.put("myUserId",this.getLoginInfo().getUserId());
        }
//        if (deptFacadeService.isInChargeOfDepartment()) {//是否是业务主管（我负责的机构的客户和归属给我的客户）
//            inchargeIntegers = deptFacadeService.getInChargeOfDeptIds();
//            String inchargeDepts = "";
//            for (Integer integer : inchargeIntegers) {
//                inchargeDepts += integer.intValue() + ",";
//            }
//            if (inchargeDepts.length() > 0) {
//                inchargeDepts = inchargeDepts.substring(0, (inchargeDepts.length() - 1));
//            }
//            conds.put("brDept", inchargeDepts);
//        }
        pdtProductCustomerBeanPage = pdtProductCustomerService.getPdtProductCustomerBeanPage(conds,
            this.getPage());
        replaceNumber();//替换号码
//        isMyAuthorityCustomer(inchargeIntegers, this.getLoginInfo().getUserId());//判断是否是我拥有权限的客户
        int count = this.getPage().getTotalRowsAmount();
        request.setAttribute("recordCount", String.valueOf(count));
        return SUCCESS;
    }

    /**
     * 是否是我有权限的客户
     * @param inchargeIntegers 我负责的机构
     * @param userId 我的UserID
     */
    private void isMyAuthorityCustomer(Integer[] inchargeIntegers, int userId) {
        List<PdtProductCustomerBean> items = new ArrayList<PdtProductCustomerBean>();
        for (PdtProductCustomerBean pdtProductCustomerBean : pdtProductCustomerBeanPage.getItems()) {
            pdtProductCustomerBean.setIsMyAuthority(false);//初始化权限客户值

            if (pdtProductCustomerBean.getBelongUserId().intValue() == userId) {
                pdtProductCustomerBean.setIsMyAuthority(true);
            } else {
                if (inchargeIntegers != null) {
                    for (Integer inchInteger : inchargeIntegers) {
                        if (pdtProductCustomerBean.getBelongDeptId().intValue() == inchInteger
                            .intValue()) {
                            pdtProductCustomerBean.setIsMyAuthority(true);
                        }
                    }
                }
            }
            items.add(pdtProductCustomerBean);
        }

        pdtProductCustomerBeanPage.setItems(items);
    }

    /**
     * 用****替换手机和固话部分号码
     */
    private void replaceNumber() {
        List<PdtProductCustomerBean> items = new ArrayList<PdtProductCustomerBean>();
        for (PdtProductCustomerBean pdtProductCustomerBean : pdtProductCustomerBeanPage.getItems()) {
            String pNumber = VmHelper.getHidePhoneNumber(pdtProductCustomerBean.getCphNumber());
            pdtProductCustomerBean.setReplaceNumber(pNumber);
            items.add(pdtProductCustomerBean);
        }

        pdtProductCustomerBeanPage.setItems(items);
    }

    /**
     * 得到替换后的号码
     * @param number
     * @param type
     * @return
    private String getReplaceNumber(String number, Integer type) {
        if (StringUtil.isNullOrEmpty(number)) {
            return "";
        } else {
            if(type == null){
                type = 3;//如果默认号码类型是null，当作固话处理
            }
            if ((type == 1) || (type == 2)) {//手机
                if (number.length() == 11) {
                    number = number.substring(0, 3) + "****" + number.substring(7, 11);
                } else if (number.length() > 4) {
                    number = number.substring(0, (number.length() - 4)) + "****";
                }
            } else {
                if (number.length() > 4) {
                    number = number.substring(0, (number.length() - 4)) + "****";
                }
            }
        }
        return number;
    }*/

    /**
     * 查询编辑、查看产品时意向客户列表
     * @return
     */
    public String queryChildPdtProductCustomerList() {
        Map<String, Object> conds = new HashMap<String, Object>();
        conds.put("productId", pdtProductCustomerBean.getProductId());
        conds.put("remark", pdtProductCustomerBean.getRemark());
        conds.put("customerName", pdtProductCustomerBean.getCustomerName());
        conds.put("cphNumber", pdtProductCustomerBean.getCphNumber());
        conds.put("userName", pdtProductCustomerBean.getUserName());
        conds.put("belongUserName", pdtProductCustomerBean.getBelongUserName());
//        conds.put("userId", this.getLoginInfo().getUserId());//我添加的意向客户
//        conds.put("brMine", this.getLoginInfo().getUserId());//归属给我的客户（客户经理，业务主管） (为了解决机构管理员负责机构A但是归属机构B特殊情况)

//        Integer[] inchargeIntegers = null;
//        if (deptFacadeService.isInChargeOfDepartment()) {//是否是业务主管（我负责的机构的客户和归属给我的客户）
//            inchargeIntegers = deptFacadeService.getInChargeOfDeptIds();
//            String inchargeDepts = "";
//            for (Integer integer : inchargeIntegers) {
//                inchargeDepts += integer.intValue() + ",";
//            }
//            if (inchargeDepts.length() > 0) {
//                inchargeDepts = inchargeDepts.substring(0, (inchargeDepts.length() - 1));
//            }
//            conds.put("brDept", inchargeDepts);
//        }
        Boolean isInChargeOf = deptFacadeService.isInChargeOfDepartment();
        if (isInChargeOf){
            String belongUserIds = deptFacadeService.getUserIdsBelongToManager(this.getLoginInfo().getRoles(),"productIntention");
            conds.put("manageUserIds",belongUserIds);
        }else {
            conds.put("myUserId",this.getLoginInfo().getUserId());
        }
        pdtProductCustomerBeanPage = pdtProductCustomerService.getPdtProductCustomerBeanPage(conds,
            this.getPage());
        replaceNumber();//替换号码
//        isMyAuthorityCustomer(inchargeIntegers, this.getLoginInfo().getUserId());//判断是否是我拥有权限的客户
        int count = this.getPage().getTotalRowsAmount();
        request.setAttribute("count", String.valueOf(count));
        return SUCCESS;
    }

    /**
     * 删除产品意向客户
     */
    public void delPdtProductCustomer() {
        PrintWriter out = null;
        try {
            out = this.getResponse().getWriter();
            pdtProductCustomerService.delPdtProductCustomerById(pdtProductCustomer
                .getProductCustomerId());
            out.write("SUCCESS");
        } catch (Exception e) {
            log.error(e);
            out.write("删除失败");
        }
    }

    /**
     * 跳转到添加意向客户页面
     * @return
     */
    public String toAddPdtProductCustomerPage() {
        Map<String, Object> conds = new HashMap<String, Object>();//查询所有产品
//        conds.put("productId", request.getParameter("productId"));
        pdtProductList = pdtProductService.getAllPdtProductByConds(conds);
        request.setAttribute("productId", request.getParameter("productId"));
        return SUCCESS;
    }

    /**
     * 根据编号查询所有客户
     */
    public void initFindCustomerByConds() {
        PrintWriter out = null;
        try {
            out = this.getResponse().getWriter();
            String customerName = request.getParameter("customerName");
            if(!StringUtil.isBlank(customerName)){
                customerName=URLDecoder.decode(customerName,"UTF-8");
            }
            String address = request.getParameter("address");
            if(!StringUtil.isBlank(address)){
                address=URLDecoder.decode(address,"UTF-8");
            }
            String cphNumber = request.getParameter("cphNumber");
            Map<String, Object> conds = new HashMap<String, Object>();
            conds.put("fetchRows", "5");//取前五条
            conds.put("customerName", customerName);
            conds.put("address", address);
            conds.put("cphNumber", cphNumber);
            List<PdtProductCustomerBean> listBean = pdtProductCustomerService
                .getAllCustomerBeanByConds(conds);

            JSONArray array = new JSONArray();
            for (PdtProductCustomerBean pdtProductCustomerBean : listBean) {
                JSONObject obj = new JSONObject();
                obj.put("customerId", pdtProductCustomerBean.getCustomerId());
                obj.put("customerName", pdtProductCustomerBean.getCustomerName());
                obj.put("customerTitle", pdtProductCustomerBean.getCustomerTitle());
                obj.put("replaceNumber", VmHelper.getHidePhoneNumber(pdtProductCustomerBean.getCphNumber()));
                obj.put("cphNumber", pdtProductCustomerBean.getCphNumber());
                obj.put("idCard", VmHelper.getHideIdCard(pdtProductCustomerBean.getIdCard()));
                obj.put("address", pdtProductCustomerBean.getAddress());
                array.add(obj);
            }
            out.write(array.toString());
        } catch (Exception e) {
            out.write("");
            log.error("PdtProductCustomerAction initFindCustomerByConds error:" + e.getMessage());
        }
    }

    /**
     * 得到替换后的身份证
     * @param number
     * @param type
     * @return
     
    private String getReplaceIdCard(String idCard) {
        //那这里就是如果长度不足4位，全部隐藏，长度超过四位，不足8位的隐藏前四位，长度超过8位的，末尾保留四位显示，再往前隐藏四位
        if(StringUtil.isNullOrEmpty(idCard)){
            return "";
        }else{
            if(idCard.length()<=4){
                idCard = "";
               for (int i = 0; i < idCard.length(); i++) {
                   idCard += "*";
               }
            }else{
                if(idCard.length()<=8){
                    idCard = "****" + idCard.substring(4,idCard.length());
                }else{
                    idCard = idCard.substring(0,(idCard.length()-8)) + "****" + idCard.substring((idCard.length()-4),idCard.length());
                }
            }
        }
        return idCard;
    }*/

    /**
     * 保存意向客户
     */
    public void savePdtProductCustomer() {
        PrintWriter out = null;
        try {
            out = this.getResponse().getWriter();
            boolean canSave = true;
            String msg = "SUCCESS";
            int customerId = pdtProductCustomer.getCustomerId();
            
            if (customerId == 0) {
                customerId = getCustomerIdByParams();//添加新客户并取得客户ID
                if (customerId == -1) {
                    canSave = false;
                    msg = "保存失败";    
                } 
            }else{
                //修改原客户
                String customerName = request.getParameter("customerName");
                String isUpdate = request.getParameter("isUpdate");
                if (isUpdate != null && isUpdate.trim().equals("true")){
                    CrmCustomer crmCustomer = crmCustomerService.getCustomerById(customerId);
                    String cphNumber = request.getParameter("cphNumber");
                    String address = request.getParameter("address");

                    crmCustomer.setUpdateUser(this.getLoginInfo().getUserId());
                    crmCustomer.setAddress(address);
                    crmCustomerService.matchCustomerInfo(crmCustomer,customerName,null,cphNumber);
//                    crmCustomerService.updateCustomerRandom(crmCustomer);
                    crmCustomerService.updateCrmCustomer(crmCustomer);
                }

                //客户“xxx”已添加过对产品“yyy”的意向，不能重复添加。
                if (pdtProductCustomer.getProductId() != null && pdtProductCustomer.getProductId() > 0) {
                    Map<String, Object> conds = new HashMap<String, Object>();
                    conds.put("customerId", customerId);
                    conds.put("productId", pdtProductCustomer.getProductId());
                    List<PdtProductCustomer> list = pdtProductCustomerService.getAllPdtProductCustomerByConds(conds);
                    if(list.size() > 0){
                        canSave = false;
                        String productInfo = request.getParameter("productName");
                        int index = productInfo.indexOf('|');
                        String productName = "";
                        if (productInfo != null && index != -1){
                            productName = productInfo.substring(index + 1,productInfo.length());
                        }
                        msg = "客户“"+customerName+"”已添加过对产品“"+productName+"”的意向，不能重复添加。";
                    }
                }
            }

            if(canSave){    
                int userId = this.getLoginInfo().getUserId();
                pdtProductCustomer.setCreateUser(userId);
                pdtProductCustomer.setUpdateUser(userId);
                pdtProductCustomer.setUserId(userId);
                pdtProductCustomer.setIsDel(0);
                pdtProductCustomer.setCustomerId(customerId);
                pdtProductCustomerService.addPdtProductCustomer(pdtProductCustomer);
            }
            out.write(msg);  
        } catch (Exception e) {
            out.write("保存失败");  
            log.error(e);
        }
    }

    /**
     * 根据界面输入参数保存新客户并得到客户Id
     * @return
     */
    private int getCustomerIdByParams() {
        try {
            String customerName = request.getParameter("customerName");
            String address = request.getParameter("address");
            String cphNumber = request.getParameter("cphNumber");

            CrmCustomer crmCustomer = new CrmCustomer(); //保存新客户
            crmCustomer.setIsReceiveSms(1);
            crmCustomer.setAddress(address);
            crmCustomer.setCustomerName(customerName);
            if (StringUtils.isNotEmpty(cphNumber) && cphNumber.startsWith("1") && cphNumber.length() == 11){
                crmCustomer.setMobilePhone1(cphNumber);
                crmCustomer.setDefaultPhoneType(1);
            }else {
                crmCustomer.setPhone(cphNumber);
                crmCustomer.setDefaultPhoneType(3);
            }
            crmCustomer.setBelongUserId(this.getLoginInfo().getUserId());
            crmCustomer.setBelongDeptId(this.getLoginInfo().getDeptId());
            CustomerExtendFieldBean exField = new CustomerExtendFieldBean();

            crmCustomerService.saveCustomer(crmCustomer, exField);
            return crmCustomer.getCustomerId();
        } catch (Exception e) {
            // TODO: handle exception
            log.error("PdtProductCustomerAction % getCustomerIdByParams",e);
            return -1;
        }
    }

    /**
     * 跳转到添加客户意向页面——小页卡
     * 
     * @return
     */
    public String addPdtProductCusCardPage(){
        try {
            Map<String,Object> conds = new HashMap<String, Object>();
            conds.put("productId",request.getParameter("productId"));
            String customerIdStr = request.getParameter("customerId");
            if(StringUtil.isNotEmpty(customerIdStr)){
                //意向产品
                pdtProductList = pdtProductService.getAllPdtProductByConds(conds);
                //客户信息
                BaseCrmCustomer customer = crmCustomerService.getCrmCustomerById(Integer.parseInt(customerIdStr));
                request.setAttribute("crmCustomer",customer);
                request.setAttribute("productId",request.getParameter("productId"));
            }
            return SUCCESS;
        }catch (Exception e){
            log.error("PdtProductCustomerAction % addPdtProductCusCardPage",e);
            return ERROR;
        }
    }

    /**
     * 跳转到选择更新方式页面
     */
    public String toChooseAddUpdate() {
        try {
            return SUCCESS;
        } catch (Exception e) {
            log.error("toChooseAddUpdate action error:", e);
            return ERROR;
        }
    }
}
