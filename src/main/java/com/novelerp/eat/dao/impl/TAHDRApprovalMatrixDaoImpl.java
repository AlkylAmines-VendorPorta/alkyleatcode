package com.novelerp.eat.dao.impl;

import javax.annotation.PostConstruct;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.core.dao.impl.AbstractJpaDAO;
import com.novelerp.eat.dao.TAHDRApprovalMatrixDao;
import com.novelerp.eat.dto.TAHDRApprovalMatrixDto;
import com.novelerp.eat.entity.TAHDRApprovalMatrix;

@Repository
public class TAHDRApprovalMatrixDaoImpl extends AbstractJpaDAO<TAHDRApprovalMatrix, TAHDRApprovalMatrixDto> implements TAHDRApprovalMatrixDao {

	@PostConstruct
	public void postConstruct() {
		setClazz(TAHDRApprovalMatrix.class, TAHDRApprovalMatrixDto.class);
	}

	public String getTahdrApprovalMatrixDetails()
	{
		StringBuilder jpql =  new StringBuilder(" Select t from TAHDRApprovalMatrix t ");
		jpql.append(" LEFT JOIN FETCH t.user u ");
		jpql.append(" LEFT JOIN FETCH u.userDetails ud ");
		jpql.append(" LEFT JOIN FETCH u.partner p");
		jpql.append(" LEFT JOIN FETCH t.tahdr tahdr ");
		/*jpql.append(" LEFT JOIN FETCH td.tahdr tahdr");*/
		jpql.append(" WHERE t.tahdr.tahdrId = :tahdrId ");	
		jpql.append(" ORDER BY t.levels ASC ");
		return jpql.toString();
	}

	public String getApprovaMatrixFromLevel(){
		StringBuilder jpql= new StringBuilder(" Select t from TAHDRApprovalMatrix t ");
/*		jpql.append(" LEFT JOIN FETCH t.user u ");
		jpql.append(" LEFT JOIN FETCH t.tahdr tahdr ");
		jpql.append(" LEFT JOIN FETCH tahdr.department d ");
		jpql.append(" LEFT JOIN FETCH tahdr.officeLocation ol ");
*/		jpql.append(" where t.levels=:levelNo and t.tahdr.tahdrId=:tahdrId ");
		return jpql.toString();
	}
	
	public String getApprovalMatrixDataFromTahdrId(){
		StringBuilder jpql= new StringBuilder(" Select t from TAHDRApprovalMatrix t ");
		jpql.append(" LEFT JOIN FETCH t.user u ");
		jpql.append(" where t.user.userId=:userId and t.tahdr.tahdrId=:tahdrId ");
		return jpql.toString();
	}
	
	public String getApprovalMatrixDataForApproval(){
		StringBuilder jpql= new StringBuilder(getApprovalMatrixDataFromTahdrId());
		jpql.append(" and t.status= :status ");
		return jpql.toString();
	}
	
	@Override
	@Transactional
	public Long getLevelsfromTahdrId(Long tahdrId){		
		StringBuilder jpql = new StringBuilder();
		jpql.append(" Select MIN(levels) as LevelNo from TAHDRApprovalMatrix t "
				+ " where t.status IS NULL and t.tahdr.tahdrId=:tahdrId ");
		TypedQuery<Long> q = getEntityManager().createQuery(jpql.toString(), Long.class);
		q.setParameter("tahdrId", tahdrId);
		Long level=q.getSingleResult();
		return level;
	}
	
	public String getNextUserToUpdateStatusAP(){
		StringBuilder jpql= new StringBuilder(" Select t from TAHDRApprovalMatrix t ");
		jpql.append(" LEFT JOIN FETCH t.user u ");
		jpql.append(" where t.tahdr.tahdrId= :tahdrId ");
		jpql.append(" AND t.levels > (select am.levels as levels from TAHDRApprovalMatrix am where am.tahdr.tahdrId = :tahdrId and am.user.userId=:userId) ");
		jpql.append(" AND t.status IS NULL ");
		return jpql.toString();
	}
	
	@Override
	@Transactional
	public int updateStatusOfCurrentUser(Long tahdrId,Long userId,String remarks,String status){		
		StringBuilder jpql= new StringBuilder();
		jpql.append(" UPDATE TAHDRApprovalMatrix t set t.status=:status, t.remarks=:remarks ");
		jpql.append( " where t.tahdr.tahdrId=:tahdrId and t.user.userId=:userId ");
		Query query=getEntityManager().createQuery(jpql.toString());
		query.setParameter("tahdrId", tahdrId);
		query.setParameter("userId", userId);
		query.setParameter("remarks", remarks);
		query.setParameter("status", status);
		int count= query.executeUpdate();
		return count;
	}
	
	@Override
	@Transactional
	public int updateStatusOfNextUser(Long tahdrId,Long userId){		
		StringBuilder jpql= new StringBuilder();
		jpql.append(" UPDATE TAHDRApprovalMatrix t set t.status='"+AppBaseConstant.DOCUMENT_STATUS_IN_PROGRESS+"' ");
		jpql.append( " where t.tahdr.tahdrId=:tahdrId and t.user.userId=:userId ");
		Query query=getEntityManager().createQuery(jpql.toString());
		query.setParameter("tahdrId", tahdrId);
		query.setParameter("userId", userId);
		int count= query.executeUpdate();
		return count;
	}
	
	@Override
	@Transactional
	public int updateStatusOfFirstUserIP(Long tahdrId){
		StringBuilder jpql= new StringBuilder();
		jpql.append(" UPDATE TAHDRApprovalMatrix t set t.status='"+AppBaseConstant.DOCUMENT_STATUS_IN_PROGRESS+"' ");
		jpql.append( " where t.tahdr.tahdrId=:tahdrId and t.levels=1 ");
		Query query=getEntityManager().createQuery(jpql.toString());
		query.setParameter("tahdrId", tahdrId);
		int count= query.executeUpdate();
		return count;
	}
	
	public String getQueryForUserByUserId(){
		StringBuilder jpql =  new StringBuilder(" Select t from TAHDRApprovalMatrix t ");
		jpql.append(" LEFT JOIN FETCH t.user u ");
		jpql.append(" WHERE t.user.userId = :userId AND t.tahdr.tahdrId=:tahdrId ");			
		return jpql.toString();
	}
	
	@Override
	@Transactional
	public int updateStatusOfUserToNull(Long tahdrId){		
		StringBuilder jpql= new StringBuilder();
		jpql.append(" UPDATE TAHDRApprovalMatrix t set t.status=NULL ");
		jpql.append( " where t.tahdr.tahdrId=:tahdrId ");
		Query query=getEntityManager().createQuery(jpql.toString());
		query.setParameter("tahdrId", tahdrId);
		int count= query.executeUpdate();
		return count;
	}
	
	@Override
	@Transactional
	public int updateLevelOnDelete(Long tahdrId,Long levels){
		StringBuilder jpql= new StringBuilder();
		jpql.append(" Update TAHDRApprovalMatrix t set t.levels=t.levels-1 ");
		jpql.append(" where t.tahdr.tahdrId=:tahdrId and t.levels>:levels ");
		Query query=getEntityManager().createQuery(jpql.toString());
		query.setParameter("tahdrId", tahdrId);
		query.setParameter("levels", levels);
		int count= query.executeUpdate();
		return count;
		
	}
	
	@Override
	public String getFirstLevelApprovalData(){
		StringBuilder jpql= new StringBuilder(" Select t from TAHDRApprovalMatrix t ");
		jpql.append(" LEFT JOIN FETCH t.user u ");
		jpql.append(" LEFT JOIN FETCH u.partner p ");
		jpql.append(" LEFT JOIN FETCH u.userDetails ud ");
		jpql.append(" LEFT JOIN FETCH t.tahdr tahdr ");
		jpql.append(" LEFT JOIN FETCH tahdr.department d ");
		jpql.append(" LEFT JOIN FETCH tahdr.officeLocation ol ");
		jpql.append(" where t.levels=:levelNo and t.tahdr.tahdrId=:tahdrId ");
		return jpql.toString();
	}
	
	@Override
	public String getNextUserToForMail(){
		StringBuilder jpql= new StringBuilder(" Select t from TAHDRApprovalMatrix t ");
		jpql.append(" LEFT JOIN FETCH t.user u ");
		jpql.append(" LEFT JOIN FETCH u.partner p ");
		jpql.append(" LEFT JOIN FETCH u.userDetails ud ");
		jpql.append(" where t.tahdr.tahdrId= :tahdrId ");
		jpql.append(" AND t.levels > (select am.levels as levels from TAHDRApprovalMatrix am where am.tahdr.tahdrId = :tahdrId and am.user.userId=:userId) ");
		jpql.append(" AND t.status='"+AppBaseConstant.DOCUMENT_STATUS_IN_PROGRESS+"'");
		return jpql.toString();
	}
}

