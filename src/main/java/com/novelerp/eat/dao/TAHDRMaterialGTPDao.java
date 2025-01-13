/**
 * @author Ankush
 */
package com.novelerp.eat.dao;

import com.novelerp.core.dao.CommonDao;
import com.novelerp.eat.dto.TAHDRMaterialGTPDto;
import com.novelerp.eat.entity.TAHDRMaterialGTP;

public interface TAHDRMaterialGTPDao extends CommonDao<TAHDRMaterialGTP, TAHDRMaterialGTPDto> {

	/**
	 * @param newTahdrDetailId
	 * @param oldTahdrDetailId
	 * @return
	 */
	public int copyTahdrMaterialGTPToNewVersion(Long newTahdrDetailId, Long oldTahdrDetailId);

	/**
	 * @param newTahdrDetailId
	 * @param oldTahdrDetailId
	 * @return
	 */
	public int updateCopiedTahdrMaterialGTPToNewVersion(Long newTahdrDetailId, Long oldTahdrDetailId);

	
}
