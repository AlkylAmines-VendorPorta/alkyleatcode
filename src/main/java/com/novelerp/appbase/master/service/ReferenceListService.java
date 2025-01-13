package com.novelerp.appbase.master.service;

import java.util.Map;

import com.novelerp.appbase.master.dto.ReferenceListDto;
import com.novelerp.appbase.master.entity.ReferenceList;
import com.novelerp.core.service.CommonService;

public interface ReferenceListService extends CommonService<ReferenceList, ReferenceListDto> {
/*
   public List<ReferenceListDto> getReferenceList(String referenceCode);*/
   
   public Map<String,String> getReferenceListMap(String referenceCode);

/**
 * @param refCode
 * @return
 */
   public Map<String, ReferenceListDto> getRefListCollection(String refCode);
   
   public boolean deleteReferenceList(Long referenceListId);
}