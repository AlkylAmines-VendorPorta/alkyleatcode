package com.novelerp.appbase.master.dao.impl;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.novelerp.appbase.master.dao.MaterialVersionDao;
import com.novelerp.appbase.master.dto.MaterialVersionDto;
import com.novelerp.appbase.master.entity.MaterialVersion;
import com.novelerp.core.dao.impl.AbstractJpaDAO;

/**
 * @author Aman Sahu
 *
 */
@Repository
public class MaterialVersionDaoImpl extends AbstractJpaDAO<MaterialVersion, MaterialVersionDto> implements MaterialVersionDao{

private static final String ENTITY_NAME="M_MATERIALVERSION";
	
	@PersistenceContext
	private EntityManager em;

	@PostConstruct
	private void init() {
		setClazz(MaterialVersion.class, MaterialVersionDto.class);
	}
	
	@Override
	@Transactional(propagation =Propagation.SUPPORTS)
	public List<MaterialVersion> findAll() {		 
		StringBuilder jpql = getBasicQuery();
		jpql.append(" WHERE ")
		.append(getActiveClause())
		.append(getBasicOrderBy());		
		EntityManager em = getEntityManager(); 
		return em.createQuery(jpql.toString(),MaterialVersion.class).getResultList();
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
	public List<MaterialVersion> getMaterialVersionList() {
		StringBuilder sb=new StringBuilder("select mv from MaterialVersion mv ");
		sb.append(" LEFT JOIN FETCH mv.material m");
		Query q=em.createQuery(sb.toString(),MaterialVersion.class);
		List<MaterialVersion> materialVersionList= q.getResultList();
		return materialVersionList;
	}
	
	public String getQueryForMaterialVersions()
	{
		StringBuilder sb=new StringBuilder("select mv from MaterialVersion mv ");
		sb.append(" LEFT JOIN FETCH mv.material m");
		sb.append(" LEFT JOIN FETCH mv.versionSpecification vs");
		sb.append(" WHERE mv.material.materialId=:materialId ");
		return sb.toString();
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<MaterialVersion> getMaterialVersionById(Long id) {
		StringBuilder sb=new StringBuilder("select mv from MaterialVersion mv ");
		sb.append(" LEFT JOIN FETCH mv.material m where mv.materialVersionId=:id");
		Query q=em.createQuery(sb.toString(),MaterialVersion.class);
		q.setParameter("id", id);
		List<MaterialVersion> materialVersionList= q.getResultList();
		return materialVersionList;
	}

	public String getMaterialVersionByMaterial(){
		StringBuilder jpql=new StringBuilder(" SELECT E FROM MaterialVersion E ");
		jpql.append(" LEFT JOIN FETCH E.versionSpecification att ");
		jpql.append(" WHERE E.material.materialId=:materialId ORDER BY E.name");
		return jpql.toString();
	}
	
	public String getQueryForMaterialVersionIsExist()
	{
		StringBuilder jpql=new StringBuilder(" SELECT mv FROM MaterialVersion mv ");
		jpql.append(" where  mv.material.materialId=:material AND mv.code=:versionNumber AND mv.partner.bPartnerId = :BPartnerId");
		return jpql.toString();
	}

}

