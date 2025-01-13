package com.novelerp.report.controller;


import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.novelerp.appbase.master.dto.AttachmentDto;
import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.appcontext.service.MediaService;
import com.novelerp.appcontext.service.SystemConfiguratorService;
import com.novelerp.core.dto.ResponseDto;
import com.novelerp.eat.service.TAHDRDetailService;
import com.novelerp.report.service.TenderReportService;
/**
 * 
 * @author Vivek Birdi
 *
 */
@Controller
public class TenderReportController {

	@Autowired
	private TenderReportService tenderReportService;
	
	@Autowired
	private TAHDRDetailService tahdrDetailService;
	
	@Autowired
	/*@Qualifier(AppBaseConstant.SFTP_MEDIA_SERVICE_IMPL)*/
    /*@Qualifier(AppBaseConstant.LOCAL_MEDIA_SERVICE_IMPL)*/
    @Qualifier(AppBaseConstant.FTP_MEDIA_SERVICE_IMPL)
	private MediaService mediaService;
	
	@Autowired
	private SystemConfiguratorService sysConfigService;
	
	@RequestMapping(value="/tenderReport/{tenderId}",method={RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody AttachmentDto generateTenderDoc(@PathVariable("tenderId") Long tahdrId){

		String fileName = tenderReportService.generateTenderReport(tahdrId);
		File file=new File(sysConfigService.getAppTempDir()+fileName);
		String filePath=mediaService.save(file, fileName);
		AttachmentDto attachment = tenderReportService.addAttachment(tahdrId, fileName,filePath);
		if(attachment == null){
			attachment = new AttachmentDto();
			attachment.setResponse(new ResponseDto(true, "Document generation failed."));
			return attachment;
		}
		tahdrDetailService.updateWithTenderDoc(attachment.getAttachmentId(), tahdrId);
		return attachment;
		
	}
}
