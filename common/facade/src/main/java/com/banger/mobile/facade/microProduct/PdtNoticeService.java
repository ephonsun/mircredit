/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :通知-Service-接口
 * Author     :QianJie
 * Create Date:Dec 11, 2012
 */
package com.banger.mobile.facade.microProduct;

import java.util.Map;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.model.microProduct.PdtNotice;

/**
 * @author QianJie
 * @version $Id: PdtNoticeService.java,v 0.1 Dec 11, 2012 3:01:39 PM QianJie Exp $
 */
public interface PdtNoticeService {
    /**
     * 编辑通知
     * @param pdtNotice
     * @return
     */
    public int editPdtNotice(PdtNotice pdtNotice);
    
    /**
     * 修改通知已读未读状态
     * @param conds
     */
    public void editPdtNoticeReadByConds(Map<String, Object> conds);
    
    /**
     * 根据id得到通知对象
     * @param noticeId
     * @return
     */
    public PdtNotice getPdtNoticeById(int noticeId);
    
    /**
     * 查询产品通知（分页查询）
     * @param conds
     * @param page
     * @return
     */
    public PageUtil<PdtNotice> getPdtNoticePage(Map<String, Object> conds, Page page);
}
