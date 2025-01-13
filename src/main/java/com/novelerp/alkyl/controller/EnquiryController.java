package com.novelerp.alkyl.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.novelerp.alkyl.dto.EnquiryDto;
import com.novelerp.alkyl.dto.PRDto;
import com.novelerp.alkyl.dto.PRLineDto;
import com.novelerp.alkyl.service.PRLineService;
import com.novelerp.alkyl.service.VendorEnquiryService;
import com.novelerp.alkyl.validator.EnquiryValidator;
import com.novelerp.appbase.master.service.EnquiryService;
import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.appcontext.dto.BPartnerDto;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.core.dto.CustomResponseDto;
import com.novelerp.core.dto.Errors;
import com.novelerp.core.util.DateUtil;
import com.novelerp.eat.dto.BidderDto;
import com.novelerp.eat.dto.ItemBidDto;
import com.novelerp.eat.service.BidderService;
import com.novelerp.eat.service.ItemBidService;

@Controller
@RequestMapping(value="/rest")
public class EnquiryController {
	
	@Autowired
	private EnquiryService enqService;
	
	@Autowired
	private BidderService bidderService;
	
	@Autowired
	private ItemBidService itemBidService;
	
	@Autowired
	private PRLineService prLineService;
	
	@Autowired
	private EnquiryValidator eqValidator;
	
	@Autowired
	private VendorEnquiryService vendorEnquiryService;
	
	@PostMapping(value="/createEnquiry")
	public @ResponseBody CustomResponseDto createEnquiry(@RequestBody BidderDto enquiry){
		try{
			enquiry=enqService.createEnquiry(enquiry);
			if(null != enquiry && null != enquiry.getResponse()){
				CustomResponseDto resp = new CustomResponseDto(!enquiry.getResponse().isHasError(),enquiry.getResponse().getMessage());
				resp.addObject("enquiry", enquiry);
				return resp;
			}else{
				throw new RuntimeException("Something went wrong");
			}
		}catch (Exception e) {
			return new CustomResponseDto(false,e.getMessage());
		}
		
	}
	
	@PostMapping(value="/createEnquiries")
	public @ResponseBody CustomResponseDto createEnquiries(@RequestBody EnquiryDto enquiries){

		try{
			
			CustomResponseDto enquiryDto=null;
			
			Errors errors = new Errors();
			eqValidator.validateEnquiry(enquiries, errors);
			
			if(errors.getErrorCount()>0){
				return new CustomResponseDto(false, errors.getErrorString());
			}
			/*enquiries.setBidEndDate(null);*/
			
			
			enquiryDto=enqService.createEnquiries(enquiries);
			
			if(enquiryDto.getMessage().equals("Enquiry Created Successfully")) {
			Map<String, Object> params= new HashMap<>();
			params.put("enqNo", enquiries.getEnqNo());
			List<BidderDto> bidderList=bidderService.findDtos("getBidByenqNo",params);
			
			//List<BidderDto> bidders = enquiries.getBidderList();
		
			for(BidderDto enquiry : bidderList){
				if("Y".equals(enquiry.getIsMailSent())){
					enqService.sendMail(enquiry);
				}
			}
			
			return new CustomResponseDto(true,"Enquiries sent to selected Vendors.");
			}else {
				return new CustomResponseDto(false,"Please Select Single Doctype");
			}
		
		}catch (Exception e) {
			return new CustomResponseDto(false,e.getMessage());
		}
		
		
	}
	
	@PostMapping(value="/getEnquiry")
	public @ResponseBody BidderDto getEnquiry(){
		ItemBidDto ib = new ItemBidDto();
		PRDto pr = new PRDto();
		BidderDto bidder = new BidderDto();
		PRLineDto prLine = new PRLineDto();
		List<ItemBidDto> ibList = new ArrayList<ItemBidDto>();
		
		ib.setPrLine(prLine);
		ibList.add(ib);
		bidder.setPr(pr);
		bidder.setItemBids(ibList);
		
		return bidder;
	}

	@PostMapping(value="/getEnquiries/{prId}")
	public @ResponseBody CustomResponseDto getPRLinebyPrId(@PathVariable("prId") Long prId){
		
		CustomResponseDto resp = null;
		Map<String, Object> params= new HashMap<>();
		params.put("prId", prId);
		List<BidderDto> bidderList=bidderService.findDtos("getBidderByPrId",params);
		
		resp=new CustomResponseDto("bidderList", bidderList);
		return resp;
	}
	
	@PostMapping(value="/getItemByBidId/{bidId}")
	public @ResponseBody CustomResponseDto getItemBidByBidId(@PathVariable("bidId") Long bidId){
		
		CustomResponseDto resp = null;
		Map<String, Object> params= new HashMap<>();
		params.put("bidderId", bidId);
		List<ItemBidDto> itemBidList=itemBidService.findDtos("getItemByBidId",params);
		
		resp=new CustomResponseDto("itemBidList", itemBidList);
		return resp;
	}
	
	@PostMapping(value="/getUnAssignedPrLine/{bidId}/{prId}")
	public @ResponseBody CustomResponseDto getUnAddedPrLine(@PathVariable("bidId") Long bidId,@PathVariable("prId") Long prId){
		
		CustomResponseDto resp = null;
		Map<String, Object> params= new HashMap<>();
		params.put("bidderId", bidId);
		params.put("prId", prId);
		List<PRLineDto> prLineList=prLineService.findDtos("getUnAssignedPrLineEnquiry",params);
		
		resp=new CustomResponseDto("prLineList", prLineList);
		return resp;
	}
	
	@PostMapping(value="/createUnAssignedPrLine")
	public @ResponseBody CustomResponseDto createUnAssignedPrLine(@RequestBody EnquiryDto enquiries){
		
		CustomResponseDto resp = null;
		try{
			
			Errors errors = new Errors();
			eqValidator.validateUnAssignedPrLine(enquiries, errors);
			
			if(errors.getErrorCount()>0){
				resp=new CustomResponseDto(false, errors.getErrorString());
				return resp;
			}
		
			BidderDto enquiry=enquiries.getBidderList().get(0);
			BPartnerDto partner=enquiries.getBidderList().get(0).getPartner();
			
			itemBidService.saveEnquiryLines(enquiries.getItemBidList(),enquiry,partner);
			return new CustomResponseDto(true, "Material Added!");
		}catch(Exception e){
			return new CustomResponseDto(false, e.getMessage());
		}
	}
	
	@PostMapping(value = "/updateEnqEndDate")
	public @ResponseBody CustomResponseDto updateEnqEndDate(@RequestBody EnquiryDto enquiries){
		Map<String, Object> propertyValueMap = AbstractContextServiceImpl.getParamMap("bidEndDate",enquiries.getBidEndDate() );
		int result = vendorEnquiryService.updateByJpql(
				propertyValueMap,
				AbstractContextServiceImpl.getParamMap("enquiryId", enquiries.getEnquiryId()));
		if (result > 0) {
			CustomResponseDto resp = new CustomResponseDto(true, "Bid End Date Updated");
			return resp;
		} else {
			return new CustomResponseDto(false, "Error While Updating Bid End Date");
		}
		
	}
	@PostMapping(value = "/getEnqByID/{enqId}")
	public @ResponseBody CustomResponseDto getEnqByID(@PathVariable("enqId") Long enqId
			) {
		CustomResponseDto resp = null;
        EnquiryDto enqDto=vendorEnquiryService.findDto(enqId);
		resp=new CustomResponseDto("enq", enqDto);
		return resp;
		}
	@PostMapping(value = "/getAllOpenEnq")
	public @ResponseBody CustomResponseDto getAllOpenEnq(){
		CustomResponseDto resp = null;
		 Map<String , Object> params = new HashMap<String, Object>();
         /* params.put("currentDate",new Date());*/
          /*params.put("status", AppBaseConstant.BIDDER_STATUS_BID_SUBMITED);*/
			/*List<BidderDto> bidderList=bidderService.findDtos("getVendorQuotationForApproval", params);*/
          List<EnquiryDto> enqList=vendorEnquiryService.findDtos("getEnquiryForNegotiate", params);
			 resp=new CustomResponseDto("enqList", enqList);
			 return resp;
		
		
	}
	@PostMapping(value="/addBidderToEnquiry")
	public @ResponseBody CustomResponseDto addBidderToEnquiry(@RequestBody EnquiryDto enquiries){

		try{
			if(null==enquiries.getEnquiryId()){
				return new CustomResponseDto(false,"Enquiry Cannot be null");
			}
			Map<String, Object> params= new HashMap<>();
			params.put("prId", enquiries.getEnquiryId());
			List<BidderDto> bidderList=bidderService.findDtos("getBidderByPrId",params);
			List<ItemBidDto> itemList=getAllItemBid(bidderList);
			enquiries.setItemBidList(itemList);
			enqService.updateEnquiries(enquiries);
			return new CustomResponseDto(true,"Vendor Added To Selecetd Enquiry");
		}catch (Exception e) {
			return new CustomResponseDto(false,e.getMessage());
		}
		
	}
	
	public List<ItemBidDto> getAllItemBid(List<BidderDto> bidderList){
		List<ItemBidDto> filteredList = new ArrayList<>();
		List<ItemBidDto> itemList =bidderList.get(0).getItemBids();
		for(ItemBidDto dto:itemList){
			dto.setItemBidId(null);
			filteredList.add(dto);
		}
		return filteredList;
	}
	@PostMapping(value = "/getEnqByPRLine/{prLineId}")
	public @ResponseBody CustomResponseDto getEnqByPRLine(@PathVariable("prLineId") Long prLineId
			) {
		CustomResponseDto resp = null;
		Map<String, Object> params= new HashMap<>();
		params.put("prLineId",prLineId);
        List<ItemBidDto> itemList=itemBidService.findDtos("getItemBidByPRLine", params);
		resp=new CustomResponseDto("enqList", itemList);
		return resp;
		}
}
