package com.banger.mobile.domain.model.tskContact;

import com.banger.mobile.domain.model.base.BaseObject;

/**
 * @author wumh E-mail:wumh@baihang-china.com
 * @version 创建时间：Dec 18, 2012 1:19:27 PM
 * 类说明
 */
public class ExecuterAttrPlugin extends BaseObject implements Cloneable{

	public ExecuterAttrPlugin() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ExecuterAttrPlugin(Integer id, Integer parentId, String textName,
			String type, Integer counts, Integer finishCount,
			Integer isActived, Integer isDel) {
		super();
		this.id = id;
		this.parentId = parentId;
		this.textName = textName;
		this.type = type;
		this.counts = counts;
		this.finishCount = finishCount;
		this.isActived = isActived;
		this.isDel = isDel;
	}
	public Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = -2226171315978000408L;
	private Integer           id;                                       //ID
    private Integer           parentId;                                 //父ID
    private String            textName;
    private String            type;                                     //类型 'U:用户 D:机构'
    private Integer           counts;                                   //总数
    private Integer           finishCount;                              //完成量
    private Integer           isActived;                                //是否启用
    private Integer           isDel;                                    //是否删除
    
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public String getTextName() {
		return textName;
	}
	public void setTextName(String textName) {
		this.textName = textName;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Integer getCounts() {
		return counts;
	}
	public void setCounts(Integer counts) {
		this.counts = counts;
	}
	public Integer getFinishCount() {
		return finishCount;
	}
	public void setFinishCount(Integer finishCount) {
		this.finishCount = finishCount;
	}
	public Integer getIsActived() {
		return isActived;
	}
	public void setIsActived(Integer isActived) {
		this.isActived = isActived;
	}
	public Integer getIsDel() {
		return isDel;
	}
	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}
}



