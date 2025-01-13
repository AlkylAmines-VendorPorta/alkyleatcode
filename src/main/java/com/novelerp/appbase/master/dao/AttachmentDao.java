package com.novelerp.appbase.master.dao;

import java.util.Map;

import com.novelerp.appbase.master.dto.AttachmentDto;
import com.novelerp.appbase.master.entity.Attachment;
import com.novelerp.core.dao.CommonDao;

public interface AttachmentDao extends CommonDao<Attachment,AttachmentDto>{
	public String getAttachmentQuery();
	public String getAttachmentDateWiseQuery();
	
	

}
