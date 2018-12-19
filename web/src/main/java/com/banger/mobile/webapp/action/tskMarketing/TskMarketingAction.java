package com.banger.mobile.webapp.action.tskMarketing;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.model.crmCounterUser.CrmCounterUser;
import com.banger.mobile.domain.model.pdtProduct.PdtProduct;
import com.banger.mobile.domain.model.system.TaskGrade;
import com.banger.mobile.domain.model.tskMarketing.TskMarketing;
import com.banger.mobile.domain.model.tskMarketing.TskMarketingAttachment;
import com.banger.mobile.domain.model.tskMarketing.TskMarketingBean;
import com.banger.mobile.domain.model.tskMarketing.TskMarketingExecute;
import com.banger.mobile.domain.model.tskMarketing.TskMarketingPlan;
import com.banger.mobile.domain.model.tskMarketing.TskMarketingPlanDetail;
import com.banger.mobile.domain.model.user.SysUser;
import com.banger.mobile.facade.crmCounterUser.CrmCounterUserService;
import com.banger.mobile.facade.dept.DeptFacadeService;
import com.banger.mobile.facade.pdtProduct.PdtProductService;
import com.banger.mobile.facade.pdtProduct.PdtTemplateService;
import com.banger.mobile.facade.system.TaskGradeService;
import com.banger.mobile.facade.tskContact.TskContactExecuteService;
import com.banger.mobile.facade.tskMarketing.TskMarketingAttachmentService;
import com.banger.mobile.facade.tskMarketing.TskMarketingExecuteService;
import com.banger.mobile.facade.tskMarketing.TskMarketingPlanDetailService;
import com.banger.mobile.facade.tskMarketing.TskMarketingPlanService;
import com.banger.mobile.facade.tskMarketing.TskMarketingService;
import com.banger.mobile.util.DateUtil;
import com.banger.mobile.util.FileUtil;
import com.banger.mobile.util.StringUtil;
import com.banger.mobile.webapp.action.BaseAction;
import com.ibm.db2.jcc.am.t;

public class TskMarketingAction extends BaseAction {

    private static final long             serialVersionUID = 3254008530408950408L;

    private TskMarketingPlanDetailService tskMarketingPlanDetailService;
    private TskMarketingPlanService       tskMarketingPlanService;
    private TaskGradeService              taskGradeService;
    private TskMarketingService           tskMarketingService;
    private DeptFacadeService             deptFacadeService;                      //机构接口Service
    private TskMarketing                  tskMarketing;
    private PageUtil<TskMarketingBean>    tskMarketingBeanPage;                   
    private TskMarketingBean              tskMarketingBean;
    private List<TaskGrade>               tskGradeList;                           //任务等级列表
    private String                        userAuth;                               //用户角色权限
    private PageUtil<TskMarketingBean>    myTskMarketingBeanPage;                 //我制定的任务列表
    private List<TskMarketingPlanDetail>  tskMarketPlanDetailToday;               //当天产品的营销计划
    private PdtTemplateService            pdtTemplateService;                     //产品模版
    private PdtProductService             pdtProductService;
    private TskMarketingAttachmentService tskMarketingAttachmentService;
    private TskContactExecuteService      tskContactExecuteService;
    private CrmCounterUserService         crmCounterUserService;

    private String                        templatesTrees;                         //产品类型树
    private String                        tskAssignTrees;                         //任务分配树
    private String                        inChargeTrees;                          //权限树
    private String                        assignTargetJson;                       //分配json数据
    private TskMarketingExecuteService    tskMarketingExecuteService;
    private List<TskMarketingAttachment>  attrList;                                //任务附件

    /**
     * 附件上传参数
     */
    private static final int              BUFFERED_SIZE    = 4 * 1024;
    private File                          fileInput;
    private String                        fileInputFileName;
    private String                        fileAttachments;                        //附件集合，多个附件用":"分隔

    private Integer roleType;
    private String proTargetType;
    
    public void initRoleType(){
    	if(deptFacadeService.isInChargeOfDepartment()){
    		roleType=0;
    	}else roleType=getLoginInfo().getUserId();
    }
    
    /**
     * 全景展示
     * @return
     */
    public String showTaskMarketView() {
        try {
        	Integer uid=this.getLoginInfo().getUserId();
            //先判断是否是业务主管
            if(deptFacadeService.isInChargeOfDepartment()){
                //在是业务主管的前提下判断是否是创建者

                String inChargeUserIds = "", inChargeUserIdsMark = "", inChargeDepts = "";
                Integer[] inChargeDeptsIntegers = deptFacadeService.getInChargeOfDeptIds();
                for (Integer deptId : inChargeDeptsIntegers) {
                    inChargeDepts += deptId.intValue() + ","; //负责的机构
                }
                if (inChargeDepts.length() > 0) {
                    inChargeDepts = inChargeDepts.substring(0, (inChargeDepts.length() - 1));
                }

                Integer[] inChargeUserIdsIntegers = deptFacadeService.getInChargeOfDeptUserIds();
                for (Integer userId : inChargeUserIdsIntegers) {
                    inChargeUserIds += userId.intValue() + ","; //负责的机构人员
                    inChargeUserIdsMark += "[" + userId.intValue() + "],";
                }
                if (inChargeUserIds.length() > 0) {
                    inChargeUserIds = inChargeUserIds.substring(0, (inChargeUserIds.length() - 1));
                    inChargeUserIdsMark = inChargeUserIdsMark.substring(0, (inChargeUserIdsMark.length() - 1));
                }
                
                Map<String, Object> conds = new HashMap<String, Object>();
                conds.put("userId", uid);
                conds.put("inChargeUserIds", inChargeUserIds+","+uid);
                conds.put("inChargeUserIdsMark", inChargeUserIdsMark+",["+uid+"]");
                conds.put("inChargeDepts", inChargeDepts);
                conds.put("completion", "execing"); //执行中
                
                //所有任务列表
                tskMarketingBeanPage = tskMarketingService.GetAllTskMarketingPageByConds(conds, this.getPage());
                //只查我制定的任务
                conds.put("assignUserId", this.getLoginInfo().getUserId());
                //我制定的任务列表
                myTskMarketingBeanPage = tskMarketingService.GetAllTskMarketingPageByConds(conds, this.getPage());
                
                if (myTskMarketingBeanPage.getItems().size()>0) {
                    userAuth = "'" + "创建者" + "'";
                } else{
                    userAuth = "'" + "业务主管" + "'";
                }
                getTskPlanListToday("业务主管");    
            }else if(deptFacadeService.isCommon()){         //判断是否是客户经理
                userAuth = "'" + "客户经理" + "'";
                queryTskMarketingListMyExecute();
                getTskPlanListToday("客户经理");
            }

            return SUCCESS;
        } catch (Exception e) {
            log.error("showTaskMarketView action error", e);
            return ERROR;
        }

    }

    /**
     * 所有任务列表
     * @return
     */
    public String queryTskMarketingList() {
        try {
            tskGradeList = taskGradeService.getUnDelActiveTaskGrade(); //load获取任务等级列表
            Integer uid=this.getLoginInfo().getUserId();
            String inChargeUserIds = "", inChargeUserIdsMark = "", inChargeDepts = "";
            Integer[] inChargeDeptsIntegers = deptFacadeService.getInChargeOfDeptIds();
            for (Integer deptId : inChargeDeptsIntegers) {
                inChargeDepts += deptId.intValue() + ","; //负责的机构
            }
            if (inChargeDepts.length() > 0) {
                inChargeDepts = inChargeDepts.substring(0, (inChargeDepts.length() - 1));
            }

            Integer[] inChargeUserIdsIntegers = deptFacadeService.getInChargeOfDeptUserIds();
            for (Integer userId : inChargeUserIdsIntegers) {
                inChargeUserIds += userId.intValue() + ","; //负责的机构人员
                inChargeUserIdsMark += "[" + userId.intValue() + "],";
            }
            if (inChargeUserIds.length() > 0) {
                inChargeUserIds = inChargeUserIds.substring(0, (inChargeUserIds.length() - 1));
                inChargeUserIdsMark = inChargeUserIdsMark.substring(0, (inChargeUserIdsMark
                    .length() - 1));
            }

            Map<String, Object> conds = new HashMap<String, Object>();
            conds.put("userId", uid);
            conds.put("inChargeUserIds", inChargeUserIds+","+uid);
            conds.put("inChargeUserIdsMark", inChargeUserIdsMark+",["+uid+"]");
            conds.put("inChargeDepts", inChargeDepts);

            if (tskMarketingBean != null) {
                if (tskMarketingBean.getGradeId() != -1) {//查所有
                    conds.put("gradeId", tskMarketingBean.getGradeId());
                }
                if (tskMarketingBean.getAssignUserId() == 0) {//查分配者是我的
                    conds.put("assignUserId", this.getLoginInfo().getUserId());
                }
                conds.put("marketingTitle", tskMarketingBean.getMarketingTitle());
                conds.put("productTarget", tskMarketingBean.getProductTarget());
            }
            conds.put("startDate", request.getParameter("createStartDate"));
            conds.put("endDate", request.getParameter("createEndDate"));
            
            if (request.getParameter("completion") == null) {
                conds.put("completion", "execing");//第一次执行中
            } else {
                conds.put("completion", request.getParameter("completion"));
            }
            conds.put("complete", request.getParameter("complete"));

            tskMarketingBeanPage = tskMarketingService.GetAllTskMarketingPageByConds(conds, this
                .getPage());
            
            int count = this.getPage().getTotalRowsAmount();
            request.setAttribute("count", String.valueOf(count));
            request.setAttribute("userId", this.getLoginInfo().getUserId());
            return SUCCESS;
        } catch (Exception e) {
            log.error("allTskMarketingList action error", e);
            return ERROR;
        }
    }

    /**
     * 格式化开始-结束时间
     * @param obj
     * @param repairType
     * @return
     */
    private String repairDateTime(Object obj, String repairType) {
        String repairDate = "";
        if (obj != null) {
            if (StringUtil.isNotEmpty(obj.toString())) {
                if (repairType.equals("S")) {
                    repairDate = obj.toString() + " 00:00:00";
                } else {
                    repairDate = obj.toString() + " 23:59:59";
                }
            }
        }
        return repairDate;
    }

    /**
     * 跳转到新建营销任务页面
     * @return
     */
    public String toAddTskMarketingPage() {
        tskGradeList = taskGradeService.getUnDelActiveTaskGrade(); //load获取任务等级列表
        JSONArray jsonArray = pdtTemplateService.getProductTemplatesTree();//加载产品树
        templatesTrees = jsonArray.toString();
        return SUCCESS;
    }

    /**
     * 验证新建营销任务(新增、编辑)
     */
    public void verifyTskMarketing() {
        PrintWriter out = null;
        try {
            out = this.getResponse().getWriter();
            String msg = "SUCCESS";
            Map<String, Object> conds = new HashMap<String, Object>();
            conds.put("marketingTitle", tskMarketing.getMarketingTitle());
            if (tskMarketing.getMarketingId() > 0) {
                conds.put("unMarketingId", tskMarketing.getMarketingId());
            }
            List<TskMarketing> list = tskMarketingService.getAllTskMarketingByConds(conds);
            //验证标题
            if ((list != null) && (list.size() > 0)) {
                msg = "1";
            } else {
                conds.clear();
                Integer count=0;
                String oldProductTarget=request.getParameter("oldProductTarget");
                if(!StringUtil.isBlank(oldProductTarget)){
                	if(!request.getParameter("productTargetName").equals(oldProductTarget)){
                		count=tskMarketingPlanService.getTskPlanByMarketingId(tskMarketing.getMarketingId());
                    }
                }
                if(count>0){
                	msg="3";
                }else{
	                if (tskMarketing.getMarketingId() > 0) {
	                    conds.put("unMarketingId", tskMarketing.getMarketingId());
	                }
	                conds.put("productTargetType", request.getParameter("productTargetType"));
	                conds.put("productTargetVal", request.getParameter("productTargetVal"));
	                conds.put("startDate", repairDateTime(request.getParameter("startDate"), "S"));
	                conds.put("endDate", repairDateTime(request.getParameter("endDate"), "E"));
	                list = tskMarketingService.getAllTskMarketingByConds(conds);
	                //验证时间范围内是否已有营销该产品或类型的任务
	                if ((list != null) && (list.size() > 0)) {
	                	msg = "2";
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
     * 保存新建营销任务
     */
    public void saveTskMarketing() {
        PrintWriter out = null;
        try {
            out = this.getResponse().getWriter();
            String msg = "SUCCESS";
            if (tskMarketing.getMarketingId() > 0) {
                TskMarketing item = tskMarketingService.getTskMarketingById(tskMarketing
                    .getMarketingId());
                item.setGradeId(tskMarketing.getGradeId());
                item.setMarketingTitle(tskMarketing.getMarketingTitle());
                item.setRemark(tskMarketing.getRemark());
                item.setProductId(tskMarketing.getProductId());
                item.setStartDate(tskMarketing.getStartDate());
                item.setEndDate(tskMarketing.getEndDate());
                item.setSubTemplateName(tskMarketing.getSubTemplateName());
                item.setTemplateId(tskMarketing.getTemplateId());
                item.setUpdateUser(this.getLoginInfo().getUserId());
                tskMarketingService.editTskMarketing(item);

                //保存执行者对象svaeTskMarketingExecute
                Map<String, Object> conds = new HashMap<String, Object>();
                conds.put("marketingId", tskMarketing.getMarketingId());
                String inChargeDepts = "";
                Integer[] inChargeDeptsIntegers = deptFacadeService.getInChargeOfDeptIds();
                for (Integer deptId : inChargeDeptsIntegers) {
                    inChargeDepts += deptId.intValue() + ","; //负责的机构
                }
                if (inChargeDepts.length() > 0) {
                    inChargeDepts = inChargeDepts.substring(0, (inChargeDepts.length() - 1));
                }
                conds.put("inChargeDepts", inChargeDepts+","+this.getLoginInfo().getDeptId());
                tskMarketingExecuteService.delTskMarketingByConds(conds);//删除原先的数据
                msg = addTskMarketingExec();
            } else {
                tskMarketing.setAssignUserId(this.getLoginInfo().getUserId());
                tskMarketing.setCreateUser(this.getLoginInfo().getUserId());
                tskMarketing.setUpdateUser(this.getLoginInfo().getUserId());
                tskMarketing.setIsDel(1);
                //保存数据
                tskMarketingService.addTskMarketing(tskMarketing);
            }
            //保存附件数据到数据库
            addAttachments(tskMarketing.getMarketingId(), this.getLoginInfo().getUserId());
            out.write("{\"result\":\""
                      + msg
                      + "\",\"marketingId\":"
                      + tskMarketing.getMarketingId()
                      + ",\"days\":\""
                      + (DateUtil.countDays(request.getParameter("startDate").toString(), request
                          .getParameter("endDate").toString(), "yyyy-MM-dd") + 1) + "\"}");
        } catch (Exception ex) {
            out.write("{\"result\":ERROR,\"marketingId\":\"0\"}");
            log.error(ex);
        }
    }

    /**
     * 保存附件数据到数据库
     * @param marketingId
     * @param userId
     */
    private void addAttachments(int marketingId, int userId) {
        if (!StringUtil.isNullOrEmpty(fileAttachments)) {
            String savePath = "c:/tskMarketing/files" + "/"
                              + DateUtil.convertDateToString("yyyyMMdd", new Date());
            //保存附件
            String[] attItems = fileAttachments.split(":");// "\\|"
            for (String atts : attItems) {
                if (!StringUtil.isNullOrEmpty(atts)) {
                    String[] att = atts.split("\\|");
                    //添加附件到数据库
                    TskMarketingAttachment attachment = new TskMarketingAttachment();
                    attachment.setMarketingId(marketingId);
                    attachment.setCreateUser(userId);
                    attachment.setUpdateUser(userId);
                    attachment.setFileName(att[2]);
                    attachment.setFileNameOld(att[0]);
                    attachment.setFilePath(savePath);
                    attachment.setFileSize(Long.parseLong(att[1]));
                    tskMarketingAttachmentService.addTskMarketingAttachment(attachment);
                }
            }
        }
    }

    /**
     * 上传任务附件
     * @throws IOException 
     */
    public void uploadTskMarketingAttachment() throws IOException {
        PrintWriter out = null;
        String oldName = "";
        //try {
        out = this.getResponse().getWriter();

        if (fileInputFileName != null && !fileInputFileName.equals("")) {
            String savePath = "c:/tskMarketing/files" + "/"
                              + DateUtil.convertDateToString("yyyyMMdd", new Date());
            FileUtil.createDir(savePath);

            oldName = String.valueOf(System.currentTimeMillis()) + new Random().nextInt()
                      + fileInputFileName.substring(fileInputFileName.lastIndexOf("."));
            //将文件上传到服务器
            File imageFile = new File(savePath + File.separator + oldName);

            copyFile(fileInput, imageFile);//上传文件

            //返回成功信息
            out.write(fileInputFileName + "||" + oldName);
        }
    }

    /**
     * 上传文件流
     * @param src
     * @param target
     * @throws IOException
     */
    private void copyFile(File src, File target) throws IOException {
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
        } catch (IOException ex) {
            throw ex;//把异常继续抛出到上层
        } finally {
            if (in != null)
                in.close();
            if (out != null)
                out.close();
        }
    }

    /**
     * 跳转到新建营销任务分配页面
     * @return
     */
    public String toAddTskMarketingNextPage() {
        request.setAttribute("days", request.getParameter("days").toString());//相差天数
        return SUCCESS;
    }
    
    /**
     * 下载任务附件
     */
    public void downloadAttachment() {
        Map<String, Object> conds = new HashMap<String, Object>();
        int attachmentId = Integer.parseInt(request.getParameter("attachmentId"));
        conds.put("attachmentId", attachmentId);
        List<TskMarketingAttachment> atts = tskMarketingAttachmentService.getAllTskMarketingAttachmentByConds(conds);
        try {
            File file = new File(atts.get(0).getFilePath()+"/"+atts.get(0).getFileName());
            FileInputStream fis = new FileInputStream(file);//服务器文件路径
            getResponse().addHeader("Content-Disposition", "attachment;filename="+new String(atts.get(0).getFileNameOld().getBytes("gbk"), "iso8859-1"));
            getResponse().addHeader("Content-Length", "" + file.length());

            getResponse().setContentType("application/octet-stream"); //设置返回的文件类型 
            OutputStream output = getResponse().getOutputStream(); //得到向客户端输出二进制数据的对象 
            BufferedInputStream bis = new BufferedInputStream(fis);//输入缓冲流      
            BufferedOutputStream bos = new BufferedOutputStream(output);//输出缓冲流     

            byte data[] = new byte[4096];//缓冲字节数    

            int size = 0;
            size = bis.read(data);
            while (size != -1) {
                bos.write(data, 0, size);
                size = bis.read(data);
            }
            bis.close();
            bos.flush();//清空输出缓冲流     
            bos.close();
            output.close();
        } catch (Exception e) {
            log.error(e);
        }
    }


    /**
     * 删除任务附件
     */
    public void delAttachment() {
        PrintWriter out = null;
        try {
            out = this.getResponse().getWriter();
            Map<String, Object> conds = new HashMap<String, Object>();
            int attachmentId = Integer.parseInt(request.getParameter("attachmentId"));
            conds.put("attachmentId", attachmentId);
            tskMarketingAttachmentService.delTskMarketingAttachmentByConds(conds);
            out.write("Success");
        } catch (Exception ex) {
            out.write("删除失败");
            log.error(ex);
        }
    }

    /**
     * 搜索选择产品
     */
    public void queryPdtProductList() {
        PrintWriter out = null;
        try {
            out = this.getResponse().getWriter();
            Map<String, Object> conds = new HashMap<String, Object>();
            conds.put("productName", request.getParameter("productName"));
            conds.put("productCode", request.getParameter("productCode"));
            List<PdtProduct> pdtProductList = pdtProductService.getProductListForTask(conds);
            JSONArray jsonArray = JSONArray.fromObject(pdtProductList);
            out.write(jsonArray.toString());
        } catch (Exception ex) {
            out.write("");
            log.error(ex);
        }
    }

    /**
     * 保存营销任务目标分配
     */
    public void svaeTskMarketingExecute() {
        PrintWriter out = null;
        try {
            out = this.getResponse().getWriter();
            String msg = "SUCCESS";

            //保存执行者对象svaeTskMarketingExecute
            Map<String, Object> conds = new HashMap<String, Object>();
            conds.put("marketingId", tskMarketing.getMarketingId());
            String inChargeDepts = "";
            Integer[] inChargeDeptsIntegers = deptFacadeService.getInChargeOfDeptIds();
            for (Integer deptId : inChargeDeptsIntegers) {
                inChargeDepts += deptId.intValue() + ","; //负责的机构
            }
            if (inChargeDepts.length() > 0) {
                inChargeDepts = inChargeDepts.substring(0, (inChargeDepts.length() - 1));
            }
            conds.put("inChargeDepts", inChargeDepts);
            tskMarketingExecuteService.delTskMarketingByConds(conds);//删除原先的数据
            msg = addTskMarketingExec();
            out.write(msg);
        } catch (Exception ex) {
            out.write("保存失败");
            log.error(ex);
        }
    }

    /**
     * 对营销任务分配目标保存到数据库
     * @return
     */
    private String addTskMarketingExec() {
        List<TskMarketingExecute> list = new ArrayList<TskMarketingExecute>();

        if (!StringUtil.isNullOrEmpty(assignTargetJson)) {
            if (!StringUtil.isNullOrEmpty(assignTargetJson)) {
                JSONArray array = JSONArray.fromObject(assignTargetJson);
                for (int i = 0; i < array.size(); i++) {
                    JSONObject jsonObject = array.getJSONObject(i);
                    TskMarketingExecute tExecute = new TskMarketingExecute();
                    tExecute.setMarketingId(tskMarketing.getMarketingId());
                    if (jsonObject.get("t").toString().toUpperCase().equals("D")) {
                        tExecute.setDeptId(Integer.parseInt(jsonObject.get("id").toString()));
                        tExecute.setUserId(0);
                        tExecute.setDeptUnassign(new BigDecimal(jsonObject.get("un").toString()));
                    } else {
                        tExecute.setDeptId(Integer.parseInt(jsonObject.get("pid").toString()));
                        tExecute.setUserId(Integer.parseInt(jsonObject.get("id").toString()));
                        tExecute.setDeptUnassign(new BigDecimal(0.00));
                    }
                    tExecute.setMarketingTarget(new BigDecimal(jsonObject.get("m").toString()));
                    list.add(tExecute);
                }
            }
            tskMarketingExecuteService.addTskMarketingExecuteBatch(list);
        }
        tskMarketingService.delTskMarketingById(tskMarketing.getMarketingId(), 0);
        return "SUCCESS";
    }
    
    /**
     * 机构树显示(新建、编辑)
     */
    public void showAllExecuterList() {
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setCharacterEncoding("UTF-8");
        try {
            PrintWriter out = response.getWriter();
            List<String> listS = null;
            if(request.getParameter("mid")!=null){
                Map<String, Object> conds = new HashMap<String, Object>();
                conds.put("marketingId", request.getParameter("mid").toString());
                List<TskMarketingExecute> tskEL = tskMarketingExecuteService.getAllTskMarketingExecuteByConds(conds);
                if(tskEL.size() > 0){
                    listS = new ArrayList<String>();
                    for (TskMarketingExecute tskMarketingExecute : tskEL) {
                        if(tskMarketingExecute.getUserId() == 0){
                            listS.add(tskMarketingExecute.getDeptId().toString());
                        }else{
                            listS.add(tskMarketingExecute.getUserId().toString());
                            if(!listS.contains(tskMarketingExecute.getDeptId())){
                            	listS.add(tskMarketingExecute.getDeptId().toString());
                            }
                        }
                    }
                }
                inChargeTrees = tskContactExecuteService.getInchargeOfExecuterTree(listS,true).toString();        
            }else{
            	inChargeTrees = tskContactExecuteService.getInchargeOfExecuterTree(listS).toString();  
            }
            out.print(inChargeTrees);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("showDeptList action error:" + e.getMessage());
        }
    }

    /**
     * 跳转到编辑我创建的营销任务页面
     * @return
     */
    public String toEditTskMarketingByAssignPage() {
    	Integer uid=this.getLoginInfo().getUserId();
    	Map<String, Object> conds = new HashMap<String, Object>();
    	if(deptFacadeService.isInChargeOfDepartment()){
    		conds.put("isCommon", 0);
    	}else{
    		conds.put("isCommon", 1);
    	}
    	String inChargeUserIds = "", inChargeUserIdsMark = "", inChargeDepts = "", inCounterUserIds = "";
        Integer[] inChargeDeptsIntegers = deptFacadeService.getInChargeOfDeptIds();
        for (Integer deptId : inChargeDeptsIntegers) {
            inChargeDepts += deptId.intValue() + ","; //负责的机构
        }
        if (inChargeDepts.length() > 0) {
            inChargeDepts = inChargeDepts.substring(0, (inChargeDepts.length() - 1));
        }

        Integer[] inChargeUserIdsIntegers = deptFacadeService.getInChargeOfDeptUserIds();
        for (Integer userId : inChargeUserIdsIntegers) {
            inChargeUserIds += userId.intValue() + ","; //负责的机构人员
            inChargeUserIdsMark += "[" + userId.intValue() + "],";
        }
        if (inChargeUserIds.length() > 0) {
            inChargeUserIds = inChargeUserIds.substring(0, (inChargeUserIds.length() - 1));
            inChargeUserIdsMark = inChargeUserIdsMark.substring(0, (inChargeUserIdsMark.length() - 1));
        }

        conds.put("userIdEdit", uid);
        conds.put("inChargeUserIdsEdit", inChargeUserIds+","+uid);
        conds.put("inChargeUserIdsMarkEdit", inChargeUserIdsMark+",["+uid+"]");
        conds.put("inChargeDeptsEdit", inChargeDepts+","+this.getLoginInfo().getDeptId());
    	
        tskGradeList = taskGradeService.getUnDelActiveTaskGrade(); //load获取任务等级列表
        JSONArray jsonArray = pdtTemplateService.getProductTemplatesTree();//加载产品树
        templatesTrees = jsonArray.toString();
        conds.put("marketingId", tskMarketing.getMarketingId());
        tskMarketingBean = tskMarketingService.getTskMarketingBeanById(conds);
        attrList = tskMarketingAttachmentService.getAllTskMarketingAttachmentByConds(conds);
        if(tskMarketingBean.getProductId() > 0){
            request.setAttribute("productTargetType", "productId");
            request.setAttribute("productTargetVal", tskMarketingBean.getProductId());
        }else{
            if(StringUtil.isNullOrEmpty(tskMarketingBean.getSubTemplateName())){
                request.setAttribute("productTargetType", "templateId");
                request.setAttribute("productTargetVal", tskMarketingBean.getTemplateId());
            }else {
                request.setAttribute("productTargetType", "subTemplateName");
                request.setAttribute("productTargetVal", tskMarketingBean.getSubTemplateName());
            }
        }
        
        
        Integer[] depts = deptFacadeService.getInChargeOfDeptIds();
//        Integer[] userIds = deptFacadeService.getInChargeOfDeptUserIds();
        List<SysUser> ls=deptFacadeService.getInChargeOfUserList();
        Integer[] userIds=null;
        if(ls.size()>0){
        	userIds=new Integer[ls.size()];
        	for (int i = 0; i < ls.size(); i++) {
        		userIds[i]=ls.get(i).getUserId();
			}
        }
        List<TskMarketingExecute> listExec = tskMarketingExecuteService.getAllTskMarketingExecuteByConds(conds);//取出执行者
        JSONArray jsonArrayS = new JSONArray();
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> parameters = new HashMap<String, Object>();
        
//        int days = (DateUtil.countDays(new Date(), tskMarketingBean.getEndDate()))+1;
        long days = (DateUtil.countDays(new Date().toLocaleString().toString(), tskMarketingBean.getEndDate().toLocaleString().toString()))+1;
        request.setAttribute("days", days);
        for (TskMarketingExecute tskMarketingExecute : listExec) {
            parameters.clear();
            if(tskMarketingExecute.getUserId() == 0){
                if(isMyInChargeData(depts,userIds,"d",tskMarketingExecute.getDeptId())){
                	String duids=getInChargeUserIdsMark(tskMarketingExecute.getDeptId());
                	String dids=deptFacadeService.getInChargeDeptIdsByDeptId(tskMarketingExecute.getDeptId());
                	String uids=crmCounterUserService.getAllCrmCounterUser(dids);
                	String counterUserIds="";
                	if(!StringUtil.isBlank(uids)){
                		String[] ids=uids.split(",");
                		for (int i = 0; i < ids.length; i++) {
                			counterUserIds +="["+ids[i]+"],";
						}
                		parameters.put("inCounterUserIds", counterUserIds.substring(0, counterUserIds.length()-1));
                	}else{
                		
                		parameters.put("inCounterUserIds", duids);
                	}
                	
                    map.put("id", tskMarketingExecute.getDeptId());
                    map.put("mt", tskMarketingExecute.getMarketingTarget());
                    parameters.put("marketingId", tskMarketingBean.getMarketingId());
                    parameters.put("deptId", tskMarketingExecute.getDeptId());
                    parameters.put("userId", 0);
                    parameters.put("inChargeUserIdsMark", duids);
                    BigDecimal ncm = tskMarketingService.getSaleMoneyByConds(parameters);
                    if(tskMarketingExecute.getMarketingTarget().subtract(ncm).compareTo(new BigDecimal(0)) < 0){//小于零
                        map.put("ds", 0);
                    }else{
                        map.put("ds", tskMarketingExecute.getMarketingTarget().subtract(ncm).divide(new BigDecimal(days), BigDecimal.ROUND_HALF_UP));
                    }
                    map.put("ncm", ncm);
                    map.put("una", tskMarketingExecute.getDeptUnassign());
                    map.put("type", "d");
                    jsonArrayS.add(map);
                }
            }else{
                if(isMyInChargeData(depts,userIds,"u",tskMarketingExecute.getUserId())){
                	//执行者管辖的柜台人员
                	List<CrmCounterUser> list=crmCounterUserService.getCounterUserByUserId(tskMarketingExecute.getUserId(), true);
                	if(list.size()>0){
                		for (CrmCounterUser u:list) {
                			inCounterUserIds +="["+u.getCounterUserId()+"],";
						}
                		parameters.put("inCounterUserIds", inCounterUserIds.substring(0, (inCounterUserIds.length()-1)));
                	}else{
                		parameters.put("inCounterUserIds", "["+tskMarketingExecute.getUserId()+"]");
                	}
                	map.put("id", tskMarketingExecute.getUserId());
                    map.put("mt", tskMarketingExecute.getMarketingTarget());
                    map.put("una", 0);
                    parameters.put("marketingId", tskMarketingBean.getMarketingId());
                    parameters.put("deptId", tskMarketingExecute.getDeptId());
                    parameters.put("userId", tskMarketingExecute.getUserId());
                    parameters.put("inChargeUserIdsMark", "["+tskMarketingExecute.getUserId()+"]");
                    BigDecimal ncm = tskMarketingService.getSaleMoneyByConds(parameters);
                    if(tskMarketingExecute.getMarketingTarget().subtract(ncm).compareTo(new BigDecimal(0)) < 0){//小于零
                        map.put("ds", 0);
                    }else{
                        map.put("ds", tskMarketingExecute.getMarketingTarget().subtract(ncm).divide(new BigDecimal(days), BigDecimal.ROUND_HALF_UP));
                    }
                    map.put("ncm", ncm);
                    map.put("type", "u");
                    jsonArrayS.add(map);
                }
            }
            
        }
        tskAssignTrees = jsonArrayS.toString();//任务分配树
        return SUCCESS;
    }

    /**
     * 获取下属人员mark
     * @param deptId
     * @return
     */
    private String getInChargeUserIdsMark(Integer deptId){
    	String incharge = deptFacadeService.getInChargeDeptIdsByDeptId(deptId);
    	String deptIds=deptFacadeService.getStringUserIdContainsByDeptIds(incharge);
        if(!StringUtil.isNullOrEmpty(deptIds)){
            return "["+deptIds.replace(",", "],[")+"]";
        }else{
            return "["+this.getLoginInfo().getUserId()+"]";
        }
    }
    /**
     * 是否是我有权限的数据
     * @param depts
     * @param userIds
     * @param dType 数据类型 d部门 u人员
     * @return
     */
    private boolean isMyInChargeData(Integer[] depts,Integer[] userIds,String dType,Integer vData){
        boolean result = false;
        if(dType.equals("d")){
        	if(depts!=null){
        		Integer[] deptIds = new Integer[depts.length+1]; 
        		for (int i = 0; i < depts.length; i++) {
        			deptIds[i]=depts[i];
				}
        		deptIds[depts.length]=this.getLoginInfo().getDeptId();
        		for (Integer integer : deptIds) {
                    if(vData.intValue() == integer.intValue()){
                        result = true;
                        break;
                    }
                }
        	}
        }else{
        	if(userIds!=null){
        		Integer[] uIds = new Integer[userIds.length+1]; 
        		for (int i = 0; i < userIds.length; i++) {
        			uIds[i]=userIds[i];
				}
        		uIds[userIds.length]=this.getLoginInfo().getUserId();
        		for (Integer integer : uIds) {
                    if(vData.intValue() == integer.intValue()){
                        result = true;
                        break;
                    }
                }
        	}
        }
        return result;
    }
    
    
    /**
     * 跳转到查看营销任务页面
     * @return
     */
    public String toViewTskMarketingPage() {
    	Map<String, Object> conds = new HashMap<String, Object>();
    	if(deptFacadeService.isInChargeOfDepartment()){
    		conds.put("isCommon", 0);
    	}else{
    		conds.put("isCommon", 1);
    	}
    	Integer uid=this.getLoginInfo().getUserId();
    	String inChargeUserIds = "", inChargeUserIdsMark = "", inChargeDepts = "" ,inCounterUserIds="";
        Integer[] inChargeDeptsIntegers = deptFacadeService.getInChargeOfDeptIds();
        if(inChargeDeptsIntegers!=null){
        	for (Integer deptId : inChargeDeptsIntegers) {
                inChargeDepts += deptId.intValue() + ","; //负责的机构
            }
            if (inChargeDepts.length() > 0) {
                inChargeDepts = inChargeDepts.substring(0, (inChargeDepts.length() - 1));
            }
        }else{
        	inChargeDepts=getLoginInfo().getUserId().toString();
        }
        
        Integer[] inChargeUserIdsIntegers = deptFacadeService.getInChargeOfDeptUserIds();
        if(inChargeUserIdsIntegers!=null){
        	for (Integer userId : inChargeUserIdsIntegers) {
                inChargeUserIds += userId.intValue() + ","; //负责的机构人员
                inChargeUserIdsMark += "[" + userId.intValue() + "],";
            }
            if (inChargeUserIds.length() > 0) {
                inChargeUserIds = inChargeUserIds.substring(0, (inChargeUserIds.length() - 1));
                inChargeUserIdsMark = inChargeUserIdsMark.substring(0, (inChargeUserIdsMark.length() - 1));
            }
        }else{
        	inChargeUserIds = getLoginInfo().getUserId().toString(); //负责的机构人员
            inChargeUserIdsMark += "[" + getLoginInfo().getUserId() + "]";
        }
        
        conds.put("userIdEdit", uid);
        conds.put("inChargeUserIdsEdit", inChargeUserIds+","+uid);
        conds.put("inChargeUserIdsMarkEdit", inChargeUserIdsMark+",["+uid+"]");
        conds.put("inChargeDeptsEdit", inChargeDepts);
    	initRoleType();
        tskGradeList = taskGradeService.getUnDelActiveTaskGrade(); //load获取任务等级列表
        JSONArray jsonArray = pdtTemplateService.getProductTemplatesTree();//加载产品树
        templatesTrees = jsonArray.toString();
        conds.put("marketingId", tskMarketing.getMarketingId());
        tskMarketingBean = tskMarketingService.getTskMarketingBeanById(conds);
        attrList = tskMarketingAttachmentService.getAllTskMarketingAttachmentByConds(conds);
        if(tskMarketingBean.getProductId() > 0){
            request.setAttribute("productTargetType", "productId");
            request.setAttribute("productTargetVal", tskMarketingBean.getProductId());
        }else{
            if(StringUtil.isNullOrEmpty(tskMarketingBean.getSubTemplateName())){
                request.setAttribute("productTargetType", "templateId");
                request.setAttribute("productTargetVal", tskMarketingBean.getTemplateId());
            }else {
                request.setAttribute("productTargetType", "subTemplateName");
                request.setAttribute("productTargetVal", tskMarketingBean.getSubTemplateName());
            }
        }
        
        
        Integer[] depts = null;
        Integer[] userIds = null;
        if(roleType==0){
        	depts = deptFacadeService.getInChargeOfDeptIds();
//            userIds = deptFacadeService.getInChargeOfDeptUserIds();
            List<SysUser> ls=deptFacadeService.getInChargeOfUserList();
            if(ls.size()>0){
            	userIds=new Integer[ls.size()];
            	for (int i = 0; i < ls.size(); i++) {
            		userIds[i]=ls.get(i).getUserId();
    			}
            }
        }else{
        	userIds=new Integer[1];
        	userIds[0]=getLoginInfo().getUserId();
        	request.setAttribute("userName", getLoginInfo().getUserName());
        }
        List<TskMarketingExecute> listExec = tskMarketingExecuteService.getAllTskMarketingExecuteByConds(conds);//取出执行者
        JSONArray jsonArrayS = new JSONArray();
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> parameters = new HashMap<String, Object>();
        
//        int days = (DateUtil.countDays(tskMarketingBean.getStartDate(), tskMarketingBean.getEndDate()) + 1);
        long days = (DateUtil.countDays(new Date().toLocaleString().toString(), tskMarketingBean.getEndDate().toLocaleString().toString()))+1;
        request.setAttribute("days", days);
        for (TskMarketingExecute tskMarketingExecute : listExec) {
            parameters.clear();
            if(tskMarketingExecute.getUserId() == 0){
                if(isMyInChargeData(depts,userIds,"d",tskMarketingExecute.getDeptId())){
                	String duids=getInChargeUserIdsMark(tskMarketingExecute.getDeptId());
                	String dids=deptFacadeService.getInChargeDeptIdsByDeptId(tskMarketingExecute.getDeptId());
                	String uids=crmCounterUserService.getAllCrmCounterUser(dids);
                	String counterUserIds="";
                	if(!StringUtil.isBlank(uids)){
                		String[] ids=uids.split(",");
                		for (int i = 0; i < ids.length; i++) {
                			counterUserIds +="["+ids[i]+"],";
						}
                		parameters.put("inCounterUserIds", counterUserIds.substring(0, counterUserIds.length()-1));
                	}else{
                		
                		parameters.put("inCounterUserIds", duids);
                	}
                	
                    map.put("id", tskMarketingExecute.getDeptId());
                    map.put("mt", tskMarketingExecute.getMarketingTarget());
                    parameters.put("marketingId", tskMarketingBean.getMarketingId());
                    parameters.put("deptId", tskMarketingExecute.getDeptId());
                    parameters.put("userId", 0);
                    parameters.put("inChargeUserIdsMark", duids);
                    BigDecimal ncm = tskMarketingService.getSaleMoneyByConds(parameters);
                    if(days<=0){
                    	map.put("ds", 0);
                    }else{
	                    if(tskMarketingExecute.getMarketingTarget().subtract(ncm).compareTo(new BigDecimal(0)) < 0){//小于零
	                        map.put("ds", 0);
	                    }else{
	                        map.put("ds", tskMarketingExecute.getMarketingTarget().subtract(ncm).divide(new BigDecimal(days), BigDecimal.ROUND_HALF_UP));
	                    }
                    }
                    map.put("ncm", ncm);
                    map.put("una", tskMarketingExecute.getDeptUnassign());
                    map.put("type", "d");
                    jsonArrayS.add(map);
                }
            }else{
                if(isMyInChargeData(depts,userIds,"u",tskMarketingExecute.getUserId())){
                	//执行者管辖的柜台人员
                	List<CrmCounterUser> list=crmCounterUserService.getCounterUserByUserId(tskMarketingExecute.getUserId(), true);
                	if(list.size()>0){
                		for (CrmCounterUser u:list) {
                			inCounterUserIds +="["+u.getCounterUserId()+"],";
						}
                		parameters.put("inCounterUserIds", inCounterUserIds.substring(0, (inCounterUserIds.length()-1)));
                	}else{
                		parameters.put("inCounterUserIds", "["+tskMarketingExecute.getUserId()+"]");
                	}
                    map.put("id", tskMarketingExecute.getUserId());
                    map.put("mt", tskMarketingExecute.getMarketingTarget());
                    map.put("una", 0);
                    parameters.put("marketingId", tskMarketingBean.getMarketingId());
                    parameters.put("deptId", tskMarketingExecute.getDeptId());
                    parameters.put("userId", tskMarketingExecute.getUserId());
                    parameters.put("inChargeUserIdsMark", "["+tskMarketingExecute.getUserId()+"]");
                    BigDecimal ncm = tskMarketingService.getSaleMoneyByConds(parameters);
                    if(days<=0){
                    	map.put("ds", 0);
                    }else{
	                    if(tskMarketingExecute.getMarketingTarget().subtract(ncm).compareTo(new BigDecimal(0)) < 0){//小于零
	                        map.put("ds", 0);
	                    }else{
	                        map.put("ds", tskMarketingExecute.getMarketingTarget().subtract(ncm).divide(new BigDecimal(days), BigDecimal.ROUND_HALF_UP));
	                    }
                    }
                    map.put("ncm", ncm);
                    map.put("type", "u");
                    jsonArrayS.add(map);
                }
            }
            
        }
        tskAssignTrees = jsonArrayS.toString();//任务分配树
        
        //营销计划list
        try {
            Integer userId = this.getLoginInfo().getUserId();

            // 任务对于产品list
            Map<String, Object> parameterMap = new HashMap<String, Object>();
            boolean isInChargeof = deptFacadeService.isInChargeOfDepartment();
            if (isInChargeof) {
                String userIdss = deptFacadeService.getInChargeOfDeptUserIds(userId, 0);
                parameterMap.put("userIds", userIdss);
            } else {
                parameterMap.put("userIds", userId);
            }
            parameterMap.put("marketingId", tskMarketing.getMarketingId());

            PageUtil<TskMarketingPlan> list = tskMarketingPlanService.getMarketingPlanPage(
                parameterMap, this.getPage());

            //所有产品数量
//            Integer productCount = tskMarketingPlanService.getProductCountByMap(parameterMap);

            request.setAttribute("dataList", list.getItems());
//            request.setAttribute("productCount", productCount);
        } catch (Exception e) {
            log.error("planList error", e);
        }
        return SUCCESS;
    }
    
    /**
     * 获取当天产品营销计划
     */
    public void getTskPlanListToday(String myAuth) {
        try {
            Integer userId = this.getLoginInfo().getUserId();
            
            Map<String, Object> parameterMap = new HashMap<String, Object>();
            
            Calendar calFrom = Calendar.getInstance();
            calFrom.set(Calendar.HOUR_OF_DAY, 0);
            calFrom.set(Calendar.MINUTE, 0);
            calFrom.set(Calendar.SECOND, 0);
            calFrom.set(Calendar.MILLISECOND, 0);
            parameterMap.put("planDateFrom", calFrom.getTime());

            Calendar calTo = Calendar.getInstance();
            calTo.set(Calendar.HOUR_OF_DAY, 23);
            calTo.set(Calendar.MINUTE, 59);
            calTo.set(Calendar.SECOND, 59);
            calTo.set(Calendar.MILLISECOND, 999);
            parameterMap.put("planDateTo", calTo.getTime());
            
            
            if (myAuth.equals("客户经理")) {
                parameterMap.put("userIds", userId);
                tskMarketPlanDetailToday = tskMarketingPlanDetailService.getMarketingPlanDetail(parameterMap);
            } else {
                String userIds = deptFacadeService.getInChargeOfDeptUserIds(userId, 0);
                parameterMap.put("userIds", userIds);
                tskMarketPlanDetailToday = tskMarketingPlanDetailService.getMarketingPlanDetail(parameterMap);
            }
            if(tskMarketPlanDetailToday!=null){
            	int tc=tskMarketPlanDetailToday.size();
            	if(tc>6){
            		tc=6;
            	}
            	for (int i = 0; i < tc; i++) {
            		TskMarketingPlanDetail plan=tskMarketPlanDetailToday.get(i);
            		// 实际销售额
                    BigDecimal todaySaleMoney = tskMarketingPlanDetailService.getTodaySaleMoney(
                    		plan.getProductId(), plan.getExecuteUserId());
                    plan.setTodaySaleMoney(todaySaleMoney);
				}
            }
        } catch (Exception e) {
            log.error("getTskPlanListToday action error", e);
        }
    }

    /**
     * 未分配的任务列表
     * @return
     */
    public String queryTskMarketingListNotAssign() {
        try {
            tskGradeList = taskGradeService.getUnDelActiveTaskGrade(); //load获取任务等级列表
            request.setAttribute("userId", this.getLoginInfo().getUserId());
            String inChargeUserIds = "", inChargeUserIdsMark = "", inChargeDepts = "";
            Integer[] inChargeDeptsIntegers = deptFacadeService.getInChargeOfDeptIds();
            for (Integer deptId : inChargeDeptsIntegers) {
                inChargeDepts += deptId.intValue() + ","; //负责的机构
            }
            if (inChargeDepts.length() > 0) {
                inChargeDepts = inChargeDepts.substring(0, (inChargeDepts.length() - 1));
            }

            Integer[] inChargeUserIdsIntegers = deptFacadeService.getInChargeOfDeptUserIds();
            for (Integer userId : inChargeUserIdsIntegers) {
                inChargeUserIds += userId.intValue() + ","; //负责的机构人员
                inChargeUserIdsMark += "[" + userId.intValue() + "],";
            }
            if (inChargeUserIds.length() > 0) {
                inChargeUserIds = inChargeUserIds.substring(0, (inChargeUserIds.length() - 1));
                inChargeUserIdsMark = inChargeUserIdsMark.substring(0, (inChargeUserIdsMark
                    .length() - 1));
            }

            Map<String, Object> conds = new HashMap<String, Object>();
            conds.put("userId", this.getLoginInfo().getUserId());
            conds.put("inChargeUserIds", inChargeUserIds);
            conds.put("inChargeUserIdsMark", inChargeUserIdsMark);
            conds.put("inChargeDepts", inChargeDepts);

            if (tskMarketingBean != null) {
                if (tskMarketingBean.getGradeId() != -1) {//查所有
                    conds.put("gradeId", tskMarketingBean.getGradeId());
                }
                if (tskMarketingBean.getAssignUserId() != -1) {//查所有
                    if (tskMarketingBean.getAssignUserId() == 0) {//0表示我的
                        conds.put("assignUserId", this.getLoginInfo().getUserId());
                    }
                }
                conds.put("marketingTitle", tskMarketingBean.getMarketingTitle());
                conds.put("productTarget", tskMarketingBean.getProductTarget());
            }
            conds.put("startDate", request.getParameter("createStartDate"));
            conds.put("endDate", request.getParameter("createEndDate"));
            
            conds.put("unStartTsk", "unStart");

            //未分配查询条件
            conds.put("notAssign", 1);

            tskMarketingBeanPage = tskMarketingService.GetAllTskMarketingPageByConds(conds, this
                .getPage());
            int count = this.getPage().getTotalRowsAmount();
            request.setAttribute("count", String.valueOf(count));
            return SUCCESS;
        } catch (Exception e) {
            log.error("allTskMarketingList action error", e);
            return ERROR;
        }
    }

    /**
     * 我制定的任务列表
     * @return
     */
    public String queryTskMarketingListMyCreate() {
        try {
        	Integer uid=this.getLoginInfo().getUserId();
            tskGradeList = taskGradeService.getUnDelActiveTaskGrade(); //load获取任务等级列表
            request.setAttribute("userId", this.getLoginInfo().getUserId());
            String inChargeUserIds = "", inChargeUserIdsMark = "", inChargeDepts = "";
            Integer[] inChargeDeptsIntegers = deptFacadeService.getInChargeOfDeptIds();
            for (Integer deptId : inChargeDeptsIntegers) {
                inChargeDepts += deptId.intValue() + ","; //负责的机构
            }
            if (inChargeDepts.length() > 0) {
                inChargeDepts = inChargeDepts.substring(0, (inChargeDepts.length() - 1));
            }

            Integer[] inChargeUserIdsIntegers = deptFacadeService.getInChargeOfDeptUserIds();
            for (Integer userId : inChargeUserIdsIntegers) {
                inChargeUserIds += userId.intValue() + ","; //负责的机构人员
                inChargeUserIdsMark += "[" + userId.intValue() + "],";
            }
            if (inChargeUserIds.length() > 0) {
                inChargeUserIds = inChargeUserIds.substring(0, (inChargeUserIds.length() - 1));
                inChargeUserIdsMark = inChargeUserIdsMark.substring(0, (inChargeUserIdsMark
                    .length() - 1));
            }

            Map<String, Object> conds = new HashMap<String, Object>();
            conds.put("userId", uid);
            conds.put("inChargeUserIds", inChargeUserIds+","+uid);
            conds.put("inChargeUserIdsMark", inChargeUserIdsMark+",["+uid+"]");
            conds.put("inChargeDepts", inChargeDepts);

            if (tskMarketingBean != null) {
                if (tskMarketingBean.getGradeId() != -1) {//查所有
                    conds.put("gradeId", tskMarketingBean.getGradeId());
                }
                if (tskMarketingBean.getAssignUserId() != -1) {//查所有
                    if (tskMarketingBean.getAssignUserId() == 0) {//0表示我的
                        conds.put("assignUserId", this.getLoginInfo().getUserId());
                    }
                }
                conds.put("marketingTitle", tskMarketingBean.getMarketingTitle());
                conds.put("productTarget", tskMarketingBean.getProductTarget());
            }
            conds.put("startDate", request.getParameter("createStartDate"));
            conds.put("endDate", request.getParameter("createEndDate"));
            if (StringUtils.isEmpty(request.getParameter("completion"))) {
                conds.put("completion", "execing");
            } else {
                conds.put("completion", request.getParameter("completion"));
            }
            conds.put("complete", request.getParameter("complete"));

            //只查我制定的任务
            conds.put("assignUserId", this.getLoginInfo().getUserId());

            tskMarketingBeanPage = tskMarketingService.GetAllTskMarketingPageByConds(conds, this
                .getPage());
            int count = this.getPage().getTotalRowsAmount();
            request.setAttribute("count", String.valueOf(count));
            return SUCCESS;
        } catch (Exception e) {
            log.error("allTskMarketingList action error", e);
            return ERROR;
        }
    }

    /**
     * 我执行的任务列表
     * @return
     */
    public String queryTskMarketingListMyExecute() {
        try {
            tskGradeList = taskGradeService.getUnDelActiveTaskGrade(); //load获取任务等级列表

            int userId = this.getLoginInfo().getUserId();
            String userIdMark = "[" + userId + "]";

            Map<String, Object> conds = new HashMap<String, Object>();
            conds.put("userId", this.getLoginInfo().getUserId());
            conds.put("userIdMark", userIdMark);
            //判断是否是业务主管
            if(deptFacadeService.isInChargeOfDepartment()){
        		conds.put("isCommon", 0);
        	}else{
        		conds.put("isCommon", 1);
        	}

            if (tskMarketingBean != null) {
                if (tskMarketingBean.getGradeId() != -1) {//查所有
                    conds.put("gradeId", tskMarketingBean.getGradeId());
                }
                if (tskMarketingBean.getAssignUserId() != -1) {//查所有
                    if (tskMarketingBean.getAssignUserId() == 0) {//0表示我的
                        conds.put("assignUserId", this.getLoginInfo().getUserId());
                    }
                }
                conds.put("marketingTitle", tskMarketingBean.getMarketingTitle());
                conds.put("productTarget", tskMarketingBean.getProductTarget());
            }
            conds.put("startDate", request.getParameter("createStartDate"));
            conds.put("endDate", request.getParameter("createEndDate"));
            if (StringUtils.isEmpty(request.getParameter("completion"))) {
                conds.put("completion", "execing");
            } else {
                conds.put("completion", request.getParameter("completion"));
            }
            conds.put("complete", request.getParameter("complete"));

            tskMarketingBeanPage = tskMarketingService.GetAllTskMarketingPageByCondsMyExecute(
                conds, this.getPage());
            int count = this.getPage().getTotalRowsAmount();
            request.setAttribute("count", String.valueOf(count));
            return SUCCESS;
        } catch (Exception e) {
            log.error("allTskMarketingList action error", e);
            return ERROR;
        }
    }

    /**
     * 重启or中止
     * @return
     */
    public void stopOrReStart() {
        try {
            if (tskMarketing != null) {
                //IsSuspend=1表示现在的状态是中止，那么应该重启
                if (tskMarketing.getIsSuspend() == 1) {
                    tskMarketingService.reStartTskMarketingById(tskMarketing.getMarketingId());
                } else if (tskMarketing.getIsSuspend() == 0) {
                    tskMarketingService.stopTskMarketingById(tskMarketing.getMarketingId());
                }
                HttpServletResponse response = ServletActionContext.getResponse();
                response.reset();
                response.setContentType("text/html;charset=utf-8");
                PrintWriter out = response.getWriter();

                out.print(1);
                out.close();
            }
        } catch (Exception e) {
            log.error("stopOrReStart action error", e);
        }
    }

    /**
     * 删除营销任务
     */
    public void delTskMarketing() {
        try {
            if (tskMarketing != null) {
                tskMarketingService.delTskMarketingById(tskMarketing.getMarketingId(),1);

                HttpServletResponse response = ServletActionContext.getResponse();
                response.reset();
                response.setContentType("text/html;charset=utf-8");
                PrintWriter out = response.getWriter();

                out.print(1);
                out.close();
            }
        } catch (Exception e) {
            log.error("delTskMarketing action error", e);
        }
    }
    
    /**
     * 验证营销任务是否有销售额
     */
    public void validateMarketingSaleMoney(){
    	try {
    		HttpServletResponse response = ServletActionContext.getResponse();
    		PrintWriter out = response.getWriter();
    		BigDecimal	b=tskMarketingService.getMarketingSaleMoney(tskMarketing.getMarketingId());
    		out.print(b);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    }

    public TskMarketing getTskMarketing() {
        return tskMarketing;
    }

    public void setTskMarketing(TskMarketing tskMarketing) {
        this.tskMarketing = tskMarketing;
    }

    public TskMarketingPlanDetailService getTskMarketingPlanDetailService() {
        return tskMarketingPlanDetailService;
    }

    public void setTskMarketingPlanDetailService(
                                                 TskMarketingPlanDetailService tskMarketingPlanDetailService) {
        this.tskMarketingPlanDetailService = tskMarketingPlanDetailService;
    }

    public TaskGradeService getTaskGradeService() {
        return taskGradeService;
    }

    public void setTaskGradeService(TaskGradeService taskGradeService) {
        this.taskGradeService = taskGradeService;
    }

    public TskMarketingService getTskMarketingService() {
        return tskMarketingService;
    }

    public void setTskMarketingService(TskMarketingService tskMarketingService) {
        this.tskMarketingService = tskMarketingService;
    }

    public PageUtil<TskMarketingBean> getTskMarketingBeanPage() {
        return tskMarketingBeanPage;
    }

    public void setTskMarketingBeanPage(PageUtil<TskMarketingBean> tskMarketingBeanPage) {
        this.tskMarketingBeanPage = tskMarketingBeanPage;
    }

    public DeptFacadeService getDeptFacadeService() {
        return deptFacadeService;
    }

    public void setDeptFacadeService(DeptFacadeService deptFacadeService) {
        this.deptFacadeService = deptFacadeService;
    }

    public TskMarketingBean getTskMarketingBean() {
        return tskMarketingBean;
    }

    public void setTskMarketingBean(TskMarketingBean tskMarketingBean) {
        this.tskMarketingBean = tskMarketingBean;
    }

    public List<TaskGrade> getTskGradeList() {
        return tskGradeList;
    }

    public void setTskGradeList(List<TaskGrade> tskGradeList) {
        this.tskGradeList = tskGradeList;
    }

    public String getUserAuth() {
        return userAuth;
    }

    public void setUserAuth(String userAuth) {
        this.userAuth = userAuth;
    }

    public PageUtil<TskMarketingBean> getMyTskMarketingBeanPage() {
        return myTskMarketingBeanPage;
    }

    public void setMyTskMarketingBeanPage(PageUtil<TskMarketingBean> myTskMarketingBeanPage) {
        this.myTskMarketingBeanPage = myTskMarketingBeanPage;
    }

    public TskMarketingPlanService getTskMarketingPlanService() {
        return tskMarketingPlanService;
    }

    public void setTskMarketingPlanService(TskMarketingPlanService tskMarketingPlanService) {
        this.tskMarketingPlanService = tskMarketingPlanService;
    }

    public List<TskMarketingPlanDetail> getTskMarketPlanDetailToday() {
        return tskMarketPlanDetailToday;
    }

    public void setTskMarketPlanDetailToday(List<TskMarketingPlanDetail> tskMarketPlanDetailToday) {
        this.tskMarketPlanDetailToday = tskMarketPlanDetailToday;
    }

    public PdtTemplateService getPdtTemplateService() {
        return pdtTemplateService;
    }

    public void setPdtTemplateService(PdtTemplateService pdtTemplateService) {
        this.pdtTemplateService = pdtTemplateService;
    }

    public PdtProductService getPdtProductService() {
        return pdtProductService;
    }

    public void setPdtProductService(PdtProductService pdtProductService) {
        this.pdtProductService = pdtProductService;
    }

    public TskMarketingAttachmentService getTskMarketingAttachmentService() {
        return tskMarketingAttachmentService;
    }

    public void setTskMarketingAttachmentService(
                                                 TskMarketingAttachmentService tskMarketingAttachmentService) {
        this.tskMarketingAttachmentService = tskMarketingAttachmentService;
    }

    public TskContactExecuteService getTskContactExecuteService() {
        return tskContactExecuteService;
    }

    public void setTskContactExecuteService(TskContactExecuteService tskContactExecuteService) {
        this.tskContactExecuteService = tskContactExecuteService;
    }

    public String getTemplatesTrees() {
        return templatesTrees;
    }

    public void setTemplatesTrees(String templatesTrees) {
        this.templatesTrees = templatesTrees;
    }

    public String getInChargeTrees() {
        return inChargeTrees;
    }

    public void setInChargeTrees(String inChargeTrees) {
        this.inChargeTrees = inChargeTrees;
    }

    public String getAssignTargetJson() {
        return assignTargetJson;
    }

    public void setAssignTargetJson(String assignTargetJson) {
        this.assignTargetJson = assignTargetJson;
    }

    public TskMarketingExecuteService getTskMarketingExecuteService() {
        return tskMarketingExecuteService;
    }

    public void setTskMarketingExecuteService(TskMarketingExecuteService tskMarketingExecuteService) {
        this.tskMarketingExecuteService = tskMarketingExecuteService;
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

    public String getFileAttachments() {
        return fileAttachments;
    }

    public void setFileAttachments(String fileAttachments) {
        this.fileAttachments = fileAttachments;
    }

    public static int getBUFFERED_SIZE() {
        return BUFFERED_SIZE;
    }

    public List<TskMarketingAttachment> getAttrList() {
        return attrList;
    }

    public void setAttrList(List<TskMarketingAttachment> attrList) {
        this.attrList = attrList;
    }

    public String getTskAssignTrees() {
        return tskAssignTrees;
    }

    public void setTskAssignTrees(String tskAssignTrees) {
        this.tskAssignTrees = tskAssignTrees;
    }

	public Integer getRoleType() {
		return roleType;
	}

	public void setRoleType(Integer roleType) {
		this.roleType = roleType;
	}

	public String getProTargetType() {
		return proTargetType;
	}

	public void setProTargetType(String proTargetType) {
		this.proTargetType = proTargetType;
	}

	public CrmCounterUserService getCrmCounterUserService() {
		return crmCounterUserService;
	}

	public void setCrmCounterUserService(CrmCounterUserService crmCounterUserService) {
		this.crmCounterUserService = crmCounterUserService;
	}

}
