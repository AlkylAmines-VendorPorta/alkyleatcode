package com.novelerp.appbase.master.dao.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.novelerp.appbase.master.dao.FinancialYearDao;
import com.novelerp.appbase.master.dto.FinancialYearDto;
import com.novelerp.appbase.master.entity.FinancialYear;
import com.novelerp.core.dao.impl.AbstractJpaDAO;
/**
 * 
 * @author varsha
 *
 */
@Repository
public class FinancialYearDaoImpl extends AbstractJpaDAO<FinancialYear, FinancialYearDto> implements FinancialYearDao{

	@PostConstruct
	private void init() {
		setClazz(FinancialYear.class, FinancialYearDto.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<FinancialYear> getYear() {
		/*StringBuilder jpql=new StringBuilder(" Select y from FinancialYear y order by y.name desc ");
		Query query=getEntityManager().createQuery(jpql.toString());
		query.setMaxResults(3);*/
		StringBuilder jpql=new StringBuilder(" Select y from FinancialYear y ")
				.append(" WHERE (now() between y.validfrom and y.validTo) ")
				.append(" OR  (:lastYear between y.validfrom and y.validTo) ")
				.append(" OR  (:lastTwoYear between y.validfrom and y.validTo) ")
				.append(" order by y.name desc ");
		Query query=getEntityManager().createQuery(jpql.toString());
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.YEAR, -1);
		Date lastYear=cal.getTime();
		cal.add(Calendar.YEAR,-1);
		Date lastTwoYear=cal.getTime();
		query.setParameter("lastYear", lastYear);
		query.setParameter("lastTwoYear", lastTwoYear);
		return query.getResultList();
	}
}
