package com.novelerp.alkyl.service;

import java.util.List;

import com.novelerp.alkyl.dto.AnnexureDto;
import com.novelerp.alkyl.entity.Annexure;
import com.novelerp.core.dto.ResponseDto;
import com.novelerp.core.service.CommonService;

public interface AnnexureService extends CommonService<Annexure, AnnexureDto>{

	public AnnexureDto saveAnnexure(AnnexureDto annexureDto);

	public ResponseDto updateAnnexureReject(Long annexureId, String remarks);

	public ResponseDto updateAnnexureApproval(Long annexureId);

	public List<AnnexureDto> getPrForQcf(String role);

	public ResponseDto updateFinalApproval(Long annexureId);

	void SendMailonFinalApproval(Long annexureId);

	void SendMailonFinalRejection(Long annexureId);

	ResponseDto updateFinalAnnexureReject(Long annexureId, String remarks);

	void SendMailonPMRejection(Long annexureId);


	void updateQCFAnnexureDataToSAP(Long annexureId);


}
