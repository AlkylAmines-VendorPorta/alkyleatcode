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

import com.novelerp.appcontext.util.ContextConstant;
import com.novelerp.commons.util.CommonUtil;
import com.novelerp.report.service.ReportService;
@Controller
public class PriceBidReportController {
	public String SEC_KEY=ContextConstant.SEC_KEY;
	public String IV_PREFIX=ContextConstant.IV_PREFIX;

	@Autowired
	private ReportService reportService;
	

	private static final String REPORT_NAME="PriceScheduleDetailNew";
	private Logger  log =  LoggerFactory.getLogger(PartnerReportController.class);

	@RequestMapping(value="/priceBid/{itemBidId}/{priceBidId}", method={RequestMethod.GET, RequestMethod.POST})
	public void partnerReport(@PathVariable("itemBidId") Long itemBidId,@PathVariable("priceBidId") Long priceBidId, HttpServletResponse response,HttpServletRequest request){	
		Map<String, Object> params =  new HashMap<>();
		params.put("T_ITEM_BID_ID", itemBidId);
		params.put("T_PRICE_BID_ID",priceBidId );
		params.put("SEC_KEY",SEC_KEY );
		params.put("IV_PREFIX",IV_PREFIX );
		String fileName =CommonUtil.getReportFileName(request, "pb_");
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
	@RequestMapping(value="/worksPriceBid/{itemBidId}", method={RequestMethod.GET, RequestMethod.POST})
	public void worksPartnerReport(@PathVariable("itemBidId") Long itemBidId, HttpServletResponse response){	
		
		Map<String, Object> params =  new HashMap<>();
		params.put("T_ITEM_BID_ID", itemBidId);
		StringBuilder headerContent =  new StringBuilder();
		headerContent.append( "attachment; filename=")
		.append("\"pb_")
		 .append(System.currentTimeMillis())
		  .append( ".pdf\"");
		response.setHeader("Content-Disposition",headerContent.toString());
		OutputStream output =  null;
		try{
			output =  response.getOutputStream();
			reportService.generateReport("Works_PriceScheduleDetails", params, output, "PDF");
		}catch (Exception e) {
			log.error("Error while processing", e );
		}

	}
}
