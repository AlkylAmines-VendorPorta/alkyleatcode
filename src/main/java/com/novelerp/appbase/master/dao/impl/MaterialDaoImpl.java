package com.novelerp.appbase.master.dao.impl;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.novelerp.appbase.master.dao.MaterialDao;
import com.novelerp.appbase.master.dto.MaterialDto;
import com.novelerp.appbase.master.dto.MaterialSearchDto;
import com.novelerp.appbase.master.entity.Material;
import com.novelerp.commons.util.CommonUtil;
import com.novelerp.core.dao.impl.AbstractJpaDAO;

/**
 * @author Aman Sahu
 *
 */
@Repository
public class MaterialDaoImpl extends AbstractJpaDAO<Material, MaterialDto> implements MaterialDao{

private static final String ENTITY_NAME="M_MATERIAL";

	@PersistenceContext
	private EntityManager em;
	
	@PostConstruct
	private void init() {
		setClazz(Material.class, MaterialDto.class);
	}
	
	@Override
	@Transactional(propagation =Propagation.SUPPORTS)
	public List<Material> findAll() {		 
		StringBuilder jpql = getBasicQuery();
		jpql.append(" WHERE ")
		.append(getActiveClause())
		.append(getBasicOrderBy());		
		EntityManager em = getEntityManager(); 
		return em.createQuery(jpql.toString(),Material.class).getResultList();
	}
	
	public StringBuilder getBasicQuery(){
		StringBuilder jpql =  new StringBuilder();
		jpql.append("SELECT u FROM ")
		.append(ENTITY_NAME)
		.append(" u LEFT JOIN FETCH u.partner p" )
		.append(" LEFT JOIN FETCH u.createdBy cb")
		.append(" LEFT JOIN FETCH u.updatedBy ub");
		return jpql;
	}
	
	public String getBasicOrderBy(){
		return " order by u.updated desc ";
	}
	
	public String getActiveClause(){
		return " u.isActive= 'Y'";
	}
	
	@Override
	public List<Material> getMaterialList() {
		StringBuilder sb=new StringBuilder("select m from Material m ");
		sb.append(" LEFT JOIN FETCH m.uom u");
		sb.append(" LEFT JOIN FETCH m.materialGroup ud");
		sb.append(" LEFT JOIN FETCH m.materialSubGroup");
		sb.append(" LEFT JOIN FETCH m.hsnCode hsn");
		sb.append(" ORDER BY m.materialId ASC");
		Query q=em.createQuery(sb.toString(),Material.class);
		List<Material> materialList= q.getResultList();
		return materialList;
	}

	@Override
	public String getMaterialListQuery() {
		StringBuilder sb=new StringBuilder("select m from Material m ");
		sb.append(" LEFT JOIN FETCH m.uom u");
		sb.append(" LEFT JOIN FETCH m.materialGroup ud");
		sb.append(" LEFT JOIN FETCH m.materialSubGroup");
		sb.append(" LEFT JOIN FETCH m.hsnCode hsn");
		sb.append(" ORDER BY m.materialId ASC");
		return sb.toString();
	}
	@Override
	public String getMaterialListQuery(String searchColumn, String searchValue){
		StringBuilder sb=new StringBuilder("select m from Material m ");
		sb.append(" LEFT JOIN FETCH m.uom u");
		sb.append(" LEFT JOIN FETCH m.partner bp");
		sb.append(" LEFT JOIN FETCH m.materialGroup ud");
		sb.append(" LEFT JOIN FETCH m.materialSubGroup");
		sb.append(" LEFT JOIN FETCH m.hsnCode hsn");
		sb.append(" where m.isActive='Y' AND bp.bPartnerId=:bPartnerId ");
		if(!"none".equalsIgnoreCase(searchValue)){
			sb.append(" AND UPPER(m."+searchColumn+") LIKE :searchValue");
		}
		sb.append(" ORDER BY m.materialId ASC");
		return sb.toString();
	}
	
	@Override
	public String getMaterialListQueryCountQry(String searchColumn,String searchValue){
		StringBuilder sb=new StringBuilder(" c.partner.bPartnerId=:bPartnerId AND c.isActive='Y' ");
				if (!"none".equalsIgnoreCase(searchValue)) {
				sb.append(" AND UPPER(c."+searchColumn+") LIKE :searchValue");
						}
	/*	sb.append(" ORDER BY c.materialId ASC");*/
		return sb.toString();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Material> getMaterialById(Long id) {
		StringBuilder sb=new StringBuilder("select m from Material m ");
		sb.append(" LEFT JOIN FETCH m.uom u");
		sb.append(" LEFT JOIN FETCH m.materialGroup ud");
		sb.append(" LEFT JOIN FETCH m.hsnCode hsn");
		sb.append(" LEFT JOIN FETCH m.materialSubGroup where m.materialId=:id");
		Query q=em.createQuery(sb.toString(),Material.class);
		q.setParameter("id", id);
		List<Material> material= q.getResultList();
		return material;
	}

	@Override
	public List<Material> getMaterialBySubGroupId(Long id) {
		StringBuilder sb=new StringBuilder("select m from Material m ");
		sb.append(" LEFT JOIN FETCH m.uom u");
		sb.append(" LEFT JOIN FETCH m.materialGroup ud");
		sb.append(" LEFT JOIN FETCH m.hsnCode hsn");
		sb.append(" LEFT JOIN FETCH m.materialSubGroup where m.materialSubGroup.materialSubGroupId =:id");
		Query q=em.createQuery(sb.toString(),Material.class);
		q.setParameter("id", id);
		@SuppressWarnings("unchecked")
		List<Material> material= q.getResultList();
		return material;
	}
	
	public String getItemSearchQuery(MaterialSearchDto material,boolean createdCheck){
		StringBuilder query = new StringBuilder();
		query.append("SELECT e FROM Material e")
		.append(" LEFT JOIN FETCH e.materialGroup mg")
		.append(" LEFT JOIN FETCH e.partner bp")
		.append(" LEFT JOIN FETCH e.materialSubGroup msg")
		.append(" LEFT JOIN FETCH e.hsnCode hsn")
		.append(" LEFT JOIN FETCH e.uom mu");
		if(material.getPartnerOrgId()!=null && createdCheck){
			query.append(" INNER JOIN FETCH PartnerOrgProduct pop ON (pop.material.materialId=e.materialId) ");
		}
		String where =  getWhereClause(material,createdCheck);
		query.append(where);
		return query.toString();
	}
	private String getWhereClause(MaterialSearchDto material,boolean createdCheck){
		StringBuilder where = new StringBuilder();
		where.append(" WHERE bp.bPartnerId=:bPartnerId AND e.isActive =:isActive");	
		if(material==null){
			return where.toString();
		}
		if(material.getPartnerOrgId()!=null && createdCheck)
		{
			where.append(" AND pop.partnerOrg.partnerOrgId=:partnerOrgId ");
		}
		if(!CommonUtil.isStringEmpty(material.getCode())){
			where.append(" AND upper(e.code) LIKE upper(:code)");
		}
		if(!CommonUtil.isStringEmpty(material.getName())){
			where.append(" AND upper(e.name) LIKE upper(:name)");
		}
		if(material.getHsnCode()!=null){
			where.append(" AND e.hsnCode.code =:hsnCode");
		}
		if(material.getMaterialGroupId()!=null){
			where.append(" AND e.materialGroup.materialGroupId =:materialGroupId");
		}
		if(material.getMaterialSubGroupId()!=null){
			where.append(" AND e.materialSubGroup.materialSubGroupId =:materialSubGroupId");
		}
		if(!CommonUtil.isStringEmpty(material.getTypeCode())){
			where.append(" AND upper(e.typeCode) LIKE upper(:typeCode)");
		}
		return where.toString();
	}
    @Override
	public String getQueryForTradingMaterial(MaterialSearchDto dto){
	StringBuilder jpql=new StringBuilder(" SELECT distinct(m) FROM Material m ")
		   .append(" LEFT JOIN FETCH m.hsnCode hc ")
	       .append(" LEFT JOIN FETCH m.uom u ") 
	       .append(" LEFT JOIN FETCH m.materialGroup mg")
		   .append(" LEFT JOIN FETCH m.materialSubGroup msg");
		   jpql.append(getWhereClauseForTradingMaterial(dto));		
	return jpql.toString();
	}
	public String getWhereClauseForTradingMaterial(MaterialSearchDto dto){
		StringBuilder where=new StringBuilder(" WHERE m.isActive='Y' ")
		.append(" AND m.materialId IN(select t.material.materialId From PartnerTradingItem t where t.partner.bPartnerId=:partnerId) ");
	    /*.append(" AND m.materialId NOT IN(select p.material.materialId From PartnerOrgPerformance p where p.partner.bPartnerId=:partnerId AND p.partnerOrg.partnerOrgId IS NULL) ");*/			
       
		if(dto.getMaterialGroupId()!=null){
			where.append(" AND m.materialGroup.materialGroupId =:materialGroupId");
		}
		if(dto.getMaterialSubGroupId()!=null){
			where.append(" AND m.materialSubGroup.materialSubGroupId =:materialSubGroupId");
		}
		if(!CommonUtil.isStringEmpty(dto.getCode())){
			where.append(" AND upper(m.code) LIKE upper(:code)");
		}
		if(!CommonUtil.isStringEmpty(dto.getName())){
			where.append(" AND upper(m.name) LIKE upper(:name)");
		}
		return where.toString();
	}
	public String getMaterialsForOrgPrfm(){
		StringBuilder query = new StringBuilder("SELECT e FROM Material e")
		.append(" LEFT JOIN FETCH e.materialGroup mg")
		.append(" LEFT JOIN FETCH e.materialSubGroup msg")
		.append(" LEFT JOIN FETCH e.hsnCode hsn")
		.append(" LEFT JOIN FETCH e.uom mu")
		.append(" INNER JOIN FETCH PartnerOrgProduct pop ON (pop.material.materialId=e.materialId) ")
		.append(" WHERE pop.partnerOrg.partnerOrgId=:partnerOrgId ");
		return query.toString();
	}
	public String getMaterialsForTraderPrfm(){
		StringBuilder query = new StringBuilder("SELECT e FROM Material e ")
		.append(" LEFT JOIN FETCH e.materialGroup mg ")
		.append(" LEFT JOIN FETCH e.materialSubGroup msg ")
		.append(" LEFT JOIN FETCH e.hsnCode hsn ")
		.append(" LEFT JOIN FETCH e.uom mu ")
		.append(" WHERE e.isActive='Y' ")
		.append(" AND e.materialId IN(select t.material.materialId From PartnerTradingItem t where t.partner.bPartnerId=:partnerId) ");
		return query.toString();
	}
	
	public String getMatrialByCode(){
		StringBuilder jpql = new StringBuilder("SELECT e FROM Material e ")
				.append(" INNER JOIN FETCH e.materialGroup mg ")
				.append(" INNER JOIN FETCH e.materialSubGroup msg ")
				.append(" INNER JOIN FETCH e.hsnCode hsn ")
				.append(" INNER JOIN FETCH e.uom mu ")
				.append(" WHERE e.isActive='Y' ")
				.append(" and e.code=:materialCode ");
		
		return jpql.toString();
	}
}			