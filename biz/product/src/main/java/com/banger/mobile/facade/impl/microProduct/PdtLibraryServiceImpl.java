/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :产品知识库-Service-接口实现
 * Author     :QianJie
 * Create Date:Nov 12, 2012
 */
package com.banger.mobile.facade.impl.microProduct;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.dao.microProduct.PdtLibraryDao;
import com.banger.mobile.domain.model.microProduct.PdtLibrary;
import com.banger.mobile.domain.model.microProduct.PdtLibraryBean;
import com.banger.mobile.facade.microProduct.PdtLibraryService;

/**
 * @author QianJie
 * @version $Id: PdtLibraryServiceImpl.java,v 0.1 Nov 12, 2012 4:12:37 PM QianJie Exp $
 */
public class PdtLibraryServiceImpl implements PdtLibraryService {

    private PdtLibraryDao pdtLibraryDao;

    public void setPdtLibraryDao(PdtLibraryDao pdtLibraryDao) {
        this.pdtLibraryDao = pdtLibraryDao;
    }

    /**
     * 根据参数得到所有知识库数据
     * @param conds
     * @return
     * @see com.banger.mobile.facade.product.PdtLibraryService#getAllPdtLibraryByConds(java.util.Map)
     */
    public List<PdtLibrary> getAllPdtLibraryByConds(Map<String, Object> conds){
        return pdtLibraryDao.getAllPdtLibraryByConds(conds);
    }

    /**
     * 根据libraryId得到所有知识库数据
     * @param libraryId
     * @return
     * @see com.banger.mobile.facade.product.PdtLibraryService#getAllLeafPdtLibraryByLibraryId(int)
     */
    public List<PdtLibrary> getAllLeafPdtLibraryByLibraryId(int libraryId){
        Map<String, Object> conds = new HashMap<String, Object>();
        conds.put("isLeaf", 0);//0:树枝节点  1:叶子节点
        List<PdtLibrary> list = pdtLibraryDao.getAllPdtLibraryByConds(conds); //取出所有数据
        String libraryIds = libraryId + ",";
        libraryIds = getLibraryIds(libraryId, libraryIds, list); //递归得到当前分类的子分类
        libraryIds = libraryIds.substring(0, (libraryIds.length() - 1)); //删除最后一个逗号
        conds.clear();
        conds.put("parentIds", libraryIds);
        conds.put("isLeaf", 1);//0:树枝节点  1:叶子节点
        return pdtLibraryDao.getAllPdtLibraryByConds(conds); //取出所有叶子数据
    }

    /**
     * 得到知识库分类树Json数据
     * @return
     * @see com.banger.mobile.facade.product.PdtLibraryService#getAllPdtLibraryTreeJson()
     */
    public JSONArray getAllPdtLibraryTreeJson() {
        Map<String, Object> map = new HashMap<String, Object>();
        JSONArray jsonArray = new JSONArray();

        Map<String, Object> conds = new HashMap<String, Object>();
        conds.put("isLeaf", 0);//0:树枝节点  1:叶子节点
        List<PdtLibrary> list = pdtLibraryDao.getAllPdtLibraryByConds(conds); //取出所有树枝节点

        for (PdtLibrary pdtLibrary : list) {
            map.put("id", pdtLibrary.getLibraryId());
            map.put("pId", pdtLibrary.getParentId());
            map.put("name", pdtLibrary.getLibTitle());
            if (hasChildNode(list, pdtLibrary.getLibraryId())) {
                map.put("open", true);
            }
            jsonArray.add(map);
        }
        return jsonArray;
    }

    /**
     * 检测某节点是否包含子节点
     * @param list
     * @param libraryId
     * @return
     */
    private boolean hasChildNode(List<PdtLibrary> list, int libraryId) {
        boolean result = false;
        for (PdtLibrary pdtLibrary : list) {
            if (pdtLibrary.getParentId() == libraryId) {
                result = true;
                break;
            }
        }
        return result;
    }

    /**
     * 添加产品知识库分类
     * @param pdtLibrary
     * @return
     * @see com.banger.mobile.facade.product.PdtLibraryService#addPdtLibrary(com.banger.mobile.domain.model.product.PdtLibrary)
     */
    public int addPdtLibrary(PdtLibrary pdtLibrary) {
        return pdtLibraryDao.addPdtLibrary(pdtLibrary);
    }

    /**
     * 添加产品知识库(包含主键)
     * @param pdtLibrary
     * @return
     * @see com.banger.mobile.facade.product.PdtLibraryService#addPdtLibraryWithKey(com.banger.mobile.domain.model.product.PdtLibrary)
     */
    public int addPdtLibraryWithKey(PdtLibrary pdtLibrary) {
        return pdtLibraryDao.addPdtLibraryWithKey(pdtLibrary);
    }

    /**
     * 删除产品知识库分类
     * @param libraryId 知识库ID
     * @param isCascade 是否级联操作
     * @see com.banger.mobile.facade.product.PdtLibraryService#delPdtLibraryById(int, boolean)
     */
    public void delPdtLibraryById(int libraryId, boolean isCascade) {
        if (isCascade) {
            Map<String, Object> conds = new HashMap<String, Object>();
            List<PdtLibrary> list = pdtLibraryDao.getAllPdtLibraryByConds(conds); //取出所有数据
            String libraryIds = libraryId + ",";
            libraryIds = getLibraryIds(libraryId, libraryIds, list); //递归得到当前分类的子分类
            libraryIds = libraryIds.substring(0, (libraryIds.length() - 1)); //删除最后一个逗号
            pdtLibraryDao.delPdtLibraryByIds(libraryIds);//执行删除
        } else {
            pdtLibraryDao.delPdtLibraryById(libraryId);
        }
    }

    /**
     * 递归得到当前知识库分类节点的所有子节点IDS
     * @param libraryId
     * @param libraryIds
     * @param list
     * @return
     */
    private String getLibraryIds(int libraryId, String libraryIds, List<PdtLibrary> list) {
        for (PdtLibrary item : list) {
            if (item.getParentId() == libraryId) {
                libraryIds += item.getLibraryId() + ",";
                libraryIds = getLibraryIds(item.getLibraryId(), libraryIds, list);
            }
        }
        return libraryIds;
    }

    /**
     * 编辑产品知识库分类
     * @param pdtLibrary
     * @return
     * @see com.banger.mobile.facade.product.PdtLibraryService#editPdtLibrary(com.banger.mobile.domain.model.product.PdtLibrary)
     */
    public int editPdtLibrary(PdtLibrary pdtLibrary) {
        return pdtLibraryDao.editPdtLibrary(pdtLibrary);
    }

    /**
     * 得到单个产品知识库分类数据
     * @param libraryId
     * @return
     * @see com.banger.mobile.facade.product.PdtLibraryService#getPdtLibraryById(int)
     */
    public PdtLibrary getPdtLibraryById(int libraryId) {
        return pdtLibraryDao.getPdtLibraryById(libraryId);
    }

    /**
     * 查询产品知识库分类（分页查询）
     * @param conds
     * @param page
     * @return
     * @see com.banger.mobile.facade.product.PdtLibraryService#getPdtLibraryPage(java.util.Map, com.banger.mobile.Page)
     */
    public PageUtil<PdtLibraryBean> getPdtLibraryPage(Map<String, Object> conds, Page page) {
        return pdtLibraryDao.getPdtLibraryPage(conds, page);
    }

    public PageUtil<PdtLibraryBean> getPdtLibraryPageForPad(Map<String, Object> conds, Page page) {
        return pdtLibraryDao.getPdtLibraryPageForPad(conds, page);
    }

    /**
     * 得到知识库 NEXT SEQ 值
     * @return
     * @see com.banger.mobile.facade.product.PdtLibraryService#getNextLibraryId()
     */
    public int getNextLibraryId() {
        return pdtLibraryDao.getNextLibraryId();
    }

    /**
     * 移动产品知识库分类
     * @param sNodeId 原节点ID
     * @param tNodeId 目标节点ID
     * @param userId 修改人
     * @see com.banger.mobile.facade.product.PdtLibraryService#movePdtLibraryNode(int, int)
     */
    public void movePdtLibraryNode(int sNodeId, int tNodeId, int userId) {
        PdtLibrary sNode = getPdtLibraryById(sNodeId);
        PdtLibrary tNode = getPdtLibraryById(tNodeId);

        if (sNode != null && tNode != null) {
            int sNodeSortNo = sNode.getSortNo();
            sNode.setSortNo(tNode.getSortNo());
            tNode.setSortNo(sNodeSortNo);

            sNode.setUpdateUser(userId);
            tNode.setUpdateUser(userId);
            editPdtLibrary(sNode);//执行排序位置交换
            editPdtLibrary(tNode);//执行排序位置交换
        }
    }

    /**
     * 查询知识库 PAD
     * @param input
     * @return
     * @see com.banger.mobile.facade.product.PdtLibraryService#queryAllLibraryForPad(java.lang.String)
     */
    public List<PdtLibrary> queryAllLibraryForPad(String input) {
        return pdtLibraryDao.queryAllLibraryForPad(input);
    }

    /**
     * 得到附属节点(包括自己)
     * @param libraryId
     * @return
     */
    public List<Integer> getBelongLeafId(int libraryId){
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("parentId",libraryId);
        param.put("isLeaf",0);
        List<PdtLibrary> firstPdtLibrary = pdtLibraryDao.getAllPdtLibraryByConds(param);
        List resultList = new ArrayList();
        resultList.add(libraryId);
        for(PdtLibrary firstBelong :firstPdtLibrary){
            resultList.add(firstBelong.getLibraryId());
            param.put("parentId",firstBelong.getLibraryId());
            List<PdtLibrary> secondPdtLibrary = pdtLibraryDao.getAllPdtLibraryByConds(param);
            for(PdtLibrary secondBelong :secondPdtLibrary){
                resultList.add(secondBelong.getLibraryId());
                param.put("parentId",secondBelong.getLibraryId());
                List<PdtLibrary> thirdPdtLibrary = pdtLibraryDao.getAllPdtLibraryByConds(param);
                for(PdtLibrary thirdBelong :thirdPdtLibrary){
                    resultList.add(thirdBelong.getLibraryId());
                }
            }
        }
        return resultList;
    }

}
