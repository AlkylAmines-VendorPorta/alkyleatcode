package com.novelerp.alkyl.service;

import java.util.List;

import com.novelerp.alkyl.dto.AnnexureDto;
import com.novelerp.alkyl.dto.PraposedReasonDto;
import com.novelerp.alkyl.entity.PraposedReason;
import com.novelerp.core.service.CommonService;

public interface PraposedReasonService extends CommonService<PraposedReason, PraposedReasonDto>{

	public List<PraposedReasonDto> savePraposedReason(List<PraposedReasonDto> praposedReasonSet, AnnexureDto annexureDto);

}
