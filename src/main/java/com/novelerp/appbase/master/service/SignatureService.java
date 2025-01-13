package com.novelerp.appbase.master.service;

import java.io.File;

import com.novelerp.appbase.master.dto.AttachmentDto;
import com.novelerp.appcontext.dto.UserDetailsDto;
import com.novelerp.core.dto.ResponseDto;


/**
 * 
 * @author Vivek Birdi
 *
 */
public interface SignatureService {

	/**
	 * Check if file have digital signature of signatory
	 * @param file
	 * @param signatoryId
	 * @return
	 */
	public ResponseDto validate(File file, UserDetailsDto userDetail);
	public ResponseDto validateCoSigner(File file,UserDetailsDto userDetail);
	/**
	 * @param file
	 * @param userDetail
	 * @param isEncrypted
	 * @return
	 */
	public ResponseDto validate(File file, UserDetailsDto userDetail, String isEncrypted);
	/**
	 * @param buffer
	 * @param userDetail
	 * @param isEncrypted
	 * @return
	 */
	public ResponseDto validate(byte[] buffer, UserDetailsDto userDetail, String isEncrypted);
	public ResponseDto validate(AttachmentDto attachment, UserDetailsDto userDetail);
	public ResponseDto validateCoSigner(AttachmentDto attachment,UserDetailsDto userDetail);
}
