package com.novelerp.appbase.master.service;

import java.util.List;

import com.novelerp.appbase.master.dto.MaterialVersionDto;
import com.novelerp.appbase.master.entity.MaterialVersion;
import com.novelerp.core.service.CommonService;

/**
 *  @author Aman
  */
public interface MaterialVersionService extends CommonService<MaterialVersion, MaterialVersionDto> {

	public List<MaterialVersionDto> getMaterialVersionList();
	public MaterialVersionDto getMaterialVersion(Long partnerId);
	public MaterialVersionDto saveMaterialVersion(MaterialVersionDto dbMaterialVersion,MaterialVersionDto dto);
	/*public ResponseDto addMaterialVersion(MaterialVersionDto dto);
	public ResponseDto editMaterialVersion(MaterialVersionDto dto);
	public ResponseDto deleteMaterialVersion(Long id);*/
}
