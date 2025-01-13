package com.novelerp.appcontext.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.novelerp.alkyl.dto.VendorCredentialReadDto;
import com.novelerp.appcontext.dto.UserDto;
import com.novelerp.appcontext.entity.User;
import com.novelerp.core.dao.CommonDao;

public interface UserDao extends CommonDao<User, UserDto> {
	
	//public ResponseDto createUser(UserDto userDto);
	    	
	public UserDto findUser(Long ID);
	public boolean checkValue(String value,Long userId);
	public List<User> getUser(String where, Map<String, Object> params, String orderBy);
	public User getDetailedUserById(Long userId);
	public int deleteUser(Date toDate);
	public List<Object[]> getUrlPatternsByRole();
	public List<User> getUserByEmailOrUserName(String userName, String isInvitedvendor);
	public boolean checkValue(String value, Long userId, String code);
	public List<Object> getTilesUrl(String value);
	 
	public boolean checkUserNameValue(String userName,Long userId);
	public List<Object> getVendorListByName(String name);
//	public List<Object> getVendorCredentailReportlist(VendorCredentialReadDto dto); 
	
	
	
	
	
}
