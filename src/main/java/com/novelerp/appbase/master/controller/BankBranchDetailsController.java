package com.novelerp.appbase.master.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.novelerp.appbase.master.dto.BankBranchDetailsDto;
import com.novelerp.appbase.master.service.BankBranchDetailsService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.core.dto.CustomResponseDto;

/** 
 * @author Ankita
 *
 */
@Controller
public class BankBranchDetailsController {

	private final Logger log = LoggerFactory.getLogger(BankBranchDetailsController.class);
	
	@Autowired
	private BankBranchDetailsService bankBranchDetailsService;
	
	@RequestMapping(value = "/getBranchName/{bankNameDetailsId}", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody List<BankBranchDetailsDto> getBranchName(@PathVariable("bankNameDetailsId") Long bankNameDetailsId) {
		Map<String, Object> params =  AbstractContextServiceImpl.getParamMap("bankNameDetailsId", bankNameDetailsId);
		List<BankBranchDetailsDto> bankBranch = bankBranchDetailsService.findDtos("getBankBranchFromBankNameId", params);
		return bankBranch;
	}
	
	@RequestMapping(value= "/getIFSCCode/{branchId}", method=RequestMethod.POST)
	@ResponseBody
	public  CustomResponseDto getIFSCCode(@PathVariable("branchId")Long branchId){
		BankBranchDetailsDto dto=null;
		dto=bankBranchDetailsService.findDto(branchId);
		CustomResponseDto response=new CustomResponseDto();
		response.addObject("ifsc", dto);
		return response;
	}
	
}

