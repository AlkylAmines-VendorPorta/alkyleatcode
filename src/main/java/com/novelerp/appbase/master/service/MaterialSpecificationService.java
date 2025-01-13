package com.novelerp.appbase.master.service;

import java.util.List;

import com.novelerp.appbase.master.dto.MaterialSpecificationDto;
import com.novelerp.appbase.master.entity.MaterialSpecification;
import com.novelerp.core.service.CommonService;

/**
 *  @author Aman
  */
public interface MaterialSpecificationService extends CommonService<MaterialSpecification, MaterialSpecificationDto> {

	public List<MaterialSpecificationDto> getMaterialSpecificationList();
	public MaterialSpecificationDto getMaterialSpecification(Long partnerId);
	/*public ResponseDto addMaterialSpecification(MaterialSpecificationDto dto);
	public ResponseDto editMaterialSpecification(MaterialSpecificationDto dto);
	public ResponseDto deleteMaterialSpecification(Long id);*/
}
