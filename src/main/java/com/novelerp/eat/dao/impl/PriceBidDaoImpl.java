/**
 * @author Ankush
 */
package com.novelerp.eat.dao.impl;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.core.dao.impl.AbstractJpaDAO;
import com.novelerp.eat.dao.PriceBidDao;
import com.novelerp.eat.dto.PriceBidDto;
import com.novelerp.eat.entity.PriceBid;

@Repository
public class PriceBidDaoImpl extends AbstractJpaDAO<PriceBid, PriceBidDto> implements PriceBidDao {

	@PostConstruct
	void init(){
		setClazz(PriceBid.class, PriceBidDto.class);
	}
	
	private String copyOldBidsToTempQuery(){
		StringBuilder sb= new StringBuilder();
		sb.append(" INSERT INTO t_temp_price_bid ( created,isactive,updated,ex_group_price_rate,"
				+ "excise_duty_amount,excise_duty_rate,fdd_amount,fdd_in_words,fdd_rate,"
				+ "freight_charge_rate,is_annexure_c1,is_confirmed,is_matched,is_quashed,"
				+ "offered_quantity,status,tax_amount,tax_rate,tic_rate,total_ex_group_price,"
				+ "total_freight_charge,total_tic,createdby,m_bpartner_id,updatedby,"
				+ "digitally_signed_doc_id,t_item_bid_id,refered_item_bid_id,t_price_bid_id,"
				+ " total_tax, total_igst,total_cgst,total_sgst,sgst_amount,igst_amount,cgst_amount,fdd_amount_with_gst,fdd_rate_with_gst,"
				+ " m_material_specification_id,sgst,igst,cgst,previous_ffd_amount_with_gst,is_previous_bid,bid_remark )");
        sb.append(" SELECT created,isactive,updated,ex_group_price_rate,"
				+ "excise_duty_amount,excise_duty_rate,fdd_amount,fdd_in_words,fdd_rate,"
				+ "freight_charge_rate,is_annexure_c1,is_confirmed,is_matched,is_quashed,"
				+ "offered_quantity,status,tax_amount,tax_rate,tic_rate,total_ex_group_price,"
				+ "total_freight_charge,total_tic,createdby,m_bpartner_id,updatedby,"
				+ "digitally_signed_doc_id,t_item_bid_id,refered_item_bid_id,t_price_bid_id, "
				+ " total_tax, total_igst,total_cgst,total_sgst,sgst_amount,igst_amount,cgst_amount,fdd_amount_with_gst,fdd_rate_with_gst,"
				+ " m_material_specification_id,sgst,igst,cgst,previous_ffd_amount_with_gst,is_previous_bid,bid_remark ");
        sb.append(" FROM t_price_bid PB ");
		sb.append(" WHERE PB.t_item_bid_id=?");
		
		/*sb.append(" SELECT * INTO T_TEMP_PRICE_BID FROM T_PRICE_BID PB ");
		sb.append(" WHERE PB.t_item_bid_id=");*/
		
		return sb.toString();
	}
	
	private String copyPreviousBidsToTempQuery(){
		StringBuilder sb= new StringBuilder();
		sb.append(" INSERT INTO t_temp_price_bid ( created,isactive,updated,ex_group_price_rate,"
				+ "excise_duty_amount,excise_duty_rate,fdd_amount,fdd_in_words,fdd_rate,"
				+ "freight_charge_rate,is_annexure_c1,is_confirmed,is_matched,is_quashed,"
				+ "offered_quantity,status,tax_amount,tax_rate,tic_rate,total_ex_group_price,"
				+ "total_freight_charge,total_tic,createdby,m_bpartner_id,updatedby,"
				+ "digitally_signed_doc_id,t_item_bid_id,refered_item_bid_id,t_price_bid_id,"
				+ " total_tax, total_igst,total_cgst,total_sgst,sgst_amount,igst_amount,cgst_amount,fdd_amount_with_gst,fdd_rate_with_gst,"
				+ " m_material_specification_id,sgst,igst,cgst,previous_ffd_amount_with_gst,is_previous_bid,bid_remark )");
        sb.append(" SELECT created,isactive,updated,ex_group_price_rate,"
				+ "excise_duty_amount,excise_duty_rate,fdd_amount,fdd_in_words,fdd_rate,"
				+ "freight_charge_rate,is_annexure_c1,is_confirmed,is_matched,is_quashed,"
				+ "offered_quantity,status,tax_amount,tax_rate,tic_rate,total_ex_group_price,"
				+ "total_freight_charge,total_tic,createdby,m_bpartner_id,updatedby,"
				+ "digitally_signed_doc_id,t_item_bid_id,refered_item_bid_id,t_price_bid_id, "
				+ " total_tax, total_igst,total_cgst,total_sgst,sgst_amount,igst_amount,cgst_amount,fdd_amount_with_gst,fdd_rate_with_gst,"
				+ " m_material_specification_id,sgst,igst,cgst,previous_ffd_amount_with_gst,'Y',? ");
        sb.append(" FROM t_price_bid PB ");
		sb.append(" WHERE PB.t_item_bid_id=?");
		return sb.toString();
	}
	
	private String copyEncryptedBidsToTempQuery(){
		StringBuilder sb= new StringBuilder();
		sb.append(" INSERT INTO t_temp_price_bid ( created,isactive,updated,ex_group_price_rate,"
				+ "excise_duty_amount,excise_duty_rate,fdd_amount,fdd_in_words,fdd_rate,"
				+ "freight_charge_rate,is_annexure_c1,is_confirmed,is_matched,is_quashed,"
				+ "offered_quantity,status,tax_amount,tax_rate,tic_rate,total_ex_group_price,"
				+ "total_freight_charge,total_tic,createdby,m_bpartner_id,updatedby,"
				+ "digitally_signed_doc_id,t_item_bid_id,refered_item_bid_id,t_price_bid_id,"
				+ " total_tax, total_igst,total_cgst,total_sgst,sgst_amount,igst_amount,cgst_amount,fdd_amount_with_gst,fdd_rate_with_gst,"
				+ " m_material_specification_id,sgst,igst,cgst,previous_ffd_amount_with_gst,is_previous_bid,bid_remark )");
        sb.append(" SELECT PB.created,PB.isactive,PB.updated,PB.ex_group_price_rate,"
				+ "PB.excise_duty_amount,PB.excise_duty_rate,PB.fdd_amount,PB.fdd_in_words,PB.fdd_rate,"
				+ "PB.freight_charge_rate,PB.is_annexure_c1,PB.is_confirmed,PB.is_matched,PB.is_quashed,"
				+ "PB.offered_quantity,PB.status,PB.tax_amount,PB.tax_rate,PB.tic_rate,PB.total_ex_group_price,"
				+ "PB.total_freight_charge,PB.total_tic,PB.createdby,PB.m_bpartner_id,PB.updatedby,"
				+ "PB.digitally_signed_doc_id,PB.t_item_bid_id,PB.refered_item_bid_id,PB.t_price_bid_id, "
				+ " PB.total_tax, PB.total_igst,PB.total_cgst,PB.total_sgst,PB.sgst_amount,PB.igst_amount,PB.cgst_amount,PB.fdd_amount_with_gst,PB.fdd_rate_with_gst,"
				+ " PB.m_material_specification_id,PB.sgst,PB.igst,PB.cgst,PB.previous_ffd_amount_with_gst,'Y',? ");
        sb.append(" FROM t_price_bid PB ");
        sb.append(" INNER JOIN  t_item_bid ib on ib.t_item_bid_id=PB.t_item_bid_id ");
        sb.append(" INNER JOIN  t_bidder b on b.t_bidder_id=ib.t_bidder_id ");
		sb.append(" WHERE b.t_tahdr_id=?");
		
		return sb.toString();
	}
	
	private String deleteOldBidsQuery(){
		StringBuilder sb= new StringBuilder();
		sb.append(" DELETE FROM T_PRICE_BID PB ");
		sb.append(" WHERE PB.t_item_bid_id=? ");
		sb.append(" AND PB.is_quashed='Y' ");
		return sb.toString();
	}
	
	private String markOldBidsToQuashed(){
		StringBuilder sb= new StringBuilder();
		sb.append(" UPDATE T_PRICE_BID PB ");
		sb.append(" SET IS_QUASHED='Y' ");
		sb.append(" WHERE pb.t_item_bid_id=? ");
		return sb.toString();
	}
	
	private String updateReqDocsQuery(){
		StringBuilder sb= new StringBuilder();
		sb.append(" UPDATE T_BIDDER_SECTION_DOC BSD ");
		sb.append(" SET T_PRICE_BID_ID=? ");
		sb.append(" WHERE BSD.T_PRICE_BID_ID=? ");
		return sb.toString();
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public int createRevisedBid(Long itemBidId,Long priceBidId){
			
			int count=copyOldBids(itemBidId);
			
			count=markOldBidsToQuashed(itemBidId);
			
			/*count=updateReqDocsForRevisedBid(priceBidId, 0l);
			
			count=deleteOldBids(itemBidId);*/
			
			return count;
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public int createPreviousBid(Long itemBidId, Long priceBidId,String bidRemark) {
		int count=copyPreviousBids(itemBidId,bidRemark);
		
		/*count=markOldBidsToQuashed(itemBidId);*/

		return count;
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public int createEncryptedBid(Long tahdrId,String bidRemark) {
		int count=copyEncrytedBids(tahdrId,bidRemark);

		return count;
	}
	

	@Transactional(propagation=Propagation.REQUIRED)
	private int copyOldBids(Long itemBidId){
		String jpql=copyOldBidsToTempQuery();
		Query query = getEntityManager().createNativeQuery(jpql);
		query.setParameter(1, itemBidId);
		int count= query.executeUpdate();
		return count;
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	private int copyPreviousBids(Long itemBidId,String bidRemark){
		String jpql=copyPreviousBidsToTempQuery();
		Query query = getEntityManager().createNativeQuery(jpql);
		query.setParameter(1, bidRemark);
		query.setParameter(2, itemBidId);
		int count= query.executeUpdate();
		return count;
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	private int copyEncrytedBids(Long tahdrId,String bidRemark){
		String jpql=copyEncryptedBidsToTempQuery();
		Query query = getEntityManager().createNativeQuery(jpql);
		query.setParameter(1, bidRemark);
		query.setParameter(2, tahdrId);
		int count= query.executeUpdate();
		return count;
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public int deleteOldBids(Long itemBidId){
		String jpql=deleteOldBidsQuery();
		Query query = getEntityManager().createNativeQuery(jpql);
		query.setParameter(1, itemBidId);
		int count= query.executeUpdate();
		return count;
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	private int markOldBidsToQuashed(Long itemBidId){
		String jpql=markOldBidsToQuashed();
		Query query = getEntityManager().createNativeQuery(jpql);
		query.setParameter(1, itemBidId);
		int count= query.executeUpdate();
		return count;
	}
	
	/* (non-Javadoc)
	 * @see com.novelerp.eat.dao.PriceBidDao#updateReqDocsForRevisedBid(java.lang.Long, java.lang.Long)
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public int updateReqDocsForRevisedBid(Long priceBidId, Long value) {
		String jpql=updateReqDocsQuery();
		Query query = getEntityManager().createNativeQuery(jpql);
		query.setParameter(1, value);
		query.setParameter(2, priceBidId);
		int count= query.executeUpdate();
		return count;
	}
	
	public String getPriceBid(){
		StringBuilder jpql= new StringBuilder();
		jpql.append(" Select pb from PriceBid pb ")
		.append(" LEFT JOIN FETCH pb.digiSignedDoc dsd ")
		.append(" INNER JOIN FETCH pb.itemBid ib ")
		.append(" INNER JOIN FETCH ib.tahdrMaterial tm ")
		.append(" INNER JOIN FETCH ib.bidder bdr ")
		.append(" INNER JOIN FETCH pb.partner bp ")
		.append(" WHERE tm.tahdrMaterialId=:tahdrMaterialId ")
		.append(" and bp.bPartnerId=:partnerId and pb.materialSpecification.materialSpecificationId is null ");
		return jpql.toString();
	}
	
	public String getPriceBidByTahdrMaterialIdAndBidderId(){
		StringBuilder jpql=new StringBuilder();
		jpql.append(" Select pb from PriceBid pb ");
		jpql.append(" INNER JOIN FETCH pb.digiSignedDoc dsd ");
		jpql.append(" WHERE pb.itemBid.bidder.bidderId=:bidderId "
				+ " AND pb.itemBid.tahdrMaterial.tahdrMaterialId=:tahdrMaterialId "
				+ " AND pb.itemBid.bidder.tahdr.tahdrStatusCode NOT IN ('PBSCH','TCOP','DBSCH','DBOP','VO','PU')");
		return jpql.toString();
	}
	
	public String getPriceBidForParts(){
		StringBuilder jpql= new StringBuilder();
		jpql.append(" Select pb from PriceBid pb ")
		.append(" LEFT JOIN FETCH pb.digiSignedDoc dsd ")
		.append(" INNER JOIN FETCH pb.itemBid ib ")
		.append(" INNER JOIN FETCH ib.tahdrMaterial tm ")
		.append(" INNER JOIN FETCH pb.materialSpecification ms ")
		.append(" INNER JOIN FETCH ms.material bom ")
		.append(" INNER JOIN FETCH ms.specification m ")
		.append(" LEFT JOIN FETCH m.uom uom ")
		.append(" LEFT JOIN FETCH m.hsnCode hsn ")
		.append(" INNER JOIN FETCH ib.bidder bdr ")
		.append(" INNER JOIN FETCH pb.partner bp ")
		.append(" WHERE tm.tahdrMaterialId=:tahdrMaterialId ")
		.append(" and bp.bPartnerId=:partnerId and pb.materialSpecification.materialSpecificationId is not null ");
		return jpql.toString();
	}
	
	public String getPriceBidAndParts(){
		StringBuilder jpql= new StringBuilder();
		jpql.append(" Select pb from PriceBid pb ")
		.append(" LEFT JOIN FETCH pb.digiSignedDoc dsd ")
		.append(" LEFT JOIN FETCH pb.materialSpecification ms ")
		.append(" LEFT JOIN FETCH pb.referedItemBid rib ")
		.append(" INNER JOIN FETCH pb.itemBid ib ")
		.append(" INNER JOIN FETCH pb.partner bp ")
		.append(" INNER JOIN FETCH ib.bidder bdr ")
		.append(" INNER JOIN FETCH bdr.tahdr t ")
		.append(" INNER JOIN FETCH t.tahdrDetail td ")
		.append(" WHERE t.tahdrId=:tahdrId and td.isActive='Y' ");
		return jpql.toString();
	}
	
	public String getPartsForPartner(){
		StringBuilder jpql= new StringBuilder();
		jpql.append(" Select pb from PriceBid pb ")
		.append(" LEFT JOIN FETCH pb.digiSignedDoc dsd ")
		.append(" LEFT JOIN FETCH pb.materialSpecification ms ")
		.append(" LEFT JOIN FETCH pb.referedItemBid rib ")
		.append(" INNER JOIN FETCH pb.itemBid ib ")
		.append(" INNER JOIN FETCH pb.partner bp ")
		.append(" INNER JOIN FETCH ib.bidder bdr ")
		.append(" INNER JOIN FETCH bdr.tahdr t ")
		.append(" INNER JOIN FETCH t.tahdrDetail td ")
		.append(" WHERE t.tahdrId=:tahdrId and td.isActive='Y' and bp.bPartnerId=:partnerId and ib.itemBidId=:itemBidId")
		.append(" and pb.materialSpecification.materialSpecificationId is not null ");
		return jpql.toString();
	}
	
	public String updateItemPBByPartsQuery(){
		StringBuilder jpql= new StringBuilder();
		jpql.append(" UPDATE (SELECT PB.* FROM T_PRICE_BID PB ")
		.append(" INNER JOIN t_item_bid ib1 ON pb.t_item_bid_id = ib1.t_item_bid_id ")
		.append(" INNER JOIN t_tahdr_material tm ON tm.t_tahdr_material_id = ib1.t_tahdr_material_id and tm.material_type_code='"+AppBaseConstant.TAHDR_MATERIAL_TYPE_CODE_BOM+"' ) t ")
		.append(" SET (EX_GROUP_PRICE_RATE, FREIGHT_CHARGE_RATE, TIC_RATE, FDD_RATE, ")
		.append("  TOTAL_EX_GROUP_PRICE, TOTAL_FREIGHT_CHARGE, TOTAL_TIC, FDD_AMOUNT ) = ")
		.append("( SELECT tegp,tfc,ttic,fdda,tegp*qty,tfc*qty,ttic*qty,fdda*qty from ")
		.append(" (SELECT SUM(pb1.TOTAL_EX_GROUP_PRICE) tegp,SUM(case pb1.TOTAL_FREIGHT_CHARGE when null then 0 else pb1.TOTAL_FREIGHT_CHARGE end) tfc, ")
		.append(" SUM(pb1.TOTAL_TIC) ttic,SUM(pb1.FDD_AMOUNT) fdda, ") 
		.append(" pb2.offered_quantity qty from t_price_bid pb2 ") 
		.append(" INNER JOIN (select t_item_bid_id,t_price_bid_id,m_material_specification_id,EX_GROUP_PRICE_RATE, FREIGHT_CHARGE_RATE, TIC_RATE, FDD_RATE, ")
		.append(" FDD_AMOUNT, TOTAL_EX_GROUP_PRICE, TOTAL_FREIGHT_CHARGE, TOTAL_TIC from t_price_bid where t_item_bid_id=? ")
		.append(" and m_material_specification_id is not null) pb1 on pb1.t_item_bid_id=pb2.t_item_bid_id and pb2.m_material_specification_id is null ")
		.append(" group by pb2.offered_quantity) tbl) ")
		.append(" WHERE t.t_item_bid_id=? and t.m_material_specification_id is null ");
		return jpql.toString();
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public int updateItemPBByParts(Long itemBidId) {
		String jpql=updateItemPBByPartsQuery();
		Query query = getEntityManager().createNativeQuery(jpql);
		query.setParameter(1, itemBidId);
		query.setParameter(2, itemBidId);
		int count= query.executeUpdate();
		return count;
	}
	
	public String verifyPriceBid(){
		StringBuilder jpql= new StringBuilder();
		jpql.append(" Select pb from PriceBid pb ")
		.append(" LEFT JOIN FETCH pb.bidderSecDoc pbsd ")
		.append(" LEFT JOIN FETCH pb.materialSpecification ms ")
		.append(" LEFT JOIN FETCH pb.digiSignedDoc dsd ")
		.append(" INNER JOIN FETCH pb.itemBid ib ")
		.append(" INNER JOIN FETCH ib.tahdrMaterial tm ")
		.append(" INNER JOIN FETCH ib.bidder bdr ")
		.append(" INNER JOIN FETCH pb.partner bp ")
		.append(" WHERE ib.itemBidId=:itemBidId ")
		.append(" and bp.bPartnerId=:partnerId and ms.materialSpecificationId is null ");
		return jpql.toString();
	}
	
	public String getLowestItemBidListBytahdrDetailId(){
		StringBuilder jpql= new StringBuilder();
		jpql.append(" Select pb from PriceBid pb ")
		.append(" LEFT JOIN FETCH pb.materialSpecification ms ")
		.append(" LEFT JOIN FETCH pb.itemBid ib ")
		.append(" LEFT JOIN FETCH ib.tahdrMaterial tm ")
		.append(" LEFT JOIN FETCH tm.tahdrDetail td ")
		.append(" WHERE td.tahdrDetailId=:tahdrDetailId and ms.materialSpecificationId is null AND ib.isLowestBid='Y' ");
		return jpql.toString();
	}	
	
	public String getLowestItemBidForMaterialId(){
		StringBuilder jpql= new StringBuilder();
		jpql.append(" Select pb from PriceBid pb ")
		.append(" LEFT JOIN FETCH pb.materialSpecification ms ")
		.append(" LEFT JOIN FETCH pb.itemBid ib ")
		.append(" LEFT JOIN FETCH ib.tahdrMaterial tm ")
		.append(" LEFT JOIN FETCH tm.tahdrDetail td ")
		.append(" WHERE ms.materialSpecificationId is null AND ib.isLowestBid='Y' ")
		.append(" and tm.tahdrMaterialId=:tahdrMaterialId ");
		return jpql.toString();
	}	
	
	public String getPBBidderListByTahdrMaterialId(){
		
		StringBuilder jpql= new StringBuilder();
		jpql.append(" Select DISTINCT b from PriceBid b ");
		jpql.append(" LEFT JOIN FETCH b.itemBid ib ");
		jpql.append(" LEFT JOIN FETCH ib.bidder bd ");
		jpql.append(" LEFT JOIN FETCH bd.partner p ");
		jpql.append(" LEFT JOIN FETCH bd.tahdr t ");
		jpql.append(" LEFT JOIN FETCH ib.tahdrMaterial tm");
		jpql.append(" LEFT JOIN FETCH tm.material tmm");
		jpql.append(" LEFT JOIN FETCH b.digiSignedDoc dstb ");
		jpql.append(" where t.tahdrId=:tahdrId "
				+" AND tm.tahdrMaterialId=:tahdrMaterialId "
				+ " AND bd.status='"+AppBaseConstant.BIDDER_STATUS_Price_BID_OPENED+"' AND b.digiSignedDoc IS NOT NULL ");
		return jpql.toString();
	}
	
public String getDecendingPBBidderDetailListByTahdrMaterialId(){
		
		StringBuilder jpql= new StringBuilder();
		jpql.append(" Select  b from PriceBid b ");
		jpql.append(" LEFT JOIN FETCH b.itemBid ib ");
		jpql.append(" LEFT JOIN FETCH ib.bidder bd ");
		jpql.append(" LEFT JOIN FETCH bd.factory fac ");
		jpql.append(" LEFT JOIN FETCH bd.partner p ");
		jpql.append(" LEFT JOIN FETCH bd.tahdr t ");
		jpql.append(" LEFT JOIN FETCH ib.tahdrMaterial tm");
		jpql.append(" LEFT JOIN FETCH tm.material m");
		jpql.append(" LEFT JOIN FETCH b.digiSignedDoc dstb ");
		jpql.append(" where t.tahdrId=:tahdrId "
				+" AND tm.tahdrMaterialId=:tahdrMaterialId "
				+ " AND bd.status='"+AppBaseConstant.BIDDER_STATUS_Price_BID_OPENED+"'"
				+ " AND ib.status='"+AppBaseConstant.BIDDER_STATUS_Price_BID_OPENED+"' "
						+ " AND b.digiSignedDoc IS NOT NULL ORDER BY TO_NUMBER(b.fddRateWithGST,'9G999G999G999G999G999g999') DESC");
		return jpql.toString();
	}

public String getDecendingPBBidderDetailListByTahdrId(){
	StringBuilder jpql= new StringBuilder();
	jpql.append(" Select  b from PriceBid b ");
	jpql.append(" LEFT JOIN FETCH b.itemBid ib ");
	jpql.append(" LEFT JOIN FETCH ib.bidder bd ");
	jpql.append(" LEFT JOIN FETCH bd.factory fac ");
	jpql.append(" LEFT JOIN FETCH bd.partner p ");
	jpql.append(" LEFT JOIN FETCH bd.tahdr t ");
	jpql.append(" LEFT JOIN FETCH ib.tahdrMaterial tm");
	jpql.append(" LEFT JOIN FETCH tm.material m");
	jpql.append(" LEFT JOIN FETCH b.digiSignedDoc dstb ");
	jpql.append(" where t.tahdrId=:tahdrId "
					+ " ORDER BY TO_NUMBER(b.fddRateWithGST,'9G999G999G999G999G999g999') DESC");
	return jpql.toString();
}

public String getAscendingPBBidderDetailListByTahdrId(){
	StringBuilder jpql= new StringBuilder();
	jpql.append(" Select  b from PriceBid b ");
	jpql.append(" LEFT JOIN FETCH b.itemBid ib ");
	jpql.append(" LEFT JOIN FETCH ib.bidder bd ");
	jpql.append(" LEFT JOIN FETCH bd.factory fac ");
	jpql.append(" LEFT JOIN FETCH bd.partner p ");
	jpql.append(" LEFT JOIN FETCH bd.tahdr t ");
	jpql.append(" LEFT JOIN FETCH ib.tahdrMaterial tm");
	jpql.append(" LEFT JOIN FETCH tm.material m");
	jpql.append(" LEFT JOIN FETCH b.digiSignedDoc dstb ");
	jpql.append(" where t.tahdrId=:tahdrId "
					+ " ORDER BY TO_NUMBER(b.fddRateWithGST,'9G999G999G999G999G999g999') ASC");
	return jpql.toString();
}

public String getDecendingPBOpenedBidderDetailListByTahdrId(){
	
	StringBuilder jpql= new StringBuilder();
	jpql.append(" Select  b from PriceBid b ");
	jpql.append(" LEFT JOIN FETCH b.itemBid ib ");
	jpql.append(" LEFT JOIN FETCH ib.bidder bd ");
	jpql.append(" LEFT JOIN FETCH bd.factory fac ");
	jpql.append(" LEFT JOIN FETCH bd.partner p ");
	jpql.append(" LEFT JOIN FETCH bd.tahdr t ");
	jpql.append(" LEFT JOIN FETCH ib.tahdrMaterial tm");
	jpql.append(" LEFT JOIN FETCH tm.material m");
	jpql.append(" LEFT JOIN FETCH b.digiSignedDoc dstb ");
	jpql.append(" where t.tahdrId=:tahdrId "
			+ " AND bd.status='"+AppBaseConstant.BIDDER_STATUS_Price_BID_OPENED+"' "
					+ " AND ib.status='"+AppBaseConstant.BIDDER_STATUS_Price_BID_OPENED+"' "
					+ " AND b.digiSignedDoc IS NOT NULL ORDER BY TO_NUMBER(b.fddRateWithGST,'9G999G999G999G999G999g999') DESC");
	return jpql.toString();
	}

public String getAscendingPBOpenedBidderDetailListByTahdrId(){
	
	StringBuilder jpql= new StringBuilder();
	jpql.append(" Select  b from PriceBid b ");
	jpql.append(" LEFT JOIN FETCH b.itemBid ib ");
	jpql.append(" LEFT JOIN FETCH ib.bidder bd ");
	jpql.append(" LEFT JOIN FETCH bd.factory fac ");
	jpql.append(" LEFT JOIN FETCH bd.partner p ");
	jpql.append(" LEFT JOIN FETCH bd.tahdr t ");
	jpql.append(" LEFT JOIN FETCH ib.tahdrMaterial tm");
	jpql.append(" LEFT JOIN FETCH tm.material m");
	jpql.append(" LEFT JOIN FETCH b.digiSignedDoc dstb ");
	jpql.append(" where t.tahdrId=:tahdrId "
			+ " AND bd.status='"+AppBaseConstant.BIDDER_STATUS_Price_BID_OPENED+"' "
					+ " AND ib.status='"+AppBaseConstant.BIDDER_STATUS_Price_BID_OPENED+"' "
					+ " AND b.digiSignedDoc IS NOT NULL ORDER BY TO_NUMBER(b.fddRateWithGST,'9G999G999G999G999G999g999') ASC");
	return jpql.toString();
	}

public String getAscendingPBBidderDetailListByTahdrMaterialId(){
	
	StringBuilder jpql= new StringBuilder();
	jpql.append(" Select  b from PriceBid b ");
	jpql.append(" LEFT JOIN FETCH b.itemBid ib ");
	jpql.append(" LEFT JOIN FETCH ib.bidder bd ");
	jpql.append(" LEFT JOIN FETCH bd.factory fac ");
	jpql.append(" LEFT JOIN FETCH bd.partner p ");
	jpql.append(" LEFT JOIN FETCH bd.tahdr t ");
	jpql.append(" LEFT JOIN FETCH ib.tahdrMaterial tm");
	jpql.append(" LEFT JOIN FETCH tm.material m");
	jpql.append(" LEFT JOIN FETCH b.digiSignedDoc dstb ");
	jpql.append(" where t.tahdrId=:tahdrId "
			+" AND tm.tahdrMaterialId=:tahdrMaterialId "
			+ " AND bd.status='"+AppBaseConstant.BIDDER_STATUS_Price_BID_OPENED+"' "
					+ " AND ib.status='"+AppBaseConstant.BIDDER_STATUS_Price_BID_OPENED+"' "
					+ " AND b.digiSignedDoc IS NOT NULL ORDER BY TO_NUMBER(b.fddRateWithGST,'9G999G999G999G999G999g999') ASC");
	return jpql.toString();
	}

public String getDecendingPBQuickBidderDetailListByTahdrMaterialId(){
	
	StringBuilder jpql= new StringBuilder();
	jpql.append(" Select DISTINCT b from PriceBid b ");
	jpql.append(" LEFT JOIN FETCH b.itemBid ib ");
	jpql.append(" LEFT JOIN FETCH ib.bidder bd ");
	jpql.append(" LEFT JOIN FETCH bd.factory fac ");
	jpql.append(" LEFT JOIN FETCH bd.partner p ");
	jpql.append(" LEFT JOIN FETCH bd.tahdr t ");
	jpql.append(" LEFT JOIN FETCH ib.tahdrMaterial tm");
	jpql.append(" LEFT JOIN FETCH tm.material tmm");
	jpql.append(" where t.tahdrId=:tahdrId "
			+" AND tm.tahdrMaterialId=:tahdrMaterialId "
			+" ORDER BY b.fddRateWithGST DESC");
	return jpql.toString();
}

@SuppressWarnings("unchecked")
@Override
public List<Object> getDecendingQuickBidderStatistic(Long tahdrId,Long tahdrMaterilId){
		StringBuilder jpql= new StringBuilder();
		jpql.append("SELECT A.x,A.y,A.Name FROM ((SELECT pb1.t_price_bid_id as priceBidId,pb1.created as x,pb1.ex_group_price_rate as y,partner.name as name ");
		/*jpql.append(" ,dense_rank() over (partition by ib1.t_tahdr_material_id order by  ");
		jpql.append(" pb1.FDD_AMOUNT_WITH_GST desc nulls last) dr1");*/
		jpql.append("  from t_price_bid pb1  ");
		jpql.append(" INNER JOIN t_item_bid ib1 ON ib1.t_item_bid_id=pb1.t_item_bid_id  ");
		jpql.append(" INNER JOIN t_tahdr_material tm1 ON tm1.t_tahdr_material_id=ib1.t_tahdr_material_id  ");
		jpql.append(" INNER JOIN t_bidder b ON b.t_bidder_id=ib1.t_bidder_id  ");
		jpql.append(" INNER JOIN m_bpartner partner ON partner.m_bpartner_id=ib1.m_bpartner_id  ");
		jpql.append(" WHERE b.t_tahdr_id=:tahdrId AND tm1.t_tahdr_material_id=:tahdrMaterilId )");
		jpql.append(" UNION ");
		jpql.append(" (SELECT  pb1.t_price_bid_id as priceBidId,pb1.created as x,pb1.ex_group_price_rate as y,partner.name as name ");
		/*jpql.append(" ,dense_rank() over (partition by ib1.t_tahdr_material_id order by  ");
		jpql.append(" pb1.FDD_AMOUNT_WITH_GST desc nulls last) dr1 ");*/
		jpql.append("  from t_temp_price_bid pb1  ");
		jpql.append(" INNER JOIN t_item_bid ib1 ON ib1.t_item_bid_id=pb1.t_item_bid_id  ");
		jpql.append(" INNER JOIN t_tahdr_material tm1 ON tm1.t_tahdr_material_id=ib1.t_tahdr_material_id  ");
		jpql.append(" INNER JOIN t_bidder b ON b.t_bidder_id=ib1.t_bidder_id  ");
		jpql.append(" INNER JOIN m_bpartner partner ON partner.m_bpartner_id=ib1.m_bpartner_id  ");
		jpql.append(" WHERE b.t_tahdr_id=:tahdrId AND tm1.t_tahdr_material_id=:tahdrMaterilId ) ORDER BY priceBidId ) A ORDER BY priceBidId DESC");
		Query query=getEntityManager().createNativeQuery(jpql.toString());
		query.setParameter("tahdrId", tahdrId);
		query.setParameter("tahdrMaterilId", tahdrMaterilId);
		List<Object> count= query.getResultList();
		return count;
}

public String getAscendingPBQuickBidderDetailListByTahdrMaterialId(){

StringBuilder jpql= new StringBuilder();
jpql.append(" Select DISTINCT b from PriceBid b ");
jpql.append(" LEFT JOIN FETCH b.itemBid ib ");
jpql.append(" LEFT JOIN FETCH ib.bidder bd ");
jpql.append(" LEFT JOIN FETCH bd.factory fac ");
jpql.append(" LEFT JOIN FETCH bd.partner p ");
jpql.append(" LEFT JOIN FETCH bd.tahdr t ");
jpql.append(" LEFT JOIN FETCH ib.tahdrMaterial tm");
jpql.append(" LEFT JOIN FETCH tm.material tmm");
jpql.append(" LEFT JOIN FETCH tm.material tmm");
jpql.append(" where t.tahdrId=:tahdrId "
		+" AND tm.tahdrMaterialId=:tahdrMaterialId "
		+" ORDER BY b.fddRateWithGST ");
	return jpql.toString();
}

	@SuppressWarnings("unchecked")
	@Override
public List<Object> getAscendingQuickBidderStatistic(Long tahdrId,Long tahdrMaterilId){
		StringBuilder jpql= new StringBuilder();
		jpql.append("SELECT A.x,A.y,A.Name FROM ((SELECT pb1.t_price_bid_id as priceBidId, pb1.created as x,pb1.ex_group_price_rate as y,partner.name as name ");
		/*jpql.append(" ,dense_rank() over (partition by ib1.t_tahdr_material_id order by  ");
		jpql.append(" pb1.FDD_AMOUNT_WITH_GST  nulls last) dr1 ");*/
		jpql.append(" from t_price_bid pb1  ");
		jpql.append(" INNER JOIN t_item_bid ib1 ON ib1.t_item_bid_id=pb1.t_item_bid_id  ");
		jpql.append(" INNER JOIN t_tahdr_material tm1 ON tm1.t_tahdr_material_id=ib1.t_tahdr_material_id  ");
		jpql.append(" INNER JOIN t_bidder b ON b.t_bidder_id=ib1.t_bidder_id  ");
		jpql.append(" INNER JOIN m_bpartner partner ON partner.m_bpartner_id=ib1.m_bpartner_id  ");
		jpql.append(" WHERE b.t_tahdr_id=:tahdrId AND tm1.t_tahdr_material_id=:tahdrMaterilId )");
		jpql.append(" UNION ");
		jpql.append(" (SELECT pb1.t_price_bid_id as priceBidId,pb1.created as x,pb1.ex_group_price_rate as y,partner.name as name ");
		/*jpql.append(" ,dense_rank() over (partition by ib1.t_tahdr_material_id order by  ");
		jpql.append(" pb1.FDD_AMOUNT_WITH_GST nulls last) dr1");*/
		jpql.append(" from t_temp_price_bid pb1  ");
		jpql.append(" INNER JOIN t_item_bid ib1 ON ib1.t_item_bid_id=pb1.t_item_bid_id  ");
		jpql.append(" INNER JOIN t_tahdr_material tm1 ON tm1.t_tahdr_material_id=ib1.t_tahdr_material_id  ");
		jpql.append(" INNER JOIN t_bidder b ON b.t_bidder_id=ib1.t_bidder_id  ");
		jpql.append(" INNER JOIN m_bpartner partner ON partner.m_bpartner_id=ib1.m_bpartner_id  ");
		jpql.append(" WHERE b.t_tahdr_id=:tahdrId AND tm1.t_tahdr_material_id=:tahdrMaterilId ) ORDER BY priceBidId) A ORDER BY priceBidId ASC");
		Query query=getEntityManager().createNativeQuery(jpql.toString());
		query.setParameter("tahdrId", tahdrId);
		query.setParameter("tahdrMaterilId", tahdrMaterilId);
		List<Object> count= query.getResultList();
		return count;
}

public String getSelfPBBidListByTahdrMaterialId(){
	
	StringBuilder jpql= new StringBuilder();
	jpql.append(" Select DISTINCT b from PriceBid b ");
	jpql.append(" LEFT JOIN FETCH b.itemBid ib ");
	jpql.append(" LEFT JOIN FETCH ib.bidder bd ");
	jpql.append(" LEFT JOIN FETCH bd.factory fac ");
	jpql.append(" LEFT JOIN FETCH bd.partner p ");
	jpql.append(" LEFT JOIN FETCH bd.tahdr t ");
	jpql.append(" LEFT JOIN FETCH ib.tahdrMaterial tm");
	jpql.append(" LEFT JOIN FETCH tm.material tmm");
	jpql.append(" LEFT JOIN FETCH b.digiSignedDoc dstb ");
	jpql.append(" where t.tahdrId=:tahdrId "
			+" AND tm.tahdrMaterialId=:tahdrMaterialId  AND p.bPartnerId=:partnerId "
			+ " AND bd.status='"+AppBaseConstant.BIDDER_STATUS_Price_BID_OPENED+"' AND b.digiSignedDoc IS NOT NULL ");
	return jpql.toString();
}
	
public String getSelfBidHistoryByBidderIdTahdrMaterialId(){
	
	StringBuilder jpql= new StringBuilder();
	jpql.append(" Select DISTINCT pb from PriceBid pb ")
	.append(" INNER JOIN FETCH pb.itemBid ib ")
	.append(" INNER JOIN FETCH ib.bidder b ")
	.append(" INNER JOIN FETCH b.partner p ")
	.append(" INNER JOIN FETCH b.tahdr t ")
	.append(" INNER JOIN FETCH ib.tahdrMaterial tm")
	.append(" INNER JOIN FETCH tm.material tmm")
	.append(" LEFT JOIN FETCH b.factory fac ")
	.append(" LEFT JOIN FETCH pb.materialSpecification ms ")
	.append(" where tm.tahdrMaterialId=:tahdrMaterialId  AND b.bidderId=:bidderId "
			+ " AND ms.materialSpecificationId IS NULL ");
	return jpql.toString();
	
}
	
		public String getCompleteBidHistoryTahdrMaterialId(){
			
			StringBuilder jpql= new StringBuilder();
			jpql.append(" Select DISTINCT pb from PriceBid pb ")
			.append(" INNER JOIN FETCH pb.itemBid ib ")
			.append(" INNER JOIN FETCH ib.bidder b ")
			.append(" INNER JOIN FETCH b.partner p ")
			.append(" INNER JOIN FETCH b.tahdr t ")
			.append(" INNER JOIN FETCH ib.tahdrMaterial tm")
			.append(" INNER JOIN FETCH tm.material tmm")
			.append(" LEFT JOIN FETCH b.factory fac ")
			.append(" LEFT JOIN FETCH pb.materialSpecification ms ")
			.append(" where tm.tahdrMaterialId=:tahdrMaterialId "
					+ " AND ms.materialSpecificationId IS NULL ");
			return jpql.toString();
			
		}

public String getSelfPBQuickBidListByTahdrMaterialId(){
	
	StringBuilder jpql= new StringBuilder();
	jpql.append(" Select DISTINCT b from PriceBid b ");
	jpql.append(" LEFT JOIN FETCH b.itemBid ib ");
	jpql.append(" LEFT JOIN FETCH ib.bidder bd ");
	jpql.append(" LEFT JOIN FETCH bd.factory fac ");
	jpql.append(" LEFT JOIN FETCH bd.partner p ");
	jpql.append(" LEFT JOIN FETCH bd.tahdr t ");
	jpql.append(" LEFT JOIN FETCH ib.tahdrMaterial tm");
	jpql.append(" LEFT JOIN FETCH tm.material tmm");
	jpql.append(" LEFT JOIN FETCH b.digiSignedDoc dstb ");
	jpql.append(" where t.tahdrId=:tahdrId "
			+" AND tm.tahdrMaterialId=:tahdrMaterialId  AND p.bPartnerId=:partnerId ");
	return jpql.toString();
}

public String getSelfPBQuickBidListByBidderIdAndMaterialId(){
	
	StringBuilder jpql= new StringBuilder();
	jpql.append(" Select DISTINCT b from PriceBid b ");
	jpql.append(" LEFT JOIN FETCH b.itemBid ib ");
	jpql.append(" LEFT JOIN FETCH ib.bidder bd ");
	jpql.append(" LEFT JOIN FETCH bd.factory fac ");
	jpql.append(" LEFT JOIN FETCH bd.partner p ");
	jpql.append(" LEFT JOIN FETCH bd.tahdr t ");
	jpql.append(" LEFT JOIN FETCH ib.tahdrMaterial tm");
	jpql.append(" LEFT JOIN FETCH tm.material tmm");
	jpql.append(" LEFT JOIN FETCH b.digiSignedDoc dstb ");
	jpql.append(" where bd.bidderId=:bidderId "
			+" AND tm.tahdrMaterialId=:tahdrMaterialId ");
	return jpql.toString();
}
	
     public String getPBBidderListByTahdrId(){
		
		StringBuilder jpql= new StringBuilder();
		jpql.append(" Select DISTINCT b from PriceBid b ");
		jpql.append(" LEFT JOIN FETCH b.itemBid ib ");
		jpql.append(" LEFT JOIN FETCH ib.bidder bd ");
		jpql.append(" LEFT JOIN FETCH bd.partner p ");
		jpql.append(" LEFT JOIN FETCH bd.tahdr t ");
		jpql.append(" LEFT JOIN FETCH ib.tahdrMaterial tm");
		jpql.append(" LEFT JOIN FETCH tm.material tmm");
		jpql.append(" LEFT JOIN FETCH b.digiSignedDoc dstb ");
		jpql.append(" where t.tahdrId=:tahdrId "
				+ " AND bd.status=:status AND b.digiSignedDoc IS NOT NULL ");
		return jpql.toString();
	}
   
   public String getC1BidderListByTahdrId(){
		
		StringBuilder jpql= new StringBuilder();
		jpql.append(" Select DISTINCT b from PriceBid b ");
		jpql.append(" LEFT JOIN FETCH b.itemBid ib ");
		jpql.append(" LEFT JOIN FETCH ib.bidder bd ");
		jpql.append(" LEFT JOIN FETCH bd.partner p ");
		jpql.append(" LEFT JOIN FETCH bd.tahdr t ");
		jpql.append(" LEFT JOIN FETCH ib.tahdrMaterial tm");
		jpql.append(" LEFT JOIN FETCH tm.material tmm");
		jpql.append(" LEFT JOIN FETCH b.digiSignedDoc dstb ");
		jpql.append(" where t.tahdrId=:tahdrId "
				+" AND tm.tahdrMaterialId=:tahdrMaterialId "
		        + "  AND bd.status='"+AppBaseConstant.BIDDER_STATUS_ANNEXURE_C1_OPENED+"' AND ib.status='"+AppBaseConstant.BIDDER_STATUS_ANNEXURE_C1_OPENED+"' "
		        		+ " AND b.digiSignedDoc IS NOT NULL");
		return jpql.toString();
	}
   
 public String getLowestPriceBidByTahdrMaterialIdAndTahdrId(){
	 StringBuilder jpql= new StringBuilder();
		jpql.append(" Select DISTINCT b from PriceBid b ");
		jpql.append(" LEFT JOIN FETCH b.itemBid ib ");
		jpql.append(" LEFT JOIN FETCH ib.bidder bd ");
		jpql.append(" LEFT JOIN FETCH bd.partner p ");
		jpql.append(" LEFT JOIN FETCH bd.tahdr t ");
		jpql.append(" LEFT JOIN FETCH t.tahdrDetail td ");
		jpql.append(" LEFT JOIN FETCH ib.tahdrMaterial tm");
		jpql.append(" LEFT JOIN FETCH tm.material tmm");
		jpql.append(" where t.tahdrId=:tahdrId "
				+" AND tm.tahdrMaterialId=:tahdrMaterialId "
		        + "  AND ib.isLowestBid='Y' AND td.isActive='Y' AND b.digiSignedDoc IS NOT NULL");
	 return jpql.toString();
 }
 
 public String getQuickLowestPriceBidByTahdrMaterialIdAndTahdrId(){
	 StringBuilder jpql= new StringBuilder();
		jpql.append(" Select DISTINCT b from PriceBid b ");
		jpql.append(" LEFT JOIN FETCH b.itemBid ib ");
		jpql.append(" LEFT JOIN FETCH ib.bidder bd ");
		jpql.append(" LEFT JOIN FETCH bd.partner p ");
		jpql.append(" LEFT JOIN FETCH bd.tahdr t ");
		jpql.append(" LEFT JOIN FETCH t.tahdrDetail td ");
		jpql.append(" LEFT JOIN FETCH ib.tahdrMaterial tm");
		jpql.append(" LEFT JOIN FETCH tm.material tmm");
		jpql.append(" where t.tahdrId=:tahdrId "
				+" AND tm.tahdrMaterialId=:tahdrMaterialId "
		        + "  AND ib.isLowestBid='Y' AND td.isActive='Y' ");
	 return jpql.toString();
 }
 
 public String getPartnerLatestBidByTahdrMaterialIdAndTahdrId(){
	 StringBuilder jpql= new StringBuilder();
		jpql.append(" Select DISTINCT b from PriceBid b ");
		jpql.append(" LEFT JOIN FETCH b.itemBid ib ");
		jpql.append(" LEFT JOIN FETCH ib.bidder bd ");
		jpql.append(" LEFT JOIN FETCH bd.partner p ");
		jpql.append(" LEFT JOIN FETCH bd.tahdr t ");
		jpql.append(" LEFT JOIN FETCH t.tahdrDetail td ");
		jpql.append(" LEFT JOIN FETCH ib.tahdrMaterial tm");
		jpql.append(" LEFT JOIN FETCH tm.material tmm");
		jpql.append(" where t.tahdrId=:tahdrId "
				   +" AND tm.tahdrMaterialId=:tahdrMaterialId "
				   +" AND p.bPartnerId=:partnerId AND td.isActive='Y' AND b.digiSignedDoc IS NOT NULL");
	 return jpql.toString();
 }
 
 public String getPartnerLatestQuickBidByTahdrMaterialIdAndTahdrId(){
	 StringBuilder jpql= new StringBuilder();
		jpql.append(" Select DISTINCT b from PriceBid b ");
		jpql.append(" LEFT JOIN FETCH b.itemBid ib ");
		jpql.append(" LEFT JOIN FETCH ib.bidder bd ");
		jpql.append(" LEFT JOIN FETCH bd.partner p ");
		jpql.append(" LEFT JOIN FETCH bd.tahdr t ");
		jpql.append(" LEFT JOIN FETCH ib.tahdrMaterial tm");
		jpql.append(" LEFT JOIN FETCH tm.material tmm");
		jpql.append(" where t.tahdrId=:tahdrId "
				   +" AND tm.tahdrMaterialId=:tahdrMaterialId "
				   +" AND p.bPartnerId=:partnerId ");
		return jpql.toString();
 }
 
 public String getPartnerPriceBidByTahdrId(){
	 StringBuilder jpql= new StringBuilder();
		jpql.append(" Select DISTINCT b from PriceBid b ");
		jpql.append(" LEFT JOIN FETCH b.itemBid ib ");
		jpql.append(" LEFT JOIN FETCH ib.bidder bd ");
		jpql.append(" LEFT JOIN FETCH bd.partner p ");
		jpql.append(" LEFT JOIN FETCH bd.tahdr t ");
		jpql.append(" LEFT JOIN FETCH ib.tahdrMaterial tm");
		jpql.append(" LEFT JOIN FETCH tm.material tmm");
		jpql.append(" LEFT JOIN FETCH tmm.uom uom ");
		jpql.append(" WHERE t.tahdrId=:tahdrId "
		           +" AND p.bPartnerId=:partnerId AND b.digiSignedDoc IS NOT NULL ");
		           /*+" AND p.bPartnerId=:partnerId  AND) ");*/
	 return jpql.toString();
 }
 
 public String getBidsForAnnexureC1(){
		StringBuilder jpql=new StringBuilder();
		jpql.append(" Select pb from PriceBid pb ")
		.append(" LEFT JOIN FETCH pb.materialSpecification ms ")
		.append(" LEFT JOIN FETCH pb.itemBid ib ")
		.append(" LEFT JOIN FETCH ib.tahdrMaterial tm ")
		.append(" LEFT JOIN FETCH tm.material m ")
		.append(" LEFT JOIN FETCH m.uom uom ")
		.append(" LEFT JOIN FETCH ib.bidder b ")
		.append(" LEFT JOIN FETCH b.tahdr t ")
		.append(" LEFT JOIN FETCH t.tahdrDetail td ")
		.append(" where td.tahdrDetailId=:tahdrDetailId ")
		.append(" and pb.partner.bPartnerId=:partnerId and ms.materialSpecificationId is null ")
		.append(" and td.isAnnexureC1='Y' and td.isActive='Y' ")
		.append(" and ib.status IN ('C1SU','ANC1')");
		return jpql.toString();
	}
 
 public String getPartnerBidsForAnnexureC1(){
		StringBuilder jpql=new StringBuilder();
		jpql.append(" Select pb from PriceBid pb ")
		.append(" LEFT JOIN FETCH pb.materialSpecification ms ")
		.append(" LEFT JOIN FETCH pb.digiSignedDoc digi ")
		.append(" LEFT JOIN FETCH pb.itemBid ib ")
		.append(" LEFT JOIN FETCH ib.tahdrMaterial tm ")
		.append(" LEFT JOIN FETCH tm.material m ")
		.append(" LEFT JOIN FETCH m.uom uom ")
		.append(" LEFT JOIN FETCH ib.bidder b ")
		.append(" LEFT JOIN FETCH b.tahdr t ")
		.append(" LEFT JOIN FETCH t.tahdrDetail td ")
		.append(" where tm.tahdrMaterialId=:tahdrMaterialId ")
		.append(" and b.bidderId=:bidderId and ms.materialSpecificationId is null ")
		.append(" and td.isAnnexureC1='Y' and td.isActive='Y' ")
		.append(" and ib.status IN ('C1SU','ANC1')");
		return jpql.toString();
	}
 
 @SuppressWarnings("unchecked")
@Override
 public List<Object> getOverAllForwardRankBy(Long tahdrId,Long partnerId){
		StringBuilder jpql= new StringBuilder();
		jpql.append(" SELECT  ");
		jpql.append(" SUM(dr1)/count(*) AS OVERALL_RANK ");
		jpql.append(" FROM ");
		jpql.append(" (SELECT pb1.ex_group_price_rate, ib1.t_tahdr_material_id,pb1.FDD_AMOUNT_WITH_GST,b.t_bidder_id AS BIDDER,partner.m_bpartner_id AS partnerId , ");
		jpql.append(" dense_rank() over (partition by ib1.t_tahdr_material_id order by  ");
		jpql.append(" pb1.FDD_AMOUNT_WITH_GST desc nulls last) ");
		jpql.append(" dr1 from t_price_bid pb1  ");
		jpql.append(" INNER JOIN t_item_bid ib1 ON ib1.t_item_bid_id=pb1.t_item_bid_id  ");
		jpql.append(" INNER JOIN t_tahdr_material tm1 ON tm1.t_tahdr_material_id=ib1.t_tahdr_material_id  ");
		jpql.append(" INNER JOIN t_bidder b ON b.t_bidder_id=ib1.t_bidder_id  ");
		jpql.append(" INNER JOIN m_bpartner partner ON partner.m_bpartner_id=ib1.m_bpartner_id  ");
		jpql.append(" WHERE b.t_tahdr_id=:tahdrId  ");
		jpql.append(" AND ib1.status='"+AppBaseConstant.BIDDER_STATUS_Price_BID_OPENED+"') AA WHERE AA.partnerId=:partnerId ");
		Query query=getEntityManager().createNativeQuery(jpql.toString());
		query.setParameter("tahdrId", tahdrId);
		query.setParameter("partnerId", partnerId);
		List<Object> count= query.getResultList();
		return count;
	}
 @SuppressWarnings("unchecked")
 @Override
  public List<Object> getOverAllReverseRankBy(Long tahdrId,Long partnerId){
 		StringBuilder jpql= new StringBuilder();
 		jpql.append(" SELECT  ");
 		jpql.append(" SUM(dr1)/count(*) AS OVERALL_RANK ");
 		jpql.append(" FROM ");
 		jpql.append(" (SELECT pb1.ex_group_price_rate, ib1.t_tahdr_material_id,pb1.FDD_AMOUNT_WITH_GST,b.t_bidder_id AS BIDDER,partner.m_bpartner_id AS partnerId , ");
 		jpql.append(" dense_rank() over (partition by ib1.t_tahdr_material_id order by  ");
 		jpql.append(" pb1.FDD_AMOUNT_WITH_GST nulls last) ");
 		jpql.append(" dr1 from t_price_bid pb1  ");
 		jpql.append(" INNER JOIN t_item_bid ib1 ON ib1.t_item_bid_id=pb1.t_item_bid_id  ");
 		jpql.append(" INNER JOIN t_tahdr_material tm1 ON tm1.t_tahdr_material_id=ib1.t_tahdr_material_id  ");
 		jpql.append(" INNER JOIN t_bidder b ON b.t_bidder_id=ib1.t_bidder_id  ");
 		jpql.append(" INNER JOIN m_bpartner partner ON partner.m_bpartner_id=ib1.m_bpartner_id  ");
 		jpql.append(" WHERE b.t_tahdr_id=:tahdrId  ");
 		jpql.append(" AND ib1.status='"+AppBaseConstant.BIDDER_STATUS_Price_BID_OPENED+"') AA WHERE AA.partnerId=:partnerId ");
 		Query query=getEntityManager().createNativeQuery(jpql.toString());
 		query.setParameter("tahdrId", tahdrId);
 		query.setParameter("partnerId", partnerId);
 		List<Object> count= query.getResultList();
 		return count;
 	}
 
 @SuppressWarnings("unchecked")
@Override
 public List<Object> getOverAllQuickForwardRankBy(Long tahdrId,Long partnerId){
		StringBuilder jpql= new StringBuilder();
		jpql.append(" SELECT  ");
		jpql.append(" SUM(dr1)/count(distinct aa.partnerid) AS OVERALL_RANK ");
		jpql.append(" FROM ");
		jpql.append(" (SELECT pb1.ex_group_price_rate, ib1.t_tahdr_material_id,pb1.FDD_AMOUNT_WITH_GST,b.t_bidder_id AS BIDDER,partner.m_bpartner_id AS partnerId , ");
		jpql.append(" dense_rank() over (partition by ib1.t_tahdr_material_id order by  ");
		jpql.append("  CAST (pb1.fdd_rate_with_gst AS FLOAT) desc nulls last) ");
		jpql.append(" dr1 from t_price_bid pb1  ");
		jpql.append(" INNER JOIN t_item_bid ib1 ON ib1.t_item_bid_id=pb1.t_item_bid_id  ");
		jpql.append(" INNER JOIN t_tahdr_material tm1 ON tm1.t_tahdr_material_id=ib1.t_tahdr_material_id  ");
		jpql.append(" INNER JOIN t_bidder b ON b.t_bidder_id=ib1.t_bidder_id  ");
		jpql.append(" INNER JOIN m_bpartner partner ON partner.m_bpartner_id=ib1.m_bpartner_id  ");
		jpql.append(" WHERE b.t_tahdr_id=:tahdrId  ");
		jpql.append(" ) AA WHERE AA.partnerId=:partnerId GROUP BY dr1 ");
		Query query=getEntityManager().createNativeQuery(jpql.toString());
		query.setParameter("tahdrId", tahdrId);
		query.setParameter("partnerId", partnerId);
		List<Object> count= query.getResultList();
		return count;
	}
 @SuppressWarnings("unchecked")
 @Override
  public List<Object> getOverAllQuickReverseRankBy(Long tahdrId,Long partnerId){
 		StringBuilder jpql= new StringBuilder();
 		jpql.append(" SELECT  ");
 		jpql.append(" SUM(dr1)/count(distinct aa.partnerid) AS OVERALL_RANK ");
 		jpql.append(" FROM ");
 		jpql.append(" (SELECT pb1.ex_group_price_rate, ib1.t_tahdr_material_id,pb1.FDD_AMOUNT_WITH_GST,b.t_bidder_id AS BIDDER,partner.m_bpartner_id AS partnerId , ");
 		jpql.append(" dense_rank() over (partition by ib1.t_tahdr_material_id order by  ");
 		jpql.append("  CAST (pb1.fdd_rate_with_gst AS FLOAT) nulls last) ");
 		jpql.append(" dr1 from t_price_bid pb1  ");
 		jpql.append(" INNER JOIN t_item_bid ib1 ON ib1.t_item_bid_id=pb1.t_item_bid_id  ");
 		jpql.append(" INNER JOIN t_tahdr_material tm1 ON tm1.t_tahdr_material_id=ib1.t_tahdr_material_id  ");
 		jpql.append(" INNER JOIN t_bidder b ON b.t_bidder_id=ib1.t_bidder_id  ");
 		jpql.append(" INNER JOIN m_bpartner partner ON partner.m_bpartner_id=ib1.m_bpartner_id  ");
 		jpql.append(" WHERE b.t_tahdr_id=:tahdrId  ");
 		jpql.append(" ) AA WHERE AA.partnerId=:partnerId GROUP BY dr1 ");
 		Query query=getEntityManager().createNativeQuery(jpql.toString());
 		query.setParameter("tahdrId", tahdrId);
 		query.setParameter("partnerId", partnerId);
 		List<Object> count= query.getResultList();
 		return count;
 	}
 @SuppressWarnings("unchecked")
@Override
 public List<Object> getForwardRankByMaterial(Long tahdrId,Long tahdrMaterialId,Long partnerId){
		StringBuilder jpql= new StringBuilder();
		jpql.append(" SELECT  ");
		jpql.append(" AA.dr1 AS RANK ");
		jpql.append(" FROM ");
		jpql.append(" (SELECT pb1.ex_group_price_rate, ib1.t_tahdr_material_id,pb1.FDD_AMOUNT_WITH_GST,b.t_bidder_id AS BIDDER,partner.m_bpartner_id AS partnerId, ");
		jpql.append(" dense_rank() over (partition by ib1.t_tahdr_material_id order by  ");
		jpql.append("  CAST (pb1.fdd_rate_with_gst AS FLOAT) desc nulls last) ");
		jpql.append(" dr1 from t_price_bid pb1  ");
		jpql.append(" INNER JOIN t_item_bid ib1 ON ib1.t_item_bid_id=pb1.t_item_bid_id  ");
		jpql.append(" INNER JOIN t_tahdr_material tm1 ON tm1.t_tahdr_material_id=ib1.t_tahdr_material_id  ");
		jpql.append(" INNER JOIN t_bidder b ON b.t_bidder_id=ib1.t_bidder_id  ");
		jpql.append(" INNER JOIN m_bpartner partner ON partner.m_bpartner_id=ib1.m_bpartner_id  ");
		jpql.append(" WHERE b.t_tahdr_id=:tahdrId AND ib1.t_tahdr_material_id=:tahdrMaterialId ");
		jpql.append(" AND ib1.status='"+AppBaseConstant.BIDDER_STATUS_Price_BID_OPENED+"' AND pb1.digitally_signed_doc_id IS NOT NULL) AA WHERE AA.partnerId=:partnerId GROUP BY dr1 ");
		Query query=getEntityManager().createNativeQuery(jpql.toString());
		query.setParameter("tahdrId", tahdrId);
		query.setParameter("tahdrMaterialId", tahdrMaterialId);
		query.setParameter("partnerId", partnerId);
		List<Object> count= query.getResultList();
		return count;
	}
 @SuppressWarnings("unchecked")
 @Override
  public List<Object> getReverseRankByMaterial(Long tahdrId,Long tahdrMaterialId,Long partnerId){
 		StringBuilder jpql= new StringBuilder();
 		jpql.append(" SELECT  ");
 		jpql.append(" AA.dr1 AS RANK ");
 		jpql.append(" FROM ");
 		jpql.append(" (SELECT pb1.ex_group_price_rate, ib1.t_tahdr_material_id,pb1.FDD_AMOUNT_WITH_GST,b.t_bidder_id AS BIDDER,partner.m_bpartner_id AS partnerId, ");
 		jpql.append(" dense_rank() over (partition by ib1.t_tahdr_material_id order by  ");
 		jpql.append("  CAST (pb1.fdd_rate_with_gst AS FLOAT) nulls last) ");
 		jpql.append(" dr1 from t_price_bid pb1  ");
 		jpql.append(" INNER JOIN t_item_bid ib1 ON ib1.t_item_bid_id=pb1.t_item_bid_id  ");
 		jpql.append(" INNER JOIN t_tahdr_material tm1 ON tm1.t_tahdr_material_id=ib1.t_tahdr_material_id  ");
 		jpql.append(" INNER JOIN t_bidder b ON b.t_bidder_id=ib1.t_bidder_id  ");
 		jpql.append(" INNER JOIN m_bpartner partner ON partner.m_bpartner_id=ib1.m_bpartner_id  ");
 		jpql.append(" WHERE b.t_tahdr_id=:tahdrId AND ib1.t_tahdr_material_id=:tahdrMaterialId ");
 		jpql.append(" AND ib1.status='"+AppBaseConstant.BIDDER_STATUS_Price_BID_OPENED+"' AND pb1.digitally_signed_doc_id IS NOT NULL) AA WHERE AA.partnerId=:partnerId GROUP BY dr1 ");
 		Query query=getEntityManager().createNativeQuery(jpql.toString());
 		query.setParameter("tahdrId", tahdrId);
 		query.setParameter("tahdrMaterialId", tahdrMaterialId);
 		query.setParameter("partnerId", partnerId);
 		List<Object> count= query.getResultList();
 		return count;
 	}
 
 @SuppressWarnings("unchecked")
 @Override
  public List<Object> getQuickForwardRankByMaterial(Long tahdrId,Long tahdrMaterialId,Long partnerId){
 		StringBuilder jpql= new StringBuilder();
 		jpql.append(" SELECT  ");
 		jpql.append(" AA.dr1 AS RANK ");
 		jpql.append(" FROM ");
 		jpql.append(" (SELECT pb1.ex_group_price_rate, ib1.t_tahdr_material_id,pb1.FDD_AMOUNT_WITH_GST,b.t_bidder_id AS BIDDER,partner.m_bpartner_id AS partnerId, ");
 		jpql.append(" dense_rank() over (partition by ib1.t_tahdr_material_id order by  ");
 		jpql.append("  CAST (pb1.fdd_rate_with_gst AS FLOAT) desc nulls last) ");
 		jpql.append(" dr1 from t_price_bid pb1  ");
 		jpql.append(" INNER JOIN t_item_bid ib1 ON ib1.t_item_bid_id=pb1.t_item_bid_id  ");
 		jpql.append(" INNER JOIN t_tahdr_material tm1 ON tm1.t_tahdr_material_id=ib1.t_tahdr_material_id  ");
 		jpql.append(" INNER JOIN t_bidder b ON b.t_bidder_id=ib1.t_bidder_id  ");
 		jpql.append(" INNER JOIN m_bpartner partner ON partner.m_bpartner_id=ib1.m_bpartner_id  ");
 		jpql.append(" WHERE b.t_tahdr_id=:tahdrId AND ib1.t_tahdr_material_id=:tahdrMaterialId ");
 		jpql.append(" ) AA WHERE AA.partnerId=:partnerId GROUP BY dr1 ");
 		Query query=getEntityManager().createNativeQuery(jpql.toString());
 		query.setParameter("tahdrId", tahdrId);
 		query.setParameter("tahdrMaterialId", tahdrMaterialId);
 		query.setParameter("partnerId", partnerId);
 		List<Object> count= query.getResultList();
 		return count;
 	}
  @SuppressWarnings("unchecked")
  @Override
   public List<Object> getQuickReverseRankByMaterial(Long tahdrId,Long tahdrMaterialId,Long partnerId){
  		StringBuilder jpql= new StringBuilder();
  		jpql.append(" SELECT  ");
  		jpql.append(" AA.dr1 AS RANK ");
  		jpql.append(" FROM ");
  		jpql.append(" (SELECT pb1.ex_group_price_rate, ib1.t_tahdr_material_id,pb1.FDD_AMOUNT_WITH_GST,b.t_bidder_id AS BIDDER,partner.m_bpartner_id AS partnerId, ");
  		jpql.append(" dense_rank() over (partition by ib1.t_tahdr_material_id order by  ");
  		jpql.append("  CAST (pb1.fdd_rate_with_gst AS FLOAT) nulls last) ");
  		jpql.append(" dr1 from t_price_bid pb1  ");
  		jpql.append(" INNER JOIN t_item_bid ib1 ON ib1.t_item_bid_id=pb1.t_item_bid_id  ");
  		jpql.append(" INNER JOIN t_tahdr_material tm1 ON tm1.t_tahdr_material_id=ib1.t_tahdr_material_id  ");
  		jpql.append(" INNER JOIN t_bidder b ON b.t_bidder_id=ib1.t_bidder_id  ");
  		jpql.append(" INNER JOIN m_bpartner partner ON partner.m_bpartner_id=ib1.m_bpartner_id  ");
  		jpql.append(" WHERE b.t_tahdr_id=:tahdrId AND ib1.t_tahdr_material_id=:tahdrMaterialId ");
  		jpql.append(" ) AA WHERE AA.partnerId=:partnerId GROUP BY dr1 ");
  		Query query=getEntityManager().createNativeQuery(jpql.toString());
  		query.setParameter("tahdrId", tahdrId);
  		query.setParameter("tahdrMaterialId", tahdrMaterialId);
  		query.setParameter("partnerId", partnerId);
  		List<Object> count= query.getResultList();
  		return count;
  	}
  
  @SuppressWarnings("unchecked")
  @Override
   public List<Object> getForwardRankByMaterial(Long tahdrId,Long tahdrMaterialId){
  		StringBuilder jpql= new StringBuilder();
  		jpql.append(" SELECT  ");
  		jpql.append(" AA.dr1 AS RANK,AA.partnerId as partnerId ");
  		jpql.append(" FROM ");
  		jpql.append(" (SELECT pb1.ex_group_price_rate, ib1.t_tahdr_material_id,pb1.FDD_AMOUNT_WITH_GST,b.t_bidder_id AS BIDDER,partner.m_bpartner_id AS partnerId, ");
  		jpql.append(" dense_rank() over (partition by ib1.t_tahdr_material_id order by  ");
  		jpql.append(" CAST (pb1.fdd_rate_with_gst AS INTEGER) desc nulls last) ");
  		jpql.append(" dr1 from t_price_bid pb1  ");
  		jpql.append(" INNER JOIN t_item_bid ib1 ON ib1.t_item_bid_id=pb1.t_item_bid_id  ");
  		jpql.append(" INNER JOIN t_tahdr_material tm1 ON tm1.t_tahdr_material_id=ib1.t_tahdr_material_id  ");
  		jpql.append(" INNER JOIN t_bidder b ON b.t_bidder_id=ib1.t_bidder_id  ");
  		jpql.append(" INNER JOIN m_bpartner partner ON partner.m_bpartner_id=ib1.m_bpartner_id  ");
  		jpql.append(" WHERE b.t_tahdr_id=:tahdrId AND ib1.t_tahdr_material_id=:tahdrMaterialId ");
  		jpql.append(" AND ib1.status='"+AppBaseConstant.BIDDER_STATUS_Price_BID_OPENED+"' AND pb1.digitally_signed_doc_id IS NOT NULL) AA GROUP BY dr1 ");
  		Query query=getEntityManager().createNativeQuery(jpql.toString());
  		query.setParameter("tahdrId", tahdrId);
  		query.setParameter("tahdrMaterialId", tahdrMaterialId);
  		List<Object> count= query.getResultList();
  		return count;
  	}
   @SuppressWarnings("unchecked")
   @Override
    public List<Object> getReverseRankByMaterial(Long tahdrId,Long tahdrMaterialId){
   		StringBuilder jpql= new StringBuilder();
   		jpql.append(" SELECT  ");
   		jpql.append(" AA.dr1 AS RANK ,AA.partnerId as partnerId");
   		jpql.append(" FROM ");
   		jpql.append(" (SELECT pb1.ex_group_price_rate, ib1.t_tahdr_material_id,pb1.FDD_AMOUNT_WITH_GST,b.t_bidder_id AS BIDDER,partner.m_bpartner_id AS partnerId, ");
   		jpql.append(" dense_rank() over (partition by ib1.t_tahdr_material_id order by  ");
   		jpql.append(" CAST (pb1.fdd_rate_with_gst AS INTEGER) nulls last) ");
   		jpql.append(" dr1 from t_price_bid pb1  ");
   		jpql.append(" INNER JOIN t_item_bid ib1 ON ib1.t_item_bid_id=pb1.t_item_bid_id  ");
   		jpql.append(" INNER JOIN t_tahdr_material tm1 ON tm1.t_tahdr_material_id=ib1.t_tahdr_material_id  ");
   		jpql.append(" INNER JOIN t_bidder b ON b.t_bidder_id=ib1.t_bidder_id  ");
   		jpql.append(" INNER JOIN m_bpartner partner ON partner.m_bpartner_id=ib1.m_bpartner_id  ");
   		jpql.append(" WHERE b.t_tahdr_id=:tahdrId AND ib1.t_tahdr_material_id=:tahdrMaterialId ");
   		jpql.append(" AND ib1.status='"+AppBaseConstant.BIDDER_STATUS_Price_BID_OPENED+"' AND pb1.digitally_signed_doc_id IS NOT NULL) AA GROUP BY dr1 ");
   		Query query=getEntityManager().createNativeQuery(jpql.toString());
   		query.setParameter("tahdrId", tahdrId);
   		query.setParameter("tahdrMaterialId", tahdrMaterialId);
   		List<Object> count= query.getResultList();
   		return count;
   	}
   
   @SuppressWarnings("unchecked")
   @Override
    public List<Object> getQuickForwardRankByMaterial(Long tahdrId,Long tahdrMaterialId){
   		StringBuilder jpql= new StringBuilder();
   		jpql.append(" SELECT  ");
   		jpql.append(" AA.dr1 AS RANK,AA.partnerId as partnerId ");
   		jpql.append(" FROM ");
   		jpql.append(" (SELECT pb1.ex_group_price_rate, ib1.t_tahdr_material_id,pb1.FDD_AMOUNT_WITH_GST,b.t_bidder_id AS BIDDER,partner.m_bpartner_id AS partnerId, ");
   		jpql.append(" dense_rank() over (partition by ib1.t_tahdr_material_id order by  ");
   		jpql.append(" CAST (pb1.fdd_rate_with_gst AS INTEGER) desc nulls last) ");
   		jpql.append(" dr1 from t_price_bid pb1  ");
   		jpql.append(" INNER JOIN t_item_bid ib1 ON ib1.t_item_bid_id=pb1.t_item_bid_id  ");
   		jpql.append(" INNER JOIN t_tahdr_material tm1 ON tm1.t_tahdr_material_id=ib1.t_tahdr_material_id  ");
   		jpql.append(" INNER JOIN t_bidder b ON b.t_bidder_id=ib1.t_bidder_id  ");
   		jpql.append(" INNER JOIN m_bpartner partner ON partner.m_bpartner_id=ib1.m_bpartner_id  ");
   		jpql.append(" WHERE b.t_tahdr_id=:tahdrId AND ib1.t_tahdr_material_id=:tahdrMaterialId ");
   		jpql.append(" ) AA GROUP BY dr1 ");
   		Query query=getEntityManager().createNativeQuery(jpql.toString());
   		query.setParameter("tahdrId", tahdrId);
   		query.setParameter("tahdrMaterialId", tahdrMaterialId);
   		List<Object> count= query.getResultList();
   		return count;
   	}
    @SuppressWarnings("unchecked")
    @Override
     public List<Object> getQuickReverseRankByMaterial(Long tahdrId,Long tahdrMaterialId){
    		StringBuilder jpql= new StringBuilder();
    		jpql.append(" SELECT  ");
    		jpql.append(" AA.dr1 AS RANK,AA.partnerId as partnerId ");
    		jpql.append(" FROM ");
    		jpql.append(" (SELECT pb1.ex_group_price_rate, ib1.t_tahdr_material_id,pb1.FDD_AMOUNT_WITH_GST,b.t_bidder_id AS BIDDER,partner.m_bpartner_id AS partnerId, ");
    		jpql.append(" dense_rank() over (partition by ib1.t_tahdr_material_id order by  ");
    		jpql.append(" CAST (pb1.fdd_rate_with_gst AS INTEGER) nulls last) ");
    		jpql.append(" dr1 from t_price_bid pb1  ");
    		jpql.append(" INNER JOIN t_item_bid ib1 ON ib1.t_item_bid_id=pb1.t_item_bid_id  ");
    		jpql.append(" INNER JOIN t_tahdr_material tm1 ON tm1.t_tahdr_material_id=ib1.t_tahdr_material_id  ");
    		jpql.append(" INNER JOIN t_bidder b ON b.t_bidder_id=ib1.t_bidder_id  ");
    		jpql.append(" INNER JOIN m_bpartner partner ON partner.m_bpartner_id=ib1.m_bpartner_id  ");
    		jpql.append(" WHERE b.t_tahdr_id=:tahdrId AND ib1.t_tahdr_material_id=:tahdrMaterialId ");
    		jpql.append(" ) AA GROUP BY dr1 ");
    		Query query=getEntityManager().createNativeQuery(jpql.toString());
    		query.setParameter("tahdrId", tahdrId);
    		query.setParameter("tahdrMaterialId", tahdrMaterialId);
    		List<Object> count= query.getResultList();
    		return count;
    	}
  
 
 public String getBidderListFromTahdrMaterialId(){
		StringBuilder jpql=new StringBuilder();
		jpql.append(" Select pb from PriceBid pb ");
		jpql.append(" LEFT JOIN FETCH pb.itemBid ib ");
		jpql.append(" LEFT JOIN FETCH pb.materialSpecification matSpec ");
		jpql.append(" LEFT JOIN FETCH ib.bidder b ");
		jpql.append(" LEFT JOIN FETCH ib.tahdrMaterial tm");
		jpql.append(" LEFT JOIN FETCH pb.partner bp ");
		jpql.append(" LEFT JOIN FETCH b.tenderPurchase purchase ");
		jpql.append(" LEFT JOIN FETCH b.factory po ");
		jpql.append(" LEFT JOIN FETCH b.tahdr t ");
		jpql.append(" LEFT JOIN FETCH t.tahdrDetail td ");
		jpql.append(" where tm.tahdrMaterialId=:tahdrMaterialId AND td.isActive='Y' ");
		/*jpql.append(" AND b.status IN('"+AppBaseConstant.BIDDER_STATUS_ANNEXURE_C1_OPENED+"','"+AppBaseConstant.BIDDER_STATUS_Price_BID_OPENED+"')" );
		jpql.append(" AND ib.status IN('"+AppBaseConstant.BIDDER_STATUS_ANNEXURE_C1_OPENED+"','"+AppBaseConstant.BIDDER_STATUS_Price_BID_OPENED+"')" );*/
		/*jpql.append(" AND b.status=( CASE WHEN td.isAnnexureC1='Y' THEN 'C1OP' ELSE 'PBOP' END) ");
		jpql.append(" AND ib.status=( CASE WHEN td.isAnnexureC1='Y' THEN 'C1OP' ELSE 'PBOP' END) ");*/
		jpql.append(" AND ( b.status='PBOP' OR b.status=( CASE WHEN td.isAnnexureC1='Y' THEN 'C1OP' ELSE 'PBOP' END) ) ");
		jpql.append(" AND (ib.status='PBOP' OR ib.status=( CASE WHEN td.isAnnexureC1='Y' THEN 'C1OP' ELSE 'PBOP' END)) ");
		jpql.append(" AND pb.materialSpecification.materialSpecificationId IS NULL");
		return jpql.toString();
	}
 
 public String getQuickBidderListFromTahdrMaterialId(){
		StringBuilder jpql=new StringBuilder();
		jpql.append(" Select pb from PriceBid pb ");
		jpql.append(" LEFT JOIN FETCH pb.itemBid ib ");
		jpql.append(" LEFT JOIN FETCH pb.materialSpecification matSpec ");
		jpql.append(" LEFT JOIN FETCH ib.bidder b ");
		jpql.append(" LEFT JOIN FETCH ib.tahdrMaterial tm");
		jpql.append(" LEFT JOIN FETCH pb.partner bp ");
		jpql.append(" LEFT JOIN FETCH b.tenderPurchase purchase ");
		jpql.append(" LEFT JOIN FETCH b.factory po ");
		jpql.append(" LEFT JOIN FETCH b.tahdr t ");
		jpql.append(" LEFT JOIN FETCH t.tahdrDetail td ");
		jpql.append(" where tm.tahdrMaterialId=:tahdrMaterialId AND td.isActive='Y' ");
		/*jpql.append(" AND b.status='NEW' ");*/
		return jpql.toString();
	}

 public String getPriceBidByStatusAndTahdrMaterialIdAndTahdrId(){
		StringBuilder jpql=new StringBuilder();
		jpql.append(" Select pb from PriceBid pb ");
		jpql.append(" LEFT JOIN FETCH pb.itemBid ib ");
		jpql.append(" LEFT JOIN FETCH ib.tahdrMaterial tm ");
		jpql.append(" LEFT JOIN FETCH ib.bidder b ");
		jpql.append(" where b.tahdr.tahdrId=:tahdrId "
				+ " AND pb.status=:status AND tm.tahdrMaterialId=:tahdrMaterialId");
		return jpql.toString();
	}
 
    @Override
	public int saveAnnexureC1DigitalSignedFile(Long priceBidId,Long bidderId,Long fileId)
	{
		StringBuilder jpql= new StringBuilder();
		jpql.append(" Update PriceBid b SET b.digiSignedDoc.attachmentId=:fileId ");
		jpql.append( " where b.priceBidId=:priceBidId AND EXISTS (SELECT ib FROM ItemBid ib WHERE "
				+ " ib.bidder.bidderId=:bidderId AND ib.status='C1SU')");
		
		Query query=getEntityManager().createQuery(jpql.toString());
		query.setParameter("priceBidId", priceBidId);
		query.setParameter("fileId", fileId);
		query.setParameter("bidderId", bidderId);
		int count= query.executeUpdate();
		return count;
	}
   /* @Override
	public int saveClauseData(Long priceBidId,Long itemBidId,String clause,String clauseData){
		StringBuilder jpql= new StringBuilder();
		jpql.append(" Update PriceBid b SET b.:clause=:clauseData ");
		jpql.append( " where b.priceBidId=:priceBidId AND EXISTS (SELECT ib FROM ItemBid ib WHERE "
				+ " ib.itemBidId=:itemBidId AND ib.status='C1SU')");
		
		Query query=getEntityManager().createQuery(jpql.toString());
		query.setParameter("priceBidId", priceBidId);
		query.setParameter("itemBidId", itemBidId);
		query.setParameter("clause", clause);
		query.setParameter("clauseData", clauseData);
		int count= query.executeUpdate();
		return count;
	}*/
	
    public String getPriceBidWithC1DigiFile(){
		StringBuilder jpql= new StringBuilder();
		jpql.append(" Select pb from PriceBid pb ")
		.append(" LEFT JOIN FETCH pb.digiSignedDoc dsd ")
		.append(" INNER JOIN FETCH pb.itemBid ib ")
		.append(" INNER JOIN FETCH ib.bidder bd ")
		.append(" WHERE pb.priceBidId=:priceBidId AND pb.isC1DigidocSubmitted='Y' ");
		return jpql.toString();
	}
    
    @Override
	public int saveAnnexureC1FileResposne(Long priceBidId,Long fileId){
		StringBuilder jpql= new StringBuilder();
		jpql.append(" Update PriceBid b SET b.isC1DigidocSubmitted='Y',b.digiSignedDoc.attachmentId=:fileId ");
		jpql.append( " WHERE b.priceBidId=:priceBidId ");
		
		Query query=getEntityManager().createQuery(jpql.toString());
		query.setParameter("priceBidId", priceBidId);
		query.setParameter("fileId", fileId);
		int count= query.executeUpdate();
		return count;
	}
    
    @Override
	public int resetAnnexureC1FileResposne(Long priceBidId){
		StringBuilder jpql= new StringBuilder();
		jpql.append(" Update PriceBid b SET b.isC1DigidocSubmitted='N',b.digiSignedDoc.attachmentId=null ");
		jpql.append( " WHERE b.priceBidId=:priceBidId ");
		
		Query query=getEntityManager().createQuery(jpql.toString());
		query.setParameter("priceBidId", priceBidId);
		int count= query.executeUpdate();
		return count;
	}
    
    public String getOpenedPriceBid(){
		StringBuilder jpql= new StringBuilder();
		jpql.append(" Select pb from PriceBid pb ")
		.append(" LEFT JOIN FETCH pb.itemBid ib ")
		.append(" LEFT JOIN FETCH ib.bidder bd ")
		.append(" LEFT JOIN FETCH pb.partner p ")
		.append(" LEFT JOIN FETCH bd.tahdr t ")
		.append(" LEFT JOIN FETCH t.tahdrDetail td ")
		.append(" LEFT JOIN FETCH ib.tahdrMaterial tm")
		.append(" LEFT JOIN FETCH tm.material tmm")
		.append(" LEFT JOIN FETCH tm.tahdrDetail td1")
		.append(" LEFT JOIN FETCH pb.digiSignedDoc dspb ")
		.append(" WHERE bd.bidderId=:bidderId "
				+ " AND td.isActive='Y' "
				+ " AND td.tahdrDetailId=td1.tahdrDetailId "
				+ " AND tm.tahdrMaterialId=:tahdrMaterialId "
				+ " AND bd.status=:bidderStatus "
				+ " AND ib.status=:itemBidStatus ");
		return jpql.toString();
	}
    
    public String getOpenedC1Bid(){
		StringBuilder jpql= new StringBuilder();
		jpql.append(" Select pb from PriceBid pb ")
		.append(" LEFT JOIN FETCH pb.itemBid ib ")
		.append(" LEFT JOIN FETCH ib.bidder bd ")
		.append(" LEFT JOIN FETCH pb.partner p ")
		.append(" LEFT JOIN FETCH bd.tahdr t ")
		.append(" LEFT JOIN FETCH t.tahdrDetail td ")
		.append(" LEFT JOIN FETCH ib.tahdrMaterial tm")
		.append(" LEFT JOIN FETCH tm.material tmm")
		.append(" LEFT JOIN FETCH tm.tahdrDetail td1")
		.append(" LEFT JOIN FETCH pb.digiSignedDoc dspb ")
		.append(" WHERE bd.bidderId=:bidderId "
				+ " AND td.isActive='Y' "
				+ " AND td.tahdrDetailId=td1.tahdrDetailId "
				+ " AND tm.tahdrMaterialId=:tahdrMaterialId "
				+ " AND bd.status=:bidderStatus "
				+ " AND ib.status=:itemBidStatus "
				+ " AND pb.isAnnexureC1='Y' ");
		return jpql.toString();
	}
    
    public String getBidListBySessionString(){
       	StringBuilder jpql= new StringBuilder();
       	jpql.append(" SELECT pb FROM PriceBid pb");
       	jpql.append(" INNER JOIN FETCH pb.itemBid ib ");
       	jpql.append(" INNER JOIN FETCH ib.bidder b ");
       	jpql.append(" INNER JOIN FETCH b.tahdr t ");
       	jpql.append(" INNER JOIN FETCH pb.itemBid ib ");
       	jpql.append(" INNER JOIN FETCH ib.tahdrMaterial tm ");
       	jpql.append(" INNER JOIN FETCH tm.tahdrDetail td ");
    	jpql.append(" INNER JOIN FETCH tm.material m ");
    	jpql.append(" WHERE pb.createdSessionId=:sessionString ");
       	return jpql.toString();
       	}
    
    @SuppressWarnings("unchecked")
    @Override
     public List<Object> getCumulativeSheetByTahdrId(Long tahdrId){
    		StringBuilder jpql= new StringBuilder();
    		jpql.append(" SELECT M.NAME AS MATERIAL,P.NAME AS BIDDER,PB.fdd_amount_with_gst AS AMOUNT FROM T_PRICE_BID PB ");
    		jpql.append("LEFT JOIN T_ITEM_BID IB On IB.T_ITEM_BID_ID=PB.T_ITEM_BID_ID ");
    		jpql.append("LEFT JOIN T_BIDDER B On B.T_BIDDER_ID=IB.T_BIDDER_ID ");
    		jpql.append("LEFT JOIN T_TAHDR T ON T.T_TAHDR_ID=B.T_TAHDR_ID ");
    		jpql.append("LEFT JOIN M_BPARTNER P On P.M_BPARTNER_ID=B.M_BPARTNER_ID ");
    		jpql.append("LEFT JOIN T_TAHDR_MATERIAL TM ON TM.T_TAHDR_MATERIAL_ID=IB.T_TAHDR_MATERIAL_ID ");
    		jpql.append("LEFT JOIN M_MATERIAL M ON M.M_MATERIAL_ID=TM.T_MATERIAL_ID ");
    		jpql.append("WHERE T.T_TAHDR_ID=:tahdrId ");
    		Query query=getEntityManager().createNativeQuery(jpql.toString());
    		query.setParameter("tahdrId", tahdrId);
    		List<Object> count= query.getResultList();
    		return count;
    	}
    
    @SuppressWarnings("unchecked")
    @Override
     public List<Object> getCumulativeSheetOpenedBidderByTahdrId(Long tahdrId){
    		StringBuilder jpql= new StringBuilder();
    		jpql.append(" SELECT M.NAME AS MATERIAL,P.NAME AS BIDDER,PB.fdd_amount_with_gst AS AMOUNT FROM T_PRICE_BID PB ");
    		jpql.append("LEFT JOIN T_ITEM_BID IB On IB.T_ITEM_BID_ID=PB.T_ITEM_BID_ID ");
    		jpql.append("LEFT JOIN T_BIDDER B On B.T_BIDDER_ID=IB.T_BIDDER_ID ");
    		jpql.append("LEFT JOIN T_TAHDR T ON T.T_TAHDR_ID=B.T_TAHDR_ID ");
    		jpql.append("LEFT JOIN M_BPARTNER P On P.M_BPARTNER_ID=B.M_BPARTNER_ID ");
    		jpql.append("LEFT JOIN T_TAHDR_MATERIAL TM ON TM.T_TAHDR_MATERIAL_ID=IB.T_TAHDR_MATERIAL_ID ");
    		jpql.append("LEFT JOIN M_MATERIAL M ON M.M_MATERIAL_ID=TM.T_MATERIAL_ID ");
    		jpql.append("WHERE T.T_TAHDR_ID=:tahdrId AND IB.status=:status AND B.status=:status ");
    		Query query=getEntityManager().createNativeQuery(jpql.toString());
    		query.setParameter("tahdrId", tahdrId);
    		query.setParameter("status", AppBaseConstant.Price_BID_OPENED);
    		List<Object> count= query.getResultList();
    		return count;
    	}

    public String getPriceBidByBidderId(){
       	StringBuilder jpql= new StringBuilder();
       	jpql.append(" SELECT a FROM PriceBid a");
       	jpql.append(" INNER JOIN FETCH a.itemBid b ");
       	jpql.append(" INNER JOIN FETCH b.bidder c ");
       	jpql.append(" INNER JOIN FETCH b.prLine d ");
       	jpql.append(" INNER JOIN FETCH d.pr pr ");
    	jpql.append(" INNER JOIN FETCH c.partner e ");
    	jpql.append(" WHERE c.bidderId=:bidderId ");
    	jpql.append("ORDER BY d.prLineNumber ASC ");
       	return jpql.toString();
       	}
	
    
    public String getPriceBidByPrId(){
       	StringBuilder jpql= new StringBuilder();
       	jpql.append(" SELECT A FROM PriceBid A");
       	jpql.append(" INNER JOIN FETCH A.itemBid B ");
       	jpql.append(" INNER JOIN FETCH B.bidder C ");
       	jpql.append(" INNER JOIN FETCH B.prLine D ");
    	jpql.append(" INNER JOIN FETCH C.partner E ");
    	/*jpql.append(" INNER JOIN FETCH D.pr F ");*/
    	jpql.append(" INNER JOIN FETCH C.enquiry F ");
    	jpql.append(" WHERE F.enquiryId=:prId ");
       	return jpql.toString();
   	}
    
    public String getPriceBidByItemBid(){
       	StringBuilder jpql= new StringBuilder();
       	jpql.append(" SELECT A FROM PriceBid A");
       	jpql.append(" INNER JOIN FETCH A.itemBid B ");
    	jpql.append(" WHERE B.itemBidId=:itemBidId ");
       	return jpql.toString();
   	}
    
    
    public String getPriceBidBypricebidid(){
       	StringBuilder jpql= new StringBuilder();
       	jpql.append(" SELECT A FROM PriceBid A");
       
    	jpql.append(" WHERE A.priceBidId=:priceBidId ");
       	return jpql.toString();
   	}
    
}

