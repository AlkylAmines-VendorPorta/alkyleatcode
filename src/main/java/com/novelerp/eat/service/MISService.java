package com.novelerp.eat.service;

import java.util.List;

import com.novelerp.core.service.CommonService;
import com.novelerp.eat.dto.MISDto;
import com.novelerp.eat.entity.MIS;

public interface MISService extends CommonService<MIS, MISDto>{

	public List<MISDto> readMisExcel();

}
