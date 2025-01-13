/**
 * @author Ankush
 */
package com.novelerp.eat.dao.impl;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import com.novelerp.core.dao.impl.AbstractJpaDAO;
import com.novelerp.eat.dao.BidderSectionDocDao;
import com.novelerp.eat.dto.BidderSectionDocDto;
import com.novelerp.eat.entity.BidderSectionDoc;

@Repository
public class BidderSectionDocDaoImpl extends AbstractJpaDAO<BidderSectionDoc, BidderSectionDocDto>
		implements BidderSectionDocDao {
	
	@PostConstruct
	void init(){
		setClazz(BidderSectionDoc.class, BidderSectionDocDto.class);
	}

	public String getBidderSecDocById(){
		StringBuilder jpql=new StringBuilder();
		jpql.append(" Select bsd from BidderSectionDoc bsd ");
		jpql.append(" LEFT JOIN FETCH bsd.attachment att ");
		jpql.append(" LEFT JOIN FETCH bsd.partner p ");
		jpql.append(" where bsd.bidderSectionDocId=:bidderSecDocId");
		jpql.append(" and bsd.partner.bPartnerId=:partnerId ");
		return jpql.toString();		
	}
	
	public String getTechnicalBidSectionDoc(){
		StringBuilder jpql= new StringBuilder();
		jpql.append(" Select tbsd from BidderSectionDoc tbsd ")
		.append(" LEFT JOIN FETCH tbsd.sectionDocument sd ")
		.append(" LEFT JOIN FETCH tbsd.attachment tbsa ")
		.append(" INNER JOIN FETCH tbsd.technicalBid tb ")
		.append(" INNER JOIN FETCH tb.itemBid ib ")
		.append(" INNER JOIN FETCH ib.tahdrMaterial tm ")
		.append(" INNER JOIN FETCH ib.bidder bdr ")
		.append(" INNER JOIN FETCH tbsd.partner bp ")
		.append(" WHERE tm.tahdrMaterialId=:tahdrMaterialId ")
		.append(" and bp.bPartnerId=:partnerId ");
		return jpql.toString();
	}
	
	public String getPriceBidSectionDoc(){
		StringBuilder jpql= new StringBuilder();
		jpql.append(" Select pbsd from BidderSectionDoc pbsd ")
		.append(" LEFT JOIN FETCH pbsd.sectionDocument sd ")
		.append(" LEFT JOIN FETCH pbsd.attachment tbsa ")
		.append(" INNER JOIN FETCH pbsd.priceBid pb ")
		.append(" INNER JOIN FETCH pb.itemBid ib ")
		.append(" INNER JOIN FETCH ib.tahdrMaterial tm ")
		.append(" INNER JOIN FETCH ib.bidder bdr ")
		.append(" INNER JOIN FETCH pbsd.partner bp ")
		.append(" WHERE tm.tahdrMaterialId=:tahdrMaterialId ")
		.append(" and bp.bPartnerId=:partnerId ");
		return jpql.toString();
	}
	
	public String getCommercialBidSectionDoc(){
		StringBuilder jpql= new StringBuilder();
		jpql.append(" Select cbsd from BidderSectionDoc cbsd ")
		.append(" INNER JOIN FETCH cbsd.sectionDocument sd ")
		.append(" INNER JOIN FETCH cbsd.attachment tbsa ")
		.append(" INNER JOIN FETCH cbsd.commercialBid cb ")
		.append(" INNER JOIN FETCH cb.bidder bdr ")
		.append(" INNER JOIN FETCH bdr.tahdr t ")
		.append(" INNER JOIN FETCH t.tahdrDetail td ")
		.append(" INNER JOIN FETCH cbsd.partner bp ")
		.append(" WHERE t.tahdrId=:tahdrId and bp.bPartnerId=:partnerId and td.isActive='Y' and td.tahdrDetailId=sd.tahdrDetail.tahdrDetailId ")
		.append(" AND cb.tahdrDetail.tahdrDetailId=td.tahdrDetailId ");
		return jpql.toString();
	}
}
