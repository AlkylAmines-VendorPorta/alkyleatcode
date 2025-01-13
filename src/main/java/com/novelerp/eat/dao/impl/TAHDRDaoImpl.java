/**
 * @author Ankush
 */
package com.novelerp.eat.dao.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.core.dao.impl.AbstractJpaDAO;
import com.novelerp.eat.dao.TAHDRDao;
import com.novelerp.eat.dto.TAHDRDetailDto;
import com.novelerp.eat.dto.TAHDRDto;
import com.novelerp.eat.dto.TenderCommitteeDto;
import com.novelerp.eat.entity.TAHDR;

@Repository
public class TAHDRDaoImpl extends AbstractJpaDAO<TAHDR, TAHDRDto> implements TAHDRDao {
	
	@PostConstruct
	public void postConstruct() {
		setClazz(TAHDR.class, TAHDRDto.class);
	}


	private String getTahdrQuery(){
		StringBuilder sb= new StringBuilder();
		sb.append(" Select DISTINCT E from TAHDR E ");
		sb.append(" LEFT JOIN FETCH E.createdBy cb ");
		return sb.toString();
	}
	
	public String getTahdrByBidderId(){
		StringBuilder sb= new StringBuilder();
		sb.append(" SELECT E FROM TAHDR E ");
		sb.append(" WHERE E.tahdrId = (SELECT B.tahdr.tahdrId FROM Bidder B "
				+ "                    Where B.bidderId=:bidderId) ");
		return sb.toString();
	}
	
	public String getTahdrDetailQuery(){
		StringBuilder sb= new StringBuilder(getTahdrQuery());
		sb.append(" LEFT JOIN FETCH E.tahdrDetail td ");
		sb.append(" LEFT JOIN FETCH td.tenderDoc doc ");
		sb.append(" LEFT JOIN FETCH td.officeNote ofno ");
		return sb.toString();
	}
	public String getTahdrDetailQueryByTypeCode(){		
		String jpql=getTahdrDetailQuery();
		jpql+= " WHERE E.tahdrTypeCode=:tenderTypeCode AND E.tahdrStatusCode='"+AppBaseConstant.DOCUMENT_STATUS_PUBLISHED+"'";
		return jpql;
	}
	public String getQueryTahdrDetailByTypeCode(){		
		String jpql=getTahdrDetailQuery();
		jpql+= " WHERE E.tahdrTypeCode=:tenderTypeCode ";
		return jpql;
	}
	
	private String getTahdrWithDetail(){
		StringBuilder jpql = new StringBuilder(getTahdrDetailQuery());
		jpql.append(" LEFT JOIN FETCH E.createdBy cb ");
		jpql.append(" LEFT JOIN FETCH E.partner bp ");
		jpql.append(" LEFT JOIN FETCH E.department d ");
		jpql.append(" LEFT JOIN FETCH E.officeLocation o");  
		return jpql.toString();
	}
	
	public String getTahdrWithAllDetail(){
		StringBuilder jpql = new StringBuilder(getTahdrDetailQuery());
		jpql.append(" LEFT JOIN FETCH E.partner bp ");
		jpql.append(" LEFT JOIN FETCH E.createdBy cb ");
		jpql.append(" LEFT JOIN FETCH E.updatedBy ub ");
		jpql.append(" LEFT JOIN FETCH E.department d ");
		jpql.append(" LEFT JOIN FETCH E.mom mom ");
		jpql.append(" WHERE E.tahdrId= :tahdrId ");
		return jpql.toString();
	}
	
	public String getQueryForTAHDRById(){		
		String jpql=getTahdrWithDetail();
				jpql+= " WHERE E.tahdrId= :tahdrId ";
		return jpql;
	}

	public String getQueryForTAHDRByIdActiveTahdrDetail(){		
		String jpql=getTahdrWithDetail();
				jpql+= " WHERE E.tahdrId= :tahdrId and td.isActive='Y' ";
		return jpql;
	}
	
	public String getQueryForTAHDRByCode(){		
		String jpql=getTahdrWithDetail();
		jpql+= " WHERE E.tahdrCode=:tahdrCode ";
		return jpql;
	}
	
	public String getQueryForTAHDRByTypeCode(){		
		StringBuilder jpql = new StringBuilder(getTahdrWithDetail());
		jpql.append(" WHERE E.tahdrTypeCode=:typeCode ");
		return jpql.toString();
	}
	
	@Override
	public TAHDR getAllTahdrDetails(Long tahdrId) {
		Map<String, Object> params = getParamMap("tahdrId", tahdrId);
		return getSingleEntity(getTahdrWithAllDetails(), params);
	}
	
	private String getTahdrWithAllDetails()
	{
		StringBuilder jpql =  new StringBuilder(" Select t from TAHDR t ");
		jpql.append(" LEFT JOIN FETCH t.tahdrDetail td ")
		.append(" LEFT JOIN FETCH td.tahdrMaterial tm ")
		.append(" LEFT JOIN FETCH tm.materialGtpList tmg ")
		.append(" LEFT JOIN FETCH td.sectionDocument sd ")
		.append(" WHERE t.tahdrId = :tahdrId ");			
		return jpql.toString();
	}

	public String getTahdrWithAllDetailsForMaterial()
	{
		StringBuilder jpql =  new StringBuilder(" Select t from TAHDR t ");
		jpql.append(" INNER JOIN FETCH t.tahdrDetail td ")
		.append(" INNER JOIN FETCH td.tahdrMaterial tm ")
		.append(" LEFT JOIN FETCH tm.materialGtpList tmg ")
		.append(" LEFT JOIN FETCH td.sectionDocument sd ")
		.append(" WHERE t.tahdrId = :tahdrId ")
		.append(" and tm.tahdrMaterialId=:tahdrMaterialId and td.isActive='Y'");			
		return jpql.toString();
	}
	
	public String getTahdrWithAllDetailsForCS()
	{
		StringBuilder jpql =  new StringBuilder(" Select t from TAHDR t ");
		jpql.append(" INNER JOIN FETCH t.tahdrDetail td ")
		.append(" LEFT JOIN FETCH td.sectionDocument sd ")
		.append(" WHERE t.tahdrId = :tahdrId ")
		.append(" and sd.code='"+AppBaseConstant.COMMERCIAL_SECTION+"' and td.isActive='Y'");			
		return jpql.toString();
	}
	
	public String getQueryForTahdrOpening(){
		StringBuilder sb= new StringBuilder(getTahdrDetailQuery());
		sb.append(" LEFT JOIN FETCH td.tenderCommittee tc ");
		sb.append(" LEFT JOIN FETCH tc.participant p ");
		sb.append(" WHERE td.techBidOpenningDate<:now ");
		sb.append(" AND p.user.userId=:userId ");
		return sb.toString();
	}
	
	public String getQueryForAnnexureC1Opening(){
		StringBuilder sb= new StringBuilder(getTahdrDetailQuery());
		sb.append(" LEFT JOIN FETCH td.tenderCommittee tc ");
		sb.append(" LEFT JOIN FETCH tc.participant p ");
		sb.append(" WHERE td.isAnnexureC1='Y'  ");
		sb.append(" AND p.user.userId=:userId ");
		sb.append(" AND td.c1OpenningDate<:now ");
		return sb.toString();
	}
	@Override
	public String getTenderListsByRole(String searchColumn, String searchValue){
		StringBuilder sb= new StringBuilder(getQueryForTAHDRByTypeCode());
		sb.append(" AND E.tahdrStatusCode IN(:tenderStatusList) ");
		if(!"none".equalsIgnoreCase(searchValue)){
			sb.append(" AND UPPER(E."+searchColumn+") LIKE :searchValue");
		}
		return sb.toString();
	}
	
	@Override
	public String getTenderListsAuditorId(String searchColumn, String searchValue){
		StringBuilder jpql = new StringBuilder(getTahdrDetailQuery());
		jpql.append(" INNER JOIN FETCH E.auditorUser au ");
		jpql.append(" LEFT JOIN FETCH E.createdBy cb ");
		jpql.append(" LEFT JOIN FETCH E.partner bp ");
		jpql.append(" LEFT JOIN FETCH E.department d ");
		jpql.append(" LEFT JOIN FETCH E.officeLocation o");  
		jpql.append(" WHERE E.tahdrTypeCode=:typeCode AND au.userId=:userId AND E.tahdrStatusCode IN(:tenderStatusList) ");
		if(!"none".equalsIgnoreCase(searchValue)){
			jpql.append(" AND UPPER(E."+searchColumn+") LIKE :searchValue");
		}
		jpql.append(" ORDER BY E.updated DESC ");
		return jpql.toString();
	}
	
	@Override
	public String getAuctionListsByRole(String searchColumn, String searchValue){
		StringBuilder sb= new StringBuilder(getQueryForTAHDRByTypeCode());
		sb.append(" AND E.tahdrStatusCode IN(:tenderStatusList) AND E.isAuction='Y' ");
		if(!"none".equalsIgnoreCase(searchValue)){
			sb.append(" AND UPPER(E."+searchColumn+") LIKE :searchValue");
		}
		return sb.toString();
	}
	
	public String getTenderListsByRoleAndLoc(){
		StringBuilder sb= new StringBuilder(getQueryForTAHDRByTypeCode());
		sb.append(" AND E.tahdrStatusCode IN(:tenderStatusList) ");
		sb.append(" AND E.officeLocation.locationTypeRef=:locationType ");
		sb.append(" ORDER BY E.created DESC ");
		return sb.toString();
	}
	
	public String getTenderListsByTahdrId(){
		StringBuilder sb= new StringBuilder(getTahdrDetailQuery());
		sb.append(" WHERE E.tahdrId = :tahdrId ");
		return sb.toString();
	}
	public String getMyTenderListsByTahdrId(){
		StringBuilder sb= new StringBuilder(getTahdrDetailQuery());
		sb.append(" WHERE E.tahdrId = :tahdrId ");
		return sb.toString();
	}
	
	public String getPublishedTenderList(){
		StringBuilder sb= new StringBuilder(getTahdrDetailQuery());
		sb.append(" WHERE E.tahdrStatusCode= :status ");
		return sb.toString();
	}
	
	public String getTenderListByStatus(){
		StringBuilder sb= new StringBuilder(getTahdrDetailQuery());
		sb.append(" WHERE E.tahdrStatusCode = :status ");
		return sb.toString();
	}
	
	public String getTenderListByStatusAndTypeCode(){
		StringBuilder sb= new StringBuilder(getTahdrDetailQuery());
		sb.append(" WHERE E.createdBy.userId=:userId AND E.tahdrStatusCode IN (:status1,:status2) AND td.isActive='Y' AND E.tahdrTypeCode=:typeCode AND E.isAuction='N' ");
		return sb.toString();
	}
	
	
	public String getAuctionListByStatusAndTypeCode(){
		StringBuilder sb= new StringBuilder(getTahdrDetailQuery());
		sb.append(" WHERE  E.createdBy.userId=:userId AND E.tahdrStatusCode IN (:status1,:status2) AND  td.isActive='Y' AND E.isAuction='Y'  AND E.tahdrTypeCode=:typeCode ");
		return sb.toString();
	}
	
	public String getQuickAuctionListByStatusAndTypeCode(){
		StringBuilder sb= new StringBuilder(getTahdrDetailQuery());
		sb.append(" WHERE  E.createdBy.userId=:userId AND E.tahdrStatusCode =:status1 AND  td.isActive='Y' AND E.isAuction='Y'  AND E.tahdrTypeCode=:typeCode ");
		return sb.toString();
	}
	
	public String getTenderListForTenderCommittee()
	{
		StringBuilder jpql=new StringBuilder(getTahdrDetailQuery());
		jpql.append("  where (E.tahdrTypeCode=:typeCode1 OR E.tahdrTypeCode=:typeCode2) And E.tahdrStatusCode NOT IN ('AP','RJ','DR','IP','VO') ORDER BY E.tahdrCode ");
		/*jpql.append("  where (E.tahdrTypeCode=:typeCode1 OR E.tahdrTypeCode=:typeCode2) AND E.tahdrStatusCode IN (:status1, :status2, (CASE WHEN :status3 IS NOT NULL Then :status3 END)) ");*/
		return jpql.toString();
	}
	
	public String getPublishedTenderListByTypeCode(){
		StringBuilder sb= new StringBuilder(getTahdrDetailQuery());
		sb.append(" WHERE E.tahdrTypeCode= :tenderTypeCode And E.tahdrStatusCode NOT IN ('AP','RJ','DR','IP','VO','PU') AND E.isAuction='N'");
		return sb.toString();
	}
	
	public String getRfqListByTypeCode(){
		StringBuilder sb= new StringBuilder(getTahdrDetailQuery());
		sb.append(" WHERE E.tahdrTypeCode= :tenderTypeCode");
		return sb.toString();
	}
	
	public String getQuickAuctionListByTypeCode(){
		StringBuilder sb= new StringBuilder(getTahdrDetailQuery());
		sb.append(" WHERE E.tahdrTypeCode= :tenderTypeCode");
		return sb.toString();
	}
	
	public String getPublishedAuctionListByTypeCode(){
		StringBuilder sb= new StringBuilder(getTahdrDetailQuery());
		sb.append(" WHERE UPPER(E.tahdrTypeCode) =:tenderTypeCode And E.tahdrStatusCode NOT IN ('AP','RJ','DR','IP','VO') AND E.isAuction='Y'");
		return sb.toString();
	}
	
	public String getSchedulingTenderListByTypeCode(){
		StringBuilder sb= new StringBuilder(getTahdrDetailQuery());
		sb.append(" WHERE E.tahdrTypeCode= :tenderTypeCode And E.tahdrStatusCode IN ('PU','TCOP','SCRDONE','PBOP','C1OP','RBOP','PBSCH','C1SCH','RBSCH','AWSCH','DBSCH','DBOP') ");
		return sb.toString();
	}
	
	public String getSchedulingAuctionListByTypeCode(){
		StringBuilder sb= new StringBuilder(getTahdrDetailQuery());
		sb.append(" WHERE E.tahdrTypeCode= :tenderTypeCode And E.tahdrStatusCode IN ('PU','TCOP','SCRDONE','PBOP','C1OP','RBOP','ASCH','PBSCH','C1SCH','RBSCH','AWSCH','DBSCH','DBOP') AND E.isAuction='Y'");
		return sb.toString();
	}
	/*public String getTenderMOMByTahdrId(){
		StringBuilder sb= new StringBuilder(getTahdrQuery());
		sb.append(" LEFT JOIN FETCH E.mom mom ");
		sb.append(" WHERE E.tahdrId = :tahdrId ");
		return sb.toString();
	}*/
	@Override
	public int scheduleTender(String schedulingType,Long tahdrId,String bidType,TAHDRDetailDto tahdrDetail) {
		StringBuilder jpql= new StringBuilder();
		jpql.append(" Update TAHDR b SET b.tahdrStatusCode=:tahdrStatusCode ");
		jpql.append( " where b.tahdrId=:tahdrId ");
		if(AppBaseConstant.TENDER_ANNEXURE_C1_SCHEDULING.equals(schedulingType)){
			
			jpql.append( " AND ( b.tahdrStatusCode IN ('"+AppBaseConstant.TENDER_PRICE_BID_OPENNING+"', "
					+ "'"+AppBaseConstant.AUCTION_SCHEDULING+"', "
					+ "'"+AppBaseConstant.TENDER_REVISED_BID_OPENNING+"', "
					+ "'"+AppBaseConstant.TENDER_ANNEXURE_C1_SCHEDULING+"')) "
					+ "AND :tahdrId IN (SELECT t.tahdrId FROM TAHDR t LEFT JOIN t.tahdrDetail td WHERE t.tahdrId=:tahdrId AND td.isActive='Y' AND td.isAnnexureC1='Y' AND now()>=( CASE WHEN t.tahdrTypeCode IN ('FA','PT') AND t.isAuction='Y' AND t.tahdrStatusCode='"+AppBaseConstant.AUCTION_SCHEDULING+"' THEN td.auctionToDate ELSE now() END) )   ");

		}else if(AppBaseConstant.TENDER_REVISED_BID_SCHEDULING.equals(schedulingType)){
				jpql.append( " AND (b.tahdrStatusCode='"+AppBaseConstant.TENDER_PRICE_BID_OPENNING+"' "
						+ "OR b.tahdrStatusCode='"+AppBaseConstant.TENDER_REVISED_BID_SCHEDULING+"') ");
			
		}else if(AppBaseConstant.TENDER_PRICE_BID_SCHEDULING.equals(schedulingType)){
			if(bidType.equals("SB") || (tahdrDetail!=null && !tahdrDetail.getIsPreliminaryScrutiny().equals("Y"))){
				jpql.append( " AND (b.tahdrStatusCode='"+AppBaseConstant.DOCUMENT_STATUS_PUBLISHED+"' OR "
						+ "b.tahdrStatusCode='"+AppBaseConstant.TENDER_PRICE_BID_SCHEDULING+"' )  ");
			}else{
				jpql.append( " AND (b.tahdrStatusCode='"+AppBaseConstant.SCRUTINY_FINISHED+"' OR "
						+ " b.tahdrStatusCode='"+AppBaseConstant.TENDER_DEVIATION_OPENNING+"'   "
						+ " b.tahdrStatusCode='"+AppBaseConstant.TENDER_PRICE_BID_SCHEDULING+"' )  ");	
			}
		}else if(AppBaseConstant.TENDER_AWARD_WINNER_SCHEDULING.equals(schedulingType)){
			jpql.append( " AND (b.tahdrStatusCode='"+AppBaseConstant.TENDER_PRICE_BID_OPENNING+"' OR "
					+ " b.tahdrStatusCode='"+AppBaseConstant.TENDER_ANNEXURE_C1_OPENNING+"' OR "
					+ "b.tahdrStatusCode='"+AppBaseConstant.TENDER_REVISED_BID_OPENNING+"' OR   "
					+ "b.tahdrStatusCode='"+AppBaseConstant.TENDER_AWARD_WINNER_SCHEDULING+"' OR   "
					+ "b.tahdrStatusCode='"+AppBaseConstant.AUCTION_SCHEDULING+"' )  ");
		}
		else if(AppBaseConstant.TENDER_DEVIATION_BID_SCHEDULING.equals(schedulingType)){
			jpql.append( " AND (b.tahdrStatusCode='"+AppBaseConstant.TENDER_TECHNO_COMMERCIAL_OPENNING+"' OR "
					+ "b.tahdrStatusCode='"+AppBaseConstant.TENDER_DEVIATION_BID_SCHEDULING+"' )  ");
			
		}
		else if(AppBaseConstant.AUCTION_SCHEDULING.equals(schedulingType)){
			jpql.append( " AND (b.tahdrStatusCode='"+AppBaseConstant.TENDER_PRICE_BID_OPENNING+"' OR "
					+ "b.tahdrStatusCode='"+AppBaseConstant.AUCTION_SCHEDULING+"' )  ");

		}
		
		Query query=getEntityManager().createQuery(jpql.toString());
		query.setParameter("tahdrId", tahdrId);
		if(AppBaseConstant.TENDER_ANNEXURE_C1_SCHEDULING.equals(schedulingType)){
			query.setParameter("tahdrStatusCode", AppBaseConstant.TENDER_ANNEXURE_C1_SCHEDULING);
		}else if(AppBaseConstant.TENDER_AWARD_WINNER_SCHEDULING.equals(schedulingType)){
			query.setParameter("tahdrStatusCode", AppBaseConstant.TENDER_AWARD_WINNER_SCHEDULING);
		}else if(AppBaseConstant.TENDER_PRICE_BID_SCHEDULING.equals(schedulingType)){
			query.setParameter("tahdrStatusCode", AppBaseConstant.TENDER_PRICE_BID_SCHEDULING);
		}else if(AppBaseConstant.TENDER_REVISED_BID_SCHEDULING.equals(schedulingType)){
			query.setParameter("tahdrStatusCode", AppBaseConstant.TENDER_REVISED_BID_SCHEDULING);
		}
		else if(AppBaseConstant.TENDER_DEVIATION_BID_SCHEDULING.equals(schedulingType)){
			query.setParameter("tahdrStatusCode", AppBaseConstant.TENDER_DEVIATION_BID_SCHEDULING);
		}
		else if(AppBaseConstant.AUCTION_SCHEDULING.equals(schedulingType)){
			query.setParameter("tahdrStatusCode", AppBaseConstant.AUCTION_SCHEDULING);
		}
		int count= query.executeUpdate();
		return count;
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public int setTenderStatus(String status,Long tahdrId) {
		StringBuilder jpql= new StringBuilder();
		jpql.append(" Update TAHDR b SET b.tahdrStatusCode=:tahdrStatusCode ");
		jpql.append( " where b.tahdrId=:tahdrId ");
		
		Query query=getEntityManager().createQuery(jpql.toString());
		query.setParameter("tahdrId", tahdrId);
		query.setParameter("tahdrStatusCode", status);
		int count= query.executeUpdate();
		return count;
	}
	@Override
	public String getMyAuctionForBidder(String searchColumn, String searchValue){
		String jpql=getTahdrDetailQuery();
		StringBuilder sb= new StringBuilder(jpql);
		sb.append(" INNER JOIN FETCH E.bidders bdr ");
		sb.append(" LEFT JOIN bdr.partner bp ");
		sb.append(" LEFT JOIN FETCH E.department dp ");
		sb.append(" LEFT JOIN FETCH E.officeType ot ");
		sb.append(" LEFT JOIN FETCH E.officeLocation ol ");
		sb.append(" WHERE bp.bPartnerId=:partnerId  and E.tahdrTypeCode=:typeCode AND E.tahdrStatusCode NOT IN ('IP','DR') AND E.isAuction='Y' ");
		if(!"none".equalsIgnoreCase(searchValue)){
			sb.append(" AND UPPER(E."+searchColumn+") LIKE :searchValue");
		}
		sb.append(" ORDER BY E.updated DESC ");
		return sb.toString();
	}
	
	@Override
	public String getMyTenderForBidder(String searchColumn, String searchValue){
		String jpql=getTahdrDetailQuery();
		StringBuilder sb= new StringBuilder(jpql);
		sb.append(" INNER JOIN FETCH E.bidders bdr ");
		sb.append(" LEFT JOIN bdr.partner bp ");
		sb.append(" LEFT JOIN FETCH E.department dp ");
		sb.append(" LEFT JOIN FETCH E.officeType ot ");
		sb.append(" LEFT JOIN FETCH E.officeLocation ol ");
		sb.append(" WHERE bp.bPartnerId=:partnerId  and E.tahdrTypeCode=:typeCode AND E.tahdrStatusCode NOT IN ('IP','DR')  ");
		if(!"none".equalsIgnoreCase(searchValue)){
			sb.append(" AND UPPER(E."+searchColumn+") LIKE :searchValue");
		}
		sb.append(" ORDER BY E.updated DESC ");
		return sb.toString();
	}
	
	@Override
	public String getTahdrWithDetailsForAwardWinner()
	{
		StringBuilder jpql =  new StringBuilder(" Select DISTINCT t from TAHDR t ");
		jpql.append(" LEFT JOIN FETCH t.tahdrDetail td ");
		jpql.append(" LEFT JOIN FETCH t.bidders bid ");
		jpql.append(" LEFT JOIN FETCH td.tahdrMaterial tm ");
		jpql.append(" LEFT JOIN FETCH tm.materialGtpList tmg ");
		jpql.append(" LEFT JOIN FETCH tm.itemBid ibid");
		jpql.append(" WHERE t.tahdrTypeCode= :tenderTypeCode And t.tahdrStatusCode= :status And td.isActive='Y' ");	
		return jpql.toString();
	}
	
	@Override
	public String getTahdrWithDetailsForAwardWinner(String searchColumn, String searchValue)
	{
		StringBuilder jpql =  new StringBuilder(" Select DISTINCT t from TAHDR t ");
		jpql.append(" LEFT JOIN FETCH t.tahdrDetail td ");
		jpql.append(" LEFT JOIN FETCH t.bidders bid ");
		jpql.append(" LEFT JOIN FETCH td.tahdrMaterial tm ");
		jpql.append(" LEFT JOIN FETCH tm.materialGtpList tmg ");
		jpql.append(" LEFT JOIN FETCH tm.itemBid ibid");
		jpql.append(" WHERE t.tahdrTypeCode= :tenderTypeCode And t.tahdrStatusCode= :status And td.isActive='Y' AND td.winnerSelectionDate<=now() ");	
		if(!"none".equalsIgnoreCase(searchValue)){
			jpql.append(" AND UPPER(t."+searchColumn+") LIKE :searchValue");
		}
		return jpql.toString();
	}
	
	@Override
	public String getRfqWithDetailsForAwardWinner(String searchColumn, String searchValue)
	{
		StringBuilder jpql =  new StringBuilder(" Select DISTINCT t from TAHDR t ");
		jpql.append(" LEFT JOIN FETCH t.tahdrDetail td ");
		jpql.append(" LEFT JOIN FETCH t.bidders bid ");
		jpql.append(" LEFT JOIN FETCH td.tahdrMaterial tm ");
		jpql.append(" LEFT JOIN FETCH tm.materialGtpList tmg ");
		jpql.append(" LEFT JOIN FETCH tm.itemBid ibid");
		jpql.append(" WHERE t.tahdrTypeCode= :tenderTypeCode And( t.tahdrStatusCode= :status Or t.tahdrStatusCode= :status1) And td.isActive='Y' AND td.technicalBidToDate<=now() ");	
		if(!"none".equalsIgnoreCase(searchValue)){
			jpql.append(" AND UPPER(t."+searchColumn+") LIKE :searchValue");
		}
		return jpql.toString();
	}
	
	
	public String getQuickTahdrWithDetailsForAwardWinner(String searchColumn, String searchValue)
	{
		StringBuilder jpql =  new StringBuilder(" Select DISTINCT t from TAHDR t ");
		jpql.append(" INNER JOIN FETCH t.tahdrDetail td ");
		jpql.append(" INNER JOIN FETCH t.bidders bid ");
		jpql.append(" INNER JOIN FETCH td.tahdrMaterial tm ");
		/*jpql.append(" LEFT JOIN FETCH tm.materialGtpList tmg ");*/
		jpql.append(" INNER JOIN FETCH tm.itemBid ibid");
		jpql.append(" WHERE t.tahdrTypeCode = :tenderTypeCode And( t.tahdrStatusCode= :status Or t.tahdrStatusCode= :status1) AND td.auctionToDate<NOW() And td.isActive='Y' ");	
		if(!"none".equalsIgnoreCase(searchValue)){
			jpql.append(" AND UPPER(t."+searchColumn+") LIKE upper(:searchValue)");
		}
		return jpql.toString();
	}
	
	@Override
	public String getQuickTahdrWithDetailsForAwardWinnerCount(String searchColumn, String searchValue)
	{
		StringBuilder jpql =  new StringBuilder();
		jpql.append(" c.tahdrTypeCode =:tenderTypeCode And ( c.tahdrStatusCode= :status OR c.tahdrStatusCode= :status1 )"
				+ " AND  NOW()>(SELECT t.auctionToDate FROM TAHDRDetail t WHERE t.isActive='Y' AND c.tahdrId=t.tahdr.tahdrId) ");
		if (!"none".equalsIgnoreCase(searchColumn))
			jpql.append(" and UPPER(c."+searchColumn+") LIKE upper(:searchValue)");
		return jpql.toString();
	}
	
	@Override
	public String getTahdrWithDetailsForAwardWinnerCount(String searchColumn, String searchValue)
	{
		StringBuilder jpql =  new StringBuilder();
		jpql.append(" c.tahdrTypeCode= :tenderTypeCode And c.tahdrStatusCode= :status");
		if (!"none".equalsIgnoreCase(searchColumn))
			jpql.append(" and UPPER(c."+searchColumn+") LIKE upper(:searchValue)");
		return jpql.toString();
	}
	
	@Override
	public String getRfqDetailsForAwardWinnerCount(String searchColumn, String searchValue)
	{
		StringBuilder jpql =  new StringBuilder();
		jpql.append(" c.tahdrTypeCode= :tenderTypeCode And ( c.tahdrStatusCode= :status OR c.tahdrStatusCode= :status1 ) "
				+ " AND  NOW()>(SELECT t.technicalBidToDate FROM TAHDRDetail t WHERE t.isActive='Y' AND c.tahdrId=t.tahdr.tahdrId) ");
		if (!"none".equalsIgnoreCase(searchColumn))
			jpql.append(" and UPPER(c."+searchColumn+") LIKE upper(:searchValue)");
		return jpql.toString();
	}
	
	/*@Override
	public String getQuickTahdrWithDetailsForAwardWinnerCount(String searchColumn, String searchValue)
	{
		StringBuilder jpql =  new StringBuilder();
		jpql.append(" c.tahdrTypeCode =:tenderTypeCode And c.tahdrStatusCode= :status AND c.tahdrDetail.auctionToDate<NOW() And c.tahdrDetail.isActive='Y'");
		if (!"none".equalsIgnoreCase(searchColumn))
			jpql.append(" and UPPER(c."+searchColumn+") LIKE upper(:searchValue)");
		return jpql.toString();
	}*/
	
	@Override
	public String getTahdrWithDetailsForAwardWinnerCount()
	{
		StringBuilder jpql =  new StringBuilder();
		jpql.append(" c.tahdrStatusCode= :status");
		return jpql.toString();
	}
	
	public String getAwardWinnerCount(){
		StringBuilder sb= new StringBuilder(getTahdrWithDetailsForAwardWinner());
		sb.append(" AND bid.status IN('"+AppBaseConstant.BIDDER_STATUS_ANNEXURE_C1_OPENED+"','"+AppBaseConstant.BIDDER_STATUS_Price_BID_OPENED+"')" );
		return sb.toString();
	}

	public String getDeviationTenderForBidder(){
		String jpql=getTahdrQuery();
		StringBuilder sb= new StringBuilder(jpql);
		sb.append(" INNER JOIN FETCH E.bidders bdr ");
		sb.append(" LEFT JOIN FETCH bdr.partner bp ");
		sb.append(" LEFT JOIN FETCH E.department dp ");
		sb.append(" LEFT JOIN FETCH E.tahdrDetail td ");
		sb.append(" WHERE bp.bPartnerId=:partnerId "
				+ " AND bdr.status IN ('DVTN','PARTPASPRELIMINARY_COMMERCIAL_PASSEDS','DBSU','PTP') AND E.tahdrTypeCode=:typeCode "
				+ "AND E.tahdrStatusCode=:status AND td.isActive='Y' AND td.deviationToDate>=now()");
				/*+ " AND td.isActive='Y' ");*/
		return sb.toString();
	}
	
	public String getDeviationMyTenderForBidder(){
		String jpql=getTahdrQuery();
		StringBuilder sb= new StringBuilder(jpql);
		sb.append(" INNER JOIN FETCH E.bidders bdr ");
		sb.append(" LEFT JOIN FETCH bdr.partner bp ");
		sb.append(" LEFT JOIN FETCH E.department dp ");
		sb.append(" LEFT JOIN FETCH E.tahdrDetail td ");
		sb.append(" WHERE bp.bPartnerId=:partnerId AND bdr.status IN ('DVTN','PARTPASPRELIMINARY_COMMERCIAL_PASSEDS') "
				+ "  AND E.tahdrStatusCode=:status AND td.isActive='Y' AND E.tahdrId=:tahdrId");
				/*+ " AND td.isActive='Y' ");*/
		return sb.toString();
	}
	
	@Override
	public String getOpeningTenderByUserId(TenderCommitteeDto tenderCommitteeDto){
		StringBuilder jpql=new StringBuilder(getTahdrQuery());
		jpql.append(" INNER JOIN FETCH E.tahdrDetail td ");
		/*jpql.append("  LEFT JOIN FETCH E.bidders bdr ");*/
		/*jpql.append(" LEFT JOIN FETCH td.tenderCommittee tc ");*/
		/*jpql.append(" LEFT JOIN FETCH tc.chairPerson cp ");
		jpql.append(" LEFT JOIN FETCH cp.userDetails ud ");
		jpql.append(" LEFT JOIN FETCH tc.participant cpp ");
		jpql.append(" LEFT JOIN FETCH cpp.user cu ");*/
		String where =  getUserWhereClause(tenderCommitteeDto);
		jpql.append(where);
		return jpql.toString();
	}
	
	public String getOpeningMyTenderByUserId(){
		StringBuilder jpql=new StringBuilder(getTahdrQuery());
		jpql.append(" INNER JOIN FETCH E.tahdrDetail td ");
		jpql.append("  LEFT JOIN FETCH E.bidders bdr ");
		/*jpql.append(" LEFT JOIN FETCH td.tenderCommittee tc ");*/
		/*jpql.append(" LEFT JOIN FETCH tc.chairPerson cp ");
		jpql.append(" LEFT JOIN FETCH cp.userDetails ud ");
		jpql.append(" LEFT JOIN FETCH tc.participant cpp ");
		jpql.append(" LEFT JOIN FETCH cpp.user cu ");*/
		jpql.append(" WHERE  E.tahdrId=:tahdrId AND E.tahdrId IN (SELECT tc.tahdr.tahdrId From TenderCommittee tc LEFT JOIN tc.participant pt LEFT JOIN tc.chairPerson cp LEFT JOIN pt.user "
				+ "WHERE tc.chairPerson.userId=:userId Or pt.user.userId=:userId) ");
		return jpql.toString();
	}
	
	@Override
	public String getOpeningTenderByPartnerId(TenderCommitteeDto tenderCommitteeDto){
		StringBuilder jpql=new StringBuilder(getTahdrQuery());
		jpql.append(" INNER JOIN FETCH E.tahdrDetail td ");
		jpql.append("  INNER JOIN FETCH E.bidders bdr ");
		jpql.append(" LEFT JOIN FETCH td.tenderCommittee tc ");
		jpql.append(" LEFT JOIN FETCH bdr.partner bp ");
		String where =  getBidderWhereClause(tenderCommitteeDto);
		jpql.append(where);
		return jpql.toString();
	}
	@Override
	public String getOpeningTenderByTahdrId(){
		StringBuilder jpql=new StringBuilder(getTahdrQuery());
		jpql.append(" INNER JOIN FETCH E.tahdrDetail td ");
		jpql.append("  LEFT JOIN FETCH E.bidders bdr ");
		jpql.append(" LEFT JOIN FETCH td.tenderCommittee tc ");
		jpql.append(" LEFT JOIN FETCH tc.chairPerson cp ");
		jpql.append(" LEFT JOIN FETCH cp.userDetails ud ");
		jpql.append(" LEFT JOIN FETCH tc.participant cpp ");
		jpql.append(" LEFT JOIN FETCH cpp.user cu ");
		jpql.append(" WHERE E.tahdrId=:tahdrId AND td.isActive='Y'  ");
		return jpql.toString();
	}
	private String getUserWhereClause(TenderCommitteeDto tenderCommitteeDto){
		StringBuilder where = new StringBuilder();
		where.append(" WHERE td.isActive =:isActive ");
		if(tenderCommitteeDto==null){
			return where.toString();
		}
		if(!tenderCommitteeDto.getTahdr().getTahdrTypeCode().equals("")){
			where.append(" AND upper(E.tahdrTypeCode)=upper(:tahdrTypeCode) ");
		}
		if(tenderCommitteeDto.getTenderVersion().getTechBidOpenningDate()!=null){
			where.append(" AND td.techBidOpenningDate<=:technicalOpening AND td.techBidOpenningDate<=now() AND E.tahdrStatusCode IN ('PU','TCOP') AND E.bidTypeCode ='TB' ");
		}
		if(tenderCommitteeDto.getTenderVersion().getDeviationOpenningDate()!=null ){
			where.append(" AND td.deviationOpenningDate<=:deviationOpening AND td.deviationOpenningDate<=now() AND E.tahdrStatusCode IN ('DBSCH','DBOP') ");
		}	
		if(tenderCommitteeDto.getTenderVersion().getC1OpenningDate()!=null){
			where.append(" AND td.c1OpenningDate<=:c1Opening AND td.c1OpenningDate<=now() AND E.tahdrStatusCode IN ('C1SCH','C1OP') ");
		}
		if(tenderCommitteeDto.getTenderVersion().getPriceBidOpenningDate()!=null){
				where.append(" AND ((td.priceBidOpenningDate<=:priceBidOpening AND td.priceBidOpenningDate<=now()) OR (td.revisedBidOpenningDate<=:priceBidOpening AND td.revisedBidOpenningDate<=now() )) "
						   + " AND ((E.tahdrStatusCode IN ('PBSCH','PBOP','RBSCH') AND E.bidTypeCode='TB') ");
	            where.append(" OR (E.tahdrStatusCode IN ('PBSCH','PBOP') AND E.bidTypeCode='SB')) ");
		}
		if(tenderCommitteeDto.getTahdr()!=null){
			if(!tenderCommitteeDto.getTahdr().getTahdrCode().equals("")){
				where.append(" AND upper(E.tahdrCode) LIKE :tahdrCode ");
			}
		}
		/*where.append("  And (tc.chairPerson.userId=:userId Or cpp.user.userId=:userId)");	*/
		where.append("  AND E.tahdrId IN (SELECT tc.tahdr.tahdrId From TenderCommittee tc LEFT JOIN tc.participant pt LEFT JOIN tc.chairPerson cp LEFT JOIN pt.user "
				+ "WHERE tc.chairPerson.userId=:userId Or pt.user.userId=:userId) ");
		return where.toString();
	}
	
	private String getBidderWhereClause(TenderCommitteeDto tenderCommitteeDto){
   		StringBuilder where = new StringBuilder();
   		/*where.append(" WHERE b.isActive =:isActive And b.partner.bPartnerId=:partnerId ");	*/
   		where.append(" WHERE bp.bPartnerId=:partnerId AND E.tahdrStatusCode NOT IN ('AP','IP','VO','DR','RJ') ");	
   		if(tenderCommitteeDto==null){
   			return where.toString();
   		}
   		if(!tenderCommitteeDto.getTahdr().getTahdrTypeCode().equals(""))
   		{
   			where.append(" AND upper(E.tahdrTypeCode)=upper(:tahdrTypeCode) ");
   		}
   		if(tenderCommitteeDto.getTenderVersion().getTechBidOpenningDate()!=null){
   			where.append(" AND date_trunc(td.techBidOpenningDate)<=:technicalOpening ");
   		}
   		if(tenderCommitteeDto.getTenderVersion().getDeviationOpenningDate()!=null ){
   			where.append(" AND date_trunc(td.deviationOpenningDate)<=:deviationOpening ");
   		}	
   		if(tenderCommitteeDto.getTenderVersion().getC1OpenningDate()!=null){
   			where.append(" AND date_trunc(td.c1OpenningDate)<=:c1Opening ");
   		}
   		if(tenderCommitteeDto.getTenderVersion().getPriceBidOpenningDate()!=null){
   			where.append(" AND date_trunc(td.priceBidOpenningDate)<=:priceBidOpening  ");
   		}
   		if(tenderCommitteeDto.getTahdr()!=null)
   		{
   			/*where.append(" AND upper(E.tahdrStatusCode)=upper(:status)  ");*/
   			if(!tenderCommitteeDto.getTahdr().getTahdrCode().equals("")){
   				where.append(" AND upper(E.tahdrCode) LIKE upper(:tenderCode)");
   			}
   		}
   		return where.toString();
   	}
	
	/*public String getTahdrListFromApprovalMatrixByRoleANDLoc()
	{
		StringBuilder jpql =  new StringBuilder(" Select t from TAHDR t ");
		jpql.append(" LEFT JOIN FETCH t.createdBy cb ");
		jpql.append(" LEFT JOIN FETCH t.department d ");
		jpql.append(" LEFT JOIN FETCH t.officeLocation o"); 
		jpql.append(" WHERE (EXISTS (select AM from TAHDRApprovalMatrix AM INNER JOIN  AM.user user INNER JOIN  AM.tahdr tahdr where user.userId= :userId and AM.status='"+AppBaseConstant.DOCUMENT_STATUS_IN_PROGRESS+"' and t.tahdrId=tahdr.tahdrId) ");
		jpql.append(" AND t.tahdrStatusCode='"+AppBaseConstant.DOCUMENT_STATUS_IN_PROGRESS+"' AND t.tahdrTypeCode=:typeCode AND t.officeLocation.locationType=:locationType) OR (t.createdBy=:userId AND t.tahdrTypeCode=:typeCode AND t.officeLocation.locationType=:locationType) ");
		jpql.append(" ORDER BY t.created DESC ");
		return jpql.toString();
	}*/
	
	/*public String getTahdrListFromApprovalMatrix()
	{
		StringBuilder jpql =  new StringBuilder(" Select t from TAHDR t ");
		jpql.append(" LEFT JOIN FETCH t.createdBy cb ");
		jpql.append(" LEFT JOIN FETCH t.department d ");
		jpql.append(" LEFT JOIN FETCH t.officeLocation o"); 
		jpql.append(" LEFT JOIN FETCH t.officeType ot");
		jpql.append(" WHERE EXISTS (select AM from TAHDRApprovalMatrix AM INNER JOIN  AM.user user INNER JOIN  AM.tahdr tahdr where user.userId= :userId and AM.status='"+AppBaseConstant.DOCUMENT_STATUS_IN_PROGRESS+"' and t.tahdrId=tahdr.tahdrId) ");
		jpql.append(" AND t.tahdrStatusCode='"+AppBaseConstant.DOCUMENT_STATUS_IN_PROGRESS+"' AND t.tahdrTypeCode=:typeCode ");
		jpql.append(" ORDER BY t.created DESC ");
		return jpql.toString();
	}*/
	
	@Override
	public String getTahdrListByRoleANDLoc(String searchColumn, String searchValue)
	{
		StringBuilder jpql =  new StringBuilder(" Select t from TAHDR t ");
		jpql.append(" LEFT JOIN FETCH t.createdBy cb ");
		jpql.append(" LEFT JOIN FETCH t.department d ");
		jpql.append(" LEFT JOIN FETCH t.officeType ot");
		jpql.append(" LEFT JOIN FETCH t.officeLocation o");
		/*jpql.append(" LEFT JOIN FETCH t.tahdrDetail td ");*/
		jpql.append(" WHERE ot.levels >=:levels ");
		/* AND t.officeLocation.locationType=:locationType ");*/
		jpql.append(" AND cb.userId=:userId AND  t.tahdrTypeCode=:typeCode ");
		if(!"none".equalsIgnoreCase(searchValue)){
			jpql.append(" AND UPPER(t."+searchColumn+") LIKE :searchValue");
		}
		jpql.append(" ORDER BY t.created DESC ");
		return jpql.toString();
	}
	
	@Override
	public String getTahdrListQueryCountQry(String searchColumn,String searchValue){
		StringBuilder jpql =  new StringBuilder(" c.officeType.levels >=:levels ");
		jpql.append(" AND c.createdBy.userId=:userId AND  c.tahdrTypeCode=:typeCode ");
		if(!"none".equalsIgnoreCase(searchValue)){
			jpql.append(" AND UPPER(c."+searchColumn+") LIKE :searchValue");
		}
		/*jpql.append(" ORDER BY c.created DESC ");*/
		return jpql.toString();
	}
	
	@Override
	public String getQuickAuctionListQueryCountQry(String searchColumn,String searchValue){
		StringBuilder jpql =  new StringBuilder("");
		jpql.append(" c.createdBy.userId=:userId AND  c.tahdrTypeCode=:typeCode ");
		if(!"none".equalsIgnoreCase(searchValue)){
			jpql.append(" AND UPPER(c."+searchColumn+") LIKE :searchValue");
		}
		/*jpql.append(" ORDER BY c.created DESC ");*/
		return jpql.toString();
	}
	
	@Override
	public String getTahdrAndTahdrDetailListByRoleANDLoc(String searchColumn, String searchValue)
	{
		StringBuilder jpql =  new StringBuilder(" Select t from TAHDR t ");
		jpql.append(" LEFT JOIN FETCH t.createdBy cb ");
		jpql.append(" LEFT JOIN FETCH t.department d ");
		jpql.append(" LEFT JOIN FETCH t.officeType ot");
		jpql.append(" LEFT JOIN FETCH t.officeLocation o"); 
		jpql.append(" LEFT JOIN FETCH t.tahdrDetail td ");
		jpql.append(" LEFT JOIN FETCH t.mom mom ");
		jpql.append(" WHERE ot.levels >=:levels AND td.isActive='Y' ");
		/* AND t.officeLocation.locationType=:locationType ");*/
		jpql.append(" AND cb.userId=:userId AND  t.tahdrTypeCode=:typeCode AND t.tahdrStatusCode NOT IN ('IP','DR') ");
		if(!"none".equalsIgnoreCase(searchValue)){
			jpql.append(" AND UPPER(t."+searchColumn+") LIKE :searchValue");
		}
		jpql.append(" ORDER BY t.updated DESC ");
		return jpql.toString();
	}
	
	public String getPublishedTahdrAndTahdrDetailListByRoleANDLoc()
	{
		StringBuilder jpql =  new StringBuilder(" Select E from TAHDR E ");
		jpql.append(" LEFT JOIN FETCH E.createdBy cb ");
		jpql.append(" LEFT JOIN FETCH E.department d ");
		jpql.append(" LEFT JOIN FETCH E.officeType ot");
		jpql.append(" LEFT JOIN FETCH E.officeLocation o"); 
		jpql.append(" LEFT JOIN FETCH E.tahdrDetail td ");
		jpql.append(" LEFT JOIN FETCH E.mom mom ");
		jpql.append(" WHERE ot.levels >=:levels AND td.isActive='Y' ");
		/* AND t.officeLocation.locationType=:locationType ");*/
		jpql.append(" AND cb.userId=:userId AND E.tahdrStatusCode='PU' AND E.tahdrTypeCode=:typeCode ");
		return jpql.toString();
	}
	
	@Override
	public String getAuctionListByRoleANDLoc(String searchColumn, String searchValue )
	{
		StringBuilder jpql =  new StringBuilder(" Select t from TAHDR t ");
		jpql.append(" LEFT JOIN FETCH t.createdBy cb ");
		jpql.append(" LEFT JOIN FETCH t.department d ");
		jpql.append(" LEFT JOIN FETCH t.officeType ot");
		jpql.append(" LEFT JOIN FETCH t.officeLocation o"); 
		jpql.append(" LEFT JOIN FETCH t.tahdrDetail td ");
		jpql.append(" LEFT JOIN FETCH t.mom mom ");
		jpql.append(" WHERE ot.levels >=:levels  AND td.isActive='Y' ");
		/* AND t.officeLocation.locationType=:locationType ");*/
		jpql.append(" AND cb.userId=:userId AND  t.tahdrTypeCode=:typeCode AND t.isAuction='Y' AND td.isActive='Y' AND t.tahdrStatusCode NOT IN ('IP','DR') ");
		if(!"none".equalsIgnoreCase(searchValue)){
			jpql.append(" AND UPPER(t."+searchColumn+") LIKE :searchValue");
		}
		jpql.append(" ORDER BY t.created DESC ");
		return jpql.toString();
	}
	
	public String getPublishedAuctionListByRoleANDLoc()
	{
		StringBuilder jpql =  new StringBuilder(" Select E from TAHDR E ");
		jpql.append(" LEFT JOIN FETCH E.createdBy cb ");
		jpql.append(" LEFT JOIN FETCH E.department d ");
		jpql.append(" LEFT JOIN FETCH E.officeType ot");
		jpql.append(" LEFT JOIN FETCH E.officeLocation o"); 
		jpql.append(" LEFT JOIN FETCH E.tahdrDetail td ");
		jpql.append(" LEFT JOIN FETCH E.mom mom ");
		jpql.append(" WHERE ot.levels >=:levels  AND td.isActive='Y' ");
		/* AND t.officeLocation.locationType=:locationType ");*/
		jpql.append(" AND cb.userId=:userId AND E.tahdrStatusCode='PU' AND E.tahdrTypeCode=:typeCode AND E.isAuction='Y' AND td.isActive='Y' ");
		return jpql.toString();
	}
	
	@Override
	public String getTahdrListFromApprovalMatrix(String searchColumn, String searchValue)
	{
		StringBuilder jpql =  new StringBuilder(" Select t from TAHDR t ");
		jpql.append(" LEFT JOIN FETCH t.createdBy cb ");
		jpql.append(" LEFT JOIN FETCH t.department d ");
		jpql.append(" LEFT JOIN FETCH t.officeLocation o"); 
		jpql.append(" LEFT JOIN FETCH t.officeType ot");
		jpql.append(" WHERE EXISTS (select AM from TAHDRApprovalMatrix AM INNER JOIN  AM.user user INNER JOIN  AM.tahdr tahdr where user.userId= :userId and AM.status='"+AppBaseConstant.DOCUMENT_STATUS_IN_PROGRESS+"' and t.tahdrId=tahdr.tahdrId) ");
		jpql.append(" AND t.tahdrStatusCode='"+AppBaseConstant.DOCUMENT_STATUS_IN_PROGRESS+"' AND t.tahdrTypeCode=:typeCode ");
		if(!"none".equalsIgnoreCase(searchValue)){
			jpql.append(" AND UPPER(t."+searchColumn+") LIKE :searchValue");
		}
		jpql.append(" ORDER BY t.created DESC ");
		return jpql.toString();
	}
	
	@Override
	public String getTahdrApprovalListQueryCountQry(String searchColumn,String searchValue){
		StringBuilder jpql =  new StringBuilder(" EXISTS (select AM from TAHDRApprovalMatrix AM INNER JOIN  AM.user user INNER JOIN  AM.tahdr tahdr where user.userId= :userId and AM.status='"+AppBaseConstant.DOCUMENT_STATUS_IN_PROGRESS+"' and c.tahdrId=tahdr.tahdrId) ");
		jpql.append(" AND c.tahdrStatusCode='"+AppBaseConstant.DOCUMENT_STATUS_IN_PROGRESS+"' AND c.tahdrTypeCode=:typeCode ");
		if(!"none".equalsIgnoreCase(searchValue)){
			jpql.append(" AND UPPER(c."+searchColumn+") LIKE :searchValue");
		}
		return jpql.toString();
	}

	
	public String getTahdrList()
	{
		StringBuilder jpql =  new StringBuilder(" Select t from TAHDR t ");
		jpql.append(" LEFT JOIN FETCH t.createdBy cb ");
		jpql.append(" LEFT JOIN FETCH t.department d ");
		jpql.append(" LEFT JOIN FETCH t.officeType ot");
		jpql.append(" LEFT JOIN FETCH t.officeLocation o"); 
		jpql.append(" WHERE cb.userId=:userId AND t.tahdrTypeCode=:typeCode ");
		jpql.append(" ORDER BY t.created DESC ");
		return jpql.toString();
	}
	
	public String getAuditoUserBytahdrId(){
		StringBuilder sb=new StringBuilder(" select t from TAHDR t ");
		sb.append(" LEFT JOIN FETCH t.auditorUser au ");
		sb.append(" WHERE  t.tahdrId=:tahdrId ");
		return sb.toString();
	}
	
	@Override
	public int openTender(Long tahdrId,String status,String remark) {
		StringBuilder jpql= new StringBuilder();
		jpql.append(" Update TAHDR b SET b.tahdrStatusCode=:tahdrStatusCode,b.bidOpenedDate=now(),b.remarks=:remarks ");
		jpql.append( " where b.tahdrId=:tahdrId ");
		
		Query query=getEntityManager().createQuery(jpql.toString());
		query.setParameter("tahdrId", tahdrId);
		query.setParameter("tahdrStatusCode", status);
		query.setParameter("remarks", remark);
		int count= query.executeUpdate();
		return count;
	}
	@Override
	public int uploadMOM(Long tahdrId,Long momAttachmentId) {
		StringBuilder jpql= new StringBuilder();
		jpql.append(" Update TAHDR b SET b.mom.attachmentId=:momAttachmentId");
		jpql.append( " where b.tahdrId=:tahdrId ");
		
		Query query=getEntityManager().createQuery(jpql.toString());
		query.setParameter("tahdrId", tahdrId);
		query.setParameter("momAttachmentId", momAttachmentId);
		int count= query.executeUpdate();
		return count;
	}
	public String getOnGoingAuctionListByTypeCode(){
		StringBuilder sb= new StringBuilder(getTahdrDetailQuery());
		sb.append(" LEFT JOIN FETCH E.bidders bdr ");
		sb.append(" LEFT JOIN FETCH E.officeLocation ofcl ");
		sb.append(" LEFT JOIN FETCH E.department d ");
		sb.append(" WHERE (E.tahdrTypeCode = :typeCode AND E.isAuction='Y') AND E.tahdrStatusCode='"+AppBaseConstant.AUCTION_SCHEDULING+"' AND td.auctionToDate>now() AND  td.auctionFromDate<now() AND "
				+ " (E.tahdrId IN (SELECT t.tahdrId FROM Bidder B "
				+ " LEFT JOIN B.partner P"
				+ " LEFT JOIN B.tahdr t WHERE P.bPartnerId=:partnerId )"
				+ " OR td.estimatedCost=0) ");
		/*sb.append(" WHERE E.tahdrTypeCode = :typeCode AND p.bPartnerId=:partnerId ");*/
		return sb.toString();
		
	}
	
	public String getOnGoingQuickAuctionListByTypeCode(){
		StringBuilder sb= new StringBuilder(getTahdrDetailQuery());
		sb.append(" LEFT JOIN FETCH E.bidders bdr ");
		/*sb.append(" LEFT JOIN FETCH bdr.partner p ");*/
		sb.append(" WHERE (E.tahdrTypeCode = :typeCode AND E.isAuction='Y') AND "
				+ " E.tahdrStatusCode='"+AppBaseConstant.DOCUMENT_STATUS_PUBLISHED+"' AND td.auctionToDate>NOW() AND  td.auctionFromDate<NOW() AND "
				+ " (E.tahdrId IN (SELECT t.tahdrId FROM Bidder B "
				+ " LEFT JOIN B.partner P"
				+ " LEFT JOIN B.tahdr t WHERE P.bPartnerId=:partnerId )"
				+ " OR td.estimatedCost=0) ");
		/*sb.append(" WHERE E.tahdrTypeCode = :typeCode AND p.bPartnerId=:partnerId ");*/
		return sb.toString();
		
	}
	
	public String getTahdrWithDetailAndDoc(){
		String str=getTahdrDetailQuery();
		StringBuilder jpql=new StringBuilder(str);
		jpql.append(" LEFT JOIN FETCH td.officeNote ofcNote ");
		jpql.append(" WHERE E.tahdrId=:tahdrId ");
		return jpql.toString();
	}
	
	@Override
	public String getMytenderCountQuery(){
		StringBuilder jpql =  new StringBuilder("EXISTS( Select user from User user ");
		jpql.append("where user.userId=:userId) ");
		return jpql.toString();
	}
	@Override
	public String getTahdrListForApprovalQuery()
	{
		StringBuilder jpql =  new StringBuilder();
		jpql.append(" WHERE EXISTS (select AM from TAHDRApprovalMatrix AM INNER JOIN  AM.user user INNER JOIN  AM.tahdr tahdr where user.userId= :userId and AM.status='"+AppBaseConstant.DOCUMENT_STATUS_IN_PROGRESS+"' and c.tahdrId=tahdr.tahdrId) ");
		jpql.append(" AND t.tahdrStatusCode='"+AppBaseConstant.DOCUMENT_STATUS_IN_PROGRESS+"'");
		return jpql.toString();
	}
	
	@Override
	public String getTahdrWithActiveDetail(){
		StringBuilder sb=new StringBuilder(getTahdrDetailQuery());
		sb.append(" WHERE E.tahdrId=:tahdrId and td.isActive='Y' ");
		return sb.toString();
	}
	
	/*@Override
	public String getAuctionListByRoleANDLocCountQry(String searchColumn, String searchValue)
	{
		StringBuilder jpql =  new StringBuilder();
		jpql.append(" c.officeType.levels >=:levels  AND c.tahdrDetail.isActive='Y' AND c.createdBy.userId=:userId ");
		jpql.append(" AND c.tahdrTypeCode= :typeCode AND c.isAuction='Y' And c.tahdrStatusCode NOT IN ('IP','DR') ");
		if (!"none".equalsIgnoreCase(searchColumn))
			jpql.append(" and UPPER(c."+searchColumn+") LIKE :searchValue");
		return jpql.toString();
	}*/
	
	
	/*@Override
	public String getMyAuctionForBidderCountQry(String searchColumn, String searchValue)
	{
		StringBuilder jpql =  new StringBuilder();
		jpql.append(" c.bidders.partner.bPartnerId=:partnerId AND c.tahdrTypeCode=:typeCode AND c.tahdrStatusCode NOT IN ('IP','DR') ");
		jpql.append(" AND c.isAuction='Y' ");
		if (!"none".equalsIgnoreCase(searchColumn))
			jpql.append(" and UPPER(c."+searchColumn+") LIKE :searchValue");
		return jpql.toString();
	}*/
	
	@Override
	public String getAuctionListsByRoleCountQry(String searchColumn, String searchValue)
	{
		StringBuilder jpql =  new StringBuilder();
		jpql.append(" c.tahdrTypeCode=:typeCode AND c.tahdrStatusCode IN(:tenderStatusList) AND c.isAuction='Y' ");
		if (!"none".equalsIgnoreCase(searchColumn))
			jpql.append(" and UPPER(c."+searchColumn+") LIKE :searchValue");
		return jpql.toString();
	}
	
	/*@Override
	public String getTahdrAndTahdrDetailListByRoleANDLocCountQry(String searchColumn, String searchValue)
	{
		StringBuilder jpql =  new StringBuilder();
		jpql.append(" c.officeType.levels >=:levels  AND c.tahdrDetail.isActive='Y' AND c.createdBy.userId=:userId ");
		jpql.append(" AND c.tahdrTypeCode= :typeCode And c.tahdrStatusCode NOT IN ('IP','DR') ");
		if (!"none".equalsIgnoreCase(searchColumn))
			jpql.append(" and UPPER(c."+searchColumn+") LIKE :searchValue");
		return jpql.toString();
	}*/
	@Override
	public String getTahdrListByCodeAndUser(String searchColumn, String searchValue)
	{
		StringBuilder jpql =  new StringBuilder(" Select t from TAHDR t ")
		.append(" LEFT JOIN FETCH t.tahdrDetail td ")
		.append(" LEFT JOIN FETCH t.createdBy cb ")
		.append(" WHERE cb.userId=:userId AND  t.tahdrTypeCode=:typeCode ");
		if(!"none".equalsIgnoreCase(searchValue)){
			jpql.append(" AND UPPER(t."+searchColumn+") LIKE :searchValue");
		}
		jpql.append(" ORDER BY t.created DESC ");
		return jpql.toString();
	}
	
	@Override
	public String getTenderListsAuditorIdCountQry(String searchColumn, String searchValue)
	{
		StringBuilder jpql =  new StringBuilder();
		jpql.append(" c.tahdrTypeCode=:typeCode AND c.auditorUser.userId=:userId AND c.tahdrStatusCode IN(:tenderStatusList) ");
		if (!"none".equalsIgnoreCase(searchColumn))
			jpql.append(" and UPPER(c."+searchColumn+") LIKE :searchValue");
		return jpql.toString();
	}
	
	@Override
	@Transactional
	public long getAuctionListByRoleANDLocCountQry(String tenderTypeCode,Long userId,Long levels,String searchColumn, String searchValue)
	{
		StringBuilder jpql =  new StringBuilder(" Select COUNT(t) from TAHDR t ");
		jpql.append(" LEFT JOIN  t.createdBy cb ");
		jpql.append(" LEFT JOIN  t.department d ");
		jpql.append(" LEFT JOIN  t.officeType ot");
		jpql.append(" LEFT JOIN  t.officeLocation o"); 
		jpql.append(" LEFT JOIN  t.tahdrDetail td ");
		jpql.append(" LEFT JOIN  t.mom mom ");
		jpql.append(" WHERE ot.levels >=:levels  AND td.isActive='Y' ");
		jpql.append(" AND cb.userId=:userId AND  t.tahdrTypeCode=:typeCode AND t.isAuction='Y' AND td.isActive='Y' AND t.tahdrStatusCode NOT IN ('IP','DR') ");
		if(!"none".equalsIgnoreCase(searchValue)){
			jpql.append(" AND UPPER(t."+searchColumn+") LIKE :searchValue");
		}
		Query q = getEntityManager().createQuery(jpql.toString());
		q.setParameter("levels", levels);
		q.setParameter("userId", userId);
		q.setParameter("typeCode", tenderTypeCode);
		if(!"none".equalsIgnoreCase(searchValue)){
		q.setParameter("searchValue", "%" + searchValue + "%");}
		Long count= (Long) q.getSingleResult();
		return count;
	}
	
	/*@Override
	public String getTahdrAndTahdrDetailListByRoleANDLocCountQry(String searchColumn, String searchValue)
	{
		StringBuilder jpql =  new StringBuilder();
		jpql.append(" c.officeType.levels >=:levels  AND c.tahdrDetail.isActive='Y' AND c.createdBy.userId=:userId ");
		jpql.append(" AND c.tahdrTypeCode= :typeCode And c.tahdrStatusCode NOT IN ('IP','DR') ");
		if (!"none".equalsIgnoreCase(searchColumn))
			jpql.append(" and UPPER(c."+searchColumn+") LIKE :searchValue");
		return jpql.toString();
	}*/
	
	@Override
	@Transactional
	public long getMyAuctionForBidderCountQry(String typeCode,Long partnerId,String searchColumn,String searchValue){
		StringBuilder sb =  new StringBuilder(" Select COUNT(E) from TAHDR E ");
		sb.append(" LEFT JOIN  E.tahdrDetail td ");
		sb.append(" LEFT JOIN  td.tenderDoc doc ");
		sb.append(" LEFT JOIN  td.officeNote ofno ");
		sb.append(" INNER JOIN FETCH E.bidders bdr ");
		sb.append(" LEFT JOIN bdr.partner bp ");
		sb.append(" LEFT JOIN FETCH E.department dp ");
		sb.append(" LEFT JOIN FETCH E.officeType ot ");
		sb.append(" LEFT JOIN FETCH E.officeLocation ol ");
		sb.append(" WHERE bp.bPartnerId=:partnerId  and E.tahdrTypeCode=:typeCode AND E.tahdrStatusCode NOT IN ('IP','DR') AND E.isAuction='Y' ");
			if(!"none".equalsIgnoreCase(searchValue)){
				sb.append(" AND UPPER(E."+searchColumn+") LIKE :searchValue");
			}
			Query q = getEntityManager().createQuery(sb.toString());
			q.setParameter("partnerId", partnerId);
			q.setParameter("typeCode", typeCode);
			if(!"none".equalsIgnoreCase(searchValue)){
			q.setParameter("searchValue", "%" + searchValue + "%");}
			Long count= (Long) q.getSingleResult();
			return count;
		}


	@Override
	@Transactional
	public long getTahdrAndTahdrDetailListByRoleANDLocCountQry(String tenderTypeCode,Long userId,Long levels,String searchColumn, String searchValue)
	{
		StringBuilder jpql =  new StringBuilder(" Select COUNT(t) from TAHDR t ");
		jpql.append(" LEFT JOIN  t.createdBy cb ");
		jpql.append(" LEFT JOIN  t.department d ");
		jpql.append(" LEFT JOIN  t.officeType ot");
		jpql.append(" LEFT JOIN  t.officeLocation o"); 
		jpql.append(" LEFT JOIN  t.tahdrDetail td ");
		jpql.append(" LEFT JOIN  t.mom mom ");
		jpql.append(" WHERE ot.levels >=:levels  AND td.isActive='Y' ");
		jpql.append(" AND cb.userId=:userId AND  t.tahdrTypeCode=:typeCode  AND t.tahdrStatusCode NOT IN ('IP','DR') ");
		if(!"none".equalsIgnoreCase(searchValue)){
			jpql.append(" AND UPPER(t."+searchColumn+") LIKE :searchValue");
		}
		Query q = getEntityManager().createQuery(jpql.toString());
		q.setParameter("levels", levels);
		q.setParameter("userId", userId);
		q.setParameter("typeCode", tenderTypeCode);
		if(!"none".equalsIgnoreCase(searchValue)){
		q.setParameter("searchValue", "%" + searchValue + "%");}
		Long count= (Long) q.getSingleResult();
		return count;
	}
	
	public String getOnGoingAuctionListByTahdrId(){
		StringBuilder sb= new StringBuilder(getTahdrDetailQuery());
		sb.append(" LEFT JOIN FETCH E.bidders bdr ");
		sb.append(" LEFT JOIN FETCH E.officeLocation ofcl ");
		sb.append(" WHERE (E.tahdrId=:tahdrId AND E.isAuction='Y') AND E.tahdrStatusCode='"+AppBaseConstant.AUCTION_SCHEDULING+"' AND td.auctionToDate>now() AND  td.auctionFromDate<now() ");
				/*+ " (E.tahdrId IN (SELECT t.tahdrId FROM Bidder B "
				+ " LEFT JOIN B.partner P"
				+ " LEFT JOIN B.tahdr t WHERE P.bPartnerId=:partnerId )"
				+ " OR td.estimatedCost=0) ");*/
		/*sb.append(" WHERE E.tahdrTypeCode = :typeCode AND p.bPartnerId=:partnerId ");*/
		return sb.toString();
	}


	@Override
	public String getTahdrApprovalListQueryForWorkflow(Map<String, Object> param) {
		StringBuilder jpql =  new StringBuilder(" EXISTS (select AM from TAHDRApprovalMatrix AM INNER JOIN  AM.user user INNER JOIN  AM.tahdr tahdr where user.userId= :userId and AM.status='"+AppBaseConstant.DOCUMENT_STATUS_IN_PROGRESS+"' and c.tahdrId=tahdr.tahdrId) ");
		jpql.append(" AND c.tahdrStatusCode='"+AppBaseConstant.DOCUMENT_STATUS_IN_PROGRESS+"' AND  c.tahdrTypeCode IN('PT','WT') ");
		jpql.append(" ORDER BY c.created DESC ");
		return jpql.toString();
	}
	@Override
	@Transactional
	public long getMyTenderForBidderCountQry(String typeCode,Long partnerId,String searchColumn,String searchValue){		
		StringBuilder sb= new StringBuilder(" Select COUNT(E) from TAHDR E ");
		sb.append(" LEFT JOIN  E.tahdrDetail td ");
		sb.append(" LEFT JOIN  td.tenderDoc doc ");
		sb.append(" LEFT JOIN  td.officeNote ofno ");
		sb.append(" INNER JOIN  E.bidders bdr ");
		sb.append(" LEFT JOIN bdr.partner bp ");
		sb.append(" LEFT JOIN  E.department dp ");
		sb.append(" LEFT JOIN  E.officeType ot ");
		sb.append(" LEFT JOIN  E.officeLocation ol ");
		sb.append(" WHERE bp.bPartnerId=:partnerId  and E.tahdrTypeCode=:typeCode AND E.tahdrStatusCode NOT IN ('IP','DR')  ");
		if(!"none".equalsIgnoreCase(searchValue)){
			sb.append(" AND UPPER(E."+searchColumn+") LIKE :searchValue");
		}
		Query q = getEntityManager().createQuery(sb.toString());
		q.setParameter("partnerId", partnerId);
		q.setParameter("typeCode", typeCode);
		if(!"none".equalsIgnoreCase(searchValue)){
		q.setParameter("searchValue", "%" + searchValue + "%");}
		Long count= (Long) q.getSingleResult();
		return count;
	}
	
		@Override
	public String getAuctionApprovalListQueryForWorkflow(Map<String, Object> param) {
		StringBuilder jpql =  new StringBuilder(" EXISTS (select AM from TAHDRApprovalMatrix AM INNER JOIN  AM.user user INNER JOIN  AM.tahdr tahdr where user.userId= :userId and AM.status='"+AppBaseConstant.DOCUMENT_STATUS_IN_PROGRESS+"' and c.tahdrId=tahdr.tahdrId) ");
		jpql.append(" AND c.tahdrStatusCode='"+AppBaseConstant.DOCUMENT_STATUS_IN_PROGRESS+"' AND  c.tahdrTypeCode = 'FA' ");
		jpql.append(" ORDER BY c.created DESC ");
		return jpql.toString();
	}
	
	@Override
	public String getTahdrListOfOpeningForReminderMail(String status){
		StringBuilder jpql= new StringBuilder(" Select tahdr from TAHDR tahdr ");
		jpql.append(" LEFT JOIN Fetch tahdr.tahdrDetail td ");
		jpql.append(" LEFT JOIN FETCH tahdr.createdBy cb ");
		jpql.append(" LEFT JOIN FETCH tahdr.partner bp ");
		if(status.equalsIgnoreCase(AppBaseConstant.DOCUMENT_STATUS_TC_BID_OPENED)){
			jpql.append(" where TRUNC(now()+1) = TRUNC(td.techBidOpenningDate)  AND tahdr.tahdrStatusCode='"+AppBaseConstant.DOCUMENT_STATUS_PUBLISHED+"' AND tahdr.bidTypeCode='"+AppBaseConstant.BID_TYPE_CODE_TWO_BID+"' ");
		}
		else if(status.equalsIgnoreCase(AppBaseConstant.DOCUMENT_STATUS_PRICE_BID_OPENED)){
			jpql.append(" where TRUNC(now()+1) = TRUNC(td.priceBidOpenningDate)  AND tahdr.tahdrStatusCode='"+AppBaseConstant.TENDER_PRICE_BID_SCHEDULING+"' ");
		}
		else if(status.equalsIgnoreCase(AppBaseConstant.DOCUMENT_STATUS_DEVIATION_BID_OPENED)){
			jpql.append(" where TRUNC(now()+1) = TRUNC(td.deviationOpenningDate)  AND tahdr.tahdrStatusCode='"+AppBaseConstant.TENDER_DEVIATION_BID_SCHEDULING+"' ");
		}
		else if(status.equalsIgnoreCase(AppBaseConstant.DOCUMENT_STATUS_C1_BID_OPENED)){
			jpql.append(" where TRUNC(now()+1) = TRUNC(td.c1OpenningDate)  AND tahdr.tahdrStatusCode='"+AppBaseConstant.TENDER_ANNEXURE_C1_SCHEDULING+"' ");
		}
		return jpql.toString();
	}

	@Override
	public String getTenderListsByRoleCountQry(String searchColumn, String searchValue){
		/*StringBuilder sb= new StringBuilder(getQueryForTAHDRByTypeCode());*/
		StringBuilder sb =  new StringBuilder();
		sb.append(" c.tahdrTypeCode=:typeCode AND c.tahdrStatusCode IN(:tenderStatusList) ");
		if (!"none".equalsIgnoreCase(searchColumn))
			sb.append(" and UPPER(c."+searchColumn+") LIKE :searchValue");
		return sb.toString();
	}
	public String getPrivateAuctionList(){
		StringBuilder jpql = new StringBuilder();
		jpql.append(" Select DISTINCT(t) from TAHDR t ")
		.append(" LEFT JOIN FETCH t.tahdrDetail td")
		.append(" LEFT JOIN FETCH t.auctionParticipant p")
		.append(" LEFT JOIN FETCH p.bPartner bp")
		.append(" WHERE t.partner.bPartnerId = :BPartnerId")
		.append(" AND t.isPrivateAuction = 'Y' AND t.tahdrStatusCode ='PU' ")
		/*.append(" AND NOW() BETWEEN td.purchaseFromDate AND td.purchaseToDate");*/
		.append(" ORDER BY t.created desc ");
		return jpql.toString();
	}
	
	public String getQuickRFQ(){
		StringBuilder jpql = new StringBuilder();
		jpql.append(" Select DISTINCT(t) from TAHDR t ")
		.append(" LEFT JOIN FETCH t.tahdrDetail td")
		.append(" LEFT JOIN FETCH t.auctionParticipant p")
		.append(" LEFT JOIN FETCH p.bPartner bp")
		.append(" WHERE t.partner.bPartnerId = :BPartnerId")
		/*.append(" AND NOW() BETWEEN td.technicalBidFromDate AND td.technicalBidFromDate");*/
		.append(" AND t.tahdrStatusCode ='PU' AND t.tahdrTypeCode ='QRFQ' ORDER BY t.created desc");
		return jpql.toString();
	}
	
	/*public String getTenderByTypeCode(){
		StringBuilder jpql = new StringBuilder();
		jpql.append(" Select DISTINCT(t) from TAHDR t ")
		.append(" LEFT JOIN FETCH t.tahdrDetail td")
		.append(" LEFT JOIN FETCH t.auctionParticipant p")
		.append(" LEFT JOIN FETCH p.bPartner bp")
		.append(" WHERE t.partner.bPartnerId = :BPartnerId")
		
		.append(" AND t.tahdrStatusCode =:status AND t.tahdrTypeCode IN :typeCode ORDER BY t.created desc");
		return jpql.toString();
	}*/
	
	public String getRFQ(){
		StringBuilder jpql = new StringBuilder();
		jpql.append(" Select DISTINCT(t) from TAHDR t ")
		.append(" LEFT JOIN FETCH t.tahdrDetail td")
		.append(" LEFT JOIN FETCH t.auctionParticipant p")
		.append(" LEFT JOIN FETCH p.bPartner bp")
		.append(" WHERE t.partner.bPartnerId = :BPartnerId")
		/*.append(" AND NOW() BETWEEN td.technicalBidFromDate AND td.technicalBidFromDate");*/
		.append(" AND t.tahdrStatusCode ='PU' AND t.tahdrTypeCode ='RFQ' ORDER BY t.created desc");
		return jpql.toString();
	}
	
	public String getPrivateAuctionListByID(){
		StringBuilder jpql = new StringBuilder();
		jpql.append(" Select t from TAHDR t ")
		.append(" LEFT JOIN FETCH t.auctionParticipant p")
		.append(" LEFT JOIN FETCH p.bPartner bp")
		.append(" WHERE t.tahdrId = :tahdrId");
		
		return jpql.toString();
	}

	@Override
	public String getTahdrListOfSubmissionForReminderMail(String status){
		StringBuilder jpql= new StringBuilder(" Select tahdr from TAHDR tahdr ");
		jpql.append(" LEFT JOIN Fetch tahdr.tahdrDetail td ");
		jpql.append(" LEFT JOIN FETCH tahdr.createdBy cb ");
		jpql.append(" LEFT JOIN FETCH tahdr.partner bp ");
		if(status.equalsIgnoreCase(AppBaseConstant.Bid_Submission)){
			jpql.append(" where TRUNC(now()+1) = TRUNC(td.technicalBidToDate)  AND tahdr.tahdrStatusCode='"+AppBaseConstant.DOCUMENT_STATUS_PUBLISHED+"' ");
		}
		else if(status.equalsIgnoreCase(AppBaseConstant.Deviation_Bid)){
			jpql.append(" where TRUNC(now()+1) = TRUNC(td.deviationToDate)  AND tahdr.tahdrStatusCode='"+AppBaseConstant.TENDER_DEVIATION_BID_SCHEDULING+"' ");
		}
		else if(status.equalsIgnoreCase(AppBaseConstant.Annexture_C1)){
			jpql.append(" where TRUNC(now()+1) = TRUNC(td.c1ToDate)  AND tahdr.tahdrStatusCode='"+AppBaseConstant.TENDER_ANNEXURE_C1_SCHEDULING+"' ");
		}
		return jpql.toString();
	}


	@Override
	public String getAuctionListByRoleANDLoc() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public String getMyAuctionForBidder() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<TAHDR> getTenderByTypeCode(Long partnerId,String status,	 String typeCode) {
		StringBuilder jpql = new StringBuilder();
		jpql.append(" Select DISTINCT(t) from TAHDR t ");
		jpql.append(" LEFT JOIN FETCH t.tahdrDetail td");
		jpql.append(" LEFT JOIN FETCH t.auctionParticipant p");
		jpql.append(" LEFT JOIN FETCH p.bPartner bp");
		jpql.append(" LEFT JOIN FETCH td.tenderDoc doc ");
		jpql.append(" LEFT JOIN FETCH td.officeNote ofno ");
		jpql.append(" WHERE t.partner.bPartnerId = :partnerId");
		/*jpql.append(" AND NOW() BETWEEN td.technicalBidFromDate AND td.technicalBidFromDate");*/
		jpql.append(" AND t.tahdrStatusCode =:status ");
		if(typeCode!=null){
			if(typeCode.equalsIgnoreCase("TENDER")){
				jpql.append(" AND  t.tahdrTypeCode IN ('PT','WT') ");
			}else if(typeCode.equalsIgnoreCase("AUCTION")){
				jpql.append(" AND  t.tahdrTypeCode IN ('FA','RA') ");
			}else if(typeCode.equalsIgnoreCase("QUICK_AUCTION")){
				jpql.append(" AND  t.tahdrTypeCode IN ('QRA','QFA') ");
			}else if(typeCode.equalsIgnoreCase("QUICK_RFQ")){
				jpql.append(" AND  t.tahdrTypeCode IN ('QRFQ') ");
			}else if(typeCode.equalsIgnoreCase("RFQ")){
				jpql.append(" AND  t.tahdrTypeCode IN ('RFQ') ");
			}
		}
		jpql.append(" ORDER BY t.created desc");
		Query q = getEntityManager().createQuery(jpql.toString(),TAHDR.class);
		q.setParameter("partnerId", partnerId);
		q.setParameter("status", status);
		
		@SuppressWarnings("unchecked")
		List<TAHDR> tahdrList=  q.getResultList();
		return tahdrList;
	}
}
