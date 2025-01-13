package com.novelerp.appbase.master.converter;

import org.springframework.stereotype.Component;

import com.novelerp.appbase.master.dto.BankBranchDetailsDto;
import com.novelerp.appbase.master.dto.BankNameDetailsDto;
import com.novelerp.appbase.master.dto.PartnerBankDetailDto;
import com.novelerp.appbase.master.entity.PartnerBankDetail;
import com.novelerp.appcontext.converter.CustomContextDozerConverter;

/**
 * 
 * @author Vivek Birdi
 *
 */
@Component
public class PartnerBankDetailConverter extends CustomContextDozerConverter<PartnerBankDetail, PartnerBankDetailDto>{

	@Override
	public PartnerBankDetailDto convertEntityToDto(PartnerBankDetail entity, Class<PartnerBankDetailDto> dtoClass) {
	
		if(entity== null){
			return null;
		}
		
		PartnerBankDetailDto dto =  new PartnerBankDetailDto();
		dto.setPartnerBankDetailId(entity.getPartnerBankDetailId());
		/*dto.setBankName(entity.getBankName());*/
		dto.setAccountNumber(entity.getAccountNumber());
		dto.setBenificaryName(entity.getBenificaryName());
		/*dto.setIfscCode(entity.getIfscCode());*/
		dto.setMobileNo(entity.getMobileNo());
		dto.setLoadDefault(true);
		dto.setIsApproved(entity.getIsApproved());
		dto.setRemark(entity.getRemark());
		/*dto.setBranchName(entity.getBranchName());*/
		if(entity.getBankNameDetails()!=null)
		{
			BankNameDetailsDto bankName=new BankNameDetailsDto();
			bankName.setBankNameDetailsId(entity.getBankNameDetails().getBankNameDetailsId());
			bankName.setName(entity.getBankNameDetails().getName());
			dto.setBankNameDetails(bankName);
		}
		if(entity.getBranchName()!=null){
			BankBranchDetailsDto branchName=new BankBranchDetailsDto();
			branchName.setBankBranchDetailsId(entity.getBranchName().getBankBranchDetailsId());
			branchName.setBranchName(entity.getBranchName().getBranchName());
		}
		return dto;
	}
	

}
