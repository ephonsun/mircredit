/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :产品知识库-Action
 * Author     :QianJie
 * Create Date:Nov 12, 2012
 */
package com.banger.mobile.webapp.action.microProduct;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.banger.mobile.domain.model.data.CustomerData;
import com.banger.mobile.facade.param.SysParamService;
import com.banger.mobile.facade.uploadFile.SysUploadFileService;
import net.sf.json.JSONArray;

import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.model.microProduct.PdtLibrary;
import com.banger.mobile.domain.model.microProduct.PdtLibraryAttachment;
import com.banger.mobile.domain.model.microProduct.PdtLibraryBean;
import com.banger.mobile.facade.microProduct.PdtLibraryAttachmentService;
import com.banger.mobile.facade.microProduct.PdtLibraryService;
import com.banger.mobile.util.DateUtil;
import com.banger.mobile.util.FileUtil;
import com.banger.mobile.util.StringUtil;
import com.banger.mobile.webapp.action.BaseAction;
import net.sourceforge.pinyin4j.PinyinHelper;
import org.apache.commons.io.FilenameUtils;

/**
 * @author QianJie
 * @version $Id: PdtLibraryAction.java,v 0.1 Nov 12, 2012 4:53:24 PM QianJie Exp $
 */
public class PdtLibraryAction extends BaseAction {

    private static final long serialVersionUID = -5844900877293674575L;

    private PdtLibraryService pdtLibraryService;                       //产品知识库Service
    private PdtLibraryAttachmentService pdtLibraryAttachmentService;   //产品知识附件Service
    private SysParamService sysParamService;
    private SysUploadFileService sysUploadFileService;

    public void setSysParamService(SysParamService sysParamService) {
        this.sysParamService = sysParamService;
    }

    public void setPdtLibraryService(PdtLibraryService pdtLibraryService) {
        this.pdtLibraryService = pdtLibraryService;
    }

    public void setPdtLibraryAttachmentService(PdtLibraryAttachmentService pdtLibraryAttachmentService) {
        this.pdtLibraryAttachmentService = pdtLibraryAttachmentService;
    }

    private PageUtil<PdtLibraryBean>   pdtLibraryBean;
    private PdtLibrary                 pdtLibrary;              //产品知识库对象
    private PdtLibrary                 pPdtLibrary;             //产品知识库上级分类对象
    private String                     pdtLibraryTreeJson;      //产品知识库Json树数据
    private List<PdtLibraryAttachment> attrList;                //知识附件列表


    /**
     * 附件上传参数
     */
    private static final int             BUFFERED_SIZE    = 4 * 1024;
    private File                         fileInput;
    private String                       fileInputFileName;
    private String                       fileAttachments;//附件集合，多个附件用":"分隔

    public PdtLibrary getPPdtLibrary() {
        return pPdtLibrary;
    }

    public void setPPdtLibrary(PdtLibrary pdtLibrary) {
        pPdtLibrary = pdtLibrary;
    }

    public String getPdtLibraryTreeJson() {
        return pdtLibraryTreeJson;
    }

    public void setPdtLibraryTreeJson(String pdtLibraryTreeJson) {
        this.pdtLibraryTreeJson = pdtLibraryTreeJson;
    }

    public PdtLibrary getPdtLibrary() {
        return pdtLibrary;
    }

    public void setPdtLibrary(PdtLibrary pdtLibrary) {
        this.pdtLibrary = pdtLibrary;
    }

    public PageUtil<PdtLibraryBean> getPdtLibraryBean() {
        return pdtLibraryBean;
    }

    public void setPdtLibraryBean(PageUtil<PdtLibraryBean> pdtLibraryBean) {
        this.pdtLibraryBean = pdtLibraryBean;
    }

    public List<PdtLibraryAttachment> getAttrList() {
        return attrList;
    }

    public void setAttrList(List<PdtLibraryAttachment> attrList) {
        this.attrList = attrList;
    }

    public String getFileAttachments() {
        return fileAttachments;
    }

    public void setFileAttachments(String fileAttachments) {
        this.fileAttachments = fileAttachments;
    }

    public File getFileInput() {
        return fileInput;
    }

    public void setFileInput(File fileInput) {
        this.fileInput = fileInput;
    }

    public String getFileInputFileName() {
        return fileInputFileName;
    }

    public void setFileInputFileName(String fileInputFileName) {
        this.fileInputFileName = fileInputFileName;
    }

    public static int getBUFFERED_SIZE() {
        return BUFFERED_SIZE;
    }

    public void setSysUploadFileService(SysUploadFileService sysUploadFileService) {
        this.sysUploadFileService = sysUploadFileService;
    }

    /**
     * 查询/展示产品知识库列表
     * @return
     */
    public String showPdtLibraryList() {
        try {
            JSONArray jsonArray = pdtLibraryService.getAllPdtLibraryTreeJson();
            pdtLibraryTreeJson = jsonArray.toString();
            return SUCCESS; //返回成功
        } catch (Exception ex) {
            log.error(ex); //记录错误日志
            return ERROR;
        }
    }

    /**
     * 加载知识分类树Json
     * @return
     */
    public void loadPdtLibraryTreeJson() {
        PrintWriter out = null;
        try {
            out = this.getResponse().getWriter();
            JSONArray jsonArray = pdtLibraryService.getAllPdtLibraryTreeJson();
            out.write(jsonArray.toString());
        } catch (Exception ex) {
            out.write("获取知识分类树失败");
            log.error(ex);
        }
    }

    /**
     * 根据参数查询叶子数据
     * @return
     */
    public String queryPdtLibraryList() {
        try {
            Map<String, Object> parameterMap = new HashMap<String, Object>();
            List<Integer> parentIds = pdtLibraryService.getBelongLeafId(pdtLibrary.getParentId());
            if( pdtLibrary.getParentId() != 1){
                parameterMap.put("parentIds",parentIds);
            }else{
                parameterMap.put("parentId", pdtLibrary.getParentId());//0表示查询所有节点下的数据，其他则查当前节点下的数据
            }
            parameterMap.put("libTitle", pdtLibrary.getLibTitle());
            parameterMap.put("libNo", pdtLibrary.getLibNo());
            //parameterMap.put("libContent", pdtLibrary.getLibContent());
            parameterMap.put("createStartDate", repairDateTime(request.getParameter("createStartDate"),"S"));
            parameterMap.put("createEndDate", repairDateTime(request.getParameter("createEndDate"),"E"));

            this.pdtLibraryBean = pdtLibraryService.getPdtLibraryPage(parameterMap, this.getPage());
            int count = this.getPage().getTotalRowsAmount();
            request.setAttribute("count", String.valueOf(count));
            return SUCCESS;
        } catch (Exception ex) {
            log.error(ex); //记录错误日志
            return ERROR;
        }
    }

    private String repairDateTime(Object obj,String repairType){
        String repairDate = "";
        if(obj != null ){
            if(StringUtil.isNotEmpty(obj.toString())){
                if(repairType.equals("S")){
                    repairDate = obj.toString() + " 00:00:00";
                }else{
                    repairDate = obj.toString() + " 23:59:59";
                }
            }
        }
        return repairDate;
    }

    /**
     * 上移/下移产品知识库节点
     */
    public void movePdtLibraryNode() {
        PrintWriter out = null;
        try {
            out = this.getResponse().getWriter();
            String moveNodeIds = request.getParameter("moveNodeIds"); //1-2 2->1
            String[] nodeIds = moveNodeIds.split("-");
            int sNodeId = Integer.parseInt(nodeIds[0]);
            int tNodeId = Integer.parseInt(nodeIds[1]);
            pdtLibraryService.movePdtLibraryNode(sNodeId, tNodeId, this.getLoginInfo().getUserId());//执行移动
            out.write("SUCCESS");
        } catch (Exception ex) {
            out.write("移动节点失败");
            log.error(ex);
        }
    }

    /**
     * 跳转到新增页面
     * @return
     */
    public String toAddPdtLibraryPage() {
        int libraryId = Integer.parseInt(request.getParameter("libraryId"));
        pPdtLibrary = pdtLibraryService.getPdtLibraryById(libraryId);
        return SUCCESS;
    }

    /**
     * 跳转到编辑页面
     * @return
     */
    public String toEditPdtLibraryPage() {
        try {
            int libraryId = Integer.parseInt(request.getParameter("libraryId"));
            int pLibraryId = Integer.parseInt(request.getParameter("pLibraryId"));
            pPdtLibrary = pdtLibraryService.getPdtLibraryById(pLibraryId);
            pdtLibrary = pdtLibraryService.getPdtLibraryById(libraryId);

            return SUCCESS;
        } catch (Exception ex) {
            log.error(ex);
            return ERROR;
        }
    }

    /**
     * 保存产品知识库分类(新增、编辑)
     */
    public void savePdtLibrary() {
        PrintWriter out = null;
        try {
            out = this.getResponse().getWriter();
            String msg = "SUCCESS";
            //新增
            if (pdtLibrary.getLibraryId() == -1) {
                msg = isExistData(pdtLibrary.getLibraryId()); //检测是否存在相同数据
                if (msg.equals("SUCCESS")) {
                    int userId = this.getLoginInfo().getUserId();
                    pdtLibrary.setCreateUser(userId);
                    pdtLibrary.setUpdateUser(userId);
                    pdtLibrary.setLibTitlePinyin(getPinYinHeadChar(pdtLibrary.getLibTitle()));
                    pdtLibraryService.addPdtLibrary(pdtLibrary);
                }
            } else {
                PdtLibrary item = pdtLibraryService.getPdtLibraryById(pdtLibrary.getLibraryId());
                msg = isExistData(pdtLibrary.getLibraryId()); //检测是否存在相同数据
                List<Integer> parentIds = pdtLibraryService.getBelongLeafId(pdtLibrary.getLibraryId());
                for(Integer parentId :parentIds){
                    if (parentId.equals(pdtLibrary.getParentId())){
                        msg = "上级分类"+"为当前分类的子分类，请重新选择" ;
                    }
                }
                if (msg.equals("SUCCESS")) {
                    item.setLibNo(pdtLibrary.getLibNo());
                    item.setLibTitle(pdtLibrary.getLibTitle());
                    item.setRemark(pdtLibrary.getRemark());
                    item.setParentId(pdtLibrary.getParentId());
                    item.setUpdateUser(this.getLoginInfo().getUserId());
                    item.setLibTitlePinyin(getPinYinHeadChar(pdtLibrary.getLibTitle()));
                    pdtLibraryService.editPdtLibrary(item);
                }
            }
            out.write(msg);
        } catch (Exception ex) {
            out.write("保存失败");
            log.error(ex);
        }
    }

    private String isExistData(int libraryId) {
        String msg = "SUCCESS";
        Map<String, Object> conds = new HashMap<String, Object>();
        conds.put("libNo", pdtLibrary.getLibNo());
        conds.put("isLeaf", pdtLibrary.getIsLeaf());
        conds.put("unLibraryId", libraryId);
        List<PdtLibrary> listNo = pdtLibraryService.getAllPdtLibraryByConds(conds);
        if (listNo.size() > 0) {
            msg = "编号为\"" + pdtLibrary.getLibNo() + "\"的知识分类已存在";
        } else {
            conds.clear();
            conds.put("libTitle", pdtLibrary.getLibTitle());
            conds.put("parentId", pdtLibrary.getParentId());
            conds.put("isLeaf", pdtLibrary.getIsLeaf());
            conds.put("unLibraryId", libraryId);
            List<PdtLibrary> listTilte = pdtLibraryService.getAllPdtLibraryByConds(conds);
            if (listTilte.size() > 0) {
                msg = "名称为\"" + pdtLibrary.getLibTitle() + "\"的知识分类已存在";
            }
        }
        return msg;
    }

    /**
     * 删除产品知识库分类
     */
    public void delPdtLibrary() {
        PrintWriter out = null;
        try {
            out = this.getResponse().getWriter();
            int libraryId = Integer.parseInt(request.getParameter("libraryId"));
            List<PdtLibrary> list = pdtLibraryService.getAllLeafPdtLibraryByLibraryId(libraryId);
            if(list.size() > 0){
                out.write("该知识分类有关联的知识信息，不能删除");
            }else{
                pdtLibraryService.delPdtLibraryById(libraryId, true);
                out.write("SUCCESS");
            }
        } catch (Exception ex) {
            out.write("删除失败");
            log.error(ex);
        }
    }

    /**
     * 删除产品知识库叶子
     */
    public void delLeafPdtLibrary() {
        PrintWriter out = null;
        try {
            out = this.getResponse().getWriter();
            int libraryId = Integer.parseInt(request.getParameter("libraryId"));
            pdtLibraryService.delPdtLibraryById(libraryId, false);

//            //删除附件
//            Map<String, Object> conds = new HashMap<String, Object>();
//            conds.put("libraryId", libraryId);
//            pdtLibraryAttachmentService.delPdtLibraryAttachmentByConds(conds);
            out.write("SUCCESS");
        } catch (Exception ex) {
            out.write("删除失败");
            log.error(ex);
        }
    }

    /**
     * 跳转到新增叶子页面
     * @return
     */
    public String toAddLeafPdtLibraryPage() {
        int libraryId = Integer.parseInt(request.getParameter("libraryId"));
        pPdtLibrary = pdtLibraryService.getPdtLibraryById(libraryId);
        return SUCCESS;
    }

    /**
     * 跳转到编辑叶子页面
     * @return
     */
    public String toEditLeafPdtLibraryPage() {
        try {
            int libraryId = Integer.parseInt(request.getParameter("libraryId"));
            //int pLibraryId = Integer.parseInt(request.getParameter("pLibraryId"));
            pdtLibrary = pdtLibraryService.getPdtLibraryById(libraryId);
            pPdtLibrary = pdtLibraryService.getPdtLibraryById(pdtLibrary.getParentId());

            Map<String, Object> conds = new HashMap<String, Object>();
            conds.put("libraryId", libraryId);
            attrList = pdtLibraryAttachmentService.getAllPdtLibraryAttachmentByConds(conds);
            return SUCCESS;
        } catch (Exception ex) {
            log.error(ex);
            return ERROR;
        }
    }

    /**
     * 跳转到编辑叶子页面
     * @return
     */
    public String toViewLeafPdtLibraryPage() {
        try {
            int libraryId = Integer.parseInt(request.getParameter("libraryId"));
            //int pLibraryId = Integer.parseInt(request.getParameter("pLibraryId"));
            pdtLibrary = pdtLibraryService.getPdtLibraryById(libraryId);
            pPdtLibrary = pdtLibraryService.getPdtLibraryById(pdtLibrary.getParentId());

            Map<String, Object> conds = new HashMap<String, Object>();
            conds.put("libraryId", libraryId);
            attrList = pdtLibraryAttachmentService.getAllPdtLibraryAttachmentByConds(conds);
            return SUCCESS;
        } catch (Exception ex) {
            log.error(ex);
            return ERROR;
        }
    }

    /**
     * 验证产品知识库分类数据(新增、编辑)
     */
    public void verifyLeafPdtLibrary(){
        PrintWriter out = null;
        try {
            out = this.getResponse().getWriter();
            String msg = "SUCCESS";
            Map<String, Object> conds = new HashMap<String, Object>();
            conds.put("libNo", pdtLibrary.getLibNo());
            conds.put("isLeaf", pdtLibrary.getIsLeaf());

            Map<String, Object> param = new HashMap<String, Object>();
            param.put("libTitle", pdtLibrary.getLibTitle());
            param.put("isLeaf", pdtLibrary.getIsLeaf());

            PdtLibrary item = pdtLibraryService.getPdtLibraryById(pdtLibrary.getLibraryId());
            //新增
            if (item == null) {
                List<PdtLibrary> listNo = pdtLibraryService.getAllPdtLibraryByConds(conds);
                if (listNo.size() > 0) {
                    msg = "编号为\"" + pdtLibrary.getLibNo() + "\"的知识已存在";
                }
                List<PdtLibrary> listName = pdtLibraryService.getAllPdtLibraryByConds(param);
                if(listName.size() >0 ){
                    msg = "标题为\"" + pdtLibrary.getLibTitle() + "\"的知识已存在";
                }
            } else {
                String oldTitle=request.getParameter("oldTxtLibTitle");
                if(!pdtLibrary.getLibTitle().equals(oldTitle)){
                    List<PdtLibrary> listName = pdtLibraryService.getAllPdtLibraryByConds(param);
                    if(listName.size() >0 ){
                        msg = "标题为\"" + pdtLibrary.getLibTitle() + "\"的知识已存在";
                    }
                }
            }

            out.write(msg);
        } catch (Exception ex) {
            out.write("未知错误");
            log.error(ex);
        }
    }


    /**
     * 保存产品知识库分类(新增、编辑)
     */
    public void saveLeafPdtLibrary() {
        PrintWriter out = null;
        try {
            out = this.getResponse().getWriter();
            String msg = "SUCCESS";
            String libTitle=pdtLibrary.getLibTitle().replaceAll("\\s*", "");
            Map<String, Object> conds = new HashMap<String, Object>();
            conds.put("libNo", pdtLibrary.getLibNo());
            conds.put("isLeaf", pdtLibrary.getIsLeaf());

            Map<String, Object> param = new HashMap<String, Object>();
            param.put("libTitle", libTitle);
            param.put("isLeaf", pdtLibrary.getIsLeaf());

            int userId = this.getLoginInfo().getUserId();
            PdtLibrary item = pdtLibraryService.getPdtLibraryById(pdtLibrary.getLibraryId());
            //新增
            if (item == null) {
                List<PdtLibrary> listNo = pdtLibraryService.getAllPdtLibraryByConds(conds);
                if (listNo.size() > 0) {
                    msg = "编号为\"" + pdtLibrary.getLibNo() + "\"的知识已存在";
                }
                List<PdtLibrary> listName = pdtLibraryService.getAllPdtLibraryByConds(param);
                if(listName.size() >0 ){
                    msg = "标题为\"" + pdtLibrary.getLibTitle() + "\"的知识已存在";
                }
                if (msg.equals("SUCCESS")) {
                    pdtLibrary.setCreateUser(userId);
                    pdtLibrary.setUpdateUser(userId);
                    pdtLibrary.setLibTitle(libTitle);
                    pdtLibrary.setLibTitlePinyin(getPinYinHeadChar(libTitle));
                    int attLibraryId = pdtLibraryService.addPdtLibrary(pdtLibrary);

                    addAttachments(attLibraryId,userId);//保存附件数据到数据库
                }
            } else {
//                conds.put("unLibraryId", pdtLibrary.getLibraryId());
//                List<PdtLibrary> listNo = pdtLibraryService.getAllPdtLibraryByConds(conds);
//                if (listNo.size() > 0) {
//                    msg = "编号为\"" + pdtLibrary.getLibNo() + "\"的知识已存在";
//                }
                if (msg.equals("SUCCESS")) {
                    item.setLibNo(pdtLibrary.getLibNo());
                    item.setLibTitle(libTitle);
                    item.setLibTitlePinyin(getPinYinHeadChar(libTitle));
                    item.setLibContent(pdtLibrary.getLibContent());
                    item.setParentId(pdtLibrary.getParentId());
                    pdtLibraryService.editPdtLibrary(item);

                    addAttachments(item.getLibraryId(),userId);//保存附件数据到数据库
                }
            }
            out.write(msg);
        } catch (Exception ex) {
            out.write("保存失败");
            log.error(ex);
        }
    }

    /**
     * 保存附件数据到数据库
     * @param attLibraryId
     * @param userId
     */
    private void addAttachments(int attLibraryId, int userId){
        if (!StringUtil.isNullOrEmpty(fileAttachments)) {
            //保存附件
            String[] attItems = fileAttachments.split(":");// "\\|"
            for (String atts : attItems) {
                if (!StringUtil.isNullOrEmpty(atts)) {
                    String[] att = atts.split("\\|");
                    //添加附件到数据库
                    PdtLibraryAttachment attachment = new PdtLibraryAttachment();
                    attachment.setLibraryId(attLibraryId);
                    attachment.setCreateUser(userId);
                    attachment.setUpdateUser(userId);
                    attachment.setFileId(Integer.parseInt(att[2]));
                    pdtLibraryAttachmentService.addPdtLibraryAttachment(attachment);
                }
            }
        }
    }

    /**
     * 上传知识附件
     * @throws IOException
     */
    public void uploadPdtLibraryAttachment() throws IOException {
        PrintWriter out = null;

        //try {
        out = this.getResponse().getWriter();
        Integer userId = this.getLoginInfo().getUserId();

        if (fileInputFileName != null && !fileInputFileName.equals("")) {
            CustomerData data = new CustomerData();
            data.setCreateUser(userId);
            Integer fileId = sysUploadFileService.saveFile(fileInput,fileInputFileName,userId, true,"",data);

            //返回成功信息
            out.write(fileId.toString());
        }
    }


    /**
     * 下载知识附件
     */
    public void downloadPdtLibraryAttachment() {
        Map<String, Object> conds = new HashMap<String, Object>();
        int libraryAttachmentId = Integer.parseInt(request.getParameter("libraryAttachmentId"));
        conds.put("libraryAttachmentId", libraryAttachmentId);
        List<PdtLibraryAttachment> atts = pdtLibraryAttachmentService
                .getPdtLibraryAttachmentByConds(conds);
        Integer fileId = atts.get(0).getFileId();
        CustomerData data = new CustomerData();
        data.setCreateUser(this.getLoginInfo().getUserId());
        sysUploadFileService.downFile(fileId,data);
    }


    /**
     * 删除知识附件
     */
    public void delPdtLibraryAttachment() {
        PrintWriter out = null;
        try {
            out = this.getResponse().getWriter();
            Map<String, Object> conds = new HashMap<String, Object>();
            int libraryAttachmentId = Integer.parseInt(request.getParameter("libraryAttachmentId"));
            conds.put("libraryAttachmentId", libraryAttachmentId);
            Integer fileId = pdtLibraryAttachmentService.getPdtLibraryAttachmentByConds(conds).get(0).getFileId();
            pdtLibraryAttachmentService.delPdtLibraryAttachmentByConds(conds,fileId);
            out.write("Success");
        } catch (Exception ex) {
            out.write("删除失败");
            log.error(ex);
        }
    }

    /**
     * 提取每个汉字的首字母
     */
    public String getPinYinHeadChar(String str) {
        String convert = "";
        for (int j = 0; j < str.length(); j++) {
            char word = str.charAt(j);
            //提取汉字的首字母
            String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(word);
            if (pinyinArray != null) {
                convert += pinyinArray[0].charAt(0);
            } else {
                convert += word;
            }
        }
        return convert;
    }


}
