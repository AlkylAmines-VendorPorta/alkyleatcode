package com.novelerp.report.controller;

import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.eat.dto.TAHDRDto;
import com.novelerp.eat.service.TAHDRService;
import com.novelerp.report.service.ReportService;

/**
 * 
 * @author Varsha
 *
 */
@Controller
public class BidSheetReportController {
	
	@Autowired
	private ReportService reportService;
	
	@Autowired
	private TAHDRService tAHDRService;
	
	private Logger  log =  LoggerFactory.getLogger(BidSheetReportController.class);
	private static final String BID_SHEET_REPORT_NAME="";
	private static final String CUMMULARIVE_BID_SHEET_REPORT_NAME="";
	
	@RequestMapping(value= {"/generateBidSheetReport/{tahdrId}/{tahdrMaterialId}"},method =RequestMethod.POST)
	public void generateBidSheetReport(@PathVariable("tahdrId") Long tahdrId,@PathVariable("tahdrMaterialId") Long tahdrMaterialId,HttpServletResponse response){
		String ReportName=null;
		TAHDRDto tender=tAHDRService.findDto(tahdrId);
	    Map<String, Object> params =  new HashMap<>();
		params.put("tahdrId", tahdrId);
		params.put("tahdrMaterialId", tahdrMaterialId);
		if(tender.getTahdrTypeCode().equals(AppBaseConstant.AUCTION_TYPE_CODE_FORWORD)){
			ReportName="Bid_Sheet_Fordward";
		}else{
			ReportName="Bid_Sheet_Reverse";

		}
		
		params.put("order", "");
		StringBuilder headerContent =  new StringBuilder();
		headerContent.append( "attachment; filename=")
		.append("\"bid_sheet_")
		 .append(System.currentTimeMillis())
		  .append( ".pdf\"");
		response.setHeader("Content-Disposition",headerContent.toString());
		OutputStream output =  null;
		try{
			output =  response.getOutputStream();
			reportService.generateReport(ReportName, params, output, "PDF");
			
		}catch (Exception e) {
			log.error("Error while processing", e );
			
		}
	}
	
	@RequestMapping(value= {"/generateCummulativeBidSheetReport/{tahdrId}/{tahdrMaterialId}"},method =RequestMethod.POST)
	public void generateCummulativeBidSheetReport(@PathVariable("tahdrId") Long tahdrId,@PathVariable("tahdrMaterialId") Long tahdrMaterialId,HttpServletResponse response){
		String ReportName=null;
		TAHDRDto tender=tAHDRService.findDto(tahdrId);
	    Map<String, Object> params =  new HashMap<>();
		params.put("tahdrId", tahdrId);
		params.put("tahdrMaterialId", tahdrMaterialId);
		if(tender.getTahdrTypeCode().equals(AppBaseConstant.AUCTION_TYPE_CODE_FORWORD)){
			ReportName="Cummulative_Bid_Sheet_Fordward";
		}else{
			ReportName="Cummulative_Bid_Sheet_Reverse";

		}
		
		params.put("order", "");
		StringBuilder headerContent =  new StringBuilder();
		headerContent.append( "attachment; filename=")
		.append("\"cu_bid_sheet_")
		 .append(System.currentTimeMillis())
		  .append( ".pdf\"");
		response.setHeader("Content-Disposition",headerContent.toString());
		OutputStream output =  null;
		try{
			output =  response.getOutputStream();
			reportService.generateReport(ReportName, params, output, "PDF");
			
		}catch (Exception e) {
			log.error("Error while processing", e );
			
		}
	}
	
	
	
}
