package com.banger.mobile.facade.points;

import com.banger.mobile.domain.model.points.JfOrderPhoto;

import java.util.List;
import java.util.Map;

/**
 * @author zhangfp
 * @version $Id: JfOrderPhotoService v 0.1 ${} 下午4:35 zhangfp Exp $
 */
public interface JfOrderPhotoService {
    public void insertJfOrderPhoto(JfOrderPhoto jfOrderPhoto);

    List<JfOrderPhoto> getJfOrderPhotoListByOrderNo(Map<String, Object> paramMap);
}
