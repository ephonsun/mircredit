package com.banger.mobile.webapp.action.loan;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.model.loan.LnLoan;
import com.banger.mobile.domain.model.loan.LnLoanInfoDictionary;
import com.banger.mobile.facade.dept.DeptFacadeService;
import com.banger.mobile.facade.loan.LnLoanInfoDictionaryService;
import com.banger.mobile.facade.loan.LnLoanService;
import com.banger.mobile.facade.loan.LnLoanTypeService;
import com.banger.mobile.webapp.action.BaseAction;

/**
 * Created with IntelliJ IDEA.
 * User: yuanme
 * Date: 13-2-5
 * Time: 下午4:13
 * To change this template use File | Settings | File Templates.
 *
 * 未提交贷款 Action
 */
public class NeedSubmitLoanAction extends BaseAction{

    private static Logger logger = Logger.getLogger(NeedSubmitLoanAction.class);

    private LnLoanService lnLoanService;
    private LnLoanTypeService lnLoanTypeService;
    private DeptFacadeService deptFacadeService;
    private LnLoanInfoDictionaryService lnLoanInfoDictionaryService; 
    
    //查询条件
    private String customer;
    private Integer loanType;
    private Date startDate;
    private Date endDate;


    /**
     * 首页列表
     * @return
     */
    public String needSubmitLoanList(){
        try{
            logger.info("-----当前登录用户："+this.getLoginInfo().getAccount()+"----- 进入申请贷款菜单...");
            //贷款类型
            Map<String,Object> paramMap = new HashMap<String,Object>();
            paramMap.put("dictionaryName", "DKLX");
    		List<LnLoanInfoDictionary> lnLoanInfoDictionaryList = lnLoanInfoDictionaryService.selectLoanInfoDictionaryList(paramMap);

            //列表数据
            Map<String, Object> conds = new HashMap<String, Object>();
            // 是否是业务主管
            Boolean isInChargeOf = deptFacadeService.isInChargeOfDepartment();
            if (isInChargeOf) {
                // 当前用户的下属用户
                String belongUserIds = deptFacadeService.getUserIdsBelongToManager(this.getLoginInfo().getRoles(),"loanInfo");
                conds.put("createUserIds", belongUserIds); // 当前用户所管理的提交申请用户
            } else {
                // 客户经理
                conds.put("createUserId", this.getLoginInfo().getUserId());
            }
            conds.put("loanStatusId", 1);   // 1表示待提交
            PageUtil<LnLoan> dataList = lnLoanService.getApplyLoanByPage(conds, this.getPage());
            logger.info("-----当前登录用户："+this.getLoginInfo().getAccount()+"----- 查询到所有申请状态贷款，数量为"+dataList.getItems().size()+"条");
            //判断是否到了指定需要高亮显示的时间：5小时
            if(dataList != null && dataList.getItems() != null){
                for (LnLoan lnLoan : dataList.getItems()) {
                    Date createDate = lnLoan.getCreateDate();
                    Date nowDate = new Date();
                    if((nowDate.getTime() - createDate.getTime()) >= 1000 * 60 * 60 * 5){
                        //超过5小时未提交,前端在高亮显示
                        lnLoan.setIsWillPast(1);
                    }
                }
            }
            request.setAttribute("typeList", lnLoanInfoDictionaryList);
            request.setAttribute("dataList", dataList.getItems());
            request.setAttribute("recordCount", dataList.getPage().getTotalRowsAmount());
            request.setAttribute("sysUserId",this.getLoginInfo().getUserId());
            return SUCCESS;
        }catch (Exception e){
            logger.error("needSubmitLoanList", e);
            e.printStackTrace();
            return ERROR;
        }
    }

    /**
     * 查询列表
     * @return
     */
    public String needSubmitLoanListQuery(){
        try{
            logger.info("-----当前登录用户："+this.getLoginInfo().getAccount()+"----- 按条件搜索申请贷款...");
            //列表数据
            Map<String, Object> conds = new HashMap<String, Object>();
            // 是否是业务主管
            Boolean isInChargeOf = deptFacadeService.isInChargeOfDepartment();
            if (isInChargeOf) {
                // 当前用户的下属用户
                String belongUserIds = deptFacadeService.getUserIdsBelongToManager(this.getLoginInfo().getRoles(),"loanInfo");
                conds.put("createUserIds", belongUserIds); // 当前用户所管理的提交申请用户
            } else {
                // 客户经理
                conds.put("createUserId", this.getLoginInfo().getUserId());
            }
            conds.put("loanStatusId", 1);   // 1表示待提交
            conds.put("customer", customer);
            conds.put("loanTypeId", loanType);
            conds.put("startCreateDate", startDate);
            endDate = lnLoanService.addSecondsForDate(endDate,59);
            conds.put("endCreateDate", endDate);
            logger.info("-----当前登录用户："+this.getLoginInfo().getAccount()+"----- 搜索条件，客户："+customer+",贷款类型："+loanType+",创建时间："+startDate+"——"+endDate);
            PageUtil<LnLoan> dataList = lnLoanService.getApplyLoanByPage(conds, this.getPage());
            logger.info("-----当前登录用户："+this.getLoginInfo().getAccount()+"----- 总共搜索出"+dataList.getItems().size()+"条贷款");
            //判断是否到了指定需要高亮显示的时间：5小时
            if(dataList != null && dataList.getItems() != null){
                for (LnLoan lnLoan : dataList.getItems()) {
                    Date createDate = lnLoan.getCreateDate();
                    Date nowDate = new Date();
                    if((nowDate.getTime() - createDate.getTime()) >= 1000 * 60 * 60 * 5){
                        //超过5小时未提交,前端在高亮显示
                        lnLoan.setIsWillPast(1);
                    }
                }
            }
            request.setAttribute("dataList", dataList.getItems());
            request.setAttribute("recordCount", dataList.getPage().getTotalRowsAmount());
            request.setAttribute("sysUserId",this.getLoginInfo().getUserId());

            return SUCCESS;
        }catch (Exception e){
            logger.error("needSubmitLoanListQuery", e);
            return ERROR;
        }
    }

    /** getter setter **/
    public void setLnLoanService(LnLoanService lnLoanService) {
        this.lnLoanService = lnLoanService;
    }

    public void setLnLoanTypeService(LnLoanTypeService lnLoanTypeService) {
        this.lnLoanTypeService = lnLoanTypeService;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Integer getLoanType() {
        return loanType;
    }

    public void setLoanType(Integer loanType) {
        this.loanType = loanType;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public DeptFacadeService getDeptFacadeService() {
        return deptFacadeService;
    }

    public void setDeptFacadeService(DeptFacadeService deptFacadeService) {
        this.deptFacadeService = deptFacadeService;
    }

	public LnLoanInfoDictionaryService getLnLoanInfoDictionaryService() {
		return lnLoanInfoDictionaryService;
	}

	public void setLnLoanInfoDictionaryService(
			LnLoanInfoDictionaryService lnLoanInfoDictionaryService) {
		this.lnLoanInfoDictionaryService = lnLoanInfoDictionaryService;
	}
    
    
}
