/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :yuanme
 * Create Date:2012-3-31
 */
package com.banger.mobile.constants;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yuanme
 * @version TransportConstants.java,v 0.1 2012-3-31 下午3:45:49
 */
public class TransportConstants {
    public static Map<String, Integer> validateFileMap              = new HashMap<String, Integer>();

    public static final String         UPLOAD_TEMP_DIR              = "/temp-file";
    public static final String         UPLOAD_TEMP_DIR_ERROR        = "/temp-file/error";
    public static final String         VALIDATED_FILE_XML_OK        = "xml.ok";
    public static final String         VALIDATED_FILE_OK            = "ok";
    public static final String         VALIDATED_FILE_FAIL          = "no";
    public static final String         ENTRYPTD_FILE_EXTENSION      = ".e";
    public static final String         COMPRESS_FILE_EXTENSION      = ".tar.gz";
    public static final String         RECORD_INFO_EXTENSION        = ".xml";
    public static final String         RECORD_FILE_EXTENSION        = ".aac";
    public static final String         RECORD_FILE_EXTENSION_LOCAL  = ".wav";
	
	public static final String         PHOTO_FILE_EXTENSION        = ".jpg";
    public static final String         AUDIO_FILE_EXTENSION        = ".aac";
    public static final String         VEDIO_FILE_EXTENSION        = ".3gp";

    /** 传输接口参数 **/
    public static final int            CONNECT_TIMEOUT              = 10000;
    public static final int            READ_TIMEOUT                 = 600000;
    public static final int            SERVER_SPEED_LIMIT           = 100000;
    public static final int            SERVER_PORT                  = 7878;
    public static final int            QUEUE_SIZE                   = 300;
    public static final int            BUFF_LENGTH                  = 2048;

    /** 文件保存路径 **/
    public static final String         RECORD_FILE_STORE_PATH_WIN   = "c:/home";
    public static final String         RECORD_FILE_STORE_PATH_LINUX = "/home";
    public static final String         RECORD_PATH                  = "RECORD_PATH";

    /** 流量控制 **/
    public static final String         FLOW_CONTROL_ISACTIVE        = "FLOW_CONTROL_ISACTIVE";
    public static final String         WEB_FLOW_CONTROL_ISACTIVE    = "WEB_FLOW_CONTROL_ISACTIVE";
    public static final String         FLOW_CONTROL_MAXBPS          = "FLOW_CONTROL_MAXBPS";

    /** 录音来源 **/
    public static final int            RECORD_SOURCE_PHONE          = 1;
    public static final int            RECORD_SOURCE_PAD            = 2;

    /** 通话方式 **/
    public static final int            CALL_TYPE_IN                 = 1;
    public static final int            CALL_TYPE_OUT                = 2;
    public static final int            CALL_TYPE_NO_ANSWER          = 3;
    public static final int            CALL_TYPE_LIVING             = 4;
    public static final int            CALL_TYPE_VISIT              = 5;
    public static final int            CALL_TYPE_NO_READ_MESSAGE    = 6;
    
    /** 文件保存路径 **/
    public static final String         AUTO_IMPORT_PATH_WIN   = "c:/autoImport";
    public static final String         AUTO_IMPORT_PATH_LINUX = "/home/autoImport";

    /** 信贷接口端口 **/
    public static final int            SERVER_LOAN_PORT                  = 8888;
}
