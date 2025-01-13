/**
 * @author Ankush
 */

package com.novelerp.eat.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.novelerp.appcontext.converter.PartnerConverter;
import com.novelerp.appcontext.dto.BPartnerDto;
import com.novelerp.appcontext.entity.Bpartner;
import com.novelerp.core.converter.CustomDozerConverter;
import com.novelerp.core.converter.ObjectConverter;
import com.novelerp.eat.dto.TAHDRDetailDto;
import com.novelerp.eat.entity.TAHDRDetail;

@Component
public class DetailConverter extends CustomDozerConverter<TAHDRDetail, TAHDRDetailDto> implements ObjectConverter<TAHDRDetail, TAHDRDetailDto>{

	@Autowired
	private PartnerConverter partnerConverter;
	
	@Override
	public TAHDRDetailDto convertEntityToDto(TAHDRDetail entity, Class<TAHDRDetailDto> dtoClass) {
		if(entity == null){
			return null;
		}
		TAHDRDetailDto tahdrDetailDto = new TAHDRDetailDto();
		tahdrDetailDto.setCommencementPeriod(entity.getCommencementPeriod());
		tahdrDetailDto.setCommercialBidFromDate(entity.getCommercialBidFromDate());
		tahdrDetailDto.setCommercialBidToDate(entity.getCommercialBidToDate());
		tahdrDetailDto.setCommercialBidOpenningDate(entity.getCommercialBidOpenningDate());
		tahdrDetailDto.setContactEmailId(entity.getContactEmailId());
		tahdrDetailDto.setDeliveryDuration(entity.getDeliveryDuration());
		tahdrDetailDto.setEstimatedCost(entity.getEstimatedCost());
		tahdrDetailDto.setIsActive(entity.getIsActive());
		tahdrDetailDto.setIsAnnexureC1(entity.getIsAnnexureC1());
		tahdrDetailDto.setIsBiennialContractRate(entity.getIsBiennialContractRate());
		tahdrDetailDto.setIsDeviation(entity.getIsDeviation());
		tahdrDetailDto.setIsSetC1Later(entity.getIsSetC1Later());
		tahdrDetailDto.setPurchaseFromDate(entity.getPurchaseFromDate());
		tahdrDetailDto.setPurchaseToDate(entity.getPurchaseToDate());
		tahdrDetailDto.setMinQuantity(entity.getMinQuantity());
		tahdrDetailDto.setIsPBDSetLater(entity.getIsPBDSetLater());
		tahdrDetailDto.setPriceBidFromDate(entity.getPriceBidFromDate());
		tahdrDetailDto.setPriceBidToDate(entity.getPriceBidToDate());
		tahdrDetailDto.setPricingProcCode(entity.getPricingProcCode());
		tahdrDetailDto.setTahdrDetailId(entity.getTahdrDetailId());
		tahdrDetailDto.setTahdrFees(entity.getTahdrFees());
		tahdrDetailDto.setTahdrStatusCode(entity.getTahdrStatusCode());
		tahdrDetailDto.setTahdrValidity(entity.getTahdrValidity());
		tahdrDetailDto.setTechBidOpenningDate(entity.getTechBidOpenningDate());
		tahdrDetailDto.setTechnicalBidFromDate(entity.getTechnicalBidFromDate());
		tahdrDetailDto.setTechnicalBidToDate(entity.getTechnicalBidToDate());
		tahdrDetailDto.setVersion(entity.getVersion());
		tahdrDetailDto.setDescription(entity.getDescription());
		tahdrDetailDto.setC1FromDate(entity.getC1FromDate());
		tahdrDetailDto.setC1OpenningDate(entity.getC1OpenningDate());
		tahdrDetailDto.setC1ToDate(entity.getC1ToDate());
		tahdrDetailDto.setDeviationFromDate(entity.getDeviationFromDate());
		tahdrDetailDto.setDeviationToDate(entity.getDeviationToDate());
		tahdrDetailDto.setDeviationOpenningDate(entity.getDeviationOpenningDate());
		tahdrDetailDto.setIsDeviationSetLater(entity.getIsDeviationSetLater());
		tahdrDetailDto.setPriceBidOpenningDate(entity.getPriceBidOpenningDate());
		tahdrDetailDto.setPreBidDate(entity.getPreBidDate());
		tahdrDetailDto.setPreQualReq(entity.getPreQualReq());
		tahdrDetailDto.setEmdFee(entity.getEmdFee());
		return tahdrDetailDto;
	}

	private BPartnerDto getPartner(TAHDRDetail entity){
		Bpartner partner =entity.getPartner();
		if(partner != null){
			partner.setLoadDefault(false);
		}
		return partnerConverter.convertEntityToDto(partner, BPartnerDto.class);
	}
	
}
