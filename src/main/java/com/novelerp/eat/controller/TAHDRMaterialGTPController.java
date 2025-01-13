/**
 * @author Ankush
 */
package com.novelerp.eat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.novelerp.appbase.master.dto.GtpParameterDto;
import com.novelerp.appbase.master.service.GtpParameterService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.commons.util.CommonUtil;
import com.novelerp.core.dto.CustomResponseDto;
import com.novelerp.core.service.impl.AbstractServiceImpl;
import com.novelerp.eat.converter.TAHDRMaterialGTPConverter;
import com.novelerp.eat.dto.TAHDRDetailDto;
import com.novelerp.eat.dto.TAHDRMaterialGTPDto;
import com.novelerp.eat.dto.TAHDRMaterialGtpList;
import com.novelerp.eat.service.TAHDRDetailService;
import com.novelerp.eat.service.TAHDRMaterialGTPService;

@Controller
public class TAHDRMaterialGTPController {

	@Autowired
	private TAHDRMaterialGTPService tahdrMaterialGtpService;
	
	@Autowired
	private GtpParameterService gtpService;
	
	@Autowired
	private TAHDRMaterialGTPConverter tahdrMaterialGtpConverter; 
	
	@Autowired
	private TAHDRDetailService tahdrDetailService;
	
	@RequestMapping(value= "/getTahdrGtpByMaterialId/{tahdrMaterialId}/{materialId}", method=RequestMethod.POST)
	public @ResponseBody CustomResponseDto saveTahdrDetails(@PathVariable("tahdrMaterialId") Long tahdrMaterialId,@PathVariable("materialId") Long materialId){
		List<GtpParameterDto> gtpList=gtpService.findDtos("getGtpParameterListByMaterial", AbstractContextServiceImpl.getParamMap("materialId", materialId));
		List<TAHDRMaterialGTPDto> tahdrGtpList=tahdrMaterialGtpService.findDtos("getTahdrGtpListByMaterialIdQuery", AbstractServiceImpl.getParamMap("tahdrMaterialId", tahdrMaterialId),tahdrMaterialGtpConverter);
		for(GtpParameterDto gtpDto: gtpList){
			int count=0;
			for(TAHDRMaterialGTPDto tahdrGtpDto: tahdrGtpList){
				if(!CommonUtil.isEqual(gtpDto.getGtpParameterId(), tahdrGtpDto.getGtp().getGtpParameterId())){
					count++;
				}else{
					continue;
				}
			}
			if(count==tahdrGtpList.size()){
				TAHDRMaterialGTPDto tahdrMaterialGtp=new TAHDRMaterialGTPDto();
				tahdrMaterialGtp.setGtp(gtpDto);
				tahdrGtpList.add(tahdrMaterialGtp);
			}
		}
		CustomResponseDto responseDto=new CustomResponseDto();
		responseDto.addObject("tahdrGtpList", tahdrGtpList);
		return responseDto;
	}
	
	@RequestMapping(value= "/saveTahdrMaterialGtp", method=RequestMethod.POST)
	public @ResponseBody CustomResponseDto saveTahdrDetails(@ModelAttribute("tahdrMaterialGTPList") TAHDRMaterialGtpList tahdrMaterialGTPList){
		List<TAHDRMaterialGTPDto> gtpDtoList=null;
		int updateTahdrDetail=0;
		CustomResponseDto response=new CustomResponseDto();
		try{
			TAHDRDetailDto tahdrDetailDto=null;
			if(tahdrMaterialGTPList.getGtpList()!= null){
				tahdrDetailDto=tahdrDetailService.findDto("getTenderDocDetails", AbstractContextServiceImpl.getParamMap("tahdrDetailId", tahdrMaterialGTPList.getGtpList().iterator().next().getTahdrDetail().getTahdrDetailId()));
				gtpDtoList = tahdrMaterialGtpService.saveTahdrMaterialGtpList(tahdrMaterialGTPList.getGtpList());
				updateTahdrDetail=tahdrDetailService.updateTenderDocOnDrafted(tahdrDetailDto);
				response.addObject("gtpDtoList", gtpDtoList);
				response.setSuccess(true);
				response.setMessage("Saved successfully..!");
			}
			else{
				response.setSuccess(false);
				response.setMessage("Nothing to save..!");
			}
			
		}catch(Exception ex){
			ex.printStackTrace();
			response.setSuccess(false);
			response.setMessage("Failed to save..!");
		}
		return response;
	}
	
}
