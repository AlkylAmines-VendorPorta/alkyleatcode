package com.novelerp.appbase.master.service;

import java.util.List;

import com.novelerp.appbase.master.dto.PartnerItemManufacturerDto;
import com.novelerp.appbase.master.dto.PartnerTradingItemDto;
import com.novelerp.appbase.master.entity.PartnerItemManufacturer;
import com.novelerp.core.dto.ResponseDto;
import com.novelerp.core.service.CommonService;

/**
 * 
 * @author Aman Sahu
 */
public interface PartnerItemManufacturerService extends CommonService<PartnerItemManufacturer, PartnerItemManufacturerDto>{
           public List<PartnerItemManufacturerDto> getPartnerItemManufacturer(Long bpartnerId);
           public PartnerItemManufacturerDto saveItemManufacture(PartnerItemManufacturerDto dto);
           public ResponseDto updateItemsApproval(PartnerTradingItemDto dto);
}
