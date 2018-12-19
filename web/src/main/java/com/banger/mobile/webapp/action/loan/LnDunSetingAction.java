/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :催收配置action
 * Author     :xuhj
 * Version    :1.0
 * Create Date:May 3, 2013
 */
package com.banger.mobile.webapp.action.loan;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.banger.mobile.domain.model.loan.LnDunSeting;
import com.banger.mobile.facade.loan.LnDunSetingService;
import com.banger.mobile.facade.param.SysParamService;
import com.banger.mobile.util.FileType;
import com.banger.mobile.webapp.action.BaseAction;

public class LnDunSetingAction extends BaseAction {

	private static final long serialVersionUID = 2564789201132135893L;
	private static final int    BUFFERED_SIZE    = 10 * 1024;
	
	private LnDunSetingService lnDunSetingService;
	private LnDunSeting lnDunSeting;
	private LnDunSeting lnDunSetinglimit;
	private SysParamService     sysParamService;
	private File recordFile;
	private File recordFileEnd;

	public LnDunSetingAction(){
		this.lnDunSeting = new LnDunSeting();
		this.lnDunSetinglimit = new LnDunSeting();
	}
	
	public String showLnDunSetingPage(){
		try {
			lnDunSeting = lnDunSetingService.getLnDunSeting(1);
			lnDunSetinglimit = lnDunSetingService.getLnDunSeting(2);


			return SUCCESS;
		} catch (Exception e) {
			return ERROR;
		}
		
	}
	
	public String checkAccount() throws IOException{
		HttpServletResponse response = ServletActionContext.getResponse();
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        String account = request.getParameter("Account");
        String passWord = request.getParameter("PassWord");
        Map<String, Object> resultMap = new HashMap<String, Object>();

        JSONArray jsonArray = new JSONArray();
		jsonArray = JSONArray.fromObject(resultMap);
		out.print(jsonArray);
		return null;
	}
	
	public void saveLnDunSeting() throws IOException{
		HttpServletResponse response = ServletActionContext.getResponse();
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
		try {
			saveExpiredRemind(lnDunSeting, 1, 1);
			saveExpiredRemind(lnDunSetinglimit, 2, 2);
			out.print("SUCCESS");
		} catch (Exception e) {
			e.printStackTrace();
            log.error("saveLnDunSeting action error:" + e.getMessage());
            out.print(e.getMessage());
		}
	}
	
	private void saveExpiredRemind(LnDunSeting seting, Integer id, Integer flag) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		Date dd = sdf.parse("00:00:00");
		seting.setDunSetingId(id);
		seting.setFlag(flag);
		if(seting!=null&&lnDunSeting.getIsActived()==null){
			seting.setIsActived(0);
			seting.setDunType(1);
			seting.setDunVoiceFile("");
			seting.setDunTime(dd);
			seting.setSendTime(dd);
			seting.setAccount("");
			seting.setPassWord("");
			seting.setSmsContent("");
		}else{
			if(seting.getDunType().equals(1)){
				seting.setSmsContent("");
				seting.setSendTime(dd);
				seting.setAccount("");
				seting.setPassWord("");
			}else{
				seting.setDunVoiceFile("");
				seting.setDunTime(dd);
			}
		}
		lnDunSetingService.save(seting);
	}
	
	public String upLoadLnRecord(){
	    String type = FileType.getFileByFile(recordFile);
	    Map<String, String> map = new HashMap<String, String>();
	    try {
	        HttpServletResponse response = ServletActionContext.getResponse();
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html;charset=UTF-8"); 
            PrintWriter out = response.getWriter();
            
            if(type==null||!type.toLowerCase().equals("wav")){
                map.put("error", "目前只支持wav文件格式上传！");
                out.append(JSONObject.fromObject(map).toString());
                return null;
            }
            if(recordFile.length()>BUFFERED_SIZE*1024){
                map.put("error", "目前不支持超过10M的头像上传！");
                out.append(JSONObject.fromObject(map).toString());
                return null;
            }
            String savePath = sysParamService.getUserPhonePath();
            File file = new File(savePath);
            if (!file.exists()) {// 目录不存在则创建
                file.mkdirs();
            }
            String fileName = URLDecoder.decode(request.getParameter("fileName"), "utf-8");
            savePath = savePath + "/" + fileName;
            File refile = new File(savePath);
            copy(recordFile, refile);
            map.put("fileName", fileName);
            out.append(JSONObject.fromObject(map).toString());
        } catch (Exception e) {
            try{
                HttpServletResponse response = ServletActionContext.getResponse();
                response.setCharacterEncoding("UTF-8");
                PrintWriter out = response.getWriter();
                map.put("error", "上传失败");
                out.append(JSONObject.fromObject(map).toString());
            }catch (IOException e1) {
                e1.printStackTrace();
            }
        }
	    return null;
	}
	/**
     * 复制文件
     * @param src
     * @param target
     */
    private void copy(File src, File target) {
        InputStream in = null;
        OutputStream out = null;
        try {
            in = new BufferedInputStream(new FileInputStream(src), BUFFERED_SIZE);
            out = new BufferedOutputStream(new FileOutputStream(target), BUFFERED_SIZE);
            byte[] bs = new byte[BUFFERED_SIZE];
            int i;
            while ((i = in.read(bs)) > 0) {
                out.write(bs, 0, i);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null)
                    in.close();
                if (out != null)
                    out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
	public void setLnDunSetingService(LnDunSetingService lnDunSetingService) {
		this.lnDunSetingService = lnDunSetingService;
	}

	public LnDunSeting getLnDunSeting() {
		return lnDunSeting;
	}

	public void setLnDunSeting(LnDunSeting lnDunSeting) {
		this.lnDunSeting = lnDunSeting;
	}

	public LnDunSeting getLnDunSetinglimit() {
		return lnDunSetinglimit;
	}

	public void setLnDunSetinglimit(LnDunSeting lnDunSetinglimit) {
		this.lnDunSetinglimit = lnDunSetinglimit;
	}

    public File getRecordFile() {
        return recordFile;
    }

    public void setRecordFile(File recordFile) {
        this.recordFile = recordFile;
    }

    public File getRecordFileEnd() {
        return recordFileEnd;
    }

    public void setRecordFileEnd(File recordFileEnd) {
        this.recordFileEnd = recordFileEnd;
    }

    public void setSysParamService(SysParamService sysParamService) {
        this.sysParamService = sysParamService;
    }

}
