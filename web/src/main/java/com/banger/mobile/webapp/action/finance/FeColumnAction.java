package com.banger.mobile.webapp.action.finance;

import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.banger.mobile.domain.model.finance.FeColumn;
import com.banger.mobile.facade.finance.FeArticleService;
import com.banger.mobile.facade.finance.FeColumnService;
import com.banger.mobile.webapp.action.BaseAction;

public class FeColumnAction extends BaseAction {
    private static final long serialVersionUID = 8744380946162558638L;

    private FeColumnService   feColumnService;
    private FeColumn          feColumn;
    private List<FeColumn>    feColumnList;
    private String            columnId;
    private FeArticleService  feArticleService;

    //  获取所有栏目(可以根据栏目名搜索)
    public String getAllColumn() {
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            if (feColumn != null) {
    
                map.put("columnName", feColumn.getColumnName());
                feColumnList = feColumnService.getAllColumnList(map);
                setFeColumnList(feColumnList);
    
            } else {
                feColumnList = feColumnService.getAllColumnList(map);
                setFeColumnList(feColumnList);
            }
        }catch(Exception e){
            e.printStackTrace();
            log.debug("getAllColumn action error:"+e.getMessage());
        }
        return SUCCESS;
    }

    //   搜索刷新tab
    public String refreshTab() {
        Map<String, Object> map = new HashMap<String, Object>();
        if (feColumn != null) {
            map.put("columnName", feColumn.getColumnName());
            feColumnList = feColumnService.getAllColumnList(map);
            setFeColumnList(feColumnList);
        }
        return SUCCESS;
    }

    //    跳转到新建栏目
    public String toAddColumn() {
        return SUCCESS;
    }

    //验证用户名是否存在
    public boolean validatorColumnName(){
        boolean returnData = false;
        try{
            FeColumn fe = feColumnService.getColumn(feColumn);

            if(fe!=null){
                returnData = false;
            }else{
                returnData = true;
            }
        }catch(Exception e){
            e.printStackTrace();
            log.debug("validatorColumnName action error:"+e.getMessage());
        }
        return returnData;
    }
    
    
    //   新建栏目
    public void addColumn() {
        //  HttpServletResponse response = ServletActionContext.getResponse();
        //response.setContentType("text/html;charset=utf-8");

        try {
            //   PrintWriter out = response.getWriter();
            if (feColumn != null) {
                boolean boolColumnName = validatorColumnName();
                
                if(boolColumnName){
                    feColumn.setIsDel(0);
                    feColumn.setIsStart(1);
                    feColumn.setCreateDate(new Date());
                    feColumn.setUpdateDate(new Date());
                    feColumn.setCreateUser(this.getLoginInfo().getUserId());
                    feColumn.setUpdateUser(this.getLoginInfo().getUserId());
                    feColumnService.addColumn(feColumn);
                }else{
                    HttpServletResponse response = ServletActionContext.getResponse();
                    response.reset();
                    response.setContentType("text/html;charset=utf-8");
                    PrintWriter out = response.getWriter();
                    
                    out.print(boolColumnName);
                    out.close();
                }
               
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            log.debug("addColumn action error:" + e.getMessage());
        }

    }

    //    保存并新建栏目
    public void addAndSave() {
        try{
            if (feColumn != null) {
                boolean boolColumnName = validatorColumnName();
                if(boolColumnName){
                    feColumn.setIsDel(0);
                    feColumn.setIsStart(1);
                    feColumn.setCreateDate(new Date());
                    feColumn.setUpdateDate(new Date());
                    feColumn.setCreateUser(this.getLoginInfo().getUserId());
                    feColumn.setUpdateUser(this.getLoginInfo().getUserId());
                    feColumnService.addColumn(feColumn);
                }else{
                    HttpServletResponse response = ServletActionContext.getResponse();
                    response.reset();
                    response.setContentType("text/html;charset=utf-8");
                    PrintWriter out = response.getWriter();
                    
                    out.print(boolColumnName);
                    out.close();
                }
            }
        }catch(Exception e){
            e.printStackTrace();
            log.debug("addAndSave action error:"+e.getMessage());
        }
    }

    //    跳转到编辑栏目
    public String toEditColumn() {
        try{
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("columnId", Integer.parseInt(columnId));
            List<FeColumn> list = feColumnService.getAllColumnList(map);

            if (list.size() != 0) {
                feColumn = list.get(0);
                setFeColumn(feColumn);
            }
            return SUCCESS;
        }catch(Exception e){
            e.printStackTrace();
            log.debug("toEditColumn action error:"+e.getMessage());
            return ERROR;
        }
        
    }

    //    编辑栏目
    public void editColumn() {
      try{
          if (feColumn != null) {
              boolean boolColumnName = validatorColumnName();
              if(boolColumnName){
                  feColumnService.editColumn(feColumn);
              }else{
                  HttpServletResponse response = ServletActionContext.getResponse();
                  response.reset();
                  response.setContentType("text/html;charset=utf-8");
                  PrintWriter out = response.getWriter();
                  
                  out.print(boolColumnName);
                  out.close();
              }
             
          }
      }catch(Exception e){
          e.printStackTrace();
          log.debug("editColumn action error:"+e.getMessage());
      }
    }

    //    删除栏目
    public void delColumn() {
      try{
          if (feColumn != null) {
              HttpServletResponse response = ServletActionContext.getResponse();
              response.reset();
              response.setContentType("text/html;charset=utf-8");
              PrintWriter out = response.getWriter();
              
              Map<String,Object> map = new HashMap<String,Object>();
              map.put("articleColumnId", feColumn.getColumnId());
              int count = feArticleService.getArticleNum(map);
              
              if(count==0){
                  feColumnService.delColumn(feColumn.getColumnId());
                  out.print(0);
              }else{
                  out.print(1);
              }
              
          }
      }catch(Exception e){
          e.printStackTrace();
          log.debug("delColumn action error:"+e.getMessage());
      }
    }

    //    启用/停用栏目
    public String ssColumn() {
        try{
            if(feColumn!=null){
                
                int states = feColumn.getIsStart();
                //启用
                if(states==0){
                    feColumnService.startColumn(feColumn.getColumnId());                    
                    showAllColumnList();
                }
                //停用
                else if(states==1){
                    feColumnService.stopColumn(feColumn.getColumnId());                    
                    showAllColumnList();
                }
            }
        }catch(Exception e){
            e.printStackTrace();
            log.debug("ssColumn action error:"+e.getMessage());
        }
        return SUCCESS;
    }
    //    显示所有栏目
    public String showAllColumnList(){
        try{
            Map<String,Object> map = new HashMap<String,Object>();
            
            feColumnList = feColumnService.getAllColumnList(map);
            return SUCCESS;
        }catch(Exception e){
            e.printStackTrace();
            return ERROR;            
        }        
    }

    //    上移栏目
    public String upColumn() {
        try{
            if(feColumn!=null){                
                feColumnService.upColumn(feColumn.getColumnId());                
                showAllColumnList();
            }
        }catch(Exception e){
            e.printStackTrace();
            log.debug("upColumn action error:"+e.getMessage());
        }
        
         return SUCCESS;
    }

    //    下移栏目
    public String downColumn() {
      try{
          if(feColumn!=null){
              feColumnService.downColumn(feColumn.getColumnId());
              showAllColumnList();
          }
      }catch(Exception e){
          e.printStackTrace();
          log.debug("downColumn action error:"+e.getMessage());
      }
        return SUCCESS;
    }
    
    
    
    public FeColumn getFeColumn() {
        return feColumn;
    }

    public void setFeColumn(FeColumn feColumn) {
        this.feColumn = feColumn;
    }

    public List<FeColumn> getFeColumnList() {
        return feColumnList;
    }

    public void setFeColumnList(List<FeColumn> feColumnList) {
        this.feColumnList = feColumnList;
    }

    public FeColumnService getFeColumnService() {
        return feColumnService;
    }

    public void setFeColumnService(FeColumnService feColumnService) {
        this.feColumnService = feColumnService;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getColumnId() {
        return columnId;
    }

    public void setColumnId(String columnId) {
        this.columnId = columnId;
    }
    
    public FeArticleService getFeArticleService() {
        return feArticleService;
    }

    public void setFeArticleService(FeArticleService feArticleService) {
        this.feArticleService = feArticleService;
    }
}
