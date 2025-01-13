package com.novelerp.eat.controller;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.appcontext.dto.BPartnerDto;
import com.novelerp.appcontext.dto.ProfileSettingDto;
import com.novelerp.appcontext.dto.RoleDto;
import com.novelerp.appcontext.dto.UserDto;
import com.novelerp.appcontext.service.BPartnerService;
import com.novelerp.appcontext.service.ContextService;
import com.novelerp.appcontext.service.MediaService;
import com.novelerp.appcontext.service.ProfileSettingService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;

@Controller("EatAppController")
public class AppController {
	
	@Autowired
	 @Qualifier("jwtUserContext") private ContextService contextService;
	@Autowired
	private BPartnerService partnerService;
	
	@Autowired
	private ProfileSettingService profileSettingService;
	
	@Autowired
	/*@Qualifier(AppBaseConstant.SFTP_MEDIA_SERVICE_IMPL)*/
    /*@Qualifier(AppBaseConstant.LOCAL_MEDIA_SERVICE_IMPL)*/
    @Qualifier(AppBaseConstant.FTP_MEDIA_SERVICE_IMPL)
    private MediaService mediaService;
	
	@RequestMapping(value= {"/"},method =RequestMethod.GET)
	public ModelAndView welcome(@RequestParam(value = "partnerString", required=false) String partnerString,HttpServletRequest request) throws UnsupportedEncodingException{
		   ModelAndView model =new ModelAndView("home");	
		   Object obj=request.getSession().getAttribute("UserSession");
			if(obj!=null){
				UserDto userDto= contextService.getUser();
				BPartnerDto partner=contextService.getPartner();
				RoleDto role=contextService.getDefaultRole();
				if(userDto == null || userDto.getRoles() == null || partner==null || partner.getbPartnerId()==null || role==null || role.getValue()==null){		
						
				}else{
					String isPasswordUpdated=userDto.getIsPasswordUpdated();
					if(isPasswordUpdated!=null && isPasswordUpdated.equalsIgnoreCase("Y")){
						/*String url=partnerService.checkPartnerValidity(userDto,role);*/
						String url=role.getViewName();
						/*partnerValidator.checkVendorValidity(partner,errors,role);
						if (CommonUtil.isCollectionEmpty(errors.getErrorList())) {
							role = userDto.getRoles().iterator().next();
						}else{
							role = appContext.getRole(AppBaseConstant.ROLE_PARTNER_USER);
						}*/
						
						model= new ModelAndView("forward:/"+url);
					}else{
						model= new ModelAndView("resetPassword");
					}
				}
			}else if(partnerString!=null){
				System.out.println("partnerString"+partnerString);
				ProfileSettingDto profile=profileSettingService.findDto("getProfileSettingByUrlString", AbstractContextServiceImpl.getParamMap("partnerString", partnerString));
				byte[] file=null;
				if(profile!=null){
					  if(profile.getProfileLogo()!=null){
						  file=  mediaService.getByteArrayByFile(profile.getProfileLogo().getName());
						  file = Base64.encodeBase64(file);
				           String base64Encoded = new String(file, "UTF-8");
						  model.addObject("userLogo", base64Encoded);
					  }
					  model.addObject("urlPattern", profile.getUrlPattern());
					  model.addObject("themeColor", profile.getThemeColor());
				}
			}
	    return model;
	}
	
	@RequestMapping(value= {"/partner/{partnerString}"},method =RequestMethod.GET)
	public ModelAndView partnerString(@PathVariable(value = "partnerString", required=false) String partnerString,HttpServletRequest request){
		   ModelAndView model =new ModelAndView("home");	
		   System.out.println("asdfghjklasdfghjkasdfgh: "+partnerString);
		   return model;
	}
}
