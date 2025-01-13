package com.novelerp.alkyl.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.novelerp.alkyl.dao.GateEntryDao;
import com.novelerp.alkyl.dto.GateEntryDto;
import com.novelerp.alkyl.dto.GateEntryReadDto;
import com.novelerp.alkyl.dto.POReadDto;
import com.novelerp.alkyl.entity.GateEntry;
import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.core.dao.impl.AbstractJpaDAO;
@Repository
public class GateEntryDaoImpl extends AbstractJpaDAO<GateEntry, GateEntryDto> implements GateEntryDao{
	@Override
	public void postConstruct() {
		setClazz(GateEntry.class, GateEntryDto.class);
	}
	@PersistenceContext
	private EntityManager em;
	@Override
	public String getNewReqNo() {
		String rePrefix = AppBaseConstant.REQPREFIX;
		StringBuilder query = new StringBuilder(" SELECT MAX(A.reqNo) FROM GateEntry A ")
				.append(" WHERE A.isActive = 'Y' AND CONCAT(A.reqNo,'') like '" + rePrefix + "%' ");
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
		StringBuilder jpql = new StringBuilder(" SELECT A FROM GateEntry A ")
				.append(" INNER JOIN FETCH A.createdBy B ").append(" WHERE A.isActive = 'Y' ");
		return jpql.toString();
	}
	
	public String getAllRecordsExceptClosed() {
		StringBuilder jpql = new StringBuilder(" SELECT A FROM GateEntry A ")
				.append(" INNER JOIN FETCH A.createdBy B ")
				.append(" INNER JOIN FETCH B.userDetails C ")
				.append("WHERE A.isActive = 'Y' and A.status != '"+AppBaseConstant.GATEENTRYCLOSED+"' and A.status != '"+AppBaseConstant.GECLOSED+"'");
		return jpql.toString();
	}
	
//	@Override
//	public String getGateEntryByFilter(GateEntryReadDto dto) {
//		StringBuilder jpql = new StringBuilder(" SELECT A FROM GateEntry A ");
////		jpql.append(" INNER JOIN FETCH A.GateEntryLinr B ");
////				jpql.append(" INNER JOIN FETCH A.partner B ");
////				jpql.append(" LEFT JOIN FETCH A.reqby D ");
////				jpql.append(" LEFT JOIN FETCH A.poAtt C ");
//				String where =  getWhereClause(dto);
//		   		jpql.append(where);
//		return jpql.toString();
//	}
//	private String getWhereClause(GateEntryReadDto dto){
//   		StringBuilder where = new StringBuilder();
//   		where.append(" WHERE A.isActive = 'Y' ");
//   		
//   		if(dto.getReqNoFrom()!=null && dto.getReqNoTo()!=null){
//   			where.append(" AND A.reqNo BETWEEN :reqNoFrom AND :reqNoTo ");
//   		}
//   		if(dto.getReqNoFrom()!=null && dto.getReqNoTo()==null){
//   			where.append(" AND A.reqNo =:reqNoFrom ");
//   		}
//   		if(dto.getReqNoFrom()==null && dto.getReqNoTo()!=null){
//   			where.append(" AND A.reqNo =:reqNoTo ");
//   		}
//   	//	if(dto.getReqDateFrom()!=null && dto.getReqDateTo()!=null){
//   	//		where.append("  AND A.date BETWEEN :fromDate AND :toDate ");
//   	//	}
//   	//	if(dto.getReqDateFrom()!=null && dto.getReqDateTo()==null){
//   	//		where.append(" AND A.date =:fromDate ");
//   //		}
//  // 		if(dto.getReqDateFrom()==null && dto.getReqDateTo()!=null){
//   	//		where.append(" AND A.date =:toDate ");
////   		}
//   		if(dto.getStatus()!=null){
//   			where.append(" AND A.status =:status ");
//   		}
//   		if(dto.getDocType()!=null){
//   			where.append(" AND A.docType =:docType ");
//   		}
//   		if(dto.getPlant()!=null){
//   			where.append(" AND A.plant =:plant ");
//   		}
//   		
//   		
//   		return where.toString();
// 	}

}
