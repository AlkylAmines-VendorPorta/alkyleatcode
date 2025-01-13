package com.novelerp.eat.dao.impl;

import javax.annotation.PostConstruct;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.novelerp.core.dao.impl.AbstractJpaDAO;
import com.novelerp.eat.dao.TenderCommitteeDao;
import com.novelerp.eat.dto.TenderCommitteeDto;
import com.novelerp.eat.entity.TenderCommittee;
/**
 * 
 * @author varsha
 *
 */
@Repository
public class TenderCommitteeDaoImpl extends AbstractJpaDAO<TenderCommittee, TenderCommitteeDto> implements TenderCommitteeDao{
	@PostConstruct
	public void postConstruct() {
		setClazz(TenderCommittee.class, TenderCommitteeDto.class);
	}
	public String getQuery()
	{
		StringBuilder jpql=new StringBuilder(" SELECT DISTINCT c from TenderCommittee c ");
		jpql.append(" LEFT JOIN FETCH c.tahdr tah ");
		jpql.append(" LEFT JOIN FETCH c.tenderVersion tv ");
		jpql.append(" LEFT JOIN FETCH c.chairPerson cp ");
		jpql.append(" LEFT JOIN FETCH cp.userDetails ud ");
		return jpql.toString();
	}
	
	public String getQueryForTenderCommittees(){
		StringBuilder jpql=new StringBuilder(getQuery());
		jpql.append("  where c.isActive='Y' ");
		return jpql.toString();
	}
	
	public String getTenderCommitteesByOpeningType(){
		StringBuilder jpql=new StringBuilder(getQuery());
		/*jpql.append("  where tah.tahdrStatusCode IN (:status1, :status2, (CASE WHEN :status3 IS NOT NULL Then :status3 END)) AND c.bidOpeningType=:openingType ");*/
		/*jpql.append("  WHERE c.bidOpeningType=:openingType AND (tah.tahdrTypeCode=:typeCode1 OR tah.tahdrTypeCode=:typeCode2) ORDER BY tah.tahdrCode ");*/
		jpql.append("  WHERE c.bidOpeningType=:openingType AND (tah.tahdrTypeCode=:typeCode1 OR tah.tahdrTypeCode=:typeCode2) ");
		return jpql.toString();
	}
	
	public String getAuctionCommitteesByOpeningType(){
		StringBuilder jpql=new StringBuilder(getQuery());
		/*jpql.append("  where tah.tahdrStatusCode IN (:status1, :status2, (CASE WHEN :status3 IS NOT NULL Then :status3 END)) AND c.bidOpeningType=:openingType ");*/
		/*jpql.append("  WHERE c.bidOpeningType=:openingType AND (tah.tahdrTypeCode=:typeCode1 OR tah.tahdrTypeCode=:typeCode2) ORDER BY tah.tahdrCode ");*/
		jpql.append("  WHERE c.bidOpeningType=:openingType AND (tah.tahdrTypeCode=:typeCode1 OR tah.tahdrTypeCode=:typeCode2) AND tah.isAuction='Y' ");
		return jpql.toString();
	}
	
	public String getQueryForCommitteeAlreadyExist(){
		StringBuilder jpql=new StringBuilder(" Select tc from TenderCommittee tc ");
		jpql.append(" INNER JOIN FETCH tc.tahdr t");
		jpql.append(" INNER JOIN FETCH tc.tenderVersion td");
		jpql.append(" where tc.tahdr.tahdrId=:tahdrId and tc.tenderVersion.tahdrDetailId=:tahdrDetailId AND tc.bidOpeningType=:bidOpeningType ");
		return jpql.toString();
	}
	
	public String getAssociatedTendersQuery(TenderCommitteeDto tenderCommitteeDto)
	{
		StringBuilder jpql=new StringBuilder(getQuery());
		jpql.append(" LEFT JOIN FETCH tah.bidders bd ");
		jpql.append(" LEFT JOIN FETCH c.participant cpp ");
		String where =  getWhereClause(tenderCommitteeDto);
		jpql.append(where);
		return jpql.toString();
	}
	
	public String getAssociatedAllTendersQuery()
	{
		StringBuilder jpql=new StringBuilder(getQuery());
		jpql.append(" LEFT JOIN FETCH tah.bidders bd ");
		jpql.append(" LEFT JOIN FETCH c.participant cpp ");
		jpql.append(" WHERE tah.tahdrTypeCode=:typeCode AND tah.tahdrStatusCode NOT IN ('AP','IP','VO','DR') ");
		return jpql.toString();
	}
	
	private String getWhereClause(TenderCommitteeDto tenderCommitteeDto){
		StringBuilder where = new StringBuilder();
		where.append(" WHERE c.isActive =:isActive AND tah.tahdrStatusCode NOT IN ('AP','IP','VO','DR','RJ') ");
		/*where.append(" WHERE c.isActive =:isActive ");*/
		if(tenderCommitteeDto==null){
			return where.toString();
		}
		if(!tenderCommitteeDto.getTahdr().getTahdrTypeCode().equals(""))
		{
			where.append(" AND upper(c.tahdr.tahdrTypeCode)=upper(:tahdrTypeCode) ");
		}
		if(tenderCommitteeDto.getTenderVersion().getTechBidOpenningDate()!=null){
			where.append(" AND Trunc(c.tenderVersion.techBidOpenningDate)<=:technicalOpening ");
		}
		if(tenderCommitteeDto.getTenderVersion().getDeviationOpenningDate()!=null ){
			where.append(" AND Trunc(c.tenderVersion.deviationOpenningDate)<=:deviationOpening ");
		}	
		if(tenderCommitteeDto.getTenderVersion().getC1OpenningDate()!=null){
			where.append(" AND Trunc(c.tenderVersion.c1OpenningDate)<=:c1Opening ");
		}
		if(tenderCommitteeDto.getTenderVersion().getPriceBidOpenningDate()!=null){
			where.append(" AND Trunc(c.tenderVersion.priceBidOpenningDate)<=:priceBidOpening  ");
		}
		if(tenderCommitteeDto.getTahdr()!=null)
		{
			if(!tenderCommitteeDto.getTahdr().getTahdrCode().equals("")){
				where.append(" AND upper(c.tahdr.tahdrCode) LIKE upper(:tenderCode)");
			}
		}
		where.append("  And (c.chairPerson.userId=:userId Or cpp.user.userId=:userId)");	
		return where.toString();
	}
	
	public String getTenderCommitteeById()
	{
		StringBuilder jpql=new StringBuilder(" SELECT DISTINCT c from TenderCommittee c ");
		jpql.append(" LEFT JOIN FETCH c.tahdr tah ");
		jpql.append(" LEFT JOIN FETCH c.chairPerson cp ");
		jpql.append(" LEFT JOIN FETCH c.participant cpp ");
		jpql.append(" LEFT JOIN FETCH cpp.user cu ");
		jpql.append(" WHERE c.tenderCommitteeId=:tenderCommitteId");
		return jpql.toString();
	}
	
	public String getTenderCommitteeByTahdrId()
	{
		StringBuilder jpql=new StringBuilder(" SELECT DISTINCT c from TenderCommittee c ");
		jpql.append(" LEFT JOIN FETCH c.tahdr tah ");
		jpql.append(" LEFT JOIN FETCH c.chairPerson cp ");
		jpql.append(" LEFT JOIN FETCH c.participant cpp ");
		jpql.append(" LEFT JOIN FETCH cpp.user cu ");
		jpql.append(" WHERE tah.tahdrId=:tahdrId");
		return jpql.toString();
	}
	
	public String getTenderCommitteeByTahdrIdAndOpeningType()
	{
		StringBuilder jpql=new StringBuilder(" SELECT DISTINCT c from TenderCommittee c ");
		jpql.append(" LEFT JOIN FETCH c.tahdr tah ");
		jpql.append(" LEFT JOIN FETCH c.chairPerson cp ");
		jpql.append(" LEFT JOIN FETCH c.participant cpp ");
		jpql.append(" LEFT JOIN FETCH cpp.user cu ");
		jpql.append(" WHERE tah.tahdrId=:tahdrId AND c.bidOpeningType=:openingType ");
		return jpql.toString();
	}
	public String getUserTenderCommitteeByTahdrIdAndOpeningType()
	{
		StringBuilder jpql=new StringBuilder(" SELECT DISTINCT c from TenderCommittee c ");
		jpql.append(" LEFT JOIN FETCH c.tahdr tah ");
		jpql.append(" LEFT JOIN FETCH c.chairPerson cp ");
		jpql.append(" LEFT JOIN FETCH c.participant cpp ");
		jpql.append(" LEFT JOIN FETCH cpp.user cu ");
		jpql.append(" WHERE tah.tahdrId=:tahdrId AND c.bidOpeningType=:openingType AND cp.userId=:userId");
		return jpql.toString();
	}
	
	@Override
	public int updateSessionKey(Long tenderCommitteeId,String sessionKey) {
		StringBuilder jpql= new StringBuilder();
		jpql.append(" Update TenderCommittee b SET sessionKey=:sessionKey ");
		jpql.append( " where b.tenderCommitteeId=:tenderCommitteeId ");
		
		Query query=getEntityManager().createQuery(jpql.toString());
		query.setParameter("tenderCommitteeId", tenderCommitteeId);
		query.setParameter("sessionKey", sessionKey);
		int count= query.executeUpdate();
		return count;
	}
	
	@Override
	public int isSessionkeyMailed(Long tenderCommitteeId) {
		StringBuilder jpql= new StringBuilder();
		jpql.append(" Update TenderCommittee b SET isMailed='Y' ");
		jpql.append( " where b.tenderCommitteeId=:tenderCommitteeId ");
		
		Query query=getEntityManager().createQuery(jpql.toString());
		query.setParameter("tenderCommitteeId", tenderCommitteeId);
		int count= query.executeUpdate();
		return count;
	}
	
	/*@Override
	public int setOtherTenderCommitteeInActive(Long tahdrId,Long tahdrDetailId,String bidOpeningType ) {
		StringBuilder jpql= new StringBuilder();
		jpql.append(" Update TenderCommittee b SET b.isActive='N' ");
		jpql.append( " where b.tahdr.tahdrId=:tahdrId AND b.tenderVersion.tahdrDetailId=:tahdrDetailId AND b.bidOpeningType=:bidOpeningType ");
		
		Query query=getEntityManager().createQuery(jpql.toString());
		query.setParameter("tahdrId", tahdrId);
		query.setParameter("tahdrDetailId", tahdrDetailId);
		query.setParameter("bidOpeningType", bidOpeningType);
		int count= query.executeUpdate();
		return count;
	}*/
	
	public String getChairPerson(){
		StringBuilder jpql=new StringBuilder(" SELECT DISTINCT c from TenderCommittee c ");
		jpql.append(" WHERE c.tenderCommitteeId=:tenderCommitteId AND c.chairPerson.userId=:userId");
		return jpql.toString();
	}
	
	public String getQueryForMailListOfCommitteeByTahdrId(){
		
		StringBuilder jpql= new StringBuilder();
		jpql.append(" Select c from TenderCommittee c ");
		jpql.append(" LEFT JOIN FETCH c.createdBy cr ");
		jpql.append(" LEFT JOIN FETCH c.chairPerson cp ");
		jpql.append(" LEFT JOIN FETCH cp.partner p ");
		jpql.append(" LEFT JOIN FETCH c.tahdr t ");
		jpql.append(" LEFT JOIN FETCH cp.userDetails ud ");
		jpql.append(" where c.tahdr.tahdrId=:tahdrId and c.bidOpeningType=:bidOpeningType");
		return jpql.toString();
	}
}
