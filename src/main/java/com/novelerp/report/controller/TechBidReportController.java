package com.novelerp.report.controller;

import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.novelerp.commons.util.CommonUtil;
import com.novelerp.report.service.ReportService;

@Controller
public class TechBidReportController {
	@Autowired
	private ReportService reportService;

	private static final String REPORT_NAME="Technical_Bid";
	private Logger  log =  LoggerFactory.getLogger(PartnerReportController.class);

	@RequestMapping(value="/technicalBidReport/{technicalBidId}", method={RequestMethod.GET, RequestMethod.POST})
	public void procurementTechReport(@PathVariable("technicalBidId") Long technicalBidId, HttpServletResponse response,HttpServletRequest request){	
		
		Map<String, Object> params =  new HashMap<>();
		params.put("T_TECHNICAL_BID_ID", technicalBidId);
		
		String fileName =CommonUtil.getReportFileName(request, "tb_");
		
		StringBuilder headerContent =  new StringBuilder();
		headerContent.append( "attachment; filename=\"")
		.append(fileName)
		.append("\"");
		response.setHeader("Content-Disposition",headerContent.toString());
		OutputStream output =  null;
		try{
			output =  response.getOutputStream();
			reportService.generateReport(REPORT_NAME, params, output, "PDF");
		}catch (Exception e) {
			log.error("Error while processing", e );
		}

	}
	
	@RequestMapping(value="/worksTechnicalBidReport/{technicalBidId}", method={RequestMethod.GET, RequestMethod.POST})
	public void worksTechBidReport(@PathVariable("technicalBidId") Long technicalBidId, HttpServletResponse response, HttpServletRequest request){	
		
		Map<String, Object> params =  new HashMap<>();
		params.put("T_TECHNICAL_BID_ID", technicalBidId);
		
		String fileName =CommonUtil.getReportFileName(request, "tb_");
		StringBuilder headerContent =  new StringBuilder();
		headerContent.append( "attachment; filename=\"")
		.append(fileName)
		.append("\"");
		response.setHeader("Content-Disposition",headerContent.toString());
		OutputStream output =  null;
		try{
			output =  response.getOutputStream();
			reportService.generateReport("Works_Technical_Bid", params, output, "PDF");
		}catch (Exception e) {
			log.error("Error while processing", e );
		}

	}
}
