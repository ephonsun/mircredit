package com.banger.mobile.facade.impl.finance;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONArray;

import com.banger.mobile.dao.finance.FeKnowledgebaseTypeDao;
import com.banger.mobile.domain.model.finance.FeKnowledgebaseType;
import com.banger.mobile.facade.finance.FeKnowledgeTypeService;

import java.util.List;

/**
 * @author wumh E-mail:wumh@baihang-china.com
 * @version 创建时间：Dec 4, 2012 11:01:29 AM
 * 类说明
 */
public class FeKnowledgeTypeServiceImpl implements FeKnowledgeTypeService {
	
	private FeKnowledgebaseTypeDao feKnowledgebaseTypeDao;
	
	public FeKnowledgebaseTypeDao getFeKnowledgebaseTypeDao() {
		return feKnowledgebaseTypeDao;
	}

	public void setFeKnowledgebaseTypeDao(
			FeKnowledgebaseTypeDao feKnowledgebaseTypeDao) {
		this.feKnowledgebaseTypeDao = feKnowledgebaseTypeDao;
	}

	/**
	 * 新建知识库分类
	 * @param feKnowledgebaseType
	 * @return 主键
	 */
	public Integer addKnowledgeType(FeKnowledgebaseType feKnowledgebaseType) {
		// TODO Auto-generated method stub
		try{
			return feKnowledgebaseTypeDao.addKnowledgeType(feKnowledgebaseType);
		}catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}

	/**
	 * 删除知识库分类
	 * @param feKnowledgebaseType
	 */
	public boolean deleteKnowledgeType(FeKnowledgebaseType feKnowledgebaseType) {
		// TODO Auto-generated method stub
		try{
			feKnowledgebaseType.setUpdateDate(new Date());
			feKnowledgebaseTypeDao.deleteKnowledgeType(feKnowledgebaseType);
			return true;
		}catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}
	
	/**
	 * 搜索个人的知识库分类树
	 * @param userId
	 * @return
	 */
	public JSONArray getSelfKnowledgeTypeTree(Integer userId) {
		// TODO Auto-generated method stub
		try{
			Map<String, Object> map = new HashMap<String, Object>();
			JSONArray jsonArray = new JSONArray();
			int rootId = 0;
			map.put("userId", userId);			
			List<FeKnowledgebaseType> list = feKnowledgebaseTypeDao.getSelfKnowledgeTypeList(map);
			map.clear();
			if(list.size()>0){
				for(FeKnowledgebaseType knowledgeType : list){
					map.put("id", knowledgeType.getKnowledgebaseTypeId());
					int pid = knowledgeType.getParentId();
					if(pid == 0){ 
						map.put("open", true);
						rootId = knowledgeType.getKnowledgebaseTypeId();
	                }
					if(pid == rootId){
						map.put("open", true);
					}
					map.put("pId",pid);
					map.put("name", knowledgeType.getKnowledgebaseTypeName());
					jsonArray.add(map);
					map.remove("open");
				}
			}
			return jsonArray;
		}catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}

	/**
	 * 下移知识库分类
	 * @param feKnowledgebaseType
	 */
	public boolean moveDownKnowledgeType(FeKnowledgebaseType feKnowledgebaseType) {
		// TODO Auto-generated method stub
		return this.moveFeKnowledgebaseType(feKnowledgebaseType.getKnowledgebaseTypeId(), "down");
	}

	/**
	 * 上移知识库分类
	 * @param feKnowledgebaseType
	 */
	public boolean moveUpKnowledgeType(FeKnowledgebaseType feKnowledgebaseType) {
		// TODO Auto-generated method stub
		return this.moveFeKnowledgebaseType(feKnowledgebaseType.getKnowledgebaseTypeId(),"up");
	}

	/**
	 * 编辑知识库分类
	 * @param feKnowledgebaseType
	 */
	public boolean updateKnowledgeType(FeKnowledgebaseType feKnowledgebaseType) {
		// TODO Auto-generated method stub
		try{
			feKnowledgebaseType.setUpdateDate(new Date());
			feKnowledgebaseTypeDao.updateKnowledgeType(feKnowledgebaseType);
			return true;
		}catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}	
	
	/**
	 * 通过主键ID获取FeKnowledgebaseType实体
	 * @param id
	 * @return
	 */
	public FeKnowledgebaseType getFeKnowledgebaseTypeById(int id){
		return feKnowledgebaseTypeDao.getFeKnowledgebaseTypeById(id);
	}
	
	/** 
	 * 上移下移
	 * @param id
	 * @param type
	 * @return
	 */
	private boolean moveFeKnowledgebaseType(int id, String type) {
		try{
			FeKnowledgebaseType knowledgeType = this.getFeKnowledgebaseTypeById(id);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("type", type);
			map.put("orderId", knowledgeType.getOrderId());
			map.put("userId", knowledgeType.getUserId());
			map.put("parentId", knowledgeType.getParentId());
			FeKnowledgebaseType desKnowledgebaseType = feKnowledgebaseTypeDao.getDesKnowledgeType(map);
			int orderId = desKnowledgebaseType.getOrderId();
			desKnowledgebaseType.setOrderId(knowledgeType.getOrderId());
			knowledgeType.setOrderId(orderId);
			feKnowledgebaseTypeDao.updateKnowledgeType(knowledgeType);
			feKnowledgebaseTypeDao.updateKnowledgeType(desKnowledgebaseType);
			return true;
		}catch (Exception e) {
			// TODO: handle exception
			return false;
		}		
	}
	
	/**
	 * 新建用户时插入知识库分类的根节点
	 * @return
	 */
	public boolean insertRootKnowBaseType(Integer userId){
		try {
			FeKnowledgebaseType knowledgebaseType = new FeKnowledgebaseType();
			knowledgebaseType.setKnowledgebaseTypeName("个人知识库");
			knowledgebaseType.setParentId(0);
			knowledgebaseType.setCreateUser(userId);
			knowledgebaseType.setUserId(userId);
			this.addKnowledgeType(knowledgebaseType);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}
	
	/**
	 * 验证父ID是否为自身或者自己的子节点
	 * @param feKnowledgebaseType
	 * @return bool
	 */
	public boolean parentIsSelfChildren(FeKnowledgebaseType feKnowledgebaseType){
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			if(feKnowledgebaseType.getKnowledgebaseTypeId().equals(feKnowledgebaseType.getParentId())){
				return true;
			}
			map.put("userId", feKnowledgebaseType.getUserId());	
			List<FeKnowledgebaseType> list = feKnowledgebaseTypeDao.getSelfKnowledgeTypeList(map);
			String childIds = getAllChildrenIds(list, feKnowledgebaseType.getKnowledgebaseTypeId());
			String[] arr = childIds.split(",");
			String pid = String.valueOf(feKnowledgebaseType.getParentId());
			for(String s : arr){
				if(s.equals(pid)){
					return true;
				}
			}
			return false;		
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}
	
	private String getAllChildrenIds(List<FeKnowledgebaseType> list,Integer id){
		String ids = "";
		for(FeKnowledgebaseType type : list){
			if(type.getParentId().equals(id)){
				Integer childId = type.getKnowledgebaseTypeId();
				ids = ids+','+childId+','+getAllChildrenIds(list,childId);
			}
		}
		return ids;
	}
	
	/**
	 * 判断同级下面是否存在同名的分类
	 * @param feKnowledgebaseType
	 * @return
	 */
	public boolean isExistSameNameInSameLevel(FeKnowledgebaseType feKnowledgebaseType){
		feKnowledgebaseType = feKnowledgebaseTypeDao.getFeKnowledgebaseType(feKnowledgebaseType);
		if(feKnowledgebaseType!=null){
			return true;
		}else {
			return false;
		}
	}
}



