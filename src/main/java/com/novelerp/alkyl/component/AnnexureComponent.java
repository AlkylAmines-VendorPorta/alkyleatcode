package com.novelerp.alkyl.component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.novelerp.alkyl.dto.PraposedReasonDto;
import com.novelerp.alkyl.service.PraposedReasonService;
import com.novelerp.alkyl.service.WinnerSelectionService;
import com.novelerp.appbase.master.dto.AttachmentDto;
import com.novelerp.appbase.master.dto.MailTemplateDto;
import com.novelerp.appbase.master.service.AttachmentService;
import com.novelerp.appbase.master.service.MailTemplateService;
import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.core.dto.CustomResponseDto;
import com.novelerp.core.dto.MailDto;
import com.novelerp.core.dto.ResponseDto;
import com.novelerp.core.service.MailService;
import com.novelerp.eat.dto.WinnerSelectionDto;
import com.sap.document.sap.rfc.functions.ObjectFactory;

@Component
public class AnnexureComponent {
	
	/*private Logger log = LoggerFactory.getLogger(getClass());*/
	
	@Autowired
	private WinnerSelectionService winnerSelectionService;
	
	@Autowired
	private PraposedReasonService praposedReasonService;
	
	
	@Autowired
	private MailTemplateService mailTemplateService;
	
	@Autowired
	private MailService mailService;
	
	@Autowired
	private AttachmentService attService;
	
	

	public List<WinnerSelectionDto> getWinnerSelectionByAnnexureId(Long annexureId){
		return winnerSelectionService.findDtos("getWinnerSelectionByAnnexureId", AbstractContextServiceImpl.getParamMap("annexureId", annexureId));
	}
	
	public List<PraposedReasonDto> getProposedReasonByAnnexureId(Long annexureId){
		return praposedReasonService.findDtos("getProposedReasonByAnnexureId", AbstractContextServiceImpl.getParamMap("annexureId", annexureId));
	}
	
	//public CustomResponseDto sendQCFApprovalMail(String enquiryId,String qcfno,List<String> ccList,String recipientList) {
	
	public CustomResponseDto sendQCFApprovalMail(String enquiryId,String qcfno,String recipientList, String recipientName) {

		CustomResponseDto resp=new CustomResponseDto();
		try {
			
			
			MailTemplateDto mailTemplate = mailTemplateService.getMailTemplateFromTemplateType(AppBaseConstant.QCF_APPROVAL_TEMPLATE);
			
			if (mailTemplate != null && enquiryId != null ) {
				Map<String, Object> map = new HashMap<>();
				String subject = mailTemplate.getSubject();

				subject = subject.replace("@qcfno@", qcfno);
				subject=  subject.replace("@tomailId@", recipientName);
				mailTemplate.setSubject(subject);
				map.put("@enquiryId@",enquiryId);
				map.put("@qcfno@", qcfno);
				
				
				List<String> toEmailIds = new ArrayList<>();
				toEmailIds.add(recipientList);
				
                List<String> ccEmailIds = new ArrayList<>();	
                
//                for(String ccMailIdList:ccList) {
//                	ccEmailIds.add(ccMailIdList);
//                }
			//	ccEmailIds.addAll(ccList);

//				List<String> ccEmailIds = new ArrayList<>();
//				
//				ccEmailIds.add(AppBaseConstant.PURCHASE_TEAM_1);

				

              mailService.sentMailByTemplateAsync(mailTemplate, map, toEmailIds, ccEmailIds, true);
              resp=new CustomResponseDto(true,"Mail Sucessfully Sent");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			resp=new CustomResponseDto(false,"Mail Sending Failed");
		}
		//return null;
		return resp;
	}
	
	public void sendQCFApprovalMailtoCCList(String enquiryId,String qcfno,List<String> ccList,String recipientName,Long attId) {

		CustomResponseDto resp=new CustomResponseDto();
		try {
		
			
			MailTemplateDto mailTemplate = mailTemplateService.getMailTemplateFromTemplateType(AppBaseConstant.QCF_CC_APPROVAL_TEMPLATE);
	
			
			if (mailTemplate != null && enquiryId != null ) {
				
				Map<String, Object> map = new HashMap<>();
				String subject = mailTemplate.getSubject();
				subject = subject.replace("@qcfno@", qcfno);
				subject=subject.replace("@tomailId@", recipientName);
				mailTemplate.setSubject(subject);
				mailTemplate.setFilePath("D:/VendorQCFComparisonFileUpload/");
				mailTemplate.setFileName("QuotationSS_"+enquiryId+".PDF");
				
//				if(att.size()>0) {
//					mailTemplate.setFilePath(att.get(0).getPath());
//					mailTemplate.setFileName(att.get(0).getName());
////					mailTemplate.setFileName(att.get(0).getFileName());
//				}
				map.put("@qcfno@", qcfno);
				map.put("@tomailId@", recipientName);
				
				
				List<String> toEmailIds = new ArrayList<>();
				
              for(String ccMailIdList:ccList) {
            	  toEmailIds.add(ccMailIdList);
                      }
			//	toEmailIds.add(recipientList);
				
               List<String> ccEmailIds = new ArrayList<>();	
 
              mailService.sentMailByTemplateAsync(mailTemplate, map, toEmailIds, ccEmailIds, true);
              
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		
		}
		//return null;
		//return resp;
	}
}