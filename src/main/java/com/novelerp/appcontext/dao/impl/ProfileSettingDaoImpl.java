package com.novelerp.appcontext.dao.impl;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import com.novelerp.appcontext.dao.ProfileSettingDao;
import com.novelerp.appcontext.dto.ProfileSettingDto;
import com.novelerp.appcontext.entity.ProfileSetting;
import com.novelerp.core.dao.impl.AbstractJpaDAO;

@Repository
public class ProfileSettingDaoImpl extends AbstractJpaDAO<ProfileSetting, ProfileSettingDto> implements ProfileSettingDao{

	@PostConstruct
	public void postConstruct() {
		setClazz(ProfileSetting.class, ProfileSettingDto.class);
	}
	
	public String getProfileSettingByPartnerId(){
		StringBuilder sb= new StringBuilder();
		sb.append(" SELECT ps FROM ProfileSetting ps ");
		sb.append(" LEFT JOIN FETCH ps.profileLogo pl ");
		sb.append(" LEFT JOIN FETCH ps.partner p ");
		sb.append(" WHERE p.bPartnerId=:partnerId ");
		return sb.toString();
	}
	
	public String getProfileSettingByUrlString(){
		StringBuilder sb= new StringBuilder();
		sb.append(" SELECT ps FROM ProfileSetting ps ");
		sb.append(" LEFT JOIN FETCH ps.profileLogo pl ");
		sb.append(" LEFT JOIN FETCH ps.partner p ");
		sb.append(" WHERE upper(ps.urlPattern)=upper(:partnerString) ");
		return sb.toString();
	}
	
}
