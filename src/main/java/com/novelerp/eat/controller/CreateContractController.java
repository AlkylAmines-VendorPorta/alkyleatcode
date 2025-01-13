package com.novelerp.eat.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.core.dto.CustomResponseDto;
import com.novelerp.core.dto.ResponseDto;
import com.novelerp.eat.dto.BidderDto;
import com.novelerp.eat.dto.ContractConditionDto;
import com.novelerp.eat.dto.ContractHeaderDto;
import com.novelerp.eat.dto.ContractItemDto;
import com.novelerp.eat.dto.ContractServiceDto;
import com.novelerp.eat.dto.TAHDRDto;
import com.novelerp.eat.dto.WinnerSelectionDto;
import com.novelerp.eat.msedcl.createContract.CreateContractDto;
import com.novelerp.eat.msedcl.createContract.ZMMCREATECONTRACTResponse;
import com.novelerp.eat.service.BidderService;
import com.novelerp.eat.service.ContractConditionService;
import com.novelerp.eat.service.ContractHeaderService;
import com.novelerp.eat.service.ContractItemService;
import com.novelerp.eat.service.ContractServiceService;
import com.novelerp.eat.service.TAHDRService;
import com.novelerp.eat.service.TahdrAwardWinnerService;

@Controller
public class CreateContractController {

	@Autowired
	private TahdrAwardWinnerService tahdrAwardWinnerService;
	/*@Autowired
	private CreateContractClient createContractClient;*/
	@Autowired
	private TAHDRService tahdrService;
	@Autowired
	private ContractHeaderService contractHeaderService;
	@Autowired
	private ContractItemService contractItemService;
	@Autowired
	private ContractServiceService contractServiceService;
	@Autowired
	private ContractConditionService contractConditionService;
	@Autowired
	private BidderService bidderService;

	@RequestMapping(value = "/getTahdrForContract/{tenderTypeCode}/{isCreated}/{pageNumber}/{pageSize}/{searchMode}/{serachValue}", method = RequestMethod.POST)
	@ResponseBody
	public CustomResponseDto tenderPreparationData(@PathVariable("tenderTypeCode") String tenderTypeCode,
			@PathVariable("isCreated") String isCreated, @PathVariable("pageNumber") int pageNumber,
			@PathVariable("pageSize") int pageSize, @PathVariable("searchMode") String searchMode,
			@PathVariable("serachValue") String serachValue) {
		Map<String, Object> params = new HashMap<>();
		params.put("tenderTypeCode", tenderTypeCode);
		params.put("status", AppBaseConstant.TENDER_AWARD_WINNER_COMPLETED);
		
		List<TAHDRDto> tahdrList = tahdrService.getTahdrWithDetailsForAwardWinner(params, pageNumber, pageSize,searchMode, serachValue);
		Long countResult = tahdrService.getTahdrWithDetailsForAwardWinnerCount(params, searchMode, serachValue);
		int LastPage = (int) ((countResult / pageSize) + 1);
		CustomResponseDto response = new CustomResponseDto();
		response.setData(tahdrList);
		response.addObject("LastPage", LastPage);
		response.addObject("tenderTypeCode", tenderTypeCode);
		return response;
	}

	@RequestMapping(value = "getTahdrForCreateContract", method = RequestMethod.POST)
	@ResponseBody
	public CustomResponseDto getTahdrForcreateContract() {
		CustomResponseDto customResponseDto = new CustomResponseDto();
		List<WinnerSelectionDto> list = tahdrAwardWinnerService.getTahdrWithDetailsForCrateContract(null, 1, 100,
				"none", "none");
		customResponseDto.setData(list);
		return customResponseDto;
	}

	@RequestMapping(value = "getWinnerBidderListByTahdrId/{tahdrId}", method = RequestMethod.POST)
	@ResponseBody
	public List<BidderDto> getBidersForContractDetail(@PathVariable("tahdrId") Long tahdrId) {
		List<BidderDto> list = tahdrAwardWinnerService.getWinnerBidderListByTahdrId(tahdrId);
		return list;
	}

	@RequestMapping(value = "getContractDetails/{bidderId}/{tahdrId}", method = RequestMethod.POST)
	@ResponseBody
	public CustomResponseDto getContractDetails(@PathVariable("bidderId") Long bidderId,
			@PathVariable("tahdrId") Long tahdrId) {
		return tahdrAwardWinnerService.getBidderContractDetails(bidderId, tahdrId);
	}

	/*
	 * @RequestMapping(value = "createContract", method = RequestMethod.POST)
	 * 
	 * @ResponseBody public String
	 * createContract(@ModelAttribute("createContract") CreateContractDto
	 * createContractDto) { return
	 * createContractClient.createContract(createContractDto).getLVALLMSGS(); }
	 */

	@RequestMapping(value = "saveContractHeader", method = RequestMethod.POST)
	@ResponseBody
	public ResponseDto saveContractHeader(@ModelAttribute("createContract") ContractHeaderDto contractHeader) {
		try {

			Map<String, Object> param = new HashMap<String, Object>();
			param.put("bidderId", contractHeader.getBidder().getBidderId());
			BidderDto bidderDto = bidderService.findDto("getBidder", param);
			TAHDRDto tender = tahdrService.findDto(contractHeader.getTahdr().getTahdrId());
			contractHeader.setBidder(bidderDto);
			contractHeader.setTahdr(tender);
			
			  if(contractHeader.getContractHeaderId() != null){
				  contractHeaderService.update(contractHeader); }
			  else{
				  contractHeaderService.save(contractHeader);
			  }
			 
			return new ResponseDto(false, "Saved successfully");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseDto(true, "Error");
	}

	@RequestMapping(value = "saveContractItem", method = RequestMethod.POST)
	@ResponseBody
	public ResponseDto saveContractItem(@ModelAttribute("createContract") ContractItemDto contractItemDto) {
		try {
			if(contractItemDto.getContractItemId() == null){
				ContractHeaderDto contractHeaderDto = contractHeaderService.findDto(contractItemDto.getContractHeader().getContractHeaderId());
				WinnerSelectionDto winnerSelectionDto = tahdrAwardWinnerService.findDto(contractItemDto.getWinnerSelection().getWinnerSelectionId());
				if (contractHeaderDto == null) {
					return new ResponseDto(true, "Contract Not Created Yet.");
				}
				contractItemDto.setContractHeader(contractHeaderDto);
				contractItemDto.setWinnerSelection(winnerSelectionDto);
				contractItemService.save(contractItemDto);
			}else{
				contractItemService.update(contractItemDto);
			}
			return new ResponseDto(false, "Saved successfully");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseDto(true, "Error");
	}

	@RequestMapping(value = "saveContractService", method = RequestMethod.POST)
	@ResponseBody
	public ResponseDto saveContractService(@ModelAttribute("createContract") ContractServiceDto contractServiceDto) {
		try {
			if (contractServiceDto.getContractServiceId() == null) {
				ContractHeaderDto contractHeaderDto = contractHeaderService.findDto(contractServiceDto.getContractHeader().getContractHeaderId());
				WinnerSelectionDto winnerSelectionDto = tahdrAwardWinnerService.findDto(contractServiceDto.getWinnerSelection().getWinnerSelectionId());
				if (contractHeaderDto == null) {
					return new ResponseDto(true, "Contract Not Created Yet.");
				}
				contractServiceDto.setContractHeader(contractHeaderDto);
				contractServiceDto.setWinnerSelection(winnerSelectionDto);
				contractServiceService.save(contractServiceDto);
			}else{
				contractServiceService.update(contractServiceDto);
			}
			return new ResponseDto(false, "Saved successfully");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseDto(true, "Error");
	}

	@RequestMapping(value = "saveContractCndnForm", method = RequestMethod.POST)
	@ResponseBody
	public ResponseDto saveContractCondition(@ModelAttribute("createContract") ContractConditionDto conditionDto) {
		try {
			if (conditionDto.getContractConditionId() == null) {
				ContractHeaderDto contractHeaderDto = contractHeaderService.findDto(conditionDto.getContractHeader().getContractHeaderId());
				WinnerSelectionDto winnerSelectionDto = tahdrAwardWinnerService.findDto(conditionDto.getWinnerSelection().getWinnerSelectionId());
				if (contractHeaderDto == null) {
					return new ResponseDto(true, "Contract Not Created Yet.");
				}
				conditionDto.setContractHeader(contractHeaderDto);
				conditionDto.setWinnerSelection(winnerSelectionDto);
				contractConditionService.save(conditionDto);
			}else{
				contractConditionService.update(conditionDto);
			}
			return new ResponseDto(false, "Saved successfully");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseDto(true, "Error");
	}

	@RequestMapping(value = "createContract/{headerId}", method = RequestMethod.POST)
	@ResponseBody
	public ResponseDto createContract(@PathVariable("headerId") Long headerId) {

		CreateContractDto contractDto = new CreateContractDto();
		Map<String, Object> params = new HashMap<>();
		params.put("headerId", headerId);
		String whereClause = "contractHeader.contractHeaderId = :headerId";

		ContractHeaderDto contractHeaderDto = contractHeaderService.findDto("getHeaderDetails", params);
		List<ContractItemDto> contractItemDtoList = contractItemService.find(whereClause, params, null);
		List<ContractServiceDto> contractServiceDtoList = contractServiceService.find(whereClause, params, null);
		List<ContractConditionDto> contractConditionDtoList = contractConditionService.find(whereClause, params, null);
		contractDto.setZmmcontractheader(contractHeaderDto);
		contractDto.setContractItemList(contractItemDtoList);
		contractDto.setContractItemserviceList(contractServiceDtoList);
		contractDto.setContractItemCondList(contractConditionDtoList);
		ZMMCREATECONTRACTResponse response = null;
		try {
			//response = createContractClient.createContract(contractDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (response != null) {
			contractHeaderService.updateHeader(headerId);
			return new ResponseDto(false, response.getLVALLMSGS());
		} else {
			return new ResponseDto(true, "Something went wrong");
		}
	}
}
