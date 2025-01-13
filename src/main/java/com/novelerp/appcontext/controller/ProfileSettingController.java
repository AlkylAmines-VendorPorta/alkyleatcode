package com.novelerp.appcontext.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.appcontext.dto.BPartnerDto;
import com.novelerp.appcontext.dto.ProfileSettingDto;
import com.novelerp.appcontext.service.ContextService;
import com.novelerp.appcontext.service.MediaService;
import com.novelerp.appcontext.service.ProfileSettingService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.core.dto.CustomResponseDto;

@Controller
public class ProfileSettingController {
	
	@Autowired
	private ProfileSettingService profileSettingService;
	
	@Autowired
	 @Qualifier("jwtUserContext") private ContextService contextService;
	
	@Autowired
	/*@Qualifier(AppBaseConstant.SFTP_MEDIA_SERVICE_IMPL)*/
    /*@Qualifier(AppBaseConstant.LOCAL_MEDIA_SERVICE_IMPL)*/
    @Qualifier(AppBaseConstant.FTP_MEDIA_SERVICE_IMPL)
    private MediaService mediaService;
	
	@RequestMapping(value = "/getProfileSetting", method = RequestMethod.POST)
	public @ResponseBody CustomResponseDto getUserById() {
		CustomResponseDto response=new CustomResponseDto();
		BPartnerDto partner=contextService.getPartner();
		ProfileSettingDto profile=profileSettingService.findDto("getProfileSettingByPartnerId", AbstractContextServiceImpl.getParamMap("partnerId", partner.getbPartnerId()));
		byte[] file=null;
		  if(profile!=null && profile.getProfileLogo()!=null){
			  file=  mediaService.getByteArrayByFile(profile.getProfileLogo().getName());
			  response.addObject("RESULT", true);
			  response.addObject("ImageFile", file);
		  }else{
			  response.addObject("RESULT", false);
			  response.addObject("MESSAGE", "IMAGE NOT FOUND");
		  }  
		response.addObject("profile", profile);
		response.addObject("no_image", "resources/home/img/No_Image_Available.jpg");
		return response;
	}
	
	@RequestMapping(value = "/updateProfileSetting", method = RequestMethod.POST)
	public @ResponseBody CustomResponseDto updateProfileSetting(@ModelAttribute("profileSetting")  ProfileSettingDto profileSetting) {
		CustomResponseDto response=new CustomResponseDto();
		boolean validateProfile=true;
		if(validateProfile){
			profileSetting=profileSettingService.updateDto(profileSetting);
			response.addObject("RESULT", true);
			response.addObject("MESSAGE", "Profile Updated,Re Login to see changes");
		}else{
			response.addObject("RESULT", false);
			response.addObject("MESSAGE", "Profile Not Updated");
		}
		return response;	
	}


}
