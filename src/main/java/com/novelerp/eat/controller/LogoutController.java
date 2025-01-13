package com.novelerp.eat.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.appcontext.dto.ContextDto;
import com.novelerp.appcontext.dto.RoleDto;
import com.novelerp.appcontext.dto.UserDto;
import com.novelerp.appcontext.dto.UserSessionDto;
import com.novelerp.appcontext.service.ContextService;
import com.novelerp.appcontext.service.UserSessionService;
import com.novelerp.eat.util.TenderContext;

@Controller
@RequestMapping("/rest")
public class LogoutController 
{
	@Autowired
	 @Qualifier("jwtUserContext") private ContextService contextService;
	
	@Autowired
	private TenderContext  tenderContext;
	
	@Autowired
	private RedisTemplate<String, Object> template;
	
	@Autowired
	private UserSessionService userSessionService;
	
	/*@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ModelAndView logoutUser(HttpServletRequest request) {
		RoleDto role=contextService.getDefaultRole();
		UserDto user=contextService.getUser();
		if(role!=null && user!=null){
			if(AppBaseConstant.ROLE_VENDOR_ADMIN.equals(role.getValue())){
				tenderContext.removeBidderFromAll(user.getUserId());
			}else{
				tenderContext.removeUserFromAll(user.getUserId());
			}
		}
		request.getSession().invalidate();
		ModelAndView mav = new ModelAndView("redirect:/");
		return mav;
	}*/
	
	@RequestMapping(value= {"/Access_Denied"},method ={RequestMethod.GET, RequestMethod.POST})
	public ModelAndView accessDenied(HttpServletRequest request){
		ModelAndView model=new ModelAndView("sessionOut");
		RoleDto role=contextService.getDefaultRole();
		UserDto user=contextService.getUser();
		if(role!=null && user!=null){
			if(AppBaseConstant.ROLE_VENDOR_ADMIN.equals(role.getValue())){
				tenderContext.removeBidderFromAll(user.getUserId());
			}else{
				tenderContext.removeUserFromAll(user.getUserId());
			}
		}
		request.getSession().invalidate();
		model.addObject("viewType","accessDenied");
		return model;
	}
		
	@RequestMapping(value= {"/sessionOut"},method =RequestMethod.GET)
	public ModelAndView sessionOut(HttpServletRequest request){
		ModelAndView model=new ModelAndView("sessionOut");
		RoleDto role=contextService.getDefaultRole();
		UserDto user=contextService.getUser();
		if(role!=null && user!=null){
			if(AppBaseConstant.ROLE_VENDOR_ADMIN.equals(role.getValue())){
				tenderContext.removeBidderFromAll(user.getUserId());
			}else{
				tenderContext.removeUserFromAll(user.getUserId());
			}
		}
		request.getSession().invalidate();
		model.addObject("viewType","sessionExpired");
		return model;
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ResponseEntity<?> logoutUser(HttpServletRequest request) {
		
		ContextDto context=contextService.getContext();
		request.getSession().invalidate();
		template.delete(context.getUserDto().getAuthToken());
		UserSessionDto userSession=userSessionService.updateUserAuthTokenSession(context.getUserDto().getAuthToken());
		if(userSession!=null && userSession.getResponse()!=null && userSession.getResponse().isHasError()==false){
			return ResponseEntity.ok("Done");
		}
		return ResponseEntity.ok("Failed");
	}
	
}