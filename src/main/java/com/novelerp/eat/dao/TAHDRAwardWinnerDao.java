package com.novelerp.eat.dao;

import com.novelerp.core.dao.CommonDao;
import com.novelerp.eat.dto.WinnerSelectionDto;
import com.novelerp.eat.entity.WinnerSelection;

public interface TAHDRAwardWinnerDao extends CommonDao<WinnerSelection, WinnerSelectionDto>{

	String getWinnerForCreateContractQuery();

	String getQueryForCreateContract();

}
