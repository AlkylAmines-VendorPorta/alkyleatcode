package com.novelerp.alkyl.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.novelerp.alkyl.dao.GateEntryDao;
import com.novelerp.alkyl.dao.PrintGateEntryDetailDao;
import com.novelerp.alkyl.dto.GateEntryDto;
import com.novelerp.alkyl.dto.PrintGateEntryDetailDto;
import com.novelerp.alkyl.entity.GateEntry;
import com.novelerp.alkyl.entity.PrintGateEntryDetail;
import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.core.dao.impl.AbstractJpaDAO;
@Repository
public class PrintGateEntryDetailDaoImpl extends AbstractJpaDAO<PrintGateEntryDetail, PrintGateEntryDetailDto> implements PrintGateEntryDetailDao{
	@Override
	public void postConstruct() {
		setClazz(PrintGateEntryDetail.class, PrintGateEntryDetailDto.class);
	}
	@PersistenceContext
	private EntityManager em;
	@Override
	public String getNewDocType() {
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
	
	
	public String getFormPrintDetails() {
		StringBuilder jpql = new StringBuilder(" SELECT A FROM PrintGateEntryDetail A ")
				.append(" WHERE A.docType =:docType");
		return jpql.toString();
		
	}
	

}
