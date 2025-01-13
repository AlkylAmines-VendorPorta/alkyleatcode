package com.novelerp.appbase.master.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.novelerp.alkyl.component.VendorProfileHistoryComponent;
import com.novelerp.alkyl.dto.PurchaseOrderLineDto;
import com.novelerp.alkyl.dto.VendorCredentialReadDto;
import com.novelerp.appbase.master.dao.PartnerCompanyAddressDao;
import com.novelerp.appbase.master.dto.PartnerCompanyAddressDto;
import com.novelerp.appbase.master.entity.PartnerCompanyAddress;
import com.novelerp.appbase.master.service.PartnerCompanyAddressService;
import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.appcontext.dto.BPartnerDto;
import com.novelerp.appcontext.dto.LocationDto;
import com.novelerp.appcontext.dto.RoleDto;
import com.novelerp.appcontext.dto.UserDetailsDto;
import com.novelerp.appcontext.dto.UserDto;
import com.novelerp.appcontext.entity.Location;
import com.novelerp.appcontext.service.ContextService;
import com.novelerp.appcontext.service.LocationService;
import com.novelerp.appcontext.service.UserService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.appcontext.util.ContextConstant;
import com.novelerp.core.dto.ResponseDto;
/**
 * @author Aman/ Vivek Birdi
 *
 */
@Service
public class PartnerCompanyAddressServiceImpl extends AbstractContextServiceImpl<PartnerCompanyAddress, PartnerCompanyAddressDto> implements PartnerCompanyAddressService{

	@Autowired
	private PartnerCompanyAddressDao  partnerCompanyAddressDao;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private LocationService locationService;
	@Autowired
	@Qualifier("jwtUserContext") 
	private ContextService contextService;
	@PostConstruct
	public void init(){
		super.init(PartnerCompanyAddressServiceImpl.class, partnerCompanyAddressDao, PartnerCompanyAddress.class, PartnerCompanyAddressDto.class);
		//setObjectConverter(partnerCompanyAddressConverter);
		setByPassProxy(true);
	}	
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public PartnerCompanyAddressDto save(PartnerCompanyAddressDto dto) {
		LocationDto location = dto.getLocation();
		if(location.getLocationId()==null){
			location = locationService.save(location);
		}else{
			location = locationService.updateDto(location);
		}
		dto.setLocation(location);
		return super.save(dto);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public PartnerCompanyAddressDto updateDto(PartnerCompanyAddressDto dto) {
		LocationDto location = dto.getLocation();
		location = locationService.updateDto(location);
		dto.setLocation(location);
		PartnerCompanyAddressDto addressDto = super.updateDto(dto);
		addressDto.setLocation(location);
		return addressDto;
	}
	@Override
	public List<LocationDto> getLocationByLocationType(String locationType,Long bPartnerId)
	{
		List<Location> locationList=partnerCompanyAddressDao.getLocationByLocationType(locationType,bPartnerId);
		List<LocationDto> locationDtoList=new ArrayList<LocationDto>();
		if(!locationList.isEmpty())
		{
			for(Location loc:locationList)
			{
				LocationDto dto=new LocationDto();
				dto.setLocationId(loc.getLocationId());
				dto.setAddress1(loc.getAddress1());
				locationDtoList.add(dto);
			}
		}
		return locationDtoList;
	}

	@Override
    @Transactional(propagation=Propagation.REQUIRED)
	public PartnerCompanyAddressDto savePartnerAddress(PartnerCompanyAddressDto dto) {
		if(dto==null){
			return null;
		}
		RoleDto role=contextService.getDefaultRole();
		if(role==null || role.getValue()==null)
		{   dto.setResponse(new ResponseDto(true, "Session Timeout"));
			return dto;
		}
		if(role.getValue().equals(ContextConstant.USER_TYPE_PATNER_USER) || role.getValue().equals(ContextConstant.USER_TYPE_VENDOR_ADMIN) || AppBaseConstant.ROLE_INFRA_ADMIN.equals(role.getValue()))
		{
			
			dto.setIsEEApproved(null);
			dto.setIsCEApproved(null);
			dto.setEeComment(null);
			dto.setCeComment(null);
		}
		if(dto!=null && dto.getPartnerCompanyAddressId()!=null && (role.getValue().equals(ContextConstant.USER_TYPE_EXECUTIVE_ENGINEER)||role.getValue().equals(ContextConstant.USER_TYPE_CHIEF_ENGINEER)))
		{
			Map<String, Object> map=new HashMap<>();
			if (dto.getIsEEApproved()!=null && role.getValue().equals(ContextConstant.USER_TYPE_EXECUTIVE_ENGINEER)) {
			  map.put("eeComment", dto.getEeComment());
			  map.put("isEEApproved", dto.getIsEEApproved());
			}else if (dto.getIsCEApproved()!=null && role.getValue().equals(ContextConstant.USER_TYPE_CHIEF_ENGINEER)) {
				  map.put("ceComment", dto.getCeComment());
				  map.put("isCEApproved", dto.getIsCEApproved());
			}
			int result= updateByJpql(map, "partnerCompanyAddressId", dto.getPartnerCompanyAddressId());
			if(result>0)
			{
				dto.setResponse(new ResponseDto(false, "Record Updated"));
				return dto;
			}else{
				dto.setResponse(new ResponseDto(true, "Error in Updated"));
				return dto;
			}
		}
		if(dto.getPartnerCompanyAddressId() == null){	
			return save(dto);
		}
		return updateDto(dto);
	}
	

	@Override
	public List<PartnerCompanyAddressDto> getUsersPrimaryAddressList(String searchText) {
		List<PartnerCompanyAddressDto> partnerCompanyAddressList=new ArrayList<>();
		List<PartnerCompanyAddress> partnerCompanyAddress=partnerCompanyAddressDao.getUsersPrimaryAddressList(searchText);
		
		partnerCompanyAddressList= getDtoList(partnerCompanyAddress);
		return partnerCompanyAddressList;
	}

	@Override
	public List<PartnerCompanyAddressDto> getRegisteredVendors(String searchText) {
		List<PartnerCompanyAddressDto> partnerCompanyAddressList=new ArrayList<>();
		List<PartnerCompanyAddress> partnerCompanyAddress=partnerCompanyAddressDao.getRegisteredVendors(searchText);
		
		partnerCompanyAddressList= getDtoList(partnerCompanyAddress);
		return partnerCompanyAddressList;
	}

	@Override
	public List<PartnerCompanyAddressDto> getNotRegisteredVendors(String searchText) {
		List<PartnerCompanyAddressDto> partnerCompanyAddressList=new ArrayList<>();
		List<PartnerCompanyAddress> partnerCompanyAddress=partnerCompanyAddressDao.getNotRegisteredVendors(searchText);
		
		partnerCompanyAddressList= getDtoList(partnerCompanyAddress);
		return partnerCompanyAddressList;
	}

	@Override
	public List<PartnerCompanyAddressDto> getAllInvitedVendors(String searchText) {
		List<PartnerCompanyAddressDto> partnerCompanyAddressList=new ArrayList<>();
		List<PartnerCompanyAddress> partnerCompanyAddress=partnerCompanyAddressDao.getAllInvitedVendors(searchText);
		
		partnerCompanyAddressList= getDtoList(partnerCompanyAddress);
		return partnerCompanyAddressList;
	}

	@Override
	public List<PartnerCompanyAddressDto> getAllVendors(String searchText) {
		List<PartnerCompanyAddressDto> partnerCompanyAddressList=new ArrayList<>();
		List<PartnerCompanyAddress> partnerCompanyAddress=partnerCompanyAddressDao.getAllVendors(searchText);
		
		partnerCompanyAddressList= getDtoList(partnerCompanyAddress);
		return partnerCompanyAddressList;
	}

	@Override
	public List<PartnerCompanyAddressDto> getVendorsforEnquiry(String name) {
		List<PartnerCompanyAddressDto> partnerCompanyAddressList=new ArrayList<>();
		List<PartnerCompanyAddress> partnerCompanyAddress=partnerCompanyAddressDao.getVendorsforEnquiry(name);
		
		partnerCompanyAddressList= getDtoList(partnerCompanyAddress);
		return partnerCompanyAddressList;
	}

	@Override
	public List<Object> getAllVendorsForUpdateCred(String searchText) {
		List<Object> obj=partnerCompanyAddressDao.getAllVendorsForUpdateCredential(searchText);
		return obj;
	}

	@Override
	public List<Object> getResendInvitationVendors(String searchText) {
		// TODO Auto-generated method stub
		List<Object> obj=partnerCompanyAddressDao.getResendInvitationVendorsQuery(searchText);
		return obj;
	}
	
	
	@Override
	public List<PartnerCompanyAddressDto> getVendorListReportbyFilter(VendorCredentialReadDto dto) {
		Map<String, Object> params=getParameterMap(dto);
		List<String> param = new ArrayList<>();
		
		if(dto.getStatus().equals("In Progress")) {
			param.add(AppBaseConstant.PARTNER_STATUS_IN_PROGRESS);
			params.put("status", param);
		}
		/*if(dto.getStatus().equals("Approved")) {
			param.add(AppBaseConstant.PARTNER_STATUS_IN_PROGRESS);
			params.put("status", param);
		}*/
		if(dto.getStatus().equals("Rejected")) {
			param.add(AppBaseConstant.PARTNER_STATUS_REJECT);
			params.put("status", param);
		}
		if(dto.getStatus().equals("Drafted")) {
			param.add(AppBaseConstant.PARTNER_STATUS_DRAFTED);
			params.put("status", param);
		}
		if(dto.getStatus().equals("Complete")) {
			param.add(AppBaseConstant.PARTNER_STATUS_COMPLETE);
			params.put("status", param);
		}
		
		String query=partnerCompanyAddressDao.getVendorCredentailReportlist(dto);
		List<PartnerCompanyAddressDto> UserCredentialList=findDtosByQuery(query, params); 
		
		for (PartnerCompanyAddressDto partneraddress:UserCredentialList) {
			
		  Long partnerID=partneraddress.getPartner().getbPartnerId();
		  UserDto vendorUser = userService.findDto("getUserByPartner", AbstractContextServiceImpl.getParamMap("bPartnerId", partnerID));
		  partneraddress.setUser(vendorUser);
		}
		
		return UserCredentialList;
	}

	private Map<String,Object> getParameterMap(VendorCredentialReadDto dto){
		Map<String, Object> params = new HashMap<String, Object>();
		if(dto.getDistrict()!=null && !dto.getDistrict().equals("")){
			params.put("district", dto.getDistrict());
		
			}
		
		if(dto.getState()!=null && !dto.getState().equals("")){
			params.put("state", dto.getState());
   		}
		
		if(dto.getVendorCode()!=null && !dto.getVendorCode().equals("")){
   			params.put("vendorCode",dto.getVendorCode());
   		}
		
		if(dto.getMailID()!=null && !dto.getMailID().equals("")){
   			params.put("mailID",dto.getMailID());
   		}
		
		if(dto.getStatus()!=null && !dto.getStatus().equals("")) {
   			params.put("status",dto.getStatus());
   		}
		return params;
	}



}

