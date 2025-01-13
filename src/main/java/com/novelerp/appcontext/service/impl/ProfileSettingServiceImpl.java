package com.novelerp.appcontext.service.impl;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.novelerp.appcontext.dao.ProfileSettingDao;
import com.novelerp.appcontext.dto.ProfileSettingDto;
import com.novelerp.appcontext.entity.ProfileSetting;
import com.novelerp.appcontext.service.ProfileSettingService;

@Service
public class ProfileSettingServiceImpl extends AbstractContextServiceImpl<ProfileSetting, ProfileSettingDto> implements ProfileSettingService{

    private Logger Log =  LoggerFactory.getLogger(ProfileSettingServiceImpl.class);
	
	@Autowired
	private ProfileSettingDao profileSettingDao;
	@PostConstruct
	private void init() {
		super.init(ProfileSettingServiceImpl.class, profileSettingDao, ProfileSetting.class, ProfileSettingDto.class);
		setByPassProxy(true);
	}
}
