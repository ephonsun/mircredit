package com.banger.mobile.facade.impl.points;

import com.banger.mobile.dao.points.JfOrderPhotoDao;
import com.banger.mobile.domain.model.points.JfOrderPhoto;
import com.banger.mobile.facade.points.JfOrderPhotoService;

import java.util.List;
import java.util.Map;

/**
 * @author zhangfp
 * @version $Id: JfOrderPhotoServiceImpl v 0.1 ${} 下午4:37 zhangfp Exp $
 */
public class JfOrderPhotoServiceImpl implements JfOrderPhotoService {

    private JfOrderPhotoDao jfOrderPhotoDao;

    public JfOrderPhotoDao getJfOrderPhotoDao() {
        return jfOrderPhotoDao;
    }

    public void setJfOrderPhotoDao(JfOrderPhotoDao jfOrderPhotoDao) {
        this.jfOrderPhotoDao = jfOrderPhotoDao;
    }

    public void insertJfOrderPhoto(JfOrderPhoto jfOrderPhoto) {
        jfOrderPhotoDao.insertJfOrderPhoto(jfOrderPhoto);
    }

    /**
     * 根据order_no获取图片列表
     *
     * @param paramMap
     * @return
     */
    public List<JfOrderPhoto> getJfOrderPhotoListByOrderNo(Map<String, Object> paramMap){
        return jfOrderPhotoDao.getJfOrderPhotoListByOrderNo(paramMap);
    }
}
