package com.banger.mobile.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;

public class ZipUtil {
	//解压指定zip文件 
    @SuppressWarnings("rawtypes")
	public static void unZip(String unZipfileName,String path){//unZipfileName需要解压的zip文件名 

        FileOutputStream fileOut; 
        File file; 
        InputStream inputStream;
        int readedBytes = 1024*64;
        byte[] buf = new byte[readedBytes];

        try{ 
        	ZipFile zipFile = new ZipFile(unZipfileName);
            for(Enumeration entries = zipFile.getEntries(); entries.hasMoreElements();){ 
                ZipEntry entry = (ZipEntry)entries.nextElement(); 
                String filename = path+entry.getName();
                file = new File(filename);
                if(entry.isDirectory()){ 
                    file.mkdirs(); 
                } 

                else{ 
                    //如果指定文件的目录不存在,则创建之. 
                    File parent = file.getParentFile(); 

                    if(!parent.exists()){ 

                        parent.mkdirs(); 

                    } 

                    inputStream = zipFile.getInputStream(entry); 
                    fileOut = new FileOutputStream(file); 
                    while((readedBytes = inputStream.read(buf) ) > 0){ 

                        fileOut.write(buf , 0 ,readedBytes ); 
                    } 
                    fileOut.close(); 
                    inputStream.close(); 
                }    

            }
            zipFile.close(); 
        }catch(IOException ioe){ 

            ioe.printStackTrace(); 

        } 

    } 


}
