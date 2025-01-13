package com.novelerp.appbase.master.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.novelerp.alkyl.component.UserComponent;
import com.novelerp.alkyl.dao.VendorEnquiryDao;
import com.novelerp.alkyl.dto.EnquiryDto;
import com.novelerp.alkyl.dto.PRDto;
import com.novelerp.alkyl.service.VendorEnquiryService;
import com.novelerp.appbase.master.service.EnquiryService;
import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.core.dto.CustomResponseDto;
import com.novelerp.core.dto.MailDto;
import com.novelerp.core.service.MailService;
import com.novelerp.core.service.impl.AbstractServiceImpl;
import com.novelerp.eat.dao.BidderDao;
import com.novelerp.eat.dao.ItemBidDao;
import com.novelerp.eat.dto.BidderDto;
import com.novelerp.eat.dto.ItemBidDto;
import com.novelerp.eat.entity.Bidder;
import com.novelerp.eat.service.BidderService;
import com.novelerp.eat.service.ItemBidService;

@Service
public class EnquiryServiceImpl extends AbstractServiceImpl<Bidder, BidderDto> implements EnquiryService {
	
	@Autowired
	private BidderDao bidderDao;
	
	@Autowired
	private ItemBidService itemBidService;
	
	@Autowired
	private MailService mailService;
	
	@Autowired
	private UserComponent userComponent;
	
	@Autowired
	private VendorEnquiryService vendorEnqService;
	
	@Autowired
	private VendorEnquiryDao vendorEnquiryDao;
	
	@Autowired
	private BidderService bidderService;
	
	@Autowired
	private ItemBidDao itembidDao;
	
	@PostConstruct
	private void init(){
		super.init(EnquiryServiceImpl.class, bidderDao, Bidder.class, BidderDto.class);
		setByPassProxy(true);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public BidderDto createEnquiry(BidderDto enquiry) {
		/*BPartnerDto partner = new BPartnerDto();
		partner.setbPartnerId(1l);*/
		
		List<ItemBidDto> enquiryLines = enquiry.getItemBids();
		
		enquiry.setItemBids(null);
		enquiry.setItemBidList(null);
		
		/*enquiry.setPartner(partner);*/
		
		enquiry.setStatus(AppBaseConstant.BIDDER_STATUS_DRAFTED);
		enquiry=super.save(enquiry);
		
		enquiryLines = itemBidService.saveEnquiryLines(enquiryLines,enquiry,enquiry.getPartner());
		enquiry.setItemBids(enquiryLines);
		return enquiry;
	}
	
//	@Override
//	@Transactional(propagation=Propagation.REQUIRED)
//	public void createEnquiries(EnquiryDto enquiries) {
//		
//		List<BidderDto> bidders = enquiries.getBidderList();
//		List<ItemBidDto> itemBids = enquiries.getItemBidList();
//		enquiries.setBidderList(null);
//		enquiries.setItemBidList(null);
//		enquiries.setCode(" Enq open");
//		enquiries.setEnqNo(vendorEnquiryDao.getNewEnqNo());
//		enquiries=vendorEnqService.save(enquiries);
//		bidders=setEnquiryToBidder(bidders, enquiries);
//		for(BidderDto enquiry : bidders){
//			enquiry.setItemBids(itemBids);
//			createEnquiry(enquiry);
//			
////			if("Y".equals(enquiry.getIsMailSent())){
////			sendMail(enquiry);
////			}
//		}
//
//	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public CustomResponseDto createEnquiries(EnquiryDto enquiries) {
		
		CustomResponseDto dto=new CustomResponseDto();
		List<BidderDto> bidders = enquiries.getBidderList();
		List<ItemBidDto> itemBids = enquiries.getItemBidList();
		String doctype="";
		
		ArrayList<String> docList = new ArrayList<String>();
		
		for(ItemBidDto item:itemBids) {
			 
			if(item!=null) {
				
		          doctype=item.getPrLine().getPr().getDocType();
		          
		          if(!docList.contains(doctype)) {
		        	  docList.add(doctype);
				}
			}
			
		}
		
		if(docList.size()<=1) {
			enquiries.setBidderList(null);
			enquiries.setItemBidList(null);
			enquiries.setCode(" Enq open");
			enquiries.setEnqNo(vendorEnquiryDao.getNewEnqNo(doctype));

		enquiries=vendorEnqService.save(enquiries);
		bidders=setEnquiryToBidder(bidders, enquiries);
		for(BidderDto enquiry : bidders){
			enquiry.setItemBids(itemBids);
			createEnquiry(enquiry);
		}
		
		dto=new CustomResponseDto(true,"Enquiry Created Successfully");
		}
		else {
			dto= new CustomResponseDto(false,"Please Select Single Doctype");
		}
		return dto;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	public void sendMail(BidderDto enquiry) {
		// TODO Auto-generated method stub
		try {
			
			Map<String, Object> params= new HashMap<>();
			params.put("enquiryId",enquiry.getEnquiry().getEnquiryId());
			// List<ItemBidDto> itemList=itemBidService.findDtos("getItemBidDetailsByenqid", params);
			
			//List<ItemBidDto> itemList=itemBidService.findDtos("getItemBidDetailsByenqidnew", params);
			List<ItemBidDto> itemPRList = itembidDao.getprNumber(enquiry.getEnquiry().getEnquiryId());
			List<ItemBidDto> itemPlantList = itembidDao.getprPlant(enquiry.getEnquiry().getEnquiryId());
//	        List<BidderDto> itemList=bidderService.findDtos("getBidByenqId", params);
	      
		  String PRNO = itemPRList.toString().replace("[", "").replace("]", "");
		  String PlantName=itemPlantList.toString().replace("[", "").replace("]", "");
		//  String PLANT=itemPlantList.toString().replace("[", "").replace("]", "");
		  
//		  String PlantName="";
//		  if(PLANT.equals("1203")){
//			  PlantName="Dahej Plant";
//		  		}
//		  else if(PLANT.equals("1202")){
//			  PlantName="Kurkumbh Plant";
//		  }
//		  else if(PLANT.equals("1201")) {
//			  PlantName="Patalganga Plant";
//		  }
//		  else if(PLANT.equals("1102")) {
//			  PlantName="Vashi- HO Plant";
//		  }
//		  else
//		  {
//			  PlantName=PLANT;
//		  }
			 
			MailDto mailDto = new MailDto();
			
//			mailDto.setSubject("New Enquiry!");
			mailDto.setSubject("New Enquiry"+"-"+PRNO+"-"+PlantName);
//			mailDto.setMailContent("<p>Hi</p><br><p>New Enquires have been found. Please Login to Explore.");
//			mailDto.setMailContent("<p>Hi</p><br><p>New Enquires have been found.<br><br>Enquiry No: "+enquiry.getEnquiry().getEnquiryId()+"<br><br> Please Login on below link to Explore."+"<br><br> https://portal.alkylamines.com/ ");
			mailDto.setMailContent("<p>Hi</p><br><p>New Enquires have been found.<br><br>Enquiry No: "+enquiry.getEnquiry().getEnquiryId()+"<br><br> Please Login on below link to Explore."+"<br><br><a href=https://portal.alkylamines.com/> https://portal.alkylamines.com/</a><br/><br/><br/>  Regards<br/> Team Alkyl<br/> </body> </html>");
			List<String> recipientList = new ArrayList<>();
			//recipientList.add("ggayatri@yopmail.com");
			recipientList.add(userComponent.getEmailByBpartnerId(enquiry.getPartner().getbPartnerId()));
			mailDto.setRecipientList(recipientList);
			List<String> CcList = new ArrayList<>();
			CcList.add(enquiry.getEnquiry().getCreatedBy().getEmail());
		//	CcList.add(ib.getBidder().getEnquiry().getCreatedBy().getEmail());
			mailDto.setCcList(CcList);
			//mailDto.getRecipientList().add(userComponent.getEmailByBpartnerId(enquiry.getPartner().getbPartnerId()));
			mailService.sendEmail(mailDto,true);
			
		} catch (Exception e) {
			log.info("ERROR",e.getMessage());
		}
			
	}
	public List<BidderDto> setEnquiryToBidder(List<BidderDto> bidder,EnquiryDto dto){
		List<BidderDto> bidderList= new ArrayList<BidderDto>();
		for(BidderDto bidderdto:bidder){
			bidderdto.setEnquiry(dto);
			bidderList.add(bidderdto);
		}
		return bidderList;
		
	}

	@Override 
	@Transactional(propagation=Propagation.REQUIRED)
	public void updateEnquiries(EnquiryDto enquiries) {
		List<BidderDto> bidders = enquiries.getBidderList();
		List<ItemBidDto> itemBids = enquiries.getItemBidList();
		enquiries.setBidderList(null);
		enquiries.setItemBidList(null);
		EnquiryDto enq = new EnquiryDto();
		enq.setEnquiryId(enquiries.getEnquiryId());
		bidders=setEnquiryToBidder(bidders, enq);
		for(BidderDto enquiry : bidders){
			enquiry.setItemBids(itemBids);
			createEnquiry(enquiry);
			if("Y".equals(enquiry.getIsMailSent())){
			sendMail(enquiry);
			}
		}
		
	}
	
}
