/**
 * @author Ankush
 */

package com.novelerp.eat.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.bouncycastle.crypto.RuntimeCryptoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.novelerp.appbase.master.dto.AttachmentDto;
import com.novelerp.appbase.master.dto.MailTemplateDto;
import com.novelerp.appbase.master.entity.Attachment;
import com.novelerp.appbase.master.service.AttachmentService;
import com.novelerp.appbase.master.service.MailTemplateService;
import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.appcontext.dto.BPartnerDto;
import com.novelerp.appcontext.dto.UserDto;
import com.novelerp.appcontext.service.UserService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.appcontext.util.ContextConstant;
import com.novelerp.core.dto.Errors;
import com.novelerp.core.service.MailService;
import com.novelerp.core.service.impl.AbstractServiceImpl;
import com.novelerp.eat.dao.TAHDRDetailDao;
import com.novelerp.eat.dto.BidderDto;
import com.novelerp.eat.dto.TAHDRDetailDto;
import com.novelerp.eat.dto.TAHDRDto;
import com.novelerp.eat.dto.TAHDRSearchDto;
import com.novelerp.eat.entity.TAHDRDetail;
import com.novelerp.eat.service.SectionDocumentService;
import com.novelerp.eat.service.StandardCustomDocService;
import com.novelerp.eat.service.TAHDRDetailService;
import com.novelerp.eat.service.TAHDRMaterialGTPService;
import com.novelerp.eat.service.TAHDRMaterialService;
import com.novelerp.eat.service.TAHDRService;
import com.novelerp.eat.validator.TAHDRDatesValidator;

@Service
public class TAHDRDetailServiceImpl extends AbstractContextServiceImpl<TAHDRDetail, TAHDRDetailDto> implements TAHDRDetailService {

	@Autowired
	private TAHDRDetailDao tahdrDetailDao;

	@Autowired
	private TAHDRMaterialService tahdrMaterialService;
	
	@Autowired
	private SectionDocumentService sectionDocumentService;
	
	@Autowired
	private StandardCustomDocService stdCustDocService;
	
	@Autowired
	private AttachmentService attachmentService;
	
	@Autowired
	private TAHDRDatesValidator tahdrDatesValidation;
	
	@Autowired
	private TAHDRService tahdrService;
	
	@Autowired
	private TAHDRMaterialGTPService tahdrMaterialGtpService;
	
	@Autowired
	private MailTemplateService mailTemplateService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private MailService mailService;
	
	@PostConstruct
	private void init() {
		super.init(TAHDRDetailServiceImpl.class, tahdrDetailDao, TAHDRDetail.class, TAHDRDetailDto.class);
		setByPassProxy(true);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public TAHDRDetailDto saveTAHDRDetail(TAHDRDetailDto newDto) {
		TAHDRDetailDto tahdrDetail= new TAHDRDetailDto();
		try{
			newDto=copyOldDetails(newDto);
			newDto=validatePreparationScope(newDto);
			newDto.setVersion(1l);
			if(newDto.getTahdr()!=null || newDto.getTahdr().getTahdrId()>0){
				tahdrDetail=super.save(newDto);
			}
		}catch(Exception ex){
			log.error(ex.getMessage());
			ex.printStackTrace();
			throw new RuntimeException(ex.getMessage());
		}
		return tahdrDetail;
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean beforeSave(TAHDRDetailDto dto) {
		
		return true;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public TAHDRDetailDto createVersion(TAHDRDetailDto oldDto){
		TAHDRDetailDto newDto=null;
		try {
			newDto = (TAHDRDetailDto) oldDto.clone();
		} catch (CloneNotSupportedException e) {
			log.error("Error creating version");
			throw new RuntimeException("Error creating version");
		}
		
		
		Long version=oldDto.getVersion();
		if(oldDto.getDesignation()!=null){
			newDto.setDesignation(oldDto.getDesignation());
		}
		newDto.setVersion(version+1l);
		newDto.setTahdrDetailId(null);
		newDto.setTenderDoc(null);
		tahdrDetailDao.createVersion(newDto.getTahdr().getTahdrId());
		newDto=super.save(newDto);
		
		if(newDto.getTahdrDetailId()!=null){
			tahdrService.setTenderStatus(AppBaseConstant.DOCUMENT_STATUS_DRAFTED, newDto.getTahdr().getTahdrId());
			newDto.getTahdr().setTahdrStatusCode(AppBaseConstant.DOCUMENT_STATUS_DRAFTED);
			if(copyOldVersionDataToNewVersion(newDto.getTahdrDetailId(),oldDto.getTahdrDetailId())){
				return newDto;
			}else{
				throw new RuntimeException("Create Version Failed. Please Try After Sometime");
			}
			
		}else{
			throw new RuntimeException("Create Version Failed. Please Try After Sometime");
		}
		
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public TAHDRDetailDto saveTAHDRDates(TAHDRDetailDto newDto,TAHDRDetailDto oldDto) {
		
			if(oldDto.getTenderDoc()!=null && oldDto.getTahdr().getTahdrStatusCode().equalsIgnoreCase(AppBaseConstant.DOCUMENT_STATUS_DRAFTED)){
				oldDto.setTenderDoc(null);
			}
			oldDto=super.updateDto(oldDto);
		
		return oldDto;
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public TAHDRDetailDto saveTAHDRDates(TAHDRDetailDto oldDto) {
		
			if(oldDto.getTenderDoc()!=null && oldDto.getTahdr().getTahdrStatusCode().equalsIgnoreCase(AppBaseConstant.DOCUMENT_STATUS_DRAFTED)){
				oldDto.setTenderDoc(null);
			}
			oldDto=super.updateDto(oldDto);
		
		return oldDto;
	}


	
	public TAHDRDetailDto copyNewDatesToOld(TAHDRDetailDto newDto,TAHDRDetailDto oldDto){
		oldDto.setCommercialBidFromDate(newDto.getCommercialBidFromDate());
		oldDto.setCommercialBidToDate(newDto.getCommercialBidToDate());
		oldDto.setCommercialBidOpenningDate(newDto.getCommercialBidOpenningDate());
		oldDto.setIsBiennialContractRate(newDto.getIsBiennialContractRate());
		oldDto.setPurchaseFromDate(newDto.getPurchaseFromDate());
		oldDto.setPurchaseToDate(newDto.getPurchaseToDate());
		oldDto.setPriceBidFromDate(newDto.getPriceBidFromDate());
		oldDto.setPriceBidToDate(newDto.getPriceBidToDate());
		oldDto.setTechBidOpenningDate(newDto.getTechBidOpenningDate());
		oldDto.setTechnicalBidFromDate(newDto.getTechnicalBidFromDate());
		oldDto.setTechnicalBidToDate(newDto.getTechnicalBidToDate());
		oldDto.setC1FromDate(newDto.getC1FromDate());
		oldDto.setC1OpenningDate(newDto.getC1OpenningDate());
		oldDto.setC1ToDate(newDto.getC1ToDate());
		oldDto.setDeviationFromDate(newDto.getDeviationFromDate());
		oldDto.setDeviationToDate(newDto.getDeviationToDate());
		oldDto.setDeviationOpenningDate(newDto.getDeviationOpenningDate());
		oldDto.setAuctionFromDate(newDto.getAuctionFromDate());
		oldDto.setAuctionToDate(newDto.getAuctionToDate());
		oldDto.setWinnerSelectionDate(newDto.getWinnerSelectionDate());
		oldDto.setIsDeviationSetLater(newDto.getIsDeviationSetLater());
		oldDto.setPriceBidOpenningDate(newDto.getPriceBidOpenningDate());
		oldDto.setPreBidDate(newDto.getPreBidDate());
		oldDto.setEditedDates(newDto.getEditedDates());
		oldDto.setDatesSubmitted(newDto.getDatesSubmitted());
		/*oldDto.setPartner(newDto.getPartner());*/
		return oldDto;
	}
	
	private TAHDRDetailDto copyOldDatesToNew(TAHDRDetailDto newDto,TAHDRDetailDto oldDto){
		newDto.setCommercialBidFromDate(oldDto.getCommercialBidFromDate());
		newDto.setCommercialBidToDate(oldDto.getCommercialBidToDate());
		newDto.setCommercialBidOpenningDate(oldDto.getCommercialBidOpenningDate());
		newDto.setIsBiennialContractRate(oldDto.getIsBiennialContractRate());
		newDto.setPurchaseFromDate(oldDto.getPurchaseFromDate());
		newDto.setPurchaseToDate(oldDto.getPurchaseToDate());
		newDto.setPriceBidFromDate(oldDto.getPriceBidFromDate());
		newDto.setPriceBidToDate(oldDto.getPriceBidToDate());
		newDto.setTechBidOpenningDate(oldDto.getTechBidOpenningDate());
		newDto.setTechnicalBidFromDate(oldDto.getTechnicalBidFromDate());
		newDto.setTechnicalBidToDate(oldDto.getTechnicalBidToDate());
		newDto.setC1FromDate(oldDto.getC1FromDate());
		newDto.setC1OpenningDate(oldDto.getC1OpenningDate());
		newDto.setC1ToDate(oldDto.getC1ToDate());
		newDto.setAuctionFromDate(oldDto.getAuctionFromDate());
		newDto.setAuctionToDate(oldDto.getAuctionToDate());
		newDto.setWinnerSelectionDate(oldDto.getWinnerSelectionDate());
		newDto.setDeviationFromDate(oldDto.getDeviationFromDate());
		newDto.setDeviationToDate(oldDto.getDeviationToDate());
		newDto.setDeviationOpenningDate(oldDto.getDeviationOpenningDate());
		newDto.setIsDeviationSetLater(oldDto.getIsDeviationSetLater());
		newDto.setPriceBidOpenningDate(oldDto.getPriceBidOpenningDate());
		newDto.setPreBidDate(oldDto.getPreBidDate());
		newDto.setDesignation(oldDto.getDesignation());
		newDto.setPartner(oldDto.getPartner());
		return newDto;
	}
	
	@Override
	public List<TAHDRDetailDto> getTAHDRList(TAHDRSearchDto tahdrSearchDto) {
		List<TAHDRDetailDto> tahdrDetailList=new ArrayList<TAHDRDetailDto>();
		try {
			List<TAHDRDetail> tahdrList = tahdrDetailDao.getTAHDRList(tahdrSearchDto);
			tahdrDetailList=getDtoList(tahdrList);
		} catch (Exception e) {
			log.error(e.toString());
			e.printStackTrace();
		}
		return tahdrDetailList;
	}
	
	/*@Override
	public List<TAHDRDetailDto> getTenderList(String typeCode, BPartnerDto partnerDto) {
		
		List<TAHDRDetailDto> tenderList=new ArrayList<TAHDRDetailDto>();
		try{
			List<TAHDRDetail> tender=tahdrDetailDao.getTAHDRTypeCode(typeCode,partnerDto);	
			tenderList=getDtoList(tender);
		}
		catch(Exception e) {
			log.error(e.toString());
			e.printStackTrace();
		}
		return tenderList;
		
	}*/

	/* (non-Javadoc)
	 * @see com.novelerp.eat.service.TAHDRDetailService#updateDto(com.novelerp.eat.dto.TAHDRDetailDto, com.novelerp.eat.dto.TAHDRDetailDto)
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public TAHDRDetailDto updateDto(TAHDRDetailDto newDto, TAHDRDetailDto oldDto) {
		newDto=validatePreparationScope(newDto);
		oldDto=copyNewDetailsToOld(newDto,oldDto);
		if(oldDto.getTenderDoc()!=null && oldDto.getTahdr().getTahdrStatusCode().equalsIgnoreCase(AppBaseConstant.DOCUMENT_STATUS_DRAFTED)){
			oldDto.setTenderDoc(null);
		}
		return updateDto(oldDto);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public TAHDRDetailDto updateQuickAuction(TAHDRDetailDto newDto, TAHDRDetailDto oldDto) {
		oldDto=copyNewDetailsToOldForQA(newDto,oldDto);
		/*if(oldDto.getTenderDoc()!=null && oldDto.getTahdr().getTahdrStatusCode().equalsIgnoreCase(AppBaseConstant.DOCUMENT_STATUS_DRAFTED)){
			oldDto.setTenderDoc(null);
		}*/
		return updateDto(oldDto);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public TAHDRDetailDto updateQuickRfq(TAHDRDto tahdr,TAHDRDetailDto newDto, TAHDRDetailDto oldDto) {
		if(oldDto!=null){
			oldDto=copyDetailsOfQuickRfq(oldDto,newDto);
			newDto=oldDto;
		}
		newDto.setTahdr(tahdr);
		/*if(oldDto.getTenderDoc()!=null && oldDto.getTahdr().getTahdrStatusCode().equalsIgnoreCase(AppBaseConstant.DOCUMENT_STATUS_DRAFTED)){
			oldDto.setTenderDoc(null);
		}*/
		return updateDto(newDto);
	}
	
	public StringBuilder findDifferentDate(TAHDRDetailDto newDto,TAHDRDetailDto oldDto){
		StringBuilder html = new StringBuilder();
		if(newDto.getPurchaseFromDate()!=null ){
		if(oldDto.getPurchaseFromDate().before(newDto.getPurchaseFromDate()) || oldDto.getPurchaseFromDate().after(newDto.getPurchaseFromDate()))
		{
			html = html.append("<br/> Sale Start Date has been changed:" +newDto.getPurchaseFromDate());
		}
		}
		if(newDto.getPurchaseToDate()!=null ){
		if(oldDto.getPurchaseToDate().before(newDto.getPurchaseToDate()) || oldDto.getPurchaseToDate().after(newDto.getPurchaseToDate()))
		{
			html = html.append("<br/> Sale End Date has been changed:" +newDto.getPurchaseToDate());
		}
		}
		if(newDto.getTechnicalBidFromDate()!=null ){
		if(oldDto.getTechnicalBidFromDate().before(newDto.getTechnicalBidFromDate()) || oldDto.getTechnicalBidFromDate().after(newDto.getTechnicalBidFromDate()))
		{
			html = html.append("<br/> Bid Start Date has been changed:" +newDto.getTechnicalBidFromDate());
		}
		}
		if(newDto.getTechnicalBidToDate()!=null ){
		if(oldDto.getTechnicalBidToDate().before(newDto.getTechnicalBidToDate()) || oldDto.getTechnicalBidToDate().after(newDto.getTechnicalBidToDate()))
		{
			html = html.append("<br/> Bid End Date has been changed:" +newDto.getTechnicalBidToDate());
		}
		}
		if(newDto.getPreBidDate()!=null ){
		if(oldDto.getPreBidDate().before(newDto.getPreBidDate()) || oldDto.getPreBidDate().after(newDto.getPreBidDate()))
		{
			html = html.append("<br/> Pre Bid Meeting Date has been changed:" +newDto.getPreBidDate());
		}
		}
		if(newDto.getTechBidOpenningDate()!=null ){
		if(oldDto.getTechBidOpenningDate().before(newDto.getTechBidOpenningDate()) || oldDto.getTechBidOpenningDate().after(newDto.getTechBidOpenningDate()))
		{
			html = html.append("<br/> Bid Opening Date has been changed:" +newDto.getTechBidOpenningDate());
		}
		}
		if(newDto.getPriceBidOpenningDate()!=null ){
		if(!oldDto.getIsPBDSetLater().equalsIgnoreCase("Y")){
		if(oldDto.getPriceBidOpenningDate().before(newDto.getPriceBidOpenningDate()) || oldDto.getPriceBidOpenningDate().after(newDto.getPriceBidOpenningDate()))
		{
			html = html.append("<br/> Price Bid Opening Date has been changed:" +newDto.getPriceBidOpenningDate());
		}
		}
		}
		if(oldDto.getIsAnnexureC1().equalsIgnoreCase("Y")){
			if(!oldDto.getIsSetC1Later().equalsIgnoreCase("Y")){
				if(newDto.getC1OpenningDate()!=null ){
					if(oldDto.getC1OpenningDate().before(newDto.getC1OpenningDate()) || oldDto.getC1OpenningDate().after(newDto.getC1OpenningDate()))
					{
						html = html.append("<br/> Annexure C1 Opening Date has been changed:" +newDto.getC1OpenningDate());
					}
					}
					if(newDto.getC1ToDate()!=null ){
					if(oldDto.getC1ToDate().before(newDto.getC1ToDate()) || oldDto.getC1ToDate().after(newDto.getC1ToDate()))
					{
						html = html.append("<br/> Annexure C1 End Date has been changed:" +newDto.getC1ToDate());
					}
					}
			}
		}
		if(newDto.getAuctionFromDate()!=null ){
		if(!oldDto.getIsAuctionDateSetLater().equalsIgnoreCase("Y")){
		if(oldDto.getAuctionFromDate().before(newDto.getAuctionFromDate()) || oldDto.getAuctionFromDate().after(newDto.getAuctionFromDate()))
		{
			html = html.append("<br/> Auction Start Date has been changed:" +newDto.getAuctionFromDate());
		}
		}
		}
		if(newDto.getAuctionToDate()!=null ){
		if(!oldDto.getIsAuctionDateSetLater().equalsIgnoreCase("Y")){
		if(oldDto.getAuctionToDate().before(newDto.getAuctionToDate()) || oldDto.getAuctionToDate().after(newDto.getAuctionToDate()))
		{
		html = html.append("<br/> Auction End Date has been changed:" +newDto.getAuctionToDate());
		}
		}
		}
		if(newDto.getWinnerSelectionDate()!=null ){
		if(!oldDto.getIsWinnerSelectionDateSetLater().equalsIgnoreCase("Y")){
		if(oldDto.getWinnerSelectionDate().before(newDto.getWinnerSelectionDate()) || oldDto.getWinnerSelectionDate().after(newDto.getWinnerSelectionDate()))
		{
		html = html.append("<br/> Winner Selection Date has been changed:" +newDto.getWinnerSelectionDate());
		}
		}
		}
		if(newDto.getDeviationFromDate()!=null ){
		if(oldDto.getDeviationFromDate().before(newDto.getDeviationFromDate()) || oldDto.getDeviationFromDate().after(newDto.getDeviationFromDate()))
		{
			html = html.append("<br/> Deviation Start Date has been changed:" +newDto.getDeviationFromDate());
		}
		}
		if(newDto.getDeviationToDate()!=null ){
		if(oldDto.getDeviationToDate().before(newDto.getDeviationToDate()) || oldDto.getDeviationToDate().after(newDto.getDeviationToDate()))
		{
			html = html.append("<br/> Deviation End Date has been changed:" +newDto.getDeviationToDate());
		}
		}
		if(newDto.getDeviationOpenningDate()!=null ){
		if(oldDto.getDeviationOpenningDate().before(newDto.getDeviationOpenningDate()) || oldDto.getDeviationOpenningDate().after(newDto.getDeviationOpenningDate()))
		{
			html = html.append("<br/> Deviation Opening Date has been changed:" +newDto.getDeviationOpenningDate());
		}
		}
		if(newDto.getDeviationOpenningDate()!=null ){
		if(oldDto.getDeviationOpenningDate().before(newDto.getDeviationOpenningDate()) || oldDto.getDeviationOpenningDate().after(newDto.getDeviationOpenningDate()))
		{
			html = html.append("<br/> Deviation Opening Date has been changed:" +newDto.getDeviationOpenningDate());
		}
		}
		if(newDto.getCommercialBidFromDate()!=null ){
		if(oldDto.getCommercialBidFromDate().before(newDto.getCommercialBidFromDate()) || oldDto.getCommercialBidFromDate().after(newDto.getCommercialBidFromDate()))
		{
			html = html.append("<br/> Commercial Bid Start Date has been changed:" +newDto.getCommercialBidFromDate());
		}
		}
		if(newDto.getCommercialBidToDate()!=null ){
		if(oldDto.getCommercialBidToDate().before(newDto.getCommercialBidToDate()) || oldDto.getCommercialBidToDate().after(newDto.getCommercialBidToDate()))
		{
			html = html.append("<br/> Commercial Bid End Date has been changed:" +newDto.getCommercialBidToDate());
		}
		}
		if(newDto.getPriceBidFromDate()!=null ){
		if(oldDto.getPriceBidFromDate().before(newDto.getPriceBidFromDate()) || oldDto.getPriceBidFromDate().after(newDto.getPriceBidFromDate()))
		{
			html = html.append("<br/> Price Bid Start Date has been changed:" +newDto.getPriceBidFromDate());
		}
		}
		if(newDto.getPriceBidToDate()!=null ){
		if(oldDto.getPriceBidToDate().before(newDto.getPriceBidToDate()) || oldDto.getPriceBidToDate().after(newDto.getPriceBidToDate()))
		{
			html = html.append("<br/> Price Bid End Date has been changed:" +newDto.getPriceBidToDate());
		}
		}
		
		return html;
		
	}
	private TAHDRDetailDto copyNewDetailsToOld(TAHDRDetailDto newDto,TAHDRDetailDto oldDto){
		oldDto.setCommencementPeriod(newDto.getCommencementPeriod());
		oldDto.setContactEmailId(newDto.getContactEmailId());
		oldDto.setDeliveryDuration(newDto.getDeliveryDuration());
		oldDto.setDescription(newDto.getDescription());
		oldDto.setEmdFee(newDto.getEmdFee());
		oldDto.setEstimatedCost(newDto.getEstimatedCost());
		oldDto.setIsAnnexureC1(newDto.getIsAnnexureC1());
		oldDto.setIsBiennialContractRate(newDto.getIsBiennialContractRate());
		oldDto.setIsDeviation(newDto.getIsDeviation());
		oldDto.setIsDeviationSetLater(newDto.getIsDeviationSetLater());
		oldDto.setIsPBDSetLater(newDto.getIsPBDSetLater());
		oldDto.setIsSetC1Later(newDto.getIsSetC1Later());
		oldDto.setAutoExtentionTime(newDto.getAutoExtentionTime());
		oldDto.setIsTechnicalBid(newDto.getIsTechnicalBid()==null?"N":newDto.getIsTechnicalBid());
		oldDto.setIsGTP(newDto.getIsGTP()==null?"N":newDto.getIsGTP());
		oldDto.setIsTechnicalDocs(newDto.getIsTechnicalDocs()==null?"N":newDto.getIsTechnicalDocs());
		oldDto.setContactPersonNo(newDto.getContactPersonNo());
		oldDto.setContactPersonName(newDto.getContactPersonName());
		oldDto.setIsCommercialDocs(newDto.getIsCommercialDocs()==null?"N":newDto.getIsCommercialDocs());
		oldDto.setIsPriceDocs(newDto.getIsPriceDocs()==null?"N":newDto.getIsPriceDocs());
		
		oldDto.setIsReqDocs(newDto.getIsReqDocs()==null?"N":newDto.getIsReqDocs());
		oldDto.setIsTenderDocs(newDto.getIsTenderDocs()==null?"N":newDto.getIsTenderDocs());
		
		oldDto.setIsPreliminaryScrutiny(newDto.getIsPreliminaryScrutiny()==null?"N":newDto.getIsPreliminaryScrutiny());
		oldDto.setIsFinalScrutiny(newDto.getIsFinalScrutiny()==null?"N":newDto.getIsFinalScrutiny());
		
		
		oldDto.setIsWinnerSelectionDateSetLater(newDto.getIsWinnerSelectionDateSetLater());
		oldDto.setIsAuctionDateSetLater(newDto.getIsAuctionDateSetLater());
		oldDto.setMinQuantity(newDto.getMinQuantity());
		oldDto.setPreQualReq(newDto.getPreQualReq());
		oldDto.setPricingProcCode(newDto.getPricingProcCode());
		oldDto.setTahdrFees(newDto.getTahdrFees());
		oldDto.setTahdrStatusCode(newDto.getTahdrStatusCode());
		oldDto.setTahdrValidity(newDto.getTahdrValidity());
		oldDto.setPreBidAddr(newDto.getPreBidAddr());
		oldDto.setBidOpeningAddr(newDto.getBidOpeningAddr());
		oldDto.setMinBidDifference(newDto.getMinBidDifference());
		oldDto.setTahdr(newDto.getTahdr());
		oldDto.setOtherCommencementPeriod(newDto.getOtherCommencementPeriod());
		oldDto.setCommencementPeriodCode(newDto.getCommencementPeriodCode());
		oldDto.setDesignation(newDto.getDesignation());
		return oldDto;
	}
	
	private TAHDRDetailDto copyNewDetailsToOldForQA(TAHDRDetailDto newDto,TAHDRDetailDto oldDto){
		oldDto.setContactEmailId(newDto.getContactEmailId());
		oldDto.setContactPersonName(newDto.getContactPersonName());
		oldDto.setContactPersonNo(newDto.getContactPersonNo());
		oldDto.setAuctionFromDate(newDto.getAuctionFromDate());
		oldDto.setAuctionToDate(newDto.getAuctionToDate());
		
		/*oldDto.setIsTechnicalBid(newDto.getIsTechnicalBid());
		oldDto.setIsCommercialBid(newDto.getIsCommercialBid());
		oldDto.setIsReqDocs(newDto.getIsReqDocs());
		oldDto.setIsTenderDocs(newDto.getIsTenderDocs());
		oldDto.setIsGTP(newDto.getIsGTP());*/
		
		oldDto.setDescription(newDto.getDescription());
		oldDto.setEstimatedCost(newDto.getEstimatedCost());
		oldDto.setMinBidDifference(newDto.getMinBidDifference());
		oldDto.setTahdr(newDto.getTahdr());
		return oldDto;
	}
	
	
	private TAHDRDetailDto copyOldDetailsToNew(TAHDRDetailDto newDto,TAHDRDetailDto oldDto){
		newDto.setCommencementPeriod(oldDto.getCommencementPeriod());
		newDto.setCommencementPeriodCode(oldDto.getCommencementPeriodCode());
		newDto.setOtherCommencementPeriod(oldDto.getOtherCommencementPeriod());
		newDto.setContactEmailId(oldDto.getContactEmailId());
		newDto.setDeliveryDuration(oldDto.getDeliveryDuration());
		newDto.setDescription(oldDto.getDescription());
		newDto.setEmdFee(oldDto.getEmdFee());
		newDto.setEstimatedCost(oldDto.getEstimatedCost());
		newDto.setIsAnnexureC1(oldDto.getIsAnnexureC1());
		newDto.setIsBiennialContractRate(oldDto.getIsBiennialContractRate());
		newDto.setIsDeviation(oldDto.getIsDeviation());
		
		newDto.setIsTechnicalBid(oldDto.getIsTechnicalBid()==null?"N":oldDto.getIsTechnicalBid());
		newDto.setIsGTP(oldDto.getIsGTP()==null?"N":oldDto.getIsGTP());
		newDto.setIsTechnicalDocs(oldDto.getIsTechnicalDocs()==null?"N":oldDto.getIsTechnicalDocs());
		
		newDto.setIsCommercialDocs(oldDto.getIsCommercialDocs()==null?"N":oldDto.getIsCommercialDocs());
		newDto.setIsPriceDocs(oldDto.getIsPriceDocs()==null?"N":oldDto.getIsPriceDocs());
		
		newDto.setIsReqDocs(oldDto.getIsReqDocs()==null?"N":oldDto.getIsReqDocs());
		newDto.setIsTenderDocs(oldDto.getIsTenderDocs()==null?"N":oldDto.getIsTenderDocs());
		
		
		newDto.setIsDeviationSetLater(oldDto.getIsDeviationSetLater());
		newDto.setIsPBDSetLater(oldDto.getIsPBDSetLater());
		newDto.setIsSetC1Later(oldDto.getIsSetC1Later());
		newDto.setMinQuantity(oldDto.getMinQuantity());
		newDto.setPreQualReq(oldDto.getPreQualReq());
		newDto.setPricingProcCode(oldDto.getPricingProcCode());
		newDto.setTahdrFees(oldDto.getTahdrFees());
		newDto.setTahdrStatusCode(oldDto.getTahdrStatusCode());
		newDto.setTahdrValidity(oldDto.getTahdrValidity());
		newDto.setPreBidAddr(oldDto.getPreBidAddr());
		newDto.setBidOpeningAddr(oldDto.getBidOpeningAddr());
		newDto.setMinBidDifference(oldDto.getMinBidDifference());
		newDto.setTahdr(oldDto.getTahdr());
		newDto.setDesignation(oldDto.getDesignation());
		
		return newDto;
		
	}
	
	private TAHDRDetailDto copyOldDetails(TAHDRDetailDto oldDto){
		TAHDRDetailDto newDto=new TAHDRDetailDto();
		
		newDto.setAutoExtentionTime(oldDto.getAutoExtentionTime());
		newDto.setCommencementPeriod(oldDto.getCommencementPeriod());
		newDto.setOtherCommencementPeriod(oldDto.getOtherCommencementPeriod());
		newDto.setMinQuantity(oldDto.getMinQuantity());
		newDto.setContactPersonName(oldDto.getContactPersonName());
		newDto.setDeliveryDuration(oldDto.getDeliveryDuration());
		newDto.setDescription(oldDto.getDescription());
		newDto.setEmdFee(oldDto.getEmdFee());
		newDto.setEstimatedCost(oldDto.getEstimatedCost());
		newDto.setIsAnnexureC1(oldDto.getIsAnnexureC1());
		newDto.setIsBiennialContractRate(oldDto.getIsBiennialContractRate());
		newDto.setIsDeviation(oldDto.getIsDeviation());
		newDto.setIsActive(oldDto.getIsActive());
		newDto.setIsTechnicalBid(oldDto.getIsTechnicalBid()==null?"N":oldDto.getIsTechnicalBid());
		newDto.setIsGTP(oldDto.getIsGTP()==null?"N":oldDto.getIsGTP());
		newDto.setIsTechnicalDocs(oldDto.getIsTechnicalDocs()==null?"N":oldDto.getIsTechnicalDocs());
		
		newDto.setIsCommercialDocs(oldDto.getIsCommercialDocs()==null?"N":oldDto.getIsCommercialDocs());
		newDto.setIsPriceDocs(oldDto.getIsPriceDocs()==null?"N":oldDto.getIsPriceDocs());
		
		newDto.setIsReqDocs(oldDto.getIsReqDocs()==null?"N":oldDto.getIsReqDocs());
		newDto.setIsTenderDocs(oldDto.getIsTenderDocs()==null?"N":oldDto.getIsTenderDocs());
		
		
		newDto.setIsDeviationSetLater(oldDto.getIsDeviationSetLater());
		newDto.setIsPBDSetLater(oldDto.getIsPBDSetLater());
		newDto.setIsSetC1Later(oldDto.getIsSetC1Later());
		newDto.setMinQuantity(oldDto.getMinQuantity());
		newDto.setPreQualReq(oldDto.getPreQualReq());
		newDto.setPricingProcCode(oldDto.getPricingProcCode());
		newDto.setTahdrFees(oldDto.getTahdrFees());
		newDto.setTahdrStatusCode(oldDto.getTahdrStatusCode());
		newDto.setTahdrValidity(oldDto.getTahdrValidity());
		newDto.setPreBidAddr(oldDto.getPreBidAddr());
		newDto.setBidOpeningAddr(oldDto.getBidOpeningAddr());
		newDto.setMinBidDifference(oldDto.getMinBidDifference());
		newDto.setTahdr(oldDto.getTahdr());
		newDto.setDesignation(oldDto.getDesignation());
		
		return newDto;
		
	}
	
	private TAHDRDetailDto copyDetailsOfQuickRfq(TAHDRDetailDto oldDto,TAHDRDetailDto newDto){
	
		oldDto.setCommencementPeriod(newDto.getCommencementPeriod());
		oldDto.setCommencementPeriodCode(newDto.getCommencementPeriodCode());
		oldDto.setOtherCommencementPeriod(newDto.getOtherCommencementPeriod());
		oldDto.setDeliveryDuration(newDto.getDeliveryDuration());
		oldDto.setDescription(newDto.getDescription());
		oldDto.setMinQuantity(newDto.getMinQuantity());
		oldDto.setContactPersonName(newDto.getContactPersonName());
		oldDto.setContactEmailId(newDto.getContactEmailId());
		oldDto.setTechnicalBidFromDate(newDto.getTechnicalBidFromDate());
		oldDto.setTechnicalBidToDate(newDto.getTechnicalBidToDate());
		oldDto.setContactPersonNo(newDto.getContactPersonNo());
		oldDto.setTahdr(newDto.getTahdr());
		return oldDto;
		
	}
	
	@Override
	public List<TAHDRDetailDto> getPublishedTadhrDetail(String typeCode)
	{
		List<TAHDRDetailDto> tenderList=new ArrayList<TAHDRDetailDto>();
		try{
			List<TAHDRDetail> tender=tahdrDetailDao.getPublishedTadhrDetail(typeCode);	
			tenderList=getDtoList(tender);
		}
		catch(Exception e) {
			log.error(e.toString());
			e.printStackTrace();
		}
		return tenderList;
	}
	@Override
	public List<TAHDRDetailDto> getTadhrDetailByStatus(String typeCode,String status)
	{
		List<TAHDRDetailDto> tenderList=new ArrayList<TAHDRDetailDto>();
		try{
			List<TAHDRDetail> tender=tahdrDetailDao.getTadhrDetailByStatus(typeCode,status);	
			tenderList=getDtoList(tender);
		}
		catch(Exception e) {
			log.error(e.toString());
			e.printStackTrace();
		}
		return tenderList;
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean updateDeviationScheduleInfo(TAHDRDetailDto dto)
	{
		int result=tahdrDetailDao.updateDeviationScheduleInfo(dto);
		if(result>0) {
			return true;
		}
		return false;
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	private boolean copyOldVersionDataToNewVersion(Long newTahdrDetailId,Long oldTahdrDetailId){
		try{
			tahdrMaterialService.copyTahdrMaterialsToNewVersion(newTahdrDetailId, oldTahdrDetailId);
			tahdrMaterialGtpService.copyTahdrMaterialGTPToNewVersion(newTahdrDetailId, oldTahdrDetailId);
			sectionDocumentService.copySectionDocsToNewVersion(newTahdrDetailId, oldTahdrDetailId);
			stdCustDocService.copyStdCustDocsToNewVersion(newTahdrDetailId, oldTahdrDetailId);
		}catch (Exception e){
			log.error(e.getMessage());
			throw new RuntimeCryptoException("Failed to copy old data to new. Create Version aborted.");
		}
		return true;
	}
	
	@Override
	@Transactional(propagation=Propagation.SUPPORTS)
	public AttachmentDto getTenderDoc(Long tahdrId) {
		Attachment attachment = tahdrDetailDao.getTenderDoc(tahdrId);
		return attachmentService.getDto(attachment, null);
		
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public int updateWithTenderDoc(Long attachmentId, Long tahdrId) {
		return tahdrDetailDao.updateWithTenderDoc(attachmentId, tahdrId);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean setNewAuctionFromDate(Long tahdrId,Date newAuctionFromDate){
		int count=tahdrDetailDao.setNewAuctionFromDate(tahdrId, newAuctionFromDate);
		if(count>0){
			return true;
			}
		return false;
	}

	@Override
	public Long getPreliminaryScrutinyCount() {
		return getRecordCount();
	}
	public long getPreliminaryScrutinyTenderCount(String roleName,String typeCode,Map<String, Object> params,String searchColumn, String searchValue){
		long resultCount=0l;
		String searchParam="";
		if(typeCode !=null && roleName !=null){
			if (!"none".equalsIgnoreCase(searchValue)) {
				params.put("searchValue", "%" + searchValue.toUpperCase() + "%");
				searchParam=" AND c.tahdr."+searchColumn+" LIKE :searchValue";
			}
			if(typeCode.equals("FA") || typeCode.equals("RA")){
				if(roleName.equals(AppBaseConstant.ROLE_AUDITOR_USER)){
					String whereCondition=" upper(c.tahdr.tahdrTypeCode)=upper(:typeCode) AND c.tahdr.isAuction='Y' AND c.tahdr.tahdrStatusCode=:status AND c.tahdr.auditorUser.userId=:userId AND c.isActive='Y' "+searchParam+" ";
					resultCount= getRecordCount(whereCondition, params);
				}else{
					String whereCondition=" upper(c.tahdr.tahdrTypeCode)=upper(:typeCode) AND c.tahdr.isAuction='Y' AND c.tahdr.tahdrStatusCode=:status AND c.isActive='Y' "+searchParam+" ";
					resultCount= getRecordCount(whereCondition, params);
				}
			}else{
				if(roleName.equals(AppBaseConstant.ROLE_AUDITOR_USER)){
					String whereCondition=" upper(c.tahdr.tahdrTypeCode)=upper(:typeCode) AND c.tahdr.tahdrStatusCode=:status AND c.tahdr.auditorUser.userId=:userId AND c.isActive='Y' "+searchParam+"   ";
					resultCount= getRecordCount(whereCondition, params);
				}else{
					String whereCondition=" upper(c.tahdr.tahdrTypeCode)=upper(:typeCode) AND c.tahdr.tahdrStatusCode=:status AND c.isActive='Y' "+searchParam+" ";
					resultCount= getRecordCount(whereCondition, params);
				}
			}
		}else{
			return resultCount;
		}
		return resultCount;
	}
	
	public List<TAHDRDetailDto> getPreliminaryScrutinyTender(String queryMethodName,Map<String, Object> params,int pageNumber, int pageSize,String searchColumn, String searchValue){
		List<TAHDRDetailDto> tahdrDetailList=new ArrayList<TAHDRDetailDto>();
		String query = invokeQueryMethod(tahdrDetailDao, queryMethodName);
		StringBuilder customeQuery=new StringBuilder(query);
		if (!"none".equalsIgnoreCase(searchValue)) {
			query=customeQuery.append(" AND  t.tahdr."+searchColumn+" LIKE :searchValue ORDER BY t.updated DESC").toString();
			params.put("searchValue",
					"%" + searchValue.toUpperCase() + "%");
		}else{
			query=customeQuery.append("  ORDER BY t.updated DESC").toString();
		}
		tahdrDetailList = findDtosByQuery(query, params, pageNumber, pageSize);
		return tahdrDetailList;
	}
	
	@Override
	public long getTahdrPurchaseQueryCount(String typeCode, BPartnerDto partnerDto,String searchColumn,String searchValue) {
		long totalCount;
		Map<String, Object> map = new HashMap<>();
		map.put("typeCode", typeCode);
		map.put("statusCode", AppBaseConstant.DOCUMENT_STATUS_PUBLISHED);
		String queryString =tahdrDetailDao.getTAHDRPurchaseCount(typeCode,partnerDto,searchColumn, searchValue);
		if (!"none".equalsIgnoreCase(searchValue)) {
			map.put("searchValue", "%" + searchValue.toUpperCase() + "%");
			totalCount = getRecordCount(queryString, map);
		}else{
			totalCount = getRecordCount(queryString, map);
		}
		return totalCount;
	}
	
	@Override
	public List<TAHDRDetailDto> getTenderList(String typeCode, BPartnerDto partnerDto, int pageNumber, int pageSize,String searchColumn, String searchValue) {
		Map<String, Object> map = new HashMap<>();
		map.put("typeCode", typeCode);
		map.put("statusCode", AppBaseConstant.DOCUMENT_STATUS_PUBLISHED);
		map.put("bPartnerId", partnerDto.getbPartnerId());
		String queryString = tahdrDetailDao.getTAHDRTypeCode(typeCode,partnerDto,searchColumn, searchValue);
		if (!"none".equalsIgnoreCase(searchValue)) {
			map.put("searchValue", "%" + searchValue.toUpperCase() + "%");
		} 
		List<TAHDRDetailDto> tahdrEntity = findDtosByQuery(queryString, map, pageNumber, pageSize);
		return tahdrEntity;
	}

	@Override
	public void mailNotificationToBidders(BidderDto bidder,TAHDRDetailDto tahdrDetail){
		MailTemplateDto mailData=mailTemplateService.getMailTemplateFromTemplateType(AppBaseConstant.TEMPLATE_TENDER_DETAIL);
		Map<String, Object> map=AbstractServiceImpl.getParamMap("role",ContextConstant.USER_TYPE_SYSUSER);
		UserDto internalUser=userService.findDto("getUserByRoleCode", map);
		if(mailData!=null  && internalUser!=null){
			String today=new SimpleDateFormat("dd-MM-yyyy").format(new Date());
			Map<String, Object> params=new HashMap<>();
			params.put("@sourceCompanyName@",internalUser.getPartner().getName());
			params.put("@honour@","");
			params.put("@msebAddress@",internalUser.getUserDetails().getLocation().getAddress1());
			params.put("@emailCorrCode@","Renewal");
			params.put("@generalDetailDate@",today);
			params.put("@vendorComapnyName@",bidder.getPartner().getName());
			params.put("@factoryName@","");
			params.put("@factoryCity@","");
			params.put("@vendorEmail@",bidder.getCreatedBy().getEmail());
			params.put("@tenderCode@", tahdrDetail.getTahdr().getTahdrCode());
			params.put("@authoriyNameAndDesignation@",internalUser.getUserDetails().getFirstName()+" "+internalUser.getUserDetails().getLastName());
			params.put("@companyTel@",internalUser.getUserDetails().getTelephone1());
			params.put("@companyFax@",internalUser.getUserDetails().getFax1());
			if(bidder.getCreatedBy().getEmail()!=null){
				mailService.sentMailByTemplate(mailData, params, bidder.getCreatedBy().getEmail());
			}
		}
	}
	
	@Override
	public void mailNotificationOfDateToBidders(BidderDto bidder,TAHDRDetailDto tahdrDetail,StringBuilder html){
		MailTemplateDto mailData=mailTemplateService.getMailTemplateFromTemplateType(AppBaseConstant.TEMPLATE_TENDER_DATE);
		Map<String, Object> map=AbstractServiceImpl.getParamMap("role",ContextConstant.USER_TYPE_SYSUSER);
		UserDto internalUser=userService.findDto("getUserByRoleCode", map);
		if(mailData!=null  && internalUser!=null){
			String today=new SimpleDateFormat("dd-MM-yyyy").format(new Date());
			Map<String, Object> params=new HashMap<>();
			params.put("@sourceCompanyName@",internalUser.getPartner().getName());
			params.put("@honour@","");
			params.put("@msebAddress@",internalUser.getUserDetails().getLocation().getAddress1());
			params.put("@emailCorrCode@","Renewal");
			params.put("@generalDetailDate@",today);
			params.put("@vendorComapnyName@",bidder.getPartner().getName());
			params.put("@factoryName@","");
			params.put("@factoryCity@","");
			params.put("@vendorEmail@",bidder.getCreatedBy().getEmail());
			params.put("@tenderCode@", tahdrDetail.getTahdr().getTahdrCode());
			params.put("@authoriyNameAndDesignation@",internalUser.getUserDetails().getFirstName()+" "+internalUser.getUserDetails().getLastName());
			params.put("@companyTel@",internalUser.getUserDetails().getTelephone1());
			params.put("@companyFax@",internalUser.getUserDetails().getFax1());
			params.put("@html@", html.toString());
			params.put("@Subject@",mailData.getSubject());
			if(bidder.getCreatedBy().getEmail()!=null){
				mailService.sentMailByTemplate(mailData, params, bidder.getCreatedBy().getEmail());
			}
		}
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public int updateTenderDocOnDrafted(TAHDRDetailDto dto) {
		int records=0;
		try{
		Map<String, Object> tenderDoc = new HashMap<>();
		if(dto!=null){
		if(dto.getTenderDoc()!=null && dto.getTahdr().getTahdrStatusCode().equalsIgnoreCase(AppBaseConstant.DOCUMENT_STATUS_DRAFTED)){
			tenderDoc.put("tenderDoc", null);
			records=updateByJpql(tenderDoc, "tahdrDetailId",dto.getTahdrDetailId());
		}
		}
		}
		catch(Exception e){
			e.printStackTrace();

		}
		return records;
	}

	@Override
	public long getPreliminaryScrutinyTenderCountForWorkflow(String roleName,String typeCode,Map<String, Object> params,String searchColumn, String searchValue) {
		long resultCount=0l;
		String searchParam="";
		if( roleName !=null){
			if (!"none".equalsIgnoreCase(searchValue)) {
				params.put("searchValue", "%" + searchValue.toUpperCase() + "%");
				searchParam=" AND c.tahdr."+searchColumn+" LIKE :searchValue";
			}
			
				if(roleName.equals(AppBaseConstant.ROLE_AUDITOR_USER)){
					String whereCondition="  c.tahdr.tahdrStatusCode=:status AND c.tahdr.auditorUser.userId=:userId AND c.isActive='Y' "+searchParam+"  ORDER BY c.updated DESC ";
					resultCount= getRecordCount(whereCondition, params);
				}else{
					String whereCondition="  c.tahdr.tahdrStatusCode=:status AND c.isActive='Y' "+searchParam+"  ORDER BY c.updated DESC";
					resultCount= getRecordCount(whereCondition, params);
				}
			
		}else{
			return resultCount;
		}
		return resultCount;
	}

	@Override
	public long getAuctionPreliminaryScrutinyTenderCountForWorkflow(String roleName,String typeCode,Map<String, Object> params,String searchColumn, String searchValue) {
		long resultCount=0l;
		String searchParam="";
		if( roleName !=null){
			if (!"none".equalsIgnoreCase(searchValue)) {
				params.put("searchValue", "%" + searchValue.toUpperCase() + "%");
				searchParam=" AND c.tahdr."+searchColumn+" LIKE :searchValue";
			}
			
			if(roleName.equals(AppBaseConstant.ROLE_AUDITOR_USER)){
				String whereCondition="  c.tahdr.isAuction='Y' AND c.tahdr.tahdrStatusCode=:status AND c.tahdr.auditorUser.userId=:userId AND c.isActive='Y' "+searchParam+" ORDER BY c.updated DESC";
				resultCount= getRecordCount(whereCondition, params);
			}else{
				String whereCondition=" c.tahdr.isAuction='Y' AND c.tahdr.tahdrStatusCode=:status AND c.isActive='Y' "+searchParam+"  ORDER BY c.updated DESC";
				resultCount= getRecordCount(whereCondition, params);
			}
			
		}else{
			return resultCount;
		}
		return resultCount;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean endQuicKAuction(Long tahdrId,Date extenedDate) {
		int count=tahdrDetailDao.endQuicKAuction(tahdrId,extenedDate);
		if(count>0){
			count =tahdrService.updateByJpql(AbstractContextServiceImpl.getParamMap("isAuctionExtended", "Y"),"tahdrId", tahdrId);
			if(count>0)
			return true	;
			else return false;
		}
		return false;
	}
	
	private TAHDRDetailDto validatePreparationScope(TAHDRDetailDto detaildto){
		if(detaildto!=null){
			String isTechnicalBidNeeded=detaildto.getIsTechnicalBid()!=null?detaildto.getIsTechnicalBid():"";
			String isTechnicalDocsNeeded=detaildto.getIsTechnicalDocs()!=null?detaildto.getIsTechnicalDocs():"";
			String isGtpNeeded=detaildto.getIsGTP()!=null?detaildto.getIsGTP():"";
			String isCommercialDocsNeeded=detaildto.getIsCommercialDocs()!=null?detaildto.getIsCommercialDocs():"";
			String isPriceDocsNeeded=detaildto.getIsPriceDocs()!=null?detaildto.getIsPriceDocs():"";
			String isPreliminaryScrutiny=detaildto.getIsPreliminaryScrutiny()!=null?detaildto.getIsPreliminaryScrutiny():"";
			/*technical bid check*/
			if(isTechnicalDocsNeeded.equals("Y") || isGtpNeeded.equals("Y")){
				detaildto.setIsTechnicalBid("Y");
				isTechnicalBidNeeded="Y";
			}else{
				detaildto.setIsTechnicalBid("N");
				isTechnicalBidNeeded="N";
			}
			
			/*required Docs check*/
			if(isTechnicalDocsNeeded.equals("Y") || isPriceDocsNeeded.equals("Y") || isCommercialDocsNeeded.equals("Y")){
				detaildto.setIsReqDocs("Y");
			}else{
				detaildto.setIsReqDocs("N");
			}
			
			/*preliminaryScrutiny Check*/
			if(!isPreliminaryScrutiny.equals("")){
				if(detaildto.getIsPreliminaryScrutiny().equals("Y") && (isTechnicalBidNeeded.equals("Y") || isCommercialDocsNeeded.equals("Y"))){
					detaildto.setIsPreliminaryScrutiny("Y");
				}else{
					detaildto.setIsPreliminaryScrutiny("N");
				}
				
				/*finalScrutiny and deviation check*/
				if(detaildto.getIsPreliminaryScrutiny().equals("N")){
					detaildto.setIsFinalScrutiny("N");
					detaildto.setIsDeviation("N");
				}
			}
			
		}
		return detaildto;
	}
	
	@Override
	public void validate(TAHDRDetailDto tahdrDetail, Errors errors){
		if( tahdrDetail.getTechnicalBidFromDate()==null){
			errors.addError("invalid.techBidStartDate","Bid Start Date is Mandatory");
		}else if(new Date().compareTo(tahdrDetail.getTechnicalBidFromDate())==0){
			errors.addError("invalid.saleDate", "Bid Start Date should not be equal to Sale Start Date");
		}else if(new Date().compareTo(tahdrDetail.getTechnicalBidFromDate())==1){
			errors.addError("invalid.techBidStartDate", "Bid Start Date should be after Sale Start Date");
		}else if( tahdrDetail.getTechnicalBidToDate()==null){
			errors.addError("invalid.techBidStartDate","Bid Start Date is Mandatory");
		}else if(tahdrDetail.getTechnicalBidFromDate().compareTo(tahdrDetail.getTechnicalBidToDate())==0){
			errors.addError("invalid.saleDate", "Bid Start Date should not be equal to Sale Start Date");
		}else if(tahdrDetail.getTechnicalBidFromDate().compareTo(tahdrDetail.getTechnicalBidToDate())==1){
			errors.addError("invalid.techBidStartDate", "Bid Start Date should be after Sale Start Date");
		}
		
		if("Y".equalsIgnoreCase(tahdrDetail.getIsWinnerSelectionDateSetLater())){
			if( tahdrDetail.getWinnerSelectionDate()==null){
				errors.addError("invalid.winnerselectiondate","Winner Selection Date Start Date is Mandatory");
			}else if(tahdrDetail.getTechnicalBidToDate().compareTo(tahdrDetail.getWinnerSelectionDate())==0){
				errors.addError("invalid.winnerselectiondate", "Winner Selection Date should not be equal to Rfq Bid End Date");
			}else if(tahdrDetail.getTechnicalBidToDate().compareTo(tahdrDetail.getWinnerSelectionDate())==1){
				errors.addError("invalid.winnerselectiondate", "Winner Selection Date should be after Rfq Bid End Date");
			}	
		}
	}
}

	