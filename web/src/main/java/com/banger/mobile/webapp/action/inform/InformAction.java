package com.banger.mobile.webapp.action.inform;

import com.banger.mobile.domain.model.inform.Inform;
import com.banger.mobile.facade.feedBack.InformService;
import com.banger.mobile.webapp.action.BaseAction;
import org.apache.log4j.Logger;
import javax.sound.sampled.AudioFormat;
import java.util.Calendar;

/**
 * Created by BH-TCL on 15-7-13.
 */
public class InformAction extends BaseAction {
    private static Logger logger = Logger.getLogger(InformAction.class);
    private InformService informService;
    private Inform inform;

    public Inform getInform() {
        return inform;
    }

    public void setInform(Inform inform) {
        this.inform = inform;
    }

    public void setInformService(InformService informService) {
        this.informService = informService;
    }

    public String getInformInfo(){
        try{
            inform =informService.getInform(1);//获取通告 默认id为1
            return "success";
        }catch (Exception e){
            logger.error("通告获取失败");
            return "error";
        }
    }

    public String updateInform(){
        try{
            inform.setUpdateUser(getLoginInfo().getUserId());
            inform.setUpdateDate(Calendar.getInstance().getTime());
            informService.updateInform(inform);
            return "success";
        }catch (Exception e){
            logger.error("通告更新失败");
            return "error";
        }
    }
}
