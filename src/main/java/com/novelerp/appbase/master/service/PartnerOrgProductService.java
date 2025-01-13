package com.novelerp.appbase.master.service;

import java.util.List;

import com.novelerp.appbase.master.dto.PartnerOrgProductDto;
import com.novelerp.appbase.master.entity.PartnerOrgProduct;
import com.novelerp.appcontext.dto.RoleDto;
import com.novelerp.core.service.CommonService;

/**
 * 
 * @author Vivek Birdi
 *
 */
public interface PartnerOrgProductService extends CommonService<PartnerOrgProduct, PartnerOrgProductDto>{

	/**
	 * @param tahdrId
	 * @param partnerId
	 * @return
	 */
	/*public List<PartnerOrgProductDto> getExmptedItemListByTahdr(Long tahdrId, Long partnerId);*/
	public PartnerOrgProductDto saveOrgProduct(PartnerOrgProductDto dto,RoleDto role);
	/**
	 * @param tahdrId
	 * @param partnerId
	 * @param partnerOrgId
	 * @return
	 */
	List<PartnerOrgProductDto> getExmptedItemListByTahdr(Long tahdrId, Long partnerId, Long partnerOrgId);

}
