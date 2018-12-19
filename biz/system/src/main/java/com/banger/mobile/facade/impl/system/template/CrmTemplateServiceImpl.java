package com.banger.mobile.facade.impl.system.template;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.dao.fieldCodeData.CrmFieldCodeDataDao;
import com.banger.mobile.dao.template.CrmTemplateDao;
import com.banger.mobile.dao.templateField.CrmTemplateFieldDao;
import com.banger.mobile.domain.model.fieldCodeData.CrmFieldCodeData;
import com.banger.mobile.domain.model.template.CrmTemplate;
import com.banger.mobile.domain.model.templateField.CrmTemplateField;
import com.banger.mobile.facade.template.CrmTemplateService;

public class CrmTemplateServiceImpl implements CrmTemplateService {
 
	protected final Log   log = LogFactory.getLog(getClass());
	private CrmTemplateDao crmTemplateDao;
    private CrmTemplateFieldDao crmTemplateFieldDao;
    private CrmFieldCodeDataDao crmFieldCodeDataDao;

	public CrmFieldCodeDataDao getCrmFieldCodeDataDao() {
		return crmFieldCodeDataDao;
	}

	public void setCrmFieldCodeDataDao(CrmFieldCodeDataDao crmFieldCodeDataDao) {
		this.crmFieldCodeDataDao = crmFieldCodeDataDao;
	}

	public CrmTemplateFieldDao getCrmTemplateFieldDao() {
		return crmTemplateFieldDao;
	}

	public void setCrmTemplateFieldDao(CrmTemplateFieldDao crmTemplateFieldDao) {
		this.crmTemplateFieldDao = crmTemplateFieldDao;
	}

	public void setCrmTemplateDao(CrmTemplateDao crmTemplateDao) {
		this.crmTemplateDao = crmTemplateDao;
	}
	
	/**
	 * 新增业务模版
	 */
	public void addCrmTemplate(CrmTemplate crmTemplate) { 
		crmTemplateDao.addCrmTemplate(crmTemplate);
	}
 
	/**
	 * 删除业务模版
	 */
	public void deleteCrmTemplate(int id) { 
		crmTemplateDao.deleteCrmTemplate(id);
	}
 
	/**
	 * 修改业务模版
	 */
	public void updateCrmTemplate(CrmTemplate crmTemplate) { 
		crmTemplateDao.updateCrmTemplate(crmTemplate);
	}
 
	/**
	 * 根据id获得业务模版实体
	 */
	public CrmTemplate getCrmTemplateById(int id) { 
		return crmTemplateDao.getCrmTemplateById(id);
	}
 
	/**
	 * 分页查询模版
	 */
	public PageUtil<CrmTemplate> getCrmTemplatePage(
			Map<String, Object> parameters, Page page) { 
		return  crmTemplateDao.getCrmTemplatePage(parameters, page);
	}
 
	/**
	 * 根据name校验业务模版
	 */
	public int CheckCrmTemplateByName(Map<String, Object> map) {		 
		return crmTemplateDao.CheckCrmTemplateByName(map);
	}
 
	/**
	 * 移动业务模版
	 */
	public String moveCrmTemplate(int id,String moveType) { 
			CrmTemplate template=crmTemplateDao.getCrmTemplateById(id);//原对象
			int tempOrder=template.getSortno();
			Map< String , Object> map=new HashMap<String, Object>();
			map.put("moveType", moveType);
			map.put("sortno", template.getSortno()); 
			map.put("templateTypeId", template.getTemplateTypeId());
			 List<CrmTemplate> tempList= crmTemplateDao.getNeedMoveCrmTemplate(map);
			 if(tempList.size()>0){//判断是否可以移动
				 CrmTemplate movetemplate=tempList.get(0);//移动对象
				  //上移
			        if (moveType.equals("Up")) {
			            //如果上一个是系统默认的话直接返回ERROR
			        	if(movetemplate.getSortno()!=1){
			        		template.setSortno(movetemplate.getSortno());
			        		movetemplate.setSortno(tempOrder);
			        		crmTemplateDao.updateCrmTemplate(template);
			        		crmTemplateDao.updateCrmTemplate(movetemplate);
			        		return "SUCCESS";
			        	}else {
			        		  return "ERROR";
						}
			        }else {
			        	template.setSortno(movetemplate.getSortno());
		        		movetemplate.setSortno(tempOrder);
		        		crmTemplateDao.updateCrmTemplate(template);
		        		crmTemplateDao.updateCrmTemplate(movetemplate);
						return "SUCCESS";
					}
			 }
			 else {
				return "ERROR";
			}
	      
	        	/*
	            CrmCustomerType maxType = crmTemplateDao.getMaxSortNoCrmCustomerType();
	            if (maxType.getSortNo() != crmType.getSortNo()) {
	                Map<String, Object> parameterMap = new HashMap<String, Object>();
	                parameterMap.put("moveType", "Up");
	                parameterMap.put("sortNo", curSortNo);
	                CrmCustomerType needMove = crmTemplateDao
	                    .getNeedMoveCrmCustomerType(parameterMap);
	                //移动
	                crmType.setSortNo(needMove.getSortNo());
	                needMove.setSortNo(curSortNo);
	                //数据持久化
	                crmTemplateDao.updateCrmCustomerType(crmType);
	                crmTemplateDao.updateCrmCustomerType(needMove);
	                return "SUCCESS";
	            }else{
	                return "ERROR";
	            }
	        } else {
	            //下移
	            //如果是最底层则返回SUCCESS
	            CrmCustomerType minType = crmTemplateDao.getMinSortNoCrmCustomerType();
	            if (minType.getSortNo() != crmType.getSortNo()) {
	                Map<String, Object> parameterMap = new HashMap<String, Object>();
	                parameterMap.put("moveType", "Down");
	                parameterMap.put("sortNo", curSortNo);
	                CrmCustomerType needMove = crmTemplateDao
	                    .getNeedMoveCrmCustomerType(parameterMap);
	                //移动
	                crmType.setSortNo(needMove.getSortNo());
	                needMove.setSortNo(curSortNo);
	                //数据持久化
	                crmTemplateDao.updateCrmCustomerType(crmType);
	                crmTemplateDao.updateCrmCustomerType(needMove);
	                return "SUCCESS";
	            }else{
	                return "ERROR";
	            }
	        }
	        */
	        
	}

    public List<CrmTemplate> getAllCrmTemplate() {
        return crmTemplateDao.getAllCrmTemplate();
    }
    
    public List<CrmTemplate> getAllPdtCrmTemplate() {
        return crmTemplateDao.getAllPdtCrmTemplate();
    }
    
    public List<CrmTemplateField> getAllTemplateFields(){
    	return crmTemplateFieldDao.getAllCrmTemplateField();
    }
    
    /**
     * 得到来电弹屏自定义字段
     * @return
     */
    public List<CrmTemplateField> getCallInFields(){
    	List<CrmTemplateField> fields = new ArrayList<CrmTemplateField>();
    	List<CrmTemplateField> allFields = crmTemplateFieldDao.getAllCrmTemplateField();
    	for(CrmTemplateField field: allFields)
    	{
    		if(field.getIsPopUp()>0)fields.add(field);
    	}
    	return fields;
    }
    
    /**
     * 得到模板集合
     * @return
     */
    public List<CrmTemplate> getTemplates(){
    	List<CrmTemplate> templates = crmTemplateDao.getAllCrmTemplate();
    	for(CrmTemplate template : templates)
    	{
    		Map<String,List<CrmTemplateField>> fMap = this.getTemplateFields();
    		String tId = String.valueOf(template.getTemplateId());
    		if(fMap.containsKey(tId))
    			template.setFields(fMap.get(tId));
    	}
    	return templates;
    }
    
    /**
     * 得到模板的自定义字段
     * @return
     */
    private Map<String,List<CrmTemplateField>> getTemplateFields()
    {
    	List<CrmTemplateField> fields = crmTemplateFieldDao.getAllCrmTemplateField();
    	Map<String,List<CrmTemplateField>> fMap = new HashMap<String,List<CrmTemplateField>>();
    	Map<String,List<CrmFieldCodeData>> cMap =  this.getTemplateFieldCodes();
    	for(CrmTemplateField field : fields)
    	{
    		if(field.getTemplateFieldType().equalsIgnoreCase("Select") || field.getTemplateFieldType().equalsIgnoreCase("MultipleSelect"))
    		{
    			String fId = String.valueOf(field.getTemplateFieldId());
    			if(cMap.containsKey(fId))
    				field.setCodes(cMap.get(fId));
    		}
    		String  tId = String.valueOf(field.getTemplateId());
    		if(!fMap.containsKey(tId))fMap.put(tId,new ArrayList<CrmTemplateField>());
    		List<CrmTemplateField> fList = fMap.get(tId);
    		fList.add(field);
    	}
    	return fMap;
    }
    
    /**
     * 得到自定义下框拉字段的代码表
     * @return
     */
    private Map<String,List<CrmFieldCodeData>> getTemplateFieldCodes()
    {
    	List<CrmFieldCodeData> codes = crmFieldCodeDataDao.getAllCrmFieldCodeData();
    	Map<String,List<CrmFieldCodeData>> cMap = new HashMap<String,List<CrmFieldCodeData>>();
    	for(CrmFieldCodeData code : codes)
    	{
    		String fId= String.valueOf(code.getFieldId());
    		if(!cMap.containsKey(fId))cMap.put(fId,new ArrayList<CrmFieldCodeData>());
    		List<CrmFieldCodeData> fList = cMap.get(fId);
    		fList.add(code);
    	}
    	return cMap;
    }
}
	   
