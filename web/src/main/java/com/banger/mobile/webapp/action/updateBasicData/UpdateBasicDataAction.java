/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :更新业务数据
 * Author     :yujh
 * Create Date:Sep 13, 2012
 */
package com.banger.mobile.webapp.action.updateBasicData;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;

import com.banger.mobile.domain.model.customer.CustomerExportBar;
import com.banger.mobile.domain.model.user.IUserInfo;
import com.banger.mobile.webapp.action.BaseAction;

/**
 * @author yujh
 * @version $Id: UpdateBasicDataAction.java,v 0.1 Sep 13, 2012 1:40:40 PM Administrator Exp $
 */
public class UpdateBasicDataAction extends BaseAction {

    private static final long     serialVersionUID = 3039084818065243100L;

    private File                  file;
    private int                   Bar;
    private static UpdateUtilList updateUtilList   = new UpdateUtilList();
    List<String>                  subFileNameList  = new ArrayList<String>();
    private static Map<Integer, CustomerExportBar> customerExportBar;//进度条

    public static Map<Integer, CustomerExportBar> getCustomerExportBar() {
        if (customerExportBar == null)
            customerExportBar = new HashMap<Integer, CustomerExportBar>();
        return customerExportBar;
    }

    public List<String> getSubFileNameList() {
        return subFileNameList;
    }

    public void setSubFileNameList(List<String> subFileNameList) {
        this.subFileNameList = subFileNameList;
    }

    /**
     * 初始化页面
     * @return
     */
    public String showUpdateBasicDataPage() {
        return SUCCESS;
    }

    /**
     * 上传并解压文件
     * @return
     */
    public List<String> uploadBasicData() {
        //   List<String> subFileNameList=new ArrayList<String>();
        try {
            int count;
            File target = new File(request.getSession().getServletContext().getRealPath(
                "/basicData/"));
            if (!target.exists()) {
                target.mkdirs();
            }
            String fileName = request.getSession().getServletContext().getRealPath(
                "/basicData/" + file.getName());
            FileUtils.copyFile(file, new File(fileName));
            ZipFile zf = new ZipFile(new File(fileName)); //创建zipfile对象
            Enumeration<?> e = zf.getEntries();
            ZipEntry ze = null;
            while (e.hasMoreElements()) {
                ze = (ZipEntry) e.nextElement();
                if (!ze.isDirectory()) {
                    byte buf[] = new byte[1024];
                    String subFileName = ze.getName();
                    int index = subFileName.lastIndexOf("//");
                    if (index > -1) {
                        subFileName = subFileName.substring(index + 1);
                    }
                    subFileName = request.getSession().getServletContext().getRealPath(
                        "/basicData/")
                                  + File.separator + subFileName;
                    subFileNameList.add(subFileName);
                    File subFile = new File(subFileName);
                    OutputStream outputStream = new FileOutputStream(subFile);
                    InputStream inputStream = zf.getInputStream(ze);
                    while ((count = inputStream.read(buf)) > -1) {
                        outputStream.write(buf, 0, count);
                    }
                    outputStream.close();
                    inputStream.close();
                }
            }
            zf.close();
            updateUtilList.setFileNameList(subFileNameList);
            return subFileNameList;//返回解压后的文件名称list
        } catch (IOException e) {
            log.error("uploadBasicData action error", e);
            return null;
        }

    }

    /**
     * 读取sql文件，返回sql语句
     * @param sqlFile
     * @return
     * @throws Exception
     */
    private List<String> loadSql(String sqlFile) throws Exception {
        List<String> sqlList = new ArrayList<String>();
        try {
            InputStream sqlFileIn = new FileInputStream(sqlFile);
            StringBuffer sqlSb = new StringBuffer();
            byte[] buff = new byte[1024];
            int byteRead = 0;
            while ((byteRead = sqlFileIn.read(buff)) != -1) {
                sqlSb.append(new String(buff, 0, byteRead));
            }
            // Windows 下换行是 \r\n, Linux 下是 \n   
            String[] sqlArr = sqlSb.toString().split("(;\\s*\\r\\n)|(;\\s*\\n)");
            String a = "TLK_MOBILE_ATTIBUTION";//电话所在地
            String b = "TLK_TELEPHONE_CODE"; //电话区号对应表 ，区域地址
            String c = "CRM_FAMILY_NAME"; //百家姓
            String d = "SYS_WHITE_LIST"; //特殊号码 白名单 SYS_WHITE_LIST
            for (int i = 0; i < sqlArr.length; i++) {
                String sql = sqlArr[i].replaceAll("--.*", "").trim();
                if (!sql.equals("")) {
                    if (sql.contains(a) || sql.contains(b) || sql.contains(c) || sql.contains(d)) {
                        sqlList.add(sql);
                    }
                }
            }
            return sqlList;
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

    /**
     * 连接数据库执行sql语句
     * @param sqlFile
     * @throws Exception
     */
    public String sqlToDB(String sqlFile) throws Exception {
        Map<String,String> map=this.loadConnSource();
        String driverClassName=map.get("driverClassName"); 
        String url=map.get("url");                         
        String username=map.get("username");
        String password=map.get("password");
        Class.forName(driverClassName).newInstance();
        Connection con = java.sql.DriverManager.getConnection(
            url, username, password);
        Statement stmt = null;
        List<String> sqlList = loadSql(sqlFile);
        int aa = 0;
        int bb = 0;
        int cc = 0;
        int dd = 0;
        String a = "TLK_MOBILE_ATTIBUTION";//电话所在地
        String b = "TLK_TELEPHONE_CODE"; //电话区号对应表 ，区域地址
        String c = "CRM_FAMILY_NAME"; //百家姓
        String d = "SYS_WHITE_LIST"; //特殊号码 白名单 SYS_WHITE_LIST
        try {
            con.setAutoCommit(false);
            stmt = con.createStatement();
            updateUtilList.setCount(0);
            if(sqlList==null || sqlList.size()==0){
                updateUtilList.setA(aa);
                updateUtilList.setB(bb);
                updateUtilList.setC(cc);
                updateUtilList.setD(dd);
                return SUCCESS;
            }  
            for (int i = 1; i <= sqlList.size(); i++) {
                stmt.addBatch(sqlList.get(i - 1));
                if (sqlList.get(i - 1).contains(a)) {
                    aa += 1;
                } else if (sqlList.get(i - 1).contains(b)) {
                    bb += 1;
                } else if (sqlList.get(i - 1).contains(c)) {
                    cc += 1;
                } else {
                    dd += 1;
                }
                
                CustomerExportBar bar = customerExportBar.get(getUserLoginInfo()
                    .getUserId());
                bar.setCuurRow(i); //当前执行的行数
                customerExportBar.put(getLoginInfo().getUserId(), bar);
                
                updateUtilList.setA(aa);
                updateUtilList.setB(bb);
                updateUtilList.setC(cc);
                updateUtilList.setD(dd);
                Thread.sleep(100);
            }
             stmt.executeBatch();
            //       System.out.println("Row count:" + Arrays.toString(rows));
            con.commit();
            //      Thread.sleep(2000);
            return SUCCESS;
        } catch (Exception e) {
            con.rollback();
            log.info("sqlToDB action error " + e.getMessage());
            return "back";
        } finally {
            con.close();
        }
    }

    /**
     * 入口
     */
    public String initExecute() {
        updateUtilList.setCount(0);
        
        Map<Integer, CustomerExportBar> customerExportBar = UpdateBasicDataAction
        .getCustomerExportBar();
        CustomerExportBar bar = new CustomerExportBar();
        bar.setIsRun(1);
        bar.setIsStop(0);
        customerExportBar.put(this.getLoginInfo().getUserId(), bar);
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/html;charset=utf-8");
        try {
            PrintWriter out = response.getWriter();
            for (int i = 0; i < updateUtilList.getFileNameList().size(); i++) {
                String res=sqlToDB(updateUtilList.getFileNameList().get(i));
                if(res.equals("back")){
                    out.print(res);
                }
            }
            out.close();
            bar.setIsRun(0);
            bar.setIsStop(1);
            return null;
        } catch (Exception e) {
            log.error("initExecute action error"+e.getMessage());
            return ERROR;
        }
    }

    /**
     * 总记录数
     * @return
     */
    public String calculateCount() {
        updateUtilList.setCount(0);
        List<String> sqlList = new ArrayList<String>();//总sql数
        List<String> subFileList = uploadBasicData();//得到zip中的文件名称
        boolean flag = false;
        if(subFileList != null){
	        for (int j = 0; j < subFileList.size(); j++) {
	            String fileName = subFileList.get(j);
	            String name = fileName.substring(fileName.lastIndexOf("."), fileName.length());
	            if(name.equals(".sql")){
	                flag = true; 
	                break;
	            }
	        }
	        if(flag== false) return "fail";
	        for (int j = 0; j < subFileList.size(); j++) {
	            try {
	                List<String> list = loadSql(subFileList.get(j));//取得sql文件中的sql语句集合
	                if(list==null ||list.size()==0){
	                    request.setAttribute("total", 0);
	                    return SUCCESS;
	                }  
	                for (String sql : list) {
	                    sqlList.add(sql);
	                }
	            } catch (Exception e) {
	                log.error("calculateCount action error" + e.getMessage());
	            }
	        }
	        request.setAttribute("total", sqlList.size());
        }
        return SUCCESS;
    }

    /**
     * 进度条
     */
    public String showProgress() {
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/html;charset=utf-8");
        try {
            PrintWriter out = response.getWriter();
            JSONObject jso = new JSONObject();
            Map<Integer, CustomerExportBar> customerExportBar=UpdateBasicDataAction.getCustomerExportBar();
            CustomerExportBar bar=customerExportBar.get(this.getLoginInfo().getUserId());
            if(bar!=null){
                jso.put("curr", bar.getCuurRow());
            }
            out.print(jso.toString());
            return null;
        } catch (Exception e) {
            log.error("importBar action error" + e.getMessage());
            return ERROR;
        }
    }

    /**
     * 结果页面
     * @return
     */
    public String showResult() {
        try {
           
            int a = updateUtilList.getA();
            int b = updateUtilList.getB();
            int c = updateUtilList.getC();
            int d = updateUtilList.getD();
            int succ = a+b+c+d;
            request.setAttribute("succ", succ);
            request.setAttribute("a", a);
            request.setAttribute("b", b);
            request.setAttribute("c", c);
            request.setAttribute("d", d);
            return SUCCESS;
        } catch (RuntimeException e) {
            log.error("showResult action error", e);
            return "fail";
        }
    }
   	/**
   	 * 显示失败结果页面
   	 * @return
   	 */
    public String showFailResult(){
        return SUCCESS;
    }

    public int getBar() {
        return Bar;
    }

    public void setBar(int bar) {
        Bar = bar;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public static UpdateUtilList getUpdateUtilList() {
        return updateUtilList;
    }

    public static void setUpdateUtilList(UpdateUtilList updateUtilList) {
        UpdateBasicDataAction.updateUtilList = updateUtilList;
    }
    private IUserInfo getUserLoginInfo() {
        HttpServletRequest req = org.apache.struts2.ServletActionContext.getRequest();
        HttpSession session = req.getSession();
        return (IUserInfo) session.getAttribute("LoginInfo");
    }
    /**
     * 加载数据库配置信息
     * @return
     */
    private Map<String,String> loadConnSource(){
        try {
            InputStream is=this.getClass().getClassLoader().getResourceAsStream("jdbc.properties");
            Properties p=new Properties();
            p.load(is);
            String driverClassName=p.getProperty("jdbc.driverClassName");
            String url=p.getProperty("jdbc.url");
            String username=p.getProperty("jdbc.username");
            String password=p.getProperty("jdbc.password");
            Map<String,String> map= new HashMap<String,String>();
            map.put("driverClassName", driverClassName);
            map.put("url", url);
            map.put("username", username);
            map.put("password", password);
            return map;
        } catch (Exception e) {
            log.error("loadResource action error"+e.getMessage());
            return null;
        }
    }
    public static void main(String [] args){
        UpdateBasicDataAction ubd= new UpdateBasicDataAction();
        Map map=ubd.loadConnSource();
        System.out.println(map.get("driverClassName"));
        System.out.println(map.get("url"));
        System.out.println(map.get("username"));
        System.out.println(map.get("password"));
    }

}
