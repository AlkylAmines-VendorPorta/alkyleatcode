/**
 * @author Ankush
 */
package com.novelerp.eat.dao;

import java.util.List;

import com.novelerp.core.dao.CommonDao;
import com.novelerp.eat.dto.TAHDRMaterialDto;
import com.novelerp.eat.entity.TAHDRMaterial;

public interface TAHDRMaterialDao extends CommonDao<TAHDRMaterial, TAHDRMaterialDto> {
	public List<TAHDRMaterial> getTahdrMaterialList(Long tahdrDetailId);
	
	public List<TAHDRMaterial> getTahdrMaterialListByTahdrId(Long tahdrId);

public TAHDRMaterial updateTahdrMaterialQuantity(Long balanceQty);
	/**
	 * @param tahdrMaterialId
	 * @return
	 */
	public TAHDRMaterial getTahdrMaterialById(Long tahdrMaterialId);


	/**
	 * @param newTahdrDetailId
	 * @param oldTahdrDetailId
	 * @return
	 */
	public int copyTahdrMaterialsToNewVersion(Long newTahdrDetailId, Long oldTahdrDetailId);

	

	public int updateBaseRate(Long tahdrId);
	public long getMaterialItemTotalCountQry(Long tahdrDetailId);
}
