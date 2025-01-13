/**
 * @author Ankush
 */
package com.novelerp.eat.dao.impl;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.core.dao.impl.AbstractJpaDAO;
import com.novelerp.eat.dao.CommercialBidDao;
import com.novelerp.eat.dto.CommercialBidDto;
import com.novelerp.eat.entity.CommercialBid;

@Repository
public class CommercialBidDaoImpl extends AbstractJpaDAO<CommercialBid, CommercialBidDto>
		implements CommercialBidDao {
	
	@PostConstruct
	void init(){
		setClazz(CommercialBid.class, CommercialBidDto.class);
	}
	
	public String getCommercialBidById(){
		StringBuilder jpql= new StringBuilder();
		jpql.append(" Select cb from CommercialBid cb ")
		.append(" LEFT JOIN FETCH cb.bidder b ")
		.append(" LEFT JOIN FETCH b.tahdr t ")
		.append(" LEFT JOIN FETCH t.tahdrDetail td ")
		.append(" LEFT JOIN FETCH cb.bidderSecDoc cdoc ")
		.append(" LEFT JOIN FETCH cdoc.sectionDocument sd ")
		.append(" LEFT JOIN FETCH sd.tahdrDetail td1 ")
		.append(" LEFT JOIN FETCH cb.digiSignedDoc dsd ")
		.append(" WHERE cb.commercialBidId=:commercialBidId AND (td.tahdrDetailId=td1.tahdrDetailId OR td1.tahdrDetailId IS NULL) AND td.isActive='Y' ")
		.append(" AND (sd.code='"+AppBaseConstant.COMMERCIAL_SECTION+"' OR sd.code is NULL) ");
		return jpql.toString();
	}
	
	public String getCommercialBidByBidderId(){
		StringBuilder jpql= new StringBuilder();
		jpql.append(" Select DISTINCT cb from CommercialBid cb ")
		.append(" LEFT JOIN FETCH cb.bidder b ")
		.append(" LEFT JOIN FETCH b.tahdr t ")
		.append(" LEFT JOIN FETCH t.tahdrDetail td ")
		.append(" LEFT JOIN FETCH cb.bidderSecDoc cdoc ")
		.append(" LEFT JOIN FETCH cdoc.sectionDocument sd ")
		.append(" LEFT JOIN FETCH sd.tahdrDetail td1 ")
		.append(" LEFT JOIN FETCH cb.digiSignedDoc dsd ")
		.append(" WHERE b.bidderId=:bidderId AND (td.tahdrDetailId=td1.tahdrDetailId OR td1.tahdrDetailId IS NULL) AND td.isActive='Y' ");
		return jpql.toString();
	}
	
	public String getCommercialBid(){
		StringBuilder jpql= new StringBuilder();
		jpql.append(" Select cb from CommercialBid cb ")
		.append(" LEFT JOIN FETCH cb.bidder bdr ")
		.append(" LEFT JOIN FETCH cb.tax tax ")
		.append(" LEFT JOIN FETCH cb.digiSignedDoc att ")
		.append(" LEFT JOIN FETCH bdr.tahdr t ")
		.append(" LEFT JOIN FETCH t.tahdrDetail td ")
		.append(" LEFT JOIN FETCH cb.tahdrDetail td1 ")
		.append(" WHERE t.tahdrId=:tahdrId ")
		.append(" and td.tahdrDetailId=td1.tahdrDetailId ")
		.append(" and td.isActive='Y' ")
		.append(" and bdr.partner.bPartnerId=:partnerId ");
		return jpql.toString();
	}
	
	public String getOpenedCommercialBid(){
		
		StringBuilder jpql= new StringBuilder();
		jpql.append(" Select cb from CommercialBid cb ")
		.append(" INNER JOIN FETCH cb.bidder bd ")
		.append(" INNER JOIN FETCH cb.partner p ")
		.append(" INNER JOIN FETCH cb.digiSignedDoc dscb ")
		.append(" INNER JOIN FETCH bd.tahdr t ")
		.append(" INNER JOIN FETCH t.tahdrDetail td ")
		.append(" where td.isActive='Y' and bd.bidderId=:bidderId "
				+ " AND bd.status=:bidderStatus ");
		return jpql.toString();
	}
	
   public String getCommercialBidByStatusAndTahdrId(){
	   StringBuilder jpql= new StringBuilder();
		jpql.append(" Select cb from CommercialBid cb ")
		.append(" INNER JOIN FETCH cb.bidder bd ")
		.append(" INNER JOIN FETCH bd.tahdr t ")
		.append(" INNER JOIN FETCH t.tahdrDetail td ")
		.append(" WHERE td.isActive='Y' and t.tahdrId=:tahdrId AND cb.status=:status AND cb.tahdrDetail.tahdrDetailId=td.tahdrDetailId");
		return jpql.toString();
	 }
}
