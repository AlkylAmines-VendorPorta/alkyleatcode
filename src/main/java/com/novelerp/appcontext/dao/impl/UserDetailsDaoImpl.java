package com.novelerp.appcontext.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.appcontext.dao.UserDetailsDao;
import com.novelerp.appcontext.dto.UserDetailsDto;
import com.novelerp.appcontext.entity.UserDetails;
import com.novelerp.core.dao.impl.AbstractJpaDAO;

@Repository
public class UserDetailsDaoImpl extends AbstractJpaDAO<UserDetails,UserDetailsDto> implements UserDetailsDao{

	Logger log=LoggerFactory.getLogger(UserDetailsDaoImpl.class);

	@PostConstruct
	public void setClazz(){
		setClazz(UserDetails.class,UserDetailsDto.class);
	}
	
	@Override
	@Transactional(propagation =Propagation.SUPPORTS)
	public List<UserDetails> findAll() {
		return execute(getJpqlQuery().toString(), null, UserDetails.class);		
	}
		
	public StringBuilder getJpqlQuery(){		
		StringBuilder jpql = new StringBuilder();
		jpql.append(" Select c from UserDetails c");
		return jpql;
	}
	
	private Map<String, Object> getParams(Long userID){
		Map<String, Object> params= new HashMap<>();
		params.put("userID", userID);
		return params;
	}
	
	private StringBuilder getQueryForUserUserDetailss(){		
		StringBuilder jpql = new StringBuilder();
		jpql.append(" Select r from UserDetails r ")
		.append(" LEFT JOIN r.createdBy  rc")
		.append(" LEFT JOIN r.updatedBy ru")
		.append(" INNER JOIN FETCH r.userUserDetailss ur")
		.append(" LEFT JOIN FETCH ur.createdBy urc" )
		.append(" LEFT JOIN FETCH ur.updatedBy uru")
		.append(" WHERE ur.user.userId = :userID");
		return jpql;
	}

	@Override
	public List<UserDetails> getUserUserDetailss(Long userID) {
		StringBuilder jpql =  getQueryForUserUserDetailss();
		return execute(jpql.toString(), getParams(userID), UserDetails.class);
	}	

	public String getUserDetailsQueryByBP(){
		StringBuilder query = new StringBuilder();
		query.append(" Select r from UserDetails r ")
		.append(" LEFT JOIN FETCH r.createdBy  rc")
		.append(" LEFT JOIN FETCH r.updatedBy ru")
		.append(" LEFT JOIN FETCH r.partner p")
		.append(" LEFT JOIN FETCH r.designation d")
		.append(" LEFT JOIN FETCH r.location l")
		.append(" WHERE r.partner.bPartnerId = :bPartnerId")
		.append(" AND r.userDetailType='COMPUSR' and r.isActive='Y'");

		return query.toString();
	}
	
	/**
	 * query for Director details
	 * @return String object
	 */
	public String getDirectorDetailsQuery(){
		StringBuilder query = new StringBuilder();
		query.append(" Select r from UserDetails r ")
		.append(" LEFT JOIN FETCH r.createdBy  rc")
		.append(" LEFT JOIN FETCH r.updatedBy ru")
		.append(" LEFT JOIN FETCH r.partner p")
		.append(" LEFT JOIN FETCH r.designation d")
		.append(" LEFT JOIN FETCH r.location l")
		.append(" LEFT JOIN FETCH l.partner lp")
		.append(" LEFT JOIN FETCH l.createdBy c")
		.append(" LEFT JOIN FETCH l.updatedBy u")
		.append(" LEFT JOIN FETCH l.country cn")
		.append(" LEFT JOIN FETCH l.region s")
		.append(" LEFT JOIN FETCH l.district d")
		.append(" WHERE r.partner.bPartnerId = :partnerId")
		.append(" AND r.userDetailType IN ('COMPDIR')")
		.append(" AND r.isActive='Y' ");

		return query.toString();
	}

	public String getUserDetailsForPaymentReceipt(){
		StringBuilder jpql=new StringBuilder(" select ud from UserDetails ud ")
		.append(" LEFT JOIN FETCH ud.partner p ")
		.append(" LEFT JOIN FETCH ud.location l ")
		.append(" LEFT JOIN FETCH l.country cn")
		.append(" LEFT JOIN FETCH l.region s")
		.append(" LEFT JOIN FETCH l.district d")
		/*.append(" LEFT JOIN FETCH ")*/
		.append(" where ud.userDetailsId=:userDetailsId ");
		return jpql.toString();
	}
	
	public String getOfficeLocationFromUserDetailsID(){
		StringBuilder jpql=new StringBuilder(" select ud from UserDetails ud ")
		.append(" LEFT JOIN FETCH ud.partner p ")
		.append(" LEFT JOIN FETCH ud.officeLocation l ")
		.append(" LEFT JOIN FETCH ud.locationType lt ")
		.append(" where ud.userDetailsId=:userDetailsId ");
		return jpql.toString();
	}
	public String getUserDetailsWithDesignation(){
		StringBuilder jpql=new StringBuilder(" select ud from UserDetails ud ")
		.append(" LEFT JOIN FETCH ud.designation d ")
		.append(" where ud.userDetailsId=:userDetailsId ");
		return jpql.toString();
	}
	
	public String getDirectorDetails(){
		StringBuilder sb = new StringBuilder()
				.append(" SELECT A FROM UserDetails A ")
				.append(" INNER JOIN FETCH A.partner B ")
				.append(" WHERE B.bPartnerId=:partnerId and A.userDetailType='"+AppBaseConstant.MANAGEMENT_USER+"' and A.isActive='Y'");
		return sb.toString();
	}
	public String getVendorUserByPartnerID(){
		StringBuilder jpql=new StringBuilder(" select A from UserDetails A ")
				.append(" INNER JOIN FETCH A.partner B ")
		.append(" WHERE B.bPartnerId=:partnerId and A.isReceiveEnquiry is not null ");
		return jpql.toString();
	}
	public String getVendorUserForPOActivity(){
		StringBuilder jpql=new StringBuilder(" select A from UserDetails A ")
				.append(" INNER JOIN FETCH A.partner B ")
		.append(" WHERE B.bPartnerId=:partnerId and A.isReceivePO='Y' and A.isActive='Y' ");
		return jpql.toString();
	}
	public String getVendorUserForAccActivity(){
		StringBuilder jpql=new StringBuilder(" select A from UserDetails A ")
				.append(" INNER JOIN FETCH A.partner B ")
		.append(" WHERE B.bPartnerId=:partnerId and A.isReceiveACInfo='Y' and A.isActive='Y' ");
		return jpql.toString();
	}
	public String getUserDetailsWithID(){
		StringBuilder jpql=new StringBuilder(" select ud from UserDetails ud ")
		.append(" where ud.userDetailsId=:userDetailsId ");
		return jpql.toString();
	}
}
