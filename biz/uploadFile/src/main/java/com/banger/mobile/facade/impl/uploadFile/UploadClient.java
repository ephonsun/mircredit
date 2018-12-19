package com.banger.mobile.facade.impl.uploadFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

/**
 * socket上传文件到DMS临时目录
 * 
 * @author gong.jz
 * 
 */
public class UploadClient {
    private static final Logger logger      = Logger.getLogger(UploadClient.class);
    private int                 packageSize = 1024;
    private String              host;
    private int                 port;
    private String              appid;

    public UploadClient(String host, int port, String appid, int packageSize) {
        this.packageSize = packageSize;
        this.host = host;
        this.port = port;
        this.appid = appid;
    }

    public String upload(File file, String newName, String userId) {
        InputStream in = null;
        UploadClientUtil client = null;
        try {
            in = new FileInputStream(file);
            int fileLength = in.available();
            byte[] fileData = new byte[fileLength];
            in.read(fileData); // 上传文件的内容
            int packageSize = this.packageSize;
            int packageTotal = fileLength / packageSize + (fileLength % packageSize > 0 ? 1 : 0);
            client = new UploadClientUtil(this.host, this.port);
            logger.info("---开始上传文件---：" + file.getName());

            client.open();

            String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
                         + "<Service name=\"ScanSubmit\"><In>" + "  <Parameters>"
                         + "    <Parameter name=\"AppId\" value=\"" + this.appid + "\"/>"
                         + "    <Parameter name=\"userId\" value=\"" + userId + "\"/>"
                         + "    <Parameter name=\"fileName\" value=\"" + newName + "\"/>"
                         + "    <Parameter name=\"packageTotal\" value=\"" + packageTotal + "\"/>"
                         + "  </Parameters>" + "</In></Service>";
            logger.info("上传XML文件 ----> 开始：" + xml);
            client.send(xml.getBytes(), packageSize);
            logger.info("上传XML文件 ----> 完成：fileName = " + file.getName());

            byte[] resp = client.recv();
            String respStr = new String(resp);
            logger.info("上传XML文件resp ----> 完成：fileName = " + file.getName() + " ----> resp： " + respStr);
            if (respStr.indexOf("READY") == -1) {
                logger.info(file.getName() + "影像服务器上传未就绪");
                throw new Exception(file.getName() + "影像上传区域服务器未就绪");
            }

            logger.info("上传实际文件 ----> 开始：fileName = " + file.getName());
            client.send(fileData, packageSize);
            logger.info("上传实际文件 ----> 完成：fileName = " + file.getName());

            resp = client.recv();
            String strResp = new String(resp);
            // fileContent.RemoteFilePath = GetRemoteFilePath(strResp);
            logger.info("上传实际文件resp ----> 完成：fileName = " + file.getName() + " ----> resp： " + strResp);
            Document doc = DocumentHelper.parseText(strResp);
            Element param = (Element) doc.selectNodes("/Service/Out/Parameter").get(0);
            String filePath = param.attributeValue("filePath");
            logger.info("---结束上传文件---：" + file.getName());

            return filePath;
        } catch (Exception e) {
            logger.error("上传文件出错", e);
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                logger.error("上传文件出错in.close出错", e);
            }

            try {
                if (client != null) {
                    client.close();
                }
            } catch (Exception e) {
                logger.error("上传文件出错client.close出错", e);
            }
        }
        return null;
    }

    /**
     * 根据本地路径上传文件到DMS临时目录，
     * 
     * @param path
     * @return 返回临时文件目录
     */
    public String sendFile(File file, String newName, String userId) {
        return upload(file, newName, userId);
    }
}
