package com.novelerp.alkyl.dao.impl;

import org.springframework.stereotype.Repository;

import com.novelerp.alkyl.dao.MaterialGateInListDao;
import com.novelerp.alkyl.dto.GateEntryReadDto;
import com.novelerp.core.dao.impl.AbstractJpaDAO;
import com.novelerp.eat.dto.MaterialGateInListDto;
import com.novelerp.eat.entity.MaterialGateInList;

@Repository
public class MaterialGateInListDaoImpl extends AbstractJpaDAO<MaterialGateInList, MaterialGateInListDto> implements MaterialGateInListDao{

	@Override
	public void postConstruct() {
		setClazz(MaterialGateInList.class, MaterialGateInListDto.class);
	}
	
	public String getMaterialGateInLIst() {
		StringBuilder jpql = new StringBuilder(" SELECT A FROM MaterialGateInList A ")
				.append(" INNER JOIN FETCH A.gateEntryLine B ")
				.append(" INNER JOIN FETCH A.materialGateIn C ")
				.append(" WHERE C.gateInId =:materialGateIn ");
		
		return jpql.toString();
	}
	
	public String getmaterialGateEntryDetails(GateEntryReadDto dto) {
		
		StringBuilder query = new StringBuilder(" SELECT A FROM MaterialGateInList A ")
				.append(" LEFT JOIN FETCH A.gateEntryLine B ")
				.append(" LEFT JOIN FETCH A.materialGateIn C ")
		        .append(" LEFT JOIN FETCH C.gateEntry ge ")
		        .append(" INNER JOIN FETCH ge.createdBy cb")
		        .append(" INNER JOIN FETCH cb.userDetails D ");
		
				String where =  getWhereClause(dto);
				query.append(where);
		
		
//		StringBuilder query = new StringBuilder(" SELECT A FROM gateEntry A ")
//				
//		         .append(" LEFT JOIN FETCH A.MaterialGateInList D ")
//		         .append(" LEFT JOIN FETCH D.gateEntryLine B ")
//		         .append(" LEFT JOIN FETCH D.materialGateIn C ");
//				String where =  getWhereClause(dto);
//				query.append(where);
			
		
       return query.toString();
}

private String getWhereClause(GateEntryReadDto dto){
	StringBuilder where = new StringBuilder();
	where.append(" WHERE ge.isActive = 'Y' ");
	
	if(dto.getReqNoFrom()!=null && dto.getReqNoTo()!=null){
		where.append(" AND ge.reqNo BETWEEN :reqNoFrom AND :reqNoTo ");
	}
	if(dto.getReqNoFrom()!=null && dto.getReqNoTo()==null){
		where.append(" AND ge.reqNo =:reqNoFrom ");
	}
	if(dto.getReqNoFrom()==null && dto.getReqNoTo()!=null){
		where.append(" AND ge.reqNo =:reqNoTo ");
	}
//	if(dto.getReqDateFrom()!=null && dto.getReqDateTo()!=null){
//		where.append("  AND A.date BETWEEN :fromDate AND :toDate ");
//	}
//	if(dto.getReqDateFrom()!=null && dto.getReqDateTo()==null){
//		where.append(" AND A.date =:fromDate ");
//		}
// 		if(dto.getReqDateFrom()==null && dto.getReqDateTo()!=null){
//		where.append(" AND A.date =:toDate ");
//	}
	if(dto.getStatus()!=null){
		where.append(" AND C.status =:status ");
	}
//	if(dto.getDocType()!=null){
//		where.append(" AND B.docType =:docType ");
//	}
	if(dto.getPlant()!=null){
		where.append(" AND ge.plant =:plant ");
	}
	
	
	
	
	return where.toString();
}

}
