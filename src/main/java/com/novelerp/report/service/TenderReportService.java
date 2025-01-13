package com.novelerp.report.service;

import com.novelerp.appbase.master.dto.AttachmentDto;

/**
 * Service to generate the tender document.
 * @author Vivek Birdi
 *
 */
public interface TenderReportService {

	public String generateTenderReport(Long tahdrId);
	
	public AttachmentDto addAttachment(Long tahdrId, String fileName);

	/**
	 * @param tahdrId
	 * @param fileName
	 * @param filePath
	 * @return
	 */
	public AttachmentDto addAttachment(Long tahdrId, String fileName, String filePath);
}
