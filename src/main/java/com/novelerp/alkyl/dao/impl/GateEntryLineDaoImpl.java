package com.novelerp.alkyl.dao.impl;

import org.springframework.stereotype.Repository;

import com.novelerp.alkyl.dao.GateEntryLineDao;
import com.novelerp.alkyl.dto.GateEntryLineDto;
import com.novelerp.alkyl.dto.GateEntryReadDto;
import com.novelerp.alkyl.entity.GateEntryLine;
import com.novelerp.core.dao.impl.AbstractJpaDAO;
@Repository
public class GateEntryLineDaoImpl extends AbstractJpaDAO<GateEntryLine, GateEntryLineDto> implements GateEntryLineDao {
	@Override
	public void postConstruct() {
		setClazz(GateEntryLine.class, GateEntryLineDto.class);
	}
	
	public String getGateEntryLineByGateEntryId() {
		StringBuilder jpql = new StringBuilder(" SELECT A FROM GateEntryLine A ")
				.append(" INNER JOIN FETCH A.gateEntry B ")
		//		.append(" INNER JOIN FETCH B.createdBy C")
//				.append(" INNER JOIN FETCH C.userDetails D ")
				.append(" WHERE B.gateEntryId =:gateEntryId ");
		return jpql.toString();
	}
	
	public String getGateEntryLineByGateEntryIdTest() {
		StringBuilder jpql = new StringBuilder(" SELECT A FROM GateEntryLine A ")
				.append(" INNER JOIN FETCH A.gateEntry B ")
				.append(" INNER JOIN FETCH B.createdBy C")
				.append(" INNER JOIN FETCH C.userDetails D ")
				.append(" LEFT JOIN FETCH B.hod H ")
				.append(" LEFT JOIN FETCH B.fh F")
				.append(" LEFT JOIN FETCH B.commercial G")
				.append(" LEFT JOIN FETCH B.planthead I")
				.append(" WHERE B.gateEntryId =:gateEntryId ");
		return jpql.toString();
	}
	
	public String getGateEntryLineDetails(GateEntryReadDto dto) {
		StringBuilder jpql = new StringBuilder(" SELECT A FROM GateEntryLine A ")
				.append(" INNER JOIN FETCH A.gateEntry B ")
				.append(" INNER JOIN FETCH B.createdBy C")
				.append(" INNER JOIN FETCH C.userDetails D ")
				.append(" LEFT JOIN FETCH B.hod H ")
				.append(" LEFT JOIN FETCH B.fh  F")
				.append(" LEFT JOIN FETCH B.commercial G");
				//.append(" WHERE B.gateEntryId =:gateEntryId ");
				String where =  getWhereClause(dto);
				jpql.append(where);
				
		return jpql.toString();
	}
	
	
	public String getGateEntryLineDetailsByLineid() {
		StringBuilder jpql = new StringBuilder(" SELECT A FROM GateEntryLine A ")
				.append(" INNER JOIN FETCH A.gateEntry B ")
				.append(" WHERE A.gateEntryLineId =:gateEntryLineId ");
//				String where =  getWhereClause(dto);
//				jpql.append(where);
				
		return jpql.toString();
	}
	
	private String getWhereClause(GateEntryReadDto dto){
   		StringBuilder where = new StringBuilder();
   		where.append(" WHERE A.isActive = 'Y' and B.docType='NRGP' ");
   		
   		if(dto.getReqNoFrom()!=null && dto.getReqNoTo()!=null){
   			where.append(" AND B.reqNo BETWEEN :reqNoFrom AND :reqNoTo ");
   		}
   		if(dto.getReqNoFrom()!=null && dto.getReqNoTo()==null){
   			where.append(" AND B.reqNo =:reqNoFrom ");
   		}
   		if(dto.getReqNoFrom()==null && dto.getReqNoTo()!=null){
   			where.append(" AND B.reqNo =:reqNoTo ");
   		}
   	//	if(dto.getReqDateFrom()!=null && dto.getReqDateTo()!=null){
   	//		where.append("  AND A.date BETWEEN :fromDate AND :toDate ");
   	//	}
   	//	if(dto.getReqDateFrom()!=null && dto.getReqDateTo()==null){
   	//		where.append(" AND A.date =:fromDate ");
   //		}
  // 		if(dto.getReqDateFrom()==null && dto.getReqDateTo()!=null){
   	//		where.append(" AND A.date =:toDate ");
//   		}
   		if(dto.getStatus()!=null){
   			where.append(" AND B.status =:status ");
   		}
   		if(dto.getDocType()!=null){
   			where.append(" AND B.docType =:docType ");
   		}
   		if(dto.getPlant()!=null){
   			where.append(" AND B.plant =:plant ");
   		}
   		
   		
   		return where.toString();
 	}
	
	
	public String getRGPGateEntryLineDetails(GateEntryReadDto dto) {
		StringBuilder jpql = new StringBuilder(" SELECT A FROM GateEntryLine A ")
				.append(" INNER JOIN FETCH A.gateEntry B ")
				.append(" INNER JOIN FETCH B.createdBy C")
				.append(" INNER JOIN FETCH C.userDetails D ")
				.append(" LEFT JOIN FETCH B.hod H ")
				.append(" LEFT JOIN FETCH B.fh  F")
				.append(" LEFT JOIN FETCH B.commercial G");
				//.append(" WHERE B.gateEntryId =:gateEntryId ");
				String where =  getRGPWhereClause(dto);
				jpql.append(where);
				
		return jpql.toString();
	}
	
	private String getRGPWhereClause(GateEntryReadDto dto){
   		StringBuilder where = new StringBuilder();
   		where.append(" WHERE A.isActive = 'Y' and B.docType='RGP' ");
   		
   		if(dto.getReqNoFrom()!=null && dto.getReqNoTo()!=null){
   			where.append(" AND B.reqNo BETWEEN :reqNoFrom AND :reqNoTo ");
   		}
   		if(dto.getReqNoFrom()!=null && dto.getReqNoTo()==null){
   			where.append(" AND B.reqNo =:reqNoFrom ");
   		}
   		if(dto.getReqNoFrom()==null && dto.getReqNoTo()!=null){
   			where.append(" AND B.reqNo =:reqNoTo ");
   		}
   	//	if(dto.getReqDateFrom()!=null && dto.getReqDateTo()!=null){
   	//		where.append("  AND A.date BETWEEN :fromDate AND :toDate ");
   	//	}
   	//	if(dto.getReqDateFrom()!=null && dto.getReqDateTo()==null){
   	//		where.append(" AND A.date =:fromDate ");
   //		}
  // 		if(dto.getReqDateFrom()==null && dto.getReqDateTo()!=null){
   	//		where.append(" AND A.date =:toDate ");
//   		}
   		if(dto.getStatus()!=null){
   			where.append(" AND B.status =:status ");
   		}
   		if(dto.getDocType()!=null){
   			where.append(" AND B.docType =:docType ");
   		}
   		if(dto.getPlant()!=null){
   			where.append(" AND B.plant =:plant ");
   		}
   		
   		
   		if(dto.getReqDateFrom()!=null && dto.getReqDateTo()!=null){
   			where.append(" AND B.created BETWEEN :reqDateFrom AND :reqDateTo ");
   		}
   		if(dto.getReqDateFrom()!=null && dto.getReqDateTo()==null){
   			where.append(" AND B.created =:reqDateFrom ");
   		}
   		if(dto.getReqDateFrom()==null && dto.getReqDateTo()!=null){
   			where.append(" AND B.created =:reqDateTo ");
   		}
   		
   		
   		return where.toString();
 	}
	
	
	
	

	
}
