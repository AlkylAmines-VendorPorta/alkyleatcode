package com.novelerp.report.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.novelerp.appbase.master.dto.AttachmentDto;
import com.novelerp.appcontext.service.SystemConfiguratorService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.core.dto.CustomResponseDto;
import com.novelerp.eat.dto.ItemScrutinyDto;
import com.novelerp.eat.dto.ScrutinyFileDto;
import com.novelerp.eat.dto.TAHDRDto;
import com.novelerp.eat.service.ItemScrutinyService;
import com.novelerp.eat.service.ScrutinyFileService;
import com.novelerp.eat.service.TAHDRService;
import com.novelerp.report.service.ReportService;

@Controller
public class FinalScrutinyReportController {
	
	@Autowired
	private ReportService reportService;
	
	@Autowired
	private ScrutinyFileService scrutinyFileService;
	
	@Autowired
	private ItemScrutinyService itemScrutinyService;
	
	@Autowired
	private SystemConfiguratorService sysConfigService;
	
	@Autowired
	private TAHDRService tAHDRService;
	
	private static final String Final_Commercial_REPORT_NAME="Final_Commercila_Scrutiny";
	private static final String Final_Technical_REPORT_NAME="Final_Technical_Scrutiny";
	private Logger  log =  LoggerFactory.getLogger(FinalScrutinyReportController.class);
	
	@RequestMapping(value= {"/generateFinalTechnicalScrutinyReport/{bidderId}/{itemBidId}"},method =RequestMethod.POST)
	public String generateFinalTechnicalScrutinyReport(@PathVariable("bidderId") Long bidderId,@PathVariable("itemBidId") Long itemBidId,HttpServletResponse response){
		StringBuilder fileName=new StringBuilder();
		String baseDir=sysConfigService.getAppDocDir();
		String fName="Final_Technical_Scrutiny_"+System.currentTimeMillis()+".pdf";
		String mergerDestinationFile= baseDir+fName;
		Map<String, Object> params =  new HashMap<>();
		params.put("T_BIDDER_ID", bidderId);
		params.put("T_ITEM_BID_ID", itemBidId);
		fileName.append(mergerDestinationFile);
		FileOutputStream output =  null;
		try{
			File file= new File(fileName.toString());
			output =  new FileOutputStream(file);
			reportService.generateReport(Final_Technical_REPORT_NAME, params, output, "PDF");
		}catch (Exception e) {
			log.error("Error while processing", e );
		}
		return fName;
	}
	
	@RequestMapping(value= {"/generateFinalCommercialScrutinyReport/{bidderId}"},method =RequestMethod.POST)
	public String generateFinalCommercialScrutinyReport(@PathVariable("bidderId") Long bidderId,HttpServletResponse response){
		StringBuilder fileName=new StringBuilder();
		String baseDir=sysConfigService.getAppDocDir();
		String fName="Final_Commercial_Scrutiny_"+System.currentTimeMillis()+".pdf";
		String mergerDestinationFile= baseDir+fName;
		Map<String, Object> params =  new HashMap<>();
		params.put("T_BIDDER_ID", bidderId);
		fileName.append(mergerDestinationFile);
		FileOutputStream output =  null;
		try{
			File file= new File(fileName.toString());
			output =  new FileOutputStream(file);
			reportService.generateReport(Final_Commercial_REPORT_NAME, params, output, "PDF");
		}catch (Exception e) {
			log.error("Error while processing", e );
		}
		return fName;
	}
	
	@RequestMapping(value="/getFinalScrutinyReport",method={RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody CustomResponseDto getTechicalScrutinyReport(@ModelAttribute("scrutinyFile")ScrutinyFileDto scrutinyFileDto,HttpServletResponse response){
		CustomResponseDto resp =new CustomResponseDto();
		TAHDRDto tahdr=tAHDRService.findDto("getTahdrByBidderId", AbstractContextServiceImpl.getParamMap("bidderId", scrutinyFileDto.getBidder().getBidderId())); 
		boolean isAuditing=tahdr==null?false:tahdr.getIsAuditing()==null?false:tahdr.getIsAuditing().equalsIgnoreCase("Y")?true:false;
		if(!isAuditing){
		ItemScrutinyDto itemScrutiny=itemScrutinyService.findDto(scrutinyFileDto.getItemScrutiny().getItemScrutinyId());
		boolean auditStatus=scrutinyFileService.isAuditingSubmitted(scrutinyFileDto,itemScrutiny);
		    if(auditStatus){
		    	String fileName="";
		    	if("TECHSCR".equalsIgnoreCase(scrutinyFileDto.getScrutinyType())){
		    		fileName = generateFinalTechnicalScrutinyReport(scrutinyFileDto.getBidder().getBidderId(),scrutinyFileDto.getItemBid().getItemBidId(),response);
		    	}else if("COMMSCR".equalsIgnoreCase(scrutinyFileDto.getScrutinyType())){
		    		fileName = generateFinalCommercialScrutinyReport(scrutinyFileDto.getBidder().getBidderId(),response);
		    	}else{
		    		resp.addObject("result", false);	
					resp.addObject("message", "Something went wrong !");
		    		return resp;
		    	}
				
				AttachmentDto attachment = scrutinyFileService.addScrutinyFile(scrutinyFileDto, fileName);
				if(attachment!=null){
					scrutinyFileDto.setAttachment(attachment);
					resp=scrutinyFileService.saveFinalScrutinyFile(scrutinyFileDto,itemScrutiny);
				}else{
					resp.addObject("result", false);	
					resp.addObject("message", "Report Generation Failed");
				}
			}else{
			    resp.addObject("result", false);	
			    resp.addObject("message", "Final Commercial Scrutiny must be Approved by auditor before generating Report ");	
			}
		}else{
		    resp.addObject("result", false);	
		    resp.addObject("message", "Cannot proceed,Commercial Final Scrutiny is under Auditing. ");	
		}
	    return resp;
	}

}
