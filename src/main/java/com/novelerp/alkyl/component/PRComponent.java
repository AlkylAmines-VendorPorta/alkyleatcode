package com.novelerp.alkyl.component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.novelerp.alkyl.dto.PRAttachmentDto;
import com.novelerp.alkyl.dto.PRDto;
import com.novelerp.alkyl.dto.PRLineDto;
import com.novelerp.alkyl.dto.PurchaseOrderDto;
import com.novelerp.alkyl.dto.ThirdPartyPRApproverDto;
import com.novelerp.alkyl.service.PRAttachmentService;
import com.novelerp.alkyl.service.PRLineService;
import com.novelerp.alkyl.service.PRService;
import com.novelerp.alkyl.service.ThirdPartyPRApproverService;
import com.novelerp.appbase.master.dto.MailTemplateDto;
import com.novelerp.appbase.master.service.MailTemplateService;
import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.appcontext.dto.BPartnerDto;
import com.novelerp.appcontext.dto.UserDetailsDto;
import com.novelerp.appcontext.dto.UserDto;
import com.novelerp.appcontext.service.ContextService;
import com.novelerp.appcontext.service.MediaService;
import com.novelerp.appcontext.service.UserService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.commons.util.CommonUtil;
import com.novelerp.core.dto.Errors;
import com.novelerp.core.service.MailService;
import com.novelerp.eat.dto.BidderDto;
import com.novelerp.eat.service.BidderService;

@Component
public class PRComponent {
	private Logger log = LoggerFactory.getLogger(getClass());
	@Autowired
	@Qualifier(value=AppBaseConstant.FTP_MEDIA_SERVICE_IMPL)
	private MediaService mediaService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PRService prService;
	
	@Autowired
	private PRAttachmentService prAttService;
	
	@Autowired
	private ThirdPartyPRApproverService thirdPartyPRApproverService;
	
	@Autowired
	private BidderService bidderService;
	
	@Autowired
	private PRLineService prLineService;
	@Autowired
	private MailTemplateService mailTemplateService;

	@Autowired
	@Qualifier("jwtUserContext")
	private ContextService contextService;
	@Autowired
	private MailService mailService;
	
	public Errors fetchPRFromFTP(){
		Errors errors = new Errors();
		
			String[] listOfFiles = mediaService.getFilesListAt(AppBaseConstant.FTP_PR_DATA_TO_PROCESS_PATH);
			if(listOfFiles==null || listOfFiles.length==0){
				errors.addError("PR fetch Error", "No files found.");
				return errors;
			}
			for(String fileName: listOfFiles){
				try{
					byte[] byteOut = mediaService.getBISFromAttachment(fileName);
					ObjectMapper objJson = new ObjectMapper();
					PRDto prDto = objJson.readValue(byteOut, PRDto.class);
					
					if(prDto == null){
						errors.addError(fileName, "Empty file");
						continue;
					}
					
					prDto=savePR(prDto, errors, fileName);
					
					if(prDto.getPrId()!=null){
						mediaService.deleteFile(fileName);
					}
				}catch(Exception e){
					errors.addError("FTPError", e.getMessage());
				}
			}
		
		return errors;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	public PRDto savePR(PRDto prDto,Errors errors,String fileName){
			
		try{
			if(prDto == null){
				errors.addError(fileName, "Empty file");
				return prDto;
			}
			PRDto dto=prService.findDto("getPlainPRByPRNumber", AbstractContextServiceImpl.getParamMap("prNo", prDto.getPrNumber()));
			if(dto!=null){
				errors.addError(prDto.getPrNumber(), "PR already exist");
				return prDto;
			}
			
			UserDto requestedBy = prDto.getRequestedBy();
			
//			if(requestedBy==null || CommonUtil.isStringEmpty(prDto.getRequestedBy().getUserName())){
//				errors.addError(fileName, "Requested By cannot be empty");
//				return prDto;
//			}
			
			String reqBy = prDto.getRequestedBy().getUserName();
			
			requestedBy = userService.findDto("getUserBYUserName",AbstractContextServiceImpl.getParamMap("username", reqBy));
			
//			if(requestedBy==null || CommonUtil.isStringEmpty(prDto.getRequestedBy().getUserName())){
//				errors.addError(fileName, "Employee with employee code "+reqBy+", Not Found");
//				return prDto;
//			}
			
			for(PRLineDto prLine:prDto.getPrLines()){
				
				if(prLine.getDesiredVendor()!=null && !CommonUtil.isStringEmpty(prLine.getDesiredVendor().getUserName())){
					
					UserDto desiredVendor = userService.findDto("getUserBYUserName",AbstractContextServiceImpl.getParamMap("username", prLine.getDesiredVendor().getUserName()));
					
					if(desiredVendor==null){
						errors.addError(fileName, "Desired Vendor with employee code "+prLine.getDesiredVendor().getUserName()+", Not Found");
						return prDto;
					}
					prLine.setDesiredVendor(desiredVendor);
				}else{
					prLine.setDesiredVendor(null);
				}
				
				if(prLine.getFixedVendor()!=null && !CommonUtil.isStringEmpty(prLine.getFixedVendor().getUserName())){
					
					UserDto fixedVendor = userService.findDto("getUserBYUserName",AbstractContextServiceImpl.getParamMap("username", prLine.getFixedVendor().getUserName()));
					
					if(fixedVendor==null){
						errors.addError(fileName, "Fixed Vendor Vendor with employee code "+prLine.getFixedVendor().getUserName()+", Not Found");
						return prDto;
					}
					prLine.setFixedVendor(fixedVendor);
				}else{
					prLine.setFixedVendor(null);
				}
                if(prLine.getBuyer()!=null && !CommonUtil.isStringEmpty(prLine.getBuyer().getUserName())){
					
					//UserDto buyer = userService.findDto("getUserBYUserName",AbstractContextServiceImpl.getParamMap("username", prLine.getBuyer().getUserName()));
                	UserDto buyer = userService.findDto("getUserBYUserNameBuyer",AbstractContextServiceImpl.getParamMap("username", prLine.getBuyer().getUserName()));
					if(buyer==null){
						errors.addError(fileName, "Buyer with employee code "+prLine.getBuyer().getUserName()+", Not Found");
						return prDto;
					}
					prLine.setBuyer(buyer);
				}else{
					prLine.setBuyer(null);
				}
                prLine.setDescription(prLine.getItemText()); 
                prLine.setHeaderText(prDto.getHeaderText());
                
			}
			
			//prDto.setHeaderText(prDto.getHeaderText());
			//prDto.setHeaderText(prDto.getPrLines().get(0).getHeaderText());
			prDto.setCreatedBy(requestedBy);
			prDto.setUpdatedBy(requestedBy);
			prDto.setRequestedBy(requestedBy);
			prDto.setStatus(AppBaseConstant.PR_STATUS_CREATED);
			return prService.save(prDto);
		}catch(Exception e){
			errors.addError(fileName, e.getMessage());
			return prDto;
		}
	}
	
	
   public Boolean createPRFromSAP(PRDto prDto) {
		
		if(prDto == null){
			return false;
		}
		UserDto requestedBy = prDto.getRequestedBy();
		
//		if(requestedBy==null || CommonUtil.isStringEmpty(prDto.getRequestedBy().getUserName())){
//			errors.addError(fileName, "Requested By cannot be empty");
//			return prDto;
//		}
		
		String reqBy = prDto.getRequestedBy().getUserName();
		
		requestedBy = userService.findDto("getUserBYUserName",AbstractContextServiceImpl.getParamMap("username", reqBy));
		
//		if(requestedBy==null || CommonUtil.isStringEmpty(prDto.getRequestedBy().getUserName())){
//			errors.addError(fileName, "Employee with employee code "+reqBy+", Not Found");
//			return prDto;
//		}
		
		for(PRLineDto prLine:prDto.getPrLines()){
			
			if(prLine.getDesiredVendor()!=null && !CommonUtil.isStringEmpty(prLine.getDesiredVendor().getUserName())){
				
				UserDto desiredVendor = userService.findDto("getUserBYUserName",AbstractContextServiceImpl.getParamMap("username", prLine.getDesiredVendor().getUserName()));
				
				if(desiredVendor==null){
					return false;
				}
				prLine.setDesiredVendor(desiredVendor);
			}else{
				prLine.setDesiredVendor(null);
			}
			
			if(prLine.getFixedVendor()!=null && !CommonUtil.isStringEmpty(prLine.getFixedVendor().getUserName())){
				
				UserDto fixedVendor = userService.findDto("getUserBYUserName",AbstractContextServiceImpl.getParamMap("username", prLine.getFixedVendor().getUserName()));
				
				if(fixedVendor==null){
					return false;
				}
				prLine.setFixedVendor(fixedVendor);
			}else{
				prLine.setFixedVendor(null);
			}
            if(prLine.getBuyer()!=null && !CommonUtil.isStringEmpty(prLine.getBuyer().getUserName())){
				
				//UserDto buyer = userService.findDto("getUserBYUserName",AbstractContextServiceImpl.getParamMap("username", prLine.getBuyer().getUserName()));
            	UserDto buyer = userService.findDto("getUserBYUserNameBuyer",AbstractContextServiceImpl.getParamMap("username", prLine.getBuyer().getUserName()));
				if(buyer==null){
					return false;
				}
				prLine.setBuyer(buyer);
			}else{
				prLine.setBuyer(null);
			}
            prLine.setDescription(prLine.getItemText()); 
//            prLine.setDescription(prLine.getItemText() +"-"+ prLine.getMaterialPOText()); 
            prLine.setHeaderText(prDto.getHeaderText());
            
		}
		
		//prDto.setHeaderText(prDto.getHeaderText());
		//prDto.setHeaderText(prDto.getPrLines().get(0).getHeaderText());
		prDto.setCreatedBy(requestedBy);
		prDto.setUpdatedBy(requestedBy);
		prDto.setRequestedBy(requestedBy);
		prDto.setPartner(contextService.getPartner());
		prDto.setStatus(AppBaseConstant.PR_STATUS_CREATED);
		
		try {
		
			prDto = prService.save(prDto);
	
		}catch (Exception e) {
			e.printStackTrace();
//			log.info("ERROR",e);
		return false;
		}
		return true;
		
	}

	
	@Transactional(propagation=Propagation.REQUIRES_NEW,readOnly=true)
	public PRDto findPR(String prNumber){
		return prService.findDto("getPRByPRNumber", AbstractContextServiceImpl.getParamMap("prNo", prNumber));
	}
	
	
	@Transactional(propagation=Propagation.REQUIRES_NEW,readOnly=true)
	public PRAttachmentDto findPRAttById(Long prAttachmentId){
		return prAttService.findDto("findPRAttById", AbstractContextServiceImpl.getParamMap("prAttachmentId", prAttachmentId));
	}
	
	@Transactional(propagation=Propagation.REQUIRES_NEW,readOnly=true)
	public PRAttachmentDto findPRAttByattId(String AttachmentId){
		return prAttService.findDto("getPRAttByAttId", AbstractContextServiceImpl.getParamMap("fileName", AttachmentId));
	}
	
	
	@Transactional(propagation=Propagation.REQUIRED)
	public List<PRAttachmentDto> savePRAtt(List<PRAttachmentDto> prAttSet){
		return prAttService.save(prAttSet);
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public List<PRAttachmentDto> savePRDoc(List<PRAttachmentDto> prAttSet){
		return prAttService.savePRDoc(prAttSet);
	}
	
	
	@Transactional(propagation=Propagation.REQUIRED)
	public List<ThirdPartyPRApproverDto> saveThirdPartyPR(List<ThirdPartyPRApproverDto> tpPrApp){
		return thirdPartyPRApproverService.save(tpPrApp);
	}
	
	
	@Transactional(propagation=Propagation.REQUIRES_NEW,readOnly=true)
	public ThirdPartyPRApproverDto findThirdPartyById(Long thirdPartyPRApproverId) {
		return thirdPartyPRApproverService.findDto("findThirdPartyById", AbstractContextServiceImpl.getParamMap("thirdPartyPRApproverId", thirdPartyPRApproverId));
	}
	
	@Transactional(propagation=Propagation.REQUIRES_NEW,readOnly=true)
	public List<PRAttachmentDto> findAttbyPRId(Long prId){
		return prAttService.findDtos("getPRAttByPrId", AbstractContextServiceImpl.getParamMap("prId", prId));
	}
	
	@Transactional(propagation=Propagation.REQUIRES_NEW,readOnly=true)
	public List<ThirdPartyPRApproverDto> findThirdPartyPRId(Long prId) {
		return thirdPartyPRApproverService.findDtos("getThirdPartyPRByPrId", AbstractContextServiceImpl.getParamMap("prId", prId));
	}
	
	@Transactional(propagation=Propagation.REQUIRES_NEW,readOnly=true)
	public List<PRLineDto> findPRById(Long prId){
		return prLineService.findDtos("getPRLinebyPrId", AbstractContextServiceImpl.getParamMap("prId", prId));
	}
	
	@Transactional(propagation=Propagation.REQUIRES_NEW,readOnly=true)
	public List<PRDto> getPR(String role, UserDetailsDto userDetailsDto){
		try{
			List<String> param = new ArrayList<>();
			Map<String, Object> params= new HashMap<>();		
			String plant=userDetailsDto.getPlant();
			if(AppBaseConstant.ROLE_REQUISTIONER_ADMIN.equals(role)){
				param.add(AppBaseConstant.PR_STATUS_RELEASED);
				param.add(AppBaseConstant.PR_STATUS_REJECTED);
				
				params.put("status", param);
				params.put("plant", plant);
				return prService.findDtos("getPRbyRole", params);
			}else if(AppBaseConstant.ROLE_APPROVER_ADMIN.equals(role)){
				param.add(AppBaseConstant.PR_STATUS_IN_PROGRESS);
				
				params.put("status", param);
				params.put("plant", plant);
				return prService.findDtos("getPRbyRole", params);
			}else if(AppBaseConstant.ROLE_PURCHASE_MANAGER_ADMIN.equals(role)){
				param.add(AppBaseConstant.PR_STATUS_ACCEPTED);
				
				params.put("status", param);
				params.put("plant", plant);
				return prService.findDtos("getPRbyRole", params);
			}else if(AppBaseConstant.ROLE_BUYER_ADMIN.equals(role)){
				param.add(AppBaseConstant.PR_STATUS_BUYER_ASSIGNED);
				
				params.put("status", param);
				params.put("plant", plant);
				return prService.findDtos("getPRbyRole", params);
			}else if(role.equals(AppBaseConstant.ROLE_PARTNER_ADMIN)){
				return prService.findDtos("getPR", null);
			}
		}catch(NullPointerException n){
			log.info("ERROR Plant is null for PR List",n);
		}catch(Exception e){
			log.info("ERROR",e);
		}
		return null;
	}
	
	
	public List<PRDto> getPRforEnquiry(String role, UserDetailsDto userDetails) {
		
		if(role.equals(AppBaseConstant.ROLE_PARTNER_ADMIN)){
			return prService.findDtos("getPRforEnquiryAdmin", null);
		}	
		
		return prService.findDtos("getPRforEnquiry", AbstractContextServiceImpl.getParamMap("plant", userDetails.getPlant()));
	}
	
	@Transactional(propagation=Propagation.REQUIRES_NEW,readOnly=true)
	public List<PRLineDto> getPRLine(String role, UserDetailsDto userDetailsDto){
		try{
			List<String> param = new ArrayList<>();
			Map<String, Object> params= new HashMap<>();			
			//String plant=userDetailsDto.getPlant();
			param.add(AppBaseConstant.PR_STATUS_ACCEPTED);
			
			params.put("status", param);
			/*params.put("plant", plant);*/
			return prLineService.findDtos("getPRLineByRole", params);
		}catch(NullPointerException n){
			log.info("ERROR Plant is null for PR List",n);
		}catch(Exception e){
			log.info("ERROR",e);
		}
		return null;
	}
	@Transactional(propagation=Propagation.REQUIRES_NEW,readOnly=true)
	public List<PRLineDto> findPRLineForBuyer(Long buyerId){
		return prLineService.findDtos("getPRLineForBuyer",null);
	}
	
	@Transactional(propagation=Propagation.REQUIRES_NEW,readOnly=true)
	public void sendMainQuatationSubmition(Long bidderId) {
		BidderDto bidder=bidderService.findDto("getBidByBidderId", AbstractContextServiceImpl.getParamMap("bidderId", bidderId));
		MailTemplateDto mailTemplate = mailTemplateService
				.getMailTemplateFromTemplateType(AppBaseConstant.QUATATION_SUBMITION_TEMPLATE);
		if (mailTemplate != null && bidder != null && bidder.getEnquiry().getCreatedBy().getEmail() != null) {
			// Map<String, Object> params =
			// AbstractContextServiceImpl.getParamMap("plant",
			// asnDto.getPo().getPoLineList().get(0).getPlant());
			// params.put("roleValue",
			// AppBaseConstant.ROLE_COMMERCIAL_ADMIN);
			// UserDto commercial =
			// userService.findDto("getUserByRoleAndPlant", params);
			Map<String, Object> map = new HashMap<>();
			String subject = mailTemplate.getSubject();
//			subject = subject.replace("@ENQ_NO@", bidder.getEnquiry().getEnqNo());
			subject = subject.replace("@ENQ_NO@", bidder.getEnquiry().getEnquiryId().toString());
			mailTemplate.setSubject(subject);
//			map.put("@ENQ_NO@", bidder.getEnquiry().getEnqNo());
			map.put("@ENQ_NO@", bidder.getEnquiry().getEnquiryId().toString());
			map.put("@VendorName@", bidder.getPartner().getName());

			List<String> toEmailIds = new ArrayList<>();
			toEmailIds.add(bidder.getEnquiry().getCreatedBy().getEmail());
			

			List<String> ccEmailIds = new ArrayList<>();
			//ccEmailIds.add(reqBy.getEmail());
		//	 ccEmailIds.add(AppBaseConstant.PURCHASE_TEAM);
			// if (commercial != null) {
			// ccEmailIds.add(commercial.getEmail());
			// }

			mailService.sentMailByTemplateAsync(mailTemplate, map, toEmailIds, ccEmailIds, true);
		}
		
	}
	@Transactional(propagation=Propagation.REQUIRES_NEW,readOnly=true)
	public PRLineDto findPRLineByPR(String prNumber, Long prID,String lineNo){
		Map<String, Object> params = new HashMap<String, Object>();
//		params.put("prId", prID);
		params.put("prNumber", prNumber);
		params.put("lineNo", lineNo);
		return prLineService.findDto("getPRLineByPRANDLINE",params);
	}
	
}
