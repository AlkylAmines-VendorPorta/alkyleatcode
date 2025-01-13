package com.novelerp.appcontext.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.novelerp.appcontext.dto.SystemConfiguratorDto;
import com.novelerp.appcontext.service.SystemConfiguratorService;
import com.novelerp.core.dto.Errors;
import com.novelerp.core.util.ValidationUtil;
import com.novelerp.core.validator.Validator;

@Component
public class SystemConfiguratorValidator implements Validator {
	
		@Autowired
		private ValidationUtil validator;

		@Autowired
		private SystemConfiguratorService sysConfigService;
		
		public void validate(Object object, Errors errors) {
			/*Validates only UserDto object*/
			if(!object.getClass().isAssignableFrom(SystemConfiguratorDto.class)){
				validator.classNotSupported(errors, "invalid.classObject", "Invalid class Object");
				return;
			}
			SystemConfiguratorDto dto = (SystemConfiguratorDto) object;
            if(dto!=null){
            		validator.rejectIfEmpty(dto.getName(), errors, "invalid.Value", "Value is Required");
    				validator.rejectIfEmpty(dto.getCode(), errors, "invalid.Code", "Code is Required");
                	validator.rejectIfEmpty(dto.getValue(), errors, "invalid.Key", "Key is Required");
    				validator.rejectIfEmpty(dto.getDescription(), errors, "invalid.Description", "Description is Required");
                	validator.rejectIfEmpty(dto.getConfigType(), errors, "invalid.ConfigType", "ConfigType is Required");
                String sysConfigName=sysConfigService.getPropertyConfigurator(dto.getValue().trim());
                if(!sysConfigName.equalsIgnoreCase("") && dto.getSystemConfiguratorId()==null){
                	validator.rejectIfEmpty("", errors, "unique.Name", "Input KEY is already in use,Provide different and unique KEY");
    			}
            }
			
		}

	}
