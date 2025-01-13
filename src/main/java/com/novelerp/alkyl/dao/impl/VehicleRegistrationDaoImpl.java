package com.novelerp.alkyl.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.novelerp.alkyl.controller.OutwardReadDto;
import com.novelerp.alkyl.dao.VehicleRegistrationDao;
import com.novelerp.alkyl.dto.ASNReadDto;
import com.novelerp.alkyl.dto.VehicleRegistrationDto;
import com.novelerp.alkyl.entity.VehicleRegistration;
import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.core.dao.impl.AbstractJpaDAO;

@Repository
public class VehicleRegistrationDaoImpl extends AbstractJpaDAO<VehicleRegistration, VehicleRegistrationDto> implements VehicleRegistrationDao{
 
	@Override
	public void postConstruct() {
		setClazz(VehicleRegistration.class, VehicleRegistrationDto.class);
	}
	@PersistenceContext
	private EntityManager em;
	@Override
	public String getNewReqNo() {
		String rePrefix = AppBaseConstant.REQPREFIX;
		StringBuilder query = new StringBuilder(" SELECT MAX(A.requestNo) FROM VehicleRegistration A ")
				.append(" WHERE A.isActive = 'Y' AND CONCAT(A.requestNo,'') like '" + rePrefix + "%' ");
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

	public String queryToGetVehicleRegistration(){
		StringBuilder jpql = new StringBuilder(" SELECT A FROM VehicleRegistration A ")
		/*.append(" LEFT JOIN FETCH A.soldToParty C ")
		.append(" LEFT JOIN FETCH A.trasnporter E ")
		.append(" LEFT JOIN FETCH A.shipToParty G ")*/
	//	.append(" LEFT JOIN FETCH A.meCode H ")
		/*.append(" LEFT JOIN FETCH A.driverPic G ")
		.append(" LEFT JOIN FETCH A.docPic H ")*/
		.append(" WHERE A.isActive='Y' and A.status!='"+AppBaseConstant.STATUSVEHICLEGATEOUT+"'");
//				.append(" WHERE A.isActive='Y'");
		jpql.append( " ORDER BY A.vehicleRegistationId DESC");
		return jpql.toString();
	}
	
	public String queryToGetVehicleRegistrationreqno(){
		StringBuilder jpql = new StringBuilder(" SELECT A FROM VehicleRegistration A ")
		/*.append(" LEFT JOIN FETCH A.soldToParty C ")
		.append(" LEFT JOIN FETCH A.trasnporter E ")
		.append(" LEFT JOIN FETCH A.shipToParty G ")*/
	//	.append(" LEFT JOIN FETCH A.meCode H ")
		/*.append(" LEFT JOIN FETCH A.driverPic G ")
		.append(" LEFT JOIN FETCH A.docPic H ")*/
		.append(" WHERE A.isActive='Y' and A.saleOrderNo=:saleorderno");
		jpql.append( " ORDER BY A.vehicleRegistationId DESC");
		return jpql.toString();
	}
	
	public String queryToGetVehicleRegistrationreqnobypo(){
		StringBuilder jpql = new StringBuilder(" SELECT A FROM VehicleRegistration A ")
		/*.append(" LEFT JOIN FETCH A.soldToParty C ")
		.append(" LEFT JOIN FETCH A.trasnporter E ")
		.append(" LEFT JOIN FETCH A.shipToParty G ")*/
	//	.append(" LEFT JOIN FETCH A.meCode H ")
		/*.append(" LEFT JOIN FETCH A.driverPic G ")
		.append(" LEFT JOIN FETCH A.docPic H ")*/
		.append(" WHERE A.isActive='Y' and A.poNo=:poNo");
		jpql.append( " ORDER BY A.vehicleRegistationId DESC");
		return jpql.toString();
	}
	
	public String queryToGetVehicleRegistrationnicergloabe(){
		StringBuilder jpql = new StringBuilder(" SELECT A FROM VehicleRegistration A ")
		/*.append(" LEFT JOIN FETCH A.soldToParty C ")
		.append(" LEFT JOIN FETCH A.trasnporter E ")
		.append(" LEFT JOIN FETCH A.shipToParty G ")*/
	//	.append(" LEFT JOIN FETCH A.meCode H ")
		/*.append(" LEFT JOIN FETCH A.driverPic G ")
		.append(" LEFT JOIN FETCH A.docPic H ")*/
		.append(" WHERE A.isActive='Y' and A.vehicleRegistrationNo=:vehicleRegistrationNo and A.requestNo=:requestNo");
		jpql.append( " ORDER BY A.vehicleRegistationId DESC");
		return jpql.toString();
	}
	
	public String getVehicleRegById(){
		StringBuilder jpql = new StringBuilder(" SELECT A FROM VehicleRegistration A ")
		/*.append(" LEFT JOIN FETCH A.shipToParty B ")
		.append(" LEFT JOIN FETCH A.soldToParty C ")
		.append(" LEFT JOIN FETCH A.trasnporter E ")*/
		//.append(" LEFT JOIN FETCH A.meCode F ")
		/*.append(" LEFT JOIN FETCH A.driverPic G ")*/
		.append(" LEFT JOIN FETCH A.docPic H ")
		.append(" WHERE A.isActive='Y' and A.vehicleRegistationId=:vehicleRegistationId ");
		jpql.append( " ORDER BY A.vehicleRegistationId DESC");
		return jpql.toString();
	}
	public String getVehicleRegBySaleOrder(){
		StringBuilder jpql = new StringBuilder(" SELECT A FROM VehicleRegistration A ")
		.append(" WHERE A.isActive='Y' and A.saleOrderNo=:saleOrderID ");
		return jpql.toString();
	}

	public  String getOutwardReportlist(OutwardReadDto dto) {
		StringBuilder jpql = new StringBuilder(" SELECT Distinct(A) FROM VehicleRegistration A ")
		.append("LEFT JOIN FETCH A.createdBy B")		
	    .append(" LEFT JOIN FETCH B.userDetails C ")
	    .append(" LEFT JOIN FETCH A.reportedby rb")
        .append(" LEFT JOIN FETCH rb.userDetails H ")
		.append(" LEFT JOIN FETCH A.gateInby gb")
        .append(" LEFT JOIN FETCH gb.userDetails F ");
	    
		String where =  getWhereClause(dto);
		jpql.append(where);
		System.out.println(jpql.toString());
	
		return jpql.toString();
		
	}
	private String getWhereClause(OutwardReadDto dto){
		
		
   		StringBuilder where = new StringBuilder();
   		where.append(" WHERE A.isActive = 'Y' AND A.requestNo IS NOT NULL");
   		
   		
   		if(dto.getSalesOrderNoFrom()!=null && dto.getSalesOrderNoTo()!=null){
   			where.append(" AND A.saleOrderNo BETWEEN :salesOrderNoFrom AND :salesOrderNoTo ");
   		}
   		if(dto.getSalesOrderNoFrom()!=null && dto.getSalesOrderNoTo()==null){
   			where.append(" AND A.saleOrderNo =:salesOrderNoFrom ");
   		}
   		if(dto.getSalesOrderNoFrom()==null && dto.getSalesOrderNoTo()!=null){
   			where.append(" AND A.saleOrderNo =:salesOrderNoTo ");
   		}
   		if(dto.getRequestNoFrom()!=null && dto.getRequestNoTo()!=null){
   			where.append(" AND A.requestNo BETWEEN :requestNoFrom AND :requestNoTo ");
   		}
   		if(dto.getRequestNoFrom()!=null && dto.getRequestNoTo()==null){
   			where.append(" AND A.requestNo =:requestNoFrom ");
   		}
   		if(dto.getRequestNoFrom()==null && dto.getRequestNoTo()!=null){
   			where.append(" AND A.requestNo =:requestNoTo ");
   			
   		}
   		
   		if(dto.getRequestDateFrom()!=null && dto.getRequestDateTo()!=null){
   			where.append(" AND A.created BETWEEN :requestDateFrom AND :requestDateTo ");
   		}
   		if(dto.getRequestDateFrom()!=null && dto.getRequestDateTo()==null){
   			where.append(" AND A.created =:requestDateFrom ");
   		}
   		if(dto.getRequestDateFrom()==null && dto.getRequestDateTo()!=null){
   			where.append(" AND A.created =:requestDateTo ");
   		}
   		if(dto.getStatus()!=null && !dto.getStatus().equals("")){
   			
   			where.append(" AND A.status = (:status) ");  			
   		}
   		
   		if(dto.getFreightScope()!=null && !dto.getFreightScope().equals("")){
   			where.append(" AND A.freightScope =:freightScope");
   		}
   		
   		if(dto.getPlant()!=null && !dto.getPlant().equals("")){
   			where.append(" AND A.plant =:plant");
   		}
   		
   		return where.toString();
	}

	
}
