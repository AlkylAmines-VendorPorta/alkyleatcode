package com.novelerp.eat.dao;

import com.novelerp.core.dao.CommonDao;
import com.novelerp.eat.dto.ScrutinyFileDto;
import com.novelerp.eat.entity.ScrutinyFile;

public interface ScrutinyFileDao  extends CommonDao<ScrutinyFile, ScrutinyFileDto>{

	public int unhookPreviousScrutinyFile(Long itemScrtunityId,String scrutinyLevel,String scrutinyType);
}
