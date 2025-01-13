/**
 * @author Ankush
 */
package com.novelerp.eat.dao.impl;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.novelerp.alkyl.dto.BidderFilterDto;
import com.novelerp.alkyl.dto.POReadDto;
import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.core.dao.impl.AbstractJpaDAO;
import com.novelerp.eat.dao.BidderDao;
import com.novelerp.eat.dto.BidderDto;
import com.novelerp.eat.dto.TenderCommitteeDto;
import com.novelerp.eat.entity.Bidder;

@Repository
public class BidderDaoImpl extends AbstractJpaDAO<Bidder, BidderDto> implements BidderDao {
	
	@PostConstruct
	public void postConstruct() {
		setClazz(Bidder.class, BidderDto.class);
	}
	
	@PersistenceContext
	private EntityManager em;
	
	public String getBidderList(){
		
		StringBuilder jpql= new StringBuilder();
		jpql.append(" Select b from Bidder b ");
		jpql.append(" LEFT JOIN FETCH b.tenderPurchase tp ");
		jpql.append(" LEFT JOIN FETCH b.tahdr t ");
		jpql.append(" LEFT JOIN FETCH t.tahdrDetail td ");
		jpql.append(" LEFT JOIN FETCH b.partner bp ");
		return jpql.toString();
	}

    public String getBidder(){
		StringBuilder jpql= new StringBuilder();
		jpql.append(" Select b from Bidder b ");
		jpql.append(" LEFT JOIN FETCH b.partner bp ");
		jpql.append(" where b.bidderId=:bidderId ");
		return jpql.toString();
	}
    
    public String getTAHDRByBidderId(){
		StringBuilder jpql= new StringBuilder();
		jpql.append(" Select b from Bidder b ");
		jpql.append(" LEFT JOIN FETCH b.tahdr t ");
		jpql.append(" WHERE b.bidderId=:bidderId ");
		return jpql.toString();
	}
    
   public String getBidderByTahdrIdAndStatus(){
		
		StringBuilder jpql= new StringBuilder();
		jpql.append(" Select b from Bidder b ");
		jpql.append(" LEFT JOIN FETCH b.tahdr t ");
		jpql.append(" where t.tahdrId=:tahdrId AND b.status IN ('PASS','DVTN','PARTPASPRELIMINARY_COMMERCIAL_PASSEDS','COMMFAIL','PTP','TCOP','COMMPASS') ");
		return jpql.toString();
	}
	/*public String getBidderByTahdrDetailId(){
		StringBuilder jpql= new StringBuilder();
		jpql.append(" Select b from Bidder b ");
		jpql.append(" LEFT JOIN FETCH b.tenderPurchase tp ");
		jpql.append(" LEFT JOIN FETCH b.tenderDetail td ");
		jpql.append(" LEFT JOIN FETCH td.tahdr t ");
		jpql.append(" LEFT JOIN FETCH b.partner bp ");
		jpql.append(" where b.tenderDetail.tahdrDetailId=:tahdrDetailId ");
		jpql.append("  and b.partner.bPartnerId=:partnerId ");
		return jpql.toString();
	}*/
	
	public String getBidderByTahdrId(){
		StringBuilder jpql= new StringBuilder();
		jpql.append(" Select b from Bidder b ");
		jpql.append(" LEFT JOIN FETCH b.tenderPurchase tp ");
		jpql.append(" LEFT JOIN FETCH b.tahdr t ");
		jpql.append(" LEFT JOIN FETCH t.tahdrDetail td ");
		jpql.append(" LEFT JOIN FETCH b.partner bp ");
		jpql.append(" where t.tahdrId=:tahdrId and td.isActive='Y' ");
		jpql.append("  and b.partner.bPartnerId=:partnerId ");
		return jpql.toString();
	}
	
	public String getSimpleBidderWithPaymentByTahdrId(){
		StringBuilder jpql= new StringBuilder();
		jpql.append(" Select b from Bidder b ");
		jpql.append(" LEFT JOIN FETCH b.tenderPurchase tp ");
		jpql.append(" LEFT JOIN FETCH b.partner p ");
		jpql.append(" WHERE b.tahdr.tahdrId=:tahdrId  ");
		jpql.append("  AND b.partner.bPartnerId=:partnerId ");
		return jpql.toString();
	}
	
	public String getBidderListOnlyByTahdrId(){
		StringBuilder jpql= new StringBuilder();
		jpql.append(" Select b from Bidder b ");
		jpql.append(" LEFT JOIN FETCH b.tahdr t ");
		jpql.append(" INNER JOIN FETCH t.tahdrDetail td ");
		jpql.append(" WHERE t.tahdrId=:tahdrId and td.isActive='Y' ");
		return jpql.toString();
	}
	
	public String getBiddersForScrutiny(){
		StringBuilder jpql= new StringBuilder();
		jpql.append(" Select b from Bidder b ");
		jpql.append(" INNER JOIN FETCH b.tenderPurchase tp ");
		jpql.append(" INNER JOIN FETCH b.tahdr t ");
		jpql.append(" INNER JOIN FETCH t.tahdrDetail td ");
		jpql.append(" INNER JOIN FETCH b.partner bp ");
		jpql.append(" LEFT JOIN FETCH bp.partnerCoSignCopy digi ");
		jpql.append(" where td.tahdrDetailId=:tahdrDetailId "
				+ " AND td.isActive='Y' AND b.status IN ('DVTN','PTP','TCOP','PARTPASPRELIMINARY_COMMERCIAL_PASSEDS','COMMFAIL','COMMPASS','PTF')");
		return jpql.toString();
	}
	
	public String getBiddersForAuditing(){
		StringBuilder jpql= new StringBuilder();
		jpql.append(" Select b from Bidder b ");
		jpql.append(" INNER JOIN FETCH b.tenderPurchase tp ");
		jpql.append(" INNER JOIN FETCH b.tahdr t ");
		jpql.append(" INNER JOIN FETCH t.tahdrDetail td ");
		jpql.append(" INNER JOIN FETCH b.partner bp ");
		jpql.append(" LEFT JOIN FETCH bp.partnerCoSignCopy digi ");
		/*jpql.append(" where b.tenderDetail.tahdrDetailId=:tahdrDetailId ");*/
		jpql.append(" where td.tahdrDetailId=:tahdrDetailId  AND td.isActive='Y' AND b.status IN ('DVTN','PTP','TCOP','PARTPASPRELIMINARY_COMMERCIAL_PASSEDS','COMMFAIL','COMMPASS','PTF') "); 
		return jpql.toString();
	}
	
	public String getBidderDetailForScrutiny(){
		StringBuilder jpql= new StringBuilder();
		jpql.append(" Select b from Bidder b ");
		jpql.append(" LEFT JOIN FETCH b.tenderPurchase tp ");
		jpql.append(" LEFT JOIN FETCH b.tahdr t ");
		jpql.append(" INNER JOIN FETCH t.tahdrDetail td ");
		jpql.append(" LEFT JOIN FETCH b.partner bp ");
		jpql.append(" where td.tahdrDetailId=:tahdrDetailId AND td.isActive='Y' AND b.partner.bPartnerId=:partnerId AND b.status NOT IN ('COMMFAIL')");
		return jpql.toString();
	}
	
	public String getBidderDetailListForScrutiny(){
		StringBuilder jpql= new StringBuilder();
		jpql.append(" Select b from Bidder b ");
		jpql.append(" LEFT JOIN FETCH b.tenderPurchase tp ");
		jpql.append(" LEFT JOIN FETCH b.tahdr t ");
		jpql.append(" INNER JOIN FETCH t.tahdrDetail td ");
		jpql.append(" LEFT JOIN FETCH b.partner bp ");
		jpql.append(" where td.tahdrDetailId=:tahdrDetailId AND td.isActive='Y' AND b.status='DVTN' ");
		return jpql.toString();
	}
	
	public String getScrutinisedBidderList(){
		StringBuilder jpql= new StringBuilder();
		jpql.append(" Select b from Bidder b ");
		jpql.append(" LEFT JOIN FETCH b.tenderPurchase tp ");
		jpql.append(" LEFT JOIN FETCH b.tahdr t ");
		jpql.append(" INNER JOIN FETCH t.tahdrDetail td ");
		jpql.append(" LEFT JOIN FETCH b.partner bp ");
		jpql.append(" WHERE td.tahdrDetailId=:tahdrDetailId AND td.isActive='Y' AND b.status='TCOP' ");
		return jpql.toString();
	}
	
	public String getBidderListByStatus(){
		StringBuilder jpql= new StringBuilder();
		jpql.append(" Select b from Bidder b ");
		jpql.append(" LEFT JOIN FETCH b.tenderPurchase tp ");
		jpql.append(" LEFT JOIN FETCH b.tahdr t ");
		jpql.append(" INNER JOIN FETCH t.tahdrDetail td ");
		jpql.append(" LEFT JOIN FETCH b.partner bp ");
		jpql.append(" WHERE td.tahdrDetailId=:tahdrDetailId AND td.isActive='Y' AND  b.status IN ('DBOP','FTBP','FTBF','FCBP','FCBF') ");
		return jpql.toString();
	}
	
	public String getBidderListByItemBidStatus(){
		StringBuilder jpql= new StringBuilder();
		jpql.append(" Select b from Bidder b ");
		jpql.append(" INNER JOIN FETCH b.itemBidList ib ");
		jpql.append(" LEFT JOIN FETCH b.tenderPurchase tp ");
		jpql.append(" LEFT JOIN FETCH b.tahdr t ");
		jpql.append(" INNER JOIN FETCH t.tahdrDetail td ");
		jpql.append(" LEFT JOIN FETCH b.partner bp ");
		jpql.append(" WHERE td.tahdrDetailId=:tahdrDetailId AND td.isActive='Y' AND ib.status=:status AND b.status IS NOT NULL ");
		return jpql.toString();
	}
	
public String getCommercialBid(){
		StringBuilder jpql= new StringBuilder();
		/*jpql.append(" Select bdr from Bidder bdr ")
		.append(" LEFT JOIN FETCH bdr.commercialBid cb ")*/
		jpql.append(" Select cb from CommercialBid cb ")
		.append(" LEFT JOIN FETCH cb.bidder bdr ")
		.append(" LEFT JOIN FETCH cb.tax tax ")
		.append(" LEFT JOIN FETCH cb.bidderSecDoc cbsd ")
		.append(" LEFT JOIN FETCH cbsd.sectionDocument csd ")
		.append(" LEFT JOIN FETCH cbsd.attachment csa ")
		.append(" LEFT JOIN FETCH bdr.tahdr t ")
		.append(" LEFT JOIN FETCH t.tahdrDetail td ")
		.append(" WHERE t.tahdrId=:tahdrId ")
		.append(" and td.isActive='Y' and (csd.tahdrDetail.tahdrDetailId=td.tahdrDetailId OR cb.tahdrDetail.tahdrDetailId=td.tahdrDetailId) ")
		.append(" and bdr.partner.bPartnerId=:partnerId ");
		return jpql.toString();
	}
	
	public String getCommercialDocByBidderId(){
		StringBuilder jpql= new StringBuilder();
		jpql.append(" Select bdr from Bidder bdr ")
		.append(" LEFT JOIN FETCH bdr.partner p ")
		.append(" LEFT JOIN FETCH bdr.tahdr t ")
		.append(" LEFT JOIN FETCH t.tahdrDetail td ")
		.append(" WHERE bdr.bidderId=:bidderId AND td.isActive='Y' ");
		return jpql.toString();
	}
	
	public String getPurchasedTenderDetail(){
		
		String jpql=" Select Distinct b from Bidder b "
				+ " LEFT JOIN FETCH b.tenderDetail td "
				+ " LEFT JOIN FETCH td.tahdr t "
				+ " LEFT JOIN FETCH td.tahdrMaterial tm "
				+ " LEFT JOIN FETCH tm.material m "
				+ " LEFT JOIN FETCH m.uom uom "
				+ " LEFT JOIN FETCH tm.materialGtpList tmg "
				+ " LEFT JOIN FETCH b.partner bp ";
		return jpql;
	}
	
	
	public String getPurchasedTenderDetailBytahdrDetail(){
		String jpql=getPurchasedTenderDetail();
		jpql+= " where b.tenderDetail.tahdrDetailId=:tahdrDetailId "
				+ " and b.partner.bPartnerId=:partnerId ";
		return jpql;
	}
	
	
	public String getPurchasedTenderDetailByTypeCode(){
		String jpql=" Select Distinct b from Bidder b "
				+ " LEFT JOIN FETCH b.tahdr t "
				+ " LEFT JOIN FETCH t.tahdrDetail td "
				+ " LEFT JOIN FETCH td.tenderDoc doc "
				+ " LEFT JOIN FETCH td.tahdrMaterial tm "
				+ " LEFT JOIN FETCH tm.materialVersion mv "
				+ " LEFT JOIN FETCH mv.material refMv "
				+ " LEFT JOIN FETCH tm.material m "
				+ " LEFT JOIN FETCH m.uom uom "
				+ " LEFT JOIN FETCH b.partner bp "
				+ " LEFT JOIN FETCH b.tenderPurchase tp"
				+ " where b.partner.bPartnerId=:partnerId "
				+ " and tp.isFAApproved='Y' "
				+ " and t.tahdrTypeCode=:tahdrTypeCode and td.isActive='Y' and t.tahdrStatusCode IN ('"+AppBaseConstant.DOCUMENT_STATUS_PUBLISHED+"','"+AppBaseConstant.TENDER_REVISED_BID_SCHEDULING+"') "
				+ " and (now() BETWEEN td.technicalBidFromDate AND td.technicalBidToDate OR now()<td.revisedBidToDate )"
				+ " ORDER BY td.technicalBidToDate DESC ";
		return jpql;
	}
	
	public String getRfqByTypeCode(){
		String jpql=" Select Distinct b from Bidder b "
				+ " LEFT JOIN FETCH b.tahdr t "
				+ " LEFT JOIN FETCH t.tahdrDetail td "
				+ " LEFT JOIN FETCH td.tenderDoc doc "
				+ " LEFT JOIN FETCH td.tahdrMaterial tm "
				+ " LEFT JOIN FETCH tm.materialVersion mv "
				+ " LEFT JOIN FETCH mv.material refMv "
				+ " LEFT JOIN FETCH tm.material m "
				+ " LEFT JOIN FETCH m.uom uom "
				+ " INNER JOIN FETCH t.auctionParticipant p"
				+ " LEFT JOIN FETCH b.partner bp "
				+ " where b.partner.bPartnerId=:partnerId "
				+ " and t.tahdrTypeCode=:tahdrTypeCode and td.isActive='Y' and t.tahdrStatusCode IN ('"+AppBaseConstant.DOCUMENT_STATUS_PUBLISHED+"','"+AppBaseConstant.TENDER_REVISED_BID_SCHEDULING+"') "
				+ " and (now() BETWEEN td.technicalBidFromDate AND td.technicalBidToDate OR now()<td.revisedBidToDate )"
				+ " ORDER BY td.technicalBidToDate DESC ";
		return jpql;
	}
	
	public String getPurchasedTenderDetailByTahdrId(){
		String jpql=" Select Distinct b from Bidder b "
				+ " LEFT JOIN FETCH b.tahdr t "
				+ " LEFT JOIN FETCH t.tahdrDetail td "
				+ " LEFT JOIN FETCH td.tahdrMaterial tm "
				+ " LEFT JOIN FETCH tm.materialVersion mv "
				+ " LEFT JOIN FETCH mv.material refMv "
				+ " LEFT JOIN FETCH tm.material m "
				+ " LEFT JOIN FETCH m.uom uom "
				+ " LEFT JOIN FETCH b.partner bp "
				+ " LEFT JOIN FETCH b.tenderPurchase tp"
				+ " where b.partner.bPartnerId=:partnerId AND t.tahdrId=:tahdrId"
				+ " and tp.isFAApproved='Y' "
				+ " and td.isActive='Y' and t.tahdrStatusCode IN ('"+AppBaseConstant.DOCUMENT_STATUS_PUBLISHED+"','"+AppBaseConstant.TENDER_REVISED_BID_SCHEDULING+"') "
				+ " and (now() BETWEEN td.technicalBidFromDate AND td.technicalBidToDate OR now()<=td.revisedBidToDate ) "
				+ " ORDER BY td.technicalBidToDate DESC ";
		return jpql;
	}
	
	public String getBidderDeviationTenderDetailByTypeCode(){
		String jpql=" Select Distinct b from Bidder b "
				+ " LEFT JOIN FETCH b.tahdr t "
				+ " LEFT JOIN FETCH t.tahdrDetail td "
				+ " LEFT JOIN FETCH td.tahdrMaterial tm "
				+ " LEFT JOIN FETCH tm.materialVersion mv "
				+ " LEFT JOIN FETCH mv.material refMv "
				+ " LEFT JOIN FETCH tm.material m "
				+ " LEFT JOIN FETCH m.uom uom "
				+ " LEFT JOIN FETCH b.partner bp "
				+ " where b.partner.bPartnerId=:partnerId  "
				+ " AND t.tahdrTypeCode=:tahdrTypeCode AND t.tahdrStatusCode=:status"
				+ " ORDER BY b.tenderDetail.technicalBidToDate DESC ";
		return jpql;
	}
	
	public String getBidderForScrutiny(){
		StringBuilder jpql= new StringBuilder();
		jpql.append(" SELECT b from Bidder b ")
		.append(" LEFT JOIN FETCH b.createdBy cb")
		.append(" LEFT JOIN FETCH b.updatedBy ub")
		.append(" LEFT JOIN FETCH b.partner p")
		.append(" LEFT JOIN FETCH b.tenderDetail td")
		.append(" LEFT JOIN FETCH b.tenderPurchase tp ")
		.append(" LEFT JOIN FETCH b.emdPayment ep")
		.append(" LEFT JOIN FETCH b.user u")
		.append(" LEFT JOIN FETCH b.bidderStatusCode bsc")
		.append(" WHERE b.bidderId=:bidderId");
		return jpql.toString();
	}
	
	
	@Override
	public int updatBidderStatus(String status,Long bidderId,String clauseStatus) {
		StringBuilder jpql= new StringBuilder();
		jpql.append(" Update Bidder b SET b.status=:status ");
		jpql.append( " WHERE b.bidderId=:bidderId ");
		if(clauseStatus!=null){
			if(clauseStatus.equals(AppBaseConstant.PRELIMINARY_TECHNICAL_PASSED)){
				jpql.append( " AND NOT EXISTS (SELECT ib FROM ItemBid ib WHERE ib.status='DVTN' AND ib.bidder.bidderId=:bidderId) ");
				jpql.append( " AND NOT EXISTS (SELECT isl FROM ItemScrutinyLine isl WHERE isl.isDeviation IN ('Y',null) AND isl.itemScrutiny.bidder.bidderId=:bidderId"
						+ " AND isl.itemScrutiny.scrutinyType='COMMSCR') ");
			}
		}
		Query query=getEntityManager().createQuery(jpql.toString());
		query.setParameter("bidderId", bidderId);
		query.setParameter("status", status);
		int count= query.executeUpdate();
		return count;
	}
	
	@Override
	public int updateStatusWithItemBidStatus(String status,String itemBidStatus,Long bidderId) {
		StringBuilder jpql= new StringBuilder();
		jpql.append(" Update Bidder b SET b.status=:status ");
		jpql.append( " where b.bidderId=:bidderId AND EXISTS (SELECT ib FROM ItemBid ib WHERE "
				+ " ib.bidder.bidderId=:bidderId AND ib.status=:itemBidStatus)");
		
		Query query=getEntityManager().createQuery(jpql.toString());
		query.setParameter("bidderId", bidderId);
		query.setParameter("status", status);
		query.setParameter("itemBidStatus", itemBidStatus);
		int count= query.executeUpdate();
		return count;
	}
	
	@Override
	public int updateBidderStatusWithSelectedStatus(String status,String oldStatus,Long bidderId) {
		StringBuilder jpql= new StringBuilder();
		jpql.append(" Update Bidder b SET b.status=:status ");
		jpql.append( " WHERE b.status=:oldStatus AND b.bidderId=:bidderId");
		
		Query query=getEntityManager().createQuery(jpql.toString());
		query.setParameter("bidderId", bidderId);
		query.setParameter("status", status);
		query.setParameter("oldStatus", oldStatus);
		int count= query.executeUpdate();
		return count;
	}
	
	@Override
	public int updateBidderStatusForNoCommercialDeviation(String status,Long bidderId) {
		StringBuilder jpql= new StringBuilder();
		jpql.append(" Update Bidder b SET b.status=:status ");
		jpql.append(" WHERE b.bidderId=:bidderId AND NOT EXISTS (SELECT il FROM ItemScrutinyLine il WHERE il.itemScrutiny.bidder.bidderId=:bidderId "
				+ " AND il.itemScrutiny.scrutinyType='COMMSCR' AND il.isDeviation='Y')");
		
		Query query=getEntityManager().createQuery(jpql.toString());
		query.setParameter("bidderId", bidderId);
		query.setParameter("status", status);
		int count= query.executeUpdate();
		return count;
	}
	
	public String getAnnexureC1Tenders(){
		StringBuilder jpql=new StringBuilder();
		jpql.append(" Select DISTINCT b from Bidder b ");
		jpql.append(" LEFT JOIN FETCH b.tahdr t ");
		jpql.append(" LEFT JOIN FETCH t.tahdrDetail td ");
		jpql.append(" LEFT JOIN b.itemBidList ib ");
		jpql.append(" where b.partner.bPartnerId=:partnerId ");
		jpql.append(" and t.tahdrTypeCode=:tahdrTypeCode AND t.tahdrStatusCode='"+AppBaseConstant.TENDER_ANNEXURE_C1_SCHEDULING+"'");
		jpql.append(" and td.isAnnexureC1='Y' and td.isActive='Y' ");
		jpql.append(" and ib.status IN ('C1SU','ANC1')");
		return jpql.toString();
	}
	
	public String getAuctionAnnexureC1Tenders(){
		StringBuilder jpql=new StringBuilder();
		jpql.append(" Select DISTINCT b from Bidder b ");
		jpql.append(" LEFT JOIN FETCH b.tahdr t ");
		jpql.append(" LEFT JOIN FETCH t.tahdrDetail td ");
		jpql.append(" LEFT JOIN b.itemBidList ib ");
		jpql.append(" where b.partner.bPartnerId=:partnerId ");
		jpql.append(" and t.tahdrTypeCode=:tahdrTypeCode AND t.tahdrStatusCode='"+AppBaseConstant.TENDER_ANNEXURE_C1_SCHEDULING+"'");
		jpql.append(" and td.isAnnexureC1='Y' and td.isActive='Y' AND t.isAuction='Y' ");
		jpql.append(" and ib.status IN ('C1SU','ANC1')");
		return jpql.toString();
	}

	/*public String getBidsForAnnexureC1(){
		StringBuilder jpql=new StringBuilder();
		jpql.append(" Select b from Bidder b ");
		jpql.append(" LEFT JOIN FETCH b.tenderDetail td ");
		jpql.append(" LEFT JOIN FETCH td.tahdr t ");
		jpql.append(" LEFT JOIN FETCH b.itemBidList ib ");
		jpql.append(" LEFT JOIN FETCH ib.tahdrMaterial tm ");
		jpql.append(" LEFT JOIN FETCH tm.material m ");
		jpql.append(" LEFT JOIN FETCH m.uom uom ");
		jpql.append(" LEFT JOIN FETCH ib.priceBid pb ");
		jpql.append(" where td.tahdrDetailId=:tahdrDetailId ");
		jpql.append(" and b.partner.bPartnerId=:partnerId ");
		jpql.append(" and td.isAnnexureC1='Y' ");
		jpql.append(" and ib.status='"+AppBaseConstant.BIDDER_STATUS_ANNEXURE_C1_CALLED+"'");
		return jpql.toString();
	}*/
	
	
	
	@Override
	public int updateBidderStatusByOpeningType(String status,String oldStatus,Long tahdrId) {
		StringBuilder jpql= new StringBuilder();
		jpql.append(" Update Bidder b SET b.status=:status ");
		jpql.append( " where b.tahdr.tahdrId=:tahdrId And b.status=:oldStatus");
		
		Query query=getEntityManager().createQuery(jpql.toString());
		query.setParameter("tahdrId", tahdrId);
		query.setParameter("status", status);
		query.setParameter("oldStatus", oldStatus);
		int count= query.executeUpdate();
		return count;
	}
	
	@Override
	public int updateBidderStatusByPBOpeningType(String status,String oldStatus,Long tahdrId) {
		StringBuilder jpql= new StringBuilder();
		jpql.append(" UPDATE Bidder b SET b.status=:status ");
		jpql.append( " WHERE b.tahdr.tahdrId=:tahdrId And b.status IN ( :oldStatus1, :oldStatus2, :oldStatus3, :oldStatus4,:oldStatus5,:oldStatus6) AND b.bidderId IN (SELECT ib.bidder.bidderId FROM "
				+ " ItemBid ib WHERE ib.status='PBOP')");
		
		Query query=getEntityManager().createQuery(jpql.toString());
		query.setParameter("tahdrId", tahdrId);
		query.setParameter("status", status);
		query.setParameter("oldStatus1", AppBaseConstant.SCRUTINY_PARTIAL_PASSED);
		query.setParameter("oldStatus2", AppBaseConstant.FINAL_TECHNICAL_PASSED);
		query.setParameter("oldStatus3", AppBaseConstant.FINAL_COMMERCIAL_PASSED);
		/*query.setParameter("oldStatus4", AppBaseConstant.BIDDER_STATUS_DEVIATION_CALLED);*/
		query.setParameter("oldStatus4", AppBaseConstant.SCRUTINY_PASSED);
		query.setParameter("oldStatus5", AppBaseConstant.BIDDER_STATUS_BID_SUBMITED);
		query.setParameter("oldStatus6", AppBaseConstant.PRELIMINARY_COMMERCIAL_PASSED);
		int count= query.executeUpdate();
		return count;
	}
	
	@Override
	public int updateBidderStatusBySBPBOpeningType(String status,String oldStatus,Long tahdrId) {
		StringBuilder jpql= new StringBuilder();
		jpql.append(" UPDATE Bidder b SET b.status=:status ");
		jpql.append(" WHERE b.tahdr.tahdrId=:tahdrId And b.status IN ( :oldStatus1) AND b.bidderId IN (SELECT ib.bidder.bidderId FROM "
				+ " ItemBid ib WHERE ib.status=:status)");
		
		Query query=getEntityManager().createQuery(jpql.toString());
		query.setParameter("tahdrId", tahdrId);
		query.setParameter("status", status);
		query.setParameter("oldStatus1", AppBaseConstant.BIDDER_STATUS_BID_SUBMITED);
		/*query.setParameter("oldStatus2", AppBaseConstant.FINAL_TECHNICAL_PASSED);
		query.setParameter("oldStatus3", AppBaseConstant.FINAL_COMMERCIAL_PASSED);
		query.setParameter("oldStatus4", AppBaseConstant.BIDDER_STATUS_DEVIATION_CALLED);
		query.setParameter("oldStatus4", AppBaseConstant.SCRUTINY_PASSED);
		query.setParameter("oldStatus5", AppBaseConstant.BIDDER_STATUS_BID_SUBMITED);
		query.setParameter("oldStatus6", AppBaseConstant.PRELIMINARY_COMMERCIAL_PASSED);*/
		int count= query.executeUpdate();
		return count;
	}
	
	public String getBidderListByTahdrId(){
		StringBuilder jpql= new StringBuilder();
		jpql.append(" Select b from Bidder b ");
		jpql.append(" LEFT JOIN FETCH b.tenderPurchase tp ");
		jpql.append(" LEFT JOIN FETCH b.tenderDetail td ");
		jpql.append(" LEFT JOIN FETCH td.tahdr t ");
		jpql.append(" LEFT JOIN FETCH b.partner bp ");
		jpql.append(" where b.tahdr.tahdrId=:tahdrId ");
		return jpql.toString();
	}
	/*public String getBidderOnlyListByTahdrId(){
		StringBuilder jpql= new StringBuilder(" Select b from Bidder b ");
		jpql.append(" LEFT JOIN FETCH b.tahdr t ");
		jpql.append(" where b.tahdr.tahdrId=:tahdrId ");
		return jpql.toString();
	}*/
		
		/*public String getTCBidderListByTahdrId(){
			StringBuilder jpql= new StringBuilder();
			jpql.append(" Select DISTINCT b from Bidder b ");
			jpql.append(" LEFT JOIN FETCH b.partner p ");
			jpql.append(" LEFT JOIN FETCH b.commercialBid cb ");
			jpql.append(" LEFT JOIN FETCH b.tahdr t ");
			jpql.append(" LEFT JOIN FETCH b.itemBidList ibl ");
			jpql.append(" LEFT JOIN FETCH ibl.technicalBid tb ");
			jpql.append(" LEFT JOIN FETCH ibl.tahdrMaterial tm");
			jpql.append(" LEFT JOIN FETCH tm.material tmm");
			jpql.append(" LEFT JOIN FETCH tb.digiSignedDoc dstb ");
			jpql.append(" where b.tahdr.tahdrId=:tahdrId ");
			return jpql.toString();
		}*/
		
       public String getPBBidderListByTahdrId(){
			
			StringBuilder jpql= new StringBuilder();
			jpql.append(" Select DISTINCT b from Bidder b ");
			jpql.append(" LEFT JOIN FETCH b.partner p ");
			jpql.append(" LEFT JOIN FETCH b.tahdr t ");
			jpql.append(" LEFT JOIN FETCH b.itemBidList ibl ");
			jpql.append(" LEFT JOIN FETCH ibl.priceBid pb");
			jpql.append(" LEFT JOIN FETCH ibl.tahdrMaterial tm");
			jpql.append(" LEFT JOIN FETCH tm.material tmm");
			jpql.append(" LEFT JOIN FETCH pb.digiSignedDoc dspb ");
			jpql.append(" where b.tahdr.tahdrId=:tahdrId AND b.status='"+AppBaseConstant.BIDDER_STATUS_Price_BID_OPENED+"'");
			return jpql.toString();
		}
       
       public String getC1BidderListByTahdrId(){
			
			StringBuilder jpql= new StringBuilder();
			jpql.append(" Select DISTINCT b from Bidder b ");
			jpql.append(" LEFT JOIN FETCH b.partner p ");
			jpql.append(" LEFT JOIN FETCH b.tahdr t ");
			jpql.append(" LEFT JOIN FETCH b.itemBidList ibl ");
			jpql.append(" LEFT JOIN FETCH ibl.priceBid pb");
			jpql.append(" LEFT JOIN FETCH ibl.tahdrMaterial tm");
			jpql.append(" LEFT JOIN FETCH tm.material tmm");
			jpql.append(" LEFT JOIN FETCH pb.digiSignedDoc dspb ");
			jpql.append(" where b.tahdr.tahdrId=:tahdrId AND b.status='"+AppBaseConstant.BIDDER_STATUS_ANNEXURE_C1_OPENED+"'");
			return jpql.toString();
		}
       
       public String getDBBidderListByTahdrId(){
			
			StringBuilder jpql= new StringBuilder();
			jpql.append(" Select DISTINCT b from Bidder b ");
			jpql.append(" LEFT JOIN FETCH b.partner p ");
			jpql.append(" LEFT JOIN FETCH b.tahdr t ");
			jpql.append(" LEFT JOIN FETCH b.itemBidList ib ");
			jpql.append(" LEFT JOIN FETCH ib.tahdrMaterial tm ");
			jpql.append(" LEFT JOIN FETCH tm.material tmm");
			jpql.append(" LEFT JOIN FETCH b.itemScrutinyList itsc ");
			jpql.append(" LEFT JOIN FETCH itsc.scrutinyFile sf ");
			jpql.append(" where b.tahdr.tahdrId=:tahdrId AND b.status='"+AppBaseConstant.BIDDER_STATUS_DEVIATION_OPENED+"'");
			return jpql.toString();
		}
       
       public String getBidderAssociatedTendersQuery(TenderCommitteeDto tenderCommitteeDto){
   		StringBuilder jpql=new StringBuilder();
   		jpql.append(" Select DISTINCT b from Bidder b ");
		jpql.append(" LEFT JOIN FETCH b.partner p ");
		jpql.append(" LEFT JOIN FETCH b.tahdr t ");
		jpql.append(" INNER JOIN FETCH t.tahdrDetail td ");
		jpql.append(" LEFT JOIN FETCH t.bidders bd ");
   		String where =  getWhereClause(tenderCommitteeDto);
   		jpql.append(where);
   		return jpql.toString();
   	}
       
       public String getBidderAssociatedMyTendersQuery(){
      		StringBuilder jpql=new StringBuilder();
      		jpql.append(" Select DISTINCT b from Bidder b ");
   		jpql.append(" LEFT JOIN FETCH b.partner p ");
   		jpql.append(" LEFT JOIN FETCH b.tahdr t ");
   		jpql.append(" INNER JOIN FETCH t.tahdrDetail td ");
   		jpql.append(" LEFT JOIN FETCH t.bidders bd ");
      		jpql.append(" WHERE t.tahdrId=:tahdrId AND p.bPartnerId=:bpartnerId");
      		return jpql.toString();
      	}
       
       public String getBidderAssociatedAllTendersQuery()
   	{
        StringBuilder jpql=new StringBuilder();
      	jpql.append(" Select DISTINCT b from Bidder b ");
   		jpql.append(" LEFT JOIN FETCH b.partner p ");
   		jpql.append(" LEFT JOIN FETCH b.tenderDetail td  ");
   		jpql.append(" LEFT JOIN FETCH td.tahdr  t ");
   		jpql.append(" LEFT JOIN FETCH t.bidders bd ");
   		jpql.append(" WHERE tah.tahdrTypeCode=:typeCode AND tah.tahdrStatusCode NOT IN ('AP','IP','VO','DR','RJ') ");
   		return jpql.toString();
   	}
       
   	
   	private String getWhereClause(TenderCommitteeDto tenderCommitteeDto){
   		StringBuilder where = new StringBuilder();
   		where.append(" WHERE td.isActive =:isActive And b.partner.bPartnerId=:partnerId  AND b.status NOT IN ('DR','PRCH') ");
   		/*where.append(" WHERE b.partner.bPartnerId=:partnerId ");*/	
   		if(tenderCommitteeDto==null){
   			return where.toString();
   		}
   		if(!tenderCommitteeDto.getTahdr().getTahdrTypeCode().equals(""))
   		{
   			where.append(" AND upper(b.tahdr.tahdrTypeCode)=upper(:tahdrTypeCode) ");
   		}
   		if(tenderCommitteeDto.getTenderVersion().getTechBidOpenningDate()!=null){
   			where.append(" AND (td.techBidOpenningDate<=:technicalOpening AND td.techBidOpenningDate<=now()) AND bd.status IN ('TCOP','SBMT') AND t.tahdrStatusCode IN ('TCOP','PU') AND t.bidTypeCode ='TB' "
   					+ " AND t.tahdrId IN (SELECT tc.tahdr.tahdrId From TenderCommittee tc WHERE tc.tahdr.tahdrId=t.tahdrId AND tc.bidOpeningType='TCO' ) ");
   		}
   		if(tenderCommitteeDto.getTenderVersion().getDeviationOpenningDate()!=null ){
   			where.append(" AND (td.deviationOpenningDate<=:deviationOpening AND td.deviationOpenningDate<=now()) AND bd.status IN ('DBOP','DBSU') AND t.tahdrStatusCode IN ('DBOP','DBSCH') "
   					+ " AND t.tahdrId IN (SELECT tc.tahdr.tahdrId From TenderCommittee tc WHERE tc.tahdr.tahdrId=t.tahdrId AND tc.bidOpeningType='DBO' ) ");
   		}	
   		if(tenderCommitteeDto.getTenderVersion().getC1OpenningDate()!=null){
   			where.append(" AND (td.c1OpenningDate<=:c1Opening AND td.c1OpenningDate<=now()) AND bd.status IN ('C1OP','C1SU') AND t.tahdrStatusCode IN ('C1OP','C1SCH') "
   					+ " AND t.tahdrId IN (SELECT tc.tahdr.tahdrId From TenderCommittee tc WHERE tc.tahdr.tahdrId=t.tahdrId AND tc.bidOpeningType='C1O' ) ");
   		}
   		if(tenderCommitteeDto.getTenderVersion().getPriceBidOpenningDate()!=null){
   			where.append(" AND (td.priceBidOpenningDate<=:priceBidOpening AND td.priceBidOpenningDate<=now()) OR ((td.revisedBidOpenningDate<=:priceBidOpening ) AND td.revisedBidOpenningDate<=now()) "
   					   + " AND ((t.tahdrStatusCode IN ('PBSCH','PBOP','RBSCH') AND t.bidTypeCode='TB') OR "
   					+ " (t.tahdrStatusCode IN ('PBSCH','PBOP') AND t.bidTypeCode='SB') ) AND t.tahdrId IN (SELECT tc.tahdr.tahdrId From TenderCommittee tc WHERE tc.tahdr.tahdrId=t.tahdrId AND tc.bidOpeningType='PBO' ) ");
   		}
   		if(tenderCommitteeDto.getTahdr()!=null)
   		{
   			/*where.append(" AND upper(b.tahdr.tahdrStatusCode)=upper(:status)  ");*/
   			if(!tenderCommitteeDto.getTahdr().getTahdrCode().equals("")){
   				where.append(" AND upper(b.tahdr.tahdrCode) LIKE upper(:tahdrCode)");
   			}
   		}
   		
   		return where.toString();
   	}
   	
public String getQueryForMailListByTahdrId(){
		
		StringBuilder jpql= new StringBuilder();
		jpql.append(" Select b from Bidder b ");
		jpql.append(" LEFT JOIN FETCH b.createdBy cr ");
		jpql.append(" LEFT JOIN FETCH b.partner p ");
		jpql.append(" LEFT JOIN FETCH b.tahdr t ");
		jpql.append(" LEFT JOIN FETCH cr.userDetails ud ");
		jpql.append(" where b.tahdr.tahdrId=:tahdrId ");
		return jpql.toString();
	}

public String getQueryForMailByBidderId(){
	
	StringBuilder jpql= new StringBuilder();
	jpql.append(" Select b from Bidder b ");
	jpql.append(" LEFT JOIN FETCH b.createdBy cr ");
	jpql.append(" LEFT JOIN FETCH b.tahdr t ");
	jpql.append(" LEFT JOIN FETCH cr.userDetails ud ");
	
	jpql.append(" where b.bidderId=:bidderId ");
	return jpql.toString();
}
public String getQueryForMailListByTahdrIdAndStatus(){
	
	StringBuilder jpql= new StringBuilder();
	jpql.append(" Select b from Bidder b ");
	jpql.append(" LEFT JOIN FETCH b.createdBy cr ");
	jpql.append(" LEFT JOIN FETCH b.partner p ");
	jpql.append(" LEFT JOIN FETCH b.tahdr t ");
	jpql.append(" where b.tahdr.tahdrId=:tahdrId AND b.status=:status ");
	return jpql.toString();
}

public String getBidderSubmitStatus(){
	StringBuilder jpql= new StringBuilder();
	jpql.append(" Select bdr from Bidder bdr ");
	jpql.append(" LEFT JOIN FETCH bdr.tahdr t ");
	jpql.append(" LEFT JOIN FETCH t.tahdrDetail td ");
	jpql.append(" LEFT JOIN FETCH bdr.itemBidList ib ");
	jpql.append(" WHERE td.tahdrDetailId=:tahdrDetailId and td.isActive='Y' ");
	jpql.append(" and bdr.partner.bPartnerId=:partnerId and ib.status='"+AppBaseConstant.BIDDER_STATUS_BID_SUBMITED+"' ");
	return jpql.toString();
}

public String submitBidderQuery(){
	StringBuilder jpql= new StringBuilder();
	jpql.append(" Update Bidder b SET b.status=:status ");
	jpql.append( " where bidderId=:bidderId");
	return jpql.toString();
}

@Override
@Transactional(propagation=Propagation.REQUIRED)
public int submitBidder(BidderDto bidder){
	String jpql=submitBidderQuery();
	Query query=getEntityManager().createQuery(jpql.toString());
	query.setParameter("status", AppBaseConstant.BIDDER_STATUS_BID_SUBMITED);
	query.setParameter("bidderId", bidder.getBidderId());
	int count= query.executeUpdate();
	return count;
}

/*public String getBidderListFromTahdrMaterialId(){
	StringBuilder jpql=new StringBuilder();
	jpql.append(" Select b from Bidder b ");
	jpql.append(" LEFT JOIN FETCH b.partner bp ");
	jpql.append(" LEFT JOIN FETCH b.tenderPurchase purchase ");
	jpql.append(" LEFT JOIN FETCH b.factory po ");
	jpql.append(" LEFT JOIN FETCH b.tenderDetail td ");
	jpql.append(" LEFT JOIN FETCH td.tahdr t ");
	jpql.append(" LEFT JOIN FETCH b.itemBidList ib ");
	jpql.append(" LEFT JOIN FETCH ib.tahdrMaterial tm ");
	jpql.append(" LEFT JOIN FETCH ib.priceBid pb");
	jpql.append(" where tm.tahdrMaterialId=:tahdrMaterialId ");
	return jpql.toString();
}*/

	public String getBidderPartnerQuery(){
		
		StringBuilder jpql = new StringBuilder();
		jpql.append("SELECT e FROM Bidder e ")
		.append(" INNER JOIN e.partner p")
		.append(" WHERE e.bidderId= :bidderId");
		
		return jpql.toString();
	}
	
	public String getBidderByTahdrDetailId(){
		StringBuilder jpql= new StringBuilder();
		jpql.append(" Select bdr from Bidder bdr ")
		.append(" LEFT JOIN FETCH bdr.tenderPurchase tp ")
		.append(" INNER JOIN FETCH bdr.tahdr tahdr ")
		.append(" INNER JOIN FETCH tahdr.tahdrDetail td ")
		.append(" LEFT JOIN FETCH bdr.factory po ")
		.append(" WHERE td.tahdrDetailId=:tahdrDetailId ")
		.append(" and bdr.partner.bPartnerId=:partnerId ");
		return jpql.toString();
	}
	
	public String getBidderListByTahdrMaterialId(){
		StringBuilder jpql= new StringBuilder();
		jpql.append(" Select bdr from Bidder bdr ")
		.append(" LEFT JOIN FETCH bdr.tahdr t ")
		.append(" LEFT JOIN FETCH bdr.factory f ")
		.append(" LEFT JOIN FETCH bdr.partner p ")
		.append(" INNER JOIN FETCH t.tahdrDetail td ")
		.append(" WHERE t.tahdrId=:tahdrId AND td.isActive='Y' AND bdr.bidderId IN "
				+ " (SELECT b.bidderId FROM ItemBid ib "
				+ "LEFT JOIN ib.bidder b "
				+ "LEFT JOIN ib.tahdrMaterial tm "
				+ "LEFT JOIN b.tahdr t WHERE t.tahdrId=:tahdrId AND tm.tahdrMaterialId=:tahdrMaterialId) ");
		return jpql.toString();
	}
	
	public String getBidderListForQRFQByTahdrId(){
		StringBuilder jpql= new StringBuilder();
		jpql.append(" Select bdr from Bidder bdr ")
		.append(" LEFT JOIN FETCH bdr.tahdr t ")
		.append(" LEFT JOIN FETCH bdr.factory f ")
		.append(" LEFT JOIN FETCH bdr.partner p ")
		.append(" INNER JOIN FETCH t.tahdrDetail td ")
		.append(" WHERE t.tahdrId=:tahdrId AND td.isActive='Y' ");
		return jpql.toString();
	}
	@Override
	public int updateBidderStatusForC1Called(String status,Long tahdrId) {
		StringBuilder jpql= new StringBuilder();
		jpql.append(" Update Bidder b SET b.status=:status ");
		jpql.append( " where b.tahdr.tahdrId=:tahdrId And  b.bidderId IN (SELECT ib.bidder.bidderId FROM ItemBid ib WHERE ib.status=:status) ");
		Query query=getEntityManager().createQuery(jpql.toString());
		query.setParameter("tahdrId", tahdrId);
		query.setParameter("status", status);
		int count= query.executeUpdate();
		return count;
	}
	
	 public String getBidderByIdWithTahdrDetail(){
			
			StringBuilder jpql= new StringBuilder();
			jpql.append(" Select b from Bidder b ");
			jpql.append(" LEFT JOIN FETCH b.tahdr t ");
			jpql.append(" LEFT JOIN FETCH t.tahdrDetail td ");
			jpql.append(" LEFT JOIN FETCH td.tenderDoc doc ");
			jpql.append(" LEFT JOIN FETCH b.partner bp ");
			jpql.append(" where b.bidderId=:bidderId and td.isActive='Y' ");
			return jpql.toString();
		}
	 
	 public String getBidderByIdWithTahdrAndOfficeDetail(){
			
			StringBuilder jpql= new StringBuilder();
			jpql.append(" Select b from Bidder b ");
			jpql.append(" LEFT JOIN FETCH b.tahdr t ");
			jpql.append(" LEFT JOIN FETCH t.tahdrDetail td ");
			jpql.append(" LEFT JOIN FETCH td.tenderDoc doc ");
			jpql.append(" LEFT JOIN FETCH b.partner bp ");
			jpql.append(" LEFT JOIN FETCH bp.officeType ofctype ");
			jpql.append(" LEFT JOIN FETCH bp.officeLocation ofcLoc ");
			jpql.append(" where b.bidderId=:bidderId and td.isActive='Y' ");
			return jpql.toString();
		}
	 
	 public String getBidderTahdrId(){
			
			StringBuilder jpql= new StringBuilder();
			jpql.append(" Select b from Bidder b ");
			jpql.append(" left join fetch b.tenderPurchase tp ");
			jpql.append(" left join fetch b.emdPayment ep ");
			jpql.append(" where b.tahdr.tahdrId=:tahdrId");
			return jpql.toString();
		}
	 
	 public String getBidderFromPaymentDetailId(){
			
			StringBuilder jpql= new StringBuilder(getBidderList());
			jpql.append(" where tp.paymentDetailId=:paymentDetailId");
			return jpql.toString();
		}	
	
	 @Override
	 @Transactional
	public long getAwardWinnerCount(Long tahdrId){		
			StringBuilder sb= new StringBuilder(" Select COUNT(E) from Bidder E ");
			sb.append(" LEFT JOIN  E.tahdr t ");
			sb.append(" LEFT JOIN  t.tahdrDetail td ");
			sb.append(" WHERE t.tahdrId=:tahdrId  AND E.status=( CASE WHEN td.isAnnexureC1='Y' THEN '"+AppBaseConstant.BIDDER_STATUS_ANNEXURE_C1_OPENED+"' ELSE '"+AppBaseConstant.BIDDER_STATUS_Price_BID_OPENED+"' END) ");
			Query q = getEntityManager().createQuery(sb.toString());
			q.setParameter("tahdrId", tahdrId);
			Long count= (Long) q.getSingleResult();
			return count;
		}
		
		 public String getOpenedBidderTahdrId(){
			StringBuilder jpql= new StringBuilder();
			jpql.append(" Select bd from Bidder bd ")
			.append(" LEFT JOIN FETCH bd.partner p ")
			.append(" LEFT JOIN FETCH bd.tahdr t ")
			.append(" LEFT JOIN FETCH t.tahdrDetail td ")
			.append(" LEFT JOIN FETCH bd.itemBidList ib ")
			.append(" LEFT JOIN FETCH ib.tahdrMaterial tm ")
			.append(" LEFT JOIN FETCH tm.tahdrDetail td1 ")
			.append(" LEFT JOIN FETCH tm.material tmm ")
			.append(" LEFT JOIN FETCH bd.itemScrutinyList isl ")
			.append(" LEFT JOIN FETCH isl.scrutinyFile sf ")
			.append(" where t.tahdrId=:tahdrId and (td.tahdrDetailId=td1.tahdrDetailId AND td.isActive='Y') "
					+" AND tm.tahdrMaterialId=:tahdrMaterialId "
					+ " AND bd.status=:status AND ib.status=:status");
			return jpql.toString();
			
		}
		 public String getDBOpenedBidderTahdrId(){
				StringBuilder jpql= new StringBuilder();
				jpql.append(" Select bd from Bidder bd ")
				.append(" LEFT JOIN FETCH bd.partner p ")
				.append(" LEFT JOIN FETCH bd.tahdr t ")
				.append(" LEFT JOIN FETCH t.tahdrDetail td ")
				.append(" LEFT JOIN FETCH bd.itemBidList ib ")
				.append(" LEFT JOIN FETCH ib.tahdrMaterial tm ")
				.append(" LEFT JOIN FETCH tm.tahdrDetail td1 ")
				.append(" LEFT JOIN FETCH tm.material tmm ")
				.append(" LEFT JOIN FETCH bd.itemScrutinyList isl ")
				.append(" LEFT JOIN FETCH isl.scrutinyFile sf ")
				.append(" where t.tahdrId=:tahdrId and (td.tahdrDetailId=td1.tahdrDetailId AND td.isActive='Y') "
						+" AND tm.tahdrMaterialId=:tahdrMaterialId "
						+" AND bd.status=:status AND ib.status=:status AND ib.itemBidId=isl.itemBid.itemBidId");
				return jpql.toString();
				
			}
		 
		 public String getSBOpenedBidderTahdrId(){
				StringBuilder jpql= new StringBuilder();
				jpql.append(" Select bd from Bidder bd ")
				.append(" LEFT JOIN FETCH bd.partner p ")
				.append(" LEFT JOIN FETCH bd.tahdr t ")
				.append(" LEFT JOIN FETCH t.tahdrDetail td ")
				.append(" LEFT JOIN FETCH bd.itemBidList ib ")
				.append(" LEFT JOIN FETCH ib.tahdrMaterial tm ")
				.append(" LEFT JOIN FETCH tm.tahdrDetail td1 ")
				.append(" LEFT JOIN FETCH tm.material tmm ")
				/*.append(" LEFT JOIN FETCH bd.itemScrutinyList isl ")
				.append(" LEFT JOIN FETCH isl.scrutinyFile sf ")*/
				.append(" where t.tahdrId=:tahdrId and (td.tahdrDetailId=td1.tahdrDetailId AND td.isActive='Y') "
						+" AND tm.tahdrMaterialId=:tahdrMaterialId "
						+ " AND bd.status=:status AND ib.status=:status ");
						/*+ "and ib.itemBidId=isl.itemBid.itemBidId");*/
				return jpql.toString();
				
			}
	
		 @Override
		 public String getTahdrListOfSubmissionForReminderMail(String status){
				
				StringBuilder jpql= new StringBuilder();
				jpql.append(" Select b from Bidder b ");
				jpql.append(" LEFT JOIN FETCH b.createdBy cr ");
				jpql.append(" LEFT JOIN FETCH b.partner p ");
				jpql.append(" LEFT JOIN FETCH b.tahdr t ");
				jpql.append(" LEFT JOIN FETCH cr.userDetails ud ");
				jpql.append(" where b.tahdr.tahdrId=:tahdrId ");
				if(status.equalsIgnoreCase(AppBaseConstant.Bid_Submission)){
					jpql.append(" AND t.tahdrStatusCode='"+AppBaseConstant.DOCUMENT_STATUS_PUBLISHED+"' ");
				}
				else if(status.equalsIgnoreCase(AppBaseConstant.Deviation_Bid)){
					jpql.append(" AND b.status IN ('"+AppBaseConstant.BIDDER_STATUS_DEVIATION_CALLED+"','"+AppBaseConstant.BIDDER_STATUS_DEVIATION_SUBMITTED+"') ");
				}
				else if(status.equalsIgnoreCase(AppBaseConstant.Annexture_C1)){
					jpql.append(" AND b.status IN ('"+AppBaseConstant.BIDDER_STATUS_ANNEXURE_C1_CALLED+"','"+AppBaseConstant.BIDDER_STATUS_ANNEXURE_C1_SUBMITED+"') ");
				}
				return jpql.toString();
			}
		 
		 public String getQueryForReminderMailListByTahdrId(){
				
				StringBuilder jpql= new StringBuilder();
				jpql.append(" Select b from Bidder b ");
				jpql.append(" LEFT JOIN FETCH b.createdBy cr ");
				jpql.append(" LEFT JOIN FETCH cr.partner p ");
				jpql.append(" LEFT JOIN FETCH b.tahdr t ");
				jpql.append(" LEFT JOIN FETCH cr.userDetails ud ");
				jpql.append(" where b.tahdr.tahdrId=:tahdrId ");
				return jpql.toString();
			}
		 
		 @Override
		 public String getBidderDataFromBidderId(){
				
				StringBuilder jpql= new StringBuilder();
				jpql.append(" Select b from Bidder b ");
				jpql.append(" LEFT JOIN FETCH b.createdBy cr ");
				jpql.append(" LEFT JOIN FETCH cr.partner p ");
				jpql.append(" LEFT JOIN FETCH cr.userDetails ud ");
				jpql.append(" where b.bidderId=:bidderId ");
				return jpql.toString();
			}
		 
		 @Override
		 @Transactional(propagation=Propagation.SUPPORTS)
		 public int updateQuickBidderForWinner(Long tahdrId){
		 	StringBuilder jpql= new StringBuilder();
		 	jpql.append(" Update Bidder b SET b.status='"+AppBaseConstant.BIDDER_STATUS_QUICK_WINNER+"'");
		 	jpql.append(" where bidderId= (SELECT ib.bidder.bidderId FROM ItemBid ib WHERE ib.bidder.tahdr.tahdrId=:tahdrId AND ib.isLowestBid='Y' "
		 			+ " AND ib.status='"+AppBaseConstant.BIDDER_STATUS_QUICK_WINNER+"')");
		 	Query query=getEntityManager().createQuery(jpql.toString());
		 	query.setParameter("tahdrId", tahdrId);
		 	int count= query.executeUpdate();
		 	return count;
		 }
		 
		 public String getBidderByPrId(){
				StringBuilder jpql= new StringBuilder();
				jpql.append(" Select distinct(A) from Bidder A ");
				jpql.append(" INNER JOIN FETCH A.partner B ");
				/*jpql.append(" INNER JOIN FETCH A.pr C ");*/
				jpql.append(" INNER JOIN FETCH A.enquiry C ");
				/*jpql.append(" INNER JOIN FETCH B.createdBy D ");*/
				jpql.append(" INNER JOIN FETCH A.itemBidList E ");
				jpql.append(" INNER JOIN FETCH E.prLine F ");
				jpql.append(" where C.enquiryId=:prId ");
				jpql.append( " ORDER BY A.bidderId ASC");
				//jpql.append( " ORDER BY E.lineNumber ASC");
				return jpql.toString();
			}
		 
		 
		 
//		 public String getBidderByenquiryId(){
//				StringBuilder jpql= new StringBuilder();
//				jpql.append(" Select distinct(A) from Bidder A ");
//				jpql.append(" INNER JOIN FETCH A.enquiry C ");
//				jpql.append(" INNER JOIN FETCH A.itemBidList E ");
//				jpql.append(" INNER JOIN FETCH E.prLine F ");
//				jpql.append(" where C.enquiryId=:enquiryId ");
//				jpql.append( " ORDER BY A.bidderId ASC");
//				return jpql.toString();
//			}
		 
		/* public String getVendorQuotation(){
				
				StringBuilder jpql= new StringBuilder();
				jpql.append(" Select a from Bidder a ");
				jpql.append(" INNER JOIN FETCH a.partner b ");
				jpql.append(" INNER JOIN FETCH a.pr c ");
				jpql.append(" INNER JOIN FETCH c.buyer d ");
				jpql.append(" where b.bPartnerId=:bpartnerId AND :currentDate<=c.bidEndDate AND a.status IN (:status) ");
				jpql.append(" where b.bPartnerId=:bpartnerId AND a.status IN (:status) ");
				return jpql.toString();
			}*/
		 public String getVendorQuotation(){
				
				StringBuilder jpql= new StringBuilder();
				jpql.append(" Select a from Bidder a ");
				jpql.append(" INNER JOIN FETCH a.partner b ");
				jpql.append(" INNER JOIN FETCH a.enquiry c ");
				jpql.append(" INNER JOIN FETCH c.createdBy d ");
//				jpql.append(" where b.bPartnerId=:bpartnerId AND :currentDate<=c.bidEndDate AND a.status IN (:status) ");
//				jpql.append(" where b.bPartnerId=:bpartnerId AND a.status IN (:status) order by c.bidEndDate desc");
//				jpql.append(" where b.bPartnerId=:bpartnerId AND a.status IN (:status) order by c.enqNo desc");
				jpql.append(" where b.bPartnerId=:bpartnerId AND a.status IN (:status) order by c.enquiryId desc");
				return jpql.toString();
			}
		 
		 public String getBidderByPrIdAndStatus(){
			StringBuilder jpql= new StringBuilder();
			jpql.append(" Select A from Bidder A ");
			jpql.append(" INNER JOIN FETCH A.partner B ");
			/*jpql.append(" INNER JOIN FETCH A.pr C ");*/
			jpql.append(" INNER JOIN FETCH A.enquiry C ");
			jpql.append(" LEFT JOIN FETCH B.createdBy D ");
			jpql.append(" where C.enquiryId=:prId AND A.status IN(:status) ");
			jpql.append( " ORDER BY A.bidderId ASC");
			return jpql.toString();
		}
		 public String getVendorQuotationForApproval(){
				
				StringBuilder jpql= new StringBuilder();
				jpql.append(" Select a from Bidder a ");
				jpql.append(" INNER JOIN FETCH a.partner b ");
				jpql.append(" INNER JOIN FETCH a.pr c ");
				jpql.append(" INNER JOIN FETCH c.buyer d ");
				jpql.append(" where c.bidEndDate=:bidEndDate and c.qcfNo is null and a.status=:status ");
				return jpql.toString();
			}
		 
		 
		 public String validateBidEndDateById(){
				
			StringBuilder jpql= new StringBuilder();
			jpql.append(" Select a from Bidder a ");
			jpql.append(" INNER JOIN FETCH a.pr c ");
			jpql.append(" where a.bidderId=:bidderId AND :currentDate<=c.bidEndDate ");
			return jpql.toString();
		}
		@Override
		//public String getBidderFilterQuery(BidderFilterDto dto) {
		public String getBidderFilterQuery(BidderFilterDto dto) {
			StringBuilder jpql= new StringBuilder();
			jpql.append(" Select A from Bidder A ");
			jpql.append(" INNER JOIN FETCH A.partner B ");
			jpql.append(" INNER JOIN FETCH A.enquiry C ");
			jpql.append(" LEFT JOIN FETCH B.createdBy D ");
			jpql.append(" INNER JOIN FETCH C.createdBy E ");
			jpql.append(" LEFT JOIN FETCH A.user F");
			String where =  getWhereClause(dto);
	   		jpql.append(where);
	  
	   		
	return jpql.toString();
	
		}
		private String getWhereClause(BidderFilterDto dto){
	   		StringBuilder where = new StringBuilder();
	   		where.append(" WHERE C.isActive = 'Y' ");
	   		if(dto.getEnqNoFrom()!=null && dto.getEnqNoTo()!=null){
	   			where.append(" AND C.enquiryId BETWEEN :enqNoFrom AND :enqNoTo ");
	   		}
	   		if(dto.getEnqNoFrom()!=null && dto.getEnqNoTo()==null){
	   			where.append(" AND C.enquiryId =:enqNoFrom ");
	   		}
	   		if(dto.getEnqNoFrom()==null && dto.getEnqNoTo()!=null){
	   			where.append(" AND C.enquiryId =:enqNoTo ");
	   		}
	   		if(dto.getEnqDateFrom()!=null && dto.getEnqDateTo()!=null){
//	   			where.append(" AND DATE(A.enquiry.created) BETWEEN :enqFromDate AND :enqToDate ");
	   			
	   			where.append(" AND A.enquiry.created BETWEEN :enqFromDate AND :enqToDate ");
	   		}
	   		if(dto.getEnqDateFrom()!=null && dto.getEnqDateTo()==null){
//	   			where.append(" AND DATE(A.enquiry.created) =:enqFromDate ");
	   			where.append(" AND A.enquiry.created =:enqFromDate ");
	   		}
	   		if(dto.getEnqDateFrom()==null && dto.getEnqDateTo()!=null){
//	   			where.append(" AND DATE(A.enquiry.created) =:enqToDate ");
	   			
	   			where.append(" AND A.enquiry.created =:enqToDate ");
	   		}
	   		if(dto.getEnqEndDateFrom()!=null && dto.getEnqEndDateTo()!=null){
	   			where.append(" AND C.bidEndDate BETWEEN :enqEndFromDate AND :enqEndToDate ");
	   		}
	   		if(dto.getEnqEndDateFrom()!=null && dto.getEnqEndDateTo()==null){
	   			where.append(" AND C.bidEndDate =:enqEndFromDate ");
	   		}
	   		if(dto.getEnqEndDateFrom()==null && dto.getEnqEndDateTo()!=null){
	   			where.append(" AND C.bidEndDate =:enqEndToDate ");
	   		}
	   		if(dto.getBuyerCode()!=null){
	   			where.append(" AND E.userName =:buyerId or LOWER(E.name) like CONCAT('%',LOWER(:buyerId),'%' ) ");
//	   			where.append(" AND E.userName =:buyerId ");
	   		}
	   		
	   		if(dto.getEnqStatus()!=null){
	   			where.append(" AND C.code =:enqStatus ");
	   		}
	   		if(dto.getBiiderStatus()!=null){
	   			where.append(" AND A.status =:bidStatus ");
	   		}
	   		if(dto.getVendorCode()!=null ){
	   			where.append(" AND (B.vendorSapCode =:vendorCode or LOWER(B.name) like CONCAT('%',LOWER(:vendorCode),'%') or LOWER(F.email) like CONCAT('%',LOWER(:vendorCode),'%')  )");
//	   			or LOWER(F.email) like CONCAT('%',LOWER(:vendorCode),'%') 
	   		}
	   		return where.toString();
	 	}
		 public String getBidByBidderId(){
				
				StringBuilder jpql= new StringBuilder();
				jpql.append(" Select A from Bidder A ");
				jpql.append(" INNER JOIN FETCH A.enquiry C ");
				jpql.append(" INNER JOIN FETCH A.partner D ");
				jpql.append(" INNER JOIN FETCH C.createdBy E ");
				
				jpql.append(" where A.bidderId=:bidderId ");
				return jpql.toString();
			}
		 
		 
//		 public String getBidByenqNo(){
//				
//				StringBuilder jpql= new StringBuilder();
//				jpql.append(" Select A from Bidder A ");
//				jpql.append(" INNER JOIN FETCH A.partner B ");
//				jpql.append(" INNER JOIN FETCH A.enquiry C ");
//				
//				jpql.append(" where C.enqNo=:enqNo ");
//				return jpql.toString();
//			}
		 
		 public String getBidByenqNo(){
				
				StringBuilder jpql= new StringBuilder();
				jpql.append(" Select A from Bidder A ");
				jpql.append(" INNER JOIN FETCH A.enquiry B ");
//				jpql.append(" INNER JOIN FETCH A.itemBidList C ");
//				jpql.append(" INNER JOIN FETCH C.prLine D ");
//				jpql.append(" INNER JOIN FETCH D.pr E ");
				jpql.append(" INNER JOIN FETCH A.partner F ");	
				jpql.append(" INNER JOIN FETCH B.createdBy G ");
				jpql.append(" where B.enqNo=:enqNo ");
				return jpql.toString();
			}
		 
//		 @Override
//			public String getNewrfqNo() {
//				String enqPrefix = AppBaseConstant.RFQPREFIX;
//				StringBuilder query = new StringBuilder(" SELECT MAX(A.rfqNo) FROM Bidder A ")
//						.append(" WHERE CONCAT(A.rfqNo,'') like '" + enqPrefix + "%' ");
//				Query q = em.createQuery(query.toString());
//				String x = (String) q.getSingleResult();
//
//				if (x == null) {
//
//					x = enqPrefix + "0000000";
//					String[] y = x.split(enqPrefix);
//					String z = y[1];
//					int a = Integer.parseInt(z);
//					a = a + 1;
//					String leftPadded = StringUtils.leftPad("" + a, 7, "0");
//					// StringBuilder sb = new StringBuilder();
//					// sb.append(x).append(a);
//					String finalNo = x + a;
//					// return sb.toString();
//					return finalNo;
//				} else {
//					String[] y = x.split(enqPrefix);
//					String z = y[1];
//					int a = Integer.parseInt(z);
//					a = a + 1;
//					String leftPadded = StringUtils.leftPad("" + a, 8, "0");
//					// String finalNo=x+a;
//					// return finalNo;
//					StringBuilder sb = new StringBuilder();
//					sb.append(enqPrefix).append(leftPadded);
//					return sb.toString();
//					// String finalNo=y[0]+a;
//					// return finalNo;
//
//				}
//			}

}