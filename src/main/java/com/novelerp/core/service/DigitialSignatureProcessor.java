package com.novelerp.core.service;

import java.io.File;
import java.util.List;

import com.novelerp.core.dto.DigitalSignatureInfo;
/**
 * 
 * @author Vivek Birdi
 *
 */
public interface DigitialSignatureProcessor {

	public DigitalSignatureInfo getDigitalSignatureInfo(File file);
	public List<DigitalSignatureInfo> getDigitalSignatureInfoList(File file);
	/**
	 * @param file
	 * @param isEncrypted
	 * @return
	 */
	public DigitalSignatureInfo getDigitalSignatureInfo(File file, String isEncrypted);
	/**
	 * @param buffer
	 * @param isEncrypted
	 * @return
	 */
	public DigitalSignatureInfo getDigitalSignatureInfo(byte[] buffer, String isEncrypted);
	public List<DigitalSignatureInfo> getDigitalSignatureInfoList(byte[] buffer,String isEncrypted);
}
