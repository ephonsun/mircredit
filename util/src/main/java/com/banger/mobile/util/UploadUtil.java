package com.banger.mobile.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public interface UploadUtil {


    /**
     * �ϴ��ļ�
     *
     * @param file
     * @param fileFileName
     * @param uploadSubPath
     * @return
     */
    String upload(File file, String fileFileName, String uploadSubPath)
            throws FileNotFoundException, IOException;

    /**
     * �ϴ������ļ�
     * @param file
     * @param fileFileName
     * @param uploadSubPath
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     */
    String[] uploadComapreFile(File file, String fileFileName, String uploadSubPath)
    throws FileNotFoundException, IOException;

    /**
     * �ϴ�ͼƬ��ѹ��
     *
     * @param file
     * @param fileFileName
     * @param uploadSubPath
     * @param width
     * @param height
     * @return
     * @throws IOException
     */
    String uploadImageWithCompress(File file, String fileFileName,
            String uploadSubPath, int width, int height) throws IOException;

    /**
     * ɾ���ļ�
     * @param docfilePath
     * @param fileName
     * @return
     */
    boolean deleteFile(String docfilePath, String fileName);

    /**
     * ��ȡ�ļ��ϴ��ĸ�·��
     * @return
     */
    String getUploadRootPath();

    public String createFileName(String preName);

    public  String createFileName(String preName,String name);
    public  String createFileName(String preName,String name,String backName);
    
    public  String createFileNameOrg(String preName, String orgname, String backName);

    public String getRealUpload();

}
