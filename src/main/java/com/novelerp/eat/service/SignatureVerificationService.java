/**
 * @author Ankush
 */
package com.novelerp.eat.service;

import com.novelerp.appbase.master.dto.AttachmentDto;
import com.novelerp.core.dto.ResponseDto;

public interface SignatureVerificationService {

	public ResponseDto verifyDigitalSignature(AttachmentDto attachment);
	
}
