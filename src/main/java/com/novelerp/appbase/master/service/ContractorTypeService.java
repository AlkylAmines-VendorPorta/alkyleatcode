package com.novelerp.appbase.master.service;

import java.util.List;

import com.novelerp.appbase.master.dto.ContractorTypeDto;
import com.novelerp.appbase.master.entity.ContractorType;
import com.novelerp.core.service.CommonService;

/**
 *  @author Aman
  */
public interface ContractorTypeService extends CommonService<ContractorType, ContractorTypeDto> {

	public List<ContractorTypeDto> getContractorTypeList();
	public ContractorTypeDto getContractorType(Long id);
	/*public ResponseDto addContractorType(ContractorTypeDto dto);
	public ResponseDto editContractorType(ContractorTypeDto dto);
	public ResponseDto deleteContractorType(Long id);*/
}
