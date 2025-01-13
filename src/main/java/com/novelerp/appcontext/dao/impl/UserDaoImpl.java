package com.novelerp.appcontext.dao.impl;

import java.sql.ResultSet;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;

import org.apache.poi.util.SystemOutLogger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.novelerp.alkyl.dto.ASNReadDto;
import com.novelerp.alkyl.dto.VendorCredentialReadDto;
import com.novelerp.appbase.util.AppBaseConstant;
//import com.novelerp.appcontext.dao.UserDao;
import com.novelerp.appcontext.dao.UserDao;
import com.novelerp.appcontext.dto.UserDto;
import com.novelerp.appcontext.entity.User;
import com.novelerp.appcontext.util.ContextConstant;
import com.novelerp.commons.util.CommonUtil;
import com.novelerp.core.dao.impl.AbstractJpaDAO;

@Repository
public class UserDaoImpl extends AbstractJpaDAO<User, UserDto> implements UserDao {

	
	@PersistenceContext
	private EntityManager em;
	
	@PostConstruct
	public void setClazz() {
		setClazz(User.class, UserDto.class);
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public UserDto findUser(Long ID) {
		StringBuilder jpql = new StringBuilder(" Select NEW com.novelerp.eauction.dto.UserDto(c.userId,c.email) FROM ");
		jpql.append(User.CLASS_Name).append(" c WHERE c.userId = :id");
		TypedQuery<UserDto> q = getEntityManager().createQuery(jpql.toString(), UserDto.class);
		q.setParameter("id", ID);
		return q.getSingleResult();
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<User> find(String where, Map<String, Object> params, String orderBy) {
		StringBuilder jpql = getJpqlQuery();
		if (!CommonUtil.isStringEmpty(where)) {
			jpql.append(" WHERE ").append(where);
		}
		return execute(jpql.toString(), params, User.class);
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<User> getUser(String where, Map<String, Object> params, String orderBy) {
		StringBuilder jpql = getJpqlQuery();
		if (!CommonUtil.isStringEmpty(where)) {
			jpql.append(" WHERE ").append(where);
		}
		return execute(jpql.toString(), params, User.class);
	}

	public StringBuilder getJpqlQuery() {
		StringBuilder jpql = new StringBuilder();
		jpql.append(" Select c from User c").append(" LEFT JOIN FETCH c.createdBy")
				.append(" LEFT JOIN FETCH c.updatedBy").append(" LEFT JOIN FETCH c.userDetails ud")
				.append(" LEFT JOIN FETCH c.partner p").append(" LEFT JOIN FETCH p.createdBy")
				.append(" LEFT JOIN FETCH p.updatedBy");
		return jpql;
	}

	public String getInvitedUsers(){		
		StringBuilder jpql = new StringBuilder()
			.append(" Select A from User A ")
			.append(" INNER JOIN FETCH A.createdBy B ")
			.append(" INNER JOIN FETCH A.updatedBy C " )
			.append(" INNER JOIN FETCH A.userDetails D ")
			.append(" INNER JOIN FETCH B.userDetails F ")
			.append(" INNER JOIN FETCH A.partner E ")
		//	.append(" WHERE  A.isInvited='Y' and E.status='"+AppBaseConstant.DOCUMENT_STATUS_IN_PROGRESS+"'");
			.append(" WHERE  E.status='"+AppBaseConstant.DOCUMENT_STATUS_IN_PROGRESS+"'");
		return jpql.toString();
	}

	@Override
	public boolean checkValue(String value,Long userId)
	{
    	StringBuilder sb=new StringBuilder("select b from User b ");
		sb.append(" where b.email=:email  ");
		if(userId!=null)
		{
			sb.append(" and b.userId<>:userId");
		}

		Query q = em.createQuery(sb.toString(), User.class);
		q.setParameter("email", value);
		if(userId!=null)
		{
			q.setParameter("userId", userId);
		}
		if (q.getResultList().size() > 0) {
			return true;
		} else
			return false;
	}

	public String getQueryForUserByEmail() {
		StringBuilder query = new StringBuilder("select c from User c ");
		query.append(" where c.email=:email ");
		return query.toString();
	}
	
	public String getQueryForUserByEmailandSapCode() {
		StringBuilder query = new StringBuilder("select c from User c ");
		query.append(" where c.email=:email or c.userName=:vendorSapCode ");
		return query.toString();
	}

	public String getQueryForUserByName(){
		StringBuilder jpql = new StringBuilder()
				.append(" Select A from User A ")
				.append(" INNER JOIN FETCH A.createdBy B ")
				.append(" INNER JOIN FETCH A.updatedBy C " )
				.append(" INNER JOIN FETCH A.userDetails D ")
				.append(" INNER JOIN FETCH B.userDetails F ")
				.append(" INNER JOIN FETCH A.partner E ")
				.append(" WHERE (LOWER(E.name) like LOWER(:name) or LOWER(E.vendorSapCode) = LOWER(:name) ) and A.isInvited='Y' ");
			return jpql.toString();
	}
	
	
	public String getQueryForUserByVendorsapCode(){
		StringBuilder jpql = new StringBuilder()
				.append(" Select A from User A ")
				.append(" LEFT JOIN FETCH A.createdBy B ")
				.append(" LEFT JOIN FETCH A.updatedBy C " )
				.append(" LEFT JOIN FETCH A.userDetails D ")
				.append(" LEFT JOIN FETCH B.userDetails F ")
				.append(" INNER JOIN FETCH A.partner E ")
				.append(" WHERE (E.vendorSapCode =:vendorSapCode or A.email=:email)");
			return jpql.toString();
	}
	
	/*
	public String getVendorListByName(){
		StringBuilder jpql = new StringBuilder("select a.m_bpartner_id,a.vendor_sap_code,b.ad_user_id,b.email,f.name as district,g.name as region,b.is_invited from m_bpartner a")
				.append(" inner join ad_user b on (b.m_bpartner_id=a.m_bpartner_id)")
				.append("  LEFT JOIN FETCH m_bp_company_address h on(h.m_bpartner_id=a.m_bpartner_id) LEFT JOIN FETCH c_location e on(e.c_location_id=h.c_location_id ) LEFT JOIN FETCH c_district f on(f.c_district_id=e.c_district_id) LEFT JOIN FETCH c_region g on(g.c_region_id=e.c_region_id) ")
				.append(" WHERE (LOWER(a.name) like CONCAT('%',LOWER(:name),'%') or a.vendorSapCode = :name");
		return jpql.toString();
		
	}*/
	

	

	
	
	
	/*public String getVendorListByName(){
		StringBuilder query = new StringBuilder()
				.append("Select A from User A")
				.append(" LEFT JOIN FETCH A.userDetails ud")
				.append(" LEFT JOIN FETCH ud.partner B ")
				.append(" LEFT JOIN FETCH ud.location l")
				.append(" LEFT JOIN FETCH l.District d")
				.append(" LEFT JOIN FETCH l.region r")

		.append(" WHERE (LOWER(B.name) like CONCAT('%',LOWER(:name),'%') or B.vendorSapCode = :name or LOWER(A.email) like CONCAT('%',LOWER(:name),'%'))")
		.append(" AND A.isActive = 'Y'");
	//	.append(" AND B.bPartnerId = bPartnerId");

		return query.toString();
		}*/
	
	
		public String getQueryForUserWithPartnerByEmail() {
		StringBuilder query = new StringBuilder("select c from User c ");
		query.append(" LEFT JOIN FETCH c.partner bp");
		query.append(" LEFT JOIN FETCH c.userDetails ud");
		query.append(" where c.email=:email ");
		return query.toString();
	}

	public User getDetailedUserById(Long userId) {
		StringBuilder sb = new StringBuilder("Select u from User u");
		sb.append(" LEFT JOIN FETCH u.userDetails ud");
		sb.append(" LEFT JOIN FETCH ud.location l");
		sb.append(" LEFT JOIN FETCH ud.officeLocation");
		sb.append(" LEFT JOIN FETCH ud.locationType lt");
		sb.append(" where u.userId=:userId");
		Query q = em.createQuery(sb.toString(), User.class);
		q.setParameter("userId", userId);
		User user = (User) q.getSingleResult();
		return user;
	}

	public String getQueryForInternalUser() {
		StringBuilder jpql = new StringBuilder("Select u from User u ");
		jpql.append(" INNER JOIN FETCH u.userDetails ud ");
		jpql.append(" INNER JOIN FETCH ud.designation d ");
		jpql.append(" INNER JOIN FETCH UserRoles ur ON (u.userId=ur.user.userId) ");
		/* jpql.append(" INNER JOIN FETCH ud.location loc  "); */
		jpql.append(" INNER JOIN FETCH ud.officeLocation ofl  ");
		jpql.append(" INNER JOIN TAHDR t ON (t.officeLocation.officeLocationId = ofl.officeLocationId) ");
		jpql.append(" where ur.role.value=:roleValue and  t.tahdrId=:tenderId ORDER BY u.name");
		return jpql.toString();
	}

	public String getQueryForParticipantUser() {
		StringBuilder jpql = new StringBuilder("Select u from User u ");
		jpql.append(" INNER JOIN FETCH u.userDetails ud ");
		jpql.append(" INNER JOIN FETCH ud.designation d ");
		jpql.append(" INNER JOIN FETCH UserRoles ur ON (u.userId=ur.user.userId) ");
		/* jpql.append(" INNER JOIN FETCH ud.location loc  "); */
		jpql.append(" INNER JOIN FETCH ud.officeLocation ofl  ");
		jpql.append(" INNER JOIN TAHDR t ON (t.officeLocation.officeLocationId = ofl.officeLocationId) ");
		jpql.append(" where ur.role.value IN ('" + AppBaseConstant.ROLE_AUDITOR_USER + "','"
				+ AppBaseConstant.ROLE_FINANCE_ADMIN + "','" + AppBaseConstant.ROLE_FINANCE_OPERATOR + "') "
				+ "and  t.tahdrId=:tenderId ORDER BY u.name");
		return jpql.toString();
	}

	public String getQueryForUserByPartnerId() {
		StringBuilder jpql = new StringBuilder("Select c from User c ");
		jpql.append(" LEFT JOIN FETCH c.partner p ");
		jpql.append(" LEFT JOIN FETCH c.userDetails ud");
		jpql.append(" LEFT JOIN FETCH c.createdBy cb ");
		jpql.append(" WHERE p.bPartnerId=:bPartnerId");
		return jpql.toString();
	}

	public String getBidderMailListByTahdrId() {
		StringBuilder jpql = new StringBuilder();
		jpql.append(" SELECT u FROM User u WHERE u.partner.bPartnerId in (Select b.partner.bPartnerId from Bidder b ");
		jpql.append(" WHERE b.tahdr.tahdrId=:tahdrId) ");
		return jpql.toString();
	}

	public String getQueryForInvitedUserToSendMail() {
		StringBuilder jpql = new StringBuilder("Select c from User c ");
		jpql.append(" LEFT JOIN FETCH c.partner p ");
		jpql.append(" WHERE p.bPartnerId = :bPartnerId");
		return jpql.toString();
	}

	public String getQueryForUserByUserId() {
		StringBuilder jpql = new StringBuilder("Select c from User c ");
		jpql.append(" LEFT JOIN FETCH c.partner p ");
		jpql.append(" WHERE c.userId =:userId");
		return jpql.toString();
	}

	public String getUserListfromLocation() {
		StringBuilder jpql = new StringBuilder("Select u from User u ");
		jpql.append(" INNER JOIN FETCH u.userDetails ud");
		jpql.append(" INNER JOIN FETCH ud.location l");
		jpql.append(" INNER JOIN FETCH ud.locationType lt");
		jpql.append(" where ud.locationTypeRef=:locationType and u.userId!=:userId and u.partner=1 ORDER BY u.email");
		return jpql.toString();
	}

	public String getUserQueryForInvitation() {
		StringBuilder jpql = new StringBuilder("Select c from User c ");
		jpql.append(" LEFT JOIN FETCH c.partner p ");
		jpql.append(" LEFT JOIN FETCH c.userDetails ud ");
		jpql.append(" WHERE p.panNumber=:PanNo ");
		return jpql.toString();
	}

	public String getQueryForAllPartnerRA() {

		StringBuilder jpql = new StringBuilder("SELECT ad from User ad ");
		jpql.append(" LEFT JOIN FETCH ad.partner bp ");
		jpql.append(" LEFT JOIN FETCH ad.userDetails ud ");
		jpql.append(" WHERE bp.isContractor =:isContractor AND bp.isCustomer =:isCustomer");

		jpql.append(" AND bp.registrationType ='PARTICIPANT'");

		return jpql.toString();
	}

	public String getQueryForEligiblePartnersByTahdrId() {
		StringBuilder jpql = new StringBuilder("SELECT U from User U ");
		jpql.append(" LEFT JOIN FETCH U.partner bp ");
		jpql.append(" LEFT JOIN FETCH U.userDetails ud ");
		jpql.append(
				" INNER JOIN MBPartnerInvitation RP On (RP.requestedPartner.bPartnerId=U.partner.bPartnerId AND RP.isInvitationApproved='Y') ");
		jpql.append(
				" WHERE U.userId NOT IN (SELECT U1.userId FROM User U1 INNER JOIN MAuctionParticipantMap ABM ON  (ABM.bPartner.bPartnerId=U1.partner.bPartnerId ");
		jpql.append(" AND ABM.tahdr.tahdrId=:tahdrId)) AND RP.partner.bPartnerId=:partnerId ");
		jpql.append(" ORDER BY U.created DESC ");
		return jpql.toString();
	}

	/*
	 * public String getQueryForAllPartnerFA() {
	 * 
	 * StringBuilder jpql=new StringBuilder("SELECT ad from User ad ");
	 * jpql.append(" LEFT JOIN FETCH ad.partner bp ");
	 * jpql.append(" LEFT JOIN FETCH ad.userDetails ud ");
	 * jpql.append("WHERE bp.isCustomer ='Y'");
	 * jpql.append("AND bp.registrationType ='PARTICIPANT'"); return
	 * jpql.toString(); }
	 */

	public String getuserByBPartner() {
		StringBuilder jpql = new StringBuilder();
		jpql.append(" Select u from User u ").append(" LEFT JOIN FETCH u.userDetails ud ")
				.append(" WHERE u.partner.bPartnerId = :BPartnerId").append(" AND u.isActive = 'Y'");

		return jpql.toString();
	}

	public String getuserById() {
		StringBuilder jpql = new StringBuilder();
		jpql.append(" Select u from User u ").append(" LEFT JOIN FETCH u.userDetails ud ")
				.append(" WHERE u.userId = :UserID").append(" AND u.isActive = 'Y'");

		return jpql.toString();
	}

	@Override
	public int deleteUser(Date toDate) {
		StringBuilder jpql = new StringBuilder(" Update User u set u.isUserDeleted='Y' ");
		jpql.append(" where Trunc(u.created)<=:toDate ");
		jpql.append(" AND (u.isUserDeleted<>'Y' OR u.isUserDeleted IS NULL) ");
		jpql.append(" AND u.userId IN ( Select usr.userId From User usr INNER JOIN usr.partner p where p.status IN('"
				+ AppBaseConstant.PARTNER_STATUS_DRAFTED + "')  ");
		jpql.append(" AND (p.value<>:code OR p.value IS NULL)) ");
		Query query = getEntityManager().createQuery(jpql.toString());
		query.setParameter("toDate", toDate, TemporalType.DATE);
		query.setParameter("code", ContextConstant.USER_TYPE_INTERNAL);
		int count = query.executeUpdate();
		return count;
	}

	public String getUserForProfileUpdateReminder() {
		StringBuilder jpql = new StringBuilder(" select u from  User u ");
		jpql.append(" LEFT JOIN FETCH u.partner ptr ");
		jpql.append(" WHERE (u.isUserDeleted<>'Y' OR u.isUserDeleted IS NULL) ");
		jpql.append(" AND u.userId IN ( Select usr.userId From User usr INNER JOIN usr.partner p where p.status IN ('"
				+ AppBaseConstant.PARTNER_STATUS_DRAFTED + "')  ");
		jpql.append(" AND (p.value<>:code OR p.value IS NULL)) ");
		return jpql.toString();
	}

	public String getQueryForUserWithPartner() {
		StringBuilder jpql = new StringBuilder(" select u from  User u ").append(" INNER JOIN FETCH u.partner p ");
		return jpql.toString();
	}

	public String getUserForPartnerRenewalReminder() {
		StringBuilder jpql = new StringBuilder(getQueryForUserWithPartner());
		jpql.append(" LEFT JOIN FETCH u.userDetails ud ");
		jpql.append(" where Trunc(p.validTo)=:date ");

		return jpql.toString();
	}

	public String getUserByRoleCode() {
		StringBuilder jpql = new StringBuilder(" select u from User u ").append(" INNER JOIN FETCH u.userDetails ud ")
				.append(" INNER JOIN FETCH u.partner p ").append(" INNER JOIN FETCH ud.designation des ")
				.append(" INNER JOIN FETCH ud.location l ").append(" LEFT JOIN FETCH l.country cn")
				.append(" LEFT JOIN FETCH l.region s").append(" LEFT JOIN FETCH l.district d")
				.append(" where u.userId IN (SELECT ur.user.userId From UserRoles ur LEFT JOIN ur.role r WHERE r.value=:role ) ");
		return jpql.toString();
	}

	public String getUserRegisteredListByRole() {
		StringBuilder query = new StringBuilder();
		query.append("Select u from User u").append(" INNER JOIN FETCH u.partner bp")
				.append(" INNER JOIN UserRoles ur on(u.userId=ur.user.userId) ").append(" INNER JOIN ur.role r")
				.append(" WHERE bp.isApproved=:isApproved").append(" AND r.value = 'VENADM'")
				.append(" AND (bp.isContractor = 'Y' OR bp.isManufacturer = 'Y' OR bp.isTrader = 'Y' )");
		return query.toString();
	}

	public String getCustomerRegisteredListByRole() {
		StringBuilder query = new StringBuilder();
		query.append("Select u from User u").append(" INNER JOIN FETCH u.partner bp")
				.append(" INNER JOIN UserRoles ur on(u.userId=ur.user.userId) ").append(" INNER JOIN ur.role r")
				.append(" WHERE bp.isApproved=:isApproved").append(" AND r.value = 'VENADM'")
				.append(" AND bp.isCustomer = 'Y'");
		return query.toString();
	}

	@Override
	public List<Object[]> getUrlPatternsByRole() {
		StringBuilder sb = new StringBuilder();
		sb.append("(SELECT MT.M_TILE_ID, MT.name,  LISTAGG(rol.NAME,',') WITHIN GROUP(order by rol.NAME ) as role ");
		sb.append("FROM m_tile tile ");
		sb.append("LEFT JOIN AD_ROLE_ACCESS_MASTER ascm ON (tile.M_TILE_ID = ascm.M_TILE_ID)  ");
		sb.append("LEFT JOIN AD_ROLE rol ON (rol.AD_ROLE_ID = ascm.ad_role_id) ");
		sb.append("INNER JOIN M_TILE MT ON (MT.M_TILE_ID=tile.PARENT_ID) ");
		sb.append("group by MT.M_TILE_ID, MT.name) ");
		sb.append("UNION ALL  ");
		sb.append(
				"(SELECT tile.M_TILE_ID, tile.name,  LISTAGG(rol.NAME,',') WITHIN GROUP(order by rol.NAME ) as role ");
		sb.append("FROM m_tile tile ");
		sb.append("LEFT JOIN AD_ROLE_ACCESS_MASTER ascm ON (tile.M_TILE_ID = ascm.M_TILE_ID)  ");
		sb.append("LEFT JOIN AD_ROLE rol ON (rol.AD_ROLE_ID = ascm.ad_role_id) ");
		sb.append("where tile.LEVEL_NO <> 0 ");
		sb.append("group by tile.M_TILE_ID, tile.name) ");
		Query query = getEntityManager().createNativeQuery(sb.toString());
		List<Object[]> resultSet = query.getResultList();
		return resultSet;
	}

	public String getQueryForUserListForLoginGenerator() {
		StringBuilder query = new StringBuilder("select c from User c ");
		query.append(" INNER JOIN FETCH c.partner bp");
		query.append(" INNER JOIN FETCH c.userDetails ud");
		query.append(" where c.isLoginCreated='N' and ud.userDetailType='" + AppBaseConstant.COMPANY_USER + "'");
		return query.toString();
	}
	
	@Override
	public boolean checkValue(String value,Long userId,String code)
	{
    	StringBuilder sb=new StringBuilder("select b from User b ");
		sb.append(" where ( b.email=:email or b.userName=:userName ) ");
		if(userId!=null)
		{
			sb.append(" and b.userId<>:userId");
		}

		Query q=em.createQuery( sb.toString(),User.class);
		q.setParameter("email", value);
		q.setParameter("userName", code);
		if(userId!=null)
		{
			q.setParameter("userId", userId);
		}
		if(q.getResultList().size()>0)
		{
			return true;
		}
		else
		return false;
	}

	public String getQueryForUserListForVendorsLoginGenerator() {
		StringBuilder query = new StringBuilder("select c from User c ");
		query.append(" INNER JOIN FETCH c.partner bp");
		query.append(" INNER JOIN FETCH c.userDetails ud");
		query.append(" where (c.isLoginCreated='N' or c.isLoginCreated is null) and ud.userDetailType='"
				+ AppBaseConstant.COMPANY_USER + "'");
		query.append(" and (c.isInvited='N' or c.isInvited is null) and c.userId IN (:listOfUserId)");
		return query.toString();
	}

	@Override
	public List<User> getUserByEmailOrUserName(String userName, String isInvitedvendor) {
		StringBuilder jpqlQuery = new StringBuilder(" select Distinct(A) from User A ");
		jpqlQuery.append(" INNER JOIN FETCH A.userDetails B ");
		jpqlQuery.append(" INNER JOIN FETCH A.partner C ");
		jpqlQuery.append(" INNER JOIN  UserRoles D on D.user.userId=A.userId ");
		jpqlQuery.append(" INNER JOIN  D.role E  ").append(" where A.userId<> null  ");

		if (!userName.isEmpty()) {
			jpqlQuery.append(
					" and (LOWER(A.userName) LIKE :usernameOrEmail or LOWER(A.email) like :usernameOrEmail) ");
		}
		if ("Y".equals(isInvitedvendor)) {
			jpqlQuery.append(" and A.isInvited='Y' ");
		} else if ("N".equals(isInvitedvendor)) {
			jpqlQuery.append(" and (A.isInvited='N' OR A.isInvited is null) ");
		} else {
			jpqlQuery.append("");
		}

		if ("internaluser".equals(isInvitedvendor)) {
			jpqlQuery.append(" and E.value not IN ('" + AppBaseConstant.ROLE_PARTNER_USER + "','"
					+ AppBaseConstant.ROLE_VENDOR_ADMIN + "'" + ")");
		} else {
			jpqlQuery.append(" and E.value IN ('" + AppBaseConstant.ROLE_PARTNER_USER + "','"
					+ AppBaseConstant.ROLE_VENDOR_ADMIN + "'" + ")");

		}

		Query query = getEntityManager().createQuery(jpqlQuery.toString());

		if (!userName.isEmpty()) {
			query.setParameter("usernameOrEmail", userName);

		}

		@SuppressWarnings("unchecked")
		List<User> resultSet = query.getResultList();
		return resultSet;
	}

	public String updateUserEmailOrUserName() {
		StringBuilder query = new StringBuilder("select c from User c ");
		query.append(" INNER JOIN FETCH c.partner bp");
		query.append(" INNER JOIN FETCH c.userDetails ud");
		query.append(" where (c.email=:email OR c.userName=:username) ");
		query.append(" and c.userId<> :userId ");
		return query.toString();
	}
	
	public String getUserBYUserName() {
		StringBuilder query = new StringBuilder("select c from User c ");
		query.append(" where c.userName=:username ");
		return query.toString();
	}
	
	public String getUserBYUserNameBuyer() {
		StringBuilder query = new StringBuilder("select c from User c ");
//		query.append(" where c.name=:username ");
		query.append(" where UPPER(c.name)=:username ");
		return query.toString();
	}
	
	public String getUserListByRole() {
		StringBuilder query = new StringBuilder();
		query.append("Select u from User u")
		.append(" INNER JOIN UserRoles ur on(u.userId=ur.user.userId) ")
		.append(" INNER JOIN ur.role r")
		.append(" WHERE r.value=:value");
		return query.toString();
	}
	
	public String getUserDetailsbyEmailOrUserName() {
		StringBuilder query = new StringBuilder("select c from User c ");
		query.append(" INNER JOIN FETCH c.partner bp");
		query.append(" LEFT JOIN FETCH c.userDetails ud");
		query.append(" LEFT JOIN FETCH ud.state s");
		query.append(" LEFT JOIN FETCH ud.district d");
		query.append(" where (UPPER(c.email)LIKE:username OR UPPER(c.userName)LIKE:username) ");
		query.append(" and c.userId<> :userId ");
		return query.toString();
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Object> getTilesUrl(String value) {
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(" SELECT C.value FROM ad_role_access_master A ");
		sb.append(" INNER JOIN ad_role B ON (B.ad_role_id=A.ad_role_id) ");
		sb.append(" INNER JOIN m_tile C ON (C.m_tile_id=A.m_tile_id) ");
		sb.append(" WHERE B.value=:value AND C.isActive='Y' ORDER BY C.sequence_no ASC ");
		
		Query query = getEntityManager().createNativeQuery(sb.toString());
		query.setParameter("value", value);
		List<Object> resultSet = query.getResultList();

		return resultSet;
	}

	@Override
	public boolean checkUserNameValue(String userName,Long userId) {
		// TODO Auto-generated method stub
		StringBuilder sb=new StringBuilder("select b from User b ");
		sb.append(" where b.userName=:userName  ");
		if(userId!=null)
		{
			sb.append(" and b.userId<>:userId");
		}
		Query q = em.createQuery(sb.toString(), User.class);
		q.setParameter("userName", userName);
		if(userId!=null)
		{
			q.setParameter("userId", userId);
		}
		if (q.getResultList().size() > 0) {
			return true;
		} else
			return false;
	}
	public String getQueryForUserListForVendorsLoginGeneratorNew() {
		StringBuilder query = new StringBuilder("select c from User c ");
		query.append(" INNER JOIN FETCH c.partner bp");
		query.append(" INNER JOIN FETCH c.userDetails ud");
		query.append(" Where c.userId IN (:listOfUserId)");
		return query.toString();
	}
	public String getQueryForUserWithPartnerByEmailANDUserID() {
		StringBuilder query = new StringBuilder("select c from User c ");
		query.append(" LEFT JOIN FETCH c.partner bp");
		query.append(" LEFT JOIN FETCH c.userDetails ud");
		query.append(" where c.email=:email or c.userName=:email ");
		return query.toString();
	}
	public String getUserByPartnerId() {
		StringBuilder jpql = new StringBuilder("Select c from User c ");
		jpql.append(" INNER JOIN FETCH c.partner p ");
		jpql.append(" INNER JOIN FETCH c.userDetails ud");
		jpql.append(" WHERE c.partner.bPartnerId=:bPartnerId ");
		return jpql.toString();
	}
	
	public String getUserByRoleAndPlant() {
		StringBuilder jpql = new StringBuilder("Select c from User c ");
		jpql.append(" INNER JOIN UserRoles ur ON (u.userId=c.user.userId) ");
		jpql.append(" INNER JOIN FETCH c.userDetails ud");
		jpql.append(" WHERE ur.role.value=:roleValue and ud.plant=:plant ");
		return jpql.toString();
	}
	public String getVendorForProfile(){		
		StringBuilder jpql = new StringBuilder()
			.append(" Select distinct(A) from User A ")
			.append(" INNER JOIN FETCH A.createdBy B ")
			.append(" INNER JOIN FETCH A.updatedBy C " )
			.append(" INNER JOIN FETCH A.userDetails D ")
			.append(" INNER JOIN FETCH B.userDetails F ")
			.append(" INNER JOIN FETCH A.partner E ")
			.append(" WHERE (LOWER(E.name) like LOWER(:name) or A.userName like :name or LOWER(A.email) like LOWER(:name) )  ");
		return jpql.toString();
	}
	public String getAllInternalUser(){		
		StringBuilder jpql = new StringBuilder()
			.append(" Select distinct(A) from User A ")
			.append(" WHERE A.isEmailLogin='N' ");
		return jpql.toString();
	}
	public String getUserbyEmail(){		
		StringBuilder jpql = new StringBuilder()
			.append(" select c from User c ")
			.append(" INNER JOIN FETCH c.partner bp ")
			.append("  where (LOWER(c.email) =LOWER(:username)) or (c.userName =:username) ");
		return jpql.toString();
	}
	
	   public List<Object> getVendorListByName(String name){
			StringBuilder query = new StringBuilder()
					//.append("select Distinct(b.name) as vendorname, b.vendor_sap_code as vendorcode, u.email as useremail, d.name as districtname, r.name as regionname, b.m_bpartner_id as bPartnerId, u.ad_user_id as userId from ad_user as u left join m_bpartner as b on u.m_bpartner_id = b.m_bpartner_id left join c_location as l on b.m_bpartner_id=l.m_bpartner_id left join c_district as d on l.c_district_id = d.c_district_id left join c_region as r on l.c_region_id = r.c_region_id ")
					.append("select Distinct(b.name) as vendorname, b.vendor_sap_code as vendorcode, u.email as useremail, d.name as districtname, r.name as regionname, b.m_bpartner_id as bPartnerId, u.ad_user_id as userId from ad_user as u left join m_bpartner as b on u.m_bpartner_id = b.m_bpartner_id left join c_location as l on b.m_bpartner_id=l.m_bpartner_id left join m_bp_company_address as ca on ca.c_location_id=l.c_location_id left join c_district as d on l.c_district_id = d.c_district_id left join c_region as r on l.c_region_id = r.c_region_id ")
					.append(" WHERE (LOWER(b.name) like '%"+name.toLowerCase()+"%'  or b.vendor_sap_code = '"+name+"' or LOWER(u.email) like '%"+name.toLowerCase()+"%') AND b.isActive='Y' AND ca.is_primary_account='Y'");
//			        .append(" WHERE (LOWER(b.name) like '%"+name.toLowerCase()+"%'  or b.vendor_sap_code = '"+name+"' or LOWER(u.email) like '%"+name.toLowerCase()+"%') AND u.isActive='Y' AND ca.is_primary_account='Y'");
			         Query query1 = getEntityManager().createNativeQuery(query.toString());
			         List<Object> resultSet = query1.getResultList();
                    return resultSet;
			
			}
	   
	   
	   /*public List<Object> getVendorCredentailReportlist(VendorCredentialReadDto dto) {
			StringBuilder jpql = new StringBuilder("select A.city as cityname, d.name as districtname from c_location A left join c_district as d on (A.c_district_id = d.c_district_id) ");

			
			String where =  getWhereClause(dto);
			jpql.append(where);
		//	System.out.println(jpql.toString());
			Query query1 = getEntityManager().createNativeQuery(jpql.toString());
		//	System.out.println(query1.toString());
		//	query1.setParameter("District", dto.getDistrict());
		//	return jpql.toString();
			List<Object> resultSet = query1.getResultList();
			
			return resultSet;
		}
		private String getWhereClause(VendorCredentialReadDto dto){
			
		//	System.out.println(dto.toString());
	   		StringBuilder where = new StringBuilder();
	   		where.append(" WHERE A.isactive='Y'");
	   		//where.append(" (WHERE B.vendor_sap_code =: VendorCode) ");
	   		
	   		if(dto.getDistrict()!=null){
	   			where.append(" AND (d.name like :District)");
	   		}
	   		return where.toString();

		}
*/
	   public String getUserByPartner() {
			StringBuilder jpql = new StringBuilder(" select distinct(u) from  User u ")
					.append(" INNER JOIN FETCH u.partner p ")
					.append("WHERE u.partner.bPartnerId=:bPartnerId");
			return jpql.toString();
		}
	   
	   public String getInternalUserById() {
			StringBuilder jpql = new StringBuilder(" select u from  User u ")					
					.append("WHERE u.userId=1");
			return jpql.toString();
		}
	
}
