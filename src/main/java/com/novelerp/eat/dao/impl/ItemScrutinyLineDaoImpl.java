package com.novelerp.eat.dao.impl;

import javax.annotation.PostConstruct;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.novelerp.core.dao.impl.AbstractJpaDAO;
import com.novelerp.eat.dao.ItemScrutinyLineDao;
import com.novelerp.eat.dto.ItemScrutinyLineDto;
import com.novelerp.eat.entity.ItemScrutinyLine;

/**
 * @author Aman Sahu
 *
 */
@Repository
public class ItemScrutinyLineDaoImpl extends AbstractJpaDAO<ItemScrutinyLine, ItemScrutinyLineDto> implements ItemScrutinyLineDao {
	
	@PostConstruct
	public void postConstruct() {
		setClazz(ItemScrutinyLine.class, ItemScrutinyLineDto.class);
	}
	
   public String getScrutinyPoint(){
		StringBuilder jpql= new StringBuilder();
		jpql.append(" Select b from ItemScrutinyLine b ");
		jpql.append(" LEFT JOIN FETCH b.scrutinyPoint sp ");
		jpql.append(" LEFT JOIN FETCH b.itemScrutiny bis ");
		jpql.append(" LEFT JOIN FETCH bis.bidder bd ");
		jpql.append(" WHERE b.itemScrutiny.itemScrutinyId=:itemScrutinyId And b.scrutinyPoint IS NOT NULL ORDER BY sp.serialNo ");
		return jpql.toString();
	}
   
   public String getBidderGtp(){
		StringBuilder jpql= new StringBuilder();
		jpql.append(" Select b from ItemScrutinyLine b ");
		jpql.append(" LEFT JOIN FETCH b.bidderGtp bg ");
		jpql.append(" LEFT JOIN FETCH bg.fileResponse fr ");
		jpql.append(" LEFT JOIN FETCH bg.tahdrMaterialgtp tmg ");
		jpql.append(" LEFT JOIN FETCH tmg.gtp gtp ");
		jpql.append(" LEFT JOIN FETCH gtp.gtpParameterType gtpt ");
		jpql.append(" LEFT JOIN FETCH b.itemScrutiny bis ");
		jpql.append(" LEFT JOIN FETCH bis.bidder bd ");
		jpql.append( " where b.itemScrutiny.itemScrutinyId=:itemScrutinyId And b.bidderGtp IS NOT NULL");
		return jpql.toString();
	}
   
   public String getBidderGtpByTahdrMaterialIdAndBidderId(){
		StringBuilder jpql= new StringBuilder();
		jpql.append(" Select b from ItemScrutinyLine b ");
		jpql.append(" LEFT JOIN FETCH b.bidderGtp bg ");
		jpql.append(" LEFT JOIN FETCH bg.fileResponse fr ");
		jpql.append(" LEFT JOIN FETCH bg.tahdrMaterialgtp tmg ");
		jpql.append(" LEFT JOIN FETCH tmg.gtp gtp ");
		jpql.append(" LEFT JOIN FETCH gtp.gtpParameterType gtpt ");
		jpql.append(" WHERE b.itemScrutiny.itemBid.tahdrMaterial.tahdrMaterialId=:tahdrMaterialId AND b.itemScrutiny.bidder.bidderId=:bidderId AND b.bidderGtp IS NOT NULL");
		return jpql.toString();
	}
   
   public String getFinalBidderGtpByTahdrMaterialIdAndBidderId(){
		StringBuilder jpql= new StringBuilder();
		jpql.append(" Select b from ItemScrutinyLine b ");
		jpql.append(" LEFT JOIN FETCH b.bidderGtp bg ");
		jpql.append(" LEFT JOIN FETCH bg.fileResponse fr ");
		jpql.append(" LEFT JOIN FETCH bg.tahdrMaterialgtp tmg ");
		jpql.append(" LEFT JOIN FETCH tmg.gtp gtp ");
		jpql.append(" LEFT JOIN FETCH gtp.gtpParameterType gtpt ");
		jpql.append(" WHERE b.isDeviation='Y' AND b.itemScrutiny.itemBid.tahdrMaterial.tahdrMaterialId=:tahdrMaterialId AND b.itemScrutiny.bidder.bidderId=:bidderId AND b.bidderGtp IS NOT NULL");
		return jpql.toString();
	}
   
   public String getDocuments(){
	   StringBuilder jpql= new StringBuilder();
		jpql.append(" Select b from ItemScrutinyLine b ");
		jpql.append(" LEFT JOIN FETCH b.itemScrutiny bis ");
		jpql.append(" LEFT JOIN FETCH bis.bidder bd ");
		jpql.append(" LEFT JOIN FETCH b.bidderSectionDoc bd ");
		jpql.append(" LEFT JOIN FETCH bd.sectionDocument sd ");
		jpql.append(" LEFT JOIN FETCH bd.attachment bda ");
		jpql.append(" LEFT JOIN FETCH b.fileResponse fr ");
		return jpql.toString();
   }
   
   public String getTechnicalDocuments(){
		
		StringBuilder jpql= new StringBuilder(getDocuments());
		jpql.append( " where b.bidderSectionDoc.bidSection='TS' And b.bidderSectionDoc IS NOT NULL And b.itemScrutiny.itemBid.itemBidId=:itemBidId");
		return jpql.toString();
	}
   
   public String getTechnicalDocumentsByTahdrMaterialIdAndBidderId(){
		
		StringBuilder jpql= new StringBuilder(getDocuments());
		jpql.append(" WHERE b.bidderSectionDoc.bidSection='TS' And b.bidderSectionDoc IS NOT NULL "
				+ " And b.itemScrutiny.itemBid.tahdrMaterial.tahdrMaterialId=:tahdrMaterialId AND b.itemScrutiny.bidder.bidderId=:bidderId ");
		return jpql.toString();
	}
   
   public String getFinalTechnicalDocumentByTahdrMaterialIdAndBidderId(){
		
		StringBuilder jpql= new StringBuilder(getDocuments());
		jpql.append(" WHERE b.isDeviation='Y'AND  b.bidderSectionDoc.bidSection='TS' And b.bidderSectionDoc IS NOT NULL "
				+ " And b.itemScrutiny.itemBid.tahdrMaterial.tahdrMaterialId=:tahdrMaterialId AND b.itemScrutiny.bidder.bidderId=:bidderId ");
		return jpql.toString();
	}
   
   public String getCommercialDocuments(){
		StringBuilder jpql= new StringBuilder(getDocuments());
		jpql.append(" WHERE b.bidderSectionDoc.bidSection='CS' And b.bidderSectionDoc IS NOT NULL And b.itemScrutiny.bidder.bidderId=:bidderId");
		return jpql.toString();
	}
   
   public String getPreCommercialDocumentsByBidderId(){
		StringBuilder jpql= new StringBuilder(getDocuments());
		jpql.append( " where b.bidderSectionDoc.bidSection='CS' And b.bidderSectionDoc IS NOT NULL And b.itemScrutiny.bidder.bidderId=:bidderId");
		return jpql.toString();
	}
   
   public String getFinalCommercialDocumentsByBidderId(){
		StringBuilder jpql= new StringBuilder(getDocuments());
		jpql.append(" WHERE b.isDeviation='Y' AND b.bidderSectionDoc.bidSection='CS' And b.bidderSectionDoc IS NOT NULL And b.itemScrutiny.bidder.bidderId=:bidderId");
		return jpql.toString();
	}
   
   public String getDeviationCommercialDocuments(){
	   StringBuilder jpql= new StringBuilder(getDocuments());
	   jpql.append( " where b.bidderSectionDoc.bidSection='CS' And b.bidderSectionDoc IS NOT NULL And b.itemScrutiny.bidder.bidderId=:bidderId And b.isDeviation='Y' ");
		return jpql.toString();
   }
   
   public String getDeviationTechnicalDocuments(){
	   StringBuilder jpql= new StringBuilder(getDocuments());
	   jpql.append( " where b.bidderSectionDoc.bidSection='TS' And b.bidderSectionDoc IS NOT NULL And b.itemScrutiny.itemBid.itemBidId=:itemBidId And b.isDeviation='Y' ");
		return jpql.toString();
   }
   
   public String getBidderGtpByItemBidId(){
		
		StringBuilder jpql= new StringBuilder();
		jpql.append(" Select b from ItemScrutinyLine b ");
		jpql.append(" LEFT JOIN FETCH b.bidderGtp bg ");
		jpql.append(" LEFT JOIN FETCH bg.tahdrMaterialgtp tmg ");
		jpql.append(" LEFT JOIN FETCH tmg.gtp gtp ");
		jpql.append(" LEFT JOIN FETCH gtp.gtpParameterType gtpt ");
		jpql.append(" LEFT JOIN FETCH b.fileResponse fr ");
		jpql.append(" LEFT JOIN FETCH b.itemScrutiny bis ");
		jpql.append(" LEFT JOIN FETCH bis.bidder bd ");
		jpql.append( " where b.itemScrutiny.itemBid.itemBidId=:itemBidId And b.isDeviation='Y' And b.bidderGtp IS NOT NULL");
		/*jpql.append( " where b.itemScrutiny.itemBid.itemBidId=:itemBidId And b.bidderGtp<>null");*/
		return jpql.toString();
	}
   
   public String getScrutinyPointByItemBidId(){
		StringBuilder jpql= new StringBuilder();
		jpql.append(" Select b from ItemScrutinyLine b ");
		jpql.append(" LEFT JOIN FETCH b.scrutinyPoint sp ");
		jpql.append(" LEFT JOIN FETCH b.fileResponse fr ");
		jpql.append(" LEFT JOIN FETCH b.itemScrutiny bis ");
		jpql.append(" LEFT JOIN FETCH bis.bidder bd ");
		jpql.append( " where b.itemScrutiny.bidder.bidderId=:bidderId And b.isDeviation='Y' And upper(b.auditorStatus)=upper('APPROVED') "
				+ "And b.scrutinyPoint IS NOT NULL");
		return jpql.toString();
	}
   
   public String getPreScrutinyPointByBidderId(){
		StringBuilder jpql= new StringBuilder();
		jpql.append(" Select b from ItemScrutinyLine b ");
		jpql.append(" LEFT JOIN FETCH b.scrutinyPoint sp ");
		jpql.append(" LEFT JOIN FETCH b.fileResponse fr ");
		jpql.append(" LEFT JOIN FETCH b.itemScrutiny bis ");
		jpql.append(" LEFT JOIN FETCH bis.bidder bd ");
		jpql.append( " where b.itemScrutiny.bidder.bidderId=:bidderId  "
				+ "And b.scrutinyPoint IS NOT NULL ORDER BY sp.serialNo");
		return jpql.toString();
	}
                 
   public String getFinalScrutinyPointByBidderId(){
		StringBuilder jpql= new StringBuilder();
		jpql.append(" Select b from ItemScrutinyLine b ");
		jpql.append(" LEFT JOIN FETCH b.scrutinyPoint sp ");
		jpql.append(" LEFT JOIN FETCH b.fileResponse fr ");
		jpql.append(" LEFT JOIN FETCH b.itemScrutiny bis ");
		jpql.append(" LEFT JOIN FETCH bis.bidder bd ");
		jpql.append( " where b.itemScrutiny.bidder.bidderId=:bidderId And b.isDeviation='Y' "
				+ "And b.scrutinyPoint IS NOT NULL ORDER BY sp.serialNo");
		return jpql.toString();
	}
   
   public String getScrutinyPointByItemScrutinyId(){
		StringBuilder jpql= new StringBuilder();
		jpql.append(" Select b from ItemScrutinyLine b ");
		jpql.append(" LEFT JOIN FETCH b.scrutinyPoint sp ");
		jpql.append(" LEFT JOIN FETCH b.fileResponse fr ");
		jpql.append(" LEFT JOIN FETCH b.itemScrutiny bis ");
		jpql.append(" LEFT JOIN FETCH bis.bidder bd ");
		jpql.append( " where b.itemScrutiny.itemScrutinyId=:itemScrutinyId And b.scrutinyPoint IS NOT NULL");
		return jpql.toString();
	}
   
   public String getStatusByItemBidId(){
	   StringBuilder jpql= new StringBuilder();
		jpql.append(" Select b from ItemScrutinyLine b ");
		jpql.append(" LEFT JOIN FETCH b.itemScrutiny bis ");
		jpql.append(" LEFT JOIN FETCH bis.scrutinyFile sf ");
		jpql.append( " where b.itemScrutiny.itemBid.itemBidId=:itemBidId"
				+ " And b.itemScrutiny.scrutinyType=:scrutinyType And b.isDeviation='Y'");
		/*jpql.append( " where b.itemScrutiny.itemBid.itemBidId=:itemBidId And b.bidderGtp<>null");*/
		return jpql.toString();
   }
   
   public String getStatusByBidderId()
   {
	   StringBuilder jpql= new StringBuilder();
		jpql.append(" Select b from ItemScrutinyLine b ");
		jpql.append(" LEFT JOIN FETCH b.itemScrutiny bis ");
		jpql.append(" LEFT JOIN FETCH bis.scrutinyFile sf ");
		jpql.append( " where b.itemScrutiny.bidder.bidderId=:bidderId"
				+ " And b.itemScrutiny.scrutinyType=:scrutinyType And b.isDeviation='Y'");
		return jpql.toString();
   }
   
   public String getFinalStatusByBidderId()
   {
	   StringBuilder jpql= new StringBuilder();
		jpql.append(" Select b from ItemScrutinyLine b ");
		jpql.append(" LEFT JOIN FETCH b.itemScrutiny bis ");
		jpql.append(" LEFT JOIN FETCH bis.bidder bd ");
		jpql.append(" LEFT JOIN FETCH bis.scrutinyFile sf ");
		jpql.append( " where b.itemScrutiny.bidder.bidderId=:bidderId"
				+ " And b.itemScrutiny.scrutinyType=:scrutinyType And b.isDeviation='Y' And b.isDeviationSubmitted='Y'");
		return jpql.toString();
   }
   
   public String getAuditingStatusBytahdrId()
   {
	   StringBuilder jpql= new StringBuilder();
		jpql.append(" Select b from ItemScrutinyLine b ");
		jpql.append(" LEFT JOIN FETCH b.itemScrutiny bis ");
		jpql.append(" LEFT JOIN FETCH bis.bidder bd ");
		jpql.append(" LEFT JOIN bd.tahdr t ");
		jpql.append( " WHERE t.tahdrId=:tahdrId AND b.isDeviation='Y' AND bis.scrutinyType='COMMSCR' "
				+ " AND (bis.isFinalAudited='Y'  OR  bis.isFinalAudited IS NULL) AND (bis.finalAuditorStatus='CLARIFICATION' OR bis.finalAuditorStatus IS NULL)"
				+ " AND bd.status<>'COMMFAIL'");
		return jpql.toString();
   }
   
   public String getFinalStatusByItemBidId()
   {
	   StringBuilder jpql= new StringBuilder();
		jpql.append(" Select b from ItemScrutinyLine b ");
		jpql.append(" LEFT JOIN FETCH b.itemScrutiny bis ");
		jpql.append(" LEFT JOIN FETCH bis.scrutinyFile sf ");
		jpql.append( " where b.itemScrutiny.itemBid.itemBidId=:itemBidId "
				+ "And b.itemScrutiny.scrutinyType=:scrutinyType And b.isDeviation='Y' And b.isDeviationSubmitted='Y'");
		return jpql.toString();
   }
   
   public String getNonScrutinizedDeviationFinalList(){
		StringBuilder jpql= new StringBuilder();
		jpql.append(" Select b from ItemScrutinyLine b ");
		jpql.append(" LEFT JOIN FETCH b.itemScrutiny bis ");
		jpql.append( " where b.itemScrutiny.itemScrutinyId=:itemScrutinyId AND bis.scrutinyType=:scrutinyType AND b.isDeviation='Y' AND b.isDeviationSubmitted='Y' AND  b.isFinalScrutinySubmitted IS NULL ");
		return jpql.toString();
	}
   
   @Override
   public int updateDeviationResponseInfo(ItemScrutinyLineDto itemScrutinylineDto){
	   StringBuilder jpql= new StringBuilder();
	   if(itemScrutinylineDto.getTextResponse()!=null){
				jpql.append(" Update ItemScrutinyLine b SET b.textResponse=:textResponse,b.isDeviationSubmitted='Y' ");
			   }
	   else if(itemScrutinylineDto.getFileResponse()!=null){
		        jpql.append(" Update ItemScrutinyLine b SET b.fileResponse.attachmentId=:attachmentId,b.isDeviationSubmitted='Y' ");
			   }
				jpql.append( " where b.itemScrutinyLineId=:itemScrutinyLineId");
		
		Query query=getEntityManager().createQuery(jpql.toString());
		query.setParameter("itemScrutinyLineId", itemScrutinylineDto.getItemScrutinyLineId());
		 if(itemScrutinylineDto.getTextResponse()!=null){
			      query.setParameter("textResponse", itemScrutinylineDto.getTextResponse());
			 }
		 else if (itemScrutinylineDto.getFileResponse()!=null){
				 query.setParameter("attachmentId", itemScrutinylineDto.getFileResponse().getAttachmentId());
			 }
		int count= query.executeUpdate();
		return count;
   }
   
   @Override
   public int updateFinalScrutinyResponseInfo(ItemScrutinyLineDto itemScrutinylineDto)
   {
	   StringBuilder jpql= new StringBuilder();
				     jpql.append(" Update ItemScrutinyLine b SET b.finalScrutinyComment=:finalScrutinyComment,b.finalScrutinyStatus=:finalScrutinyStatus, ");
				     jpql.append( " b.isFinalScrutinySubmitted='Y' where b.itemScrutinyLineId=:itemScrutinyLineId AND NOT EXISTS (SELECT isl FROM ItemScrutinyLine isl INNER JOIN isl.itemScrutiny isc INNER JOIN isc.bidder bid INNER JOIN bid.tahdr t "
										+ "WHERE isl.itemScrutinyLineId=:itemScrutinyLineId AND t.isAuditing='Y') ");
				     //jpql.append( " b.isFinalScrutinySubmitted='Y' where b.itemScrutinyLineId=:itemScrutinyLineId");
		Query query=getEntityManager().createQuery(jpql.toString());
		query.setParameter("itemScrutinyLineId", itemScrutinylineDto.getItemScrutinyLineId());
		query.setParameter("finalScrutinyComment", itemScrutinylineDto.getFinalScrutinyComment());
		query.setParameter("finalScrutinyStatus", itemScrutinylineDto.getFinalScrutinyStatus());
		int count= query.executeUpdate();
		return count;
   }
   
   public String getPendingScrutinyByItemScrutinyId(){
		
		StringBuilder jpql= new StringBuilder();
		jpql.append(" Select b from ItemScrutinyLine b ");
		jpql.append(" LEFT JOIN FETCH b.itemScrutiny bis ");
		jpql.append(" LEFT JOIN FETCH bis.bidder dd ");
		jpql.append(" LEFT JOIN FETCH dd.partner p ");
		jpql.append(" LEFT JOIN FETCH bis.itemBid ib ");
		jpql.append(" LEFT JOIN FETCH ib.tahdrMaterial tm ");
		jpql.append(" LEFT JOIN FETCH tm.material tmm ");
		jpql.append(" WHERE b.itemScrutiny.itemScrutinyId=:itemScrutinyId AND b.isDeviation IS NULL ");
		return jpql.toString();
	}
   
   public String getDeviationScrutinyByItemScrutinyId(){
		
		StringBuilder jpql= new StringBuilder();
		jpql.append(" Select b from ItemScrutinyLine b ");
		jpql.append(" LEFT JOIN FETCH b.itemScrutiny bis ");
		jpql.append(" WHERE b.itemScrutiny.itemScrutinyId=:itemScrutinyId AND b.isDeviation='Y' ");
		return jpql.toString();
	}
   
   public String getPendingAuditingItemByItemScrutinyId(){
		
		StringBuilder jpql= new StringBuilder();
		jpql.append(" Select b from ItemScrutinyLine b ");
		jpql.append(" LEFT JOIN FETCH b.itemScrutiny bis ");
		jpql.append(" LEFT JOIN FETCH bis.bidder dd ");
		jpql.append(" LEFT JOIN FETCH dd.partner p ");
		jpql.append(" WHERE b.itemScrutiny.itemScrutinyId=:itemScrutinyId AND bis.scrutinyType='COMMSCR' AND b.isDeviation='Y'AND b.auditorStatus IS NULL ");
		return jpql.toString();
	}
   
   public String getPendingDeviationItemByItemScrutinyId(){
		StringBuilder jpql= new StringBuilder();
		jpql.append(" Select b from ItemScrutinyLine b ");
		jpql.append(" LEFT JOIN FETCH b.itemScrutiny bis ");
		jpql.append( " where b.itemScrutiny.itemScrutinyId=:itemScrutinyId AND bis.scrutinyType=:scrutinyType AND b.isDeviation='Y' AND b.isDeviationSubmitted IS NULL ");
		return jpql.toString();
	}
   public String getFinalRejectedListByItemBidId(){
		
		StringBuilder jpql= new StringBuilder();
		jpql.append(" Select b from ItemScrutinyLine b ");
		jpql.append(" LEFT JOIN FETCH b.itemScrutiny bis ");
		jpql.append( " WHERE b.itemScrutiny.itemBid.itemBidId=:itemBidId AND bis.scrutinyType=:scrutinyType AND "
				+ " b.finalScrutinyStatus='REJECTED' ");
		return jpql.toString();
	}
   
   public String getDeviatedListByItemScrutinyId(){
		
		StringBuilder jpql= new StringBuilder();
		jpql.append(" Select b from ItemScrutinyLine b ");
		jpql.append(" LEFT JOIN FETCH b.itemScrutiny bis ");
		jpql.append( " where b.itemScrutiny.itemScrutinyId=:itemScrutinyId AND b.isDeviation='Y' ");
		return jpql.toString();
	}
   
   public String getRejectedAuditingListByItemScrutinyId(){
		
		StringBuilder jpql= new StringBuilder();
		jpql.append(" Select b from ItemScrutinyLine b ");
		jpql.append(" LEFT JOIN FETCH b.itemScrutiny bis ");
		jpql.append( " where b.itemScrutiny.itemScrutinyId=:itemScrutinyId AND b.auditorStatus='CLARIFICATION' ");
		return jpql.toString();
	}
   
   public String getRejectedAuditingListByBidderId(){
		
		StringBuilder jpql= new StringBuilder();
		jpql.append(" Select b from ItemScrutinyLine b ");
		jpql.append( " where b.itemScrutiny.bidder.bidderId=:bidderId AND b.finalAuditorStatus='CLARIFICATION' ");
		return jpql.toString();
	}
   
   public String getFinalRejectedAuditingListByItemScrutinyId(){
		
		StringBuilder jpql= new StringBuilder();
		jpql.append(" Select b from ItemScrutinyLine itsc ");
		jpql.append(" LEFT JOIN FETCH b.itemScrutiny bis ");
		jpql.append(" LEFT JOIN FETCH bis.bidder b ");
		jpql.append( " where bis.bidder.bidderId=:bidderId AND b.finalAuditorStatus='REJECTED' ");
		return jpql.toString();
	}
   
   public String getNonFinalScrutinizedBidderByTahdrId(){
		
		StringBuilder jpql= new StringBuilder();
		jpql.append(" Select isl from ItemScrutinyLine isl ");
		jpql.append(" LEFT JOIN FETCH isl.itemScrutiny itemscr ");
		jpql.append(" LEFT JOIN FETCH itemscr.bidder b ");
		jpql.append(" LEFT JOIN FETCH itemscr.itemBid ib ");
		jpql.append(" WHERE b.tahdr.tahdrId=:tahdrId AND ib.status NOT IN ('DVTN','PTF') AND isl.isFinalScrutinySubmitted IS NULL AND isl.isDeviation='Y' ");
		jpql.append(" AND b.status<>'COMMFAIL' ");
		return jpql.toString();
	}
   public String getRejectItemScrtunyLingTahdrDetailId(){
		
		StringBuilder jpql= new StringBuilder();
		jpql.append(" Select isl from ItemScrutinyLine isl ");
		jpql.append(" LEFT JOIN FETCH isl.itemScrutiny itemscr ");
		jpql.append(" LEFT JOIN FETCH itemscr.bidder b ");
		jpql.append(" LEFT JOIN FETCH b.tahdr t ");
		jpql.append(" LEFT JOIN FETCH t.tahdrDetail td ");
		jpql.append(" WHERE td.tahdrDetailId=:tahdrDetailId AND td.isActive='Y' AND "
				+ " itemscr.scrutinyType='COMMSCR' "
				+ " AND isl.scrutinyPoint IS NOT NULL AND (isl.auditorStatus IS NULL OR isl.auditorStatus='CLARIFICATION') ");
		return jpql.toString();
	}
   
   public String getNonAuditedItemScrtunyLineByBidderId(){
		
		StringBuilder jpql= new StringBuilder();
		jpql.append(" Select isl from ItemScrutinyLine isl ");
		jpql.append(" LEFT JOIN FETCH isl.itemScrutiny itemscr ");
		jpql.append(" LEFT JOIN FETCH itemscr.bidder b ");
		jpql.append(" LEFT JOIN FETCH b.tahdr t ");
		jpql.append(" LEFT JOIN FETCH t.tahdrDetail td ");
		jpql.append(" WHERE b.bidderId=:bidderId AND td.isActive='Y' AND "
				+ " itemscr.scrutinyType='COMMSCR' "
				+ " AND (isl.scrutinyPoint IS NOT NULL OR isl.bidderSectionDoc IS NOT NULL) AND isl.auditorStatus IS NULL ");
		return jpql.toString();
	}
   public String getClarificationItemScrtunyLineByBidderId(){
		
		StringBuilder jpql= new StringBuilder();
		jpql.append(" Select isl from ItemScrutinyLine isl ");
		jpql.append(" LEFT JOIN FETCH isl.itemScrutiny itemscr ");
		jpql.append(" LEFT JOIN FETCH itemscr.bidder b ");
		jpql.append(" LEFT JOIN FETCH b.tahdr t ");
		jpql.append(" LEFT JOIN FETCH t.tahdrDetail td ");
		jpql.append(" WHERE b.bidderId=:bidderId AND td.isActive='Y' AND "
				+ " itemscr.scrutinyType='COMMSCR' "
				+ " AND (isl.scrutinyPoint IS NOT NULL OR isl.bidderSectionDoc IS NOT NULL ) AND isl.auditorStatus='CLARIFICATION' ");
		return jpql.toString();
	}
   
  public String getNonFinalAuditedItemScrtunyLineByBidderId(){
		
		StringBuilder jpql= new StringBuilder();
		jpql.append(" Select isl from ItemScrutinyLine isl ");
		jpql.append(" LEFT JOIN FETCH isl.itemScrutiny itemscr ");
		jpql.append(" LEFT JOIN FETCH itemscr.bidder b ");
		jpql.append(" LEFT JOIN FETCH b.tahdr t ");
		jpql.append(" LEFT JOIN FETCH t.tahdrDetail td ");
		jpql.append(" WHERE b.bidderId=:bidderId AND td.isActive='Y' AND "
				+ " itemscr.scrutinyType='COMMSCR' "
				+ " AND (isl.scrutinyPoint IS NOT NULL OR isl.bidderSectionDoc IS NOT NULL ) AND isl.finalAuditorStatus IS NULL AND isl.isDeviation='Y' AND isl.isDeviationSubmitted='Y'");
		return jpql.toString();
	}
  public String getFinalClarificationItemScrtunyLineByBidderId(){
		
		StringBuilder jpql= new StringBuilder();
		jpql.append(" Select isl from ItemScrutinyLine isl ");
		jpql.append(" LEFT JOIN FETCH isl.itemScrutiny itemscr ");
		jpql.append(" LEFT JOIN FETCH itemscr.bidder b ");
		jpql.append(" LEFT JOIN FETCH b.tahdr t ");
		jpql.append(" LEFT JOIN FETCH t.tahdrDetail td ");
		jpql.append(" WHERE b.bidderId=:bidderId AND td.isActive='Y' AND "
				+ " itemscr.scrutinyType='COMMSCR' "
				+ " AND (isl.scrutinyPoint IS NOT NULL OR isl.bidderSectionDoc IS NOT NULL ) AND isl.finalAuditorStatus='CLARIFICATION' AND isl.isDeviation='Y' AND isl.isDeviationSubmitted='Y'");
		return jpql.toString();
	}
   
   public String getNonAuditedItemScrutinyByTahdrId(){
		
		StringBuilder jpql= new StringBuilder();
		jpql.append(" Select isl from ItemScrutinyLine isl ");
		jpql.append(" LEFT JOIN  isl.itemScrutiny itemscr ");
		jpql.append(" LEFT JOIN  itemscr.bidder b ");
		jpql.append(" LEFT JOIN  b.tenderDetail t ");
		jpql.append( " where t.tahdr.tahdrId=:tahdrId AND itemscr.scrutinyType='COMMSCR' AND itemscr.preliminaryScrutinyStatus IS NULL ");
		return jpql.toString();
	}
   
   public String getItemScrtunyLineById(){
		
		StringBuilder jpql= new StringBuilder();
		jpql.append(" Select isl from ItemScrutinyLine isl ");
		jpql.append(" LEFT JOIN FETCH isl.scrutinyPoint scrp ");
		jpql.append(" LEFT JOIN FETCH isl.bidderGtp bg ");
		jpql.append(" LEFT JOIN FETCH bg.tahdrMaterialgtp tmg ");
		jpql.append(" LEFT JOIN FETCH tmg.gtp gtp ");
		jpql.append(" LEFT JOIN FETCH gtp.gtpParameterType gtpt ");
		jpql.append(" LEFT JOIN FETCH isl.fileResponse file ");
		jpql.append(" LEFT JOIN FETCH isl.itemScrutiny itemsc ");
		jpql.append(" LEFT JOIN FETCH itemsc.itemBid ib ");
		jpql.append(" LEFT JOIN FETCH itemsc.bidder b ");
		jpql.append(" LEFT JOIN FETCH isl.bidderSectionDoc bd ");
		jpql.append( " where isl.itemScrutinyLineId=:itemScrutinyLineId ");
		return jpql.toString();
	}
   @Override
   public int updateFinalScrutinyAuditorResponseInfo(ItemScrutinyLineDto dto){
	   StringBuilder jpql= new StringBuilder();
				     jpql.append(" Update ItemScrutinyLine b SET b.finalAuditorStatus=:finalAuditorStatus,b.finalAuditorComment=:finalAuditorComment");
				     jpql.append( " WHERE b.itemScrutinyLineId=:itemScrutinyLineId AND b.finalScrutinyComment IS NOT NULL AND b.finalScrutinyStatus IS NOT NULL");
		Query query=getEntityManager().createQuery(jpql.toString());
		query.setParameter("finalAuditorStatus",dto.getFinalAuditorStatus());
		query.setParameter("finalAuditorComment",dto.getFinalAuditorComment());
		query.setParameter("itemScrutinyLineId",dto.getItemScrutinyLineId());
		int count= query.executeUpdate();
		return count;
   }
   
}
