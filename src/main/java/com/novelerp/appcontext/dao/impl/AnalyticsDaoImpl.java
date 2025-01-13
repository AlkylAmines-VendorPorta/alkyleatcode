package com.novelerp.appcontext.dao.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.appcontext.dao.AnalyticsDao;
import com.novelerp.appcontext.dao.QueryMasterDao;
import com.novelerp.appcontext.dto.AnalyticsRevenueDto;
import com.novelerp.appcontext.entity.QueryMaster;
import com.novelerp.core.dao.impl.AbstractJpaDAO;
import com.novelerp.eat.dto.PaymentDetailDto;
import com.novelerp.eat.entity.PaymentDetail;
@Repository
public class AnalyticsDaoImpl extends AbstractJpaDAO<PaymentDetail, PaymentDetailDto>implements AnalyticsDao{

	@Autowired
	private QueryMasterDao queryMasterDao;
	private static final Logger LOG = LoggerFactory.getLogger(AnalyticsDao.class);
	@Override
	public List<AnalyticsRevenueDto> getRegistrationRevenue( Long MChargeID,Long fyId) {
		List<AnalyticsRevenueDto> analyticsRevenueDto2 = new ArrayList<AnalyticsRevenueDto>();
		Map<String, Object> params= new HashMap<>();
		String queryName=AppBaseConstant.query_for_revenue_graph;
		try{
		QueryMaster queryForrevenueFraph= queryMasterDao.findOne("name ='"+queryName+"' ", params, null);
		
		params.put("@MChargeID@",Long.toString(MChargeID));
		params.put("@financialYear@",fyId.toString());
		String content=queryForrevenueFraph.getValue();
		for(Map.Entry<String, Object> entry : params.entrySet()){
			String key = entry.getKey();
			String value = (String) entry.getValue();
			content=content.replace(key, value);
		}
		Query query=getEntityManager().createNativeQuery(content);
		
		List<Object[]> count= query.getResultList();
		for(Object[] o: count){
			AnalyticsRevenueDto analytics = new AnalyticsRevenueDto();
			analytics.setLabel(String.valueOf(o[0]));
			analytics.setY(new BigDecimal(String.valueOf(o[1])));
			analyticsRevenueDto2.add(analytics);
		}
		}catch (Exception e) {
			LOG.error(e.toString());
		}
		return analyticsRevenueDto2;
	}
	@Override
	public List<AnalyticsRevenueDto> getTotalSavingDetails(Long materialID,
			Long departmentID,String TahdrType, String IsAuction,Long fyId) {
		List<AnalyticsRevenueDto> analyticsRevenueDto2 = new ArrayList<AnalyticsRevenueDto>();
		 Map<String, Object> params= new HashMap<>();
		 String queryName=AppBaseConstant.Query_For_TotalSaving_Graph;
		 try{
			QueryMaster queryForrevenueFraph= queryMasterDao.findOne("name ='"+queryName+"' ", params, null);
			if(materialID == 0){
				params.put("@MaterilaID@"," ");
			}else{
			params.put("@MaterilaID@","AND G.M_MATERIAL_ID= "+Long.toString(materialID));
			}
			if(departmentID == 0){
				params.put("@DepartmentID@"," ");
			}else{
			params.put("@DepartmentID@","AND I.M_DEPARTMENT_ID= "+Long.toString(departmentID));
			}
			params.put("@financialYear@",fyId.toString());
			params.put("@TahdrType@",TahdrType);
			params.put("@isAuction@",IsAuction);
			String content=queryForrevenueFraph.getValue();
			for(Map.Entry<String, Object> entry : params.entrySet()){
				String key = entry.getKey();
				String value = (String) entry.getValue();
				content=content.replace(key, value);
			}
			
			LOG.info(content);
			
			Query query=getEntityManager().createNativeQuery(content);
			
			List<Object[]> count= query.getResultList();
			
			for(Object[] o: count){
				AnalyticsRevenueDto analytics = new AnalyticsRevenueDto();
				analytics.setY(new BigDecimal(String.valueOf(o[0])));
				analytics.setLabel(String.valueOf(o[1]));
				
				analyticsRevenueDto2.add(analytics);
			}
		 }catch (Exception e) {
			 LOG.error(e.toString());
		}
			return analyticsRevenueDto2;
	}
	
	/* public String getTotalRevenue(Date StartDate, Date EndDate, Long MChargeID){
		
		   	StringBuilder jpql= new StringBuilder();
		   	jpql.append(" SELECT * from (select A.MONTH_DISPLAY,  ");
			jpql.append(" case when sum(C.amount) is null then 0 else sum(C.amount) end as amount , ");
			jpql.append(" CASE WHEN A.MONTH_VALUE<4 THEN A.MONTH_VALUE+12 ELSE A.MONTH_VALUE END as MONTH_VALUE ");
			jpql.append(" from  WWV_FLOW_MONTHS_MONTH A ");
			jpql.append(" LEFT OUTER JOIN (select  to_char(B.created,'MONTH') as month ,amount  ");
			jpql.append(" from T_PAYMENT B  ");
			jpql.append("   where M_CHARGE_ID='"+MChargeID+"' and  created BETWEEN '"+formattedDate1+"' and '"+formattedDate2+"') C ON ( UPPER(A.MONTH_DISPLAY) =UPPER(C.month))  ");
			jpql.append(" group by A.MONTH_DISPLAY,A.MONTH_VALUE) D order by D.MONTH_VALUE asc ");
			
		   	return jpql.toString();
		   	}
*/
	 public String getDateFormated(Date utilDate){
		 java.sql.Date unFormatedDate = new java.sql.Date(utilDate.getTime());
		 SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

		 String formattedDate = formatter.format(unFormatedDate);
		return formattedDate;
		 
	 }

}
