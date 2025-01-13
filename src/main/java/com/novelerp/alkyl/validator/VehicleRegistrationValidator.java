package com.novelerp.alkyl.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.novelerp.alkyl.dto.VehicleRegistrationDto;
import com.novelerp.core.dto.Errors;
import com.novelerp.core.util.ValidationUtil;
import com.novelerp.core.validator.Validator;
@Component
public class VehicleRegistrationValidator implements Validator{
	
	@Autowired
	private ValidationUtil validationUtil;

	@Override
	public void validate(Object object, Errors errors) {
		// TODO Auto-generated method stub
		
	}
public void validateVehicleRegistration(VehicleRegistrationDto dto, Errors errors){
		
		if(dto==null){
			
			validationUtil.reject(errors, "Empty Object", "Empty Form");
		}
		if(dto.getRequiredOn()==null){
			validationUtil.reject(errors, "Required ON", "Required on cannot be null");
		}
		
		if(dto.getSaleOrderNo()!=null) {
		if(dto.getPlant()==null){			
			validationUtil.reject(errors, "Plant", "Plant cannot be null");
		}
		}
		if(dto.getPoNo()!=null) {
			if(dto.getFromPlant()==null || "".equals(dto.getFromPlant())) {
				validationUtil.reject(errors, "Plant", "From Plant cannot be null");
			}
			
			if(dto.getToPlant()==null || "".equals(dto.getToPlant())) {
				validationUtil.reject(errors, "Plant", "To Plant cannot be null");
			}
		}
		/*if(dto.getSoldToParty()==null){
			validationUtil.reject(errors, "Solt To Party", "Solt To Party cannot be null");
		}
		if(dto.getShipToParty()==null){
			validationUtil.reject(errors, "ship To Party", "ship To Party cannot be null");
		}
		if(dto.getDestination()==null){
			validationUtil.reject(errors, "Destination", "Destination cannot be null");
		}
		if(dto.getQty()==null){
			validationUtil.reject(errors, "Qty", "Qty cannot be null");
		}
		if(dto.getMeCode()==null){
			validationUtil.reject(errors, "ME CODE", "ME Code cannot be null");
		}
		if(dto.getTrasnporter()==null){
			validationUtil.reject(errors, "Transporter", "Transporter cannot be null");
		}
		if(dto.getVehicleType()==null){
			validationUtil.reject(errors, "Vehicle Type", "Vehicle Type cannot be null");
		}
		if(dto.getFreightScope()==null){
			validationUtil.reject(errors, "Frieght Scope", "Frieght Scope cannot be null");
		}*/
}
public void validateReportVehicle(VehicleRegistrationDto dto, Errors errors){
	
	if(dto==null){
		
		validationUtil.reject(errors, "Empty Object", "Empty Form");
	}
	if(dto.getVehicleRegistrationNo()==null){
		validationUtil.reject(errors, "Registration No", "Registration No cannot be null");
	}
	if(dto.getLicenseNo()==null){
		validationUtil.reject(errors, "Licence No", "Licence No cannot be null");
	}
	if(dto.getDriverName()==null){
		validationUtil.reject(errors, "Driver Name", "Driver Name cannot be null");
	}
	if(dto.getDriverPic()==null){
		validationUtil.reject(errors, "Driver Photo", "Driver Photo cannot be null");
	}
	if(dto.getDocPic()==null){
		validationUtil.reject(errors, "Document Photo", "Document Photo cannot be null");
	}
}
}
