/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :产品-Action
 * Author     :QianJie
 * Create Date:Nov 12, 2012
 */
package com.banger.mobile.webapp.action.microProduct;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import com.banger.mobile.facade.param.SysParamService;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.model.microProduct.PdtProduct;
import com.banger.mobile.domain.model.microProduct.PdtProductCustomer;
import com.banger.mobile.domain.model.microProduct.PdtProductCustomerBean;
import com.banger.mobile.facade.dept.DeptFacadeService;
import com.banger.mobile.facade.microProduct.PdtProductCustomerService;
import com.banger.mobile.facade.microProduct.PdtProductService;
import com.banger.mobile.util.DateUtil;
import com.banger.mobile.util.FileType;
import com.banger.mobile.util.StringUtil;
import com.banger.mobile.util.VmHelper;
import com.banger.mobile.webapp.action.BaseAction;

/**
 * @author QianJie
 * @version $Id: PdtProductAction.java,v 0.1 Nov 12, 2012 4:52:33 PM QianJie Exp $
 */
public class PdtProductAction extends BaseAction {

    private static final long serialVersionUID = 4480905508209947426L;

    private PdtProductService         pdtProductService;                      //产品Service
    private PdtProductCustomerService pdtProductCustomerService;              //产品意向客户Service
    private DeptFacadeService         deptFacadeService;                      //机构接口Service
    private SysParamService sysParamService;

    private static final int    BUFFERED_SIZE    = 100;

    public void setSysParamService(SysParamService sysParamService) {
        this.sysParamService = sysParamService;
    }

    public void setPdtProductService(PdtProductService pdtProductService) {
        this.pdtProductService = pdtProductService;
    }
    public void setPdtProductCustomerService(PdtProductCustomerService pdtProductCustomerService) {
        this.pdtProductCustomerService = pdtProductCustomerService;
    }
    public void setDeptFacadeService(DeptFacadeService deptFacadeService) {
        this.deptFacadeService = deptFacadeService;
    }

    private PageUtil<PdtProduct>             pdtProductPage;             //产品分页查询
    private PdtProduct                       pdtProduct;                 //产品实体
    private File                             logoImage;                  //产品logo
    private PageUtil<PdtProductCustomerBean> pdtProductCustomerBeanPage; //产品意向客户分页查询


    public PageUtil<PdtProduct> getPdtProductPage() {
        return pdtProductPage;
    }

    public void setPdtProductPage(PageUtil<PdtProduct> pdtProductPage) {
        this.pdtProductPage = pdtProductPage;
    }

    public PageUtil<PdtProductCustomerBean> getPdtProductCustomerBeanPage() {
        return pdtProductCustomerBeanPage;
    }

    public void setPdtProductCustomerBeanPage(
            PageUtil<PdtProductCustomerBean> pdtProductCustomerBeanPage) {
        this.pdtProductCustomerBeanPage = pdtProductCustomerBeanPage;
    }

    public PdtProduct getPdtProduct() {
        return pdtProduct;
    }

    public void setPdtProduct(PdtProduct pdtProduct) {
        this.pdtProduct = pdtProduct;
    }

    public File getLogoImage() {
        return logoImage;
    }

    public void setLogoImage(File logoImage) {
        this.logoImage = logoImage;
    }

    /**
     * 根据参数查询产品数据
     * @return
     */
    public String queryPdtProductList() {
        try {
            Map<String, Object> parameterMap = new HashMap<String, Object>();
            if (pdtProduct != null) {
                parameterMap.put("productName", pdtProduct.getProductName());
                parameterMap.put("productCode", pdtProduct.getProductCode());
                parameterMap.put("createStartDate", repairDateTime(request.getParameter("createStartDate"),"S"));
                parameterMap.put("createEndDate", repairDateTime(request.getParameter("createEndDate"),"E"));
            }
            this.pdtProductPage = pdtProductService.getPdtProductPage(parameterMap, this.getPage());
            int count = this.getPage().getTotalRowsAmount();
            request.setAttribute("count", String.valueOf(count));
            return SUCCESS;
        } catch (Exception ex) {
            log.error(ex); //记录错误日志
            return ERROR;
        }
    }

    private String repairDateTime(Object obj,String repairType){
        String repairDate = "";
        if(obj != null ){
            if(StringUtil.isNotEmpty(obj.toString())){
                if(repairType.equals("S")){
                    repairDate = obj.toString() + " 00:00:00";
                }else{
                    repairDate = obj.toString() + " 23:59:59";
                }
            }
        }
        return repairDate;
    }

    /**
     * 删除产品
     */
    public void delPdtProduct() {
        PrintWriter out = null;
        try {
            out = this.getResponse().getWriter();
            int productId = Integer.parseInt(request.getParameter("productId"));
            String productName = request.getParameter("productName").toString();
            Map<String, Object> conds = new HashMap<String, Object>();
            conds.put("productId", productId);
            List<PdtProductCustomer> pcList = pdtProductCustomerService.getAllPdtProductCustomerByConds(conds);
            if(pcList.size() > 0 ){
                out.write("产品\""+ productName +"\"下有关联意向客户，不能够删除！");
            }else{
                pdtProductService.delPdtProductById(productId);
                out.write("SUCCESS");
            }

        } catch (Exception ex) {
            out.write("删除失败");
            log.error(ex);
        }
    }

    /**
     * 跳转到新增页面
     * @return
     */
    public String toAddPdtProductPage() {
        return SUCCESS;
    }

    /**
     * 跳转到编辑页面
     * @return
     */
    public String toEditPdtProductPage() {
        int productId = Integer.parseInt(request.getParameter("productId"));
        pdtProduct = pdtProductService.getPdtProductById(productId);

        Map<String, Object> conds = new HashMap<String, Object>();
        conds.put("productId", productId);
//        conds.put("userId", this.getLoginInfo().getUserId());//我添加的意向客户
//        conds.put("brMine", this.getLoginInfo().getUserId());//归属给我的客户（客户经理，业务主管） (为了解决机构管理员负责机构A但是归属机构B特殊情况)
//
//        Integer[] inchargeIntegers = null;
//        if(deptFacadeService.isInChargeOfDepartment()){//是否是业务主管（我负责的机构的客户和归属给我的客户）
//            inchargeIntegers = deptFacadeService.getInChargeOfDeptIds();
//            String inchargeDepts = "";
//            for (Integer integer : inchargeIntegers) {
//                inchargeDepts += integer.intValue()+",";
//            }
//            if(inchargeDepts.length() > 0){
//                inchargeDepts = inchargeDepts.substring(0,(inchargeDepts.length()-1));
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
        pdtProductCustomerBeanPage = pdtProductCustomerService.getPdtProductCustomerBeanPage(conds, this.getPage());
        replaceNumber();//替换号码
//        isMyAuthorityCustomer(inchargeIntegers,this.getLoginInfo().getUserId());//判断是否是我拥有权限的客户
        int count = this.getPage().getTotalRowsAmount();
        request.setAttribute("count", String.valueOf(count));
        return SUCCESS;
    }

    /**
     * 跳转到查看产品页面
     * @return
     */
    public String toViewPdtProductPage() {
        int productId = Integer.parseInt(request.getParameter("productId"));
        pdtProduct = pdtProductService.getPdtProductById(productId);

        Map<String, Object> conds = new HashMap<String, Object>();
        conds.put("productId", productId);
//        conds.put("userId", this.getLoginInfo().getUserId());//我添加的意向客户
//        conds.put("brMine", this.getLoginInfo().getUserId());//归属给我的客户（客户经理，业务主管） (为了解决机构管理员负责机构A但是归属机构B特殊情况)
//
//        Integer[] inchargeIntegers = null;
//        if(deptFacadeService.isInChargeOfDepartment()){//是否是业务主管（我负责的机构的客户和归属给我的客户）
//            inchargeIntegers = deptFacadeService.getInChargeOfDeptIds();
//            String inchargeDepts = "";
//            for (Integer integer : inchargeIntegers) {
//                inchargeDepts += integer.intValue()+",";
//            }
//            if(inchargeDepts.length() > 0){
//                inchargeDepts = inchargeDepts.substring(0,(inchargeDepts.length()-1));
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
        pdtProductCustomerBeanPage = pdtProductCustomerService.getPdtProductCustomerBeanPage(conds, this.getPage());
        replaceNumber();//替换号码
//        isMyAuthorityCustomer(inchargeIntegers,this.getLoginInfo().getUserId());//判断是否是我拥有权限的客户
        int count = this.getPage().getTotalRowsAmount();
        request.setAttribute("count", String.valueOf(count));
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
    private void replaceNumber(){
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
     * 保存产品(新增、编辑)
     */
    public void savePdtProduct() {
        PrintWriter out = null;
        try {
            out = this.getResponse().getWriter();
            String msg = "SUCCESS";
            if(pdtProduct.getProductId() == 0){//新增
                msg = isExistData(0,0);   //检测是否存在相同数据
                if(msg.equals("SUCCESS")){
                    int userId = this.getLoginInfo().getUserId();
                    pdtProduct.setCreateUser(userId);
                    pdtProduct.setUpdateUser(userId);
                    if(!StringUtil.isBlank(pdtProduct.getRemark())){
                        pdtProduct.setRemark(pdtProduct.getRemark().replaceAll("<", "&lt;").replaceAll(">", "&gt;"));
                    }
                    pdtProductService.addPdtProduct(pdtProduct);
                }
            }else{
                PdtProduct item = pdtProductService.getPdtProductById(pdtProduct.getProductId());
                msg = isExistData(pdtProduct.getProductId(),1);   //检测是否存在相同数据
                if(msg.equals("SUCCESS")){
                    //item.setProductCode(pdtProduct.getProductCode());
                    item.setProductName(pdtProduct.getProductName());
                    item.setProductCoverImage(pdtProduct.getProductCoverImage());
                    item.setFeature(pdtProduct.getFeature());
                    item.setApplyNeedData(pdtProduct.getApplyNeedData());
                    if(!StringUtil.isBlank(pdtProduct.getRemark())){
                        item.setRemark(pdtProduct.getRemark().replaceAll("<", "&lt;").replaceAll(">", "&gt;"));
                    }else{
                        item.setRemark("");
                    }
                    item.setSuitableUser(pdtProduct.getSuitableUser());
                    item.setUpdateUser(this.getLoginInfo().getUserId());
                    pdtProductService.editPdtProduct(item);
                }
            }
            out.write(msg);
        } catch (Exception ex) {
            out.write("保存失败");
            log.error(ex);
        }
    }

    private String isExistData(int productId,int flag) {
        String msg = "SUCCESS";
        if(flag == 0){
            Map<String, Object> conds = new HashMap<String, Object>();
            conds.put("productName", pdtProduct.getProductName());
            conds.put("unProductId", productId);
            List<PdtProduct> listName = pdtProductService.getAllPdtProductByConds(conds);
            if (listName.size() > 0) {
                msg = "名称为\"" + pdtProduct.getProductName() + "\"的产品已存在";
            } else {
                //            conds.clear();
                //            //conds.put("productCode", pdtProduct.getProductCode());
                //            conds.put("unProductId", productId);
                //            List<PdtProduct> listCode = pdtProductService.getAllPdtProductByConds(conds);
                //            if (listCode.size() > 0) {
                //                msg = "编号为\"" + pdtProduct.getProductCode() + "\"的产品已存在";
                //            }
            }
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("productCode", pdtProduct.getProductCode());
            param.put("unProductId", productId);
            List<PdtProduct> listCode = pdtProductService.getAllPdtProductByConds(param);
            if (listCode.size() > 0) {
                msg = "代码为\"" + pdtProduct.getProductCode() + "\"的产品已存在";
            }
        }else{
            Map<String, Object> conds = new HashMap<String, Object>();
            conds.put("productName", pdtProduct.getProductName());
            conds.put("unProductId", productId);
            List<PdtProduct> listName = pdtProductService.getAllPdtProductByConds(conds);
            if (listName.size() > 0) {
                msg = "名称为\"" + pdtProduct.getProductName() + "\"的产品已存在";
            }
        }
        return msg;

    }

    public void upLoadLogoFile(){
        String type = FileType.getFileByFile(logoImage);
        Map<String, String> map = new HashMap<String, String>();
        try{
            HttpServletResponse response = ServletActionContext.getResponse();
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html;charset=UTF-8");

            PrintWriter out = response.getWriter();
            if (type==null||(!type.toLowerCase().equals("jpg")
                    && !type.toLowerCase().equals("gif")
                    && !type.toLowerCase().equals("bmp")
                    && !type.toLowerCase().equals("png")
                    && !type.toLowerCase().equals("jpeg"))) {
                map.put("error", "目前只支持bmp、jpg、gif、jpeg、png图像文件格式上传！");
                out.append(JSONObject.fromObject(map).toString());
                return;
            }
            String now = DateUtil.convertDateToString("yyyyMMdd", new Date());
            String savePath = sysParamService.getRecordPath() + File.separator + now;

            File file = new File(savePath);
            if (!file.exists()) {// 文件不存在则创建
                file.mkdirs();
            }

            // 生成guid 文件名
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            String newFileName = uuid + "." + type;

            // 将文件上传到服务器
            File imageFile = new File(savePath + File.separator + newFileName);
            copy(logoImage, imageFile);

            // 得到图片长宽

            BufferedImage buff = ImageIO.read(logoImage);
            map.put("width", buff.getWidth() + "");
            map.put("height", buff.getHeight() + "");

            // 返回成功信息
            map.put("fname", newFileName);
            map.put("folder", now);
            map.put("fsize", logoImage.length() + "");
            map.put("fullPath", savePath + File.separator + newFileName);
            out.append(JSONObject.fromObject(map).toString());
        }catch (Exception e) {
            try{
                HttpServletResponse response = ServletActionContext.getResponse();
                response.setCharacterEncoding("UTF-8");
                PrintWriter out = response.getWriter();
                map.put("error", "上传失败");
                out.append(JSONObject.fromObject(map).toString());
            }catch (IOException e1) {
                log.error(e1);
            }
        }
    }
    //复制文件
    private void copy(File src, File target) {
        InputStream in = null;
        OutputStream out = null;
        try {
            in = new BufferedInputStream(new FileInputStream(src), BUFFERED_SIZE);
            out = new BufferedOutputStream(new FileOutputStream(target), BUFFERED_SIZE);
            byte[] bs = new byte[BUFFERED_SIZE];
            int i;
            while ((i = in.read(bs)) > 0) {
                out.write(bs, 0, i);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null)
                    in.close();
                if (out != null)
                    out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void isDelProduct(){
        Map<String, String> map = new HashMap<String, String>();
        try{
            HttpServletResponse response = ServletActionContext.getResponse();
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html;charset=UTF-8");

            PrintWriter out = response.getWriter();
            String productId = request.getParameter("productId");
            PdtProduct pdtProduct = pdtProductService.getPdtProductById(Integer.parseInt(productId.trim()));
            if(pdtProduct.getIsDel() == 1){
                map.put("error", "您查看的产品已删除");
                out.append(JSONObject.fromObject(map).toString());
                return;
            }
            map.put("result", "SUCCESS");
            out.append(JSONObject.fromObject(map).toString());
        }catch (Exception e){
            e.printStackTrace();
        }



    }

}
