package com.banger.mobile.facade.impl.finance;

import java.util.List;

import com.banger.mobile.dao.finance.FeArticeleAttachmentDao;
import com.banger.mobile.domain.model.finance.FeArticleAttachment;
import com.banger.mobile.facade.finance.FeArticleAttachmentService;

public class FeArticleAttachmentServiceImpl implements
		FeArticleAttachmentService {
	private FeArticeleAttachmentDao feArticeleAttachmentDao;
	
	public void saveFeArticleAttachment(FeArticleAttachment attachment) {
		
		feArticeleAttachmentDao.saveFeArticleAttachment(attachment);
	}

	public List<FeArticleAttachment> getFeArticleAttachmentByAttr(
			Integer acticleId) {
		return feArticeleAttachmentDao.getFeArticleAttachmentByAttr(acticleId);
	}

	public Integer deleteFeArticleAttachmentByAttr(
			FeArticleAttachment attachment) {
		return feArticeleAttachmentDao.deleteFeArticleAttachmentByAttr(attachment);
	}

	public FeArticleAttachment getFeArticleAttachmentById(Integer attachmentId) {
		return feArticeleAttachmentDao.getFeArticleAttachmentById(attachmentId);
	}

	

	public FeArticeleAttachmentDao getFeArticeleAttachmentDao() {
		return feArticeleAttachmentDao;
	}

	public void setFeArticeleAttachmentDao(
			FeArticeleAttachmentDao feArticeleAttachmentDao) {
		this.feArticeleAttachmentDao = feArticeleAttachmentDao;
	}

	public Integer deleteFeArticleAttachmentById(Integer acticleId) {
		// TODO Auto-generated method stub
		return feArticeleAttachmentDao.deleteFeArticleAttachmentById(acticleId);
	}

}
