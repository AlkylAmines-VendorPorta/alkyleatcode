package com.novelerp.appcontext.dao.impl;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.appcontext.dao.UserRolesDao;
import com.novelerp.appcontext.dto.BPartnerDto;
import com.novelerp.appcontext.dto.UserRolesDto;
import com.novelerp.appcontext.entity.UserRoles;
import com.novelerp.core.dao.impl.AbstractJpaDAO;

/**
 * 
 * @author Vivek Birdi
 *
 */
@Repository
public class UserRolesDaoImpl extends AbstractJpaDAO<UserRoles,UserRolesDto> implements UserRolesDao {

	@PersistenceContext
	private EntityManager em;
	
	
	
		@PostConstruct
		public void init(){
			setClazz(UserRoles.class, UserRolesDto.class);
		}

		@SuppressWarnings("unchecked")
		@Override
		public List<UserRoles> getUserRolesList(String locationType,String officeLocation,BPartnerDto dtos) {
			
			StringBuilder sb=new StringBuilder("SELECT DISTINCT ur from UserRoles ur ");
			sb.append(" LEFT JOIN FETCH ur.user u");
			sb.append(" LEFT JOIN FETCH u.userDetails ud");
			sb.append(" LEFT JOIN FETCH ud.designation d");
			sb.append(" LEFT JOIN FETCH ud.location l");
			sb.append(" LEFT JOIN FETCH ud.locationType lt");
			sb.append(" LEFT JOIN FETCH ud.officeLocation ofl");
			sb.append(" LEFT JOIN FETCH ud.approverUser app");
			sb.append(" LEFT JOIN FETCH ur.role r ");
			/*sb.append(" where ur.isDefault='Y' and ur.role.value NOT IN ('"+AppBaseConstant.ROLE_PARTNER_ADMIN+"','"+AppBaseConstant.ROLE_SYS_ADMIN+"') ");*/
			sb.append(" where ur.isDefault='Y' and ur.role.value NOT IN ('"+AppBaseConstant.ROLE_SYS_ADMIN+"') ");
			if(dtos!=null){
				sb.append("AND u.partner.bPartnerId=:partnerId");
			}
			if(locationType!=null && officeLocation!=null){
				sb.append(" AND lt.name=:locationType AND ofl.name=:officeLocation AND r.isAdmin='N'");
			}
			Query q=em.createQuery(sb.toString(),UserRoles.class);
			if(locationType!=null && officeLocation!=null){
				q.setParameter("locationType", locationType);
				q.setParameter("officeLocation", officeLocation);
			}
			if(dtos!=null){
				q.setParameter("partnerId",dtos.getbPartnerId());
			}
			List<UserRoles> userRolesList= q.getResultList();
			return userRolesList;
		}
		
		public String getInternalUserListQuery(){
			StringBuilder sb=new StringBuilder("SELECT DISTINCT ur from UserRoles ur ");
			sb.append(" LEFT JOIN FETCH ur.user u");
			sb.append(" LEFT JOIN FETCH u.userDetails ud");
			sb.append(" LEFT JOIN FETCH ud.designation d");
			sb.append(" LEFT JOIN FETCH ud.location l");
			sb.append(" LEFT JOIN FETCH ud.locationType lt");
			sb.append(" LEFT JOIN FETCH ud.officeLocation ofl");
			sb.append(" LEFT JOIN FETCH ur.role r where r.value<>'"+AppBaseConstant.ROLE_SYS_ADMIN+"' ");
			sb.append(" AND r.value NOT IN ('"+AppBaseConstant.ROLE_VENDOR_ADMIN+"','"+AppBaseConstant.ROLE_PARTNER_USER+"') ");
			return sb.toString();
		}
		
		@SuppressWarnings("unchecked")
		@Override
		public List<UserRoles> getUserRolesByUserId(Long userId) {
			StringBuilder sb=new StringBuilder("select ur from UserRoles ur ");
			sb.append(" LEFT JOIN FETCH ur.user u");
			sb.append(" LEFT JOIN FETCH u.userDetails ud");
			sb.append(" LEFT JOIN FETCH ud.designation d");
			sb.append(" LEFT JOIN FETCH ud.location l");
			sb.append(" LEFT JOIN FETCH ud.locationType lt");
			sb.append(" LEFT JOIN FETCH ud.officeLocation ofl");
			sb.append(" LEFT JOIN FETCH ur.role r where u.userId=:userId");
			Query q=em.createQuery(sb.toString(),UserRoles.class);
			q.setParameter("userId", userId);
			List<UserRoles> userRolesList= q.getResultList();
			return userRolesList;
		}
		
		@SuppressWarnings("unchecked")
		@Override
		public List<UserRoles> searchUserRoles(String litral) {
			StringBuilder sb=new StringBuilder("select ur from UserRoles ur ");
			sb.append(" LEFT JOIN FETCH ur.user u");
			sb.append(" LEFT JOIN FETCH u.userDetails ud");
			sb.append(" LEFT JOIN FETCH ud.designation d");
			sb.append(" LEFT JOIN FETCH ud.location l");
			sb.append(" LEFT JOIN FETCH ud.locationType lt");
			sb.append(" LEFT JOIN FETCH ur.role r where upper(u.name) like upper(:literal)");
			Query q=em.createQuery(sb.toString(),UserRoles.class);
			q.setParameter("literal", "%"+litral+"%");
			List<UserRoles> userRolesList= q.getResultList();
			return userRolesList;
		}

		public String getQueryForUserRoleByPartnerId(){
			StringBuilder jpql=new StringBuilder("select ur from UserRoles ur ");
			jpql.append(" INNER JOIN FETCH ur.user u");
			jpql.append(" where  u.partner.bPartnerId=:bpartnerId");
			System.out.println("getQueryForUserRoleByPartnerId" + jpql.toString());
			return jpql.toString();
			
		}
		
/*		@Override
		public List<UserRoles> findAll(String where, String orderBy) {

		return super.findAll(where, orderBy);
		}
		
		private StringBuilder getJpql(){
			StringBuilder jpql =  new StringBuilder();
			jpql.append(" Select ")
			return jpql;
		}
*/
		/*public String getUserRegisteredListByRole(){
			StringBuilder query =  new StringBuilder();
			query.append("SELECT ur FROM UserRoles ur")
			.append(" INNER JOIN FETCH ur.user user")
			.append(" INNER JOIN FETCH user.userDetails ud")
			.append(" INNER JOIN FETCH user.partner p")
			.append(" INNER JOIN FETCH ur.role r")
			.append(" WHERE p.isApproved = :isApproved")
			.append(" AND r.value = 'VENADM'")
			.append(" AND (p.isContractor = 'Y' OR p.isManufacturer = 'Y' OR p.isTrader = 'Y')");
			return query.toString();
		}*/
		
		
		
		/*public String getCustomerRegisteredListByRole(){
			StringBuilder query =  new StringBuilder();
			query.append("SELECT ur FROM UserRoles ur")
			.append(" INNER JOIN FETCH ur.user user")
			.append(" INNER JOIN FETCH user.userDetails ud")
			.append(" INNER JOIN FETCH user.partner p")
			.append(" INNER JOIN FETCH ur.role r")
			.append(" WHERE p.isApproved = :isApproved")
			.append(" AND r.value = 'VENADM'")
			.append(" AND p.isCustomer = 'Y'");
			return query.toString();
		}*/
		
		public String getUserRolesListByUserId() {
			StringBuilder sb=new StringBuilder("select ur from UserRoles ur ");
			sb.append(" LEFT JOIN FETCH ur.user u");
			sb.append(" LEFT JOIN FETCH ur.role r where u.userId=:userId");
			return sb.toString();
		}
		
}
