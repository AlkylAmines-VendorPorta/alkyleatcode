/**
 * @author Ankush
 */
package com.novelerp.eat.service;

import java.util.List;

import com.novelerp.core.service.CommonService;
import com.novelerp.eat.dto.TAHDRMaterialGTPDto;

public interface TAHDRMaterialGTPService extends CommonService<com.novelerp.eat.entity.TAHDRMaterialGTP, com.novelerp.eat.dto.TAHDRMaterialGTPDto> {

	public List<TAHDRMaterialGTPDto> saveTahdrMaterialGtpList(List<TAHDRMaterialGTPDto> gtpDtoList);

	/**
	 * @param newTahdrDetailId
	 * @param oldTahdrDetailId
	 * @return
	 */
	public int copyTahdrMaterialGTPToNewVersion(Long newTahdrDetailId, Long oldTahdrDetailId);
	
}
