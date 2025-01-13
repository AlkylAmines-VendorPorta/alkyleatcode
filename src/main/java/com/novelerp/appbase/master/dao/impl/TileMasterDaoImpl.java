package com.novelerp.appbase.master.dao.impl;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.novelerp.appbase.master.dao.TileMasterDao;
import com.novelerp.appbase.master.dto.TileMasterDto;
import com.novelerp.appbase.master.entity.TileMaster;
import com.novelerp.core.dao.impl.AbstractJpaDAO;

/**
 * @author Aman Sahu
 *
 */
@Repository
public class TileMasterDaoImpl extends AbstractJpaDAO<TileMaster, TileMasterDto> implements TileMasterDao{

/*private static final String ENTITY_NAME="M_TILE";*/

@PersistenceContext
private EntityManager em;
	
	@PostConstruct
	private void init() {
		setClazz(TileMaster.class, TileMasterDto.class);
	}
	
	@Override
	@Transactional(propagation =Propagation.SUPPORTS)
	public List<TileMaster> findAll() {		 
		StringBuilder jpql = getBasicQuery();
		jpql.append(" WHERE ")
		.append(getActiveClause())
		.append(getBasicOrderBy());		
		EntityManager em = getEntityManager(); 
		return em.createQuery(jpql.toString(),TileMaster.class).getResultList();
	}
	
	public StringBuilder getBasicQuery(){
		StringBuilder jpql =  new StringBuilder();
		jpql.append("SELECT u FROM TileMaster u")
		.append(" LEFT JOIN FETCH u.partner p" )
		.append(" LEFT JOIN FETCH u.createdBy cb")
		.append(" LEFT JOIN FETCH u.updatedBy ub");
		return jpql;
	}
	
	public StringBuilder QueryForTileList(){
		StringBuilder jpql =  new StringBuilder();
		jpql.append("SELECT u FROM TileMaster u")
		.append(" WHERE u.isSummary=:isSummary ")
		.append(" ORDER BY u.sequenceNo ASC");
		return jpql;
	}
	public StringBuilder QueryForSubTileList(){
		StringBuilder jpql =  new StringBuilder();
		jpql.append("SELECT u FROM TileMaster u")
		.append(" WHERE u.isSummary=:isSummary  AND u.sequenceNo=0 ")
		.append(" ORDER BY u.parentId ASC");
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
	public List<TileMaster> getTileMasterList() {
		StringBuilder sb=new StringBuilder("select t from TileMaster t ");
		Query q=em.createQuery(sb.toString(),TileMaster.class);
		List<TileMaster> tileMasterList= q.getResultList();
		return tileMasterList;
	}
	
	@Override
	public String getTilesByRoleIdQuery() {
		
		StringBuilder sb=new StringBuilder("SELECT tileParent.tileMasterId, tileParent.entity, tileParent.countWhereCondition,tileParent.elementId FROM TileMaster tileParent");
		sb.append(" LEFT JOIN TileMaster tileChild with tileChild.parentId = tileParent.tileMasterId ");
		sb.append(" LEFT JOIN RoleAccessMaster ram with ram.tile.tileMasterId= tileChild.tileMasterId ");
		sb.append(" WHERE ram.role.roleId = 8 AND tileParent.levelNo = 0 ");
		//sb.append(" GROUP BY tileParent.tileMasterId, tileParent.entity, tileParent.countWhereCondition,tileParent.elementId");
		return sb.toString();
		
	}

}

