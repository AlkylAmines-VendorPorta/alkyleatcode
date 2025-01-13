package com.novelerp.appcontext.service;

import com.novelerp.appcontext.dto.UserDetailsDto;
import com.novelerp.appcontext.entity.UserDetails;
import com.novelerp.core.service.CommonService;

public interface UserDetailsService extends CommonService<UserDetails, UserDetailsDto>{
	
	public UserDetailsDto getUserDetailsByUserId(Long userId);
	public UserDetailsDto createUserDetails(UserDetailsDto userDetailsDto);
	public UserDetailsDto updateUserDetails(UserDetailsDto userDetailsDto);
	/**
	 * Save user detail with Location
	 * @param userDetailDto
	 * @return
	 */
	public UserDetailsDto saveWithLocation(UserDetailsDto userDetailDto);
	
	/**
	 * update user details with location
	 * @param userDetailDto
	 * @return
	 */
	public UserDetailsDto updateWithLocation(UserDetailsDto userDetailDto);
    public UserDetailsDto saveCompanyContact(UserDetailsDto dto);
    public UserDetailsDto saveManagementDetails(UserDetailsDto dto);
}
