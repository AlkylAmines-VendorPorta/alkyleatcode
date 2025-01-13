package com.novelerp.appbase.master.dao;

import java.util.List;

import com.novelerp.appbase.master.dto.PublicNoticeDto;
import com.novelerp.appbase.master.entity.PublicNotice;
import com.novelerp.core.dao.CommonDao;

/**
 *  @author Aman
 */
public interface PublicNoticeDao extends CommonDao<PublicNotice, PublicNoticeDto> {

	public List<PublicNotice> getPublicNoticeList();
	public List<PublicNotice> getPublicNoticeListById(Long id);
}
