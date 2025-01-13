package com.novelerp.alkyl.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.novelerp.alkyl.dao.MaterialGateInDao;
import com.novelerp.alkyl.dto.GateEntryReadDto;
import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.core.dao.impl.AbstractJpaDAO;
import com.novelerp.eat.dto.MaterialGateInDto;
import com.novelerp.eat.entity.MaterialGateIn;

@Repository
public class MaterialGateInDaoImpl extends AbstractJpaDAO<MaterialGateIn, MaterialGateInDto> implements MaterialGateInDao{
	
	@Override
	public void postConstruct() {
		setClazz(MaterialGateIn.class, MaterialGateInDto.class);
	}
	@PersistenceContext
	private EntityManager em;
	@Override
	public String getNewReqNo() {
		String rePrefix = AppBaseConstant.DOCPREFIX;
		StringBuilder query = new StringBuilder(" SELECT MAX(A.docNo) FROM MaterialGateIn A ")
				.append(" WHERE A.isActive = 'Y' AND CONCAT(A.docNo,'') like '" + rePrefix + "%' ");
		Query q = em.createQuery(query.toString());
		String x = (String) q.getSingleResult();

		if (x == null) {

			x = rePrefix + "000000";
			String[] y = x.split(rePrefix);
			String z = y[1];
			int a = Integer.parseInt(z);
			a = a + 1;
			String leftPadded = StringUtils.leftPad("" + a, 7, "0");
			// StringBuilder sb = new StringBuilder();
			// sb.append(x).append(a);
			String finalNo = x + a;
			// return sb.toString();
			return finalNo;
		} else {
			String[] y = x.split(rePrefix);
			String z = y[1];
			int a = Integer.parseInt(z);
			a = a + 1;
			String leftPadded = StringUtils.leftPad("" + a, 7, "0");
			// String finalNo=x+a;
			// return finalNo;
			StringBuilder sb = new StringBuilder();
			sb.append(rePrefix).append(leftPadded);
			return sb.toString();
			// String finalNo=y[0]+a;
			// return finalNo;

		}
	}
	public String getAllRecords() {
		StringBuilder jpql = new StringBuilder(" SELECT A FROM MaterialGateIn A ")
				.append(" INNER JOIN FETCH A.createdBy B ")
				.append(" INNER JOIN FETCH A.gateEntry C ")
				.append(" WHERE A.isActive = 'Y' ");
		return jpql.toString();
	}
	
	public String getAllRecordsExceptClosed() {
		StringBuilder jpql = new StringBuilder(" SELECT A FROM MaterialGateIn A ")
				.append(" INNER JOIN FETCH A.createdBy B ")
				.append(" INNER JOIN FETCH A.gateEntry C ")
				.append(" WHERE A.isActive = 'Y' and A.status != '"+AppBaseConstant.MATERIALCLOSED+"' and C.status != '"+AppBaseConstant.GATEENTRYCANCELED+"'");
		return jpql.toString();
	}

	
//	public String getmaterialGateEntryDetails(GateEntryReadDto dto) {
//		
//		StringBuilder query = new StringBuilder(" SELECT A FROM MaterialGateInList A ")
//				.append(" INNER JOIN FETCH A.gateEntryLine B ")
//				.append(" INNER JOIN FETCH A.materialGateIn C ")
//		        .append(" INNER JOIN FETCH C.gateEntry ge ");
//				String where =  getWhereClause(dto);
//				query.append(where);
//				
//				
//				 
//
//               
//
//		    
//		
//       return query.toString();
//}
//
//private String getWhereClause(GateEntryReadDto dto){
//	StringBuilder where = new StringBuilder();
//	where.append(" WHERE C.isActive = 'Y' ");
//	
//	if(dto.getReqNoFrom()!=null && dto.getReqNoTo()!=null){
//		where.append(" AND ge.reqNo BETWEEN :reqNoFrom AND :reqNoTo ");
//	}
//	if(dto.getReqNoFrom()!=null && dto.getReqNoTo()==null){
//		where.append(" AND ge.reqNo =:reqNoFrom ");
//	}
//	if(dto.getReqNoFrom()==null && dto.getReqNoTo()!=null){
//		where.append(" AND ge.reqNo =:reqNoTo ");
//	}
////	if(dto.getReqDateFrom()!=null && dto.getReqDateTo()!=null){
////		where.append("  AND A.date BETWEEN :fromDate AND :toDate ");
////	}
////	if(dto.getReqDateFrom()!=null && dto.getReqDateTo()==null){
////		where.append(" AND A.date =:fromDate ");
////		}
//// 		if(dto.getReqDateFrom()==null && dto.getReqDateTo()!=null){
////		where.append(" AND A.date =:toDate ");
////	}
//	if(dto.getStatus()!=null){
//		where.append(" AND C.status =:status ");
//	}
////	if(dto.getDocType()!=null){
////		where.append(" AND B.docType =:docType ");
////	}
//	if(dto.getPlant()!=null){
//		where.append(" AND ge.plant =:plant ");
//	}
//	
//	
//	return where.toString();
//}

	

}
