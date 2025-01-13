package com.novelerp.appbase.master.dao;

import com.novelerp.appbase.master.dto.PartnerItemManufacturerDto;
import com.novelerp.appbase.master.entity.PartnerItemManufacturer;
import com.novelerp.appcontext.dto.RoleDto;
import com.novelerp.core.dao.CommonDao;

/**
 * @author Aman Sahu
 *
 */
public interface PartnerItemManufacturerDao extends CommonDao<PartnerItemManufacturer, PartnerItemManufacturerDto>{
   public String getQueryForPartnerItemManufacturerQuery(RoleDto role);
}
