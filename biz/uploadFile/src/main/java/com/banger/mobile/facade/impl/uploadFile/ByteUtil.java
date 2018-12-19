package com.banger.mobile.facade.impl.uploadFile;

/**
 * 
 * @author Administrator
 * 
 */
public class ByteUtil {

    public static final int bytes2int(byte[] b) {

        int mask = 0xff;
        int temp = 0;
        int res = 0;
        for (int i = 0; i < 4; i++) {
            res <<= 8;
            temp = b[i] & mask;
            res |= temp;
        }
        return res;
    }

    public static final byte[] int2bytes(int num) {

        byte[] b = new byte[4];
        for (int i = 0; i < 4; i++) {
            b[i] = (byte) (num >>> (24 - i * 8));
        }
        return b;
    }
}
