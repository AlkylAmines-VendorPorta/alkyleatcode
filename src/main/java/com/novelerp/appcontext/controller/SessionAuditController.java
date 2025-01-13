package com.novelerp.appcontext.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.novelerp.appcontext.dto.UserDto;
import com.novelerp.appcontext.dto.UserRolesDto;
import com.novelerp.appcontext.dto.UserSessionDto;
import com.novelerp.appcontext.service.UserRolesService;
import com.novelerp.appcontext.service.UserSessionService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.commons.util.CommonUtil;
import com.novelerp.core.dto.CustomResponseDto;
import com.novelerp.eat.dto.PriceBidDto;
import com.novelerp.eat.service.PriceBidService;
import com.novelerp.eat.service.TempPriceBidService;

/**
 * 
 * @author Varsha
 *
 */
@Controller
public class SessionAuditController {
	
	@Autowired
	private UserSessionService userSessionService;
	
	@Autowired
	private UserRolesService userRolesService;
	
	@Autowired
	private PriceBidService priceBidService;
	
	@Autowired
	private TempPriceBidService tempPriceBidService;
	
	@RequestMapping(value= {"/sessionAudit"},method =RequestMethod.GET)
	public ModelAndView sessionAudit()
	{
		return new ModelAndView("sessionAudit");
	}
	
	@RequestMapping(value="/getSessionList", method=RequestMethod.POST, produces="application/json")
	public @ResponseBody CustomResponseDto getSessionList(@RequestParam("pageNumber")int pageNumber, 
														  @RequestParam("pageSize") int pageSize,
														  @RequestParam("searchMode") String searchMode , 
														  @RequestParam("searchValue") String searchValue){
        Map<String, Object> objectMap = new HashMap<>();
		CustomResponseDto customResponse=new CustomResponseDto();
		long countResult = userSessionService.getSessionAuditsQueryCount(searchMode, searchValue);
		int LastPage = (int) ((countResult / pageSize) + 1);
		List<UserSessionDto> sessions = userSessionService.getSessionAuditList(pageNumber, pageSize, searchMode, searchValue);
		objectMap.put("LastPage", LastPage);
		customResponse.setData(sessions);
		customResponse.setObjectMap(objectMap);
		return customResponse;
	}
	
	@RequestMapping(value="/getSessionListByRole", method=RequestMethod.POST, produces="application/json")
	public @ResponseBody CustomResponseDto getSessionListByRole(@RequestParam("pageNumber")int pageNumber, 
														  @RequestParam("pageSize") int pageSize,
														  @RequestParam("searchMode") String searchMode , 
														  @RequestParam("searchValue") String searchValue){
        Map<String, Object> objectMap = new HashMap<>();
		CustomResponseDto customResponse=new CustomResponseDto();
		long countResult = userSessionService.getSessionAuditsQueryCount(searchMode, searchValue);
		int LastPage = (int) ((countResult / pageSize) + 1);
		List<UserSessionDto> sessions = userSessionService.getSessionAuditList(pageNumber, pageSize, searchMode, searchValue);
		objectMap.put("LastPage", LastPage);
		customResponse.setData(sessions);
		customResponse.setObjectMap(objectMap);
		return customResponse;
	}	
	
	@RequestMapping(value="/getSessionActivity/{sessionString}", method=RequestMethod.POST, produces="application/json")
	public @ResponseBody CustomResponseDto getSessionActivity(@PathVariable("sessionString") String sessionString){
		CustomResponseDto customResponse=new CustomResponseDto();
		UserSessionDto userSession=userSessionService.findDto("getUserSessionBySessionKey", AbstractContextServiceImpl.getParamMap("sessionString", sessionString));
		if(userSession!=null){
			UserDto user=userSession.getCreatedBy();
			if(user!=null){
				UserRolesDto userRole=userRolesService.getUserRolesByUserId(user.getUserId());
				String role=userRole.getRole().getValue();
				customResponse.addObject("role", role);
				customResponse.addObject("result", true);
			}else{
				customResponse.addObject("result", false);
				customResponse.addObject("message", "No User assign to this  session");
			}
		}else{
			customResponse.addObject("result", false);
			customResponse.addObject("message", "No Session Details Found");
		}
		return customResponse;
	}
	
	@RequestMapping(value="/getBidsSubmittedBySessionKey/{sessionString}", method=RequestMethod.POST, produces="application/json")
	public @ResponseBody CustomResponseDto getBidsSubmittedBySessionKey(@PathVariable("sessionString") String sessionString){
		CustomResponseDto customResponse=new CustomResponseDto();
		List<PriceBidDto> priceBidList=priceBidService.getBidListBySessionId(sessionString);
		List<PriceBidDto> tempPriceBidList =tempPriceBidService.getBidListBySessionId(sessionString);
		if(!CommonUtil.isCollectionEmpty(priceBidList) && !CommonUtil.isCollectionEmpty(tempPriceBidList)){
						priceBidList.addAll(tempPriceBidList);
		}
		customResponse.addObject("priceBidList", priceBidList);
		customResponse.addObject("sessionString", sessionString);
		return customResponse;
	}
	
	
}
