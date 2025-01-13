package com.novelerp.appcontext.dao;

import java.util.List;

import com.novelerp.appcontext.dto.UserDetailsDto;
import com.novelerp.appcontext.entity.UserDetails;
import com.novelerp.core.dao.CommonDao;

public interface UserDetailsDao extends CommonDao<UserDetails,UserDetailsDto>{

	public List<UserDetails> getUserUserDetailss(Long userID);
}
