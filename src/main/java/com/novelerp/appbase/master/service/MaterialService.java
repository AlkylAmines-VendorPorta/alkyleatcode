package com.novelerp.appbase.master.service;

import java.util.List;

import com.novelerp.appbase.master.dto.MaterialDto;
import com.novelerp.appbase.master.dto.MaterialSearchDto;
import com.novelerp.appbase.master.entity.Material;
import com.novelerp.core.service.CommonService;

/**
 *  @author Aman
  */
public interface MaterialService extends CommonService<Material, MaterialDto> {

	public List<MaterialDto> getMaterialList();
	public MaterialDto getMaterial(Long entityId);
	/*public ResponseDto addMaterial(MaterialDto dto);
	public ResponseDto editMaterial(MaterialDto dto);
	public ResponseDto deleteMaterial(Long id);*/
	public List<MaterialDto> getMaterialBySubGroup(Long id);
	
	public List<MaterialDto> getActiveMaterials();
	public List<MaterialDto> getSearchedMaterialList(MaterialSearchDto materialDto);
	public List<MaterialDto> getMaterialList(int pageNumber, int pageSize, String searchColumn, String SearchValue,Long id);
	public long getMaterialListQueryCount(String searchColumn, String searchValue,Long id);
	public List<MaterialDto> getTradingMaterial(MaterialSearchDto dto);
	public boolean deleteMaterial(Long id);
}