/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :文件压缩工具类...
 * Author     :yuanme
 * Create Date:2012-3-28
 */
package com.banger.mobile.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;
import org.apache.commons.compress.archivers.tar.TarArchiveOutputStream;
import org.apache.log4j.Logger;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.Tar;
import org.apache.tools.ant.taskdefs.Zip;
import org.apache.tools.ant.types.FileSet;
import org.apache.tools.ant.types.ResourceCollection;
import org.apache.tools.ant.types.selectors.FileSelector;
import org.apache.tools.tar.TarEntry;
import org.apache.tools.tar.TarOutputStream;


/**
 * 文件压缩工具类
 * @author xiall
 * @version CompressUtil.java,v 0.1 2012-3-28 下午3:08:46 yuanme
 */
public class CompressUtil {
    private static Logger logger = Logger.getLogger(CompressUtil.class);

	 public static void WriteToTarGzip(String folderPath, String targzipFilePath,String rootPath) throws Throwable   
     {   
        byte[] buf = new byte[1024]; //设定读入缓冲区尺寸   
         try   
          {  
            //建立压缩文件输出流   
              FileOutputStream fout=new FileOutputStream(targzipFilePath);   
             //建立tar压缩输出流   
              TarOutputStream tout=new TarOutputStream(fout);  
              addFiles(tout,folderPath,rootPath);              
              tout.close();
              fout.close();             
                
             //建立压缩文件输出流   
              FileOutputStream gzFile=new FileOutputStream(targzipFilePath+".gz");   
             //建立gzip压缩输出流   
              GZIPOutputStream gzout=new GZIPOutputStream(gzFile);   
             //打开需压缩文件作为文件输入流   
              FileInputStream tarin=new FileInputStream(targzipFilePath);   //targzipFilePath是文件全路径   
              int len;   
              while ((len=tarin.read(buf)) != -1)   
              {   
                  gzout.write(buf,0,len);   
              }   
              gzout.close();   
              gzFile.close();   
              tarin.close(); 
              
          }
         catch(FileNotFoundException e)   
          {   
              System.out.println(e);   
          }catch(IOException e)   
          {   
              System.out.println(e);   
          } 
          
          File tarfile=new File(targzipFilePath);
          tarfile.delete();
     }
	 
	 public static void addFiles(TarOutputStream tout,String folderPath,String rootPath) throws Throwable   
     {   
         File srcPath =new File(folderPath);   
         int length=srcPath.listFiles().length;   
         byte[] buf = new byte[1024]; //设定读入缓冲区尺寸   
         File[] files = srcPath.listFiles();   
         try   
          {  
              for(int i=0;i<length;i++)   
              {            
                 if(files[i].isFile())
                 {
                     String filename=srcPath.getPath()+File.separator+files[i].getName();   
                     System.out.println(filename);
                     //打开需压缩文件作为文件输入流   
                      FileInputStream fin=new FileInputStream(filename);   //filename是文件全路径   
                      TarEntry tarEn=new TarEntry(files[i]); //此处必须使用new TarEntry(File file);   
                      String newName = filename.substring(rootPath.length()+1, filename.length());
                      //newName=new String(newName.getBytes("UTF-8"),"ISO-8859-1");
                      //newName=new String(newName.getBytes("UTF-8"),"GB18030");
                      tarEn.setName(newName);  //此处需重置名称，默认是带全路径的，否则打包后会带全路径   
                      tout.putNextEntry(tarEn);     
                      int num;   
                      while ((num=fin.read(buf)) != -1)   
                      {   
                          tout.write(buf,0,num);   
                      }   
                      tout.closeEntry();   
                      fin.close(); 
                 }
                 else
                 {
                     System.out.println(files[i].getPath());
                     addFiles(tout,files[i].getPath(),rootPath);
                 }
              }              
          }catch(FileNotFoundException e)   
          {   
              System.out.println(e);   
          }catch(IOException e)   
          {   
              System.out.println(e);   
          }          
        
     }
	private ResourceCollection selector;
	 
	 public static void compress(String rootSavePath,String targzipFilePath) {
		 File srcdir = new File(rootSavePath);
		 if (!srcdir.exists()){
			 throw new RuntimeException(rootSavePath + "不存在！");
		 }
	          
        File targetFile = new File(targzipFilePath);	        
        Tar tar = new Tar();
        tar.setDestFile(targetFile); 
        tar.setBasedir(srcdir);
        tar.execute();
	 }
	 
	 public static void callLinuxCmd(String baseSavePath ,String SoFilePath){
		 
		 String  command = "sh /home/compress.sh "+baseSavePath+" "+SoFilePath+"";
		 logger.info("LnLoanServiceImpl.callLinuxCmd : cmd: "+command);
			 
			 try {
				try {
					Runtime.getRuntime().exec(command).waitFor();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		 
	 }
	 
	 /**
     * @param args
     * @throws Exception 
     */
    public static void main(String[] args) throws Exception {
        //compress(new File("1"), new File("1.tar.gz"));
        //decompress(new File("target.tar.gz"), new File("target"));
        //decompress(new File("1.tar.gz"));
        System.out.println(System.getProperty("os.name"));
        System.out.println(System.getProperty("os.version"));
    }

}
