package com.banger.mobile.service;

import com.banger.mobile.encryption.EncryptionUtil;
import org.apache.commons.io.FilenameUtils;

import java.io.File;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-2-25
 * Time: 下午4:31
 * To change this template use File | Settings | File Templates.
 */
public class DecryptFileTest {
    public static void main(String[] args) {
        try {
            System.out.println(FilenameUtils.getFullPathNoEndSeparator("c:/test/"));
            System.out.println(FilenameUtils.getFullPathNoEndSeparator("c:/test"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
