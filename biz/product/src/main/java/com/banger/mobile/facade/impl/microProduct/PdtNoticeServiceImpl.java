/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :通知-Service-接口实现
 * Author     :QianJie
 * Create Date:Dec 11, 2012
 */
package com.banger.mobile.facade.impl.microProduct;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.dao.microProduct.PdtNoticeDao;
import com.banger.mobile.dao.microProduct.PdtNoticeUserDao;
import com.banger.mobile.domain.model.microProduct.PdtNotice;
import com.banger.mobile.domain.model.microProduct.PdtNoticeUser;
import com.banger.mobile.facade.microProduct.PdtNoticeService;
import com.banger.mobile.util.StringUtil;

/**
 * @author QianJie
 * @version $Id: PdtNoticeServiceImpl.java,v 0.1 Dec 11, 2012 3:02:37 PM QianJie Exp $
 */
public class PdtNoticeServiceImpl implements PdtNoticeService {

    private PdtNoticeDao pdtNoticeDao;
    private PdtNoticeUserDao pdtNoticeUserDao;
    
    public void setPdtNoticeDao(PdtNoticeDao pdtNoticeDao) {
        this.pdtNoticeDao = pdtNoticeDao;
    }

    public void setPdtNoticeUserDao(PdtNoticeUserDao pdtNoticeUserDao) {
        this.pdtNoticeUserDao = pdtNoticeUserDao;
    }

    /**
     * 编辑通知
     * @param pdtNotice
     * @return
     * @see com.banger.mobile.facade.product.PdtNoticeService#editPdtNotice(com.banger.mobile.domain.model.product.PdtNotice)
     */
    public int editPdtNotice(PdtNotice pdtNotice) {
        return pdtNoticeDao.editPdtNotice(pdtNotice);
    }
    
    /**
     * 修改通知已读未读状态
     * @param conds
     * @see com.banger.mobile.facade.product.PdtNoticeService#editPdtNoticeReadByConds(java.util.Map)
     */
    @Transactional
    public void editPdtNoticeReadByConds(Map<String, Object> conds) {
        String noticeIds = conds.get("noticeIds").toString();
        int userId = Integer.parseInt(conds.get("userId").toString());
        int isRead = Integer.parseInt(conds.get("readState").toString());
        if(StringUtil.isNotEmpty(noticeIds)){
            //删除选中通知状态
            pdtNoticeUserDao.delPdtNoticeUserReadByConds(conds);
            
            String[] noticeArr = noticeIds.split(",");
            List<PdtNoticeUser> list =new ArrayList<PdtNoticeUser>();
            for (String notice : noticeArr) {
                PdtNoticeUser nUser =new PdtNoticeUser();
                nUser.setCreateUser(userId);
                nUser.setIsRead(isRead);
                nUser.setNoticeId(Integer.parseInt(notice));
                nUser.setUpdateUser(userId);
                nUser.setUserId(userId);
                list.add(nUser);
            }
            //批量添加通知
            pdtNoticeUserDao.addPdtNoticeUserBatch(list);
        }
    }
    
    /**
     * 根据id得到通知对象
     * @param noticeId
     * @return
     * @see com.banger.mobile.facade.product.PdtNoticeService#getPdtNoticeById(int)
     */
    public PdtNotice getPdtNoticeById(int noticeId) {
        return pdtNoticeDao.getPdtNoticeById(noticeId);
    }

    /**
     * 查询产品通知（分页查询）
     * @param conds
     * @param page
     * @return
     * @see com.banger.mobile.facade.product.PdtNoticeService#getPdtNoticePage(java.util.Map, com.banger.mobile.Page)
     */
    public PageUtil<PdtNotice> getPdtNoticePage(Map<String, Object> conds, Page page) {
        return pdtNoticeDao.getPdtNoticePage(conds, page);
    } 
}
