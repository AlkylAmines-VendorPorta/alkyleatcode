package com.novelerp.appbase.validator;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.novelerp.appbase.master.dto.PartnerOrgDto;
import com.novelerp.appbase.master.dto.PartnerOrgExperienceDto;
import com.novelerp.appbase.master.service.PartnerOrgService;
import com.novelerp.commons.util.CommonUtil;
import com.novelerp.core.dto.Errors;
import com.novelerp.core.util.ValidationUtil;
import com.novelerp.core.validator.Validator;

/**
 * 
 * @author Varsha Patil
 *
 */
@Component
public class PartnerOrgExperienceValidator implements Validator{

	@Autowired
	private ValidationUtil validator;
    
	@Autowired
	private PartnerOrgService partnerOrgService;
		
		@Override
		public void validate(Object object, Errors errors) {
			if(!object.getClass().isAssignableFrom(PartnerOrgExperienceDto.class)){
				validator.classNotSupported(errors, "invalid.classObject", "Invalid class Object");
				return;
			}
			PartnerOrgExperienceDto dto=(PartnerOrgExperienceDto) object;
			checkExpYear(dto,errors);
			
		}
		public void checkExpYear(PartnerOrgExperienceDto dto,Errors errors){
			if(dto.getExpDesignMonths()==null || dto.getExpDesignYear()==null){
				validator.reject(errors, "invalid", "Experince design month and year is required");
			}
			if(dto.getExpManufacturingMonths()==null || dto.getExpManufacturingYear()==null){
				validator.reject(errors, "invalid", "Experince manufacturing month and year is required");
			}
			if(dto.getExpSupplyingMonths()==null || dto.getExpSupplyingYear()==null){
				validator.reject(errors, "invalid", "Experince supplying month and year is required");
			}
			if(dto.getExpTestingMonths()==null || dto.getExpTestingYear()==null){
				validator.reject(errors, "invalid", "Experince testing month and year is required");
			}
			if(CommonUtil.isListEmpty(errors.getErrorList())){
			  validateYear(dto,errors);
			}
		}
		public void validateYear(PartnerOrgExperienceDto dto,Errors errors){
			if(dto==null || dto.getPartnerOrg()==null || dto.getPartnerOrg().getPartnerOrgId()==null){
				validator.reject(errors, "invalid", "Factory id not found");
				return;
			}
	    	PartnerOrgDto partnerOrg=partnerOrgService.findDto(dto.getPartnerOrg().getPartnerOrgId());
	    	if(partnerOrg==null || partnerOrg.getEstdDate()==null){
	    		validator.reject(errors, "invalid", "Factory data not found");
				return;
	    	}
	    	/*int year=DateUtil.getYearsDiff(partnerOrg.getEstdDate(),new Date());*/
	    	LocalDate now = LocalDate.now();
	    	LocalDate estdDate = partnerOrg.getEstdDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	    	Period period=Period.between(estdDate, now);
	    	Short years= (short) period.getYears();
	    	Short months= (short) period.getMonths();
	    	if(dto.getExpManufacturingYear()>years || (dto.getExpManufacturingYear().compareTo(years)==0 && dto.getExpManufacturingMonths()>months)){
	    		validator.reject(errors, "invalid", "Manufacturing experience year and month greater than factory established date");
	    	}
	    	if(dto.getExpDesignYear()>years || (dto.getExpDesignYear().compareTo(years)==0 && dto.getExpDesignMonths()>months)){
	    		validator.reject(errors, "invalid", "Design experience year and month greater than factory established date");
	    	}
	    	if(dto.getExpSupplyingYear()>years || (dto.getExpSupplyingYear().compareTo(years)==0 && dto.getExpSupplyingMonths()>months)){
	    		validator.reject(errors, "invalid", "Supplying experience year and month greater than factory established date");
	    	}
	    	if(dto.getExpTestingYear()>years || (dto.getExpTestingYear().compareTo(years)==0 && dto.getExpTestingMonths()>months)){
	    		validator.reject(errors, "invalid", "Testing experience year and month greater than factory established date");
	    	}
	    	
	    }
}
