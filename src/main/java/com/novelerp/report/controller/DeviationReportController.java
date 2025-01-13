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

import com.novelerp.report.service.ReportService;

@Controller
public class DeviationReportController {
	
	@Autowired
	private ReportService reportService;
	
	private static final String Deviation_Commercial_REPORT_NAME="Deviation_Bid_CB";
	private static final String Deviation_Technical_REPORT_NAME="Deviation_Bid_TB";
	
	private Logger  log =  LoggerFactory.getLogger(DeviationReportController.class);

	@RequestMapping(value= {"/generateTechnicalDeviationReport/{bidderId}/{itemBidId}"},method =RequestMethod.POST)
	public void generateTechnicalDeviationReport(@PathVariable("bidderId") Long bidderId,@PathVariable("itemBidId") Long itemBidId,HttpServletResponse response){
		Map<String, Object> params =  new HashMap<>();
		params.put("T_BIDDER_ID", bidderId);
		params.put("T_ITEM_BID_ID", itemBidId);
		StringBuilder headerContent =  new StringBuilder();
		headerContent.append( "attachment; filename=")
		.append("\"dbt_")
		 .append(System.currentTimeMillis())
		  .append( ".pdf\"");
		response.setHeader("Content-Disposition",headerContent.toString());
		OutputStream output =  null;
		try{
			output =  response.getOutputStream();
			reportService.generateReport(Deviation_Technical_REPORT_NAME, params, output, "PDF");
			
		}catch (Exception e) {
			log.error("Error while processing", e );
			
		}
	}
	
	@RequestMapping(value= {"/generateCommercialDeviationReport/{bidderId}"},method =RequestMethod.POST)
	public void generateCommercialDeviationReport(@PathVariable("bidderId") Long bidderId,HttpServletResponse response){
		System.out.println("bidderId from generateCommercialScrutinyReport : "+bidderId);
		Map<String, Object> params =  new HashMap<>();
		params.put("T_BIDDER_ID", bidderId);
		StringBuilder headerContent =  new StringBuilder();
		headerContent.append( "attachment; filename=")
		.append("\"dbc_")
		 .append(System.currentTimeMillis())
		  .append( ".pdf\"");
		response.setHeader("Content-Disposition",headerContent.toString());
		OutputStream output =  null;
		try{
			output =  response.getOutputStream();
			reportService.generateReport(Deviation_Commercial_REPORT_NAME, params, output, "PDF");
		}catch (Exception e) {
			log.error("Error while processing", e );
			}
	}
}
