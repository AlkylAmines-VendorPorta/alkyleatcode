package com.novelerp.appbase.master.service;


import java.util.List;

import com.novelerp.appbase.master.dto.UOMDto;
import com.novelerp.appbase.master.entity.UOM;
import com.novelerp.core.service.CommonService;

/**
 * 
 * @author Ankita Tirodkar
 *
 */

public interface UOMService extends CommonService<UOM, UOMDto> {
	
	public List<UOMDto> getUomList();
	public UOMDto getUom(Long entityId);
	public List<UOMDto> getSearchedUomList(String word);
	public boolean deleteUOM(Long id);
	/*public ResponseDto addUom(UOMDto uomDto);*/
	/*public ResponseDto editUom(UOMDto uomDto);
	public ResponseDto deleteUom(Long id);*/

}
