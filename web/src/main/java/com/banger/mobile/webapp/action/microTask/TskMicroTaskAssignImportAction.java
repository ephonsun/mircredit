/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :Administrator
 * Create Date:2013-5-17
 */
package com.banger.mobile.webapp.action.microTask;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.banger.mobile.domain.Enum.customer.EnumCustomer;
import com.banger.mobile.domain.model.microTask.CounterOutPintMessage;
import com.banger.mobile.domain.model.microTask.TskAssignImportBar;
import com.banger.mobile.domain.model.microTask.TskMicroTask;
import com.banger.mobile.domain.model.microTask.TskMicroTaskExecuteAssign;
import com.banger.mobile.facade.impl.task.microTask.TskMicroTaskAssignServiceImpl;
import com.banger.mobile.facade.microTask.TskMicroTaskAssignService;
import com.banger.mobile.importUtil.ReadExcel;
import com.banger.mobile.util.FileUtil;
import com.banger.mobile.util.StringUtil;
import com.banger.mobile.webapp.action.BaseAction;

/**
 * @author yanqf
 * @version $Id: TskMicroTaskAssignImportAction.java,v 0.1 2013-5-17 上午10:43:54
 *          yanqf Exp $
 */
public class TskMicroTaskAssignImportAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private int taskId; // 当前任务的ID

	private File excelFile; // 要导入的Excel文件
	private String imType; // 导入类型(执行者||机构)

	private String excelFileName; // 要导入的Excel的名称
	private String excelFilePath; // 要导入的Excel的路径

	private TskMicroTask tskMicTask; // 任务对象
	private TskMicroTaskExecuteAssign tskMicTaskExe; // 任务执行指定

	private List<String> initSysField; // 数据库中的字段
	private List<String> excelHeaders; // 要导入的Excel的表头
	private CounterOutPintMessage counterOutPintMessage; // 导入结果

	private TskMicroTaskAssignService tskMicroTaskAssignService;

	/**
	 * 跳转到导入任务分配界面
	 * 
	 * @return
	 */
	public String transTskImportPage() {
		// 获取当前任务的ID
		String taskId = String.valueOf(this.request.getParameter("taskId"));
		// 获取当前导入类型(导入执行者||机构)
		String imType = String.valueOf(this.request.getParameter("sysType"));
		if (!StringUtil.isBlank(taskId)) {
			this.request.setAttribute("taskId", taskId);
		}
		if (!StringUtil.isBlank(imType)) {
			this.request.setAttribute("imType", imType);
		}
		return SUCCESS;
	}

	/**
	 * 提供导入模板下载
	 * 
	 * @return
	 */
	public String provExcelSample() {
		try {
			HttpServletRequest req = ServletActionContext.getRequest();
			@SuppressWarnings("deprecation")
			String path = req.getRealPath("") + "/template/tsk_assign.xls";
			File file = new File(path);
			if (!file.exists()) {
				this.getResponse().setContentType("text/html;charset=GBK");
				this.getResponse().getWriter().print("指定的文件不存在！");
			} else {
			    String imType = String.valueOf(this.request.getParameter("sysType"));
			    String ipmName="";
			    if(!StringUtil.isBlank(imType)){
			        if(imType.equals("sysUser")){
			            ipmName="人员";
			        }else if(imType.equals("sysDept")){
			            ipmName="机构";
			        }
			    }
				ServletOutputStream out = this.getResponse().getOutputStream();
				this.getResponse().setHeader(
						"Content-Disposition",
						"attachment; filename="
								+ java.net.URLEncoder.encode("导入"+ipmName+"任务模版.xls",
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
			log.error("TskMicroTaskAssignImportAction provExcelSample error:"
					+ e.getMessage());
			return ERROR;
		}
		return null;
	}

	/**
	 * 比较Excel中字段和数据库中字段
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String importExcelContrast() {
		try {
			if (excelFile != null) {
				// 获取表头,导入总数...
				excelHeaders = ReadExcel.readColumnNames(excelFile.getPath());
			}
			// 表头为空时，提示是空Excel
			if (excelHeaders == null) {
				this.request.setAttribute("failureMsg",
						"您导入的文件格式不正确（可能文件经过格式修改），可下载模板使用模板导入！");
				return "failure";
			} else if (excelHeaders.size() == 0) {
				this.request.setAttribute("failureMsg", "您导入的文件是空文件，请重新导入！");
				return "failure";
			} else {
				// 获取文件绝对路径
				File serverPath = new File(this.request.getSession()
						.getServletContext().getRealPath("/upload/"));
				if (!serverPath.exists()) {
					serverPath.mkdir();
				}
				String fileName = this.request.getSession().getServletContext()
						.getRealPath("/upload/" + excelFile.getName());
				// 用于Action中传递的文件名称
				excelFileName = excelFile.getName();
				// 将文件拷贝到本地
				FileUtil.copyFile(excelFile, new File(fileName));
				// 传递文件路径
				this.request.setAttribute("filePath", fileName);
				Map<String, String> map = (Map<String, String>) request
						.getSession().getAttribute("recordMap");
				// 当前任务ID
				request.setAttribute("taskId", taskId);
				// 导入类型
				request.setAttribute("imType", imType);
				// 导入总数
				request.setAttribute("total", map.get("total"));
				// 批次
				request.setAttribute("bathchCount", map.get("bathchCount"));
				// 空行数据
				request.setAttribute("nullCount", map.get("nullCount"));
				// 数据库字段
				request.setAttribute("initSysField", initSysField());
			}
		} catch (Exception e) {
			log.error("TskMicroTaskAssignImportAction importExcelContrast error:"
					+ e.getMessage());
			return ERROR;
		}
		return SUCCESS;
	}

	/**
	 * 逻辑验证(通过进度条验证)
	 * 
	 * @return
	 */
	public String logicVerify() {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		try {
			PrintWriter out = response.getWriter();
			Map<Integer, TskAssignImportBar> assignExportBar = TskMicroTaskAssignServiceImpl
					.getTskAssignImportBar();
			TskAssignImportBar bar = null;
			if (assignExportBar.containsKey(getLoginInfo().getUserId())) {
				bar = assignExportBar.get(getLoginInfo().getUserId());
				bar.setCuurRow(0);
			} else {
				bar = new TskAssignImportBar();
				bar.setIsRun(0);
				bar.setIsStop(0);
				assignExportBar.put(getLoginInfo().getUserId(), bar);
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
		} catch (Exception e) {
			log.error("TskMicroTaskAssignImportAction logicVerify error:"
					+ e.getMessage());
			return null;
		}
		return null;
	}

	/**
	 * 将Excel中的数据导入数据库
	 * 
	 * @return
	 */
	public String excelToDataBase() {
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			Map<String, Object> paramMap = new HashMap<String, Object>();
			counterOutPintMessage = null;
			JSONObject jso = new JSONObject();
			Map<Integer, TskAssignImportBar> assignExportBar = TskMicroTaskAssignServiceImpl
					.getTskAssignImportBar();
			TskAssignImportBar bar = assignExportBar.get(this.getLoginInfo()
					.getUserId());
			if (bar.getIsStop().equals(0)) {
				// 远方传来的excelFileName
				String fileName = this.request.getSession().getServletContext()
						.getRealPath("/upload/" + excelFileName);
				// 获取当前任务ID
				paramMap.put("taskId", taskId);
				// 获取文件名
				paramMap.put("fileName", fileName);
				// 获取导入类型
				paramMap.put("imType", imType);
				// 导入数据库
				counterOutPintMessage = tskMicroTaskAssignService
						.excelToDataBase(paramMap);
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
			log.error("TskMicroTaskAssignImportAction excelToDataBase error:"
					+ e.getMessage());
			return null;
		}
		return null;
	}

	/**
	 * 进度条
	 * 
	 * @return
	 */
	public String tskAssignBar() {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		try {
			PrintWriter out = response.getWriter();
			JSONObject jso = new JSONObject();
			Map<Integer, TskAssignImportBar> assignExportBar = TskMicroTaskAssignServiceImpl
					.getTskAssignImportBar();
			TskAssignImportBar bar = assignExportBar.get(getLoginInfo()
					.getUserId());
			if (bar != null) {
				jso.put("curr", bar.getCuurRow());
			}
			out.print(jso.toString());
			return null;
		} catch (Exception e) {
			log.error("TskMicroTaskAssignImportAction tskAssignBar error:"
					+ e.getMessage());
			return null;
		}
	}

	/**
	 * 终止导入
	 * 
	 * @return
	 */
	public String interruptTskAssign() {
		try {
			Map<Integer, TskAssignImportBar> assignExportBar = TskMicroTaskAssignServiceImpl
					.getTskAssignImportBar();
			TskAssignImportBar bar = assignExportBar.get(this.getLoginInfo()
					.getUserId());
			if (bar.getIsRun().equals(1)) {
				bar.setCuurRow(0);
				bar.setIsRun(0);
				bar.setIsStop(1);// 正在导出时终止
			}
			return null;
		} catch (Exception e) {
			log.error("TskMicroTaskAssignImportAction interruptTskAssign error:"
					+ e.getMessage());
			return ERROR;
		}
	}

	/**
	 * 导出失败数据(Excel形式)
	 * 
	 * @return
	 */
	public void exportFileData() {
        BufferedInputStream br = null;
        BufferedOutputStream bos=null;
        try {
            // 服务器的绝对路径
            String path = request.getParameter("filePath");
            // 读取文件名：用于设置客户端保存时指定默认文件名
            int index = path.lastIndexOf("/"); // 前提：传入的path字符串以“\”表示目录分隔符
            // 错误信息Excel文件的名称
            String fileName = path.substring(index + 1);
            // 通过名称找到路径
            String errorFilePath = this.request.getRealPath("/") + "Records/"+ fileName;
            // 检查文件是否存在
            File file = new File(errorFilePath);
            if (!file.exists()) {
                HttpServletResponse res = this.getResponse();
                PrintWriter outt = res.getWriter();
                this.getResponse().setContentType("text/html;charset=GBK");
                outt.print("指定文件不存在！");
            } else {
                fileName="导入失败的客户资料"+System.currentTimeMillis()+".xls";
                byte[] buf = new byte[2*1024];
                int len = 0;
                getResponse().reset();//必须加，不然保存不了临时文件     
                getResponse().setContentType("application/octet-stream");
                getResponse().setHeader("Content-Disposition",
                    "attachment; filename=" + new String(fileName.getBytes("gbk"), "iso8859-1"));
                getResponse().setCharacterEncoding("UTF-8");
                br = new BufferedInputStream(new FileInputStream(file));
                bos = new java.io.BufferedOutputStream(getResponse().getOutputStream());   
                while ((len = br.read(buf, 0, buf.length)) != -1) {
                    bos.write(buf, 0, len);
                    len++;
                }
                bos.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("exportFileData action error:" + e.getMessage());
        }finally{
            if (br != null) {  
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                br=null;
            }
            if(bos!=null){
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                bos=null;
            }
        }
    }

	/**
	 * 初始化数据库字段
	 * 
	 * @return
	 */
	private Map<String, String> initSysField() {
		Map<String, String> sysMap = new HashMap<String, String>();
		sysMap.put("assignOb", "分配对象");
		sysMap.put("assignTa", "任务目标");
		return sysMap;
	}

	// Getter Setter Method
	public int getTaskId() {
		return taskId;
	}

	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}

	public File getExcelFile() {
		return excelFile;
	}

	public void setExcelFile(File excelFile) {
		this.excelFile = excelFile;
	}

	public String getImType() {
		return imType;
	}

	public void setImType(String imType) {
		this.imType = imType;
	}

	public String getExcelFileName() {
		return excelFileName;
	}

	public void setExcelFileName(String excelFileName) {
		this.excelFileName = excelFileName;
	}

	public String getExcelFilePath() {
		return excelFilePath;
	}

	public void setExcelFilePath(String excelFilePath) {
		this.excelFilePath = excelFilePath;
	}

	public TskMicroTask getTskMicTask() {
		return tskMicTask;
	}

	public void setTskMicTask(TskMicroTask tskMicTask) {
		this.tskMicTask = tskMicTask;
	}

	public TskMicroTaskExecuteAssign getTskMicTaskExe() {
		return tskMicTaskExe;
	}

	public void setTskMicTaskExe(TskMicroTaskExecuteAssign tskMicTaskExe) {
		this.tskMicTaskExe = tskMicTaskExe;
	}

	public List<String> getInitSysField() {
		return initSysField;
	}

	public void setInitSysField(List<String> initSysField) {
		this.initSysField = initSysField;
	}

	public List<String> getExcelHeaders() {
		return excelHeaders;
	}

	public void setExcelHeaders(List<String> excelHeaders) {
		this.excelHeaders = excelHeaders;
	}

	public CounterOutPintMessage getCounterOutPintMessage() {
		return counterOutPintMessage;
	}

	public void setCounterOutPintMessage(
			CounterOutPintMessage counterOutPintMessage) {
		this.counterOutPintMessage = counterOutPintMessage;
	}

	public TskMicroTaskAssignService getTskMicroTaskAssignService() {
		return tskMicroTaskAssignService;
	}

	public void setTskMicroTaskAssignService(
			TskMicroTaskAssignService tskMicroTaskAssignService) {
		this.tskMicroTaskAssignService = tskMicroTaskAssignService;
	}

}
