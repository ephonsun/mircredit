/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :产品知识库-Service-接口
 * Author     :QianJie
 * Create Date:Nov 12, 2012
 */
package com.banger.mobile.facade.microProduct;

import java.util.List;
import java.util.Map;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.model.microProduct.PdtLibrary;
import com.banger.mobile.domain.model.microProduct.PdtLibraryBean;

import net.sf.json.JSONArray;

/**
 * @author QianJie
 * @version $Id: PdtLibraryService.java,v 0.1 Nov 12, 2012 4:12:37 PM QianJie Exp $
 */
public interface PdtLibraryService {
    /**
     * 根据参数得到所有知识库数据
     * @param conds
     * @return
     */
    public List<PdtLibrary> getAllPdtLibraryByConds(Map<String, Object> conds);
    
    /**
     * 根据libraryId得到所有知识库数据
     * @param libraryId
     * @return
     */
    public List<PdtLibrary> getAllLeafPdtLibraryByLibraryId(int libraryId);
    
    /**
     * 得到知识库分类树Json数据
     * @return
     */
    public JSONArray getAllPdtLibraryTreeJson();
    
    /**
     * 添加产品知识库分类
     * @param pdtLibrary
     * @return
     */
    public int addPdtLibrary(PdtLibrary pdtLibrary);
    
    /**
     * 添加产品知识库(包含主键)
     * @param pdtLibrary
     * @return
     */
    public int addPdtLibraryWithKey(PdtLibrary pdtLibrary);
    
    /**
     * 删除产品知识库分类
     * @param libraryId 知识库ID
     * @param isCascade 是否级联操作
     */
    public void delPdtLibraryById(int libraryId,boolean isCascade);
    
    /**
     * 编辑产品知识库分类
     * @param pdtLibrary
     * @return
     */
    public int editPdtLibrary(PdtLibrary pdtLibrary);
    
    /**
     * 移动产品知识库分类
     * @param sNodeId 原节点ID
     * @param tNodeId 目标节点ID
     * @param userId 修改人
     */
    public void movePdtLibraryNode(int sNodeId, int tNodeId,int userId);
    
    /**
     * 得到单个产品知识库分类数据
     * @param libraryId
     * @return
     */
    public PdtLibrary getPdtLibraryById(int libraryId);
    
    /**
     * 查询产品知识库分类（分页查询）
     * @param conds
     * @param page
     * @return
     */
    public PageUtil<PdtLibraryBean> getPdtLibraryPage(Map<String, Object> conds, Page page);

    /**
     * 得到知识库 NEXT SEQ 值
     * @return
     */
    public int getNextLibraryId();
    
    /**
     * 查询知识库 PAD
     * @param input
     * @return
     */
    public List<PdtLibrary> queryAllLibraryForPad(String input);

    /**
     * 得到附属节点(包括自己)
     * @param libraryId
     * @return
     */
    public List<Integer> getBelongLeafId(int libraryId);


    PageUtil<PdtLibraryBean> getPdtLibraryPageForPad(Map<String, Object> conds, Page page);
}
