package com.novelerp.alkyl.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.novelerp.alkyl.dao.GateEntryDao;
import com.novelerp.alkyl.dao.PRDao;
import com.novelerp.alkyl.dto.AdvanceShipmentNoticeDto;
import com.novelerp.alkyl.dto.GateEntryDto;
import com.novelerp.alkyl.dto.GateEntryLineDto;
import com.novelerp.alkyl.dto.GateEntryReadDto;
import com.novelerp.alkyl.dto.POReadDto;
import com.novelerp.alkyl.dto.PurchaseOrderDto;
import com.novelerp.alkyl.entity.GateEntry;
import com.novelerp.alkyl.service.GateEntryLineService;
import com.novelerp.alkyl.service.GateEntryService;
import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
@Service
public class GateEntryServiceImpl extends AbstractContextServiceImpl<GateEntry, GateEntryDto> implements GateEntryService{

	@Autowired
	private GateEntryDao gateEntryDao;
	
	@Autowired
	private GateEntryLineService gateEntryLineService;
	
	@Autowired
	private GateEntryService gateEntryService;
	
	@PostConstruct
	protected void init() {
		super.init(GateEntryServiceImpl.class, gateEntryDao, GateEntry.class, GateEntryDto.class);
		setByPassProxy(true);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public GateEntryDto save(GateEntryDto dto) {
		List<GateEntryLineDto> gateEntryLineDto=null;
		if(dto.getGateEntryLineList()!=null){
			gateEntryLineDto=dto.getGateEntryLineList();
			dto.setGateEntryLineList(null);
		}
		
		if(null==dto.getGateEntryId()){
			dto.setStatus(AppBaseConstant.GATEENTRYCREATED);
		dto.setReqNo(gateEntryDao.getNewReqNo());
		dto=super.save(dto);
		}else{
		updateDto(dto);
		}
		gateEntryLineService.saveLines(dto,gateEntryLineDto);
		return dto;
		
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public GateEntryDto saveGateEntry(GateEntryDto dto) {
		List<GateEntryLineDto> gateEntryLineDto=null;
		if(dto.getGateEntryLineList()!=null){
			gateEntryLineDto=dto.getGateEntryLineList();
			dto.setGateEntryLineList(null);
		}
		if(null==dto.getGateEntryId()){
		dto.setReqNo(gateEntryDao.getNewReqNo());
		dto=super.save(dto);
		}else{
//		update(dto);
			
			Map<String , Object> param = AbstractContextServiceImpl.getParamMap("status", AppBaseConstant.COMMERCIALAPPROVED);
			param.put("vendorName", dto.getVendorName());
			param.put("vendorAddress", dto.getVendorAddress());
			param.put("remark", dto.getRemark());
			param.put("docType", dto.getDocType());
//			param.put("rejectDesc", dto.getRejectDesc());
//			param.put("hodRejectDesc", dto.getHodRejectDesc());
			param.put("commRejectDesc", dto.getCommRejectDesc());
			param.put("plant", dto.getPlant());
			//param.put("purpose", dto.getPurpose());
			param.put("returnBy", dto.getReturnBy());
			param.put("vehicleNo", dto.getVehicleNo());
			param.put("vehicleType", dto.getVehicleType());
			param.put("transporterName", dto.getTransporterName());
			
			int result = gateEntryService.updateByJpql(param,AbstractContextServiceImpl.getParamMap("gateEntryId", dto.getGateEntryId()));
			//dto	=super.updateDto(dto);
		}
		gateEntryLineService.saveLines(dto,gateEntryLineDto);
		return dto;
		
	}
	
	
	
	
	
//	@Override
//	public List<GateEntryDto> getGateEntryByFilter(GateEntryReadDto dto) {
//		Map<String, Object> params=getParameterMap(dto);
//		String query=gateEntryDao.getGateEntryByFilter(dto);
//		List<GateEntryDto> poList=findDtosByQuery(query, params);
//		return poList;
//	}
//	private Map<String,Object> getParameterMap(GateEntryReadDto dto){
//		Map<String, Object> params = new HashMap<String, Object>();
//		if(dto.getReqNoFrom()!=null && dto.getReqNoTo()!=null) {
//			params.put("reqNoFrom", dto.getReqNoFrom());
//			params.put("reqNoTo", dto.getReqNoTo());
//		}
//		if(dto.getReqNoFrom()!=null && dto.getReqNoTo()==null){
//   			params.put("reqNoFrom", dto.getReqNoFrom());
//   		}
//   		if(dto.getReqNoFrom()==null && dto.getReqNoTo()!=null){
//   			params.put("reqNoTo", dto.getReqNoTo());
//   		}
//   		if(dto.getStatus()!=null){
//   			params.put("status", dto.getStatus());
//   		}
//   		if(dto.getDocType()!=null){
//   			params.put("docType", dto.getDocType());
//   		}
//   		if(dto.getPlant()!=null){
//   			params.put("plant", dto.getPlant());
//   		}
////   		if(dto.getReqDateFrom()!=null && dto.getReqDateTo()!=null){
////   			params.put("fromDate", dto.getReqDateFrom());
////			params.put("toDate", dto.getReqDateTo());
////   		}
////   		if(dto.getReqDateFrom()!=null && dto.getReqDateTo()==null){
////   			params.put("fromDate", dto.getReqDateFrom());
////   		}
////   		if(dto.getReqDateFrom()==null && dto.getReqDateTo()!=null){
////   			params.put("toDate", dto.getReqDateTo());
////   		}
//		return params;
//	}
		
}
