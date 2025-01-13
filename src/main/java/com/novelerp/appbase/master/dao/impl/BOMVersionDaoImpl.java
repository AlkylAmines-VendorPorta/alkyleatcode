package com.novelerp.appbase.master.dao.impl;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.novelerp.appbase.master.dao.BOMVersionDao;
import com.novelerp.appbase.master.dto.BomVersionDto;
import com.novelerp.appbase.master.entity.BOMVersion;
import com.novelerp.core.dao.impl.AbstractJpaDAO;

/**
 * @author Ankita
 *
 */
@Repository
public class BOMVersionDaoImpl extends AbstractJpaDAO<BOMVersion, BomVersionDto> implements BOMVersionDao {

	private static final String ENTITY_NAME="M_BOM_VERSION";
	
	@PersistenceContext
	private EntityManager em;

	@PostConstruct
	private void init() {
		setClazz(BOMVersion.class, BomVersionDto.class);
	}

	public String getBomVersionFromMaterial() {
		StringBuilder sb=new StringBuilder("select bv from BOMVersion bv ");
		sb.append(" LEFT JOIN FETCH bv.material m where m.materialId=:materialId ");
		/*sb.append(" LEFT JOIN FETCH bv.material.hsnCode hsn ");
		sb.append(" LEFT JOIN FETCH bv.material.materialGroup mg where m.materialId=:materialId");*/
		return sb.toString();
	}
	
	public String getBomVersionForTahdrMaterial(){
		StringBuilder sb=new StringBuilder("Select distinct(bv) from BOMVersion bv ");
		sb.append("  INNER JOIN MaterialSpecification ms on bv.bomVersionId=ms.material.bomVersionId ");
		sb.append(" where bv.material.materialId=:materialId ");
		return sb.toString();
	}
}
