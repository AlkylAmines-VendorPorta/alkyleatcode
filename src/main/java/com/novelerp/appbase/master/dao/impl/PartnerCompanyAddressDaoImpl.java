package com.novelerp.appbase.master.dao.impl;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.novelerp.alkyl.dto.VendorCredentialReadDto;
import com.novelerp.appbase.master.dao.PartnerCompanyAddressDao;
import com.novelerp.appbase.master.dto.PartnerCompanyAddressDto;
import com.novelerp.appbase.master.entity.PartnerCompanyAddress;
import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.appcontext.dto.BPartnerDto;
import com.novelerp.appcontext.entity.Location;
import com.novelerp.appcontext.util.ContextConstant;
import com.novelerp.core.dao.impl.AbstractJpaDAO;
/**
 * 
 * @author Aman
 */
@Repository
public class PartnerCompanyAddressDaoImpl extends AbstractJpaDAO<PartnerCompanyAddress, PartnerCompanyAddressDto> implements PartnerCompanyAddressDao{

	@PersistenceContext
	private EntityManager em;
	
	
	@PostConstruct
	private void init() {
		setClazz(PartnerCompanyAddress.class, PartnerCompanyAddressDto.class);
	}
	
	public String getAddressQuery(){
		StringBuilder query =  new StringBuilder();
		query.append("SELECT a FROM PartnerCompanyAddress a")
		.append(" LEFT JOIN FETCH a.location l ")
		.append(" LEFT JOIN FETCH a.partner p")
		.append(" LEFT JOIN FETCH l.createdBy c")
		.append(" LEFT JOIN FETCH l.updatedBy u")
		.append(" LEFT JOIN FETCH l.country cn")
		.append(" LEFT JOIN FETCH l.region r")
		.append(" LEFT JOIN FETCH l.district d")
		.append(" INNER JOIN FETCH a.createdBy ac")
		.append(" INNER JOIN FETCH a.updatedBy au")		
		.append(" WHERE a.partner.bPartnerId = :partnerId" );
		return query.toString();
	}

	public String getAddressQueryByLocation(){
		StringBuilder query =  new StringBuilder();
		query.append("SELECT a FROM PartnerCompanyAddress a")
		.append(" LEFT JOIN FETCH a.location l ")
		.append(" LEFT JOIN FETCH a.partner p")
		.append(" LEFT JOIN FETCH l.createdBy c")
		.append(" LEFT JOIN FETCH l.updatedBy u")
		.append(" LEFT JOIN FETCH l.country cn")
		.append(" LEFT JOIN FETCH l.region r")
		.append(" LEFT JOIN FETCH l.district d")
		.append(" INNER JOIN FETCH a.createdBy ac")
		.append(" INNER JOIN FETCH a.updatedBy au")		
		.append(" WHERE a.partner.bPartnerId = :partnerId and a.locationType= :locationType and a.isActive='Y'" );
		return query.toString();
	}
	
	public String getPrimaryAddressQueryByLocation(){
		StringBuilder query =  new StringBuilder();
		query.append("SELECT a FROM PartnerCompanyAddress a")
		.append(" LEFT JOIN FETCH a.location l ")
		.append(" LEFT JOIN FETCH a.partner p")
		.append(" LEFT JOIN FETCH l.createdBy c")
		.append(" LEFT JOIN FETCH l.updatedBy u")
		.append(" LEFT JOIN FETCH l.country cn")
		.append(" LEFT JOIN FETCH l.region r")
		.append(" LEFT JOIN FETCH l.district d")
		.append(" INNER JOIN FETCH a.createdBy ac")
		.append(" INNER JOIN FETCH a.updatedBy au")		
		.append(" WHERE a.partner.bPartnerId = :partnerId and a.locationType= :locationType and a.isActive='Y' and a.isPrimaryAccount='Y' " );
		return query.toString();
	}
	
	 public List<Object> getregionByLocationandID(String locationType,Long partnerId){
			StringBuilder query = new StringBuilder()					
					.append("select e.code as regioncode, e.description as regionname from m_bp_company_address as a left join c_location as b on a.c_location_id=b.c_location_id left join m_bpartner as c on a.m_bpartner_id=c.m_bpartner_id left join m_country as d on b.m_country_id=d.m_country_id left join c_region as e on b.c_region_id=e.c_region_id left join c_district as f on b.c_district_id=f.c_district_id ")
					.append("WHERE a.m_bpartner_id = :partnerId and a.locationtype= :locationType and a.isactive='Y' and a.is_primary_account='Y'");
			         Query query1 = getEntityManager().createNativeQuery(query.toString());
			         query1.setParameter("locationType", locationType);
			         query1.setParameter("partnerId", partnerId);
			         List<Object> resultSet = query1.getResultList();
              return resultSet;
			
			}
	
	
	public String getregionByLocation(){
		StringBuilder query =  new StringBuilder();
		
		
		query.append("SELECT a FROM PartnerCompanyAddress a")
		.append(" LEFT JOIN FETCH a.location l ")
		.append(" LEFT JOIN FETCH a.partner p")
		.append(" LEFT JOIN FETCH l.createdBy c")
		.append(" LEFT JOIN FETCH l.updatedBy u")
		.append(" LEFT JOIN FETCH l.country cn")
		.append(" LEFT JOIN FETCH l.region r")
		.append(" LEFT JOIN FETCH l.district d")
		.append(" INNER JOIN FETCH a.createdBy ac")
		.append(" INNER JOIN FETCH a.updatedBy au")		
		.append(" WHERE a.partner.bPartnerId = :partnerId and a.locationType= :locationType and a.isActive='Y' and a.isPrimaryAccount='Y' " );
		return query.toString();
	}
	

	
	@SuppressWarnings("unchecked")
	public List<Location> getLocationByLocationType(String locationType,Long bPartnerId){
		StringBuilder query =  new StringBuilder();
		query.append("SELECT a.location FROM PartnerCompanyAddress a")
		.append(" INNER JOIN FETCH a.location l ")
		.append(" where a.locationType=: LocationType and a.partner.bPartnerId=:bPartnerId");	
		Query q=em.createQuery(query.toString(),PartnerCompanyAddress.class);
		q.setParameter("LocationType", locationType);
		q.setParameter("bPartnerId", bPartnerId);
		List<Location> locationList= q.getResultList();
		return locationList;
		
	}
	
	
	
	public String getDefaultAddress(){
		StringBuilder query =  new StringBuilder();
		query.append("SELECT c FROM PartnerCompanyAddress c ")
		.append(" LEFT JOIN FETCH c.location l ")
		.append(" LEFT JOIN FETCH l.country cn")
		.append(" LEFT JOIN FETCH l.region r")
		.append(" LEFT JOIN FETCH l.district d")
		.append(" WHERE c.partner.bPartnerId = :partnerId ORDER BY c.created ASC " );
		/*.append(" WHERE l.created=( SELECT Min(l.created) from Location WHERE l.partner.bPartnerId = :partnerId)" );*/
		return query.toString();
	}
	
	@Override
	public List<PartnerCompanyAddress> getUsersPrimaryAddressList(String searchText) {
		StringBuilder jpqlQuery = new StringBuilder(" select Distinct A from PartnerCompanyAddress A ");
		jpqlQuery.append(" INNER JOIN FETCH A.location B  ")
		.append(" INNER JOIN FETCH B.district F  ")
		.append(" INNER JOIN FETCH B.region G  ")
		.append(" INNER JOIN FETCH B.partner C ")
		.append(" INNER JOIN FETCH A.updatedBy H ")
//		.append(" INNER JOIN FETCH A.createdBy I ")
		.append(" INNER JOIN UserRoles D on C.bPartnerId=D.partner.bPartnerId")
		.append(" INNER JOIN D.role E  ")
		.append(" INNER JOIN User I WITH (I.partner.bPartnerId=C.bPartnerId) ")
		.append(" where E.value IN ('" + AppBaseConstant.ROLE_PARTNER_USER + "','"
					+ AppBaseConstant.ROLE_VENDOR_ADMIN + "'" + ")")
		.append(" and A.isPrimaryAccount ='Y' and (I.userName like :srch OR I.email like :srch OR C.name like :srch ) ");
		Query query = getEntityManager().createQuery(jpqlQuery.toString());
		query.setParameter("srch", "%"+searchText+"%");
		@SuppressWarnings("unchecked")
		List<PartnerCompanyAddress> resultSet = query.getResultList();
		return resultSet;
	}
	
	@Override
	public List<PartnerCompanyAddress> getRegisteredVendors(String searchText) {
		StringBuilder jpqlQuery = new StringBuilder(" select Distinct A from PartnerCompanyAddress A ");
		jpqlQuery.append(" INNER JOIN FETCH A.location B  ")
		.append(" INNER JOIN FETCH B.district F  ")
		.append(" INNER JOIN FETCH B.region G  ")
		.append(" INNER JOIN FETCH B.partner C ")
		.append(" INNER JOIN FETCH A.updatedBy H ")
//		.append(" INNER JOIN FETCH A.createdBy I ")
		.append(" INNER JOIN UserRoles D on C.bPartnerId=D.partner.bPartnerId")
		.append(" INNER JOIN D.role E  ")
		.append(" INNER JOIN User I WITH (I.partner.bPartnerId=C.bPartnerId) ")
		.append(" where C.vendorSapCode is not null AND I.isInvited='Y' AND C.status='"+AppBaseConstant.DOCUMENT_STATUS_COMPLETE+"' AND ")
		.append(" E.value IN ('" + AppBaseConstant.ROLE_PARTNER_USER + "','" )
		.append( AppBaseConstant.ROLE_VENDOR_ADMIN + "'" + ")" )
		.append(" and A.isPrimaryAccount ='Y' and ( C.vendorSapCode like :srch OR LOWER(I.email) like LOWER(:srch) OR LOWER(C.name) like LOWER(:srch) ) ");
		Query query = getEntityManager().createQuery(jpqlQuery.toString());
		query.setParameter("srch", "%"+searchText+"%");
		@SuppressWarnings("unchecked")
		List<PartnerCompanyAddress> resultSet = query.getResultList();
		return resultSet;
	}
	
	@Override
	public List<PartnerCompanyAddress> getNotRegisteredVendors(String searchText) {
		StringBuilder jpqlQuery = new StringBuilder(" select Distinct A from PartnerCompanyAddress A ");
		jpqlQuery.append(" INNER JOIN FETCH A.location B  ")
		.append(" LEFT JOIN FETCH B.district F  ")
		.append(" LEFT JOIN FETCH B.region G  ")
		.append(" INNER JOIN FETCH B.partner C ")
		.append(" INNER JOIN FETCH A.updatedBy H ")
//		.append(" INNER JOIN FETCH A.createdBy I ")
		.append(" INNER JOIN UserRoles D on C.bPartnerId=D.partner.bPartnerId")
		.append(" INNER JOIN D.role E  ")
		.append(" INNER JOIN User I WITH (I.partner.bPartnerId=C.bPartnerId) ")
		.append(" where  C.status<>'"+AppBaseConstant.DOCUMENT_STATUS_COMPLETE+"' AND  ")
		.append(" E.value IN ('" + AppBaseConstant.ROLE_PARTNER_USER + "','" )
		.append( AppBaseConstant.ROLE_VENDOR_ADMIN + "'" + ")" )
		.append(" and A.isPrimaryAccount ='Y' and ( C.vendorSapCode like :srch OR LOWER(I.email) like LOWER(:srch) OR LOWER(C.name) like LOWER(:srch) ) ");
		Query query = getEntityManager().createQuery(jpqlQuery.toString());
		query.setParameter("srch", "%"+searchText+"%");
		@SuppressWarnings("unchecked")
		List<PartnerCompanyAddress> resultSet = query.getResultList();
		return resultSet;
	}
	
	@Override
	public List<PartnerCompanyAddress> getAllVendors(String searchText) {
		StringBuilder jpqlQuery = new StringBuilder(" select Distinct A from PartnerCompanyAddress A ");
		jpqlQuery.append(" INNER JOIN FETCH A.location B  ")
		.append(" LEFT JOIN FETCH B.district F  ")
		.append(" LEFT JOIN FETCH B.region G  ")
		.append(" INNER JOIN FETCH B.partner C ")
		.append(" INNER JOIN FETCH A.updatedBy H ")
//		.append(" INNER JOIN FETCH A.createdBy I ")
		.append(" INNER JOIN UserRoles D on C.bPartnerId=D.partner.bPartnerId")
		.append(" INNER JOIN D.role E  ")
		.append(" INNER JOIN User I WITH (I.partner.bPartnerId=C.bPartnerId) ")
		.append(" where C.vendorSapCode is not null AND ")
		.append(" E.value IN ('" + AppBaseConstant.ROLE_PARTNER_USER + "','" )
		.append( AppBaseConstant.ROLE_VENDOR_ADMIN + "'" + ")" )
		.append(" and A.isPrimaryAccount ='Y' and (I.userName like :srch OR I.email like :srch OR C.name like :srch ) ");
		Query query = getEntityManager().createQuery(jpqlQuery.toString());
		query.setParameter("srch", "%"+searchText+"%");
		@SuppressWarnings("unchecked")
		List<PartnerCompanyAddress> resultSet = query.getResultList();
		return resultSet;
	}
	
	@Override
	public List<PartnerCompanyAddress> getAllInvitedVendors(String searchText) {
		StringBuilder jpqlQuery = new StringBuilder(" select Distinct A from PartnerCompanyAddress A ");
		jpqlQuery.append(" INNER JOIN FETCH A.location B  ")
		.append(" LEFT JOIN FETCH B.district F  ")
		.append(" LEFT JOIN FETCH B.region G  ")
		.append(" INNER JOIN FETCH B.partner C ")
		.append(" INNER JOIN FETCH A.updatedBy H ")
//		.append(" INNER JOIN FETCH A.createdBy I ")
		.append(" INNER JOIN UserRoles D on C.bPartnerId=D.partner.bPartnerId")
		.append(" INNER JOIN D.role E  ")
		.append(" INNER JOIN User I WITH (I.partner.bPartnerId=C.bPartnerId) ")
		.append(" where C.status<>'"+AppBaseConstant.DOCUMENT_STATUS_COMPLETE+"' AND I.isInvited is not null AND I.isInvited='Y' AND")
		.append(" E.value IN ('" + AppBaseConstant.ROLE_PARTNER_USER + "','" )
		.append( AppBaseConstant.ROLE_VENDOR_ADMIN + "'" + ")" )
		.append(" and A.isPrimaryAccount ='Y' and ( C.vendorSapCode like :srch OR  LOWER(I.email) like :srch OR LOWER(C.name) like :srch ) ");
		Query query = getEntityManager().createQuery(jpqlQuery.toString());
		query.setParameter("srch", "%"+searchText+"%");
		@SuppressWarnings("unchecked")
		List<PartnerCompanyAddress> resultSet = query.getResultList();
		return resultSet;
	}

	@Override
	public List<PartnerCompanyAddress> getVendorsforEnquiry(String searchText) {
		StringBuilder jpqlQuery = new StringBuilder(" select Distinct(A) from PartnerCompanyAddress A ");
		jpqlQuery.append(" INNER JOIN FETCH A.location B  ")
		.append(" LEFT JOIN FETCH B.district F  ")
		.append(" LEFT JOIN FETCH B.region G  ")
		.append(" LEFT JOIN FETCH B.partner C ")
		.append(" LEFT JOIN FETCH A.updatedBy H ")
//		.append(" INNER JOIN FETCH A.createdBy I ")
		.append(" LEFT JOIN UserRoles D on C.bPartnerId=D.partner.bPartnerId")
		.append(" LEFT JOIN D.role E  ")
		.append(" LEFT JOIN User I WITH (I.partner.bPartnerId=C.bPartnerId) ")
		/*.append(" where I.isInvited is not null AND I.isInvited='Y' AND")
		.append(" E.value IN ('" + AppBaseConstant.ROLE_PARTNER_USER + "','" )
		.append( AppBaseConstant.ROLE_VENDOR_ADMIN + "'" + ")" )*/
		.append(" where LOWER(C.name) like  LOWER(:srch) ");
		/*.append(" AND C.bPartnerId NOT IN ( ")
		.append(" SELECT Z.bPartnerId FROM Bidder L ")
		.append(" INNER JOIN L.partner Z")
		.append(" INNER JOIN L.pr X")
		.append(" WHERE X.prId=:prId ) ");*/
		Query query = getEntityManager().createQuery(jpqlQuery.toString());
		query.setParameter("srch", "%"+searchText+"%");
		/*query.setParameter("prId", prId);*/
		@SuppressWarnings("unchecked")
		List<PartnerCompanyAddress> resultSet = query.getResultList();
		return resultSet;
	}
	@Override
	public List<Object> getAllVendorsForUpdateCredential(String searchText) {
		
		StringBuilder jpql = new StringBuilder(" select a.m_bpartner_id,a.name as companyName,a.vendor_sap_code,b.ad_user_id,b.email,f.name as district,g.name as region,b.is_invited, a.status from m_bpartner a ")
				.append(" inner join ad_user b on(b.m_bpartner_id=a.m_bpartner_id) inner join ad_user_roles c on(c.ad_user_id=b.ad_user_id) inner join ad_role d on(d.ad_role_id=c.ad_role_id)  ")
		        .append("  Left join m_bp_company_address h on(h.m_bpartner_id=a.m_bpartner_id) left join c_location e on(e.c_location_id=h.c_location_id ) left join c_district f on(f.c_district_id=e.c_district_id) left join c_region g on(g.c_region_id=e.c_region_id) ")
		        .append(" where  d.value in('" + AppBaseConstant.ROLE_PARTNER_USER + "','"+AppBaseConstant.ROLE_VENDOR_ADMIN + "')  and h.is_primary_account ='Y' and ")
		        .append(" ( b.user_name like :srch or LOWER(b.email) like LOWER(:srch) or LOWER(a.name) like LOWER(:srch) ) ");
			Query 	query=getEntityManager().createNativeQuery(jpql.toString());	
			query.setParameter("srch", "%"+searchText+"%");
			@SuppressWarnings("unchecked")
			List<Object> resultSet= query.getResultList();
		
		return resultSet;
	}

	@Override
	public List<Object> getResendInvitationVendorsQuery(String searchText) {
		StringBuilder jpql = new StringBuilder(" select a.m_bpartner_id,a.name, b.email,b.ad_user_id,b.is_invited, a.status,b.user_name from m_bpartner a ")
				.append(" inner join ad_user b on(b.m_bpartner_id=a.m_bpartner_id) inner join ad_user_roles c on(c.ad_user_id=b.ad_user_id) inner join ad_role d on(d.ad_role_id=c.ad_role_id)")
		        .append(" where  d.value in('" + AppBaseConstant.ROLE_PARTNER_USER+ "','"+AppBaseConstant.ROLE_VENDOR_ADMIN + "') and b.is_invited = 'Y' and b.isLoginCreated = 'Y' and ")
		        .append(" (b.user_name like :srch or LOWER(b.email) like LOWER(:srch) or LOWER(a.name) like LOWER(:srch) ) ");
			Query query=getEntityManager().createNativeQuery(jpql.toString());	
			query.setParameter("srch", "%"+searchText+"%");
			@SuppressWarnings("unchecked")
			List<Object> resultSet= query.getResultList();
		
		return resultSet;
	}
	public String getAddressQueryByID(){
		StringBuilder query =  new StringBuilder();
		query.append("SELECT a FROM PartnerCompanyAddress a")
		.append(" LEFT JOIN FETCH a.location l ")
		.append(" LEFT JOIN FETCH a.partner p")
		.append(" LEFT JOIN FETCH l.createdBy c")
		.append(" LEFT JOIN FETCH l.updatedBy u")
		.append(" LEFT JOIN FETCH l.country cn")
		.append(" LEFT JOIN FETCH l.region r")
		.append(" LEFT JOIN FETCH l.district d")
		.append(" INNER JOIN FETCH a.createdBy ac")
		.append(" INNER JOIN FETCH a.updatedBy au")		
		.append(" WHERE a.partnerCompanyAddressId = :id" );
		return query.toString();
	}
	
	@Override
	public String getVendorCredentailReportlist(VendorCredentialReadDto dto) {
		StringBuilder jpql = new StringBuilder();
		//jpql.append("select a.vendor_sap_code as VendorCode from m_bpartner as a");

		jpql.append("select Distinct(A) from PartnerCompanyAddress A");		
	    jpql.append(" LEFT JOIN FETCH A.location l ");
		jpql.append(" LEFT JOIN FETCH A.partner p");
	    jpql.append(" LEFT JOIN FETCH l.district d ");
		jpql.append(" LEFT JOIN FETCH l.region r");	
		jpql.append(" LEFT JOIN User I ON p.bPartnerId=I.partner.bPartnerId");
		
		String where =  getWhereClause(dto);
  		jpql.append(where);
 
    	return jpql.toString();
  			
	}
       private String getWhereClause(VendorCredentialReadDto dto){
		
		
   		StringBuilder where = new StringBuilder();
   		where.append(" WHERE A.isPrimaryAccount='Y' ");
   		
   		   		
   		if(dto.getDistrict()!=null && !dto.getDistrict().equals("")){
   			where.append(" AND d.name = :district");
   		}
   		if(dto.getState()!=null && !dto.getState().equals("")){
   			where.append(" AND r.name=:state");
   		}
   		
   		if(dto.getVendorCode()!=null && !dto.getVendorCode().equals("")){
   			where.append(" AND p.vendorSapCode=:vendorCode");
   		}
   		
   		if(dto.getMailID()!=null && !dto.getMailID().equals("")){
   			where.append(" AND I.email=:mailID");
   		}
   		
   		if(dto.getStatus()!=null && !dto.getStatus().equals("")) {
   			where.append("AND p.status=:status");
   		}
   		
   		return where.toString();

	}
       
   
	
}
