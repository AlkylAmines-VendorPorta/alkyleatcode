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
public class AnnexureC1ReportController {
	
	@Autowired
	private ReportService reportService;
	
	private static final String AnnexureC1_REPORT_NAME="Annexure_C1";
	/*private static final String Preliminary_Technical_REPORT_NAME="Preliminary_Technical_Scrutiny";*/
	private Logger  log =  LoggerFactory.getLogger(PreliminaryScrutinyReportController.class);
	
	@RequestMapping(value= {"/generateAnnexureC1Report/{priceBidId}"},method =RequestMethod.POST)
	public void generateTechnicalScrutinyReport(@PathVariable("priceBidId") Long priceBidId,HttpServletResponse response){
		System.out.println("dfghjkl..."+priceBidId);
		Map<String, Object> params =  new HashMap<>();
		/*bidderId =Long.parseLong("1049");*/
		params.put("T_PRICE_BID_ID", priceBidId);
		StringBuilder headerContent =  new StringBuilder();
		headerContent.append( "attachment; filename=")
		.append("\"pb_")
		 .append(System.currentTimeMillis())
		  .append( ".pdf\"");
		response.setHeader("Content-Disposition",headerContent.toString());
		OutputStream output =  null;
		try{
			output =  response.getOutputStream();
			reportService.generateReport(AnnexureC1_REPORT_NAME, params, output, "PDF");
		}catch (Exception e) {
			log.error("Error while processing", e );
			}
	}
	
}
