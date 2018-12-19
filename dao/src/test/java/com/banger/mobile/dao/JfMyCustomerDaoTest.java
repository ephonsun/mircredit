package com.banger.mobile.dao;

import com.banger.mobile.dao.points.JfMyCustomerDao;
import com.banger.mobile.domain.model.points.JfMyCustomer;
import com.banger.mobile.domain.model.user.SysUserBean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-8-22
 * Time: 下午2:44
 * To change this template use File | Settings | File Templates.
 */
public class JfMyCustomerDaoTest extends BaseDaoTestCase{

    private JfMyCustomerDao jfMyCustomerDao;

    public JfMyCustomerDao getJfMyCustomerDao() {
        return jfMyCustomerDao;
    }

    public void setJfMyCustomerDao(JfMyCustomerDao jfMyCustomerDao) {
        this.jfMyCustomerDao = jfMyCustomerDao;
    }
    /**
     * 测试dao是否为空
     * @throws Exception
     */
    public void testDaoNotNull() throws Exception {
        assertNotNull(jfMyCustomerDao);
    }


    public void testSearchMyCustomer(){
        Map<String,Object> map= new  HashMap<String,Object>();
        map.put("userId",60);
       // List<JfMyCustomer> crm=jfMyCustomerDao.searchMyCustomer(map);
        //System.out.print(crm.size());

    }

    public void testRemoveMyCustomer(){
        Map<String,Object> map= new  HashMap<String,Object>();
        map.put("customerCodeArray","'K0001'");
        jfMyCustomerDao.removeMyCustomer(map);
    }

    public void testAddMyCustomer(){
        JfMyCustomer crm=new JfMyCustomer();
        crm.setBelongToDept("10001");
        crm.setCustomerCode("K0001");
        crm.setCustomerName("张三");
        crm.setIdCard("430423198710138235");
        crm.setUserId(60);
        crm.setPhoneNumber1("15869123052");
        jfMyCustomerDao.addMyCustomer(crm);
    }


}
