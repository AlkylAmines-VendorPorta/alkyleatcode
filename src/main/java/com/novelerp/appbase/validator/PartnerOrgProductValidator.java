package com.novelerp.appbase.validator;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.novelerp.appbase.master.dto.PartnerOrgProductDto;
import com.novelerp.appbase.master.service.PartnerOrgProductService;
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
public class PartnerOrgProductValidator implements Validator{

	@Autowired
	private ValidationUtil validator;
	@Autowired
	private PartnerOrgProductService partnerOrgProductService;
	@Override
	public void validate(Object object, Errors errors) {
		if(!object.getClass().isAssignableFrom(PartnerOrgProductDto.class)){
			validator.classNotSupported(errors, "invalid.classObject", "Invalid class Object");
			return;
		}
		PartnerOrgProductDto orgProduct = (PartnerOrgProductDto) object;
		checkForUniqueMaterial(orgProduct,errors);
	}
	private void checkForUniqueMaterial(PartnerOrgProductDto dto,Errors errors)
	{
		Map<String, Object> params=AbstractServiceImpl.getParamMap("partnerOrgId", dto.getPartnerOrg().getPartnerOrgId());
		params.put("materialId",dto.getMaterial().getMaterialId());
		PartnerOrgProductDto orgProduct=partnerOrgProductService.findDto("getProductQueryForUniqueMaterial", params);
        if(null!=orgProduct && !CommonUtil.isEqual(orgProduct.getPartnerOrgProductId(), dto.getPartnerOrgProductId())){
			
			validator.reject(errors, "already.Used", "Selected Material Has Already Added !");
		}
	}
}
