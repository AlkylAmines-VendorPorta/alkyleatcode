package com.novelerp.appbase.master.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.novelerp.appbase.master.converter.MaterialVersionConverter;
import com.novelerp.appbase.master.dao.MaterialVersionDao;
import com.novelerp.appbase.master.dto.MaterialVersionDto;
import com.novelerp.appbase.master.entity.MaterialVersion;
import com.novelerp.appbase.master.service.MaterialVersionService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.commons.util.CommonUtil;
import com.novelerp.core.dto.ResponseDto;

/** 
 * @author Aman
 *
 */
@Service
public class MaterialVersionServiceImpl extends AbstractContextServiceImpl<MaterialVersion, MaterialVersionDto> implements MaterialVersionService {

	@Autowired
	private MaterialVersionDao materialVersionDao;
	
	@Autowired
	private MaterialVersionConverter materialVersionConverter;
	
	@PostConstruct
	private void init() {
		super.init(MaterialVersionServiceImpl.class, materialVersionDao, MaterialVersion.class, MaterialVersionDto.class);
		setByPassProxy(true);
	}

	@Override
	public List<MaterialVersionDto> getMaterialVersionList()
	{
		List<MaterialVersionDto> MaterialVersionList=new ArrayList<MaterialVersionDto>();
		List<MaterialVersion> MaterialVersionEntity= materialVersionDao.getMaterialVersionList();
		
		if(!MaterialVersionEntity.isEmpty())
		{
			for(MaterialVersion MaterialVersion:MaterialVersionEntity)
			{
				MaterialVersionDto MaterialVersionDto=materialVersionConverter.convertEntityToDto(MaterialVersion, MaterialVersionDto.class);
				MaterialVersionList.add(MaterialVersionDto);
			}
		}
		return MaterialVersionList;
	}
	
	@Override
	public MaterialVersionDto getMaterialVersion(Long entityId)
	{
		MaterialVersionDto MaterialVersionDto=null;
				
		try
		{
			List<MaterialVersion> MaterialVersion=materialVersionDao.getMaterialVersionById(entityId);
			if(!MaterialVersion.isEmpty())
			{
				MaterialVersionDto=materialVersionConverter.convertEntityToDto(MaterialVersion.get(0), MaterialVersionDto.class);
			}
			
		}
		catch(Exception e)
		{
			System.err.println("Error in Fetching MaterialVersion" + e.getMessage());
		}
		return MaterialVersionDto;
	}
	@Override
	@Transactional
	public MaterialVersionDto saveMaterialVersion(MaterialVersionDto dbMaterialVersion,MaterialVersionDto dto){
		ResponseDto response=new ResponseDto();
		if(null!=dbMaterialVersion && !CommonUtil.isEqual(dto.getMaterialVersionId(), dbMaterialVersion.getMaterialVersionId())){
			response= new ResponseDto(true, "Version is already created for specified material...!");
			dto.setResponse(response);
		}else if(null!=dbMaterialVersion && CommonUtil.isEqual(dto.getMaterialVersionId(), dbMaterialVersion.getMaterialVersionId())){
			
			dto=updateDto(dto);
		}else if(null==dbMaterialVersion && (null==dto.getMaterialVersionId() || !(dto.getMaterialVersionId()>0))){
			
			dto=save(dto);	
		}else{
			response= new ResponseDto(true,"Error in Save");
			dto.setResponse(response);
		}
		return dto;
	}
	
	/*@Override
	@Transactional
	public ResponseDto editMaterialVersion(MaterialVersionDto MaterialVersionDto)
	{
		System.out.println("..MaterialVersionServiceImpl-editMaterialVersion()..");
		
		try {
				MaterialVersion MaterialVersion=MaterialVersionConverter.getEntityFromDto(MaterialVersionDto, MaterialVersion.class);
				MaterialVersionDao.update(MaterialVersion);
				return new ResponseDto(AppBaseConstant.SUCCESS," MaterialVersion- "+MaterialVersionDto.getName()+" Updated Succesfully ",MaterialVersionDto.getId());
		} catch (Exception e) {
			
			System.err.println("Error in Updated MaterialVersion " + e.getMessage());
			return new ResponseDto(AppBaseConstant.FAILURE," MaterialVersion- "+MaterialVersionDto.getName()+"  Not Updated ",MaterialVersionDto.getId());
			
		}
		
	}
	
	@Override
	@Transactional
	public ResponseDto deleteMaterialVersion(Long id)
	{
		System.out.println("..MaterialVersionServiceImpl-deleteMaterialVersion()..");
		MaterialVersionDto MaterialVersionDto = new MaterialVersionDto();
		try {
			
			MaterialVersionDao.deleteById(id);
			return new ResponseDto(AppBaseConstant.SUCCESS," MaterialVersion- "+MaterialVersionDto.getName()+" Deleted Succesfully ",MaterialVersionDto.getId());
		} catch (Exception e) {
			
			System.err.println("Error in Deleting MaterialVersion " + e.getMessage());
			return new ResponseDto(AppBaseConstant.FAILURE," MaterialVersion- "+MaterialVersionDto.getName()+" Cannot be Deleted",MaterialVersionDto.getId());
		}
		
	}*/

}


