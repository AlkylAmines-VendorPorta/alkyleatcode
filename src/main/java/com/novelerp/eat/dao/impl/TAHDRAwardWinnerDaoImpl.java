package com.novelerp.eat.dao.impl;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import com.novelerp.core.dao.impl.AbstractJpaDAO;
import com.novelerp.eat.dao.TAHDRAwardWinnerDao;
import com.novelerp.eat.dto.WinnerSelectionDto;
import com.novelerp.eat.entity.WinnerSelection;

@Repository
public class TAHDRAwardWinnerDaoImpl extends AbstractJpaDAO<WinnerSelection, WinnerSelectionDto> implements TAHDRAwardWinnerDao {

	@PostConstruct
	public void postConstruct() {
		setClazz(WinnerSelection.class, WinnerSelectionDto.class);
	}
	
	public String getwinnerDataFromBidderID(){
		StringBuilder jpql =  new StringBuilder(" Select ws from WinnerSelection ws ");
		jpql.append(" LEFT JOIN FETCH ws.itemBid ib ");
		jpql.append(" LEFT JOIN FETCH ib.tahdrMaterial tm ");
		jpql.append(" LEFT JOIN FETCH ib.bidder b ");
		jpql.append(" WHERE b.bidderId=:bidderId AND tm.tahdrMaterialId=:tahdrMaterialId ");
		return jpql.toString();
	}
	
	public String getWinnerListByTypecodeandStatus(){
		StringBuilder jpql =  new StringBuilder(" Select ws from WinnerSelection ws ");
		jpql.append(" INNER JOIN FETCH ws.itemBid ib ");
		jpql.append(" INNER JOIN FETCH ib.tahdrMaterial ibtm ");
		jpql.append(" INNER JOIN FETCH ibtm.material ibtmm ");
		jpql.append(" INNER JOIN FETCH ib.bidder b ");
		jpql.append(" INNER JOIN FETCH b.partner bp ");
		jpql.append(" INNER JOIN FETCH ws.tahdr t ");
		jpql.append(" INNER JOIN FETCH t.tahdrDetail td ");
		jpql.append(" LEFT JOIN FETCH b.factory f ");
		jpql.append(" WHERE t.tahdrTypeCode=:typeCode AND td.isActive='Y'");
		jpql.append(" AND t.tahdrStatusCode= :status");
		return jpql.toString();
	}
	
	public String getWinnerListForMail(){
		StringBuilder jpql =  new StringBuilder(" Select ws from WinnerSelection ws ");
		jpql.append(" INNER JOIN FETCH ws.itemBid ib ");
		jpql.append(" INNER JOIN FETCH ib.tahdrMaterial ibtm ");
		jpql.append(" INNER JOIN FETCH ibtm.material ibtmm ");
		jpql.append(" INNER JOIN FETCH ib.bidder b ");
		jpql.append(" LEFT JOIN FETCH b.createdBy cr ");
		jpql.append(" INNER JOIN FETCH b.partner bp ");
		jpql.append(" INNER JOIN FETCH ws.tahdr t ");
		jpql.append(" INNER JOIN FETCH t.tahdrDetail td ");
		jpql.append(" LEFT JOIN FETCH b.factory f ");
		jpql.append(" LEFT JOIN FETCH cr.userDetails ud ");
		jpql.append(" WHERE t.tahdrId=:tahdrId ");
		return jpql.toString();
	}
	
	@Override
	public String getWinnerForCreateContractQuery(){
		/*StringBuilder jpql =  new StringBuilder(" Select winner from WinnerSelection winner ");
		jpql.append(" LEFT JOIN FETCH  winner.itemBid itembid");
		jpql.append(" LEFT JOIN FETCH  itembid.bidder bidder");
		jpql.append(" LEFT JOIN FETCH  itembid.tahdrMaterial ibtm ");
		jpql.append(" LEFT JOIN FETCH  ibtm.material ibtmm ");
		jpql.append(" LEFT JOIN FETCH bidder.createdBy cr ");
		jpql.append(" LEFT JOIN FETCH bidder.partner bp ");
		jpql.append(" LEFT JOIN FETCH winner.tahdr t ");
		jpql.append(" LEFT JOIN FETCH t.tahdrDetail td ");
		jpql.append(" LEFT JOIN FETCH bidder.factory f ");
		jpql.append(" LEFT JOIN FETCH cr.userDetails ud ");*/
		
		StringBuilder jpql =  new StringBuilder(" SELECT DISTINCT(bidder) FROM Bidder bidder ");
		jpql.append(" INNER JOIN FETCH bidder.partner bp ");
		jpql.append(" LEFT JOIN WinnerSelection ws ON ws.tahdr.tahdrId = bidder.tahdr .tahdrId ");
		jpql.append(" WHERE ws.tahdr.tahdrId = :tahdrId");
		return jpql.toString();
	}
	
	@Override
	public String getQueryForCreateContract(){
		
		StringBuilder jpql =  new StringBuilder(" SELECT DISTINCT(bidder) FROM Bidder bidder ");
		jpql.append(" INNER JOIN FETCH bidder.partner bp ");
		jpql.append(" LEFT JOIN WinnerSelection ws ON ws.tahdr.tahdrId = bidder.tahdr.tahdrId ");
		jpql.append(" WHERE ws.tahdr.tahdrId = :tahdrId");
		return jpql.toString();
		
	}
	public String getItemListOfBidder(){
		StringBuilder jpql =  new StringBuilder(" Select ws from WinnerSelection ws ");
		jpql.append(" INNER JOIN FETCH ws.itemBid ib ");
		jpql.append(" INNER JOIN FETCH ib.tahdrMaterial ibtm ");
		jpql.append(" INNER JOIN FETCH ibtm.material ibtmm ");
		jpql.append(" INNER JOIN FETCH ib.bidder b ");
		jpql.append(" WHERE b.bidderId= :bidderId");
		return jpql.toString();
	}
	
}
