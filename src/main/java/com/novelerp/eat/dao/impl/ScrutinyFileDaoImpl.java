package com.novelerp.eat.dao.impl;

import javax.annotation.PostConstruct;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.novelerp.core.dao.impl.AbstractJpaDAO;
import com.novelerp.eat.dao.ScrutinyFileDao;
import com.novelerp.eat.dto.ScrutinyFileDto;
import com.novelerp.eat.entity.ScrutinyFile;

@Repository
public class ScrutinyFileDaoImpl extends AbstractJpaDAO<ScrutinyFile, ScrutinyFileDto> implements ScrutinyFileDao {
	
	@PostConstruct
	public void postConstruct() {
		setClazz(ScrutinyFile.class, ScrutinyFileDto.class);
	}
	
	public String getTechnicalScrutinyFileByItemBidIdAndBidderId(){
		StringBuilder jpql= new StringBuilder();
		jpql.append(" SELECT sf FROM ScrutinyFile sf ");
		jpql.append(" LEFT JOIN FETCH  sf.attachment a ");
		jpql.append(" WHERE sf.isActive ='Y' AND sf.itemBid.itemBidId=:itemBidId AND sf.bidder.bidderId=:bidderId And sf.scrutinyType=:scrutinyType And sf.scrutinyLevel=:scrutinyLevel ");
		jpql.append(" AND 'Y'= CASE  WHEN :scrutinyLevel='PRELIMINARY' THEN sf.itemScrutiny.isScrutinySubmitted ELSE sf.itemScrutiny.isFinalScrutinySubmitted END");
		return jpql.toString();
	}
	
	public String getTechnicalScrutinyFileByTahdrMaterialIdAndBidderId(){
		StringBuilder jpql= new StringBuilder();
		jpql.append(" SELECT sf FROM ScrutinyFile sf ");
		jpql.append(" LEFT JOIN FETCH  sf.attachment a ");
		jpql.append(" LEFT JOIN FETCH sf.itemScrutiny its ");
		jpql.append(" WHERE sf.isActive ='Y' AND sf.itemBid.tahdrMaterial.tahdrMaterialId=:tahdrMaterialId AND sf.bidder.bidderId=:bidderId And sf.scrutinyType=:scrutinyType And sf.scrutinyLevel=:scrutinyLevel ");
		jpql.append(" AND 'Y'= CASE  WHEN :scrutinyLevel='PRELIMINARY' THEN sf.itemScrutiny.isScrutinySubmitted ELSE sf.itemScrutiny.isFinalScrutinySubmitted END");
		return jpql.toString();
	}
	
	public String getTechnicalScrutinyFileByBidderId(){
		StringBuilder jpql= new StringBuilder();
		jpql.append(" SELECT sf FROM ScrutinyFile sf ");
		jpql.append(" LEFT JOIN FETCH  sf.attachment a ");
		jpql.append(" LEFT JOIN FETCH  sf.itemBid ib ");
		jpql.append(" LEFT JOIN FETCH  ib.tahdrMaterial tm ");
		jpql.append(" LEFT JOIN FETCH  tm.material m ");
		jpql.append(" WHERE sf.isActive ='Y' AND sf.bidder.bidderId=:bidderId And sf.scrutinyType=:scrutinyType And sf.scrutinyLevel=:scrutinyLevel ");
		jpql.append(" AND 'Y'= CASE  WHEN :scrutinyLevel='PRELIMINARY' THEN sf.itemScrutiny.isScrutinySubmitted ELSE sf.itemScrutiny.isFinalScrutinySubmitted END");
		return jpql.toString();
	}

	
	public String getCommercialScrutinyFileAndBidderId(){
		StringBuilder jpql= new StringBuilder();
		jpql.append(" SELECT sf FROM ScrutinyFile sf ");
		jpql.append(" LEFT JOIN FETCH sf.attachment a ");
		jpql.append(" LEFT JOIN FETCH sf.itemScrutiny its ");
		jpql.append(" WHERE sf.isActive ='Y' AND sf.bidder.bidderId=:bidderId And sf.scrutinyType=:scrutinyType And sf.scrutinyLevel=:scrutinyLevel ");
		jpql.append(" AND 'Y' = CASE  WHEN :scrutinyLevel='PRELIMINARY' THEN sf.itemScrutiny.isScrutinySubmitted ELSE sf.itemScrutiny.isFinalScrutinySubmitted END");
		return jpql.toString();
	}
	
	
	
	@Override
	public int unhookPreviousScrutinyFile(Long itemScrtunityId,String scrutinyLevel,String scrutinyType){
		   StringBuilder jpql= new StringBuilder();
					     jpql.append(" Update ScrutinyFile sf SET isActive='N' ");
					     jpql.append(" WHERE sf.itemScrutiny.itemScrutinyId=:itemScrutinyId AND sf.scrutinyLevel=:scrutinyLevel");
					     jpql.append(" AND sf.scrutinyType=:scrutinyType");
			Query query=getEntityManager().createQuery(jpql.toString());
			query.setParameter("itemScrutinyId", itemScrtunityId);
			query.setParameter("scrutinyLevel", scrutinyLevel);
			query.setParameter("scrutinyType", scrutinyType);
			int count= query.executeUpdate();
			return count;
	}

}
