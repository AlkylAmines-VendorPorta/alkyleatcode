package com.novelerp.alkyl.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.novelerp.alkyl.controller.PurchaseOrderController;
import com.novelerp.alkyl.dao.PurchaseOrderDao;
import com.novelerp.alkyl.dto.POReadDto;
import com.novelerp.alkyl.dto.PurchaseOrderDto;
import com.novelerp.alkyl.dto.PurchaseOrderLineDto;
import com.novelerp.alkyl.entity.PurchaseOrder;
import com.novelerp.alkyl.service.PurchaseOrderLineService;
import com.novelerp.alkyl.service.PurchaseOrderService;
import com.novelerp.appbase.master.dto.AttachmentDto;
import com.novelerp.appbase.master.dto.SFTPUploadDto;
import com.novelerp.appbase.master.service.AttachmentService;
import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.appcontext.dto.BPartnerDto;
import com.novelerp.appcontext.service.MediaService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.eat.dto.TenderCommitteeDto;

@Service
public class PurchaseOrderServiceImpl extends AbstractContextServiceImpl <PurchaseOrder,PurchaseOrderDto> implements PurchaseOrderService {

	@Autowired
	private PurchaseOrderDao purchaseorder;
	
	@Autowired
	private PurchaseOrderLineService poLineService;
	
	@Autowired
	private AttachmentService attachmentService;
	
	@Autowired
	@Qualifier(AppBaseConstant.FTP_MEDIA_SERVICE_IMPL)
	private MediaService mediaService;
	
	@PostConstruct
	public void init(){
		super.init(PurchaseOrderServiceImpl.class, purchaseorder, PurchaseOrder.class, PurchaseOrderDto.class);
		setByPassProxy(true);
	}
	
	/*@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public PurchaseOrderDto save(PurchaseOrderDto dto) {
		List<PurchaseOrderLineDto> poLineList= dto.getPoLineList();
		dto.setPoLineList(null);
		dto = super.save(dto);
		poLineService.save(poLineList, dto, dto.getPartner());
		return dto;
	}*/
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public PurchaseOrderDto save(PurchaseOrderDto dto) {
		List<PurchaseOrderLineDto> poLineList= dto.getPoLineList();
		dto.setPoLineList(null);
		PurchaseOrderDto oldDto = findDto("getPObyNumber", AbstractContextServiceImpl.getParamMap("poNumber", dto.getPurchaseOrderNumber()));
//		AttachmentDto poAtt = attachmentService.save(getPOAttachemnt(dto));
//		dto.setPoAtt(poAtt);
		if(oldDto!=null){
			/*poLineService.updateByJpql(AbstractContextServiceImpl.getParamMap("isActive", "N"), 
					AbstractContextServiceImpl.getParamMap("purchaseOrder.purchaseOrderId", oldDto.getPurchaseOrderId()));*/
			oldDto = getNewDtoFromOldDto(oldDto,dto);
			oldDto = super.updateDto(oldDto);
		}else{
			
			oldDto = super.save(dto);
		}
		poLineService.save(poLineList, oldDto, oldDto.getPartner());
		
//		poLineService.updateisActive(poLineList, oldDto, oldDto.getPartner());
		
		return dto;
	}
	
	private AttachmentDto getPOAttachemnt(PurchaseOrderDto po){
		AttachmentDto poAtt = new AttachmentDto();
		poAtt.setName(po.getPurchaseOrderNumber()+".pdf");
		poAtt.setFileName(po.getPurchaseOrderNumber()+".pdf");
		SFTPUploadDto dto = mediaService.getCredentials();
		poAtt.setPath(dto.getPath()+AppBaseConstant.FTP_PO_PDF_PATH);
		poAtt.setFileExtension("pdf");
		poAtt.setPartner(po.getPartner());
		return poAtt;
	}
	
	private PurchaseOrderDto getNewDtoFromOldDto(PurchaseOrderDto oldDto,PurchaseOrderDto newDto){
		oldDto.setCode(newDto.getCode());
		oldDto.setDate(newDto.getDate());
		oldDto.setDescription(newDto.getDescription());
		oldDto.setDocumentType(newDto.getDocumentType());
		oldDto.setIncomeTerms(newDto.getIncomeTerms());
		oldDto.setName(newDto.getName());
		oldDto.setPurchaseGroup(newDto.getPurchaseGroup());
		oldDto.setRemark(newDto.getRemark());
		oldDto.setStatus(newDto.getStatus());
		oldDto.setVendorCode(newDto.getVendorCode());
		oldDto.setVendorName(newDto.getVendorName());
		oldDto.setVersionNumber(newDto.getVersionNumber());
		/* partner Added on 28-8-2023*/
		oldDto.setPartner(newDto.getPartner());
		//oldDto.setPoAtt(newDto.getPoAtt());
		oldDto.setCreatedBy(newDto.getCreatedBy());
		oldDto.setUpdatedBy(newDto.getUpdatedBy());
		oldDto.setPrDate(newDto.getPrDate());
		//oldDto.setOutboundDeliveryNo(newDto.getOutboundDeliveryNo());
		oldDto.setPoTypeMsg(newDto.getPoTypeMsg());
		oldDto.setUserID(newDto.getUserID());
//		if("X".equalsIgnoreCase(newDto.getDeleted())){
//			oldDto.setDeleted(newDto.getDeleted());
//			oldDto.setIsActive("N");
//		}
//		if("D".equalsIgnoreCase(newDto.getDeleted())){
//			//oldDto.setDeleted(newDto.getDeleted());
//			oldDto.setIsActive("N");
//		}
//		if("B".equalsIgnoreCase(newDto.getBlocked())){
//			//oldDto.setDeleted(newDto.getBlocked());
//			oldDto.setIsActive("N");
//			
//		}
		return oldDto;
	}

	@Override
	public Long getPobyPartnerIdNew(Long partnerId){
		return purchaseorder.getPobyPartnerId(partnerId);
	}
	@Override
	public Long getReleasedPobyPartnerIdNew(Long partnerId){
		return purchaseorder.getReleasedPobyPartnerId(partnerId);
	}
	@Override
	public Long getRejectedPobyPartnerIdNew(Long partnerId){
		return purchaseorder.getRejectedPobyPartnerId(partnerId);
	}
	@Override
	public Long getAcceptedPobyPartnerIdNew(Long partnerId){
		return purchaseorder.getAcceptedPobyPartnerId(partnerId);
	}

	@Override
	public Long getOpenPoCountbyPartnerId(Long partnerId) {
		Map<String, Object> params = new HashMap<>();
		params.put("partnerId", partnerId);
		params.put("status", AppBaseConstant.PO_STATUS_CLOSED);
		return getRecordCount("c.partner.bPartnerId = :partnerId and c.isActive = 'Y' and c.status !=:status ", params);
	}

	@Override
	public List<PurchaseOrderDto> getPoByFilter(POReadDto dto) {
		Map<String, Object> params=getParameterMap(dto);
		String query=purchaseorder.getPOByFilter(dto);
		List<PurchaseOrderDto> poList=findDtosByQuery(query, params);
		return poList;
	}
	private Map<String,Object> getParameterMap(POReadDto dto){
		Map<String, Object> params = new HashMap<String, Object>();
		if(dto.getPoNoFrom()!=null && dto.getPoNoTo()!=null){
			params.put("fromNo", dto.getPoNoFrom());
			params.put("toNo", dto.getPoNoTo());
   		}
   		if(dto.getPoNoFrom()!=null && dto.getPoNoTo()==null){
   			params.put("fromNo", dto.getPoNoFrom());
   		}
   		if(dto.getPoNoFrom()==null && dto.getPoNoTo()!=null){
   			params.put("toNo", dto.getPoNoTo());
   		}
   		if(dto.getPoDateFrom()!=null && dto.getPoDateTo()!=null){
   			params.put("fromDate", dto.getPoDateFrom());
			params.put("toDate", dto.getPoDateTo());
   		}
   		if(dto.getPoDateFrom()!=null && dto.getPoDateTo()==null){
   			params.put("fromDate", dto.getPoDateFrom());
   		}
   		if(dto.getPoDateFrom()==null && dto.getPoDateTo()!=null){
   			params.put("toDate", dto.getPoDateTo());
   		}
   		if(dto.getEmpCode()!=null && dto.getVendorCode()!=null){
   			params.put("id", dto.getEmpCode());
   			params.put("vendorCode", dto.getVendorCode());
   		}
   		if(dto.getEmpCode()!=null && dto.getVendorCode()==null){   			
   			params.put("id", dto.getEmpCode());
   		}
   		if(dto.getEmpCode()==null && dto.getVendorCode()!=null){   			
   			params.put("vendorCode", dto.getVendorCode());
   		}
   		if(dto.getIsVendorFilter()){
   			params.put("bid", dto.getbPartnerId());
   		}
   		
   		if(dto.getOutboundDeliveryNo() != null){
   			params.put("outboundDeliveryNo", dto.getOutboundDeliveryNo());
   		}
   		return params;
	}
	
	
	
}
