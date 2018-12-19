package com.banger.mobile.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.banger.mobile.domain.model.finance.FeArticleAttachment;
import com.banger.mobile.facade.BaseServiceTestCase;
import com.banger.mobile.facade.finance.FeArticleAttachmentService;

public class FeActicleAttachmentServiceTest extends BaseServiceTestCase{
	
	private FeArticleAttachmentService feActicleAttachmentService;
	
	public FeArticleAttachmentService getFeActicleAttachmentService() {
		return feActicleAttachmentService;
	}
	public void setFeActicleAttachmentService(
			FeArticleAttachmentService feActicleAttachmentService) {
		this.feActicleAttachmentService = feActicleAttachmentService;
	}
	
	public void atestsaveFeArticleAttachment(){
		FeArticleAttachment attachment = new FeArticleAttachment();
		attachment.setArticleId(12);
		attachment.setCreateDate(new Timestamp(new Date().getTime()));
		attachment.setCreateUser(2);
		attachment.setFileName("111");
		attachment.setFilePath("22");
		attachment.setFileNameOld("");
		attachment.setFileSize(2000L);
		attachment.setUploadDate(new Timestamp(new Date().getTime()));
		feActicleAttachmentService.saveFeArticleAttachment(attachment);
		setComplete();
		endTransaction();
	}
	
	public void atestgetFeArticleAttachmentByAttr(){
		List<FeArticleAttachment> list = feActicleAttachmentService.getFeArticleAttachmentByAttr(12);
		assertNotSame(list.size(),0);
	}
	
	public void testdeleteFeArticleAttachmentByAttr(){
		FeArticleAttachment attachment = new FeArticleAttachment();
		attachment.setAttachmentId(3);
		feActicleAttachmentService.deleteFeArticleAttachmentByAttr(attachment);
		setComplete();
		endTransaction();
	}
}
