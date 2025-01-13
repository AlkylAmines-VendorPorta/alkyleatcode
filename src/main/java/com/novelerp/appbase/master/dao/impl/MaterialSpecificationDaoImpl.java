package com.novelerp.appbase.master.dao.impl;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.novelerp.appbase.master.dao.MaterialSpecificationDao;
import com.novelerp.appbase.master.dto.MaterialSpecificationDto;
import com.novelerp.appbase.master.entity.MaterialSpecification;
import com.novelerp.core.dao.impl.AbstractJpaDAO;

/**
 * @author Aman Sahu
 *
 */
@Repository
public class MaterialSpecificationDaoImpl extends AbstractJpaDAO<MaterialSpecification, MaterialSpecificationDto> implements MaterialSpecificationDao{

private static final String ENTITY_NAME="M_MATERIALSPECIFICATION";
	
	@PersistenceContext
	private EntityManager em;

	@PostConstruct
	private void init() {
		setClazz(MaterialSpecification.class, MaterialSpecificationDto.class);
	}
	
	@Override
	@Transactional(propagation =Propagation.SUPPORTS)
	public List<MaterialSpecification> findAll() {		 
		StringBuilder jpql = getBasicQuery();
		jpql.append(" WHERE ")
		.append(getActiveClause())
		.append(getBasicOrderBy());		
		EntityManager em = getEntityManager(); 
		return em.createQuery(jpql.toString(),MaterialSpecification.class).getResultList();
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
	@SuppressWarnings("unchecked")
	@Override
	public List<MaterialSpecification> getMaterialSpecificationList() {
		StringBuilder sb=new StringBuilder("select ms from MaterialSpecification ms ");
		sb.append(" LEFT JOIN FETCH ms.material m");
		sb.append(" LEFT JOIN FETCH m.hsnCode hs ");
		Query q=em.createQuery(sb.toString(),MaterialSpecification.class);
		List<MaterialSpecification> materialSpecificationList= q.getResultList();
		return materialSpecificationList;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<MaterialSpecification> getMaterialSpecificationListById(Long id) {
		StringBuilder sb=new StringBuilder("select ms from MaterialSpecification ms ");
		sb.append(" LEFT JOIN FETCH ms.material m where ms.materialSpecificationId=:id");
		Query q=em.createQuery(sb.toString(),MaterialSpecification.class);
		q.setParameter("id", id);
		List<MaterialSpecification> materialSpecificationList= q.getResultList();
		return materialSpecificationList;
	}
	
	
	public String getMaterialSpecificationListByTahdrId() {
		StringBuilder sb=new StringBuilder("select ms from MaterialSpecification ms ");
		sb.append(" WHERE ms.bomVersion.bomVersionId IN (SELECT tm.bomVersion.bomVersionId FROM TAHDRMateriall tm "
				+ " WHERE tm.tahdrDetail.tahdr.tahdrId=:tahdrId AND tm.tahdrDetail.isActive='Y')");
		return sb.toString();
	}
	
	public String getSpecificationListByMaterialId() {
		StringBuilder sb=new StringBuilder("select ms from MaterialSpecification ms ");
		sb.append(" LEFT JOIN FETCH ms.specification m where m.materialId=:materialId");
		return sb.toString();
	}

	public String getSpecificationListByBomversionId() {
		StringBuilder sb=new StringBuilder("select ms from MaterialSpecification ms ");
		sb.append(" LEFT JOIN FETCH ms.material m ");
		sb.append(" LEFT JOIN FETCH ms.specification s ");
		sb.append(" LEFT JOIN FETCH s.hsnCode h ");
		sb.append(" LEFT JOIN FETCH ms.specification sp where m.bomVersionId=:bomVersionId");
		return sb.toString();
	}
	public String getSpecificationListByTahdrMaterialId() {
		StringBuilder sb=new StringBuilder("select ms from MaterialSpecification ms ")
		.append(" INNER JOIN FETCH ms.material bom ")
		.append(" INNER JOIN FETCH ms.specification m ")
		.append(" INNER JOIN FETCH m.uom uom ")
		.append(" INNER JOIN FETCH m.hsnCode hsn ")
		.append(" INNER JOIN TAHDRMaterial tm with bom.material.materialId=tm.material.materialId and bom.bomVersionId=tm.bomVersion.bomVersionId ")
		.append(" where tm.tahdrMaterialId=:tahdrMaterialId ");
		return sb.toString();
	}
}

