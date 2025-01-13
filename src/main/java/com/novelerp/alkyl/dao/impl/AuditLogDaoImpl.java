package com.novelerp.alkyl.dao.impl;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.novelerp.alkyl.dao.AuditLogDao;
import com.novelerp.alkyl.dto.AdvanceShipmentNoticeDto;
import com.novelerp.alkyl.dto.AdvanceShipmentNoticeLineDto;
import com.novelerp.alkyl.dto.AuditLogDto;
import com.novelerp.alkyl.entity.AuditLog;
import com.novelerp.alkyl.service.AuditLogService;
import com.novelerp.core.dao.impl.AbstractJpaDAO;

@Repository
public class AuditLogDaoImpl extends AbstractJpaDAO<AuditLog, AuditLogDto> implements AuditLogDao{
	
	@PersistenceContext
	private EntityManager em;
	
	
	@PostConstruct
	public void postConstruct() {
		setClazz(AuditLog.class, AuditLogDto.class);
	}
	
	@SuppressWarnings("unchecked")
//	@Transactional(propagation =Propagation.REQUIRED,readOnly = true)
	public List<AuditLogDto> getAuditLog(String uniqueCode){
		StringBuilder jpql = new StringBuilder(" SELECT A.CreatedByID, A.CreatedByName,A.CreatedDateTime,A.UpdatedByID,A.UpdatedByName,A.UpdatedDateTime,A.UpdatedColumn,A.OldValue,A.NewValue,A.UniqueKey,A.TableName FROM AuditLog A ")
		.append(" WHERE (A.uniqueKey=:uniqueKey) or (A.tableName=:tableName) or (A.oldValue=:oldValue) or (A.newValue=:newValue)");
		
		
		 Query query1 = getEntityManager().createNativeQuery(jpql.toString());
		 query1.setParameter("uniqueKey", uniqueCode);
		 query1.setParameter("tableName", uniqueCode);
		 query1.setParameter("oldValue", uniqueCode);
		 query1.setParameter("newValue", uniqueCode);
		 
		 List<Object[]> data= query1.getResultList();
		 
		 List<AuditLogDto> auditfinaldto=new ArrayList<AuditLogDto>();
		 for(Object[] array:data){
			 AuditLogDto auditLogReport=new AuditLogDto();
			 long CreatedByID=((BigInteger)(array[0])).longValue();
			 auditLogReport.setCreatedByID(CreatedByID);
			 auditLogReport.setCreatedByName((String) array[1]);
			 auditLogReport.setCreatedDateTime((Timestamp) array[2]);
			 long UpdatedByID=((BigInteger)(array[3])).longValue();
			 auditLogReport.setUpdatedByID(UpdatedByID);
			 auditLogReport.setUpdatedByName((String) array[4]);
			 auditLogReport.setUpdatedDateTime((Timestamp) array[5]);
			 auditLogReport.setUpdatedColumn((String) array[6]);
			 auditLogReport.setOldValue((String) array[7]);
			 auditLogReport.setNewValue((String) array[8]);
			 auditLogReport.setUniqueKey((String) array[9]);
			 auditLogReport.setTableName((String) array[10]);
			 
			 auditfinaldto.add(auditLogReport);
		 }
//         List<Object> resultSet = query1.getResultList();
//        return resultSet;
		 
		 return auditfinaldto;
	}
	
	

//	public String getAuditLog(){
//		StringBuilder jpql = new StringBuilder(" SELECT A FROM AuditLog A ")
//		.append(" WHERE A.uniqueKey=:uniqueKey");
//        return jpql.toString();
//	}

}
