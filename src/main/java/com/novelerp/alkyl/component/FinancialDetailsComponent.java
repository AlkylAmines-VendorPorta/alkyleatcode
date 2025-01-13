package com.novelerp.alkyl.component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.novelerp.alkyl.dto.FinancialDetailsDto;
import com.novelerp.alkyl.dto.PartnerFinancialDetailDto;
import com.novelerp.appbase.master.dto.PartnerFinancialAttachmentDto;
import com.novelerp.appbase.master.service.PartnerFinancialDetailsService;
import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.core.dto.ResponseDto;

@Component
public class FinancialDetailsComponent {
	

	@Autowired
	private PartnerFinancialDetailsService fdService;
	
//	public List<PartnerFinancialAttachmentDto> getPartnerFinancialAttList(FinancialDetailsDto financeDto){
//		
//		List<PartnerFinancialAttachmentDto> attList = new ArrayList<>();
//		
//		for(PartnerFinancialDetailDto dto : financeDto.getFinancialDetailsList()){
//			
//			PartnerFinancialAttachmentDto balanceSheet = dto.getBalanceSheet();
//			balanceSheet.setFinancialYear(dto.getFinancialYear());
//			attList.add(balanceSheet);
//			
//			PartnerFinancialAttachmentDto profitNLoss = dto.getProfitAndLoss();
//			profitNLoss.setFinancialYear(dto.getFinancialYear());
//			attList.add(profitNLoss);
//			
//			PartnerFinancialAttachmentDto turnOver = dto.getTurnOver();
//			turnOver.setFinancialYear(dto.getFinancialYear());
//			attList.add(turnOver);
//			
//		}
//		
//		return attList;
//	}
	
//	public FinancialDetailsDto setFinancialDetailsDtoFromList(List<PartnerFinancialAttachmentDto> attList){
//		 FinancialDetailsDto fdDto = new FinancialDetailsDto();
//		 
//		 Map<Long, PartnerFinancialDetailDto> map=new HashMap<>();
//		 for(PartnerFinancialAttachmentDto att: attList){
//			 PartnerFinancialDetailDto dto = null;
//			 
//			 if(att.getFinancialYear() !=null && att.getFinancialYear().getFinancialYearId() !=null){
//			 
//				 dto = map.get(att.getFinancialYear().getFinancialYearId());
//				 dto=map.get(att.getFinancialYearNew());
//				 if(dto == null){
//					 dto = new PartnerFinancialDetailDto();
//				 }
//			 }else{
//				 throw new RuntimeException();
//			 }
//			 
//			 dto.setFinancialYear(att.getFinancialYear());
//			 if(AppBaseConstant.PROFIT_AND_LOSS.equals(att.getFinacialType())){
//				 dto.setProfitAndLoss(att);
//			 }else if(AppBaseConstant.BALANCE_SHEET_ACCOUNT.equals(att.getFinacialType())){
//				 dto.setBalanceSheet(att);
//			 }else{
//				 dto.setTurnOver(att); 
//			 }
//			 
//			 map.put(dto.getFinancialYear().getFinancialYearId(), dto);
//			
//		 }
//		 List<PartnerFinancialDetailDto> fdAttList = new ArrayList<PartnerFinancialDetailDto>(map.values());
//		 fdDto.setFinancialDetailsList(fdAttList);
//		 return fdDto;
//	}
public List<PartnerFinancialAttachmentDto> getPartnerFinancialAttList(FinancialDetailsDto financeDto){
		
		List<PartnerFinancialAttachmentDto> attList = new ArrayList<>();
		
		for(PartnerFinancialDetailDto dto : financeDto.getFinancialDetailsList()){
			
			PartnerFinancialAttachmentDto balanceSheet = dto.getBalanceSheet();
			balanceSheet.setFinancialYearNew(dto.getFinancialYearNew());
			attList.add(balanceSheet);
			
			PartnerFinancialAttachmentDto profitNLoss = dto.getProfitAndLoss();
			profitNLoss.setFinancialYearNew(dto.getFinancialYearNew());
			attList.add(profitNLoss);
			
			PartnerFinancialAttachmentDto turnOver = dto.getTurnOver();
			turnOver.setFinancialYearNew(dto.getFinancialYearNew());
			attList.add(turnOver);
			
		}
		
		return attList;
	}
	public FinancialDetailsDto setFinancialDetailsDtoFromList(List<PartnerFinancialAttachmentDto> attList){
		 FinancialDetailsDto fdDto = new FinancialDetailsDto();
		 
		 Map<String, PartnerFinancialDetailDto> map=new HashMap<>();
		 for(PartnerFinancialAttachmentDto att: attList){
			 PartnerFinancialDetailDto dto = null;
			 
			 if(att.getFinancialYearNew()!=null)			 {
				 dto=map.get(att.getFinancialYearNew());
				 if(dto == null){
					 dto = new PartnerFinancialDetailDto();
				 }
			 }else{
				 throw new RuntimeException();
			 }
			 
			 dto.setFinancialYearNew(att.getFinancialYearNew());
			 if(AppBaseConstant.PROFIT_AND_LOSS.equals(att.getFinacialType())){
				 dto.setProfitAndLoss(att);
			 }else if(AppBaseConstant.BALANCE_SHEET_ACCOUNT.equals(att.getFinacialType())){
				 dto.setBalanceSheet(att);
			 }else{
				 dto.setTurnOver(att); 
			 }
			 
			 map.put(dto.getFinancialYearNew(), dto);
		 }
		 List<PartnerFinancialDetailDto> fdAttList = new ArrayList<PartnerFinancialDetailDto>(map.values());
		 fdDto.setFinancialDetailsList(fdAttList);
		 return fdDto;
	}
	
	
	public FinancialDetailsDto saveFinancialDetails(FinancialDetailsDto dto){
		List<PartnerFinancialAttachmentDto> attList = fdService.processFinancialAttachmentRequest(getPartnerFinancialAttList(dto));
		FinancialDetailsDto fd = setFinancialDetailsDtoFromList(attList);
		fd.setResponse(new ResponseDto(false,"Financial Details Saved..!"));
		return fd;
	}
	
}
