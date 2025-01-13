package com.novelerp.appbase.master.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.novelerp.appbase.master.converter.MaterialDetailConverter;
import com.novelerp.appbase.master.dao.MaterialDao;
import com.novelerp.appbase.master.dto.MaterialDto;
import com.novelerp.appbase.master.dto.MaterialSearchDto;
import com.novelerp.appbase.master.entity.Material;
import com.novelerp.appbase.master.service.MaterialService;
import com.novelerp.appcontext.dto.BPartnerDto;
import com.novelerp.appcontext.service.ContextService;
import com.novelerp.appcontext.service.SystemConfiguratorService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.commons.util.CommonUtil;

/**
 * @author Aman
 *
 */
@Service
public class MaterialServiceImpl extends AbstractContextServiceImpl<Material, MaterialDto> implements MaterialService {

	@Autowired
	private MaterialDao materialDao;
	
	@Autowired
	 @Qualifier("jwtUserContext") private ContextService contextService;

	@Autowired
	private MaterialDetailConverter materialDetailConverter;
	
	@Autowired
	private SystemConfiguratorService sysConfiguratorService;

	@PostConstruct
	private void init() {
		super.init(MaterialServiceImpl.class, materialDao, Material.class, MaterialDto.class);
		/*setObjectConverter(materialDetailConverter);*/
		setByPassProxy(true);
	}

	@Override
	public List<MaterialDto> getMaterialList() {
		List<MaterialDto> materialList = new ArrayList<MaterialDto>();
		List<Material> materialEntity = materialDao.getMaterialList();

		materialList = getDtoList(materialEntity);

		return materialList;
	}

	@Override
	public List<MaterialDto> getMaterialList(int pageNumber, int pageSize, String searchColumn, String searchValue,Long id) {
		List<MaterialDto> materialList = new ArrayList<MaterialDto>();
		Map<String, Object> materialParams = null;
		String queryString = materialDao.getMaterialListQuery(searchColumn, searchValue);
		if(id!=null){
			materialParams= AbstractContextServiceImpl.getParamMap("bPartnerId", id);}
		if (!"none".equalsIgnoreCase(searchValue)) {
			materialParams = AbstractContextServiceImpl.getParamMap("searchValue",
					"%" + searchValue.toUpperCase() + "%");
		} 
		materialList = findDtosByQuery(queryString, materialParams, pageNumber, pageSize);
		return materialList;
	}

	@Override
	public long getMaterialListQueryCount(String searchColumn, String searchValue,Long id) {
		long totalCount;
		
			Map<String, Object> materialParams = null;
			if(id!=null){
			materialParams= AbstractContextServiceImpl.getParamMap("bPartnerId", id);}
			String queryString = materialDao.getMaterialListQueryCountQry(searchColumn,searchValue);

			if (!"none".equalsIgnoreCase(searchValue)) {
			materialParams = AbstractContextServiceImpl.getParamMap("searchValue", "%" + searchValue.toUpperCase() + "%");
			totalCount = getRecordCount(queryString, materialParams);
		}else{
			totalCount = getRecordCount(queryString, materialParams);
		}
		return totalCount;
	}

	@Override
	public MaterialDto getMaterial(Long entityId) {

		MaterialDto MaterialDto = null;
		try {
			List<Material> MaterialList = materialDao.getMaterialById(entityId);

			if (!MaterialList.isEmpty()) {
				MaterialDto = materialDetailConverter.convertEntityToDto(MaterialList.get(0), MaterialDto.class);
			}
		} catch (Exception e) {
			System.err.println("Error in Fetching Material" + e.getMessage());
		}
		return MaterialDto;
	}

	@Override
	public List<MaterialDto> getMaterialBySubGroup(Long id) {
		List<MaterialDto> materialList = new ArrayList<MaterialDto>();
		List<Material> materialEntity = materialDao.getMaterialBySubGroupId(id);
		materialList = getDtoList(materialEntity);
		return materialList;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public List<MaterialDto> getActiveMaterials() {
		Map<String, Object> materialParams = AbstractContextServiceImpl.getParamMap("isActive", "Y");
		return find("isActive= :isActive", materialParams, "name");
	}

	@Override
	public List<MaterialDto> getSearchedMaterialList(MaterialSearchDto materialDto) {
		Map<String, Object> params = getParameterMap(materialDto);
		BPartnerDto bPartnerDto =contextService.getPartner();
		params.put("bPartnerId", bPartnerDto.getbPartnerId());
		String createrCheck=sysConfiguratorService.getPropertyConfigurator("eat.material.creater_check");
		boolean isCreaterCheck=false;
		if(createrCheck.equalsIgnoreCase("Y")){
			isCreaterCheck=true;
		}
		String query = materialDao.getItemSearchQuery(materialDto,isCreaterCheck);
		List<MaterialDto> materialDtoList = findDtosByQuery(query, params);
		return materialDtoList;
	}

	private Map<String, Object> getParameterMap(MaterialSearchDto searchDto) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("isActive", "Y");
		if (searchDto.getPartnerOrgId() != null) {
			params.put("partnerOrgId", searchDto.getPartnerOrgId());
		}
		if (!CommonUtil.isStringEmpty(searchDto.getCode())) {
			String code = "%" + searchDto.getCode() + "%";
			params.put("code", code);
		}
		if (!CommonUtil.isStringEmpty(searchDto.getName())) {
			String name = "%" + searchDto.getName() + "%";
			params.put("name", name);
		}
		if (searchDto.getHsnCode() != null) {
			/* String hsnCode = "%" +searchDto.getHsnCode() +"%"; */
			params.put("hsnCode", searchDto.getHsnCode());
		}
		if (searchDto.getMaterialGroupId() != null) {
			params.put("materialGroupId", searchDto.getMaterialGroupId());
		}
		if (searchDto.getMaterialSubGroupId() != null) {
			params.put("materialSubGroupId", searchDto.getMaterialSubGroupId());
		}
		if (!CommonUtil.isStringEmpty(searchDto.getTypeCode())) {
			String typeCode = "%" + searchDto.getTypeCode() + "%";
			params.put("typeCode", typeCode);
		}
		return params;
	}
	/*
	 * @Override
	 * 
	 * @Transactional public ResponseDto editMaterial(MaterialDto MaterialDto) {
	 * System.out.println("..MaterialServiceImpl-editMaterial()..");
	 * 
	 * try { Material Material=MaterialConverter.getEntityFromDto(MaterialDto,
	 * Material.class); MaterialDao.update(Material); return new
	 * ResponseDto(AppBaseConstant.SUCCESS," Material- "+MaterialDto.getName()
	 * +" Updated Succesfully ",MaterialDto.getId()); } catch (Exception e) {
	 * 
	 * System.err.println("Error in Updated Material " + e.getMessage()); return
	 * new
	 * ResponseDto(AppBaseConstant.FAILURE," Material- "+MaterialDto.getName()
	 * +"  Not Updated ",MaterialDto.getId());
	 * 
	 * }
	 * 
	 * }
	 * 
	 * @Override
	 * 
	 * @Transactional public ResponseDto deleteMaterial(Long id) {
	 * System.out.println("..MaterialServiceImpl-deleteMaterial()..");
	 * MaterialDto MaterialDto = new MaterialDto(); try {
	 * 
	 * MaterialDao.deleteById(id); return new
	 * ResponseDto(AppBaseConstant.SUCCESS," Material- "+MaterialDto.getName()
	 * +" Deleted Succesfully ",MaterialDto.getId()); } catch (Exception e) {
	 * 
	 * System.err.println("Error in Deleting Material " + e.getMessage());
	 * return new
	 * ResponseDto(AppBaseConstant.FAILURE," Material- "+MaterialDto.getName()
	 * +" Cannot be Deleted",MaterialDto.getId()); }
	 * 
	 * }
	 */

	@Override
	public List<MaterialDto> getTradingMaterial(MaterialSearchDto dto) {
		Map<String, Object> params = getParameterMapForTradingMaterial(dto);
		String query = materialDao.getQueryForTradingMaterial(dto);
		List<MaterialDto> materialDtoList = findDtosByQuery(query, params);
		return materialDtoList;
	}
	private Map<String, Object> getParameterMapForTradingMaterial(MaterialSearchDto searchDto) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("partnerId", searchDto.getbPartnerId());
		if (!CommonUtil.isStringEmpty(searchDto.getCode())) {
			String code = "%" + searchDto.getCode() + "%";
			params.put("code", code);
		}
		if (!CommonUtil.isStringEmpty(searchDto.getName())) {
			String name = "%" + searchDto.getName() + "%";
			params.put("name", name);
		}
		if (searchDto.getMaterialGroupId() != null) {
			params.put("materialGroupId", searchDto.getMaterialGroupId());
		}
		if (searchDto.getMaterialSubGroupId() != null) {
			params.put("materialSubGroupId", searchDto.getMaterialSubGroupId());
		}
		return params;
	}

	@Override
	@Transactional
	public boolean deleteMaterial(Long id) {
		
		return deleteById(id);
	}
	@Transactional
	public void removeTax(Long id)
	{
		materialDao.deleteById(id);
	}
}
