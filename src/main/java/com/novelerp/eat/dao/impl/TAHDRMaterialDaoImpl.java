/**
 * @author Ankush
 */

package com.novelerp.eat.dao.impl;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.novelerp.core.dao.impl.AbstractJpaDAO;
import com.novelerp.eat.dao.TAHDRMaterialDao;
import com.novelerp.eat.dto.TAHDRMaterialDto;
import com.novelerp.eat.entity.TAHDRMaterial;

@Repository
public class TAHDRMaterialDaoImpl extends AbstractJpaDAO<TAHDRMaterial, TAHDRMaterialDto> implements TAHDRMaterialDao {

	@PostConstruct
	public void postConstruct() {
		setClazz(TAHDRMaterial.class, TAHDRMaterialDto.class);
	}
	
	public String checkExistingMaterialQuery(){
		StringBuilder jpql= new StringBuilder();
		jpql.append(" select tm from TAHDRMaterial tm "
				+ " LEFT JOIN FETCH tm.tahdrDetail td "
				+ " LEFT JOIN FETCH tm.material m "
				+ " where tm.material.materialId =:materialId and tm.tahdrDetail.tahdrDetailId= :tahdrDetailId");
				/*+ " and tm.materialTypeCode= :materialTypeCode ");*/
		return jpql.toString();
	}

	private StringBuilder getTahdrMaterialListQuery(){
		StringBuilder jpql= new StringBuilder();
		jpql.append(" select tm from TAHDRMaterial tm ");
		jpql.append(" LEFT JOIN FETCH tm.materialVersion mv ");
		jpql.append(" LEFT JOIN FETCH mv.material refMv ");
		jpql.append(" LEFT JOIN FETCH tm.material m ");
		jpql.append(" LEFT JOIN FETCH tm.bomVersion bv");
		jpql.append(" LEFT JOIN FETCH m.uom uom ");
		jpql.append(" LEFT JOIN FETCH m.hsnCode hsn  ");
		jpql.append(" LEFT JOIN FETCH tm.tahdrDetail td ");
		jpql.append(" where tm.tahdrDetail.tahdrDetailId= :tahdrDetailId ORDER BY m.name");
		return jpql;
	}
	
	public String getTahdrMaterialByIdQuery(){
		StringBuilder jpql= new StringBuilder();
		jpql.append(" select tm from TAHDRMaterial tm "
				+ " LEFT JOIN FETCH tm.material m "
				+ " LEFT JOIN FETCH m.uom uom "
				+ " LEFT JOIN FETCH tm.bomVersion bv "
				+ " LEFT JOIN FETCH tm.tahdrDetail td "
				+ " LEFT JOIN FETCH td.tahdr t "
				+ " where tm.tahdrMaterialId= :tahdrMaterialId ");
		return jpql.toString();
	}
	
	@Override
	public List<TAHDRMaterial> getTahdrMaterialList(Long tahdrDetailId) {
		StringBuilder jpql= getTahdrMaterialListQuery();
		TypedQuery<TAHDRMaterial> q = getEntityManager().createQuery(jpql.toString(), TAHDRMaterial.class);
		q.setParameter("tahdrDetailId", tahdrDetailId);
		List<TAHDRMaterial> materialList= q.getResultList();
		return materialList;
	}
	
	@Override
	public List<TAHDRMaterial> getTahdrMaterialListByTahdrId(Long tahdrId) {
		StringBuilder jpql= new StringBuilder();
		jpql.append(" select tm from TAHDRMaterial tm ");
		jpql.append(" LEFT JOIN FETCH tm.materialVersion mv ");
		jpql.append(" LEFT JOIN FETCH mv.material refMv ");
		jpql.append(" LEFT JOIN FETCH tm.material m ");
		jpql.append(" LEFT JOIN FETCH tm.bomVersion bv");
		jpql.append(" LEFT JOIN FETCH m.uom uom ");
		jpql.append(" LEFT JOIN FETCH tm.tahdrDetail td ");
		jpql.append(" where tm.tahdrDetail.tahdr.tahdrId= :tahdrId AND td.isActive='Y' ");
		TypedQuery<TAHDRMaterial> q = getEntityManager().createQuery(jpql.toString(), TAHDRMaterial.class);
		q.setParameter("tahdrId", tahdrId);
		List<TAHDRMaterial> materialList= q.getResultList();
		return materialList;
	}
	
	public String getQueryTahdrMaterialListByTahdrId() {
		StringBuilder jpql= new StringBuilder();
		jpql.append(" select tm from TAHDRMaterial tm ");
		jpql.append(" LEFT JOIN FETCH tm.materialVersion mv ");
		jpql.append(" LEFT JOIN FETCH mv.material refMv ");
		jpql.append(" LEFT JOIN FETCH tm.material m ");
		jpql.append(" LEFT JOIN FETCH tm.bomVersion bv");
		jpql.append(" LEFT JOIN FETCH m.uom uom ");
		jpql.append(" LEFT JOIN FETCH tm.tahdrDetail td ");
		jpql.append(" where tm.tahdrDetail.tahdr.tahdrId= :tahdrId AND td.isActive='Y' ");
		return jpql.toString();
	}

	/* (non-Javadoc)
	 * @see com.novelerp.eat.dao.TAHDRMaterialDao#getTahdrMaterialById(java.lang.Long)
	 */
	@Override
	public TAHDRMaterial getTahdrMaterialById(Long tahdrMaterialId) {
		StringBuilder jpql= new StringBuilder(getTahdrMaterialByIdQuery());
		TypedQuery<TAHDRMaterial> q = getEntityManager().createQuery(jpql.toString(), TAHDRMaterial.class);
		q.setParameter("tahdrMaterialId", tahdrMaterialId);
		TAHDRMaterial material= q.getSingleResult();
		return material;
	}
	
	/**
	 * @author Vivek Birdi
	 * @return query
	 */
	public String getRefTahdrMaterialsQuery(){
		StringBuilder jpql= new StringBuilder();
		jpql.append(" select tm from TAHDRMaterial tm ")
		.append(" INNER JOIN FETCH tm.tahdrDetail td ")
		.append(" INNER JOIN FETCH td.tahdr th")
		.append(" INNER JOIN FETCH tm.materialVersion mv ")
		.append(" INNER JOIN FETCH mv.material m ")
		.append(" INNER JOIN FETCH mv.versionSpecification vs ")
		.append(" where th.tahdrId= :tahdrId and td.isActive ='Y' ");
		return jpql.toString();
	}

	private String copyTahdrMaterials(){
		StringBuilder jpql=new StringBuilder();
		jpql.append(" Insert into T_TAHDR_MATERIAL (T_TAHDR_MATERIAL_ID,CREATED,ISACTIVE,UPDATED, "
				+ " ABOUT_SPEC_CODE,MATERIAL_DESCRIPTION,MATERIAL_TYPE_CODE,QUANTITY, "
				+ " SPECIFICATION_VERSION,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,T_MATERIAL_ID, "
				+ " M_MATERIAL_VERSION_ID,T_TAHDR_DETAIL_ID,M_BOM_VERSION_ID) ");
		jpql.append( " SELECT T_TAHDRMATERIAL_SEQ.NEXTVAL,CREATED,ISACTIVE,UPDATED, "
				+ " ABOUT_SPEC_CODE,MATERIAL_DESCRIPTION,MATERIAL_TYPE_CODE,QUANTITY, "
				+ " SPECIFICATION_VERSION,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,T_MATERIAL_ID, "
				+ " M_MATERIAL_VERSION_ID,?,M_BOM_VERSION_ID from T_TAHDR_MATERIAL " );
		jpql.append(" WHERE t_tahdr_detail_id=? ");
		return jpql.toString();
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public int copyTahdrMaterialsToNewVersion(Long newTahdrDetailId,Long oldTahdrDetailId){
		String jpql=copyTahdrMaterials();
		Query query = getEntityManager().createNativeQuery(jpql);
		query.setParameter(1, newTahdrDetailId);
		query.setParameter(2, oldTahdrDetailId);
		int count= query.executeUpdate();
		return count;
	}

	@Override
	public TAHDRMaterial updateTahdrMaterialQuantity(Long balanceQty) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public int updateBaseRate(Long tahdrId){
		StringBuilder jpql= new StringBuilder();
		jpql.append("UPDATE T_TAHDR_MATERIAL TM SET BASE_PRICE_RATE =(SELECT PB.FDD_RATE_WITH_GST ");
		jpql.append("	FROM T_PRICE_BID PB ");
		jpql.append("	INNER JOIN T_ITEM_BID IB ON IB.T_ITEM_BID_ID=PB.T_ITEM_BID_ID ");
		jpql.append("	INNER JOIN T_TAHDR_MATERIAL TM1 ON TM1.T_TAHDR_MATERIAL_ID=IB.T_TAHDR_MATERIAL_ID ");
		jpql.append("	WHERE IB.IS_LOWEST_BID='Y' AND TM1.T_TAHDR_MATERIAL_ID =TM.T_TAHDR_MATERIAL_ID AND PB.M_MATERIAL_SPECIFICATION_ID IS NULL) ");
		jpql.append("   WHERE TM.T_TAHDR_DETAIL_ID = (SELECT TD.T_TAHDR_DETAIL_ID FROM T_TAHDR_DETAIL TD ");
		jpql.append("   INNER JOIN T_TAHDR T ON T.T_TAHDR_ID=TD.T_TAHDR_ID ");
		jpql.append("   WHERE T.T_TAHDR_ID=:tahdrId AND TD.ISACTIVE='Y') ");
		Query query=getEntityManager().createNativeQuery(jpql.toString());
		query.setParameter("tahdrId", tahdrId);
		int count= query.executeUpdate();
		return count;
	}
	
	@Override
	@Transactional
	public long getMaterialItemTotalCountQry(Long tahdrDetailId){		
		StringBuilder jpql=new StringBuilder(" Select SUM(tm.quantity) from TAHDRMaterial tm ")
				.append(" LEFT JOIN  tm.tahdrDetail td ")
				.append(" where td.tahdrDetailId=:tahdrDetailId ");
		Query q = getEntityManager().createQuery(jpql.toString());
		q.setParameter("tahdrDetailId", tahdrDetailId);
		Long count= (Long) q.getSingleResult();
		return count;
	}
}
