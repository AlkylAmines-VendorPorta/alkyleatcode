package com.novelerp.alkyl.dao.impl;

import org.springframework.stereotype.Repository;

import com.novelerp.alkyl.dao.WinnerSelectionDao;
import com.novelerp.core.dao.impl.AbstractJpaDAO;
import com.novelerp.eat.dto.WinnerSelectionDto;
import com.novelerp.eat.entity.WinnerSelection;

@Repository
public class WinnerSelectionDaoImpl extends AbstractJpaDAO<WinnerSelection, WinnerSelectionDto> implements WinnerSelectionDao{
	
	@Override
	public void postConstruct() {
		setClazz(WinnerSelection.class, WinnerSelectionDto.class);
	}
	
	public String getWinnerSelectionByAnnexureId(){
		StringBuilder jpql = new StringBuilder(" SELECT A FROM WinnerSelection A ")
		.append(" INNER JOIN FETCH A.annexure C ")
		.append(" INNER JOIN FETCH A.itemBid D ")
		.append(" INNER JOIN FETCH D.bidder E ")
		.append(" INNER JOIN FETCH E.partner F ")
		.append(" INNER JOIN FETCH D.prLine G ")
		.append(" INNER JOIN FETCH C.enquiry H ")
		.append(" INNER JOIN FETCH G.pr I ")
		.append(" INNER JOIN FETCH G.buyer J ");
		jpql.append(" WHERE A.annexure.annexureId=:annexureId and A.isActive='Y'");
		jpql.append( " ORDER BY D.prLine.prLineNumber ASC");
		//jpql.append(" WHERE A.annexure.annexureId=:annexureId and A.isActive='Y' and A.allocatedQty>0 ");
		return jpql.toString();
	}
	

}
