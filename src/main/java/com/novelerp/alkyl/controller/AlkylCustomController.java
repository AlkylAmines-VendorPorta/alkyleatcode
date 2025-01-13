package com.novelerp.alkyl.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.novelerp.alkyl.component.ASNComponent;
import com.novelerp.alkyl.dto.ASNCustomDto;
import com.novelerp.alkyl.dto.AdvanceShipmentNoticeDto;
import com.novelerp.alkyl.dto.PurchaseOrderDto;
import com.novelerp.alkyl.service.AdvanceShipmentNoticeService;
import com.novelerp.alkyl.service.PurchaseOrderService;
import com.novelerp.appbase.master.dto.AsnReminderDto;
import com.novelerp.appbase.master.dto.AttachmentDto;
import com.novelerp.appbase.master.entity.AsnReminder;
import com.novelerp.appbase.master.service.AsnReminderService;
import com.novelerp.appbase.master.service.ReferenceListService;
import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.appcontext.dao.BPartnerDao;
import com.novelerp.appcontext.dto.BPartnerDto;
import com.novelerp.appcontext.dto.UserDto;
import com.novelerp.appcontext.entity.Bpartner;
import com.novelerp.appcontext.entity.User;
import com.novelerp.appcontext.service.BPartnerService;
import com.novelerp.appcontext.service.UserService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.core.dto.CustomResponseDto;
import com.novelerp.core.util.CoreReferenceConstants;

@Controller
@RequestMapping("/rest")
public class AlkylCustomController {

	@Autowired
	private PurchaseOrderService poService;

	@Autowired
	private ASNComponent asnComponent;

	@Autowired
	private ReferenceListService refListService;

	@Autowired
	private AdvanceShipmentNoticeService asnService;
	@Autowired
	private UserService userService;
	@Autowired
	private BPartnerService bPartnerService;
	@Autowired
	private AsnReminderService asnReminderService;

	@PostMapping(value = "/sendASNCreationReminderMail")
	public @ResponseBody CustomResponseDto asnGateIn(@RequestBody ASNCustomDto asnForm) {
		try{
			if(asnForm.getPono()==null || asnForm.getInvoiceno()==null){
				CustomResponseDto resp = new CustomResponseDto(false, "All fields required");
				return resp;
			}
					
		Map<String, Object> params = new HashMap<String, Object>();
		AsnReminderDto attachment = new AsnReminderDto();
		attachment.setPoNo(asnForm.getPono());
		attachment.setInvoiceNo(asnForm.getInvoiceno());
		attachment.setInvoiceDate(asnForm.getInvoicedate());
		params.put("poNumber", asnForm.getPono());
		PurchaseOrderDto dto=poService.findDto("getPOByPONO", params);
		UserDto vendor = userService.findDto("getUserBYUserName",AbstractContextServiceImpl.getParamMap("username", dto.getVendorCode()));
		attachment.setVendor(vendor);
		//attachment.setCCMail(dto);
		
	//	String RequestedBy=dto.getReqby().getEmail();
	
	//	asnComponent.sendASNCreationReminderMail(asnForm.getPono(), asnForm.getInvoiceno(), asnForm.getInvoicedate(),vendor.getEmail(),RequestedBy,dto.getPoemail());
		
		asnComponent.sendASNCreationReminderMail(asnForm.getPono(), asnForm.getInvoiceno(), asnForm.getInvoicedate(),vendor.getEmail(),dto.getPoemail());
		attachment.setVendorEmail(vendor.getEmail());
		
		asnReminderService.save(attachment);
		}catch (Exception e) {
			CustomResponseDto resp = new CustomResponseDto(false, "Request Cannot Be Sent");
			return resp;
		}
		CustomResponseDto resp = new CustomResponseDto(true, "Request Send Successfully");
				return resp;
		
	}
	
	@PostMapping(value = "/sendASNCreationReminderForGateEntry/{pono}")
	public @ResponseBody CustomResponseDto sendASNCreationReminderForGateEntry(@PathVariable("pono") String pono) {
		
	try {
		//Map<String, Object> params = new HashMap<String, Object>();
		AsnReminderDto attachment = new AsnReminderDto();
		
		PurchaseOrderDto dto=poService.findDto("getPOByPONO", AbstractContextServiceImpl.getParamMap("poNumber", pono));
		UserDto vendor = userService.findDto("getUserBYUserName",AbstractContextServiceImpl.getParamMap("username", dto.getVendorCode()));
		attachment.setVendor(vendor);
		
		attachment.setPoNo(pono);
	  //  String RequestedBy=dto.getReqby().getEmail();
		//asnComponent.sendASNCreationReminderMail(pono, attachment.getInvoiceNo(), attachment.getInvoiceDate(),vendor.getEmail(),RequestedBy,dto.getPoemail());
		asnComponent.sendASNCreationReminderMail(pono, attachment.getInvoiceNo(), attachment.getInvoiceDate(),vendor.getEmail(),dto.getPoemail());
		
		attachment.setVendorEmail(vendor.getEmail());
		asnReminderService.save(attachment);
	}catch (Exception e) {
		CustomResponseDto resp = new CustomResponseDto(false, "Request Cannot Be Sent");
		return resp;
	}
	CustomResponseDto resp = new CustomResponseDto(true, "Request Send Successfully");
			return resp;
	
}

	@PostMapping(value = "/getASNStatus/{asnId}")
	public @ResponseBody CustomResponseDto getASNStatus(@PathVariable("asnId") Long asnId) {
		if (asnId == null) {
			CustomResponseDto resp = new CustomResponseDto(false, "ASN no cannot be null");
			return resp;
		}
		AdvanceShipmentNoticeDto advanceShipmentNoticeDto = asnService.findDto("getAsnByASNNo",
				AbstractContextServiceImpl.getParamMap("asnId", (int) (long) asnId));
		if (advanceShipmentNoticeDto == null) {
			CustomResponseDto resp = new CustomResponseDto(false, "ASN Not Found");
			return resp;
		}
		Map<String, String> asnStatusList = refListService.getReferenceListMap(CoreReferenceConstants.ASN_STATUS);
		CustomResponseDto resp = new CustomResponseDto();
		resp.addObject("asn", advanceShipmentNoticeDto);
		resp.addObject("status", asnStatusList);
		return resp;
	}

	@PostMapping(value = "/getSSNStatus/{asnId}")
	public @ResponseBody CustomResponseDto getSSNStatus(@PathVariable("asnId") String asnId) {
		if (asnId == null) {
			CustomResponseDto resp = new CustomResponseDto(false, "ASN no cannot be null");
			return resp;
		}
		AdvanceShipmentNoticeDto advanceShipmentNoticeDto = asnService.findDto("getSSNBySSNNo",
				AbstractContextServiceImpl.getParamMap("asnId", asnId));
		if (advanceShipmentNoticeDto == null) {
			CustomResponseDto resp = new CustomResponseDto(false, "SSN Not Found");
			return resp;
		}
		Map<String, String> serviceSheetStatusList = refListService
				.getReferenceListMap(CoreReferenceConstants.SERVICE_SHEET_STATUS);
		CustomResponseDto resp = new CustomResponseDto();
		resp.addObject("asn", advanceShipmentNoticeDto);
		resp.addObject("status", serviceSheetStatusList);
		return resp;
	}

	@PostMapping(value = "/updateASNStatus/{asnId}/{status}")
	public @ResponseBody CustomResponseDto cancelASN(@PathVariable("asnId") Long asnId,
			@PathVariable("status") String status) {
		int result = asnService.updateByJpql(AbstractContextServiceImpl.getParamMap("status", status),
				AbstractContextServiceImpl.getParamMap("advanceShipmentNoticeId", asnId));
		if (result > 0) {
			CustomResponseDto resp = new CustomResponseDto(true, "Status Updated");
			return resp;
		}
		CustomResponseDto resp = new CustomResponseDto(false, "Status Updation Failed");
		return resp;
	}

	@PostMapping(value = "/getPODetails/{pono}")
	public @ResponseBody CustomResponseDto getPODetails(@PathVariable("pono") String pono) {
		if (pono == null) {
			CustomResponseDto resp = new CustomResponseDto(false, "PO no cannot be null");
			return resp;
		}
		PurchaseOrderDto purchaseOrderDto = poService.findDto("getPObyPONumber",
				AbstractContextServiceImpl.getParamMap("poNumber", pono));
		if (purchaseOrderDto == null) {
			CustomResponseDto resp = new CustomResponseDto(false, "PO Not Found");
			return resp;
		}
		Map<String, String> purchaseOrderStatusList = refListService
				.getReferenceListMap(CoreReferenceConstants.PO_STATUS);
		CustomResponseDto resp = new CustomResponseDto();
		resp.addObject("po", purchaseOrderDto);
		resp.addObject("status", purchaseOrderStatusList);
		return resp;
	}

	@PostMapping(value = "/updatePODetails")
	public @ResponseBody CustomResponseDto updatePODetails(@RequestBody PurchaseOrderDto dto) {
		if (dto.getStatus() == null || dto.getVendorCode() == null) {
			CustomResponseDto resp = new CustomResponseDto(false, "Status Or Requested By cannot be null");
			return resp;
		}
		UserDto requestedBy = userService.findDto("getUserBYUserName",
				AbstractContextServiceImpl.getParamMap("username", dto.getVendorCode()));
		if (requestedBy == null) {
			CustomResponseDto resp = new CustomResponseDto(false,
					"Employee with code " + dto.getVendorCode() + " not found");
			return resp;
		}
		User user = new User();
		user.setUserId(requestedBy.getUserId());
		Map<String, Object> param = AbstractContextServiceImpl.getParamMap("status", dto.getStatus());
		param.put("status", dto.getStatus());
		param.put("reqby", user);
		int result = poService.updateByJpql(param,
				AbstractContextServiceImpl.getParamMap("purchaseOrderId", dto.getPurchaseOrderId()));
		if (result > 0) {
			CustomResponseDto resp = new CustomResponseDto(true, "PO Updated");
			return resp;
		}
		CustomResponseDto resp = new CustomResponseDto(false, "PO Updation Failed");
		return resp;
	}

	@PostMapping(value = "/getVendorDetails/{email}")
	public @ResponseBody CustomResponseDto getVendorDetails(@PathVariable("email") String email) {
		if (email == null) {
			CustomResponseDto resp = new CustomResponseDto(false, "email cannot be null");
			return resp;
		}
		UserDto user = userService.findDto("getUserbyEmail", AbstractContextServiceImpl.getParamMap("username", email));
		if (user == null) {
			CustomResponseDto resp = new CustomResponseDto(false, "Vendor Not Found");
			return resp;
		}
		Map<String, String> vendorStatusList = refListService.getReferenceListMap(AppBaseConstant.DOCUMENT_STATUS);
		CustomResponseDto resp = new CustomResponseDto();
		resp.addObject("partner", user);
		resp.addObject("status", vendorStatusList);
		return resp;
	}

	@PostMapping(value = "/updateVendorStatus")
	public @ResponseBody CustomResponseDto updateVendorStatus(@RequestBody BPartnerDto dto) {
		if (dto.getStatus() == null || dto.getbPartnerId() == null) {
			CustomResponseDto resp = new CustomResponseDto(false, "Status or PartnerID cannot be null");
			return resp;
		}
		Map<String, Object> param = AbstractContextServiceImpl.getParamMap("status", dto.getStatus());
		int result = bPartnerService.updateByJpql(param,
				AbstractContextServiceImpl.getParamMap("bPartnerId", dto.getbPartnerId()));
		if (result > 0) {
			CustomResponseDto resp = new CustomResponseDto(true, "Partner Updated");
			return resp;
		}
		CustomResponseDto resp = new CustomResponseDto(false, "Partner Updation Failed");
		return resp;
	}
}
