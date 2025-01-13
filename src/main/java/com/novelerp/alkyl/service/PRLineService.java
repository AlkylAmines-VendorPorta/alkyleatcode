package com.novelerp.alkyl.service;

import java.util.List;

import com.novelerp.alkyl.dto.PRDto;
import com.novelerp.alkyl.dto.PRLineDto;
import com.novelerp.alkyl.dto.PRLineFilterDto;
import com.novelerp.alkyl.dto.PRLineFromSapDto;
import com.novelerp.alkyl.entity.PRLine;
import com.novelerp.appcontext.dto.BPartnerDto;
import com.novelerp.core.service.CommonService;

public interface PRLineService extends CommonService<PRLine, PRLineDto> {

	//public List<PRLineDto> save(List<PRLineDto> prLines, PRDto PR);

	public List<PRLineDto> getPRLineListBYFilter(PRLineFilterDto prLineDto);

	public boolean updatePRBuyerAssign(List<PRLineDto> prLineDto);

//	public List<PRLineDto> save(List<PRLineDto> prServiceLines, PRDto pr, PRLineDto prLine);
	public List<PRLineDto> save(List<PRLineDto> prServiceLines, PRDto pr, BPartnerDto partner,PRLineDto prLine);

	public List<PRLineDto> save(List<PRLineDto> prLines, PRDto pr, BPartnerDto partner);

	public List<PRLineDto> getPRFromSAPAfterPOCreation(PRLineFilterDto prLineDto);

	public List<PRLineDto> addprToprlineFromSap(List<PRLineDto> prLineList, PRDto pr);



	

}
