package com.novelerp.alkyl.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.novelerp.alkyl.dto.InternalUserDto;
import com.novelerp.appbase.master.service.InternalUserService;
import com.novelerp.appbase.master.service.ReferenceListService;
import com.novelerp.appbase.validator.InternalUserValidator;
import com.novelerp.appcontext.dto.BPartnerDto;
import com.novelerp.appcontext.dto.RoleDto;
import com.novelerp.appcontext.dto.UserDto;
import com.novelerp.appcontext.dto.UserRolesDto;
import com.novelerp.appcontext.service.ContextService;
import com.novelerp.appcontext.service.RoleService;
import com.novelerp.appcontext.service.UserDetailsService;
import com.novelerp.appcontext.service.UserRolesService;
import com.novelerp.appcontext.service.UserService;
import com.novelerp.core.dto.CustomResponseDto;
import com.novelerp.core.dto.Errors;
import com.novelerp.core.util.CoreReferenceConstants;


@Controller
@RequestMapping("/rest")
public class InternalUserAlkylController {
	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private UserRolesService userRolesService;
	
	@Autowired
	private ReferenceListService refListService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	@Qualifier("jwtUserContext")
	private ContextService contextService;
	
	@Autowired
	private InternalUserService internalUserService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private InternalUserValidator internalUserValidator;
	
	@RequestMapping(value={"/getInternalUserDropDown"},method=RequestMethod.POST,produces="application/json")
	public @ResponseBody CustomResponseDto getInternalUserDropDown(){
		CustomResponseDto resp = new CustomResponseDto();
//		Map<String,String> roleList =refListService.getReferenceListMap(CoreReferenceConstants.ROLE_LIST);
//		 List<RoleDto> roleList=roleService.findDto("getUserRoles", null);
		 List<RoleDto> roleList=roleService.findDtos("getRolesForNewUser", null);
		Map<String,String> plantList =refListService.getReferenceListMap(CoreReferenceConstants.PlANT);
		Map<String,String> costCenterList =refListService.getReferenceListMap(CoreReferenceConstants.COST_CENTER);
		Map<String, Object> params = new HashMap<String, Object>();
		List<UserDto> internalUserList=userService.findDtos("getAllInternalUser", params);
		resp.addObject("plantList",plantList);
		resp.addObject("roleList", roleList);
		resp.addObject("costCenterList", costCenterList);
		resp.addObject("internalUserList", internalUserList);
		return resp;
		
	}
	
	@RequestMapping(value={"/saveIternalUser"},method=RequestMethod.POST,produces="application/json")
	public @ResponseBody CustomResponseDto saveInernalUser(@RequestBody UserRolesDto userRolesDto) {
		CustomResponseDto response =new CustomResponseDto();
		Errors errors =  new Errors();
		internalUserValidator.validateInternalUser(userRolesDto, errors);
		if(errors.getErrorCount()>0){
			response.setSuccess(false);
			response.setMessage(errors.getErrorString());
			return response;
		}else{
			response= internalUserService.saveInteralUserCore(userRolesDto);
		}
		return response;	
		
	}
	
	@RequestMapping(value = "/getInternalUsersList", method = RequestMethod.POST)
	public @ResponseBody CustomResponseDto getInternalUsersList(){
		CustomResponseDto response= new CustomResponseDto();
		Map<String,String> plantList =refListService.getReferenceListMap(CoreReferenceConstants.PlANT);
		Map<String,String> costCenterList =refListService.getReferenceListMap(CoreReferenceConstants.COST_CENTER);
		List<RoleDto> roleList=roleService.findDtos("getRolesForNewUser", null);
		RoleDto role=contextService.getDefaultRole();
		List<UserRolesDto> userRolesList=null;
		if(role!=null ){
			BPartnerDto bpartnerdto =contextService.getPartner();
			userRolesList=userRolesService.getUserRoles(null,null,bpartnerdto);
		}
		Map<String, Object> params = new HashMap<String, Object>();
		List<UserDto> internalUserList=userService.findDtos("getAllInternalUser", params);
		response.addObject("plantList",plantList);
		response.addObject("costCenterList",costCenterList);
		response.addObject("roleList", roleList);
		response.addObject("userRolesList", userRolesList);
		response.addObject("internalUserList", internalUserList);
		return  response;
	}
	
	@RequestMapping(value={"/updateInternalUser"},method=RequestMethod.POST,produces="application/json")
	public @ResponseBody CustomResponseDto updateInternalUser(@RequestBody UserRolesDto userRolesDto) {
		CustomResponseDto response =new CustomResponseDto();

		response= internalUserService.updateInternalUserCore(userRolesDto);
		
		return response;
	}
	
	/*@RequestMapping(value={"/updateUserRoles"},method=RequestMethod.POST,produces="application/json")
	public @ResponseBody CustomResponseDto updateUserRoles(@RequestBody List<UserRolesDto> userRolesDto) {
		CustomResponseDto response =null;List<UserRolesDto> resRoles=new ArrayList<UserRolesDto>();
		try{
			for(UserRolesDto dto:userRolesDto){
				
				resRoles.add(dto.getUserRolesId()==null?
						userRolesService.save(dto):userRolesService.updateDto(dto));
			}
			response=new CustomResponseDto(true,"Record Created!");
			response.addObject("userRolesDtos",resRoles);
		}catch(Exception e){
			return new CustomResponseDto(true,e.getMessage());
		}
		return response;
	}*/
	
	@PostMapping(value="/updateUserRoles")
	public @ResponseBody CustomResponseDto updateUserRoles(@RequestBody InternalUserDto internalUser) {
		
		CustomResponseDto response =null;List<UserRolesDto> resRoles=new ArrayList<UserRolesDto>();
		try{
			for(UserRolesDto dto:internalUser.getUserRolesDtos()){
				
				RoleDto role=new RoleDto();
				role.setRoleId(dto.getRoleId());
				
				UserDto user=new UserDto();
				user.setUserId(dto.getUserId());
				
				dto.setUser(user);
				dto.setRole(role);
				
				resRoles.add(dto.getUserRolesId()==null?
						userRolesService.save(dto):userRolesService.updateDto(dto));
			}
			response=new CustomResponseDto(true,"Records Updated!");
			response.addObject("userRolesDtos",resRoles);
		}catch(Exception e){
			return new CustomResponseDto(true,e.getMessage());
		}
		return response;
	}
	
	@PostMapping(value="/getUserRoles/{userId}")
	public @ResponseBody CustomResponseDto getUserRoles(@PathVariable("userId") Long userId){
		
		CustomResponseDto resp = null;Map<String, Object> params= new HashMap<>();
		params.put("userId", userId);
		List<UserRolesDto> userRolesList=userRolesService.findDtos("getUserRolesListByUserId",params);
		
		resp=new CustomResponseDto("userRolesList", userRolesList);
		return resp;
	}
	
	@PostMapping(value="/deleteUserRoles/{userRolesId}")
	public @ResponseBody CustomResponseDto deleteUserRoles(@PathVariable("userRolesId") Long userRolesId){
		
		boolean result = userRolesService.deleteById(userRolesId);
		
		return result?new CustomResponseDto(true,"Record Deleted!"):new CustomResponseDto(false,"Failed to Delete Record!");
	}
	
	
	@PostMapping(value="/getCostcentreList/{plantCode}")
	public @ResponseBody String getInvoiceDetails(@PathVariable("plantCode") String plantCode){
//	String url="https://172.18.2.28:44300/sap/bc/yweb03_ws_13?sap-client=100&gsber=plant";
		String url="http://103.231.11.54:8000/sap/bc/yweb03_ws_13?sap-client=009&gsber="+1820;
		 URLConnection request=null;   
		try {
	            URL u = new URL(url);
	            request =  u.openConnection();

	            request.connect();


	            BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
	            StringBuilder sb = new StringBuilder();
	            String line;
	            while ((line = br.readLine()) != null) {
	                sb.append(line + "\n");
	            }
	            br.close();
               /* ObjectMapper objJson = new ObjectMapper();
                VehicleRegistrationDto dto = objJson.readValue(sb.toString(), VehicleRegistrationDto.class);
                vehicleRegistrationComponent.saveInvoiceMailDetail(dto,regId);*/
	            return sb.toString();


	        } catch (MalformedURLException ex) {
	            ex.printStackTrace();
	        } catch (IOException ex) {
	            ex.printStackTrace();
	        }finally {
	            if (request != null) {
	                try {
	                	((HttpURLConnection) request).disconnect();
	                } catch (Exception ex) {
	                    ex.printStackTrace();
	                }
	            }
	        }
	        return null;
	}
	
//	@Scheduled(cron = "0 0 * * * *")
//	@PostMapping(value = "/fetchAnyUser")
//	public void fetchAnyUser() {
//		System.out.println("fetchAnyUser Scheduler after 1 hour");
//		
//		List<UserDto> userList=userService.findDtos("getInternalUserById", null);
//		 return;
//	}
}
