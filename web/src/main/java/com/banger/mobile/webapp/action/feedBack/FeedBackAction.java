package com.banger.mobile.webapp.action.feedBack;

import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.model.feedback.FeedBack;
import com.banger.mobile.facade.feedBack.FeedBackService;
import com.banger.mobile.webapp.action.BaseAction;
import org.apache.log4j.Logger;

import javax.servlet.ServletOutputStream;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by BH-TCL on 15-3-9.
 */
public class FeedBackAction extends BaseAction {
    private static Logger logger = Logger.getLogger(FeedBackAction.class);
    private FeedBackService feedBackService;
    private PageUtil<FeedBack> feedBackList;

    private String titleName;
    private String modular;
    private String createUser;
    private Integer status=0;//默认为0
    private String fileUrl;
    private int                    totalAmount;                                     //总记录数
    private int ischuli=0;//处理 0 不处理 1处理
    private Integer feedBackId;


    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getCreateUser() {
        return createUser;
    }

    public Integer getFeedBackId() {
        return feedBackId;
    }

    public void setFeedBackId(Integer feedBackId) {
        this.feedBackId = feedBackId;
    }

    public int getIschuli() {
        return ischuli;
    }

    public void setIschuli(int ischuli) {
        this.ischuli = ischuli;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Integer getStatus() {
        return status;
    }

    public String getModular() {
        return modular;
    }

    public String getTitleName() {
        return titleName;
    }

    public void setTitleName(String titleName) {
        this.titleName = titleName;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setModular(String modular) {
        this.modular = modular;
    }

    public void setFeedBackList(PageUtil<FeedBack> feedBackList) {
        this.feedBackList = feedBackList;
    }

    public PageUtil<FeedBack> getFeedBackList() {
        return feedBackList;
    }

    public void setFeedBackService(FeedBackService feedBackService) {
        this.feedBackService = feedBackService;
    }

    public String showFeedBackList(){
        try{
            Map<String,Object> map = new HashMap<String, Object>();
            map.put("titleName",titleName);
            map.put("modular",modular);
            map.put("createUser",createUser);
            map.put("status",status);
            //处理
            if(ischuli==1){
                FeedBack feedBack=new FeedBack();
                feedBack.setFeedBackId(feedBackId);
                feedBack.setStatus(1);
                feedBackService.updateFeedBack(feedBack);
                ischuli=0;
            }
            feedBackList=feedBackService.queryFeedBacksPage(map,this.getPage());
            totalAmount=this.getPage().getTotalRowsAmount();
            return "success";
        }catch (Exception e){
            log.error("FeedBackAction % showFeedBackList",e);
            return "error";
        }
    }

    public void downFeedBackFile(){
        try{
           synchronized("downFeedBackFile"){
            File downFile=new File(fileUrl);
            FileInputStream fis = new FileInputStream(downFile);// 服务器文件路径
            ServletOutputStream output = this.getResponse().getOutputStream();
            this.getResponse().addHeader("Content-Disposition", "attachment;filename="
                    + new String(downFile.getName().getBytes("gbk"),
                    "iso8859-1"));
            BufferedInputStream bis = new BufferedInputStream(fis);// 输入缓冲流
            BufferedOutputStream bos = new BufferedOutputStream(output);// 输出缓冲流

            byte buff[] = new byte[4096];// 缓冲字节数

            int bytesRead;
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, bytesRead);
            }
            if (bis != null){
                bis.close();
            }
            if (bos != null){
                bos.flush();// 清空输出缓冲流
                bos.close();
            }
            if (output != null){
                output.close();
            }
        }
        }catch (Exception e){
            logger.error("FeedBackAction % getFile",e);
        }
    }
}
