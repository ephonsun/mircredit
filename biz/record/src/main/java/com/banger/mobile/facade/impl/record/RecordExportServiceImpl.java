/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :录音导出...
 * Author     :cheny
 * Create Date:2012-9-11
 */
package com.banger.mobile.facade.impl.record;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;


import com.banger.mobile.constants.FileUploadPathConstants;
import com.banger.mobile.dao.user.SysUserDao;
import com.banger.mobile.domain.model.customer.CustomerExportBar;
import com.banger.mobile.domain.model.customer.CustomerExportContext;
import com.banger.mobile.domain.model.record.RecordExportBean;
import com.banger.mobile.domain.model.templateField.CrmTemplateField;
import com.banger.mobile.domain.model.user.IUserInfo;
import com.banger.mobile.facade.record.RecordExportService;
import com.banger.mobile.facade.system.CdSystemService;
import com.banger.mobile.facade.template.CrmTemplateService;
import com.banger.mobile.util.DateUtil;
import com.banger.mobile.util.ReflectorUtil;
import com.banger.mobile.util.TypeUtil;

/**
 * @author cheny
 * @version $Id: RecordExportServiceImpl.java,v 0.1 2012-9-11 下午12:51:43 cheny Exp $
 */
public class RecordExportServiceImpl implements RecordExportService{
    
    private  SysUserDao sysUserDao;                   //用户
    private CrmTemplateService crmTemplateService;    //模板
    private CdSystemService codetableService ;        //省份 城市 

    
    private  SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    
    public void setCrmTemplateService(CrmTemplateService crmTemplateService) {
        this.crmTemplateService = crmTemplateService;
    }

    public void setSysUserDao(SysUserDao sysUserDao) {
        this.sysUserDao = sysUserDao;
    }

    public void setCodetableService(CdSystemService codetableService) {
        this.codetableService = codetableService;
    }

    private static Map<Integer,CustomerExportBar> customerExportBar;
    
    /**
     * 获得导出进度条
     * @return
     */
    public static Map<Integer, CustomerExportBar> getCustomerExportBar() {
        if(customerExportBar == null) customerExportBar = new HashMap<Integer,CustomerExportBar>();
        return customerExportBar;
    }
    
    /**
     * 获得登录信息
     * @return
     */
    private IUserInfo getUserLoginInfo(){
        HttpServletRequest req = org.apache.struts2.ServletActionContext.getRequest();
        HttpSession session = req.getSession();
        return (IUserInfo)session.getAttribute("LoginInfo");
    }
    
    /**
     * 创建excel表头
     * @param excelSheet
     * @param data
     * @param type
     */
    protected void createHeader(CustomerExportContext ctx,HSSFSheet excelSheet,int type){ 
        ctx.setSheetRows(0);
        List<String> head = getHeadExcel(ctx,type);//表头
        HSSFRow newRow = excelSheet.createRow(0);   
        //循环导出列            
        for(int j=0;j<head.size();j++){               
            HSSFCell newCell = newRow.createCell((short)j);                
            newCell.setCellValue(head.get(j));                
        }        
   }
    /**
     * 获得excel sheet索引
     * @param ctx
     * @param book
     * @return
     */
    private HSSFSheet getSheetIndex(CustomerExportContext ctx,HSSFWorkbook book,int type){
        int n = book.getNumberOfSheets();
        if(n==0){
            HSSFSheet sheet = book.createSheet("Sheet" + 1);
            createHeader(ctx,sheet,type);
            return sheet;
        }
        else
        {
            if(ctx.getSheetRows()<ctx.getSheetMaxRows())
            {
                HSSFSheet sheet = book.getSheetAt(n-1);
                return sheet;
            }
            else
            {
                HSSFSheet sheet = book.createSheet("Sheet" + (n+1));
                createHeader(ctx,sheet,type);
                return sheet;
            }
        }
    }
    /**
     * 插入数据行
     * @param excelWorkbook
     * @param data
     * @param customers
     * @param type
     */
    public void insertRow(CustomerExportContext ctx,HSSFWorkbook book,int type,List<RecordExportBean> customers){ 
      //循环数据源导出数据集         
        HSSFSheet newsheet = null;
        for (RecordExportBean dr : customers) {
            newsheet = getSheetIndex(ctx,book,type);
            ctx.setRecordCount(ctx.getRecordCount()+1);
            ctx.setSheetRows(ctx.getSheetRows()+1);
            HSSFRow newRow = newsheet.createRow(ctx.getSheetRows());
            Integer userId = getUserLoginInfo().getUserId();
          
            if(customerExportBar.containsKey(userId)){
                CustomerExportBar bar = customerExportBar.get(userId);
                if(bar.getIsRun().equals(1) && bar.getIsStop().equals(0)){
                    bar.setCuurRow(ctx.getRecordCount()+(ctx.getBatchCount()-1)*ctx.getPageSize());  //当前执行的行数
                }else{
                    bar.setCuurRow(0);
                    break;
                }
            }
            insertCell(dr,newRow,ctx,type);    
         } 
     
     }
    
    /**
     * 插入单元格数据     
     * @param customer
     * @param newRow
     * @param data
     * @param type
     */
    protected void insertCell(RecordExportBean customer,HSSFRow newRow,CustomerExportContext ctx,int type){
        List<String> cellContents =  getContentExcel(ctx,customer,type);
        for (int i=0;i<cellContents.size();i++) {
            HSSFCell cell = newRow.createCell((short)i);
            cell.setCellValue(cellContents.get(i));
        } 
    }
    
    /**
     * 构造表头
     * @param data
     * @param type
     * @return
     */
    public List<String> getHeadExcel(CustomerExportContext ctx,int type){
        Map<String,String> baseColumn = ctx.getParams();//初始化基础字段
        Map<String,CrmTemplateField> feildMap = ctx.getFields(); //所有的模板字段
        Map<String,Object> headMap = new LinkedHashMap<String,Object>();
        List<String> head = new ArrayList<String>();
        List<String> titleHead = new ArrayList<String>();
        Map<String,String> map = new LinkedHashMap<String, String>();
        
        //列表中字段默认导出
        titleHead.add("客户姓名（电话）");
        
        if(type == 0){//所有记录
            titleHead.add("联系类型");
            titleHead.add("联系时间");
            titleHead.add("录音时长");
            titleHead.add("业务类型");
            titleHead.add("备注");
            titleHead.add("操作者");
        }else if(type == 1){//通话记录
            titleHead.add("通话类型");
            titleHead.add("开始时间");
            titleHead.add("时长");
            titleHead.add("业务类型");
            titleHead.add("记录来源");
            titleHead.add("状态");
            titleHead.add("操作者");
        }else if(type == 2){//座谈记录
            titleHead.add("座谈类型");
            titleHead.add("开始时间");
            titleHead.add("时长");
            titleHead.add("业务类型");
            titleHead.add("记录来源");
            titleHead.add("状态");
            titleHead.add("操作者");
        }else if(type == 3){//拜访记录
            titleHead.add("拜访时间");
            titleHead.add("业务类型");
            titleHead.add("备注");
            titleHead.add("操作者");
        }else if(type == 4){//短信记录
            titleHead.add("短信类型");
            titleHead.add("内容");
            titleHead.add("发送/接收时间");
            titleHead.add("拆分条数");
            titleHead.add("状态");
            titleHead.add("操作者");
        }else if(type == 5) {//彩信记录
            titleHead.add("彩信标题");
            titleHead.add("发送时间");
            titleHead.add("状态");
            titleHead.add("操作者");
        }
        //客户字段
        String[] codes = ctx.getData().split(":");
        for (String code : codes) {
            if(!code.contains(","))  {//基础字段
                if(code.equals("customerBelong")){
                    titleHead.add("用户名");
                    titleHead.add("用户姓名");
                    titleHead.add("归属机构编号");
                    titleHead.add("归属机构名称");
                }else titleHead.add(baseColumn.get(code));
            }
            else{
                String[] cd = code.split(",");
                int n = Integer.valueOf(cd[0]);
                if(n<1) {//自定义字段
                    map.put(cd[2]+","+cd[3],cd[1]);
                }else{//扩展字段
                    headReNew(titleHead,cd[2],cd[1],feildMap);
                    
                }
            }
        }
        //处理自定义字段重复
        List<String> extName=new ArrayList<String>();//字段名集合(去除重复)
        Map<String,String> hmap = new LinkedHashMap<String,String>();
        for(String wd : map.keySet()){
            String[] wds = wd.split(",");
            if(extName.contains(wds[0])){//有重名
                if(head.lastIndexOf(wds[0]) != -1){
                    String fk = (String)headMap.get(wds[0]);
                    headMap.remove(wds[0]);
                    headMap.put(map.get(wds[0]+","+hmap.get(wds[0]))+"_"+wds[0], fk);
                    head.set(head.lastIndexOf(wds[0]),map.get(wds[0]+","+hmap.get(wds[0]))+"_"+wds[0]);
                }
                head.add(map.get(wd)+"_"+wds[0]);
                headMap.put(map.get(wd)+"_"+wds[0], wds[1]);
            }else{//没重名
                hmap.put(wds[0], wds[1]);//记录字段名和属性
                extName.add(wds[0]);
                head.add(wds[0]);
                headMap.put(wds[0], wds[1]);
            }
        }
        //自定义字段表头加上单位
        for(String sk : head){
            for(String key : headMap.keySet()){
                if(key.equals(sk)){
                    String feildKey = (String)headMap.get(key);//字段字段属性
                    for(String fm : feildMap.keySet()){
                        if(fm.equalsIgnoreCase(feildKey)){
                            String per = feildMap.get(fm).getMeasurement();//单位
                            if(per!=null && !per.equals("")){
                                titleHead.add(key+"("+per+")");
                                continue;
                            }else{
                                titleHead.add(key); 
                                continue;
                            }
                        }
                    }
                    continue;
                }
            }
        }
        return titleHead;
    }

    /**
     * 构造表格数据
     * @param data
     * @param crmCustomer
     * @param type
     * @return
     */
    public List<String> getContentExcel(CustomerExportContext ctx,RecordExportBean crmCustomer,int type){
        String content="";
        Integer calltime;
        List<String> contents = new ArrayList<String>();
        Map<String,CrmTemplateField> feildMap = ctx.getFields(); //所有的模板字段
        Map<Integer,String> userNameMap = ctx.getUserNames(); //所有的用户名
        
        //联系记录列表字段
        content = (String)ReflectorUtil.getPropertyValue(crmCustomer, "customerName");
        if(content != null && !content.equals("")){
            String name = content;
            content = (String)ReflectorUtil.getPropertyValue(crmCustomer, "defaultPhone");
            if(content != null && !content.equals(""))  contents.add(name+"("+content+")");
            else contents.add(name);
        }else{
            content = (String)ReflectorUtil.getPropertyValue(crmCustomer, "defaultPhone");
            if(content != null && !content.equals(""))  contents.add(content);
            else contents.add("未知");
        }
        
        if(type == 0){//所有记录
            content = (String)ReflectorUtil.getPropertyValue(crmCustomer, "callTypeName");
            if(content != null)  contents.add(content);
            else contents.add("");
            Date date =(Date)ReflectorUtil.getPropertyValue(crmCustomer, "startDate");
            if(date != null)  contents.add(df.format(date));
            else contents.add("");
            calltime = (Integer)ReflectorUtil.getPropertyValue(crmCustomer, "callTime");
            if(calltime != null) contents.add(changTime(calltime));
            else contents.add("");
            content = (String)ReflectorUtil.getPropertyValue(crmCustomer, "bizTypeName");
            if(content != null)  contents.add(content);
            else contents.add("");
            content = (String)ReflectorUtil.getPropertyValue(crmCustomer, "remark");
            if(content != null)  contents.add(content);
            else contents.add("");
            content = (String)ReflectorUtil.getPropertyValue(crmCustomer, "userName");
            if(content != null)  contents.add(content);
            else contents.add("");
            
        }else if(type == 1){//通话记录
            content = (String)ReflectorUtil.getPropertyValue(crmCustomer, "callTypeName");
            if(content != null)  contents.add(content);
            else contents.add("");
            Date date =(Date)ReflectorUtil.getPropertyValue(crmCustomer, "startDate");
            if(date != null)  contents.add(df.format(date));
            else contents.add("");
            calltime = (Integer)ReflectorUtil.getPropertyValue(crmCustomer, "callTime");
            if(calltime != null) contents.add(changTime(calltime));
            else contents.add("");
            content = (String)ReflectorUtil.getPropertyValue(crmCustomer, "bizTypeName");
            if(content != null)  contents.add(content);
            else contents.add("");
            content = (String)ReflectorUtil.getPropertyValue(crmCustomer, "recordSourceName");
            if(content != null)  contents.add(content);
            else contents.add("");
            content = (String)ReflectorUtil.getPropertyValue(crmCustomer, "isCanceledName");
            if(content != null)  contents.add(content);
            else contents.add("");
            content = (String)ReflectorUtil.getPropertyValue(crmCustomer, "userName");
            if(content != null)  contents.add(content);
            else contents.add("");
            
        }else if(type == 2){//座谈记录
            content = (String)ReflectorUtil.getPropertyValue(crmCustomer, "callTypeName");
            if(content != null)  contents.add(content);
            else contents.add("");
            Date date =(Date)ReflectorUtil.getPropertyValue(crmCustomer, "startDate");
            if(date != null)  contents.add(df.format(date));
            else contents.add("");
            calltime = (Integer)ReflectorUtil.getPropertyValue(crmCustomer, "callTime");
            if(calltime != null) contents.add(changTime(calltime));
            else contents.add("");
            content = (String)ReflectorUtil.getPropertyValue(crmCustomer, "bizTypeName");
            if(content != null)  contents.add(content);
            else contents.add("");
            content = (String)ReflectorUtil.getPropertyValue(crmCustomer, "recordSourceName");
            if(content != null)  contents.add(content);
            else contents.add("");
            content = (String)ReflectorUtil.getPropertyValue(crmCustomer, "isCanceledName");
            if(content != null)  contents.add(content);
            else contents.add("");
            content = (String)ReflectorUtil.getPropertyValue(crmCustomer, "userName");
            if(content != null)  contents.add(content);
            else contents.add("");
            
        }else if(type == 3){//拜访记录
            Date date =(Date)ReflectorUtil.getPropertyValue(crmCustomer, "startDate");
            if(date != null)  contents.add(df.format(date));
            else contents.add("");
            content = (String)ReflectorUtil.getPropertyValue(crmCustomer, "bizTypeName");
            if(content != null)  contents.add(content);
            else contents.add("");
            content = (String)ReflectorUtil.getPropertyValue(crmCustomer, "remark");
            if(content != null)  contents.add(content);
            else contents.add("");
            content = (String)ReflectorUtil.getPropertyValue(crmCustomer, "userName");
            if(content != null)  contents.add(content);
            else contents.add("");
            
        }else if(type == 4){//短信记录
            content = (String)ReflectorUtil.getPropertyValue(crmCustomer, "callTypeName");
            if(content != null)  contents.add(content);
            else contents.add("");
            content = (String)ReflectorUtil.getPropertyValue(crmCustomer, "content");
            if(content != null)  contents.add(content);
            else contents.add("");
            Date date =(Date)ReflectorUtil.getPropertyValue(crmCustomer, "startDate");
            if(date != null)  contents.add(df.format(date));
            else contents.add("");
            Integer count = (Integer)ReflectorUtil.getPropertyValue(crmCustomer, "splitCount");
            if(count != null)  contents.add(count.toString());
            else contents.add("");
            content = (String)ReflectorUtil.getPropertyValue(crmCustomer, "status");
            if(content != null)  contents.add(content);
            else contents.add("");
            content = (String)ReflectorUtil.getPropertyValue(crmCustomer, "userName");
            if(content != null)  contents.add(content);
            else contents.add("");
            
        }else if(type == 5){//彩信记录
            content = (String)ReflectorUtil.getPropertyValue(crmCustomer, "mmsTitle");
            if(content != null)  contents.add(content);
            else contents.add("");
            Date date =(Date)ReflectorUtil.getPropertyValue(crmCustomer, "startDate");
            if(date != null)  contents.add(df.format(date));
            else contents.add("");
            content = (String)ReflectorUtil.getPropertyValue(crmCustomer, "status");
            if(content != null)  contents.add(content);
            else contents.add("");
            content = (String)ReflectorUtil.getPropertyValue(crmCustomer, "userName");
            if(content != null)  contents.add(content);
            else contents.add("");
        }
        //客户字段
        String[] codes = ctx.getData().split(":");
        for (String code : codes) {
            if(!code.contains(","))  {//基础字段
                if(code.equals("customerBelong")){
                    content = (String)ReflectorUtil.getPropertyValue(crmCustomer, "belongUserAccount");
                    if(content != null) contents.add(content);
                    else contents.add("");
                    content = (String)ReflectorUtil.getPropertyValue(crmCustomer, "belongUserName");
                    if(content != null) contents.add(content);
                    else contents.add("");
                    content = (String)ReflectorUtil.getPropertyValue(crmCustomer, "belongDeptCode");
                    if(content != null) contents.add(content);
                    else contents.add("");
                    content = (String)ReflectorUtil.getPropertyValue(crmCustomer, "belongDeptName");
                    if(content != null) contents.add(content);
                    else contents.add("");
                }
                
                /*********导出年龄开始**********/
                else if(code.equals("age")){
                    String birthday = null;
                    String year = DateUtil.getDateToString(new Date()).substring(0,4);
                    Date date =(Date)ReflectorUtil.getPropertyValue(crmCustomer, "birthday");
                    if(date != null) birthday= DateUtil.getDateToString(date);
                    int age =0;
                    if(birthday != null){//有生日
                        String birthyear =birthday.substring(0,4);//出生年份
                        age = Integer.valueOf(year)-Integer.valueOf(birthyear);
                        contents.add(age+"");
                    }else{
                        contents.add("");
                    }
                /*********导出年龄结束**********/ 
                }else if(code.equals("province")){//省份
                    String pro = (String)ReflectorUtil.getPropertyValue(crmCustomer, code);
                    if(pro != null && !pro.equals("")){
                        contents.add( codetableService.getProvinceByCode(pro).getShortName());
                    }else contents.add("");
                }else if(code.equals("city")){//城市
                    String city = (String)ReflectorUtil.getPropertyValue(crmCustomer, code);
                    if(city != null && !city.equals("")){
                        contents.add( codetableService.getCityByCode(city).getShortName());
                    }else contents.add("");    
                }else if("birthday,lastContactDate,custCreateDate,custUpdateDate".contains(code)){//Date类型
                    Date date =(Date)ReflectorUtil.getPropertyValue(crmCustomer, code);
                    if(date != null){
                        contents.add(DateUtil.getDateToString(date));
                    }else contents.add("");
                }else if(code.equals("isReceiveSms")){//是否愿意接收短信
                    Integer i = (Integer)ReflectorUtil.getPropertyValue(crmCustomer, "isReceiveSms");
                    if(i != null){
                        if(i.equals(1))contents.add("是");
                        else contents.add("否");
                    }else contents.add("是");
                }else if("custCreateUser,custUpdateUser".contains(code)){//新建者、修改者
                    Integer id = (Integer)ReflectorUtil.getPropertyValue(crmCustomer, code);
                    if(id != null) {
                        if(userNameMap.containsKey(id)) contents.add(userNameMap.get(id));
                        else contents.add("");
                    }
                    else contents.add("");    
                }else{
                    content = (String)ReflectorUtil.getPropertyValue(crmCustomer, code);
                    if(content != null) contents.add(content);
                    else contents.add("");
                }
            }else{
                String[] cd = code.split(",");
                int n = Integer.valueOf(cd[0]);
                if(n<1) {//自定义字段
                    changeType(contents,cd[3],crmCustomer,feildMap);
                }else{//扩展字段
                    changeType(contents,cd[2],crmCustomer,feildMap);
                }
            }
        }
        return contents;
    }
    
    /**自定义字段类型转换
     * 
     * @param contents 单元格内容集合
     * @param wd 单元格属性值
     * @param crmCustomer 反射对象
     * @param feildMap 模板字段
     */
    public void changeType(List<String> contents,String wd,RecordExportBean crmCustomer,Map<String,CrmTemplateField> feildMap){
        for(String feildName : feildMap.keySet()){
            if(feildName.equalsIgnoreCase(wd)){
                CrmTemplateField feild = feildMap.get(feildName);
                String feildType = feild.getTemplateFieldType();
                if("MultipleSelect,Select,Text,TextArea".contains(feildType)){
                    String tt = (String)ReflectorUtil.getPropertyValue(crmCustomer, wd);
                    if(tt!=null && !tt.equals("")){
                        contents.add(tt);
                    }else contents.add("");
                }else if(feildType.equals("YesNo")){
                    String tt = (String)ReflectorUtil.getPropertyValue(crmCustomer, wd);
                    if(tt!=null && !tt.equals("")){
                        if(tt.equalsIgnoreCase("yes")) contents.add("是");
                        else contents.add("否");
                    }else contents.add("否");
                }else if(feildType.equals("Date")){
                    Date date = (Date)ReflectorUtil.getPropertyValue(crmCustomer, wd);
                    if(date != null){
                        contents.add(DateUtil.getDateToString(date));
                    }else contents.add("");
                }else if(feildType.equals("Number")){
                   Double d = (Double)ReflectorUtil.getPropertyValue(crmCustomer, wd);
                   if(d != null){
                       String ss = (String)TypeUtil.changeType(d,String.class);
                       if(ss.endsWith(".0")) contents.add(ss.substring(0,ss.length()-2));
                       else contents.add(ss);
                   }else contents.add("");
                }else contents.add("");
            }
        }
    }
    
    
    /**表头改造加单位
     * 
     * @param head 表头集合
     * @param feildName  字段属性
     * @param tempFileName 模板字段名
     *  @param feildMap  模板字段
     */
    public void headReNew(List<String> head,String feildName,String tempFileName,Map<String,CrmTemplateField> fmap){
        for(String key : fmap.keySet()){
            if(key.equalsIgnoreCase(feildName)){
                String per = fmap.get(key).getMeasurement();//单位
                if(per!=null && !per.equals("")){
                    head.add(tempFileName+"("+per+")");
                }else{
                    head.add(tempFileName); 
                }
            }
        }
    }
    /**
     * 导出基础字段
     */
     public Map<String, String> initParameter(){
         Map<String, String> map = new LinkedHashMap<String, String>();
         map.put("customerBelong", "客户归属");
         map.put("customerName", "客户姓名");
         map.put("sex", "性别");
         map.put("customerTitle", "称谓");
         map.put("customerNo", "客户编号");
         map.put("customerTypeName", "客户类型");
         map.put("customerIndustryName", "所处行业");
         map.put("custIdCard", "身份证");
         map.put("birthday", "生日");
         map.put("company", "单位");
         map.put("age", "年龄");
         map.put("custRemark", "客户简介");
         map.put("province", "省份");
         map.put("city", "城市");
         map.put("address", "联系地址");
         map.put("mobilePhone1", "手机1");
         map.put("mobilePhone1Remark", "手机1备注");
         map.put("mobilePhone2", "手机2");
         map.put("mobilePhone2Remark", "手机2备注");
         map.put("phone", "固话");
         map.put("phoneExt", "固话分机");
         map.put("fax", "传真");
         map.put("faxExt", "传真分机");
         map.put("email", "邮箱");
         map.put("lastContactDate", "最后联系时间");
//         map.put("isReceiveSms", "是否愿意接收短信");
         map.put("custCreateUser", "新建者");
         map.put("custCreateDate", "新建时间");
         map.put("custUpdateUser", "最后修改者");
         map.put("custUpdateDate", "修改时间");
         return map;
     }
    /**
     * 创建文件
     */
    public File createFile(HttpServletRequest request){
        try {
            String path = FileUploadPathConstants.REC_EXPORT_PATH;
            // 服务器绝对路径
            path = request.getRealPath("/") + path;
            // 检查文件夹是否存在
            File obj = new File(path);
            if (!obj.exists()) {
                obj.mkdirs();
            }
            File file = new File(request.getRealPath("/")+FileUploadPathConstants.REC_EXPORT_PATH+File.separator+"record_export"+this.getUserLoginInfo().getUserId()+".xls");
            if(! file.exists()){
               file.createNewFile();
            }
            return file;
        } catch (IOException e) {
            return null;
        }
    }
    
    /**
     * 时长转换成HH:mm:ss
     * @param str
     * @return
     */
    public String changTime(Integer l){
        String H =""+l/3600;
        if(H.length()<2)H="0"+H;
        l = l%3600;
        String M = ""+l/60;
        if(M.length()<2)M="0"+M;
        l = l%60;
        String S = ""+l;
        if(S.length()<2)S="0"+S;
        return H+":"+M+":"+S;
    }
}
