/**
 * @author Ankita
 * 
 * */
package com.novelerp.eat.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.novelerp.appbase.master.dto.MailTemplateDto;
import com.novelerp.appbase.master.service.MailTemplateService;
import com.novelerp.appbase.master.service.ReferenceListService;
import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.appcontext.dto.RoleDto;
import com.novelerp.appcontext.dto.UserDto;
import com.novelerp.appcontext.service.BPartnerService;
import com.novelerp.appcontext.service.ContextService;
import com.novelerp.appcontext.service.UserService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.appcontext.util.ContextConstant;
import com.novelerp.commons.util.CommonUtil;
import com.novelerp.core.dto.CustomResponseDto;
import com.novelerp.core.service.MailService;
import com.novelerp.eat.dto.PriceBidDto;
import com.novelerp.eat.dto.TAHDRDto;
import com.novelerp.eat.dto.TAHDRMaterialDto;
import com.novelerp.eat.dto.WinnerSelectionDto;
import com.novelerp.eat.service.BidderService;
import com.novelerp.eat.service.LiveBidService;
import com.novelerp.eat.service.PriceBidService;
import com.novelerp.eat.service.SMSService;
import com.novelerp.eat.service.TAHDRMaterialService;
import com.novelerp.eat.service.TAHDRService;
import com.novelerp.eat.service.TahdrAwardWinnerService;

@Controller
public class TAHDRAwardWinnerController {/*

	@Autowired
	private TAHDRService tahdrService;

	@Autowired
	 @Qualifier("jwtUserContext") private ContextService contextService;

	@Autowired
	private PriceBidService priceBideService;

	@Autowired
	private TAHDRMaterialService tahdrMaterialService;
	@Autowired
	private ReferenceListService refListService;

	@Autowired
	private TahdrAwardWinnerService tahdrAwardService;

	@Autowired
	private MailTemplateService mailTemplateService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private MailService mailService;
	
	@Autowired
	private LiveBidService liveBidService;
	
	@Autowired
	private SMSService smsService;
	
	@Autowired
	private BidderService bidderService;
	
	@Autowired
	private BPartnerService partnerService;
	
	@RequestMapping(value = "/tenderWinnerSelection", method = RequestMethod.GET)
	public ModelAndView tenderWinnerSelection() {
		ModelAndView model = new ModelAndView("winnerSelection");
		model.addObject("documentType",ContextConstant.DOCUMENT_TYPE_TENDER);
		model.addObject("type", "winnerSelection");
		return model;
	}
	
	@RequestMapping(value= "/auctionWinnerSelection", method=RequestMethod.GET)
	public ModelAndView auctionWinnerSelection(){
		ModelAndView model= new ModelAndView("winnerSelection");
		model.addObject("documentType",ContextConstant.DOCUMENT_TYPE_AUCTION);
		model.addObject("type", "winnerSelection");
		return model;
	}
	
	@RequestMapping(value= "/tahdrAwardWinnerData/{tenderTypeCode}/{awardStatus}/{pageNumber}/{pageSize}/{searchMode}/{serachValue}", method=RequestMethod.POST)
	@ResponseBody
	public CustomResponseDto tenderPreparationData(@PathVariable("tenderTypeCode")String tenderTypeCode,
												   @PathVariable("awardStatus") String awardStatus,
												   @PathVariable("pageNumber")int pageNumber, 
												   @PathVariable("pageSize") int pageSize,
												   @PathVariable("searchMode") String searchMode , 
												   @PathVariable("serachValue") String serachValue){
		RoleDto roleDto= contextService.getDefaultRole();
		Map<String, Object> params=new HashMap<>();
		params.put("tenderTypeCode", tenderTypeCode);
		if(awardStatus.equalsIgnoreCase("N")){
			if(tenderTypeCode.equalsIgnoreCase("QRFQ") || tenderTypeCode.equalsIgnoreCase("RFQ")){
				params.put("status", AppBaseConstant.DOCUMENT_STATUS_PUBLISHED);
			}else{
				params.put("status", AppBaseConstant.TENDER_AWARD_WINNER_SCHEDULING);
			}
		}
		else if(awardStatus.equalsIgnoreCase("Y")){
			params.put("status", AppBaseConstant.TENDER_AWARD_WINNER_COMPLETED);
		}else if(awardStatus.equalsIgnoreCase("O")){
			params.put("status", AppBaseConstant.DOCUMENT_STATUS_PUBLISHED);
		}
		//List<TAHDRDto> tahdrList=tahdrService.findDtos("getTahdrWithDetailsForAwardWinner", params); 
		List<TAHDRDto> tahdrList=tahdrService.getTahdrWithDetailsForAwardWinner(params, pageNumber, pageSize, searchMode, serachValue); 
		Long countResult = tahdrService.getTahdrWithDetailsForAwardWinnerCount(params, searchMode, serachValue);
		int LastPage = (int) ((countResult / pageSize) + 1);
		
		List<TAHDRDto> awardWinner=tahdrService.findDtos("getAwardWinnerCount", params);
		Map<String,String> tenderStatus=refListService.getReferenceListMap(AppBaseConstant.DOCUMENT_STATUS);
		
		
		CustomResponseDto response=new CustomResponseDto();
		response.setData(tahdrList);
		response.addObject("LastPage", LastPage);
		response.addObject("listTahdr", tahdrList);
		response.addObject("tenderTypeCode", tenderTypeCode);
		response.addObject("tenderStatus", tenderStatus);
		response.addObject("roleDto", roleDto);
		response.addObject("awardWinner", awardWinner);
		return response;
	}
	
	@RequestMapping(value= "/getAwardWinnerCount/{tahdrId}", method=RequestMethod.POST)
	@ResponseBody
	public CustomResponseDto getAwardWinnerCount(@PathVariable("tahdrId")Long tahdrId){
		Long biddercount = bidderService.getAwardWinnerCount(tahdrId);
		CustomResponseDto response=new CustomResponseDto(); 
		response.addObject("biddercount", biddercount);
		return response;
	}
	
	@RequestMapping(value= "/getBidderListFromTahdrMaterial/{tahdrMaterialId}", method=RequestMethod.POST)
	public @ResponseBody List<PriceBidDto> getBidderListFromTahdrMaterial(@PathVariable("tahdrMaterialId")Long tahdrMaterialId){
		Map<String, Object> params =  AbstractContextServiceImpl.getParamMap("tahdrMaterialId", tahdrMaterialId);
		List<PriceBidDto> priceBidList = priceBideService.findDtos("getBidderListFromTahdrMaterialId", params);
		return priceBidList;
	}
	
	@RequestMapping(value= "/getBidderListFromTahdrMaterial/{typeCode}/{tahdrMaterialId}", method=RequestMethod.POST)
	public @ResponseBody List<PriceBidDto> getBidderListFromTahdrMaterial(@PathVariable("typeCode")String typeCode,@PathVariable("tahdrMaterialId")Long tahdrMaterialId){
		Map<String, Object> params =  AbstractContextServiceImpl.getParamMap("tahdrMaterialId", tahdrMaterialId);
		List<PriceBidDto> priceBidList =new ArrayList<>();
		if(!typeCode.equals("") && (typeCode.equalsIgnoreCase("QRA") || typeCode.equalsIgnoreCase("QFA"))){
			priceBidList = priceBideService.findDtos("getQuickBidderListFromTahdrMaterialId", params);
		}else if(!typeCode.equals("") && (typeCode.equalsIgnoreCase("QRFQ") || typeCode.equalsIgnoreCase("RFQ"))){
			priceBidList = priceBideService.findDtos("getQuickBidderListFromTahdrMaterialId", params);
		}else{
			priceBidList = priceBideService.findDtos("getBidderListFromTahdrMaterialId", params);
		}
		
		return priceBidList;
	}
	
	@RequestMapping(value="/saveWinnerBidder", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody CustomResponseDto saveWinnerBidder(@ModelAttribute ("materialBidders") WinnerSelectionDto dto){
		CustomResponseDto respose=  new CustomResponseDto();
		WinnerSelectionDto winnerSelectionDto=new WinnerSelectionDto();
		TAHDRMaterialDto tahdrMaterialDto=tahdrMaterialService.findDto(dto.getItemBid().getTahdrMaterial().getTahdrMaterialId());		
		
		if(tahdrMaterialDto.getAllocatedQty()==null){
			if((CommonUtil.isEqual(tahdrMaterialDto.getQuantity(), dto.getAllocatedQty())) || tahdrMaterialDto.getQuantity()>dto.getAllocatedQty()){
				if(dto.getWinnerSelectionId() == null){
					winnerSelectionDto= tahdrAwardService.save(dto);
					
					Long balanceQty=tahdrMaterialDto.getQuantity()-dto.getAllocatedQty();
					if(balanceQty!=null){
						Map<String, Object> map = new HashMap<>();
						map.put("allocatedQty", balanceQty);
						tahdrMaterialService.updateByJpql(map, "tahdrMaterialId", tahdrMaterialDto.getTahdrMaterialId());
						winnerSelectionDto.setRemainingQty(balanceQty);
					}
				}
			}
		}
		else if(tahdrMaterialDto.getAllocatedQty()!=null){
			
			if((CommonUtil.isEqual(tahdrMaterialDto.getAllocatedQty(), dto.getAllocatedQty())) || tahdrMaterialDto.getAllocatedQty()>dto.getAllocatedQty())
			{
				if(dto.getWinnerSelectionId() == null){
				winnerSelectionDto= tahdrAwardService.save(dto);
				
				Long balanceQty=tahdrMaterialDto.getAllocatedQty()-dto.getAllocatedQty();
				if(balanceQty!=null){
					Map<String, Object> map = new HashMap<>();
					map.put("allocatedQty", balanceQty);
					tahdrMaterialService.updateByJpql(map, "tahdrMaterialId", tahdrMaterialDto.getTahdrMaterialId());
					winnerSelectionDto.setRemainingQty(balanceQty);
				} 
				}
				else if(dto.getWinnerSelectionId()!=null){
				WinnerSelectionDto winnerData= tahdrAwardService.findDto(dto.getWinnerSelectionId());
				Long totalAllotedQuantityToBidder=tahdrMaterialDto.getAllocatedQty()+winnerData.getAllocatedQty();
				if(totalAllotedQuantityToBidder>=dto.getAllocatedQty()){
					winnerSelectionDto= tahdrAwardService.updateDto(dto);
					Long balanceQty=totalAllotedQuantityToBidder-dto.getAllocatedQty();
					if(balanceQty!=null){
						Map<String, Object> map = new HashMap<>();
						map.put("allocatedQty", balanceQty);
						tahdrMaterialService.updateByJpql(map, "tahdrMaterialId", tahdrMaterialDto.getTahdrMaterialId());
						winnerSelectionDto.setRemainingQty(balanceQty);
					} 
				}
				else{
			respose.setMessage("The Allocated Quantity is Greater than the Remaining Quantity.");
			respose.setSuccess(false);
			return respose;
				}
				}
			
			}
		}
		Map<String, Object> params=new HashMap<>();
		params.put("tahdrMaterialId", dto.getItemBid().getTahdrMaterial().getTahdrMaterialId());
		List<PriceBidDto> priceBidList = priceBideService.findDtos("getBidderListFromTahdrMaterialId", params);
		respose.addObject("priceBidList", priceBidList);
		respose.addObject("winnerSelectionDto", winnerSelectionDto);
		respose.setSuccess(true);
		return respose;
	}
	
	@RequestMapping(value= "/getWinnerSelectionDataFromBidder/{bidderId}/{tahdrMaterialId}", method=RequestMethod.POST)
	public @ResponseBody CustomResponseDto getWinnerSelectionDataFromBidder(@PathVariable("bidderId")Long bidderId,@PathVariable("tahdrMaterialId")Long tahdrMaterialId){
		CustomResponseDto response=new CustomResponseDto();
		Map<String, Object> params=new HashMap<>();
		params.put("bidderId", bidderId);
		params.put("tahdrMaterialId", tahdrMaterialId);
		
		WinnerSelectionDto winnerDto = tahdrAwardService.findDto("getwinnerDataFromBidderID", params);
		response.addObject("winnerData", winnerDto); 
		return response;
	}
	
	@RequestMapping(value= "/submitWinnerSelectionTahdr/{tahdrId}", method=RequestMethod.POST)
	public @ResponseBody CustomResponseDto submitWinnerSelectionTahdr(@PathVariable("tahdrId")Long tahdrId){
	CustomResponseDto response=new CustomResponseDto();
	Map<String, Object> params=new HashMap<>();
	TAHDRDto tender=tahdrService.findDto(tahdrId);
	if(tender==null){
		response.setSuccess(false);
		response.setMessage("Cannot Submit Award Winner");
		} else {
			int data = 0;
			if(tender.getTahdrTypeCode().equals("QFA") || tender.getTahdrTypeCode().equals("QRA") ){
				response=liveBidService.saveQuickAuctionWinner(tahdrId);
				response.setSuccess((boolean) response.getObjectMap().get("Status"));
				if((boolean) response.getObjectMap().get("Status")){
					data = 1;
				}
				response.setMessage((String) response.getObjectMap().get("Message"));
			}else if(tender.getTahdrTypeCode().equals("QRFQ")){
				params.put("tahdrStatusCode", AppBaseConstant.QUICK_RFQ_AWARD_WINNER_COMPLETED);
				data=tahdrService.updateByJpql(params, "tahdrId", tahdrId);
			}else if(tender.getTahdrTypeCode().equals("RFQ")){
				params.put("tahdrStatusCode", AppBaseConstant.RFQ_AWARD_WINNER_COMPLETED);
				data=tahdrService.updateByJpql(params, "tahdrId", tahdrId);
			}else{
				params.put("tahdrStatusCode", AppBaseConstant.TENDER_AWARD_WINNER_COMPLETED);
				data=tahdrService.updateByJpql(params, "tahdrId", tahdrId);
			}
			if (data == 0) {
				response.setSuccess(false);
				response.setMessage("Cannot Submit Award Winner");
			} else {
				response.setSuccess(true);
				response.setMessage("Award Winner Submitted.");
				Map<String, Object> param = new HashMap<>();
				param.put("tahdrId", tahdrId);
				List<WinnerSelectionDto> winnerData = tahdrAwardService.findDtos("getWinnerListForMail", param);
				MailTemplateDto mailData = mailTemplateService
						.getMailTemplateFromTemplateType(AppBaseConstant.TEMPLATE_AWARD_WINNER);
				List<String> emailList=new ArrayList<>();
				for (WinnerSelectionDto winnerDto : winnerData) {
					Map<String, Object> map = AbstractServiceImpl.getParamMap("role",
							ContextConstant.USER_TYPE_SYSUSER);
					UserDto internalUser = userService.findDto("getUserByRoleCode", map);
					if (mailData != null) {
						if (winnerDto.getAllocatedQty() != null && winnerDto.getAllocatedQty() != 0l) {
							if (winnerDto.getItemBid().getBidder()!= null) {
								UserDto user=null;
								String email ="";
								if(tender.getTahdrTypeCode().equals("QRFQ") || tender.getTahdrTypeCode().equals("RFQ")){
									user=userService.fetchUser(winnerDto.getItemBid().getBidder().getPartner().getbPartnerId());
									email =user==null?"":user.getEmail();
								}else{
									email = winnerDto.getItemBid().getBidder().getCreatedBy().getEmail();
								}
								emailList.add(email);
							}
						}
					}
					Map<String, String> smsParam = new HashMap<String, String>();
					params.put(AppBaseConstant.SMS_PARAMETER_1,
							winnerDto.getTahdr().getTahdrCode().replaceAll(" ", "%20"));
					smsService.sendSMS(winnerDto.getItemBid().getBidder().getCreatedBy().getUserDetails().getMobileNo(),
							AppBaseConstant.SMS_TEMPLATE_AWARDED_FOR_TENDER, smsParam);
				}
				String tenderType =  tender.getTahdrTypeCode().equals("RFQ")?"Request For Proposal":tender.getTahdrTypeCode().equals("QRFQ")?"Quick Request For Proposal"
						:tender.getTahdrTypeCode().equals("FA")?"Forward Auction":tender.getTahdrTypeCode().equals("RA")?"Reverse Auction":tender.getTahdrTypeCode().equals("PT")?"Procuremet Tender":
							tender.getTahdrTypeCode().equals("WT")?"Works Tender ":"";
				String sub=	tenderType+" Winner";
				String message = "YOU HAVE BEEN SELECTED AS WINNER FOR AUCTION "
						+ tender.getTahdrCode() + ".CONGRATULATIONS.";
				partnerService.sendEmailToPartner(emailList, sub, message);
			}
			}
	return response;
	}
*/}
