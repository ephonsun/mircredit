package com.banger.mobile.webapp.action.loan;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.model.loan.LnRejectCustomer;
import com.banger.mobile.facade.loan.LnRejectCustomerService;
import com.banger.mobile.facade.param.SysParamService;
import com.banger.mobile.util.FileUtil;
import com.banger.mobile.webapp.action.BaseAction;
import org.apache.log4j.Logger;

import javax.servlet.ServletOutputStream;
import java.io.*;
import java.util.*;

/**
 * Created by BH-TCL on 14-12-4.
 */
public class RejectCustomerAction extends BaseAction {
    private static Logger logger = Logger.getLogger(RejectCustomerAction.class);
    private SysParamService sysParamService;
    //查询条件
    private String customer;
    private Date startDate;
    private Date endDate;
    private String applyUser;
    private String userIds;
    private String deptIds;
    private String BelongToType;

    public String getUserIds() {
        return userIds;
    }

    public void setUserIds(String userIds) {
        this.userIds = userIds;
    }

    public String getDeptIds() {
        return deptIds;
    }

    public void setDeptIds(String deptIds) {
        this.deptIds = deptIds;
    }

    public String getBelongToType() {
        return BelongToType;
    }

    public void setBelongToType(String belongToType) {
        BelongToType = belongToType;
    }

    private String allId;

    public String getAllId() {
        return allId;
    }

    public void setAllId(String allId) {
        this.allId = allId;
    }

    private LnRejectCustomerService lnRejectCustomerService;

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getApplyUser() {
        return applyUser;
    }

    public void setApplyUser(String applyUser) {
        this.applyUser = applyUser;
    }

    public void setLnRejectCustomerService(LnRejectCustomerService lnRejectCustomerService) {
        this.lnRejectCustomerService = lnRejectCustomerService;
    }

    public void setSysParamService(SysParamService sysParamService) {
        this.sysParamService = sysParamService;
    }

    /**
     * 获取列表
     * 附带查询功能
     *
     * @return
     */
    public String rejectCustomerList() {
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            List<Integer> list = new ArrayList<Integer>();

            if ("brAll".equalsIgnoreCase(this.getBelongToType())) {
                list = null;
            } else if ("brMine".equalsIgnoreCase(this.getBelongToType())) {
                list.add(this.getLoginInfo().getUserId());
            } else if ("brUser".equalsIgnoreCase(this.getBelongToType())) {
                if (this.getUserIds().length() <= 0 || this.getUserIds() == null) {
                    list = null;
                } else {
                    String[] userIds = this.getUserIds().split(",");
                    for (String userId : userIds) {
                        list.add(Integer.parseInt(userId));
                    }
                }
            } else {
                list = null;
            }

            if (this.getCustomer() != null && this.getCustomer().length() > 0)
                map.put("customerName", this.getCustomer());
            if (list != null)
                map.put("userIds", list);
            if (startDate != null)
                map.put("startDate", startDate);
            if (endDate != null)
                map.put("endDate", endDate);
            map.put("isDel", 0);
            PageUtil<LnRejectCustomer> lnRejectCustomerlist = lnRejectCustomerService.queryLnRejectCustomerPage(map, this.getPage());
            request.setAttribute("dataList", lnRejectCustomerlist.getItems());
            request.setAttribute("recordCount", lnRejectCustomerlist.getPage().getTotalRowsAmount());
            request.setAttribute("sysUserId", this.getLoginInfo().getUserId());

            request.setAttribute("userName", this.getLoginInfo().getUserName());
            request.setAttribute("dataCode", this.getLoginInfo().getDataCompetence()); //用户数据权限
            return SUCCESS;
        } catch (Exception e) {
            logger.error("RejectCustomerAction　％　rejectCustomerList:" + e);
            return ERROR;
        }
    }

    /**
     * 查询
     *
     * @return
     */
    public String queryRejectCustomerList() {
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            List<Integer> list = new ArrayList<Integer>();

            if ("brAll".equalsIgnoreCase(this.getBelongToType())) {
                list = null;
            } else if ("brMine".equalsIgnoreCase(this.getBelongToType())) {
                list.add(this.getLoginInfo().getUserId());
            } else if ("brUser".equalsIgnoreCase(this.getBelongToType())) {
                if (this.getUserIds().length() <= 0 || this.getUserIds() == null) {
                    list = null;
                } else {
                    String[] userIds = this.getUserIds().split(",");
                    for (String userId : userIds) {
                        list.add(Integer.parseInt(userId));
                    }
                }
            } else {
                list = null;
            }
            if (this.getCustomer() != null && this.getCustomer().length() > 0)
                map.put("customerName", this.getCustomer());
            if (list != null)
                map.put("userIds", list);
            if (startDate != null)
                map.put("startDate", startDate);
            if (endDate != null)
                map.put("endDate", endDate);
            map.put("isDel", 0);
            PageUtil<LnRejectCustomer> lnRejectCustomerlist = lnRejectCustomerService.queryLnRejectCustomerPage(map, this.getPage());
            request.setAttribute("dataList", lnRejectCustomerlist.getItems());
            request.setAttribute("recordCount", lnRejectCustomerlist.getPage().getTotalRowsAmount());
            request.setAttribute("sysUserId", this.getLoginInfo().getUserId());

            request.setAttribute("userName", this.getLoginInfo().getUserName());
            request.setAttribute("dataCode", this.getLoginInfo().getDataCompetence()); //用户数据权限
            return SUCCESS;
        } catch (Exception e) {
            logger.error("RejectCustomerAction　％　rejectCustomerList:" + e);
            return ERROR;
        }
    }

    public void deleteRejectCustomer() {
        try {
            String str = this.getAllId();
            if (str == null || str.length() <= 0) {
                logger.error("没有要删除的项");
                return;
            }
            Map<String, Object> map = new HashMap<String, Object>();
            LnRejectCustomer lnRejectCustomer = null;
            List<LnRejectCustomer> lnRejectCustomers = null;
            String[] ids = str.split(",");
            for (int i = 0; i < ids.length; i++) {
                List<Integer> list = new ArrayList<Integer>();
                list.add(Integer.parseInt(ids[i]));
                map.put("rejectCustomerIds", list);
                lnRejectCustomers = lnRejectCustomerService.queryRejectCustomerByIds(map);
                if (lnRejectCustomers == null || lnRejectCustomers.size() <= 0)
                    continue;
                lnRejectCustomer = lnRejectCustomers.get(0);
                lnRejectCustomer.setIsDel(1);//删除
                lnRejectCustomerService.updateLnRejectCustomer(lnRejectCustomer);
            }
        } catch (Exception e) {
            logger.error("RejectCustomerAction　％deleteRejectCustomer:" + e);
        }
    }

    /**
     * 导出excel
     */
    public void exportLnRejcetCustomerList() {
        try {
            logger.info("-----当前登录用户：" + this.getLoginInfo().getAccount() + "----- RejectCustomerAction.exportLnRejcetCustomerList");
            List<Integer> list = new ArrayList<Integer>();
            Map<String, Object> map = new HashMap<String, Object>();
            List<LnRejectCustomer> lnRejectCustomers = null;
            String str = this.getAllId();
            if (str == null || str.length() <= 0) {
                logger.info("未选中要导出记录");
                return;
            }
            if ("CheckAll".equalsIgnoreCase(str)) {
                if (this.getApplyUser() != null && this.getApplyUser().length() > 0) {
                    String[] userIds = this.getApplyUser().split(",");
                    for (String userId : userIds) {
                        list.add(Integer.parseInt(userId));
                    }
                } else {
                    list = null;
                }
                if (this.getCustomer() != null && this.getCustomer().length() > 0)
                    map.put("customerName", this.getCustomer());
                if (list != null)
                    map.put("userIds", list);
                if (startDate != null)
                    map.put("startDate", startDate);
                if (endDate != null)
                    map.put("endDate", endDate);
                map.put("isDel", 0);
            } else {
                String[] allId = str.split(",");

                for (int i = 0; i < allId.length; i++) {
                    list.add(Integer.parseInt(allId[i]));
                }
                //查出所有id对应的记录
                map.put("rejectCustomerIds", list);
            }
            lnRejectCustomers = lnRejectCustomerService.queryRejectCustomerByIds(map);

            if (lnRejectCustomers == null) {
                logger.info("空记录集，不用导出");
                return;
            }

            Long times = Calendar.getInstance().getTimeInMillis();
            String dir = sysParamService.getRecordPath() + File.separator + "lnRejectCustomerExportList";
            String curDownFilePath = dir + File.separator + "拒绝客户" + times + ".xls";
            File f = new File(dir);
            if (!f.exists()) {
                f.mkdirs();
            }
            lnRejectCustomerService.getlnRejectCustomerExportListFile(lnRejectCustomers, curDownFilePath);
            File downFile = new File(curDownFilePath);
            if (downFile.exists()) {
                synchronized (this) {
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
                    if (bis != null) {
                        bis.close();
                    }
                    if (bos != null) {
                        bos.flush();// 清空输出缓冲流
                        bos.close();
                    }
                    if (output != null) {
                        output.close();
                    }
                    FileUtil.deleteFile(downFile.getAbsolutePath());
                }
            }
        } catch (Exception e) {
            logger.error("RejectCustomerAction%:exportLnRejcetCustomerList" + e);
        }
    }
}
