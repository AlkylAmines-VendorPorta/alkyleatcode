package com.novelerp.eat.service.impl;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.eat.dao.SapVendorDetailDao;
import com.novelerp.eat.dto.GSTVendorClassDto;
import com.novelerp.eat.dto.ReconAccountDto;
import com.novelerp.eat.dto.SapVendorDetailDto;
import com.novelerp.eat.dto.VendorAccountGroupDto;
import com.novelerp.eat.dto.WithHoldingTaxCodeDto;
import com.novelerp.eat.entity.SapVendorDetail;
import com.novelerp.eat.service.GSTVendorClassService;
import com.novelerp.eat.service.ReconAccountService;
import com.novelerp.eat.service.SapVendorDetailService;
import com.novelerp.eat.service.VendorAccountGroupService;
import com.novelerp.eat.service.WithHoldingTaxCodeService;
@Service
public class SapVendorDetailServiceImpl extends AbstractContextServiceImpl<SapVendorDetail, SapVendorDetailDto>  implements SapVendorDetailService{

	@Autowired
	private SapVendorDetailDao sapVendorDetailDao;
	@Autowired
	private WithHoldingTaxCodeService withHoldingTaxCodeService;
	@Autowired
	private VendorAccountGroupService vendorAccountGroupService;
	@Autowired
	private GSTVendorClassService gstVendorClassService;
	@Autowired
	private ReconAccountService reconAccountService;
	
	@PostConstruct
	private void init() {
		super.init(SapVendorDetailServiceImpl.class, sapVendorDetailDao, SapVendorDetail.class, SapVendorDetailDto.class);
		setByPassProxy(true);
	}
	
	@Override
	public SapVendorDetailDto setDtoDetail(SapVendorDetailDto sapVendorDto){
		
		VendorAccountGroupDto accountGroupDto = vendorAccountGroupService.findDto(sapVendorDto.getVendorAccountGroup().getVendorAccountGroupId());
		GSTVendorClassDto gstVendorClassDto = gstVendorClassService.findDto(sapVendorDto.getGstVendorClass().getGstVendorClassId());
		WithHoldingTaxCodeDto idctrWithtTaxTypeDto = withHoldingTaxCodeService.findDto(sapVendorDto.getIdctrWithtTaxType().getWithHoldingTaxCodeId());
		WithHoldingTaxCodeDto withtTaxType1Dto = withHoldingTaxCodeService.findDto(sapVendorDto.getWithHdTaxCode1().getWithHoldingTaxCodeId());
		WithHoldingTaxCodeDto withtTaxType2Dto = withHoldingTaxCodeService.findDto(sapVendorDto.getWithHdTaxCode2().getWithHoldingTaxCodeId());
		ReconAccountDto reconAccountDto = reconAccountService.findDto(sapVendorDto.getReconAccount().getReconAccountId());
		sapVendorDto.setVendorAccountGroup(accountGroupDto);
		sapVendorDto.setIdctrWithtTaxType(idctrWithtTaxTypeDto);
		sapVendorDto.setWithHdTaxCode1(withtTaxType1Dto);
		sapVendorDto.setWithHdTaxCode2(withtTaxType2Dto);
		sapVendorDto.setGstVendorClass(gstVendorClassDto);
		sapVendorDto.setReconAccount(reconAccountDto);
		return sapVendorDto;
	}
	
}
