package com.novelerp.appbase.master.dao.impl;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import com.novelerp.appbase.master.dao.PartnerOrgProductDao;
import com.novelerp.appbase.master.dto.PartnerOrgProductDto;
import com.novelerp.appbase.master.entity.PartnerOrgProduct;
import com.novelerp.core.dao.impl.AbstractJpaDAO;
/**
 * 
 * @author Vivek Birdi
 *
 */
@Repository
public class PartnerOrgProductDaoImpl extends AbstractJpaDAO<PartnerOrgProduct, PartnerOrgProductDto> implements PartnerOrgProductDao{

	@PostConstruct
	public void init() {
		setClazz(PartnerOrgProduct.class, PartnerOrgProductDto.class);
	}
	
	public String getOrgProductQuery(){
		StringBuilder query =  new StringBuilder();
		query.append("SELECT e FROM PartnerOrgProduct e")
		.append(" LEFT JOIN FETCH e.createdBy c")
		.append(" LEFT JOIN FETCH e.updatedBy u")
		.append(" LEFT JOIN FETCH e.partnerOrg o")
		.append(" LEFT JOIN FETCH e.material m")
		.append(" LEFT JOIN FETCH m.hsnCode hc ")
		.append(" LEFT JOIN FETCH e.uom uo")	
		.append(" LEFT JOIN FETCH e.partnerOrgRegistration r")
		.append(" LEFT JOIN FETCH e.partnerOrgBIS b")
		.append(" LEFT JOIN FETCH e.industrialLicenceCopy lc")
		.append(" WHERE e.partnerOrg.partnerOrgId = :partnerOrgId");
		return query.toString();
	}
	
	public String getExmptedItemListByTahdr(){
		StringBuilder jpql= new StringBuilder();
		jpql.append(" SELECT pop from PartnerOrgProduct pop ")
		.append(" INNER JOIN FETCH pop.material prod ")
		.append(" INNER JOIN TAHDRMaterial tm with tm.material.materialId=prod.materialId ")
		.append(" INNER JOIN tm.tahdrDetail td ")
		.append(" INNER JOIN td.tahdr t ")
		.append(" INNER JOIN pop.partnerOrg po ")
		.append(" INNER JOIN pop.partner bp ")
		.append(" WHERE t.tahdrId=:tahdrId and bp.bPartnerId=:partnerId and td.isActive='Y' and po.partnerOrgId=:partnerOrgId ");
		return jpql.toString();
	}
	public String getProductQueryForUniqueMaterial()
	{
		StringBuilder jpql= new StringBuilder(" select p from PartnerOrgProduct p ");
		jpql.append(" WHERE p.partnerOrg.partnerOrgId=:partnerOrgId AND p.material.materialId=:materialId ");
		return jpql.toString();
	}
}
