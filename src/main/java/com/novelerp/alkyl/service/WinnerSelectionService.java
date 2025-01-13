package com.novelerp.alkyl.service;

import java.util.List;

import com.novelerp.alkyl.dto.AnnexureDto;
import com.novelerp.core.service.CommonService;
import com.novelerp.eat.dto.WinnerSelectionDto;
import com.novelerp.eat.entity.WinnerSelection;

public interface WinnerSelectionService extends CommonService<WinnerSelection,WinnerSelectionDto>{

	public List<WinnerSelectionDto> saveWinnerSelection(List<WinnerSelectionDto> winnerSelectionSet, AnnexureDto annexureDto);

}
