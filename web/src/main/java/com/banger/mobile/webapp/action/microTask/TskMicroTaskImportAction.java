/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :导入任务客户名单
 * Author     :YanQiFan
 * Create Date:2013-3-12
 */
package com.banger.mobile.webapp.action.microTask;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.banger.mobile.domain.Enum.customer.EnumCustomer;
import com.banger.mobile.domain.model.customer.CounterOutPintMessage;
import com.banger.mobile.domain.model.customer.CustomerExportBar;
import com.banger.mobile.domain.model.microTask.TskMicroTaskTarget;
import com.banger.mobile.facade.impl.task.microTask.TskMicroTaskTargetServiceImpl;
import com.banger.mobile.facade.microTask.TskMicroTaskTargetService;
import com.banger.mobile.importUtil.ReadExcel;
import com.banger.mobile.util.FileUtil;
import com.banger.mobile.webapp.action.BaseAction;

/**
 * @author YanQiFan
 * @version $Id: TskMicroTaskImportAction.java,v 0.1 2013-3-12 下午1:25:01
 *          Administrator Exp $
 */
public class TskMicroTaskImportAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private File excelFile; // 上传的文件
	private String filePath; // 文件路径（action间的请求参数）
	private String excelFileName; // 文件名称
	private TskMicroTaskTarget taskTarget; // 客户名单Bean
	private TskMicroTaskTargetService tskMicroTaskTargetService; // 业务逻辑
	private List<String> headList = new ArrayList<String>(); // 表头
	private Map<String, String> headMap = null; // 数据库中的字段
	private CounterOutPintMessage counterOutPintMessage; // 输出结果

	/**
	 * 跳转到导入页面
	 * 
	 * @return
	 */
	public String toImportPage() {
		return SUCCESS;
	}

	/**
	 * 下载模板
	 * 
	 * @return
	 */
	public String tskExportTemp() {
		try {
			HttpServletRequest req = ServletActionContext.getRequest();
			@SuppressWarnings("deprecation")
			String path = req.getRealPath("") + "/template/crm_tskcustomer.xls";
			File file = new File(path);
			if (!file.exists()) {
				this.getResponse().setContentType("text/html;charset=GBK");
				this.getResponse().getWriter().print("指定的文件不存在！");
			} else {
				ServletOutputStream out = this.getResponse().getOutputStream();
				this.getResponse().setHeader(
						"Content-Disposition",
						"attachment; filename="
								+ java.net.URLEncoder.encode("导入客户名单模版.xls",
										"UTF-8"));
				BufferedInputStream ios = null;
				BufferedOutputStream bos = null;
				ios = new BufferedInputStream(new FileInputStream(path));
				bos = new BufferedOutputStream(out);
				byte[] buff = new byte[2048];
				int bytesRead;
				while (((bytesRead = ios.read(buff, 0, buff.length)) != -1)) {
					bos.write(buff, 0, bytesRead);
				}
				ios.close();
				bos.close();
			}
		} catch (Exception e) {
			log.error("exportFailExcel action error:" + e.getMessage());
			return ERROR;
		} finally {

		}
		return null;
	}

	/**
	 * 提交Excel并做数据对比
	 * 
	 * @return
	 */
	@SuppressWarnings({ "deprecation", "unchecked" })
	public String importContrast() {
		try {
			// 获取任务ID
			taskTarget = new TskMicroTaskTarget();
			taskTarget
					.setTaskId(Integer.valueOf(request.getParameter("taskId")));
			// 获取表头
			if (excelFile != null) {
				headList = ReadExcel.readColumnNames(excelFile.getPath());
			}
			if (headList == null) {
				this.request.setAttribute("failureMsg",
						"您导入的文件格式不正确，可下载模板使用模板导入！");
				return "failure";
			} else if (headList.size() == 0) {
				this.request.setAttribute("failureMsg", "您导入的文件是空文件，请重新导入！");
				return "failure";
			} else {
				// 获取文件路径
				File target = new File(this.request.getRealPath("/upload/"));
				if (!target.exists()) {
					target.mkdir();
				}
				String fileName = this.request.getRealPath("/upload/"
						+ excelFile.getName());
				// 用于Action中传递的文件名称
				excelFileName = excelFile.getName();
				// 将文件拷贝到本地
				FileUtil.copyFile(excelFile, new File(fileName));
				// 传递文件路径
				this.request.setAttribute("filePath", fileName);
				Map<String, String> map = (Map<String, String>) request
						.getSession().getAttribute("recordMap");
				request.setAttribute("total", map.get("total"));
				request.setAttribute("bathchCount", map.get("bathchCount"));
				request.setAttribute("nullCount", map.get("nullCount"));
				request.setAttribute("headMap", initSysData());
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.request.setAttribute("failureMsg",
					"导入暂时不支持Office2007或以上的版本的Excel,请先转换成Office97-2003版本。");
			return ERROR;
		}

		return SUCCESS;
	}

	// 初始化系统表字段
	private LinkedHashMap<String, String> initSysData() {
		headMap = new LinkedHashMap<String, String>();
		headMap.put("crmName", "客户姓名");
		headMap.put("phoneNumber", "联系电话");
		headMap.put("remark", "备注");
		return (LinkedHashMap<String, String>) headMap;
	}

	/**
	 * 导入验证
	 * 
	 * @return
	 */
	public String importTskCustVerify() {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		try {
			PrintWriter out = response.getWriter();
			Map<Integer, CustomerExportBar> customerExportBar = TskMicroTaskTargetServiceImpl
					.getCustomerExportBar();
			CustomerExportBar bar = null;
			if (customerExportBar.containsKey(getLoginInfo().getUserId())) {
				bar = customerExportBar.get(getLoginInfo().getUserId());
				bar.setCuurRow(0);
			} else {
				bar = new CustomerExportBar();
				bar.setIsRun(0);
				bar.setIsStop(0);
				customerExportBar.put(getLoginInfo().getUserId(), bar);
			}
			if (bar != null) {
				if (bar.getIsRun().equals(1) && bar.getIsStop().equals(0)) {
					bar.setIsRun(0);
					bar.setIsStop(1);
					out.print(EnumCustomer.IMPORT_WARN.getValue());
				}
			}
			bar.setIsRun(1);
			bar.setIsStop(0);
			return null;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 导入Excel数据到数据库
	 * 
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public String execlToDataBase() {
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			counterOutPintMessage = null;
			JSONObject jso = new JSONObject();
			Map<Integer, CustomerExportBar> customerExportBar = TskMicroTaskTargetServiceImpl
					.getCustomerExportBar();
			CustomerExportBar bar = customerExportBar.get(this.getLoginInfo()
					.getUserId());
			if (bar.getIsStop().equals(0)) {
				// 远方传来的excelFileName
				String fileName = this.request.getRealPath("/upload/"
						+ excelFileName);
				counterOutPintMessage = tskMicroTaskTargetService.excelToDb(
						fileName, taskTarget);
				bar.setIsRun(0);
				bar.setIsStop(1);
			} else {
				bar.setIsRun(0);
			}
			if (counterOutPintMessage != null) {
				jso.put("successRecordCount",
						counterOutPintMessage.getSuccessRecordCount());
				jso.put("failRecordCount",
						counterOutPintMessage.getFailRecordCount());
				jso.put("errorFilePath",
						counterOutPintMessage.getErrorFilePath());
			}
			out.print(jso.toString());
		} catch (Exception e) {
			e.printStackTrace();
			log.error("execlToDataBaseTask action error:" + e.getMessage());
			return null;
		}
		return null;
	}

	/**
	 * 导入进度条
	 * 
	 * @return
	 */
	public String importTskCustomerBar() {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		try {
			PrintWriter out = response.getWriter();
			JSONObject jso = new JSONObject();
			Map<Integer, CustomerExportBar> customerExportBar = TskMicroTaskTargetServiceImpl
					.getCustomerExportBar();
			CustomerExportBar bar = customerExportBar.get(getLoginInfo()
					.getUserId());
			if (bar != null) {
				jso.put("curr", bar.getCuurRow());
			}
			out.print(jso.toString());
			return null;
		} catch (Exception e) {
			return ERROR;
		}
	}

	/**
	 * 终止导入
	 * 
	 * @return
	 */
	public String importTskCustomerStop() {
		try {
			Map<Integer, CustomerExportBar> customerExportBar = TskMicroTaskTargetServiceImpl
					.getCustomerExportBar();
			CustomerExportBar bar = customerExportBar.get(this.getLoginInfo()
					.getUserId());
			if (bar.getIsRun().equals(1)) {
				bar.setCuurRow(0);
				bar.setIsRun(0);
				bar.setIsStop(1);// 正在导出时终止
			}
			return null;
		} catch (Exception e) {
			log.error("importCustomerTaskStop action error:" + e.getMessage());
			return ERROR;
		}
	}

	/**
	 * 导出失败
	 * 
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public String exportFailTaskExcel() {
		try {
			// 服务器的绝对路径
			String path = request.getParameter("filePath");
			// 读取文件名：用于设置客户端保存时指定默认文件名
			int index = path.lastIndexOf("/"); // 前提：传入的path字符串以“\”表示目录分隔符
			// 错误信息Excel文件的名称
			String fileName = path.substring(index + 1);
			// 通过名称找到路径
			String errorFilePath = this.request.getRealPath("/") + "Records/"
					+ fileName;
			// 检查文件是否存在
			File obj = new File(errorFilePath);
			HttpServletResponse res = this.getResponse();
			if (!obj.exists()) {
				PrintWriter outt = res.getWriter();
				this.getResponse().setContentType("text/html;charset=GBK");
				outt.print("指定文件不存在！");
			} else {
				// 写流文件到前端浏览器
                long ctm = System.currentTimeMillis();
				ServletOutputStream out = res.getOutputStream();
				this.getResponse().setHeader(
						"Content-Disposition",
						"attachment; filename="
								+ java.net.URLEncoder.encode("导入失败的客户资料" + ctm
                                + ".xls", "UTF-8"));
				BufferedInputStream bis = null;
				BufferedOutputStream bos = null;
				bis = new BufferedInputStream(
						new FileInputStream(errorFilePath));
				bos = new BufferedOutputStream(out);
				byte[] buff = new byte[2048];
				int bytesRead;
				while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
					bos.write(buff, 0, bytesRead);
				}
				bis.close();
				bos.close();
				return null;
			}
		} catch (Exception e) {
			log.error("exportFailTaskExcel action error:" + e.getMessage());
			return ERROR;
		}
		return null;
	}

	// getter setter 方法

	public File getExcelFile() {
		return excelFile;
	}

	public void setExcelFile(File excelFile) {
		this.excelFile = excelFile;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getExcelFileName() {
		return excelFileName;
	}

	public void setExcelFileName(String excelFileName) {
		this.excelFileName = excelFileName;
	}

	public TskMicroTaskTarget getTaskTarget() {
		return taskTarget;
	}

	public void setTaskTarget(TskMicroTaskTarget taskTarget) {
		this.taskTarget = taskTarget;
	}

	public List<String> getHeadList() {
		return headList;
	}

	public void setHeadList(List<String> headList) {
		this.headList = headList;
	}

	// 业务的依赖注入
	public void setTskMicroTaskTargetService(
			TskMicroTaskTargetService tskMicroTaskTargetService) {
		this.tskMicroTaskTargetService = tskMicroTaskTargetService;
	}

}
