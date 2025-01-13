package com.novelerp.appbase.master.dao.impl;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import com.novelerp.appbase.master.dao.PartnerTradingItemDao;
import com.novelerp.appbase.master.dto.PartnerTradingItemDto;
import com.novelerp.appbase.master.entity.PartnerTradingItem;
import com.novelerp.core.dao.impl.AbstractJpaDAO;

/**
 * 
 * @author Aman Sahu
 *
 */
@Repository
public class PartnerTradingItemDaoImpl extends AbstractJpaDAO<PartnerTradingItem, PartnerTradingItemDto> implements PartnerTradingItemDao{

	@PostConstruct
	private void init() {
		setClazz(PartnerTradingItem.class, PartnerTradingItemDto.class);
	}
	
	public String getPartnerItemManufacturerQuery(){
		StringBuilder query = new StringBuilder();
		query.append("SELECT e FROM PartnerTradingItem e")
		.append(" LEFT JOIN FETCH e.partnerItemManufacutrer if ")
		.append(" WHERE e.partnerItemManufacutrer.partnerItemManufacturerId= :partnerItemManufacturerId");		
		return query.toString();
	}
	public String getTradingItemQueryForUniqueMaterial()
	{
		StringBuilder query = new StringBuilder("SELECT t FROM PartnerTradingItem t");
		query.append(" WHERE t.partnerItemManufacutrer.partnerItemManufacturerId=:partnerItemManufacturerId AND t.material.materialId=:materialId ");
		return query.toString();
	}
	
}