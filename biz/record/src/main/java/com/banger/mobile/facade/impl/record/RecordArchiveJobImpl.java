/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :录音归档...
 * Author     :huangk
 * Create Date:2012-4-11
 */
package com.banger.mobile.facade.impl.record;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import banger.util.BDate;

import com.banger.mobile.domain.model.record.RecordInfo;
import com.banger.mobile.facade.param.SysParamService;
import com.banger.mobile.facade.record.IRecordArchiveJobFacade;
import com.banger.mobile.facade.record.RecordInfoService;
import com.banger.mobile.util.FileUtil;
import com.banger.mobile.util.StringUtil;


/**
 * @author huangk
 * @version RecordInfoXmlParserJob.java,v 0.1 2012-3-31 下午2:15:25
 */
public class RecordArchiveJobImpl implements IRecordArchiveJobFacade {
    private RecordInfoService recordInfoService;                       //录音信息service
    private SysParamService sysParamService;
    protected transient final Log log = LogFactory.getLog(getClass()); //日志
    
    
    public void setRecordInfoService(RecordInfoService recordInfoService) {
        this.recordInfoService = recordInfoService;
    }


    public void setSysParamService(SysParamService sysParamService) {
        this.sysParamService = sysParamService;
    }


    /**
     * 定时器job 归档录音
     * @see com.banger.mobile.transport.facade.impl.IRecordArchiveJobFacade#doJob()
     */
    public void doArchiveJob(){
        try {
        //查询录音归档时间
        Map<String, Object> map=new HashMap<String, Object>();
        map = sysParamService.querySysParam();
        int gdtime=Integer.parseInt(map.get("RECORD_GD_DAYS").toString());//!
        Date d = BDate.addDay(new Date(),-gdtime);
        BDate bd=new BDate(d);
        Date pd = new BDate(bd.getYear(),bd.getMonth(),bd.getDay()).getValue();
        SimpleDateFormat dateformat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date=dateformat.format(pd);
        Date archiveDate=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
        String archiveDt=sdf.format(archiveDate);//new今天时间作为归档后的目录名
        Map<String,Object> m = new HashMap<String,Object>();
        m.put("GdDATE", date);
        List<RecordInfo> list = recordInfoService.queryRecordToGd(m);
//        if(list!=null){
//            String newpath=map.get("RECORD_GD_PATH").toString();
//            for(int i=0;i<list.size();i++ ){
//                if(!StringUtil.isEmpty(list.get(i).getFileName())&&!StringUtil.isEmpty(list.get(i).getFilePath())){
//                    File f=new File(list.get(i).getFilePath()+"/"+list.get(i).getFileName());
//                    File fpath2=new File(list.get(i).getFilePath());
//                    if(f.exists()){//当原文件存在
//                        FileUtil.moveFile2Dir(f, newpath+"/"+archiveDt);
//                    }
//                    recordInfoService.updateArchiveById(list.get(i).getRecordInfoId(), newpath+"/"+archiveDt);
//                    if(fpath2.list().length==0){
//                       fpath2.delete();
//                    }
//                } else{
//                    recordInfoService.updateArchiveById(list.get(i).getRecordInfoId(), newpath+"/"+archiveDt);
//                }          
//            }
//        }
        } catch (Exception e) {
            log.error("RecordArchiveJobImpl doArchiveJob :"+e.getMessage());
        }
    }
}
