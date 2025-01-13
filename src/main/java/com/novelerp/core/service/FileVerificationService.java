package com.novelerp.core.service;

import java.io.File;

import com.novelerp.core.dto.FileVerificationDto;

public interface FileVerificationService {

	FileVerificationDto verifyFile(File f) ;
	FileVerificationDto verifyFile(File f, long pVendorid);
	
}
