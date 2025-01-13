package com.novelerp.eat.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.novelerp.appbase.master.entity.Attachment;
import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.appcontext.dto.BPartnerDto;
import com.novelerp.appcontext.util.ContextConstant;
import com.novelerp.commons.util.CommonUtil;
import com.novelerp.core.dao.impl.AbstractJpaDAO;
import com.novelerp.eat.dao.TAHDRDetailDao;
import com.novelerp.eat.dto.TAHDRDetailDto;
import com.novelerp.eat.dto.TAHDRSearchDto;
import com.novelerp.eat.entity.TAHDRDetail;

@Repository
public class TAHDRDetailDaoImpl extends AbstractJpaDAO<TAHDRDetail, TAHDRDetailDto> implements TAHDRDetailDao {

	@PersistenceContext
	private EntityManager em;
	

	@PostConstruct
	void init(){
		setClazz(TAHDRDetail.class, TAHDRDetailDto.class);
	}
	
	public String getQueryForTAHDRDetailByTahdrDetailId(){		
		StringBuilder jpql = new StringBuilder();
		jpql.append(" Select td from TAHDRDetail td ");
		jpql.append(" LEFT JOIN FETCH td.tenderDoc att ");
		jpql.append(" LEFT JOIN FETCH td.officeNote ofno ");
		jpql.append(" WHERE td.tahdrDetailId= :tahdrDetailId ");
		return jpql.toString();
	}
	
	public String getQueryForTAHDRDetailMaterialWithGTP(){		
		StringBuilder jpql = new StringBuilder();
		jpql.append(" Select Distinct td from TAHDRDetail td ");
		jpql.append(" LEFT JOIN FETCH td.tahdr t  ");
		jpql.append(" LEFT JOIN FETCH td.tahdrMaterial tm ");
		jpql.append(" LEFT JOIN FETCH tm.material m ");
		jpql.append(" LEFT JOIN FETCH m.uom uom ");
		jpql.append(" LEFT JOIN FETCH m.hsnCode hsn ");
		jpql.append(" LEFT JOIN FETCH tm.sectionDocumentSet sd ");
		jpql.append(" LEFT JOIN FETCH tm.materialGtpList tmg ");
		jpql.append(" LEFT JOIN FETCH tmg.gtp g ");
		jpql.append(" LEFT JOIN FETCH g.gtpParameterType gt ");
		jpql.append(" WHERE td.tahdrDetailId= :tahdrDetailId ");
		return jpql.toString();
	}
	
	public String getQueryForTAHDRDetailMaterialWithGTPManufacturer(){		
		StringBuilder jpql = new StringBuilder();
		jpql.append(" Select Distinct td from TAHDRDetail td ");
		jpql.append(" LEFT JOIN FETCH td.tahdr t  ");
		jpql.append(" LEFT JOIN FETCH td.tahdrMaterial tm ");
		jpql.append(" LEFT JOIN FETCH tm.material m ");
		jpql.append(" LEFT JOIN FETCH m.uom uom ");
		jpql.append(" LEFT JOIN FETCH m.hsnCode hsn ");
		jpql.append(" LEFT JOIN FETCH tm.sectionDocumentSet sd ");
		jpql.append(" LEFT JOIN FETCH tm.materialGtpList tmg ");
		jpql.append(" LEFT JOIN FETCH tmg.gtp g ");
		jpql.append(" LEFT JOIN FETCH g.gtpParameterType gt ");
		jpql.append(" INNER JOIN PartnerOrgProduct pop with pop.material.materialId=m.materialId ");
		jpql.append(" WHERE td.tahdrDetailId= :tahdrDetailId and pop.partnerOrg.partnerOrgId=:factoryId ");
		jpql.append("and pop.partner.bPartnerId=:partnerId and (pop.isCEApproved='"+AppBaseConstant.EXEMPTED_APPROVED+"' OR pop.isCEApproved='Y')");
		return jpql.toString();
	}
	
	public String getQueryForTAHDRDetailMaterialWithGTPManufacturerEXEM(){		
		StringBuilder jpql = new StringBuilder();
		jpql.append(" Select Distinct td from TAHDRDetail td ");
		jpql.append(" LEFT JOIN FETCH td.tahdr t  ");
		jpql.append(" LEFT JOIN FETCH td.tahdrMaterial tm ");
		jpql.append(" LEFT JOIN FETCH tm.material m ");
		jpql.append(" LEFT JOIN FETCH m.uom uom ");
		jpql.append(" LEFT JOIN FETCH m.hsnCode hsn ");
		jpql.append(" LEFT JOIN FETCH tm.sectionDocumentSet sd ");
		jpql.append(" LEFT JOIN FETCH tm.materialGtpList tmg ");
		jpql.append(" LEFT JOIN FETCH tmg.gtp g ");
		jpql.append(" LEFT JOIN FETCH g.gtpParameterType gt ");
		jpql.append(" INNER JOIN PartnerOrgProduct pop with pop.material.materialId=m.materialId ");
		jpql.append(" WHERE td.tahdrDetailId= :tahdrDetailId and pop.partnerOrg.partnerOrgId=:factoryId ");
		jpql.append("and pop.partner.bPartnerId=:partnerId and (pop.isCEApproved='"+AppBaseConstant.EXEMPTED_APPROVED+"' or pop.partner.companyType='"+AppBaseConstant.COMPANY_TYPE_GOVT+"')");
		return jpql.toString();
	}
	
	public String getQueryForTAHDRDetailMaterialWithGTPTrader(){		
		StringBuilder jpql = new StringBuilder();
		jpql.append(" Select Distinct td from TAHDRDetail td ");
		jpql.append(" LEFT JOIN FETCH td.tahdr t  ");
		jpql.append(" LEFT JOIN FETCH td.tahdrMaterial tm ");
		jpql.append(" LEFT JOIN FETCH tm.material m ");
		jpql.append(" LEFT JOIN FETCH m.uom uom ");
		jpql.append(" LEFT JOIN FETCH m.hsnCode hsn ");
		jpql.append(" LEFT JOIN FETCH tm.sectionDocumentSet sd ");
		jpql.append(" LEFT JOIN FETCH tm.materialGtpList tmg ");
		jpql.append(" LEFT JOIN FETCH tmg.gtp g ");
		jpql.append(" LEFT JOIN FETCH g.gtpParameterType gt ");
		/*jpql.append(" INNER JOIN PartnerTradingItem pti with pti.material.materialId=m.materialId ");*/
		jpql.append(" WHERE td.tahdrDetailId= :tahdrDetailId ");
		/*jpql.append("and pti.partner.bPartnerId=:partnerId ");*/
		return jpql.toString();
	}

	public String getQueryForTAHDRDetailMaterialWithGTPManufacturerForPurchase(){		
		StringBuilder jpql = new StringBuilder();
		jpql.append(" Select Distinct td from TAHDRDetail td ");
		jpql.append(" LEFT JOIN FETCH td.tahdr t  ");
		jpql.append(" LEFT JOIN FETCH td.tahdrMaterial tm ");
		jpql.append(" LEFT JOIN FETCH tm.material m ");
		jpql.append(" LEFT JOIN FETCH m.uom uom ");
		jpql.append(" LEFT JOIN FETCH m.hsnCode hsn ");
		jpql.append(" LEFT JOIN FETCH tm.sectionDocumentSet sd ");
		jpql.append(" LEFT JOIN FETCH tm.materialGtpList tmg ");
		jpql.append(" LEFT JOIN FETCH tmg.gtp g ");
		jpql.append(" LEFT JOIN FETCH g.gtpParameterType gt ");
		jpql.append(" INNER JOIN PartnerOrgProduct pop with pop.material.materialId=m.materialId ");
		jpql.append(" WHERE td.tahdrDetailId= :tahdrDetailId and pop.partnerOrg.partnerOrgId=:factoryId ");
		jpql.append("and pop.partner.bPartnerId=:partnerId and (pop.isCEApproved=:apValue or pop.partner.companyType='"+AppBaseConstant.COMPANY_TYPE_GOVT+"')");
		return jpql.toString();
	}
	/*public String getQueryForTAHDRDetailMaterialWithGTP(){		
		StringBuilder jpql = new StringBuilder();
		jpql.append(" Select b from Bidder b");
		jpql.append(" INNER JOIN FETCH b.tahdr t ");
		jpql.append(" INNER JOIN FETCH t.tahdrDetail td ");
		jpql.append(" INNER JOIN FETCH b.factory f ");
		jpql.append(" INNER JOIN FETCH f.partnerOrgProduct pop ");
		jpql.append(" INNER JOIN FETCH td.tahdrMaterial tm ");
		jpql.append(" INNER JOIN FETCH tm.material m ");
		jpql.append(" INNER JOIN FETCH m.uom uom ");
		jpql.append(" INNER JOIN FETCH tm.sectionDocumentSet sd ");
		jpql.append(" INNER JOIN FETCH tm.materialGtpList tmg ");
		jpql.append(" INNER JOIN FETCH tmg.gtp g ");
		jpql.append(" INNER JOIN FETCH g.gtpParameterType gt ");
		jpql.append(" INNER JOIN FETCH b.partner bp ");
		jpql.append(" WHERE td.tahdrDetailId= :tahdrDetailId ");
		jpql.append(" tm.material.materialId= pop.material.materialId ");
		return jpql.toString();
	}*/
	
	public String getQueryForTAHDRDetail(){
		StringBuilder jpql = new StringBuilder();
		jpql.append(" Select td from TAHDRDetail td ")
		.append(" INNER JOIN FETCH td.tahdr t ")
		.append(" LEFT JOIN FETCH td.officeNote office ")
		.append(" LEFT JOIN FETCH td.designation designation ")
		.append(" WHERE td.tahdr.tahdrId= :tahdrId ")
		.append(" ORDER BY td.created DESC ");
		return jpql.toString();
	}
	public String getQueryForActiveTAHDRDetail(){
		StringBuilder jpql = new StringBuilder();
		jpql.append(" Select td from TAHDRDetail td ")
		.append(" INNER JOIN FETCH td.tahdr t ")
		.append(" WHERE td.tahdr.tahdrId= :tahdrId ")
		.append(" AND td.isActive='Y' ");
		return jpql.toString();
	}
	
	private StringBuilder disableOldTahdrDetail(){		
		StringBuilder jpql = new StringBuilder();
		jpql.append(" update TAHDRDetail td "
				+ " set td.isActive='N' ")
		.append(" WHERE td.tahdr.tahdrId= :tahdrId");
		
		return jpql;
	}
	
	public String QueryForTAHDRDetailById(){		
		StringBuilder jpql = new StringBuilder();
		jpql.append(" Select td from TAHDRDetail td "
				+ " LEFT JOIN FETCH td.tahdr t "
				+ " LEFT JOIN FETCH td.partner "
				+ " LEFT JOIN FETCH td.designation designation "
				+ " LEFT JOIN FETCH td.tenderDoc doc "
				+ " LEFT JOIN FETCH td.officeNote office ")
		.append(" WHERE td.tahdrDetailId= :tahdrDetailId and td.isActive='Y'");
		return jpql.toString();
	}
	
	public String QueryForTAHDRDetailByTahdrId(){		
		StringBuilder jpql = new StringBuilder();
		jpql.append(" Select td from TAHDRDetail td "
				+ " LEFT JOIN FETCH td.tahdr t "
				+ " LEFT JOIN FETCH td.partner ")
		.append(" WHERE td.tahdr.tahdrId= :tahdrId and td.isActive='Y'");
		return jpql.toString();
	}
	
	@Override
	@Transactional
	public Long getVersionNumber(Long tahdrId){		
		StringBuilder jpql = new StringBuilder();
		jpql.append(" Select MAX(td.version) as version from TAHDRDetail td "
				+ " WHERE td.tahdr.tahdrId= :tahdrId");
		TypedQuery<Long> q = getEntityManager().createQuery(jpql.toString(), Long.class);
		q.setParameter("tahdrId", tahdrId);
		Long version=q.getSingleResult();
		return version;
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public int createVersion(Long tahdrId){
			String jpql=disableOldTahdrDetail().toString();
			Query query = getEntityManager().createQuery(jpql);
			query.setParameter("tahdrId", tahdrId);
			int count= query.executeUpdate();
			return count;
	}

	@Override
	@Transactional
	public List<TAHDRDetail> getTAHDRList(TAHDRSearchDto tahdrSearchDto) {
		
		
		StringBuilder jpql = new StringBuilder("SELECT tahd from TAHDRDetail tahd INNER JOIN FETCH  tahd.tahdr tahdr  ");
		jpql.append(" LEFT JOIN FETCH  tahdr.mom mom ");
		
		if(tahdrSearchDto.getTahdrMaterial()!=null && tahdrSearchDto.getTahdrMaterial().getMaterialTypeCode()!=null && !CommonUtil.isStringEmpty(tahdrSearchDto.getTahdrMaterial().getMaterialTypeCode())){
			jpql.append(" LEFT JOIN  FETCH TAHDRMaterial m ON tahd.tahdrDetailId=m.tahdrDetail.tahdrDetailId ");
		  }
		
		String where=" where tahd.isActive='Y' AND tahdr.isPrivateAuction is null AND now()<=tahd.purchaseToDate";
		jpql.append(where);
		if(tahdrSearchDto.getRole()!=null){
			String locationType=tahdrSearchDto.getRole();
			if(locationType.equals("MSEBUSER"))
			jpql.append(" AND upper(tahdr.officeType) like upper(:officeType) ");
		 }
		if(tahdrSearchDto.getTahdr().getTahdrTypeCode().equalsIgnoreCase(ContextConstant.TAHDR_TYPE_REVERSE_AUCTION)){
			jpql.append(" AND tahdr.tahdrTypeCode = '"+AppBaseConstant.AUCTION_TYPE_CODE_REVERSE+"'");
			jpql.append(" AND tahdr.isAuction = 'Y' ");
		 }
		/*if(tahdrSearchDto.getTahdrMaterial()!=null && tahdrSearchDto.getTahdrMaterial().getTahdrMaterialId()!=null){*/
		/*if(!tahdrSearchDto.getTahdrMaterial().getMaterialTypeCode().isEmpty())*/
		if(tahdrSearchDto.getTahdrMaterial()!=null  && tahdrSearchDto.getTahdrMaterial().getMaterialTypeCode()!=null && !CommonUtil.isStringEmpty(tahdrSearchDto.getTahdrMaterial().getMaterialTypeCode()))
		{
			jpql.append(" AND upper(m.materialTypeCode) like upper(:mTypeCode) ");
		}
		
		if(tahdrSearchDto.getTahdr() !=null){
		/*if(!tahdrSearchDto.getTahdr().getTahdrCode().isEmpty() && tahdrSearchDto.getTahdr().getTahdrCode()!=null) {*/
			if(tahdrSearchDto.getTahdr().getTahdrCode()!=null && !CommonUtil.isStringEmpty(tahdrSearchDto.getTahdr().getTahdrCode())) {
			jpql.append(" AND upper(tahdr.tahdrCode) like upper(:code) ");
		}
		/*if(!tahdrSearchDto.getTahdr().getTahdrTypeCode().isEmpty() && tahdrSearchDto.getTahdr().getTahdrTypeCode()!=null  ) {*/
			if(tahdrSearchDto.getTahdr().getTahdrTypeCode()!=null && !tahdrSearchDto.getTahdr().getTahdrTypeCode().equalsIgnoreCase(ContextConstant.TAHDR_TYPE_REVERSE_AUCTION)){
			if(tahdrSearchDto.getTahdr().getTahdrTypeCode()!=null && !CommonUtil.isStringEmpty(tahdrSearchDto.getTahdr().getTahdrTypeCode()) ) {
			jpql.append(" AND upper(tahdr.tahdrTypeCode) like upper(:typeCode) ");
			}
		}
		/*if(!tahdrSearchDto.getTahdr().getBidTypeCode().isEmpty() && tahdrSearchDto.getTahdr().getBidTypeCode()!=null )*/
			if(tahdrSearchDto.getTahdr().getTahdrTypeCode()!=null && !CommonUtil.isStringEmpty(tahdrSearchDto.getTahdr().getBidTypeCode()) )
		{
			jpql.append(" AND upper(tahdr.bidTypeCode) like upper(:bidTypeCode) ");
		}
		}
		if(tahdrSearchDto.getTahdrDetail() !=null){
		if(tahdrSearchDto.getTahdrDetail().getTechnicalBidFromDate()!=null)
		{
			jpql.append(" AND Trunc(tahd.technicalBidFromDate)>=:techBidFromDate ");
		}
		if(tahdrSearchDto.getTahdrDetail().getTechnicalBidToDate()!=null)
		{
			jpql.append(" AND Trunc(tahd.technicalBidToDate)<=:techBidToDate ");
		}
		/*if(tahdrSearchDto.getTahdrDetail().getCommercialBidFromDate()!=null){
			jpql.append(" AND Trunc(tahd.commercialBidFromDate)<=:comBidFromDate ");
		}
		if(tahdrSearchDto.getTahdrDetail().getCommercialBidToDate()!=null){
			jpql.append(" AND Trunc(tahd.commercialBidToDate)>=:comBidToDate ");	
		}*/
		if(tahdrSearchDto.getTahdrDetail().getPriceBidOpenningDate()!=null){
			jpql.append(" AND Trunc(tahd.priceBidOpenningDate)=:priceBidOpenningDate ");
		}
		/*if(tahdrSearchDto.getTahdrDetail().getPriceBidToDate()!=null){
			jpql.append(" AND Trunc(tahd.priceBidToDate)>=:priceBidToDate ");
		}*/
		if(tahdrSearchDto.getTahdrDetail().getTechBidOpenningDate()!=null){
			jpql.append(" AND Trunc(tahd.techBidOpenningDate)=:techBidOpenDate ");
		 }
		}
		if(tahdrSearchDto.getRole()==null)
		{
			jpql.append(" AND tahdr.tahdrStatusCode='"+AppBaseConstant.DOCUMENT_STATUS_PUBLISHED+"'" );
		}
		jpql.append(" ORDER BY tahd.publishingDate DESC NULLS LAST ");
		
		Query query=getEntityManager().createQuery(jpql.toString(),TAHDRDetail.class);
		if(tahdrSearchDto.getRole()!=null)
		{
			String userRole=tahdrSearchDto.getRole();
			if(userRole.equals("MSEBUSER"))
				query.setParameter("officeType", tahdrSearchDto.getLocationType());
		}
		
		if(!CommonUtil.isStringEmpty(tahdrSearchDto.getTahdr().getTahdrCode()) && tahdrSearchDto.getTahdr().getTahdrCode()!=null ) {
			query.setParameter("code", "%"+tahdrSearchDto.getTahdr().getTahdrCode()+"%");
		}
		if(tahdrSearchDto.getTahdr().getTahdrTypeCode()!=null && !tahdrSearchDto.getTahdr().getTahdrTypeCode().equalsIgnoreCase(ContextConstant.TAHDR_TYPE_REVERSE_AUCTION)){
		if(!CommonUtil.isStringEmpty(tahdrSearchDto.getTahdr().getTahdrTypeCode()) && tahdrSearchDto.getTahdr().getTahdrTypeCode()!=null) {
			query.setParameter("typeCode","%"+ tahdrSearchDto.getTahdr().getTahdrTypeCode()+"%");
		}
		}
		if(!CommonUtil.isStringEmpty(tahdrSearchDto.getTahdr().getBidTypeCode()) && tahdrSearchDto.getTahdr().getBidTypeCode()!=null)
		{
			query.setParameter("bidTypeCode", "%"+tahdrSearchDto.getTahdr().getBidTypeCode()+"%");
		}
		if(tahdrSearchDto.getTahdrDetail()!=null){
		if(tahdrSearchDto.getTahdrDetail().getTechnicalBidFromDate()!=null)
		{
			query.setParameter("techBidFromDate", tahdrSearchDto.getTahdrDetail().getTechnicalBidFromDate(),TemporalType.DATE);
		}
		if(tahdrSearchDto.getTahdrDetail().getTechnicalBidToDate()!=null)
		{
			query.setParameter("techBidToDate", tahdrSearchDto.getTahdrDetail().getTechnicalBidToDate(),TemporalType.DATE);
		}
		/*if(tahdrSearchDto.getTahdrDetail().getCommercialBidFromDate()!=null)
		{
			query.setParameter("comBidFromDate", tahdrSearchDto.getTahdrDetail().getCommercialBidFromDate(),TemporalType.DATE);
		}
		if(tahdrSearchDto.getTahdrDetail().getCommercialBidToDate()!=null)
		{
			query.setParameter("comBidToDate", tahdrSearchDto.getTahdrDetail().getCommercialBidToDate(),TemporalType.DATE);
		}*/
		if(tahdrSearchDto.getTahdrDetail().getPriceBidOpenningDate()!=null)
		{
			query.setParameter("priceBidOpenningDate", tahdrSearchDto.getTahdrDetail().getPriceBidOpenningDate(),TemporalType.DATE);
		}
		/*if(tahdrSearchDto.getTahdrDetail().getPriceBidToDate()!=null)
		{
			query.setParameter("priceBidToDate", tahdrSearchDto.getTahdrDetail().getPriceBidToDate(),TemporalType.DATE);
		}*/
		if(tahdrSearchDto.getTahdrDetail().getTechBidOpenningDate()!=null)
		{
			query.setParameter("techBidOpenDate", tahdrSearchDto.getTahdrDetail().getTechBidOpenningDate(),TemporalType.DATE);
			
		}
		}
		if(tahdrSearchDto.getTahdrMaterial()!=null){
		if(!CommonUtil.isStringEmpty(tahdrSearchDto.getTahdrMaterial().getMaterialTypeCode()) && tahdrSearchDto.getTahdrMaterial().getMaterialTypeCode()!=null)
		{
			query.setParameter("mTypeCode", "%"+tahdrSearchDto.getTahdrMaterial().getMaterialTypeCode()+"%");
		}
		}
		@SuppressWarnings("unchecked")
		List<TAHDRDetail> tahdrList=query.getResultList();
		return tahdrList;
	}
	/*@Override
	@Transactional
	public List<TAHDRDetail> getAllTAHDRList(TAHDRSearchDto tahdrSearchDto) {
		
		StringBuilder jpql = new StringBuilder("SELECT tahd from TAHDRDetail tahd INNER JOIN FETCH  tahd.tahdr tahdr  ");
		if(tahdrSearchDto.getTahdrMaterial().getTahdrMaterialId()!=null )
		{
			jpql.append(" LEFT JOIN  FETCH TAHDRMaterial m ON tahd.tahdrDetailId=m.tahdrDetail.tahdrDetailId ");
		}
		String where=" where tahd.isActive='Y'";
		jpql.append(where);
		Query query=getEntityManager().createQuery(jpql.toString(),TAHDRDetail.class);
		@SuppressWarnings("unchecked")
		List<TAHDRDetail> tahdrList=query.getResultList();
		return tahdrList;
	}*/
	
	/*@Override
	public List<TAHDRDetail> getTAHDRTypeCode(String typeCode,BPartnerDto partnerDto) {
		StringBuilder sb=new StringBuilder(" select t from TAHDRDetail t ");
		sb.append(" LEFT JOIN FETCH t.partner bp ");
		sb.append(" LEFT JOIN FETCH t.tahdr ta ");
		sb.append(" LEFT JOIN FETCH t.tenderDoc td ");
		sb.append(" WHERE ta.tahdrTypeCode =:typeCode AND t.isActive='Y' ");
		sb.append(" AND ta.tahdrStatusCode= :statusCode  AND now() BETWEEN t.purchaseFromDate AND t.purchaseToDate ");
		if(partnerDto.getIsManufacturer()!=null && partnerDto.getIsManufacturer().equalsIgnoreCase("Y") && partnerDto.getIsTrader()!=null && partnerDto.getIsTrader().equalsIgnoreCase("Y") && typeCode.equalsIgnoreCase(AppBaseConstant.TENDER_TYPE_CODE_PROCUREMENT)){
			sb.append(" AND (ta.isManufacturer='Y' OR ta.isTrader='Y') ");
		}
		else if(partnerDto.getIsManufacturer()!=null && partnerDto.getIsManufacturer().equalsIgnoreCase("Y") && typeCode.equalsIgnoreCase(AppBaseConstant.TENDER_TYPE_CODE_PROCUREMENT))
		{
			sb.append(" AND ta.isManufacturer='Y' ");
		}
		else if(partnerDto.getIsTrader()!=null && partnerDto.getIsTrader().equalsIgnoreCase("Y") && typeCode.equalsIgnoreCase(AppBaseConstant.TENDER_TYPE_CODE_PROCUREMENT)){
			sb.append(" AND ta.isTrader='Y' ");
		}
		Query q=em.createQuery(sb.toString(),TAHDRDetail.class);
		System.err.println("typeCode: "+typeCode);
		q.setParameter("typeCode", typeCode);
		q.setParameter("statusCode", AppBaseConstant.DOCUMENT_STATUS_PUBLISHED);
		@SuppressWarnings("unchecked")
		List<TAHDRDetail> detailList= q.getResultList();
		return detailList;
		
	}*/
	
	/*@Override
	public List<TAHDRDetail> getTAHDRTypeCode(String typeCode) {
		StringBuilder sb=new StringBuilder(" select t from TAHDRDetail t ");
		sb.append(" INNER JOIN FETCH t.tahdr ta ");
		sb.append(" INNER JOIN FETCH t.tahdrMaterial tm ");
		sb.append(" INNER JOIN FETCH t.tahdrMaterial tm ");
		sb.append(" WHERE ta.tahdrTypeCode =:typeCode AND t.isActive='Y' ");
		sb.append(" AND ta.tahdrStatusCode= :statusCode  AND now() BETWEEN t.purchaseFromDate AND t.purchaseToDate ");
		Query q=em.createQuery(sb.toString(),TAHDRDetail.class);
		System.err.println("typeCode: "+typeCode);
		q.setParameter("typeCode", typeCode);
		q.setParameter("statusCode", AppBaseConstant.DOCUMENT_STATUS_PUBLISHED);
		@SuppressWarnings("unchecked")
		List<TAHDRDetail> detailList= q.getResultList();
		return detailList;
		
	}*/
	
	@Override
	public List<TAHDRDetail> getPublishedTadhrDetail(String typeCode) {
		StringBuilder sb=new StringBuilder(" select t from TAHDRDetail t ");
		sb.append(" LEFT JOIN FETCH t.tahdr ta ");
		sb.append(" where upper(ta.tahdrTypeCode)=upper(:typeCode) AND ta.tahdrStatusCode ='PU' AND t.isActive='Y' ");
		Query q=em.createQuery(sb.toString(),TAHDRDetail.class);
		q.setParameter("typeCode", typeCode);
		@SuppressWarnings("unchecked")
		List<TAHDRDetail> detailList= q.getResultList();
		return detailList;
		
	}
	@Override
	public List<TAHDRDetail> getTadhrDetailByStatus(String typeCode,String status) {
		StringBuilder sb=new StringBuilder(" select t from TAHDRDetail t ");
		sb.append(" LEFT JOIN FETCH t.tahdr ta ");
		sb.append(" LEFT JOIN FETCH ta.department d ");
		sb.append(" WHERE  upper(ta.tahdrTypeCode)=upper(:typeCode) AND ta.tahdrStatusCode=:status AND t.isActive='Y' ORDER BY t.updated DESC");
		Query q=em.createQuery(sb.toString(),TAHDRDetail.class);
		q.setParameter("typeCode", typeCode);
		q.setParameter("status", status);

		@SuppressWarnings("unchecked")
		List<TAHDRDetail> detailList= q.getResultList();
		return detailList;
		
	}
	
	public String getTadhrDetailQueryByStatus() {
		StringBuilder sb=new StringBuilder(" select t from TAHDRDetail t ");
		sb.append(" LEFT JOIN FETCH t.tahdr ta ");
		sb.append(" LEFT JOIN FETCH ta.department d ");
		sb.append(" WHERE  upper(ta.tahdrTypeCode)=upper(:typeCode) AND ta.tahdrStatusCode=:status AND t.isActive='Y' ");
		return sb.toString();
	}
	
	public String getTadhrDetailByTahdrIdAndAuditorId(){
		StringBuilder sb=new StringBuilder(" select t from TAHDRDetail t ");
		sb.append(" LEFT JOIN FETCH t.tahdr ta ");
		sb.append(" LEFT JOIN FETCH ta.department d ");
		sb.append(" where  ta.tahdrId=:tahdrId AND ta.auditorUser.userId=:userId AND t.isActive='Y' ");
		return sb.toString();
	}
	
	public String getTadhrDetailByStatusAndTahdrIdAndAuditorId(){
		StringBuilder sb=new StringBuilder(" select t from TAHDRDetail t ");
		sb.append(" LEFT JOIN FETCH t.tahdr ta ");
		sb.append(" LEFT JOIN FETCH ta.department d ");
		sb.append(" where  ta.tahdrId=:tahdrId AND ta.tahdrStatusCode=:status AND ta.auditorUser.userId=:userId AND t.isActive='Y' ");
		return sb.toString();
	}
	
	public String getMyTadhrDetailByTahdrId(){
		StringBuilder sb=new StringBuilder(" select t from TAHDRDetail t ");
		sb.append(" LEFT JOIN FETCH t.tahdr ta ");
		sb.append(" LEFT JOIN FETCH ta.department d ");
		sb.append(" where ta.tahdrId=:tahdrId  AND t.isActive='Y' ");
		return sb.toString();
	}
	
	public String getMyTadhrDetailByStatusAndTahdrId(){
		StringBuilder sb=new StringBuilder(" select t from TAHDRDetail t ");
		sb.append(" LEFT JOIN FETCH t.tahdr ta ");
		sb.append(" LEFT JOIN FETCH ta.department d ");
		sb.append(" where ta.tahdrId=:tahdrId AND ta.tahdrStatusCode=:status  AND t.isActive='Y' ");
		return sb.toString();
	}
	
	public String getMyTadhrDetail(){
		StringBuilder sb=new StringBuilder(" select t from TAHDRDetail t ");
		sb.append(" LEFT JOIN FETCH t.tahdr ta ");
		sb.append(" LEFT JOIN FETCH ta.department d ");
		sb.append(" where  upper(ta.tahdrTypeCode)=upper(:typeCode) AND ta.tahdrStatusCode=:status AND ta.auditorUser.userId=:userId AND t.isActive='Y' ");
		return sb.toString();
	}
	public String getTenderDetailByStatusAndAuditorId(){
		StringBuilder sb=new StringBuilder(" select t from TAHDRDetail t ");
		sb.append(" LEFT JOIN FETCH t.tahdr ta ");
		sb.append(" LEFT JOIN FETCH ta.department d ");
		sb.append(" where  upper(ta.tahdrTypeCode)=upper(:typeCode) AND ta.tahdrStatusCode=:status AND ta.isAuditing='Y' AND ta.auditorUser.userId=:userId AND t.isActive='Y' ");
		return sb.toString();
	}
	
	public String getAuctionDetailByStatusAndAuditorId(){
		StringBuilder sb=new StringBuilder(" select t from TAHDRDetail t ");
		sb.append(" LEFT JOIN FETCH t.tahdr ta ");
		sb.append(" LEFT JOIN FETCH ta.department d ");
		sb.append(" where  upper(ta.tahdrTypeCode)=upper(:typeCode) AND ta.isAuction='Y' AND ta.isAuditing='Y' AND ta.tahdrStatusCode=:status AND ta.auditorUser.userId=:userId AND t.isActive='Y' ");
		return sb.toString();
	}
	
	public String getAuctionDetailByStatus(){
		StringBuilder sb=new StringBuilder(" select t from TAHDRDetail t ");
		sb.append(" LEFT JOIN FETCH t.tahdr ta ");
		sb.append(" LEFT JOIN FETCH ta.department d ");
		sb.append(" where  upper(ta.tahdrTypeCode)=upper(:typeCode) AND ta.isAuction='Y' AND ta.tahdrStatusCode=:status AND t.isActive='Y' ");
		return sb.toString();
	}
	
	
	public String getQueryForTAHDRDetailVersion()
	{
		StringBuilder sb=new StringBuilder(" select t from TAHDRDetail t ");
		sb.append(" INNER JOIN FETCH t.tahdr tah ");
		sb.append(" where t.isActive='Y' ");
		return sb.toString();
	}
	
	public String getStdCstDocSet(){
		StringBuilder sb=new StringBuilder(" select t from TAHDRDetail t ");
		sb.append(" INNER JOIN FETCH t.standardCustomDoc std ");
		sb.append(" LEFT JOIN FETCH std.attachment att ");
		sb.append(" where t.tahdrDetailId=:tahdrDetailId AND t.isActive='Y' ");
		return sb.toString();
	}
	
	public String getQueryForPreBidTAHDRDtail(){
		StringBuilder jpql = new StringBuilder();
		jpql.append(" Select td from TAHDRDetail td ");
		jpql.append(" LEFT JOIN FETCH td.tahdr t ");
		jpql.append(" LEFT JOIN FETCH t.mom mom ");/*
		jpql.append(" LEFT JOIN FETCH td.tenderCommittee tc ");
		jpql.append(" LEFT JOIN FETCH tc.chairPerson cp ");*/
		jpql.append(" WHERE td.tahdr.tahdrStatusCode= :status AND td.technicalBidFromDate<now() AND td.isActive='Y' ");
		return jpql.toString();
	}
	
	@Override
	public int updateDeviationScheduleInfo(TAHDRDetailDto dto)
	{
		StringBuilder jpql= new StringBuilder();
		jpql.append(" UPDATE TAHDRDetail t SET t.deviationToDate=:deviationLastDate,t.deviationOpenningDate=:deviationBidOpeningDate,t.deviationFromDate=now() ");
		/*if(dto.getIsPBDSetLater().equals("Y")){
			jpql.append( " where t.tahdrDetailId=:tahdrDetailId AND :deviationLastDate>now() AND :deviationBidOpeningDate>now() ");
		}else{
			jpql.append( " where t.tahdrDetailId=:tahdrDetailId AND :deviationLastDate>now() AND :deviationBidOpeningDate>now() "
					+ " AND t.priceBidOpenningDate> :deviationBidOpeningDate AND :deviationLastDate<= :deviationBidOpeningDate");
		}*/
		jpql.append( " WHERE t.tahdrDetailId=:tahdrDetailId ");
		Query query=getEntityManager().createQuery(jpql.toString());
		query.setParameter("tahdrDetailId", dto.getTahdrDetailId());
		query.setParameter("deviationLastDate", dto.getDeviationToDate());
		query.setParameter("deviationBidOpeningDate", dto.getDeviationOpenningDate());
		int count= query.executeUpdate();
		return count;
	}
	
	@Override
	public int setNewAuctionFromDate(Long tahdrId,Date newLastDate) {
		StringBuilder jpql= new StringBuilder();
		jpql.append(" Update TAHDRDetail b SET b.auctionToDate=:newLastDate ");
		jpql.append( " where b.tahdr.tahdrId=:tahdrId ");
		
		Query query=getEntityManager().createQuery(jpql.toString());
		query.setParameter("tahdrId", tahdrId);
		query.setParameter("newLastDate", newLastDate);
		int count= query.executeUpdate();
		return count;
	}
	
	/*@Override
	public String getBiddersQuery(){
		
		StringBuilder jpql= new StringBuilder();
				
		return null;
	}*/
	
	
	/**
	 * Check if active TAHDRDetail record has tender document attachment
	 * @author Vivek Birdi
	 * @param tahdrId
	 * @return true, if active TAHDRDetail has an tender document attachment, else false.
	 */
	@Override
	@Transactional(propagation=Propagation.SUPPORTS)
	public Attachment getTenderDoc(Long tahdrId){		
		String jpql =  getTenderDocFromTahdrId();
		
		Map<String, Object> params   =  new HashMap<>();
		params.put("tahdrId", tahdrId);
		
		List<TAHDRDetail> tahdrDetails = getEntities(jpql, params);
		if(CommonUtil.isListEmpty(tahdrDetails)){
			return null;
		}
		
		TAHDRDetail tahdrDetail = tahdrDetails.get(0);
		return tahdrDetail.getTenderDoc();
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public int updateWithTenderDoc(Long attachmentId, Long tahdrId){
		
		StringBuilder updateQuery = new StringBuilder();
		updateQuery.append("UPDATE TAHDRDetail e SET e.tenderDoc.attachmentId = ")
		.append(attachmentId)
		.append(" WHERE e.isActive='Y' AND e.tahdr.tahdrId =")
		.append(tahdrId);
		
		return executeUpdate(updateQuery.toString());
	}
	
	public String getQueryForTAHDRDates(){		
		StringBuilder jpql = new StringBuilder();
		jpql.append(" Select Distinct td from TAHDRDetail td ");
		jpql.append(" LEFT JOIN FETCH td.tahdr t  ");
		jpql.append(" WHERE td.tahdrDetailId= :tahdrDetailId ");
		return jpql.toString();
	}
	
	public String getTenderDocFromTahdrId(){
		StringBuilder jpql = new StringBuilder();
		jpql.append("SELECT e FROM TAHDRDetail e ")
		.append(" INNER JOIN FETCH e.tenderDoc td ")
		.append(" INNER JOIN FETCH e.tahdr t ")
		.append(" INNER JOIN FETCH e.partner bp ")
		.append(" INNER JOIN FETCH e.designation des ")
		.append(" WHERE e.isActive = 'Y' AND t.tahdrId = :tahdrId " );
		return jpql.toString();
	}
	
	public String getTenderDocDetails(){
		StringBuilder jpql = new StringBuilder();
		jpql.append(" Select  td from TAHDRDetail td ");
		jpql.append(" LEFT JOIN FETCH td.tenderDoc doc ");
		jpql.append(" LEFT JOIN FETCH td.tahdr t  ");
		jpql.append(" WHERE td.tahdrDetailId= :tahdrDetailId and td.isActive='Y' ");
		return jpql.toString();
	}
	
	@Override
	public String getTAHDRPurchaseCount(String typeCode,BPartnerDto partnerDto,String searchColumn,String searchValue) {
		StringBuilder jpql =  new StringBuilder(" c.tahdr.tahdrTypeCode=:typeCode AND c.isActive='Y' ");
		jpql.append(" AND c.tahdr.tahdrStatusCode= :statusCode  AND NOW() BETWEEN c.purchaseFromDate AND c.purchaseToDate ");
		
		if(partnerDto.getIsManufacturer()!=null && partnerDto.getIsManufacturer().equalsIgnoreCase("Y") && partnerDto.getIsTrader()!=null && partnerDto.getIsTrader().equalsIgnoreCase("Y") && typeCode.equalsIgnoreCase(AppBaseConstant.TENDER_TYPE_CODE_PROCUREMENT)){
			jpql.append(" AND (c.tahdr.isManufacturer='Y' OR c.tahdr.isTrader='Y') ");
		}
		else if(partnerDto.getIsManufacturer()!=null && partnerDto.getIsManufacturer().equalsIgnoreCase("Y") && typeCode.equalsIgnoreCase(AppBaseConstant.TENDER_TYPE_CODE_PROCUREMENT))
		{
			jpql.append(" AND c.tahdr.isManufacturer='Y' ");
		}
		else if(partnerDto.getIsTrader()!=null && partnerDto.getIsTrader().equalsIgnoreCase("Y") && typeCode.equalsIgnoreCase(AppBaseConstant.TENDER_TYPE_CODE_PROCUREMENT)){
			jpql.append(" AND c.tahdr.isTrader='Y' ");
		}
		return jpql.toString();
		
	}
	
	@Override
	public String getTAHDRTypeCode(String typeCode,BPartnerDto partnerDto,String searchColumn, String searchValue) {
		StringBuilder sb=new StringBuilder(" select t from TAHDRDetail t ");
		sb.append(" LEFT JOIN FETCH t.partner bp ");
		sb.append(" LEFT JOIN FETCH t.tahdr ta ");
		sb.append(" LEFT JOIN FETCH t.tenderDoc td ");
		/*sb.append(" WHERE ta.tahdrTypeCode in ('RA','FA') AND t.isActive='Y' ");*/
		sb.append(" WHERE ta.tahdrTypeCode =:typeCode AND t.isActive='Y' ");
		sb.append(" AND ta.tahdrStatusCode= :statusCode  AND NOW() BETWEEN t.purchaseFromDate AND t.purchaseToDate AND (ta.isPrivateAuction is null ");
		/*sb.append(" OR (ta.isPrivateAuction ='Y'  and ap.bPartner.bPartnerId =:bPartnerId)) ");*/
		sb.append(" OR (ta.isPrivateAuction ='Y'  and  ta.tahdrId IN (SELECT tpm.tahdr.tahdrId FROM MAuctionParticipantMap tpm WHERE tpm.bPartner.bPartnerId =:bPartnerId ))) ");
		if(partnerDto.getIsManufacturer()!=null && partnerDto.getIsManufacturer().equalsIgnoreCase("Y") && partnerDto.getIsTrader()!=null && partnerDto.getIsTrader().equalsIgnoreCase("Y") && typeCode.equalsIgnoreCase(AppBaseConstant.TENDER_TYPE_CODE_PROCUREMENT)){
			sb.append(" AND (ta.isManufacturer='Y' OR ta.isTrader='Y') ");
		}
		else if(partnerDto.getIsManufacturer()!=null && partnerDto.getIsManufacturer().equalsIgnoreCase("Y") && typeCode.equalsIgnoreCase(AppBaseConstant.TENDER_TYPE_CODE_PROCUREMENT))
		{
			sb.append(" AND ta.isManufacturer='Y' ");
		}
		else if(partnerDto.getIsTrader()!=null && partnerDto.getIsTrader().equalsIgnoreCase("Y") && typeCode.equalsIgnoreCase(AppBaseConstant.TENDER_TYPE_CODE_PROCUREMENT)){
			sb.append(" AND ta.isTrader='Y' ");
		}
		if(!"none".equalsIgnoreCase(searchValue)){
			sb.append(" AND UPPER(t."+searchColumn+") LIKE :searchValue");
		}
		sb.append(" ORDER BY t.created DESC ");
		return sb.toString();
		
	}
	
	 @Override
	 @Transactional(propagation=Propagation.SUPPORTS)
	 public int endQuicKAuction(Long tahdrId,Date extendedDate){
	 	StringBuilder jpql= new StringBuilder();
	 	jpql.append(" Update TAHDRDetail  SET auctionToDate= :extendedDate");
	 	jpql.append(" WHERE tahdr.tahdrId=:tahdrId AND isActive='Y' ");
	 	Query query=getEntityManager().createQuery(jpql.toString()); 
	 	query.setParameter("tahdrId", tahdrId);
	 	query.setParameter("extendedDate", extendedDate);
	 	int count= query.executeUpdate();
	 	return count;
	 }

	
	@Override
	public String getTahdrDetailFromTahdrDetailId(){
		StringBuilder sb=new StringBuilder(" select t from TAHDRDetail t ");
		sb.append(" LEFT JOIN FETCH t.tahdr ta ");
		sb.append(" where t.tahdrDetailId=:tahdrDetailId  AND t.isActive='Y' ");
		return sb.toString();
	}
}
