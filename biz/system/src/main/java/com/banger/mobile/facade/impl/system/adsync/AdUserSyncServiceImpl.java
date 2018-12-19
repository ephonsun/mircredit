package com.banger.mobile.facade.impl.system.adsync;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;

import org.quartz.SchedulerException;

import com.banger.mobile.domain.model.adsync.SyncAdNode;
import com.banger.mobile.domain.model.adsync.SyncAdPcUsersSetting;
import com.banger.mobile.domain.model.dept.SysDept;
import com.banger.mobile.domain.model.user.SysUser;
import com.banger.mobile.facade.adsync.AdUserSyncService;
import com.banger.mobile.facade.dept.DeptService;
import com.banger.mobile.util.DebugUtil;
import com.banger.mobile.util.QuartzUtil;
import com.banger.mobile.facade.user.SysUserService;
import com.banger.mobile.util.StringUtil;

public class AdUserSyncServiceImpl implements AdUserSyncService {
	
	private DeptService dept;				//机构service
	private SysUserService user;
	private String enable;
	
	public void setEnable(String enable){
		this.enable = enable;
	}
	
	public boolean getTaskEnable(){
		return "true".equalsIgnoreCase(this.enable);
	}
	
	public void setDept(DeptService dept) {
		this.dept = dept;
	}

	public void setUser(SysUserService user) {
		this.user = user;
	}

	public List<SyncAdNode> getOUList(SyncAdPcUsersSetting setting) {
		try {
			List<SyncAdNode> nodes = this.getDeptNames(setting);
			Collections.sort(nodes,new DeptNameSort());
			return nodes;
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 同步所有AD域数据
	 * @return
	 */
	public String syncAdAllNode(SyncAdPcUsersSetting setting){
		try {
			SyncAdNode root = this.getDeptTree(setting);
		 
			if(StringUtil.isNullOrEmpty(setting.getAdSyncDeptNode()))return "AD域同步节点不能为空";
			SyncAdNode node = this.findDeptNode(root, setting.getAdSyncDeptNode());
			List<SyncAdNode> users = this.getUserNames(setting);
			Collections.sort(users,new UserNameSort());
			Map<String,List<SyncAdNode>> userMap = this.getSortNodeMap(users);
			if(node!=null){
				if(setting.getAdSyncDeptId()!=null && setting.getAdSyncDeptId().intValue()>0){
					DebugUtil.write("用户节点"+node.getName()+"查找父节点"+node.getFullName());
					if(userMap.containsKey(node.getFullName())){
						for(SyncAdNode un : userMap.get(node.getFullName())){
							DebugUtil.write("同步用户节点:"+node.getName());
							this.syncAdUserNode(un, setting.getAdSyncDeptId());
						}
					}
					if(node.getChildren()!=null){
						for(SyncAdNode n : node.getChildren()){
							DebugUtil.write("同步机构节点:"+node.getName());
							syncAdDeptNode(n,setting.getAdSyncDeptId(),userMap);
						}
					}
				}
				else return "找不到相标机构";
			}
			else return "找不到AD域同步节点";
			//String logfile = "e:/ldap_log_"+(new Date()).getTime()+".txt";
			//DebugUtil.save(logfile);
			return "SUCCESS";
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "同步出错:"+e.getMessage();
		}
	}
	
	/**
	 * 添加同步任务
	 */
	public void AddSyncJob(SyncAdPcUsersSetting setting){
		if(this.getTaskEnable() && setting!=null){
			if(setting.getAdActived()!=null && setting.getAdActived().intValue()>0){
				if("time".equalsIgnoreCase(setting.getAdSyncMode())){
					AdSyncJob job = new AdSyncJob();
					String jobName = "Time_AdSync";
			        String jobGroup = "JobGroup_AdSync";
			        String group = "Group_AdSync";
			        String name = "Name_AdSync";
        	        String cronExpress = SyncJob.GetJobCronExpress(setting);//获取定时触发表达式

          	      //QuartzUtil.addJob(jobName,mmsJob,cronExpress,paramsMap);
         	       try {
						QuartzUtil.addJob(name, group, jobName, jobGroup, job,"repeat",null,null,cronExpress,new HashMap<Object,Object>());
					} catch (SchedulerException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	/**
	 * 删除同步任务
	 */
	public void removeSyncJob(SyncAdPcUsersSetting setting){
		if(this.getTaskEnable() && setting!=null){
			String jobName = "Time_AdSync";
    	    String jobGroup = "JobGroup_AdSync";
    	    String group = "Group_AdSync";
    	    String name = "Name_AdSync";
    	    try {
				QuartzUtil.removeJob(name, group, jobName, jobGroup);
			} catch (SchedulerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private void syncAdUserNode(SyncAdNode node,Integer deptId){
		SysUser u = user.getAllUserByAccount(node.getAccount().trim());
		if(u!=null && u.getIsDel()<1){
			DebugUtil.write("用户"+node.getName()+"("+node.getAccount()+")"+"已存在");
			node.setTargetId(u.getUserId());
		}
		else{
			DebugUtil.write("新增用户"+node.getName()+"("+node.getAccount()+")");
			u = new SysUser();
			u.setAccount(node.getAccount().trim());
			u.setUserCode(node.getAccount().trim());
			u.setDeptId(deptId);
			u.setUserName(node.getName());
			user.syncAddSysUser(u, 1);
		}
	}
	
	/**
	 * 同步机构节点数据
	 * @param node
	 * @param parentId
	 */
	private void syncAdDeptNode(SyncAdNode node,Integer parentId,Map<String,List<SyncAdNode>> userMap){
		SysDept d = dept.getDeptByName(node.getName(), parentId);
		if(d!=null){
			node.setTargetId(d.getDeptId());
		}
		else{
			d = new SysDept();
			d.setDeptName(node.getName());
			dept.insertDept(d, parentId,1);
			node.setTargetId(d.getDeptId());
		}
		
		if(userMap.containsKey(node.getFullName())){
			for(SyncAdNode un : userMap.get(node.getFullName())){
				this.syncAdUserNode(un, d.getDeptId());
			}
		}
		
		if(node.getChildren()!=null){
			for(SyncAdNode n : node.getChildren()){
				syncAdDeptNode(n,node.getTargetId(),userMap);
			}
		}
	}
	
	private SyncAdNode findDeptNode(SyncAdNode node,String nodeName){
		if(node.getFullName().equals(nodeName))return node;
		if(node.getChildren()!=null){
			for(SyncAdNode n : node.getChildren()){
				SyncAdNode cn = findDeptNode(n,nodeName);
				if(cn!=null)return cn;
			}
		}
		return null;
	}
	
	private SyncAdNode getDeptTree(SyncAdPcUsersSetting setting) throws NamingException{
		List<SyncAdNode> nodes = this.getDeptNames(setting);
		SyncAdNode root = new SyncAdNode();
		root.setName(setting.getAdName());
		root.setFullName(setting.getAdName());
		Collections.sort(nodes,new DeptNameSort());
        setChildNodes(root,getSortNodeMap(nodes));
        return root;
	}
	
	private List<SyncAdNode> getUserNames(SyncAdPcUsersSetting setting){
		try {
			Hashtable<String,String> HashEnv = new Hashtable<String,String>();
		    String LDAP_URL = setting.getAdLdapUrl().trim();	//LDAP访问地址
		    String adminName = setting.getAdAdminName().trim();	//注意用户名的写法：domain\User 或 User@domain.com
		    String adminPassword = setting.getAdAdminPassword(); //密码
		    
		    if(!StringUtil.isNullOrEmpty(adminName) && (adminName.charAt(0)=='.' || adminName.charAt(adminName.length()-1)=='.'))adminName = "";
		    
		    HashEnv.put(Context.SECURITY_AUTHENTICATION, "simple"); //LDAP访问安全级别
		    HashEnv.put(Context.SECURITY_PRINCIPAL, adminName); //AD User
		    HashEnv.put(Context.SECURITY_CREDENTIALS, adminPassword); //AD Password
		    HashEnv.put(Context.INITIAL_CONTEXT_FACTORY,"com.sun.jndi.ldap.LdapCtxFactory"); //LDAP工厂类
		    HashEnv.put(Context.PROVIDER_URL, LDAP_URL);
			
			LdapContext ctx = new InitialLdapContext(HashEnv, null);
			
			SearchControls searchCtls = new SearchControls(); //Create the search controls
		    searchCtls.setSearchScope(SearchControls.SUBTREE_SCOPE); //Specify the search scope
		 
		    String searchFilter = "objectClass=User"; //specify the LDAP search filter
		    
		    String searchBase = setting.getAdSyncDeptNode()+","+this.getAdNameSearch(setting.getAdName());
		    //String searchBase = this.getAdNameSearch(setting.getAdName());
		    
		    String returnedAtts[] = {
		            "url", "whenChanged", "employeeID", "name", "userPrincipalName",
		            "physicalDeliveryOfficeName", "departmentNumber", "telephoneNumber",
		            "homePhone", "mobile", "department", "sAMAccountName", "whenChanged",
		            "mail"}; //定制返回属性
		  
		    searchCtls.setReturningAttributes(returnedAtts); //设置返回属性集
		  
		       //Search for objects using the filter
		    NamingEnumeration<SearchResult> answer = ctx.search(searchBase,searchFilter,searchCtls);

		    List<SyncAdNode> list = new ArrayList<SyncAdNode>();
		    
		    DebugUtil.write("--------------------------开始读取用户节点--------------------");
		    while (answer.hasMoreElements()) {
		       SearchResult sr = (SearchResult) answer.next();
		       SyncAdNode node = new SyncAdNode();
		       node.setFullName(sr.getName()+","+setting.getAdSyncDeptNode());
		       String pname = this.getParentNode(sr.getName()+","+setting.getAdSyncDeptNode());
		       node.setParentName(pname);
		       node.setName(sr.getAttributes().get("name").get().toString());
		       node.setAccount(sr.getAttributes().get("sAMAccountName").get().toString());
		       list.add(node);
		       DebugUtil.write("节点全名称:"+node.getFullName()+",父节点名称:"+node.getParentName()+",节点名称:"+node.getName()+",帐号:"+node.getAccount());
		    }
		    DebugUtil.write("------------------------结束读取用户节点------------------------");
		    return list;
		    
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	private List<SyncAdNode> getDeptNames(SyncAdPcUsersSetting setting) throws NamingException{
			Hashtable<String,String> HashEnv = new Hashtable<String,String>();
		    String LDAP_URL = setting.getAdLdapUrl().trim();	//LDAP访问地址
		    String adminName = setting.getAdAdminName().trim();	//注意用户名的写法：domain\User 或 User@domain.com
		    String adminPassword = setting.getAdAdminPassword(); //密码
		    
		    if(!StringUtil.isNullOrEmpty(adminName) && (adminName.charAt(0)=='.' || adminName.charAt(adminName.length()-1)=='.'))adminName = "";
		    
		    HashEnv.put(Context.SECURITY_AUTHENTICATION, "simple"); //LDAP访问安全级别
		    HashEnv.put(Context.SECURITY_PRINCIPAL, adminName); //AD User
		    HashEnv.put(Context.SECURITY_CREDENTIALS, adminPassword); //AD Password
		    HashEnv.put(Context.INITIAL_CONTEXT_FACTORY,"com.sun.jndi.ldap.LdapCtxFactory"); //LDAP工厂类
		    HashEnv.put(Context.PROVIDER_URL, LDAP_URL);
			
			LdapContext ctx;
			try {
				ctx = new InitialLdapContext(HashEnv, null);
			} catch (NamingException e) {
				if(e.getMessage().indexOf("LDAP")>-1){
					throw new NamingException("AD域帐号或密码错误");
				}
				else throw e;
			}
			
			SearchControls searchCtls = new SearchControls(); //Create the search controls
		    searchCtls.setSearchScope(SearchControls.SUBTREE_SCOPE); //Specify the search scope
		 
		    String searchFilter = "objectClass=organizationalUnit"; //specify the LDAP search filter
		    
		    String searchBase = this.getAdNameSearch(setting.getAdName());
		    
		    String returnedAtts[] = {
		            "url", "whenChanged", "employeeID", "name", "userPrincipalName",
		            "physicalDeliveryOfficeName", "departmentNumber", "telephoneNumber",
		            "homePhone", "mobile", "department", "sAMAccountName", "whenChanged",
		            "mail"}; //定制返回属性
		  
		    searchCtls.setReturningAttributes(returnedAtts); //设置返回属性集
		  
		       //Search for objects using the filter
		    NamingEnumeration<SearchResult> answer = ctx.search(searchBase,searchFilter,searchCtls);

		    List<SyncAdNode> list = new ArrayList<SyncAdNode>();
		    
		    while (answer.hasMoreElements()) {
		       SearchResult sr = (SearchResult) answer.next();
		       SyncAdNode node = new SyncAdNode();
		       node.setFullName(sr.getName());
		       String pname = this.getParentNode(sr.getName());
		       node.setParentName(pname.equals("")?setting.getAdName():pname);
		       node.setName(this.getAdNodeName(sr.getName()));
		       list.add(node);
		    }
		    return list;
	}
	
	private Map<String,List<SyncAdNode>> getSortNodeMap(List<SyncAdNode> nodes)
	{
		Map<String,List<SyncAdNode>> map = new HashMap<String,List<SyncAdNode>>();
		for(SyncAdNode node : nodes)
		{
			String key = node.getParentName();
			if(!map.containsKey(key))map.put(key,new ArrayList<SyncAdNode>());
			map.get(key).add(node);
		}
		return map;
	}

	private void setChildNodes(SyncAdNode node,Map<String,List<SyncAdNode>> map)
    {
        String key = node.getFullName();
        if(map.containsKey(key))
        {
        	List<SyncAdNode> nodes = map.get(key);
        	for(SyncAdNode n : nodes)
        	{
        		node.add(n);
        	}
        }

        if (node.getChildren() != null)
        {
        	for(SyncAdNode obj : node.getChildren())
            {
        		setChildNodes(obj, map);
            }
        }
    }
	
	private String getParentNode(String fullName){
		String[] names = fullName.split("\\,");
		String pname="";
		for(int i=1;i<names.length;i++){
			pname+=(pname.equals(""))?names[i]:","+names[i];
		}
		return pname;
	}
	
	private String getAdNodeName(String fullName){
		String[] names = fullName.split("\\,");
		String str1 = names[0];
		int n = str1.indexOf("=");
		String str2 = str1.substring(n+1).trim();
		return str2;
	}
	
	private String getAdNameSearch(String adName){
		if(!StringUtil.isNullOrEmpty(adName) && (adName.charAt(0)=='.' || adName.charAt(adName.length()-1)=='.'))return "";
		String[] names = adName.split("\\.");
		String dc = "";
		for(String name : names){
			dc+=(dc.equals(""))?"DC="+name:",DC="+name;
		}
		return dc;
	}
	
	/**
	 * 节点名称排序
	 * @author
	 *
	 */
	class DeptNameSort implements Comparator<SyncAdNode>{

		public int compare(SyncAdNode o1, SyncAdNode o2) {
			String[] n1s = o1.getFullName().split("\\,");
			String[] n2s = o2.getFullName().split("\\,");
			if(n1s.length<n2s.length){
				return 1;
			}
			else if(n1s.length==n2s.length){
				int p = o1.getParentName().compareTo(o2.getParentName());
				return (p!=0)?p:o1.getName().compareTo(o2.getName());
			}
			else{
				return -1;
			}
		}
	}
	
	class UserNameSort extends DeptNameSort{
	}
	
	static class SyncJob {
		
		public static String GetJobCronExpress(SyncAdPcUsersSetting setting) {
	        String cronExpressions = "";
	        String sendRate = setting.getAdSyncRate();//获取发送频率类型 day：每天    week：每周    month：每月   year：每年
	        String condition = setting.getAdSyncRateSetting();//获取触发表达式
	        SimpleDateFormat sdfTime = new SimpleDateFormat("HH:mm:ss"); //得到时间
	        String[] time = sdfTime.format(setting.getAdSyncTime()).toString().split(":");
	        String timeH = time[0];
	        String timeM = time[1];

            //day：每天    week：每周    month：每月   year：每年
            if (sendRate.equals("day")) {
                if (condition.equals("1")) { //每天发送
                    // 0 0 12 * * ? 每天12点触发
                    cronExpressions = StringUtil.format("0 {0} {1} ? * *", timeM, timeH);
                } else {
                    Calendar startCal = Calendar.getInstance();
                    startCal.setTime(setting.getAdSyncTime());
                    //隔x天发送(每x天执行一次)
                    cronExpressions = StringUtil.format(
                        "0 {0} {1} {2} * ? *",
                        timeM,
                        timeH,
                        startCal.get(Calendar.DAY_OF_MONTH) + "/"
                                + setting.getAdSyncRateSetting()); //0 05 12 2/3 * ? *
                }
            } else if (sendRate.equals("week")) {
                String[] week = condition.split("\\|");
                String weekCondition = "";
                for (String w : week) {
                    weekCondition += GetWeekByNumber(w) + ",";
                }
                weekCondition = weekCondition.substring(0, weekCondition.length() - 1);
                //0 10,44 14 ? 3 WED 3月分每周三下午的 2点10分和2点44分触发
                cronExpressions = StringUtil.format("0 {0} {1} ? * {2}", timeM, timeH,
                    weekCondition);
            } else if (sendRate.equals("month")) {
                String[] days = condition.split("\\|");
                String dayCondition = "";
                for (String day : days) {
                    dayCondition += day + ",";
                }
                dayCondition = dayCondition.substring(0, dayCondition.length() - 1);
                //0 15 10 15 * ? 每月15号上午10点15分触发
                cronExpressions = StringUtil
                    .format("0 {0} {1} {2} * ?", timeM, timeH, dayCondition);
            } else if (sendRate.equals("year")) {
                String[] date = condition.split("-");
                String dateDay = date[0];
                String dateMonth = date[1];
                //0 11 11 11 11 ? 每年的11月11号 11点11分触发(光棍节)
                cronExpressions = StringUtil.format("0 {0} {1} {2} {3} ? *", timeM, timeH,
                    dateMonth, dateDay);
            }
	        return cronExpressions;
	    }
		
		public static String GetWeekByNumber(String number) {
	        if (number.equals("1")) {
	            return "MON";
	        } else if (number.equals("2")) {
	            return "TUE";
	        } else if (number.equals("3")) {
	            return "WED";
	        } else if (number.equals("4")) {
	            return "THU";
	        } else if (number.equals("5")) {
	            return "FRI";
	        } else if (number.equals("6")) {
	            return "SAT";
	        } else if (number.equals("7")) {
	            return "SUN";
	        } else {
	            return "";
	        }
	    }
	}
}
