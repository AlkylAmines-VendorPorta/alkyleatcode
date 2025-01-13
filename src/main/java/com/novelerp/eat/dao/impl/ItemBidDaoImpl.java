package com.novelerp.eat.dao.impl;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.core.dao.impl.AbstractJpaDAO;
import com.novelerp.eat.dao.ItemBidDao;
import com.novelerp.eat.dto.ItemBidDto;
import com.novelerp.eat.entity.ItemBid;

/**
 * @author Aman Sahu
 *
 */
@Repository
public class ItemBidDaoImpl extends AbstractJpaDAO<ItemBid, ItemBidDto> implements ItemBidDao {
	
	@PostConstruct
	public void postConstruct() {
		setClazz(ItemBid.class, ItemBidDto.class);
	}
	
	public String getItemBidsForBidder(){
		StringBuilder jpql= new StringBuilder();
		jpql.append(" Select ib from ItemBid ib ")
		.append(" INNER JOIN FETCH ib.tahdrMaterial tm ")
		.append(" INNER JOIN FETCH tm.material m ")
		.append(" INNER JOIN FETCH m.uom uom ")
		.append(" INNER JOIN FETCH m.hsnCode hsn ")
		.append(" INNER JOIN FETCH ib.bidder bdr ")
		.append(" INNER JOIN FETCH bdr.tahdr tahdr ")
		.append(" INNER JOIN FETCH tahdr.tahdrDetail td ")
		.append(" INNER JOIN tm.tahdrDetail tdm with tdm.isActive='Y' ")
		.append(" LEFT JOIN FETCH bdr.factory po ")
		.append(" WHERE td.tahdrDetailId=:tahdrDetailId ")
		.append(" and bdr.partner.bPartnerId=:partnerId ");
		return jpql.toString();
	}
	
/*public String getItemListByBidderId(){
		
		StringBuilder jpql= new StringBuilder();
		jpql.append(" Select distinct b from ItemBid b ");
		jpql.append(" LEFT JOIN FETCH b.tahdrMaterial tm ");
		jpql.append(" LEFT JOIN FETCH b.technicalBid tb ");
		jpql.append( " LEFT JOIN FETCH tb.bidderGtp tbg ");
		jpql.append(" LEFT JOIN FETCH tb.bidderSecDoc cbd ");
	    jpql.append( " LEFT JOIN FETCH tbg.tahdrMaterialgtp tmg ");
		jpql.append( " LEFT JOIN FETCH tmg.gtp tgtp ");
		jpql.append( " LEFT JOIN FETCH tgtp.gtpParameterType tgtpt ");
		jpql.append( " LEFT JOIN FETCH tm.material tmm ");
		jpql.append( " LEFT JOIN FETCH tmm.hsnCode hsn ");
		jpql.append( " LEFT JOIN FETCH tmm.uom tmmu ");
		jpql.append( " where b.bidder.bidderId=:bidderId ");
		return jpql.toString();
	}*/
public String getItemListByBidderId(){
	StringBuilder jpql= new StringBuilder();
	jpql.append(" Select distinct b from ItemBid b ");
	jpql.append( " where b.bidder.bidderId=:bidderId ");
	return jpql.toString();
}

public String getOpenedItemListByBidderId(){
	StringBuilder jpql= new StringBuilder();
	jpql.append(" SELECT ib from ItemBid ib ");
	jpql.append(" LEFT JOIN FETCH ib.bidder b ");
	jpql.append(" WHERE b.bidderId=:bidderId AND ib.status=:itemStatus AND b.status=:bidderStatus");
	return jpql.toString();
}

public String getItemListByBidderIdAndStatus(){
	
	StringBuilder jpql= new StringBuilder();
	jpql.append(" Select distinct b from ItemBid b ");
	jpql.append(" LEFT JOIN FETCH b.tahdrMaterial tm ");
	jpql.append(" LEFT JOIN FETCH tm.tahdrDetail td ");/*
	jpql.append(" INNER JOIN FETCH b.technicalBid tb ");
	jpql.append( " LEFT JOIN FETCH tb.bidderGtp tbg ");
	jpql.append(" LEFT JOIN FETCH tb.bidderSecDoc cbd ");
    jpql.append( " LEFT JOIN FETCH tbg.tahdrMaterialgtp tmg ");
	jpql.append( " LEFT JOIN FETCH tmg.gtp tgtp ");
	jpql.append( " LEFT JOIN FETCH tgtp.gtpParameterType tgtpt ");*/
	jpql.append( " LEFT JOIN FETCH tm.materialVersion ms ");
	jpql.append( " LEFT JOIN FETCH tm.material tmm ");
	jpql.append( " LEFT JOIN FETCH tmm.hsnCode hsn ");
	jpql.append( " LEFT JOIN FETCH tmm.uom tmmu ");
	jpql.append( " WHERE b.bidder.bidderId=:bidderId AND td.isActive='Y' AND b.status IN( :status1, :status2,'FAIL') ");
	return jpql.toString();
}
public String getItemListByBidderIdAndOneStatus(){
	
	StringBuilder jpql= new StringBuilder();
	jpql.append(" Select distinct b from ItemBid b ");
	jpql.append(" LEFT JOIN FETCH b.tahdrMaterial tm ");/*
	jpql.append(" INNER JOIN FETCH b.technicalBid tb ");
	jpql.append( " LEFT JOIN FETCH tb.bidderGtp tbg ");
	jpql.append(" LEFT JOIN FETCH tb.bidderSecDoc cbd ");
    jpql.append( " LEFT JOIN FETCH tbg.tahdrMaterialgtp tmg ");
	jpql.append( " LEFT JOIN FETCH tmg.gtp tgtp ");
	jpql.append( " LEFT JOIN FETCH tgtp.gtpParameterType tgtpt ");*/
	jpql.append( " LEFT JOIN FETCH tm.material tmm ");
	jpql.append( " LEFT JOIN FETCH tmm.hsnCode hsn ");
	jpql.append( " LEFT JOIN FETCH tmm.uom tmmu ");
	jpql.append( " where b.bidder.bidderId=:bidderId AND b.status=:status ");
	return jpql.toString();
}

public String getItemListByLiveAuction(){
	
	StringBuilder jpql= new StringBuilder();
	jpql.append(" Select distinct b from ItemBid b ");
	jpql.append(" LEFT JOIN FETCH b.bidder bd ");
	jpql.append(" LEFT JOIN FETCH bd.tahdr t ");
	jpql.append(" LEFT JOIN FETCH b.tahdrMaterial tm ");
	jpql.append( " LEFT JOIN FETCH tm.material tmm ");
	jpql.append( " LEFT JOIN FETCH tmm.hsnCode hsn ");
	jpql.append( " LEFT JOIN FETCH tmm.uom tmmu ");
	jpql.append( " where t.tahdrId=:tahdrId AND bd.partner.bPartnerId=:partnerId AND b.status='"+AppBaseConstant.BIDDER_STATUS_Price_BID_OPENED+"' ");
	return jpql.toString();
}

	public String getBidderGtpByItemBid(){
		StringBuilder jpql= new StringBuilder();
		jpql.append(" SELECT e from ItemBid e ")
		.append(" LEFT JOIN FETCH e.technicalBid tb ")
		.append(" LEFT JOIN FETCH e.bidder eb ")
		.append(" LEFT JOIN FETCH tb.bidderGtp bg  ")
		.append(" LEFT JOIN FETCH tb.bidderSecDoc tbd ")
		.append(" WHERE e.itemBidId=:itemBidId");
		return jpql.toString();
	}
	
	public String getTechnicalBid(){
		StringBuilder jpql= new StringBuilder();
		jpql.append(" Select ib from ItemBid ib ")
		.append(" LEFT JOIN FETCH ib.tahdrMaterial tm ")
		.append(" LEFT JOIN FETCH ib.technicalBid tb ")
		.append(" WHERE ib.itemBidId=:itemBidId ")
		.append(" and tb.technicalBidId=:technicalBidId ");
		return jpql.toString();
	}
	
	public String getPriceBid(){
		StringBuilder jpql= new StringBuilder();
		jpql.append(" Select ib from ItemBid ib ")
		.append(" LEFT JOIN FETCH ib.tahdrMaterial tm ")
		.append(" LEFT JOIN FETCH ib.technicalBid tb ")
		.append(" LEFT JOIN FETCH ib.priceBid pb ")
		.append(" WHERE ib.itemBidId=:itemBidId ")
		.append(" and pb.priceBidId=:priceBidId ");
		return jpql.toString();
	}
	
	public String getItemBid(){
		StringBuilder jpql= new StringBuilder();
		jpql.append(" Select ib from ItemBid ib ")
		.append(" LEFT JOIN FETCH ib.technicalBid tb ")
		.append(" LEFT JOIN FETCH ib.priceBid pb ")
		.append(" WHERE ib.itemBidId=:itemBidId ");
		return jpql.toString();
	}
	
	public String getItemBidWithBidder(){
		StringBuilder jpql= new StringBuilder();
		jpql.append(" Select ib from ItemBid ib ")
		.append(" LEFT JOIN FETCH ib.bidder tb ")
		.append(" LEFT JOIN FETCH ib.tahdrMaterial tm ")
		.append(" LEFT JOIN FETCH tm.material tmm ")
		.append(" WHERE ib.itemBidId=:itemBidId ");
		return jpql.toString();
	}
	
	@Override
	public int updateItemBidStatusByOpeningType(String status,String checkStatus,Long tahdrId){
		StringBuilder jpql= new StringBuilder();
		jpql.append(" Update ItemBid ib SET ib.status=:status ");
		jpql.append(" WHERE ib.itemBidId IN (SELECT c.itemBidId FROM ItemBid c WHERE c.tahdrMaterial.tahdrDetail.isActive='Y' AND c.tahdrMaterial.tahdrDetail.tahdr.tahdrId=:tahdrId) AND ib.status=:checkStatus And ib.bidder.bidderId IN (SELECT b.bidderId FROM Bidder b ");
		jpql.append(" WHERE b.tahdr.tahdrId=:tahdrId AND b.status=:checkStatus ) ");
		Query query=getEntityManager().createQuery(jpql.toString());
		query.setParameter("tahdrId", tahdrId);
		query.setParameter("status", status);
		query.setParameter("checkStatus", checkStatus);
		int count= query.executeUpdate();
		return count;
	}
	
	@Override
	public int updateItemBidStatusByPBOpeningTypeForTwo(String status,String checkStatus,Long tahdrId){
		StringBuilder jpql= new StringBuilder();
		jpql.append(" Update ItemBid ib SET ib.status=:status ");
		jpql.append(" WHERE ib.itemBidId IN (SELECT c.itemBidId FROM ItemBid c WHERE c.tahdrMaterial.tahdrDetail.isActive='Y' AND c.tahdrMaterial.tahdrDetail.tahdr.tahdrId=:tahdrId) AND ib.status IN (:checkStatus1, :checkStatus2) And ib.bidder.bidderId IN (SELECT b.bidderId FROM Bidder b");
		jpql.append(" where b.tahdr.tahdrId=:tahdrId And b.status IN ( :oldStatus1, :oldStatus2, :oldStatus3, :oldStatus4, :oldStatus5, :oldStatus6, :oldStatus7 ) )");
		Query query=getEntityManager().createQuery(jpql.toString());
		query.setParameter("tahdrId", tahdrId);
		query.setParameter("status", status);
		query.setParameter("checkStatus1", AppBaseConstant.PRELIMINARY_TECHNICAL_PASSED);
		query.setParameter("checkStatus2", AppBaseConstant.SCRUTINY_PASSED);
		query.setParameter("oldStatus1", AppBaseConstant.PRELIMINARY_TECHNICAL_PASSED);
		query.setParameter("oldStatus6", AppBaseConstant.SCRUTINY_PARTIAL_PASSED);
		query.setParameter("oldStatus2", AppBaseConstant.SCRUTINY_PASSED);
		query.setParameter("oldStatus3", AppBaseConstant.FINAL_COMMERCIAL_PASSED);
		query.setParameter("oldStatus5", AppBaseConstant.FINAL_TECHNICAL_PASSED);
		query.setParameter("oldStatus4", AppBaseConstant.BIDDER_STATUS_BID_SUBMITED);
		query.setParameter("oldStatus7", AppBaseConstant.PRELIMINARY_COMMERCIAL_PASSED);
		int count= query.executeUpdate();
		return count;
	}
	@Override
	public int updateItemBidStatusByPBOpeningTypeForSingle(String status,String checkStatus,Long tahdrId){
		StringBuilder jpql= new StringBuilder();
		jpql.append(" Update ItemBid ib SET ib.status=:status ");
		jpql.append(" WHERE ib.itemBidId IN (SELECT c.itemBidId FROM ItemBid c WHERE c.tahdrMaterial.tahdrDetail.isActive='Y' AND c.tahdrMaterial.tahdrDetail.tahdr.tahdrId=:tahdrId) AND ib.status=:checkStatus And ib.bidder.bidderId IN (SELECT b.bidderId FROM Bidder b");
		jpql.append(" WHERE b.tahdr.tahdrId=:tahdrId And b.status IN ( :oldStatus1, :oldStatus2, :oldStatus3, :oldStatus4) )");
		Query query=getEntityManager().createQuery(jpql.toString());
		query.setParameter("tahdrId", tahdrId);
		query.setParameter("status", status);
		query.setParameter("checkStatus", AppBaseConstant.BIDDER_STATUS_BID_SUBMITED);
		query.setParameter("oldStatus1", AppBaseConstant.PRELIMINARY_TECHNICAL_PASSED);
		query.setParameter("oldStatus2", AppBaseConstant.SCRUTINY_PASSED);
		query.setParameter("oldStatus3", AppBaseConstant.FINAL_COMMERCIAL_PASSED);
		query.setParameter("oldStatus4", AppBaseConstant.BIDDER_STATUS_BID_SUBMITED);
		int count= query.executeUpdate();
		return count;
	}
	@Override
	public int updateItemBidStatus(String status,Long itemBidId) {
		StringBuilder jpql= new StringBuilder();
		jpql.append(" Update ItemBid ib SET ib.status=:status ");
		jpql.append( " where ib.itemBidId=:itemBidId");
		
		Query query=getEntityManager().createQuery(jpql.toString());
		query.setParameter("itemBidId", itemBidId);
		query.setParameter("status", status);
		int count= query.executeUpdate();
		return count;
	}
	/*public String getItemBidByL1Bid(){
		StringBuilder jpql= new StringBuilder();
		jpql.append(" Select ib from ItemBid ib ");
		jpql.append(" LEFT JOIN FETCH ib.priceBid pb ");
		jpql.append(" WHERE ib.bidder.tahdr.tahdrId=:tahdrId And ib.priceBid.exGroupPriceRate IN (SELECT Min(pb.exGroupPriceRate) from ");
		jpql.append(" PriceBid pb)");
		return jpql.toString();
	}	*/
	
	
	@Override
	public String getItemBidByBidSubmitted(){
		StringBuilder jpql= new StringBuilder();
		jpql.append(" Select ib from ItemBid ib ");
		jpql.append(" LEFT JOIN FETCH ib.tahdrMaterial tm ");
		jpql.append(" LEFT JOIN FETCH tm.tahdrDetail td ");
		jpql.append(" WHERE ib.bidder.tahdr.tahdrId=:tahdrId AND td.isActive='Y' AND ib.status=:status And ib.bidder.bidderId IN (SELECT b.bidderId FROM Bidder b");
		jpql.append(" WHERE b.tahdr.tahdrId=:tahdrId And b.status=:status) ");
		return jpql.toString();
	}
	
	public String getItemBidForPBOpening(){
		StringBuilder jpql= new StringBuilder();
		jpql.append(" Select ib from ItemBid ib ");
		jpql.append(" LEFT JOIN FETCH ib.tahdrMaterial tm ");
		jpql.append(" LEFT JOIN FETCH tm.tahdrDetail td ");
		jpql.append(" WHERE ib.bidder.tahdr.tahdrId=:tahdrId AND td.isActive='Y' And ib.bidder.status IN ('SBMT','TCOP','FCBP','COMMPASS','PARTPASPRELIMINARY_COMMERCIAL_PASSEDS',"
				+ "'FTBP','PTP','PASS') ");
		return jpql.toString();
	}
	@Override
	@Transactional(propagation=Propagation.SUPPORTS)
	public int updateItemBidStatusForMarkingLowestBid(Long tahdrId){
		StringBuilder jpql= new StringBuilder();
		jpql.append("UPDATE t_item_bid SET is_lowest_bid=(CASE  WHEN t_item_bid_id ");
		jpql.append("	    IN (SELECT itemBidId ");
		jpql.append("	        FROM (SELECT ib1.t_item_bid_id as itemBidId, ");
		jpql.append("	              dense_rank() over (partition by ib1.t_tahdr_material_id order by   CAST (pb1.fdd_rate_with_gst AS INTEGER) nulls last) dr1 from t_price_bid pb1 ");
		jpql.append("		          INNER JOIN t_item_bid ib1 ON ib1.t_item_bid_id=pb1.t_item_bid_id AND ib1.status='PBOP' ");
		jpql.append("	              INNER JOIN t_tahdr_material tm1 ON tm1.t_tahdr_material_id=ib1.t_tahdr_material_id ");
		jpql.append("	              INNER JOIN t_bidder b ON b.t_bidder_id=ib1.t_bidder_id ");
		jpql.append("	              WHERE b.t_tahdr_id=:tahdrId AND pb1.M_MATERIAL_SPECIFICATION_ID IS NULL AND pb1.DIGITALLY_SIGNED_DOC_ID IS NOT NULL ) dr WHERE dr.dr1=1) THEN 'Y' ");
	  	jpql.append("                 ELSE 'N' END ) WHERE t_bidder_id IN (SELECT t_bidder_id FROM t_bidder bd WHERE bd.t_tahdr_id=:tahdrId) ");
		Query query=getEntityManager().createNativeQuery(jpql.toString());
		query.setParameter("tahdrId", tahdrId);
		int count= query.executeUpdate();
		return count;
	}
	
	@Override
	@Transactional(propagation=Propagation.SUPPORTS)
	public int updateQuickItemBidStatusForMarkingLowestBid(Long tahdrId){
		StringBuilder jpql= new StringBuilder();
		jpql.append("UPDATE t_item_bid SET is_lowest_bid=(CASE  WHEN t_item_bid_id ");
		jpql.append("	    IN (SELECT itemBidId ");
		jpql.append("	        FROM (SELECT ib1.t_item_bid_id as itemBidId, ");
		jpql.append("	              dense_rank() over (partition by ib1.t_tahdr_material_id order by CAST (pb1.fdd_rate_with_gst AS INTEGER) nulls last) dr1 from t_price_bid pb1 ");
		jpql.append("		          INNER JOIN t_item_bid ib1 ON ib1.t_item_bid_id=pb1.t_item_bid_id ");
		jpql.append("	              INNER JOIN t_tahdr_material tm1 ON tm1.t_tahdr_material_id=ib1.t_tahdr_material_id ");
		jpql.append("	              INNER JOIN t_bidder b ON b.t_bidder_id=ib1.t_bidder_id ");
		jpql.append("	              WHERE b.t_tahdr_id=:tahdrId  ) dr WHERE dr.dr1=1) THEN 'Y' ");
	    jpql.append("                 ELSE 'N' END ) WHERE t_bidder_id IN (SELECT t_bidder_id FROM t_bidder bd WHERE bd.t_tahdr_id=:tahdrId) ");
		Query query=getEntityManager().createNativeQuery(jpql.toString());
		query.setParameter("tahdrId", tahdrId);
		int count= query.executeUpdate();
		return count;
	}
	
	@Override
	@Transactional(propagation=Propagation.SUPPORTS)
	public int updateItemBidStatusForMarkingHighestBid(Long tahdrId){
		StringBuilder jpql= new StringBuilder();
		jpql.append("UPDATE t_item_bid SET is_lowest_bid=(CASE  WHEN t_item_bid_id ");
		jpql.append("	    IN (SELECT itemBidId ");
		jpql.append("	        FROM (SELECT ib1.t_item_bid_id as itemBidId, ");
		jpql.append("	              dense_rank() over (partition by ib1.t_tahdr_material_id order by CAST (pb1.fdd_rate_with_gst AS INTEGER) desc nulls last) dr1 from t_price_bid pb1 ");
		jpql.append("		          INNER JOIN t_item_bid ib1 ON ib1.t_item_bid_id=pb1.t_item_bid_id AND ib1.status='PBOP' ");
		jpql.append("	              INNER JOIN t_tahdr_material tm1 ON tm1.t_tahdr_material_id=ib1.t_tahdr_material_id ");
		jpql.append("	              INNER JOIN t_bidder b ON b.t_bidder_id=ib1.t_bidder_id ");
		jpql.append("	              WHERE b.t_tahdr_id=:tahdrId  AND pb1.M_MATERIAL_SPECIFICATION_ID IS NULL AND pb1.DIGITALLY_SIGNED_DOC_ID IS NOT NULL) dr WHERE dr.dr1=1) THEN 'Y' ");
	    jpql.append("                 ELSE 'N' END ) WHERE t_bidder_id IN (SELECT t_bidder_id FROM t_bidder bd WHERE bd.t_tahdr_id=:tahdrId) ");
		Query query=getEntityManager().createNativeQuery(jpql.toString());
		query.setParameter("tahdrId", tahdrId);
		int count= query.executeUpdate();
		return count;
	}
	
	@Override
	@Transactional(propagation=Propagation.SUPPORTS)
	public int updateQuickItemBidStatusForMarkingHighestBid(Long tahdrId){
		StringBuilder jpql= new StringBuilder();
		jpql.append("UPDATE t_item_bid SET is_lowest_bid=(CASE  WHEN t_item_bid_id ");
		jpql.append("	    IN (SELECT itemBidId ");
		jpql.append("	        FROM (SELECT ib1.t_item_bid_id as itemBidId, ");
		jpql.append("	              dense_rank() over (partition by ib1.t_tahdr_material_id order by CAST (pb1.fdd_rate_with_gst AS INTEGER) desc nulls last) dr1 from t_price_bid pb1 ");
		jpql.append("		          INNER JOIN t_item_bid ib1 ON ib1.t_item_bid_id=pb1.t_item_bid_id ");
		jpql.append("	              INNER JOIN t_tahdr_material tm1 ON tm1.t_tahdr_material_id=ib1.t_tahdr_material_id ");
		jpql.append("	              INNER JOIN t_bidder b ON b.t_bidder_id=ib1.t_bidder_id ");
		jpql.append("	              WHERE b.t_tahdr_id=:tahdrId  ) dr WHERE dr.dr1=1) THEN 'Y' ");
	    jpql.append("                 ELSE 'N' END ) WHERE t_bidder_id IN (SELECT t_bidder_id FROM t_bidder bd WHERE bd.t_tahdr_id=:tahdrId) ");
		Query query=getEntityManager().createNativeQuery(jpql.toString());
		query.setParameter("tahdrId", tahdrId);
		int count= query.executeUpdate();
		return count;
	}
	public int updateItemBidStatusForMarkingLowestBid(Long tahdrId,Long tahdrMaterialIld){
		StringBuilder jpql= new StringBuilder();
		jpql.append("UPDATE t_item_bid SET is_lowest_bid=(CASE  WHEN t_item_bid_id ");
		jpql.append("	    IN (SELECT itemBidId ");
		jpql.append("	        FROM (SELECT ib1.t_item_bid_id as itemBidId, pb1.fdd_amount_with_gst, ");
		jpql.append("	              dense_rank() over (partition by ib1.t_tahdr_material_id order by CAST (pb1.fdd_rate_with_gst AS INTEGER) nulls last) dr1 from t_price_bid pb1 ");
		jpql.append("		          INNER JOIN t_item_bid ib1 ON ib1.t_item_bid_id=pb1.t_item_bid_id AND ib1.status='PBOP' ");
		jpql.append("	              INNER JOIN t_tahdr_material tm1 ON tm1.t_tahdr_material_id=ib1.t_tahdr_material_id ");
		jpql.append("	              INNER JOIN t_bidder b ON b.t_bidder_id=ib1.t_bidder_id ");
		jpql.append("	              WHERE b.t_tahdr_id=:tahdrId AND tm1.tahdrMaterialId= :tahdrMaterialId  AND ib1.status='PBOP') dr WHERE dr.dr1=1) THEN 'Y' ");
	    jpql.append("                 ELSE 'N' END ) WHERE t_bidder_id IN (SELECT t_bidder_id FROM t_bidder bd WHERE bd.t_tahdr_id=:tahdrId) ");
		Query query=getEntityManager().createNativeQuery(jpql.toString());
		query.setParameter("tahdrId", tahdrId);
		int count= query.executeUpdate();
		return count;
	}
	
	@Override
	public int updateItemBidStatusForMarkingAnnexureC1Called(Long tahdrId, int baseRate,String status){
		StringBuilder jpql= new StringBuilder();
		jpql.append(" UPDATE t_item_bid  SET status=:status  WHERE t_item_bid_id ");
		jpql.append("      IN (SELECT    ");
		jpql.append("          pb2.t_item_bid_id  ");
		jpql.append("          FROM t_price_bid pb2   ");
		jpql.append("          INNER JOIN t_item_bid ib2 ON ib2.t_item_bid_id=pb2.t_item_bid_id AND ib2.status='PBOP' ");
        jpql.append("          INNER JOIN t_bidder b ON b.t_bidder_id = ib2.t_bidder_id  ");
        jpql.append("          INNER JOIN (SELECT   ");
        jpql.append("          (CAST (pb1.fdd_rate_with_gst AS INTEGER)*(100+ :baseRate))/100 AS BASERATE , ");
        jpql.append("            ib1.t_tahdr_material_id AS MATERIAL,pb1.t_price_bid_id ");
        jpql.append("          FROM  t_price_bid pb1   ");
        jpql.append("     INNER JOIN t_item_bid ib1 ON ib1.t_item_bid_id = pb1.t_item_bid_id  ");
        jpql.append("     INNER JOIN t_tahdr_material tm1 ON tm1.t_tahdr_material_id = ib1.t_tahdr_material_id ");
        jpql.append("     INNER JOIN t_bidder b ON b.t_bidder_id = ib1.t_bidder_id  ");
        jpql.append("     WHERE b.t_tahdr_id =:tahdrId AND ib1.is_lowest_bid='Y') D ON D.MATERIAL=ib2.t_tahdr_material_id  ");
        jpql.append("     WHERE CAST (pb2.fdd_rate_with_gst AS INTEGER) <= D.BASERATE AND  b.t_tahdr_id =:tahdrId AND ib2.is_lowest_bid<>'Y' ) ");
		Query query=getEntityManager().createNativeQuery(jpql.toString());
		query.setParameter("tahdrId", tahdrId);
		query.setParameter("baseRate", baseRate);
		query.setParameter("status", status);
		int count= query.executeUpdate();
		return count;
	}
	
	 public String getTahdrDataFromItemBidId(){
			StringBuilder jpql=new StringBuilder();
			jpql.append(" Select ib from ItemBid ib ");
			jpql.append(" LEFT JOIN FETCH ib.bidder b ");
			jpql.append(" LEFT JOIN FETCH b.tahdr t ");
			jpql.append(" LEFT JOIN FETCH t.tahdrDetail td ");
			jpql.append(" LEFT JOIN FETCH td.tahdrMaterial tm ");
			jpql.append(" LEFT JOIN FETCH tm.materialGtpList tmg ");
			jpql.append(" where ib.itemBidId=:itemBidId ");
			return jpql.toString();
		}
	 
	 @Override
		@Transactional(propagation=Propagation.SUPPORTS)
		public int updateQuickItemBidForWinner(Long tahdrId){
			StringBuilder jpql= new StringBuilder();
			jpql.append(" Update ItemBid SET status='"+AppBaseConstant.BIDDER_STATUS_QUICK_WINNER+"'");
			jpql.append(" where itemBidId= (SELECT ib.itemBidId FROM ItemBid ib WHERE ib.bidder.tahdr.tahdrId=:tahdrId AND ib.isLowestBid='Y')");
			Query query=getEntityManager().createQuery(jpql.toString());
			query.setParameter("tahdrId", tahdrId);
			int count= query.executeUpdate();
			return count;
		}

	 
	 public String getItemByBidId(){
			StringBuilder jpql= new StringBuilder();
			jpql.append(" Select DISTINCT(A) from ItemBid A ")
			.append(" INNER JOIN FETCH A.prLine B ");
			jpql.append( " WHERE A.bidder.bidderId=:bidderId ");
			jpql.append( " ORDER BY A.prLine.prLineNumber ASC");
			return jpql.toString();
		}
	 

	 public String getItemBidByBidderId(){
			StringBuilder jpql=new StringBuilder();
			jpql.append(" Select a from ItemBid a ");
			jpql.append(" INNER JOIN FETCH a.bidder b ");
			jpql.append(" INNER JOIN FETCH b.partner c ");
			jpql.append(" INNER JOIN FETCH a.prLine d ");
			jpql.append(" INNER JOIN FETCH d.pr pr ");
			jpql.append(" WHERE b.bidderId=:bidderId ");
			return jpql.toString();
		}
	 
	 public String getprAttByBidderId() {
			StringBuilder jpql=new StringBuilder();
			jpql.append(" Select a from ItemBid a ");
			jpql.append(" INNER JOIN FETCH a.bidder b ");
			jpql.append(" INNER JOIN FETCH b.partner c ");
			jpql.append(" INNER JOIN FETCH a.prLine d ");
			jpql.append(" INNER JOIN FETCH d.pr pr ");
			jpql.append(" INNER JOIN FETCH pr.prAttSet prAtt ");			
			jpql.append(" WHERE b.bidderId=:bidderId ");
			return jpql.toString();
	 }
	 
	 
	 public String getItemBidByeqnId(){
			StringBuilder jpql=new StringBuilder();
			jpql.append(" Select DISTINCT(a) from ItemBid a ");
			jpql.append(" INNER JOIN FETCH a.bidder b ");
			jpql.append(" INNER JOIN FETCH b.enquiry C ");
			jpql.append(" INNER JOIN FETCH a.prLine d ");
			jpql.append(" WHERE b.bidderId=:bidderId ");
			return jpql.toString();
		}
	 
	 
	 public String getprLineByitemId(){
			StringBuilder jpql=new StringBuilder();
			jpql.append(" Select DISTINCT(a) from ItemBid a ");
			jpql.append(" INNER JOIN FETCH a.prLine d ");
			jpql.append(" WHERE a.itemBidId=:itemBidId ");
			return jpql.toString();
		}
	 
	 

	/*@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public int updateLowestBid(Long prId) {
		StringBuilder jpql= new StringBuilder(" update t_item_bid set is_lowest_bid=(CASE WHEN t_item_bid_id in (select itemid from ( ")
		.append(" select b.t_item_bid_id as itemid ,MIN(a.ex_group_price_rate) from t_price_bid a ")
		.append(" inner join t_item_bid b on(b.t_item_bid_id=a.t_item_bid_id) ")
		.append(" inner join t_pr_line c on(c.t_pr_line_id=b.t_pr_line_id) ")
		.append(" inner join t_pr z on(z.t_pr_id=c.t_pr_id) ")
		.append(" where z.t_pr_id=:prId ")
		.append(" group by c.t_pr_line_id, b.t_item_bid_id)as subq) THEN 'Y' ELSE 'N' END) ");
		Query query=getEntityManager().createNativeQuery(jpql.toString());
		query.setParameter("prId", prId);
		int count= query.executeUpdate();
		return count;
	}*/
	 @Override
		@Transactional(propagation=Propagation.REQUIRED)
		public int updateLowestBid(Long prId) {
			StringBuilder jpql= new StringBuilder(" update t_item_bid set is_lowest_bid=(CASE WHEN t_item_bid_id in (select itemid from ( ")
			.append(" select b.t_item_bid_id as itemid ,MIN(a.ex_group_price_rate) as ex_group_price_rate  from t_price_bid a ")
			.append(" inner join t_item_bid b on(b.t_item_bid_id=a.t_item_bid_id) ")
			.append(" inner join t_pr_line c on(c.t_pr_line_id=b.t_pr_line_id) ")
			.append(" inner join t_bidder y on(y.t_bidder_id=b.t_bidder_id) ")
			.append(" inner join t_enquiry z on(z.t_enquiry_id=y.t_enquiry_id) ")
			.append(" where z.t_enquiry_id=:prId ")
			.append(" group by c.t_pr_line_id, b.t_item_bid_id) as subq) THEN 'Y' ELSE 'N' END) ");
			Query query=getEntityManager().createNativeQuery(jpql.toString());
			query.setParameter("prId", prId);
			int count= query.executeUpdate();
			return count;
		}
	 public String getItemBidByPRLine(){
			StringBuilder jpql=new StringBuilder();
			jpql.append(" Select a from ItemBid a ");
			jpql.append(" INNER JOIN FETCH a.bidder b ");
			jpql.append(" INNER JOIN FETCH a.prLine c ");
			jpql.append(" INNER JOIN FETCH b.enquiry d ");
			jpql.append(" INNER JOIN FETCH b.partner e ");
			jpql.append(" WHERE a.prLine.prLineId=:prLineId ");
			return jpql.toString();
		}
	 
	 
	 public String getItemBidByPRLineID(){
			StringBuilder jpql=new StringBuilder();
			jpql.append(" Select a from ItemBid a ");
			jpql.append(" INNER JOIN FETCH a.bidder b ");
			jpql.append(" INNER JOIN FETCH a.prLine c ");
			jpql.append(" INNER JOIN FETCH c.pr g ");
			jpql.append(" INNER JOIN FETCH b.enquiry d ");
			jpql.append(" INNER JOIN FETCH b.partner e ");
			jpql.append(" WHERE a.prLine.prLineId=:prLineId ");
			return jpql.toString();
		}
	 
	 public String getItemBidDetailsByenqid(){
		 StringBuilder jpql=new StringBuilder();
			jpql.append(" Select DISTINCT(a) from ItemBid a ");
			jpql.append(" INNER JOIN FETCH a.bidder b ");
			jpql.append(" INNER JOIN FETCH b.enquiry c ");
			jpql.append(" INNER JOIN FETCH a.prLine d ");
			jpql.append(" INNER JOIN FETCH d.pr e ");
			jpql.append(" INNER JOIN FETCH b.partner f ");
			jpql.append(" INNER JOIN FETCH c.createdBy E ");
			jpql.append(" WHERE c.enquiryId=:enquiryId ");
			return jpql.toString();
		}
	 
	   public List<ItemBidDto> getprNumber(Long enquiryId){
			StringBuilder query = new StringBuilder()
					.append("select Distinct(pr.pr_number) as pr_number from t_item_bid as u left join t_bidder as b on u.t_bidder_id = b.t_bidder_id left join t_enquiry as l on b.t_enquiry_id=l.t_enquiry_id left join t_pr_line as ca on ca.t_pr_line_id=u.t_pr_line_id left join t_pr as pr on pr.t_pr_id=ca.t_pr_id ")
			        .append(" WHERE (l.t_enquiry_id = '"+enquiryId+"')");
			         Query query1 = getEntityManager().createNativeQuery(query.toString());
			         List<ItemBidDto> resultSet = query1.getResultList();
                  return resultSet;
			
			}
	   
	   public List<ItemBidDto> getprPlant(Long enquiryId){
				StringBuilder query = new StringBuilder()
						.append("select Distinct(ca.plantDESC) as plant from t_item_bid as u left join t_bidder as b on u.t_bidder_id = b.t_bidder_id left join t_enquiry as l on b.t_enquiry_id=l.t_enquiry_id left join t_pr_line as ca on ca.t_pr_line_id=u.t_pr_line_id left join t_pr as pr on pr.t_pr_id=ca.t_pr_id ")
				        .append(" WHERE (l.t_enquiry_id = '"+enquiryId+"')");
				         Query query1 = getEntityManager().createNativeQuery(query.toString());
				         List<ItemBidDto> resultSet = query1.getResultList();
	                  return resultSet;
				
				}
}
