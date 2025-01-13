package com.novelerp.appcontext.service;

import java.util.Map;

import com.novelerp.appcontext.dto.BpartnerApprovalDto;
import com.novelerp.appcontext.entity.BpartnerApproval;
import com.novelerp.core.service.CommonService;

/**
 * 
 * @author Varsha Patil
 *
 */
public interface PartnerApprovalService extends CommonService<BpartnerApproval, BpartnerApprovalDto>{
         public boolean updatePartnerApprovalForNewApproval(Long bpartnerId,Map<String, Object> map);
}
