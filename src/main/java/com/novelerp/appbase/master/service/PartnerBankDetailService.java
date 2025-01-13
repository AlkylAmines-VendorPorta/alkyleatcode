package com.novelerp.appbase.master.service;

import com.novelerp.appbase.master.dto.PartnerBankDetailDto;
import com.novelerp.appbase.master.entity.PartnerBankDetail;
import com.novelerp.core.service.CommonService;

/**
 * 
 * @author Vivek Birdi
 *
 */
public interface PartnerBankDetailService extends CommonService<PartnerBankDetail, PartnerBankDetailDto> {
  public PartnerBankDetailDto saveBankDetails(PartnerBankDetailDto dto);
}
