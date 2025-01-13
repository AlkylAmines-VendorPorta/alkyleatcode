package com.novelerp.alkyl.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.novelerp.alkyl.component.VendorProfileHistoryComponent;
import com.novelerp.appbase.master.dto.PartnerBankDetailDto;
import com.novelerp.appbase.master.dto.PartnerOrgDto;
import com.novelerp.appbase.master.service.PartnerOrgService;
import com.novelerp.appcontext.dto.BPartnerDto;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.commons.util.CommonUtil;

@Controller
@RequestMapping(value="/rest")
public class OtherDetailsController {
	@Autowired
	private PartnerOrgService partnerOrgService;
	@Autowired
	private VendorProfileHistoryComponent vendorProfileComponent;

	@RequestMapping(value="/getOtherDetails/{partnerId}", method=RequestMethod.POST, produces="application/json")
	public @ResponseBody PartnerOrgDto getOtherDetails(@PathVariable("partnerId") Long partnerId){
		List<PartnerOrgDto> orgList = partnerOrgService.findDtos("getFactoryListQuery", AbstractContextServiceImpl.getParamMap("partnerId", partnerId));
		if(!CommonUtil.isCollectionEmpty(orgList)){
			return orgList.get(0);
		}else{
			return new PartnerOrgDto();
		}
		
	}
	
	@RequestMapping(value="/saveOtherDetails", method=RequestMethod.POST, produces="application/json")
	public @ResponseBody PartnerOrgDto saveOrgDetails (@RequestBody PartnerOrgDto dto){
//		dto=partnerOrgService.savePartnerOrg(dto)
		
		if( dto.getPartnerOrgId() != null){
			try{
				Map<String,Object> param = new HashMap<String,Object>();
				param.put("partnerOrgId", dto.getPartnerOrgId());
				PartnerOrgDto oldDto=partnerOrgService.findDto("getPlainPartnerOrgById", param);
				vendorProfileComponent.createOtherhistory(dto,oldDto);
				}catch(Exception e){
					e.printStackTrace();
				}
			return partnerOrgService.updateDto(dto);
		
		}else{

			return partnerOrgService.savePartnerOrg(dto);
			
		}
	}
}
