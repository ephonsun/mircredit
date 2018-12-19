package com.banger.mobile.service;

import java.io.File;
import java.util.concurrent.CountDownLatch;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;

public class HttpFileClientThread implements Runnable {
    private String         filename;
    private CountDownLatch threadsSignal;

    public HttpFileClientThread(CountDownLatch threadsSignal, String src) {
        this.filename = src;
        this.threadsSignal = threadsSignal;
    }

    public void run() {
        String targetURL = null;// TODO 指定URL
        File targetFile = null;// TODO 指定上传文件

        targetFile = new File("e:/test/" + filename);
        targetURL = "http://192.168.1.188:8080/fileUpload"; //servleturl
        PostMethod filePost = new PostMethod(targetURL);

        try {
            Part[] parts = { new FilePart(targetFile.getName(), targetFile) };
            filePost.setRequestEntity(new MultipartRequestEntity(parts, filePost.getParams()));
            HttpClient client = new HttpClient();
            client.getHttpConnectionManager().getParams().setConnectionTimeout(5000);
            int status = client.executeMethod(filePost);
            if (status == HttpStatus.SC_OK) {
                System.out.println("上传成功");
                // 上传成功
            } else {
                System.out.println("上传失败");
                // 上传失败
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            filePost.releaseConnection();
        }

        threadsSignal.countDown();
        System.out.println(threadsSignal.getCount());
        System.out.println(filename + "传输完毕...");
    }

}
