package com.novelerp.appbase.validator;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.novelerp.appbase.master.dto.PartnerTradingItemDto;
import com.novelerp.appbase.master.service.PartnerTradingItemService;
import com.novelerp.commons.util.CommonUtil;
import com.novelerp.core.dto.Errors;
import com.novelerp.core.service.impl.AbstractServiceImpl;
import com.novelerp.core.util.ValidationUtil;
import com.novelerp.core.validator.Validator;
/**
 * 
 * @author Varsha
 *
 */
@Component
public class PartnerTradingItemValidator implements Validator {

	@Autowired
	private ValidationUtil validator;
	@Autowired
	private PartnerTradingItemService partnerTradingItemService;
	@Override
	public void validate(Object object, Errors errors) {
		if(!object.getClass().isAssignableFrom(PartnerTradingItemDto.class)){
			validator.classNotSupported(errors, "invalid.classObject", "Invalid class Object");
			return;
		}
		PartnerTradingItemDto dto = (PartnerTradingItemDto) object;
		checkForUniqueMaterial(dto,errors);
	}
	private void checkForUniqueMaterial(PartnerTradingItemDto dto,Errors errors)
	{
		Map<String, Object> params=AbstractServiceImpl.getParamMap("partnerItemManufacturerId", dto.getPartnerItemManufacutrer().getPartnerItemManufacturerId());
		params.put("materialId",dto.getMaterial().getMaterialId());
		PartnerTradingItemDto itemDto=partnerTradingItemService.findDto("getTradingItemQueryForUniqueMaterial", params);
        if(null!=itemDto && !CommonUtil.isEqual(itemDto.getPartnerTradingItemId(), dto.getPartnerTradingItemId())){
			
			validator.reject(errors, "already.Used", "Selected Material Already Added !");
		}
	}

}
