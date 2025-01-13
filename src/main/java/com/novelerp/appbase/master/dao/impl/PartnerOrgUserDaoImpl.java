package com.novelerp.appbase.master.dao.impl;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import com.novelerp.appbase.master.dao.PartnerOrgUserDao;
import com.novelerp.appbase.master.dto.PartnerOrgUserDto;
import com.novelerp.appbase.master.entity.PartnerOrgUser;
import com.novelerp.core.dao.impl.AbstractJpaDAO;
/**
 * 
 * @author Vivek Birdi
 */
@Repository
public class PartnerOrgUserDaoImpl extends AbstractJpaDAO<PartnerOrgUser, PartnerOrgUserDto> implements PartnerOrgUserDao{

	@PostConstruct
	private void init() {
		setClazz(PartnerOrgUser.class, PartnerOrgUserDto.class);
	}
	
	/**
	 * 
	 * @return - query string for partnerOrgUser
	 */
	public String getOrgUserByOrgQuery(){
		StringBuilder query =  new StringBuilder();
		query.append("SELECT u FROM PartnerOrgUser u")
		.append(" LEFT JOIN FETCH u.createdBy c")
		.append(" LEFT JOIN FETCH u.updatedBy up")
		.append(" LEFT JOIN FETCH u.partner p")
		.append(" LEFT JOIN FETCH u.partnerOrg p")
		.append(" LEFT JOIN FETCH u.userDetail ud")
		.append(" LEFT JOIN FETCH ud.designation udd")
		.append(" LEFT JOIN FETCH ud.createdBy udc")
		.append(" LEFT JOIN FETCH ud.updatedBy udu")
		.append(" LEFT JOIN FETCH ud.partner udp")
		.append(" LEFT JOIN FETCH ud.location ul")
		.append(" LEFT JOIN FETCH ul.createdBy ulc")
		.append(" LEFT JOIN FETCH ul.updatedBy ulu")
		.append(" LEFT JOIN FETCH ul.partner ulp")
		.append(" LEFT JOIN FETCH ul.district d")
		.append(" LEFT JOIN FETCH ul.region d")
		.append(" LEFT JOIN FETCH ul.country d")
		.append(" WHERE u.isActive='Y' AND u.partnerOrg.partnerOrgId = :partnerOrgId");
		return query.toString();
	}
}
