package com.novelerp.eat.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.appcontext.dto.UserDto;
import com.novelerp.appcontext.service.ContextService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.eat.dao.TAHDRApprovalMatrixDao;
import com.novelerp.eat.dto.TAHDRApprovalMatrixDto;
import com.novelerp.eat.entity.TAHDRApprovalMatrix;
import com.novelerp.eat.service.TAHDRApprovalMatrixService;

@Service
public class TAHDRApprovalMatrixServiceImpl  extends AbstractContextServiceImpl<TAHDRApprovalMatrix, TAHDRApprovalMatrixDto> implements TAHDRApprovalMatrixService {

	@Autowired
	private TAHDRApprovalMatrixDao tahdrApprovalMatrixDao;
	
	@Autowired
	 @Qualifier("jwtUserContext") private ContextService contextService;
	@PostConstruct
	private void init() {
		super.init(TAHDRApprovalMatrixService.class, tahdrApprovalMatrixDao, TAHDRApprovalMatrix.class, TAHDRApprovalMatrixDto.class);
		setByPassProxy(true);
	}

	@Override
	public List<TAHDRApprovalMatrixDto> getUserNameForTenderList(String userName) {
		List<TAHDRApprovalMatrixDto> tenderList= new ArrayList<TAHDRApprovalMatrixDto>();
		
		if(userName!=null){
			tenderList=findDtos("getTahdrListFromApprovalMatrix",AbstractContextServiceImpl.getParamMap("name",userName));
		}
		return tenderList;
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public TAHDRApprovalMatrixDto setStatusApprove(TAHDRApprovalMatrixDto approvalMatrixDto, String status){
		Map<String,Object> param= new HashMap<>();
		param.put("status", status);
		updateByJpql(param, "tahdrApprovalMatrixId",approvalMatrixDto.getTahdrApprovalMatrixId());
		/*if(approvalMatrixDto!=null && status.equalsIgnoreCase(AppBaseConstant.DOCUMENT_STATUS_APPROVED)){
			approvalMatrixDto=setStatusInProgress(tahdrId);
		}*/
		return approvalMatrixDto;
	}
	
	@Override
	public TAHDRApprovalMatrixDto getApprovalMatrixForAP(Long tahdrId){
		Map<String,Object> map= new HashMap<>();
		TAHDRApprovalMatrixDto approvalMatrixDto=null;
		UserDto userDto=contextService.getUser();
		map.put("tahdrId", tahdrId);
		map.put("status", AppBaseConstant.DOCUMENT_STATUS_IN_PROGRESS);
		map.put("userId", userDto.getUserId());
		approvalMatrixDto=findDto("getApprovalMatrixDataForApproval",map );
		return approvalMatrixDto;
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public int setStatusInProgress(Long id) {
		Map<String,Object> param= new HashMap<>();
		int approvalMatrixDto=0;
		if(id!=null){
		param.put("status", AppBaseConstant.DOCUMENT_STATUS_IN_PROGRESS);
		approvalMatrixDto=updateByJpql(param, "tahdrApprovalMatrixId",id);
		}
		return approvalMatrixDto;
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public int updateLevelOnDelete(Long tahdrId,Long levels){
		int approvalMatrixDto=0;
		if(tahdrId!=null && levels!=null ){
		approvalMatrixDto=tahdrApprovalMatrixDao.updateLevelOnDelete(tahdrId, levels);
		}
		return approvalMatrixDto;
	}
	
	@Override
	public TAHDRApprovalMatrixDto getRowForUpdateApprovalMatrix(Long tahdrId,Long approvalMatrixRow){
		TAHDRApprovalMatrixDto approvalMatrixDto=null;
		Map<String,Object> map= new HashMap<>();
		map.put("tahdrId", tahdrId);
		map.put("levelNo", approvalMatrixRow);
		approvalMatrixDto=findDto("getApprovaMatrixFromLevel",map );
		return approvalMatrixDto;
	}
	
	@Override
	public Long getRowForUpdateApprovalMatrixLevel(Long tahdrId){
		Long approvalMatrix=null;
		approvalMatrix=tahdrApprovalMatrixDao.getLevelsfromTahdrId(tahdrId);
		return approvalMatrix;
	}
}

