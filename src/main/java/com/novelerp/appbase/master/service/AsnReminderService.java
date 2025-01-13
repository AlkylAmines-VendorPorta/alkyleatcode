package com.novelerp.appbase.master.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.novelerp.appbase.master.dto.AsnReminderDto;
import com.novelerp.appbase.master.entity.AsnReminder;
import com.novelerp.core.dto.ResponseDto;
import com.novelerp.core.service.CommonService;

public interface AsnReminderService extends CommonService<AsnReminder, AsnReminderDto>{

	public List<AsnReminderDto> saveExcelData(MultipartFile multipartFile) throws IOException;

	boolean deleteAsnReminder(Long id);
	
	}
