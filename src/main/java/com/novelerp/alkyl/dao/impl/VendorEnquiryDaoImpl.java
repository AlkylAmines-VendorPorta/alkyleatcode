package com.novelerp.alkyl.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.novelerp.alkyl.dao.VendorEnquiryDao;
import com.novelerp.alkyl.dto.EnquiryDto;
import com.novelerp.alkyl.entity.Enquiry;
import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.core.dao.impl.AbstractJpaDAO;
import com.novelerp.eat.dto.BidderDto;
@Repository
public class VendorEnquiryDaoImpl extends AbstractJpaDAO<Enquiry, EnquiryDto> implements VendorEnquiryDao {

	@Override
	public void postConstruct() {
		setClazz(Enquiry.class, EnquiryDto.class);
	}
	@PersistenceContext
	private EntityManager em;
	
	
	
//	@Override
//	public String getNewEnqNo() {
//		String enqPrefix = AppBaseConstant.ENQPREFIX;
//		StringBuilder query = new StringBuilder(" SELECT MAX(A.enqNo) FROM Enquiry A ")
//				.append(" WHERE A.isActive = 'Y' AND CONCAT(A.enqNo,'') like '" + enqPrefix + "%' ");
//		Query q = em.createQuery(query.toString());
//		String x = (String) q.getSingleResult();
//
//		if (x == null) {
//
//			x = enqPrefix + "000000";
//			String[] y = x.split(enqPrefix);
//			String z = y[1];
//			int a = Integer.parseInt(z);
//			a = a + 1;
//			String leftPadded = StringUtils.leftPad("" + a, 7, "0");
//			// StringBuilder sb = new StringBuilder();
//			// sb.append(x).append(a);
//			String finalNo = x + a;
//			// return sb.toString();
//			return finalNo;
//		} else {
//			String[] y = x.split(enqPrefix);
//			String z = y[1];
//			int a = Integer.parseInt(z);
//			a = a + 1;
//			String leftPadded = StringUtils.leftPad("" + a, 7, "0");
//			// String finalNo=x+a;
//			// return finalNo;
//			StringBuilder sb = new StringBuilder();
//			sb.append(enqPrefix).append(leftPadded);
//			return sb.toString();
//			// String finalNo=y[0]+a;
//			// return finalNo;
//
//		}
//	}
	
	@Override
	public String getNewEnqNo(String Doctype) {
//		String enqPrefix = AppBaseConstant.ENQPREFIX;
		String enqPrefix="";
		if(Doctype.equals("ZENG")) {
			enqPrefix="23";
		}else if(Doctype.equals("ZPKG")) {
			enqPrefix="22";
		}
		else if(Doctype.equals("ZPRJ")) {
			enqPrefix="25";
		}
		else if(Doctype.equals("ZRAM")) {
			enqPrefix="21";
		}
		else if(Doctype.equals("ZSCH")) {
			enqPrefix="24";
		}
		else if(Doctype.equals("ZSEJ")) {
			enqPrefix="27";
		}else if(Doctype.equals("ZSER")) {
			enqPrefix="26";
		}
		
		StringBuilder query = new StringBuilder(" SELECT MAX(A.enqNo) FROM Enquiry A ")
				.append(" WHERE A.isActive = 'Y' AND CONCAT(A.enqNo,'') like '" + enqPrefix + "%' ");
		Query q = em.createQuery(query.toString());
		String x = (String) q.getSingleResult();

		if (x == null) {

			x = enqPrefix + "0000000";
			String[] y = x.split(enqPrefix);
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
			String[] y = x.split(enqPrefix);
			String z = y[1];
			int a = Integer.parseInt(z);
			a = a + 1;
			String leftPadded = StringUtils.leftPad("" + a, 8, "0");
			// String finalNo=x+a;
			// return finalNo;
			StringBuilder sb = new StringBuilder();
			sb.append(enqPrefix).append(leftPadded);
			return sb.toString();
			// String finalNo=y[0]+a;
			// return finalNo;

		}
	}

	
	 public String getEnquiryForNegotiate(){
			
			StringBuilder jpql= new StringBuilder();
			jpql.append(" Select a from Enquiry a ");
			jpql.append(" INNER JOIN FETCH a.createdBy b ");
			jpql.append(" where a.code='open' order by a.enquiryId desc ");
			return jpql.toString();
		}
	 public String getCloseEnquiry(){
			
			StringBuilder jpql= new StringBuilder();
			jpql.append(" Select a from Enquiry a ");
			jpql.append(" INNER JOIN FETCH a.createdBy b ");
			jpql.append(" where a.code='close' ");
			return jpql.toString();
		}
	 public String getEnquiryForQCF(){
			
			StringBuilder jpql= new StringBuilder();
			jpql.append(" Select a from Enquiry a ");
			jpql.append(" INNER JOIN FETCH a.createdBy b ");
			jpql.append(" Order By a.enquiryId desc ");
			/*jpql.append(" where :currentDate>a.bidEndDate ");*/
			return jpql.toString();
		}



	 
	 
	 
}
