package com.novelerp.eat.service;

import com.novelerp.core.service.CommonService;
import com.novelerp.eat.dto.ContractHeaderDto;
import com.novelerp.eat.entity.ContractHeader;

public interface ContractHeaderService extends CommonService<ContractHeader, ContractHeaderDto>{

	void updateHeader(Long contractHeaderId);

}
