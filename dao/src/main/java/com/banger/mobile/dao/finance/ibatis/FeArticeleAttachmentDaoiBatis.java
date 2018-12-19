package com.banger.mobile.dao.finance.ibatis;

import java.util.List;

import com.banger.mobile.dao.finance.FeArticeleAttachmentDao;
import com.banger.mobile.domain.model.finance.FeArticleAttachment;
import com.banger.mobile.ibatis.GenericDaoiBatis;

public class FeArticeleAttachmentDaoiBatis  extends GenericDaoiBatis  implements FeArticeleAttachmentDao {

	public FeArticeleAttachmentDaoiBatis(Class persistentClass) {
		super(FeArticeleAttachmentDao.class);
	}
	
	public FeArticeleAttachmentDaoiBatis() {
		super(FeArticeleAttachmentDao.class);
	}
	public void saveFeArticleAttachment(FeArticleAttachment attachment) {
		this.getSqlMapClientTemplate().insert("insertFeArticleAttachment",attachment);
	}

	public List<FeArticleAttachment> getFeArticleAttachmentByAttr(
			Integer acticleId) {
		return this.getSqlMapClientTemplate().queryForList("getFeArticleAttachments",acticleId);
	}

	public Integer deleteFeArticleAttachmentByAttr(
			FeArticleAttachment attachment) {
		return this.getSqlMapClientTemplate().delete("deleteFeArticleAttachment",attachment);
	}

	public FeArticleAttachment getFeArticleAttachmentById(Integer attachmentId) {
		return (FeArticleAttachment) this.getSqlMapClientTemplate().queryForObject("getFeArticleAttachmentById",attachmentId);
	}

	public Integer deleteFeArticleAttachmentById(Integer acticleId) {
		return this.getSqlMapClientTemplate().delete("deleteFeArticleAttachmentByActicleId",acticleId);
	}

}
