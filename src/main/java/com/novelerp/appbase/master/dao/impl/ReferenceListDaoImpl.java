package com.novelerp.appbase.master.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.novelerp.appbase.master.dao.ReferenceListDao;
import com.novelerp.appbase.master.dto.ReferenceListDto;
import com.novelerp.appbase.master.entity.ReferenceList;
import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.core.dao.impl.AbstractJpaDAO;

@Repository
public class ReferenceListDaoImpl extends AbstractJpaDAO<ReferenceList, ReferenceListDto> implements ReferenceListDao{
	
	@PostConstruct
	public void postConstruct() {
		setClazz(ReferenceList.class, ReferenceListDto.class);
	}

	private Map<String, Object> getParams(Long referenceId){
		Map<String, Object> params= new HashMap<>();
		params.put("code", referenceId);
		return params;
	}
	
	private StringBuilder getQueryForReferenceList(){		
		StringBuilder jpql = new StringBuilder();
		jpql.append(" Select rl from ReferenceList rl ")
		.append(" LEFT JOIN rl.reference r ")
		.append(" WHERE rl.reference.code = :code");
		return jpql;
	}
	
	@Override
	public List<ReferenceList> getReferenceList(Long refernceId) {
		StringBuilder jpql =  getQueryForReferenceList();
		return execute(jpql.toString(), getParams(refernceId), ReferenceList.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ReferenceList> getReferenceListByType(String type) {
		StringBuilder jpql=new StringBuilder(" Select rl from ReferenceList rl ")
				.append(" LEFT JOIN FETCH rl.reference r ")
				.append(" WHERE rl.reference.code = :code AND rl.isActive='Y' ORDER BY rl.name ");
		Query query=getEntityManager().createQuery(jpql.toString());
		query.setParameter("code", type);
		return query.getResultList();
	}
	
	public String getReferenceListByCode(){		
		StringBuilder jpql = new StringBuilder();
		jpql.append(" Select rl from ReferenceList rl ")
		.append(" WHERE rl.code = :code ");
		return jpql.toString();
	}
	
	public String getReferenceListByBPartner(){
		StringBuilder jpql = new StringBuilder();
		jpql.append(" Select rl from ReferenceList rl ")
		.append("LEFT JOIN FETCH rl.reference r")
		.append(" WHERE rl.reference.referenceId = :referenceId");
		
		return jpql.toString();
	}	
	
	public String getPaymentModeFortahdrPurchaseWT(){
		StringBuilder jpql=new StringBuilder();
		jpql.append(" Select rl from ReferenceList rl ")
		.append(" LEFT JOIN FETCH rl.reference r ")
		.append(" where rl.reference.code = :code AND rl.isActive='Y' AND rl.code NOT IN ('"+AppBaseConstant.PAYMENT_MODE_ISEXEMP+"','"+AppBaseConstant.PAYMENT_MODE_BG+"') ");
		return jpql.toString();
	}
	
	public String getPaymentModeFortahdrPurchasePT(){
		StringBuilder jpql=new StringBuilder();
		jpql.append(" Select rl from ReferenceList rl ")
		.append(" LEFT JOIN FETCH rl.reference r ")
		.append(" where rl.reference.code = :code AND rl.isActive='Y' AND rl.code NOT IN ('"+AppBaseConstant.PAYMENT_MODE_BG+"') ");
		return jpql.toString();
	}
	
	public String getPaymentModeForEMDWorks(){
		StringBuilder jpql=new StringBuilder();
		jpql.append(" Select rl from ReferenceList rl ")
		.append(" LEFT JOIN FETCH rl.reference r ")
		.append(" where rl.reference.code = :code AND rl.isActive='Y' AND rl.code NOT IN ('"+AppBaseConstant.PAYMENT_MODE_ISEXEMP+"') ");
		return jpql.toString();
	}
	
	public String getSchedulingListForTenders(){
		StringBuilder jpql=new StringBuilder();
		jpql.append(" Select rl from ReferenceList rl ")
		.append(" LEFT JOIN FETCH rl.reference r ")
		.append(" where rl.reference.code = :code  AND rl.code NOT IN ('"+AppBaseConstant.AUCTION_SCHEDULING+"') ");
		return jpql.toString();
	}
	
	public String getRefernceListData(){
		StringBuilder jpql=new StringBuilder();
		jpql.append(" Select rl from ReferenceList rl ")
		.append(" LEFT JOIN FETCH rl.reference r ")
		.append(" where rl.reference.code = :referenceCode  AND rl.code=:code ");
		return jpql.toString();
	}
	public String getRefernceListByValue(){
		StringBuilder jpql=new StringBuilder();
		jpql.append(" Select rl from ReferenceList rl ")
		.append(" LEFT JOIN FETCH rl.reference r ")
		.append(" where rl.reference.referenceId = :referenceCode  AND rl.code=:code ");
		return jpql.toString();
	}
	
}