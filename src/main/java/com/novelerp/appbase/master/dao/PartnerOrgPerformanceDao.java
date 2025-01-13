package com.novelerp.appbase.master.dao;

import com.novelerp.appbase.master.dto.PartnerOrgPerformanceDto;
import com.novelerp.appbase.master.entity.PartnerOrgPerformance;
import com.novelerp.core.dao.CommonDao;
/**
 * @author Aman
 *
 */
public interface PartnerOrgPerformanceDao extends CommonDao<PartnerOrgPerformance, PartnerOrgPerformanceDto>{
   public String checkForDuplicatePerformance(PartnerOrgPerformanceDto dto);
}
