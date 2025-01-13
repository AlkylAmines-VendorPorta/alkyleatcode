package com.novelerp.alkyl.dao.impl;

import java.sql.Timestamp;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.novelerp.alkyl.dao.VendorProfileHistoryDao;
import com.novelerp.alkyl.dto.VendorProfileHistoryDto;
import com.novelerp.alkyl.entity.VendorProfileHistory;
import com.novelerp.core.dao.impl.AbstractJpaDAO;
@Repository
public class VendorProfileDaoImpl extends AbstractJpaDAO<VendorProfileHistory, VendorProfileHistoryDto>
implements VendorProfileHistoryDao{

	@PersistenceContext
	private EntityManager em;

	@PostConstruct
	public void postConstruct() {

		setClazz(VendorProfileHistory.class, VendorProfileHistoryDto.class);
	}
	public String getVendorHistory() {
		StringBuilder jpql = new StringBuilder(" SELECT A FROM VendorProfileHistory A ")
				.append(" INNER JOIN FETCH A.partner B ")
				 .append(" WHERE A.isActive = 'Y' and B.bPartnerId=:partnerId ")
		.append(" order by A.created desc ");
		return jpql.toString();
	}
	
	@Override
	public boolean updateIsActiveStatus(Long partnerId) {
		try {
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			StringBuilder sb = new StringBuilder();
			sb.append(" UPDATE VendorProfileHistory A ")
					.append(" set A.isActive='N' ")
					.append(" where A.created<=:timestamp and A.bPartnerId=:partnerId");
			Query query = getEntityManager().createQuery(sb.toString());
			query.setParameter("timestamp", timestamp);
			query.setParameter("partnerId", partnerId);
			int result = query.executeUpdate();
			System.out.println("updateIsActiveStatus try block"+result);
			return result>0;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("updateIsActiveStatus catch block"+e.getMessage());
		}
		return true;
	}
}
