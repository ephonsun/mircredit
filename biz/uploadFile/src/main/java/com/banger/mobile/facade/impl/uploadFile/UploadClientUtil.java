package com.banger.mobile.facade.impl.uploadFile;

import com.banger.mobile.constants.TransportConstants;
import org.apache.log4j.Logger;

import java.io.*;
import java.net.Socket;
import java.net.SocketAddress;

public class UploadClientUtil {
    private static final Logger logger = Logger.getLogger(UploadClientUtil.class);
    private String ip;
    private int port;
    private Socket socket;

    public UploadClientUtil(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    public void open() throws IOException {
        socket = new Socket(ip, port);
        socket.setSoTimeout(TransportConstants.READ_TIMEOUT);
        SocketAddress address = socket.getLocalSocketAddress();
        if (!socket.isConnected()) {
            socket.connect(address);
        }
    }

    public void send(byte[] data, int size) throws IOException {
        byte[] sendByte = data;
        byte[] newSendByte = sendByte;

        int dataLength = sendByte.length;

        int flag = dataLength / size;
        int end = dataLength % size;
        int senderNum = 0; // 发送次数

        // 如果没有余数，发包时就不用多发一次
        if (end > 0) {
            senderNum = flag + 1;
        } else {
            senderNum = flag;
        }
        byte[] dataInfo;// 文件数据
        OutputStream out = new BufferedOutputStream(socket.getOutputStream());
        // 开始分包发送
        for (int i = 0; i < senderNum; i++) {
            if (i < flag) {
                dataInfo = new byte[size];
                for (int j = 0; j < size; j++) {
                    dataInfo[j] = newSendByte[i * size + j];
                }
                sendVarData(dataInfo, out);
            } else {
                dataInfo = new byte[end];
                for (int j = 0; j < end; j++) {
                    dataInfo[j] = newSendByte[i * size + j];
                }
                sendVarData(dataInfo, out);
            }
        }
    }

    private void sendVarData(byte[] data, OutputStream out) throws IOException {

        int dataLength = data.length;
        byte[] sendBuf = new byte[4 + dataLength];
        byte[] dataSize = ByteUtil.int2bytes(dataLength);

        for (int i = 0; i < 4; i++) {
            sendBuf[i] = dataSize[i];
        }

        for (int i = 0; i < dataLength; i++) {
            sendBuf[i + 4] = data[i];
        }
        out.write(sendBuf);
        out.flush();
    }

    public byte[] recv() throws Exception {
        InputStream in = null;
        int recvLen; // 实际接收大小
        int recvTotalLen = 0; // 实际接收总大小
        int dataLen; // 将要接收数据总大小
        byte[] dataSize = new byte[4];
        in = new BufferedInputStream(socket.getInputStream());
        recvLen = in.read(dataSize);
        if (recvLen != 4) {
            logger.error("接收报文头异常");
            throw new Exception("接收报文头异常");
        }
        dataLen = ByteUtil.bytes2int(dataSize);

        byte[] dataRecv = new byte[dataLen];
        while (recvTotalLen < dataLen) {
            recvLen = in.read(dataRecv, recvTotalLen, dataLen - recvTotalLen);
            recvTotalLen += recvLen;
        }

        return dataRecv;
    }

    public void close() throws IOException {
        if (socket != null){
            socket.shutdownInput();
            socket.shutdownOutput();
            socket.close();
        }
    }

}
