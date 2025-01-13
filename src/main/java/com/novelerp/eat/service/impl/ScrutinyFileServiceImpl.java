package com.novelerp.eat.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.novelerp.appbase.master.dto.AttachmentDto;
import com.novelerp.appbase.master.service.AttachmentService;
import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.appcontext.dto.BPartnerDto;
import com.novelerp.appcontext.service.ContextService;
import com.novelerp.appcontext.service.SystemConfiguratorService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.commons.util.CommonUtil;
import com.novelerp.core.dto.CustomResponseDto;
import com.novelerp.eat.dao.ScrutinyFileDao;
import com.novelerp.eat.dto.BidderDto;
import com.novelerp.eat.dto.ItemBidDto;
import com.novelerp.eat.dto.ItemScrutinyDto;
import com.novelerp.eat.dto.ScrutinyFileDto;
import com.novelerp.eat.entity.ScrutinyFile;
import com.novelerp.eat.service.BidderService;
import com.novelerp.eat.service.BidderStatusService;
import com.novelerp.eat.service.ItemBidService;
import com.novelerp.eat.service.ItemScrutinyService;
import com.novelerp.eat.service.ScrutinyFileService;
import com.novelerp.eat.service.TechnicalBidService;

@Service
public class ScrutinyFileServiceImpl extends AbstractContextServiceImpl<ScrutinyFile, ScrutinyFileDto> implements ScrutinyFileService{

	protected Logger log; 
	
	@Autowired
	private ScrutinyFileDao scrutinyFileDao;
	
	@Autowired
	private BidderService bidderService;
	
	@Autowired
	private ItemBidService itemBidService;
	
	@Autowired
	private BidderStatusService bidderStatusService;
	
	@Autowired
	private ItemScrutinyService itemScrutinyService;
	
	@Autowired
	private TechnicalBidService technicalBidService;
	
	@Autowired
	 @Qualifier("jwtUserContext") private ContextService contextService;
	
	@Autowired
	private AttachmentService attachmentService;
	
	@Autowired
	private SystemConfiguratorService sysConfiguratorService;
	
	@PostConstruct
	private void init(){
		super.init(ScrutinyFileServiceImpl.class, scrutinyFileDao, ScrutinyFile.class, ScrutinyFileDto.class);
		setByPassProxy(true);
	}
	
	@Override
	public boolean isAuditingSubmitted(ScrutinyFileDto scrutinyFileDto,ItemScrutinyDto itemScrutinyDto){
		boolean result=false;
		if(scrutinyFileDto.getScrutinyType().equals("COMMSCR")){
			if(scrutinyFileDto.getScrutinyLevel().equals("PRELIMINARY")){
				result=itemScrutinyDto.getPreliminaryAuditorStatus()==null?false:itemScrutinyDto.getPreliminaryAuditorStatus().equals("APPROVED")?true:false;
			}else{
				result=itemScrutinyDto.getFinalAuditorStatus()==null?false:itemScrutinyDto.getFinalAuditorStatus().equals("APPROVED")?true:false;
			}
		}else if(scrutinyFileDto.getScrutinyType().equals("TECHSCR")){
			result=true;
		}
		return result;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public CustomResponseDto savePreliminaryScrutinyFile(ScrutinyFileDto scrutinyFile,ItemScrutinyDto itemScrutiny) {
		CustomResponseDto response=new CustomResponseDto();
		if(itemScrutiny!=null){
			if(itemScrutiny.getPreliminaryScrutinyStatus()!=null){
				ScrutinyFileDto newScrutinyFile=saveScrutinyFile(scrutinyFile,itemScrutiny.getItemScrutinyId());
		        if(newScrutinyFile.getResponse().isHasError()){
		        	response.addObject("result", false);	
		        	response.addObject("message", "Record Not Saved");	
				}else{
					/* Map<String,Object> propertyValueMap= new HashMap<>();
		        	 propertyValueMap.put("isScrutinySubmitted","Y");
		        	itemScrutinyService.updateByJpql(propertyValueMap, "scrutinyFileId", itemScrutiny.getItemScrutinyId());*/
					
						BidderDto bidder=scrutinyFile.getBidder();
						ItemBidDto itemBid=scrutinyFile.getItemBid();
						if(scrutinyFile.getScrutinyType().equals("TECHSCR")){
							if(itemScrutiny.getPreliminaryScrutinyStatus().toUpperCase().equals("REJECTED")){
								String status=AppBaseConstant.PRELIMINARY_TECHNICAL_FAILED;
								technicalBidService.updateTechnicalBidStatus(status,itemBid.getItemBidId());
								Long itemBidId=itemBid.getItemBidId();
								itemBidService.updateItemBidStatus(status,itemBidId);
								bidderService.updateBidderPreliminaryScrutinyStatus(status, bidder);
								bidderStatusService.saveBidderStatus(bidder, 
										status, itemScrutiny.getPreliminaryScrutinyComment());
								
							}
							else if(itemScrutiny.getPreliminaryScrutinyStatus().toUpperCase().equals("APPROVED")){
								String status=AppBaseConstant.PRELIMINARY_TECHNICAL_PASSED;
								technicalBidService.updateTechnicalBidStatus(status,itemBid.getItemBidId());
								itemBidService.updateItemBidScrutinyStatus(status,itemBid);
							    bidderService.updateBidderPreliminaryScrutinyStatus(status, bidder);
								bidderStatusService.saveBidderStatus(bidder, 
										status,itemScrutiny.getPreliminaryScrutinyComment());
							}
							else if(itemScrutiny.getPreliminaryScrutinyStatus().toUpperCase().equals("DEVIATION")){
								String status=AppBaseConstant.DEVIATION_CALLED;
								technicalBidService.updateTechnicalBidStatus(status,itemBid.getItemBidId());
								itemBidService.updateItemBidScrutinyStatus(status,itemBid);
								bidderService.updateBidderPreliminaryScrutinyStatus(status, bidder);
								bidderStatusService.saveBidderStatus(bidder, 
										status,itemScrutiny.getPreliminaryScrutinyComment());
							}
					}else{
							if(itemScrutiny.getPreliminaryScrutinyStatus().toUpperCase().equals("REJECTED")){
								String status=AppBaseConstant.PRELIMINARY_COMMERCIAL_FAILED;
								bidderService.updateStatus(status, bidder.getBidderId());
								bidderStatusService.saveBidderStatus(bidder, 
										status,itemScrutiny.getPreliminaryScrutinyComment());
								
							}
							else if(itemScrutiny.getPreliminaryScrutinyStatus().toUpperCase().equals("APPROVED")){
								String status=AppBaseConstant.PRELIMINARY_COMMERCIAL_PASSED;
								bidderService.updateBidderPreliminaryScrutinyStatus(status, bidder);
								bidderStatusService.saveBidderStatus(bidder, 
										status,itemScrutiny.getPreliminaryScrutinyComment());
								
							}
							else if(itemScrutiny.getPreliminaryScrutinyStatus().toUpperCase().equals("DEVIATION")){
								String status=AppBaseConstant.DEVIATION_CALLED;
								bidderService.updateBidderPreliminaryScrutinyStatus(status, bidder);
								bidderStatusService.saveBidderStatus(bidder, 
										status,itemScrutiny.getPreliminaryScrutinyComment());
							}
					}
						response.addObject("result", true);	
						response.addObject("newScrutinyFile", newScrutinyFile);
			        	response.addObject("message", "Record Saved Succesfully");	
				}	
			}else{
				response.addObject("result", false);	
	        	response.addObject("message", "Save Scrurity  Before Submiting scrutiny File");
			}
		}else{
			response.addObject("result", false);	
        	response.addObject("message", "Scrutiny line not found");
		}
        return response;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public CustomResponseDto saveFinalScrutinyFile(ScrutinyFileDto scrutinyFile,ItemScrutinyDto itemScrutiny) {
		CustomResponseDto response=new CustomResponseDto();
		
		if(itemScrutiny!=null){
			if(itemScrutiny.getFinalScrutinyStatus()!=null){
				ScrutinyFileDto newScrutinyFile=saveScrutinyFile(scrutinyFile,itemScrutiny.getItemScrutinyId());
				if(newScrutinyFile.getResponse().isHasError()){
		        	response.addObject("result", false);	
		        	response.addObject("message", "Record Not Saved");	
				}else{
					 /*Map<String,Object> propertyValueMap= new HashMap<>();
		        	 propertyValueMap.put("isFinalScrutinySubmitted","Y");
		        	 itemScrutinyService.updateByJpql(propertyValueMap, "scrutinyFileId", itemScrutiny.getItemScrutinyId());*/
						BidderDto bidder=scrutinyFile.getBidder();
						ItemBidDto itemBid=scrutinyFile.getItemBid();
						String status="";
						if(scrutinyFile.getScrutinyType().equals("TECHSCR")){
							if(itemScrutiny.getFinalScrutinyStatus().equalsIgnoreCase("APPROVED")){
								 status=AppBaseConstant.SCRUTINY_PASSED;
								itemBidService.updateItemBidScrutinyStatus(status,itemBid);
								bidderService.updateStatus( 
										AppBaseConstant.FINAL_TECHNICAL_PASSED,bidder.getBidderId());
								bidderStatusService.saveBidderStatus(bidder, 
								AppBaseConstant.FINAL_TECHNICAL_PASSED,itemScrutiny.getFinalScrutinyComment());
							}
							else if (itemScrutiny.getFinalScrutinyStatus().equalsIgnoreCase("REJECTED")){
								status=AppBaseConstant.SCRUTINY_FAILED;
								itemBidService.updateItemBidScrutinyStatus(status,itemBid);
								bidderService.updateStatus(AppBaseConstant.FINAL_TECHNICAL_FAILED,bidder.getBidderId());
								bidderStatusService.saveBidderStatus(bidder, 
								AppBaseConstant.FINAL_TECHNICAL_FAILED,itemScrutiny.getFinalScrutinyComment());
							}
						}else{
							if(itemScrutiny.getFinalScrutinyStatus().equalsIgnoreCase("APPROVED")){
								bidderService.updateStatus(AppBaseConstant.FINAL_COMMERCIAL_PASSED,bidder.getBidderId());
								bidderStatusService.saveBidderStatus(bidder, 
								AppBaseConstant.FINAL_COMMERCIAL_PASSED,itemScrutiny.getFinalScrutinyComment());
							}
							else if(itemScrutiny.getFinalScrutinyStatus().equalsIgnoreCase("REJECTED")){
								bidderService.updateStatus(AppBaseConstant.FINAL_COMMERCIAL_FAILED,bidder.getBidderId());
								bidderStatusService.saveBidderStatus(bidder, 
								AppBaseConstant.FINAL_COMMERCIAL_FAILED,itemScrutiny.getFinalScrutinyComment());
							}
						}
						
					response.addObject("result", true);	
					response.addObject("newScrutinyFile", newScrutinyFile);
		        	response.addObject("message", "Record Saved Succesfully");		
				}
			}else{
				response.addObject("result", false);	
	        	response.addObject("message", "Save Scrurity  Before Submiting scrutiny File");
			}
			}else{
				response.addObject("result", false);	
	        	response.addObject("message", "Scrutiny line not found");
			}
		return response;
	}

	@Transactional(propagation=Propagation.REQUIRED)
	private ScrutinyFileDto saveScrutinyFile(ScrutinyFileDto scrutinyFile,Long itemScrutinyId){
		scrutinyFile.setIsActive("Y");
		if(scrutinyFile.getScrutinyFileId()==null){
			scrutinyFile=save(scrutinyFile);
		}else{
			scrutinyFile=updateDto(scrutinyFile);	
		}	
		if(!scrutinyFile.getResponse().isHasError()){
			 Map<String,Object> propertyValueMap= new HashMap<>();
			if(scrutinyFile.getScrutinyLevel().equals("PRELIMINARY")){
				 propertyValueMap.put("isScrutinySubmitted","Y");
			}else{
				 propertyValueMap.put("isFinalScrutinySubmitted","Y");
			}
        	itemScrutinyService.updateByJpql(propertyValueMap, "itemScrutinyId", itemScrutinyId);
		}
		return scrutinyFile;
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean unhookPreviousScrutinyFile(Long itemScrtunityId,String scrutinyLevel,String scrutinyType){
		boolean result=false;
			int count=scrutinyFileDao.unhookPreviousScrutinyFile(itemScrtunityId,scrutinyLevel,scrutinyType);
			 if(count==1) result=true;
			return result	;
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public AttachmentDto addScrutinyFile(ScrutinyFileDto scrutinyFile, String fileName){
	/*	Map<String, Object> params =  new HashMap<>();
		params.put("bidderId", scrutinyFile.getBidder().getBidderId());
		if("TECHSCR".equalsIgnoreCase(scrutinyFile.getScrutinyType())){
			params.put("itemBidId", scrutinyFile.getItemBid().getItemBidId());
		}
		params.put("scrutinyType", scrutinyFile.getScrutinyType());
		params.put("scrutinyLevel",scrutinyFile.getScrutinyLevel());*/
		BPartnerDto partner=contextService.getPartner();
		String filePath  =  sysConfiguratorService.getAppDocDir();
		
		if(CommonUtil.isStringEmpty(filePath)
				|| CommonUtil.isStringEmpty(fileName)){
			return null;
		}
		//ScrutinyFileDto scrutinyFileDto=findDto(queryName, params);
		AttachmentDto attachment =new AttachmentDto();
		attachment.setFileExtension("pdf");
		attachment.setPath(filePath);
		attachment.setName(fileName);
		attachment.setFileName(fileName);
		attachment.setPartner(partner);
		attachment = attachmentService.save(attachment);
		return attachment;
	}
}
