package com.novelerp.appbase.master.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.novelerp.appbase.master.dto.PartnerOrgFinancialDto;
import com.novelerp.appbase.master.entity.PartnerOrgFinancial;
import com.novelerp.appbase.master.entity.PartnerSignatory;
import com.novelerp.appcontext.converter.PartnerConverter;
import com.novelerp.appcontext.dto.BPartnerDto;
import com.novelerp.appcontext.entity.Bpartner;
import com.novelerp.core.converter.CustomDozerConverter;
import com.novelerp.core.converter.ObjectConverter;
/**
 * @author Aman
 *
 */
@Component
public class PartnerOrgFinancialConverter extends CustomDozerConverter<PartnerOrgFinancial, PartnerOrgFinancialDto> implements ObjectConverter<PartnerOrgFinancial, PartnerOrgFinancialDto>{

	@Autowired
	private PartnerConverter partnerConverter;
	
	private BPartnerDto getPartner(PartnerSignatory entity){
		Bpartner partner =entity.getPartner();
		if(partner != null){
			partner.setLoadDefault(false);
		}
		return partnerConverter.convertEntityToDto(partner, BPartnerDto.class);
	}
	@Override
	public PartnerOrgFinancialDto convertEntityToDto(PartnerOrgFinancial entity,
			Class<PartnerOrgFinancialDto> dtoClass) {
		// TODO Auto-generated method stub
		return null;
	}

}
