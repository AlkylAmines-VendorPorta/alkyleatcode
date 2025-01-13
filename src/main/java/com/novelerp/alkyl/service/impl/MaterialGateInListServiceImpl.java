package com.novelerp.alkyl.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.novelerp.alkyl.dao.MaterialGateInListDao;
import com.novelerp.alkyl.dto.GateEntryReadDto;
import com.novelerp.alkyl.service.MaterialGateInListService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.eat.dto.MaterialGateInDto;
import com.novelerp.eat.dto.MaterialGateInListDto;
import com.novelerp.eat.entity.MaterialGateInList;

@Service
public class MaterialGateInListServiceImpl extends AbstractContextServiceImpl<MaterialGateInList, MaterialGateInListDto> implements MaterialGateInListService{

	@Autowired
	private MaterialGateInListDao materialGateInListDao;
	
	@PostConstruct
	protected void init() {
		// TODO Auto-generated method stub
		super.init(MaterialGateInListServiceImpl.class, materialGateInListDao, MaterialGateInList.class, MaterialGateInListDto.class);
		setByPassProxy(true);
	}
	
	@Override
	public List<MaterialGateInListDto> getGateEntryMaterialByFilter(GateEntryReadDto dto) {
		Map<String, Object> params=getParameterMap(dto);
		//String query=materialGateInDao.getmaterialGateEntryDetails(dto);
		String query=materialGateInListDao.getmaterialGateEntryDetails(dto);
		List<MaterialGateInListDto> materiaGateinlList=findDtosByQuery(query, params);
		return materiaGateinlList;
	}
	private Map<String,Object> getParameterMap(GateEntryReadDto dto){
		Map<String, Object> params = new HashMap<String, Object>();
		if(dto.getReqNoFrom()!=null && dto.getReqNoTo()!=null) {
			params.put("reqNoFrom", dto.getReqNoFrom());
			params.put("reqNoTo", dto.getReqNoTo());
		}
		if(dto.getReqNoFrom()!=null && dto.getReqNoTo()==null){
   			params.put("reqNoFrom", dto.getReqNoFrom());
   		}
   		if(dto.getReqNoFrom()==null && dto.getReqNoTo()!=null){
   			params.put("reqNoTo", dto.getReqNoTo());
   		}
   		if(dto.getStatus()!=null){
   			params.put("status", dto.getStatus());
   		}
//   		if(dto.getDocType()!=null){
//   			params.put("docType", dto.getDocType());
//   		}
   		if(dto.getPlant()!=null){
   			params.put("plant", dto.getPlant());
   		}
   		
   		

		return params;
	}
}
