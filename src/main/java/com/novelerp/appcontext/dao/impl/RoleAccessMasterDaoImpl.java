package com.novelerp.appcontext.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.novelerp.appcontext.dao.RoleAccessMasterDao;
import com.novelerp.appcontext.dto.RoleAccessMasterDto;
import com.novelerp.appcontext.entity.RoleAccessMaster;
import com.novelerp.core.dao.impl.AbstractJpaDAO;

/** 
 * @author Aman
 *
 */
@Repository
public class RoleAccessMasterDaoImpl extends AbstractJpaDAO<RoleAccessMaster,RoleAccessMasterDto> implements RoleAccessMasterDao{

	Logger log=LoggerFactory.getLogger(RoleAccessMasterDaoImpl.class);

	@PersistenceContext
	private EntityManager em;
	
	@PostConstruct
	public void setClazz(){
		setClazz(RoleAccessMaster.class,RoleAccessMasterDto.class);
	}
	
	@Override
	@Transactional(propagation =Propagation.SUPPORTS)
	public List<RoleAccessMaster> findAll() {
		return execute(getJpqlQuery().toString(), null, RoleAccessMaster.class);		
	}
		
	public StringBuilder getJpqlQuery(){		
		StringBuilder jpql = new StringBuilder();
		jpql.append(" Select c from RoleAccessMaster c");
		return jpql;
	}
	
	private Map<String, Object> getParams(Long roleID){
		Map<String, Object> params= new HashMap<>();
		params.put("roleID", roleID);
		return params;
	}
	
	private StringBuilder getQueryForUserRoleAccessMasters(){		
		StringBuilder jpql = new StringBuilder();
		jpql.append(" Select r from RoleAccessMaster r ")
		.append(" LEFT JOIN FETCH r.role rl")
		.append(" LEFT JOIN FETCH r.tile tl")
		.append(" WHERE r.role.roleId = :roleID and r.isActive='Y' ORDER BY tl.sequenceNo ASC ");
		return jpql;
	}

	@Override
	public List<RoleAccessMaster> getUserRoleAccessMastersByRoleId(Long roleID) {
		StringBuilder jpql =  getQueryForUserRoleAccessMasters();
		return execute(jpql.toString(), getParams(roleID), RoleAccessMaster.class);
	}	
	
	@Override
	public List<RoleAccessMaster> getTilesByRoleId(Long roleId) {
		StringBuilder sb=new StringBuilder("Select r from RoleAccessMaster r");
		sb.append(" LEFT JOIN FETCH r.tile tl");
		sb.append(" where r.role.roleId = :roleID and r.viewOnly='Y'");
		Query q=em.createQuery(sb.toString(),RoleAccessMaster.class);
		q.setParameter("roleID", roleId);
		@SuppressWarnings("unchecked")
		List<RoleAccessMaster> list= q.getResultList();
		return list;
	}
	
	public String getTilesByRoleId(){
		StringBuilder jpql= new StringBuilder();
		jpql.append(" Select r from RoleAccessMaster r");
		jpql.append(" LEFT JOIN FETCH r.tile tl");
		jpql.append(" LEFT JOIN FETCH tl.parentTile pt ");
		jpql.append(" where r.role.roleId = :roleId and r.viewOnly='Y'");
		jpql.append(" ORDER BY tl.parentTile ASC,tl.viewSequence ASC ");
		return jpql.toString();
	}
	
	public String getWorkFlowsTilesByRoleId(){
		StringBuilder jpql= new StringBuilder();
		jpql.append(" Select r from RoleAccessMaster r");
		jpql.append(" LEFT JOIN FETCH r.tile tl");
		jpql.append(" LEFT JOIN FETCH tl.parentTile pt ");
		jpql.append(" where r.role.roleId = :roleId and r.viewOnly='Y' AND  tl.isWorkflow='Y' AND tl.levelNo=1 ");
		return jpql.toString();
	}
	
	@Override
	public List<RoleAccessMaster> getSubTilesByRoleId(Long roleId,Long tileId) {
		StringBuilder sb=new StringBuilder("Select r from RoleAccessMaster r");
		sb.append(" LEFT JOIN FETCH r.tile tl");
		sb.append(" where r.role.roleId = :roleID and r.tile.parentId=:tileID and r.viewOnly='Y'");
		Query q=em.createQuery(sb.toString(),RoleAccessMaster.class);
		q.setParameter("roleID", roleId);
		q.setParameter("tileID", tileId);
		@SuppressWarnings("unchecked")
		List<RoleAccessMaster> list= q.getResultList();
		return list;
	}
	
	public String getSubTilesByRoleId(){
		StringBuilder jpql= new StringBuilder();
		jpql.append("Select r from RoleAccessMaster r");
		jpql.append(" LEFT JOIN FETCH r.tile tl");
		jpql.append(" LEFT JOIN FETCH tl.parentTile pt ");
		jpql.append(" where r.role.roleId = :roleId and r.tile.parentId=:tileId and r.viewOnly='Y'");
		jpql.append(" ORDER BY tl.parentTile ASC,tl.viewSequence ASC ");
		return jpql.toString();
	}
	
	@Override
	public List<RoleAccessMaster> getAccessByRoleId(Long roleId,Long tileId) {
		StringBuilder sb=new StringBuilder("Select r from RoleAccessMaster r");
		sb.append(" LEFT JOIN FETCH r.tile tl");
		sb.append(" LEFT JOIN FETCH r.role rl");
		sb.append(" where r.role.roleId = :roleID and r.tile.tileMasterId=:tileID");
		Query q=em.createQuery(sb.toString(),RoleAccessMaster.class);
		q.setParameter("roleID", roleId);
		q.setParameter("tileID", tileId);
		@SuppressWarnings("unchecked")
		List<RoleAccessMaster> list= q.getResultList();
		return list;
	}
	
	public String getAccessByRoleId(){
		StringBuilder jpql= new StringBuilder();
		jpql.append("Select r from RoleAccessMaster r");
		jpql.append(" LEFT JOIN FETCH r.tile tl");
		jpql.append(" LEFT JOIN FETCH r.role rl");
		jpql.append(" where r.role.roleId = :roleID and r.tile.tileMasterId=:tileID");
		return jpql.toString();
	}
	
	@Override
	public String getSubTilesByRoleIdAndTileIDQuery(){
		StringBuilder sb=new StringBuilder("Select r from RoleAccessMaster r");
		sb.append(" LEFT JOIN FETCH r.tile tl");
		sb.append(" where r.role.roleId = :roleId and r.tile.parentId IN( :tileId ) and r.viewOnly='Y'");
		return sb.toString();
	}
	@Override
	public String getSubTilesForWorkflowQuery(){
		StringBuilder sb=new StringBuilder("Select r from RoleAccessMaster r");
		sb.append(" LEFT JOIN FETCH r.tile tl");
		sb.append(" where r.role.roleId = :roleId and tl.isWorkflow='Y' and r.viewOnly='Y'");
		return sb.toString();
	}
	
	@Override
	public String getTilesByRoleIdQuery() {
		/*StringBuilder sb=new StringBuilder("SELECT ram FROM RoleAccessMaster ram");
		sb.append(" LEFT JOIN ram.tile tileChild");
		sb.append(" LEFT JOIN TileMaster tileParent with tileChild.parentId = tileParent.tileMasterId");
		sb.append(" WHERE ram.role.roleId = 8 AND tileParent.levelNo = 0 ");
		sb.append(" GROUP BY tileParent");
		return sb.toString();*/
		
		StringBuilder sb=new StringBuilder("SELECT tileParent FROM TileMaster tileParent");
		sb.append(" LEFT JOIN TileMaster tileChild with tileChild.parentId = tileParent.tileMasterId ");
		sb.append(" LEFT JOIN RoleAccessMaster ram with ram.tile.tileMasterId= tileChild.tileMasterId ");
		sb.append(" WHERE ram.role.roleId = 8 AND tileParent.levelNo = 0 ");
		sb.append(" GROUP BY tileParent");
		return sb.toString();
		
	}


}

