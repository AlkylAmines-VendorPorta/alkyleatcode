package com.novelerp.alkyl.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.time.DateUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.HandlerMapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.novelerp.alkyl.component.ASNComponent;
import com.novelerp.alkyl.component.ASNLineComponent;
import com.novelerp.alkyl.component.GRNComponent;
import com.novelerp.alkyl.dto.ASNLineCostCenterDto;
import com.novelerp.alkyl.dto.ASNReadDto;
import com.novelerp.alkyl.dto.AdvanceShipmentNoticeDto;
import com.novelerp.alkyl.dto.AdvanceShipmentNoticeLineDto;
import com.novelerp.alkyl.dto.POReadDto;
import com.novelerp.alkyl.dto.PurchaseOrderDto;
import com.novelerp.alkyl.dto.PurchaseOrderLineDto;
import com.novelerp.alkyl.dto.SSNReadDto;
import com.novelerp.alkyl.dto.VehicleRegistrationDto;
import com.novelerp.alkyl.entity.AdvanceShipmentNoticeLine;
import com.novelerp.alkyl.service.AdvanceShipmentNoticeLineService;
import com.novelerp.alkyl.service.AdvanceShipmentNoticeService;
import com.novelerp.alkyl.service.PurchaseOrderService;
import com.novelerp.alkyl.validator.ASNValidator;
import com.novelerp.alkyl.validator.GRNValidator;
import com.novelerp.alkyl.validator.QCValidator;
import com.novelerp.appbase.master.dto.ReferenceListDto;
import com.novelerp.appbase.master.service.ReferenceListService;
import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.appcontext.dto.SystemConfiguratorDto;
import com.novelerp.appcontext.dto.UserDetailsDto;
import com.novelerp.appcontext.entity.User;
import com.novelerp.appcontext.service.ContextService;
import com.novelerp.appcontext.service.SystemConfiguratorService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.commons.util.CommonUtil;
import com.novelerp.core.dto.CustomResponseDto;
import com.novelerp.core.dto.Errors;
import com.novelerp.core.dto.ResponseDto;
import com.novelerp.core.util.CoreReferenceConstants;
import com.novelerp.eat.dto.MaterialGateInDto;
import com.sap.document.sap.rfc.grn.GrnFtpObject;
import com.sap.document.sap.rfc.grn.ZmmGrnPortalClient;
import com.sap.document.sap.rfc.ses.ZMMServiceSheet_Client;

@Controller
@RequestMapping("/rest")

public class ASNController {

	@Autowired
	private ReferenceListService refListService;

	@Autowired
	private PurchaseOrderService poService;

	@Autowired
	private AdvanceShipmentNoticeService asnService;

	@Autowired
	@Qualifier("jwtUserContext")
	private ContextService contextService;

	@Autowired
	private ASNComponent asnComponent;

	@Autowired
	private ASNLineComponent asnLineComponent;

	@Autowired
	private AdvanceShipmentNoticeLineService asnLineService;

	@Autowired
	private GRNComponent grnComponent;

	@Autowired
	private GRNValidator validator;

	@Autowired
	private ASNValidator asnvalidator;

	@Autowired
	private QCValidator qcvalidator;

	@Autowired
	private ZMMServiceSheet_Client zmmServiceSheetClient;

	// @Autowired
	// private SystemConfiguratorService sysConfigService;

	@PostMapping(value = "/getPOListByPOForASN")
	public @ResponseBody CustomResponseDto getPOListByPOForASN() {
		List<PurchaseOrderDto> poList = poService.findDtos("getPurchaseOrderByStatus", null);

		return new CustomResponseDto("purchaseOrderList", poList);
	}

	@PostMapping(value = "/getASNReport")
	public @ResponseBody CustomResponseDto getASNReport(@RequestBody ASNReadDto dto) {

		CustomResponseDto resp = new CustomResponseDto();
		List<AdvanceShipmentNoticeDto> asnreportlist = asnService.getASNReportbyFilter(dto);

		resp.addObject("AsnReportList", asnreportlist);

		return resp;
		// return new CustomResponseDto("AsnReportList", asnreport);
	}

	@PostMapping(value = "/getSSNReport")
	public @ResponseBody CustomResponseDto getSSNReport(@RequestBody SSNReadDto dto) {

		CustomResponseDto resp = new CustomResponseDto();
		List<AdvanceShipmentNoticeDto> ssnreportlist = asnService.getSSNReportbyFilter(dto);

		resp.addObject("ssnreportList", ssnreportlist);

		return resp;

	}

	@PostMapping(value = "/saveASN")
	public @ResponseBody CustomResponseDto saveASN(@RequestBody AdvanceShipmentNoticeDto asn) {
		asn = asnService.save(asn);
		return new CustomResponseDto("asnDetails", asn);
	}

	@PostMapping(value = "/reportGateEntry")
	public @ResponseBody CustomResponseDto saveGateEntry(@RequestBody AdvanceShipmentNoticeDto asn) {
		AdvanceShipmentNoticeDto Dto = asnService.findDto("getASNByASNId",
				AbstractContextServiceImpl.getParamMap("asnId", asn.getAdvanceShipmentNoticeId()));

		String pono = Dto.getPo().getPurchaseOrderNumber();
		ResponseDto resp = validatePO(pono, asn.getAdvanceShipmentNoticeId());
		if (resp.isHasError()) {
			asn.setResponse(resp);

			return new CustomResponseDto("asnDetails", asn);

		} else {

			asn = asnComponent.updateGateEntry(asn);
			try {
				asnComponent.sendMainOnGateEntry(asn);
			} catch (Exception e) {

			}
			return new CustomResponseDto("asnDetails", asn);
		}
		// return resp;
	}

	@PostMapping(value = "/getASN")
	public @ResponseBody CustomResponseDto getASN() {
		CustomResponseDto resp = null;
		Map<String, Object> params = new HashMap<String, Object>();
		List<AdvanceShipmentNoticeDto> asnList = new ArrayList<>();
		List<String> param = new ArrayList<>();
		if (contextService != null && contextService.getDefaultRole() != null
				&& !CommonUtil.isStringEmpty(contextService.getDefaultRole().getValue())) {
			String role = contextService.getDefaultRole().getValue();
			// String plant = contextService.getUserDetails().getPlant();
			// BPartnerDto partner = contextService.getPartner();
			if (role.equals(AppBaseConstant.ROLE_SECURITY_ADMIN)) {
				param.add(AppBaseConstant.ASN_STATUS_IN_TRANSIT);
				param.add(AppBaseConstant.ASN_STATUS_OHC_PASSED);
				param.add(AppBaseConstant.ASN_STATUS_105);
				param.add(AppBaseConstant.ASN_STATUS_GATE_IN);
				param.add(AppBaseConstant.ASN_STATUS_QC_PASSED);
				param.add(AppBaseConstant.ASN_STATUS_UNLOAD);
				param.add(AppBaseConstant.ASN_STATUS_REPORTED);
				param.add(AppBaseConstant.ASN_STATUS_103_Posted);
			} else if (role.equals(AppBaseConstant.ROLE_SAFETY_ADMIN)) {
				param.add(AppBaseConstant.ASN_STATUS_REPORTED);
				param.add(AppBaseConstant.ASN_STATUS_SAFETY_CHECK_FAILED);
				param.add(AppBaseConstant.ASN_STATUS_SAFETY_CHECK_HOLD);
			} else if (role.equals(AppBaseConstant.ROLE_OHC_ADMIN)) {
				param.add(AppBaseConstant.ASN_STATUS_SAFETY_CHECK_PASSED);
				param.add(AppBaseConstant.ASN_STATUS_OHC_FAILED);
				param.add(AppBaseConstant.ASN_STATUS_OHC_HOLD);
			} else if (role.equals(AppBaseConstant.ROLE_STORE_ADMIN)) {
				param.add(AppBaseConstant.ASN_STATUS_IN_TRANSIT);
				param.add(AppBaseConstant.ASN_STATUS_GATE_OUT);
				param.add(AppBaseConstant.ASN_STATUS_QC_PASSED);
				param.add(AppBaseConstant.ASN_STATUS_GATE_IN);
				param.add(AppBaseConstant.ASN_STATUS_UNLOAD);
				param.add(AppBaseConstant.ASN_STATUS_103_Posted);
				param.add(AppBaseConstant.ASN_STATUS_101);
			} else if (role.equals(AppBaseConstant.ROLE_QC_ADMIN)) {
				param.add(AppBaseConstant.ASN_STATUS_QC_PASSED);
				param.add(AppBaseConstant.ASN_STATUS_GATE_IN);
				param.add(AppBaseConstant.ASN_STATUS_UNLOAD);
				param.add(AppBaseConstant.ASN_STATUS_QC_FAILED);
			} else if (role.equals(AppBaseConstant.ROLE_PARTNER_ADMIN)) {
				param.add(AppBaseConstant.ASN_STATUS_IN_TRANSIT);
				// param.add(AppBaseConstant.ASN_STATUS_OHC_PASSED);
				param.add(AppBaseConstant.ASN_STATUS_105);
				param.add(AppBaseConstant.ASN_STATUS_REPORTED);
				// param.add(AppBaseConstant.ASN_STATUS_SAFETY_CHECK_FAILED);
				// param.add(AppBaseConstant.ASN_STATUS_SAFETY_CHECK_HOLD);
				// param.add(AppBaseConstant.ASN_STATUS_SAFETY_CHECK_PASSED);
				// param.add(AppBaseConstant.ASN_STATUS_OHC_FAILED);
				// param.add(AppBaseConstant.ASN_STATUS_OHC_HOLD);
				// param.add(AppBaseConstant.ASN_STATUS_QC_PASSED);
				param.add(AppBaseConstant.ASN_STATUS_GATE_IN);
				param.add(AppBaseConstant.ASN_STATUS_UNLOAD);
				// param.add(AppBaseConstant.ASN_STATUS_CLOSED);
				param.add(AppBaseConstant.ASN_STATUS_GATE_OUT);
				param.add(AppBaseConstant.ASN_STATUS_103_Posted);
				param.add(AppBaseConstant.ASN_STATUS_101);

				// param.add(AppBaseConstant.SERVICE_ENTRY_SHEET_STATUS_APPROVED);
			}
			params.put("status", param);
			// params.put("plant", plant);
			asnList = asnService.findDtos("getASNBYStatus", params);
			// AbstractContextServiceImpl.getParamMap("status", param));

			resp = new CustomResponseDto("asnList", asnList);
			resp.addObject("role", role);
			Map<String, ReferenceListDto> withHoldingTax = refListService
					.getRefListCollection(CoreReferenceConstants.WITH_HOLDING_TAX);
			resp.addObject("withHoldingTax", withHoldingTax);
			Map<String, String> glMaster = refListService.getReferenceListMap(CoreReferenceConstants.GL_MASTER);
			resp.addObject("glMaster", glMaster);
		}

		return resp;
	}

	@PostMapping(value = "/getASNListbyFilter")
	public @ResponseBody CustomResponseDto getASNListbyFilter(@RequestBody ASNReadDto dto) {

		CustomResponseDto resp = new CustomResponseDto();
		List<AdvanceShipmentNoticeDto> asnreportlist = asnService.getASNbyFilter(dto);

		resp.addObject("AsnReportList", asnreportlist);

		return resp;
		// return new CustomResponseDto("AsnReportList", asnreport);
	}

	@PostMapping(value = "/getASNwithoutPO")
	public @ResponseBody CustomResponseDto getASNwithoutPO() {
		CustomResponseDto resp = null;
		Map<String, Object> params = new HashMap<String, Object>();
		List<AdvanceShipmentNoticeDto> asnList = new ArrayList<>();
		List<String> param = new ArrayList<>();
		if (contextService != null && contextService.getDefaultRole() != null
				&& !CommonUtil.isStringEmpty(contextService.getDefaultRole().getValue())) {
			String role = contextService.getDefaultRole().getValue();
			String plant = contextService.getUserDetails().getPlant();
			// BPartnerDto partner = contextService.getPartner();
			if (role.equals(AppBaseConstant.ROLE_SECURITY_ADMIN)) {
				param.add(AppBaseConstant.ASN_STATUS_IN_TRANSIT);
				param.add(AppBaseConstant.ASN_STATUS_OHC_PASSED);
				param.add(AppBaseConstant.ASN_STATUS_105);
				param.add(AppBaseConstant.ASN_STATUS_QC_PASSED);
				param.add(AppBaseConstant.ASN_STATUS_UNLOAD);
				param.add(AppBaseConstant.ASN_STATUS_REPORTED);
				param.add(AppBaseConstant.ASN_STATUS_103_Posted);
			} else if (role.equals(AppBaseConstant.ROLE_SAFETY_ADMIN)) {
				param.add(AppBaseConstant.ASN_STATUS_REPORTED);
				param.add(AppBaseConstant.ASN_STATUS_SAFETY_CHECK_FAILED);
				param.add(AppBaseConstant.ASN_STATUS_SAFETY_CHECK_HOLD);
			} else if (role.equals(AppBaseConstant.ROLE_OHC_ADMIN)) {
				param.add(AppBaseConstant.ASN_STATUS_SAFETY_CHECK_PASSED);
				param.add(AppBaseConstant.ASN_STATUS_OHC_FAILED);
				param.add(AppBaseConstant.ASN_STATUS_OHC_HOLD);
			} else if (role.equals(AppBaseConstant.ROLE_STORE_ADMIN)) {
				param.add(AppBaseConstant.ASN_STATUS_IN_TRANSIT);
				param.add(AppBaseConstant.ASN_STATUS_GATE_OUT);
				param.add(AppBaseConstant.ASN_STATUS_QC_PASSED);
				param.add(AppBaseConstant.ASN_STATUS_GATE_IN);
				param.add(AppBaseConstant.ASN_STATUS_UNLOAD);
				param.add(AppBaseConstant.ASN_STATUS_103_Posted);
			} else if (role.equals(AppBaseConstant.ROLE_QC_ADMIN)) {
				param.add(AppBaseConstant.ASN_STATUS_QC_PASSED);
				param.add(AppBaseConstant.ASN_STATUS_GATE_IN);
				param.add(AppBaseConstant.ASN_STATUS_UNLOAD);
				param.add(AppBaseConstant.ASN_STATUS_QC_FAILED);
			} else if (role.equals(AppBaseConstant.ROLE_PARTNER_ADMIN)) {
				param.add(AppBaseConstant.ASN_STATUS_IN_TRANSIT);
				// param.add(AppBaseConstant.ASN_STATUS_OHC_PASSED);
				param.add(AppBaseConstant.ASN_STATUS_105);
				param.add(AppBaseConstant.ASN_STATUS_REPORTED);
				// param.add(AppBaseConstant.ASN_STATUS_SAFETY_CHECK_FAILED);
				// param.add(AppBaseConstant.ASN_STATUS_SAFETY_CHECK_HOLD);
				// param.add(AppBaseConstant.ASN_STATUS_SAFETY_CHECK_PASSED);
				// param.add(AppBaseConstant.ASN_STATUS_OHC_FAILED);
				// param.add(AppBaseConstant.ASN_STATUS_OHC_HOLD);
				// param.add(AppBaseConstant.ASN_STATUS_QC_PASSED);
				param.add(AppBaseConstant.ASN_STATUS_GATE_IN);
				param.add(AppBaseConstant.ASN_STATUS_UNLOAD);
				// param.add(AppBaseConstant.ASN_STATUS_CLOSED);
				param.add(AppBaseConstant.ASN_STATUS_GATE_OUT);
				param.add(AppBaseConstant.ASN_STATUS_103_Posted);

				// param.add(AppBaseConstant.SERVICE_ENTRY_SHEET_STATUS_APPROVED);
			}
			params.put("status", param);
			// params.put("plant", plant);
			asnList = asnService.findDtos("getASNwithoutpo", params);
			// AbstractContextServiceImpl.getParamMap("status", param));

			resp = new CustomResponseDto("asnList", asnList);
			resp.addObject("role", role);
			Map<String, ReferenceListDto> withHoldingTax = refListService
					.getRefListCollection(CoreReferenceConstants.WITH_HOLDING_TAX);
			resp.addObject("withHoldingTax", withHoldingTax);
			Map<String, String> glMaster = refListService.getReferenceListMap(CoreReferenceConstants.GL_MASTER);
			resp.addObject("glMaster", glMaster);
		}

		return resp;
	}

	@PostMapping(value = "/getASNByPO/{poId}")
	public @ResponseBody CustomResponseDto getASNForPO(@PathVariable("poId") Long poId) {
		CustomResponseDto resp = new CustomResponseDto();
		List<AdvanceShipmentNoticeDto> asnList = asnService.findDtos("getASNByPOId",
				AbstractContextServiceImpl.getParamMap("poId", poId));
		resp.addObject("asnList", asnList);
		return resp;
	}

	@RequestMapping(value = { "/getASNStatusList" }, method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody CustomResponseDto getASNStatusList() {
		CustomResponseDto resp = new CustomResponseDto();
		Map<String, String> asnStatusList = refListService.getReferenceListMap(CoreReferenceConstants.ASN_STATUS);
		Map<String, String> serviceSheetStatusList = refListService
				.getReferenceListMap(CoreReferenceConstants.SERVICE_SHEET_STATUS);
		Map<String, String> serviceEntrySheetStatusList = refListService
				.getReferenceListMap(CoreReferenceConstants.SERVICE_ENTRY_SHEET_STATUS);
		Map<String, String> vehicleRegistrationStatusList = refListService
				.getReferenceListMap(CoreReferenceConstants.VEHICLE_REGISTRATION_STATUS);
		resp.addObject("asnStatusList", asnStatusList);
		resp.addObject("serviceSheetStatusList", serviceSheetStatusList);
		resp.addObject("serviceEntrySheetStatusList", serviceEntrySheetStatusList);
		resp.addObject("vehicleRegistrationStatusList", vehicleRegistrationStatusList);
		return resp;
	}

	@PostMapping(value = "/safetyCheckPassed/{asnId}")
	public @ResponseBody CustomResponseDto safetyCheckPassed(@PathVariable("asnId") Long asnId) {
		Map<String, Object> param = AbstractContextServiceImpl.getParamMap("isSafetyPassed", "Y");
		param.put("safetyPassedDate", new Date());
		param.put("status", AppBaseConstant.ASN_STATUS_SAFETY_CHECK_PASSED);
		int result = asnService.updateByJpql(param,
				AbstractContextServiceImpl.getParamMap("advanceShipmentNoticeId", asnId));
		if (result > 0) {
			CustomResponseDto resp = new CustomResponseDto(true, "Safety Check Passed");
			resp.addObject("status", AppBaseConstant.ASN_STATUS_SAFETY_CHECK_PASSED);
			return resp;
		} else {
			return new CustomResponseDto(false, "Error While Updating Safety Check Status");
		}

	}

	/*
	 * @PostMapping(value="/markASNInTransit/{asnId}") public @ResponseBody
	 * CustomResponseDto markASNInTransit(@PathVariable("asnId") Long asnId){ try {
	 * if(asnLineService.submitASN(asnId)){ CustomResponseDto resp = new
	 * CustomResponseDto(true,"ASN Marked as In-Transit"); resp.addObject("status",
	 * AppBaseConstant.ASN_STATUS_IN_TRANSIT); return resp; } } catch (Exception e)
	 * { return new CustomResponseDto(false,e.getMessage()); } return new
	 * CustomResponseDto(false,"Error While marking ASN as In-Transit"); }
	 */

	@PostMapping(value = "/markASNInTransit")
	public @ResponseBody CustomResponseDto markASNInTransit(@RequestBody AdvanceShipmentNoticeDto asn) {
		// List<AdvanceShipmentNoticeLineDto> asnList = new ArrayList<>();

		/*
		 * for(AdvanceShipmentNoticeLineDto dto:asn.getAsnLineList()) {
		 * if(dto.getDeliveryQuantity()!=0) { asnList.add(dto); }
		 * 
		 * } if(asnList.size()>0) { asn.setAsnLineList(null);
		 * asn.setAsnLineList(asnList); }else { CustomResponseDto respN = new
		 * CustomResponseDto();
		 * 
		 * respN.setSuccess(false);
		 * respN.setMessage("ASN Cannot Create With Zero Quantity"); return respN; }
		 */

		Errors errors = new Errors();
		CustomResponseDto respN = new CustomResponseDto();
		asnvalidator.validateAsnDetails(asn, errors);
		if (errors.getErrorCount() > 0) {
			respN.setSuccess(false);
			respN.setMessage(errors.getErrorString());
			return respN;
		} else {

			try {
				asn = asnLineService.submitASN(asn);
				if (asn != null && asn.getResponse() != null && !asn.getResponse().isHasError()) {
					CustomResponseDto resp = new CustomResponseDto(true,
							"ASN Marked as In-Transit" + '\n' + " ASN No is :" + asn.getAdvanceShipmentNoticeNo());
					resp.addObject("status", AppBaseConstant.ASN_STATUS_IN_TRANSIT);
					try {
						asnComponent.sendMainOnAsnCreation(asn);
					} catch (Exception e) {
						e.printStackTrace();
					}
					return resp;
				}
			} catch (Exception e) {
				e.printStackTrace();
				return new CustomResponseDto(false, e.getMessage());
			}
			return new CustomResponseDto(false, "Error While marking ASN as In-Transit");

		}
	}

	@PostMapping(value = "/markASNInTransitSTO")
	public @ResponseBody CustomResponseDto markASNInTransitSTO(@RequestBody AdvanceShipmentNoticeDto asn) {
		// List<AdvanceShipmentNoticeLineDto> asnList = new ArrayList<>();

		/*
		 * for(AdvanceShipmentNoticeLineDto dto:asn.getAsnLineList()) {
		 * if(dto.getDeliveryQuantity()!=0) { asnList.add(dto); }
		 * 
		 * } if(asnList.size()>0) { asn.setAsnLineList(null);
		 * asn.setAsnLineList(asnList); }else { CustomResponseDto respN = new
		 * CustomResponseDto();
		 * 
		 * respN.setSuccess(false);
		 * respN.setMessage("ASN Cannot Create With Zero Quantity"); return respN; }
		 */

		// Errors errors = new Errors();
		// CustomResponseDto respN = new CustomResponseDto();
		// asnvalidator.validateAsnDetails(asn, errors);
		// if (errors.getErrorCount() > 0) {
		// respN.setSuccess(false);
		// respN.setMessage(errors.getErrorString());
		// return respN;
		// } else {

		try {
			asn = asnLineService.submitASN(asn);
			if (asn != null && asn.getResponse() != null && !asn.getResponse().isHasError()) {
				CustomResponseDto resp = new CustomResponseDto(true,
						"ASN Marked as In-Transit" + '\n' + " ASN No is :" + asn.getAdvanceShipmentNoticeNo());
				resp.addObject("status", AppBaseConstant.ASN_STATUS_IN_TRANSIT);
				try {
					asnComponent.sendMainOnAsnCreation(asn);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return resp;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new CustomResponseDto(false, e.getMessage());
		}
		return new CustomResponseDto(false, "Error While marking ASN as In-Transit");

		// }
	}

	@PostMapping(value = "/submitServiceSheet")
	public @ResponseBody CustomResponseDto submitServiceSheet(@RequestBody AdvanceShipmentNoticeDto asn) {
		try {

			CustomResponseDto resp = new CustomResponseDto();
			Errors errors = new Errors();

			validator.validateSSNDetails(asn, errors);
			if (errors.getErrorCount() > 0) {
				resp.setSuccess(false);
				resp.setMessage(errors.getErrorString());
				return resp;

			} else {
			
			asn.setStatus(AppBaseConstant.SERVICE_SHEET_STATUS_IN_PROGRESS);
			asn = asnLineService.submitServiceSheet(asn);
			if (asn != null && asn.getResponse() != null && !asn.getResponse().isHasError()) {
//				CustomResponseDto resp = new CustomResponseDto(true,
//						"Service Sheet is Under Approval" + '\n' + " Service Sheet No is :" + asn.getServiceSheetNo());
				resp.setSuccess(true);
				resp.setMessage("Service Sheet is Under Approval" + '\n' + " Service Sheet No is :" + asn.getServiceSheetNo());
				resp.addObject("status", AppBaseConstant.SERVICE_SHEET_STATUS_IN_PROGRESS);
				resp.addObject("asn", asn);
				try {
					asnComponent.sendMainOnSubmitServiceSheet(asn);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return resp;
			}
			}
			// }
		} catch (Exception e) {
			return new CustomResponseDto(false, e.getMessage());
		}
		// return new CustomResponseDto(false, "Error While Submit");
		return new CustomResponseDto(false, asn.getResponse().getMessage());
	}

	@PostMapping(value = "/updateServiceSheet")
	public @ResponseBody CustomResponseDto updateServiceSheet(@RequestBody AdvanceShipmentNoticeDto asn) {
		try {

			asn.setStatus(AppBaseConstant.SERVICE_SHEET_STATUS_IN_PROGRESS);
			asn = asnLineService.submitServiceSheet(asn);
			if (asn != null && asn.getResponse() != null && !asn.getResponse().isHasError()) {
				CustomResponseDto resp = new CustomResponseDto(true, "Service Sheet is Updated Sucessfully");
				resp.addObject("status", AppBaseConstant.SERVICE_SHEET_STATUS_IN_PROGRESS);
				resp.addObject("asn", asn);

				return resp;
			}

		} catch (Exception e) {
			return new CustomResponseDto(false, e.getMessage());
		}
		// return new CustomResponseDto(false, "Error While Submit");
		return new CustomResponseDto(false, asn.getResponse().getMessage());
	}

	@PostMapping(value = "/getApprovalPendingServiceSheet")
	public @ResponseBody CustomResponseDto getApprovalPendingServiceSheet() {

		CustomResponseDto resp = null;
		String role = contextService.getDefaultRole().getValue();
		String userName = contextService.getUser().getUserName();
		Map<String, String> serviceSheetStatusList = refListService
				.getReferenceListMap(CoreReferenceConstants.SERVICE_SHEET_STATUS);
		// List<AdvanceShipmentNoticeDto> dto=null;
		// if(userName.equals("2907")) {
		// dto=asnService.findDtos("getApprovalPendingServiceSheet", null);
		//
		// }
		// else {
		// dto=asnService.findDtos("getApprovalPendingServiceSheetbyUser",
		// AbstractContextServiceImpl.getParamMap("userName", userName));
		// }

		List<AdvanceShipmentNoticeDto> dto = asnService.findDtos("getApprovalPendingServiceSheet", null);

		resp = new CustomResponseDto("ApprovalPendingServiceSheetLIst", dto);
		resp.addObject("role", role);
		resp.addObject("user", contextService.getUser());
		resp.addObject("serviceSheetStatusList", serviceSheetStatusList);

		return resp;

	}

	@PostMapping(value = "/submitServiceEntry")
	public @ResponseBody CustomResponseDto submitServiceEntry(@RequestBody AdvanceShipmentNoticeDto asn) {
		try {
			asn.setStatus(AppBaseConstant.SERVICE_ENTRY_SHEET_STATUS_IN_PROGRESS);
			asn = asnLineService.submitServiceEntry(asn);
			try {
				asnComponent.sendMailOnProceedBillBooking(asn);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (asn != null && asn.getResponse() != null && !asn.getResponse().isHasError()) {
				CustomResponseDto resp = new CustomResponseDto(true, "Service Sheet is Under Approval");
				resp.addObject("status", AppBaseConstant.SERVICE_ENTRY_SHEET_STATUS_IN_PROGRESS);
				resp.addObject("asn", asn);
				return resp;
			}
		} catch (Exception e) {
			return new CustomResponseDto(false, e.getMessage());
		}
		return new CustomResponseDto(false, "Error While Submit");
	}

	@PostMapping(value = "/safetyCheckFailed/{asnId}/{remark}")
	public @ResponseBody CustomResponseDto safetyCheckFailed(@PathVariable("asnId") Long asnId,
			@PathVariable("remark") String remark) {
		Map<String, Object> param = AbstractContextServiceImpl.getParamMap("status",
				AppBaseConstant.ASN_STATUS_SAFETY_CHECK_FAILED);
		param.put("rejectReason", remark);
		int result = asnService.updateByJpql(param,
				AbstractContextServiceImpl.getParamMap("advanceShipmentNoticeId", asnId));

		if (result > 0) {
			CustomResponseDto resp = new CustomResponseDto(true, "Safety Check Failed");
			resp.addObject("status", AppBaseConstant.ASN_STATUS_SAFETY_CHECK_FAILED);
			return resp;
		} else {
			return new CustomResponseDto(false, "Error While Updating Safety Check Status");
		}

	}

	@PostMapping(value = "/safetyCheckHold/{asnId}/{remark}")
	public @ResponseBody CustomResponseDto safetyCheckHold(@PathVariable("asnId") Long asnId,
			@PathVariable("remark") String remark) {
		Map<String, Object> param = AbstractContextServiceImpl.getParamMap("status",
				AppBaseConstant.ASN_STATUS_SAFETY_CHECK_HOLD);
		param.put("rejectReason", remark);
		int result = asnService.updateByJpql(param
		/*
		 * AbstractContextServiceImpl.getParamMap("status",
		 * AppBaseConstant.ASN_STATUS_SAFETY_CHECK_HOLD)
		 */
				, AbstractContextServiceImpl.getParamMap("advanceShipmentNoticeId", asnId));
		if (result > 0) {
			CustomResponseDto resp = new CustomResponseDto(true, "Safety Check HOLD");
			resp.addObject("status", AppBaseConstant.ASN_STATUS_SAFETY_CHECK_HOLD);
			return resp;
		} else {
			return new CustomResponseDto(false, "Error While Updating Safety Check Status");
		}

	}

	@PostMapping(value = "/ohcPassed/{asnId}")
	public @ResponseBody CustomResponseDto ohcPassed(@PathVariable("asnId") Long asnId) {
		Map<String, Object> param = AbstractContextServiceImpl.getParamMap("isOHCPassed", "Y");
		param.put("ohcPassedDate", new Date());
		param.put("status", AppBaseConstant.ASN_STATUS_OHC_PASSED);
		int result = asnService.updateByJpql(param,
				AbstractContextServiceImpl.getParamMap("advanceShipmentNoticeId", asnId));
		if (result > 0) {
			CustomResponseDto resp = new CustomResponseDto(true, "OHC Passed");
			resp.addObject("status", AppBaseConstant.ASN_STATUS_OHC_PASSED);
			return resp;
		} else {
			return new CustomResponseDto(false, "Error While Updating OHC Status");
		}

	}

	@PostMapping(value = "/ohcHold/{asnId}/{remark}")
	public @ResponseBody CustomResponseDto ohcHold(@PathVariable("asnId") Long asnId,
			@PathVariable("remark") String remark) {
		Map<String, Object> param = AbstractContextServiceImpl.getParamMap("status",
				AppBaseConstant.ASN_STATUS_OHC_HOLD);
		param.put("rejectReason", remark);
		int result = asnService.updateByJpql(param
		/*
		 * AbstractContextServiceImpl.getParamMap("status",
		 * AppBaseConstant.ASN_STATUS_OHC_HOLD)
		 */
				, AbstractContextServiceImpl.getParamMap("advanceShipmentNoticeId", asnId));
		if (result > 0) {
			CustomResponseDto resp = new CustomResponseDto(true, "OHC on Hold");
			resp.addObject("status", AppBaseConstant.ASN_STATUS_OHC_HOLD);
			return resp;
		} else {
			return new CustomResponseDto(false, "Error While Updating OHC on Hold");
		}

	}

	@PostMapping(value = "/ohcFailed/{asnId}/{remark}")
	public @ResponseBody CustomResponseDto ohcFailed(@PathVariable("asnId") Long asnId,
			@PathVariable("remark") String remark) {

		Map<String, Object> param = AbstractContextServiceImpl.getParamMap("status",
				AppBaseConstant.ASN_STATUS_OHC_FAILED);
		param.put("rejectReason", remark);
		int result = asnService.updateByJpql(param,
				AbstractContextServiceImpl.getParamMap("advanceShipmentNoticeId", asnId));
		if (result > 0) {
			CustomResponseDto resp = new CustomResponseDto(true, "OHC Failed");
			resp.addObject("status", AppBaseConstant.ASN_STATUS_OHC_FAILED);
			return resp;
		} else {
			return new CustomResponseDto(false, "Error While Updating OHC Status");
		}

	}
	// @PostMapping(value="/qcPassed/{asnId}")
	// public @ResponseBody CustomResponseDto qcPassed(@PathVariable("asnId")
	// Long asnId){
	// int result =
	// asnService.updateByJpql(AbstractContextServiceImpl.getParamMap("status",
	// AppBaseConstant.ASN_STATUS_QC_PASSED)
	// , AbstractContextServiceImpl.getParamMap("advanceShipmentNoticeId",
	// asnId));
	// if(result>0){
	// CustomResponseDto resp = new CustomResponseDto(true,"QC Passed");
	// resp.addObject("status", AppBaseConstant.ASN_STATUS_QC_PASSED);
	// return resp;
	// }else{
	// return new CustomResponseDto(false,"Error While Updating QC Status");
	// }
	//
	// }

	@PostMapping(value = "/qcPassed")
	public @ResponseBody CustomResponseDto qcPassed(@RequestBody AdvanceShipmentNoticeDto asn) {

		Errors errors = new Errors();
		CustomResponseDto resp = new CustomResponseDto();
		qcvalidator.validateQCDetails(asn, errors);
		if (errors.getErrorCount() > 0) {
			resp.setSuccess(false);
			resp.setMessage(errors.getErrorString());
			return resp;

		} else {

			boolean qcStatus = asnLineComponent.qcPassASN(asn);
			if (qcStatus) {
				resp = new CustomResponseDto(true, "QC Pass Successful");
				resp.addObject("status", AppBaseConstant.ASN_STATUS_QC_PASSED);
				return resp;
			} else {
				return new CustomResponseDto(false, "Error While Updating QC Status");
			}
		}
	}

	@PostMapping(value = "/editASN/{asnId}")
	public @ResponseBody CustomResponseDto editASN(@PathVariable("asnId") Long asnId) {
		int result = asnService.updateByJpql(
				AbstractContextServiceImpl.getParamMap("status", AppBaseConstant.ASN_STATUS_DRAFTED),
				AbstractContextServiceImpl.getParamMap("advanceShipmentNoticeId", asnId));
		if (result > 0) {
			CustomResponseDto resp = new CustomResponseDto(true, "ASN changed to DRAFTED mode.");
			resp.addObject("status", AppBaseConstant.ASN_STATUS_DRAFTED);
			return resp;
		} else {
			return new CustomResponseDto(false, "Error While Updating EDIT Status");
		}

	}

	@PostMapping(value = "/cancelASN/{asnId}/{remark}")
	public @ResponseBody CustomResponseDto cancelASN(@PathVariable("asnId") Long asnId,
			@PathVariable("remark") String remark) {
		Map<String, Object> param = AbstractContextServiceImpl.getParamMap("status",
				AppBaseConstant.ASN_STATUS_CANCELED);
		param.put("cancelReason", remark);
		int result = asnService.updateByJpql(param,
				AbstractContextServiceImpl.getParamMap("advanceShipmentNoticeId", asnId));
		if (result > 0) {
			CustomResponseDto resp = new CustomResponseDto(true, "ASN Canceled.");
			resp.addObject("status", AppBaseConstant.ASN_STATUS_CANCELED);
			return resp;
		} else {
			return new CustomResponseDto(false, "Error While Canceling ASN");
		}

	}

	@PostMapping(value = "/cancelSSN/{asnId}/{remark}")
	public @ResponseBody CustomResponseDto cancelSSN(@PathVariable("asnId") Long asnId,
			@PathVariable("remark") String remark) {
		Map<String, Object> param = AbstractContextServiceImpl.getParamMap("status",
				AppBaseConstant.ASN_STATUS_CANCELED);
		param.put("cancelReason", remark);
		int result = asnService.updateByJpql(param,
				AbstractContextServiceImpl.getParamMap("advanceShipmentNoticeId", asnId));
		if (result > 0) {
			try {

				AdvanceShipmentNoticeDto advanceShipmentNoticeDto = asnService.findDto("getAsnAndpoByASNId",
						AbstractContextServiceImpl.getParamMap("asnId", asnId));

				String poNo = advanceShipmentNoticeDto.getPo().getPurchaseOrderNumber();
				String SSNNo = advanceShipmentNoticeDto.getServiceSheetNo();

				// Create a trust manager that does not validate certificate chains
				TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
					public java.security.cert.X509Certificate[] getAcceptedIssuers() {
						return null;
					}

					public void checkClientTrusted(X509Certificate[] certs, String authType) {
					}

					public void checkServerTrusted(X509Certificate[] certs, String authType) {
					}
				} };
				// Install the all-trusting trust manager
				SSLContext sc = null;
				try {
					sc = SSLContext.getInstance("SSL");
				} catch (NoSuchAlgorithmException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					sc.init(null, trustAllCerts, new java.security.SecureRandom());
				} catch (KeyManagementException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

				// Create all-trusting host name verifier
				HostnameVerifier allHostsValid = new HostnameVerifier() {
					public boolean verify(String hostname, SSLSession session) {
						return true;
					}
				};
				// Install the all-trusting host verifier
				HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);

				String url = "https://172.18.2.36:44300/sap/bc/yweb03_WS_57?sap-client=100&PONO=" + poNo + "&SSNNO="+ SSNNo;
				// String url
				// ="https://172.18.2.29:44300/sap/bc/yweb03_WS_57?sap-client=100&PONO="+poNo+"&SSNNO="+SSNNo;

				System.out.println(url);
				// ResponseDto resp1 = new ResponseDto();
				URLConnection request = null;
				try {
					// URL u = new URL( URLEncoder.encode(url, "UTF-8"));
					URL u = new URL(url.replace(" ", "%20"));
					request = u.openConnection();
					request.setRequestProperty("Accept", "application/json");
					// snippet begins
					request.connect();
					BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
					StringBuilder sb = new StringBuilder();
					String line;
					while ((line = br.readLine()) != null) {
						sb.append(line + "\n");
					}
					System.out.println(sb);
					br.close();

					// JSONObject obj = new JSONObject(sb.toString());
				} catch (MalformedURLException ex) {
					ex.printStackTrace();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

			CustomResponseDto resp = new CustomResponseDto(true, "SSN Canceled.");
			resp.addObject("status", AppBaseConstant.ASN_STATUS_CANCELED);
			return resp;
		} else {
			return new CustomResponseDto(false, "Error While Canceling SSN");
		}

	}

	@PostMapping(value = "/qcFailed/{asnId}/{remark}")
	public @ResponseBody CustomResponseDto qcFailed(@PathVariable("asnId") Long asnId,
			@PathVariable("remark") String remark) {

		try {
			if (asnLineComponent.qcFailASN(asnId, remark)) {
				CustomResponseDto resp = new CustomResponseDto(true, "QC Failed");
				resp.addObject("status", AppBaseConstant.ASN_STATUS_QC_FAILED);
				return resp;
			} else {
				return new CustomResponseDto(false, "Error While Updating QC Status");
			}
		} catch (Exception e) {
			return new CustomResponseDto(false, e.getMessage());
		}
	}

	/*
	 * @PostMapping(value="/asnUnload") public @ResponseBody CustomResponseDto
	 * asnUnload(@RequestBody AdvanceShipmentNoticeDto asn){ boolean unloadStatus =
	 * asnLineComponent.unloadASN(asn); if(unloadStatus){ CustomResponseDto resp =
	 * new CustomResponseDto(true,"ASN Unload Successful"); resp.addObject("status",
	 * AppBaseConstant.ASN_STATUS_UNLOAD); return resp; }else{ return new
	 * CustomResponseDto(false,"Error While Updating Unload Status"); }
	 * 
	 * }
	 */

	@PostMapping(value = "/asnUnload/{asnId}")
	public @ResponseBody CustomResponseDto asnUnload(@PathVariable("asnId") Long asnId) {
		Map<String, Object> param = AbstractContextServiceImpl.getParamMap("isUnload", "Y");
		param.put("unloadDate", new Date());
		param.put("status", AppBaseConstant.ASN_STATUS_UNLOAD);

		int result = asnService.updateByJpql(param,
				AbstractContextServiceImpl.getParamMap("advanceShipmentNoticeId", asnId));

		if (result > 0) {
			CustomResponseDto resp = new CustomResponseDto(true, "ASN Unload Successful");
			resp.addObject("status", AppBaseConstant.ASN_STATUS_UNLOAD);
			return resp;
		} else {
			return new CustomResponseDto(false, "Error While Updating Unload Status");
		}

	}

	// @PostMapping(value = "/asn105")
	// public @ResponseBody CustomResponseDto asn105(@RequestBody
	// AdvanceShipmentNoticeDto asn) {
	// CustomResponseDto resp = new CustomResponseDto();
	// String pono=asn.getPo().getPurchaseOrderNumber();
	// Long asnId=asn.getAdvanceShipmentNoticeId();
	// String outbounddeliveryNo=asn.getPo().getOutboundDeliveryNo();
	//
	// if("".equals(outbounddeliveryNo) || outbounddeliveryNo==null) {
	// ResponseDto response=validatePO(pono,asnId);
	// if(response.isHasError()) {
	// resp.setSuccess(false);
	// resp.setMessage(response.getMessage());
	// return resp;
	//
	// }
	// }
	//
	// Errors errors = new Errors();
	//
	//
	//
	// validator.validateGrnDetails(asn, errors);
	// if (errors.getErrorCount() > 0) {
	// resp.setSuccess(false);
	// resp.setMessage(errors.getErrorString());
	// return resp;
	//
	// } else {
	// boolean unloadStatus = false;
	// try {
	// Map<String, Object> param =
	// AbstractContextServiceImpl.getParamMap("postingDate", asn.getPostingDate());
	// User grnPostedby= new User();
	// grnPostedby.setUserId(contextService.getUser().getUserId());
	// param.put("grnPostedby", grnPostedby);
	// int result = asnService.updateByJpql(param, AbstractContextServiceImpl
	// .getParamMap("advanceShipmentNoticeId", asn.getAdvanceShipmentNoticeId()));
	// if (result > 0) {
	// unloadStatus = asnLineComponent.unloadASN105(asn);
	// } else {
	// return new CustomResponseDto(false, "Error While GRN Posting");
	// // return new CustomResponseDto(false, "GRN 103 is not processed yet in
	// SAP");
	// }
	// } catch (Exception e) {
	// //return new CustomResponseDto(false, "Error While Posting GRN ");
	// return new CustomResponseDto(false, "GRN 103 is not processed yet in SAP");
	// }
	// if (unloadStatus) {
	// resp = new CustomResponseDto(true, "GRN Posted Successful");
	// resp.addObject("status", AppBaseConstant.ASN_STATUS_105);
	//
	//// resp.addObject("status", AppBaseConstant.ASN_STATUS_105_Posted);
	// return resp;
	// } else {
	// return new CustomResponseDto(false, "Error While Posting GRN ");
	// // return new CustomResponseDto(false, "GRN 103 is not processed yet in
	// SAP");
	// }
	// }
	//
	//
	// }

	@PostMapping(value = "/asn105")
	public @ResponseBody CustomResponseDto asn105(@RequestBody AdvanceShipmentNoticeDto asn) throws ParseException {

		CustomResponseDto resp = new CustomResponseDto();
		String pono = asn.getPo().getPurchaseOrderNumber();
		Long asnId = asn.getAdvanceShipmentNoticeId();
		Integer asnno = asn.getAdvanceShipmentNoticeNo();
		String user = asn.getuser();
		String pass = asn.getpass();
		String coa = asn.getIsCOA();
		String batchNo = asn.getBatchNo();

		// ResponseDto response=validatePO(pono,asnId);
		// if(response.isHasError()) {
		// resp.setSuccess(false);
		// resp.setMessage(response.getMessage());
		// return resp;
		// }

		Errors errors = new Errors();

		validator.validateGrnDetails(asn, errors);
		if (errors.getErrorCount() > 0) {
			resp.setSuccess(false);
			resp.setMessage(errors.getErrorString());
			return resp;

		} else {
			boolean unloadStatus = false;
			try {
				Map<String, Object> param = AbstractContextServiceImpl.getParamMap("postingDate", asn.getPostingDate());
				User grnPostedby = new User();
				grnPostedby.setUserId(contextService.getUser().getUserId());
				param.put("grnPostedby", grnPostedby);
				int result = asnService.updateByJpql(param, AbstractContextServiceImpl
						.getParamMap("advanceShipmentNoticeId", asn.getAdvanceShipmentNoticeId()));
				if (result > 0) {
					unloadStatus = asnLineComponent.unloadASN105(asn);
					// ---------------------------------------------------------------------------103
					// by webervice-----------------------------------------------

					try {
						// Create a trust manager that does not validate certificate chains
						TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
							public java.security.cert.X509Certificate[] getAcceptedIssuers() {
								return null;
							}

							public void checkClientTrusted(X509Certificate[] certs, String authType) {
							}

							public void checkServerTrusted(X509Certificate[] certs, String authType) {
							}
						} };
						// Install the all-trusting trust manager
						SSLContext sc = null;
						try {
							sc = SSLContext.getInstance("SSL");
						} catch (NoSuchAlgorithmException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						try {
							sc.init(null, trustAllCerts, new java.security.SecureRandom());
						} catch (KeyManagementException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

						// Create all-trusting host name verifier
						HostnameVerifier allHostsValid = new HostnameVerifier() {
							public boolean verify(String hostname, SSLSession session) {
								return true;
							}
						};
						// Install the all-trusting host verifier
						HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
						String username = user;
						String password = pass;
						// String username="abap_ext1";
						// String password="HanaDS@123";

						// String url
						// ="https://172.18.2.33:44300/sap/bc/yweb03_ws_31?sap-client=110&po="+pono+"&asn="+asnno+"&asnid="+asnId+"&mvttyp=105"+"&coa="+coa;
						// String url
						// ="https://172.18.2.28:44300/sap/bc/yweb03_ws_31?sap-client=100&po="+pono+"&asn="+asnno+"&asnid="+asnId+"&mvttyp=105"+"&coa="+coa+"&batch="+batchNo;
						String url = "https://172.18.2.36:44300/sap/bc/yweb03_ws_31?sap-client=100&po=" + pono + "&asn="+ asnno + "&asnid=" + asnId + "&mvttyp=105" + "&coa=" + coa + "&batch=" + batchNo;
						// String url
						// ="https://172.18.2.29:44300/sap/bc/yweb03_ws_31?sap-client=100&po="+pono+"&asn="+asnno+"&asnid="+asnId+"&mvttyp=105"+"&coa="+coa+"&batch="+batchNo;
						System.out.println(url);
						// ResponseDto resp1 = new ResponseDto();
						URLConnection request = null;
						try {
							// URL u = new URL( URLEncoder.encode(url, "UTF-8"));
							URL u = new URL(url.replace(" ", "%20"));
							request = u.openConnection();
							request.setRequestProperty("Accept", "application/json");
							// snippet begins
							request.setRequestProperty("Authorization", "Basic "
									+ Base64.getEncoder().encodeToString((username + ":" + password).getBytes()));
							request.connect();
							BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
							StringBuilder sb = new StringBuilder();
							String line;
							while ((line = br.readLine()) != null) {
								sb.append(line + "\n");
							}
							System.out.println(sb);
							br.close();

							JSONObject obj = new JSONObject(sb.toString());
							String flag = obj.getString("Stts_flg");

							if (flag.equals("false")) {
								// Map<String, Object> propertyValueMap =
								// AbstractContextServiceImpl.getParamMap("status",
								// AppBaseConstant.ASN_STATUS_103_Posted);
								// result =
								// asnService.updateByJpql(propertyValueMap,AbstractContextServiceImpl.getParamMap("advanceShipmentNoticeId",asn.getAdvanceShipmentNoticeId()));

								String error = obj.getString("ERROR");
								return new CustomResponseDto(false, error);

							} else {
								String doctype = obj.getString("MAT_DOC");
								String year = obj.getString("DOC_YEAR");

								Map<String, Object> propertyValueMap = new HashMap<>();
								// propertyValueMap = AbstractContextServiceImpl.getParamMap("status",
								// AppBaseConstant.ASN_STATUS_105);
								propertyValueMap.put("grnId", doctype);
								propertyValueMap.put("grnYear", year);
								propertyValueMap.put("status", AppBaseConstant.ASN_STATUS_105);
								propertyValueMap.put("isGrn", "Y");
								propertyValueMap.put("grnDate", new Date());

								result = asnService.updateByJpql(propertyValueMap, AbstractContextServiceImpl
										.getParamMap("advanceShipmentNoticeId", asn.getAdvanceShipmentNoticeId()));
								resp.addObject("status", AppBaseConstant.ASN_STATUS_105);
								return new CustomResponseDto(true,
										"GRN Posted Successful" + '\n' + " Material Docno is :" + doctype);

							}
						} catch (MalformedURLException ex) {
							ex.printStackTrace();
						} catch (IOException ex) {
							ex.printStackTrace();
							// throw new RuntimeException("Error while writing file");
							return new CustomResponseDto(false, "Invalid SAP Login credentials");

						} finally {
							if (request != null) {
								try {
									((HttpURLConnection) request).disconnect();
								} catch (Exception ex) {
									ex.printStackTrace();
								}
							}

						}
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					return new CustomResponseDto(false, "Error While GRN Posting");
					// return new CustomResponseDto(false, "GRN 103 is not processed yet in SAP");
				}
			} catch (Exception e) {
				return new CustomResponseDto(false, "Error While Posting GRN ");
				// return new CustomResponseDto(false, "GRN 103 is not processed yet in SAP");
			}
			if (unloadStatus) {
				// resp = new CustomResponseDto(true, "GRN Posted Successful");
				// resp.addObject("status", AppBaseConstant.ASN_STATUS_105);

				// resp.addObject("status", AppBaseConstant.ASN_STATUS_105_Posted);
				return resp;
			} else {
				return new CustomResponseDto(false, "Error While Posting GRN ");
				// return new CustomResponseDto(false, "GRN 103 is not processed yet in SAP");
			}

		}

	}

	// ---------------------------------------------------------with
	// ftp--------------------------------------------------------------------
	// @PostMapping(value = "/asnGateIn/{asnId}/{gDate}")
	// public @ResponseBody CustomResponseDto asnGateIn(@PathVariable("asnId") Long
	// asnId,
	// @PathVariable("gDate") String gDate) {
	//
	// //CustomResponseDto dto=new CustomResponseDto();
	// String status;
	// Map<String, Object> params = new HashMap<String, Object>();
	// params.put("asnId", asnId);
	// List<AdvanceShipmentNoticeLineDto> lineDtos =
	// asnLineService.findDtos("getASNLinesByASNId", params);
	// Map<String, String> plantList =
	// refListService.getReferenceListMap(CoreReferenceConstants.GATE_IN_PLANT);
	// Object value = plantList.get(lineDtos.get(0).getPoLine().getPlant());
	// if ("".equals(value) || value == null) {
	// //status = AppBaseConstant.ASN_STATUS_GATE_IN;
	//
	// status = AppBaseConstant.ASN_STATUS_103_Posted;
	// } else {
	// status = AppBaseConstant.ASN_STATUS_101;
	// }
	//
	// String
	// pono=lineDtos.get(0).getAdvanceshipmentnotice().getPo().getPurchaseOrderNumber();
	// int
	// asnno=lineDtos.get(0).getAdvanceshipmentnotice().getAdvanceShipmentNoticeNo();
	//
	// String
	// outbounddelivery=lineDtos.get(0).getAdvanceshipmentnotice().getPo().getOutboundDeliveryNo();
	//
	// if("".equals(outbounddelivery) || outbounddelivery==null) {
	//
	// ResponseDto response=validatePO(pono,asnId);
	// if(response.isHasError()) {
	// CustomResponseDto resp = new CustomResponseDto();
	// resp.setSuccess(false);
	// resp.setMessage(response.getMessage());
	// return resp;
	//
	// }
	// }
	//
	//
	// Map<String, Object> param = AbstractContextServiceImpl.getParamMap("status",
	// status);
	// param.put("issueDate", gDate);
	// param.put("isGateIn", "Y");
	//// param.put("gateInDate", new Date());
	// param.put("date_103", new Date());
	//
	//
	//
	// boolean result = false;
	//
	// try {
	//
	// result = asnService.processGateIn(asnId, lineDtos, param);
	//// try {
	//// // Create a trust manager that does not validate certificate chains
	//// TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager()
	// {
	//// public java.security.cert.X509Certificate[] getAcceptedIssuers() {
	//// return null;
	//// }
	////
	//// public void checkClientTrusted(X509Certificate[] certs, String authType) {
	//// }
	////
	//// public void checkServerTrusted(X509Certificate[] certs, String authType) {
	//// }
	//// } };
	////
	//// // Install the all-trusting trust manager
	//// SSLContext sc = null;
	//// try {
	//// sc = SSLContext.getInstance("SSL");
	//// } catch (NoSuchAlgorithmException e1) {
	//// // TODO Auto-generated catch block
	//// e1.printStackTrace();
	//// }
	//// try {
	//// sc.init(null, trustAllCerts, new java.security.SecureRandom());
	//// } catch (KeyManagementException e1) {
	//// // TODO Auto-generated catch block
	//// e1.printStackTrace();
	//// }
	//// HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
	////
	//// // Create all-trusting host name verifier
	//// HostnameVerifier allHostsValid = new HostnameVerifier() {
	//// public boolean verify(String hostname, SSLSession session) {
	//// return true;
	//// }
	//// };
	////
	//// // Install the all-trusting host verifier
	//// HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
	//// result = asnService.processGateIn(asnId, lineDtos, param);
	//// Map<String, Object> jsonlist = new HashMap<String, Object>();
	//// JSONArray newjsonlist=new JSONArray();
	////
	//// jsonlist.put("pono",
	// lineDtos.get(0).getAdvanceshipmentnotice().getPo().getPurchaseOrderNumber());
	//// jsonlist.put("lineDtos", lineDtos);
	//// newjsonlist.put(jsonlist);
	////// String jsonnewlist = new String(newjsonlist.toString());
	////// String json = mapper.writeValueAsString(jsonnewlist);
	////// System.out.println(json);
	////
	//// String url
	// ="https://vhaklds4ci.sap.alkylamines.com:44300/sap/bc/yweb03_ws_31?sap-client=100&json="+newjsonlist;
	//// System.out.println(url);
	//// ResponseDto resp = new ResponseDto();
	//// URLConnection request = null;
	//// try {
	//// // URL u = new URL( URLEncoder.encode(url, "UTF-8"));
	//// URL u = new URL(url.replace(" ","%20"));
	//// request = u.openConnection();
	//// request.connect();
	//// BufferedReader br = new BufferedReader(new
	// InputStreamReader(request.getInputStream()));
	//// StringBuilder sb = new StringBuilder();
	//// String line;
	//// while ((line = br.readLine()) != null) {
	//// sb.append(line + "\n");
	//// }
	//// System.out.println(sb);
	//// br.close();
	////
	//// }
	//// catch (MalformedURLException ex) {
	//// ex.printStackTrace();
	//// } catch (IOException ex) {
	//// ex.printStackTrace();
	//// } finally {
	//// if (request != null) {
	//// try {
	//// ((HttpURLConnection) request).disconnect();
	//// } catch (Exception ex) {
	//// ex.printStackTrace();
	//// }
	//// }
	////
	//// }
	//// } catch (JSONException e) {
	//// // TODO Auto-generated catch block
	//// e.printStackTrace();
	//// }
	// } catch (Exception e) {
	// e.printStackTrace();
	// return new CustomResponseDto(false, "Error While Updating GATE IN Status");
	// // return new CustomResponseDto(false, "Invalid SAP Login credentials");
	// }
	//
	//
	// if (result) {
	// // CustomResponseDto resp = new CustomResponseDto(true, "GATE IN
	// Successful");
	// CustomResponseDto resp = new CustomResponseDto(true, "103 Posted
	// Successful");
	// // resp.addObject("status", AppBaseConstant.ASN_STATUS_GATE_IN);
	//
	// resp.addObject("status", AppBaseConstant.ASN_STATUS_103_Posted);
	// return resp;
	// }
	// else {
	// return new CustomResponseDto(false, "Error While Updating GATE IN Status");
	//// return new CustomResponseDto(false, "Invalid SAP Login credentials");
	// }
	//
	// }

	// ---------------------------------------------------------with
	// ftp--------------------------------------------------------------------

	// //---------------------------------------------------------with
	// WS--------------------------------------------------------------------
	//
	@PostMapping(value = "/asnGateIn/{asnId}/{gDate}/{user}/{pass}")
	public @ResponseBody CustomResponseDto asnGateIn(@PathVariable("asnId") Long asnId,
			@PathVariable("gDate") String gDate, @PathVariable("user") String user, @PathVariable("pass") String pass) throws ParseException {

		// CustomResponseDto dto=new CustomResponseDto();
		String status;
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("asnId", asnId);
		List<AdvanceShipmentNoticeLineDto> lineDtos = asnLineService.findDtos("getASNLinesByASNId", params);
		Map<String, String> plantList = refListService.getReferenceListMap(CoreReferenceConstants.GATE_IN_PLANT);
		Object value = plantList.get(lineDtos.get(0).getPoLine().getPlant());
		
		
		
		 Date poDate=lineDtos.get(0).getAdvanceshipmentnotice().getPo().getDate();
		
		 DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		 DateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");

		 Date newpoDate = formatter.parse(formatter.format(poDate));
	 
		 Date postingDate=formatter1.parse(gDate);  
		 
		 Date yesterdayDate=DateUtils.addDays(new Date(), -1);
		 
		 Date newyesterdayDate = formatter.parse(formatter.format(yesterdayDate));
		 
		 String lastdateoflastMonth= LocalDate.now().withDayOfMonth(1).minusDays(1).toString();
		 Date lastDate=formatter1.parse(lastdateoflastMonth);
		 String firstdateofcurrMonth=LocalDate.now().withDayOfMonth( 1 ).toString();
		 Date firstDate=formatter1.parse(firstdateofcurrMonth);
		 String seconddateofcurrMonth=LocalDate.now().withDayOfMonth( 2 ).toString();
		 Date secondDate=formatter1.parse(seconddateofcurrMonth);
		 String thirddateofcurrMonth=LocalDate.now().withDayOfMonth( 3 ).toString();
		 Date thirdDate=formatter1.parse(thirddateofcurrMonth);
		 Date today=new Date();
		 Date todayDate=formatter.parse(formatter.format(today));
//		 String fourthdateofcurrMonth=LocalDate.now().withDayOfMonth( 4 ).toString();
//		 Date fourthDate=formatter1.parse(fourthdateofcurrMonth);
//		 String fifthdateofcurrMonth=LocalDate.now().withDayOfMonth( 5 ).toString();
//		 Date fifthDate=formatter1.parse(fifthdateofcurrMonth);
//		 if(postingDate.before(newyesterdayDate)) {
//			 return new CustomResponseDto(false, "GRN is not allowed for more than yesterday date");
//		 }
		 
		 if(todayDate.equals(secondDate) || todayDate.equals(firstDate) || todayDate.equals(thirdDate)) {
		 if(postingDate.before(newyesterdayDate) && (!postingDate.equals(lastDate) && !postingDate.equals(firstDate) && !postingDate.equals(secondDate))) {
			 return new CustomResponseDto(false, "Posting Date is not allowed before 2 days");
		 }
		 }
		 else if(postingDate.before(newyesterdayDate)) {
			 return new CustomResponseDto(false, "Posting Date is not allowed before 2 days"); 
		 }
		
		 if(postingDate.before(newpoDate)) {
			 return new CustomResponseDto(false, "Posting Date Should be Greater than PO Date");
		 }
		 
		// if ("".equals(value) || value == null) {
		// //status = AppBaseConstant.ASN_STATUS_GATE_IN;
		//
		// status = AppBaseConstant.ASN_STATUS_103_Posted;
		// } else {
		// status = AppBaseConstant.ASN_STATUS_101;
		// }

		String pono = lineDtos.get(0).getAdvanceshipmentnotice().getPo().getPurchaseOrderNumber();
		int asnno = lineDtos.get(0).getAdvanceshipmentnotice().getAdvanceShipmentNoticeNo();

		Map<String, Object> parameter = AbstractContextServiceImpl.getParamMap("issueDate", gDate);
		int closedresult = asnService.updateByJpql(parameter,
				AbstractContextServiceImpl.getParamMap("advanceShipmentNoticeId", asnId));

		// ResponseDto response=validatePO(pono,asnId);
		// if(response.isHasError()) {
		// CustomResponseDto resp = new CustomResponseDto();
		// resp.setSuccess(false);
		// resp.setMessage(response.getMessage());
		// return resp;
		// }
		// ---------------------------------------------------------------------------103
		// by webervice-----------------------------------------------
		// Create a trust manager that does not validate certificate chains
		TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
			public java.security.cert.X509Certificate[] getAcceptedIssuers() {
				return null;
			}

			public void checkClientTrusted(X509Certificate[] certs, String authType) {
			}

			public void checkServerTrusted(X509Certificate[] certs, String authType) {
			}
		} };

		// Install the all-trusting trust manager
		SSLContext sc = null;
		try {
			sc = SSLContext.getInstance("SSL");
		} catch (NoSuchAlgorithmException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			sc.init(null, trustAllCerts, new java.security.SecureRandom());
		} catch (KeyManagementException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

		// Create all-trusting host name verifier
		HostnameVerifier allHostsValid = new HostnameVerifier() {
			public boolean verify(String hostname, SSLSession session) {
				return true;
			}
		};

		// Install the all-trusting host verifier
		HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
		String username = user;
		String password = pass;
		// String username="abap_ext1";
		// String password="HanaDS@123";

		// String url
		// ="https://172.18.2.33:44300/sap/bc/yweb03_ws_31?sap-client=110&po="+pono+"&asn="+asnno+"&asnid="+asnId+"&mvttyp=103";
		// String url
		// ="https://172.18.2.28:44300/sap/bc/yweb03_ws_31?sap-client=100&po="+pono+"&asn="+asnno+"&asnid="+asnId+"&mvttyp=103";

		// String url
		// ="https://172.18.2.29:44300/sap/bc/yweb03_ws_31?sap-client=100&po="+pono+"&asn="+asnno+"&asnid="+asnId+"&mvttyp=103";
		String url = "https://172.18.2.36:44300/sap/bc/yweb03_ws_31?sap-client=100&po=" + pono + "&asn=" + asnno+ "&asnid=" + asnId + "&mvttyp=103";
		System.out.println(url);
		// ResponseDto resp1 = new ResponseDto();
		URLConnection request = null;
		try {
			// URL u = new URL( URLEncoder.encode(url, "UTF-8"));
			URL u = new URL(url.replace(" ", "%20"));
			request = u.openConnection();
			request.setRequestProperty("Accept", "application/json");
			// snippet begins
			request.setRequestProperty("Authorization",
					"Basic " + Base64.getEncoder().encodeToString((username + ":" + password).getBytes()));
			request.connect();
			BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
			StringBuilder sb = new StringBuilder();
			String line;
			while ((line = br.readLine()) != null) {
				sb.append(line + "\n");
			}
			System.out.println(sb);
			br.close();

			JSONObject obj = new JSONObject(sb.toString());
			String flag = obj.getString("Stts_flg");

			if (flag.equals("false")) {
				String error = obj.getString("ERROR");

				return new CustomResponseDto(false, error);

			} else {
				String sap103Id = obj.getString("MAT_DOC");
				String sap103Year = obj.getString("DOC_YEAR");
				status = AppBaseConstant.ASN_STATUS_103_Posted;

				Map<String, Object> param = AbstractContextServiceImpl.getParamMap("status", status);
				param.put("issueDate", gDate);
				param.put("isGateIn", "Y");
				// param.put("gateInDate", new Date());
				param.put("date_103", new Date());
				param.put("sap103Year", sap103Year);
				param.put("sap103Id", sap103Id);

				// Map<String, Object> par = AbstractContextServiceImpl.getParamMap("status",
				// status);
				// par.put("user", user);
				// par.put("pass", pass);
				// par.put("pono", pono);
				// par.put("asnno", asnno);
				// par.put("doctype",doctype);
				// par.put("year", year);

				boolean result = false;
				boolean flag1 = Boolean.parseBoolean(flag);

				try {

					result = asnService.processGateIn(asnId, lineDtos, param);
					if (result == flag1) {
						// CustomResponseDto resp = new CustomResponseDto(true, "GATE IN Successful");
						CustomResponseDto resp = new CustomResponseDto(true,
								" 103 Posted Successful " + '\n' + " Material Docno is :" + sap103Id);

						// resp.addObject("status", AppBaseConstant.ASN_STATUS_GATE_IN);

						resp.addObject("status", AppBaseConstant.ASN_STATUS_103_Posted);
						return resp;
					}

				} catch (Exception e) {
					e.printStackTrace();
					// return new CustomResponseDto(false, "Error While Updating GATE IN Status");
					// return new CustomResponseDto(false, " 103 Error:" + error);
				}

			}
		} catch (MalformedURLException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
			// throw new RuntimeException("Error while writing file");
			return new CustomResponseDto(false, "Invalid SAP Login credentials");

		} finally {
			if (request != null) {
				try {
					((HttpURLConnection) request).disconnect();
				} catch (Exception ex) {
					ex.printStackTrace();

				}
			}

		}
		// ---------------------------------------------------------------------------103
		// by webervice-----------------------------------------------

		return null;

	}

	// ---------------------------------------------------------with
	// WS--------------------------------------------------------------------

	// @RequestMapping(value="/moveType103ForSAP/{asnId}" , method =
	// RequestMethod.GET)
	// public @ResponseBody CustomResponseDto
	// moveType103ForSAP(@PathVariable("asnId") Long asnId) {
	// //CustomResponseDto dto=new CustomResponseDto();
	// String status;
	// Map<String, Object> params = new HashMap<String, Object>();
	// params.put("asnId", asnId);
	// List<AdvanceShipmentNoticeLineDto> lineDtos =
	// asnLineService.findDtos("getASNLinesByASNId", params);
	// Map<String, String> plantList =
	// refListService.getReferenceListMap(CoreReferenceConstants.GATE_IN_PLANT);
	// Object value = plantList.get(lineDtos.get(0).getPoLine().getPlant());
	// if ("".equals(value) || value == null) {
	// //status = AppBaseConstant.ASN_STATUS_GATE_IN;
	//
	// status = AppBaseConstant.ASN_STATUS_103_Posted;
	// } else {
	// status = AppBaseConstant.ASN_STATUS_101;
	// }
	//
	// String
	// pono=lineDtos.get(0).getAdvanceshipmentnotice().getPo().getPurchaseOrderNumber();
	//
	//
	// ResponseDto response=validatePO(pono,asnId);
	// if(response.isHasError()) {
	// CustomResponseDto resp = new CustomResponseDto();
	// resp.setSuccess(false);
	// resp.setMessage(response.getMessage());
	// return resp;
	//
	// }
	//
	// boolean result = false;
	//
	// try {
	//
	// result = asnService.processGateInForSAP(asnId, lineDtos);
	// }
	// catch (Exception e) {
	// e.printStackTrace();
	// return new CustomResponseDto(false, "Error While Updating GATE IN Status");
	// }
	//
	//
	// if (result) {
	// // CustomResponseDto resp = new CustomResponseDto(true, "GATE IN
	// Successful");
	// CustomResponseDto resp = new CustomResponseDto(true, "103 Posted
	// Successful");
	// // resp.addObject("status", AppBaseConstant.ASN_STATUS_GATE_IN);
	//
	// resp.addObject("status", AppBaseConstant.ASN_STATUS_103_Posted);
	// return resp;
	// }
	// else {
	// return new CustomResponseDto(false, "Error While Updating GATE IN Status");
	// }
	//
	//
	// }

	// @PostMapping(value="/asnGateOut/{asnId}")
	// public @ResponseBody CustomResponseDto asnGateOut(@PathVariable("asnId")
	// Long asnId){
	//
	//// Map<String , Object> param =
	// AbstractContextServiceImpl.getParamMap("isGateOut", "Y");
	//// param.put("gateOutDate", new Date());
	//// param.put("status", AppBaseConstant.ASN_STATUS_GATE_OUT);
	//// int result = asnService.updateByJpql(param,
	// AbstractContextServiceImpl.getParamMap("advanceShipmentNoticeId",
	// asnId));
	//// if(result>0){
	//// CustomResponseDto resp = new CustomResponseDto(true,"GATE OUT
	// Successful");
	//// resp.addObject("status", AppBaseConstant.ASN_STATUS_GATE_OUT);
	//// return resp;
	//// }else{
	//// return new CustomResponseDto(false,"Error While Updating GATE OUT
	// Status");
	//// }
	//
	// Map<String , Object> param =
	// AbstractContextServiceImpl.getParamMap("isGateOut", "Y");
	// param.put("status", AppBaseConstant.ASN_STATUS_CLOSED);
	// param.put("gateOutDate", new Date());
	// int result = 0;
	//
	// Map<String , Object> where =
	// AbstractContextServiceImpl.getParamMap("status",
	// AppBaseConstant.ASN_STATUS_105);
	// where.put("advanceShipmentNoticeId",asnId);
	//
	//
	// result= asnService.updateByJpql(param,where);
	// if(result==0){
	//
	// param = AbstractContextServiceImpl.getParamMap("status",
	// AppBaseConstant.ASN_STATUS_GATE_OUT);
	// param.put("isGateOut", "Y");
	// param.put("gateOutDate", new Date());
	//
	// where = new HashMap<>();
	// where.put("advanceShipmentNoticeId",asnId );
	// result= asnService.updateByJpql(param,where);
	// CustomResponseDto resp = new CustomResponseDto(true,"GATE OUT
	// Successful");
	// return resp;
	// }else{
	// CustomResponseDto resp = new CustomResponseDto(true,"GATE OUT
	// Successful");
	// return resp;
	// }
	// }

	// @PostMapping(value="/asnGateOut")
	// public @ResponseBody CustomResponseDto asnGateOut(@RequestBody
	// AdvanceShipmentNoticeDto asn){
	// int result =
	// asnService.updateByJpql(AbstractContextServiceImpl.getParamMap("status",
	// AppBaseConstant.ASN_STATUS_CLOSED)
	// , AbstractContextServiceImpl.getParamMap("advanceShipmentNoticeId",
	// asn.getAdvanceShipmentNoticeId()));
	//
	// if(result>0){
	// CustomResponseDto resp = new CustomResponseDto(true,"GATE OUT
	// Successful");
	// resp.addObject("status", AppBaseConstant.ASN_STATUS_CLOSED);
	// return resp;
	// }else{
	// return new CustomResponseDto(false,"Error While Updating GATE OUT
	// Status");
	// }
	// }
	@PostMapping(value = "/asnGateOut/{asnId}")
	public @ResponseBody CustomResponseDto asnGateOut(@PathVariable("asnId") Long asnId) {
		boolean result = asnService.updateNewASNClosedStatus(asnId);
		if (result) {
			User closedBy = new User();
			closedBy.setUserId(contextService.getUser().getUserId());

			Map<String, Object> parameter = AbstractContextServiceImpl.getParamMap("closedBy", closedBy);

			int closedresult = asnService.updateByJpql(parameter,
					AbstractContextServiceImpl.getParamMap("advanceShipmentNoticeId", asnId));

			// param.put("closedBy", closedBy);
			CustomResponseDto resp = new CustomResponseDto(true, "GATE OUT Successful");
			return resp;

		} else {

			Map<String, Object> param = AbstractContextServiceImpl.getParamMap("status",
					AppBaseConstant.ASN_STATUS_GATE_OUT);
			param.put("isGateOut", "Y");
			param.put("gateOutDate", new Date());
			User closedBy = new User();
			closedBy.setUserId(contextService.getUser().getUserId());
			param.put("closedBy", closedBy);

			Map<String, Object> where = new HashMap<>();
			where.put("advanceShipmentNoticeId", asnId);
			int resultnew = asnService.updateByJpql(param, where);
			CustomResponseDto resp = new CustomResponseDto(true, "GATE OUT Successful");
			return resp;

		}
	}

	
	
	@PostMapping(value = "/asnGateOutWithoutPO/{asnId}")
	public @ResponseBody CustomResponseDto asnGateOutWithoutPO(@PathVariable("asnId") Long asnId) {


			Map<String, Object> param = AbstractContextServiceImpl.getParamMap("status",
					AppBaseConstant.ASN_STATUS_GATE_OUT_WITHOUT_PO);
			param.put("isGateOut", "Y");
			param.put("gateOutDate", new Date());
			User closedBy = new User();
			closedBy.setUserId(contextService.getUser().getUserId());
			param.put("closedBy", closedBy);

			Map<String, Object> where = new HashMap<>();
			where.put("advanceShipmentNoticeId", asnId);
			int resultnew = asnService.updateByJpql(param, where);
			CustomResponseDto resp = new CustomResponseDto(true, "GATE OUT Successful");
			return resp;

		
	}
	
	@PostMapping(value = "/approveServiceSheet/{asnId}")
	public @ResponseBody CustomResponseDto approveServiceSheet(@PathVariable("asnId") Long asnId) {
		int result = asnService.updateByJpql(
				AbstractContextServiceImpl.getParamMap("status", AppBaseConstant.SERVICE_SHEET_STATUS_APPROVED),
				AbstractContextServiceImpl.getParamMap("advanceShipmentNoticeId", asnId));
		if (result > 0) {
			CustomResponseDto resp = new CustomResponseDto(true, "Service Sheet Approved");
			resp.addObject("status", AppBaseConstant.SERVICE_SHEET_STATUS_APPROVED);
			try {
				AdvanceShipmentNoticeDto advanceShipmentNoticeDto = asnService.findDto("getAsnAndPartnerByASNId",
						AbstractContextServiceImpl.getParamMap("asnId", asnId));
				asnComponent.sendMailOnApproveServiceSheet(advanceShipmentNoticeDto);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return resp;
		} else {
			return new CustomResponseDto(false, "Error While Approving Service Sheet");
		}
	}

	@PostMapping(value = "/rejectServiceSheet/{asnId}/{reason}")
	public @ResponseBody CustomResponseDto rejectServiceSheet(@PathVariable("asnId") Long asnId,
			@PathVariable("reason") String rejectReason) {
		Map<String, Object> propertyValueMap = AbstractContextServiceImpl.getParamMap("status",
				AppBaseConstant.SERVICE_SHEET_STATUS_REJECTED);
		propertyValueMap.put("description", rejectReason);
		int result = asnService.updateByJpql(propertyValueMap,
				AbstractContextServiceImpl.getParamMap("advanceShipmentNoticeId", asnId));
		if (result > 0) {
			CustomResponseDto resp = new CustomResponseDto(true, "Service Sheet Rejected");
			resp.addObject("status", AppBaseConstant.SERVICE_SHEET_STATUS_REJECTED);
			return resp;
		} else {
			return new CustomResponseDto(false, "Error While Rejecting Service Sheet");
		}
	}

	@PostMapping(value = "/approveServiceEntry/{asnId}")
	public @ResponseBody CustomResponseDto approveServiceEntry(@PathVariable("asnId") Long asnId) {
		AdvanceShipmentNoticeDto asn = asnService.findDto("getASNByASNId",
				AbstractContextServiceImpl.getParamMap("asnId", asnId));
		try {
			if (asnService.approveServiceEntrySheet(asn)) {
				CustomResponseDto resp = new CustomResponseDto(true, "Service Entry Sheet Approved");
				resp.addObject("status", AppBaseConstant.SERVICE_ENTRY_SHEET_STATUS_APPROVED);
				return resp;
			} else {
				return new CustomResponseDto(false, "Error While Approving Service Sheet");
			}
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new CustomResponseDto(false, "Error While Approving Service Sheet");
		}
	}

	@PostMapping(value = "/rejectServiceEntry/{asnId}/{reason}")
	public @ResponseBody CustomResponseDto rejectServiceEntry(@PathVariable("asnId") Long asnId,
			@PathVariable("reason") String rejectReason) {
		Map<String, Object> propertyValueMap = AbstractContextServiceImpl.getParamMap("status",
				AppBaseConstant.SERVICE_ENTRY_SHEET_STATUS_REJECTED);
		propertyValueMap.put("description", rejectReason);
		int result = asnService.updateByJpql(propertyValueMap,
				AbstractContextServiceImpl.getParamMap("advanceShipmentNoticeId", asnId));
		if (result > 0) {
			CustomResponseDto resp = new CustomResponseDto(true, "Service Entry Sheet Rejected");
			resp.addObject("status", AppBaseConstant.SERVICE_ENTRY_SHEET_STATUS_REJECTED);
			return resp;
		} else {
			return new CustomResponseDto(false, "Error While Rejecting Service Entry Sheet");
		}
	}

	// @Scheduled(cron = "0 0/10 * * * ?")
	// @Scheduled(cron = "*/10 * * * * *")
	// @PostMapping(value = "/fetchGRNNO")
	// public @ResponseBody ResponseDto fetchGRNNO() {
	// System.out.println("Into Scheduler");
	// Errors errors = grnComponent.fetchGRNNO();
	// if (errors.getErrorCount() == 0) {
	// return new ResponseDto(false, "Fetch Complete");
	// } else {
	// return new ResponseDto(true, errors.getErrorStringWithCode());
	// }
	//
	// // return new GRNFTPResponse();
	// }

	@PostMapping(value = "/proceedBillBooking")
	public @ResponseBody CustomResponseDto proceedBillBooking(@RequestBody AdvanceShipmentNoticeDto asn) {
		try {
			asnService.billBooking(asn);
			return new CustomResponseDto(true, "Posting Complete");
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new CustomResponseDto(false, "Posting Failed");
		}

	}

	@PostMapping(value = "/getASNForBooking")
	public @ResponseBody CustomResponseDto getASNForBooking() {
		CustomResponseDto resp = null;
		List<AdvanceShipmentNoticeDto> asnList = new ArrayList<>();
		Map<String, Object> params = new HashMap<String, Object>();
		List<String> param = new ArrayList<>();
		if (contextService != null && contextService.getDefaultRole() != null
				&& !CommonUtil.isStringEmpty(contextService.getDefaultRole().getValue())) {
			String role = contextService.getDefaultRole().getValue();
			String plant = contextService.getUserDetails().getPlant();
			// BPartnerDto partner = contextService.getPartner();
			if (role.equals(AppBaseConstant.ROLE_PARTNER_ADMIN)) {
				param.add(AppBaseConstant.ASN_STATUS_105);
				param.add(AppBaseConstant.ASN_STATUS_CLOSED);
				param.add(AppBaseConstant.SERVICE_ENTRY_SHEET_STATUS_APPROVED);
				param.add(AppBaseConstant.ASN_STATUS_101);
				params.put("status", param);
				asnList = asnService.findDtos("getASNBYStatusForAdmin", params);
			} else {
				params.put("status", param);
				params.put("plant", plant);
				asnList = asnService.findDtos("getASNBYStatus", params);
			}

			/* AbstractContextServiceImpl.getParamMap("status", param)); */

			resp = new CustomResponseDto("asnList", asnList);
			resp.addObject("role", role);
			Map<String, ReferenceListDto> withHoldingTax = refListService
					.getRefListCollection(CoreReferenceConstants.WITH_HOLDING_TAX);
			resp.addObject("withHoldingTax", withHoldingTax);
			Map<String, String> glMaster = refListService.getReferenceListMap(CoreReferenceConstants.GL_MASTER);
			resp.addObject("glMaster", glMaster);
		}

		return resp;
	}

	@PostMapping(value = "/approveServiceSheetByDto")
	public @ResponseBody CustomResponseDto approveServiceSheetByDto(@RequestBody AdvanceShipmentNoticeDto asn) {
		boolean isCostCenterValidate = validateCostCenter(asn.getAsnLineList());
		if (!isCostCenterValidate) {
			return new CustomResponseDto(false, "Cost Center Quantity Did Not Matched Delivery Quantity ");
		}
		boolean status = asnLineService.updateCostCenter(asn.getAsnLineList());
		if (!status) {
			return new CustomResponseDto(false, "Error While Updating Cost Center");
		}
		int result = asnService.updateByJpql(
				AbstractContextServiceImpl.getParamMap("status", AppBaseConstant.SERVICE_SHEET_STATUS_APPROVED),
				AbstractContextServiceImpl.getParamMap("advanceShipmentNoticeId", asn.getAdvanceShipmentNoticeId()));
		if (result > 0) {
			CustomResponseDto resp = new CustomResponseDto(true, "Service Sheet Approved");
			resp.addObject("status", AppBaseConstant.SERVICE_SHEET_STATUS_APPROVED);
			try {
				AdvanceShipmentNoticeDto advanceShipmentNoticeDto = asnService.findDto("getAsnAndPartnerByASNId",
						AbstractContextServiceImpl.getParamMap("asnId", asn.getAdvanceShipmentNoticeId()));
				asnComponent.sendMailOnApproveServiceSheet(advanceShipmentNoticeDto);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return resp;
		} else {
			return new CustomResponseDto(false, "Error While Approving Service Sheet");
		}
	}

	public boolean validateCostCenter(List<AdvanceShipmentNoticeLineDto> asnLineList) {
		try {
			double totalQty = 0;

			for (AdvanceShipmentNoticeLineDto asnLine : asnLineList) {
				if (null != asnLine.getServiceLineList()) {
					for (AdvanceShipmentNoticeLineDto serviceLine : asnLine.getServiceLineList()) {
						if (null != serviceLine) {
							double costCenterQTY = 0;
							if (serviceLine.getAsnLineCostCenter() != null) {
								totalQty = serviceLine.getDeliveryQuantity();
								for (ASNLineCostCenterDto costdto : serviceLine.getAsnLineCostCenter()) {
									costCenterQTY = costCenterQTY + Double.parseDouble(costdto.getQuantity());

								}
							}
							if (totalQty != costCenterQTY) {
								return false;
							}
						}
					}
				}
			}
		} catch (Exception e) {
			return false;
		}
		return true;

	}

	/*
	 * @PostMapping(value = "/approveServiceSheet2/{asnId}") public @ResponseBody
	 * CustomResponseDto approveServiceSheet2(@PathVariable("asnId") Long asnId) {
	 * int result = asnService.updateByJpql(
	 * AbstractContextServiceImpl.getParamMap("status",
	 * AppBaseConstant.SERVICE_SHEET_STATUS_APPROVED),
	 * AbstractContextServiceImpl.getParamMap("advanceShipmentNoticeId", asnId)); if
	 * (result > 0) { CustomResponseDto resp = new CustomResponseDto(true,
	 * "Service Sheet Approved"); resp.addObject("status",
	 * AppBaseConstant.SERVICE_SHEET_STATUS_APPROVED); try {
	 * AdvanceShipmentNoticeDto advanceShipmentNoticeDto =
	 * asnService.findDto("getAsnAndPartnerByASNId",
	 * AbstractContextServiceImpl.getParamMap("asnId", asnId));
	 * asnComponent.sendMailOnApproveServiceSheet(advanceShipmentNoticeDto);
	 * zmmServiceSheetClient.postServiceEntrySheet(advanceShipmentNoticeDto); }
	 * catch (Exception e) { e.printStackTrace(); } return resp; } else { return new
	 * CustomResponseDto(false, "Error While Approving Service Sheet"); } }
	 */
	// @PostMapping(value = "/approveServiceSheet2")
	// public @ResponseBody CustomResponseDto approveServiceSheet2(@RequestBody
	// AdvanceShipmentNoticeDto asn) {
	// boolean isCostCenterValidate = validateCostCenter(asn.getAsnLineList());
	// if (!isCostCenterValidate) {
	// return new CustomResponseDto(false, "Cost Center Quantity Did Not Matched
	// Delivery Quantity ");
	// }
	// boolean status = asnLineService.updateCostCenter(asn.getAsnLineList());
	// if (!status) {
	// return new CustomResponseDto(false, "Error While Updating Cost Center");
	// }
	//
	// try {
	//// AdvanceShipmentNoticeDto advanceShipmentNoticeDto =
	// asnService.findDto("getAsnAndPartnerByASNId",
	//// AbstractContextServiceImpl.getParamMap("asnId",
	// asn.getAdvanceShipmentNoticeId()));
	//
	// String username=asn.getuser();
	// String password=asn.getpass();
	//
	//// if(!username.equals("") && !password.equals("")) {
	//
	// AdvanceShipmentNoticeDto advanceShipmentNoticeDto =
	// asnService.findDto("getAsnAndPartnerByASNId",
	// AbstractContextServiceImpl.getParamMap("asnId",
	// asn.getAdvanceShipmentNoticeId()));
	//
	// Map<String, Object> param = AbstractContextServiceImpl.getParamMap("user",
	// username);
	// param.put("pass", password);
	//
	// boolean result=
	// zmmServiceSheetClient.postServiceEntrySheet(advanceShipmentNoticeDto);
	// // CustomResponseDto result=
	// zmmServiceSheetClient.postServiceEntrySheet(advanceShipmentNoticeDto,param);
	// if(result) {
	// // if(result.getMessage().equals("Updated successfully")) {
	//
	// Map<String, Object> params = AbstractContextServiceImpl.getParamMap("status",
	// AppBaseConstant.SERVICE_SHEET_STATUS_APPROVED_2);
	// User ssnApprovedby= new User();
	// ssnApprovedby.setUserId(contextService.getUser().getUserId());
	// params.put("ssnApprovedBy", ssnApprovedby);
	// params.put("ssnApprovedDate", new Date());
	//
	//
	// asnService.updateByJpql(params,
	// AbstractContextServiceImpl.getParamMap("advanceShipmentNoticeId",
	// asn.getAdvanceShipmentNoticeId()));
	//// asnService.updateByJpql(
	//// AbstractContextServiceImpl.getParamMap("status",
	// AppBaseConstant.SERVICE_SHEET_STATUS_APPROVED_2),
	//// AbstractContextServiceImpl.getParamMap("advanceShipmentNoticeId",
	// asn.getAdvanceShipmentNoticeId()));
	// CustomResponseDto resp = new CustomResponseDto(true, "Service Sheet
	// Approved");
	// resp.addObject("status", AppBaseConstant.SERVICE_SHEET_STATUS_APPROVED_2);
	//
	// return resp;
	//
	// }
	// else {
	// // result.getMessage();
	// // return new CustomResponseDto(false, result.getMessage());
	// return new CustomResponseDto(false, "Error While Approving Service Sheet");
	// }
	//// }else {
	//// return new CustomResponseDto(false, "Please enter login and password");
	////
	//// }
	// } catch (Exception e) {
	// e.printStackTrace();
	// return new CustomResponseDto(false, e.getMessage());
	// }
	//
	//
	//
	//
	//// int result = asnService.updateByJpql(
	//// AbstractContextServiceImpl.getParamMap("status",
	// AppBaseConstant.SERVICE_SHEET_STATUS_APPROVED_2),
	//// AbstractContextServiceImpl.getParamMap("advanceShipmentNoticeId",
	// asn.getAdvanceShipmentNoticeId()));
	//// if (result > 0) {
	//// CustomResponseDto resp = new CustomResponseDto(true, "Service Sheet
	// Approved");
	//// resp.addObject("status", AppBaseConstant.SERVICE_SHEET_STATUS_APPROVED_2);
	//// try {
	//// AdvanceShipmentNoticeDto advanceShipmentNoticeDto =
	// asnService.findDto("getAsnAndPartnerByASNId",
	//// AbstractContextServiceImpl.getParamMap("asnId",
	// asn.getAdvanceShipmentNoticeId()));
	//// /*
	//// * Map<String, Object> params = new HashMap<String, Object>();
	//// * params.put("value", AppBaseConstant.SSNVERSION); SystemConfiguratorDto
	//// * sysConfig=sysConfigService.findDto("getSysConfiguratorByType",params);
	//// * if(!"2".equals(sysConfig.getName())){
	//// * asnComponent.sendMailOnApproveServiceSheet(advanceShipmentNoticeDto); }
	//// */
	//// zmmServiceSheetClient.postServiceEntrySheet(advanceShipmentNoticeDto);
	//// } catch (Exception e) {
	//// e.printStackTrace();
	//// return new CustomResponseDto(false, e.getMessage());
	//// }
	//// return resp;
	//// } else {
	//// return new CustomResponseDto(false, "Error While Approving Service Sheet");
	//// }
	// }
	//

	@PostMapping(value = "/approveServiceSheet2")
	public @ResponseBody CustomResponseDto approveServiceSheet2(@RequestBody AdvanceShipmentNoticeDto asn) {
		boolean isCostCenterValidate = validateCostCenter(asn.getAsnLineList());
		if (!isCostCenterValidate) {
			return new CustomResponseDto(false, "Cost Center Quantity Did Not Matched Delivery Quantity ");
		}
		boolean status = asnLineService.updateCostCenter(asn.getAsnLineList());
		if (!status) {
			return new CustomResponseDto(false, "Error While Updating Cost Center");
		}

		// Create a trust manager that does not validate certificate chains
		TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
			public java.security.cert.X509Certificate[] getAcceptedIssuers() {
				return null;
			}

			public void checkClientTrusted(X509Certificate[] certs, String authType) {
			}

			public void checkServerTrusted(X509Certificate[] certs, String authType) {
			}
		} };

		// Install the all-trusting trust manager
		SSLContext sc = null;
		try {
			sc = SSLContext.getInstance("SSL");
		} catch (NoSuchAlgorithmException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			sc.init(null, trustAllCerts, new java.security.SecureRandom());
		} catch (KeyManagementException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

		// Create all-trusting host name verifier
		HostnameVerifier allHostsValid = new HostnameVerifier() {
			public boolean verify(String hostname, SSLSession session) {
				return true;
			}
		};

		// Install the all-trusting host verifier
		HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);

		String username = asn.getuser();
		String password = asn.getpass();
		Long asnId = asn.getAdvanceShipmentNoticeId();

	//	 String url="https://172.18.2.29:44300/sap/bc/yweb03_WS_65?sap-client=100&asnid="+asnId;
		String url = "https://172.18.2.36:44300/sap/bc/yweb03_WS_65?sap-client=100&asnid=" + asnId;

		System.out.println(url);

		URLConnection request = null;
		try {
			// URL u = new URL( URLEncoder.encode(url, "UTF-8"));
			URL u = new URL(url.replace(" ", "%20"));
			request = u.openConnection();
			request.setRequestProperty("Accept", "application/json");
			// snippet begins
			request.setRequestProperty("Authorization",
					"Basic " + Base64.getEncoder().encodeToString((username + ":" + password).getBytes()));
			request.connect();
			BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
			StringBuilder sb = new StringBuilder();
			String line;
			while ((line = br.readLine()) != null) {
				sb.append(line + "\n");
			}
			System.out.println(sb);
			br.close();

			JSONObject obj = new JSONObject(sb.toString());

			String message = obj.getString("message");
			
			if (message.equals("Not updated Unsuccessfully")) {
				
				String error = obj.getString("ERROR");

				return new CustomResponseDto(false, error);
				
			} else {
				//String doctype = obj.getString("ServicesheetNo");

				Map<String, Object> params = AbstractContextServiceImpl.getParamMap("status",
						AppBaseConstant.SERVICE_SHEET_STATUS_APPROVED_2);
				User ssnApprovedby = new User();
				ssnApprovedby.setUserId(contextService.getUser().getUserId());
				params.put("ssnApprovedBy", ssnApprovedby);
				params.put("ssnApprovedDate", new Date());
				// params.put("sap103Id",doctype);

				asnService.updateByJpql(params, AbstractContextServiceImpl.getParamMap("advanceShipmentNoticeId",
						asn.getAdvanceShipmentNoticeId()));
				CustomResponseDto resp = new CustomResponseDto(true,
						"Service Sheet Approved" + '\n' + " And Service Sheet No is created in SAP");
				resp.addObject("status", AppBaseConstant.SERVICE_SHEET_STATUS_APPROVED_2);

				asnComponent.sendMailOnServiceSheetApproval(asn.getAdvanceShipmentNoticeId());
				
				
				return resp;


			}
		} catch (MalformedURLException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
			// throw new RuntimeException("Error while writing file");
			return new CustomResponseDto(false, "Invalid SAP Login credentials");

		} finally {
			if (request != null) {
				try {
					((HttpURLConnection) request).disconnect();
				} catch (Exception ex) {
					ex.printStackTrace();

				}
			}

		}
		return null;

	}

	@PostMapping(value = "/getAsnByAsnId/{asnId}")
	public @ResponseBody CustomResponseDto getAsnByAsnId(@PathVariable("asnId") Long asn) {
		// AdvanceShipmentNoticeDto asndto=asnComponent.getAsnByAsnId(asn);
		AdvanceShipmentNoticeDto asndto = asnService.findDto("getASNByASNId",
				AbstractContextServiceImpl.getParamMap("asnId", asn));
		List<AdvanceShipmentNoticeLineDto> asnLineList = asnLineComponent.getASNLineForASNCreation(asn);
		CustomResponseDto resp = new CustomResponseDto("asnLineList", asnLineList);
		resp.addObject("asn", asndto);
		System.out.println(asndto.toString());
		return resp;
	}

	public ResponseDto validatePO(String PONO, Long asnId) {

		// Create a trust manager that does not validate certificate chains
		TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
			public java.security.cert.X509Certificate[] getAcceptedIssuers() {
				return null;
			}

			public void checkClientTrusted(X509Certificate[] certs, String authType) {
			}

			public void checkServerTrusted(X509Certificate[] certs, String authType) {
			}
		} };

		// Install the all-trusting trust manager
		SSLContext sc = null;
		try {
			sc = SSLContext.getInstance("SSL");
		} catch (NoSuchAlgorithmException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			sc.init(null, trustAllCerts, new java.security.SecureRandom());
		} catch (KeyManagementException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

		// Create all-trusting host name verifier
		HostnameVerifier allHostsValid = new HostnameVerifier() {
			public boolean verify(String hostname, SSLSession session) {
				return true;
			}
		};

		// Install the all-trusting host verifier
		HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);

		String url = "https://172.18.2.36:44300/sap/bc/yweb03_ws_21?sap-client=100&pono=" + PONO;
		// String url
		// ="https://172.18.2.29:44300/sap/bc/yweb03_ws_21?sap-client=100&pono=" + PONO;

		// String url =
		// "http://103.231.11.54:8000/sap/bc/yweb03_ws_21?sap-client=009&pono=6500019612";
		System.out.println(url);
		ResponseDto resp = new ResponseDto();
		URLConnection request = null;
		try {
			URL u = new URL(url);
			request = u.openConnection();
			request.connect();
			BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
			StringBuilder sb = new StringBuilder();
			String line;
			while ((line = br.readLine()) != null) {
				sb.append(line + "\n");
			}
			System.out.println(sb);
			br.close();

			JSONObject obj = new JSONObject(sb.toString());
			String Message = obj.getString("message");

			if (Message.equals("PO not yet released")) {

				resp.setMessage("PO not yet released ");
				resp.setHasError(true);

			} else if (Message.equals("PO NOT FOUND,PLS CHECK PO NO..")) {
				resp.setMessage("PO NOT FOUND,PLS CHECK PO NO..");
				resp.setHasError(true);
			}

			else {
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("asnId", asnId);
				List<AdvanceShipmentNoticeLineDto> asnlineList = asnLineService.findDtos("getASNLinesByASNId", params);
				// StringBuffer QuantityExceeded = new StringBuffer();

				for (AdvanceShipmentNoticeLineDto asnline : asnlineList)

				{
					// JSONArray headerArray = obj.getJSONArray("HEADER");
					JSONObject headerArray = obj.getJSONObject("HEADER");

					// for (int i = 0; i < headerArray.length(); i++) {
					// JSONObject currentObj = headerArray.getJSONObject(i);
					// {
					// for (int j = 0; j < headerArray.length(); j++) {
					JSONArray poLineListArray = headerArray.getJSONArray("poLineList");

					{

						for (int k = 0; k < poLineListArray.length(); k++) {

							JSONObject poobject = poLineListArray.getJSONObject(k);

							String poLineitemno = poobject.getString("lineItemNumber");
							Double balanceQuantity = poobject.getDouble("balanceQuantity");
							String asnLineNo = asnline.getPoLine().getLineItemNumber();
							Double deliveryQTY = asnline.getDeliveryQuantity();

							// if (asnLineNo.equals(poLineitemno)) {
							// if (deliveryQTY > balanceQuantity) {
							//
							// String string1 = "Delivery Quantity Exceeded in Line No "+ asnLineNo;
							// String string2 = " and Balance Quantity for the same is "+ balanceQuantity;
							//
							// // String string3="Line No "+ asnLineNo;
							// QuantityExceeded.append(string1);
							// QuantityExceeded.append(string2);
							// // QuantityExceeded.append(string3);
							// QuantityExceeded.append('\n');
							// resp.setMessage(QuantityExceeded.toString());
							// //resp.setHasError(true);
							//
							// } else {
							//
							// resp.setHasError(false);
							//
							// }
							// }

							/*
							 * try {
							 * 
							 * JSONArray serviceLineListArray = poobject.getJSONArray("serviceList"); { for
							 * (int l = 0; l < serviceLineListArray.length(); l++) { JSONObject
							 * serviceobject = serviceLineListArray.getJSONObject(l); Double
							 * BALANCEQUANTITY1 = serviceobject .getDouble("BALANCEQUANTITY1"); if
							 * (deliveryQTY >= BALANCEQUANTITY1) { resp.setHasError(false);
							 * 
							 * } else { resp.setMessage("Delivery Quantity Exceeded");
							 * resp.setHasError(true);
							 * 
							 * }
							 * 
							 * } } } catch (Exception e) { resp.setMessage("Service List is empty"); }
							 */
						}
					}
					// }

				}

			}

			// }
			// }

		} catch (MalformedURLException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (request != null) {
				try {
					((HttpURLConnection) request).disconnect();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}

		}
		return resp;
	}

	@PostMapping(value = "/createSTOASN/{outboundDeliverNo}")
	public @ResponseBody CustomResponseDto createSTOASN(@PathVariable("outboundDeliverNo") String outboundDeliverNo) {
		CustomResponseDto resp = new CustomResponseDto();

		Map<String, Object> params = new HashMap<String, Object>();

		// List<String> param = new ArrayList<>();

		params.put("outboundDeliverNo", outboundDeliverNo);

		// Create a trust manager that does not validate certificate chains
		TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
			public java.security.cert.X509Certificate[] getAcceptedIssuers() {
				return null;
			}

			public void checkClientTrusted(X509Certificate[] certs, String authType) {
			}

			public void checkServerTrusted(X509Certificate[] certs, String authType) {
			}
		} };

		// Install the all-trusting trust manager
		SSLContext sc = null;
		try {
			sc = SSLContext.getInstance("SSL");
		} catch (NoSuchAlgorithmException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			sc.init(null, trustAllCerts, new java.security.SecureRandom());
		} catch (KeyManagementException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

		// Create all-trusting host name verifier
		HostnameVerifier allHostsValid = new HostnameVerifier() {
			public boolean verify(String hostname, SSLSession session) {
				return true;
			}
		};

		// Install the all-trusting host verifier
		HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);

		// String
		// url="https://172.18.2.33:44300/sap/bc/yweb03_ws_35?sap-client=110&outno=" +
		// outboundDeliverNo;
		// String
		// url="https://172.18.2.28:44300/sap/bc/yweb03_ws_35?sap-client=100&outno=" +
		// outboundDeliverNo;
		String url = "https://172.18.2.36:44300/sap/bc/yweb03_ws_35?sap-client=100&outno=" + outboundDeliverNo;
		// String
		// url="https://172.18.2.29:44300/sap/bc/yweb03_ws_35?sap-client=100&outno=" +
		// outboundDeliverNo;
		// String
		// url="https://172.18.2.36:44300/sap/bc/yweb03_ws_21?sap-client=100&pono="+outboundDeliverNo+"&doctyp=STO";
		System.out.println(url);

		URLConnection request = null;
		try {
			URL u = new URL(url);
			request = u.openConnection();
			request.connect();
			BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
			StringBuilder sb = new StringBuilder();
			String line;
			while ((line = br.readLine()) != null) {
				sb.append(line + "\n");
			}
			System.out.println(sb);
			br.close();
			JSONObject obj = new JSONObject(sb.toString());
			// JSONObject headerArray = obj.getJSONObject("HEADER");
			System.out.println(obj);
			// System.out.println(headerArray);

			// String Message = obj.getString("message");
			//
			// if (Message.equals("Outbound Delivery Not Found,PLS CHECK ..")) {
			//
			// resp.setMessage("Outbound Delivery Not Found,PLS CHECK ..");
			// resp.setSuccess(false);
			//
			// }else {

			ObjectMapper objJson = new ObjectMapper();

			JSONObject headerArray = obj.getJSONObject("HEADER");
			objJson.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			System.out.println(obj);
			System.out.println(headerArray);

			// PurchaseOrderDto dto = objJson.readValue(headerArray.toString(),
			// PurchaseOrderDto.class);
			// List<PurchaseOrderDto> stoASNList= new ArrayList<PurchaseOrderDto>();
			// List<PurchaseOrderLineDto> stoASNlineList= dto.getPoLineList();
			//
			AdvanceShipmentNoticeDto dto = objJson.readValue(headerArray.toString(), AdvanceShipmentNoticeDto.class);
			List<AdvanceShipmentNoticeDto> stoASNList = new ArrayList<AdvanceShipmentNoticeDto>();
			List<AdvanceShipmentNoticeLineDto> stoASNlineList = dto.getAsnLineList();
			// dto.setStatus("ACPT");
			stoASNList.add(dto);
			// stoASNlineList.add((PurchaseOrderLineDto) dto.getPoLineList());

			resp.addObject("stoASNList", stoASNList);
			resp.addObject("stoASNlineList", stoASNlineList);

			// }

		} catch (MalformedURLException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (request != null) {
				try {
					((HttpURLConnection) request).disconnect();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}

		}

		return resp;

	}

	@PostMapping(value = "/asn101")
	public @ResponseBody CustomResponseDto asn101(@RequestBody AdvanceShipmentNoticeDto asn) throws ParseException {
		CustomResponseDto resp = new CustomResponseDto();
		// String pono=asn.getPo().getPurchaseOrderNumber();

		Errors errors = new Errors();

		validator.validateGrnDetails(asn, errors);
		if (errors.getErrorCount() > 0) {
			resp.setSuccess(false);
			resp.setMessage(errors.getErrorString());
			return resp;

		} else {

			boolean unloadStatus = false;
			try {
				Map<String, Object> param = AbstractContextServiceImpl.getParamMap("postingDate", asn.getPostingDate());
				// User grnPostedby= new User();
				// grnPostedby.setUserId(contextService.getUser().getUserId());
				// param.put("grnPostedby", grnPostedby);
				int result = asnService.updateByJpql(param, AbstractContextServiceImpl
						.getParamMap("advanceShipmentNoticeId", asn.getAdvanceShipmentNoticeId()));
				if (result > 0) {
					unloadStatus = asnLineComponent.unloadASN101(asn);
				} else {
					return new CustomResponseDto(false, "Error While 101 Posting");

				}
			} catch (Exception e) {
				return new CustomResponseDto(false, "Error While 101 Posting ");
				// return new CustomResponseDto(false, "GRN 103 is not processed yet in SAP");
			}

			if (unloadStatus) {
				resp = new CustomResponseDto(true, "101 Posted Successful");
				resp.addObject("status", AppBaseConstant.ASN_STATUS_101);
				return resp;
			} else {
				return new CustomResponseDto(false, "Error While 101 Posting ");
				// return new CustomResponseDto(false, "GRN 103 is not processed yet in SAP");
			}

		}

	}

	@PostMapping(value = "/rejectASN/{asnId}/{remark}")
	public @ResponseBody CustomResponseDto rejectASN(@PathVariable("asnId") Long asnId,
			@PathVariable("remark") String remark) {
		Map<String, Object> param = AbstractContextServiceImpl.getParamMap("status",
				AppBaseConstant.ASN_STATUS_REJECTED);
		param.put("rejectReason", remark);
		int result = asnService.updateByJpql(param,
				AbstractContextServiceImpl.getParamMap("advanceShipmentNoticeId", asnId));
		if (result > 0) {
			
			try {

				AdvanceShipmentNoticeDto advanceShipmentNoticeDto = asnService.findDto("getAsnAndpoByASNId",
						AbstractContextServiceImpl.getParamMap("asnId", asnId));

				int ASNNo = advanceShipmentNoticeDto.getAdvanceShipmentNoticeNo();
				String status = AppBaseConstant.ASN_STATUS_REJECTED;

				// Create a trust manager that does not validate certificate chains
				TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
					public java.security.cert.X509Certificate[] getAcceptedIssuers() {
						return null;
					}

					public void checkClientTrusted(X509Certificate[] certs, String authType) {
					}

					public void checkServerTrusted(X509Certificate[] certs, String authType) {
					}
				} };
				// Install the all-trusting trust manager
				SSLContext sc = null;
				try {
					sc = SSLContext.getInstance("SSL");
				} catch (NoSuchAlgorithmException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					sc.init(null, trustAllCerts, new java.security.SecureRandom());
				} catch (KeyManagementException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

				// Create all-trusting host name verifier
				HostnameVerifier allHostsValid = new HostnameVerifier() {
					public boolean verify(String hostname, SSLSession session) {
						return true;
					}
				};
				// Install the all-trusting host verifier
				HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);

				
//				String url = "https://172.18.2.29:44300/sap/bc/yweb03_WS_54?sap-client=100&ASNNO=" + ASNNo + "&STATUS="+ status;
				String url = "https://172.18.2.36:44300/sap/bc/yweb03_WS_54?sap-client=100&ASNNO=" + ASNNo + "&STATUS="+ status;
				

				System.out.println(url);
				// ResponseDto resp1 = new ResponseDto();
				URLConnection request = null;
				try {
					// URL u = new URL( URLEncoder.encode(url, "UTF-8"));
					URL u = new URL(url.replace(" ", "%20"));
					request = u.openConnection();
					request.setRequestProperty("Accept", "application/json");
					// snippet begins
					request.connect();
					BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
					StringBuilder sb = new StringBuilder();
					String line;
					while ((line = br.readLine()) != null) {
						sb.append(line + "\n");
					}
					System.out.println(sb);
					br.close();

					// JSONObject obj = new JSONObject(sb.toString());
				} catch (MalformedURLException ex) {
					ex.printStackTrace();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

			
			
			CustomResponseDto resp = new CustomResponseDto(true, "ASN Rejected.");
			resp.addObject("status", AppBaseConstant.ASN_STATUS_REJECTED);
			return resp;
		} else {
			return new CustomResponseDto(false, "Error While Rejecting ASN");
		}

	}

}
