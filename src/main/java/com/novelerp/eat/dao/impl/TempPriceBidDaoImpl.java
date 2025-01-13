package com.novelerp.eat.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.core.dao.impl.AbstractJpaDAO;
import com.novelerp.core.dto.BidderParticipationDto;
import com.novelerp.eat.dao.TempPriceBidDao;
import com.novelerp.eat.dto.PriceBidDto;
import com.novelerp.eat.entity.TempPriceBid;

@Repository
public class TempPriceBidDaoImpl extends AbstractJpaDAO<TempPriceBid, PriceBidDto> implements TempPriceBidDao {
	
	@PostConstruct
	public void postConstruct() {
		setClazz(TempPriceBid.class, PriceBidDto.class);
	}
	
   public String getTempPriceBidListByTahdrMaterialId(){
		StringBuilder jpql= new StringBuilder();
		jpql.append(" Select b from TempPriceBid b ");
		jpql.append(" LEFT JOIN FETCH b.itemBid ib ");
		jpql.append(" LEFT JOIN FETCH ib.bidder bd ");
		jpql.append(" LEFT JOIN FETCH bd.factory fac ");
		jpql.append(" LEFT JOIN FETCH bd.partner p ");
		jpql.append(" LEFT JOIN FETCH bd.tahdr t ");
		jpql.append(" LEFT JOIN FETCH ib.tahdrMaterial tm ");
		jpql.append(" LEFT JOIN FETCH tm.material tmm ");
		jpql.append(" LEFT JOIN FETCH b.digiSignedDoc dstb ");
		jpql.append(" WHERE t.tahdrId=:tahdrId "
				   +" AND tm.tahdrMaterialId=:tahdrMaterialId "
				   +" AND bd.status='"+AppBaseConstant.BIDDER_STATUS_Price_BID_OPENED+"' AND b.digiSignedDoc IS NOT NULL  ORDER BY b.created DESC ");
		return jpql.toString();
	}
   public String getSelfTempPriceBidListByTahdrMaterialId(){
		StringBuilder jpql= new StringBuilder();
		jpql.append(" Select b from TempPriceBid b ");
		jpql.append(" LEFT JOIN FETCH b.itemBid ib ");
		jpql.append(" LEFT JOIN FETCH ib.bidder bd ");
		jpql.append(" LEFT JOIN FETCH bd.factory fac ");
		jpql.append(" LEFT JOIN FETCH bd.partner p ");
		jpql.append(" LEFT JOIN FETCH bd.tahdr t ");
		jpql.append(" LEFT JOIN FETCH ib.tahdrMaterial tm ");
		jpql.append(" LEFT JOIN FETCH tm.material tmm ");
		jpql.append(" LEFT JOIN FETCH b.digiSignedDoc dstb ");
		jpql.append(" WHERE t.tahdrId=:tahdrId "
				   +" AND tm.tahdrMaterialId=:tahdrMaterialId AND p.bPartnerId=:partnerId "
				   +" AND bd.status='"+AppBaseConstant.BIDDER_STATUS_Price_BID_OPENED+"' AND b.digiSignedDoc IS NOT NULL AND b.isPreviousBid<>'Y' AND b.bidRemark NOT IN ('CHA','AOP')");
		return jpql.toString();
	}
   
   public String getAllTempPriceBidListByTahdrMaterialId(){
		StringBuilder jpql= new StringBuilder();
		jpql.append(" Select b from TempPriceBid b ");
		jpql.append(" LEFT JOIN FETCH b.itemBid ib ");
		jpql.append(" LEFT JOIN FETCH ib.bidder bd ");
		jpql.append(" LEFT JOIN FETCH bd.factory fac ");
		jpql.append(" LEFT JOIN FETCH bd.partner p ");
		jpql.append(" LEFT JOIN FETCH bd.tahdr t ");
		jpql.append(" LEFT JOIN FETCH ib.tahdrMaterial tm ");
		jpql.append(" LEFT JOIN FETCH tm.material tmm ");
		jpql.append(" LEFT JOIN FETCH b.digiSignedDoc dstb ");
		jpql.append(" WHERE t.tahdrId=:tahdrId "
				   +" AND tm.tahdrMaterialId=:tahdrMaterialId AND p.bPartnerId=:partnerId "
				   +" AND bd.status='"+AppBaseConstant.BIDDER_STATUS_Price_BID_OPENED+"' AND b.digiSignedDoc IS NOT NULL ORDER BY b.created DESC  ");
		return jpql.toString();
	}
	
   public String getTempPriceBidByTahdrMaterialIdAndBidderId(){
		StringBuilder jpql=new StringBuilder();
		jpql.append(" Select tpb from TempPriceBid tpb ");
		jpql.append(" LEFT JOIN FETCH tpb.digiSignedDoc dsd ");
		jpql.append(" WHERE tpb.itemBid.bidder.bidderId=:bidderId "
				+ " AND tpb.itemBid.tahdrMaterial.tahdrMaterialId=:tahdrMaterialId AND tpb.itemBid.bidder.tahdr.tahdrStatusCode NOT IN ('PBSCH','TCOP','DBSCH','DBOP','VO') "
				+ " ORDER BY tpb.created DESC");
		return jpql.toString();
	}
   
   public String getTempPBHistoryByBidderId(){
		 StringBuilder jpql= new StringBuilder();
		 	jpql.append(" SELECT tpb FROM TempPriceBid tpb ")
			.append(" INNER JOIN FETCH tpb.itemBid ib ")
			.append(" INNER JOIN FETCH ib.bidder b ")
			.append(" INNER JOIN FETCH b.partner p ")
			.append(" INNER JOIN FETCH b.tahdr t ")
			.append(" INNER JOIN FETCH ib.tahdrMaterial tm")
			.append(" INNER JOIN FETCH tm.material tmm")
			.append(" LEFT JOIN FETCH tpb.digiSignedDoc doc ")
			.append(" LEFT JOIN FETCH b.factory fac ")
			.append(" WHERE tm.tahdrMaterialId=:tahdrMaterialId AND b.bidderId=:bidderId "
					+ " AND doc.attachmentId IS NOT NULL ");
			
			return jpql.toString();
	 }
   
   	public String getCompleteTempPBHistoryByTahdrMaterialId(){
		 StringBuilder jpql= new StringBuilder();
		 	jpql.append(" SELECT tpb FROM TempPriceBid tpb ")
			.append(" INNER JOIN FETCH tpb.itemBid ib ")
			.append(" INNER JOIN FETCH ib.bidder b ")
			.append(" INNER JOIN FETCH b.partner p ")
			.append(" INNER JOIN FETCH b.tahdr t ")
			.append(" INNER JOIN FETCH ib.tahdrMaterial tm")
			.append(" INNER JOIN FETCH tm.material tmm")
			.append(" LEFT JOIN FETCH tpb.digiSignedDoc doc ")
			.append(" LEFT JOIN FETCH b.factory fac ")
			.append(" WHERE tm.tahdrMaterialId=:tahdrMaterialId "
					+ " AND doc.attachmentId IS NOT NULL ");
			
			return jpql.toString();
	 }
	 
   public String getSelfTempQuickPriceBidListByTahdrMaterialId(){
		StringBuilder jpql= new StringBuilder();
		jpql.append(" Select b from TempPriceBid b ");
		jpql.append(" LEFT JOIN FETCH b.itemBid ib ");
		jpql.append(" LEFT JOIN FETCH ib.bidder bd ");
		jpql.append(" LEFT JOIN FETCH bd.factory fac ");
		jpql.append(" LEFT JOIN FETCH bd.partner p ");
		jpql.append(" LEFT JOIN FETCH bd.tahdr t ");
		jpql.append(" LEFT JOIN FETCH ib.tahdrMaterial tm ");
		jpql.append(" LEFT JOIN FETCH tm.material tmm ");
		jpql.append(" LEFT JOIN FETCH b.digiSignedDoc dstb ");
		jpql.append(" WHERE t.tahdrId=:tahdrId ");
		jpql.append(" AND tm.tahdrMaterialId=:tahdrMaterialId AND p.bPartnerId=:partnerId ORDER BY b.created DESC");
		return jpql.toString();
	}
   
   public String getSelfTempQuickPriceBidListByBidderIdAndMaterialId(){
		StringBuilder jpql= new StringBuilder();
		jpql.append(" Select b from TempPriceBid b ");
		jpql.append(" LEFT JOIN FETCH b.itemBid ib ");
		jpql.append(" LEFT JOIN FETCH ib.bidder bd ");
		jpql.append(" LEFT JOIN FETCH bd.factory fac ");
		jpql.append(" LEFT JOIN FETCH bd.partner p ");
		jpql.append(" LEFT JOIN FETCH bd.tahdr t ");
		jpql.append(" LEFT JOIN FETCH ib.tahdrMaterial tm ");
		jpql.append(" LEFT JOIN FETCH tm.material tmm ");
		jpql.append(" LEFT JOIN FETCH b.digiSignedDoc dstb ");
		jpql.append(" WHERE bd.bidderId=:bidderId ");
		jpql.append(" AND tm.tahdrMaterialId=:tahdrMaterialId ORDER BY b.created DESC");
		return jpql.toString();
	}
   
   public String getAllTempQuickPriceBidListByTahdrMaterialId(){
		StringBuilder jpql= new StringBuilder();
		jpql.append(" Select b from TempPriceBid b ");
		jpql.append(" LEFT JOIN FETCH b.itemBid ib ");
		jpql.append(" LEFT JOIN FETCH ib.bidder bd ");
		jpql.append(" LEFT JOIN FETCH bd.factory fac ");
		jpql.append(" LEFT JOIN FETCH bd.partner p ");
		jpql.append(" LEFT JOIN FETCH bd.tahdr t ");
		jpql.append(" LEFT JOIN FETCH ib.tahdrMaterial tm ");
		jpql.append(" LEFT JOIN FETCH tm.material tmm ");
		jpql.append(" LEFT JOIN FETCH b.digiSignedDoc dstb ");
		jpql.append(" WHERE t.tahdrId=:tahdrId ");
		jpql.append(" AND tm.tahdrMaterialId=:tahdrMaterialId ORDER BY b.created DESC");
		return jpql.toString();
	}
   public String getBidderParticipation(Long tahdrId, Long materialId){
   	StringBuilder jpql= new StringBuilder();
   	jpql.append(" SELECT  ");
	jpql.append(" bp.name as label,(count(tp.t_price_bid_id)+1) as y,bp.name as legendText ");
	jpql.append(" from t_temp_price_bid tp ");
	jpql.append(" INNER JOIN t_item_bid ib on ib.t_item_bid_id = tp.t_item_bid_id ");
	jpql.append(" INNER JOIN t_tahdr_material tm on tm.t_tahdr_material_id = ib.t_tahdr_material_id  ");
	jpql.append(" INNER JOIN t_bidder tb on tb.t_bidder_id = ib.t_bidder_id ");
	jpql.append(" INNER JOIN t_tahdr tt on tt.t_tahdr_id = tb.t_tahdr_id  ");
	jpql.append(" INNER JOIN m_bpartner bp on bp.m_bpartner_id =tb.m_bpartner_id  ");
	jpql.append(" where tt.t_tahdr_id =:tahdrId  and tm.t_tahdr_material_id=:tahdrMaterialId");
	jpql.append(" group by tb.t_bidder_id,bp.name ");
	
   	return jpql.toString();
   	}

@Override
public List<BidderParticipationDto> getBidderInvitation(Long tahdrId, Long materialId) {
	// TODO Auto-generated method stub
	return getBidderInterest(tahdrId,materialId);
}
@Transactional(propagation=Propagation.REQUIRED)
private List<BidderParticipationDto> getBidderInterest(Long tahdrId, Long materialId){
	String jpql=getBidderParticipation(tahdrId,materialId);
	Query query=getEntityManager().createNativeQuery(jpql.toString());
	query.setParameter("tahdrId", tahdrId);
	query.setParameter("tahdrMaterialId", materialId);
	List<Object[]> count= query.getResultList();
	List<BidderParticipationDto> bidderParticipateList = new ArrayList<>();
	for(Object[] o: count){
		BidderParticipationDto BidPart = new BidderParticipationDto();
		BidPart.setLabel(String.valueOf(o[0]));
		BidPart.setY(Long.parseLong(String.valueOf(o[1])));
		BidPart.setLegendText(String.valueOf(o[2]));
		bidderParticipateList.add(BidPart);
	}
	
	return bidderParticipateList;
}

    public String getBidListBySessionString(){
   	StringBuilder jpql= new StringBuilder();
   	jpql.append(" SELECT tpb FROM TempPriceBid tpb");
   	jpql.append(" INNER JOIN FETCH tpb.itemBid ib ");
   	jpql.append(" INNER JOIN FETCH ib.tahdrMaterial tm ");
	jpql.append(" INNER JOIN FETCH tm.material m ");
	jpql.append(" WHERE tpb.createdSessionId=:sessionString ");
   	return jpql.toString();
   	}


	
}
