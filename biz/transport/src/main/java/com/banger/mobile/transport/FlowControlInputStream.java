/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :flow control
 * Author     :yuanme
 * Create Date:2012-3-29
 */
package com.banger.mobile.transport;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 流量控制
 * @author yuanme
 * @version FlowControlInputStream.java,v 0.1 2012-4-1 下午3:25:27
 */
public class FlowControlInputStream extends FilterInputStream {

    private long timestamp;
    private int  maxbps;
    private int  currentbps;
    private int  bytesread;

    // ----------------------------------------------------------
    // constructor
    public FlowControlInputStream(InputStream in, int maxbps) {
        super(in);
        this.maxbps = maxbps;
        this.currentbps = 0;
        this.bytesread = 0;
        this.timestamp = System.currentTimeMillis();
    }

    // ----------------------------------------------------------
    // decorated methods
    
    /**
     * read input
     * @return read byte length
     * @throws IOException
     * @see java.io.FilterInputStream#read()
     */
    public int read() throws IOException {
        synchronized (in) {
            int avaliable = check();
            if (avaliable == 0) {
                waitForAvailable();
                avaliable = check();
            }
            int value = in.read();
            update(1);
            return value;
        }
    }

    /**
     * read input byte array
     * @param b
     * @return
     * @throws IOException
     * @see java.io.FilterInputStream#read(byte[])
     */
    public int read(byte[] b) throws IOException {
        return read(b, 0, b.length);

    }

    /**
     * read input byte array
     * @param b
     * @param off
     * @param len
     * @return
     * @throws IOException
     * @see java.io.FilterInputStream#read(byte[], int, int)
     */
    public int read(byte[] b, int off, int len) throws IOException {
        synchronized (in) {
            int avaliable = check();
            if (avaliable == 0) {
                waitForAvailable();
                avaliable = check();
            }
            int n = in.read(b, off, Math.min(len, avaliable));
            update(n);
            return n;
        }
    }

    /**
     * check speed
     * @return
     */
    public int check() {
        long now = System.currentTimeMillis();
        if (now - timestamp >= 1000) {
            timestamp = now;
            currentbps = bytesread;
            bytesread = 0;
            return maxbps;
        } else {
            return maxbps - bytesread;
        }
    }

    /**
     * is speed is to fast,then wait
     */
    private void waitForAvailable() {
        long time = System.currentTimeMillis() - timestamp;
        boolean isInterrupted = false;
        while (time < 1000) {
            try {
                Thread.sleep(1000 - time);
            } catch (InterruptedException e) {
                isInterrupted = true;
            }
            time = System.currentTimeMillis() - timestamp;
        }
        if (isInterrupted)
            Thread.currentThread().interrupt();
        return;

    }

    private void update(int n) {
        bytesread += n;
    }

    public int getCurrentbps() {
        return currentbps;
    }
}