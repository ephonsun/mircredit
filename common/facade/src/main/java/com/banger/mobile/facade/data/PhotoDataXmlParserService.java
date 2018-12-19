/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :资料业务接口
 * Author     :yuanme
 * Version    :1.0
 * Create Date:May 3, 2012
 */
package com.banger.mobile.facade.data;


/**
 * @author yuanme
 * @version $Id: PhotoDataXmlParserService.java,v 0.1 2012-5-24 下午04:42:52 yuanme Exp $
 */
public interface PhotoDataXmlParserService {

    /**
     * 从xml文件中提取照片信息
     */
    public void doJob(String fileName);
}
