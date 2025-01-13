/**
 * @author Ankush
 */
package com.novelerp.eat.service.impl;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.novelerp.alkyl.dto.AdvanceShipmentNoticeLineDto;
import com.novelerp.alkyl.dto.PRLineDto;
import com.novelerp.alkyl.dto.QuotationDto;
import com.novelerp.alkyl.service.BidderAttachmentService;
import com.novelerp.alkyl.service.PRLineService;
import com.novelerp.appbase.master.dto.AttachmentDto;
import com.novelerp.appbase.master.dto.CustomResponseDto;
import com.novelerp.appbase.master.dto.MaterialSpecificationDto;
import com.novelerp.appbase.master.service.AttachmentService;
import com.novelerp.appbase.master.service.MaterialSpecificationService;
import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.appcontext.dto.BPartnerDto;
import com.novelerp.appcontext.service.ContextService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.appcontext.util.ContextConstant;
import com.novelerp.commons.util.CommonUtil;
import com.novelerp.core.dto.BidStatisticDto;
import com.novelerp.core.dto.Errors;
import com.novelerp.core.dto.GraphDto;
import com.novelerp.core.service.SecurityService;
import com.novelerp.core.service.impl.MD5Security;
import com.novelerp.core.util.FileUtil;
import com.novelerp.core.util.ValidationUtil;
import com.novelerp.eat.dao.PriceBidDao;
import com.novelerp.eat.dto.BidderDto;
import com.novelerp.eat.dto.BidderSectionDocDto;
import com.novelerp.eat.dto.ItemBidDto;
import com.novelerp.eat.dto.PriceBidDto;
import com.novelerp.eat.dto.SectionDocumentDto;
import com.novelerp.eat.entity.PriceBid;
import com.novelerp.eat.service.BidderSectionDocService;
import com.novelerp.eat.service.BidderService;
import com.novelerp.eat.service.CommercialBidService;
import com.novelerp.eat.service.ItemBidService;
import com.novelerp.eat.service.PriceBidService;
import com.novelerp.eat.service.SectionDocumentService;

@Service
public class PriceBidServiceImpl extends AbstractContextServiceImpl<PriceBid, PriceBidDto> implements PriceBidService {

	public String SEC_KEY=ContextConstant.SEC_KEY;
	public String IV_PREFIX=ContextConstant.IV_PREFIX;
	
	@Autowired
	private PriceBidDao priceBidDao; 
	
	@Autowired
	private BidderSectionDocService bidderSecDocService;
	
	@Autowired
	private SectionDocumentService secDocService;
	
	@Autowired
	private MaterialSpecificationService materialSpecService;
	
	@Autowired
	private SecurityService securityService;
	
	@Autowired
	private PRLineService prLineService;
	
	@Autowired
	 @Qualifier("jwtUserContext") private ContextService contextService;
	
	@Autowired
	private FileUtil fileUtil;
	
	@Autowired
	private AttachmentService attachmentService;
	
	@Autowired
	private ItemBidService itemBidService;
	
	@Autowired
	private CommercialBidService commercialBidService;
	
	@Autowired
	private BidderService bidderService;
	
	@Autowired
	private BidderAttachmentService bidderAttachmentService;
	
	@Autowired
	private ValidationUtil validationUtil;
	
	@PostConstruct
	void init(){
		super.init(PriceBidServiceImpl.class, priceBidDao, PriceBid.class, PriceBidDto.class);
		setByPassProxy(true);
	}
	
	/* (non-Javadoc)
	 * @see com.novelerp.appcontext.service.impl.AbstractContextServiceImpl#save(com.novelerp.appcontext.dto.CommonContextDto)
	 */
	@Override
	public PriceBidDto save(PriceBidDto dto) {
		if(null==dto.getPriceBidId() || !(dto.getPriceBidId()>0)){
			Set<BidderSectionDocDto> bidderSecDocSet=dto.getBidderSecDoc();
			dto.setBidderSecDoc(null);
			/*dto.setStatus(AppBaseConstant.DOCUMENT_STATUS_DRAFTED);*/
			dto.setReferedItemBid(dto.getItemBid());
			dto=calculate(dto);
			dto=encryptPB(dto);
			if(dto!=null && (dto.getResponse()==null || !dto.getResponse().isHasError())){
				dto= super.save(dto);
			}else{
				dto=decryptPB(dto);
				return dto;
			}
			dto.setBidderSecDoc(bidderSecDocSet);
		}else if(null==dto.getBidderSecDoc() || dto.getBidderSecDoc().isEmpty()){
			/*dto.setStatus(AppBaseConstant.DOCUMENT_STATUS_DRAFTED);*/
			dto=calculate(dto);
			dto=encryptPB(dto);
			if(dto!=null && (dto.getResponse()==null || !dto.getResponse().isHasError())){
				dto=super.updateDto(dto);
			}else{
				dto=decryptPB(dto);
				return dto;
			}
			
		}
		if(null!=dto.getBidderSecDoc() && !dto.getBidderSecDoc().isEmpty()){
			dto=bidderSecDocService.save(dto);
		}
		dto=decryptPB(dto);
		return dto;
	}
	
	@Override
	public PriceBidDto saveLiveBid(PriceBidDto dto) {
		if(null==dto.getPriceBidId() || !(dto.getPriceBidId()>0)){
			Set<BidderSectionDocDto> bidderSecDocSet=dto.getBidderSecDoc();
			dto.setBidderSecDoc(null);
			dto.setStatus(AppBaseConstant.DOCUMENT_STATUS_DRAFTED);
			dto.setReferedItemBid(dto.getItemBid());
			if(null==dto.getBidderSecDoc() || dto.getBidderSecDoc().isEmpty()){
				dto=calculate(dto);
			}
			dto= super.save(dto);
			dto.setBidderSecDoc(bidderSecDocSet);
		}else if(null==dto.getBidderSecDoc() || dto.getBidderSecDoc().isEmpty()){
			dto.setStatus(AppBaseConstant.DOCUMENT_STATUS_DRAFTED);
			dto=calculate(dto);
			dto=super.updateDto(dto);
		}
		if(null!=dto.getBidderSecDoc() && !dto.getBidderSecDoc().isEmpty()){
			dto=bidderSecDocService.save(dto);
		}
		return dto;
	}
	
	@Override
	public PriceBidDto mergeUpdatedParts(Long tahdrMaterialId,Long partId){
		BPartnerDto partner=contextService.getPartner();
		Long partnerId=partner.getbPartnerId();
		PriceBidDto pb=getPriceBid(tahdrMaterialId, partnerId);
		if(pb!=null){
			List<PriceBidDto> parts=getPriceBidForParts(tahdrMaterialId, partnerId);
			if(!CommonUtil.isCollectionEmpty(parts)){
				pb.setExGroupPriceRate("0");
				pb.setFreightChargeRate(0);
				pb.setTicRate("0");
				for(PriceBidDto part:parts){
					if((partId==null || !CommonUtil.isEqual(partId, part.getPriceBidId())) && null!=part.getPriceBidId()){
						pb=mergePart(pb,part);
					}
				}
			}
		}
		return pb;
	}
	
	@Override
	public PriceBidDto mergePart(PriceBidDto pbNew,PriceBidDto part){
		
		BigDecimal totalExWorksPriceForPart=new BigDecimal(part.getTotalExGroupPrice());
		BigDecimal totalFreightChargeForPart=new BigDecimal(part.getTotalFreightCharge());
		BigDecimal totalTicForPart=new BigDecimal(part.getTotalTic());
		
		BigDecimal exWorksPriceRatePB=new BigDecimal(pbNew.getExGroupPriceRate());;
		BigDecimal freightChargePB=new BigDecimal(pbNew.getFreightChargeRate());;
		BigDecimal ticPB=new BigDecimal(pbNew.getTicRate());
		
		exWorksPriceRatePB=exWorksPriceRatePB.add(totalExWorksPriceForPart);
		freightChargePB=freightChargePB.add(totalFreightChargeForPart);
		ticPB=ticPB.add(totalTicForPart);

		pbNew.setExGroupPriceRate(exWorksPriceRatePB.toString());
		/*pbNew.setFreightChargeRate(freightChargePB.toString());*/
		pbNew.setTicRate(ticPB.toString());
		
		return pbNew;
	}
	
	@Override
	public PriceBidDto calculate(PriceBidDto dto){
		if(null==dto.getBidderSecDoc() || dto.getBidderSecDoc().isEmpty()){
			BPartnerDto partner=contextService.getPartner();
			BigDecimal qty=new BigDecimal(dto.getOfferedQuantity());
			BigDecimal exWorksPriceRate=new BigDecimal(dto.getExGroupPriceRate());
			BigDecimal freightCharge=new BigDecimal(dto.getFreightChargeRate());
			BigDecimal tic=new BigDecimal(dto.getTicRate());
			BigDecimal taxRate=BigDecimal.ZERO;
			BigDecimal igst=BigDecimal.ZERO;
			BigDecimal cgst=BigDecimal.ZERO;
			BigDecimal sgst=BigDecimal.ZERO;
			
			BigDecimal taxAmount=BigDecimal.ZERO;
			BigDecimal igstAmount=BigDecimal.ZERO;
			BigDecimal cgstAmount=BigDecimal.ZERO;
			BigDecimal sgstAmount=BigDecimal.ZERO;
			
			BigDecimal totalTax=BigDecimal.ZERO;
			BigDecimal totalIgst=BigDecimal.ZERO;
			BigDecimal totalCgst=BigDecimal.ZERO;
			BigDecimal totalSgst=BigDecimal.ZERO;
			
			BigDecimal fddRateWithGST=BigDecimal.ZERO;
			BigDecimal fddAmountWithGST=BigDecimal.ZERO;
			
			BigDecimal totalExWorksPrice=qty.multiply(exWorksPriceRate);
			BigDecimal totalFreightCharge=qty.multiply(freightCharge);
			BigDecimal totalTic=qty.multiply(tic);
			
			BigDecimal fddRate=exWorksPriceRate.add(freightCharge).add(tic);//without GST
			BigDecimal fddAmount=qty.multiply(fddRate);//total fdd without GST
			
			if(dto.getMaterialSpecification()==null && partner.getIsGstApplicable().equals("Y")){
				
				taxRate=new BigDecimal(dto.getTaxRate());
				taxAmount=fddRate.multiply(taxRate).divide(new BigDecimal(100));
				totalTax=qty.multiply(taxAmount);
				
				if(partner.isIntraState()){
					cgst=taxRate.divide(new BigDecimal(2));
					sgst=taxRate.divide(new BigDecimal(2));
					
					cgstAmount=fddRate.multiply(cgst).divide(new BigDecimal(100));
					sgstAmount=fddRate.multiply(sgst).divide(new BigDecimal(100));
					
					totalCgst=qty.multiply(cgstAmount);
					totalSgst=qty.multiply(sgstAmount);
				}else{
					igst=taxRate;
					
					igstAmount=fddRate.multiply(igst).divide(new BigDecimal(100));
					
					totalIgst=qty.multiply(igstAmount);
				}
				
				dto.setIgst(igst.toString());
				dto.setCgst(cgst.toString());
				dto.setSgst(sgst.toString());
				
				dto.setTaxAmount(taxAmount.toString());
				dto.setIgstAmount(igstAmount.toString());
				dto.setCgstAmount(cgstAmount.toString());
				dto.setSgstAmount(sgstAmount.toString());
				
				dto.setTotalTax(totalTax.toString());
				dto.setTotalIgst(totalIgst.toString());
				dto.setTotalCgst(totalCgst.toString());
				dto.setTotalSgst(totalSgst.toString());
			}
			
			fddRateWithGST=fddRate.add(taxAmount);
			fddAmountWithGST=fddAmount.add(totalTax);

			dto.setFddRateWithGST(fddRateWithGST.toString());
			dto.setFddAmountWithGST(fddAmountWithGST.toString());
			
			dto.setTotalExGroupPrice(totalExWorksPrice.toString());
			/*dto.setTotalFreightCharge(totalFreightCharge.toString());*/
			dto.setTotalTic(totalTic.toString());
			
			dto.setFddRate(fddRate.toString());
			dto.setFddAmount(fddAmount.toString());
			
		return dto;
		}else{
			return dto;
		}
			
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public PriceBidDto submit(PriceBidDto dto,boolean doEncryt){
		dto.setStatus(AppBaseConstant.BIDDER_STATUS_BID_SUBMITED);
		dto.setEncryptBid(doEncryt);
		if(dto!=null && (dto.getResponse()==null || !dto.getResponse().isHasError())){
			dto=encryptPB(dto);
			dto=super.updateDto(dto);
		}else{
			return dto;
		}
		dto=decryptPB(dto);
		return dto;
	}
	/* (non-Javadoc)
	 * @see com.novelerp.eat.service.PriceBidService#createRevisedBid(java.lang.Long)
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public int createRevisedBid(Long itemBidId,Long priceBidId) {
		if(itemBidId!=null && priceBidId!=null){
			return priceBidDao.createRevisedBid(itemBidId,priceBidId);	
		}
		return 0;
	}

	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public int createPreviousBid(Long itemBidId,Long priceBidId,String bidRemark) {
		if(itemBidId!=null && priceBidId!=null && !bidRemark.equals("")){
			return priceBidDao.createPreviousBid(itemBidId,priceBidId,bidRemark);	
		}
		return 0;
	}
	
	/* (non-Javadoc)
	 * @see com.novelerp.eat.service.PriceBidService#updateReqDocsForRevisedBid(java.lang.Long, java.lang.Long)
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public int updateReqDocsForRevisedBid(Long priceBidId, Long value) {
		return priceBidDao.updateReqDocsForRevisedBid(priceBidId, value);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public int deleteOldBids(Long itemBidId) {
		return priceBidDao.deleteOldBids(itemBidId);
	}

	/* (non-Javadoc)
	 * @see com.novelerp.eat.service.PriceBidService#getPriceBid(java.lang.Long, java.lang.Long)
	 */
	@Override
	public PriceBidDto getPriceBid(Long tahdrMaterialId, Long partnerId) {
		Map<String, Object> map= new HashMap<>();
		map.put("tahdrMaterialId", tahdrMaterialId);
		map.put("partnerId", partnerId);
		PriceBidDto pb=findDto("getPriceBid", map);
		pb=decryptPB(pb);
		return pb;
	}
	
	@Override
	public List<BidderSectionDocDto> getPriceBidSectionDoc(Long tahdrMaterialId,Long partnerId){
		Map<String,Object> map=new HashMap<>();
		map.put("tahdrMaterialId", tahdrMaterialId);
		map.put("partnerId", partnerId);
		List<BidderSectionDocDto> bidderSecDoc=bidderSecDocService.findDtos("getPriceBidSectionDoc", map);
		if(CommonUtil.isCollectionEmpty(bidderSecDoc)){
			Map<String,Object> map1=new HashMap<>();
			map1.put("tahdrMaterialId", tahdrMaterialId);
			map1.put("sdCode", AppBaseConstant.PRICE_BID);
			List<SectionDocumentDto> sectionDocList= secDocService.findDtos("getSectionDocByCodeAndTahdrMaterialId", map1);
			bidderSecDoc=getBidderSecDocSetFromTMG(sectionDocList);
		}
		return bidderSecDoc;
	}
	
	private List<BidderSectionDocDto> getBidderSecDocSetFromTMG(List<SectionDocumentDto> sectionDocSet){
		List<BidderSectionDocDto> bidderSectionDocSet= new ArrayList<>();
		if(null!=sectionDocSet){
			for(SectionDocumentDto sd:sectionDocSet){
					BidderSectionDocDto bidderSecDocDto=new BidderSectionDocDto();
					bidderSecDocDto.setSectionDocument(sd);
					bidderSecDocDto.setBidSection(AppBaseConstant.PRICE_BID);
					bidderSectionDocSet.add(bidderSecDocDto);
			}
		}
		return bidderSectionDocSet;
	}

	/* (non-Javadoc)
	 * @see com.novelerp.eat.service.PriceBidService#getPriceBidForParts(java.lang.Long, java.lang.Long)
	 */
	@Override
	public List<PriceBidDto> getPriceBidForParts(Long tahdrMaterialId, Long partnerId) {
		Map<String, Object> map=new HashMap<>();
		map.put("tahdrMaterialId", tahdrMaterialId);
		map.put("partnerId", partnerId);
		List<PriceBidDto> pbList=findDtos("getPriceBidForParts",map);
		List<MaterialSpecificationDto> parts=materialSpecService.findDtos("getSpecificationListByTahdrMaterialId", AbstractContextServiceImpl.getParamMap("tahdrMaterialId", tahdrMaterialId));
		if(!CommonUtil.isCollectionEmpty(parts)){
			pbList=getPriceBidForPartsFromParts(pbList,parts);
			pbList=decryptPBList(pbList);
		}
		return pbList;
	}
	
	private List<PriceBidDto> getPriceBidForPartsFromParts(List<PriceBidDto> pbList,List<MaterialSpecificationDto> parts){
		if(CommonUtil.isCollectionEmpty(pbList)){
			pbList=new ArrayList<>();
		}
		for(MaterialSpecificationDto ms:parts){
			int count=0;
			for(PriceBidDto priceBid: pbList){
				if(!CommonUtil.isEqual(ms.getMaterialSpecificationId(), priceBid.getMaterialSpecification().getMaterialSpecificationId())){
					count++;
				}else{
					continue;
				}
			}
			if(count==pbList.size()){
				PriceBidDto pb=new PriceBidDto();
				pb.setMaterialSpecification(ms);
				pbList.add(pb);
			}
		}
		return pbList;
	}
	
	@Override
	public List<PriceBidDto> encryptPBList(List<PriceBidDto> pbList){
		List<PriceBidDto> pbListNew=new ArrayList<>();
		for(PriceBidDto pb:pbList){
			pbListNew.add(encryptPB(pb));
		}
		return pbListNew;
	}
	
	@Override
	public List<PriceBidDto> decryptPBList(List<PriceBidDto> pbList){
		List<PriceBidDto> pbListNew=new ArrayList<>();
		for(PriceBidDto pb:pbList){
			pbListNew.add(decryptPB(pb));
		}
		return pbListNew;
	}
	
	@Override
	public PriceBidDto encryptPB(PriceBidDto dto){
		if(dto!=null && dto.getIsEncrypted().equals("N") && dto.getEncryptBid() 
				&& (null==dto.getBidderSecDoc() || dto.getBidderSecDoc().isEmpty())){

			BPartnerDto partner=contextService.getPartner();
			String panNo="";
			if(partner!=null){
				panNo=partner.getPanNumber();
			}else{
				dto.getResponse().setHasError(true);
				dto.getResponse().setMessage("Partner is empty");
				return dto;
			}
			
			try{
				String iv=panNo+IV_PREFIX;
				
				dto.setExGroupPriceRate(securityService.secureData(dto.getExGroupPriceRate(), SEC_KEY,iv));
				dto.setTotalExGroupPrice(securityService.secureData(dto.getTotalExGroupPrice(), SEC_KEY,iv));
				
				dto.setFddAmount(securityService.secureData(dto.getFddAmount(), SEC_KEY,iv));
				dto.setFddRate(securityService.secureData(dto.getFddRate(), SEC_KEY,iv));
				
				/*dto.setFreightChargeRate(securityService.secureData(dto.getFreightChargeRate(), SEC_KEY,iv));
				dto.setTotalFreightCharge(securityService.secureData(dto.getTotalFreightCharge(), SEC_KEY,iv));*/
			
				dto.setOfferedQuantity(securityService.secureData(dto.getOfferedQuantity(), SEC_KEY,iv));
				
				dto.setTicRate(securityService.secureData(dto.getTicRate(), SEC_KEY,iv));
				dto.setTotalTic(securityService.secureData(dto.getTotalTic(), SEC_KEY,iv));
				
				if(dto.getMaterialSpecification()==null){
					dto.setFddAmountWithGST(securityService.secureData(dto.getFddAmountWithGST(), SEC_KEY,iv));
					dto.setFddRateWithGST(securityService.secureData(dto.getFddRateWithGST(), SEC_KEY,iv));
					dto.setAmountInWords(securityService.secureData(dto.getAmountInWords(), SEC_KEY,iv));
					if(partner.getIsGstApplicable().equals("Y")){
						encryptGSTFields(partner, dto, iv, securityService);
					}
				}
				
				dto.setIsEncrypted("Y");
				
			}catch(Exception e){
				log.error(e.getMessage());
				throw new RuntimeException("Error While Encryption.");
			}
			
			return dto;
		
		}else{
			return dto;
		}
	}

	@Override
	public PriceBidDto decryptPB(PriceBidDto dto){
		if(dto!=null && dto.getIsEncrypted().equals("Y") 
				&& (null==dto.getBidderSecDoc() || dto.getBidderSecDoc().isEmpty())){

			BPartnerDto partner=dto.getPartner();
			String panNo="";
			if(partner!=null){
				panNo=partner.getPanNumber();
			}else{
				dto.getResponse().setHasError(true);
				dto.getResponse().setMessage("Partner is empty");
				return dto;
			}
			try{
				String iv=panNo+IV_PREFIX;
				
				dto.setExGroupPriceRate(securityService.decryptData(dto.getExGroupPriceRate(), SEC_KEY,iv));
				dto.setTotalExGroupPrice(securityService.decryptData(dto.getTotalExGroupPrice(), SEC_KEY,iv));
				
				dto.setFddAmount(securityService.decryptData(dto.getFddAmount(), SEC_KEY,iv));
				dto.setFddRate(securityService.decryptData(dto.getFddRate(), SEC_KEY,iv));
				
				/*dto.setFreightChargeRate(securityService.decryptData(dto.getFreightChargeRate(), SEC_KEY,iv));
				dto.setTotalFreightCharge(securityService.decryptData(dto.getTotalFreightCharge(), SEC_KEY,iv));
				*/
				dto.setOfferedQuantity(securityService.decryptData(dto.getOfferedQuantity(), SEC_KEY,iv));
				
				dto.setTicRate(securityService.decryptData(dto.getTicRate(), SEC_KEY,iv));
				dto.setTotalTic(securityService.decryptData(dto.getTotalTic(), SEC_KEY,iv));
				
				
				if(dto.getMaterialSpecification()==null){
					dto.setFddRateWithGST(securityService.decryptData(dto.getFddRateWithGST(), SEC_KEY,iv));
					dto.setFddAmountWithGST(securityService.decryptData(dto.getFddAmountWithGST(), SEC_KEY,iv));
					dto.setAmountInWords(securityService.decryptData(dto.getAmountInWords(), SEC_KEY,iv));
					if(partner.getIsGstApplicable().equals("Y")){
						try{
							decryptGSTFields(partner, dto, iv, securityService);	
						}
						catch (Exception e) {
							e.printStackTrace();
							log.error(e.getMessage());
						}
						
					}
				}
				
				dto.setIsEncrypted("N");
			}catch(Exception e){
				log.error(e.getMessage());
				throw new RuntimeException("Error While Decryption.");
			}
			
			return dto;
		
		}else{
			return dto;
		}
	}
	
	private PriceBidDto encryptGSTFields(BPartnerDto partner, PriceBidDto dto,String iv,SecurityService securityService) throws Exception{
		try{
			if(partner.isIntraState()){
				
				dto.setSgst(securityService.secureData(dto.getSgst(), SEC_KEY,iv));
				dto.setSgstAmount(securityService.secureData(dto.getSgstAmount(), SEC_KEY,iv));
				dto.setTotalSgst(securityService.secureData(dto.getTotalSgst(), SEC_KEY,iv));
				
				dto.setCgst(securityService.secureData(dto.getCgst(), SEC_KEY,iv));
				dto.setCgstAmount(securityService.secureData(dto.getCgstAmount(), SEC_KEY,iv));
				dto.setTotalCgst(securityService.secureData(dto.getTotalCgst(), SEC_KEY,iv));
				
			}else{
				
				dto.setIgst(securityService.secureData(dto.getIgst(), SEC_KEY,iv));
				dto.setIgstAmount(securityService.secureData(dto.getIgstAmount(), SEC_KEY,iv));
				dto.setTotalIgst(securityService.secureData(dto.getTotalIgst(), SEC_KEY,iv));
				
			}

			dto.setTaxAmount(securityService.secureData(dto.getTaxAmount(), SEC_KEY,iv));
			dto.setTaxRate(securityService.secureData(dto.getTaxRate(), SEC_KEY,iv));
			dto.setTotalTax(securityService.secureData(dto.getTotalTax(), SEC_KEY,iv));
		}catch(Exception e){
			log.error(e.getMessage());
			throw e;
		}
		return dto;
	}
	
	private PriceBidDto decryptGSTFields(BPartnerDto partner, PriceBidDto dto,String iv,SecurityService securityService) throws Exception{

		try{
			if(partner.isIntraState()){
				
				dto.setCgst(securityService.decryptData(dto.getCgst(), SEC_KEY,iv));
				dto.setCgstAmount(securityService.decryptData(dto.getCgstAmount(), SEC_KEY,iv));
				dto.setTotalCgst(securityService.decryptData(dto.getTotalCgst(), SEC_KEY,iv));
				
				dto.setSgst(securityService.decryptData(dto.getSgst(), SEC_KEY,iv));
				dto.setSgstAmount(securityService.decryptData(dto.getSgstAmount(), SEC_KEY,iv));
				dto.setTotalSgst(securityService.decryptData(dto.getTotalSgst(), SEC_KEY,iv));
				
			}else{
				
				dto.setIgst(securityService.decryptData(dto.getIgst(), SEC_KEY,iv));
				dto.setIgstAmount(securityService.decryptData(dto.getIgstAmount(), SEC_KEY,iv));
				dto.setTotalIgst(securityService.decryptData(dto.getTotalIgst(), SEC_KEY,iv));
				
			}
			
			dto.setTaxAmount(securityService.decryptData(dto.getTaxAmount(), SEC_KEY,iv));
			dto.setTaxRate(securityService.decryptData(dto.getTaxRate(), SEC_KEY,iv));
			dto.setTotalTax(securityService.decryptData(dto.getTotalTax(), SEC_KEY,iv));
		}catch(Exception e){
			log.error(e.getMessage());
			throw e;
		}
		
		return dto;
	
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean encryptAllPb(List<PriceBidDto> pbList){
		if(!CommonUtil.isCollectionEmpty(pbList)){
			for(PriceBidDto pb:pbList){
				try{
					pb.setEncryptBid(true);
					pb=encryptPB(pb);
					pb=updateDto(pb);
				}catch(Exception ex){
					return false;
				}				
			}
			return true;
		}else{
			return true;
		}
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean decryptAllPb(List<PriceBidDto> pbList){
		if(!CommonUtil.isCollectionEmpty(pbList)){
			for(PriceBidDto pb:pbList){
				pb=decryptPB(pb);
				/*try {
					if(!decryptPriceBidDoc(pb)){
						throw new RuntimeException("Decryption Failed");
					}
				} catch (IOException e) {
					log.error(e.getMessage());
					throw new RuntimeException("Decryption Failed");
				}*/
				pb=updateDto(pb);
			}
			return true;
		}else{
			return false;
		}
	}
	
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean saveEncryptedBidBeforeOpening(Long tahdrId,String bidRemark){
			int count=priceBidDao.createEncryptedBid(tahdrId, bidRemark);
			if(count>0){
				return true;
			}else{
				return false;
			}
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public int getOverAllRankBy(Long tahdrId,Long partnerId,String typeCode){
		List<Object> object=null;
		if(typeCode.equals("QFA") || typeCode.equals("QRA") || typeCode.equals("QRFQ") || typeCode.equals("RFQ")){
			if(typeCode.equals("QFA")){
				object=  priceBidDao.getOverAllQuickForwardRankBy(tahdrId, partnerId);
			}else{
				object=  priceBidDao.getOverAllQuickReverseRankBy(tahdrId, partnerId);
			}	
		}else{
			if(typeCode.equals("FA")){
				object=  priceBidDao.getOverAllForwardRankBy(tahdrId, partnerId);
			}else{
				object=  priceBidDao.getOverAllReverseRankBy(tahdrId, partnerId);
			}	
		}
		
		
		if(!CommonUtil.isCollectionEmpty(object)){
			Integer rank =  ((BigDecimal) object.iterator().next()).intValue();
			return rank;
		}
		
		return 0;
		
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public int getRankByMaterial(Long tahdrId,Long tahdrMaterialId,Long partnerId,String typeCode){
		List<Object> object=null;
		if(typeCode.equals("QFA") || typeCode.equals("QRA")){
			if(typeCode.equals("QFA")){
				object=  priceBidDao.getQuickForwardRankByMaterial(tahdrId, tahdrMaterialId, partnerId);
			}else{
				object=  priceBidDao.getQuickReverseRankByMaterial(tahdrId, tahdrMaterialId, partnerId);
			}	
		}else{
			if(typeCode.equals("FA")){
				object=  priceBidDao.getForwardRankByMaterial(tahdrId, tahdrMaterialId, partnerId);
			}else{
				object=  priceBidDao.getReverseRankByMaterial(tahdrId, tahdrMaterialId, partnerId);
			}	
		}
		if(!CommonUtil.isCollectionEmpty(object)){
			Integer rank =  ((BigInteger) object.iterator().next()).intValueExact();
			return rank;
		}
		
		return 0;
		
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public List<Object> getRankOfAllBidderByMaterial(Long tahdrId,Long tahdrMaterialId,String typeCode){
		List<Object> object=null;
		if(typeCode.equals("QFA") || typeCode.equals("QRA")){
			if(typeCode.equals("QFA")){
				object=  priceBidDao.getQuickForwardRankByMaterial(tahdrId, tahdrMaterialId);
			}else{
				object=  priceBidDao.getQuickReverseRankByMaterial(tahdrId, tahdrMaterialId);
			}	
		}else{
			if(typeCode.equals("FA")){
				object=  priceBidDao.getForwardRankByMaterial(tahdrId, tahdrMaterialId);
			}else{
				object=  priceBidDao.getReverseRankByMaterial(tahdrId, tahdrMaterialId);
			}	
		}
		return object;
		
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public int updateStatus(String status,Long id,Long digiSignDocId){
		Map<String,Object> propertyValueMap= new HashMap<>();
		propertyValueMap.put("status", status);
		propertyValueMap.put("digiSignedDoc.attachmentId", digiSignDocId);
		
		Map<String,Object> whereClauseParameters = new HashMap<>();
		whereClauseParameters.put("priceBidId", id);
		
		return priceBidDao.updateByJpql(propertyValueMap,whereClauseParameters);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public int updateStatusByItemBidId(String status,Long itemBidId,Long digiSignDocId){
		Map<String,Object> propertyValueMap= new HashMap<>();
		propertyValueMap.put("status", status);
		propertyValueMap.put("digiSignedDoc.attachmentId", digiSignDocId);
		
		Map<String,Object> whereClauseParameters = new HashMap<>();
		whereClauseParameters.put("itemBid.itemBidId", itemBidId);
		
		return priceBidDao.updateByJpql(propertyValueMap,whereClauseParameters);
	}
	
	/*@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean saveClauseData(Long priceBidId,Long itemBidId ,String clause,String clauseData){
		int count=priceBidDao.saveClauseData(priceBidId, itemBidId, clause, clauseData);
		if(count>0){
			return true;
		}
		return false;
	}*/
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean saveAnnexureC1FileResposne(PriceBidDto dto){
		Long priceBidId=dto.getPriceBidId();
		Long fileId=dto.getDigiSignedDoc().getAttachmentId();
		 int result=priceBidDao.saveAnnexureC1FileResposne(priceBidId,fileId);
		 if(result>0) return true;
			else return false;
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean resetAnnexureC1FileResposne(PriceBidDto dto){
		Long priceBidId=dto.getPriceBidId();
		 int result=priceBidDao.resetAnnexureC1FileResposne(priceBidId);
		 if(result>0) return true;
			else return false;
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean decryptPriceBidDoc(PriceBidDto pb) throws IOException{
		AttachmentDto att=pb.getDigiSignedDoc();
		BPartnerDto partner=pb.getPartner();
		String path=att.getPath();
		String fileName=att.getName();
		
		try{
			File file=new File(path+fileName);			
			byte[] bytes=fileUtil.getBytesFromFile(file);
			if(bytes==null){
				throw new RuntimeException("File Not Found");
			}
			boolean attachmentDecrypted= attachmentService.updateAttachmentDecryptionStatus(att);
			
			if(attachmentDecrypted){
				
				String iv=IV_PREFIX+partner.getPanNumber();
				bytes=MD5Security.decryptDoc(bytes, SEC_KEY, iv);
				
				boolean fileWriteComplete=fileUtil.writeBytesToFile(file, bytes);
				if(fileWriteComplete){
					return true;
				}else{
					return false;
				}
			}else{
				return false;
			}
		}catch(Exception e){
			log.error(e.getMessage());
			return false;
		}
	}
	
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean decryptPriceBidDoc(List<PriceBidDto> pbList) throws IOException{
		if(!CommonUtil.isCollectionEmpty(pbList)){
			for(PriceBidDto pb:pbList){
				try{
					decryptPriceBidDoc(pb);
				}catch(Exception e){
					log.error(e.getMessage());
				}
			}
			return true;
		}else{
			return false;
		}
		
	}

	@Override
	public List<GraphDto> setGraphDtoDetails(List<PriceBidDto> priceHistoryList) {
		List<GraphDto> graphDtoDetails=new ArrayList<>();
		for(PriceBidDto pb:priceHistoryList){
			GraphDto graphDto = new GraphDto();
					graphDto.setLabel(pb.getItemBid().getBidder().getTahdr().getTahdrCode());
					graphDto.setY(Long.parseLong(pb.getFddAmountWithGST()));
					graphDtoDetails.add(graphDto);
			/*graphDto.setLabel(pb.getCreated().toString());
			graphDto.setY(Long.parseLong(pb.getFddAmountWithGST()));
			graphDto.setX(pb.getCreated().getTime());
			graphDto.setName(pb.getPartner().getName());
			graphDtoDetails.add(graphDto);*/
		}
		
		return graphDtoDetails;
	}
	
	public List<BidStatisticDto> setBidStatisticGraphDtoDetails(List<PriceBidDto> priceHistoryList) {
		List<BidStatisticDto> graphDtoDetails=new ArrayList<>();
		for(PriceBidDto pb:priceHistoryList){
			BidStatisticDto graphDto = new BidStatisticDto();
			graphDto.setX(pb.getCreated().getTime());
			graphDto.setY(Long.parseLong(pb.getFddAmountWithGST()));
			/*graphDto.setY(Long.parseLong(pb.getFddAmountWithGST()));
			graphDto.setX(pb.getCreated().getTime());*/
			graphDto.setName(pb.getPartner().getName());
			graphDtoDetails.add(graphDto);
		}
		
		return graphDtoDetails;
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public long updateStatusForPriceBid(String bidSection,Long bidderId,Long itemBidId){
		long result = 0; 
		try{
		if(bidSection.equalsIgnoreCase(AppBaseConstant.PRICE_BID) && bidderId!=null && itemBidId!=null){
		updateStatusByItemBidId(AppBaseConstant.DOCUMENT_STATUS_DRAFTED, itemBidId, null);
		itemBidService.updateStatus(AppBaseConstant.DOCUMENT_STATUS_DRAFTED, itemBidId);
		commercialBidService.updateStatusByBidderId(AppBaseConstant.DOCUMENT_STATUS_DRAFTED, bidderId, null);
		bidderService.updateStatus(AppBaseConstant.DOCUMENT_STATUS_DRAFTED, bidderId);
		result = 1;
		}
		}
		catch (Exception e) {
			log.error("EXCEPTION", e);
		}
		
		return result;
	}

	@Override
	public List<Object> getDecendingQuickBidderStatistic(Long tahdrId, Long tahdrMaterilId) {
		List<Object> list=priceBidDao.getDecendingQuickBidderStatistic(tahdrId, tahdrMaterilId);
		if(!CommonUtil.isCollectionEmpty(list)){
			for(Object o:list){
				o.toString();
				/*BidStatisticDto bisStats=new BidStatisticDto();
				bisStats.setX(Long.parseLong(String.valueOf(o)));
				bisStats.setY(Long.parseLong(String.valueOf(o)));
				bisStats.setName(String.valueOf(o));*/
				
			}
			
		}
		return list;
	}

	@Override
	public List<Object> getAscendingQuickBidderStatistic(Long tahdrId, Long tahdrMaterilId) {
		return priceBidDao.getAscendingQuickBidderStatistic(tahdrId, tahdrMaterilId);
	}
	

	@Override
	public List<PriceBidDto> getBidListBySessionId(String sessionString) {
		List<PriceBidDto> priceBidList=null;
		if(sessionString!=null){
			priceBidList=findDtos("getBidListBySessionString", AbstractContextServiceImpl.getParamMap("sessionString", sessionString)) ;
		}
		return priceBidList;
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public Object getCumulativeSheetData(Long tahdrId,boolean opened){
		List<Object> object=null;
		if(opened){
			object=priceBidDao.getCumulativeSheetOpenedBidderByTahdrId(tahdrId);
		}else{
			object=priceBidDao.getCumulativeSheetByTahdrId(tahdrId);
		}
		
		return object;
		
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public QuotationDto saveQuaotation(QuotationDto quotation){
		List<PriceBidDto> newQuotationList = new ArrayList<>();
		for(PriceBidDto pb: quotation.getQuotations()){
			if(null == pb.getPriceBidId()){
				
				pb = super.save(pb);
			}else{
				pb = super.updateDto(pb);
			}
			newQuotationList.add(pb);
		
		}			
		
		quotation.setBidder(bidderService.updateBidderTotalBid(quotation.getBidder()));
		quotation.setQuotations(newQuotationList);
		if(null!=quotation.getBidderAttachment()){
		quotation.setBidderAttachment(bidderAttachmentService.save(quotation.getBidderAttachment(),quotation.getBidder()));
		}
		return quotation;
	}
	
	
	public CustomResponseDto updateDeliverDate(Long itemBidId,Date deliverdate,Errors errors){
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("itemBidId", itemBidId);
		 
		 List<ItemBidDto> itemBiddto = itemBidService.findDtos("getprLineByitemId", params);
		 
		
		long result=0;
		try {
		if(deliverdate.before(new Date())) {
			
			validationUtil.reject(errors, "Empty Delivery Date ", "Delivery Date Must be today's or later for Material:"+itemBiddto.get(0).getPrLine().getMaterialCode());
			//return new CustomResponseDto(false, "Delivery Date Must be today's or later for Material:"+itemBiddto.get(0).getPrLine().getMaterialCode());
		}
		}catch(Exception e) {
			
		}
		return null;
		
//		HashMap<String, Object> prLineMap=new HashMap<>();
//		prLineMap.put("deliverDate",deliverdate);
//		result=prLineService.updateByJpql(prLineMap, AbstractContextServiceImpl.getParamMap("prLineId", itemBiddto.get(0).getPrLine().getPrLineId()));
		//return true;
		
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public PriceBidDto updateDto(PriceBidDto pb){
		
			if(null == pb.getPriceBidId()){
				
				pb = super.save(pb);
			}else{
				pb = super.updateDto(pb);
			}
		
			
		
		return pb;
	}
	
	
	

	/*@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public List<PriceBidDto> submitQuotation(List<PriceBidDto> quotations,Long bidderId) {
		Map<String, Object> whereCls = AbstractContextServiceImpl.getParamMap("bidderId", bidderId);
		Map<String, Object> params = AbstractContextServiceImpl.getParamMap("status", AppBaseConstant.BIDDER_STATUS_BID_SUBMITED);
		int result = bidderService.updateByJpql(params, whereCls);
		if(result>0){
			return saveQuaotation(quotations);
		}else{
			throw new RuntimeException("Error while updating Enquiry status");
		}		
	}
	*/
	
}
