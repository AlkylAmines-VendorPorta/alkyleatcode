package com.novelerp.appbase.master.dao;

import com.novelerp.appbase.master.dto.PartnerInfraItemDto;
import com.novelerp.appbase.master.entity.PartnerInfraItem;
import com.novelerp.appcontext.dto.RoleDto;
import com.novelerp.core.dao.CommonDao;

/**
 * 
 * @author Varsha Patil
 *
 */
public interface PartnerInfraItemDao extends CommonDao<PartnerInfraItem, PartnerInfraItemDto>{
	
	public int uploadClarification(Long infraItemId,Long partnerId,Long attachmentId);
	public int updateInfraItemLevel(PartnerInfraItemDto dto,RoleDto role);
	public int updateInfraItemStaus(PartnerInfraItemDto dto,RoleDto role);

}
