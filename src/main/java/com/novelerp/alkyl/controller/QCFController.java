package com.novelerp.alkyl.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.novelerp.alkyl.component.AnnexureComponent;
import com.novelerp.alkyl.dto.AnnexureDto;
import com.novelerp.alkyl.dto.EnquiryDto;
import com.novelerp.alkyl.dto.PRDto;
import com.novelerp.alkyl.dto.PRLineDto;
import com.novelerp.alkyl.dto.PraposedReasonDto;
import com.novelerp.alkyl.service.AnnexureService;
import com.novelerp.alkyl.service.PRLineService;
import com.novelerp.alkyl.service.PRService;
import com.novelerp.alkyl.service.VendorEnquiryService;
import com.novelerp.appbase.master.service.ReferenceListService;
import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.appcontext.dto.BPartnerDto;
import com.novelerp.appcontext.service.ContextService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.commons.util.CommonUtil;
import com.novelerp.core.dto.CustomResponseDto;
import com.novelerp.core.util.CoreReferenceConstants;
import com.novelerp.eat.dto.BidderDto;
import com.novelerp.eat.dto.ItemBidDto;
import com.novelerp.eat.dto.PriceBidDto;
import com.novelerp.eat.dto.WinnerSelectionDto;
import com.novelerp.eat.service.BidderService;
import com.novelerp.eat.service.PriceBidService;

@Controller
@RequestMapping(value = "/rest")
public class QCFController {

	private Logger log = LoggerFactory.getLogger(getClass());
	@Autowired
	private PRLineService prLineService;

	@Autowired
	private PriceBidService priceBidService;

	@Autowired
	private ReferenceListService refListService;

	@Autowired
	@Qualifier("jwtUserContext")
	private ContextService contextService;

	@Autowired
	private PRService prService;

	@Autowired
	private BidderService bidderService;

	@Autowired
	private AnnexureService annexureService;
	
	@Autowired
	private VendorEnquiryService vendorEnquiryService;

	/*
	 * @Autowired private WinnerSelectionService winnerSelectionService;
	 */

	/*
	 * @Autowired private PraposedReasonService praposedReasonService;
	 */

	@Autowired
	private AnnexureComponent annexureComponent;

	@PostMapping(value = "/getQcfPR")
	public @ResponseBody CustomResponseDto getQcfPR() {
		CustomResponseDto resp = null;
		List<PRDto> prList = new ArrayList<PRDto>();
		List<EnquiryDto> enquiryList = new ArrayList<EnquiryDto>();
		String role = null;
		BPartnerDto partner = null;
		Map<String, String> prStatusList = refListService.getReferenceListMap(CoreReferenceConstants.PR_STATUS);

		if (contextService != null && contextService.getDefaultRole() != null
				&& !CommonUtil.isStringEmpty(contextService.getDefaultRole().getValue())) {
			role = contextService.getDefaultRole().getValue();
			partner = contextService.getPartner();
		}
		if (AppBaseConstant.ROLE_PURCHASE_MANAGER_ADMIN.equals(role)
				|| AppBaseConstant.ROLE_GENERAL_MANAGER_ADMIN.equals(role)
				|| AppBaseConstant.ROLE_EXECUTIVE_MANAGER_ADMIN.equals(role)) {
			List<AnnexureDto> annexureDtos = annexureService.getPrForQcf(role);
			for(AnnexureDto annexure:annexureDtos){
				enquiryList.add(annexure.getEnquiry());
			}
		}else{
			/*prList = prService.findDtos("getPR", null);*/
			 Map<String , Object> params = new HashMap<String, Object>();
	           /* params.put("currentDate",new Date());*/
			enquiryList=vendorEnquiryService.findDtos("getEnquiryForQCF", params);
		}
		/*
		 * List<UserDto> buyerList=userService.findDtos("getUserListByRole",
		 * AbstractContextServiceImpl.getParamMap("value",
		 * AppBaseConstant.ROLE_BUYER_ADMIN));
		 * 
		 * List<UserDto> technicalList=userService.findDtos("getUserListByRole",
		 * AbstractContextServiceImpl.getParamMap("value",
		 * AppBaseConstant.ROLE_TECHNICAL_ADMIN));
		 */

		Map<String, String> priorityList = refListService.getReferenceListMap(CoreReferenceConstants.PRIORITY);

		Map<String, String> proposedReasonList = refListService
				.getReferenceListMap(CoreReferenceConstants.PRIORITY_REASON);

		/*resp = new CustomResponseDto("prList", prList);*/
		resp = new CustomResponseDto("prStatusList", prStatusList);
		resp.addObject("role", role);
		resp.addObject("partner", partner);
		/*
		 * resp.addObject("buyerList", buyerList);
		 * resp.addObject("technicalList", technicalList);
		 */
		resp.addObject("priorityList", priorityList);
		resp.addObject("proposedReasonList", proposedReasonList);
		resp.addObject("enquiryList", enquiryList);

		return resp;
	}

	@PostMapping(value = "/getQCF/{prId}")
	public @ResponseBody CustomResponseDto getQCF(@PathVariable("prId") Long prId) {

		CustomResponseDto resp = null;
		String role = null;
		BPartnerDto partner = null;
		Map<String, Object> params = AbstractContextServiceImpl.getParamMap("prId", prId);

		if (contextService != null && contextService.getDefaultRole() != null
				&& !CommonUtil.isStringEmpty(contextService.getDefaultRole().getValue())) {
			role = contextService.getDefaultRole().getValue();
			partner = contextService.getPartner();
		}

		List<PriceBidDto> priceBidList = priceBidService.findDtos("getPriceBidByPrId", params);
		List<BidderDto> bidderList = bidderService.findDtos("getBidderByPrId", params);
		List<PRLineDto> prLineList = getPRLIneByItemBid(bidderList.get(0));

		List<WinnerSelectionDto> winnerSelectionList = null;
		List<PraposedReasonDto> proposedReasonList = null;

		AnnexureDto annexureDto = annexureService.findDto("getAnnexureByPrId", params);

		if (annexureDto != null) {
			/*
			 * winnerSelectionList=winnerSelectionService.findDtos(
			 * "getWinnerSelectionByAnnexureId",
			 * AbstractContextServiceImpl.getParamMap("annexureId",
			 * annexureDto.getAnnexureId()));
			 */
			winnerSelectionList = annexureComponent.getWinnerSelectionByAnnexureId(annexureDto.getAnnexureId());
			proposedReasonList = annexureComponent.getProposedReasonByAnnexureId(annexureDto.getAnnexureId());
			/*
			 * proposedReasonList=praposedReasonService.findDtos(
			 * "getProposedReasonByAnnexureId",
			 * AbstractContextServiceImpl.getParamMap("annexureId",
			 * annexureDto.getAnnexureId()));
			 */
		}

		resp = new CustomResponseDto("priceBidList", priceBidList);
		resp.addObject("bidderList", bidderList);
		resp.addObject("prLineList", prLineList);

		resp.addObject("annexureDto", annexureDto);
		resp.addObject("winnerSelectionList", winnerSelectionList);
		resp.addObject("praposedReasonList", proposedReasonList);

		resp.addObject("role", role);
		resp.addObject("partner", partner);

		return resp;
	}

	@PostMapping(value = "/generateQCF/{prId}")
	public @ResponseBody CustomResponseDto generateQCF(@PathVariable("prId") Long prId) {

		try {
			prService.generateQCF(prId);
			return new CustomResponseDto(true, "QCF Generated Successfully");
		} catch (Exception e) {
			log.info("ERROR", e);
			return new CustomResponseDto(true, e.getMessage());
		}

	}

	@PostMapping(value = "/getQCFReport/{prId}")
	public @ResponseBody CustomResponseDto getQCFReport(@PathVariable("prId") Long prId) {

		try {
			return new CustomResponseDto("reportList", prService.getQCFReport(prId));
		} catch (Exception e) {
			log.info("ERROR", e);
			return new CustomResponseDto(true, e.getMessage());
		}

	}

	public List<PRLineDto> getPRLIneByItemBid(BidderDto dto){
		List<PRLineDto> prLineList = new ArrayList<>();
		for(ItemBidDto itemDto:dto.getItemBidList()){
			if(null!=itemDto.getPrLine())
			prLineList.add(itemDto.getPrLine());
		}
		return prLineList;
		
	}
	@PostMapping(value = "/getQCFForApproval/{prId}")
	public @ResponseBody CustomResponseDto getQCFForApproval(@PathVariable("prId") Long prId) {

		CustomResponseDto resp = null;
		String role = null;
		BPartnerDto partner = null;
		Map<String, Object> params = AbstractContextServiceImpl.getParamMap("prId", prId);

		if (contextService != null && contextService.getDefaultRole() != null
				&& !CommonUtil.isStringEmpty(contextService.getDefaultRole().getValue())) {
			role = contextService.getDefaultRole().getValue();
			partner = contextService.getPartner();
		}

		List<PriceBidDto> priceBidList = priceBidService.findDtos("getPriceBidByPrId", params);
		List<BidderDto> bidderList = bidderService.findDtos("getBidderByPrId", params);
		List<PRLineDto> prLineList = getPRLIneByItemBid(bidderList.get(0));

		List<WinnerSelectionDto> winnerSelectionList = null;
		List<PraposedReasonDto> proposedReasonList = null;

		AnnexureDto annexureDto = annexureService.findDto("getAnnexureByPrId", params);

		if (annexureDto != null) {
			/*
			 * winnerSelectionList=winnerSelectionService.findDtos(
			 * "getWinnerSelectionByAnnexureId",
			 * AbstractContextServiceImpl.getParamMap("annexureId",
			 * annexureDto.getAnnexureId()));
			 */
			winnerSelectionList = annexureComponent.getWinnerSelectionByAnnexureId(annexureDto.getAnnexureId());
			proposedReasonList = annexureComponent.getProposedReasonByAnnexureId(annexureDto.getAnnexureId());
			/*
			 * proposedReasonList=praposedReasonService.findDtos(
			 * "getProposedReasonByAnnexureId",
			 * AbstractContextServiceImpl.getParamMap("annexureId",
			 * annexureDto.getAnnexureId()));
			 */
		}

		resp = new CustomResponseDto("priceBidList", priceBidList);
		resp.addObject("bidderList", bidderList);
		resp.addObject("prLineList", prLineList);

		resp.addObject("annexureDto", annexureDto);
		resp.addObject("winnerSelectionList", winnerSelectionList);
		resp.addObject("praposedReasonList", proposedReasonList);

		resp.addObject("role", role);
		resp.addObject("partner", partner);

		return resp;
	}
}
