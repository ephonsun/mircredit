/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :sql过滤...
 * Author     :Administrator
 * Create Date:Sep 13, 2012
 */
package com.banger.mobile.webapp.action.updateBasicData;

/**
 * @author Administrator
 * @version $Id: SqlFileExecutor.java,v 0.1 Sep 13, 2012 2:07:39 PM Administrator Exp $
 */
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;

/**  
 * 读取 SQL 脚本并执行  
 * @author Unmi  
 */
public class SqlFileExecutor {
    /**  
     * 读取 SQL 文件，获取 SQL 语句  
     * @param sqlFile SQL 脚本文件  
     * @return List<sql> 返回所有 SQL 语句的 List  
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
            for (int i = 0; i < sqlArr.length; i++) {
                String sql = sqlArr[i].replaceAll("--.*", "").trim();
                if (!sql.equals("")) {
                    sqlList.add(sql);
                }
            }
            return sqlList;
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

    /**  
      * 传入连接来执行 SQL 脚本文件，这样可与其外的数据库操作同处一个事物中  
     * @param conn 传入数据库连接  
      * @param sqlFile SQL 脚本文件  
      * @throws Exception  
      */
    public void execute(Connection conn, String sqlFile) throws Exception {
        Statement stmt = null;
        List<String> sqlList = loadSql(sqlFile);
        stmt = conn.createStatement();
        for (String sql : sqlList) {
            stmt.addBatch(sql);
        }
        int[] rows = stmt.executeBatch();
        System.out.println("Row count:" + Arrays.toString(rows));
    }

    /**  
     * 自建连接，独立事物中执行 SQL 文件  
     * @param sqlFile SQL 脚本文件  
     * @throws Exception  
    */
    public void execute(String sqlFile) throws Exception {
        Class.forName("com.ibm.db2.jcc.DB2Driver").newInstance();
        Connection con = java.sql.DriverManager.getConnection(
            "jdbc:db2://192.168.1.83:50000/CITIC", "db2admin", "888888");
        Statement stmt = null;
        List<String> sqlList = loadSql(sqlFile);
        try {
            con.setAutoCommit(false);
            stmt = con.createStatement();
            for (String sql : sqlList) {
                stmt.addBatch(sql);
            }
            int[] rows = stmt.executeBatch();
            System.out.println("Row count:" + Arrays.toString(rows));
            con.commit();
        } catch (Exception ex) {
            con.rollback();
            throw ex;
        } finally {
            con.close();
        }
    }

    /**
     * 解压zip文件
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public List<String> ZipFile() throws Exception {
        String name = "";
        List<String> nameList = new ArrayList<String>();
        BufferedInputStream is = null;
        FileOutputStream fos = null;
        BufferedOutputStream bos = null;
        
        int count;
        ZipFile zf = new ZipFile(new File("F://zip.zip"));
        Enumeration e = zf.getEntries();
        ZipEntry ze=null;
        while (e.hasMoreElements()) {
             ze = (ZipEntry) e.nextElement();
            if (!ze.isDirectory()) {
                //System.out.println(new String(ze.getName().getBytes("ISO-8859-1"), "GB2312"));
                name = ze.getName();
                nameList.add(name);
                byte buf[] = new byte[1024];
                String fileName = ze.getName();
                int index = fileName.lastIndexOf("//");
                if (index > -1) {
                    fileName = fileName.substring(index + 1);
                }
                fileName = "F://" + fileName;
                File file = new File(fileName);
                OutputStream outputStream = new FileOutputStream(file);
                InputStream inputStream = zf.getInputStream(ze);
                while ((count = inputStream.read(buf)) > -1) {
                    outputStream.write(buf, 0, count);
                }
                outputStream.close();
                inputStream.close();
            }
        }
        zf.close();
        return nameList;
    }

    public static void main(String[] args) throws Exception {
        SqlFileExecutor sfe = new SqlFileExecutor();
        List<String> list = sfe.ZipFile();
        for (int i = 0; i < list.size(); i++) {
            sfe.execute("F://" + list.get(i));
        }
    }
}