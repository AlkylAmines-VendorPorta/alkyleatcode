package com.novelerp.alkyl.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.novelerp.alkyl.dao.GateEntryLineDao;
import com.novelerp.alkyl.dao.MaterialGateInDao;
import com.novelerp.alkyl.dto.GateEntryDto;
import com.novelerp.alkyl.dto.GateEntryLineDto;
import com.novelerp.alkyl.dto.GateEntryReadDto;
import com.novelerp.alkyl.entity.GateEntry;
import com.novelerp.alkyl.entity.GateEntryLine;
import com.novelerp.alkyl.service.GateEntryLineService;
import com.novelerp.alkyl.service.MaterialGateInListService;
import com.novelerp.alkyl.service.MaterialGateInService;
import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.appcontext.entity.User;
import com.novelerp.appcontext.service.ContextService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.core.dto.CustomResponseDto;
import com.novelerp.eat.dto.MaterialGateInDto;
import com.novelerp.eat.dto.MaterialGateInListDto;
@Service
public class GateEntryLineServiceImpl extends AbstractContextServiceImpl<GateEntryLine, GateEntryLineDto> implements GateEntryLineService{

	@Autowired
	private GateEntryLineDao gateEntryLineDao;
	
	@Autowired
	private MaterialGateInService materialGateInService;
	
	@Autowired
	private MaterialGateInListService materialGateInListService;
	
	@Autowired
	private MaterialGateInDao materialGateInDao;
	
	@Autowired
	@Qualifier("jwtUserContext")
	private ContextService contextService;
	
	@Autowired
	private GateEntryLineService gateEntryLineService;
	
	@PostConstruct
	protected void init() {
		super.init(GateEntryLineServiceImpl.class, gateEntryLineDao, GateEntryLine.class, GateEntryLineDto.class);
		setByPassProxy(true);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void saveLines(GateEntryDto dto, List<GateEntryLineDto> gateEntryLineDto) {
		for(GateEntryLineDto lineDto:gateEntryLineDto){
			lineDto.setGateEntry(dto);
			if(null==lineDto.getGateEntryLineId()){
			save(lineDto);
			}else{
				
				Map<String, Object> param = new HashMap<String, Object>();
				
				param.put("materialCode", lineDto.getMaterialCode());
				param.put("materialDesc", lineDto.getMaterialDesc());
				param.put("materialQty", lineDto.getMaterialQty());
				param.put("materialRate", lineDto.getMaterialRate());
//				param.put("materialCost", lineDto.getMaterialCost());
				param.put("repairingCost", lineDto.getRepairingCost());
//				param.put("receivedQty", lineDto.getReceivedQty());
//				param.put("gateInQty", lineDto.getGateInQty());
				param.put("uom", lineDto.getUom());
				param.put("purpose", lineDto.getPurpose());
				int result = gateEntryLineService.updateByJpql(param,AbstractContextServiceImpl.getParamMap("gateEntryLineId", lineDto.getGateEntryLineId()));
			// update(lineDto);
			}
		}
		
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void updateGateIn(GateEntryDto gateEntryDto) {
		for(GateEntryLineDto lineDto:gateEntryDto.getGateEntryLineList()){
			if(null==lineDto.getReceivedQty()){
				lineDto.setReceivedQty(0d);
			}
			double recqty=lineDto.getReceivedQty()+lineDto.getGateInQty();
			Map<String, Object> propertyValueMap = AbstractContextServiceImpl.getParamMap("receivedQty",recqty);
			propertyValueMap.put("gateInQty", lineDto.getGateInQty());
			updateByJpql(
					propertyValueMap,
					AbstractContextServiceImpl.getParamMap("gateEntryLineId", lineDto.getGateEntryLineId()));
		}
		MaterialGateInDto materialGateInDto = new MaterialGateInDto();
		materialGateInDto.setGateEntry(gateEntryDto);
		materialGateInDto.setStatus(AppBaseConstant.MATERIALGATEIN);
		materialGateInDto.setDocNo(materialGateInDao.getNewReqNo());
		materialGateInDto.setGateIn(contextService.getUser());
		materialGateInDto.setGateInDate(new Date());
		materialGateInDto=materialGateInService.save(materialGateInDto);
		for(GateEntryLineDto lineDto:gateEntryDto.getGateEntryLineList()){
			if(lineDto.getGateInQty()>0){
		MaterialGateInListDto materialGateInListDto = new MaterialGateInListDto();
		materialGateInListDto.setMaterialGateIn(materialGateInDto);
		materialGateInListDto.setGateEntryLine(lineDto);
		materialGateInListDto.setGateInQty(lineDto.getGateInQty());
		materialGateInListService.save(materialGateInListDto);
			}
		}
	}
	
	
	@Override
	public List<GateEntryLineDto> getGateEntryByFilter(GateEntryReadDto dto) {
		Map<String, Object> params=getParameterMap(dto);
		String query=gateEntryLineDao.getGateEntryLineDetails(dto);
		List<GateEntryLineDto> poList=findDtosByQuery(query, params);
		return poList;
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
   		if(dto.getDocType()!=null){
   			params.put("docType", dto.getDocType());
   		}
   		if(dto.getPlant()!=null){
   			params.put("plant", dto.getPlant());
   		}
//   		if(dto.getReqDateFrom()!=null && dto.getReqDateTo()!=null){
//   			params.put("fromDate", dto.getReqDateFrom());
//			params.put("toDate", dto.getReqDateTo());
//   		}
//   		if(dto.getReqDateFrom()!=null && dto.getReqDateTo()==null){
//   			params.put("fromDate", dto.getReqDateFrom());
//   		}
//   		if(dto.getReqDateFrom()==null && dto.getReqDateTo()!=null){
//   			params.put("toDate", dto.getReqDateTo());
//   		}
		return params;
	}
	
	
	@Override
	public List<GateEntryLineDto> getGateEntryRGPByFilter(GateEntryReadDto dto) {
		Map<String, Object> params=getRGPParameterMap(dto);
		//String query=gateEntryLineDao.getGateEntryLineDetails(dto);
		String query=gateEntryLineDao.getRGPGateEntryLineDetails(dto);
		List<GateEntryLineDto> poList=findDtosByQuery(query, params);
		return poList;
	}
	private Map<String,Object> getRGPParameterMap(GateEntryReadDto dto){
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
   		if(dto.getDocType()!=null){
   			params.put("docType", dto.getDocType());
   		}
   		if(dto.getPlant()!=null){
   			params.put("plant", dto.getPlant());
   		}
   		if(dto.getReqDateFrom()!=null && dto.getReqDateTo()!=null){
   			//params.put("fromDate", dto.getAsnDateFrom());
			//params.put("toDate", dto.getAsnDateTo());
   			params.put("reqDateFrom", dto.getStartReqDateFrom());
   			dto.setReqDateFrom(dto.getStartReqDateFrom());
   			params.put("reqDateTo", dto.getLastReqDateTo());
   			dto.setReqDateTo(dto.getLastReqDateTo());
   		}
   		if(dto.getReqDateFrom()!=null && dto.getReqDateTo()==null){
   			params.put("reqDateFrom", dto.getStartReqDateFrom());
			params.put("reqDateTo", dto.getLastReqDateFrom());
			dto.setReqDateFrom(dto.getStartReqDateFrom());
			dto.setReqDateTo(dto.getLastReqDateFrom());
   		}
   		
   		if(dto.getReqDateFrom()==null && dto.getReqDateTo()!=null){
   			params.put("reqDateFrom", dto.getStartReqDateTo());
			params.put("reqDateTo", dto.getLastReqDateTo());
			dto.setReqDateFrom(dto.getStartReqDateTo());
			dto.setReqDateTo(dto.getLastReqDateTo());
   		}
		return params;
	}
	
	
	
	
}
