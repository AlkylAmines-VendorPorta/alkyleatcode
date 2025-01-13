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
import java.util.Set;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.novelerp.appbase.master.dto.MailTemplateDto;
import com.novelerp.appbase.master.dto.PublicNoticeDto;
import com.novelerp.appbase.master.dto.ReferenceListDto;
import com.novelerp.appbase.master.service.DepartmentService;
import com.novelerp.appbase.master.service.MailTemplateService;
import com.novelerp.appbase.master.service.PublicNoticeService;
import com.novelerp.appbase.master.service.ReferenceListService;
import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.appcontext.dto.BPartnerDto;
import com.novelerp.appcontext.dto.UserDto;
import com.novelerp.appcontext.service.ContextService;
import com.novelerp.appcontext.service.UserService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.appcontext.util.ContextConstant;
import com.novelerp.commons.util.CommonUtil;
import com.novelerp.core.dto.CustomResponseDto;
import com.novelerp.core.dto.ErrorDto;
import com.novelerp.core.dto.Errors;
import com.novelerp.core.dto.ResponseDto;
import com.novelerp.core.exception.CustomException;
import com.novelerp.core.service.MailService;
import com.novelerp.core.service.impl.AbstractServiceImpl;
import com.novelerp.eat.dao.TAHDRApprovalMatrixDao;
import com.novelerp.eat.dao.TAHDRDao;
import com.novelerp.eat.dto.BidderDto;
import com.novelerp.eat.dto.ItemBidDto;
import com.novelerp.eat.dto.PriceBidDto;
import com.novelerp.eat.dto.TAHDRApprovalMatrixDto;
import com.novelerp.eat.dto.TAHDRDetailDto;
import com.novelerp.eat.dto.TAHDRDto;
import com.novelerp.eat.dto.TenderCommitteeDto;
import com.novelerp.eat.entity.SectionDocument;
import com.novelerp.eat.entity.TAHDR;
import com.novelerp.eat.entity.TAHDRDetail;
import com.novelerp.eat.entity.TAHDRMaterial;
import com.novelerp.eat.service.BidderService;
import com.novelerp.eat.service.ItemBidService;
import com.novelerp.eat.service.PriceBidService;
import com.novelerp.eat.service.TAHDRApprovalMatrixService;
import com.novelerp.eat.service.TAHDRDetailService;
import com.novelerp.eat.service.TAHDRMaterialService;
import com.novelerp.eat.service.TAHDRService;
import com.novelerp.eat.validator.TAHDRDatesValidator;

@Service
public class TAHDRServiceImpl extends AbstractContextServiceImpl<TAHDR, TAHDRDto> implements TAHDRService {

	@Autowired
	private TAHDRDao tahdrDao;

	@Autowired
	private DepartmentService deptService;

	@Autowired
	private ReferenceListService refListService;

	@Autowired
	 @Qualifier("jwtUserContext") private ContextService contextService;

	@Autowired
	private TAHDRDetailService tahdrDetailService;

	@Autowired
	private PublicNoticeService publicNoticeService;

	@Autowired
	private ItemBidService itemBidService;

	@Autowired
	private BidderService bidderService;

	@Autowired
	private TAHDRApprovalMatrixDao tahdrApprovalMatrixDao;
	
	@Autowired
	private TAHDRApprovalMatrixService tahdrApprovalMatrixService;
	
	@Autowired
	private TAHDRMaterialService tAHDRMaterialService;

	@Autowired
	private PriceBidService priceBidService;
	@Autowired
	private TAHDRDatesValidator tahdrDatesValidation;
	
	@Autowired
	private MailTemplateService mailTemplateService;
	
	@Autowired
	private UserService userService;

	@Autowired
	private MailService mailService;
	
	private Logger Log =  LoggerFactory.getLogger(TAHDRServiceImpl.class);
	
	@PostConstruct
	private void init() {
		super.init(TAHDRServiceImpl.class, tahdrDao, TAHDR.class, TAHDRDto.class);
		setByPassProxy(true);
	}

	@Override
	public boolean beforeSave(TAHDRDto dto) {
		try {
			dto.setDepartment(deptService.findDto("getDepartmentById",
					getParamMap("departmentId", dto.getDepartment().getDepartmentId())));
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	@Override
	public boolean isTahdrExists(String code) {
		TAHDRDto tahdr = null;
		try {
			tahdr = findDto("getQueryForTAHDRByCode", getParamMap("tahdrCode", code));
			if (null != tahdr) {
				return true;
			} else {
				return false;
			}
		} catch (Exception ex) {
			return false;
		}
	}

	@Override
	public long getTahdrListQueryCount(Map<String, Object> map,String searchColumn, String searchValue) {
		long totalCount;
		String queryString =tahdrDao.getTahdrListQueryCountQry(searchColumn, searchValue);
		if (!"none".equalsIgnoreCase(searchValue)) {
			map.put("searchValue", "%" + searchValue.toUpperCase() + "%");
			totalCount = getRecordCount(queryString, map);
		}else{
			totalCount = getRecordCount(queryString, map);
		}
		return totalCount;
	}
	
	@Override
	public List<TAHDRDto> getTahdrList(Map<String, Object> map, int pageNumber, int pageSize,String searchColumn, String searchValue) {
		String queryString = tahdrDao.getTahdrListByRoleANDLoc(searchColumn, searchValue);
		if (!"none".equalsIgnoreCase(searchValue)) {
			map.put("searchValue", "%" + searchValue.toUpperCase() + "%");
		} 
		List<TAHDRDto> tahdrEntity = findDtosByQuery(queryString, map, pageNumber, pageSize);
		return tahdrEntity;
	}

	@Override
	public long getTahdrApprovalListQueryCount(Map<String, Object> map,String searchColumn, String searchValue) {
		long totalCount;
		String queryString =tahdrDao.getTahdrApprovalListQueryCountQry(searchColumn, searchValue);
		if (!"none".equalsIgnoreCase(searchValue)) {
			map.put("searchValue", "%" + searchValue.toUpperCase() + "%");
			totalCount = getRecordCount(queryString, map);
		}else{
			totalCount = getRecordCount(queryString, map);
		}
		return totalCount;
	}
	
	
	@Override
	public List<TAHDRDto> getTahdrApprovalList(Map<String, Object> map, int pageNumber, int pageSize,String searchColumn, String searchValue) {
		String queryString = tahdrDao.getTahdrListFromApprovalMatrix(searchColumn, searchValue);
		if (!"none".equalsIgnoreCase(searchValue)) {
			map.put("searchValue", "%" + searchValue.toUpperCase() + "%");
		} 
		List<TAHDRDto> tahdrEntity = findDtosByQuery(queryString, map, pageNumber, pageSize);
		return tahdrEntity;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public ResponseDto submitTahdrDetails(TAHDRDto tahdrDto, UserDto current, UserDto next,
			TAHDRDetailDto tahdrDetaildto) {
		ResponseDto response = new ResponseDto();
		BPartnerDto partnerId = new BPartnerDto();
		BPartnerDto partnerDto = contextService.getPartner();
		partnerId.setbPartnerId(partnerDto.getbPartnerId());
		TAHDR tahdr = tahdrDao.getAllTahdrDetails(tahdrDto.getTahdrId());
		Map<String, Object> map = new HashMap<>();

		if (tahdrDto.getTahdrStatusCode().equals("VO")) {
			tahdr.setTahdrStatusCode(tahdrDto.getTahdrStatusCode());
			response.setMessage(tahdrDto.getTahdrStatusCode());
			response.setHasError(false);
			return response;
		}
		if (tahdr.getTahdrDetail().iterator().next().getTenderDoc() != null) {
			if (tahdrDto.getTahdrStatusCode().equals("IP")) {
				List<ErrorDto> errors = checkTahdrDetails(tahdr);
				if(!"RFQ".equalsIgnoreCase(tahdr.getTahdrTypeCode())){
					errors.addAll(checkForSaleDate(tahdrDetaildto));	
				}
				if (CommonUtil.isListEmpty(errors)) {
					response.setHasError(false);
					inProgress(tahdrDto);
					
					Map<String, Object> officeNote = new HashMap<>();
					if (tahdrDto.getTahdrDetailList().iterator().next().getOfficeNote().getAttachmentId()!=null) {
						officeNote.put("officeNote.attachmentId",tahdrDto.getTahdrDetailList().iterator().next().getOfficeNote().getAttachmentId());
						tahdrDetailService.updateByJpql(officeNote, "tahdrDetailId",tahdrDto.getTahdrDetailList().iterator().next().getTahdrDetailId());
					}
					map.put("tahdrStatusCode", tahdrDto.getTahdrStatusCode());
					response.setMessage(tahdrDto.getTahdrStatusCode());
					updateByJpql(map, "tahdrId", tahdrDto.getTahdrId());
				} else {
					response.setHasError(true);
					response.setErrors(errors);
				}
			} else if (tahdrDto.getTahdrStatusCode().equals("AP") || tahdrDto.getTahdrStatusCode().equals("RJ")) {
				Errors errorsDate = new Errors();
				if(tahdrDetaildto.getVersion()>1 && tahdrDetaildto.getIsActive().equalsIgnoreCase("Y")){
					tahdrDatesValidation.checkForActiveCreateVersion(tahdrDetaildto, errorsDate);
				}else if("RFQ".equalsIgnoreCase(tahdr.getTahdrTypeCode())){
						   tahdrDetailService.validate(tahdrDetaildto, errorsDate);
				}
				else{
					tahdrDatesValidation.validate(tahdrDetaildto, errorsDate);
				}

				if (errorsDate.getErrorCount() > 0) {
					response.setHasError(true);
					response.setErrors(errorsDate.getErrorList());
					tahdrDto.setResponse(response);
					return response;
				}
				else{
					response.setHasError(false);
					approveReject(tahdrDto, current, next);
					Map<String, Object> officeNote = new HashMap<>();
					if (tahdrDto.getTahdrDetailList().iterator().next().getOfficeNote().getAttachmentId()!=null) {
					officeNote.put("officeNote.attachmentId",tahdrDto.getTahdrDetailList().iterator().next().getOfficeNote().getAttachmentId());
					tahdrDetailService.updateByJpql(officeNote, "tahdrDetailId",tahdrDto.getTahdrDetailList().iterator().next().getTahdrDetailId());
				}
				response.setMessage(tahdrDto.getTahdrStatusCode());
				}
			} else if (tahdrDto.getTahdrStatusCode().equals("PU")) {
				List<ErrorDto> errors = checkTahdrDetails(tahdr);
				if (CommonUtil.isListEmpty(errors)) {

					Errors errorsDate = new Errors();
					if(tahdrDetaildto.getVersion()>1 && tahdrDetaildto.getIsActive().equalsIgnoreCase("Y")){
						tahdrDatesValidation.checkForActiveCreateVersion(tahdrDetaildto, errorsDate);
					}else if("RFQ".equalsIgnoreCase(tahdr.getTahdrTypeCode())){
					   tahdrDetailService.validate(tahdrDetaildto, errorsDate);
					}else{
						tahdrDatesValidation.validate(tahdrDetaildto, errorsDate);
					}

					if (errorsDate.getErrorCount() > 0) {
						response.setHasError(true);
						response.setErrors(errorsDate.getErrorList());
						tahdrDto.setResponse(response);
						return response;
					} else {
						response.setHasError(false);
						if (!CommonUtil.isCollectionEmpty(tahdrDto.getTahdrDetailList())) {
							Map<String, Object> mapValue = new HashMap<>();
							mapValue.put("publishingDate",tahdrDto.getTahdrDetailList().iterator().next().getPublishingDate());
							mapValue.put("partner.bPartnerId", partnerId.getbPartnerId());
							if (tahdrDto.getTahdrDetailList().iterator().next().getOfficeNote().getAttachmentId()!=null) {
								mapValue.put("officeNote.attachmentId",tahdrDto.getTahdrDetailList().iterator().next().getOfficeNote().getAttachmentId());
							}
							tahdrDetailService.updateByJpql(mapValue, "tahdrDetailId",tahdrDto.getTahdrDetailList().iterator().next().getTahdrDetailId());

							TAHDRDto tahdrId = new TAHDRDto();
							tahdrId.setTahdrId(tahdrDto.getTahdrId());
							map.put("tahdrStatusCode", tahdrDto.getTahdrStatusCode());
							map.put("remarks", tahdrDto.getRemarks());
							map.put("partner.bPartnerId", partnerId.getbPartnerId());
							updateByJpql(map, "tahdrId", tahdrDto.getTahdrId());
							response.setMessage(tahdrDto.getTahdrStatusCode());

							PublicNoticeDto publicNoticeDto = new PublicNoticeDto();
							publicNoticeDto.setPublishingDate(
									tahdrDto.getTahdrDetailList().iterator().next().getPublishingDate());
							publicNoticeDto.setTahdr(tahdrId);
							publicNoticeDto.setPartner(partnerId);
							if (tahdrDto.getRemarks() != ""){
								publicNoticeDto.setDescription(tahdrDto.getRemarks());
							}
							if (tahdrDto.getTahdrDetailList().iterator().next().getOfficeNote().getAttachmentId()!=null) {
								publicNoticeDto.setAttachment(tahdrDto.getTahdrDetailList().iterator().next().getOfficeNote());
							}	
							publicNoticeService.save(publicNoticeDto);
						}
					}
				} else {
					response.setHasError(true);
					response.setErrors(errors);
				}
			}
		} else {
			response.setHasError(true);
			response.setMessage("Generate the document by clicking on the Generate Document Button.");
		}

		return response;

	}
	
	private List<ErrorDto>  checkForSaleDate(TAHDRDetailDto tahdrDetail){
		List<ErrorDto> errors = new ArrayList<ErrorDto>();
		/*if(tahdrDetail.getPurchaseFromDate()==null || tahdrDetail.getPurchaseToDate()==null)
		{
			ErrorDto err = new ErrorDto();
			err.setErrorMessage("Sale End Date is Mandatory");
			errors.add(err);
		}
		else if(tahdrDetail.getPurchaseFromDate().compareTo(tahdrDetail.getPurchaseToDate())==0)
		{
			ErrorDto err = new ErrorDto();
			err.setErrorMessage("Sale End Date should not be equal to Sale Start Date");
			errors.add(err);
		}
		else if(tahdrDetail.getPurchaseFromDate().compareTo(tahdrDetail.getPurchaseToDate())==1)
		{
			ErrorDto err = new ErrorDto();
			err.setErrorMessage("Sale End Date should be after Sale Start Date");
			errors.add(err);
		}*/
		if(new Date().after(tahdrDetail.getPurchaseFromDate()))
		{
			ErrorDto err = new ErrorDto();
			err.setErrorMessage("Sale Start Date should be after tender publised");
			errors.add(err);
		}
		/*else if(tahdrDetail.getPurchaseFromDate().compareTo(tahdrDetail.getPurchaseToDate())==-1)
		{
			ErrorDto err = new ErrorDto();
			err.setErrorMessage("Sale End Date is Mandatory");
			errors.add(err);
			return;
		}*/
		return errors;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	private void approveReject(TAHDRDto tahdrDto, UserDto current, UserDto next) {

		if (AppBaseConstant.DOCUMENT_STATUS_APPROVED.equals(tahdrDto.getTahdrStatusCode())) {
			approve(tahdrDto, current, next);
		}
		if (AppBaseConstant.DOCUMENT_STATUS_REJECTED.equals(tahdrDto.getTahdrStatusCode())) {
			reject(tahdrDto, current);
		}

		if (!tahdrDto.getTahdrStatusCode().equalsIgnoreCase(AppBaseConstant.DOCUMENT_STATUS_REJECTED)) {
			if (next != null) {
				return;
			}
		}
		Map<String, Object> map = new HashMap<>();
		Map<String, Object> officeNote = new HashMap<>();
		if (!CommonUtil.isStringEmpty(tahdrDto.getRemarks())) {
			map.put("remarks", tahdrDto.getRemarks());
		}
		map.put("tahdrStatusCode", tahdrDto.getTahdrStatusCode());
		officeNote.put("officeNote.attachmentId",
				tahdrDto.getTahdrDetailList().iterator().next().getOfficeNote().getAttachmentId());
		tahdrDetailService.updateByJpql(officeNote, "tahdrDetailId",
				tahdrDto.getTahdrDetailList().iterator().next().getTahdrDetailId());
		updateByJpql(map, "tahdrId", tahdrDto.getTahdrId());

	}

	private void approve(TAHDRDto tahdrDto, UserDto current, UserDto next) {
		tahdrApprovalMatrixDao.updateStatusOfCurrentUser(tahdrDto.getTahdrId(), current.getUserId(),
				tahdrDto.getRemarks(), tahdrDto.getTahdrStatusCode());
		if (next != null) {
			tahdrApprovalMatrixDao.updateStatusOfNextUser(tahdrDto.getTahdrId(), next.getUserId());
		}
		return;
	}

	private void reject(TAHDRDto tahdrDto, UserDto current) {
		tahdrApprovalMatrixDao.updateStatusOfCurrentUser(tahdrDto.getTahdrId(), current.getUserId(),
				tahdrDto.getRemarks(), tahdrDto.getTahdrStatusCode());
		return;
	}

	private void inProgress(TAHDRDto tahdrDto) {
		tahdrApprovalMatrixDao.updateStatusOfFirstUserIP(tahdrDto.getTahdrId());
		return;
	}

	@Override
	public ResponseDto changeStatusFromRJtoDR(Long tahdrId) {
		ResponseDto response = new ResponseDto();
		if (tahdrId != null) {
			statusChangeToDR(tahdrId);
		}
		return response;
	}

	private void statusChangeToDR(Long tahdrId) {
		tahdrApprovalMatrixDao.updateStatusOfUserToNull(tahdrId);
		return;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.novelerp.eat.service.TAHDRService#getAccessClause(java.lang.String)
	 */
	@Override
	public List<String> getAccessClause(String role) {
		List<String> accessClause = new ArrayList<>();
		if (!(role == null) && !role.equals("")) {
			switch (role) {
			case ContextConstant.USER_TYPE_EXECUTIVE_ENGINEER:
				accessClause.add(AppBaseConstant.DOCUMENT_STATUS_PUBLISHED);
				accessClause.add(AppBaseConstant.DOCUMENT_STATUS_DRAFTED);
				accessClause.add(AppBaseConstant.DOCUMENT_STATUS_IN_PROGRESS);
				accessClause.add(AppBaseConstant.DOCUMENT_STATUS_COMPLETE);
				accessClause.add(AppBaseConstant.DOCUMENT_STATUS_TC_BID_OPENED);
				accessClause.add(AppBaseConstant.DOCUMENT_STATUS_VOID);
				accessClause.add(AppBaseConstant.DOCUMENT_STATUS_APPROVED);
				accessClause.add(AppBaseConstant.DOCUMENT_STATUS_REJECTED);
				break;

			case ContextConstant.USER_TYPE_SYSUSER:
				accessClause.add(AppBaseConstant.DOCUMENT_STATUS_PUBLISHED);
				accessClause.add(AppBaseConstant.DOCUMENT_STATUS_DRAFTED);
				accessClause.add(AppBaseConstant.DOCUMENT_STATUS_IN_PROGRESS);
				accessClause.add(AppBaseConstant.DOCUMENT_STATUS_COMPLETE);
				accessClause.add(AppBaseConstant.DOCUMENT_STATUS_TC_BID_OPENED);
				accessClause.add(AppBaseConstant.DOCUMENT_STATUS_VOID);
				accessClause.add(AppBaseConstant.DOCUMENT_STATUS_APPROVED);
				accessClause.add(AppBaseConstant.DOCUMENT_STATUS_REJECTED);
				break;

			case ContextConstant.USER_TYPE_CHIEF_ENGINEER:
				accessClause.add(AppBaseConstant.DOCUMENT_STATUS_IN_PROGRESS);
				break;

			case ContextConstant.USER_TYPE_VENDOR_ADMIN:
				accessClause.add(AppBaseConstant.DOCUMENT_STATUS_PUBLISHED);
				accessClause.add(AppBaseConstant.DOCUMENT_STATUS_TC_BID_OPENED);
				accessClause.add(AppBaseConstant.DOCUMENT_STATUS_SCRUTINY);
				accessClause.add(AppBaseConstant.DOCUMENT_STATUS_FINAL_SCRUTINY);
				accessClause.add(AppBaseConstant.DOCUMENT_STATUS_COMPLETE);
				accessClause.add(AppBaseConstant.DOCUMENT_STATUS_PRICE_BID_OPENED);
				accessClause.add(AppBaseConstant.DOCUMENT_STATUS_C1_BID_OPENED);
				accessClause.add(AppBaseConstant.DOCUMENT_STATUS_DEVIATION_BID_OPENED);
				break;

			case ContextConstant.USER_TYPE_SCRUTINY_ENGINEER:
				accessClause.add(AppBaseConstant.DOCUMENT_STATUS_TC_BID_OPENED);
				accessClause.add(AppBaseConstant.DOCUMENT_STATUS_DEVIATION_BID_OPENED);
				accessClause.add(AppBaseConstant.TENDER_DEVIATION_BID_SCHEDULING);
				accessClause.add(AppBaseConstant.SCRUTINY_FINISHED);
				break;

			case ContextConstant.USER_TYPE_AUDITOR:
				accessClause.add(AppBaseConstant.DOCUMENT_STATUS_TC_BID_OPENED);
				accessClause.add(AppBaseConstant.DOCUMENT_STATUS_DEVIATION_BID_OPENED);
				break;

			default:
				break;
			}
		}
		return accessClause;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.novelerp.eat.service.TAHDRService#getAccessAction(java.lang.String)
	 */
	@Override
	public List<ReferenceListDto> getAccessAction(String role) {
		Map<String, ReferenceListDto> documentStatus = refListService
				.getRefListCollection(AppBaseConstant.DOCUMENT_STATUS);
		List<ReferenceListDto> accessAction = new ArrayList<>();
		if (!(role == null) && !role.equals("")) {
			/*switch (role) {
			case ContextConstant.USER_TYPE_EXECUTIVE_ENGINEER:
				accessAction.add(documentStatus.get(AppBaseConstant.DOCUMENT_STATUS_PUBLISHED));
				accessAction.add(documentStatus.get(AppBaseConstant.DOCUMENT_STATUS_IN_PROGRESS));
				accessAction.add(documentStatus.get(AppBaseConstant.DOCUMENT_STATUS_VOID));
				break;

			case ContextConstant.USER_TYPE_SYSUSER:
				accessAction.add(documentStatus.get(AppBaseConstant.DOCUMENT_STATUS_PUBLISHED));
				accessAction.add(documentStatus.get(AppBaseConstant.DOCUMENT_STATUS_IN_PROGRESS));
				accessAction.add(documentStatus.get(AppBaseConstant.DOCUMENT_STATUS_VOID));
				break;

			case ContextConstant.USER_TYPE_CHIEF_ENGINEER:
				accessAction.add(documentStatus.get(AppBaseConstant.DOCUMENT_STATUS_APPROVED));
				accessAction.add(documentStatus.get(AppBaseConstant.DOCUMENT_STATUS_REJECTED));
				accessAction.add(documentStatus.get(AppBaseConstant.DOCUMENT_STATUS_VOID));
				break;

			default:
				break;
			}*/
				accessAction.add(documentStatus.get(AppBaseConstant.DOCUMENT_STATUS_PUBLISHED));
				accessAction.add(documentStatus.get(AppBaseConstant.DOCUMENT_STATUS_IN_PROGRESS));
				accessAction.add(documentStatus.get(AppBaseConstant.DOCUMENT_STATUS_VOID));
				accessAction.add(documentStatus.get(AppBaseConstant.DOCUMENT_STATUS_APPROVED));
				accessAction.add(documentStatus.get(AppBaseConstant.DOCUMENT_STATUS_REJECTED));
				accessAction.add(documentStatus.get(AppBaseConstant.DOCUMENT_STATUS_VOID));

		}
		return accessAction;
	}

	public boolean validateAccessAction(String action) {
		contextService.getRoles();
		List<ReferenceListDto> accessAction = getAccessAction(contextService.getDefaultRole().getValue());
		for (ReferenceListDto actionAllowed : accessAction) {
			if (actionAllowed.getCode().equals(action)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public List<ErrorDto> checkTahdrDetails(TAHDR tahdr) {
		List<ErrorDto> errors = new ArrayList<ErrorDto>();

		if (CommonUtil.isCollectionEmpty(tahdr.getTahdrDetail())) {
			ErrorDto err = new ErrorDto();
			err.setErrorMessage("Add Tender Details");
			errors.add(err);
		} else if (tahdr.getTahdrDetail().iterator().next().getDatesSubmitted() == null) {
			ErrorDto err = new ErrorDto();
			err.setErrorMessage("Add Tender Dates");
			errors.add(err);
		} else {
			errors.addAll(checktenderMaterialDetails(tahdr.getTahdrDetail()));
		}

		return errors;
	}

	private List<ErrorDto> checktenderMaterialDetails(Set<TAHDRDetail> tahdrDetail) {
		List<ErrorDto> errors = new ArrayList<ErrorDto>();
		for (TAHDRDetail tenderDetail : tahdrDetail) {
			if (CommonUtil.isCollectionEmpty(tenderDetail.getTahdrMaterial())) {
				ErrorDto err = new ErrorDto();
				err.setErrorMessage("Add Atleast one Material");
				errors.add(err);
			} else if (tenderDetail.getTahdr().getTahdrTypeCode().equals("WT")) {
				errors.addAll(checkRequiredDocs(tenderDetail.getTahdrMaterial(),tenderDetail.getSectionDocument()));
				return errors;
			} else if (tenderDetail.getTahdr().getTahdrTypeCode().equals("FA") || tenderDetail.getTahdr().getTahdrTypeCode().equals("RA") ) {
				return errors;
			} else {
				if(tenderDetail.getIsGTP()!=null && tenderDetail.getIsGTP().equals("Y")){
					errors.addAll(checktenderMaterialGTP(tenderDetail.getTahdrMaterial()));
				}
				
			}

		}
		return errors;
	}

	private List<ErrorDto> checktenderMaterialGTP(Set<TAHDRMaterial> tahdrMaterial) {
		List<ErrorDto> errors = new ArrayList<ErrorDto>();
		for (TAHDRMaterial tenderMat : tahdrMaterial) {
			if (CommonUtil.isCollectionEmpty(tenderMat.getMaterialGtpList())) {
				ErrorDto err = new ErrorDto();
				err.setErrorMessage(tenderMat.getMaterialDescription() + "- Add alteast one GTP in this item");
				errors.add(err);
			}
		}
		return errors;
	}
	
	private List<ErrorDto> checkRequiredDocs(Set<TAHDRMaterial> tahdrMaterial,Set<SectionDocument> sectionDocument) {
		List<ErrorDto> errors = new ArrayList<ErrorDto>();
		
		boolean techSec = false;
		boolean priceSec = false;
		for (TAHDRMaterial tenderMat : tahdrMaterial) {
			 ErrorDto err = new ErrorDto();
			if (!CommonUtil.isCollectionEmpty(tenderMat.getSectionDocumentSet())) {
			for(SectionDocument secDocument : sectionDocument){
				if(!secDocument.getCode().equalsIgnoreCase(AppBaseConstant.COMMERCIAL_SECTION)){
				if(secDocument.getTahdrMaterial().getTahdrMaterialId()==tenderMat.getTahdrMaterialId())
				{
					if(secDocument.getCode().equalsIgnoreCase(AppBaseConstant.TECHNICAL_SECTION)){
						techSec = true;	
					}
					if(secDocument.getCode().equalsIgnoreCase(AppBaseConstant.PRICE_BID)){
						priceSec= true;
					}
				}
				}
			}
					 if(techSec== true && priceSec==true){
						 System.out.println("Proper");
					 }
					 else{
						
						 if(techSec == false){
						 err.setErrorMessage(tenderMat.getMaterialDescription() + "- Add alteast one Technical section for this item. "+"\n");
						 errors.add(err);
						 }
						 if(priceSec == false){
						 err.setErrorMessage(tenderMat.getMaterialDescription() + "- Add alteast one Price section for this item. "+"\n");
						 errors.add(err);
						 }
					 }
					 techSec=false;
					 priceSec=false;
			
		}
			else{
				 err.setErrorMessage(tenderMat.getMaterialDescription() + "- Add alteast one Price section and Technical Section for this item. "+"\n");
				 errors.add(err);
			}
		}
		return errors;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public CustomResponseDto openingTender(TAHDRDto tahdr, List<ItemBidDto> itemBidList, List<PriceBidDto> pbList) {
		CustomResponseDto response = new CustomResponseDto();
		String resultMsg = "";
		boolean resultStatus = false;
		String status = tahdr.getTahdrStatusCode().equalsIgnoreCase("SBPBOP")?"PBOP":tahdr.getTahdrStatusCode();
		
		String bidTypeCode = tahdr == null ? "" : tahdr.getBidTypeCode();
		String tenderTypeCode = tahdr.getTahdrTypeCode();
		String selectedTenderIsAuction=tahdr.getIsAuction();
		String remarks = tahdr.getRemarks();
		Long tahdrId = tahdr.getTahdrId();
		/* Map<String,Object> map= new HashMap<>(); */
		int result = 0;
		/*
		 * map.put("tahdrStatusCode",status); map.put("remarks",remarks);
		 * map.put("bidOpenedDate","SYSDATE");
		 */
		if (status.equals(AppBaseConstant.DOCUMENT_STATUS_VOID)) {
			resultStatus = openTender(tahdrId, status, remarks);
			if (resultStatus) {
				resultMsg = "Tender has been Canceled Successfully";
				resultStatus = true;
			} else {
				resultMsg = "Tender not Canceled";
				resultStatus = false;
			}
		} else {
			if (status.toUpperCase().equals("PBOP") ) {
				String oldStatus = "";

				if (!CommonUtil.isListEmpty(itemBidList)) {
					if (bidTypeCode.equalsIgnoreCase("SB")) {
						result = itemBidService.updateItemBidStatusByPBOpeningTypeForSingle(status, oldStatus, tahdrId);
					} else {
						result = itemBidService.updateItemBidStatusByPBOpeningTypeForTwo(status, oldStatus, tahdrId);
					}
					if (result > 0) {
						if (bidTypeCode.equalsIgnoreCase("SB")) {
							result = bidderService.updateBidderStatusBySBPBOpeningType(status, oldStatus, tahdrId);
						} else {
							result = bidderService.updateBidderStatusByPBOpeningType(status, oldStatus, tahdrId);
						}
						
						if (result > 0) {
							resultStatus = openTender(tahdrId, "PBOP", remarks);
							if (resultStatus) {
								if (!tenderTypeCode.equals("WT")) {
									priceBidService.saveEncryptedBidBeforeOpening(tahdrId, AppBaseConstant.PRICE_BID_SCRAP_AFTER_OPENING); // save all bid in temp
									boolean bidsDecrypted = priceBidService.decryptAllPb(pbList);
									
									if (bidsDecrypted) {
										if(!tenderTypeCode.equals("WT")){
											boolean isLowestBidSelected=itemBidService.selectHighOrLowItemBidByTahdrId(tahdrId,tenderTypeCode,selectedTenderIsAuction);
											if(isLowestBidSelected){
												tAHDRMaterialService.updateBaseRate(tahdrId);  // set base rate in Table:t_tahdr_material
												resultMsg="Price Bid Opened SuccessFully And Lowest bid Selected !";
												resultStatus = true;
											}else{
												resultMsg="Price Bid Opened SuccessFully But Lowest bid Not Selected";
												resultStatus = true;
											}
										/*resultMsg = "Price Bid Opened Successfully";
										resultStatus = true;*/
									} else{
										resultMsg = "Price Bid Not Opened";
										throw new CustomException("Tender Status Not updated/BAserate not set");
									}
										
									}else {
										resultMsg = "Price Bid Not Opened";
										throw new CustomException("Tender Status Not updated/PriceBid not Decrypted");
									}
								} else {
									resultMsg = "Price Bid Opened Successfully";
									resultStatus = true;
								}
							} else {
								resultMsg = "Tender Status Not updated";
								throw new CustomException("Tender Status Not updated");
							}
						} else {
							resultMsg = "No Bidder Found For Price Opening";
							throw new CustomException("No Bidder Found For Price Opening");
						}
					} else {
						resultMsg = "No Item Found For Price Opening";
						throw new CustomException("No Item Found For Price Opening");
					}
				} else
					resultMsg = "No Item Found Passed For Price Opening";
			} else if (status.toUpperCase().equals("TCOP")) {
				String oldStatus = AppBaseConstant.BIDDER_STATUS_BID_SUBMITED;

				if (!CommonUtil.isListEmpty(itemBidList)) {
					result = itemBidService.updateItemBidStatusByOpeningType(status, oldStatus, tahdrId);
					if (result > 0) {
						result = bidderService.updateBidderStatusByOpeningType(status, oldStatus, tahdrId);
						if (result > 0) {
							resultStatus = openTender(tahdrId, status, remarks);
							if (resultStatus) {
								resultMsg = "Techno-Commercial Bid has Opened Successfully";
								resultStatus = true;
							} else {
								resultMsg = "Tender Status Not updated";
								throw new CustomException("Tender Status Not updated");
							}
						} else {
							resultMsg = "No Bidder Found For Techno-Commercial Opening";
							throw new CustomException("No Bidder Found For Techno-Commercial Opening");
						}
					} else {
						resultMsg = "No Item Found For Techno-Commercial Opening";
						throw new CustomException("No Item Found For Techno-Commercial Opening");
					}
				} else
					resultMsg = "No Item Found Passed For Techno-Commercial Opening";

			} else if (status.toUpperCase().equals("DBOP")) {
				String oldStatus = AppBaseConstant.BIDDER_STATUS_DEVIATION_SUBMITTED;

					result = itemBidService.updateItemBidStatusByOpeningType(status, oldStatus, tahdrId);
					result = bidderService.updateBidderStatusByOpeningType(status, oldStatus, tahdrId);
					if (result > 0) {
						if (result > 0) {
							resultStatus = openTender(tahdrId, status, remarks);
							if (resultStatus) {
								resultMsg = "Deviation Bid has Opened Successfully";
								resultStatus = true;
							} else {
								resultMsg = "Tender Status Not updated";
								throw new CustomException("Tender Status Not updated");
							}
						} else {
							resultMsg = "No Bidder Found For Deviation Opening";
							throw new CustomException("No Bidder Found For Deviation Opening");
						}
					} else {
						resultMsg = "No Item Found For Deviation Opening";
						throw new CustomException("No Item Found For Deviation Opening");
					}

			} else if (status.toUpperCase().equals("C1OP")) {
				String oldStatus = AppBaseConstant.BIDDER_STATUS_ANNEXURE_C1_SUBMITED;
				if (!CommonUtil.isListEmpty(itemBidList)) {
					result = itemBidService.updateItemBidStatusByOpeningType(status, oldStatus, tahdrId);
					if (result > 0) {
						result = bidderService.updateBidderStatusByOpeningType(status, oldStatus, tahdrId);
						if (result > 0) {
							resultStatus = openTender(tahdrId, status, remarks);
							if (resultStatus) {
								resultMsg = "Annexure C1 Bid is Opened Successfully";
								resultStatus = true;
							} else {
								resultMsg = "Tender Status Not updated";
								throw new CustomException("Tender Status Not updated");
							}
						} else {
							resultMsg = "No Bidder Found For Annexure C1 Opening";
							throw new CustomException("No Bidder Found For Annexure C1 Opening");
						}
					} else {
						resultMsg = "No Item Found For Annexure C1 Opening";
						throw new CustomException("No Item Found For Annexure C1 Opening");
					}
				} else
					resultMsg = "No Item Found Passed For Annexure C1 Opening";

			}
		}
		response.addObject("bidTypeCode", bidTypeCode);
		response.addObject("result", resultStatus);
		response.addObject("resultMessage", resultMsg);
		response.addObject("openingType", tahdr.getTahdrStatusCode());
		return response;
	}

	/*@Override
	public CustomResponseDto getBidderByOpeningType(Long tahdrId, Long tahdrMaterialId) {
		CustomResponseDto response = new CustomResponseDto();
		Map<String, Object> params = new HashMap<>();
		params.put("tahdrId", tahdrId);

		TAHDRDto tahdr = findDto("getTenderListsByTahdrId", params);
		String status = tahdr.getTahdrStatusCode();
		if (status.equals(AppBaseConstant.BIDDER_STATUS_TECHNO_COMMERCIAL_OPENED)) {
			params.put("tahdrMaterialId", tahdrMaterialId);
			
			List<TechnicalBidDto> bidderForTCList = technicalBidService.findDtos("getTCBidderListByTahdrId", params);
			List<BidderDto> bidderForTCList = bidderService.findDtos("getTCBidderListByTahdrId", params);
			response.addObject("responseList", bidderForTCList);
			response.addObject("openingType", AppBaseConstant.BIDDER_STATUS_TECHNO_COMMERCIAL_OPENED);
		} else if (status.equals(AppBaseConstant.BIDDER_STATUS_ANNEXURE_C1_OPENED)) {
			params.put("tahdrMaterialId", tahdrMaterialId);
			
			List<PriceBidDto> bidderForTBList = priceBidService.findDtos("getC1BidderListByTahdrId", params);
			response.addObject("responseList", bidderForTBList);
			response.addObject("openingType", AppBaseConstant.BIDDER_STATUS_Price_BID_OPENED);
		} else if (status.equals(AppBaseConstant.BIDDER_STATUS_Price_BID_OPENED)) {
			params.put("tahdrMaterialId", tahdrMaterialId);
			if (tahdr.getBidTypeCode().equals("SB")) {
				List<TechnicalBidDto> bidderForTCList = technicalBidService.findDtos("getTCBidderListByTahdrId", params);
				List<BidderDto> bidderForTCList = bidderService.findDtos("getTCBidderListByTahdrId", params);
				response.addObject("responseTcList", bidderForTCList);
				response.addObject("bidTypeCode", tahdr.getBidTypeCode());
			}
			
			List<PriceBidDto> bidderForTBList = priceBidService.findDtos("getPBBidderListByTahdrMaterialId", params);
			response.addObject("responseList", bidderForTBList);
			response.addObject("openingType", AppBaseConstant.BIDDER_STATUS_Price_BID_OPENED);
		} else if (status.equals(AppBaseConstant.BIDDER_STATUS_DEVIATION_OPENED)) {
			List<BidderDto> bidderForPBList = bidderService.findDtos("getDBBidderListByTahdrId", params);
			response.addObject("responseList", bidderForPBList);
			response.addObject("openingType", AppBaseConstant.BIDDER_STATUS_DEVIATION_OPENED);
		}
		return response;
	}*/
	
	@Override
	public CustomResponseDto getBidderByOpeningType(Long tahdrId, Long tahdrMaterialId) {
		CustomResponseDto response = new CustomResponseDto();
		
		Map<String, Object> params = new HashMap<>();
		params.put("tahdrId", tahdrId);
		
		TAHDRDto tahdr = findDto("getTenderListsByTahdrId", params);
		String status = tahdr.getTahdrStatusCode();
		String bidType=tahdr.getBidTypeCode();
		params.put("tahdrMaterialId", tahdrMaterialId);
		List<BidderDto> bidders = new ArrayList<>();
		if(bidType.equalsIgnoreCase("SB")){
			/*params.put("status", status);*/
			List<PriceBidDto> bidderForPBList = priceBidService.findDtos("getPBBidderListByTahdrMaterialId", params);
			response.addObject("responseList", bidderForPBList);
		}else{
			params.put("status", status);
			if(status.equalsIgnoreCase("TCOP")){
				bidders = bidderService.findDtos("getOpenedBidderTahdrId", params);
			}else{
				bidders = bidderService.findDtos("getDBOpenedBidderTahdrId", params);
			}
			
			response.addObject("responseList", bidders);
		}
		
		response.addObject("openingType", status);
		response.addObject("bidTypeCode", tahdr.getBidTypeCode());
		return response;
	}
	
	@Override
	public CustomResponseDto getBidderCommercialBid(Long tahdrId) {
		CustomResponseDto response = new CustomResponseDto();
		Map<String, Object> params = new HashMap<>();
		params.put("tahdrId", tahdrId);
		List<BidderDto> bidderForPBList = bidderService.findDtos("getDBBidderListByTahdrId", params);
		response.addObject("responseList", bidderForPBList);
		response.addObject("openingType", AppBaseConstant.BIDDER_STATUS_DEVIATION_OPENED);
		return response;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public boolean updateTahdrMom(TAHDRDto tahdr) {
		Long tahdrId=tahdr.getTahdrId();
		Long momAttachmentId=tahdr.getMom().getAttachmentId();
		int count=tahdrDao.uploadMOM(tahdrId, momAttachmentId);
		if(count>0){
			return true;
			}
		return false;
		
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public CustomResponseDto scheduleTender(String schedulingType, Long tahdrId, String bidType) {
		CustomResponseDto response = new CustomResponseDto();
		TAHDRDetailDto detailDto=null;
		int result = 0;
		if (AppBaseConstant.TENDER_ANNEXURE_C1_SCHEDULING.equalsIgnoreCase(schedulingType)) {
			result = tahdrDao.scheduleTender(schedulingType, tahdrId, bidType,detailDto);
			if (result > 0) {
				boolean markedStatus = itemBidService.updateItemBidStatusForMarkingAnnexureC1Called(tahdrId,
						AppBaseConstant.BASE_PRICE_INCREASE_RATE, AppBaseConstant.BIDDER_STATUS_ANNEXURE_C1_CALLED);
				boolean markedBidderStatus = false;
				if (markedStatus) {
					markedBidderStatus = bidderService
							.updateBidderStatusForC1Called(AppBaseConstant.BIDDER_STATUS_ANNEXURE_C1_CALLED, tahdrId);
				}
				response.addObject("result", true);
				response.addObject("bidderResult", markedBidderStatus);
			} else {
				response.addObject("result", false);
			}

		} else {
			if(AppBaseConstant.TENDER_PRICE_BID_SCHEDULING.equalsIgnoreCase(schedulingType)){
				detailDto=tahdrDetailService.findDto("getQueryForActiveTAHDRDetail", AbstractContextServiceImpl.getParamMap("tahdrId", tahdrId));
			}
			result = tahdrDao.scheduleTender(schedulingType, tahdrId, bidType,detailDto);
			if (result > 0) {
				response.addObject("result", true);

			} else {
				response.addObject("result", false);
			}
		}
		return response;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public boolean setTenderStatus(String status, Long tahdrId) {
		int result = 0;
		result = tahdrDao.setTenderStatus(status, tahdrId);
		if (result > 0) {
			return true;
		}
		return false;
	}

	@Override
	public List<TAHDRDto> getAssociatedTenders(TenderCommitteeDto tenderCommitteeDto, UserDto user) {
		List<TAHDRDto> tenderList = new ArrayList<TAHDRDto>();
		Map<String, Object> params = getParameterMap(tenderCommitteeDto, user);
		/*
		 * String query =
		 * tenderCommitteeDao.getAssociatedTendersQuery(tenderCommitteeDto);
		 */
		String query = getOpeningTenderByUserId(tenderCommitteeDto);
		tenderList = findDtosByQuery(query, params);
		return tenderList;
	}

	private Map<String, Object> getParameterMap(TenderCommitteeDto tenderCommitteeDto, UserDto user) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("isActive", "Y");
		if (user != null) {
			params.put("userId", user.getUserId());
		}
		if (tenderCommitteeDto.getTahdr().getTahdrTypeCode() != null) {
			params.put("tahdrTypeCode", tenderCommitteeDto.getTahdr().getTahdrTypeCode());
		}
		if (tenderCommitteeDto.getTenderVersion().getTechBidOpenningDate() != null) {
			Date technicalOpening = tenderCommitteeDto.getTenderVersion().getTechBidOpenningDate();
			params.put("technicalOpening", technicalOpening);
		}
		if (tenderCommitteeDto.getTenderVersion().getDeviationOpenningDate() != null) {
			Date deviationOpening = tenderCommitteeDto.getTenderVersion().getDeviationOpenningDate();
			params.put("deviationOpening", deviationOpening);
		}
		if (tenderCommitteeDto.getTenderVersion().getC1OpenningDate() != null) {
			Date c1Opening = tenderCommitteeDto.getTenderVersion().getC1OpenningDate();
			params.put("c1Opening", c1Opening);
		}
		if (tenderCommitteeDto.getTenderVersion().getPriceBidOpenningDate() != null) {
			Date priceBidOpening = tenderCommitteeDto.getTenderVersion().getPriceBidOpenningDate();
			params.put("priceBidOpening", priceBidOpening);
		}
		if (tenderCommitteeDto.getTahdr() != null) {
			if (!tenderCommitteeDto.getTahdr().getTahdrCode().equals("")) {
				params.put("tahdrCode", "%" + tenderCommitteeDto.getTahdr().getTahdrCode().toUpperCase() + "%");
			}
		}

		return params;
	}
	private Map<String, Object> getParameterMap(TAHDRDetailDto tahdrDetailDto, UserDto user) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("isActive", "Y");
		if (user != null) {
			params.put("userId", user.getUserId());
		}
		if (tahdrDetailDto.getTahdr().getTahdrTypeCode() != null) {
			params.put("tahdrTypeCode", tahdrDetailDto.getTahdr().getTahdrTypeCode());
		}
		if (tahdrDetailDto.getTechBidOpenningDate() != null) {
			Date technicalOpening = tahdrDetailDto.getTechBidOpenningDate();
			params.put("technicalOpening", technicalOpening);
		}
		if (tahdrDetailDto.getDeviationOpenningDate() != null) {
			Date deviationOpening = tahdrDetailDto.getDeviationOpenningDate();
			params.put("deviationOpening", deviationOpening);
		}
		if (tahdrDetailDto.getC1OpenningDate() != null) {
			Date c1Opening = tahdrDetailDto.getC1OpenningDate();
			params.put("c1Opening", c1Opening);
		}
		if (tahdrDetailDto.getPriceBidOpenningDate() != null) {
			Date priceBidOpening = tahdrDetailDto.getPriceBidOpenningDate();
			params.put("priceBidOpening", priceBidOpening);
		}
		if (tahdrDetailDto.getTahdr() != null) {
			if (!tahdrDetailDto.getTahdr().getTahdrCode().equals("")) {
				params.put("tahdrCode", "%" + tahdrDetailDto.getTahdr().getTahdrCode().toUpperCase() + "%");
			}
		}

		return params;
	}

	@Override
	public List<TAHDRDto> getBidderAssociatedTenders(TenderCommitteeDto tenderCommitteeDto, BPartnerDto partner) {
		List<TAHDRDto> tenderList = new ArrayList<TAHDRDto>();
		Map<String, Object> params = getBidderParameterMap(tenderCommitteeDto, partner);
		/*
		 * String query =
		 * tenderCommitteeDao.getAssociatedTendersQuery(tenderCommitteeDto);
		 */
		String query = getOpeningTenderByPartnerId(tenderCommitteeDto);
		tenderList = findDtosByQuery(query, params);
		return tenderList;
	}

	private Map<String, Object> getBidderParameterMap(TenderCommitteeDto tenderCommitteeDto, BPartnerDto partner) {
		Map<String, Object> params = new HashMap<String, Object>();
		/* params.put("isActive", "Y"); */
		if (partner != null) {
			params.put("partnerId", partner.getbPartnerId());
		}
		if (tenderCommitteeDto.getTahdr().getTahdrTypeCode() != null) {
			params.put("tahdrTypeCode", tenderCommitteeDto.getTahdr().getTahdrTypeCode());
		}
		if (tenderCommitteeDto.getTenderVersion().getTechBidOpenningDate() != null) {
			Date technicalOpening = tenderCommitteeDto.getTenderVersion().getTechBidOpenningDate();
			params.put("technicalOpening", technicalOpening);
		}
		if (tenderCommitteeDto.getTenderVersion().getDeviationOpenningDate() != null) {
			Date deviationOpening = tenderCommitteeDto.getTenderVersion().getDeviationOpenningDate();
			params.put("deviationOpening", deviationOpening);
		}
		if (tenderCommitteeDto.getTenderVersion().getC1OpenningDate() != null) {
			Date c1Opening = tenderCommitteeDto.getTenderVersion().getC1OpenningDate();
			params.put("c1Opening", c1Opening);
		}
		if (tenderCommitteeDto.getTenderVersion().getPriceBidOpenningDate() != null) {
			Date priceBidOpening = tenderCommitteeDto.getTenderVersion().getPriceBidOpenningDate();
			params.put("priceBidOpening", priceBidOpening);
		}
		if (tenderCommitteeDto.getTahdr() != null) {
			params.put("status", tenderCommitteeDto.getTahdr().getTahdrStatusCode());
			if (!tenderCommitteeDto.getTahdr().getTahdrCode().equals("")) {
				params.put("tahdrCode", "%" + tenderCommitteeDto.getTahdr().getTahdrCode() + "%");
			}
		}

		return params;
	}

	@Override
	public String getOpeningTenderByUserId(TenderCommitteeDto tenderCommitteeDto) {
		return tahdrDao.getOpeningTenderByUserId(tenderCommitteeDto);
	}

	@Override
	public String getOpeningTenderByPartnerId(TenderCommitteeDto tenderCommitteeDto) {
		return tahdrDao.getOpeningTenderByPartnerId(tenderCommitteeDto);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public boolean openTender(Long tahdrId, String status, String remark) {
		int result = 0;
		result = tahdrDao.openTender(tahdrId, status, remark);
		if (result > 0) {
			return true;
		}
		return false;
	}

	@Override
	public List<TAHDRDto> getTenderForCommitteeFormation(Map<String, Object> params) {
		List<TAHDRDto> tenderList = new ArrayList<TAHDRDto>();
		/*
		 * params.put("status3", null); String openingType=(String)
		 * params.get("openingType"); if(openingType.equals(AppBaseConstant.
		 * TENDER_COMMITTEE_TechnoCommercial_Opening)){ params.put("status1",
		 * AppBaseConstant.DOCUMENT_STATUS_PUBLISHED); params.put("status2",
		 * AppBaseConstant.TENDER_TECHNO_COMMERCIAL_OPENNING); }else
		 * if(openingType.equals(AppBaseConstant.
		 * TENDER_COMMITTEE_Deviation_Opening)){ params.put("status1",
		 * AppBaseConstant.TENDER_DEVIATION_BID_SCHEDULING);
		 * params.put("status2", AppBaseConstant.TENDER_DEVIATION_OPENNING);
		 * }else if(openingType.equals(AppBaseConstant.
		 * TENDER_COMMITTEE_PriceBid_Opening)){ params.put("status3",
		 * AppBaseConstant.DOCUMENT_STATUS_PUBLISHED); params.put("status1",
		 * AppBaseConstant.TENDER_PRICE_BID_SCHEDULING); params.put("status2",
		 * AppBaseConstant.TENDER_PRICE_BID_OPENNING); }else
		 * if(openingType.equals(AppBaseConstant.
		 * TENDER_COMMITTEE_RevisedBid_Opening)){ params.put("status1",
		 * AppBaseConstant.TENDER_REVISED_BID_SCHEDULING); params.put("status2",
		 * AppBaseConstant.TENDER_REVISED_BID_OPENNING); }else
		 * if(openingType.equals(AppBaseConstant.
		 * TENDER_COMMITTEE_Annexure_Opening)){ params.put("status1",
		 * AppBaseConstant.TENDER_ANNEXURE_C1_SCHEDULING); params.put("status2",
		 * AppBaseConstant.TENDER_ANNEXURE_C1_OPENNING); }else{ return
		 * tenderList; }
		 */
		params.remove("openingType");
		params.remove("searchValue");
		tenderList = findDtos("getTenderListForTenderCommittee", params);
		return tenderList;
	}

	@Override
	public List<TAHDRDto> getTahdrWithDetailsForAwardWinner(Map<String, Object> params, int pageNumber, int pageSize,
			String searchColumn, String searchValue) {

		List<TAHDRDto> tahdrList = null;
		String query ="";
		String typeCode=(String) params.get("tenderTypeCode");
        if(typeCode.equalsIgnoreCase("QRA") || typeCode.equalsIgnoreCase("QFA")  ){
        	params.put("status1", "QAWCO");
        	query = tahdrDao.getQuickTahdrWithDetailsForAwardWinner(searchColumn, searchValue);
        }else if(typeCode.equalsIgnoreCase("QRFQ") ){
        	params.put("status1", "QRFQCO");
        	query = tahdrDao.getRfqWithDetailsForAwardWinner(searchColumn, searchValue);
        }else if(typeCode.equalsIgnoreCase("RFQ")){
        	params.put("status1", "RFQCO");
        	query = tahdrDao.getRfqWithDetailsForAwardWinner(searchColumn, searchValue);
        }else{
        	query = tahdrDao.getTahdrWithDetailsForAwardWinner(searchColumn, searchValue);
        }
		
		if (!"none".equalsIgnoreCase(searchValue)) {
			params.put("searchValue", "%" + searchValue.toUpperCase() + "%");
			tahdrList = findDtosByQuery(query, params, pageNumber, pageSize);
		} else {
			tahdrList = findDtosByQuery(query, params, pageNumber, pageSize);
		}
		return tahdrList;

	}

	@Override
	public Long getTahdrWithDetailsForAwardWinnerCount(Map<String, Object> params, String searchMode,
			String searchValue) {
		String query ="";
		String typeCode=(String) params.get("tenderTypeCode");
        if(typeCode.equalsIgnoreCase("QRA") || typeCode.equalsIgnoreCase("QFA")  ){
        	params.put("status1", "QAWCO");
        	query = tahdrDao.getQuickTahdrWithDetailsForAwardWinnerCount(searchMode, searchValue);
        }else if(typeCode.equalsIgnoreCase("QRFQ")){
        	params.put("status1", "QRFQCO");
        	query = tahdrDao.getRfqDetailsForAwardWinnerCount(searchMode, searchValue);
        }else if(typeCode.equalsIgnoreCase("RFQ")){
        	params.put("status1", "RFQCO");
        	query = tahdrDao.getRfqDetailsForAwardWinnerCount(searchMode, searchValue);
        }else{
        	query = tahdrDao.getTahdrWithDetailsForAwardWinnerCount(searchMode, searchValue);
        }
		if (!"none".equalsIgnoreCase(searchValue)) {
			params.put("searchValue", "%" + searchValue.toUpperCase() + "%");
			return getRecordCount(query, params);
		} else {
			return getRecordCount(query, params);
		}
	}

	@Override
	public TAHDRDto getTahderAllDetails(Long tahdrID) {
		TAHDR tahdr = tahdrDao.getAllTahdrDetails(tahdrID);
		return getDto(tahdr);
	}

	@Override
	public Long getMyTendersCount() {
		Map<String, Object> param = new HashMap<String, Object>();
		UserDto userDto = contextService.getUser();
		param.put("userId", userDto.getUserId());
		String where = tahdrDao.getMytenderCountQuery();
		Long count = getRecordCount(where, param);
		return count;
	}

	@Override
	public Long getTahdrWithDetailsForAwardWinnerCount() {
		Map<String, Object> params = new HashMap<>();
		params.put("status", AppBaseConstant.TENDER_AWARD_WINNER_SCHEDULING);
		String query = tahdrDao.getTahdrWithDetailsForAwardWinnerCount();
		return getRecordCount(query, params);
	}
	
	public long getTenderBidsCount(String typeCode,Map<String, Object> params,String searchColumn, String searchValue){
		long resultCount=0l;
		String searchParam="";
		if(typeCode !=null){
			if (!"none".equalsIgnoreCase(searchValue)) {
				params.put("searchValue", "%" + searchValue.toUpperCase() + "%");
				searchParam=" AND c."+searchColumn+" LIKE :searchValue";
			}
			if(typeCode.equals("FA") || typeCode.equals("RA")){
					String whereCondition=" UPPER(c.tahdrTypeCode) LIKE :tenderTypeCode And c.tahdrStatusCode NOT IN ('AP','RJ','DR','IP','VO') AND c.isAuction='Y' "+searchParam+"  ";
					resultCount= getRecordCount(whereCondition, params);
			}else if(typeCode.equals("QRFQ") || typeCode.equals("RFQ")){
				String whereCondition=" c.tahdrTypeCode= :tenderTypeCode "+searchParam+" ";
				resultCount= getRecordCount(whereCondition, params);
		    }else{
					String whereCondition=" c.tahdrTypeCode= :tenderTypeCode And c.tahdrStatusCode NOT IN ('AP','RJ','DR','IP','VO','PU') AND c.isAuction='N'  "+searchParam+" ";
					resultCount= getRecordCount(whereCondition, params);
			 }
		}else{
			return resultCount;
		}
		return resultCount;
	}
	
	public long getSchedulingTenderCount(String typeCode,Map<String, Object> params,String searchColumn, String searchValue){
		long resultCount=0l;
		String searchParam="";
		if(typeCode !=null){
			if (!"none".equalsIgnoreCase(searchValue)) {
				params.put("searchValue", "%" + searchValue.toUpperCase() + "%");
				searchParam=" AND c."+searchColumn+" LIKE :searchValue";
			}
			if(typeCode.equals("FA") || typeCode.equals("RA")){
					String whereCondition=" c.tahdrTypeCode= :tenderTypeCode And c.tahdrStatusCode IN ('PU','TCOP','SCRDONE','PBOP','C1OP','RBOP','ASCH','PBSCH','C1SCH','RBSCH','AWSCH','DBSCH') AND c.isAuction='Y' "+searchParam+"  ";
					resultCount= getRecordCount(whereCondition, params);
				}
			else{
					String whereCondition=" c.tahdrTypeCode= :tenderTypeCode And c.tahdrStatusCode IN ('PU','TCOP','SCRDONE','PBOP','C1OP','RBOP','PBSCH','C1SCH','RBSCH','AWSCH','DBSCH') AND c.isAuction='N'  "+searchParam+" ";
					resultCount= getRecordCount(whereCondition, params);
			 }
		}else{
			return resultCount;
		}
		return resultCount;
	}
	
	public long getPreBidTenderCount(String typeCode,Map<String, Object> params,String searchColumn, String searchValue){
		long resultCount=0l;
		String searchParam="";
		if(typeCode !=null){
			if (!"none".equalsIgnoreCase(searchValue)) {
				params.put("searchValue", "%" + searchValue.toUpperCase() + "%");
				searchParam=" AND c."+searchColumn+" LIKE :searchValue";
			}
			if(typeCode.equals("FA") || typeCode.equals("RA")){
					String whereCondition="  c.officeType.levels >=:levels  AND c.createdBy.userId=:userId AND c.tahdrStatusCode='PU' AND c.tahdrTypeCode=:typeCode AND c.isAuction='Y' "
							+ "AND  c.tahdrId=(SELECT td.tahdr.tahdrId FROM TAHDRDetail td WHERE td.tahdr.tahdrId=c.tahdrId AND td.isActive='Y') "
							+ " "+searchParam+"  ";
					resultCount= getRecordCount(whereCondition, params);
				}
			else{
					String whereCondition=" c.officeType.levels >=:levels AND c.createdBy.userId=:userId AND c.tahdrStatusCode='PU' AND c.tahdrTypeCode=:typeCode "
							+ " AND c.tahdrId=(SELECT td.tahdr.tahdrId FROM TAHDRDetail td WHERE td.tahdr.tahdrId=c.tahdrId AND td.isActive='Y')  "
							+ " "+searchParam+"  ";
					resultCount= getRecordCount(whereCondition, params);
			 }
		}else{
			return resultCount;
		}
		return resultCount;
	}
	
	public long getBidSheetTenderCount(String typeCode,Map<String, Object> params,String searchColumn, String searchValue){
		long resultCount=0l;
		String searchParam="";
		if(typeCode !=null){
			if (!"none".equalsIgnoreCase(searchValue)) {
				params.put("searchValue", "%" + searchValue.toUpperCase() + "%");
				searchParam=" AND c."+searchColumn+" LIKE :searchValue";
			}
			if(typeCode.equals("FA") || typeCode.equals("RA")){
					String whereCondition="  c.createdBy.userId=:userId AND c.tahdrStatusCode IN (:status1,:status2) AND  "
							+ " c.tahdrId=(SELECT td.tahdr.tahdrId FROM TAHDRDetail td WHERE td.tahdr.tahdrId=c.tahdrId AND td.isActive='Y') "
							+ " AND c.isAuction='Y'  AND c.tahdrTypeCode=:typeCode "+searchParam;
					resultCount= getRecordCount(whereCondition, params);
			}else if(typeCode.equals("QFA") || typeCode.equals("QRA")){
				String whereCondition="  c.createdBy.userId=:userId AND c.tahdrStatusCode =:status1 AND  "
						+ " c.tahdrId=(SELECT td.tahdr.tahdrId FROM TAHDRDetail td WHERE td.tahdr.tahdrId=c.tahdrId AND td.isActive='Y') "
						+ " AND c.isAuction='Y'  AND c.tahdrTypeCode=:typeCode "+searchParam;
				resultCount= getRecordCount(whereCondition, params);
			}
			else{
					String whereCondition=" c.createdBy.userId=:userId AND c.tahdrStatusCode IN (:status1,:status2) AND "
							+ " c.tahdrId=(SELECT td.tahdr.tahdrId FROM TAHDRDetail td WHERE td.tahdr.tahdrId=c.tahdrId AND td.isActive='Y') "
							+ " AND c.tahdrTypeCode=:typeCode "+searchParam;
					resultCount= getRecordCount(whereCondition, params);
			 }
		}else{
			return resultCount;
		}
		return resultCount;
	}
	
	public List<TAHDRDto> getTenders(String queryMethodName,Map<String, Object> params,int pageNumber, int pageSize,String searchColumn, String searchValue){
		List<TAHDRDto> tahdrList=new ArrayList<TAHDRDto>();
		String query = invokeQueryMethod(tahdrDao, queryMethodName);
		StringBuilder customeQuery=new StringBuilder(query);
		if (!"none".equalsIgnoreCase(searchValue)) {
			query=customeQuery.append(" AND E."+searchColumn+" LIKE :searchValue ORDER BY E.updated DESC").toString();
			params.put("searchValue","%" + searchValue.toUpperCase() + "%");
		} else{
			query=customeQuery.append("  ORDER BY E.updated DESC").toString();
		}
		tahdrList = findDtosByQuery(query, params, pageNumber, pageSize);
		return tahdrList;
	}

	@Override
	public long getTahdrApprovalListQueryCount(Map<String, Object> param) {
		long totalCount;
		String queryString =tahdrDao.getTahdrApprovalListQueryForWorkflow(param);
		totalCount = getRecordCount(queryString, param);
		
		return totalCount;
	}

	@Override
	public long getAuctionApprovalListQueryCount(Map<String, Object> param) {
		long totalCount;
		String queryString =tahdrDao.getAuctionApprovalListQueryForWorkflow(param);
		totalCount = getRecordCount(queryString, param);
		
		return totalCount;
	}
	
	@Override
	public void mailNotificationStatusWise(TAHDRDto tahdrDto,List<BidderDto> bidderData,List<TAHDRApprovalMatrixDto> approvalMatrixuser,String tahdrStatusCode){
		
		if(tahdrStatusCode!=null)
		{
			if(tahdrStatusCode.equalsIgnoreCase(AppBaseConstant.DOCUMENT_STATUS_VOID)){
				MailTemplateDto mailData=mailTemplateService.getMailTemplateFromTemplateType(AppBaseConstant.TEMPLATE_ABANDONED_TENDER);
				Map<String, Object> map=AbstractServiceImpl.getParamMap("role",ContextConstant.USER_TYPE_SYSUSER);
				UserDto internalUser=userService.findDto("getUserByRoleCode", map);
				if(mailData!=null  && internalUser!=null){
					for(BidderDto bidder : bidderData){
						mailNotificationForBidderVOStatus(bidder,tahdrDto,mailData,internalUser);
					}
					for(TAHDRApprovalMatrixDto approvalUser : approvalMatrixuser){
						mailNotificationForAppMatrixUserVOStatus(approvalUser,tahdrDto,mailData,internalUser);
					}
					mailNotificationForCretorVOStatus(tahdrDto.getCreatedBy(),tahdrDto,mailData,internalUser);
				}
			}
			else if(tahdrStatusCode.equalsIgnoreCase(AppBaseConstant.DOCUMENT_STATUS_IN_PROGRESS)){
				MailTemplateDto mailData=mailTemplateService.getMailTemplateFromTemplateType(AppBaseConstant.TEMPLATE_TAHDR_SEND_FOR_APPROVAL);
				Map<String, Object> map=AbstractServiceImpl.getParamMap("role",ContextConstant.USER_TYPE_SYSUSER);
				UserDto internalUser=userService.findDto("getUserByRoleCode", map);
				Map<String, Object> paramList=AbstractServiceImpl.getParamMap("tahdrId",tahdrDto.getTahdrId());
				paramList.put("levelNo",1l);
				TAHDRApprovalMatrixDto firstLevelApproval = tahdrApprovalMatrixService.findDto("getFirstLevelApprovalData", paramList);
				if(mailData!=null  && internalUser!=null && firstLevelApproval!=null){
					mailNotificationForFirstLevelAppMatrixUser(firstLevelApproval,tahdrDto,mailData,internalUser);
				}
			}
			else if(tahdrStatusCode.equalsIgnoreCase(AppBaseConstant.DOCUMENT_STATUS_PUBLISHED)){
				MailTemplateDto mailData=mailTemplateService.getMailTemplateFromTemplateType(AppBaseConstant.TEMPLATE_PUBLISH_TENDER);
				Map<String, Object> map=AbstractServiceImpl.getParamMap("role",ContextConstant.USER_TYPE_SYSUSER);
				UserDto internalUser=userService.findDto("getUserByRoleCode", map);
				if(mailData!=null  && internalUser!=null){
					/*if(!approvalMatrixuser.isEmpty()){*/
					for(TAHDRApprovalMatrixDto approvalUser : approvalMatrixuser){
						mailNotificationForAppMatrixUserPUStatus(approvalUser,tahdrDto,mailData,internalUser);
					}
					/*}*/
					mailNotificationForCretorPUStatus(tahdrDto.getCreatedBy(),tahdrDto,mailData,internalUser);
				}
			}
			else if(tahdrStatusCode.equalsIgnoreCase(AppBaseConstant.DOCUMENT_STATUS_REJECTED)){
				MailTemplateDto mailData=mailTemplateService.getMailTemplateFromTemplateType(AppBaseConstant.TEMPLATE_REJECT_TENDER);
				Map<String, Object> map=AbstractServiceImpl.getParamMap("role",ContextConstant.USER_TYPE_SYSUSER);
				UserDto internalUser=userService.findDto("getUserByRoleCode", map);
				if(mailData!=null  && internalUser!=null){
					mailNotificationForCretorRJStatus(tahdrDto.getCreatedBy(),tahdrDto,mailData,internalUser);
				}
			}
			else if(tahdrStatusCode.equalsIgnoreCase(AppBaseConstant.DOCUMENT_STATUS_APPROVED)){
				MailTemplateDto mailData=mailTemplateService.getMailTemplateFromTemplateType(AppBaseConstant.TEMPLATE_APPROVE_TENDER);
				Map<String, Object> map=AbstractServiceImpl.getParamMap("role",ContextConstant.USER_TYPE_SYSUSER);
				UserDto internalUser=userService.findDto("getUserByRoleCode", map);
				UserDto userDto=contextService.getUser();
				Map<String, Object> params=AbstractServiceImpl.getParamMap("userId",userDto.getUserId());
				params.put("tahdrId", tahdrDto.getTahdrId());
				TAHDRApprovalMatrixDto matrixUser=tahdrApprovalMatrixService.findDto("getNextUserToForMail", params);
				if(mailData!=null  && internalUser!=null){
					mailNotificationForCretorAPStatus(tahdrDto.getCreatedBy(),tahdrDto,mailData,internalUser,userDto);
					if(matrixUser!=null){
						mailNotificationForNextLevelAPStatus(matrixUser,tahdrDto,mailData,internalUser);	
					}
					
				}
			}
			
		}
		}
	
	@Override
	public void mailNotificationForBidderVOStatus(BidderDto bidder,TAHDRDto tahdr,MailTemplateDto mailData,UserDto internalUser){
		try{
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
			params.put("@tenderCode@", tahdr.getTahdrCode());
			params.put("@authoriyNameAndDesignation@",internalUser.getUserDetails().getFirstName()+" "+internalUser.getUserDetails().getLastName());
			params.put("@companyTel@",internalUser.getUserDetails().getTelephone1());
			params.put("@companyFax@",internalUser.getUserDetails().getFax1());
			if(bidder.getCreatedBy().getEmail()!=null){
				mailService.sentMailByTemplate(mailData, params, bidder.getCreatedBy().getEmail());
			}
		}
	}
	catch (NullPointerException ex) {
		ex.printStackTrace();
		Log.info("mailNotificationForCretorVOStatus/tahdrServiceIMPL" + ex);
	}
	catch (Exception e) {
		e.printStackTrace();
		Log.info("mailNotificationForCretorVOStatus/tahdrServiceIMPL" + e);
	}
	}
	
	@Override
	public void mailNotificationForAppMatrixUserVOStatus(TAHDRApprovalMatrixDto approvalUser,TAHDRDto tahdr,MailTemplateDto mailData,UserDto internalUser){
		try{
		if(mailData!=null  && internalUser!=null){
			String today=new SimpleDateFormat("dd-MM-yyyy").format(new Date());
			Map<String, Object> params=new HashMap<>();
			params.put("@sourceCompanyName@",internalUser.getPartner().getName());
			params.put("@honour@","");
			params.put("@msebAddress@",internalUser.getUserDetails().getLocation().getAddress1());
			params.put("@emailCorrCode@","Renewal");
			params.put("@generalDetailDate@",today);
			params.put("@vendorComapnyName@",approvalUser.getUser().getPartner().getName());
			params.put("@factoryName@","");
			params.put("@factoryCity@","");
			params.put("@vendorEmail@",approvalUser.getUser().getEmail());
			params.put("@tenderCode@", tahdr.getTahdrCode());
			params.put("@authoriyNameAndDesignation@",internalUser.getUserDetails().getFirstName()+" "+internalUser.getUserDetails().getLastName());
			params.put("@companyTel@",internalUser.getUserDetails().getTelephone1());
			params.put("@companyFax@",internalUser.getUserDetails().getFax1());
			if(approvalUser.getUser().getEmail()!=null){
				mailService.sentMailByTemplate(mailData, params, approvalUser.getUser().getEmail());
			}
		}
	}
	catch (NullPointerException ex) {
		ex.printStackTrace();
		Log.info("mailNotificationForCretorVOStatus/tahdrServiceIMPL" + ex);
	}
	catch (Exception e) {
		e.printStackTrace();
		Log.info("mailNotificationForCretorVOStatus/tahdrServiceIMPL" + e);
	}
	}
	
	@Override
	public void mailNotificationForCretorVOStatus(UserDto creator,TAHDRDto tahdr,MailTemplateDto mailData,UserDto internalUser){
		try{
		if(mailData!=null  && internalUser!=null){
			String today=new SimpleDateFormat("dd-MM-yyyy").format(new Date());
			Map<String, Object> params=new HashMap<>();
			params.put("@sourceCompanyName@",internalUser.getPartner().getName());
			params.put("@honour@","");
			params.put("@msebAddress@",internalUser.getUserDetails().getLocation().getAddress1());
			params.put("@emailCorrCode@","Renewal");
			params.put("@generalDetailDate@",today);
			params.put("@vendorComapnyName@",creator.getPartner().getName());
			params.put("@factoryName@","");
			params.put("@factoryCity@","");
			params.put("@vendorEmail@",creator.getEmail());
			params.put("@tenderCode@", tahdr.getTahdrCode());
			params.put("@authoriyNameAndDesignation@",internalUser.getUserDetails().getFirstName()+" "+internalUser.getUserDetails().getLastName());
			params.put("@companyTel@",internalUser.getUserDetails().getTelephone1());
			params.put("@companyFax@",internalUser.getUserDetails().getFax1());
			if(creator.getEmail()!=null){
				mailService.sentMailByTemplate(mailData, params, creator.getEmail());
			}
		}
		}
		catch (NullPointerException ex) {
			ex.printStackTrace();
			Log.info("mailNotificationForCretorVOStatus/tahdrServiceIMPL" + ex);
		}
		catch (Exception e) {
			e.printStackTrace();
			Log.info("mailNotificationForCretorVOStatus/tahdrServiceIMPL" + e);
		}
	}
	
	@Override
	public void mailNotificationForFirstLevelAppMatrixUser(TAHDRApprovalMatrixDto approvalUser,TAHDRDto tahdr,MailTemplateDto mailData,UserDto internalUser){
		try{
		if(mailData!=null  && internalUser!=null){
			String today=new SimpleDateFormat("dd-MM-yyyy").format(new Date());
			Map<String, Object> params=new HashMap<>();
			params.put("@sourceCompanyName@",internalUser.getPartner().getName());
			params.put("@honour@","");
			params.put("@msebAddress@",internalUser.getUserDetails().getLocation().getAddress1());
			params.put("@emailCorrCode@","Renewal");
			params.put("@generalDetailDate@",today);
			params.put("@vendorComapnyName@",approvalUser.getUser().getPartner().getName());
			params.put("@factoryName@","");
			params.put("@factoryCity@","");
			params.put("@vendorEmail@",approvalUser.getUser().getEmail());
			params.put("@tenderCode@", tahdr.getTahdrCode());
			params.put("@authoriyNameAndDesignation@",internalUser.getUserDetails().getFirstName()+" "+internalUser.getUserDetails().getLastName());
			params.put("@companyTel@",internalUser.getUserDetails().getTelephone1());
			params.put("@companyFax@",internalUser.getUserDetails().getFax1());
			if(approvalUser.getUser().getEmail()!=null){
				mailService.sentMailByTemplate(mailData, params, approvalUser.getUser().getEmail());
			}
		}
	}
	catch (NullPointerException ex) {
		ex.printStackTrace();
		Log.info("mailNotificationForCretorVOStatus/tahdrServiceIMPL" + ex);
	}
	catch (Exception e) {
		e.printStackTrace();
		Log.info("mailNotificationForCretorVOStatus/tahdrServiceIMPL" + e);
	}
	}
	
	
	@Override
	public void mailNotificationForAppMatrixUserPUStatus(TAHDRApprovalMatrixDto approvalUser,TAHDRDto tahdr,MailTemplateDto mailData,UserDto internalUser){
		try{
		if(mailData!=null  && internalUser!=null){
			String today=new SimpleDateFormat("dd-MM-yyyy").format(new Date());
			Map<String, Object> params=new HashMap<>();
			params.put("@sourceCompanyName@",internalUser.getPartner().getName());
			params.put("@honour@","");
			params.put("@msebAddress@",internalUser.getUserDetails().getLocation().getAddress1());
			params.put("@emailCorrCode@","Renewal");
			params.put("@generalDetailDate@",today);
			params.put("@vendorComapnyName@",approvalUser.getUser().getPartner().getName());
			params.put("@factoryName@","");
			params.put("@factoryCity@","");
			params.put("@vendorEmail@",approvalUser.getUser().getEmail());
			params.put("@tenderCode@", tahdr.getTahdrCode());
			params.put("@version@", tahdr.getTahdrDetail().iterator().next().getVersion().toString());
			params.put("@authoriyNameAndDesignation@",internalUser.getUserDetails().getFirstName()+" "+internalUser.getUserDetails().getLastName());
			params.put("@companyTel@",internalUser.getUserDetails().getTelephone1());
			params.put("@companyFax@",internalUser.getUserDetails().getFax1());
			if(approvalUser.getUser().getEmail()!=null){
				mailService.sentMailByTemplate(mailData, params, approvalUser.getUser().getEmail());
			}
		}
	}
	catch (NullPointerException ex) {
		ex.printStackTrace();
		Log.info("mailNotificationForCretorPUStatus/tahdrServiceIMPL" + ex);
	}
	catch (Exception e) {
		e.printStackTrace();
		Log.info("mailNotificationForCretorPUStatus/tahdrServiceIMPL" + e);
	}
	}
	
	@Override
	public void mailNotificationForCretorPUStatus(UserDto creator,TAHDRDto tahdr,MailTemplateDto mailData,UserDto internalUser){
		try{
		if(mailData!=null  && internalUser!=null){
			String today=new SimpleDateFormat("dd-MM-yyyy").format(new Date());
			Map<String, Object> params=new HashMap<>();
			params.put("@sourceCompanyName@",internalUser.getPartner().getName());
			params.put("@honour@","");
			params.put("@msebAddress@",internalUser.getUserDetails().getLocation().getAddress1());
			params.put("@emailCorrCode@","Renewal");
			params.put("@generalDetailDate@",today);
			params.put("@vendorComapnyName@",creator.getPartner().getName());
			params.put("@factoryName@","");
			params.put("@factoryCity@","");
			params.put("@vendorEmail@",creator.getEmail());
			params.put("@tenderCode@", tahdr.getTahdrCode());
			params.put("@version@", tahdr.getTahdrDetail().iterator().next().getVersion().toString());
			params.put("@authoriyNameAndDesignation@",internalUser.getUserDetails().getFirstName()+" "+internalUser.getUserDetails().getLastName());
			params.put("@companyTel@",internalUser.getUserDetails().getTelephone1());
			params.put("@companyFax@",internalUser.getUserDetails().getFax1());
			if(creator.getEmail()!=null){
				mailService.sentMailByTemplate(mailData, params, creator.getEmail());
			}
		}
		}
		catch (NullPointerException ex) {
			ex.printStackTrace();
			Log.info("mailNotificationForCretorVOStatus/tahdrServiceIMPL" + ex);
		}
		catch (Exception e) {
			e.printStackTrace();
			Log.info("mailNotificationForCretorVOStatus/tahdrServiceIMPL" + e);
		}
	}

	@Override
	public void mailNotificationForCretorRJStatus(UserDto creator,TAHDRDto tahdr,MailTemplateDto mailData,UserDto internalUser){
		try{
		if(mailData!=null  && internalUser!=null){
			String today=new SimpleDateFormat("dd-MM-yyyy").format(new Date());
			Map<String, Object> params=new HashMap<>();
			params.put("@sourceCompanyName@",internalUser.getPartner().getName());
			params.put("@honour@","");
			params.put("@msebAddress@",internalUser.getUserDetails().getLocation().getAddress1());
			params.put("@emailCorrCode@","Renewal");
			params.put("@generalDetailDate@",today);
			params.put("@vendorComapnyName@",creator.getPartner().getName());
			params.put("@factoryName@","");
			params.put("@factoryCity@","");
			params.put("@vendorEmail@",creator.getEmail());
			params.put("@tenderCode@", tahdr.getTahdrCode());
			params.put("@status@", "Rejected");
			params.put("@authoriyNameAndDesignation@",internalUser.getUserDetails().getFirstName()+" "+internalUser.getUserDetails().getLastName());
			params.put("@companyTel@",internalUser.getUserDetails().getTelephone1());
			params.put("@companyFax@",internalUser.getUserDetails().getFax1());
			if(creator.getEmail()!=null){
				mailService.sentMailByTemplate(mailData, params, creator.getEmail());
			}
		}
		}
		catch (NullPointerException ex) {
			ex.printStackTrace();
			Log.info("mailNotificationForCretorRJStatus/tahdrServiceIMPL" + ex);
		}
		catch (Exception e) {
			e.printStackTrace();
			Log.info("mailNotificationForCretorRJStatus/tahdrServiceIMPL" + e);
		}
	}
	
	@Override
	public void mailNotificationForNextLevelAPStatus(TAHDRApprovalMatrixDto approvalUser,TAHDRDto tahdr,MailTemplateDto mailData,UserDto internalUser){
		try{
		if(mailData!=null  && internalUser!=null){
			String today=new SimpleDateFormat("dd-MM-yyyy").format(new Date());
			Map<String, Object> params=new HashMap<>();
			params.put("@sourceCompanyName@",internalUser.getPartner().getName());
			params.put("@honour@","");
			params.put("@msebAddress@",internalUser.getUserDetails().getLocation().getAddress1());
			params.put("@emailCorrCode@","Renewal");
			params.put("@generalDetailDate@",today);
			params.put("@vendorComapnyName@",approvalUser.getUser().getPartner().getName());
			params.put("@factoryName@","");
			params.put("@factoryCity@","");
			params.put("@vendorEmail@",approvalUser.getUser().getEmail());
			params.put("@tenderCode@", tahdr.getTahdrCode());
			params.put("@manualSentence@", " is pending with you for approval.  ");
			params.put("@authoriyNameAndDesignation@",internalUser.getUserDetails().getFirstName()+" "+internalUser.getUserDetails().getLastName());
			params.put("@companyTel@",internalUser.getUserDetails().getTelephone1());
			params.put("@companyFax@",internalUser.getUserDetails().getFax1());
			if(approvalUser.getUser().getEmail()!=null){
				mailService.sentMailByTemplate(mailData, params, approvalUser.getUser().getEmail());
			}
		}
	}
	catch (NullPointerException ex) {
		ex.printStackTrace();
		Log.info("mailNotificationForCretorVOStatus/tahdrServiceIMPL" + ex);
	}
	catch (Exception e) {
		e.printStackTrace();
		Log.info("mailNotificationForCretorVOStatus/tahdrServiceIMPL" + e);
	}
	}
	
	@Override
	public void mailNotificationForCretorAPStatus(UserDto creator,TAHDRDto tahdr,MailTemplateDto mailData,UserDto internalUser,UserDto user){
		try{
		if(mailData!=null  && internalUser!=null){
			String today=new SimpleDateFormat("dd-MM-yyyy").format(new Date());
			Map<String, Object> params=new HashMap<>();
			params.put("@sourceCompanyName@",internalUser.getPartner().getName());
			params.put("@honour@","");
			params.put("@msebAddress@",internalUser.getUserDetails().getLocation().getAddress1());
			params.put("@emailCorrCode@","Renewal");
			params.put("@generalDetailDate@",today);
			params.put("@vendorComapnyName@",creator.getPartner().getName());
			params.put("@factoryName@","");
			params.put("@factoryCity@","");
			params.put("@vendorEmail@",creator.getEmail());
			params.put("@tenderCode@", tahdr.getTahdrCode());
			params.put("@manualSentence@", "is Approved by '"+user.getName()+"'");
			params.put("@authoriyNameAndDesignation@",internalUser.getUserDetails().getFirstName()+" "+internalUser.getUserDetails().getLastName());
			params.put("@companyTel@",internalUser.getUserDetails().getTelephone1());
			params.put("@companyFax@",internalUser.getUserDetails().getFax1());
			if(creator.getEmail()!=null){
				mailService.sentMailByTemplate(mailData, params, creator.getEmail());
			}
		}
		}
		catch (NullPointerException ex) {
			ex.printStackTrace();
			Log.info("mailNotificationForCretorAPStatus/tahdrServiceIMPL" + ex);
		}
		catch (Exception e) {
			e.printStackTrace();
			Log.info("mailNotificationForCretorAPStatus/tahdrServiceIMPL" + e);
		}
	}
	
	@Override
	public List<TAHDRDto> getTahdrToBeOpenedForReminderMail(String status){
		Map<String, Object> params=new HashMap<>();
		String queryString = tahdrDao.getTahdrListOfOpeningForReminderMail(status);
		List<TAHDRDto> tahdrEntity = findDtosByQuery(queryString, params);
		return tahdrEntity;
	}
	
	@Override
	public List<TAHDRDto> getTahdrOfSubmissionForReminderMail(String status){
		Map<String, Object> params=new HashMap<>();
		String queryString = tahdrDao.getTahdrListOfSubmissionForReminderMail(status);
		List<TAHDRDto> tahdrEntity = findDtosByQuery(queryString, params);
		return tahdrEntity;
	}

	@Override
	public List<TAHDRDto> getAssociatedTenders(TAHDRDetailDto tahdrDetailDto, UserDto user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TAHDRDto> getTenderByTypeCode(Long partnerId,String status ,String typeCode){
		List<TAHDR> tenders = tahdrDao.getTenderByTypeCode(partnerId,status,typeCode);
		List<TAHDRDto> dtoList=new ArrayList<>();
		if(!CommonUtil.isCollectionEmpty(tenders)){
			dtoList=getDtoList(tenders);
		}
		return dtoList;
	}
	
}
