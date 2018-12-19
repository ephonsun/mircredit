package com.banger.mobile.service;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.concurrent.CountDownLatch;

import com.banger.mobile.constants.TransportConstants;

public class FileClientThread implements Runnable {
    private String         filename;
    private CountDownLatch threadsSignal;

    public FileClientThread(CountDownLatch threadsSignal, String src) {
        this.filename = src;
        this.threadsSignal = threadsSignal;
    }

    public void run() {
        Boolean result = sendFile();
        //        while (!result) {
        //            try {
        //                Thread.sleep(1000);
        //            } catch (InterruptedException e) {
        //                e.printStackTrace();
        //            }
        //            result = sendFile();
        //        }
        //File f = new File("d:/test/" + filename);
        //FileUtil.moveFile2Dir(f, "d:/test/backup/");
        threadsSignal.countDown();
        System.out.println(threadsSignal.getCount());
        System.out.println(filename + "传输完毕...");
    }

    private Boolean sendFile() {
        Boolean result = false;
        DataOutputStream ps = null;
        Socket socket = null;
        RandomAccessFile fileff = null;
        DataInputStream pi = null;
        try {
            File f = new File("e:/test/" + filename);
            socket = new Socket();
            socket.connect(new InetSocketAddress("192.168.1.188", 7878),
                TransportConstants.CONNECT_TIMEOUT);
            //socket.setSoTimeout(TransportConstants.READ_TIMEOUT);

            // 告诉服务端文件名
            ps = new DataOutputStream(socket.getOutputStream());
            ps.writeUTF(f.getName() + ":" + f.length());
            ps.flush();

            // 取得续传点
            pi = new DataInputStream(socket.getInputStream());
            String uploadedLength = pi.readUTF();

            long filelength = Long.parseLong(uploadedLength);
            fileff = new RandomAccessFile(f, "r");

            fileff.skipBytes((int) filelength);

            byte[] by = new byte[TransportConstants.BUFF_LENGTH];
            int amount = fileff.read(by);
            int count = 0;
            while (amount != (-1)) {
                ps.write(by, 0, amount);
                amount = fileff.read(by);
                count++;
            }

            String isOk = pi.readUTF();

            fileff.close();
            ps.flush();
            ps.close();
            pi.close();
            socket.close();

            //            File remoteFile = new File(
            //                "D:\\Work\\金融营销管理系统\\03.编码\\Server\\BangerCrm\\web\\target\\banger-webapp-1.0\\temp-file\\record\\"
            //                        + filename);
            //            if (f.length() == remoteFile.length()) {
            //                result = true;
            //            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileff != null) {
                    fileff.close();
                    fileff = null;
                }
                if (ps != null) {
                    ps.close();
                    ps = null;
                }
                if (pi != null) {
                    pi.close();
                    pi = null;
                }
                if (socket != null) {
                    socket.close();
                    socket = null;
                }
            } catch (IOException e) {
            }
        }
        return result;
    }
}
