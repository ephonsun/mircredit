/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :文件压缩工具类...
 * Author     :yuanme
 * Create Date:2012-3-28
 */
package com.banger.mobile.compress;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;
import org.apache.commons.compress.archivers.tar.TarArchiveOutputStream;

/**
 * 文件压缩工具类
 * @author yuanme
 * @version CompressUtil.java,v 0.1 2012-3-28 下午3:08:46 yuanme
 */
public class CompressUtil {
    private static final String BASE_DIR    = "";
    private static final String PATH        = "/";
    private static final int    BUFFER      = 1024;
    private static final String FILE_SUFFIX = ".tar.gz";

    /**
     * 压缩至指定文件
     * @param src 原始文件或文件夹(13912348888_201203031112332.e)
     * @param dest 打包后文件(13912348888_201203031112332.tar.gz)
     * @throws Exception 异常
     */
    public static void compress(File src, File dest) throws Exception {
        //不管是文件还是文件夹都统一先用tar打包
        File tmp = new File(dest.getAbsolutePath() + ".tmp");
        archive(src, tmp);

        //用gzip压缩
        compressFileGZIP(tmp, dest);

        //删除临时文件
        tmp.delete();
    }

    /**
     * 压缩至源文件相同路径
     * 默认后缀名为.tar.gz
     * @param src 原始文件或文件夹(13912348888_201203031112332.e)
     * @throws Exception 异常
     */
    public static void compress(File src) throws Exception {
        //不管是文件还是文件夹都统一先用tar打包
        File tmp = new File(src.getAbsolutePath() + ".tmp");
        archive(src, tmp);

        //用gzip压缩
        String srcFilePath = src.getAbsolutePath();
        String destFilePath = srcFilePath.substring(0, srcFilePath.lastIndexOf("."));
        File dest = new File(destFilePath + FILE_SUFFIX);
        compressFileGZIP(tmp, dest);

        //删除临时文件
        tmp.delete();
    }

    /**
     * 解压缩到指定文件夹
     * @param src 压缩文件(13912348888_201203031112332.tar.gz)
     * @param dest 解压后存放路径
     * @throws Exception 异常
     */
    public static void decompress(File src, File dest) throws Exception {
        //用gzip解压缩
        File tmp = new File(dest.getAbsolutePath() + ".tmp");
        decompressFileGZIP(src, tmp);

        //解包
        dearchive(tmp, dest);

        //删除临时文件
        tmp.delete();
    }

    /**
     * 解压缩到源文件相同路径
     * @param src 压缩文件(13912348888_201203031112332.tar.gz)
     * @throws Exception 异常
     */
    public static void decompress(File src) throws Exception {
        //用gzip解压缩
        File tmp = new File(src.getAbsolutePath() + ".tmp");
        decompressFileGZIP(src, tmp);

        //解包
        dearchive(tmp);

        //删除临时文件
        tmp.delete();
    }

    /**
     * gzip压缩文件
     * @param src 原始文件
     * @param dest 压缩后文件
     * @throws Exception 异常
     */
    private static void compressFileGZIP(File src, File dest) throws Exception {
        FileInputStream fis = new FileInputStream(src);
        FileOutputStream fos = new FileOutputStream(dest);
        GZIPOutputStream gos = new GZIPOutputStream(fos);
        int len;
        byte bs[] = new byte[1024];
        while ((len = fis.read(bs)) != -1) {
            gos.write(bs, 0, len);
        }
        gos.finish();
        gos.flush();
        gos.close();
        fos.close();
        fis.close();
    }

    /**
     * 解压gzip文件
     * @param src 原始文件
     * @param dest 解压后文件
     * @throws Exception 异常
     */
    private static void decompressFileGZIP(File src, File dest) throws Exception {
        FileInputStream fis = new FileInputStream(src);
        FileOutputStream fos = new FileOutputStream(dest);
        GZIPInputStream gis = new GZIPInputStream(fis);
        int count;
        byte data[] = new byte[BUFFER];
        while ((count = gis.read(data, 0, BUFFER)) != -1) {
            fos.write(data, 0, count);
        }
        fos.close();
        gis.close();
        fis.close();
    }

    /**
     * 归档
     * @param srcFile 源路径
     * @param destPath 目标路径
     * @throws Exception 异常
     */
    private static void archive(File srcFile, File destFile) throws Exception {
        TarArchiveOutputStream taos = new TarArchiveOutputStream(new FileOutputStream(destFile));
        archive(srcFile, taos, BASE_DIR);
        taos.flush();
        taos.close();
    }

    /**
     * 归档
     * @param srcFile 源路径
     * @param taos TarArchiveOutputStream
     * @param basePath 归档包内相对路径
     * @throws Exception
     */
    private static void archive(File srcFile, TarArchiveOutputStream taos, String basePath)
                                                                                           throws Exception {
        if (srcFile.isDirectory()) {
            archiveDir(srcFile, taos, basePath);
        } else {
            archiveFile(srcFile, taos, basePath);
        }
    }

    /**
     * 目录归档
     * @param dir 源目录
     * @param taos TarArchiveOutputStream
     * @param basePath 归档包内相对路径
     * @throws Exception 异常
     */
    private static void archiveDir(File dir, TarArchiveOutputStream taos, String basePath)
                                                                                          throws Exception {
        File[] files = dir.listFiles();
        if (files.length < 1) {
            TarArchiveEntry entry = new TarArchiveEntry(basePath + dir.getName() + PATH);
            taos.putArchiveEntry(entry);
            taos.closeArchiveEntry();
        }
        for (File file : files) {
            // 递归归档
            archive(file, taos, basePath + dir.getName() + PATH);
        }
    }

    /**
     * 数据归档
     * @param data 待归档数据
     * @param path 归档数据的当前路径
     * @param name 归档文件名
     * @param taos TarArchiveOutputStream
     * @throws Exception 异常
     */
    private static void archiveFile(File file, TarArchiveOutputStream taos, String dir)
                                                                                       throws Exception {
        TarArchiveEntry entry = new TarArchiveEntry(dir + file.getName());
        entry.setSize(file.length());
        taos.putArchiveEntry(entry);
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
        int count;
        byte data[] = new byte[BUFFER];
        while ((count = bis.read(data, 0, BUFFER)) != -1) {
            taos.write(data, 0, count);
        }
        bis.close();
        taos.closeArchiveEntry();
    }

    /**
     * 解归档到源文件同一目录
     * @param srcFile 源文件
     * @throws Exception 异常
     */
    private static void dearchive(File srcFile) throws Exception {
        String basePath = srcFile.getParent();
        dearchive(srcFile, basePath);
    }

    /**
     * 解归档到指定路径
     * @param srcFile 源文件
     * @param destFile 目标路径
     * @throws Exception
     */
    private static void dearchive(File srcFile, File destFile) throws Exception {
        TarArchiveInputStream tais = new TarArchiveInputStream(new FileInputStream(srcFile));
        dearchive(destFile, tais);
        tais.close();
    }

    /**
     * 解归档
     * 
     * @param srcFile
     * @param destPath
     * @throws Exception
     */
    private static void dearchive(File srcFile, String destPath) throws Exception {
        dearchive(srcFile, new File(destPath));
    }

    /**
     * 文件 解归档
     * @param destFile 目标文件
     * @param tais ZipInputStream
     * @throws Exception 异常
     */
    private static void dearchive(File destFile, TarArchiveInputStream tais) throws Exception {
        TarArchiveEntry entry = null;
        while ((entry = tais.getNextTarEntry()) != null) {
            // 文件
            String dir = destFile.getPath() + File.separator + entry.getName();
            File dirFile = new File(dir);
            // 文件检查
            fileProber(dirFile);
            if (entry.isDirectory()) {
                dirFile.mkdirs();
            } else {
                dearchiveFile(dirFile, tais);
            }
        }
    }

    /**
     * 文件解归档
     * @param destFile 目标文件
     * @param tais TarArchiveInputStream
     * @throws Exception 异常
     */
    private static void dearchiveFile(File destFile, TarArchiveInputStream tais) throws Exception {
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(destFile));
        int count;
        byte data[] = new byte[BUFFER];
        while ((count = tais.read(data, 0, BUFFER)) != -1) {
            bos.write(data, 0, count);
        }
        bos.close();
    }

    /**
     * 文件探针
     * 当父目录不存在时，创建目录！
     * @param dirFile 目标路径
     */
    private static void fileProber(File dirFile) {
        File parentFile = dirFile.getParentFile();
        if (!parentFile.exists()) {
            // 递归寻找上级目录
            fileProber(parentFile);
            parentFile.mkdir();
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
