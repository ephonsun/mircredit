/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :积分商城中照片DAO...
 * Author     :yangy
 * Create Date:Aug 15, 2012
 */
package com.banger.mobile.dao.points;

import com.banger.mobile.domain.model.points.JfMyCustomer;
import com.banger.mobile.domain.model.points.JfOrderPhoto;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: zhangfp
 * Date: 13-8-21
 * Time: 下午4:50
 * To change this template use File | Settings | File Templates.
 */
public interface JfOrderPhotoDao {


    void insertJfOrderPhoto(JfOrderPhoto jfOrderPhoto);

    List<JfOrderPhoto> getJfOrderPhotoListByOrderNo(Map<String, Object> paramMap);
}
