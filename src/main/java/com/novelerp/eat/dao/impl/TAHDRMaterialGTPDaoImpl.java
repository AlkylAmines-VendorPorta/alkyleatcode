/**
 * @author Ankush
 */
package com.novelerp.eat.dao.impl;

import javax.annotation.PostConstruct;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.novelerp.core.dao.impl.AbstractJpaDAO;
import com.novelerp.eat.dao.TAHDRMaterialGTPDao;
import com.novelerp.eat.dto.TAHDRMaterialGTPDto;
import com.novelerp.eat.entity.TAHDRMaterialGTP;

@Repository
public class TAHDRMaterialGTPDaoImpl extends AbstractJpaDAO<TAHDRMaterialGTP, TAHDRMaterialGTPDto>	implements TAHDRMaterialGTPDao {
	
	@PostConstruct
	public void postConstruct() {
		setClazz(TAHDRMaterialGTP.class, TAHDRMaterialGTPDto.class);
	}
	
	public String getTahdrGtpListByMaterialIdQuery(){
		StringBuilder jpql= new StringBuilder();
		jpql.append(" select tmg from TAHDRMaterialGTP tmg "
				+ " LEFT JOIN FETCH tmg.gtp gtp "
				+ " LEFT JOIN FETCH gtp.gtpParameterType "
				+ " where tmg.tahdrMaterial.tahdrMaterialId= :tahdrMaterialId order by tmg.gtp.gtpParameterId");
		return jpql.toString();
	}
	
	public String getTahdrMaterialGtpByTahdrMaterialId(){
		StringBuilder jpql = new StringBuilder();
		jpql.append(" Select tmg from TAHDRMaterialGTP tmg ")
		.append(" INNER JOIN FETCH tmg.tahdrMaterial tm ")
		.append(" INNER JOIN FETCH tmg.gtp g ")
		.append(" INNER JOIN FETCH g.gtpParameterType gt ")
		.append(" WHERE tm.tahdrMaterialId=:tahdrMaterialId ");
		return jpql.toString();
	}
	
	private String queryToCopyTahdrMaterialGTP(){
		StringBuilder jpql = new StringBuilder();
		jpql.append(" INSERT INTO T_TAHDR_MATERIAL_GTP  ")
		.append(" ( ")
		.append(" t_tahdr_material_gtp_id,created,isactive,updated,ispublish,createdby, ")
		.append(" m_bpartner_id,updatedby,m_gtp_id,t_tahdr_material_id,T_TAHDR_DETAIL_ID ")
		.append(" ) ")
		.append(" SELECT t_tahdr_material_gtp_seq.NEXTVAL, ttmg.created,ttmg.isactive, ")
		.append(" ttmg.updated,ttmg.ispublish,ttmg.createdby,ttmg.m_bpartner_id,ttmg.updatedby, ")
		.append( "ttmg.m_gtp_id,ttmg.t_tahdr_material_id,? ")
		.append(" FROM t_tahdr_material_gtp ttmg ")
		.append(" inner join t_tahdr_material ttm on ttmg.t_tahdr_material_id=ttm.t_tahdr_material_id ")
		.append(" WHERE ttm.t_tahdr_detail_id =? ");
		return jpql.toString();
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public int copyTahdrMaterialGTPToNewVersion(Long newTahdrDetailId,Long oldTahdrDetailId){
		String jpql=queryToCopyTahdrMaterialGTP();
		Query query = getEntityManager().createNativeQuery(jpql);
		query.setParameter(1, newTahdrDetailId);
		query.setParameter(2, oldTahdrDetailId);
		int count= query.executeUpdate();
		updateCopiedTahdrMaterialGTPToNewVersion( newTahdrDetailId, oldTahdrDetailId);
		return count;
	}
	
	
	private String queryToUpdateCopiedTahdrMaterialGTP(){
		StringBuilder jpql=new StringBuilder();
		jpql.append(" UPDATE T_TAHDR_MATERIAL_GTP TTMG ")
		.append(" SET TTMG.T_TAHDR_MATERIAL_ID=")
		.append(" ( ")
		.append(" SELECT T_TAHDR_MATERIAL_ID FROM T_TAHDR_MATERIAL TTM ")
        .append(" WHERE TTM.T_MATERIAL_ID= ")
        .append(" ( ")
        .append(" SELECT T_MATERIAL_ID FROM T_TAHDR_MATERIAL TTM2  ")
        .append(" WHERE TTM2.T_TAHDR_MATERIAL_ID=TTMG.T_TAHDR_MATERIAL_ID AND TTM2.T_TAHDR_DETAIL_ID=? ")
        .append(" ) ")
        .append(" AND TTM.T_TAHDR_DETAIL_ID=? ")
        .append(" ) ")
        .append(" WHERE TTMG.T_TAHDR_DETAIL_ID=? ") ;
		return jpql.toString();
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public int updateCopiedTahdrMaterialGTPToNewVersion(Long newTahdrDetailId,Long oldTahdrDetailId){
		String jpql=queryToUpdateCopiedTahdrMaterialGTP();
		Query query = getEntityManager().createNativeQuery(jpql);
		query.setParameter(1, oldTahdrDetailId);
		query.setParameter(2, newTahdrDetailId);
		query.setParameter(3, newTahdrDetailId);
		int count= query.executeUpdate();
		return count;
	}
	
}
