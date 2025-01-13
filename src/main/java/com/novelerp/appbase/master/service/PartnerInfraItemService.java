package com.novelerp.appbase.master.service;

import java.util.List;

import com.novelerp.appbase.master.dto.PartnerInfraItemDto;
import com.novelerp.appbase.master.entity.PartnerInfraItem;
import com.novelerp.appcontext.dto.RoleDto;
import com.novelerp.core.service.CommonService;

/**
 * 
 * @author Varsha Patil
 *
 */
public interface PartnerInfraItemService extends CommonService<PartnerInfraItem, PartnerInfraItemDto>{
     public PartnerInfraItemDto saveInfraItems(PartnerInfraItemDto dto,PartnerInfraItemDto infraItem);
     public boolean uploadClarification(Long infraItemId,Long partnerId,Long attachmentId);
     public boolean updateInfraItemStatus(PartnerInfraItemDto partnerInfraItem);
     public List<PartnerInfraItemDto> getInfraItems(Long partnerId);
     public boolean updateInfraItemLevel(PartnerInfraItemDto dto,RoleDto role,String isApproved);
     public boolean updateInfraItem(PartnerInfraItemDto dto); 
     
}
