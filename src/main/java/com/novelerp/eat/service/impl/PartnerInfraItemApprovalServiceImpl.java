package com.novelerp.eat.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.novelerp.appbase.master.dto.InfraApprovalLevelDto;
import com.novelerp.appbase.master.dto.PartnerInfraItemDto;
import com.novelerp.appbase.master.service.PartnerInfraItemService;
import com.novelerp.appbase.master.service.PartnerItemDrawingDocService;
import com.novelerp.appbase.master.service.PartnerItemTypeTestDetailsService;
import com.novelerp.appcontext.dto.UserDto;
import com.novelerp.appcontext.service.UserService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.commons.util.CommonUtil;
import com.novelerp.core.dto.CustomResponseDto;
import com.novelerp.core.dto.MailDto;
import com.novelerp.core.dto.ResponseDto;
import com.novelerp.core.exception.CustomException;
import com.novelerp.core.service.MailService;
import com.novelerp.eat.dao.PartnerInfraItemApprovalDao;
import com.novelerp.eat.dto.PartnerInfraItemApprovalDto;
import com.novelerp.eat.entity.PartnerInfraItemApproval;
import com.novelerp.eat.service.PartnerInfraItemApprovalService;

@Service
public class PartnerInfraItemApprovalServiceImpl extends AbstractContextServiceImpl<PartnerInfraItemApproval, PartnerInfraItemApprovalDto> implements PartnerInfraItemApprovalService{
	
	@Autowired
	private PartnerInfraItemApprovalDao partnerInfraItemApprovalDao;
	
	@Autowired
	private PartnerInfraItemService partnerInfraItemService;
	
	@Autowired
	private PartnerItemTypeTestDetailsService partnerItemTypeTestDetailsService;
	
	@Autowired
	private PartnerItemDrawingDocService partnerItemDrawingDocService;
	
	@Autowired
	private MailService mailService;
	
	@Autowired
	private UserService userService;
		
	@PostConstruct
	private void init() {
		super.init(PartnerInfraItemApprovalServiceImpl.class, partnerInfraItemApprovalDao, PartnerInfraItemApproval.class, PartnerInfraItemApprovalDto.class);
		setByPassProxy(true);
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	private List<PartnerInfraItemApprovalDto> createPartnerInfraItemApprovalLine(List<InfraApprovalLevelDto> infraApprovalLevel,PartnerInfraItemDto partnerInfraItem){
		List<PartnerInfraItemApprovalDto> partnerInfraItemApprovalDtoList=new ArrayList<PartnerInfraItemApprovalDto>();
		if(!CommonUtil.isCollectionEmpty(infraApprovalLevel)){
			for(InfraApprovalLevelDto dto:infraApprovalLevel){
				PartnerInfraItemApprovalDto partnerInfraItemApprovalLine=new PartnerInfraItemApprovalDto();
				partnerInfraItemApprovalLine.setInfraApprovalLevel(dto);
				partnerInfraItemApprovalLine.setPartnerInfraItem(partnerInfraItem);
				partnerInfraItemApprovalLine=savePartnerInfraItemApprovalLine(partnerInfraItemApprovalLine);
				partnerInfraItemApprovalDtoList.add(partnerInfraItemApprovalLine);	
			}
		}
		return partnerInfraItemApprovalDtoList;
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public CustomResponseDto createInfraItemLine(List<InfraApprovalLevelDto> infraApprovalLevels,PartnerInfraItemDto partnerInfraItem){
    	CustomResponseDto response=new CustomResponseDto();
		createPartnerInfraItemApprovalLine(infraApprovalLevels, partnerInfraItem);
		response.addObject("result", true);
		response.addObject("message", "success:Line Created");
		return response;
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public PartnerInfraItemApprovalDto savePartnerInfraItemApprovalLine(PartnerInfraItemApprovalDto partnerInfraItemApprovalDto){
		if(partnerInfraItemApprovalDto.getPartnerInfraItemApprovalId() == null){
			return super.save(partnerInfraItemApprovalDto);
		}
		return super.updateDto(partnerInfraItemApprovalDto);	
		}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public CustomResponseDto uploadClarification(PartnerInfraItemApprovalDto dto){
		CustomResponseDto response=new CustomResponseDto();
		if(dto!=null && dto.getPartner()!=null && dto.getPartner().getbPartnerId()!=null 
		&& dto.getPartnerInfraItem()!=null && dto.getPartnerInfraItem().getPartnerInfraItemId()!=null
		&& dto.getPartnerInfraItem().getAttachment()!=null && dto.getPartnerInfraItem().getAttachment().getAttachmentId()!=null){
		boolean result=partnerInfraItemService.uploadClarification(dto.getPartnerInfraItem().getPartnerInfraItemId(), dto.getPartner().getbPartnerId(), dto.getPartnerInfraItem().getAttachment().getAttachmentId());
		
		if(result){	
			    boolean lineResult=resetApprovalLine(dto);
			    if(lineResult){
			    	boolean typeTestResult=partnerItemTypeTestDetailsService.resetInfraTypeTest(dto.getPartnerInfraItem().getPartnerInfraItemId(), dto.getPartner().getbPartnerId());
			        if(typeTestResult){
			        	boolean docResult=partnerItemDrawingDocService.resetInfraDrawingDocs(dto.getPartnerInfraItem().getPartnerInfraItemId(), dto.getPartner().getbPartnerId());
			            if(docResult){
			            	response.addObject("message", "Clarification has been uploaded succesfully !");
			            	response.addObject("result", true);
			            }else{
			            	response.addObject("message", "Drawing Document Not Reset !");
			            	response.addObject("result", false);
			            	throw new CustomException("Drawing Document Not Reset");
			            }
			        }else{
			        	response.addObject("message", "Type Test Not Reset !");
		            	response.addObject("result", false);
			        	throw new CustomException("Type Test Not Reset");
			        }
			    }else{
			    	response.addObject("message", "Approval Line Not Reset !");
	            	response.addObject("result", false);
			    	throw new CustomException("Approval Line Not Reset");
			    }
		}else{
			response.addObject("message", "Clarification has been not uploaded !");
		}
		}else{
	    	response.addObject("result", false);
		    response.addObject("message", "Partner Or Infra Item Or Attachment Data Not Found");
		}
		return response;
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public ResponseDto saveInfraApprovalLine(PartnerInfraItemApprovalDto dto,String roleName){
		ResponseDto response=new ResponseDto();
		
		Long partnerId=dto.getPartner().getbPartnerId();
		Long infraItemId=dto.getPartnerInfraItem().getPartnerInfraItemId();
		String status=dto.getIsApproved();
		String comment=dto.getComment();
		int resultCount=0;
		/*if(AppBaseConstant.INFRA_APPROVE.equals(status) || AppBaseConstant.INFRA_REJECT.equals(status)){*/
		   resultCount=partnerInfraItemApprovalDao.saveInfraApprovalLine(status, comment, infraItemId, partnerId, roleName);
		/*}*/
		if(resultCount>0){
				/*if(AppBaseConstant.INFRA_CLARIFY.equals(status)){
					resetApprovalLine(dto);
					partnerItemTypeTestDetailsService.resetInfraTypeTest(infraItemId, partnerId);
					partnerItemDrawingDocService.resetInfraDrawingDocs(infraItemId, partnerId);
				}*/
		  response.setHasError(false);
		  response.setMessage("Infra Approval Updated");
		  return response;
		}
		response.setHasError(true);
		response.setMessage("Error In Approval Updated");
		return response;
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean resetApprovalLine(PartnerInfraItemApprovalDto partnerInfraItemApprovalDto){
		Long partnerId=partnerInfraItemApprovalDto.getPartner().getbPartnerId();
		Long infraItemId=partnerInfraItemApprovalDto.getPartnerInfraItem().getPartnerInfraItemId();
		int resultCount=partnerInfraItemApprovalDao.resetInfraApprovalLine(infraItemId, partnerId);
		boolean result =resultCount>0?true:false;
		return result;
	}
	
	@Override
	public boolean sendInfraApprovalMail(PartnerInfraItemApprovalDto latestApproval){
		
		Long partnerId=latestApproval.getPartner().getbPartnerId();
		String itemName=latestApproval.getPartnerInfraItem().getMaterial().getName();
		String status=latestApproval.getIsApproved();
		Long levelNo=latestApproval.getInfraApprovalLevel().getLevel();
		UserDto user=userService.findDto("getQueryForUserByPartnerId",AbstractContextServiceImpl.getParamMap("partnerId", partnerId));
		if(user!=null && user.getEmail()!=null){
		String sub="Infra Item Approval ";
		String message="<p>Hi</p><br><h1><p>The Infra Item: "+itemName+" is "+status+" at level-"+levelNo+". </h1><p>"
				+ " <br><h1><p> Regards,<br>Mahavitran State Electricity Board</p></h1>";
		return sendMail(user.getEmail(), sub, message);
		}
		return false;
	}
	
	private boolean sendMail(String email,String sub,String message){
		if(!"".equals(email)){
			MailDto mailDto = new MailDto();
			mailDto.setSubject(sub);
			mailDto.setMailContent(message);
			mailDto.setSingleRecipient(email);
			mailService.sendSingleEmailWithResult(mailDto,true);
			return true;
		}
		return false;
	}

	@Override
	public CustomResponseDto submitInfraItem() {
		// TODO Auto-generated method stub
		return null;
	}
}
