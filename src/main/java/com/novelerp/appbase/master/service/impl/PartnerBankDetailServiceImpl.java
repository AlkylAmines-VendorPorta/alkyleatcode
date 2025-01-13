package com.novelerp.appbase.master.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.novelerp.appbase.master.dao.PartnerBankDetailDao;
import com.novelerp.appbase.master.dto.PartnerBankDetailDto;
import com.novelerp.appbase.master.entity.PartnerBankDetail;
import com.novelerp.appbase.master.service.BankBranchDetailsService;
import com.novelerp.appbase.master.service.BankNameDetailsService;
import com.novelerp.appbase.master.service.PartnerBankDetailService;
import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.appcontext.dto.RoleDto;
import com.novelerp.appcontext.service.ContextService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.appcontext.util.ContextConstant;
import com.novelerp.core.dto.ResponseDto;
/**
 * 
 * @author Vivek Birdi
 *
 */
@Service
public class PartnerBankDetailServiceImpl extends AbstractContextServiceImpl<PartnerBankDetail, PartnerBankDetailDto>  implements PartnerBankDetailService{

	@Autowired
	private PartnerBankDetailDao partnerBankDetailDao;
	@Autowired
	@Qualifier("jwtUserContext") 
	private ContextService contextService;
	
	@Autowired
	private BankNameDetailsService bankNameService;
	
	@Autowired
	private BankBranchDetailsService bankBranchService;
	
	@PostConstruct
	public void init(){
		init(PartnerBankDetailServiceImpl.class, partnerBankDetailDao, PartnerBankDetail.class, PartnerBankDetailDto.class);
		/*setObjectConverter(partnerBankDetailConverter);*/
		setByPassProxy(true);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public PartnerBankDetailDto saveBankDetails(PartnerBankDetailDto dto) {
		if(dto==null){
			dto = new PartnerBankDetailDto();
			dto.setResponse(new ResponseDto(true, "Empty Bank Details"));
			return dto;
		}
		RoleDto role=contextService.getDefaultRole();
		if(role==null || role.getValue()==null)
		{   dto.setResponse(new ResponseDto(true, "Session Timeout"));
			return dto;
		}
		if(role.getValue().equals(ContextConstant.USER_TYPE_PATNER_USER) || role.getValue().equals(ContextConstant.USER_TYPE_VENDOR_ADMIN) || AppBaseConstant.ROLE_INFRA_ADMIN.equals(role.getValue()))
		{
			dto.setIsEEApproved(null);
			dto.setIsCEApproved(null);
			dto.setEeComment(null);
			dto.setCeComment(null);
		}
		if(dto!=null && dto.getPartnerBankDetailId()!=null && (role.getValue().equals(ContextConstant.USER_TYPE_EXECUTIVE_ENGINEER)||role.getValue().equals(ContextConstant.USER_TYPE_CHIEF_ENGINEER)))
		{
			Map<String, Object> map=new HashMap<>();
			if (dto.getIsEEApproved()!=null && role.getValue().equals(ContextConstant.USER_TYPE_EXECUTIVE_ENGINEER)) {
			  map.put("eeComment", dto.getEeComment());
			  map.put("isEEApproved", dto.getIsEEApproved());
			}else if (dto.getIsCEApproved()!=null && role.getValue().equals(ContextConstant.USER_TYPE_CHIEF_ENGINEER)) {
				  map.put("ceComment", dto.getCeComment());
				  map.put("isCEApproved", dto.getIsCEApproved());
			}
			int result= updateByJpql(map, "partnerBankDetailId", dto.getPartnerBankDetailId());
			if(result>0)
			{
				dto.setResponse(new ResponseDto(false, "Record Updated"));
				return dto;
			}else{
				dto.setResponse(new ResponseDto(true, "Error in Updated"));
				return dto;
			}
		}
		if(dto.getBankNameDetails()!=null && dto.getBankNameDetails().getBankNameDetailsId()==null){
			dto.setBankNameDetails(bankNameService.save(dto.getBankNameDetails()));
		}else{
			dto.setBankNameDetails(bankNameService.updateDto(dto.getBankNameDetails()));
		}
		if(dto.getBranchName()!=null && dto.getBranchName().getBankBranchDetailsId()==null){
			dto.setBranchName(bankBranchService.save(dto.getBranchName()));
		}else{
			dto.setBranchName(bankBranchService.updateDto(dto.getBranchName()));
		}
		if(dto.getPartnerBankDetailId() == null){
			return save(dto);
		}else{
			return updateDto(dto);
		}
	}
}
