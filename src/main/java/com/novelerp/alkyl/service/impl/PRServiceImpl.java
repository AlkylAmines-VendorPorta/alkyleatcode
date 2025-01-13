package com.novelerp.alkyl.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.novelerp.alkyl.component.PRComponent;
import com.novelerp.alkyl.dao.PRDao;
import com.novelerp.alkyl.dto.POReadDto;
import com.novelerp.alkyl.dto.PRDto;
import com.novelerp.alkyl.dto.PRLineDto;
import com.novelerp.alkyl.dto.PRReadDto;
import com.novelerp.alkyl.dto.PurchaseOrderDto;
import com.novelerp.alkyl.entity.PR;
import com.novelerp.alkyl.service.PRAttachmentService;
import com.novelerp.alkyl.service.PRLineService;
import com.novelerp.alkyl.service.PRService;
import com.novelerp.alkyl.service.VendorEnquiryService;
import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.appcontext.dto.BPartnerDto;
import com.novelerp.appcontext.dto.UserDto;
import com.novelerp.appcontext.entity.User;
import com.novelerp.appcontext.service.BPartnerService;
import com.novelerp.appcontext.service.ContextService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.core.dto.ResponseDto;
import com.novelerp.core.exception.CustomException;
import com.novelerp.core.util.DateUtil;
import com.novelerp.eat.service.ItemBidService;

@Service
public class PRServiceImpl extends AbstractContextServiceImpl<PR, PRDto> implements PRService {

	@Autowired
	private PRDao prDao;
	
	@Autowired
	private PRComponent prComponent;
	
	@Autowired
	private PRLineService prLineService;
	
	@Autowired
	private PRAttachmentService prAttService;
	
	@Autowired
	private ItemBidService itemBidService;
	
	@Autowired
	private VendorEnquiryService vendorEnquiryService;
	
	@Autowired
	@Qualifier("jwtUserContext")
	private ContextService contextService;
	
	@Autowired
	private BPartnerService partnerService;
	
	@Autowired
	private PRService prService;
	
	@PostConstruct
	protected void init() {
		// TODO Auto-generated method stub
		super.init(PRServiceImpl.class, prDao, PR.class, PRDto.class);
		setByPassProxy(true);
	}
	
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public PRDto save(PRDto dto) {
		if(dto==null){
			return new PRDto();
		}
		
		List<PRLineDto> prLines = dto.getPrLines();
		dto.setPrLines(null);
		PRDto oldDto=findDto("getPRByPRNumber", AbstractContextServiceImpl.getParamMap("prNo", dto.getPrNumber()));
		//PRDto oldDto = prComponent.findPR(dto.getPrNumber());
	
		if(oldDto!=null){
			/*poLineService.updateByJpql(AbstractContextServiceImpl.getParamMap("isActive", "N"), 
					AbstractContextServiceImpl.getParamMap("purchaseOrder.purchaseOrderId", oldDto.getPurchaseOrderId()));*/
			oldDto = copyNewDtoToOld(dto, oldDto);
			oldDto = super.updateDto(oldDto);
		}else{
			
			oldDto = super.save(dto);
		}
//		if(oldDto!=null){
//			//dto = copyNewDtoToOld(dto, oldDto);
//			oldDto = copyNewDtoToOld(oldDto,dto);
//			oldDto = super.updateDto(oldDto);
//		}
//		
//		if(dto.getPrId()==null){
//			dto = super.save(dto);
//		}
//		else{
//			dto = super.updateDto(dto);
//		}
		
//		prLines = prLineService.save(prLines,dto);
		//prLines = prLineService.save(prLines,oldDto,oldDto.getPartner());
		prLineService.save(prLines,oldDto,oldDto.getPartner());
		//dto.setPrLines(prLines);
		return dto;
	}
	
	private PRDto copyNewDtoToOld(PRDto newDto, PRDto oldDto){
		oldDto.setCode(newDto.getCode());
		oldDto.setDescription(newDto.getDescription());
		oldDto.setDocType(newDto.getDocType());
		oldDto.setIsTC(newDto.getIsTC());
		oldDto.setName(newDto.getName());
		oldDto.setPstyp(newDto.getPstyp());
		oldDto.setRequestedBy(newDto.getRequestedBy());
		oldDto.setStatus(newDto.getStatus());
		
		BPartnerDto bpartnerdto =contextService.getPartner();
	//	oldDto.setpartsetbPartnerId(bpartnerdto.getbPartnerId());
		//BPartnerDto partner = partnerService.findDto("getPartnerByVendorSAPCodeForPO",AbstractContextServiceImpl.getParamMap("vendorCode", contextService.getPartner().getbPartnerId()));
		oldDto.setPartner(bpartnerdto);
		
		return oldDto;
	}


	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public PRDto updatePRSubmit(PRDto prDto) {
		long result=0;ResponseDto response=null;
		
		HashMap<String, Object> prMap=new HashMap<>();
		prMap.put("priority", prDto.getPriority());
		prMap.put("isTC", prDto.getIsTC());
		prMap.put("status", AppBaseConstant.PR_STATUS_RELEASED);
		prDto.setStatus(AppBaseConstant.PR_STATUS_RELEASED);
		
		User releasedBy= new User();
		releasedBy.setUserId(contextService.getUser().getUserId());
		prMap.put("releasedBy", releasedBy);
		prMap.put("releasedDate", new Date());
		
		if("Y".equals(prDto.getIsTC())){
			prMap.put("tcApprover.userId", prDto.getTcApprover().getUserId());
		}
		
//		prDto.setPrAttSet(prComponent.savePRAtt(prDto.getPrAttSet()));
		prDto.setPrAttSet(prComponent.savePRDoc(prDto.getPrAttSet()));
		prDto.setEmailSet(prComponent.saveThirdPartyPR(prDto.getEmailSet()));
		
		result=updateByJpql(prMap, AbstractContextServiceImpl.getParamMap("prId", prDto.getPrId()));
		
		if(result>0){
			for(PRLineDto prLine:prDto.getPrLines()){
				
				HashMap<String, Object> prLineMap=new HashMap<>();
				prLineMap.put("deliverDate", prLine.getDeliverDate());
				prLineMap.put("requiredDate", prLine.getRequiredDate());
				prLineMap.put("description", prLine.getDescription());
				prLineMap.put("headerText", prLine.getHeaderText());
				if(prLine.getBuyerId()!=null){
					User user = new User();
					user.setUserId(prLine.getBuyerId());
				prLineMap.put("buyer", user);
				}
				result=prLineService.updateByJpql(prLineMap, AbstractContextServiceImpl.getParamMap("prLineId", prLine.getPrLineId()));
			}
		}
		
		if(result>0){
			response=new ResponseDto(false, "Record Updated");
			prDto.setResponse(response);
			return prDto;
		}
		
		response=new ResponseDto(true, "Failed Updated");
		prDto.setResponse(response);
		return prDto;
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public PRDto updatePRSave(PRDto prDto) {
		long result=0;ResponseDto response=null;
		
		HashMap<String, Object> prMap=new HashMap<>();
		prMap.put("priority", prDto.getPriority());
		prMap.put("isTC", prDto.getIsTC());
		
		if("Y".equals(prDto.getIsTC())){
			prMap.put("tcApprover.userId", prDto.getTcApprover().getUserId());
		}
		
		prDto.setPrAttSet(prComponent.savePRAtt(prDto.getPrAttSet()));
		prDto.setEmailSet(prComponent.saveThirdPartyPR(prDto.getEmailSet()));
		
		result=updateByJpql(prMap, AbstractContextServiceImpl.getParamMap("prId", prDto.getPrId()));
		
		if(result>0){
			for(PRLineDto prLine:prDto.getPrLines()){
				
				HashMap<String, Object> prLineMap=new HashMap<>();
				prLineMap.put("deliverDate", prLine.getDeliverDate());
				prLineMap.put("requiredDate", prLine.getRequiredDate());
				prLineMap.put("description", prLine.getDescription());
				prLineMap.put("headerText", prLine.getHeaderText());
				if(prLine.getBuyerId()!=null){
					User user = new User();
					user.setUserId(prLine.getBuyerId());
				prLineMap.put("buyer", user);
				}
				result=prLineService.updateByJpql(prLineMap, AbstractContextServiceImpl.getParamMap("prLineId", prLine.getPrLineId()));
			}
		}
		
		if(result>0){
			response=new ResponseDto(false, "Record Updated");
			prDto.setResponse(response);
			return prDto;
		}
		
		response=new ResponseDto(true, "Failed Updated");
		prDto.setResponse(response);
		return prDto;
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public PRDto updatePRApprove(PRDto prDto) {
		long result=0;ResponseDto response=null;
		
		HashMap<String, Object> prMap=new HashMap<>();
		prMap.put("priority", prDto.getPriority());
		prMap.put("isTC", prDto.getIsTC());
		prMap.put("status", AppBaseConstant.PR_STATUS_ACCEPTED);
		prDto.setStatus(AppBaseConstant.PR_STATUS_ACCEPTED);
		prMap.put("approvedBy.userId", prDto.getApprovedBy().getUserId());
		prMap.put("approvedDate", new Date());
		
		if("Y".equals(prDto.getIsTC())){
			prMap.put("tcApprover.userId", prDto.getTcApprover().getUserId());
		}
		
		//prDto.setPrAttSet(prAttService.save(prDto.getPrAttSet()));
		prDto.setPrAttSet(prComponent.savePRDoc(prDto.getPrAttSet()));
		prDto.setEmailSet(prComponent.saveThirdPartyPR(prDto.getEmailSet()));
		
		result=updateByJpql(prMap, AbstractContextServiceImpl.getParamMap("prId", prDto.getPrId()));
		
		if(result>0){
			for(PRLineDto prLine:prDto.getPrLines()){
				
				HashMap<String, Object> prLineMap=new HashMap<>();
				prLineMap.put("deliverDate", prLine.getDeliverDate());
				prLineMap.put("requiredDate", prLine.getRequiredDate());
				prLineMap.put("description", prLine.getDescription());
				prLineMap.put("headerText", prLine.getHeaderText());
				if(prLine.getBuyerId()!=null){
					User user = new User();
					user.setUserId(prLine.getBuyerId());
				prLineMap.put("buyer", user);
				}
				result=prLineService.updateByJpql(prLineMap, AbstractContextServiceImpl.getParamMap("prLineId", prLine.getPrLineId()));
			}
		}
		
		if(result>0){
			response=new ResponseDto(false, "Record Updated");
			prDto.setResponse(response);
			return prDto;
		}
		
		response=new ResponseDto(true, "Failed Updated");
		prDto.setResponse(response);
		return prDto;
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public ResponseDto updatePRReject(Long prId,String remarks) {
		long result=0;
		
		HashMap<String, Object> prMap=new HashMap<>();
		
		prMap.put("status", AppBaseConstant.PR_STATUS_REJECTED);
		prMap.put("remarks", remarks);
		
		result=updateByJpql(prMap, AbstractContextServiceImpl.getParamMap("prId", prId));
		
		if(result>0){
			return new ResponseDto(false, "Record Updated");
		}
		
		return new ResponseDto(true, "Failed Updated");
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public PRDto updatePRBuyerAssign(PRDto prDto) { 
		long result=0;ResponseDto response=null;
		
		HashMap<String, Object> prMap=new HashMap<>();
		/*prMap.put("buyer.userId", prDto.getBuyer().getUserId());
		prMap.put("bidEndDate", prDto.getBidEndDate());*/
		prMap.put("status", AppBaseConstant.PR_STATUS_BUYER_ASSIGNED);
		prDto.setStatus(AppBaseConstant.PR_STATUS_BUYER_ASSIGNED);
		
		result=updateByJpql(prMap, AbstractContextServiceImpl.getParamMap("prId", prDto.getPrId()));
		
		if(result>0){
			for(PRLineDto prLine:prDto.getPrLines()){
				
				HashMap<String, Object> prLineMap=new HashMap<>();
				prLineMap.put("deliverDate", prLine.getDeliverDate());
				prLineMap.put("requiredDate", prLine.getRequiredDate());
				prLineMap.put("description", prLine.getDescription());
				prLineMap.put("buyer.userId", prLine.getBuyer().getUserId());
				result=prLineService.updateByJpql(prLineMap, AbstractContextServiceImpl.getParamMap("prLineId", prLine.getPrLineId()));
			}
			if(result>0){
				response=new ResponseDto(false, "Record Updated");
				prDto.setResponse(response);
				return prDto;
			}
		}
		
		response=new ResponseDto(true, "Failed Updated");
		prDto.setResponse(response);
		return prDto;
	}


	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void generateQCF(Long prId) {
		
		int result=0;
		
		result=itemBidService.updateLowestBid(prId);
		
		HashMap<String, Object> prMap=new HashMap<>();
		prMap.put("qcfNo", generateQCFNo());
		prMap.put("code", "close");
		prMap.put("bidEndDate",new Date());
		result=vendorEnquiryService.updateByJpql(prMap, AbstractContextServiceImpl.getParamMap("enquiryId", prId));
		
		if(!(result>0)){
			throw new CustomException("Unable to Create QCF of Enquiry "+prId);
		}
			
	}


	private String generateQCFNo() {
		Random random = new Random();
		String randomNo = String.format("%04d", random.nextInt(10000));
		return (DateUtil.getYear(new Date())+randomNo).toString();
	}


	@Override
	public List<Object> getQCFReport(Long prId) {
		// TODO Auto-generated method stub
		return prDao.getQCFReport(prId);
	}


	@Override
	public List<PRDto> getPRByFilter(PRReadDto dto) {
		Map<String, Object> params=getParameterMap(dto);
		String query=prDao.getPRByFilter(dto);
		List<PRDto> prList=findDtosByQuery(query, params);
		return prList;
	}
	private Map<String,Object> getParameterMap(PRReadDto dto){
		Map<String, Object> params = new HashMap<String, Object>();
		if(dto.getPrNoFrom()!=null && dto.getPrNoTo()!=null){
			params.put("fromNo", dto.getPrNoFrom());
			params.put("toNo", dto.getPrNoTo());
   		}
   		if(dto.getPrNoFrom()!=null && dto.getPrNoTo()==null){
   			params.put("fromNo", dto.getPrNoFrom());
   		}
   		if(dto.getPrNoFrom()==null && dto.getPrNoTo()!=null){
   			params.put("toNo", dto.getPrNoTo());
   		}
   		if(dto.getPrDateFrom()!=null && dto.getPrDateTo()!=null){
   			params.put("prDateFrom", dto.getStartprDateFrom());
   			dto.setPrDateFrom(dto.getStartprDateFrom());
   			params.put("prDateTo", dto.getLastprDateTo());
   			dto.setPrDateTo(dto.getLastprDateTo());
//   			params.put("fromDate", dto.getPrDateFrom());
//			params.put("toDate", dto.getPrDateTo());
   		}
   		if(dto.getPrDateFrom()!=null && dto.getPrDateTo()==null){
//   			params.put("fromDate", dto.getPrDateFrom());
   			params.put("prDateFrom", dto.getStartprDateFrom());
			params.put("prDateTo", dto.getLastprDateFrom());
			dto.setPrDateFrom((dto.getStartprDateFrom()));
			dto.setPrDateTo(dto.getLastprDateFrom());
   		}
   		if(dto.getPrDateFrom()==null && dto.getPrDateTo()!=null){
//   			params.put("toDate", dto.getPrDateTo());
   			params.put("prDateFrom", dto.getStartprDateTo());
			params.put("prDateTo", dto.getLastprDateTo());
			dto.setPrDateFrom(dto.getStartprDateTo());
			dto.setPrDateTo(dto.getLastprDateTo());
   		}
   		if(dto.getStatus()!=null){
   			params.put("status", dto.getStatus());
   		}
   		if(dto.getBuyerCode()!=null){
   			params.put("buyer", dto.getBuyerCode());
   		}
   		if(dto.getPlant()!=null){
   			params.put("plant", dto.getPlant());
   		}
   		return params;
	}
	
}
