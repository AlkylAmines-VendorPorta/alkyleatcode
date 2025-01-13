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

import com.novelerp.alkyl.dao.MaterialGateInDao;
import com.novelerp.alkyl.dao.MaterialGateInListDao;
import com.novelerp.alkyl.dto.GateEntryLineDto;
import com.novelerp.alkyl.dto.GateEntryReadDto;
import com.novelerp.alkyl.service.GateEntryLineService;
import com.novelerp.alkyl.service.GateEntryService;
import com.novelerp.alkyl.service.MaterialGateInListService;
import com.novelerp.alkyl.service.MaterialGateInService;
import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.appcontext.entity.User;
import com.novelerp.appcontext.service.ContextService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.eat.dto.MaterialGateInDto;
import com.novelerp.eat.dto.MaterialGateInListDto;
import com.novelerp.eat.entity.MaterialGateIn;

@Service
public class MaterialGateInServiceImpl extends AbstractContextServiceImpl<MaterialGateIn, MaterialGateInDto> implements MaterialGateInService {

	@Autowired
	private MaterialGateInDao materialGateInDao;
	
	@Autowired
	private MaterialGateInListService materialGateInListService;
	
	@Autowired
	private GateEntryLineService gateEntryLineService;
	
	@Autowired
	private MaterialGateInService materialGateInService;
	
	@Autowired
	private GateEntryService gateEntryService;
	
	@Autowired
	@Qualifier("jwtUserContext")
	private ContextService contextService;
	
	@Autowired
	private MaterialGateInListDao materialGateInListDao;
	
	@PostConstruct
	protected void init() {
		// TODO Auto-generated method stub
		super.init(MaterialGateInServiceImpl.class, materialGateInDao, MaterialGateIn.class, MaterialGateInDto.class);
		setByPassProxy(true);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void updateRejectQty(MaterialGateInDto materialGateInDto) {
		for(MaterialGateInListDto lineDto:materialGateInDto.getMaterialGateInList()){
			Map<String, Object> propertyValueMap = AbstractContextServiceImpl.getParamMap("rejectQty",lineDto.getRejectQty());
			propertyValueMap.put("acceptQty", lineDto.getAcceptQty());
			materialGateInListService.updateByJpql(
					propertyValueMap,
					AbstractContextServiceImpl.getParamMap("gateInListId", lineDto.getGateInListId()));
			if(null!=lineDto.getRejectQty()){
			if(lineDto.getRejectQty()>0){
				GateEntryLineDto gateEntryLineDto=gateEntryLineService.findDto(lineDto.getGateEntryLine().getGateEntryLineId());
				double recqty=gateEntryLineDto.getReceivedQty()-lineDto.getRejectQty();
				Map<String, Object> valueMap = AbstractContextServiceImpl.getParamMap("receivedQty",recqty);
				gateEntryLineService.updateByJpql(
						valueMap,
						AbstractContextServiceImpl.getParamMap("gateEntryLineId", lineDto.getGateEntryLine().getGateEntryLineId()));
			
			
			}
			}
		}
		
		
		
//		User materialCheck= new User();
//		materialCheck.setUserId(contextService.getUser().getUserId());
//		Map<String, Object> valueMap = AbstractContextServiceImpl.getParamMap("status",AppBaseConstant.MATERIALCHECKED);
//		valueMap.put("materialCheck", materialCheck);
//		valueMap.put("materialCheckDate", new Date());
//		materialGateInService.updateByJpql(
//				valueMap,
//				AbstractContextServiceImpl.getParamMap("gateInId", materialGateInDto.getGateInId()));
	
		
		
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void UpdateMaterialGateIn(MaterialGateInDto materialGateInDto) {
		
		User materialCheck= new User();
		materialCheck.setUserId(contextService.getUser().getUserId());
		Map<String, Object> valueMap = AbstractContextServiceImpl.getParamMap("status",AppBaseConstant.MATERIALCHECKED);
		valueMap.put("materialCheck", materialCheck);
		valueMap.put("materialCheckDate", new Date());
		materialGateInService.updateByJpql(
				valueMap,
				AbstractContextServiceImpl.getParamMap("gateInId", materialGateInDto.getGateInId()));
		
	}
	
	

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void closeMaterialGateIn(MaterialGateInDto materialGateInDto) {
		for(MaterialGateInListDto lineDto:materialGateInDto.getMaterialGateInList()){
			Map<String, Object> propertyValueMap = AbstractContextServiceImpl.getParamMap("rejectQty",lineDto.getRejectQty());
			propertyValueMap.put("acceptQty", lineDto.getAcceptQty());
			materialGateInListService.updateByJpql(
					propertyValueMap,
					AbstractContextServiceImpl.getParamMap("gateInListId", lineDto.getGateInListId()));
			if(lineDto.getRejectQty()>0){
				GateEntryLineDto gateEntryLineDto=gateEntryLineService.findDto(lineDto.getGateEntryLine().getGateEntryLineId());
				MaterialGateInListDto materialListDto=materialGateInListService.findDto(lineDto.getGateInListId());
				if(null==materialListDto.getRejectQty()){
					materialListDto.setRejectQty(0d);
				}
				double recqty=(gateEntryLineDto.getReceivedQty()+materialListDto.getRejectQty())-lineDto.getRejectQty();
				Map<String, Object> valueMap = AbstractContextServiceImpl.getParamMap("receivedQty",recqty);
				gateEntryLineService.updateByJpql(
						valueMap,
						AbstractContextServiceImpl.getParamMap("gateEntryLineId", lineDto.getGateEntryLine().getGateEntryLineId()));
			
			
			}
			
		//	GateEntryLineDto gateEntryLineDto=gateEntryLineService.findDto(lineDto.getGateEntryLine().getGateEntryLineId());
//			Map<String, Object> params= new HashMap<>();
//			params.put("gateEntryLineId", lineDto.getGateEntryLine().getGateEntryLineId());
//			
//			
//			List<GateEntryLineDto> gateEntryLineDtoTest=gateEntryLineService.findDtos("getGateEntryLineDetailsByLineid", params);
//			
//			
//			for(GateEntryLineDto gateline: gateEntryLineDtoTest) {
//			if(gateline.getMaterialQty().equals(gateline.getReceivedQty())) {
//				User closedBy= new User();
//				closedBy.setUserId(contextService.getUser().getUserId());
//				Map<String, Object> valueMap = AbstractContextServiceImpl.getParamMap("status",AppBaseConstant.GECLOSED);
//				valueMap.put("closedBy", closedBy);
//				valueMap.put("closedDate", new Date());
//				gateEntryService.updateByJpql(
//						valueMap,
//						AbstractContextServiceImpl.getParamMap("gateEntryId", gateline.getGateEntry().getGateEntryId()));
//			}
//			}
		}
//		User closedBy= new User();
//		closedBy.setUserId(contextService.getUser().getUserId());
//		Map<String, Object> valueMap = AbstractContextServiceImpl.getParamMap("status",AppBaseConstant.MATERIALCLOSED);
//		valueMap.put("closedBy", closedBy);
//		valueMap.put("closedDate", new Date());
//		materialGateInService.updateByJpql(
//				valueMap,
//				AbstractContextServiceImpl.getParamMap("gateInId", materialGateInDto.getGateInId()));
	
		
		
	}
	
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void closedMaterialGateInUpdate(MaterialGateInDto materialGateInDto) {
		
		User closedBy= new User();
		closedBy.setUserId(contextService.getUser().getUserId());
		Map<String, Object> valueMap = AbstractContextServiceImpl.getParamMap("status",AppBaseConstant.MATERIALCLOSED);
		valueMap.put("closedBy", closedBy);
		valueMap.put("closedDate", new Date());
		materialGateInService.updateByJpql(
				valueMap,
				AbstractContextServiceImpl.getParamMap("gateInId", materialGateInDto.getGateInId()));
		
		
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void closedGateEntryUpdate(MaterialGateInListDto lineDto) {
		
		Map<String, Object> params= new HashMap<>();
		params.put("gateEntryLineId", lineDto.getGateEntryLine().getGateEntryLineId());		
		List<GateEntryLineDto> gateEntryLineDtoTest=gateEntryLineService.findDtos("getGateEntryLineDetailsByLineid", params);		
		
		for(GateEntryLineDto gateline: gateEntryLineDtoTest) {
		if(gateline.getMaterialQty().equals(gateline.getReceivedQty())) {
			User closedBy= new User();
			closedBy.setUserId(contextService.getUser().getUserId());
			Map<String, Object> valueMap = AbstractContextServiceImpl.getParamMap("status",AppBaseConstant.GECLOSED);
			valueMap.put("closedBy", closedBy);
			valueMap.put("closedDate", new Date());
			gateEntryService.updateByJpql(
					valueMap,
					AbstractContextServiceImpl.getParamMap("gateEntryId", gateline.getGateEntry().getGateEntryId()));
		}
		}
	}
	

}
