package com.novelerp.eat.dao.impl;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.core.dao.impl.AbstractJpaDAO;
import com.novelerp.eat.dao.ItemScrutinyDao;
import com.novelerp.eat.dto.ItemScrutinyDto;
import com.novelerp.eat.entity.ItemScrutiny;

/**
 * @author Aman Sahu
 *
 */
@Repository
public class ItemScrutinyDaoImpl extends AbstractJpaDAO<ItemScrutiny, ItemScrutinyDto> implements ItemScrutinyDao {
	
	@PostConstruct
	public void postConstruct() {
		setClazz(ItemScrutiny.class, ItemScrutinyDto.class);
	}
	
public String getTechnicalItemscrutiny(){
		StringBuilder jpql= new StringBuilder();
		jpql.append(" Select b from ItemScrutiny b ");
		/*jpql.append( " where b.bidder.bidderId=:bidderId AND b.itemBid.itemBidId=:itemBidId And b.scrutinyType='TECHSCR or b.scrutinyType='COMMSCR'");*/
		jpql.append( " where b.itemBid.itemBidId=:itemBidId And b.scrutinyType='TECHSCR' ");
		return jpql.toString();
	}

public String getItemscrutinyByItemBidId(){
	StringBuilder jpql= new StringBuilder();
	jpql.append(" Select b from ItemScrutiny b ");
	jpql.append(" LEFT JOIN FETCH b.scrutinyFile sf ");
	jpql.append( " where b.itemBid.itemBidId=:itemBidId And b.scrutinyType='TECHSCR' ");
	return jpql.toString();
}

public String getItemscrutinyByBidderIdAndTadrMaterialId(){
	StringBuilder jpql= new StringBuilder();
	jpql.append(" Select b from ItemScrutiny b ");
	jpql.append(" INNER JOIN FETCH b.scrutinyFile sf ");
	jpql.append(" WHERE b.itemBid.tahdrMaterial.tahdrMaterialId=:tahdrMaterialId AND b.bidder.bidderId=:bidderId And b.scrutinyType='TECHSCR' ");
	return jpql.toString();
}

public String getItemscrutinyByBidderId(){
	StringBuilder jpql= new StringBuilder();
	jpql.append(" Select b from ItemScrutiny b ");
	jpql.append(" LEFT JOIN FETCH b.scrutinyFile sf ");
	jpql.append(" WHERE b.bidder.bidderId=:bidderId And b.scrutinyType='COMMSCR' ");
	return jpql.toString();
}

public String getNotUploadedScrutinyFileByTahdrId(){
	StringBuilder jpql= new StringBuilder();
	jpql.append(" Select b from ItemScrutiny b ");
	jpql.append(" WHERE b.bidder.tahdr.tahdrId=:tahdrId And (b.isScrutinySubmitted IS NULL OR b.isScrutinySubmitted='N')");
	return jpql.toString();
}

public String getNotUploadedFinalScrutinyFileByTahdrId(){
	StringBuilder jpql= new StringBuilder();
	jpql.append(" Select b from ItemScrutiny b ");
	jpql.append(" WHERE b.bidder.tahdr.tahdrId=:tahdrId And (b.isFinalScrutinySubmitted IS NULL OR b.isFinalScrutinySubmitted='N') ");
	jpql.append(" AND b.bidder.status IN ('FTBP','FTBF','FCBP','FCBF') ");
	return jpql.toString();
}

public String getCommercialScrutinyByBidderId(){
	StringBuilder jpql= new StringBuilder();
	jpql.append(" Select b from ItemScrutiny b ");
	jpql.append( " where b.bidder.bidderId=:bidderId And b.scrutinyType=:scrutinyType ");
	return jpql.toString();
}

public String getTechnicalScrutinyByBidderId(){
	StringBuilder jpql= new StringBuilder();
	jpql.append(" Select b from ItemScrutiny b ");
	jpql.append(" RIGHT JOIN FETCH b.itemBid ib ");
	jpql.append(" LEFT JOIN FETCH ib.tahdrMaterial tm ");
	jpql.append(" LEFT JOIN FETCH tm.material tmm ");
	jpql.append(" Where b.bidder.bidderId=:bidderId And b.scrutinyType=:scrutinyType order by tm.tahdrMaterialId ");
	return jpql.toString();
}

public String getItemScrutinyByTahdrId(){
	StringBuilder jpql= new StringBuilder();
	jpql.append(" Select s from ItemScrutiny s ");
	jpql.append(" LEFT JOIN FETCH s.bidder b ");
	jpql.append(" LEFT JOIN FETCH b.createdBy cr");
	jpql.append(" LEFT JOIN FETCH s.itemBid ib ");
	jpql.append(" LEFT JOIN FETCH ib.tahdrMaterial tm ");
	jpql.append(" LEFT JOIN FETCH tm.material tmm ");
	jpql.append( " where b.tahdr.tahdrId=:tahdrId AND s.scrutinyType=:scrutinyType");
	return jpql.toString();
}


public String getCommercialItemscrutiny(){
	StringBuilder jpql= new StringBuilder();
	jpql.append(" Select b from ItemScrutiny b ");
	jpql.append( " where  b.scrutinyType='COMMSCR' And b.bidder.bidderId=:bidderId ");
	return jpql.toString();
}

public String getCommercialItemscrutinyByTahdrId(){
	StringBuilder jpql= new StringBuilder();
	jpql.append(" Select b from ItemScrutiny b ");
	jpql.append(" LEFT JOIN FETCH b.bidder bd ");
	jpql.append(" LEFT JOIN FETCH bd.tahdr t ");
	jpql.append( " where  b.scrutinyType='COMMSCR' And t.tahdrId=:tahdrId ");
	return jpql.toString();
}

public String getTechnicalItemscrutinyByStatus(){
	StringBuilder jpql= new StringBuilder();
	jpql.append(" Select b from ItemScrutiny b ");
	jpql.append( " where  b.scrutinyType='TECHSCR' And b.bidder.bidderId=:bidderId "
			+ "AND upper(b.preliminaryScrutinyStatus)=upper('APPROVED') OR upper(b.preliminaryScrutinyStatus)=upper('DEVIATION')");
	return jpql.toString();
}

/*public String getItemscrutinyForPreliminaryScrutiny(){
	
	StringBuilder jpql= new StringBuilder();
	jpql.append(" Select b from ItemScrutiny b ");
	jpql.append( " LEFT JOIN FETCH b.itemBid it");
	jpql.append( " LEFT JOIN FETCH b.createdBy c");
	jpql.append( " LEFT JOIN FETCH b.updatedBy u");
	jpql.append( " LEFT JOIN FETCH b.partner p");
	jpql.append( " LEFT JOIN FETCH b.bidder bd");
	jpql.append( " LEFT JOIN FETCH b.scrutinyFile sf");
	jpql.append( " where b.itemScrutinyId=:itemScrutinyId");
	return jpql.toString();
}*/
/*public String getItemscrutinyForPreliminaryScrutiny(){
	
	StringBuilder jpql= new StringBuilder();
	jpql.append(" Update  ItemScrutiny b SET b.preliminaryScrutinyComment=:comment, b.preliminaryScrutinyStatus=:status ");
	jpql.append( " where b.itemScrutinyId=:itemScrutinyId");
	return jpql.toString();
}*/

	@Override
	public int getItemscrutinyForPreliminaryScrutiny(Long itemScrutinyId,String comment,String status) {
		StringBuilder jpql= new StringBuilder();
		jpql.append(" Update ItemScrutiny b SET b.preliminaryScrutinyComment=:comment, b.preliminaryScrutinyStatus=:status,b.isScrutinySubmitted='N' ");
		jpql.append( " where b.itemScrutinyId=:itemScrutinyId ");
		if(status.toUpperCase().equals("APPROVED")){
			jpql.append( " AND NOT EXISTS (SELECT isl FROM ItemScrutinyLine isl WHERE isl.itemScrutiny.itemScrutinyId=:itemScrutinyId AND isl.isDeviation IN (null,'Y')) ");
		}else if(status.toUpperCase().equals("DEVIATION")){
			jpql.append( " AND EXISTS (SELECT isl FROM ItemScrutinyLine isl WHERE isl.itemScrutiny.itemScrutinyId=:itemScrutinyId AND isl.isDeviation IN ('Y')) ");
		}
		Query query=getEntityManager().createQuery(jpql.toString());
		query.setParameter("itemScrutinyId", itemScrutinyId);
		query.setParameter("comment", comment);
		query.setParameter("status", status);
		int count= query.executeUpdate();
		return count;
	}
	
	@Override
	public int resetItemscrutinyForPreliminaryScrutiny(Long itemScrutinyId) {
		StringBuilder jpql= new StringBuilder();
		jpql.append(" Update ItemScrutiny b SET b.preliminaryScrutinyComment=null, b.preliminaryScrutinyStatus=null,b.isScrutinySubmitted='N' ");
		jpql.append( " where b.itemScrutinyId=:itemScrutinyId ");
		
		Query query=getEntityManager().createQuery(jpql.toString());
		query.setParameter("itemScrutinyId", itemScrutinyId);
		
		int count= query.executeUpdate();
		return count;
	}
	
	@Override
	public int resetItemscrutinyForFinalScrutiny(Long itemScrutinyId) {
		StringBuilder jpql= new StringBuilder();
		jpql.append(" Update ItemScrutiny b SET b.finalScrutinyComment=null, b.finalScrutinyStatus=null,b.isFinalScrutinySubmitted='N' ");
		jpql.append( " where b.itemScrutinyId=:itemScrutinyId ");
		
		Query query=getEntityManager().createQuery(jpql.toString());
		query.setParameter("itemScrutinyId", itemScrutinyId);
		
		int count= query.executeUpdate();
		return count;
	}
	
	@Override
	public int saveBidderDeviationResposne(Long itemScrutinyId,String status,Long fileId){
		StringBuilder jpql= new StringBuilder();
		jpql.append(" Update ItemScrutiny b SET b.bidderDeviationStatus=:status,b.scrutinyFile.attachmentId=:fileId ");
		jpql.append( " WHERE b.itemScrutinyId=:itemScrutinyId AND NOT EXISTS(SELECT isl FROM ItemScrutinyLine isl "
				       + "    WHERE isl.itemScrutiny.itemScrutinyId=:itemScrutinyId AND isl.isDeviationSubmitted IS NULL AND isl.isDeviation='Y') ");
		
		Query query=getEntityManager().createQuery(jpql.toString());
		query.setParameter("itemScrutinyId", itemScrutinyId);
		query.setParameter("status", status);
		query.setParameter("fileId", fileId);
		int count= query.executeUpdate();
		return count;
	}
	
	@Override
	   public int updateFinalScrutinyResponseInfo(ItemScrutinyDto itemScrutinyDto){
		   StringBuilder jpql= new StringBuilder();
					     jpql.append(" Update ItemScrutiny b SET b.finalScrutinyComment=:finalScrutinyComment,b.finalScrutinyStatus=:finalScrutinyStatus,b.isFinalScrutinySubmitted='N' ");
					     jpql.append( " WHERE b.itemScrutinyId=:itemScrutinyId ");
					     if(itemScrutinyDto.getFinalScrutinyStatus().toUpperCase().equals("APPROVED")){
								jpql.append( " AND NOT EXISTS (SELECT isl FROM ItemScrutinyLine isl INNER JOIN isl.itemScrutiny isc INNER JOIN isc.bidder bid INNER JOIN bid.tahdr t "
										+ "WHERE isl.itemScrutiny.itemScrutinyId=:itemScrutinyId AND (upper(isl.finalScrutinyStatus) IN (NULL,'REJECTED') OR t.isAuditing='Y')) ");
							}
			Query query=getEntityManager().createQuery(jpql.toString());
			query.setParameter("itemScrutinyId", itemScrutinyDto.getItemScrutinyId());
			query.setParameter("finalScrutinyComment", itemScrutinyDto.getFinalScrutinyComment());
			query.setParameter("finalScrutinyStatus", itemScrutinyDto.getFinalScrutinyStatus());
			int count= query.executeUpdate();
			return count;
	   }
	
	@Override
	   public int updateFinalScrutinyAuditorResponseInfo(ItemScrutinyDto dto) {
		   StringBuilder jpql= new StringBuilder();
					     jpql.append(" Update ItemScrutiny b SET b.finalAuditorStatus=:finalAuditorStatus,b.finalAuditorComment=:finalAuditorComment,b.isFinalAudited=:isFinalAudited ");
					     jpql.append( " WHERE b.itemScrutinyId=:itemScrutinyId AND b.finalScrutinyComment IS NOT NULL AND b.finalScrutinyStatus IS NOT NULL");
			Query query=getEntityManager().createQuery(jpql.toString());
			query.setParameter("finalAuditorStatus",dto.getFinalAuditorStatus());
			query.setParameter("finalAuditorComment",dto.getFinalAuditorComment());
			query.setParameter("isFinalAudited",dto.getIsFinalAudited());
			query.setParameter("itemScrutinyId",dto.getItemScrutinyId());
			int count= query.executeUpdate();
			return count;
	   }
	
	public String getFinalResp(){
		StringBuilder jpql= new StringBuilder();
		jpql.append(" Select b from ItemScrutiny b ");
		jpql.append( " where b.itemScrutinyId=:itemScrutinyId");
		return jpql.toString();
	}
	
   public String getNonScrutinedBidderByTahdrIdResp(){
		StringBuilder jpql= new StringBuilder();
		jpql.append(" Select b from ItemScrutiny b ");
		jpql.append(" LEFT JOIN FETCH b.bidder bd ");
		jpql.append( " where bd.tahdrId=:tahdrId AND b.bidderDeviationStatus='Y' AND b.finalScrutinyStatus IS NULL ");
		return jpql.toString();
	}
	
       public String getItemScrutinyByItemScrutinyId(){
			StringBuilder jpql= new StringBuilder();
			jpql.append(" Select DISTINCT itsc from ItemScrutiny itsc ");
			jpql.append(" LEFT JOIN FETCH itsc.bidder b ");
			jpql.append(" LEFT JOIN FETCH b.createdBy u ");
			jpql.append(" LEFT JOIN FETCH b.tahdr t ");
			jpql.append(" where itsc.itemScrutinyId=:itemScrutinyId ");
			return jpql.toString();
		}
       
       public int revokeDeviationConfirmation(Long itemScrutinyId){
    	   StringBuilder jpql= new StringBuilder();
   		jpql.append(" Update ItemScrutiny b SET b.bidderDeviationStatus=null,b.scrutinyFile.attachmentId=null ");
   		jpql.append( " WHERE b.itemScrutinyId =:itemScrutinyId ");
   		
   		Query query=getEntityManager().createQuery(jpql.toString());
   		query.setParameter("itemScrutinyId", itemScrutinyId);
   		
   		int count= query.executeUpdate();
   		return count;
       }
       @SuppressWarnings("unchecked")
       @Override
       public List<Object> getScrutinyEngrEmailList(Long tahdrId){
   		StringBuilder jpql= new StringBuilder();
   		jpql.append(" Select distinct cb.email FROM t_item_scrutiny its ");
   		jpql.append(" Left join ad_user cb on cb.ad_user_id=its.createdBy ");
   		jpql.append(" Left join t_bidder b on b.t_bidder_id=its.t_bidder_id ");
   		jpql.append(" Left join t_tahdr t on t.t_tahdr_id=b.t_tahdr_id ");
   		jpql.append(" WHERE t.t_tahdr_id=:tahdrId");
   		Query query=getEntityManager().createNativeQuery(jpql.toString());
		query.setParameter("tahdrId", tahdrId);
		List<Object> emailList= query.getResultList();
		return emailList;
   	}
       public String getPreliminaryNotAuditedList(){
   		StringBuilder jpql= new StringBuilder();
   		jpql.append(" Select b from ItemScrutiny b ");
   		jpql.append(" WHERE b.bidder.tahdr.tahdrId=:tahdrId AND b.isAudited IS NULL AND b.scrutinyType='COMMSCR'");
   		return jpql.toString();
   	}
       
       public String getPreliminaryClarifiedAuditedList(){
      		StringBuilder jpql= new StringBuilder();
      		jpql.append(" Select b from ItemScrutiny b ");
      		jpql.append(" WHERE b.bidder.tahdr.tahdrId=:tahdrId AND (b.preliminaryAuditorStatus IS NULL OR b.preliminaryAuditorStatus ='CLARIFICATION')  AND b.scrutinyType='COMMSCR'");
      		return jpql.toString();
      	}
       
       public String getFinalClarifiedAuditedList(){
     		StringBuilder jpql= new StringBuilder();
     		jpql.append(" Select b from ItemScrutiny b ");
     		jpql.append(" WHERE b.bidder.tahdr.tahdrId=:tahdrId AND (b.finalAuditorStatus IS NULL AND b.finalAuditorStatus ='CLARIFICATION')  AND b.scrutinyType='COMMSCR'");
     		return jpql.toString();
     	}
       
       public String getFinalNotAuditedList(){
     		StringBuilder jpql= new StringBuilder();
     		jpql.append(" Select b from ItemScrutiny b ");
     		jpql.append(" WHERE b.bidder.tahdr.tahdrId=:tahdrId AND b.isFinalAudited IS NULL AND b.scrutinyType='COMMSCR'"
     				+ " AND b.bidderDeviationStatus ='Y' ");
     		jpql.append("AND b.status NOT IN ('PTF','COMMFAIL','FCBF')");
     		return jpql.toString();
     	}
   
   public String getOpenedTechDvtnBid(){
		StringBuilder jpql= new StringBuilder();
		jpql.append(" Select is FROM ItemScrutiny is ")
		.append(" LEFT JOIN FETCH is.itemBid ib ")
		.append(" LEFT JOIN FETCH ib.bidder bd ")
		.append(" LEFT JOIN FETCH bd.tahdr t ")
		.append(" LEFT JOIN FETCH t.tahdrDetail td ")
		.append(" LEFT JOIN FETCH ib.tahdrMaterial tm ")
		.append(" LEFT JOIN FETCH tm.material tmm ")
		.append(" LEFT JOIN FETCH tm.tahdrDetail td1 ")
		.append(" LEFT JOIN FETCH is.scrutinyFile sf ")
		.append(" WHERE bd.bidderId=:bidderId "
				+ " AND td.isActive='Y' "
				+ " AND td.tahdrDetailId=td1.tahdrDetailId "
				+ " AND tm.tahdrMaterialId=:tahdrMaterialId "
				+ " AND bd.status=:bidderStatus "
				+ " AND ib.status=:itemBidStatus "
				+ " AND is.scrutinyType="+AppBaseConstant.TECHNICAL_SCRUTINY);
		return jpql.toString();
	}
   
   public String getOpenedCommercialDvtnBid(){
		StringBuilder jpql= new StringBuilder();
		jpql.append(" Select is FROM ItemScrutiny is ")
		.append(" LEFT JOIN FETCH is.itemBid ib ")
		.append(" LEFT JOIN FETCH ib.bidder bd ")
		.append(" LEFT JOIN FETCH bd.tahdr t ")
		.append(" LEFT JOIN FETCH t.tahdrDetail td ")
		.append(" LEFT JOIN FETCH ib.tahdrMaterial tm ")
		.append(" LEFT JOIN FETCH tm.material tmm ")
		.append(" LEFT JOIN FETCH tm.tahdrDetail td1 ")
		.append(" LEFT JOIN FETCH is.scrutinyFile sf ")
		.append(" WHERE bd.bidderId=:bidderId "
				+ " AND td.isActive='Y' "
				+ " AND td.tahdrDetailId=td1.tahdrDetailId "
				+ " AND bd.status=:bidderStatus "
				+ " AND is.scrutinyType="+AppBaseConstant.COMMERCIAL_SCRUTINY);
		return jpql.toString();
	}
}

