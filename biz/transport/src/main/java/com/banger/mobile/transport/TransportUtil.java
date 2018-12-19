/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :保存录音文件...
 * Author     :yuanme
 * Create Date:2012-8-7
 */
package com.banger.mobile.transport;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.banger.mobile.constants.TransportConstants;
import com.banger.mobile.facade.param.SysParamService;
import com.banger.mobile.util.SystemUtil;

/**
 * @author yuanme
 * @version TransportUtil.java,v 0.1 2012-8-7 下午3:56:24
 */
public class TransportUtil {
    private static Map<String, Integer> deptUserCountMap = new ConcurrentHashMap<String, Integer>();

    private static final String         DEPT             = "DEPT_";

    /**
     * 得到录音文件保存路径
     * @return
     */
    public static String getRecordPath(SysParamService sysParamService) {
        String result = "";
        String dbRecPath = getRecPath(sysParamService);
        if (dbRecPath != null && !"".equals(dbRecPath)) {
            result = dbRecPath;
        } else {
            result = getDefaultRecordPath();
        }
        return result;
    }

    /**
     * 
     * @param sysParamService
     * @return
     */
    public static String getRecPath(SysParamService sysParamService) {
        String result = "";
        if (sysParamService != null) {
            Map<String, Object> map = sysParamService.querySysParam();
            if (map != null) {
                Object path = map.get(TransportConstants.RECORD_PATH);
                if (path != null) {
                    result = path.toString();
                }
            }
        }
        return result;
    }

    /**
     * 得到默认录音文件保存路径
     * @return
     */
    public static String getDefaultRecordPath() {
        String result = "";
        if (SystemUtil.isWindows()) {
            result = TransportConstants.RECORD_FILE_STORE_PATH_WIN;
        } else {
            result = TransportConstants.RECORD_FILE_STORE_PATH_LINUX;
        }
        return result;
    }

    /**
     * 取得机构下当前正在传输用户数量
     * @param deptId
     * @return
     */
    public static int getDeptUserCount(Integer deptId) {
        Integer count = deptUserCountMap.get(DEPT + deptId);
        if (count != null) {
            return deptUserCountMap.get(DEPT + deptId);
        } else {
            return 0;
        }

    }

    /**
     * 更新用户数量
     * @param deptId
     * @param i
     */
    public static void updateDeptUserCount(Integer deptId, int count) {
        deptUserCountMap.put(DEPT + deptId, count);
    }

    /**
     * 更新用户数量
     * @param deptId
     * @param i
     */
    public static void updateDeptUserCountAfter(Integer deptId) {
        Integer count = deptUserCountMap.get(DEPT + deptId);
        if (count != null && count > 0) {
            deptUserCountMap.put(DEPT + deptId, count - 1);
        }

    }
}
