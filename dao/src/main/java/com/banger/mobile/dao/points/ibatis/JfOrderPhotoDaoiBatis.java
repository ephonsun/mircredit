package com.banger.mobile.dao.points.ibatis;

import com.banger.mobile.dao.points.JfMyCustomerDao;
import com.banger.mobile.dao.points.JfOrderPhotoDao;
import com.banger.mobile.domain.model.points.JfMyCustomer;
import com.banger.mobile.domain.model.points.JfOrderPhoto;
import com.banger.mobile.ibatis.GenericDaoiBatis;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: zhangfp
 * Date: 13-8-21
 * Time: 下午4:59
 * To change this template use File | Settings | File Templates.
 */
public class JfOrderPhotoDaoiBatis extends GenericDaoiBatis implements JfOrderPhotoDao {
    public JfOrderPhotoDaoiBatis() {
        super(JfOrderPhoto.class);
    }
    /**
     * @param persistentClass
     */
    public JfOrderPhotoDaoiBatis(Class persistentClass) {
        super(JfOrderPhoto.class);
    }

    /**
     * 添加数据
     * 
     * @param jfOrderPhoto
     */
    public void insertJfOrderPhoto(JfOrderPhoto jfOrderPhoto){
        this.getSqlMapClientTemplate().insert("insertJfOrderPhoto",jfOrderPhoto);
    }

    /**
     * 根据order_no获取图片列表
     * 
     * @param paramMap
     * @return
     */
    public List<JfOrderPhoto> getJfOrderPhotoListByOrderNo(Map<String, Object> paramMap){
        return this.getSqlMapClientTemplate().queryForList("getJfOrderPhotoListByOrderNo",paramMap);
    }
}
