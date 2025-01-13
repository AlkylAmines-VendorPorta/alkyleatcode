/**
 * @author Ankush
 */

package com.novelerp.eat.service;

import java.util.List;

import com.novelerp.core.service.CommonService;
import com.novelerp.eat.dto.TAHDRMaterialDto;
import com.novelerp.eat.entity.TAHDRMaterial;

public interface TAHDRMaterialService extends CommonService<TAHDRMaterial, TAHDRMaterialDto> {

	public TAHDRMaterialDto saveTAHDRMaterial(TAHDRMaterialDto tahdrMaterial);

	public List<TAHDRMaterialDto> getTahdrMaterialList(Long tahdrDetailId);
	
	public TAHDRMaterialDto updateTahdrMaterialQty(Long balanceQty);
	public List<TAHDRMaterialDto> getTahdrMaterialListByTahdrId(Long tahdrId);

	/**
	 * @param material
	 * @return
	 */
	boolean isItemAlreadyExist(TAHDRMaterialDto material);

	/**
	 * @param tahdrMaterial
	 * @return
	 */
	boolean updateTAHDRMaterial(TAHDRMaterialDto tahdrMaterial);

	/**
	 * @param tahdrMaterialId
	 * @return
	 */
	public TAHDRMaterialDto getTahdrMaterialById(Long tahdrMaterialId);
	
	/**
	 * @param id
	 * @return
	 */
	public boolean deleteTAHDRMaterial(Long id);
	
	public int copyTahdrMaterialsToNewVersion(Long newTahdrDetailId,Long oldTahdrDetailId);

	public boolean updateBaseRate(Long tahdrId);
	
	public long getTotalMaterialItemsCount(Long tahdrDetailId);
}
