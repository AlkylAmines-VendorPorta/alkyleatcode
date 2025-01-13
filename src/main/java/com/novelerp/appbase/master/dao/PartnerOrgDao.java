package com.novelerp.appbase.master.dao;

import com.novelerp.appbase.master.dto.PartnerOrgDto;
import com.novelerp.appbase.master.entity.PartnerOrg;
import com.novelerp.appcontext.dto.RoleDto;
import com.novelerp.core.dao.CommonDao;
/**
 * @author Aman
 *
 */
public interface PartnerOrgDao extends CommonDao<PartnerOrg, PartnerOrgDto> {

	public String getPartnerOrgsQuery(RoleDto role);
}
