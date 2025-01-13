package com.novelerp.report.service;

public interface PriceBidReportService {

	/**
	 * validate access
	 * @param bidderId
	 * @return path of generated file
	 */
	public boolean validate(Long bidderId);
}
