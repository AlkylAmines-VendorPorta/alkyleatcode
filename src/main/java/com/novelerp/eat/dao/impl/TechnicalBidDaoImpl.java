/**
 * @author Ankush
 */
package com.novelerp.eat.dao.impl;

import javax.annotation.PostConstruct;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.novelerp.core.dao.impl.AbstractJpaDAO;
import com.novelerp.eat.dao.TechnicalBidDao;
import com.novelerp.eat.dto.TechnicalBidDto;
import com.novelerp.eat.entity.TechnicalBid;

@Repository
public class TechnicalBidDaoImpl extends AbstractJpaDAO<TechnicalBid, TechnicalBidDto> implements TechnicalBidDao {

	@PostConstruct
	void init(){
		setClazz(TechnicalBid.class, TechnicalBidDto.class);
	}
	
	
	@Override
	public int updateTechnicalBidStatus(String status,Long itemBidId) {
		StringBuilder jpql= new StringBuilder();
		jpql.append(" Update TechnicalBid tb SET tb.status=:status ");
		jpql.append( " where tb.itemBid.itemBidId=:itemBidId");
		
		Query query=getEntityManager().createQuery(jpql.toString());
		query.setParameter("itemBidId", itemBidId);
		query.setParameter("status", status);
		int count= query.executeUpdate();
		return count;
	}
	
	public String getTechnicalBid(){
		StringBuilder jpql= new StringBuilder();
		jpql.append(" Select tb from TechnicalBid tb ")
		.append(" LEFT JOIN FETCH tb.digiSignedDoc dsd ")
		.append(" INNER JOIN FETCH tb.itemBid ib ")
		.append(" INNER JOIN FETCH ib.tahdrMaterial tm ")
		.append(" INNER JOIN FETCH ib.bidder bdr ")
		.append(" INNER JOIN FETCH tb.partner bp ")
		.append(" WHERE tm.tahdrMaterialId=:tahdrMaterialId ")
		.append(" and bp.bPartnerId=:partnerId ");
		return jpql.toString();
	}
	
	public String getTechnicalBidByItemBid(){
		StringBuilder jpql= new StringBuilder();
		jpql.append(" Select DISTINCT tb from TechnicalBid tb ")
		.append(" LEFT JOIN FETCH tb.itemBid ib ")
		.append(" LEFT JOIN FETCH tb.digiSignedDoc dsd ")
		.append(" LEFT JOIN FETCH tb.bidderSecDoc bsd ")
		.append(" LEFT JOIN FETCH tb.bidderGtp bg ")
		.append(" WHERE ib.itemBidId=:itemBidId ");
		return jpql.toString();
	}
	
	public String getDeviationTechnicalBidByBidderIdAndStatus(){
		StringBuilder jpql= new StringBuilder();
		jpql.append(" Select DISTINCT tb from TechnicalBid tb ")
		.append(" LEFT JOIN FETCH tb.itemBid b ")
		.append(" LEFT JOIN FETCH b.bidder bd ")
		.append(" LEFT JOIN FETCH tb.digiSignedDoc dsd ")
		.append(" LEFT JOIN FETCH b.tahdrMaterial tm ")
		.append(" LEFT JOIN FETCH tm.tahdrDetail td ")
		.append( " LEFT JOIN FETCH tm.materialVersion ms ")
		.append( " LEFT JOIN FETCH tm.material tmm ")
		.append( " LEFT JOIN FETCH tmm.hsnCode hsn ")
		.append( " LEFT JOIN FETCH tmm.uom tmmu ")
		.append( " where bd.bidderId=:bidderId AND td.isActive='Y' AND b.status IN( :status1, :status2) ");
		return jpql.toString();
	}
	
	public String getTechnicalBidByBidderIdAndStatus(){
		StringBuilder jpql= new StringBuilder();
		jpql.append(" Select DISTINCT tb from TechnicalBid tb ")
		.append(" LEFT JOIN FETCH tb.itemBid b ")
		.append(" LEFT JOIN FETCH b.bidder bd ")
		.append(" LEFT JOIN FETCH tb.digiSignedDoc dsd ")
		.append(" LEFT JOIN FETCH b.tahdrMaterial tm ")
		.append(" LEFT JOIN FETCH tm.tahdrDetail td ")
		.append( " LEFT JOIN FETCH tm.materialVersion ms ")
		.append( " LEFT JOIN FETCH tm.material tmm ")
		.append( " LEFT JOIN FETCH tmm.hsnCode hsn ")
		.append( " LEFT JOIN FETCH tmm.uom tmmu ")
		.append( " WHERE bd.bidderId=:bidderId AND td.isActive='Y' ")
		.append( " AND b.status IN( :status1, :status2,'PARTPASPRELIMINARY_COMMERCIAL_PASSEDS','PTP','PTF') ");
		/*.append( " where b.bidder.bidderId=:bidderId AND b.bidder.tahdr.tahdrDetail.isActive='Y' AND b.status IN( :status1, :status2) ");*/
		return jpql.toString();
	}
	
	public String getTechnicalBidByBidderIdAndTahdrMaterialId(){
		StringBuilder jpql= new StringBuilder();
		jpql.append(" Select tb from TechnicalBid tb ")
		.append(" LEFT JOIN FETCH tb.digiSignedDoc dsd ")
		.append( " WHERE tb.itemBid.bidder.bidderId=:bidderId AND tb.itemBid.tahdrMaterial.tahdrDetail.isActive='Y' AND tb.itemBid.tahdrMaterial.tahdrMaterialId=:tahdrMaterialId");
		return jpql.toString();
	}
	
	public String verifyTechnicalBid(){
		StringBuilder jpql= new StringBuilder();
		jpql.append(" Select tb from TechnicalBid tb ")
		.append(" LEFT JOIN FETCH tb.digiSignedDoc dsd ")
		.append(" LEFT JOIN FETCH tb.bidderSecDoc tbsd ")
		.append(" LEFT JOIN FETCH tb.bidderGtp bg ")
		.append(" INNER JOIN FETCH tb.itemBid ib ")
		.append(" INNER JOIN FETCH ib.tahdrMaterial tm ")
		.append(" INNER JOIN FETCH ib.bidder bdr ")
		.append(" INNER JOIN FETCH bdr.tahdr t ")
		.append(" INNER JOIN FETCH tb.partner bp ")
		.append(" WHERE ib.itemBidId=:itemBidId ")
		.append(" and bp.bPartnerId=:partnerId ");
		return jpql.toString();
	}
	
	public String getTCBidderListByTahdrId(){
		
		StringBuilder jpql= new StringBuilder();
		jpql.append(" Select b from TechnicalBid b ")
		.append(" LEFT JOIN FETCH b.itemBid ib ")
		.append(" LEFT JOIN FETCH ib.bidder bd ")
		.append(" LEFT JOIN FETCH bd.partner p ")
		.append(" LEFT JOIN FETCH bd.tahdr t ")
		.append(" LEFT JOIN FETCH ib.tahdrMaterial tm")
		.append(" LEFT JOIN FETCH tm.material tmm")
		.append(" LEFT JOIN FETCH b.digiSignedDoc dstb ")
		.append(" where t.tahdrId=:tahdrId AND tm.tahdrMaterialId=:tahdrMaterialId ");
		return jpql.toString();
		
		/*StringBuilder jpql= new StringBuilder();
		jpql.append(" Select bd from Bidder bd ")
		.append(" LEFT JOIN FETCH bd.partner p ")
		.append(" LEFT JOIN FETCH bd.tahdr t ")
		.append(" LEFT JOIN FETCH t.tahdrDetail td ")
		.append(" LEFT JOIN FETCH bd.itemBidList ib ")
		.append(" LEFT JOIN FETCH ib.tahdrMaterial tm ")
		.append(" LEFT JOIN FETCH tm.tahdrDetail td1 ")
		.append(" LEFT JOIN FETCH tm.material tmm ")
		.append(" where t.tahdrId=:tahdrId and (td.tahdrDetailId=td1.tahdrDetailId AND td.isActive='Y') "
				+" AND tm.tahdrMaterialId=:tahdrMaterialId "
				+ " AND bd.status='"+AppBaseConstant.BIDDER_STATUS_TECHNO_COMMERCIAL_OPENED+"'");
		return jpql.toString();*/
		
	}
	
	public String getOpenedTechnicalBid(){
		StringBuilder jpql= new StringBuilder();
		jpql.append(" Select tb from TechnicalBid tb ")
		.append(" LEFT JOIN FETCH tb.itemBid ib ")
		.append(" LEFT JOIN FETCH ib.bidder bd ")
		.append(" LEFT JOIN FETCH tb.partner p ")
		.append(" LEFT JOIN FETCH bd.tahdr t ")
		.append(" LEFT JOIN FETCH t.tahdrDetail td ")
		.append(" LEFT JOIN FETCH ib.tahdrMaterial tm")
		.append(" LEFT JOIN FETCH tm.material tmm")
		.append(" LEFT JOIN FETCH tm.tahdrDetail td1")
		.append(" LEFT JOIN FETCH tb.digiSignedDoc dstb ")
		.append(" WHERE bd.bidderId=:bidderId "
				+ " AND td.isActive='Y' "
				+ " AND td.tahdrDetailId=td1.tahdrDetailId "
				+ " AND tm.tahdrMaterialId=:tahdrMaterialId "
				+ " AND bd.status=:bidderStatus "
				+ " AND ib.status=:itemBidStatus ");
		return jpql.toString();
	}
	
/*public String getTCBidderListForSbByTahdrId(){
		
		StringBuilder jpql= new StringBuilder();
		jpql.append(" Select b from TechnicalBid b ");
		jpql.append(" LEFT JOIN FETCH b.itemBid ib ");
		jpql.append(" LEFT JOIN FETCH ib.bidder bd ");
		jpql.append(" LEFT JOIN FETCH bd.partner p ");
		jpql.append(" LEFT JOIN FETCH bd.commercialBid cb ");
		jpql.append(" LEFT JOIN FETCH cb.digiSignedDoc dscb ");
		jpql.append(" LEFT JOIN FETCH bd.tahdr t ");
		jpql.append(" LEFT JOIN FETCH ib.tahdrMaterial tm");
		jpql.append(" LEFT JOIN FETCH tm.material tmm");
		jpql.append(" LEFT JOIN FETCH b.digiSignedDoc dstb ");
		jpql.append(" where t.tahdrId=:tahdrId and (cb.tahdrDetail.isActive='Y' OR cb.commercialBidId is null) "
				+" AND tm.tahdrMaterialId=:tahdrMaterialId "
				+ " AND bd.status='"+AppBaseConstant.BIDDER_STATUS_Price_BID_OPENED+"'");
		return jpql.toString();
	}*/
	
	public String getTechnicalBidByStatusAndTahdrMaterialIdAndTahdrId(){
		StringBuilder jpql= new StringBuilder();
		jpql.append(" Select DISTINCT b from TechnicalBid b ");
		jpql.append(" LEFT JOIN FETCH b.itemBid ib ");
		jpql.append(" LEFT JOIN FETCH ib.tahdrMaterial tm ");
		jpql.append(" LEFT JOIN FETCH ib.bidder bd ");
		jpql.append(" where bd.tahdr.tahdrId=:tahdrId "
				+ " AND b.status=:status AND tm.tahdrMaterialId=:tahdrMaterialId");
		return jpql.toString();
	}
}
