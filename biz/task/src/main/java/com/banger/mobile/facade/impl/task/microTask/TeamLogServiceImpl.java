package com.banger.mobile.facade.impl.task.microTask;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.dao.microTask.TeamLogDao;
import com.banger.mobile.domain.model.microTask.TeamLog;
import com.banger.mobile.facade.microTask.TeamLogService;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

/**
 * Created by BH-TCL on 15-5-5.
 */
public class TeamLogServiceImpl implements TeamLogService {

    private static Logger logger = Logger.getLogger(TeamLogServiceImpl.class);
    private TeamLogDao teamLogDao;

    public void setTeamLogDao(TeamLogDao teamLogDao) {
        this.teamLogDao = teamLogDao;
    }

    @Override
    public void insertTeamLog(TeamLog teamLog) {
        teamLogDao.insertTeamLog(teamLog);
    }

    /**
     * 分页查询
     * @param conds
     * @param curPage
     * @return
     */
    @Override
    public PageUtil<TeamLog> queryFeedBacksPage(Map<String, Object> conds, Page curPage) {
        return teamLogDao.queryFeedBacksPage(conds,curPage);
    }

    /**
     * 更新团队日志
     * @param teamLog
     */
    @Override
    public void updateFeedBack(TeamLog teamLog) {
        teamLogDao.updateFeedBack(teamLog);
    }

    /**
     * 查单条记录
     * map.put("userId","")
     * map.put("teamLogId","")
     * @param map
     * @return
     */
    @Override
    public TeamLog getTeamLog(Map<String, Object> map) {
        return teamLogDao.getTeamLog(map);
    }


    /**
     * 获取选中部门下所有已经填写个人日志的数据统计
     * @param deptId
     * @return
     */
    @Override
    public TeamLog getTeamLogSum(Integer deptId) {
        return teamLogDao.getTeamLogSum(deptId);
    }

    /**
     * 获取选中部门下所有要填写的人数
     * @param deptId
     * @return
     */
    @Override
    public Integer getNeedTeamLogCount(Integer deptId) {
        return teamLogDao.getNeedTeamLogCount(deptId);
    }

    /**
     * 本机构的要填写人数
     * @param deptId
     * @return
     */
    @Override
    public Integer getDeptTeamLogCount(Integer deptId) {
        return teamLogDao.getDeptTeamLogCount(deptId);
    }

    /**
     * 本机构的已经填写人数
     * @param deptId
     * @return
     */
    @Override
    public Integer getDeptTeamLogCountWhitWriter(Integer deptId) {
        return teamLogDao.getDeptTeamLogCountWhitWriter(deptId);
    }

    /**
     * 本机构已经填写的统计
     * @param deptId
     * @return
     */
    @Override
    public TeamLog getDeptTeamLogSum(Integer deptId) {
        return teamLogDao.getDeptTeamLogSum(deptId);
    }

    /**
     * 获取选中部门下已经填写的人数
     * @param deptId
     * @return
     */
    @Override
    public Integer getNeedTeamLogCountWhitWriter(Integer deptId) {
        return teamLogDao.getNeedTeamLogCountWhitWriter(deptId);
    }

    public void getTeamLogExporFile(TeamLog teamLog,String deptOrUserName,String path) {
        try{
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Workbook book = new HSSFWorkbook();
            Sheet sheet = book.createSheet("团队日志");
            Row row = sheet.createRow(0);
            row.createCell(0).setCellValue("日期");
            row.createCell(1).setCellValue("姓名或者机构名称");
            row.createCell(2).setCellValue("昨日总结电话营销(整数)");
            row.createCell(3).setCellValue("昨日总结实地营销(整数)");
            row.createCell(4).setCellValue("昨日总结接待咨询(整数)");
            row.createCell(5).setCellValue("昨日总结接待申请(整数)");
            row.createCell(6).setCellValue("昨日总结调查笔数(整数)");
            row.createCell(7).setCellValue("昨日总结放贷笔数(整数)");
            row.createCell(8).setCellValue("昨日总结贷后笔数(整数)");
            row.createCell(9).setCellValue("昨日总结上会笔数(整数)");
            row.createCell(10).setCellValue("今日计划电话营销(整数)");
            row.createCell(11).setCellValue("今日计划实地营销(整数)");
            row.createCell(12).setCellValue("今日计划接待咨询(整数)");
            row.createCell(13).setCellValue("今日计划接待申请(整数)");
            row.createCell(14).setCellValue("今日计划调查笔数(整数)");
            row.createCell(15).setCellValue("今日计划放贷笔数(整数)");
            row.createCell(16).setCellValue("今日计划贷后笔数(整数)");
            row.createCell(17).setCellValue("今日计划上会笔数(整数)");
            row.createCell(18).setCellValue("本月累计通过笔数(整数)");
            row.createCell(19).setCellValue("本月净增户数任务指标(整数)");
            row.createCell(20).setCellValue("本月净增余额任务指标(整数)");
            row.createCell(21).setCellValue("本月通过未发放金额(保留2位小数)");
            row.createCell(22).setCellValue("预计至月底放款金额(保留2位小数)");
            row.createCell(23).setCellValue("预计至月底净增金额(保留2位小数)");
            row.createCell(24).setCellValue("待处理笔数(整数)");
            row.createCell(25).setCellValue("本月新增笔数");
            row.createCell(26).setCellValue("本月转贷笔数");
            row.createCell(27).setCellValue("本月叠加笔数");
            row.createCell(28).setCellValue("本月到期笔数");
            row.createCell(29).setCellValue("本月提前结清笔数");
            row.createCell(30).setCellValue("本月提前结清金额");
            row.createCell(31).setCellValue("本月放款笔数");
            row.createCell(32).setCellValue("本月放款加权利率%");
            row.createCell(33).setCellValue("本月有余额户数");
            row.createCell(34).setCellValue("上月有余额户数");
            row.createCell(35).setCellValue("本月净增户数");
            row.createCell(36).setCellValue("本月放款金额");
            row.createCell(37).setCellValue("本月贷款余额");
            row.createCell(38).setCellValue("上月贷款余额");
            row.createCell(39).setCellValue("本月净增余额");

            row = sheet.createRow(1);
            row.createCell(0).setCellValue(sdf.format(Calendar.getInstance().getTime()));
            row.createCell(1).setCellValue(deptOrUserName);
            row.createCell(2).setCellValue(teamLog.getZrzjdhyx());
            row.createCell(3).setCellValue(teamLog.getZrzjsdyx());
            row.createCell(4).setCellValue(teamLog.getZrzjjdzx());
            row.createCell(5).setCellValue(teamLog.getZrzjjdsq());
            row.createCell(6).setCellValue(teamLog.getZrzjdcbs());
            row.createCell(7).setCellValue(teamLog.getZrzjfdbs());
            row.createCell(8).setCellValue(teamLog.getZrzjdhbs());
            row.createCell(9).setCellValue(teamLog.getZrzjshbs());
            row.createCell(10).setCellValue(teamLog.getJrjhdhyx());
            row.createCell(11).setCellValue(teamLog.getJrjhsdyx());
            row.createCell(12).setCellValue(teamLog.getJrjhjdzc());
            row.createCell(13).setCellValue(teamLog.getJrjhjdsq());
            row.createCell(14).setCellValue(teamLog.getJrjhdcbs());
            row.createCell(15).setCellValue(teamLog.getJrjhfdbs());
            row.createCell(16).setCellValue(teamLog.getJrjhdhbs());
            row.createCell(17).setCellValue(teamLog.getJrjhshbs());
            row.createCell(18).setCellValue(teamLog.getByljtgbs());
            row.createCell(19).setCellValue(teamLog.getByjzhsrwzb());
            row.createCell(20).setCellValue(teamLog.getByjzyerwzb());
            row.createCell(21).setCellValue(teamLog.getBytgwffje().toString());
            row.createCell(22).setCellValue(teamLog.getYjydfkje().toString());
            row.createCell(23).setCellValue(teamLog.getYjydjzje().toString());
            row.createCell(24).setCellValue(teamLog.getDclbs());
            row.createCell(25).setCellValue(teamLog.getByxzbs());
            row.createCell(26).setCellValue(teamLog.getByzdbs());
            row.createCell(27).setCellValue(teamLog.getBydjbs());
            row.createCell(28).setCellValue(teamLog.getBydqbs());
            row.createCell(29).setCellValue(teamLog.getBytqjqbs());
            row.createCell(30).setCellValue(teamLog.getBytqjqje().toString());
            row.createCell(31).setCellValue(teamLog.getByfkbs());
            row.createCell(32).setCellValue(teamLog.getByfkjqll().toString());
            row.createCell(33).setCellValue(teamLog.getByyyehs());
            row.createCell(34).setCellValue(teamLog.getSyyyehs());
            row.createCell(35).setCellValue(teamLog.getByjzhs());
            row.createCell(36).setCellValue(teamLog.getByfkje().toString());
            row.createCell(37).setCellValue(teamLog.getBydkye().toString());
            row.createCell(38).setCellValue(teamLog.getSydkye().toString());
            row.createCell(39).setCellValue(teamLog.getByjzye().toString());


            sheet.autoSizeColumn(0);
            sheet.autoSizeColumn(1);
            sheet.autoSizeColumn(2);
            sheet.autoSizeColumn(3);
            sheet.autoSizeColumn(4);
            sheet.autoSizeColumn(5);
            sheet.autoSizeColumn(6);

            FileOutputStream os = new FileOutputStream(new File(path));
            book.write(os);
            os.close();
        }catch(Exception e){
            logger.error("getTeamLogExporFile", e);
        }

    }
}
