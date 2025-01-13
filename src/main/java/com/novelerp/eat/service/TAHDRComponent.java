/**
 * @author Ankush
 */
package com.novelerp.eat.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.novelerp.appbase.master.dto.PublicNoticeDto;
import com.novelerp.appbase.master.service.PublicNoticeService;
import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.commons.util.CommonUtil;
import com.novelerp.core.dto.ErrorDto;
import com.novelerp.core.dto.ResponseDto;
import com.novelerp.eat.dao.TAHDRDao;
import com.novelerp.eat.dto.TAHDRApprovalMatrixDto;
import com.novelerp.eat.dto.TAHDRDto;
import com.novelerp.eat.entity.TAHDR;

@Component
public class TAHDRComponent {

	@Autowired
	private TAHDRService tahdrService;
	
	@Autowired
	private TAHDRDetailService tahdrDetailService;
	
	@Autowired
	private PublicNoticeService publicNoticeService;
	
	@Autowired
	private TAHDRApprovalMatrixService tahdrApprovalMatrixService;
	
	@Autowired
	private TAHDRDao tahdrDao; 
	
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public ResponseDto submitTahdrDetails(TAHDRDto tahdrDto) {
		ResponseDto response=new ResponseDto();
		
		TAHDR tahdr = tahdrDao.getAllTahdrDetails(tahdrDto.getTahdrId());
		Map<String,Object> map= new HashMap<>();
		
		
		if(!tahdrDto.getTahdrStatusCode().equals("VO")){
			/*if(validateAccessAction(tahdrDto.getTahdrStatusCode())){*/
		
				if(tahdrDto.getTahdrStatusCode().equals("IP")){
					List<ErrorDto> errors=tahdrService.checkTahdrDetails(tahdr);
					if(CommonUtil.isListEmpty(errors)){
					response.setHasError(false);
					map.put("tahdrStatusCode",tahdrDto.getTahdrStatusCode());
					Long approvalMatrixRow=tahdrApprovalMatrixService.getRowForUpdateApprovalMatrixLevel(tahdrDto.getTahdrId());
					TAHDRApprovalMatrixDto approvalMatrix =tahdrApprovalMatrixService.getRowForUpdateApprovalMatrix(tahdrDto.getTahdrId(),approvalMatrixRow);
					tahdrApprovalMatrixService.setStatusInProgress(approvalMatrix.getTahdrApprovalMatrixId());
					response.setMessage(tahdrDto.getTahdrStatusCode());
					tahdrService.updateByJpql(map, "tahdrId",tahdrDto.getTahdrId());
					}
					else{
						response.setHasError(true);
						response.setErrors(errors);
					}
				}else if(tahdrDto.getTahdrStatusCode().equals("AP")|| tahdrDto.getTahdrStatusCode().equals("RJ")){
					TAHDRApprovalMatrixDto tahdrApprovalMatrix=null;
					tahdrApprovalMatrix=tahdrApprovalMatrixService.getApprovalMatrixForAP(tahdrDto.getTahdrId());
					tahdrApprovalMatrix=tahdrApprovalMatrixService.setStatusApprove(tahdrApprovalMatrix,tahdrDto.getTahdrStatusCode());
					if(tahdrApprovalMatrix!=null && tahdrDto.getTahdrStatusCode().equalsIgnoreCase(AppBaseConstant.DOCUMENT_STATUS_APPROVED)){
					int approvalMatrixDto=tahdrApprovalMatrixService.setStatusInProgress(tahdrApprovalMatrix.getTahdrApprovalMatrixId());
					}
					if(tahdrApprovalMatrix==null || tahdrDto.getTahdrStatusCode().equals("RJ") ){
					Map<String,Object> officeNote= new HashMap<>();
					if(tahdrDto.getRemarks()!="")
						map.put("remarks",tahdrDto.getRemarks());
						map.put("tahdrStatusCode",tahdrDto.getTahdrStatusCode());
						officeNote.put("officeNote.attachmentId", tahdrDto.getTahdrDetailList().iterator().next().getOfficeNote().getAttachmentId());
						tahdrDetailService.updateByJpql(officeNote, "tahdrDetailId", tahdrDto.getTahdrDetailList().iterator().next().getTahdrDetailId());
						tahdrService.updateByJpql(map, "tahdrId",tahdrDto.getTahdrId());
						response.setMessage(tahdrDto.getTahdrStatusCode());
					}
					}else if(tahdrDto.getTahdrStatusCode().equals("PU")){
					/*TAHDRDetailDto tahdrDetaildto=tahdrDetailService.findDto(tahdrDto.getTahdrDetailList().iterator().next().getTahdrDetailId());		
					Errors errorsDate =  new Errors();
					tahdrDatesValidation.validate(tahdrDetaildto, errorsDate);
					
					if(errorsDate.getErrorCount()>0){
						response.setHasError(true);
						response.setErrors(errorsDate.getErrorList());
						tahdrDto.setResponse(response);
						return response;
					}*/
					List<ErrorDto> errors=tahdrService.checkTahdrDetails(tahdr);
					if(CommonUtil.isListEmpty(errors)){
						response.setHasError(false);
					if(!CommonUtil.isCollectionEmpty(tahdrDto.getTahdrDetailList()))
					{
				Map<String,Object> mapValue= new HashMap<>();
				PublicNoticeDto publicNoticeDto= new PublicNoticeDto();
				TAHDRDto tahdrId = new TAHDRDto();
				tahdrId.setTahdrId(tahdrDto.getTahdrId());
				mapValue.put("publishingDate", tahdrDto.getTahdrDetailList().iterator().next().getPublishingDate());
				tahdrDetailService.updateByJpql(mapValue, "tahdrDetailId", tahdrDto.getTahdrDetailList().iterator().next().getTahdrDetailId());
				publicNoticeDto.setPublishingDate(tahdrDto.getTahdrDetailList().iterator().next().getPublishingDate());
				publicNoticeDto.setTahdr(tahdrId);
				if(tahdrDto.getRemarks()!="")
				publicNoticeDto.setDescription(tahdrDto.getRemarks());
				publicNoticeService.save(publicNoticeDto);
				map.put("tahdrStatusCode",tahdrDto.getTahdrStatusCode());
				map.put("remarks", tahdrDto.getRemarks());
				tahdrService.updateByJpql(map, "tahdrId",tahdrDto.getTahdrId());
				response.setMessage(tahdrDto.getTahdrStatusCode());
					}
					}
					else{
						response.setHasError(true);
						response.setErrors(errors);
					}
				}
				
				/*updateByJpql(map, "tahdrId",tahdrDto.getTahdrId());
				response.setMessage(tahdrDto.getTahdrStatusCode());*/
		}
		/*else{
			
			response.setMessage("Invalid Access");
		}*/
			
		/*}*/
		else{
			tahdr.setTahdrStatusCode(tahdrDto.getTahdrStatusCode());
			response.setMessage(tahdrDto.getTahdrStatusCode());	
		}
		return response;
	
	}
	
}
